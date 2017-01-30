package Tracking.user.DAO;

/**
 * Created by yale on 20/09/16.
 */


import java.io.InputStream;
import java.sql.*;
import java.util.Scanner;

public class TrackingDao {
    private Connection conn = null;

    public Connection createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/tracking?user=user&password=user1&zeroDateTimeBehavior=convertToNull");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/tracking?user=user&password=user1&zeroDateTimeBehavior=convertToNull");

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

    public Connection changeDb(String db) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/"+db+"?user=user&password=user1&zeroDateTimeBehavior=convertToNull");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/tracking?user=user&password=user1&zeroDateTimeBehavior=convertToNull");

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

    public Connection getConn(){
        return this.conn;
    }



//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<execute UPDATE:
    public int executeUpdateWithPhoto(String query, String user, InputStream photo) {
        int output = 0;
        changeDb("imaging");


        PreparedStatement stmt1 = null;
        int result=0;

        System.out.println("attempting execute UPDATE: " + query);

        try {
            stmt1 = conn.prepareStatement(query);
            stmt1.setString(1,user);
            stmt1.setBlob(2, photo);
            System.out.println("  executing UPDATE: " + stmt1);
            result = executeUpdate(stmt1);

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            e.printStackTrace();
        }
        createConnection();
        return result;
    }
    public int executeUpdate(PreparedStatement stmt){
        if (conn == null) {
            createConnection();
        }

        System.out.println("executing UPDATE: " + stmt);
        int result = 0;

        try {
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            e.printStackTrace();
        }
        return result;

    }

    public int executeUpdate(String query){
        if (conn == null) {
            createConnection();
        }
        Statement stmt1 = null;
        int result=0;

        System.out.println("executing UPDATE: " + query);

        try {
            stmt1 = conn.createStatement();
            result = stmt1.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            e.printStackTrace();
        }
        return result;

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<execute QUERY
    public String executeQueryReturnString(String query) {
        String output = "";
        ResultSet rs = executeQuery(query);
        try {
            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + ",   ");
                    output += (rs.getString(i) + ",   ");
                }
                output += "\n";
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            e.printStackTrace();
        }
        return output;
    }
    public ResultSet executeQuery(String query) {
        if (conn == null) {
            createConnection();
        }
        Statement stmt1 = null;
        ResultSet rs = null;

        System.out.println("executing query: " + query);

        try {
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            e.printStackTrace();
        }
        return rs;
    }

    public static void main(String[] args) {
        TrackingDao test = new TrackingDao();
        test.createConnection();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                return;
            }

            System.out.println(test.executeQueryReturnString(input));

        }
        return;
    }

}

