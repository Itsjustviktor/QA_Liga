package lesson_2;

public class ComparisonTests {
    public static void main(String[] args) {
        int a = 1;
        int b = 1;
        int c = 2;
        int d = 0;

        boolean result = a < b;
        System.out.println(a + " < " + b + ": " + result);

        result = a < c;
        System.out.println(a + " < " + c + ": " + result);

        result = a <= b;
        System.out.println(a + " <= " + b + ": " + result);

        result = c == d;
        System.out.println(c + " == " + d + ": " + result);

        result = c != d;
        System.out.println(c + " != " + d + ": " + result);

        result = b == a;
        System.out.println(b + " == " + a + ": " + result);

        result = b >= a;
        System.out.println(b + " >= " + a + ": " + result);

        result = b >= c;
        System.out.println(b + " >= " + c + ": " + result);

        result = c > d;
        System.out.println(c + " > " + d + ": " + result);
    }
}
