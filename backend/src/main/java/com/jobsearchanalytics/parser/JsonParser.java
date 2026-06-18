package com.jobsearchanalytics.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobsearchanalytics.util.ColumnMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JsonParser implements FileParser {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supports(String filename) {
        return filename != null &&
                filename.toLowerCase().endsWith(".json");
    }

    @Override
    public List<Map<String, String>> parse(MultipartFile file) {

        try {

            List<Map<String, Object>> rawList =
                    objectMapper.readValue(
                            file.getInputStream(),
                            new TypeReference<>() {}
                    );

            List<Map<String, String>> rows = new ArrayList<>();

            for (Map<String, Object> item : rawList) {

                Map<String, String> row = new java.util.HashMap<>();

                for (Map.Entry<String, Object> entry : item.entrySet()) {

                    String normalizedKey = ColumnMapper.map(
                            entry.getKey()
                    );

                    String value = entry.getValue() == null
                            ? ""
                            : String.valueOf(entry.getValue());

                    row.put(normalizedKey, value.trim());
                }

                rows.add(row);
            }

            return rows;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to parse JSON file: " + e.getMessage(),
                    e
            );
        }
    }
}
