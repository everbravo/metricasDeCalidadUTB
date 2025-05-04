package com.utb.metrics.common;

import net.datafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Locale;

public class CommonFunctions {

    private static final Faker faker = new Faker(new Locale("es"));

    public static String generateCellPhone() {
        StringBuilder cellPhone = new StringBuilder("3");
        for (int i = 0; i < 9; i++) {
            int digit = (int) (Math.random() * 10);
            cellPhone.append(digit);
        }
        return cellPhone.toString();
    }

    public static String generateDocNumber() {
        StringBuilder document = new StringBuilder();
        int firstDigit = (int) (Math.random() * 9) + 1;
        document.append(firstDigit);
        for (int i = 0; i < 8; i++) {
            document.append((int) (Math.random() * 10));
        }
        int lastDigit = (int) (Math.random() * 9) + 1;
        document.append(lastDigit);

        return document.toString();
    }

    public static String generateRandomEmail() {
        String local = RandomStringUtils.randomAlphanumeric(10).toLowerCase();
        return local + "@mailinator.com";
    }

    public static String generateRandomCompanyName() {
        return faker.company().name();
    }

    public static String generateRandomFullName() {
        return faker.name().fullName();
    }

    public static String generateRandomName() {
        return faker.name().name();
    }

    public static String generateRandomLastName() {
        return faker.name().lastName();
    }

    public static String generateRandomPassword() {
        return faker.internet().password(8, 16, true, true);
    }

    public static String generateRandomPhone() {
        return faker.phoneNumber().phoneNumberNational();
    }

}
