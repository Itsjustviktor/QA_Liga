package ru.digitalleague;

import nl.altindag.console.ConsoleCaptor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.digitalleague.storage_example.Storage;
import static org.assertj.core.api.Assertions.assertThat;


public class RemoveTest {


    /**
     * Добавление товаров на склад перед тестами.
     */
    @Before
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
     * Тест удаления существующего товара.
     */
    @Test
    public void deleteExistingGood() {
        Storage.removeObject("apple");
        Assert.assertEquals(false, Storage.isInStock("apple"));
    }

    /**
     * Тест удаления несуществующего товара.
     */
    @Test
    public void deleteNonExistingGood() {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();
        String ObjName = "melon"; // Удаляемый элемент.
        Storage.removeObject(ObjName); // Удаление несуществующего элемента
        assertThat(consoleCaptor.getStandardOutput()).contains("Removing failure. Object "+ObjName+" not found."); // Считывания сообщения об ошибке и его проверка.
        consoleCaptor.close();
    }
}
