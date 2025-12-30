# To-Do List Application

A simple and efficient To-Do List application built with Spring Boot 3, Java 21, and MySQL. This project demonstrates a classic REST API with a focus on clean architecture and containerization.

## Technologies Used
* Java 21
* Spring Boot 3.x
* Spring Data JPA (Hibernate)
* MySQL 8.0
* Docker & Docker Compose
* Maven

## Getting Started

### Prerequisites
* JDK 21 installed.
* Maven installed (or use the included ./mvnw).
* MySQL (if running locally without Docker).

### Option 1: Running Locally (Windows/Development)
1. Configure the Database:
   Ensure you have a MySQL schema named todolist_db. Update src/main/resources/application.properties with your local credentials if they differ from:
    - User: root
    - Password: user

2. Run the Application:
   Open your terminal in the project root and run:
   ./mvnw spring-boot:run

   The API will be available at http://localhost:8080.

## Running with Docker (Containerized)

This project is Docker-ready. You can deploy the entire stack (App + Database) without installing MySQL on your host machine.

1. Build the JAR file:
   ./mvnw clean package -DskipTests

2. Spin up the containers:
   docker compose up --build -d

3. Access the App:
    - Application: http://localhost:8081 (Mapped to internal 8080)
    - MySQL: localhost:3307 (Mapped to internal 3306)

## API Endpoints (Quick Reference)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| GET | /api/tasks | Retrieve all tasks |
| POST | /api/tasks | Create a new task |
| PUT | /api/tasks/{id} | Update an existing task |
| DELETE | /api/tasks/{id} | Delete a task |

## Project Structure
- src/main/java: Backend logic (Controllers, Services, Models).
- src/main/resources: Configuration and DB properties.
- Dockerfile: Instructions for the app image.
- docker-compose.yml: Orchestration for App and MySQL.

