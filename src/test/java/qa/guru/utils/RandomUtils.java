package qa.guru.utils;

import com.github.javafaker.Faker;
import qa.guru.model.Gender;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Map;

public class RandomUtils {

    private static final Faker FAKER = new Faker(Locale.ENGLISH);
    private static final String[] STATES = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    private static final Map<String, String[]> CITIES_BY_STATE = Map.of(
            "NCR", new String[]{"Delhi", "Gurgaon", "Noida"},
            "Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"},
            "Haryana", new String[]{"Karnal", "Panipat"},
            "Rajasthan", new String[]{"Jaipur", "Jaiselmer"}
    );
    private static final String[] SUBJECTS = {"English", "Chemistry", "Computer Science",
            "Commerce", "Economics", "Social Studies", "Arts", "History",
            "Maths", "Accounting", "Physics", "Biology", "Hindi", "Civics"};
    private static final String[] HOBBIES = {"Sports", "Reading", "Music"};
    private static final String[] PICTURES = {"img.png"};

    public static String getRandomFirstName() {
        return FAKER.name().firstName();
    }

    public static String getRandomLastName() {
        return FAKER.name().lastName();
    }

    public static String getRandomGender() {
        return FAKER.options().option(Gender.class).getDescription();
    }

    public static LocalDate getRandomBirthDay(int minAge, int maxAge) {
        var birthDay = FAKER.date().birthday(minAge, maxAge);

        return LocalDate.ofInstant(birthDay.toInstant(), ZoneId.systemDefault());
    }

    public static String getRandomPhoneNumber(int length) {
        return FAKER.phoneNumber().subscriberNumber(length);
    }

    public static String getRandomEmail() {
        return FAKER.internet().emailAddress();
    }

    public static String getRandomAddress() {
        return String.format("%s, %s, %s",
                FAKER.address().streetAddressNumber(),
                FAKER.address().city(),
                FAKER.address().country());
    }

    public static String getRandomState() {
        return FAKER.options().option(STATES);
    }

    public static String getCity(String state) {
        var cities = CITIES_BY_STATE.get(state);

        return FAKER.options().option(cities);
    }

    public static String getRandomSubject() {
        return FAKER.options().option(SUBJECTS);
    }

    public static String getRandomHobby() {
        return FAKER.options().option(HOBBIES);
    }

    public static String getRandomPicture() {
        return FAKER.options().option(PICTURES);
    }

}
