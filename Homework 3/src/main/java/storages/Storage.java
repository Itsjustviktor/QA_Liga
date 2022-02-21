package storages;

import goods.Good;
import skeleton.Skeleton;

import java.util.List;
import java.util.Scanner;

public class Storage extends Skeleton {

    private Integer capacity;
    private Integer shelfCapacity;

    public Storage(String name, Integer capacity, Integer shelfCapacity) {
        super(name);
        this.capacity = capacity;
        this.shelfCapacity = shelfCapacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setShelfCapacity(Integer capacity) {
        this.shelfCapacity = capacity;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getShelfCapacity() {
        return shelfCapacity;
    }



    /**
     * Метод для добавления нового склада.
     * В данном методе происходит ввод строки из консоли - название и размер склада.
     * Дальше она сравнивается при помощи регулярных выражений на корректность: в названии - только буквы,
     * в размере склада - только цифры, ввод только 2 параметров склада.
     * В конечном итоге, объекту класса - Storage присваивается название и размер.
     * @param scan       сканер для реализации ввода с консоли.
     * @param newStorage склад пользователя, объект класса Storage.
     */
    public static void addNewStorage(Scanner scan, Storage newStorage) {
        while (true) {
            String newUserStorageFromConsole = scan.nextLine();
            String[] subStr; // Массив раздельных слов из строки.
            String delimeter = "\\s"; // Разделитель строки.
            subStr = newUserStorageFromConsole.split(delimeter); // Запись слов в массив.

            if (subStr.length == 3) // Проверка на правильность ввода.
            {
                String wordsOrNotRegex = "[a-zA-Zа-яА-я]+"; // Regex для проверки имени склада.
                String numbersOrNotRegex = "[\\d]+"; // Regex для проверки вместимости склада и полок.
                boolean resultWords = subStr[0].matches(wordsOrNotRegex);
                boolean resultCapacity = subStr[1].matches(numbersOrNotRegex);
                boolean resultShelfCapacity = subStr[2].matches(numbersOrNotRegex);

                if (resultWords == true && resultCapacity == true && resultShelfCapacity == true && Integer.parseInt(subStr[1]) != 0 && Integer.parseInt(subStr[2]) != 0) // Проверка на правильность ввода.
                {
                    newStorage.setName(subStr[0]); // Установка названия для экземпляра класса.
                    newStorage.setCapacity(Integer.parseInt(subStr[1])); // Установка вместимости для экземпляра класса.
                    newStorage.setShelfCapacity(Integer.parseInt(subStr[2])); // Установка вместимости полки для экземпляра класса.
                    break;
                }
                else {error();}
            }
            else {error();}
        }
    }

    /**
     * Метод для отображения всех товаров на складе.
     * В данном методе происходит перебор массива и последовательный вывод товаров в консоль
     * @param goodsMass массив экземпляров класса Good
     */
    public static void showStorageWithGoods(List<Good> goodsMass) {
        if (goodsMass.size() == 0) {System.out.println("Склад пуст, добавьте товар");}
        else
        {
            for (int i = 0; i < goodsMass.size(); i++)
                System.out.println(i + 1 + ". " + goodsMass.get(i).getName() + ", в количестве " + goodsMass.get(i).getQuantity() + " штук");
        } // Вывод товаров со склада.
    }

    /**
     * Метод для вывода ошибки ввода.
     */
    public static void error()
    {
        System.out.println("Вы ввели неккоректные значение, попробуйте еще раз\n");
    }
}
