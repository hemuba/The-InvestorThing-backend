# 💼 InvestMate Backend

![Java](https://img.shields.io/badge/Java-17+-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Build](https://img.shields.io/badge/build-passing-brightgreen)
![Profile](https://img.shields.io/badge/Profile-dev%2Fprod-orange)

> 🧱 *This backend is the first building block of the full InvestMate platform.*  
> Upcoming modules include a web dashboard, analytics engine, and automation tools.

**InvestMate** is a Spring Boot REST API designed to manage and analyze your investment portfolio.  
It currently integrates with Oracle Database and supports operations for both **Stocks** and **ETFs**.

---

## 🚀 Features

- ✅ CRUD for **Stocks** and **Current Holdings**
- ✅ CRUD for **ETFs** and **Owned ETFs**
- ✅ DTO structure (Request/Response)
- ✅ Centralized JSON responses with `ApiResponse`
- ✅ Global error handling via `@ControllerAdvice`
- ✅ Input validation with Jakarta Validation
- ✅ Logging with SLF4J + Logback
- ✅ Multi-profile support: `dev` / `prod`
- ✅ Modular service and repository layers
- 🔜 Scheduled price updates, analytics, and statistics modules (coming soon)

---

## 🧠 Tech Stack

- Java 17+
- Spring Boot 3.x
- Maven
- Oracle Database 19c
- Jakarta Validation
- SLF4J + Logback
- Postman (for API testing)

---

## 📊 API Overview

### 📈 STOCKS

| Method | Endpoint                             | Description                        |
|--------|---------------------------------------|------------------------------------|
| GET    | `/investment-manager/all-stocks`     | Get all stocks                     |
| GET    | `/investment-manager/all-stocks/by-ticker` | Get a stock by ticker       |
| GET    | `/investment-manager/all-stocks/{sector}`  | Filter stocks by sector      |
| GET    | `/investment-manager/my-stocks`      | Get current holdings               |
| GET    | `/investment-manager/my-stocks/by-ticker` | Get current stock by ticker |
| POST   | `/investment-manager/my-stocks/add-stock` | Add a stock to wallet       |
| DELETE | `/investment-manager/my-stocks/{ticker}` | Delete a stock from wallet  |

### 💰 ETFs

| Method | Endpoint                                | Description                          |
|--------|------------------------------------------|--------------------------------------|
| GET    | `/investment-manager/all-etf`           | List all ETFs                        |
| GET    | `/investment-manager/all-etf/by-ticker` | Get ETF by ticker                    |
| GET    | `/investment-manager/all-etf/{theme}`   | Filter ETFs by theme                 |
| POST   | `/investment-manager/all-etf/add-etf`   | Add a new ETF to repository          |
| GET    | `/investment-manager/my-etf`            | List currently owned ETFs            |
| GET    | `/investment-manager/my-etf/by-ticker`  | Get owned ETF by ticker              |
| POST   | `/investment-manager/my-etf/add-etf`    | Add ETF to your wallet               |

---

## 🧪 Want to test without a database?

Check out the lightweight version of InvestMate with in-memory storage:

👉 [`in-memory-version`](https://github.com/hemuba/investmate-backend/tree/in-memory-version)

- ✅ All REST methods (GET, POST, PUT, PATCH, DELETE)
- ✅ Works without Oracle DB
- 🚫 No persistence after restart

Perfect for demos, frontend testing, or rapid prototyping.

---

## ⚙️ Run Locally

```bash
# Clone the project
git clone https://github.com/hemuba/investmate-backend.git
cd investmate-backend

# Run with development profile
./mvnw spring-boot:run -Dspring.profiles.active=dev
```

**Ports:**
- `dev` → `8081`
- `prod` → `8080`

---

## 👤 Author

**Alessandro De Vincenti**  
Software Engineer  
💻 Java | Spring Boot | PL/SQL | Bash | Python  

---

## 📌 License

This project is licensed under the MIT License.