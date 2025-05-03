package com.utb.metrics.pages;

import com.utb.metrics.domainObjects.RegisterDomain;
import com.utb.metrics.integrations.CaptchaSolver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }


    public void resolveCaptcha() throws Exception {
        CaptchaSolver.callResolverAndUpdate(driver);
    }

    private void selectRegisterForm() {
        driver.findElement(By.id("naturalPerson")).click();
    }

    public void completeForm(RegisterDomain data) {
        selectRegisterForm();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));

        new Select(driver.findElement(By.name("documentType"))).selectByVisibleText("Cédula de ciudadanía");
        driver.findElement(By.name("documentNumber")).sendKeys(data.getDocumentNumber());
        driver.findElement(By.id("name")).sendKeys(data.getName());
        driver.findElement(By.name("lastName")).sendKeys(data.getLastName());
        driver.findElement(By.name("cellPhone")).sendKeys(data.getCellPhone());
        emailField.sendKeys(data.getEmail());

    }

    public void sendForm() {
        driver.findElement(By.xpath("//button[@type='submit' and contains(text(),'Enviar')]")).click();
    }

    public boolean isCaptchaErrorToastVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='toast-container']//div[contains(@class, 'toast-error')]//div[contains(text(), 'Debe validar el captcha')]")
            ));
            return toast.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isInvalidEmailErrorToastVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='toast-container']//div[contains(@class, 'toast-error')]//div[contains(text(), 'Debes ingresar un correo válido')]")
            ));
            return toast.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void completePasswordForm(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

        passwordField.sendKeys(password);
        driver.findElement(By.name("confirmPassword")).sendKeys(password);
    }

    public boolean isPasswordFormInvalid() {
        WebElement continuarButton = driver.findElement(By.xpath("//div[contains(text(),'Continuar')]"));

        return continuarButton.getAttribute("class").contains("btn-rounded-disabled");
    }
}