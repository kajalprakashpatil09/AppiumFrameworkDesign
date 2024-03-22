package org.qa.testutils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
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
This class contains configuration methods required for android device
 */

public class AndroidBaseTest extends AppiumUtils{
    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass(alwaysRun = true)
    public void configureAppium() throws IOException {
        //Create a object for class properties to read properties from file
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "/src/test/java/org/qa/config/config.properties");
        prop.load(fis);

        //start appium server
        service= startAppiumServer(prop.getProperty("ipAddress"), Integer.parseInt(prop.getProperty("port")));

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(prop.getProperty("androidDeviceName"));
        options.setApp("/Users/kajal/Desktop/AppiumProject/src/test/resources/General-Store.apk");

        //configuration for client to send commands to server specified address and port
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass
    public void tearDown(){
        //close app
        driver.quit();

        //stop appium server
        service.stop();
    }

}
