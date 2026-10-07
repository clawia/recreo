# Retro - s53 - une base rouge poussee, trois engagements ajoutes apres le planning et jamais affectes

- **Date** : 2026-10-07.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : sprint de relecture de contenu seul. Une instance de `nadia-prof` (un passage), deux developpeurs sur
  leurs propres paquets, aucun branchement, aucune ligne de matrice, aucun Done pedagogique.
- **Sources** : `review.md` de ce dossier (obstacles, mesures), `planning.md`, `DEC-0112`, l'arbitrage de `nadia-prof`,
  `rapport-qualite.md`, `confirmation-archunit.md`, `relecture-cloture.md`, `RAPPORT-tokens.md`, `git log` et `git diff` sur
  `9d44d510..560b0fd2`. Je n'ai relu aucune transcription de sous-agent.

---

## 1. Ce qui a marche

**La lecture de testabilite a mis la base rouge sur la table avant les corrections decidees par l'arbitrage.** `lecture-testabilite.md`
est commite a 13:58:27 ; la premiere de ces corrections l'est a 14:27:13. `yanis-qa` a mesure `clean verify` sur la racine, trouve 2168
tests executes dont 5 echecs, et nomme les trois classes et les paquets. Il a aussi dit que C4 (suite de `adapter-content` et de
`bootstrap` verte) etait inatteignable a la base.

**Decision d'abord, edit ensuite, pour sept points sur neuf edites.** `ARBITRAGE-PREALABLE-s53-...` est commite a 14:20:46 ; les
edits de P1, P3, P4, P5, P6, P8 et P10 le sont entre 14:27:13 et 14:29:11 (`git log --no-merges --format='%h %ad' --date=format:%H:%M:%S`).
Les deux exceptions sont P2 et P7 (decisions d'auteure) : review section 1.

**Les chiffres annonces ont ete refaits, et l'ecart avec la base est explique.** 2174 tests : `yanis-qa` sur `54953faa`, `theo-archi`
sur `54953faa`, moi sur `560b0fd2` (rapports XML apres `clean verify`) : meme total, meme repartition par module. Les 2168 de la
lecture de testabilite sont un total surefire seul d'une construction arretee a `bootstrap` ; a perimetre egal, delta 0.

**Le relecteur a refait une mesure ecrite par trois documents et en a trouve une autre fausse.** "Six fichiers" a espace ordinaire
dans un `enonce` : un seul (`cm1-grands-nombres`, 14 lignes). En-tete `FER-3` de `cp-qlm-situer-la-france` : les numeros d'items
etaient faux. Bloc de P2 de l'arbitrage : espaces insecables en tete qui empechaient de le coller tel quel.

**Une seule instance de `nadia-prof`, un seul passage, `PAR-9` non declenchee.** Son arbitrage chiffre le passage a 0,5 (section 14),
la ou le planning proposait 0,0 : elle a corrige le prix qu'on lui proposait.

**Aucun garde-fou n'a ete assoupli pour verdir la base.** `rapport-qualite.md` section 3 : 5 echecs sur `5c1353a9`, 0 sur
`54953faa`, par correction de contenu ; le seul test touche entre les deux arbres ne change que le texte d'un motif.

## 2. Ce qui a coince

**La base de s53 etait rouge a l'ouverture : 5 echecs de `bootstrap`.** Faits lus dans les fichiers : trois garde-fous de
`bootstrap` derivent leur population du repertoire de contenu et jugent donc un paquet non branche (`ADR-0013` obligation 1) ;
`70b1cdc1` a declare les 45 paquets dans quatre tests d'`adapter-content` et dans aucun de `bootstrap`. **Cause rapportee par
l'agent lanceur de cette tache, non prouvee par un journal du depot** : la session de contenu du 2026-10-07 a ete poussee apres un
`mvn` limite a `adapter-content`, qui ne contient pas ces garde-fous. Cout : une story (`S53-REL-10`) et trois commits de contenu qui
ne figuraient dans aucun planning, sur quatre paquets que ne vise aucun des onze points (sauf l'item 7 de `vocabulaire-de-position`).

**Le tronc deja pousse est rouge cote `bootstrap`.** `origin/round-two` est `76a50e8b` ; `git diff --name-only 76a50e8b 5c1353a9` rend
`planning.md` seul, donc les sources du tronc sont celles de la base mesuree rouge. Je ne l'ai pas mesure directement. **Seul le push
de s53 le corrige** ; je n'ai rien pousse.

**Trois engagements sont entres dans le sprint par une `DEC` posterieure au planning, et personne ne les a livres.** Le planning date
de 12:18, `DEC-0112` de 14:03. `DEC-0112` section 5 engage `CRP-CM1-R1` a `R3`, `PAR-4` et le journal de tokens, et sa section 9 me
demande de les reintegrer au planning et de les affecter. `git log -- planning.md` ne rend aucun amendement a cet effet. Ils
n'avaient donc ni affectation nominative ni ligne dans la verification poste/story de `PAR-16` (`squad/README.md` section 4) : un
engagement qui n'est pas dans cette table n'est pas porte. Le perimetre que l'utilisatrice a demande etait P1 a P11 ; ces trois
postes l'excedent, et leur statut est **non livre, arbitrage de l'utilisatrice en attente**.

**`S53-REL-10` a ete absorbee sans acte.** Trois commits portent son nom ; ni le planning, ni le backlog, ni `DEC-0112` ne la
portent. La regle de ce depot est qu'une story absorbee en cours de sprint s'acte par un amendement a la decision d'origine, dans le
meme geste que sa fusion. Il n'existe pas.

**Quatre chiffres ecrits dans des documents de s53 se sont reveles faux ou contradictoires.** "0 fichier" a espace insecable dans
`competence` (4 fichiers dans trois autres documents), "six fichiers" (un), "onze paquets plus anciens" (neuf), "51 paquets" (45). La
famille est celle que `CLAUDE.md` nomme : un chiffre annonce se reverifie, il ne se relaie pas. Le "51" est dans le sujet d'un
commit pousse, qui ne sera pas reecrit. Deux autres formes sont a corriger dans les documents : "Relaye de l'utilisatrice" n'est
pas une des trois formes de `DEC-0062`.

**Le journal de tokens n'a toujours pas de proprietaire.** La regeneration de `squad/rapports/JOURNAL-tokens.md` remplace des
sections mesurees par *"Aucune consommation mesuree"* : dans la regeneration que j'ai obtenue, les sections de s23, s24, s25, s26 et s27 la
portent chacune une fois, alors que le journal commite leur donne 13 lignes de chiffres chacune. Je l'ai restaure
par `git checkout -- squad/rapports/JOURNAL-tokens.md`, comme a s52 (decision de l'utilisatrice du 2026-10-06 de ne pas commiter le
journal regenere), et conserve la regeneration hors depot. **La section de s53 est donc absente du journal commite**, comme celles de
s51 et de s52. `RAPPORT-tokens.md` est lance avec `--sprint s53` explicite : `git status --short` apres l'execution ne montre que
le journal (restaure) et le `RAPPORT-tokens.md` de s53 ; aucun `RAPPORT-tokens.md` de s22 a s27 n'a bouge.

## 3. La lecon du push : le job a lancer avant un push de contenu

**Avant tout `git push`, y compris pour du contenu seul : `bash outils/mvn-jdk21.sh -B clean verify` sur la racine du depot, sans
`-pl`.** C'est la commande du job `build` de la CI. `mvn -pl adapters/adapter-content test` ne charge pas `bootstrap` : il ne voit ni
`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`, ni `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest`, ni
`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`, qui jugent tout paquet present dans `contenu/`, branche ou non. Ecrire un
paquet, c'est donc passer ces trois classes, pas seulement les garde-fous du module ou on ecrit. Une mesure partielle nomme son
perimetre (`CLAUDE.md`, exigence `-am`) ; elle ne tient jamais lieu de la porte technique, et la porte technique nomme les jobs de
`ci.yml` qu'elle a lances. C'est la famille de defaut que l'amendement du 2026-09-08 de `CLAUDE.md` decrit : annoncer la porte verte sur la foi d'une mesure
partielle.

## 4. Actions, routees et non tranchees

| # | Action | Route a | Artefact attendu |
|---|---|---|---|
| A1 | Sort des trois postes non livres (`CRP-CM1-R1` a `R3`, `PAR-4`, journal de tokens) : engager avec proprietaire, ou retirer avec motif | utilisatrice, `camille-po` | `DEC` d'ouverture de s54 |
| A2 | Pousser s53 : le tronc distant est rouge cote `bootstrap` | utilisatrice | push |
| A3 | Amender `DEC-0112` : acter `S53-REL-10` ; corriger "0 fichier" (R1) et la provenance "Relaye de l'utilisatrice" (R2) | `camille-po` | amendement |
| A4 | Corriger "six fichiers" de la section 11 de l'arbitrage (R3) | `nadia-prof` | arbitrage corrige |
| A5 | Poser l'erratum de "onze paquets plus anciens" au backlog (R5) | `camille-po` | note au backlog |
| A6 | Corriger les 14 `enonce` de `cm1-grands-nombres` et les deux constantes `PREFIXE_ECRIRE` et `PREFIXE_LIRE` dans un meme commit (R4) | `theo-archi` ou developpeur du test | story `S53-REPORT-GRANDS-NOMBRES`, backlog de s54 |
| A7 | Donner un proprietaire au journal de tokens, et decider s'il couvre aussi `RAPPORT-tokens.md` | `camille-po`, `theo-archi` | `DEC` de s54 |
| A8 | Ecrire dans `squad/README.md` la regle du push (section 3 de cette retro) | `theo-archi` | amendement du README |
| A9 | Corriger la forme de provenance de `relecture-p11.md` lignes 7 a 9 (R2) | `relecteur-pedagogique` | fichier corrige |
| A10 | Corriger la mesure "0 sur 182" de la lecture de testabilite (R1) | `yanis-qa` | note ou fichier corrige |
| A11 | Actions de s52 non traitees par s53 : A1, A2, A4, A6, A7, A8 (preuve : `review.md` obstacle 15) | leurs proprietaires | voir la retro de s52 |

## 5. Amendements au process

**Aucun amendement n'est ecrit par moi dans `squad/README.md`.** Deux propositions sont justifiees par un fait mesure de s53 ; je les
demande, je ne les ecris pas.

| Section de `squad/README.md` | Proposition | Fait mesure | Demandee a |
|---|---|---|---|
| 9 (porte technique, avant le push) | Ecrire la commande de la section 3 de cette retro comme geste obligatoire avant un push, contenu seul compris | base rouge de s53, 5 echecs, 45 paquets pousses | `theo-archi` |
| 4 (`PAR-16`) et 9 | Un engagement ajoute par une `DEC` apres le planning s'ecrit dans un amendement du planning, avec affectation nominative, avant l'ouverture du premier worktree concerne | trois postes de `DEC-0112` sans affectation, non livres | `camille-po`, `sofia-sm` |

## 6. Une ligne sur les trailers

Le constat transmis est que certains commits sont signes "Claude Opus 5.5" au lieu de "Claude Sonnet 5.5", sans effet de CI (la CI ne
lit que le sujet, `squad/README.md` section 8). **Mesure** (`git log --no-merges --format='%h|%s|%(trailers:key=Co-Authored-By,valueonly)'`
puis `--all --since=2026-10-07`) : sur `9d44d510..560b0fd2`, les seuls trailers "Opus 5.5" sont sur 3 commits `[NAD]` (1) et `[THEO]` (2),
agents que `RAPPORT-tokens.md` place sur `claude-opus-5-5` ; tous les autres commits d'agent sont "Sonnet 5.5". Sur toutes les branches
locales depuis le 2026-10-07, tout trailer "Opus 5.5" est aussi sur un commit `[NAD]` ou `[THEO]`. **Zero ecart** avec la
regle de `DEC-0110` 17.4 sur les commits que je vois. Je ne retrouve donc pas les commits signes a tort ; s'ils existent, ils ne sont
pas dans les branches locales. Un trailer errone n'est pas reecrit (`DEC-0110` 17.4).

## Decisions prises

**Aucune `DEC` ni `ADR` n'est creee ou amendee par cette retro.**
