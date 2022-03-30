package com.nacrt.demo.jdknew.jdk8.e_date_time;

import java.time.*;
import java.time.temporal.ChronoField;

/**
 * DemoDateTime
 *
 * @author zhenghao
 * @date 2022/3/30 10:25
 */
public class DemoDateTime {

    /**
     * java.time包下新增：
     * ● Instant——它代表的是时间戳
     * ● LocalDate——不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等。
     * ● LocalTime——它代表的是不含日期的时间
     * ● LocalDateTime——它包含了日期及时间，不过还是没有偏移信息或者说时区。
     * ● ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的。
     */
    public static void main(String[] args) {
        testLocalDateTime();
        testZonedDateTime();
        testInstant();
    }

    private static void testInstant() {
        Instant now = Instant.now();
        System.out.println("now = " + now);
        long epochSecond = now.getEpochSecond();
        System.out.println("epochSecond = " + epochSecond);
        int nano = now.getNano();
        System.out.println("nano = " + nano);
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(epochSecond, nano, ZoneOffset.UTC);
        System.out.println("localDateTime = " + localDateTime);

        long milliOfSecond = now.getLong(ChronoField.MILLI_OF_SECOND);
        System.out.println("milliOfSecond = " + milliOfSecond);
    }

    // 时区相关
    private static void testZonedDateTime() {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("now = " + now);

        // 解析时间
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);
    }

    private static void testLocalDateTime() {
        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间: " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("月: " + month + ", 日: " + day + ", 秒: " + seconds);

        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);
        System.out.println("date1.isAfter(date2) = " + currentTime.isAfter(date2));

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
    }
}
