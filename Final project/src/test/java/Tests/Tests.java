package Tests;

import Steps.*;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.*;
import static Tools.UrlChecker.urlChecker;

public class Tests {
    StepsHeaderPage stepsHeaderPage;
    StepsDaysGoodPage stepsDaysGoodPage;
    StepsLocationPage stepsLocationPage;
    StepsAuthorizationPage stepsAuthorizationPage;
    StepsCartPage stepsCartPage;
    StepsMostViewedGoods stepsMostViewedGoods;
    StepsGoodsPage stepsGoodsPage;
    StepsFavoritePage stepsFavoritePage;
    StepsComparePage stepsComparePage;
    StepsFiltersPage stepsFiltersPage;

    @BeforeMethod
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
        stepsFavoritePage = new StepsFavoritePage();
        stepsComparePage = new StepsComparePage();
        stepsFiltersPage = new StepsFiltersPage();
    }

    @AfterMethod
    public void settingsAfterTests(){
        Selenide.clearBrowserCookies();
        Selenide.closeWebDriver();
    }

    @Test (testName = "Проверка кнопок шапки mvideo")
    public void checkMvideoHeader(){
        stepsHeaderPage.ordersStatusButtonIsDisplayedAndIsEnabled();
        stepsHeaderPage.profileButtonIsDisplayedAndIsEnabled();
        stepsHeaderPage.cartButtonIsDisplayedAndIsDisabled();
        stepsHeaderPage.compareButtonIsDisplayedAndIsDisabled();
        stepsHeaderPage.favoritesButtonIsDisplayedAndIsDisabled();
    }

    @Test (testName = "Проверка активации кнопки корзины")
    public void checkCartButtonIsActive(){
        stepsDaysGoodPage.daysGoodIsDisplayed();
        stepsDaysGoodPage.daysGoodAddToCart();
        stepsHeaderPage.cartButtonIsEnabledAndContainsGood(1);
    }

    @Test (testName = "Проверка перехода в корзину и правильность ее отображения")
    public void checkCartThatContainsGood(){
        stepsDaysGoodPage.daysGoodIsDisplayed();
        stepsDaysGoodPage.daysGoodAddToCart();
        stepsHeaderPage.clickOnCartButton();
        stepsCartPage.headerMyCartIsDisplayed();
        urlChecker("/cart");
        stepsCartPage.checkAddedGoodAndExistedGood(stepsDaysGoodPage.addedGoods());
        stepsCartPage.continueButtonIsDisplayed();
        stepsCartPage.headerThatContainsQuantityGoodsIsDisplayed();
        stepsCartPage.realSummOfGoodsEqualsToExpectedSumm();
    }

    @Test (testName = "Проверка добавления 2 товаров в корзину и правильность ее отображения")
    public void addTwoGoodsFromMostViewedGoodsToCart(){
        stepsMostViewedGoods.mostViewedGoodsContainerIsDisplayed();
        stepsMostViewedGoods.addGoodToCart(1);
        stepsMostViewedGoods.addGoodToCart(2);
        stepsHeaderPage.clickOnCartButton();
        stepsCartPage.checkAddedGoodAndExistedGood(stepsMostViewedGoods.addedGoods());
        stepsCartPage.realSummOfGoodsEqualsToExpectedSumm();
    }

    @Test (testName = "Проверка поиска товаров")
    public void findGoods(){
        stepsHeaderPage.inputTextInInputFieldIsDisplayed();
        stepsHeaderPage.inputTextInInputField("apple");
        stepsHeaderPage.clickOnSearchButton();
        stepsGoodsPage.loadGoodsPage();
        urlChecker("/product-list-page");
        stepsGoodsPage.goodsContainName("apple");
    }

    @Test (testName = "Проверка сортировки товаров")
    public void sortGoodsOnList(){
        stepsHeaderPage.inputTextInInputFieldIsDisplayed();
        stepsHeaderPage.inputTextInInputField("apple");
        stepsHeaderPage.clickOnSearchButton();
        stepsGoodsPage.loadGoodsPage();
        urlChecker("/product-list-page");
        stepsGoodsPage.goodsContainName("apple");
        stepsFiltersPage.filterMostFavoriteIsDisplayed();
        stepsFiltersPage.clickOnFirstIsMoreExpensiveButton();
        stepsGoodsPage.loadGoodsPage();
        stepsGoodsPage.goodsPriceDecreases();
    }

    @Test (testName = "Проверка модального окна авторизации клиента")
    public void checkAuthorizationWindow(){
        stepsHeaderPage.clickOnProfileButton();
        stepsAuthorizationPage.modalWindowIsDisplayed();
        stepsAuthorizationPage.closeButtonIsDisplayed();
        stepsAuthorizationPage.phoneInputFieldIsDisplayed();
        stepsAuthorizationPage.сontinueButtonIsDisplayedAndIsDisabled();
        stepsAuthorizationPage.buttonForLawPersonsIsDisplayed();
    }

    @Test (testName = "Проверка добавления товаров в список сравнения")
    public void checkAddedGoodToCompareList(){
        stepsHeaderPage.inputTextInInputFieldIsDisplayed();
        stepsHeaderPage.inputTextInInputField("apple");
        stepsHeaderPage.clickOnSearchButton();
        stepsGoodsPage.loadGoodsPage();
        urlChecker("/product-list-page");
        stepsGoodsPage.addGoodToCompare(1);
        stepsGoodsPage.addGoodToCompare(2);
        stepsGoodsPage.addGoodToCompare(3);
        stepsHeaderPage.clickOnСompareButton();
        urlChecker("/product-comparison");
        stepsComparePage.checkAddedGoodAndExistedGood(stepsGoodsPage.addedGoodsToCompare());
    }

    @Test (testName = "Проверка добавления товаров в список избранных")
    public void checkAddedGoodToFavoriteList(){
        stepsHeaderPage.inputTextInInputFieldIsDisplayed();
        stepsHeaderPage.inputTextInInputField("apple");
        stepsHeaderPage.clickOnSearchButton();
        stepsGoodsPage.loadGoodsPage();
        urlChecker("/product-list-page");
        stepsGoodsPage.addGoodToFavorite(1);
        stepsGoodsPage.addGoodToFavorite(2);
        stepsGoodsPage.addGoodToFavorite(3);
        stepsHeaderPage.clickOnFavoriteButton();
        urlChecker("/wish-list");
        stepsFavoritePage.checkAddedGoodAndExistedGood(stepsGoodsPage.addedGoodsToFavorite());
    }

    @Test (testName = "Проверка изменения города")
    public void checkChangeCity(){
       stepsHeaderPage.clickOnLocationButton();
       stepsLocationPage.modalWindowIsDisplayed();
       stepsLocationPage.selectCity("Краснодар");
       stepsLocationPage.modalWindowIsntDisplayed();
       stepsHeaderPage.checkSelectedCity("Краснодар");
    }
}
