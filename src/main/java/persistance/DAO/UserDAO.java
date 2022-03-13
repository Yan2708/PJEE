package persistance.DAO;

import mediatek2022.Utilisateur;
import persistance.DbManager;
import persistance.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public Utilisateur getUser(String login, String password) {
        try {
            Connection connection;
            PreparedStatement ps = DbManager.getConnection().prepareStatement("select * from user where username=? and password=?");
            ps.setString(1,login);
            ps.setString(2,password);
            ResultSet r = ps.executeQuery();
            if (r.next())
                return new User(r.getString("username"),r.getBoolean("type"),new Object[]{r.getInt("id")});

            ps.close();
            r.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    };
}
