package com.knubisoft.weatherdata.table.tablebuilder;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.knubisoft.weatherdata.dataprocessor.PeriodProcessor;
import com.knubisoft.weatherdata.parser.jsontoobject.ParserToObject;
import com.knubisoft.weatherdata.reader.FileReading;
import com.knubisoft.weatherdata.table.tabledto.WeatherNamings;
import com.knubisoft.weatherdata.table.tabledto.WeatherValues;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class TableBuilder {

    private static final FileReading NAMES_READER = new FileReading(new File("src/main/resources/info/names.json"));
    private static final FileReading UNITS_READER = new FileReading(new File("src/main/resources/info/units.json"));

    /**
     * Generates document and puts there full result.
     *
     * @param values Weather data.
     * @param startDateTime Date from request.
     * @param endDateTime Date from request.
     * @return Document path.
     */
    @SneakyThrows
    public String generatePdf(List<WeatherValues> values, String startDateTime, String endDateTime) {

        List<LocalDate> datesList = new PeriodProcessor().createListOfDates(startDateTime, endDateTime);

        String fileName = "weatherChangesTable";
        String docPath = checkForExistsDocument(fileName);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(docPath));

        buildResult(values, document, datesList);

        return new File(docPath).getAbsolutePath();
    }

    /**
     * Checks if PDF document name is already exists. If it`s true - iterates exists name.
     *
     * @param fileName File name to check.
     * @return Unique document name.
     */
    private String checkForExistsDocument(String fileName) {
        String docName = "src/main/resources/pdftables/" + fileName + ".pdf";

        int i = 0;
        while (Files.exists(Path.of(docName))){
            i++;
            String newFileName = fileName + i;
            docName = "src/main/resources/pdftables/" + newFileName + ".pdf";
        }

        return docName;
    }

    /**
     * Assembles a table from different parts.
     *
     * @param values Weather data.
     * @param document Document.
     * @param datesList List of dates.
     */
    private void buildResult(List<WeatherValues> values, Document document, List<LocalDate> datesList)
            throws DocumentException {

        document.open();

        PdfPTable headerTable = new PdfPTable(3);
        designTableHeader(headerTable);
        document.add(headerTable);

        for(int i = 0; i < values.size(); i++) {

            PdfPTable table = new PdfPTable(3);
            addRows(table, i, values);

            PdfPTable dateTable = new PdfPTable(1);
            designTableSubtitle(dateTable, i, datesList);

            document.add(dateTable);
            document.add(table);
        }

        document.close();
    }

    /**
     * Creates header.
     *
     * @param headerTable Part of table.
     */
    private void designTableHeader(PdfPTable headerTable) {
        Stream.of("Name", "Value", "Unit")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    headerTable.addCell(header);
                });
    }

    /**
     * Creates subtitle.
     *
     * @param dateTable Part of table.
     * @param i Iterator for getting date.
     * @param datesList List of dates.
     */
    private void designTableSubtitle(PdfPTable dateTable, int i, List<LocalDate> datesList){
        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase(String.valueOf(datesList.get(i))));
        horizontalAlignCell.setBackgroundColor(BaseColor.MAGENTA);
        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_MIDDLE);

        dateTable.addCell(horizontalAlignCell);
    }

    /**
     * Creates rows.
     *
     * @param table Part of table.
     * @param i Iterator for getting date.
     * @param data Weather data.
     */
    private void addRows(PdfPTable table, int i, List<WeatherValues> data) {

        WeatherNamings names = new ParserToObject().readNamesOrUnits(NAMES_READER.getContent());
        WeatherNamings units = new ParserToObject().readNamesOrUnits(UNITS_READER.getContent());

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
