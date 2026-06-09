package com.jobsearchanalytics.util;

public final class EnumMapper {

    private EnumMapper() {
    }

    public static String normalize(String value) {

        if (value == null) {
            return null;
        }

        return value
                .trim()
                .toUpperCase()
                .replace("-", "_")
                .replace(" ", "_");
    }
}
