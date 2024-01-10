package htw.berlin.webtech;

import htw.berlin.webtech.Password.Password;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    void testPasswordGettersAndSetters() {
        Password password = new Password("Service", "Username", "Passwort", "Beschreibung");
        password.setId(1L);

        assertEquals(1L, password.getId());
        assertEquals("Service", password.getService());
        assertEquals("Username", password.getUsername());
        assertEquals("Passwort", password.getPassword());
        assertEquals("Beschreibung", password.getDescription());
    }
}
