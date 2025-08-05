package com.example.textanalyzer.controller;

import com.example.textanalyzer.exception.GlobalExceptionHandler;
import com.example.textanalyzer.model.TextAnalysis;
import com.example.textanalyzer.service.TextAnalyzerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class TextAnalyzerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TextAnalyzerService textAnalyzerService;

    @BeforeEach
    void setUp() {
        TextAnalyzerController controller = new TextAnalyzerController(textAnalyzerService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void analyzeText_VowelType_ReturnsCorrectAnalysis() throws Exception {
        TextAnalysis mockAnalysis = new TextAnalysis();
        mockAnalysis.setText("hello");
        Map<Character, Integer> vowels = new HashMap<>();
        vowels.put('e', 1);
        vowels.put('o', 1);
        mockAnalysis.setVowels(vowels);
        
        when(textAnalyzerService.analyzeVowels("hello")).thenReturn(mockAnalysis);
        
        mockMvc.perform(post("/api/text-analysis/analyze")
                .param("text", "hello")
                .param("type", "vowel"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("hello"))
                .andExpect(jsonPath("$.vowels.e").value(1))
                .andExpect(jsonPath("$.vowels.o").value(1));
    }

    @Test
    void analyzeText_ConsonantType_ReturnsCorrectAnalysis() throws Exception {
        TextAnalysis mockAnalysis = new TextAnalysis();
        mockAnalysis.setText("hello");
        Map<Character, Integer> consonants = new HashMap<>();
        consonants.put('h', 1);
        consonants.put('l', 2);
        mockAnalysis.setConsonants(consonants);
        
        when(textAnalyzerService.analyzeConsonants("hello")).thenReturn(mockAnalysis);
        
        mockMvc.perform(post("/api/text-analysis/analyze")
                .param("text", "hello")
                .param("type", "consonant"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("hello"))
                .andExpect(jsonPath("$.consonants.h").value(1))
                .andExpect(jsonPath("$.consonants.l").value(2));
    }

    @Test
    void analyzeText_BothType_ReturnsCorrectAnalysis() throws Exception {
        TextAnalysis mockAnalysis = new TextAnalysis();
        mockAnalysis.setText("hello");
        Map<Character, Integer> vowels = new HashMap<>();
        vowels.put('e', 1);
        vowels.put('o', 1);
        Map<Character, Integer> consonants = new HashMap<>();
        consonants.put('h', 1);
        consonants.put('l', 2);
        mockAnalysis.setVowels(vowels);
        mockAnalysis.setConsonants(consonants);
        
        when(textAnalyzerService.analyzeAll("hello")).thenReturn(mockAnalysis);
        
        mockMvc.perform(post("/api/text-analysis/analyze")
                .param("text", "hello")
                .param("type", "both"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("hello"))
                .andExpect(jsonPath("$.vowels.e").value(1))
                .andExpect(jsonPath("$.consonants.h").value(1));
    }

    @Test
    void analyzeText_InvalidType_ReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/text-analysis/analyze")
                .param("text", "hello")
                .param("type", "invalid"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.error").value("Invalid analysis type: invalid. Use 'vowel', 'consonant', or 'both'"));
    }

    @Test
    void analyzeText_MissingTextParameter_ReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/text-analysis/analyze")
                .param("type", "vowel"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void analyzeText_MissingTypeParameter_ReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/text-analysis/analyze")
                .param("text", "hello"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test 
    void analyzeText_CaseInsensitiveType_ReturnsOk() throws Exception {
        TextAnalysis mockAnalysis = new TextAnalysis();
        when(textAnalyzerService.analyzeVowels(anyString())).thenReturn(mockAnalysis);
        
        mockMvc.perform(post("/api/text-analysis/analyze")
                .param("text", "hello")
                .param("type", "VOWEL"))
                .andExpect(status().isOk());
    }

}