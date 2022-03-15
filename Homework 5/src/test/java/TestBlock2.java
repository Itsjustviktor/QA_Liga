import org.testng.annotations.*;

public class TestBlock2 {
    /**
     * Тест, который не делает ничего. Нужен, чтобы сработал метод printAfterClass.
     */
    @Test
    private void doNothing() {}

    /**
     * Вывод в консоль "AfterClass".
     */
    @AfterClass
    private void printAfterClass() {System.out.println("AfterClass");}

    /**
     * Вывод в консоль "AfterTest".
     */
    @AfterTest
    private void printAfterTest() {System.out.println("AfterTest");}
}
