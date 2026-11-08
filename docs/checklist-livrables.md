# Checklist de conformité à l’énoncé

| Exigence | Fichier / emplacement | État |
|---|---|---|
| Q1 suivi des demandes et labels | `docs/suivi-taches.md` | Registre local préparé; Issues distantes non créées |
| Q1 workflow Git dans README | `README.md` | Documenté; dépôt Git local |
| Q2 analyse du domaine | `docs/01-cadrage-et-analyse.md`, `docs/02-architecture-et-choix.md` | Présent |
| Q2 schéma PDF | `bdd/schema base de données.pdf` | Présent |
| Q2 SQL structure et données d’essai | `bdd/script.sql` | Présent |
| Q3 backend Java Spring Boot | `src/backend/` | Code présent; exécution non validée dans cet environnement |
| Q3 frontend Angular | `src/frontend/` | Code présent; création/mise à jour client et filtres date/statut/client; exécution non validée ici |
| Q4 tests backend et frontend | `src/backend/src/test/`, `src/frontend/src/app/` | Sources de tests présentes; non lancées ici |
| Q4 rapports frontend/backend | `couverture/` | Guides d’emplacement; rapports réels à générer en CI, aucun pourcentage simulé |
| Q5 pipeline build, tests et couverture téléchargeable | `.github/workflows/ci.yml` | Configuration présente; dépend des secrets et du runner GitHub |
| Q5 images DockerHub | workflow CI et Dockerfiles | Publication sur tag, nécessite secrets Docker Hub |
| Q5 deux Dockerfiles et Compose | `src/backend/Dockerfile`, `src/frontend/Dockerfile`, `docker-compose.yml` | Présents |
| Q5 procédure de déploiement | `docs/deploiement.md` et README | Présente |

L’énoncé ne demande pas de contrat juridique, de conception UX formelle ou de portfolio/soutenance pour cette étude de cas. Aucun livrable de ces catégories n’est revendiqué comme exigence ShopWise.
