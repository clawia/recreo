# Planning - s49 - proposition : trois corrections differees de s48, inscription du chantier de
sourcing du corpus d'oeuvres du domaine public, ouverture du vivier francais CE1

- **Horodatage de debut** : 2026-10-03 14:59 (UTC+02:00).
- **Casquette** : `sofia-sm`, Scrum Master.
- **Slug** : `s49-corrections-s48-sourcing-oeuvres-et-ouverture-francais-ce1`, dossier ouvert par ce
  planning - aucun document prealable ne l'avait ouvert.
- **Nature de ce document : une proposition, pas un objectif gele.** Aucune `DEC` n'ouvre a ce jour
  le perimetre de s49. Je ne la fabrique pas a sa place - router le perimetre, la priorite et le
  chiffrage a `camille-po` est ma regle, pas une option (table de routage, `CLAUDE.md` racine). Ce
  document rassemble ce que le depot sait deja, propose un perimetre et route explicitement ce qui
  manque avant qu'un objectif puisse etre gele.

## 1. Lignee verifiee avant toute ecriture

Pas de worktree dedie : ce planning est un document unique, ecrit directement sur `round-two`.
Verifie avant d'ecrire : `git rev-parse --abbrev-ref HEAD` rend `round-two` ; `git status --short`
rend vide (arbre propre) ; `git log -1 --format=%H` rend `eee7022eec97bf1c52a9dbf00e67f7279618cff0`,
l'amendement de cloture de s48. Ce planning ne touche aucun fichier hors de
`squad/sprints/s49-corrections-s48-sourcing-oeuvres-et-ouverture-francais-ce1/planning.md`.

## 2. Etat de sortie de s48, verifie dans le depot

- **Couverture** : `squad/programme/MATRICE-couverture.md` porte **119/182 (65,4 %)**, `MAT-CE1-08`
  `couvert-valide` depuis la cloture de s48 (`dcae5ce0`).
- **Quatre reserves pedagogiques ouvertes** a la cloture du Done pedagogique de s48
  (`PRO-CE1-R1`/`PRO-CE1-R2`, l'item non leve d'`ANG-CP-R1` et d'`ANG-CP-R2` plus la reserve neuve
  `ANG-CP-R3`, la reserve sans identifiant de l'indice de l'item 7 d'`EMC-CP-14`) sont deja ecrites
  en **trois stories de correction differee**, groupees par paquet, par `camille-po`
  (`docs/backlog/backlog.md`, commit `dcae5ce0`), sans developpeur affecte.
- **Le chantier de sourcing d'un corpus d'oeuvres du domaine public** (`DEC-0077`, en souffrance
  depuis s29, bloquant neuf lignes en histoire des arts CM1 et en arts plastiques CP/CM1) a ete
  explicitement refuse pour s48 par `theo-archi`
  (`squad/rapports/avis-faisabilite-s48-sept-stories.md`, section 11), qui demande son inscription
  a s49 et s'engage a rendre l'`ADR` et sa regle a l'ouverture du sprint qui l'inscrit.
- **Une reference perimee** subsiste dans `DEC-0106` (section 4) : elle cite la borne de longueur du
  texte support comme "non posee" (`DEC-0104` section 1.1) alors qu'elle l'a ete la veille
  (`LON-CE1-3`, `LON-CE1-4`, `ARBITRAGE-s47-longueur-du-texte-support-au-ce1.md`). Signalee par
  `nadia-prof` elle-meme (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 0.1), routee a
  `camille-po`, **non corrigee a ce jour** (`review.md` s48, section 7).

## 3. Etat du vivier instruit, reverifie ce jour

### 3.1 Mathematiques CE1 - vivier instruit epuise

`squad/programme/MATRICE-couverture.md`, section "Mathematiques - CE1 (cycle 2)" : chaque ligne
deja instruite par `nadia-prof` (`MAT-CE1-01` a `MAT-CE1-18`, hors `MAT-CE1-09` deja `en cours` sous
limite textuelle ecrite d'avance) est `couvert-valide`. Aucune ligne neuve de mathematiques CE1
n'est instruite a `couvert-valide` atteignable a ce jour. Ouvrir une ligne neuve de mathematiques
CE1 a s49 supposerait un arbitrage prealable au prealable, qui n'existe pas.

### 3.2 Francais CE1 - vivier neuf, instruit a s48 (instance B)

`ARBITRAGE-PREALABLE-s48-francais-ce1.md` instruit les 26 lignes du referentiel
(`FRA-CE1-01` a `FRA-CE1-26`) : **26/26 ACCEPTE SOUS CONDITIONS**, **24 lignes plafonnees
`couvert-valide` atteignable** (toutes sauf `FRA-CE1-03` et `FRA-CE1-21`, plafonnees `en cours` -
respectivement reconnaissance immediate des mots frequents et ecriture sans modele d'un mot
invariable, non servies par ce produit). **Aucune de ces 26 lignes n'a encore bouge dans la
matrice** : l'instruction leve le risque que `DEC-0105` avait ferme autrement (vivier hors
mathematiques CE1 epuise), mais aucun paquet n'existe encore sur aucune de ces lignes - c'est un
vivier ouvert, pas un lot pret a developper. **Choisir la ou les lignes a construire en premier,
et leur tarif, est une priorisation produit** : je ne la fais pas a la place de `camille-po`. Je
nomme toutefois, a titre d'eclairage et sans la trancher, que les lignes de lecture et comprehension
(`FRA-CE1-01`, `FRA-CE1-02`, section 3 de l'arbitrage) portent le moins de conditions transverses
nouvelles a verifier (pas de `texteSupport`) et pourraient constituer un premier paquet simple a
chiffrer sur le modele d'une ligne neuve.

### 3.3 Le chantier de sourcing du corpus d'oeuvres du domaine public

`DEC-0077` (section 4, option B : corpus du domaine public) est tranchee depuis le 2026-09-05 ;
seule son execution manque. `theo-archi` route quatre questions a lui-meme avant de pouvoir
l'engager (source, preuve de domaine public par oeuvre - `ART-3`, format d'integration, verification
de chaque preuve) et demande un `ADR` avec sa regle. Son inscription au perimetre de s49 est demandee
explicitement par `theo-archi` ; je la transmets sans la trancher, `camille-po` decidant si elle
entre au chiffrage du sprint ou si elle ouvre un chantier parallele hors borne, sur le meme modele
que l'instance B d'instruction a s48.

## 4. Perimetre propose a `camille-po` (non gele)

Je propose, sans le trancher :

- **Les trois stories de correction differee de s48** (section 2), deja ecrites avec criteres
  d'acceptation complets, risque pedagogique deja borne par les fiches de `nadia-prof` - meme
  logique que les corrections differees de s47 reprises a s48.
- **L'inscription du chantier de sourcing du corpus d'oeuvres du domaine public** (section 3.3),
  a la demande explicite de `theo-archi`. Je propose qu'il ouvre par l'`ADR` et sa regle, avant tout
  travail de recherche de sources, pour que le sprint n'engage pas une charge non bornee.
- **L'ouverture du vivier francais CE1** (section 3.2) comme candidate de developpement neuf, sur le
  modele des sprints precedents (une ligne neuve par sprint depuis s37) : a `camille-po` de choisir
  la ou les lignes, leur tarif, et si l'audit pedagogique associe entre dans la meme instance que les
  trois corrections ou dans une instance separee.
- **La correction de la reference perimee dans `DEC-0106`** (section 2), signalee par `nadia-prof`
  et non encore faite, a traiter dans le document qui ouvre s49 ou dans un amendement dedie.

## 5. Repartition des taches proposee, contingente au gel du perimetre

| Agent | Tache |
|---|---|
| `camille-po` | Ecrit la `DEC` d'ouverture de s49 (perimetre, tarif des trois corrections, choix et tarif de la ou des lignes de francais CE1 retenues, decision sur l'inscription du chantier de sourcing au chiffrage ou hors chiffrage) ; corrige la reference perimee de `DEC-0106` section 4 |
| `theo-archi` | Rend un avis de faisabilite dedie a s49 sur les trois corrections (verifie qu'aucune ne touche de code de production au-dela du YAML et de son test de paquet, sur le meme modele que s48) et sur la ou les lignes de francais CE1 retenues une fois choisies ; rend l'`ADR` et sa regle ArchUnit pour le chantier de sourcing du corpus d'oeuvres du domaine public, comme il s'y est engage |
| `nadia-prof`, instance A (Done pedagogique) | Rejoue les trois paquets corriges et confirme la levee des quatre reserves ; audite le ou les paquets neufs de francais CE1 une fois livres, contre les conditions deja ecrites dans `ARBITRAGE-PREALABLE-s48-francais-ce1.md` |
| `marc-dev`, `lea-dev`, `sofia-sm` (casquette dev) | Affectation nominative finale une fois le perimetre gele ; proposition de repartition : un developpeur sur la ou les lignes neuves de francais CE1 (volume le plus consequent, conditions transverses neuves a respecter), les deux autres se partagent les trois corrections differees (volume faible, un ou deux fichiers YAML et leur test par correction) |
| `yanis-qa` | Rapport qualite de cloture (`mvn -B clean verify`, delta de tests explique, catalogue) |
| `relecteur-pedagogique` | Relit les textes neufs et corriges (orthographe, accentuation, conventions de redaction) avant la cloture |
| `ines-design` | Aucune tache due a ce jour sur les corrections et sur un paquet textuel de francais ; a solliciter si le chantier de sourcing d'oeuvres entre au perimetre et touche un rendu visuel |

## 6. Risques et points a trancher, par ordre de priorite

1. **Aucune `DEC` n'ouvre s49.** Tant qu'elle n'existe pas, aucun developpeur ne peut etre affecte
   nominativement avec certitude sur le perimetre final.
2. **Aucune ligne de francais CE1 n'est encore choisie ni chiffree.** Le vivier est ouvert (section
   3.2) mais le choix de la ou des premieres lignes a construire, et leur tarif, restent a
   `camille-po`.
3. **Le perimetre et le chiffrage du chantier de sourcing du corpus d'oeuvres du domaine public ne
   sont pas arretes.** `theo-archi` demande son inscription mais n'a pas encore pu ecrire l'`ADR`
   qu'il conditionne a l'ouverture du sprint qui l'inscrit - une boucle que seule `camille-po` peut
   ouvrir en inscrivant le chantier avant l'`ADR`, ou en le laissant hors perimetre une sixieme fois.
4. **Aucun avis de faisabilite dedie de `theo-archi` n'existe pour s49**, ni sur les trois
   corrections, ni sur la ou les lignes de francais CE1 une fois choisies.
5. **La reference perimee de `DEC-0106`** (section 2) reste a corriger par `camille-po` ; elle
   n'affecte aucun travail en cours mais pollue toute citation future de cette section.

## 7. Ce que ce planning ne fait pas

- Il ne gele aucun objectif : aucune `DEC` ne le fonde a ce jour.
- Il ne choisit ni ne chiffre aucune ligne de francais CE1 : le choix et le tarif appartiennent a
  `camille-po`.
- Il n'engage pas le chantier de sourcing du corpus d'oeuvres du domaine public : il transmet la
  demande de `theo-archi` sans la trancher.
- Il ne corrige pas la reference perimee de `DEC-0106` : routee a `camille-po`, qui l'avait deja
  recue en cloture de s48.
- Il n'affecte nominativement aucun developpeur de maniere definitive : l'affectation de la section
  5 est une proposition, pas une decision.
- Il ne cree ni n'amende aucun `ADR` ni aucune `DEC`.

## Decisions prises

Aucune. Ce planning est une proposition ; le perimetre de s49 reste a geler par une `DEC` de
`camille-po`, suivie d'un avis de faisabilite de `theo-archi`. Je reviens affecter nominativement
les developpeurs des que ces deux documents existent dans le depot.
