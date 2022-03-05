import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class EmployeesFromDepartment_05 {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        String departmentsForExtract = "Research and Development";


        entityManager.createQuery("select e " +
                " from Employee e" +
                " where e.department.name = :departmentName " +
                " order by e.salary, e.id", Employee.class)
                .setParameter("departmentName", departmentsForExtract)
                .getResultStream()
                .forEach(e -> {
                    String format = String.format("%s %s from %s - $%.2f", e.getFirstName(),
                            e.getLastName(), departmentsForExtract, e.getSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12))));
                    System.out.println(format);
                });

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
