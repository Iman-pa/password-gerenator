package src.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.model.Password;
import src.model.exception.ImpossiblePasswordException;
import src.model.exception.NullPasswordException;

public class PasswordTest {
    private Password password;

    @BeforeEach
    public void setup() {
        password = new Password();
    }

    @Test
    public void testGenerator() {
        assertNull(password.getPassword());
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

    @Test
    public void testGeneratePassword() {
        try {
            password.generatePass(5, false, false, false, false);
        } catch (ImpossiblePasswordException e) {
            // expected
        }
        try {
            password.generatePass(13, true, false, true, false);
            assertEquals(6, password.getStrength());
            password.generatePass(12, true, true, true, true);
            assertEquals(8, password.getStrength());
            password.generatePass(15, true, true, true, true);
            assertEquals(10, password.getStrength());
        } catch (ImpossiblePasswordException e) {
            fail();
        }
    }
}
