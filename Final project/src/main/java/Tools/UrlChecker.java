package Tools;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.testng.Assert;

public class UrlChecker {
    /**
     * Проверка url на соответствие.
     * @param expectedUrl ожидаемое url.
     */
    @Step("Проверка url на соответствие")
    public static void urlChecker(String expectedUrl) {
        String currentUrl = WebDriverRunner.url();
        Assert.assertTrue(currentUrl.contains(expectedUrl),
                "Url не совпадают.");
    }
}
