# Rapport qualite - S06 - obstacle mesure sous 500 px

- **Auteur** : yanis-qa
- **Date** : 2026-08-07
- **Obstacle route par** : sofia-sm, sur le fait etabli par ines-design (plancher de largeur de
  `chrome --headless --window-size`) et sa consequence sur S6-8 (deja fusionnee) et sur l'obstacle
  de debordement du menu a 360 px (commit `4b63f62`).
- **Provisionnement** : worktree dedie hors de l'arbre du depot, branche forkee de `round-two` a
  `2370519`. Lignee verifiee :
  ```
  $ outils/verifier-lignee.sh
  le worktree sur la branche 's06-yanis-qa' descend de round-two.
  ```
  Le script rend le bon verdict sur ce cas (worktree neuf, provisionne exactement sur la tete de
  `round-two`) : rien a signaler sur son comportement.
- **JDK** : `outils/verifier-jdk.sh` donne, sur ce poste, `java du PATH : 1.8.0_202 | JDK de
  compilation : 21.0.10 | JAVA_HOME : vide | toolchains lu par Maven : non (fichier present sous
  un nom non lu)`. Toutes les commandes `mvn` et `chrome`/`java` de ce rapport ont ete lancees avec
  `JAVA_HOME` pointe sur ce JDK 21 le temps de la commande, jamais ecrit dans un fichier commite.
- **Ce que je n'ai pas touche** : `docs/backlog/backlog.md`, `squad/conformite/`, les `ADR`,
  `recreo.css` et les gabarits de production. La seule modification de `recreo.css` de ce rapport
  est une mutation locale, non commise, faite pour la preuve de morsure du point 2 et immediatement
  annulee (`git status --short` rendu vide juste apres, verifie).

## 1. Le plancher est-il reel, et lequel

Reel, et il vaut **exactement 500 px** de largeur de contenu (`window.innerWidth`), pas
"environ 500".

**Methode** : page de mesure minimale (`document.write(window.innerWidth + "x" +
window.innerHeight)`, taille de police large pour une lecture directe sur la capture), chargee en
`file://` avec `chrome.exe --headless=new --disable-gpu --user-data-dir=<profil isole neuf>
--no-first-run --window-size=<L>,600 --screenshot=...`. Le `--user-data-dir` isole est necessaire :
sans lui, une instance Chrome deja ouverte sur le poste absorbe l'invocation ("ouverture dans une
session de navigateur existante") et le resultat mesure alors autre chose qu'un headless reel -
piege distinct de celui d'ines-design, verifie en le retrouvant avec `tasklist` (chrome.exe deja
present) avant de l'eliminer.

Balayage de `L` de 200 a 600 px par paliers. Pour tout `L` demande de 200 a 510, le texte rendu
est identiquement `500x505`. Preuve que ce n'est pas une mise a l'echelle mais un **decoupage** :
la boite englobante des pixels non blancs du texte (mesuree en Pillow) est au pixel pres identique
en position absolue (`(3, 11, 151, 37)`) quelle que soit la largeur de sortie du PNG (200, 320,
360, 400, 500, 510 px) - le PNG rendu est un decoupage a gauche d'une page toujours mise en page
comme si le viewport faisait 500 px, jamais une image redimensionnee. A partir de `L = 516`, le
plancher cesse de s'appliquer et le contenu suit `L - 16` (520 -> 504, 550 -> 534, 600 -> 584 ; 16
px correspondant a la reserve de barre de defilement). J'ai aussi reproduit le cas exact d'ines
(demande `320,480` sur une session Chrome deja ouverte) et obtenu le meme `500x385` qu'elle
rapporte.

**Consequence directe pour toute mesure anterieure** : toute capture prise avec cette forme de
commande a une largeur demandee inferieure a 516 px n'a jamais mesure cette largeur - elle a
mesure un decoupage de la mise en page a 500 px.

## 2. A quelle largeur S6-8 a-t-elle reellement ete verifiee, et le correctif tient-il

**Le depot ne permet pas de reconstituer la commande exacte de lea-dev.** Le commit `4fabd6f`
ecrit "verifie sur le jar reconstruit a 360 px" sans citer la ligne de commande ; le commit
d'obstacle `4b63f62` dit seulement "sur Chrome headless reel (jar reconstruit)". Aucun des deux
fichiers, ni aucun autre du depot, ne porte l'invocation utilisee. Si c'est la meme famille de
commande que celle qui a servi a trouver le debordement de menu (ce que suggere `4b63f62` en les
presentant comme la meme verification), alors, par le point 1, la largeur reellement rendue lors
de la verification de S6-8 etait le plancher (~500 px), pas 360 px : l'enonce "verifie a 360 px"
du commit est une affirmation de precision que le depot ne peut pas justifier.

**Cela n'invalide pas pour autant le correctif.** Je l'ai verifie par deux voies independantes de
la mesure de lea-dev :

- **Lecture statique de la cascade CSS**, sur les fichiers reellement servis par le jar construit
  a `round-two` (`2370519`) : `recreo.css` ne declare **aucune** regle `@media` (`grep -c "@media"`
  rend 0). `bootstrap.min.css` en declare, mais la seule regle qui porte le rembourrage lateral de
  `.container` (`padding-right:calc(var(--bs-gutter-x) * .5)`, `--bs-gutter-x:1.5rem`, soit
  0,75 rem) est la regle de base, hors de toute `@media` ; les blocs `@media` qui citent
  `.container` n'y redeclarent que `max-width`, jamais le rembourrage. La propriete en cause ne
  peut donc varier avec la largeur d'ecran dans ce code, quelle que soit la largeur a laquelle on
  la lit - le raisonnement d'ines-design est exact, verifie ici par moi sur le fichier servi et non
  repris de son affirmation.
- **Rendu reel a une largeur veritable de 360 px**, par la methode du point 4 (iframe CSS a largeur
  fixe, hors du plancher). Sur `main#jeu` (`class="container zone-jeu"`, le meme element sur les
  cinq etats de l'ecran de jeu, `DEC-0012`/`ADR-0020`) : marge gauche mesuree a **12 px** (premier
  pixel non fond a `x = 12` par balayage de la ligne du bouton "Calcul CP" avec Pillow, sur une
  capture ou l'iframe fait 360 px CSS de large et la fenetre Chrome parente 600 px, donc hors
  plancher). 12 px = 0,75 rem = la valeur inconditionnelle de `.container`, jamais ecrasee.

**Conclusion sur S6-8** : le correctif tient a 360 px, prouve independamment de la mesure qui a
servi a fusionner la story. La reserve porte sur l'exactitude de l'enonce "verifie a 360 px" dans
le commit de fusion, pas sur le resultat technique.

## 3. Le debordement de l'entree de menu a 360 px

**Le depot ne peut pas affirmer que ce debordement existe a une largeur reelle de 360 px sur le
code de `round-two` (`2370519`), non corrige.** Je l'ai teste par la methode du point 4 (iframe
CSS a 360 px, fenetre Chrome parente a 600 px, hors plancher), sur le jar reconstruit a la tete de
`round-two`, sans aucun des correctifs proposes par ines-design (branche `s06-ines-menu-360` non
fusionnee) : "Les jeux" est entierement visible, sur une seule ligne, sans debordement.

En revanche, en reproduisant la meme page avec la commande directe fautive du point 1
(`--window-size=360,900 --screenshot`, sans passer par un profil isole ni une iframe), la capture
ne montre **aucune trace de "Les jeux"** dans le bandeau - visuellement, exactement le symptome
decrit dans `4b63f62` ("sort du cadre visible"). Ce n'est pas une coincidence au vu du point 1 :
si la page est reellement mise en page pour 500 px et que `.menu` est pousse a l'extreme droite de
cette largeur virtuelle (`margin-right: auto` sur `.nom-site`), un decoupage a gauche a 360 px
ampute mecaniquement tout ce qui se trouve apres `x = 360`, y compris un bouton qui n'a rien d'un
defaut de mise en page a une largeur reelle de 360 px.

Je ne peux pas conclure avec certitude que c'est *exactement* ce qui s'est produit sur le poste de
lea-dev, faute de sa commande exacte (point 2) ou de son environnement de police. Ce que le depot
peut affirmer : **deux mesures independantes par une methode qui evite le plancher - la mienne et
celle d'ines-design dans `proposition-ines-menu-360px.md`, sur des rendus Chrome reels distincts -
ne trouvent aucun debordement a 360 px sur ce code**, et **une mesure par la commande directe
fautive reproduit le symptome signale sans qu'aucune propriete CSS ne deborde reellement**. La
conclusion la plus defendable est que l'obstacle route en `4b63f62` a ete ouvert sur un artefact de
decoupage, pas sur un defaut de mise en page reel - sans que ce rapport ferme la question a la
place de `theo-archi` ou de `nadia-prof`, qui restent les destinataires nommes de l'obstacle.

Point de gouvernance a signaler a `sofia-sm` : aucune des deux verifications qui ont motive
`4b63f62` (celle de lea-dev pour S6-8, celle qui a trouve le debordement) n'a laisse de commande
citable dans le depot. C'est le meme defaut que celui nomme en s05 pour le rembourrage - une
methode non ecrite n'est pas une methode qu'on peut remettre a l'epreuve.

## 4. Une methode de mesure sous 500 px, reproductible

Ce qui manque au depot et que je propose, sans l'automatiser (ce choix, s'il doit devenir un
garde-fou permanent de CI, revient a `theo-archi`, qui instruit deja la question sur ce meme
obstacle) :

1. Construire le jar (`mvn -B package`) et le lancer sur un port local
   (`java -jar bootstrap/target/recreo.jar --server.port=<port libre>`).
2. Ecrire, hors du depot, une page HTML minimale avec une seule balise `<iframe>` dont le CSS fixe
   `width` a la largeur exacte a verifier (`360px`, `375px`, etc.) et pointant vers l'URL locale de
   l'application. La largeur CSS d'une iframe n'est pas soumise au plancher de fenetre du
   navigateur (verifie au point 1 et confirme ici sur l'application reelle) : c'est elle qui sert
   de vraie largeur de viewport pour la page chargee dedans.
3. Capturer cette page conteneur avec `chrome --headless=new --disable-gpu --user-data-dir=<profil
   isole neuf> --window-size=<largeur parente >= 600>,<hauteur> --screenshot=...` - la fenetre
   parente doit depasser le plancher (516 px), pas la largeur qu'on cherche a verifier.
4. Mesurer sur l'image obtenue, pas en confiance : bbox Pillow (comme au point 1) ou, plus
   simplement pour un controle visuel, une lecture directe de la capture.

**Limite explicite de cette methode**, a ne pas laisser croire resolue : elle ne donne acces qu'a
une mesure en pixels sur l'image (une iframe cross-origine ne permet pas d'appeler
`getBoundingClientRect` depuis la page conteneur - contrainte de securite du navigateur, pas un
defaut de la methode). Pour une assertion programmatique en pixels exacts sans capture d'ecran ni
retouche temporaire d'un gabarit de production, il faut piloter Chrome par le protocole DevTools
(`Emulation.setDeviceMetricsOverride`, `Page.navigate`, `Page.captureScreenshot` ou
`Runtime.evaluate` pour lire `getBoundingClientRect` directement) : ce protocole expose un reglage
de viewport totalement independant du plancher de fenetre du point 1. Je ne l'ai pas mis en oeuvre
ici (aucun client WebSocket n'est actuellement une dependance du depot ni de mon environnement, et
en ajouter un pour ce seul rapport aurait ete disproportionne) ; c'est la piste qu'ines-design a
deja signalee a `theo-archi`, et ma mesure ne fait que confirmer, par une preuve pixel independante
de la sienne, qu'elle est necessaire : **`--window-size` seul, quelle que soit son wrapper (test
Java, script shell), ne peut pas servir de base a un garde-fou automatique en dessous de 516 px**.

## 5. `mvn -B clean verify`

Commande lancee sur le worktree de ce rapport, `JAVA_HOME` sur un JDK 21.0.10 (voir en-tete),
apres `clean` uniquement (aucun rapport d'une execution anterieure conserve) :

```
mvn -B clean verify
...
recreo-domain            -> Tests run: 43, Failures: 0, Errors: 0, Skipped: 0
recreo-application       -> Tests run: 7,  Failures: 0, Errors: 0, Skipped: 0
recreo-adapter-web       -> Tests run: 121, Failures: 0, Errors: 0, Skipped: 0
recreo-adapter-content   -> Tests run: 32, Failures: 0, Errors: 0, Skipped: 0
recreo-bootstrap (unit)  -> Tests run: 71, Failures: 0, Errors: 0, Skipped: 0
recreo-bootstrap (IT)    -> Tests run: 3,  Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

**Total : 277 tests, 0 echec, 0 erreur.** Methode du chiffre : somme des six blocs "Results" du
journal de cette seule execution, sur les cinq modules qui portent du code de production plus la
distinction unitaire/integration de `bootstrap` (`JarRecreoIT`, `CatalogueChargeableIT`,
`PropositionsAccentueesJouablesIT`, executes en phase `verify` par le plugin d'integration, donc
comptes a part des 71 tests unitaires du meme module). Identique au total de reference de
`round-two` a `2370519` annonce par `sofia-sm` : aucune derive constatee, ce qui est attendu
puisque je n'ai rien fusionne sur cette branche.

**ArchRule : 28**, meme methode de comptage que le chiffre de reference (recherche du motif
`ArchRule \w+ =` dans le code source, jamais dans les `ADR` ni dans `squad/`) : 27 dans
`bootstrap/src/test/java/com/recreo/archi/` (10 fichiers), 1 dans
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/archi/AssertionsWebTest.java`. Exclus
explicitement de ce compte : les occurrences textuelles de "ArchRule" dans les `ADR` (citations en
prose, pas des regles executees) et dans le module fixture
`bootstrap/src/test/resources/contreexemple/LesGardeFousSontMisALEpreuveTest/faux-module/` (un
contre-exemple delibere, jamais execute par la suite reelle).

**Preuve de morsure verifiee sur place pour le garde-fou de S6-8**
(`LeConteneurTierGardeSonRembourrageLateralTest`), puisque je le cite au point 2 comme preuve que
le correctif tient : j'ai remis localement `.zone-jeu { padding: 2rem 0; }` (le raccourci fautif
retire par S6-8), relance `mvn -B test -Dtest=LeConteneurTierGardeSonRembourrageLateralTest` seul
-> **1 echec sur 3**, message explicite citant `.zone-jeu` et `accueil.html`. Restaure le
correctif -> **3/3**, `git status --short` vide juste apres (aucune trace laissee dans le
worktree). Le garde-fou mord reellement, verifie ici et non repris du commentaire du commit qui
l'introduit.

## Ce qui reste ouvert, a router par `sofia-sm`

- La commande exacte utilisee par lea-dev pour S6-8 et pour trouver le debordement de menu n'est
  ecrite nulle part dans le depot : rien a remettre a l'epreuve sur cette mesure precise, seulement
  sur la famille de commande dont elle releve vraisemblablement (point 1).
- Le debordement du menu a 360 px, route en `4b63f62` vers `ines-design`, `lea-dev`,
  `theo-archi` et `nadia-prof`, n'est confirme par aucune mesure de ce rapport ni par la seconde
  mesure d'ines-design : la branche `s06-ines-menu-360` reste un correctif en attente d'un defaut
  dont l'existence a une largeur reelle de 360 px n'est pas etablie sur ce code. Je ne le classe
  pas moi-meme sans objet - c'est a `sofia-sm`/`nadia-prof` de statuer, avec ce constat en main.
- Le garde-fou automatique de largeur reelle sous 500 px reste a router par `theo-archi` (obstacle
  deja ouvert par ines-design) : ce rapport confirme, par une preuve pixel independante, que
  `--window-size` seul ne peut pas le porter, et nomme le protocole DevTools comme mecanisme
  candidat, sans trancher.
