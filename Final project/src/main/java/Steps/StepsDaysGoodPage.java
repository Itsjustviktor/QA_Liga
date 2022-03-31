package Steps;

import Pages.DaysGoodPage;
import org.testng.Assert;

import java.util.Map;


public class StepsDaysGoodPage {

    private DaysGoodPage daysGoodPage;
    public StepsDaysGoodPage() {
        daysGoodPage = DaysGoodPage.getDaysGoodPage();
    }

    /**
     * Карточка "Товар дня" отображается.
     */
    public void daysGoodIsDisplayed(){
        Assert.assertTrue(daysGoodPage.daysGoodIsDisplayed(),"Товар дня не отображается.");
    }

    /**
     * Добавленный(ые) товар(ы) дня в корзину.
     */
    public void daysGoodAddToCart() {
        if (daysGoodPage.daysGoodIsDisplayed()){
            daysGoodPage.daysGoodAddToCart();
            daysGoodPage.rememberGood();
        }
        else System.out.println("Товар не отображается, добавление невозможно.");
    }

    /**
     * Возвращение map добавленных товаров.
     * @return map добавленных товаров.
     */
    public Map<String, Integer> addedGoods (){
        return daysGoodPage.getRememberDaysGoodsMap();
    }

}
