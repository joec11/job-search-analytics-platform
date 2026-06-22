package com.jobsearchanalytics.service.importer;

import com.jobsearchanalytics.dto.response.ImportPreviewResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImportPreviewService {
    ImportPreviewResponse previewFile(MultipartFile file);
}
