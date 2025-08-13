# Changelog

## [Unreleased]
- Turn CentLedger into a REST API using Javalin or Spring Boot
- Add editing and deleting of entries
- Show totals and simple financial overviews
- Filter transactions by date range
- Create API endpoints for creating, reading, updating, and deleting entries
- Support other storage formats like SQLite, JSON, or a full database

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
- Working REST API! Can create and retrieve entries via HTTP endpoints
- Spring Boot application with proper service/controller architecture
- Persistent CSV storage that loads on startup and saves after each entry
- Clean separation of concerns with domain models, service layer, and web layer
- Still missing: editing/deleting entries, user authentication, database integration
- No totals, summaries, or date filtering — yet
- Ready for frontend integration or API testing with tools like Postman
