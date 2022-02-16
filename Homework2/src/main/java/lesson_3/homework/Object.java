package lesson_3.homework;

public abstract class Object {
    private String name;

    public Object (String Name) // Конструктор класса Object.
    {
        this.name = Name;
    }

    public String GetName() // Метод получения значения name.
    {
        return name;
    }
}
