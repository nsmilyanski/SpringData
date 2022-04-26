package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    private Team homeTeam;

    @ManyToOne(optional = false)
    private Team awayTeam;


    @Column(name = "home_goals")
    private int homeGoals;


    @Column(name = "away_goals")
    private int awayGoals;


    @Column(name = "time_of_game", nullable = false)
    private LocalDate timeOfGame;

    @Column(name = "home_team_win_bet_rate")
    private float homeTeamWinBetRate;

    @Column(name = "away_team_win_bet_rate")
    private float awayTeamWinBetRate;


    @Column(name = "draw_bet_rate")
    private float drawBetRate;

    @ManyToOne(optional = false)
    private Round round;

    @ManyToOne(optional = false)
    private Competition competition;

    @OneToMany(targetEntity = Statistic.class, mappedBy = "game")
    private Set<Statistic> statistics;

    @ManyToMany(mappedBy = "games")
    private Set<Player> players;

    @ManyToMany
    @JoinTable
    private Set<BetGame> betGames;

    public Game() {
    }

    public Game(Team homeTeam, Team awayTeam, int homeGoals, int awayGoals, LocalDate timeOfGame, float homeTeamWinBetRate, float awayTeamWinBetRate, float drawBetRate) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.timeOfGame = timeOfGame;
        this.homeTeamWinBetRate = homeTeamWinBetRate;
        this.awayTeamWinBetRate = awayTeamWinBetRate;
        this.drawBetRate = drawBetRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public LocalDate getTimeOfGame() {
        return timeOfGame;
    }

    public void setTimeOfGame(LocalDate timeOfGame) {
        this.timeOfGame = timeOfGame;
    }

    public float getHomeTeamWinBetRate() {
        return homeTeamWinBetRate;
    }

    public void setHomeTeamWinBetRate(float homeTeamWinBetRate) {
        this.homeTeamWinBetRate = homeTeamWinBetRate;
    }

    public float getAwayTeamWinBetRate() {
        return awayTeamWinBetRate;
    }

    public void setAwayTeamWinBetRate(float awayTeamWinBetRate) {
        this.awayTeamWinBetRate = awayTeamWinBetRate;
    }

    public float getDrawBetRate() {
        return drawBetRate;
    }

    public void setDrawBetRate(float drawBetRate) {
        this.drawBetRate = drawBetRate;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Set<Statistic> getStatistics() {
        return statistics;
    }

    public void setStatistics(Set<Statistic> statistics) {
        this.statistics = statistics;
    }

    //    Home team Win bet rate, Away Team Win Bet Rate, Draw Game Bet Rate, Round, Competition)


}
