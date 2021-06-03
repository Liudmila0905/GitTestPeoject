package Monitors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class ComparisonPage extends ParentPage{
    WebDriver driver;
    WebDriverWait wait;

    public ComparisonPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver = driver;
        this.wait = wait;
    }
    public boolean checkMonitorsCompare (String monitorName1, String monitorName2){
        WebElement ccc = wait.until(presenceOfElementLocated(By.cssSelector("button[class*='button comparison-settings']")));
        List<WebElement> listCompare = driver.findElements(By.cssSelector("div[class='product ng-star-inserted']"));
        if (listCompare.size() != 2) {
            return false;
        }

        //compare names for first monitor
        WebElement el1 = listCompare.get(0);
        String strName = el1.findElement(By.cssSelector("a[class='product__heading']")).getText();
        if (!monitorName2.equals(strName)) {
            return false;
        }

        //compare names for second monitor
        WebElement el2 = listCompare.get(1);
        strName = el2.findElement(By.cssSelector("a[class='product__heading']")).getText();
        if (!monitorName1.equals(strName)) {
            return false;
        }
        return true;
    }
}
