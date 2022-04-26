package AppsIntroduction;

import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        int[] idsOfMinions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        PreparedStatement updateMinions = connection.prepareStatement("update minions\n" +
                " set age = age + 1, name = lower(`name`)\n" +
                " where id = ?;");

        for (int idsOfMinion : idsOfMinions) {
            updateMinions.setInt(1, idsOfMinion);

            updateMinions.executeUpdate();

        }



        PreparedStatement selectAllMinions = connection.prepareStatement("select name, age from minions;");

        ResultSet resultSet = selectAllMinions.executeQuery();

        while (resultSet.next()){
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");

            System.out.printf("%s %d%n", name, age);
        }


    }
}
