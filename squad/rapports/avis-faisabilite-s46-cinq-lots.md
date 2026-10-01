# s46 - Avis de faisabilite (`theo-archi`) - cinq lots de `DEC-0102` : `MAT-CE1-05`, `EMC-CM1-01`, `EMC-CM1-09` (instance A), `SCI-CM1-03`, `SCI-CM1-16` (instance B)

- **Date** : 2026-10-01
- **Base** : `round-two` a `8b0fe8b4`, plus `DEC-0102` et la section "Sprint 46" de
  `docs/backlog/backlog.md` lus dans l'arbre de travail, non encore commites par `camille-po`. Aucun
  diff de code ni de contenu dans cet avis.
- **Pourquoi ce fichier est ici** : `DEC-0102` section 8 route l'avis a `theo-archi` ; le dossier de
  sprint est a ouvrir par `sofia-sm`, qui reprend ou cite cet avis dans `planning.md`.
- **Ce que cet avis ne vaut pas** : il ne fixe aucun contenu, aucun critere, aucune affectation, et ne
  rejuge aucune condition. Le Done pedagogique reste a `nadia-prof`.

---

## 1. Verdicts

| Lot | Ligne | Verdict |
|---|---|---|
| `S46-MAT-SOUSTRACTION` | `MAT-CE1-05` | **infaisable en l'etat des conditions, sans que l'architecture soit en cause.** La mecanique et le chargeur suffisent, aucun `ADR` n'est du. Mais `SOP-CE1-5` n'admet que trois formes d'erreur, et **les trois donnent toujours une valeur superieure a la bonne reponse** (section 3.2). La bonne reponse est donc la plus petite sur 100 % des items a trois propositions, et `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` est rouge par construction. Il faut une decision de `nadia-prof` avant le premier item (Q1) |
| `S46-EMC-EMOTION-CM1` | `EMC-CM1-01` | **faisable en l'etat.** Zero code de production |
| `S46-EMC-INSTITUTIONS-CM1` | `EMC-CM1-09` | **faisable en l'etat.** Zero code de production. Une question non bloquante (Q2) |
| `S46-SCI-MOUVEMENT-CM1` | `SCI-CM1-03` | **faisable en l'etat.** Zero code de production. Un piege de fabrication (section 6) |
| `S46-SCI-PHENOMENES-CM1` | `SCI-CM1-16` | **faisable en l'etat.** Zero code de production |

**Aucune mecanique neuve, aucune `ArchRule` neuve, aucun `ADR`, aucune persistance.** Les cinq lots
sont des paquets statiques de type `question`, joues au `bandit-manchot`, sur le patron de s45. L'etat
d'une partie reste dans la session HTTP (`DEC-0028`).

## 2. Ce que coute chaque lot

Le patron de s45 (`avis-faisabilite-s45-cinq-lots.md`, section 3) vaut tel quel : le paquet, son
entree de catalogue, son test de paquet, et l'inventaire de `TexteAccentueExposeParLePortEntrantTest`
pour les chaines neuves (titre, consigne, competence). Les mecaniques ne lisent aucun champ de matiere
(`ADR-0006`). Les cinq domaines sont deja servis par au moins un paquet : `Nombres et calculs` (CE1),
`Respecter autrui`, `Construire une culture civique`, `Matière, mouvement, énergie, information`,
`La planète Terre. Les êtres vivants dans leur environnement` (releve du premier `domaine:` des
paquets `cm1-emc-*` et `cm1-sciences-*`, et de `ce1-additions-posees`).

En plus, pour le lot `MAT` seulement : une entree de `SousPartieDeMathematiquesCe1`
(`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`, `ADR-0097`), sur la convention des
entrees existantes : ligne `MAT-CE1-05`, libelle du referentiel sans le point final, `"Poser et
effectuer une soustraction (nombres à deux ou trois chiffres)"`. Sans elle, le paquet est rouge.

## 3. `S46-MAT-SOUSTRACTION` (`MAT-CE1-05`)

### 3.1 Correction de mon avis de s45 : aucun `ADR` n'est du ici

J'ai ecrit a s45 (`avis-faisabilite-s45-cinq-lots.md`, fin de la section 4.1) que
`ChaquePaquetDeSoustractionsDeCe1TientSaRepartitionParFormeTest` avait *"la meme construction, motif
`a - b = ?`"* que le garde-fou des additions, et que la meme restriction de perimetre s'appliquerait
le jour ou `MAT-CE1-05` serait engagee. **C'est faux.** Ce garde-fou derive sa population de la
**competence declaree** (`ADR-0097`, decision 2), egale a celle de
`SousPartieDeMathematiquesCe1.SOUSTRACTIONS` (`"Calcul mental - soustractions dans la limite de
100"`). Sa mise a l'epreuve `unPaquetDontLaCompetenceNEstPasCelleDesSoustractionsSortDuPerimetre`
le verifie deja. Un paquet qui declare la competence de `MAT-CE1-05` n'y entre pas, quelle que soit
la forme de ses enonces. Constat fait en lisant la methode `retenir` du garde-fou a `8b0fe8b4`.

### 3.2 Les trois erreurs de `SOP-CE1-5` sont toujours au-dessus de la bonne reponse

Pour `a - b` avec `0 <= b <= a`, en notant `a_i` et `b_i` les chiffres de rang `i` :

1. **emprunt oublie** (la definition de `SOP-CE1-5`) : `somme de |a_i - b_i| * 10^i`. Comme
   `a - b = somme de (a_i - b_i) * 10^i` et que `|x| >= x`, cette valeur est superieure ou egale a
   `a - b`, avec egalite si et seulement si aucune colonne n'emprunte ;
2. **emprunt non reporte** : la colonne qui emprunte gagne 10, la suivante ne perd pas 1. Le resultat
   vaut `a - b` plus `10^(i+1)` par emprunt, donc strictement plus que `a - b` des qu'il y a un
   emprunt ;
3. **somme au lieu de la difference** : `a + b >= a - b`.

**Mesure exhaustive.** Script Python de poste, non commite, sur tous les couples
`10 <= a <= 1000`, `0 <= b <= a` : 335 124 couples portent au moins un emprunt. Sur ces couples,
**zero** valeur des trois formes est inferieure ou egale a la bonne reponse. Sur les couples sans
emprunt, les formes 1 et 2 egalent la bonne reponse dans 100 % des cas : seule la somme reste.

**Ce que le garde-fou de rang en fait.** Il classe la bonne reponse par sa valeur parmi trois
propositions numeriques, et exige que chaque rang porte au moins 10 % des items (`SEUIL_MINIMUM`) et
aucun plus de la moitie (`SEUIL_MAJORITE`). Sur un paquet qui tient `SOP-CE1-5`, le rang "plus petite"
porte 100 % des items a trois propositions, le rang "plus grande" 0 %. Rouge sur les deux seuils,
quel que soit le choix des nombres. Ce garde-fou est ne du refus de `S12-3` par `nadia-prof` : une
strategie qui presse toujours le meme rang ne doit pas gagner la majorite des manches.

**Le precedent qui passe, pour comparaison.** `ce1-soustractions-jusqu-a-100` (`MAT-CE1-03`, 32 items,
meme script) : 15 items ou la bonne reponse est la plus petite, 13 au milieu, 4 la plus grande. Il
passe parce que ses distracteurs ne sont pas bornes a ces trois formes. L'addition de s45 passait
parce que la retenue oubliee y donne une valeur plus petite (`48 + 26 -> 64`) : en soustraction,
l'oubli donne une valeur plus grande.

### 3.3 Trois sorties qui ne sont pas des solutions

1. **Des items a deux propositions.** Le garde-fou de rang ne lit que les items a trois propositions
   numeriques : il deviendrait vert, et presser la plus petite proposition gagnerait toujours. C'est le
   defaut de `S12-3`, construit. Refuse.
2. **Inscrire le paquet dans les dettes du garde-fou** (`DETTE_DE_RANG`, plafond 2, et
   `DETTE_DE_SEUIL_MINIMUM`, plafond 3 : chaque plafond vaut aujourd'hui exactement le nombre de
   paquets nommes). Relever un plafond pour admettre un paquet neuf fait de ces cliquets des listes
   d'exemptions. Refuse.
3. **Des distracteurs hors des trois formes.** Vert pour l'automate, contraire a la lettre de
   `SOP-CE1-5` ("et seulement celles-la"), donc refus previsible a l'audit.

### 3.4 Ce qu'il faut pour que la ligne devienne faisable

Une decision pedagogique, que je ne prends pas : `nadia-prof` dit quelle erreur nommee, produisant une
valeur **inferieure** a la bonne reponse, entre dans la liste de `SOP-CE1-5`, ou dit autrement comment
la condition se concilie avec le garde-fou de rang (Q1). La contrainte arithmetique, elle, est fixee
par le garde-fou : sur les items a trois propositions, au moins la moitie porte un distracteur
inferieur a la bonne reponse, et au moins 10 % en portent deux.

Deux contraintes de fabrication a connaitre au meme moment, sans etre bloquantes :

- **la somme et `SOP-CE1-4`** : `a + b` n'a le meme nombre de chiffres que `a - b` que si les deux
  restent sous la meme puissance de dix (`52 - 27 = 25`, `79` admis ; `52 - 47 = 5`, `99` exclu), ce
  qui la disqualifie sur une partie des items ;
- **les items sans emprunt** (au moins deux, `SOP-CE1-2`) : seule la somme y est disponible, donc un
  seul distracteur. `SOP-CE1-4` exige alors que la somme partage le chiffre des unites de la
  difference, ce qui n'arrive que si le chiffre des unites de `b` vaut 0 ou 5.

### 3.5 Conditions encodables, une fois Q1 tranchee

Sur le patron de `PaquetCe1AdditionsPoseesTest`.

| Condition | Assertion | Ce qui reste a `nadia-prof` |
|---|---|---|
| `SOP-CE1-1` | la consigne de l'entree de catalogue contient `Pose` | la mention dans la matrice |
| `SOP-CE1-2` | au moins 3 items a deux chiffres, au moins 3 a trois chiffres ; emprunts comptes par colonne : au moins la moitie des items avec un emprunt, au moins 1 avec deux, au moins 2 sans | rien |
| `SOP-CE1-3` | au moins `ceil(2n/3)` items ont un terme de trois chiffres ou un premier terme superieur a 100 | rien |
| `SOP-CE1-4` | meme nombre de chiffres pour les propositions ; au moins un distracteur partage le chiffre des unites | "meme ordre de grandeur", au-dela du nombre de chiffres |
| `SOP-CE1-5` | marqueur en commentaire sur chaque ligne `distracteurs`, relu et compare au calcul a partir des operandes ; entiers positifs, distincts | que les erreurs soient celles d'un enfant |
| `SOP-CE1-6` | enonce strictement `a - b = ?`, `0 <= b <= a <= 1000`, propositions faites de chiffres seuls | rien |

## 4. `S46-EMC-EMOTION-CM1` (`EMC-CM1-01`)

Sur le patron de `PaquetCm1EmcFaitOpinionTest` et `PaquetCpEmcEmotionsAutruiTest`. Les deux taches
(identifier l'emotion, distinguer ce qu'une personne ressent de ce qu'elle fait) s'ecrivent en trois
propositions textuelles, que `Question` porte sans changement.

| Condition | Assertion | Ce qui reste a `nadia-prof` |
|---|---|---|
| `FER-2` | au moins 11 items (10 manches, `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`) | rien |
| `FER-3` | marqueur par item `# tache: emotion` ou `# tache: ressentir-agir`, deux valeurs, au moins 3 items pour la moins servie | que ce soient deux taches |
| `EMC-1`, `EMC-8` | aucun enonce ne s'adresse a l'enfant a la deuxieme personne pour lui demander ce qu'il ressent ou ferait (verification partielle, a construire sur les formes que la source cite) | le fond |
| `EMC-2`, `EMC-5` | aucune | entierement |
| `CYC-1` | aucune : aucun garde-fou ne compare deux paquets | entierement |

## 5. `S46-EMC-INSTITUTIONS-CM1` (`EMC-CM1-09`)

Meme patron. `FER-1` est calculable : chacune des trois institutions de la parenthese (mairie,
conseil municipal, ecole) est la `bonneReponse` d'au moins un item, lue dans le paquet charge. `FER-2`
et `FER-3` comme en section 4, taches `role` et `a-qui-s-adresser`. `EMC-6` et `CYC-1` restent a
`nadia-prof`.

La story ne cite que `EMC-6`. Si un item de la tache "a qui s'adresser" a pour bonne reponse un service
d'urgence, `EMC-7` (exactitude des numeros d'urgence) pourrait valoir. Je ne le tranche pas (Q2).

## 6. `S46-SCI-MOUVEMENT-CM1` (`SCI-CM1-03`)

Sur le patron de `PaquetCm1SciencesEnergieTest`. La story cite cette fois `SCI-1` dans sa version qui
vaut (l'`indice`) et `SCI-4`, l'ecart route deux fois a s44 et s45 ne se reproduit pas ici.

| Condition | Assertion | Ce qui reste a `nadia-prof` |
|---|---|---|
| `FER-1` | marqueur par item `# element: trajectoire`, `vitesse` ou `repere`, les trois presents | que l'item serve l'element |
| `FER-2`, `FER-3` | au moins 11 items ; marqueur `# tache:`, deux valeurs, au moins 3 pour la moins servie | que ce soient deux taches |
| `SCI-1` | chaque indice compte une phrase et ne contient ni la bonne reponse de son item ni `ta réponse` | que l'indice dise pourquoi la conception est fausse |
| `SCI-4` | deux distracteurs sur chaque item ; avec `AuPlusTroisPropositionsParItemTest`, exactement trois propositions | quels items portent une conception fausse |
| `SCI-3` | items dont la bonne reponse porte un chiffre : au plus `floor(n / 6)` | les valeurs ecrites en lettres |
| `HIS-3` | chaque ligne `- enonce:` porte `# HIS-3:` suivi d'un texte non vide | que la source dise le fait |
| refus d'avance (aucun calcul de vitesse, de duree ou de longueur) | aucun enonce ne porte deux nombres suivis d'une unite de longueur ou de temps | le fond |

**Piege de fabrication.** Une proposition qui commence par un nombre suivi de `h`, `heure`, `min` ou
`minute` est lue comme une duree par le garde-fou de rang (`ADR-0102`, motif
`RESSEMBLE_A_UNE_DUREE`), et trois propositions numeriques entrent dans sa population. Des propositions
textuelles (`plus vite`, `en ligne droite`) evitent les deux, et le refus d'avance n'en demande pas
d'autres.

## 7. `S46-SCI-PHENOMENES-CM1` (`SCI-CM1-16`)

Meme patron que la section 6, sans le refus d'avance et le piege de duree.

- `FER-1` : marqueur par item `# element: geologique`, `meteorologique` ou `risque`, les trois
  presents.
- `BIA-2` est en partie calculable : marqueur par item `# lieu: france`, `europe`, `ailleurs` ou
  `non-situe`. Assertion : parmi les items situes, au moins un est en France ou en Europe. Que le
  paquet n'enseigne pas une "geographie du malheur" reste a `nadia-prof`.
- `SCI-2` : aucune assertion, entierement a `nadia-prof`.

**Adjacence a signaler a l'instance B, sans collision.** `cm1-comprehension` (francais CM1) porte un
item d'inference dont la bonne reponse est `"une grosse tempête"`. Un item ne sert qu'une ligne
(`ADR-0081`, clause 5) : rien n'est compte deux fois. Releve par recherche lexicale de `séisme`,
`éruption`, `tempête`, `inondation` et `volcan` sur les paquets de `contenu/`, un seul resultat.

## 8. Architecture : rien de neuf a encoder

Chaque condition des sections 3 a 7 est une propriete d'un item ou d'un paquet de YAML. ArchUnit lit
du bytecode : le mecanisme automatique qui les garde est le test de chaque paquet, execute par le job
`build`. Aucune dependance neuve entre mecaniques ni vers un contenu concret : les regles de
`DecouplageMecaniqueContenuTest` n'ont rien de neuf a interdire.

Compte inchange depuis s45 : **36 `static final ArchRule`, 35 `@ArchTest`**. Methode : `git grep -c`
sur les fichiers suivis de `domain`, `application`, `adapters` et `bootstrap`, arbres `contreexemple`
exclus, donc `target/` et worktrees exclus par construction.

## 9. Questions routees, via `sofia-sm`

| Code | Question | A qui | Bloquante ? |
|---|---|---|---|
| Q1 | `SOP-CE1-5` : les trois formes admises sont toujours au-dessus de la bonne reponse (section 3.2), ce qui rend le garde-fou de rang rouge par construction. Quelle erreur nommee, de valeur inferieure, entre dans la liste, ou comment la condition se concilie-t-elle avec ce garde-fou ? | `nadia-prof` | **oui, avant le premier item du lot `MAT`** |
| Q2 | `EMC-CM1-09` : `EMC-7` vaut-elle sur les items dont la bonne reponse est un service d'urgence ? | `nadia-prof`, puis `camille-po` pour la story | non |
| Q3 | Si Q1 n'est pas tranchee avant l'ecriture, le lot `MAT` ne peut pas etre livre vert sans l'une des sorties refusees en section 3.3 | `sofia-sm` | planification du lot |

## 10. Mesure

- **Faits arithmetiques et rangs** (section 3.2) : script Python de poste, non commite, enumerant
  les couples et lisant `ce1-soustractions-jusqu-a-100.yaml` par `yaml.safe_load`. Les definitions
  codees sont celles de `SOP-CE1-5` ; "non reporte" est code comme "chaque colonne modulo 10, sans
  retenue propagee".
- **Garde-fous cites** : lus dans le code a `8b0fe8b4`. Aucun n'a ete execute sur un paquet neuf,
  puisqu'aucun n'existe : leurs verdicts sur les lots sont deduits de leur code.
- **Gardes lancees sur ce fichier** : `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (10
  tests) et `ToutesLesReferencesDeDecisionResolventTest` (3 tests), par `bash outils/mvn-jdk21.sh -B
  -pl bootstrap -am test -Dtest=...`, zero echec, machine virtuelle des tests en Java 21.0.10 (version
  lue dans les rapports Surefire). `verifier-chemins.sh` sur ce fichier : vert. `mvn -B clean verify`
  complet non lance : ce diff ne touche que ce fichier.
