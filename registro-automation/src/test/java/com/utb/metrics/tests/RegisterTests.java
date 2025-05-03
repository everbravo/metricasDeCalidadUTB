package com.utb.metrics.tests;

import com.utb.metrics.base.BaseTest;
import com.utb.metrics.common.CommonFunctions;
import com.utb.metrics.domainObjects.RegisterDomain;
import com.utb.metrics.pages.RegisterPage;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegisterTests extends BaseTest {

    private final RegisterDomain registerDomain = RegisterDomain.builder()
            .name("Ever")
            .email(CommonFunctions.generateRandomEmail())
            .cellPhone(CommonFunctions.generateCellPhone())
            .documentNumber(CommonFunctions.generateDocNumber())
            .lastName("Bravo")
            .build();

    private final String URL_SITE = "https://www.mesfix.com/users/signup";

    @Test
    public void CP04_registerWithoutAcceptTermsAndConditions() {
        driver.get(URL_SITE);
        RegisterPage reg = new RegisterPage(driver);
        reg.completeForm(registerDomain);
        reg.sendForm();

        assertTrue(reg.isCaptchaErrorToastVisible());
    }

    @Test
    public void CP01_validRegister() throws Exception {
        driver.get(URL_SITE);
        RegisterPage reg = new RegisterPage(driver);
        reg.completeForm(registerDomain);
        reg.resolveCaptcha();
        reg.sendForm();

        assertFalse(reg.isCaptchaErrorToastVisible());
    }

    @Test
    public void CP02_invalidEmail() {
        driver.get(URL_SITE);
        RegisterPage reg = new RegisterPage(driver);
        reg.completeForm(registerDomain.toBuilder()
                .email(registerDomain.getEmail().replace("@", ""))
                .build());
        reg.sendForm();

        assertTrue(reg.isInvalidEmailErrorToastVisible());
    }

    @Test
    public void CP03_invalidPassword() throws Exception {
        driver.get(URL_SITE);
        RegisterPage reg = new RegisterPage(driver);
        reg.completeForm(registerDomain);
        reg.resolveCaptcha();
        reg.sendForm();
        reg.completePasswordForm("password12+"); // condiciones correctas: 8 carácteres, una mayúscula, un número y un carácter especial.

        assertTrue(reg.isPasswordFormInvalid());
    }
}