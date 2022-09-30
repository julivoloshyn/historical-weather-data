package com.knubisoft.weatherdata.chart.chageschart.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.knubisoft.weatherdata.chart.chageschart.ChangesChart;
import com.knubisoft.weatherdata.chart.chartbuilder.HumidityChartBuilder;
import com.knubisoft.weatherdata.chart.chartbuilder.PrecipitationChartBuilder;
import com.knubisoft.weatherdata.chart.chartbuilder.TemperatureChartBuilder;
import com.knubisoft.weatherdata.dataprocessor.GivenPeriod;
import com.knubisoft.weatherdata.chart.chartdto.HumidityChangesData;
import com.knubisoft.weatherdata.chart.chartdto.PrecipitationChangesData;
import com.knubisoft.weatherdata.chart.chartdto.TemperatureChangesData;
import lombok.SneakyThrows;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.plot.Plot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class ChangesChartImpl implements ChangesChart {

    GivenPeriod period = new GivenPeriod();

    @SneakyThrows
    @Override
    public <T> void createChart(List<T> data,
                                String startDateTime,
                                String endDateTime) {

        String className = "";
        List<LocalDate> dates = period.createListOfDates(startDateTime, endDateTime);

        for(T object : data){
            if(object instanceof PrecipitationChangesData){
                PrecipitationChartBuilder precipitationChangesChart = new PrecipitationChartBuilder();
                className = precipitationChangesChart.getClass().getSimpleName();
                Plot plot = precipitationChangesChart.designChart(data, dates);
                saveChartAsPng(plot, className);
            }

            if(object instanceof TemperatureChangesData){
                TemperatureChartBuilder temperatureChartBuilder = new TemperatureChartBuilder();
                className = temperatureChartBuilder.getClass().getSimpleName();
                Plot plot = temperatureChartBuilder.designChart(data, dates);
                saveChartAsPng(plot, className);
            }

            if(object instanceof HumidityChangesData){
                HumidityChartBuilder humidityChartBuilder = new HumidityChartBuilder();
                className = humidityChartBuilder.getClass().getSimpleName();
                Plot plot = humidityChartBuilder.designChart(data, dates);
                saveChartAsPng(plot, className);
            }
        }

        generateDocument(className);
    }

    private void saveChartAsPng(Plot plot, String className) throws IOException {

        String imageName = className.substring(0, 1).toLowerCase(Locale.ROOT) + className.substring(1);

        ChartUtils.saveChartAsPNG(new File(
                        "src/main/resources/images/" + imageName + ".png"),
                plot.getChart(),
                500,
                300);
    }

    @SneakyThrows
    private void generateDocument(String className){

        Document document = new Document();
        String imageName = className.substring(0, 1).toLowerCase(Locale.ROOT) + className.substring(1);
        PdfWriter.getInstance(document, new FileOutputStream(
                "src/main/resources/pdf/" + imageName + ".pdf"));

        document.open();

        Image img = Image.getInstance(
                "src/main/resources/images/" + imageName + ".png");
        document.add(img);

        document.close();
    }

}
