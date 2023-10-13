
The Tasks and Users Management API is a Spring Boot application that provides a
RESTful API for managing tasks and users. This application allows users to perform
various actions, including creating tasks, updating tasks, managing users,
adding comments to tasks, and more.


## Features

    The API provides the following features:

## User Management:
    Retrieve the list of users
    Add a new user to the list
    Update the details of a user
    Remove a user from the list
    Activate/deactivate user accounts

## Task Management:
    Retrieve the list of tasks
    Add a new task to the list
    Update the details of a task
    Mark a task as completed
    Remove a task from the list
    Add comments to a task
    
## Database:
    Uses an in-memory database (H2) to store tasks, users, and comments

## Prerequisites

    Before getting started with this project, ensure you have the following prerequisites:
    
    IntelliJ IDEA installed
    Java Development Kit (JDK) 17 or higher installed
    Spring Boot installed
    Maven for building and managing dependencies

## Steps to Run the Application

1. Open Your Project:

    - Open IntelliJ IDEA.
    - Select "File" > "Open" and navigate to your project directory.
    - Click "Open" to load your project.
   
2. Locate the Main Class:
    - Find the main class of your Spring Boot application.
   This class  contains a `main` method in `TasksAndUsersApiApplication` class.


3. Run the Application:

    - Open the `TasksAndUsersApiApplication.java` file, which should be your main class.
    - Click the green arrow to start the application.
    - Open a web browser and navigate to the URL, which is usually `http://localhost:8080/`

## How to test the Application

4. Run The Unit Tests:

    - Navigate to `src/test/java/com/shufersalOnline/tasksAndUsersApi` directory that contains my test classes.
    - This directory typically includes subdirectories for unit tests and integration tests.
    - Click the green arrow to start the specific test.


