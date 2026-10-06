# Fiche de conformité - bandit-manchot-conjugaison-present-ce1

- **Mécanique** : bandit manchot (mécanique générique, partagée avec les autres jeux du catalogue).
- **Contenu(s)** : à créer, type `question`, au moins 20 items (`TRV-CE1-7`). Story `S52-FRA-PRESENT`
  (`docs/backlog/backlog.md`), décision `DEC-0111` (section 4), développeur : `marc-dev` (planning de s52).
  Le nom de fichier de contenu et l'identifiant d'entrée de catalogue sont fixés par le développeur au
  premier commit ; le nom de cette fiche est proposé par `camille-po` et renommé si l'identifiant diffère.
- **Statut global** : en construction - aucun contenu écrit à l'ouverture de cette fiche.
  **REFUSÉ** le 2026-10-06 par `nadia-prof` (s52, instance A), sur `befc4128` : voir le bloc de validation.
  **VALIDÉ** au second passage le 2026-10-06, sur `40989ba9`.
- **Dernière mise à jour** : 2026-10-06 (ouverture, `camille-po`, s52 ; verdict, `nadia-prof`, s52).

## Compétences visées

| Matière | Niveau | Domaine | Compétence | Référence au référentiel |
|---|---|---|---|---|
| français | CE1 (cycle 2) | Orthographe et grammaire | Conjuguer au présent de l'indicatif *être*, *avoir*, les verbes du premier groupe et quelques verbes fréquents (*aller*, *faire*, *dire*, *venir*) | `squad/programme/francais/competences-ce1.md`, `FRA-CE1-17` |

Arbitrage préalable : `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 5.8, verdict
**accepté sous conditions `PRS-CE1-1` à `PRS-CE1-3`, plafond `couvert-valide`**. Conditions transverses
`TRV-CE1-1` à `TRV-CE1-7` (sections 2 et 6.1), `LON-CE1-1`, `LON-CE1-2`, `LON-CE1-5` (section 1).

## Critères pédagogiques attendus - camille-po

- [ ] Mécanique `bandit-manchot`, forme d'item `question`, sans illustration, sans `texteSupport`, sans
  champ à voix haute (`TRV-CE1-1`).
- [ ] Les six verbes (*être*, *avoir*, *aller*, *faire*, *dire*, *venir*) sont la cible d'au moins la moitié
  des items ; aucun verbe en `-ger`, `-cer`, `-eler`, `-eter`, `-yer`, ni *acheter*, ni *lever*
  (`PRS-CE1-1`).
- [ ] Le sujet de chaque item est un pronom personnel, jamais un nom ni un prénom ; chacune des six personnes
  est le sujet d'au moins un dixième des items (`PRS-CE1-2`) ; aucun sujet n'est un groupe nominal
  (frontière avec `FRA-CE1-14`, `ACV-CE1-3`).
- [ ] Trois propositions par item, deux à deux distinctes, une seule acceptable ; chaque distracteur est de la
  famille (a) autre personne du même verbe, (b) forme régularisée d'un verbe irrégulier, ou (c) infinitif,
  déclarée à côté de l'item (`PRS-CE1-3`, `TRV-CE1-2`).
- [ ] La bonne réponse est strictement la plus longue des trois propositions dans la moitié des items au plus
  (`TRV-CE1-2`).
- [ ] Chaque item porte `FRA-CE1-17` et elle seule ; énoncé, propositions et indice respectent `LON-CE1-1`,
  `LON-CE1-2`, `LON-CE1-5`, `TRV-CE1-4`, `TRV-CE1-5` ; aucun indice ne donne la réponse.
- [ ] Aucune forme *alle* ni *alles* tant que la question Q1 de l'avis de faisabilité de s52 (R5 du planning)
  n'a pas de réponse écrite de `nadia-prof`.
- [ ] Le jeu ne demande jamais d'écrire une forme et ne sert ni le futur ni le passé composé
  (`FRA-CE1-18`, `-19`).
- [ ] Texte servi à l'enfant intégralement accentué, orthographe et ponctuation irréprochables (`DEC-0002`).
- [ ] Mention de couverture attendue : aucune (la ligne ferme sans mention de couverture partielle).

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pré-rempli ni anticipé par un autre agent.

### Verdict du 2026-10-06 (`nadia-prof`, s52, instance A) : **REFUSÉ**

**Done pédagogique non prononcé.** `FRA-CE1-17` reste hors de `couvert-valide`. Le paquet revient à
`camille-po`. Un seul motif de refus (section 4), réparable en changeant deux indices. Un second défaut
(section 5), qui n'aurait été qu'une réserve à lui seul, est à corriger dans le même rejeu.

#### 1. Ce que j'ai fait

**Audit joué.** Paquet `ce1-conjugaison-present.yaml` et entrée de catalogue
`bandit-manchot-conjugaison-present-ce1` au commit `befc4128`, joués sur le jar construit à ce sha (arbre
propre avant et après la construction, `-DskipTests package`), copié hors du worktree et lancé sur le
port 18830. **Rendu non vu au navigateur** : appels HTTP réels, HTML servi lu.

**Porte technique : non mesurée par moi sur `befc4128`**, pour le motif écrit dans
`FICHE-bandit-manchot-categories-ce1.md` (section 1). Le jeu se lance, sert et termine ses parties sans
erreur.

**Lecture masquée d'abord** : les 24 phrases lues sans les propositions ; j'ai conjugué chacune
moi-même. Mes 24 formes sont les 24 bonnes réponses du fichier.

**Quarante parties complètes de dix manches**, mêmes conduites que pour le paquet de catégories. **Les
24 items tirés** (11 à 26 fois chacun), chacun avec au moins une erreur et une révélation. Sur les 400
manches : aucune anomalie de service - propositions et indice servis identiques au fichier, aucune
révélation avant la troisième erreur, réponse révélée juste. Position de la bonne réponse : 134, 140
et 126 aux trois places. Écran de fin atteint ; après une révélation, il redonne la phrase et la bonne
réponse.

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne : choisir la forme écrite du présent, à partir du pronom sujet.
Aucun futur, aucun passé composé, aucune forme à écrire. Les distracteurs (a) homophones de la bonne
réponse (*dessine* / *dessines*, *dit* / *dis*, *es* / *est*) sont le coeur de la compétence au CE1 :
l'accord écrit qui ne s'entend pas.

#### 3. Les conditions

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `PRS-CE1-1` | **oui** | les six verbes nommés sur 18 items sur 24 ; six verbes du premier groupe sans modification orthographique |
| `PRS-CE1-2` | **oui** | toujours un pronom ; chaque personne sujet de 4 items |
| `PRS-CE1-3` | **oui** | familles déclarées et vérifiées ; (b) : *disez*, *faisez*, *faisent*, *allent*, *venent*, toutes admises d'avance (mon arbitrage de s52, section 1) ; ni *alle*, ni *alles*, ni participe |
| `TRV-CE1-1` | **oui** | `CE1`, une ligne, aucun champ à voix haute, aucun texte support, aucune illustration |
| `TRV-CE1-2` | **oui** | trois propositions distinctes, une seule acceptable ; bonne réponse strictement la plus longue sur 5 items sur 24 |
| `TRV-CE1-3` | **sans objet** | aucun son désigné |
| `TRV-CE1-4` | **oui** | aucun mot exclu ; *verbe* admis |
| `TRV-CE1-5` | **non, motif du refus** | section 4 |
| `TRV-CE1-6` | **en partie** | aucun prénom, aucun *"tu"* qui suppose un foyer ; **activités distribuées par genre**, section 5 |
| `TRV-CE1-7` | **oui** | 24 items |
| `LON-CE1-1`, `-2`, `-5` | **oui** | phrases courtes, une phrase ; propositions d'un mot |

#### 4. Motif du refus : deux indices renvoient à l'oreille là où elle ne décide pas (`TRV-CE1-5`)

| Item | Phrase | Propositions | Indice servi |
|---|---|---|---|
| 9 | *« parler » : vous ___ trop fort.* | **parlez** / parlent / parler | *"Lis la phrase dans ta tête avec chaque proposition."* |
| 24 | *« dire » : tu ___ la vérité.* | **dis** / dit / dire | *"Lis la phrase dans ta tête avec chaque proposition."* |

*Parlez* et *parler* se prononcent de même ; *dis* et *dit* aussi. L'enfant qui s'est trompé - le plus
souvent en choisissant *parler*, une erreur fréquente au CE1 - lit l'indice, relit
la phrase dans sa tête, et l'entend juste avec sa faute. L'indice ne l'arrête pas à un étage
insuffisant, comme ceux qu'`ALP-CE1-R1` a fait corriger : **il lui donne une méthode qui confirme
l'erreur que l'item est fait pour corriger**, et il lui dit, sans l'écrire, que l'orthographe du verbe
s'entend. C'est la règle fausse que `TRV-CE1-5` interdit. Je l'ai vu en jouant : l'indice servi sur ces
deux items est bien celui du fichier.

**Cause probable, à vérifier par `marc-dev`** : les cinq indices tournent dans l'ordre du fichier, un
item sur cinq (items 4, 9, 14, 19, 24 pour celui-ci). Sur les items 4, 14 et 19, la bonne réponse ne
s'entend pas comme ses distracteurs, et l'indice y est juste.

**Ce qui lève le refus** : sur tout item dont la bonne réponse a un distracteur qui se prononce de même,
l'indice ne renvoie pas à l'écoute de la phrase. Sur ce paquet, ce sont les items 9 et 24 ; l'un des
quatre autres indices du paquet, qui renvoient au sujet, convient. Le critère est décidable sur l'item
si le test tient une table des paires homophones du paquet.

#### 5. À corriger dans le même rejeu : trois activités servies du côté du stéréotype (`TRV-CE1-6`)

Les sujets féminins portent 2 items sur 8 à la troisième personne : *elle écoute l'histoire*, *elles
vont à la bibliothèque*. Les sujets masculins portent les 6 autres, dont *ils font du bruit*. Écouter,
aller lire d'un côté, faire du bruit de l'autre : c'est le partage connu, et il va tout entier du même
côté. *Ils* peut désigner un groupe mêlé, et *ils lavent les verres* va contre le stéréotype ; c'est
pourquoi, seul, ce défaut n'aurait été qu'une réserve, du même ordre que `TPS-CE1-R1`.

**Ce qui le lève** : l'une au moins des trois activités (*écouter l'histoire*, *aller à la
bibliothèque*, *faire du bruit*) change de côté. Le plus simple, sans toucher une réponse : deux *il*
et deux *elle*, deux *ils* et deux *elles*, et *faire du bruit* porté par *elles*.

#### 6. Exactitude, niveau, ton

**Les 24 réponses sont justes**, une seule acceptable par item, vérifié en lisant chaque phrase avec
chacune des trois propositions. Accentuation relue à la main sur les homographes (Q3 de mon arbitrage de
s52) : *écoute*, *écoutes*, *dessine*, *lave*, *travaille*, *êtes*, *à* devant *l'école*, *l'heure*,
*la piscine*, *la bibliothèque* ; *a* (verbe) en distracteur de l'item 2 est juste ; aucun participe
passé servi. Les doutes de `marc-dev` sur *dit* / *dites* / *faites* et *a* / *la* / *ou* : rien de
fautif.

**Niveau** : verbes et situations de la classe et de la cour.

#### 7. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1ConjugaisonPresentTest` tient les verbes, les personnes, les familles contre la table des
formes du présent, la longueur. **Il ne lit ni ce qu'un indice fait faire à l'enfant devant les
propositions de son item, ni l'activité d'un sujet selon son genre** : je reste la seule garde des deux
défauts de ce verdict, et de l'accent des homographes, en jouant.

**`MNC-1`** : sans objet, aucun Done n'est prononcé.

**Identifiants** : aucun neuf, aucun renommé. Le refus et le défaut de la section 5 ne portent pas
d'identifiant de réserve.

**Signé** : `nadia-prof`, s52, instance A, 2026-10-06.

### Second passage, le 2026-10-06 (`nadia-prof`, s52, instance A) : **VALIDÉ**

**Done pédagogique prononcé.** `camille-po` peut passer `FRA-CE1-17` à `couvert-valide`, sans mention de
couverture partielle. Aucune réserve. Le refus du premier passage reste écrit ci-dessus.

#### 1. Ce que j'ai fait

**Audit joué.** Rejeu `40989ba9` (`marc-dev`), joué sur le jar construit à ce sha (arbre propre avant et
après la construction, aucun Maven en cours dans le worktree, `-DskipTests package`), copié hors du
worktree et lancé sur le port 18831. **Rendu non vu au navigateur.** **Porte technique : non mesurée par
moi sur `40989ba9`** ; le jeu se lance, sert et termine ses parties sans erreur.

**Le diff, lu contre `befc4128`** : quatre items touchés, aucune autre lettre du paquet hors de
l'en-tête. Items 9 et 24 : l'indice change. Items 16 et 17 : *ils* devient *elles*, *il* devient *elle*.

**Lecture masquée d'abord des quatre items touchés** : *vous ___ trop fort* (parler), *tu ___ la vérité*
(dire), *elles ___ du bruit* (faire), *elle ___ merci* (dire). Mes quatre formes (*parlez*, *dis*,
*font*, *dit*) sont celles du fichier.

**Paquet entier rejoué** : quarante parties de dix manches, puis soixante. Les 24 items tirés (11 à 25 fois
chacun sur la première série), chacun avec au moins une erreur et une révélation ; aucune anomalie de
service, l'indice servi est celui du fichier. Position de la bonne réponse : 129, 113 et 158 sur la
première série, 194, 198 et 208 sur la seconde ; l'écart de la première ne se reproduit pas.

#### 2. Contre la barre du refus

| Barre | Tenue |
|---|---|
| `TRV-CE1-5` : sur tout item dont la bonne réponse a un distracteur homophone, l'indice ne renvoie pas à l'écoute de la phrase | **oui**. Item 9 (*parlez* / *parler*) : *"Le mot avant le trou montre comment finit le verbe."* ; item 24 (*dis* / *dit*) : *"Demande-toi : de qui parle la phrase ?"*. Les deux renvoient au sujet. *"Lis la phrase dans ta tête avec chaque proposition."* reste sur les items 4, 14 et 19, où aucune proposition ne se prononce comme la bonne réponse (*faisons* / *faites* / *faire*, *avons* / *avez* / *avoir*, *sommes* / *êtes* / *être*) |
| `TRV-CE1-6` : l'une au moins des trois activités change de côté | **oui** : *"elles font du bruit"*. Troisième personne : deux *il*, deux *elle*, deux *ils*, deux *elles* |

*Elle dit merci* n'ajoute aucune activité du côté du stéréotype. Toutes les autres conditions du
premier passage restent tenues : rien d'autre n'a changé.

#### 3. Ce dont je reste la seule garde sur cette ligne

Inchangé depuis le premier passage, section 7 : ce qu'un indice fait faire à l'enfant devant les
propositions de son item, la répartition des activités par genre, et l'accent des homographes.

### Mentions de non-couverture opposables

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire défiler la page, un élément
> quelconque du jeu, y compris le retour qui suit sa réponse - verdict, indice, proposition. Cette
> propriété est sortie de la porte pédagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumée. Aucune mesure ne l'a instruite sur ce
> paquet.

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s52, instance A, 2026-10-06.
