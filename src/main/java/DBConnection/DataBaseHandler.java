package DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataBaseHandler {
private Const polecenia = new Const();
    public DataBaseHandler()
    {
        DataBaseConnection dbConnect = new DataBaseConnection();

        try ( Connection connection = dbConnect.connect())
        {
            System.out.println("Dziala");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
