package org.dmitriyChinyaknin.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class TimeServiceByOffsetDateTimeImpl implements TimeServiceByOffsetDateTime {

    private final ZoneOffset MOSCOW_ZONE_OFFSET = ZoneOffset.ofHours(3);

    @Override
    public boolean isHolidayDateOrTime(OffsetDateTime dateTime) {
        OffsetDateTime convertMoscowTime = dateTime.withOffsetSameInstant(MOSCOW_ZONE_OFFSET);
        return isHolidayDate(convertMoscowTime) || !isWorkTime(convertMoscowTime);
    }

    public boolean isWorkTime(OffsetDateTime dateTime) {
        return dateTime.getHour() >= 9 && (dateTime.getHour() < 18 || (dateTime.getHour() == 18 && dateTime.getMinute() == 0));
    }

    public boolean isHolidayDate(OffsetDateTime dateTime) {
        return isHoliday(dateTime.getDayOfMonth());
    }

    private boolean isHoliday(int day) {
        return (day % 7 == 4) || (day % 7 == 5) || day == 1 || day == 9 || day == 10;
    }
}
