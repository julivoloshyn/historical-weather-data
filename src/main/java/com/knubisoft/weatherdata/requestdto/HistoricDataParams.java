package com.knubisoft.weatherdata.requestdto;

import com.knubisoft.weatherdata.requestdto.validation.annotation.DateTimeFormat;
import com.knubisoft.weatherdata.requestdto.validation.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Validated
public class HistoricDataParams {

    @DateTimeFormat(message = "Incorrect date time format.")
    @NotEmpty(message = "Date can not be empty.")
    private String startDateTime;

    @NotEmpty(message = "Location can not be empty.")
    private String location;

    @NotEmpty(message = "Date can not be empty.")
    @DateTimeFormat(message = "Incorrect date time format.")
    private String endDateTime;
}
