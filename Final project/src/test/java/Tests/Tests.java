package Tests;

import Steps.*;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.*;
import static Formatters.UrlChecker.urlChecker;
import static com.codeborne.selenide.Selenide.switchTo;

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


    @Test (priority = 1, testName = "Проверка кнопок шапки mvideo")
    public void checkMvideoHeader(){
        stepsHeaderPage.ordersStatusButtonIsDisplayedAndIsEnabled();
        stepsHeaderPage.profileButtonIsDisplayedAndIsEnabled();
        stepsHeaderPage.cartButtonIsDisplayedAndIsDisabled();
        stepsHeaderPage.compareButtonIsDisplayedAndIsDisabled();
        stepsHeaderPage.favoritesButtonIsDisplayedAndIsDisabled();
    }

    @Test (priority = 2, testName = "Проверка активации кнопки корзины")
    public void checkCartButtonIsActive(){
        stepsDaysGoodPage.daysGoodIsDisplayed();
        stepsDaysGoodPage.daysGoodAddToCart();
        stepsHeaderPage.cartButtonIsEnabledAndContainsGood(1);
    }

    @Test (priority = 3, testName = "Проверка перехода в корзину и правильность ее отображения")
    public void checkCartThatContainsGood(){
        stepsDaysGoodPage.daysGoodIsDisplayed();
        stepsDaysGoodPage.daysGoodAddToCart();
        stepsHeaderPage.clickOnCartButton();
        urlChecker("/cart");
        stepsCartPage.headerMyCartIsDisplayed();
        stepsCartPage.checkAddedGoodAndExistedGood(stepsDaysGoodPage.addedGoods());
        stepsCartPage.continueButtonIsDisplayed();
        stepsCartPage.headerThatContainsQuantityGoodsIsDisplayed();
        stepsCartPage.realSummOfGoodsEqualsToExpectedSumm();
    }

    @Test (priority = 4, testName = "Проверка добавления 2 товаров в корзину и правильность ее отображения")
    public void addTwoGoodsFromMostViewedGoodsToCart(){
        stepsMostViewedGoods.mostViewedGoodsContainerIsDisplayed();
        stepsMostViewedGoods.addGoodToCart1(15);
        //stepsMostViewedGoods.addGoodToCart1(2);
        stepsHeaderPage.clickOnCartButton();
        stepsCartPage.checkAddedGoodAndExistedGood(stepsMostViewedGoods.addedGoods());
        stepsCartPage.realSummOfGoodsEqualsToExpectedSumm();
    }

    @Test (priority = 5, testName = "Проверка поиска товаров")
    public void findGoods(){
        stepsHeaderPage.inputTextInInputFieldIsDisplayed();
        stepsHeaderPage.inputTextInInputField("apple");
        stepsHeaderPage.clickOnSearchButton();
        urlChecker("/product-list-page");
        stepsGoodsPage.loadGoodsPage();
        stepsGoodsPage.goodsContainName("apple");
    }

    @Test (priority = 6, testName = "Проверка сортировки товаров")
    public void sortGoodsOnList(){
        stepsHeaderPage.inputTextInInputFieldIsDisplayed();
        stepsHeaderPage.inputTextInInputField("apple");
        stepsHeaderPage.clickOnSearchButton();
        urlChecker("/product-list-page");
        stepsGoodsPage.loadGoodsPage();
        stepsGoodsPage.goodsContainName("apple");
        stepsFiltersPage.filterMostFavoriteIsDisplayed();
        stepsFiltersPage.clickOnFirstIsMoreExpensiveButton();
        stepsGoodsPage.loadGoodsPage();
        stepsGoodsPage.goodsPriceDecreases();
    }

    @Test (priority = 7, testName = "Проверка модального окна авторизации клиента")
    public void checkAuthorizationWindow(){
        stepsHeaderPage.clickOnProfileButton();
        stepsAuthorizationPage.modalWindowIsDisplayed();
        stepsAuthorizationPage.closeButtonIsDisplayed();
        stepsAuthorizationPage.phoneInputFieldIsDisplayed();
        stepsAuthorizationPage.сontinueButtonIsDisplayedAndIsDisabled();
        stepsAuthorizationPage.buttonForLawPersonsIsDisplayed();
    }

    @Test (priority = 8, testName = "Проверка добавления товаров в список сравнения")
    public void checkAddedGoodToCompareList(){
        stepsHeaderPage.inputTextInInputFieldIsDisplayed();
        stepsHeaderPage.inputTextInInputField("apple");
        stepsHeaderPage.clickOnSearchButton();
        urlChecker("/product-list-page");
        stepsGoodsPage.loadGoodsPage();
        stepsGoodsPage.addGoodToCompare(1);
        stepsGoodsPage.addGoodToCompare(2);
        stepsGoodsPage.addGoodToCompare(3);
        stepsHeaderPage.clickOnСompareButton();
        urlChecker("/product-comparison");
        stepsComparePage.checkAddedGoodAndExistedGood(stepsGoodsPage.addedGoodsToCompare());
    }

    @Test (priority = 9, testName = "Проверка добавления товаров в список избранных")
    public void checkAddedGoodToFavoriteList(){
        stepsHeaderPage.inputTextInInputFieldIsDisplayed();
        stepsHeaderPage.inputTextInInputField("apple");
        stepsHeaderPage.clickOnSearchButton();
        urlChecker("/product-list-page");
        stepsGoodsPage.loadGoodsPage();
        stepsGoodsPage.addGoodToFavorite(1);
        stepsGoodsPage.addGoodToFavorite(2);
        stepsGoodsPage.addGoodToFavorite(3);
        stepsHeaderPage.clickOnFavoriteButton();
        urlChecker("/wish-list");
        stepsFavoritePage.checkAddedGoodAndExistedGood(stepsGoodsPage.addedGoodsToFavorite());
    }

    @Test (priority = 10, testName = "Проверка изменения города")
    public void checkChangeCity(){
       stepsHeaderPage.clickOnLocationButton();
       stepsLocationPage.modalWindowIsDisplayed();
       stepsLocationPage.selectCity("Краснодар");
       stepsLocationPage.modalWindowIsntDisplayed();
       stepsHeaderPage.checkSelectedCity("Краснодар");
    }
}
