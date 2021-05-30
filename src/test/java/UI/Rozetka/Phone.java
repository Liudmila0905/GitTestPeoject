package UI.Rozetka;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

//2) Verification of 3 filters (manufacturer, price, your own choice)
//1. Navigate to https://rozetka.com.ua/
//2. Search by "samsung"
//3. Click "Мобильные телефоны" in the product filters panel
//4. Add to filters "Apple" and "Huawai"
//5. Verify all filtered products are products made by Samsung, Apple or Huawai

//1. Navigate to https://rozetka.com.ua/
//2. Search by "samsung"
//3. Click "Мобильные телефоны" in the product filters panel
//4. Add to price filter: 5000<price<15000
//5. Verify all filtered products are products with price from range
public class Phone {
    String initialUr = "https://rozetka.com.ua/";
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
    public void navigateAction() {
        driver.get(initialUr);
    }

    @Test
    public void testSamsung() throws InterruptedException {
        driver.findElement(By.name("search")).sendKeys("samsung" + Keys.ENTER);
        wait.until(presenceOfElementLocated(By.cssSelector("button[class='button button--navy button--small catalog-settings__filter-button']")));
        WebElement phoneElem = driver.findElement(By.cssSelector("a[href='https://rozetka.com.ua/mobile-phones/c80003/producer=samsung/']"));
        phoneElem.click();
        wait.until(presenceOfElementLocated(By.cssSelector("a[class='breadcrumbs__link']")));

        boolean bFindApple = addToFilterByName("Apple");
        Thread.sleep(3000);
        boolean bFindHuawei = addToFilterByName("Huawei");
        Thread.sleep(3000);
        List<WebElement> lstItems = driver.findElements(By.cssSelector("a[class='goods-tile__heading ng-star-inserted']"));
        for (WebElement eleItem : lstItems) {
            WebElement span = eleItem.findElement(By.cssSelector("span[class='goods-tile__title']"));
            boolean bFind = span.getText().contains("Apple") || span.getText().contains("Huawei") || span.getText().contains("Samsung");
            Assert.assertFalse(!bFind);
        }

    }

    private boolean addToFilterByName(String name) {
        List<WebElement> options = driver.findElements(By.cssSelector("li[class='checkbox-filter__item ng-star-inserted']"));
        for (int i = 0; i < options.size(); i++) {
            WebElement option = options.get(i);
            WebElement checkbox = option.findElement(By.cssSelector("input[type='checkbox']"));
            if (checkbox.getAttribute("id").equals(name)) {
                option.click();
                return true;
            }
        }
        return false;
    }

    @Test
    public void testSamsungPrice() throws InterruptedException {
        driver.findElement(By.name("search")).sendKeys("samsung" + Keys.ENTER);
        wait.until(presenceOfElementLocated(By.cssSelector("button[class='button button--navy button--small catalog-settings__filter-button']")));
        WebElement phoneElem = driver.findElement(By.cssSelector("a[href='https://rozetka.com.ua/mobile-phones/c80003/producer=samsung/']"));
        phoneElem.click();
        wait.until(presenceOfElementLocated(By.cssSelector("a[class='breadcrumbs__link']")));
        WebElement min = driver.findElement(By.cssSelector("input[formcontrolname='min']"));
        min.clear();
        min.sendKeys("5000");
        WebElement max = driver.findElement(By.cssSelector("input[formcontrolname='max']"));
        max.clear();
        max.sendKeys("15000");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(3000);
        List<WebElement> lstPrices = driver.findElements(By.cssSelector("p[class='ng-star-inserted']"));
        for (WebElement eleItem : lstPrices) {
            WebElement span = eleItem.findElement(By.cssSelector("span[class='goods-tile__price-value']"));
            String sPrice = span.getText();
            sPrice = sPrice.replaceAll("\\s","");
            int iPrice = Integer.parseInt(sPrice);
            assertTrue(iPrice >= 5000 && iPrice <= 15000 );
        }
    }
    @Test
    public void testSamsungReadyToShip () throws InterruptedException{
        driver.findElement(By.name("search")).sendKeys("samsung" + Keys.ENTER);
        wait.until(presenceOfElementLocated(By.cssSelector("button[class='button button--navy button--small catalog-settings__filter-button']")));
        WebElement phoneElem = driver.findElement(By.cssSelector("a[href='https://rozetka.com.ua/mobile-phones/c80003/producer=samsung/']"));
        phoneElem.click();
        wait.until(presenceOfElementLocated(By.cssSelector("a[class='breadcrumbs__link']")));

        boolean bFindReady = addToFilterByName("Готов к отправке");
        Thread.sleep(3000);
        List<WebElement> lstItems = driver.findElements(By.cssSelector("div[class='goods-tile__inner']"));
        for (WebElement eleItem : lstItems) {
            WebElement use = eleItem.findElement(By.cssSelector("div[class='goods-tile__availability goods-tile__availability--available ng-star-inserted']"));
            boolean bFind = use.getText().contains("Готов к отправке");
            Assert.assertFalse(!bFind);
        }
    }
}
