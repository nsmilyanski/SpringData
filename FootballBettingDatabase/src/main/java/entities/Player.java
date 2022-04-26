package entities;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(name = "squad_number", nullable = false)
    private int squadNumber;

    @ManyToOne(optional = false)
    private Team team;

    @ManyToOne(optional = false)
    private Position position;

    @Column(name = "is_currently_injured")
    private boolean isCurrentlyInjured;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statistic", referencedColumnName = "id")
    private Statistic statistic;

    @ManyToMany
    @JoinTable(
            name = "players_games",
            joinColumns =
            @JoinColumn(name = "players_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "games_id", referencedColumnName = "id")
    )
    private Set<Game> games;

    public Player() {
    }

    public Player(String name, int squadNumber) {
        this.name = name;
        this.squadNumber = squadNumber;
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

    public int getSquadNumber() {
        return squadNumber;
    }

    public void setSquadNumber(int squadNumber) {
        this.squadNumber = squadNumber;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isCurrentlyInjured() {
        return isCurrentlyInjured;
    }

    public void setCurrentlyInjured(boolean currentlyInjured) {
        isCurrentlyInjured = currentlyInjured;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }
}
