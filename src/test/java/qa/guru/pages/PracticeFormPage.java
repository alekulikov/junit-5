package qa.guru.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import qa.guru.pages.components.CalendarComponent;
import qa.guru.pages.components.TableComponent;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

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

    @Step("Открыть страницу")
    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        return this;
    }

    @Step("Убрать баннеры")
    public PracticeFormPage removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    @Step("Заполнить имя")
    public PracticeFormPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Заполнить фамилию")
    public PracticeFormPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    @Step("заполнить email")
    public PracticeFormPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    @Step("Выбрать пол")
    public PracticeFormPage setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    @Step("Заполнить номер телефона")
    public PracticeFormPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    @Step("Заполнить дату рождения")
    public PracticeFormPage setDateOfBirth(LocalDate birthday) {
        calendarInput.click();
        calendarComponent.setDate(String.format("%02d", birthday.getDayOfMonth()),
                birthday.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                String.valueOf(birthday.getYear()));

        return this;
    }

    @Step("Добавить предмет")
    public PracticeFormPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();

        return this;
    }

    @Step("Выбрать хобби")
    public PracticeFormPage setHobby(String hobby) {
        hobbiesWrapper.$(byText(hobby)).click();

        return this;
    }

    @Step("Загрузить изображение")
    public PracticeFormPage setPicture(String fileName) {
        uploadPictureControl.uploadFromClasspath(fileName);

        return this;
    }

    @Step("Заполнить текущий адрес")
    public PracticeFormPage setCurrentAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);

        return this;
    }

    @Step("Выбрать штат")
    public PracticeFormPage setState(String state) {
        stateComponent.click();
        stateComponent.$(byText(state)).click();

        return this;
    }

    @Step("Выбрать город")
    public PracticeFormPage setCity(String city) {
        cityComponent.click();
        cityComponent.$(byText(city)).click();

        return this;
    }

    @Step("Отправить форму")
    public PracticeFormPage submit() {
        submitButton.scrollTo();
        submitButton.click();

        return this;
    }

    @Step("Полю {key} соответствует значение {value}")
    public PracticeFormPage checkResultValue(String key, String value) {
        resultsTable.checkTableValue(key, value);

        return this;
    }

    @Step("Поле {key} не заполнено")
    public PracticeFormPage checkResultValueIsEmpty(String key) {
        resultsTable.checkTableValueIsEmpty(key);

        return this;
    }

    @Step("Форма не была заполнена")
    public PracticeFormPage checkResultIsNotVisible() {
        resultsTable.checkTableIsNotVisible();

        return this;
    }
}