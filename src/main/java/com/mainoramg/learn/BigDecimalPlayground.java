package com.mainoramg.learn;

import java.math.BigDecimal;

public class BigDecimalPlayground {

    public static void main(String[] args) {
        printBigDecimalsAsStrings();
        compareTwoBigDecimals();
    }

    /**
     * Comparing two Big Decimals
     */
    static void compareTwoBigDecimals() {
        BigDecimal balance = new BigDecimal("22.99");
        System.out.println("balance="+balance);
        final BigDecimal firstChargeAmount = new BigDecimal("2.99");
        final BigDecimal secondChargeAmount = new BigDecimal("19.99");
        System.out.println("charges="+firstChargeAmount.add(secondChargeAmount).toString());

        if (balance.compareTo(firstChargeAmount.add(secondChargeAmount)) < 0) {
            System.out.println("Not enough balance");
        } else {
            System.out.println("All good, a lot of balance, proceed!");
        }
    }

    /**
     * Print Big Decimals as String
     */
    static void printBigDecimalsAsStrings() {
        BigDecimal balance = new BigDecimal("22.99");
        System.out.println("balance="+balance);

        BigDecimal amount = new BigDecimal("18.99");
        String displayAmount = "This is the amount: "+amount;
        System.out.println(displayAmount);
    }
}
