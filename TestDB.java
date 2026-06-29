import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://bdpaui3unqbl6yfhnky5-mysql.services.clever-cloud.com:3306/bdpaui3unqbl6yfhnky5?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "u7m72momrlv7ddx3";
        String pass = "WVYMjj4c1SCJol3YIB88";
        
        System.out.println("Testing connection to: " + url);
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
