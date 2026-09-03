# Review de s27 - deux matieres neuves

- **Horodatage de fin** : 2026-09-03 18:59
- **Duree effective** : depuis l'ouverture (2026-09-02 22:08), environ 20h51.
- **Increment mesure** : `round-two` a `b51803d`.

---

## Porte technique - confirmee trois fois independamment

**Rapport qualite de `yanis-qa`** (`squad/sprints/s27-deux-matieres-neuves/rapport-qualite.md`,
mesure sur `fe14ea2`) : `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, **BUILD
SUCCESS**, **1125 tests, 0 echec, 0 erreur** (somme de l'attribut `tests="..."` de 158 rapports XML
apres `clean`, methode de l'amendement s11). Il confirme par sa propre execution, sur son propre
`clean`, le chiffre annonce par `sofia-sm`/`theo-archi`.

**Ma propre execution sur l'arbre final** (`b51803d`, apres les trois branches restantes : Done
pedagogique, ratification de `camille-po`, correction de `lea-dev`) : `bash outils/mvn-jdk21.sh -B
clean verify`, reacteur complet, **BUILD SUCCESS**.

```
recreo-domain .......................................... SUCCESS ( 82 tests)
recreo-application ...................................... SUCCESS ( 13 tests)
recreo-adapter-web ....................................... SUCCESS (386 tests)
recreo-adapter-content .................................... SUCCESS (491 tests)
recreo-bootstrap ........................................... SUCCESS (150 tests + 6 IT)
```

**Total : 1128 tests, 0 echec, 0 erreur** (somme des `Tests run` par module, apres `clean`, sur le
reacteur complet, sans aucun `-pl`). L'ecart avec le chiffre de `yanis-qa` (1125) est explique et
non tu : `lea-dev` l'a elle-meme verifie et ecrit dans son commit (`c9b9c2b`) - "1 128 tests,
exactement le compte donne pour `d259b5f`" - les trois tests d'ecart viennent de la seconde fusion
de `s27-theo` (14e et 15e defauts, normalisation de la cle de lot) qui a suivi la mesure de
`yanis-qa`, pas d'une divergence de methode. Confirme par ma propre execution ci-dessus, pas
recopie.

**Confirmation ArchUnit** : aucun `confirmation-archunit.md` distinct n'a ete ouvert ce sprint. Les
regles d'architecture vivent dans les suites `com.recreo.archi.*` (domain) et l'equivalent de
`bootstrap` (`AbstractionsCiteesTest`, `ArchitectureEnCouchesTest`, `DecouplageMecaniqueContenuTest`,
`PureteDesCouchesTest`, `LesReglesMordentTest`, `ScanDeComposantsTest`,
`ToutesLesReferencesDeDecisionResolventTest`, `ConcordanceDesTagsDAgentTest`,
`LaMachineVirtuelleDesTestsPorteLaVersionQueLeBuildExigeTest`) : toutes vertes dans le decompte
ci-dessus. Chaque commit de `theo-archi` porte lui-meme sa preuve (`139d785` : "Mesure : bash
outils/mvn-jdk21.sh -B clean verify sur la racine, reacteur complet, BUILD SUCCESS. JDK 21.0.10
rapporte par la machine virtuelle des tests.") - je le releve plutot que de fabriquer une
confirmation qu'il n'a pas ecrite separement.

---

## Corrections du `relecteur-pedagogique`

Commit `caec614`, deux corrections, aucune ligne de competence changee :

1. **Comptage d'enseignements perime** : `squad/programme/MATRICE-couverture.md` recopiait "sept
   enseignements du cycle 2" et "cinq autres", le compte d'avant la correction du 2026-09-02
   (`fcdc365`), en contradiction avec la note en tete du meme fichier qui declare huit et dix
   depuis cette date - ecrit a 21:32, jamais repris apres la correction de 22:26. Corrige a
   huit/dix et six/huit.
2. **Quatre referentiels neufs pas au grain annonce** : `questionner-le-monde/competences-cp.md`,
   `histoire-geographie/competences-cm1.md`, `langues-vivantes/competences-cp.md` et
   `competences-cm1.md` annoncaient etre "au meme grain" que les referentiels de mathematiques et
   de francais, integralement accentues, mais leurs titres et en-tetes ne l'etaient pas.
   Reaccentues ; les lignes de competence elles-memes etaient deja accentuees.

**Deux verifications sans defaut trouve, notees comme telles** : il a distingue les /si/ des /sj/
plutot que d'uniformiser - "/si/ n'apparait que dans les citations verbatim de la clause de s01,
/sj/ partout ou un agent parle en son nom propre" - et il a cherche d'autres negations non mesurees
dans les quatre garde-fous neufs sans en trouver.

**Deux recits perimes dans mes propres fichiers**, releves par lui et corriges par moi (ce n'etait
pas a lui de le faire) : la note du 2026-09-03 dans `planning.md` sur `DEC-0070` restee au
conditionnel treize minutes apres son ecriture, et la fermeture formelle de
`squad/conformite/OBSTACLE-s27-remplissage-couleur-monnaie.md`, resolu de fait avant d'etre ferme.

---

## Tableau des obstacles du sprint

| # | Obstacle | Categorie | Route vers | Resolution |
|---|---|---|---|---|
| 1 | `DEC-0072` citee mais absente de l'arbre (5e et 6e occurrence de la dette "fichier reste sur une branche") | Gouvernance | `camille-po` | Ferme : fichier entre avec la fusion de `retours-utilisatrice` |
| 2 | `git reset --hard` involontaire sur la branche de `theo-archi` en session concurrente | Coordination (moi, sous investigation) | orchestrateur | Ferme : aucune donnee perdue, regle de coordination posee apres coup (voir `retro.md`) |
| 3 | `ADR-0077` annoncait `POIDS_D_UN_DESSIN_MAXIMUM` a 2685, faux a la mesure (2775) | Technique | `theo-archi` | Ferme : `ADR-0077` amende, chiffre corrige, ecart documente contre lui-meme |
| 4 | Test de prediction d'`ADR-0077` obsolete + mecanisme de mutation jamais mordu sur son propre resultat | Qualite/technique | `theo-archi` | Ferme : test retire avec motif ecrit, mecanisme corrige et mordu (17/0) |
| 5 | 14e defaut : cle de lot d'`ADR-0079` non normalisee, vert a tort sur un descripteur mal recopie | Technique, trouve par mutation | `theo-archi` (route par `yanis-qa`) | Ferme : normalisation posee dans les deux garde-fous qui lisent le meme champ, preuve par retrait (9/0 et 7/0) |
| 6 | Deux recits perimes dans les fichiers de `sofia-sm` (`DEC-0070` conditionnel, obstacle monnaie non ferme) | Redaction | `sofia-sm` (moi) | Ferme : notes datees ajoutees, obstacle ferme avec sa chaine de resolution |
| 7 | Reserve bloquante de `nadia-prof` sur l'item 5 d'histoire-geographie (formulation sur-etendue) | Pedagogie | `marc-dev` | Ferme : reformulation reprise mot pour mot |
| 8 | Reserve non bloquante de `nadia-prof` sur `cp-qlm-corps-sens` (item trop long, mot hors niveau) | Pedagogie | `lea-dev` | Ferme : correction a six mots reprise mot pour mot |
| 9 | `nombreDeManches: 7` servi sans decision produit ecrite (exigence `DEC-0064`) | Produit | `camille-po` | Ferme : `DEC-0073` ratifie |
| 10 | Ligne de matrice "monnaie" suspendue tant que le correctif de couleur n'etait pas entre | Produit | `camille-po` | Ferme : restauree `couvert-valide` a l'entree du correctif |
| 11 | Titre "Calcul CP" seul a porter le niveau de l'enfant, `S1-8` bloquant sa correction | Pedagogie + technique | `nadia-prof` puis `theo-archi` | Ferme : `S1-8` retrecie (le critere du grapheme /sj/ etait faux en extension sur quatre autres titres), titre applique, `ADR-0078` amende |

---

## Avis de `nadia-prof` et Done pedagogique

**Six verdicts, aucun refus** (branche `matieres-absentes`, commit `430648e`, arbre `round-two` a
`d259b5f`, jar reconstruit, parties reelles par appels HTTP, audit non degrade) :

| Paquet | Verdict | Mouvement de ligne |
|---|---|---|
| `bandit-manchot-voyelle-du-son-cp` | **VALIDE** | `en cours` -> **`couvert-valide`** |
| `bandit-manchot-vivant-non-vivant-cp` | **VALIDE** | `non couvert` -> `en cours` |
| `bandit-manchot-etats-eau-cp` | **VALIDE** | `non couvert` -> `en cours` |
| `bandit-manchot-corps-sens-cp` | **VALIDE SOUS RESERVE** (`COR-R1`, non bloquante, levee depuis par `lea-dev`) | `non couvert` -> `en cours` |
| `bandit-manchot-histoire-geographie-cm1` | **VALIDE** | onze lignes `non couvert` -> `en cours` |
| Rejeu de `bandit-manchot-monnaie-cp` | **VALIDE**, Done rendu | `en cours` -> **`couvert-valide`** (restauration) |

"LA LIGNE DES GRAPHEMES SE FERME. [...] C'est la seule fermeture du sprint." (`nadia-prof`, commit
`430648e`). Les quatre lignes neuves (questionner le monde, histoire-geographie) **ouvrent**, elles
ne **ferment** rien : "un premier paquet de matiere neuve ouvre une ligne, il ne la ferme pas."

**Sa borne** : "4,8 sur 5, tenue en pratique. Rien decline, rien echantillonne."

**Ce qu'elle consigne contre elle** (`430648e`) : "Mon pilote d'audit a d'abord rendu un item
d'histoire impossible a resoudre ; c'etait mon outil et non le produit, l'apostrophe etant echappee
dans la valeur du bouton. Et le premier `mvn verify` a echoue parce que mon propre jar tenait le
port. Deux faux defauts que j'ai failli porter." Et, sur la monnaie : "j'ai cru voir sur la planche
que le billet de 20 etait plus petit que celui de 10. J'ai verifie avant d'en faire une reserve,
c'est faux, tous les billets font 210x112. La geometrie corrige l'oeil."

**Ce qu'elle porte a son credit** : elle a mesure le precedent avant d'accepter les items binaires
de questionner le monde plutot que de trancher au jugement (`cp-nature-mot`, `cm1-homophones`,
`cp-son-dans-mot` deja binaires et valides) ; et elle a declare d'avance que `VOI-9` ne gouverne pas
le paquet de voyelles, "pour qu'on ne refuse pas ce paquet dans deux sprints sur une extension que
je refuse moi-meme".

**Sept conditions sans garde executable, a ne jamais laisser lire comme gardees** : `VOI-3`,
`VOI-5`, `VOI-8`, `COU-2`, `COU-3`, `HIS-2`, et **l'exactitude factuelle**. "Rien ne doit suggerer
le contraire."

**Une limite declaree, pas une dette** : un paquet ecrivant "Histoire" la ou les autres ecrivent
"Histoire-geographie" formerait son propre lot, invisible a toute comparaison de chaines - vue par
`nadia-prof` et la matrice, qui comptent des lignes de referentiel et non des chaines.

**Une reserve neuve et datee, matiere pour s28, non une dette de s27** : la consigne et l'enonce se
repetent sur les trois paquets a voix. "Les deux textes sont identiques dans les paquets que j'ai
valides a s26 sans les voir. Je vois plus loin ; je l'ecris comme neuf, pas comme un durcissement de
l'ancien."

### Done pedagogique

**Prononce.** Six lots, aucun refus. Une ligne de matrice ferme (`en cours` -> `couvert-valide`,
graphemes), une ligne restaure (`en cours` -> `couvert-valide`, monnaie, restauration et non
fermeture), quatorze lignes ouvrent (`non couvert` -> `en cours`), zero ligne neuve ferme.

### Done technique

**Prononce.** `mvn -B clean verify` vert sur le reacteur complet, `b51803d`, 1128 tests, 0 echec -
confirme independamment par `yanis-qa` (1125, sur `fe14ea2`, ecart explique et re-verifie ci-dessus)
et par `lea-dev` (1128, sur `b51803d`, chiffre cite dans son propre commit).
