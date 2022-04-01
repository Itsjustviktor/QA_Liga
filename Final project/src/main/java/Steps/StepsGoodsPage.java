package Steps;

import Pages.GoodsPage;
import Pages.HeaderPage;
import org.testng.Assert;

import java.util.Map;

import static com.codeborne.selenide.Selenide.actions;

public class StepsGoodsPage {
    private GoodsPage goodsPage;
    private HeaderPage headerPage;
    public StepsGoodsPage() {
        goodsPage = GoodsPage.getGoodsPage();
        headerPage = HeaderPage.getHeaderPage();
    }

    /**
     * Добавление товара в избранное.
     * @param elementGood позиция товара на странице.
     */
    public void addGoodToFavorite(Integer elementGood){
        if (elementGood-1 <= goodsPage.quantityOfGoods() && elementGood > 0){
            if(goodsPage.addGoodToFavoriteIsInactive(elementGood-1)){
                goodsPage.addGoodToFavorite(elementGood-1);
                goodsPage.rememberFavoriteGood(elementGood-1);
                actions().moveToElement(headerPage.getHeaderPlug()).perform();
                headerPage.popupWindowShouldBeHidden();
            }
            else Assert.assertTrue(goodsPage.addGoodToFavoriteIsInactive(elementGood-1),
                    "Товар уже находится в избранном.");
        }
        else Assert.assertTrue(elementGood-1 < goodsPage.quantityOfGoods() && elementGood > 0,
                "Позиция товара не соввпадает с отображаемыми товарами на странице");
    }

    /**
     * Добавление товара в избранное.
     * @param elementGood позиция товара на странице.
     */
    public void addGoodToCompare(Integer elementGood){
        if (elementGood-1 <= goodsPage.quantityOfGoods() && elementGood > 0){
            if(goodsPage.addGoodToCompareIsInactive(elementGood-1)){
                goodsPage.addGoodToCompare(elementGood-1);
                goodsPage.rememberCompareGood(elementGood-1);
                actions().moveToElement(headerPage.getHeaderPlug()).perform();
                headerPage.popupWindowShouldBeHidden();
            }
            else Assert.assertTrue(goodsPage.addGoodToCompareIsInactive(elementGood-1),
                    "Товар уже находится в сравнении.");
        }
        else Assert.assertTrue(elementGood-1 < goodsPage.quantityOfGoods() && elementGood > 0,
                "Позиция товара не соввпадает с отображаемыми товарами на странице");
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

    public void goodsContainName(String goodsName){
        Assert.assertTrue(goodsPage.goodsContainName(goodsName), "Не все товары содержат название \""+goodsName+"\"");
    }

    /**
     * Проверка располагаются ли товары на странице по убыванию цены.
     */
    public void goodsPriceDecreases(){
        Assert.assertTrue(goodsPage.goodsPriceDecreases(),
                "Товары расположены не по убыванию цен");
    }

    /**
     * Ожидание загрузки страницы и прогрузка товаров после ее открытия или нажатие на фильтр.
     */
    public void loadGoodsPage(){
        goodsPage.loaderShouldBeDisappear();
        goodsPage.showProducts();
    }
}
