package entities;

import javax.persistence.*;

@Entity
@Table(name = "result_prediction")
public class ResultPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String prediction;

    @OneToOne(mappedBy = "resultPrediction")
    private BetGame betGame;

    public ResultPrediction() {
    }

    public ResultPrediction(String prediction) {
        this.prediction = prediction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public BetGame getBetGame() {
        return betGame;
    }

    public void setBetGame(BetGame betGame) {
        this.betGame = betGame;
    }
}
