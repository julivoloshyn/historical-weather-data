package com.knubisoft.weatherdata.chart.resultchart.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.knubisoft.weatherdata.chart.resultchart.ResultChart;
import com.knubisoft.weatherdata.chart.chartbuilder.impl.HumidityChartBuilder;
import com.knubisoft.weatherdata.chart.chartbuilder.impl.PrecipitationChartBuilder;
import com.knubisoft.weatherdata.chart.chartbuilder.impl.TemperatureChartBuilder;
import com.knubisoft.weatherdata.dataprocessor.PeriodProcessor;
import com.knubisoft.weatherdata.chart.chartdto.HumidityChangesData;
import com.knubisoft.weatherdata.chart.chartdto.PrecipitationChangesData;
import com.knubisoft.weatherdata.chart.chartdto.TemperatureChangesData;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.plot.Plot;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.function.Supplier;

/**
 * Result chart of required data from builder classes initially writes to .png file and then to .pdf.
 */
public class ResultChartImpl implements ResultChart {

    private static final String IMAGE_NAME = "src/main/resources/image/image.png";

    PeriodProcessor period = new PeriodProcessor();

    /**
     * Puts all data to PDF file.
     *
     * @param data List of weather values.
     * @param startDateTime Date time from request.
     * @param endDateTime Date time from request.
     * @param <T> Unknown type.
     * @return Path of result .pdf.
     */
    @SneakyThrows
    @Override
    public <T> String buildResult(List<T> data,
                                  String startDateTime,
                                  String endDateTime) {

        List<LocalDate> dates = period.createListOfDates(startDateTime, endDateTime);

        LinkedHashMap<Class<?>, Supplier<String>> resultMap = new LinkedHashMap<>();

        resultMap.put(PrecipitationChangesData.class, () -> {
            String className = PrecipitationChangesData.class.getSimpleName();
            Plot plot = new PrecipitationChartBuilder().designChart(data, dates);
            saveChartAsPng(plot, className);
            return className;
        });

        resultMap.put(TemperatureChangesData.class, () -> {
            String className = TemperatureChangesData.class.getSimpleName();
            Plot plot = new TemperatureChartBuilder().designChart(data, dates);
            saveChartAsPng(plot, className);
            return className;
        });

        resultMap.put(HumidityChangesData.class, () -> {
            String className = HumidityChangesData.class.getSimpleName();
            Plot plot = new HumidityChartBuilder().designChart(data, dates);
            saveChartAsPng(plot, className);
            return className;
        });

        return generatePdf(resultMap.get(data.get(0).getClass()).get());
    }


    /**
     * Creates and saves chart to PNG.
     *
     * @param plot      Chart.
     * @param className Name of builder class.
     */
    @SneakyThrows
    private void saveChartAsPng(Plot plot, String className) {
        ChartUtils.saveChartAsPNG(new File(IMAGE_NAME), plot.getChart(), 550, 300);
    }

    /**
     * Generates PDF and saves an image there.
     *
     * @param className Name of builder class.
     * @return Path of document.
     */
    @SneakyThrows
    public String generatePdf(String className) {
        String fileName = getFileName(className);

        Document document = new Document();
        String docName = checkForExistsDocument(fileName);

        PdfWriter.getInstance(document,
                new FileOutputStream(docName));
        Image img = Image.getInstance(IMAGE_NAME);

        document.open();
        document.add(img);
        document.close();

        return new File(docName).getAbsolutePath();
    }

    /**
     * Checks if PDF document name is already exists. If it`s true - iterates exists name.
     *
     * @param fileName File name to check.
     * @return Unique document name.
     */
    private String checkForExistsDocument(String fileName) {
        String docName = "src/main/resources/pdfchart/" + fileName + ".pdf";

        int i = 0;
        while (Files.exists(Path.of(docName))){
            i++;
            String newFileName = fileName + i;
            docName = "src/main/resources/pdfchart/" + newFileName + ".pdf";
        }

        return docName;
    }

    /**
     * Changes class name to camel case.
     *
     * @param className Name of builder class.
     * @return Name for file.
     */
    private String getFileName(String className) {
        return StringUtils.uncapitalize(className);
    }

}
