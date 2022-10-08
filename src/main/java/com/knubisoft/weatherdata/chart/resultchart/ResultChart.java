package com.knubisoft.weatherdata.chart.resultchart;

import java.util.List;

public interface ResultChart {

    <T> String buildResult(List<T> data, String startDateTime, String endDateTime);
}
