import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;

public class FindLatest10Projects_09 {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Project> resultList = entityManager.createQuery("select p from Project p " +
                " order by p.startDate desc", Project.class)
                .setMaxResults(10)
                .getResultList();

        resultList.sort(Comparator.comparing(Project::getName));

        resultList.forEach(p ->{
            System.out.printf("Project name:%s%n", p.getName());
            System.out.printf("Project Description:%s%n", p.getDescription());
            System.out.printf("Project Start Date%s:%n", p.getStartDate());
            System.out.printf("Project End Date:%s%n", p.getEndDate());
        });

        entityManager.getTransaction().commit();;
        entityManager.close();

    }
}
