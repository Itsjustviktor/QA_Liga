package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;


public class GoodsPage {
    private static GoodsPage goodsPage;

    private String xpathGoodsFinalPrice = "//following::span[contains(@class, 'price__main-value')][1]";
    private String xpathGoodsFavoriteButton = "//following::div[contains(@class, 'wishlist-button-block')][1]" +
            "//button[contains(@class, 'button')][1]";
    private String xpathGoodsCompareButton = "//following::div[contains(@class, 'compare-button-block')][1]" +
            "//button[contains(@class, 'button')][1]";

    @FindBy(xpath = "//div[contains(@class, 'product-card__title-line-container')]")
    private ElementsCollection tittlesCollection;
    @FindBy (xpath = "//mvid-skeleton")
    private SelenideElement loader;
    @FindBy(xpath = "//div[contains(@class, 'product-cards-row')]")
    private ElementsCollection productContainers;
    private Map<String, Integer> rememberFavoriteGoodsMap = new HashMap<>();
    private Map<String, Integer> rememberCompareGoodsMap = new HashMap<>();

    private ElementsCollection getProductContainers() {
        return productContainers;
    }
    public Map<String, Integer> getRememberFavoriteGoodsMap() {
        return rememberFavoriteGoodsMap;
    }
    public Map<String, Integer> getRememberCompareGoodsMap() {
        return rememberCompareGoodsMap;
    }
    private SelenideElement getLoader() {
        return loader;
    }
    public ElementsCollection getTittlesCollection() {
        return tittlesCollection;
    }

    private GoodsPage() {}
    public static GoodsPage getGoodsPage() {
        if (Objects.isNull(goodsPage)) goodsPage = Selenide.page(new GoodsPage());
        return goodsPage;
    }


    /**
     * Метод для прогрузки товаров на странице.
     */
    public void showProducts(){
        getProductContainers()
                .asDynamicIterable()
                .forEach(ele -> ele.scrollIntoView("{block: \"center\"}"));
    }

    /**
     * Возращение кол-ва товаров на странице.
     * @return кол-ва товаров на странице
     */
    public Integer quantityOfGoods() {
        return getTittlesCollection().size();
    }

    /**
     * Ожидание исчезновения loader.
     * @return true - loader не виден, false - виден.
     */
    public void loaderShouldBeDisappear(){
        getLoader().shouldBe(Condition.disappear, Duration.ofSeconds(20));
    }

    /**
    * Нажатие на кнопку добавления в избранное.
     * @param goodsPosition номер позиции товара.
    */
    public void addGoodToFavorite(Integer goodsPosition){
        getTittlesCollection().get(goodsPosition)
                .find(By.xpath("."+ xpathGoodsFavoriteButton))
                .scrollIntoView("{block: \"center\"}")
                .click();
        Selenide.sleep(1000);
    }

    /**
     * Проверка добавлен ли этот товар в избранное.
     * @param goodsPosition номер позиции товара.
     * @return true - добавлен, false - не добавлен.
     */
    public boolean addGoodToFavoriteIsInactive(Integer goodsPosition){
        return getTittlesCollection().get(goodsPosition)
                .find(By.xpath("."+ xpathGoodsFavoriteButton))
                .getAttribute("title")
                .equals("Добавить в избранное");
    }

    /**
     * Нажатие на кнопку добавления в сравнение.
     * @param goodsPosition номер позиции товара.
     */
    public void addGoodToCompare(Integer goodsPosition) {
        getTittlesCollection().get(goodsPosition)
                .find(By.xpath("." + xpathGoodsCompareButton))
                .scrollIntoView("{block: \"center\"}")
                .click();
        Selenide.sleep(1000);
    }

    /**
     * Проверка добавлен ли этот товар в сравнение.
     * @param goodsPosition номер позиции товара.
     * @return true - не добавлен, false - добавлен.
     */
    public boolean addGoodToCompareIsInactive(Integer goodsPosition){
        return getTittlesCollection().get(goodsPosition)
                .find(By.xpath("."+ xpathGoodsCompareButton))
                .getAttribute("title")
                .equals("Добавить в сравнение");
    }

    /**
     * Запоминание в map название и цену добавляемого в избранное товара.
     * @param goodsPosition номер позиции товара.
     */
    public void rememberFavoriteGood(Integer goodsPosition){
            String goodsName = getTittlesCollection().get(goodsPosition)
                        .find(By.xpath(".//a"))
                        .getText()
                        .replace("&nbsp;", " ");
            Integer goodsPrice = Integer.parseInt(
                    getTittlesCollection().get(goodsPosition)
                    .find(By.xpath("." + xpathGoodsFinalPrice))
                    .getText()
                    .replace(" ","")
                    .replace("₽", ""));
            getRememberFavoriteGoodsMap().put(goodsName, goodsPrice);
    }

    /**
     * Запоминание в map название и цену добавляемого в сравнение товара.
     * @param goodsPosition номер позиции товара.
     */
    public void rememberCompareGood(Integer goodsPosition){
        String goodsName = getTittlesCollection().get(goodsPosition)
                .find(By.xpath(".//a"))
                .getText()
                .replace("&nbsp;", " ");
        Integer goodsPrice = Integer.parseInt(
                getTittlesCollection().get(goodsPosition)
                        .find(By.xpath("." + xpathGoodsFinalPrice))
                        .getText()
                        .replace(" ","")
                        .replace("₽", ""));
        getRememberCompareGoodsMap().put(goodsName, goodsPrice);
    }

    /**
     * Проверка содержат ли все товары определенное слово в названии.
     * @param name название.
     * @return true - содержат, false - не содержат.
     */
    public boolean goodsContainName(String name){
        boolean elementContainsNameIsTrue = true;
        for (SelenideElement selenideElement : getTittlesCollection().asDynamicIterable()) {
            boolean elementThatContainsName = selenideElement.find(By.xpath(".//a"))
                    .getText()
                    .toLowerCase()
                    .replace("&nbsp;", " ")
                    .contains(name.toLowerCase());
            if (!elementThatContainsName) elementContainsNameIsTrue = false;
        }
        return elementContainsNameIsTrue;
    }


    /**
     * Проверка располагаются ли товары на странице по убыванию цены.
     * @return true - сортировка соответствует, false - сортировка не соответствует.
     */
    public boolean goodsPriceDecreases(){
        boolean goodsPriceDecreasesIsTrue = true;
        boolean currentGoodsPriceMoreThenNextPrice;
        for (int i = 0; i < getTittlesCollection().size()-1; i++){
                currentGoodsPriceMoreThenNextPrice = Integer.parseInt(getTittlesCollection().get(i)
                        .find(By.xpath("." + xpathGoodsFinalPrice))
                        .getText()
                        .replace(" ","")
                        .replace("₽", ""))
                    >=
                        Integer.parseInt(getTittlesCollection().get(i+1)
                                .find(By.xpath("." + xpathGoodsFinalPrice))
                                .getText()
                                .replace(" ","")
                                .replace("₽", ""));
                if (!currentGoodsPriceMoreThenNextPrice) goodsPriceDecreasesIsTrue = false;
            }
        return goodsPriceDecreasesIsTrue;
    }




}
