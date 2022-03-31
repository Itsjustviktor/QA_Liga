package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$$x;

public class ComparePage {
    private static ComparePage comparePage;

    private String xpathTittle = "//h3[contains(@class, 'fl-product-tile__title')]";
    private String xpathLastPrice = "//span[contains(@class, 'fl-product-tile-price__current')]";

    Map<String, Integer> compareGoods = new LinkedHashMap<>();
    private ElementsCollection goodsContainers = $$x("//div[contains(@class, 'c-compare-cell__pinable-product-tile')]");

    private ElementsCollection getGoodsContainers() {
        return goodsContainers;
    }
    public Map<String, Integer> getCompareGoods() {
        return compareGoods;
    }

    private ComparePage() {}
    public static ComparePage getComparePage() {
        if (Objects.isNull(comparePage)) comparePage = Selenide.page(new ComparePage());
        return comparePage;
    }

    /**
     * Проверка пуста ли корзина.
     * @return true - пуста, false - не пуста.
     */
    public boolean compareGoodsIsntEmpty(){
        return getGoodsContainers().isEmpty();
    }

    /**
     * Определение кол-ва товаров, добавленных в избранное.
     * @return кол-во товаров в корзине.
     */
    public Integer compareGoodsSize(){
        return getGoodsContainers().size();
    }


    /**
     * Запоминание товаров, лежащих в избранном, в map.
     */
    public void rememberGoodsNameAndPriceInCompare() {
        getGoodsContainers().asDynamicIterable().forEach(
                selenideElement -> {
                    getCompareGoods().put(
                            selenideElement.find(By.xpath("." + xpathTittle))
                                    .getText(),
                            Integer.parseInt(selenideElement.find(By.xpath("." + xpathLastPrice))
                                    .getText()
                                    .replace(" ","")
                                    .replace("¤","")
                                    .replace("₽", ""))
                    );
                }
        );
    }

    /**
     * Сравнение maps - товаров, лежащих в избранном, и товаров, отправленных в избранное. <br>
     * Сравнение происходит по названию и цене товаров.
     * @param receivedGoods map с отправленным товаром в избранное.
     * @return true - maps совпадают, false - не совпадают.
     */
    public boolean compareGoodsNameAndPrice(Map<String, Integer> receivedGoods){
        return getCompareGoods().entrySet()
                .stream()
                .allMatch(entry -> receivedGoods.containsKey(entry.getKey()) && receivedGoods.containsValue(entry.getValue()));
    }



}
