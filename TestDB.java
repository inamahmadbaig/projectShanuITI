import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://bdpaui3unqbl6yfhnky5-mysql.services.clever-cloud.com:3306/bdpaui3unqbl6yfhnky5?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "u7m72momrlv7ddx3";
        String pass = "WVYMjj4c1SCJol3YIB88";
        
        System.out.println("Testing connection to: " + url);
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            System.out.println("Connection successful!");
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SHOW PROCESSLIST")) {
                System.out.println("--- ACTIVE CONNECTIONS ---");
                while (rs.next()) {
                    System.out.println(String.format("ID: %s, User: %s, Host: %s, DB: %s, Command: %s, Time: %s, State: %s, Info: %s",
                            rs.getString("Id"), rs.getString("User"), rs.getString("Host"),
                            rs.getString("db"), rs.getString("Command"), rs.getString("Time"),
                            rs.getString("State"), rs.getString("Info")));
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
