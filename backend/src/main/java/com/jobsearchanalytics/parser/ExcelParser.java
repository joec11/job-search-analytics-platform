package com.jobsearchanalytics.parser;

import com.jobsearchanalytics.util.ColumnMapper;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

@Component
public class ExcelParser implements FileParser {

    @Override
    public boolean supports(String filename) {

        return filename != null &&
                (
                        filename.endsWith(".xlsx")
                                ||
                                filename.endsWith(".xls")
                );
    }

    @Override
    public List<Map<String, String>> parse(MultipartFile file) {

        try (InputStream is = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(is)) {

            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            if (!rowIterator.hasNext()) {
                return Collections.emptyList();
            }

            Row headerRow = rowIterator.next();

            List<String> headers = new ArrayList<>();

            for (Cell cell : headerRow) {

                String normalizedHeader = ColumnMapper.map(
                        cell.getStringCellValue()
                );

                headers.add(normalizedHeader);
            }

            List<Map<String, String>> rows = new ArrayList<>();

            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();

                Map<String, String> rowMap = new HashMap<>();

                for (int i = 0; i < headers.size(); i++) {

                    Cell cell = row.getCell(
                            i,
                            Row.MissingCellPolicy.CREATE_NULL_AS_BLANK
                    );

                    rowMap.put(
                            headers.get(i),
                            getCellValue(cell)
                    );
                }

                rows.add(rowMap);
            }

            return rows;

        }
        catch (Exception e) {

            throw new RuntimeException(
                    "Failed to parse Excel file: "
                            + e.getMessage(),
                    e
            );
        }
    }


    private String getCellValue(Cell cell) {

        return switch (cell.getCellType()) {

            case STRING -> cell.getStringCellValue().trim();

            case BOOLEAN ->
                    String.valueOf(
                            cell.getBooleanCellValue()
                    );

            case NUMERIC -> {

                if (DateUtil.isCellDateFormatted(cell)) {

                    yield cell.getLocalDateTimeCellValue()
                            .toLocalDate()
                            .toString();
                }

                double value =
                        cell.getNumericCellValue();

                if (value == (long) value) {

                    yield String.valueOf(
                            (long) value
                    );
                }

                yield String.valueOf(value);
            }

            default -> "";
        };
    }

}
