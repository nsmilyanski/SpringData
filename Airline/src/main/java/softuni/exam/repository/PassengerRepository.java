package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entities.Passenger;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    boolean existsPassengerByEmail(String email);
    Passenger findPassengerByEmail(String email);

    @Query("select distinct p from Passenger p join fetch p.tickets t " +
            "order by size(t) desc, p.email asc ")
    List<Passenger> findAllPassengersOrderByTicketsDesc();
}
