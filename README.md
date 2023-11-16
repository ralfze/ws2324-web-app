# TODO List API Documentation

# dice-web-server
A dice web server in Springboot with react frontend.
Backend communicates with a mongoDB database server.
Backend communicates with REST.

## Overview

This repository contains the source code for a RESTful API that manages a list of TODO items. The API provides endpoints for listing, creating, updating, and deleting TODO items.

## API Endpoints

### Get All TODO Items

- **URL:** `/todos`
- **HTTP Method:** GET
- **Description:** Retrieve a list of all TODO items.

### Get TODO Item by ID

- **URL:** `/todos/{id}`
- **HTTP Method:** GET
- **Description:** Retrieve a specific TODO item by its ID.

### Create TODO Item

- **URL:** `/todos/{title}`
- **HTTP Method:** POST
- **Description:** Create a new TODO item with the given title.

### Update TODO Item

- **URL:** `/todos/{id}/{title}`
- **HTTP Method:** PUT
- **Description:** Update an existing TODO item with the specified ID by setting its title to the new value.

### Delete TODO Item

- **URL:** `/todos/{id}`
- **HTTP Method:** DELETE
- **Description:** Delete a TODO item with the specified ID.

## Data Model

The API uses a data model for TODO items with the following attributes:

- `id` (Integer): The unique identifier of the TODO item.
- `title` (String): The title or description of the TODO item.

## Prerequisites

Before running the API, make sure you have the following prerequisites installed:

- Java Development Kit (JDK)
- Spring Boot framework
- A database (e.g., H2, MySQL) if the `TodoRepository` is configured to use one

## Running the Application

To run the application, follow these steps:

1. Clone this repository to your local machine.
2. Build and run the project using a Java IDE or the command line.

### Configuration

You can configure the application by modifying the application properties or configuration files as needed.

## Usage


To run the application and its associated PostgreSQL database using Docker Compose, follow these steps:

1. Clone this repository to your local machine.
2. Make sure you have Docker and Docker Compose installed.

3. Navigate to the root directory of the cloned repository where the `docker-compose.yml` file is located.

4. Open a terminal or command prompt.

5. Run the following command to start the services defined in the `docker-compose.yml` file:

   ```bash
   docker-compose up -d

6. Use your preferred API client (e.g., Postman, curl) to interact with the API endpoints as described in the documentation.


## Compile Backend
./mvnw clean install 

### Build run
./mvnw spring-boot:run

### Delete Docker Container after stop
docker compose up --force-recreate
docker compose down
#### Delete Docker Images
docker image rm dice-web-app/backend && docker image rm dice-web-app/frontend


## Run React UI
npm start

### Package React UI
npm run build

### Components used for UI
npm i @chakra-ui/react @emotion/react @emotion/styled framer-motion
npm install axios