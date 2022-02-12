package lesson_2;

public class ConditionTests {
    public static void main(String[] args) {
        System.out.println();
    }

    public static void ifTest(int a) {
        if (a == 0) {
            System.out.println("zero");
        } else if (a == 1) {
            System.out.println("one");
        } else {
            System.out.println("other");
        }
    }

    public static void switchTest(int a) {
        switch (a) {
            case 0:
                System.out.println("zero");
                break;
            case 1:
                System.out.println("one");
            case 2:
                System.out.println("two");
                break;
            default:
                System.out.println("other");
        }
    }
}
