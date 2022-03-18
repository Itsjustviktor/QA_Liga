package ru.digitalleague.storage_example;

/**
 * Класс для валидации команд пользователя
 */
public class MsgValidator {
    /**
     * Валидация команды добавления
     *
     * @param params параметры команды
     * @return флаг результата проверки
     */
    public static boolean validateAddMsg(String[] params) {
        return params.length == 3 && params[1].matches("[a-zA-Z]+") && params[2].matches("\\d+");
    }

    /**
     * Валидация параметризованной команды
     *
     * @param params параметры команды
     * @return флаг результата проверки
     */
    public static boolean validateParametrizedMsg(String[] params) {
        return params.length == 2 && params[1].matches("[a-zA-Z]+");
    }
}
