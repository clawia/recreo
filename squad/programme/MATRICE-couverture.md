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

Une precision s'imposait sur une ligne, et elle est desormais corrigee : le referentiel
`squad/programme/francais/competences-cp.md` portait une annotation heritee ("couvert par le
jeu Bandit manchot - Les syllabes, voir FICHE-francais-cp-syllabes.md, s02") qui datait de `main`.
Ce fichier de fiche, ce sprint `s02` et ce jeu n'ont jamais existe sur `round-two`. Cette
annotation avait ete ignoree par cette matrice depuis le sprint 0 (la ligne demarrait a
`non couvert` comme toutes les autres, voir `DEC-0001`), mais elle est restee ecrite dans le
referentiel lui-meme jusqu'a ce que `nadia-prof` la signale nommement dans son audit du
2026-08-06 (`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`, "Suites hors de ma main") :
un referentiel qui declare couverte une competence qui ne l'est pas masque exactement le trou que
cette matrice existe pour rendre visible. L'annotation est retiree du referentiel a cette date ;
aucune autre annotation du meme genre n'a ete trouvee dans les trois autres fichiers de
`squad/programme/` apres relecture complete des quatre referentiels.

## Mathematiques - CP (cycle 2)

Source : `squad/programme/mathematiques/competences-cp.md`.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| mathematiques | CP | Nombres et calculs | Denombrer, lire, ecrire et decomposer les nombres jusqu'a 100 | - | - | non couvert |
| mathematiques | CP | Nombres et calculs | Comparer et ranger des nombres jusqu'a 100 (<, >, =) | - | - | non couvert |
| mathematiques | CP | Nombres et calculs | Calcul mental - additions dans la limite de 10 (puis 20) | bandit-manchot-additions-cp | bandit manchot | couvert-valide |
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

Etat au sprint 0, apres la porte pedagogique : `nadia-prof` a prononce **REFUSE** sur
`squad/conformite/FICHE-bandit-manchot-additions-cp.md` (trois motifs bloquants sur le texte lu
par l'enfant et sur un ecran d'erreur, sept reserves non bloquantes). Le contenu mathematique
lui-meme etait deja juge exact et n'a jamais eu besoin de correction (61 manches auditees au
sprint 0, zero faute arithmetique).

Etat au sprint 1, apres la reprise de la porte pedagogique (2026-08-05) : les trois motifs de
refus sont leves, `nadia-prof` prononce **VALIDE SOUS RESERVE, Done pedagogique ACCORDE**. La
ligne **reste neanmoins `en cours`**, et non `couvert-valide` : `nadia-prof` motive explicitement
cet ecart dans son verdict - sur 171 manches reellement jouees, 43,9 % portent sur les sommes 9 ou
10 et 7,6 % seulement sur une somme inferieure ou egale a 4, ce qui revient a travailler surtout le
bord superieur de l'intervalle plutot que "les additions dans la limite de 10" dans leur ensemble.
Elle pose une condition ecrite pour que cette ligne passe a `couvert-valide` : cette repartition
corrigee. Le sprint 2 n'a pas touche a ce point (chantier d'identite visuelle uniquement,
`DEC-0006`) ; la condition est restee sans story jusqu'a `DEC-0007` et la story S3-1 du sprint 3
(`docs/backlog/backlog.md`), qui l'instruisent enfin, avec le seuil chiffre que `nadia-prof` devra
verifier a l'audit.

**Etat au 2026-08-06, audit de cloture de s03** : `nadia-prof` a joue 2 600 manches reelles sur
l'increment corrige (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section 1.1). Les
sommes 9 et 10 representent 34,2 % des manches (contre 43,9 % avant correction), les sommes
inferieures a 5 en representent 18,0 % (contre 7,6 %), les operandes nuls 13,7 %, et les 65 couples
valides sortent tous. **Sa condition est levee et la ligne passe a `couvert-valide`** - c'est la
premiere ligne de la matrice a atteindre ce statut. Reserve attachee, qui n'est pas une condition :
la mention "(puis 20)" de la competence reste hors du perimetre de ce jeu, dont la borne est 10 ;
si "puis 20" doit etre couvert, ce sera une autre story et un nouvel audit.

L'increment est suivi (Done pedagogique, pas la ligne de matrice) dans
`squad/validation/FILE-ATTENTE.md`.

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
| francais | CP | Lecture et comprehension de l'ecrit | Associer les graphemes aux phonemes (voyelles, consonnes simples, puis digraphes ch, ou, on...) | bandit-manchot-le-bon-son-cp | bandit manchot | en cours |
| francais | CP | Lecture et comprehension de l'ecrit | Decoder un mot regulier inconnu par assemblage syllabique | - | - | non couvert |
| francais | CP | Lecture et comprehension de l'ecrit | Lire a voix haute un texte court et simple avec fluidite croissante | - | - | non couvert |
| francais | CP | Lecture et comprehension de l'ecrit | Comprendre un texte narratif tres court (questions explicites : qui, ou, quoi) | - | - | non couvert |
| francais | CP | Ecriture | Copier un mot, une phrase courte sans erreur, en respectant l'espacement | - | - | non couvert |
| francais | CP | Ecriture | Ecrire de maniere autonome un mot simple en respectant les correspondances grapheme/phoneme connues | - | - | non couvert |
| francais | CP | Orthographe et grammaire | Reconnaitre et utiliser les marques du pluriel les plus frequentes (-s) | - | - | non couvert |
| francais | CP | Orthographe et grammaire | Distinguer les principaux types de mots (nom, verbe) dans une phrase tres simple | - | - | non couvert |
| francais | CP | Vocabulaire | Utiliser des mots precis pour designer des objets, actions, sensations du quotidien | - | - | non couvert |

Note sur la ligne `en cours` : `DEC-0008-la-couverture-reprend-francais-cp-et-cm1.md` ouvre cette
competence au sprint 4, sur la matiere premiere `contenu-repris/mots/cp-le-bon-son.yml` (story
S4-2, `docs/backlog/backlog.md`).

**Etat au 2026-08-06, premier audit** : `nadia-prof` prononce **REFUSE**
(`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`) sur cinq motifs cumules : une consigne
qui annonce un son qui n'est jamais joue ; des crochets phonetiques `[an]` montres a l'enfant ; 23
items sur 24 resolubles par simple appariement visuel des lettres, sans passer par le phoneme ;
trois items qui admettent deux reponses vraies mais n'en acceptent qu'une ; et aucune aide
disponible dans ce jeu, motif qu'elle qualifie de structurel plutot que d'oubli de contenu (un
paquet `type: question` ne peut porter d'indice, contrairement a `type: additions-bornees`). La
ligne **reste `en cours`**. Le jeu est retire du catalogue jouable en attendant sa correction
(`DEC-0009-retrait-le-bon-son-du-catalogue.md`) ; stories de correction S5-2 a S5-5 de
`docs/backlog/backlog.md`.

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
| francais | CM1 | Orthographe et grammaire | Distinguer et utiliser correctement les principaux homophones grammaticaux (et/est, son/sont, a/à...) | bandit-manchot-homophones-cm1 | bandit manchot | en cours |
| francais | CM1 | Orthographe et grammaire | Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif | - | - | non couvert |
| francais | CM1 | Vocabulaire | Utiliser des synonymes et antonymes pour preciser ou nuancer un propos | - | - | non couvert |
| francais | CM1 | Vocabulaire | Utiliser a bon escient des mots de liaison simples pour structurer un texte court | - | - | non couvert |

Note sur la ligne `en cours` : `DEC-0008-la-couverture-reprend-francais-cp-et-cm1.md` ouvre cette
competence au sprint 4, sur la matiere premiere `contenu-repris/mots/cm1-homophones.yml` (story
S4-1, `docs/backlog/backlog.md`) - premiere ligne de matrice couverte pour le CM1, tous jeux
confondus.

**Etat au 2026-08-06, premier audit** : `nadia-prof` prononce **VALIDE SOUS RESERVE**, Done
pedagogique **accorde** (`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`) : le contenu
est exact item par item et le rattachement au referentiel est juste. La ligne **reste neanmoins
`en cours`**, pas `couvert-valide` : elle motive l'ecart elle-meme - la competence vise "les
**principaux** homophones grammaticaux", le paquet en couvre cinq (a/a, et/est, on/ont, son/sont,
ou/ou), il en manque cinq parmi les plus courants (ce/se, ces/ses, la/la/l'a, mais/mes, peu/peut).
Sa condition ecrite pour `couvert-valide` : ces cinq couples ajoutes **et**, dans le meme geste,
des phrases qui obligent l'enfant a regarder ailleurs que juste avant le trou (profondeur CM1,
pas CE1/CE2) - story S5-6 de `docs/backlog/backlog.md`. Reserve distincte, non bloquante pour ce
statut mais nommee : dix items pour dix manches, donc aucune variete entre deux parties - story
S5-7.

## Synthese au sprint 0, mise a jour a l'ouverture du sprint 4

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 10 | 1 | 0 |
| mathematiques CM1 | 12 | 12 | 0 | 0 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 10 | 1 | 0 |
| **Total** | **46** | **43** | **3** | **0** |

Ces chiffres etaient inchanges depuis le sprint 0 apres trois sprints (s00 a s02) qui ont produit
un seul jeu sur une seule competence, encore `en cours` a ce jour - voir la note sous le tableau
mathematiques CP ci-dessus. `DEC-0007` avait tranche que s03 completerait cette ligne
(repartition des sommes) plutot que d'en ouvrir une nouvelle ; l'increment corrige est fusionne,
en attente de l'audit de cloture de `nadia-prof`. **`DEC-0008` renverse cette priorisation sur
demande explicite de l'utilisatrice** : le sprint 4 ouvre deux lignes de plus, l'une en CP et
l'une en CM1, toutes deux en francais (stories S4-1, S4-2, `docs/backlog/backlog.md`). Aucune des
trois lignes `en cours` ne passe a `couvert-valide` par cette seule ouverture : ce statut n'est
prononce que par `nadia-prof`, apres audit reel de chaque increment.

Le trou qui restait le plus visible - **francais, qui n'avait encore aucune ligne `en cours`** -
est celui que `DEC-0008` traite. Le CM1 sort egalement de son etat a zero ligne, tous jeux
confondus, avec la ligne homophones grammaticaux.

## Synthese apres les verdicts du 2026-08-06 (cloture de s03, premier audit de s04)

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 10 | 0 | 1 |
| mathematiques CM1 | 12 | 12 | 0 | 0 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 10 | 1 | 0 |
| **Total** | **46** | **43** | **2** | **1** |

`nadia-prof` a rendu son verdict le 2026-08-06 sur les trois increments soumis
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, `FICHE-bandit-manchot-le-bon-son-cp.md`,
`FICHE-bandit-manchot-homophones-cm1.md`) :

- **Mathematiques CP** obtient la premiere ligne `couvert-valide` du produit : la condition posee
  en s01 et reportee deux fois est levee.
- **Francais CM1** (homophones grammaticaux) obtient un Done pedagogique accorde (`VALIDE SOUS
  RESERVE`), mais la ligne reste `en cours` : cinq couples d'homophones courants manquent a "les
  principaux" (story S5-6, `docs/backlog/backlog.md`).
- **Francais CP** (graphemes/phonemes) est **REFUSE** sur cinq motifs, dont un structurel (un
  paquet `type: question` ne peut porter d'indice) ; la ligne reste `en cours`, et le jeu est
  retire du catalogue jouable jusqu'a correction (`DEC-0009-retrait-le-bon-son-du-catalogue.md`,
  stories S5-2 a S5-5).

Le trou qui demeure : le produit compte desormais une ligne `couvert-valide` sur 46, deux lignes
`en cours` dont l'une correspond a un jeu refuse et retire du catalogue, et 43 lignes `non
couvert`.

## Perimetre de s05 (2026-08-06)

`DEC-0011-perimetre-s05-reste-correctif.md` : le sprint 5 ne cree aucune ligne nouvelle. Les seuls
mouvements possibles ce sprint portent sur les trois lignes deja engagees, via l'audit des
corrections `docs/backlog/backlog.md` S5-2 a S5-7. Le motif n'est pas une regle generale (voir
`DEC-0011`, qui se distingue explicitement de `DEC-0007`) : aucun candidat de `contenu-repris/` ne
correspond integralement a une ligne non encore ouverte sans reproduire le defaut de couverture
partielle deja sanctionne sur les homophones CM1. L'ouverture suivante est nommee, pas indefinie :
sprint 6, sur `textes-trous/cm1-accords.yml` ou `textes-trous/cm1-conjugaison.yml`, deja
identifies par `DEC-0008`.
