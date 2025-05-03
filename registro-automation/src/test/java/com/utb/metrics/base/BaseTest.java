package com.utb.metrics.base;

import com.utb.metrics.common.GoogleAdmin;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() throws IOException {
        GoogleAdmin.initChrome();

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        GoogleAdmin.closeRemoteDebuggingSession();
    }


}
