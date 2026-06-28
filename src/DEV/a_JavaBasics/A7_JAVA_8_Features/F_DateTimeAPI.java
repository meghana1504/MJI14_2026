package DEV.a_JavaBasics.A7_JAVA_8_Features;
import java.time.LocalDate;

// Good article : https://medium.com/munchy-bytes/introduction-to-date-time-api-in-java-8-574a8df34912
//References
//https://www.baeldung.com/java-8-date-time-intro

public class F_DateTimeAPI {
    public static void main(String[] args) {
    /*
    * Working with dates and time is a common task in the world of Software development
    * Building a financial application, or e-commerce platform, or simply needing to record timestamps,
      the need for an easy-to-use Date and Time API is absolutely crucial
    * Java 8 introduced a powerful Date/Time API within java.time package to handle date and time-related operations
    *
    * */

    /*
     * LocalDate class
     * The LocalDate class represents a date without a timezone in the format yyyy-mm-dd.
     * */
        LocalDate today = LocalDate.now();
        System.out.println("Today's date is "+today);

    // By using of or parse method we can create an instance of a LocalDate for a specific date
    LocalDate birthday = LocalDate.of(2026,04, 15);
    System.out.println("wish me on "+birthday);
    LocalDate joining = LocalDate.parse("2021-07-26");
    System.out.println("My joining date in Dassault was : "+joining);

    // We can add/subtract days, months, and years from an instance of LocalDate.

    // We can derive the day of the week, day, month, and year from an instance of LocalDate.

    // We can check if a particular year is a leap year by doing the following.

    // Comparison of 2 dates using isBefore() and isAfter()

    /*
    LocalTime class
    The LocalTime class represents time without a date.
    *
    */

    // An instance of the current time can be created using the LocalTime.now() method.

    // By using of or parse method we can create an instance of a LocalTime for a specific time.

    // Similar to LocalDate, we can add/subtract minutes, seconds, and hours from an instance of a LocalTime.

    // We can obtain seconds, minutes, and hours from a specific instance of a LocalTime.

    // Comparison of 2 times using isBefore() and isAfter()


        /*
        LocalDateTime class
        LocalDateTime class is used to represent date and time without a time zone.
        * */

        // Similar ops as LocalDate and LocalTime

        /*
        ZonedDateTime class
        ZonedDateTime is provided by Java8 to handle time-zone-specific dates and times.
        * */

        /*
        * MORE CLASSES
        *
        * Period -  The Period class is used to modify the values of a given date and to get the difference between to given class.
        *
        * Duration Class - Similar to Period Class, Duration Class is used to modify the values of a given time or to find the difference between two times
        *
        *
        * */

        /*
        * Temporal Adjusters
        * Temporal adjusters are useful when carrying out diverse calculations involving dates and times
        * We can use them to manipulate temporal objects effectively and address queries such as discovering the first day of the Month or finding the next Sunday of the month etc
         * */

    // WE WILL CONTINUE CODING LATER, JUST GO THROUGH THE ARTICLE DIRECTLY FOR THE REVISION


    }
}


/*
FULL CONTENT OF THE ARTICLE COPY PASTED BELOW

LocalDate class
The LocalDate class represents a date without a timezone in the format yyyy-mm-dd.

An instance of the current date can be created using the LocalDate.now() method.

LocalDate today = LocalDate.now();   // 2023-08-28
By using of or parse method we can create an instance of a LocalDate for a specific date.

LocalDate localDate = LocalDate.of(2023, 08, 31);  // 2023-08-31

LocalDate localDate = LocalDate.parse("2023-09-20"); // 2023-09-20
We can add/subtract days, months, and years from an instance of LocalDate.

LocalDate yesterday = LocalDate.now().minusDays(1);     // 2023-08-27
LocalDate tomorrow  = LocalDate.now().plusDays(1);    // 2023-08-29

LocalDate lastMonth = LocalDate.now().minusMonths(1);  //2023-07-28
LocalDate nextMonth = LocalDate.now().plusMonths(1);   //2023-09-28

LocalDate lastYear  = LocalDate.now().minusYears(1);  //2022-08-28
LocalDate nextYear  = LocalDate.now().plusYears(1);   //2024-08-28
We can derive the day of the week, day, month, and year from an instance of LocalDate.

DayOfWeek day = LocalDate.now().getDayOfWeek();   //MONDAY


int day     = LocalDate.parse("2023-01-12").getDayOfMonth();   // 12
Month month = LocalDate.parse("2023-01-12").getMonth();        // JANUARY
int year    = LocalDate.parse("2023-01-12").getYear();         // 2023
We can check if a particular year is a leap year by doing the following.

boolean isLeap  = LocalDate.now().isLeapYear();                 //false
boolean isLeap1 = LocalDate.parse("2024-01-12").isLeapYear();   //true
Comparison of two dates can be done as follows.

// create local dates
LocalDate date1 = LocalDate.parse("2022-08-12");
LocalDate date2 = LocalDate.parse("2023-01-09");

// check if date1 is before date2
boolean isBefore = date1.isBefore(date2);   //true

// check if date1 is after date2
boolean isAfter = date1.isAfter(date2);     //false
LocalTime class
The LocalTime class represents time without a date.

An instance of the current time can be created using the LocalTime.now() method.

LocalTime now = LocalTime.now();       // 14:07:12.503202341
By using of or parse method we can create an instance of a LocalTime for a specific time.

LocalTime eightFifteen = LocalTime.parse("08:15");  // 08:15
LocalTime eightFifty   = LocalTime.of(8, 50);       // 08:50
Similar to LocalDate, we can add/subtract minutes, seconds, and hours from an instance of a LocalTime.

LocalTime timeInOneHour   = LocalTime.now().plus(1, ChronoUnit.HOURS);  //15:16:05.836826479
LocalTime timeInOneMinute = LocalTime.now().plusMinutes(1);             //14:17:05.836826479
We can obtain seconds, minutes, and hours from a specific instance of a LocalTime.

//Local Time : 14:21:22.383214776

int hour = LocalTime.now().getHour();        //14
int minutes = LocalTime.now().getMinute();   //21
int seconds = LocalTime.now().getSecond();`  //22
We can compare two times as follows.

LocalTime time1 = LocalTime.parse("02:23");
LocalTime time2 = LocalTime.parse("23:13");

// check if time1 is before time2
boolean isBefore = time1.isBefore(time2);   // true

// check if time1 is after time2
boolean isAfter = time1.isAfter(time2);    // false
LocalDateTime class
LocalDateTime class is used to represent date and time without a time zone.

Similar operations as LocalDate and LocalTime can be performed for this class too.

//Get the current date and time

LocalDateTime now = LocalDateTime.now(); //2023-08-28T14:31:21.433439004

//Create an instance using of and parse

LocalDateTime parseDateTime = LocalDateTime.parse("2023-09-29T15:20");  //2023-09-29T15:20

LocalDateTime givenDateTime = LocalDateTime.of(2023, Month.SEPTEMBER, 4, 02, 02); //2023-09-04T02:02
We can add specific time and date units like hours, minutes, seconds, months, years, and days using plus and minus methods.

LocalDateTime yesterday         = LocalDateTime.now().plusDays(1);
LocalDateTime timebeforeOneHour = LocalDateTime.now().minusHours(1);
We can get the specific day, month, year, second, minute, hour from an instance of a LocalDateTime class.

 //LocalDateTime : 2023-08-28T14:40:11.302826996

int hour     = LocalDateTime.now().getHour();          // 14
int minutes  = LocalDateTime.now().getMinute();        // 40
int seconds  = LocalDateTime.now().getSecond();        // 11

int day      = LocalDateTime.now().getDayOfMonth();    // 28
Month month  = LocalDateTime.now().getMonth();         // AUGUST
int year     = LocalDateTime.now().getYear();          // 2023
We can also compare two LocalDateTime instances using isAfter and isBefore methods.

ZonedDateTime class
ZonedDateTime is provided by Java8 to handle time-zone-specific dates and times.

Get Mohammadhu Faalil’s stories in your inbox
Join Medium for free to get updates from this writer.

Enter your email
Subscribe

Remember me for faster sign in

We can get the available ZoneIds as follows.

Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
The zone Ids would be as Asia/Aden, America/Cuiaba, etc.

We can convert the LocalDateTime to a specific zone.

LocalDateTime now = LocalDateTime.now(); //2023-08-28T15:00:30.487070279

ZonedDateTime zonedDT = ZonedDateTime.of(now, ZoneId.of("Africa/Nairobi"));  //2023-08-28T15:00:30.487070279+03:00[Africa/Nairobi]
The ZonedDateTime also provides the parse method to get time-zone-specific date-time.

ZonedDateTime zonedDT = ZonedDateTime.parse("2019-12-20T10:15:30+05:00[Asia/Karachi]");
Similar to the previously mentioned classes, we can execute plus/minus operations, get different time and date units, and compare two instances using the ZonedDateTime class as well.

Period Class
The Period class is used to modify the values of a given date and to get the difference between to given class.

The following code illustrates how you can manipulate an instance of LocalDate:

// Current Local Date : 2023-08-28

// Add a period of 6 days
LocalDate futureDate = LocalDate.now().plus(Period.ofDays(6));  // 2023-09-03

// Subtract a period of 2 weeeks
LocalDate pastDate = LocalDate.now().minus(Period.ofWeeks(2));  //2023-08-14

// Add period of 2 months and 15 days
LocalDate specificDate = LocalDate.now().plus(Period.ofMonths(2).plusDays(15)); //2023-11-12
We can get the difference between two dates in different date units using Period class.

LocalDate date1 = LocalDate.of(2022, Month.AUGUST, 12);   // 2022-08-12
LocalDate date2 = LocalDate.of(2023, Month.DECEMBER, 02); // 2023-12-02

// Difference between two dates
Period period = Period.between(date1, date2);    //P1Y3M20D


int days = period.getDays();       // 20
int months = period.getMonths();   // 3
int years = period.getYears();     // 1
boolean isNegative = period.isNegative(); // false
boolean isZero = period.isZero();         // false
P1Y3M20D represents a Period of 1 Year, 3 Months, and 20 Days.

We can use the ChronoUnit class to obtain the difference between two dates in a specific unit such as day, month, or year.

long days = ChronoUnit.DAYS.between(date1, date2);     // 477
long months = ChronoUnit.MONTHS.between(date1, date2); // 15
long years = ChronoUnit.YEARS.between(date1, date2);   // 1
Duration Class
Similar to Period Class, Duration Class is used to modify the values of a given time or to find the difference between two times.

// Current Local Time: 15:39:40.053910730

// Add a duration of 8 hours
LocalTime futureTime = LocalTime.now().plus(Duration.ofHours(8));  //23:39:40.063033292

// Subtract 1 day
LocalTime pastTime = LocalTime.now().minus(Duration.ofDays(1));   //15:39:40.063269984

// Add 4 Days and 3 hours
LocalTime specificTime = LocalTime.now().plus(Duration.ofDays(4).plusHours(3)); //15:39:40.063269984
We can get the duration between two given times as follows.

LocalTime time1   = LocalTime.of(10, 30); // 10:30
LocalTime time2   = LocalTime.of(12, 28); // 12:28

// get the difference between times
Duration duration = Duration.between(time1, time2);  //PT1H58M

long seconds = duration.getSeconds(); // 7080
long minutes = duration.toMinutes();  // 118
long hours = duration.toHours();      // 1
We can use the ChronoUnit class to obtain the difference between two times in a specific unit such as seconds, minutes, or hours.

long seconds = ChronoUnit.SECONDS.between(time1, time2); // 7080
long minutes = ChronoUnit.MINUTES.between(time1, time2); // 118
long hours   = ChronoUnit.HOURS.between(time1, time2); // 1
Temporal Adjusters
Temporal adjusters are useful when carrying out diverse calculations involving dates and times. We can use them to manipulate temporal objects effectively and address queries such as discovering the first day of the Month or finding the next Sunday of the month etc.

LocalDate givenDate= LocalDate.parse("2023-08-28")

givenDate.with(TemporalAdjusters.lastDayOfMonth()) //2023-08-31
givenDate.with(TemporalAdjusters.firstDayOfMonth()) //2023-08-01
givenDate.with(TemporalAdjusters.firstDayOfNextMonth()) //2023-09-01
Some of the other Temporal Adjusters are :

next(DayOfWeek.TUESDAY)
dayOfWeekInMonth
firstDayOfNextYear
firstDayOfYear
lastDayOfNextMonth
lastDayOfNextYear
lastDayOfYear
Summary
In this blog article, we’ve taken an in-depth journey through Java 8’s Date and Time API, along with detailed examples and thorough explanations. I hope that you’ve acquired a solid understanding of this essential concept and will confidently tackle the challenges related to date and time operations.

Your feedback is invaluable to us. Please feel free to share your thoughts, questions, and insights. We appreciate your engagement and look forward to assisting you on your programming journey.

Happy coding!


LocalDateTime class is used to represent date and time without a time zone.

LocalDate yesterday = LocalDate.now().plusDays(1); // 2023-08-27
LocalDate tomorrow = LocalDate.now().minusDays(1); // 2023-08-29
plusDays(1) represents tomorrow not yesterday ..similarly minusDays(1) represents yesterday not tomorrow. Please correct it..
1



* */