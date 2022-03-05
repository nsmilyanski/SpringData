import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AddressesWithEmployee_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("select a from Address a " +
                " where a.employees.size > 0 " +
                " order by a.employees.size desc", Address.class)
                .setMaxResults(10)
                .getResultStream()
                .forEach(a -> {
                    String format = String.format("%s, %s, - %d employees", a.getText(),
                            a.getTown(), a.getEmployees().size());
                    System.out.println(format);
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
