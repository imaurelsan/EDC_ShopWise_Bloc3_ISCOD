# Registre des tâches (à reporter dans GitHub Issues)

Les Issues distantes n’ont pas été créées : aucun dépôt ou jeton GitHub n’a été fourni. Le tableau fournit des tâches, labels et statuts initiaux à créer dans l’onglet Issues. Le workflow de pull request est décrit dans le README.

| Issue | Sujet | Labels proposés | Statut local |
|---|---|---|---|
| #1 | Créer et mettre à jour les clients | `feature`, `backend`, `frontend` | Implémenté dans le prototype |
| #2 | Créer des rendez-vous liés à un client | `feature`, `backend` | Implémenté dans le prototype |
| #3 | Filtrer la liste des rendez-vous | `feature`, `backend`, `frontend` | Implémenté dans le prototype |
| #4 | Modifier l’état d’un rendez-vous | `feature`, `backend`, `frontend` | Implémenté dans le prototype |
| #5 | Créditer les points une seule fois | `feature`, `backend`, `business-rule` | Implémenté dans le prototype |
| #6 | Consulter la fiche client | `feature`, `api` | Implémenté (pas d’authentification) |
| #7 | Consulter solde et transactions | `feature`, `backend`, `frontend` | Implémenté dans le prototype |
| #8 | Produire rapports de couverture et publier les images | `ci`, `quality` | Workflow configuré, publication sur tag |
| #9 | Préparer Docker Compose et Helm | `deployment`, `infra` | Configurations fournies, non exécutées ici |
| #10 | Ajouter contrôle d’accès et stratégie RGPD production | `security`, `backlog` | À faire avant mise en production |

À chaque fusion réelle, créer une branche issue, rattacher un commit par `(#n)` et fermer l’issue par la PR. Les statuts ci-dessus décrivent seulement le dépôt local.
