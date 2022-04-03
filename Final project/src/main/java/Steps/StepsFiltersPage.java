package Steps;

import Pages.FiltersPage;
import io.qameta.allure.Step;
import org.testng.Assert;

public class StepsFiltersPage {
    private FiltersPage filtersPage;
    public StepsFiltersPage() {
        filtersPage = FiltersPage.getFiltersPage();
    }

    /**
     * Фильтр "Сначала популярные" отоюражается.
     */
    @Step("Фильтр \"Сначала популярные\" отоюражается")
    public void filterMostFavoriteIsDisplayed(){
        Assert.assertTrue(filtersPage.filterMostFavoriteIsDisplayed(),
                "выпадающий список вариантов сортировки со значением “Сначала популярные” не отображается.");
    }

    /**
     * Нажатие на фильтр "Сначала дороже".
     */
    @Step("Нажатие на фильтр \"Сначала дороже\"")
    public void clickOnFirstIsMoreExpensiveButton(){
        if (filtersPage.filterFirstIsMoreExpensiveIsntActive()){
            filtersPage.clickOnSortedFiltersContainer();
                if(filtersPage.checkSortedFiltersContainerWasDrop())
                     filtersPage.clickOnFirstIsMoreExpensiveButton();
                else Assert.assertTrue(filtersPage.checkSortedFiltersContainerWasDrop(),
                        "Выпадающий список вариантов сортировки не виден.");
        }
        else Assert.assertTrue(filtersPage.filterFirstIsMoreExpensiveIsntActive(),
                "Выпадающий список вариантов сортировки со значением “Сначала дороже” уже активный.");
    }

}
