package Steps;

import Pages.ComparePage;
import io.qameta.allure.Step;
import org.testng.Assert;
import java.util.Map;

public class StepsComparePage {
    private ComparePage comparePage;
    public StepsComparePage() {
        comparePage = ComparePage.getComparePage();
    }

    /**
     * Сравнение товаров находящихся в сравнении с товарами, которые были добавлены.
     * @param addedGoods map добавленных товаров.
     */
    @Step("Сравнение товаров находящихся в сравнении с товарами, которые были добавлены")
    public void checkAddedGoodAndExistedGood(Map<String, Integer> addedGoods){
        if(!comparePage.compareGoodsIsntEmpty()) {
            comparePage.rememberGoodsNameAndPriceInCompare();
            if(checkMapsSizes(addedGoods)) {
                Assert.assertTrue(comparePage.compareGoodsNameAndPrice(addedGoods),
                        "Карточки добавленных товаров не соответствуют карточкам товаров в корзине");}
            else Assert.assertTrue(checkMapsSizes(addedGoods),
                    "Кол-во добавленных товаров и кол-во товаров в корзине не совпадают!");
        }
        else Assert.assertFalse(comparePage.compareGoodsIsntEmpty(),
                "Корзина пуста!");
    }

    /**
     * Проверка кол-ва  добавляемых товаров и товаров в сравнении.
     * @param addedGoods map добавленных товаров.
     * @return true - кол-во совпадает, false кол-во не совпадает.
     */
    private boolean checkMapsSizes(Map<String, Integer> addedGoods){
        return (comparePage.compareGoodsSize() == addedGoods.size());
    }

}
