package com.knubisoft.weatherdata.table;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.knubisoft.weatherdata.dataprocessor.GivenPeriod;
import com.knubisoft.weatherdata.parser.jsontoobject.ParserToObject;
import com.knubisoft.weatherdata.reader.FileReader;
import com.knubisoft.weatherdata.weatherdto.WeatherNamings;
import com.knubisoft.weatherdata.weatherdto.WeatherValues;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class TableProcessor {

    private static final FileReader NAMES_READER = new FileReader(new File("src/main/resources/info/names.json"));
    private static final FileReader UNITS_READER = new FileReader(new File("src/main/resources/info/units.json"));

    @SneakyThrows
    public void createPdf(List<WeatherValues> values,
                          String startDateTime,
                          String endDateTime) {

        List<LocalDate> datesList = new GivenPeriod().createListOfDates(startDateTime, endDateTime);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/pdftables/iTextTable.pdf"));

        buildResult(values, document, datesList);
    }

    private void buildResult(List<WeatherValues> values, Document document, List<LocalDate> datesList) throws DocumentException {
        document.open();

        PdfPTable headerTable = new PdfPTable(3);
        addTableHeader(headerTable);
        document.add(headerTable);

        for(int i = 0; i < values.size(); i++) {

            PdfPTable table = new PdfPTable(3);
            addRows(table, i, values);

            PdfPTable dateTable = new PdfPTable(1);
            addTableSubtitle(dateTable, i, datesList);

            document.add(dateTable);
            document.add(table);
        }

        document.close();
    }

    private void addTableSubtitle(PdfPTable dateTable, int i, List<LocalDate> datesList){
        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase(String.valueOf(datesList.get(i))));
        horizontalAlignCell.setBackgroundColor(BaseColor.MAGENTA);
        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_MIDDLE);

        dateTable.addCell(horizontalAlignCell);
    }

    private void addTableHeader(PdfPTable headerTable) {
        Stream.of("Name", "Value", "Unit")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    headerTable.addCell(header);
                });
    }

    private void addRows(PdfPTable table, int i, List<WeatherValues> data) {

        WeatherNamings names = new ParserToObject().readAllNamesOrUnits(NAMES_READER.getContent());
        WeatherNamings units = new ParserToObject().readAllNamesOrUnits(UNITS_READER.getContent());

        table.addCell(names.getMaxt());
        table.addCell(String.valueOf(data.get(i).getMaxt()));
        table.addCell(units.getMaxt());

        table.addCell(names.getMint());
        table.addCell(String.valueOf(data.get(i).getMint()));
        table.addCell(units.getMint());

        table.addCell(names.getWspd());
        table.addCell(String.valueOf(data.get(i).getWspd()));
        table.addCell(units.getWspd());

        table.addCell(names.getVisibility());
        table.addCell(String.valueOf(data.get(i).getVisibility()));
        table.addCell(units.getVisibility());

        table.addCell(names.getSolarradiation());
        table.addCell(String.valueOf(data.get(i).getSolarradiation()));
        table.addCell(units.getSolarradiation());

        table.addCell(names.getWeathertype());
        table.addCell(data.get(i).getWeathertype());
        table.addCell(units.getWeathertype());

        table.addCell(names.getHumidity());
        table.addCell(String.valueOf(data.get(i).getHumidity()));
        table.addCell(units.getHumidity());

        table.addCell(names.getPresipсover());
        table.addCell(String.valueOf(data.get(i).getPresipсover()));
        table.addCell(units.getPresipсover());

        table.addCell(names.getConditions());
        table.addCell(data.get(i).getConditions());
        table.addCell(units.getConditions());

    }
}
