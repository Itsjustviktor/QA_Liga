import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class FiltersPage {

    private static FiltersPage filtersPage;

    /**
     * Кнопка переключения вида на таблицу.
     */
    @FindBy(xpath = "//mvid-button[contains(@class, 'listing-view-switcher__button--grid')]" +
            "//child::mvid-icon[contains(@class,'listing-view-switcher__button-icon') " +
            "and not(contains(@class, 'listing-view-switcher__button-icon--selected'))]")
    private SelenideElement gridButton;

    /**
     * Контейнер для всех фильтров.
     */
    @FindBy(xpath = "//div[contains(@class, 'filter-container__filters-row')]")
    private SelenideElement allFiltersContainer;

    /**
     * Лоадер для перекрытия товаров при загрузке.
     */
    private SelenideElement loader = $x("//div[@class='skeleton']");

    private String xpathFilter = "//div[contains(@class, 'accordion__container')][contains(.,'%s')]";
    /**
     * Контейнер конкретного фильтра.
     */
    private SelenideElement filter;

    private String xpathChecboxesContainer = "//div[contains(@class, 'filter-checkbox-list__container')]";
    /**
     * Контейнер со всеми чекбоксами. Если filter закрыт, контейнер не отображается.
     */
    private SelenideElement checboxesContainer;

    private String xpathCheckbox = "//div[@class = 'checkbox'][contains(.,'%s')]";
    /**
     * Конкретный чекбокс.
     */
    private SelenideElement checkbox;

    /**
     * Коллекция со всеми кнопками "Показать ещё".
     */
    private ElementsCollection showAllButtons = $$x("//p[contains(.,'Показать ещё')]");

    private String xpathToggleButton = "//mvid-switcher[contains(.,'%s')]";
    /**
     * Конкретный toggle button.
     */
    private SelenideElement toggleButton;

    public SelenideElement getGridButton() {
        return gridButton;
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
        this.checkbox = $x(toStringFormat("",xpathCheckbox,checkboxName));
//        this.checkbox = getChecboxesContainer()
//                        .find(By.xpath(
//                                toStringFormat(".", xpathCheckbox, checkboxName)));
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
     * Переключение вида товаров на таблицу.
     */
    public void switchViewToGrid()
    {
        if(getGridButton().isDisplayed() == true) {
            getGridButton()
                    .shouldBe(Condition.visible)
                    .scrollIntoView("{block: \"center\"}")
                    .click();
            getLoader().shouldBe(Condition.disappear);}
    }

    /**
     * Открытие(нажатие) фильтра, если фильтр закрыт.
     * @param filterName имя фильтра.
     */
    public void openFilter(String filterName) {
        setFilter(filterName);
        setChecboxesContainer();

        if (getChecboxesContainer().isDisplayed() == false) {
            getFilter()
                    .shouldBe(Condition.visible)
                    .scrollIntoView("{block: \"center\"}")
                    .click();
            setChecboxesContainer();
        }
    }

    /**
     * Нажатие на кнопки "Показать еще" для полного раскрытия всех фильтов.
     */
    public void showAllCheckboxes()
    {
        while(showAllButtons.last().exists())
        showAllButtons.asDynamicIterable()
                .forEach(button -> button.scrollIntoView("{block: \"center\"}")
                                            .shouldBe(Condition.visible)
                                            .click());
    }

    /**
     * Нажатие на чекбокс.
     */
    public void selectCheckboxFilter(String checkboxName)
    {
        setCheckbox(checkboxName);
        SelenideElement checkbox = getCheckbox();

        if(checkbox.isDisplayed() == true) {
            checkbox
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




