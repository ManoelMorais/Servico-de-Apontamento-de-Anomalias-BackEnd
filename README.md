# Anomaly Reporting Service — Backend

System developed as the final project of the **Electrical Distribution Network Technician Course at SENAI/SE** (Brazil's National Industrial Learning Service), in partnership with electrical field professionals from Sergipe state.

The goal is to digitize and standardize the process of registering anomalies in electrical distribution networks, replacing manual methods (WhatsApp, spreadsheets, paper) with a centralized, traceable, data-driven workflow.

---

## 🚀 Features

- **Field anomaly registration** with geolocation (latitude/longitude)
- **Photo and video upload** as evidence of each occurrence
- **Classification by type and criticality** (Critical, High, Medium, Low)
- **Approval workflow** with automatic routing to responsible supervisor/manager
- **Analytical dashboards** for management oversight
- **Complete history** of anomalies by region and type
- **User authentication** with role-based access control (field, supervisor, management)
- **Automatic notifications** triggered by status progression
- **REST API** for integration with the Angular frontend

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3 |
| Persistence | Spring Data JPA / Hibernate |
| Database | PostgreSQL |
| Security | Spring Security + JWT |
| Build | Maven |
| Deploy | Koyeb |

---

## 📐 Architecture

```
src/
├── controller/       # REST endpoints
├── service/          # Business logic
├── repository/       # Database access via JPA
├── model/            # Database entities
├── dto/              # Data Transfer Objects
├── security/         # JWT configuration and authentication
└── config/           # General application configuration
```

---

## 🗄️ Main Entities

| Entity | Description |
|---|---|
| `Usuario` | Registered collaborators with access profiles |
| `Anomalia` | Full occurrence record with type, criticality and location |
| `Foto` | Images attached to each anomaly |
| `Localizacao` | GPS coordinates of the occurrence |
| `StatusHistorico` | History of status changes for each anomaly |

---

## ⚙️ Running Locally

### Prerequisites
- Java 17+
- Maven 3.8+
- PostgreSQL 14+

### Setup

1. Clone the repository:
```bash
git clone https://github.com/manoelalmorais/Servico-de-Apontamento-de-Anomalias-BackEnd.git
cd Servico-de-Apontamento-de-Anomalias-BackEnd
```

2. Configure environment variables in `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/anomalias_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
jwt.secret=YOUR_JWT_SECRET
```

3. Run:
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

---

## 🌐 Deployment

The application is hosted in production on **Koyeb**.

> Frontend repository: [Servico-de-Apontamento-de-Anomalias-FrontEnd](https://github.com/manoelalmorais/Servico-de-Apontamento-de-Anomalias-FrontEnd)

---

## 📊 Project Background

This system was built based on field research conducted with electrical sector professionals in Sergipe (technicians, supervisors, engineers and maintenance electricians), which found:

- **100%** of respondents considered the current process inefficient
- **60%** reported anomalies via WhatsApp or informal messages
- **100%** agreed on the need for a dedicated internal system
- **80%** encountered anomalies daily or weekly

The proposed solution was priced at **R$ 32,760.00** and presented with full technical, economic and market feasibility analysis (SWOT + Porter's Five Forces).

---

## 👨‍💻 Developer

**Manoel Almeida de Morais**
Backend Java Developer | Power Platform | Aracaju, Brazil

- 📧 manoelalmorais@gmail.com
- 💼 [LinkedIn](https://linkedin.com/in/manoelalmorais)
- 🐙 [GitHub](https://github.com/manoelalmorais)
