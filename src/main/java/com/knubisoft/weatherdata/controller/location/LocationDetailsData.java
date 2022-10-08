package com.knubisoft.weatherdata.controller.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDetailsData {
    private String address;
    private double latitude;
    private double longitude;
    private String tz;
}
