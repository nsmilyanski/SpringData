import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("CODE_FIRST");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();




        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
