import com.github.javafaker.Faker;
import model.Person;
import repository.PersonRepository;
import service.PersonService;
import utils.TableUtils;
import view.MainView;

import java.sql.*;
import java.util.*;
public class Main {
    private static final String databaseUrl="jdbc:postgresql://localhost:5432/javafinaldb";
    private static final String username="postgres";
    private static final String password="1234";
    private static PersonService personService =
            new PersonService(new PersonRepository());

    public static void main(String[] args) {
        try {
            // Establish connection to the PostgreSQL database
            Connection connection = DriverManager.getConnection(databaseUrl, username, password);
            Scanner input = new Scanner(System.in);

            // Login loop
            boolean loggedIn = false;
            do {
                System.out.print("Enter username: ");
                String username = input.nextLine();
                System.out.print("Enter password: ");
                String password = input.nextLine();

                // Query to check if the username and password match in the database
                String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    loggedIn = true;
                } else {
                    System.out.println("Incorrect username or password. Please try again.");
                }
            } while (!loggedIn);

            // Proceed to main menu
            int option;
            do {
                option = MainView.renderMain(input);
                switch (option) {
                    case 1: {
                        input.nextLine(); // clear buffer
                        System.out.println(
                                personService.createPerson(input) > 0 ?
                                        "Successfully Created a New Person"
                                        : ""
                        );

                    }
                    break;
                    case 2: {
                        System.out.println(
                                personService
                                        .updatePerson(input) > 0 ?
                                        "Successfully Update Person Info"
                                        : ""
                        );
                    }
                    break;
                    case 3: {
                        System.out.println(
                                personService
                                        .deletePersonByID(input) > 0 ?
                                        "Successfully Remove the Person"
                                        : "");
                        ;
                    }
                    break;
                    case 4: {
                        int showOption;
                        List<String> showMenu = new ArrayList<>(List.of(
                                "Show Original Order",
                                "Show Descending Order (ID)",
                                "Show Descending Order (name) ",
                                "Exit"));
                        do {
                            TableUtils.renderMenu(showMenu, "Show Person Information");
                            System.out.print("Choose your option: ");
                            showOption = input.nextInt();


                            switch (showOption) {
                                case 1:

                                    TableUtils.renderObjectToTable(personService.getAllPerson());
                                    break;
                                case 2:
                                    // descending id
                                    TableUtils.renderObjectToTable(
                                            personService.getAllPersonDescendingByID()
                                    );
                                    break;
                                case 3:
                                    // descending name
                                    TableUtils.renderObjectToTable(
                                            personService.getAllPersonDescendingByName()
                                    );
                                    break;
                                default:
                                    System.out.println("Invalid option ...!!!!");
                                    break;
                            }
                        } while (showOption != showMenu.size());
                    }
                    break;
                    case 5: {
                        int searchOption;
                        List<String> searchMenu = new ArrayList<>(Arrays.asList(
                                "Search By ID",
                                "Search By Gender",
                                "Search By Country",
                                "Exit"));
                        do {
                            TableUtils.renderMenu(searchMenu, "Search for Person");
                            System.out.print("Choose your option:");
                            searchOption = input.nextInt();
                            switch (searchOption) {
                                case 1:
                                    int searchID = 0;
                                    System.out.println("Enter Person ID to search:");
                                    searchID = input.nextInt();
                                    int finalSearchID = searchID;
                                    try {
                                        Person optionalPerson =
                                                personService.getAllPerson()
                                                        .stream()
                                                        .filter(person -> person.getId() == finalSearchID)
                                                        .findFirst()
                                                        .orElseThrow(() -> new ArithmeticException("Whatever exception!! "));
                                        TableUtils.renderObjectToTable(
                                                Collections.singletonList(optionalPerson));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                        System.out.println("There is no element with ID=" + searchID);
                                    }

                                    break;
                                case 2:
                                    break;
                                case 3:
                                    break;
                            }

                        } while (searchOption != searchMenu.size());

                    }
                    break;
                    case 6:
                        System.out.println("Exit from the program!!! ");
                        break;
                    default:
                        System.out.println("Invalid Option!!!!!! ");
                        break;
                }

            } while (option != 6);

            // Close resources
            input.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

