# [Sprint s05] Relecture pedagogique - relecteur-pedagogique

- **Date** : 2026-08-06
- **Perimetre lu** : `planning.md`, `avis-faisabilite.md`, `confirmation-archunit.md` de ce dossier ;
  `DEC-0010`, `DEC-0011`, `DEC-0012` ; `ADR-0016`, `ADR-0017`, `ADR-0018` et les amendements
  d'`ADR-0011` et d'`ADR-0013` ; les sections Sprint 5 et Sprint 6 de `docs/backlog/backlog.md` ;
  les trois fiches `squad/conformite/FICHE-bandit-manchot-*.md` (dont l'arbitrage du 2026-08-06 sur
  les homophones) ; `adapters/adapter-content/src/main/resources/contenu/cp-le-bon-son.yaml` et
  `cm1-homophones.yaml` caractere par caractere ; `accueil.html`, `GestionnaireDesErreurs.java`,
  `PartieHttpTest.java`, `CatalogueChargeableIT.java`, `ChaquePaquetPorteUnIndiceTest.java` pour
  verifier la documentation contre le code.
- **Pas touche** : aucun code de production, aucun test, aucune logique. `yanis-qa` et
  `theo-archi` travaillent en parallele dans le meme arbre ; je n'ai modifie que du texte.

## 1. Corrections appliquees

Une seule faute trouvee, repetee a l'identique dans trois fichiers : l'exemple d'homophone
`a/à` perdait son accent grave et devenait `a/a` (un mot cite deux fois plutot que le couple
qu'il est censé illustrer). Corrige aux trois endroits ou le texte est actuellement lu comme la
description vivante de la ligne "homophones CM1" :

- `adapters/adapter-content/src/main/resources/contenu/cm1-homophones.yaml:8` (champ
  `descripteur.competence`)
- `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md:15` (table "Competences visees")
- `squad/programme/MATRICE-couverture.md:181` (ligne de la matrice)

Le reste des livrables non executables de s05 (planning, avis de faisabilite, confirmation
ArchUnit, les trois ADR, les trois DEC, les sections Sprint 5 et Sprint 6 du backlog, les deux
fiches de conformite modifiees ce sprint) est propre : tirets simples et guillemets droits partout,
aucun tiret cadratin, aucune apostrophe ou guillemet typographique, aucun emoji, aucun chemin de
poste de travail ni nom d'utilisateur. `~/.m2/toolchains.xml` (`planning.md`) est la forme
portable exigee par `CLAUDE.md`, et la mention de `toochains.xml` est une citation intentionnelle
d'un nom de fichier fautif du passe (deja constate en s03), pas une nouvelle faute.

## 2. Le meme defaut, non corrige, en dehors du perimetre de s05

La meme faute (`a/a` pour `a/à`, et une seconde du meme ordre, `ou/ou` pour `ou/où`) existe aussi
dans `DEC-0008-la-couverture-reprend-francais-cp-et-cm1.md` (lignes 10, 80, 111) et dans la section
Sprint 4 du backlog (`docs/backlog/backlog.md:819-825`, story S4-1). Elle est anterieure a s05 (s04,
jamais relu par moi : aucun `relecture.md` n'existe pour s04) et je ne l'ai pas corrigee : `DEC-0008`
est une decision actee, et une story de sprint clos, je ne les edite pas en silence. Je le signale a
`sofia-sm` pour `review.md` : soit `camille-po` corrige ces deux occurrences comme une faute
d'orthographe ordinaire, soit elle explique pourquoi elles restent en l'etat.

## 3. Contenu enfant introduit ou modifie ce sprint - a lister pour camille-po et nadia-prof

Relu caractere par caractere, accents et apostrophes verifies un par un. **Aucune faute trouvee** :
tous les accents sont presents (y compris `ÉTOILE` et `CHÂTEAU`, deja rattrapes deux fois par
`lea-dev` par le passe et intacts ici), toutes les apostrophes sont droites, aucune apostrophe
typographique ne s'est glissee dans `l'a`, `c'est`, `qu'il` et leurs voisins. Le texte est integre
au sens de l'amendement du 2026-08-04 a `DEC-0002`. Ce constat ne prejuge de rien sur le fond
pedagogique - c'est le mandat de `nadia-prof`, pas le mien - donc tout ce qui suit est **du texte
neuf a auditer**, pas du texte deja valide.

### `cp-le-bon-son.yaml` - 21 items, tous modifies ou nouvellement pourvus d'un indice

- Les reponses et distracteurs de tous les items ont perdu leurs crochets (`an` au lieu de `[an]`,
  etc.), lignes 10 a 93.
- Un champ `indice` neuf sur chacun des 21 items restants (lignes 13, 17, 21, 25, 29, 33, 37, 41,
  45, 49, 53, 57, 61, 65, 69, 73, 77, 81, 85, 89, 93).
- Trois items ont disparu par rapport aux 24 d'origine : `MOUCHE`, `MOUTON`, `BALANÇOIRE`
  (motif 4 du refus, les items a deux sons reellement presents).

### `cm1-homophones.yaml` - 22 items, tous portent desormais un indice neuf

- **10 items existants, indice neuf, enonce inchange** (conforme au critere de S5-6 qui interdit
  de modifier les dix items existants) : lignes d'indice 13, 17, 21, 25, 29, 33, 37, 41, 45, 49.
- **12 items neufs, enonce et indice neufs**, cinq familles (`ce/se`, `ces/ses`, `la`/`là`/`l'a`,
  `mais/mes`, `peu/peut`) : lignes 50 a 97. Le triplet `la/là/l'a` est ecrit en deux paires
  pivotant sur `la` (lignes 50-65), conformement a l'arbitrage du 2026-08-06 de `nadia-prof`.

### Le libelle du menu neuf

- `adapters/adapter-web/src/main/resources/templates/accueil.html:27` : le lien "Accueil", seule
  entree du menu introduit par `DEC-0012`. Orthographe et accentuation correctes ; reste a
  `nadia-prof` de juger s'il est comprehensible sans explication d'adulte (deja signale comme tel
  dans la story S6-1 du backlog).

## 4. Verification de la documentation contre le code

- **`ADR-0018` contre `GestionnaireDesErreurs.java`** : conforme. Les deux gestionnaires
  (`partieIndisponible`, niveau INFO ; `defautImprevu`, niveau ERROR avec pile) existent exactement
  comme decrits, `PartieIndisponible` existe et n'est leve que par `PartieController`.
- **L'amendement du 2026-08-06 a `ADR-0013` contre les tests reels** : conforme.
  `PartieHttpTest.java` joue effectivement contre `CatalogueDeTest` avec des propositions `"1"`,
  `"2"` ; `CatalogueChargeableIT.java` appelle bien `demarrer` et jamais `jouer`. L'amendement dit
  vrai.
- **`DEC-0012` contre `accueil.html`** : conforme. Le menu contient exactement une entree
  ("Accueil"), equivalente au bouton "Les jeux" deja present dans les fragments de jeu.
- **`ADR-0016` contre `ChaquePaquetPorteUnIndiceTest.java`** : le code a avance plus vite que le
  texte de l'ADR, sans que ce soit une erreur - a signaler quand meme. `ADR-0016` montre
  `DETTE_DE_CONTENU = Set.of("cm1-homophones", "cp-le-bon-son")` et `PLAFOND_DE_DETTE = 2`, explicitement
  daté "etat au 2026-08-06, ouverture de s05" : c'etait vrai au moment ou `theo-archi` l'a ecrit.
  Le code aujourd'hui a `DETTE_DE_CONTENU = Set.of()` (dette fermee, les deux paquets ont ecrit
  leurs indices) et documente lui-meme ce changement en commentaire. **Ce que je releve** :
  `planning.md` demandait explicitement que `theo-archi` confirme cette fermeture "a la review
  plutot que de le decouvrir par surprise dans un diff", et `confirmation-archunit.md` ne mentionne
  ni `ADR-0016`, ni `DETTE_DE_CONTENU`, ni cette fermeture. Ce n'est pas une faute de redaction que
  je peux corriger moi-meme ; je le signale a `sofia-sm` pour `review.md`.

## 5. Points a trancher - je ne les tranche pas

- **La faute d'accent dans `DEC-0008` et dans la story S4-1 du backlog** (section 2 ci-dessus) :
  a `camille-po`, via `sofia-sm`.
- **La confirmation manquante de la fermeture de la dette de `ADR-0016`** dans
  `confirmation-archunit.md` (section 4 ci-dessus) : a `theo-archi`, via `sofia-sm`.
- **Le contenu enfant liste en section 3** : chaque indice neuf, chaque phrase neuve du couple
  `la/là/l'a` et des quatre autres familles, et le libelle "Accueil", attendent le Done pedagogique
  de `nadia-prof` - je n'ai verifie que la forme, pas le fond.

## Fichiers modifies

- `adapters/adapter-content/src/main/resources/contenu/cm1-homophones.yaml`
- `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`
- `squad/programme/MATRICE-couverture.md`
- `squad/sprints/s05-lever-le-refus-du-bon-son/relecture.md` (ce fichier, nouveau)
