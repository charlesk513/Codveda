import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/Library_Management";
    private static final String USER = "charles";
    private static final String PASSWORD = "k1c2@513";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
