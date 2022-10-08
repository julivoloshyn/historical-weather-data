package com.knubisoft.weatherdata.chart.chartbuilder.impl;

import com.knubisoft.weatherdata.chart.chartbuilder.ChartBuilder;
import com.knubisoft.weatherdata.chart.chartdto.TemperatureChangesData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.BasicStroke;
import java.awt.Color;
import java.time.LocalDate;
import java.util.List;

/**
 * Building temperature changes chart for specified period.
 */
public class TemperatureChartBuilder implements ChartBuilder {

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

        JFreeChart chart = createLineChart((List<TemperatureChangesData>) data, period);

        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        Plot plot = chart.getPlot();
        plot.setBackgroundPaint(Color.white);

        return plot;
    }

    /**
     * Builds a specific area chart.
     *
     * @param data List of weather values.
     * @param period List of dates between period.
     * @return Chart.
     */
    private JFreeChart createLineChart(List<TemperatureChangesData> data, List<LocalDate> period) {
        return ChartFactory.createLineChart(
                "Temperature changes for the specified period",
                "Date",
                "Temperature (degC)",
                buildChart(data, period),
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    /**
     * Builds chart line.
     *
     * @param data List of weather values.
     * @param period List of dates between period.
     * @return Dataset.
     */
    private DefaultCategoryDataset buildChart(List<TemperatureChangesData> data,
                                              List<LocalDate> period) {
        String maxt = "Maximum temperature";
        String mint = "Minimum temperature";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i = 0; i < period.size(); i++){
            dataset.addValue(data.get(i).getMaxt(), maxt, period.get(i));
            dataset.addValue(data.get(i).getMint(), mint, period.get(i));
        }

        return dataset;
    }
}
