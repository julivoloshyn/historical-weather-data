package com.knubisoft.weatherdata.chartbuilder;

import com.knubisoft.weatherdata.chartbuilder.dataprocessor.GivenPeriod;
import com.knubisoft.weatherdata.pdfgenerator.ImageInsertion;
import com.knubisoft.weatherdata.weatherdto.TemperatureChangesData;
import lombok.SneakyThrows;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class TemperatureChangesChart {

    @SneakyThrows
    public void createChart(List<TemperatureChangesData> data,
                            String unitGroup,
                            String startDateTime,
                            String endDateTime) {

        GivenPeriod period = new GivenPeriod();
        ImageInsertion imageInsertion = new ImageInsertion();

        List<LocalDate> dates = period.createListOfDates(startDateTime, endDateTime);

        DefaultCategoryDataset dataset = buildChart(data, dates);

        String type;
        if(unitGroup.equals("us")){
            type = "degF";
        } else {
            type = "degC";
        }

        saveAsPng(dataset, type);
        imageInsertion.generateDocument(this.getClass().getSimpleName());
    }

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

    private Plot designChart(DefaultCategoryDataset dataset, String type) {

        JFreeChart chart = ChartFactory.createLineChart(
                "Temperature changes for the specified period",
                "Date",
                "Temperature (" + type + ")",
                dataset,
                PlotOrientation.HORIZONTAL,
                true,
                true,
                false
        );

        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        Plot plot = chart.getPlot();
        plot.setBackgroundPaint(Color.white);

        return plot;
    }

    private void saveAsPng(DefaultCategoryDataset dataset,
                           String type) throws IOException {

        Plot plot = designChart(dataset, type);

        ChartUtils.saveChartAsPNG(new File("src/main/resources/images/temperatureChangesChart.png"),
                plot.getChart(),
                500,
                700);
    }

}
