package com.mainoramg.buchalka.section3;

public class StringImmutable {

    public static void main(String[] args) {
        String str1 = "Hola Lucas";
        String str2 = "Hola Lucas 2";
        String str3 = "Hola Lucas";
        String str4 = "Hola Lucas 2";

        if (str1 == str2) {
            System.out.println("str1 and str2 are equal");
        } else {
            System.out.println("str1 and str2 are not equal");
        }

        if(str1.equals(str2)){
            System.out.println("Equal variables:");
            System.out.println(str1.hashCode() + "\n" + str2.hashCode());
        }

        if (str1 == str3) {
            System.out.println("str1 and str3 are equal");
        } else {
            System.out.println("str1 and str3 are not equal");
        }

        if(str1.equals(str3)){
            System.out.println("Equal variables:");
            System.out.println(str1.hashCode() + "\n" + str3.hashCode());
        }

        if (str2 == str3) {
            System.out.println("str2 and str3 are equal");
        } else {
            System.out.println("str2 and str3 are not equal");
        }

        if (str2 == str4) {
            System.out.println("str2 and str4 are equal");
        } else {
            System.out.println("str2 and str4 are not equal");
        }
    }
}
