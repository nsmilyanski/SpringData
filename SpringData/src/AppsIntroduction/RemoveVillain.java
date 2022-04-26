package AppsIntroduction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class RemoveVillain {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        int villain_id = Integer.parseInt(scanner.nextLine());

        PreparedStatement allMinionsOfVillain = connection.prepareStatement("select count(minion_id) as cm " +
                " from minions_villains where villain_id = ?;");

        allMinionsOfVillain.setInt(1, villain_id);

        ResultSet resultSet = allMinionsOfVillain.executeQuery();

        boolean next = resultSet.next();

        int countId = resultSet.getInt("cm");

        if (countId == 0){
            System.out.println("No such villain was found");
        }else {
            PreparedStatement selectVillain = connection.prepareStatement("select name from villains " +
                    "where id = ?");
            selectVillain.setInt(1, villain_id);

            ResultSet resultSet1 = selectVillain.executeQuery();

            boolean next1 = resultSet1.next();

            String name = resultSet1.getString("name");

            PreparedStatement deleteVillain = connection.prepareStatement("delete from minions_villains" +
                    " where villain_id = ?");
            deleteVillain.setInt(1, villain_id);

            deleteVillain.executeUpdate();

            PreparedStatement removeVillain = connection.prepareStatement("delete from villains " +
                    "where id = ?;");
            removeVillain.setInt(1, villain_id);

            removeVillain.executeUpdate();

            System.out.printf("%s was deleted%n", name);
            System.out.printf("%d minions released", countId);
        }


    }
}
