package lesson_3.homework;
import lesson_3.homework.Methods.*;
import lesson_3.homework.Objects.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); // Создание сканера для принятия ответа пользователя.

        ClickableObject[] massGood = new ClickableObject[3];
        massGood[0] = new ClickableObject("Смартфоны", "urlTel", "https://www.ozon.ru/category/smartfony-15502/");
        massGood[1] = new ClickableObject("Планшеты", "urlTabl", "https://www.ozon.ru/category/planshety-15525/");
        massGood[2] = new ClickableObject("Ноутбуки", "urlLapt", "https://www.ozon.ru/category/noutbuki-15692/");
        /* Создание объектов класса ClickableObject - категории товаров. */

        ClickableObject[] massSearch = new ClickableObject[3];
        massSearch[0] = new ClickableObject("Везде", "urlEverywhere", "");
        massSearch[1] = new ClickableObject("Одежда", "urlClothes", "");
        massSearch[2] = new ClickableObject("Мебель", "urlFurniture", "");
        /* Создание объектов класса ClickableObject - категории поиска. */

        DropdownObject[] massDropObj = new DropdownObject[3];
        massDropObj[0] = new DropdownObject("Бытовая техника");
        massDropObj[1] = new DropdownObject("Смартфоны и смарт-часы");
        massDropObj[2] = new DropdownObject("Телевизоры");
        /* Создание объектов класса DropdownObject - категории выпадающего списка. */

        InputObject find = new InputObject("Главный поиск", "");
        /* Создание объекта класса InputObject - поле для ввода. */

        try {
            while (true) {
                System.out.print("Выберите область:\n" +
                        "1 - Кликабельные объекты\n" +
                        "2 - Объекты - выпадающий список\n" +
                        "3 - Объекты для ввода\n");
                int choiceAreaNum = scan.nextInt(); // Получение выбора пользователя.

                if (choiceAreaNum == 1) // Если пользователь выбрал Кликабельные объекты.
                {
                    while (true) // Создание бесконечного цикла.
                    {
                        System.out.print("\nВыберите подобласть:\n" +
                                "1 - Категории поиска\n" +
                                "2 - Категории товаров\n");
                        int choiceSubCatNum = scan.nextInt(); // Получение выбора пользователя.

                        if (choiceSubCatNum == 1)  // Если пользователь выбрал Категории поиска.
                        {
                            System.out.print("\nВыберите категорию поиска:\n");
                            for (int i = 0; i < massSearch.length; i++)
                                System.out.println(i + 1 + " - " + massSearch[i].GetName()); /* Проход по массиву massSearch и
                                                                                                вывод каждого объекта на экран. */
                            int choiceCatGoodNum = scan.nextInt(); // Получение выбора пользователя.
                            massSearch[choiceCatGoodNum - 1].Click(); // Вызов метода Click().
                            massSearch[choiceCatGoodNum - 1].AfterClick(massSearch[choiceCatGoodNum - 1].GetName()); /* Вызов метода AfterClick(),
                                                                                                                        в который мы отправляем
                                                                                                                        категорию, полученную путем
                                                                                                                        вызова метода GetName(). */
                            break;
                        }

                        else if (choiceSubCatNum == 2) // Если пользователь выбрал Категории товаров.
                        {
                            System.out.print("\nВыберите категорию товаров:\n");
                            for (int i = 0; i < massGood.length; i++)
                                System.out.println(i + 1 + " - " + massGood[i].GetName()); /* Проход по массиву massGood и
                                                                                              вывод каждого объекта на экран. */
                            int choiceCatGoodNum = scan.nextInt(); // Получение выбора пользователя.
                            massGood[choiceCatGoodNum - 1].Click(); // Вызов метода Click().
                            massGood[choiceCatGoodNum - 1].AfterClick(massGood[choiceCatGoodNum - 1].GetGoodURL()); /* Вызов метода AfterClick(),
                                                                                                                        в который мы отправляем
                                                                                                                        URL, полученный путем вызова
                                                                                                                        метода GetGoodURL(). */
                            break;
                        }
                        else
                        {
                            System.out.println("\nВведите корректное значение\n"); // Исключение неккоректного ввода.
                        }
                    }
                }

                else if (choiceAreaNum == 2) // Если пользователь выбрал Объекты - выпадающий список.
                {
                    while (true) {
                        System.out.print("\nВыберите подобласть: \n");

                        for (int i = 0; i < massDropObj.length; i++)
                            System.out.println(i + 1 + " - " + massDropObj[i].GetName()); /* Проход по массиву massDropObj и
                                                                                              вывод каждого объекта на экран. */

                        int choiceCatGoodNum = scan.nextInt(); // Получение выбора пользователя.
                        if (choiceCatGoodNum - 1 <= massDropObj.length) /* Условие: если выбор пользователя соответствует кол-ву элементов
                                                                           массива. */
                        {
                            massDropObj[choiceCatGoodNum - 1].Click(); // Вызов метода Click().
                            massDropObj[choiceCatGoodNum - 1].AfterClick(massDropObj[choiceCatGoodNum - 1].GetName()); /* Вызов метода AfterClick(),
                                                                                                                        в который мы отправляем
                                                                                                                        категорию, полученную путем
                                                                                                                        вызова метода GetName(). */
                            break;
                        }

                        else
                        {
                            System.out.println("\nВведите корректное значение"); // Исключение неккоректного ввода.
                        }
                    }
                }

                else if (choiceAreaNum == 3) // Если пользователь выбрал Объекты для ввода.
                {
                    System.out.print("\nВведите искомое название: \n");
                    String findGoodText = scan.next(); // Получение выбора пользователя.
                    find.Click(); // Вызов метода Click().
                    find.SetInput(findGoodText); // Вызов метода SetInput(), который устанавливает новый текст, написанный пользователем.
                    find.AfterClick(find.GetInput()); /* Вызов метода AfterClick(), в который мы отправляем текст, полученную путем
                                                        вызова метода GetInput(). */

                }
                else {
                    System.out.println("\nВведите корректное значение"); // Исключение неккоректного ввода
                }
            }
        }
        catch (Throwable t) // Обработчик ошибок.
        {
            System.out.println("\nУпс, я сломался(\nПроблема: " + t);
        }
    }
}
