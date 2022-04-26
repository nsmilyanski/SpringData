package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "positions")
public class Position {

    @Id
    @Column(nullable = false, length = 2, unique = true)
    private String id;


    @Column(name = "position_description")
    private String positionDescription;


    @OneToMany(targetEntity = Player.class, mappedBy = "position")
    private Set<Player> players;

    public Position() {
    }

    public Position(String positionDescription) {
        this.positionDescription = positionDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
