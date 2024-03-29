package persistance;

import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.PersistentMediatheque;
import mediatek2022.Utilisateur;
import persistance.DAO.DocumentDAO;
import persistance.DAO.UserDAO;

import java.util.List;

// classe mono-instance  dont l'unique instance est connue de la m�diatheque
// via une auto-d�claration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-Fran�ois Brette 01/01/2018
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


	// renvoie la liste de tous les documents disponibles de la m�diath�que
	@Override
	public List<Document> tousLesDocumentsDisponibles() {
		synchronized (this){
			return new DocumentDAO().tousLesDocumentsDisponibles();
		}
	}

	// va r�cup�rer le User dans la BD et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		synchronized (this){
			return new UserDAO().getUser(login, password);
		}
	};

	// va r�cup�rer le document de num�ro numDocument dans la BD
	// et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		synchronized (this){
			return new DocumentDAO().getDocument(numDocument);
		}
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		synchronized (this){
			new DocumentDAO().ajoutDocument(type,args);
		}
	}



}
