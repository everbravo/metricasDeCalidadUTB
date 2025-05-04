package com.utb.metrics.tests;

import com.utb.metrics.base.BaseTest;
import com.utb.metrics.common.CommonFunctions;
import com.utb.metrics.domainObjects.RegisterDomain;
import com.utb.metrics.pages.RegisterPage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.DEFAULT)
public class RegisterTests extends BaseTest {

    private final RegisterDomain registerDomain = RegisterDomain.builder()
            .name(CommonFunctions.generateRandomName())
            .email(CommonFunctions.generateRandomEmail())
            .cellPhone(CommonFunctions.generateCellPhone())
            .documentNumber(CommonFunctions.generateDocNumber())
            .lastName(CommonFunctions.generateRandomLastName())
            .companyName(CommonFunctions.generateRandomCompanyName())
            .legalAgent(CommonFunctions.generateRandomFullName())
            .password(CommonFunctions.generateRandomPassword())
            .phoneNumber(CommonFunctions.generateRandomPhone())
            .build();

    private final String URL_SITE = "https://www.mesfix.com/users/signup";

    @Test
    public void CP05_emailUserAlreadyExists() throws Exception {
        driver.get(URL_SITE);
        RegisterPage reg = new RegisterPage(driver);
        registerDomain.setEmail("nflorez@gmail.com");
        reg.completeFormNaturalPerson(registerDomain);
        reg.resolveCaptcha();
        reg.sendForm();

        assertTrue(reg.isEmailAlreadyExistErrorToastVisible());
    }

    @Test
    public void CP04_registerWithoutAcceptTermsAndConditions() {
        driver.get(URL_SITE);
        RegisterPage reg = new RegisterPage(driver);
        reg.completeFormNaturalPerson(registerDomain);
        reg.sendForm();

        assertTrue(reg.isCaptchaErrorToastVisible());
    }

    @Test
    public void CP04_validRegister() throws Exception {
        driver.get(URL_SITE);
        RegisterPage reg = new RegisterPage(driver);
        reg.completeFormNaturalPerson(registerDomain);
        reg.resolveCaptcha();
        reg.sendForm();

        assertFalse(reg.isCaptchaErrorToastVisible());
    }

    @Test
    public void CP02_invalidEmail() {
        driver.get(URL_SITE);
        RegisterPage reg = new RegisterPage(driver);
        reg.completeFormNaturalPerson(registerDomain.toBuilder()
                .email(registerDomain.getEmail().replace("@", ""))
                .build());
        reg.sendForm();

        assertTrue(reg.isInvalidEmailErrorToastVisible());
    }

    @Test
    public void CP03_invalidPassword() throws Exception {
        driver.get(URL_SITE);
        RegisterPage reg = new RegisterPage(driver);
        reg.completeFormNaturalPerson(registerDomain);
        reg.resolveCaptcha();
        reg.sendForm();
        reg.completePasswordForm("password12+"); // condiciones correctas: 8 carácteres, una mayúscula, un número y un carácter especial.

        assertTrue(reg.isPasswordFormInvalid());
    }

    @Test
    public void CP01_successfulCompanyRegistration() throws Exception {
        driver.get(URL_SITE);
        RegisterPage reg = new RegisterPage(driver);
        reg.partOneFormLegalPerson(registerDomain);
        reg.resolveCaptcha();
        reg.clickSendButton();

        reg.partTwoFormLegalPerson(registerDomain);
        assertTrue(reg.isWelcomeModalVisible());
    }
}
