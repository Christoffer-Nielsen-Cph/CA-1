package dtos;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class AddressDTO implements Serializable {
    private int id;
    private String address;
    private String additionalInfo;
    private CityInfoInnerDTO cityinfo;
    private Set<PersonInnerDTO> people = new LinkedHashSet<>();

    public AddressDTO() {
    }

    public AddressDTO(int id, String address, String additionalInfo, CityInfoInnerDTO cityinfo, Set<PersonInnerDTO> people) {
        this.id = id;
        this.address = address;
        this.additionalInfo = additionalInfo;
        this.cityinfo = cityinfo;
        this.people = people;
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

    public CityInfoInnerDTO getCityinfo() {
        return cityinfo;
    }

    public void setCityinfo(CityInfoInnerDTO cityinfo) {
        this.cityinfo = cityinfo;
    }

    public Set<PersonInnerDTO> getPeople() {
        return people;
    }

    public void setPeople(Set<PersonInnerDTO> people) {
        this.people = people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDTO entity = (AddressDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.additionalInfo, entity.additionalInfo) &&
                Objects.equals(this.cityinfo, entity.cityinfo) &&
                Objects.equals(this.people, entity.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, additionalInfo, cityinfo, people);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "address = " + address + ", " +
                "additionalInfo = " + additionalInfo + ", " +
                "cityinfo = " + cityinfo + ", " +
                "people = " + people + ")";
    }

    public static class CityInfoInnerDTO implements Serializable {
        private int id;
        private int zipCode;
        private String city;

        public CityInfoInnerDTO() {
        }

        public CityInfoInnerDTO(int id, int zipCode, String city) {
            this.id = id;
            this.zipCode = zipCode;
            this.city = city;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CityInfoInnerDTO entity = (CityInfoInnerDTO) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.zipCode, entity.zipCode) &&
                    Objects.equals(this.city, entity.city);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, zipCode, city);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "zipCode = " + zipCode + ", " +
                    "city = " + city + ")";
        }
    }

    public static class PersonInnerDTO implements Serializable {
        private int id;
        private String email;
        private String firstName;
        private String lastName;

        public PersonInnerDTO() {
        }

        public PersonInnerDTO(int id, String email, String firstName, String lastName) {
            this.id = id;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PersonInnerDTO entity = (PersonInnerDTO) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.email, entity.email) &&
                    Objects.equals(this.firstName, entity.firstName) &&
                    Objects.equals(this.lastName, entity.lastName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, email, firstName, lastName);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "email = " + email + ", " +
                    "firstName = " + firstName + ", " +
                    "lastName = " + lastName + ")";
        }
    }
}
