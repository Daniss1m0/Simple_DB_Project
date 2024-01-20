package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private String url = "jdbc:oracle:thin:@localhost:1521:ORCL"; // Zastąp z odpowiednim URL
    private String user = "c##nazwa";
    private String password = "haslo";

    public Connection connect() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
