import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.*;

public class Tests {
    public static void main(String[] args) {
        //test_1();
        //test_2();
        //test_3();
        //test_4();
        test_5();
        //test_6();
        //test_7();
        //test_8();
        //test_9();
    }

    /**
     * Получить List чисел в виде текстовых элементов
     */
    public static void test_1() {
        List<Integer> integerList = getIntList();
        integerList.stream()
                .map(String::valueOf)
                .forEach((s) -> System.out.print(s));
    }

    /**
     * Отсортировать список по убыванию
     */
    public static void test_2() {
        List<Integer> integerList = getIntList();
        integerList.stream()
                .sorted((i1, i2) -> -i1.compareTo(i2))
                .forEach((s) -> System.out.print(s));
    }

    /**
     * Получить одну строку текста. Каждый элемент должен начинаться с текста "Number - ".
     * Элементы должны разделяться запятой и пробелом.
     * В начале итоговой строки должен быть текст "Number list: "
     * В конце итоговой строки должен быть текст "end of list.".
     */
    public static void test_3() {
        List<String> stringList = getStringList();
        System.out.print(
        stringList.stream()
                .map(s -> "Number - "+s+", ")
                .collect(joining("", "Number list: ", "End of list."))
        );
    }

    /**
     * Получить мапу со значениями, ключи которых больше трех и меньше девяти
     */
    public static void test_4() {
        Map<Integer, String> map = getMap();
        map.entrySet().stream()
                .filter(k -> k.getKey() > 3 && k.getKey() < 9)
                .forEach((m) -> System.out.print(m + " "));
    }

    /**
     * Перемешать все элементы в мапе.
     * Пример результата:
     * Элемент 1: ключ - 5, значение "five"
     * Элемент 2: ключ - 7, значение "seven"
     * Элемент 3: ключ - 2, значение "two"
     * и так далее.
     */
    public static void test_5() {
        Map<Integer, String> map = getMap(); //Заполнение мапы
        Map<Integer, String> newShuffleMap =
                map.entrySet().stream()
                        .collect(toMap(
                        k -> k.getKey(),
                        v -> v.getValue(),
                        (k1, k2) -> k1, IdentityHashMap::new));
        newShuffleMap.forEach(
                (k, v) -> System.out.println("Ключ - "+ k +", значение \""+v+"\""));
    }

    /**
     * Установить во всех элементах isDisplayed = true, и оставить в списке только элементы с value NULL.
     */
    public static void test_6() {
        List<WebElement> elements = getElements();
        elements.stream()
                .peek(d -> d.setDisplayed(true))
                .filter(v -> v.getValue() == null)
                .forEach(o -> System.out.println("isDisplayed = "+o.isDisplayed()+", value = "+o.getValue()));
    }

    /**
     * Инвертировать isDisplayed параметр каждого элемента и отсортировать список по типу элемента
     * со следующим приоритетом:
     * 1. TEXT
     * 2. INPUT_FIELD
     * 3. CHECKBOX
     * 4. BUTTON
     * 5. RADIO_BUTTON
     * 6. IMAGE
     */
    public static void test_7() {
       List<WebElement> elements = getElements();
       List<WebElement> elPriority = new ArrayList<>();

        elPriority = elements.stream()
                .peek(x -> x.setDisplayed(!x.isDisplayed()))
                .sorted(Comparator.comparing(x-> {
                    return x.getType() == Type.TEXT;
                }))
                .sorted(Comparator.comparing(x-> {
                    return x.getType() == Type.INPUT_FIELD;
                }))
                .sorted(Comparator.comparing(x-> {
                    return x.getType() == Type.CHECKBOX;
                }))
                .sorted(Comparator.comparing(x-> {
                    return x.getType() == Type.BUTTON;
                }))
                .sorted(Comparator.comparing(x-> {
                    return x.getType() == Type.RADIO_BUTTON;
                }))
                .sorted(Comparator.comparing(x-> {
                    return x.getType() == Type.IMAGE;
                }))
                .collect(Collectors.toList());
        elPriority.forEach(v->System.out.println(v.getType()+ " " + v.isDisplayed()));
    }

    /**
     * Создать мапу:
     * ключ - текст
     * значение - тип элемента
     */
    public static void test_8() {
        List<WebElement> elements = getElements();
        Map<Object, Object> newMap = new HashMap<>();
        newMap= elements
                .stream()
                .filter(k -> k.getText() != null)
                .collect(toMap(
                    k -> k.getText(),
                    v -> v.getType(),
                    (k1, k2) -> k1, HashMap::new));
        newMap.forEach((k, v) -> System.out.println(k + " - " + v));
    }

    /**
     * Получить список элементов, у которых текст или значение оканчивается на число от 500 и более.
     * И отсортировать по увеличению сначала элементы с текстом, а затем по убыванию элементы со значением.
     */
    public static void test_9() {
        List<WebElement> elements = getElements();
        List<WebElement> elements1;
        System.out.println("Значения:");
        elements1 = elements
                .stream()
                .filter(el -> (nonNull(el.getText())
                        && Integer.parseInt(el.getText().replace("Text of element ", "")) >= 500)
                        ||
                        (nonNull(el.getValue())
                                && Integer.parseInt(el.getValue().replace("Value of element ", "")) >= 500))
                .sorted(
                        (a, b) -> {
                            if (nonNull(a.getText()) && nonNull(b.getText())) {
                                return Integer.valueOf(a.getText().replace("Text of element ", ""))
                                        .compareTo(Integer.valueOf(b.getText().replace("Text of element ", "")));
                            }
                            if (nonNull(a.getValue()) && nonNull(b.getValue())) {
                                return Integer.valueOf(b.getValue().replace("Value of element ", ""))
                                        .compareTo(Integer.valueOf(a.getValue().replace("Value of element ", "")));
                            }
                            return Boolean.compare(isNull(a.getText()), isNull(b.getText()));
                        })
                .collect(toList());
        elements1.forEach(
                element -> System.out.println("{text: \"" + element.getText() + "\", value: \"" + element.getValue() + "\"}")
        );
    }


    public static Map<Integer, String> getMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
        map.put(10, "ten");
        return map;
    }

    public static List<String> getStringList() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        list.add("six");
        list.add("seven");
        list.add("one");
        list.add("nine");
        list.add("ten");
        return list;
    }

    public static List<Integer> getIntList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        return list;
    }

    public static List<WebElement> getElements() {
        List<WebElement> result = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            result.add(new WebElement());
        }
        return result;
    }
}


