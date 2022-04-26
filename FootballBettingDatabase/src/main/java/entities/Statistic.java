package entities;

import javax.persistence.*;

@Entity
@Table(name = "statistics")
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    private Game game;

    @Column(name = "scored_goals")
    private int scoredGoals;

    @Column(name = "player_assists")
    private int playerAssists;

    @Column(name = "played_minutes_during_game")
    private int playedMinutesDuringGame;

    @OneToOne(mappedBy = "statistic")
    private Player player;

    public Statistic() {
    }

    public Statistic(int scoredGoals, int playerAssists, int playedMinutesDuringGame) {
        this.scoredGoals = scoredGoals;
        this.playerAssists = playerAssists;
        this.playedMinutesDuringGame = playedMinutesDuringGame;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public int getPlayerAssists() {
        return playerAssists;
    }

    public void setPlayerAssists(int playerAssists) {
        this.playerAssists = playerAssists;
    }

    public int getPlayedMinutesDuringGame() {
        return playedMinutesDuringGame;
    }

    public void setPlayedMinutesDuringGame(int playedMinutesDuringGame) {
        this.playedMinutesDuringGame = playedMinutesDuringGame;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
