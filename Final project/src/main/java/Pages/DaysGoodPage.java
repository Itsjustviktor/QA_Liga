package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import java.util.*;

public class DaysGoodPage {
    private static DaysGoodPage daysGoodPage;

    /**
     * Xpath для поиска кнопки "В корзину".
     */
    private String xpathDaysProductAddToCartButton = "//button[contains(@class, 'cart_btn')]";
    /**
     * Xpath для поиска названия товара.
     */
    private String xpathGoodTitle ="//div[contains(@class, 'title')]//a";
    /**
     * Xpath для поиска цены товара.
     */
    private String xpathGoodPrice ="//span[@class='price__main-value']";

    @FindBy(xpath = "//mvid-day-product[contains(@class, 'main') and contains(@class, 'product')]")
    private SelenideElement daysGoodContainer;
    Map<String, Integer> rememberDaysGoodsMap = new HashMap<>();

    private SelenideElement getDaysGoodContainer() {
        return daysGoodContainer;
    }

    public Map<String, Integer> getRememberDaysGoodsMap() {
        return rememberDaysGoodsMap;
    }

    private DaysGoodPage() {}
    public static DaysGoodPage getDaysGoodPage() {
        if (Objects.isNull(daysGoodPage)) daysGoodPage = Selenide.page(new DaysGoodPage());
        return daysGoodPage;
    }

    /**
     * Отображение товара дня.
     * @return true - отображается, false - не отображается.
     */
    public boolean daysGoodIsDisplayed(){
        return getDaysGoodContainer()
                .isDisplayed();
    }

    /**
     * Нажатие на кнопку "В корзину" у товара дня
     */
    public void daysGoodAddToCart(){
        getDaysGoodContainer()
                .find(By.xpath("." + xpathDaysProductAddToCartButton))
                .shouldBe(Condition.visible)
                .scrollIntoView("{block: \"center\"}")
                .click();
    }

    /**
     * Запоминание в map название и цену товара дня.
     */
    public void rememberGood(){
        String goodsName = getDaysGoodContainer()
                                        .find(By.xpath("." + xpathGoodTitle))
                                        .getText();
        Integer goodsPrice = Integer.parseInt(getDaysGoodContainer()
                                        .find(By.xpath("." + xpathGoodPrice))
                                        .getText()
                                        .replace(" ",""));
        getRememberDaysGoodsMap().put(goodsName, goodsPrice);
    }

}
