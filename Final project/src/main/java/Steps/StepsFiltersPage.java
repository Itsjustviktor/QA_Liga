package Steps;

import Pages.FavoritePage;
import Pages.FiltersPage;
import org.testng.Assert;

public class StepsFiltersPage {
    private FiltersPage filtersPage;
    public StepsFiltersPage() {
        filtersPage = FiltersPage.getFiltersPage();
    }

    public void filterMostFavoriteIsDisplayed(){
        Assert.assertTrue(filtersPage.filterMostFavoriteIsDisplayed(),
                "выпадающий список вариантов сортировки со значением “Сначала популярные” не отображается.");
    }

    public void clickOnFirstIsMoreExpensiveButton(){
        if (filtersPage.filterFirstIsMoreExpensiveIsntDisplayed()){
            filtersPage.clickOnSortedFiltersContainer();
            filtersPage.clickOnFirstIsMoreExpensiveButton();
        }
        else Assert.assertTrue(filtersPage.filterFirstIsMoreExpensiveIsntDisplayed(),
                "Выпадающий список вариантов сортировки со значением “Сначала дороже” уже активный.");
    }

}
