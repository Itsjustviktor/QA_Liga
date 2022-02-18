package Objects;
import Methods.Actions;
import Skeleton.*;
import Skeleton.Object;

public class InputObject extends Object implements Actions {
    private String input;

    public InputObject(String Name, String Input) // Конструктор класса InputObject.
    {
        super(Name);
        this.input = Input;
    }

    public void SetInput(String data) // Метод установки значения input.
    {
        this.input = data;
    }

    public String GetInput() // Метод получения значения input
    {
        return input;
    }

    @Override
    public void Click() // Перегруженный метод, который выводит "Идет поиск...".
    {
        System.out.println("\nИдет поиск...");
    }

    @Override
    public void AfterClick(String data) /* Перегруженный метод, который получает данные о вводимом названии
                                           и выводит сообщение о поиске. */
    {
        System.out.println("Поиск по названию: " + data+"\n");
    }
}
