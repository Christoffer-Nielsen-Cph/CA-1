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
    public PhoneDTO(int id, int number, String description) {
        this.id = id;
        this.number = number;
        this.description = description;
    }
    public PhoneDTO(Phone phone) {
        this.id = phone.getId();
        this.number = phone.getNumber();
        this.description = phone.getDescription();

    }
    public static List<PhoneDTO> getDTOs(List<Phone> phones) {
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
    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "number = " + number + ", " +
                "description = " + description +
                ")";
    }
}
