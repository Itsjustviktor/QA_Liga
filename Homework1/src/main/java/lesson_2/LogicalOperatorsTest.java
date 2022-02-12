package lesson_2;

public class LogicalOperatorsTest {
    public static void main(String[] args) {
        int a = 1;
        int b = 1;
        int c = 2;
        int d = 0;

        boolean result = compare(a, c) & isGreater(b, d);
        System.out.println("first result: " + result + "\n");

        result = compare(a, c) && isGreater(b, d);
        System.out.println("second result: " + result + "\n");

        result = isGreater(b, d) | compare(a, c);
        System.out.println("third result: " + result + "\n");

        result = isGreater(b, d) || compare(a, c);
        System.out.println("fourth result: " + result + "\n");
    }

    public static boolean compare(int a, int b) {
        boolean result = a == b;
        System.out.println(a + " == " + b + ": " + result);
        return result;
    }

    public static boolean isGreater(int a, int b) {
        boolean result = a > b;
        System.out.println(a + " > " + b + ": " + result);
        return result;
    }
}
