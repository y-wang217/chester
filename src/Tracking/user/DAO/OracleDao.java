package Tracking.user.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by yale on 27/09/16.
 */
public class OracleDao {
    private Connection conn = null;

    public Connection createConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:admin/doitnow@10.10.10.3:7777");
            //10.10.10.3 - virtual server
            //10.10.10.102

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        OracleDao test = new OracleDao();
        test.createConnection();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                return;
            }

//            System.out.println(test.executeQuery(input));

        }
        return;
    }

    public static int oracleNumber(){
        return 0;
    }
}
