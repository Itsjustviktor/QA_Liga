package Objects;
import Methods.Actions;
import Skeleton.*;
import Skeleton.Object;

public class DropdownObject extends Object implements Actions {

    public DropdownObject (String Name) // Конструктор класса DropdownObject.
    {
        super(Name);
    }

    @Override
    public void Click() // Перегруженный метод, который выводит "Вы навелись на выпадающий список!".
    {
        System.out.println("\nВы навелись на выпадающий список!");
    }

    @Override
    public void AfterClick(String data) /* Перегруженный метод, который получает данные о категории
                                           и выводит сообщение о выбранной категории. */
    {
        System.out.println("Выпал список категории: " + data +"\n");
    }
}
