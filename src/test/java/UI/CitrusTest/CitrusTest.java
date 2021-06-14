package UI.CitrusTest;
import Citrus.HomePage;
import Citrus.SmartphoneDetailPage;
import Citrus.SmartphonePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class CitrusTest {

    HomePage homePage;
    SmartphonePage smartphonePage;
    SmartphoneDetailPage smartphoneDetailPage;

    String productName = "Apple iPhone 12 Pro 128GB";
    @BeforeClass
    public void setup(){
     open("https://citrus.ua");
     homePage = new HomePage();
     smartphonePage = new SmartphonePage();
     smartphoneDetailPage = new SmartphoneDetailPage();
    }
    @Test
    public void productPage_addToBasket ()  {
        homePage.hoverMenuSmartphony();
        homePage.clickLink ("Apple");

        smartphonePage.clickProductName (productName);

        String productPrice = smartphoneDetailPage.getProductPrice().replace("₴", "").trim();
        smartphoneDetailPage.addToBasket();

        smartphoneDetailPage.clickBasketIconHeader();
        smartphoneDetailPage.getBasket().shouldBe(Condition.visible);
        smartphoneDetailPage.getProductNameFromBasket().shouldHaveSize(1);
        SelenideElement sss = smartphoneDetailPage.getProductNameFromBasket().get(0);
        String s1 = sss.text();
        sss.shouldHave(Condition.text(productName));
        smartphoneDetailPage.getBasketTotalPrice().shouldHave(Condition.text(productPrice));


    }
}
