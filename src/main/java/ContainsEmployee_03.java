import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class ContainsEmployee_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        String[] searchFor = scanner.nextLine().split(" ");

        Long singleResult = entityManager.createQuery("select count(e) from Employee e" +
                " where e.firstName = :first_name " +
                " and e.lastName = :last_name", Long.class)
                .setParameter("first_name", searchFor[0])
                .setParameter("last_name", searchFor[1])
                .getSingleResult();

        if (singleResult == 0){
            System.out.println("No");
        }else {
            System.out.println("Yes");
        }

        entityManager.getTransaction().commit();

    }
}
