package com.example.textanalyzer.service;

import com.example.textanalyzer.model.TextAnalysis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextAnalyzerServiceTest {

    private final TextAnalyzerService textAnalyzerService = new TextAnalyzerService();

    @Test
    void analyzeText_All() {
        TextAnalysis textAnalysis = textAnalyzerService.analyzeAll("gorkEm.?lIKEScoDing!");
        assertEquals(2, textAnalysis.getVowels().get('o'));
        assertEquals(2, textAnalysis.getVowels().get('e'));
        assertEquals(2, textAnalysis.getVowels().get('i'));
        assertEquals(3, textAnalysis.getVowels().size());

        assertEquals(2, textAnalysis.getConsonants().get('g'));
        assertEquals(1, textAnalysis.getConsonants().get('m'));
        assertEquals(2, textAnalysis.getConsonants().get('k'));
        assertEquals(9, textAnalysis.getConsonants().size());

        assertNull(textAnalysis.getConsonants().get('!'));
        assertNull(textAnalysis.getConsonants().get('?'));
        assertNull(textAnalysis.getConsonants().get('.'));
    }

    @Test
    void analyzeText_OnlyVowel() {
        TextAnalysis textAnalysis = textAnalyzerService.analyzeVowels("gorkEm.?lIKEScoDing!");
        assertEquals(2, textAnalysis.getVowels().get('o'));
        assertEquals(2, textAnalysis.getVowels().get('e'));
        assertEquals(2, textAnalysis.getVowels().get('i'));
        assertEquals(3, textAnalysis.getVowels().size());

        assertNull(textAnalysis.getConsonants());

        assertNull(textAnalysis.getVowels().get('!'));
        assertNull(textAnalysis.getVowels().get('?'));
        assertNull(textAnalysis.getVowels().get('.'));
    }

    @Test
    void analyzeText_OnlyConsonant() {
        TextAnalysis textAnalysis = textAnalyzerService.analyzeConsonants("gorkEm.?lIKEScoDing!");
        assertNull(textAnalysis.getVowels());

        assertEquals(2, textAnalysis.getConsonants().get('g'));
        assertEquals(1, textAnalysis.getConsonants().get('m'));
        assertEquals(2, textAnalysis.getConsonants().get('k'));
        assertEquals(9, textAnalysis.getConsonants().size());

        assertNull(textAnalysis.getConsonants().get('!'));
        assertNull(textAnalysis.getConsonants().get('?'));
        assertNull(textAnalysis.getConsonants().get('.'));
    }

}