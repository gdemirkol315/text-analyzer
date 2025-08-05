# Text Analyzer

A full-stack text analysis application that analyzes vowels and consonants in text input. Built with Spring Boot backend and Angular frontend.

## Overview

The Text Analyzer provides both online and offline text analysis capabilities:
- **Vowel Analysis**: Count frequency of vowels (a, e, i, o, u)
- **Consonant Analysis**: Count frequency of consonants 
- **Combined Analysis**: Analyze both vowels and consonants together

## Architecture

- **Backend**: Spring Boot 3.5.4 with Java 21
- **Frontend**: Angular 20.1.4 with Material Design
- **Communication**: RESTful API with versioning support
- **Deployment**: Standalone Spring Boot JAR + Angular build

## Quick Start

### Prerequisites
- Java 21+
- Node.js 18+
- npm or yarn

### Running the Application

1. **Start Backend**:
   ```bash
   cd backend
   ./gradlew bootRun
   ```
   Backend runs on `http://localhost:8080`

2. **Start Frontend**:
   ```bash
   cd frontend
   npm install
   npm start
   ```
   Frontend runs on `http://localhost:4200`

3. **Access Application**: Open `http://localhost:4200` in your browser

## Features

### Core Functionality
- Real-time text analysis for vowels and consonants
- Character frequency counting and visualization
- Session-based result history with expandable cards
- Online/offline mode toggle with fallback support

### Technical Features
- API versioning (`/api/v1/analyze`)
- CORS configuration for development
- Input validation and error handling
- Responsive Material Design UI
- Toast notifications for user feedback
- Session storage for data persistence

## API Documentation

### Analyze Text
- **Endpoint**: `POST /api/v1/analyze`
- **Parameters**:
  - `text` (string, required): Text to analyze
  - `type` (string, required): Analysis type (`vowel`, `consonant`, `both`)
- **Response**: JSON with character frequency maps

Example:
```bash
curl -X POST "http://localhost:8080/api/v1/analyze" \
  -d "text=Hello World" \
  -d "type=both"
```

## Development

### Backend Development
See [backend/README.md](backend/README.md) for detailed backend setup and development guidelines.

### Frontend Development  
See [frontend/README.md](frontend/README.md) for detailed frontend setup and development guidelines.

### Testing
- **Backend**: `./gradlew test` (JUnit 5)
- **Frontend**: `npm test` (Karma + Jasmine)

### Building for Production
- **Backend**: `./gradlew build` (creates executable JAR)
- **Frontend**: `npm run build` (creates dist/ folder)

## Project Structure

```
text-analyzer/
├── backend/                 # Spring Boot application
│   ├── src/main/java/      # Java source code
│   ├── src/test/           # Unit tests
│   └── build.gradle        # Gradle configuration
├── frontend/               # Angular application
│   ├── src/app/           # Angular components and services
│   ├── src/assets/        # Static assets
│   └── package.json       # npm dependencies
└── README.md              # This file
```