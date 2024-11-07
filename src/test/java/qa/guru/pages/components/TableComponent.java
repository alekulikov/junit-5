package qa.guru.pages.components;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TableComponent {
    public void checkTableValue(String key, String value) {
        $(".table-responsive").$(byText(key)).parent()
                .shouldHave(text(value));
    }

    public void checkTableValueIsEmpty(String key) {
        $(".table").$(byText(key)).sibling(0)
                .shouldBe(empty);
    }

    public void checkTableIsNotVisible() {
        $(".table").shouldNotBe(visible);
    }
}
