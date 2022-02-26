package persistance;

import mediatek2022.Document;
import mediatek2022.Utilisateur;
import persistance.documents.Dvd;
import persistance.documents.Livre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManager {
    public static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pjee","root","91502");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }







}
