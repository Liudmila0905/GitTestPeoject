package Monitors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MonitorsPage extends ParentPage {
    WebDriver driver;
    WebDriverWait wait;

    public MonitorsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver = driver;
        this.wait = wait;
    }
    private int getPrice(WebElement elem) {
        WebElement iPrice = elem.findElement(By.className("goods-tile__price-value"));
        String strPrice = iPrice.getText();
        strPrice = strPrice.replaceAll("\\s", "");
        return Integer.parseInt(strPrice);
    }

    private WebElement getMonitor(int price) {
        List<WebElement> listElem = driver.findElements(By.className("goods-tile__inner"));

        for (int i = 0; i<listElem.size(); i++ ) {
            WebElement iElem = listElem.get(i);
            int value = getPrice(iElem);
            if (value < price) {
                return iElem;
            }
        }

        // monitor not found, return NULL
        return null;
    }

    private String getName(WebElement elem) {
        WebElement iName = elem.findElement(By.cssSelector("a[class='goods-tile__heading ng-star-inserted']"));
        return iName.getAttribute("title");
    }

    public Monitor clickMonitor(int price){
        WebElement monitor = getMonitor(price);
        Monitor mon = new Monitor();

        mon.setName(getName(monitor));
        mon.setPrice(getPrice(monitor));

        monitor.click();

        return mon;
    }
}
