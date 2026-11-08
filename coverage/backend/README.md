# Rapport de couverture backend

Le rapport chiffré n’a pas été généré dans l’environnement de préparation (Java/Maven non disponibles). Ne pas présenter de pourcentage comme mesuré. Après `cd src/backend && mvn verify`, consulter `target/site/jacoco/index.html` et l’artefact de CI `backend-coverage`. Le workflow impose un seuil global JaCoCo de 60 % instructions et branches; il faut compléter les tests jusqu’à ce que la porte qualité passe.
