package com.github.marksanders.weatherwatch.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.ZoneId;

import org.junit.Test;

public class TimeZoneServiceImplTest {

    private TimeZoneServiceImpl uut = new TimeZoneServiceImpl();

    @Test
    public void testLondon() {
        ZoneId timeZone = uut.getTimeZoneForCity(2643743);
        assertNotNull(timeZone);
        assertEquals("Europe/London", timeZone.toString());
    }

    @Test
    public void testHongKong() {
        ZoneId timeZone = uut.getTimeZoneForCity(1819729);
        assertNotNull(timeZone);
        assertEquals("Asia/Hong_Kong", timeZone.toString());
    }

    @Test
    public void testOther() {
        ZoneId timeZone = uut.getTimeZoneForCity(999999);
        assertNull(timeZone);
    }

}
