# 🧪 StockManager – In-Memory Version (Full CRUD, No DB)

This branch implements a complete REST API for managing stock investments,  
**without using any database**. All data is stored in memory using Java `List` objects and will be lost after each restart.

---

## 🎯 Purpose

- ✅ Test the full backend logic without Oracle or JPA setup
- ✅ Supports complete CRUD (Create, Read, Update, Delete)
- ✅ Perfect for frontend testing, demos, and local experiments
- 🚫 No persistence — data resets on app restart

---

## 🧠 Key Differences from `master` branch

| Feature                  | `in-memory-version` (this branch) | `master` (JPA-based) |
|--------------------------|-----------------------------------|-----------------------|
| Database connection      | ❌ None                            | ✅ Oracle 19c         |
| JPA Entities             | ❌ Not used                        | ✅ Yes                |
| Persistence              | ❌ In-memory only                  | ✅ Full DB            |
| CRUD Support             | ✅ Full (GET, POST, PUT, PATCH, DELETE) | 🟡 WIP           |

---

## 🔁 Full REST Endpoints

### 📘 GET

| Endpoint                 | Description                         |
|--------------------------|-------------------------------------|
| `GET /stocks`            | List all stocks                     |
| `GET /stocks/by`         | Filter stocks by ticker and/or sector |

### ➕ POST

| Endpoint                  | Description                   |
|---------------------------|-------------------------------|
| `POST /stocks`            | Add a single stock            |
| `POST /stocks/batch`      | Add multiple stocks (batch)   |

### ❌ DELETE

| Endpoint                   | Description              |
|----------------------------|--------------------------|
| `DELETE /stocks/{ticker}`  | Remove a stock by ticker |

### 🔁 PUT

| Endpoint                  | Description                 |
|---------------------------|-----------------------------|
| `PUT /stocks/{ticker}`    | Replace all fields of a stock |

### ✏️ PATCH

| Endpoint                    | Description                  |
|-----------------------------|------------------------------|
| `PATCH /stocks/{ticker}`    | Partially update a stock     |

---

## ⚙️ How to Run

```bash
# Clone the repo and checkout this branch
git clone https://github.com/hemuba/stockmanager-be.git
cd stockmanager-be
git checkout in-memory-version

# Run the application
./mvnw spring-boot:run
```

Runs on default port: **8080**

---

## 🛡️ Author

**Alessandro De Vincenti**  
Software Engineer  
Java EE | Spring Boot | PL/SQL | Bash | Python