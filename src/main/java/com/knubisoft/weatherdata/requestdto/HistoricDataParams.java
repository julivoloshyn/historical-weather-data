package com.knubisoft.weatherdata.requestdto;

import com.knubisoft.weatherdata.requestdto.validation.annotation.DateTimeFormat;
import com.knubisoft.weatherdata.requestdto.validation.annotation.NotEmpty;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(notes = "Start date time", example = "2002-05-13", required = true)
    @DateTimeFormat(message = "Incorrect date time format.")
    @NotEmpty(message = "Date can not be empty.")
    private String startDateTime;

    @ApiModelProperty(notes = "Location", example = "Paris", required = true)
    @NotEmpty(message = "Location can not be empty.")
    private String location;

    @ApiModelProperty(notes = "End date time", example = "2002-05-17", required = true)
    @NotEmpty(message = "Date can not be empty.")
    @DateTimeFormat(message = "Incorrect date time format.")
    private String endDateTime;
}
