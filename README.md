# Job Search Analytics Platform

A full-stack application for uploading, tracking, and analyzing job applications across multiple sources such as LinkedIn, Indeed, ZipRecruiter, and others.

This project is currently in its **initial development phase**, focused on establishing a stable backend and frontend foundation and verifying full-stack communication.

---

## Current Features (MVP Scaffold)

### Backend (Spring Boot)
- REST API foundation
- Health check endpoint  
  - `GET /api/health`
- Spring Boot project structure
- Spring Data JPA setup (PostgreSQL-ready)
- CORS configuration for frontend communication
- Initial domain models:
  - `JobApplication`
  - `UploadRecord`
  - `AnalyticsSummary (DTO)`

---

### Frontend (Angular)
- Angular standalone application setup
- Basic UI scaffold
- HTTP integration with backend
- Displays backend health response
- API service layer for backend communication

---

### Integration
- Angular frontend communicates with Spring Boot backend
- Verified frontend → backend HTTP requests
- CORS enabled for local development

---

## Tech Stack

### Backend
- Java 21
- Spring Boot 3+
- Spring Web
- Spring Data JPA
- PostgreSQL

### Frontend
- Angular (standalone setup)
- TypeScript
- RxJS
- Angular HttpClient

### Dev Tools
- Docker & Docker Compose (PostgreSQL for local development)
- Node.js + npm
- Concurrently (development workflow orchestration)
- Wait-on (backend readiness check)
- Maven

---

## API Endpoints (Current)

### Health Check
```http
GET /api/health
````

### Response

```json id="9n8z3k"
{
  "status": "Backend is running",
  "timestamp": "2026-06-04T00:00:00Z"
}
```

---

## Planned API Endpoints

### File Upload

* `POST /api/files/upload`

### Job Applications

* `GET /api/jobs`
* `GET /api/jobs/{id}`
* `POST /api/jobs`
* `PUT /api/jobs/{id}`
* `DELETE /api/jobs/{id}`

### Analytics

* `GET /api/analytics/summary`
* `GET /api/analytics/sources`
* `GET /api/analytics/salaries`
* `GET /api/analytics/locations`

---

## Domain Model (Planned Core)

### JobApplication

```java id="0c3x4v"
UUID id;
String title;
String company;
String location;
String source;
LocalDate dateApplied;
BigDecimal salaryMin;
BigDecimal salaryMax;
```

### UploadRecord

```java id="b8tq3m"
UUID id;
String fileName;
String fileType;
String uploadSource;
LocalDateTime uploadedAt;
```

### AnalyticsSummary

```java id="q7m2ks"
Integer totalApplications;
Integer uniqueCompanies;
Integer uniqueLocations;
Double averageSalary;
```

---

## Development Workflow

The project includes a unified development workflow for running the full-stack application locally.

Start the entire system with:

```bash
npm run dev
```

This command will:

* Start PostgreSQL via Docker Compose
* Start the Spring Boot backend
* Wait for backend readiness (`/api/health`)
* Start the Angular frontend

---

### Additional Commands

```bash id="m3v8aa"
npm run docker:init   # Start PostgreSQL containers
npm run docker:down   # Stop containers
npm run clean         # Stop containers and remove volumes
```

---

## Project Status

Current stage:

> Infrastructure setup complete
> Frontend ↔ backend integration working
> Business logic (jobs, uploads, analytics) in progress

---

## Next Milestones

* Job CRUD API implementation
* PostgreSQL persistence with JPA entities
* CSV/Excel file upload + parsing
* Analytics aggregation engine
* JWT authentication
* Dashboard UI improvements

---

## Notes

This system is designed to:

* Centralize job application tracking
* Support bulk uploads from spreadsheets
* Provide analytics on job search activity
* Track applications across multiple platforms
* Generate insights into salary and hiring trends
