package dtos;

import entities.Person;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class PersonDTO implements Serializable {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private AddressInnerDTO address;
    private Set<HobbyInnerDTO> hobbies = new LinkedHashSet<>();
    private Set<PhoneInnerDTO> phones = new LinkedHashSet<>();

    public PersonDTO() {
    }

    public PersonDTO(int id, String email, String firstName, String lastName, AddressInnerDTO address, Set<HobbyInnerDTO> hobbies, Set<PhoneInnerDTO> phones) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.hobbies = hobbies;
        this.phones = phones;
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

    public AddressInnerDTO getAddress() {
        return address;
    }

    public void setAddress(AddressInnerDTO address) {
        this.address = address;
    }

    public Set<HobbyInnerDTO> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<HobbyInnerDTO> hobbies) {
        this.hobbies = hobbies;
    }

    public Set<PhoneInnerDTO> getPhones() {
        return phones;
    }

    public void setPhones(Set<PhoneInnerDTO> phones) {
        this.phones = phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO entity = (PersonDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.hobbies, entity.hobbies) &&
                Objects.equals(this.phones, entity.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName, address, hobbies, phones);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "email = " + email + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "address = " + address + ", " +
                "hobbies = " + hobbies + ", " +
                "phones = " + phones + ")";
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

    public static class HobbyInnerDTO implements Serializable {
        private int id;
        private String description;

        public HobbyInnerDTO() {
        }

        public HobbyInnerDTO(int id, String description) {
            this.id = id;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HobbyInnerDTO entity = (HobbyInnerDTO) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.description, entity.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, description);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "description = " + description + ")";
        }
    }

    public static class PhoneInnerDTO implements Serializable {
        private int id;
        private int number;
        private String description;

        public PhoneInnerDTO() {
        }

        public PhoneInnerDTO(int id, int number, String description) {
            this.id = id;
            this.number = number;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PhoneInnerDTO entity = (PhoneInnerDTO) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.number, entity.number) &&
                    Objects.equals(this.description, entity.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, number, description);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "number = " + number + ", " +
                    "description = " + description + ")";
        }
    }
}
