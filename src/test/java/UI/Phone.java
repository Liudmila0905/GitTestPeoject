package UI;
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

//2) Verification of 3 filters (manufacturer, price, your own choice)
//1. Navigate to https://rozetka.com.ua/
//2. Search by "samsung"
//3. Click "Мобильные телефоны" in the product filters panel
//4. Add to filters "Apple" and "Huawai"
//5. Verify all filtered products are products made by Samsung, Apple or Huawai
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
    public void testPositiveLogin() {
        driver.findElement(By.name("search")).sendKeys("samsung" + Keys.ENTER);
        WebElement stats = wait.until(presenceOfElementLocated(By.cssSelector("a[class='categories-filter__link categories-filter__link_type_first categories-filter__link_with_icon']")));
        //WebElement elem = driver.findElement(By.linkText("Мобильные телефоны"));
        //elem.click();
        WebElement phoneElem = driver.findElement(By.linkText("Мобильные телефоны")));
        phoneElem.click();
        wait.until(presenceOfElementLocated(By.className("goods-tile__price-value")));
    }
}