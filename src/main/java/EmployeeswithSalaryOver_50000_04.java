import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class EmployeeswithSalaryOver_50000_04 {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();


        List<String> richEmployees = entityManager.createQuery("select firstName from Employee e" +
                " where e.salary > 50000 ", String.class)
                .getResultList();

        for (String employee: richEmployees) {
            System.out.println(employee);
        }

        entityManager.getTransaction().commit();



    }
}
