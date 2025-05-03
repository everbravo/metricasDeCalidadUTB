package com.utb.metrics.integrations;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CaptchaSolver {
    private static final String API_KEY = "0a955233fe00c4bc15ef04be89d28d26";
    private static final String SITE_KEY = "6LcWpWYUAAAAAH1odGWmfOh2WN1gLqYwsEIfwshJ";
    private static final OkHttpClient client = new OkHttpClient();

    private static String resolverCaptcha(String url) throws Exception {
        String requestUrl = String.format(
                "http://2captcha.com/in.php?key=%s&method=userrecaptcha&googlekey=%s&pageurl=%s",
                API_KEY, SITE_KEY, url);

        Request request = new Request.Builder()
                .url(requestUrl)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        String captchaId = responseBody.split("\\|")[1];

        String resultUrl = String.format("http://2captcha.com/res.php?key=%s&action=get&id=%s", API_KEY, captchaId);
        String token = null;

        while (token == null) {
            Thread.sleep(10000);
            Request resultRequest = new Request.Builder()
                    .url(resultUrl)
                    .build();

            Response resultResponse = client.newCall(resultRequest).execute();
            String resultBody = resultResponse.body().string();

            if (resultBody.contains("CAPCHA_NOT_READY")) {
                continue;
            }

            token = resultBody.split("\\|")[1];
        }

        return token;
    }

    public static void callResolverAndUpdate(WebDriver driver) throws Exception {
        String token = resolverCaptcha(driver.getCurrentUrl());

        ((JavascriptExecutor) driver).executeScript(
                "document.getElementById('g-recaptcha-response').style.display = 'block';"
        );
        ((JavascriptExecutor) driver).executeScript(
                "document.getElementById('g-recaptcha-response').value = arguments[0];", token
        );

        WebElement recaptcha = driver.findElement(By.id("g-recaptcha-response"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", recaptcha
        );
    }

}
