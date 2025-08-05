package com.example.textanalyzer.model;

import lombok.Data;

import java.util.Map;

@Data
public class TextAnalysis {
    private String text;
    private Map<Character,Integer> vowels;
    private Map<Character,Integer> consonants;
}
