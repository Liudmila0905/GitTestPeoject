package Monitors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class ParentPage {

    WebDriver driver;
    WebDriverWait wait;

    public void goBack() {
        //press 'back' button
        driver.navigate().back();
    }

    public void openComparator() {
        //click on Compare Icon in header
        WebElement elemCompare = driver.findElement(By.cssSelector("span[class='counter counter--gray ng-star-inserted']"));
        elemCompare.click();

        //click on link 'Мониторы (2)'
        WebElement monitorCompare = wait.until(presenceOfElementLocated(By.cssSelector("a[class='comparison-modal__link']")));
        monitorCompare.click();

    }

    public ParentPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
}
