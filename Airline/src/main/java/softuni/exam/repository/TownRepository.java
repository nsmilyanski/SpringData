package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entities.Town;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {
    boolean existsTownByName(String name);
    Town findTownByName(String name);
}
