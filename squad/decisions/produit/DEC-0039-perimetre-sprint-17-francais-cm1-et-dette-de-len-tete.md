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
