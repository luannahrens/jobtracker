# Job Tracker

A Java Spring Boot web application for tracking job applications. This application allows users to manage their job search process by recording, updating, and monitoring the status of job applications.

## Features

- **View Job Applications**: List all job applications with optional status filtering
- **Add New Applications**: Create new job application entries with company name, job title, status, date applied, and notes
- **Edit Applications**: Update existing job application details
- **Delete Applications**: Remove job applications from the tracker
- **Status Tracking**: Track application status (e.g., Applied, Interviewing, Rejected, Accepted)
- **H2 Database Console**: Access the H2 in-memory database console for data inspection

## Technologies Used

- **Java 25**
- **Spring Boot 4.0.6**
- **Spring Web MVC**
- **Spring JDBC**
- **H2 Database** (in-memory)
- **Thymeleaf** (templating engine)
- **Spring Validation**
- **Maven** (build tool)

## Prerequisites

- Java 25 or higher
- Maven 3.6+ (or use the included Maven wrapper)

## Getting Started

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd jobtracker
   ```

2. **Build the application**:
   ```bash
   ./mvnw clean compile
   ```

3. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

   The application will start on `http://localhost:8080`

4. **Access the application**:
   - Main application: `http://localhost:8080/applications`
   - H2 Database Console: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:jobtrackerdb`
     - Username: `sa`
     - Password: (leave blank)

## Usage

- Navigate to `/applications` to view all job applications
- Click "Add New Application" to create a new entry
- Use the status filter to view applications by status
- Edit or delete applications using the respective buttons

## Project Structure

```
src/
├── main/
│   ├── java/com/example/jobtracker/
│   │   ├── JobtrackerApplication.java          # Main application class
│   │   ├── controller/JobApplicationController.java  # Web controller
│   │   ├── controller/JobApplicationRestController.java  # REST controller
│   │   ├── model/JobApplication.java           # Data model
│   │   ├── repository/JobApplicationRepository.java  # Data access
│   │   └── service/JobApplicationService.java # Business logic
│   └── resources/
│       ├── application.properties              # Configuration
│       ├── data.sql                            # Initial data
│       ├── schema.sql                          # Database schema
│       └── templates/                          # Thymeleaf templates
└── test/
    └── java/com/example/jobtracker/
        └── JobtrackerApplicationTests.java     # Unit tests
```

## Database

The application uses an H2 in-memory database that is initialized with schema and sample data on startup. Data is not persisted between application restarts.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
