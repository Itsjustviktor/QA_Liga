package Steps;

import Pages.HeaderPage;
import Pages.MostViewedGoodsPage;
import org.testng.Assert;
import java.util.Map;
import static com.codeborne.selenide.Selenide.actions;

public class StepsMostViewedGoods {
    private MostViewedGoodsPage mostViewedGoods;
    private HeaderPage headerPage;
    public StepsMostViewedGoods() {
        mostViewedGoods = MostViewedGoodsPage.getMostViewedGoods();
        headerPage = HeaderPage.getHeaderPage();
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
     * @param elementGood позиция товара на странице.
     */
    public void addGoodToCart(Integer elementGood){
        Integer numberIntoCartBubble = 1;
        if (elementGood <= mostViewedGoods.quantityOfGoods() && elementGood > 0){ // Если пользователь правильно указал позицию товара.
            if(mostViewedGoods.addGoodToCartIsInactive(elementGood-1)){ // Если кнопка добавления товара в корзину активна.
                if (headerPage.cartBubbleIsDisplayed()) { // Если bubble отображается
                    numberIntoCartBubble = headerPage.getCartBubbleQuantity();
                    clickToCartButtonIntoGoodsContainer(elementGood);
                    headerPage.waitCartContainsSomeGood(numberIntoCartBubble+1);
                }
                else { // Если bubble не отображается
                    clickToCartButtonIntoGoodsContainer(elementGood);
                    headerPage.waitCartContainsSomeGood(numberIntoCartBubble);
                }
            }
            else Assert.assertTrue(mostViewedGoods.addGoodToCartIsInactive(elementGood-1),
                    "Товар уже находится в корзине.");
        }
        else Assert.assertTrue(elementGood <= mostViewedGoods.quantityOfGoods() && elementGood > 0,
                "Позиция товара не соввпадает с отображаемыми товарами на странице");
    }

    /**
     * Нажатие на кнопку добаления в корзину у товара, ожидания сокрытия окна с товарами.
     * @param elementGood позиция товара на странице.
     */
    private void clickToCartButtonIntoGoodsContainer(Integer elementGood){
        mostViewedGoods.rememberCartGood(elementGood-1);
        mostViewedGoods.addGoodToCart(elementGood-1);
        actions().moveToElement(headerPage.getHeaderPlug()).perform();
        headerPage.popupWindowShouldBeHidden();
    }

    /**
     * Возвращение map добавленных товаров.
     * @return map добавленных товаров.
     */
    public Map<String, Integer> addedGoods (){
        return mostViewedGoods.getRememberMostViewedGoodsMap();
    }

}
