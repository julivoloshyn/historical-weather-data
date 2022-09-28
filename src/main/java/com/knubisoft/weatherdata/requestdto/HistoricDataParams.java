package com.knubisoft.weatherdata.requestdto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class HistoricDataParams {

    @NotNull
    private String startDateTime;

    @NotNull
    private String aggregateHours;

    @NotNull
    private String location;

    @NotNull
    private String endDateTime;

    @NotNull
    private String unitGroup;
}
