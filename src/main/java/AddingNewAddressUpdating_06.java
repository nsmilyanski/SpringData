import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AddingNewAddressUpdating_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        String addressText = "Vitoshka 15";

        Address address = new Address();
        address.setText(addressText);
        entityManager.persist(address);

        String lastName = scanner.nextLine();

        entityManager.createQuery(" update Employee e" +
                " set e.address = :employeeAddress " +
                " where e.lastName = :employeeLastName")
                .setParameter("employeeAddress", address)
                .setParameter("employeeLastName", lastName)
                .executeUpdate();


        entityManager.getTransaction().commit();;
        entityManager.close();
    }
}
