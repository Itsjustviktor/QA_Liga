package Steps;

import Pages.MostViewedGoodsPage;
import org.testng.Assert;

import java.util.Map;

public class StepsMostViewedGoods {
    private MostViewedGoodsPage mostViewedGoods;
    public StepsMostViewedGoods() {
        mostViewedGoods = MostViewedGoodsPage.getMostViewedGoods();
    }

    public void mostViewedGoodsContainerIsDisplayed(){
        mostViewedGoods.showAllContainers();
        Assert.assertTrue(mostViewedGoods.mostViewedGoodsContainerIsDisplayed(), "f");
    }

    public void addGoodToCart(String goodsName){
        mostViewedGoods.scrollToMostViewedGoodsContainer();
        mostViewedGoods.setGood(goodsName);
        if(mostViewedGoods.addGoodToCartIsInactive()){
        mostViewedGoods.addGoodToCart();
        mostViewedGoods.rememberGood();}
        else System.out.println("Товар уже находится в корзине.");
    }

    public void addGoodToFavorite(String goodsName){
        mostViewedGoods.scrollToMostViewedGoodsContainer();
        mostViewedGoods.setGood(goodsName);
        if(mostViewedGoods.addGoodToFavoriteIsInactive()){
        mostViewedGoods.addGoodToFavorite();
        mostViewedGoods.rememberGood();}
        else System.out.println("Товар уже находится в избранном.");
    }

    public void addGoodToCompare(String goodsName){
        mostViewedGoods.scrollToMostViewedGoodsContainer();
        mostViewedGoods.setGood(goodsName);
        if(mostViewedGoods.addGoodToCompareIsInactive()){
        mostViewedGoods.addGoodToFavorite();
        mostViewedGoods.rememberGood();}
        else System.out.println("Товар уже находится в сравнении.");
    }

    /**
     * Возвращение map добавленных товаров.
     * @return map добавленных товаров.
     */
    public Map<String, Integer> addedGoods (){
        return mostViewedGoods.getRememberMostViewedGoodsMap();
    }
}
