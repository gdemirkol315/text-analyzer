# Text Analyzer Frontend

Angular application providing an interactive UI for text analysis with vowel and consonant counting capabilities.

## Technology Stack

- **Framework**: Angular 20.1.4
- **UI Library**: Angular Material 20.1.4
- **Notifications**: ngx-toastr 19.0.0
- **Build Tool**: Angular CLI 20.1.4
- **Testing**: Karma

## Features

### Core Functionality
- **Text Analysis**: Real-time analysis of vowels and consonants
- **Analysis Types**: 
  - Vowel analysis only
  - Consonant analysis only  
  - Combined vowel and consonant analysis
- **Dual Mode Operation**:
  - **Online Mode**: Uses backend API (`http://localhost:8080/api/v1/analyze`)
  - **Offline Mode**: Client-side analysis with automatic fallback
- **Results Management**:
  - Session-based storage of analysis history
  - Expandable result cards with detailed character frequency tables
  - Clear all results functionality

### User Experience
- **Material Design**: Clean, responsive interface
- **Toast Notifications**: User feedback for actions and errors
- **Loading States**: Progress indicators during API calls
- **Form Validation**: Input validation with error messages
- **Mode Toggle**: Easy switching between online/offline modes

## Getting Started

### Prerequisites
- Node.js 18+ 
- npm

### Installation & Development

```bash
# Install dependencies
npm install

# Start development server
ng serve
```

The application runs on `http://localhost:4200/` and will automatically reload when you modify source files.

### Building

```bash
# Development build
ng build

# Production build
ng build --configuration production
```

Build artifacts are stored in the `dist/` directory.

## Architecture

### Component Structure
```
src/app/
├── components/
│   ├── text-analyzer/           # Main analysis component
│   │   ├── text-analyzer.component.ts
│   │   ├── text-analyzer.component.html
│   │   └── text-analyzer.component.css
│   └── analysis-results/        # Results display component
│       ├── analysis-results.component.ts
│       ├── analysis-results.component.html
│       └── analysis-results.component.css
├── models/
│   └── text-analysis.model.ts   # TypeScript interfaces
├── services/
│   ├── text-analyzer.service.ts # Offline analysis logic
│   ├── api.service.ts           # Backend API communication
│   └── storage.service.ts       # Session storage management
└── app.config.ts               # Application configuration
```

### Key Components

#### TextAnalyzer Component
- **Location**: `components/text-analyzer/text-analyzer.component.ts:34`
- **Purpose**: Main UI component for text input and analysis controls
- **Features**:
  - Text input with analysis type selection
  - Online/offline mode toggle
  - Results management and display

#### AnalysisResults Component  
- **Location**: `components/analysis-results/analysis-results.component.ts:24`
- **Purpose**: Displays analysis results in expandable cards
- **Features**:
  - Character frequency tables
  - Total counts for vowels/consonants
  - Responsive Material Design layout

### Services

#### TextAnalyzerService
- **Location**: `services/text-analyzer.service.ts:7`
- **Purpose**: Client-side text analysis logic
- **Methods**:
  - `analyze()`: Main analysis dispatcher
  - `analyzeVowels()`: Vowel-only analysis
  - `analyzeConsonants()`: Consonant-only analysis
  - `analyzeAll()`: Combined analysis

#### ApiService
- **Location**: `services/api.service.ts`
- **Purpose**: HTTP client for backend communication
- **Features**: API calls with error handling and fallback support

#### StorageService
- **Location**: `services/storage.service.ts`
- **Purpose**: Session storage management for analysis results

## Development Workflows

### Code Scaffolding

```bash
# Generate new component
ng generate component component-name

# Generate new service
ng generate service service-name

# Generate new interface
ng generate interface interface-name

# See all available schematics
ng generate --help
```

### Testing

```bash
# Run unit tests
ng test

# Run tests with coverage
ng test --code-coverage

# Run tests in watch mode
ng test --watch
```

Tests use Karma test runner with Jasmine framework.

### Code Quality

The project includes Prettier configuration for consistent code formatting:

```json
{
  "overrides": [
    {
      "files": "*.html", 
      "options": {
        "parser": "angular"
      }
    }
  ]
}
```

## Configuration

### Backend Integration
The frontend communicates with the Spring Boot backend running on `http://localhost:8080`. API calls are made to:
- `POST /api/v1/analyze` - Text analysis endpoint

### Session Storage
Analysis results and user preferences are stored in browser session storage:
- `analysisResults`: Array of analysis history
- `isOnlineMode`: User's preferred analysis mode

## Deployment

### Development Deployment
```bash
ng serve
```

### Production Deployment
```bash
# Build for production
ng build --configuration production

# Serve static files from dist/ directory
# Use any static file server (nginx, Apache, etc.)
```

### Docker Deployment
```dockerfile
FROM nginx:alpine
COPY dist/frontend /usr/share/nginx/html
EXPOSE 80
```

## Additional Resources

- [Angular Documentation](https://angular.dev/)
- [Angular Material Components](https://material.angular.io/)
- [Angular CLI Reference](https://angular.dev/tools/cli)
