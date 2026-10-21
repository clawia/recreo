# s57 - palier 3 : verdict pedagogique des cinq lots CE1 (`nadia-prof`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-21, 13:46 (UTC+02:00), lu par `date`.
- **Arbre juge** : `round-two` a `8407f73f`. Porte technique etablie par le coordinateur au sommet `ad9ef86d` (verify,
  package, prefixes, chemins, outils ; 2854 tests) ; `8407f73f` n'ajoute que des fichiers de `squad/`. Je n'ai lance ni
  `mvn` ni aucun job : je n'annonce pas la porte, je la reprends.
- **Ce que j'ai lu et vu** : `releve-ecran-ines-palier-3.md` en entier (branche `s57-ines-r1`, commit `56ce720e`, dont le
  diff avec `8407f73f` ne touche ni `domain`, ni `application`, ni `adapters`, ni `bootstrap` : `git diff --stat` vide) ;
  45 de ses 133 captures, a l'outil de lecture d'image (liste en section 0). Contre-verification sur `8407f73f` : comptes
  `bonneReponse:` des cinq paquets (20, 20, 24, 22, 24), texte-support de `-06` 14, enonces d'EMC 8 et 9, enonces de
  `MAT-CE1-01` 15, 16, 19, zero *"a lieu"* dans `ce1-negation` : egaux au releve.
- **Audit en partie degrade, declare** : je n'ai pas lance le jar moi-meme. Le jeu est celui du pilote d'`ines-design`
  (110 items sur 110, arrivee, deux erreurs, revelation, bonne reponse), lu dans son texte DOM et dans ses captures. Ce que
  cela ne montre pas est en section 2.
- **Ce que ce fichier fait** : il prononce quatre Done et un refus, et ouvre les quatre fiches des Done. Il ne modifie
  aucun paquet, ni le catalogue, ni la matrice. Il n'ecrit ni n'alloue aucun identifiant de condition ou de reserve : les
  reserves sont designees par le repere de mes documents qui les a ouvertes (`V3`, `V4`, `S3` de `veto-nadia-gabarit-pli.md`
  et `lecture-nadia-palier-3.md`), ou par une description. Non commite par moi.
- **Etat final, ligne ajoutee le 2026-10-21 a 18:28 (ecart 18 de `releve-relecteur-cloture-palier-3.md`)** : **cinq Done**.
  `-07` est **VALIDE**, sans reserve (derniere section, 14:49). Le Resume, la section 3.2 et la ligne `-07` du Routage sont
  l'etat de 13:46 : historiques, non reecrits. Resume a jour et autres corrections : derniere section de ce fichier.

## Resume

| Lot | Paquet | Verdict | Motif court |
|---|---|---|---|
| `FRA-CE1-06` | `ce1-inference` | **VALIDE SOUS RESERVE** | conditions tenues ; reserves du pli, non bloquantes, deja ecrites, plus une neuve |
| `FRA-CE1-07` | `ce1-texte-documentaire` | **REFUSE** | items 5, 9, 17 : se resolvent sans lire le texte, la condition du jeu texte masque n'est pas tenue |
| `FRA-CE1-11` | `ce1-negation` | **VALIDE SOUS RESERVE** | conditions tenues ; reserve `V3` (reponse attendue sous le pli a la revelation) |
| `MAT-CE1-01` | `ce1-nombres-jusqu-a-1000` | **VALIDE**, plafond `en cours` | conditions tenues, *decomposer* lu par l'enfant : 0 sur 516 ecrans, mention en cellule |
| `EMC-CE1-01` | `ce1-emc-biens-communs` | **VALIDE SOUS RESERVE** | conditions tenues ; reserves `V3`, item 17 (`S3`), et une neuve |

Aucun lot n'est *impossible sans appareil reel* : aucune de mes conditions de Done ne l'exige (section 2).

## 0. Captures lues

Toutes a 744 x 551 avec barre, dans le dossier de captures du releve (hors depot) :

- `-07`, etat masque fabrique, les 20 : `07-i01-masque-744.png` a `07-i20-masque-744.png` ; plus `07-i01-erreur-744.png` (etat
  reel, texte visible).
- `-06` : `06-i14-arrivee`, `-erreur`, `-revelation`, `-erreur-ordre021-2rangees` ; `06-i16-erreur`, `-revelation` ;
  `06-i19-erreur`, `-revelation` ; `06-i20-revelation`.
- EMC : `emc-i08-erreur`, `emc-i09-arrivee`, `emc-i13-revelation`, `emc-i14-erreur`.
- `-11` : `11-i13-revelation`.
- Section D : `d-bandit-manchot-nature-mot-cp-arrivee`, `-position-syllabe-cp-arrivee`, `-matiere-objets-cp-arrivee`,
  `-melanges-cm1-arrivee` ; section F : `f-proposition-sortie-744.png`.

## 1. Conditions du Done : lesquelles le releve satisfait

Sources : `arbitrage-nadia-preparation-palier-3-ce1.md` 4.1 a 4.4 ; `lecture-nadia-palier-3.md` S1 a S5 ;
`veto-nadia-gabarit-pli.md` V3 a V6.

| Condition | Etat | Ce qui la tient |
|---|---|---|
| 4.1 point 1, comptes et longueurs | tenue | ma lecture du 2026-10-20 |
| 4.1 point 2, lecture complete dans un commit ancetre | tenue | `7301cbbc`, ancetre de `8407f73f` |
| 4.1 point 3, points d'audit | tenue | lecture, et l'ecran : intrus du premier mot absent en EMC tache 1 (meme article, meme nombre, `emc-i09-arrivee`) |
| 4.1 point 4, pli apres une erreur | reserve non bloquante, comme ecrit | `06-i14-erreur` : verdict et indice coupes ; les 109 autres items, indice entier au-dessus du pli |
| 4.1 point 5, exactitude relue a l'ecran | **tenue par le releve** | 110 items, texte rendu egal au fichier ; aucune erreur de fait (section 3) |
| 4.1 point 6, audite sur le commit joue | tenue | `8407f73f`, qui porte les corrections de S1 et S3 |
| 4.2, `-07` : chaque item joue texte masque | **non tenue** : 3 items sur 20 | section 4 |
| 4.3, `MAT-CE1-01` : mention de `NUM-CE1-2` mot pour mot ; *decomposer* jamais lu | tenue | cellule de la matrice, ligne 1021 (amendement de `camille-po` du 2026-10-21) ; 0 occurrence sur 516 ecrans (releve, section C) |
| 4.4, EMC : `BCO-CE1-1`, `-2`, `NRC-CE1-1` | tenue | lecture, corrections de S1 et S3 servies |
| S3 : `-06` 14, EMC 8, 9, indice d'EMC 14 | tenue | texte rendu : *"La pluie se met à tomber."*, *"Un de ces objets est à un seul élève. Lequel ?"*, *"Parmi ces objets, lequel est à un seul élève ?"*, *"Demande-toi : qui doit savoir que le livre est abîmé ?"* |
| S4 : espaces insecables, chevrons | tenue | 0 signe precede d'une espace simple sur les 110 items (releve, section E) ; c'est un releve, la regle reste au `relecteur-pedagogique` |

## 2. Ce que le releve ne satisfait pas, et qui peut le fournir

| Ce qui manque | Condition de Done ? | Qui peut le fournir |
|---|---|---|
| `V5` point 6 : une police et un appareil reels | **non** | **l'utilisatrice**, sur une tablette avec la pile de polices d'`ADR-0054` |
| le geste d'un enfant, ce qu'il comprend apres *"Essaie encore !"* | non, et jamais ne l'a ete | l'utilisatrice, avec un enfant ; personne dans la squad |
| un lecteur d'ecran, le clavier, les couleurs | non | `ines-design` (lecteur d'ecran, clavier) ; un appareil reel pour les couleurs |
| `V5` point 2 : `cp-nature-mot`, le mot designe dans un enonce **sur deux lignes** | hors des cinq lots | `ines-design`, par un item de deux lignes rendues ; la capture ne montre qu'une ligne |

**Correction de mon propre constat.** Le 2026-10-21 (`V6`, point 3), j'ai ecrit que le Done de `-06`, de l'EMC et de `-11`
se prononcerait *"apres mon jeu de l'arbre fusionne (V5)"*. `V5` point 6 (police et appareil reels) est une chose que seul un
appareil montre ; je ne l'ai jamais ecrit comme une barre, et aucun Done de ce depot ne l'a exige (le precedent le plus proche,
`FICHE-bandit-manchot-texte-narratif-ce1.md`, est joue en Chrome sans tete). Je ne l'ajoute pas apres coup. Ce qu'un appareil
reel peut changer, c'est la place des lignes : le pli et les coupures, que l'utilisatrice a dits non bloquants. Il ne peut changer
ni l'exactitude, ni le niveau, ni ce qu'un item demande de lire. Je reste la seule garde de ce point.

`V5`, points 1, 3, 4, 5, lus sur les captures, dans cette police :

1. Enonces sur deux lignes a 1,25 (`-06` 14, EMC 13 et 14, `-11` 13 ; CP `matiere-objets` ; CM1 `melanges` sur trois lignes) :
   les lignes ne se touchent pas, l'ecart se lit. Tenu.
2. Non montre : voir la derniere ligne de la table ci-dessus.
3. Proposition a 58 px a cote de *"Les jeux"* a 50 px (`f-proposition-sortie-744.png`) : les trois propositions, larges, en
   aplat, dans le cadre du jeu, se voient comme ce sur quoi on appuie ; *"Les jeux"* reste un bouton d'en-tete. Tenu.
4. `-06` 14 apres une erreur, trois rangees (`06-i14-erreur-744.png`) : on lit *"Ce n'est pas ça"* coupe a mi-hauteur,
   *"Essaie encore !"* invisible, l'indice coupe. En deux rangees (`-ordre021-2rangees`), tout est lisible. La reserve `V6`
   *"a traiter"* est confirmee, non bloquante.
5. A la revelation : EMC 13 montre *"elle tourne les pages"* sans *"doucement"* ; `-11` 13, *"Yanis ne range pas ses"* sans
   *"crayons."* ; `-06` 14, la reponse est entierement sous le pli ; `-06` 16, 19, 20, la reponse est coupee a mi-hauteur des
   lettres. La reserve `V3` est confirmee, non bloquante. Dans chaque cas, la bonne reponse est la seule proposition non
   barree, visible au-dessus : l'enfant peut avancer.

## 3. Par lot : ce que j'ai lu et vu

### 3.1 `FRA-CE1-06`, `ce1-inference` : VALIDE SOUS RESERVE

- **Lu** : les 20 items du releve (section B), egaux au fichier ; captures de 14, 16, 19, 20.
- **Exactitude** : 20 bonnes reponses justes, 40 distracteurs faux pour le texte ; item 19, *"pour jouer avec l'eau"*,
  vraisemblable et contredit par *"chaque jour"* et la tige (`INF-CE1-3` *b*). Aucune bonne reponse n'est une suite de mots du
  texte (`INF-CE1-2`).
- **Ancrage** : sentiment 7, lieu 6, cause 7 ; chaque reponse relie au plus deux faits (`INF-CE1-1`). C'est bien l'inference,
  pas le prelevement.
- **Apres une erreur** : l'indice dit ou regarder (*"Relis ce qui arrive à son ventre."*, `06-i16-erreur`) ; a la revelation,
  la bonne reponse est montree. L'enfant n'est jamais bloque.
- **Reserves non bloquantes** : `V4`/`V6` (item 14, verdict et indice sous le pli dans 2 ordres sur 6) ; `V3` (reponse
  attendue sous le pli a la revelation, items 14, 16, 19, 20). **Reserve neuve, datee du 2026-10-21** : l'enonce de 14 se
  coupe dans le mot compose, *"au-"* en fin de ligne et *"dessus de lui ?"* a la suivante (`06-i14-arrivee-744.png`). Un lecteur
  de CE1 lit *"au-"* seul. Exigence : a 744 x 551, aucun mot a trait d'union d'un enonce coupe en fin de ligne. Le levier
  (contenu ou rendu) n'est pas a moi.

### 3.2 `FRA-CE1-07`, `ce1-texte-documentaire` : REFUSE

Section 4.

### 3.3 `FRA-CE1-11`, `ce1-negation` : VALIDE SOUS RESERVE

- **Lu** : les 24 items (section B), egaux au fichier ; capture de 13 a la revelation ; chevrons et insecables (section E).
- **Exactitude** : 24 bonnes reponses justes, `ne ... pas` au present partout, elision juste (*n'aime*, *n'écoute*,
  *n'ouvre*, *n'habite*, *n'attend*, *n'utilise*, *n'oublie*, *n'arrive*) ; chaque distracteur est une faute que la ligne
  apprend a reperer (`NEG-CE1-3`).
- **Indices** : trois, chacun dit ou regarder (*"Cherche les deux petits mots qui entourent le verbe."*) ; plus aucun *"a
  lieu"*.
- **Reserve non bloquante** : `V3` (item 13 a 744 ; 18 a 720 avec barre).

### 3.4 `MAT-CE1-01`, `ce1-nombres-jusqu-a-1000` : VALIDE, plafond `en cours`

- **Lu** : les 22 items (section B), egaux au fichier ; capture `f-proposition-sortie-744.png` (item 20) ; ecran de fin
  d'une partie de dix manches (releve, section C).
- **Exactitude** : 22 bonnes reponses justes, 44 distracteurs distingues par la valeur (zero intercalaire 305, 407, 604, 806 ;
  tranche 60 a 99 : 372, 497, 771, 990). *Huit cents* avec son *s*, *trois cent cinq* sans.
- ***Decomposer*** : 0 occurrence sur 516 ecrans (DOM, texte rendu, attributs, corps HTTP des pages de navigation). Le refus
  ecrit d'avance n'est pas atteint.
- **Enonces 15, 16, 19** : *"Quel nombre a ..."*, la correction de S1.
- **Mention** : la cellule porte celle de `NUM-CE1-2` mot pour mot. La ligne passe a `en cours`, jamais au-dela.
- **Aucune reserve** : 0 item sous le pli apres une erreur ; aucun item dans `V3`.

### 3.5 `EMC-CE1-01`, `ce1-emc-biens-communs` : VALIDE SOUS RESERVE

- **Lu** : les 24 items (section B), egaux au fichier ; captures de 8, 9, 13, 14.
- **Ancrage** : tache 1 (a qui est cet objet), 12 items ; tache 2 (le geste qui protege), 12 items ; les trois exemples de la
  ligne en bonne reponse ; ni *ranger* ni *remettre* en bonne reponse (`BCO-CE1-1`). Chaque bien personnel est celui d'un
  personnage nomme, a l'ecole ; aucun item ne parle de ce que l'enfant possede (`BCO-CE1-2`).
- **Corrections servies** : tache 1 a meme article et meme nombre (`emc-i09-arrivee`) ; 14, 15, 17, 20, 21 sans distracteur
  absurde ; 8, 9 et l'indice de 14 comme en S3.
- **Reserves non bloquantes** : `V3` (13 a 744 ; 21 a 720 avec barre) ; item 17 (`S3`), confirme sur le texte rendu : la
  question dit *"avant de l'ouvrir"*, et *"il mange en lisant"* et *"il l'ouvre avec des doigts collants"* ne sont pas des
  gestes d'avant ; l'enfant peut eliminer par la forme. **Reserve neuve, datee du 2026-10-21** : l'enonce de 13 se coupe a
  *"Que fait-"* / *"elle pour ne pas l'abîmer ?"* (`emc-i13-revelation-744.png`), meme exigence qu'en 3.1.

## 4. `-07`, le jeu a texte masque

### 4.1 Ce que disent mes regles

**Elles ne disent pas qu'un item qui se resout sans le texte est refuse d'avance.** Mes refus ecrits d'avance pour `-07` sont deux :
un fait faux (`DOC-CE1-3`), une bonne reponse seule presente dans le texte (`DOC-CE1-2`). Le jeu texte masque est une
**condition du Done** (`arbitrage-nadia-palier-2.md` 6.2, reprise en 4.2 de la preparation du palier 3) : *"un item auquel un
enfant repond sans lire mesure sa culture"*. C'est le risque propre de la ligne (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`,
3.7). Une condition non tenue ne fait pas un Done : le lot est refuse, avec la liste.

Mon critere, celui de ma lecture du 2026-10-20 (section 2), sans le changer : sans le texte, un enfant de CE1 trouve la
reponse par une culture commune a son age, ou par la logique du geste, ou parce que les distracteurs ne sont pas vraisemblables
pour la seule question. Ma barre de correction etait : *"demander un fait que seul le texte donne, ou des distracteurs aussi
vraisemblables que la bonne reponse sans le texte"* (documentaires), *"un detail que seule la consigne fixe"* (consignes).

### 4.2 Les 20 items, etat masque (captures `07-iNN-masque-744.png`)

| Item | Sans le texte | Tient ? |
|---|---|---|
| 1 lapin | *"des carottes"* attire la culture, *"de l'herbe"* aussi : le texte departage | oui |
| 2 fraise | *"sur un pommier"* s'ecarte ; *"sous la terre"* reste aussi vraisemblable que *"près du sol"* pour qui n'a pas vu un fraisier | oui |
| 3 crocodile | *"dans la rivière"* attire ; le lieu de ponte n'est pas une culture de CE1 | oui |
| 4 Lune | *"du Soleil"* est la confusion courante | oui |
| 5 papier | **non** : *"le papier vient des arbres"* est une culture courante a 7 ans, et ni le sable ni la laine ne sont vraisemblables pour du papier | **non** |
| 6 poissons | *"avec des poumons"* et *"à la surface"* restent vraisemblables | oui |
| 7 papillon | *"du miel"* attire autant que *"du nectar"* | oui |
| 8 soleil | l'est et l'ouest ne sont pas acquis au CE1 | oui |
| 9 chauve-souris | **non** : *"la chauve-souris vit la nuit"* est une culture courante ; *"elle sort"* et *"elle mange des insectes"* sont deux gestes de nuit, que *"le jour"* de la question ecarte | **non** |
| 10 eau | *"à cent degrés"* et *"à dix degrés"* restent vraisemblables pour un enfant qui n'a pas l'echelle des temperatures (cycle 3) | oui |
| 11 koala | *"du bambou"* (le panda) attire | oui |
| 12 pli | *"un rond"* reste aussi vraisemblable que *"un cœur"* | oui |
| 13 rincer | *"à l'eau chaude"* reste aussi vraisemblable que *"à l'eau claire"* | oui |
| 14 eau | *"beaucoup"* reste vraisemblable (*"une plante a besoin d'eau"*) | oui |
| 15 couleur | arbitraire sans le texte | oui |
| 16 ranger | les trois objets se rangent | oui |
| 17 en dernier | **non** : on casse les oeufs d'abord, on ajoute, on melange a la fin ; la logique du geste donne *"Mélange bien"* | **non** |
| 18 trace | *"un feutre"* reste aussi vraisemblable que *"un crayon"* | oui |
| 19 s'arreter | *"jusqu'à la grille"* ne repond pas a *"où s'arrêter"* et s'ecarte par la forme ; *"à gauche"* reste vraisemblable | oui |
| 20 verre | l'usage met souvent le sirop d'abord : la culture egare, le texte departage | oui |

**Les neuf items que j'avais signales (1, 2, 7, 9, 12, 13, 14, 16, 18)** : huit tiennent, **9 ne tient pas**. **L'item 17**
ne tient pas. **L'item 5**, que je ne tranchais qu'en jouant (lecture du 2026-10-20, section 2), ne tient pas. Les autres cas
que je reservais au jeu (4, 8, 10, 11) tiennent.

**Ce n'est pas un deplacement de poteaux.** Le 2026-10-20 (S1), j'ai ecrit que les corrections demandees etaient faites et
n'etaient plus une condition ; dans le meme document (S3, S5), j'ai laisse au jeu texte masque les items 9 et 17, et la
condition du jeu texte masque des 20 items est ecrite depuis s57 palier 2. Je juge ces trois items contre le critere qu'ils
avaient avant.

### 4.3 Les indices qui renvoient au texte quand le texte est cache

**Defaut de l'etat fabrique, pas du produit.** Le produit n'a pas d'etat *texte masque* : le texte est toujours affiche au-dessus
de la question, et apres une erreur l'indice est rendu a cote de lui (`07-i01-erreur-744.png` : texte visible, *"Cherche le mot
« lapin » dans le texte."*). L'etat masque est mon instrument de verification, une experience de pensee qu'`ines-design` a
rendue visible. Dans cet etat, les 20 indices designent le support cache (8 disent *"dans le texte"*, 6 *"dans la
consigne"*, 5 *"Relis la ... phrase / instruction"*, 1 *"Les instructions se lisent dans l'ordre."*) : c'est ce qu'un indice
de prelevement doit faire. Rien a corriger. Petite inexactitude du releve, sans effet : sa section A cite *"le texte dit"*, que
porte aucun indice de `-07`.

### 4.4 Le refus, actionnable

| Item | Motif | Correction (seul le YAML du paquet ; `DOC-CE1-1` a `-3` et `TRV-CE1-2` tenus) |
|---|---|---|
| 5 | se resout par la culture (*papier* / *arbre*) et par deux distracteurs non vraisemblables | demander un fait que seul le texte donne. Exemple qui tient sur le texte actuel : *"Avec quoi fabrique-t-on le verre ?"*, bonne reponse *"du sable"*, distracteurs *"du bois"*, *"de la laine"* (tous trois dans le texte) |
| 9 | se resout par la culture (*la chauve-souris vit la nuit*) | garder l'etre (`DOC-CE1-3`) ; une question dont la reponse ne se deduit pas de *"elle vit la nuit"*, ou des distracteurs aussi vraisemblables de jour que la bonne reponse. Je ne propose pas d'exemple : un lieu de repos ou une nourriture de chauve-souris rendrait vrais plusieurs distracteurs, ce que `TRV-CE1-2` interdit |
| 17 | se resout par la logique du geste | une question sur un detail que seule la consigne fixe, sur le modele de 15, 19, 20. Exemple : *"Que faut-il faire juste après avoir cassé les œufs ?"*, bonne reponse *"Ajoute du sucre"*, distracteur *"Mélange bien"* |

Je rejoue les trois items corriges, texte masque, sur le commit qui les porte ; le reste du lot est acquis a ce jour (17 items
tenus, exactitude tenue, pli : 0 sur 20). Aucune fiche n'est ouverte pour ce lot.

## 5. Hors des cinq lots, vu sur les captures de la section D (pas un verdict)

- `bandit-manchot-matiere-objets-cp` : consigne de CP sur deux lignes, *"Lis bien, puis dis en quelle matière est fait
  l'objet, ou trie les matières selon leurs propriétés."* : deux taches liees par *ou*, et *propriétés*. C'est la lisibilite
  que je disais juger en jouant ces jeux (`V2`). A rouvrir sur la fiche de ce jeu, par moi, quand `camille-po` l'engage.
- Meme capture : un trait vertical sombre entre *"En"* et *"quelle"*, a la seconde ligne de l'enonce, qui rejoint le cadre du
  jeu. Je n'en connais pas la cause. A `ines-design`.

## 6. Ce dont je reste la seule garde

| Propriete | Pourquoi aucun test ne la tient |
|---|---|
| Qu'un item de `-07` ne se reponde pas sans le texte | aucun test ne lit la culture d'un enfant ; le releve ne fait que montrer l'ecran |
| Qu'un mot a trait d'union ne se coupe pas en fin de ligne d'un enonce | aucun test ne lit une coupure ; elle depend de la police |
| Ce qu'une police et un appareil reels changent aux coupures et au pli | aucun appareil dans la squad |
| Qu'aucun item d'EMC CE1 ne se resolve par un reflexe du CP (`NRC-CE1-1`) | aucun garde-fou ne compare deux paquets |

## 7. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | `-06`, `-11` : passage a `couvert-valide` ; EMC : `couvert-valide` ; `MAT-CE1-01` : `en cours` avec sa mention ; les reserves de 3.1, 3.3, 3.5 en stories ; `-07` : les trois items de 4.4 en backlog, ligne inchangee |
| `sofia-sm` | le commit de ce fichier et des quatre fiches ; le jar du commit qui corrigera `-07` 5, 9, 17 |
| `ines-design` | la derniere ligne du tableau de la section 2 (nature-mot sur deux lignes) ; le trait de la section 5 ; le lecteur d'ecran et le clavier, si elle les engage |
| l'utilisatrice | si elle le veut : une tablette reelle, la pile de polices d'`ADR-0054`, un enfant. Rien de cela n'est une condition de ces Done |

**Signe** : `nadia-prof`, s57, palier 3, 2026-10-21, 13:46.

## Rectification du 2026-10-21 (`nadia-prof`, sur le constat de `camille-po`)

**Ma phrase de la section 2, *"le pli et les coupures, que l'utilisatrice a dits non bloquants"*, est inexacte pour moitie.**
L'utilisatrice a dit le pli : *"le pli n'est pas bloquant"* (reponse a `Q-N4`, citee dans
`FICHE-bandit-manchot-phrases-types-ce1.md` et `FICHE-bandit-manchot-pronoms-ce1.md`). Elle n'a rien dit des coupures de
ligne : aucune phrase d'elle ne les qualifie (`grep` sur `squad/decisions` et sur ce sprint : seule ma phrase les nomme). Je
retire *"et les coupures"* de ce que je lui attribue ; le reste du fichier n'est pas modifie.

1. **Ce que je decide moi-meme** : la coupure d'un mot a trait d'union en fin de ligne (`-06` 14 *"au-"* / *"dessus"*, EMC 13
   *"Que fait-"* / *"elle"*) est une **reserve non bloquante, sur ma seule autorite de Done pedagogique**. Motif : le mot reste
   entier sur deux lignes consecutives, l'enonce est court, et ni la question, ni les propositions, ni la reponse attendue n'en
   sont changees ; l'enfant lit la question et peut repondre. C'est une gene de lecture, pas une erreur de contenu ni un
   obstacle a l'item. Il en va de meme de toute coupure de ligne que j'ai vue dans ce verdict.
2. **Les Done de `-06`, `-11` et de l'EMC tiennent sur ma seule autorite : oui.** `-11` n'a aucune coupure de mot a trait
   d'union ; ses reserves sont celles du pli. La correction de V6 de la section 2 tient pour le pli, dont la source
   est l'utilisatrice ; pour les coupures, elle tient par la decision du point 1.
3. **Aucune reponse de l'utilisatrice n'est necessaire.**

## `-07`, rejeu texte masque des items 5, 9, 17 (2026-10-21, `nadia-prof`)

- **Lu** : `git diff 8407f73f ac986aca` sur `ce1-texte-documentaire.yaml` (branche `s57-marc-doc-p3`, non fusionnee) : seuls
  les items 5, 9 et 17 changent. Genres comptes sur `ac986aca` : **11 documentaires, 9 consignes** (et non 9 et 11 comme le
  disait le message de relance) ; `DOC-CE1-1` tient. Les 17 autres items : etat masque du releve d'`ines-design`, juges en
  section 4.2, inchanges.
- **Audit degrade, declare** : ces trois items ne sont pas joues a l'ecran ; je les juge sur le fichier, en lisant l'enonce et
  les trois propositions sans le texte.

| Item | Sans le texte | Tient ? | Le reste |
|---|---|---|---|
| 5 *"Avec quoi fabrique-t-on le verre ?"* (*"du sable"* ; *"du bois"*, *"de la laine"*) | le verre fait de sable n'est pas une culture de CE1, et le sable n'a rien de transparent : aucune des trois ne s'impose, le texte departage | oui | fait exact ; trois matieres dans le texte (`DOC-CE1-2`) ; l'indice nomme le mot de la question |
| 9 *"Comment dort le cheval ?"* (*"debout"* ; *"en boule"*, *"la tête en bas"*) | *"la tête en bas"* s'ecarte pour un cheval ; *"en boule"* reste vraisemblable pour qui n'a pas vu un cheval dormir, comme *"sous la terre"* en 2 | oui | *"Le cheval dort debout"* est la generalisation des documentaires pour enfants (il se couche aussi pour le sommeil profond) : je l'accepte au meme titre que *"Le pull se fabrique avec de la laine"* (lecture du 2026-10-20, section 2) |
| 17 *"Que faut-il faire juste après avoir cassé les œufs ?"* (*"Ajoute du sucre"* ; *"Mélange bien"*, *"Casse deux œufs"*) | *"Casse deux œufs"* s'ecarte ; on peut melanger les oeufs avant ou apres le sucre : deux restent vraisemblables, la consigne departage | oui | detail que seule la consigne fixe, la barre de 4.4 |

**L'ecart de l'item 9 (le cheval au lieu de la chauve-souris) : accepte.** J'ai ecrit *"garder l'etre (`DOC-CE1-3`)"* ; ce que
`DOC-CE1-3` protege, c'est un etre qu'un enfant de CE1 connait ou se represente sans image, et le cheval l'est. J'avais
moi-meme renonce a proposer un exemple sur la chauve-souris, pour le motif que donne `marc-dev` (des distracteurs vrais). La
chauve-souris reste dans le texte, comme distracteur. Ce n'est pas un abaissement de ma barre : c'est son motif, tenu.

`TRV-CE1-2` (longueur) : la bonne reponse est strictement la plus longue dans 5 items sur 20, comme avant ; aucun des trois
items neufs n'en est.

**Verdict : pas encore prononce. Il depend de l'ecran, pour un seul motif.** Le jeu texte masque est tenu sur les 20 items.
Mais ma condition 4.1 point 5 (*"chaque bonne reponse et chaque distracteur relus a l'ecran"*) et le point 6 (audite sur le
commit joue) ne sont pas tenus pour ces trois items : je les ai exiges des quatre autres lots, je ne m'en dispense pas ici.
Aucun refus : il ne reste aucun motif de contenu.

**A demander a `ines-design`** : sur le jar construit a `ac986aca` (ou sur `round-two` apres sa fusion), le releve des items 5,
9 et 17 de `-07` dans la forme de sa section A : texte DOM des etats V, E et R, et une capture a 744 x 551 de l'arrivee, de
l'erreur et de la revelation. Des qu'il montre le texte du fichier rendu tel quel, je prononce le Done de `-07` et j'ouvre sa
fiche ; le pli et les coupures de ligne, s'il y en a, sont des reserves non bloquantes (section 4.1 point 4 ; rectification
ci-dessus).

**Signe** : `nadia-prof`, s57, palier 3, 2026-10-21.

## `-07`, verdict apres le releve d'ecran des items 5, 9, 17 (2026-10-21, `nadia-prof`) : VALIDE

- **Lu** : `releve-ecran-ines-palier-3.md`, sections I a L (branche `s57-ines-r2`, commit `89f4afeb`), jar de `round-two` a
  `ea5d9429`. Le YAML de `ea5d9429` et celui de `ac986aca` ont le meme blob (`5f3e6c95`, `git rev-parse`) : ce que j'ai juge
  sur le fichier est ce qui est servi. Captures lues : `07-i05-r2-masque-arrivee-744`, `07-i09-r2-masque-arrivee-744`,
  `07-i09-r2-erreur-744`, `07-i17-r2-masque-arrivee-744`, `07-i17-r2-erreur-744`, `07-i17-r2-revelation-744`.
- **Ma condition 4.1 point 5 est tenue pour ces trois items** : enonce, texte-support, bonne reponse, distracteurs et indice
  rendus egaux au fichier, caractere par caractere (15 comparaisons sur 15), dans les six ordres. Point 6 tenu : audite sur
  le commit servi.
- **Texte masque, a l'ecran** : ce que j'avais juge sur le fichier se confirme (section precedente) ; les 20 items tiennent.
- **Apres une erreur** : indice entier au-dessus du pli pour les trois items (bas a 402, 402, 478). **A la revelation** :
  reponse attendue entiere (bas a 434, 434, 510). L'enonce de 17 passe sur deux lignes, sans mot coupe. L'indice de 17 a la
  revelation finit a 564 : je n'exige rien sur l'indice a la revelation (`veto-nadia-gabarit-pli.md`, section 4).

**Verdict : VALIDE, Done pedagogique prononce, aucune reserve.** Le refus du 2026-10-21 (section 4) reste ecrit ; il est leve
par la correction d'`afd33e88`. `camille-po` peut passer `FRA-CE1-07` a `couvert-valide`. Fiche ouverte :
`squad/conformite/FICHE-bandit-manchot-texte-documentaire-ce1.md`.

**Signe** : `nadia-prof`, s57, palier 3, 2026-10-21.

## Amendement du 2026-10-21, 18:28 (`nadia-prof`) : ecarts 18, 21, 22 et 25 du releve de cloture

Source des ecarts : `releve-relecteur-cloture-palier-3.md`, section 3.3. **Rien au-dessus n'est reecrit** ; chaque phrase
corrigee reste lisible a sa place. Aucun verdict ne change.

**Ecart 18, Resume a jour.** Le Resume du haut est celui de 13:46. L'etat final :

| Lot | Verdict final | Fiche |
|---|---|---|
| `FRA-CE1-06` | **VALIDE SOUS RESERVE** | `FICHE-bandit-manchot-inference-ce1.md` |
| `FRA-CE1-07` | **REFUSE** a 13:46, puis **VALIDE** a 14:49, aucune reserve | `FICHE-bandit-manchot-texte-documentaire-ce1.md` |
| `FRA-CE1-11` | **VALIDE SOUS RESERVE** | `FICHE-bandit-manchot-negation-ce1.md` |
| `MAT-CE1-01` | **VALIDE**, plafond `en cours` | `FICHE-bandit-manchot-nombres-jusqu-a-1000-ce1.md` |
| `EMC-CE1-01` | **VALIDE SOUS RESERVE** | `FICHE-bandit-manchot-biens-communs-ce1.md` |

Ce fichier prononce donc cinq Done et un refus leve, et ouvre cinq fiches. La ligne `-07` du Routage (*"ligne inchangee"*) est
remplacee par : `camille-po` passe `FRA-CE1-07` a `couvert-valide` (fait par elle, `cb4d81d0`).

**Ecart 21, en-tete.** *"Porte technique etablie par le coordinateur"* n'a pas de source nommee. Source, forme 1 de `DEC-0062` :
`sofia-sm`, `rapport-sofia-dev-palier-3.md` section 13, porte de `ad9ef86d` : jobs `prefixes`, `chemins`, `outils`, `build`
(verify racine et package), 2854 tests.

**Ecart 22, derniere section.** *"leve par la correction d'`afd33e88`"* : `afd33e88` est la fusion. Lire : leve par la
correction `ac986aca` (`marc-dev`), fusionnee par `afd33e88`.

**Ecart 25, section 1, ligne 4.3.** *"cellule de la matrice, ligne 1021"* etait exact a `8407f73f`, l'arbre juge ; la ligne
bouge depuis. Lire : la cellule de `MAT-CE1-01` dans `squad/programme/MATRICE-couverture.md`, lue a `8407f73f`.

**Signe** : `nadia-prof`, 2026-10-21, 18:28.
