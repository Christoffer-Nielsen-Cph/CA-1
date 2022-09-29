package dtos;

import entities.Movie;
import entities.Person;
import entities.Phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PhoneDTO {
    private int id;
    private int number;
    private String description;
    private Person person;

    public PhoneDTO(Phone phone) {
        if(phone.getId()!=0)
            this.id = phone.getId();
        this.number = phone.getNumber();
        this.description = phone.getDescription();
        this.person = phone.getPerson();
    }

    public static List<PhoneDTO> toList(List<Phone> phones) {
        return phones.stream().map(PhoneDTO::new).collect(Collectors.toList());
    }


    public Phone getEntity(){
        Phone p = new Phone(this.number, this.description, this.person);
        if(id != 0)
            p.setId(this.id);

        return p;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneDTO)) return false;
        PhoneDTO phoneDTO = (PhoneDTO) o;
        return id == phoneDTO.id && number == phoneDTO.number && description.equals(phoneDTO.description) && person.equals(phoneDTO.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, description, person);
    }
}
