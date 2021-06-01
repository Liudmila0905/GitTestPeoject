package Yahoo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

//1. Write automation test for gmail.com
//
//- Login to mail.google.com with existing account
//- Create new email (with TO=*the same email*, subject, email text), attach file from your local machine, click send
//- Go to inbox, verify email came, verify subject, content of email, verify file is attached
public class Yahoo {
    String initialUrl = "https://mail.yahoo.com";
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
    }

    @AfterClass
    public void closeBworser() {
        driver.quit();
    }


    @Test
    public void testYahooLogin() throws InterruptedException {
        driver.get(initialUrl);
        driver.findElement(By.cssSelector("a[data-ylk='mKey:signin_click']")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("input[id='login-username']")));
        driver.findElement(By.id("login-username")).sendKeys("avtotest");
        driver.findElement(By.id("login-signin")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("input[id='login-passwd']")));
        driver.findElement(By.cssSelector("input[id='login-passwd']")).sendKeys("misya123");
        driver.findElement(By.id("login-signin")).click();

        wait.until(presenceOfElementLocated(By.cssSelector("div[class='folder-list p_R k_w W_6D6F U_3mS2U']")));
        driver.findElement(By.cssSelector("a[data-test-id='compose-button']")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("div[data-test-id='compose']")));

        driver.findElement(By.cssSelector("input[id='message-to-field']")).sendKeys("avtotest@yahoo.com");
        driver.findElement(By.cssSelector("input[data-test-id='compose-subject']")).sendKeys("Test email");
        driver.findElement(By.cssSelector("div[class='rte em_N ir_0 iy_A iz_h N_6Fd5']")).sendKeys("Hello, friend");

        WebElement divInbox = driver.findElement(By.cssSelector("div[data-test-folder-container='Inbox']"));
        Boolean isPresent = driver.findElements(By.cssSelector("span[data-test-id='displayed-count']")).size() > 0;

        int cnt = 0;
        String sCount;
        if (isPresent) {
            WebElement webCount = divInbox.findElement(By.cssSelector("span[data-test-id='displayed-count']"));
            sCount = webCount.getText();
            cnt = Integer.parseInt(sCount);
            cnt++;

            driver.findElement(By.cssSelector("button[data-test-id='compose-send-button']")).click();
            wait.until(ExpectedConditions.textToBePresentInElement(webCount, Integer.toString(cnt)));
        } else {
            driver.findElement(By.cssSelector("button[data-test-id='compose-send-button']")).click();
            wait.until(presenceOfElementLocated(By.cssSelector("span[data-test-id='displayed-count']")));

        }


        driver.findElement(By.cssSelector("span[data-test-folder-name='Inbox']")).click();

        //check
        List<WebElement> listItems = driver.findElements(By.cssSelector("li[class='H_A hd_n p_a L_0 R_0']"));
        WebElement firstMail = listItems.get(0);
        String sTitle = firstMail.findElement(By.cssSelector("span[data-test-id='message-subject']")).getText();
        Assert.assertTrue(sTitle.equals("Test email"));

        Thread.sleep(5000);
    }

}