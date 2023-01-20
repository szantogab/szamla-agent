package hu.letscode.billing.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The complete billing request. To be serialized as XML.
 */
@SuppressWarnings("PMD.UnusedPrivateField")
@XmlRootElement(name = "xmlszamlapdf")
public class PdfRequest {

    @JacksonXmlProperty(isAttribute = true)
    private final String xmlns = "http://www.szamlazz.hu/xmlszamlapdf";

    @XmlElement(name = "felhasznalo", required = false)
    private String user;

    @XmlElement(name = "szamlaagentkulcs", required = false)
    private String agentKey;

    @XmlElement(name = "jelszo", required = false)
    private String password;

    @XmlElement(name = "szamlaszam", required = true)
    private String invoiceNo;

    @XmlElement(name = "szamlaKulsoAzon", required = true)
    private String externalInvoiceNo;

    @XmlElement(name = "valaszVerzio", required = true)
    private int answerType = 2;

    public void setUser(String user) {
        this.user = user;
    }

    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public void setExternalInvoiceNo(String externalInvoiceNo) {
        this.externalInvoiceNo = externalInvoiceNo;
    }

    public void setAnswerType(int answerType) {
        this.answerType = answerType;
    }
}
