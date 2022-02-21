package skeleton;

public abstract class Skeleton {

    private String name;

    public Skeleton (String name)
    {
        this.name = name;
    }

    public void setName(String nameToSet)
    {
        this.name = nameToSet;
    }

    public String getName()
    {
        return name;
    }

}
