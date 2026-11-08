# Contrat API (prototype)

Base locale : `http://localhost:8080/api`. JSON, CORS activé pour le client local. Aucun jeton n’est attendu.

| Méthode et chemin | Corps / paramètres | Résultat |
|---|---|---|
| `POST /clients` | `{ "shopId":1,"name":"...","email":"...","phone":"..." }` | Client créé, 201 |
| `GET /clients/{id}` | — | Fiche client |
| `PUT /clients/{id}` | mêmes champs | Fiche modifiée |
| `GET /clients/{id}/loyalty` | — | `{ balance, transactions }` |
| `POST /appointments` | `{ "customerId":1,"date":"2026-11-10","time":"10:00:00","service":"Conseil" }` | Rendez-vous planifié, 201 |
| `GET /appointments?date=2026-11-10&status=PLANNED&customerId=1` | filtres tous facultatifs | Liste filtrée |
| `PATCH /appointments/{id}/status` | `{ "status":"HONORED" }` ou `CANCELLED` | Rendez-vous modifié; récompense unique sur honoré |

Erreurs à prévoir côté appelant : 400 pour une requête invalide, 404 pour une ressource absente, 409 pour une contrainte d’unicité. Les formats de retour suivent les objets Java sérialisés; une enveloppe d’erreur uniforme reste une amélioration.
