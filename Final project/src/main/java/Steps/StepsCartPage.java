package Steps;

import Pages.CartPage;
import Pages.DaysGoodPage;
import org.testng.Assert;
import java.util.Map;

public class StepsCartPage {

    private CartPage cartPage;
    public StepsCartPage() {
        cartPage = CartPage.getCartPage();
    }

    /**
     * Отображение заголовка "Моя корзина".
     */
    public void headerMyCartIsDisplayed(){
        Assert.assertTrue(cartPage.headerMyCartIsDisplayed(),
                "Заголовок \"Моя корзина\" не отображается.");
    }

    /**
     * Сравнение товаров находящихся в корзине с товарами, которые были добавлены.
     * @param addedGoods map добавленных товаров.
     */
    public void checkAddedGoodAndExistedGood(Map<String, Integer> addedGoods){
        if(!cartPage.cartIsntEmpty()) {
            cartPage.rememberGoodsNameAndPriceInCart(); //Получение товаров из корзины.
                if(checkMapsSizes(addedGoods)) {
                    Assert.assertTrue(cartPage.compareGoodsNameAndPrice(addedGoods),
                            "Карточки добавленных товаров не соответствуют карточкам товаров в корзине");}
                else Assert.assertTrue(checkMapsSizes(addedGoods),
                        "Кол-во добавленных товаров и кол-во товаров в корзине не совпадают!");
        }
        else Assert.assertFalse(cartPage.cartIsntEmpty(),
                "Корзина пуста!");
    }

    /**
     * Проверка кол-ва  добавляемых товаров и товаров в корзине.
     * @param addedGoods map добавленных товаров.
     * @return true - кол-во совпадает, false кол-во не совпадает.
     */
    private boolean checkMapsSizes(Map<String, Integer> addedGoods){
        return (cartPage.getCartSize() == addedGoods.size());
    }

    /**
     * Проверка видимости кнопки "Перейти к оформлению".
     */
    public void continueButtonIsDisplayed(){
        Assert.assertTrue(cartPage.continueButtonIsDisplayed(),
                "Кнопка \"Перейти к оформлению\" не отображается.");
    }

    /**
     * Проверка отображается ли заголовок и содержит в зоголовке "В корзине X товар(а)" реальное кол-во товара.
     */
    public void headerThatContainsQuantityGoodsIsDisplayed(){
        Assert.assertTrue(cartPage.headerThatContainsQuantityGoodsIsDisplayed() &&
                (cartPage.headerQuantityGoodsIsCart() == cartPage.getCartSize()),
                "Заголовок не отображается или кол-во товара не совпадает");
    }

    /**
     * Проверка общей стоимости товаров с ожидаемой стоимостью из header.
     */
    public void realSummOfGoodsEqualsToExpectedSumm(){
        Assert.assertEquals(cartPage.realSummOfGoods(), cartPage.expectedSummOfGoods(),
                "Общая сумма товаров не совпадает с суммой из заголовка");
    }

}
