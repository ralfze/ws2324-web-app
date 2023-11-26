# DICE List API Documentation

# dice-web-server
A dice web server in Springboot with react frontend.
Backend communicates with a mongoDB database server.
Backend communicates with REST.

## Needed Pre-Conditions for online development
1. Change Cross-Origin URL to Frontend URL in file \
Path: ws2324-web-app/backend/src/main/java/com/diceweb/diceapp/DiceController.java

```java
File:DiceController.java
@CrossOrigin(origins = "NEW-URL-FROM-FRONTEND", allowCredentials = "true")

```

2. Change URL for requests in the Frontend to Backend URL in file \
Path: ws2324-web-app/frontend/src/App.js

```javascript
File:App.js
const baseURL = "NEW-URL-TO-BACKEND";

```
3. Expose PORT of the Backend public
```bash
gp ports visibility 8081:public
```

## Overview

This repository contains the source code for a RESTful API that manages a list of DICE items. The API provides endpoints for listing, creating, updating, and deleting DICE items.

## API Endpoints
dice-web-backend: http://localhost:8081 \
dice-web-backend-actuator: http://localhost:8081/actuator \
dice-web-backend-api-docs: http://localhost:8081/api-docs \
dice-web-backend-swagger: http://localhost:8081/swagger-ui/index.html

dice-web-frontend: http://localhost:3000

mongo: http://localhost:27017

jaeger-service: http://localhost:16686


### Get All DICE Items

- **URL:** `/dices`
- **HTTP Method:** GET
- **Description:** Retrieve a list of all DICE items.

### Get DICE Item by ID

- **URL:** `/dices/{id}`
- **HTTP Method:** GET
- **Description:** Retrieve a specific DICE item by its ID.

### Create DICE Item

- **URL:** `/dices/`
- **HTTP Method:** POST
- **Description:** Create a new DICE item with the given title.

### Update DICE Item

- **URL:** `/dices/{id}?message=`
- **HTTP Method:** PUT
- **Description:** Update an existing DICE item with the specified ID. Allows to change via Parameter "?message=".

### Delete DICE Item

- **URL:** `/dices/{id}`
- **HTTP Method:** DELETE
- **Description:** Delete a DICE item with the specified ID.

### Update DICE Item

- **URL:** `/dices/{id}/reroll`
- **HTTP Method:** PUT
- **Description:** Update an existing DICE item with the specified ID. Allows to reroll the last roll.

## Data Model

The API uses a data model for DICE items with the following attributes:

- `id` (Integer): The unique identifier of the DICE item.
- `rolledNumber` (int): The rolled Number of the last roll.
- `sizeOfTheDice` (int): The rolled Number of the last roll.
- `created` (Instant): The date/time when the roll was created.
- `modified` (Instant): The date/time of the new roll.
- `message` (String): The message of the roll.


## Prerequisites

Before running the API, make sure you have the following prerequisites installed:

- Java Development Kit (JDK)
- Spring Boot framework
- A database (e.g., H2, MySQL) if the `DiceRepository` is configured to use one

## Running the Application

To run the application, follow these steps:

1. Clone this repository to your local machine.
2. Build and run the project using a Java IDE or the command line.

### Configuration

You can configure the application by modifying the application properties or configuration files as needed.

## Configure Environment Variables (tested but missing link in apps)
eval $(gp env -e FRONTEND_URL=`gp url 3000`)
eval $(gp env -e BACKEND_URL=`gp url 8081`)

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

# Some Information

## Compile Backend
./mvnw clean install 

### Build run
./mvnw spring-boot:run

### Delete Docker Container after stop
docker compose up
docker compose down
#### Delete Docker Images
docker image rm dice-web-app/backend && docker image rm dice-web-app/frontend


## Run React UI
npm start

### Package React UI
npm run build

### Components used for UI
npm i @chakra-ui/react @emotion/react @emotion/styled framer-motion
npm i @chakra-ui/icons
npm i axios