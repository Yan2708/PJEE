package persistance;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public abstract class ADocument implements Document {
    private int id;
    private String name;
    private Integer idEmprunteur;

    public ADocument(String name,int emprunt, int id) {
        this.name = name;
        this.id=id;
        idEmprunteur=emprunt;
    }

    @Override
    public boolean disponible() {
        return idEmprunteur==0;
    }

    @Override
    public void emprunt(Utilisateur u) throws Exception {
        if (!disponible())
            throw new Exception("le livre est déjà emprunté");
        idEmprunteur= (int) u.data()[0];
        DbManager.emprunt(this,(int)u.data()[0]);
    }

    @Override
    public void retour() {
        idEmprunteur=0;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return id+" "+name +" "+idEmprunteur;
    }
}
