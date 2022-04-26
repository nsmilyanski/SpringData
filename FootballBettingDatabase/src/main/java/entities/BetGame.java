package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "bet_games")
public class BetGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(
            name = "betGames_games",
            joinColumns =
            @JoinColumn(name = "betGames_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "games_id", referencedColumnName = "id")
    )
    private Set<Game> games;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resultPrediction_id", referencedColumnName = "id")
    private ResultPrediction resultPrediction;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bet_id", referencedColumnName = "id")
    private Bet bet;


    public BetGame() {
    }

    public BetGame(Set<Game> games, ResultPrediction resultPrediction) {
        this.games = games;
        this.resultPrediction = resultPrediction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public ResultPrediction getResultPrediction() {
        return resultPrediction;
    }

    public void setResultPrediction(ResultPrediction resultPrediction) {
        this.resultPrediction = resultPrediction;
    }
}
