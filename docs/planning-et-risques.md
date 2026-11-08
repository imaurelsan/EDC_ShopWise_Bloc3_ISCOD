# Plan de travail et risques

## Ordre de réalisation proposé

| Étape | Travail | Vérification de sortie |
|---|---|---|
| 1 | Clarifier les règles fidélité et statuts | User stories et cas limites acceptés |
| 2 | Modèle SQL, contraintes, jeu fictif | Schéma relu, script rejouable |
| 3 | API clients, rendez-vous, points | Tests unitaires et d’intégration |
| 4 | Écrans responsive et appels API | Parcours desktop/mobile testés |
| 5 | Docker, pipeline, documentation | CI verte, conteneurs démarrés |
| 6 | Retour commerçant et corrections | Issues closes et décision de version |

## Risques

Le risque principal est la fuite de coordonnées : l’API ouverte est strictement une contrainte du cas et non une décision de production. Autres risques : attribution répétée de points si l’idempotence est retirée, saisie de rendez-vous sur créneaux déjà occupés, indisponibilité de la base, dépendance à des identifiants Docker personnels dans CI et incompréhension du périmètre RGPD. Les mesures immédiates sont données fictives, unicité en base pour la récompense, tests, secrets GitHub et rappel explicite de la limite d’accès.

Les durées et dates de réalisation ne sont pas imposées dans le sujet; ce plan est séquentiel et sans estimation inventée. Le responsable du produit doit confirmer le nombre de points, les règles de modification/annulation, la durée des prestations et le modèle de comptes.
