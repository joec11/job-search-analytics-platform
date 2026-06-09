package com.jobsearchanalytics.service.importer;

import com.jobsearchanalytics.dto.response.ImportSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportServiceImpl implements ImportService {

    @Override
    public ImportSummary importFile(MultipartFile file) {

        return new ImportSummary(
                0,
                0,
                0,
                List.of()
        );
    }
}
