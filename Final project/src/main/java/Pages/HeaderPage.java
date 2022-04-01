package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import java.util.Objects;
import static Tools.XpathFormatter.formatXpath;

public class HeaderPage {
    private static HeaderPage headerPage;

    /**
     * Xpath для нахождения неактивного элемента.
     */
    private String xpathDisabledStatus = "//mvid-header-icon[contains(@class, 'disabled')]";
    /**
     * Xpath для нахождения активного элемента.
     */
    private String xpathEnabledStatus = "//mvid-header-icon[not(contains(@class, 'disabled'))]";
    /**
     * Xpath для нахождения кнопки "Статус заказа".
     */
    private String xpathOrdersStatusButton = "//mvid-header-icon[contains(@class, 'tab-status-order')]";
    /**
     * Xpath для нахождения поля ввода для поиска.
     */
    private String xpathInputField = "//input[contains(@class, 'input__field')]";
    /**
     * Xpath для нахождения кнопки поиска.
     */
    private String xpathSearchButton = "//mvid-icon[contains(@class, 'search-icon')]";
    /**
     * Xpath для нахождения текущего выбранного города.
     */
    private String xpathSelectedCity = "//span[text() = '%s']";
    /**
     * Xpath для нахождения bubble, который содержит определенное кол-во товаров
     */
    private String xpathBubbleCartWithSomeGoods = "//mvid-bubble[contains(.,%d)]";

    @FindBy (xpath = "//div[contains(@class, 'tab-profile')]")
    private SelenideElement profileButton;
    @FindBy (xpath = "//div[contains(@class, 'tab-compare')]")
    private SelenideElement compareButton;
    @FindBy (xpath = "//div[contains(@class, 'tab-personal')]")
    private SelenideElement favoritesButton;
    @FindBy (xpath = "//div[contains(@class, 'tab-cart')]")
    private SelenideElement cartButton;
    @FindBy (xpath = "//div[contains(@class, 'input__container')]")
    private SelenideElement inputContainer;
    @FindBy (xpath = "//div[contains(@class, 'app-header-top')]" +
            "//child::div[contains(@class, 'location')]")
    private SelenideElement locationButton;
    @FindBy (xpath = "//div[contains(@class, 'tooltip__item')]")
    private SelenideElement popupWindow;
    /**
     * Локатор на главный header. Используется для отвода курсора.
     */
    @FindBy (xpath = "//div[contains(@class,'app-header-top')]")
    private SelenideElement headerPlug;

    private SelenideElement getCompareButton() {
        return compareButton;
    }
    private SelenideElement getFavoritesButton() {
        return favoritesButton;
    }
    private SelenideElement getProfileButton() {
        return profileButton;
    }
    private SelenideElement getCartButton() {
        return cartButton;
    }
    private SelenideElement getInputContainer() {
        return inputContainer;
    }
    private SelenideElement getLocationButton() {
        return locationButton;
    }
    private SelenideElement getPopupWindow() {
        return popupWindow;
    }
    /**
     * Возвращает элемент header. Используется для отвода курсора.
     * @return элемент header
     */
    public SelenideElement getHeaderPlug() {
        return headerPlug;
    }

    private HeaderPage() {}
    public static HeaderPage getHeaderPage() {
        if (Objects.isNull(headerPage)) headerPage = Selenide.page(new HeaderPage());
        return headerPage;
    }

    /**
     * Отображение кнопки "Статус заказа".
     * @return true - отображается, false - не отображается.
     */
    public boolean ordersStatusButtonIsDisplayed() {
        return Selenide
                .$x(xpathOrdersStatusButton)
                .isDisplayed();
    }

    /**
     * Кнопка "Статус заказа" активна.
     * @return true - активна, false - не активна.
     */
    public boolean ordersStatusButtonIsEnabled() {
        return Selenide
                .$x(xpathOrdersStatusButton + "[not(contains(@class, 'disabled'))]")
                .isDisplayed();
    }

    /**
     * Отображение кнопки "Войти".
     * @return true - отображается, false - не отображается.
     */
    public boolean profileButtonIsDisplayed() {
        return getProfileButton().isDisplayed();
    }

    /**
     * Кнопка "Войти" активна.
     * @return true - активна, false - не активна.
     */
    public boolean profileButtonIsEnabled() {
        return getProfileButton()
                .find(By.xpath("." + xpathEnabledStatus))
                .isDisplayed();
    }

    /**
     * Нажатие на кнопку "Войти".
     */
    public void clickOnProfileButton(){
        getProfileButton().click();
    }

    /**
     * Отображение кнопки "Сравнение".
     * @return true - отображается, false - не отображается.
     */
    public boolean compareButtonIsDisplayed() {
        return getCompareButton().isDisplayed();
    }

    /**
     * Кнопка "Сравнение" неактивна.
     * @return true - неактивна, false - активна.
     */
    public boolean compareButtonIsDisabled() {
        return getCompareButton()
                .find(By.xpath("." + xpathDisabledStatus))
                .isDisplayed();
    }

    /**
     * Кнопка "Сравнение" активна.
     * @return true - активна, false - не активна.
     */
    public boolean compareButtonIsEnabled() {
        return getCompareButton()
                .find(By.xpath("." + xpathEnabledStatus))
                .isDisplayed();
    }

    /**
     * Нажатие на кнопку "Сравнение".
     */
    public void clickOnСompareButton(){
        getCompareButton().click();
    }

    /**
     * Отображение кнопки "Избранное".
     * @return true - отображается, false - не отображается.
     */
    public boolean favoritesButtonIsDisplayed() {
        return getFavoritesButton().isDisplayed();
    }

    /**
     * Кнопка "Избранное" неактивна.
     * @return true - неактивна, false - активна.
     */
    public boolean favoritesButtonIsDisabled() {
        return getFavoritesButton()
                .find(By.xpath("." + xpathDisabledStatus))
                .isDisplayed();
    }

    /**
     * Кнопка "Избранное" активна.
     * @return true - активна, false - не активна.
     */
    public boolean favoritesButtonIsEnabled() {
        return getFavoritesButton()
                .find(By.xpath("." + xpathEnabledStatus))
                .isDisplayed();
    }

    /**
     * Нажатие на кнопку "Ибранное".
     */
    public void clickOnFavoritesButton(){
        getFavoritesButton().click();
    }

    /**
     * Отображение кнопки "Корзина".
     * @return true - отображается, false - не отображается.
     */
    public boolean cartButtonIsDisplayed() {
        return getCartButton().isDisplayed();
    }

    /**
     * Кнопка "Корзина" неактивна.
     * @return true - неактивна, false - активна.
     */
    public boolean cartButtonIsDisabled() {
        return getCartButton()
                .find(By.xpath("." + xpathDisabledStatus))
                .isDisplayed();
    }

    /**
     * Кнопка "Корзина" активна.
     * @return true - активна, false - неактивна.
     */
    public boolean cartButtonIsEnabled() {
        return getCartButton()
                .find(By.xpath("." + xpathEnabledStatus))
                .shouldBe(Condition.visible)
                .isDisplayed();
    }

    /**
     * Корзина содержит определенное кол-во товара.
     * @param quantity ожидаемое кол-во товара.
     * @return true - кол-во совпадает, false - кол-во не совпадает.
     */
    public boolean checkToCartContainsOneGood(Integer quantity){
        return getCartButton()
                .find(By.xpath(formatXpath(".", xpathBubbleCartWithSomeGoods, quantity)))
                .shouldBe(Condition.visible)
                .isDisplayed();
    }

    /**
     * Нажатие на кнопку "Корзина".
     */
    public void clickOnCartButton(){
        getCartButton().click();
    }

    /**
     * Ввод текста в строку поиска.
     * @param text вводимый текст.
     */
    public void inputTextInInputField(String text) {
        getInputContainer()
                .find(By.xpath("." + xpathInputField))
                .shouldBe(Condition.visible)
                .setValue(text);
    }

    /**
     * Cтрока поиска отображается.
     * @return true - отображается, false - не отображается.
     */
    public boolean inputTextInInputFieldIsDisplayed() {
        return getInputContainer()
                .find(By.xpath("." + xpathInputField))
                .isDisplayed();
    }

    /**
     * Нажатие на кнопку поиска.
     */
    public void clickOnSearchButton() {
        getInputContainer()
                .find(By.xpath("." + xpathSearchButton))
                .shouldBe(Condition.visible)
                .click();
    }

    /**
     * Отображение кнопки выбора города.
     * @return
     */
    public boolean locationButtonIsDisplayed() {
        return getLocationButton()
                .shouldBe(Condition.visible)
                .isDisplayed();
    }

    /**
     * Нажатие на кнопку выбора города.
     */
    public void clickOnLocationButton(){
        getLocationButton()
                .shouldBe(Condition.visible)
                .click();
    }

    /**
     * Проверка выбранного города.
     * @param city ожидаемый город.
     * @return true - города совпадают, false - города не совпадают.
     */
    public boolean checkSelectedCity(String city){
        return getLocationButton()
                .find(By.xpath(formatXpath(".", xpathSelectedCity, city)))
                .isDisplayed();
    }

    /**
     * Ожидание сокрытия окна с товарами при добавлении в избранное, сравнение, корзину.
     */
    public void popupWindowShouldBeHidden(){
        getPopupWindow().shouldBe(Condition.hidden);
    }
}
