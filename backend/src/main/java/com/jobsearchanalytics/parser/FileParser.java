package com.jobsearchanalytics.parser;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileParser {

    boolean supports(String filename);

    List<Map<String, String>> parse(MultipartFile file);

}
