import org.dmitriyChinyaknin.exception.UncorrectedDate;
import org.dmitriyChinyaknin.exception.UncorrectedFormatDate;
import org.dmitriyChinyaknin.exception.UncorrectedLengthDate;
import org.dmitriyChinyaknin.service.TimeService;
import org.dmitriyChinyaknin.service.TimeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TimeServiceImplTest {

    private TimeService timeService;

    @BeforeEach
    void init() {
        timeService = new TimeServiceImpl();
    }

    @Test
    void testIsWeekend() {
        assertFalse(timeService.isHolidayDate("02/05/2024"));
        assertFalse(timeService.isHolidayDate("08/05/2024"));
        assertFalse(timeService.isHolidayDate("14.05-2024"));
        assertFalse(timeService.isHolidayDate("24.05/2024"));

        assertTrue(timeService.isHolidayDate("01/05/2024"));
        assertTrue(timeService.isHolidayDate("09/05/2024"));
        assertTrue(timeService.isHolidayDate("18/05/2024"));
        assertTrue(timeService.isHolidayDate("26/05/2024"));
        assertTrue(timeService.isHolidayDate("10/05/2024"));
    }

    @Test
    void testUncorrectedDateLength() {
        assertThrows(UncorrectedLengthDate.class, () -> timeService.isHolidayDate("99523/05/2024"));
        assertThrows(UncorrectedLengthDate.class, () -> timeService.isHolidayDate("ab124/05/2024"));
        assertThrows(UncorrectedLengthDate.class, () -> timeService.isHolidayDate("-5.541205-2024"));
        assertThrows(UncorrectedLengthDate.class, () -> timeService.isHolidayDate("66.05/2024234"));
    }

    @Test
    void testUncorrectedDateDay() {
        assertThrows(UncorrectedDate.class, () -> timeService.isHolidayDate("99/05/2024"));
        assertThrows(UncorrectedDate.class, () -> timeService.isHolidayDate("00/05/2024"));
        assertThrows(UncorrectedDate.class, () -> timeService.isHolidayDate("-5.05-2024"));
        assertThrows(UncorrectedDate.class, () -> timeService.isHolidayDate("66.05/2024"));
    }

    @Test
    void testUncorrectedDateMonth() {
        assertThrows(UncorrectedDate.class, () -> timeService.isHolidayDate("11/06/2024"));
        assertThrows(UncorrectedDate.class, () -> timeService.isHolidayDate("09/ab/2024"));
        assertThrows(UncorrectedDate.class, () -> timeService.isHolidayDate("18.64-2024"));
        assertThrows(UncorrectedDate.class, () -> timeService.isHolidayDate("22.cc/2024"));
    }

    @Test
    void testUncorrectedDateYear() {
        assertThrows(UncorrectedDate.class, () -> timeService.isHolidayDate("11/06/2025"));
        assertThrows(UncorrectedDate.class, () -> timeService.isHolidayDate("09/ab/1984"));
        assertThrows(UncorrectedDate.class, () -> timeService.isHolidayDate("18.64-1111"));
        assertThrows(UncorrectedDate.class, () -> timeService.isHolidayDate("22.cc/0094"));
    }

    @Test
    void testUncorrectedDateFormat() {
        assertThrows(UncorrectedFormatDate.class, () -> timeService.isHolidayDate("11%06/2025"));
        assertThrows(UncorrectedFormatDate.class, () -> timeService.isHolidayDate("09/ab=1984"));
        assertThrows(UncorrectedFormatDate.class, () -> timeService.isHolidayDate("18~64-1111"));
        assertThrows(UncorrectedFormatDate.class, () -> timeService.isHolidayDate("22.cc+0094"));
    }
}
