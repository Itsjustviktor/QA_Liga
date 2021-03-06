package Steps;

import Pages.HeaderPage;
import io.qameta.allure.Step;
import org.testng.Assert;

public class StepsHeaderPage {

    private HeaderPage headerPage;
    public StepsHeaderPage() {
        headerPage = HeaderPage.getHeaderPage();
    }

    /**
     * Кнопка "Статус заказа" отображается и активна.
     */
    @Step("Кнопка \"Статус заказа\" отображается и активна")
    public void ordersStatusButtonIsDisplayedAndIsEnabled() {
        Assert.assertTrue(headerPage.ordersStatusButtonIsDisplayed() && headerPage.ordersStatusButtonIsEnabled(),
                "Кнопка \"Статус заказа\" не отображается или неактивна.");
    }

    /**
     * Кнопка "Войти" отображается и активна.
     */
    @Step("Кнопка \"Войти\" отображается и активна")
    public void profileButtonIsDisplayedAndIsEnabled() {
        Assert.assertTrue(headerPage.profileButtonIsDisplayed() && headerPage.profileButtonIsEnabled(),
                "Кнопка \"Войти\" не отображается или неактивна.");
    }

    /**
     * Кнопка "Сравнение" отображается и неактивна.
     */
    @Step("Кнопка \"Сравнение\" отображается и неактивна")
    public void compareButtonIsDisplayedAndIsDisabled() {
        Assert.assertTrue(headerPage.compareButtonIsDisplayed() && headerPage.compareButtonIsDisabled(),
                "Кнопка \"Сравнение\" не отображается или активна.");
    }

    /**
     * Нажатие на кнопку "Сравнение".
     */
    @Step("Нажатие на кнопку \"Сравнение\"")
    public void clickOnСompareButton(){
        if (headerPage.compareButtonIsDisplayed() && headerPage.compareButtonIsEnabled())
            headerPage.clickOnСompareButton();
        else
            Assert.assertTrue(headerPage.compareButtonIsDisplayed() && headerPage.compareButtonIsEnabled(),
                "Кнопка \"Сравнение\" не отображается или неактивна.");
    }

    /**
     * Кнопка "Избранное" отображается и неактивна.
     */
    @Step("Кнопка \"Избранное\" отображается и неактивна")
    public void favoritesButtonIsDisplayedAndIsDisabled() {
        Assert.assertTrue(headerPage.favoritesButtonIsDisplayed() && headerPage.favoritesButtonIsDisabled(),
                "Кнопка \"Избранное\" не отображается или активна.");
    }

    /**
     * Нажатие на кнопку "Избранное".
     */
    @Step("Нажатие на кнопку \"Избранное\"")
    public void clickOnFavoriteButton(){
        if (headerPage.favoritesButtonIsDisplayed() && headerPage.favoritesButtonIsEnabled())
            headerPage.clickOnFavoritesButton();
        else Assert.assertTrue(headerPage.favoritesButtonIsDisplayed() && headerPage.favoritesButtonIsEnabled(),
                "Кнопка \"Избранное\" не отображается или неактивна.");
    }

    /**
     * Кнопка "Корзина" отображается и неактивна.
     */
    @Step("Кнопка \"Корзина\" отображается и неактивна")
    public void cartButtonIsDisplayedAndIsDisabled() {
        Assert.assertTrue(headerPage.cartButtonIsDisplayed() && headerPage.cartButtonIsDisabled(),
                "Кнопка \"Корзина\" не отображается или активна.");
    }

    /**
     * Кнопка "Корзина" активна и содержит определенное кол-во товара.
     * @param quantity ожидаемое кол-во товара.
     */
    @Step ("Кнопка \"Корзина\" активна и содержит кол-во товара: \"{quantity}\".")
    public void cartButtonIsEnabledAndContainsGood(Integer quantity){
        Assert.assertTrue(headerPage.cartButtonIsEnabled() && headerPage.checkToCartContainsSomeGood(quantity),
                "Кнопка \"Корзина\" не отображается или не содержит товара");
    }

    /**
     * Нажатие на кнопку "Корзина".
     */
    @Step("Нажатие на кнопку \"Корзина\"")
    public void clickOnCartButton(){
        if (headerPage.cartButtonIsDisplayed() && headerPage.cartButtonIsEnabled())
            headerPage.clickOnCartButton();
        else Assert.assertTrue(headerPage.cartButtonIsDisplayed() && headerPage.cartButtonIsEnabled(),
                "Кнопка \"Корзина\" не отображается или неактивна.");
    }

    /**
     * Нажатие на кнопку выбора города.
     */
    @Step("Нажатие на кнопку выбора города")
    public void clickOnLocationButton(){
        if (headerPage.locationButtonIsDisplayed())
        headerPage.clickOnLocationButton();
        else Assert.assertTrue(headerPage.locationButtonIsDisplayed(),
                "Кнопка с выбором города не отображается.");
    }

    /**
     * Проверка совпадает ли ожидаемый город с текущим.
     * @param city ожидаемый город.
     */
    @Step("Проверка совпадает ли ожидаемый город \"{city}\" с текущим")
    public void checkSelectedCity(String city){
        Assert.assertTrue(headerPage.checkSelectedCity(city),
                "Выбранный город не совпадает с ожидаемым.");
    }

    /**
     * Нажатие на кнопку "Войти".
     */
    @Step("Нажатие на кнопку \"Войти\"")
    public void clickOnProfileButton(){
        if (headerPage.profileButtonIsDisplayed() && headerPage.profileButtonIsEnabled())
            headerPage.clickOnProfileButton();
        else Assert.assertTrue(headerPage.profileButtonIsDisplayed() && headerPage.profileButtonIsEnabled(),
                "Кнопка \"Войти\" не отображается или неактивна.");
    }

    /**
     * Вставка названия товара в окно поиска.
     * @param goodsName название товара.
     */
    @Step("Вставка названия товара: \"{goodsName}\" в окно поиска")
    public void inputTextInInputField(String goodsName){
        headerPage.inputTextInInputField(goodsName);
    }

    /**
     * Нажатие на кнопку поиска.
     */
    @Step("Нажатие на кнопку поиска")
    public void clickOnSearchButton(){
        headerPage.clickOnSearchButton();
    }

    /**
     * Поле ввода названия товара активно.
     */
    @Step("Поле ввода названия товара активно")
    public void inputTextInInputFieldIsDisplayed(){
        Assert.assertTrue(headerPage.inputTextInInputFieldIsDisplayed(),
                "Строка поиска товаров не отображается.");
    }

}
