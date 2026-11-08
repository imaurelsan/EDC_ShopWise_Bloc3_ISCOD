# Cadrage et analyse fonctionnelle

## Situation

ShopWise vise les petits commerces ayant peu de temps pour administrer leurs clients et leur planning. Le premier cas de référence est l’épicerie « Chez Marie ». Les fonctions ici développées sont exclusivement les clients, les rendez-vous et les points de fidélité.

## Utilisateurs et besoins

Le commerçant tient les coordonnées, crée et filtre les rendez-vous et met à jour leur état. Un client peut consulter sa fiche et son historique de points. Les données de démonstration ne représentent pas de personnes réelles.

| Réf. | User story | Conditions d’acceptation retenues |
|---|---|---|
| US1 | Commerçant, créer/mettre à jour une fiche client (nom, email, téléphone) | Nom et email requis; email au format valide; lecture et mise à jour par identifiant. |
| US2 | Commerçant, créer un rendez-vous (client, date, heure, service) | Client existant; service, date et heure requis; état initial `PLANNED`. |
| US3 | Commerçant, lister et filtrer les rendez-vous par date, état, client | Chaque filtre est optionnel et combinable. |
| US4 | Commerçant, déclarer honoré ou annulé | Seuls `PLANNED` et `CANCELLED` peuvent être honorés; un rendez-vous honoré ne récompense qu’une fois. |
| US5 | Commerçant, attribuer des points après rendez-vous honoré | 10 points de démonstration crédités lors de la transition vers `HONORED`, une seule transaction par rendez-vous. |
| US6 | Client, consulter ses informations | Consultation par identifiant sans connexion, compromis explicitement limité au prototype. |
| US7 | Client, consulter solde et transactions | Solde = somme des mouvements; historique chronologique, mouvements positifs dans cette version. |

## Règles et exclusions

Un rendez-vous est rattaché à un client et à un commerce. Chaque client porte un `shop_id` afin de préparer le cloisonnement multi-commerces. Le prototype ne valide pas les collisions de créneau, car la durée des services n’est pas précisée. Les changements d’état ne peuvent pas débiter des points. Toute annulation après honoré ne retire pas les points : une politique de compensation est à arbitrer avec le métier.

L’API est ouverte comme demandé, mais une consultation par identifiant ne constitue pas une authentification et expose des données. En environnement réel, il faudrait vérifier l’identité, le consentement, le cloisonnement, les durées de conservation et les droits RGPD avant de rendre ces routes accessibles.

## Modèle métier

Un commerce possède plusieurs clients. Un client possède plusieurs rendez-vous et transactions de fidélité. Une transaction est facultativement liée à un rendez-vous ; une contrainte d’unicité garantit une récompense unique. Le schéma et le script d’initialisation sont sous `bdd/`.
