package org.qa.pageObjects.android;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.qa.utils.AndroidActions;

/*
This class contains elements and function for user interaction on these elements from Form page
 */
public class FormPage extends AndroidActions {
    private AndroidDriver driver;

    @AndroidFindBy(className="android.widget.EditText")
    private WebElement nameField;

    @AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
    private WebElement femaleOption;

    @AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
    private WebElement maleOption;

    @AndroidFindBy(id="android:id/text1")
    private WebElement country;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;

    @AndroidFindBy(xpath="(//android.widget.Toast)[1]")
    private WebElement toastMsg;

    public FormPage(AndroidDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public void setNameField(String text){
        nameField.sendKeys(text);
        driver.hideKeyboard();
    }

    public void setGenderOption(String gender){
        if(gender.equals("female"))
             femaleOption.click();
        else
            maleOption.click();
    }

    public void selectCountry(String countryName){
        country.click();
        scrollToText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
    }

    public void submitForm(){
        shopButton.click();
  }

    public String getToastMessage(){
        return toastMsg.getAttribute("name");
  }

  public void setActivity(){
      ((JavascriptExecutor)driver).executeScript("mobile:startActivity", ImmutableMap.of(
              "intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"
      ));
  }
}
