package org.dmitriyChinyaknin.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class TimeServiceByOffsetDateTimeImpl implements TimeServiceByOffsetDateTime {

    TimeService timeService = new TimeServiceImpl();

    /**
     * Смещение временной зоны Москвы (UTC+3).
     */
    private final ZoneOffset MOSCOW_ZONE_OFFSET = ZoneOffset.ofHours(3);

    /**
     * Проверяет, является ли указанная дата/время праздничным или рабочим.
     */
    @Override
    public boolean isHolidayDateOrTime(OffsetDateTime dateTime) {
        OffsetDateTime convertMoscowTime = dateTime.withOffsetSameInstant(MOSCOW_ZONE_OFFSET);
        return isHolidayDate(convertMoscowTime) || !isWorkTime(convertMoscowTime);
    }

    /**
     * Проверяет, является ли указанное время рабочим (с 9:00 до 18:00).
     */
    private boolean isWorkTime(OffsetDateTime dateTime) {
        return dateTime.getHour() >= 9 && (dateTime.getHour() < 18 || (dateTime.getHour() == 18 && dateTime.getMinute() == 0) && dateTime.getSecond() == 0 && dateTime.getNano() == 0);
    }

    /**
     * Проверяет, является ли указанная дата праздничной.
     */
    private boolean isHolidayDate(OffsetDateTime convertMoscowTime) {
        String result = getDateWithZero(convertMoscowTime.getDayOfMonth())
                + "."
                + getDateWithZero(convertMoscowTime.getMonthValue())
                + "."
                + convertMoscowTime.getYear();
        return timeService.isHolidayDate(result);
    }

    /**
     * Форматирует число дня месяца, добавляя  ноль при необходимости.
     * Возвращает строку с отформатированным числом дня месяца.
     */
    private String getDateWithZero(int date) {
        String result = String.valueOf(date);
        if (result.length() < 2) {
            return "0" + result;
        }
        return result;
    }
}
