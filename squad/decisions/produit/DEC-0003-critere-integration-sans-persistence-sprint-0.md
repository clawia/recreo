# DEC-0003 - Le sprint 0 ne cree pas de story de persistence, le critere d'integration est reformule

- **Type** : story, portee
- **Date** : 2026-08-04
- **Auteur** : camille-po
- **Sprint** : s0
- **Competence programme visee** : transverse.
- **Statut** : Acceptee

## Contexte

`theo-archi` route un obstacle sur la story S0-1 (`avis-faisabilite.md`, section S0-1) : le
troisieme critere d'acceptation demandait que `mvn verify` couvre des "tests d'integration H2".
Or `ADR-0001-decoupage-en-modules-maven.md` ne cree pas `adapter-persistence` : aucune story du
sprint 0 ne demande de conserver quoi que ce soit entre deux parties (l'etat de la partie vit
dans la session HTTP, voir `avis-faisabilite.md` section S0-2), et un module sans usage reel
serait un module sans test - c'est nomme dans l'ADR comme la contre-mesure a la dette n°5 de
`main`.

Deux issues etaient possibles : reformuler le critere pour qu'il ne presuppose pas de
persistence, ou ajouter une story de durabilite et en assumer le cout. Theo recommande la
premiere et s'oppose techniquement a la seconde ; il precise que le perimetre appartient a
Camille.

## Decision

Je retiens la premiere issue. Aucune story de persistence n'est ajoutee au sprint 0. Le
troisieme critere d'acceptation de la story S0-1 est reformule dans `docs/backlog/backlog.md` :
"tests d'integration H2" devient "tests d'integration Spring Boot (contexte applicatif demarre,
parcours HTTP verifie de bout en bout)", ce que le sprint livre reellement.

Je ne cree pas de story de durabilite parce qu'aucun besoin utilisateur ne la justifie
aujourd'hui : aucune des cinq stories du sprint 0 ne demande qu'un enfant retrouve sa progression
apres avoir ferme le jeu. Introduire `adapter-persistence` sans un tel besoin creerait exactement
la situation que l'ADR signale comme une dette potentielle : un module ouvert par anticipation,
faiblement teste parce que rien ne l'utilise reellement.

**Engagement pour la suite**, deja pris par `theo-archi` dans son ADR ("Ce qui me ferait changer
d'avis") : la premiere story qui demandera explicitement de conserver la progression d'un enfant
entre deux sessions (reprendre une partie interrompue, un historique de score sur plusieurs
jours) declenchera la creation d'`adapter-persistence` dans le meme commit que cette story, avec
sa couche ajoutee a la regle ArchUnit dans le meme geste. Ce sera alors le moment ou "H2" entre
reellement en jeu.

## Contradiction tracee avec CLAUDE.md

`CLAUDE.md` mentionne H2 a deux endroits qui, lus au niveau du sprint 0, ne correspondent pas a
ce qui existe :

- section "Stack" : "Persistence : JPA + H2 (dev/test), PostgreSQL vise en prod" ;
- section "Regles cles", definition du Done technique : "Done technique = CI verte (mvn verify :
  compile + tests unitaires + integration H2 + ArchUnit)".

Les deux phrases decrivent une **cible du produit recreo dans son ensemble**, pas l'etat d'un
sprint donne - et `CLAUDE.md` renvoie lui-meme explicitement le decoupage exact des modules a
l'ADR de Theo ("Le decoupage exact en modules est une decision d'architecture"). Tant qu'aucune
story n'introduit `adapter-persistence`, "integration H2" n'a rien a verifier : il n'y a pas de
contradiction de fond, mais la formulation du Done technique dans `CLAUDE.md` est ecrite comme
si H2 existait des le premier sprint, ce qui a cause l'obstacle route ici. Je trace cet ecart
pour qu'il soit corrige : la phrase gagnerait a preciser "integration H2, des que
`adapter-persistence` existe" plutot que de citer H2 comme une brique presente en permanence.
Cette DEC ne modifie pas elle-meme `CLAUDE.md` ; la correction de formulation revient a qui
edite prochainement ce fichier.

## Justification pedagogique

Decision transverse, sans impact direct sur une competence du programme : elle porte sur le
perimetre d'un critere de test, pas sur un contenu ou une mecanique vue par l'enfant. Le jeu
reste jouable de bout en bout ; l'absence de persistence signifie seulement que le score ne
survit pas a un redemarrage du serveur pendant le sprint 0, ce qui n'affecte aucune des
competences visees par la matrice de couverture a ce stade.

## Impact

- `docs/backlog/backlog.md`, story S0-1 : troisieme critere d'acceptation reformule.
- `DEC-0001-perimetre-sprint-0.md` : annotee d'un renvoi vers cette DEC (le perimetre en quatre
  parties ne change pas, seul un critere de test est precise).
- `squad/programme/MATRICE-couverture.md` : aucune ligne affectee.
- Aucune nouvelle story ajoutee au sprint 0.
