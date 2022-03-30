package Steps;

import Pages.HeaderPage;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.commands.As;
import org.testng.Assert;
import org.testng.annotations.*;

public class StepsHeaderPage {

    private HeaderPage headerPage;
    public StepsHeaderPage() {
        headerPage = HeaderPage.getHeaderPage();
    }

    /**
     * Кнопка "Статус заказа" отображается и активна.
     */
    public void ordersStatusButtonIsDisplayedAndIsEnabled() {
        Assert.assertTrue(headerPage.ordersStatusButtonIsDisplayed() && headerPage.ordersStatusButtonIsEnabled(),
                "Кнопка \"Статус заказа\" не отображается или неактивна.");
    }

    /**
     * Кнопка "Войти" отображается и активна.
     */
    public void profileButtonIsDisplayedAndIsEnabled() {
        Assert.assertTrue(headerPage.profileButtonIsDisplayed() && headerPage.profileButtonIsEnabled(),
                "Кнопка \"Войти\" не отображается или неактивна.");
    }

    /**
     * Кнопка "Сравнение" отображается и неактивна.
     */
    public void compareButtonIsDisplayedAndIsDisabled() {
        Assert.assertTrue(headerPage.compareButtonIsDisplayed() && headerPage.compareButtonIsDisabled(),
                "Кнопка \"Сравнение\" не отображается или активна.");
    }

    /**
     * Кнопка "Избранное" отображается и неактивна.
     */
    public void favoritesButtonIsDisplayedAndIsDisabled() {
        Assert.assertTrue(headerPage.favoritesButtonIsDisplayed() && headerPage.favoritesButtonIsDisabled(),
                "Кнопка \"Избранное\" не отображается или активна.");
    }

    /**
     * Кнопка "Корзина" отображается и неактивна.
     */
    public void cartButtonIsDisplayedAndIsDisabled() {
        Assert.assertTrue(headerPage.cartButtonIsDisplayed() && headerPage.cartButtonIsDisabled(),
                "Кнопка \"Корзина\" не отображается или активна.");
    }

    /**
     * Кнопка "Корзина" активна и содержит определенное кол-во товара.
     * @param quantity ожидаемое кол-во товара.
     */
    public void cartButtonIsEnabledAndContainsGood(Integer quantity){
        Assert.assertTrue(headerPage.cartButtonIsEnabled() && headerPage.checkToCartContainsOneGood(quantity),
                "Кнопка \"Корзина\" не отображается или не содержит товара");
    }

    /**
     * Нажатие на кнопку выбора города.
     */
    public void clickOnLocationButton(){
        if (headerPage.locationButtonIsDisplayed() == true)
        headerPage.clickOnLocationButton();
        else System.out.println("Кнопка с выбором города не отображается.");
    }

    /**
     * Проверка совпадает ли ожидаемый город с текущим.
     * @param city ожидаемый город.
     */
    public void checkSelectedCity(String city){
        Selenide.Wait();
        Assert.assertTrue(headerPage.checkSelectedCity(city),
                "Выбранный город не совпадает с ожидаемым.");
    }

    /**
     * Нажатие на кнопку "Войти"
     */
    public void clickOnProfileButton(){
        if (headerPage.profileButtonIsDisplayed() && headerPage.profileButtonIsEnabled() == true)
            headerPage.clickOnProfileButton();
        else System.out.println("Кнопка \"Войти\" не отображается или неактивна.");
    }

    /**
     * Нажатие на кнопку "Корзина"
     */
    public void clickOnCartButton(){
        if (headerPage.cartButtonIsDisplayed() && headerPage.cartButtonIsEnabled() == true)
            headerPage.clickOnCartButton();
        else System.out.println("Кнопка \"Корзина\" не отображается или неактивна.");
    }

}
