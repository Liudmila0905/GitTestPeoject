package UI.Rozetka;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Monitors.RozetkaHomePage;
import Monitors.ComputersNoteBookPage;
import Monitors.MonitorsPage;
import Monitors.MonitorDetailsPage;
import Monitors.Monitor;
import org.testng.Assert;
import Monitors.ComparisonPage;

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
public class TestMonitors {
    WebDriver driver;
    WebDriverWait wait;
    RozetkaHomePage homePage;
    ComputersNoteBookPage computersNoteBook ;
    MonitorsPage monitorsPage;
    MonitorDetailsPage monitorDetailsPage;
    ComparisonPage comparisonPage;
    String baseUrl = "https://rozetka.com.ua/";

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
        driver.get(baseUrl);
        homePage = new RozetkaHomePage(driver, wait);
        computersNoteBook = new ComputersNoteBookPage(driver, wait);
        monitorsPage= new MonitorsPage(driver, wait);
        monitorDetailsPage = new MonitorDetailsPage(driver, wait);
        comparisonPage = new ComparisonPage(driver, wait);
    }

    @Test
    public void testSearchByEnterClick() throws InterruptedException {
        homePage.performClickComputersNoteBook();
        computersNoteBook.performClickMonitors();
        Monitor monitor1 = monitorsPage.clickMonitor(4000);
        Assert.assertTrue(monitorDetailsPage.addToCompare("1"));
        monitorDetailsPage.goBack();
        Monitor monitor2 = monitorsPage.clickMonitor(monitor1.getPrice());
        Assert.assertTrue(monitorDetailsPage.addToCompare("2"));

        homePage.openComparator();
        Assert.assertTrue(comparisonPage.checkMonitorsCompare(monitor1.getName(), monitor2.getName()));

        Thread.sleep(5000);
    }

}


