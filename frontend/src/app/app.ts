import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatToolbarModule,
    FormsModule
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('Text Analyzer');
  
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
    }
  }
}
