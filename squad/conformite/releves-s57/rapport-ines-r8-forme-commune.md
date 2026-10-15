# R8 / Q-R3 - forme commune sans L1 ni L2 : cotes de reference, regles proposees, cotes apres, effets sur les autres jeux

- Auteure : `ines-design`.
- Date : 2026-10-15, 20:14 (UTC+02:00), lu par `date`.
- Branche : `s57-ines-r3`, issue de `round-two` a `6dbb855d` (`git log -1 --format=%H` : `6dbb855d49df2aaf6eb0d222f99a8a010e2561c8`), worktree dedie.
- Demande : `cloture-palier-1.md` (`R8`), `avis-theo-poste-8-art-cp-10.md` (`TB4`, B.6, `Q-T5`), `reponse-nadia-forme2-palier-1.md` section 3 (`Q-T5` : forme commune cherchee sans `L1` ni `L2`), `engagement-poste-8.md` (`A5`, port 19243), `reponse-nadia-q-palier-1.md` section 1 (`Q-R5` : jar de base).
- **Ce document propose. Il ne prononce aucun Done : le veto est a `nadia-prof`, l'avis d'architecture a `theo-archi`. Aucune regle n'est dans `recreo.css`.**
- Fichiers : `candidat-r3.css` (le texte exact injecte pour la mesure), `candidat-r3-option-bandeau.css` (une option, section 7), `pilote-r3.mjs`, `pilote-r3-echantillon.mjs`, `polices-r3.mjs`, `ablation-r3.mjs`, `agreger-r3.mjs`, `analyser-echantillon-r3.mjs`, `tableau-r3.mjs`, `compacter-r3.mjs` ; releves `r3-*.json` ; cotes item par item `r3-cotes-par-item.md`.

## 0. Resume

| Point | Resultat |
|---|---|
| Cotes de reference, 744 x 551, jar de `6dbb855d` | **Identiques aux fiches** : `TPH-CE1-R1` 0 sur 24 (568 a 650), `GNA-CE1-R1` 1 sur 24 (486 a 650), `VSJ-CE1-R2` 22 sur 24 (534 a 582) |
| Forme proposee | Cinq regles CSS, dans `@media (min-width: 720px)`, sans `L1` (visage, etoiles, bandeau, socle gardent leur hauteur) ni `L2` (le score garde sa ligne) |
| Cotes apres, 744 x 551 | **24 sur 24 au-dessus du pli sur les trois jeux**, bas de `.indice` au plus 548. Gain de 94 a 132 px sur `TPH`, 86 a 102 sur `GNA`, 86 a 116 sur `VSJ` ; **102 px sur les items qui en demandaient 99** |
| Marge | **3 px** (548 pour 551) sur les items a trois rangees et indice de deux lignes. 1 px sur la reponse revelee (550) |
| Les 144 couples (item, ordre des propositions) par jeu | 0 couple visible sans la forme et cache avec ; 144 sur 144 visibles avec (bas <= 551), sur chacun des trois jeux |
| Autres jeux | 40 jeux, 2193 etats par largeur : **0 indice perdu sous le pli**, 0 indice plus bas, 0 debordement neuf. Le bloc du message est plus haut dans 62 etats a 744 (jusqu'a +58 px en revelation). `ART-CP-10` : 66 mesures sur 66 identiques |
| Ce que la forme ne leve pas | 3 items de `ce1-texte-narratif` a **552** (1 px), cote a gagner 133 px |
| Effet de bord a trancher par `nadia-prof` | L'ascenseur apparait a la revelation sur 45 items sur 72 a 744 (1 sur 72 sans la forme) : les cases bougent de 7,5 px |
| Plus que du CSS ? | **Non** : aucun gabarit, aucune constante, aucun marqueur derive par le controleur. Une garde de test se declenche si un selecteur nomme `.cabinet` (section 6) ; la forme n'en nomme aucun |

## 1. Methode et base

1. **Jar de base** (`Q-R5`) : le commit d'ou part la branche, `6dbb855d`, arbre propre. `bash outils/mvn-jdk21.sh -B -q clean package -DskipTests`, jar servi sur un port libre (18571). Pas de candidat dans le jar : le candidat est un `<style id="candidat">` ajoute au `<head>` apres la feuille reelle, comme au poste 8.
2. **Mesure appariee.** Chaque etat est mesure sur le meme DOM, sans puis avec le candidat (`disabled` du `<style>`), a chaque largeur (la fenetre est redimensionnee sur place, l'etat de la partie ne bouge pas). Aucune cote n'est comparee entre deux parties differentes. Fenetre 551 de haut, Chrome sans tete sur le port 19243, `getBoundingClientRect()`.
3. **Verification du bras "base"** : un premier passage sans aucun style injecte (`r3-base-744-sans-style.json`, premiere version du pilote, sans bascule) donne a 744 le meme bas d'indice que le bras base de la bascule sur **les 24 items des trois jeux (72 sur 72)**.
4. **Items** : les trois paquets sont joues item par item (24 sur 24, 5 a 11 parties de dix manches), une premiere erreur puis une seconde puis la revelation. L'erreur est choisie parmi les distracteurs lus dans le YAML du paquet ; le gabarit, lui, ne connait que ce que le serveur sert.
5. **Ordre des propositions.** Le serveur tire l'ordre ; le repli des rangees en depend. A chaque erreur et a chaque revelation, les six permutations des trois propositions sont mesurees (boutons reranges dans le DOM puis remis). Voir section 2, point 2.
6. **Vocabulaire** : "au-dessus du pli" ou "visible" = bas de la boite <= 551 (c'est la lecture des criteres de `nadia-prof` et des scripts `agreger-r3.mjs` et `analyser-echantillon-r3.mjs`). **Largeurs versees** : 720, 744, 992. 768 et 1200 ont ete mesurees dans la meme passe, leurs releves ne sont pas verses (taille) ; je n'en cite aucun chiffre.
7. **Etat du catalogue** : `grep -c "^  - id:" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 150 ; 145 jeux `mecanique: bandit-manchot`, 3 `designation`, 2 `saisie`. **Le "143 jeux" des textes de `nadia-prof` date du catalogue a 148.**

## 2. Cotes de reference (jar de base, 744 x 551, une erreur)

Detail item par item, avec les trois propositions servies : `r3-cotes-par-item.md` (colonnes "Indice, jar de base", "Verdict, jar de base", "Propositions servies"). Resume par classe :

| Jeu | Classe d'items | Indice (haut - bas) | Verdict (haut - bas) | Rangees | A gagner |
|---|---|---|---|---|---|
| `TPH-CE1-R1` | 15 items de type (`declarative`, `interrogative`, `exclamative`) | 566 - 616 | 507 - 537 | 2 | 65 |
| | 3 items de reconnaissance a phrase courte (items 1, 22, 23) | 518 - 568 | 459 - 489 | 2 | 17 |
| | 6 items de reconnaissance a phrase longue (items 4, 7, 10, 13, 16, 19) | 600 - 650 | 541 - 571 | 3 | **99** |
| `GNA-CE1-R1` | 22 items | 518 - 568 | 459 - 489 | 2 | 17 |
| | `les gateaux` (item 16) | 436 - 486 | 377 - 407 | 1 | 0 |
| | `des manteaux chauds` (item 9) | 600 - 650 | 541 - 571 | 3 | **99** |
| `VSJ-CE1-R2` | 22 items | 484 - 534 | 425 - 455 | 1 | 0 |
| | items 15 et 19 (enonce sur trois lignes) | 532 - 582 | 473 - 503 | 1 | **31** |

Les numeros d'item sont ceux du YAML du paquet, comptes depuis 0 (le backlog compte depuis 1 : les items 15 et 19 de `VSJ` sont ses items 16 et 20). Ces cotes sont celles des fiches et du backlog, au pixel (`FICHE-bandit-manchot-phrases-types-ce1.md` section 5 ; `S57-GNA-CE1-R1` ; `S57-VSJ-CE1-R2`). Deux precisions que les fiches ne portent pas :

1. **Le verdict, sur les items a trois rangees, finit a 571 : il est lui aussi sous le pli** (les fiches le disent pour `TPH`). Les 24 items de `GNA` et `VSJ` ont leur verdict au-dessus du pli, sauf l'item 9 de `GNA` (571).
2. **Le cote d'un item peut dependre de l'ordre servi.** Sur les 72 items, **un seul** : `TPH` item 22 (`Le vent souffle fort.`), 568 si les trois phrases se rangent sur deux rangees, 650 sur trois. Les cotes de `nadia-prof` (518 - 568 pour ses trois items courts) sont celles de l'ordre qu'elle a vu. Les 71 autres items ont le meme cote dans les six ordres (`r3-trois-jeux.base.json`, champ `ordres`).

## 3. Forme proposee

Texte exact : `candidat-r3.css`. Sujet : l'ecran de manche (`.ecran-de-jeu` qui contient `.propositions`), hors `.ecran-deux-oeuvres` qui garde sa feuille. **Aucune regle ne nomme un paquet, un etat (`.est-juste`, `.est-faux`), une longueur ou `.cabinet`.** Tout est dans `@media (min-width: 720px)` : sous 720 px rien ne change.

| Regle | Ce qu'elle fait | Gain mesure (item `des manteaus chauds`, 650 au depart, regle seule) |
|---|---|---|
| R3-1 | `padding-top: 0` sur `.zone-jeu` quand elle porte une manche : le score commence au bord de la bande de l'en-tete (section 6, point 3) | -32 |
| R3-2 | Marges de 4 px a zero : haut et bas du score, bas de la consigne, bas de l'enonce | -16 |
| R3-3 | `row-gap: 0.5rem` entre deux rangees de propositions (l'ecart lateral reste 1 rem) | -16 sur trois rangees (8 par ecart) |
| R3-4 | Message en deux colonnes : le signal (15 rem) a gauche, l'indice, la revelation et l'explication a droite, au lieu de dessous | -34 |
| R3-5 | Le premier bloc de la colonne de droite perd sa marge haute de 4 px | -4 |
| Ensemble | 650 -> **548** | **-102** (les cinq gains s'additionnent : 32 + 16 + 16 + 34 + 4) |

**Aucune regle ne peut tomber** : `r3-ablation.txt`, meme item, candidat sans une regle : sans R3-1 580, sans R3-2 564, sans R3-3 564, sans R3-4 582, **sans R3-5 552** (1 px sous la barre). Sur les trois autres items de `r3-ablation.txt` (`TPH` item 4, `VSJ` item 15, `ce1-texte-narratif` item 18) : R3-1, R3-2 et R3-5 valent partout 32, 16 et 4 ; R3-3 vaut 8 par ecart de rangee (0 sur une rangee) ; R3-4 vaut 34 quand l'indice a deux lignes et **64** quand il en a une (`TPH` 4 et `ce1-texte-narratif` 18).

**Ce que la forme ne touche pas** : ni la taille ni le rembourrage ni la couleur d'un bouton, d'une case, d'un libelle ou de l'icone du signal ; ni le visage, ni les etoiles, ni la hauteur du bandeau et du socle (`L1` absent) ; ni la position du score (`L2` absent) ; ni l'en-tete et son bouton de sortie `Les jeux` ; aucun texte. Une seule chose se deplace pour l'enfant : tout monte de 48 px sous l'en-tete, le message se lit a droite du signal, les rangees de propositions sont plus serrees (8 px).

**Lecture des conditions de `theo-archi` (`C1'`, `C2`, `C3`).** `C1'` point 1 : R3-1 a R3-3 ont pour sujet des elements hors `.message-jeu` et nomment ni `.message-jeu`, ni `.signal`, ni un etat. Point 2 : R3-4 et R3-5 nomment `.message-jeu` et `.signal`, jamais un etat ; la disposition est la meme dans les trois etats par construction (aucune regle ne lit `.est-juste` ni `.est-faux`) ; je n'ai pas mesure les abscisses du signal et du texte etat par etat. Point 3 : sans objet, l'ecran de texte est un flux vertical, aucun element ne s'etend sur la rangee du message et sur une rangee au-dessus. `C2` : aucune regle ne vise une image de proposition. `C3` : aucune regle ne masque rien.

## 4. Cotes apres (744 x 551, bas de `.indice` apres une erreur)

| Jeu | Au-dessus du pli, base | Au-dessus du pli, forme | Bas de l'indice, base | Bas de l'indice, forme | Verdict, bas, forme | Signal, bas, forme |
|---|---|---|---|---|---|---|
| `TPH-CE1-R1` | 0 / 24 | **24 / 24** | 568 - 650 | **444 - 548** | 462 - 536 | 470 - 544 |
| `GNA-CE1-R1` | 1 / 24 | **24 / 24** | 486 - 650 | **400 - 548** | 388 - 536 | 396 - 544 |
| `VSJ-CE1-R2` | 22 / 24 | **24 / 24** | 534 - 582 | **418 - 496** | 436 - 484 | 444 - 492 |

- **Gain par item** (`r3-cotes-par-item.md`, colonne "Gain") : `TPH` 94 a 132 ; `GNA` 86 a 102 ; `VSJ` 86 a 116. Les items qui demandaient 99 px (650) descendent de 102 (a 548) ou de 132 (a 518, indice d'une ligne).
- **Tous les ordres.** `TPH` : 144 couples (item, ordre), bas d'indice de 444 a 548 ; `GNA` : 400 a 548 ; `VSJ` : 418 a 496. Pire ordre de chaque item sous 551 : 24 sur 24, trois fois. Couples au-dessus du pli au depart et en dessous apres : 0 (`agreger-r3.mjs`, ligne "permutations").
- **Autres largeurs versees.** 720 : 24 sur 24 sur les trois jeux, bas de l'indice au plus 548 (`TPH` 492 - 548, `GNA` 400 - 548, `VSJ` 418 - 496). 992 : 24 sur 24 aussi, au plus 518 (`TPH`), 444 (`GNA`), 418 (`VSJ`) ; sans la forme, a 992, `TPH` est a 15 sur 24 et `GNA` a 15 sur 24 (`VSJ` 24 sur 24).
- **Marge de 3 px et polices** (`polices-r3.mjs`, neuf polices imposees au `<body>`, apres une erreur, 744, items les plus bas ; les polices imposees ne remplacent pas un appareil) :

| Item (base -> forme, police du poste) | Polices ou la forme depasse 551 | Cote la plus haute |
|---|---|---|
| `TPH` 4 (650 -> 518) | aucune | 548 (Verdana, Courier New : l'indice passe a deux lignes) |
| `GNA` 9 (650 -> 548) | aucune | 548 |
| `VSJ` 15 (582 -> 496) | **Courier New : 570** (base 664) | 570 |
| `ce1-texte-narratif` 18 (684 -> 552) | toutes sauf Times New Roman (478) | 582 (Verdana, Courier New, Comic Sans MS) |

- **Revelation (hors critere : `Q-R2` = non).** La reponse a presser (`.reponse-attendue`) finit au plus a **550** sur les 72 items (base : 600 a 682 sur `TPH`, 518 a 682 sur `GNA`, 566 a 614 sur `VSJ` ; 0, 1 et 0 sur 24 au-dessus du pli ; forme : 24, 24 et 24 sur 24). L'indice, qui reste affiche a la revelation, finit a 530 - 634 (`TPH`), 486 - 634 (`GNA`) et 504 - 582 (`VSJ`) : au-dessus du pli sur 1, 1 et 22 items sur 24 (0, 0 et 0 sans la forme).

## 5. Les autres jeux `bandit-manchot`

### 5.1 Echantillon nomme (`r3-echantillon.json`, `analyser-echantillon-r3.mjs`)

40 jeux, 3 parties de dix manches chacun, politique aveugle (deux appuis sur la premiere proposition non barree, puis un appui sur une barree, puis la reponse revelee), mesure appariee a 720, 744 et 992 : **2193 etats par largeur**. Les jeux :

- francais CE1 : `texte-narratif-ce1`, `conjugaison-present-ce1`, `passe-compose-ce1`, `categories-ce1`, `ordre-alphabetique-ce1` ; francais CP : `syllabes-cp`, `texte-narratif-cp`, `position-syllabe-cp`, `son-dans-mot-cp`, `lettre-du-son-cp`, `le-bon-son-cp`, `nature-mot-cp` ;
- a texte support ou tableau : `comprehension-cm1`, `substituts-cm1`, `histoire-geographie-lire-un-document-cm1`, `sciences-extraire-information-cm1` ;
- a image : `figures-planes-cp`, `monnaie-cp`, `solides-ce1`, `decrire-une-oeuvre-cp`, `sens-multiplication-ce1` ;
- propositions longues (jusqu'a 102 signes) : `usages-numeriques-cm1`, `fait-opinion-cm1`, `vote-election-cm1`, `institutions-cm1`, `etapes-developpement-cm1`, `roles-ecole-commune-cp`, `vocabulaire-musical-cp`, `devise-cp` ;
- propositions courtes ou deux propositions : `additions-ce1`, `tables-multiplication-cm1`, `homophones-cm1`, `comparer-nombres-cp` ; quatre propositions : `conjugaison-present-cm1`, `accords-groupe-nominal-cm1` ;
- autres : `mots-de-liaison-cm1`, `fractions-cm1`, `accord-sujet-verbe-cm1`, `additions-cp` (jeu tire) ;
- `rapprocher-deux-oeuvres-cp` (`ART-CP-10`, temoin).

Resultat, par largeur (720 / 744 / 992) :

| Mesure | 720 | 744 | 992 |
|---|---|---|---|
| Etats ou l'indice (erreur) ou la reponse revelee etait visible sans la forme (bas <= 551) et ne l'est plus avec | **0** | **0** | **0** |
| Etats ou l'indice (erreur) est plus bas avec la forme | 0 | 0 | 0 |
| Etats ou un element deborde a droite avec la forme et pas sans | 0 | 0 | 0 |
| Etats ou l'indice (erreur) ou la reponse revelee n'etait pas visible sans la forme et l'est avec | 288 | 292 | 282 |
| Etats ou le bloc `.message-jeu` est plus haut avec la forme | 68 | 62 | 41 |
| Hausse maximale du bloc | +58 px | +58 px | +12 px |

Le bas de l'ecran gagne de 6 a 132 px a chaque etat hors `ART-CP-10` ; **aucun etat ne descend** (gain minimal 6 px : `vote-election-cm1`, revelation, 720). `ART-CP-10` : 66 mesures sur 66 identiques sans et avec la forme (`:not(.ecran-deux-oeuvres)` tient).

**Ou le bloc du message est plus haut** (R3-4 : dans une colonne de 444 px, un texte long passe sur plus de lignes que sur 696 px) :
- `institutions-cm1`, revelation, `Quel est le role du conseil municipal ?` : +58 px a 744 (reponse longue a 2 rem gras sur plusieurs lignes) ; a 720 aussi `vote-election-cm1`, revelation, +58 ;
- 26 revelations a +10 px a 744 (`fait-opinion-cm1`, `vote-election-cm1`, `institutions-cm1`, `etapes-developpement-cm1`, `devise-cp`, `comprehension-cm1`, `histoire-geographie-lire-un-document-cm1`, `sciences-extraire-information-cm1`) ;
- `conjugaison-present-cm1` : **35 etats d'erreur sur 35, +12 px** : ce jeu n'a pas d'indice, le message se reduit au signal, dont le verdict passe sur deux lignes dans les 15 rem (76 px au lieu de 64). Le gain du haut (44 px et plus) l'emporte.
Dans tous ces etats le bas de l'ecran descend quand meme moins bas qu'avant (gain de 6 px au moins) et aucun indice ni aucune reponse revelee ne perd le pli (ligne 1 du tableau).

### 5.2 Clause de non-regression de `S57-TPH-CE1-R1` (`Q-R4`)

| Element de la clause | Mesure | Sortie |
|---|---|---|
| 1. 22 items de `ce1-verbe-sujet` (484 - 534) | 22 sur 22 au-dessus du pli, bas au plus 448 | tenu |
| 1. `les gateaux` de `ce1-accord-groupe-nominal` (436 - 486) | 400 (bas) | tenu |
| 2. 11 items de `ce1-texte-narratif` a 550 | 11 sur 11 a 434, dans les six ordres | tenu |
| 3. `additions-cp`, tous ses items | **echantillon seulement** : 22 enonces differents vus sur 65, 33 erreurs, bas d'indice 486 -> 370 (un seul cote), reponse revelee 518 -> 402 | **non mesure en entier** |
| 4. `comprehension-cm1`, tous ses items | 20 sur 20 mesures a 744 dans les six ordres : base 1 sur 20 au-dessus du pli, forme 9 sur 20 en l'ordre servi (8 sur 20 au pire ordre) ; couples (item, ordre) passes de dessus a dessous : 0 sur 120 | tenu (aucun item n'a perdu le pli) |

Cote de reference de 3 et 4 (`Q-R5`) : le bras "base" des releves, date de cette passe, jar `6dbb855d`, 744 x 551, methode de la section 1. `git merge-base --is-ancestor 6dbb855d <commit livre>` et `git diff --stat 6dbb855d <commit livre>` : voir le retour de ce commit (le commit livre est un commit de mesure, sans fichier de production).

## 6. Effets de bord

1. **L'ascenseur apparait a la revelation, et les cases bougent de 7,5 px (`nadia-prof`, `Q-T11`).** Avec la forme, le document tient dans 551 px apres une erreur, puis depasse a la revelation (l'indice et la phrase de revelation s'ajoutent). Le navigateur ajoute alors une barre de 15 px : les cases se decalent de 7,5 px a 744 (le conteneur est centre) et, a 720, se retrecissent de 15 px. **Echec1 -> revelation, 72 items** (`r3-trois-jeux.*.json`) : a 744, 1 sur 72 sans la forme, **45 sur 72 avec** ; a 720, 1 sur 72 contre **41 sur 72** (largeur des cases qui change) ; a 992, 31 sur 72 contre 16 sur 72. Meme chose sur `ce1-texte-narratif` et `comprehension-cm1` (0 sur 40 contre 19 sur 40 a 744). Je ne l'ai pas corrige : a la revelation le bas du message va jusqu'a 634 (`r3-trois-jeux.cand.json`), l'ascenseur est donc inevitable sans reprendre encore des pixels (un essai de rembourrage bas a zero, non verse, ne l'a pas enleve) ; `scrollbar-gutter: stable` serait une regle globale, hors de mon perimetre. **Aucune rangee ne change** (0 sur 72 a 720, 744 et 992). La question de `N6` est a `nadia-prof`.
2. **Le verdict d'erreur est sur deux lignes dans les 15 rem** (`Ce n'est pas ca.` / `Essaie encore !`, la coupure tombe entre les deux phrases grace aux espaces insecables deja servies par `PartieController` a `6dbb855d`). Un texte insecable plus large que la colonne deborderait : sous Courier New le texte du verdict depasse le bord droit du signal de 24 px (`r3-polices-*.json`, champ `debordVerdict`) ; sous Verdana il reste 8 px ; sous la police du poste 33 px. Aucun element ne sort a droite de la fenetre dans les 6579 mesures (2193 etats, trois largeurs : `debord neuf : 0`), mais cette mesure ne voit pas un texte qui sort de son propre cadre ; seul `polices-r3.mjs` le voit, sur quatre items. Une constante du verdict dont une phrase insecable depasserait 156 px poserait le meme probleme.
3. **Le haut de page** : le score commence au bord de la bande de l'en-tete (boite du score a 82 - 112) ; il commencait a 118. Sa ligne de 30 px pour un texte de 20 px laisse environ 5 px d'air au-dessus des lettres (je l'ai regarde sur capture, captures non versees : le score est serre sous la bande). **C'est la plus visible des cinq regles et la seule que je ne sais pas defendre pour l'enfant** : elle vaut 32 des 102 px, et 4 px rendus remettent le pire item a 552. L'ecran de fin de partie et les autres ecrans gardent leurs 32 px : le contenu de l'ecran de fin demarre 32 px plus bas que celui de la derniere manche.
4. **Les rangees de propositions sont a 8 px l'une de l'autre** (16 avant) ; l'ecart lateral reste 16 px. La cible tactile (66 px de haut, 44 au plancher) ne change pas.
5. **`ART-CP-10`** : 66 mesures sur 66 identiques (`:not(.ecran-deux-oeuvres)`). Je n'ai pas joue son pilote (`J5` d'`yanis-qa`) : la forme ne le touche pas, je ne l'affirme pas au-dela de ces 66 mesures.
6. **`designation` et `saisie`** (5 jeux) : leur fragment ne contient aucun `.propositions` (`curl` sur `POST /parties/<id>` pour les cinq : 0 occurrence ; le jeu `phrases-types` en contient 1). Les selecteurs ne les atteignent pas.
7. **Garde-fous du depot.** Feuille de production + candidat, `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test` : **492 tests, 0 echec** (mesure partielle, perimetre `adapter-web` et ses dependances amont, pas le depot). **Premier jet, rouge** : le sujet etait `.ecran-de-jeu:has(> .cabinet)` ; `CabinetDeLaMachineTest` (2 tests : `lesDimensionsDuCabinetSontExprimeesEnRemOuEnPourcentJamaisEnPixels` et `chaqueSelecteurEpingleEstEprouveParLeJeuFautif`) lit toute regle qui nomme une classe en `cabinet` et en exige une version fautive dans le jeu `contreexemple/CabinetDeLaMachineTest/dimension-en-pixels`. J'ai donc change le sujet en `:has(.propositions)`. La feuille de production a ete restauree (`git checkout`), `git status` ne montre aucun fichier modifie hors `squad/`.
8. **Saillance** : aucune propriete de taille, de rembourrage, de couleur ou de largeur d'un bouton ou d'un libelle n'est touchee (`ADR-0017` n'est pas en jeu). La position change : le message est a droite du signal et non dessous, tout monte de 48 px. Ce que l'enfant lit en premier (l'enonce a 2 rem, le signal a 3 rem) garde sa taille ; l'indice garde ses 1,25 rem et son cadre, et se lit a droite du signal, pas dessous.

## 7. Ce que la forme ne leve pas, et une option

**`ce1-texte-narratif`** (`S57-RCT-CE1-R1`, cote a gagner 133 px) : base 11 sur 20 au-dessus du pli (550 pour 11 items ; 602 pour 5 ; 632 pour 3 ; 684 pour 1, dans l'ordre servi) ; forme **19 sur 20 en l'ordre servi, 17 sur 20 dans le pire des six ordres** : les items 13 (`Que fait Ines en dernier ?`), 17 (`Que fait Ibrahim en dernier ?`) et 18 (`Que se passe-t-il a la fin ?`) finissent a **552** (684 - 132). **Un pixel.** Les items 13 et 17 valent 602 ou 684 selon l'ordre des propositions, l'item 18 684 dans les six : les cotes du backlog (Ibrahim, Chloe a 684) sont celles du pire ordre, pas un ecart.

**Option** (`candidat-r3-option-bandeau.css`, **non comprise dans la forme**) : le bandeau du meuble passe de 2 rem a 1,75 rem (-4 px) ; le visage (18 px) et l'accent peint restent. Mesure a 744 (`r3-option-bandeau.*.json`) : `ce1-texte-narratif` **20 sur 20 au-dessus du pli, bas au plus 548, dans les six ordres** ; `TPH` 24 sur 24, 470 - 544. **Cette regle nomme `.cabinet-bandeau`** : elle entre dans le perimetre de `CabinetDeLaMachineTest` et demande de completer le jeu fautif (comme au poste 8), dans le meme commit. Je ne la propose pas pour la forme commune : elle touche l'habillage du meuble, que `nadia-prof` a garde pour `L1`. Elle est la; si 133 px comptent, c'est la plus petite que j'ai trouvee.

## 8. Ce qui reste a mesurer

1. **`additions-cp`** : les 65 enonces et leurs six ordres (`Q-T8`, `CR4`). Vu : 22 enonces sur 65, un seul cote. Les 43 autres ne sont pas mesures ; le script qui les enumere reste a ecrire (affectation de `sofia-sm`).
2. **102 jeux `bandit-manchot` sur 145** n'ont aucun item mesure (43 jeux touches : les 3 jeux de la demande, 40 d'echantillon, dont 2 aussi en entier). Les 40 d'echantillon : 3 parties de dix manches, pas tous les items.
3. **Hors 720 a 992** : sous 720 px la forme ne s'applique pas (`@media`), rien n'a ete mesure ; 1200 et plus : mesure dans la passe, non versee.
4. **Appareils reels** : neuf polices imposees sur quatre items, pas un appareil. La tablette de 744 x 551 de `DEC-0040` n'a pas ete jouee.
5. **Lecteur d'ecran** (`F11`) : l'ordre de lecture du message (signal puis texte) n'a pas change dans le DOM ; je ne l'ai pas ecoute.
6. **Jar de la forme** : aucune mesure sur un jar qui porte la forme dans `recreo.css`. Le candidat est injecte apres la feuille reelle ; l'ordre de cascade est le meme que celui d'une feuille versee en fin de fichier, mais c'est une mesure d'avant versement, a rejouer sur la tete de la branche de la forme (`Q-R5`).
7. **Rejeu de `nadia-prof`** : sur son jar, a son oeil.

## 9. Ecarts et limites de cette passe

- Le pilote a ete reecrit deux fois en cours de route (mesure par largeur, puis mesure appariee) ; **tous les chiffres cites ci-dessus viennent de la derniere version versee**. Le releve `r3-base-744-sans-style.json` vient de la premiere version (voir section 1, point 3).
- Les premiers releves paraissaient inverses (bras "base" et "candidat" echanges : l'interrupteur etait a l'envers). Trouve en lisant le resultat, corrige, tout est remesure sur la version versee.
- Le "pli" que mesure le critere (`bas de .indice <= 551`) n'est pas le bas du verdict : pour 3 items de `ce1-texte-narratif` le verdict finit a 570, sous le pli, avec l'indice a 552.
- Le commentaire de R3-1 dans `candidat-r3.css` a ete reecrit apres les dernieres mesures ; les regles, elles, n'ont pas change depuis.
- Les cotes sont des cotes du document, a `scrollY = 0` ; la fenetre de 551 px de haut est emulee, pas une fenetre de bureau.
- Releves compactes (`compacter-r3.mjs`) : seuls les champs lus par les trois scripts d'agregation sont gardes ; les sorties brutes (une trentaine de Mo) ne sont pas versees.

## 10. Reproduction

Prerequis : un JDK 21 declare dans `~/.m2/toolchains.xml`, un Chrome, Node 22 ou plus. Le chemin de Chrome et de `java` ne s'ecrit pas ici.

```
bash outils/mvn-jdk21.sh -B -q clean package -DskipTests
java -jar bootstrap/target/recreo.jar --server.port=<port libre>
node squad/conformite/releves-s57/pilote-r3.mjs <chrome> <profil-jetable> http://127.0.0.1:<port> <sortie> squad/conformite/releves-s57/candidat-r3.css 744,720,768,992,1200 <port-de-debogage> <items.json> bandit-manchot-phrases-types-ce1,bandit-manchot-accord-groupe-nominal-ce1,bandit-manchot-verbe-sujet-ce1
node squad/conformite/releves-s57/agreger-r3.mjs squad/conformite/releves-s57/r3-trois-jeux.cand.json --ref squad/conformite/releves-s57/r3-trois-jeux.base.json
node squad/conformite/releves-s57/pilote-r3-echantillon.mjs <chrome> <profil-jetable> http://127.0.0.1:<port> <sortie.json> squad/conformite/releves-s57/candidat-r3.css 744,720,992 <port-de-debogage> <jeux,csv>
node squad/conformite/releves-s57/analyser-echantillon-r3.mjs squad/conformite/releves-s57/r3-echantillon.json 744
```

`items.json` : `{ "<jeu>": [ {enonce, bonneReponse, distracteurs, indice} ] }`, extrait du YAML de `adapters/adapter-content/src/main/resources/contenu/` ; le jeu est joue sans le lire.
