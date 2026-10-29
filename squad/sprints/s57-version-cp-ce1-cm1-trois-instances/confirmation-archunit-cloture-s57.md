# [Sprint s57] Clôture - confirmation ArchUnit du sprint, gardes écrits ou avalisés, ADR touchés, dettes ouvertes

- **Date** : 2026-10-29, 11:58 (UTC+01:00), lue par `date`.
- **Auteur** : `theo-archi`. Branche `s57-cl-theo-s57`, worktree dédié coupé de `round-two` à `a3e05f13`, `git status --short` vide avant écriture.
- **Objet** : la confirmation ArchUnit que la review exige (`squad/README.md`, section 4), pour la clôture de s57 décidée par l'utilisatrice le 2026-10-29 (relayée par l'agent lanceur). Ce fichier porte aussi le sommet du palier 5 : `cloture-palier-5.md` (sections 11 et 12) relève l'absence de `confirmation-archunit-palier-5.md`. Dire si ce fichier ferme cet obstacle revient à `sofia-sm`.
- **Ouverture de s57 retenue : `771b55de`** (2026-10-14 15:30, UTC+02:00), dernier commit avant l'horodatage de début de `planning.md` (2026-10-14 15:45), lu par `git rev-list -1 --before`.
- **Version** : **JDK 21.0.10**, propriété `java.version` des 43 rapports `TEST-*.xml` de `M1`, une seule valeur (`ADR-0071`).
- **Ce que ce fichier ne fait pas** : il ne modifie ni code, ni test, ni `ADR`, ni `DEC`, ni registre, ni story. Il ne prononce aucun Done.

## 0. Verdict

| Code | Question | Verdict |
|---|---|---|
| `V1` | Les règles ArchUnit sont-elles vertes à la clôture ? | **oui** : 43 classes des répertoires `archi`, 286 tests, 0 échec, dont les 15 classes porteuses de règles, 88 tests (`M1`, de ma main, à `a3e05f13`). `verify` complet à `d9313f9e` **repris** (`M3`) |
| `V2` | Le compte de règles a-t-il bougé pendant s57 ? | **non** : 41 déclarations, 38 `@ArchTest`, 16 fichiers, de `771b55de` à `a3e05f13` (section 2) |
| `V3` | Une règle ArchUnit a-t-elle été ajoutée ou modifiée ? | **non**. Des gardes non ArchUnit ont été ajoutés ou étendus : section 3, chacun avec sa morsure |
| `V4` | Quels `ADR` ont changé ? | **aucun créé, huit modifiés**, statuts inchangés (section 4) |
| `V5` | La commande de mesure transmise mesure-t-elle quelque chose ? | **non** : `-Dtest='com.recreo.archi.*'` lance 0 test et rend `BUILD SUCCESS` (`M0`). C'est un faux vert (section 5, `DA2`) |
| `V6` | Dettes d'architecture ouvertes | dix lignes, chacune avec son propriétaire (section 5) |

## 1. Mesures

| Code | Mesure | Arbre | Auteur | Résultat |
|---|---|---|---|---|
| `M0` | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest='com.recreo.archi.*' -Dsurefire.failIfNoSpecifiedTests=false` | `a3e05f13` | moi | exit 0, `BUILD SUCCESS`, 11:42:49. **Aucune ligne `Tests run` dans le journal, aucun répertoire `bootstrap/target/surefire-reports`** : 0 test lancé. Je n'ai pas cherché pourquoi Surefire 3.5.6 ne résout pas ce motif |
| `M1` | `bash outils/mvn-jdk21.sh -B -pl bootstrap,adapters/adapter-web,domain -am test -Dtest='**/archi/*Test' -Dsurefire.failIfNoSpecifiedTests=false` | `a3e05f13` | moi | exit 0, `BUILD SUCCESS`, 11:47:02. Lignes `Tests run` : 8 (`domain`, 3 classes), 2 (`adapter-web`, 1 classe), 276 (`bootstrap`, 39 classes), soit **286 tests, 0 échec, 0 erreur, 0 ignoré** [calcul]. Sommes des rapports XML égales à ces trois lignes |
| `M2` | gardes du sprint, 26 classes : les 21 marquées `M2` en section 3, plus `PaquetCe1LireLHeureTest`, `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`, `LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest`, `TexteAccentueExposeParLePortEntrantTest` et `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest` ; même enveloppe, `-pl bootstrap -am` | `a3e05f13` | moi | exit 0, `BUILD SUCCESS`, 11:50:17. Lignes `Tests run` : 71 (`adapter-web`), 458 (`adapter-content`), 34 (`bootstrap`), soit **563 tests, 0 échec** [calcul] |
| `M3` | `mvn -B clean verify` complet, racine | `d9313f9e` | agent lanceur, cité par `DEC-0120` A.292 point 2 | 3644 tests (120, 31, 523, 2569, 395 unitaires, 6 d'intégration), 0 échec. **Repris, non relancé** |
| `M4` | écart de code entre `d9313f9e` et `HEAD` | les deux | moi | `git diff --stat d9313f9e HEAD -- adapters bootstrap domain application` ne rend rien : `M3` vaut pour le code de `a3e05f13` par déduction, pas par mesure |
| `M5` | poids des illustrations, hors du test | `a3e05f13` | moi | blobs de `HEAD` des 68 fichiers `.svg` de `adapters/adapter-web/src/main/resources/static/illustrations`, retours chariot retirés : **96 873 octets au total, 4 633 pour le plus gros** |

Périmètre de `M1` : les classes dont le chemin contient un répertoire `archi`, dans `domain`, `adapter-web` et `bootstrap`. `application` et `adapter-content`, construits par `-am`, n'en ont aucune. Ce n'est pas un `verify` : `M1` et `M2` sont des mesures partielles, `-am` présent.

Détail des 15 classes porteuses de règles (attribut `tests` des rapports de `M1`), identique classe par classe au palier 4 (`confirmation-archunit-palier-4.md:33`) : `AssertionsWebTest` 2, `AbstractionsCiteesTest` 5, `AccesALEtatDeSessionTest` 2, `ArchitectureEnCouchesTest` 2, `ChargementDuContenuTest` 2, `DecouplageMecaniqueContenuTest` 13, `DifficulteTest` 3, `GestionDesErreursTest` 4, `LOrdreDesMatieresVientDeLaDonneeTest` 4, `LOrdreDesNiveauxResteUneDonneeTest` 5, `LeChoixDesJeuxResteDerriereLePortTest` 1, `LeSeulCookieEstLeMarqueurDePartieTest` 2, `LesReglesMordentTest` 39, `PureteDesCouchesTest` 2, `ScanDeComposantsTest` 2. Les 28 autres classes de `M1` sont des gardes de contenu ou de dépôt rangés sous `archi` (198 tests [calcul : 286 - 88]).

## 2. Compte de règles

Méthode d'`ADR-0104` : lignes des `*.java` suivis au commit nommé (`git grep <commit>`, ce qui exclut les worktrees d'agents et les fichiers non suivis) qui correspondent à `^\s*(public |private |protected )?static final ArchRule ` d'une part, à `^\s*@ArchTest\s*$` d'autre part ; fichiers par `git grep -l` sur l'un ou l'autre motif ; contre-exemple par le premier motif restreint à `*RegleContreExemple.java`.

| Commit | `static final ArchRule` | `@ArchTest` | Fichiers | dont `RegleContreExemple.java` |
|---|---|---|---|---|
| `771b55de` (ouverture de s57) | 41 | 38 | 16 | 2 |
| `f3b06989` (clôture du palier 4, départ du palier 5) | 41 | 38 | 16 | 2 |
| `d9313f9e` (`verify` de `M3`) | 41 | 38 | 16 | 2 |
| `a3e05f13` (clôture) | **41** | **38** | **16** | 2 |

**Règles réelles : 39 déclarations dans 15 fichiers**, dont 38 exécutées par `@ArchTest` et une par deux `.check(...)` explicites (`ScanDeComposantsTest`). Les 2 lignes en plus sont la classe locale nommée `ArchRule` du faux dépôt d'`ADR-0013`. Même lecture qu'aux paliers 2 à 4.

## 3. Ce qui a bougé dans les gardes pendant s57

### 3.1 Règles ArchUnit : rien

- `git diff --stat 771b55de HEAD` sur les 16 fichiers de la section 2 ne rend rien.
- Tout le diff `*.java` du sprint : 54 fichiers, 26 386 insertions, 96 suppressions (`git diff --shortstat`). Aucune ligne ajoutée ne porte `@Disabled`, `@Ignore`, `ArchIgnore`, `allowEmptyShould(true)` ni `assume` (`grep` vide).
- Code de production : **un seul fichier** modifié, `PartieController.java` (`472dce51`, `6a80fe66`, `lea-dev`). Les 15 classes de règles de `M1` le jugent.
- Suppressions du palier 5 (`git diff f3b06989 HEAD -- '*.java'`), lues une à une : 4 lignes. Trois sont des valeurs de cliquet remplacées (`69203`, `87`, `56`, voir `G10`), et la quatrième est une fin de chaîne `));` devenue `),` pour qu'une entrée s'ajoute (`G12`). Aucune assertion retirée. Les 92 suppressions des paliers 1 à 4 ne sont pas relues ici : celles du palier 4 le sont dans `confirmation-archunit-palier-4.md`, section 4.

### 3.2 Gardes nés d'un amendement d'`ADR` (paliers 1 à 4)

| Code | `ADR` | Classe | Morsure ou jeu fautif | Tests dans `M2` |
|---|---|---|---|---|
| `G1` | `ADR-0067`, 2026-10-14 | `LeVerdictNeSeCoupeJamaisDansUnePhraseTest` | mutation en mémoire, `laProprieteMordSurUneEspaceOrdinaireRemise` | 4 |
| `G2` | `ADR-0067`, 2026-10-15 | `LaPhraseDeRevelationDUneMancheEnImageNeSeCoupeJamaisDansUnePhraseTest` | mutation en mémoire, `laProprieteMordSurUneEspaceOrdinaireRemiseEtSurUnInsecableEntreLesDeuxPhrases` | 2 |
| `G3` | `ADR-0104`, 2026-10-14 (renvois dans `ADR-0084` et `ADR-0024`) | `LEcranDeFinDUnJeuAReponseEnImageNeRendAucunTexteDeLItemTest` | quatre méthodes `leJugeRefuse...` sur des écrans fautifs écrits dans le test | 10 |
| `G4` | `ADR-0065` A3, 2026-10-16 | `AucunMotServiNeSEcarteDeSonAccentTest` | `unAffixeCiteAvecSonTraitDUnionNEstPasJugeCommeUnMot`, `unMotColleAUnTraitDUnionEnBordResteInvisible` | 7 |
| `G5` | `ADR-0031`, 2026-10-16 et 2026-10-20 | `UnEcranNeGouvernePasLApparenceDUnAutreTest` | onze méthodes ajoutées, dont `chaqueClasseDuMessageNommeeDepuisUnSujetHorsDeLuiFaitEchouerLeGardeFou`, `uneRegleImbriqueeQuiNommeLeMessageEstRefuseeParLeLecteur`, `unMessageQuiPerdSaRegionDAnnonceFaitEchouerLeCliquet` | 14 |
| `G6` | `ADR-0054`, 2026-10-20 et 2026-10-21 (`DEC-0122`) | `LaPileDePolicesServieNeChangePasEnSilenceTest` | faux dépôt commité (15 fichiers sous son répertoire `contreexemple`), `unDepotQuiRedefinitLaPileFaitEchouerLeGardeFou`, deux morsures par échappement CSS | 12 |
| `G7` | `ADR-0058`, 2026-10-21 | `EcranDeSaisieTest` | **aucune morsure distincte** : une égalité `autocorrect` vaut `off` sur l'écran rendu (`:109`). Je n'ai pas muté le gabarit pour la voir rougir | 15 |

### 3.3 Gardes de contenu écrits ou avalisés dans mes avis, sans `ADR`

| Code | Garde | Classe | Jeu fautif exécuté | Tests |
|---|---|---|---|---|
| `G8` | dix manches sauf dérogation nommée (palier 4, `lecture-theo-p4-10-garde-manches.md`) | `ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest` | cinq catalogues fautifs commités, cinq méthodes `...FaitEchouerLeGardeFou` | 7 (`M2`) |
| `G9` | garde des cadrans et garde de paire `S1` à `S4` (`avis-theo-palier-5-horloge-mat-ce1-11.md`, section 5.2) | `LesCadransMontrentCeQueLeursItemsAffirmentTest` | `leJeuFautifCommiteEstRefuse` (cadran fautif) ; `lePremierJetDeLHorlogeFigeEstRefuse`, qui exige `S1` = 10 et `S3` = 12. Le premier jet figé est le matériau réel : `git rev-parse` rend le même blob, `42eeeced`, pour le paquet servi à `a5d5026b` et pour le fichier figé à `HEAD` | 14 (`M1`) |
| `G10` | cliquet de poids, 69 203 vers 96 873 octets (`DEC-0120` A.284) | `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` | `leCliquetVautExactementCeQueCeLotInstalle` exige l'égalité. `M5` donne 96 873 et 4 633 hors du test : **le plafond vaut la dette, sans marge**. La Javadoc cite maintenant l'accord (`:357-359`), ce qui ferme l'écart `V4` de mon avis | 17 (`M2`) |
| `G10b` | cliquets d'illustration, 87 vers 99 items et 56 vers 68 fichiers | `LIllustrationDeContenuResoutEtAucuneNeDortTest` | cliquets en égalité ; 68 est le nombre de fichiers de `M5` | 22 (`M1`) |
| `G11` | `G12-3`, table fermée groupe vers sens (`avis-theo-palier-5-garde-g12-3-accord-ce1-qlm-generations.md`) | `PaquetCe1QlmGenerationsTest` | `SENS_DU_GROUPE` (`:134-142`), sondes 74 et 75 de `generations-fautives.yaml` lues par `leJeuFautifCommiteEstRefuseSurLOrdreInverseDeLaBonneSuiteEtSurUnEnonceSansSens`. Un seul énoncé du jeu garde le masculin sur des générations : celui de la sonde 74 (`:382`), comme l'avis le demande | 40 (`M2`) |
| `G12` | garde de rang, neuf exemptions du palier 5 (une par paquet `ce1-qlm-*`) | `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | chaque motif chiffré est recompté sur le paquet servi (variantes `amputee`, `enTrop`, `fantome`, `muette`). `ce1-lire-l-heure` n'est pas exempté : il est jugé sous `ADR-0102` | 28 (`M2`) |
| `G13` | conditions transverses des neuf paquets QLM CE1 | `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest` | dix méthodes `leJeuFautifCommite...`, 14 fichiers commités | 18 (`M2`) |
| `G14` | gardes par paquet des neuf lots (mes avis `avis-theo-palier-5-gardes-*` et `-ajustements-*`) | les neuf `PaquetCe1Qlm*Test` | un répertoire `contreexemple` par classe | 374 (`M2`) |
| `G15` | forme commune des propositions longues (palier 2, `lecture-theo-p2-7-forme-commune.md`) | `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` | faux dépôt de 6 fichiers, cinq méthodes `...FaitEchouerLeGardeFou` | 7 (`M2`) |

**Limite de `G14`.** Je n'ai pas relu, clause par clause, que chaque garde de mes avis sur les neuf lots est écrite comme je l'ai demandée. Seul `G11` est relu ligne à ligne. `M2` prouve que ces classes sont vertes et qu'elles refusent leurs jeux fautifs. Il ne prouve pas leur conformité à mes avis.

**`ADR-0086`, `ADR-0087` et `ADR-0102` n'ont reçu aucun commit pendant s57** (section 4), et leurs règles n'ont pas changé. `ADR-0086` : `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`, 9 tests dans `M1`. Son amendement de s32 (section 2 : la preuve sur premier jet figé survit à la correction) est le principe que `G9` applique. `ADR-0087` : `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`, 5 tests dans `M1`. Il juge les tables de ce fichier (section 6). `ADR-0102` : appliqué à l'horloge par `G12`, sans modification (mon avis sur l'horloge, `V1`).

## 4. `ADR` créés ou modifiés pendant s57

Méthode : `git diff --name-status 771b55de HEAD -- squad/decisions/adr/` rend 9 lignes, toutes `M`, **aucune `A`** : aucun `ADR` n'est créé. `git log 771b55de..HEAD -- squad/decisions/adr/` rend 10 commits, le dernier `50c74519` (2026-10-21 18:54), avant `eb267ba7` (2026-10-21 22:34), ouverture retenue du palier 4 : `git diff --name-status eb267ba7 HEAD -- squad/decisions/adr` ne rend rien, **aucun `ADR` n'a changé aux paliers 4 et 5**. Les lignes `Statut` des huit fichiers ne figurent pas dans le diff : les statuts sont ceux d'avant s57.

| `ADR` | Titre (nom du fichier) | Commits de s57 | Statut |
|---|---|---|---|
| `ADR-0024` | le-verdict-porte-l-explication-de-l-item-joue-la-graphie-reste-au-paquet | `6a80fe66` (renvoi) | Accepte, inchangé |
| `ADR-0031` | un-ecran-ne-gouverne-jamais-l-apparence-d-un-autre | `b0c47c97`, `78b260e3` | Accepte, amendé le 2026-10-16 et le 2026-10-20 |
| `ADR-0054` | la-largeur-offerte-a-une-rangee-est-une-fonction-epinglee-jamais-un-effet-de-bord-d-un-seuil-tiers | `558dd887`, `19aa6c98` | Accepte, amendé le 2026-10-20 et le 2026-10-21 |
| `ADR-0058` | la-saisie-normalise-dans-sa-mecanique-declare-son-mode-strict-dans-l-item-et-aucun-contrat-ne-porte-de-comportement | `5a0b55f3`, `50c74519` (correction des écarts 36 à 38) | Accepte pour ses clauses de conception, une seule de ses règles écrite, inchangé |
| `ADR-0065` | un-mot-lu-par-un-enfant-est-juge-sur-un-lexique-exterieur-et-la-classe-des-homographes-reste-a-la-main | `76642a48` (A3) | Accepte, inchangé |
| `ADR-0067` | le-moyen-de-typ-1-est-la-donnee-et-la-dette-est-arretee-par-un-cliquet-exact-avant-d-etre-corrigee | `472dce51`, `7ab7bfd7` (`lea-dev`) | Accepte pour le cliquet, choix du caractère ouvert hors du verdict, inchangé |
| `ADR-0084` | la-reponse-d-une-fin-de-partie-voyage-avec-la-question-qu-elle-commente | `6a80fe66` (renvoi) | Accepte, inchangé |
| `ADR-0104` | rapprocher-deux-oeuvres-se-sert-par-des-propositions-qui-sont-des-reproductions-et-la-mecanique-ne-lit-jamais-une-proposition | `6a80fe66` | Accepte, inchangé |

`ADR-index.md` porte une ligne d'amendement pour `ADR-0067` (deux), `ADR-0104`, `ADR-0031` (deux) et `ADR-0054` (deux). `ADR-0065` A3 et `ADR-0058` n'y ont pas de ligne d'amendement : leurs commits ne changent que la cellule de statut de la table principale (1 ligne chacun, `git show --stat`). Je ne l'ai pas corrigé : ce fichier ne touche pas l'index.

## 5. Dettes d'architecture ouvertes à la clôture

Aucune n'est payée ici.

| Code | Dette | État à `a3e05f13` | Propriétaire |
|---|---|---|---|
| `DA1` | **Clé de type de l'entrée de catalogue** (`cloture-palier-5.md`, section 12) | `catalogue.yaml`, `schema: 1` : 173 entrées (`grep -c "^  - id:"`), **0** ligne `type:`. Le type vit dans le descripteur du paquet (`ce1-qlm-generations.yaml:3`, `type: question`), alors que le texte de `S57-QLM-CE1-ENTREES-CATALOGUE` écrit `type: question` comme une clé du catalogue. Ma lecture, non actée : le chargeur unique lit le type du paquet, et une clé de catalogue copierait cette donnée. Une décision de schéma s'écrit en `ADR`, que ce fichier ne crée pas | `theo-archi`, `ADR` ou refus écrit |
| `DA2` | **Faux vert de mesure** (`P6`) : `-Dsurefire.failIfNoSpecifiedTests=false` combiné à un motif qui ne désigne aucune classe | mesuré en `M0` : 0 test, `BUILD SUCCESS`. Je réponds à la condition de `P6` : **ce n'est pas une règle d'architecture**, aucune `ArchRule` ne lit une ligne de commande. C'est une règle de méthode : une mesure partielle citée porte ses lignes `Tests run` | `yanis-qa` |
| `DA3` | Garde générique de l'écart `E3` (`confirmation-archunit-palier-3.md:85`) | **non écrit** : `git ls-files` ne connaît aucun `LesPropositionsEtLesTextesSupportsDeCe1TiennentLeursBornesTest`. Story `S57-GARDE-CE1-PROPOSITIONS-ET-SUPPORTS` non engagée | `camille-po`, puis le développeur engagé |
| `DA4` | Recensement d'`ADR-0013` aveugle aux chemins assemblés par segments (`lecture-theo-p4-10-garde-manches.md:53-58`) | **non payé** : `ADR-0013` absent du diff de la section 4 | `theo-archi` |
| `DA5` | `verifier-chemins.sh` ne voit pas la forme `X--...` (`avis-theo-palier-4.md`, section 5) | **non payé** : `git log f3b06989..HEAD -- .github outils` rend 0 commit | `theo-archi`, amendement d'`ADR-0010` avec son cas de test |
| `DA6` | `T1` : entrées mortes de `EFFETS_CONTRAIRES` (`PaquetCe1QlmBesoinsDeLaPlanteTest:99-103`) | La table est une **liste d'admission** : chaque distracteur de la tâche privation doit y figurer. Sur 11 entrées, **2 ne nomment aucun distracteur servi** : *elle grandit plus vite* et *elle devient plus solide* (`grep -ic` sur `ce1-qlm-besoins-de-la-plante.yaml` : 0). *Elle pousse plus vite*, que `planning.md` nomme comme ancienne amorce, est servie (`:169`). Les deux entrées mortes admettraient sans preuve un distracteur qui reviendrait. Mon avis : les retirer, dans un commit de code avec le test | `marc-dev`, sur mon avis |
| `DA7` | Garde des cadrans : un troisième élément en forme d'aiguille, hors de la forme exacte, passe en silence (mon avis sur l'horloge, `F4`). C'est une règle en prose sans test | défaut absent aujourd'hui : chacun des 12 `horloge-*.svg` porte exactement 2 `<line>` (`grep -o "<line"`). Les 132 `<path>` des cadrans ne sont pas lus par la garde | `theo-archi`, si un redessin le demande |
| `DA8` | `P9` : ordre de fusion de `-03` avant `-02` | **non instruit ici** : je n'ai pas relu les faits (3) et (4) de l'amendement du 2026-10-27 15:40 de `planning.md` | `theo-archi` |
| `DA9` | Caractère de `ADR-0067` | tranché pour le verdict (U+00A0, amendement du 2026-10-14), ouvert pour le reste du texte servi | `theo-archi` et `sofia-sm` |
| `DA10` | Mots proposés au-dessus du clavier, et condition de `MAT-CE1-14` (`DA4` et `DA5` de `confirmation-archunit-palier-3.md`) | stories non engagées, état inchangé (titres du backlog, non relus plus avant) | `camille-po` pour l'engagement, `theo-archi` pour l'instruction |

**Ce que je ne compte pas comme dette d'architecture** : les propriétés que mes avis déclarent *"non gardé"* sur les neuf lots QLM. Ce sont des propriétés de contenu, laissées par écrit à la lecture de `nadia-prof`. Elles sont déclarées. Ce ne sont pas des règles écrites en prose sans leur test.

**Non vérifié** : le `verify` complet, l'étape *"Package du fat jar"* et la CI GitHub, que personne n'a lue (`cloture-palier-5.md`, section 6, Q1). L'accord de l'utilisatrice sur le poids, que je ne connais que par `DEC-0120` A.284. Les morsures de `G7` et de `G14` au-delà du vert de `M2`.

## 6. Jobs de `ci.yml` lancés pour ce commit

Lancés dans mon worktree, ce fichier présent, avant le commit. Je ne déduis aucun job que je n'ai pas lancé.

- **`build`, mesure partielle** : `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` et `ToutesLesReferencesDeDecisionResolventTest`, `-pl bootstrap -am`. Résultat dans le compte rendu du commit, pas ici.
- **`chemins`**, forme CI : `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh'` passé à `verifier-chemins.sh` par l'entrée standard.
- **`prefixes`** : sujet du commit passé à `verifier-prefixes.sh`.
- **Non lancés** : le `verify` complet (ce commit n'ajoute qu'un fichier sous `squad/`), le fat jar, le job `outils`.

**Le Done technique n'est pas prononcé par moi.**

## Décisions prises

**Aucune `ADR` ni `DEC` créée ni amendée par ce fichier**, `ADR-index.md` inchangé. Il confirme les règles ArchUnit vertes à la clôture et le compte inchangé sur tout le sprint. Il liste les gardes du sprint avec leur morsure et les huit `ADR` modifiés. Il nomme dix dettes, chacune avec son propriétaire, et un faux vert de mesure.
