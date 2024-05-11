import org.dmitriyChinyaknin.service.TimeServiceByOffsetDateTime;
import org.dmitriyChinyaknin.service.TimeServiceByOffsetDateTimeImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeServiceByOffsetDateTimeImplTest {
    private TimeServiceByOffsetDateTime timeServiceByOffsetDateTimeImpl;

    @BeforeEach
    void init() {
        timeServiceByOffsetDateTimeImpl = new TimeServiceByOffsetDateTimeImpl();
    }

    @Test
    void testIsHolidayDateOrTime() {
        assertFalse(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(7,17,5,0,3)));
        assertFalse(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(15,14,48,0,4)));
        assertFalse(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(23,13,15,0,6)));
        assertFalse(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(31,15,45,0,7)));
        assertFalse(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(24,18,0,0,3)));

        assertTrue(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(7,8,2,0,3)));
        assertTrue(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(11,2,22,0,7)));
        assertTrue(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(19,20,45,0,9)));
        assertTrue(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(25,1,4,0,2)));
        assertTrue(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(24,18,0,1,3)));
    }

    private OffsetDateTime createDateAndTime(int day, int hour, int minute, int seconds, int zoneOffset) {
        return OffsetDateTime.of(2024,
                5,
                day,
                hour,
                minute,
                seconds,
                0,
                ZoneOffset.ofHours(zoneOffset));
    }
}
