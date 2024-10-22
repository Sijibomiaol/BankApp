# Bank Application

A Java-based banking application that allows users to manage their accounts, perform transactions, and generate bank statements. The application utilizes Spring Boot for the backend, JWT for authentication, and follows RESTful API principles.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [API Documentation](#api-documentation)
- [Directory Structure](#directory-structure)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- User account creation and login
- Balance enquiry
- Credit and debit transactions
- Fund transfer between accounts
- Generate and download bank statements in PDF format

## Technologies Used

- **Java**: Programming language used to develop the application
- **Spring Boot**: Framework for building the RESTful web service
- **Spring Security**: For handling authentication and authorization
- **JWT (JSON Web Token)**: For secure token-based authentication
- **Maven**: Build and dependency management
- **MySQL/PostgreSQL**: Database management (depending on your configuration)
- **iText PDF**: Library for generating PDF documents

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Sijibomiaol/BankApp.git
   cd BankApp


API Documentation
Endpoints
1. Create New User Account
POST /api/customer
Request Body: CustomerRequest
Response: BankResponse
2. User Login
POST /api/customer/login
Request Body: LoginDto
Response: BankResponse
3. Balance Enquiry
GET /api/customer/balanceEnquiry
Request Body: EnquiryRequest
Response: BankResponse
4. Name Enquiry
GET /api/customer/nameEnquiry
Request Body: EnquiryRequest
Response: BankResponse
5. Credit Account
POST /api/customer/credit
Request Body: CreditDebitRequest
Response: BankResponse
6. Debit Account
POST /api/customer/debit
Request Body: CreditDebitRequest
Response: BankResponse
7. Fund Transfer
POST /api/customer/transfer
Request Body: TransferRequest
Response: BankResponse
8. Generate Bank Statement
GET /api/customer/bankStatement
Request Params: accountNumber, startDate, endDate
Response: BankStatementResponse
DTOs
AccountInfo
BankResponse
BankStatementResponse
CreditDebitRequest
CustomerRequest
EmailDetails
EnquiryRequest
LoginDto
TransactionDto
TransferRequest
Repositories
CustomerRepository
TransactionRepository
Services
BankStatement
CustomerServiceImpl
CustomUserDetails
EmailServiceImpl
TransactionServiceImpl
Configuration
JWTAuthenticationEntryPoint
JWTAuthenticationFilter
JwtTokenProvider
Entities
Customer
Transaction
Enums
Role

Directory Structure

├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── sijibomiaol
│   │   │           └── the_bank
│   │   │               ├── config
│   │   │               ├── controller
│   │   │               ├── dto
│   │   │               ├── entity
│   │   │               ├── enum
│   │   │               ├── repository
│   │   │               └── service
│   │   └── resources
│   │       └── application.properties
└── pom.xml

Usage
Send requests to the appropriate endpoints using tools like Postman or cURL.
Ensure to include a JWT token in the Authorization header for protected endpoints.
Contributing
Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.
