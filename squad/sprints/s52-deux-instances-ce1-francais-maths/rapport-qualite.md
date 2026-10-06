# Rapport qualite de cloture - s52

**Auteur** : `yanis-qa`. **Date** : 2026-10-07. **Arbre mesure** : `round-two` a `40daa419` (hash complet
`40daa419f03e70cb89e342c1cbcc96346cbe0956`), arbre fusionne du retrait, de l'instance A, de l'instance C
et des amendements, mesure dans un worktree dedie ouvert en tete detachee puis rattache a la branche
`s52-yanis-qualite`. `git status --porcelain` vide apres les builds, hors le test ajoute en section 4.

**Perimetre** : les quatre jobs de `.github/workflows/ci.yml`, relu avant la mesure. Le job `build` est
mesure en reacteur complet, sans `-pl`. Ce rapport porte la porte **technique** uniquement. Il ne dit rien
du Done pedagogique, prononce par `nadia-prof` seule dans les `FICHE-<jeu>.md`.

Deux commandes du workflow sont adaptees sur ce poste, sans effet sur le verdict : les juges Python sont
lances avec `python` (la boucle du workflow retombe sur `python` si `python3` est absent), et
`mvn -B verify` est lance par l'enveloppe `outils/mvn-jdk21.sh -B clean verify` (JDK de la toolchain,
`clean` en plus).

## 1. Les quatre jobs, sur `40daa419`

### `prefixes` : vert

- `bash .github/scripts/verifier-prefixes-test.sh` : tous les cas passent, code 0.
- `bash .github/scripts/commit-atteignable-test.sh` : tous les cas passent, code 0.
- `git log --no-merges --format=%s b4ce7d93..HEAD | bash .github/scripts/verifier-prefixes.sh` : `Tous les
  commits portent un prefixe d'agent connu.`, code 0. La plage `b4ce7d93..40daa419` (commit de planning de
  s52) porte 23 sujets hors fusions. Ce n'est pas la plage `github.event.before..GITHUB_SHA` d'un push reel :
  la branche `BASE` du workflow n'est pas exercee, seulement ses deux scripts.

### `chemins` : vert

- `bash .github/scripts/verifier-chemins-test.sh` : tous les cas passent, code 0.
- `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh` :
  `Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.`, code 0. Tous les fichiers
  suivis, comme le job CI.

### `outils` : vert

Boucle du workflow rejouee a l'identique, trois motifs : `outils/*-test.sh` 5 juges,
`squad/outils/*/*-test.sh` 2 juges, `outils/*-test.py` 2 juges. **9 juges purs executes, 0 echec**, aucun
motif vide.

### `build` : vert

Commande : `bash outils/mvn-jdk21.sh -B clean verify`, depuis la racine du worktree, reacteur complet.
Resultat : `BUILD SUCCESS`, 7 min 12 s, code de sortie 0, les six modules `SUCCESS`.

**JDK** : la machine virtuelle des tests rapporte `Started ... using Java 21.0.10` (20 occurrences dans le
journal, une seule valeur).

Le second pas du workflow (`mvn -B -pl bootstrap -am package`, fat jar) n'a pas ete lance : `verify` produit
le jar et `JarRecreoIT` l'exerce, mais le pas lui-meme n'est pas rejoue ici.

## 2. Compte de tests

Methode : somme de l'attribut `tests` des rapports XML de surefire et failsafe sous chaque `target/`, apres
le `clean verify` ci-dessus, sur `40daa419`.

| Module | surefire | failsafe |
|---|---|---|
| domain | 120 | - |
| application | 17 | - |
| adapter-web | 401 | - |
| adapter-content | 1318 | - |
| bootstrap | 310 | 6 |

**2172 tests au total (2166 unitaires et ArchUnit, 6 d'integration), 0 echec, 0 erreur, 0 ignore.**

Second lancement, meme commande, apres l'ajout du test de la section 4 (arbre = `40daa419` + ce seul
fichier) : `BUILD SUCCESS`, 13 min 30 s, **2174 tests** (bootstrap surefire 312, le reste inchange), 0 echec.
La duree double entre les deux lancements, le poste etant charge par d'autres couloirs ; je ne conclus rien
sur la duree.

## 3. Ecart de base : 2084 a 2172

Methode : pour chaque fichier de test touche par `git diff --name-status <base> HEAD -- '*/src/test/java/*.java'`,
comptage des annotations `@Test`, `@ParameterizedTest`, `@ArchTest`, `@TestFactory`, `@RepeatedTest` a la base
et a `HEAD`. Controle : la somme par module de ces deltas contre `ca4c934a` (arbre mesure par le rapport de
s51 : adapter-content 1237, bootstrap surefire 295) vaut **+81** et **+15**, et la mesure de ce rapport donne
1318 - 1237 = +81 et 310 - 295 = +15. Les deux concordent a l'unite ; le comptage par annotation est donc
fiable sur ce perimetre. Les bases 2084 et 2125 n'ont **pas** ete mesurees par un build : elles sont
reconstituees par arithmetique sur ces comptes.

| Etape | Delta | Origine | Module |
|---|---|---|---|
| s51 (`ca4c934a`) | 2076 | rapport qualite s51 | - |
| ADR-0080 A2 (`edf15763`, `[THEO]`, avant `b4ce7d93`) | +6 | `DeuxZonesDUnItemNeSeChevauchentJamaisTest` neuve (4), `LaBonneZoneNEstPasPredictibleSurUnPaquetTest` 4 a 6 | adapter-content |
| = base de planning `b4ce7d93` | 2082 | | |
| Retrait | +2 | `LesJeuxRetiresDuCatalogueNeSontPasServisTest` neuve (2) | bootstrap |
| = base `b4ce7d93` + retrait | 2084 | | |
| Instance A | +41 | `PaquetCe1CategoriesTest` 11, `PaquetCe1ConjugaisonPresentTest` 14, `PaquetCe1SynonymesContrairesTest` 12, `PaquetCe1AlphabetiqueTest` 11 a 13, `PaquetCe1TempsTest` 10 a 12 | adapter-content |
| = branche A | 2125 | | |
| Instance C | +47 | `PaquetCe1ComparerRangerTest` 16, `PaquetCe1SensMultiplicationTest` 10, `PaquetCe1SymetrieTest` 8 (adapter-content, 34) ; `LesFiguresDeSymetrieMontrentCeQueLeursItemsAffirmentTest` 6, `LesReseauxDePointsMontrentCeQueLeursItemsAffirmentTest` 7 (bootstrap, 13) | adapter-content, bootstrap |
| = tip `40daa419` | 2172 | | |

Delta de `2084` a `2172` : **+88** = adapter-content +75 (41 de A, 34 de C), bootstrap +13. Les comptes par
classe neuve concordent avec les rapports XML (11, 16, 14, 10, 8, 12, 6, 7, 2). Les autres fichiers touches
(`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `TexteAccentueExposeParLePortEntrantTest`,
`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`, `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest`,
les trois balayages de designation, `LIllustrationDeContenuResoutEtAucuneNeDortTest`,
`ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest`) changent de lignes, pas de
nombre d'annotations. Ce que je n'ai pas verifie : que les assertions de ces fichiers modifies restent aussi
fortes qu'avant.

Delta annonce sur la base `ca4c934a` (2076) : **+96** (+81 adapter-content, +15 bootstrap), arbre `40daa419`.
`sofia-sm` etablit le total sur l'arbre fusionne final.

## 4. Q6 : le critere "sans redemarrage" du retrait

Le critere (story `S52-RETRAIT-SCIENCES-CM1`) n'a pas d'occurrence en production. Je le teste sur un cas
fabrique : `UnePartieOuverteSurUnJeuRetireNeServeAucunResultatTest` (bootstrap, `@SpringBootTest` + `MockMvc`
sur le vrai catalogue, a commiter sur `s52-yanis-qualite`, a fusionner apres le retrait). La session HTTP est
amorcee par une vraie partie du vrai catalogue, puis son identifiant de jeu est remplace par un identifiant
absent du catalogue ; un coup est joue sur ce jeu.

- Verifie : reponse 200, ecran d'erreur francais (*"Oups ! Le jeu a un souci."*, lien de retour), aucun corps
  technique, aucun ecran de resultat. Temoin positif dans le meme fichier : le meme coup sur un jeu du
  catalogue ne donne pas l'ecran d'erreur, et le journal est vide. La cause est lue dans le journal du
  gestionnaire d'erreurs (*"jeu inconnu : ..."*) : sans cela, une derive des noms d'attributs de session
  (`recreo.jeuId`, package-private dans `EtatDePartieEnSession`, recopie en chaine dans le test) rendrait le
  test vert sur une simple session vide.
- Resultat : 2 tests verts, ecran d'erreur conforme au critere.

**F1 (a router par `sofia-sm`, pas corrige).** Le critere dit *"meme comportement que `S5-1`"*. L'ecran est le
meme, le journal ne l'est pas. Sur ce cas, `PartieService.trouver` leve `NoSuchElementException`, que
`GestionnaireDesErreurs.defautImprevu` journalise en **ERROR avec sa pile**. Un `POST /parties/{inconnu}` de
`S5-1` passe par `PartieController.jeuOuEchec`, leve `PartieIndisponible` et journalise en INFO
(`UneErreurImprevueLaisseUneTraceTest` l'epingle). Mesure : un test provisoire (retire) assertant le niveau
INFO sur ce cas a rendu `["ERROR"]`. Au sens d'`ADR-0018`, cette situation (jeu retire, session conservee) est
prevue et ne devrait pas se classer en defaut. Le test commite n'asserte pas le niveau, pour ne pas figer un
comportement que je juge discutable ; la decision est a `theo-archi`.

## 5. Mise a l'epreuve de garde-fous sur le materiau reel

Commande : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,bootstrap -am test -Dtest=<classes>
-Dsurefire.failIfNoSpecifiedTests=false`. Chaque mutation porte sur un fichier de contenu de production, est
retiree par `git checkout -- <fichier>` apres la mesure, et `git status --porcelain` le confirme.

| Mutation | Garde-fou | Rouge constate |
|---|---|---|
| `ce1-symetrie.yaml`, item 1 : zone `ligne-c` deplacee en `{ x: 50, y: 8, 16, 16 }`, qui recouvre `ligne-b` | `ZON-13`, `DeuxZonesDUnItemNeSeChevauchentJamaisTest.surLeContenuReelDeProduction...` | oui : `les zones "ligne-b" ... et "ligne-c" ... se chevauchent ... (ZON-13)`, 1 echec sur 4 |
| `ce1-symetrie.yaml`, les 11 items : la bonne zone declaree en premier | rang de la bonne zone (A2), `LaBonneZoneNEstPasPredictibleSurUnPaquetTest` | oui : `la bonne zone est la premiere zone declaree de son item sur 11 items sur 11`, 1 echec sur 6 |
| `ce1-categories.yaml`, les 24 items : ` de la maison` ajoute a chaque `bonneReponse` | `TRV-CE1-2`, `PaquetCe1CategoriesTest.trv2...` | oui : `TRV-CE1-2 : 24 items ou la bonne reponse est strictement la plus longue sur 24` ; 2 autres tests du meme fichier rougissent aussi (`ctg2ctg3...`, `trv5...`) |

Constats :

- **ZON-13 et le rang de la bonne zone d'A2 ont rencontre un contenu de designation de CE1** : les deux
  mordent sur `ce1-symetrie`. Le planning de s52 notait qu'ils n'en avaient pas encore rencontre ; je n'ai pas relu les paquets de designation anterieurs pour le confirmer.
- Sous les deux mutations de `ce1-symetrie`, `PaquetCe1SymetrieTest` (8 tests) reste **vert** : il ne juge ni
  le chevauchement ni le rang. Ces deux fautes ne sont arretees que par les garde-fous partages. Si l'un des
  deux perd `ce1-symetrie` de son perimetre, rien d'autre ne le dira.
- Non mis a l'epreuve : le garde-fou d'explication (`LExplicationDUnItemReelAtteintLEcranDeRetourTest`). J'ai
  choisi `TRV-CE1-2` entre les deux candidats ; je ne declare donc pas l'autre eprouve.
- Le test de la section 4 n'a pas ete mute (correctif retire) : son temoin positif le protege d'une session
  vide, mais je n'ai pas retire `trouver` de `PartieService` pour voir le rouge. Non eprouve.

## 6. Fins de ligne (condition CRLF de l'incident de cloture)

Le poste a `core.autocrlf=true` (`git config --show-origin`). Je n'ai pas fabrique de copie jetable : le
worktree de ce rapport est **deja** dans la condition demandee. `git ls-files --eol` : les 820 fichiers
`*.svg`, `*.yaml` et `*.java` suivis sont `i/lf w/crlf` (index LF, copie de travail CRLF), comme 2203 fichiers
au total ; les 17 fichiers restes LF sont ceux que `.gitattributes` declare `-text` (`static/vendor/**` et ses
contre-exemples), voulus. Le `clean verify` de la section 1 s'est donc execute sur des sources CRLF : **2172
tests verts, aucun test fragile aux fins de ligne**. Le correctif `173b48f7` (tests de dessins insensibles aux
fins de ligne) tient sur cet arbre. Limite : je n'ai pas mesure l'autre sens (LF partout), qui est celui de la
CI Linux et qui n'a pas ete le cote cassant de l'incident.

## 7. Criteres d'acceptation que ni un test ni un automate ne tranchent

Source : les criteres des stories engagees dans `docs/backlog/backlog.md` (section Sprint 52 et sections
Sprint 51 pour les stories reprises). A `nadia-prof` :

- `S52-RETRAIT-SCIENCES-CM1` : aucun. La story le dit (*"rien de pedagogique"*).
- `S52-FRA-PRESENT` : l'exactitude de chaque forme conjuguee et de chaque distracteur ; la vraisemblance d'un
  distracteur comme erreur reelle d'un enfant de CE1 ; que le paquet travaille `FRA-CE1-17` et non `-14` ou
  `-18`.
- `S52-FRA-SYNONYMES-CONTRAIRES` : qu'une seule reponse soit acceptable par item ; qu'aucun distracteur ne soit
  un synonyme ou un contraire reconnu ; que le mot cible soit frequent au CE1.
- `S52-FRA-CATEGORIES` : qu'une seule reponse soit acceptable (aucun membre discute) ; l'exactitude de chaque
  categorie ; la vraisemblance de chaque distracteur.
- `S51-MAT-SYMETRIE` : que l'axe ou la case se trouve sur le dessin seul, a la taille de rendu, zones
  masquees, puis que la zone declaree bonne couvre l'endroit trouve (`ZON-9`) ; la lisibilite des dessins ;
  l'exactitude de chaque symetrie. La moitie debordement de `ZON-13` est aussi hors d'un automate
  (`DeuxZonesDUnItemNeSeChevauchentJamaisTest` la declare).
- `S51-MAT-MULTIPLICATION` : qu'un reseau se compte a l'oeil sans se confondre ; l'exactitude de chaque
  produit ; la vraisemblance de chaque distracteur.
- `S51-MAT-COMPARER-RANGER` : que le paquet travaille `MAT-CE1-02` et non `MAT-CP-02` ni `MAT-CE1-03` ;
  l'exactitude arithmetique de chaque enonce et de chaque distracteur.
- `TPS-CE1-R1` : qu'aucune activite n'est replacee du cote d'un stereotype par les prenoms nouveaux, et
  qu'une seule reponse reste acceptable par item.
- `ALP-CE1-R1` : que chaque indice corrige mene a la lettre qui decide.

La tracabilite est verifiee **au niveau de la story** : chacune des neuf stories est nommee dans au moins un fichier de test. Je n'ai pas
verifie critere par critere que chaque critere testable a un test qui le nomme.

## 8. Couvert, non couvert

- Chaque module a des tests (domain 120, application 17, adapter-web 401, adapter-content 1318, bootstrap 316
  au tip). Aucun module sans test.
- Non lances : le pas `package` du job `build`. Aucun serveur de jeu n'a ete demarre (aucun port de la plage
  18930-18949 utilise) : tout est passe par `MockMvc` et le reacteur.
- Tests instables : aucun constate. Chaque suite a tourne deux fois sur ce poste (tip puis tip + 1 test) sans
  divergence ; deux passages ne prouvent pas l'absence d'instabilite.
- Le journal Maven contient des avertissements `The requested profile "..." could not be activated` (profils
  demandes par la configuration du poste, absents du depot) : sans effet.

## 9. Verdict de la porte technique

`prefixes` vert, `chemins` vert, `outils` vert, `build` vert, les quatre lances par moi sur `40daa419` (build
rejoue avec le test de la section 4, vert aussi). Reserve : le pas `package` n'est pas rejoue. Ce verdict est
celui de ma mesure locale ; le juge reste la CI sur le push. Il ne dit rien du Done pedagogique.
