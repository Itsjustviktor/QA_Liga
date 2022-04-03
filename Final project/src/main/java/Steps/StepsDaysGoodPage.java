package Steps;

import Pages.DaysGoodPage;
import Pages.HeaderPage;
import com.codeborne.selenide.commands.As;
import io.qameta.allure.Step;
import org.testng.Assert;
import java.util.Map;
import static com.codeborne.selenide.Selenide.actions;

public class StepsDaysGoodPage {
    private HeaderPage headerPage;
    private DaysGoodPage daysGoodPage;
    public StepsDaysGoodPage() {
        daysGoodPage = DaysGoodPage.getDaysGoodPage();
        headerPage = HeaderPage.getHeaderPage();
    }


    /**
     * Карточка "Товар дня" отображается.
     */
    @Step ("Карточка \"Товар дня\" отображается")
    public void daysGoodIsDisplayed(){
        Assert.assertTrue(daysGoodPage.daysGoodIsDisplayed(),"Товар дня не отображается.");
    }

    /**
     * Добавленние товара дня в корзину.
     */
    @Step ("Добавленние товара дня в корзину.")
    public void daysGoodAddToCart() {
        if (daysGoodPage.daysGoodIsDisplayed()){
            Integer numberIntoCartBubble = 1;
            if (headerPage.cartBubbleIsDisplayed()) { // Если bubble отображается
                numberIntoCartBubble = headerPage.getCartBubbleQuantity();
                clickToCartButtonIntoDaysGoodsContainer();
                headerPage.waitCartContainsSomeGood(numberIntoCartBubble+1);
            }
            else {
                clickToCartButtonIntoDaysGoodsContainer();
                headerPage.waitCartContainsSomeGood(numberIntoCartBubble);
            }
        }
        else Assert.assertTrue(daysGoodPage.daysGoodIsDisplayed()
                ,"Товар не отображается, добавление невозможно.");;
    }

    /**
     * Нажатие на кнопку добаления в корзину у товара дня, ожидания сокрытия окна с товарами.
     */
    private void clickToCartButtonIntoDaysGoodsContainer(){
        daysGoodPage.rememberGood();
        daysGoodPage.daysGoodAddToCart();
        actions().moveToElement(headerPage.getHeaderPlug()).perform();
        headerPage.popupWindowShouldBeHidden();
    }

    /**
     * Возвращение map добавленных товаров.
     * @return map добавленных товаров.
     */
    public Map<String, Integer> addedGoods (){
        return daysGoodPage.getRememberDaysGoodsMap();
    }

}
