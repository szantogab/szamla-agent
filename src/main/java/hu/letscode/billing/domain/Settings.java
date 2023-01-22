package hu.letscode.billing.domain;

import javax.xml.bind.annotation.XmlElement;

/**
 * Object containing information about the users' account which the client operating on behalf. To be serialized as XML.
 */
@SuppressWarnings("PMD.UnusedPrivateField")
public class Settings {

    @XmlElement(name = "felhasznalo", required = false)
    private String user;

    @XmlElement(name = "szamlaagentkulcs", required = false)
    private String agentKey;

    @XmlElement(name = "jelszo", required = false)
    private String password;

    @XmlElement(name = "eszamla", required = false)
    private Boolean electricBill;

    @XmlElement(name = "kulcstartojelszo", required = false)
    private String keyChainPassword;

    @XmlElement(name = "szamlaLetoltes", required = false)
    private Boolean downloadBill;

    @XmlElement(name = "valaszVerzio", required = false)
    private Integer answerType;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAgentKey() {
        return agentKey;
    }

    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getElectricBill() {
        return electricBill;
    }

    public void setElectricBill(Boolean electricBill) {
        this.electricBill = electricBill;
    }

    public String getKeyChainPassword() {
        return keyChainPassword;
    }

    public void setKeyChainPassword(String keyChainPassword) {
        this.keyChainPassword = keyChainPassword;
    }

    public Boolean getDownloadBill() {
        return downloadBill;
    }

    public void setDownloadBill(Boolean downloadBill) {
        this.downloadBill = downloadBill;
    }

    public Integer getAnswerType() {
        return answerType;
    }

    public void setAnswerType(Integer answerType) {
        this.answerType = answerType;
    }
}
