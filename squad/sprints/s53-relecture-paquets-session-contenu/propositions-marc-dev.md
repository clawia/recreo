# [Sprint s53] Propositions de marc-dev - U1 - 2026-10-07

- **Auteur** : marc-dev, auteur des paquets `ce1-accord-groupe-nominal`, `ce1-masses-contenances`, `cm1-emc-principes-republique`
  (commits d'ajout `34fdd167` pour `ce1-accord-groupe-nominal`, `8de2927f` pour `ce1-masses-contenances`, `d27ba381` pour les trois EMC).
- **Base de mesure** : `5c1353a9` (tip de `round-two`), worktree `s53-marc-relecture`. Rien n'a ete compile ni lance : ce fichier
  ne contient que des lectures de fichiers et des `grep`.
- **Ce que ce fichier n'est pas** : une decision. Aucun YAML n'est edite a cette etape. `nadia-prof` decide P1, P3, P4, P6 ;
  `camille-po` decide P9. Les textes ci-dessous sont des propositions a appliquer apres leur decision (critere C1 du planning).
- **Convention des blocs YAML** : accents et espaces insecables (U+00A0) avant `:`, `?` et dans les guillemets francais, comme dans
  les paquets (`TYP-1`, `ADR-0067`). Les YAML proposes sont des paquets complets d'item, commentaires de famille compris, a coller
  tels quels.
- **Ecarts avec le planning**, tous releves en relisant les fichiers : la ligne de la bonne reponse de P1 est la 30 (juste) ;
  l'indice de P4 item 5 a seize mots (F6), ce que le planning ne dit pas ; l'item 9 de `principes` a le meme defaut que
  l'item 8 et n'est pas dans la liste de P4 (F4).

## Resume

| Point | Issue proposee | Items touches |
|---|---|---|
| P1 | corriger : "des beaux chapeaux" devient "les beaux chapeaux" | 1 (item 1) |
| P3 | corriger : remplacer le distracteur "la liberté" de l'item 4 ; l'en-tete reste vraie | 1 (item 4) |
| P4 | corriger les items 5, 6, 7, 8, 10, 11 (indice seul) ; item 9 propose en plus ; items 1 a 4 gardes | 6 (+1) |
| P6 | corriger l'item "1 kg, combien de g ?" ; item "1000 g, combien de kg ?" : corriger aussi (variante : accepter avec motif) | 2 |
| P9 | accepter avec motif, aucun YAML change (la decision reste a `camille-po`) | 0 |

## P1 - `ce1-accord-groupe-nominal.yaml`, item 1

### Constat

```
grep -n 'bonneReponse: "des beaux chapeaux"' adapters/adapter-content/src/main/resources/contenu/ce1-accord-groupe-nominal.yaml
30:    bonneReponse: "des beaux chapeaux"
grep -c 'des beaux' adapters/adapter-content/src/main/resources/contenu/ce1-accord-groupe-nominal.yaml
2   (lignes 30 et 31 : la bonne reponse et le distracteur "des beaux chapeau" ; "des beaux chapeaus" est la meme ligne 31)
```

Recherche d'un autre "des" suivi d'un adjectif place avant le nom pluriel dans une bonne reponse du paquet : aucun. Les autres
adjectifs avant le nom (`les petits chats`, `les gros gâteaux`, `les petits oiseaux`, `les grands arbres`) sont tous avec "les".
L'item 1 est donc le seul concerne.

### La norme

Ce que je sais, **sans avoir pu le verifier dans le depot** (`grep -rIl "de beaux" squad docs` ne rend que le planning et le backlog) :
quand un adjectif pluriel precede le nom, la norme ecrite remplace "des" par "de" ("de beaux chapeaux", "de petits oiseaux").
"des beaux chapeaux" est tres repandu a l'oral et dans l'ecrit courant, et les grammaires le decrivent comme tolere plutot que comme
faute, surtout quand adjectif et nom forment une unite ("des jeunes gens", "des petits pois"). Le point de `camille-po` dit
"la norme est de beaux chapeaux". **Je ne cite aucun ouvrage** : `nadia-prof` confirme la regle et son degre de tolerance.

### Issue proposee : corriger, par "les"

Pourquoi pas "de beaux chapeaux" : la forme normee oblige l'enfant de CE1 a un "de" a la place du "des" qu'il vient d'apprendre
comme pluriel de "un" ; c'est une regle que `FRA-CE1-15` ne nomme pas (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 5.6, "ce
qui fait la ligne de CE1 : l'adjectif, le `-x`, le féminin"), et les trois distracteurs porteraient alors "de" ou "des" pour des
raisons etrangeres a la ligne. Pourquoi pas un autre nom : le motif de l'item est le `-x` de "chapeaux" avec un adjectif en `-x`
("beaux") ; "les" garde l'item sans toucher a sa famille.

Conditions verifiees dans `ARBITRAGE-PREALABLE-s48-francais-ce1.md` :

- `GNA-CE1-1` : le pluriel en `-x` reste dans le meme item, les comptes (six en `-s`, douze en `-x`, six en `-e`) ne bougent pas.
- `GNA-CE1-2` : "beaux" (de "beau", en `-eau`) et "chapeaux" sont en `-eau`, hors exceptions ; aucun feminin.
- `GNA-CE1-3` : le groupe entier est propose ; les familles restent (a) marque oubliee ("les beaux chapeau") et (c) `-s` a la
  place de `-x` ("les beaux chapeaus"). La ligne `# GNA-CE1-3: a, c` reste vraie.
- `TRV-CE1-2` : "les beaux chapeaus" a le meme nombre de lettres que "les beaux chapeaux" (18) ; "les" et "des" ont la meme longueur,
  donc le nombre d'items ou la bonne reponse est strictement la plus longue ne change pas.
- `TRV-CE1-5` : l'indice reste a neuf mots, sans la reponse, et n'enseigne pas de regle fausse.
- Les deux distracteurs sont distincts de la bonne reponse et entre eux.
- Aucun autre item du paquet ne porte "les beaux chapeaux" (`grep -c "les beaux" ` rend 0 aujourd'hui).
- Garde-fous de test : `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` declare ce paquet par un texte qui compte
  "24 items sur 24" et "72 propositions" ; aucun de ces comptes ne change.

### Texte propose, item complet (remplace les lignes 27 a 32)

```yaml
  # GNA-CE1-3: a, c
  # GNA-CE1-1: pluriel en -x
  - enonce: "Quel groupe de mots est bien écrit ?"
    bonneReponse: "les beaux chapeaux"
    distracteurs: ["les beaux chapeau", "les beaux chapeaus"]
    indice: "Compare la fin de chaque mot entre les trois groupes."
```

### Variante, si `nadia-prof` veut la forme normee

`bonneReponse: "de beaux chapeaux"`, `distracteurs: ["de beaux chapeau", "de beaux chapeaus"]`. Je la deconseille pour la raison
ci-dessus ; si elle la prefere, l'indice et la famille ne changent pas, mais `TRV-CE1-2` est a recompter (une lettre de moins que
"des").

## P3 - `cm1-emc-principes-republique.yaml`, item 4

### Constat

```
sed -n 81,84p adapters/adapter-content/src/main/resources/contenu/cm1-emc-principes-republique.yaml
81  - enonce: "Quel principe de la République garantit que l'école publique ne favorise aucune religion ?"
82    bonneReponse: "la laïcité"
83    distracteurs: ["la fraternité", "la liberté"]
grep -n '^#.*la liberte. contre' adapters/adapter-content/src/main/resources/contenu/cm1-emc-principes-republique.yaml
27:# "le refus des discriminations" (item 5), ni "la liberte" contre "la laicite" (item 4). A juger par
```

L'en-tete (lignes 25 a 28, regle `EMC-5`) dit que "la liberté" n'est pas proposee contre "la laïcité" a l'item 4 ; l'item 4 la porte
en distracteur. Les deux lignes se contredisent, et c'est l'item qui est faux : l'en-tete dit la propriete que `nadia-prof` garde.

### Issue proposee : corriger l'item, laisser l'en-tete

Motif : le paquet lui-meme definit la laicite comme une liberte (item 9, bonne reponse "la liberté de croire ou de ne pas croire" ;
en-tete, lignes 18 a 20). Un enfant qui lit "ne favorise aucune religion" peut donc, sans erreur de lecture, penser "la liberté".
C'est exactement le "principe voisin que l'enonce permettrait aussi de nommer". Modifier l'en-tete pour autoriser ce distracteur
irait contre `EMC-5`, ecrite par `nadia-prof`.

Le remplacant ne peut pas etre "l'égalité" : "ne favorise aucune religion" se dit aussi "traite toutes les religions de la même
façon", le meme piege par un autre voisin. Il ne peut pas etre "le refus des discriminations" non plus. Je propose un mot qui n'est
pas un des cinq principes de `FER-1` et que l'enonce ne permet pas de nommer : **"la sécurité"** (premiere proposition) ; seconde
proposition : **"la démocratie"** (mot de l'article 1er de la Constitution, deja cite en en-tete, ligne 44 ; l'enonce ne parle
d'aucun vote). `nadia-prof` choisit ou en propose un autre. Aucun des deux n'enonce un interdit de croire ni de parler de religion
(en-tete, lignes 20 a 23).

Effet sur les garde-fous : aucun. Les propositions restent textuelles (`LeRang...` declare 11 items, 33 propositions, aucune
numerique) et la famille de l'en-tete ("FER-1") ne compte que les bonnes reponses.

### Texte propose, item 4 complet (remplace les lignes 81 a 84)

```yaml
  - enonce: "Quel principe de la République garantit que l'école publique ne favorise aucune religion ?"
    bonneReponse: "la laïcité"
    distracteurs: ["la fraternité", "la sécurité"]
    indice: "Ce principe dit comment l'école et l'État se comportent envers les croyances."
```

Variante : `distracteurs: ["la fraternité", "la démocratie"]`. L'indice de cet item n'est pas touche (voir P4 : il ne reformule pas la
bonne reponse).

## P4 - `cm1-emc-principes-republique.yaml`, indices

### Constat, reverifie item par item

```
grep -n 'indice:' adapters/adapter-content/src/main/resources/contenu/cm1-emc-principes-republique.yaml | awk -F'indice: ' '{print $1 split($2,a," ") " mots"}'
```

Longueurs en mots : item 1 : 10 ; 2 : 9 ; 3 : 8 ; 4 : 12 ; **5 : 16** ; 6 : 12 ; 7 : 10 ; **8 : 14** ; **9 : 13** ; **10 : 16** ; 11 : 8.

Critere de lecture : l'indice **reformule la bonne reponse** s'il redit son contenu au point qu'on peut choisir sans connaitre la
notion. Les items 1 a 5 demandent un **nom** de principe (FER-3, tache 1) : leur indice glose le nom. Les items 6 a 11 demandent un
**sens** (FER-3, tache 2) : leur indice glose la phrase qui est la bonne reponse.

| Item | Bonne reponse | Indice actuel | Reformule la reponse ? |
|---|---|---|---|
| 1 | la liberté | "Chacun peut s'exprimer sans être puni pour ce qu'il pense." | glose du nom, comme 2, 3 et 5 |
| 2 | l'égalité | "Tous les enfants ont les mêmes droits devant l'école." | glose du nom |
| 3 | la fraternité | "Ces élèves se soutiennent les uns les autres." | glose du nom |
| 4 | la laïcité | "Ce principe dit comment l'école et l'État se comportent envers les croyances." | non : dit ou regarder, ne definit pas |
| 5 | le refus des discriminations | "La loi punit le fait de traiter quelqu'un moins bien à cause de ce qu'il est." | glose du nom, **et 16 mots** |
| 6 | jusqu'à ce qu'elle nuise à autrui | "La liberté d'une personne s'arrête là où commencent les droits des autres." | **oui** : "nuise à autrui" et "droits des autres" disent la meme chose |
| 7 | tous ont les mêmes droits, sans distinction | "Les personnes sont différentes, mais leurs droits sont les mêmes." | **oui** : "mêmes droits" est repris mot pour mot |
| 8 | se soutenir et s'entraider entre citoyens | "Ce mot parle de solidarité entre des personnes qui ne se connaissent pas toutes." | **oui** : "solidarité" est le synonyme de "s'entraider", et "ne se connaissent pas" ecarte le distracteur "même famille" ; 14 mots |
| 9 | la liberté de croire ou de ne pas croire | "Chaque élève décide pour lui-même, et l'école ne choisit pas à sa place." | **oui, en partie** : "décide pour lui-même" = "croire ou ne pas croire", et "l'école ne choisit pas" ecarte le premier distracteur ; 13 mots |
| 10 | ne pas traiter quelqu'un moins bien à cause de ce qu'il est ou de ce qu'il croit | "Remarquer une différence n'est pas la faute, mais en faire une raison de traiter mal, si." | **oui** : "raison de traiter mal" reprend la bonne reponse et "remarquer... n'est pas la faute" ecarte le distracteur "ne jamais remarquer" ; 16 mots |
| 11 | elle respecte toutes les croyances sans en favoriser aucune | "La République ne prend parti pour aucune croyance." | **oui** : "ne prend parti pour aucune" = "sans en favoriser aucune" |

Comptage : **cinq indices de sens reformulent la reponse (6, 7, 8, 10, 11)**, un sixieme en partie (9). Les six items du point
P4 sont 5, 6, 7, 8, 10, 11 : **l'item 5 n'est pas une reformulation de la reponse** (la reponse est un nom, l'indice le glose comme
aux items 1 a 3) mais son indice a seize mots. **L'item 9, absent de P4, a le meme defaut que l'item 8.**

`TRV-CE1-5` (indice : une methode ou un lieu ou regarder, jamais la reponse, douze mots au plus) vient de l'arbitrage de francais CE1.
**Son application a l'EMC CM1 est a confirmer par `nadia-prof`** (planning, critere de `S53-REL-3`). Sans elle, la limite de douze mots
tombe et il reste le seul critere "jamais la reponse".

### Issue proposee

- **Items 6, 7, 8, 10, 11 : corriger** l'indice. Motif : ils redisent la bonne reponse.
- **Item 5 : corriger** (variante : accepter l'indice avec un motif si `TRV-CE1-5` ne s'applique pas a l'EMC, dans quel cas
  les items 1 a 3 sont a traiter de la meme maniere). Motif : seize mots, et l'indice definit le nom que l'enonce fait deja deviner.
- **Item 9 : corriger, propose en plus de la liste de P4.** Je le sors de la liste ecrite par le relecteur ; `nadia-prof` le garde
  ou l'ecarte.
- **Items 1 a 4 : garder.** Ils glosent le nom d'un principe a partir d'une situation et tiennent en douze mots au plus.

**Ce que ces indices ne font plus** : enseigner. Un indice qui ne donne ni la reponse ni son sens ne dit plus qu'une methode ("compare",
"élimine"). C'est le prix de la regle, et un item sans indice reste possible (`ADR-0016` : la cle `indice` est optionnelle). Je propose
une methode par item, sans retirer l'indice ; si `nadia-prof` juge ces methodes vides, la variante est de **retirer l'indice**
des items touches.

**`PAR-14` (planning, Q7)** : sur onze items, 5 indices reecrits = 45,5 % ; 6 (avec l'item 5 ou 9, selon la decision) = 54,5 % ;
7 = 63,6 %. Seul le champ `indice` change, jamais l'enonce ni les propositions. A `nadia-prof` de dire si cela compte pour la regle.

### Textes proposes, items complets

Chaque bloc recopie l'item du fichier a `5c1353a9` (enonce, reponse, distracteurs inchanges, espaces compris) ; seule la ligne `indice` differe. Il remplace l'item de meme numero (lignes 85-88, 89-92, 93-96, 97-100, 101-104, 105-108, 109-112).

Item 5 (11 mots) :

```yaml
  - enonce: "Un employeur n'a pas le droit de refuser un emploi à quelqu'un à cause de son origine : la loi l'interdit. Quel principe de la République cela protège-t-il ?"
    bonneReponse: "le refus des discriminations"
    distracteurs: ["la laïcité", "la fraternité"]
    indice: "Relis ce que la loi interdit, puis compare avec chaque principe."
```

Item 6 (10 mots) :

```yaml
  - enonce: "Jusqu'où va la liberté de chacun dans la République ?"
    bonneReponse: "jusqu'à ce qu'elle nuise à autrui"
    distracteurs: ["jusqu'à ce qu'un adulte la refuse", "jusqu'à ce que la majorité ne soit pas d'accord"]
    indice: "Teste chaque phrase avec un exemple pris dans la classe."
```

Item 7 (11 mots) :

```yaml
  - enonce: "Que veut dire l'égalité dans la devise de la République ?"
    bonneReponse: "tous ont les mêmes droits, sans distinction"
    distracteurs: ["tous doivent être pareils", "tous doivent gagner la même somme"]
    indice: "Lis les trois phrases et élimine celles qui te semblent fausses."
```

Item 8 (10 mots) :

```yaml
  - enonce: "Que veut dire la fraternité dans la devise de la République ?"
    bonneReponse: "se soutenir et s'entraider entre citoyens"
    distracteurs: ["avoir les mêmes idées que les autres", "faire partie de la même famille"]
    indice: "Relis la devise de la République et le mot demandé."
```

Item 9 (9 mots) :

```yaml
  - enonce: "Que garantit la laïcité à chaque élève de l'école publique ?"
    bonneReponse: "la liberté de croire ou de ne pas croire"
    distracteurs: ["l'enseignement de la même religion à tous les élèves", "le droit de choisir les règles de l'école"]
    indice: "Compare chaque phrase avec ce que dit la question."
```

Item 10 (11 mots) :

```yaml
  - enonce: "Que veut dire refuser les discriminations ?"
    bonneReponse: "ne pas traiter quelqu'un moins bien à cause de ce qu'il est ou de ce qu'il croit"
    distracteurs: ["ne jamais remarquer les différences entre les personnes", "éviter de parler aux personnes différentes de soi"]
    indice: "Cherche le mot refuser dans la question, puis relis chaque phrase."
```

Item 11 (12 mots) :

```yaml
  - enonce: "Que veut dire « la République est laïque » ?"
    bonneReponse: "elle respecte toutes les croyances sans en favoriser aucune"
    distracteurs: ["elle impose à tous la religion de la majorité", "elle demande à chaque citoyen de choisir une religion"]
    indice: "Pense au mot laïque : lis chaque phrase pour voir si elle convient."
```

## P6 - `ce1-masses-contenances.yaml`

### Constat

```
grep -n "kilo veut dire mille" adapters/adapter-content/src/main/resources/contenu/ce1-masses-contenances.yaml
74:    indice: "Le mot kilo veut dire mille."
78:    indice: "Le mot kilo veut dire mille."
grep -c 'bonneReponse: "1000 g"' adapters/adapter-content/src/main/resources/contenu/ce1-masses-contenances.yaml
1
```

Deux items, pas un : ligne 71-74, "1 kg, combien de g ?" (reponse "1000 g") et ligne 75-78, "1000 g, combien de kg ?" (reponse "1 kg").
Le backlog ne decrit que le premier.

### Issue proposee

- **Item "1 kg, combien de g ?" : corriger.** "mille" ecrit en lettres est la reponse "1000" ; l'indice la donne.
- **Item "1000 g, combien de kg ?" : corriger aussi** (variante : accepter avec motif). Motif de correction : l'indice y donne la
  relation qui fait le passage entier, il est de meme nature que le premier ; "1 kg" n'est pas ecrit dedans, ce que la variante
  invoque. Je corrige les deux par coherence : un item ou la relation est donnee et l'autre ou elle ne l'est pas ferait croire que la
  difference est dans la formulation, alors qu'elle est dans le sens de la conversion.

Observation hors point, **non proposee a la correction** : les items des lignes 37-40 et 41-44 (choix d'unite, reponses "le gramme"
et "le kilogramme") portent "Un kilogramme, c'est mille grammes." Sur ces items, la phrase n'est pas la reponse, mais elle donne la
conversion qu'on demande 30 lignes plus bas ; l'enfant qui a vu l'indice une fois n'a plus rien a retrouver. `nadia-prof` dit si
cela ressortit a P6.

Contrainte `MAS-CE1-6` (`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md`) : un indice n'enseigne pas que la taille donne la
masse ; les deux textes ci-dessous n'en parlent pas. Le rang de la bonne reponse (en-tete, lignes 20 a 22 : 5 hauts, 5 bas, 3 du
milieu) ne depend pas de l'indice. Aucune des deux nouvelles phrases n'ecrit "1000", "100", "10" ni "1 kg", donc aucun distracteur
n'y figure. `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` declare ce paquet (18 items ecartes) ; propositions inchangees.

### Textes proposes, items complets

```yaml
  - enonce: "1 kg, combien de g ?"
    bonneReponse: "1000 g"
    distracteurs: ["100 g", "10 g"]
    indice: "Cherche ce que veut dire le mot kilo dans le mot kilogramme."
  - enonce: "1000 g, combien de kg ?"
    bonneReponse: "1 kg"
    distracteurs: ["10 kg", "100 kg"]
    indice: "Pense au mot kilogramme : que veut dire kilo ?"
```

(Onze mots et huit mots.) Les deux indices disent ou regarder ; ils ne donnent ni la relation, ni un nombre.

## P9 - `cm1-emc-atteinte-autrui`, `cm1-emc-respect-differences`, `cm1-emc-principes-republique`

### Constat

```
grep -c "Mention exigee d'avance" adapters/adapter-content/src/main/resources/contenu/cm1-emc-*.yaml
atteinte-autrui 1, droit-devoir 0, emotion-ressentir-agir 1, fait-opinion 0, institutions 1, principes-republique 1,
regle-loi 1, regles-vie-collective 1, respect-differences 1, symboles 1, vote-election 1
grep -l "^# DEGRADE" adapters/adapter-content/src/main/resources/contenu/cm1-emc-*.yaml
(aucune sortie)
for p in <les onze> ; do grep -c "paquet: $p\$" adapters/adapter-content/src/main/resources/catalogue.yaml ; done
```

Les **neuf** paquets EMC CM1 qui portent la mention : trois non branches (ceux de P9, ajoutes par `d27ba381`) et **six branches et
valides** (`emotion-ressentir-agir`, `institutions`, `regle-loi`, `regles-vie-collective`, `symboles`, `vote-election`). Aucun des neuf
ne porte `DEGRADE`. Les six branches portent la meme mention sans marqueur.

La mention vient de `squad/programme/enseignement-moral-et-civique/competences-cm1.md`, section "Mention exigee d'avance si une ligne
bouge" : elle est ecrite pour **la matrice** ("la matrice porte cette mention"), pas pour un paquet. Le paquet la recopie en
commentaire d'en-tete. Les 25 fichiers qui portent `# DEGRADE` en ligne 1 (`grep -c '^# DEGRADE' .../contenu/*.yaml`) nomment, eux,
ce qui manque au paquet pour servir un verbe de sa ligne : "mesurer" (masses), la saisie (anglais), l'image (arts, HDA). **Je n'ai trouve
aucune definition ecrite du marqueur `DEGRADE`** dans `squad/`, `docs/` ni `CLAUDE.md` (`grep -rIn DEGRADE squad docs CLAUDE.md .claude` ne rend que le planning et le point P9 du backlog) ;
sa seule definition est l'usage et les messages des commits `10bad629` ("l'en-tete de chaque paquet porte DEGRADE et ce qui manque")
et `d27ba381`.

Verbes de la ligne servis : `EMC-CM1-02` "reconnaître... et savoir à qui s'adresser" : `atteinte-autrui` sert les deux (items 1 a 6
la reconnaissance, items 7 a 11 "À qui est-il important d'en parler ?", `grep -n "enonce:.*À qui"`). `EMC-CM1-03` et `EMC-CM1-06` :
"identifier ce qu'interdit" et "comprendre le sens", servis par les enonces des deux autres paquets. Je n'ai pas relu les onze items
de `respect-differences` pour cette phrase.

### Issue proposee : accepter avec motif, aucun YAML change

Motif : la mention n'est pas equivalente a `DEGRADE`. Elle borne ce que **le produit** sert d'EMC, pour les neuf paquets, et le depot
a deja branche six de ces paquets sans marqueur ; ajouter `DEGRADE` aux trois seuls les ferait paraitre degrades par rapport a
six qui disent la meme chose. Un `DEGRADE` se justifie par un verbe de la ligne que le paquet ne sert pas, ce que je ne constate pas
ici. Si `camille-po` juge la mention equivalente, ce sont **neuf** paquets, et non trois, qu'il faut traiter.

Ce que je ne fais pas : trancher. C'est la `DEC` de `camille-po` (Q6).

## Findings, recapitulatif

| Code | Constat | Point |
|---|---|---|
| F1 | l'item 1 de `ce1-accord-groupe-nominal` est le seul "des" + adjectif avant le nom | P1 |
| F2 | la regle "de beaux chapeaux" n'est ecrite nulle part dans le depot ; je ne l'ai pas verifiee dans un ouvrage | P1 |
| F3 | l'en-tete de `principes` (EMC-5) et l'item 9 ("liberté de croire") disent que "la liberté" est un voisin de "la laïcité" | P3 |
| F4 | cinq indices de sens reformulent la reponse (6, 7, 8, 10, 11), un en partie (9) | P4 |
| F5 | l'item 5 est dans la liste du relecteur mais son indice glose un nom, comme 1 a 3 ; il a seize mots | P4 |
| F6 | quatre indices depassent douze mots (5 : 16, 8 : 14, 9 : 13, 10 : 16) | P4 |
| F7 | l'application de `TRV-CE1-5` a l'EMC CM1 n'est pas arbitree | P4 |
| F8 | le backlog dit un item ("1 kg, combien de g ?"), le fichier en porte deux | P6 |
| F9 | les indices des lignes 40 et 44 donnent "un kilogramme, c'est mille grammes" | P6 |
| F10 | six paquets EMC CM1 branches portent la mention sans `DEGRADE` ; aucune definition ecrite du marqueur | P9 |

## Ce que je n'ai pas fait

Aucun YAML edite, aucun test lance, aucun jar joue : les paquets ne sont pas branches, rien ne s'affiche a l'ecran. Je n'ai relu aucun
autre fichier que ceux des cinq points. Je ne prononce aucune decision : P1, P3, P4, P6 a `nadia-prof`, P9 a `camille-po`.
