import java.util.Scanner;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P01 Calendar Printer
// Files: (a list of all source files used by that program)
// Course: CS 300 Term 1 Year 1
//
// Author: Justin Chan
// Email: jacahn@gmail.com
// Lecturer's Name: Mouna Ayari Ben Hadj Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources:
//
// Project PDF
// http://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2019/09/P01CalendarPrinter.pdf
//
// Class String Documentation
// https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#substring(int)
//
// Wikipedia
// https://en.wikipedia.org/wiki/Gregorian_calendar#Months
// https://en.wikipedia.org/wiki/Zeller%27s_congruence#Implementation_in_software
// https://en.wikipedia.org/wiki/Leap_year#Algorithm
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class CalendarPrinter {

  private final static String[] DAYS_OF_WEEK = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
  private final static String[] MONTHS_OF_YEAR =
      {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
  private final static int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    boolean on = true;
    while (on) {
      System.out.println("Welcome to Calendar Printer.\n============================");
      System.out.println("Enter the month to print: ");
      String month = sc.nextLine();
      System.out.println("Enter the year to print: ");
      String year = sc.nextLine();
      String[][] cal = generateCalendar(month, year);
      for (int i = 0; i < cal.length; i++) {
        for (int j = 0; j < cal[0].length; j++) {
          System.out.print(cal[i][j]);
        }
      }
      System.out.println("============================\n");
      while (true) {
        System.out.println("Again? (Y/N)");
        month = sc.next();
        sc.nextLine();
        if (month.equalsIgnoreCase("y"))
          break;
        else if (month.equalsIgnoreCase("n")) {
          on = false;
          break;
        }
      }
    }

    sc.close();
  }

  /**
   * Calculates the number of centuries (rounded down) that is represented by the specified year
   * (ie. the integer part of year/100).
   * 
   * @param year to compute the century of (based on the Gregorian Calendar AD) String must contain
   *             the digits of a single non-negative int for year.
   * @return number of centuries in the specified year
   */
  public static int getCentury(String year) {
    return Integer.parseInt(year) / 100;
  }

  /**
   * Calculates the number of years between the specified year, and the first year in the specified
   * year's century. This number is always between 0 - 99. Not accurate to definition of century of
   * gregorian calendar. Simply year % 100.
   * 
   * @param year to compute the year within century of (Gregorian Calendar AD) String must contain
   *             the digits of a single non-negative int for year.
   * @return number of years since first year in the current century
   */
  public static int getYearWithinCentury(String year) {
    // Correct to gregorian calendar implementation
    // Centuries start the first day of the year 01; so the 21st century starys on Jan 1, 2001.
    // int foo = Integer.parseInt(year);
    // if (foo % 100 == 0)
    // return 99;
    // return foo % 100 - 1;
    return Integer.parseInt(year) % 100;
  }

  /**
   * This method computes whether the specified year is a leap year or not.
   * 
   * @param yearString is the year that is being checked for leap-year-ness String must contain the
   *                   digits of a single non-negative int for year.
   * @return true when the specified year is a leap year, and false otherwise
   */
  public static boolean getIsLeapYear(String yearString) {
    int foo = Integer.parseInt(yearString);
    if (foo % 100 == 0) {
      if (foo % 400 == 0)
        return true;
      return false;
    }
    if (foo % 4 == 0)
      return true;
    return false;
  }

  /**
   * Converts the name or abbreviation for any month into the index of that month's abbreviation
   * within MONTHS_OF_YEAR. Matches the specified month based only on the first three characters,
   * and is case in-sensitive.
   * 
   * @param month which may or may not be abbreviated to 3 or more characters
   * @return the index within MONTHS_OF_YEAR that a match is found at and returns -1, when no match
   *         is found
   */
  public static int getMonthIndex(String month) {
    month = month.substring(0, 3);
    for (int i = 0; i < MONTHS_OF_YEAR.length; i++) {
      if (month.equalsIgnoreCase(MONTHS_OF_YEAR[i]))
        return i;
    }
    return -1;
  }

  /**
   * Calculates the number of days in the specified month, while taking into consideration whether
   * or not the specified year is a leap year.
   * 
   * @param month which may or may not be abbreviated to 3 or more characters
   * @param year  of month that days are being counted for (Gregorian Calendar AD) String must
   *              contain the digits of a single non-negative int for year.
   * @return the number of days in the specified month (between 28-31)
   */
  public static int getNumberOfDaysInMonth(String month, String year) {
    if (getIsLeapYear(year) && getMonthIndex(month) == 1)
      return 29;
    return DAYS_IN_MONTH[getMonthIndex(month)];
  }

  /**
   * Calculates the index of the first day of the week in a specified month. The index returned
   * corresponds to position of this first day of the week within the DAYS_OF_WEEK class field.
   * 
   * @param month which may or may not be abbreviated to 3 or more characters
   * @param year  of month to determine the first day from (Gregorian Calendar AD) String must
   *              contain the digits of a single non-negative int for year.
   * @return index within DAYS_OF_WEEK of specified month's first day
   */
  public static int getFirstDayOfWeekInMonth(String month, String year) {
    int q = 1;
    int k = getYearWithinCentury(year);
    int j = getCentury(year);

    int m = getMonthIndex(month) + 1;
    if (m == 1) { // If jan, m = 13
      m = 13;
      k--;
    }
    if (m == 2) { // If feb, m = 14
      m = 14;
      k--;
    }

    return (int) (((q + ((13 * (m + 1) / 5) + k + k / 4 + 5 * j + j / 4)) % 7) + 5) % 7;
  }

  /**
   * Creates and initializes a 2D String array to reflect the specified month. The first row of this
   * array [0] should contain labels representing the days of the week, starting with Monday, as
   * abbreviated in DAYS_OF_WEEK. Every later row should contain dates under the corresponding days
   * of week. Entries with no corresponding date in the current month should be filled with a single
   * period. There should not be any extra rows that are either blank, unused, or completely filled
   * with periods. For example, the contents for September of 2019 should look as follows, where
   * each horizontal row is stored in different array within the 2d result:
   *
   * MON TUE WED THU FRI SAT SUN . . . . . . 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21
   * 22 23 24 25 26 27 28 29 30 . . . . . .
   *
   * @param month which may or may not be abbreviated to 3 or more characters
   * @param year  of month generate calendar for (Gregorian Calendar AD) String must contain the
   *              digits of a single non-negative int for year.
   * @return 2d array of strings depicting the contents of a calendar
   */
  public static String[][] generateCalendar(String month, String year) {
    int fd = getFirstDayOfWeekInMonth(month, year); // First Day
    int ld = getNumberOfDaysInMonth(month, year); // Last Day

    String[][] cal = new String[2 + (ld + fd) / 7][7];

    // Setup Header
    cal[0] = DAYS_OF_WEEK.clone();
    for (int i = 0; i < 7; i++) {
      cal[0][i] = " " + cal[0][i] + " ";
    }
    cal[0][6] += "\n";

    int curDay = 1;

    for (int i = 1; i < cal.length; i++) {
      for (int j = 0; j < cal[0].length; j++) {
        // if (curDay > ld)
        // break;
        if (((i - 1) * 7 + j >= fd) && ((i - 1) * 7 + j < fd + ld)) {
          if (curDay < 10)
            cal[i][j] = "  " + curDay + "  ";
          else
            cal[i][j] = "  " + curDay + " ";
          curDay++;
        } else
          cal[i][j] = "  .  ";
      }
      cal[i][6] += "\n";
    }

    return cal;
  }
}
