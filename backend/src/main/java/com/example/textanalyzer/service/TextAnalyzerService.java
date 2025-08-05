package com.example.textanalyzer.service;

import com.example.textanalyzer.model.TextAnalysis;

import java.util.HashMap;
import java.util.Map;

/**
 * This class counts number of vowels and consonants in a Text and saving this values in a TextAnalysis instance
 */
public class TextAnalyzerService {
    private final String vowels = "aeiou";

    public TextAnalysis analyzeAll(String textToAnalyze) {
        TextAnalysis textAnalysis = new TextAnalysis();
        textAnalysis.setText(textToAnalyze);

        getVowels(textAnalysis);
        getConsonants(textAnalysis);
        return textAnalysis;
    }

    public TextAnalysis analyzeVowels(String textToAnalyze) {
        TextAnalysis textAnalysis = new TextAnalysis();
        textAnalysis.setText(textToAnalyze);

        getVowels(textAnalysis);
        return textAnalysis;
    }

    public TextAnalysis analyzeConsonants(String textToAnalyze) {
        TextAnalysis textAnalysis = new TextAnalysis();
        textAnalysis.setText(textToAnalyze);

        getConsonants(textAnalysis);
        return textAnalysis;
    }

    private void getVowels(TextAnalysis textAnalysis) {
        Map<Character, Integer> vowelsGathered = new HashMap<>();
        for (char c : textAnalysis.getText().toLowerCase().toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                vowelsGathered.merge(c, 1, Integer::sum);
            }
        }
        textAnalysis.setVowels(vowelsGathered);
    }

    private void getConsonants(TextAnalysis textAnalysis) {
        Map<Character, Integer> consonantsGathered = new HashMap<>();
        for (char c : textAnalysis.getText().toLowerCase().toCharArray()) {
            if (vowels.indexOf(c) == -1 && Character.isLetter(c)) {
                consonantsGathered.merge(c, 1, Integer::sum);
            }
        }
        textAnalysis.setConsonants(consonantsGathered);
    }
}
