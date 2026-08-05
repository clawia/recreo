# [Sprint s00] Rapport de sprint - socle

- **Debut** : 2026-08-04 15:39
- **Fin** : 2026-08-04 23:57
- **Duree effective** : 8h18

## Increment livre

Un socle applicatif Java 21 / Spring Boot en Clean Architecture a cinq modules sert de bout en
bout, avec un contenu pedagogique reel, une manche du jeu bandit manchot sur les additions dans
la limite de 10 (mathematiques, CP) - mais l'enfant ne peut pas encore y jouer en pratique, le
Done pedagogique ayant ete refuse.

En detail :

- Cinq modules Maven (`domain`, `application`, `adapters/adapter-web`, `adapters/adapter-content`,
  `bootstrap`), packages en fat jar unique (`bootstrap/target/recreo.jar`), 21 regles ArchUnit
  toutes contre-exemplifiees, garde-fou de prefixe de commit extrait en script versionne et teste.
- La mecanique bandit manchot (`com.recreo.domain.mechanics.banditmanchot`) et le contenu additions
  bornees CP sont decouples des deux cotes : la meme mecanique joue reellement deux contenus
  differents sans changement de code, verifie a l'execution et pas seulement par ArchUnit.
- 92 tests verts, aucune instabilite constatee sur executions repetees.
- `nadia-prof` a joue 61 manches reellement et refuse le Done pedagogique sur trois motifs : texte
  non accentue affiche a l'enfant, score en notation illisible au CP, ecran d'erreur en anglais
  sans issue. Le contenu mathematique lui-meme est juge exact et sans defaut.

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| ADR-0001 | Decouper le build en cinq modules Maven et orienter les dependances vers l'interieur | Architecture | Accepte, amende |
| ADR-0002 | Interdire toute dependance externe dans `domain` et dans `application` | Architecture | Accepte, amende |
| ADR-0003 | Prouver que chaque regle ArchUnit mord, par un contre-exemple qui doit la faire echouer | Architecture | Accepte, amende (deux fois) |
| ADR-0004 | Decoupler la mecanique du contenu par deux contrats fermes et une regle bidirectionnelle | Architecture | Accepte, amende |
| ADR-0005 | Abandonner `RuleSet` et interdire les abstractions fantomes | Architecture | Accepte, amende |
| ADR-0006 | Supprimer la configuration de difficulte du domaine et la porter par le paquet de contenu | Architecture | Accepte, amende (sa regle centrale reecrite) |
| ADR-0007 | Charger tout le contenu avec un seul chargeur pilote par un schema | Architecture | Accepte, amende |
| ADR-0008 | Loger tout le comportement de jeu sur le serveur et brider le JavaScript par un budget | Architecture | Accepte, amende |
| ADR-0009 | Extraire les garde-fous de CI dans des scripts versionnes, et leur donner des contre-exemples | Architecture | Accepte, **nouveau ce sprint** |
| DEC-0001 | Perimetre du sprint 0 et choix de la premiere competence couverte | Produit | Acceptee |
| DEC-0002 | Statut des emoji et pictogrammes dans le contenu et l'interface destines a l'enfant | Produit | Acceptee, amendee |
| DEC-0003 | Le sprint 0 ne cree pas de story de persistence, le critere d'integration est reformule | Produit | Acceptee |

Neuf ADR (dont un nouveau, `ADR-0009`, et huit amendes) et trois DEC (dont une amendee,
`DEC-0002`).

## Etat des deux Done

| Done | Juge par | Etat | Reference |
|---|---|---|---|
| Technique | CI (`mvn verify`) | vert - 92 tests, 0 echec, plus 14 cas verts sur `verifier-prefixes-test.sh` | `squad/sprints/s00-socle/rapport-qualite.md`, `squad/sprints/s00-socle/confirmation-archunit.md` |
| Pedagogique | nadia-prof | REFUSE | `squad/conformite/FICHE-bandit-manchot-additions-cp.md` |

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| JDK 21 absent du `PATH`, toolchains Maven mal nommees | sofia-sm | decision n°1 de `planning.md` | 2026-08-04 |
| Ecart `CLAUDE.md` / `ADR-0001` / `DEC-0003` sur `adapter-persistence` et "integration H2" | relecteur-pedagogique | correction directe (pas d'ADR/DEC requis) | `89f7bae` |
| Catalogue de repli (`CatalogueDeRepliConfiguration`) contraire au chargement eager sans repli silencieux d'`ADR-0007` | lea-dev | correction directe | `abc8495` |
| Regles de garde sans contre-exemple (4 signalees par `yanis-qa`, 6 trouvees a l'inventaire de `theo-archi`) | theo-archi | amendements `ADR-0001` a `ADR-0008` | `f2bef10` |
| Garde-fou de prefixe de commit non teste (script shell sans test automatise) | yanis-qa puis theo-archi | `ADR-0009` | `f2bef10` |
| Refus pedagogique sans traduction en story ni exigence positive pour le texte de categorie B | camille-po | `DEC-0002` amendee + `docs/backlog/backlog.md` section Sprint 1 | `881768a` |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| `leContratDeContenuNePorteQueDuTexte` (ADR-0006) admet encore un enum arithmetique s'il est place dans le paquet de contrat lui-meme (`com.recreo.domain.content`) | theo-archi | 2026-08-04 (releve a la relecture de sofia-sm en cloture de sprint) | un contre-exemple qui ferme ce trou, ou une decision explicite de l'accepter, au sprint 1 |
| L'increment `bandit-manchot-additions-cp` reste non valide pedagogiquement, verdict REFUSE de `nadia-prof`, trace dans `squad/validation/FILE-ATTENTE.md` | camille-po / nadia-prof | 2026-08-04 (sprint 0) | les stories S1-1 a S1-3 du backlog livrees, puis un nouveau passage de `nadia-prof` |
| Aucun agent n'a mandat sur le design d'interface et l'accessibilite visuelle (`recreo.css` ecrit par `marc-dev`, juge par `nadia-prof`, tous deux hors mandat) ; les stories S1-6 et S1-9 du backlog n'ont aucun agent naturel | sofia-sm | 2026-08-04 (obstacle identifie apres la retro) | la definition du neuvieme agent (design d'interface) dans `.claude/agents/`, son prefixe de commit ajoute a `verifier-prefixes.sh` et a son test, son entree dans `EQUIPE.md`, `squad/README.md` et `CLAUDE.md` - tout prevu au planning du sprint 1, rien de cela n'est encore ecrit |

## Couverture programme

Aucune ligne de `squad/programme/MATRICE-couverture.md` n'atteint `couvert-valide` ce sprint, et
c'est la reponse honnete : ce statut n'est jamais attribue sur la seule foi d'une CI verte, et
`nadia-prof` a refuse le Done pedagogique. La seule ligne mouvementee est mathematiques / CP /
"Calcul mental - additions dans la limite de 10 (puis 20)", qui passe de `non couvert` (etat de
reconstruction de `round-two`) a `en cours` (jeu `bandit-manchot-additions-cp`, mecanique bandit
manchot) et y reste a la cloture du sprint. Elle ne peut pas passer plus loin avant un verdict
`VALIDE` sur la fiche de conformite, et le "puis 20" du referentiel reste hors perimetre par
`DEC-0001` meme le jour ou la partie "jusqu'a 10" sera validee.

## Consommation de tokens

Produit par `python outils/rapport-tokens.py --sprint s00`, ecrit dans
`squad/sprints/s00-socle/RAPPORT-tokens.md` et cumule dans `squad/rapports/JOURNAL-tokens.md`.
Fenetre mesuree : 2026-08-04 13:39 a 21:58 UTC (l'horodatage de debut du planning, converti en
UTC, a l'heure de generation du rapport).

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 44 | 88 | 10 662 | 316 724 | 2 331 260 |
| `sofia-sm` | claude-sonnet-5 | 48 | 96 | 4 862 | 811 803 | 1 666 641 |
| `theo-archi` | claude-opus-5 | 320 | 11 833 | 155 891 | 995 872 | 38 802 010 |
| `marc-dev` | claude-sonnet-5 | 239 | 478 | 34 445 | 1 261 559 | 39 934 157 |
| `lea-dev` | claude-sonnet-5 | 173 | 1 732 | 41 234 | 907 925 | 21 121 377 |
| `yanis-qa` | claude-sonnet-5 | 154 | 122 386 | 31 599 | 706 928 | 19 065 319 |
| `relecteur-pedagogique` | claude-sonnet-5 | 155 | 2 144 | 24 887 | 709 691 | 14 436 633 |
| `nadia-prof` | claude-opus-5 | 68 | 4 022 | 33 689 | 183 292 | 2 853 354 |
| orchestrateur (fil principal) | claude-opus-5 | 93 | 178 | 108 093 | 503 396 | 14 607 798 |
| **Total** | | **1294** | **142 957** | **445 362** | **6 397 190** | **154 818 549** |

Les quatre compteurs sont volontairement separes (les additionner classerait faux : le cache lu
depasse couramment l'output de deux ordres de grandeur), et ce ne sont pas des euros.

**Reserve de lecture, a ne pas ignorer** : le sprint 0 a ete ouvert dans une session ou la branche
orpheline `round-two` avait vide `.claude/agents/` et desenregistre les 8 agents (voir
`squad/sprints/s00-socle/retro.md`). Les toutes premieres invocations de `camille-po` et
`theo-archi` pour le planning et les huit ADR fondateurs ont donc pu tourner avant leur
reenregistrement complet, ce qui peut fausser l'attribution par agent sur cette portion du
sprint - c'est precisement ce que la note de passation, desormais supprimee, avait signale par
avance. Le tableau ci-dessus n'est pas corrige pour cet effet : c'est la mesure brute produite par
l'outil, pas une reconstitution manuelle.
