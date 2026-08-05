# [Sprint s02] Rapport qualite - yanis-qa (redige a posteriori)

- **Date de redaction** : 2026-08-05, apres la cloture du sprint.
- **Auteur** : yanis-qa.
- **Pourquoi ce rapport est ecrit a posteriori** : `squad/sprints/s02-identite-visuelle/review.md`
  le dit sans l'adoucir, section "Porte technique" - aucun rapport qualite n'existait pour s02.
  `sofia-sm` n'a pas invente un Done technique a sa place ; ce fichier comble le manque, dans le
  meme esprit : ce qui suit constate l'etat reel de la suite, pas un vert que je n'aurais pas vu.
- **Perimetre audite** : commit `df6b4e3` (dernier commit de contenu du sprint, refus de S2-1
  leve, sous reserve). Verifie dans un arbre de travail isole (`git worktree`, hors de l'arbre
  partage avec `theo-archi` et `camille-po`), pour ne rien perturber de leur travail en cours et ne
  pas deplacer `HEAD` de la branche partagee.
- **JDK utilise** : aucun JDK 21 n'a ete trouve installe sur ce poste au moment de cette execution
  (seul un JDK 25 Temurin l'etait). Utilise a sa place : il compile et execute sans probleme le
  code cible sur la propriete `java.version=21` du POM parent. C'est un ecart factuel avec les
  audits precedents du sprint (`nadia-prof`, `sofia-sm`), qui citent un JDK 21.0.10 - je le signale
  plutot que de le taire, sans inventer d'explication a la disparition de ce JDK sur ce poste.

## 1. Commande et resultat

```
JAVA_HOME=<JDK compatible avec java.version=21> mvn -B clean verify
```

**Vert**, execute deux fois de suite sur `df6b4e3`, meme resultat les deux fois.

| Module | Tests | Echecs |
|---|---|---|
| `recreo-domain` | 31 | 0 |
| `recreo-application` | 7 | 0 |
| `recreo-adapter-web` | 38 | 0 |
| `recreo-adapter-content` | 5 | 0 |
| `recreo-bootstrap` (unitaires) | 49 | 0 |
| `recreo-bootstrap` (integration, Failsafe) | 2 | 0 |
| **Total** | **132** | **0** |

Ce total (132) correspond exactement a celui que `sofia-sm` avait constate de passage a la
cloture (`review.md`, section "Porte technique") : je le confirme moi-meme, plutot que de le
reprendre sur sa seule parole.

L'ecart avec le rapport de s01 (120 tests sur `82070bd`) est explique par les 12 tests ajoutes
dans `adapter-web` pour S2-1 et S2-3 : `TailleDesTextesEtBoutonsTest` (6, nouveau),
`HabillageDesEcransTest` (3, nouveau), `FaviconTest` (3, nouveau). `FrontieresDuJavaScriptTest`
existait deja depuis s00 (regle generale ADR-0003/ADR-0008), aucune de ses methodes n'est propre a
ce sprint.

## 2. Suivi de l'echec transitoire signale par theo-archi a la cloture de s01

`avis-architecture-cloture.md` (s01) rapportait deux echecs dans `LesReglesMordentTest` au premier
`clean verify` d'un passage, suivis d'un `BUILD SUCCESS` sans qu'une ligne ait change - huit
tentatives de reproduction toutes vertes, rapport Surefire fautif deja ecrase, cause non
identifiee, hypothese non confirmee (construction Maven concurrente dans le meme `target/`,
plusieurs agents ecrivant dans le meme arbre a ce moment-la).

**Ce sprint (s02), et dans le cadre de ce rapport, aucune reapparition constatee.** J'ai relance
`LesReglesMordentTest` isolement quatre fois de suite (une fois dans le `clean verify` complet
ci-dessus, trois fois seul), toutes vertes (22 tests, 0 echec, a chaque execution). Je ne referme
pas le point pour autant : quatre executions vertes de plus ne prouvent pas l'absence d'un defaut
intermittent, elles montrent seulement qu'il ne s'est pas reproduit dans les conditions ou je l'ai
cherche - un arbre de travail isole, sans ecrivain concurrent. Je n'invente aucune cause. Le point
reste ouvert au sens ou personne n'a explique pourquoi il s'est produit une fois ; il n'y a
simplement aucune nouvelle occurrence a signaler depuis s01.

## 3. Tracabilite des criteres d'acceptation - S2-1, S2-2, S2-3

Reference : `docs/backlog/backlog.md`, section Sprint 2. Le sprint n'a pas modifie
`squad/programme/MATRICE-couverture.md` (stories transverses, `DEC-0006`), donc aucune ligne de
couverture programme n'est en jeu ici.

### S2-1 - Aucun texte ni bouton plus petit que la consigne

| Critere (resume) | Test | Constat |
|---|---|---|
| Selecteurs de texte listes (`.score`, `.indice`, `.fin-de-partie`, `.erreur`, `.pas-de-jeu`, `.message-reprise`) >= taille de `.consigne` | `TailleDesTextesEtBoutonsTest.aucunTexteNePasseSousLaTailleDeLaConsigne` | Couvert, nomme. **Mais** cette methode enumere une liste de selecteurs ecrite en dur, alors que le critere du backlog dit explicitement "au minimum" (un plancher, pas une liste fermee) - voir section 4, meme forme de fragilite que celle demontree par `nadia-prof` sur les boutons. |
| Tout bouton actionnable >= 1,25rem, jamais laisse au defaut du navigateur | `TailleDesTextesEtBoutonsTest.toutBoutonReellementRenduEstAuMoinsAUnRemVingtCinq` | Couvert, nomme, au moment de `df6b4e3` (aucun bouton sans classe n'etait alors rendu par les gabarits livres). **Le mecanisme de derivation de ce test etait lui-meme defaillant** : voir section 4, defaut demontre par `nadia-prof` et corrige par moi apres la cloture de s02 (`164b662`, hors du perimetre de ce rapport a posteriori, mais route ici pour que la trace soit complete). |
| `.indice` >= `.consigne` | `TailleDesTextesEtBoutonsTest.lIndiceEstAuMoinsAussiGrandQueLaConsigne` | Couvert, nomme. |
| `h1` porte une regle de taille explicite | `TailleDesTextesEtBoutonsTest.h1PorteUneRegleDeTailleExplicite` | Couvert, nomme. |
| Hierarchie visuelle percue par un enfant de CP (non testable par automate) | - | Hors mandat, releve exclusivement de `nadia-prof` (refus puis levee, `FICHE-bandit-manchot-additions-cp.md`). Correctement non teste par un automate. |
| (Motif supplementaire du refus, cible tactile 44x44px) | `TailleDesTextesEtBoutonsTest.toutBoutonReellementRenduAUneCibleTactileDAuMoinsQuaranteQuatrePixels` | Couvert, nomme, trace vers le motif du refus plutot que vers un numero de critere du backlog (le critere lui-meme ne chiffre pas de cible tactile) - tracabilite correcte au sens large du mandat de `yanis-qa` (le nom du test porte la reference). |

### S2-2 - Un habillage visuel qui fait de recreo une application

| Critere (resume) | Test | Constat |
|---|---|---|
| Chaque selecteur liste porte au moins une regle de couleur, de fond ou d'espacement | `HabillageDesEcransTest.chaqueEcranNuPorteAuMoinsUneRegleDeCouleurDeFondOuDEspacement` | Couvert, nomme. La liste de selecteurs est ici la liste exacte donnee par le critere lui-meme (un ensemble ferme, pas un "au minimum") - enumeration correcte, pas une fragilite. |
| Les couleurs viennent d'une palette limitee et nommee, reutilisee | `HabillageDesEcransTest.lesCouleursProviennentDUnePaletteNommeeEtReutilisee` | Couvert, nomme (variables `--recreo-*` en nombre >= 4, chacune reutilisee au moins deux fois). |
| Toute ressource visuelle ajoutee est servie en local, sans CDN ni police telechargee | `HabillageDesEcransTest.aucuneRessourceExterneNiPoliceTelechargeeALExecution` | Couvert pour le CSS (absence de `http://`, `https://`, `@font-face`, `fonts.googleapis`). Rien de plus n'a ete ajoute par S2-2 (aucune image), donc pas de gap reel, mais le test ne verifierait pas une ressource visuelle non textuelle ajoutee par une story future. |
| `recreo.js` n'a recu aucune ligne de comportement nouveau | Aucun test nomme | **Non couvert par un test automatise.** Ce critere compare un etat avant/apres dans le temps (un diff), pas un invariant statique sur le fichier tel qu'il existe a un instant donne : aucun test unique ne peut l'exprimer sans rejouer l'historique Git. Verifie a la main par `sofia-sm` en review (git diff). Je ne le classe pas comme testable en l'etat ; s'il doit l'etre, la forme la plus proche serait un budget de taille par fichier verifie a chaque sprint (ce que `FrontieresDuJavaScriptTest.leJavaScriptDAgrementTientDansSonBudget` fait deja, mais en general, pas cible sur "aucune ligne ajoutee cette story-ci"). |
| Adequation du gout, ambiance, caractere accueillant (non testable par automate) | - | Hors mandat, `nadia-prof` seule. Correctement non teste. |

### S2-3 - Une icone pour reconnaitre recreo dans mon navigateur

| Critere (resume) | Test | Constat |
|---|---|---|
| `/favicon.ico` repond 200 | `FaviconTest.laRequeteFaviconRepond200AvecUnTypeDImage` | Couvert, nomme. |
| `Content-Type` annonce un type d'image | Meme test (`.startsWith("image/")`) | Couvert, nomme. |
| Icone servie comme ressource statique d'`adapter-web`, empaquetee dans le fat jar | `FaviconTest.lIconeExisteCommeRessourceStatiqueDAdapterWeb` | Couvert pour l'emplacement du fichier. L'absence d'appel reseau/CDN n'est pas assertee explicitement pour ce fichier precis (elle l'est implicitement par sa nature de ressource statique locale) - gap mineur de tracabilite, pas un defaut de comportement constate. |
| `accueil.html` reference explicitement l'icone (`<link rel="icon">`) | `FaviconTest.accueilReferenceExplicitementLIcone` | Couvert, nomme, assertion structurelle jsoup (`page.select("link[rel=icon]")`), pas de regex. |
| Qualite du dessin pour un enfant de CP/CM1 (non testable par automate) | - | Hors mandat, `nadia-prof` seule (accorde sous reserve : monogramme "R", demande un dessin d'objet pour la suite). |

## 4. Le garde-fou qui laissait repasser le defaut qu'il devait empecher - et sa correction

`review.md` et `nadia-prof` (`FICHE-bandit-manchot-additions-cp.md`) l'ont demontre, pas suppose :
`TailleDesTextesEtBoutonsTest`, a `df6b4e3`, derivait la liste des boutons a controler des
**classes CSS** portees par les boutons reellement rendus (`element.classNames()`), et non des
boutons eux-memes. Un `<button type="button">Rejouer</button>` sans attribut `class`, ajoute par
`nadia-prof` dans `fragments/retour.html` pour verifier le garde-fou, n'apparaissait dans aucune
classe et les six tests restaient verts. C'est exactement la forme du defaut de s01 sur "Encore !".

Ce defaut est **corrige apres la cloture de s02**, hors du perimetre de ce rapport a posteriori
mais route ici pour la tracabilite : commit `164b662`, meme fichier de test. La regle part
desormais des elements `<button>` et `<a href>` reellement rendus, et controle pour chacun les
selecteurs qui s'appliquent vraiment (ses classes si elle en porte, la balise nue sinon) ; un
bouton sans classe echoue maintenant au lieu d'etre absent du controle. Reproduit avant correction
(vert avec le bouton de demonstration present), puis apres correction (rouge avec le meme bouton,
vert une fois retire). Voir le commentaire de classe du fichier corrige pour le detail.

Ce point avait ete route par `sofia-sm` a la fois a moi et a `theo-archi`, sans prescrire la forme
de la correction ("ce n'est pas une decision de test et d'architecture qui m'appartient seule").
Je confirme que la forme choisie ici (deriver du rendu, pas des classes) reste une decision de
test, propre a ce fichier ; si le principe general - une convention de balisage garantissant qu'un
element interactif porte toujours une classe controlable - doit devenir une regle a l'echelle du
depot, ce n'est pas une decision que je prends seul : `theo-archi` travaille en parallele sur le
principe general, et c'est a lui de la trancher structurellement si elle doit exister.

## 5. Autres assertions fragiles du meme genre - recherche faite

- **Enumeration au lieu de derivation, deuxieme occurrence** : `TailleDesTextesEtBoutonsTest.
  aucunTexteNePasseSousLaTailleDeLaConsigne` enumere en dur les six selecteurs de texte
  (`.score`, `.indice`, `.fin-de-partie`, `.erreur`, `.pas-de-jeu`, `.message-reprise`), alors que
  le critere du backlog qui les introduit dit "au minimum" - exactement le mot qui avait motive le
  refus initial sur `.proposition` pour les boutons. Un nouveau selecteur de texte lu par l'enfant,
  ajoute par une story future, ne serait pas controle par ce test tant qu'il n'est pas ajoute a la
  main a cette liste. Ce n'est pas aussi grave que le defaut des boutons (aucun texte de ce type
  n'a ete omis a ce jour), mais c'est la meme forme de risque, et je la signale plutot que de la
  laisser dormir.
- **Assertion HTML par expression reguliere** : aucune dans le code de test de production, a
  `df6b4e3` comme sur l'arbre courant. La seule classe qui en contient une
  (`AssertionRegexContreExemple`) existe pour etre refusee : `AssertionsWebTest.
  aucuneAssertionParExpressionReguliere` (ArchUnit) l'interdit dans `adapter-web`, et sa
  non-vacuite est prouvee par `AssertionsWebTest.uneAssertionParExpressionReguliereEstRefusee`.
  Confirme par recherche sur l'ensemble de l'arbre de test (`Pattern.compile`, `java.util.regex`) :
  aucune autre occurrence.
- **Test qui passerait meme si le comportement etait casse (vacuite)** : les quatre tests de
  ce sprint qui derivent une liste depuis les gabarits ou le CSS
  (`auMoinsUnBoutonEstReleveDansLesGabarits`, et les deux tests "tout bouton..." qui en dependent)
  assertent explicitement que la liste derivee n'est pas vide avant de tester chaque element ; ils
  ne passeraient donc pas silencieusement sur un ensemble vide. Verifie par lecture du code.
- **Ordre de tirage non graine** : sans objet pour ce sprint - aucun test de S2-1/S2-2/S2-3 ne
  depend du hasard.

## 6. Confirmation ArchUnit de `theo-archi` - toujours absente pour s02 au moment de ce rapport

`review.md` le constate deja : aucun fichier `avis-architecture-cloture.md` ni
`confirmation-archunit.md` n'existe pour s02. Ce n'est pas mon mandat de le produire a sa place -
je confirme seulement que les 22 methodes de `LesReglesMordentTest` (module `bootstrap`) passent
dans le `clean verify` rapporte en section 1, ce qui n'est pas une revue regle par regle et ne se
substitue pas a son avis.

## 7. Ce qui est couvert, en une phrase par story

- **S2-1** : aucun des selecteurs de texte ou de bouton controles ne retombe sous 1,25rem ni sous
  la cible tactile de 44px, mais le mecanisme qui derive "tout bouton controle" avait un angle mort
  reel (boutons sans classe), demontre par `nadia-prof`, corrige apres la cloture du sprint.
- **S2-2** : les onze selecteurs cibles portent tous une regle de couleur, fond ou espacement, et
  les couleurs viennent d'une palette nommee et reutilisee ; le critere "aucune ligne de JS
  nouvelle" n'a pas d'equivalent testable par un invariant statique et reste verifie a la main.
- **S2-3** : l'icone existe, repond 200 en `image/*`, est servie hors ligne et referencee
  explicitement dans `accueil.html` ; sa qualite de dessin pour un enfant reste au seul jugement de
  `nadia-prof` (accordee sous reserve, monogramme a redessiner en objet).

## 8. Points remontes a `sofia-sm`

1. Le defaut de derivation par classes CSS de `TailleDesTextesEtBoutonsTest` (section 4) est
   corrige ; la question de fond qu'il souleve - une convention de balisage garantissant qu'un
   element interactif porte toujours une classe controlable - reste ouverte et releve de
   `theo-archi` si elle doit devenir une regle structurelle.
2. `aucunTexteNePasseSousLaTailleDeLaConsigne` enumere une liste fermee pour un critere ouvert
   ("au minimum") : meme forme de risque que celle qui a motive le refus initial de S2-1, non
   encore corrige. Je recommande la meme derivation depuis les gabarits que celle appliquee aux
   boutons, le jour ou ce fichier est retouche.
3. Le critere "`recreo.js` n'a recu aucune ligne de comportement nouveau" (S2-2) n'est pas
   testable par un automate statique ; il restera verifie a la main (git diff) sauf decision
   contraire de `sofia-sm` ou `theo-archi`.
4. L'echec transitoire de `LesReglesMordentTest` signale par `theo-archi` a la cloture de s01 ne
   s'est pas reproduit dans les quatre executions isolees relancees pour ce rapport ; la cause
   reste inconnue et le point n'est pas referme.
5. Aucun obstacle bloquant pour le Done technique de s02 au sens strict de `mvn verify` : la suite
   est verte, sans test desactive ni assertion affaiblie pour l'obtenir. Le Done technique complet
   au sens de `squad/README.md` reste toutefois incomplet tant qu'aucune confirmation ArchUnit de
   `theo-archi` n'existe pour ce sprint (section 6).
