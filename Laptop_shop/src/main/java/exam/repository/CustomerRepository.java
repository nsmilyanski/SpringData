package exam.repository;

import exam.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsCustomerByEmail(@Email String email);
}
