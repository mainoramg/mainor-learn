package com.mainor.learn;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
//        System.out.println( "Test for lastFour(456789, 1254): " + PlayingWithString.lastFour("456789", "1254") );
//        System.out.println( "Test for lastFour(456789, ): " + PlayingWithString.lastFour("456789", "") );
//        System.out.println( "Test for lastFour(456789, null): " + PlayingWithString.lastFour("456789", null) );

//        PlayingWithEnum test = new PlayingWithEnum();
//        System.out.println( "isVoid true: " + test.letsChangeTheType(true, PlayingWithEnum.Type.VOID).toString() );
//        System.out.println( "isVoid false: " + test.letsChangeTheType(false, PlayingWithEnum.Type.VOID).toString() );

//        MapToHtml mapToHtml = new MapToHtml();
//        System.out.println(mapToHtml.mapToHtml(mapToHtml.getData()));

//        String downloads = "[test]";
//        downloads = downloads.substring(1);
//        downloads = downloads.substring(0, downloads.length() - 1);
//        System.out.println("downloads="+downloads);

//        PalyingWithOptional palyingWithOptional = new PalyingWithOptional();
//        palyingWithOptional.mapExample("mainoramg.com.test");
//        palyingWithOptional.mapExample(null);

        PlayingWithDates playingWithDates = new PlayingWithDates();
        String date = "05/02/1970";
        playingWithDates.setLocalDateFromString(date);
        System.out.println("Original date: "+date);
        System.out.println("Month: "+playingWithDates.getMonth());
        System.out.println("Day: "+playingWithDates.getDay());
        System.out.println("Year: "+playingWithDates.getYear());

        int i;

        for (i=0; i<=5; i++) {
            switch (i) {
                case 0:
                    System.out.println("i is less than one");
                case 1:
                    System.out.println("i is less than two");
                case 2:
                    System.out.println("i is less than three");
                    break;
                case 3:
                    System.out.println("i is less than four");
                case 4:
                    System.out.println("i is less than five");
            }
            System.out.println();
        }
    }
}
