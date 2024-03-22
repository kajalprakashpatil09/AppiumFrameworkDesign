package org.qa.androidtest;

import org.qa.testutils.AndroidBaseTest;
import org.qa.pageObjects.android.CartPage;
import org.qa.pageObjects.android.FormPage;
import org.qa.pageObjects.android.ProductCatalogPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
This class contains tests to verify cart page functionality
 */

public class CartPageTest extends AndroidBaseTest {

    CartPage cartPage;

    @BeforeMethod
    public void setup() throws InterruptedException {
        cartPage = new CartPage(driver);
        Thread.sleep(5000);
    }

    @Test
    public void cartTest() throws InterruptedException {
        FormPage formPage = new FormPage(driver);
        Thread.sleep(5000);
        formPage.setNameField("test search");
        formPage.setGenderOption("female");
        formPage.selectCountry("Argentina");
        formPage.submitForm();

        ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
        productCatalogPage.addItemToCartByIndex(0);
        productCatalogPage.addItemToCartByIndex(0);
        productCatalogPage.goToCartPage();

        Assert.assertEquals(cartPage.getTotalAmountOfSelectedProducts(), cartPage.getTotalAmount());
        cartPage.acceptTerms();
        cartPage.submitOrder();
        Thread.sleep(3000);
    }
}
