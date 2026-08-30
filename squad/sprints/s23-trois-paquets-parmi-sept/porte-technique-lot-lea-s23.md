# Porte technique - lot `lea-dev` de s23 (`s23-lea`)

- **Auteure** : `lea-dev`
- **Date** : 2026-08-30
- **Branche** : `s23-lea`, worktree dedie depuis `round-two` a `b1d8d9f`
- **Contenu du lot** : les cinq reserves non bloquantes de `cm1-problemes-durees`
  (`squad/conformite/FICHE-bandit-manchot-problemes-durees-cm1.md`, bloc de validation du
  2026-08-30 ; `docs/backlog/backlog.md`, section "Sprint 22", story 5 du planning de s23).
  Aucun paquet neuf, `catalogue.yaml` non touche.

## 1. Ce qui a change, reserve par reserve

- **Reserve A (`D-6`, derniere clause)** : l'item 9 (Diego) porte desormais un second
  distracteur ERR-BASE100 superieur a 59 minutes ("1 heure 75 minutes", calcule sur les instants
  propres de l'item 9), pour que l'ecriture de l'item 11 ("2 heures 70 minutes") ne soit plus la
  seule anomalie de ce type visible sur les 72 propositions. L'autre distracteur de l'item 9 est
  inchange.
- **Reserve B (`TYP-1`)** : les 24 enonces portent une espace insecable (U+00A0) devant leur "?"
  final, au lieu de l'espace ordinaire. Meme caractere que `cm1-reperes` (REP-6), seul precedent
  du depot. Le cliquet de dette (`AucunSigneDoubleNEstDetachableDeSonMotTest`, `ADR-0067`) baisse
  en consequence : `cm1-problemes-durees` de 27 a 3 (les 3 restantes sont des ":" dans des
  indices, hors du perimetre ecrit de cette reserve), total de 540 a 516.
- **Reserve C** : l'instant "9 h 10" de l'item 8 (Iris) porte une espace insecable entre "9" et
  "h", sur le seul cas mesure par `nadia-prof`. `PaquetCm1ProblemesDureesTest.INSTANT` tolere
  desormais ce caractere pour continuer a lire l'instant.
- **Reserve D (`D-7`, regularite structurelle)** : l'item 15 est reecrit avec deux distracteurs
  qui surestiment tous deux l'arrivee (ERR-REPORT trop, "15 h 40" ; ERR-BASE100, "15 h 20"),
  deux erreurs deja nommees par le fichier - aucune famille nouvelle inventee. La bonne reponse
  ("14 h 40") devient la plus petite des trois sur cet item. Rang du paquet entier : 7 / 12 / 5
  (29,2 % / 50,0 % / 20,8 %), contre 6 / 12 / 6 avant - toujours dans les bornes 10-50 % du
  garde-fou generique (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, non exempte
  pour ce paquet).
- **Reserve E** : le commentaire d'en-tete ne pretend plus que l'item de Nour se deroule "en
  classe" ; seul l'item d'Iris le dit.

Note versee sans trancher : le critere de la reserve D tel qu'ecrit dans `docs/backlog/
backlog.md` le rattache a "`D-6` (plausibilite et plafond du tiers)", mais la condition `D-6` de
cette histoire (arbitrage prealable, section 3.4, point 16) est explicitement "un plancher, pas
une prescription" et ne porte aucun plafond du tiers - ce plafond appartient a `MD-6`, une
condition d'une **autre** histoire (multiplications-divisions). Aucune des deux erreurs
reutilisees ici (ERR-REPORT, ERR-BASE100) n'est nouvelle ni plafonnee par `D-6` ; je le signale a
`camille-po` sans corriger le backlog moi-meme.

## 2. Verifie en jouant

Fat jar reconstruit sur l'arbre du worktree, lance sur le JDK 21 de la toolchain, session HTTP
reelle (`/parties/bandit-manchot-problemes-durees-cm1`, puis `/coups`), reponses correctes
soumises pour avancer d'item en item. Extraits du HTML reellement rendu (pas relu depuis le YAML
source) :

- Item 9 (Diego) : `<button ... value="1 heure 75 minutes">`, enonce se terminant par
  `match&#160;?` (U+00A0 avant le "?", confirme sur l'octet).
- Item 8 (Iris) : enonce `"... 9 h 10. ... la préparation ?"` - les deux espaces
  insecables (reserve C entre "9" et "h", reserve B avant "?") sont bien servies, pas seulement
  ecrites dans la source.
- Item 15 (film, 13 h 05) : propositions `14 h 40` (bonne reponse), `15 h 40`, `15 h 20` - la
  bonne reponse est la plus petite des trois a l'ecran.

## 3. La commande, sa version de JDK, son perimetre

```
bash outils/mvn-jdk21.sh -B clean verify
```

**Reacteur complet**, six modules, aucune mesure partielle. JDK de compilation et d'execution des
tests, lu dans la ligne de demarrage Spring Boot : **21.0.10** (toolchain). `BUILD SUCCESS`, six
modules en `SUCCESS`.

## 4. Le compte de tests, avec sa methode

**Methode** : somme des attributs `tests`/`failures`/`errors`/`skipped` de l'element
`<testsuite>` de chaque `TEST-*.xml` sous `target/surefire-reports/` et
`target/failsafe-reports/`, apres le `clean verify` ci-dessus.

**119 rapports, 772 tests, 0 echec, 0 erreur, 0 ignore.**

**Delta** : 0 test de plus que la base mesuree par `sofia-sm` a la fusion d'ouverture de s23
(`b1d8d9f`, 772 tests). Ce lot ne cree aucun test neuf : il modifie une donnee de contenu deja
couverte par les garde-fous existants (`PaquetCm1ProblemesDureesTest`, `AucunSigneDoubleNEst
DetachableDeSonMotTest`, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `LaValeurD
UnePropositionEstDeriveeDeCeQuElleEcritTest`), qui restent tous verts sur le contenu modifie -
c'est cette couverture prealable qui fait le test de ce lot, pas un test ajoute. Le total sur
l'arbre fusionne reste a etablir par `sofia-sm`.

## 5. Fichiers touches

- `adapters/adapter-content/src/main/resources/contenu/cm1-problemes-durees.yaml`
- `adapters/adapter-content/src/test/java/com/recreo/adapter/content/
  AucunSigneDoubleNEstDetachableDeSonMotTest.java` (table de dette et total)
- `adapters/adapter-content/src/test/java/com/recreo/adapter/content/
  PaquetCm1ProblemesDureesTest.java` (motif `INSTANT`, tolerance U+00A0)

## 6. Ce que je route

- `camille-po` : la reference erronee de `docs/backlog/backlog.md` (`D-6` cite avec un "plafond
  du tiers" qui appartient a `MD-6`), section 1 ci-dessus.
- `nadia-prof` : reaudit de `cm1-problemes-durees` a sa prochaine ceremonie - les cinq reserves
  sont levees sur leur substance, mais seule elle prononce si elles le sont sur le fond.
- `theo-archi`, `ines-design`, `sofia-sm` : le choix du caractere pour le reste de la dette
  `TYP-1` (516 occurrences restantes) reste ouvert (`ADR-0067`) ; ma correction de la reserve B
  suit le seul precedent deja ecrit (`cm1-reperes`, U+00A0) sans le trancher pour le reste du
  catalogue.
