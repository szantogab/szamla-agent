package hu.letscode.billing.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aMultipart;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToXml;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.common.io.ByteStreams;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import hu.letscode.billing.client.factory.HttpPostFactory;
import hu.letscode.billing.client.factory.TrustAllHttpClientFactory;
import hu.letscode.billing.domain.BillingCreateResponse;
import hu.letscode.billing.domain.BillingRequest;
import hu.letscode.billing.domain.Buyer;
import hu.letscode.billing.domain.Header;
import hu.letscode.billing.domain.Item;
import hu.letscode.billing.domain.Language;
import hu.letscode.billing.domain.Seller;
import hu.letscode.billing.domain.Settings;
import hu.letscode.billing.domain.TaxCode;
import hu.letscode.billing.domain.factory.ItemFactory;
import hu.letscode.billing.service.factory.BillingServiceFactory;

/**
 * @author Krisztian_Papp Test class for {@link SzamlaAgentClient}.
 */
public class CreateBillTest {

    private SzamlaAgentClient underTest;
    private XmlMapper xmlMapper;
    private final String apiUrl = "http://localhost:8089";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Before
    public void setUp() {
        underTest = new SzamlaAgentClient(new TrustAllHttpClientFactory(), new HttpPostFactory(), apiUrl);
        xmlMapper = BillingServiceFactory.createXmlMapper();
    }

    @Test
    public void itShouldSerializeRequestAccordingToScheme() throws IOException {
        // GIVEN
        stubBillingResponse("mock/response/create_bill_failure.xml");
        byte[] content = xmlMapper.writeValueAsBytes(createBillingRequest());
        // WHEN
        underTest.execute(XmlField.CREATE_BILL, content);
        // THEN
        verifyRequest("expected/request/create_bill.xml", "action-xmlagentxmlfile");
    }

    @Test
    public void itShouldSerializeDifferentTaxcodes() throws IOException {
        // GIVEN
        stubBillingResponse("mock/response/create_bill_failure.xml");
        BillingRequest createBillingRequest = createBillingRequest();
        createBillingRequest.setItems(createFullTaxItemList());
		byte[] content = xmlMapper.writeValueAsBytes(createBillingRequest);
        // WHEN
        underTest.execute(XmlField.CREATE_BILL, content);
        // THEN
        verifyRequest("expected/request/create_bill_with_different_vat_rate.xml", "action-xmlagentxmlfile");
    }

    @Test
    public void itShouldSerializeResponseWhenFailure() throws IOException {
        // GIVEN
        stubBillingResponse("mock/response/create_bill_failure.xml");
        byte[] content = xmlMapper.writeValueAsBytes(createBillingRequest());
        // WHEN
        String actual = underTest.execute(XmlField.CREATE_BILL, content);
        // THEN
        BillingCreateResponse response = xmlMapper.readValue(actual, BillingCreateResponse.class);
        assertEquals(false, response.isSuccess());
        assertEquals("3", response.getErrorCode());
        assertEquals("Bejelentkezési hiba - a megadott login név és jelszó pároshoz nem létezik felhasználó",
                response.getErrorMessage());
    }

    @Test
    public void itShouldSerializeResponseWhenSuccess() throws JAXBException, IOException {
        // GIVEN
        stubBillingResponse("mock/response/create_bill_success.xml");
        byte[] content = xmlMapper.writeValueAsBytes(createBillingRequest());
        // WHEN
        String actual = underTest.execute(XmlField.CREATE_BILL, content);
        // THEN
        BillingCreateResponse response = xmlMapper.readValue(actual, BillingCreateResponse.class);
        assertEquals(true, response.isSuccess());
        assertEquals("XXX-2012-3", response.getBillNumber());
        assertEquals(BigDecimal.valueOf(30000), response.getBillNetValue());
        assertEquals(BigDecimal.valueOf(38100), response.getBillGrossValue());
        assertEquals(BigDecimal.valueOf(38100), response.getReceivable());
        assertEquals("content", response.getPdfContentBase64());
        assertEquals("someurl", response.getUrlForBuyer());
    }

    private void stubBillingResponse(String responseFile) throws IOException {
        byte[] response = ByteStreams.toByteArray(getClass().getClassLoader().getResourceAsStream(responseFile));
        stubFor(post("/").willReturn(aResponse().withStatus(200).withBody(response)));
    }

    private void verifyRequest(String requestFile, String fieldName) throws IOException {
        byte[] response = ByteStreams.toByteArray(getClass().getClassLoader().getResourceAsStream(requestFile));
        wireMockRule.verify(postRequestedFor(urlEqualTo("/")).withAllRequestBodyParts(aMultipart(fieldName)
                .withBody(equalToXml(new String(response)))));
    }

    private BillingRequest createBillingRequest() {
        BillingRequest billingRequest = new BillingRequest();
        billingRequest.setBuyer(createBuyer()).setSeller(createSeller()).setHeader(createHeader())
                .setItems(createItemList()).setSettings(createSettings());
        return billingRequest;
    }

    private Seller createSeller() {
        Seller seller = new Seller();
        seller.setAccountNumber("555555555-55555555-55555555");
        seller.setBankName("OTP");
        seller.setEmailContent("Fizesse ki a számlát");
        seller.setEmailSubject("Számla értesítő");
        seller.setSellerSignatory("Papp Krisztián");
        return seller;
    }

    private Settings createSettings() {
        Settings settings = new Settings();
        settings.setAnswerType(2).setDownloadBill(false).seteBill(false)
                .setAgentKey("ujcrrs4aqcxim9bp32rsv5ek6tpkbedwqtns2k7xz6");
        return settings;
    }

    private List<Item> createItemList() {
        List<Item> items = new ArrayList<>();
        items.add(createItem());
        return items;
    }

    private Item createItem() {
        ItemFactory factory = new ItemFactory();
        return factory.create("name", "test", "db", BigDecimal.TEN, BigDecimal.ONE, TaxCode.AAM);
    }

    private List<Item> createFullTaxItemList() {
        List<Item> items = new ArrayList<>();
        items.add(createFullTaxItem());
        return items;
    }

    private Item createFullTaxItem() {
        ItemFactory factory = new ItemFactory();
        return factory.create("name", "test", "db", BigDecimal.TEN, BigDecimal.ONE, TaxCode.TWENTYSEVEN);
    }

    private Buyer createBuyer() {
        Buyer buyer = new Buyer();
        buyer.setSendMail(true).setComment("test").setAddress("address").setCity("city")
                .setEmail("krisztian@letscode.hu").setName("Papp Krisztián").setPhoneNumber("555-666")
                .setPostalCode("BN1 6UQ").setTaxNumber("12345678-1-42").setSignatoryName("Papp Krisztián");
        return buyer;
    }

    private Header createHeader() {
        Header header = new Header();
        header.setComment("test").setCorrectionBill(false).setFinalBill(false).setLanguage(Language.HU)
                .setDeadlineDate(LocalDateTime.of(2020, 11, 29, 0,0))
                .setLeavenedDate(LocalDateTime.of(2020, 11, 29, 0,0))
                .setPaymentType("Átutalás").setCurrency(Currency.getInstance("HUF")).setImprestBill(false)
                .setExchangeBank("OTP").setExchangeRate(BigDecimal.ONE)
                .setFulfillmentDate(LocalDateTime.of(2020, 11, 29, 0,0));
        return header;
    }

}
