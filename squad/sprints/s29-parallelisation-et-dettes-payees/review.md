# Review de s29 - parallelisation et dettes payees

- **Horodatage de fin** : 2026-09-06 16:00
- **Duree effective** : depuis l'ouverture (2026-09-05 21:35), environ 18h25.
- **Increment mesure** : `round-two` a `ea1850e`.

---

## Porte technique - confirmee, avec un delta explique et non relaye

**Rapport qualite de `yanis-qa`** (`squad/sprints/s29-parallelisation-et-dettes-payees/rapport-qualite.md`,
mesure sur `8ef4fb2`, dernier commit avant sa propre mesure) : `bash outils/mvn-jdk21.sh -B clean
verify`, worktree dedie, reacteur complet, **BUILD SUCCESS**, **1262 tests, 0 echec, 0 erreur, 170
rapports XML**.

**Deux commits de contenu et de code sont arrives apres cette mesure**, avant la cloture : `marc-dev`
corrige `REV-2` (`6c75f28`, +2 tests, mesure a lui 1286/174) et `lea-dev` ajoute les quatre fichiers
de test de matiere de l'EMC que `yanis-qa` venait de trouver absents (`fa968ee`, +22 tests, mesure a
elle 1286/174, chiffre identique a celui de `marc-dev` sur les deux lots reunis). **1262 + 2 + 22 =
1286** : le delta est explique poste par poste, pas seulement constate.

**Ma propre execution sur l'arbre final** (`ea1850e`, apres le commit de cloture de `camille-po` qui
ne touche que `docs/backlog/backlog.md`, `DEC-0077`, `DEC-index.md` et `MATRICE-couverture.md` -
aucun fichier Java ni YAML de contenu) : `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet,
sans `-pl`. **BUILD SUCCESS**, les six modules en `SUCCESS`.

**Compte de tests, methode de l'amendement s11** : somme de l'attribut `tests="..."` de chaque
rapport XML sous `target/surefire-reports/` et `target/failsafe-reports/`, apres `clean`, sur le
reacteur complet : **174 fichiers XML, 1286 tests, 0 echec, 0 erreur.** Aucun ecart avec le chiffre
annonce par `marc-dev` et par `lea-dev`, confirme par moi-meme sur mon propre `clean`, sur l'arbre de
cloture.

**Mesure d'entree** (planning, `961565d`) : 1240 tests. **Sortie** : 1286. **Delta du sprint : +46**,
sur quatre lots de contenu neufs, un garde-fou de balayage (`ADR-0082`, 18 tests, deja compte dans
l'entree car livre en pre-sprint), un correctif structurel (`ADR-0083`) et deux paquets de test
ajoutes en reprise. Aucun ecart trouve entre les quatre mesures independantes de ce sprint
(`yanis-qa`, `marc-dev`, `lea-dev`, moi-meme).

**Verdict : porte technique verte**, confirmee independamment par quatre mesures et par la mienne sur
l'arbre de cloture.

## Garde-fous ajoutes - deux, aucun n'est une `ArchRule`, et les deux le disent elles-memes

Aucune `ArchRule` n'est ajoutee ce sprint : `ADR-0082` et `ADR-0083` l'ecrivent chacune
explicitement et donnent le motif technique (l'objet juge - un arbre de fichiers d'images pour l'un,
une concatenation qui compile en `invokedynamic` pour l'autre - n'est pas un type Java, donc hors de
ce qu'ArchUnit peut exprimer). Les deux sont des garde-fous de balayage sur la population reelle,
verifies verts dans mon execution ci-dessus :

- **`ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest`** (`ADR-0082`, 18
  tests, 13 morsures) : toute reproduction servie porte une preuve double (l'oeuvre libre, la
  photographie libre), le registre vit dans une donnee et jamais dans un commentaire.
- **`AucunTexteServiNEnchasseLaReponseDansUnePhraseTest`** (`ADR-0083`, module `bootstrap`) : aucun
  noeud de texte servi n'enchasse une valeur de contenu dans une phrase composee par la mecanique.
  Mesuree sur **2512 ecrans reels** (1256 items x 2 ecrans). J'ai lu son mecanisme de mise a
  l'epreuve plutot que de le relayer : le code de production remis dans son etat d'avant s29 rend
  **3150 griefs**, ce que `yanis-qa` a lui-meme reproduit dans son propre worktree en degradant puis
  en restaurant `PartieController.java` et `retour.html`.

`ADR-0073` est amendee (troisieme population d'images, `static/oeuvres/`) sans creer de regle neuve :
la propriete de sa clause 2 est etendue, pas revisee.

## Ce que la parallelisation a reellement achete

`nadia-prof` avait ecrit d'avance, avant que le sprint ne soit joue, que doubler sa borne
n'acheterait aucune ligne de plus : la composition a une seule instance (celle de son arbitrage
prealable, section 8.2) plafonnait deja a neuf lignes, et la variante A retenue a huit ferme
exactement les memes neuf. **Verifie : le plafond arithmetique de neuf lignes est atteint, ni plus ni
moins, et une composition en serie a 5,0 l'aurait atteint aussi.** La parallelisation n'a donc pas
achete de couverture supplementaire par rapport a un sprint en serie.

Ce qu'elle a achete, chiffre et non suppose :

- **`POS-R1` payee**, apres trois sprints en reserve sur une ligne deja `couvert-valide`.
- **Six reserves textuelles levees** (`COR-R2`, `EAU-R1`, `HG-R4`, `HG-R5`, `ANG-R1`, plus `POS-R1`
  elle-meme au sens large).
- **La dette des paquets a voix payee**, apres quatre sprints d'attente depuis s26 - elle n'aurait
  pas eu de place dans un sprint en serie a 5,0, dont la borne etait deja consommee par les quatre
  lots de contenu seuls.
- **Une passe transverse conduite pour la premiere fois comme tache payee et non comme sous-produit
  gratuit d'une accumulation en serie**, qui a trouve deux defauts qu'aucune autre methode de ce
  sprint n'aurait trouves : `REV-1` (la phrase composee par la mecanique autour de la bonne reponse,
  fautive sur 12 items de 4 paquets, traversant tous les sprints de contenu du depot depuis que la
  mecanique existe) et `CONS-1` (une consigne generique passee de 5 a 10 jeux au catalogue en un
  seul sprint, visible seulement sur trois etats successifs du catalogue entier).
- **1,0 de marge de rejeu nommee a l'ouverture**, qui n'avait jamais existe dans ce depot et qui a
  servi une premiere fois (0,25 tires par l'Instance B pour sa troisieme lecture de la division
  posee).

**Le fait de methode que `nadia-prof` avait annonce est verifie et non seulement suppose** : la
parallelisation n'a pas cause `REV-1` ni `CONS-1`, tous deux anterieurs a s29 - elle les aurait
laisses dormir un sprint de plus, la ou un mode serie les aurait rencontres en accumulant. C'est
l'inverse d'un gain de couverture : un gain de **detection** de defauts que le mode serie produit
comme sous-produit et que le mode parallele doit acheter explicitement, ce qui a ete fait
(`PAR-4`, 0,5).

## Corrections du `relecteur-pedagogique`

Commit `af1a624` :

1. **Une faute dans le texte servi a l'enfant**, sur environ 90 items neufs et une trentaine repris :
   une espace parasite avant un point dans le support d'un item d'histoire. L'espace insecable de
   `TYP-1` verifiee programmatiquement sur les seize fichiers de contenu touches, sans exception.
2. **Six references de fichier cassees au rendu** par un saut de ligne a l'interieur d'un bloc de
   code (`DEC-0075`, `DEC-0077`) : corrigees.
3. **Un commentaire factuellement faux** dans le paquet d'anglais, qui attribuait a `nadia-prof` un
   texte de remplacement qu'elle n'a pas fourni : corrige.
4. **Un chiffre perime signale et non corrige, a bon droit** : le cout du sprint etait ecrit 9,25 sur
   8,0 dans trois documents au moment de la relecture, alors que la reverification finale de
   `nadia-prof` avait deja porte ce chiffre a 9,50. Le relecteur l'a signale plutot que de le
   corriger lui-meme - une figure d'arbitrage n'est pas de son mandat - et `camille-po` l'a corrige
   dans le commit de cloture (`ea1850e`).

Aucune reference brisee : les vingt-six identifiants neufs du sprint sont tous definis une fois et
resolvent.

---

## Tableau des obstacles du sprint

| # | Obstacle | Categorie | Route vers | Resolution |
|---|---|---|---|---|
| 1 | Le perimetre annonce a l'ouverture (cinq lots a 1,0) valait en realite 6,0 a 7,0 sur la borne de `nadia-prof` | Pedagogie/produit, `nadia-prof` se corrige contre l'estimation initiale | `camille-po`/utilisatrice | Ferme : `DEC-0077` section 8.1, perimetre revise a quatre lots |
| 2 | Que faire d'un refus tardif dans un sprint deja a sa borne, question posee sans reponse assumee depuis la retro de s28 | Gestion, route par `camille-po` a l'ouverture | `nadia-prof`/utilisatrice | Ferme : `nadia-prof` audite chaque lot le jour de sa livraison plutot qu'en bloc a la cloture (`DEC-0077` section 8.6), reponse structurelle plutot que ponctuelle |
| 3 | Faire travailler deux instances de `nadia-prof` en parallele : quelle borne, quel plafond individuel | Gestion/pedagogie | Utilisatrice, sur chiffrage de `nadia-prof` (`PAR-0` a `PAR-8`) | Ferme : `DEC-0077` section 8.2, borne a 8,0, plafond individuel 5,0, variante A retenue |
| 4 | Quatre paquets d'EMC (deux CP, deux CM1) refuses au premier passage de l'Instance B - classement binaire hors sujet, charge de lecture, mensonge non distingue d'un axe different | Pedagogie | `lea-dev` (correction) | Ferme : trois corriges et reaudites `couvert-valide` le meme jour (`4dbf9b2`, `56f8967`), le quatrieme (`regles-de-vie`) valide des le premier passage |
| 5 | `POS-R1` rejouee, corrigee une premiere fois de facon insuffisante - le correctif remplace un defaut par un autre (rang constant, puis reponse toujours au milieu sans diviser) | Pedagogie, `nadia-prof` trouve son propre poteau non atteint | `marc-dev` (correction) | Ferme : seconde reprise (`130a538`), test qui mord la serie et non l'item, `POS-R1` levee, `POS-R2` ouverte |
| 6 | `REV-1` - la phrase composee par la mecanique autour de la bonne reponse, fautive sur 12 items de 4 paquets, trouvee par la passe transverse | Technique, cause dans l'adapter web | `theo-archi` (route par `nadia-prof`) | Ferme : `ADR-0083`, correctif structurel, meme jour (`8ef4fb2`) |
| 7 | `REV-2` - sur l'ecran de fin de partie, le verdict negatif dementait le dernier geste toujours juste de l'enfant, trouve par `nadia-prof` en reverifiant le correctif de `REV-1` | Pedagogie/technique | `theo-archi` pour l'implementation, texte prescrit par `nadia-prof` | Ferme : texte applique mot pour mot (`6c75f28`), `VALIDE SOUS RESERVE` |
| 8 | `REV-3` - l'ecran de fin de partie nomme une reponse sans porter sa question, cause structurelle | Technique | `theo-archi` | **Ouvert**, routee et non tranchee, story ecrite pour s30 |
| 9 | `CONS-1` - la consigne generique passee de 5 a 10 jeux en un sprint, contre la propre regle de `nadia-prof` posee a s28 | Produit, trouve par la passe transverse | `camille-po` | Ferme : `DEC-0078`, regle produit a partir de s30, correctif non bloquant planifie |
| 10 | Ecart de comptage sur les lignes fermees : les deux instances annoncaient huit, il y en a neuf (`EMC-CP-06` omise du total de l'Instance A) | Gouvernance, couture entre deux instances | `camille-po` (trouve en recomptant) | Ferme : `DEC-0077` section 9, matrice corrigee a 52/138 |
| 11 | `yanis-qa` trouve les quatre paquets d'EMC livres sans aucun fichier de test de matiere, ni a la livraison ni a la reprise apres refus - le meme defaut qu'a s28, sur quatre paquets au lieu d'un | Qualite | `sofia-sm` pour `lea-dev` | Ferme : `fa968ee`, quatre fichiers, 22 tests, morsure verifiee par mutation |
| 12 | Deux declarations inexactes dans des commentaires YAML commites (`"dix distracteurs reecrits"` quand huit le sont ; `"tous les enonces reecrits"` quand neuf sur onze le sont) | Qualite, redaction factuelle | `sofia-sm` pour `lea-dev` | Ferme : `fa968ee`, chiffres exacts, contenu intouche |
| 13 | `docs/backlog/backlog.md` presentait encore `REV-1` comme un travail restant pour s30 alors que le correctif etait deja livre | Gouvernance, artefact de sequence | `sofia-sm`/`camille-po` | Ferme : `ea1850e`, `REV-1` et `REV-2` sortis du backlog, `REV-3` ecrite comme story neuve |
| 14 | Cout du sprint perime (9,25) dans trois documents apres que la reverification finale de `nadia-prof` l'a porte a 9,50 | Redaction/gouvernance, trouve par le relecteur | `camille-po` | Ferme : `ea1850e`, corrige dans les trois documents, historique lisible |
| 15 | Trois reserves de s30 manquaient a la phrase de synthese des affectations, chacune ayant pourtant son entree correcte | Gouvernance | `camille-po` | Ferme : `ea1850e`, classees contre l'entree de chaque reserve |
| 16 | `DEC-0075` (corpus de reproductions d'oeuvres, sourcing et forme non instruits) | Produit/technique | `theo-archi` | Ferme, hors-sprint : `ADR-0082`, source (renonciation `CC0-1.0` enregistree), pivot 1900, registre dedie |
| 17 | Registre des 147 identifiants de conditions sans forme (`PAR-5`) | Gouvernance/technique | `theo-archi` (route par `nadia-prof` et `camille-po`) | **Ouvert**, aucune story de s29 ne le construit, verifie seulement qu'aucune collision n'a ete creee |
| 18 | Garde-fou mecanique de coherence entre un bilan de matiere et le compte reel de ses lignes | Technique | `theo-archi` | **Ouvert**, pose comme question, non tranche |
| 19 | Garde-fou possible pour `POS-2` sur la division posee, calculable mais non encode | Technique | `theo-archi` | **Ouvert**, signale, non requis pour lever `POS-R1` ce sprint |
| 20 | Trois incidents d'orchestration de `sofia-sm` : mesures concurrentes sur un worktree partage (rouges intermittents), une consigne de mesure au module qui a laisse passer un rouge que seul le reacteur complet voit, et du travail d'un agent embarque deux fois dans le commit d'un autre | Capacite/gestion | `sofia-sm` (moi-meme) | **Reconnu en retro**, aucune ligne ni verdict affecte - `yanis-qa` avait deja adopte le worktree dedie depuis s28, generalisation a traiter au planning de s30 |

---

## Avis de `nadia-prof` et Done pedagogique

**Deux instances disjointes, chacune auditant ses propres lots, aucune ne relisant l'autre**
(`round-two` a `ba3f603` pour l'Instance A, `5c35f6c` puis `56f8967` pour l'Instance B, plus la
reverification de `REV-1` sur `8ef4fb2`) :

| Ligne | Jeu | Instance | Mouvement |
|---|---|---|---|
| `QLM-CP-02` | `bandit-manchot-cycle-de-vie-cp` | A | `non couvert` -> **`couvert-valide`** |
| `QLM-CP-07` | `bandit-manchot-objet-technique-cp` | A | `non couvert` -> **`couvert-valide`** |
| `HG-CM1-06` | `bandit-manchot-histoire-geographie-cm1` | A | `en cours` -> **`couvert-valide`** |
| `HG-CM1-07` | idem | A | `en cours` -> **`couvert-valide`** |
| `HG-CM1-14` | idem | A | `en cours` -> **`couvert-valide`**, sur sa part servie (document textuel seul) |
| `EMC-CP-06` | `bandit-manchot-regles-de-vie-cp` | B, premier passage | `non couvert` -> **`couvert-valide`**, sous reserve non bloquante |
| `EMC-CP-02` | `bandit-manchot-permis-interdit-cp` | B, reaudit apres refus | `non couvert` -> **`couvert-valide`** |
| `EMC-CM1-11` | `bandit-manchot-fait-opinion-cm1` | B, reaudit apres refus | `non couvert` -> **`couvert-valide`** |
| `EMC-CM1-07` | `bandit-manchot-droit-devoir-cm1` | B, reaudit apres refus | `non couvert` -> **`couvert-valide`** |

**Neuf lignes ferment**, le plafond arithmetique pose avant le sprint (`DEC-0077` section 8.4).
**Quatre paquets sur cinq evalues au premier passage ont ete refuses** (`EMC-CP-02`, `EMC-CM1-11`,
`EMC-CM1-07`, et le rejeu de `POS-R1`) - seul `EMC-CP-06` a ete valide d'emblee. Les quatre ont ete
repris et tous valides au reaudit, le meme jour pour les trois premiers, un jour plus tard pour la
division.

**Prix reel du sprint : 9,50 sur une borne de 8,0** (Instance A 4,25/5,0, Instance B 5,25/5,0),
recompte independamment par `camille-po` sur le releve de verdicts de chacune (`DEC-0077`, section
9.4). **La marge de rejeu, nommee a 1,0 a l'ouverture et jamais utilisee dans ce depot auparavant,
tombe a 0,75** apres le seul tirage de l'Instance B (0,25, sur sa recherche de la troisieme lecture
de la division posee). Le supplement de 0,25 de l'Instance A (reverification de `REV-1`) tient dans
son plafond individuel et ne touche pas la marge du sprint.

**Mentions exigees d'avance, reprises mot pour mot dans la matrice** : `HG-CM1-14` (*"document
textuel seul ; carte et image restent non couvertes"*), `EMC-CP-06` (*"connaissance de la regle et
de ce a quoi elle sert seule ; sa mise en oeuvre en situation n'est pas servie"*), `EMC-CM1-07` et
`EMC-CM1-11` (mention de couverture partielle appliquee par defaut faute de verdict qui la leve
explicitement).

**Quinze reserves ouvertes pour s30**, aucune perdue : `CYV-R1`, `OBJ-R1`, `OBJ-R2`, `HG-R6`,
`HG-R7`, `REG-R1`, `PER-R1`, `FOP-R1`, `FOP-R2`, `FOP-R3`, `DRO-R1`, `DRO-R2`, `DRO-R3`, `POS-R2`, et
`VIV-R2` (ouverte depuis s28, desormais executable - `nadia-prof` a tranche sa propre piste et
prescrit trois enonces de remplacement mot pour mot).

**Conditions restant sans garde executable**, sur les neuf lignes fermees ce sprint : la meme
famille que celle nommee a s28 - aucun test du depot ne sait qu'un enonce scolaire est vrai, `FER-1`
et `FER-3` restent des jugements non automatisables, la plausibilite d'un distracteur n'a aucun
garde-fou, et `VOI-3`/`VOI-5`/`VOI-8` ne sont gardees par personne, pas meme par `nadia-prof` -
ce que la dette payee ce sprint repare, c'est que le produit cessait de demander par ecrit le
contraire de ce qu'il demande a l'adulte.

### Done pedagogique

**Prononce.** Neuf lignes ferment (`non couvert` ou `en cours` -> `couvert-valide`). Quatre paquets
sur cinq refuses au premier passage, tous corriges et valides au reaudit dans le sprint, avec leurs
motifs de refus lisibles entiers dans les fiches de conformite. `REV-1` corrige et rejoue,
`VALIDE SOUS RESERVE` ; `REV-2` refusee puis levee sur un texte prescrit ; `REV-3` routee et non
tranchee.

### Done technique

**Prononce.** `mvn -B clean verify` vert sur le reacteur complet, `ea1850e`, **1286 tests, 0 echec,
174 rapports XML** - confirme independamment par `yanis-qa` (1262 sur `8ef4fb2`, avant les deux
derniers lots), par `marc-dev` et `lea-dev` (1286 chacun, sur leurs commits respectifs) et par ma
propre execution sur l'arbre de cloture. Aucune `ArchRule` ajoutee ; deux garde-fous de balayage
neufs (`ADR-0082`, `ADR-0083`), tous deux verifies verts.
