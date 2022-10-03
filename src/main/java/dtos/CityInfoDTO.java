package dtos;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class CityInfoDTO implements Serializable {
    private int id;
    private int zipCode;
    private String city;
    private Set<AddressInnerDTO> addresses = new LinkedHashSet<>();

    public CityInfoDTO() {
    }

    public CityInfoDTO(int id, int zipCode, String city, Set<AddressInnerDTO> addresses) {
        this.id = id;
        this.zipCode = zipCode;
        this.city = city;
        this.addresses = addresses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<AddressInnerDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressInnerDTO> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityInfoDTO entity = (CityInfoDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.zipCode, entity.zipCode) &&
                Objects.equals(this.city, entity.city) &&
                Objects.equals(this.addresses, entity.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, zipCode, city, addresses);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "zipCode = " + zipCode + ", " +
                "city = " + city + ", " +
                "addresses = " + addresses + ")";
    }

    public static class AddressInnerDTO implements Serializable {
        private int id;
        private String address;
        private String additionalInfo;

        public AddressInnerDTO() {
        }

        public AddressInnerDTO(int id, String address, String additionalInfo) {
            this.id = id;
            this.address = address;
            this.additionalInfo = additionalInfo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAdditionalInfo() {
            return additionalInfo;
        }

        public void setAdditionalInfo(String additionalInfo) {
            this.additionalInfo = additionalInfo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AddressInnerDTO entity = (AddressInnerDTO) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.address, entity.address) &&
                    Objects.equals(this.additionalInfo, entity.additionalInfo);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, address, additionalInfo);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "address = " + address + ", " +
                    "additionalInfo = " + additionalInfo + ")";
        }
    }
}
