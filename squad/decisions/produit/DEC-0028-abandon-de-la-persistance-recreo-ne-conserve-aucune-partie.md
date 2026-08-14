# DEC-0028 - Abandon de la persistance : recreo ne conserve aucune partie entre deux sessions

- **Type** : perimetre produit, tracabilite
- **Date** : 2026-08-14
- **Auteur** : camille-po
- **Sprint** : s12
- **Competence programme visee** : transverse - pas de contenu pedagogique.
- **Statut** : Acceptee

## Contexte

L'utilisatrice tranche le 2026-08-14, en ces termes : "je ne souhaite pas persister une partie, ce
n'est pas le but de recreo." Cette phrase ferme, de maniere definitive, une question que le depot
laissait ouverte au conditionnel depuis le sprint 0.

Le probleme que cette decision resout n'est pas un manque de story - il n'y en avait aucune, et ce
n'est pas ce que je viens ajouter au backlog. Le probleme est que **le depot annonce cette
persistance comme une cible du produit**, dans trois textes, lus ici mot pour mot avant d'ecrire
quoi que ce soit :

1. **`CLAUDE.md`, section "Stack"** : "Persistence : cible du produit, pas du sprint 0. JPA + H2 en
   dev et test, PostgreSQL en production, portee par un module `adapters/adapter-persistence` cree
   dans le meme commit que la premiere story qui a besoin de conserver une donnee entre deux
   sessions (voir `ADR-0001`, `DEC-0003`). Absente du code livre a ce jour." Et, section "Regles
   cles", definition du Done technique : "Technique = CI verte (`mvn verify` : compilation, tests
   unitaires, tests d'integration Spring Boot, ArchUnit, et integration H2 une fois
   `adapter-persistence` cree)".
2. **`ADR-0001-decoupage-en-modules-maven.md`** (`theo-archi`) : la decision elle-meme n'implemente
   pas la persistance ("`adapter-persistence` n'est pas cree. Aucune story du socle ne demande de
   durabilite"), mais sa section "Ce qui me ferait changer d'avis" pose un declencheur explicite et
   unique pour la creer : "Une story qui demande de conserver la progression d'un enfant entre deux
   sessions : je cree `adapter-persistence` et j'ajoute sa couche a la regle dans le meme commit."
3. **`DEC-0003-critere-integration-sans-persistence-sprint-0.md`** (moi-meme, s0) : ne cree aucune
   story de persistence au sprint 0, pour un motif explicitement temporaire ("aucune des cinq
   stories du sprint 0 ne demande qu'un enfant retrouve sa progression apres avoir ferme le jeu").
   Sa section "Engagement pour la suite" reprend le meme declencheur que l'ADR, avec deux exemples
   nommes : "reprendre une partie interrompue, un historique de score sur plusieurs jours."
   `DEC-0003` trace elle-meme un ecart de formulation dans `CLAUDE.md` ("la phrase gagnerait a
   preciser 'integration H2, des que `adapter-persistence` existe' plutot que de citer H2 comme une
   brique presente en permanence"), sans le corriger elle-meme.

**Verification faite avant d'ecrire cette decision** : aucun des trois textes ne motive la
persistance par autre chose que conserver la progression d'un enfant entre deux sessions
(reprendre une partie interrompue, un historique de score). Ni `ADR-0001` ni `DEC-0003` ne
mentionnent un autre usage possible (compte utilisateur, contenu edite par un adulte, etc.). La
decision de l'utilisatrice - "je ne souhaite pas persister une partie" - couvre donc **entierement**
le seul motif que ces trois textes anticipaient ; elle ne les contredit pas partiellement, elle
ferme la question qu'ils laissaient conditionnelle.

C'est exactement la dette n°1 de `CLAUDE.md`, section "Dettes de la version precedente" : "Une
abstraction centrale documentee partout mais jamais implementee... Soit une abstraction existe dans
le code, soit un `ADR` acte explicitement son abandon." Tant que cette decision n'est pas ecrite,
trois textes decrivent une brique du produit qui n'arrivera jamais.

## Decision

1. **Recreo ne persiste rien entre deux sessions.** Aucune progression, aucun score, aucune partie
   en cours ne survit a la fermeture de l'onglet ou du navigateur, ni a un redemarrage du serveur.
   Ce n'est pas un manque provisoire du sprint en cours : c'est un choix de perimetre assume pour la
   duree de vie du produit, sur decision explicite de l'utilisatrice du 2026-08-14.
2. **`adapter-persistence` ne sera pas cree pour ce motif.** Le declencheur ecrit par `theo-archi`
   dans `ADR-0001` et repris par moi dans `DEC-0003` ("une story qui demande de conserver la
   progression d'un enfant entre deux sessions") ne peut plus se produire pour ce motif precis :
   aucune story de ce genre ne sera plus ecrite par moi. Si le produit a un jour besoin d'ecrire
   quelque chose de durable (par exemple un compte adulte, ou un contenu edite hors du code), ce
   sera un motif distinct, a justifier de nouveau le moment venu - pas une reouverture de ce
   declencheur-ci.
3. **JPA, H2 et PostgreSQL cessent d'etre une cible du produit.** Ils ne sont abandonnes ni comme
   choix technique dans l'absolu (ce n'est pas ma decision) ni comme possibilite pour un besoin
   futur different : ils sont abandonnes **comme reponse au besoin qu'ils servaient jusqu'ici**,
   conserver une partie, qui n'existe plus.

## Ce qui doit changer, et qui n'est pas ecrit par moi

Je n'edite ni `ADR-0001` ni `DEC-0003` ni `CLAUDE.md` : ce sont respectivement le document de
`theo-archi`, un document dont la valeur historique tient a rester tel qu'ecrit au sprint 0, et un
fichier hors de mon perimetre de redaction technique. Je nomme ici precisement ce qui doit y etre
amende, pour que `sofia-sm` le route :

- **`CLAUDE.md`, section "Stack"** : le paragraphe "Persistence" doit cesser de la nommer "cible du
  produit". Il devrait dire, en substance, que recreo ne conserve aucune donnee entre deux sessions,
  par decision produit (`DEC-0028`), et non plus annoncer JPA/H2/PostgreSQL comme une brique a
  venir.
- **`CLAUDE.md`, section "Regles cles", definition du Done technique** : la clause "et integration
  H2 une fois `adapter-persistence` cree" doit etre retiree - elle decrit un evenement qui
  n'arrivera plus pour ce motif. Le Done technique redevient : "CI verte (`mvn verify` :
  compilation, tests unitaires, tests d'integration Spring Boot, ArchUnit)".
- **`ADR-0001`, section "Ce qui me ferait changer d'avis"** : le premier point ("Une story qui
  demande de conserver la progression d'un enfant entre deux sessions...") doit etre amende par
  `theo-archi` pour refleter que ce declencheur precis est ferme par decision produit, et non plus
  laisse ouvert comme une eventualite normale du backlog.

## Justification pedagogique

Decision transverse, sans impact sur une competence du programme : elle ne touche ni un contenu ni
une mecanique vue par l'enfant, seulement ce que le serveur retient apres la fermeture du
navigateur. Un enfant qui rejoue depuis le debut a chaque session n'est prive d'aucune competence
du referentiel ; `squad/programme/MATRICE-couverture.md` n'a aucune ligne a modifier.

## Impact

- `squad/programme/MATRICE-couverture.md` : aucune ligne affectee.
- `docs/backlog/backlog.md` : aucune story ajoutee ni retiree - il n'y en avait pas a retirer.
- `CLAUDE.md` et `ADR-0001` : amendements nommes ci-dessus, routes a `sofia-sm` / `theo-archi`, non
  executes par cette decision.
- `DEC-0003` : non modifiee ; son "Engagement pour la suite" doit desormais se lire a la lumiere de
  `DEC-0028`, qui le ferme pour le motif qu'il nommait.
