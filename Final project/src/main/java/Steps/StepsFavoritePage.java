package Steps;

import Pages.FavoritePage;
import io.qameta.allure.Step;
import org.testng.Assert;
import java.util.Map;

public class StepsFavoritePage {
    private FavoritePage favoritePage;
    public StepsFavoritePage() {
        favoritePage = FavoritePage.getFavoritePage();
    }

    /**
     * Сравнение товаров находящихся в избранном с товарами, которые были добавлены.
     * @param addedGoods map добавленных товаров.
     */
    @Step("Сравнение товаров находящихся в избранном с товарами, которые были добавлены")
    public void checkAddedGoodAndExistedGood(Map<String, Integer> addedGoods){
        if(!favoritePage.favoriteGoodsIsntEmpty()) {
            favoritePage.rememberGoodsNameAndPriceInFavorite(); //Получение товаров из корзины.
            if(checkMapsSizes(addedGoods)) {
                Assert.assertTrue(favoritePage.compareGoodsNameAndPrice(addedGoods),
                        "Карточки добавленных товаров не соответствуют карточкам товаров в корзине");
            }
            else Assert.assertTrue(checkMapsSizes(addedGoods),
                    "Кол-во добавленных товаров и кол-во товаров в корзине не совпадают!");
        }
        else Assert.assertFalse(favoritePage.favoriteGoodsIsntEmpty(),
                "Корзина пуста!");
    }

    /**
     * Проверка кол-ва  добавляемых товаров и товаров в избранном.
     * @param addedGoods map добавленных товаров.
     * @return true - кол-во совпадает, false кол-во не совпадает.
     */
    private boolean checkMapsSizes(Map<String, Integer> addedGoods){
        return (favoritePage.favoriteGoodsSize() == addedGoods.size());
    }

}
