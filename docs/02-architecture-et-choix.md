# Architecture et choix de conception

## Vue d’ensemble

Interface Angular responsive → API REST Spring Boot → PostgreSQL. L’API sépare les contrôleurs, la logique métier, les entités JPA et les repositories. Ce découpage reste volontairement lisible pour un prototype pédagogique. Il n’y a ni broker ni microservices : le volume et les besoins du scénario ne justifient pas cette complexité.

## Modèle relationnel

`shop` 1—N `customer`; `customer` 1—N `appointment`; `customer` 1—N `loyalty_transaction`; `appointment` 1—0..N `loyalty_transaction`, avec unicité de `appointment_id` dans le cas d’une récompense. Le PDF `bdd/schema base de données.pdf` présente les colonnes et liens.

## API externe

Le contrat minimal est documenté dans `docs/api.md`. Les dates utilisent `YYYY-MM-DD` et les statuts `PLANNED`, `HONORED`, `CANCELLED`. Pour garder le prototype simple, les erreurs métier et validations renvoient des réponses HTTP standard Spring.

## Sécurité et données

Données d’essai inventées. La mise en production exige authentification, droits par commerce, limitation d’accès aux informations personnelles, TLS, journalisation sans donnée sensible, stratégie d’effacement/export, sauvegardes, secrets hors dépôt et tests de sécurité. Ces mécanismes ne sont pas fournis, puisque l’énoncé exclut l’authentification avancée.
