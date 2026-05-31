# NexusHR Lite - AI Enabled HR Management System

## Project
NexusHR Lite is a Java Full Stack HR Management System with employee management, attendance, leave management, payroll calculation, dashboard and simple AI-based attrition risk prediction.

## Technologies Used
- Java 17
- Spring Boot 3.3.5
- Spring MVC
- Spring Data JPA
- Thymeleaf
- H2 Database
- HTML, CSS
- Maven

## Tools Required
- JDK 17 or above
- Maven
- VS Code or IntelliJ IDEA
- Browser
- Thunder Client or Postman for API testing

## How to Run
1. Extract this ZIP.
2. Open the folder in VS Code or IntelliJ.
3. Open terminal in project folder.
4. Run:

```bash
mvn spring-boot:run
```

5. Open browser:

```text
http://localhost:8080
```

## H2 Database Console
Open:

```text
http://localhost:8080/h2-console
```

Use:
- JDBC URL: jdbc:h2:mem:nexushrdb
- Username: sa
- Password: keep empty

## Pages
- Dashboard: http://localhost:8080
- Employees: http://localhost:8080/employees
- Attendance: http://localhost:8080/attendance
- Leaves: http://localhost:8080/leaves
- Payroll: http://localhost:8080/payroll

## API Testing
GET all employees:

```text
GET http://localhost:8080/api/employees
```

POST employee:

```json
{
  "fullName": "Priya Reddy",
  "email": "priya@example.com",
  "department": "IT",
  "designation": "Software Engineer",
  "salary": 55000,
  "joiningDate": "2026-05-01",
  "performanceScore": 76
}
```

API URL:

```text
POST http://localhost:8080/api/employees
```

## Expected Output
- Employee added successfully
- Attendance marked successfully
- Leave request submitted
- Leave approved/rejected
- Payroll generated
- Dashboard counts updated
- Attrition risk displayed as LOW, MEDIUM or HIGH
