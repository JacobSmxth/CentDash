# LedgerLite - CLI Personal Finance Ledger

*A lightweight, secure personal finance API written in Java*

![Java](https://img.shields.io/badge/language-Java-orange)
![Build](https://img.shields.io/badge/build-Gradle-blue)
![License](https://img.shields.io/badge/license-MIT-green)
![Status](https://img.shields.io/badge/status-Pre--Alpha-yellow)

---

## **About**

LedgerLite is a **Java-based finance management API** designed for speed, security, and modularity.
It will serve as a backend foundation for CLI tools, web applications, and potentially mobile apps. All interacting through a clean, hopefully well-documented REST interface.

---

## **Planned Features**

*  Secure password hashing
*  Manage incomes, expenses, and budgets
*  Persistent storage (CSV now, database planned)
*  Lightweight, scalable Java backend
*  REST API-ready architecture for easy integration into web or mobile apps

---

## **Tech Stack**

* **Language:** Java* **Build Tool:** Gradle
* **Libraries:**

  * OpenCSV (current CSV read/write)
  * Spring Boot (planned for REST endpoints)

* **Planned Database:** SQLite or PostgreSQL

---

## **Installation**

More info once finished. For now its a gradle project so download then:
```./gradlew run```

## **Usage**

## **API Endpoints** *(Planned)*

| Method | Endpoint         | Description                  |
| ------ | ---------------- | ---------------------------- |
| POST   | `/auth/register` | Register a new user          |
| POST   | `/auth/login`    | Authenticate and receive JWT |
| GET    | `/transactions`  | Fetch all transactions       |
| POST   | `/income`        | Add new income entry         |
| POST   | `/expense`       | Add new expense entry        |
| GET    | `/budget`        | Retrieve current budgets     |

---


## **Contributing**

PRs welcome! Fork, branch, and submit pull requests. I'm learning how to use Github so hit me!

---

## **License**

MIT — See `LICENSE`!


