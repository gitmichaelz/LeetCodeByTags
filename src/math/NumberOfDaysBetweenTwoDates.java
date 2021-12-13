package math;

/**
 * Write a program to count the number of days between two dates.
 *
 * The two dates are given as strings, their format is YYYY-MM-DD as shown in the examples.
 *
 *
 *
 * Example 1:
 *
 * Input: date1 = "2019-06-29", date2 = "2019-06-30"
 * Output: 1
 */
public class NumberOfDaysBetweenTwoDates {
    public int daysBetweenDates(String date1, String date2) {
        return Math.abs(countSince1971(date1) - countSince1971(date2));
    }

    public int countSince1971(String date) {
        int[] monthDays = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] data = date.split("-");

        int year = Integer.parseInt(data[0]);
        int month = Integer.parseInt(data[1]);
        int day = Integer.parseInt(data[2]);

        for (int i = 1971; i < year; i++) {//any year, 1900, 1950...
            day += isALeapYear(i) ? 366 : 365;
        }
        for (int i = 1; i < month; i++) {
            if (isALeapYear(year) && i == 2) {
                day += 1;
            }
            day += monthDays[i];
        }
        return day;
    }

    public boolean isALeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
