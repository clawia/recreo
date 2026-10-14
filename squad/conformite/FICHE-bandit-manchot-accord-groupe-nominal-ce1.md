# Fiche de conformite - bandit-manchot-accord-groupe-nominal-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-accord-groupe-nominal.yaml` (24 items, type
  `question`), ecrit en s53 (`S53-FRA-ACCORD-GROUPE-NOMINAL`), non modifie par le branchement.
- **Entree de catalogue** : `bandit-manchot-accord-groupe-nominal-ce1`, titre servi *"Des mots bien accordés"*, consigne
  servie *"Lis bien, puis choisis la bonne réponse."*, `nombreDeManches: 10`. Branchee par `sofia-sm` (casquette
  developpeuse), commit `72931c94`, branche `s57-sofia-fra-ce1-15`, **non fusionnee** au moment du verdict.
- **Ligne declaree** : `FRA-CE1-15`. **Story** : `S57-BR-FRA-CE1-15`. **Decision** : `DEC-0120` A.33, A.58.
- **Competence programme visee** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-15`, *"Accorder en nombre et en
  genre dans le groupe nominal (déterminant, nom, adjectif) : pluriel en -s et en -x (-eau, -au, -eu), féminin en -e."*
- **Conditions opposables** : `GNA-CE1-1` a `GNA-CE1-3`, `TRV-CE1-1` a `TRV-CE1-7`, `LON-CE1-1`, `-2`, `-5`
  (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, sections 2 et 5.6).
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-10-14 par `nadia-prof` (s57, palier 1, instance A'),
  apres avoir joue le jar de la branche. Fiche ouverte par ce verdict.
- **Derniere mise a jour** : 2026-10-14.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-14 (`nadia-prof`, s57, palier 1, instance A') : **VALIDE SOUS RESERVE**

**Done pedagogique prononce.** `camille-po` peut passer `FRA-CE1-15` a `couvert-valide`, sans mention de couverture
partielle, **apres la fusion de `72931c94`** (ou d'un commit dont le contenu servi est identique : le conflit de
`catalogue.yaml` avec le poste 4 se resout en gardant les deux entrees, sans toucher leurs six lignes), et faire une story
de la reserve `GNA-CE1-R1` (section 5), non bloquante.

#### 1. Ce que j'ai fait

- **Lecture complete** prealable : `lecture-nadia-palier-1.md`, section 2.3 (`7161ab69`), 24 items.
- **Objet** : le worktree de la branche est a `3459ddb8`, qui n'ajoute a `72931c94` que `rapport-sofia-dev.md` (`git diff
  --stat 72931c94 3459ddb8` : un fichier sous `squad/`). Le jar joue sert donc le contenu de `72931c94`.
- **Build** : `bash outils/mvn-jdk21.sh -B clean verify` dans ce worktree, racine, sans `-pl` : `BUILD SUCCESS`, 2519
  tests (120, 31, 486, 1505, 371 et 6 d'integration), 0 echec ; JDK 21.0.10. Job `build` seul, lance par moi.
  `git merge-base --is-ancestor 729b0d8a HEAD` sort 0 (porte P-2) ; `bash outils/verifier-lignee.sh` sort 0.
- **Jar** : port 18512 (A', palier 1 ; libre avant, second lancement de ce port apres l'arret du premier) ; *"Tomcat
  started on port 18512"*, PID 53496, arrete par ce PID seul. Dossier de poste `s57-p1-nadia-prof-ap`.
- **Accueil** : `GET /niveaux/CE1/matieres/francais` sert *"Des mots bien accordés"* en dernier des quinze jeux.
- **Par les routes servies** : 15 parties de dix manches, **les 24 items tires**, 223 erreurs, 37 revelations, **0 ecart**
  (propositions, indices et revelations identiques au fichier, aucun indice avant la premiere erreur).
- **Rendu a 744 x 551, apres une erreur**, les 24 items un par un (methode de la fiche de `-13`, rejeu du 2026-10-14),
  et une capture.

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne : l'enfant choisit, entre trois groupes nominaux entiers, celui dont le determinant,
le nom et l'adjectif s'accordent. 21 groupes portent un adjectif ; pluriel en -x 12, en -s 6, feminin en -e 6. La frontiere
avec `-14` (accord sujet-verbe, *"Un ou plusieurs ?"*) tient : aucun verbe a accorder ici.

#### 3. Contenu, joue

| Point | Constat a l'ecran |
|---|---|
| `GNA-CE1-2`, exactitude des marques | les 24 bonnes reponses sont justes, relues une a une. Les -x sont tous des noms ou adjectifs en -eau ou -eu (*chapeaux, beaux, gâteaux, bateaux, cheveux, jeux, manteaux, oiseaux, tableaux, cadeaux, drapeaux, feux*), aucune exception (*bleus*, *pneus*) ; les six feminins ajoutent un -e sans autre changement (*petite, grande, chaude, ronde, noire, verte*). *gros* reste *gros* au pluriel : juste. **Tenu** |
| `GNA-CE1-3`, une seule reponse acceptable | chaque distracteur porte une faute d'accord et une seule famille : marque oubliee (*les beaux chapeau*), marque sur le mauvais mot (*un petite fille*, *une assiette rondes*), -s pour -x (*les gâteaus*). Aucun distracteur absurde, aucun acceptable. **Tenu** |
| `TRV-CE1-5`, indices | quatre indices, vrais, sans proposition : *"Compare la fin de chaque mot entre les trois groupes."*, *"Vérifie chaque mot du groupe, du premier au dernier."*, *"Demande-toi : parle-t-on d'un seul ou de plusieurs ?"*, *"Demande-toi : le nom est-il masculin ou féminin ?"* ; *masculin*, *féminin* sont admis (`TRV-CE1-4`). **Tenu** |
| `TRV-CE1-6`, ton et roles | groupes sans personnage sauf *une petite fille* ; rien a relever |

#### 4. Le titre et la consigne

- **Titre *"Des mots bien accordés"*** : **tenu.** *Accorder* est le mot de la classe pour cette lecon, et le titre dit ce
  que l'enfant fait : choisir les mots qui vont ensemble. Il se distingue de *"Un ou plusieurs ?"* (accord sujet-verbe)
  dans la liste. L'enonce dit *"bien écrit"*, le titre *"bien accordés"* : l'enfant de CE1 y lit la meme chose, la seule
  faute possible dans un item etant une faute d'accord.
- **Consigne *"Lis bien, puis choisis la bonne réponse."*, reprise de `-13` et `-10`** : **tenue.** L'enonce porte la
  question a chaque manche ; la consigne generique ne la contredit pas et ne la redouble pas (le regime de `J5`). **Pas
  une reserve.**

#### 5. Reserve neuve, datee du 2026-10-14, non bloquante

**`GNA-CE1-R1` - a 744 x 551, apres une erreur, le bloc de l'indice depasse le pli sur 23 items sur 24.** Les propositions
tiennent sur deux rangees : sur 22 items le bloc `.indice` va de **518 a 568** (le texte d'une ligne reste lisible, le
cadre est coupe a 17 px du bas, capture a l'appui) ; sur *"des manteaux chauds"* (trois rangees) le verdict est coupe (541
- 571) et l'indice entierement sous le pli (600 - 650) ; sur *"les gâteaux"* (une rangee) tout tient (indice a 436 - 486).
Meme cause que `TPH-CE1-R1`, regime de la reponse de l'utilisatrice a `Q-N4` (non bloquant). **Ce qui la leve** : a 744 x
551, apres une erreur, le bas du bloc `.indice` est au-dessus de y = 551 sur les 24 items.

**Precision sur `TPH-CE1-R1`, de la meme mesure** : la ligne *"518 - 568, coupe"* de sa fiche (trois items courts) designe
ce meme etat, texte lisible et cadre coupe ; le constat n'en est pas change.

#### 6. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1AccordGroupeNominalTest` recompte `GNA-CE1-1` sur les champs et garde la forme des familles. **Il ne garde pas** :
l'exactitude de chaque marque (`GNA-CE1-2`), qu'une seule proposition soit acceptable, la verite des indices, la position de
l'indice par rapport au pli (`GNA-CE1-R1`). **Je les tiens en jouant.**

**Identifiants** : un neuf, `GNA-CE1-R1`. Forme copiee de `VSJ-CE1-R1` : trois segments, niveau au milieu, marqueur `R`
dans le dernier (`ADR-0100`) ; prefixe `GNA-CE1` deja alloue (`GNA-CE1-1` a `-3`) ; ligne de la section 4 de
`REGISTRE-identifiants.md` dans le meme diff.

**Tarif consomme** : audit 1,0 (poste 5, instance A'). Marge de rejeu de A' intacte.

**Signe** : `nadia-prof`, s57, palier 1, instance A', 2026-10-14.
