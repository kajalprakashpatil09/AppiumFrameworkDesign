package org.qa.androidtest;

import org.qa.testutils.AndroidBaseTest;
import org.qa.pageObjects.android.FormPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
This class contains tests to verify form page functionality
 */
public class FormPageTest extends AndroidBaseTest {

    FormPage formPage;

    @BeforeMethod
    public void setup() throws InterruptedException {
         formPage = new FormPage(driver);
         Thread.sleep(5000);
         //formPage.setActivity();
    }

    /*
   Verify error message on Home screen
    */
    @Test(groups={"smoke"})
    public void verifyToastMessage() throws InterruptedException {
        formPage.submitForm();
        Assert.assertEquals(formPage.getToastMessage(), "Please enter your name1");
    }

    /*
     Fill the form details and verify login functionality
     */
    @Test(dataProvider = "FormData")
    public void fillForm(String name, String gender, String country) throws InterruptedException {
        formPage.setNameField("KPP");
        formPage.setGenderOption("female");
        formPage.selectCountry("Argentina");
        formPage.submitForm();
    }
}
