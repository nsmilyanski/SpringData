import com.mysql.cj.xdevapi.Collection;
import entities.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeesMaximumSalaries_12 {
    public static void main(String[] args) {


        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery(" SELECT d FROM Department d"
                        , Department.class)
                .getResultStream()
                .map(Department::getEmployees)
                .map(e -> e.stream()
                        .max(Comparator.comparingDouble(d -> d.getSalary().doubleValue()))
                        .orElseThrow(IllegalStateException::new))
                .filter(e -> e.getSalary().compareTo(new BigDecimal("30000")) < 0 || e.getSalary().compareTo(new BigDecimal("70000")) > 0)
                .forEach(e -> System.out.printf("%s %.2f%n", e.getDepartment().getName(), e.getSalary()));


        entityManager.getTransaction().commit();
        entityManager.close();




    }
}
