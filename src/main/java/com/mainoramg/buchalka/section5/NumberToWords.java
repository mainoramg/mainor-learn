package com.mainoramg.buchalka.section5;

public class NumberToWords {

    public static void main(String[] args) {
        numberToWords(0); // should print "Zero".
        numberToWords(123); // should print "One Two Three".
        numberToWords(1010); // should print "One Zero One Zero".
        numberToWords(1000); // should print "One Zero Zero Zero".
        numberToWords(-12); // should print "Invalid Value" since the parameter is negative.
        System.out.println(getDigitCount(0)); // should return 1 since there is only 1 digit
        System.out.println(getDigitCount(123)); // should return 3
        System.out.println(getDigitCount(-12)); // should return -1 since the parameter is negative
        System.out.println(getDigitCount(5200)); // should return 4 since there are 4 digits in the number
        System.out.println(reverse(-121)); // should  return -121
        System.out.println(reverse(1212)); // should return  2121
        System.out.println(reverse(1234)); // should return 4321
        System.out.println(reverse(100)); // should return 1
    }

    public static void numberToWords(int number) {
        if (number < 0) {
            System.out.println("Invalid Value");
        } else if (number == 0) {
            System.out.println("Zero");
        } else {
            int reversed = reverse(number);
            for (int n = reversed ;n > 0; n/=10) {
                int lastDigit = n % 10;
                switch (lastDigit) {
                    case 0:
                        System.out.println("Zero");
                        break;
                    case 1:
                        System.out.println("One");
                        break;
                    case 2:
                        System.out.println("Two");
                        break;
                    case 3:
                        System.out.println("Three");
                        break;
                    case 4:
                        System.out.println("Four");
                        break;
                    case 5:
                        System.out.println("Five");
                        break;
                    case 6:
                        System.out.println("Six");
                        break;
                    case 7:
                        System.out.println("Seven");
                        break;
                    case 8:
                        System.out.println("Eight");
                        break;
                    case 9:
                        System.out.println("Nine");
                        break;
                }
            }

            int numberDigitCount = getDigitCount(number);
            int reversedDigitCount = getDigitCount(reversed);
            if (numberDigitCount > reversedDigitCount) {
                for (int i = numberDigitCount - reversedDigitCount; i > 0; i--) {
                    System.out.println("Zero");
                }
            }
        }
    }

    public static int reverse(int number) {
        boolean isNegative = number < 0;
        number = isNegative ? number * -1 : number;

        int reversed = 0;
        for (;number > 0; number/=10) {
            int lastDigit = number % 10;
            reversed = (reversed * 10) + lastDigit;
        }

        return isNegative ? reversed * -1 : reversed;
    }

    public static int getDigitCount(int number) {
        if (number < 0) {
            return -1;
        }
        else if (number == 0) {
            return 1;
        }

        int digitCount = 0;
        for (;number > 0; number/=10,digitCount++);

        return digitCount;
    }
}
