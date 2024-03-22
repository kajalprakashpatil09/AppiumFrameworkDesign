package org.qa.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

/*
This class contains reusable iOS application actions
 */
public class IOSActions extends AppiumUtils{
    IOSDriver driver;

    public IOSActions(IOSDriver driver){
//        super(driver);
        this.driver=driver;
    }

}
