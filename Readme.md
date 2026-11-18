# API Société

API REST développée en **Java avec Spring Boot**, permettant de consulter, modifier et supprimer les données d'entreprises enregistrées dans une base MySQL.

L'accès aux données est sécurisé avec OAuth2 et des JWT signés asymétriquement par Keycloak.

## Technologies utilisées

- Java 21
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA / Hibernate
- OAuth2 Resource Server
- Keycloak
- JWT / RS256
- MySQL
- PostgreSQL
- Python
- Maven
- Springdoc OpenAPI / Swagger
- Docker / Docker Compose

## Architecture

Le projet suit une architecture en couches :

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

| Répertoire | Responsabilité |
|---|---|
| `config` | Configuration et sécurité de l'application |
| `controller` | Gestion des requêtes HTTP |
| `dto` | Objets de transfert de données |
| `entity` | Entités JPA |
| `repository` | Accès à la base de données |
| `service` | Logique métier |

## Authentification

L'authentification est déléguée à Keycloak via OAuth2, en utilisant le flux **Client Credentials**.

```text
Client
   │
   │ Client ID + Client Secret
   ▼
Keycloak
   │
   │ JWT signé en RS256
   ▼
Client
   │
   │ Authorization: Bearer <JWT>
   ▼
API REST
   │
   │ Validation du JWT
   ▼
MySQL
```

Keycloak signe les JWT avec une clé privée RSA. L'API vérifie leur signature à l'aide de la clé publique récupérée auprès de Keycloak.

### Configuration Keycloak

| Paramètre | Valeur |
|---|---|
| Realm | `societe` |
| Client ID | `societe-api` |
| Flux OAuth2 | Client Credentials |
| Algorithme de signature | RS256 |
| Audience | `societe-api` |
| Durée de validité du JWT | 5 minutes |
| Issuer local | `http://localhost:8081/realms/societe` |

### Obtention d'un JWT

```bash
TOKEN=$(curl -fsS -X POST \
  http://localhost:8081/realms/societe/protocol/openid-connect/token \
  -u societe-api \
  -d "grant_type=client_credentials" \
  | jq -r '.access_token')
```

Le secret du client est demandé lors de l'exécution.

### Utilisation du JWT

```bash
curl -i \
  -H "Authorization: Bearer $TOKEN" \
  http://localhost:8080/v1/companies/1807254
```

## Sécurité

Spring Security vérifie :

- La signature du JWT ;
- L'émetteur ;
- L'audience ;
- La validité temporelle du jeton.

Les routes suivantes sont publiques :

```text
/health
/swagger-ui.html
/swagger-ui/**
/v3/api-docs/**
```

Les autres routes nécessitent un JWT valide.

### Tests réalisés

| Test | Résultat |
|---|---|
| Génération d'un JWT RS256 | Réussite |
| Requête sans JWT | HTTP 401 |
| Requête avec un JWT malformé | HTTP 401 |
| Requête avec un JWT expiré | HTTP 401 |
| Accès aux données avec un JWT valide | HTTP 200 |

## Base de données

Deux bases de données sont utilisées :

- **MySQL** : stockage des données des entreprises.
- **PostgreSQL** : stockage des données et de la configuration Keycloak.

Les données sont conservées dans des volumes Docker persistants.

Sur une nouvelle installation, les données métier doivent être importées dans MySQL.

## Documentation de l'API

- Swagger UI : http://localhost:8080/swagger-ui.html
- OpenAPI JSON : http://localhost:8080/v3/api-docs
- Console Keycloak : http://localhost:8081/admin

## Compilation

Sous Linux :

```bash
./mvnw clean package
```

Sous Windows :

```powershell
.\mvnw.cmd clean package
```

Sans exécuter les tests :

```bash
./mvnw clean package -DskipTests
```

## Docker

### Configuration

Copier le fichier `.env.example` :

```bash
cp .env.example .env
```

Renseigner les variables d'environnement dans le fichier `.env`.

Ce fichier contient les informations sensibles et ne doit pas être versionné dans Git.

### Démarrage

```bash
docker compose up -d --build
```

Les services utilisés sont :

| Conteneur | Fonction |
|---|---|
| `api-societe` | API REST Java |
| `mysql-societe` | Base de données métier |
| `keycloak-societe` | Serveur d'authentification OAuth2 |
| `keycloak-db-societe` | Base PostgreSQL de Keycloak |
| `analytics-societe` | Service d'analyse Python |

Le realm Keycloak est configuré pour être automatiquement importé lors d'une nouvelle installation depuis :

```text
keycloak/realms/societe-realm.json
```

### Arrêt

```bash
docker compose down
```

Les conteneurs ne démarrent pas automatiquement au démarrage de la machine.

## Déploiement réseau

La configuration actuelle est destinée au développement local.

Pour un déploiement sur le réseau St-Michel, les adresses d'accès doivent être adaptées et les communications sécurisées avec HTTPS.

## Objectif du projet

- Développer une API REST Java.
- Utiliser une architecture en couches.
- Accéder aux données avec JPA et MySQL.
- Sécuriser une API REST avec OAuth2 et Keycloak.
- Utiliser des JWT signés asymétriquement en RS256.
- Conteneuriser et déployer les services avec Docker Compose.