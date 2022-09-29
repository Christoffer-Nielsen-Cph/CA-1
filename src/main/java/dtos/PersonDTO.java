package dtos;

import entities.Movie;
import entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonDTO {
    private int person_Id;
    private String email;
    private String firstName;
    private String lastName;




    public PersonDTO(Person person) {
        if(person.getId()!=0)
            this.person_Id = person.getId();
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
    }

    public static List<PersonDTO> toList(List<Person> persons) {
        return persons.stream().map(PersonDTO::new).collect(Collectors.toList());
    }


    public Person getEntity(){
        Person p = new Person(this.email,this.firstName, this.lastName);
        if(person_Id != 0)
            p.setId(this.person_Id);

        return p;
    }

    public void setPerson_Id(int person_Id) {
        this.person_Id = person_Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return person_Id == personDTO.person_Id && email.equals(personDTO.email) && firstName.equals(personDTO.firstName) && lastName.equals(personDTO.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person_Id, email, firstName, lastName);
    }


}

