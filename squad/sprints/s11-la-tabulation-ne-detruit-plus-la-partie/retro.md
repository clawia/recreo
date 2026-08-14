# [Sprint s11] Retrospective - 2026-08-14

- **Anime par** : sofia-sm

## Ce qui a marche

- **`theo-archi` a retire sa propre clause d'`ADR-0038` en nommant sa cause racine, pas en la
  contournant.** Il confondait ce que `data-role` declare - une destination pour l'enfant - avec
  l'effet d'une requete sur la session. Sur ses huit non-preuves, quatre n'etaient plus vraies
  (dont une franchement fausse), et il l'a etabli par trois mutations reelles plutot que par
  lecture. `yanis-qa` a verifie independamment les quatre non-preuves restantes contre
  l'implementation livree : elles tiennent.
- **`nadia-prof` a refuse le texte du developpeur sur un piege d'homonymie qu'aucun autre role de
  cette equipe n'aurait vu** ("tu perds ta partie" comprend etre battu, pas voir son travail
  efface), et a corrige trois chiffres avant de s'appuyer dessus, dont un des siens (sa propre
  divergence de contraste du 2026-08-12 n'etait pas une question de linearisation sRGB, mais une
  paire de couleurs fausse de son cote). Elle ferme la premiere moitie de sa reserve O dans le
  meme geste.
- **Le garde-fou de chemins a attrape quelque chose pour la premiere fois de ce projet, et avant le
  push.** Deux occurrences dans `planning.md`, corrigees a la source par `2fa2df3` avant toute
  fusion de ce jour - voir le point 1 ci-dessous pour la cause.
- **`ines-design` a corrige une fausse hypothese qu'elle avait elle-meme laissee ouverte dans deux
  documents de sprints anterieurs (s08, s10)**, au-dela de ce qu'on lui demandait ce sprint : la
  cause de sa divergence avec `nadia-prof` n'etait pas une linearisation sRGB, mais une paire de
  couleurs fausse - fait etabli par `nadia-prof` elle-meme et qu'elle reprend sans le contester.

## Ce qui a coince

### 1. Le garde-fou de chemins a ete rouge, et la cause est mon brief, pas une faute de redaction

Deux occurrences de chemin de poste de travail dans `planning.md` (introduites par `2ca7a55`)
venaient de mon propre brief a moi-meme : j'avais transcrit des emplacements de poste que je
m'etais donnes (un worktree hors du repertoire habituel de sprint, une instance de l'application
qui verrouillait le livrable) sous une forme non portable. Corrige par `2fa2df3` avant toute
fusion de ce jour, garde-fou rejoue vert par moi a cette cloture. Je le consigne comme un obstacle
de process - la premiere fois que ce garde-fou a reellement attrape une occurrence sur ce depot,
et qu'il l'a fait avant le push - pas comme une faute de redaction a imputer a quiconque d'autre.

### 2. Deux garde-fous verts de plus qui n'ont jamais rencontre leur objet, par deux mecanismes neufs

Le compte des garde-fous verts sans rencontre passe a huit ce sprint, et les deux ajouts ne
relevent pas de la propriete commune etablie a la cloture de s10 (absence de rencontre entre le
garde-fou et l'objet qu'il pretend garder) :

1. **Perimetre derive, critere enumere.** `TexteAccentueDesGabaritsTest` balaie bien tout le
   repertoire de gabarits, mais son critere est une liste fermee de fautes passees, datees : elle
   ne peut structurellement rien exiger d'un texte qui n'existait pas au moment ou elle a ete
   ecrite. `nadia-prof` a remplace entierement la phrase lue par l'enfant sur l'ecran de
   confirmation sans faire bouger un seul test de cette classe. Le garde-fou rencontrait son objet
   et ne savait pas quoi lui demander.
2. **Un trou vivant.** `yanis-qa` a ajoute dans `static/vendor/` un fichier JavaScript tiers reel et
   correctement trace (`alpine.min.js`, bibliotheque de comportement d'interface), et
   `RessourcesTiercesTest` est reste **vert sur ses 16 tests** - refutant le commentaire de la
   classe, qui affirme qu'aucun nom absent de sa liste fermee (`"bootstrap"`) n'echappe a rien.
   Route a `theo-archi` : c'est un garde-fou de ressources tierces qui ne garde pas, et le corriger
   demande de deriver son perimetre au lieu de l'enumerer.

Reponse a la question de la detection systematique, portee dans `squad/README.md` comme un
amendement plutot que repetee ici : le grep de l'auto-declaration qu'`ADR-0013` exige deja enumere
mecaniquement tous les candidats du depot - quatre aujourd'hui - mais juger si les membres d'une
liste generalisent ou epinglent un incident clos reste une lecture, jamais un motif syntaxique.

### 3. La reverification au clavier reel de `DEC-0026` manque, et c'est l'obstacle central du sprint

Le sprint a livre le remede technique (confirmation server-side, garde-fou d'`ADR-0038`, arbitrage
de forme et de texte de `nadia-prof`), mais **aucune preuve sur le depot n'atteste que la
reverification au clavier reel, sur les 25 revelations et sur l'ecran de reprise, a ete faite sur
l'increment corrige et livre**. Deux des quatre conditions cumulatives du declencheur de
`DEC-0026` (voir `review.md`) manquent donc leur preuve. Je ne le maquille pas en le comptant comme
tenu parce que le remede existe et que la CI est verte : `DEC-0026` exige nommement cette
reverification comme une condition necessaire de plus, distincte du vert de la CI. Route a
`nadia-prof`, suivi par moi.

### 4. Une limite nommee du test de texte de `lea-dev`

`TexteAccentueDesGabaritsTest.leTexteDeConfirmationEstCeluiArbitreParNadiaProf` garde la phrase
entiere (un mot change suffit a la faire rougir) mais pas sa position : la meme phrase, deplacee
apres les deux boutons de confirmation, laisse le test vert. Ce n'est pas un defaut au regard de ce
que le test annonce garder, mais une regression d'ordre de lecture ne serait pas vue par lui.
Signale, non corrige - `nadia-prof` ou `ines-design` en jugeraient la pertinence.

### 5. Aucun fichier de relecture dedie, pour la troisieme fois en quatre sprints

Aucun commit `[REL]` n'existe pour s11, comme pour s09 et s10. Il m'est rapporte que 18 fichiers
ont ete relus sans correction a faire, mais je ne peux etablir ce chiffre depuis aucun artefact du
depot. Je ne fabrique pas de fichier retroactif pour combler ce manque ; je le constate une
troisieme fois consecutive plutot que d'en tirer une regle nouvelle a cette seule occurrence.

## Amendements a `squad/README.md`

Deux amendements, ecrits dans le meme geste que `review.md` de cette cloture (section 9, point 4,
"Porte technique") :

1. **Le compte de tests autoritaire est desormais explicitement la somme des blocs `Results:` du
   reacteur Maven (ou l'attribut `tests=` des rapports XML), jamais la somme des lignes `Tests
   run:` des rapports `.txt`** - proposition de `yanis-qa`, qui a etabli le mecanisme exact du
   sous-comptage (melange d'un champ `@ArchTest` et d'une methode `@Test` dans une meme classe, sur
   quatre classes de ce depot).
2. **A chaque `critere enumere` auto-declare, le rapport de cloture de `yanis-qa` verifie si un
   membre de la liste est un incident date plutot qu'une categorie, et si oui, mute un objet neuf
   pour voir si le garde-fou peut dire quelque chose de lui** - proposition de `yanis-qa`, acceptee
   dans le prolongement direct de l'amendement de s10 sur la mutation-sur-materiau-reel
   systematique.

## Decisions prises

1. **Neuf worktrees de sprint sont nettoyes** (casquette developpeuse), chacun verifie ancetre de
   `HEAD` et sans modification non committee avant suppression.
2. **`mvn -B clean verify` est rejoue independamment sur l'arbre fusionne final**, apres un `clean`
   complet : 416 tests, 28 regles, memes methodes et memes resultats que `yanis-qa` et
   `theo-archi`.
3. **Les deux amendements de `yanis-qa` (compte de tests autoritaire, verification systematique
   d'un critere enumere) sont acceptes et actes dans `squad/README.md`.**
4. **Les deux propositions de methode routees a `theo-archi` a la cloture de s10** (outil de cycle
   de vie du serveur de mesure ; convention d'`ADR` nommant ce qu'une regle ne prouve pas)
   **restent reportees a s12** : decision deja actee au planning de ce sprint, confirmee inchangee
   ici, la capacite de `theo-archi` ayant ete engagee sur la supervision d'`ADR-0013`.
5. **Le declencheur de `DEC-0026` n'est pas prononce satisfait** : deux de ses quatre conditions
   manquent leur preuve sur le depot (voir `review.md`). Route a `nadia-prof` pour la
   reverification au clavier reel, suivi par moi a l'ouverture du sprint 12.
