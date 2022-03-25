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

    /**
     * Переключение отображение товаров на таблицу.
     */
    public void switchViewToGrid() {
        filtersPage.switchViewToGrid();
    }

    /**
     * Установка соответствующего фильтра.
     * @param filterName название блока фильтров.
     * @param checkboxName название фильтра.
     */
    public void selectCheckboxFilter(String filterName, String checkboxName){
        filtersPage.openFilter(filterName);
        filtersPage.showAllCheckboxes();
        filtersPage.selectCheckboxFilter(checkboxName);
    }

    /**
     * Устаовка toggle button фильтра.
     * @param toggleButtonName название toggle button.
     */
    public void selectToggleButton(String toggleButtonName){
        filtersPage.selectToggleButton(toggleButtonName);
    }

    /**
     * Поиск товара на всех страницах. <br>
     * Перед преминением необходимо включить отображение таблицей.
     * @param productName название товара.
     */
    public void findProductCard(String productName)
    {
        goodPage.findProductCard(productName);
    }

}
