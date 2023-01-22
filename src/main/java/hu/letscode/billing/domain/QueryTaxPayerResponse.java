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
    @JacksonXmlProperty(localName = "taxpayerValidity")
    private boolean validity;

    @JacksonXmlProperty(localName = "taxpayerData")
    private TaxPayerData taxPayerData;

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

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TaxPayerData {
        @JacksonXmlProperty(localName = "taxpayerName")
        private String name;

        @JacksonXmlProperty(localName = "taxpayerShortName")
        private String shortName;

        @JacksonXmlProperty(localName = "taxNumberDetail")
        private TaxNumberDetail taxNumberDetail;

        @JacksonXmlProperty(localName = "incorporation")
        private String incorporation;

        @JacksonXmlProperty(localName = "taxpayerAddressList")
        private ArrayList<TaxPayerAddressItem> taxPayerAddresses;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public TaxNumberDetail getTaxNumberDetail() {
            return taxNumberDetail;
        }

        public void setTaxNumberDetail(TaxNumberDetail taxNumberDetail) {
            this.taxNumberDetail = taxNumberDetail;
        }

        public ArrayList<TaxPayerAddressItem> getTaxPayerAddresses() {
            return taxPayerAddresses;
        }

        public void setTaxPayerAddresses(ArrayList<TaxPayerAddressItem> taxPayerAddresses) {
            this.taxPayerAddresses = taxPayerAddresses;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class TaxNumberDetail {
            @JacksonXmlProperty(localName = "taxpayerId")
            private String taxPayerId;

            @JacksonXmlProperty(localName = "vatCode")
            private String vatCode;

            @JacksonXmlProperty(localName = "countyCode")
            private String countyCode;

            public String getTaxPayerId() {
                return taxPayerId;
            }

            public void setTaxPayerId(String taxPayerId) {
                this.taxPayerId = taxPayerId;
            }

            public String getVatCode() {
                return vatCode;
            }

            public void setVatCode(String vatCode) {
                this.vatCode = vatCode;
            }

            public String getCountyCode() {
                return countyCode;
            }

            public void setCountyCode(String countyCode) {
                this.countyCode = countyCode;
            }
        }
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class TaxPayerAddressItem {
            @JacksonXmlProperty(localName = "taxpayerAddressType")
            private String addressType;
            @JacksonXmlProperty(localName = "taxpayerAddress")
            private TaxpayerAddress address;

            @JsonIgnoreProperties(ignoreUnknown = true)
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