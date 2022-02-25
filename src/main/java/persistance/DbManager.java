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

    // renvoie la liste de tous les documents disponibles de la médiathèque

    public static List<Document> tousLesDocumentsDisponibles() {
        ArrayList<Document> res = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from document");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                switch (rs.getString("type")){
                    case "dvd":
                        res.add(new Dvd(rs.getString("name"),rs.getInt("emprunteur"),rs.getInt("id")));
                        break;
                    case "livre":
                        res.add(new Livre(rs.getString("name"),rs.getInt("emprunteur"),rs.getInt("id")));
                        break;
                }
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    // va récupérer le User dans la BD et le renvoie
    // si pas trouvé, renvoie null

    public static Utilisateur getUser(String login, String password) {

        try {
            PreparedStatement ps = connection.prepareStatement("select * from user where username=? and password=?");
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

    // va récupérer le document de numéro numDocument dans la BD
    // et le renvoie
    // si pas trouvé, renvoie null

    public static Document getDocument(int numDocument) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from document where id=?");
            ps.setInt(1,numDocument);
            ResultSet r = ps.executeQuery();
            if (r.next())
                return new Livre(r.getString("name"),r.getInt("emprunteur"), r.getInt("id"));
            ps.close();
            r.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void ajoutDocument(int type, Object... args) {
        // args[0] -> le titre
        // args [1] --> l'auteur
        // etc... variable suivant le type de document
        try {
            PreparedStatement ps = connection.prepareStatement("insert into document (type,name,auteur) values (?,?,?)");
            ps.setString(2, (String) args[0]);
            ps.setString(3, (String) args[1]);
            switch (type){
                case 1:
                    ps.setString(1,"livre");
                    break;
                case 2:
                    ps.setString(1,"dvd");
                    break;
            }
            System.out.println(type);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void emprunt(Document doc, int id){
        try {
            PreparedStatement ps = connection.prepareStatement("update document set emprunteur=? where id=?");
            ps.setInt(1,id);
            ps.setInt(2,Integer.parseInt(doc.toString().split(" ")[0]));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void retour(Document doc){
        try {
            PreparedStatement ps = connection.prepareStatement("update document set emprunteur=null where id=?");
            ps.setInt(1,Integer.parseInt(doc.toString().split(" ")[0]));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
