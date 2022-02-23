package persistance;

import mediatek2022.Utilisateur;

public class User implements Utilisateur {
    private String name;
    private boolean isBibliothecaire;
    private Object[] data;


    public User(String name, boolean isBibliothecaire, Object[] data) {
        this.name = name;
        this.isBibliothecaire = isBibliothecaire;
        this.data=data;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean isBibliothecaire() {
        return isBibliothecaire;
    }

    @Override
    public Object[] data() {
        return data;
    }
}
