# [Sprint s02] Retrospective - 2026-08-05

- **Anime par** : sofia-sm

## Ce qui a marche

- **Un trou de backlog, une fois vu, a ete comble dans la meme heure.** `camille-po` a rejoue
  l'application, constate l'absence d'identite visuelle, ecrit trois stories et `DEC-0006` en un
  seul commit (`5af6160`), et `ines-design` a livre une premiere version 25 minutes plus tard
  (`3be5a86`). L'observation en jeu a directement produit du backlog ecrit, pas seulement une
  remarque orale.
- **Le refus pedagogique a ete leve dans le meme sprint, sur la base d'un motif mesure et non
  d'une impression.** `nadia-prof` a chiffre son refus au pixel (surfaces, ecarts, cible tactile),
  `ines-design` a corrige exactement ce que le motif nommait, et `nadia-prof` a remesure avec le
  meme protocole pour rendre les deux etats comparables. Aucun aller-retour perdu sur un motif
  ambigu.
- **`nadia-prof` a verifie son propre garde-fou en le cassant, pas en le lisant.** Plutot que de se
  contenter du vert de `TailleDesTextesEtBoutonsTest`, elle a mute `.proposition` a 1,1 rem pour
  confirmer que la regle mord vraiment sur ce qu'elle est censee attraper, puis elle a ajoute un
  bouton sans classe pour chercher activement le trou suivant. C'est cette demarche qui a trouve le
  defaut le plus serieux du sprint (voir ci-dessous), pas une relecture.

## Ce qui a coince

### 1. Un sprint entier sans planning, declenche par une observation en jeu

**Aucun `planning.md` n'existe pour s02, et ce n'est pas un oubli de ma part a corriger
retrospectivement : le sprint n'a reellement pas eu cette ceremonie.** Le travail est parti d'un
constat fait en jouant l'application (`recreo.css` a 8 selecteurs, `/favicon.ico` en 404), ecrit
directement par `camille-po` en `DEC-0006` et trois stories, puis code par `ines-design` sans
qu'aucun objectif n'ait ete gele, sans affectation nominative formelle, et sans avis de faisabilite
ecrit de `theo-archi` avant le premier commit de code.

Rien dans ce sprint ne montre que cette absence a couté quelque chose de concret : les stories
etaient deja tres precises (reserves 6 et 7 de la fiche de conformite, reprises "telles quelles"
par `DEC-0006`), et `theo-archi` n'avait de toute facon aucun `ADR` a ouvrir pour du CSS et un
fichier d'icone statique. Mais le mecanisme qui aurait detecte un desaccord de perimetre avant que
le code ne soit ecrit - le planning - n'a pas tourné. Que ca n'ait pas coute cette fois ne garantit
rien pour la prochaine story qui, elle, aurait besoin d'un avis de faisabilite avant d'etre codee.

### 2. Le trou de backlog a survecu a la creation meme de l'agent cense le couvrir

`ines-design` a ete creee pendant s01 (amendement n 4 de la retro de s00) avec un mandat deja large
- "design d'interface et accessibilite visuelle (contraste, tailles de police et de signal,
semantique des formes, ergonomie pour un enfant de CP qui lit encore peu)". Elle n'a ete affectee
qu'aux deux stories qui existaient au backlog a ce moment (S1-6, S1-9). **Personne n'a demande, au
moment de sa creation, ce dont le design avait besoin au-dela de ces deux stories** : ni palette,
ni typographie, ni icone n'ont ete mis au backlog en meme temps que le mandat qui les couvrait.
Le trou a donc vecu tout un sprint (s01) avant d'etre vu en s02, alors que l'agent qui aurait pu le
combler existait deja. Ce n'est pas un manquement d'`ines-design`, qui a fait exactement ce qu'on
lui a demande - c'est un trou de process : creer un mandat large sans, dans le meme geste,
interroger ce mandat pour en tirer les stories qu'il implique.

### 3. Un garde-fou ecrit dans le meme commit que le code a ete vert en manquant precisement le defaut vise

`TailleDesTextesEtBoutonsTest` a ete corrige dans `fd16057` exactement comme la regle l'exige :
tests dans le meme commit que le code, et la correction repondait bien au defaut signale par
`nadia-prof` (liste de boutons ecrite en dur, `.proposition` absent). Le test est passe de vert-a-
tort a vert-a-raison sur ce point precis - `nadia-prof` l'a verifie en le cassant, la regle mord
bien sur `.proposition`.

Mais la nouvelle version derive la liste des boutons a controler depuis les **classes** des
elements rendus. Un bouton sans classe ne produit aucune entree : il est invisible pour la regle.
`nadia-prof` l'a demontre en ajoutant un `<button type="button">Rejouer</button>` sans classe dans
`fragments/retour.html` - les six tests sont restes verts. **C'est la forme exacte du defaut que ce
garde-fou existait pour empecher** : en s01, "Encore !" etait un `<button>` sans classe tombe au
defaut du navigateur, et aucun test ne l'avait vu venir.

Ecrire le test dans le meme commit que le code, comme `squad/README.md` l'exige depuis s00, est une
condition necessaire - mais ce sprint montre qu'elle n'est pas suffisante. Le test etait bien la, il
etait meme correctement corrige sur le defaut qui l'avait fait echouer une fois ; il restait un
angle mort que personne n'avait cherche a l'interieur du test lui-meme, jusqu'a ce que `nadia-prof`
le fasse en le cassant volontairement. **`ADR-0003` pose deja ce principe, mais seulement pour les
regles ArchUnit** ("toute regle ArchUnit qui garde une decision d'ADR est accompagnee d'un
contre-exemple, et un test verifie que la regle echoue sur ce contre-exemple" - "une regle sans
contre-exemple n'est pas consideree comme encodee"). `TailleDesTextesEtBoutonsTest` n'est pas une
regle ArchUnit, c'est un test JUnit ordinaire qui lit du CSS et du HTML rendu ; `ADR-0003` ne le
couvre donc pas au sens strict, et rien d'autre ne l'exigeait. C'est exactement le trou par lequel
ce defaut est passe deux fois.

### 4. Un outil de mesure qui suppose qu'un planning a toujours eu lieu

En consequence directe du point 1 : `python outils/rapport-tokens.py --sprint s02` echoue
("Sprint inconnu : s02", et en amont "ignore s02-identite-visuelle : pas d'horodatage de debut dans
planning.md"). Le script construit la fenetre temporelle d'un sprint exclusivement a partir de
l'horodatage de debut lu dans `planning.md` ; sans ce fichier, aucune fenetre n'existe, et aucun
`RAPPORT-tokens.md` ne peut etre produit pour s02. Ce n'est pas un bug du script - il fait
exactement ce que `squad/README.md` section 6 decrit - c'est un angle mort de conception qui ne
s'est jamais manifeste avant parce qu'aucun sprint precedent n'avait manque de planning. Je ne
fabrique pas de `planning.md` pour le faire fonctionner : voir amendement 4 ci-dessous.

## Amendements au process

| Section de squad/README.md | Changement | Raison |
|---|---|---|
| 9, etape 1 (Planning) | Quand un sprint demarre a partir d'une observation faite en jeu plutot que d'un planning formel, `sofia-sm` l'ecrit explicitement dans `review.md` (pas de `planning.md` fabrique apres coup) et route en retro la question de savoir si l'objectif aurait du etre gele et faisabilite-verifie avant le premier commit de code, plutot que de laisser l'absence de planning se remarquer seulement a la cloture. | s02 est le premier sprint sans planning du depot ; rien dans `squad/README.md` ne dit aujourd'hui quoi faire quand cette ceremonie n'a pas eu lieu, alors que la regle fondatrice interdit d'en fabriquer une a posteriori. |
| 1 (L'equipe) | Quand un mandat d'agent est etendu ou cree avec un perimetre plus large que les stories qui le motivent immediatement (comme le mandat d'`ines-design` en s01), le planning qui suit doit explicitement demander a cet agent ce que son mandat complet implique comme stories manquantes, plutot que de le laisser affecte aux seules stories deja ecrites. | Le mandat d'`ines-design` couvrait deja l'identite visuelle depuis sa creation en s01 ; le trou de backlog a quand meme survecu un sprint entier parce que personne n'a pose la question au moment de l'elargir. |
| 9, etape 2 (Developpement) et ADR-0003 | Un test qui verifie une propriete visuelle ou structurelle en derivant sa liste d'objets a controler depuis le code rendu (classes CSS, selecteurs, attributs) doit lui-meme etre mis a l'epreuve par un contre-exemple qui retire l'attribut dont depend la derivation - au meme titre que la regle d'`ADR-0003` pour ArchUnit, mais etendue explicitement aux tests JUnit qui jouent ce role de garde-fou derive. | `TailleDesTextesEtBoutonsTest` derive sa liste de boutons des classes CSS ; un bouton sans classe est un angle mort que la seule ecriture du test dans le meme commit que le code n'a pas empeche, et que seule une mise a l'epreuve explicite (ce que `nadia-prof` a fait a la main) a revele. |
| 6 (Le rapport de consommation de tokens) | `outils/rapport-tokens.py` doit pouvoir attribuer une fenetre a un sprint qui n'a pas eu de planning, par exemple en retombant sur l'horodatage du premier commit portant le slug du sprint quand `planning.md` n'existe pas, plutot que d'ignorer purement et simplement le sprint et de faire echouer `--sprint sNN`. | s02 n'a produit aucun `RAPPORT-tokens.md` faute d'horodatage de debut ; la consommation reelle de tokens du sprint n'est donc pas mesurable du tout, ce qui est pire que le defaut qu'un journal de tokens existe pour resoudre. |

## Decisions prises

Aucune decision prise pendant cette retro au sens d'un nouvel `ADR` ou d'une nouvelle `DEC`. Les
quatre amendements ci-dessus sont des propositions tracees pour le prochain planning ; ils ne
modifient pas `squad/README.md` par eux-memes, pour laisser la squad les discuter avant de les
acter.
