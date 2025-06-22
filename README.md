# TrainUp Server

**TrainUp** — це серверна частина веб-застосунку для взаємодії тренерів та клієнтів у фітнес-сфері. Система забезпечує зручне керування тренуваннями, комунікацію, відстеження прогресу та планування за допомогою календаря.

## Технології

- **Java 24**
- **Spring Boot**
- **Spring Security + JWT**
- **Spring Data JPA**
- **PostgreSQL**
- **RESTful API**
- **Lombok**

## Функціонал

- Реєстрація та авторизація з використанням JWT
- Планування та керування тренуваннями (CRUD)
- Реалізація чату між тренером і клієнтом
- Відстеження фізичного прогресу користувачів
- Інтеграція з календарем тренувань
- Призначення клієнтів тренерам
- Захищені REST API-ендпоїнти

## Структура проєкту
src/
├── main/
│ ├── java/com/trainup/
│ │ ├── controller/ // REST-контролери
│ │ ├── dto/ // DTO-об’єкти
│ │ ├── entity/ // JPA-сутності
│ │ ├── repository/ // Інтерфейси до БД
│ │ ├── service/ // Бізнес-логіка
│ │ ├── config/ // Конфігурація безпеки та JWT
│ │ └── TrainUpApplication.java
│ └── resources/
│ └── application.properties

## ▶️ Запуск проєкту локально

### 1. Клонуйте репозиторій:
   ```bash
   git clone https://github.com/ykarpa/TrainUpServer.git
   cd TrainUpServer
   ```

### 2. Налаштуйте БД PostgreSQL (наприклад через Docker або локально):

   Оберіть один із варіантів:

#### 🔹 Через Docker (рекомендовано)

```bash
docker run --name trainup-postgres \
  -e POSTGRES_DB=trainup \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=your_password \
  -p 5432:5432 \
  -d postgres:15
```

#### 🔹 Локально (без Docker)

```sql
CREATE DATABASE trainup;
CREATE USER trainup_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE trainup TO trainup_user;
```

### 3. Заповніть `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/trainup
spring.datasource.username=postgres
spring.datasource.password=your_password
```

### 4. Зберіть і запустіть застосунок

#### За допомогою Maven:

```bash
./mvnw spring-boot:run
```

#### Або збірка JAR-файлу:

```bash
./mvnw clean package
java -jar target/trainup-server-0.0.1-SNAPSHOT.jar
```

## Деплой

## Додатково
Клієнтська частина: [TrainUp](https://github.com/ykarpa/TrainUpUI)  
