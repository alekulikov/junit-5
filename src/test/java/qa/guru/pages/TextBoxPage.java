package qa.guru.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {

    private final SelenideElement userNameInput = $("#userName"),
            userEmailInput = $("#userEmail"),
            currentAddressInput = $("#currentAddress"),
            permanentAddressInput = $("#permanentAddress"),
            submitButton = $("#submit"),
            outputComponent = $("#output");

    public TextBoxPage openPage() {
        open("/text-box");
        $(".container.playgound-body h1").shouldHave(text("Text Box"));

        return this;
    }

    public TextBoxPage setName(String value) {
        userNameInput.setValue(value);

        return this;
    }

    public TextBoxPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public TextBoxPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    public TextBoxPage setPermanentAddress(String value) {
        permanentAddressInput.setValue(value);

        return this;
    }

    public TextBoxPage submit() {
        submitButton.click();

        return this;
    }

    public TextBoxPage checkResultValue(String value) {
        outputComponent.shouldHave(text(value));

        return this;
    }
}
