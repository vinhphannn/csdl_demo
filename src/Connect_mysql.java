import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect_mysql {

    public Connection getConnection(String dbURL, String userName, String password) {
        Connection conn = null;
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("Connect Successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connect Failure!");
            e.printStackTrace();
        }
        return conn;
    }
}
