# Confirmation ArchUnit - s55 (`theo-archi`)

- **Auteur** : `theo-archi`. **Date** : 2026-10-08.
- **Objet** : confirmer, pour la cloture de s55, que les regles ArchUnit sont vertes sur l'arbre fusionne, que leur compte
  n'a pas bouge et qu'aucune n'est affaiblie ; juger les ecarts de `lea-dev` a la proposition d'`ines-design` (T3 de
  `S55-ART-CP-10-MECANIQUE`) contre mes `ADR` ; mesurer mon correctif du garde-fou de provenance sur l'arbre fusionne.
- **Arbre juge** : `round-two` a **`af8b326e`** (fusion de `s55-lea-mecanique`), worktree dedie, branche `s55-theo-cloture`,
  `git status --short` vide avant chaque mesure et apres chaque mutation. `bash outils/verifier-lignee.sh` : "descend de la
  lignee de round-two".
- **Bases** : debut de s55 = **`fbe07d79`**, dernier commit de `round-two` avant la fusion du planning de s55 (`98e4bbcd`),
  lu sur `git log --first-parent`. Le prompt designait le debut du sprint par un prefixe `ca3e` : **aucun objet de l'historique
  ne porte ce prefixe** (`git log --all --format=%H`), le plus proche est `ca425c17`, troisieme fusion du sprint. Seconde base
  demandee : `fa4ddc7e` (fusion de `s55-marc-branchement`).
- **Version** : **JDK 21.0.10**, propriete `java.version` des 289 rapports `TEST-*.xml` du reacteur complet (section 1), seule
  valeur trouvee (`ADR-0071`).

## 0. Verdict

| Question | Verdict |
|---|---|
| Les regles ArchUnit sont-elles vertes ? | **oui** : 240 tests de `com.recreo.archi`, 0 echec ; reacteur complet `BUILD SUCCESS` (section 1) |
| Le compte a-t-il change ? | **non** : 39 `static final ArchRule` et 36 `@ArchTest` contre-exemples compris, 37 et 36 hors contre-exemples, aux trois bases (section 2) |
| Une regle est-elle affaiblie ? | **non** : aucun fichier de regle ArchUnit ni de contre-exemple ArchUnit dans le diff ; les deux garde-fous de `com.recreo.archi` touches sont etendus, jamais relaches (section 3) |
| Les ecarts E1 et E2 de `lea-dev` sont-ils conformes ? | **oui** (section 4) |
| L'ecart E3 est-il impose par `ADR-0046` ? | **non**, mesure : un cadre en tirets pose sur l'image laisse tout le reacteur vert (F3) |
| Le plafond de 176 px d'`ADR-0104` decision 4.3 tient-il ? | **oui a la mesure, garde par rien** : dette ouverte (F4) |
| Le correctif de provenance tient-il sur l'arbre fusionne ? | **oui** sans et avec `-Dproject.build.outputTimestamp`, et sa retraite rougit les trois copies (section 5) |
| Un `ADR` est-il necessaire ? | **non** (section 6) |

## 1. Commandes et resultats

| Mesure | Commande | Horloge | Resultat |
|---|---|---|---|
| R1, reacteur complet (job `build`, etape 1) | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine | 21:04:37 a 21:10:56 | `BUILD SUCCESS`, 289 rapports, **2 303 tests, 0 echec, 0 erreur, 0 ignore** : domain 120, application 18, adapter-web 407, adapter-content 1 419, bootstrap 333 Surefire et 6 Failsafe |
| R2, fat jar (job `build`, etape 2) | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package`, sans `clean`, apres R1, comme la CI | 21:11:35 a 21:14:09 | `BUILD SUCCESS`, memes comptes Surefire par module que R1 |
| R3, build reproductible | R1 avec `-Dproject.build.outputTimestamp=1980-02-01T00:00:00Z` | 21:14:20 a 21:19:37 | `BUILD SUCCESS`, memes comptes que R1 ; les 53 entrees du jar de `domain` portent `1980-02-01 00:00` (`unzip -l`) |

Comptes : somme des attributs `tests`, `failures`, `errors`, `skipped` des rapports `TEST-*.xml` des repertoires
`target/surefire-reports` et `target/failsafe-reports` des cinq modules. Perimetre ArchUnit : rapports dont la classe est dans
`com.recreo.archi`, **240** ; s54 en comptait 229. Les +11 sont les 7 tests de `UnItemAPropositionsEnImageTientSesTroisInvariantsTest`
(neuf) et les 4 `@Test` neufs de `LIllustrationDeContenuResoutEtAucuneNeDortTest` (18 -> 22). `DecouplageMecaniqueContenuTest` 13,
`LesReglesMordentTest` 38 (37 + 1 sous le nom court, meme ecart de rapport qu'en s54).

## 2. Compte de regles

Methode d'`ADR-0104` : lignes des `*.java` suivis par Git (`git grep` sur le commit, worktrees d'agents exclus par construction)
qui correspondent a `^\s*(public |private |protected )?static final ArchRule ` d'une part, a `^\s*@ArchTest\s*$` d'autre part.

| Commit | `ArchRule` | `@ArchTest` | dont `RegleContreExemple.java` |
|---|---|---|---|
| `fbe07d79` (debut de s55) | 39 | 36 | 2 et 0 |
| `fa4ddc7e` | 39 | 36 | 2 et 0 |
| `af8b326e` (cloture) | **39** | **36** | 2 et 0 |

La mecanique `ART-CP-10` (T2 `dfd2f107`, T3 `363793e3`) laisse donc 39 et 36 inchanges, comme prevu par `ADR-0104`.

## 3. Diff des tests d'architecture

`git diff --name-status <base>..af8b326e` restreint a `bootstrap/src/test/java/com/recreo/archi`,
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/archi`, `bootstrap/src/test/java/com/recreo/domain` (contre-exemples de
mecanique) et `contreexemple/LesGardeFousSontMisALEpreuveTest` :

- **depuis `fbe07d79`** et **depuis `fa4ddc7e`** : le meme resultat, deux fichiers, tous deux de `dfd2f107` (`git diff
  fbe07d79..fa4ddc7e` sur ce perimetre est vide). `LIllustrationDeContenuResoutEtAucuneNeDortTest` (+223 -2) et
  `UnItemAPropositionsEnImageTientSesTroisInvariantsTest` (neuf, 233 lignes), plus deux jeux fautifs YAML neufs sous
  `bootstrap/src/test/resources/contreexemple/`.
- **Aucun des deux ne declare d'`ArchRule`** : ce sont des garde-fous de contenu (decision 7 d'`ADR-0104`, garde-fous 1 et 2).
- **Les deux seules lignes retirees** sont l'appel de `chaqueIllustrationDeclareeParUnPaquetServiResoutEtAucunFichierNeDort` a
  `verifier` a quatre arguments, remplace par l'appel a cinq arguments qui ajoute les propositions-images. L'ancienne surcharge
  delegue avec une liste vide : population etendue, aucune clause retiree.
- Sur tout le diff de tests du sprint (`*/src/test/java/*`, 19 fichiers) : aucun `@Disabled`, `@Ignore`, `ArchIgnore`,
  `allowEmptyShould(true)` ni `assume` ajoute. Les deux garde-fous hors ArchUnit dont un seuil bouge suivent la decision 7
  d'`ADR-0104` : cliquet de `RessourcesTiercesTest` 3 -> 4 (point 5), exemption `luParLEnfant` des propositions-images (point 3) ;
  la dette de `AucunSigneDoubleNEstDetachableDeSonMotTest` baisse (502 -> 490).
- Aucune ligne de `domain`, d'`application`, de `src/main/java` hors `PartieController`, aucun POM, aucun workflow dans le diff du
  sprint.

## 4. Ecarts de `lea-dev` a la proposition d'`ines-design`

Source : `rapport-lea-dev-mecanique.md`, section "Ecarts" ; code lu : diff de `PartieController.java`, `fragments/manche.html` et
`recreo.css` depuis `fbe07d79`.

- **F1. E1, classes (`.proposition-oeuvre`, `.oeuvre-revelee` non posees) : conforme.** Le bouton garde un seul `th:classappend`
  sur `${propositionsEssayees}`, ce que la clause d'`ADR-0027` exige. Les deux classes neuves (`ecran-deux-oeuvres` sur la racine,
  `propositions-oeuvres` sur la rangee) sont nommees par la feuille, ce que la ligne 7 de la decision 7 d'`ADR-0104` conditionne,
  et R1 est vert. `data-oeuvre-revelee` est derive du gabarit sur `propositionsEnImage`, `mancheRevelee` et `focusCibles` : le
  gabarit ne lit aucun prefixe, le controleur le lit a deux endroits, tous deux nommes par la decision 3 (rangee, ecran de fin).
  Le selecteur `.propositions.propositions-oeuvres .proposition` a la specificite (0,3,0) des teintes cycliques et vient apres
  elles : il l'emporte, comme le rapport l'ecrit. **Une derive de commentaire** : le second commentaire de `manche.html` sur la
  revelation dit encore "la bonne oeuvre est marquee (oeuvre-revelee)", classe qui n'existe pas ; le premier commentaire et le CSS
  disent juste. Fichier de `lea-dev`, je ne le touche pas.
- **F2. E2, anneau pose sur l'image et non sur le bouton : conforme a `ADR-0046`, en lettre et en motif.** Le perimetre
  d'`ADR-0046` est l'element pressable : un `outline` declare au repos sur `.proposition` ferait exiger `outline` a `:focus-visible`
  (amendement s16, `.btn:focus-visible` declarant `outline`), donc ecraser l'anneau de focus clavier ou le recopier. L'image n'est
  pas un pressable et aucune regle d'etat tierce ne l'atteint : ce que l'`ADR` protege, une declaration maison effacee par un etat
  de la bibliotheque, ne peut pas lui arriver. Ce n'est pas un contournement, c'est le bon sujet. La dette d'etats reste vide.
- **F3. E3, cadre en tirets d'echec abandonne : `ADR-0046` ne l'impose pas.** Le motif ecrit ("meme cause qu'E2") vaut pour un
  `border` sur `.proposition.deja-essayee`, pas pour un cadre porte par l'image, qui est exactement le geste d'E2. Mesure par
  mutation : `.propositions-oeuvres .proposition.deja-essayee img { border-style: dashed; }` ajoute a `recreo.css`, `bash
  outils/mvn-jdk21.sh -B clean verify -pl bootstrap -am -Dmaven.test.failure.ignore=true` (cinq modules, `-am`) : **0 echec sur
  2 303 tests**, fichier restaure, `git status --short` vide. Le canal de forme de `M3` n'est donc bloque par aucun garde-fou ;
  sa forme reste a `ines-design` et `nadia-prof`.
- **F4. Plafond de 176 px (`ADR-0104` decision 4.3) : tenu a la mesure, garde par rien.** L'image porte `.image-item`, mais
  `.propositions-oeuvres .proposition img` (0,2,1) remplace son `max-height: 11rem` par `max-height: 100%`. Le plafond tient
  alors par l'arithmetique de la case (`max-width: calc(11rem + 0.5rem + 0.25rem)`, `aspect-ratio: 1 / 1`, rembourrage, bordures).
  Mesure : page statique de ma main, chargee avec la feuille vendorisee et `recreo.css` du depot et trois reproductions reelles
  (`oeuvre-hippopotame` 264 x 352, `oeuvre-quatre-saisons` 258 x 352, `oeuvre-grande-vague` 352 x 237), Chrome sans tete, largeur
  rapportee 1 008, 704 et 500 px : boite d'image **au plus 178 px CSS, contenu au plus 174 px**, bordure de 2 px comprise dans
  l'ecart. Le balisage est de ma main, la feuille et les images non. **Ce qui ne garde rien** :
  `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` lit le seul bloc `.image-item`, toujours a 11rem ;
  elargir le `calc` de la case monterait le plafond sans rougir aucun test, ce que la decision 4.3 interdit sans amender
  `ADR-0082`. **Dette ouverte, `S55-ART-CP-10-PLAFOND`** : un garde-fou qui derive de `recreo.css` le plus grand cote rendu d'une
  image sous `.propositions-oeuvres` et l'egale au `max-height` de `.image-item`, du au plus tard avec le premier paquet a
  propositions-images (population vide a s55, aucun enfant ne voit ce rendu). A planifier par `sofia-sm`.

Hors de ma frontiere et non juge : l'apparence des etats `:hover` et `:focus-visible` de la case (teinte cyclique ou fond neutre),
le meuble (E6), l'`alt` vide (E7, decision de `nadia-prof`).

## 5. Correctif du garde-fou de provenance (`a3606b2f`, trois copies)

`ProvenanceDesClassesAmontEstLeDepotTest` existe dans `application`, `adapter-web` et `adapter-content`. Les lignes ajoutees et
retirees par `a3606b2f` sont identiques dans les trois (meme empreinte `md5sum` des lignes `+` et `-` de chaque fichier).

| Mesure | Commande | Resultat |
|---|---|---|
| Sans la propriete | R1 | vert : application 3, adapter-web 4, adapter-content 4 tests ; entrees du jar a horodatages distincts, branche "jar ordinaire" |
| Avec la propriete | R3 | vert, memes comptes ; entrees du jar toutes a `1980-02-01 00:00`, branche "jar reproductible" |
| Repli `SOURCE_DATE_EPOCH` nomme par la Javadoc | `SOURCE_DATE_EPOCH=318211200 bash outils/mvn-jdk21.sh -B clean verify -pl application -am` | vert ; 53 entrees du jar de `domain` a `1980-02-01 00:00` : le repli de `maven-archiver` est reel |
| Retraite du correctif | les trois fichiers ramenes a `a3606b2f^`, puis `bash outils/mvn-jdk21.sh -B clean verify -pl adapters/adapter-web,adapters/adapter-content -am -Dproject.build.outputTimestamp=1980-02-01T00:00:00Z -Dmaven.test.failure.ignore=true` ; fichiers restaures, `git status --short` vide | **rouge dans les trois copies** : application 1 echec sur 2, adapter-web 2 sur 3, adapter-content 2 sur 3, message "le .class compile (...recreo-domain-0.1.0-SNAPSHOT.jar, horodatage 1980-01-31T23:00:00Z) est plus ancien que son .java source" |

La retraite prouve deux choses que le vert seul ne prouve pas : en `verify`, les classes amont viennent bien du **jar** (le
message nomme le `.jar`), et R3 exerce la branche corrigee. L'horodatage lu, `1980-01-31T23:00:00Z` pour une valeur fixee a
`1980-02-01T00:00:00Z`, montre la dependance au fuseau que la Javadoc nomme. Ce que le correctif perd, ecrit dans sa Javadoc : un
`target/classes` perime reempaquete apres modification du source passerait dans un build reproductible. Aucun POM du depot ne fixe
`project.build.outputTimestamp`. La cause du rouge de CI du 2026-10-08 n'est ecrite dans aucun fichier de `squad/`, et je n'ai pas
observe d'execution GitHub sur ce commit.

## 6. `ADR` appele par une decision de s55 ?

**Aucun.** Les decisions d'architecture du chantier `ART-CP-10` sont dans `ADR-0104` (`Accepte`), dont la regle (13) est verte.
Les ecarts E1 a E3 sont des choix d'apparence ou de selecteur sous des `ADR` existants (`ADR-0027`, `ADR-0046`, `ADR-0104`
decision 4), et aucun n'est encodable en bytecode. Le correctif de provenance corrige un garde-fou que gouverne aucun `ADR`, avec
son jeu fautif dans le meme commit. Le seul manque constate, F4, est une decision deja ecrite et non gardee : elle ouvre une dette,
pas un `ADR`. `ADR-index.md` n'a rien a changer.

## 7. Jobs de `.github/workflows/ci.yml`

| Job | Lance ? | Commande | Resultat |
|---|---|---|---|
| `build` | **oui**, ses deux etapes | R1 puis R2, sur `af8b326e` (ce commit n'ajoute qu'un fichier de `squad/`) | `BUILD SUCCESS` deux fois |
| `chemins` | **oui** | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | rendu dans le compte rendu de la tache |
| `prefixes` | **oui** | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, puis le sujet de ce commit dans `verifier-prefixes.sh` | rendu dans le compte rendu de la tache |
| `outils` | **non** | aucun outil touche par ce commit | - |

Aucun Done n'est prononce par ce fichier.
