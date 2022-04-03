package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import java.util.Objects;

public class FiltersPage {
    private static FiltersPage filtersPage;

    /**
     * Xpath для нахождения фильтра "Сначала популярные".
     */
    String xpathFilterMostFavorite = "//span[contains(., 'Сначала популярные')]";
    /**
     * Xpath для нахождения названия фильтра "Сначала дороже".
     */
    String xpathFilterFirstIsMoreExpensive = "//span[contains(., 'Сначала дороже')]";
    /**
     * Xpath для нахождения кнопки фильтра "Сначала дороже".
     */
    String xpathFilterFirstIsMoreExpensiveButton = "//div[contains(@class, 'dropdown__option') " +
            "and contains(@tabindex, '0') " +
            "and contains(., 'Сначала дороже')]";

    @FindBy (xpath = "//div[contains(@class, 'dropdown__title')]")
    private SelenideElement sortedFiltersContainer;
    @FindBy (xpath = "//div[contains(@class, 'dropdown__options')]")
    private SelenideElement dropdownFiltersWindow;

    private SelenideElement getSortedFiltersContainer() {
        return sortedFiltersContainer;
    }

    private SelenideElement getDropdownFiltersWindow() {
        return dropdownFiltersWindow;
    }

    private FiltersPage() {}
    public static FiltersPage getFiltersPage() {
        if (Objects.isNull(filtersPage)) filtersPage = Selenide.page(new FiltersPage());
        return filtersPage;
    }

    /**
     * Отображается выпадающий список вариантов сортировки со значением “Сначала популярные”.
     * @return true - отображается, false - не отображается.
     */
    public boolean filterMostFavoriteIsDisplayed(){
        return getSortedFiltersContainer()
                .find(By.xpath("." + xpathFilterMostFavorite))
                .isDisplayed();
    }

    /**
     * Выпадающий список вариантов сортировки со значением “Сначала дороже” не активный.
     * @return true - не активный, false - активный.
     */
    public boolean filterFirstIsMoreExpensiveIsntActive(){
        return !getSortedFiltersContainer()
                .find(By.xpath("." + xpathFilterFirstIsMoreExpensive))
                .isDisplayed();
    }

    /**
     * Нажатие на контейнер с фильтрами.
     */
    public void clickOnSortedFiltersContainer(){
         getSortedFiltersContainer()
                 .scrollIntoView("{block: \"center\"}")
                 .click();
    }

    /**
     * Проверка выпал ли контейнер с фильтрами.
     */
    public boolean checkSortedFiltersContainerWasDrop(){
        return getSortedFiltersContainer()
                .isDisplayed();
    }

    /**
     * Нажатие на фильтр "Сначала дороже".
     */
    public void clickOnFirstIsMoreExpensiveButton(){
        getDropdownFiltersWindow()
                .find(By.xpath("." + xpathFilterFirstIsMoreExpensiveButton))
                .scrollIntoView("{block: \"center\"}")
                .click();
    }

}
