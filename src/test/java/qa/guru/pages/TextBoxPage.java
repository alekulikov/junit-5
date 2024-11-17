package qa.guru.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TextBoxPage {

    private final SelenideElement userNameInput = $("#userName"),
            userEmailInput = $("#userEmail"),
            currentAddressInput = $("#currentAddress"),
            permanentAddressInput = $("#permanentAddress"),
            submitButton = $("#submit"),
            outputComponent = $("#output");

    @Step("Открыть страницу")
    public TextBoxPage openPage() {
        open("/text-box");
        $(".container.playgound-body h1").shouldHave(text("Text Box"));

        return this;
    }

    @Step("Убрать баннеры")
    public TextBoxPage removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    @Step("Заполнить имя")
    public TextBoxPage setName(String value) {
        userNameInput.setValue(value);

        return this;
    }

    @Step("Заполнить email")
    public TextBoxPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    @Step("Заполнить текущий адрес")
    public TextBoxPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    @Step("Заполнить постоянный адрес")
    public TextBoxPage setPermanentAddress(String value) {
        permanentAddressInput.setValue(value);

        return this;
    }

    @Step("Отправить форму")
    public TextBoxPage submit() {
        submitButton.click();

        return this;
    }

    @Step("Значение {value} присутствует на странице")
    public TextBoxPage checkResultValue(String value) {
        outputComponent.shouldHave(text(value));

        return this;
    }
}
