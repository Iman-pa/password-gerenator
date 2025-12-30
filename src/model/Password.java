package src.model;

import java.security.SecureRandom;

import src.model.exception.NullPasswordException;

public class Password {
    private String password;
    private final static SecureRandom random = new SecureRandom();
    private final static String DIGITS = "1234567890";
    private final static String UPPERLETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXWZ";
    private final static String LOWERLETTERS = "abcdefghijklmnopqrstuvwxyz";
    private final static String SYMBOLS = "!@#$%^&*";

    // MODIFIES: this
    // EFFECTS: initializes this Password object by calling generatePass(length) which 
            // length is given by the user and is passed to generatorPass
    public Password(int length) {
        password = null;
        generatePass(length);
    }

    // MODIFIES: this
    // EFFECTS: generates a new password based on the given length by random
    public void generatePass(int lengthPass) {
        String allChar = DIGITS + UPPERLETTERS + LOWERLETTERS + SYMBOLS;
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < lengthPass; i++) {
            int index = random.nextInt(allChar.length());
            pass.append(allChar.charAt(index));
        }
        this.password = pass.toString();
    }

    // EFFECTS:
    // throws NullPasswordException if this.password is null;
    // returns true if and only if this.password satisfies ALL of the following:
    // - length is at least 8 characters
    // - contains at least one uppercase English letter ('A'–'Z')
    // - contains at least one digit ('0'–'9')
    // - contains at least one symbol character
    // (a character that is not a letter and not a digit);
    // returns false otherwise

    public boolean isValid() throws NullPasswordException {
        boolean hasDigit = false;
        boolean hasSymbol = false;
        boolean hasUpper = false;
        if (password == null) {
            throw new NullPasswordException();
        }
        if (password.length() < 8) {
            return false;
        }
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
                hasSymbol = true;
            }
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            }
        }
        if (!hasSymbol || !hasDigit || !hasUpper) {
            return false;
        }
        return true;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // EFFECTS: sets password to null
    public void resetPassword() {
        password = null;
    }

}
