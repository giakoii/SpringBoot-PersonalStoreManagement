## 🛒 Personal Store Management Project
## 🌟 Overview
This project is a personal store management system built using modern Java technologies. It provides APIs for various store operations, including:

- 🔐 User authentication using JWT
- 🛍️ Product management (CRUD operations)
- 📦 Order processing and tracking
## Technologies Used
- Java
- Spring Boot
- Spring security
- Maven
- JPA/Hibernate
- JWT for authentication
- IntelliJ IDEA 2024.1

## Project Structure
src  
├── main  
│   ├── java  
│   │   └── project  
│   │       └── personal  
│   │           └── personalstoremanagementproject  
│   │               ├── controllers           # REST APIs  
│   │               │   ├── v1                # Version 1 APIs  
│   │               │       ├── AbstractApiController  
│   │               │       ├── AbstractApiRequest  
│   │               │       └── AbstractApiResponse  
│   │               ├── common                # Common utilities and shared logic  
│   │               ├── configuration         # Application configurations  
│   │               ├── entities             # Database models  
│   │               ├── exceptions           # Custom exception handling  
│   │               ├── repositories         # Data access layer  
│   │               ├── services             # Business logic  
│   │               └── utils                # Utility classes  
│   └── resources  
│       ├── application.properties           # Application configuration file  
│       └── constantCsv                      # CSV files for constants  
│  
└── test  
└── java  
└── project  
└── personal  
└── personalstoremanagementproject

<h2>
     Architecture of the project
</h2>
<img src="assets/main_architecture_project.png">

## 🌟 Features
### 🛠️ Core Features
1. User Management: Register, login, and manage user profiles.
2. Product Management:
Add, update, delete, and view products.
3. Order Processing:
Create and manage orders, track status.
4. Security:
Authentication using JWT.
Role-based access control (e.g., Admin, Customer).
### 🚀 Planned Features
📊 Generate sales reports.<br>
📈 Dashboard with analytics.<br>
🌐 Multi-language support.

## 🚀 Getting Started

