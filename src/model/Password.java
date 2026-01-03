package src.model;

import java.security.SecureRandom;

import src.model.exception.ImpossiblePasswordException;
import src.model.exception.NullPasswordException;

public class Password {
    private String password;
    private final static SecureRandom random = new SecureRandom();
    private final static String DIGITS = "1234567890";
    private final static String UPPERLETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXWZ";
    private final static String LOWERLETTERS = "abcdefghijklmnopqrstuvwxyz";
    private final static String SYMBOLS = "!@#$%^&*";
    private int length;

    // MODIFIES: this
    // EFFECTS: initializes this Password object by calling generatePass(length) which 
            // length is given by the user and is passed to generatorPass
    public Password() {
        password = null;
    }

    // MODIFIES: this
    // EFFECTS: generate a password based on the choices by the user about length, uppercase, lowercase, digit and symbols.
    public void generatePass(int lengthPass, boolean upper, boolean lower, boolean digit, boolean symbol) throws ImpossiblePasswordException {
        if (!upper && !lower && !digit && !symbol) {
            throw new ImpossiblePasswordException();
        }
        this.length = lengthPass;
        String pool = "";
        StringBuilder pass = new StringBuilder();
        if (upper) {
            pass.append(UPPERLETTERS.charAt(random.nextInt(UPPERLETTERS.length())));
            pool += UPPERLETTERS;
            lengthPass -= 1;
        }
        if (lower) {
            pass.append(LOWERLETTERS.charAt(random.nextInt(LOWERLETTERS.length())));
            pool += LOWERLETTERS;
            lengthPass -= 1;
        }
        if (digit) {
            pass.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
            pool += DIGITS;
            lengthPass -= 1;
        }
        if (symbol) {
            pass.append(SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));
            pool += SYMBOLS;
            lengthPass -= 1;
        }
        for (int i = 0; i < lengthPass; i++) {
            int index = random.nextInt(pool.length());
            pass.append(pool.charAt(index));
        }
        char[] chars = pass.toString().toCharArray();
        for (int i = chars.length - 1; i>0; i--) {
            int j = random.nextInt(i+1);
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        pass = new StringBuilder(new String(chars));
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

    //EFFECTS: returns the strength of the password, if it's null it returns 0
    public int getStrength() {
        if (password == null) {
            return 0;
        }
        int score = 0;
        if (password.matches(".*[A-Z].*")) {
            score += 2;
        }
        if (password.matches(".*[a-z].*")) {
            score += 2;
        }
        if (password.matches(".*[0-9].*")) {
            score +=2;
        }
        if (password.matches(".*[!@#$%^&*].*")) {
            score += 2;
        }
        if (password.length() > 12) {
            score +=2;
        }
        return score;
    }

}
