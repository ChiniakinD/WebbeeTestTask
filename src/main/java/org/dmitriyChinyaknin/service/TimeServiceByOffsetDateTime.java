package org.dmitriyChinyaknin.service;

import java.time.OffsetDateTime;

public interface TimeServiceByOffsetDateTime {
    boolean isHolidayDateOrTime(OffsetDateTime dateTime);
}
