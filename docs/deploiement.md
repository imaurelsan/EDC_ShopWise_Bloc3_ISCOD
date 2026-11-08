# Procédure de déploiement

## Compose (démonstration locale)

1. Installer Docker Engine et Docker Compose.
2. Depuis la racine : `docker compose up --build -d`.
3. Vérifier `docker compose ps`, puis ouvrir `http://localhost:4200`; vérifier l’API par `curl http://localhost:8080/api/appointments`.
4. Arrêter avec `docker compose down`. Les données du volume PostgreSQL restent présentes; `docker compose down -v` les supprime.

Les identifiants de base sont uniquement locaux et déclarés dans Compose pour le cas de démonstration. Ne pas les réutiliser en production. Définir secrets, sauvegardes, TLS, domaine, supervision et politiques d’accès avant exposition.

## CI et publication

Le workflow s’exécute sur push et pull request pour builder et tester. Les rapports JaCoCo et Angular sont exportés comme artefacts. À la création d’un tag `v*`, le job Docker construit puis pousse les images. Ajouter dans GitHub les secrets `DOCKERHUB_USERNAME` et `DOCKERHUB_TOKEN`, puis définir dans les variables `DOCKERHUB_REPOSITORY` (ex. compte/nom). Aucun compte n’est présumé ici.

## Minikube et Helm

Préparer un Minikube avec ressources adaptées et installer Helm. Construire/pousser les images ou les charger dans Minikube (`minikube image load ...`). Modifier `deploy/helm/shopwise/values.yaml` : images et URL d’API. PostgreSQL doit être déjà accessible et la configuration doit fournir `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD` via un Secret Kubernetes. Puis :

```sh
helm lint deploy/helm/shopwise
helm install shopwise deploy/helm/shopwise
kubectl get pods,services
```

Le chart de démonstration déploie les deux applications mais pas PostgreSQL, l’ingress, ni les Secrets. Adapter l’exposition réseau et les politiques de sécurité au cluster choisi.
