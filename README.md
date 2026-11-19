# Bharat Hardware Mart – HardwareShop

| Folder | Port | What it is |
|--------|------|-----------|
| `HardwareShopApi/` | 8085 | REST API with MySQL, validation, Basic auth, CORS |
| `HardwareShopPage/` | 8086 | Web page that lists the products using `fetch` |

Java 17 and Maven are needed. MySQL must be running.

## Setup
1. In MySQL: `CREATE DATABASE hardware_db;`
2. Edit `spring.datasource.username` and `spring.datasource.password` in `HardwareShopApi/src/main/resources/application.properties` to match your MySQL.
3. Run the API: `mvn spring-boot:run` in `HardwareShopApi` (or `mvnw spring-boot:run` if you have the wrapper from Spring Initializr). Open http://localhost:8085/
4. Run the page: `mvn spring-boot:run` in `HardwareShopPage`, then open http://localhost:8086/

## Executable jar (Session 1, task 3)
```
cd HardwareShopApi
mvn package
java -jar target/HardwareShopApi-0.0.1-SNAPSHOT.jar --server.port=9090
```
Then open http://localhost:9090/

## API
| Method | URL | Login | Result |
|--------|-----|-------|--------|
| GET | `/api/products` | no | 200, list |
| GET | `/api/products/{id}` | no | 200, or 404 |
| POST | `/api/products` | shopadmin | 201, or 400 if invalid |
| PUT | `/api/products/{id}` | shopadmin | 200, 400, or 404 |
| DELETE | `/api/products/{id}` | shopadmin | 204, or 404 |
| PUT | `/api/products/{id}/sell/{quantity}` | shopadmin | 200, 404 unknown id, 409 not enough stock |

Login: user `shopadmin`, password `Shop@123` (practice-only, in `application.properties`).

CORS: only `http://localhost:8086` may read products with GET.

## Postman
Import `HardwareShopApi/HardwareShopApi.postman_collection.json` and run it with the Collection Runner (7 tests). The API must be running.

## Test log files
`session3_test_log.txt` and `session4_tests.txt` list the requests and expected status codes. Fill in the actual results after you run them.
