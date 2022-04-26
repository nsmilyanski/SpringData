package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(name = "letter_initials", length = 3, nullable = false)
    private String letterInitials;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "primaryKitColor_id", referencedColumnName = "id")
    private Color primaryKitColor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "secondaryKitColor_id", referencedColumnName = "id")
    private Color secondaryKitColor;

    private float budget;

    @ManyToOne(optional = false)
    private Town town;

    @OneToOne(targetEntity = Player.class, mappedBy = "team")
    private Set<Player> players;

    public Team() {
    }

    public Team(String name, String letterInitials, Color primaryKitColor, Color secondaryKitColor, float budget) {
        this.name = name;
        this.letterInitials = letterInitials;
        this.primaryKitColor = primaryKitColor;
        this.secondaryKitColor = secondaryKitColor;
        this.budget = budget;
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

    public String getLetterInitials() {
        return letterInitials;
    }

    public void setLetterInitials(String letterInitials) {
        this.letterInitials = letterInitials;
    }

    public Color getPrimaryKitColor() {
        return primaryKitColor;
    }

    public void setPrimaryKitColor(Color primaryKitColor) {
        this.primaryKitColor = primaryKitColor;
    }

    public Color getSecondaryKitColor() {
        return secondaryKitColor;
    }

    public void setSecondaryKitColor(Color secondaryKitColor) {
        this.secondaryKitColor = secondaryKitColor;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
