package persistance;

public class Dvd extends ADocument{


    public Dvd(String name, int emprunt) {
        super(name, emprunt);
    }

    @Override
    public String getType() {
        return "Dvd";
    }
}
