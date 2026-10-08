# Fiche de conformité - bandit-manchot-passe-compose-ce1

- **Mécanique** : bandit manchot (mécanique générique, partagée avec les autres jeux du catalogue).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-passe-compose.yaml` (24 items, type
  `question`), branché par `marc-dev` (`b9e9b9b3`, story `S54-BR-FRA-CE1-19`). Entrée de catalogue
  `bandit-manchot-passe-compose-ce1`, titre servi *"Les verbes au passé composé"*, consigne servie *"Lis bien,
  puis choisis la bonne forme du verbe."*, `nombreDeManches: 10`.
- **Ligne déclarée** : `FRA-CE1-19`. **Décision** : `DEC-0114`.
- **Conditions opposables** : `PCO-CE1-1` à `PCO-CE1-3` (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section
  5.10), `TRV-CE1-1` à `TRV-CE1-7`, `LON-CE1-1`, `-2`, `-5` ; conditions avant branchement `K3`, `K4`
  (`ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md`, section 6).
- **Statut global** : **VALIDÉ SOUS RÉSERVE** le 2026-10-08 par `nadia-prof` (s54, instance A), sur
  `5cad6650` : une réserve, `PCO-CE1-R1`. Voir le bloc de validation.
- **État au 2026-10-08, après rejeu** : **`PCO-CE1-R1` LEVÉE** sur `c876fadc` (`marc-dev`), joué au sommet
  `e9c3ec52`. Plus aucune réserve ouverte. Bloc de rejeu en fin de fiche.
- **Dernière mise à jour** : 2026-10-08 (rejeu de `PCO-CE1-R1`, `nadia-prof`, s54, instance A). Aucune fiche
  n'avait été ouverte avant le branchement ; la section des critères de `camille-po` est écrite le
  2026-10-08 par `camille-po` (routage de l'arbitrage de s54, section 12 : `K3`, `K4` comme critères).

## Compétences visées

| Matière | Niveau | Domaine | Compétence | Référence au référentiel |
|---|---|---|---|---|
| français | CE1 (cycle 2) | Orthographe et grammaire | Conjuguer au passé composé *être*, *avoir* et les verbes du premier groupe, avec l'auxiliaire *avoir* | `squad/programme/francais/competences-ce1.md`, `FRA-CE1-19` |

## Critères pédagogiques attendus - camille-po

Écrits par `camille-po` le 2026-10-08 (s54), après le verdict, à partir des conditions de `nadia-prof`
(`ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md`, section 6, routage de la section 12) et de la réserve de son verdict.
Une condition pédagogique générale (`PCO-CE1-1` à `PCO-CE1-3`, `TRV-CE1-1` à `TRV-CE1-7`, `LON-CE1-1`, `-2`, `-5`) n'est pas
recopiée ici : elle vit dans `ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 5.10. La colonne "Avant" est mesurée sur `34fdd167`
(paquet de s53, avant branchement), la colonne "Aujourd'hui" sur le sommet de la branche, avec la commande indiquée, lancée
depuis la racine du dépôt, sur `adapters/adapter-content/src/main/resources/contenu/ce1-passe-compose.yaml`.

**Compétence** : `FRA-CE1-19`, conjuguer au passé composé *être*, *avoir* et les verbes du premier groupe, avec l'auxiliaire
*avoir*. **Mécanique** : bandit manchot, `type: question`, 24 items, 10 manches.

| # | Critère (entrée / sortie observable) | Commande | Avant | Aujourd'hui |
|---|---|---|---|---|
| `K3` | Aucun indice ne dit *"deux mots"* : un indice qui présente comme ce qui fait la bonne réponse un critère qu'un distracteur remplit confirme l'enfant qui s'est trompé (`TRV-CE1-5`, motif de `nadia-prof` du 2026-10-06) | `grep -c 'indice:.*deux mots'` | 8 | 0 |
| `K3` | Onze items portent l'indice *"Ne recopie pas le verbe écrit au début : il change."* (items 1, 3, 4, 6, 7, 13, 15, 16, 18, 19, 22) | `grep -c 'indice: "Ne recopie pas le verbe'` | 0 | 11 |
| `K3` | Quatre items portent l'indice *"Avec ce sujet, comment s'écrit « avoir » au présent ?"* (items 9, 10, 12, 24) | `grep -c 'indice: "Avec ce sujet, comment'` | 0 | 4 |
| `K4` | Les trois énoncés d'*être* qui faisaient suivre *avoir été* d'un lieu (emploi familier pour *aller*, que la classe corrige) deviennent *"« être » : hier, ___ en retard."* (item 13), *"... en avance."* (item 17), *"... la première."* (item 21) ; bonne réponse et distracteurs ne changent pas, le présent reste faux avec *hier* | `grep -cE 'enonce: .*hier, ___ (en retard\|en avance\|la premi.re)\.'` | 0 | 3 |

**Critère de levée de la réserve `PCO-CE1-R1`** (non bloquante, ouverte le 2026-10-08, mots de `nadia-prof`, section 5 du bloc de
validation, repris sans reformulation) : *"sur tout item qui sert l'infinitif à la place du participe, l'indice parle de cette
erreur"*. Ce qui la lève, mot pour mot : sur les items **2, 5, 8, 9, 11, 12, 20, 23**, et sur eux seuls, la ligne `indice` devient
`indice: "Ne recopie pas le verbe écrit au début : il change."`. Énoncés, bonnes réponses, distracteurs, familles déclarées et
en-tête ne changent pas. Mesure d'entrée : `grep -c 'indice: "Ne recopie pas le verbe'` rend **11 aujourd'hui** et doit rendre
**19** ; `grep -c 'indice: "Avec ce sujet, comment'` rend **4 aujourd'hui** et doit rendre **2** (items 10 et 24) ; le diff ne
touche que ces huit lignes. Rouge sur l'existant : 11 et 4. Régime et prix : mot pour mot, 0,25 (`PAR-10`), rejeu par `nadia-prof`.
Story : `PCO-CE1-R1` (`docs/backlog/backlog.md`, "Sprint 54").

**Ce que ces critères ne font pas** : ils ne disent pas que les 24 réponses sont justes ni qu'un indice sert l'enfant devant les
propositions de son item. `PaquetCe1PasseComposeTest` refuse la chaîne *"deux mots"* dans un indice ; il ne lit ni ce qu'un indice
fait faire à l'enfant ni qu'il parle de l'erreur de l'infinitif (`PCO-CE1-R1`). `nadia-prof` reste la seule garde de ces deux
propriétés, de la correction d'un emploi que la classe corrige (`K4`), de l'accent des participes et de la répartition des
activités par genre.

**Mention de couverture** : aucune n'est due. `MNC-1` est reproduite par `nadia-prof` dans le bloc de validation.

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pré-rempli ni anticipé par un autre agent.

### Verdict du 2026-10-08 (`nadia-prof`, s54, instance A) : **VALIDÉ SOUS RÉSERVE**

**Done pédagogique prononcé.** `camille-po` peut passer `FRA-CE1-19` à `couvert-valide`, sans mention de
couverture partielle, et faire une story de la réserve `PCO-CE1-R1` (section 5).

#### 1. Ce que j'ai fait

**Audit joué.** Jar construit au sommet de ma branche `5cad6650` (qui contient `b9e9b9b3`), arbre propre
avant et après la construction, `-DskipTests package`, copié hors du worktree et lancé sur le JDK 21.0.10,
port 18854. **Rendu non vu au navigateur** : appels HTTP réels, HTML servi lu.

**Porte technique : non mesurée par moi.** Le jeu se lance, sert et termine ses parties sans erreur. Le Done
technique reste à juger par la CI au sha de fusion.

**Le diff de `b9e9b9b3`, lu contre mon arbitrage** : quinze indices remplacés, `N1` sur les items 1, 3, 4, 6, 7,
13, 15, 16, 18, 19, 22, `N2` sur 9, 10, 12, 24 ; l'indice *"deux mots"* a disparu ; énoncés des items 13, 17, 21
au texte de `K4` ; en-tête remplacé. Mot pour mot. `L2` et `L3` non appliquées (recommandations).

**Lecture masquée d'abord** : les 24 phrases lues sans les propositions ; j'ai conjugué chacune. Mes 24
formes sont les 24 bonnes réponses du fichier.

**Quarante parties complètes de dix manches** : une juste, une avec une erreur par manche, une avec trois
erreurs par manche, trente-sept mêlées (zéro à trois erreurs tirées par manche). **Les 24 items tirés** (10 à
23 fois chacun), chacun avec au moins une erreur et une révélation. Sur les 400 manches : aucune anomalie de
service - propositions et indice servis identiques au fichier, consigne présente sur chaque écran (manche,
erreur, révélation), aucune révélation avant la troisième erreur, réponse révélée juste. Position de la
bonne réponse : 133, 152 et 115 aux trois places. Écran de fin atteint sur les 40 parties.

**Ce qui se passe après une erreur, vu en jouant** : la proposition essayée est marquée, *"Ce n'est pas ça.
Essaie encore !"* et l'indice de l'item s'affichent ; l'enfant choisit parmi les deux autres. C'est le cadre
dans lequel j'ai lu chaque indice (section 4).

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne : choisir la forme écrite du passé composé, à partir du pronom sujet et
d'un mot de temps (*hier*, *ce matin*, *la semaine dernière*). L'erreur de l'infinitif pour le participe
(*j'ai chanter*), *"l'erreur du CE1"* (`PCO-CE1-3`), est servie sur 20 items sur 24. Aucun verbe à
auxiliaire *être*, aucun pronominal, aucun imparfait.

#### 3. Les conditions

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `PCO-CE1-1` | **oui** | auxiliaire *avoir* seul ; *être* et *avoir* sur 8 items, dix verbes du premier groupe ; aucun complément avant le verbe |
| `PCO-CE1-2` | **oui** | toujours un pronom ; chaque personne sujet de 4 items |
| `PCO-CE1-3` | **oui** | familles déclarées et vérifiées ; (a) sur 20 items ; jamais l'imparfait |
| `TRV-CE1-1` | **oui** | `CE1`, une ligne, aucun champ à voix haute, aucun texte support, aucune illustration |
| `TRV-CE1-2` | **oui** | trois propositions distinctes, une seule acceptable (avec *hier*, le présent n'est pas une phrase correcte) ; bonne réponse strictement la plus longue sur 3 items (10, 14, 24) |
| `TRV-CE1-3` | **sans objet** | aucun son désigné |
| `TRV-CE1-4` | **oui** | ni *participe* ni *auxiliaire* servis ; `N2` dit *« avoir » au présent* |
| `TRV-CE1-5` | **oui au sens de mon motif de s52 ; réserve `PCO-CE1-R1`** | 4 indices distincts, 11 mots au plus, aucun ne donne la réponse ; section 4 |
| `TRV-CE1-6` | **oui** | aucun prénom ; aucune activité du côté du stéréotype ; aucun *tu* qui suppose un foyer ou un objet |
| `TRV-CE1-7` | **oui** | 24 items |
| `LON-CE1-1`, `-2`, `-5` | **oui** | énoncés de 8 mots au plus, une phrase ; propositions de 3 mots au plus |
| `K3` | **oui** | quinze indices au texte de mon arbitrage, items exacts |
| `K4` | **oui** | *en retard*, *en avance*, *la première* : *avoir été* n'est plus suivi d'un lieu ; le présent reste faux avec *hier* |

#### 4. Une correction de mon propre constat, avant de m'en servir

Ma barre de `ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md`, section 1.2, est écrite plus large que son
motif : *"un indice qui donne un critère ne doit pas être satisfait par un distracteur de son item"*. Lue à
la lettre, elle condamne mon propre `N1` sur ses onze items : *"Ne recopie pas le verbe écrit au début"* est
satisfait par le présent (*je chante*) et par l'auxiliaire faux (*j'as joué*). **Ce que mon motif de s52
interdit** est un indice qui présente comme ce qui fait la bonne réponse un critère qu'un distracteur
remplit (*"un verbe au passé composé a deux mots"*) : l'enfant s'y trouve confirmé. Une interdiction (`N1`) ou
une condition nécessaire (`N2`) qu'un distracteur respecte ne confirme personne : elle ne lui parle pas.
C'est l'étage insuffisant, déjà traité en réserve et non en refus (`ALP-CE1-R1`). Je juge les quinze
indices sur cette lecture, qui est celle de mon motif ; la phrase de la section 1.2 est à lire ainsi.

**Ce que j'ai vu en jouant, sur ce cadre.** Sur 8 items, l'enfant qui fait l'erreur centrale (a) lit un
indice qui ne lui en parle pas :

| Item | Phrase | Propositions | Indice servi | Ce que l'indice fait après l'erreur (a) |
|---|---|---|---|---|
| 2 | *hier soir, ___ un film* | **tu as regardé** / tu as regarder / tu a regardé | I2 | rien : les deux restantes se lisent au passé |
| 5 | *hier, ___ les étoiles* | **vous avez regardé** / vous avez regarder / vous regardez | I2 | écarte le présent ; l'enfant trouve sans savoir ce qu'il a raté |
| 8 | *ce matin, ___ une maison* | **tu as dessiné** / tu as dessiner / tu dessines | I2 | idem |
| 9 | *hier, ___ de la chance* | **il a eu** / il a avoir / il as eu | `N2` | *il a* : son choix le contenait ; écarte *il as eu* |
| 11 | *ce matin, ___ un arbre* | **vous avez dessiné** / vous avez dessiner / vous avons dessiné | I2 | rien |
| 12 | *hier, ___ un cadeau* | **ils ont eu** / ils ont avoir / ils a eu | `N2` | *ils ont* : son choix le contenait ; écarte *ils a eu* |
| 20 | *hier, ___ à la maîtresse* | **tu as parlé** / tu as parler / tu parles | I2 | écarte le présent |
| 23 | *hier, ___ trop fort* | **vous avez parlé** / vous avez parler / vous parlez | I2 | écarte le présent |

Aucun de ces indices n'est faux, aucun ne donne la réponse, aucun ne confirme l'erreur au sens de mon
motif : **ce n'est pas un motif de refus**. Mais l'erreur que la ligne existe pour corriger n'est pas nommée
à l'enfant qui la fait, sur 8 des 20 items qui la servent, alors que le paquet porte déjà l'indice qui la
nomme (`N1`). Les items 9 et 12 sont de ma main : j'y ai posé `N2` à mon arbitrage, en visant (b) plutôt que
(a).

#### 5. La réserve, datée du 2026-10-08

**`PCO-CE1-R1` - sur tout item qui sert l'infinitif à la place du participe, l'indice parle de cette
erreur.** Réserve nouvelle, non bloquante. Elle reprend `L2` de mon arbitrage (items 2, 5, 8, 11, 20, 23),
que je n'avais pas rendue exigible, et y ajoute les items 9 et 12 ; ce n'est pas un durcissement de `K3`, que
le paquet tient.

**Ce qui la lève, mot pour mot** : sur les items **2, 5, 8, 9, 11, 12, 20, 23**, et sur eux seuls, la ligne
`indice` devient celle que portent déjà les items 1, 3, 4 :

```yaml
    indice: "Ne recopie pas le verbe écrit au début : il change."
```

Énoncés, bonnes réponses, distracteurs, familles déclarées et en-tête ne changent pas. Après correction :
`N1` sur 19 items, I2 sur les items 14 et 17, `N2` sur 10 et 24, I3 sur 21 ; `TRV-CE1-5` (*"pas identique sur
tout le paquet"*) reste tenue. **Ce que la correction coûte, et je le dis** : sur les items 2, 9, 11 et 12,
l'enfant qui choisit l'auxiliaire faux (b) lira un indice qui ne lui parle pas de l'auxiliaire. C'est le prix
de faire passer l'erreur centrale de la ligne avant l'autre ; un seul indice par item ne sert pas deux
familles. **Régime : mot pour mot, 0,25** (`PAR-10`). Le rejeu vérifie que le diff ne touche que ces huit
lignes, puis rejoue les huit items.

`L3` (consigne qui nomme le passé composé) reste une recommandation : avec *hier*, rien ne l'exige.

#### 6. Exactitude, niveau, ton

**Les 24 réponses sont justes**, une seule acceptable par item, vérifié en lisant chaque phrase avec chacune
des trois propositions. Accents relus à la main sur les participes et les homographes : *chanté*, *regardé*,
*lavé*, *dansé*, *joué*, *dessiné*, *écouté*, *travaillé*, *aidé*, *parlé*, *été* portent le leur ; *eu* n'en
a pas ; *à l'heure*, *à la maîtresse*, *très*, *idée*, *première*, *dernière* justes. *Tu as été à l'heure*,
*vous avez été en avance*, *elle a été la première* sont des phrases correctes du français scolaire.

**Niveau** : verbes et situations de la classe et de la vie courante. Le bouton de
trois mots (*nous avons travailler*) se distingue de la bonne réponse par deux lettres en fin de bouton :
c'est la compétence elle-même, non un défaut de lisibilité.

#### 7. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1PasseComposeTest` tient les verbes, les personnes, les familles contre la table des formes, la
part de (a), la longueur, et **refuse la chaîne littérale *"deux mots"*** dans un indice. Il opérationnalise
ma barre plus étroitement que moi : il ne lit pas ce qu'un indice fait faire à l'enfant devant les
propositions de son item, ni qu'un indice parle de l'erreur (a) (`PCO-CE1-R1`). **Je reste la seule garde**
de ces deux propriétés, de la correction d'un emploi que la classe corrige (`K4`), de l'accent des participes
et de la répartition des activités par genre, en jouant. L'équipe ne doit pas les croire couvertes par la CI.

### Mentions de non-couverture opposables

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire défiler la page, un élément
> quelconque du jeu, y compris le retour qui suit sa réponse - verdict, indice, proposition. Cette
> propriété est sortie de la porte pédagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumée. Aucune mesure ne l'a instruite sur ce
> paquet.

**Identifiants** : un neuf, `PCO-CE1-R1`. Forme copiée de `SYC-CE1-R1` : trois segments, niveau au milieu,
marqueur `R` dans le dernier (`ADR-0100`). Préfixe `PCO-CE1` déjà alloué (`PCO-CE1-1` à `-3`). Alloué dans
`squad/decisions/REGISTRE-identifiants.md`, section 4, dans le même diff. Aucun renommé.

**Signé** : `nadia-prof`, s54, instance A, 2026-10-08.

---

## Bloc de rejeu du 2026-10-08 - `PCO-CE1-R1` - nadia-prof UNIQUEMENT

### Verdict : **`PCO-CE1-R1` LEVÉE**

La ligne `FRA-CE1-19` reste `couvert-valide`, sans mention de couverture partielle. Le statut de la fiche
passe à **VALIDÉ**, sans réserve ouverte. Le verdict du 2026-10-08 au-dessus reste écrit tel quel.

### 1. Le diff, lu contre ma barre

Commit `c876fadc` (`marc-dev`), contenu dans `e9c3ec52` (`git merge-base --is-ancestor`). `git show -U0` : huit
blocs, lignes 36, 51, 66, 71, 81, 86, 126, 141 du fichier, et rien d'autre. Ce sont les lignes `indice` des items
**2, 5, 8, 9, 11, 12, 20, 23**, vérifié en lisant l'énoncé, la bonne réponse et les distracteurs au-dessus de
chacune : ce sont les huit phrases de mon tableau de la section 4. Énoncés, bonnes réponses, distracteurs,
familles déclarées et en-tête inchangés.

Sur le fichier : `grep -c 'indice: "Ne recopie pas le verbe'` rend **19**, `grep -c 'indice: "Avec ce sujet,
comment'` rend **2** (items 10 et 24). I2 reste sur 14 et 17, I3 sur 21. `TRV-CE1-5` (*"pas identique sur tout le
paquet"*) reste tenue : quatre indices distincts.

**L'espace insécable.** Les huit lignes portent U+00A0 avant les deux-points, octet pour octet comme les items
1, 3, 4 (`cat -A` : `M-BM- :` sur les onze lignes déjà en place et sur les huit nouvelles). Ma barre disait : la
ligne devient *"celle que portent déjà les items 1, 3, 4"*. C'est donc le mot pour mot de ma barre, pas un
écart, et je l'accepte. L'insécable elle-même relève du `relecteur-pedagogique` (`DEC-0002`, amendement du
2026-08-04) ; je constate seulement que les huit lignes sont identiques aux trois de référence.

### 2. Ce que j'ai joué

Jar construit au sommet `e9c3ec52` (`-DskipTests package`), copié hors du worktree, lancé sur le JDK 21.0.10,
appels HTTP réels, HTML servi lu, rendu non vu au navigateur. **Deux séries de 150 parties**, sur chaque
manche le bouton de l'infinitif quand il est servi. **Les huit items tirés** dans chaque série, de 2 à 11 fois
chacun, et un seul indice distinct par item sur l'ensemble des tirages. Après *tu as regarder*,
*vous avez regarder*, *tu as dessiner*, *il a avoir*, *vous avez dessiner*, *ils ont avoir*, *tu as parler*,
*vous avez parler* : l'indice servi est chaque fois *"Ne recopie pas le verbe écrit au début : il change."*,
le même à chaque tirage. L'enfant qui recopie l'infinitif lit désormais ce qu'il a raté.

**Le prix que j'avais annoncé est payé comme annoncé** : sur 2, 9, 11 et 12, l'enfant qui choisit l'auxiliaire
faux lit un indice qui ne lui parle pas de l'auxiliaire. Ce n'est pas un motif nouveau ; je l'avais écrit.

### 3. Ce dont je reste la seule garde

Inchangé par ce rejeu : `PaquetCe1PasseComposeTest` ne lit pas ce qu'un indice fait faire à l'enfant devant les
propositions de son item, ni qu'il parle de l'erreur (a). **Je reste la seule garde de ces deux propriétés.**
Les deux comptes de `grep` ci-dessus sont des mesures, pas un garde-fou : aucun test ne les tient.

**Identifiants** : aucun écrit, aucun renommé. **Régime** : mot pour mot, 0,25 (`PAR-10`), comme annoncé.

**Signé** : `nadia-prof`, s54, instance A, 2026-10-08.
