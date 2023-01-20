package hu.letscode.billing.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * The API response.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryTaxPayerResponse {

    @JacksonXmlProperty(localName = "sikeres")
    private boolean success;

    @JacksonXmlProperty(localName = "taxpayerValidity")
    private boolean validity;

    @JacksonXmlProperty(localName = "taxPayerData")
    private TaxPayerData taxPayerData;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isValidity() {
        return validity;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    public TaxPayerData getTaxPayerData() {
        return taxPayerData;
    }

    public void setTaxPayerData(TaxPayerData taxPayerData) {
        this.taxPayerData = taxPayerData;
    }

    public static class TaxPayerData {
        @JacksonXmlProperty(localName = "taxpayerName")
        private String taxpayerName;

        @JacksonXmlProperty(localName = "taxNumberDetail")
        private TaxNumberDetail taxNumberDetail;

        @JacksonXmlProperty(localName = "taxPayerAddressList")
        private ArrayList<TaxPayerAddressItem> taxPayerAddresses;

        public static class TaxNumberDetail {
            private String taxpayerId;
            private String vatCode;

            public String getTaxpayerId() {
                return taxpayerId;
            }

            public void setTaxpayerId(String taxpayerId) {
                this.taxpayerId = taxpayerId;
            }

            public String getVatCode() {
                return vatCode;
            }

            public void setVatCode(String vatCode) {
                this.vatCode = vatCode;
            }
        }

        public static class TaxPayerAddressItem {
            @JacksonXmlProperty(localName = "taxpayerAddressType")
            private String addressType;
            @JacksonXmlProperty(localName = "taxpayerAddress")
            private TaxpayerAddress address;

            public static class TaxpayerAddress {
                @JacksonXmlProperty(localName = "countryCode")
                private String countryCode;
                @JacksonXmlProperty(localName = "postalCode")
                private String postalCode;
                @JacksonXmlProperty(localName = "city")
                private String city;
                @JacksonXmlProperty(localName = "streetName")
                private String streetName;
                @JacksonXmlProperty(localName = "publicPlaceCategory")
                private String publicPlaceCategory;
                @JacksonXmlProperty(localName = "number")
                private String number;

                public String getCountryCode() {
                    return countryCode;
                }

                public void setCountryCode(String countryCode) {
                    this.countryCode = countryCode;
                }

                public String getPostalCode() {
                    return postalCode;
                }

                public void setPostalCode(String postalCode) {
                    this.postalCode = postalCode;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getStreetName() {
                    return streetName;
                }

                public void setStreetName(String streetName) {
                    this.streetName = streetName;
                }

                public String getPublicPlaceCategory() {
                    return publicPlaceCategory;
                }

                public void setPublicPlaceCategory(String publicPlaceCategory) {
                    this.publicPlaceCategory = publicPlaceCategory;
                }

                public String getNumber() {
                    return number;
                }

                public void setNumber(String number) {
                    this.number = number;
                }
            }

            public String getAddressType() {
                return addressType;
            }

            public void setAddressType(String addressType) {
                this.addressType = addressType;
            }

            public TaxpayerAddress getAddress() {
                return address;
            }

            public void setAddress(TaxpayerAddress address) {
                this.address = address;
            }
        }
    }
}