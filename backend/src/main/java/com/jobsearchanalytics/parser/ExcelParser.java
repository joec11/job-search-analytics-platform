package com.jobsearchanalytics.parser;

import com.jobsearchanalytics.util.ColumnMapper;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Component
public class ExcelParser implements FileParser {

    @Override
    public boolean supports(String filename) {
        return filename != null &&
                (filename.toLowerCase().endsWith(".xlsx") ||
                 filename.toLowerCase().endsWith(".xls"));
    }

    @Override
    public List<Map<String, String>> parse(MultipartFile file) {

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();

            if (!rows.hasNext()) {
                throw new RuntimeException("Excel file is empty");
            }

            // header row
            Row headerRow = rows.next();

            List<String> headers = new ArrayList<>();

            for (Cell cell : headerRow) {
                headers.add(ColumnMapper.normalize(cell.getStringCellValue().trim()));
            }

            List<Map<String, String>> result = new ArrayList<>();

            while (rows.hasNext()) {

                Row row = rows.next();

                Map<String, String> map = new HashMap<>();

                for (int i = 0; i < headers.size(); i++) {

                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    String value = switch (cell.getCellType()) {
                        case STRING -> cell.getStringCellValue();
                        case NUMERIC -> String.valueOf(cell.getNumericCellValue());
                        case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
                        default -> "";
                    };

                    map.put(headers.get(i), value.trim());
                }

                result.add(map);
            }

            return result;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage(), e);
        }
    }
}
