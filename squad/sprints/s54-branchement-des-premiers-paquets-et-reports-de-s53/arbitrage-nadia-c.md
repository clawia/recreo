# Arbitrage prealable de s54, instance C - `cp-arts-oeuvres-familles` (`ART-CP-10`, arts plastiques CP)

- **Auteure** : `nadia-prof`, instance C (`DEC-0114` A.10 et A.18). **Date** : 2026-10-08. **Sprint** : s54, phase 1.
- **Arbre** : branche `s54-nadia-c`, issue de `round-two` a `95214218`. Le paquet est lu a ce sha ; `s54-lea-art` ne le modifie pas encore
  (`git diff --stat round-two...s54-lea-art` : vide).
- **Ce qui m'est pose** : `DEC-0114` A.6, A.11, A.12, A.13 et A.19, points 1 a 5 ; planning de s54, `Q3`.
- **Ce document ne prononce aucun Done.** Le paquet n'est pas branche : je n'ai rien joue. **Audit degrade pour tout ce qui suit, et je le declare** :
  j'ai lu les douze items du YAML, pas un ecran. L'audit se fera sur le jar construit apres `S54-BR-ART-CP-10`.
- **Aucun identifiant neuf n'est ecrit ici** : les points de la section 6 sont numerotes dans ce document, pas alloues.

## Resume

| Question | Reponse |
|---|---|
| (a) Plafond | **Rien sur `ART-CP-10`**, ni `couvert-valide` ni `en cours`. Je confirme la ligne deja ecrite par l'instance A ; je n'en ecris pas une seconde (section 1) |
| (b) Tarif | **Confirme** : audit 1,0, rejeu 0,5, plafond 0,0, ce document 0,0. Instance C : 1,5 |
| (c) Ouverture de C | **Oui, a mes conditions, et pas comme une borne a trois instances** (section 3) |
| (d) Terrain neuf | **Non** (section 4) |
| (e) Faits de l'en-tete | Confirmes sauf trois, corriges en section 5 |
| (f) Jouable tel quel | **Jouable, pas tel quel a ma porte** : six corrections decidees avant branchement (section 6) |

## 1. (a) Le plafond

La ligne de l'instance A (`squad/conformite/ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md`, section 9, `Q6`, commit `a3819bcd` du 2026-10-07),
reprise mot pour mot :

> *`cp-arts-oeuvres-familles`, branche tel quel, laisse `ART-CP-10` a `non couvert` : il ne sert ni la reconnaissance ni le rapprochement d'oeuvres
> (son en-tete le dit), il sert le vocabulaire des familles, qui n'est pas la ligne, et aucune oeuvre ne peut y etre designee en texte sans contrefacon (`CON-1`).*

**Je la confirme apres lecture des douze items.** L'instance A l'avait ecrite sur l'en-tete seul ; la lecture ne la change pas. Ecrire un second plafond ici
serait deux instances qui ecrivent la meme decision : la forme (c). Le critere 6 de `S54-BR-ART-CP-10` (plafond avant l'arbitrage de C) est tenu par ce commit.

**Une precision, pas une correction.** Ce que le paquet sert n'est pas seulement "le vocabulaire des familles". Les items 5 a 9 (outils et matieres : pinceau,
crayon, appareil photo, pierre, argile) sont le terrain d'`ART-CP-11`, deja `couvert-valide` depuis s39 (`cp-arts-outils`). L'item 9 de ce paquet est presque
l'item 6 de `cp-arts-outils` (*l'argile* pour modeler une petite sculpture). Les items 1 a 4 et 10 a 12 (familles, metiers, lieu) ne correspondent a aucune ligne
de mon referentiel.

**Pourquoi la ligne ne ferme pas.** `ART-CP-10` demande que l'enfant voie deux oeuvres ensemble et les rapproche sur ce qu'il voit, et qu'il reconnaisse des
oeuvres deja rencontrees (`ARBITRAGE-PREALABLE-s50-art-cp.md`, Q1 et Q5). Ce paquet ne montre aucune oeuvre. Un VALIDE a l'audit dira que le paquet peut rester
au catalogue ; il ne bougera pas la matrice.

## 2. (b) Le tarif

| Poste | Tarif | Motif |
|---|---|---|
| Audit de `cp-arts-oeuvres-familles` | **1,0** | premier passage d'un paquet jamais audite : le prix est celui du lot, pas de la ligne (s50, Q1). Un paquet qui ne ferme rien ne coute pas moins a jouer |
| Rejeu en cas de refus | **0,5** | `PAR-10`. Un seul rejeu : un second refus laisse le paquet hors catalogue, dit comme tel a la review |
| Ligne de plafond | **0,0** | deja ecrite par A |
| Ce document | **0,0** | il ne fait que lire ; les corrections de la section 6 sont jugees dans le 1,0 de l'audit |
| **Instance C** | **1,5** | |

## 3. (c) L'ouverture de C : oui, a mes conditions

### 3.1 Les conditions (i) a (iii) ne sont pas reunies, et je ne derive aucune borne

Ces trois conditions disent ce qui me ferait **rouvrir la derivation d'une borne a trois instances** (`ARBITRAGE-PREALABLE-s46...` section 5). Lues sur s54 :

1. **(i) non etablie.** Sous `PAR-1` et `PAR-2`, une troisieme instance ne prend rien du francais CE1 (A) ni des sciences et de l'EMC (B). Il reste G3 et G4,
   qui ne ferment rien, et G7 a G11, sans plafond ecrit. Je ne les compte pas comme un vivier instruit (s51, section 4 : l'excedent prenable, pas l'excedent brut).
2. **(ii) non verifiee.** Le dernier sprint parallele clos est s52. Sa verification contre les formes (a), (b) et (c) m'est routee depuis la cloture de s52
   (`review.md` de s52, obstacle 16 ; `review.md` de s53, obstacle 15, "Ouvert"). Je ne l'ai pas faite. Je ne la presume pas remplie.
3. **(iii) tenue** (section 4).

**Conclusion : aucune borne a trois instances n'est derivee, et 9,5 n'en est pas une.** Ma section 5 de s46 reste telle quelle pour la prochaine derivation.

### 3.2 Ce qu'est C, et pourquoi elle tient quand meme

C n'est pas une capacite de plus. C'est **le prix d'un branchement que l'utilisatrice a decide** (`Q6`). Sans elle, le paquet est au catalogue sans verdict : la
situation que `DEC-0009` a coutee a s04. Sur ce point, le motif de la condition (i) ("une troisieme instance ne financerait aucune ligne") ne s'applique pas :
C ne finance aucune ligne, et elle n'est pas ouverte pour cela.

**Je tiens 9,5 pour la borne opposable de s54, et de s54 seul**, comme l'ecart que `PAR-9` prevoit ("ou elle ecrit l'ecart et son motif"), pas comme un
amendement. `PAR-8` (8,0 au total, 5,0 et trois lots par instance) et `PAR-9` restent ecrites telles quelles. Je rejoins `DEC-0114` A.20 : aucun precedent pour s55.

**Le motif de (ii) est la couture entre instances. C en a peu** : un lot ; une matiere que personne d'autre ne porte (`PAR-2`) ; worktree, branche et
scratchpad dedies (A.14) ; elle n'ecrit que ce document et sa section de la fiche ; elle n'alloue aucun identifiant. **Le seul risque de couture que j'ai
trouve est deja sous mes yeux** : le plafond ecrit par A. Je l'ai ferme en section 1, par confirmation et non par une seconde ecriture.

### 3.3 La barre de 4,0 de `DEC-0112` 5.1

**Cette barre n'existe pas comme plancher d'instance.** L'instance B de s54 l'a corrige (`ARBITRAGE-PREALABLE-s54-instance-b-sciences-emc-cm1.md`, section 1.1) :
ma condition (i) est une condition pour deriver une troisieme instance, pas une charge minimale. C'est ma phrase de s52 qui a invite la lecture de `DEC-0112`, et
je confirme la correction. **C, a 1,5, n'est sous aucune barre que j'aie ecrite.** La question de `DEC-0112` 5.1 a l'utilisatrice n'a donc pas a lui etre reposee.

### 3.4 Mes conditions

1. C porte ce seul lot. Tout ajout passe par un nouvel ecart, ecrit.
2. Si s54 se clot avec une occurrence des formes (a), (b) ou (c) dans laquelle C est en cause, elle compte contre la condition (ii) a la prochaine derivation.
3. La verification de s52 contre les trois formes reste due par moi. Ce document ne la remplace pas.

## 4. (d) Terrain neuf : non

Arts plastiques CP compte trois lignes `couvert-valide` (`ART-CP-05`, `ART-CP-09`, `ART-CP-11`). `ART-1`, `ART-2` et `CON-1` ont mordu du contenu reel a s39
(`cp-arts-outils`) et a s50 (`cp-decrire-une-oeuvre`, refuse deux fois puis valide). Le paquet a la forme de `cp-arts-outils` : question, trois propositions en
texte, sans image. **`PAR-13` est tenue pour s54** : A ne lit pas le francais CE1 comme un terrain neuf, B n'en porte aucun, C non plus.

## 5. (e) Les faits de l'en-tete

| Fait de l'en-tete | Verdict | Methode |
|---|---|---|
| Aucune image, aucune vue de deux oeuvres | **confirme** | aucun champ d'image dans les douze items |
| Aucune oeuvre nommee, aucun item ne suppose une oeuvre deja vue | **confirme** | lecture des douze items |
| `ART-1` : aucun mot de couleur ; `ART-2` : aucun gout | **confirme** | recherche de mots de couleur et de gout : seul l'en-tete en contient |
| Douze items | **confirme** | `grep -c bonneReponse` : 12 |
| *"connaissances courantes du vocabulaire de CP"* (`HIS-3`) | **confirme, sauf les items 9 et 11** | item 9 : une seconde reponse defendable ; item 11 : un indice inexact (section 6) |
| *"ce que le jeu verifie est la famille d'une oeuvre, par son metier, son outil ou sa facon d'etre vue"* | **corrige** | il verifie aussi des matieres (items 8 et 9) et un lieu (item 12). Surtout, **"tableau" et "photographie" ne sont jamais la bonne reponse** : seules "statue" (deux fois) et "dessin" le sont. Le vocabulaire des trois familles n'est donc servi qu'a moitie |
| Descripteur : *"Reconnaître quelques œuvres déjà rencontrées et les rapprocher [...] (famille d'œuvre, métier, outil)"* | **corrige** | la parenthese n'est pas dans mon referentiel ; elle prete a la ligne un perimetre qu'elle n'a pas. Le descripteur n'est pas affiche (aucun gabarit ne le lit), donc l'enfant ne le voit pas. Le titre et la consigne, eux, sont tenus par le critere 3 de la story |

## 6. (f) Jouable par un enfant de CP accompagne

**Jouable** : chaque enonce fait une phrase de 4 a 14 mots, apostrophe comptee comme separateur (`LON-1` et `LON-2` tenues), il n'y a ni image ni couleur, et la voix n'est pas la competence. L'adulte
est la, mais l'enfant lit seul ; la presence de l'adulte ne dispense pas de la lisibilite (`CLAUDE.md`).

**Pas tel quel a ma porte.** Six defauts qu'un enfant verrait. Je les **decide comme corrections avant branchement** (critere `C2`), pour que l'audit ne les
rencontre pas. Le texte de remplacement est a `lea-dev` ; je juge le resultat a l'audit.

1. **Item 8, distracteur *"l'eau"*** : absurde, et l'enonce (*"matiere dure"*) l'ecarte deja, comme *"le papier"*. L'item devient une devinette. Il faut deux
   matieres qu'un enfant de CP associe a une statue ou a un objet fait a la main, et que seule la durete ecarte (exemple recevable : *"la pate a modeler"*).
2. **Item 9, distracteur *"le papier"*** : seconde reponse defendable. En classe, on modele avec du papier journal mouille (papier mache). Il faut un materiau
   qui ne se modele pas a la main (exemple recevable : *"le bois"*).
3. **Item 11** : il double l'item 1 (meme reponse, meme propriete : en faire le tour). Son indice est inexact : *"un devant, un derriere et deux cotes"*, un tableau
   les a aussi. Il faut un autre item. **Une piste qui comble le manque de la section 5** : un item dont la bonne reponse est *"un tableau"* (par exemple : une oeuvre
   peinte qu'on accroche au mur, avec *"une statue"* et *"une photographie"* en distracteurs).
4. **Item 12, distracteurs *"dans une boulangerie"*, *"dans un garage"*** : absurdes. Il faut deux lieux ou un enfant va pour regarder ou emprunter quelque
   chose, mais qui ne gardent pas d'oeuvres (exemple recevable : *"dans une bibliotheque"*, *"dans un zoo"*).
5. **Les items 7 et 10 se donnent leur reponse** : l'enonce 10 (*"faite sur une feuille avec un crayon"*) donne la reponse de l'item 7 (*"un crayon"*), et l'enonce 7
   (*"un dessin"*) celle de l'item 10. Sur dix manches tirees parmi douze items, les deux sortent presque toujours ensemble. L'un des deux enonces change.
6. **Trois indices donnent la reponse d'un autre item** : l'indice 2 (*"pinceaux"*) donne l'item 6 ; l'indice 3 (*"un appareil"*) donne l'item 5 ; l'indice 4
   (*"la pierre ou l'argile"*) donne les items 8 et 9. **Regle** : aucun enonce ni indice ne dit la relation qu'un autre item du paquet demande de trouver.

**Ce que je regarderai en jouant, sans en faire une condition** : *"sculpteur"* (p muet) et *"photographie"* sont-ils lus par un enfant de CP quand ils sont
proposes cote a cote ; la parente *photographie* / *photographe* (item 3) aide-t-elle l'enfant ou lui donne-t-elle la reponse sans rien apprendre ; *"des ciseaux"*
pour faire un tableau (item 6) est-il une seconde reponse defendable pour un enfant qui fait du collage en classe.

## 7. Ce dont je reste la seule garde sur ce paquet

Aucun test ne verifie : qu'un distracteur est plausible et faux ; qu'un indice ou un enonce ne donne pas la reponse d'un autre item ; que deux items ne se
doublent pas ; qu'un enfant de CP lit les propositions. Je les verifie en jouant, item par item.

## 8. Routage

| A qui | Quoi |
|---|---|
| `lea-dev` | Section 6, points 1 a 6, dans le diff de `S54-BR-ART-CP-10`, puis le test du paquet sur le texte corrige |
| `camille-po` | Section 5 : descripteur du paquet ; faut-il rattacher les items 5 a 9 a `ART-CP-11` (aucun effet sur la matrice) ? A toi de le dire. Hors de ce paquet : le bilan des arts plastiques CP de la matrice (*"Bilan corrige le 2026-10-07"*, `DEC-0113`) compte encore `ART-CP-10` en `non couvrable` alors que la ligne est revenue a `non couvert` |
| `sofia-sm` | Section 2, tarifs de C pour la review ; section 3.4 ; ordre de fusion inchange (branchement avant l'audit) |
| `nadia-prof`, instance C | L'audit, sur le jar construit apres la fusion de `S54-BR-ART-CP-10` |
