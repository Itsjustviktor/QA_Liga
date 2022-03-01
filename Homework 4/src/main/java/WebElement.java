import java.util.Random;

public class WebElement {
    private Type type;
    private boolean isDisplayed;
    private String text;
    private String value;

    public WebElement() {
        Random random = new Random();
        Type[] types = Type.values();
        type = types[random.nextInt(types.length)];
        isDisplayed = random.nextBoolean();
        setText(getType());
        setValue(getType());
    }

    private void setText(Type type) {
        if (!type.equals(Type.IMAGE) && !type.equals(Type.INPUT_FIELD))
            text = "Text of element " + new Random().nextInt(1000);
    }

    private void setValue(Type type) {
        if (type.equals(Type.INPUT_FIELD))
            value = "Value of element " + new Random().nextInt(1000);
    }

    public Type getType() {
        return type;
    }

    public void setDisplayed(boolean isDisplayed) {
        this.isDisplayed = isDisplayed;
    }


    public boolean isDisplayed() {
        return isDisplayed;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
