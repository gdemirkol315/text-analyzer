import { TestBed } from '@angular/core/testing';
import { TextAnalyzerService } from './text-analyzer.service';

describe('TextAnalyzerService', () => {
  let service: TextAnalyzerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TextAnalyzerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('analyzeAll', () => {
    it('should analyze both vowels and consonants correctly', () => {
      const result = service.analyzeAll('gorkEm.?lIKEScoDing!');

      // Check vowels
      expect(result.vowels.get('o')).toBe(2);
      expect(result.vowels.get('e')).toBe(2);
      expect(result.vowels.get('i')).toBe(2);
      expect(result.vowels.size).toBe(3);

      // Check consonants
      expect(result.consonants.get('g')).toBe(2);
      expect(result.consonants.get('r')).toBe(1);
      expect(result.consonants.get('k')).toBe(2);
      expect(result.consonants.get('m')).toBe(1);
      expect(result.consonants.get('l')).toBe(1);
      expect(result.consonants.get('s')).toBe(1);
      expect(result.consonants.get('c')).toBe(1);
      expect(result.consonants.get('d')).toBe(1);
      expect(result.consonants.get('n')).toBe(1);
      expect(result.consonants.size).toBe(9);

      expect(result.text).toBe('gorkEm.?lIKEScoDing!');
    });

    it('should handle empty string', () => {
      const result = service.analyzeAll('');

      expect(result.vowels.size).toBe(0);
      expect(result.consonants.size).toBe(0);
      expect(result.text).toBe('');
    });

    it('should ignore non-alphabetic characters', () => {
      const result = service.analyzeAll('123!@#$%^&*()');

      expect(result.vowels.size).toBe(0);
      expect(result.consonants.size).toBe(0);
      expect(result.text).toBe('123!@#$%^&*()');
    });

    it('should handle mixed case properly', () => {
      const result = service.analyzeAll('AaEeIiOoUu');

      expect(result.vowels.get('a')).toBe(2);
      expect(result.vowels.get('e')).toBe(2);
      expect(result.vowels.get('i')).toBe(2);
      expect(result.vowels.get('o')).toBe(2);
      expect(result.vowels.get('u')).toBe(2);
      expect(result.vowels.size).toBe(5);
      expect(result.consonants.size).toBe(0);
    });
  });

  describe('analyzeVowels', () => {
    it('should analyze only vowels', () => {
      const result = service.analyzeVowels('gorkEm.?lIKEScoDing!');

      // Check vowels
      expect(result.vowels.get('o')).toBe(2);
      expect(result.vowels.get('e')).toBe(2);
      expect(result.vowels.get('i')).toBe(2);
      expect(result.vowels.size).toBe(3);

      // Consonants should be empty
      expect(result.consonants.size).toBe(0);

      expect(result.text).toBe('gorkEm.?lIKEScoDing!');
    });

    it('should handle text with no vowels', () => {
      const result = service.analyzeVowels('bcdfg');

      expect(result.vowels.size).toBe(0);
      expect(result.consonants.size).toBe(0);
      expect(result.text).toBe('bcdfg');
    });

    it('should handle text with only vowels', () => {
      const result = service.analyzeVowels('aeiou');

      expect(result.vowels.get('a')).toBe(1);
      expect(result.vowels.get('e')).toBe(1);
      expect(result.vowels.get('i')).toBe(1);
      expect(result.vowels.get('o')).toBe(1);
      expect(result.vowels.get('u')).toBe(1);
      expect(result.vowels.size).toBe(5);
      expect(result.consonants.size).toBe(0);
    });
  });

  describe('analyzeConsonants', () => {
    it('should analyze only consonants', () => {
      const result = service.analyzeConsonants('gorkEm.?lIKEScoDing!');

      // Vowels should be empty
      expect(result.vowels.size).toBe(0);

      // Check consonants
      expect(result.consonants.get('g')).toBe(2);
      expect(result.consonants.get('r')).toBe(1);
      expect(result.consonants.get('k')).toBe(2);
      expect(result.consonants.get('m')).toBe(1);
      expect(result.consonants.get('l')).toBe(1);
      expect(result.consonants.get('s')).toBe(1);
      expect(result.consonants.get('c')).toBe(1);
      expect(result.consonants.get('d')).toBe(1);
      expect(result.consonants.get('n')).toBe(1);
      expect(result.consonants.size).toBe(9);

      expect(result.text).toBe('gorkEm.?lIKEScoDing!');
    });

    it('should handle text with no consonants', () => {
      const result = service.analyzeConsonants('aeiou');

      expect(result.vowels.size).toBe(0);
      expect(result.consonants.size).toBe(0);
      expect(result.text).toBe('aeiou');
    });

    it('should handle text with only consonants', () => {
      const result = service.analyzeConsonants('bcdfg');

      expect(result.vowels.size).toBe(0);
      expect(result.consonants.get('b')).toBe(1);
      expect(result.consonants.get('c')).toBe(1);
      expect(result.consonants.get('d')).toBe(1);
      expect(result.consonants.get('f')).toBe(1);
      expect(result.consonants.get('g')).toBe(1);
      expect(result.consonants.size).toBe(5);
    });
  });

  describe('analyze', () => {
    it('should delegate to analyzeVowels for vowel type', () => {
      spyOn(service, 'analyzeVowels').and.returnValue({
        text: 'test',
        vowels: new Map([['e', 1]]),
        consonants: new Map()
      });

      const result = service.analyze('test', 'vowel');

      expect(service.analyzeVowels).toHaveBeenCalledWith('test');
      expect(result.vowels.get('e')).toBe(1);
    });

    it('should delegate to analyzeConsonants for consonant type', () => {
      spyOn(service, 'analyzeConsonants').and.returnValue({
        text: 'test',
        vowels: new Map(),
        consonants: new Map([['t', 2]])
      });

      const result = service.analyze('test', 'consonant');

      expect(service.analyzeConsonants).toHaveBeenCalledWith('test');
      expect(result.consonants.get('t')).toBe(2);
    });

    it('should delegate to analyzeAll for both type', () => {
      spyOn(service, 'analyzeAll').and.returnValue({
        text: 'test',
        vowels: new Map([['e', 1]]),
        consonants: new Map([['t', 2]])
      });

      const result = service.analyze('test', 'both');

      expect(service.analyzeAll).toHaveBeenCalledWith('test');
      expect(result.vowels.get('e')).toBe(1);
      expect(result.consonants.get('t')).toBe(2);
    });

    it('should throw error for unknown analysis type', () => {
      expect(() => {
        service.analyze('test', 'unknown' as any);
      }).toThrowError('Unknown analysis type: unknown');
    });
  });
});
