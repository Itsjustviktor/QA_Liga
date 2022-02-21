package goods;

import skeleton.Skeleton;
import storages.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Good extends Skeleton {

    private Integer quantity;

    public Good(String name, Integer quantity)
    {
        super(name);
        this.quantity = quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    /**
     * Метод для добавления товара на склад.
     * В данном методе происходит ввод строки из консоли - название и количество товара.
     * Дальше она сравнивается при помощи регулярных выражений на корректность: в названии - только буквы,
     * в количестве товара - только цифры, ввод только 2 параметров товара.
     * Если на складе еще остались свободные полки, товар добавляется в List, иначе выводится ошибка.
     * @param scan сканер для реализации ввода с консоли.
     * @param goodsMass массив экземпляров класса Good
     * @param newStorage экземпляр класса Storage
     */
    public static void addGood(Scanner scan, List<Good> goodsMass, Storage newStorage)
    {
        while (true)
        {
            System.out.println("Введите, через пробел, навзание товара и его количество."+
                    "\nИли напишите back для вызвращения в меню.");
            String newGoodFromConsole = scan.nextLine();
            String[] subStr; // Массив раздельных слов из строки.
            String delimeter = "\\s"; // Разделитель строки.
            subStr = newGoodFromConsole.split(delimeter); // Запись слов в массив.

            if (subStr[0].toLowerCase().equals("back")) {break;} // Выход в главное меню.
            else
            {
                if (subStr.length == 2) // Проверка на правильность ввода.
                {
                    String wordsOrNotRegex = "[a-zA-Zа-яА-я]+"; // Regex для проверки имени товара.
                    String numbersOrNotRegex = "[\\d]+"; // Regex для проверки количества товара.
                    boolean resultName = subStr[0].matches(wordsOrNotRegex);
                    boolean resultQuantity = subStr[1].matches(numbersOrNotRegex);

                    if (resultName == true && resultQuantity == true && Integer.parseInt(subStr[1]) != 0) // Проверка на правильность ввода.
                    {
                        if (goodsMass.size() == 0) // Если склад пустой, просто добавляем товар.
                        {
                            addGoodToMass(goodsMass, subStr, newStorage);
                            break;
                        }
                        else // Если склад не пустой, тогда
                        {
                            for (int i = 0; i < goodsMass.size(); i++) // Проход по всему складу
                            {
                                if (goodsMass.get(i).getName().toLowerCase().equals(subStr[0].toLowerCase()) && goodsMass.get(i).getQuantity() <= 10) // Поиск схожего товара по названию, количество которого < 10 на полке
                                {
                                    if (goodsMass.get(i).getQuantity() + Integer.parseInt(subStr[1]) <= 10) // Если кол-во нового товара + кол-во товара на полне не превышает вместительность полки.
                                    {
                                        goodsMass.get(i).setQuantity(goodsMass.get(i).getQuantity() + Integer.parseInt(subStr[1])); // Установка нового значения.
                                        System.out.println("На складе уже есть полка для данного товара.\n" +
                                                "Количество товара успешно обновлено!\n");
                                        break;
                                    }
                                    else // Если кол-во нового товара + кол-во товара на полне превышает вместительность полки.
                                    {
                                        Integer remains = goodsMass.get(i).getQuantity() + Integer.parseInt(subStr[1]) - 10; // Узнаем оставшееся кол-во товара
                                        goodsMass.get(i).setQuantity(10); // Пополняем старую полку с товаром до максимального значения
                                        System.out.println("На полке с данным товаром закончилось место!\n" +
                                                "Желаете добавить оставшийся товар в количестве "+remains+" на новую(ые) полку(и)?\n" +
                                                "1. Да\n" +
                                                "2. Нет\n");
                                        String ramainsGoodsFromConsole = scan.nextLine();
                                        String ramainsGoodsRegex = "[1-2]"; // Regex для проверки количества товара.
                                        boolean resultramainsGoods = ramainsGoodsFromConsole.matches(ramainsGoodsRegex);
                                        if (resultramainsGoods == true) // Проверка на правильность ввода.
                                        {
                                            if (Integer.parseInt(ramainsGoodsFromConsole) == 1)
                                            {
                                                if (remains > 10) // Если кол-во оставшегося товара больше, чем может вместить в себя 1 полка.
                                                {
                                                    int withoutdot = remains/10; // Расчет целого числа полок, которых потребуется для размещения товара.
                                                    int withdot = remains%10; // Расчет остатка, который необходимо будет поместить на новую полку.
                                                    subStr[1] = Integer.toString(10); // Задаем кол-во товаров на каждой полной полке.
                                                    for (int j = 0; j < withoutdot; j++) // Добавляем полные полки на склад в кол-ве withoutdot.
                                                    {
                                                        addGoodToMass(goodsMass, subStr, newStorage);
                                                    }
                                                    if (withdot == 0) {break;} // Если оставшееся кол-во = 0, нам не рационально создавать пустую полку, бейкуемся
                                                    else // Если оставшееся кол-во > 0.
                                                    {
                                                        subStr[1] = Integer.toString(withdot); // Задаем оставшееся кол-во товаров после полного заполнения предыдущих полок.
                                                        addGoodToMass(goodsMass, subStr, newStorage);
                                                        break;
                                                    }
                                                }
                                                else // Если кол-во оставшегося товара меньше, чем размер 1 полки
                                                {
                                                    subStr[1] = Integer.toString(remains);
                                                    addGoodToMass(goodsMass, subStr, newStorage);
                                                    break;
                                                }
                                            }
                                            if (Integer.parseInt(ramainsGoodsFromConsole) == 2) {break;} // Отмена добавления на новые полки.
                                        }
                                        else {error();}
                                    }
                                }
                                else
                                {
                                    addGoodToMass(goodsMass, subStr, newStorage);
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    else {error();}
                }
                else {error();}
            }
        }
    }

    private static void addGoodToMass(List<Good> goodsMass, String subStr[], Storage newStorage)
    {
        if (goodsMass.size() < newStorage.getCapacity()) // Проверка на остаток места на складе.
        {
            goodsMass.add(new Good(subStr[0].substring(0, 1).toUpperCase()+subStr[0].substring(1).toLowerCase(), Integer.parseInt(subStr[1]))); // Добавление нового товара на склад.
            System.out.println("Товар успешно добавлен на полку!");
        }
        else
        {
            System.out.println("На складе недостаточно мест, пожалуйста, удалите старые товары");
        }
    }

    /**
     * Метод для удаления товара со склада.
     * В данном методе происходит ввод строки из консоли - номер полки.
     * Дальше она сравнивается при помощи регулярного выражения на корректность: номер полки - только цифры.
     * Если вводимое значение соответствует резмеру заполненного склада, товар удаляется, иначе выводится ошибка.
     * @param scan сканер для реализации ввода с консоли.
     * @param goodsMass массив экземпляров класса Good
     */
    public static void deleteGood(Scanner scan, List<Good> goodsMass)
    {
        while (true)
        {
            System.out.println("Пожалуйста, выберите полку с которой хотите удалить товар." +
                    "\nИли напишите back для вызвращения в меню.\n");
            String choiceDeletableGoodFromConsole = scan.nextLine();
            String numbersOrNotRegex = "[1-9]+"; // Regex для проверки вводимой полки.
            boolean resultName = choiceDeletableGoodFromConsole.matches(numbersOrNotRegex);

            if (choiceDeletableGoodFromConsole.toLowerCase().equals("back")) {break;} // Выход в главное меню.

            else
            {
                if (resultName == true && Integer.parseInt(choiceDeletableGoodFromConsole) <= goodsMass.size()) // Проверка заполненности склада.
                {
                    goodsMass.remove(Integer.parseInt(choiceDeletableGoodFromConsole)-1); // Удаление товара.
                    System.out.println("Товар успешно удален!");
                    break;
                }
                else {error();}
            }
        }
    }

    /**
     * Метод для вывода ошибки ввода.
     */
    public static void error()
    {
        System.out.println("Вы ввели неккоректные значение, попробуйте еще раз\n");
    }
}
