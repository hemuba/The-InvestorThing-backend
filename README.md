# 📊 StockManager Backend

![Java](https://img.shields.io/badge/Java-17+-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Build](https://img.shields.io/badge/build-passing-brightgreen)
![Profile](https://img.shields.io/badge/Profile-dev%2Fprod-orange)

Spring Boot REST API for managing stock investments.  
Provides -at the moment- read-only access to two Oracle database tables: `STOCKS` and `CURRENT_STOCKS`.

---

## 🚀 Features

- ✅ Read-only operations (GET) for both general and current stocks // POST, DELETE, PUT and PATCH method to be added.
- ✅ JPA repositories: `StockRepository` and `MyStockRepository`
- ✅ DTO response structure: `StockDTOResp`, `MyStockDTOResp`
- ✅ DTO request structure: `StockDTOReq`, `MyStockDTOReq`
- ✅ Centralized JSON response via `ApiResponse`
- ✅ Profile-based config: `application-public.yml`, `application-secrets.yml` handled via `application.yml`
- ✅ Logging with SLF4J + Logback
- ✅ Custom Exceptions `NotFoundException`, `BadRequestException`, `MultiStatusException`, `UnprocessableEntityException` hanlded via GlobalExceptionHandler class.
- ✅ Input validation with `jakarta.validation`

---

## 🧠 Tech Stack

- Java 17+
- Spring Boot 3.x
- Maven
- Oracle Database 19c
- Jakarta Validation
- SLF4J + Logback

---

## 🧪 API Overview

### 🔍 From `STOCKS` table

| Method | Endpoint                        | Description                        |
|--------|----------------------------------|------------------------------------|
| GET    | `/stocks/all-stocks`            | List all stocks                    |
| GET    | `/stocks/all-stocks/by-ticker`  | Get one stock by ticker (query)    |
| GET    | `/stocks/all-stocks/{sector}`   | Get stocks by sector (path var)    |

### 📈 From `CURRENT_STOCKS` table

| Method | Endpoint                              | Description                        |
|--------|----------------------------------------|------------------------------------|
| GET    | `/stocks/my-stocks`                   | List all currently held stocks     |
| GET    | `/stocks/my-stocks/by-ticker`         | Get current stock by ticker        |

> ℹ️ POST, PUT, PATCH, DELETE endpoints are not yet implemented.

---

## 🔄 Want to run without a database?

If you want to try the full CRUD version of StockManager **without Oracle or JPA**,  
check out the dedicated branch:

👉 [`in-memory-version`](https://github.com/hemuba/stockmanager-be/tree/in-memory-version)

It supports:

- ✅ All REST methods: GET, POST, PUT, PATCH, DELETE
- ✅ In-memory storage (no DB)
- 🚫 No persistence on restart

Ideal for frontend testing, mock APIs, or quick demos.

---


## ⚙️ Run Locally

```bash
# Clone the repo
git clone https://github.com/hemuba/stockmanager-be.git
cd stockmanager-be

# Run with dev profile
./mvnw spring-boot:run -Dspring.profiles.active=dev
```

Default ports:
- `dev` → 8081
- `prod` → 8080

---

## 🛡️ Author

**Alessandro De Vincenti**  
Software Engineer  
Java EE | Spring Boot | PL/SQL | Bash | Python
