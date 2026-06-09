package com.jobsearchanalytics.controller;

import com.jobsearchanalytics.dto.response.ApiResponse;
import com.jobsearchanalytics.dto.response.ImportSummary;
import com.jobsearchanalytics.service.importer.ImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/import")
@RequiredArgsConstructor
public class ImportController {

    private final ImportService importService;

    @PostMapping
    public ApiResponse<ImportSummary> importFile(
            @RequestParam("file") MultipartFile file) {

        return new ApiResponse<>(
                true,
                "Import completed",
                importService.importFile(file)
        );
    }
}
