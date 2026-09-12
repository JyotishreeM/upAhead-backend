# UpAhead Backend

UpAhead is a student-focused application designed to provide a foundation for managing user accounts and future student-related services.

This repository contains the backend REST API for the UpAhead application, built using Java and Spring Boot.

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- REST API
- BCrypt Password Encoding

## Features

- User registration
- User login
- Secure password storage using BCrypt
- JWT token generation after successful authentication
- JWT-based authentication for protected APIs
- Stateless authentication using Spring Security
- MySQL database integration
- RESTful API architecture

## Authentication Flow

The application uses JWT-based authentication.

1. User registers through the registration API.
2. Password is encrypted using BCrypt before being stored.
3. User logs in using email and password.
4. Backend validates the credentials.
5. A JWT access token is generated after successful authentication.
6. The client sends the token with protected API requests:

```text
Authorization: Bearer <token>
```

7. The JWT authentication filter validates the authentication request before allowing access to protected resources.

## API Endpoints

### Register User

```http
POST /api/auth/register
```

Creates a new user account.

### Login

```http
POST /api/auth/login
```

Authenticates the user and returns a JWT access token along with user information.

Example response structure:

```json
{
  "token": "<JWT_TOKEN>",
  "user": {
    "userId": 1,
    "userName": "Example User",
    "emailId": "example@email.com"
  }
}
```

### Protected API

```http
GET /api/test/protected
```

Requires a valid JWT access token.

## Security

Public endpoints:

```text
/api/auth/register
/api/auth/login
```

Other endpoints require authentication.

Passwords are not stored as plain text. BCrypt password hashing is used before storing passwords in the database.

## Configuration

Sensitive credentials are configured using environment variables rather than being committed to the repository.

The application expects:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
JWT_SECRET
JWT_EXPIRATION
```

Example `application.properties` configuration:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

jwt.secret=${JWT_SECRET}
jwt.expiration=${JWT_EXPIRATION:86400000}
```

Do not commit database passwords or JWT secrets to source control.

## Running the Application Locally

### Prerequisites

Make sure you have:

- Java 21
- MySQL
- Git

The project includes the Maven Wrapper, so a separate Maven installation is not required.

### Set Environment Variables

Example using PowerShell:

```powershell
$env:DB_URL="your-database-url"
$env:DB_USERNAME="your-database-username"
$env:DB_PASSWORD="your-database-password"
$env:JWT_SECRET="your-base64-jwt-secret"
$env:JWT_EXPIRATION="86400000"
```

### Start the Backend

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

The backend runs locally on:

```text
http://localhost:8080
```

## Project Structure

```text
src/main/java/com/upahead/backend/
├── controller/
├── dto/
├── entity/
├── repository/
├── security/
├── service/
└── UpaheadBackendApplication.java
```

The `security` package contains the JWT authentication and Spring Security configuration.

## Frontend

The frontend of UpAhead is developed using Angular and communicates with this backend through REST APIs.

## Future Improvements

Planned improvements include:

- Angular JWT interceptor integration
- User profile APIs
- Role-based authorization
- Refresh token support
- Improved exception handling and validation
- Student-specific features
- Cloud deployment

## Author

**Jyotishree Majumder**

Angular Developer expanding into Java and Spring Boot full-stack development.