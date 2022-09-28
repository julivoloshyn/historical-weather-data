package com.knubisoft.weatherdata.weatherdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class FullWeatherData {
    private double wdir;
    private double temp;
    private double maxt;
    private double visibility;
    private double wspd;
    private double solarenergy;
    private double heatindex;
    private double cloudcover;
    private double mint;
    private double precip;
    private double solarradiation;
    private String weathertype;
    private double snowdepth;
    private double sealevelpressure;
    private double snow;
    private double dew;
    private double humidity;
    private double presipcover;
    private double wgust;
    private String conditions;
    private double windchill;
}
