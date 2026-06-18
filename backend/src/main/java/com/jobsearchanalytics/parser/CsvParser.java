package com.jobsearchanalytics.parser;

import com.jobsearchanalytics.util.ColumnMapper;
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

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)
        )) {

            String headerLine = reader.readLine();

            if (headerLine == null || headerLine.isBlank()) {
                throw new RuntimeException("CSV file is empty or missing headers");
            }

            String[] rawHeaders = headerLine.split(",");

            List<String> headers = new ArrayList<>();

            for (String h : rawHeaders) {
                headers.add(ColumnMapper.map(h.trim()));
            }

            List<Map<String, String>> rows = new ArrayList<>();

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.isBlank()) continue;

                String[] values = line.split(",", -1);

                Map<String, String> row = new HashMap<>();

                for (int i = 0; i < headers.size(); i++) {

                    String value = i < values.length
                            ? values[i].trim()
                            : "";

                    row.put(headers.get(i), value);
                }

                rows.add(row);
            }

            return rows;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to parse CSV file: " + e.getMessage(),
                    e
            );
        }
    }
}
