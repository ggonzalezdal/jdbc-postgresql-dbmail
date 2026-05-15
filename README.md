# DBMail JDBC Project

A modern Java JDBC learning project using PostgreSQL, Gradle, and Eclipse.

This project demonstrates how to connect a Java application to a PostgreSQL database using JDBC and the PostgreSQL JDBC Driver.

---

# Technologies Used

- Java 21
- PostgreSQL 16
- JDBC
- Gradle
- Eclipse IDE
- pgAdmin 4

---

# Project Structure

```text
dbmail
├── build.gradle
├── settings.gradle.kts
├── README.md
├── gradle/
├── src/
│   └── main/
│       ├── java/
│       │   └── dbmail/
│       │       └── ConnectionTest.java
│       └── resources/
│           └── db.properties
```

---

# Database Setup

Database name:

```text
dbmail
```

Schema name:

```text
dbmail
```

Main tables:

- users
- forums
- messages
- readings

---

# Gradle Configuration

Main dependency:

```gradle
implementation 'org.postgresql:postgresql:42.7.7'
```

The PostgreSQL JDBC Driver is automatically downloaded from Maven Central.

---

# db.properties

The project uses an external configuration file located in:

```text
src/main/resources/db.properties
```

Example:

```properties
db.url=jdbc:postgresql://localhost:5432/dbmail
db.user=postgres
db.password=YOUR_PASSWORD
```

Important:
Do not upload real credentials to public repositories.

---

# First JDBC Connection Test

The class:

```text
ConnectionTest.java
```

performs:

1. Loading configuration from `db.properties`
2. Opening a PostgreSQL JDBC connection
3. Executing:

```sql
SELECT version();
```

4. Printing the result in the console

Example output:

```text
Connection successful!
PostgreSQL 16.12, compiled by Visual C++ build 1944, 64-bit
```

---

# Running the Project

In Eclipse:

```text
Right click ConnectionTest.java
→ Run As
→ Java Application
```

---

# JDBC Concepts Practiced

- JDBC API
- DriverManager
- Connection
- Statement
- ResultSet
- External configuration with Properties
- Gradle dependency management
- PostgreSQL JDBC Driver

---

# Future Improvements

Planned next steps:

- Query users from database
- PreparedStatement examples
- INSERT / UPDATE / DELETE operations
- Transaction management
- Stored procedures
- Error handling improvements
- DAO pattern introduction

---

# Learning Objective

This project is designed as a hands-on introduction to backend database programming with Java and PostgreSQL using modern Gradle workflows.