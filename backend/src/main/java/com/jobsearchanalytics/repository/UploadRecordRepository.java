package com.jobsearchanalytics.repository;

import com.jobsearchanalytics.model.UploadRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UploadRecordRepository extends JpaRepository<UploadRecord, UUID> {
}
