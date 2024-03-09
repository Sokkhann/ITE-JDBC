package service;

import com.github.javafaker.Faker;
import model.Person;
import utils.Singleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonServiceImp implements IPersonService{

    @Override
    public void addPerson(Faker faker, String url, String user, String password) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String queryCheckId = "SELECT COUNT(*) FROM person_tb WHERE id = ?";
            String queryInsertPerson = "INSERT INTO person_tb (id, fullname, gender, email, address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement checkIdStatement = connection.prepareStatement(queryCheckId);
            PreparedStatement insertPersonStatement = connection.prepareStatement(queryInsertPerson);

            int id;
            ResultSet resultSet;
            do {
                id = (int) faker.number().randomNumber();
                checkIdStatement.setInt(1, id);
                resultSet = checkIdStatement.executeQuery();
                resultSet.next();
            } while (resultSet.getInt(1) > 0);

            // Set values for the insert statement
            insertPersonStatement.setInt(1, id);
            insertPersonStatement.setString(2, faker.name().fullName());
            insertPersonStatement.setString(3, faker.demographic().sex());
            insertPersonStatement.setString(4, faker.internet().emailAddress());
            insertPersonStatement.setString(5, faker.address().country());

            int rowsInserted = insertPersonStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Person inserted successfully.");
            } else {
                System.out.println("Failed to insert person.");
            }
        } catch (SQLException e) {
            System.out.println("Database operation failed.");
            e.printStackTrace();
        }

    }
    @Override
    public List<Person> showAllPerson(String url, String username, String password) {
        List<Person> personList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM person_tb";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("All persons in the database:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("fullname");
                String gender = resultSet.getString("gender");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                Person person = new Person();

                person.setId(id);
                person.setFullName(name);
                person.setGender(gender);
                person.setEmail(email);
                person.setAddress(address);
                personList.add(person);
            }
            for (Person person : personList) {
                Singleton.getPersonView().renderPerson(personList);
            }
        } catch (SQLException e) {
            System.out.println("Database operation failed.");
            e.printStackTrace();
        }
        return personList;
    }
    @Override
    public void deletePerson(Scanner input, String url, String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.print("Please Enter ID: ");
            int id = input.nextInt();
            String query = "DELETE FROM person_tb WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Person deleted successfully.");
            } else {
                System.out.println("No person found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Database operation failed.");
            e.printStackTrace();
        }
    }

}