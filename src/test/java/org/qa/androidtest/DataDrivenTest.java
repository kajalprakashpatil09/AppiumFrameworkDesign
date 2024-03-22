package org.qa.androidtest;

import org.qa.testutils.AndroidBaseTest;
import org.qa.pageObjects.android.FormPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/*
This class contains data driven tests to verify home page functionality
 */

public class DataDrivenTest extends AndroidBaseTest {

    FormPage formPage;

    @BeforeMethod
    public void setup() throws InterruptedException {
         formPage = new FormPage(driver);
         Thread.sleep(5000);
    }

    /*
     Data driven test to read data from json file
     */
    @Test(dataProvider = "FormDataFromJson")
    public void fillFormTestDataFromJson(HashMap<String, String> input) throws InterruptedException {
        formPage.setNameField(input.get("name"));
        formPage.setGenderOption(input.get("gender"));
        formPage.selectCountry(input.get("country"));
        formPage.submitForm();
    }

    @DataProvider(name="FormDataFromJson")
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>>  data = getJsonData("/Users/kajal/Desktop/AppiumFrameworkDesign/src/test/resources/ecommerce.json");
        return new Object[][]{
            {data.get(0)},
    };
    }

    /*
    Data driven test to read data from dataProvider method
    */
    @Test(dataProvider = "FormData")
    public void fillForm(String name, String gender, String country) throws InterruptedException {
        formPage.setNameField(name);
        formPage.setGenderOption(gender);
        formPage.selectCountry(country);
        formPage.submitForm();
    }

    @DataProvider(name="FormData")
    public Object[][] getDataFromMethod(){
        return new Object[][]{
                {"KPP", "female", "Argentina"},
            {"AAA", "male", "Albania"},
            {"ANA", "female", "Andorra"}
        };
    }
}
