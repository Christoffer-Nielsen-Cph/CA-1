package dtos;



//make a dto for the person entity, the dto should contain all the fields from the entity, and the dto should be used in the facade and the resource


import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;

import java.util.Set;

public class NewPersonDTO {
    private String email;
    private String firstName;
    private String lastName;
    private Address address;
    private Set<Phone> phones;
    private Set<Hobby> hobbies;

    public NewPersonDTO(String email, String firstName, String lastName, Address address, Set<Phone> phones, Set<Hobby> hobbies) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phones = phones;
        this.hobbies = hobbies;
    }

    public NewPersonDTO(Person person) {
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.address = person.getAddress();
        this.phones = person.getPhones();
        this.hobbies = person.getHobbies();
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "NewPersonDTO{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", phones=" + phones +
                ", hobbies=" + hobbies +
                '}';
    }
}
