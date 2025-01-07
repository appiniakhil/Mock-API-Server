# Mock API Server

This is a simple, yet flexible mock API server built with Spring Boot. It allows you to define mock endpoints and responses using a database, enabling you to easily create mock APIs for testing or development purposes.

## Features

-   **Dynamic Mocking:**  Define mock API endpoints and responses using a REST API, stored in a database.
-   **Flexible Matching:**  The server can match requests based on URL path, HTTP method, and request headers.
-   **Response Processing:** Response bodies can be processed by data faker for dynamic data generation.
-   **Configurable Response Codes:**  Set custom HTTP status codes for mock responses.
-   **Request Header Capturing:**  Request headers are saved along with the configuration.
-   **Virtual Threads**: Enabled virtual thread support.

## Getting Started

### Prerequisites

-   Java 17 or higher
-   Gradle (if you wish to build from source)
-   A database server (PostgreSQL, MySQL are supported and can be configured)

### Installation

1.  **Clone the repository:**

    ```bash
    git clone <your-repo-url>
    cd <your-project-directory>
    ```

2.  **Build the project with gradle:**

    ```bash
    ./gradlew build
    ```
   or use gradle idea import in intellij ide.

3. **Run the Spring Boot application:**
    ```bash
    ./gradlew bootRun
    ```

   Or run the class `org.testsigma.mockserver.MockserverApplication` using your IDE.

4.  **Ensure database is set up:**

   - Create a database with the name `mockserver` if using the default settings.
   - Update database configurations in the `application.properties` file.

### Configuration

The application's configuration is handled in the `application.properties` file. Here are the key settings:

-   **`spring.application.name`**: Sets the application's name.
-   **`server.port`**:  The port on which the server will listen (default `8080`).
-   **`spring.datasource.url`**: JDBC URL for your database.
-   **`spring.datasource.username`**:  Your database username.
-   **`spring.datasource.password`**: Your database password.
-   **`spring.datasource.driver-class-name`**: JDBC driver class name (e.g., `org.postgresql.Driver`).
-   **`spring.jpa.hibernate.ddl-auto`**:  Controls database schema creation/update (`update`, `create`, or `none`). This must be set to `none` in a production setting.
-   **`spring.sql.init.mode`**: Configures whether to use the sql init scripts.
-   **`jwt.secret`**: The secret key for JWT tokens (ensure this is a strong, random key in production).
-   **`jwt.expiration`**:  Expiration time for JWT tokens in seconds.
-   **`spring.jpa.show-sql`**: Configures to view generated sql queries on startup.
-  **`spring.threads.virtual.enabled`**: Configures to use virtual threads.

### Key Endpoints

#### Authentication Controller (`AuthController`)

-   **`POST /api/auth/login`**:
    *   Authenticates a user and returns a JWT token.
    *   Requires a JSON payload like:
        ```json
        {
          "tenantId": "1234"
        }
        ```
    *   Example Response:
        ```json
          {
           "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjM0IiwiaWF0IjoxNzM2MjcyNDEwLCJleHAiOjE3MzcxMzY0MTB9.1wPznXw176kl9T7TOPATE-HvAmYOldyahBN4WxzwlrseqEChDcPKVSyX_XdEr-jUTF0V6PSsBeXWkqRNla4wGQ"
          }
        ```
    *  Note the port is 8085.

#### Configuration Controller (`ConfigurationController`)

-   **`POST /api/mock/config`**:
    *   Creates a new mock configuration.
    *   Requires a JSON payload representing the configuration. Example:
      
       ```json
      {
       "name": "my-mock-config",
       "uriPath": "/api/test2334",
       "method": "GET",
        "requestBody": null,
        "responseBody": "appini akhil",
       "response": "Test Response",
        "statusCode": 200
       }
``
    *   Example Response:
       ```json
        {
          "id": 5,
          "tenantId": "1234",
           "uriPath": "/api/test2334",
           "method": "GET",
           "requestBody": null,
           "responseBody": "appini akhil",
           "statusCode": 200,
          "delay": null,
         "responseHeaders": null
         }
        ```
    *  Note the port is 8085.

#### Mock Controller (`MockController`)

-   **`ANY /<any-path>`**:
    *   This is the main entrypoint for all mock requests, it will use the corresponding config to generate a response.
    *   The application will check database for the configuration that matches the url.
    *   You can use any HTTP method (`GET`, `POST`, `PUT`, `DELETE`, `PATCH`).
    *   The response and response code is derived from the configuration.
    *  Example response
          * `GET http://localhost:8085/api/test2334`
          * Example response `"appini akhil"`
    *   Returns the configured response with the configured status code and content type.
    *   Note the port is 8085.


## Technologies Used

-   Spring Boot
-   Spring Data JPA
-   PostgreSQL
-   Lombok
-   Log4j2
-   Java Faker
-   JJWT
-   Gradle
