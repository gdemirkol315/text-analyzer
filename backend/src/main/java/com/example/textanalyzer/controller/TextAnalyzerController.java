package com.example.textanalyzer.controller;

import com.example.textanalyzer.model.AnalysisType;
import com.example.textanalyzer.model.TextAnalysis;
import com.example.textanalyzer.service.TextAnalyzerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/text-analysis")
@CrossOrigin(origins = "*")
@Validated
public class TextAnalyzerController {
    
    private final TextAnalyzerService textAnalyzerService;
    
    public TextAnalyzerController(TextAnalyzerService textAnalyzerService) {
        this.textAnalyzerService = textAnalyzerService;
    }

    /**
     * Analyzes text for vowel and consonant occurrences.
     *
     * @param text the input text to analyze
     * @param type analysis type: "vowel", "consonant", or "both"
     * @return TextAnalysis containing character frequency maps
     * @throws IllegalArgumentException if type is invalid
     */
    @PostMapping("/analyze")
    public TextAnalysis analyzeText(
            @RequestParam @NotBlank String text, 
            @RequestParam @NotBlank String type) {
        AnalysisType analysisType = AnalysisType.fromString(type);
        
        switch (analysisType) {
            case VOWEL:
                return textAnalyzerService.analyzeVowels(text);
            case CONSONANT:
                return textAnalyzerService.analyzeConsonants(text);
            case BOTH:
                return textAnalyzerService.analyzeAll(text);
            default:
                throw new IllegalArgumentException("Invalid analysis type");
        }
    }
}
