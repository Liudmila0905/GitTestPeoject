package Monitors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class ComputersNoteBookPage extends ParentPage{
    WebDriver driver;
    WebDriverWait wait;
    By searchLinkMonitors = By.linkText("Мониторы");

    public ComputersNoteBookPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver = driver;
        this.wait = wait;

    }
    public void performClickMonitors(){
        WebElement monitor = wait.until(presenceOfElementLocated(searchLinkMonitors));
        monitor.click();
        //wait.until(presenceOfElementLocated(By.className("goods-tile__price-value")));
    }
}
