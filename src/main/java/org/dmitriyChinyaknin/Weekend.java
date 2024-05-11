package org.dmitriyChinyaknin;

import org.dmitriyChinyaknin.service.TimeService;
import org.dmitriyChinyaknin.service.TimeServiceByOffsetDateTime;
import org.dmitriyChinyaknin.service.TimeServiceByOffsetDateTimeImpl;
import org.dmitriyChinyaknin.service.TimeServiceImpl;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class Weekend {
    public static void main(String[] args) {
        TimeService timeService = new TimeServiceImpl();
        TimeServiceByOffsetDateTime timeServiceByOffsetDateTime = new TimeServiceByOffsetDateTimeImpl();
        OffsetDateTime dateTime = OffsetDateTime.now();

        System.out.println("Демонстрация работы со считыванием даты с ПК: ");
        System.out.println("Исходные данные: " + dateTime + "\nРезультат: " + timeServiceByOffsetDateTime.isHolidayDateOrTime(OffsetDateTime.now()) + "\n");

        System.out.println("Демонстрация работы с ручным вводом данных: ");
        dateTime = OffsetDateTime.of(2024,
                05,
                14,
                17,
                34,
                42,
                0,
                ZoneOffset.ofHours(3));
        System.out.println("Исходные данные: " + dateTime + "\nРезультат: " + timeServiceByOffsetDateTime.isHolidayDateOrTime(dateTime) + "\n");

        System.out.println("Демонстрация работы с учетом только даты: ");
        String date = "01/05-2024";
        System.out.println("Исходные данные: " + date + "\nРезультат: " + timeService.isHolidayDate(date));
    }
}
