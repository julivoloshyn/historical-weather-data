package com.knubisoft.weatherdata;

import com.knubisoft.weatherdata.reader.FileReading;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileReaderTest {

    FileReading instance = new FileReading(new File("src/test/resources/fileToTest"));

    @Test
    public void readFileToString_Success() {
        String result = "test";
        assertEquals(result, instance.getContent());
    }
}
