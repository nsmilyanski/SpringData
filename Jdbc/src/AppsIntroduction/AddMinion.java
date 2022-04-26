package AppsIntroduction;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class AddMinion {
    private static String minionName;
    private static int minionAge;
    private static String town;
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String[] minionsArr = scanner.nextLine().split("\\s+");
        minionName = minionsArr[1];
        minionAge = Integer.parseInt(minionsArr[2]);
        town = minionsArr[3];

        String[] villainArr  = scanner.nextLine().split("\\s+");

        String villainName = villainArr[1];

        PreparedStatement prepareStatementMinions = connection.prepareStatement("select t.id from towns as t" +
                " where t.name = ?;");
        prepareStatementMinions.setString(1, town);

        ResultSet resultMinions = prepareStatementMinions.executeQuery();

        int town_id = findTownsId(connection, town, prepareStatementMinions, resultMinions);

        PreparedStatement preparedStatementVillains = connection.prepareStatement("select id from villains\n" +
                "where `name` = ?;");
        preparedStatementVillains.setString(1 , villainName);

        ResultSet resultVillain = preparedStatementVillains.executeQuery();

        int villaninId = checkVillainsId(connection, villainName, preparedStatementVillains, resultVillain);

        PreparedStatement insertNewMinion = connection.prepareStatement("insert into minions(name, age, town_id)\n" +
                "values\n" +
                "(?, ?, ?);");

        insertNewMinion.setString(1, minionName);
        insertNewMinion.setInt(2, minionAge);
        insertNewMinion.setInt(3, town_id);

        insertNewMinion.executeUpdate();

        PreparedStatement getLastMinion = connection.prepareCall("select id from minions \n" +
                "order by id desc\n" +
                "limit 1;");

        ResultSet resultSet = getLastMinion.executeQuery();

        resultSet.next();
        int minion_id = resultSet.getInt("id");

        PreparedStatement insertIntoMinions_Villains = connection.prepareStatement("insert into minions_villains (minion_id, villain_id)" +
                "values" +
                "(?, ?)");
        insertIntoMinions_Villains.setInt(1, minion_id);
        insertIntoMinions_Villains.setInt(2, villaninId);

        insertIntoMinions_Villains.executeUpdate();

        System.out.printf("Successfully added %s to be minion of %s", minionName, villainName);




    }
    private static int  checkVillainsId(Connection connection, String name, PreparedStatement preparedStatement, ResultSet resultVillains) throws SQLException {
       int villainsId = 0;
        if (!resultVillains.next()){
            PreparedStatement insertIntoVillains = connection.prepareStatement("insert into villains (name, evilness_factor)\n" +
                    "values\n" +
                    "(? , ?);");
            insertIntoVillains.setString(1, name);
            insertIntoVillains.setString(2, "evil");
            insertIntoVillains.executeUpdate();

            ResultSet newVillain = preparedStatement.executeQuery();
            newVillain.next();
            villainsId = newVillain.getInt("id");

            System.out.printf("Villain %s was added to the database.%n", name);
        }else {
            villainsId = resultVillains.getInt("id");
        }
        return villainsId;

    }

    private static int findTownsId(Connection connection, String town, PreparedStatement prepareStatementMinions, ResultSet resultMinions) throws SQLException {
        int town_id;
        if (!resultMinions.next()){
            PreparedStatement insertIntoMinions = connection.prepareStatement("insert into towns (name)" +
                    "values(?)");
            insertIntoMinions.setString(1, town);
            insertIntoMinions.executeUpdate();

            ResultSet newTownSet = prepareStatementMinions.executeQuery();
            newTownSet.next();
            town_id = newTownSet.getInt("id");
            System.out.printf("Town %s was added to the database.%n", town);
        }else {
            town_id = resultMinions.getInt("id");
        }
        return town_id;
    }
}
