package storages;

import skeleton.Skeleton;

public class Storage extends Skeleton {

    private Integer capacity;

    public Storage(String name, Integer capacity) {
        super(name);
        this.capacity = capacity;
    }

    public void setCapacity(Integer capacity)
    {
        this.capacity = capacity;
    }

    public Integer getCapacity(Integer capacity)
    {
        this.capacity = capacity;
        return capacity;
    }
}
