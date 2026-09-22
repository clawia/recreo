# Planning - s37 - premier paquet mathematiques CE1 et ordre des niveaux

- **Horodatage de debut** : 2026-09-21 22:05 (UTC+02:00).
- **Casquette** : `sofia-sm`, Scrum Master.
- **Slug** : celui deja choisi par `theo-archi` dans son worktree de preparation
  (`s37-premier-paquet-mathematiques-ce1-et-ordre-des-niveaux`), repris tel quel.
- **Prealable, deja fait avant l'ouverture de ce planning** : `DEC-0089` (ouverture du perimetre
  CE1, mathematiques seule) et `DEC-0090` (l'ordre des niveaux de l'en-tete devient une cle de tri
  explicite) sont toutes deux **Acceptees**.

## 1. Lignee et fusion des deux worktrees

Verifie sur chacun des deux worktrees, avant toute ecriture (`git rev-parse --abbrev-ref HEAD`
puis `git merge-base --is-ancestor round-two HEAD`) :

- `s37-camille` (`../recreo-s37-camille`, commit `67ebefe0`) : descend de `round-two` a `f118c17a`.
- `s37-theo` (`../recreo-s37-theo`, commit `6c7ac3fd`) : descend de `round-two` a `f118c17a`.
- `s37-sofia` (`../recreo-s37-sofia`, ouvert pour ce planning depuis `round-two` a `f118c17a`) :
  descend de `round-two`.

Les deux branches de preparation ne touchent aucun fichier en commun : `s37-camille` ne modifie
que `docs/backlog/backlog.md` ; `s37-theo` ne touche que des fichiers sous `adapters/`,
`application/`, `bootstrap/` et `squad/decisions/adr/` plus le fichier d'avis de faisabilite du
sprint. Fusionnees sans conflit sur `s37-sofia` :

- integration de `s37-theo` jusqu'a `6c7ac3fd` (avance rapide, `s37-sofia` etait encore a
  `round-two`).
- fusion de `s37-camille` (commit de fusion cree, `docs/backlog/backlog.md` seul modifie).

## 2. Porte technique de l'arbre fusionne

`bash outils/mvn-jdk21.sh -B clean verify` sur la racine du worktree fusionne, JDK **21.0.10**
rapporte par la machine virtuelle des tests : **BUILD SUCCESS**, 6 modules sur 6, 0 echec. Les
trois autres jobs de `.github/workflows/ci.yml`, relances par `sofia-sm` sur ce meme arbre fusionne
plutot que deduits du seul `mvn verify` (amendement de s31, cloture) :

- **`chemins`** : `verifier-chemins-test.sh` vert, puis `git ls-files | verifier-chemins.sh` sur le
  depot fusionne - aucun chemin de poste de travail ni nom de compte trouve.
- **`prefixes`** : `verifier-prefixes-test.sh` et `commit-atteignable-test.sh` verts, puis
  `git log --no-merges --format=%s round-two..HEAD | verifier-prefixes.sh` - tous les commits du
  sprint portent un prefixe d'agent connu.
- **`outils`** : les neuf juges purs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`,
  `outils/*-test.py`) executes un par un - "9 juge(s) pur(s) execute(s)", tous verts.

Les quatre jobs de la CI sont verts sur l'arbre fusionne, a l'ouverture du sprint.

## 3. Objectif gele

**Deux lots disjoints, de nature differente.**

- **`S36-ORDRE-NIVEAUX` est terminee** : `ADR-0095` (`Accepte`), le mecanisme
  (`application/src/main/java/com/recreo/application/catalog/OrdreDesNiveaux.java`, applique par
  `PartieService.niveauxDuCatalogue()`), deux garde-fous neufs avec leurs morsures
  (`ChaqueNiveauServiEstNommeDansLaSequenceTest`, `LOrdreDesNiveauxResteUneDonneeTest`), sont deja
  dans l'arbre fusionne. Ce lot n'a plus aucune action de developpement due.
- **`S37-1` (premier paquet mathematiques CE1, `MAT-CE1-03` sous-partie additions) est bloquee
  avant tout developpement**, sur deux reponses pedagogiques et non sur un obstacle technique.
  Aucune ligne de code, de test ou de contenu n'est engagee sur ce lot a ce planning.

Ce sprint ne rouvre ni CM2 (differe par `DEC-0089`), ni aucune autre matiere CE1 que les
mathematiques, ni les sous-parties de `MAT-CE1-03` autres que les additions.

## 4. Les stories, competence visee et avis de faisabilite de `theo-archi`

| Story / lot | Competence visee | Ce qu'il ferme ou ouvre | Verdict de `theo-archi` |
|---|---|---|---|
| `S36-ORDRE-NIVEAUX` | Transverse - navigation de l'en-tete, aucune competence de programme | Livree entiere : `ADR-0095`, mecanisme, deux garde-fous | Faisable et livree, rien a faire |
| `S37-1` | Mathematiques, CE1, `MAT-CE1-03` (sous-partie additions, "Nombres et calculs") | Rien de ferme a ce planning : bloquee avant developpement | Faisable sous condition non technique |

### Avis de faisabilite de `theo-archi`, integral (`squad/sprints/s37-premier-paquet-mathematiques-ce1-et-ordre-des-niveaux/avis-faisabilite.md`)

Le document complet vit deja dans ce meme dossier de sprint, fusionne depuis `s37-theo`. Ce qui
suit en reprend chaque conclusion substantielle, pour que ce planning porte l'avis sans obliger a
ouvrir un second fichier.

**Methode des mesures citees dans l'avis** : classe de production reelle
`AdditionsBorneesContentSource` (jamais une reimplementation), 200 000 tirages par graine
(protocole de `DEC-0007`), `dejaVus` vide a chaque tirage, perimetre de build `-pl domain -am`,
JDK 21.0.10 rapporte par la machine virtuelle des tests via `bash outils/mvn-jdk21.sh`. Les deux
classes de mesure ont ete ecrites, executees puis supprimees dans le worktree de `theo-archi` ;
`git status --porcelain` vide apres coup - aucune mesure jetable n'est commitee.

**`S36-ORDRE-NIVEAUX` - faisable, et livree.** Mecanisme retenu : une cle de tri
(`OrdreDesNiveaux.SEQUENCE`) appliquee par `PartieService.niveauxDuCatalogue()`. Le niveau reste
une `String` declaree par le paquet, `ADR-0006` n'est pas rouverte. Les deux autres formes citees
par la story (cle dans chaque descripteur de paquet, comparateur dans un adaptateur) sont ecartees
dans l'`ADR` : la premiere reproduit la dette 2 de `CLAUDE.md` (un meme ordinal recopie dans plus
de quarante fichiers editables independamment), la seconde contredit `ADR-0044`. Le garde-fou
`ChaqueNiveauServiEstNommeDansLaSequenceTest` refuse tout niveau servi absent de la sequence, sur
une population derivee du catalogue servi, et est verifie distinct du garde-fou existant
d'`ADR-0006` par assertion (deux contre-exemples soumis aux deux regles dans le meme test : la
regle neuve rougit, celle d'`ADR-0006` reste verte, la premiere laissant passer un `enum Niveau`
que la seconde n'aurait pas vu). Cout nomme : ajouter un niveau reste une donnee cote contenu, mais
rend le build rouge si la sequence ne le nomme pas - un futur `CE2` paiera une decision produit sur
sa place, ce qui est l'intention. `theo-archi` a livre ce lot entier plutot que de laisser un type
declare et non cable (dette 1 de `CLAUDE.md`) ; l'affectation du budget ainsi libere revient a
`sofia-sm` - voir section 5.

Porte technique de ce lot seul, mesuree par `theo-archi` sur son propre worktree avant fusion :
`mvn -B clean verify`, JDK 21.0.10, BUILD SUCCESS, cinq modules verts, 0 echec. Comptes de regles
ArchUnit, methode explicite : `static final ArchRule` 34 -> 36, `@ArchTest` 33 -> 35 (l'ecart d'une
unite entre les deux comptes preexiste, cause deja nommee par `ADR-0094` : une `ArchRule` du depot
n'est pas annotee).

**`S37-1` - faisable sous une condition qui n'est pas technique.** `camille-po` demande si la
constante `ACCEPTATION_OPERANDE_NUL_SUR = 3` (`domain`, `AdditionsBorneesContentSource`) doit etre
recalibree pour `sommeMaximale = 100`, ou reste valable telle quelle. Reponse de `theo-archi` :
**ni l'un ni l'autre**. Mesure, proportion de tirages a operande nul, constante inchangee : 12,87 %
a `sommeMaximale = 10` (reproduit les 12,92 % de l'amendement du 2026-08-05 a `DEC-0004`, ce qui
valide le protocole), 6,5 % a 20, **1,32 % a 100**. Plafond du levier mesure par mutation de la
constante (1, 2, 3) puis restauration de l'arbre : a `sommeMaximale = 100`, la constante a 1 (le
filtre neutralise) plafonne a **3,86 %**, deja sous la borne basse de la bande CP (5 %,
`DEC-0004`). Cause arithmetique : les couples valides croissent en O(n²), les couples a operande
nul en O(n) - la part brute passe de 30,8 % a 3,88 % entre 10 et 100. **A cette echelle, le
probleme que la constante corrige n'existe plus, et aucune de ses valeurs ne produit une proportion
de la bande CP.**

Trois options techniques en decoulent, chacune conditionnee a la cible que `nadia-prof` fixera :
cible autour de 1,3 % (ce que le code produit deja) -> zero ligne de Java, une seule cle YAML ;
cible ailleurs dans `]0 % ; 3,86 %]` -> la constante devient un parametre de paquet lu par
`AdditionsBorneesContentSourceFactory`, changement de donnee conforme a `ADR-0006` ; cible
au-dela de 3,86 % -> mecanisme neuf deconseille par `theo-archi` pour un seul paquet, qui prefere
alors un paquet statique de type `question`. Un defaut latent est signale independamment de la
reponse : la constante est un `private static final` partage par tous les paquets
`additions-bornees` ; `S37-1` en cree un second paquet et rend ce partage visible des que la cible
CE1 differe de celle deja produite.

Second point route dans le meme geste : la forme de la distribution des sommes a
`sommeMaximale = 100` (meme mesure, meme classe) - moyenne 67,1, mediane 71, 35,97 % des tirages
au-dessus de 80, 1,76 % seulement de la forme "un chiffre plus un chiffre", 42,23 % avec retenue.
`DEC-0007` point 2 avait fixe une table de repartition des sommes precisement parce que cette forme
est pedagogique, pour `sommeMaximale = 10` ; aucune table equivalente n'existe pour 100. Si le
generatif est retenu sans reponse a cette question, la forme du tirage n'est pas pilotable.

Trois points operationnels mesures, pour le developpeur qui engagera `S37-1` une fois les reponses
connues : un distracteur peut depasser 100 (1,32 % des cas, moins frequent qu'au CP ou c'est deja
servi et valide) ; le 4e critere d'acceptation de la story est vide si le generatif est retenu
(l'enonce est l'expression elle-meme) ; `CE1` est deja nomme dans la sequence d'`ADR-0095`, donc
`S37-1` ne paie rien sur ce point, `ADR-0078` continuant d'interdire un titre de jeu qui nomme son
niveau.

**Verdict** : faisable en l'etat, chemin le moins cher ouvert (`sommeMaximale: 100` dans un paquet
YAML, zero ligne de Java) et il le reste tant que la cible CE1 d'operande nul est celle que le code
produit deja. Rien a recalibrer techniquement. Ce qui bloque un engagement ferme, ce sont **deux
reponses de `nadia-prof`** : la cible d'operande nul a l'echelle 100, et la necessite ou non d'une
table de repartition des sommes comme au CP.

## 5. Affectations nominatives

- **`S36-ORDRE-NIVEAUX`** : **personne**. Le lot est deja livre entier dans l'arbre fusionne
  (`ADR-0095`, mecanisme, deux garde-fous, porte technique verte) ; il n'a besoin d'aucune action
  de developpement supplementaire a ce planning. Il reste a passer devant `yanis-qa` et
  `relecteur-pedagogique` a la review, comme tout lot livre - aucune competence de programme n'est
  en jeu, donc aucun avis de `nadia-prof` n'est du sur ce lot (story transverse, section 4).
- **`S37-1`** : **`nadia-prof`**, pour les deux reponses pedagogiques qui bloquent tout
  engagement de developpement :
  - la cible d'acceptation d'operande nul pour des additions bornees a `sommeMaximale = 100`
    (fourchette technique ouverte : `]0 % ; 3,86 %]`, `]0 % ; 100 %[` si un mecanisme neuf est
    accepte a la place du filtre actuel) ;
  - si une table de repartition des sommes, equivalente a celle que `DEC-0007` point 2 fixe pour
    l'echelle 10, est necessaire a l'echelle 100.

  Le troisieme point technique mineur souleve par `theo-archi` (la constante
  `ACCEPTATION_OPERANDE_NUL_SUR` partagee entre paquets `additions-bornees`, dont `S37-1` cree un
  second) n'est pas affecte a ce planning : il se tranche une fois la cible connue, entre
  `theo-archi` et le developpeur qui engagera `S37-1`, et n'engage aucune action avant cela.
  Aucun developpeur n'est affecte a `S37-1` a ce planning : la story n'est pas engageable tant que
  les deux reponses ci-dessus manquent (le 6e critere d'acceptation compare une mesure a une cible
  qui n'existe pas encore).

## 6. Verification `PAR-16` - point de controle poste/story

**Ce que `PAR-16` demande litteralement** (`squad/README.md`, section 4) : verifier, poste par
poste, la table de composition de la decision de strategie en vigueur. **Cette table n'existe pas
a s37**, meme constat qu'a s35 et s36 : la fenetre du Plan 2 (`DEC-0083`) a ete refermee par
`DEC-0087` sans qu'une strategie neuve de composition budgetaire ne soit tranchee au-dela.

**Ce que `PAR-16` verifie a la place, meme fallback qu'aux deux sprints precedents** : chaque
element retenu porte-t-il une affectation nominative ou un motif explicite de non-action ?

| Element | Affectation nominative | Issue |
|---|---|---|
| `S36-ORDRE-NIVEAUX` | aucune - lot deja livre | Non engage au sens developpement, aucune action requise (section 5) |
| `S37-1` - cible d'operande nul a l'echelle 100 | `nadia-prof` | Routee |
| `S37-1` - table de repartition des sommes a l'echelle 100 | `nadia-prof` | Routee |
| `S37-1` - partage de la constante entre paquets `additions-bornees` | `theo-archi` + developpeur non nomme, une fois la cible connue | Differee, aucune action requise avant la reponse pedagogique |

Aucun element n'est "non engage, budget libere" au sens de `PAR-16` : il n'y a pas de budget a
liberer hors d'une table de composition, qui n'existe pas a ce sprint. Chaque element porte soit
une affectation nominative, soit un motif explicite de non-action.

## 7. Ce que ce planning ne fait pas

- Il n'engage aucune ligne de code, de test ni de contenu sur `S37-1` : la story reste bloquee tant
  que `nadia-prof` n'a pas rendu ses deux reponses.
- Il ne fixe aucune cible pedagogique pour l'operande nul ni pour la repartition des sommes a
  l'echelle 100 : ce travail reste a `nadia-prof`.
- Il ne tranche pas le choix technique entre reutiliser `additions-bornees` recalibre ou authorer un
  paquet statique de type `question` pour `S37-1` : ce choix reste a `theo-archi`, une fois les
  reponses pedagogiques connues.
- Il ne rouvre ni CM2, ni aucune matiere CE1 autre que les mathematiques (`DEC-0089`).
- Il ne prononce aucun Done pedagogique : la review le fera, sur `S36-ORDRE-NIVEAUX` uniquement
  puisque `S37-1` n'a rien a auditer a ce stade.
