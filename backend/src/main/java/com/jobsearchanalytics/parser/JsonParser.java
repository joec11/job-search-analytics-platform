package com.jobsearchanalytics.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobsearchanalytics.util.ColumnMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Component
public class JsonParser implements FileParser {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supports(String filename) {
        return filename != null && filename.toLowerCase().endsWith(".json");
    }

    @Override
    public List<Map<String, String>> parse(MultipartFile file) {

        try {

            List<Map<String, Object>> raw =
                    objectMapper.readValue(file.getInputStream(),
                            new TypeReference<>() {});

            List<Map<String, String>> result = new ArrayList<>();

            for (Map<String, Object> item : raw) {

                Map<String, String> row = new HashMap<>();

                for (Map.Entry<String, Object> entry : item.entrySet()) {

                    String key = ColumnMapper.normalize(entry.getKey());
                    String value = entry.getValue() == null ? "" : entry.getValue().toString();

                    row.put(key, value);
                }

                result.add(row);
            }

            return result;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON file: " + e.getMessage(), e);
        }
    }
}
