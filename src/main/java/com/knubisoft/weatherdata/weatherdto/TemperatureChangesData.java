package com.knubisoft.weatherdata.weatherdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class TemperatureChangesData {
    private double maxt;
    private double mint;
}
