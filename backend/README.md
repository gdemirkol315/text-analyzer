# Text Analyzer Backend

Spring Boot REST API for text analysis that counts vowels and consonants in provided text.

## Technology Stack

- **Framework**: Spring Boot 3.5.4
- **Java Version**: 21
- **Build Tool**: Gradle 8.14.3
- **Dependencies**: 
  - Spring Web
  - Spring Validation
  - Lombok
  - JUnit 5

## Getting Started

### Prerequisites
- Java 21 or higher
- Gradle (or use included wrapper)

### Running the Application

```bash
# Using Gradle wrapper (recommended)
./gradlew bootRun

# Or using installed Gradle
gradle bootRun
```

The application starts on `http://localhost:8080`

### Building

```bash
# Build executable JAR
./gradlew build

# Run tests
./gradlew test

# Clean build directory
./gradlew clean
```

The built JAR will be available in `build/libs/text-analyzer-0.0.1-SNAPSHOT.jar`

## API Documentation

### Base URL
```
http://localhost:8080/api/v1
```

### Endpoints

#### POST /analyze
Analyzes text for vowel and/or consonant frequency.

**Parameters:**
- `text` (required): The text to analyze
- `type` (required): Analysis type - `vowel`, `consonant`, or `both`

**Example Requests:**

```bash
# Analyze vowels only
curl -X POST "http://localhost:8080/api/v1/analyze" \
  -d "text=Hello World" \
  -d "type=vowel"

# Analyze consonants only  
curl -X POST "http://localhost:8080/api/v1/analyze" \
  -d "text=Hello World" \
  -d "type=consonant"

# Analyze both vowels and consonants
curl -X POST "http://localhost:8080/api/v1/analyze" \
  -d "text=Hello World" \
  -d "type=both"
```

**Response Format:**
```json
{
  "text": "Hello World",
  "vowels": {
    "e": 1,
    "o": 2
  },
  "consonants": {
    "h": 1,
    "l": 3,
    "w": 1,
    "r": 1,
    "d": 1
  }
}
```

## Configuration

### Application Properties
Located in `src/main/resources/application.properties`:

```properties
spring.application.name=text-analyzer

# CORS Configuration
cors.allowed-origins=http://localhost:4200

# API Configuration
api.version=v1
api.base-path=/api
```

### CORS Configuration
The application is configured to accept requests from `http://localhost:4200` for frontend development.

## Architecture

### Package Structure
```
com.example.textanalyzer/
├── TextAnalyzerApplication.java    # Main application class
├── config/
│   └── CorsConfig.java            # CORS configuration
├── controller/
│   └── TextAnalyzerController.java # REST endpoints
├── exception/
│   └── GlobalExceptionHandler.java # Error handling
├── model/
│   ├── AnalysisType.java          # Analysis type enum
│   └── TextAnalysis.java          # Response model
└── service/
    └── TextAnalyzerService.java   # Business logic
```

### Key Components

#### TextAnalyzerController
- **Location**: `controller/TextAnalyzerController.java:30`
- **Purpose**: REST endpoint for text analysis
- **Validation**: Uses `@NotBlank` for input validation

#### TextAnalyzerService  
- **Location**: `service/TextAnalyzerService.java:13`
- **Purpose**: Core business logic for text analysis
- **Methods**:
  - `analyzeAll()`: Counts both vowels and consonants
  - `analyzeVowels()`: Counts vowels only
  - `analyzeConsonants()`: Counts consonants only

#### AnalysisType Enum
- **Location**: `model/AnalysisType.java:6`
- **Purpose**: Type-safe analysis type handling
- **Values**: `VOWEL`, `CONSONANT`, `BOTH`

## Error Handling

The application includes comprehensive error handling:

- **Input Validation**: Required parameters are validated using Bean Validation
- **Type Validation**: Invalid analysis types return clear error messages
- **Global Exception Handler**: Centralized error response formatting

## Testing

### Running Tests
```bash
./gradlew test
```

### Test Coverage
- **Controller Tests**: API endpoint validation and response testing
- **Service Tests**: Business logic unit tests  
- **Integration Tests**: Application context loading
- **Exception Handler Tests**: Error scenario validation

### Test Files
- `TextAnalyzerControllerTest.java`: REST endpoint tests
- `TextAnalyzerServiceTest.java`: Service layer tests
- `GlobalExceptionHandlerTest.java`: Error handling tests
- `AnalysisTypeTest.java`: Enum validation tests

## Development

### Code Style
- Uses Lombok for reducing boilerplate code
- Follows Spring Boot conventions
- Comprehensive JavaDoc documentation
- Input validation with Bean Validation

### API Versioning
The API uses path-based versioning configured through application properties:
- Base path: `/api`
- Version: `v1`
- Full path: `/api/v1/analyze`

## Deployment

### JAR Deployment
```bash
# Build
./gradlew build

# Run
java -jar build/libs/text-analyzer-0.0.1-SNAPSHOT.jar
```

### Configuration Override
```bash
# Override application properties
java -jar text-analyzer.jar --server.port=9090
java -jar text-analyzer.jar --cors.allowed-origins=https://example.com
```