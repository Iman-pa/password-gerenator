package src.ui;

import java.util.Scanner;
import src.model.Password;
import src.model.exception.NullPasswordException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the length of the passeord that you want: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        Password pass = new Password(n);
        System.out.println(pass.getPassword());
        System.out.println("Is the password valid?");
        try {
            System.out.println(pass.isValid());
        } catch (NullPasswordException e) {
            System.out.println("The password is null");
        }
        scanner.close();
    }
}