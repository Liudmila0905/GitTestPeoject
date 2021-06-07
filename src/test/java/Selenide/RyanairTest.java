package Selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class RyanairTest {
   // WebDriver driver;
    @Test
    public void ticketSearch () throws InterruptedException {
        Configuration.timeout = 15000;
        open("https://www.ryanair.com/ua/en");

        SelenideElement div1 = $(By.cssSelector("div[class='cookie-popup-with-overlay__box']"));
        SelenideElement accept = div1.$(By.cssSelector("button[data-ref='cookie.accept-all']"));
        accept.click();
        $("#input-button__departure").clear();
        $("#input-button__departure").val("Vienna");
        $(By.cssSelector("input[id='input-button__destination']")).click();
        $(By.cssSelector("input[id='input-button__destination']")).val("Kyiv");
        $(By.cssSelector("button[data-ref='flight-search-widget__cta']")).click();
        $(By.cssSelector("div[data-ref='input-button__dates-from']")).click();
        $(By.cssSelector("div[data-id='2021-06-18']")).click();
        $(By.cssSelector("div[data-id='2021-06-25']")).click();
        $(By.cssSelector("fsw-input-button[uniqueid='passengers']")).click();
        SelenideElement passengersPicker = $(By.cssSelector("ry-counter[data-ref='passengers-picker__adults']"));
        SelenideElement increase = passengersPicker.$(By.cssSelector("div[data-ref='counter.counter__increment']"));
        increase.click();
        $(By.cssSelector("button[data-ref='flight-search-widget__cta']")).click();
        $$(By.cssSelector("journey[*='header ng-tns-c107']")).shouldHaveSize(2);

        SelenideElement block =  $(By.cssSelector("journey[class='ng-tns-c107-7']"));
        SelenideElement ticket1 =  block.$(By.cssSelector("button[class='date-item text-700 date-item--selected ng-star-inserted']"));
        SelenideElement dataData = ticket1.$(By.cssSelector("span[class='date-item__day-of-month date-item__day-of-month--selected h4']")).shouldHave(Condition.exactText("18"));
        SelenideElement dataMonth = ticket1.$(By.cssSelector("span[class='date-item__month date-item__month--selected h4']")).shouldHave(Condition.exactText("Jun"));
        SelenideElement block2 =  $(By.cssSelector("journey[class='ng-tns-c107-10']"));
        SelenideElement ticket2  = block2.$(By.cssSelector("button[class='date-item text-700 date-item--selected ng-star-inserted']"));
        SelenideElement dataData2  = ticket2.$(By.cssSelector("span[class='date-item__day-of-month date-item__day-of-month--selected h4']")).shouldHave(Condition.exactText("25"));
        SelenideElement dataMonth2 = ticket2.$(By.cssSelector("span[class='date-item__month date-item__month--selected h4']")).shouldHave(Condition.exactText("Jun"));

        Thread.sleep(5000);
    }
}
