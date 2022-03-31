package Steps;

import Pages.MostViewedGoodsPage;
import org.testng.Assert;

import java.util.Map;

public class StepsMostViewedGoods {
    private MostViewedGoodsPage mostViewedGoods;
    public StepsMostViewedGoods() {
        mostViewedGoods = MostViewedGoodsPage.getMostViewedGoods();
    }

    /**
     * Проверка отображения блока "Самые просматриваемые".
     */
    public void mostViewedGoodsContainerIsDisplayed(){
        mostViewedGoods.showAllContainers();
        Assert.assertTrue(mostViewedGoods.mostViewedGoodsContainerIsDisplayed(),
                "Блок \"Самые просматриваемые\" не отображается");
    }

    /**
     * Добавление товара в корзину.
     * @param goodsName наименование товара.
     */
    public void addGoodToCart(String goodsName){
        mostViewedGoods.scrollToMostViewedGoodsContainer(); // Прокрутка к блоку "Самые просматриваемые".
        mostViewedGoods.setGood(goodsName); // Поиск товара.
        if(mostViewedGoods.goodIsExist()){ // Если товар существует.
            if(mostViewedGoods.addGoodToCartIsInactive()){ // Если товар не находится в корзине.
            mostViewedGoods.addGoodToCart(); // Добавление товара в корзину.
            mostViewedGoods.rememberGood(); // Запоминание товара в map.
            }
            else Assert.assertTrue(mostViewedGoods.addGoodToCartIsInactive(),
                    "Товар уже находится в корзине.");
        }
        else Assert.assertTrue(mostViewedGoods.goodIsExist(),
                "Товар не существует.");
    }

    /**
     * Возвращение map добавленных товаров.
     * @return map добавленных товаров.
     */
    public Map<String, Integer> addedGoods (){
        return mostViewedGoods.getRememberMostViewedGoodsMap();
    }
}
