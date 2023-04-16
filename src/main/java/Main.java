import javax.swing.*;
import java.awt.*;
import java.sql.*;



public class Main {

    public static void main(String... args) throws SQLException {
        DB_API api = DB_API.getAPI("jdbc:mysql://localhost", "filtatos", "root", "");
        GUI gui = new GUI(api);

//        String url = "jdbc:mysql://localhost";
//        String databaseName = "filtatos";
//        int port = 3306;
//        String username = "root";
//        String password = "";
//        Connection con = null;
//                try {
//                    con = DriverManager.getConnection(
//                            url + ":" + port + "/" + databaseName + "?characterEncoding=UTF-8", username, password);
//                } catch (Exception e) {
//                    System.err.println(Arrays.toString(e.getStackTrace()));
//                }
//
//                if (con == null) {
//                    System.exit(1);
//                }
//
//                String query = "CREATE TABLE `children_b` (\n" +
//                        "\t`id` INT,\n" +
//                        "\t`employeid` INT,\n" +
//                        "\t`age` INT,\n" +
//                        "\tPRIMARY KEY (`id`)\n" +
//                        ");";
//                Statement statement = con.createStatement();
//                int res = statement.executeUpdate(query);
//
//               System.err.println(res);
    }
}