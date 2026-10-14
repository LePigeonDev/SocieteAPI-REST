# API analytique Société

API Flask indépendante de l'API Java, utilisant Pandas, SQLAlchemy et MySQL.

## Organisation

- `app/routes/` : points d'entrée HTTP.
- `app/services/` : calculs et agrégations statistiques.
- `app/database/` : connexion MySQL mutualisée.
- `app/config.py` : configuration par variables d'environnement.

## Démarrage

Le fichier `api/.env` existant doit contenir `MYSQL_DATABASE`, `MYSQL_USER` et `MYSQL_PASSWORD`.

Depuis le dossier `api/` :

```sh
docker compose -f compose.yaml -f compose.analytics.yaml up -d --build analytics
```

Remarque : si le service MySQL n'est pas démarré, démarrer la pile actuelle normalement au préalable.

## API

- `GET /analytics/health` : teste la connexion MySQL.
- `GET /analytics/stats/tables` : liste les tables disponibles.
- `GET /analytics/stats/NOM_TABLE/count` : nombre exact de lignes.
- `GET /analytics/stats/NOM_TABLE/group/NOM_COLONNE?limit=20` : effectifs par valeur, limités à 100 catégories.

La route groupée est agrégée par MySQL, puis transformée avec Pandas pour éviter de charger toute la table en mémoire.

## Tests

```sh
cd analytics
python -m pip install -r requirements.txt
python -m pytest -q
```

## Sécurité

Les routes sont des exemples de développement et ne doivent pas être exposées publiquement via Traefik sans contrôle d'accès. L'utilisateur MySQL devrait avoir uniquement SELECT sur les tables nécessaires.
