package Steps;

import Pages.GoodsPage;
import Pages.HeaderPage;
import io.qameta.allure.Step;
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
    @Step("Добавление товара, находящийся {elementGood}ым в списке в избранное")
    public void addGoodToFavorite(Integer elementGood){
        Integer numberIntoCartBubble = 1;
        if (elementGood-1 <= goodsPage.quantityOfGoods() && elementGood > 0){ // Если пользователь правильно указал позицию товара.
            if(goodsPage.addGoodToFavoriteIsInactive(elementGood-1)){ // Если кнопка добавления товара в избранное активна.
                if (headerPage.favoriteBubbleIsDisplayed()) { // Если bubble отображается
                    numberIntoCartBubble = headerPage.getFavoriteBubbleQuantity();
                    clickToFavoriteButtonIntoGoodsContainer(elementGood);
                    headerPage.waitFavoriteContainsSomeGood(numberIntoCartBubble+1);
                }
                else { // Если bubble не отображается
                    clickToFavoriteButtonIntoGoodsContainer(elementGood);
                    headerPage.waitFavoriteContainsSomeGood(numberIntoCartBubble);
                }
            }
            else Assert.assertTrue(goodsPage.addGoodToFavoriteIsInactive(elementGood-1),
                    "Товар уже находится в избранном.");
        }
        else Assert.assertTrue(elementGood-1 <= goodsPage.quantityOfGoods() && elementGood > 0,
                "Позиция товара не соввпадает с отображаемыми товарами на странице");
    }

    /**
     * Нажатие на кнопку добаления в избранное у товара, ожидания сокрытия окна с товарами.
     * @param elementGood позиция товара на странице.
     */
    private void clickToFavoriteButtonIntoGoodsContainer(Integer elementGood){
        goodsPage.rememberFavoriteGood(elementGood-1);
        goodsPage.addGoodToFavorite(elementGood-1);
        actions().moveToElement(headerPage.getHeaderPlug()).perform();
        headerPage.popupWindowShouldBeHidden();
    }

    /**
     * Добавление товара в сравнение.
     * @param elementGood позиция товара на странице.
     */
    @Step("Добавление товара, находящийся {elementGood}ым в списке в сравнение")
    public void addGoodToCompare(Integer elementGood){
        Integer numberIntoCartBubble = 1;
        if (elementGood-1 <= goodsPage.quantityOfGoods() && elementGood > 0){ // Если пользователь правильно указал позицию товара.
            if(goodsPage.addGoodToCompareIsInactive(elementGood-1)){ // Если кнопка добавления товара в избранное активна.
                if (headerPage.compareBubbleIsDisplayed()) { // Если bubble отображается
                    numberIntoCartBubble = headerPage.getСompareBubbleQuantity();
                    clickToCompareButtonIntoGoodsContainer(elementGood);
                    headerPage.waitСompareContainsSomeGood(numberIntoCartBubble+1);
                }
                else { // Если bubble не отображается
                    clickToCompareButtonIntoGoodsContainer(elementGood);
                    headerPage.waitСompareContainsSomeGood(numberIntoCartBubble);
                }
            }
            else Assert.assertTrue(goodsPage.addGoodToCompareIsInactive(elementGood-1),
                    "Товар уже находится в сравнении.");
        }
        else Assert.assertTrue(elementGood-1 < goodsPage.quantityOfGoods() && elementGood > 0,
                "Позиция товара не соввпадает с отображаемыми товарами на странице");
    }

    /**
     * Нажатие на кнопку добаления в сравнение у товара, ожидания сокрытия окна с товарами.
     * @param elementGood позиция товара на странице.
     */
    private void clickToCompareButtonIntoGoodsContainer(Integer elementGood){
        goodsPage.rememberCompareGood(elementGood-1);
        goodsPage.addGoodToCompare(elementGood-1);
        actions().moveToElement(headerPage.getHeaderPlug()).perform();
        headerPage.popupWindowShouldBeHidden();
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

    /**
     * Проверка вхождений слова в названиях у всех товаров.
     * @param goodsName частичка или полное название товара.
     */
    @Step("Проверка вхождений слова \"{elementGood}\" в названиях у всех товаров")
    public void goodsContainName(String goodsName){
        Assert.assertTrue(goodsPage.goodsContainName(goodsName), "Не все товары содержат название \""+goodsName+"\"");
    }

    /**
     * Проверка располагаются ли товары на странице по убыванию цены.
     */
    @Step("Проверка располагаются ли товары на странице по убыванию цены")
    public void goodsPriceDecreases(){
        Assert.assertTrue(goodsPage.goodsPriceDecreases(),
                "Товары расположены не по убыванию цен");
    }

    /**
     * Ожидание загрузки страницы и прогрузка товаров после ее открытия или нажатие на фильтр.
     */
    @Step("Ожидание загрузки страницы и прогрузка товаров после ее открытия или нажатие на фильтр")
    public void loadGoodsPage(){
        goodsPage.loaderShouldBeDisappear();
        goodsPage.showProducts();
    }

}
