# DEC-0039 - Perimetre du sprint 17 : francais CM1 non couvert et la dette, la dette de capacite sort du perimetre de la squad

- **Type** : portee, arbitrage de trajectoire
- **Date** : 2026-08-19
- **Auteur** : camille-po
- **Sprint** : s17
- **Competence programme visee** : francais, CM1 (cycle 3) - deux lignes du referentiel
  (`squad/programme/francais/competences-cm1.md`), toutes deux `non couvert` a ce jour. Les
  sections 3 et 4 de cette decision sont transverses - pas de contenu pedagogique.
- **Statut** : Proposee - ce document propose un perimetre, il ne le gele pas. `sofia-sm` gele
  l'objectif et affecte nominativement au planning, apres l'avis de faisabilite de `theo-archi`.

## Contexte

L'utilisatrice a lu l'etat de couverture corrige de ce jour
(`squad/programme/MATRICE-couverture.md`, section "Synthese au sprint 17 (2026-08-19)") et tranche
elle-meme le perimetre du sprint 17 : **le francais CM1 non couvert, et la dette**. Elle ajoute que
la dette de capacite, nommee sans proprietaire depuis plusieurs sprints, sort du perimetre de la
squad - elle la prend a sa charge (section 3).

Neuf lignes de francais CM1 sont `non couvert` a ce jour, une est `en cours` (homophones), une est
`couvert-valide` (conjugaison au present). Je les reprends une a une contre le referentiel avant
d'ecrire une ligne, comme `DEC-0030` l'a fait pour les mathematiques CM1 en s13.

## 1. Deux lignes entrent au sprint 17

- **Story S17-2** - Comprendre un texte narratif ou documentaire (questions explicites et
  implicites, inferences simples). Matiere premiere : `contenu-repris/quiz/cm1-comprehension.yml`
  (8 items, structure deja compatible avec `type: question`). Fiche ouverte :
  `squad/conformite/FICHE-bandit-manchot-comprehension-cm1.md`.
- **Story S17-3** - Utiliser des synonymes et antonymes pour preciser ou nuancer un propos, en
  **couverture partielle nommee** : synonymes seuls, aucun antonyme, faute de matiere premiere
  (verifie par recherche du mot "antonyme" et de ses derives dans `contenu-repris/` : aucun fichier
  n'en porte). Matiere premiere : `contenu-repris/associer/cm1-synonymes.yml` (12 paires), reprise
  comme matiere et reformulee en question a choix multiple pour rester dans le schema
  `type: question` existant - la forme d'association par paires du fichier source ne correspond a
  aucune mecanique de `domain/mechanics` aujourd'hui (seule `banditmanchot` y est implementee), et
  en ouvrir une serait une question d'architecture hors de mon mandat. Fiche ouverte :
  `squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md`.

Les deux stories sont ecrites dans `docs/backlog/backlog.md`, section "Sprint 17", avec leurs
criteres d'acceptation. Chacune ouvre une fiche de conformite sous `squad/conformite/`, remplie a
l'exception du bloc de validation, reserve a `nadia-prof`.

**Deux ouvertures de contenu neuf, pas trois.** Mon plafond auto-impose est de trois par sprint
(`DEC-0030`, section 2), motive par le constat du sprint 12 : "un increment techniquement simple
peut consommer autant de temps sur ses defauts que sur son contenu". Je ne l'atteins pas ce sprint,
sur le meme raisonnement que celui qui m'a deja fait descendre a deux ouvertures en s13 face a une
charge annexe importante : ce sprint porte, en plus des deux ouvertures, dix obstacles herites de
s16 (section 4), la resurrection de "Le bon petit mot" (section 2, qui n'est pas une ouverture mais
qui mobilise la meme capacite de developpement et un nouveau Done pedagogique complet), et
l'arbitrage prealable deja pose sur `S17-1` (indicateur de focus clavier). Charger un troisieme
contenu neuf sur ce meme sprint reproduirait exactement le risque que je nommais deja en s13.

**L'arbitrage prealable de `nadia-prof` precede le code, sans exception, sur les deux stories.**
Ordre etabli depuis l'incident du "Bon verbe" en s07 (`squad/README.md`, section 10), tenu a chaque
ouverture de contenu depuis. Le detail des questions posees est dans chaque fiche :

- `S17-2` : la forme (texte affiche avec la question) constitue-t-elle un exercice valide de la
  competence visee, ou un exercice de recherche d'information localisee que le sens commun peut
  resoudre sans avoir compris le texte - meme risque que celui qui a fait refuser "Le bon son" en
  s02 sur l'appariement visuel.
- `S17-3` : la forme MCQ est-elle un proxy acceptable pour un verbe de production ("utiliser") ; le
  perimetre partiel (synonymes seuls) est-il acceptable comme premiere ouverture.

Aucun code n'est ecrit sur l'une ou l'autre story avant que ces arbitrages ne soient rendus.

## 2. La resurrection de "Le bon petit mot" : elle avance une ligne deja `en cours`, elle n'en ouvre pas

`DEC-0024` (s10) a laisse au backlog, non affectee, la story de raccourcissement de
`cm1-homophones.yaml` - contenu pedagogiquement valide (Done `VALIDE` du 2026-08-10), retire du
catalogue jouable pour un seul motif de forme (son enonce le plus long ne tient pas au-dessus du
pli). Je l'affecte au sprint 17 sous le nom **`S17-4`**, dans `docs/backlog/backlog.md`, avec les
deux conditions deja ecrites par `DEC-0024` reprises au mot pres, sans les redurcir ni les
affaiblir :

- **condition de forme** (point 3 de `DEC-0024`) : sur le paquet raccourci, l'etat le plus charge de
  chaque manche tient au-dessus du pli a 360 x 640, mesure par `outils/mesurer-rendu.sh`, jamais par
  un compte de caracteres (`DEC-0016`) ;
- **correction des indices dans le meme geste** (point 5 de `DEC-0024`) : les 22 indices sur 24 qui
  nomment la reponse au lieu de la manipulation qui y mene sont corriges en meme temps que le
  raccourcissement, pour ne pas rouvrir le meme fichier une seconde fois pour un motif distinct.

Le paquet raccourci repasse devant `nadia-prof` comme un contenu neuf, sur la barre en six points
qu'elle a deja ecrite pour ce paquet (`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`) -
je ne la rouvre pas, je rappelle qu'elle s'applique. **Cette story ne compte pas dans le plafond de
la section 1** : la ligne est deja `en cours` depuis s10, jeu et mecanique deja renseignes dans la
matrice ; ce travail la fait progresser vers `couvert-valide`, il ne fait passer aucune ligne de
`non couvert` a `en cours`. Voir `squad/programme/MATRICE-couverture.md`, note dediee sous le
tableau francais CM1.

## 3. La dette de capacite sort du perimetre de la squad

L'utilisatrice reprend a sa charge deux des dix obstacles ouverts a l'entree de s17
(`squad/sprints/s16-deux-defauts-et-dettes-de-len-tete/RAPPORT.md`, section "Obstacles / Ouverts a
l'entree de s17") :

- **La dependance a une seule personne pour toute mesure de rendu.** Aucun agent de la squad, hors
  `nadia-prof`, ne peut produire un chiffre de rendu sur ce poste : aucun outil du depot ne pilote
  un navigateur reel (`ADR-0046`, section "Ce que la squad n'a pas vu, et pourquoi"). Route jusqu'ici
  a `sofia-sm` (capacite) et `camille-po`, sans decision jamais prise.
- **`R-BARRE-4` / la reserve **F11**** (l'ecoute reelle au lecteur d'ecran). Nommee depuis avant s10,
  confirmee et enrichie a chaque sprint qui touche l'en-tete
  (`squad/conformite/FICHE-barre-entree-niveau.md`), jamais levee, jamais assignee : "hors de la
  portee de la squad" est la formule exacte qui la decrit depuis s13
  (`squad/sprints/s16-deux-defauts-et-dettes-de-len-tete/RAPPORT.md`).

**Ce que ce transfert change concretement.** Ce n'etaient pas des obstacles sans motif : c'etaient
des obstacles sans proprietaire capable de trancher, reconduits de rapport en rapport parce
qu'aucun agent de la squad n'a mandat pour investir dans un navigateur pilote reellement ou pour
produire une ecoute de lecteur d'ecran. L'utilisatrice en devient la proprietaire explicite. Ce
n'est plus une dette de la squad a nommer au sprint suivant : je le retire de mon perimetre et de
celui de `sofia-sm` a compter de s17, et je le route une derniere fois ci-dessous pour que le
tableau des obstacles cesse de les reconduire sans decision.

**Ce que je ne fais pas.** Je ne pretends pas juger si l'un ou l'autre chantier est facile ou
difficile a mener hors de la squad - ce jugement n'est pas mien. Je ne retire aucune des reserves
pedagogiques qui y sont adossees (`F11`, `R-BARRE-4` dans `squad/conformite/
FICHE-barre-entree-niveau.md`) : elles restent la responsabilite de `nadia-prof` de les nommer et de
les rouvrir si l'ecoute ou la mesure de rendu venaient a exister par un autre chemin.

## 4. Le filtre par matiere : troisieme report, compte comme tel, pour la premiere fois sur instruction expresse

`DEC-0032` a differe le filtre par matiere une premiere fois (s14 vers s15) sous deux conditions
explicites. `DEC-0037` l'a differe une seconde fois (s16), aucune des deux conditions n'etant levee.
L'utilisatrice fixe elle-meme le perimetre de s17 sur "le francais CM1 non couvert, et la dette" -
elle ne nomme pas le filtre par matiere. Je ne le propose donc pas ce sprint.

Meme si elle l'avait inclus, les deux prealables resteraient a lever avant tout code : la
nomenclature de matiere accentuee (`DEC-0032` point 2, sans reponse ecrite de `theo-archi` depuis le
2026-08-17) et l'absence de toute proposition de forme d'`ines-design` pour un second axe de filtre
combine, dont `nadia-prof` a date la bascule en condition bloquante
(`squad/conformite/FICHE-barre-entree-niveau.md`, section 4, R-BARRE-3 / S14-B).

**Je le compte comme un troisieme report, sans en attenuer le chiffre** - c'est la regle que
`DEC-0007` a posee pour ce produit : un report isole est un arbitrage, trois reports successifs sont
une direction non choisie. La difference avec les deux precedents, que je nomme pour ce qu'elle est
sans la faire valoir comme une excuse : ce n'est plus une continuation silencieuse de la squad, c'est
l'utilisatrice elle-meme qui, en fixant le perimetre de s17 sur le francais CM1 et la dette, choisit
de ne pas y inclure le filtre par matiere. C'est exactement la situation que `DEC-0007` decrivait
comme le point de bascule ("jusqu'a ce que l'utilisatrice demande elle-meme pourquoi") - sauf qu'ici
elle a deja parle, dans le sens du report. Le chiffre du report reste le meme ; son proprietaire
change.

## 5. Ce qui reste `non couvert`, differe nommement

- **Lire silencieusement un texte de longueur croissante et en restituer l'essentiel.** Premier
  report, structurel : la competence demande une production (un resume dans les mots de l'enfant),
  le mecanisme du bandit manchot ne sait que faire choisir une proposition parmi plusieurs. Route a
  `theo-archi` le jour ou une mecanique de production ou de saisie libre est envisagee pour le
  produit - meme famille que "reperage sur une droite numerique" en mathematiques CM1
  (`ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 1).
- **Rediger un texte narratif court et coherent** et **Relire et corriger son texte a l'aide de
  reperes simples.** Meme famille structurelle (production d'ecriture libre plutot que
  reconnaissance), premier report pour chacune, meme routage a `theo-archi`.
- **Reperer les substituts d'un personnage (pronoms, groupes nominaux) dans un texte**, **Accorder
  le verbe avec son sujet (cas frequents, temps simples)** et **Utiliser a bon escient des mots de
  liaison simples.** Differees pour capacite, premier report chacune. Verifie par recherche cible
  dans `contenu-repris/` (mots-cles "pronom", "substitut", "accord verbe/sujet", "liaison",
  "connecteur") : aucune matiere premiere identifiee pour aucune des trois. Servables en principe par
  le schema `type: question` existant, sans arbitrage de principe identifie a ce jour, mais un
  contenu neuf integralement ecrit sans matiere premiere reste le plus couteux a bien ecrire du
  premier coup - je prefere les caler sur un sprint qui ne porte pas deja deux ouvertures et dix
  obstacles. Candidates prioritaires du sprint suivant.
- **Accorder en genre et en nombre dans le groupe nominal (determinant/nom/adjectif).** Differee
  pour capacite, premier report. Matiere premiere partielle identifiee
  (`contenu-repris/textes-trous/cm1-accords.yml`, 5 items, "L'accord de l'adjectif" seul) : elle ne
  couvre pas l'accord determinant/nom que la competence nomme aussi. Je le nomme des maintenant,
  avant tout code, pour que la couverture partielle qu'un paquet fonde sur ce seul fichier porterait
  soit vue venir plutot que decouverte a l'audit, si cette ligne est retenue telle quelle au sprint
  suivant.

**Un repris ecarte, nomme pour ne pas etre redecouvert par erreur.**
`contenu-repris/mots/cm1-nature-mot.yml` ("La nature du mot" - nom/verbe/adjectif/determinant) a
l'apparence d'une matiere premiere de grammaire CM1, mais ne correspond a aucune des onze
competences du referentiel CM1 actuel : "distinguer les principaux types de mots" est une competence
de CP (`squad/programme/francais/competences-cp.md`), pas de CM1. Le reprendre pour "boucher" une
ligne de CM1 inventerait une competence que le referentiel ne porte pas. Je l'ecarte explicitement
plutot que de le laisser trainer comme une fausse piste pour un sprint futur.

## Impact

- `docs/backlog/backlog.md`, sprint 17 : stories `S17-2`, `S17-3`, `S17-4` ajoutees, avec un lien
  vers cette decision.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0039` ajoutee.
- `squad/conformite/FICHE-bandit-manchot-comprehension-cm1.md` et
  `FICHE-bandit-manchot-synonymes-cm1.md` : ouvertes, remplies a l'exception du bloc de validation.
- `squad/programme/MATRICE-couverture.md` : deux lignes francais CM1 passent `non couvert` a
  `en cours` (jeu et mecanique renseignes), une note versee sous le tableau pour la resurrection de
  la ligne homophones (pas un mouvement de statut), une section "Perimetre de s17" et une synthese
  mise a jour.
- L'engagement pose par `DEC-0037` (si s17 ne rouvre aucune ligne, ce sera ecrit comme un changement
  de direction) est **leve par ce perimetre, une fois gele par `sofia-sm`** : ce sprint est le
  premier depuis trois a viser une ligne du programme.

## Ce que je route

- **`theo-archi`** : avis de faisabilite sur `S17-2`, `S17-3`, `S17-4` ; confirmation que le schema
  `type: question` porte les deux formes retenues (MCQ sur texte-support, MCQ synonyme) sans code
  neuf.
- **`nadia-prof`** : les deux arbitrages prealables bloquants de la section 1, avant tout code sur
  `S17-2` et `S17-3` ; reverification de `S17-4` comme un contenu neuf sur la barre en six points
  deja ecrite.
- **`ines-design`** : aucune saisie sur ce perimetre de contenu ; reste due sur `S17-1`, deja posee.
- **`sofia-sm`** : gel de l'objectif et affectation nominative au planning ; verification que le
  tableau des obstacles de `RAPPORT.md` ne reconduit plus, a compter de s17, les deux lignes de
  capacite transferees en section 3.

## Ce qui doit etre arbitre avant tout code

- `S17-2` et `S17-3` : les arbitrages prealables de la section 1, chacun sur sa propre fiche.
- `S17-4` : aucun arbitrage de principe neuf - les deux conditions de `DEC-0024` s'appliquent telles
  qu'ecrites, et le paquet repasse devant `nadia-prof` comme un contenu neuf des qu'il est raccourci.

## Amendement du 2026-08-19 - les trois arbitrages prealables sont rendus

`nadia-prof` a rendu les trois arbitrages prealables bloquants nommes ci-dessus
(`squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md`, branche `s17-nadia`,
tete `c5d2bce`, worktree dedie non fusionne). Je n'efface aucune ligne des sections
precedentes : ce qui suit corrige et complete, ne remplace pas.

### 1. `S17-2` - la matiere premiere refusee, ce que cela change au plafond et au cout

`nadia-prof` a mesure les huit items de `contenu-repris/quiz/cm1-comprehension.yml` un par un :
huit sur huit resolubles par appariement de mots avec le texte, au moins quatre sur huit resolubles
sans le texte, zero item sur huit qui demande l'inference que le referentiel nomme, et un support
d'une seule phrase qui n'est pas un texte. Sa conclusion : **"ce n'est pas une reprise, c'est un
paquet neuf".** Le principe (une question a choix multiple peut verifier une comprehension reelle)
reste **ACCEPTE**, sous cinq conditions bloquantes (COMP-1 a COMP-5, reprises dans
`squad/conformite/FICHE-bandit-manchot-comprehension-cm1.md` et dans la story `S17-2` du backlog).

**Ce que cela change a mon estimation de cout, dit sans detour** : quand j'ai ouvert cette story, une
partie de mon raisonnement pour la classer parmi les deux ouvertures raisonnables de ce sprint (et
non parmi les lignes differees pour capacite, section 5) tenait a l'existence d'une matiere premiere
qui semblait directement transposable. **Cette economie n'existe pas.** Le paquet a ecrire est
integralement neuf, au meme rang de cout qu'une ligne comme "reperer les substituts d'un personnage"
que j'ai differee en section 5 pour ce motif precis (aucune matiere premiere).

**Ce que je ne change pas, et pourquoi je le maintiens neanmoins a deux ouvertures ce sprint plutot
que de le redescendre a un et de differer `S17-2`** : ce que `nadia-prof` a produit en echange n'est
pas rien - elle a resolu **avant tout code** les deux questions qui, dans les precedents refuses de
ce produit (Le bon son en s02, Le bon verbe en s07), n'avaient ete decouvertes qu'a l'audit, apres
que du contenu avait deja ete ecrit : la validite du principe pedagogique, et le budget de rendu
exact (5 lignes au style d'enonce actuel, 8 lignes a un style dedie - section 0 de son arbitrage,
mesure avant que je n'ecrive un seul item). C'est precisement l'inverse du risque qui m'a fait
differer les lignes sans matiere premiere de la section 5 : la, personne n'a encore instruit le
contenu ni mesure son rendu ; ici, les deux sont faits, et ce qui reste a ecrire est cadre par cinq
conditions decidables plutot que par un principe encore flou. Je retiens donc `S17-2` dans le
perimetre, avec son cout revise a la hausse (contenu integralement neuf, plus un prealable de design
avant le premier item - section 2 ci-dessous) mais son risque de premier jet reduit par l'arbitrage
deja rendu. **Le plafond de deux ouvertures ne change pas** : je n'ouvre pas de troisieme ligne pour
compenser, le sprint reste a deux ouvertures de contenu neuf.

### 2. `S17-2` - le critere "sans gabarit ni feuille de style neuve" est leve

`nadia-prof` a mesure que ce critere, tel que j'avais ouvert la fiche, ne peut pas tenir avec la
competence visee : au style d'enonce actuel, le budget est de 5 lignes, qui ne portent qu'un
reperage litteral - exactement ce qu'elle refuse par COMP-1 et COMP-2. Elle l'a ecrit sans detour :
**si ce critere est maintenu, sa reponse au principe devient NON.**

**Je leve ce critere plutot que de sortir la story du perimetre.** C'est mon arbitrage, pas le sien :
le critere venait de moi, a l'ouverture de la fiche, et son seul motif etait d'eviter un cout de
developpement, pas une exigence pedagogique. Le sacrifier pour tenir une regle de forme que je m'etais
moi-meme fixee, alors que `nadia-prof` vient de montrer precisement ou et pourquoi elle bloque la
competence, serait ecrire un refus a l'avance pour economiser une decision de style. **Condition de
remplacement (COMP-5, bloquante)** : le texte-support recoit un style distinct de celui de l'enonce,
plus petit, sur les deux chiffres qu'elle a mesures (5 lignes insuffisant, 8 lignes suffisant). La
forme exacte (taille, police, couleur) n'est pas la mienne a choisir : je la route a `ines-design`
pour la proposition et a `theo-archi` pour le mecanisme, avant qu'aucun des vingt items de `S17-2`
ne soit ecrit sur sa base - `nadia-prof` jugera cette proposition sur sa lisibilite, elle ne l'a pas
tranchee elle-meme. Ce n'est pas une mecanique neuve (aucun code de jeu ne change, une seule regle
de style s'ajoute pour un champ) : je ne considere donc pas que cela deplace `S17-2` hors du
perimetre de contenu pour en faire une story d'architecture.

### 3. `S17-3` - statut retenu pour la ligne des synonymes

Je retiens la distinction que `nadia-prof` pose, au mot pres : les antonymes ne sont pas un
prolongement du meme geste que les synonymes (contrairement aux cinq partialites deja acceptees sur
ce produit), ils sont l'autre operation. **Decision** : cette ligne reste `en cours` quel que soit
le verdict pedagogique rendu sur le paquet synonymes seul, et elle n'entre jamais dans le numerateur
du taux de couverture tant que les antonymes n'y sont pas ajoutes - y compris si `nadia-prof` rend un
jour un Done `VALIDE` sans reserve sur ce paquet. Je ne laisse pas cette ligne suivre le chemin de
"tables de multiplication" (`couvert-valide` avec une mention restrictive) : le motif qu'elle donne
(l'autre operation, pas un prolongement) est plus fort que celui des tables (une propriete du meme
verbe non mesuree), et je le retiens tel quel plutot que d'arbitrer moi-meme une nuance pedagogique.

**Comment la matrice l'exprime** : par une note en prose sous le tableau francais CM1
(`squad/programme/MATRICE-couverture.md`), sur le meme mecanisme que la note "comparer/ranger" de
s09 - pas par un statut nouveau. La nomenclature de cette matrice compte trois statuts
(`non couvert` / `en cours` / `couvert-valide`) depuis le sprint 0 ; ce n'est pas a une decision de
perimetre d'en ajouter un quatrieme, et la prose porte deja cette nuance sans en avoir besoin.

**Story compagne** : `S17-5` (antonymes), ecrite au backlog, non affectee, sur demande de
`nadia-prof` (condition SYN-4, non bloquante mais datee).

## Ce que je route (ajout du 2026-08-19)

- **`ines-design`** : proposition de style pour le texte-support de `S17-2` (COMP-5), avant tout
  item ecrit sur sa base.
- **`theo-archi`** : mecanisme de ce style neuf (une regle CSS pour un champ, pas une mecanique de
  jeu neuve) et avis de faisabilite sur `S17-2` et `S17-3` tels qu'amendes ici.
- **`sofia-sm`** : verifier au planning que `S17-5` (antonymes) figure au backlog avant la cloture de
  s17, meme non affectee, condition posee par `nadia-prof`.

## Amendement du 2026-08-19 (camille-po, s17) - `S17-2` sort du perimetre de s17

Les trois rendus attendus sur `COMP-5` sont revenus, chacun lu a sa source
(`squad/conformite/FICHE-bandit-manchot-comprehension-cm1.md`, section "Jugement de lisibilite du
texte-support" ; `squad/sprints/s17-francais-cm1-et-dette/proposition-ines-mise-en-page-manche-
comprehension.md` ; `squad/sprints/s17-francais-cm1-et-dette/avis-mecanisme-texte-support-comp5.md`
et `ADR-0050`, tous fusionnes sur `round-two` a `150c428`). Je tranche la question de perimetre
qu'ils me routent tous les trois : `S17-2` n'est pas de leur ressort de fermer.

**Le fait, sans l'habiller.** `nadia-prof` accepte `COMP-5` sur la forme (variante nue
d'`ines-design`, lisible), mais son propre budget de "8 lignes / 45 mots" - mesure ce matin sans
consigne, sans bloc de question et avec des propositions d'un chiffre - ne survit pas a la mise en
page reelle de `S17-2` : consigne, question et propositions ajoutent chacune plus que les
26,81 px de marge qui restaient. **Le budget compose reel est de 4 lignes, environ 20 mots, dans
l'assemblage le plus favorable qui soit encore realiste** - elle le dit elle-meme insuffisant pour
porter l'inference que `COMP-3` exige, et le paquet y redeviendrait le reperage litteral qu'elle a
deja refuse pour "Le bon son" en s02. Il faut retrouver 90 a 120 px. `ines-design` en degage 44
sans aucun cout pour l'enfant (sa recommandation), ou 82 par une voie qu'elle deconseille
elle-meme parce qu'elle retire le score de l'ecran - et ni l'un ni l'autre ne referme l'ecart.
`theo-archi` n'apporte aucun pixel : le mecanisme est confirme (`S17-2` reste une story de
contenu, pas d'architecture - cinq fichiers, quatre modules, mais aucune evolution de schema), et
il ferme deux trous de garde-fou reels et independants de ce sprint (le plancher de 1,25rem
n'etait garde par aucune regle derivee, l'identite d'ecran ne couvrait pas un troisieme texte
rendu) sans en tirer un seul pixel vertical.

**Pourquoi ce n'est pas un arbitrage de degre que je peux prendre a la place de `nadia-prof`.**
Une "forme reduite" n'est disponible ici que si un contenu ecrit au budget survivant (20 a environ
31 mots, en comptant le meilleur gain qu'`ines-design` propose) peut encore satisfaire `COMP-3`.
`nadia-prof` a deja repondu, dans le meme rendu, sans conditionnel : *"a 20 mots, un support ne
porte pas d'inference, donc COMP-3 n'est pas atteignable et le paquet redevient le reperage
litteral qu'elle a refuse"* - et elle ne s'en dedit pas parce que la conclusion devient genante.
Ce n'est pas une nuance de perimetre a la maniere des synonymes (`S17-3`, une partialite qu'elle a
elle-meme bornee et acceptee) : c'est un contenu qui, ecrit au seul budget que la squad sait
produire aujourd'hui, echouerait la condition bloquante qu'elle a posee pour que le principe
tienne. Trancher "une forme reduite mais valide" reviendrait a decider a sa place que `COMP-3`
peut ceder, ce qui n'est pas mien a decider.

**Decision : `S17-2` sort du perimetre de s17 et retourne au backlog, non affectee.** Aucun code de
production n'existe pour ce champ a ce jour - verifie plutot que suppose : aucune classe des
modules `domain`, `application` ni `adapters` ne porte de champ ou de propriete nommant un
texte-support, aucune entree `bandit-manchot-comprehension-cm1` n'existe dans `catalogue.yaml`,
aucun fichier de contenu `cm1-comprehension.yaml` n'existe sous
`adapters/adapter-content/src/main/resources/contenu/`. Rien n'est donc a defaire : la sortie de
perimetre ne coute aucun retour arriere de code. Ce que je garde du travail fait ce sprint est tout
le contraire d'une perte - `COMP-1` a `COMP-7`, le tableau chiffre de la section 3 du jugement de
`nadia-prof`, les deux variantes d'`ines-design` avec leur cout exact, et `ADR-0050` avec ses deux
garde-fous - c'est la matiere qu'une prochaine ouverture de cette ligne n'aura pas a reproduire.

**Ce que je ne fais pas.** Je ne retire aucune des deux regles CSS d'`ADR-0050` ni les garde-fous
qui les portent (`TailleDesTextesEtBoutonsTest`, `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`) :
ils ferment des trous reels et prealables a `S17-2`, independants de son sort - le plancher de
1,25rem valait deja pour sept selecteurs geres a la main, il vaut desormais pour toute la feuille ;
l'identite d'ecran valait deja pour enonce + propositions, elle sait desormais classer un troisieme
texte rendu, quel qu'il soit. Je ne rouvre aucun jugement de `nadia-prof` sur `COMP-1` a `COMP-7` :
ils restent la matiere de travail du jour ou cette ligne rouvre. Je ne dessine aucune mise en page
et je ne tranche aucun mecanisme : voir "Ce que je route" ci-dessous.

**Ce que je route.** L'ecart de 90 a 120 px ne se ferme pas dans le mandat deja donne a
`ines-design` (un champ generique, sans toucher au cabinet ni au score) ni dans celui deja donne a
`theo-archi` (le mecanisme du champ, pas la disposition de l'ecran). Le combler demanderait de
rouvrir un element partage par tous les jeux du catalogue - le gabarit de l'ecran de manche
lui-meme (le cabinet, la place du score) plutot qu'un champ scope a un seul contenu - ce qui est
exactement le type de cycle de mise en page que ce produit s'interdit hors d'un gel explicite
(memoire de squad : gel de design au sprint 9, reouverture au sprint 10). **Je nomme cet
arbitrage, je ne le prends pas** : c'est a `sofia-sm` de decider si et quand elle mandate
`ines-design` et `theo-archi` sur une revision du gabarit de l'ecran de manche lui-meme, hors de la
capacite deja engagee sur s17 (deux ouvertures et dix obstacles de dette, `DEC-0039` section 1).

## Ce que s17 rend reellement a la couverture, en clair

Le planning de s17 comptait `S17-2` comme la seule ligne susceptible d'atteindre `couvert-valide`
ce sprint. Elle en sort. Ce qui reste :

- **`S17-3` (synonymes)** ouvre bien une ligne (`non couvert` -> `en cours`) si elle va a son
  terme : c'est la seule ligne que s17 fait avancer. Elle **ne peut pas** atteindre
  `couvert-valide`, meme seule et meme sur un contenu que `nadia-prof` validerait sans reserve -
  section 3 de cet amendement, deja ecrite avant ce jour, non rouverte ici : les antonymes doivent
  s'y ajouter avant que cette ligne n'y pretende.
- **`S17-4` (homophones)** reste `en cours` comme avant s17 : son retour a `couvert-valide` est
  bloque par un motif distinct et deja ecrit (`DEC-0024`, amendement du 2026-08-19 : la mesure
  `BPM-1` et le Done pedagogique complet sur le paquet raccourci manquent tous deux).
- **`S17-2` (comprehension)** retourne `non couvert` (section ci-dessous, `MATRICE-couverture.md`).

**Aucune ligne de `squad/programme/MATRICE-couverture.md` n'atteindra `couvert-valide` a la
cloture de s17.** L'engagement pose par `DEC-0037` (si s17 ne rouvre aucune ligne, ce sera ecrit
comme un changement de direction) reste leve par `S17-3` seule : au moins une ligne progresse, ce
n'est pas un quatrieme sprint de stabilisation silencieuse. Mais l'ambition plus haute que le
planning avait nommee - une ligne qui franchit `couvert-valide` ce sprint - ne se realise pas, et
je ne l'habille pas autrement.
## Amendement du 2026-08-19 (camille-po, s17) - `S17-C` (tabindex de la revelation) n'entre pas au perimetre de s17

`nadia-prof` a rendu sa sonde post-livraison sur `S17-1` (`squad/conformite/FICHE-barre-entree-
niveau.md`, section "Sonde post-livraison du 2026-08-19 (s17) sur S17-1", branche `s17-nadia-foc`,
increment mesure `round-two` a `d649e15`) : **VALIDE SOUS RESERVE**, `S16-A` **FERMEE**. Elle ouvre
quatre reserves datees, dont une qui m'est routee comme arbitrage de perimetre : `S17-C`,
bloquante - au clavier, a la revelation d'une manche, le bouton que le jeu nomme ("C'est auront.
Appuie sur auront.") **est le seul absent de l'ordre de tabulation**, parce que
`static/js/recreo.js` pose `tabindex="-1"` sur sa cible meme quand elle est deja focalisable.
**Le defaut date de `M13` (s10)** : il n'est pas cause par `S17-1`, l'anneau de focus l'a seulement
rendu visible pour la premiere fois.

**Ce que je ne fais pas.** Je ne juge pas la perception a la place de `nadia-prof` : sa mesure
("`aurai > (document) > CP > CM1 > Les jeux > aura > avaient`", "auront" absent) est actee telle
quelle. Je ne dessine ni ne tranche le correctif de `static/js/recreo.js` ni son garde-fou : voir
"Ce que je route" ci-dessous. Je ne compte pas la capacite liberee par la sortie de `S17-2` du
perimetre comme un motif d'entree - ce n'en est pas un, et je ne m'en sers pas ici.

**Decision : `S17-C` n'entre pas au perimetre de s17. Elle sort au backlog comme story propre,
non affectee.** Trois motifs, aucun ne portant sur la gravite du defaut (bloquante, elle le reste
tant qu'elle tient) :

1. **Le defaut est anterieur de sept sprints et n'a jamais ete impute a aucune story** : il n'est
   la consequence d'aucun geste de s17, contrairement a `S17-A` et `S17-D` qu'`S17-1` a directement
   introduits ou exposes par construction. Le charger sur ce sprint reviendrait a faire porter a
   `S17-1` un passif qui existait deja sous les sept increments precedents sans que personne ne le
   voie.
2. **Le correctif touche `static/js/recreo.js`**, exactement la zone que `CLAUDE.md` nomme comme
   dette 4 de la version precedente ("le comportement des mecaniques loge dans le JavaScript
   client, que rien ne testait"). Ce depot a deja un test qui execute reellement ce script
   (`RecreoJsDeplaceLeFocusTest`, S10) mais son propre Javadoc dit ce qu'il ne couvre pas :
   *"le vrai focus clavier d'un navigateur (tabindex, ordre de tabulation reel...)"* - son faux DOM
   ne modelise aujourd'hui aucun attribut `tabindex`. Fermer `S17-C` avec autre chose qu'une
   verification manuelle de plus (le mode de defaillance que `nadia-prof` nomme elle-meme en
   section 7 : *"aucun garde-fou executable [...] c'est moi le garde-fou"*) suppose donc une
   decision sur comment un attribut `tabindex` entre dans ce faux DOM, ou sur une autre voie de
   garde-fou - une question de mecanisme, pas de contenu, qui n'a pas encore de reponse ecrite.
3. **Le cout n'est pas cerne.** Le geste de correction lui-meme parait simple dans son enonce ("ne
   poser `tabindex="-1"` que sur une cible qui n'est pas deja focalisable"), mais son garde-fou ne
   l'est pas (point 2), et `DEC-0039` porte deja dix obstacles herites de s16, la resurrection du
   "bon petit mot" et deux dettes de garde-fou sur ce seul sprint. Ajouter un correctif de cout
   inconnu dans une zone que le produit a deja identifiee comme sa dette la plus lourde reproduirait
   le risque nomme depuis `DEC-0021` : *"un increment techniquement simple peut consommer autant de
   temps sur ses defauts que sur son contenu."*

**Ce que cela ne change pas.** `S17-C` reste bloquante au sens de `nadia-prof` : rien ici ne
l'affaiblit, ne la reformule ni ne la rend non bloquante par un tour de plume - a la difference de
`M4` en s10 (`DEC-0025`), je ne leve aucune condition, je diffère seulement le sprint qui la
traite. `S16-A` reste FERMEE, ce constat n'est pas rouvert.

## Ce que `S17-1` livre a s17

`S16-A` (l'absence totale d'indicateur de focus clavier) est fermee : c'est ce que le sprint
livre, mesure par `nadia-prof` sur cinq ecrans et une partie jouee entierement au clavier. **Le
Done pedagogique de `S17-1` n'est pas prononce en totalite a la cloture de s17** : la story se
ferme **VALIDE SOUS RESERVE**, avec `S17-C` desormais nommement differee au-dela de s17 (ci-dessus)
et `S17-A`/`S17-D` encore `en cours` sur leurs branches respectives (`ines-design` pour la forme de
`S17-A`, `marc-dev` pour le correctif de `S17-D`) sans que j'en prejuge l'issue avant la cloture -
`S17-B` seule est non bloquante. C'est `nadia-prof`, et elle seule, qui prononcera si `S17-A` et
`S17-D` ferment a temps pour ce sprint ; ce que je peux dire dans tous les cas, c'est que la
reserve `S17-C` restera ouverte a la cloture de s17 quel que soit le sort des deux autres, puisque
sa resolution n'est plus de ce sprint.

## Ce que je route (ajout du 2026-08-19, second)

- **`theo-archi`** : le mecanisme du correctif de `static/js/recreo.js` pour `S17-C` (ne poser
  `tabindex="-1"` que sur une cible qui n'est pas deja focalisable) et son garde-fou - notamment si
  `RecreoJsDeplaceLeFocusTest` peut etendre son faux DOM pour modeliser `tabindex`, ou si cette
  clause reste, comme la clause 1 de `M13`, verifiee seulement par `nadia-prof` au clavier reel
  hors CI.
- **`sofia-sm`** : affectation de la story `S17-C` (`docs/backlog/backlog.md`) a un sprint futur,
  apres l'avis de `theo-archi` ci-dessus.

## Amendement du 2026-08-19 (camille-po, s17) - le mandat sur le cabinet et le score n'est elargi a personne ; l'ecart reste ferme jusqu'a la reouverture de `S17-2`

`sofia-sm` me route un obstacle qu'elle refuse de trancher a ma place, et son motif est le bon :
elargir le mandat d'un agent est une decision de perimetre, pas une decision de Scrum Master.
L'arbitrage qui fermerait les 90 a 120 px manquants de `S17-2` (amendement ci-dessus) porte sur
une revision de l'**ecran de manche partage** - emprise du cabinet, place du score - une zone que
ni `ines-design` (scopee au champ generique, `.texte-support`) ni `theo-archi` (mecanisme, qui n'en
rend aucun pixel) ne tiennent aujourd'hui sous mandat.

**Ce que je verifie avant de trancher, plutot que de le supposer.** `ines-design` a deja nomme
elle-meme pourquoi elle ne va pas plus loin de son propre chef
(`squad/sprints/s17-francais-cm1-et-dette/proposition-ines-mise-en-page-manche-comprehension.md`,
section 1) : le cabinet (`.cabinet`, `.cabinet-bandeau`, `.cabinet-socle`, `.cabinet-levier`) et
l'ecart entre propositions sont *"l'identite visuelle du meuble, actee sur plusieurs sprints
(`ADR-0030`, `ADR-0033`, `ADR-0036`, F5)"*, et le `gap` de 1rem vient nommement d'une reserve de
`nadia-prof` sur le geste de l'enfant (`S3-5`). Masquer le score, sa seule autre piste, coute une
information visible sur tous les autres jeux du catalogue et elle ecrit elle-meme : *"ce n'est pas
ma decision seule a prendre."*

**Pourquoi ce n'est pas une simple question de repartition du travail entre deux agents
disponibles.** Le cabinet et le score ne sont pas des elements scopes a `S17-2` : ce sont des
elements **partages par les huit jeux deja au catalogue**, dont plusieurs portent un Done
pedagogique deja accorde sur des proprietes qui en dependent directement - `M4` (le pli, huit
jeux), `S15-8`/`S15-9` (couleur et trait d'une proposition deja essayee, quatre positions), les
conditions `F5` a `F12` sur l'habillage du meuble, et la reserve de geste de `S3-5` elle-meme.
Elargir un mandat sur cette zone pour gagner 44 a 82 px sur un seul ecran encore non construit
reviendrait a rouvrir une question de forme sur des jeux deja juges, pour un gain que ni
`ines-design` ni `theo-archi` ne garantissent suffisant - elle l'ecrit elle-meme : *"je ne sais pas
si mes pixels suffisent."* C'est exactement le type de cycle de mise en page que ce produit gouverne
par un gel explicite plutot que par une extension de perimetre au fil de l'eau (memoire de squad :
gel de design au sprint 9, reouverture nommee au sprint 10 pour le meuble lui-meme). Une revision de
l'identite partagee de l'ecran de manche est une decision de cette famille ; elle ne s'ouvre pas en
annexe d'une story de contenu qui, par ailleurs, est deja hors du perimetre de s17.

**Decision : je n'elargis le mandat d'aucun des deux agents aujourd'hui.** L'ecart reste ferme -
personne ne le detient pour l'executer - jusqu'a ce que `S17-2` soit rouverte a un sprint futur.
Ce n'est pas une troisieme branche que `sofia-sm` n'aurait pas nommee : c'est le second choix
qu'elle a pose ("acter que ce vide reste ouvert et que l'ecart reste ferme tant que `S17-2` n'est
pas rouverte"), retenu plutot que le premier.

**Le proprietaire de cette question, nomme pour qu'elle ne se reconduise pas sans porteur.** Ce
n'est ni `ines-design` ni `theo-archi` aujourd'hui - c'est etabli. **Ce n'est pas non plus une
question a transferer a l'utilisatrice** comme `DEC-0039` section 3 l'a fait pour la mesure de
rendu : la aussi il s'agissait d'un manque d'**outillage** (aucun agent ne pouvait piloter un
navigateur reel), un investissement hors du budget d'outils de la squad. Ici, la squad a la
competence necessaire des deux cotes (design, architecture) ; ce qui manque est un **arbitrage de
risque produit** - accepter de rouvrir une identite partagee et deja jugee, pour un gain incertain,
sur une story qui n'est pas priorisee ce sprint - et c'est un arbitrage de perimetre, donc **le
mien**. **Je le garde : `camille-po` est proprietaire de cette question**, avec un declencheur
explicite plutot qu'un report muet - le jour ou `S17-2` revient au perimetre d'un sprint, l'ouverture
ou non d'un mandat borne sur le cabinet et le score pour cette story est tranchee dans le meme
geste que sa reouverture, avec son cout et son risque nommes a ce moment-la plutot qu'aujourd'hui
sans contenu en face.

**Ce que je ne fais pas.** Je ne juge pas si 44, 82 ou davantage de pixels suffiraient un jour :
seule une proposition reelle d'`ines-design`, sur un mandat explicitement ouvert, pourrait le dire.
Je ne rouvre aucun Done pedagogique deja accorde sur `M4`, `S15-8`, `S15-9` ou les conditions `F` du
meuble.

## Ce que je route

- **`sofia-sm`** : ne plus reconduire cet obstacle comme une ligne sans proprietaire dans les
  rapports de sprint - il a desormais un proprietaire (`camille-po`) et un declencheur explicite (la
  reouverture de `S17-2`), sur le meme modele que les obstacles transferes en section 3.
