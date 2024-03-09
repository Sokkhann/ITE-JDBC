package utils;

import java.util.Scanner;

public class Util {
    public static void login(Scanner input, String username, String password) {
        do {
            System.out.print("Enter username: ");
            username = input.nextLine();

            System.out.print("Enter password: ");
            password = input.nextLine();

            if (!username.equals(username) || !password.equals(password)) {
                System.out.println("Incorrect username or password. Please try again.");
            }
        } while (!username.equals(username) || !password.equals(password));

    }
}
