# Employee Management System

A Java Swing desktop application for managing employee records with a MySQL database.

## Features

- Add employee records
- View and search employee records
- Update employee details
- Delete employee records
- Print the employee list
- Login screen and desktop UI

## Requirements

- JDK 17 or newer
- Maven 3.8 or newer
- MySQL server

## Build and run

Build and run the application from the repository root:

```bash
mvn clean package
mvn exec:java
```

The Java sources are in `src/main/java/`, UI images are in `src/main/resources/icons/`, and the third-party JARs used by the application are in `lib/`.

## Database configuration

Create and configure the MySQL database and tables expected by the application. Review `src/main/java/employee/management/system/Conn.java` and configure the connection for your local environment before running the app. Do not commit real database passwords or other credentials; move them to environment-based configuration before deploying this application.

## Project layout

```text
.
├── pom.xml
├── README.md
├── lib/                             # Third-party JARs
└── src/main/
    ├── java/employee/management/system/ # Swing screens and database connection
    └── resources/icons/                 # UI images
```
