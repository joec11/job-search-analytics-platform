package com.jobsearchanalytics.controller;

import com.jobsearchanalytics.dto.response.ImportPreviewResponse;
import com.jobsearchanalytics.service.importer.ImportPreviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/import")
public class ImportController {

    private final ImportPreviewService importPreviewService;

    public ImportController(ImportPreviewService importPreviewService) {
        this.importPreviewService = importPreviewService;
    }

    @PostMapping("/preview")
    public ResponseEntity<ImportPreviewResponse> preview(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(importPreviewService.previewFile(file));
    }
}
