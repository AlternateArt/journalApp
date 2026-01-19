# 📔 Journal App (Spring Boot + MongoDB)

A robust backend application built with **Java** and **Spring Boot** to manage daily journal entries, using **MongoDB** for flexible, document-based data storage. This project demonstrates RESTful API development, dependency injection, and the Repository pattern.

## 🚀 Features

* **CRUD Operations:** Create, Read, Update, and Delete journal entries.
* **NoSQL Integration:** Uses `Spring Data MongoDB` for efficient data handling.
* **REST API:** Exposes endpoints for interacting with journal data.
* **Scalable Architecture:** Built with the Controller-Service-Repository pattern.

## 🛠️ Tech Stack

* **Language:** Java (JDK 17 or later)
* **Framework:** Spring Boot 3+
* **Database:** MongoDB (NoSQL)
* **Build Tool:** Maven
* **Testing:** JUnit 5, Mockito

## 💻 Getting Started

Follow these steps to get the application running on your local machine.

### Prerequisites
1.  **Java Development Kit (JDK):** Version 17 or higher.
2.  **MongoDB:** You must have MongoDB installed locally OR a connection string to a cloud cluster (MongoDB Atlas).

### Installation & Run

1.  **Clone the repository**
    ```bash
    git clone [https://github.com/AlternateArt/journalApp.git](https://github.com/AlternateArt/journalApp.git)
    ```

2.  **Navigate to the project folder**
    ```bash
    cd journalApp
    ```

3.  **Configure Database**
    * Open `src/main/resources/application.properties` (or `.yml`).
    * Verify the MongoDB connection string.
    * *Default Local Setting:*
      ```properties
      spring.data.mongodb.uri=mongodb://localhost:27017/journaldb
      spring.data.mongodb.auto-index-creation=true
      ```

4.  **Run the Application**
    * **On Windows:**
      ```bash
      ./mvnw.cmd spring-boot:run
      ```
    * **On Mac/Linux:**
      ```bash
      ./mvnw spring-boot:run
      ```

5.  **Access the App**
    * Server starts at: `http://localhost:8080`
    * Test endpoints using Postman or your browser.

## 📂 Project Structure

```text
src
├── main
│   ├── java/com/alternateart/journalapp
│   │   ├── controller  # Handles HTTP Requests
│   │   ├── entity      # @Document classes (MongoDB Models)
│   │   ├── repository  # Extends MongoRepository
│   │   ├── service     # Business Logic
│   │   └── JournalAppApplication.java
│   └── resources
│       └── application.properties # MongoDB Config
└── test                # Unit Tests
