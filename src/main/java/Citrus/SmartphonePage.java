package Citrus;
import static com.codeborne.selenide.Selenide.*;

public class SmartphonePage {
    String productNameLink = "//a/span[contains(text(),'%s')]";
    public void clickProductName(String productName) {
        $x(String.format(productNameLink, productName)).click();
    }
}
