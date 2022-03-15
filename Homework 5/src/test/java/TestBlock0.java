import org.testng.annotations.*;

public class TestBlock0 {
    /**
     * Входные данные для наших тестов.
     * @return возвращение объекта двумерного массива.
     */
    @DataProvider(name = "data")
    public Object[][] entryData() {
        return new Object[][]{
                {1, 2},
                {"", ""},
                {"three", "four"},
                {true, false}
        };
    }

    /**
     * Проверка входных данных на отсутствие значений.
     * @param o1 первое значение данных.
     * @param o2 второе значение данных.
     * @return возвращение boolean - отсутствие данных.
     */
    @Test(enabled = false) // Данный тест должен только вызываться, выключаетм его при помощи enabled = false.
    public static boolean checkEntryData(Object o1, Object o2)
    {
        if (o1 == "" || o2 == "") {return true;}
        else {return false;}
    }

    /**
     * Вывод в консоль входных данных: "Test data: o1 o2".
     * @param o1 первое значение данных.
     * @param o2 второе значение данных.
     */
    @Test(enabled = false) // Данный тест должен только вызываться, выключаетм его при помощи enabled = false.
    public static void printEntryData(Object o1, Object o2) {System.out.println("Test data: "+o1+" "+o2);}

    /**
     * Вывод в консоль "BeforeSuite".
     */
    @BeforeSuite
    private void printBeforeSuite() {System.out.println("BeforeSuite");}

    /**
     * Вывод в консоль "BeforeClass".
     */
    @BeforeClass
    private void printBeforeClass() {System.out.println("BeforeClass");}

    /**
     * Вывод в консоль "Test".
     */
    @Test
    private void printTest() {System.out.println("Test");}

    /**
     * Тест входных значений. <br>
     * 1) В переменную variableСheckEntryData запоминается результат работы метода checkEntryData. <br>
     * 2) Если значение false, то происходит выполенение методов printTest и printEntryData.
     * @param o1 первое значение данных.
     * @param o2 второе значение данных.
     */
    @Test(dataProvider = "data")
    private void testEntryData(Object o1, Object o2) {
        boolean variableСheckEntryData = checkEntryData(o1, o2);
        if (variableСheckEntryData == false) {printTest(); printEntryData(o1,o2);}
    }

    /**
     * Вывод в консоль "AfterMethod".
     */
    @AfterMethod
    private void printAfterMethod() {System.out.println("AfterMethod");}

    /**
     * Вывод в консоль "AfterSuite".
     */
    @AfterSuite
    private void printAfterSuite() {System.out.println("AfterSuite");}
}
