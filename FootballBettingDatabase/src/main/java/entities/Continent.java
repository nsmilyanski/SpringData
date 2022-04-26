package entities;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "continents")
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(targetEntity = Country.class, mappedBy = "continent")
    private Set<Country> countries;

    public Continent() {
    }

    public Continent(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
}
