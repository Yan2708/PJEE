package persistance.documents;

import mediatek2022.Document;
import mediatek2022.Utilisateur;
import persistance.ADocument;

public class Livre extends ADocument {

    public Livre(String name, int emprunt, int id) {
        super(name, emprunt,id);
    }

    @Override
    public String getType() {
        return "Livre";
    }
}
