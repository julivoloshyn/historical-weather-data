package com.knubisoft.weatherdata.table.tabledto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class WeatherNamings {
    private String maxt;
    private String mint;
    private String visibility;
    private String wspd;
    private String solarradiation;
    private String weathertype;
    private String humidity;
    private String presipсover;
    private String conditions;
}
