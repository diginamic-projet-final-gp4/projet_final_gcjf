# Documentation de l'API

## Dossiers

- **controller** : contient les classes qui gèrent les requêtes HTTP
- **models** : contient les classes qui représentent les entités de la base de données
- **repository** : contient les classes qui gèrent les requêtes SQL
- **security** : contient les classes qui gèrent la sécurité de l'application
- **services** : contient les classes qui gèrent la logique métier

## Nommage des classes

- Les classes sont nommées en anglais et en CamelCase

### Exemple :

Pour la classe 'Utilisateur', on aura `User` suivi du type de classe (Controller, Service, Repository, Model)
Exemple : `UserController`
