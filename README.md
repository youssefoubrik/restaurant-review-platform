# Restaurant Review- **Maven** + **Docker Compose**

## 🚀 Quick Start

### Prerequisitesform 🍽️

# Restaurant Review Platform 🍽️

A modern restaurant review platform built with Spring Boot, Elasticsearch, and OAuth2 security.

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Elasticsearch](https://img.shields.io/badge/Elasticsearch-8.12.0-yellow.svg)](https://www.elastic.co/)
[![License](https://img.shields.io/badge/License-Tutorial-blue.svg)](https://github.com/devtiro)

## ✨ Features

- 🏪 **Restaurant Management**: Full CRUD operations for restaurant listings
- 🔍 **Advanced Search**: Multi-criteria search (name, cuisine, location, rating)
- ⭐ **Review System**: Complete review and rating functionality
- 📸 **Photo Upload**: Image upload and management system
- 🔐 **Secure Authentication**: OAuth2 JWT via Keycloak integration
- 📍 **Geolocation Search**: Radius-based location filtering
- 🚀 **High Performance**: Elasticsearch-powered search engine

## 🛠️ Tech Stack

| Category           | Technology                                  |
| ------------------ | ------------------------------------------- |
| **Backend**        | Java 21, Spring Boot 3.5.4, Spring Security |
| **Database**       | Elasticsearch 8.12.0                        |
| **Authentication** | Keycloak 23.0 (OAuth2 JWT)                  |
| **Monitoring**     | Kibana 8.12.0                               |
| **Build Tools**    | Maven, Docker Compose                       |
| **Code Quality**   | Lombok, MapStruct, JUnit 5                  |

## 🚀 Quick Start

### Prerequisites

- ☕ Java 21+
- 🐳 Docker & Docker Compose
- 📦 Maven 3.6+
- 🔧 Git

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/youssefoubrik/restaurant-review-platform.git
cd restaurant-review-platform

# 2. Start infrastructure services
docker-compose up -d

# 3. Configure Keycloak (http://localhost:9090)
# Login: admin/admin → Create realm 'restaurant-review'

# 4. Run the application
./mvnw spring-boot:run
```

🎉 **Application ready at**: http://localhost:8080

### Service URLs

- **API**: http://localhost:8080/api/restaurants
- **Elasticsearch**: http://localhost:9200
- **Kibana**: http://localhost:5601
- **Keycloak**: http://localhost:9090

## 📚 API Endpoints

## � Quick Start

### Prerequisites

- Java 21+, Docker, Maven

### Setup

```bash
# Clone the repository
git clone https://github.com/youssefoubrik/restaurant-review-platform.git
cd restaurant-review-platform

# Start infrastructure (Elasticsearch, Kibana, Keycloak)
docker-compose up -d

# Configure Keycloak
# 1. Go to http://localhost:9090 (admin/admin)
# 2. Create realm 'restaurant-review'

# Run application
./mvnw spring-boot:run
```

Application runs on http://localhost:8080

## 📚 API Endpoints

### 🏪 Restaurants

| Method   | Endpoint                | Description                     |
| -------- | ----------------------- | ------------------------------- |
| `GET`    | `/api/restaurants`      | Search restaurants with filters |
| `POST`   | `/api/restaurants`      | Create new restaurant           |
| `GET`    | `/api/restaurants/{id}` | Get restaurant details          |
| `PUT`    | `/api/restaurants/{id}` | Update restaurant               |
| `DELETE` | `/api/restaurants/{id}` | Delete restaurant               |

### ⭐ Reviews

| Method   | Endpoint                                             | Description       |
| -------- | ---------------------------------------------------- | ----------------- |
| `GET`    | `/api/restaurants/{restaurantId}/reviews`            | List all reviews  |
| `POST`   | `/api/restaurants/{restaurantId}/reviews`            | Create new review |
| `PUT`    | `/api/restaurants/{restaurantId}/reviews/{reviewId}` | Update review     |
| `DELETE` | `/api/restaurants/{restaurantId}/reviews/{reviewId}` | Delete review     |

### 📸 Photos

| Method | Endpoint                | Description             |
| ------ | ----------------------- | ----------------------- |
| `POST` | `/api/photos`           | Upload restaurant photo |
| `GET`  | `/api/photos/{photoId}` | Retrieve photo          |

### 🔍 Search Example

```http
GET /api/restaurants?q=pizza&minRating=4.0&latitude=40.7128&longitude=-74.0060&radius=5&page=1&size=10
```

**Search Parameters:**

- `q` - Search query (name, cuisine type)
- `minRating` - Minimum rating filter (0-5)
- `latitude`, `longitude`, `radius` - Geolocation search
- `page`, `size` - Pagination controls

## 📁 Project Architecture

```
src/main/java/com/oubrik/restaurant/
├── 🚀 RestaurantApplication.java         # Spring Boot Main Class
├── 🎮 controllers/                       # REST API Controllers
│   ├── RestaurantController.java         # Restaurant CRUD operations
│   ├── ReviewController.java             # Review management
│   ├── PhotoController.java              # Photo upload/retrieval
│   └── ErrorController.java              # Global exception handler
├── 💼 services/                          # Business Logic Layer
│   └── impl/                            # Service implementations
├── 🗃️ domain/
│   ├── entities/                        # Elasticsearch Documents
│   ├── repositories/                    # Data Access Layer
│   └── dtos/                           # API Data Transfer Objects
├── 🔄 mappers/                          # MapStruct Entity↔DTO Mappers
└── ❌ exceptions/                       # Custom Exception Classes
```

## 📺 Tutorial Reference

This project is based on the comprehensive **Devtiro YouTube Tutorial**:  
**"Build a Restaurant Review Platform with Spring Boot - Full Build - Intermediate Project"**

<div align="center">

**Built with ❤️ following the Devtiro tutorial series**

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Elasticsearch](https://img.shields.io/badge/Elasticsearch-005571?style=for-the-badge&logo=elasticsearch&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)

</div>
