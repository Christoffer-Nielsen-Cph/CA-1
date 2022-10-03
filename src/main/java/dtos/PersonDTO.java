package dtos;

import entities.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class PersonDTO implements Serializable {
    private final int id;
    private final String email;
    private final String firstName;
    private final String lastName;
    private AddressInnerDTO address;
    private final List<HobbyInnerDTO> hobbies = new ArrayList<>();
    private final List<PhoneInnerDTO> phones = new ArrayList<>();

    public PersonDTO(int id, String email, String firstName, String lastName, AddressInnerDTO address) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public PersonDTO(int id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.address = new AddressInnerDTO(person.getAddress());
        person.getHobbies().forEach(hobby -> {
            hobbies.add(new HobbyInnerDTO(hobby));
        });
        person.getPhones().forEach(phone -> {
            phones.add(new PhoneInnerDTO(phone));
        });
    }

    public static List<PersonDTO> getDTOs(List<Person> people) {
        List<PersonDTO> personDTOList = new ArrayList<>();
        people.forEach(person -> {
            personDTOList.add(new PersonDTO(person));
        });
        return personDTOList;

    }


    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public AddressInnerDTO getAddress() {
        return address;
    }

    public List<HobbyInnerDTO> getHobbies() {
        return hobbies;
    }

    public List<PhoneInnerDTO> getPhones() {
        return phones;
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
        private final int id;
        private final String address;
        private final String additionalInfo;
        private int zipCode;
        private String cityName;

        public AddressInnerDTO(int id, String address, String additionalInfo) {
            this.id = id;
            this.address = address;
            this.additionalInfo = additionalInfo;
        }

        public AddressInnerDTO(Address address) {
            this.id = address.getId();
            this.address = address.getAddress();
            this.additionalInfo = address.getAdditionalInfo();
            this.zipCode = address.getCityinfo().getZipCode();
            this.cityName = address.getCityinfo().getCity();
        }

        public int getId() {
            return id;
        }

        public String getAddress() {
            return address;
        }

        public String getAdditionalInfo() {
            return additionalInfo;
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
        private final int id;
        private final String description;
        public HobbyInnerDTO(int id, String description) {
            this.id = id;
            this.description = description;
        }

        public HobbyInnerDTO(Hobby hobby) {
            this.id = hobby.getId();
            this.description = hobby.getDescription();
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "description = " + description + ")";
        }
    }

    public static class PhoneInnerDTO implements Serializable {
        private final int id;
        private final int number;
        private final String description;

        public PhoneInnerDTO(int id, int number, String description) {
            this.id = id;
            this.number = number;
            this.description = description;
        }

        public PhoneInnerDTO(Phone phone) {
            this.id = phone.getId();
            this.number = phone.getNumber();
            this.description = phone.getDescription();
        }

        public int getId() {
            return id;
        }

        public int getNumber() {
            return number;
        }

        public String getDescription() {
            return description;
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
