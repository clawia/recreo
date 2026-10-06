# [Sprint s52] Planning - 2026-10-06

- **Horodatage de debut** : 2026-10-06 20:51 (UTC+02:00), lu par `date` au moment de l'ecriture. La lecture
  des sources avait commence a 20:35 (premiere lecture de `date` de la tache).
- **Anime par** : sofia-sm (casquette Scrum Master ; aucune story de developpement portee par moi a s52)
- **Participants** : aucun agent invoque pour ce planning, qui constate l'etat du depot et affecte. **Sources
  lues, par auteur** : camille-po (`DEC-0111`, backlog "Sprint 52" et stories `S51-MAT-*`),
  theo-archi (avis de faisabilite `squad/sprints/s52-deux-instances-ce1-francais-maths/avis-faisabilite.md`,
  `ADR-0080` amendement A2), nadia-prof (arbitrage prealable
  `squad/conformite/ARBITRAGE-PREALABLE-s52-instances-ce1.md`), ines-design (contraintes de dessin,
  `squad/design/contrainte-ines-s52-dessins-de-l-instance-c-mathematiques-ce1.md`).
- **Slug** : `s52-deux-instances-ce1-francais-maths`. Le dossier existait avant ce planning (il porte l'avis
  de `theo-archi`, non modifie ici).
- **Lignee verifiee avant ecriture** : `git rev-parse --abbrev-ref HEAD` rend `round-two`, `git log -1
  --format=%h` rend `93aade92`, `bash outils/verifier-lignee.sh` sort 0, `git status --short` est vide. Ce
  planning est ecrit dans le checkout principal, sur instruction de l'orchestrateur, parce que personne d'autre
  n'y ecrit. Depuis la cloture de s51 (`85fa65aa`), `git log` rend cinq commits, tous de decision, d'avis ou de
  contrainte (`[NAD]`, `[CAM]`, `[THEO]` deux fois, `[INES]`) ; aucun developpement n'a precede ce planning, et
  `DEC-0111` comme l'avis le precedent : il n'y a rien a rattraper (amendement de s48, section 9 du README).

> **Avertissement - ecart non elucide sur les trailers `Co-Authored-By` "Claude Opus 5.5".** Deux comptes
> circulent : **8** (`retro.md` de s51, `review.md` de s51 obstacle 6, `DEC-0110` section 17.4) et **18**
> (transmis dans la consigne de cette tache, sans fichier ni methode au depot). Je n'explique pas 18. Mes
> propres mesures, qui ne sont ni l'un ni l'autre : `git rev-list --count --grep='^Co-Authored-By: Claude
> Opus' e4d8ba3b..93aade92` rend **11** commits (les 8 de s51 plus `4aad468b`, `edf15763`, `93aade92` de s52)
> et `git rev-list --count --grep='^Co-Authored-By: Claude Opus' round-two` rend **568** sur tout l'historique ;
> meme commande avec "Sonnet" : 25 et 338. Le chiffre de 18 ne se retrouve sur aucune de ces deux plages.
> Aucune regle ne fixe la valeur avant `DEC-0110` 17.4 ; elle est ecrite depuis ce commit (section "Amendement
> du README", plus bas). Je ne reecris aucun commit.

## Objectif du sprint

Gele, repris de `DEC-0111` section 1 : **porter a `couvert-valide` jusqu'a cinq lignes** (`FRA-CE1-17`,
`FRA-CE1-25`, `FRA-CE1-26`, `MAT-CE1-17`, `MAT-CE1-06`), **faire passer `MAT-CE1-02` a `en cours`** (moitie
textuelle), **lever `TPS-CE1-R1` et `ALP-CE1-R1`** et **retirer du catalogue servi les deux paquets de
sciences CM1 refuses**, sans qu'aucune instance de `nadia-prof` depasse 5,0. Mesure : `MATRICE-couverture.md`,
**127/182 au depart** (commande de la note de tete de la matrice relancee par moi : `couvert-valide` 127, `en
cours` 3, `non couvert` 52, `non couvrable en l'etat` 76, total 258), **132/182 au mieux (72,5 %)**. Ce n'est
pas une promesse : un Done pedagogique refuse reste un refus.

**Ce que le perimetre engage a la cloture de ce planning permet, et que l'objectif gele ne modifie pas** :
`MAT-CE1-17` n'est pas engagee (section "Conditions de l'instance C"). Au mieux **quatre fermetures,
131/182 (72,0 %)** ; cinq, donc 132/182, seulement si `camille-po` re-engage `MAT-CE1-17` par amendement ecrit.

**Provenance.**

- **Instruction recue de MASTER**, forme 3 de `squad/README.md` section 2 bis, *recu de MASTER, sans source
  dans le depot*, le 2026-10-06, citee mot pour mot : *"créée plusieurs instances de nadia pour couvrir le
  CE1"*. **Cet enonce ne fonde rien** : ni le nombre d'instances, ni une ligne, ni un tarif. Le nombre vient de
  `nadia-prof` (arbitrage prealable de s52, sections 5, 6 et 7 : deux instances, `PAR-2` non amende, borne a
  trois refusee sur la condition (i)).
- **Decisions de l'utilisatrice du 2026-10-06**, forme 2, citees par `DEC-0111` section 0 (le fichier et la
  section ou `camille-po` les a ecrites) : *"Retirer du catalogue (Recommandé)"* pour les deux paquets de
  sciences refuses, et *"Pratique depuis s41 (Recommandé)"* pour la lecture du plafond de capacite. Elles
  fondent le retrait et le chiffrage (8,0 engageables, un rejeu de 0,5 par instance), pas le reste.

## Stories retenues

| Story | Titre | Competence programme visee | Decision liee | Affectee a |
|---|---|---|---|---|
| `S52-RETRAIT-SCIENCES-CM1` | Retirer du catalogue servi les deux paquets de sciences CM1 refuses (hors capacite de `nadia-prof`, **premiere a fusionner**) | transverse, aucun contenu | `DEC-0111` s.3 | dev : marc-dev ; pas d'audit pedagogique |
| `S52-FRA-PRESENT` | Conjuguer au present (1,0) | francais / CE1 / `FRA-CE1-17` | `DEC-0111` s.4 | dev : marc-dev ; audit : nadia-prof instance A |
| `S52-FRA-SYNONYMES-CONTRAIRES` | Trouver un synonyme ou un contraire d'un mot frequent (1,0) | francais / CE1 / `FRA-CE1-25` | `DEC-0111` s.4 | dev : marc-dev ; audit : instance A |
| `S52-FRA-CATEGORIES` | Classer des mots par categorie et trouver le mot qui les designe tous (1,0) | francais / CE1 / `FRA-CE1-26` | `DEC-0111` s.4 | dev : marc-dev ; audit : instance A |
| Correction `TPS-CE1-R1` (backlog, "Story de correction differee - reserve `TPS-CE1-R1`") | Trois activites de "Quand ça se passe ?" servies du cote du stereotype (0,5, correction, pas un lot) | francais / CE1 / `FRA-CE1-20`, ligne deja `couvert-valide`, non rouverte | `DEC-0111` s.4 ; `PAR-10` | dev : marc-dev ; audit : instance A |
| Correction `ALP-CE1-R1` (backlog, "Story de correction differee - reserve `ALP-CE1-R1`") | Trois indices de "Comme dans le dictionnaire" (0,5, correction) | francais / CE1 / `FRA-CE1-23`, ligne deja `couvert-valide`, non rouverte | `DEC-0111` s.4 ; `PAR-10` | dev : marc-dev ; audit : instance A |
| `S51-MAT-COMPARER-RANGER` | Comparer, ranger et encadrer jusqu'a 1000, moitie textuelle (1,0, **ne ferme pas**) | mathematiques / CE1 / `MAT-CE1-02` | `DEC-0111` s.5 | dev : lea-dev ; audit : nadia-prof instance C |
| `S51-MAT-MULTIPLICATION` | Comprendre le sens de la multiplication (1,5 : 1,0 + 0,5 lot illustre, `REC-1`) | mathematiques / CE1 / `MAT-CE1-06` | `DEC-0111` s.5 | dev et dessins : lea-dev ; audit : instance C |
| `S51-MAT-SYMETRIE` | Reconnaitre un axe de symetrie et completer une figure (1,5) | mathematiques / CE1 / `MAT-CE1-17` | `DEC-0111` s.5 | **non engagee a la cloture de ce planning** ; si re-engagee : dev et dessins : lea-dev ; audit : instance C |

**Tarifs** (`DEC-0111` section 2, non rediscutes ici) : A = 1,0 + 1,0 + 1,0 + 0,5 + 0,5 = **4,0 engages**, 4,5 / 5,0
avec le rejeu nomme, trois lots. C engage a la cloture = 1,5 + 1,0 = **2,5**, 3,0 / 5,0 avec le rejeu, deux
lots ; avec `MAT-CE1-17` ce serait 4,0 et 4,5 / 5,0. Les corrections ne comptent pas comme lots (`DEC-0107`
section 4). Le retrait n'entre dans aucun des deux plafonds : son cout est donne par l'avis (section 3.1),
**deux fichiers, 12 lignes de catalogue et 8 chaines de test retirees, aucune ligne ajoutee**, et `theo-archi`
n'en tire pas d'unite de tarif (`DEC-0009` n'en posait pas non plus) ; je n'en invente pas. `PAR-1`, `PAR-2`,
`PAR-8` : verifies par `camille-po` ligne par ligne dans `DEC-0111` section 2 ; je ne les rejuge pas.
**Engage 6,5 a la cloture** sur les 8,0 de `DEC-0111` ; **1,5 libere** (`MAT-CE1-17`, destination s53 selon
`DEC-0111` 5.2). La passe transverse `PAR-4` n'est pas engagee (`DEC-0111` section 7, deuxieme report).

## Affectations nominatives (`PAR-16`)

| Agent | Taches | Couloir |
|---|---|---|
| **marc-dev** | `S52-RETRAIT-SCIENCES-CM1` **d'abord** ; puis, une fois le retrait fusionne, `TPS-CE1-R1`, `ALP-CE1-R1`, `S52-FRA-CATEGORIES`, `S52-FRA-PRESENT`, `S52-FRA-SYNONYMES-CONTRAIRES` dans cet ordre | retrait, puis instance A |
| **lea-dev** | `S51-MAT-COMPARER-RANGER`, `S51-MAT-MULTIPLICATION`, puis `S51-MAT-SYMETRIE` si elle est re-engagee. **Developpeuse du paquet et dessinatrice des dessins de C** (Q5 de l'avis) | instance C |
| **theo-archi** | A1 (message du cliquet de designation), A2bis (plancher de zone), reponse sur la forme du test d'absence du retrait si marc-dev la demande, `S51-OUTIL-JOURNAL-TOKENS` **affectee, non engagee** (`DEC-0110` 17.5), confirmation ArchUnit a la cloture | worktree propre, hors couloir |
| **ines-design** | Completer la section 4 de ses contraintes (quadrillage 6 x 6), mesurer le rendu des dessins de C a quatre largeurs quand lea-dev les livre. Ne dessine rien (`contrainte-ines-s52-...`, section 6) | worktree propre |
| **yanis-qa** | Lecture de testabilite des criteres des stories engagees, Q6, rapport qualite de cloture sur l'arbre fusionne (quatre jobs nommes), tirage de garde-fous mutes | cloture |
| **relecteur-pedagogique** | Relecture de fin de sprint des textes neufs et corriges, des fichiers de ceremonie et de l'amendement du README ; une ligne meme s'il ne trouve rien (amendement s38) | cloture |
| **nadia-prof** | Instance A (audit des cinq postes de A, reponses a Q1 a Q3) et instance C (audit des postes de C, reponses a Q4 et aux questions d'`ines-design`) | instances A et C |
| **camille-po** | Amendements de `DEC-0111` (F2, lecture de 5.2 (1), re-engagement eventuel), fiches de conformite des six jeux, noms d'affectation reportes au backlog si elle le juge utile | worktree propre |
| sofia-sm (casquette dev) | aucune | - |

Justification :

1. **Aucun developpeur sur deux worktrees d'instance en parallele.** marc-dev tient le couloir du retrait
   puis l'instance A : deux worktrees, **jamais ouverts en meme temps**, parce que A ne s'ouvre qu'apres la
   fusion du retrait. lea-dev ne tient que C. Je ne reproduis pas la derogation de s51 (`HOM-CE1-R1` livree par
   lea-dev au lieu de marc-dev, sans trace ; `review.md` de s51, obstacle 7) : aucune reaffectation de ce genre
   n'est prevue, et si elle a lieu elle entre au tableau d'obstacles de la review **le jour meme** (action A8
   de la retro de s51).
2. **lea-dev dessine C.** Les dessins de ce depot ont un seul precedent de bout en bout : `git log --format=%s
   -- adapters/adapter-web/src/main/resources/static/illustrations/` rend six commits `[LEA]`, deux `[INES]`,
   un `[SOF]`, **aucun `[MARC]`**. Elle a livre `6bad75e0` (s25, `DES-1` a `DES-7` verifiees sur douze
   dessins), `605038a1` (s34, la frise), `14a1b4e9` (s35, le plan de la classe, premier paquet de designation)
   et `5b72c213` (s39, `ce1-solides` : le paquet, ses tests et seize fichiers de dessin dans un seul commit,
   `git show --stat --format= 5b72c213 | grep -c '\.svg'` rend 16 ; ce que la regle de l'avis section 4.4 demande). C est le terrain neuf du sprint (`PAR-13`) ; je mets sur
   lui la developpeuse qui a deja livre un dessin et son item ensemble.
3. **Un seul ecrivain dans C : reponse a `DEC-0111` section 8 point 1 et a Q5.** Une branche, un worktree, un
   ecrivain pour les paquets et pour les dessins. L'avis (section 4.4) dit qu'un dessin et l'item qui le nomme
   arrivent dans le meme commit parce que `LIllustrationDeContenuResoutEtAucuneNeDortTest` juge les deux sens ;
   avec un seul ecrivain la regle est tenue par construction de la branche, et les cliquets de poids et
   d'illustration sont poses une seule fois, par la meme main, sur le meme arbre. Deux worktrees pour C auraient
   fait deux branches qui montent les memes constantes. **Le prix, ecrit** : C est le couloir le plus long
   (au plus trois paquets, au moins dix dessins de symetrie si elle est engagee, six a huit reseaux de points),
   porte par une seule personne.
4. **marc-dev tient le francais CE1 et le retrait.** Il a livre `224803e0` (`FRA-CE1-01`), `fc6f9c16` et
   `15efbcca` (`FRA-CE1-02`), `a7af90b3` (`FRA-CE1-16`, les homophones) : il connait les sept conditions
   transverses et les bornes de lisibilite. Le retrait ne demande aucun dessin et ne demande pas de passer
   devant `nadia-prof` : c'est la premiere tache, la plus courte, et A ne peut pas s'ouvrir avant. **Ce que ce
   choix coute** : `TPS-CE1-R1` et `ALP-CE1-R1` corrigent des paquets de lea-dev (`24430ed3`, `e25a1cd3`), pas
   les siens ; il lit leurs tests de paquet avant d'editer.
5. **Charge.** A (cinq postes, YAML et tests, aucun dessin, aucun cliquet) est plus leger que C. Si lea-dev
   devient le chemin critique, je peux reaffecter `S51-MAT-COMPARER-RANGER` a marc-dev, **sous trois
   conditions** : lea-dev ne l'a pas commencee, marc-dev n'a plus aucun poste de A en cours (pas de deux
   worktrees d'instance en parallele), et l'entree au tableau d'obstacles est ecrite le jour meme. Decision de
   capacite, mon role ; elle n'est pas prise ici.
6. **Conflit de fusion previsible, que j'assume.** A, C et le retrait editent les memes listes :
   `catalogue.yaml`, `TexteAccentueExposeParLePortEntrantTest`, et, pour A et C seulement,
   `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (avis, section 4.4). A s51 deux couloirs les ont
   fusionnes sans perte. Section "Ordre de fusion".

### Ecriture commune a chaque paquet neuf (avis, sections 1.3 et 1.4)

Reportee dans la consigne de marc-dev (trois paquets de A) et de lea-dev (trois paquets de C). Chaque paquet
neuf inscrit, **dans le meme commit que son contenu** : une entree de `catalogue.yaml` ; une entree de
`PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` avec son motif (**ecrit apres le paquet** : un nombre ecrit dans un motif est
compare au paquet par `chaqueCompteEcritDansUnMotifVautLeCompteMesureSurLePaquet`) ; ses chaines dans
l'inventaire de `TexteAccentueExposeParLePortEntrantTest` ; une espace insecable devant `?`, `!`, `:`, `;` dans les
items **et** dans le titre et la consigne de l'entree de catalogue. Pieges mesures : drapeau
`UNICODE_CHARACTER_CLASS` sur les expressions regulieres ; la regle de longueur `TRV-CE1-2` (bonne reponse
strictement la plus longue dans la moitie des items au plus) n'a aucun garde-fou transverse, elle s'asserte dans
le test de paquet. Specifiques : `S52-FRA-PRESENT` evite *alle* et *alles* tant que Q1 n'a pas de reponse
(l'avis, section 1.2 : le garde-fou d'accents les refuse) ; `S52-FRA-SYNONYMES-CONTRAIRES` attend Q2 pour la
redaction de sa question ; les homographes *mur*/*sur*/*pres*/*marche* passent sans accent le garde-fou : a la
lecture de `nadia-prof` (Q3).

## Conditions de l'instance C (`DEC-0111` section 5.2), verifiees a la cloture de ce planning

Je verifie physiquement chaque condition, sur le disque, a 20:51. **Une condition ecrite comme attendue n'est
pas une condition remplie** (README section 9, etape 7).

| Poste | Condition (`DEC-0111` 5.2) | Preuve lue | Etat a la cloture |
|---|---|---|---|
| `MAT-CE1-02` moitie textuelle | aucune | avis, V9 : *"faisable en l'etat"* | **engagee** |
| `MAT-CE1-06` | (2) contraintes d'`ines-design` pour les reseaux de points ; (3) un developpeur affecte au dessin ; A2 non requis | (2) `squad/design/contrainte-ines-s52-...`, section 3 (dimensions, point, ecart, palette, poids, exemple, refus), commite en `7350fb07` ; (3) lea-dev, section "Affectations" de ce planning | **engagee** : (2) et (3) remplies |
| `MAT-CE1-17` (1) | `ZON-13` en garde-fou et rang de la bonne zone en troisieme clause du garde-fou 4 | `edf15763` ; avis section 4.1 : verts a `7350fb07` | **remplie** |
| `MAT-CE1-17` (1) | **et le message** du test de plafond de designation **corrige dans le meme diff** | `AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest` dit encore *"Etendre le balayage de production"* (lecture a `93aade92`) ; `edf15763` ne le touche pas ; `ADR-0080` A2 section 3 renvoie la correction *"au plus tard dans le diff qui monte ce cliquet de 2 a 3"* | **non remplie** ; et la lettre *"dans le meme diff"* ne peut plus l'etre, `edf15763` etant commite sans elle (ecart R3) |
| `MAT-CE1-17` (2) | contraintes de dessin : figure a lignes candidates, **quadrillage 6 x 6**, **legende a 36 libelles** | figures a lignes candidates : section 2 complete. Quadrillage : section 4, titre *"ce que ce document ne couvre qu'en esquisse"*, mesure de la legende *"non faite"*, remplacee par une proposition routee a `nadia-prof` | **non remplie** : deux des trois elements ne sont pas ecrits au sens de la condition |
| `MAT-CE1-17` (3) | un developpeur affecte au dessin | lea-dev, ce planning | **remplie** |

**Si une condition de C manque a la cloture du planning, `DEC-0111` section 5.2 dit : "non engage, budget
libere"** (`PAR-16`), 1,5 libere par poste, destination s53, et `MAT-CE1-17` passe de neuf a dix sprints de
report. Deux conditions de `MAT-CE1-17` manquent. **`MAT-CE1-17` est donc non engage, budget libere, 1,5,
destination s53, a la cloture de ce planning.** `MAT-CE1-06` et `MAT-CE1-02` sont engages, et
`MAT-CE1-06` s'ouvre sans A2 comme la `DEC` le prevoit.

**Ce que je ne fais pas** : je ne re-engage pas `MAT-CE1-17` moi-meme. Ses 1,5 sont libres pour s53 ; les depenser
a s52 est une decision produit, et c'est `camille-po` qui a ecrit la condition et son issue. **Je ne juge pas non
plus** si l'esquisse du quadrillage suffit pedagogiquement (Q4 de l'avis, adressee a `nadia-prof` et a moi) :
mon constat de capacite est que la condition (2), lue a la lettre, n'est pas etablie, donc je ne l'engage pas.

**Qui debloque quoi, dans quel ordre** (U1 a U3 se font en parallele, U4 les suit) :

| # | Qui | Quoi | Fichier attendu | Avant |
|---|---|---|---|---|
| U1 | `theo-archi` | Corriger le message du cliquet (A1) et amender `ADR-0080` A2 section 4 : plancher de zone 16 unites et non 13 (A2bis). **Un seul commit-lieu : `round-two`, via son worktree propre, fusionne par moi. Jamais aussi sur la branche de C** (A1 de `ADR-0065` a ete commite deux fois a s51, obstacle 4 de la review) | `ADR-0080` amende, test corrige, sur `round-two` | l'ouverture du worktree de C |
| U2 | `ines-design` | Ecrire la section 4 (quadrillage 6 x 6) comme les sections 2 et 3 : mesures faites, cases candidates, legende ou son remplacement | contrainte d'`ines-design` completee, sur `round-two` | la decision de `camille-po` |
| U3 | `nadia-prof` instance C | Repondre aux questions d'`ines-design` (section 5 de son document, "Pour `nadia-prof`") et a Q4 : onglet au bout de la ligne ou zone sur la ligne ; cases candidates du quadrillage, tout ou sous-ensemble ; leurre loyal ; texte alternatif ; l'esquisse suffit-elle | reponse ecrite dans le worktree de C | la decision de `camille-po` |
| U4 | `camille-po` | Quand U1, U2 et U3 existent **physiquement** (je le lui confirme), amender `DEC-0111` : re-engager ou non `MAT-CE1-17`, **lire la condition (1) a la lettre de `ADR-0080` A2 section 3** (ecart R3), dire si le report s'arrete a neuf | amendement de `DEC-0111` | le premier commit du paquet de symetrie |

lea-dev commence par `S51-MAT-COMPARER-RANGER` et `S51-MAT-MULTIPLICATION` : U1 a U4 se jouent pendant son
travail, pas devant lui. Elle ne commence `S51-MAT-SYMETRIE` que sur l'amendement U4 et apres `S51-MAT-MULTIPLICATION`.
**Si U4 manque a la cloture du sprint, `MAT-CE1-17` ferme le sprint non engagee et le dit la review.**

## Avis de faisabilite - theo-archi

Source : `squad/sprints/s52-deux-instances-ce1-francais-maths/avis-faisabilite.md` (non modifie). Mesure :
reacteur complet a `7350fb07`, **2082 tests, 0 echec**, `BUILD SUCCESS`, JDK 21.0.10 rapporte par la machine
virtuelle des tests, **job `build` seul** (avis, section 0). Ce chiffre est la base d'un autre agent : je le
remesurerai moi-meme sur l'arbre fusionne a la review, je ne le relaie pas. **Aucune story ne touche
`src/main/java`, aucune n'appelle de regle ArchUnit** : le compte d'`ArchRule` est inchange par s52.

| Story | Avis | ADR requis |
|---|---|---|
| `S52-RETRAIT-SCIENCES-CM1` (V1) | faisable en l'etat, deux fichiers ; **ne doit pas toucher** `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (avis 3.3, ecart F2) | non |
| `S52-FRA-PRESENT` (V2) | faisable en l'etat, sans *alle* ni *alles* | non, sauf si Q1 rend ces formes requises |
| `S52-FRA-SYNONYMES-CONTRAIRES` (V3) | faisable en l'etat ; homographes sans accent invisibles au garde-fou | non |
| `S52-FRA-CATEGORIES` (V4) | faisable en l'etat ; *marche* invisible au garde-fou | non |
| `TPS-CE1-R1` (V5), `ALP-CE1-R1` (V6) | faisables en l'etat, YAML seul | non |
| `S51-MAT-SYMETRIE` (V7) | faisable sous condition : materiau, dessinateur, message du cliquet ; zones a 296 px | non : A2 est commite ; A1 et A2bis a ecrire |
| `S51-MAT-MULTIPLICATION` (V8) | faisable sous condition du materiau et du dessinateur ; un `<circle>` par point, pas un chemin unique (avis 4.2) | non |
| `S51-MAT-COMPARER-RANGER` (V9) | faisable en l'etat | non |

## Isolation (`DEC-0111` section 8, `squad/README.md` section 9, reserve de `nadia-prof` du 2026-10-06)

**Pourquoi cette section est la plus longue.** s50 a ecrase un fichier entre deux instances (forme (c)) ; s51 a
tenu avec un sous-repertoire par couloir. `nadia-prof` (arbitrage de s52, section 2.3) demande un
sous-repertoire **par agent**, aucune instance a la racine, un port par agent. A s51, `marc-dev` et `nadia-prof`
B ont partage un fichier de cookies dans `instance-b` a 32 minutes d'ecart : sans effet, mais le geste de s50 en
petit.

| Couloir | Branche | Worktree (dossier voisin du checkout) | Agents qui y ecrivent | Scratchpad, un par agent |
|---|---|---|---|---|
| Retrait | `s52-retrait-sciences-cm1` | `../recreo-s52-retrait-sciences-cm1` | marc-dev | `<scratchpad>/retrait/marc-dev` |
| A | `s52-nadia-instance-a` | `../recreo-s52-nadia-instance-a` | marc-dev (paquets), nadia-prof A (fiches, reponses, verdicts) | `<scratchpad>/instance-a/marc-dev`, `<scratchpad>/instance-a/nadia` |
| C | `s52-nadia-instance-c` | `../recreo-s52-nadia-instance-c` | lea-dev (paquets et dessins), nadia-prof C (fiches, reponses, verdicts) | `<scratchpad>/instance-c/lea-dev`, `<scratchpad>/instance-c/nadia` |
| `theo-archi` | `s52-theo-a1-a2bis` | `../recreo-s52-theo-a1-a2bis` | theo-archi | `<scratchpad>/theo/theo-archi` |
| `ines-design` | `s52-ines-contraintes` | `../recreo-s52-ines-contraintes` | ines-design | `<scratchpad>/ines/ines-design` |
| `camille-po` | `s52-camille-amendements` | `../recreo-s52-camille-amendements` | camille-po | `<scratchpad>/camille/camille-po` |

`<scratchpad>` designe le repertoire temporaire de la session de s52 (aucun chemin de poste n'est ecrit ici). Les
worktrees de `yanis-qa` et du `relecteur-pedagogique` sont ouverts par moi a la cloture, sur l'arbre fusionne
(README section 9, amendement s19 : une relecture ne se lance que sur un arbre qui porte ce qu'elle lit).

**Ports** (un par agent, plages disjointes et distinctes de celles de s51, a verifier libres avant le lancement) :
marc-dev 18810-18829, nadia-prof A 18830-18849, lea-dev 18850-18869, nadia-prof C 18870-18889, ines-design
18890-18909, theo-archi 18910-18929, yanis-qa 18930-18949.

**Trois regles de couture, ecrites avant qu'un incident les rende utiles.**

1. **Le developpeur et l'auditrice d'un couloir partagent le worktree et la branche, comme a s51, et jamais le
   scratchpad ni le port.** nadia-prof joue le jar de ce worktree : le developpeur lui donne le sha livre,
   n'y lance ni `clean` ni `package` tant que son serveur tourne, et elle annonce l'arret du sien.
   Deux agents commitent sur la meme branche : jamais en meme temps (un message de retour entre les deux).
2. **Un fichier ecrit ailleurs se lit avec `git show <branche>:<chemin>`** (les objets sont communs aux
   worktrees), jamais en le copiant dans le scratchpad d'un autre agent. Les contraintes d'`ines-design`
   et les amendements de `theo-archi` et de `camille-po` arrivent par `round-two` ; les reponses de
   `nadia-prof` restent dans le worktree de son couloir.
3. **Une collision constatee s'ecrit** (fiche, ou message de retour) : l'incident vu et non ecrit n'a pas eu
   lieu pour le depot.

**Fichiers partages entre couloirs** : `catalogue.yaml`, `TexteAccentueExposeParLePortEntrantTest` (retrait, A,
C), `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (A et C). Ce sont des listes : on garde les ajouts
des deux cotes. **Forme (a)** : aucune story de s52 ne modifie un paquet et sa consigne dans le meme sprint ; le
retrait retire une entree sans toucher un fichier de paquet. `TPS-CE1-R1` et `ALP-CE1-R1` ne touchent que le
YAML du paquet, pas la consigne de son entree de catalogue. `REGISTRE-identifiants.md` : aucun identifiant neuf
n'est attendu (`DEC-0111` section 11).

## Sequence de demarrage

L'orchestrateur lance les agents ; je n'en lance aucun. Les commandes d'ouverture sont de la forme
`git worktree add ../<dossier> -b <branche> round-two`, suivies de `bash outils/verifier-lignee.sh` dans le
worktree. **Je verifie avant chaque ouverture que le commit qui porte ce que l'agent doit lire est deja sur
`round-two`** (README section 9, amendement s40) : ce planning l'est des son commit.

1. **Le retrait, en premier.** Ouvrir le couloir du retrait et lancer marc-dev (consigne B1). Rien d'autre ne
   le precede : un enfant peut encore jouer les deux paquets refuses tant qu'il n'est pas fusionne (`DEC-0111`
   section 3). Il est fusionne **des qu'il est livre et mesure**, sans attendre le reste du sprint.
2. **Pendant le retrait, en parallele** : ouvrir et lancer `theo-archi` (U1, consigne B6), `ines-design` (U2,
   B7), `camille-po` (F2, fiches, B8). Aucun ne touche le worktree du retrait.
3. **Retrait fusionne** : je verifie sa lignee, je lance `bash outils/mvn-jdk21.sh -B clean verify` sur l'arbre
   fusionne et je mesure le compte moi-meme. **Puis j'ouvre A** depuis le tip et je lance marc-dev (B2) et
   nadia-prof instance A (B3).
4. **U1 fusionne** (`ADR-0080` amende, message du cliquet corrige, sur `round-two`) : **puis j'ouvre C** depuis ce
   tip, de sorte que la branche de C contienne A1 sans le recommitter, et je lance lea-dev (B4) et nadia-prof
   instance C (B5). **Si U1 manque quand le retrait est fusionne, C attend** : lea-dev ne commence pas sur une
   base sans A1, et c'est le prix de ne pas commiter A1 deux fois. A s'ouvre sans l'attendre.
5. **marc-dev, instance A** : `TPS-CE1-R1`, `ALP-CE1-R1` (YAML seul, premier audit rapide), puis
   `S52-FRA-CATEGORIES`, `S52-FRA-PRESENT`, et **en dernier** `S52-FRA-SYNONYMES-CONTRAIRES`, pour laisser a Q2 le
   temps d'une reponse. Chaque livraison : message avec le sha, nadia-prof A joue.
6. **lea-dev, instance C** : `S51-MAT-COMPARER-RANGER` (aucune condition, premier audit), `S51-MAT-MULTIPLICATION`,
   puis `S51-MAT-SYMETRIE` seulement sur U4.
7. **nadia-prof, instances A et C** : audit paquet par paquet dans leur worktree, fiches de conformite, verdicts.
   Un refus reste un refus.
8. **Daily** : je demande a chaque agent de confirmer que son premier commit est dans son worktree dedie
   (README section 3, amendement s23). Je classe et je route, je n'ecris aucun fichier.
9. **Cloture** : fusion, `yanis-qa` (quatre jobs nommes), `theo-archi` (ArchUnit), `relecteur-pedagogique`,
   `review.md`, `RAPPORT.md`, `python outils/rapport-tokens.py --sprint s52`, `retro.md`.

### Ordre de fusion

**Retrait, puis A, puis C**, chaque fusion suivie de `bash outils/mvn-jdk21.sh -B clean verify` sur le commit de
fusion, **avant** de fusionner la suivante. Pourquoi cet ordre : le retrait en premier est exige par `DEC-0111`
et par l'avis (4.4) ; A avant C parce que A ne porte aucun cliquet exact et que C en porte plusieurs (plafond
de designation, dette de lisibilite, `DESSINS_DE_DESIGNATION`, poids total, items illustres, fichiers
d'illustration, avis section 4.3) : la derniere fusion est celle qui est jugee par le reacteur complet avec tout
le contenu des deux autres.

- **Tout total exact est la valeur que le reacteur complet mesure sur l'arbre fusionne, jamais la somme des
  valeurs de deux branches** (avis, section 4.4). Motifs : `POIDS_D_UN_DESSIN_MAXIMUM` est un maximum, un fichier
  touche des deux cotes compte une fois, et des constantes differentes fusionnent sans conflit textuel alors que
  chacune a ete mesuree sur un arbre qui manquait le materiau de l'autre. Les clauses d'egalite rougissent sur
  l'arbre fusionne si la valeur est fausse ; elles ne sont jamais relachees.
- **Conflit probable sur un total exact** (un cliquet, ou le compte de tests que chaque agent annonce) : je prends
  la mesure et je dis d'ou vient l'ecart avec chaque annonce. Aucun compte annonce par un agent ne correspond au
  total apres fusion : chacun mesure la base plus son propre lot.
- **Je ne resous jamais un conflit en affaiblissant une assertion.** Si deux tests se contredisent apres fusion,
  l'un des deux dit quelque chose de faux, et je trouve lequel.
- **Lignee** : avant chaque fusion, `bash outils/verifier-lignee.sh` dans le worktree du couloir et `git
  merge-base --is-ancestor round-two <branche>` ; l'agent dit l'avoir fait, c'est moi qui reponds de ce que je
  fusionne.
- Si C livre en deux temps (`MAT-CE1-02` et `MAT-CE1-06`, puis `MAT-CE1-17` apres U4), la seconde fusion
  remesure les cliquets de la meme facon.

## Consignes de lancement (copiables)

Coller le **bloc commun**, puis le bloc de l'agent. `<scratchpad>` est le repertoire temporaire de la session ;
l'orchestrateur le remplace par sa valeur avant le lancement.

```
BLOC COMMUN - s52 (a lire avant tout ecrit)

1. Worktree dedie : tu travailles dans le worktree nomme dans ta consigne, ouvert avant ton premier ecrit.
   Avant ton premier commit, lance `bash outils/verifier-lignee.sh` dans ce worktree. S'il echoue, tu
   t'arretes et tu le dis.
2. Branche dediee : celle de ta consigne. Tu ne commites ni sur round-two ni sur une autre branche.
3. Scratchpad dedie, PAR AGENT : `<scratchpad>/<couloir>/<ton-nom>`, cree par toi au lancement. Tout fichier
   de travail (jouer.py, cookies, journaux de mesure) y vit. Rien a la racine de `<scratchpad>`, rien dans le
   sous-repertoire d'un autre agent, y compris dans ton propre couloir.
4. Serveur de jeu : lance depuis le `bootstrap/target/recreo.jar` de TON worktree (`java -jar`), jamais depuis
   un autre checkout, sur un port de ta plage, verifie libre avant le lancement. Tu ecris ce port dans ton
   message de retour. Plages : marc-dev 18810-18829, nadia-prof instance A 18830-18849, lea-dev 18850-18869,
   nadia-prof instance C 18870-18889, ines-design 18890-18909, theo-archi 18910-18929, yanis-qa 18930-18949.
5. Registre : toute ecriture dans `squad/decisions/REGISTRE-identifiants.md` se fait depuis le worktree de ton
   couloir. Aucun identifiant neuf n'est attendu a s52 ; si tu en alloues un, tu le dis.
6. Mesures : toute mesure du reacteur, complete ou partielle, se fait depuis ton worktree. Toute mesure
   partielle porte `-am`. Si elle touche catalogue.yaml, un fichier de contenu ou le registre, elle inclut
   bootstrap : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,bootstrap -am test`. Tout chiffre que
   tu cites porte sa commande et son perimetre ; un chiffre relaye n'est pas un chiffre mesure.
7. Processus : tu n'arretes jamais un processus par nom d'image ou de programme. Par PID, apres avoir verifie
   son identite. Tu arretes ton propre serveur a la fin de ton travail.
8. Collision : si un fichier, un port ou un repertoire que tu utilisais a ete touche par un autre agent, tu
   l'ecris dans ton message de retour (dans la fiche pour nadia-prof). Un incident vu et non ecrit n'a pas eu lieu.
9. Avant chaque commit, lance sur ton diff les quatre jobs de .github/workflows/ci.yml et nomme dans ton
   retour ceux que tu as lances, sans deduire les autres :
   - prefixes : `git log --no-merges --format=%s round-two..HEAD | bash .github/scripts/verifier-prefixes.sh`
   - chemins : `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh`
   - outils : `for t in outils/*-test.sh; do bash "$t"; done` (et `outils/*-test.py` avec python)
   - build : `bash outils/mvn-jdk21.sh -B clean verify` sur la racine
10. Commits : prefixe `[TAG]` de ton agent, sujet en ASCII ; trailer `Co-Authored-By` = le modele que tu
    executes reellement (squad/README.md section 8). Ni push, ni rebase, ni reset, ni fusion : la fusion est a
    sofia-sm. Aucun secret, aucun chemin de poste de travail, aucun nom d'utilisateur dans un fichier commite.
11. Un fichier ecrit par un autre agent hors de ton worktree se lit avec `git show <branche>:<chemin>`.
12. Contenu servi a l'enfant : redige integralement accentue des le premier brouillon (DEC-0002).
13. Si ton travail s'arrete sur une erreur d'infrastructure, ton worktree reste intact : tu le dis, tu ne le
    jettes pas.
```

```
B1 - marc-dev - S52-RETRAIT-SCIENCES-CM1 (premiere story a fusionner)

Worktree ../recreo-s52-retrait-sciences-cm1, branche s52-retrait-sciences-cm1, scratchpad
<scratchpad>/retrait/marc-dev. Tag [MARC].

Lis : DEC-0111 section 3 ; docs/backlog/backlog.md, story "S52-RETRAIT-SCIENCES-CM1" ;
squad/sprints/s52-deux-instances-ce1-francais-maths/avis-faisabilite.md sections 3 et 6 ; ADR-0061.

Fais : retire de adapters/adapter-content/src/main/resources/catalogue.yaml les deux entrees
bandit-manchot-activite-humaine-cm1 et bandit-manchot-terre-cm1 ; retire de
TexteAccentueExposeParLePortEntrantTest les 8 chaines orphelines et les commentaires qui les annoncent
(deux identifiants, deux titres, deux consignes, deux competences ; le domaine "La planète Terre. Les êtres
vivants dans leur environnement" reste).

Ne touche pas : contenu/cm1-sciences-activite-humaine.yaml, contenu/cm1-sciences-terre.yaml,
PaquetCm1SciencesActiviteHumaineTest, PaquetCm1SciencesTerreTest, les deux fiches de conformite, ni
LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest. L'avis (section 3.3) a mesure que retirer ses deux
entrees le rend rouge ("ni juges ni declares") ; la story et DEC-0111 section 8 point 2 disent l'inverse, ecart
route a camille-po. Tu suis la mesure de l'avis. Si TA mesure la dement, tu le dis dans ton retour.

Test : les deux premiers criteres de la story sont "rouges sur l'existant". Ecris dans le meme commit le test
qui l'est (les deux identifiants absents du catalogue servi, jamais une constante qui recopie l'association
entree-paquet : ADR-0061). Si tu ne trouves pas de forme qui tienne cette clause, tu t'arretes et tu le dis :
theo-archi tranche, pas toi.

Mesure : reacteur complet depuis ton worktree. L'avis annonce 2082 tests a 7350fb07 et 1 echec sous le retrait
seul : c'est une base, pas ton resultat. Rends : le sha, les jobs de la CI que tu as lances (bloc commun 9),
le total que TU as mesure avec sa commande, et ce que tu n'as pas pu verifier.
Des que ton retour est la, tu n'ouvres rien d'autre : A s'ouvre apres la fusion du retrait.
```

```
B2 - marc-dev - instance A, francais CE1 (apres la fusion du retrait)

Worktree ../recreo-s52-nadia-instance-a, branche s52-nadia-instance-a, scratchpad
<scratchpad>/instance-a/marc-dev. Tag [MARC]. nadia-prof instance A partage ce worktree et cette branche, pas
ton scratchpad ni ton port : donne-lui le sha livre, ne lance ni clean ni package tant que son serveur tourne.

Ordre : (1) TPS-CE1-R1, (2) ALP-CE1-R1 (YAML seul, paquets de lea-dev : lis leurs tests avant d'editer ; la
consigne de catalogue n'est pas touchee), (3) S52-FRA-CATEGORIES, (4) S52-FRA-PRESENT, (5) S52-FRA-SYNONYMES-CONTRAIRES
en dernier (Q2, reponse de nadia-prof attendue).

Lis : DEC-0111 section 4 ; les stories dans docs/backlog/backlog.md ; l'avis de faisabilite sections 1 et 2 ;
ARBITRAGE-PREALABLE-s48-francais-ce1.md (conditions TRV-CE1-1 a 7, LON-CE1-1, 2, 5 et celles de ta ligne). Les
conditions de nadia-prof sont citees par identifiant dans chaque critere : tu les lis dans l'arbitrage.

Ecriture commune a chaque paquet neuf, dans le meme commit que son contenu : entree de catalogue.yaml ; entree de
PAQUETS_HORS_DU_DOMAINE_NUMERIQUE avec motif (ecrit apres le paquet, les comptes sont verifies) ; chaines dans
l'inventaire de TexteAccentueExposeParLePortEntrantTest ; espace insecable devant ? ! : ; dans les items ET dans
le titre et la consigne de l'entree de catalogue ; drapeau UNICODE_CHARACTER_CLASS sur tes expressions
regulieres ; la regle TRV-CE1-2 (bonne reponse strictement la plus longue dans la moitie des items au plus)
s'asserte dans ton test de paquet. Tes tests sont dans le meme commit que ton code.
Pas de texteSupport, pas d'illustration, pas de voix haute (TRV-CE1-1).

Specifiques : FRA-CE1-17 sans "alle" ni "alles" tant que Q1 n'a pas de reponse ecrite de nadia-prof (le
garde-fou d'accents les refuse). FRA-CE1-25 : la redaction de la question attend Q2 ("synonyme" est-il un mot
que l'enfant lit seul). Les homographes mur, sur, pres, marche passent sans accent le garde-fou : tu ne t'y fies
pas, tu les montres a nadia-prof (Q3).
Avant de soumettre une correction : reprends verbatim toute formulation que nadia-prof a ecrite comme forme
acceptee, et cherche dans le fichier le mot de la bonne reponse que tu viens de placer dans un indice
(README section 9, amendement s47).
ALP-CE1-R1 : le critere "aucun item dont la decision se fait a la deuxieme lettre ne porte un indice qui
s'arrete a la premiere" s'ecrit dans le test de paquet et doit etre rouge sur l'existant.

Aucune reaffectation sans entree au tableau d'obstacles : si tu bloques, tu le dis a sofia-sm.
Rends a chaque livraison : sha, jobs lances, total mesure avec commande et perimetre.
```

```
B3 - nadia-prof - instance A, francais CE1

Worktree ../recreo-s52-nadia-instance-a, branche s52-nadia-instance-a, scratchpad
<scratchpad>/instance-a/nadia. Tag [NAD]. marc-dev ecrit dans le meme worktree et la meme branche, pas dans
ton scratchpad : tu joues le jar de ce worktree sur ton port, sur le sha qu'il te donne.

Audit : S52-FRA-CATEGORIES, S52-FRA-PRESENT, S52-FRA-SYNONYMES-CONTRAIRES (verdict et fiche chacune), et la
levee de TPS-CE1-R1 et ALP-CE1-R1 sans rouvrir les lignes FRA-CE1-20 et FRA-CE1-23. Tu rejoues le paquet entier
a chaque passage. Done pedagogique a toi seule ; MNC-1 dans tout Done VALIDE ou VALIDE SOUS RESERVE. Un audit
non joue se declare degrade.

Questions de l'avis de faisabilite (section 5), a repondre par ecrit dans ce worktree avant que marc-dev
n'arrive au paquet concerne : Q1 (formes regularisees d'"aller" au singulier : requises ? si oui, amendement
d'ADR-0065 de la forme d'A1, par theo-archi), Q2 (le mot "synonyme" dans un enonce de FRA-CE1-25), Q3
(homographes mur, sur, pres, marche).

Isolation : sous-repertoire propre, aucun fichier a la racine de <scratchpad>, un port de ta plage, le jar de
ce worktree. Une collision constatee s'ecrit dans la fiche.
Hors de ce couloir et sans budget : A2 de la retro de s51 (ligne 89 de
ARBITRAGE-PREALABLE-s51-fra-ce1-22-graphie-cedille.md a 162 caracteres), reponse a C5 clause 1 d'ADR-0104,
texte de PAR-9 (DEC-0111 section 7) : tu les traites quand tu veux, pas devant un audit.
```

```
B4 - lea-dev - instance C, mathematiques CE1 (apres la fusion du retrait ET de U1)

Worktree ../recreo-s52-nadia-instance-c, branche s52-nadia-instance-c, scratchpad
<scratchpad>/instance-c/lea-dev. Tag [LEA]. Tu es la seule ecrivaine de paquets et de dessins de ce couloir :
un dessin et l'item qui le nomme arrivent dans le meme commit (avis section 4.4), les cliquets de poids et
d'illustration sont poses une seule fois. nadia-prof instance C partage ton worktree et ta branche, pas ton
scratchpad ni ton port : donne-lui le sha livre, ne lance ni clean ni package tant que son serveur tourne.

Ordre : (1) S51-MAT-COMPARER-RANGER, (2) S51-MAT-MULTIPLICATION, (3) S51-MAT-SYMETRIE UNIQUEMENT si
camille-po a amende DEC-0111 pour la re-engager (sofia-sm te le dit). Avant le premier commit de (3), verifie
que le message du test de plafond de designation ne demande plus d'"Etendre le balayage de production" : s'il
le demande encore, tu t'arretes.

Lis : DEC-0111 section 5 ; les trois stories (backlog, section "Sprint 51", tarifs de s52) ; l'avis sections 4.1
a 4.4 (cliquets a monter, un par un, avec le fichier de chacun) ; les contraintes d'ines-design :
git show round-two:squad/design/contrainte-ines-s52-dessins-de-l-instance-c-mathematiques-ce1.md ;
ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md et -lot-3.md (SYM-CE1, MUL-CE1, ORD-CE1) ; ARBITRAGE-PREALABLE-s52-instances-ce1.md.

Dessins de reseaux de points (MAT-CE1-06) : un <circle> par point, jamais un chemin unique (avis 4.2) ; pas de
10 x 10 tant que la reponse ecrite sur la densite et le poids n'existe pas : commence par des reseaux de 5 x 7
au plus. Un dessin plus lourd que 4 633 octets deplace POIDS_D_UN_DESSIN_MAXIMUM avec sa valeur mesuree et son
motif (ADR-0036 clause 5).
Dessins de symetrie (MAT-CE1-17, si engagee) : plancher de zone = 16 unites sur un cadre de vue de 100 (ines-design,
296 px a 320 px de fenetre), PAS 13 comme ADR-0080 A2 section 4 jusqu'a A2bis ; un dessin par item ;
nombreDeManches 8 ; zones de meme taille dans un item ; controles geometriques de la section 2.5 des contraintes
en script de poste dans ton scratchpad, non commite.
Cliquets : lis chaque clause d'egalite dans le code, pas dans la paraphrase de l'avis. Une mesure partielle
touchant catalogue ou contenu inclut bootstrap (bloc commun 6).
Ecriture commune a chaque paquet neuf : voir B2 (catalogue, PAQUETS_HORS_DU_DOMAINE_NUMERIQUE ou
PAQUETS_A_ITEMS_ECARTES si le paquet est mixte, inventaire, espaces insecables, SousPartieDeMathematiquesCe1
pour la valeur de sous-partie). La mention de couverture est la matiere de nadia-prof et de camille-po, pas la tienne.
Rends a chaque livraison : sha, jobs lances, total mesure avec commande et perimetre, valeur de chaque cliquet
deplace avec sa commande de mesure.
```

```
B5 - nadia-prof - instance C, mathematiques CE1

Worktree ../recreo-s52-nadia-instance-c, branche s52-nadia-instance-c, scratchpad
<scratchpad>/instance-c/nadia. Tag [NAD]. lea-dev ecrit dans le meme worktree et la meme branche, pas dans ton
scratchpad : tu joues le jar de ce worktree sur ton port, sur le sha qu'elle te donne.

Audit : S51-MAT-COMPARER-RANGER (la ligne passe a "en cours", ne ferme pas, mention de ORD-CE1-5 mot pour mot),
S51-MAT-MULTIPLICATION (MUL-CE1-7 : la mention depend des dessins), et S51-MAT-SYMETRIE si DEC-0111 est amendee
pour la re-engager (mention de couverture fixee d'avance, arbitrage de s43 lot 3 section 3). Tu lis les
dessins a leur taille de rendu, zones masquees (ZON-9, MUL-CE1-5) : cette propriete n'est tenue que par toi.
Done pedagogique a toi seule ; MNC-1 dans tout Done VALIDE ou VALIDE SOUS RESERVE.

Reponses ecrites dans ce worktree (U3) : Q4 de l'avis (le quadrillage 6 x 6, en esquisse dans les contraintes
d'ines-design, suffit-il a engager les items de completion ?) et les questions de la section 5 de ses contraintes,
"Pour nadia-prof" : onglet au bout de la ligne ou zone sur la ligne, texte alternatif, leurre loyal et cases
candidates du quadrillage, densite d'un reseau de points. Elles ne sont pas bloquantes pour MAT-CE1-02 ni
MAT-CE1-06 sauf la densite.
Trois agents ecrivent dans ce worktree (lea-dev, toi, et tes reponses lisibles par ines-design via git show) :
sous-repertoire propre, un port de ta plage, une collision constatee s'ecrit dans la fiche.
```

```
B6 - theo-archi - U1 (A1 et A2bis), hors couloir

Worktree ../recreo-s52-theo-a1-a2bis, branche s52-theo-a1-a2bis, scratchpad <scratchpad>/theo/theo-archi.
Tag [THEO]. Tu commites UNE fois : ta correction arrive sur round-two par moi, elle n'est jamais aussi
commitee sur la branche d'un couloir (A1 d'ADR-0065 l'a ete deux fois a s51).

Fais : (A1) corrige le message de AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest, qui
demande encore d'"Etendre le balayage de production" ; ne monte pas le plafond (2 reste 2 : il sera monte avec
le premier paquet de MAT-CE1-17). (A2bis) amende ADR-0080 A2 section 4 : plancher de zone derive de la largeur
servie la plus etroite, 296 px (ines-design, 1.2 de ses contraintes), soit 16 unites et non 13 ; tu ne changes
pas le garde-fou 3, ce qui reste A3. Une regle ArchUnit ou un test de contenu pour chaque amendement, ou le motif
ecrit de son absence (TEMPLATES.md section 5).
Reponds, si marc-dev ou moi te le demandons, a la forme du test d'absence du retrait (ADR-0061).
S51-OUTIL-JOURNAL-TOKENS t'est affectee (DEC-0110 17.5), non chiffree, non engagee a s52 : tu n'y touches pas.
A3 de la retro de s51 (le rattachement de 02ba9e80 dans confirmation-archunit.md) : correction de ton fichier,
sans budget, quand tu veux.
Mesure depuis ton worktree, -am sur toute mesure partielle. Lance les quatre jobs sur ton diff.
```

```
B7 - ines-design - U2, hors couloir

Worktree ../recreo-s52-ines-contraintes, branche s52-ines-contraintes, scratchpad
<scratchpad>/ines/ines-design. Tag [INES]. Tu ne dessines aucun dessin livre.

Fais : ecris la section 4 de ta contrainte (quadrillage 6 x 6, SYM-CE1-3, DEP-CE1-2) comme tes sections 2 et 3 :
mesures faites sur rendu reel, avec leur commande (outils/mesurer-rendu.sh sous 516 px), cases candidates,
legende ou son remplacement ; ce qui reste une proposition pour nadia-prof est range comme tel (elle seule tranche
la pedagogie). Ses reponses (U3) sont dans le worktree de C : `git show s52-nadia-instance-c:<chemin>`. Deux questions sont deja a theo-archi (poids d'un 10 x 10, variante en chemin unique) : tu n'attends
pas leur reponse. Ton fichier arrive sur round-two par moi ; lea-dev le lit avec git show.
Plus tard, quand lea-dev livre des dessins (je te donne le sha) : bascule TON worktree en detache sur ce sha,
mesure le rendu des ecrans de manche, de reponse, de fin de partie et d'erreur a 320, 360, 744 et 992 px
(ta section 6) ; jamais dans le worktree de C. Un port de ta plage.
```

```
B8 - camille-po - amendements, hors couloir

Worktree ../recreo-s52-camille-amendements, branche s52-camille-amendements, scratchpad
<scratchpad>/camille/camille-po. Tag [CAM].

Points qui te sont routes par le planning de s52 (squad/sprints/s52-deux-instances-ce1-francais-maths/planning.md,
section "Points routes") : R1 (ecart F2 sur le retrait), R3 (lecture de DEC-0111 5.2 (1) contre ADR-0080 A2
section 3), R4 (fiches de conformite des six jeux, a ouvrir avant la premiere livraison de chacun), U4
(re-engagement de MAT-CE1-17, quand je te confirme que U1, U2 et U3 existent sur le disque). Tu amendes
DEC-0111 par un amendement ecrit, dans le meme geste que la fusion du travail qu'il decide.
Le backlog dit encore "a affecter par sofia-sm" : l'enregistrement des noms est le planning ; reporte-les
dans les stories si ta lecture de PAR-16 l'exige.
```

```
B9 - yanis-qa - qualite (debut et cloture)

Au demarrage : lis les criteres d'acceptation des stories engagees (backlog) et dis par ecrit, dans ton rapport de
cloture, lesquels ne sont pas testables par un automate (nadia-prof les joue). Q6 : le critere "sans redemarrage"
du retrait n'a pas d'occurrence en production (avis 3.3, le catalogue est lu une fois au demarrage) ; tu le
testes sur un identifiant de jeu fabrique, ou tu dis pourquoi tu ne le testes pas. Si tu ecris un test, c'est
dans ton propre worktree, et je le fusionne apres le retrait : il ne retarde pas le retrait.
A la cloture : worktree ouvert par moi sur l'arbre fusionne. Rapport qualite avec la commande exacte, le total
mesure (somme des attributs tests des rapports XML, apres clean), l'ecart avec la base de 2082 et son origine,
les QUATRE JOBS nommes comme lances ou non lances, un tirage de un ou deux garde-fous recents mutes sur le
materiau reel (ZON-13 et le rang de la bonne zone d'A2 n'ont pas encore rencontre de contenu de designation de
CE1 : dis-le s'il n'y en a pas). Toute mesure partielle porte -am.
```

```
B10 - relecteur-pedagogique - cloture

Worktree ouvert par moi sur l'arbre fusionne. Perimetre : les paquets neufs et corriges de A et de C (contenu servi
a l'enfant : francais correct et integralement accentue, DEC-0002), les fichiers de ceremonie de s52, l'amendement du
README section 8, et les conventions de redaction de CLAUDE.md. Tu lances .github/scripts/verifier-chemins.sh sur ce
que tu relis. Si tu ne trouves rien a corriger, tu ecris une ligne qui le dit, avec la date et le perimetre.
```

## Porte technique

- **Juge : la CI, tous les jobs de `.github/workflows/ci.yml`** : `prefixes`, `chemins`, `outils`, `build`.
  `mvn verify` n'est que le contenu du job `build`. **Chaque agent nomme les jobs qu'il a lances** ; aucun n'en
  deduit un autre (`CLAUDE.md` racine, amendement du 2026-09-08).
- **`yanis-qa`** : rapport qualite de cloture sur l'arbre fusionne, quatre jobs nommes, commande exacte, total
  mesure et origine de l'ecart avec la base de 2082 (job `build` seul, avis section 0).
- **`theo-archi`** : confirmation ArchUnit sur l'arbre fusionne ; aucune regle neuve n'est attendue.
- **`relecteur-pedagogique`** : relecture de fin de sprint (consigne B10).
- **Porte pedagogique** : prononcee **uniquement** par `nadia-prof`, instances A et C, apres avoir joue chaque
  paquet, avec un motif ecrit dans `squad/conformite/FICHE-<jeu>.md`. Je ne la prononce jamais. Aucun Done n'est
  prononce par ce planning.

## Verification poste/story (`PAR-16`, `squad/README.md` section 4)

Controle fait a la cloture de ce planning, poste par poste, sur la composition de `DEC-0111` section 10. Les
stories sont citees par titre ; le numero de ligne du backlog n'est pas une reference stable
(`grep -n "^### S5[12]-" docs/backlog/backlog.md` donne le temoin du jour).

| Poste | Tarif | Story | Affectation nominative | Issue |
|---|---|---|---|---|
| Retrait des deux paquets | hors capacite de `nadia-prof`, cout = 2 fichiers (avis 3.1) | `S52-RETRAIT-SCIENCES-CM1` | marc-dev / couloir retrait | engage |
| `FRA-CE1-17` | 1,0 | `S52-FRA-PRESENT` | marc-dev / instance A | engage |
| `FRA-CE1-25` | 1,0 | `S52-FRA-SYNONYMES-CONTRAIRES` | marc-dev / instance A | engage |
| `FRA-CE1-26` | 1,0 | `S52-FRA-CATEGORIES` | marc-dev / instance A | engage |
| `TPS-CE1-R1` | 0,5 | story de correction differee, section "Sprint 51" | marc-dev / instance A | engage |
| `ALP-CE1-R1` | 0,5 | story de correction differee, section "Sprint 51" | marc-dev / instance A | engage |
| `MAT-CE1-17` | 1,5 | `S51-MAT-SYMETRIE` | lea-dev / instance C (nominee, non engagee) | **non engage, budget libere**, 1,5, destination s53 (`DEC-0111` 5.2) ; deux conditions sur trois manquent |
| `MAT-CE1-06` | 1,5 | `S51-MAT-MULTIPLICATION` | lea-dev / instance C | engage |
| `MAT-CE1-02` (moitie textuelle) | 1,0 | `S51-MAT-COMPARER-RANGER` | lea-dev / instance C | engage |
| Passe transverse (`PAR-4`) | aucun montant alloue | aucune | aucune | **non engage, budget libere** (aucun montant), deuxieme report, la `DEC` de s53 l'engage ou l'abandonne (`DEC-0111` section 7) |

Resultat : **aucun poste engage sans story, aucun poste engage sans affectation nominative.** Engage 6,5 sur 8,0 ;
libere 1,5. **Ecart que je signale sans le corriger** : les neuf stories portent encore au backlog *"Agent
pressenti : a affecter par `sofia-sm`"* ; le backlog est a `camille-po`, ce planning est l'enregistrement de
l'affectation nominative.

## Points routes (par categorie ; le daily n'est pas journalise)

**Ecarts de l'avis** (les etiquettes F1 a F3 sont celles de la consigne de cette tache ; l'avis numerote F1 et F3
et ne numerote pas F2) :

| Code | Point | Categorie | Agent route | Artefact attendu | Etat |
|---|---|---|---|---|---|
| R1 (F2) | Le retrait ne doit pas toucher `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (avis 3.3, mesure : le toucher rend les deux paquets *"ni juges ni declares"*). `DEC-0111` section 8 point 2 et le critere de la story disent l'inverse. marc-dev suit l'avis (B1) | produit | `camille-po` | amendement de `DEC-0111` section 8 point 2 et du critere de la story | ouvert, non bloquant pour le retrait |
| R2 (F3, A2bis) | Plancher de zone : 13 unites (`ADR-0080` A2 section 4) contre 16 (`ines-design` : 296 px a 320 px de fenetre, 13 unites = 38,5 px sous les 44 px). Le garde-fou 3 calcule a 352 px et ne le voit pas : faux vert possible | technique | `theo-archi` | `ADR-0080` amende (A2bis) | ouvert = U1 ; lea-dev suit 16 en attendant |
| R3 (F1, A1) | Message du cliquet de designation a corriger avant le commit qui monte le plafond de 2 a 3 (avis 5, A1). **Ecart propre** : `DEC-0111` 5.2 (1) exige le message corrige *"dans le meme diff"* que A2 ; `ADR-0080` A2 section 3 le renvoie au diff qui monte le cliquet ; l'avis (section 5) le dit bloquant *"pour le premier commit du paquet de `MAT-CE1-17`, pas pour son engagement"*. J'applique la lettre de la `DEC` pour l'engagement (section "Conditions de C") | technique, produit | `theo-archi` (le test), `camille-po` (la lecture de sa condition) | test corrige ; amendement de `DEC-0111` | ouvert = U1 et U4 |
| R4 | Fiches de conformite des six jeux (aucune n'existe : `ls squad/conformite` ne rend aucun nom de s52) | pedagogie | `camille-po` | six fiches (`TEMPLATES.md` section 7) avant la premiere livraison de chacune | ouvert |

**Questions de l'avis** :

| Code | Point | Categorie | Agent route | Artefact attendu | Etat |
|---|---|---|---|---|---|
| R5 | Q1 : formes regularisees d'*aller* au singulier requises dans `PRS-CE1-3` (b) ? Si oui, amendement d'`ADR-0065` de la forme d'A1 | pedagogie | `nadia-prof` instance A | reponse ecrite ; puis `theo-archi` | ouvert ; non bloquant sauf reponse oui |
| R6 | Q2 : *synonyme* dans un enonce de `FRA-CE1-25` (`TRV-CE1-4`) | pedagogie | `nadia-prof` A | reponse ecrite | ouvert ; `S52-FRA-SYNONYMES-CONTRAIRES` est donc la derniere de A |
| R7 | Q3 : homographes *mur*, *sur*, *pres*, *marche* invisibles au garde-fou | pedagogie | `nadia-prof` A | reponse ou lecture | ouvert |
| R8 | Q4 : le quadrillage 6 x 6, en esquisse, suffit-il a engager les items de completion ? **Ma part** : la condition (2), lue a la lettre, n'est pas etablie, donc `MAT-CE1-17` n'est pas engagee ; je ne juge pas la suffisance pedagogique. Plus les questions d'`ines-design` a `nadia-prof` (onglet ou zone sur la ligne, cases candidates, leurre loyal, texte alternatif, densite d'un reseau) | pedagogie | `nadia-prof` C, `ines-design` | reponse ecrite ; section 4 completee | ouvert = U2, U3 |
| R9 | Q5 : dessinateur de C | capacite | `sofia-sm` | affectation au planning | **repondu ici** : lea-dev, un seul ecrivain, un seul worktree (section "Affectations", point 3) |
| R10 | Q6 : critere *"sans redemarrage"* du retrait, sans occurrence en production | qualite | `yanis-qa` | rapport qualite | ouvert, non bloquant |
| R11 | Forme du test d'absence du retrait sans recopier l'association entree-paquet (`ADR-0061`), si marc-dev ne trouve pas de forme (B1) | technique | `theo-archi` | reponse | conditionnel |
| R12 | 10 x 10 en reseau de points : poids (3 395 octets en `<circle>`, 1 238 de marge sous 4 633) et densite ; un 10 x 10 est-il a ecarter ? (`ines-design`, section 5, pour `theo-archi` point 3) | technique, pedagogie | `theo-archi`, `nadia-prof` C | reponse ecrite | ouvert ; lea-dev commence a 5 x 7 |

**Reports et points hors capacite** :

| Code | Point | Categorie | Agent route | Etat |
|---|---|---|---|---|
| R13 | Texte de `PAR-9` (ecart avec la pratique depuis s41) ; `PAR-4` a deux instances | produit | `nadia-prof` (le texte), `camille-po` (s53) | `DEC-0111` section 7 : route, non traite a s52 |
| R14 | `ART-CP-10` et `ADR-0104` (`Propose`, suspendu a `C5` clause 1) : troisieme sprint de suite hors perimetre ; l'issue s'ecrit a l'ouverture de s53 | pedagogie | `nadia-prof` | `DEC-0111` section 9 |
| R15 | Ecart "palier 2" de la matrice : second report | produit | `camille-po` | `DEC-0111` section 9 |
| R16 | `S51-OUTIL-JOURNAL-TOKENS` (journal de tokens qui perd des mesures a la regeneration) : affectee a `theo-archi`, non chiffree, non engagee | technique | `theo-archi` | second report ; le journal commite reste tel quel (`DEC-0110` 17.2) |
| R17 | A2 et A3 de la retro de s51 : ligne 89 de l'arbitrage de `nadia-prof` toujours a 162 caracteres (`awk 'length($0)>150'` sur le fichier, relance), rattachement de `02ba9e80` toujours a "instance B" dans `confirmation-archunit.md` (ligne 108) | redaction | `nadia-prof`, `theo-archi` | ouverts, corrections de leurs propres fichiers, sans budget |

**Actions de la retro de s51** : A1 close par l'arbitrage de s52 (sections 1.4 et 2.3, qui existe) ; A4 et A5
traitees par `DEC-0111` (sections 3, 5 et 7) ; **A7 close par ce commit** (section suivante) ; A8 appliquee (section
"Affectations", point 1) ; A9 = R16 ; A2 et A3 = R17 ; **A6** (les criteres des stories de sciences demandent-ils de
verifier chaque distracteur contre la question posee ?) : aucune reponse ecrite trouvee, `grep -n "A6"` sur
`DEC-0111` ne rend rien ; elle reste ouverte chez `camille-po` et `nadia-prof`, sans effet sur s52 (aucune story de
sciences), mais `S52-FRA-SYNONYMES-CONTRAIRES` porte la meme famille de defaut (*"aucun distracteur qu'un
dictionnaire donnerait aussi"*).

**Aucun obstacle n'est clos par ce planning.** Un obstacle ne se clot que quand le fichier d'`ADR` ou de `DEC`
qui le tranche existe sur le disque : R1 et R3 quand `DEC-0111` est amendee, R2 quand `ADR-0080` porte A2bis, R4
quand les six fiches existent.

## Amendement du README (action A7 de la retro de s51)

`DEC-0110` section 17.4 propose le texte et le confie a `sofia-sm` ; la section 9 du README (etape 9) m'attribue
l'amendement de ce fichier. **Je l'ecris dans ce commit** : une phrase ajoutee a la fin de la section 8 de
`squad/README.md`, reprise de `DEC-0110` 17.4 sans en modifier le fond, et la phrase qui dit d'ou elle vient.
**Je n'edite ni `DEC-0110` ni la retro de s51** : l'action A7 est close ici, et c'est ce planning qui le dit.

## Ce que ce planning ne fait pas

- Il ne decide ni A1, ni A2bis, ni la forme du test d'absence du retrait, ni la suffisance pedagogique du
  quadrillage, ni la lecture de la condition (1) de `MAT-CE1-17`.
- Il ne re-engage pas `MAT-CE1-17`, ne derive aucune borne a trois instances et n'amende ni `PAR-2`, ni `PAR-4`,
  ni `PAR-9`, ni `PAR-11`.
- Il ne prononce aucun Done, technique ou pedagogique, et ne change aucun statut de la matrice.
- Il n'ouvre aucun worktree ni aucune branche, et ne cree ni n'amende aucun `ADR` ni `DEC`. Il ne modifie ni le
  backlog, ni la matrice, ni une fiche, ni le registre, ni l'avis de faisabilite.

## Decisions prises

Aucune `ADR` ni `DEC` n'est creee ou amendee par ce planning. Sept decisions de planning, qui ne relevent
d'aucun autre role :

1. **Affectations nominatives** : marc-dev (retrait, puis instance A), lea-dev (instance C, paquets et dessins),
   justifiees plus haut ; theo-archi, ines-design, yanis-qa, relecteur-pedagogique, camille-po, nadia-prof dans leur
   role.
2. **Un seul ecrivain, une seule branche et un seul worktree dans C** (`DEC-0111` section 8 point 1, Q5 de
   l'avis) ; lea-dev dessine.
3. **`MAT-CE1-17` est non engagee, budget libere, 1,5, destination s53**, parce que deux de ses trois conditions
   manquent a la cloture du planning (`DEC-0111` 5.2). `MAT-CE1-06` et `MAT-CE1-02` sont engagees. Engage 6,5.
4. **Ordre de deblocage de C** : U1, U2, U3 en parallele, puis U4 (`camille-po`) ; lea-dev ne commence la symetrie
   que sur U4.
5. **Ordre de fusion** : retrait, A, C, un reacteur complet sur chaque commit de fusion, tout total exact remesure
   et jamais additionne.
6. **Isolation** : un worktree, une branche et un scratchpad **par agent** dans chaque couloir, un port par agent,
   ecrits dans le bloc commun de chaque consigne.
7. **Amendement de `squad/README.md` section 8** sur la valeur du trailer `Co-Authored-By` (A7).
