# API Société

API REST développée en **Java avec Spring Boot**.

Le projet met en place une architecture backend permettant de gérer une API sécurisée avec authentification JWT et persistance des données dans une base MySQL.

## Technologies utilisées

* Java 21
* Spring Boot
* Spring Web
* Spring Security
* Spring Data JPA
* OAuth2 Resource Server
* JWT
* MySQL
* Maven
* Springdoc OpenAPI / Swagger
* Docker / Docker Compose

## Architecture

Le projet suit une architecture classique Spring Boot :

```text
src/main/java/fr/nathan/api/
├── config/
├── controller/
├── dto/
├── entity/
├── repository/
├── service/
└── ApiApplication.java
```

### `controller`

Contient les contrôleurs REST.

Ils reçoivent les requêtes HTTP et appellent les services nécessaires.

Exemple :

```text
POST /v1/api/auth/...
GET  /api/health
```

### `service`

Contient la logique métier de l'application.

Cette couche permet notamment de séparer le fonctionnement interne de l'application des contrôleurs HTTP.

Elle contient également la gestion des JWT.

### `repository`

Contient les interfaces Spring Data JPA permettant de communiquer avec la base de données.

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
MySQL
```

### `entity`

Contient les entités JPA représentant les données enregistrées dans MySQL.

### `dto`

Contient les DTO (*Data Transfer Objects*).

Ils permettent de définir précisément les données reçues et envoyées par l'API sans exposer directement les entités de la base de données.

### `config`

Contient la configuration de l'application, notamment la sécurité.

La classe `SecurityConfig` configure :

* Spring Security ;
* l'authentification JWT ;
* la signature des JWT en `HS256` ;
* les routes publiques ;
* les routes nécessitant une authentification.

## Authentification

L'API utilise des **JSON Web Tokens (JWT)**.

Le fonctionnement général est :

```text
Utilisateur
    │
    │ Identifiants
    ▼
API d'authentification
    │
    │ JWT
    ▼
Utilisateur
    │
    │ Authorization: Bearer <token>
    ▼
Routes protégées
```

La clé utilisée pour signer les JWT est fournie avec la variable d'environnement :

```text
JWT_SECRET
```

L'issuer utilisé par l'application est :

```text
societe.api
```

## Sécurité

Certaines routes sont accessibles sans authentification, notamment :

```text
/api/health
/v1/api/auth/**
/swagger-ui/**
/v3/api-docs/**
```

Les autres routes nécessitent un JWT valide.

Les requêtes utilisent l'en-tête HTTP :

```http
Authorization: Bearer <JWT>
```

## Base de données

L'application utilise **MySQL** avec Spring Data JPA et Hibernate.

La connexion est configurée à l'aide des variables :

```text
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
```

Hibernate est utilisé pour faire le lien entre les objets Java et les données présentes dans MySQL.

## Documentation de l'API

La documentation OpenAPI est générée automatiquement avec Springdoc.

Swagger UI est disponible sur :

```text
http://127.0.0.1:8080/swagger-ui.html
```

La spécification OpenAPI JSON est disponible sur :

```text
http://127.0.0.1:8080/v3/api-docs
```

## Compilation

Le projet utilise le Maven Wrapper.

Sous Linux :

```bash
./mvnw clean package
```

Sous Windows :

```powershell
.\mvnw.cmd clean package
```

Pour compiler sans lancer les tests :

```bash
./mvnw clean package -DskipTests
```

## Docker

L'application et MySQL peuvent également être lancés avec Docker Compose :

```bash
docker compose up -d --build
```

Les deux services utilisés sont :

```text
api-societe
mysql-societe
```

## Objectif du projet

Ce projet permet de mettre en pratique plusieurs concepts importants du développement backend Java :

* création d'une API REST ;
* architecture en couches ;
* utilisation de DTO ;
* accès aux données avec JPA ;
* authentification JWT ;
* sécurisation des routes avec Spring Security ;
* documentation avec Swagger/OpenAPI ;
* utilisation de MySQL ;
* conteneurisation avec Docker.
