package persistance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void data() {
        User user = new User("ok",false,new Object[]{56});
        assertEquals(user.data()[0],56);
    }
}