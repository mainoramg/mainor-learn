package com.mainoramg.learn;

public class StringPlayground {

    public static void main(String[] args) {
        testingSubstring();
        testingSplit();
        maskCcNumbers();
    }

    /**
     * Mask CC numbers
     */
    static void maskCcNumbers() {
        System.out.println( "Test for lastFour(456789, 1254): " + lastFour("456789", "1254") );
        System.out.println( "Test for lastFour(456789, ): " + lastFour("456789", "") );
        System.out.println( "Test for lastFour(456789, null): " + lastFour("456789", null) );
    }
    public static String lastFour(String bin, String l4) {
        l4 = l4 == null ? "" : l4;
        bin = bin == null ? "" : bin;
        return bin + "################".substring(l4.length() + bin.length()) + l4;
    }

    /**
     * Testing split
     */
    static void testingSplit() {
        String extraKey = "2066460541|test";
        String[] extraKeys = extraKey.split("[\\/\\|]");
        String newRecipientEmail = extraKeys[0];
        System.out.println("new email:"+newRecipientEmail);
    }

    /**
     * Testing substring
     */
    static void testingSubstring() {
        String downloads = "[test]";
        downloads = downloads.substring(1);
        downloads = downloads.substring(0, downloads.length() - 1);
        System.out.println("downloads="+downloads);
    }
}
