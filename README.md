# Student Placement Management System

A web-based Student Placement Management System developed using Java, Spring Boot, Spring Data JPA, Thymeleaf, Bootstrap, and MySQL.

The application provides separate interfaces for administrators/teachers and students. Administrators can manage students, companies, and placement records, while students can log in to view their profile and placement information.

## Features

### Admin / Teacher

- Admin login and logout
- Protected admin dashboard
- View total students, companies, and placements
- Add, edit, delete, and view students
- Search students by Student ID
- Search students by name
- View placement information while searching students
- Add, edit, delete, and view companies
- Add, edit, delete, and view placement records
- Manage placement status
- Protected admin routes

### Student

- Student login using Student ID and registered email
- Protected student dashboard
- View personal profile
- View course and academic information
- View placement status
- View assigned company information
- Student logout

## Technologies Used

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Thymeleaf
- MySQL
- HTML
- Bootstrap
- Maven
- Git
- GitHub
- VS Code

## Project Structure

```text
src/
└── main/
    ├── java/
    │   └── com/adityasingh/studentplacementportal/
    │       ├── config/
    │       ├── controller/
    │       ├── entity/
    │       ├── repository/
    │       ├── service/
    │       └── StudentPlacementPortalApplication.java
    │
    └── resources/
        ├── static/
        ├── templates/
        └── application.properties
```

## Main Modules

### Student Management

Administrators can create, view, update, delete, and search student records.

### Company Management

Administrators can maintain company information such as company name, location, package, and eligibility requirements.

### Placement Management

Placement records connect students with companies and contain information such as placement date and placement status.

### Student Portal

Students have a separate login and dashboard where they can view their own profile and placement information.

## Authentication and Route Protection

The application contains separate login flows for:

- Admin / Teacher
- Student

Protected pages cannot be accessed directly without the required login session.

## Database

The application uses MySQL for persistent data storage.

Main entities include:

- Student
- Company
- Placement

Placement records associate students with companies.

## How to Run the Project

1. Clone the repository.
2. Open the project in VS Code or another Java IDE.
3. Make sure Java and MySQL are installed.
4. Create/configure the MySQL database.
5. Update the database configuration in `application.properties`.
6. Run `StudentPlacementPortalApplication.java`.
7. Open the application in a browser at `http://localhost:8080`.

## Application Pages

```text
/                     Home
/login                Admin / Teacher Login
/student-login        Student Login
/admin-dashboard      Admin Dashboard
/students             Student Management
/companies            Company Management
/placements           Placement Management
/student-dashboard    Student Dashboard
```

## Future Improvements

- Spring Security authentication
- Password encryption
- Role-based authorization
- Student registration
- Placement eligibility checking
- Resume upload
- Placement statistics and charts
- Email notifications
- REST API support
- Deployment to a cloud platform

## Author

**Aditya Kumar Singh**

B.Tech Computer Science and Engineering