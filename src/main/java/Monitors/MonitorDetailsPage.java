package Monitors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MonitorDetailsPage extends ParentPage{
    WebDriver driver;
    WebDriverWait wait;

    public MonitorDetailsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver = driver;
        this.wait = wait;
    }
    public boolean addToCompare(String sCounter) {
        wait.until(presenceOfElementLocated(By.cssSelector("p[class='product__code detail-code']")));
        //Add monitor to comparison.
        WebElement buttonCompare = driver.findElement(By.cssSelector("button[class='compare-button ng-star-inserted']"));
        buttonCompare.click();
        //Verify icon (1) appears in header close to comparison image (scales).
        wait.until(presenceOfElementLocated(By.cssSelector("use[href='#icon-header-compare']")));
        WebElement countCompare = driver.findElement(By.cssSelector("span[class='counter counter--gray ng-star-inserted']"));
        return countCompare.getText().contains(sCounter);

    }
}
