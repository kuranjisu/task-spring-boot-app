# Task Spring Application
This is an exercise on the use of Spring Boot JPA and Postgres Database

# Convert the Application to JAR File
  Run this command in the current directory
  `./mvnw clean package -DskipTests`
  
# Docker Folder
Using the command below copy the jar file from target folder to src\main\docker using the command:
`cp target/spring-app-0.0.1-SNAPSHOT.jar docker`

# Build
Change directory to docker directory
`cd docker`

Then, build
`docker build -t task .`

# Run 
Run the Application
`docker compose up`

Then, go to:
`http://localhost:8082/`
