# [Sprint s01] Rapport qualite - yanis-qa

- **Date** : 2026-08-05
- **Auteur** : yanis-qa
- **Perimetre** : arbre fusionne par sofia-sm au commit `d534451` (fusion des trois worktrees
  marc-dev, lea-dev, ines-design), stories S1-1 a S1-9 (`docs/backlog/backlog.md`, sprint 1) et
  les cinq points tranches par `DEC-0004`. Pendant que j'ecrivais ce rapport, `theo-archi` a
  ferme sa propre porte en parallele sur ce meme arbre partage et a commite `82070bd` (ADR-0012
  complete, regle mise a l'epreuve) : je l'ai isole (`git stash`, verifie separement, puis
  restaure sans rien perdre du travail des autres agents) pour pouvoir juger les deux etats
  separement plutot que de melanger un commit que je n'avais pas mandat de rapporter avec celui
  que j'avais a auditer. Le detail est dans la section suivante ; le reste de ce rapport porte sur
  l'etat courant de la branche (`82070bd`, qui n'ajoute ni ne retire aucun test de production des
  neuf stories du sprint, seulement la fermeture d'un manque d'ADR-0012 deja route a `theo-archi`).

## Commande et resultat

```
JAVA_HOME=<JDK 21> mvn -B clean verify
```

**Vert**, sur les deux commits, verifies separement.

| Module | Tests sur `d534451` | Tests sur `82070bd` (HEAD) | Echecs |
|---|---|---|---|
| `recreo-domain` | 31 | 31 | 0 |
| `recreo-application` | 7 | 7 | 0 |
| `recreo-adapter-web` | 26 | 26 | 0 |
| `recreo-adapter-content` | 5 | 5 | 0 |
| `recreo-bootstrap` (unitaires) | 48 | 49 | 0 |
| `recreo-bootstrap` (integration, Failsafe) | 2 | 2 | 0 |
| **Total** | **119** | **120** | **0** |

L'ecart d'un test entre les deux colonnes est explique, pas mysterieux : `82070bd` ajoute exactement
une methode de test (`LesReglesMordentTest.uneLongueurDePartieDansLeContratDeContenuEstRefusee`,
non-vacuite du contre-exemple d'ADR-0012). Aucune instabilite reelle constatee : chaque commit,
teste isolement en `clean verify` a plusieurs reprises, rapporte toujours le meme total pour
lui-meme, zero echec dans tous les cas.

**Isolation des deux commits, par honnetete envers ce rapport.** Ma premiere tentative de commit a
inclus par erreur des fichiers d'un autre agent qui travaillait en parallele sur le meme arbre
(consequence directe du travail en worktrees paralleles amorce ce sprint - voir planning.md,
"changement assume"). Je l'ai defait immediatement (`git reset --soft`, jamais de commit ecrase ni
de `push --force`), puis j'ai isole `d534451` avec `git stash --include-untracked` pour le tester
seul, avant de restaurer integralement le travail des autres agents (rien perdu : chaque fichier
restaure verifie identique a son etat avant mon intervention). Le tableau ci-dessus est le resultat
de cette double verification.

**Ecart avec l'annonce de la fusion** : le commit `d534451` annonce 110 tests. La suite reellement
executee en compte 119 sur ce meme commit - un ecart favorable (plus de tests que l'annonce, jamais
moins), qui n'affecte donc pas le verdict, mais je le signale pour que le chiffre annonce dans un
message de commit ne soit jamais pris comme le compte de reference sans re-executer la suite.

## Couverture reelle par module - la dette nommee par CLAUDE.md n'est pas reproduite

CLAUDE.md liste, parmi les dettes de la version precedente a ne pas reproduire : des modules
entiers sans aucun test (`application`, `adapter-web`, `adapter-persistence`).

| Module | Tests | Constat |
|---|---|---|
| `application` | 7 (1 classe, `PartieServiceTest`) | Couvert des son premier commit de code. Le service d'orchestration n'est plus teste par ricochet : `PartieServiceTest` verifie directement `demarrer`, `jouer`, la resolution par le registre de mecaniques, et la transmission de `nombreDeManches` (ADR-0012). |
| `adapter-web` | 26 (5 classes) | Couvert des son premier commit de code (sprint 0). Ce sprint y ajoute `ErreurDeSessionPerdueTest` (ADR-0011) et des cas dans `PartieControllerTest` et `TexteAccentueDesGabaritsTest`. Toutes les assertions HTML passent par jsoup (voir section suivante). |
| `adapter-content` | 5 (1 classe, `YamlContentPackLoaderTest`) | Le chargeur est couvert (schema, deux types de paquet, quatre cas d'echec nommant fichier et champ, dont le nouveau champ `nombreDeManches` d'ADR-0012). Point plus faible : les deux fabriques concretes (`AdditionsBorneesContentSourceFactory`, `QuestionContentSourceFactory`) ne sont exercees qu'a travers le chemin heureux du chargeur, pas par leurs propres cas d'echec unitaires (champ `parametres` absent, `sommeMaximale` non entier, item sans `distracteurs`, etc.). Ce n'est pas un module a zero test, c'est une couverture indirecte et incomplete en profondeur - non bloquant, a approfondir. |
| `domain` | 31 (5 classes) | Couvre la mecanique (`BanditManchotTest`, 16 tests, dont les trois nouveaux de S1-5 et celui d'ADR-0012), les sources de contenu, et les deux regles ArchUnit de decouplage propres a ce module. |
| `bootstrap` | 49 unitaires + 2 IT (HEAD `82070bd`) | Regles ArchUnit (dont les deux nouvelles d'ADR-0011), non-vacuite (`LesReglesMordentTest`), parcours HTTP complet (`PartieHttpTest`, S0), catalogue reel charge (`CatalogueChargeableIT`, verifie ADR-0007 et ADR-0012 ensemble). |
| `adapter-persistence` | n'existe pas | Conforme a `CLAUDE.md` : ce module n'est cree qu'a la premiere story qui a besoin de persistance entre deux sessions. Aucune story de s01 ne le demande ; son absence n'est donc pas une dette. |

**Conclusion** : la dette "modules entiers sans aucun test" n'est pas reproduite sur `round-two`.
Les cinq modules livres ont des tests reels, cibles, et rapides (aucun ne demarre tout le contexte
Spring en premier recours ; `bootstrap` reserve `@SpringBootTest` a une poignee de cas explicitement
justifies).

## Preuve d'execution du decouplage mecanique/contenu

`BanditManchotTest.laMemeMecaniqueJoueDeuxContenusDifferentsSansAucunChangementDeCode` (heritee de
S0-3, toujours verte apres le changement de signature de `GameMechanic.start` par ADR-0012) fait
jouer la meme instance de `BanditManchot` sur un contenu combinatoire de mathematiques CP puis sur
un contenu enumere d'anglais CM1, sans sous-classe ni configuration. C'est la preuve d'execution
que le principe directeur du produit exige en plus de la preuve structurelle ArchUnit de
theo-archi. Ce test existe et tourne ; le decouplage n'est donc pas seulement theorique.

## Tracabilite des criteres d'acceptation

### S1-1 - Un texte que l'enfant peut lire sans apprendre une faute

| Critere | Test |
|---|---|
| Le retour faux affiche chaque mot accentue correctement, sans faute | `TexteAccentueDesGabaritsTest.lesTroisFautesRepereesParNadiaNeReviennentPas` (comparaison de sous-chaine sur les trois gabarits) |
| Le message de fin de partie est integralement accentue | `TexteAccentueDesGabaritsTest.leMessageDeFinDePartieResteAccentue` ("joué"), `PartieControllerTest.lEcranDeFinDePartieEstAccentueEtEnRegistreEnfant` |
| Audit du `relecteur-pedagogique` sur les trois gabarits | Non automatisable par construction (jugement de redaction) - hors de mon mandat, confie au bon agent |

### S1-2 - Un score que l'enfant de CP sait lire

| Critere | Test |
|---|---|
| Score sous forme `Score : N`, aucun symbole operatoire | `PartieControllerTest.leScoreNAffichePasDeSymboleOperatoire`, `.uneReponseCorrecteAfficheLeSignalJusteEtAvanceLeScore` (`"Score : 1"`), `.uneReponseIncorrecteAfficheLeSignalFauxEtDesigneLaBonneReponse` (`"Score : 0"`) |

### S1-3 - Un ecran franchissable quand quelque chose tourne mal

| Critere | Test |
|---|---|
| Statut 200, `Content-Type` HTML, message exact, bouton "Recommencer" vers `/`, aucun marqueur technique | `ErreurDeSessionPerdueTest` (deux cas : coup sans partie en cours, demarrage sur jeu inconnu) |
| Une porte de rendu d'erreur existe dans `adapter-web`, ne rend jamais de corps technique brut | `GestionDesErreursTest` (ArchUnit, deux regles + non-vacuite dans `LesReglesMordentTest`) |

### S1-4 - Une partie qui a une fin et une sortie

| Critere | Test |
|---|---|
| La partie se termine exactement au nombre de manches annonce, meme avec un contenu combinatoire infini | `BanditManchotTest.laPartieSeTermineApresLeNombreDeManchesMemeAvecUnContenuCombinatoireInfini`, `PartieControllerTest.unEcranDeFinSAfficheReellementApresLeNombreDeManchesMemeAvecUnContenuInfini`, `PartieServiceTest.demarrerTransmetLeNombreDeManchesDuJeuALaMecanique`, `YamlContentPackLoaderTest` (valeur 10 lue depuis le YAML), `CatalogueChargeableIT` (le vrai catalogue porte une valeur positive) |
| Ecran de fin reel, score final au format S1-2 | `PartieControllerTest.unEcranDeFinSAfficheReellementApresLeNombreDeManches...` |
| Bouton de retour visible sur n'importe quel ecran | `PartieControllerTest.leBoutonDeRetourAlAccueilEstVisibleSurLaManche`, `.leBoutonDeRetourAlAccueilEstVisibleApresUneReponse`. Non teste explicitement sur l'ecran de fin de partie lui-meme, mais le bouton vit dans le meme fragment Thymeleaf que les deux ecrans deja testes (`retour.html`, un seul gabarit, pas de duplication) : il ne peut pas disparaitre de l'ecran de fin sans casser un test deja vert. Nuance mineure, non bloquante. |
| Reprise silencieuse sur session valide | `PartieControllerTest.rechargerLaPageEnCoursDePartieRepresenteLaMancheSansAucunMessage` |
| Message exact sur session perdue | `PartieControllerTest.rechargerLaPageAvecUneSessionPerdueAfficheLeMessageDePartiePerdue`, `.leMessageDePartiePerdueNeReapparaitPasApresAvoirEteAffiche` |

### S1-5 - Un enfant qui echoue plusieurs fois est aide, pas enferme

| Critere | Test |
|---|---|
| Item rate trois fois -> indice a la representation suivante | `BanditManchotTest.unItemRateTroisFoisEstRepresenteAvecUnIndiceLaFoisSuivante`, `PartieControllerTest.unIndiceApparaitApresTroisEchecsSurLeMemeItem` |
| Trois echecs consecutifs (item quelconque) -> item deja reussi represente, puis retour au tirage normal | `BanditManchotTest.troisEchecsConsecutifsRepresententUnItemDejaReussiPuisLeTirageNormalReprend` |
| Trois echecs consecutifs sans aucun item deja reussi -> tirage normal, sans erreur | `BanditManchotTest.troisEchecsConsecutifsSansAucunItemReussiSuiventLeTirageNormal` |
| Le bouton de sortie reste visible pendant une serie d'echecs | Pas de test dedie a ce scenario precis (serie d'echecs + bouton). Meme remarque que pour S1-4 : le bouton vit dans le fragment partage, deja teste independamment. Nuance mineure, non bloquante. |

### S1-6 - Un signal juste/faux qui ne depend pas de distinguer les couleurs

**Aucun test automatise, pour les quatre criteres.** `theo-archi` avait pourtant identifie dans son
avis de faisabilite un critere testable par automate ("les deux formes sont structurellement
differentes, deux jeux de regles CSS distincts"). La CSS livree par `ines-design` (`recreo.css`)
distingue bien `.signal.est-juste` (coche, `clip-path`) et `.signal.est-faux` (croix, deux barres
en `::before`/`::after`) - verifie par lecture, pas par un test qui l'affirmerait de facon
regressible. `PartieControllerTest` verifie la presence des classes `.signal.est-juste` et
`.signal.est-faux` dans le HTML rendu (donc que le bon signal est choisi selon le verdict), mais
aucun test ne verifie que les deux jeux de regles CSS sont reellement distincts entre eux ni que
l'emoji est absent du fichier CSS lui-meme. **Critere non couvert, remonte plus bas.**

### S1-7 - Des additions qui font vraiment calculer

| Critere | Test |
|---|---|
| Sur 200 tirages a graine fixee, entre 10 et 30 comportent au moins un operande nul | `AdditionsBorneesContentSourceTest.surDeuxCentsTiragesAGraineFixeeEntre10Et30OntAuMoinsUnOperandeNul` - voir verification de determinisme ci-dessous |

### S1-8 - Des mots d'interface que l'enfant de CP comprend

| Critere | Test |
|---|---|
| Le bouton de continuation n'emploie plus "manche" | `PartieControllerTest.leBoutonPourContinuerNEmploiePlusLeMotManche` |
| L'ecran de fin n'est plus de registre adulte | `PartieControllerTest.lEcranDeFinDePartieEstAccentueEtEnRegistreEnfant` |
| L'ecran "aucune partie disponible" est reformule en vocabulaire courant | **Non couvert par un test nomme.** Le texte est bien change en production (`accueil.html:18`, `"Il n'y a pas de jeu pour le moment."` remplace `"Aucune partie disponible pour le moment."`), mais aucun test ne demarre un catalogue vide pour verifier que ce texte s'affiche. Critere non couvert, remonte plus bas. |
| Le retour faux est au present, une phrase courte | `PartieControllerTest.leRetourFauxEstAuPresentEtEnUnePhraseCourte` |
| Le libelle "Additions CP" ne porte plus le grapheme "ti" | `CatalogueChargeableIT` (verifie sur le vrai catalogue, pas un catalogue de test) |

### S1-9 - Un calcul et un signal plus grands que les boutons

**Aucun test automatise, pour les deux criteres**, alors que `theo-archi` les avait lui-meme
qualifies de testables par automate ("les valeurs declarees dans `recreo.css`, lues par
comparaison de valeur sur le fichier texte"). Verifie par lecture : `.enonce` porte `font-size:
2rem` (le critere demande au moins 2rem, contre 1,1rem constate pour `.proposition`) et
`.icone-signal` porte `width`/`height: 3rem` (le critere demande au moins 3rem, contre 1,5rem
constate). Les deux valeurs respectent le critere a la lecture, mais rien ne le garde contre une
regression future. **Critere non couvert, remonte plus bas.**

## Assertions fragiles - verifications faites

- **Assertion HTML par expression reguliere** : aucune dans le code de test de production. La
  seule classe qui en contient une (`AssertionRegexContreExemple`) existe pour etre refusee :
  `AssertionsWebTest.aucuneAssertionParExpressionReguliere` (ArchUnit) l'interdit dans
  `adapter-web`, et sa non-vacuite est prouvee par
  `AssertionsWebTest.uneAssertionParExpressionReguliereEstRefusee`, qui verifie que la regle
  echoue reellement contre ce contre-exemple. Toutes les assertions HTML reelles passent par
  jsoup (selecteurs de classe), y compris les deux nouveaux fichiers de test de ce sprint
  (`ErreurDeSessionPerdueTest`, cas ajoutes dans `PartieControllerTest`).
- **Ordre de tirage non graine** : aucun trouve. Chaque test qui depend du hasard recoit un
  `RandomSource` construit soit sur `new Random(<graine fixe>)`, soit sur une lambda constante
  (`bound -> 0`). Aucun test de ce sprint n'utilise `new Random()` sans graine ni ne depend de
  l'ordre d'iteration d'un `HashSet`/`HashMap` dans le chemin qu'il exerce.
- **Tests qui passeraient meme casses (vacuite)** : les deux nouvelles regles ArchUnit
  d'existence de ce sprint (`GestionDesErreursTest.uneAdviceDErreurExiste`,
  `DecouplageMecaniqueContenuTest.uneMecaniqueVitDansSonProprePaquet`, deja presente avant ce
  sprint) portent `allowEmptyShould(false)`, donc echouent si aucune classe ne correspond, plutot
  que de passer par defaut sur un ensemble vide. Verifie par lecture du code, pas par un
  contre-exemple dedie a chacune (ce tableau regle par regle est le mandat de `theo-archi`, pas le
  mien).

## S1-7 - verification de determinisme du test sur les operandes nuls

`AdditionsBorneesContentSourceTest.surDeuxCentsTiragesAGraineFixeeEntre10Et30OntAuMoinsUnOperandeNul`
utilise `new Random(2026)` comme seule source de hasard, consommee sequentiellement sur les 200
tirages (tirage des deux operandes, boucle de rejet, melange des distracteurs) sans navigateur, ni
horloge, ni structure a iteration non ordonnee dans le chemin exerce.

- Execute cinq fois de suite (seul, puis dans la classe complete, puis dans le module complet en
  `clean verify`) : resultat stable a chaque fois, dans la fourchette `[10, 30]` exigee par
  `DEC-0004`.
- Reimplementation independante du meme algorithme de tirage, avec la meme graine `2026`, en
  dehors du depot : compte obtenu de l'ordre d'une vingtaine sur 200 tirages, coherent avec la
  fourchette du test et avec l'ordre de grandeur annonce en review par `lea-dev` ("environ 20").
- **Conclusion : le test est deterministe, pas instable.** Aucune preuve d'instabilite trouvee.

## Defauts et gaps de couverture

| Constat | Bloquant / non bloquant pour le Done technique | Detail |
|---|---|---|
| S1-6 : zero test automatise sur les quatre criteres | Non bloquant (`mvn verify` reste vert independamment), mais gap de tracabilite reel | `theo-archi` avait lui-meme qualifie un critere de testable par automate. A ecrire avant que `nadia-prof` ne considere l'increment stable dans le temps. |
| S1-9 : zero test automatise sur les deux criteres | Non bloquant, gap de tracabilite reel | Idem : critere que `theo-archi` avait qualifie testable par automate (lecture de valeur dans `recreo.css`). |
| S1-8, critere "ecran catalogue vide" : aucun test nomme | Non bloquant | Le texte de production est correct ; seul le test manque. |
| `adapter-content` : les deux fabriques de contenu ne sont testees qu'a travers le chemin heureux du chargeur | Non bloquant | Couverture indirecte reelle, mais aucun cas d'echec propre a chaque fabrique (champ `parametres` absent, type non entier, `distracteurs` manquant) n'a son propre test unitaire. |
| Indice de S1-5 ("Compte avec tes doigts pour t'aider.") code en dur dans `AdditionsBorneesContentSource` plutot que porte par le paquet YAML | Non bloquant, deja route | Constat independant, confirme par lecture du code (`domain/src/main/java/com/recreo/domain/content/source/AdditionsBorneesContentSource.java`, constante `INDICE`). `camille-po` a deja route ce point (`DEC-0005-indice-s1-5-vit-dans-le-yaml-pas-dans-le-domaine.md`, non bloquant pour la cloture de s01, corrige au backlog de s02) et `relecteur-pedagogique` l'a confirme dans sa propre relecture. Je le confirme ici du seul point de vue qui m'incombe : ce n'est pas un defaut de test, `BanditManchotTest` et `PartieControllerTest` couvrent bien le comportement observable (l'indice apparait au bon moment), mais aucun test ne peut garantir que ce texte reste modifiable sans recompilation tant qu'il vit dans une constante Java. |

## Points remontes a sofia-sm

1. **S1-6 et S1-9 n'ont recu aucun test alors que `theo-archi` les avait explicitement qualifies
   de testables par automate dans son avis de faisabilite.** Cela laisse deux criteres
   d'acceptation sans filet de regression : une future modification de `recreo.css` pourrait
   annuler la distinction coche/croix ou reduire les tailles de police sans qu'aucun `mvn verify`
   ne le detecte. Je recommande qu'un test soit ecrit avant que l'increment ne soit considere
   stable - a affecter, pas necessairement a moi si un autre agent est mieux place pour juger la
   forme du test le plus adapte a de la CSS.
2. **S1-8, critere "ecran catalogue vide"** : meme remarque, perimetre plus restreint.
3. Aucun obstacle bloquant pour le Done technique de ce sprint : `mvn verify` est vert, sans test
   desactive ni assertion affaiblie pour l'obtenir.

## Ce qui est couvert, en une phrase par story

- **S1-1** : les trois fautes connues ne reviennent pas et les textes cibles restent accentues ;
  le jugement de fond reste a `relecteur-pedagogique`.
- **S1-2** : le score s'affiche exactement sous la forme decidee, sans aucun symbole operatoire.
- **S1-3** : toute erreur imprevue du chemin de jeu rend un fragment HTML francais, jamais un
  corps technique brut, garde a la fois structurellement (ArchUnit) et par le contenu (jsoup).
- **S1-4** : une partie se termine exactement au nombre de manches configure, meme sur un contenu
  infini, avec un ecran de fin reel et une reprise coherente selon que la session est valide ou
  perdue.
- **S1-5** : l'indice et la manche plus simple se declenchent exactement aux seuils decides,
  avec le cas limite (aucun item reussi) correctement traite.
- **S1-6** : le rendu correct existe et a ete verifie par lecture, mais aucun test ne le garde.
- **S1-7** : la proportion de tirages a operande nul est verifiee de facon deterministe dans la
  fourchette decidee.
- **S1-8** : quatre des cinq reformulations sont couvertes par un test nomme ; la cinquieme
  (ecran catalogue vide) ne l'est pas encore.
- **S1-9** : le rendu correct existe et a ete verifie par lecture, mais aucun test ne le garde.
