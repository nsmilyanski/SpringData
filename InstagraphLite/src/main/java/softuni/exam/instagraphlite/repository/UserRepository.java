package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsUserByUsername(String username);

    User findUserByUsername(String username);

    @Query("select distinct u from User u join fetch u.posts p " +
            "order by size(p) desc ")
    List<User> fidAllUsersOrderByCountPostDescThenByUserIdAcs();
}
