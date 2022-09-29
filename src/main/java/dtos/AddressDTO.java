package dtos;

import entities.Address;
import entities.Cityinfo;
import entities.Movie;
import entities.Person;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AddressDTO {
    private int id;
    private String address;
    private String additionalInfo;
    private Cityinfo cityinfo;
    private Set<Person> people = new LinkedHashSet<>();

    public AddressDTO(Address address) {
        if(address.getId()!=0)
            this.id = address.getId();
        this.address = address.getAddress();
        this.address = address.getAdditionalInfo();
        this.cityinfo = address.getCityinfo();
        this.people = address.getPeople();
    }

    public static List<AddressDTO> toList(List<Address> addresses) {
        return addresses.stream().map(AddressDTO::new).collect(Collectors.toList());
    }


    public Address getEntity(){
        Address a = new Address(this.address, this.additionalInfo, this.cityinfo, this.people);
        if(id != 0)
            a.setId(this.id);

        return a;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressDTO)) return false;
        AddressDTO that = (AddressDTO) o;
        return id == that.id && address.equals(that.address) && additionalInfo.equals(that.additionalInfo) && cityinfo.equals(that.cityinfo) && people.equals(that.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, additionalInfo, cityinfo, people);
    }
}
