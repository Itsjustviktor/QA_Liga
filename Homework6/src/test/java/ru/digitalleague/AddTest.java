package ru.digitalleague;

        import org.junit.Assert;
        import org.junit.Before;
        import org.junit.Rule;
        import org.junit.Test;
        import org.junit.rules.ExpectedException;
        import ru.digitalleague.storage_example.Storage;
        import static org.junit.Assert.assertEquals;


public class AddTest {

    /**
     * Очищение склада перед тестами.
     */
    @Before
    public void emptyStorage() {Storage.clearStorage();}

    /**
     * Тест проверки добавления нового товара на склад.
     */
    @Test
    public void addNewObject() {
        Storage.addObject("apple", 1);
        assertEquals(1, Storage.getProductAmount("apple"));
    }

    /**
     * Тест добавления товара, кол-во которого имеет отрицательное значение.
     */
    @Test
    public void addNegativeAmount() {
        Storage.addObject("apricot", -100);
        assertEquals(false, Storage.isInStock("apricot"));
    }

    /**
     * Тест добавления нового товара, кол-во которого больше вместимости полки.
     */
    @Test
    public void addOverStorageAmount() {
        Storage.addObject("melon", 11);
        assertEquals(false, Storage.isInStock("melon"));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Тест добавления товара, кол-во которого выходит за пределы int.
     */
    @Test ()
    public void addOverIntAmount(){
        try{
            Storage.addObject("pear", Integer.parseInt("10000000000"));
            }
        catch (Exception e) {
            Assert.assertEquals("", e.getMessage());
        }
    }

    /**
     * Тест проверки добавления существующего товара на склад.
     */
    @Test
    public void addToExistObject() {
        Storage.addObject("nuts", 3);
        Storage.addObject("nuts", 6);
        assertEquals(9, Storage.getProductAmount("nuts"));
    }

    /**
     * Тест проверки добавление существующего товара на склад, который превышает вместимость полки.
     */
    @Test
    public void addToExistObjectOverStorageAmount() {
        Storage.addObject("apple", 9);
        Storage.addObject("apple", 3);
        assertEquals(9, Storage.getProductAmount("apple"));
    }

    /**
     * Тест проверки добавление существующего товара на склад, количество которого отрицательное.
     */
    @Test
    public void addSameObjectWithNegativeAmount() {
        Storage.addObject("apple", 9);
        Storage.addObject("apple", -3);
        assertEquals(9, Storage.getProductAmount("apple"));
    }
}