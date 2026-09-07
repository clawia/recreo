# Relecture pedagogique - Sprint 31

**Auteur** : `relecteur-pedagogique`. **Depot mesure** : worktree dedie (`recreo-s31-relecteur`,
branche `s31-relecteur`, creee sur `3181b5e6` - `round-two` - et verifiee descendante de lui avant
toute ecriture). Aucun autre worktree n'a ete touche ; les deux instances de `nadia-prof` auditent
en parallele sur les leurs, non lus.

**Perimetre lu** : `planning.md`, `avis-faisabilite-theo.md`, `confirmation-archunit.md`,
`ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md`,
`DEC-0081`, `DEC-index.md`, `ADR-0085`, `ADR-0086`, `ADR-index.md`,
`squad/decisions/REGISTRE-identifiants.md`, section "Sprint 31" et story `S32-CAT1` de
`docs/backlog/backlog.md`, la ligne `EMC-CP-04` de `MATRICE-couverture.md` (seule ligne touchee ce
sprint), les huit referentiels ayant recu leurs codes de ligne, et les commentaires des dix paquets
YAML touches (huit paquets neufs, deux reprises).

**Non lu, hors perimetre de la commande** : `rapport-qualite.md` de `yanis-qa`. Ce fichier n'existe
pas sur `round-two` a `3181b5e6` : il vit sur la branche non fusionnee `s31-yanis`
(commit `f2f97e0`, 21:53), posterieure au commit qui borne cette relecture. Je ne l'ai pas ouvert.

## 1. Ce que j'ai corrige

**Une faute, sans ambiguite.** `DEC-0081`, section 6, tableau des codes alloues, ligne "Langues
vivantes (anglais) CP" : la decision cite entre guillemets droits le titre d'une note de
`squad/programme/langues-vivantes/competences-cp.md` comme *"Point de nomage non tranche"*. Le
fichier source porte *"Point de nommage non tranché"* (deux `m`). Corrige dans `DEC-0081` pour que
la citation soit exacte.

Rien d'autre a corriger : aucun tiret cadratin, aucun guillemet typographique, aucun emoji, aucun
chemin absolu de poste de travail ni nom d'utilisateur dans l'ensemble du perimetre lu (planning,
avis de faisabilite, confirmation ArchUnit, arbitrage prealable, `DEC-0081`, les deux `ADR`, le
registre, la section "Sprint 31" du backlog, les commentaires des dix paquets touches).

## 2. Un chiffre errone dans le registre lui-meme, signale et non corrige

`squad/decisions/REGISTRE-identifiants.md`, section 1, note de `sofia-sm` a l'assemblage (ligne 49) :

> Compte revise : **527 identifiants, sur 83 prefixes** (aucun prefixe neuf : `BIN`, `CAT`, `FAB` et
> `PLA` etaient deja libres).

**Le compte de 527 identifiants est exact** - verifie independamment en comptant les lignes de la
table section 4, moins les deux lignes d'exemple de forme (`PREFIXE-<n>`, `PREFIXE-R<n>`) qui ne
sont pas des allocations.

**Le compte de 83 prefixes ne l'est pas.** `BIN`, `CAT`, `FAB` et `PLA` sont quatre prefixes que le
registre ne portait pas avant l'assemblage de ce sprint - `theo-archi` mesurait deja 83 prefixes
**avant** que ces quatre identifiants n'existent (`ADR-0085`, `avis-faisabilite-theo.md`). Un
prefixe "libre" au sens de la section 1 du registre veut dire *absent de la table, donc disponible
pour une allocation neuve* - ce n'est pas une propriete qui empeche d'en compter un de plus une fois
qu'il est utilise. Ajouter quatre identifiants sous quatre prefixes qui n'existaient pas ajoute
necessairement quatre prefixes, pas zero.

**Verification independante, en comptant la colonne "Identifiant" de la section 4 du registre tel
qu'il est ecrit aujourd'hui** (prefixes distincts, en retirant les deux lignes d'exemple de forme et
l'homonyme `UTF-8` sous cliquet, exactement comme le prescrit la methode de la section 1) : **87
prefixes distincts**, pas 83. C'est le chiffre que la note aurait du annoncer.

**Ce que cela signifie** : le document dont la raison d'etre est de porter le compte a jour du
depot porte lui-meme, dans sa propre note de correction, un chiffre faux sur l'une de ses deux
mesures. Je ne le corrige pas : ce n'est pas une faute de langue, c'est une donnee de gouvernance
qui appartient a `sofia-sm` (auteure de la note) et a `theo-archi` (gardien de `PAR-5` et de la
methode de comptage). **Je route ce point aux deux.**

Nulle part ailleurs ce chiffre de 87 n'apparait dans `round-two` a `3181b5e6` ; les trois autres
mentions du couple 523/83 (`ADR-0085`, son entree dans `ADR-index.md`, `avis-faisabilite-theo.md`,
`DEC-0081` section 9) sont toutes anterieures a l'assemblage de `sofia-sm` (14:22 et avant) et
decrivent fidelement l'etat qu'elles mesuraient a leur date : ce ne sont pas des chiffres perimes,
au sens ou l'exigent les conventions de ce depot - elles ne pretendent pas decrire l'etat courant du
registre. Seule la note de correction elle-meme, ecrite pour etre la version a jour, se trompe.

## 3. Le reste verifie sans defaut

- **`ADR-index.md`** : la dette que `theo-archi` nomme lui-meme (`ADR-0064` et `ADR-0065` sur la
  meme ligne physique depuis huit sprints) est bien fermee - verifie ligne par ligne : 86 entrees de
  table pour 86 fichiers `ADR-*.md` sous `squad/decisions/adr/`, aucun doublon, aucun manquant,
  `ADR-0064` et `ADR-0065` chacun sur sa propre ligne.
- **`DEC-index.md`** : l'entree `DEC-0081` est presente, resume fidelement la decision (composition
  retenue, borne, `CAT-1`, correction 81 -> 79).
- **`squad/decisions/REGISTRE-identifiants.md`** : les 67 chemins de document distincts que la table
  cite comme lieu d'allocation d'un identifiant existent tous sur le disque ; aucun chemin absolu de
  poste de travail. Le compte de 527 identifiants (section 2 ci-dessus) est confirme.
- **Les huit referentiels ayant recu leurs codes** (arts plastiques CP/CM1, education musicale
  CP/CM1, EPS CP/CM1, histoire des arts CM1, langues vivantes CP) : le nombre de codes alloues dans
  chaque fichier correspond exactement a la table de `DEC-0081`, section 6 (11, 10, 11, 10, 11, 10,
  10, 6). Verifie en particulier `education-musicale/competences-cm1.md` : ses deux puces de note de
  bas de page (*reversible* / *irreversible*) sont bien formatees en liste sans etre des lignes de
  competence et ne recoivent aucun code - c'est exactement l'ecart que corrige `DEC-0081` (81 -> 79
  lignes sans code), et son propre "Bilan chiffre" affiche 10 lignes, cohérent avec les dix codes
  alloues.
- **`MATRICE-couverture.md`** : seule modification du sprint, l'annotation de la ligne `EMC-CP-04`
  (routage de `DEC-0081`, section 7) - executee, cite le bon refus d'avance et le bon motif
  (`EMC-4`), aucun autre statut ne bouge.
- **`docs/backlog/backlog.md`, section "Sprint 31" et `S32-CAT1`** : toutes les fiches de conformite
  et decisions citees (`FICHE-bandit-manchot-regles-de-vie-cp.md`,
  `FICHE-bandit-manchot-permis-interdit-cp.md`, `FICHE-bandit-manchot-melanges-cm1.md`,
  `FICHE-bandit-manchot-objet-technique-cm1.md`, `DEC-0074`, `DEC-0078`, `DEC-0081`, `ADR-0081`,
  `DEC-0064`) existent et resolvent.
- **`confirmation-archunit.md`** : l'arithmetique du tableau de la section 1 est reverifiee de tete
  (99+13+388+617+198+6 = 1321 avant, 99+13+388+617+209+6 = 1332 apres, delta +11 = 4+7) et concorde
  avec le texte.
- **Les dix paquets YAML touches** (`cp-emc-emotions-autrui`, `cp-emc-formules-de-politesse`,
  `cp-emc-exclusion`, `cp-emc-regle-souhait-loi`, `cm1-sciences-etats-matiere`,
  `cm1-sciences-regimes-alimentaires`, `cm1-sciences-familles-materiaux`,
  `cm1-sciences-etapes-developpement`, et les reprises `cm1-sciences-melanges` et
  `cm1-sciences-objet-technique`) : commentaires clairs, accentues, sans tiret cadratin ni guillemet
  typographique ; les references qu'ils portent (`ADR-0086`, `DEC-0064`, `ADR-0067`, sections de
  l'arbitrage prealable) resolvent.

## 4. Points a trancher, recapitulatif pour `sofia-sm`

| Point | Nature | A qui |
|---|---|---|
| `REGISTRE-identifiants.md`, note d'assemblage : "83 prefixes" au lieu de 87 | chiffre errone dans un document de gouvernance vive | `sofia-sm` (auteure de la note), `theo-archi` (gardien de `PAR-5`) |
| `rapport-qualite.md` de `yanis-qa` non fusionne dans `round-two` a la date de cette relecture | hors perimetre, a relire quand il sera fusionne | `sofia-sm` |

Aucun autre point a trancher : le reste du perimetre lu est conforme.
