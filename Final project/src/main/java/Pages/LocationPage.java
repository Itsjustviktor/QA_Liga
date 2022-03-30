package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;
import static Formatters.XpathFormatter.formatXpath;

public class LocationPage {
    private static LocationPage locationPage;

    /**
     * Xpath для поиска города по названию.
     */
    private String xpathCity = "//li[text() = '%s']";
    /**
     * Xpath для поиска заголовка "Выберите город".
     */
    private String xpathHeader = "//h3[contains(.,'Выберите город')]";

    @FindBy(xpath = "//div[contains(@class, 'modal-layout')]")
    private SelenideElement modalWindow;

    private SelenideElement getModalWindow() {
        return modalWindow;
    }

    private LocationPage() {}
    public static LocationPage getLocationPage() {
        if (Objects.isNull(locationPage)) locationPage = Selenide.page(new LocationPage());
        return locationPage;
    }

    /**
     * Проверка что город находится в окне выбора города.
     * @param city город.
     * @return true - город присутствует, false - отсутствует.
     */
    public boolean cityIsExist(String city){
        return Selenide
                .$x(formatXpath("", xpathCity, city))
                .isDisplayed();
    }

    /**
     * Выбор города.
     * @param city город.
     */
    public void selectCity(String city) {
        Selenide
                .$x(formatXpath("",xpathCity, city))
                .click();
    }

    /**
     * Проверка видимости модального окна с выбором города.
     * @return true - окно видимо, false - невидимо.
     */
    public boolean modalWindowIsDisplayed(){
        return getModalWindow().isDisplayed();
    }

    /**
     * Проверка видимости модального окна с выбором города.
     * @return true - окно невидимо, false - видимо.
     */
    public boolean modalWindowIsntDisplayed(){
        return !getModalWindow()
                .shouldBe(Condition.disappear)
                .isDisplayed();
    }

    /**
     * Проверка отображения заголовка "Выберите город".
     * @return true - отображается, false - не отображается.
     */
    public boolean headerIsDisplayed(){
        return getModalWindow()
                .find(By.xpath("." + xpathHeader))
                .shouldBe(Condition.visible)
                .isDisplayed();
    };

}
