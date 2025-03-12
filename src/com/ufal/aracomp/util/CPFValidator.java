package com.ufal.aracomp.util;

public class CPFValidator {

    public static boolean validade(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

        int sum = 0;

        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }

        int firstCheckDigit = 11 - (sum % 11);

        if (firstCheckDigit >= 10) {
            firstCheckDigit = 0;
        }

        sum = 0;

        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }

        int secondCheckDigit = 11 - (sum % 11);
        if (secondCheckDigit >= 10) {
            secondCheckDigit = 0;
        }

        return cpf.charAt(9) == (char) ('0' + firstCheckDigit) && cpf.charAt(10) == (char) ('0' + secondCheckDigit);
    }

}
