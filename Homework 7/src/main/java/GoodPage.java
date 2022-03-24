import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$$x;

public class GoodPage {

    private SelenideElement titleLineContainer;
    private SelenideElement priceLabel;
    private SelenideElement addToCartButton;
    private SelenideElement addToFavoritesButton;
    private SelenideElement addToComparisonButton;

    private ElementsCollection paginationLinks = $$x("//li[contains(@class, 'page-item')]");

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

    public void findProductCard(String filterName) {
        while (paginationLinks.last().exists()) {
        if (getProductCard(filterName).getProductTitle().isDisplayed()) {
            getProductCard(filterName)
                    .getProductTitle()
                    .shouldBe(Condition.visible)
                    .scrollIntoView("{block: \"center\"}");
            break;
        } else {clickToNextPage();}}
    }
}



