package com.knubisoft.weatherdata.reader;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Getter
@RequiredArgsConstructor
public class FileReading {

    private final File source;

    /**
     * Reads file to string.
     *
     * @return String.
     */
    @SneakyThrows
    public String getContent() {
        return FileUtils.readFileToString(source, StandardCharsets.UTF_8);
    }
}
