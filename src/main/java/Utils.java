import java.sql.Date;
import java.sql.ResultSet;
import java.time.Instant;

public class Utils {
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);

        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public static String constructValues(String[] values) {
        StringBuilder res = new StringBuilder();
        res.append('(');

        for (String v : values) {
            if (!v.isEmpty()) {
                if (isInteger(v) || v.equals("true") || v.equals("false")) {
                    res.append(v).append(", ");
                } else {
                    res.append('\'').append(v).append("', ");
                }
            }
            else
                res.append("''").append(", ");
        }
        res.deleteCharAt(res.lastIndexOf(","));
        res.append(')');
        return res.toString();
    }

    public static String isMarried(String ans) {
        return String.valueOf(ans.compareTo("Παντρεμένος") == 0);
    }
    public static String isDidakt(String ans) {
        return String.valueOf(ans.compareTo("Διδακτικό προσωπικό") == 0);
    }

    public static boolean checkRaise(int id, String current_date, double salary_amount) {
        DB_API api = DB_API.getAPI("jdbc:mysql://localhost", "filtatos", "root", "");

        String query = "SELECT * FROM `employees` WHERE id = '" + id + "'";

        boolean raised = false;

        try {
            // run query and for each employee check if he has a raise
            ResultSet rs = api.executeQuery(query);
            rs.next();

                String hire_date = rs.getString("startdate");

                // check if hire_date is at least one year before current_date
                if (Date.valueOf(hire_date).toLocalDate().plusYears(1).isBefore(Date.valueOf(current_date).toLocalDate())) {
                    query = "SELECT * FROM `salarydata` WHERE `employeid` = " + id;
                    ResultSet rs2 = api.executeQuery(query);

                    // check if rs2 is empty
                    if (!rs2.isBeforeFirst()) {
                        // if it is, add a raise
                        query = "INSERT INTO `salarydata` (`employeid`, `amount`, `date`) VALUES (" + id + ", " + salary_amount + ", '" + current_date + "')";
                        api.executeUpdate(query);
                        raised = true;
                    }
                    else if (rs2.next()) {
                        // if not, add a raise
                        query = "UPDATE `salarydata` SET `amount` = `amount` * 1.15 WHERE `employeid` = " + id;
                        api.executeUpdate(query);
                        raised = true;
                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return raised;
    }
}
