package lesson_2;

public class VariableDeclarations {
    public static void main(String[] args) {
        int a = 10;
        System.out.println("a = " + a);

        int b = a * 2 - 3;
        System.out.println("b = " + b);

        String text = a + " not equals" + b;
        System.out.println("text: " + text);

        int sum = sum(a, b);
        System.out.println("sum a and b = " + sum);
    }

    public static int sum(int a, int b) {
        return a + b;
    }
}
