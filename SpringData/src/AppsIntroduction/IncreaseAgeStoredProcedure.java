package AppsIntroduction;

import com.mysql.cj.xdevapi.PreparableStatement;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);


        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);


        int minion_id = Integer.parseInt(scanner.nextLine());

        PreparedStatement updateIdWithProcedure = connection.prepareStatement("create procedure usp_get_older (minion_id int)\n" +
                " begin\n" +
                " update minions \n" +
                " set age = age + 1\n" +
                " where id = minion_id;\n" +
                " end ");

        updateIdWithProcedure.executeUpdate();

        PreparedStatement callProcedure = connection.prepareStatement("call usp_get_older(?);");
        callProcedure.setInt(1, minion_id);

        callProcedure.executeUpdate();

        PreparedStatement selectMinion = connection.prepareStatement("select name, age from minions " +
                "where id = ?");
        selectMinion.setInt(1, minion_id);

        ResultSet resultSet = selectMinion.executeQuery();

        resultSet.next();

        String name = resultSet.getString("name");

        int age = resultSet.getInt("age");

        System.out.println(name +" " + age);
    }
}
