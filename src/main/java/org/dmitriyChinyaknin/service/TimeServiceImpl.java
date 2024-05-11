package org.dmitriyChinyaknin.service;

import org.dmitriyChinyaknin.exception.UncorrectedDate;
import org.dmitriyChinyaknin.exception.UncorrectedFormatDate;
import org.dmitriyChinyaknin.exception.UncorrectedLengthDate;

public class TimeServiceImpl implements TimeService {

    private final int DATE_LENGTH = 10;

    public boolean isHolidayDate(String date) {
        return isHoliday(parseDate(date));
    }

    private boolean isHoliday(int day) {
        return (day % 7 == 4) || (day % 7 == 5) || day == 1 || day == 9 || day == 10;
    }

    private int parseDate(String date) {
        if (date.length() != DATE_LENGTH) {
            throw new UncorrectedLengthDate("Некорректная длина даты");
        }
        String result = date.replaceAll("[_./-]", ".");

        char[] resultCharArray = result.toCharArray();
        if (resultCharArray[2] != '.' || resultCharArray[5] != '.') {
            throw new UncorrectedFormatDate("Некорректный формат даты");
        }

        int dataDay = getDataDay(result.substring(0, 2));
        checkDataMonth(result.substring(3, 5));
        checkDataYear(result.substring(6, 10));

        return dataDay;
    }

    private int getDataDay(String day) {
        int number;
        try {
            number = Integer.parseInt(day);
        } catch (NumberFormatException e) {
            throw new UncorrectedDate("Недопустимый день в дате");
        }
        checkDataDay(number);
        return number;
    }

    private void checkDataDay(int number) {
        if (number < 1 || number > 31) {
            throw new UncorrectedDate("Недопустимый день в дате");
        }
    }

    private void checkDataMonth(String month) {
        if (!month.equals("05")) {
            throw new UncorrectedDate("Недопустимый месяц в дате");
        }
    }

    private void checkDataYear(String year) {
        if (!year.equals("2024")) {
            throw new UncorrectedDate("Недопустимый год в дате");
        }
    }
}