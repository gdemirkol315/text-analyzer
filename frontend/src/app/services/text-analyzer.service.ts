import { Injectable } from '@angular/core';
import { TextAnalysis, AnalysisType } from '../models/text-analysis.model';

@Injectable({
  providedIn: 'root'
})
export class TextAnalyzerService {
  private readonly VOWELS = 'aeiou';

  analyze(text: string, type: AnalysisType): TextAnalysis {
    switch (type) {
      case 'vowel':
        return this.analyzeVowels(text);
      case 'consonant':
        return this.analyzeConsonants(text);
      case 'both':
        return this.analyzeAll(text);
      default:
        throw new Error(`Unknown analysis type: ${type}`);
    }
  }

  analyzeAll(text: string): TextAnalysis {
    const vowels = new Map<string, number>();
    const consonants = new Map<string, number>();

    const lowerText = text.toLowerCase();
    
    for (const char of lowerText) {
      if (this.isAlphabetic(char)) {
        if (this.VOWELS.includes(char)) {
          vowels.set(char, (vowels.get(char) || 0) + 1);
        } else {
          consonants.set(char, (consonants.get(char) || 0) + 1);
        }
      }
    }

    return {
      text,
      vowels,
      consonants
    };
  }

  analyzeVowels(text: string): TextAnalysis {
    const vowels = new Map<string, number>();
    const consonants = new Map<string, number>();

    const lowerText = text.toLowerCase();
    
    for (const char of lowerText) {
      if (this.isAlphabetic(char) && this.VOWELS.includes(char)) {
        vowels.set(char, (vowels.get(char) || 0) + 1);
      }
    }

    return {
      text,
      vowels,
      consonants
    };
  }

  analyzeConsonants(text: string): TextAnalysis {
    const vowels = new Map<string, number>();
    const consonants = new Map<string, number>();

    const lowerText = text.toLowerCase();
    
    for (const char of lowerText) {
      if (this.isAlphabetic(char) && !this.VOWELS.includes(char)) {
        consonants.set(char, (consonants.get(char) || 0) + 1);
      }
    }

    return {
      text,
      vowels,
      consonants
    };
  }

  private isAlphabetic(char: string): boolean {
    return /^[a-zA-Z]$/.test(char);
  }
}