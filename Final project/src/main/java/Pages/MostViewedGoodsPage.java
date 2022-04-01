package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MostViewedGoodsPage {
    private static MostViewedGoodsPage mostViewedGoods;

    private String xpathMostViewedGoodsContainer = "//mvid-simple-product-collection-mp[contains(., 'Самые просматриваемые')]";
    private String xpathGoodsFinalPrice = "//following::span[contains(@class, 'price__main-value')][1]";
    private String xpathGoodsCartButton = "//following::button[contains(@class, 'actions__cart')][1]";
    private String xpathGoodsFavoriteButton = "//following::button[contains(@class, 'actions__favorite')][1]";
    private String xpathGoodsCompareButton = "//following::button[contains(@class, 'actions__compare')][1]";
    private String xpathForwardButton = "//button[contains(@class, 'forward')]";
    private String xpathBackwardButton = "//button[contains(@class, 'backward')]";

    @FindBy (xpath = "//mvid-simple-product-collection-mp[contains(., 'Самые просматриваемые')]")
    private SelenideElement mostViewedGoodsContainer;
    //@FindBy(xpath = "//div[contains(@class, 'product-mini-card__name')]")
    private ElementsCollection goodsTittleCollections = $$x (xpathMostViewedGoodsContainer + "//div[contains(@class, 'product-mini-card__name')]");
    @FindBy (xpath = "//mvid-simple-product-collection-mp")
    private ElementsCollection containers;
    private SelenideElement forwardButton = $x(xpathMostViewedGoodsContainer + xpathForwardButton);
    private SelenideElement backwardButton = $x(xpathMostViewedGoodsContainer + xpathBackwardButton);

    private Map<String, Integer> rememberMostViewedGoodsMap = new HashMap<>();

    private ElementsCollection getContainers() {
        return containers;
    }
    private SelenideElement getMostViewedGoodsContainer() {
        return mostViewedGoodsContainer;
    }
    private SelenideElement getForwardButton() {
        return forwardButton;
    }
    private SelenideElement getBackwardButton() {
        return backwardButton;
    }
    private ElementsCollection getTittlesCollection() {
        return goodsTittleCollections;
    }
    public Map<String, Integer> getRememberMostViewedGoodsMap() {
        return rememberMostViewedGoodsMap;
    }

    private MostViewedGoodsPage() {}
    public static MostViewedGoodsPage getMostViewedGoods () {
        if (Objects.isNull(mostViewedGoods)) mostViewedGoods = Selenide.page(new MostViewedGoodsPage());
        return mostViewedGoods;
    }

    /**
     * Прогрузка блоков контейнеров.
     */
    public void showAllContainers(){
        getContainers().asDynamicIterable()
                .forEach(containers -> containers
                        .scrollIntoView("{block: \"center\"}")
                        .shouldBe(Condition.visible));
    }

    /**
     * Блок "Самые просматриваемые" отображается.
     * @return true - отображается, false - не отображается.
     */
    public boolean mostViewedGoodsContainerIsDisplayed(){
        return getMostViewedGoodsContainer().scrollIntoView("{block: \"center\"}")
                .isDisplayed();
    }

    /**
     * Прокрутка к блоку "Самые просматриваемые".
     */
    public void scrollToMostViewedGoodsContainer(){
        getMostViewedGoodsContainer().scrollIntoView("{block: \"center\"}");
        Selenide.sleep(300);
    }

    /**
     * Возращение кол-ва товаров на странице.
     * @return кол-ва товаров на странице
     */
    public Integer quantityOfGoods() {
        return getTittlesCollection().size();
    }

    /**
     * Нажатие на кнопку добавления в корзину.
     * @param goodsPosition номер позиции товара.
     */
    public void addGoodToCart(Integer goodsPosition){
        getTittlesCollection().get(goodsPosition)
                .find(By.xpath("."+ xpathGoodsCartButton))
                .scrollIntoView("{block: \"center\"}")
                .click();
        Selenide.sleep(1000);
    }

    /**
     * Проверка добавлен ли этот товар в корзину.
     * @param goodsPosition номер позиции товара.
     * @return true - не добавлен, false - добавлен.
     */
    public boolean addGoodToCartIsInactive(Integer goodsPosition){
        return getTittlesCollection().get(goodsPosition)
                .find(By.xpath("."+ xpathGoodsCartButton))
                .getAttribute("title")
                .equals("Добавить в корзину");
    }

    /**
     * Запоминание в map название и цену добавляемого в корзину товара.
     * @param goodsPosition номер позиции товара.
     */
    public void rememberCartGood(Integer goodsPosition){
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
        getRememberMostViewedGoodsMap().put(goodsName, goodsPrice);
    }

    public boolean tittleIsDisplayed(Integer goodsPosition){
        return getTittlesCollection().get(goodsPosition).isDisplayed();
    }

    public boolean forwardButtonIsDisplayed(){
        return getForwardButton()
                .scrollIntoView("{block: \"center\"}")
                .isDisplayed();
    }

    public void forwardButtonClick(){
        getForwardButton()
                .scrollIntoView("{block: \"center\"}")
                .click();
    }

    public boolean backwardButtonIsDisplayed(){
        return getBackwardButton()
                .scrollIntoView("{block: \"center\"}")
                .isDisplayed();
    }

    public void backwardButtonClick(){
        getBackwardButton()
                .scrollIntoView("{block: \"center\"}")
                .click();
    }

}
