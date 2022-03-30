package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import static Formatters.XpathFormatter.formatXpath;
import static com.codeborne.selenide.Selenide.*;

import java.util.Objects;

public class MostViewedGoodsPage {
    private static MostViewedGoodsPage mostViewedGoods;

    private String xpathGoodsTittle = "//div[contains(@class, 'product-mini-card__name')" +
            "and contains(.,'%s')]";
    private String xpathGoodsFinalPrice = "//following::span[contains(@class, 'price__main-value')][1]";
    private String xpathGoodsCartButton = "//following::button[contains(@title, 'Добавить в корзину')][1]";
    private String xpathGoodsInactiveCartButton = "//following::button[contains(@class, 'actions__cart')][1]";
    private String xpathGoodsFavoriteButton = "//following::button[contains(@title, 'Добавить в избранное')][1]";
    private String xpathGoodsInactiveFavoriteButton = "//following::button[contains(@class, 'actions__favorite')][1]";
    private String xpathGoodsCompareButton = "//following::button[contains(@title, 'Добавить в сравнение')][1]";
    private String xpathGoodsInactiveCompareButton = "//following::button[contains(@class, 'actions__compare')][1]";

    @FindBy (xpath = "//mvid-simple-product-collection-mp[contains(., 'Самые просматриваемые')]")
    private SelenideElement mostViewedGoodsContainer;
    private SelenideElement good;
    private ElementsCollection containers = $$x("//mvid-simple-product-collection-mp");

    //mvid-simple-product-collection-mp[contains(., 'Самые просматриваемые')]
    //div[contains(@class, 'product-mini-card__name') and contains(.,'Смартфон Xiaomi Redmi Note 11 NFC 4GB+128GB Twilight Blue')]
    //following::span[contains(@class, 'price__main-value')][1]


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
        getGood().find(By.xpath("."+xpathGoodsCartButton))
                .scrollIntoView("{block: \"center\"}")
                .click();
        Selenide.sleep(1000);
    }

    /**
     * Проверка добавлен ли этот товар в корзину.
     * @return true - не добавлен, false - добавлен.
     */
    public boolean addGoodToCartIsInactive(){
        return getGood().find(By.xpath("."+xpathGoodsInactiveCartButton)).getAttribute("title").equals("Добавить в корзину");
    }

    /**
     * Нажатие на кнопку добавления в избранное.
     */
    public void addGoodToFavorite(){
        getGood().find(By.xpath("."+xpathGoodsFavoriteButton))
                .scrollIntoView("{block: \"center\"}")
                .click();
        Selenide.sleep(1000);
    }

    /**
     * Проверка добавлен ли этот товар в избранное.
     * @return true - добавлен, false - не добавлен.
     */
    public boolean addGoodToFavoriteIsInactive(){
        return getGood().find(By.xpath("."+xpathGoodsInactiveFavoriteButton)).getAttribute("title").equals("Добавить в избранное");
    }

    /**
     * Нажатие на кнопку добавления в избранное.
     */
    public void addGoodToCompare(){
        getGood().find(By.xpath("."+xpathGoodsCompareButton))
                .scrollIntoView("{block: \"center\"}")
                .click();
        Selenide.sleep(1000);
    }

    /**
     * Проверка добавлен ли этот товар в сравнение.
     * @return true - добавлен, false - не добавлен.
     */
    public boolean addGoodToCompareIsInactive(){
        return getGood().find(By.xpath("."+xpathGoodsInactiveCompareButton)).getAttribute("title").equals("Добавить в сравнение");
    }

}
