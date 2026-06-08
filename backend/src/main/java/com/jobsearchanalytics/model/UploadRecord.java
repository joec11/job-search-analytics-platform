package com.jobsearchanalytics.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class UploadRecord {

    @Id
    @GeneratedValue
    private UUID id;

    private String fileName;
    private String fileType;
    private String uploadSource;

    private LocalDateTime uploadedAt;

    // getters and setters
}
