package com.example.textanalyzer.service;

import com.example.textanalyzer.model.TextAnalysis;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * This class counts number of vowels and consonants in a Text and saving this values in a TextAnalysis instance
 */
@Service
public class TextAnalyzerService {
    private static final String VOWELS = "aeiou";

    public TextAnalysis analyzeAll(String textToAnalyze) {
        TextAnalysis textAnalysis = new TextAnalysis();
        textAnalysis.setText(textToAnalyze);

        textAnalysis.setVowels(getVowels(textAnalysis));
        textAnalysis.setConsonants(getConsonants(textAnalysis));
        return textAnalysis;
    }

    public TextAnalysis analyzeVowels(String textToAnalyze) {
        TextAnalysis textAnalysis = new TextAnalysis();
        textAnalysis.setText(textToAnalyze);

        textAnalysis.setVowels(getVowels(textAnalysis));
        return textAnalysis;
    }

    public TextAnalysis analyzeConsonants(String textToAnalyze) {
        TextAnalysis textAnalysis = new TextAnalysis();
        textAnalysis.setText(textToAnalyze);

        textAnalysis.setConsonants(getConsonants(textAnalysis));
        return textAnalysis;
    }

    private Map<Character, Integer> getVowels(TextAnalysis textAnalysis) {
        Map<Character, Integer> vowelsGathered = new HashMap<>();
        for (char c : textAnalysis.getText().toLowerCase().toCharArray()) {
            boolean isVowel = VOWELS.indexOf(c) != -1 && Character.isLetter(c);
            if (isVowel) {
                vowelsGathered.merge(c, 1, Integer::sum);
            }
        }
        return vowelsGathered;
    }

    private Map<Character, Integer> getConsonants(TextAnalysis textAnalysis) {
        Map<Character, Integer> consonantsGathered = new HashMap<>();
        for (char c : textAnalysis.getText().toLowerCase().toCharArray()) {
            boolean isConsonant = VOWELS.indexOf(c) == -1 && Character.isLetter(c);
            if (isConsonant) {
                consonantsGathered.merge(c, 1, Integer::sum);
            }
        }
        return consonantsGathered;
    }
}
