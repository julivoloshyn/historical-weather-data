package com.knubisoft.weatherdata.chart.chartbuilder.impl;

import com.knubisoft.weatherdata.chart.chartbuilder.ChartBuilder;
import com.knubisoft.weatherdata.chart.chartdto.HumidityChangesData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.AreaRendererEndType;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Color;
import java.time.LocalDate;
import java.util.List;

/**
 * Building humidity changes chart for specified period.
 */
public class HumidityChartBuilder implements ChartBuilder {

    /**
     * Creates a design of chart and returns result.
     *
     * @param data List of weather values.
     * @param period List of dates between period.
     * @param <T> Unknown type.
     * @return Result.
     */
    public <T> Plot designChart(List<T> data,
                                List<LocalDate> period) {

        JFreeChart chart = createAreaChart((List<HumidityChangesData>) data, period);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setForegroundAlpha(0.3f);
        plot.setBackgroundPaint(Color.white);

        AreaRenderer renderer = (AreaRenderer) plot.getRenderer();
        renderer.setEndType(AreaRendererEndType.LEVEL);
        renderer.setSeriesPaint(0, Color.CYAN);

        return plot;
    }

    /**
     * Builds a specific area chart.
     *
     * @param data List of weather values.
     * @param period List of dates between period.
     * @return Chart.
     */
    private JFreeChart createAreaChart(List<HumidityChangesData> data, List<LocalDate> period) {
        return ChartFactory.createAreaChart(
                "Humidity changes for the specified period",
                "Date",
                "Humidity (%)",
                buildChart(data, period),
                PlotOrientation.VERTICAL,
                false,
                true,
                true
        );
    }

    /**
     * Builds chart line.
     *
     * @param data List of weather values.
     * @param period List of dates between period.
     * @return Dataset.
     */
    private DefaultCategoryDataset buildChart(List<HumidityChangesData> data,
                                              List<LocalDate> period) {
        String precip = "Humidity";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i = 0; i < period.size(); i++){
            dataset.addValue(data.get(i).getHumidity(), precip, period.get(i));
        }

        return dataset;
    }

}
