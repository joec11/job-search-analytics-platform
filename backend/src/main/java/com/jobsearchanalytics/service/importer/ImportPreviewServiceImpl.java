package com.jobsearchanalytics.service.importer;

import com.jobsearchanalytics.dto.response.ImportPreviewResponse;
import com.jobsearchanalytics.parser.FileParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ImportPreviewServiceImpl implements ImportPreviewService {

    private final List<FileParser> parsers;

    public ImportPreviewServiceImpl(List<FileParser> parsers) {
        this.parsers = parsers;
    }

    @Override
    public ImportPreviewResponse previewFile(MultipartFile file) {

        FileParser parser = resolveParser(file.getOriginalFilename());

        List<Map<String, String>> rows = parser.parse(file);

        List<Map<String, String>> preview = new ArrayList<>();
        List<Map<String, Object>> errors = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++) {

            Map<String, String> row = rows.get(i);

            String jobTitle = safe(row.get("jobTitle"));
            String companyName = safe(row.get("companyName"));

            boolean isValid = !jobTitle.isBlank() && !companyName.isBlank();

            if (isValid) {

                Map<String, String> clean = new LinkedHashMap<>();

                clean.put("jobTitle", jobTitle);
                clean.put("companyName", companyName);
                clean.put("location", safe(row.get("location")));
                clean.put("workType", safe(row.get("workType")));
                clean.put("employmentType", safe(row.get("employmentType")));
                clean.put("salaryRange", safe(row.get("salaryRange")));
                clean.put("jobUrl", safe(row.get("jobUrl")));
                clean.put("status", safe(row.get("status")));
                clean.put("source", safe(row.get("source")));
                clean.put("dateApplied", safe(row.get("dateApplied")));

                preview.add(clean);

            } else {

                Map<String, Object> error = new LinkedHashMap<>();
                error.put("row", i + 1);
                error.put("message", "jobTitle and companyName are required");
                error.put("data", row);

                errors.add(error);
            }
        }

        return new ImportPreviewResponse(
                rows.size(),
                preview.size(),
                errors.size(),
                preview,
                errors
        );
    }

    /**
     * Strategy pattern: selects correct parser based on file type
     */
    private FileParser resolveParser(String filename) {

        if (filename == null) {
            throw new RuntimeException("File name is missing");
        }

        return parsers.stream()
                .filter(p -> p.supports(filename))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("No parser found for file: " + filename));
    }

    private String safe(String value) {
        return value == null ? "" : value.trim();
    }
}
