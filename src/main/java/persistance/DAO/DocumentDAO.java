package persistance.DAO;

import mediatek2022.Document;
import persistance.DbManager;
import persistance.documents.Dvd;
import persistance.documents.Livre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO {

    public List<Document> tousLesDocumentsDisponibles() {
        ArrayList<Document> res = new ArrayList<>();
        try {
            PreparedStatement ps = DbManager.connection.prepareStatement("select * from document");
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

    public Document getDocument(int numDocument) {
        try {
            PreparedStatement ps = DbManager.connection.prepareStatement("select * from document where id=?");
            ps.setInt(1,numDocument);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                switch (rs.getString("type")){
                    case "dvd":
                        return (new Dvd(rs.getString("name"),rs.getInt("emprunteur"),rs.getInt("id")));
                    case "livre":
                        return (new Livre(rs.getString("name"),rs.getInt("emprunteur"),rs.getInt("id")));

                }
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void ajoutDocument(int type, Object... args) {
        // args[0] -> le titre
        // args [1] --> l'auteur
        // etc... variable suivant le type de document
        try {
            PreparedStatement ps = DbManager.connection.prepareStatement("insert into document (type,name,auteur) values (?,?,?)");
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

    public void emprunt(Document doc, int id){
        try {
            PreparedStatement ps = DbManager.connection.prepareStatement("update document set emprunteur=? where id=?");
            ps.setInt(1,id);
            ps.setInt(2,Integer.parseInt(doc.toString().split(" ")[0]));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retour(Document doc){
        try {
            PreparedStatement ps = DbManager.connection.prepareStatement("update document set emprunteur=null where id=?");
            ps.setInt(1,Integer.parseInt(doc.toString().split(" ")[0]));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
