package com.housing.finance.util;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CSVParser {

    private CSVParser() {

    }

    public static <T> List<T> read(Class<T> clazz, MultipartFile file, CsvSchema csvSchema) {
        List<T> result = new ArrayList<>();

        try {
            CsvMapper mapper = new CsvMapper();
            ObjectReader reader = initReader(mapper, csvSchema, clazz);
            InputStream stream = file.getInputStream();

            result = reader.<T>readValues(stream).readAll();
        } catch (IOException e) {
            log.error(e.toString());
        }

        return result;
    }

    private static <T> ObjectReader initReader(CsvMapper mapper, CsvSchema schema, Class<T> clazz) {
        return mapper
                .enable(CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE)
                .readerFor(clazz).with(schema);
    }
}
