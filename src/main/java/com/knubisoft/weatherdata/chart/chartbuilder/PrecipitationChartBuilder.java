package com.knubisoft.weatherdata.chart.chartbuilder;

import com.knubisoft.weatherdata.chart.chartdto.PrecipitationChangesData;
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

public class PrecipitationChartBuilder {

    public <T> Plot designChart(List<T> data,
                                List<LocalDate> period) {

        JFreeChart chart = ChartFactory.createAreaChart(
                "Precipitation changes for the specified period",
                "Date",
                "Precipitation (mm)",
                buildChart((List<PrecipitationChangesData>) data, period),
                PlotOrientation.VERTICAL,
                false,
                true,
                true
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setForegroundAlpha(0.3f);
        plot.setBackgroundPaint(Color.white);

        AreaRenderer renderer = (AreaRenderer) plot.getRenderer();
        renderer.setEndType(AreaRendererEndType.LEVEL);
        renderer.setSeriesPaint(0, Color.BLUE);

        return plot;
    }

    private DefaultCategoryDataset buildChart(List<PrecipitationChangesData> data,
                                              List<LocalDate> period) {
        String precip = "Precipitation";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i = 0; i < period.size(); i++){
            dataset.addValue(data.get(i).getPrecip(), precip, period.get(i));
        }

        return dataset;
    }

}
