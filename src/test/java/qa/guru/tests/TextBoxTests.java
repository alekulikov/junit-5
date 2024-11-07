package qa.guru.tests;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import qa.guru.pages.TextBoxPage;

import static qa.guru.utils.RandomUtils.*;

public class TextBoxTests extends TestBase {

    TextBoxPage textBoxPage = new TextBoxPage();

    @Tags({
            @Tag("SMOKE"),
            @Tag("WEB")
    })
    @Test
    @DisplayName("Успешная регистрация")
    void fillFormTest() {
        var firstName = getRandomFirstName();
        var email = getRandomEmail();
        var currentAddress = getRandomAddress();
        var permanentAddress = getRandomAddress();

        textBoxPage.openPage()
                .setName(firstName)
                .setEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .submit();

        textBoxPage
                .checkResultValue(firstName)
                .checkResultValue(email)
                .checkResultValue(currentAddress)
                .checkResultValue(permanentAddress);
    }
}