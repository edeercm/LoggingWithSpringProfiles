# Environment-Specific Logging with Spring Profiles - Project Documentation

## Project Overview

This project demonstrates how to configure a Spring Boot application to use different logging settings depending on the environment where it runs. Using Spring Profiles (`dev`, `test`, `prod`), we set up specific logging levels and outputs to help developers during development, testers during testing, and keep logs clean and efficient in production.

We also built a simple REST API to manage a list of users, with basic logging added to track application behavior.

---

## What We Did Step by Step

### Step 1: Project Setup

- Created a new Spring Boot project with dependencies: Spring Web and Spring Boot Actuator.  
- Imported the project into an IDE like IntelliJ or Eclipse.

### Step 2: Create REST Endpoints

- Developed a simple REST API with three endpoints:  
  - `GET /users` to list all users.  
  - `POST /users` to add a new user.  
  - `DELETE /users/{id}` to delete a user by their ID.  
- Added logging inside these endpoints to:  
  - Log successful operations with INFO level.  
  - Log warnings or errors for invalid input or when a user doesn’t exist.

### Step 3: Configure Profiles

- Created three profile-specific configuration files:  
  - `application-dev.properties`  
  - `application-test.properties`  
  - `application-prod.properties`  
- Each file sets environment-specific properties like server port and logging levels.  
- For example, the `dev` profile logs detailed DEBUG and INFO messages, while `prod` only logs WARN and ERROR.

### Step 4: Configure Logging with Logback

- Created a `logback-spring.xml` file to manage logging behavior.  
- Defined two logging outputs (appenders):  
  - Console appender for `dev` and `test` profiles.  
  - File appender for `prod` profile, saving logs in a `logs/app.log` file.  
- Set logging levels for each profile:  
  - `dev`: DEBUG and INFO logs in console.  
  - `test`: Only INFO logs in console.  
  - `prod`: WARN and ERROR logs saved to file.

### Step 5: Testing Profiles and Logging

- Activated different profiles by setting `spring.profiles.active` in `application.properties` or via command line.  
- Verified that each profile shows logs with correct levels and outputs (console or file).  
- Tested REST endpoints with tools like Postman to see logs generated in real time.

---

## Why This Matters

- **Improved Debugging:** In development, detailed logs help quickly find issues.  
- **Cleaner Testing Logs:** Testing environment shows less noisy logs focusing on important info.  
- **Efficient Production Logs:** Production only records warnings and errors, saving resources and making it easier to spot real problems.  
- **Flexible Configuration:** Using profiles allows easy switching between environments without changing code.  
- **Better Maintenance:** Logs are organized and useful for monitoring app health and troubleshooting.

---

## How to Run This Project

1. Clone the repository to your local machine.  
2. Open the project in your favorite IDE.  
3. Choose the active profile by editing `application.properties` or passing a command-line argument, e.g.:  
   - `spring.profiles.active=dev`  
   - `spring.profiles.active=test`  
   - `spring.profiles.active=prod`  
4. Run the application.  
5. Use Postman or similar to call the REST endpoints (`GET /users`, `POST /users`, `DELETE /users/{id}`).  
6. Observe logs in the console or in the log file depending on the profile.

---

## Summary

This project gives you a clear example of how to configure environment-specific logging in a Spring Boot application using Spring Profiles and Logback. It balances the need for detailed information in development with performance and clarity in production. The simple user management REST API provides practical examples of logging in action.

---

