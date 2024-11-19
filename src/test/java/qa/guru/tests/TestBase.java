package qa.guru.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static qa.guru.helpers.AttachHelper.*;

public class TestBase {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "chrome_126").split("_")[1];
        Configuration.browserSize = System.getProperty("windowSize", "1920x1080");
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@" + System.getProperty("remoteHost") + "/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListeners() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void attachesAndCloseBrowser() {
        screenshotAs("Result");
        pageSource();
        browserConsoleLogs();
        addVideo();
        closeWebDriver();
    }
}
