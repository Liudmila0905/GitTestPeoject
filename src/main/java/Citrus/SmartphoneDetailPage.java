package Citrus;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class SmartphoneDetailPage {
    //SelenideElement productPrice = $x("//b[@class='buy-section__new-price']");
    SelenideElement productPrice = $("b[class='buy-section__new-price']");
    SelenideElement buyButton = $x("//button[contains(@class,'buy-section__buy-btn')]");
    SelenideElement closeButton = $("i[class='el-dialog__close el-icon el-icon-close']");
    SelenideElement basketButton = $("i[class='icon-new-citrus-cart']");
    SelenideElement basketWidget = $("span[class='el-dialog__title']");
    ElementsCollection basketProducts = $$("div[class='ctrs-basket-item__products']");
    //ElementsCollection basketProducts = $$("a[class='ctrs-basket-product__name']");
    //SelenideElement totalPrice = $("span[class='ctrs-main-price ctrs-basket-footer__new-price']");
    SelenideElement totalPrice = $x("//span[contains(@class,'ctrs-basket-footer__new-price')]");

    public String getProductPrice() {

        return productPrice.getText().trim();
    }

    public void addToBasket() {
        buyButton.click();
        closeButton.click();
    }

    public void clickBasketIconHeader() {
        basketButton.click();
    }

    public SelenideElement getBasket() {
        return basketWidget;
    }

    public ElementsCollection getProductNameFromBasket() {
        return basketProducts;
    }

    public SelenideElement getBasketTotalPrice() {
        return totalPrice;
    }
}
