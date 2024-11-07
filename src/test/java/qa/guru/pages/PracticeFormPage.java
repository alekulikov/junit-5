package qa.guru.pages;

import com.codeborne.selenide.SelenideElement;
import qa.guru.pages.components.CalendarComponent;
import qa.guru.pages.components.TableComponent;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {
    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            uploadPictureControl = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateComponent = $("#state"),
            cityComponent = $("#city"),
            submitButton = $("#submit");

    CalendarComponent calendarComponent = new CalendarComponent();
    TableComponent resultsTable = new TableComponent();

    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        return this;
    }

    public PracticeFormPage removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public PracticeFormPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public PracticeFormPage setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public PracticeFormPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public PracticeFormPage setDateOfBirth(LocalDate birthday) {
        calendarInput.click();
        calendarComponent.setDate(String.format("%02d", birthday.getDayOfMonth()),
                birthday.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                String.valueOf(birthday.getYear()));

        return this;
    }

    public PracticeFormPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();

        return this;
    }

    public PracticeFormPage setHobby(String hobby) {
        hobbiesWrapper.$(byText(hobby)).click();

        return this;
    }

    public PracticeFormPage setPicture(String fileName) {
        uploadPictureControl.uploadFromClasspath(fileName);

        return this;
    }

    public PracticeFormPage setCurrentAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);

        return this;
    }

    public PracticeFormPage setState(String state) {
        stateComponent.click();
        stateComponent.$(byText(state)).click();

        return this;
    }

    public PracticeFormPage setCity(String city) {
        cityComponent.click();
        cityComponent.$(byText(city)).click();

        return this;
    }

    public PracticeFormPage submit() {
        submitButton.scrollTo();
        submitButton.click();

        return this;
    }

    public PracticeFormPage checkResultValue(String key, String value) {
        resultsTable.checkTableValue(key, value);

        return this;
    }

    public PracticeFormPage checkResultValueIsEmpty(String key) {
        resultsTable.checkTableValueIsEmpty(key);

        return this;
    }

    public PracticeFormPage checkResultIsNotVisible() {
        resultsTable.checkTableIsNotVisible();

        return this;
    }
}