package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import static Formatters.XpathFormatter.formatXpath;
import static com.codeborne.selenide.Selenide.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MostViewedGoodsPage {
    private static MostViewedGoodsPage mostViewedGoods;

    private String xpathGoodsTittle = "//div[contains(@class, 'product-mini-card__name')" +
            "and contains(.,'%s')]";
    private String xpathGoodsFinalPrice = "//following::span[contains(@class, 'price__main-value')][1]";
    private String xpathGoodsCartButton = "//following::button[contains(@class, 'actions__cart')][1]";
    private String xpathGoodsFavoriteButton = "//following::button[contains(@class, 'actions__favorite')][1]";
    private String xpathGoodsCompareButton = "//following::button[contains(@class, 'actions__compare')][1]";

    @FindBy (xpath = "//mvid-simple-product-collection-mp[contains(., 'Самые просматриваемые')]")
    private SelenideElement mostViewedGoodsContainer;
    private SelenideElement good;
    private ElementsCollection containers = $$x("//mvid-simple-product-collection-mp");
    Map<String, Integer> rememberMostViewedGoodsMap = new HashMap<>();

    public void setGood(String goodsName) {
        this.good = $x(formatXpath("", xpathGoodsTittle, goodsName));
    }

    private ElementsCollection getContainers() {
        return containers;
    }
    private SelenideElement getMostViewedGoodsContainer() {
        return mostViewedGoodsContainer;
    }
    private SelenideElement getGood() {
        return good;
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
     * Блок прогрузка контейнеров с товарами отображается.
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

    public void scrollToMostViewedGoodsContainer(){
        getMostViewedGoodsContainer().scrollIntoView("{block: \"center\"}");
        Selenide.sleep(200);
    }

    /**
     * Нажатие на кнопку добавления в корзину.
     */
    public void addGoodToCart(){
        getGood().find(By.xpath("."+ xpathGoodsCartButton))
                .scrollIntoView("{block: \"center\"}")
                .click();
        Selenide.sleep(1000);
    }

    /**
     * Проверка добавлен ли этот товар в корзину.
     * @return true - не добавлен, false - добавлен.
     */
    public boolean addGoodToCartIsInactive(){
        return getGood().find(By.xpath("."+ xpathGoodsCartButton)).getAttribute("title").equals("Добавить в корзину");
    }

    /**
     * Нажатие на кнопку добавления в избранное.
     */
    public void addGoodToFavorite(){
        getGood().find(By.xpath("."+ xpathGoodsFavoriteButton))
                .scrollIntoView("{block: \"center\"}")
                .click();
        Selenide.sleep(1000);
    }

    /**
     * Проверка добавлен ли этот товар в избранное.
     * @return true - добавлен, false - не добавлен.
     */
    public boolean addGoodToFavoriteIsInactive(){
        return getGood().find(By.xpath("."+ xpathGoodsFavoriteButton)).getAttribute("title").equals("Добавить в избранное");
    }

    /**
     * Нажатие на кнопку добавления в избранное.
     */
    public void addGoodToCompare(){
        getGood().find(By.xpath("."+ xpathGoodsCompareButton))
                .scrollIntoView("{block: \"center\"}")
                .click();
        Selenide.sleep(1000);
    }

    /**
     * Проверка добавлен ли этот товар в сравнение.
     * @return true - добавлен, false - не добавлен.
     */
    public boolean addGoodToCompareIsInactive(){
        return getGood().find(By.xpath("."+ xpathGoodsCompareButton)).getAttribute("title").equals("Добавить в сравнение");
    }

    /**
     * Запоминание в map название и цену товара дня.
     */
    public void rememberGood(){
        String goodsName = getGood()
                .find(By.xpath(".//a"))
                .getText();
        Integer goodsPrice = Integer.parseInt(getGood()
                .find(By.xpath("." + xpathGoodsFinalPrice))
                .getText()
                .replace(" ",""));
        getRememberMostViewedGoodsMap().put(goodsName, goodsPrice);
    }
}
