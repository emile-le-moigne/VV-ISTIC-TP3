package fr.istic.vv;

class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) throws IllegalArgumentException {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date for " + day + "/" + month + "/" + year);
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (year < 1 || (month < 1 || (month > 12 || day < 1))) {
            return false;
        }
        int lastDayOfMonth = 31;
        if (month == 4 || (month == 6 || (month == 9 || month == 11))) {
            lastDayOfMonth = 30;
        } else if (month == 2) {
            lastDayOfMonth = isLeapYear(year) ? 29 : 28;
        }
        return day <= lastDayOfMonth;
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    public Date nextDate() {
        int nextDay = day;
        int nextMonth = month;
        int nextYear = year;

        int lastDayOfMonth = 31;
        if (nextMonth == 4 || (nextMonth == 6 || (nextMonth == 9 || nextMonth == 11))) {
            lastDayOfMonth = 30;
        } else if (nextMonth == 2) {
            lastDayOfMonth = isLeapYear(nextYear) ? 29 : 28;
        }
        if (nextDay < lastDayOfMonth) {
            nextDay++;
        } else {
            nextDay = 1;
            if (nextMonth < 12) {
                nextMonth++;
            } else {
                nextMonth = 1;
                nextYear++;
            }
        }
        return new Date(nextDay, nextMonth, nextYear);
    }

    public Date previousDate() {
        int previousDay = day - 1;
        int previousMonth = month;
        int previousYear = year;

        if (previousDay == 0) {
            previousMonth -= 1;
            if (previousMonth == 0) {
                previousMonth = 12;
                previousYear -= 1;
            }
            if (previousMonth == 2) {
                if (isLeapYear(previousYear)) {
                    previousDay = 29;
                } else {
                    previousDay = 28;
                }
            } else if (previousMonth == 4 || previousMonth == 6 || previousMonth == 9 || previousMonth == 11) {
                previousDay = 30;
            } else {
                previousDay = 31;
            }
        }

        return new Date(previousDay, previousMonth, previousYear);
    }

    public int compareTo(Date other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare to a null date.");
        }

        if (year > other.year) {
            return 1;
        } else if (year < other.year) {
            return -1;
        } else if (month > other.month) {
            return 1;
        } else if (month < other.month) {
            return -1;
        } else if (day > other.day) {
            return 1;
        } else if (day < other.day) {
            return -1;
        } else {
            return 0;
        }
    }
}
