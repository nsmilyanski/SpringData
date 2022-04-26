package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private BigDecimal money;

    @Column(name = "date_of_bet")
    private LocalDate timeOfBet;

    @ManyToOne(optional = false)
    private User user;

    public Bet() {
    }

    public Bet(BigDecimal money, LocalDate timeOfBet) {
        this.money = money;
        this.timeOfBet = timeOfBet;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public LocalDate getTimeOfBet() {
        return timeOfBet;
    }

    public void setTimeOfBet(LocalDate timeOfBet) {
        this.timeOfBet = timeOfBet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
