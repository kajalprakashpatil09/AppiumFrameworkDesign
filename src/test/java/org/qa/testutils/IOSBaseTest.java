package org.qa.testutils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.qa.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

/*
This class contains functions to perform iOS device configuration
 */

public class IOSBaseTest extends AppiumUtils {
    public IOSDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass(alwaysRun = true)
    public void configureAppium() throws IOException {
        //Create a object for class properties to read properties from file
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "/src/test/java/org/qa/config/config.properties");
        prop.load(fis);

        //start appium server
        service= startAppiumServer(prop.getProperty("ipAddress"), Integer.parseInt(prop.getProperty("port")));

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(prop.getProperty("iOSDeviceName"));
        options.setApp("/Users/kajal/Desktop/AppiumProject/src/test/resources/UIKitCatalog.app");
        options.setPlatformVersion(prop.getProperty("iOSPlatformVersion"));
        options.setWdaLaunchTimeout(Duration.ofSeconds(5));

        //configuration for client to send commands to server specified address and port
        driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass
    public void tearDown(){
        //close app
        driver.quit();

        //stop appium server
        service.stop();
    }

    public void swipeAction(WebElement element){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", "left",
                "percent", 0.75
        ));
    }

    public void canScrollMore(){
        boolean canScrollMore;
        do
        {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0
            ));
        } while(canScrollMore);
    }

    public void longPress(WebElement element){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "duration", 2000
        ));
    }

    public void dragAndDrop(WebElement element){
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", 661,
                "endY", 587
        ));
    }

}
