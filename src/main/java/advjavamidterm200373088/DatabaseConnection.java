package advjavamidterm200373088;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String HOST = "MySQL80";

    private static final String USER = "root";

    private static final String PASSWORD = "Matthew99";

    private static final String DB_NAME = "f23test1";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + DB_NAME, USER, PASSWORD);
    }
}
