package com.example.textanalyzer.model;

public enum AnalysisType {
    VOWEL("vowel"),
    CONSONANT("consonant"),
    BOTH("both");

    private final String value;

    AnalysisType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AnalysisType fromString(String value) {
        for (AnalysisType type : AnalysisType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid analysis type: " + value + ". Use 'vowel', 'consonant', or 'both'");
    }
}