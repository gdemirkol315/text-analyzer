package com.example.textanalyzer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnalysisTypeTest {

    @Test
    void fromString_ValidVowel_ReturnsVowelType() {
        assertEquals(AnalysisType.VOWEL, AnalysisType.fromString("vowel"));
    }

    @Test
    void fromString_ValidConsonant_ReturnsConsonantType() {
        assertEquals(AnalysisType.CONSONANT, AnalysisType.fromString("consonant"));
    }

    @Test
    void fromString_ValidBoth_ReturnsBothType() {
        assertEquals(AnalysisType.BOTH, AnalysisType.fromString("both"));
    }

    @Test
    void fromString_CaseInsensitive_ReturnsCorrectType() {
        assertEquals(AnalysisType.VOWEL, AnalysisType.fromString("VOWEL"));
        assertEquals(AnalysisType.CONSONANT, AnalysisType.fromString("Consonant"));
        assertEquals(AnalysisType.BOTH, AnalysisType.fromString("BOTH"));
    }

    @Test
    void fromString_InvalidType_ThrowsException() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> AnalysisType.fromString("invalid")
        );
        assertTrue(exception.getMessage().contains("Invalid analysis type: invalid"));
    }

    @Test
    void fromString_NullInput_ThrowsException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> AnalysisType.fromString(null)
        );
    }

    @Test
    void fromString_EmptyInput_ThrowsException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> AnalysisType.fromString("")
        );
    }

    @Test
    void getValue_ReturnsCorrectValues() {
        assertEquals("vowel", AnalysisType.VOWEL.getValue());
        assertEquals("consonant", AnalysisType.CONSONANT.getValue());
        assertEquals("both", AnalysisType.BOTH.getValue());
    }
}