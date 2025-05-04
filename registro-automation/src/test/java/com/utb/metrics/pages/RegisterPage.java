package com.utb.metrics.pages;

import com.utb.metrics.common.CommonFunctions;
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
import java.util.List;

public class RegisterPage {

    private final WebDriver driver;
    private final By nextButtonOne = By.xpath("//div[contains(@class, 'btn-rounded') and @data-param='one' and contains(text(), 'Continuar')]");
    private final By nextButtonTwo = By.xpath("//div[contains(@class, 'btn-rounded') and @data-param='two' and contains(text(), 'Continuar')]");
    private final By nextButtonThree = By.xpath("//div[contains(@class, 'btn-rounded') and @data-param='three' and contains(text(), 'Continuar')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }


    public void resolveCaptcha() throws Exception {
        CaptchaSolver.callResolverAndUpdate(driver);
    }

    private void selectRegisterForm() {
        driver.findElement(By.id("naturalPerson")).click();
    }

    private void selectRegisterFormLegalPerson() {
        driver.findElement(By.id("legalPerson")).click();
    }

    public void completeFormNaturalPerson(RegisterDomain data) {
        selectRegisterForm();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));

        new Select(driver.findElement(By.name("documentType"))).selectByVisibleText("Cédula de ciudadanía");
        driver.findElement(By.name("documentNumber")).sendKeys(data.getDocumentNumber());
        driver.findElement(By.id("name")).sendKeys(data.getName());
        driver.findElement(By.name("lastName")).sendKeys(data.getLastName());
        driver.findElement(By.name("cellPhone")).sendKeys(data.getCellPhone());
        emailField.sendKeys(data.getEmail());
    }

    public void partOneFormLegalPerson(RegisterDomain data) {
        selectRegisterFormLegalPerson();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));

        clickRegisterFormLegalPersonIntention();

        driver.findElement(By.name("documentNumber")).sendKeys(data.getDocumentNumber());
        driver.findElement(By.name("cellPhone")).sendKeys(data.getCellPhone());
        emailField.sendKeys(data.getEmail());
    }

    public void partTwoFormLegalPerson(RegisterDomain data) {
        completePasswordForm(data.getPassword());
        nextButton(nextButtonOne);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement socioeconomicTypeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("socioeconomicType")));

        driver.findElement(By.name("names")).sendKeys(data.getName());
        driver.findElement(By.name("lastNames")).sendKeys(data.getLastName());
        new Select(driver.findElement(By.name("genderType"))).selectByVisibleText("Masculino");
        new Select(driver.findElement(By.name("documentType"))).selectByVisibleText("Cédula de ciudadanía");
        driver.findElement(By.name("documentNumber")).sendKeys(CommonFunctions.generateDocNumber());
        new Select(driver.findElement(By.name("documentRegion"))).selectByVisibleText("ANTIOQUIA");
        new Select(driver.findElement(By.name("documentCity"))).selectByVisibleText("MEDELLIN");
        driver.findElement(By.name("birthDate")).sendKeys("01/01/1990");
        new Select(driver.findElement(By.name("firstAddress"))).selectByVisibleText("CARRERA");
        driver.findElement(By.name("secondAddress")).sendKeys("100");
        driver.findElement(By.name("thirdAddress")).sendKeys("50");
        driver.findElement(By.name("fourthAddress")).sendKeys("25");
        new Select(driver.findElement(By.name("addressRegion"))).selectByVisibleText("ANTIOQUIA");
        new Select(driver.findElement(By.name("addressCity"))).selectByVisibleText("MEDELLIN");
        socioeconomicTypeField.sendKeys("5");

        nextButton(nextButtonTwo);
        WebElement anualSaleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("anualSale")));
        new Select(driver.findElement(By.name("firstAddress"))).selectByVisibleText("CARRERA");
        driver.findElement(By.name("secondAddress")).sendKeys("100");
        driver.findElement(By.name("thirdAddress")).sendKeys("50");
        driver.findElement(By.name("fourthAddress")).sendKeys("25");
        new Select(driver.findElement(By.name("entityRegion"))).selectByVisibleText("ANTIOQUIA");
        new Select(driver.findElement(By.name("entityCity"))).selectByVisibleText("MEDELLIN");
        driver.findElement(By.name("entityPhone")).sendKeys(data.getPhoneNumber());
        driver.findElement(By.name("entityName")).sendKeys(data.getCompanyName());
        driver.findElement(By.name("employees")).sendKeys("100");
        new Select(driver.findElement(By.name("sector"))).selectByVisibleText("TECNOLOGÍA");
        anualSaleField.sendKeys("Menos de $1.000.000.000");

        nextButton(nextButtonThree);
        WebElement occupationField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("occupation")));
        new Select(driver.findElement(By.name("source"))).selectByVisibleText("Charlas o conversatorios");
        new Select(driver.findElement(By.name("profession"))).selectByVisibleText("Ingeniería y afines");
        occupationField.sendKeys("Gerente financiero");

        nextButton(nextButtonThree);
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

        passwordField.sendKeys(password);
        driver.findElement(By.name("confirmPassword")).sendKeys(password);
    }

    public boolean isPasswordFormInvalid() {
        WebElement continuarButton = driver.findElement(By.xpath("//div[contains(text(),'Continuar')]"));

        return continuarButton.getAttribute("class").contains("btn-rounded-disabled");
    }

    public boolean isEmailAlreadyExistErrorToastVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='toast-container']//div[contains(@class, 'toast-error')]//div[contains(text(), " +
                            "'Hay un problema con tu registro, por favor contacte con soporte@plataform.com.')]")
            ));
            return toast.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    private void clickRegisterFormLegalPersonIntention() {
        List<WebElement> radioButtons = driver.findElements(By.name("intention"));
        radioButtons.getFirst().click();
    }

    public void clickSendButton() {
        sendForm();
    }

    public void nextButton(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public boolean isWelcomeModalVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("https://www.mesfix.com/ms/general/profileAccount"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
