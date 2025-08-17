# Changelog

## [Unreleased]
- Add editing and deleting of entries and budgets
- Show totals and simple financial overviews
- Filter transactions by date range
- Create API endpoints for updating and deleting entries and budgets
- Support other storage formats like SQLite, JSON, or a full database
- Link expenses to automatically update budgets when created
- Refine BudgetCategory enum and its use cases

## [0.4.0] - Budget Management System - Day 9
- Added comprehensive budget management system with full CRUD operations
- Created `Budget` domain class with UUID identification, description, current/max amounts in cents, and last edit timestamps
- Implemented `BudgetCategory` enum for NEEDS/WANTS categorization (foundation for future features)
- Built `BudgetService` class with HashMap-based storage for improved lookup performance
- Added CSV persistence for budgets using `budgets.csv` with proper headers and data validation
- Created `BudgetController` with REST endpoints for budget management:
  - `GET /api/budgets` - Retrieve all budgets as HashMap
  - `POST /api/budgets` - Create new budget with auto-generated UUID
- Separated budget logic from ledger logic into dedicated service and controller layers
- Refactored `LedgerService` to remove budget-related code for cleaner architecture
- Fixed small bugs related to budget creation and storage
- Changed budget storage from list-based to HashMap for O(1) lookup performance
- Used Java records for clean request DTOs (`CreateBudget`)

### Day 8
- No work

### Day 7
- No work

### Day 6
- No work

### Day 5
- No work

## [0.3.1] - Project Rename
- Renamed project from LedgerLite to CentLedger
- Updated all package names from `com.ledgerlite` to `com.centledger`
- Renamed main application class from `LedgerLiteApplication` to `CentLedgerApplication`
- Updated build configuration and project files

## [0.1.0] - Early Build

### Day 1
- Started with the very first `LedgerLite.java` file
- Built the `Entry` class to store a description, an amount in cents (using `long` for accuracy), and a timestamp (`LocalDateTime`)
- Added the `Ledger` class to hold a list of entries in memory
- Created `Income` and `Expense` classes extending `Entry` so transactions are clearly typed
- Wrote simple methods to add income, add expenses, and print all entries to the console

### Day 2
- Added more helper methods in `Ledger` to make working with entries cleaner
- Tried writing a CSV manually using plain Java I/O (`FileWriter` and `BufferedReader`)
- Hit the usual pain points with manual parsing, so switched to using a CSV library
- Moved everything into a Gradle project to manage dependencies easily

### Day 3
- Integrated OpenCSV for painless CSV read/write
- Built `writeFile` to save all entries to `data.csv` with headers
- Built `loadFile` to read `data.csv` on startup and restore previous entries
- Added the `EntryType` enum (`INCOME`, `EXPENSE`) to get rid of string checks and make type handling cleaner
- Updated `Income` and `Expense` constructors to automatically set their `EntryType`
- Adjusted CSV logic to read and write using the enum instead of raw strings

### Day 4
- Complete restructure to prepare for Spring Boot integration
- Broke apart the monolithic `LedgerLiteApplication.java` into proper service and controller layers
- Created `LedgerService` class to handle all business logic and CSV operations
- Built `LedgerController` with REST endpoints (`/api/health`, `/api/entries`)
- Moved domain classes (`Entry`, `Income`, `Expense`, `EntryType`) into their own package
- Added Spring Boot dependencies and annotations (`@Service`, `@RestController`, `@RequestMapping`)
- Created `application.properties` for configuration (CSV path, server port)
- Got the REST API working! Can now POST new entries and GET all entries via HTTP
- Added proper HTTP status codes and request/response handling
- Used Java records for clean request DTOs (`CreateEntry`)

## Current Status
- Working REST API with both ledger and budget management! Can create and retrieve entries and budgets via HTTP endpoints
- Spring Boot application with proper service/controller architecture and separated business logic
- Dual CSV persistence: `data.csv` for entries, `budgets.csv` for budgets - both load on startup and save after each operation
- Clean separation of concerns with domain models, dedicated service layers, and web controllers
- HashMap-based budget storage for O(1) lookup performance with UUID-based identification
- Still missing: editing/deleting entries and budgets, user authentication, database integration
- No automatic budget updates when expenses are added, no totals, summaries, or date filtering — yet
- Ready for frontend integration or API testing with tools like Postman
