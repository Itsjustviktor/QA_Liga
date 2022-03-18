package ru.digitalleague.storage_example;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, описывающий работу со складом
 */
public class Storage {
    private static final int STORAGE_SIZE = 3;
    private static final int ONE_ITEM_AMOUNT = 10;
    private static final Map<String, Integer> storage = new HashMap<>();
    private static int freePlaces;

    /**
     * Добавления вещи на склад
     *
     * @param objectName   название вещи
     * @param objectAmount количество вещей
     */
    public static boolean addObject(String objectName, int objectAmount) {
        if (isInStock(objectName)) {
            return addToExistObject(objectName, objectAmount);
        } else {
            return addNewObject(objectName, objectAmount);
        }
    }

    /**
     * Удаление вещи со склада
     *
     * @param objectName название вещи
     */
    public static boolean removeObject(String objectName) {
        if (isInStock(objectName)) {
            storage.remove(objectName);
            System.out.printf("Object %s successfully removed. \n", objectName);
            return true;
        } else {
            System.out.printf("Removing failure. Object %s not found.\n", objectName);
            return false;
        }
    }

    /**
     * Вывод информации о складе
     */
    public static void showAllStorage() {
        System.out.println("Storage info:");
        storage.forEach((key, value) -> System.out.printf("%s - %s/%s\n", key, value, ONE_ITEM_AMOUNT));
        if (getFreePlaces() > 0)
            System.out.printf("Also storage has %s free places for new items\n", getFreePlaces());
    }

    /**
     * Вывод информации о наличии вещи на складе
     *
     * @param objectName название вещи
     */
    public static void findObject(String objectName) {
        if (getProductAmount(objectName) != 0) {
            System.out.printf("Object %s is present in %s pcs\n", objectName, getProductAmount(objectName));
        } else {
            System.out.printf("Object %s not found.\n", objectName);
        }
    }

    /**
     * Добавление вещи к уже существующей
     *
     * @param name   название вещи
     * @param amount количество вещей
     */
    private static boolean addToExistObject(String name, int amount) {
        if (storage.get(name) + amount > ONE_ITEM_AMOUNT) {
            System.out.printf("Sorry! You can add only %s of %s\n", ONE_ITEM_AMOUNT - storage.get(name), name);
            return false;
        } else {
            storage.put(name, storage.get(name) + amount);
            System.out.printf("%s of %s(s) successfully added. \n", amount, name);
            return true;
        }
    }

    /**
     * Добавление новой вещи на склад
     *
     * @param name   название вещи
     * @param amount количество вещей
     */
    private static boolean addNewObject(String name, int amount) {
        if (getFreePlaces() > 0) {
            if (amount > ONE_ITEM_AMOUNT) {
                System.out.printf("Sorry! You can add only %s of %s\n", ONE_ITEM_AMOUNT, name);
                return false;
            } else {
                storage.put(name, amount);
                System.out.printf("%s of %s(s) successfully added. \n", amount, name);
                return true;
            }
        } else {
            System.out.println("Cannot add object. No free space.\n");
            return false;
        }
    }

    /**
     * Проверка наличия вещи на складе
     *
     * @param object название вещи
     * @return флаг наличия вещи на складе
     */
    public static boolean isInStock(String object) {
        return storage.containsKey(object);
    }

    /**
     * Вычисление количества свободных мест на складе
     */
    public static void checkFreePlaces() {
        freePlaces = STORAGE_SIZE - storage.size();
    }

    /**
     * Получение количества свободных полок
     *
     * @return количество свободных полок
     */
    public static int getFreePlaces() {
        checkFreePlaces();
        return freePlaces;
    }

    /**
     * Получает количество вещи на складе
     *
     * @param productName название вещи
     * @return количество вещи на складе (если ее нет, то возвращается 0)
     */
    public static int getProductAmount(String productName) {
        return storage.getOrDefault(productName, 0);
    }

    /**
     * Очищает полностью склад.
     */
    public static void clearStorage()
    {
        storage.clear();
    }
}
