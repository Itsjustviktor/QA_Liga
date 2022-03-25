import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.*;

public class GoodPage {

    private SelenideElement titleLineContainer;
    private SelenideElement priceLabel;
    private SelenideElement addToCartButton;
    private SelenideElement addToFavoritesButton;
    private SelenideElement addToComparisonButton;

    private ElementsCollection paginationLinks = $$x("//li[contains(@class, 'page-item')]");
    private ElementsCollection productContainers = $$x("//div[contains(@class, 'product-cards-row')]");

    private final String XPATH_TITLE_LINE_CONTAINER = "//div[contains(@class, 'product-card__title-line-container')]";
    private final String XPATH_NESTED_NAME_LINK = "[mvid-plp-product-title[.//a[text() = ' %s ']]]";

    GoodPage(String productName) {
        String titleContainerPath = String.format(XPATH_TITLE_LINE_CONTAINER + XPATH_NESTED_NAME_LINK, productName);
        titleLineContainer = Selenide.$x(titleContainerPath);
        priceLabel = Selenide.$x(titleContainerPath +
                "/following-sibling::div[contains(@class, 'product-card__price-block-container')]" +
                "//span[@class = 'price__main-value']");
        String followingSiblingProductCheckoutButtonsContainer = "/following-sibling::mvid-plp-product-checkout";
        addToCartButton = Selenide.$x(titleContainerPath +
                followingSiblingProductCheckoutButtonsContainer + "//button[span[text() = 'В корзину']]");
        addToFavoritesButton = Selenide.$x(titleContainerPath +
                followingSiblingProductCheckoutButtonsContainer + "//div[contains(@class, 'wishlist-button-block')]//button");
        addToComparisonButton = Selenide.$x(titleContainerPath +
                followingSiblingProductCheckoutButtonsContainer + "//div[contains(@class, 'compare-button-block')]//button");
    }

    public GoodPage() {
    }

    FiltersPage filtersPage = new FiltersPage();

    static GoodPage getProductCard(String productName) {
        return new GoodPage(productName);
    }

    public SelenideElement getProductTitle() {
        return titleLineContainer;
    }

    public SelenideElement getPriceLabel() {
        return priceLabel;
    }

    public SelenideElement getAddToCartButton() {
        return addToCartButton;
    }

    public SelenideElement getAddToComparisonButton() {
        return addToComparisonButton;
    }

    public SelenideElement getAddToFavoritesButton() {
        return addToFavoritesButton;
    }

    /**
     * Метод, который кликает на слудеющую страницу.
     */
    public void clickToNextPage() {
        if (paginationLinks.last().exists()) {
            paginationLinks
                    .last()
                    .scrollIntoView("{block: \"center\"}")
                    .shouldBe(Condition.visible)
                    .click();
            filtersPage.getLoader().shouldBe(Condition.disappear);
        }
    }

    /**
     * Метод для прогрузки товаров на странице.
     */
    public void showProducts(){
        productContainers
                .asDynamicIterable()
                .forEach(ele -> ele.scrollIntoView("{block: \"center\"}"));
    }

    /**
     * Метод для поиска товара на всех страницах.
     * @param productName название товара.
     */
    public void findProductCard(String productName) {
        do{
        showProducts();
        if (getProductCard(productName).getProductTitle().isDisplayed()) {
            getProductCard(productName)
                    .getProductTitle()
                    .shouldBe(Condition.visible)
                    .scrollIntoView("{block: \"center\"}");
            break;
        } else {clickToNextPage();}}
        while (paginationLinks.last().exists());
    }
}



