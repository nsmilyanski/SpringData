import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns_13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();

        String townInput = scanner.nextLine();


//        entityManager.createQuery("delete from Address a " +
//                "where a.town = :currentTown");

        List<Address> addressList = entityManager.createQuery("select a from Address a " +
                "where a.town.name = :currentTown", Address.class)
                .setParameter("currentTown", townInput)
                .getResultList();

        List<Employee> employeeList = entityManager.createQuery("select e from Employee e " +
                "where e.address.town.name = :currentTown ", Employee.class)
                .setParameter("currentTown", townInput)
                .getResultList();


        Town town;

        try {
            town = entityManager.createQuery("select t from Town t " +
                    "where t.name = :currentTown", Town.class)
                    .setParameter("currentTown", townInput)
                    .getSingleResult();

        }catch (NoResultException e){
            System.out.printf("%s does not exist in db.", townInput);
            return;
        }

        entityManager.getTransaction().begin();


        try {
            for (Employee employee:  employeeList) {
                if (employee.getAddress().getTown().getName().equals(townInput)){
                    employee.setAddress(null);
                }
            }

            for (Address address: addressList) {
                if (address.getTown().getName().equals(townInput)){
                    entityManager.remove(address);
                }
            }

            entityManager.remove(town);
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }

        entityManager.getTransaction().commit();


        if (addressList.size() > 1){
            System.out.printf("%d addresses in %s deleted", addressList.size(), townInput);
        }else {
            System.out.printf("%d address in %s deleted", addressList.size(), townInput);
        }


    }
}
