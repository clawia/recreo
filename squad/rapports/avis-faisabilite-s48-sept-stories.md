# s48 - Avis de faisabilite (`theo-archi`) - sept stories de `DEC-0106` : `MAT-CE1-08`, cinq corrections differees de s47, `SOP-CE1-R1`

- **Date** : 2026-10-03
- **Base** : `round-two` a `bfdc7fee`. Arbre de travail a ce commit, plus des modifications non
  commitees limitees a `squad/` (`DEC-0105`, `DEC-0106`, `DEC-index.md`, `MATRICE-couverture.md`,
  dossier de s48). Aucun diff de code ni de contenu dans cet avis.
- **Pourquoi ce fichier est ici** : le planning de s48 (section 5, section 6 point 3) et `DEC-0106`
  (section 6) routent cet avis a `theo-archi`. Meme emplacement et meme nommage que les avis de s44 a
  s47 (`squad/rapports/avis-faisabilite-s4x-*.md`).
- **Ce que cet avis ne vaut pas** : il ne fixe aucun contenu, aucun critere, aucune affectation, et ne
  rejuge aucune condition. Le Done pedagogique reste a `nadia-prof`.

---

## 0. L'etat sur lequel cet avis est fonde est vert

`bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet depuis la racine, sur l'arbre decrit
ci-dessus : **`BUILD SUCCESS`**, 6 modules sur 6, termine le 2026-10-03 a 10:06:59. Version : **JDK
21.0.10**, telle que la machine virtuelle des tests la rapporte (20 lignes *"using Java 21.0.10"* dans
le journal, aucune autre version).

| Ce qui est compte | Resultat | Methode |
|---|---|---|
| Tests du reacteur | **1938, 0 echec, 0 erreur, 0 ignore** (120 / 17 / 400 / 1107 / 288 / 6) | somme des six blocs `[INFO] Tests run:` de ce lancement apres `clean` |
| Tests de `com.recreo.archi` | **209, 0 echec, 0 erreur** | somme des attributs `tests` des 30 rapports `TEST-com.recreo.archi.*.xml` de `bootstrap/target/surefire-reports` |
| Regles ArchUnit | **36 `static final ArchRule`, 35 `@ArchTest`** | `git grep -c -F` sur les fichiers suivis de `domain`, `application`, `adapters`, `bootstrap`, pathspec `':!**/contreexemple/**'`, somme par fichier, lance par `rtk proxy` |

Memes chiffres que la confirmation de s47 (`squad/sprints/s47-tables-ce1-decouverte-cp-et-emc/confirmation-archunit.md`).
Seuls les jobs du `build` sont couverts : `chemins`, `prefixes` et `outils` n'ont pas ete lances pour
fonder cet avis.

## 1. Verdicts

| Code | Story | Verdict |
|---|---|---|
| V1 | `MAT-CE1-08` | **faisable en l'etat**, sans `ADR` ni `ArchRule`. Paquet, entree de catalogue, test de paquet neuf, une entree de `SousPartieDeMathematiquesCe1`, inventaire de `TexteAccentueExposeParLePortEntrantTest`. Quatre pieges de fabrication (section 3), deux questions non bloquantes a `nadia-prof` (Q1, Q2) |
| V2 | `TAB-CE1-R1` | **faisable en l'etat.** YAML seul. Le test de paquet ne lit aucun indice : la correction n'est gardee par rien sans une assertion de plus (section 4) |
| V3 | `AUT-CP-R1` | **faisable en l'etat.** YAML seul. Un defaut du test de paquet a ne pas declencher (section 5) |
| V4 | `ANG-CP-R1` / `ANG-CP-R2` | **faisable en l'etat.** YAML seul. Aucun piege technique (section 6) |
| V5 | `EMC-CP-14`, deux reserves | **faisable en l'etat.** YAML seul. Un piege de `TYP-1` sur la forme donnee par `nadia-prof` (section 7) |
| V6 | `EMC-CM1-10`, deux reserves | **faisable en l'etat.** YAML seul. Forme verifiee contre la fiche. Deux pieges du garde-fou de forme des propositions (section 8) |
| V7 | `SOP-CE1-R1` | **faisable, test de paquet a adapter.** Le marqueur neuf fait rougir le test tel qu'il est ; la cinquieme forme doit y etre encodee avec son jeu fautif. L'arbitrage est deja rendu, contrairement a ce que `DEC-0106` section 3 suppose (section 9) |

**Aucune des sept ne touche un fichier sous `src/main/java`.** Les six corrections touchent un
fichier YAML de `adapters/adapter-content/src/main/resources/contenu/` chacune, plus, pour V2 et V7,
le test de paquet correspondant. `MAT-CE1-08` ajoute en plus une entree a `catalogue.yaml` et des
lignes a deux tests transverses. Aucune mecanique neuve, aucune cle de schema neuve, aucune
persistance (`DEC-0028`).

## 2. Ce qui vaut pour les six corrections

- **Aucune ne change le nombre d'items** de son paquet. `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest`,
  qui fige le lot `Questionner le monde / CP` a 148 items, reste vert sur `AUT-CP-R1`.
- **Aucune ne change une chaine du catalogue** (titre, consigne, competence) :
  `TexteAccentueExposeParLePortEntrantTest` n'est pas a toucher.
- **Copier les formes depuis les fiches de `nadia-prof`, jamais depuis `docs/backlog/backlog.md`.** Le
  backlog les recopie sans accents (*"Un sac oublie, on n'y touche pas : on previent un adulte."*).
  `AucunMotServiNeSEcarteDeSonAccentTest` ne voit pas `oublie`, qui est un mot francais : un texte
  servi a l'enfant sans accent passerait la CI.
- **`TYP-1` s'applique aux indices.** `AucunSigneDoubleNEstDetachableDeSonMotTest` lit enonce,
  propositions **et indice**, et sa dette est nulle pour les cinq paquets corriges (absents de
  `DETTE_PAR_FICHIER`). Tout `?`, `!`, `:` ou `;` ajoute prend un blanc insecable devant lui.
- **`PAR-14` ne s'applique a aucune**, recompte sur les criteres des stories : 5/40, 2/14, 4/12,
  2/11, 5/12, 2/18 items touches.

## 3. `MAT-CE1-08` (V1)

Patron : `PaquetCm1ProblemesEtapesTest` (`ETA-*`, CM1) pour le marqueur de derivation,
`PaquetCe1TablesMultiplicationTest` pour le jeu fautif commite (`ADR-0013` obligation 3).

| Condition | Assertion encodable | Ce qui reste a `nadia-prof` |
|---|---|---|
| `PRO-CE1-1` | marqueur par item declarant ses etapes ; deux etapes au moins un tiers, une etape au moins un quart, aucune a trois | rien |
| `PRO-CE1-2` | chaque operation est une etape d'au moins un cinquieme des items, comptee une fois par item | rien |
| `PRO-CE1-3` | aucun item a une etape n'a la multiplication pour etape | rien |
| `PRO-CE1-4` | aucun nombre ni resultat intermediaire au-dela de 1000, aucune division ; lexique exclu (achat, prix, piece, billet, euro, centime, partage) en mot entier | la technique posee (Q1) ; les formes de partage et de groupement non lexicales |
| `PRO-CE1-5` | marqueur de famille par distracteur, valeur recalculee depuis la derivation ; trois propositions entieres positives distinctes ; rangs entre 10 % et 50 % sur le paquet | la plausibilite de la famille (c), que l'arbitrage lui reserve |
| `PRO-CE1-6` | `LON-CE1-1` et `LON-CE1-2` sont deja tenues par `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` (`ADR-0101`), dette nulle pour un paquet neuf ; presupposes sociaux par lexique | les roles par genre ; `voiture` (Q2) |
| `PRO-CE1-7` | au moins 20 items ; indice de douze mots au plus, non identique sur tout le paquet, sans nombre egal a un resultat intermediaire, a la bonne reponse ou a un distracteur | que l'indice donne la methode de la premiere etape |

**Piege 1, l'ordre des nombres.** Le test du CM1 extrait les trois nombres de l'enonce **dans leur
ordre d'ecriture** et les applique dans cet ordre (`nombresDe`, `recalculer`). L'exemple de
l'arbitrage, *"Léa a 12 billes. Sami lui donne 3 sachets de 5 billes."*, calcule `3 × 5` avant
`12 + 15` : l'ordre d'ecriture n'est pas l'ordre des etapes. Recopier ce patron interdirait cette
forme ou la ferait rougir. Le marqueur doit porter les operandes de chaque etape (par exemple
`3*5=15; 12+15=27`), et le test verifier que chaque operande donne est un nombre de l'enonce. Un
nombre ecrit en lettres (*"trois sachets"*) echappe a `\d+` : l'imposer en chiffres, ou le test ne
voit pas l'operande.

**Piege 2, les mots entiers.** Le test du CM1 cherche ses presupposes par sous-chaine
(`doesNotContain("chat")`) : `achat`, `chaton`, `château` rougissent, `animaux` passe. Et le `\b` de
Java est ASCII sur ce JDK : `Pattern.compile("\\bdur\\b")` trouve `dur` dans `la durée` (verifie sur
21.0.10, section 12). Ecrire les lexiques de `PRO-CE1-4` et `PRO-CE1-6` en mots entiers avec
`Pattern.UNICODE_CHARACTER_CLASS`, pluriels nommes.

**Piege 3, l'indice par sous-chaine.** Le test du CM1 verifie `doesNotContain(bonneReponse)` : une
bonne reponse `2` rougit un indice qui ecrit `12`. Comparer des nombres extraits, pas des
sous-chaines.

**Piege 4, le rang.** Les plafonds de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`
valent exactement leur dette (`DETTE_DE_RANG` 2 sur 2, `DETTE_DE_SEUIL_MINIMUM` 3 sur 3) : aucune
exemption n'est disponible. L'arbitrage le sait deja (section 2.4) : sans les familles (c) et (d), le
rang du milieu vaut zero. Le garde-fou rougit au-dessus de 50 % en strict, `PRO-CE1-5` dit *"au plus
50 %"* : les deux sont compatibles. Propositions en entiers nus, comme au CM1 et comme
`PRO-CE1-5` l'ecrit (*"des entiers positifs"*).

**Adjacences, sans collision.** `ce1-problemes-durees` (`MAT-CE1-12`) sert deja des problemes au
CE1. Un probleme portant sur une duree serait a cette ligne (`ADR-0081`, un item une ligne), et le
garde-fou de rang lit une proposition en `h` ou `min` comme une duree (`ADR-0102`). `PRO-CE1-4`
exclut la monnaie, pas les durees (Q2). Identifiant libre : `ce1-problemes-etapes` n'existe pas ;
`cm1-problemes-etapes` existe, et `AucunTitreDeJeuNePorteSonNiveauTest` interdit de les distinguer
par le titre.

**Sous-partie** : entree `MAT-CE1-08` dans `SousPartieDeMathematiquesCe1`
(`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`, `ADR-0097`), libelle du referentiel sans
le point final : `"Résoudre des problèmes d'addition, de soustraction et de multiplication à une ou
deux étapes"`. Ni deux-points ni point-virgule.

## 4. `TAB-CE1-R1` (V2)

Cinq lignes `indice:` de `ce1-tables-multiplication.yaml` (items `2 × 1` a `10 × 1`). La forme
*"Multiplier par 1, le nombre ne change pas."* est servie mot pour mot par `cm1-tables-multiplication.yaml`
(items `1 × 1` et suivants). Aucun signe double, aucun garde-fou d'indice ne la vise.

**Ce que le test de paquet ne garde pas** : `PaquetCe1TablesMultiplicationTest` ne lit aucun indice.
La correction serait verte, et sa regression aussi. Le critere *"porte sur le facteur 1"* se teste :
tout item dont un facteur vaut 1 sert exactement cet indice. Une assertion, dans le meme commit.

## 5. `AUT-CP-R1` (V3)

Deux lignes `indice:` de `cp-qlm-autrefois-aujourdhui.yaml` (items 7 et 13). Le critere *"ne contient
plus le mot robinet"* se teste en une assertion sur les deux indices.

**Defaut du test de paquet, a ne pas declencher.** `MOT_DEPRECIATIF` de
`PaquetCpQlmAutrefoisAujourdhuiTest` est `\b(difficile|dur|pauvre)\b` sans
`UNICODE_CHARACTER_CLASS`. Sur JDK 21.0.10, il trouve `dur` dans `durée` (section 12) : un indice
corrige qui ecrirait *"la durée"* rougirait pour `BIA-3` a tort. Les deux formes de `nadia-prof` ne le
declenchent pas. C'est exactement le piege que mon avis de s47 (section 4) nommait, et le test l'a
quand meme. Correction en un drapeau, au developpeur qui touche ce test ; je ne l'impose pas a s48.

## 6. `ANG-CP-R1` / `ANG-CP-R2` (V4)

Quatre items de `cp-anglais-culture.yaml` : deux distracteurs des items 10 et 12, deux indices des
items 1 et 2. `PaquetCpAnglaisCultureTest` ne lit ni distracteur ni indice au-dela de leur nombre :
rien n'y rougit. Q5 de s47 reste opposable a tout texte neuf (aucun mot anglais), et seule
`nadia-prof` la juge.

## 7. `EMC-CP-14` (V5)

Indice de l'item 7, enonce de l'item 11 de `cp-emc-alerte.yaml`.

- **Item 7, `TYP-1`.** La forme de la fiche porte `" : "`. Le blanc devant le deux-points doit etre
  insecable, sinon `AucunSigneDoubleNEstDetachableDeSonMotTest` rougit : `cp-emc-alerte` n'a aucune
  dette.
- **Item 11, `LON-1` et `LON-2`.** La forme de la fiche est la seule phrase de situation. L'enonce
  servi garde sa question : *"Hugo voit dans la rue un chien perdu qui a peur. Que doit-il faire ?"*
  compte **14 mots** sous la convention d'`ADR-0089`, pour un plafond de 15.
- Bonnes reponses et distracteurs inchanges : `PaquetCpEmcAlerteTest` et la clause 1 du garde-fou de
  forme ne voient aucune difference.

## 8. `EMC-CM1-10` (V6)

**Forme verifiee contre `squad/conformite/FICHE-bandit-manchot-vote-election-cm1.md`**, troisieme
passage, section 4, qui reconduit les deux reserves du second passage (section 7) sans changement :
(1) `"le délégué de classe"`, distracteur des items 3, 4, 5 et 6, est une personne la ou la question
demande un vote ou un droit ; (2) l'item 11 devient, exemple de la fiche, *"Avec le suffrage
universel, qui a le droit de voter ?"*. La story du backlog les reprend fidelement. La fiche ne donne
pas le distracteur de remplacement de la reserve 1, seulement sa categorie.

**Precision pour `camille-po`** : `DEC-0106` section 2 decrit *"deux distracteurs/une question"*. La
story touche **cinq items sur douze** (quatre distracteurs et un enonce). Toujours sous la moitie :
`PAR-14` ne s'applique pas, le tarif tient.

`PaquetCm1EmcVoteElectionTest` ne contraint pas les distracteurs. Deux clauses de
`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` le font, sur tout le paquet :

- **Clause 3, marge nulle.** L'item 11 a deja ses deux distracteurs marques `seulement`, et pas sa
  bonne reponse : un item marque sur un plancher de deux. Un distracteur de remplacement qui
  rendrait un second item marque fait rougir le paquet.
- **Clause 2.** Un meme remplacement de quatre mots ou plus servi sur les quatre items, que ne partage
  aucune bonne reponse du paquet, fait quatre distracteurs du meme moule : rouge. `"le délégué de
  classe"` y echappe aujourd'hui parce qu'il est bonne reponse des items 1 et 2.

## 9. `SOP-CE1-R1` (V7)

**L'arbitrage est rendu.** `nadia-prof` a tranche le 2026-10-02
(`FICHE-bandit-manchot-soustractions-posees-ce1.md`, section "Arbitrage de `SOP-CE1-R1`") : cinquieme
forme, le decalage des colonnes, de valeur `a - 10 × b`, obligatoire sur tout item sans emprunt.
`SOP-CE1-5` est amendee en consequence dans `ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`.
`DEC-0106` section 3 decrit encore *"arbitrage puis, selon son issue, reprise"*, et la story du backlog
liste encore les deux issues. La story de reprise que la fiche demande a `camille-po` (section 4)
n'existe pas dans le backlog. Le travail de s48 est la reprise seule.

**Le test de paquet rougit tel qu'il est.** `valeurDe` echoue sur tout marqueur inconnu (*"marqueur
SOP-CE1-5 inconnu"*). Ce qu'il faut y ajouter, tel que la fiche le route a `theo-archi` et au
developpeur :

1. le marqueur de la cinquieme forme, recalcule en `a - 10 × b` ;
2. sa regle d'admission : `b` a un chiffre de moins que `a`, valeur positive, meme nombre de chiffres
   que la bonne reponse, calcul sans emprunt ;
3. sa presence obligatoire sur tout item sans emprunt ;
4. un jeu fautif commite qui mord la clause neuve (`ADR-0013` obligation 3), sur le patron de
   `leJeuFautifCommiteEstRefuseSurSonMarqueurErrone`.

**Ce qui reste vert, recompte sur le test** (deux items `2 chiffres - 2 chiffres` remplaces par deux
items `3 chiffres - 2 chiffres`) : `SOP-CE1-2` garde 3 items a deux operandes de deux chiffres pour un
plancher de 3, et 13 a trois chiffres ; `SOP-CE1-3` passe de 13 a 15 qualifiants sur 18, seuil 12.

**Piege 1, `SOP-CE1-4` fixe le chiffre des unites de `b`.** Le decalage a le chiffre des unites de
`a`, la bonne reponse celui de `a - b` : ils ne coincident que si `b` finit par 0. La somme coincide
si `b` finit par 0 ou 5. Le second terme doit donc finir par 0 ou 5, et par 0 si la somme a quatre
chiffres. Verifie par balayage : les 3 110 couples admissibles de la fiche finissent tous par 0 ou 5.

**Piege 2, le rang a marge nulle.** Rangs mesures sur les 16 items a trois propositions du paquet
servi : milieu 7, plus petite 5, plus grande 4. Deux items neufs a trois propositions (decalage dessous,
somme dessus) portent la bonne reponse au milieu : **9 sur 18, soit exactement 50 %**. Le garde-fou
generique rougit au-dessus de 50 % en strict, donc passe, sans aucune marge, et sans exemption
disponible. La regle propre au paquet (*"toujours la plus petite"*) passe de 7 a 5 victoires sur 18.

**Piege 3, commentaires perimes.** L'en-tete du YAML dit *"quatre formes"* et ecrit que la somme est la
*"seule forme disponible sur un item sans aucun emprunt"*. Les deux deviennent faux dans le meme
commit.

## 10. Architecture : rien a encoder en ArchUnit

Chaque condition des sections 3 a 9 porte sur un item ou un paquet YAML. ArchUnit lit du bytecode :
ces conditions sont gardees par les tests de paquet et les garde-fous de balayage, executes par le
job `build`. Aucune dependance neuve entre classes, aucune classe de `domain/mechanic` ou de
`domain/mechanics` touchee, aucun chargeur neuf : `DecouplageMecaniqueContenuTest` et les regles de
couches n'ont rien de neuf a interdire. Aucun `ADR` n'est du par s48.

## 11. Le chantier de sourcing du corpus d'oeuvres du domaine public : refuse pour s48

`DEC-0077` section 4 me route quatre questions : la source, la preuve de domaine public par oeuvre
(`ART-3`), le format d'integration, qui verifie chaque preuve. Je ne l'engage pas a s48. Motif : il est
hors du perimetre gele par `DEC-0106` (section 6), et ses quatre questions demandent un `ADR` avec sa
regle (par exemple, toute image servie resout une entree de corpus portant les champs d'`ART-3`), plus
un travail de recherche de sources que la borne de s48 ne finance pas. L'engager sans `DEC` reviendrait
a fixer la charge du sprint a la place de `camille-po`.

**Ce refus n'est pas un report sans date.** Le chantier attend depuis s29 et bloque neuf lignes. Je
demande a `camille-po`, via `sofia-sm`, de l'inscrire au perimetre de s49. Je rendrai l'`ADR` et sa
regle a l'ouverture du sprint qui l'inscrit.

## 12. Questions et routages, via `sofia-sm`

| Code | Point | A qui | Bloquant ? |
|---|---|---|---|
| Q1 | `PRO-CE1-4`, *"aucune operation qui exigerait une technique posee"* : se lit-elle comme les bornes de `MAT-CE1-03` (additions et soustractions dans la limite de 100) et les tables de `MAT-CE1-07` pour la multiplication ? Si oui, elle s'encode ; sinon elle reste a l'audit | `nadia-prof` | non, mais a trancher avant d'ecrire le test |
| Q2 | `PRO-CE1-4` et `PRO-CE1-6` : une duree est-elle exclue de `MAT-CE1-08` comme la monnaie (adjacence `MAT-CE1-12`) ? Le mot `voiture` est-il exclu, ou seulement la voiture possedee par la famille ? | `nadia-prof` | non |
| Q3 | `SOP-CE1-R1` : arbitrage rendu le 2026-10-02 ; `DEC-0106` section 3 et la story du backlog le decrivent comme a venir, et la story de reprise demandee par la fiche n'existe pas | `camille-po` | oui, avant d'affecter un developpeur a V7 |
| Q4 | `EMC-CM1-10` : cinq items touches et non trois (section 8). Tarif inchange | `camille-po` | non |

## 13. Mesure

- **`mvn verify`** : section 0. Journal conserve hors du depot.
- **Garde-fous cites** : lus dans le code a `bfdc7fee`. Aucun n'a ete execute sur un paquet corrige ou
  neuf, puisqu'aucun n'existe : leurs verdicts sur les sept stories sont deduits de leur code.
- **Le `\b` ASCII** : programme Java de poste, non commite, lance sur JDK 21.0.10.
  `Pattern.compile("\\bdur\\b")` trouve une correspondance dans `la durée` et dans `c'est dur`, pas
  dans `la vie était dure`.
- **`SOP-CE1-R1`** : script Python de poste, non commite, qui balaie `100 <= a <= 999`,
  `10 <= b <= 99` sans emprunt et applique la regle d'admission de la fiche et `SOP-CE1-4`. Resultat :
  22 275 couples, 11 022 admettant le decalage, 3 110 tenant `SOP-CE1-4`, les memes que la fiche.
  Chiffres des unites de `b` sur ces 3 110 : {0, 5}. Rangs : comptes a la main sur les 18 items de
  `ce1-soustractions-posees.yaml`.
- **Comptes d'items touches** (section 2) : criteres d'acceptation des stories de
  `docs/backlog/backlog.md`, et la fiche pour `SOP-CE1-R1`.
- **Gardes lancees sur ce fichier** : `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (10
  tests) et `ToutesLesReferencesDeDecisionResolventTest` (3 tests), par `bash outils/mvn-jdk21.sh -B
  -pl bootstrap -am test -Dtest=...`, zero echec, Java 21.0.10 lu dans les rapports Surefire.
  `verifier-chemins.sh` sur ce fichier : vert.
