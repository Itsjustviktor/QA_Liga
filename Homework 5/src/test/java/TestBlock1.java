import org.testng.annotations.*;

public class TestBlock1 extends TestBlock0 {
    /**
     * Вывод в консоль "BeforeClass".
     */
    @BeforeClass
    private void printBeforeClass() {System.out.println("BeforeClass");}

    /**
     * Вывод в консоль "Test".
     */
    @Test(invocationCount = 2, priority = 1) // Данный тест должен вызываться первым и повторяться 2 раза.
    private void printTest() {System.out.println("Test");}

    /**
     * Тест входных значений. <br>
     * 1) В переменную variableСheckEntryData запоминается результат работы метода checkEntryData. <br>
     * 2) Если значение false, то происходит выполенение методов printTest и printEntryData. <br>
     * 3) Если значение true, то происходит выполенение метода printAfterMethod.
     * @param o1 первое значение данных.
     * @param o2 второе значение данных.
     */
    @Test(dataProvider = "data", priority = 2) // Данный тест должен вызываться вторым.
    private void testEntryData(Object o1, Object o2) {
        boolean variableСheckEntryData = TestBlock0.checkEntryData(o1, o2);
        if (variableСheckEntryData == false) {printTest(); TestBlock0.printEntryData(o1,o2);}
        else {printAfterMethod();}
    }

    /**
     * Вывод в консоль "AfterMethod".
     */
    @AfterMethod(enabled = false)
    private void printAfterMethod() {System.out.println("AfterMethod");}

    /**
     * Вывод в консоль "AfterClass".
     */
    @AfterClass
    private void printAfterClass() {System.out.println("AfterClass");}
}
