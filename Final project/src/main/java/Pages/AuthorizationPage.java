package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;

public class AuthorizationPage {
    private static AuthorizationPage authorizationPage;

    /**
     * Xpath кнопки закрытия модального окна.
     */
    private String xpathCloseButton = "//mvid-icon[contains(@type, 'close') and contains(@class, 'login-form-top-nav__btn-icon')]";
    /**
     * Xpath для поиска заголовка "Вход или регистрация".
     */
    private String xpathHeader = "//h2[contains(.,'Вход или регистрация')]";
    /**
     * Xpath place holder "Телефон".
     */
    private String xpathPhonesPlaceHolder = "//span[contains(.,'Телефон')]";
    /**
     * Xpath активной кнопки "Продолжить"
     */
    private String xpathContinueButtonEnabledStatus = "//button[not(contains(@disabled, 'true'))]";

    @FindBy(xpath = "//div[contains(@class, 'modal-layout')]")
    private SelenideElement modalWindow;
    @FindBy (xpath = "//div[contains(@class,'form-field__input-container')]")
    private SelenideElement phoneInputField;
    @FindBy (xpath = "//div[contains(@class,'login-form__column_button')]")
    private SelenideElement continueButton;
    @FindBy (xpath = "//button[contains(@class, 'login-form__link')][contains(.,'Для юридических лиц')]")
    private SelenideElement buttonForLawPersons;

    private SelenideElement getModalWindow() {
        return modalWindow;
    }
    private SelenideElement getPhoneInputField() {
        return phoneInputField;
    }
    private SelenideElement getContinueButton() {
        return continueButton;
    }
    private SelenideElement getButtonForLawPersons() {
        return buttonForLawPersons;
    }

    private AuthorizationPage() {}
    public static AuthorizationPage getAuthorizationPage() {
        if (Objects.isNull(authorizationPage)) authorizationPage = Selenide.page(new AuthorizationPage());
        return authorizationPage;
    }

    /**
     * Проверка видимости модального окна входа или регистрации.
     * @return true - окно видимо, false - невидимо.
     */
    public boolean modalWindowIsDisplayed(){
        return getModalWindow()
                .find(By.xpath("." + xpathCloseButton))
                .shouldBe(Condition.visible)
                .isDisplayed();
    }

    /**
     * Проверка видимости кнопки закрытия модального окна.
     * @return true - отображается, false - не отображается.
     */
    public boolean closeButtonIsDisplayed(){
        return getModalWindow()
                .find(By.xpath("." + xpathCloseButton))
                .shouldBe(Condition.visible)
                .isDisplayed();
    }

    /**
     * Проверка видимости заголовка.
     * @return true - заголовок видим, false - невидим.
     */
    public boolean headerIsDisplayed(){
        return getModalWindow()
                .find(By.xpath("." + xpathHeader))
                .shouldBe(Condition.visible)
                .isDisplayed();
    }

    /**
     * Проверка видимости place holder "Телефон".
     * @return true - заголовок видим, false - невидим.
     */
    public boolean phoneInputFieldIsDisplayed(){
        return getPhoneInputField()
                .find(By.xpath("." + xpathPhonesPlaceHolder))
                .shouldBe(Condition.visible)
                .isDisplayed();
    }

    /**
     * Отображение кнопки "Продолжить".
     * @return true - отображается, false - не отображается.
     */
    public boolean сontinueButtonIsDisplayed() {
        return getContinueButton().isDisplayed();
    }

    /**
     * Кнопка "Продолжить" неактивна.
     * @return true - неактивна, false - активна.
     */
    public boolean сontinueButtonIsDisabled() {
        return !getContinueButton()
                .find(By.xpath("." + xpathContinueButtonEnabledStatus))
                .isDisplayed();
    }

    /**
     * Отображение кнопки "Для юридических лиц".
     * @return true - отображается, false - не отображается.
     */
    public boolean buttonForLawPersonsIsDisplayed(){
        return getButtonForLawPersons()
                .isDisplayed();
    }

}
