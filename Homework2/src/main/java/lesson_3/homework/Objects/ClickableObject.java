package lesson_3.homework.Objects;
import lesson_3.homework.Methods.Actions;
import lesson_3.homework.Object;

import java.util.regex.Pattern;

public class ClickableObject extends Object implements Actions {
    private String pictureURL;
    private String goodURL;


    public ClickableObject(String Name, String PictureURL, String GoodURL) // Конструктор класса ClickableObject.
    {
        super(Name);
        this.pictureURL = PictureURL;
        this.goodURL = GoodURL;
    }

    public String GetGoodURL() // Метод получения значения URL.
    {
        return goodURL;
    }

    @Override
    public void Click() // Перегруженный метод, который выводит "Клик!".
    {
        System.out.println("\nКлик!");
    }

    @Override
    public void AfterClick(String data) /* Перегруженный метод, который сравнивает, через регулярное выражение, переданное в него
                                           значение. Если это URI, то выводит сообщение + ссылку, если же нет, то выводит
                                           категорию поиска. */
    {
        String WebOrNotRegex = "^(https?:\\/\\/)?([\\w-]{1,32}\\.[\\w-]{1,32})[^\\s@]*$";
        boolean result = data.matches(WebOrNotRegex);

        if (result)
            System.out.println("Вы перешли по ссылке: " + data+"\n");

        else
            System.out.println("Вы выбрали категорию поиска: " + data+"\n");
    }
}
