import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;

public class Tests {


    @Test
    public void a()
    {
        Selenide.open("https://www.mvideo.ru/televizory-i-cifrovoe-tv-1/televizory-65");
        Steps steps = new Steps();
        steps.switchViewToGrid();
        steps.selectCheckboxFilter("Категория", "OLED-телевизоры");
        steps.selectCheckboxFilter("Диагональ", "39\" - 48\"");
        steps.findProductCard("Телевизор LG OLED48C1RLA");
        System.out.println(1);
    }
}
