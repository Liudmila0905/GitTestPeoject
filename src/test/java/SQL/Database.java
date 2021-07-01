//CREATE DATABASE
//CREATE TABLE
//INSERT
//SELECT (and verify data)
//UPDATE
//SELECT (and verify data)
//DELETE
//SELECT (and verify data)
//DROP TABLE
//*verify table was dropped
//DROP DATABASE
//*verify database was dropped

package SQL;
import java.sql.*;

public class Database {
    public static void main(String[] args) {
        Connection conn1 = null;
        try {
            //Connect
            String url1 = "jdbc:mysql://localhost:3306/FirstDB?serverTimezone=UTC";
            String user = "root";
            String password = "misya123";

            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test1");
            }

            //Create table
            Statement statement1 =  conn1.createStatement();
            statement1.executeUpdate("CREATE TABLE `FirstDB`.`new_table1` (" +
        "  `id` INT NOT NULL," +
        "  `name` VARCHAR(45) NULL," +
              "  `salary` INT NULL," +
        "  `profession` VARCHAR(45) NULL," +
        "  `experience` INT NULL,"+
        "  PRIMARY KEY (`id`));");

            //Insert
            statement1.executeUpdate("INSERT INTO `FirstDB`.`new_table3` (`id`, `name`, `salary`, `profession`, `experience`) VALUES ('1', 'Clara', '5000', 'QA', '10');");
            statement1.executeUpdate("INSERT INTO `FirstDB`.`new_table3` (`id`, `name`, `salary`, `profession`, `experience`) VALUES ('2', 'Tom', '2000', 'Designer', '3');");
            statement1.executeUpdate("INSERT INTO `FirstDB`.`new_table3` (`id`, `name`, `salary`, `profession`, `experience`) VALUES ('3', 'Pol', '6000', 'Dev', '16');");

            //SELECT (and verify data)
            ResultSet resultSet = statement1.executeQuery("SELECT * FROM FirstDB.new_table3;");
            String[] arr = null;

            while (resultSet.next()) {
               String em = resultSet.getString("name");
                arr = em.split("\n");
                for (int i =0; i < arr.length; i++){
                    System.out.println(arr[i]);
                }
            }

            ResultSet resultSet1 = statement1.executeQuery("SELECT name FROM FirstDB.new_table3 where id=1;");
            resultSet1.next();
            String sName = resultSet1.getString("name");
            if (!sName.equals("Clara")) {
                System.out.println("Test is failed");
            }

            //UPDATE
            statement1.executeUpdate("UPDATE new_table3 SET name = 'Samuel' WHERE id = 2;");
            //SELECT (and verify data)
            ResultSet resultSet2 = statement1.executeQuery("SELECT name FROM FirstDB.new_table3 where id=2;");
            resultSet2.next();
            String sName1 = resultSet2.getString("name");
            if (!sName1.equals("Samuel")) {
                System.out.println("Test is failed");
            }
            //DELETE
           statement1.executeUpdate("DELETE FROM new_table3 WHERE (id = '2')");
            //SELECT (and verify data)
            ResultSet resultSet3 = statement1.executeQuery("SELECT name FROM FirstDB.new_table3 where id=2;");

            if (resultSet3.next()) {
                System.out.println("Test is failed");
            }
            //DROP TABLE
            statement1.executeUpdate("DROP TABLE new_table1");
            DatabaseMetaData dbm = conn1.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "new_table1", null);
            if (tables.next()) {
                System.out.println("Test is failed");
            }

        } catch (SQLException e) {
        e.printStackTrace();
        } finally {
        try {
            conn1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

}

