# VoltGrid V2 – Execution Guide

## Overview
This guide walks you through the steps required to build, run, and test the **VoltGrid V2** EV‑station simulation project. The project is a pure Java Maven application (see `pom.xml`). It models an electric‑vehicle charging station with reservation handling, billing, priority strategies, and concurrency simulation.

---

## Prerequisites
| Requirement | Version |
|-------------|---------|
| **Java Development Kit (JDK)** | 17 or later |
| **Maven** | 3.9.x |
| **Git** (optional, for source control) | any |
| **IDE** (IntelliJ IDEA, VS Code, Eclipse, …) | recommended |

Make sure `java` and `mvn` are available on your `PATH`:
```bash
java -version
mvn -version
```

---

## 1. Clone the Repository (if you haven't already)
```bash
git clone <repository‑url> "D:/VoltGrid V2"
cd "D:/VoltGrid V2"
```
(If you already have the folder, skip this step.)

---

## 2. Build the Project
From the project root (`D:/VoltGrid V2`):
```bash
mvn clean compile
```
* `clean` removes previous build artifacts.
* `compile` compiles all sources under `src/main/java`.

If you need to package a runnable JAR:
```bash
mvn package
```
The JAR will be placed in `target/voltgrid-v2-<version>.jar`.

---

## 3. Run the Application
The entry point lives in `src/main/java/com/evstation/app/EVStationApp.java`.
### 3.1 Using Maven Exec Plugin (no JAR required)
```bash
mvn exec:java -Dexec.mainClass="com.evstation.app.EVStationApp"
```
### 3.2 Using the Packaged JAR
```bash
java -jar target/voltgrid-v2-*.jar
```
Both commands start an interactive console menu (see `MenuController`). The console lets you:
* Add vehicles (personal, commercial, emergency)
* Create reservations
* View charging bays and their status
* Run the **charging simulation** (concurrency demo)

---

## 4. Configuration
`src/main/java/com/evstation/config/StationConfig.java` reads static configuration values (number of bays, tariff rates, etc.).
* To change defaults, edit the constants in this class and rebuild.

---

## 5. Running Tests (if any)
The project currently does not ship unit tests, but you can add them under `src/test/java`. To run existing tests:
```bash
mvn test
```
---

## 6. Common Commands Summary
| Step | Command | Purpose |
|------|---------|---------|
| Build | `mvn clean compile` | Compile source files |
| Package | `mvn package` | Produce a runnable JAR |
| Run (Maven) | `mvn exec:java -Dexec.mainClass=...` | Launch without JAR |
| Run (JAR) | `java -jar target/…jar` | Launch packaged artifact |
| Clean | `mvn clean` | Delete `target/` folder |
| Test | `mvn test` | Execute test suite |

---

## 7. Troubleshooting
* **`JAVA_HOME` not set** – Set `JAVA_HOME` to your JDK installation directory.
* **Maven not found** – Install Maven and add its `bin` directory to `PATH`.
* **Compilation errors** – Ensure you are using JDK 17+; older versions lack required language features.
* **`Port already in use`** – The simulation spawns threads but does not bind network ports. If you added network code, verify free ports.

---

## 8. Extending the Project
1. Add new **strategy** implementations under `strategy/pricing` or `strategy/priority` and wire them in `StationConfig`.
2. Create additional **hardware** components in `hardware/` and inject them via dependency injection (manual constructor injection is used).
3. Enhance the UI by replacing the console `MenuController` with a Swing/JavaFX front‑end.

---

*Document last updated: 2026‑11‑23*