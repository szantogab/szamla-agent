package hu.letscode.billing.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * The complete billing request. To be serialized as XML.
 */
@SuppressWarnings("PMD.UnusedPrivateField")
@XmlRootElement(name = "xmltaxpayer")
public class QueryTaxPayerRequest {

    @JacksonXmlProperty(isAttribute = true)
    private final String xmlns = "http://www.szamlazz.hu/xmltaxpayer";

    @XmlElement(name = "beallitasok")
    private Settings settings;

    @XmlElement(name = "torzsszam")
    private String taxNo;

    public QueryTaxPayerRequest setSettings(Settings settings) {
        this.settings = settings;
        return this;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }
}