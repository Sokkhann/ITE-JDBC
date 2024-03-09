import com.github.javafaker.Faker;
import model.Person;
import repository.PersonRepository;
import utils.Singleton;
import utils.Util;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:postgresql://localhost:5432/javafinaldb";
    private static final String username = "postgres";
    private static final String password = "1234";

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Faker faker = new Faker();

        // tell user to login first
        Util.login(input, username, password);

        int answer;
        do {
            Singleton.getPersonView().menu();
            answer = input.nextInt();

            switch (answer) {
                case 1 -> {
                    System.out.println("Add Person");
                    Singleton.getPersonServiceImp().addPerson(faker, url, username, password);
                }

                case 2 -> {
                    System.out.println("Show ALl Person");
                    Singleton.getPersonServiceImp().showAllPerson(url, username, password);
                }

                case 3 -> {
                    System.out.println("Delete");
                    Singleton.getPersonServiceImp().deletePerson(input, url, username, password);
                    System.out.println("Delete Successfully");
                }

                case 4 -> {
                    System.exit(0);
                }
            }
        }while (answer!=4);
    }
}
