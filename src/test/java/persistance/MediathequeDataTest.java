package persistance;

import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.Media;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MediathequeDataTest {

    @BeforeEach
    void init() throws ClassNotFoundException {
        Class.forName("persistance.MediathequeData");
    }

    @Test
    void tousLesDocumentsDisponibles() {
        List<Document> doc = Mediatheque.getInstance().tousLesDocumentsDisponibles();
        for (Document d:doc) {
            System.out.println(d);
        }
    }

    @Test
    void getUser() {
        Utilisateur u = Mediatheque.getInstance().getUser("yan","li");
        u.equals(new User("yan",true,new Object[1]));
    }

    @Test
    void getDocument() {
    }

    @Test
    void ajoutDocument() {
    }
}