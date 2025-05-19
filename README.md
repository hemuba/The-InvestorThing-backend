# The Investor Thing – Backend

This project is a **Java Spring Boot backend** integrated with **Oracle Database** and enhanced with **Python scripts** for financial data retrieval.  
It is part of a larger full-stack investment portfolio manager application.

> WIP – A FE (React/TypeScript) will be added in a separate module.

---

## Tech Stack

- **Java 17**
- **Spring Boot 3**
  - Spring Web (REST API)
  - Spring Data JPA (Hibernate)
- **Oracle Database 19c**
- **Maven**
- **Python 3.12** (for data scripts)
  - `yfinance` (market data)
  - `oracledb` (DB connector)
- **SLF4J + Logback** (logging)
- **JUnit & Mockito** (testing)

---

## Features

- Track **current holdings** (stocks, ETFs, crypto)
- Store **historical prices**
- Insert and update market data from external APIs
- REST endpoints for managing and retrieving portfolio data
- Python scripts for **automated ingestion** of fresh data into Oracle

---

## Python Integration

This project includes Python scripts under `scripts/` to populate the database with fresh market data.

- `update_crypto_history.py` → updates `CRYPTO_HISTORY` table
- `update_etf_history.py` → updates `ETF_HISTORY` table

**Execution:**
```bash
python scripts/update_crypto_history.py
```

---

## Disclaimer

This project uses the [`yfinance`](https://github.com/ranaroussi/yfinance) library to download public market data.  
**The data is used exclusively for educational and personal use. No data is redistributed or monetized.**

---

## Running the Backend

1. Configure Oracle DB and environment variables:
    - `SCHEMA_PASSWORD`
    - `DSN` (e.g., `localhost:1521/XXXX`)
2. Start the Spring Boot backend:
```bash
./mvnw spring-boot:run
```

---

## Project Structure (Backend)

```
theinvestorthing-backend/
│
├── src/main/java/com/theinvestorthing/backend/
│ ├── common/
│ ├── crypto/
│ ├── etf/
│ ├── stocks/
│ └── TheInvestorThingApplication.java
│
├── scripts/
│ ├── crypto_updates/
│ │ └── update_crypto_history.py
│ ├── etf_updates/
│ │ └── update_etf_history.py
│ ├── common/
│ │	└── db_connection.py
│ └── logs/ # gitignored
│
├── logs/ # gitignored
├── .gitignore
├── README.md
└── pom.xml
```

---

##  Roadmap

- [x] Backend Spring Boot setup
- [x] Oracle DB integration
- [x] Crypto & ETF tracking via Python
- [ ] Frontend UI (React/TypeScript)
- [ ] User authentication
- [ ] Real-time dashboards
- [ ] Portfolio analytics

---

## Author

**Alessandro De Vincenti**  
Software Engineer 
Passionate about software engineering and automation.

---

## License

This project is licensed under the MIT License.
