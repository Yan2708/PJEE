package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {

    private static Connection connection = null;

    public synchronized static Connection getConnection() {
        if(connection==null){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pjee","root","91502");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}
