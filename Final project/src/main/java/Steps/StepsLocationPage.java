package Steps;

import Pages.LocationPage;
import io.qameta.allure.Step;
import org.testng.Assert;

public class StepsLocationPage {
    private LocationPage locationPage;
    public StepsLocationPage() {
        locationPage = LocationPage.getLocationPage();
    }

    /**
     * Выбор города.
     * @param city город.
     */
    @Step("Выбор города \"{city}\"")
    public void selectCity(String city) {
        if (locationPage.cityIsExist(city))
            locationPage.selectCity(city);
        else Assert.assertTrue(locationPage.cityIsExist(city),
                    "Города нет в списке!");
    }

    /**
     * Модальное окно и заголовок "Выберите город" отображаются.
     */
    @Step("Модальное окно и заголовок \"Выберите город\" отображаются")
    public void modalWindowIsDisplayed(){
        Assert.assertTrue(locationPage.modalWindowIsDisplayed() && locationPage.headerIsDisplayed(),
                "Модальное окно или его заголовок не отображается.");
    }

    /**
     * Модальное окно не отображаются.
     */
    @Step("Модальное окно не отображаются")
    public void modalWindowIsntDisplayed(){
        Assert.assertTrue(locationPage.modalWindowIsntDisplayed(),
                "Модальное окно видимо.");
    }

}
