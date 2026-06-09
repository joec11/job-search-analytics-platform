package com.jobsearchanalytics.service.importer;

import com.jobsearchanalytics.dto.response.ImportSummary;
import com.jobsearchanalytics.util.ExcelParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImportServiceImpl implements ImportService {

    private final ExcelParser excelParser;

    @Override
    public ImportSummary importFile(MultipartFile file) {

        // 1. Parse Excel → List of rows
        List<Map<String, String>> rows = excelParser.parse(file);

        int totalRows = rows.size();

        // 2. For now: no validation, no DB insert yet
        int successfulRows = totalRows;
        int failedRows = 0;

        return new ImportSummary(
                totalRows,
                successfulRows,
                failedRows,
                List.of()
        );
    }
}
