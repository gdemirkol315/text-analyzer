import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { TextAnalysis, AnalysisType } from '../models/text-analysis.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private readonly API_VERSION = 'v1';
  private baseUrl = `http://localhost:8080/api/${this.API_VERSION}/analyze`;

  constructor(private http: HttpClient) {}

  analyzeText(text: string, type: AnalysisType): Observable<TextAnalysis> {
    const params = new URLSearchParams();
    params.set('text', text);
    params.set('type', type);

    return this.http.post<any>(this.baseUrl, params.toString(), {
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    }).pipe(
      map(response => this.mapBackendResponse(response)),
      catchError(this.handleError)
    );
  }

  private mapBackendResponse(response: any): TextAnalysis {
    return {
      text: response.text,
      vowels: new Map(Object.entries(response.vowels || {})),
      consonants: new Map(Object.entries(response.consonants || {}))
      //If response.vowels/consonants is null, undefined, or falsy, it defaults to an empty object {}
    };
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An error occurred';

    if (error.error instanceof ErrorEvent) {
      errorMessage = `Client error: ${error.error.message}`;
    } else {
      errorMessage = `Server error: ${error.status} - ${error.message}`;
    }

    console.error('API Service Error:', errorMessage);
    return throwError(() => new Error(errorMessage));
  }
}
