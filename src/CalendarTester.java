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
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class CalendarTester {
  public static void main(String[] args) {
    // run all tests
    System.out.print("testGetCentury");
    System.out.println("\t\t\t" + testGetCentury());
    System.out.print("testGetYearWithinCentury");
    System.out.println("\t" + testGetYearWithinCentury());
    System.out.print("testGetIsLeapYear");
    System.out.println("\t\t" + testGetIsLeapYear());
    System.out.print("testGetMonthIndex");
    System.out.println("\t\t" + testGetMonthIndex());
    System.out.print("testGetNumberOfDaysInMonth");
    System.out.println("\t" + testGetNumberOfDaysInMonth());
    System.out.print("testGetFirstDayOfWeekInMonth");
    System.out.println("\t" + testGetFirstDayOfWeekInMonth());
    System.out.print("testGenerateCalendar");
    System.out.println("\t\t" + testGenerateCalendar());
  }

  /**
   * Tests getCentury functionality, but isn't a fully comprehensive test.
   * 
   * @return
   */
  public static boolean testGetCentury() {
    if (CalendarPrinter.getCentury("2") != 0) { // test zero
      return false;
    }
    if (CalendarPrinter.getCentury("2019") != 20) { // test low year
      return false;
    }
    if (CalendarPrinter.getCentury("44444") != 444) { // test high year
      return false;
    }
    return true;
  }

  /**
   * Tests getYearWithinCentury functionality, but isn't a fully comprehensive test.
   * 
   * @return
   */
  public static boolean testGetYearWithinCentury() {
    if (CalendarPrinter.getYearWithinCentury("2000") != 0) { // test edge
      return false;
    }
    if (CalendarPrinter.getYearWithinCentury("1901") != 1) { // test edge
      return false;
    }
    if (CalendarPrinter.getYearWithinCentury("1810") != 10) { // test regular year
      return false;
    }
    return true;
  }

  /**
   * Tests getIsLeapYear functionality, but isn't a fully comprehensive test.
   * 
   * @return
   */
  public static boolean testGetIsLeapYear() {
    if (CalendarPrinter.getIsLeapYear("2036") != true) { // test leap year
      return false;
    }
    if (CalendarPrinter.getIsLeapYear("2400") != true) { // test leap yaer evenly divisible by 100
      return false;
    }
    if (CalendarPrinter.getIsLeapYear("1805") != false) { // test non-leap year
      return false;
    }
    if (CalendarPrinter.getIsLeapYear("1900") != false) { // test non-leap year evenly divisible by
                                                          // 4
      return false;
    }
    return true;
  }

  /**
   * Tests getMonthIndex functionality, but isn't a fully comprehensive test.
   * 
   * @return
   */
  public static boolean testGetMonthIndex() {
    if (CalendarPrinter.getMonthIndex("jan") != 0) { // test edge
      return false;
    }
    if (CalendarPrinter.getMonthIndex("dec") != 11) { // test edge
      return false;
    }
    if (CalendarPrinter.getMonthIndex("JaN") != 0) { // test capitalization
      return false;
    }
    if (CalendarPrinter.getMonthIndex("january") != 0) { // test length (only first 3 letters should
                                                         // count)
      return false;
    }
    if (CalendarPrinter.getMonthIndex("janRAMDOMTEXT") != 0) { // test length (only first 3 letters
                                                               // should count)
      return false;
    }
    return true;
  }

  /**
   * Tests getNumberOfDaysInMonth functionality, but isn't a fully comprehensive test.
   * 
   * @return
   */
  public static boolean testGetNumberOfDaysInMonth() {
    if (CalendarPrinter.getNumberOfDaysInMonth("jul", "2019") != 31) // test regular month
      return false;
    if (CalendarPrinter.getNumberOfDaysInMonth("feb", "2036") != 29) // test leap year feb
      return false;
    if (CalendarPrinter.getNumberOfDaysInMonth("feb", "2019") != 28) // test non-leap year feb
      return false;
    return true;
  }

  /**
   * Tests getFirstDayOfWeekInMonth functionality, but isn't a fully comprehensive test.
   * 
   * @return
   */
  public static boolean testGetFirstDayOfWeekInMonth() {
    if (CalendarPrinter.getFirstDayOfWeekInMonth("sep", "2019") != 6) // test according to calendar
      return false;
    if (CalendarPrinter.getFirstDayOfWeekInMonth("aug", "2019") != 3) // test according to calendar
      return false;
    if (CalendarPrinter.getFirstDayOfWeekInMonth("sep", "1939") != 4) // test different century
      return false;
    return true;
  }

  /**
   * Tests generateCalendar functionality, but isn't a fully comprehensive test.
   * 
   * @return
   */
  public static boolean testGenerateCalendar() {
    String[][] arr = CalendarPrinter.generateCalendar("March", "2015"); // starts on sunday w/ 31
                                                                        // days, so will need 6 rows
    if (arr.length != 7) // Should have 6 rows of numbers + header
      return false;
    if (!arr[1][6].replaceAll("\\s", "").equals("1")) { // First day should be sunday
      return false;
    }
    arr = CalendarPrinter.generateCalendar("Feb", "2016"); // starts on monday w/ 29 days, so will
                                                           // need 5 rows
    if (arr.length != 6) // Should have 5 rows of numbers + header
      return false;
    if (!arr[1][0].replaceAll("\\s", "").equals("1")) { // First day should be monday
      return false;
    }
    return true;
  }
}
