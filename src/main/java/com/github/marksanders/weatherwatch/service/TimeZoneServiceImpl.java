package com.github.marksanders.weatherwatch.service;

import java.time.ZoneId;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TimeZoneServiceImpl implements TimeZoneService {
    private static final int LONDON_ID = 2643743;
    private static final int HONG_KONG_ID = 1819729;

    private static final Map<Integer, ZoneId> TIME_ZONE_MAP;
    
    static {
        final Map<Integer, ZoneId> timeZoneMap = new HashMap<>();
        timeZoneMap.put(LONDON_ID, ZoneId.of("Europe/London"));
        timeZoneMap.put(HONG_KONG_ID, ZoneId.of("Asia/Hong_Kong"));
        
        TIME_ZONE_MAP = Collections.unmodifiableMap(timeZoneMap);
    }

    @Override
    public ZoneId getTimeZoneForCity(int cityId) {
        return TIME_ZONE_MAP.get(cityId);
    }

}
