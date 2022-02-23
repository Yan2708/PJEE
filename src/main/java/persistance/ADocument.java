package persistance;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public abstract class ADocument implements Document {

    private String name;
    private int idEmprunteur;

    public ADocument(String name,int emprunt) {
        this.name = name;
        idEmprunteur=emprunt;
    }

    @Override
    public boolean disponible() {
        return idEmprunteur==0;
    }

    @Override
    public void emprunt(Utilisateur u) throws Exception {
        if (idEmprunteur!=0)
            throw new Exception("le libre est déjà emprunté");
        idEmprunteur= (int) u.data()[0];
    }

    @Override
    public void retour() {
        idEmprunteur=0;
    }

    public abstract String getType();
}
