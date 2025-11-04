#  Gestion des Stations-Service

Application web complète de gestion des stations-service et des prix de carburants, développée avec Spring Boot et Angular.

## Description

Cette application permet de gérer efficacement :
- Les stations-service (CRUD complet)
- Les types de carburants disponibles
- Les prix journaliers des carburants par station

## Architecture

### Backend - Spring Boot REST API
- **Port** : `8080`
- **Technologies** : Spring Boot, Spring Data JPA, MySQL, Maven
- **Architecture** : Couches (Entity, Repository, Service, Controller)

### Frontend - Angular
- **Port** : `4200`
- **Technologies** : Angular 17+, Bootstrap 5, TypeScript
- **Emplacement** : `/frontend` (intégré dans le projet backend)

### Base de données - MySQL
- **Port** : `3306`
- **Nom de la base** : `gestion_stations`

## 🚀 Installation et Démarrage

### Prérequis
- Java 17+
- Node.js 18+
- MySQL 8+
- Maven 3+
- Angular CLI

### Configuration de la base de données
```sql
CREATE DATABASE gestion_stations;
```

Modifier `src/main/resources/application.properties` avec vos identifiants MySQL.

### Démarrage du Backend
```bash
# Cloner le projet
git clone https://github.com/votre-username/gestion-stations.git
cd gestion-stations

# Compiler et lancer
mvn clean install
mvn spring-boot:run
```

Le backend sera accessible sur **http://localhost:8080**

### Démarrage du Frontend
```bash
# Naviguer vers le dossier frontend
cd gestion-station-frontend

# Installer les dépendances
npm install

# Lancer l'application
ng serve
```

Le frontend sera accessible sur **http://localhost:4200**

## 📡 Endpoints API

### Stations
- `GET /api/stations` - Liste toutes les stations
- `POST /api/stations` - Créer une station
- `PUT /api/stations/{id}` - Modifier une station
- `DELETE /api/stations/{id}` - Supprimer une station

### Carburants
- `GET /api/carburants` - Liste tous les carburants
- `POST /api/carburants` - Créer un carburant
- `PUT /api/carburants/{id}` - Modifier un carburant
- `DELETE /api/carburants/{id}` - Supprimer un carburant

### Prix Journaliers
- `GET /api/prix` - Liste tous les prix
- `POST /api/prix/station/{stationId}/carburant/{carburantId}` - Créer un prix
- `GET /api/prix/station/{stationId}` - Prix par station
- `PUT /api/prix/{id}` - Modifier un prix
- `DELETE /api/prix/{id}` - Supprimer un prix

## 🗂️ Structure du Projet
```
gestion-stations/
├── src/main/java/           # Code source Backend
│   ├── entities/            # Entités JPA
│   ├── repositories/        # Repositories
│   ├── services/            # Services métier
│   └── controllers/         # Controllers REST
├── src/main/resources/      # Configuration Backend
│   └── application.properties
├── gestion-station-frontend/                # Application Angular
│   ├── src/app/
│   │   ├── components/      # Composants Angular
│   │   ├── services/        # Services HTTP
│   │   └── models/          # Modèles TypeScript
│   └── package.json
└── pom.xml                  # Configuration Maven
```

## ✨ Fonctionnalités

- ✅ CRUD complet pour les stations-service
- ✅ Gestion des types de carburants
- ✅ Suivi des prix journaliers
- ✅ Recherche et filtrage avancés
- ✅ Interface utilisateur moderne et responsive
- ✅ Validation des données côté client et serveur

## 🛠️ Outils de Développement

- **IDE** : IntelliJ IDEA
- **Tests API** : Postman
- **Base de données** : MySQL 

