import { Injectable } from '@angular/core';
import { AnalysisResult } from '../models/text-analysis.model';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  private readonly STORAGE_KEY = 'textAnalysisResults';

  saveResults(results: AnalysisResult[]): void {
    try {
      // Convert Map objects to arrays for JSON serialization
      const serializedResults = results.map(result => ({
        ...result,
        analysis: {
          ...result.analysis,
          vowels: Array.from(result.analysis.vowels.entries()),
          consonants: Array.from(result.analysis.consonants.entries())
        }
      }));
      
      sessionStorage.setItem(this.STORAGE_KEY, JSON.stringify(serializedResults));
    } catch (error) {
      console.warn('Failed to save results to sessionStorage:', error);
    }
  }

  loadResults(): AnalysisResult[] {
    try {
      const stored = sessionStorage.getItem(this.STORAGE_KEY);
      if (!stored) {
        return [];
      }

      const parsed = JSON.parse(stored);
      
      // Convert arrays back to Map objects
      return parsed.map((result: any) => ({
        ...result,
        timestamp: new Date(result.timestamp), // Convert back to Date object
        analysis: {
          ...result.analysis,
          vowels: new Map(result.analysis.vowels),
          consonants: new Map(result.analysis.consonants)
        }
      }));
    } catch (error) {
      console.warn('Failed to load results from sessionStorage:', error);
      return [];
    }
  }

  clearResults(): void {
    try {
      sessionStorage.removeItem(this.STORAGE_KEY);
    } catch (error) {
      console.warn('Failed to clear results from sessionStorage:', error);
    }
  }
}