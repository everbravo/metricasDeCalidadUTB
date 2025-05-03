package com.utb.metrics.common;

public class CommonFunctions {

    public static String generateCellPhone() {
        StringBuilder cellPhone = new StringBuilder("3");
        for (int i = 0; i < 9; i++) {
            int digit = (int) (Math.random() * 10);
            cellPhone.append(digit);
        }
        return cellPhone.toString();
    }

    public static String genDocNumber() {
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

}
