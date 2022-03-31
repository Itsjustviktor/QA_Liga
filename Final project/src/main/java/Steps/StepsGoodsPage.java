package Steps;

import Pages.DaysGoodPage;
import Pages.GoodsPage;
import org.testng.Assert;

import java.util.Map;

public class StepsGoodsPage {
    private GoodsPage goodsPage;
    public StepsGoodsPage() {
        goodsPage = GoodsPage.getGoodsPage();
    }

    /**
     * Добавление товара в избранное.
     * @param quantityGoods позиция товара на странице.
     */
    public void addGoodToFavorite(Integer quantityGoods){
        goodsPage.loaderShouldBeDisappear();
        goodsPage.showProducts();
        if (quantityGoods-1 <= goodsPage.quantityOfTittles() && quantityGoods > 0){
            if(goodsPage.addGoodToFavoriteIsInactive(quantityGoods-1)){
                goodsPage.addGoodToFavorite(quantityGoods-1);
                goodsPage.rememberFavoriteGood(quantityGoods-1);
            }
            else Assert.assertTrue(goodsPage.addGoodToFavoriteIsInactive(quantityGoods-1),
                    "Товар уже находится в избранном.");
        }
        else Assert.assertTrue(quantityGoods-1 < goodsPage.quantityOfTittles(),
                "Номер товара не соввпадает с отображаемыми товарами на странице");
    }


    /**
     * Добавление товара в избранное.
     * @param quantityGoods позиция товара на странице.
     */
    public void addGoodToCompare(Integer quantityGoods){
        goodsPage.loaderShouldBeDisappear();
        goodsPage.showProducts();
        if (quantityGoods-1 <= goodsPage.quantityOfTittles() && quantityGoods > 0){

            if(goodsPage.addGoodToCompareIsInactive(quantityGoods-1)){
                goodsPage.addGoodToCompare(quantityGoods-1);
                goodsPage.rememberCompareGood(quantityGoods-1);
            }
            else Assert.assertTrue(goodsPage.addGoodToCompareIsInactive(quantityGoods-1),
                    "Товар уже находится в избранном.");
        }
        else Assert.assertTrue(quantityGoods-1 < goodsPage.quantityOfTittles(),
                "Номер товара не соввпадает с отображаемыми товарами на странице");
    }

    /**
     * Возвращение map добавленных товаров в избранное.
     * @return map добавленных товаров.
     */
    public Map<String, Integer> addedGoodsToFavorite (){
        return goodsPage.getRememberFavoriteGoodsMap();
    }

    /**
     * Возвращение map добавленных товаров в сравнение.
     * @return map добавленных товаров.
     */
    public Map<String, Integer> addedGoodsToCompare (){
        return goodsPage.getRememberCompareGoodsMap();
    }

}
