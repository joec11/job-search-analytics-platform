package com.jobsearchanalytics.dto.response;

import java.util.List;
import java.util.Map;

public class ImportPreviewResponse {

    private int totalRows;
    private int validRows;
    private int invalidRows;

    private List<Map<String, String>> preview;
    private List<Map<String, Object>> errors;

    public ImportPreviewResponse() {}

    public ImportPreviewResponse(
            int totalRows,
            int validRows,
            int invalidRows,
            List<Map<String, String>> preview,
            List<Map<String, Object>> errors
    ) {
        this.totalRows = totalRows;
        this.validRows = validRows;
        this.invalidRows = invalidRows;
        this.preview = preview;
        this.errors = errors;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getValidRows() {
        return validRows;
    }

    public void setValidRows(int validRows) {
        this.validRows = validRows;
    }

    public int getInvalidRows() {
        return invalidRows;
    }

    public void setInvalidRows(int invalidRows) {
        this.invalidRows = invalidRows;
    }

    public List<Map<String, String>> getPreview() {
        return preview;
    }

    public void setPreview(List<Map<String, String>> preview) {
        this.preview = preview;
    }

    public List<Map<String, Object>> getErrors() {
        return errors;
    }

    public void setErrors(List<Map<String, Object>> errors) {
        this.errors = errors;
    }
}
