# Student Placement Management System

A web-based **Student Placement Management System** developed using Java, Spring Boot, Spring Data JPA, Thymeleaf, Bootstrap, and MySQL.

The system is designed to manage college placement activities through separate portals for:

- Placement Head / TPO
- Teacher / Placement Coordinator
- Students

Teachers can manage students, companies, and placement records. The Placement Head / TPO can monitor overall placement statistics, while students can securely log in to view their own profile and placement information.

The application is deployed online using **Railway** with a cloud-hosted MySQL database.

---

## Live Project

The deployed application can be accessed at:

```text
https://student-placement-portal-production.up.railway.app
```

---

## Features

### Placement Head / TPO

- Separate TPO login
- Protected TPO dashboard
- View total number of students
- View total number of companies
- View total placement records
- View selected students
- View pending students
- View rejected students
- View highest package
- View overall placement percentage
- Monitor overall placement performance
- Separate TPO logout

---

### Teacher / Placement Coordinator

- Separate Teacher / Admin login
- Protected Teacher dashboard
- View total students, companies, and placements
- Add, edit, delete, and view students
- Search students by Student ID
- Search students by name
- View student placement information
- Add, edit, delete, and view companies
- Add, edit, delete, and view placement records
- Update placement status
- View complete student placement status
- Filter students by:
  - Selected
  - Pending
  - Rejected
  - Not Placed
- View company and package details for placed students
- Protected Teacher routes
- Teacher logout

---

### Student

- Separate Student login
- Login using Student ID and registered email
- Protected Student dashboard
- View personal profile
- View course
- View branch
- View CGPA
- View passing year
- View placement status
- View assigned company
- View package information
- Student logout

---

## User Roles

The system contains three main roles:

```text
Placement Head / TPO
        |
        v
   TPO Dashboard
        |
        v
Overall Placement Monitoring


Teacher / Placement Coordinator
        |
        v
   Teacher Dashboard
        |
        v
Students | Companies | Placements | Placement Status


Student
        |
        v
   Student Dashboard
        |
        v
Personal Profile and Placement Information
```

---

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
- Railway
- VS Code

---

## Project Structure

```text
src/
└── main/
    ├── java/
    │   └── com/adityasingh/studentplacementportal/
    │       │
    │       ├── config/
    │       │   ├── LoginInterceptor.java
    │       │   └── WebConfig.java
    │       │
    │       ├── controller/
    │       │   ├── HomeController.java
    │       │   ├── LoginController.java
    │       │   ├── StudentLoginController.java
    │       │   ├── StudentDashboardController.java
    │       │   ├── StudentController.java
    │       │   ├── CompanyController.java
    │       │   ├── PlacementController.java
    │       │   ├── PlacementStatusController.java
    │       │   ├── TpoLoginController.java
    │       │   └── TpoDashboardController.java
    │       │
    │       ├── entity/
    │       │   ├── Student.java
    │       │   ├── Company.java
    │       │   └── Placement.java
    │       │
    │       ├── repository/
    │       │   ├── StudentRepository.java
    │       │   ├── CompanyRepository.java
    │       │   └── PlacementRepository.java
    │       │
    │       ├── service/
    │       │   ├── StudentService.java
    │       │   ├── CompanyService.java
    │       │   └── PlacementService.java
    │       │
    │       └── StudentPlacementPortalApplication.java
    │
    └── resources/
        │
        ├── static/
        │
        ├── templates/
        │   ├── index.html
        │   ├── login.html
        │   ├── student-login.html
        │   ├── student-dashboard.html
        │   ├── admin-dashboard.html
        │   ├── student.html
        │   ├── company.html
        │   ├── placement.html
        │   ├── placement-status.html
        │   ├── tpo-login.html
        │   └── tpo-dashboard.html
        │
        └── application.properties
```

---

## Main Modules

### Student Management

Teachers / Placement Coordinators can manage student records.

Available operations include:

- Add Student
- View Students
- Edit Student
- Delete Student
- Search by Student ID
- Search by Student Name

Student information includes:

- Name
- Email
- Phone
- Course
- Branch
- CGPA
- Passing Year

---

### Company Management

Teachers can manage recruiting companies.

Company information includes:

- Company Name
- Location
- Package
- Minimum CGPA

Available operations include:

- Add Company
- View Companies
- Edit Company
- Delete Company

---

### Placement Management

Placement records connect students with recruiting companies.

Each placement record contains:

- Student
- Company
- Placement Date
- Placement Status

Supported placement statuses are:

```text
Selected
Pending
Rejected
```

Teachers can add, edit, delete, and view placement records.

---

## Student Placement Status Tracking

Teachers can view the placement condition of every student from a dedicated Placement Status page.

Students can be classified as:

```text
Selected
Pending
Rejected
Not Placed
```

The Placement Status page displays:

- Student ID
- Student Name
- Branch
- CGPA
- Placement Status
- Company
- Package

Teachers can filter the list using:

```text
All
Selected
Pending
Rejected
Not Placed
```

---

## TPO / Placement Head Dashboard

The Placement Head / TPO dashboard is mainly designed for monitoring overall placement performance.

The dashboard displays:

- Total Students
- Total Companies
- Total Placement Records
- Selected Students
- Pending Students
- Rejected Students
- Highest Package
- Placement Percentage

This provides a quick overview of the placement activity of the institution.

---

## Teacher / Placement Coordinator Dashboard

The Teacher dashboard provides access to the main management operations of the system.

Teachers can access:

```text
Student Management
Company Management
Placement Management
Placement Status Tracking
```

The dashboard also displays:

- Total Students
- Total Companies
- Total Placements

---

## Student Portal

Students have their own separate login and dashboard.

Students can only view their own information, including:

- Personal Details
- Course
- Branch
- CGPA
- Passing Year
- Placement Status
- Company Details
- Package Information

Students do not have access to Teacher or TPO management operations.

---

## Authentication and Route Protection

The application contains separate authentication flows for:

```text
Placement Head / TPO
Teacher / Placement Coordinator
Student
```

Teacher management pages are protected using:

- HTTP Sessions
- Spring MVC HandlerInterceptor

If an unauthenticated user tries to access a protected Teacher page, the user is redirected to the Teacher login page.

TPO and Student dashboards also require their respective login sessions.

---

## Database

The project uses **MySQL** for persistent data storage.

Main entities include:

```text
Student
Company
Placement
```

### Relationship

```text
Student
   |
   |
   v
Placement
   ^
   |
   |
Company
```

A placement record connects a student with a company and stores placement-related information.

---

## Local Development

To run the project locally:

1. Clone the repository.

2. Open the project in VS Code or another Java IDE.

3. Make sure Java and MySQL are installed.

4. Create or configure the MySQL database.

5. Configure the database connection inside:

```text
src/main/resources/application.properties
```

6. Run:

```text
StudentPlacementPortalApplication.java
```

7. Open the application in your browser:

```text
http://localhost:8080
```

The Spring Boot application must be running when using localhost.

---

## Online Deployment

The project is deployed using **Railway**.

The online architecture is:

```text
User
  |
  v
Railway Public URL
  |
  v
Spring Boot Application
  |
  v
Railway MySQL Database
```

The online version does not require the project to be running on the local computer.

It can be accessed from:

- Mobile phones
- Laptops
- Desktop computers
- College computers

using the public Railway URL.

---

## Application Routes

```text
/                       Home Page

/teacher-login          Teacher / Admin Login
/admin-dashboard        Teacher / Placement Coordinator Dashboard

/students               Student Management
/companies              Company Management
/placements             Placement Management
/placement-status       Student Placement Status

/student-login          Student Login
/student-dashboard      Student Dashboard

/tpo-login              Placement Head / TPO Login
/tpo-dashboard          Placement Head / TPO Dashboard
```

---

## Git and GitHub

Git is used for version control and GitHub is used to store the project repository.

Typical workflow:

```bash
git status
git add .
git commit -m "Describe your changes"
git push
```

Railway is connected to the GitHub repository.

Whenever new code is pushed to GitHub, Railway automatically builds and deploys the latest version of the application.

---

## Future Improvements

The following features can be added in future versions:

- Spring Security
- BCrypt password encryption
- Database-based Teacher and TPO accounts
- Advanced role-based authorization
- Student registration
- Placement eligibility checking
- Resume upload
- Branch-wise placement statistics
- Company-wise placement statistics
- Placement charts and analytics
- Email notifications
- Forgot password functionality
- Export placement reports to PDF
- Export placement reports to Excel
- REST API support

---

## Author

**Aditya Kumar Singh**

B.Tech Computer Science and Engineering

Student Placement Management System Project