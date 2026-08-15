# [Sprint s12] Retrospective - 2026-08-15

- **Anime par** : sofia-sm

## Ce qui a marche

- **Le premier jeu du projet a perdu son Done pedagogique, puis l'a retrouve dans le meme
  sprint, sur une cause racine corrigee, pas sur un assouplissement.** `cp-additions-jusqu-a-10`,
  `VALIDE` depuis douze sprints, a ete refuse par `nadia-prof` sur 1 320 manches reellement jouees
  (66,45 % de victoires pour la strategie "toujours l'intermediaire", sans lire l'enonce). La cause
  a ete trouvee dans la fabrique de contenu (`AdditionsBorneesContentSource`, pas dans le paquet
  YAML), corrigee par `marc-dev`, et revalidee le jour meme sur 12 000 manches reellement jouees a
  33,55 / 33,55 / 32,90. Aucun poteau n'a bouge : la barre de refus est restee celle de s01.
- **La meme famille de defaut - un garde-fou vert qui ne rencontre pas ou ne demande pas la bonne
  chose - est apparue sous trois formes distinctes ce sprint, chacune revelee par la precedente.**
  Le perimetre enumere (identifie en s11) a mene au critere enumere (`ADR-0039`, une liste de refus
  laisse passer un objet non nomme), qui a mene a l'echantillon trop court (`marc-dev`, 50 tirages a
  une graine trop peu pour atteindre un cas rare). Une chaine causale, pas trois incidents isoles.
- **La variante humaine de la meme famille a ete trouvee dans une mesure vieille de douze sprints,
  et corrigee par celle-la meme qui l'avait ecrite.** Le releve de `nadia-prof` en s0 portait sur la
  position a l'ecran (battue uniformement avant rendu), pas sur le rang par valeur qu'il pretendait
  couvrir - une mesure qui, par construction, ne pouvait pas voir le biais qu'elle a fallu douze
  sprints pour trouver.
- **`nadia-prof` a corrige six chiffres ce sprint, dont quatre des siens, toujours contre elle-meme
  et jamais en sa faveur.** Sa propre formule sur l'un d'eux : "je n'ai pas 0,1 point d'ecart avec
  lui, c'est moi qui l'avais avec la verite." Elle a aussi change sa propre condition P-6 (perimetre)
  en cours de sprint, sur un motif qu'elle nomme elle-meme : "c'est en croyant fermer par une liste
  que j'ai ouvert la breche" - la meme lecon qu'`ADR-0039` tire du code, retrouvee independamment
  dans une condition pedagogique.
- **`yanis-qa` a cherche d'autres releves qui mesurent autre chose que ce qu'ils annoncent, sans se
  contenter d'une seule instance trouvee**, et a nomme lui-meme la limite de sa propre recherche
  (aucun marqueur obligatoire pour cette classe de defaut, contrairement au critere enumere) plutot
  que de laisser croire a une couverture exhaustive.
- **`theo-archi` a mesure, avant de trancher, l'etat le plus dangereux que l'ecran de confirmation
  pouvait prendre** (`ADR-0040`, mutation M2 : Entree seule, sans tabulation, detruisait la partie
  et passait la CI verte) plutot que de se fier a la lecture qui lui avait ete transmise.

## Ce qui a coince

### 1. Aucune ceremonie de planning ecrite pour le sprint 12

Aucun fichier `planning.md` n'existe dans ce dossier - quatrieme sprint sur douze dans ce cas (s02,
s04, s10, s12). Je ne fabrique pas ce fichier retroactivement : une ceremonie sans fichier n'a pas
eu lieu, je le constate ici plutot que de le maquiller. Consequence directe : `review.md` ne peut
pas porter de duree effective, et `outils/rapport-tokens.py --sprint s12` echoue pour la meme
raison que pour s10 (voir `RAPPORT.md`, section tokens).

### 2. `confirmation-archunit.md` absent pour la quatrieme fois d'affilee (s09, s10, s11, s12)

`ADR-0013` en fait le lieu ou `theo-archi` tient la discipline de comptage des regles ArchUnit a
chaque cloture. Le fond n'est pas perdu - `ADR-0039` et `ADR-0040` portent chacun leur propre
confirmation de compte (28 regles, inchange), et je les reverifie moi-meme dans `review.md` - mais
c'est la quatrieme ceremonie de forme differente qui ne produit pas le fichier que sa propre
decision nomme. Je l'avais repris a mon compte a une cloture precedente ; je ne l'ai pas fait
produire davantage cette fois. Je le note sans fabriquer le fichier apres coup.

### 3. Le tiret cadratin des quatre referentiels de competences etait rompu depuis douze sprints, sans que personne ne l'ait vu

`squad/programme/francais/` et `squad/programme/mathematiques/` portaient un tiret cadratin dans
leurs titres et certains intitules, alors que toute citation litterale de ces intitules ailleurs
dans le depot (matrice, `DEC`, fiches, backlog, paquets YAML) employait deja un tiret simple depuis
le premier sprint qui les cite. La correspondance etait donc rompue depuis douze sprints sans
qu'aucune relecture, aucun garde-fou et aucun agent ne le remarque avant `f8d6a93`. Aucun garde-fou
de ce depot ne compare un referentiel a ses propres citations ; ce n'est pas une regle que je
propose d'ecrire ici, mais un angle mort a nommer.

### 4. Quatorze worktrees de sprint sur le disque a l'ouverture de cette cloture

Meme constat qu'aux trois cloture precedentes, avec un compte qui ne baisse pas dans le temps : 18
en s09, cinq en s10, neuf en s11, quatorze en s12. Toutes les quatorze branches etaient des ancetres
verifies de `HEAD` : nettoyees a cette cloture (casquette developpeuse), sans perte. Je ne propose
pas de regle nouvelle pour la quatrieme fois consecutive - un nettoyage systematique a chaque
fusion de lot, plutot que groupe a la cloture, romprait le rythme des developpeurs sans qu'aucun
risque reel ne l'ait justifie a ce jour - mais le chiffre ne baisse pas et merite d'etre surveille
une cinquieme fois avant de conclure qu'aucune regle n'est necessaire.

### 5. Un obstacle de methode rapporte, non trace sur le depot

Il m'est rapporte que le `relecteur-pedagogique` a d'abord valide ce sprint sur un JDK Temurin 25 en
croyant le JDK 21 absent du poste, avant de rejouer et de corriger lui-meme son rapport une fois
`outils/verifier-jdk.sh` consulte. Comme pour l'absence de fichier de relecture dedie a plusieurs
sprints consecutifs, je ne peux pas l'etablir moi-meme depuis le depot : aucun commit ni fichier ne
porte cette correction. Ce n'est pas une faute - il s'est corrige lui-meme avant que quiconque
d'autre ne le fasse - mais c'est un obstacle de methode qui aurait merite une trace ecrite, au meme
titre que la mesure JDK que je documente moi-meme dans `review.md`.

### 6. Deux paquets tiennent sur un seul item d'ecart

`cp-soustractions` (12/24) et `cp-problemes-additifs` (11/22) sont exactement a 50,0 % sur le
premier garde-fou de rang, et ne passent que parce que la comparaison est stricte (`>`, pas `>=`).
`nadia-prof` les juge deja justes et ne demande pas de les deplacer ; `yanis-qa` a rejoue le calcul
depuis le code de production et confirme les deux chiffres a la decimale. Ce n'est pas un defaut
a corriger, mais une marge a connaitre : un retrait ou un ajout d'un seul item sur l'un des deux,
pour une raison sans rapport avec cette propriete, ferait basculer silencieusement le verdict du
garde-fou.

## Amendements a `squad/README.md`

**Un amendement, ecrit dans le meme geste que `review.md` de cette cloture** (section 9, etape 4,
apres le paragraphe sur le critere enumere de s11) : le rapport de cloture de `yanis-qa` nomme
desormais explicitement, a chaque verdict qui s'appuie sur un releve automatique cite comme preuve,
la propriete que ce releve peut structurellement voir et celle qu'il ne peut pas - proposition de
`yanis-qa`, acceptee, sur le meme modele que son amendement de s11 pour le critere enumere. Motif :
deux instances de la meme famille de defaut ("une mesure creditee pour une propriete qu'elle ne
peut pas voir") trouvees dans ce seul depot, l'une vieille de douze sprints.

## Decisions prises

1. **Les deux derniers lots du sprint (`yanis-qa`, `marc-dev`) sont fusionnes** apres verification
   de lignee, sans conflit.
2. **`mvn -B clean verify` est rejoue independamment sur l'arbre fusionne final**, apres avoir
   diagnostique et contourne un JDK 8 par defaut sur le `PATH` : 464 tests, 28 regles, garde-fou de
   chemins en code 0, memes chiffres et memes methodes que `yanis-qa` et `theo-archi`.
3. **La proposition de `yanis-qa` (nommer ce qu'un releve automatique ne peut pas voir) est
   acceptee** et actee dans `squad/README.md`, section 9.
4. **Aucun `planning.md` n'est fabrique retroactivement pour s12** : son absence est constatee
   ci-dessus, pas maquillee. Consequence actee : le rapport de tokens ne peut pas etre produit pour
   ce sprint (`RAPPORT.md`).
5. **Quatorze worktrees et quatorze branches de sprint sont nettoyes** (casquette developpeuse),
   chacun verifie ancetre de `HEAD` avant suppression.
6. **`confirmation-archunit.md` n'est pas fabrique a la place de `theo-archi`** pour la quatrieme
   fois d'affilee : le contenu qu'il devrait porter est reconstruit par moi dans `review.md`, a
   partir d'`ADR-0039` et `ADR-0040` eux-memes, mais le fichier que l'ADR nomme reste absent.
7. **Le recit non trace du `relecteur-pedagogique` sur son propre JDK est consigne comme une
   annonce rapportee**, pas comme un fait etabli par le depot, et route comme obstacle de methode
   pour s13.
