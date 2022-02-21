package goods;

import skeleton.Skeleton;

public class Good extends Skeleton {

    private Integer quantity;
    private Integer shelf;

    public Good(String name, Integer quantity, Integer shelf)
    {
        super(name);
        this.quantity = quantity;
        this.shelf = shelf;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public void setShelf(Integer shelf)
    {
        this.shelf = shelf;
    }

    public Integer getQuantity(Integer quantity)
    {
        this.quantity = quantity;
        return quantity;
    }

    public Integer getShelf(Integer shelf)
    {
        this.shelf = shelf;
        return shelf;
    }

}
