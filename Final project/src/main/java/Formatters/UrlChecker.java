package Formatters;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;

public class UrlChecker {
    /**
     * Проверка url на соответствие.
     * @param expectedUrl ожидаемое url.
     */
    public static void urlChecker(String expectedUrl) {
        Selenide.sleep(500);
        String currentUrl = WebDriverRunner.url();
        Assert.assertTrue(currentUrl.contains(expectedUrl),
                "Url не совпадают.");
    }
}
