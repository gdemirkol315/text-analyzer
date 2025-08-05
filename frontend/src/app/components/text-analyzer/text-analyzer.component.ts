import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-text-analyzer',
  imports: [
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    FormsModule
  ],
  templateUrl: './text-analyzer.component.html',
  styleUrl: './text-analyzer.component.css'
})
export class TextAnalyzer {
  textInput = '';
  selectedAnalysis = '';
  
  analysisTypes = [
    { value: 'vowel', label: 'Vowel Analysis' },
    { value: 'consonant', label: 'Consonant Analysis' },
    { value: 'both', label: 'Vowel and Consonant Analysis' }
  ];
  
  executeAnalysis() {
    if (this.textInput && this.selectedAnalysis) {
      console.log('Executing analysis:', this.selectedAnalysis, 'on text:', this.textInput);
      // TODO: Implement API call to backend
    }
  }
}
