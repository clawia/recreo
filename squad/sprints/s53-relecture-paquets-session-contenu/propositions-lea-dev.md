# [Sprint s53] Propositions de lea-dev (U1)

- **Auteure** : lea-dev. **Base de mesure** : `5c1353a9` (round-two, fusion du planning), worktree `s53-lea-relecture`.
- **Perimetre** : P2, P5, P7, P8, P10, P11 (planning, section "Ordre", U1).
  **Appliques dans cette etape** : P2 et P7 (decisions d'auteure, un commit chacun).
  **Propositions seulement, aucun YAML edite** : P5, P8, P10, P11.
- **Espaces insecables** : le YAML ci-dessous est extrait des fichiers ou derive d'eux par remplacement exact, et porte les memes
  espaces insecables (U+00A0) devant `?` et a l'interieur des guillemets francais. Ce sont des caracteres reels.
- **Methode, un piege** : sur ce poste `grep -P '\xC2\xA0'` ne trouve rien (le motif s'interprete en caracteres, pas en octets) et donne
  un faux zero. Les comptes d'espaces insecables ci-dessous sont faits avec `LC_ALL=C grep` et l'octet litteral
  (`grep -l "competence: \".*$(printf '\xc2\xa0'):"`) ou avec Python.

## P2 - `ce1-pronoms` (APPLIQUE, commit `9bd0ad21`)

**Constat verifie.** L'en-tete (PRN-CE1-2) classe les items 1, 2, 4, 5, 7, 8, 11, 12, 15, 20 "le sens decide" (deux candidats du
texte ont le genre et le nombre du pronom) et les dix autres "l'accord". J'ai relu le `texteSupport` des vingt items
(`grep -n 'texteSupport' adapters/adapter-content/src/main/resources/contenu/ce1-pronoms.yaml`, a `5c1353a9`). Candidats = noms ou
groupes nominaux qui precedent le pronom.

| Item | Pronom | Candidats avant le pronom | De meme genre et nombre | En-tete | Relecture |
|---|---|---|---|---|---|
| 1 | il | Tom, Malo | Tom, Malo | sens | sens |
| 2 | il | Karim, Sacha, le bus | les trois | sens | sens |
| 3 | elle | Zoé, le jeu, Paul | Zoé seule | accord | accord |
| 4 | elle | Lina, Aya, un appareil | Lina, Aya | sens | sens |
| 5 | ils | Noé et Hugo, les oiseaux | les deux | sens | sens |
| 6 | ils | Samir et Lucie, une cabane | Samir et Lucie seuls | accord | accord |
| 7 | elles | Nina et Lola, des fraises | les deux | sens | sens |
| 8 | elles | Jade et Anna, des cartes | les deux | sens | sens |
| 9 | il | Bastien, la porte, Maëlle | Bastien seul | accord | accord |
| **10** | elle | Ilyes, **une pomme**, Sofia | **Sofia et une pomme** | accord | **sens** |
| 11 | il | Rayan, le ballon, Yanis | les trois | sens | sens |
| 12 | elle | Inès, Kenza, la table | les trois | sens | sens |
| **13** | elle | Léo, **une histoire**, Rose | **Rose et une histoire** | accord | **sens** |
| **14** | ils | **Enzo et Maya**, **leurs livres** | **Enzo et Maya et leurs livres** | accord | **sens** |
| 15 | il | le chat, Elias, un biscuit | les trois | sens | sens |
| 16 a 18 | le garçon, l'animal, la fille | deux ou trois candidats | le mot lexical decide | accord | accord |
| 19 | ils | Nael et Eva, des fleurs | Nael et Eva seuls (des fleurs : féminin) | accord | accord |
| 20 | elles | Mila et Jeanne, deux maisons | les deux | sens | sens |

**Resultat** : l'en-tete annonce 10 "sens" et 10 "accord" ; la relecture en trouvait 13 et 7. Les items 10, 13 et 14 portaient un
candidat de meme genre et nombre que le pronom (une pomme et une histoire : féminin singulier, comme "elle" ; leurs livres : masculin
pluriel, comme "ils"). Sur ces trois items l'accord ne decidait pas : il fallait le sens, contre ce que l'en-tete disait.

**Issue appliquee : corriger.** Le nom en trop de chacun des trois textes change de genre ou de nombre, pour que l'accord decide
vraiment. Ids, bonnes reponses, ordre des propositions, indices, classe de l'en-tete et comptes (il 5, elle 5, ils 4, elles 3 ;
10 "sens", 10 "accord") ne bougent pas. L'en-tete n'est pas edite : il redevient exact.

**Motif.** Alternative ecartee : reclasser les trois items en "sens" (13 sens, 7 accord). PRN-CE1-2 demande au moins un tiers de
"sens" (7 sur 20), les deux voies le respectent. J'ai garde la reecriture parce que les indices de ces trois items (`Relis la phrase
d'avant pour trouver qui agit.`, `Cherche qui fait l'action dans la phrase d'avant.`) sont ceux d'items a l'accord, et qu'un mot
par texte est le changement minimal. Contrepartie : `leurs affaires` remplace `leurs livres`, sans lien avec le sens de la phrase.

**Texte exact des trois items apres edit** (le commentaire de tete `# elle, l'accord` / `# ils, l'accord` est inchange) :

```yaml
  - enonce: "Que désigne « elle » dans le texte ?"
    texteSupport: "Ilyes donne un gâteau à Sofia. Elle dit merci."
    bonneReponse: "Sofia"
    distracteurs: ["Ilyes", "un gâteau"]
    indice: "Relis la phrase d'avant pour trouver qui agit."
```

```yaml
  - enonce: "Que désigne « elle » dans le texte ?"
    texteSupport: "Léo lit un livre à Rose. Elle écoute bien."
    bonneReponse: "Rose"
    distracteurs: ["Léo", "un livre"]
    indice: "Cherche qui fait l'action dans la phrase d'avant."
```

```yaml
  - enonce: "Que désigne « ils » dans le texte ?"
    texteSupport: "Enzo et Maya rangent leurs affaires. Ils les posent sur l'étagère."
    bonneReponse: "Enzo et Maya"
    distracteurs: ["Enzo", "leurs affaires"]
    indice: "Relis la phrase d'avant pour trouver qui agit."
```


## P7 - `cm1-hda-epoque-civilisation` (APPLIQUE, commit `f43e5f96`)

**Constat verifie.** La ligne 6 de l'en-tete dit qu'**aucune oeuvre individuelle n'est nommee** (HDA-1). L'enonce de l'item 4 disait
"comme le pont du Gard". Commande avant l'edit :
`git show 5c1353a9:adapters/adapter-content/src/main/resources/contenu/cm1-hda-epoque-civilisation.yaml | grep '^  - enonce:' | grep -c 'pont du Gard'`
rend 1. Apres : `grep '^  - enonce:' cm1-hda-epoque-civilisation.yaml | grep -c 'pont du Gard'` rend 0.

**Autres noms d'oeuvre dans les enonces** : `grep '^  - enonce:' cm1-hda-epoque-civilisation.yaml | grep -noE 'Gard|Versailles|Eiffel|Zeus|Louis XIV|Parthénon|Colisée|Lascaux|Notre-Dame|Pyramide|Paris|1889'`
rend Zeus (item 2), Louis XIV (item 10), Paris et 1889 (item 11). Zeus est un dieu, Louis XIV un roi, Paris une ville et 1889 une
date : aucun n'est le nom d'une oeuvre. Je les laisse. L'item 11 designe la tour Eiffel sans la nommer, l'item 10 designe Versailles
sans le nommer ("une galerie de miroirs") : c'est la regle de l'en-tete, pas un ecart.

**Issue appliquee : retirer le nom de l'enonce.** Le reste de l'enonce, la bonne reponse (`la Rome antique`), les distracteurs,
l'indice et l'id ne changent pas.

**Motif.** Garder le nom aurait oblige a reecrire l'en-tete (HDA-1). Le marqueur d'epoque reste dans l'enonce : arches sur plusieurs
etages, aqueduc, et l'indice sur la Gaule. Point a ne pas oublier : le commentaire d'en-tete (ligne 15) cite encore "aqueduc
du pont du Gard" comme **source du fait** (HIS-3), avec le fichier ou il est verifie. Je l'ai laisse : c'est une reference, pas un
enonce. Si `relecteur-pedagogique` lit la phrase de la ligne 6 comme couvrant les commentaires, c'est une ligne a changer.

**Texte exact de l'item 4 apres edit** :

```yaml
  - enonce: "Un aqueduc en pierre, à plusieurs étages d'arches, transporte l'eau sur de longues distances. À quelle civilisation appartient-il ?"
    bonneReponse: "la Rome antique"
    distracteurs: ["la Grèce antique", "l'Égypte antique"]
    indice: "Ce peuple a construit des aqueducs jusqu'en Gaule."
```


## Mesure P2 et P7

Commande : `bash outils/mvn-jdk21.sh -B -am -pl adapters/adapter-content test`, depuis le worktree, sur `f43e5f96`.

- Sortie : `Tests run: 1318, Failures: 0, Errors: 0, Skipped: 0`, `BUILD SUCCESS`, reacteur `recreo`, `recreo-domain`,
  `recreo-application`, `recreo-adapter-content` tous SUCCESS.
- Version : l'enveloppe annonce `Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10`. Je n'ai pas lu la version que la machine
  virtuelle des tests rapporte (ADR-0071) : 21.0.10 est celle du JDK resolu par l'enveloppe.
- Perimetre : `adapter-content` et ses amonts (`-am`). `bootstrap` non lance (C4 le demande a la livraison).
  Jobs de la CI lances : aucun (ni `prefixes`, ni `chemins`, ni `outils`, ni `build`).
- **1318 est un total de ce module a la base `5c1353a9` plus mes deux edits de YAML, pas un delta : je n'ai ajoute aucun test.**
- **Declaration de `70b1cdc1` a changer : aucune.** Les declarations de `ce1-pronoms` et `cm1-hda-epoque-civilisation` dans
  `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` comptent des propositions (20 items, 60 propositions ; 12 items, 36
  propositions) : mes edits n'ajoutent ni ne retirent de proposition.

## P5 - `cp-qlm-vocabulaire-de-position` item 7 (proposition, decide `nadia-prof`)

**Constat verifie.** `grep -n 'le chien est loin' adapters/adapter-content/src/main/resources/contenu/cp-qlm-vocabulaire-de-position.yaml`
rend 1 ligne (item 7, ligne 72 du fichier a `5c1353a9`). L'enonce dit la position (`le chien est loin`) puis demande `Qui est le plus
loin ?` ; la bonne reponse est `le chien`. L'enfant recopie. C'est exactement ce que l'en-tete du paquet dit s'interdire : "aucun
item ne demande la position qu'il decrit". Aucun autre item de ce paquet ne fait cela (les items 1 a 6 posent la relation inverse,
8 a 11 un contraire, 12 a 14 une extremite).

**Issue proposee : corriger.** L'item 7 rejoint la tache "extremite d'un alignement" (comme 12 a 14) : trois animaux ordonnes par
"plus loin que", l'enfant doit raisonner pour designer le plus loin. L'enonce ne dit plus qui est loin. Le chien reste la bonne
reponse, le rang et l'indice sont inchanges.

**Texte exact propose** (item 7) :

```yaml
  - enonce: "Le lapin est plus loin de la porte que le chat. Le chien est plus loin que le lapin. Qui est le plus loin de la porte ?"
    bonneReponse: "le chien"
    distracteurs: ["le chat", "le lapin"]
    indice: "Compare la distance entre chaque animal et la porte."
```


**Motif.** (1) La bonne reponse n'est plus dans l'enonce comme proposition de position. (2) L'item porte deux distracteurs au lieu
d'un, ce qui solde P10 pour cet item (le chat y etait l'unique distracteur). (3) Le texte reste a la portee d'un CP accompagne :
trois phrases courtes, un seul mot de position, la derniere phrase est la question. **Alternative ecartee** : la relation inverse
des items 1 a 6 (`Le chien est loin de la porte. Où est la porte par rapport au chien ?`) : "loin" est symetrique, la reponse
serait le mot de l'enonce.

**Consequences d'ecriture, si adoptee** :

- En-tete du paquet, ligne FER-3 : "relation inverse entre deux elements (items 1 a 7)" devient "items 1 a 6", et "extremite d'un
  alignement de trois elements (items 12 a 14)" devient "items 7 et 12 a 14". FER-1 ne change pas (`loin` reste couvert par l'item 9).
- `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, entree `cp-qlm-vocabulaire-de-position` (lignes 491 et 492 a
  `5c1353a9`) : le motif ecrit "41 propositions" et "certains items portent un seul distracteur". Avec l'item 7 a deux distracteurs,
  41 (13 items a trois propositions, 1 a deux) devient 42 (14 items a trois) et la parenthese devient fausse.
  **C'est une declaration de texte a corriger dans le meme commit, sans toucher d'assertion** ; je ne l'ai pas verifiee en la
  jouant, c'est une lecture. Je n'edite aucun Java.

## P10 - items a un seul distracteur (proposition, decide `theo-archi`, volet pedagogique `nadia-prof`)

**Constat verifie.** `awk '/distracteurs: \["[^"]*"\]$/' adapters/adapter-content/src/main/resources/contenu/cp-qlm-vocabulaire-de-position.yaml adapters/adapter-content/src/main/resources/contenu/cp-qlm-situer-la-france.yaml`
rend deux lignes a `5c1353a9` : `distracteurs: ["le chat"]` (vocabulaire, item 7) et `distracteurs: ["la France"]` (situer-la-france,
item 12). **Nombre d'items a un seul distracteur : 1 dans chaque fichier, 2 en tout.**

**Issue proposee : au moins deux distracteurs, en ajoutant un candidat qui existe dans le texte de l'item** (propositions de meme
nature, designant un element de l'enonce). Pour l'item 7 de `vocabulaire-de-position`, c'est la proposition P5 ci-dessus : si elle est
adoptee, P10 est soldee pour ce fichier. Pour l'item 12 de `situer-la-france` :

```yaml
  - enonce: "La France fait partie de l'Europe. Lyon est une ville de France. Qu'est-ce qui est le plus grand ?"
    bonneReponse: "l'Europe"
    distracteurs: ["la France", "Lyon"]
    indice: "Un pays est à l'intérieur d'un continent."

```


**Motif.** Un item a deux propositions donne une chance sur deux au hasard ; a trois, une sur trois, comme les autres items de ces
deux paquets. Je n'arbitre pas l'admissibilite d'un item a un distracteur (question Q5, `theo-archi` puis `nadia-prof`). L'item 12
change d'enonce (une phrase de plus) pour que le distracteur ajoute soit une ville nommee dans le texte, plus petite que la France,
et que la bonne reponse reste `l'Europe`.

**Si l'issue est "accepter", aucun YAML ne change.**

**Consequences d'ecriture, si adoptee** :

- `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, entree `cp-qlm-situer-la-france` (ligne 488) : "35 propositions"
  devient 36 (12 items a trois propositions) et la parenthese "certains items portent un seul distracteur" devient fausse.
  Declaration de texte, lecture non jouee.
- **Observation hors perimetre, non traitee** : l'en-tete de `situer-la-france` classe l'item 12 dans "situer un espace par son
  point cardinal par rapport a la France (items 5 a 12)", et l'item 12 n'en pose aucun. A signaler a `sofia-sm`.

## P8 - `ce1-texte-documentaire` (proposition, decide `nadia-prof`)

**Regle lue.** `TRV-CE1-6` (`squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md`, ligne 210) : *"rien de ce qui s'adresse a
l'enfant (tu) ne suppose une configuration familiale, un logement, un jardin, un animal, des vacances ou un objet possede"*.
L'en-tete du paquet cite TRV-CE1-6 sous la forme "aucune situation qui suppose un logement, un animal possede ou des vacances" : il
omet "un objet possede". C'est ce qui a laisse passer l'item 16.

**Balayage de tous les possessifs.** Commande, sur les lignes qui ne sont pas des commentaires (enonce, texteSupport,
propositions, indice) :
`grep -nwiE 'ton|ta|tes|mon|ma|mes|notre|nos|votre|vos|son|sa|ses|leur|leurs' ce1-texte-documentaire.yaml | grep -v '^[0-9]*: *#'`
Elle rend **cinq lignes, trois items** :

| Ligne | Item | Occurrence | Adresse a l'enfant | Proposition |
|---|---|---|---|---|
| 41-42 | 3 (crocodile) | `ses œufs` (enonce et texte) | non, troisieme personne, animal | accepter |
| 114 | 15 (`Trace un trait... Écris ton prénom en haut.`) | `ton prénom` | oui | accepter, ou reecrire (voir ci-dessous) |
| 120 | 16 (`Range ton cahier. Ferme ta trousse. Mets ta chaise...`) | `ton cahier`, `ta trousse`, `ta chaise` | oui | reecrire |
| 121 | 16 | `ta chaise` (bonneReponse) | oui | reecrire |
| 122 | 16 | `ta trousse`, `ton cahier` (distracteurs) | oui | reecrire |

Numerotation des items : rang d'apparition dans le fichier (le planning cite l'item des lignes 120 a 122). Le fichier ne
contient aucun autre possessif (`mon`, `ma`, `mes`, `notre`, `nos`, `votre`, `vos`, `son`, `sa`, `leur` : zero).

**Item 16 (ligne 120 a 122), issue proposee : reecrire.** `ton cahier`, `ta trousse` et `ta chaise` sont des objets que la regle
dit ne pas supposer possedes ; la chaise comme la trousse dependent d'un enfant qui en a une. Le possessif devient un article. La
bonne reponse reste a la meme place, l'ordre des propositions et l'indice ne changent pas.

**Texte exact propose (item 16)** :

```yaml
  - enonce: "Que faut-il mettre sous la table ?"
    texteSupport: "Range le cahier. Ferme la trousse. Mets la chaise sous la table."
    bonneReponse: "la chaise"
    distracteurs: ["la trousse", "le cahier"]
    indice: "Relis la dernière instruction."
```


**Item 15 (ligne 114), `ton prénom`.** Je propose **d'accepter** : un prénom n'est pas un objet possédé, tout élève en a un, et la
regle vise la configuration familiale et les biens. Si `nadia-prof` lit la regle plus strictement (le possessif "ton" lui-meme), voici
le texte de remplacement :

```yaml
  - enonce: "De quelle couleur faut-il colorier le rond ?"
    texteSupport: "Trace un trait en rouge. Colorie le rond en bleu. Écris un prénom en haut."
    bonneReponse: "en bleu"
    distracteurs: ["en rouge", "en vert"]
    indice: "Cherche le mot « rond » dans la consigne."
```


**Motif.** Un `ton`, `ta` ou `tes` devant un objet est exactement le cas de TRV-CE1-6 ; il n'y en a qu'un item. `ses œufs` n'est
pas adresse a l'enfant. **Apres correction de l'item 16 seul, la meme recherche rend 3 lignes (41, 42, 114) ; si l'item 15 est aussi
reecrit, 2 lignes (41, 42), toutes deux `ses œufs`.** Declarations de test : aucune entree du paquet ne compte une proposition
qui change (le nombre de propositions reste 60).

## P11 - espace avant ":" dans `competence` (proposition, decideur : utilisatrice, arbitrage du 2026-10-07)

**Constat verifie, avec un chiffre different de celui du backlog.** Commandes :

- Espace ordinaire avant ":" dans `competence` : `grep -l ' competence: ".* :' adapters/adapter-content/src/main/resources/contenu/*.yaml`
  rend **13 fichiers** (les quatre du point et neuf autres, comme le planning l'a mesure).
- Espace insecable avant ":" dans `competence` : `LC_ALL=C grep -l "competence: \".*$(printf '\xc2\xa0'):" adapters/adapter-content/src/main/resources/contenu/*.yaml`
  rend **4 fichiers** : `ce1-accord-groupe-nominal`, `ce1-classes-de-mots`, `ce1-inference`, `ce1-texte-narratif`. La convention
  a l'espace insecable est donc deja appliquee dans quatre paquets francais CE1 et pas dans les treize.
- Hors `competence` : 50 fichiers portent au moins une espace insecable avant ":" sur 182, et 25 fichiers en portent une dans un
  `enonce` (mesure Python). Six fichiers portent une espace ordinaire avant ":" dans un `enonce` : **le point ne s'arrete pas a
  `competence`**, je n'en ai pas fait le perimetre.
- Je n'ai pas elucide l'ecart "onze paquets plus anciens" (backlog) contre neuf (mesure) : mes quatre fichiers + les neuf autres font
  13, pas 11 + 4. La commande ci-dessus est celle que le decideur peut refaire.

**Mes quatre lignes** (les trois de lea-dev et celle de `principes`, qui est a marc-dev et que je cite pour le decideur) :

| Fichier | Ligne | Texte actuel (espace ordinaire avant ":") |
|---|---|---|
| `cm1-arts-decrire-une-oeuvre-lexique` | 23 | `Décrire une œuvre d'art avec un lexique élémentaire : support, format, matière, sujet (référentiel CM1, ligne ART-CM1-08).` |
| `cm1-hda-caracteristiques-techniques` | 24 | `Dégager d'une œuvre observée ses principales caractéristiques techniques et formelles : technique, geste, genre (référentiel CM1, ligne HDA-CM1-02).` |
| `cm1-hda-decrire-lexique` | 24 | `Décrire une œuvre d'art en employant un lexique simple et adapté : support, format, matière, couleur, sujet (référentiel CM1, ligne HDA-CM1-01).` |
| `cm1-emc-principes-republique` (marc-dev) | 8 | `Comprendre le sens des principes de la République : liberté, égalité, fraternité, laïcité, refus des discriminations.` |

**Issues possibles, au decideur :**

- **O1 - espace insecable dans les treize** : un seul caractere remplace une espace ordinaire par ligne, avant le premier ":" de la
  `competence`. Le texte devient celui des quatre paquets CE1. Texte exact : les lignes ci-dessus avec ` :` remplace par
  U+00A0 puis `:` (aucun autre mot ne change). Aucune ligne n'a d'autre ":" dans la `competence` (les parentheses
  `(référentiel CM1, ligne ...)` n'en portent pas).
- **O2 - accepter l'espace ordinaire** : aucun YAML ne change ; le motif a ecrire est que `competence` est une ligne de metadonnee
  de paquet, pas un texte de l'enfant, et que rien ne la lit a l'ecran (le planning ne l'a pas trouvee dans un gabarit : lecture
  de `theo-archi`, que je n'ai pas refaite).

**Ma recommandation : O1, treize lignes, un commit.** Motif : le depot a deja quatre paquets a l'espace insecable, aucun test
ne l'exige ni ne l'interdit (je n'ai pas cherche de garde-fou dedie), et le cout est un caractere par ligne. Ce n'est pas mon arbitrage :
le decideur est l'utilisatrice, et c'est a elle de dire si le perimetre inclut les enonces (six fichiers).

## Ce que je n'ai pas fait

- Aucune edition de P5, P8, P10, P11 ni de Java. Aucun push, rebase ni reset.
- Aucun branchement au catalogue : `git diff round-two --name-only | grep -c catalogue.yaml` rend 0.
- `bootstrap` et le reacteur complet ne sont pas lances ; la suite de `adapter-content` a ete lancee une fois, sur `f43e5f96`.
