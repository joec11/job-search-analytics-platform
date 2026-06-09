package com.jobsearchanalytics.service.importer;

import com.jobsearchanalytics.dto.response.ImportSummary;
import org.springframework.web.multipart.MultipartFile;

public interface ImportService {

    ImportSummary importFile(MultipartFile file);

}
