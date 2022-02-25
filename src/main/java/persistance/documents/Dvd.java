package persistance.documents;

import persistance.ADocument;

public class Dvd extends ADocument {


    public Dvd(String name, int emprunt, int id) {
        super(name, emprunt,id);
    }

    @Override
    public String getType() {
        return "Dvd";
    }
}
