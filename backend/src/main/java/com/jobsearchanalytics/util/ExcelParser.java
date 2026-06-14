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

            // -----------------------------
            // HEADER ROW
            // -----------------------------
            Row headerRow = rowIterator.next();
            List<String> headers = new ArrayList<>();

            for (Cell cell : headerRow) {
                String rawHeader = getCellString(cell).trim();
                String normalizedHeader = ColumnMapper.map(rawHeader);
                headers.add(normalizedHeader);
            }

            List<Map<String, String>> rows = new ArrayList<>();

            // -----------------------------
            // DATA ROWS
            // -----------------------------
            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();

                // skip completely empty rows
                if (isRowEmpty(row)) continue;

                Map<String, String> rowMap = new HashMap<>();

                for (int i = 0; i < headers.size(); i++) {

                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    String value = normalizeValue(getCellString(cell));

                    rowMap.put(headers.get(i), value);
                }

                rows.add(rowMap);
            }

            return rows;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage(), e);
        }
    }

    // -----------------------------
    // SAFE CELL READER
    // -----------------------------
    private String getCellString(Cell cell) {
        if (cell == null) return "";

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();

            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    yield cell.getLocalDateTimeCellValue()
                        .toLocalDate()
                        .toString();
                }
                
                double value = cell.getNumericCellValue();
                
                if (value == (long) value) {
                    yield String.valueOf((long) value);
                }
                
                yield String.valueOf(value);
            }

            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }

    // -----------------------------
    // VALUE NORMALIZATION
    // -----------------------------
    private String normalizeValue(String value) {

        if (value == null) return null;

        String v = value.trim();

        if (v.isEmpty()) return null;
        if (v.equals("--")) return null;

        return v;
    }

    // -----------------------------
    // EMPTY ROW CHECK
    // -----------------------------
    private boolean isRowEmpty(Row row) {

        if (row == null) return true;

        for (Cell cell : row) {
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                if (!getCellString(cell).trim().isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }
}
