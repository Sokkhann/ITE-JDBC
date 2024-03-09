package service;

import com.github.javafaker.Faker;
import model.Person;

import java.util.List;
import java.util.Scanner;

public interface IPersonService {
    void addPerson(Faker faker, String url, String username, String password);
    List<Person> showAllPerson(String url, String username, String password);
    void deletePerson(Scanner input, String url, String username, String password);
}
