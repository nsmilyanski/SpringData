package AppsIntroduction;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetVillainsNames {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement stmt =
                connection.prepareStatement("select v.name, count(distinct m.id) as count_minions from villains as v\n" +
                        " join minions_villains as mv\n" +
                        " on v.id = mv.villain_id\n" +
                        " join minions as m on m.id = mv.minion_id\n" +
                        " group by mv.villain_id\n" +
                        " having count_minions > 15\n" +
                        " order by count_minions desc;");

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            String name = resultSet.getString("name");
            int count_minions = resultSet.getInt("count_minions");

            System.out.printf("%s %d", name, count_minions);

        }

    }
}
