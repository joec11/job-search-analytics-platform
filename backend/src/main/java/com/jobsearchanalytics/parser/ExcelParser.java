package com.jobsearchanalytics.parser;

import com.jobsearchanalytics.util.ColumnMapper;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.*;

@Component
public class ExcelParser implements FileParser {

    @Override
    public boolean supports(String filename) {
        return filename != null &&
                (filename.toLowerCase().endsWith(".xlsx")
                        || filename.toLowerCase().endsWith(".xls"));
    }

    @Override
    public List<Map<String, String>> parse(MultipartFile file) {

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();

            if (!rows.hasNext()) {
                throw new RuntimeException("Excel file is empty");
            }

            // Header row
            Row headerRow = rows.next();

            List<String> headers = new ArrayList<>();

            for (Cell cell : headerRow) {
                headers.add(ColumnMapper.normalize(cell.getStringCellValue().trim()));
            }

            List<Map<String, String>> result = new ArrayList<>();

            while (rows.hasNext()) {

                Row row = rows.next();

                Map<String, String> map = new LinkedHashMap<>();

                for (int i = 0; i < headers.size(); i++) {

                    Cell cell = row.getCell(
                            i,
                            Row.MissingCellPolicy.CREATE_NULL_AS_BLANK
                    );

                    map.put(headers.get(i), getCellValue(cell));
                }

                result.add(map);
            }

            return result;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to parse Excel file: " + e.getMessage(),
                    e
            );
        }
    }

    /**
     * Converts an Excel cell into a normalized String value.
     */
    private String getCellValue(Cell cell) {

        if (cell == null) {
            return "";
        }

        return switch (cell.getCellType()) {

            case STRING ->
                    cell.getStringCellValue().trim();

            case BOOLEAN ->
                    String.valueOf(cell.getBooleanCellValue());

            case NUMERIC -> {

                if (DateUtil.isCellDateFormatted(cell)) {

                    LocalDate date = cell
                            .getLocalDateTimeCellValue()
                            .toLocalDate();

                    yield date.toString();   // ISO-8601 (yyyy-MM-dd)
                }

                double value = cell.getNumericCellValue();

                if (value == Math.floor(value)) {
                    yield String.valueOf((long) value);
                }

                yield String.valueOf(value);
            }

            case FORMULA -> {

                FormulaEvaluator evaluator =
                        cell.getSheet()
                                .getWorkbook()
                                .getCreationHelper()
                                .createFormulaEvaluator();

                CellValue evaluated = evaluator.evaluate(cell);

                yield switch (evaluated.getCellType()) {

                    case STRING ->
                            evaluated.getStringValue().trim();

                    case BOOLEAN ->
                            String.valueOf(evaluated.getBooleanValue());

                    case NUMERIC -> {

                        if (DateUtil.isCellDateFormatted(cell)) {

                            LocalDate date = cell
                                    .getLocalDateTimeCellValue()
                                    .toLocalDate();

                            yield date.toString();
                        }

                        double value = evaluated.getNumberValue();

                        if (value == Math.floor(value)) {
                            yield String.valueOf((long) value);
                        }

                        yield String.valueOf(value);
                    }

                    default -> "";
                };
            }

            case BLANK -> "";

            default -> "";
        };
    }

}
