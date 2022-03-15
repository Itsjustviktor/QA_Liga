import org.testng.annotations.*;

public class TestBlock3 {
    /**
     * Вывод в консоль "BeforeTest".
     */
    @BeforeTest
    private void printBeforeTest() {System.out.println("BeforeTest");}

    /**
     * Вывод в консоль "Test".
     */
    @Test
    private void printTest() {System.out.println("Test");}

    /**
     * Вывод в консоль "AfterMethod".
     */
    @AfterMethod
    private void printAfterMethod() {System.out.println("AfterMethod");}

    /**
     * Вывод в консоль "AfterTest".
     */
    @AfterTest
    private void printAfterTest() {System.out.println("AfterTest");}
}
