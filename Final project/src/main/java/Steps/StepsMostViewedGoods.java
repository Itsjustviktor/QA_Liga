package Steps;

import Pages.MostViewedGoodsPage;
import org.testng.Assert;

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
        mostViewedGoods.addGoodToCart();}
        else System.out.println("Товар уже находится в корзине.");
    }

    public void addGoodToFavorite(String goodsName){
        mostViewedGoods.scrollToMostViewedGoodsContainer();
        mostViewedGoods.setGood(goodsName);
        if(mostViewedGoods.addGoodToFavoriteIsInactive())
        mostViewedGoods.addGoodToFavorite();
        else System.out.println("Товар уже находится в избранном.");
    }

    public void addGoodToCompare(String goodsName){
        mostViewedGoods.scrollToMostViewedGoodsContainer();
        mostViewedGoods.setGood(goodsName);
        if(mostViewedGoods.addGoodToCompareIsInactive())
        mostViewedGoods.addGoodToFavorite();
        else System.out.println("Товар уже находится в сравнении.");
    }
}
