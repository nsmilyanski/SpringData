package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "initial_letters", nullable = false, length = 3)
    private String initialLetters;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Continent continent;

    @OneToMany(targetEntity = Town.class, mappedBy = "country")
    private Set<Town> towns;

    public Country() {
    }

    public Country(String initialLetters, String name) {
        this.initialLetters = initialLetters;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInitialLetters() {
        return initialLetters;
    }

    public void setInitialLetters(String initialLetters) {
        this.initialLetters = initialLetters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }
}
