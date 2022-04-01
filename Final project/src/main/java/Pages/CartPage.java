package Pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.*;

import static com.codeborne.selenide.Selenide.$$x;

public class CartPage {
    private static CartPage cartPage;

    /**
     * Xpath для поиска названия товара.
     */
    private String xpathTittle = "//a[contains(@class,'c-cart-item__title')]";
    /**
     * Xpath для поиска последней цены (с учетом скидок и прочего).
     */
    private String xpathLastPrice = "//span[contains(@class,'c-cart-item__price')]";

    @FindBy (xpath = "//span[contains(@class, 'c-header-checkout__logo') and contains(.,'Моя корзина')]")
    private SelenideElement headerMyCart;
    @FindBy (xpath = "//input[@value='Перейти к оформлению']")
    private SelenideElement continueButton;
    @FindBy (xpath = "//div[contains(@class, 'c-cost-line__title-wrap')]//span[contains(@class, 'c-cost-line__title')]")
    private SelenideElement headerThatContainsQuantityGoods;
    @FindBy (xpath = "//span[contains(@class,'c-cost-line__text')]")
    private SelenideElement expectedSummOfGoods;
    private ElementsCollection goodsContainers = $$x("//div[contains(@class, 'c-cart-item__wrapper ')]");

    Map<String, Integer> cartsGoods = new LinkedHashMap<>();

    private ElementsCollection getGoodsContainers() {return goodsContainers;}
    private SelenideElement getHeaderMyCart() {
        return headerMyCart;
    }
    private SelenideElement getContinueButton() {
        return continueButton;
    }
    private SelenideElement getHeaderThatContainsQuantityGoods() {
        return headerThatContainsQuantityGoods;
    }
    private SelenideElement getExpectedSummOfGoods() {
        return expectedSummOfGoods;
    }
    private Map<String, Integer> getCartsGoods() {
        return cartsGoods;
    }


    private CartPage() {}
    public static CartPage getCartPage() {
        if (Objects.isNull(cartPage)) cartPage = Selenide.page(new CartPage());
        return cartPage;
    }

    /**
     * Получение URL страницы.
     * @return URL страницы.
     */
    public String getUrl()
    {
       return WebDriverRunner.url()
               .replace("https://www.mvideo.ru", "");
    }

    /**
     * Проверка видимости заголовка.
     * @return true - заголовок видим, false - невидим.
     */
    public boolean headerMyCartIsDisplayed(){
        return getHeaderMyCart().isDisplayed();
    }

    /**
     * Проверка пуста ли корзина.
     * @return true - пуста, false - не пуста.
     */
    public boolean cartIsntEmpty(){
        return getGoodsContainers().isEmpty();
    }

    /**
     * Определение кол-ва товаров, добавленных в корзину.
     * @return кол-во товаров в корзине.
     */
    public Integer getCartSize(){
        return getGoodsContainers().size();
    }

    /**
     * Запоминание товаров, лежащих в корзине, в map.
     */
    public void rememberGoodsNameAndPriceInCart() {
        getGoodsContainers().asDynamicIterable().forEach(
                selenideElement -> {
                    getCartsGoods().put(
                            selenideElement.find(By.xpath("." + xpathTittle))
                                    .getText(),
                            Integer.parseInt(selenideElement.find(By.xpath("." + xpathLastPrice))
                                    .getText()
                                    .replace(" ","")
                                    .replace("¤",""))
                    );
                }
        );
    }

    /**
     * Сравнение maps - товаров, лежащих в корзине, и товаров, отправленных в корзину. <br>
     * Сравнение происходит по названию и цене товаров.
     * @param receivedGoods map с отправленным товаром в корзину.
     * @return true - maps совпадают, false - не совпадают.
     */
    public boolean compareGoodsNameAndPrice(Map<String, Integer> receivedGoods){
        return getCartsGoods().entrySet()
                .stream()
                .allMatch(entry -> receivedGoods.containsKey(entry.getKey()) && receivedGoods.containsValue(entry.getValue()));
    }

    /**
     * Проверка видимости кнопки "Перейти к оформлению".
     * @return true - кнопка видна, false - не видна.
     */
    public boolean continueButtonIsDisplayed(){
        return getContinueButton().isDisplayed();
    }

    /**
     * Проверка видимости заголовка "В корзине X товар(а)".
     * @return true - заголовок виден, false - не виден.
     */
    public boolean headerThatContainsQuantityGoodsIsDisplayed(){
        return getHeaderThatContainsQuantityGoods().isDisplayed();
    }

    /**
     * Получения кол-ва товара из заголовка "В корзине X товар(а)".
     * @return кол-ва товара.
     */
    public Integer headerQuantityGoodsIsCart(){
        return Integer.parseInt(getHeaderThatContainsQuantityGoods().getText()
                .replace(" ", "")
                .replace("Вкорзине", "")
                .replace("товар", "")
                .replace("товара", ""));
    }

    /**
     * Получение и сложение стоимости всех товаров в корзине.
     * @return общая стоимость корзины.
     */
    public Integer realSummOfGoods(){
        Integer realSummOfGoods = 0;

        for (SelenideElement selenideElement : getGoodsContainers().asDynamicIterable()) {
            realSummOfGoods += Integer.parseInt(selenideElement.find(By.xpath("." + xpathLastPrice))
                    .getText()
                    .replace(" ","")
                    .replace("¤",""));
        }
        return realSummOfGoods;
    }

    /**
     * Получения общей ожидаемой стоимости товаров в корзине.
     * @return общая ожидаемая стоимость.
     */
    public Integer expectedSummOfGoods(){
         return Integer.parseInt(getExpectedSummOfGoods().getText()
                .replace(" ","")
                .replace("¤",""));
    }
}
