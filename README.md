# Todo API (Spring Boot, H2, MapStruct)

Layered architecture: Controller → Service → Repository. DTO mapping via MapStruct.  
H2 in-memory DB with console at `/h2-console`.

## Build & Run
```bash
mvn spring-boot:run
# or build jar
mvn clean package && java -jar target/todo-api-0.0.1-SNAPSHOT.jar
```
## API
- Users: `/api/users`
- Tasks: `/api/tasks`

Supports `GET, POST, PUT, PATCH, DELETE`. See controllers for request/response DTOs.
