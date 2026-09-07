class Solution {

    private boolean isLeap(int year) {
        return (year % 400 == 0) || 
               (year % 4 == 0 && year % 100 != 0);
    }

    public String dayOfTheWeek(int day, int month, int year) {

        String[] days = {
            "Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday"
        };

        int[] monthDays = {
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };

        int totalDays = 0;

        // Days in complete years from 1971
        for (int y = 1971; y < year; y++) {
            totalDays += isLeap(y) ? 366 : 365;
        }

        // Days in complete months of the current year
        for (int m = 1; m < month; m++) {
            totalDays += monthDays[m - 1];

            if (m == 2 && isLeap(year)) {
                totalDays++;
            }
        }

        // Days elapsed in the current month
        totalDays += day - 1;

        // Jan 1, 1971 was Friday -> index 5
        return days[(5 + totalDays) % 7];
    }
}