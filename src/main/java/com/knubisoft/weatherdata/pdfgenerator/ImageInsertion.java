package com.knubisoft.weatherdata.pdfgenerator;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;

import java.io.FileOutputStream;
import java.util.Locale;

public class ImageInsertion {

    @SneakyThrows
    public void generateDocument(String className){

        Document document = new Document();
        String imageName = className.substring(0, 1).toLowerCase(Locale.ROOT) + className.substring(1);
        PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/pdf/" + imageName + ".pdf"));

        document.open();

        Image img = Image.getInstance("src/main/resources/images/" + imageName + ".png");
        document.add(img);

        document.close();
    }

}
