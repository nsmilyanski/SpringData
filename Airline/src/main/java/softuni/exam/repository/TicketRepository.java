package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    boolean existsTicketBySerialNumber(String serialNumber);

}
