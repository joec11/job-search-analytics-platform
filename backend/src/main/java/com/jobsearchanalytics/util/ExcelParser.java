package com.jobsearchanalytics.util;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

@Component
public class ExcelParser {

    public List<Map<String, String>> parse(MultipartFile file) {

        try (InputStream is = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(is)) {

            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            if (!rowIterator.hasNext()) {
                return Collections.emptyList();
            }

            // Header row
            Row headerRow = rowIterator.next();
            List<String> headers = new ArrayList<>();

            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue().trim());
            }

            List<Map<String, String>> rows = new ArrayList<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Map<String, String> rowMap = new HashMap<>();

                for (int i = 0; i < headers.size(); i++) {

                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    String value = switch (cell.getCellType()) {
                        case STRING -> cell.getStringCellValue();
                        case NUMERIC -> String.valueOf(cell.getNumericCellValue());
                        case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
                        default -> "";
                    };

                    rowMap.put(headers.get(i), value.trim());
                }

                rows.add(rowMap);
            }

            return rows;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage(), e);
        }
    }
}
