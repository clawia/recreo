# Fiche de conformite - ce1-qlm-04

- **Mecanique** : `bandit-manchot`, `type: question`, sans texte support, comme les paquets d'EMC CE1 ; aucune modification de
  la mecanique, aucune illustration (`docs/backlog/backlog.md`, annotation du 2026-10-25, cinquieme, *"Ce qui est commun aux
  neuf stories"*).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-familles-d-aliments.yaml` (12 items, lus par
  `grep -c "bonneReponse:"`), ecrit par `marc-dev` (instance neuve, `S57-BR-QLM-CE1`).
- **Entree de catalogue** : aucune. `QC3` : un paquet n'entre au catalogue qu'apres un Done pedagogique ou un refus leve ;
  `grep -c "ce1-qlm" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0 le 2026-10-27.
- **Ligne declaree** : `QLM-CE1-04`. **Story** : `S57-BR-QLM-CE1-04` (`docs/backlog/backlog.md`). **Decision** : `DEC-0120` A.227, A.250 a A.255,
  A.268 a A.280.
- **Competence programme visee** : `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-04`, *"Classer des aliments courants dans leur famille (fruits et legumes, feculents, produits laitiers, viande, poisson et oeufs, produits sucres) et reconnaitre un repas varie."*
- **Conditions opposables** : celles de la story `S57-BR-QLM-CE1-04` et les reperes `QC1` a `QC9` du bloc commun, **non recopiees ici** :
  la story fait foi, ses mots sont ceux de `nadia-prof`. Plafond de la ligne : `couvert-valide`.
- **Statut global** : **en attente d'audit a l'ecran**. Fiche ouverte par `camille-po` le 2026-10-27 (`DEC-0120` A.280, reponse
  de l'utilisatrice a `Q2` : nom `FICHE-ce1-qlm-<lot>.md`). Aucun verdict. La ligne `QLM-CE1-04` reste `non couvert` dans la
  matrice, qui ne bouge qu'au verdict de `nadia-prof`.
- **Statut global, mis a jour le 2026-10-28 par `camille-po` (`DEC-0120` A.283), l'ouverture ci-dessus etant perimee et conservee** : **VALIDE** par `nadia-prof` le 2026-10-27 (s57, palier 5, lot 4 sur 9), sans reserve, fusionne dans `round-two` par `ca957d50`. Voir le bloc de validation.
- **Ligne de matrice, etat du 2026-10-28** : `QLM-CE1-04` reste `non couvert`. Motifs lus au depot : (1) `QC3`, aucune entree de catalogue (`grep -c "ce1-qlm" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0), donc aucun jeu a nommer dans la cellule "Jeu(x) couvrant" ; (2) Done technique complet, les quatre jobs de `.github/workflows/ci.yml` (`QC8`, A.280 point 2), dont seul `build` a ete lance. Aucun des deux n'est une decision de `nadia-prof`. La ligne bouge quand l'entree est fusionnee et que la CI complete est verte (`S57-QLM-CE1-ENTREES-CATALOGUE`) ; fusion du paquet dans `round-two` : `ca957d50`.
- **Stories du backlog pour ce lot** (`docs/backlog/backlog.md`, annotation du 2026-10-28, douzieme, `DEC-0120` A.283) : aucune reserve, aucune story pour le lot ; `S57-MECA-FIN-DE-PARTIE-BRAVO-ZERO` (mecanique commune, vue par `nadia-prof` a l'audit de ce lot, hors lot). Aucune n'est engagee.
- **Derniere mise a jour** : 2026-10-27 ; 2026-10-28 (`camille-po`, `DEC-0120` A.283 : statut, ligne `verify`, etat de la ligne de matrice, stories).

---

## Competence visee

| Matiere | Niveau | Competence | Reference au referentiel |
|---|---|---|---|
| questionner le monde | CE1 (cycle 2) | Classer des aliments courants dans leur famille (fruits et legumes, feculents, produits laitiers, viande, poisson et oeufs, produits sucres) et reconnaitre un repas varie. | `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-04` |

## Criteres attendus - camille-po

Les criteres d'acceptation de cette fiche sont ceux de la story `S57-BR-QLM-CE1-04` (`docs/backlog/backlog.md`, titre *"Classer un aliment dans sa famille, reconnaitre un repas varie"*) et
les reperes `QC1` a `QC9` du bloc commun. Je ne les recopie pas : une copie derive de la source. Je n'ecris aucune mesure ici.

- [ ] Criteres de la story `S57-BR-QLM-CE1-04`, numerotes dans le backlog.
- [ ] `QC1` a `QC9` du bloc commun (paquet, ligne declaree, branchement, test du paquet, densite, conditions negatives,
  lecture avant `verify`, Done technique, ce que `nadia-prof` garde seule).
- [ ] Texte servi a l'enfant integralement accentue, orthographe et ponctuation irreprochables (`DEC-0002`).

## Parcours du lot, faits lus au depot

Source : `planning.md` du sprint s57, amendement du 2026-10-27 15:40, section 1 ; chaque SHA est un commit de `round-two`.

| Geste | Auteur | Commit | Fichier |
|---|---|---|---|
| premier jet | `marc-dev` | `a9b1480a` | `ce1-qlm-familles-d-aliments.yaml` |
| lecture du premier jet | `nadia-prof` | `c80ea30d` | `lecture-nadia-premier-jet-ce1-qlm-familles-d-aliments.md` |
| corrections | `marc-dev` | `f273c6d9` | `ce1-qlm-familles-d-aliments.yaml` |
| relecture des corrections (seconde lecture) | `nadia-prof` | `5a9ded0f` | `relecture-nadia-corrections-ce1-qlm-familles-d-aliments.md` |

Les fichiers de `nadia-prof` sont dans `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/`. Cette fiche n'en reprend aucun
verdict : la lecture et la relecture sont des gestes avant `verify`, pas le Done pedagogique.

## Etat de la porte technique

**Ce qu'un fichier du depot prouve, et rien de plus.**

- **Aucun `mvn verify` complet n'a ete lance pour ce lot** (`planning.md`, amendement du 2026-10-27 15:40, section 1 : fait donne
  par l'agent lanceur, non verifie ; les relectures ecrivent *"aucun autre job de `ci.yml` lance"*).
- **Regle en vigueur** (`DEC-0120` A.280, reponse de l'utilisatrice a `Q1`, option `O1`) : un `mvn verify` complet **avant** l'audit
  a l'ecran de `nadia-prof` de ce lot ; passage CI complet (`prefixes`, `chemins`, `outils`, `build`) a la cloture du palier ; toute
  annonce de porte nomme les jobs lances.
- Premier `verify` complet du lot : **fait au niveau du reacteur, pas par lot** (mis a jour le 2026-10-28 par `camille-po`, `DEC-0120` A.283 ; texte d'origine *"a faire"* perime, conserve ici). `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, sur `90c421a7` : `BUILD SUCCESS`, 3618 tests unitaires et 6 d'integration, 0 echec, mesure par `sofia-sm` (`planning.md`, amendement du 2026-10-27 21:34, section 3), **job `build` seul**. `prefixes` et `chemins` n'ont ete lances qu'en commande de garde-fou, forme CI, `outils` n'a pas ete lance : la porte technique complete n'est pas annoncee (A.280 point 2). Sur `1a810b79`, base de cette mise a jour : un `verify` complet vert (3 624 tests, `build` seul) est **annonce par l'agent lanceur et n'est ecrit dans aucun fichier du depot que j'aie lu** ; `git diff --name-only 90c421a7 1a810b79` ne liste que des fichiers `.md`. Que ce `verify` unique tienne lieu de `verify` du lot n'est ecrit nulle part ; `nadia-prof` l'a lu ainsi dans son verdict.

## Ce dont `nadia-prof` reste la seule garde sur cette ligne

Ce qu'aucun test ne tient (`QC9`, `arbitrage-nadia-palier-5-qlm-9-lots.md`, section 6) : l'exactitude de chaque fait contre la source
ecrite en commentaire du paquet, `NRC-CE1-1` sur le sujet de l'item, `CYC-1` contre les paquets voisins, `TRV-CE1-6`, l'operation
mentale de `FER-3`, et les conditions propres a la ligne que la story nomme.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Verdict du 2026-10-27 (`nadia-prof`, s57, palier 5, `P2`, lot 4 sur 9) : **VALIDE**

**Done pedagogique prononce** pour `S57-BR-QLM-CE1-04`, paquet `ce1-qlm-familles-d-aliments` (12 items), ligne `QLM-CE1-04`.
Branche `s57-p5-nadia-audit-04`, base `round-two` a `d6f2e007`.

**Porte technique, ce que j'ai lu et rien de plus.** `mvn -B clean verify` complet, reacteur entier, vert a `90c421a7` (3624 tests,
0 echec), mesure par `sofia-sm` (`planning.md`, amendement du 2026-10-27 21:34, section 3) : c'est le `verify` par lot d'A.280
`O1`, job `build` seul. Le contenu du paquet n'a pas change depuis `f273c6d9`, ancetre de `90c421a7` ; de `90c421a7` a `d6f2e007`,
seuls deux `.md` changent (`git diff --name-only`). Je n'ai relance aucun `verify`. Les jobs `prefixes`, `chemins`, `outils` sont
a la cloture du palier (A.280).

**Ce qui a ete joue, et comment.** Jar fourni par l'agent lanceur, copie hors du depot ; horodatage de ses entrees 21:14 a 21:18,
commit de construction non lu. J'ai compare par `cmp` trois ressources du jar a celles de `round-two` : le paquet,
`catalogue.yaml` et `matieres.yaml`, identiques. Lance sur JDK 21.0.10 (celui de la toolchain), port 18104 par
`--server.port`, *"Tomcat started on port 18104"*, PID lu par `netstat`, arrete par ce PID, port libre ensuite, aucune erreur au
journal.

**Le paquet n'est pas au catalogue (`QC3`), et je dis comment je l'ai servi quand meme.** J'ai ajoute une entree a une **copie**
de `catalogue.yaml`, hors du depot, chargee avant celle du jar par `PropertiesLauncher` et `-Dloader.path` : identifiant
`bandit-manchot-familles-d-aliments-ce1`, titre *"Les familles d'aliments"*, consigne *"Lis bien, puis choisis la bonne
réponse."* (celle des paquets d'EMC CE1), `bandit-manchot`, 10 manches. **Ce titre et cette consigne sont les miens, pour jouer.**
Ce ne sont pas ceux que l'entree de catalogue portera ; je ne les audite pas ici. Aucun fichier du depot n'est modifie par ce
geste. Le jeu est apparu sur `/niveaux/CE1/matieres/questionner-le-monde`.

Jeu par moi, requetes HTTP sur les fragments htmx (`POST /parties/...`, `.../coups`), HTML rendu lu sans les commentaires :

- une partie a deux erreurs par manche, une partie a une erreur, une manche a trois erreurs (la meme fausse deux fois, puis
  l'autre) ;
- 150 parties sans erreur, pour la repartition des items et du rang de la bonne reponse ;
- trois regles de surface jouees contre les quatre menus et contre les huit classements (voir plus bas).

**Non fait par moi** : aucun rendu dans un navigateur, aucune capture, pli non mesure (`ines-design`). Je n'ai vu ni la taille ni la
coupure des boutons, seulement leur texte.

**Ce que l'enfant voit, constate a l'ecran :**

1. **Texte servi = texte du paquet.** Les 12 enonces, les 36 propositions et les 12 indices s'affichent comme le paquet les ecrit.
   Accents servis en UTF-8 (*réponse*, *féculents*, *œufs*, *À quelle*). L'espace avant *?* est insecable dans les 12 enonces du
   paquet (lu au fichier) : je n'ai pas pu voir son rendu sans navigateur.
2. **Une partie fait 10 manches, toutes distinctes, sur 12 items.** Dans les 150 parties : **2 a 4 items de menu** par partie
   (2 dans 14 parties, 3 dans 71, 4 dans 65). Les deux taches de `FER-3` sont donc dans chaque partie. Rang de la bonne reponse,
   sur 1500 manches : 503, 513, 484. Aucun rang n'est previsible.
3. **Erreur.** Au premier faux : *"Ce n'est pas ça. Essaie encore !"* suivi de l'indice de l'item, la proposition pressee
   marquee `deja-essayee`. Presser deux fois la meme fausse ne compte qu'une erreur. A la troisieme erreur : *"Ce n'est pas ça.
   Appuie sur la bonne réponse : féculents"* et l'indice reste (*"Pense aux pâtes et au riz."*). Revele, l'indice dit pourquoi :
   les pates et le riz sont des feculents. Un enfant bloque peut avancer.
4. **Fin de partie** : *"Tu as trouvé, tout seul : 10"* et *"Tu as joué toutes les questions : 10"*, puis *"Bravo, c'est juste ! C'est
   fini !"*. Une manche reussie apres une erreur compte 0 dans *tout seul* (0 sur 10 dans les deux parties avec erreurs). Ce
   comportement est celui de la mecanique et pas celui de ce lot. Je ne le juge pas ici.

**Les deux points que j'avais laisses a l'ecran** (`lecture-nadia-premier-jet-ce1-qlm-familles-d-aliments.md`, 1, `Lisibilite`,
et 3, `I3`) :

- **Douze aliments a ranger dans un item de menu.** A l'ecran, chaque menu est un seul bouton de 3 ou 4 mots courts (36 caracteres
  au plus : *fromage, banane, chocolat, confiture*) ; l'indice du premier faux donne la methode (*"Compte les familles de chaque
  menu."*). Ce que j'en lis : un enfant de CE1 peut lire et ranger ces trois boutons. Il peut aussi abandonner le compte et
  eliminer : a la troisieme erreur la reponse est revelee. C'est la mecanique commune, sans defaut propre a ce lot. Je ne peux
  pas mesurer l'endurance d'un enfant reel. **Pas de reserve.**
- ***Feculents* dans les huit triples de classement.** Joue en ne pressant jamais *féculents* : la regle echoue sur les 2 items ou
  il est la bonne reponse, et l'un d'eux est la pomme de terre, le piege de la ligne. Sur les 6 autres, elle laisse un choix entre
  deux familles, que seul le savoir tranche. **Tient.**

**Regles de surface jouees contre les menus** (4 items, familles comptees sur la liste du paquet) : *"le plus de fruits et
legumes"* (la regle du CP) donne 1 faux et 3 egalites ; *"sans produit sucre"* ne tranche aucun item ; *"le menu le plus long"*
donne 3 faux sur 4 ; *"le menu avec du riz"* donne 2 justes sur les 3 items ou le riz apparait. C'est le compte que j'avais
accepte a la relecture (*"riz ... 2 fois sur 3 apparitions"*, `relecture-nadia-corrections-ce1-qlm-familles-d-aliments.md`, 3), et
le riz n'est pas dans le quatrieme. Aucune regle ne remplace le compte des familles. **Tient.**

**Ce qui tient** (verifie, a ne pas defaire) :

- **Ancrage** : `QLM-CE1-04`, *"Classer des aliments courants dans leur famille ... et reconnaitre un repas varie"*. Les deux verbes
  sont travailles : 8 items de classement et 4 de menu, et chaque menu demande de ranger chaque aliment avant de comparer.
- **Exactitude** : les 19 aliments sont dans la bonne famille au CE1, et les lignes de source disent ce que dit la page (lecture
  et relecture, inchangees depuis `f273c6d9`). A l'ecran, aucun texte ne differe du paquet.
- **Distracteurs** : plausibles. *Fruits et légumes* pour la pomme de terre est le faux que l'enfant choisit vraiment.
- **Niveau** : enonces d'une ligne, de 7 a 12 mots ; propositions de 1 a 4 mots ; indices de 3 a 8 mots, aucun ne donne la
  reponse. Les indices de classement nomment un aliment de la meme famille ; les indices de menu donnent la methode.
- **`NRC-CE1-1`, `CYC-1`** : tenus a la lecture, rien de change.
- **Biais, ton** : aucun item ne demande ce que l'enfant mange ; aucun menu n'est dit mauvais ; aucun foyer. *Sacha* est un prenom
  epicene. *Bravo !* et *Essaie encore !* ne sont pas condescendants.

**Observation, sans reserve.** Dix manches sur douze items : la pomme de terre manque dans 30 parties sur 150. *Produits sucrés*
et *fruits et légumes* sont chacun la bonne reponse d'un seul item. Ils manquent comme bonne reponse dans 17 et 22 parties sur 150.
C'est le regime de `FER-2` pour tout paquet de plus de dix items, pas un defaut du lot.

**Ce que ce verdict ne couvre pas.** L'entree de catalogue de `QC3` n'existe pas : titre, consigne, nombre de manches. La consigne
est lue par l'enfant, donc elle m'appartient. Quand l'entree sera ecrite, je la lis avant la fusion. Ce n'est pas une reserve sur le
paquet, et elle n'ouvre aucune story.

**Je reste la seule garde** de l'exactitude de chaque famille contre la classification de l'ecole et contre la page citee ; de
la plausibilite de chaque distracteur, menu compris ; de ce qu'aucun aliment ne designe le menu attendu ; de ce qu'un indice reste
une methode ; de `NRC-CE1-1` sur le sujet et de `CYC-1` ; de `BIA-4`, de *mauvais* et de `TRV-CE1-6` dans leurs paraphrases. **Aucun
test ne garde ces points.**

**Pour `camille-po`** : Done pedagogique prononce ; la ligne `QLM-CE1-04` peut passer a `couvert-valide`, son plafond. Aucune
reserve, aucune story a ouvrir. `review.md` : aucun fichier de ce nom pour s57, je n'en cree pas ; la reprise est a la cloture du
palier.

**Duree de l'audit, a l'horloge** (`date`, donnee brute) : debut **21:51:40**, fin **22:11:26**, avant le commit, soit
**19 min 46 s**. Lecture de la procedure, jeu, et verifications avant commit sauf `verifier-prefixes.sh`, lance apres le commit (il lit le message), comprises. Je ne la convertis pas en bareme.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
