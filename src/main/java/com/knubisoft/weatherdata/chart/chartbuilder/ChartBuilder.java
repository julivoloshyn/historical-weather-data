package com.knubisoft.weatherdata.chart.chartbuilder;

import org.jfree.chart.plot.Plot;

import java.time.LocalDate;
import java.util.List;

public interface ChartBuilder {
    <T> Plot designChart(List<T> data, List<LocalDate> period);
}
