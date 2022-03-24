import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;

public class Steps {

    public static GoodPage getProductCard(String productName) {return GoodPage.getProductCard(productName);}
    GoodPage goodPage = new GoodPage();

    private FiltersPage filtersPage;
    public Steps() {
        filtersPage = FiltersPage.getFiltersPage();
    }


    public void switchViewToGrid() {
        filtersPage.switchViewToGrid();
    }

    public void selectCheckboxFilter(String filterName, String checkboxName){
        filtersPage.openFilter(filterName, checkboxName);
        filtersPage.showAllCheckboxes();
        filtersPage.selectCheckboxFilter();
    }

    public void selectToggleButton(String toggleButtonName){
        filtersPage.selectToggleButton(toggleButtonName);
    }

    public void findProductCard(String productName)
    {
        goodPage.findProductCard(productName);
    }

}
