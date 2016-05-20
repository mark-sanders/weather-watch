package com.github.marksanders.weatherwatch.dto;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.core.style.ToStringCreator;

public class Weather {

    private final String cityName;
    private final int cityId;
    private final ZonedDateTime cityDateTime;
    private final String description;
    private final Temperature temperature;   
    private final ZonedDateTime citySunriseDateTime;
    private final ZonedDateTime citySunsetDateTime;

    public Weather(
            final String cityName, 
            final int cityId, 
            final ZonedDateTime cityDateTime, 
            final String description,
            final Temperature temperature, 
            final ZonedDateTime citySunriseDateTime, 
            final ZonedDateTime citySunsetDateTime) {
        this.cityName = cityName;
        this.cityId = cityId;
        this.cityDateTime = cityDateTime;
        this.description = description;
        this.temperature = temperature;
        this.citySunriseDateTime = citySunriseDateTime;
        this.citySunsetDateTime = citySunsetDateTime;
    }

    public String getCityName() {
        return cityName;
    }

    public int getCityId() {
        return cityId;
    }

    public ZonedDateTime getCityDateTime() {
        return cityDateTime;
    }

    public String getDescription() {
        return description;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public ZonedDateTime getCitySunriseDateTime() {
        return citySunriseDateTime;
    }

    public ZonedDateTime getCitySunsetDateTime() {
        return citySunsetDateTime;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("cityName", cityName)
                .append("cityId", cityId)
                .append("cityDateTime", cityDateTime)
                .append("description", description)
                .append("temperature", temperature)
                .append("citySunriseDateTime", citySunriseDateTime)
                .append("citySunsetDateTime", citySunsetDateTime)
                .append("temperature", temperature)
                .toString();
    }

    public static class Builder {
        private String cityName;
        private int cityId;
        private ZonedDateTime cityDateTime;
        private String description;
        private Temperature temperature;   
        private ZonedDateTime citySunriseDateTime;
        private ZonedDateTime citySunsetDateTime;
        private ZoneId timeZone;

        public Builder cityName(final String cityName) {
            this.cityName = cityName;
            return this;
        }

        public Builder cityId(final int cityId) {
            this.cityId = cityId;
            return this;
        }

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public Builder temperatureKelvin(final double temperatureKelvin) {
            this.temperature = new Temperature(temperatureKelvin);
            return this;
        }

        public Builder temperatureCelsius(final double temperatureCelsius) {
            this.temperature = Temperature.fromCelsius(temperatureCelsius);
            return this;
        }

        public Builder cityDateTime(final ZonedDateTime cityDateTime) {
            this.cityDateTime = cityDateTime;
            return this;
        }

        public Builder citySunriseDateTime(final ZonedDateTime citySunriseDateTime) {
            this.citySunriseDateTime = citySunriseDateTime;
            return this;
        }

        public Builder citySunsetDateTime(final ZonedDateTime citySunsetDateTime) {
            this.citySunsetDateTime = citySunsetDateTime;
            return this;
        }
        
        public Builder timeZone(final ZoneId zoneId) {
            checkDateTime(cityDateTime, "cityDateTime");
            checkDateTime(citySunriseDateTime, "citySunriseDateTime");
            checkDateTime(citySunsetDateTime, "citySunsetDateTime");

            this.timeZone = zoneId;
            return this;
        }

        public Builder cityDateTime(final long unixUtcSeconds) {
            checkTimeZone("cityDateTime");
            this.cityDateTime = toZonedDateTime(unixUtcSeconds);
            return this;
        }


        public Builder citySunriseDateTime(final long unixUtcSeconds) {
            checkTimeZone("citySunriseDateTime");
            this.citySunriseDateTime = toZonedDateTime(unixUtcSeconds);
            return this;
        }

        public Builder citySunsetDateTime(final long unixUtcSeconds) {
            checkTimeZone("citySunsetDateTime");
            this.citySunsetDateTime = toZonedDateTime(unixUtcSeconds);
            return this;
        }

        private ZonedDateTime toZonedDateTime(final long unixUtcSeconds) {
            final Instant instance = Instant.ofEpochSecond(unixUtcSeconds);
            return ZonedDateTime.ofInstant(instance, timeZone);
        }
        

        public Weather build() {
            return new Weather(
                    this.cityName,
                    this.cityId,
                    this.cityDateTime,
                    this.description,
                    this.temperature,
                    this.citySunriseDateTime,
                    citySunsetDateTime); 
        }

        private static void checkDateTime(ZonedDateTime dateTimeField, String dateTimeFieldName) {
            if (dateTimeField != null) {
                throw new IllegalArgumentException(
                        "Cannot set time zone when " + dateTimeFieldName + " already set");
            }
        }

        private void checkTimeZone(String dateTimeFieldName) {
            if (timeZone == null) {
                throw new IllegalArgumentException(
                        "Cannot set " + dateTimeFieldName + " when time zone not set");
            }
        }
    }  
}
