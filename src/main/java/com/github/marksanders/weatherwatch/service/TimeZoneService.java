package com.github.marksanders.weatherwatch.service;

import java.time.ZoneId;

public interface TimeZoneService {

    ZoneId getTimeZoneForCity(int cityId);
}
