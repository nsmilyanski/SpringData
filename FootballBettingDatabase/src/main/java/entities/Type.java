package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(targetEntity = Competition.class, mappedBy = "type")
    private Set<Competition> competitions;

    public Type() {
    }

    public Type(String name) {
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

    public Set<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Set<Competition> competitions) {
        this.competitions = competitions;
    }
}
