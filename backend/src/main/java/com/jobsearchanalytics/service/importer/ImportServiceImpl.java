package com.jobsearchanalytics.service.importer;

import com.jobsearchanalytics.dto.response.ImportError;
import com.jobsearchanalytics.dto.response.ImportSummary;
import com.jobsearchanalytics.model.JobApplication;
import com.jobsearchanalytics.repository.JobApplicationRepository;
import com.jobsearchanalytics.util.ExcelParser;
import com.jobsearchanalytics.util.JobApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImportServiceImpl implements ImportService {

    private final ExcelParser excelParser;
    private final JobApplicationRepository repository;

    @Override
    public ImportSummary importFile(MultipartFile file) {

        List<Map<String, String>> rows = excelParser.parse(file);

        List<JobApplication> validApplications = new ArrayList<>();
        List<ImportError> errors = new ArrayList<>();

        int rowNumber = 1;

        for (Map<String, String> row : rows) {

            try {
                JobApplication job = JobApplicationMapper.fromRow(row);

                // -------------------------
                // REQUIRED FIELD VALIDATION
                // -------------------------

                if (job.getJobTitle() == null || job.getJobTitle().isBlank()) {
                    errors.add(new ImportError(rowNumber, "jobTitle is required"));
                    rowNumber++;
                    continue;
                }

                if (job.getCompanyName() == null || job.getCompanyName().isBlank()) {
                    errors.add(new ImportError(rowNumber, "companyName is required"));
                    rowNumber++;
                    continue;
                }

                validApplications.add(job);

            } catch (Exception e) {
                errors.add(new ImportError(
                        rowNumber,
                        "Unexpected error: " + e.getMessage()
                ));
            }

            rowNumber++;
        }

        repository.saveAll(validApplications);

        return new ImportSummary(
                rows.size(),
                validApplications.size(),
                errors.size(),
                errors
        );
    }
}
