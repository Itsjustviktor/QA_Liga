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
    public void addGoodToCart1(Integer elementGood){

        do{
            if(mostViewedGoods.tittleIsDisplayed(elementGood)){
                addGoodToCart(elementGood);
                break;
            }
            else mostViewedGoods.forwardButtonClick();
        }
        while (!mostViewedGoods.forwardButtonIsDisplayed());

        do{
            if(mostViewedGoods.tittleIsDisplayed(elementGood)){
                addGoodToCart(elementGood);
                break;
            }
            else mostViewedGoods.backwardButtonClick();
        }
        while (!mostViewedGoods.backwardButtonIsDisplayed());

//        while (!mostViewedGoods.forwardButtonIsDisplayed()){
//            if(mostViewedGoods.tittleIsDisplayed(elementGood)){
//                addGoodToCart(elementGood);
//                break;
//            }
//            else mostViewedGoods.forwardButtonClick();
//        }
//        while (!mostViewedGoods.backwardButtonIsDisplayed()){
//            if(mostViewedGoods.tittleIsDisplayed(elementGood)){
//                addGoodToCart(elementGood);
//                break;
//            }
//            else mostViewedGoods.backwardButtonClick();
//        }
    }

    /**
     * Возвращение map добавленных товаров.
     * @return map добавленных товаров.
     */
    public Map<String, Integer> addedGoods (){
        return mostViewedGoods.getRememberMostViewedGoodsMap();
    }

    private void addGoodToCart(Integer elementGood){
        if (elementGood-1 <= mostViewedGoods.quantityOfGoods() && elementGood > 0){
            if(mostViewedGoods.addGoodToCartIsInactive(elementGood-1)){ //Если кнопка корзины активна
                mostViewedGoods.addGoodToCart(elementGood-1);
                mostViewedGoods.rememberCartGood(elementGood-1);
                actions().moveToElement(headerPage.getHeaderPlug()).perform();
                headerPage.popupWindowShouldBeHidden();
            }
            else Assert.assertTrue(mostViewedGoods.addGoodToCartIsInactive(elementGood-1),
                    "Товар уже находится в корзине.");
        }
        else Assert.assertTrue(elementGood-1 < mostViewedGoods.quantityOfGoods() && elementGood > 0,
                "Позиция товара не соввпадает с отображаемыми товарами на странице");
    }
}
