package UI.Rozetka;

import UI.Rozetka.Monitor;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
//1. Navigate to https://rozetka.com.ua/
//2. Hover "Ноутбуки и компьютеры", click "Мониторы", wait for page to load
//3. Find first monitor with price less then 4000UAH, click on image of this monitor, wait for page to load
//4. Add monitor to comparison. Verify icon (1) appears in header close to comparison image (scales). Remember price, name
//5. Click back button in browser
//6. Find first monitor which price is less then first monitor. Click on image of found monitor. Wait for page to load
//7. Add second monitor to comparison. Verify icon (2) appears in header close to comparison image (scales). Remember price, name
//8. Click on comparison image in header. Click on "Мониторы (2)". Wait for page to load
//9. Verify that in comparison you have just 2 monitors
//10. Verify that names are correct (equal to names which you stored in step4 and step7)
//11. Verify that prices are correct (equal to prices which you stored in step4 and step7)
public class Monitors {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    @BeforeMethod
    public void navigateAction(){
        driver.get("https://rozetka.com.ua/");
    }
    @Test
    public void testSearchByEnterClick() throws InterruptedException {
        WebElement elem = driver.findElement(By.linkText("Ноутбуки и компьютеры"));
        elem.click();
        WebElement monitorLink = wait.until(presenceOfElementLocated(By.linkText("Мониторы")));
        monitorLink.click();

        wait.until(presenceOfElementLocated(By.className("goods-tile__price-value")));

        Monitor monitor1 = null;
        Monitor monitor2 = null;


        ////test monitor 1
        //get first monitor with price less 4000
        WebElement elemMonitor1 = getMonitor(4000);
        // get price for monitor 1 (int)
        int price = getPrice(elemMonitor1);
        // get name for monitor 1
        String name = getName(elemMonitor1);
        // click on image, add to compare, check count
        testMonitor(elemMonitor1, "1");
        //save name & price
        monitor1 = new Monitor(name, price);

        Assert.assertTrue(monitor1 != null);

        //press 'back' button
        driver.navigate().back();

        ////test monitor 2
        //get first monitor with price less price of monitor 1
        WebElement elemMonitor2 = getMonitor(monitor1.getPrice());
        // get price for monitor 2 (int)
        price = getPrice(elemMonitor2);
        // get name for monitor 2
        name = getName(elemMonitor2);
        // click on image, add to compare, check count
        testMonitor(elemMonitor2, "2");
        //save name & price
        monitor2 = new Monitor(name, price);

        //click on Compare Icon in header
        WebElement elemCompare = driver.findElement(By.cssSelector("span[class='counter counter--gray ng-star-inserted']"));
        elemCompare.click();

        //click on link 'Мониторы (2)'
        WebElement monitorCompare = wait.until(presenceOfElementLocated(By.cssSelector("a[class='comparison-modal__link']")));
        monitorCompare.click();
////////////////
        //check that count of monitors equals 2
        WebElement ccc = wait.until(presenceOfElementLocated(By.cssSelector("button[class*='button comparison-settings']")));
        List<WebElement> listCompare = driver.findElements(By.cssSelector("div[class='product ng-star-inserted']"));
        Assert.assertTrue(listCompare.size() == 2);

        //compare names for first monitor
        WebElement el1 = listCompare.get(0);
        String strName = el1.findElement(By.cssSelector("a[class='product__heading']")).getText();
        Assert.assertTrue(monitor2.getName().equals(strName));

        //compare names for second monitor
        WebElement el2 = listCompare.get(1);
        strName = el2.findElement(By.cssSelector("a[class='product__heading']")).getText();
        Assert.assertTrue(monitor1.getName().equals(strName));


    }

    private void testMonitor(WebElement iElem, String sCount) {
        WebElement iImage = iElem.findElement(By.cssSelector("a[class='goods-tile__picture ng-star-inserted']"));
        iImage.click();
        wait.until(presenceOfElementLocated(By.cssSelector("p[class='product__code detail-code']")));

        //Add monitor to comparison.
        WebElement buttonCompare = driver.findElement(By.cssSelector("button[class='compare-button ng-star-inserted']"));
        buttonCompare.click();
        //Verify icon (1) appears in header close to comparison image (scales).
        wait.until(presenceOfElementLocated(By.cssSelector("use[href='#icon-header-compare']")));
        WebElement countCompare = driver.findElement(By.cssSelector("span[class='counter counter--gray ng-star-inserted']"));
        Assert.assertTrue(countCompare.getText().contains(sCount));
    }

    // get name of monitor
    private String getName(WebElement elem) {
        WebElement iName = elem.findElement(By.cssSelector("a[class='goods-tile__heading ng-star-inserted']"));
        return iName.getAttribute("title");
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

    //return price of monitor (int)
    private int getPrice(WebElement elem) {
        WebElement iPrice = elem.findElement(By.className("goods-tile__price-value"));
        String strPrice = iPrice.getText();
        strPrice = strPrice.replaceAll("\\s","");
        return Integer.parseInt(strPrice);
    }
}
