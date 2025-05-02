# Product Sorting Service

**REST API to sort products based on configurable criteria such as sales units and stock availability.**

---

## Technologies Used

- Java 21
- Spring Boot 3.4.5
- Spring Web
- Spring Data JPA
- H2 Database (in-memory)
- Lombok
- Swagger OpenAPI 3 (springdoc-openapi)
- Maven

---

## How to Run the Project

1. Clone this repository:

```bash
git clone https://github.com/leandroLupano/product-sorting-service.git
```

2. Import it into your preferred IDE as a Maven project.

3. Build the project:

```bash
mvn clean install
```

4. Run the application:

```bash
mvn spring-boot:run
```

5. The application will start at:

```
http://localhost:8080/api/actuator
```

---

## API Documentation

Swagger UI is available at:

```
http://localhost:8080/api/swagger-ui.html
```

You can explore all endpoints, see request/response examples, and test the API from there.

---

## Main Endpoints

### GET `/products/criteria`
Retrieves all available sorting criteria.

### POST `/products/sort`
Sorts the products based on the weights provided for each criterion.

**Example Request Body:**

```json
{
    "weights": {
        "sales units criterion": 0.7,
        "stock criterion": 0.3
    }
}
```

---

## Good Practices Applied

- Clear separation of layers (Controller, Service, Domain, DTOs, Entities, Repository).
- Centralized exception handling with `@ControllerAdvice`.
- Logging with SLF4J (`@Slf4j`).
- In-memory database initialization using `data.sql`.
- OpenAPI 3 documentation with Swagger UI.
- Extensible architecture for adding new scoring criteria.

---

## Additional Notes

- The database is reset on every application restart (H2 memory DB).
- To switch to PostgreSQL, uncomment the appropriate section in `application.yml`.
- Example data is preloaded automatically from `data.sql`.

---

## Author

Leandro Lupano


---

## Original Technical Test Description
> Given a list of products displayed in a T-shirt category, you are required to implement an algorithm that sorts the list based on a set of sorting criteria.  
> Each sorting criterion will have an associated weight, and the score of each product to be sorted will be determined by the weighted sum of the criteria.
>
> The defined sorting criteria are the sales units criterion and the stock ratio criterion, and new criteria may be added in the future.
>
> - The sales units criterion will assign a score to each product based on the number of units sold.
> - The stock ratio criterion will assign a score based on the sizes that currently have available stock.
>
> **Product list:**
>
> | id | name                                | sales_units | stock             |
> |----|-------------------------------------|-------------|-------------------|
> | 1  | V-NECH BASIC SHIRT                  | 100         | S: 4 / M:9 / L:0  |
> | 2  | CONTRASTING FABRIC T-SHIRT           | 50          | S: 35 / M:9 / L:9 |
> | 3  | RAISED PRINT T-SHIRT                 | 80          | S: 20 / M:2 / L:20|
> | 4  | PLEATED T-SHIRT                      | 3           | S: 25 / M:30 / L:10|
> | 5  | CONTRASTING LACE T-SHIRT             | 650         | S: 0 / M:1 / L:0  |
> | 6  | SLOGAN T-SHIRT                       | 20          | S: 9 / M:2 / L:5  |
>
> **Requirements:**
>
> - The functionality must be exposed through a REST service that receives the weights for each criterion.
> - The choice of programming language and frameworks is free.

---
