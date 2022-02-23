package persistance;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public class Livre extends ADocument {

    public Livre(String name, int emprunt) {
        super(name, emprunt);
    }

    @Override
    public String getType() {
        return "Livre";
    }
}
