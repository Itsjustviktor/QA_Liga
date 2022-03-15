import goods.Good;
import storages.Storage;
import export.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Excel excel = new Excel("Склад","C:\\Exceles\\Products.xls");
        Scanner scan = new Scanner(System.in);
        Storage newStorage = new Storage("", 0, 0);
        Good newGood = new Good("",0);

        String user = System.getenv("USERNAME"); // Получение имени пользователя
        System.out.println("Приветвую вас, "+user+"!\nПожалуйста введите название, размер вашего склада и размер полки через пробел");

        newStorage.addNewStorage(scan, newStorage);

        List <Good> goods = new ArrayList<>(newStorage.getCapacity()); // Создание листа с товарами

        while (true)
        {
            System.out.println("\nВыберите дальнейшее действие:\n" +
                    "1 - добавить товар на склад\n" +
                    "2 - удалить товар со склада\n" +
                    "3 - посмотреть товары на складе\n" +
                    "4 - экспорт товаров на складе в excel\n" +
                    "5 - Выйти из программы");

            String choice = scan.nextLine();
            String numbersFrom1To4Regex = "[1-5]"; // Regex для проверки ввода числа выбора.
            boolean resultNumbers = choice.matches(numbersFrom1To4Regex);

            if (resultNumbers == true)
            {
                if (Integer.parseInt(choice) == 1)
                {
                    newGood.addGood(scan, goods, newStorage);
                }
                if (Integer.parseInt(choice) == 2)
                {
                    newGood.deleteGood(scan, goods);
                }
                if (Integer.parseInt(choice) == 3)
                {
                    newStorage.showStorageWithGoods(goods);
                }
                if (Integer.parseInt(choice) == 4)
                {
                    excel.newExcel(goods);
                }
                if (Integer.parseInt(choice) == 5)
                {
                    System.exit(0);
                }
            }
            else
            {
                System.out.println("Пожалуйста выберите число из списка");
            }
        }
    }
}
