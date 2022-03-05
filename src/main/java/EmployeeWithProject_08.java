import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;
import java.util.Set;

public class EmployeeWithProject_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        int id = Integer.parseInt(scanner.nextLine());

        Employee result = entityManager.createQuery("select e from Employee e " +
                " where e.id = :employeeId", Employee.class)
                .setParameter("employeeId", id)
                .getSingleResult();

        System.out.printf("%s %s - %s", result.getFirstName(), result.getLastName(), result.getJobTitle());

        Set<Project> projects = result.getProjects();

        projects.stream().forEach(p -> System.out.println(p.getName()));

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
