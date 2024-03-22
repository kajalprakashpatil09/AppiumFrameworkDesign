package org.qa.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.qa.utils.AndroidActions;

import java.util.List;

/*
This class contains elements and function for user interaction on these elements from Cart page
 */
public class CartPage extends AndroidActions {
    private AndroidDriver driver;

    @AndroidFindBy(id= "com.androidsample.generalstore:id/productPrice")
    List<WebElement> productsInCart;

    @AndroidFindBy(id= "com.androidsample.generalstore:id/totalAmountLbl")
    WebElement totalAmount;

    @AndroidFindBy(id= "com.androidsample.generalstore:id/termsButton")
    WebElement terms;

    @AndroidFindBy(id= "android:id/button1")
    WebElement acceptButton;

    @AndroidFindBy(className= "android.widget.CheckBox")
    WebElement checkBox;

    @AndroidFindBy(id= "com.androidsample.generalstore:id/btnProceed")
    WebElement proceedButton;

    public CartPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public double getTotalAmountOfSelectedProducts(){
       double productPrice = 0;
       for(int i=0;i<productsInCart.size();i++){
           productPrice = productPrice + getFormattedAmount(productsInCart.get(i).getAttribute("text"));
       }
       return productPrice;
    }

    public double getTotalAmount(){
        return getFormattedAmount(totalAmount.getAttribute("text"));
    }

    public double getFormattedAmount(String amount){
        double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    public void acceptTerms(){
        longPress(terms);
        acceptButton.click();
    }

    public void submitOrder(){
        checkBox.click();
        proceedButton.click();
    }
}
