# Gestion d'un parc de dinosaures

## Description

**Projet du cours GLO-4002 à l'Université Laval. Ce projet est l'API d'un jeu vidéo dont le but est de gérer un parc de
dinosaures.**


## Notes au correcteur

- Choix des têtes d'agrégat (choix d'un seul et unique repo). Après plusieurs heures de discussion en équipe et deux rencontres avec les auxiliaires du cours, on en est venu à la conclusion que ce serait mieux d'avoir un seul repo puisque toutes les têtes d'agrégats dans le domaine (`Turn`, `Pantry`, `DinosaurHerd`) passent inévitablement par la `Game`. Puisque l'on veut que ce soit l'état de la game qui soit sauvegardé, alors pour éviter des problèmes de concurrences, il semble préférable que tout soit récupéré et sauvegardé en même temps. L'autre option que nous avons considérée et implémentée plus tôt était d'avoir plusieurs repos (pour `Turn`, `Pantry`, `DinosaurHerd`) ainsi qu'un repository pour `Game`, où game contiendrait les ids des autres têtes d'agrégats. Cependant, cela nécessite une logique d'assemblage dupliquée dans plusieurs use cases ou encore d'imbriquer les repositories (donc que le repo s'occupe de la logique d'assemblage), ce qui n'était pas recommandé initialement par les auxiliaires lors de nos discussions. En effet, ils jugeaint que la possibilité d'accéder à des têtes d'aggrégat à plusieurs niveaux semblait douteuse et dangereuse au niveau de la synchronicité et cohérence de la persistence.

- Logique de feeding (éviter le back and forth avec la `Pantry` ; gérer uniquement les ressources consommées). Lorsque nous avons décidé du flot d'information pour le feeding des dinosaures, on a dû faire un choix entre prendre toutes les ressources de la `Pantry`, dire aux dinosaures de se feed et retourner ce qu'il reste OU faire comme on fait là et demander aux dinosaures ce dont ils ont besoin, prendre seulement ce qui est essentiel dans la `Pantry` (selon une "commande" appelée `ResourcesNeeds` passée en paramètre) et feed les dinosaures avec les quantités retournées. En d'autres termes, on hésitait entre 2 calls à la pantry et 1 au troupeau ou encore 2 calls au troupeau et 1 call à pantry. Par souci de simplicité, on a opté pour la seconde option, car ajouter des ressources avec différentes dates d'expiration impliquerait une logique d'assemblage assez lourde. Avec la deuxième option, il semble aussi que ce soit un flot d'information plus naturel (c'est un peu comme si les dinosaures allaient au resto!).

![image](https://user-images.githubusercontent.com/89754487/143073916-691042db-7123-48ac-add9-ee220dbe534c.png)

- Attributs publics dans les API requests/dtos : la logique d'assemblage des dtos en entrée dans Jetty/Jersey nécessite un constructeur par défaut pour les classes avec des attributs membres privés. Dans la mesure où la pertinence de deux constructeurs n'était pas évidente/implicite au lecteur dans ces classes, nous avons choisi de laisser les attributs publics dans les dtos reçus du client pour éviter toute ambiguïté.

- Pour le moment, on a décidé de laisser la méthode compareTo dans `Dinosaur`, au lieu de l'extraire dans une classe qui implémente `Comparator` puisqu'on a seulement besoin de ce compareTo pour le moment. Dans le cas advenant qu'il pourrait y avoir plusieurs façons de comparer les dinosaures, on ajouterait des classes. Dans la classe qui implémente `FeedingOrderStrategy`, on a donc mis en attribut les stratégies de comparateur de dinosaures pour qu'éventuellement on puisse les injecter à un constructeur.

## Environnement technologique

- Java 11 (Open JDK)
- JUnit 5
- Mockito 3
- Gestion des dépendances avec Maven

**Les requêtes supportées par l'API du projet sont listées dans [progress.json](progress.json).**

**[Description des requêtes de l'API](https://projet2021.qualitelogicielle.ca/stories/).**



## Setup du Projet

### Préparer/installer le projet

Pour installer l'application, utilisez :

```bash
mvn clean install
```



### Exécuter l'app

Pour exécuter l'application, utilisez :

```bash
mvn exec:java -pl application
```

L'app s'exécute sur [http://localhost:8181](http://localhost:8181).



### Exécuter les tests unitaires

Les tests se trouvent dans le package `src/test/java/ca/ulaval/glo4002/game`.

Pour exécuter les tests unitaires, utilisez :

```bash
mvn test
```



### Tests E2E

Les tests E2E sont dans un répertoire Postman à la racine du projet.

Pour les exécuter, importez la collection "diGLOsaure" et l'environnement "Local" dans Postman. Assurez-vous d'ailleurs de bien sélectionner cet environnement avant de lancer les tests.
