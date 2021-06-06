package Monitors;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RozetkaHomePageFactory extends ParentPage{
    WebDriver driver;

    @FindBy(linkText = "Ноутбуки и компьютеры")
    private WebElement searchInput;
    public RozetkaHomePageFactory(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void performSearchRequestFactory(String searchInputText) {
        searchInput.sendKeys(searchInputText + Keys.ENTER);
    }


}
