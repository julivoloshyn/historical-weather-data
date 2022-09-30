package com.knubisoft.weatherdata.chart.chageschart;

import java.util.List;

public interface ChangesChart {

    <T> void createChart(List<T> data, String startDateTime, String endDateTime);
}
