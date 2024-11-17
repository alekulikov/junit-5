package qa.guru.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import qa.guru.model.Gender;
import qa.guru.pages.PracticeFormPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Stream;

import static qa.guru.model.Gender.*;
import static qa.guru.utils.RandomUtils.*;

@Feature("Регистрация пользователя")
@Story("Регистрация пользователя в расширенной форме")
@Owner("alekulikov")
@Link(value = "Testing", url = "https://github.com/alekulikov/junit-5")
class PracticeFormTests extends TestBase {

    public PracticeFormPage practiceFormPage = new PracticeFormPage();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH);

    public static Stream<Arguments> fullFieldsTest() {
        return Stream.of(
                Arguments.of(MALE, LocalDate.now().minusYears(100)),
                Arguments.of(FEMALE, LocalDate.now().minusYears(50)),
                Arguments.of(OTHER, LocalDate.now().minusYears(18))
        );
    }

    @Severity(SeverityLevel.CRITICAL)
    @MethodSource
    @Tag("WEB")
    @ParameterizedTest(name = "Успешная регистрация с заполнением всех полей - {0}, дата рождения {1}")
    void fullFieldsTest(Gender gender, LocalDate birthDate) {
        var firstName = getRandomFirstName();
        var lastName = getRandomLastName();
        var email = getRandomEmail();
        var currentAddress = getRandomAddress();
        var phoneNumber = getRandomPhoneNumber(10);
        var state = getRandomState();
        var city = getCity(state);
        var picture = getRandomPicture();
        var subject = getRandomSubject();
        var hobby = getRandomHobby();

        practiceFormPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender.getDescription())
                .setUserNumber(phoneNumber)
                .setDateOfBirth(birthDate)
                .setSubject(subject)
                .setHobby(hobby)
                .setPicture(picture)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .submit();

        practiceFormPage
                .checkResultValue("Student Name", firstName + " " + lastName)
                .checkResultValue("Student Email", email)
                .checkResultValue("Gender", gender.getDescription())
                .checkResultValue("Mobile", phoneNumber)
                .checkResultValue("Date of Birth", birthDate.format(formatter))
                .checkResultValue("Subjects", subject)
                .checkResultValue("Hobbies", hobby)
                .checkResultValue("Picture", picture)
                .checkResultValue("Address", currentAddress)
                .checkResultValue("State and City", state + " " + city);
    }

    @Severity(SeverityLevel.BLOCKER)
    @EnumSource(Gender.class)
    @Tags({
            @Tag("SMOKE"),
            @Tag("WEB")
    })
    @ParameterizedTest(name = "Успешная регистрация с заполнением только обязательных полей - {0}")
    public void onlyRequestedFields(Gender gender) {
        var firstName = getRandomFirstName();
        var lastName = getRandomLastName();
        var phoneNumber = getRandomPhoneNumber(10);
        var birthDate = getRandomBirthDay(18, 100);

        practiceFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender.getDescription())
                .setUserNumber(phoneNumber)
                .setDateOfBirth(birthDate)
                .submit();

        practiceFormPage
                .checkResultValue("Student Name", firstName + " " + lastName)
                .checkResultValueIsEmpty("Student Email")
                .checkResultValue("Gender", gender.getDescription())
                .checkResultValue("Mobile", phoneNumber)
                .checkResultValue("Date of Birth", birthDate.format(formatter))
                .checkResultValueIsEmpty("Subjects")
                .checkResultValueIsEmpty("Hobbies")
                .checkResultValueIsEmpty("Picture")
                .checkResultValueIsEmpty("Address")
                .checkResultValueIsEmpty("State and City");
    }

    @Severity(SeverityLevel.BLOCKER)
    @ValueSource(strings = {
            "5553535",
            "+09123245213",
            "12345!7890"
    })
    @Tags({
            @Tag("SMOKE"),
            @Tag("WEB")
    })
    @ParameterizedTest(name = "Регистрация с некорректным номером телефона не происходит - {0}")
    void incorrectPhoneNumberTest(String phoneNumber) {
        var firstName = getRandomFirstName();
        var lastName = getRandomLastName();
        var gender = getRandomGender();
        var birthDate = getRandomBirthDay(18, 100);

        practiceFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .setDateOfBirth(birthDate)
                .submit();

        practiceFormPage.checkResultIsNotVisible();
    }
}