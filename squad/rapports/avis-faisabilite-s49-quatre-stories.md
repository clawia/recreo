# s49 - Avis de faisabilite (`theo-archi`) - quatre stories de `DEC-0107` et le chantier de sourcing du corpus d'oeuvres

- **Date** : 2026-10-03
- **Base** : `round-two` a `cafd9fe5`, arbre propre. Aucun diff de code ni de contenu dans cet avis.
- **Pourquoi ce fichier est ici** : `DEC-0107` (sections 5 et 8) et le planning de s49 (section 5)
  routent a `theo-archi` un avis sur les trois corrections et sur `FRA-CE1-01`, et l'`ADR` du chantier
  de sourcing. Meme emplacement et meme nommage que les avis de s44 a s48.
- **Ce que cet avis ne vaut pas** : il ne fixe aucun contenu, aucune affectation, et ne rejuge
  aucune condition. Le Done pedagogique reste a `nadia-prof`.

---

## 0. L'etat sur lequel cet avis est fonde est vert

`bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet depuis la racine, a `cafd9fe5` :
**`BUILD SUCCESS`**, 6 modules sur 6, termine le 2026-10-03 a 15:27:05. Version : **JDK 21.0.10**,
telle que la machine virtuelle des tests la rapporte (20 lignes *"using Java 21.0.10"* dans le
journal, aucune autre version).

| Ce qui est compte | Resultat | Methode |
|---|---|---|
| Tests du reacteur | **1954, 0 echec, 0 erreur, 0 ignore** (120 / 17 / 400 / 1123 / 288 / 6) | somme des six blocs `[INFO] Tests run:` de ce lancement apres `clean` |
| Tests de `com.recreo.archi` | **209, 0 echec, 0 erreur** | somme des attributs `tests` des 30 rapports `TEST-com.recreo.archi.*.xml` de `bootstrap/target/surefire-reports` |
| Regles ArchUnit | **36 `static final ArchRule`, 35 `@ArchTest`** | `git grep -c -F` sur les fichiers suivis de `domain`, `application`, `adapters`, `bootstrap`, pathspec `':!**/contreexemple/**'`, somme par fichier, lance par `rtk proxy` |

Ecart avec l'avis de s48 : +16 tests, tous dans `adapter-content` (1107 a 1123), travail livre a
s48, dont les 12 de `PaquetCe1ProblemesEtapesTest`. Seuls les jobs du `build` sont couverts : `chemins`, `prefixes` et
`outils` n'ont pas ete lances pour fonder cet avis.

## 1. Verdicts

| Code | Story | Verdict |
|---|---|---|
| V1 | `PRO-CE1-R1` / `PRO-CE1-R2` | **faisable en l'etat.** YAML et test de paquet. Valeurs recalculees (section 3). Le lexique du test est sans accents : c'est la cause que `nadia-prof` me route, a payer dans le meme commit |
| V2 | `ANG-CP-R3` + `ANG-CP-R1` (item 12) + `ANG-CP-R2` (item 2) | **faisable en l'etat.** YAML seul ; une assertion de paquet recommandee, mesuree sur le materiau reel (section 4) |
| V3 | `EMC-CP-14`, item 7 | **faisable en l'etat.** YAML seul ; une assertion recommandee (section 5) |
| V4 | `FRA-CE1-01` | **faisable en l'etat**, sans `ADR` ni `ArchRule`. Paquet, entree de catalogue, test de paquet neuf avec jeu fautif, inventaire de `TexteAccentueExposeParLePortEntrantTest`. Six pieges (section 6), deux questions non bloquantes (Q1, Q2) |
| V5 | Chantier de sourcing | **l'`ADR` existe : `ADR-0082`, Accepte le 2026-09-05**, avec son garde-fou vert. Je n'en ecris pas un second (section 7) |

**Aucune des quatre stories ne touche un fichier sous `src/main/java`.** Les trois corrections
touchent un YAML de `adapters/adapter-content/src/main/resources/contenu/` chacune, plus, pour V1, le
test de paquet. `FRA-CE1-01` ajoute un YAML, une entree de `catalogue.yaml`, un test et son jeu fautif
sous `src/test`, et des lignes a un test transverse. Aucune mecanique, aucune cle de schema, aucune
persistance (`DEC-0028`). Le seul code de production qui nomme `CE1` est `OrdreDesNiveaux`
(`application`), qui ordonne les niveaux sans regarder la matiere ; aucun ne nomme `Français`
(`git grep` sur `*/src/main/java/*` et les gabarits).

## 2. Ce qui vaut pour les trois corrections

- **Copier les formes depuis les fiches, jamais depuis le backlog**, qui les ecrit sans accents
  (*"Petite Sirene"*, *"coeur"*, *"oublie"*). Meme piege qu'a s48, toujours non garde pour un mot
  francais sans accent.
- **Aucune ne change le nombre d'items ni une chaine du catalogue** : `TexteAccentueExposeParLePortEntrantTest`
  et `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest` ne sont pas a toucher.
- **`PAR-14` ne s'applique a aucune**, recompte sur les criteres : 4/24, 3/12, 1/11.

## 3. `PRO-CE1-R1` / `PRO-CE1-R2` (V1)

**`PRO-CE1-R1`, items 9, 10, 11.** Sur un item a deux etapes, le test n'admet que (a), (b) ou (c) ;
(d) exige une etape unique. "Deux autres familles" que (c) veut donc dire `fam=a,b`, et rien d'autre.
Valeurs recalculees par la regle du test (`valeurDeLaFamille`) :

| Item | Derivation | Bonne | (a) | (b) | Rang |
|---|---|---|---|---|---|
| 9 | `add(40,10);sub(R,15)` | 35 | 50 | 65 | plus petite |
| 10 | `add(38,14);sub(R,18)` | 34 | 52 | 70 | plus petite |
| 11 | `add(44,16);sub(R,22)` | 38 | 60 | 82 | plus petite |

Distracteurs dans l'ordre du marqueur : `["50", "65"]`, `["52", "70"]`, `["60", "82"]`. Rangs du paquet :
plus petite 7, milieu 8, plus grande 9, soit 29 %, 33 %, 37,5 % - les bornes que `nadia-prof` a
annoncees, dans 10 a 50 %. Indices des items 9 et 11 sans nombre : rien ne rougit.

**`PRO-CE1-R2`, item 8.** Enonce et marqueur sont sur la meme ligne, et le test retrouve le marqueur
par l'enonce : changer l'un sans l'autre est impossible, la forme de la fiche ne change aucun nombre.

**Le lexique sans accents, cause de ce que `nadia-prof` decrit** (fiche, section 6).
`LEXIQUE_PRO_CE1_4` ecrit `piece`, `achete`, `partagee`, `divise` : sous `UNICODE_CHARACTER_CLASS`
et `CASE_INSENSITIVE`, `\bpiece\b` ne trouve pas `pièce`, les points de code different. C'est le
piege 2 de mon avis de s48 deplace d'un cran : le `\b` a ete corrige, pas les mots. Correction, dans
le meme commit que `PRO-CE1-R2` : formes accentuees ajoutees (`pièce`, `acheté`, `partagé`,
`divisé` et leurs flexions, `divise` garde comme verbe), plus `magasin`, `vente`, `vendre`, `vend`,
`vendent`, `vendu` et flexions, qui gardent le critere de la story. Une assertion en memoire qui fait
trouver `pièce` et `vend` au verificateur mord l'ajout.

**Commentaires perimes** : l'en-tete du YAML donne les rangs 4/11/9 et nomme *"le magasin scolaire"*.
Faux dans le meme commit.

## 4. `ANG-CP-R3`, `ANG-CP-R1` item 12, `ANG-CP-R2` item 2 (V2)

Trois lignes de `cp-anglais-culture.yaml`. `PaquetCpAnglaisCultureTest` ne lit ni distracteur ni
indice au-dela de leur nombre : rien n'y rougit, et rien n'y garde la correction.

**Assertion recommandee, mesuree sur le materiau reel** : *aucun mot de quatre lettres ou plus de
l'enonce ne figure dans la seule bonne reponse.* Sur les 12 items a `cafd9fe5`, elle trouve un item,
le 10 (`statue`) ; sur le paquet corrige avec les formes de la fiche, zero. C'est la propriete que
`nadia-prof` nomme (*"un mot de la question repete dans la seule bonne reponse"*), pas l'item.
`\w` de Java est ASCII sans `UNICODE_CHARACTER_CLASS` : `États` serait coupe en `tats`.

**Clause 2 de `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`** : *"ils dessinent le drapeau"*
et *"ils lèvent le drapeau"* partagent le moule de leurs quatre premiers mots, comme les deux
distracteurs actuels. Deux distracteurs pour un plancher de quatre : vert, sans changement de marge.

## 5. `EMC-CP-14`, item 7 (V3)

Une ligne `indice:` de `cp-emc-alerte.yaml`. La forme de la fiche retire le `" : "` : le blanc
insecable part avec lui, aucun `TYP-1` a tenir.

**Ne pas generaliser la propriete a "l'indice ne reprend aucun mot de la bonne reponse"** : les items
4, 8 et 10, valides, reprennent *adulte*. L'assertion qui tient : *aucun indice du paquet ne contient
une forme de `prévenir`* (motif `pr[ée]v(en|ien)`). Rouge sur l'item 7 a `cafd9fe5`, sur lui seul ;
vert apres correction.

## 6. `FRA-CE1-01` (V4)

Patron : `PaquetCe1ProblemesEtapesTest` (marqueur relu dans le fichier source, jeu fautif commite,
`ADR-0013` obligation 3), `PaquetCe1DivisionTest` pour l'absence de `texteAVoixHaute`. Fichiers, sur
le modele de `d08d6bbe` : YAML, `catalogue.yaml`, test, jeu fautif, `TexteAccentueExposeParLePortEntrantTest`.
`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` ne voit que les mathematiques : rien a y
ajouter.

| Condition | Assertion encodable | Ce qui reste a `nadia-prof` |
|---|---|---|
| `TRV-CE1-1` | `niveau: "CE1"`, `lignesDeReferentiel: ["FRA-CE1-01"]`, aucun `texteAVoixHaute`, aucune `consigneAdulte` | rien |
| `TRV-CE1-2` | trois propositions distinctes ; marqueur par item `cible=` et `fam=` ; bonne reponse strictement la plus longue sur la moitie des items au plus (Q2) | qu'une seule soit acceptable |
| `TRV-CE1-3` | un seul mot entre `«` et `»` dans l'enonce, qui contient une graphie de la famille cible | aucune prononciation regionale |
| `TRV-CE1-4` | lexique exclu en mots entiers, formes accentuees | rien |
| `TRV-CE1-5` | indice present, douze mots au plus, non identique sur le paquet, ne contient pas la bonne reponse en mot entier | qu'il donne une methode (Q1) |
| `TRV-CE1-7` | 20 items au moins | rien |
| `LON-CE1-5` | une proposition, six mots au plus, une phrase | rien |
| `GRC-CE1-1` | `cible` dans un vocabulaire ferme de six familles ; mot de reference et bonne reponse en portent une graphie ; chaque famille au moins un dixieme | rien |
| `GRC-CE1-2` | lexique d'exclusion (`ville`, `mille`, `village`, `tranquille`, flexions) refuse en bonne reponse | qu'une seule proposition porte le son ; la terminaison verbale `-ient` |
| `GRC-CE1-3` | famille de chaque distracteur declaree dans (a), (b), (c) ; (a) au moins un tiers ; un (a) contient la graphie cible suivie d'une voyelle, ou de `n` puis d'une voyelle | (b) et (c) |

`LON-CE1-1` et `LON-CE1-2` sont deja tenues par `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest`
(`ADR-0101`), dont la population est le niveau, francais compris : dette nulle pour un paquet neuf.

**Piege 1, les lexiques en mots entiers et accentues.** `graphème`, `phonème`, `préfixe` portent un
accent : le defaut de la section 3 se reproduit ici a l'identique si on copie le patron. Mots entiers
sous `UNICODE_CHARACTER_CLASS`, formes accentuees ecrites, flexions nommees.

**Piege 2, l'indice par sous-chaine.** Une bonne reponse `main` rougirait un indice qui ecrit
`demain`. Comparer des mots entiers, comme le piege 3 de s48 comparait des nombres.

**Piege 3, le terme `-ient` ne se separe pas par un suffixe.** `vient`, `tient`, `devient` portent le
son de *chien* ; `étudient`, `patient`, `client` ne le portent pas. Une regle de suffixe rougirait les
premiers ou laisserait passer les seconds. Pas d'assertion : `nadia-prof` garde ce cas. Le lexique
de `GRC-CE1-2` est un perimetre enumere assume (`ADR-0039`), vert par defaut sur `Lille`, `million`,
`distiller`.

**Piege 4, la famille (a) n'existe que sur les graphies nasales.** Les trois exemples de `GRC-CE1-3`
sont `ain`, `ein`, `ien` : une voyelle apres le `n` defait la nasale. Sur `ill`, `gn` et `eil`, une
voyelle qui suit ne change pas la lecture. Les items qui ciblent `ain`/`ein`, `oin`, `ien` portent
donc tout le tiers de (a). Sur un paquet de 24 : au moins 8 items nasals, et chacune des six familles
au moins 3 fois.
Arithmetique compatible, a tenir a la redaction.

**Piege 5, les guillemets.** `«` et `»` portent un blanc insecable a l'interieur
(`LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest`, clause 2, `ADR-0060`) et `?` en porte un
devant lui (`TYP-1`). Ecrire le caractere, jamais sa notation. Et le marqueur se trouve par l'enonce :
`LIGNE_ITEM` exige le marqueur sur la ligne `- enonce:`.

**Piege 6, le titre.** Il ne porte pas son niveau (`AucunTitreDeJeuNePorteSonNiveauTest`) et ne
reprend pas *"Le bon son"* (CP). Le second point est une convention de `TexteAccentueExposeParLePortEntrantTest`,
pas un garde-fou.

**Non applicable** : `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` ne lit que des
propositions numeriques, et `TRV-CE1-2` le remplace ici. Les trois clauses de
`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` exigent trois ou quatre mots : un mot seul
n'y entre pas.

## 7. Le chantier de sourcing : l'`ADR` existe, c'est `ADR-0082`

**Je corrige mon avis de s48 (section 11).** J'y ecrivais que les quatre questions de `DEC-0077`
section 4 *"demandent un `ADR` avec sa regle"*, et je proposais pour regle *"toute image servie resout
une entree de corpus portant les champs d'`ART-3`"*. **Cet `ADR` est le mien, ecrit le 2026-09-05,
le jour de `DEC-0077`, au commit `4f594d64`** : `ADR-0082`, Accepte. Sa regle est exactement celle que
je proposais, et elle tourne : `ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest`,
18 tests verts dans le lancement de la section 0. `DEC-0105` (*"aucun `ADR`"*) et `DEC-0107` section 5
reprennent mon erreur.

| Question de `DEC-0077` | Reponse d'`ADR-0082` |
|---|---|
| La source | une renonciation `CC0-1.0` publiee par l'institution, verifiee oeuvre par oeuvre, sans liste blanche (decision 1) |
| La preuve par oeuvre (`ART-3`) | auteur mort avant 1900, ou oeuvre anonyme anterieure a 1900 ; neuf champs dans `squad/corpus/oeuvres-domaine-public.yaml` (decisions 3 et 4) |
| Le format | JPEG sous `static/oeuvres/`, identifiant `oeuvre-[a-z0-9-]+`, forme d'item `question` et cles `illustration` inchangees, zero ligne de chargeur (decisions 2 et 5) |
| Qui verifie | le garde-fou, pour la forme et la refaisabilite ; `nadia-prof`, pour l'attribution, la fidelite et `ART-5`. *"Une entree qui ment passe"* |

**Pourquoi je n'ecris pas un second `ADR`.** Il redeciderait la meme chose : deux sources de la meme
decision divergent. Un numero n'est jamais reutilise ; en consommer un pour une copie serait une
dette.

**Pourquoi aucune `ArchRule`.** Les objets juges sont des fichiers d'image, des entrees de registre et
des repertoires, aucun n'est un type Java (`ADR-0082`, section "Regle executable"). Ce qui reste a
faire n'ajoute aucune dependance entre couches : T2 et T4 sont des garde-fous de test, T3 change un
gabarit et un controleur d'`adapter-web` par un prefixe de chaine. `DecouplageMecaniqueContenuTest` et
les regles de couches n'ont rien de neuf a interdire.

**Ce qui reste, et qui ne demande aucune decision neuve** (`ADR-0082`, decision 7 et taches) :

- **T2, T3, T4, environ deux jours de developpeur** : resolution d'un identifiant `oeuvre-` dans
  `LIllustrationDeContenuResoutEtAucuneNeDortTest`, seconde branche de gabarit et seconde exemption
  etroite de `RessourcesTiercesTest` avec leur morsure, plafond de pixels, cliquet de poids mesure,
  materiau de reference remplace par du materiau reel. Chacun a son alarme dans le depot, sauf le
  plafond de pixels, qui est a ecrire avec la premiere reproduction. Les trois garde-fous existent a
  `cafd9fe5`.
- **T5, la constitution du corpus, commence par le choix des oeuvres de `nadia-prof`** (`DEC-0077`
  section 4, `ADR-0082` taches). Un developpeur seul ne peut pas l'engager. `DEC-0107` dit *"aucun
  Done pedagogique"* : c'est exact, mais ce choix consomme du temps de `nadia-prof` hors de sa borne.
- **Les cinq questions qu'`ADR-0082` route a `nadia-prof`** (le registre a la place du commentaire de
  paquet pour `ART-3`, le vingtieme siecle ampute, `REC-1` sur une reproduction, le texte visible dans
  une oeuvre, le prix de la verification d'attribution) n'ont pas de reponse ecrite que j'aie trouvee.
  Elles bornent les paquets T6 a T8, pas le dispositif T2 a T4.

## 8. Questions et routages, via `sofia-sm`

| Code | Point | A qui | Bloquant ? |
|---|---|---|---|
| Q1 | `TRV-CE1-3` et `TRV-CE1-5` : un indice peut-il nommer des lettres pour dire ou regarder (*"regarde la lettre apres a, i, n"*) sans employer une graphie comme nom d'un son ? | `nadia-prof` | non, mais a trancher avant d'ecrire les indices |
| Q2 | `TRV-CE1-2`, *"strictement la plus longue"* : sur des mots seuls, la longueur se compte en lettres ? | `nadia-prof` | non ; le test compte en lettres si rien n'est dit |
| Q3 | `DEC-0105` section 2 et `DEC-0107` section 5 disent qu'aucun `ADR` n'existe pour le chantier : `ADR-0082` existe depuis s29. La borne de forme de `DEC-0107` (l'`ADR` rendu avant la cloture) est satisfaite depuis le 2026-09-05 | `camille-po` | non |
| Q4 | Affecter T2 a T4 a un developpeur ; T5 attend le choix des oeuvres par `nadia-prof` | `sofia-sm` | non |

## 9. Mesure

- **`mvn verify`** : section 0. Journal conserve hors du depot.
- **Garde-fous cites** : lus dans le code a `cafd9fe5`. Aucun n'a ete execute sur un paquet corrige ou
  neuf, puisqu'aucun n'existe. Leurs verdicts sur les quatre stories sont deduits de leur code.
- **Valeurs de V1** : calculees a la main par la regle de `valeurDeLaFamille`. Les rangs sur 24 items
  partent des rangs que la fiche mesure (4/11/9) : trois items passent du milieu a la plus petite.
- **Assertions de V2 et V3** : script Python de poste, non commite, sur le YAML a `cafd9fe5` puis sur
  le meme YAML corrige en memoire avec les formes des fiches. V2 : mots extraits par `\w+` Unicode,
  quatre lettres au moins, presents dans l'enonce et la bonne reponse, absents des deux distracteurs.
  V3 : relu sur les onze indices.
- **`ADR-0082`** : fichier, commit `4f594d64` (`git log` sur `squad/corpus/oeuvres-domaine-public.yaml`,
  seul commit), rapport Surefire du garde-fou (`tests="18"`, zero echec).
- **Gardes lancees sur ce fichier et sur la correction de l'avis de s48** :
  `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (10 tests) et
  `ToutesLesReferencesDeDecisionResolventTest` (3 tests), par `bash outils/mvn-jdk21.sh -B -pl
  bootstrap -am test -Dtest=...`, zero echec, Java 21.0.10 lu dans les rapports Surefire.
  `verifier-chemins.sh` sur les deux fichiers : vert.
