# Student Placement Portal — Study Notes (Phase 1 & 2)

These notes explain the application you have built so far: a Spring Boot web application in which a placement officer can add, view, edit, and delete student records.

## 1. What the project does

At the moment, the **Student Registration** page is an admin/placement-officer page. It performs CRUD operations:

| CRUD | Meaning | In this project |
| --- | --- | --- |
| Create | add new data | register a student |
| Read | show existing data | display the student list |
| Update | change existing data | edit a student |
| Delete | remove data | delete a student |

The application uses Spring Boot for the backend, Thymeleaf for dynamic HTML pages, Bootstrap for styling, and MySQL for data storage.

## 2. Project structure

```text
src/main
├── java/com/adityasingh/studentplacementportal
│   ├── StudentPlacementPortalApplication.java   # starts the app
│   ├── controller/                              # receives browser requests
│   ├── service/                                 # business logic
│   ├── repository/                              # database access
│   └── entity/                                  # Java-to-table mappings
└── resources
    ├── application.properties                   # app and database settings
    └── templates/                               # Thymeleaf HTML pages
```

Spring Boot scans classes below the package containing `StudentPlacementPortalApplication`. Because `controller`, `service`, `repository`, and `entity` are all below that package, Spring can find them automatically.

## 3. How the whole application starts

File: `StudentPlacementPortalApplication.java`

```java
@SpringBootApplication
public class StudentPlacementPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentPlacementPortalApplication.class, args);
    }
}
```

`@SpringBootApplication` is a convenient combination of three important features:

- `@Configuration`: this class can provide Spring configuration.
- `@EnableAutoConfiguration`: Spring Boot configures common things automatically, such as Spring MVC, JPA, Thymeleaf, and the web server, based on the dependencies in `pom.xml`.
- `@ComponentScan`: Spring searches for classes marked with annotations such as `@Controller`, `@Service`, and `@Repository`.

`SpringApplication.run(...)` creates Spring's application context (the container that manages objects), connects configured services, and starts the embedded Tomcat server on port 8080.

**Important:** Run only this class (or `./mvnw spring-boot:run`). Do not run a controller, service, repository, or entity directly.

## 4. JDBC and MySQL connection

File: `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/placement_system
spring.datasource.username=...
spring.datasource.password=...
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

`jdbc:mysql://localhost:3306/placement_system` means:

- `jdbc:mysql` — use the JDBC driver for MySQL.
- `localhost` — MySQL is running on this same computer.
- `3306` — MySQL's default port.
- `placement_system` — the database name.

JDBC (Java Database Connectivity) is Java's standard way to communicate with a database. In this project, Spring Data JPA and Hibernate use JDBC behind the scenes, so you normally write Java repository calls instead of raw SQL.

The remaining JPA settings are:

```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

- `ddl-auto=update`: Hibernate updates database tables to match your entity. This is convenient while learning; avoid relying on it for production database migrations.
- `show-sql=true`: prints generated SQL in the console.
- `format_sql=true`: makes that SQL easier to read.

**Security note:** never commit a real database password to GitHub. Later, move it to an environment variable or a local, ignored configuration file.

## 5. MVC architecture

MVC keeps responsibilities separate:

```text
Browser → Controller → Service → Repository → Hibernate/JPA → JDBC → MySQL
Browser ← Controller ← Service ← Repository ← Hibernate/JPA ← JDBC ← MySQL
```

- **Model:** application data. Here, `Student` objects and the `students` list are model data.
- **View:** the HTML pages, such as `Student.html` and `index.html`.
- **Controller:** the class that maps URLs to Java methods.

The Service and Repository layers are not technically part of the word MVC, but they make the code more organised and professional.

## 6. The Student entity

File: `entity/Student.java`

An entity is a normal Java class that Hibernate maps to a database table.

```java
@Entity
@Table(name = "student")
public class Student { ... }
```

- `@Entity` tells JPA/Hibernate that this class represents database data.
- `@Table(name = "student")` maps it to the MySQL table named `student`.

### Primary key

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int studentId;
```

- `@Id` marks the unique identifier of a table row.
- `@GeneratedValue` tells JPA that the id is created automatically.
- `GenerationType.IDENTITY` uses MySQL's auto-increment behaviour.

Each field becomes a table column. For example:

```java
@Column(name = "passing_year")
private int passingYear;
```

This maps Java's camelCase field `passingYear` to the SQL column `passing_year`.

### Constructors, getters, and setters

The no-argument constructor is required because Hibernate needs to create an empty object before filling its fields from a database row.

Getters read private data (`getName()`), and setters update it (`setName(...)`). Fields are private to protect the object's data and control access through methods.

## 7. The repository

File: `repository/StudentRepository.java`

```java
public interface StudentRepository extends JpaRepository<Student, Integer> { }
```

`JpaRepository<Student, Integer>` tells Spring Data:

- the repository manages `Student` entities;
- the entity id type is `Integer`.

Spring automatically provides useful methods, including:

```java
save(student)        // INSERT a new row or UPDATE an existing row
findAll()            // SELECT all rows
findById(id)         // SELECT one row by id
deleteById(id)       // DELETE a row by id
count()              // count rows
```

You do not write SQL for these basic operations. Hibernate creates the SQL and runs it using JDBC.

## 8. The service

File: `service/StudentService.java`

```java
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
```

`@Service` marks this as a business-logic class managed by Spring.

The constructor is **constructor injection**. Spring creates one `StudentRepository` object and gives it to the service. This is better than manually creating it with `new StudentRepository()` because Spring controls the dependency and can test or replace it more easily.

Current service methods:

- `getAllStudents()` calls `findAll()`.
- `saveStudent(student)` calls `save(student)`.
- `getStudentById(id)` calls `findById(id).orElse(null)`.
- `deleteStudent(id)` calls `deleteById(id)`.

Later, this is where you can add validation and rules—for example, rejecting a CGPA outside the valid range.

## 9. The controller

File: `controller/StudentController.java`

`@Controller` means this class handles web requests and returns view/template names.

### Open the student page

```java
@GetMapping("/students")
public String studentPage(Model model) {
    model.addAttribute("student", new Student());
    model.addAttribute("students", studentService.getAllStudents());
    return "student";
}
```

When the browser visits `/students`:

1. `@GetMapping` selects this method for an HTTP GET request.
2. An empty `Student` object is sent to the form as `student`.
3. All saved students are sent to the page as `students`.
4. The returned view name tells Thymeleaf to render the student template.

`Model` is a container that passes Java data from the controller to the HTML page.

### Save a student

```java
@PostMapping("/students/save")
public String saveStudent(@ModelAttribute Student student) {
    studentService.saveStudent(student);
    return "redirect:/students";
}
```

- A form submits a POST request to `/students/save`.
- `@ModelAttribute` reads form fields and puts their values into a `Student` object.
- The service saves it.
- `redirect:/students` asks the browser to make a fresh GET request so the new list appears. This prevents accidental duplicate submission after a refresh.

### Edit a student

```java
@GetMapping("/students/edit/{id}")
public String editStudent(@PathVariable Integer id, Model model) { ... }
```

`{id}` is a variable in the URL. For `/students/edit/5`, `@PathVariable Integer id` receives the value `5`. The controller loads that student and sends it back to the same form. The hidden id field makes `save()` update that existing row rather than insert a new one.

### Delete a student

```java
@GetMapping("/students/delete/{id}")
public String deleteStudent(@PathVariable Integer id) { ... }
```

This uses the id from the URL, deletes the row, and redirects back to the list. In a production application, deletion should use POST or DELETE with CSRF protection rather than GET, because GET requests should not change data.

## 10. Thymeleaf template

File: `resources/templates/Student.html`

Thymeleaf turns an HTML template into a page with live data from Java.

```html
<form th:action="@{/students/save}" th:object="${student}" method="post">
```

- `th:action` builds the form URL.
- `th:object="${student}"` connects the form to the `student` object supplied by the controller.
- `method="post"` sends data using HTTP POST.

```html
<input type="hidden" th:field="*{studentId}">
<input type="text" th:field="*{name}">
```

`th:field` connects an input to a property of the current `student` object. The hidden id is important for editing.

```html
<tr th:each="student : ${students}">
    <td th:text="${student.name}"></td>
</tr>
```

- `th:each` loops through every item in the `students` list.
- `th:text` safely displays a value.

```html
<a th:href="@{/students/edit/{id}(id=${student.studentId})}">Edit</a>
```

This creates a per-student link such as `/students/edit/5`.

Bootstrap classes such as `container`, `form-control`, `btn`, and `table` only control appearance. They do not contain Java or database logic.

## 11. Request flows to memorise

### Create

```text
Fill form → POST /students/save → Controller → Service → Repository.save()
→ Hibernate generates INSERT SQL → JDBC → MySQL → redirect → updated list
```

### Read

```text
GET /students → Controller → Service → Repository.findAll()
→ MySQL → student list → Model → Thymeleaf renders table
```

### Update

```text
Click Edit → GET /students/edit/{id} → load one Student → pre-filled form
→ submit form with studentId → Repository.save() → UPDATE SQL
```

### Delete

```text
Click Delete → GET /students/delete/{id} → Repository.deleteById(id)
→ DELETE SQL → redirect → refreshed list
```

## 12. `pom.xml` dependencies

- `spring-boot-starter-data-jpa`: Spring Data JPA, Hibernate, and database integration.
- `spring-boot-starter-thymeleaf`: Thymeleaf templates.
- `spring-boot-starter-webmvc`: controllers, routing, MVC, and embedded web server support.
- `mysql-connector-j`: JDBC driver that lets Java talk to MySQL.
- `spring-boot-devtools`: restarts the application during development after file changes.

## 13. Quick interview answers

**What is Spring Boot?**  
A framework that speeds up Spring application development using auto-configuration, starter dependencies, and embedded servers.

**What is JPA?**  
A Java specification for mapping objects to database tables. Hibernate is a common JPA implementation.

**What is Hibernate?**  
An ORM (Object-Relational Mapping) framework that converts Java object operations into SQL.

**What is JDBC?**  
The standard Java API for connecting to and executing SQL against databases.

**Why use a Service layer?**  
To keep business logic outside the controller, making the code easier to maintain and test.

**`@Controller` vs `@RestController`?**  
`@Controller` normally returns HTML view names. `@RestController` returns data directly, usually JSON.

**Why `redirect:/students` after saving?**  
It follows the Post/Redirect/Get pattern, showing fresh data and preventing duplicate form submission on refresh.

## 14. Current status and next steps

Completed:

- Spring Boot project setup
- MySQL/JDBC configuration
- Student entity
- Student CRUD module
- Thymeleaf form and Bootstrap table

Recommended next feature: create a **Company CRUD module** using the same structure:

```text
Company entity → Company repository → Company service → Company controller → company.html
```

After that, the Placement module can connect a student with a company.
