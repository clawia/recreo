# Fiche de conformite - bandit-manchot-rapprocher-deux-oeuvres-cp

- **Mecanique** : bandit manchot, propositions en image (`ADR-0104`).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-rapprocher-deux-oeuvres.yaml`
  (6 items, type `question`), ecrit par `lea-dev` (`25f0c1ee`) depuis `CHOIX-OEUVRES-s56-art-cp-10.md`.
- **Entree de catalogue** : `bandit-manchot-rapprocher-deux-oeuvres-cp` (`f37e33dd`), titre servi *"Des œuvres
  faites de la même façon"*, consigne servie *"Regarde l'œuvre, puis trouve celle qui est faite de la même façon."*,
  `nombreDeManches: 5`.
- **Ligne declaree** : `ART-CP-10`. **Stories** : `S55-ART-CP-10-PAQUET`, `S56-AUD-ART-CP-10`. **Decision** :
  `DEC-0116`.
- **Competence programme visee** : `squad/programme/arts-plastiques/competences-cp.md`, `ART-CP-10`, *"Reconnaître
  quelques œuvres déjà rencontrées et les rapprocher les unes des autres."*
- **Conditions opposables** : regles 1 a 6, `M1` a `M6`, `X1` a `X7` (`ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md`,
  sections 3.2 a 3.4) ; `B1` a `B14` (`ARBITRAGE-PREALABLE-s56-instance-b-art-cp-10-et-emc-cm1.md`) ; `N1` a `N6`, `Q2` a
  `Q6`, la condition bloquante de `Q3` et les criteres 1 a 8 de la marque (`ARBITRAGE-s56-forme-art-cp-10.md`, blocs du
  2026-10-09 a 21:37 et 22:07).
- **Statut global** : **REFUSE**, prononce le 2026-10-11 par `nadia-prof` (s56), apres avoir joue le jar servi. Rejeu du
  2026-10-15 (s57, instance B) sur `ac9ae080` : refus leve, `RDO-CP-R1` et `RDO-CP-R2` levees, reserve neuve `RDO-CP-R3` ;
  statut **VALIDE SOUS RESERVE**.
- **Derniere mise a jour** : 2026-10-15.

> **Fiche ouverte par `nadia-prof`** : `camille-po` n'en avait pas ouvert (`ls squad/conformite` suivi de
> `grep -c rapprocher` rendait 0 a `a6ca1751`). Les lignes d'en-tete descriptives sont reprises du catalogue et du
> paquet ; `camille-po` les complete si elle le veut. Le bloc de validation est le mien.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-11 (`nadia-prof`, s56) : **REFUSE**

**Motif, actionnable.** A 744 x 551 (`DEC-0040`), **a la revelation, la phrase de `M4`** (*"C'est cette œuvre qui va
avec. Appuie dessus."*) **est entierement sous le pli** : sa boite va de y = 626 a y = 656 (bas de la derniere ligne de
texte a 654), le pli est a 551, sans aucun defilement (`scrollY` = 0). **Il manque au moins 105 px** (656 - 551) pour
qu'elle soit entiere au-dessus du pli. Meme position sur les six items et sur les quatre parties jouees jusqu'a la
revelation. La barre est la mienne, ecrite **avant** l'entree de catalogue : *"Bloquant a l'audit : a 744 x 551, la
reference, les trois cases, les croix (echec) ou l'anneau (revelation), et la phrase de `M4` sont au-dessus du pli"*
(`ARBITRAGE-s56-forme-art-cp-10.md`, bloc du 2026-10-09, 21:37, `Q3`). **Je ne la deplace pas, ni vers le haut ni vers
le bas.** Ce qui la remplit est tenu : la reference, les trois cases, les deux croix et l'anneau sont au-dessus du
pli. Seule la phrase manque.

**Ce qui leve le refus** : a 744 x 551, a la revelation, la boite de `.reponse-revelee` finit au-dessus de y = 551 sur
les six items, et la reference, les trois cases, les croix et l'anneau restent au-dessus du pli ; `N1` a `N6` restent
tenues. **La facon de gagner ces 105 px n'est pas a moi** (forme de retrait du meuble de `theo-archi`, place de la
pastille, place du bloc de message : `theo-archi`, `ines-design`, `lea-dev`). Rejeu au tarif de `PAR-10` (0,5).

**Correction de mon propre constat, avant de m'en servir.** Le meme bloc de 21:37 ecrit, sous la condition :
*"Mesure : tenu avec O1 et le meuble."* **C'est faux, et ce n'etait pas une mesure de la phrase.** J'ai lu le bas du
contenu de revelation du vehicule statique d'`ines-design` (622, puis 662 avec O1) comme s'il disait ou commence la
phrase ; sur le jar, la revelation porte le verdict, la phrase, l'explication et l'indice, et le document descend a
796. `lea-dev` l'a releve a l'oeil (y = 642 environ, `rapport-lea-dev.md` 11.3) ; ma mesure au navigateur le confirme
(626 a 656, le milieu de la ligne est a 641). Le defaut d'aiguillage est le mien : l'entree de catalogue a ete
ecrite contre une barre que mon propre texte declarait deja tenue.

#### 1. Ce que j'ai fait

**Audit joue, sur le jar.** Worktree `s56-nadia-art` a `a6ca1751` (`round-two`, qui contient `25f0c1ee`, `1f0fe8e4`,
`696f4f20`, `f37e33dd` et `a44a9d5b`). `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl` :
**BUILD SUCCESS**, 0 echec, 0 erreur sur **2386 tests** (domain 120, application 18, adapter-web 442, adapter-content
1461, bootstrap 339, integration 6), toolchain JDK 21.0.10, 5 min 51 s. **C'est le seul job de `ci.yml` que j'ai lance
pour jouer** ; les autres sont nommes au commit. Jar `bootstrap/target/recreo.jar` lance sur JDK 21.0.10, port dedie,
arrete par son PID, port ferme ensuite.

**Joue au navigateur, par la page complete servie (htmx compris)** : Chrome sans tete, pilote par son port de
deverminage depuis un script de poste hors depot qui reprend la classe `Navigateur` de
`squad/outils/nadia-prof/balayage-largeur-rangee.py` (profil propre, ferme par son processus). Chaque appui est un
`click()` sur le bouton de l'oeuvre ; la lecture attend l'evenement `htmx:afterSettle`. **Mesures** : viewport lu au
navigateur (`[innerWidth, innerHeight]` rend `[744, 551]`), `getBoundingClientRect().top + scrollY` et `.height` sur
`.illustration img`, chaque `.proposition` et son `img`, `.verdict`, `.reponse-revelee`, `.explication`, `.indice` ;
bas de la derniere ligne de texte par `Range.getClientRects()` ; pastille lue par `getComputedStyle(bouton, '::before')`.

| Conduite | Parties | Largeur x hauteur | Ce qu'elle couvre |
|---|---|---|---|
| deux erreurs, la premiere repetee, puis la bonne (revelation) | 4 | 744 x 551 | 20 manches, **les six items reveles au moins une fois** |
| deux erreurs puis la bonne | 1 | 744 x 551 | les deux croix, puis *"Bravo !"* |
| juste du premier coup | 1 | 744 x 551 | ecran de fin d'une partie reussie |
| clavier : Tab jusqu'aux cases, Entree sur un distracteur, Tab, Maj+Tab | 1 | 744 x 551 | l'anneau de focus contre la croix et l'anneau de revelation |
| revelation | 1 | 1024 x 768 | la meme phrase hors du plus petit ecran |

**Limites, nommees.** Capture a densite 1, ecran de poste, aucun doigt ni survol reel : ce que je dis de la lisibilite
de la croix et des oeuvres est un jugement d'adulte sur ces captures (`squad/README.md`, section 4, amendement de
s15). Aucun enfant n'a joue.

#### 2. Ancrage au programme

Le jeu travaille **cette** ligne. L'enfant rapproche deux oeuvres par la facon dont elles sont faites (la touche, la
maniere de dessiner l'eau, la lumiere d'une fenetre), contre un distracteur qui partage le sujet sans la propriete.
*"Déjà rencontrées"* : 7 des 11 oeuvres proposees sont aussi servies par `cp-decrire-une-oeuvre`
(`grep -o "oeuvre-[a-z-]*"` sur son fichier : chambre, femme au luth, grande vague, marche aux chevaux, moissonneurs,
quatre saisons, rue sous la pluie), dont trois des six references.

#### 3. Les cinq points de l'agent lanceur

**(1) La croix a 40 px, pale contre sombre, focus et revelation : tenu.** Mesure sur le jar : la pastille fait 40 x 40,
de y = 486 a 526, sous la case (cases de 338 a 482, inchangees avant et apres l'erreur, `N6`), sur le fond du meuble,
jamais sur l'image (`N1`). Elle a le meme rendu sous une reproduction pale (moissonneurs, rue sous la pluie) et sous
une sombre (femme au luth, jeune femme a l'aiguiere, quatre saisons, autoportrait de Leyster) : elle ne depend pas de
l'image (`N2`). Je la reconnais a 40 px comme la croix rouge du signal faux affiche sous le meuble ; elle dit
*"pas celle-la"*. Opacite de l'image essayee a 0,5 en plus (`N4`). Apres la deuxieme erreur, deux croix ; a la
revelation, elles restent et la bonne oeuvre porte l'anneau sans croix (`N5`). **Pas de confusion de forme** (`N3`) :
l'anneau de focus clavier est un trait fin pose au bord de la case blanche et n'apparait qu'au clavier
(`:focus-visible`) ; l'anneau de revelation est un trait epais d'encre qui serre l'image ; la croix est un signe sous
la case. Trois formes, trois places.

**(2) La phrase `M4` : refus**, motif ci-dessus. **Le releve `Q7` d'`ines-design` est arrive pendant que j'ecrivais
ce bloc** (`squad/conformite/releves-s56/Q7-releve-gabarit-livre.md`, `b2136295`, fusionne par `e2c4ea32`, que j'ai
fusionne dans ma branche avant de commiter ; cette fusion n'apporte que des tests et ce releve, aucun fichier servi :
`git diff --stat a6ca1751 e2c4ea32` ne touche rien sous `src/main/`). Il mesure par `getBoundingClientRect()`, sur le
jar, les six items, et **rend a 744 x 551 les memes cotes que les miennes** : `M4` 626 - 656, verdict 567 - 597, indice
626 - 676 a l'echec, croix 484 - 524. Deux mesures independantes, meme chiffre : le motif ne repose pas sur la mienne
seule. Il ajoute trois faits que je n'avais pas mesures :

- **a 992 x 551**, `M4` est a 670 - 700 et **la croix est coupee par le pli** (528 - 568 : 23 px visibles sur 40). Ma
  condition bloquante est ecrite pour 744 x 551 et je ne l'etends pas apres coup ; ce fait entre dans `RDO-CP-R1`,
  date d'aujourd'hui ;
- a 390 x 551, les cases sont sous le pli des la manche fraiche : disposition empilee, que je n'exige pas (`DEC-0040`,
  `Q3`) ;
- a 744, avec une barre de defilement peinte, **les cases se decalent de 7,5 px** quand le document depasse la
  fenetre apres l'erreur. Ce n'est pas la marque qui les deplace (`N6` vise ce que la marque fait bouger), c'est
  l'apparition du defilement, commune a tout jeu dont le message pousse le document sous le pli ; mes captures, sans
  barre peinte, ne le montrent pas. Observation, sans reserve.

**(3) Les trois lectures de `rapport-lea-dev.md` 11.2 : confirmees toutes les trois.**

1. **Critere 4, anneau et disque : confirmee.** Le critere voulait dire *"la marque se voit contre ce qui l'entoure"*.
   La pastille a deux bords possibles, le disque clair et l'anneau d'encre ; celui que l'oeil suit est celui qui
   contraste. Sur le meuble, c'est le disque (16,11:1) ; sur la page claire, l'anneau (14,09:1). Lire *"l'anneau
   seul"* refuserait une marque que je vois nettement sur le meuble (1,14:1 pour un trait qu'on ne voit pas, pendant
   que le disque tranche). Le meilleur des deux contre chaque entourage, c'est ce que j'ai voulu ecrire.
2. **Critere 1, le `:has(...)` de la rangee : confirmee.** La regle de la rangee est la clause (c) du critere 7 (elle
   deplace ce qui est sous la rangee) ; elle n'est pas la marque. Un critere 1 rempli par elle laisserait passer
   l'absence de croix (mutation E1 verte) : la lecture stricte est la bonne.
3. **Critere 6, deux distracteurs : confirmee.** Avec trois propositions, *"deux erreurs puis la revelation"* ne se
   joue qu'en repetant une erreur. C'est la conduite que j'ai jouee sur le jar : apres D1, D2, D1, les deux oeuvres
   essayees portent `deja-essayee`, la bonne porte `data-oeuvre-revelee="true"` et pas `deja-essayee`, sur les six
   items.

**(4) `B6`, objet en volume : tenu, et je reste la seule garde.** Les douze propositions des six items designent onze
oeuvres (champ de ble, chambre, moissonneurs, jeune femme a l'aiguiere, femme au luth, averse sur le pont, grande
vague, rue sous la pluie, quatre saisons, marche aux chevaux, autoportrait de Leyster) : peintures et estampes,
regardees une a une. `oeuvre-hippopotame` n'est proposee nulle part (lecture du fichier). Le refus de test de
`theo-archi` (avis de s56, amendement du 2026-10-11) a un motif que j'accepte : le support n'est dans aucune donnee.
**Tant qu'il n'y a pas de champ de support, c'est moi qui garde `B6`, a chaque rejeu.** Observation sans reserve : la
reproduction du *Marche aux chevaux* montre son cadre dore ; c'est un distracteur, le cadre ne designe pas la reponse.

**(5) L'amendement d'`ADR-0104` et les deux exclusions : ratifies par `theo-archi`, pas par moi.** Je n'en juge que
ce que l'enfant voit, et j'y ai trouve un defaut que ces garde-fous ne visaient pas : `RDO-CP-R2`.

#### 4. Les conditions du paquet, jouees

| Condition | Constat sur le jar |
|---|---|
| `M1` | avant tout geste, a 744 x 551 : reference, enonce et trois cases au-dessus du pli (hauteur du document 551). **Tenu** |
| `B1`, `B2`, `B8`, `B9`, `B10` | enonce, texte alternatif, indices, explications, titre et consigne servis identiques a `CHOIX-OEUVRES-s56-art-cp-10.md` ; aucun identifiant `oeuvre-` lu sur mes captures (le garde-fou 4 le juge dans le HTML). **Tenu** |
| `B3`, `B5`, `B7` | six items, references distinctes, couples dans les deux sens ; jamais la bonne oeuvre seule de son format (lu sur les tailles servies : en hauteur 93 a 120 x 134, en largeur 134 x 100 a 107). **Tenu** |
| Regle 2 | sur chaque item, un distracteur partage le sujet sans la propriete (moissonneurs ; rue sous la pluie ; autoportrait de Leyster). **Tenu** |
| Regles 1 et 6, `Q8` | a 134 px, la touche se voit sur le champ de ble (le ciel) et sur la chambre (les stries du plancher) ; les traits fins de l'eau sur les deux estampes, a 93 et a 134 px ; la fenetre a gauche sur la jeune femme a l'aiguiere. **La femme au luth est la plus faible** : la lumiere de la fenetre s'y lit a 117 et a 154 px, sans plus. **Tenu, de justesse, pour ce tableau** |
| Regle 5, `K1` | **les six explications lues a l'ecran a la revelation**, presentes et exactes : *"on voit chaque trait du pinceau"* (chambre, champ), *"l'eau est dessinée avec des traits fins"* (vague, averse), *"la lumière entre par une fenêtre, à gauche"* (luth, aiguiere). **Tenu** |
| `E2` | anneau d'encre sur la seule bonne oeuvre a la revelation, sans croix. **Tenu** |
| `X7` | aucune oeuvre a l'ecran de fin. **Tenu a la lettre** ; voir `RDO-CP-R2` |

Hors du plus petit ecran, a 1024 x 768 : la phrase de `M4` (670 a 700) et l'explication (704 a 754) sont au-dessus du
pli ; l'indice commence a 758.

#### 5. Reserves neuves, datees du 2026-10-11, non bloquantes

**`RDO-CP-R1` - a l'echec, a 744 x 551, ni le verdict ni l'indice ne sont au-dessus du pli.** Apres une erreur, le
verdict (*"Ce n'est pas ça. Essaie encore !"*) va de y = 567 a 597 et l'indice de 626 a 676 : l'enfant ne voit que la
croix. C'est la reserve que mon bloc de 21:37 annoncait (*"S'il l'est sur le jeu reel, c'est une reserve datee"*) ; ce
n'est pas une barre neuve. **Second fait, date d'aujourd'hui, du releve `Q7`** : a 992 x 551, la croix elle-meme est
coupee (528 - 568), alors que c'est le seul signal d'echec au-dessus du pli. **Ce qui la leve** : apres une erreur, a
744 x 551 la boite de `.indice` finit au-dessus de y = 551, et a 992 x 551 la pastille de chaque oeuvre essayee finit
au-dessus de y = 551, sur les six items. La correction du motif de refus peut la lever dans le meme geste.

**`RDO-CP-R2` - l'ecran de fin parle d'oeuvres qu'il ne montre pas.** Apres une derniere manche revelee, l'ecran de
fin affiche *"Tu n'as pas trouvé."*, puis l'enonce *"Quelle œuvre est faite de la même façon que celle-ci ?"* et
l'explication *"Dans ces deux œuvres, ..."* ; apres une derniere manche reussie, *"Bravo, c'est juste !"* et
l'explication. Aucune oeuvre n'est a l'ecran (`X7`, comme je l'ai voulu) : *"celle-ci"* et *"ces deux œuvres"* ne
designent rien. Ce n'est pas un durcissement d'`X7`, qui est tenue : c'est un defaut que je n'avais pas vu en
l'ecrivant. **Propriete a tenir** : sur l'ecran de fin d'un jeu a reponses en image, aucun texte ne designe une oeuvre
qui n'est pas a l'ecran (*"celle-ci"*, *"ces deux œuvres"*). La forme est a `theo-archi` et `ines-design`. Les deux
garde-fous ecartes par l'amendement d'`ADR-0104` n'en sont pas la cause et ne l'auraient pas vu ; **je suis seule
garde de cette propriete**.

#### 6. Ce dont je reste la seule garde sur ce jeu

Aucun automate ne juge : la phrase `M4`, l'indice et la croix au-dessus du pli (le releve `Q7` les mesure une fois, aucun
test ne les garde), `RDO-CP-R2`,
la regle 2 et le sujet de chaque oeuvre, la visibilite de chaque propriete a 93-176 px, `B6`, la reconnaissance de la
croix et sa distinction des deux anneaux, l'exactitude des explications, `X2`, `X3`, `X6`. L'equipe ne doit pas les
croire couvertes par une CI verte : celle de cet arbre l'est. **Ce qui ne depend plus de moi seule** : `B2`, `B3`, `B5`,
`B7` et `B8` (sans le sens de l'enonce) ont recu des garde-fous de `lea-dev`, fusionnes par `80c06e64` pendant mon audit ;
je ne les ai ni lus en detail ni mutes.

#### 7. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | Le motif de refus et `RDO-CP-R1`, `RDO-CP-R2` en stories, avec les chiffres et les proprietes de ce bloc, sans reformulation. **`ART-CP-10` reste `non couvert`** ; aucune mention de s50 a reproduire |
| `theo-archi`, `ines-design`, `lea-dev` | La cause des 105 px et de l'ecran de fin ; la forme est a eux |
| `ines-design` | `Q7` est rendu ; les sept largeurs non mesurees (section 8 de son releve) au rejeu, sur le gabarit corrige |

**Identifiants** : deux neufs, `RDO-CP-R1` et `RDO-CP-R2`. Forme copiee d'`ANG-CP-R1` : trois segments, niveau au
milieu, marqueur `R` dans le dernier (`ADR-0100`). Prefixe `RDO-CP` libre (`git grep -c "RDO-"` ne rendait rien).
Alloues dans `squad/decisions/REGISTRE-identifiants.md`, section 4, dans le meme diff. Aucun renomme.

**Tarif consomme** : audit 2,5 (premier passage, `S56-AUD-ART-CP-10`). Le poste est livre : un refus n'est pas un
report.

**Signe** : `nadia-prof`, s56, 2026-10-11.

---

### Rejeu du 2026-10-15 (`nadia-prof`, s57, palier 1, instance B) : **VALIDE SOUS RESERVE** ; refus leve, `RDO-CP-R1` et `RDO-CP-R2` levees, reserve neuve `RDO-CP-R3`

**Motif.** A 744 x 551, a la revelation, la boite de la phrase de `M4` va de y = 468 a y = 528 (bas du texte a 526) sur les six
items, avec et sans barre de defilement, et la reference (bas au plus a 328), les trois cases (260 a 404), les croix (406 a 446)
et l'anneau sont au-dessus du pli : **la barre du refus du 2026-10-11 est atteinte, je leve le refus**. Je ne la deplace pas.
Reste une reserve neuve, non bloquante : la premiere phrase de `M4`, insecable, deborde de sa colonne de 18,2 px de 720 a 768
et sort de l'ecran a 720 (`RDO-CP-R3`, section 6).

**Objet joue.** Branche `s57-nadia-rejeu-b2`, tete `ac9ae080`, issue de `s57-yanis-j5`. Le contenu servi est celui de `06c7804f`
(B2, `lea-dev`, retour de l'entree au catalogue) : `git diff --name-only 06c7804f ac9ae080 -- adapters domain application
bootstrap` ne rend aucune ligne (les commits entre les deux ne touchent que `outils/` et `squad/`). L'entree du catalogue est
identique, ligne a ligne, a celle d'avant le retrait (`diff` des six lignes sur `13d00bfe^` et sur `ac9ae080` : vide) ; le paquet
et les reproductions n'ont pas change depuis le refus (`git diff --stat 13d00bfe HEAD` sur `static/oeuvres` et
`cp-rapprocher-deux-oeuvres.yaml` : vide). **Commit joue, pour `R1` et `R2` d'`avis-faisabilite.md` 3.3 : `ac9ae080`.**

#### 1. Ce que j'ai fait

- **Build** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, sur `ac9ae080` : `BUILD SUCCESS`, 2597 tests
  (120, 31, 492, 1567, 381 et 6 d'integration, lignes `Tests run` de la sortie Maven), 0 echec, 0 erreur ; JDK 21.0.10
  (`java.version` des 60 rapports Surefire de `bootstrap`, une valeur).
- **Jar** : le `recreo.jar` de ce build, copie dans mon dossier de poste `s57-p1-nadia-prof-b` (hors depot) et lance sur le
  port 18513 (libre avant : `netstat -ano` filtre ne rendait rien). Journal : *"Tomcat started on port 18513"*, *"using Java
  21.0.10 with PID 38204"*. Arrete par ce PID seul ; le port ne figure plus parmi les ports a l'ecoute ensuite.
- **Joue au navigateur, par la page servie** (htmx compris) : script de poste hors depot, Chrome sans tete pilote par son
  port de deverminage (19213, `git grep 19213` ne rend rien), profil neuf dans mon dossier de poste. Chaque Chrome s'est ferme
  par son PID (53564, 53224, 21248) ; aucun processus de navigateur ne restait dont la ligne de commande nomme mon dossier.
  Aucun processus arrete par nom.
- **Conduites**, parties completes de cinq manches jusqu'a l'ecran de fin : tout reveler (une oeuvre, l'autre, la premiere
  repetee, puis la bonne revelee), juste du premier coup, une erreur puis la bonne.
- **Deux modes** : barre de defilement classique (Chrome par defaut sur ce poste, 15 px) et **sans barre peinte**
  (`--hide-scrollbars`, comme la barre superposee d'une tablette). Cinq largeurs, 720, 744, 768, 992 et 1200, hauteur 551.
- **Volume** : 126 parties, 630 manches, 649 etats d'echec mesures, 223 revelations, 126 ecrans de fin. **Les six items
  reveles, essayes a tort et joues justes a chaque largeur et dans chaque mode.**
- **Mesures** : `getBoundingClientRect()` plus `scrollY` ; bas de la derniere ligne de texte par `Range.getClientRects()` ;
  lignes et coupure par la position de chaque mot (methode de `coupure-poste-8.mjs`) ; `scrollWidth` et `clientWidth` du
  document. Captures regardees a 720 et 744 (manche fraiche, echec, revelation, ecran de fin).
- **Juge pur**, sur le releve de `lea-dev` (`releve-b2-jar.json`, `ebceb838`, tete mesuree `06c7804f`) : `J1` a `J5` OK,
  *"VERDICT DES COTES : tout passe"*, sortie 0 (sortie complete : `rejeu-nadia-b2-palier-1.md`, section 2). Mes cotes
  rendent les siennes a la meme largeur et au meme mode.

**Limites, nommees.** Une police, celle que le jar sert sur ce poste ; densite 1 ; aucun appareil de la cible, aucun doigt,
aucun enfant. Le clavier n'est pas rejoue : la feuille n'a change aucune regle de la marque, du focus ni de la revelation
depuis le refus (`git diff a6ca1751 HEAD` sur `recreo.css` ne nomme `focus` que dans `.bascule` et `.tuile`, de l'accueil) ;
mon constat `N3` de s56 vaut sans mesure neuve.

#### 2. Cotes, revelation et echec, six items

| Largeur | Mode | `M4` (boite ; bas du texte) | Texte de `M4` au-dela de sa boite | Au-dela de l'ecran | Indice a l'echec (bas) | Croix (bas) | Cases (haut a bas ; cote) |
|---|---|---|---|---|---|---|---|
| 720 | barre | 463 - 523 ; 521 | 33,2 px | **21,2 px**, defilement horizontal (726 pour 705) | 543 | 441 | 260 - 404 ; 144, puis 260 - 399 ; 139 apres l'erreur |
| 720 | sans barre | 468 - 528 ; 526 | 18,2 px | **6,2 px**, defilement horizontal (726 pour 720) | 548 | 446 | 260 - 404 ; 144 |
| 744 | barre | 468 - 528 ; 526 | 18,2 px | 1,7 px, defilement horizontal (731 pour 729) | 548 | 446 | 260 - 404 ; 144 |
| 744 | sans barre | 468 - 528 ; 526 | 18,2 px | non | 548 | 446 | 260 - 404 ; 144 |
| 768 | barre | 468 - 528 ; 526 | 18,2 px | non | 548 | 446 | 260 - 404 ; 144 |
| 768 | sans barre | 468 - 528 ; 526 | 18,2 px | non | 548 | 446 | 260 - 404 ; 144 |
| 992 | barre | 512 - 542 ; 540 | non | non | 562 | 490 | 260 - 448 ; 188 |
| 992 | sans barre | 512 - 542 ; 540 | non | non | 562 | 490 | 260 - 448 ; 188 |
| 1200 | barre | 464 - 494 ; 492 | non | non | 514 | 442 | 212 - 400 ; 188 |
| 1200 | sans barre | 464 - 494 ; 492 | non | non | 514 | 442 | 212 - 400 ; 188 |

Reference : bas au plus a 328 a toutes les largeurs. Le message commence au bas du meuble et tient entre ses deux bords a
toutes les largeurs (a 744 avec barre : 216,5 a 712,5 pour l'un et l'autre) : le veto de `reponse-nadia-forme-poste-8.md`
est tenu. A 720 avec barre, la croix est a 401 - 441 : la rangee est plus courte de 5 px.

#### 3. La barre du refus : atteinte

Les trois conditions de levee du 2026-10-11 : boite de `.reponse-revelee` au-dessus de y = 551 a 744 sur les six items (528) ;
reference, cases, croix et anneau au-dessus du pli (328, 404, 446, l'anneau sur la case de 260 a 404) ; `N1` a `N6` tenues
(section 5). **Refus leve.** Les 105 px manquants en ont ete gagnes 128 (656 en s56, 528 ici).

#### 4. `RDO-CP-R1` et `RDO-CP-R2` : levees

- **`RDO-CP-R1`.** A 744 x 551, apres une erreur, le bas de `.indice` est a 548 sur les six items, avec et sans barre ; a
  992 x 551, la croix de chaque oeuvre essayee finit a 490. **Levee.** La marge est de 3 px, sur une police : je l'ai ecrit le
  2026-10-15 (`reponse-nadia-forme2-palier-1.md` 2.2), ce n'est pas une garantie, et `J3` la garde sur un releve, pas sur
  le jar servi demain.
- **`RDO-CP-R2`.** Sur les 126 ecrans de fin : *"Tu as trouvé, tout seul :"*, le nombre, *"Tu as joué toutes les questions
  :"*, le nombre, *"Tu n'as pas trouvé."* ou *"Bravo, c'est juste !"*, *"C'est fini !"*. Aucune question rappelee, aucune
  explication, aucune image dans le fragment (la seule image de la page est le crayon de l'en-tete), aucun des attributs de
  `reponse-nadia-forme2-palier-1.md` 4.2. **Aucun texte ne designe une oeuvre absente : levee.**
  `LEcranDeFinDUnJeuAReponseEnImageNeRendAucunTexteDeLItemTest` (10 tests, verts dans mon build) la garde desormais ; je reste
  seule garde d'un texte pose par la feuille ou le JavaScript.

#### 5. Les conditions de forme, jouees

| Condition | Constat sur le jar |
|---|---|
| `L1` (bandeau et socle) | `display: none` sur cet ecran, dans tous les etats ; le meuble garde son cadre et son levier. Accepte sur cet ecran seul |
| `L4` (2.2 : *"je le regarde au rejeu"*) | l'enonce finit exactement ou le meuble commence (ecart 0 a toutes les largeurs). En gras, sur deux lignes, sur le fond clair, au-dessus du cadre sombre : il se lit comme la question, pas comme une piece du meuble. **Tenu** |
| Verdict, forme C (2.4) | a l'echec, *"Ce n'est pas ça. / Essaie encore !"* : deux lignes, coupure entre les deux phrases, dans sa boite, aux cinq largeurs et dans les deux modes ; *"Ce n'est pas ça."* a la revelation et *"Bravo !"* sur une ligne. `coupure-b2-jar.json`, variante `A` (texte servi) : 45 mesures, 0 coupure dans une phrase, 5 depassements, tous en Courier New, que la feuille ne sert pas (2.1). **Tenu** |
| Phrase de `M4` (2.3), coupure | *"C'est cette œuvre qui va avec. / Appuie dessus."* de 720 a 768, une ligne a 992 et 1200 : jamais coupee dans une phrase. **Tenu** |
| Phrase de `M4` (2.3), debordement | **non tenu** : section 6 |
| `N1`, `N2`, `N4`, `N5` | croix de 40 x 40 sous la case, sur le meuble ; les deux oeuvres essayees la gardent a la revelation, la bonne porte l'anneau sans croix. **Tenu** |
| `N6` | haut des cases a 260 (212 a 1200) dans tous les etats, a toutes les largeurs ; largeur constante entre etats de meme largeur utile. A 720 avec barre, voir `Q-T11` de `theo-archi` dans `rejeu-nadia-b2-palier-1.md`, section 4 : observation, pas un refus. **Tenu** |
| `B1` a `B10`, regle 2, `B6` | paquet inchange depuis le refus ; enonce et consigne servis identiques au catalogue et au paquet ; onze oeuvres, aucun objet en volume (`oeuvre-hippopotame` proposee nulle part). **Tenu** ; je reste seule garde de `B6` |
| Regle 5, `K1` | les trois explications servies a la revelation, identiques au paquet et exactes. A 744, l'explication commence a 532, sous la phrase de `M4` ; elle n'est pas une barre (`Q-R2`) |
| Regle 6 | a 720 avec barre, apres une erreur, la plus grande reproduction passe de 134 a 129 px de large : la touche et les traits fins restent ceux que j'ai vus a 93 - 134 px en s56 |

#### 6. Reserve neuve, datee du 2026-10-15, non bloquante

**`RDO-CP-R3` - la premiere phrase de `M4` deborde de sa colonne.** *"C'est cette œuvre qui va avec."* est insecable (forme C,
`ADR-0067`) et mesure 262 px. Sa colonne en a 244 de 744 a 768 (229 a 720 avec barre). Elle sort donc du meuble de 18,2 px
(33,2 a 720 avec barre). **A 720, elle sort de l'ecran** : 21,2 px avec barre, ou l'enfant lit *"qui va ave"* sans
defilement horizontal, et 6,2 px sans barre, ou le point final est coupe ; une barre de defilement horizontale apparait. **A
744 avec barre**, elle depasse la largeur utile de 1,7 px, et la barre horizontale apparait aussi. C'est la condition de forme
que j'ai ecrite le 2026-10-15 (`reponse-nadia-forme2-palier-1.md` 2.3, *"et le texte ne deborde pas de sa boite"*), dont
j'avais dit qu'une forme qui ne la tiendrait pas donnerait au rejeu une reserve neuve et non bloquante. Ce n'est pas la barre
du refus, qui est atteinte.

**J'avais ecrit que je trancherais si "jamais coupee" et "ne deborde pas" ne tenaient pas ensemble a 744 ; je tranche.**
Ne pas deborder l'emporte : un mot coupe par le bord de l'ecran, ou une page qui defile de cote, gene plus la lecture d'un
enfant de CP qu'un retour a la ligne dans une phrase. Une coupure dans la premiere phrase est acceptee si aucune forme ne
tient les deux.

**Ce qui la leve** : a 720, 744 et 768 x 551, a la revelation, sur les six items, avec une barre de defilement classique et
sans barre peinte, le bord droit de chaque mot de `.reponse-revelee` est au plus au bord droit de sa boite, et `scrollWidth`
du document egale `clientWidth`. La coupure reste entre les deux phrases, sauf si la forme ne peut pas tenir les deux.

**Voies, sans choix de ma part** : la largeur de la colonne du message ou du signal (`ines-design`, `theo-archi`) ; la
coupure dans la phrase, qui touche le garde-fou de l'amendement D.4 d'`ADR-0067` (`theo-archi`) ; une phrase plus courte, qui
est a moi et demanderait un rejeu. Rejeu au tarif de `PAR-10`.

#### 7. Observations, sans reserve

- **A 992 x 551, apres une erreur, le verdict finit a 576 et l'indice a 562** : la deuxieme ligne du verdict (*"Essaie
  encore !"*) est sous le pli ; la croix (450 - 490) et la premiere ligne sont au-dessus. Ma condition sur l'indice est ecrite
  pour 744 ; je ne l'etends pas apres coup.
- A 744, a la revelation, l'explication commence a 532 et le document descend a 758 : l'enfant voit la phrase de `M4` sans
  defiler, l'explication en partie.

#### 8. Ce dont je reste la seule garde sur ce jeu

Le debordement de la phrase de `M4` et le defilement horizontal (aucun juge ne compare un texte a sa boite : `J1` a `J5` ne
lisent que des boites, et le garde-fou de D.4 lit `wholeText()`, pas la geometrie) ; la coupure du verdict sur les ecrans de
designation et de saisie ; `B6` ; la regle 2 et le sujet de chaque oeuvre ; l'exactitude des explications ; la lisibilite de
l'enonce colle au meuble ; la distinction de la croix et des deux anneaux ; un texte de l'ecran de fin pose par la feuille ou
le JavaScript. **Ce qui ne depend plus de moi seule** : la barre du refus et `RDO-CP-R1` (`J2`, `J3`, sur un releve), le veto
du message (`J4`), `N6` sur un releve (`J5`), `RDO-CP-R2` (test de `D7`).

#### 9. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | `ART-CP-10` a `couvert-valide` **apres la fusion de B2**, `R1` et `R2` tenus ; `RDO-CP-R3` en story, avec les cotes de la section 2 et la levee de la section 6, sans reformulation ; l'observation de 992 (section 7), a elle |
| `sofia-sm` | la fusion de B2 sous `R1` et `R2` d'`avis-faisabilite.md` 3.3, commit joue `ac9ae080` (contenu servi de `06c7804f`) |
| `ines-design`, `theo-archi` | la forme de `RDO-CP-R3` |

**Identifiant** : un neuf, `RDO-CP-R3`. Forme copiee de `RDO-CP-R1` : trois segments, niveau au milieu, marqueur `R` dans le
dernier (`ADR-0100`). Prefixe `RDO-CP` deja alloue ; `git grep "RDO-CP-R3"` ne rendait rien avant d'ecrire. Alloue dans
`squad/decisions/REGISTRE-identifiants.md`, section 4, dans le meme diff.

**Tarif consomme** : rejeu 0,5 (poste 8, instance B, `engagement-poste-8.md`). Aucun prix neuf.

**Signe** : `nadia-prof`, s57, palier 1, instance B, 2026-10-15.
