# [Sprint s00] Retrospective - 2026-08-04

- **Anime par** : sofia-sm

## Ce qui a marche

- **Le sequencement serie a evite les conflits d'arbre de travail.** Les trois etapes de code
  (theo-archi puis marc-dev puis lea-dev) ne se sont jamais chevauchees : chacune n'a demarre
  qu'une fois la precedente committee et compilable (`a3e530b`, puis `0c9c13f`, puis `424c402`).
  Aucun conflit de fusion, aucun `mvn verify` lance sur un module a moitie ecrit.
- **Les ADR ecrits avant le code ont servi de specification exploitable, pas de decoration.**
  `avis-faisabilite.md` annonce, avant le premier commit de code, les cinq modules, le paquet de
  la mecanique, le paquet du contenu combinatoire et l'absence de `DifficultyConfig` - et c'est
  exactement ce que livrent `a3e530b`, `0c9c13f` et `424c402`. Aucun ecart entre l'avis et le
  livre n'a ete constate a la review.
- **L'exigence de contre-exemple d'ADR-0003 a effectivement attrape des regles qui ne mordaient
  pas.** Ce n'est pas une garantie theorique : elle a trouve un vrai defaut, `leContratDeContenuNePorteQueDuTexte`
  (voir ci-dessous), au premier essai du contre-exemple qui aurait du la faire echouer.

## Ce qui a coince

Trois faits, decouverts a des moments differents du sprint, relevent du **meme mecanisme** : une
garantie annoncee dans un ADR ou un rapport, qu'aucun automate ne verifiait reellement.

1. **Les cinq tests de non-vacuite d'ADR-0003 passaient sur un paquet inexistant.** ArchUnit leve
   le meme `AssertionError` ("failed to check any classes") pour une regle dont l'ensemble source
   est vide que pour une regle qui mord reellement - un test de non-vacuite mal ecrit peut donc
   etre vert pour la mauvaise raison. Trouve et corrige par `theo-archi` (`0dbe146`), qui a du
   ajouter `hasMessageContaining` sur le nom pleinement qualifie de la classe fautive, pas
   seulement sur le type d'exception, pour distinguer les deux cas.
2. **`leContratDeContenuNePorteQueDuTexte`, presentee par ADR-0006 comme "celle qui mord
   vraiment", ne mordait sur aucune des deux formes reelles de la dette n°2 de `main`.** Les
   primitifs sont ranges dans `java.lang` par ArchUnit 1.5 (donc `int minOperand` passait) et
   `haveRawType` ignore les arguments generiques (donc `Set<Operation> operations` passait aussi).
   La regle interdisait un `java.time.LocalDate` et laissait passer exactement ce qu'elle devait
   interdire. Decouvert uniquement parce qu'`ADR-0003` exigeait d'ecrire le contre-exemple et de
   le voir echouer - une relecture ne l'avait pas vu.
3. **Sur 21 regles de garde, 5 seulement etaient contre-exemplifiees au premier commit du
   sprint** (`ef3fda6`), **et `yanis-qa` n'en avait recense que 7 sur les 16 manquantes** (2
   corrigees elle-meme pour `ADR-0001`, 5 signalees a `theo-archi` pour `ADR-0005`/`ADR-0006`/
   `ADR-0007`). Les 9 restantes - dont la purete de `application`, la regle de suffixe d'ADR-0003
   elle-meme, trois des sept regles d'ADR-0004 dont l'isolation entre mecaniques qui a menti 25
   sprints sur `main`, et la regle d'assertion web d'ADR-0008 - n'ont ete trouvees qu'a
   l'inventaire complet que `theo-archi` a produit lui-meme en fin de sprint (`0dbe146`), pas par
   la relecture ni par le rapport qualite.

**Cout de coordination reel** : trois etapes de code strictement en serie (theo-archi, puis
marc-dev, puis lea-dev), chacune ne pouvant demarrer qu'une fois la precedente committee et
compilable. Aucun parallelisme possible sur ce sprint : le meme arbre de travail et l'ordre de
dependance (squelette avant mecanique, mecanique avant contenu) l'imposaient.

**Fait de process, a ne pas omettre** : la squad n'etait pas invocable a l'ouverture du sprint 0.
La creation de la branche orpheline `round-two` avait vide `.claude/agents/`, desenregistrant les
8 agents avant que leurs definitions ne soient reprises. Consequence concrete : le sprint a du
etre coupe en deux sessions, avec une note de passation (`squad/sprints/s00-socle/ETAT-REPRISE.md`,
supprimee a cette cloture conformement a son propre en-tete) pour que la deuxieme session reprenne
sans reconstituer l'etat a la main. C'est un cout de process - la disponibilite de l'outillage
d'agents - pas un cout technique du produit.

## Amendements au process

| Section de squad/README.md | Changement | Raison |
|---|---|---|
| 9, etape 4 (Porte technique) | Exiger que la confirmation ArchUnit de `theo-archi` produise ou mette a jour, a chaque review, le tableau "regle par regle" (regle, ADR, contre-exemple, test de non-vacuite), pas seulement le resultat de `mvn verify`. | `theo-archi` a lui-meme tenu ce tableau dans `confirmation-archunit.md` ce sprint pour qu'il ne se perime pas ; sans cette exigence explicite, rien n'empeche qu'il disparaisse au sprint suivant et que les 9 regles non trouvees par le rapport qualite ce sprint restent non trouvees indefiniment. |
| 9, etape 1 (Planning) | L'avis de faisabilite de `theo-archi` doit distinguer explicitement, pour chaque critere d'acceptation d'une story, s'il est testable par un automate ou intrinsequement non testable (vocabulaire de CP, perception d'un signal visuel), plutot que de laisser cette distinction emerger seulement au rapport qualite. | `yanis-qa` a du le faire elle-meme, story par story, dans `rapport-qualite.md` ce sprint (S0-4, S0-5) ; le backlog actuel ne porte cette distinction pour aucune story, ce qui force chaque QA a la refaire de zero. |
| 9, etape 5 (Relecture) | Le perimetre de la relecture doit citer explicitement le texte destine a l'enfant (categorie B au sens de `DEC-0002`) parmi les livrables qu'elle controle, pas seulement "les livrables non executables" au sens large. | Aucune etape ne verifiait ce texte avant la porte pedagogique ce sprint ; c'est `nadia-prof` qui l'a trouve avec trois fautes d'accentuation, et `camille-po` vient de corriger le manque en amendant `DEC-0002` pour donner au relecteur un mandat explicite sur cette categorie - le README doit refleter ce mandat plutot que de laisser `DEC-0002` seule le porter. |

## Decisions prises

Aucune decision prise pendant cette retro. Les trois amendements ci-dessus sont des propositions
tracees pour le prochain planning ; ils ne modifient pas `squad/README.md` par eux-memes - ce
n'est ni un `ADR` ni une `DEC`, et je ne l'edite pas dans ce geste pour laisser la squad les
discuter au prochain planning avant de les acter.
