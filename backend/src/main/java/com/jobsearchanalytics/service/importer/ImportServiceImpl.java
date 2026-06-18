package com.jobsearchanalytics.service.importer;

import com.jobsearchanalytics.dto.response.ImportError;
import com.jobsearchanalytics.dto.response.ImportSummary;
import com.jobsearchanalytics.model.JobApplication;
import com.jobsearchanalytics.parser.FileParser;
import com.jobsearchanalytics.repository.JobApplicationRepository;
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

    private static final int MAX_ROWS = 5000;

    private final List<FileParser> parsers;
    private final JobApplicationRepository repository;

    @Override
    public ImportSummary importFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Uploaded file is empty");
        }

        FileParser parser = parsers.stream()
                .filter(p -> p.supports(file.getOriginalFilename()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Unsupported file type: " + file.getOriginalFilename()
                ));

        List<Map<String, String>> rows = parser.parse(file);

        if (rows == null || rows.isEmpty()) {
            throw new RuntimeException("No data found in uploaded file");
        }

        if (rows.size() > MAX_ROWS) {
            throw new RuntimeException(
                    "File exceeds maximum allowed rows: " + MAX_ROWS
            );
        }

        List<JobApplication> validApplications = new ArrayList<>();
        List<ImportError> errors = new ArrayList<>();

        int rowNumber = 1;

        for (Map<String, String> row : rows) {

            try {

                JobApplication job = JobApplicationMapper.fromRow(row);

                if (job.getJobTitle() == null ||
                        job.getJobTitle().isBlank()) {

                    errors.add(new ImportError(rowNumber, "jobTitle is required"));
                    rowNumber++;
                    continue;
                }

                if (job.getCompanyName() == null ||
                        job.getCompanyName().isBlank()) {

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
