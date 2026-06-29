package com.jobsearchanalytics.parser;

import com.jobsearchanalytics.util.ColumnMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class CsvParser implements FileParser {

    @Override
    public boolean supports(String filename) {
        return filename != null && filename.toLowerCase().endsWith(".csv");
    }

    @Override
    public List<Map<String, String>> parse(MultipartFile file) {

        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)
                );
                CSVParser csvParser = CSVFormat.DEFAULT
                        .builder()
                        .setHeader()
                        .setSkipHeaderRecord(true)
                        .setIgnoreSurroundingSpaces(true)
                        .setTrim(true)
                        .build()
                        .parse(reader)
        ) {

            List<String> headers = csvParser.getHeaderNames()
                    .stream()
                    .map(ColumnMapper::normalize)
                    .toList();

            List<Map<String, String>> rows = new ArrayList<>();

            for (CSVRecord record : csvParser) {

                if (record.stream().allMatch(String::isBlank)) {
                    continue;
                }

                Map<String, String> row = new LinkedHashMap<>();

                for (int i = 0; i < headers.size(); i++) {

                    String value = "";

                    if (i < record.size()) {
                        value = record.get(i);

                        if (value != null) {
                            value = value.trim();
                        } else {
                            value = "";
                        }
                    }

                    row.put(headers.get(i), value);
                }

                rows.add(row);
            }

            return rows;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage(), e);
        }
    }
}
