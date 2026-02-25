# Task Tracker App

A Java-based Task Tracking System designed to manage and query programs, projects, and tasks for individuals. This application follows SOLID principles and provides a robust API for data management and reporting.

## 🚀 Features

- **Data Import**: Upload JSON files to populate the system with individuals, programs, and projects.
- **Flexible Querying**: Retrieve programs and projects assigned to specific individuals.
- **Task Analytics**: Count pending tasks at different levels (Individual, Program, Project).
- **Relational Mapping**: Quickly identify the parent program for any given project.

## 🛠️ Technology Stack

- **Java 11**: Core logic and data models.
- **Maven**: Dependency management and build automation.
- **Java Servlets (JSP/Servlet API)**: Web layer and API endpoints.
- **Jackson & Gson**: JSON processing and serialization.
- **JUnit**: Automated unit testing.

## 📁 Project Structure

```text
task-tracker-app/
├── src/main/java/com/axeno/tasktrackerapp/
│   ├── controller/   # API Servlets (Endpoints)
│   ├── model/        # Data Entities (Program, Project, Individual, etc.)
│   ├── service/      # Business Logic (Import & Query services)
│   └── exception/    # Custom Error Handling
├── src/main/Files/   # Sample JSON data for imports
└── pom.xml           # Maven Configuration
```

## 🔌 API Endpoints

### 1. Data Import
- **URL**: `POST /api/import`
- **Description**: Imports program and individual data from a JSON file.
- **Request**: Multipart form-data with key `programsData`.
- **Response**: `200 OK` on success, `400 Bad Request` on validation failure.

### 2. Query Programs by Individual
- **URL**: `GET /api/query/programs?userId={userId}`
- **Description**: Returns all programs associated with a specific individual.
- **Parameters**: `userId` (String)

### 3. Query Projects by Individual
- **URL**: `GET /api/query/projects?userId={userId}`
- **Description**: Returns all projects associated with a specific individual.
- **Parameters**: `userId` (String)

### 4. Pending Task Count
- **URL**: `GET /api/query/tasks/pending/count`
- **Description**: Returns the count of pending tasks based on filters.
- **Parameters (Choose one path)**:
    - `userId`: Count for all projects of an individual.
    - `userId` & `programId`: Count for an individual within a specific program.
    - `projectId`: Count for a specific project.
- **Response**: `{"count": X}`

### 5. Get Program Name for Project
- **URL**: `GET /api/query/programs/name?projectId={projectId}`
- **Description**: Returns the name of the program containing a specific project.
- **Response**: `{"programName": "..."}`

## 📊 Data Format (JSON Import)

When importing data via `/api/import`, use the following structure:

```json
{
  "individuals": [
    { "id": "ind-1", "name": "Alice Smith", "type": "EMPLOYEE" }
  ],
  "programs": [
    {
      "id": "prog-1",
      "name": "Transformation 2024",
      "projects": [
        {
          "id": "proj-1",
          "name": "Cloud Migration",
          "status": "IN_PROGRESS",
          "tasks": [
            { "id": "task-1", "name": "Audit Servers", "status": "COMPLETED" }
          ]
        }
      ]
    }
  ]
}
```

## ⚙️ Setup & Installation

1. **Clone the repository**.
2. **Build the project**:
   ```bash
   mvn clean install
   ```
3. **Run in a Servlet Container**: Deploy the generated `.war` file to a server like Apache Tomcat (compatible with Servlet 4.0).