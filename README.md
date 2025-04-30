# StockManager Backend

This is the backend service for the StockManager project, developed with Java and Spring Boot.

## Overview

StockManager is a work-in-progress backend REST API for managing stock investments.  
It is part of a larger fullstack project that will include:

- A dedicated frontend (planned)
- Oracle Database schema with stored procedures, triggers and functions
- Scheduled Python scripts for automated database updates
- Future migration to JPA + Hibernate

The goal of this repository is to grow alongside my learning path and progressively showcase real-world backend development skills.

---

## Tech Stack

- Java 17
- Spring Boot 3
- Maven
- Spring Validation (Jakarta)
- RESTful architecture
- DTO separation (Request / Response)
- Enum mapping
- Custom logic in POJOs (e.g., calculated fields)

---

## Current Features

- Add single or multiple stocks via POST
- Retrieve all or filtered stocks via GET
- Delete stocks via DELETE
- Update stocks via PUT or PATCH
- Input validation with @Valid and @Validated
- Dynamic calculation of current return and total values
- Clean separation of layers (Controller, Service, Repository)

---

## API Endpoints

| Method | Endpoint                     | Description                     |
|--------|------------------------------|---------------------------------|
| GET    | `/get-stocks`                | Get all stocks                  |
| POST   | `/add-stock`                 | Add a single stock              |
| POST   | `/add-stocks`                | Add multiple stocks             |
| GET    | `/get-stocks-by`             | Filter stocks by sector         |
| DELETE | `/remove-stock`              | Delete a stock by ticker        |
| PUT    | `/update-stock/{ticker}`     | Full update of a stock          |
| PATCH  | `/patch-stock/{ticker}`      | Partial update of a stock       |

---

## Run the Project

```bash
./mvnw spring-boot:run
```

or, if using Windows:

```bash
mvnw.cmd spring-boot:run
```

---

## Next Steps

- Add global exception handling with @ControllerAdvice
- Introduce unit testing with JUnit and Mockito
- Integrate JPA and Hibernate for persistence
- Prepare the Oracle schema and initial data
- Add a full frontend (React or Angular)
- Automate data sync with Python + Windows Task Scheduler

---

## License

MIT
