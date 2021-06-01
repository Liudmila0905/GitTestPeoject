package Yahoo;
// Write automation test for drag&drop
//- Navigate to http://demo.guru99.com/test/drag_drop.html
//- Drag and drop all possible webelements to their placeholders

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DragDrop {
    @Test
    public void DragDrop() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://demo.guru99.com/test/drag_drop.html");

        WebElement From1 =driver.findElement(By.cssSelector("li[id='fourth']"));
        WebElement To1 =driver.findElement(By.cssSelector("ol[id='amt8']"));
        WebElement From2 =driver.findElement(By.cssSelector("li[id='credit2']"));
        WebElement To2 =driver.findElement(By.cssSelector("ol[id='bank']"));
        WebElement From3 =driver.findElement(By.cssSelector("li[id='fourth']"));
        WebElement To3 =driver.findElement(By.cssSelector("ol[id='amt7']"));
        WebElement From4 =driver.findElement(By.cssSelector("li[id='credit1']"));
        WebElement To4 =driver.findElement(By.cssSelector("ol[id='loan']"));

        Actions act=new Actions(driver);
        act.dragAndDrop(From1, To1).build().perform();
        act.dragAndDrop(From2, To2).build().perform();
        act.dragAndDrop(From3, To3).build().perform();
        act.dragAndDrop(From4, To4).build().perform();
        driver.quit();
    }
   }
