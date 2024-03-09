package repository;

import com.github.javafaker.Faker;
import model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository {
    static Faker faker = new Faker();
    private List<Person> cachePerson = null;

    public List<Person> getAllPerson() {

        if (cachePerson!=null) {
            return cachePerson;
        }
        List<Person> personData = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            personData.add(new Person()
                    .setId(100 + i)
                    .setFullName(faker.name().fullName())
                    .setGender(faker.options().option("male", "female"))
                    .setEmail(faker.internet().emailAddress())
                    .setAddress(faker.address().country()));
        }
        cachePerson = personData;
        return personData;
    }
}
