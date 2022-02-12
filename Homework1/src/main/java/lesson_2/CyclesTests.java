package lesson_2;

import java.util.Arrays;

public class CyclesTests {
    public static void main(String[] args) {
        continueTest();
    }

    public static void whileTest() {
        int i = 0;
        while (i < 5) {
            System.out.println(i);
            i++;
        }
    }

    public static void doWhileTest() {
        int i = 0;
        do {
            System.out.println(i);
            i++;
        } while (i < 5);
    }

    public static void forTest() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
    }

    public static void forEachTest() {
        String[] strs = new String[]{"one", "two", "three"};
        for (String text : strs) {
            System.out.println(text);
        }
    }

    public static void breakTest() {
        for (int i = 0; i < 15; i++) {
            if (i == 3) break;
            System.out.println(i);
        }
    }

    public static void continueTest() {
        for (int i = 0; i < 15; i++) {
            if ((i % 2) == 0) continue;
            System.out.println(i);
        }
    }

    public static void preElementInForEachTest() {
        String[] strs = new String[]{"one", "two", "three"};
        for (String text : strs) {
            System.out.println(text);
            int beforeElementIndex = Arrays.asList(strs).indexOf(text) - 1;
            if (beforeElementIndex >= 0) {
                System.out.println("before element is: " + strs[beforeElementIndex]);
            }
        }
    }
}
