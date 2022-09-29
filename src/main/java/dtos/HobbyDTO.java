package dtos;

import entities.Hobby;
import entities.Hobby;
import entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class HobbyDTO {
    private int id;
    private String desciption;
    private Set<Person> people;

    public HobbyDTO(Hobby Hobby) {
        if(Hobby.getId()!=0)
            this.id = Hobby.getId();
    }

    public static List<HobbyDTO> toList(List<Hobby> Hobbys) {
        return Hobbys.stream().map(HobbyDTO::new).collect(Collectors.toList());
    }


    public Hobby getEntity(){
        Hobby m = new Hobby(this.desciption,this.people);
        if(id != 0)
            m.setId(this.id);

        return m;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HobbyDTO HobbyDTO = (HobbyDTO) o;
        return id == HobbyDTO.id && desciption == HobbyDTO.desciption && people.equals(HobbyDTO.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, people, desciption);
    }
}
