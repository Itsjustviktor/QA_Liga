package ru.digitalleague;

import nl.altindag.console.ConsoleCaptor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.digitalleague.storage_example.Storage;
import static org.assertj.core.api.Assertions.assertThat;

public class InformationsTest {

    /**
     * Добавление товаров на склад перед тестами.
     */
    @Before
    @Test
    public void setUp(){
        Storage.addObject("apple", 3);
    }

    /**
     * Очищение склада после тестов.
     */
    @After
    public void emptyStorage() {
        Storage.clearStorage();
    }

    /**
     * Тест поиска несуществующего товара
     */
    @Test
    public void findNonExistingGood() {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();
        String ObjName = "melon";
        Storage.findObject(ObjName);
        assertThat(consoleCaptor.getStandardOutput()).contains("Object "+ObjName+" not found."); // Считывания сообщения об ошибке и его проверка.
        consoleCaptor.close();
    }

    /**
     * Тест поиска существующего товара
     */
    @Test
    public void findExistingGood() {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();
        String ObjName = "apple";
        Storage.findObject(ObjName);
        assertThat(consoleCaptor.getStandardOutput()).contains("Object "+ObjName+" is present in "+Storage.getProductAmount(ObjName)+" pcs"); // Считывания сообщения об ошибке и его проверка.
        consoleCaptor.close();
    }

    /**
     * Тест поиска есть ли товар на складе
     */
    @Test
    public void isInStock() {
        Assert.assertEquals(true, Storage.isInStock("apple"));
    }

    /**
     * Тест поиска нет ли товар на складе
     */
    @Test
    public void isntInStock() {
        Assert.assertEquals(false, Storage.isInStock("melon"));
    }

    /**
     * Тест поиска получения кол-ва существующего товара
     */
    @Test
    public void productAmount() {
        Assert.assertEquals(3, Storage.getProductAmount("apple"));
    }

    /**
     * Тест поиска получения кол-ва несуществующего товара
     */
    @Test
    public void productAmountNonExistingGood() {
        Assert.assertEquals(0, Storage.getProductAmount("melon"));
    }

    /**
     * Тест получения доступных полок неполного склада
     */
    @Test
    public void getFreePlacesTest () {
        Assert.assertEquals(2, Storage.getFreePlaces());
    }

    /**
     * Тест получения доступных полок полного склада
     */
    @Test
    public void getFreePlacesTest_FullStorage () {
        Storage.addObject("melon", 4);
        Storage.addObject("nuts", 4);
        Assert.assertEquals(0, Storage.getFreePlaces());
    }
}
