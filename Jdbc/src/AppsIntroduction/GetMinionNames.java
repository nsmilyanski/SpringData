package AppsIntroduction;

import com.mysql.cj.xdevapi.PreparableStatement;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetMinionNames {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        int id = Integer.parseInt(scanner.nextLine());

        PreparedStatement prepareStatementVillain = connection.prepareStatement("select name from villains" +
                " where id = ?;");
        prepareStatementVillain.setInt(1, id);

        PreparedStatement prepareStatement = connection.prepareStatement("select distinct m.name as minion_name, m.age as minion_age from villains as v\n" +
                " join minions_villains as mv\n" +
                " on v.id = mv.villain_id\n" +
                " join minions as m on m.id = mv.minion_id\n" +
                " where v.id =  ?;");
        prepareStatement.setInt(1, id);

        ResultSet resultSet = prepareStatementVillain.executeQuery();

        if (resultSet.next()) {
            String villainName = resultSet.getString("name");
            System.out.println("Villain: " + villainName);
        }else {
            System.out.println(String.format("No villain with ID %d exists in the database.", id));
            return;
        }

        ResultSet resultMinions = prepareStatement.executeQuery();

        int index = 0;
        while (resultMinions.next()){
            index++;
            String minion_name = resultMinions.getString("minion_name");
            int minion_age = resultMinions.getInt("minion_age");

            System.out.printf("%d. %s %d%n", index, minion_name, minion_age);

        }
    }
}
