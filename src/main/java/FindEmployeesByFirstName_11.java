import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Employee> select_e_from_employee_e_ = entityManager.createQuery("select e from Employee e ", Employee.class)
                .getResultList();

        String startWord = scanner.nextLine();

        List<Employee> specificEmployees = new ArrayList<>();

        for (Employee employee: select_e_from_employee_e_) {
            if (employee.getFirstName().toLowerCase().startsWith(startWord.toLowerCase())){
                specificEmployees.add(employee);
            }
        }

        specificEmployees.forEach(e ->{
            String format = String.format("%s %s - %s - ($%.2f)", e.getFirstName(), e.getLastName(),
                    e.getJobTitle(), e.getSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12))));

            System.out.println(format);
        });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
