import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class IncreaseSalaries_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

      List<String> employeeDepartments = List.of("Engineering", "Tool Design",
              "Marketing", "Information Services");

//        entityManager.createQuery("update Employee  " +
//                " set salary = salary + (salary * 0.12) " +
//                " where department.name in (:eDepartments")
//                .setParameter("eDepartments", employeeDepartments)
//                .executeUpdate();



        entityManager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.department.name IN (:departments)", Employee.class)
                .setParameter("departments", employeeDepartments)
                .getResultStream()
                .forEach(e -> e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12))));

        entityManager.getTransaction().commit();;
        entityManager.close();
    }
}
