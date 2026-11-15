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

The repository currently contains a NetBeans project under `Employee-Management-System/`. A root Maven build is provided to compile that source tree and package its icons:

```bash
mvn clean package
mvn exec:java
```

The third-party JARs used by the existing application are in `Employee-Management-System/lib/` and are referenced by the root `pom.xml`.

## Database configuration

Create and configure the MySQL database and tables expected by the application. Review `Employee-Management-System/src/employee/management/system/Conn.java` and configure the connection for your local environment before running the app. Do not commit real database passwords or other credentials; move them to environment-based configuration before deploying this application.

## Current layout

```text
.
├── pom.xml
├── README.md
└── Employee-Management-System/
    ├── lib/                         # Existing third-party JARs
    ├── nbproject/                   # Existing NetBeans metadata
    └── src/
        ├── employee/management/system/  # Swing screens and database connection
        └── icons/                        # UI images
```

The application source files already use Java's conventional `PascalCase.java` naming. The current repository also contains a nested NetBeans project and a committed JVM crash log; those legacy paths need to be removed or migrated as part of a follow-up cleanup.
