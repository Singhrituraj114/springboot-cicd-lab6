# Lab 6 - CI/CD Pipeline Project

This is a sample Spring Boot application for the CI/CD pipeline demonstration.

## Project Structure

```
sample-app/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/springbootcicd/
│   │   │       ├── SpringBootCicdApplication.java
│   │   │       └── controller/
│   │   │           └── AppController.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/example/springbootcicd/
│               ├── SpringBootCicdApplicationTests.java
│               └── controller/
│                   └── AppControllerTests.java
```

## How to Use This Sample Application

### Option 1: Create Your Own GitHub Repository

1. Create a new repository on GitHub
2. Initialize it with the sample app files:
   ```bash
   cd sample-app
   git init
   git add .
   git commit -m "Initial commit"
   git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git
   git push -u origin main
   ```

### Option 2: Use an Existing Spring Boot Repository

If you already have a Spring Boot application:
- Ensure it has a `pom.xml` file
- Ensure it includes Spring Boot Actuator dependency
- The application should expose a health endpoint at `/actuator/health`

## API Endpoints

Once deployed, the application exposes these endpoints:

- `GET /api/` - Home endpoint with application info
- `GET /api/hello` - Simple hello message
- `GET /api/info` - Detailed application information
- `GET /actuator/health` - Health check endpoint

## Running Locally

```bash
# Build the application
mvn clean package

# Run the application
java -jar target/springboot-cicd-app.jar

# Or use Maven
mvn spring-boot:run
```

Access at: http://localhost:8080

## Important Notes

1. Update the Jenkinsfile with your actual Docker Hub username
2. Update the Kubernetes deployment.yml with your Docker Hub username
3. Update the terraform.tfvars with your GitHub repository URL
4. Ensure your repository is public or configure GitHub credentials in Jenkins
