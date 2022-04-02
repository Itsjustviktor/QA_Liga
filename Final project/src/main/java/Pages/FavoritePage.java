package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$$x;

public class FavoritePage {
    private static FavoritePage favoritePage;

    /**
     * Xpath для нахождения названия товара.
     */
    private String xpathTittle = "//h3[contains(@class, 'wishlist-product-title')]";
    /**
     * Xpath для нахождения цены товара.
     */
    private String xpathLastPrice = "//div[contains(@class, 'wishlist-price__current')]";

    Map<String, Integer> favoriteGoods = new LinkedHashMap<>();
    private ElementsCollection goodsContainers = $$x("//div[contains(@class, 'wishlist-item-holder')]");

    private ElementsCollection getGoodsContainers() {
        return goodsContainers;
    }

    public Map<String, Integer> getFavoriteGoods() {
        return favoriteGoods;
    }

    private FavoritePage() {}
    public static FavoritePage getFavoritePage() {
        if (Objects.isNull(favoritePage)) favoritePage = Selenide.page(new FavoritePage());
        return favoritePage;
    }

    /**
     * Проверка пуста ли корзина.
     * @return true - пуста, false - не пуста.
     */
    public boolean favoriteGoodsIsntEmpty(){
        return getGoodsContainers().isEmpty();
    }

    /**
     * Определение кол-ва товаров, добавленных в избранное.
     * @return кол-во товаров в корзине.
     */
    public Integer favoriteGoodsSize(){
        return getGoodsContainers().size();
    }

    /**
     * Запоминание товаров, лежащих в избранном, в map.
     */
    public void rememberGoodsNameAndPriceInFavorite() {
        getGoodsContainers().asDynamicIterable().forEach(
                selenideElement -> {
                    getFavoriteGoods().put(
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
        return getFavoriteGoods().entrySet()
                .stream()
                .allMatch(entry -> receivedGoods.containsKey(entry.getKey()) && receivedGoods.containsValue(entry.getValue()));
    }

}
