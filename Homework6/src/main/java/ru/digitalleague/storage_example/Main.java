package ru.digitalleague.storage_example;

import java.util.Scanner;

/**
 * Основное тело программы
 */
public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static boolean status = true;

    /**
     * Поддержание процесса работы склада
     *
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Welcome to storage!");
        System.out.println("For information about available commands enter \"help\"");
        while (status) {
            System.out.println("Awaiting command:");
            String command = SCANNER.nextLine();
            String[] comAndValue = command.split(" ");
            switch (comAndValue[0].toLowerCase()) {
                case "add":
                    if (MsgValidator.validateAddMsg(comAndValue))
                        Storage.addObject(comAndValue[1].toLowerCase(), Integer.parseInt(comAndValue[2]));
                    else
                        System.out.println("Incorrect command. Please check and repeat.");
                    break;
                case "delete":
                    if (MsgValidator.validateParametrizedMsg(comAndValue))
                        Storage.removeObject(comAndValue[1].toLowerCase());
                    else
                        System.out.println("Incorrect command. Please check and repeat.");
                    break;
                case "check":
                    if (MsgValidator.validateParametrizedMsg(comAndValue))
                        Storage.findObject(comAndValue[1].toLowerCase());
                    else
                        System.out.println("Incorrect command. Please check and repeat.");
                    break;
                case "showall":
                    Storage.showAllStorage();
                    break;
                case "help":
                    System.out.println("Add [Object Name Amount] - command to add object to storage");
                    System.out.println("Delete [Object Name Amount] - command to remove object from storage");
                    System.out.println("Check [Object Name Amount] - command to check object availability");
                    System.out.println("ShowAll - command to show all storage content");
                    break;
                case "exit":
                    status = false;
                    System.out.println("Storage program is finished");
                    break;
                default:
                    System.out.println("Incorrect command. Please check and repeat.");
            }
        }
    }
}
