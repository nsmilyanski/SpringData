package softuni.exam.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "serial_number", unique = true)
    private String serialNumber;


    private BigDecimal price;


    private LocalDateTime takeoff;

    @ManyToOne
    private Town fromTown;

    @ManyToOne
    private Town toTown;

    @ManyToOne
    private Passenger passenger;


    @ManyToOne
    private Plane plane;

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getTakeoff() {
        return takeoff;
    }

    public void setTakeoff(LocalDateTime takeoff) {
        this.takeoff = takeoff;
    }

    public Town getFromTown() {
        return fromTown;
    }

    public void setFromTown(Town fromTown) {
        this.fromTown = fromTown;
    }

    public Town getToTown() {
        return toTown;
    }

    public void setToTown(Town toTown) {
        this.toTown = toTown;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && Objects.equals(serialNumber, ticket.serialNumber) && Objects.equals(price, ticket.price) && Objects.equals(takeoff, ticket.takeoff) && Objects.equals(fromTown, ticket.fromTown) && Objects.equals(toTown, ticket.toTown) && Objects.equals(passenger, ticket.passenger) && Objects.equals(plane, ticket.plane);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serialNumber, price, takeoff, fromTown, toTown, passenger, plane);
    }
}
