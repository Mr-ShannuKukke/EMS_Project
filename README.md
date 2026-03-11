<>Employee Management System (EMS)<>
====================================
Overview:
---------

The Employee Management System (EMS) is a full-stack web application built using Spring Boot and React.
It allows users to manage employee records through a modern UI while interacting with RESTful backend APIs.

The backend follows a layered architecture with DTO pattern, while the frontend is built using React with Material UI to provide a responsive and professional dashboard interface.

The application supports CRUD operations, pagination, sorting, search functionality, validation, and global exception handling.

=====

<>Tech Stack
------------

Backend:
--------
Java
Spring Boot
Spring Data JPA
Hibernate
MySQL
Maven
Lombok
ModelMapper
JUnit
Mockito

Frontend:
--------
React
Material UI
Vite
JavaScript
Fetch API

Tools:
------
Git
GitHub
Postman
IntelliJ IDEA

====

<>Application Architecture
--------------------------

Backend Architecture:
---------------------
Controller → Service → Repository → Database

src/main/java/com/example/ems_project
 ├── Config
 ├── Controller
 ├── DTO
 ├── Exception
 ├── Model
 ├── Repository
 └── Service

---

## Features

* Create Employee
* Get All Employees
* Get Employee by ID
* Update Employee
* Delete Employee
* Pagination
* Sorting
* Global Exception Handling
* DTO Pattern
* ModelMapper Mapping
* Input Validation
* Unit Testing

---

## API Base URL

```
http://localhost:8081/api/employees
```

---

## API Endpoints

| Method | Endpoint             | Description                               |
| ------ | -------------------- | ----------------------------------------- |
| POST   | /api/employees       | Create employee                           |
| GET    | /api/employees       | Get all employees                         |
| GET    | /api/employees/{id}  | Get employee by ID                        |
| PUT    | /api/employees/{id}  | Update employee                           |
| DELETE | /api/employees/{id}  | Delete employee                           |
| GET    | /api/employees/paged | Get employees with pagination and sorting |

---

## Pagination & Sorting

Pagination endpoint:

```
GET /api/employees/paged
```

Example request:

```
GET /api/employees/paged?pageNo=0&pageSize=5&sortBy=id
```

### Parameters

| Parameter | Description                              |
| --------- | ---------------------------------------- |
| pageNo    | Page number (default = 0)                |
| pageSize  | Number of records per page (default = 5) |
| sortBy    | Field to sort by (default = id)          |

---

## Validation

Employee fields are validated using **Jakarta Validation**.

Example rules:

* First Name → Cannot be blank
* Last Name → Cannot be blank
* Email → Must be valid format
* Department → Cannot be blank

---

## Global Exception Handling

The project implements centralized exception handling using:

```
@RestControllerAdvice
```

Handled exceptions:

* EmployeeNotFoundException
* Validation errors (MethodArgumentNotValidException)

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

## Database Configuration

Database Name:

```
ems_dto
```

Table Name:

```
employees_dto
```

`application.properties` configuration:

```
spring.datasource.url=jdbc:mysql://localhost:3306/ems_dto?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

---

## Running the Application

Clone the repository:

```
git clone https://github.com/Mr-ShannuKukke/EMS_Project.git
```

Navigate to the project directory and run:

```
mvn spring-boot:run
```

Server will start at:

```
http://localhost:8081
```

---

## Example API Test

Create Employee:

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

## Author

Shannu Kukke
