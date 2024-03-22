package org.qa.androidtest;

import org.testng.Assert;
import org.testng.annotations.Test;

/*
This class contains tests to verify reporting functionality in framework
 */

public class ReporterTest {

    @Test
    public void test1(){
        Assert.assertEquals(true, false);
    }

    @Test
    public void test2(){
        Assert.assertEquals(true, false);
    }

    @Test
    public void test3(){
        Assert.assertEquals(true, false);
    }

    @Test
    public void test4(){
        Assert.assertEquals(true, true);
    }
}
