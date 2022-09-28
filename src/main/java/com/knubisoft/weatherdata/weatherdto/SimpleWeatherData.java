package com.knubisoft.weatherdata.weatherdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class SimpleWeatherData {
    private double temp;
    private double maxt;
    private double visibility;
    private double wspd;
    private double mint;
    private String weathertype;
    private double humidity;
    private double presipсover;
    private String conditions;
}
