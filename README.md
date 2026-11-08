# ShopWise — clients, fidélité et rendez-vous

Projet d’étude de cas pour une épicerie indépendante. Ce dépôt comprend une API Java/Spring Boot, une interface Angular, le schéma relationnel, des tests, une chaîne CI et des configurations Docker. Il s’agit d’une base de travail à relire et à défendre : les choix et résultats doivent être compris, adaptés et vérifiés avant toute remise pédagogique.

## Périmètre fonctionnel

Les parcours retenus couvrent les sept user stories de l’énoncé : créer ou modifier un client, créer un rendez-vous, filtrer la liste, passer un rendez-vous à honoré ou annulé, attribuer des points lors du premier passage à honoré, consulter une fiche client et afficher solde et historique. Une attribution de 10 points est une hypothèse de démonstration, paramétrable dans `application.yml`. La connexion client est volontairement remplacée par une consultation sans authentification, conformément au périmètre « API ouverte » de l’énoncé ; ce n’est pas adapté à des données réelles.

## Lancement local avec Docker

Prérequis : Docker avec le plugin Compose. Depuis la racine :

```sh
docker compose up --build
```

L’interface répond sur http://localhost:4200 et l’API sur http://localhost:8080. L’API est documentée dans `docs/api.md`. La base PostgreSQL locale est initialisée par `bdd/script.sql`. Les données sont fictives.

Pour exécuter les tests sans Docker : installez Java 17+ et Maven puis exécutez `cd src/backend && mvn verify`. Dans un autre terminal, installez Node.js 20+ puis `cd src/frontend && npm install && npm test -- --watch=false --code-coverage`. Les rapports sortent sous `src/backend/target/site/jacoco/` et `src/frontend/coverage/`.

## Déploiement Kubernetes / Helm

Un chart de démonstration est rangé dans `deploy/helm/shopwise`. Construire et publier au préalable les deux images, puis remplacer les valeurs `image.repository` et `image.tag` dans `values.yaml`. Avec un cluster Minikube actif : `helm install shopwise deploy/helm/shopwise`. Pour supprimer : `helm uninstall shopwise`. PostgreSQL est ici externe au chart et doit être fourni, avec ses identifiants via variables d’environnement. Voir `docs/deploiement.md`.

## Workflow et suivi

Workflow de branches : `main` stable, `develop` intégration, branches `feature/<numero>-<sujet>` et `fix/<numero>-<sujet>`. Une branche est fusionnée par pull request après revue, tests verts et mise à jour de documentation. Commits courts, par exemple `feat(clients): ajouter la fiche client (#1)`. Le registre `docs/suivi-taches.md` représente les Issues à créer dans GitHub ; aucun accès à un compte GitHub n’étant fourni, ces Issues et l’onglet Actions ne sont pas publiés par ce livrable.

## Contenu et traçabilité

- `docs/` : analyse, décisions, API, planification, checklist et usage des outils.
- `bdd/` : script PostgreSQL et schéma PDF.
- `src/` : code Java et Angular ainsi que les tests.
- `.github/workflows/ci.yml` : compilation, tests, rapports et publication Docker Hub sur tag.
- `deploy/helm/` : chart simple Kubernetes.

Les répertoires de couverture contiennent le statut honnête de mesure à la création. Les rapports chiffrés réels sont produits par CI ; ils ne sont pas préremplis avec des chiffres non mesurés.

## Limites connues

Pas d’authentification ni d’autorisation, pas de notifications réelles, pas de paiement ni d’intégration de SMS/email. Le client et le rendez-vous sont liés à un commerce, mais il reste nécessaire d’ajouter des contrôles d’accès et des règles de confidentialité avant un usage réel. Les migrations de schéma, la sauvegarde, l’observabilité et la gestion des secrets doivent être renforcées pour la production.

## Usage des outils et intégrité

Le dépôt a été préparé avec une assistance générative pour structurer des exemples de contenu et de code. Conformément à la charte pédagogique fournie, cette utilisation doit être déclarée dans le formulaire de l’établissement, les parties concernées revues par l’apprenant, et le fonctionnement compris avant présentation. Consigner les instructions et résultats selon les règles applicables. Ce dépôt ne prétend pas établir une contribution personnelle ni une expérience vécue.
