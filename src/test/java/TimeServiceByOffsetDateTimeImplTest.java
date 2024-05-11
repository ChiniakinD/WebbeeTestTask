import org.dmitriyChinyaknin.service.TimeServiceByOffsetDateTime;
import org.dmitriyChinyaknin.service.TimeServiceByOffsetDateTimeImpl;
import org.dmitriyChinyaknin.service.TimeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeServiceByOffsetDateTimeImplTest {
    private TimeServiceByOffsetDateTime timeServiceByOffsetDateTimeImpl;
    OffsetDateTime dateTime = OffsetDateTime.now();

    private OffsetDateTime createDateAndTime(int day, int hour, int minute, int zoneOffset) {
        return OffsetDateTime.of(2024,
                05,
                day,
                hour,
                minute,
                0,
                0,
                ZoneOffset.ofHours(zoneOffset));
    }
    @BeforeEach
    void init() {
        timeServiceByOffsetDateTimeImpl = new TimeServiceByOffsetDateTimeImpl();
    }

    @Test
    void testIsHolidayDateOrTime() {
        assertFalse(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(7,12,5,3)));
        assertFalse(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(15,14,48,4)));
        assertFalse(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(23,13,15,5)));
        assertFalse(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(31,15,45,5)));

        assertTrue(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(1,15,30,6)));
        assertTrue(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(11,2,22,5)));
        assertTrue(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(19,20,45,4)));
        assertTrue(timeServiceByOffsetDateTimeImpl.isHolidayDateOrTime(createDateAndTime(25,1,4,7)));
    }
}
