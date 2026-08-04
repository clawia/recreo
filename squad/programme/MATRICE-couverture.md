# Matrice de couverture - recreo

Cette matrice est l'outil de pilotage le plus important de `camille-po`. Elle liste, une ligne
par competence, l'integralite des quatre referentiels actuellement disponibles
(`squad/programme/mathematiques/competences-cp.md`, `competences-cm1.md`,
`squad/programme/francais/competences-cp.md`, `competences-cm1.md`), et l'etat reel du produit
face a chacune d'entre elles sur la branche `round-two`.

## Comment la lire

| Colonne | Sens |
|---|---|
| Matiere | La matiere du programme (mathematiques, francais, puis les suivantes a mesure que des referentiels existeront). |
| Niveau | CP (cycle 2) ou CM1 (cycle 3). |
| Domaine | Le regroupement tel qu'il apparait dans le referentiel source (ex. "Nombres et calculs"). |
| Competence | L'intitule de la competence, repris tel quel du referentiel. |
| Jeu(x) couvrant | Le ou les jeux (mecanique + contenu) qui la couvrent, ou `-` si aucun. |
| Mecanique | La mecanique de jeu reutilisable qui la sert (ex. "bandit manchot"), ou `-`. |
| Statut | `non couvert` / `en cours` / `couvert-valide` (voir ci-dessous). |

**Statuts** :

- `non couvert` : aucun jeu ne travaille cette competence.
- `en cours` : un jeu existe et cible cette competence, mais n'a pas encore recu le verdict
  `VALIDE` de `nadia-prof` sur `squad/conformite/FICHE-<jeu>.md` (Done technique atteint ou en
  cours, Done pedagogique pas encore prononce).
- `couvert-valide` : `nadia-prof` a rendu un verdict `VALIDE` sur la fiche de conformite
  correspondante. Ce statut n'est **jamais** attribue sur la seule foi d'une CI verte.

## Qui la met a jour, et quand

`camille-po` la met a jour :

- **au planning**, quand une story nouvelle ou modifiee cible une competence (colonnes Jeu(x)
  couvrant / Mecanique renseignees, statut passe a `en cours`) ;
- **a la review**, en fonction de la confirmation technique de `yanis-qa` et `theo-archi`
  (Done technique) et, seulement si `nadia-prof` a rendu un verdict `VALIDE`, en faisant passer
  la ligne a `couvert-valide`.

Un statut `couvert-valide` refuse ou remis en cause par `nadia-prof` (verdict `REFUSE` ou
`VALIDE SOUS RESERVE` avec reserves non levees) repasse a `en cours` et redevient du backlog.

## Etat de reconstruction (round-two)

Cette matrice est reconstruite **depuis zero** au sprint 0 de `round-two`. Aucune ligne n'herite
d'un statut de `main` : `main` n'est pas une reference, et rien n'y a ete rejoue ni revalide sur
cette branche.

Une precision s'impose sur une ligne : le referentiel
`squad/programme/francais/competences-cp.md` contient une annotation heritee ("couvert par le
jeu Bandit manchot - Les syllabes, voir FICHE-francais-cp-syllabes.md, s02") qui date de `main`.
Ce fichier de fiche, ce sprint `s02` et ce jeu n'existent pas sur `round-two`. Cette annotation
est ignoree ici ; la ligne correspondante demarre a `non couvert` comme toutes les autres, en
attendant qu'une story `round-two` la couvre reellement (voir `DEC-0001`).

## Mathematiques - CP (cycle 2)

Source : `squad/programme/mathematiques/competences-cp.md`.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| mathematiques | CP | Nombres et calculs | Denombrer, lire, ecrire et decomposer les nombres jusqu'a 100 | - | - | non couvert |
| mathematiques | CP | Nombres et calculs | Comparer et ranger des nombres jusqu'a 100 (<, >, =) | - | - | non couvert |
| mathematiques | CP | Nombres et calculs | Calcul mental - additions dans la limite de 10 (puis 20) | bandit-manchot-additions-cp | bandit manchot | en cours |
| mathematiques | CP | Nombres et calculs | Calcul mental - soustractions dans la limite de 10 | - | - | non couvert |
| mathematiques | CP | Nombres et calculs | Complements a 10 | - | - | non couvert |
| mathematiques | CP | Nombres et calculs | Resoudre des problemes simples d'addition/soustraction en situation (1 etape) | - | - | non couvert |
| mathematiques | CP | Nombres et calculs | Reconnaitre et utiliser la monnaie (pieces/billets simples) dans des problemes concrets | - | - | non couvert |
| mathematiques | CP | Grandeurs et mesures | Comparer des longueurs, des masses, des contenances (sans instrument gradue) | - | - | non couvert |
| mathematiques | CP | Grandeurs et mesures | Se reperer dans la journee, la semaine ; lire l'heure pleine sur une horloge | - | - | non couvert |
| mathematiques | CP | Espace et geometrie | Se reperer et decrire des deplacements sur un quadrillage simple | - | - | non couvert |
| mathematiques | CP | Espace et geometrie | Reconnaitre et nommer quelques figures planes (carre, rectangle, triangle, cercle) | - | - | non couvert |

Note sur la ligne `en cours` : le referentiel regroupe "jusqu'a 10 (puis 20)" en une seule
competence. Le sprint 0 ne couvre que la borne jusqu'a 10 ; la borne jusqu'a 20 reste a traiter
et ne suffit pas a elle seule a faire passer la ligne en `couvert-valide` meme si la partie
jusqu'a 10 est validee par Nadia.

## Mathematiques - CM1 (cycle 3)

Source : `squad/programme/mathematiques/competences-cm1.md`.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| mathematiques | CM1 | Nombres et calculs | Lire, ecrire, decomposer et comparer les nombres entiers jusqu'au million | - | - | non couvert |
| mathematiques | CM1 | Nombres et calculs | Calcul mental - tables de multiplication (jusqu'a x10), rappel automatise | - | - | non couvert |
| mathematiques | CM1 | Nombres et calculs | Calcul mental - multiplications et divisions simples (multiples de 10, 100) | - | - | non couvert |
| mathematiques | CM1 | Nombres et calculs | Effectuer une multiplication posee d'un nombre a 2-3 chiffres par un nombre a 1-2 chiffres | - | - | non couvert |
| mathematiques | CM1 | Nombres et calculs | Effectuer une division euclidienne simple (quotient et reste) par un diviseur a 1 chiffre | - | - | non couvert |
| mathematiques | CM1 | Nombres et calculs | Notion de fraction simple (unites fractionnaires, fractions inferieures a 1) et reperage sur une droite numerique | - | - | non couvert |
| mathematiques | CM1 | Nombres et calculs | Resoudre des problemes a plusieurs etapes mobilisant les 4 operations | - | - | non couvert |
| mathematiques | CM1 | Grandeurs et mesures | Convertir des unites de longueur, masse, contenance dans le systeme metrique | - | - | non couvert |
| mathematiques | CM1 | Grandeurs et mesures | Calculer un perimetre de figures usuelles (carre, rectangle) | - | - | non couvert |
| mathematiques | CM1 | Grandeurs et mesures | Resoudre des problemes de durees (calcul d'une duree, d'un horaire d'arrivee) | - | - | non couvert |
| mathematiques | CM1 | Espace et geometrie | Reconnaitre, nommer et tracer des figures planes (carre, rectangle, triangle, cercle) avec outils (regle, equerre, compas) | - | - | non couvert |
| mathematiques | CM1 | Espace et geometrie | Reconnaitre et utiliser la notion de symetrie axiale simple | - | - | non couvert |

## Francais - CP (cycle 2)

Source : `squad/programme/francais/competences-cp.md`.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| francais | CP | Langage oral et conscience phonologique | Denombrer les syllabes orales d'un mot (segmentation syllabique) | - | - | non couvert |
| francais | CP | Langage oral et conscience phonologique | Localiser une syllabe dans un mot (debut / milieu / fin) | - | - | non couvert |
| francais | CP | Langage oral et conscience phonologique | Identifier un son (phoneme) dans un mot | - | - | non couvert |
| francais | CP | Lecture et comprehension de l'ecrit | Associer les graphemes aux phonemes (voyelles, consonnes simples, puis digraphes ch, ou, on...) | - | - | non couvert |
| francais | CP | Lecture et comprehension de l'ecrit | Decoder un mot regulier inconnu par assemblage syllabique | - | - | non couvert |
| francais | CP | Lecture et comprehension de l'ecrit | Lire a voix haute un texte court et simple avec fluidite croissante | - | - | non couvert |
| francais | CP | Lecture et comprehension de l'ecrit | Comprendre un texte narratif tres court (questions explicites : qui, ou, quoi) | - | - | non couvert |
| francais | CP | Ecriture | Copier un mot, une phrase courte sans erreur, en respectant l'espacement | - | - | non couvert |
| francais | CP | Ecriture | Ecrire de maniere autonome un mot simple en respectant les correspondances grapheme/phoneme connues | - | - | non couvert |
| francais | CP | Orthographe et grammaire | Reconnaitre et utiliser les marques du pluriel les plus frequentes (-s) | - | - | non couvert |
| francais | CP | Orthographe et grammaire | Distinguer les principaux types de mots (nom, verbe) dans une phrase tres simple | - | - | non couvert |
| francais | CP | Vocabulaire | Utiliser des mots precis pour designer des objets, actions, sensations du quotidien | - | - | non couvert |

## Francais - CM1 (cycle 3)

Source : `squad/programme/francais/competences-cm1.md`.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| francais | CM1 | Lecture et comprehension de l'ecrit | Lire silencieusement un texte de longueur croissante et en restituer l'essentiel | - | - | non couvert |
| francais | CM1 | Lecture et comprehension de l'ecrit | Comprendre un texte narratif ou documentaire (questions explicites et implicites, inferences simples) | - | - | non couvert |
| francais | CM1 | Lecture et comprehension de l'ecrit | Reperer les substituts d'un personnage (pronoms, groupes nominaux) dans un texte | - | - | non couvert |
| francais | CM1 | Ecriture | Rediger un texte narratif court et coherent (quelques phrases, temps du recit respecte) | - | - | non couvert |
| francais | CM1 | Ecriture | Relire et corriger son texte a l'aide de reperes simples (majuscules, ponctuation) | - | - | non couvert |
| francais | CM1 | Orthographe et grammaire | Accorder le verbe avec son sujet (cas frequents, temps simples : present, futur, imparfait) | - | - | non couvert |
| francais | CM1 | Orthographe et grammaire | Accorder en genre et en nombre dans le groupe nominal (determinant/nom/adjectif) | - | - | non couvert |
| francais | CM1 | Orthographe et grammaire | Distinguer et utiliser correctement les principaux homophones grammaticaux (et/est, son/sont, a/a...) | - | - | non couvert |
| francais | CM1 | Orthographe et grammaire | Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif | - | - | non couvert |
| francais | CM1 | Vocabulaire | Utiliser des synonymes et antonymes pour preciser ou nuancer un propos | - | - | non couvert |
| francais | CM1 | Vocabulaire | Utiliser a bon escient des mots de liaison simples pour structurer un texte court | - | - | non couvert |

## Synthese au sprint 0

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 10 | 1 | 0 |
| mathematiques CM1 | 12 | 12 | 0 | 0 |
| francais CP | 12 | 12 | 0 | 0 |
| francais CM1 | 11 | 11 | 0 | 0 |
| **Total** | **46** | **45** | **1** | **0** |

Le trou le plus visible : **francais n'a encore aucune ligne `en cours`**. C'est un point a
traiter des le sprint 1 (voir `docs/backlog/backlog.md`, section "Backlog - sprints suivants") -
le calcul mental n'est un point de depart que si la matrice montre vite qu'il n'est pas une fin
en soi.
