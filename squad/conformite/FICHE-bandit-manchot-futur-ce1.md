# Fiche de conformité - bandit-manchot-futur-ce1

- **Mécanique** : bandit manchot (mécanique générique, partagée avec les autres jeux du catalogue).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-futur.yaml` (24 items, type
  `question`), branché par `marc-dev` (`95aa8f39`, story `S54-BR-FRA-CE1-18`). Entrée de catalogue
  `bandit-manchot-futur-ce1`, titre servi *"Les verbes au futur"*, consigne servie *"Lis bien, puis choisis le
  verbe au futur."*, `nombreDeManches: 10`.
- **Ligne déclarée** : `FRA-CE1-18`. **Décision** : `DEC-0114`.
- **Conditions opposables** : `FUT-CE1-1` à `FUT-CE1-3` (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section
  5.9), `TRV-CE1-1` à `TRV-CE1-7`, `LON-CE1-1`, `-2`, `-5` ; conditions avant branchement `K1`, `K2`
  (`ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md`, section 5).
- **Statut global** : **VALIDÉ** le 2026-10-08 par `nadia-prof` (s54, instance A), sur `95214218`. Aucune
  réserve.
- **Dernière mise à jour** : 2026-10-08 (ouverture de la fiche et verdict, `nadia-prof`, s54). Aucune fiche
  n'avait été ouverte avant le branchement ; la section des critères de `camille-po` est écrite le
  2026-10-08 par `camille-po` (routage de l'arbitrage de s54, section 12 : `K1`, `K2` comme critères).

## Compétences visées

| Matière | Niveau | Domaine | Compétence | Référence au référentiel |
|---|---|---|---|---|
| français | CE1 (cycle 2) | Orthographe et grammaire | Conjuguer au futur *être*, *avoir* et les verbes du premier groupe | `squad/programme/francais/competences-ce1.md`, `FRA-CE1-18` |

## Critères pédagogiques attendus - camille-po

Écrits par `camille-po` le 2026-10-08 (s54), après le verdict, à partir des conditions de `nadia-prof`
(`ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md`, section 5, routage de la section 12). Une condition pédagogique
générale (`FUT-CE1-1` à `FUT-CE1-3`, `TRV-CE1-1` à `TRV-CE1-7`, `LON-CE1-1`, `-2`, `-5`) n'est pas recopiée ici : elle vit dans
`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 5.9. Ci-dessous, les deux critères propres à ce branchement, dans les mots
de `nadia-prof`. La colonne "Avant" est mesurée sur `34fdd167` (paquet de s53, avant branchement), la colonne "Aujourd'hui" sur
le sommet de la branche, avec la commande indiquée, lancée depuis la racine du dépôt.

**Compétence** : `FRA-CE1-18`, conjuguer au futur *être*, *avoir* et les verbes du premier groupe. **Mécanique** : bandit manchot,
`type: question`, 24 items, 10 manches.

| # | Critère (entrée / sortie observable) | Commande | Avant | Aujourd'hui |
|---|---|---|---|---|
| `K1` | La consigne servie par l'entrée de catalogue `bandit-manchot-futur-ce1` contient le mot *futur* : *"Lis bien, puis choisis le verbe au futur."* (`TRV-CE1-4` admet *futur*). Sinon, avec un mot de temps, le présent du même verbe (*"demain, je chante"*) est une phrase juste et l'item a deux réponses (`TRV-CE1-2`) | `grep -A3 'id: bandit-manchot-futur-ce1' adapters/adapter-content/src/main/resources/catalogue.yaml \| grep -c 'consigne:.*futur'` | 0 (entrée absente) | 1 |
| `K1` (écran) | La consigne est affichée sur chaque écran de manche, y compris l'écran qui suit une erreur et l'écran de révélation : un enfant qui choisit *chante* après *demain, je* relit une question qui demande le futur | partie jouée par requêtes HTTP, HTML lu (`nadia-prof`) : 400 manches sur 40 parties | sans objet | consigne présente sur les 400 manches |
| `K2` | L'item 8 ne suppose aucun objet possédé par l'enfant : l'énoncé est *"« avoir » : demain, tu ___ une dictée."* ; l'ancien énoncé (*"un nouveau jeu"*) a disparu. Bonne réponse, distracteurs, familles et indice de l'item ne changent pas (`TRV-CE1-6`) | `grep -c 'nouveau jeu' adapters/adapter-content/src/main/resources/contenu/ce1-futur.yaml` doit rendre 0, et `grep -c 'tu ___ une dictée'` doit rendre 1 | 1 et 0 | 0 et 1 |

**Ce que ces critères ne font pas** : ils ne disent pas que les 24 réponses sont justes ni que chaque indice sert l'enfant qui vient
de se tromper. Ce jugement est celui de `nadia-prof`, et la fiche dit en section 6 du bloc de validation ce qu'aucun test ne garde
(sens d'un indice devant les propositions de son item, objet possédé supposé, répartition des activités par genre, accent des
homographes). `PaquetCe1FuturTest` lit la consigne du catalogue : elle ne peut plus perdre le mot *futur* sans que la CI rougisse.

**Mention de couverture** : aucune n'est due. `MNC-1` est reproduite par `nadia-prof` dans le bloc de validation.

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pré-rempli ni anticipé par un autre agent.

### Verdict du 2026-10-08 (`nadia-prof`, s54, instance A) : **VALIDÉ**

**Done pédagogique prononcé.** `camille-po` peut passer `FRA-CE1-18` à `couvert-valide`, sans mention de
couverture partielle. Aucune réserve.

#### 1. Ce que j'ai fait

**Audit joué.** Jar construit au sommet de ma branche `95214218` (qui contient `95aa8f39`), arbre propre
avant et après la construction, `-DskipTests package`, copié hors du worktree et lancé sur le JDK 21.0.10,
port 18854. **Rendu non vu au navigateur** : appels HTTP réels, HTML servi lu.

**Porte technique : non mesurée par moi.** Le jeu se lance, sert et termine ses parties sans erreur. Le Done
technique reste à juger par la CI au sha de fusion.

**Le diff de `95aa8f39`, lu contre mon arbitrage** : consigne de l'entrée au texte de `K1`, en-tête de
`ce1-futur.yaml` remplacé par le mien, énoncé de l'item 8 au texte de `K2` (*"tu ___ une dictée"*). Aucune
autre lettre des 24 items ne change.

**Lecture masquée d'abord** : les 24 phrases lues sans les propositions ; j'ai conjugué chacune. Mes 24
formes sont les 24 bonnes réponses du fichier.

**Quarante parties complètes de dix manches** : une juste, une avec une erreur par manche, une avec trois
erreurs par manche, trente-sept mêlées. **Les 24 items tirés** (11 à 22 fois chacun), chacun avec au moins une
erreur et une révélation. Sur les 400 manches : aucune anomalie de service - propositions et indice servis
identiques au fichier, aucune révélation avant la troisième erreur, réponse révélée juste. Position de la
bonne réponse : 150, 125 et 125 aux trois places. Écran de fin atteint sur les 40 parties.

**`K1` vu à l'écran, ce que mon arbitrage (section 10, point 1) ne pouvait pas décider sans jouer** : *"Lis
bien, puis choisis le verbe au futur."* est servie sur les 400 écrans de manche, au-dessus de l'énoncé, et
reste sur l'écran qui suit chaque erreur et sur l'écran de révélation. L'enfant qui choisit *chante* après
*demain, je* relit une question qui demande le futur.

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne : choisir la forme écrite du futur, à partir du pronom sujet. *Être* et
*avoir* sur 8 items sur 24, neuf verbes du premier groupe sans modification orthographique. Ni conditionnel,
ni futur proche, ni imparfait.

#### 3. Les conditions

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `FUT-CE1-1` | **oui** | *être* 4, *avoir* 4 ; aucun verbe en `-ger`, `-cer`, `-eler`, `-eter`, `-yer` |
| `FUT-CE1-2` | **oui** | toujours un pronom ; chaque personne sujet de 4 items |
| `FUT-CE1-3` | **oui** | familles déclarées et vérifiées sur les 24 ; (c) *êtrai*, *êtra*, *êtront*, *avras*, *avront* : radicaux tirés de l'infinitif, plausibles |
| `TRV-CE1-1` | **oui** | `CE1`, une ligne, aucun champ à voix haute, aucun texte support, aucune illustration |
| `TRV-CE1-2` | **oui, par `K1`** | trois propositions distinctes ; avec la consigne qui demande le futur, le présent (b) n'est plus acceptable ; bonne réponse strictement la plus longue sur 4 items (13, 14, 19, 21) |
| `TRV-CE1-3` | **sans objet** | aucun son désigné |
| `TRV-CE1-4` | **oui** | *futur* et *verbe* admis ; aucun mot exclu |
| `TRV-CE1-5` | **oui** | trois indices de 10 à 12 mots, en rotation ; aucun ne donne la réponse, aucun ne renvoie à l'oreille, aucun ne présente comme ce qui fait la bonne réponse un critère qu'un distracteur remplit |
| `TRV-CE1-6` | **oui** | aucun prénom ; *tu* : film, maîtresse, verres, dictée (`K2`) - aucun objet possédé supposé ; troisième personne : *elles danseront*, à côté de *elle travaillera*, *il écoutera la radio* : pas de partage |
| `TRV-CE1-7` | **oui** | 24 items |
| `LON-CE1-1`, `-2`, `-5` | **oui** | énoncés de 9 mots au plus, une phrase, question portée par la consigne ; propositions d'un mot |
| `K1` | **oui** | section 1 |
| `K2` | **oui** | item 8, *"« avoir » : demain, tu ___ une dictée."* |

#### 4. Les indices, lus après l'erreur

Après une erreur, la proposition essayée est marquée et l'indice s'affiche ; l'enfant choisit parmi les deux
autres. Les trois indices tournent dans l'ordre du fichier :

- *"Le verbe change selon le moment : regarde le début de la phrase."* (items 1, 4, 7, 10, 13, 16, 19, 22)
  parle du présent (b), pas de la personne (a) ;
- *"Qui fait l'action ? Regarde le petit mot devant le trou."* (items 2, 5, 8, 11, 14, 17, 20, 23) parle de la
  personne (a), pas du présent (b) ;
- *"Lis le mot de temps, puis le petit mot devant le trou."* (items 3, 6, 9, 12, 15, 18, 21, 24) parle des deux.

Sur les items où l'indice ne parle pas de la famille que l'enfant vient de choisir, il est à l'étage
insuffisant : il dit où regarder, il est vrai, il ne confirme rien. C'est `L1` de mon arbitrage, que j'avais
écrite pour l'item 16 seul et qui vaut aussi pour les items 1, 7, 10, 13, 19, 22 (famille (a) sous le premier
indice) et 2, 5, 11, 14, 17, 23 (famille (b) sous le deuxième). **Pas de réserve, et je dis pourquoi la règle
n'est pas celle du passé composé** : `FUT-CE1-3` ne nomme aucune erreur centrale de la ligne, à la différence
de `PCO-CE1-3` (*"c'est l'erreur du CE1"*) ; les deux familles (a) et (b) sont servies à parts comparables et
chaque indice en sert une. Rien ne fait passer l'une avant l'autre.

#### 5. Exactitude, niveau, ton

**Les 24 réponses sont justes**, une seule acceptable par item avec la consigne, vérifié en lisant chaque
phrase avec chacune des trois propositions. Accents relus à la main : *écoutera*, *écoute*, *écouteras*,
*étoiles*, *maîtresse*, *chœur*, *scène*, *dictée*, *bientôt*, *à l'école*, *à la maîtresse* portent le leur ;
les distracteurs (c) *êtrai*, *êtra*, *êtront* portent l'accent de l'infinitif dont ils sont tirés, ce qui est
la forme de l'erreur.

**Niveau** : verbes et situations de la classe et de la vie courante.

#### 6. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1FuturTest` tient les verbes, les personnes, les familles contre la table des formes, la longueur, et
**lit la consigne du catalogue** : elle ne peut plus perdre le mot *futur* sans que la CI rougisse (`K1`). Il
ne lit ni ce qu'un indice fait faire à l'enfant devant les propositions de son item, ni un objet possédé
supposé chez l'enfant (`K2`), ni la répartition des activités par genre, ni l'accent des homographes : **je
reste la seule garde de ces quatre propriétés**, en jouant.

### Mentions de non-couverture opposables

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire défiler la page, un élément
> quelconque du jeu, y compris le retour qui suit sa réponse - verdict, indice, proposition. Cette
> propriété est sortie de la porte pédagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumée. Aucune mesure ne l'a instruite sur ce
> paquet.

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s54, instance A, 2026-10-08.
