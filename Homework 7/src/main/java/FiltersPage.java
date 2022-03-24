import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$x;

public class FiltersPage {

    private static FiltersPage filtersPage;

    @FindBy(xpath = "//mvid-button[contains(@class, 'listing-view-switcher__button--grid')]//button[contains(@class, 'button')]")
    private SelenideElement gridButton;
    @FindBy(xpath = "//div[contains(@class, 'listing-view-switcher__pointer--grid')]")
    private SelenideElement gridChecker;

    @FindBy(xpath = "//div[contains(@class, 'filter-container__filters-row')]")
    private SelenideElement allFiltersContainer;

    private SelenideElement loader = $x("//div[@class='skeleton']");

    private String xpathFilter = "//div[contains(@class, 'accordion__container')][contains(.,'%s')]";
    private SelenideElement filter;

    private String xpathChecboxesContainer = "//div[contains(@class, 'filter-checkbox-list__container')]";
    private SelenideElement checboxesContainer;

    private String xpathCheckbox = "//div[@class = 'checkbox'][contains(.,'%s')]";
    private SelenideElement checkbox;

    private String xpathShowAllButton =  "//p[contains(@class,'show-all ng-star-inserted')]";
    private SelenideElement showAllButton;

    private String xpathToggleButton = "//mvid-switcher[contains(.,'%s')]";
    private SelenideElement toggleButton;

    public SelenideElement getGridButton() {
        return gridButton;
    }

    public SelenideElement getgridChecker() {
        return gridChecker;
    }

    public SelenideElement getAllFiltersContainer() {
        return allFiltersContainer;
    }

    public SelenideElement getFilter() {
        return filter;
    }
    public void setFilter(String filterName) {
        this.filter = getAllFiltersContainer()
                .find(By.xpath(
                        toStringFormat(".", xpathFilter, filterName)));
    }

    public SelenideElement getChecboxesContainer() {
        return checboxesContainer;
    }
    public void setChecboxesContainer() {
        this.checboxesContainer = getFilter()
                .find(By.xpath(String.format("."+xpathChecboxesContainer)));
    }

    public SelenideElement getCheckbox() {
        return checkbox;
    }
    public void setCheckbox(String checkboxName) {
        this.checkbox = getChecboxesContainer()
                        .find(By.xpath(
                                toStringFormat(".", xpathCheckbox, checkboxName)));
    }

    public SelenideElement getShowAllButton() {
        return showAllButton;
    }
    public void setShowAllButton(){
        this.showAllButton = getFilter()
                .find(By.xpath("." + xpathShowAllButton));
    }

    public SelenideElement getToggleButton() {
        return toggleButton;
    }
    public void setToggleButton(String toggleButton) {
        this.toggleButton = getAllFiltersContainer()
                .find(By.xpath(
                        toStringFormat(".", xpathToggleButton, toggleButton)));
    }

    public SelenideElement getLoader() {
        return loader;
    }

    public FiltersPage() {
    }
    public static FiltersPage getFiltersPage() {
        if (Objects.isNull(filtersPage)) filtersPage = Selenide.page(new FiltersPage());
        return filtersPage;
    }


    /**
     * Преобразование xpath в стринг, если требуется подстановка какого либо значения.
     * @param dot добавление точки перед xpath при необходимости повторного поиска по веб элементу.
     * @param xpath xpath.
     * @param value подставляемое значение.
     * @return преобразованный xpath.
     */
    private String toStringFormat(String dot, String xpath, String value)
    {
        return String.format(dot + xpath, value);
    }

    /**
     * Проверка текущего отображения товаров.
     * @return true, если отображение таблицей, false, если отображение листом.
     */
    private boolean checkCurrentView()
    {
        return getgridChecker().isDisplayed();
    };

    /**
     * Инициализация веб элементов.
     * @param filterName имя фильтра.
     * @param checkboxName имя чекбокса.
     */
    private void initializeWebElements(String filterName, String checkboxName)
    {
        setFilter(filterName);
        setChecboxesContainer();
        setCheckbox(checkboxName);
        setShowAllButton();
    };

    /**
     * Переключение вида товаров на таблицу.
     */
    public void switchViewToGrid()
    {
        if(checkCurrentView() == false) {
            getGridButton()
                    .shouldBe(Condition.visible)
                    .click();}
    }

    /**
     * Открытие(нажатие) фильтра, если фильтр закрыт.
     * @param filterName имя фильтра.
     * @param checkboxName имя чекбокса.
     */
    public void openFilter(String filterName, String checkboxName)
    {
        initializeWebElements(filterName, checkboxName); // Первичная инициализация.
        if (getChecboxesContainer().isDisplayed() == false){
            getFilter()
                .shouldBe(Condition.visible)
                .scrollIntoView("{block: \"center\"}")
                .click();
            initializeWebElements(filterName, checkboxName);} // Вторичная инициализация, если фильтр был закрыт.
    }

    /**
     * Нажатие на кнопку "Показать еще", если она присутствует в фильтре.
     */
    public void showAllCheckboxes()
    {
        SelenideElement showAllButton = getShowAllButton();
            if (showAllButton.isDisplayed() == true){
                showAllButton
                    .shouldBe(Condition.visible)
                    .scrollIntoView("{block: \"center\"}")
                    .shouldBe(Condition.visible)
                    .click();
            getLoader().shouldBe(Condition.disappear);}
    }

    /**
     * Нажатие на чекбокс.
     */
    public void selectCheckboxFilter()
    {
        if(getCheckbox().isDisplayed() == true) {
            getCheckbox()
                    .shouldBe(Condition.visible)
                    .scrollIntoView("{block: \"center\"}")
                    .click();
            getLoader().shouldBe(Condition.disappear);}
    }

    /**
     * Нажатие на toggle button.
     * @param toggleButtonName название toggle button.
     */
    public void selectToggleButton(String toggleButtonName)
    {
        setToggleButton(toggleButtonName);
        SelenideElement toggleButton = getToggleButton();
        if (toggleButton.isDisplayed() == true){
            toggleButton
                    .shouldBe(Condition.visible)
                    .scrollIntoView("{block: \"center\"}")
                    .click();
            getLoader().shouldBe(Condition.disappear);
        }
    }


}




