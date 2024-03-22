package org.qa.androidtest;

import org.qa.testutils.AndroidBaseTest;
import org.qa.pageObjects.android.FormPage;
import org.qa.pageObjects.android.ProductCatalogPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
This class contains tests to verify product catalog functionality
 */
public class ProductCatalogPageTest extends AndroidBaseTest {

    ProductCatalogPage productCatalogPage;

    @BeforeMethod
    public void setup() throws InterruptedException {
        productCatalogPage = new ProductCatalogPage(driver);
        Thread.sleep(5000);
    }

    @Test
    public void addProductsToCart() throws InterruptedException {
        FormPage formPage = new FormPage(driver);
        Thread.sleep(5000);
        formPage.setNameField("test search");
        formPage.setGenderOption("female");
        formPage.selectCountry("Argentina");
        formPage.submitForm();

        productCatalogPage.addItemToCartByIndex(0);
        productCatalogPage.addItemToCartByIndex(0);
        productCatalogPage.goToCartPage();
    }
}
