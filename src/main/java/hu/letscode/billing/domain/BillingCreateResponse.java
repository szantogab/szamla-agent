package hu.letscode.billing.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.math.BigDecimal;

/**
 * The API response.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillingCreateResponse {

    @JacksonXmlProperty(localName = "sikeres")
    private boolean success;
    @JacksonXmlProperty(localName = "szamlaszam")
    private String billNumber;
    @JacksonXmlProperty(localName = "szamlanetto")
    private BigDecimal billNetValue;
    @JacksonXmlProperty(localName = "szamlabrutto")
    private BigDecimal billGrossValue;
    @JacksonXmlProperty(localName = "pdf")
    private String pdfContentBase64;
    @JacksonXmlProperty(localName = "hibakod")
    private String errorCode;
    @JacksonXmlProperty(localName = "hibauzenet")
    private String errorMessage;
    @JacksonXmlProperty(localName = "kintlevoseg")
    private BigDecimal receivable;
    @JacksonXmlProperty(localName = "vevoifiokurl")
    private String urlForBuyer;
    // CPD-OFF

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public BigDecimal getBillNetValue() {
        return billNetValue;
    }

    public void setBillNetValue(BigDecimal billNetValue) {
        this.billNetValue = billNetValue;
    }

    public BigDecimal getBillGrossValue() {
        return billGrossValue;
    }

    public void setBillGrossValue(BigDecimal billGrossValue) {
        this.billGrossValue = billGrossValue;
    }

    public String getPdfContentBase64() {
        return pdfContentBase64;
    }

    public void setPdfContentBase64(String pdfContent) {
        this.pdfContentBase64 = pdfContent;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public BigDecimal getReceivable() {
        return receivable;
    }

    public void setReceivable(BigDecimal receivable) {
        this.receivable = receivable;
    }

    public String getUrlForBuyer() {
        return urlForBuyer;
    }

    public void setUrlForBuyer(String urlForBuyer) {
        this.urlForBuyer = urlForBuyer;
    }
    // CPD-ON

}
