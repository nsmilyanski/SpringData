package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(nullable = false)
    private BigDecimal balance;

    @OneToMany(targetEntity = Bet.class, mappedBy = "user")
    private Set<Bet> bets;

    public User() {
    }

    public User(String username, String password, String email, String fullName, BigDecimal balance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Set<Bet> getBets() {
        return bets;
    }

    public void setBets(Set<Bet> bets) {
        this.bets = bets;
    }
}
