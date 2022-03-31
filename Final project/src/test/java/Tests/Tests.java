package Tests;

import Steps.*;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Configuration;
import org.testng.Assert;
import org.testng.annotations.*;

public class Tests {
    StepsHeaderPage stepsHeaderPage;
    StepsDaysGoodPage stepsDaysGoodPage;
    StepsLocationPage stepsLocationPage;
    StepsAuthorizationPage stepsAuthorizationPage;
    StepsCartPage stepsCartPage;
    StepsMostViewedGoods stepsMostViewedGoods;
    StepsGoodsPage stepsGoodsPage;

    @BeforeTest
    public void settingsBeforeTests(){
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 90000;
        Selenide.open("https://www.mvideo.ru/");
        stepsHeaderPage = new StepsHeaderPage();
        stepsDaysGoodPage = new StepsDaysGoodPage();
        stepsLocationPage = new StepsLocationPage();
        stepsAuthorizationPage = new StepsAuthorizationPage();
        stepsCartPage = new StepsCartPage();
        stepsMostViewedGoods = new StepsMostViewedGoods();
        stepsGoodsPage = new StepsGoodsPage();
    }

    @AfterClass
    public void settingsAfterTests(){
        Selenide.clearBrowserCookies();
        Selenide.closeWebDriver();
    }

    @Test (priority = 1)
    public void checkMvideoHeader(){
        stepsHeaderPage.ordersStatusButtonIsDisplayedAndIsEnabled();
        stepsHeaderPage.profileButtonIsDisplayedAndIsEnabled();
        stepsHeaderPage.cartButtonIsDisplayedAndIsDisabled();
        stepsHeaderPage.compareButtonIsDisplayedAndIsDisabled();
        stepsHeaderPage.favoritesButtonIsDisplayedAndIsDisabled();
    }

    @Test (priority = 2)
    public void checkCartButtonIsActive(){
        stepsDaysGoodPage.daysGoodIsDisplayed();
        stepsDaysGoodPage.daysGoodAddToCart();
        stepsHeaderPage.cartButtonIsEnabledAndContainsGood(1);
    }

    @Test (priority = 3)
    public void checkCartThatContainsGood(){
        stepsDaysGoodPage.daysGoodIsDisplayed();
        stepsDaysGoodPage.daysGoodAddToCart();
        stepsHeaderPage.clickOnCartButton();
        stepsCartPage.checkCurrentPage("/cart");
        stepsCartPage.headerMyCartIsDisplayed();
        stepsCartPage.checkAddedGoodAndExistedGood(stepsDaysGoodPage.addedGoods());
        stepsCartPage.continueButtonIsDisplayed();
        stepsCartPage.headerThatContainsQuantityGoodsIsDisplayed();
        stepsCartPage.realSummOfGoodsEqualsToExpectedSumm();
    }

    @Test (priority = 4)
    public void addTwoGoodsFromMostViewedGoodsToCart(){
        stepsMostViewedGoods.mostViewedGoodsContainerIsDisplayed();
        stepsMostViewedGoods.addGoodToCart("Смартфон Xiaomi Redmi Note 11 NFC 4GB+128GB Twilight Blue");
        stepsMostViewedGoods.addGoodToCart("Планшет HUAWEI MatePad T10 (2021) 2+32GB Wi-Fi Blue (AGRK-W09)");
        stepsHeaderPage.clickOnCartButton();
        stepsCartPage.checkAddedGoodAndExistedGood(stepsMostViewedGoods.addedGoods());
        stepsCartPage.realSummOfGoodsEqualsToExpectedSumm();
    }

    @Test (priority = 7)
    public void checkAuthorizationWindow(){
        stepsHeaderPage.clickOnProfileButton();
        stepsAuthorizationPage.modalWindowIsDisplayed();
        stepsAuthorizationPage.closeButtonIsDisplayed();
        stepsAuthorizationPage.phoneInputFieldIsDisplayed();
        stepsAuthorizationPage.сontinueButtonIsDisplayedAndIsDisabled();
        stepsAuthorizationPage.buttonForLawPersonsIsDisplayed();
    }

    @Test (priority = 8)
    public void checkAddedGoodToCompareList(){
        stepsHeaderPage.inputTextInInputFieldIsDisplayed();
        stepsHeaderPage.inputTextInInputField("apple");
        stepsHeaderPage.clickOnSearchButton();
        stepsGoodsPage.addGoodToFavorite(0);
        //stepsGoodsPage.addGoodToFavorite(1);
        stepsGoodsPage.addGoodToCompare(1);
//        stepsGoodsPage.addGoodToCompare("Смартфон Apple iPhone 11 64GB Black (MHDA3RU/A)");
       // stepsHeaderPage.clickOnСompareButton();
//        stepsGoodsPage.addGoodToCompare("Смартфон Apple iPhone 11 64GB White (MHDC3RU/A)");


    }

    @Test (priority = 10)
    public void checkChangeCity(){
       stepsHeaderPage.clickOnLocationButton();
       stepsLocationPage.modalWindowIsDisplayed();
       stepsLocationPage.selectCity("Краснодар");
       stepsLocationPage.modalWindowIsntDisplayed();
       stepsHeaderPage.checkSelectedCity("Краснодар");
    }
}
