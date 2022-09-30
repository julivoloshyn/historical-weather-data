package com.knubisoft.weatherdata.reader;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Getter
@RequiredArgsConstructor
public class FileReader {

    private final File source;

    @SneakyThrows
    public String getContent() {
        return FileUtils.readFileToString(source, StandardCharsets.UTF_8);
    }
}
