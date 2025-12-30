package src.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.model.Password;
import src.model.exception.NullPasswordException;

public class PasswordTest {
    private Password password;

    @BeforeEach
    public void setup() {
        password = new Password(2);
    }

    @Test
    public void testGenerator() {
        password.generatePass(4);
        assertNotNull(password.getPassword());
        try {
            assertFalse(password.isValid());
        } catch (NullPasswordException e) {
            //expected
        }
    }

    @Test
    public void testIsValid() {
        try {
            password.setPassword("Abc123!");
            assertFalse(password.isValid());
            password.setPassword("abdt33214!");
            assertFalse(password.isValid());
            password.setPassword("Agirnrieon%");
            assertFalse(password.isValid());
            password.setPassword("Abjf2345221");
            assertFalse(password.isValid());
            password.setPassword("Abc2f12&");
            assertTrue(password.isValid());
        } catch (NullPasswordException e) {
            fail();
        }

    }

    @Test
    public void testInvalidException() {
        try {
            password.setPassword(null);
            password.isValid();
            fail();
        } catch (NullPasswordException e) {
            //expected
        }
    }

    @Test
    public void testReset() {
        password.setPassword("aaa");
        assertEquals("aaa", password.getPassword());
        password.resetPassword();
        assertNull(password.getPassword());
    }
}
