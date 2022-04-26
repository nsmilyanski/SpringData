package AppsIntroduction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ChangeTownNamesCasing {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String country = scanner.nextLine();

        PreparedStatement preparedStatement = connection.prepareStatement("update towns\n" +
                " set `name` = upper(`name`)\n" +
                " where country = ?; ");
        preparedStatement.setString(1, country);

        preparedStatement.executeUpdate();

        List<String> townsList = new ArrayList<>();

        PreparedStatement selectTowns = connection.prepareStatement("select name from towns where " +
                "country = ?;");
        selectTowns.setString(1, country);

        ResultSet resultSet = selectTowns.executeQuery();

        while (resultSet.next()){
            String name = resultSet.getString("name");
            townsList.add(name);
        }


        if (townsList.isEmpty()){
            System.out.println("No town names were affected.");
        }else {
            System.out.printf("%d town names were affected.%n", townsList.size());
            System.out.println(townsList);
        }


    }
}
