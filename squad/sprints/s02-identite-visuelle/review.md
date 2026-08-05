# [Sprint s02] Review - 2026-08-05

- **Horodatage de debut** : aucun. **Ce sprint n'a pas eu de planning.** Voir l'anomalie
  ci-dessous ; elle n'est pas fabriquee retrospectivement.
- **Horodatage de fin** : 2026-08-05 17:34 (commit `df6b4e3`, dernier commit du sprint).
- **Duree effective** : non mesurable de la maniere habituelle (fin moins debut du planning),
  puisqu'aucun planning n'a eu lieu. Pour information seulement, l'ecart entre le premier commit
  du sprint (`5af6160`, 16:22:55) et le dernier (`df6b4e3`, 17:34:38) est de 1h12 : ce n'est pas
  une duree de ceremonie, c'est une mesure Git de repli, a lire comme telle.
- **Anime par** : sofia-sm

## Anomalie de methode - aucun planning n'a eu lieu

**Fait le plus important de ce sprint, a dire sans l'adoucir.** Il n'existe aucun
`squad/sprints/s02-identite-visuelle/planning.md`, et je n'en fabrique pas un a posteriori : un
fichier de ceremonie qui n'a pas eu lieu est un faux, exactement au meme titre qu'une ceremonie
sans fichier n'a pas eu lieu.

Ce qui s'est reellement passe : `camille-po` a rejoue l'application sur `round-two`, constate que
`recreo.css` ne declarait que 8 selecteurs et que l'application n'avait ni palette ni icone, et a
ouvert trois stories (S2-1, S2-2, S2-3) et `DEC-0006` en reaction directe a cette observation
(commit `5af6160`). Aucune ceremonie de planning n'a gele d'objectif, affecte nominativement, ni
recueilli d'avis de faisabilite de `theo-archi` avant que `ines-design` ne commence a coder
(`3be5a86`, 25 minutes apres `5af6160`).

Ce constat est traite comme il doit l'etre : en fait, ici, et developpe en amendement dans
`retro.md`. Il n'est pas gomme par le reste de cette review.

## Story par story

| Story | Livre | Verdict de nadia-prof |
|---|---|---|
| S2-1 - aucun texte ni bouton plus petit que la consigne | **Livre en deux temps.** Premiere version (`3be5a86`) : tous les selecteurs cibles passent a 1,25 rem ou plus, sauf `.proposition` (boutons de reponse), reste a 1,1 rem et non repris dans la palette. **REFUSE** sur ce commit (voir ci-dessous). Correction (`fd16057`) : `.proposition` passe a 1,25 rem, entre dans la palette, cible tactile >= 44 x 44 px, 12 px d'ecart non fusionnable entre boutons, `.retour-accueil` repasse en style discret (contour). **Refus leve** sur ce second commit (`df6b4e3`). | **REFUSE puis ACCORDE SOUS RESERVE** (refus conserve pour trace, non efface) |
| S2-2 - habillage general des ecrans nus | Palette de variables CSS nommees et reutilisees ; `h1`, `.jeux`, `.demarrer`, `.retour-accueil`, `.score`, `.fin-de-partie`, `.indice`, `.erreur`, `.pas-de-jeu`, `.message-reprise`, `.recommencer` portent tous une regle explicite ; aucune ligne de `recreo.js` touchee ; rien servi hors du fat jar. | **VALIDE SOUS RESERVE (accorde)**, non rouvert par le second passage |
| S2-3 - icone d'application | `/favicon.ico` repond 200, `image/x-icon`, 4201 octets, servi depuis les ressources statiques, referencee dans `accueil.html`. | **VALIDE SOUS RESERVE (accorde)**, non rouvert par le second passage |

### Le verdict de nadia-prof, repris tel quel, non adouci

Repris **integralement** depuis `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, points 2
et 2 bis. Je ne reinterprete pas ces mots, je les cite.

**Motif du refus initial de S2-1 (point 2)** : *"il y a plus grave que la taille : cet increment a
inverse la hierarchie visuelle dans le mauvais sens."* Mesure au pixel sur la meme manche, avant
(`5af6160`) et apres (`3be5a86`) :

| Element | Avant | Apres |
|---|---|---|
| Bouton de sortie "Les jeux" | 66 x 21 px, defaut navigateur | 115 x 47 px, bleu plein |
| Un bouton de reponse | 46 x 41 px, gris par defaut | 46 x 41 px, gris par defaut, inchange |

*"Avant ce sprint, le bouton de reponse etait l'objet cliquable le plus grand de l'ecran de jeu ;
la sortie etait le plus petit. Apres ce sprint, la sortie occupe pres de trois fois la surface d'un
bouton de reponse, elle est la seule zone coloree de l'ecran [...] Pour un enfant de CP qui ne
dechiffre pas encore les libelles et se guide a la saillance, l'element qui appelle la main est
desormais celui qui fait quitter le jeu. [...] C'est un recul pedagogique produit par cet
increment, pas une dette heritee, et c'est ce qui fait la difference entre une reserve et un
refus."*

**Motif de la levee du refus, apres remesure (point 2 bis)** :

| Ecran de jeu | Avant refus (`3be5a86`) | Apres correction (`fd16057`) |
|---|---|---|
| Un bouton de reponse | 46 x 41 px, gris par defaut | 836 x 47 px, bleu plein, gras |
| Ecart entre deux boutons de reponse | 8 px, marges fondues | 12 px, non fusionnables |
| Bouton de sortie "Les jeux" | 115 x 47 px, seule zone coloree | 114 x 46 px, contour sans aplat |

*"Ce que je voulais voir, je le vois [...] la sortie faisait 2,9 fois la surface d'un bouton de
reponse, elle en fait maintenant 0,13 fois. Un enfant de CP qui ne dechiffre pas les libelles et se
guide a la saillance est ramene vers la tache et non vers la porte."*

## Porte technique - ce qui est reellement verifie, et ce qui ne l'est pas

**Je ne prononce pas le Done technique de ce sprint au sens ou `CLAUDE.md` et `squad/README.md` le
definissent, parce qu'il n'a pas ete rendu par ceux qui en sont les garants.**

- **`yanis-qa` n'a produit aucun rapport qualite pour s02.** Aucun fichier
  `rapport-qualite.md` n'existe dans `squad/sprints/s02-identite-visuelle/`.
- **`theo-archi` n'a fourni aucune confirmation ArchUnit pour s02.** Aucun fichier
  `avis-architecture-cloture.md` ni `confirmation-archunit.md` n'existe pour ce sprint.
- **Ce qui existe reellement** : `ines-design` (`3be5a86`, `fd16057`) et `nadia-prof`
  (`4751874`, `df6b4e3`) rapportent chacune, dans leurs commits ou dans la fiche de conformite,
  avoir constate `mvn -B verify` vert au moment de leur travail - `df6b4e3` le dit explicitement
  ("Fichiers restaures, arbre propre, mvn -B verify vert"), et `nadia-prof` precise avoir audite
  sur JDK 21.0.10. Ce n'est ni un rapport qualite de `yanis-qa` ni une confirmation ArchUnit de
  `theo-archi` : c'est une observation de passage, faite par des agents dont ce n'est pas le
  mandat de porte technique.
- **Ce que j'ai verifie moi-meme a la cloture, casquette Scrum Master** : `mvn -B verify` relance
  depuis la racine sur JDK 21.0.10 (`JAVA_HOME` positionne pour la duree de la commande,
  jamais ecrit dans un fichier commite), a `HEAD` (`df6b4e3`). **BUILD SUCCESS, 132 tests, 0
  echec** (31 `domain`, 7 `application`, 38 `adapter-web`, 5 `adapter-content`, 49 + 2
  `bootstrap`). J'ai egalement rejoue les deux scripts de garde-fou de CI
  (`verifier-prefixes-test.sh`, `verifier-chemins-test.sh`) : tous les cas passent. Ceci confirme
  que l'arbre compile et que ses propres tests passent au moment de la cloture ; **ce n'est pas la
  porte technique** telle que definie par `squad/README.md` section 7 et section 9, etape 4 -
  cette porte exige un rapport qualite nomme et une confirmation ArchUnit nommee, produits par les
  agents qui en sont garants, pas une reverification de passage par un tiers.
- **Manque route** : la production d'un rapport qualite `yanis-qa` et d'une confirmation
  ArchUnit `theo-archi` pour ce sprint, avant que le Done technique de s02 puisse etre considere
  comme rendu au sens plein. Voir tableau des obstacles.

## Un garde-fou qui a laisse repasser precisement le defaut qu'il devait empecher

**Point le plus serieux de ce sprint.** `nadia-prof` a demontre, et non suppose, un defaut dans
`TailleDesTextesEtBoutonsTest` (`adapters/adapter-web/src/test/java/com/recreo/adapter/web/
TailleDesTextesEtBoutonsTest.java`). Depuis `fd16057`, la liste des boutons a controler n'est plus
ecrite en dur : elle est derivee des gabarits reellement rendus, par
`element.classNames()` sur chaque `<button>` et `<a href>`. C'est une vraie correction du defaut
que `nadia-prof` avait signale sur `3be5a86` (une liste fermee qui omettait `.proposition`).

Mais `classNames()` ne renvoie rien pour un element sans attribut `class`. `nadia-prof` a verifie
en le cassant : elle a ajoute `<button type="button">Rejouer</button>`, sans classe, dans
`fragments/retour.html`, relance la suite, et **les six tests sont restes verts**. C'est exactement
la forme du defaut de s01, ou "Encore !" etait un `<button type="submit">` sans classe et tombait
au defaut du navigateur (13 px) sans qu'aucun test ne le voie.

Je route ce point a `yanis-qa` et `theo-archi`, sans en decider la forme - `nadia-prof` elle-meme ne
la prescrit pas ("la regle devrait partir des boutons rendus et non de leurs classes, de sorte
qu'un bouton sans classe soit un echec et non un angle mort"). Ce n'est pas a moi de choisir entre
un identifiant synthetique par position, un attribut `data-*` obligatoire ou toute autre mecanique :
c'est une decision de test et d'architecture.

## Le trou d'audit sous ~500 px de large

`nadia-prof` le nomme elle-meme et ne le tait pas : *"Mon harnais de rendu impose une largeur de
mise en page minimale d'environ 500 px [...] Je ne me prononce donc pas sur le rendu en dessous
d'environ 500 px de large, alors qu'une tablette ou un telephone tenu debout est un support
plausible pour cette application."* Ce n'est ni un refus ni une reserve chiffree : c'est une zone
que sa porte pedagogique ne couvre pas, faute d'outillage. Je ne la classe pas comme close : ce
trou d'audit doit vivre quelque part plutot que d'etre tacitement considere comme couvert. Il est
route ci-dessous, a la fois comme question de perimetre produit (une tablette en portrait est-elle
un support vise ?) et comme question d'outillage (un harnais de rendu capable de moins de 500 px).

## Reserves nouvelles de nadia-prof, routees a camille-po

Cinq points, aucun bloquant, tous nommes par `nadia-prof` dans la fiche de conformite (point 2 bis)
pour que `camille-po` en fasse des stories :

1. Le chiffre d'un bouton de reponse occupe 20 px dans une barre de 836 px (2 % de l'objet), alors
   que c'est le nombre, pas la barre, qui distingue les trois propositions.
2. Les 12 px d'ecart entre deux boutons de reponse sont un plancher mesure, pas un confort ;
   `nadia-prof` voudrait au moins 1 rem.
3. Un appui rate (geste, pas calcul) est aujourd'hui compte comme une erreur de calcul - question
   produit, explicitement pas un defaut de S2-1.
4. Sur l'ecran de fin de partie, la sortie reste a la fois la seule action possible et le style le
   plus discret : la retrogradation de `.retour-accueil` est juste sur l'ecran de jeu (ou elle
   concurrencait la reponse), elle ne l'est pas sur l'ecran de fin (ou elle n'a plus rien a
   concurrencer).
5. L'icone S2-3 est accordee mais reste un monogramme "R" : `nadia-prof` demande un dessin d'objet
   lisible a 16 px pour un sprint suivant, plutot qu'une lettre qui demande justement la competence
   que l'enfant de CP est en train d'acquerir.

`DEC-0006` avait laisse ouverte la question d'une fiche de conformite distincte pour ce chantier
transverse. `nadia-prof` la tranche elle-meme (elle consigne dans la fiche existante,
`FICHE-bandit-manchot-additions-cp.md`, plutot que d'en ouvrir une nouvelle) et renvoie a
`camille-po` la seule partie qu'elle ne tranche pas : le jour ou un deuxieme jeu existera, la partie
"coquille d'application" de cet habillage n'aura plus de fiche naturelle ou vivre. Note, pas
decidee ici.

## Tableau des obstacles du sprint

| Description | Categorie | Agent route | Decision / artefact | Statut |
|---|---|---|---|---|
| Trou de backlog constate en jouant (aucune story d'identite visuelle, `recreo.css` a 8 selecteurs, aucune icone) | produit | camille-po | `DEC-0006`, trois stories S2-1/S2-2/S2-3 ecrites dans `docs/backlog/backlog.md` | clos |
| S2-1 refuse : hierarchie de saillance inversee sur l'ecran de jeu | pedagogie | nadia-prof, ines-design | refus (`4751874`) puis correction (`fd16057`), refus leve (`df6b4e3`), `FICHE-bandit-manchot-additions-cp.md` points 2 et 2 bis | clos |
| Aucun rapport qualite `yanis-qa` ni confirmation ArchUnit `theo-archi` pour s02 : la porte technique n'a pas ete rendue par ses garants | qualite / technique | yanis-qa, theo-archi | aucun fichier ne le tranche | ouvert |
| `TailleDesTextesEtBoutonsTest` derive la liste des boutons de leurs classes : un bouton sans classe est invisible a la regle, demontre par nadia-prof (meme forme que le defaut de s01) | qualite / technique | yanis-qa, theo-archi | aucun fichier ne le tranche | ouvert |
| Audit pedagogique impossible sous ~500 px de large avec le harnais actuel de nadia-prof, alors qu'un support tablette/telephone portrait est plausible | produit + qualite | camille-po (perimetre), yanis-qa / theo-archi (outillage) | aucun fichier ne le tranche | ouvert |
| Cinq reserves nouvelles de nadia-prof sur S2-1/S2-2/S2-3 (poids du chiffre, ecart entre boutons, appui rate compte comme erreur de calcul, retrogradation mal calibree sur l'ecran de fin, icone a redessiner en objet lisible a 16 px) | produit | camille-po | aucun fichier ne le tranche encore, a instruire en stories | ouvert |
| Question de process laissee ouverte par `DEC-0006` (fiche de conformite distincte pour un habillage transverse une fois un deuxieme jeu existant) | produit / process | camille-po | tranchee par nadia-prof pour ce sprint (reste dans la fiche existante) ; volet process pour l'avenir encore ouvert | ouvert |
| Aucun planning pour s02 : le sprint est parti d'une observation en jouant, pas d'une ceremonie | process | sofia-sm | constat ecrit ici, developpe en amendement dans `retro.md` | ouvert (amendement en discussion) |
| Le trou de backlog a survecu a la creation meme d'`ines-design` pendant s01, sans qu'on lui demande le perimetre complet de son mandat au-dela des deux stories qui l'avaient motivee | process | sofia-sm | constat ecrit ici, developpe en amendement dans `retro.md` | ouvert (amendement en discussion) |
| `outils/rapport-tokens.py --sprint s02` echoue ("Sprint inconnu : s02") : le script exige un horodatage de debut dans `planning.md`, absent puisqu'aucun planning n'a eu lieu | process / outillage | sofia-sm | constat ecrit ici, developpe en amendement dans `retro.md` | ouvert (amendement en discussion) |

Rappel : un obstacle n'est `clos` que si le fichier qui le tranche existe physiquement. Verifie
pour les deux lignes marquees `clos` ci-dessus : `DEC-0006`, `docs/backlog/backlog.md`,
`FICHE-bandit-manchot-additions-cp.md`, `4751874`, `fd16057` et `df6b4e3` existent tous sur le
disque et ont ete lus avant d'ecrire ce tableau.

## Porte pedagogique - nadia-prof

- **Verdict d'ensemble du chantier** : **ACCORDE**, apres correction. S2-1 **ACCORDE SOUS
  RESERVE** (refus initial leve, conserve pour trace) ; S2-2 **VALIDE SOUS RESERVE (accorde)** ;
  S2-3 **VALIDE SOUS RESERVE (accorde)**.
- **Fiche** : `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, points 1, 2, 2 bis, 3, 4, 5,
  6.
- **Audit joue** : oui, non degrade, deux fois (avant et apres correction de S2-1), sur JDK 21.0.10,
  fat jar lance sur le port 8099, neuf puis sept ecrans rasterises et mesures au pixel.
- **Ce que ce verdict ne change pas** : le Done pedagogique du jeu `bandit-manchot-additions-cp`,
  accorde en s01, **n'a a aucun moment ete retire ni suspendu** - ce verdict porte sur l'habillage,
  pas sur le contenu mathematique ni sur les libelles, reverifies intacts aux deux passages.
  `squad/programme/MATRICE-couverture.md` n'est pas modifiee : ces trois stories sont transverses,
  `DEC-0006` l'avait deja pose.

## Decisions prises pendant cette review

Aucune. Cette review reprend le verdict deja ecrit par `nadia-prof` dans la fiche de conformite et
route les obstacles qu'elle a nommes ; elle ne tranche rien qui n'y soit deja.
