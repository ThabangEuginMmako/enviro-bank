# Enviro-Bank Data Centre Division

This Spring Boot application is a file parser that processes a CSV file containing account profile data. It stores the records in an in-memory H2 database, converts base64 image data to physical image files, and generates HTTP links for accessing the images. The application exposes a REST API for retrieving the image files based on account holder name and surname.

## Features

- Parses a CSV file containing account profile data
- Stores the records in an in-memory H2 database
- Converts base64 image data to physical image files
- Generates HTTP links for accessing the image files
- Provides a REST API for retrieving the image files by account holder name and surname

## Prerequisites

- Java 8 or higher
- Maven

## Getting Started

1. Clone the repository:

   ```shell
   git clone https://github.com/yourname/your-repo.git

2. Navigate to the project directory:
    ```shell
   cd your-repo

3. Build the project using Maven:
    ```shell
   mvn clean install
   
4. Run the application:
    ```shell
   mvn spring-boot:run

Access the generated image files through the HTTP image link endpoint. For example, if your server is running at http://localhost:8080, and you have an image file with the name "image123.png" associated with a profile, you can access it at http://localhost:8080/v1/api/image/{name}/{surname}/image123.png, where {name} and {surname} are the respective values of the profile.