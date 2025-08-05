import { Component, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';

import { AnalysisResult, AnalysisType } from '../../models/text-analysis.model';
import { AnalysisResults } from '../analysis-results/analysis-results.component';
import { TextAnalyzerService } from '../../services/text-analyzer.service';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-text-analyzer',
  imports: [
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    FormsModule,
    AnalysisResults
  ],
  templateUrl: './text-analyzer.component.html',
  styleUrl: './text-analyzer.component.css'
})
export class TextAnalyzer implements OnInit {
  textInput = '';
  selectedAnalysis = '';
  results: AnalysisResult[] = [];

  analysisTypes = [
    { value: 'vowel', label: 'Vowel Analysis' },
    { value: 'consonant', label: 'Consonant Analysis' },
    { value: 'both', label: 'Vowel and Consonant Analysis' }
  ];

  constructor(
    private textAnalyzerService: TextAnalyzerService,
    private storageService: StorageService
  ) {}

  ngOnInit(): void {
    // Load previously saved results from sessionStorage
    this.results = this.storageService.loadResults();
  }

  executeAnalysis() {
    if (this.textInput && this.selectedAnalysis) {
      const analysis = this.textAnalyzerService.analyze(
        this.textInput,
        this.selectedAnalysis as AnalysisType
      );

      const result: AnalysisResult = {
        id: this.generateId(),
        timestamp: new Date(),
        analysis,
        analysisType: this.getAnalysisTypeLabel(this.selectedAnalysis)
      };

      // Add new result to the beginning of the array (newest first)
      this.results.unshift(result);

      // Save to sessionStorage
      this.storageService.saveResults(this.results);

      // Clear the input for next analysis
      this.textInput = '';
      this.selectedAnalysis = '';
    }
  }

  private generateId(): string {
    return Date.now().toString() + Math.random().toString(36).substr(2, 9);
  }

  clearAllResults(): void {
    this.results = [];
    this.storageService.clearResults();
  }

  private getAnalysisTypeLabel(type: string): string {
    const typeMap: { [key: string]: string } = {
      'vowel': 'Vowel',
      'consonant': 'Consonant',
      'both': 'Vowel and Consonant'
    };
    return typeMap[type] || type;
  }
}
