# DEC-0037 - Perimetre du sprint 16 : fermeture des dettes de l'en-tete et des deux defauts trouves par l'utilisatrice, zero ouverture de contenu, troisieme sprint de stabilisation consecutif nomme comme tel

- **Type** : perimetre de sprint
- **Date** : 2026-08-19
- **Auteur** : camille-po
- **Sprint** : s16
- **Competence programme visee** : transverse pour l'integralite de ce document. Aucune des six
  stories qu'il ouvre ne cible une ligne de `squad/programme/MATRICE-couverture.md` ; ce fait est
  lui-meme l'objet de la section "Couverture programme" ci-dessous.
- **Statut** : Proposee - ce document propose un perimetre, il ne le gele pas. L'objectif du
  sprint et l'affectation restent la main de `sofia-sm` au planning, apres l'avis de faisabilite
  de `theo-archi` sur chacune des stories.

## Contexte

Je verifie ici, poste par poste, ce qui a ete rassemble depuis les clotures de s14 et de s15,
plutot que de le reprendre sur parole - plusieurs references de ce type se sont deja revelees
perimees. Sources verifiees et citees a chaque point.

## Decision

### 1. Ce qui entre

**Les deux defauts de l'utilisatrice** (deja ecrits ce jour) :
- `S16-1` - le message de partie interrompue ne s'affiche que pour une interruption plausible
  (`DEC-0036`).
- `S16-2` - l'adresse affichee correspond au catalogue complet apres un retour depuis "Les jeux".

**Le residu chiffre d'`ADR-0046`, arbitrage de forme deja rendu par `nadia-prof` le 2026-08-18**
(`squad/conformite/FICHE-barre-entree-niveau.md`, section 4 ; `squad/sprints/s15-cinq-arbitrages-
de-l-en-tete/RAPPORT.md`, ligne "Trou du selecteur compose - implementation de la forme neutre",
encore ouverte) :
- `S16-3` - une puce de niveau active garde son apparence sous le pointeur.
- `S16-4` - une proposition deja essayee garde son apparence sous le pointeur.

Aucune des deux ne rouvre un arbitrage : `nadia-prof` a deja tranche la forme (variante neutre,
conditions S15-8 et S15-9), et `ADR-0046` a deja pose les quatre exigences que le lot doit tenir
("Ce que j'exige du lot qui corrigera", point 4 : "la forme neutre [...] est la seule que le lot
peut ecrire sans rien demander"). Rien ne bloque leur developpement avant code.

**Le trou de garde-fou nomme par `nadia-prof` le 2026-08-18, a fermer avant que le premier
libelle accentue issu du modele ne passe par ce chemin** (`FICHE-barre-entree-niveau.md`, section
5, citation : *"Ce trou doit etre ferme avant que le premier libelle accentue ne passe par la, pas
apres"*) :
- `S16-5` - tout libelle rendu par le modele est soumis au meme garde-fou d'accentuation que le
  texte ecrit en clair dans les gabarits.

**La reserve S15-D, non bloquante, candidate de story nommee par `sofia-sm` elle-meme**
(`RAPPORT.md` de s15, ligne "Reserve S15-D") :
- `S16-6` - la phrase qui nomme le filtre en cours apparait aussi apres un changement de niveau
  confirme pendant une partie.

### 2. Ce qui sort, differe ou reste hors de mon mandat, et pourquoi

**Le filtre par matiere - second report, je le compte comme tel.** `DEC-0032` l'a differe une
premiere fois de s14 a s15, sous deux conditions explicites. Aucune des deux n'est levee a la
cloture de s15 :
- **la nomenclature affichable** : `S16-5` ferme le trou de garde-fou, mais ne tranche pas la
  question d'architecture posee par `DEC-0032` point 2 (valeur interne elle-meme accentuee, ou
  libelle d'affichage distinct) - question routee a `theo-archi` depuis le 2026-08-17, toujours
  sans reponse ecrite trouvee dans le depot ;
- **R-BARRE-3 / S14-B**, datee par `nadia-prof` elle-meme le 2026-08-17 : *"le jour ou un second
  axe de filtre s'ajoute [...] c'est a ce moment-la que je la poserai en condition"*
  (`FICHE-barre-entree-niveau.md`, section 4). Aucune proposition d'`ines-design` n'existe pour
  faire lire a l'enfant deux filtres combines ; c'est un prealable a instruire, pas un geste de ce
  sprint.

Le filtre par matiere **ne fait pas partie du perimetre de s16**. C'est le second report sur ce
sujet ; le premier est `DEC-0032` (2026-08-17, s14). Je ne le referme pas sur un motif nouveau :
les deux motifs de `DEC-0032` tiennent encore, l'un partiellement adresse par `S16-5`, l'autre
intact.

**R-BARRE-1 (grands nombres CM1, +22 px de pli depuis s14).** Story candidate deja nommee
(`RAPPORT.md` de s15), mais sur un jeu et un ecran sans rapport avec le chantier de l'en-tete de
s14/s15/s16. Je ne l'ouvre pas ce sprint : rien ne l'a aggravee depuis sa derniere mesure, et
l'ajouter melangerait deux chantiers dans un sprint deja charge de dettes. Elle reste au backlog
candidat.

**Les trois documents manquants de la cloture de s15** (`RAPPORT.md`, "aucun rapport qualite
(`yanis-qa`), aucune confirmation ArchUnit motivee (`theo-archi`), aucune relecture
(`relecteur-pedagogique`) n'existent pour ce sprint") : ce ne sont pas des stories de backlog,
elles ne portent aucun besoin d'enfant. Je les route directement, ci-dessous, aux trois agents
qui les doivent.

**Les dettes techniques nommees et non affectees** (garde-fou de provenance des classes amont
`-am`, migration du marqueur d'ecran de jeu vers un attribut, predicat `estRemplacant` d'`ADR-0042`
sans declencheur atteint) : je ne les tranche pas, je ne leur invente pas de story. Elles
appartiennent a `theo-archi` et n'ont, a ce jour, ni prealable pedagogique ni besoin d'enfant a
formuler - ce sont des questions d'architecture et de dette de production, hors de mon mandat.
Elles doivent apparaitre dans le perimetre que `sofia-sm` gele, ou en etre explicitement exclues
par elle ; je ne les geles pas moi-meme.

**La capacite de mesure de rendu - je la nomme, je ne la tranche pas.** `nadia-prof` a ecrit
quatre fois en deux jours qu'elle est la seule de la squad a pouvoir produire un chiffre de rendu
sur ce poste, et `theo-archi` a mesure qu'aucun outil du depot ne pilote un navigateur reel :
`outils/mesurer-rendu.sh` rasterise un etat par defaut, sans parametre d'element ni d'evenement -
personne ne peut simuler un survol (`ADR-0046`, section "Ce que la squad n'a pas vu, et
pourquoi"). Les quatre defauts trouves ces deux derniers jours l'ont tous ete par l'utilisatrice en
jouant, aucun par l'outillage de la squad. Ce n'est pas une question de contenu ni une story que je
peux ecrire avec un besoin d'enfant : c'est une question de capacite d'equipe. Je la nomme a
`sofia-sm` pour qu'elle decide si l'investissement (piloter un navigateur reel, meme en mode
minimal) entre dans ce sprint ou reste hors de portee ; je n'en fais pas une condition de mon
perimetre.

**Une reference perimee trouvee en verifiant, signalee plutot que corrigee par moi.**
`CLAUDE.md` cite *"ADR-0045, section 'Prerequis de mesure'"* comme le lieu ou le chiffre de l'ecart
`-am` du 2026-08-17 est porte. J'ai lu `ADR-0045` en entier : c'est une decision sur la coherence
interne d'un garde-fou de catalogue, elle ne contient aucune section "Prerequis de mesure" et ne
parle jamais de `-am`. La mesure existe reellement, mais ailleurs :
`squad/sprints/s14-points-d-entree-par-niveau/avis-faisabilite.md`, section 12, *"Le prerequis
`-am`, remonte dans CLAUDE.md - et pourquoi une phrase ne suffira pas"*. Je ne corrige pas
`CLAUDE.md` moi-meme (redaction technique, hors de mon mandat) ; je le signale a `theo-archi`, qui
a ecrit les deux textes, pour qu'il rectifie la reference le jour ou il ouvre le mecanisme du
garde-fou `-am` lui-meme.

### 3. Plafond d'ouvertures de contenu neuf : 0

**Aucune competence de `squad/programme/MATRICE-couverture.md` n'ouvre a s16.** Je le decide, je ne
le subis pas, et je le motive plutot que de le laisser filer une troisieme fois en silence.

**Le compte, nomme explicitement.** s14 et s15 n'ont ouvert ni ferme aucune ligne
(`RAPPORT.md` de s15, section "Couverture programme" : *"aucune ligne [...] n'est ouverte ni fermee
par ce sprint"* ; meme constat verifie sur `squad/programme/MATRICE-couverture.md`, dont la
derniere section de synthese date du sprint 13, 2026-08-16). **s16, tel que je le propose ici, sera
le troisieme sprint consecutif sans mouvement de couverture.**

**Pourquoi je le decide ainsi plutot que d'ouvrir une ligne en parallele.** Les six stories de ce
perimetre sont toutes des consequences directes de s14 et s15 : deux defauts trouves par
l'utilisatrice sur du code livre, deux residus d'un arbitrage de forme deja rendu, un trou de
garde-fou nomme par `nadia-prof` elle-meme, une reserve non bloquante deja candidate. C'est
exactement le motif que j'ai deja ecrit dans `DEC-0030` : *"un increment techniquement simple peut
consommer autant de temps sur ses defauts que sur son contenu"*. Ouvrir une ligne de contenu neuf
dans le meme sprint reproduirait le risque que cette phrase nommait, sur la meme capacite de
developpement qui doit deja fermer six dettes et trois documents de cloture.

**Ce que je n'en conclus pas.** Ce n'est pas une pause de la couverture programme decidee pour
elle-meme, et ce n'est pas un troisieme report de `DEC-0007` au meme sens que `DEC-0007` avait
lui-meme nomme trois reports consecutifs sans proprietaire : ici chaque sprint a un motif ecrit et
distinct (s14 : demande de l'utilisatrice sur les points d'entree ; s15 : ses cinq arbitrages en
rejouant ; s16 : les consequences directes des deux premiers). Mais un troisieme sprint sans
mouvement, meme motive, est un fait que je ne veux pas voir devenir un quatrieme par defaut. **Je
pose donc une limite explicite** : si s17 ne rouvre pas au moins une ligne de
`squad/programme/MATRICE-couverture.md`, ce sera a `sofia-sm` et a moi de l'ecrire comme un
changement de direction du produit, pas comme une continuation silencieuse.

### 4. Couverture programme - etat reel

**Inchangee depuis le sprint 13** : 23 lignes `couvert-valide`, 5 `en cours`, 46 `non couvert`, sur
74 lignes de competence au total. Aucune des six stories de ce perimetre ne cible une competence
(section 1), donc, conformement a la regle de mise a jour de la matrice elle-meme
(`squad/programme/MATRICE-couverture.md`, section "Qui la met a jour, et quand" : mise a jour "au
planning, quand une story nouvelle ou modifiee cible une competence"), **je ne modifie pas la
matrice ce sprint** - il n'y a rien a y ecrire tant qu'aucune ligne n'est ciblee.

## Ce que je route

- **`theo-archi`** : avis de faisabilite sur `S16-1` a `S16-6` ; le mecanisme des points 1 et 2 de
  la section "Ce qui entre" ; la question d'architecture encore ouverte de `DEC-0032` point 2
  (representation de la nomenclature de matiere, avant tout filtre par matiere) ; la correction de
  la reference perimee de `CLAUDE.md` vers `ADR-0045` ; les dettes non affectees nommees en
  section 2 (`-am`, migration du marqueur, `estRemplacant`), a faire entrer ou exclure
  explicitement du perimetre que `sofia-sm` gelera.
- **`sofia-sm`** : affectation et gel de l'objectif ; decision de capacite sur l'outillage de
  mesure de rendu (survol, resolution de navigateur) ; verification que les trois documents de
  cloture de s15 sont produits.
- **`yanis-qa`** : rapport qualite de s15, encore absent.
- **`theo-archi`** (second chapeau) : confirmation ArchUnit motivee de s15, encore absente.
- **`relecteur-pedagogique`** : relecture des livrables textuels de s15, encore absente.
- **`nadia-prof`** : aucun arbitrage prealable requis pour `S16-1` a `S16-6` (voir section
  suivante) ; ses Done pedagogiques restent dus a la review de chaque story, nommement sur
  `S16-1`, `S16-2` et `S16-6` (rendu change) et, si elle le juge necessaire, sur `S16-3` a `S16-5`
  (garde-fous et etats neutres, aucun rendu ne change par construction).

## Ce qui doit etre arbitre avant tout code

**Rien, pour les six stories de ce perimetre.** Chacune part d'un arbitrage deja rendu ou d'un
comportement deja specifie :
- `S16-1` : texte deja valide (`DEC-0004`, `FICHE-bandit-manchot-additions-cp.md`), seule la regle
  de declenchement change (`DEC-0036`).
- `S16-2` : aucun mot ne change, le comportement de confirmation existant n'est pas retire.
- `S16-3`, `S16-4` : forme deja arbitree par `nadia-prof` le 2026-08-18 (variante neutre).
- `S16-5` : garde-fou pur, aucun rendu ne change.
- `S16-6` : application de la meme phrase que `S16-2`/`S14-1` a une route ou elle manque encore.

**Ce qui reste bloque, et qui n'entre pas dans ce perimetre** : le filtre par matiere, tant que la
question d'architecture de `DEC-0032` point 2 n'est pas tranchee par `theo-archi` et que
`R-BARRE-3` / `S14-B` n'a pas recu de proposition d'`ines-design` suivie d'un arbitrage prealable de
`nadia-prof`, exactement sur le modele qui a deja gouverne `S14-1` et `S6-9`/`DEC-0027`.

## Impact

- `docs/backlog/backlog.md`, sprint 16 : stories `S16-3` a `S16-6` ajoutees, avec un lien vers
  cette decision.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0037` ajoutee.
- `squad/programme/MATRICE-couverture.md` : **inchangee**, motif ecrit ci-dessus.
- `squad/conformite/FICHE-barre-entree-niveau.md` : inchangee par cette decision, `nadia-prof` la
  complete a la review de `S16-3`, `S16-4` et `S16-6`.

## Correction du 2026-08-19 (trouvee par le relecteur-pedagogique, chiffre etabli par sofia-sm)

**La section "Ce qui sort, differe ou reste hors de mon mandat, et pourquoi" cite "R-BARRE-1
(grands nombres CM1, +22 px de pli depuis s14)" : ce chiffre inverse le sens du mouvement.**
Source primaire verifiee par `sofia-sm` : `squad/conformite/FICHE-barre-entree-niveau.md`, section
"S15-7" - avant s14 y = 397, s14 y = 410 (+13 px), lot 3 (`b109f98`) y = 419 (**+22 px** - c'est le
lot 3 qui degrade), lot 4 (rang unique et etat actif) y = 411 (**+14 px** - le lot 4 **recupere
8 px**, il n'aggrave rien). Le chiffre reel a la cloture de s15, et donc a l'ouverture de s16, est
**+14 px**, non +22. Je ne corrige pas la phrase d'origine : je verse ici la lecture correcte de
la source, avec sa methode, comme `sofia-sm` l'a fait sur ses trois documents de s15
(`RAPPORT.md`, `review.md`, `planning.md` de s16).

**Ce que cela change a mon arbitrage : rien.** J'avais motive l'exclusion de `R-BARRE-1` du
perimetre de s16 par deux faits, aucun des deux ne portant sur le sens du chiffre : elle est *"sur
un jeu et un ecran sans rapport avec le chantier de l'en-tete"*, et je ne voulais pas *"melanger
deux chantiers dans un sprint deja charge de dettes"*. Le seul mot que le chiffre juste retire est
ma phrase *"rien ne l'a aggravee depuis sa derniere mesure"* : ce n'est pas qu'elle n'a pas
empire, c'est qu'elle **s'est amelioree de 8 px** par un effet de bord du lot qui a corrige le
rang unique de l'en-tete. Un pli qui recule de 8 px n'est pas un pli qui s'aggrave de 9, et cela ne
cree aucune urgence nouvelle a ouvrir la story candidate ce sprint - c'en retire, si quelque chose,
puisque le seul fait nouveau va dans le sens inverse d'une degradation. La decision de ne pas
ouvrir `R-BARRE-1` en s16 reste donc telle quelle, sur son motif de perimetre, corrigee sur son
seul chiffre.
