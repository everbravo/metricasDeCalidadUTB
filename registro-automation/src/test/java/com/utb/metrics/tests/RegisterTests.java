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
            .email("bijiwi2308@benznoi.com") //uso https://temp-mail.org/es/
            .cellPhone(CommonFunctions.generateCellPhone())
            .documentNumber(CommonFunctions.genDocNumber())
            .lastName("Bravo")
            .build();

    private final String URL_SITE = "https://www.mesfix.com/users/signup";

    @Test
    public void CP04_registerWithoutAcceptTermsAndConditions() throws Exception {
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
}