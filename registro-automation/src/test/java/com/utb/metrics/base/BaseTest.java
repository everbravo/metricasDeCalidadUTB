package com.utb.metrics.base;

import com.utb.metrics.common.GoogleAdmin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

public class BaseTest {

    protected static WebDriver driver;
    private ScreenshotUtil screenshotUtil;

    @BeforeClass
    public static void setUp() throws IOException {
        GoogleAdmin.initChrome();

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        driver = new ChromeDriver(options);
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        GoogleAdmin.closeRemoteDebuggingSession();
    }

}
