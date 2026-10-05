# [Sprint s51] Planning - 2026-10-05

- **Horodatage de debut** : 2026-10-05 20:51 (UTC+02:00), lu par `date` au moment de l'ecriture.
- **Anime par** : sofia-sm (casquette Scrum Master ; aucune story de developpement portee par moi a s51)
- **Participants** : camille-po (`DEC-0110`, amendee section 14), theo-archi (avis de faisabilite,
  `squad/sprints/s51-trois-instances-francais-sciences-maths/avis-faisabilite.md`), nadia-prof
  (arbitrage prealable `squad/conformite/ARBITRAGE-PREALABLE-s51-borne-a-trois-instances.md`,
  instances A et B), marc-dev, lea-dev, yanis-qa, relecteur-pedagogique
- **Slug** : `s51-trois-instances-francais-sciences-maths`. Le dossier existait avant ce planning (il
  portait l'avis de `theo-archi`) ; le slug garde le nom d'ouverture a trois instances, que `DEC-0110`
  section 14 a ramenee a deux. Je ne renomme pas un dossier deja reference par ses fichiers.
- **Lignee verifiee avant ecriture** : `git rev-parse --abbrev-ref HEAD` rend `round-two`, `git log -1
  --format=%h` rend `f68458de` (cloture de s50), `git merge-base --is-ancestor round-two HEAD` reussit.
  Le checkout principal porte des fichiers non commites d'autres agents (`DEC-0110`, `ADR-0104`, avis de
  `theo-archi`, arbitrage de `nadia-prof`, ce dossier) ; ce planning n'ecrit que son propre fichier et
  l'amendement de la retro de s50 (section 6).

## Objectif du sprint

Gele, repris de `DEC-0110` section 14.3 : **porter a `couvert-valide` jusqu'a six lignes**
(`FRA-CE1-22`, `FRA-CE1-20`, `FRA-CE1-23` ; `SCI-CM1-10`, `SCI-CM1-15`, `SCI-CM1-18`) **et lever
`HOM-CE1-R1`**, sans qu'aucune instance de `nadia-prof` depasse 5,0. Total : **A 3,5 + B 4,5 = 8,0.**
Mesure : `MATRICE-couverture.md`, 123/182 au depart, **129/182 au mieux (70,9 %)**. Ce n'est pas une
promesse : un Done pedagogique refuse reste un refus. `MAT-CE1-02` ne passe pas a `en cours` a s51.

**Provenance.** Composition arretee par l'utilisatrice sur la question Q1 (acceptation du refus de
`nadia-prof`), enonce recu de MASTER, sans source dans le depot, le 2026-10-05, cite par `DEC-0110`
section 14.1 : *"Q1"*. Cet enonce ne fonde que ce que la squad fait du refus. Le motif du refus est celui de
`nadia-prof` (arbitrage prealable, sections 2.3, 2.4, 3 a 5), et c'est lui qui fait foi.

**Instance C (mathematiques CE1) : non engagee, report a s52.** `MAT-CE1-17`, `MAT-CE1-06` et
`MAT-CE1-02` (moitie textuelle) sont *"non engage, budget libere"* (`PAR-16`), 3,0 libere, destination
s52. Aucune branche, aucun worktree, aucun scratchpad, aucun developpeur pour C. Les trois stories
`S51-MAT-*` restent ecrites au backlog.

## Stories retenues

| Story | Titre | Competence programme visee | Decision liee | Affectee a |
|---|---|---|---|---|
| `S51-FRA-GRAPHIE` | Choisir la graphie d'un son selon la lettre qui suit (1,0) | francais / CE1 / `FRA-CE1-22` | `DEC-0110` s.3 ; **sous condition suspensive (section "Blocage a router")** | dev : lea-dev ; audit : nadia-prof instance A |
| `S51-FRA-TEMPS` | Situer une phrase dans le temps (1,0) | francais / CE1 / `FRA-CE1-20` | `DEC-0110` s.3 | dev : lea-dev ; audit : nadia-prof instance A |
| `S51-FRA-ALPHABETIQUE` | Ranger des mots dans l'ordre alphabetique (1,0) | francais / CE1 / `FRA-CE1-23` | `DEC-0110` s.3 | dev : lea-dev ; audit : nadia-prof instance A |
| Correction `HOM-CE1-R1` (backlog, section "Backlog - sprints suivants") | Prenoms d'origines variees dans "Les bons mots" (0,5, correction, pas un lot) | francais / CE1 / `FRA-CE1-16`, ligne deja `couvert-valide`, non rouverte | `DEC-0110` s.3 ; `PAR-10` | dev : marc-dev ; audit : nadia-prof instance A |
| `S51-SCI-ACTIVITE-HUMAINE-CM1` | Effet de l'activite humaine sur l'environnement, geste de preservation (1,5) | sciences et technologie / CM1 / `SCI-CM1-18` | `DEC-0110` s.4 | dev : marc-dev ; audit : nadia-prof instance B |
| `S51-SCI-CORPS-CM1` | Alimentation, activite physique, sommeil, hygiene et fonctionnement du corps (1,5) | sciences et technologie / CM1 / `SCI-CM1-10` | `DEC-0110` s.4 | dev : marc-dev ; audit : nadia-prof instance B |
| `S51-SCI-TERRE-CM1` | La Terre dans le systeme solaire, jour, nuit, saisons (1,5) | sciences et technologie / CM1 / `SCI-CM1-15` | `DEC-0110` s.4 | dev : marc-dev ; audit : nadia-prof instance B |
| `S51-MAT-SYMETRIE`, `S51-MAT-MULTIPLICATION`, `S51-MAT-COMPARER-RANGER` | Instance C | mathematiques / CE1 / `MAT-CE1-17`, `-06`, `-02` (moitie textuelle) | `DEC-0110` s.14.5 | **non engagees a s51, reportees a s52** (`PAR-16`) |

**Tarifs** (`DEC-0110` sections 2 et 14.3, non rediscutes ici) : A = 1,0 + 1,0 + 1,0 + 0,5 = **3,5 / 5,0**,
marge 1,5 dont rejeu 0,5, trois lots. B = 1,5 x 3 = **4,5 / 5,0**, marge 0,5 = rejeu, trois lots. Les
corrections ne comptent pas comme lots (`DEC-0107` section 4). `PAR-1` (aucun lot partage), `PAR-2` (un
couple matiere/niveau par instance) et `PAR-8` : verifies par `camille-po` dans `DEC-0110` ; je ne les
rejuge pas.

### Affectations des developpeurs, et pourquoi

| Developpeur | Stories | Instance de `nadia-prof` qui audite |
|---|---|---|
| **lea-dev** | `S51-FRA-TEMPS`, `S51-FRA-ALPHABETIQUE`, puis `S51-FRA-GRAPHIE` (ou la ligne qui la remplace) | A |
| **marc-dev** | correction `HOM-CE1-R1` d'abord, puis `S51-SCI-ACTIVITE-HUMAINE-CM1`, `S51-SCI-CORPS-CM1`, `S51-SCI-TERRE-CM1` | A pour `HOM-CE1-R1`, B pour les trois lignes de sciences |
| sofia-sm (casquette dev) | aucune | - |

Justification :

1. **Un flux de redaction par instance.** lea-dev alimente A, marc-dev alimente B. Les deux instances
   auditent en parallele et chacune ne recoit que d'un auteur : pas de file d'attente croisee, pas de
   developpeur dont le retard bloque les deux instances.
2. **Le seul croisement est `HOM-CE1-R1`**, affecte a marc-dev, et il ne cree pas de goulot : c'est un
   fichier YAML seul, 0,5 (avis de `theo-archi` V4, aucun test ne nomme un prenom), qu'il ecrit en
   premier. Cela decharge lea-dev, qui porte trois paquets neufs dont un sous condition suspensive, et
   laisse la correction etre auditee par A des les premieres heures. marc-dev commence les trois paquets
   de sciences apres ; son premier paquet ne depend pas de A.
3. **Charge.** lea-dev : trois paquets neufs. marc-dev : trois paquets neufs plus une correction. Les
   tarifs de `DEC-0110` (3,0 pour les trois lignes de francais, 4,5 plus 0,5 pour la sciences et la
   correction) mesurent la charge d'audit de `nadia-prof`, pas le temps de redaction : je ne les lis pas
   comme une charge de developpement. Si marc-dev devient le chemin critique, je reaffecte
   `S51-SCI-TERRE-CM1` a lea-dev une fois ses paquets de francais livres (categorie capacite, mon role).
4. **Ordre de marc-dev sur la sciences** : `S51-SCI-ACTIVITE-HUMAINE-CM1` en premier. C'est la ligne que
   `nadia-prof` appelait *"la plus dangereuse du lot"* (`DEC-0110` section 4 ; `SCI-CM1-16` a ferme au
   troisieme passage a s46) ; la commencer tot laisse le temps d'un rejeu avec une marge de 0,5. Le test
   `SCI-2` y attend la reponse a Q2 (section "Points routes") ; si elle manque quand marc-dev arrive a ce
   test, il passe aux deux autres paquets et revient.
5. **Conflit de fusion previsible, que j'assume.** Chaque paquet neuf ecrit dans les memes fichiers
   partages (entree de `catalogue.yaml`, `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`, inventaire de
   `TexteAccentueExposeParLePortEntrantTest`). Deux flux paralleles se rencontreront a la fusion sur ces
   trois fichiers. Je fusionne branche par branche, `mvn -B clean verify` sur l'arbre fusionne apres
   chaque fusion, et je ne resous jamais un conflit en affaiblissant une assertion.

### Ecriture commune a chaque paquet neuf (avis de `theo-archi`, section 3, V2 a V4, et section 4)

Reportee telle quelle dans les taches de lea-dev (trois paquets de francais) et de marc-dev (trois
paquets de sciences). Chaque paquet neuf inscrit, **dans le meme commit que son contenu** :

1. **une entree dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`**, avec son motif
   (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`) ;
2. **ses cinq chaines de catalogue dans l'inventaire de `TexteAccentueExposeParLePortEntrantTest`**
   (identifiant, titre, consigne, domaine, competence) ;
3. **une espace insecable devant `?`, `!`, `:` et `;`**, dans les items **et dans le titre et la
   consigne de l'entree de catalogue** (`AucunSigneDoubleNEstDetachableDeSonMotTest` compte le catalogue ;
   une entree neuve n'herite d'aucune dette).

Pieges deja mesures (avis de s50, section 2, repris par `theo-archi`) : drapeau
`UNICODE_CHARACTER_CLASS` sur les expressions regulieres, compte des mots. Specifiques :

- `S51-FRA-TEMPS` : *présent* est strictement la plus longue des trois propositions ; la fenetre
  `TRV-CE1-2` (au plus la moitie) contre `TPS-CE1-1` (au moins un quart) se tient a la redaction et
  s'asserte dans le test de paquet. Les trois mots portent leur accent.
- `S51-FRA-ALPHABETIQUE` : la decision tombe a la premiere ou a la deuxieme lettre, jamais a la
  troisieme (`ALP-CE1-2`) ; s'asserte en comparant les deux premieres lettres en caracteres bruts.
- `HOM-CE1-R1` : YAML seul ; un prenom accentue s'ecrit avec son accent.
- Sciences : un item a trois propositions numeriques entre dans le calcul de rang et rend le paquet
  partiellement juge : le declarer dans `PAQUETS_A_ITEMS_ECARTES` avec le compte exact, ou garder le
  paquet homogene. `SCI-2` s'encode en test de paquet (aucun jeton d'adresse a l'enfant en mot entier,
  sous `UNICODE_CHARACTER_CLASS`, sur enonce, propositions, indice et explication), la liste exacte
  etant celle de `nadia-prof` (Q2).

Chaque developpeur ecrit ses tests **avec** son code, et ne pousse que si `mvn verify` est vert en
local. **Annoncer la porte technique, c'est nommer les jobs lances** (`CLAUDE.md` racine) : voir
section "Porte technique".

## Blocage a router : `S51-FRA-GRAPHIE` (`FRA-CE1-22`) est infaisable en l'etat

**Constat de `theo-archi`** (avis, section 2 verdict V1, section 3 "V1, le bloquant, mesure") :
`GRL-CE1-2` impose le distracteur *un garcon* pour *un garçon*. `LexiqueFrancais.squelette` retire la
cedille, *garcon* n'est pas au lexique, et `AucunMotServiNeSEcarteDeSonAccentTest` refuse le paquet
(*"le mot "garcon" n'est pas une forme du francais, alors que [garçon] en est une du meme squelette"*).
Le garde-fou n'a ni table d'exemption ni cliquet. Contre-epreuve de `theo-archi` : les quatre items en
`ç` remplaces par des mots sans cedille, 3 tests, 0 echec : la cedille est la cause, et la seule. Les
regles `m` (*janbe*) et `g` (*gitare*) ne declenchent rien. Le paquet que la story decrit est donc rouge
par construction.

**Voie proposee par `theo-archi`, A1** : amendement d'`ADR-0065`, forme la plus etroite trouvee : *un mot
sans sa marque est admis dans un distracteur si et seulement si la bonne reponse du meme item porte la
forme marquee a la meme position, et que les deux propositions ne different que par cette marque.* Regle
executable : une clause neuve de `AucunMotServiNeSEcarteDeSonAccentTest` et sa mise a l'epreuve. **L'autre
voie** (une autre forme d'item pour `c`/`ç`, par exemple propositions `c` / `ç`) contredit `GRL-CE1-2`
telle que `nadia-prof` l'a ecrite : elle lui appartient.

**Decision de planning : l'engagement de `S51-FRA-GRAPHIE` est sous condition suspensive.**

> **Condition** : A1 est ecrit par `theo-archi` (fichier `ADR-0065` amende ou `ADR` neuf, existant
> physiquement dans le depot) **ET** `nadia-prof` a valide par ecrit que A1 tient `GRL-CE1-2` (sa
> question Q1), **avant que l'instance A ne touche cette ligne**.
>
> **Si la condition n'est pas remplie** : `camille-po` remplace la ligne par une autre ligne de
> francais CE1 (21 atteignables, section 7 de `ARBITRAGE-PREALABLE-s48-francais-ce1.md` ; `DEC-0110`
> section 3), par amendement ecrit de `DEC-0110` dans le meme geste, avec son tarif, son avis de
> faisabilite de `theo-archi` et sa story au backlog (`PAR-16`). Je ne choisis pas la ligne de
> remplacement et je ne la tarife pas.
>
> **Dans tous les cas, `S51-FRA-TEMPS` et `S51-FRA-ALPHABETIQUE` demarrent sans attendre.** Ils sont
> faisables en l'etat (avis V2, V3). Je n'ordonne pas A a `S51-FRA-GRAPHIE` en premier.

Ce que cette condition ne fait pas : elle ne tranche ni A1 (a `theo-archi`), ni sa recevabilite
pedagogique (a `nadia-prof`), ni le choix d'une ligne de remplacement (a `camille-po`). Obstacle ouvert
tant que le fichier d'`ADR` n'existe pas : je ne le clos pas sur une intention annoncee. La
**marge de l'instance A** (1,5 dont rejeu 0,5) couvre un refus ; elle ne couvre pas un refus **plus** une
ligne de remplacement a un tarif plus haut : si `camille-po` choisit un tarif different de 1,0, la
verification `PAR-8` se refait dans son amendement.

## Avis de faisabilite - theo-archi

Source : `squad/sprints/s51-trois-instances-francais-sciences-maths/avis-faisabilite.md` (non modifie
ici). Mesure : reacteur complet a `f68458de`, 2004 tests, 0 echec, `BUILD SUCCESS`, JDK 21.0.10 rapporte
par la machine virtuelle des tests ; **seul le job `build` couvert** (avis, section 0). Ce chiffre est la
base d'un autre agent : je le reverifierai moi-meme sur l'arbre fusionne a la review, je ne le relaye pas.
Quatre paquets-sondes passes par les garde-fous reels ; **aucune story ne touche `src/main/java`,
aucune n'appelle de regle ArchUnit** (A1, A2 et A3 portent sur des garde-fous de contenu JUnit).

| Story | Avis | ADR requis |
|---|---|---|
| `S51-FRA-GRAPHIE` (V1) | **infaisable en l'etat**, faisable moyennant `ADR` : le distracteur sans cedille que `GRL-CE1-1` et `GRL-CE1-2` imposent est refuse par `AucunMotServiNeSEcarteDeSonAccentTest` | amendement d'`ADR-0065` (A1), **a ecrire avant la ligne** |
| `S51-FRA-TEMPS` (V2) | faisable en l'etat | non |
| `S51-FRA-ALPHABETIQUE` (V3) | faisable en l'etat | non |
| `HOM-CE1-R1` (V4) | faisable en l'etat, YAML seul | non |
| `S51-SCI-CORPS-CM1` (V5) | faisable en l'etat | non |
| `S51-SCI-TERRE-CM1` (V6) | faisable en l'etat | non |
| `S51-SCI-ACTIVITE-HUMAINE-CM1` (V7) | faisable en l'etat ; `SCI-2` encodable en test de paquet, liste des jetons a `nadia-prof` (Q2) | non |
| `S51-MAT-SYMETRIE` (V8), s52 | faisable sous condition (materiau, au moins dix dessins, `ZON-13` encode avant le premier commit du paquet) | amendement d'`ADR-0080` (A2) |
| `S51-MAT-MULTIPLICATION` (V9), s52 | faisable sous condition du materiau (reseaux de points) | non |
| `S51-MAT-COMPARER-RANGER` (V10), s52 | faisable en l'etat | non |

V8 a V10 servent s52 : l'instance C n'est pas engagee, `DEC-0110` section 8 (dernier alinea) prevoyait
que l'avis les porte quand meme.

## Isolation des instances de `nadia-prof` (`DEC-0110` section 14.4, `squad/README.md` section 9)

| Instance | Branche | Worktree | Scratchpad |
|---|---|---|---|
| A (francais CE1) | `s51-nadia-instance-a` | worktree dedie, dossier voisin du checkout, `recreo-s51-nadia-instance-a` | un repertoire temporaire hors depot, **propre a A** |
| B (sciences et technologie CM1) | `s51-nadia-instance-b` | worktree dedie, dossier voisin du checkout, `recreo-s51-nadia-instance-b` | un repertoire temporaire hors depot, **propre a B** |

**Pourquoi cette section est la plus longue du planning.** s50 a viole la regle de la section 9 du README
(*"toute paire d'instances du meme agent tournant en parallele recoit un scratchpad aussi dedie que son
worktree"*, amendement de s30, applicable depuis s31) : les deux instances partageaient session, checkout
et scratchpad, et ont ecrit `jouer.py` au meme endroit a 21:31:21 et 21:31:34. C'est la forme (c) de
l'arbitrage prealable de `nadia-prof`, section 2.3, que la retro de s50 n'avait pas consignee (amendement
de cette retro, section 6 de ce document). `DEC-0109` ne citait pas la regle.

**Clauses obligatoires de la consigne de lancement de chacune des deux instances**, ecrites dans la
consigne et pas laissees a la memoire de l'agent (`squad/README.md` section 9, amendement de s39 :
`sofia-sm` ouvre le worktree de l'agent qu'elle route, dans le meme geste que le routage) :

1. **Un worktree dedie** (A : `s51-nadia-instance-a`, B : `s51-nadia-instance-b`), ouvert par moi
   **avant le premier ecrit** de l'instance, et `outils/verifier-lignee.sh` (ou, a defaut, `git
   merge-base --is-ancestor round-two HEAD`) lance avant le premier commit.
2. **Une branche dediee** du meme nom.
3. **Un scratchpad dedie**, repertoire temporaire hors depot propre a l'instance, cree au lancement ;
   aucun fichier de travail (`jouer.py` ou autre) n'est ecrit a la racine du scratchpad de session ni dans
   celui de l'autre instance.
4. **Le serveur de jeu se lance depuis le `bootstrap/target/recreo.jar` du worktree de l'instance**, jamais
   depuis le checkout partage (exposition constatee en s50 par `nadia-prof`, arbitrage prealable section
   2.3, "Une exposition de plus").
5. **Toute ecriture dans `squad/decisions/REGISTRE-identifiants.md` se fait depuis le worktree de
   l'instance**, jamais depuis le checkout partage (README section 9, amendement du 2026-09-10).
6. **Toute mesure du reacteur se fait depuis le worktree de l'instance** (README section 9, amendement
   s30), avec `-am` pour une mesure partielle (`CLAUDE.md` racine).
7. **Une collision constatee s'ecrit** dans la fiche ou dans le message de retour : l'incident vu et non
   ecrit n'a pas eu lieu pour le depot (arbitrage prealable, section 1.1).

Les worktrees des developpeurs (lea-dev, marc-dev), de `yanis-qa` et de `theo-archi` sont ouverts par moi
dans le meme geste que le routage de chacun (README section 9, amendement s39). **Aucune branche, aucun
worktree, aucun scratchpad pour l'instance C.** Je n'ouvre rien dans ce planning : l'ouverture se fait au
lancement, pour que la branche descende du `round-two` du moment.

## Points routes (par categorie ; le daily n'est pas journalise)

| Code | Point | Categorie | Agent route | Artefact attendu | Etat |
|---|---|---|---|---|---|
| R1 | **Bloquant** : A1, amendement d'`ADR-0065` (avis, Q1 et Q6) | technique | `theo-archi` | `ADR-0065` amende ou `ADR` neuf, **fichier existant** | ouvert, **premiere tache de la sequence** |
| R2 | **Bloquant** : validation par `nadia-prof` que A1 tient `GRL-CE1-2` (avis, Q1) | pedagogie | `nadia-prof` | avis ecrit dans un fichier du depot (fiche ou arbitrage, son choix) | ouvert, **meme tache que R1, en parallele** |
| R3 | Remplacement de la ligne `FRA-CE1-22` si R1 ou R2 manque | produit | `camille-po` | amendement de `DEC-0110`, tarif, story au backlog, avis de `theo-archi` | conditionnel |
| R4 | Q2 : liste des jetons d'adresse a l'enfant pour le test de `SCI-2` (avis, Q2) | pedagogie | `nadia-prof` | avis ecrit (fiche de conformite de `S51-SCI-ACTIVITE-HUMAINE-CM1` ou arbitrage) | ouvert, non bloquant pour demarrer, bloquant pour le test `SCI-2` |
| R5 | Clause `C5` clause 1 d'`ADR-0104` (aucune image dans une boite de proposition) : l'`ADR` est ecrit, statut `Propose`, passe `Accepte` sur sa seule reponse | pedagogie | `nadia-prof` | reponse ecrite | **hors capacite de s51** : aucune story, aucun budget |
| R6 | `ART-CP-10` entre-t-elle dans s52 ou plus tard ? | produit | `camille-po` | `DEC` | **hors capacite de s51** |
| R7 | Q4 (nombre de manches de la symetrie, un dessin par item ; libelle de sous-partie de `MAT-CE1-02`) | produit | `camille-po` | `DEC` | **reportee a s52** (C non engagee) |
| R8 | Q5 (affecter le dessinateur des dessins de C, convention s25 et s39) | capacite | `sofia-sm` | affectation au planning de s52 | **reportee a s52** |
| R9 | Q3 (contraintes de dessin, legende a 36 libelles, mesure de L) ; A2 et A3 | design, technique | `ines-design`, `theo-archi` | contraintes, amendements d'`ADR-0080` | **reportes a s52** (A2 a ecrire avant le premier paquet de designation de CE1, A3 avant la moitie designation de `MAT-CE1-02`) |
| R10 | Ecart "palier 2" de la matrice : la designation existe depuis s36, la matrice note "palier 2 requis" sur `QLM-CP-12` et deux lignes de HG CM1, et ne dit rien sur `QLM-CP-10` (`DEC-0110` sections 6 et 11, route a `sofia-sm`) | produit | `camille-po` (la matrice est la sienne) | cellules de la matrice reecrites avec la limite reelle (materiau, `DEC-0028`, autre) ou lignes instruites | signale, a traiter hors capacite de s51 |
| R11 | `PAR-4` a deux instances ; ecart `PAR-9` (4,0 marge comprise) contre la pratique depuis s41 (`DEC-0110` section 14.7) | produit | `camille-po` | `DEC` | **questions posees a s52** |
| R12 | Verification des formes (a), (b), (c) a la cloture de s51, qui rouvrirait la derivation a trois instances pour s52 (arbitrage prealable, section 8) | pedagogie | `nadia-prof` | document neuf si elle conclut a zero occurrence | a la cloture |

**Aucun obstacle n'est clos par ce planning.** R1 se clot quand le fichier d'`ADR` existe ; R2 quand
l'avis ecrit existe.

## Porte technique

- **Juge : la CI, tous les jobs de `.github/workflows/ci.yml`** : `prefixes` (prefixes de commit par
  agent), `chemins` (chemins de poste de travail), `outils` (juges purs des outils de poste), `build`
  (Build et tests, Java 21 : `mvn verify`). `mvn verify` n'est que le contenu du job `build`.
- **`yanis-qa`** : rapport qualite de cloture sur l'arbre fusionne, **en nommant les quatre jobs qu'il a
  lances**, avec la commande exacte, le total de tests mesure sur l'arbre fusionne et l'origine de l'ecart
  avec la base de 2004. Toute mesure partielle cite son perimetre et porte `-am`.
- **`theo-archi`** : confirmation ArchUnit sur l'arbre fusionne, plus l'ecriture de A1 (R1).
- **`relecteur-pedagogique`** : relecture en fin de sprint des textes neufs et corriges (six paquets plus
  la correction), des fichiers de ceremonie et de l'amendement de la retro de s50.
- **Porte pedagogique** : prononcee **uniquement** par `nadia-prof`, instances A et B, apres avoir joue
  chaque paquet, avec un motif ecrit dans `squad/conformite/FICHE-<jeu>.md`. Je ne la prononce jamais.
  **`ines-design` n'a aucune tache a s51** : ni dessin, ni rendu neuf (C n'est pas engagee).

## Verification poste/story (`PAR-16`, `squad/README.md` section 4)

Controle fait a la cloture de ce planning, poste par poste, sur la composition de `DEC-0110` section 14
(`DEC-0086` section 5) :

| Poste | Tarif | Story au backlog (`grep -n "^### S51-" docs/backlog/backlog.md`) | Affectation nominative | Issue |
|---|---|---|---|---|
| `FRA-CE1-22` | 1,0 | `S51-FRA-GRAPHIE` (ligne 18706) | lea-dev / instance A | engage **sous condition suspensive** (R1, R2) ; sinon remplace par `camille-po` (R3) |
| `FRA-CE1-20` | 1,0 | `S51-FRA-TEMPS` (ligne 18759) | lea-dev / instance A | engage |
| `FRA-CE1-23` | 1,0 | `S51-FRA-ALPHABETIQUE` (ligne 18811) | lea-dev / instance A | engage |
| `HOM-CE1-R1` | 0,5 | story de correction differee (ligne 4058) | marc-dev / instance A | engage |
| `SCI-CM1-10` | 1,5 | `S51-SCI-CORPS-CM1` (ligne 18859) | marc-dev / instance B | engage |
| `SCI-CM1-15` | 1,5 | `S51-SCI-TERRE-CM1` (ligne 18913) | marc-dev / instance B | engage |
| `SCI-CM1-18` | 1,5 | `S51-SCI-ACTIVITE-HUMAINE-CM1` (ligne 18957) | marc-dev / instance B | engage |
| `MAT-CE1-17` | 1,0 | `S51-MAT-SYMETRIE` (ligne 19007) | aucune | **non engage, budget libere**, 1,0, report a s52 |
| `MAT-CE1-06` | 1,0 | `S51-MAT-MULTIPLICATION` (ligne 19084) | aucune | **non engage, budget libere**, 1,0, report a s52 |
| `MAT-CE1-02` (moitie textuelle) | 1,0 | `S51-MAT-COMPARER-RANGER` (ligne 19177) | aucune | **non engage, budget libere**, 1,0, report a s52 |
| Passe transverse (`PAR-4`) | aucun montant alloue | aucune | aucune | **non engage, budget libere** (aucun montant n'etait alloue), question posee a s52 (R11) |

Resultat : **aucun poste engage sans story, aucun poste engage sans affectation nominative, aucun poste
de C engage.** Montant libere : 3,0 (C), destination s52. Total engage : **8,0** (A 3,5 + B 4,5), contre
11,0 avant l'amendement de `DEC-0110`.

**Ecart que je signale sans le corriger.** Les huit stories engagees portent encore, au backlog, la
ligne *"Agent pressenti : a affecter par `sofia-sm`"* ; le backlog est a `camille-po`, ce planning est
l'enregistrement de l'affectation nominative. Si la lecture stricte de `PAR-16` exige l'affectation
dans la story elle-meme, c'est a elle de reporter les noms ci-dessus.

## Sequence de demarrage

1. **Premiere tache, des l'ouverture : R1 et R2 en parallele.** `theo-archi` ecrit A1 ; `nadia-prof`
   repond a Q1. Rien d'autre ne les attend.
2. **Sans attendre R1 et R2** : j'ouvre les worktrees et les branches (A, B, lea-dev, marc-dev), je lance
   les instances A et B avec les sept clauses de la section d'isolation, je verifie la lignee de chaque
   worktree avant son premier commit. Je pose Q2 a `nadia-prof` (R4).
3. **lea-dev** : `S51-FRA-TEMPS`, puis `S51-FRA-ALPHABETIQUE`. `S51-FRA-GRAPHIE` seulement quand R1 et R2
   sont satisfaits ; sinon, la ligne que `camille-po` aura substituee (R3).
4. **marc-dev** : `HOM-CE1-R1` (YAML seul), puis `S51-SCI-ACTIVITE-HUMAINE-CM1`, `S51-SCI-CORPS-CM1`,
   `S51-SCI-TERRE-CM1`.
5. **nadia-prof, instances A et B** : audit paquet par paquet des livraisons, chacune dans son worktree,
   sa branche et son scratchpad ; fiches de conformite ; verdicts. Un refus reste un refus.
6. **A la cloture** : fusion branche par branche avec verification de la lignee de chaque worktree par
   moi-meme (le provisionnement sur la mauvaise branche s'est produit a chaque sprint depuis s03), puis
   `yanis-qa` (quatre jobs nommes), `theo-archi` (ArchUnit), `relecteur-pedagogique`, `review.md`,
   `RAPPORT.md`, `python outils/rapport-tokens.py --sprint s51`, `retro.md`.

## Ce que ce planning ne fait pas

- Il ne decide ni A1, ni la recevabilite pedagogique de A1, ni la ligne de remplacement de `FRA-CE1-22`.
- Il ne prononce aucun Done, technique ou pedagogique, et ne change aucun statut de la matrice.
- Il n'ouvre aucun worktree, aucune branche, ne commite rien, ne cree ni n'amende aucun `ADR` ni `DEC`.
- Il n'engage rien pour l'instance C et ne derive aucune borne a trois instances.

## Decisions prises

Aucune `ADR` ni `DEC` n'est creee ou amendee par ce planning. Trois decisions de planning, qui ne
relevent d'aucun autre role :

1. **Affectations nominatives** des six stories de contenu et de la correction : lea-dev (A, trois lignes
   de francais), marc-dev (`HOM-CE1-R1` puis les trois lignes de sciences de B), justifiees plus haut.
2. **L'engagement de `S51-FRA-GRAPHIE` est sous condition suspensive** : A1 ecrit par `theo-archi` et
   valide par `nadia-prof` avant que l'instance A touche la ligne ; sinon remplacement par `camille-po`,
   et `S51-FRA-TEMPS` et `S51-FRA-ALPHABETIQUE` demarrent sans attendre.
3. **Isolation** : un worktree, une branche et un scratchpad dedies pour A et pour B, ecrits dans la
   consigne de lancement de chaque instance ; aucun pour C.
