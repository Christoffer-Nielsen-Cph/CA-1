package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "cityinfo")
public class Cityinfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cityinfo_id", nullable = false)
    private int id;

    @Column(name = "zipCode", nullable = false)
    private int zipCode;

    @Column(name = "city", nullable = false, length = 45)
    private String city;

    @OneToMany(mappedBy = "cityinfo")
    private Set<Address> addresses = new LinkedHashSet<>();

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

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Cityinfo{" +
                "id=" + id +
                ", zipCode=" + zipCode +
                ", city='" + city + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}