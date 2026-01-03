package src.ui;

import java.util.Scanner;
import src.model.Password;
import src.model.exception.ImpossiblePasswordException;
import src.model.exception.NullPasswordException;

public class Main {
    public static void main(String[] args) {
        boolean upper = false, lower = false, digit = false, symbol = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the length of the passeord that you want: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Do you want any uppercases in your password(yes/no)? ");
        String upperCase = scanner.nextLine();
        if (upperCase.equals("yes")) {
            upper = true;
        }
        System.out.println("Do you want any lowercases in your password(yes/no)? ");
        String lowerCase = scanner.nextLine();
        if (lowerCase.equals("yes")) {
            lower = true;
        }
        System.out.println("Do you want any digits in your password(yes/no)? ");
        String digits = scanner.nextLine();
        if (digits.equals("yes")) {
            digit = true;
        }
        System.out.println("Do you want any symbols in your password(yes/no)? ");
        String symbols = scanner.nextLine();
        if (symbols.equals("yes")) {
            symbol = true;
        }
        Password pass1 = new Password();
        Password pass2 = new Password();
        Password pass3 = new Password();
        try {
            pass1.generatePass(n, upper, lower, digit, symbol);
            System.out.println("Pass 1: " + pass1.getPassword());
            pass2.generatePass(n, upper, lower, digit, symbol);
            System.out.println("Pass 2: " + pass2.getPassword());
            pass3.generatePass(n, upper, lower, digit, symbol);
            System.out.println("Pass 3: " + pass3.getPassword());
        } catch (ImpossiblePasswordException e) {
            System.out.println("Impossible password, try again.");;
        }
        System.out.println("Which password is valid? ");
        try {
            System.out.println("Pass 1: " + pass1.isValid() + ", the strength of the password: " + pass1.getStrength());
            System.out.println("Pass 2: " + pass2.isValid() + ", the strength of the password: " + pass2.getStrength());
            System.out.println("Pass 3: " + pass3.isValid() + ", the strength of the password: " + pass3.getStrength());
        } catch (NullPasswordException e) {
            System.out.println("The password is null");
        }
        System.out.println("Pick which one of the passwords do you want by entering 1, 2, or 3: ");
        int x = scanner.nextInt();
        scanner.nextLine();
        if (x == 1) {
            System.out.println("The password is set to " + pass1.getPassword());
        } else if (x == 2) {
            System.out.println("The password is set to " + pass2.getPassword());
        } else if (x == 3) {
            System.out.println("The password is set to " + pass3.getPassword());
        } else {
            System.out.println("invalid input");
        }
        scanner.close();
    }
}