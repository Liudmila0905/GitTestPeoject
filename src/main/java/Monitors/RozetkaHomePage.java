package Monitors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class RozetkaHomePage extends ParentPage {

    WebDriver driver;
    WebDriverWait wait;

    By searchLinkComputersNoteBook = By.linkText("Ноутбуки и компьютеры");

    public RozetkaHomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver = driver;
        this.wait = wait;
    }

    public void performClickComputersNoteBook(){
        driver.findElement(searchLinkComputersNoteBook).click();

    }

}
