package org.qa.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.qa.utils.AndroidActions;

import java.util.List;

/*
This class contains elements and function for user interaction on these elements from Product Catalog page
 */
public class ProductCatalogPage extends AndroidActions {

    private AndroidDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> addToCart;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cart;

    public ProductCatalogPage(AndroidDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public void addItemToCartByIndex(int index){
        addToCart.get(index).click();
    }

    public void goToCartPage() throws InterruptedException {
       cart.click();
       Thread.sleep(3000);
    }

}
