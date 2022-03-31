package Steps;

import Pages.AuthorizationPage;
import Pages.DaysGoodPage;
import io.qameta.allure.Step;
import org.testng.Assert;

public class StepsAuthorizationPage {

    private AuthorizationPage authorizationPage;
    public StepsAuthorizationPage() {
        authorizationPage = AuthorizationPage.getAuthorizationPage();
    }

    /**
     * Отображение кнопки закрытия модального окна.
     */
    public void closeButtonIsDisplayed(){
        Assert.assertTrue(authorizationPage.closeButtonIsDisplayed(),
                "Кнопка закрытия модального окна не отображается.");
    }

    /**
     * Модального окно и заголовок "Вход или регистрация" отображается.
     */
    public void modalWindowIsDisplayed(){
        Assert.assertTrue(authorizationPage.modalWindowIsDisplayed() && authorizationPage.headerIsDisplayed(),
                "Модального окно или заголовок \"Вход или регистрация\" не отображается.");
    }

    /**
     * Отображение input field с плейсхолдером “Телефон”.
     */
    public void phoneInputFieldIsDisplayed(){
        Assert.assertTrue(authorizationPage.phoneInputFieldIsDisplayed(),
                "Input field с плейсхолдером \"Телефон\" не отображается.");
    }

    /**
     * Кнопка продолжить отображается и неактивна.
     */
    public void сontinueButtonIsDisplayedAndIsDisabled(){
        Assert.assertTrue(authorizationPage.сontinueButtonIsDisplayed() && authorizationPage.сontinueButtonIsDisabled(),
                "Кнопка продолжить не отображается или активна.");
    }

    /**
     * Отображение кнопки "Для юридических лиц".
     */
    public void buttonForLawPersonsIsDisplayed(){
        Assert.assertTrue(authorizationPage.buttonForLawPersonsIsDisplayed(),
                "Кнопка \"Для юридических лиц\" не отображается.");
    }
}
