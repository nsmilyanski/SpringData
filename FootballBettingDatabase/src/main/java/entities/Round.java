package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rounds")
public class Round {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(nullable = false)
    private String name;

    @OneToMany(targetEntity = Game.class, mappedBy = "round")
    private Set<Game> games;

    public Round() {
    }

    public Round(String name) {
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
