# s57, palier 5 : seconde lecture de l'horloge `MAT-CE1-11` (`ce1-lire-l-heure`), après la correction de `lea-dev`

- **Auteure** : `nadia-prof`, professeure des écoles, seule autorité sur le Done pédagogique.
- **Date** : 2026-10-28, 15:29 (UTC+01:00), lue par `date`.
- **Arbre** : branche `s57-p5-nadia-lecture2-horloge`, base `round-two` à `cd74dc79` (fusion de `s57-p5-lea-horloge-corr`). Aucun
  push, aucun rebase, aucune fusion. Ce fichier est le seul que j'écris.
- **Type** : seconde lecture, après la correction (`DEC-0120` A.272, condition C2). **Ni audit à l'écran, ni Done, ni refus formel,
  ni bloc de validation rempli, ni identifiant de condition ou de réserve neuf** : ce qui est numéroté ici l'est dans ce fichier, sans
  préfixe. Ni paquet, ni dessin, ni test, ni catalogue, ni matrice, ni registre, ni backlog, ni mes textes précédents.
- **Audit dégradé, déclaré** : je n'ai pas joué le jar et je n'ai rien regardé à l'écran. J'ai lu le paquet, le diff des dessins, le
  garde et son contre-exemple, et j'ai relancé deux tests.
- **Lu** : `lecture-nadia-premier-jet-horloge-mat-ce1-11.md` ; `arbitrage-nadia-horloge-strategie-de-paire-et-pli.md` ;
  `rapport-lea-dev-palier-5-horloge-mat-ce1-11.md`, section *"Corrections après la lecture"* ; `ce1-lire-l-heure.yaml` (en entier) ;
  `LesCadransMontrentCeQueLeursItemsAffirmentTest.java` (lignes 310 à 459, et les lignes du premier jet figé) ;
  `avis-ines-palier-5-horloge-mat-ce1-11.md` (F1, F2, méthode du relevé) ; story `S57-BR-MAT-CE1-11-HORLOGE` de
  `docs/backlog/backlog.md` ; `FICHE-ce1-lire-l-heure.md` ; `DEC-0120`, texte de `Q-273-1`.

## Résumé

| Point | Verdict de seconde lecture |
|---|---|
| Arbitrage 4.1, exigences 1 à 3 | **Tenues toutes les trois** (section 1) |
| Exigence 2 de ma lecture (glyphes, relevé) | **Tenue**, inchangée depuis mon arbitrage : aucun dessin n'a bougé (section 1) |
| Les six distracteurs changés | **Plausibles tous les six**, `11 h 30` le plus faible et recevable ; un seul sens de lecture sur chaque cadran (section 2) |
| Les douze items | **Douze justes**, aucun à reprendre (section 2.2) |
| Avant l'audit à l'écran | quatre choses, dont aucune n'est à `lea-dev` (section 3) |
| Réserves | **a** maintenue ; **c**, neuve et datée, sur une stratégie que ma barre n'a jamais comptée (section 4) |
| Troisième lecture | **Je ne l'exige pas** (section 5) |

## 1. Les exigences, une par une

| Exigence | Tenue | Preuve que j'ai lue ou relancée |
|---|---|---|
| Arbitrage 4.1, point 1 : `S1` à `S4` au plus 6 sur 12, donc 6 exactement, `horloge-f` et `horloge-g` sur l'heure précédente, le reste inchangé | **oui** | Relancé par moi : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=LesCadransMontrentCeQueLeursItemsAffirmentTest -Dsurefire.failIfNoSpecifiedTests=false`, périmètre `bootstrap` et ses amonts, cette classe seule, JDK 21.0.10 annoncé par l'enveloppe : `BUILD SUCCESS`, 14 tests, 0 échec. Ligne affichée, recopiée : `strategies de paire : S1 = 6, S2 = 6, S3 = 6, S4 = 6 sur 12 items`. Ce qui n'a pas bougé : `git diff --stat a5d5026b cd74dc79` sur le répertoire des illustrations est vide ; le diff du paquet entre ces deux commits ne touche que six lignes `distracteurs` et le commentaire d'en-tête ; les douze `bonneReponse`, les douze distracteurs d'aiguilles inversées, la forme chiffrée et les quarts à 9 sur l'heure suivante sont ceux du premier jet |
| Arbitrage 4.1, point 2 : le garde de paire écrit selon ma 1.3, dans le commit qui corrige le contenu | **oui** | `f33d03b1` porte à la fois le paquet et le garde. Lu dans `comptesDePaire` : regroupement par minutes, item sans paire perdu pour les quatre, `S1` et `S2` sur les heures écrites de 1 à 12, `S3` et `S4` sur `floorMod(b - a, 12)` avec l'écart de 6 perdu, dénominateur égal au nombre total d'items (`compte[0] * 2 > items.size()`), stratégie jamais appliquée rouge. C'est ma définition. Le premier jet figé est le blob `42eeeced` de `a5d5026b` (`git hash-object` et `git rev-parse` rendent le même) ; `lePremierJetDeLHorlogeFigeEstRefuse` le refuse par `assertThatThrownBy` |
| Arbitrage 4.1, point 3 : le rapport donne le sens de chaque voisin et recopie les comptes du garde | **oui** | Tableau item par item du rapport, six précédentes et six suivantes ; ses comptes (6, 6, 6, 6 sur le paquet ; 10, 2, 12, 0 sur le premier jet) sont ceux que j'ai écrits dans mon arbitrage et, pour le paquet, ceux que le garde m'affiche |
| Lecture, exigence 2 : glyphes relus et relevé à 744 x 551 au `getBoundingClientRect()`, petite aiguille sur un quart à 3 et un quart à 9 | **oui** | Déclarée tenue dans mon arbitrage (section 3) sur l'avis d'`ines-design` : F1 (aucun glyphe ne tombe, plancher de 14 px tenu), F2 (déport de 3,65 px), cote par `getBoundingClientRect().bottom + scrollY`, `horloge-g` et `horloge-j` regardés. Aucun dessin n'a changé depuis (diff vide ci-dessus) : le relevé vaut pour les dessins servis |

Relancé aussi, hors brief, parce que `ADR-0086` clause 2 et les formes du paquet en dépendent : `bash outils/mvn-jdk21.sh -B -pl
adapters/adapter-content -am test -Dtest=PaquetCe1LireLHeureTest -Dsurefire.failIfNoSpecifiedTests=false`, périmètre
`adapter-content` et ses amonts, cette classe seule : `BUILD SUCCESS`, 6 tests, 0 échec. **Ces deux exécutions sont des mesures
partielles, pas le `verify` du lot ni la CI.**

## 2. Les distracteurs

### 2.1 Les six qui changent

Le cadran ne change pas ; je relis chaque distracteur contre le cadran servi (angles relus dans les douze fichiers, inchangés).

| Item | Cadran | Avant | Après | Ce qui le rend plausible pour un CE1 | Verdict |
|---|---|---|---|---|---|
| `horloge-a` | 3 h, petite aiguille sur 3 | 4 h | 2 h | heure voisine d'une graduation (`HOR-5` du CP, *"une heure voisine d'une ou deux graduations"*) ; à une heure pile, l'erreur d'un côté vaut celle de l'autre | plausible |
| `horloge-b` | 6 h, petite aiguille sur 6 | 7 h | 5 h | idem | plausible |
| `horloge-f` | 12 h 30, petite aiguille à mi-chemin de 12 et de 1 | 1 h 30 | 11 h 30 | l'enfant qui sait que l'heure est *"le nombre d'avant"* et prend celui d'avant 12. Plus faible que `1 h 30`, la petite aiguille étant à mi-chemin, mais à la même distance et non absurde. Je l'avais annoncé à relire ici : **recevable** | plausible, le plus faible des douze |
| `horloge-g` | 12 h 15, petite aiguille à 7,5 degrés après 12 | 1 h 15 | 11 h 15 | l'enfant qui voit la petite aiguille presque sur 12 et croit qu'elle n'y est pas encore arrivée. **Meilleur que `1 h 15`**, que j'avais jugé faible au premier jet | plausible |
| `horloge-h` | 4 h 15, petite aiguille à 7,5 degrés après 4, grande sur 3 | 5 h 15 | 3 h 15 | même erreur que `-g` ; s'y ajoute que la grande aiguille est sur 3 : un enfant qui lit la grande aiguille comme l'heure et sait que c'est un quart arrive aussi à `3 h 15`. Deux erreurs mènent à ce distracteur : il en est plus fort, ce n'est pas une seconde lecture du cadran | plausible, le plus fort des six |
| `horloge-i` | 10 h 15, petite aiguille à 7,5 degrés après 10 | 11 h 15 | 9 h 15 | même erreur que `-g` | plausible |

**Un seul sens de lecture, sur chaque cadran.** Aucun des six distracteurs n'est une heure que le cadran montre : la grande aiguille
reste sur 12, 6 ou 3, la petite aiguille est à sa place exacte, et aucun des six n'a la même position sur 12 heures qu'une autre
proposition de son item (garde M, `lecturesDe`, verte dans l'exécution de la section 1). Aucun ne lit le cadran sur 24 heures.

**Accents.** Les six lignes changées ne portent que des chiffres et `h` : aucun accent n'y est dû. Les textes accentués du paquet
(*"La petite aiguille a passé quel nombre ?"*, *"Mathématiques"*) n'ont pas été réécrits par la correction. Le contrôle systématique de
l'orthographe et de la ponctuation reste au `relecteur-pedagogique` (`DEC-0002`).

### 2.2 Les douze items

| Item | Réponse | Distracteurs | Juste | Lisible CE1 | Niveau | Distracteurs plausibles | Verdict |
|---|---|---|---|---|---|---|---|
| `horloge-a` | 3 h | 12 h 15, 2 h | oui | oui | heure pleine | oui | bon |
| `horloge-b` | 6 h | 12 h 30, 5 h | oui | oui | heure pleine | oui | bon |
| `horloge-c` | 9 h | 12 h 45, 10 h | oui | oui | heure pleine | oui, inchangé | bon |
| `horloge-d` | 3 h 30 | 6 h, 4 h 30 | oui | oui | demi-heure | oui, inchangé | bon |
| `horloge-e` | 8 h 30 | 6 h, 9 h 30 | oui | oui | demi-heure | oui, inchangé | bon |
| `horloge-f` | 12 h 30 | 6 h, 11 h 30 | oui | oui | demi-heure | oui, `11 h 30` faible | bon, regardé à l'écran |
| `horloge-g` | 12 h 15 | 3 h, 11 h 15 | oui | oui | quart | oui | bon, regardé à l'écran |
| `horloge-h` | 4 h 15 | 3 h, 3 h 15 | oui | oui | quart | oui | bon |
| `horloge-i` | 10 h 15 | 3 h, 9 h 15 | oui | oui | quart | oui | bon |
| `horloge-j` | 2 h 45 | 9 h, 3 h 45 | oui | oui | quart (*moins le quart*) | oui, inchangé | bon |
| `horloge-k` | 9 h 45 | 9 h, 10 h 45 | oui | oui | quart | oui, inchangé | bon |
| `horloge-l` | 11 h 45 | 9 h, 12 h 45 | oui | oui | quart | oui, inchangé | bon |

Énoncé, texte alternatif, indices : inchangés depuis ma lecture du premier jet, où je les ai jugés (sa section 1.2). `CON-5` et
`HOR-7` restent tenues. *"Regardé à l'écran"* n'est pas une réserve : c'est ce que je regarderai en premier à l'audit.

## 3. Ce qui reste à faire avant mon audit à l'écran

Rien n'est à corriger dans le paquet. Ce dont j'ai besoin pour auditer :

1. **Un `mvn -B clean verify` complet** (`DEC-0120` A.280 point 2), sur un commit nommé par son SHA, et le résultat inscrit dans la
   fiche, section *"État de la porte technique"*, par qui le lance. Je n'audite que ce SHA-là.
2. **Un jar construit depuis ce même SHA**, servi sur un port que je peux ouvrir, avec une entrée de catalogue **locale et non
   commitée** (point 3) pour que le jeu soit atteignable par l'interface, comme `lea-dev` l'a fait au premier jet. Qui le construit et
   le sert est une question ouverte du dépôt ; je dis seulement qu'il me le faut, et qu'il me faut les douze items atteignables, à
   744 x 551, avec une erreur possible sur chaque item.
3. **L'entrée de catalogue que je lirai**, quand le moment viendra (`QC3` : pas avant le Done ou un refus levé). Je ne l'écris pas dans
   le catalogue ; voici ce que je lirais, de ma main :
   - titre : *"L'heure sur l'horloge"* ;
   - consigne : *"Regarde les deux aiguilles, puis choisis la bonne heure."* (9 mots, une phrase, même gabarit que la consigne de
     `ce1-calendrier-dates`, *"Pense au calendrier, puis choisis la bonne réponse."*). Je ne reprends pas *"Quelle heure est-il ?"*
     comme titre : c'est l'énoncé de chaque manche.
4. **La story à jour avant l'audit.** La fiche dit que les conditions opposables sont *"celles de la story"*, et le critère 5 de
   `S57-BR-MAT-CE1-11-HORLOGE` cite encore mon exigence 1 du premier jet et sa convention *"12 h reste 12"*, avec *"Aujourd'hui : 10 sur
   12, rouge"* ; le critère 7 attend encore un relevé à 360 que mon arbitrage (2.2) retire des critères de ce paquet. Mon arbitrage les
   a routés à `camille-po` (section 6) ; ce n'est pas fait. Je n'audite pas contre un critère que j'ai remplacé : à `camille-po`, par
   `sofia-sm`.

Ce qui n'est pas dû avant l'audit : aucun redessin, aucun glyphe, aucun relevé de pli neuf, aucune lecture d'`ines-design`.

## 4. Réserves, non bloquantes

- **Réserve a, maintenue** (lecture, 3.2 ; arbitrage, 4.2). La proposition d'une autre forme est toujours fausse, 12 sur 12. La
  correction ne l'a pas touchée et je ne le demandais pas.
- **Réserve c, neuve, datée du 2026-10-28** (ce n'est pas un durcissement de ma barre de paire, qui est tenue). Mon arbitrage (4.3)
  écrivait qu'une stratégie conditionnée par la forme des minutes n'est pas dans la barre, et qu'avec trois items par forme elle garde au
  moins deux items sur trois dans chaque forme. Je l'ai comptée sur le paquet servi `[calcul]`, à la main, dans l'ordre du cadran :
  *"aux heures pleines et aux quarts à 15, prendre l'heure d'après dans la paire ; aux demies et aux quarts à 45, l'heure d'avant"* gagne
  **2 + 2 + 3 + 3 = 10 items sur 12**. Le plancher que trois items par forme permettent est 8 sur 12. Les deux 3 sur 3 viennent de moi :
  les quarts à 9 sur l'heure suivante sont ma condition, et les quarts à 3 tous sur l'heure précédente suivent l'exemple de faisabilité
  de mon arbitrage (1.4), que `lea-dev` a repris tel quel. Pour s'en servir, un enfant devrait découvrir, d'une partie à l'autre, une
  règle par forme sans regarder les aiguilles ; pour les quarts à 45, cette règle est d'ailleurs celle de la lecture (*"moins le quart"* :
  l'heure d'avant le nombre approché). Je n'en fais pas une exigence de ce lot. Ce qui la lèverait, dans un sprint suivant : plus de trois
  items par forme, ou la réserve a. Je reste la seule garde de cette propriété : aucun test ne la compte.

Ce que je regarderai à l'écran, sans réserve écrite : `11 h 30` sur `horloge-f` ; la petite aiguille à 7,5 degrés sur `horloge-g` à
`-l` ; les glyphes lus comme des chiffres ; rien de lisible dans les 5 px sous le pli ; les aiguilles près du moyeu sur `horloge-h` et
`-k`.

## 5. Coût

- **Cette lecture est la seconde lecture de l'horloge**, due parce que la correction a changé des propositions (A.272, condition C2).
  A.272 (règle point 1) et A.280 (point 3, geste (a)) la classent à 0,50 hors marge.
- **`Q-273-1`, question (ii)** : *"la seconde lecture du premier jet de l'horloge est-elle 0,50 hors marge, comme celle d'un lot de
  l'instance neuve ?"* Dans ses faits, elle est fermée : la seconde lecture a eu lieu, elle est unique, et elle est rangée au geste que
  A.272 et A.280 nomment. **Je ne fixe aucun prix** : le chiffre reste celui que ces deux articles écrivent, pas le mien. La question (i)
  n'est pas touchée.
- **Une troisième lecture n'est pas due.** Elle ne le serait que si je l'exigeais, et je ne l'exige pas : rien n'est à corriger dans le
  paquet (section 3). La prochaine fois que je lis ce lot, c'est mon audit à l'écran.
- Durée de cette lecture : non mesurée.

## 6. Routage

| À qui | Quoi |
|---|---|
| `sofia-sm` | l'ordre est inchangé : `verify` complet du lot, puis mon audit à l'écran ; section 3, points 1 et 2 |
| `camille-po`, par `sofia-sm` | section 3, point 4 (critères 5 et 7 de la story) ; réserve c, à garder pour un sprint suivant, sans story avant mon verdict |
| `lea-dev` | rien |

**Signé** : `nadia-prof`, s57, palier 5, 2026-10-28.
