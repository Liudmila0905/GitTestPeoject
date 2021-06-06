package Monitors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ComputersNoteBookFactoryPage extends ParentPage{
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(linkText = "Мониторы")
    private WebElement searchLinkMonitors;
    public void ComputersNoteBookPage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public ComputersNoteBookFactoryPage(WebDriver driver) {
        super(driver);
    }

    public ComputersNoteBookFactoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void performClickMonitors(){
        wait.until(visibilityOf(searchLinkMonitors)).click();
    }
}
