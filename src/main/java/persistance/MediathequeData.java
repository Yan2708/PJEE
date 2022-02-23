package persistance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mediatek2022.*;

// classe mono-instance  dont l'unique instance est connue de la médiatheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-François Brette 01/01/2018
	private static PersistentMediatheque instance;
	static {
		instance=new MediathequeData();
		Mediatheque.getInstance().setData(instance);
	}

	public static PersistentMediatheque getInstance() {
		return instance;
	}


	private MediathequeData() {
	}



	private Connection connect() throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pjee","root","91502");
			return connection;
	}



	// renvoie la liste de tous les documents disponibles de la médiathèque
	@Override
	public List<Document> tousLesDocumentsDisponibles() {
		ArrayList<Document> res = new ArrayList<>();
		try {
			PreparedStatement ps = connect().prepareStatement("select * from document where emprunteur==0");
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				res.add(new Livre(rs.getString("name"),rs.getInt("emprunteur")));
			}
			ps.close();
			rs.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {

		try {
			PreparedStatement ps = connect().prepareStatement("select * from user where username=? and password=?");
			ps.setString(1,login);
			ps.setString(2,password);
			ResultSet r = ps.executeQuery();
			if (r.next())
				return new User(r.getString("username"),r.getBoolean("type"),new Object[r.getInt("id")]);

			ps.close();
			r.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	};

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		try {
			PreparedStatement ps = connect().prepareStatement("select * from document where id=?");
			ps.setInt(1,numDocument);
			ResultSet r = ps.executeQuery();
			if (r.next())
				return new Livre(r.getString("name"),r.getInt("emprunteur"));
			ps.close();
			r.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc... variable suivant le type de document
		try {
			PreparedStatement ps = connect().prepareStatement("insert into document (type,name,auteur) values (?,?,?)");
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
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}



}
