# Fiche de conformite - bandit-manchot-familles-d-oeuvres-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-arts-oeuvres-familles.yaml`
  (12 items, type `question`), paquet `DEGRADE` ecrit en s53, corrige par `lea-dev` le 2026-10-08
  (commit `9a0011b1`) sur les six points de mon arbitrage prealable.
- **Entree de catalogue** : `bandit-manchot-familles-d-oeuvres-cp`, titre servi *"Les familles
  d'œuvres"*, consigne servie *"Choisis le mot qui va avec la statue, le tableau ou la
  photographie."*, `nombreDeManches: 10` (commit `920e19e2`).
- **Ligne declaree** : `ART-CP-10`. **La ligne ne ferme pas et reste `non couvert`**, quel que soit
  ce verdict (plafond ecrit par l'instance A, confirme dans mon arbitrage prealable, section 1).
- **Story** : `S54-BR-ART-CP-10` (`docs/backlog/backlog.md`).
- **Arbitrage prealable** :
  `squad/sprints/s54-branchement-des-premiers-paquets-et-reports-de-s53/arbitrage-nadia-c.md`
  (instance C, 2026-10-08, commit `c5c2d50f`).
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-10-08 par `nadia-prof`
  (instance C, s54), apres avoir joue le jar servi.
- **Derniere mise a jour** : 2026-10-08 (premier audit).

Nom de cette fiche : la convention du depot (`FICHE-<id de l'entree de catalogue>`). Le critere 7
de la story la nomme `FICHE-cp-arts-oeuvres-familles.md` ; c'est le nom du paquet, pas celui du
jeu. Ecart signale a `camille-po`, la fiche n'est pas dupliquee.

---

## 1. Ce que j'ai joue

Arbre : branche `s54-nadia-c` a `5cdadef3` (round-two fusionne, branchement et corrections de
`lea-dev` compris). `bash outils/mvn-jdk21.sh -B clean verify` : sortie 0, tests lances sur
Java 21.0.10. Je n'ai lance que le job `build` de la CI ; les autres jobs de `ci.yml` ne sont pas
lances par moi.

Ressources embarquees : `catalogue.yaml` et `contenu/cp-arts-oeuvres-familles.yaml` extraits de
`recreo-adapter-content-0.1.0-SNAPSHOT.jar` dans `bootstrap/target/recreo.jar`, `cmp` contre les
fichiers commites : identiques tous les deux.

Jar lance sur Java 21.0.10, parcours par les routes servies : `GET /niveaux/CP` (le jeu y est
liste sous son titre), `POST /parties/bandit-manchot-familles-d-oeuvres-cp`, puis
`POST /parties/bandit-manchot-familles-d-oeuvres-cp/coups`. Quatre parties de dix manches :

| Partie | Ce que j'ai fait | Fin de partie servie |
|---|---|---|
| 1 | aucune erreur | *"Tu as trouvé, tout seul : 10"* |
| 2 | une erreur par manche, puis la bonne reponse | *"Tu as trouvé, tout seul : 0"* |
| 3 | deux erreurs par manche (les deux distracteurs), puis la bonne reponse | *"Tu as trouvé, tout seul : 0"* |
| 4 | trois fois le meme distracteur, puis la bonne reponse revelee | *"Tu n'as pas trouvé."* suivi de la question et de *"La bonne réponse : un pinceau"* |

**Les douze items sont sortis au moins une fois, chacun avec au moins une erreur volontaire.**
Chaque partie tire dix items distincts.

Traitement de l'erreur, vu a l'ecran : apres une erreur, *"Ce n'est pas ça. Essaie encore !"* et
l'indice de l'item ; apres la troisieme, *"Appuie sur la bonne réponse : <reponse>"*, l'indice
restant affiche. Un enfant bloque avance toujours. C'est l'ecran commun des paquets de questions,
pas une propriete de ce paquet.

## 2. Les six corrections de mon arbitrage prealable (section 6)

Texte de remplacement choisi par `lea-dev`, juge ici.

| Point | Ce que j'avais demande | Ce qui est servi | Verdict |
|---|---|---|---|
| 1. Item 8 | deux matieres associees a un objet fait a la main, que seule la durete ecarte | *"la pâte à modeler"*, *"la laine"* | **tenu**. La laine est une matiere de l'objet fait main (tricot, pompon), plausible, et *"dure"* l'ecarte comme la pate a modeler |
| 2. Item 9 | un materiau qui ne se modele pas a la main a la place de *"le papier"* | *"le bois"*, *"le fer"* (remplace aussi *"le carton"*) | **tenu**. Ni l'un ni l'autre ne se modele a la main ; tous deux servent a faire des statues, donc plausibles. Aucun n'est propose a l'item 8, ou le bois serait une seconde bonne reponse |
| 3. Item 11 | un autre item ; piste : bonne reponse *"un tableau"* | *"Quelle œuvre peinte accroche-t-on au mur ?"*, *"un tableau"*, distracteurs *"une statue"*, *"une photographie"* | **tenu**. L'item ne double plus l'item 1, et *"tableau"* est enfin une bonne reponse. *"Peinte"* est le seul mot qui ecarte la photographie : c'est le mot a lire, c'est bien. Voir la reserve 2 sur son indice |
| 4. Item 12 | deux lieux ou l'on va regarder ou emprunter, sans oeuvres | *"dans une bibliothèque"*, *"dans un zoo"* | **tenu** |
| 5. Items 7 et 10 | l'un des deux enonces ne donne plus la reponse de l'autre | item 7 : *"Avec quel outil trace-t-on des traits sur une feuille ?"* ; item 10 : *"Quelle œuvre fait-on sur une feuille, à la main ?"* | **tenu**. Aucun des deux ne nomme plus la reponse de l'autre |
| 6. Indices 2, 3, 4 | aucun enonce ni indice ne dit la relation qu'un autre item demande | 2 : *"travaille avec de la peinture"* ; 3 : *"fait des images qu'on peut imprimer"* ; 4 : *"travaille avec ses mains, debout devant un gros bloc"* | **tenu**. J'ai relu les douze enonces et les douze indices contre les douze relations demandees : aucun ne nomme la reponse d'un autre item |

En-tete du paquet : matieres, lieu et *"photographie jamais bonne reponse"* sont ecrits, la
parenthese du descripteur est retiree. **Tenu.**

**Je corrige un de mes constats.** Mon arbitrage, point 5, ecrivait que les items 7 et 10 sortent
*"presque toujours ensemble"* sur dix manches tirees parmi douze. Le calcul donne 45 tirages sur
66, soit environ deux parties sur trois. Le defaut etait reel, ma frequence etait exageree.

## 3. Ancrage au programme

`squad/programme/arts-plastiques/competences-cp.md`, `ART-CP-10` : reconnaitre quelques oeuvres
deja rencontrees et les rapprocher. **Ce paquet ne travaille pas cette competence** : il ne montre
aucune oeuvre et n'en fait rapprocher aucune. Il sert le vocabulaire des familles d'oeuvres, des
metiers et d'un lieu ; ses items 5 a 9 sont sur le terrain d'`ART-CP-11`, deja `couvert-valide`.
Le verdict de cette fiche dit que le paquet peut rester au catalogue. **Il ne bouge pas la
matrice.**

## 4. Exactitude, distracteurs, niveau

- **Exactitude** : douze enonces et douze bonnes reponses verifies, aucune erreur de fait.
- **Distracteurs** : tous pris dans la famille de la bonne reponse (oeuvre contre oeuvre, metier
  contre metier, outil contre outil, matiere contre matiere, lieu contre lieu). Aucun n'est absurde
  depuis les corrections.
- **Lisibilite** : une phrase par enonce, une phrase par indice, trois propositions courtes. Le
  test de longueur des enonces de CP passe dans la suite verte.
- **`ART-1`, `ART-2`** : aucun mot de couleur, aucune question de gout. **Tenues.**
- **`CON-1`** : aucune oeuvre nommee ni decrite. **Tenue.**

Ce que j'avais dit regarder en jouant, sans en faire une condition :

- *"sculpteur"* et *"photographie"* cote a cote : je les ai lus a l'ecran, je n'ai pas vu un enfant
  les lire. Mots longs, mais l'adulte est la et la question de l'item 4 (*"Qui fait des statues ?"*)
  porte le sens. Pas de reserve.
- *"photographie"* / *"photographe"* (item 3), et *"appareil photo"* (item 5) : l'enfant peut
  trouver la reponse par la forme du mot. C'est la famille de mots, une strategie de lecture que
  le CP enseigne ; je ne la compte pas comme une devinette. Pas de reserve.
- *"des ciseaux"* pour faire un tableau (item 6) : on fait un collage avec des ciseaux, pas un
  tableau. Pas de seconde reponse defendable. Pas de reserve.

## 5. Reserves (2026-10-08, non bloquantes)

Les trois portent sur du texte que mon arbitrage n'avait pas lu (la consigne n'existait pas sur
l'arbre que j'ai lu, l'item 11 est neuf) ou sur un point que je n'avais pas releve (reserve 3).
Ce sont des reserves nouvelles, pas un durcissement des six points, qui sont leves.

1. **La consigne de catalogue ne decrit pas la tache de cinq items sur douze.** *"Choisis le mot
   qui va avec la statue, le tableau ou la photographie"* est servie sur chaque manche. Elle est
   fausse pour les items 7 (*"un crayon"* va avec le dessin) et 10 (la reponse est *"un dessin"*,
   absent de la consigne, et la consigne nomme deux distracteurs de cet item, *"une statue"* et
   *"une photographie"*). Elle est incoherente pour les items 1 et 11, ou la reponse est l'une
   des trois familles elle-meme et non un mot qui *va avec*, et pour l'item 12 elle ne dit rien de
   la question de lieu. **Non bloquant pour la meme raison que `FOP-R1`** : chaque enonce porte
   sa question entiere, l'enfant lit l'enonce. **Propriete a tenir** : la consigne nomme la tache
   que les douze items demandent, ou redevient generique, puisque chaque enonce nomme la sienne.
   Le texte est a `lea-dev`, je le juge en le rejouant. Fichier : `catalogue.yaml`, entree
   `bandit-manchot-familles-d-oeuvres-cp`, champ `consigne`.
2. **L'indice de l'item 11 n'ecarte pas le distracteur proche.** *"On la voit sur le mur, avec un
   cadre autour."* vaut aussi pour une photographie encadree, et *"sur le mur"* repete l'enonce.
   Apres une erreur sur *"une statue"*, il reste un tableau et une photographie, et l'indice ne
   departage pas les deux. **Propriete a tenir** : l'indice de l'item 11 dit ce qui distingue un
   tableau d'une photographie (la peinture), sans nommer le peintre ni le pinceau (items 2 et 6,
   regle du point 6 de mon arbitrage).
3. **Les trois metiers sont au masculin.** *"un peintre"*, *"un sculpteur"*, *"un photographe"*
   sont les bonnes reponses et les distracteurs des items 2, 3 et 4. Les indices disent *"Cette
   personne"*, l'attention y etait. Meme motif que les metiers tous masculins de
   `FICHE-bandit-manchot-problemes-additifs-cp.md`. **Propriete a tenir** : au moins un metier sur
   trois est servi au feminin (*"une photographe"*, *"une peintre"*, *"une sculptrice"*), la meme
   forme partout ou il apparait dans le paquet.

## 6. Ce dont je reste la seule garde

Aucun test ne verifie qu'un distracteur est plausible et faux, qu'un indice ou un enonce ne donne
pas la reponse d'un autre item, que deux items ne se doublent pas, qu'un indice departage la bonne
reponse du distracteur proche, que la consigne decrit la tache des items, ni l'equilibre des
genres des metiers. **Je les ai verifies ce jour en jouant et en relisant les douze items ; rien
ne les garde au prochain changement de ce paquet.**

Ce que je n'ai pas verifie : la lecture par un enfant reel (aucun enfant n'a joue), le rendu
visuel (je n'ai lu que le HTML servi, pas un ecran de navigateur), les jobs de `ci.yml` autres
que `build`.

---

## Bloc de validation

> **Done pedagogique : VALIDE SOUS RESERVE.**
>
> **Prononce le 2026-10-08 par `nadia-prof` (instance C, s54), apres avoir joue le jar construit
> sur `5cdadef3`**, quatre parties, douze items sur douze, erreurs volontaires jusqu'a la
> revelation.
>
> **Ce que j'ai verifie** : les six corrections de mon arbitrage prealable (toutes tenues), les
> douze items (exacts, distracteurs plausibles), `ART-1`, `ART-2`, `CON-1` (tenues), la lisibilite
> pour un CP accompagne, le traitement de l'erreur, l'identite des ressources embarquees et des
> fichiers commites.
>
> **Reserves** (section 5, a faire en stories par `camille-po`) : 1, la consigne de catalogue ;
> 2, l'indice de l'item 11 ; 3, les metiers tous masculins. Aucune ne bloque.
>
> **Mouvement de ligne : aucun.** `ART-CP-10` reste `non couvert`. Ce verdict dit que le paquet
> peut rester au catalogue ; il ne vaut pour aucune ligne de la matrice.
>
> **Tarif consomme** : audit 1,0. Rejeu 0,5 non consomme. Instance C : 1,0 sur 1,5.
