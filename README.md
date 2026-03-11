# Employee Management System (EMS)

## Overview

The **Employee Management System (EMS)** is a **full-stack web application** built using **Spring Boot** and **React**.

It allows users to manage employee records through a modern UI while interacting with RESTful backend APIs.

The backend follows a **layered architecture with DTO pattern**, while the frontend is built using **React with Material UI** to provide a responsive and professional dashboard interface.

The application supports **CRUD operations, pagination, sorting, search functionality, validation, and global exception handling.**

---

# Tech Stack

## Backend

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- ModelMapper
- JUnit
- Mockito

## Frontend

- React
- Material UI
- Vite
- JavaScript
- Fetch API

## Tools

- Git
- GitHub
- Postman
- IntelliJ IDEA

---

# Application Architecture

## Backend Architecture

```
Controller → Service → Repository → Database
```

```
src/main/java/com/example/ems_project
 ├── Config
 ├── Controller
 ├── DTO
 ├── Exception
 ├── Model
 ├── Repository
 └── Service
```

## Frontend Architecture

```
ems-frontend
 ├── src
 │   ├── App.jsx
 │   ├── main.jsx
 │   └── components
 ├── package.json
 └── vite.config.js
```

---

# Features

## Backend Features

- Create Employee
- Get All Employees
- Get Employee by ID
- Update Employee
- Delete Employee
- Pagination
- Sorting
- DTO Pattern
- ModelMapper Mapping
- Global Exception Handling
- Input Validation
- Unit Testing

## Frontend Features

- Add Employee UI
- Edit Employee UI
- Delete Employee with Confirmation Dialog
- Search Employee (Live Search)
- Pagination UI
- Success Toast Notifications
- Responsive Dashboard Layout
- Modern UI using Material UI

---

# API Base URL

```
http://localhost:8081/api/employees
```

---

# API Endpoints

| Method | Endpoint | Description |
|------|------|------|
| POST | /api/employees | Create employee |
| GET | /api/employees | Get all employees |
| GET | /api/employees/{id} | Get employee by ID |
| PUT | /api/employees/{id} | Update employee |
| DELETE | /api/employees/{id} | Delete employee |
| GET | /api/employees/paged | Pagination and sorting |

---

# Pagination & Sorting

Example request:

```
GET /api/employees/paged?pageNo=0&pageSize=5&sortBy=id
```

### Parameters

| Parameter | Description |
|------|------|
| pageNo | Page number |
| pageSize | Records per page |
| sortBy | Sorting field |

---

# Validation

Employee fields are validated using **Jakarta Validation**.

Rules include:

- First Name → Cannot be blank
- Last Name → Cannot be blank
- Email → Must be valid format
- Department → Cannot be blank

---

# Global Exception Handling

Centralized error handling implemented using:

```
@RestControllerAdvice
```

Handled exceptions:

- EmployeeNotFoundException
- MethodArgumentNotValidException

Example error response:

```
{
  "timeStamp": "2026-03-09T12:30:10",
  "status": 404,
  "error": "Not Found",
  "message": "Employee not found with id: 5",
  "path": "/api/employees/5"
}
```

---

# Database Configuration

### Database

```
ems_dto
```

### Table

```
employees_dto
```

Example `application.properties` configuration:

```
spring.datasource.url=jdbc:mysql://localhost:3306/ems_dto?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

---

# Running the Application

## Clone the Repository

```
git clone https://github.com/Mr-ShannuKukke/EMS_Project.git
```

---

# Run Backend

```
mvn spring-boot:run
```

Server runs at:

```
http://localhost:8081
```

---

# Run Frontend

Navigate to frontend folder:

```
cd ems-frontend
```

Install dependencies:

```
npm install
```

Start React application:

```
npm run dev
```

Frontend runs at:

```
http://localhost:5173
```

---

# Example API Request

### Create Employee

```
POST /api/employees
```

Request body:

```
{
  "firstName": "John",
  "lastName": "Doe",
  "emailId": "john.doe@email.com",
  "department": "IT"
}
```

---

# Author

**Shannu Kukke**

GitHub:  
https://github.com/Mr-ShannuKukke/EMS_Project
