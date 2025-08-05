export interface TextAnalysis {
  text: string;
  vowels: Map<string, number>;
  consonants: Map<string, number>;
}

export type AnalysisType = 'vowel' | 'consonant' | 'both';

export interface AnalysisResult {
  id: string;
  timestamp: Date;
  analysis: TextAnalysis;
  analysisType: string;
}