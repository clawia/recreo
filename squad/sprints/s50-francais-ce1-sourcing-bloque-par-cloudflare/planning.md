# Planning - s50 - proposition : vivier francais CE1 (plusieurs lignes), chantier de sourcing
d'oeuvres bloque par un fournisseur, corrections differees en attente

- **Horodatage de debut** : 2026-10-03 (UTC+02:00).
- **Casquette** : `sofia-sm`, Scrum Master.
- **Slug** : `s50-francais-ce1-sourcing-bloque-par-cloudflare`, dossier ouvert par ce planning -
  aucun document prealable ne l'avait ouvert.
- **Nature de ce document : une proposition, pas un objectif gele.** Aucune `DEC` n'ouvre a ce jour
  le perimetre de s50. Je ne la fabrique pas a sa place - router le perimetre, le choix des lignes,
  le tarif et le nombre d'instances a `camille-po` est ma regle, pas une option (table de routage,
  `CLAUDE.md` racine). Ce document rassemble ce que le depot sait deja, propose un perimetre et
  route explicitement ce qui manque avant qu'un objectif puisse etre gele.

## 1. Lignee verifiee avant toute ecriture

`git rev-parse --abbrev-ref HEAD` rend `round-two` ; `git log -1 --format=%H` rend
`799d82fdb6de192a1bf08004130aaec137e8dbec`, l'amendement de cloture de s49. `git status --short`
n'est **pas** vide - voir section 3 : un chantier non commite y figure, anterieur a ce planning,
que ce document ne cree pas et qu'il ne modifie pas. Ce planning n'ecrit que
`squad/sprints/s50-francais-ce1-sourcing-bloque-par-cloudflare/planning.md`.

## 2. Etat de sortie de s49, verifie dans le depot

- **Couverture** : `squad/programme/MATRICE-couverture.md` porte **120/182 (65,9 %)**,
  `FRA-CE1-01` `couvert-valide` depuis la cloture de s49.
- **Une reserve neuve, non bloquante, deja routee au backlog, developpeur non affecte** :
  `GRC-CE1-R1` (le distracteur *colline* revient sur les quatre items `ill` de "Le meme son"),
  story ecrite dans `docs/backlog/backlog.md` (commit `a272c703`).
- **Quatre autres reserves non bloquantes, deja ecrites en stories de correction differee au
  backlog, developpeur non affecte sur aucune d'elles** : `SOU-CE1-R1` (soustractions CE1,
  distracteur non identifiable), `DIV-CE1-R1` (division CE1, distracteur incalculable sur huit
  items), `SOP-CE1-R1` (soustractions posees CE1 - `nadia-prof` a deja rendu son propre arbitrage
  le 2026-10-02, s47 : cinquieme forme de distracteur admise sur les deux items sans emprunt, story
  d'execution deja ecrite), `TAB-CE1-R1` (tables de multiplication CE1, l'indice des items a
  facteur 1 n'enseigne pas l'erreur). Les cinq corrections (`GRC-CE1-R1` incluse) sont toutes
  pretes a developper au meme tarif de precedent, **0,5 chacune** (`DEC-0107` section 2, "meme
  precedent `PAR...`" applique aux trois corrections de s49).
- **`DEC-0106` section 4** (reference perimee sur la borne de longueur du texte support) :
  **deja corrigee** (`review.md` s49, section 7, "Resolu dans `DEC-0107` section 7"). Rien a router
  ici.

## 3. Le chantier de sourcing du corpus d'oeuvres (`ADR-0082`) : progres reel, non commite, et bloque
pour sa partie contenu par un fournisseur, pas par le depot

`squad/rapports/sourcing-oeuvres-s49-verification.md` (session principale, acces web reel, hors
mandat des sous-agents) verifie individuellement 25 oeuvres candidates contre la fiche publiee de
leur institution (licence `CC0-1.0` exacte exigee par `ADR-0082`), redimensionne chaque fichier a
352 px et ecrit le registre. **Ce travail existe sur le disque et n'est pas commite** : `git
status --short` porte `squad/corpus/oeuvres-domaine-public.yaml`, le dossier
`adapters/adapter-web/src/main/resources/static/oeuvres/`, la mise a jour du cliquet de poids dans
`ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest.java`, et
`squad/decisions/REGISTRE-identifiants.md`. **Le depot Git est la seule memoire partagee** : tant
que ce commit n'existe pas, ce travail n'existe pas au sens du gouvernement de ce projet, meme s'il
est reel et verifie.

**Mais T6 (`ADR-0082`, "paquet arts plastiques CP, lignes 9 et 10", 20 items, tarif 2,0) ne peut
pas etre construit en l'etat.** `ART-CP-09` ("decrire une oeuvre") exige 8 oeuvres et n'en a que 5
au registre ; `ART-CP-10` ("reconnaitre des oeuvres deja rencontrees") perd la paire qu'elle
exigeait (3 et 9, meme peintre). Les trois oeuvres manquantes (`oeuvre-chambre` - Van Gogh,
`oeuvre-rue-sous-la-pluie` - Caillebotte, `oeuvre-grande-jatte` - Seurat, toutes trois a l'Art
Institute of Chicago) sont **verifiees `CC0-1.0`** mais leur fichier est bloque par une protection
Cloudflare du fournisseur (HTTP 403 a toute requete automatisee) : **ce n'est pas un refus de
licence, c'est une barriere technique qu'aucun agent de la squad ne peut franchir**, hors de la
meme famille que la limite d'acces web nommee en retro de s49 mais d'une nature differente - ici
l'acces existe et la preuve est faite, seul le telechargement du fichier echoue. `nadia-prof` a
deja tranche de ne pas remplacer ces trois oeuvres (`squad/corpus/CHOIX-oeuvres-candidates-T5.md`,
section 3.1) et ecrit elle-meme : *"le lot d'arts plastiques CP ne se joue pas avant leur entree"*.
Je ne rouvre pas ce choix a sa place.

**Ce que je propose pour s50 sur ce chantier, sans le trancher** :
1. Commiter l'etat verifie (registre a 25 oeuvres, fichiers JPEG, cliquet de poids, correction de
   `REGISTRE-identifiants.md`) comme premier geste du sprint, pour que ce travail entre dans la
   seule memoire que ce projet reconnait. Je route l'affectation de ce commit a `theo-archi`
   (proprietaire du cliquet de poids et du garde-fou ArchUnit qu'il reverifie) ou a un developpeur
   sous sa confirmation - une question de capacite que je trancherai des que le perimetre est gele.
2. **Ne pas engager T6 a s50** : la lot de contenu reste bloque tant que les trois fichiers ne sont
   pas recuperes a la main par une personne munie d'un navigateur, hors de la portee des agents -
   exactement ce que l'utilisatrice signale dans sa demande de ce planning. Je route a
   `camille-po` la question de signaler ce besoin a l'utilisatrice, et a `theo-archi` la
   confirmation que le garde-fou de poids reste coherent sur 25 oeuvres avant tout commit.

## 4. Vivier mathematiques CE1 - reverifie, toujours instruit epuise

`squad/programme/MATRICE-couverture.md`, section "Mathematiques - CE1 (cycle 2)" : les dix-huit
lignes du referentiel (`MAT-CE1-01` a `MAT-CE1-18`) sont toutes `couvert-valide`, a l'exception de
`MAT-CE1-09` (longueurs), deja `en cours` sous une limite textuelle posee d'avance par `nadia-prof`
elle-meme ("mesurer... avec une regle graduee" n'est pas servable par du texte seul). **Aucune
ligne neuve de mathematiques CE1 n'est instruite a `couvert-valide` atteignable a ce jour** - meme
constat que `s49` (planning, section 3.1 ; `DEC-0107`, section 3). Ouvrir une ligne neuve de
mathematiques CE1 supposerait un arbitrage prealable de `nadia-prof` qui n'existe pas. Seuls les
quatre correctifs de la section 2 restent a jouer sur ce vivier.

**Aucun autre vivier hors francais CE1 et mathematiques CE1 n'est signale instruit** (`DEC-0107`,
section 3 : *"aucun autre vivier hors mathematiques n'est signale"*, et rien de nouveau n'a ete
instruit depuis). Je ne l'invente pas : arts plastiques CP est instruit mais bloque (section 3),
et aucune autre matiere/niveau ne porte d'arbitrage prealable pret a chiffrer.

## 5. Vivier francais CE1 - vivier ouvert, un paquet deja livre, 24 lignes candidates

`ARBITRAGE-PREALABLE-s48-francais-ce1.md` instruit les 26 lignes du referentiel (`FRA-CE1-01` a
`FRA-CE1-26`) : **24 lignes plafonnees `couvert-valide` atteignable** (`FRA-CE1-03` et `FRA-CE1-21`
restent plafonnees `en cours`, non servies par ce produit). `FRA-CE1-01` est fermee depuis s49.
**`FRA-CE1-02` et les 22 autres lignes restent au vivier ouvert, non engagees.**

Je nomme, a titre d'eclairage et sans trancher a la place de `camille-po` : `FRA-CE1-02` ("la
valeur d'une lettre selon les lettres qui l'entourent") porte, comme `FRA-CE1-01`, le moins de
conditions transverses neuves a verifier - pas de `texteSupport` (la borne de longueur du texte
support ne concerne que `FRA-CE1-04` a `FRA-CE1-07`). C'est la candidate la plus directement
chiffrable sur le modele de `FRA-CE1-01` (tarif `1,0`, meme nature de competence - `nadia-prof`
source du referentiel).

**Le frein pose a s49** - *"une ligne neuve par sprint pour un vivier qui n'a encore produit aucun
paquet"* - visait un vivier **vierge**. `FRA-CE1-01` a depuis produit un paquet livre et audite :
le motif qui limitait a une ligne ne s'applique plus tel quel. Je le signale a `camille-po` comme
un changement d'etat qui justifie d'examiner plusieurs lignes a s50, sans decider a sa place
combien ni lesquelles.

## 6. Composition proposee, et pourquoi une seule instance malgre le plafond de quatre

**Instruction recue hors des neuf agents, sans source dans le depot avant ce document** (forme 3,
`squad/README.md` section 2 bis) : *"jusqu'a 4 instances de nadia-prof si besoin"*, recue le
2026-10-03, pour maximiser la croissance de couverture du programme. **Je ne la fonde sur rien par
moi-meme** - une consigne de cette forme ne vaut jamais motif (deuxieme borne de la forme 3) -, je
la route a `camille-po` pour qu'elle l'acte en `DEC` d'ouverture, **comme un plafond disponible,
pas un plafond impose.**

**Et le plafond disponible ne se traduit pas ici en plafond utile.** `PAR-2` interdit qu'une meme
matiere soit partagee entre deux instances. Le seul vivier non bloque et non epuise a ce jour est
**francais CE1, une seule matiere** (section 5) ; les quatre correctifs de mathematiques CE1
(section 2) et `GRC-CE1-R1` (francais CE1) touchent des paquets deja `couvert-valide`, sans ouvrir
de matiere neuve ; arts plastiques CP est bloque (section 3) ; aucune autre matiere n'est instruite
(section 4). **Une seconde instance n'aurait, a ce jour, aucune matiere distincte a porter sans
violer `PAR-2`** - ouvrir une seconde instance sur francais CE1 ou sur mathematiques CE1 en parallele
de la premiere reviendrait exactement a l'interdit que `PAR-2` existe pour empecher. Je recommande
donc **une seule instance de `nadia-prof` pour s50**, non par prudence de calendrier mais parce que
le vivier reellement instruit ne porte qu'une matiere exploitable cette fois-ci. Si `camille-po` ou
`theo-archi` debloquent T6 en cours de sprint (fichiers recuperes), la question d'une seconde
instance degagee pour arts plastiques CP redeviendrait pertinente - je la rouvrirais alors.

**Composition proposee pour cette instance unique, non chiffree, a `camille-po` de sceller :**

| Element | Nature | Tarif de precedent | Lot au sens de `PAR-8` |
|---|---|---|---|
| `FRA-CE1-02` et au moins une ligne francais CE1 supplementaire parmi les 22 disponibles (choix de `camille-po`) | ligne neuve | ~1,0 chacune (precedent `FRA-CE1-01`) | oui |
| `GRC-CE1-R1` | correction differee, francais CE1 | 0,5 (precedent `DEC-0107`) | non, precedent hors compte de lots |
| `SOU-CE1-R1`, `DIV-CE1-R1`, `SOP-CE1-R1`, `TAB-CE1-R1` | corrections differees, mathematiques CE1 | 0,5 chacune (meme precedent) | non |

**`PAR-8` (plafond individuel 5,0, au plus trois lots)** : au plus trois lignes neuves de francais
CE1 tiennent dans la borne une fois les cinq corrections (2,5 au total) deduites de 5,0 - soit
2,5 restants, donc deux a trois lignes neuves selon leur tarif exact, a `camille-po` de sceller.
**`PAR-1`** (aucun lot partage entre deux instances) est trivialement tenue a une seule instance.
**`PAR-7`** (terrain vierge) ne s'applique pas : ni francais CE1 ni mathematiques CE1 ne sont
vierges (chacun porte deja au moins une ligne `couvert-valide`). **`CYC-1`** (continuite cycle 2 /
cycle 3) n'est pas due : aucune ligne de CM1 n'entre dans cette composition.

## 7. Repartition des taches proposee, contingente au gel du perimetre

| Agent | Tache |
|---|---|
| `camille-po` | Ecrit la `DEC` d'ouverture de s50 : acte l'instruction recue (section 6) comme plafond disponible, choisit et tarife la ou les lignes de francais CE1 retenues au-dela de `FRA-CE1-02`, tranche si les cinq corrections differees entrent au meme chiffrage, decide du sort de T6 et du chantier de sourcing (engager le commit hors chiffrage en lots, sur le modele de `DEC-0107` section "tarif en lots" pour T2-T5 ; signaler a l'utilisatrice le besoin de recuperation manuelle des trois fichiers bloques) |
| `theo-archi` | Rend un avis de faisabilite dedie a s50 sur la ou les lignes de francais CE1 retenues ; confirme le cliquet de poids et le garde-fou ArchUnit sur l'etat a 25 oeuvres avant tout commit du chantier de sourcing ; ne rend aucun avis sur T6, non engage |
| `nadia-prof`, instance unique (Done pedagogique) | Audite la ou les lignes neuves de francais CE1 une fois livrees ; rejoue les quatre paquets mathematiques CE1 et le paquet francais CE1 corriges et confirme la levee de leurs cinq reserves |
| `marc-dev`, `lea-dev`, `sofia-sm` (casquette dev) | Affectation nominative finale une fois le perimetre gele par `camille-po` ; proposition : un developpeur sur la ou les lignes neuves de francais CE1, l'autre sur les cinq corrections differees (volume faible, un fichier YAML et son test par correction), `sofia-sm` ou `theo-archi` sur le commit du chantier de sourcing |
| `yanis-qa` | Rapport qualite de cloture (`mvn -B clean verify`, delta de tests explique, catalogue) |
| `relecteur-pedagogique` | Relit les textes neufs et corriges avant la cloture |
| `ines-design` | Aucune tache due a ce jour : ni rendu visuel neuf, ni T6 engage |

## 8. Risques et points a trancher, par ordre de priorite

1. **Aucune `DEC` n'ouvre s50.** Tant qu'elle n'existe pas, aucun developpeur ne peut etre affecte
   nominativement avec certitude.
2. **Le chantier de sourcing est reel mais non commite.** Un commit doit entrer au depot avant que
   quoi que ce soit ne s'appuie dessus - sinon le prochain recompte de couverture ou le prochain
   `mvn verify` ne trouvera toujours rien.
3. **T6 reste bloque par un fournisseur, pas par la squad.** Aucun agent ne peut lever ce blocage ;
   seule une recuperation manuelle par une personne munie d'un navigateur le peut. A signaler a
   l'utilisatrice via `camille-po`.
4. **Le nombre d'instances annonce (jusqu'a quatre) et le nombre d'instances utile (une, par
   `PAR-2`) different.** Si `camille-po` souhaite davantage de parallelisme, il faudra d'abord
   debloquer ou instruire une seconde matiere - je ne l'invente pas ici.
5. **Aucune ligne de francais CE1 au-dela de `FRA-CE1-02` n'est encore choisie ni chiffree** : a
   `camille-po`.

## 9. Ce que ce planning ne fait pas

- Il ne gele aucun objectif : aucune `DEC` ne le fonde a ce jour.
- Il ne choisit ni ne chiffre aucune ligne de francais CE1 au-dela de la nommer a titre
  d'eclairage : le choix et le tarif appartiennent a `camille-po`.
- Il ne tranche pas le sort de T6 ni du chantier de sourcing : il constate le blocage et route.
- Il ne commite aucun fichier du chantier de sourcing : il nomme ce qui reste a commiter et le
  route.
- Il ne fonde rien sur l'instruction recue hors des neuf agents : il la cite mot pour mot, la date,
  et la route a `camille-po` comme l'exige la forme 3.
- Il n'affecte nominativement aucun developpeur de maniere definitive.
- Il ne cree ni n'amende aucun `ADR` ni aucune `DEC`.

## Decisions prises

Aucune. Ce planning est une proposition ; le perimetre de s50 reste a geler par une `DEC` de
`camille-po`, suivie d'un avis de faisabilite de `theo-archi`. Je reviens affecter nominativement
les developpeurs des que ces deux documents existent dans le depot.

## Amendement du 2026-10-04 (`sofia-sm`, cloture) : horodatage de debut precise a l'heure

La section 0 ne portait que la date (*"2026-10-03 (UTC+02:00)"*), sans heure : insuffisant pour
`outils/rapport-tokens.py`, qui exige une heure sur la ligne *"Horodatage de debut"* pour situer la
fenetre du sprint (`RE_HORODATAGE`, groupe `HH:MM` obligatoire). Verifie au disque, pas invente :
`git log --follow --format=%H %ad --date=iso-strict -- squad/sprints/s50-francais-ce1-sourcing-bloque-par-cloudflare/planning.md`
rend un seul commit, `343f86c7`, horodate `2026-10-03T21:53:14+02:00` - le commit qui a cree ce
fichier. Je ne touche pas au texte original ci-dessus : cette ligne complete la mesure, elle ne la
corrige pas.

**Horodatage de debut : 2026-10-03 21:53 (UTC+02:00).**
