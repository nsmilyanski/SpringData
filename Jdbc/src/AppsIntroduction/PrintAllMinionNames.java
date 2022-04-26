package AppsIntroduction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement selectAllMinions = connection.prepareStatement("select id, `name` from minions \n" +
                " order by id;");

        ResultSet resultSet = selectAllMinions.executeQuery();

        List<String> minionsList = new ArrayList<>();

        while (resultSet.next()){
            String name = resultSet.getString("name");

            minionsList.add(name);
        }

        int size = minionsList.size() - 1;

        for (int i = 0; i < minionsList.size(); i++) {
            System.out.println(minionsList.get(i));
            System.out.println(minionsList.get(size - i));
        }


    }
}
