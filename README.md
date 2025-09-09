# CentLedger - CLI Personal Finance Ledger

*A lightweight, secure personal finance API written in Java*

![Java](https://img.shields.io/badge/language-Java-orange)
![Build](https://img.shields.io/badge/build-Gradle-blue)
![License](https://img.shields.io/badge/license-MIT-green)
![Status](https://img.shields.io/badge/status-Pre--Alpha-yellow)

---

## **About**

CentLedger is a **Java-based finance management API** with UUID-based transaction and budget management. Features include persistent CSV storage, RESTful endpoints with proper HTTP responses, and a modular architecture ready for CLI tools, web applications, and mobile apps.

---

## **Current Features**

* ✅ UUID-based transaction and budget management
* ✅ Manage incomes, expenses, and budgets with HashMap storage
* ✅ Persistent CSV storage with automatic UUID generation
* ✅ REST API with proper HTTP status codes and location headers
* ✅ Lightweight, scalable Java Spring Boot backend

## **Planned Features**

* 🔄 Secure user authentication and password hashing
* 🔄 Database integration (SQLite/PostgreSQL)
* 🔄 Transaction filtering and search capabilities
* 🔄 Budget-expense linking and automatic updates
* 🔄 Financial reporting and analytics
* 🔄 API documentation with Swagger/OpenAPI

---

## **Tech Stack**

* **Language:** Java
* **Build Tool:** Gradle
* **Libraries:**

  * OpenCSV (CSV read/write with UUID support)
  * Spring Boot (REST API with proper HTTP responses)

* **Storage:** CSV-based persistence (database integration planned)
* **Architecture:** Service-Controller pattern with domain models

---

## **Installation**

Clone the repository and run the application:

```bash
git clone <repository-url>
cd CentLedger
./gradlew :app:bootRun
```

The API will start on `http://localhost:8080` by default.

## **Usage**

### Adding Transactions
```bash
# Add an income
curl -X POST http://localhost:8080/api/entries \
  -H "Content-Type: application/json" \
  -d '{"type": "INCOME", "desc": "Salary", "cents": 500000}'

# Add an expense
curl -X POST http://localhost:8080/api/entries \
  -H "Content-Type: application/json" \
  -d '{"type": "EXPENSE", "desc": "Groceries", "cents": 7500}'
```

### Managing Budgets
```bash
# Create a budget
curl -X POST http://localhost:8080/api/budgets \
  -H "Content-Type: application/json" \
  -d '{"desc": "Monthly Food Budget", "current": 0, "max": 50000}'

# Get all budgets
curl http://localhost:8080/api/budgets
```

## **API Endpoints**

All endpoints return proper HTTP status codes and location headers for created resources. Entries and budgets are identified by UUIDs for reliable referencing.

| Method | Endpoint         | Description                            | Response |
| ------ | ---------------- | -------------------------------------- | -------- |
| POST   | `/api/entries`   | Add a new transaction (income/expense) | 201 Created with Location header |
| GET    | `/api/entries`   | Fetch all transactions as HashMap      | 200 OK |
| POST   | `/api/budgets`   | Create a new budget                    | 201 Created with Location header |
| GET    | `/api/budgets`   | Fetch all budgets as HashMap           | 200 OK |
| GET    | `/api/health`    | Check API health                       | 200 OK |

---


## **Contributing**

PRs welcome! Fork, branch, and submit pull requests. I'm learning how to use Github so hit me!

---

## **License**

MIT — See `LICENSE`!


