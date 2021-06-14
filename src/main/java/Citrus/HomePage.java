package Citrus;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    SelenideElement smartphonyLine = $x("//main[@class='home-page']//a[@href='/smartfony/']/span[@class='title']");
    String menuLink =  "//main[@class='home-page']//a/span[text()='%s']";
    public void hoverMenuSmartphony() {
        smartphonyLine.hover();

    }

    public void clickLink(String linkText) {
        $x(String.format(menuLink, linkText)).click();
    }
}
