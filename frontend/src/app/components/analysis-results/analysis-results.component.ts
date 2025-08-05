import { Component, Input } from '@angular/core';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { TextAnalysis, AnalysisResult } from '../../models/text-analysis.model';

interface CharacterCount {
  character: string;
  count: number;
}

@Component({
  selector: 'app-analysis-results',
  imports: [
    MatExpansionModule,
    MatTableModule,
    MatCardModule,
    CommonModule
  ],
  templateUrl: './analysis-results.component.html',
  styleUrl: './analysis-results.component.css'
})
export class AnalysisResults {
  @Input() results: AnalysisResult[] = [];
  
  displayedColumns: string[] = ['character', 'count'];
  
  getVowelData(analysis: TextAnalysis): CharacterCount[] {
    return Array.from(analysis.vowels.entries()).map(([character, count]) => ({
      character,
      count
    }));
  }
  
  getConsonantData(analysis: TextAnalysis): CharacterCount[] {
    return Array.from(analysis.consonants.entries()).map(([character, count]) => ({
      character,
      count
    }));
  }
  
  getTotalVowels(analysis: TextAnalysis): number {
    return Array.from(analysis.vowels.values()).reduce((sum, count) => sum + count, 0);
  }
  
  getTotalConsonants(analysis: TextAnalysis): number {
    return Array.from(analysis.consonants.values()).reduce((sum, count) => sum + count, 0);
  }
  
  hasVowels(analysis: TextAnalysis): boolean {
    return analysis.vowels.size > 0;
  }
  
  hasConsonants(analysis: TextAnalysis): boolean {
    return analysis.consonants.size > 0;
  }
}
