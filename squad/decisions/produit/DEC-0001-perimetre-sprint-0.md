# DEC-0001 - Perimetre du sprint 0 et choix de la premiere competence couverte

- **Type** : portee, priorisation
- **Date** : 2026-08-04
- **Auteur** : camille-po
- **Sprint** : s0
- **Competence programme visee** : mathematiques, CP (cycle 2), domaine "Nombres et calculs" -
  "Calcul mental - additions dans la limite de 10" (sous-partie de la ligne du referentiel qui
  couvre aussi "puis 20" - voir Impact).
- **Statut** : Acceptee

## Contexte

`round-two` est une reconstruction **from scratch** : le depot ne contient aucun code Java, et
aucune ligne de la matrice de couverture n'herite d'un statut de `main`. Le sprint 0 doit donc
livrer deux choses a la fois : un socle technique (squelette Maven multi-module, Clean
Architecture, CI verte) et une premiere mecanique de jeu **jouable de bout en bout avec un
contenu pedagogique reel**, faute de quoi le sprint 0 ne prouve rien - ni que le socle tient, ni
que le principe de decouplage mecanique/contenu fonctionne en pratique.

Deux questions se posaient : que met-on exactement dans ce sprint (perimetre), et par quelle
competence commence-t-on la couverture (choix produit, pas un choix ad hoc) ?

Le README du dossier `contenu-repris/` rappelle que ces fichiers sont une reserve de matiere
premiere, pas un cahier des charges, et que tout contenu repris doit repasser devant
`nadia-prof` comme un contenu neuf. Le choix de la premiere competence ne peut donc pas se faire
en fonction de ce qui est le plus facile a recopier depuis `contenu-repris/`, mais en fonction de
ce qui est le plus simple a **valider pedagogiquement** et le plus utile pour **prouver le
decouplage** sans le compromettre.

## Decision

Le sprint 0 livre exactement quatre choses, ni plus ni moins :

1. Le socle technique - squelette Maven multi-module en Clean Architecture, CI qui fait tourner
   `mvn verify`, et le garde-fou qui rejette tout commit sans prefixe d'agent connu (dette 7 de
   la version precedente, corrigee des ce sprint). Le detail technique (decoupage exact des
   modules, choix d'implementation) est de la responsabilite de `theo-archi`, pas de cette
   decision.
2. Une mecanique de jeu generique et reutilisable, le **bandit manchot** : une manche presente
   une question et un ensemble de propositions dont une seule est correcte, donne un retour
   immediat juste/faux, et enchaine sur plusieurs manches avec un score visible. Cette mecanique
   ne connait aucune matiere ni aucun contenu concret.
3. Un garde-fou automatique (verifie par `theo-archi`) qui rend le decouplage mecanique/contenu
   impossible a violer, pas seulement recommande.
4. Un premier contenu pedagogique reel injecte dans cette mecanique : **mathematiques, CP,
   calcul mental - additions dont les deux operandes et la somme sont compris entre 0 et 10.**

Aucune autre matiere, aucun autre niveau, aucune autre mecanique n'est ouverte pendant ce sprint.

## Justification pedagogique

Le choix de "additions CP jusqu'a 10" comme premiere competence repose sur trois criteres, pas
sur la facilite de recopier `contenu-repris/cp-calcul-mental.yml` :

- **Simplicite d'audit pedagogique.** Une addition dans la limite de 10 a une reponse binaire
  vraie/fausse, sans nuance linguistique, sans enonce ouvert a interpretation, sans image a
  juger. `nadia-prof` peut l'auditer completement des le premier increment, ce qui permet une
  porte pedagogique rapide et fiable - un prealable utile avant d'attaquer des competences plus
  ambigues (comprehension de texte, accords grammaticaux).
- **Genericite de la mecanique.** Le bandit manchot (tirage - choix parmi propositions - retour
  immediat) sert cette competence sans qu'aucune de ses regles internes ne mentionne
  "addition", "CP" ou "10" : le contenu est entierement injecte. C'est le test le plus simple
  possible du principe directeur du produit, avant de le confronter a un contenu plus complexe
  (une conjugaison, une carte de geographie) qui pourrait masquer une fuite du decouplage sous
  la pression du calendrier.
- **Position dans le referentiel.** C'est la premiere competence de calcul du referentiel maths
  CP, avant les competences de mesure ou de geometrie qui exigent un support visuel ou spatial
  que la mecanique bandit manchot (texte + choix) ne sait pas encore bien servir.

Ce choix ne prejuge pas d'une preference du produit pour les mathematiques sur les autres
matieres. Le calcul mental n'est qu'un point d'entree technique pour le sprint 0 ; c'est
`squad/programme/MATRICE-couverture.md`, pas cette decision, qui pilote la priorisation a partir
du sprint 1 - et elle signale deja que le francais n'a, apres ce sprint, encore aucune ligne
`en cours`.

## Impact

- `squad/programme/MATRICE-couverture.md` : la ligne mathematiques/CP/"Calcul mental - additions
  dans la limite de 10 (puis 20)" passe a `en cours`, jeu `bandit-manchot-additions-cp`,
  mecanique `bandit manchot`. Le "puis 20" reste explicitement hors perimetre : la ligne ne
  pourra passer a `couvert-valide` que quand les deux bornes seront couvertes et validees.
- `docs/backlog/backlog.md` : section "Sprint 0" detaillee avec 5 stories (S0-1 a S0-5).
- `squad/conformite/FICHE-bandit-manchot-additions-cp.md` : ouverte, bloc de validation laisse
  vide pour `nadia-prof`.
- `squad/validation/FILE-ATTENTE.md` : a alimenter par `camille-po`/`sofia-sm` a la review du
  sprint 0, en respectant la regle des 2-3 increments non valides pedagogiquement en attente.
- Aucun autre jeu, aucune autre matiere, aucun autre contenu de `contenu-repris/` n'est ouvert ni
  planifie par cette decision.

## Amendements

Deux obstacles routes par `theo-archi` apres son avis de faisabilite (`squad/sprints/s00-socle/
avis-faisabilite.md`) ont precise, sans changer, le perimetre en quatre parties pose ici :

- `DEC-0002-emoji-et-pictogrammes-contenu-enfant.md` : le critere d'acceptation de S0-5 est
  reformule (retrait du mot "icone").
- `DEC-0003-critere-integration-sans-persistence-sprint-0.md` : le critere d'acceptation de S0-1
  est reformule ("tests d'integration H2" devient "tests d'integration Spring Boot"), sans
  ajouter de story de persistence.
