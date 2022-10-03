package dtos;

import entities.Person;
import entities.Phone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhoneDTO implements Serializable {
    private final int id;
    private final int number;
    private final String description;
    private final PersonInnerDTO person;

    public PhoneDTO(int id, int number, String description, PersonInnerDTO person) {
        this.id = id;
        this.number = number;
        this.description = description;
        this.person = person;
    }

    public PhoneDTO(Phone phone) {
        this.id = phone.getId();
        this.number = phone.getNumber();
        this.description = phone.getDescription();
        this.person = new PersonInnerDTO(phone.getPerson());


    }
    public static List<PhoneDTO> getDTOs(List<Phone> phones){
        List<PhoneDTO> phoneDTOList = new ArrayList<>();
        phones.forEach(phone -> {
            phoneDTOList.add(new PhoneDTO(phone));
        });
        return phoneDTOList;

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

    public PersonInnerDTO getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "number = " + number + ", " +
                "description = " + description + ", " +
                "person = " + person + ")";
    }

    public static class PersonInnerDTO implements Serializable {
        private final int id;
        private final String email;
        private final String firstName;
        private final String lastName;

        public PersonInnerDTO(int id, String email, String firstName, String lastName) {
            this.id = id;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public PersonInnerDTO(Person person) {
            this.id = person.getId();
            this.email = person.getEmail();
            this.firstName = person.getFirstName();
            this.lastName = person.getLastName();
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
