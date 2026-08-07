# squad - le journal operationnel de recreo

Ce dossier est le journal de l'equipe qui construit `recreo`. Il est un **livrable de premiere
classe**, au meme titre que le code Java.

## Le principe qui fonde tout

**Le depot Git est la seule memoire partagee de la squad.**

Ce qui n'est pas ecrit ici n'existe pas :

- une ceremonie sans fichier dans `squad/sprints/` n'a pas eu lieu ;
- une decision qui n'est pas redigee en `ADR` ou en `DEC` n'a pas ete prise, meme si tout le
  monde l'applique ;
- un obstacle n'est clos que quand le fichier qui le tranche **existe physiquement** ;
- un test qui n'est pas ecrit ne couvre rien.

Les agents n'ont pas de memoire entre deux invocations. Ce fichier, et ceux qui l'entourent, sont
cette memoire.

## 1. L'equipe

Neuf agents, decrits dans [`EQUIPE.md`](EQUIPE.md), definis dans `.claude/agents/`.

Trois developpeurs codent : Marc, Lea, et Sofia sous sa casquette dev. Deux agents detiennent une
autorite exclusive : `theo-archi` sur l'architecture, `nadia-prof` sur la pedagogie. Un agent
propose sans trancher : `ines-design`, sur le design d'interface et l'accessibilite visuelle, sous
le veto pedagogique de `nadia-prof`.

## 2. Conventions de nommage

La regle generale : **le tri alphabetique d'un dossier doit donner le tri chronologique ou
logique.**

| Type | Chemin | Proprietaire |
|---|---|---|
| Sprint | `squad/sprints/sNN-<slug>/` | `sofia-sm` |
| Ceremonie | `<sprint>/planning.md`, `<sprint>/review.md`, `<sprint>/retro.md` | `sofia-sm` |
| Synthese de sprint | `<sprint>/RAPPORT.md` | `sofia-sm` |
| Consommation de tokens | `<sprint>/RAPPORT-tokens.md` | `sofia-sm` (genere) |
| ADR technique | `squad/decisions/adr/ADR-XXXX-<slug>.md` | `theo-archi` |
| Decision produit | `squad/decisions/produit/DEC-XXXX-<slug>.md` | `camille-po` |
| Fiche de conformite | `squad/conformite/FICHE-<jeu>.md` | `camille-po` ouvre, `nadia-prof` valide |
| Referentiel de competences | `squad/programme/<matiere>/competences-<niveau>.md` | repris, en lecture |
| Matrice de couverture | `squad/programme/MATRICE-couverture.md` | `camille-po` |
| Journal des tokens | `squad/rapports/JOURNAL-tokens.md` | `sofia-sm` (genere) |

Numerotation des `ADR` et des `DEC` : quatre chiffres, **jamais reutilisee**, meme quand une
decision est rejetee ou remplacee. Ca garde l'historique honnete. `ADR-index.md` et
`DEC-index.md` sont des tableaux recapitulatifs, une ligne par decision, tenus a jour par leur
proprietaire a chaque creation et a chaque changement de statut.

**Un fichier par ceremonie. On n'ajoute jamais a la suite d'un fichier existant** : diffs Git
propres, pas d'ecriture concurrente, tri par date automatique.

## 3. Pilotage par increment, pas par duree

Il n'y a **pas de timebox**. Un sprint est un **increment livrable**, quelle que soit sa duree
reelle.

`planning.md` porte l'horodatage de **debut**, `review.md` porte l'horodatage de **fin** et la
duree effective. C'est cette fenetre temporelle qui sert aussi a attribuer la consommation de
tokens au sprint (voir section 6).

## 4. Les ceremonies

Trois produisent un fichier, une non.

| Ceremonie | Fichier | Ce qu'elle contient obligatoirement |
|---|---|---|
| Planning | `planning.md` | Horodatage de debut, objectif gele, stories avec competence visee, affectations nominatives, avis de faisabilite de Theo |
| Daily | **aucun** | Synchronisation de travail. Sofia collecte, classe et route ; elle n'ecrit pas de fichier |
| Review | `review.md` | Horodatage de fin, duree, rapport qualite de Yanis, confirmation ArchUnit de Theo, corrections du relecteur, tableau des obstacles, avis de Nadia et Done pedagogique |
| Retro | `retro.md` | Ce qui a marche, ce qui a coince, amendements a ce README |

**Pourquoi le daily n'est pas journalise.** Sur la version precedente de `recreo`, le daily etait
une ceremonie documentee dont **aucun fichier n'a jamais ete ecrit en 25 sprints**. Plutot que de
maintenir une regle que personne ne suit, on acte la pratique reelle.

Consequence a tenir : le **tableau des obstacles** ne disparait pas avec le daily. Il remonte
dans `review.md` et dans `RAPPORT.md`. Sans lui, la chaine "obstacle route vers un agent,
decision ecrite, obstacle clos" serait perdue.

Regle de cloture : un fichier de ceremonie n'est complet que s'il contient une section
`## Decisions prises` **non vide**, ou l'enonce explicite qu'aucune decision n'a ete prise.

**Methode de comptage obligatoire (amendement s06, retro de s05).** Tout chiffre de comptage cite
dans `rapport-qualite.md` ou `confirmation-archunit.md` (nombre de tests, nombre de regles ArchUnit,
nombre d'items de contenu) porte sa methode dans le meme paragraphe que le chiffre : perimetre
parcouru, source de la liste, ce qui est explicitement exclu. Un chiffre sans methode ne se compare
a rien, meme quand il est juste - en s05, `theo-archi` a du corriger un meme chiffre trois fois
("29" puis "25" puis "27" regles ArchUnit) faute de l'avoir ecrite la premiere fois. **Le comptage
se fait apres un `clean`** : un rapport laisse par une execution mutee anterieure produit un total
juste avec des echecs faux, et `theo-archi` s'y est lui-meme repris une fois en s06 en le
verifiant.

## 5. La synthese de sprint - `RAPPORT.md`

C'est le document qu'on lit en premier pour savoir ce qui s'est passe. Ecrit par Sofia a la
cloture, il agrege :

- l'increment livre, en une phrase comprehensible sans contexte ;
- **la liste complete des decisions** : chaque `ADR` et chaque `DEC` cree ou modifie, avec
  numero, titre et statut ;
- l'etat des deux Done ;
- les obstacles resolus et ceux encore ouverts ;
- le tableau de consommation de tokens.

## 6. Le rapport de consommation de tokens

`outils/rapport-tokens.py` agrege la consommation **par agent et par sprint** a partir des
transcriptions que Claude Code ecrit sous
`~/.claude/projects/<chemin du depot encode>/`. Chaque sous-agent y laisse un fichier
`subagents/agent-<id>.jsonl` et un `agent-<id>.meta.json` qui porte son `agentType`.

Sofia le lance a chaque review :

```
python outils/rapport-tokens.py --sprint sNN
```

Si `python` ne resout pas un interpreteur 3.x utilisable sur le poste, appeler l'interpreteur par
son chemin complet - un chemin local ne s'ecrit jamais dans un fichier du depot.

Il ecrit `squad/sprints/sNN-<slug>/RAPPORT-tokens.md` et met a jour
`squad/rapports/JOURNAL-tokens.md`.

L'attribution a un sprint se fait par **fenetre temporelle** : l'intervalle entre l'horodatage de
debut du `planning.md` et l'horodatage de fin du `review.md`. Ce qui tombe hors de toute fenetre
est reporte sur une ligne `hors sprint`, jamais reparti arbitrairement.

Les quatre compteurs (`input`, `output`, `cache cree`, `cache lu`) sont toujours affiches
**separement**. Les additionner en un chiffre unique donnerait un classement faux : le cache lu
depasse couramment l'output de deux ordres de grandeur. Le rapport ne convertit rien en euros :
ce sont des compteurs de tokens.

## 7. Le double Done

**Done technique** : `mvn verify` vert - compilation, tests unitaires, tests d'integration Spring
Boot, ArchUnit, et integration H2 une fois `adapter-persistence` cree. C'est **la CI qui juge**,
pas un agent. Yanis et Theo en sont les garants : ils rendent le jugement fiable et le rapportent
honnetement.

**Done pedagogique** : prononce par **`nadia-prof`**, et par elle seule, avec un motif ecrit dans
`squad/conformite/FICHE-<jeu>.md`. Trois verdicts : `VALIDE`, `VALIDE SOUS RESERVE`, `REFUSE`.

Les deux sont independants. Un increment techniquement irreprochable peut etre refuse
pedagogiquement, et c'est un fonctionnement normal, pas un incident. Un refus renvoie l'increment
a Camille, qui en fait du backlog ; il ne bloque pas le sprint suivant.

**Gouvernance d'une story absorbee en cours de route (amendement s06, retro de s05).** Une story ou
une decision de perimetre qu'un sprint absorbe en cours de route depuis un autre sprint -
deplacement d'une story planifiee ailleurs, execution anticipee d'un chantier prevu au sprint
suivant - est actee par un amendement ecrit a la decision d'origine, dans le meme geste que sa
fusion sur `round-two`, jamais seulement constatee a la review du sprint qui suit. En s05,
`DEC-0012` placait explicitement le rhabillage du gabarit au sprint 6 ; il a ete livre et audite
dans s05 sans qu'aucun amendement ecrit ne le dise au moment des faits, et l'anomalie n'a ete
constatee qu'a la cloture du sprint suivant.

## 8. Prefixes de commit par agent

Format : `[TAG] <resume>`.

| Tag | Agent |
|---|---|
| `[CAM]` | Camille, Product Owner |
| `[SOF]` | Sofia, Scrum Master et dev |
| `[THEO]` | Theo, architecte |
| `[MARC]` | Marc, dev |
| `[LEA]` | Lea, dev |
| `[YAN]` | Yanis, QA |
| `[REL]` | Relecteur pedagogique |
| `[NAD]` | Nadia, professeure des ecoles |
| `[INES]` | Ines, designer d'interface |

Ca permet d'isoler la production d'un agent : `git log --grep='^\[NAD\]'`.

Ce n'est pas une convention declarative : la CI **rejette** un commit dont le message ne commence
pas par un tag connu (voir `.github/workflows/ci.yml`). Sur la version precedente, ce garde-fou
etait annonce dans la documentation et n'existait pas dans le workflow.

## 9. Derouler un sprint

**1. Planning.** Camille ecrit ou affine les stories et met a jour la matrice. Theo donne un avis
de faisabilite ecrit sur chacune et ouvre un `ADR` si la story en appelle un. Sofia gele
l'objectif, affecte nominativement, ecrit `planning.md` avec l'horodatage de debut. **Avant tout
commit dans un worktree neuf, chaque agent verifie que sa branche descend de `round-two`**
(amendement s06, retro de s05) : `outils/verifier-lignee.sh`, plutot qu'une commande recopiee en
prose dans chaque `planning.md`. Tant que ce script n'est pas encore sur le disque, la verification
reste tenue a la main (`git rev-parse --abbrev-ref HEAD` puis
`git merge-base --is-ancestor round-two HEAD`).

**2. Developpement.** Marc, Lea et Sofia codent, tests dans le meme commit. Yanis traduit les
criteres d'acceptation en tests et fait tourner la suite. Tout obstacle va a Sofia, qui classe,
route, attend la decision **ecrite**, debloque.

**3. Daily.** Sofia collecte et route. Pas de fichier.

**4. Porte technique.** `mvn verify` vert. Yanis produit le rapport qualite, Theo confirme
ArchUnit avec le resultat de la commande. **Un garde-fou cite comme fermant un refus pedagogique ou
une dette nommee n'est declare complet, dans le `review.md` qui le cite, qu'une fois la preuve de
morsure decrite explicitement** (amendement s06, retro de s05) - mutation reelle du code, relecture
adverse de l'inegalite qu'il verifie, ou tout autre moyen nomme. "Il est vert" ne suffit jamais
comme preuve de morsure, y compris quand l'auteur lui-meme le croit.

**5. Relecture.** Le `relecteur-pedagogique` passe sur les livrables non executables. Il corrige
ce qui est sans ambiguite, signale le reste.

**6. Porte pedagogique.** Nadia joue l'increment, l'audite contre le referentiel, ecrit son
verdict dans la fiche de conformite. Si c'est `VALIDE`, Camille passe la ligne de matrice a
`couvert-valide`.

**7. Review.** Sofia ecrit `review.md` : horodatage de fin, duree, rapport de Yanis, confirmation
de Theo, corrections du relecteur, tableau des obstacles, verdict de Nadia.

**8. Cloture.** Sofia lance le rapport de tokens et ecrit `RAPPORT.md`.

**9. Retro.** Sofia anime, ecrit `retro.md`, et amende ce README si le process doit evoluer.

**Outils permanents (amendement s06, retro de s05).** Deux scripts testes selon la doctrine
d'`ADR-0009` outillent les etapes 1 et 4 ci-dessus :

- `outils/verifier-lignee.sh` (etape 1) : verifie qu'un worktree descend de `round-two`, code de
  sortie non nul et message explicite nommant la branche fautive sinon, sans jamais imprimer de
  chemin absolu de poste.
- `outils/verifier-jdk.sh` (etape 4) : separe un diagnostic libre (sortie d'erreur, peut nommer un
  chemin de poste) d'un constat citable (sortie standard, jamais un chemin) sur le JDK utilise pour
  compiler. C'est ce second bloc qui se colle tel quel dans `rapport-qualite.md` ou
  `confirmation-archunit.md`, a la place d'une description en prose de la verification.

Ce paragraphe acte la regle ; ni l'un ni l'autre script n'existe encore sur le disque a l'ouverture
du sprint 6, les stories qui les livrent le font. Jusqu'a leur fusion, les etapes 1 et 4 restent
tenues par la commande recopiee a la main et par la prose du rapport qualite. Le besoin qui motive
`outils/verifier-jdk.sh` est reel et pas seulement une question de forme : verifie le 2026-08-07, un
JDK 21.0.10 est installe et compile sur un poste ou le fichier de toolchains Maven declare bien
trois JDK dont un 21 sous un nom que Maven ne lit pas - quatre agents avaient conclu a tort en s05
qu'aucun JDK 21 n'y etait disponible, une erreur de fond et non seulement de methode.

## 10. Frontieres entre agents

- Camille ne decide pas la technique. Theo ne decide pas le produit ni la pedagogie.
- Marc, Lea et Sofia n'inventent aucune regle d'architecture : ils remontent a Sofia, qui route
  vers Theo, et ils attendent la decision ecrite.
- Yanis n'assouplit jamais le Done technique pour tenir un calendrier.
- Le relecteur ne modifie pas la logique du code ; il ne juge pas ce que lit un enfant.
- Ines propose le design d'interface et l'accessibilite visuelle ; elle ne tranche ni
  l'architecture ni le produit, ne juge pas le niveau scolaire d'un contenu, et ne reecrit pas la
  logique metier. Son design ne se valide jamais contre l'avis de Nadia.
- **Aucun agent, hors Nadia, ne prononce le Done pedagogique.**

## 11. Arborescence

```
squad/
  README.md               ce fichier - le process
  EQUIPE.md               les 8 agents, miroir humain de .claude/agents/
  TEMPLATES.md            gabarits vierges de tous les artefacts
  sprints/
    sNN-<slug>/
      planning.md         sofia-sm
      review.md           sofia-sm
      retro.md            sofia-sm
      RAPPORT.md          sofia-sm
      RAPPORT-tokens.md   genere
  decisions/
    adr/                  theo-archi + ADR-index.md
    produit/              camille-po + DEC-index.md
  conformite/             FICHE-<jeu>.md - camille-po ouvre, nadia-prof valide
  programme/
    MATRICE-couverture.md camille-po
    mathematiques/        referentiels repris de la version precedente
    francais/             referentiels repris de la version precedente
  rapports/
    JOURNAL-tokens.md     cumul de la consommation, tous sprints
```
