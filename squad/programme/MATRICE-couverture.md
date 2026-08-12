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
| mathematiques | CP | Nombres et calculs | Comparer et ranger des nombres jusqu'a 100 (<, >, =) | bandit-manchot-comparer-nombres-cp | bandit manchot | en cours |
| mathematiques | CP | Nombres et calculs | Calcul mental - additions dans la limite de 10 (puis 20) | bandit-manchot-additions-cp | bandit manchot | couvert-valide |
| mathematiques | CP | Nombres et calculs | Calcul mental - soustractions dans la limite de 10 | bandit-manchot-soustractions-cp | bandit manchot | en cours |
| mathematiques | CP | Nombres et calculs | Complements a 10 | bandit-manchot-complements-10-cp | bandit manchot | couvert-valide |
| mathematiques | CP | Nombres et calculs | Resoudre des problemes simples d'addition/soustraction en situation (1 etape) | bandit-manchot-problemes-additifs-cp | bandit manchot | en cours |
| mathematiques | CP | Nombres et calculs | Reconnaitre et utiliser la monnaie (pieces/billets simples) dans des problemes concrets | - | - | non couvert |
| mathematiques | CP | Grandeurs et mesures | Comparer des longueurs, des masses, des contenances (sans instrument gradue) | - | - | non couvert |
| mathematiques | CP | Grandeurs et mesures | Se reperer dans la journee, la semaine ; lire l'heure pleine sur une horloge | - | - | non couvert |
| mathematiques | CP | Espace et geometrie | Se reperer et decrire des deplacements sur un quadrillage simple | - | - | non couvert |
| mathematiques | CP | Espace et geometrie | Reconnaitre et nommer quelques figures planes (carre, rectangle, triangle, cercle) | - | - | non couvert |

Note sur la ligne `en cours` (comparer et ranger) : `DEC-0019-perimetre-sprint-9-quatre-lignes-
textuelles-de-mathematiques-cp-denombrement-et-monnaie-differes.md` ouvre cette ligne au sprint 9
sur la seule comparaison de deux nombres par un signe (story S9-3, `docs/backlog/backlog.md`). Le
"ranger" nomme par cette meme competence (ordonner une suite de trois nombres ou plus) reste hors
du perimetre de ce jeu et ne suffit donc pas, a lui seul, a faire passer cette ligne en
`couvert-valide` meme si "comparer" est valide par `nadia-prof`.

Note sur les lignes `en cours` (soustractions, complements a 10, problemes) : `DEC-0019` les ouvre
au sprint 9 sur `docs/backlog/backlog.md`, stories S9-1, S9-2, S9-4, chacune sur un contenu neuf en
`type: question`, sans matiere premiere de `contenu-repris/` identifiee pour ces trois lignes.

Precision sur la ligne "complements a 10" (S9-2) : cette ligne a ete bloquee en planning, puis
debloquee par `DEC-0020-s9-2-debloquee-nombre-de-manches-abaisse-pour-les-complements-a-10.md`.
L'espace de la forme canonique de cette competence est fini a onze items au maximum (`ADR-0032`),
contre dix manches declarees pour les trois autres jeux du sprint : le jeu qui la couvre declare
`nombreDeManches: 7` au lieu de 10, une asymetrie de duree de partie assumee par cette decision
plutot qu'un elargissement des formes d'items, qui aurait ete une decision pedagogique.

Note sur les deux lignes qui restent `non couvert` (denombrer, monnaie) : `DEC-0019` les differe
explicitement plutot que de les laisser en silence, sur un motif corrige le 2026-08-12 (amendement
de `DEC-0019`, suite a l'avis de faisabilite de `theo-archi`) :

- **Denombrer** : un pictogramme-caractere (une face de de, un glyphe repete) est techniquement
  portable dans une chaine YAML ordinaire, sans code ni gabarit neuf, et `DEC-0002` place ce
  registre hors de la regle de redaction du depot. Ce qui reste non tranche, et qui garde cette
  ligne `non couvert` ce sprint, est pedagogique : la recevabilite d'un tel pictogramme pour tester
  reellement "denombrer" et la fiabilite de son rendu (un glyphe qui varie selon la police ou
  l'appareil devient une reponse fausse, pas seulement un defaut visuel) sont des jugements de
  `nadia-prof`, non encore rendus.
- **Monnaie** : le verbe "reconnaitre" designe une reconnaissance visuelle qu'aucun texte seul ne
  peut porter. Le verbe "utiliser", lui, est entierement textuel et prouve par du contenu deja joue
  en production sur `main` (`contenu-repris/quiz/cp-monnaie.yml`). Couvrir "utiliser" seul
  reproduirait neanmoins le motif de couverture partielle deja sanctionne sur ce produit (additions
  "jusqu'a 10 (puis 20)", homophones "principaux", conjugaison "un temps sur trois") : ce jeu
  n'exercerait jamais "reconnaitre". La ligne reste donc `non couvert` dans son ensemble.

Voir `DEC-0019` (decision d'origine et amendement du 2026-08-12) pour le detail complet, y compris
la revue de `contenu-repris/quiz/cp-monnaie.yml`, matiere premiere conservee pour une story future.

Note sur la ligne `en cours` (calcul mental additions) : le referentiel regroupe "jusqu'a 10 (puis
20)" en une seule competence. Le sprint 0 ne couvre que la borne jusqu'a 10 ; la borne jusqu'a 20
reste a traiter et ne suffit pas a elle seule a faire passer la ligne en `couvert-valide` meme si
la partie jusqu'a 10 est validee par Nadia.

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

**Etat au 2026-08-10, audit de cloture de s06** : `nadia-prof` prononce **VALIDE SOUS RESERVE**,
Done pedagogique accorde (`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`, verdict du
2026-08-10). **Le REFUSE du 2026-08-06 est leve sur ses cinq motifs**, apres recomposition
integrale du paquet (S6-6, S6-7) autour des seules trois voyelles nasales `an`, `on`, `in`. La
ligne **reste `en cours`** : la competence du referentiel nomme aussi les voyelles simples, les
consonnes simples et les digraphes `ch`, `ou`, `oi`, que ce paquet ne travaille pas et ne peut pas
travailler sans qu'un son soit reellement joue (`nadia-prof` ferme elle-meme cette piste le
2026-08-06 : "sans son, un digraphe transparent ne peut donner lieu qu'a une recherche de
lettres"). Sa condition ecrite pour `couvert-valide` (meme fiche, verdict du 2026-08-10, section
6) : les voyelles et consonnes simples couvertes par ce jeu ou un autre ; `ou`, `oi` et `ch`
couverts par un jeu qui emet un son ; les trois nasales gardent les proprietes deja acquises. Le
jeu peut rejoindre le catalogue (`DEC-0009` est levee de fait par ce verdict, `sofia-sm` fusionne
l'entree de catalogue).

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
| francais | CM1 | Orthographe et grammaire | Distinguer et utiliser correctement les principaux homophones grammaticaux (et/est, son/sont, a/à...) | bandit-manchot-homophones-cm1 | bandit manchot | couvert-valide |
| francais | CM1 | Orthographe et grammaire | Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif | bandit-manchot-conjugaison-present-cm1 | bandit manchot | couvert-valide |
| francais | CM1 | Vocabulaire | Utiliser des synonymes et antonymes pour preciser ou nuancer un propos | - | - | non couvert |
| francais | CM1 | Vocabulaire | Utiliser a bon escient des mots de liaison simples pour structurer un texte court | - | - | non couvert |

Note sur la ligne `en cours` (homophones) : `DEC-0008-la-couverture-reprend-francais-cp-et-cm1.md`
ouvre cette competence au sprint 4, sur la matiere premiere `contenu-repris/mots/cm1-homophones.yml`
(story S4-1, `docs/backlog/backlog.md`) - premiere ligne de matrice couverte pour le CM1, tous jeux
confondus.

Note sur la ligne `en cours` (conjugaison), ouverte au sprint 6 : `DEC-0013-perimetre-sprint-6.md`
ouvre cette competence sur `textes-trous/cm1-conjugaison.yml` (story S6-3,
`docs/backlog/backlog.md`), **sur le present de l'indicatif uniquement**. Le futur et l'imparfait,
les deux autres temps nommes par cette meme ligne, restent explicitement `non couvert` par ce
paquet : la part non couverte est assumee des l'ouverture, pas decouverte a l'audit (voir `DEC-0013`
pour le motif du choix entre les deux candidats de `textes-trous/`).

**Etat au 2026-08-06, premier audit** : `nadia-prof` prononce **VALIDE SOUS RESERVE**, Done
pedagogique **accorde** (`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`) : le contenu
est exact item par item et le rattachement au referentiel est juste. La ligne **reste neanmoins
`en cours`**, pas `couvert-valide` : elle motive l'ecart elle-meme - la competence vise "les
**principaux** homophones grammaticaux", le paquet en couvre cinq (a/à, et/est, on/ont, son/sont,
ou/où), il en manque cinq parmi les plus courants (ce/se, ces/ses, la/là/l'a, mais/mes, peu/peut).
Sa condition ecrite pour `couvert-valide` : ces cinq couples ajoutes **et**, dans le meme geste,
des phrases qui obligent l'enfant a regarder ailleurs que juste avant le trou (profondeur CM1,
pas CE1/CE2) - story S5-6 de `docs/backlog/backlog.md`. Reserve distincte, non bloquante pour ce
statut mais nommee : dix items pour dix manches, donc aucune variete entre deux parties - story
S5-7.

**Etat au 2026-08-07, audit de l'increment de s05** : `nadia-prof` reconduit et elargit le Done
pedagogique (**VALIDE SOUS RESERVE**) aux douze items neufs et aux vingt-deux indices
(`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`, verdict du 2026-08-07). **La condition
n'est PAS levee, la ligne reste `en cours`.** Quatre des six points de sa condition sont tenus ; il
manque deux items neufs de plus dont le mot decisif n'est pas voisin du trou, et la correction d'un
indice qui instruit une substitution qui n'est pas du francais - chiffres exacts a la section 3 du
meme verdict, repris sans les redurcir ni les affaiblir dans les stories S6-4 et S6-5
(`docs/backlog/backlog.md`).

**Etat au 2026-08-10, audit de cloture de s06** : `nadia-prof` prononce **VALIDE**, Done
pedagogique accorde sans reserve bloquante, sur le paquet porte a 24 items par S6-4 et S6-5
(`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`, verdict du 2026-08-10). **Sa condition
en six points du 2026-08-06 est levee : la ligne passe a `couvert-valide`.** C'est la deuxieme
ligne du produit a atteindre ce statut, et la premiere en francais. Le perimetre exact de ce
`couvert-valide` est nomme par elle-meme pour ne pas etre confondu avec une couverture totale :
dix familles d'homophones (`a/à`, `et/est`, `son/sont`, `on/ont`, `ou/où`, `ce/se`, `ces/ses`,
`la/là/l'a`, `mais/mes`, `peu/peut`), chacune travaillee dans les deux sens, avec une profondeur de
CM1 sur la moitie des items neufs. **`c'est`/`s'est` (et `sait`/`sais`) n'y est pas** : elle
satisfait ses quatre criteres de "principal" et c'est l'une des fautes les plus frequentes au CM1,
mais `nadia-prof` refuse de grossir une condition deja engagee par l'equipe - elle la nomme comme
premier candidat d'une story ulterieure (voir "Backlog - sprints suivants").

**Etat au 2026-08-10, premier audit de la ligne conjugaison** : `nadia-prof` prononce **REFUSE**
sur `bandit-manchot-conjugaison-present-cm1`
(`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict du 2026-08-10). Motif
unique et suffisant : cinq des douze indices du paquet enoncent une regle de conjugaison fausse, et
trois d'entre elles sont dementies par un autre item du meme paquet. Les douze enonces eux-memes
sont exacts et bien equilibres (les six personnes grammaticales representees deux fois chacune,
les trois groupes travailles). La ligne **reste `en cours`** - elle ne pouvait de toute facon pas
atteindre `couvert-valide` sur cet increment, qui ne couvre qu'un temps sur trois (`DEC-0013`). Le
jeu est retire du catalogue jouable en attendant sa correction
(`DEC-0014-retrait-le-bon-verbe-du-catalogue.md`).

**Etat au 2026-08-10, second audit, increment livre en s07** : `nadia-prof` prononce **VALIDE SOUS
RESERVE**, Done pedagogique **accorde**, sur le commit `10dd74d` (`HEAD` de `round-two`)
(`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict "second audit,
increment livre en s07"). **Le REFUSE du 2026-08-10 est leve** : les douze indices sont desormais
vrais et aucun ne dementit plus un autre item du meme paquet, verifie un par un et vu en jeu. La
ligne **reste `en cours`**, et le second audit ne change rien a cet etat : la barre qu'elle a
ecrite elle-meme pour `couvert-valide` (ouvrir le futur et l'imparfait, voir le refus du
2026-08-10, section 5) est inchangee et non atteinte, ce paquet ne couvrant qu'un temps sur trois.
Ce Done pedagogique leve, a lui seul, ne remet pas le jeu au catalogue : voir `DEC-0017` pour la
decision de remise, qui verifie en plus la seconde condition posee par `DEC-0015` (quatrieme
amendement) sur le repli de mise en page.

**Etat au 2026-08-11, troisieme audit, sur l'increment livre en s08 (S8-4, commit `533967d`)** :
`nadia-prof` prononce **REFUSE**
(`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict du 2026-08-11). Ce
refus porte sur l'activation de "Le bon verbe" au catalogue (deux propositions plus larges que
leur colonne de 152 px a 360 px, dans le cabinet livre le meme jour), pas sur le contenu : **sa
barre fermee de six points pour l'ouverture du futur et de l'imparfait est LEVEE**, verifiee point
par point, et le contenu de S8-4 est valide. `DEC-0018` tranche que le jeu reste au catalogue
pendant la correction de cette disposition (story `S8-5`), a la difference du precedent
`DEC-0014`, et fixe le denouement des deux issues possibles du prochain audit.

**La ligne restait `en cours` a ce stade.** La barre en six points levee autorise le passage a
`couvert-valide`, mais je choisis de ne pas le faire tant que le verdict global inscrit sur cette
fiche, pour ce commit, est `REFUSE` : voir `DEC-0018`, section "Decision" point 4, pour le motif
complet. La ligne passera a `couvert-valide` au premier verdict qui n'est pas un `REFUSE` sur
l'increment corrige de `S8-5`, sans que la barre en six points soit rouverte a cette occasion ; si
ce verdict est de nouveau `REFUSE`, la ligne reste `en cours` et le jeu sort du catalogue
(`DEC-0018`, section "Decision" point 3).

**Etat au 2026-08-11, quatrieme audit, sur l'increment corrige de S8-5 (commit `bd9a8f0`)** :
`nadia-prof` prononce **VALIDE SOUS RESERVE**, Done pedagogique **accorde**
(`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict du 2026-08-11,
"quatrieme audit"). **Le REFUSE du 2026-08-11 (troisieme audit) est LEVE** : son motif unique -
`mangeons` a 156,02 px de glyphes dans une boite de 152,00 px, ecrit sur la paroi du cabinet - est
ferme, mesure sur 880 propositions dans 220 etats et 22 parties reelles, minimum du paquet
2,86 px sur `mangeons`, zero proposition qui deborde et zero sous 2,0 px d'air. **La ligne passe
de `en cours` a `couvert-valide`**, conformement a la condition posee par `DEC-0018` section 4 :
le premier verdict sur l'increment corrige de `S8-5` qui n'est pas un `REFUSE`. La barre en six
points de S8-4, levee depuis le troisieme audit, n'est pas rouverte ni reauditee a cette occasion.

Le verdict est **sous reserve**, pour une seule raison que `nadia-prof` nomme explicitement comme
n'etant pas un motif : le defaut de pli (24 items sur 25 avec l'indice affiche), regi par
`DEC-0016` amende, traite comme story commune aux deux jeux concernes et non comme une condition
de cette ligne. Deux reserves non bloquantes, distinctes du pli, sont versees au backlog
(`docs/backlog/backlog.md`, section "Autres intentions") : l'inconfort des quatre colonnes de ce
paquet a 360 px (aucune taille acceptable par `nadia-prof` ne le rend confortable, 2,86 px d'air
reel contre 20 px demandes par sa propre definition d'une colonne lisible) et le risque qu'un
futur paquet a quatre propositions herite de 30 px sans qu'aucune ligne de test ne le signale.

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

## Perimetre de s06 (2026-08-07)

`DEC-0013-perimetre-sprint-6.md` : le sprint 6 ouvre une ligne de plus, sur `textes-trous/
cm1-conjugaison.yml` (story S6-3, `docs/backlog/backlog.md`) - **la ligne
`francais | CM1 | Orthographe et grammaire | Conjuguer les verbes frequents au present, futur et
imparfait de l'indicatif` passe `en cours` par ce planning**, jeu et mecanique renseignes
ci-dessus. Ce paquet ne couvre que le present de l'indicatif ; le futur et l'imparfait restent
`non couvert` par construction, assumes des l'ouverture (`DEC-0013`, qui motive ce choix face a
l'autre candidat, `textes-trous/cm1-accords.yml`, non retenu ce sprint). **Aucune ligne ne passe a
`couvert-valide` par ce planning** : ce statut n'est prononce que par `nadia-prof`, apres audit
reel de l'increment - y compris pour la ligne homophones CM1, dont les deux points restants
(S6-4, S6-5) visent a lever la condition deja ecrite, sans prejuger du verdict.

Le sprint 6 porte par ailleurs le rhabillage du gabarit (S6-1, S6-2, deja livre et audite en s05,
`DEC-0012` amendee en consequence), la sortie de quarantaine de "Le bon son" (S6-6, S6-7, lot
indivisible) et une dette de forme et d'outillage (S6-8 a S6-14) : aucune de ces stories ne porte
de competence programme, elles n'ont donc pas d'impact sur cette matrice.

## Synthese apres l'ouverture de s06 (2026-08-07)

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 10 | 0 | 1 |
| mathematiques CM1 | 12 | 12 | 0 | 0 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 2 | 0 |
| **Total** | **46** | **42** | **3** | **1** |

Une ligne de plus qu'a la cloture de s05 : francais CM1 passe de une a deux lignes `en cours`
(homophones grammaticaux, conjugaison au present). Aucune ligne ne passe `couvert-valide` par ce
planning ; ce mouvement attend l'audit de `nadia-prof` sur les increments de s06.

## Synthese apres les verdicts du sprint 6 (2026-08-10)

`nadia-prof` a rendu trois verdicts a la cloture de s06, sur les fiches
`FICHE-bandit-manchot-conjugaison-present-cm1.md`, `FICHE-bandit-manchot-homophones-cm1.md` et
`FICHE-bandit-manchot-le-bon-son-cp.md` :

- **Francais CM1 (homophones grammaticaux)** obtient **`couvert-valide`** : sa condition en six
  points du 2026-08-06 est levee. Deuxieme ligne du produit a atteindre ce statut, premiere en
  francais.
- **Francais CM1 (conjugaison au present)** est **REFUSE** : cinq indices sur douze enoncent une
  regle de conjugaison fausse, trois dementies par un autre item du meme paquet. La ligne reste
  `en cours`. Le jeu, jouable au moment de l'audit, est retire du catalogue
  (`DEC-0014-retrait-le-bon-verbe-du-catalogue.md`). **Ce REFUSE est leve au sprint 7** : voir la
  section "Synthese apres les verdicts du sprint 7" ci-dessous.
- **Francais CP (graphemes/phonemes, "Le bon son")** obtient **VALIDE SOUS RESERVE** : le REFUSE du
  2026-08-06 est leve sur ses cinq motifs, apres recomposition complete du paquet autour des trois
  voyelles nasales. La ligne reste `en cours` : la competence nomme aussi les voyelles et consonnes
  simples et les digraphes `ch`, `ou`, `oi`, qu'aucun jeu ne couvre encore.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 10 | 0 | 1 |
| mathematiques CM1 | 12 | 12 | 0 | 0 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 1 | 1 |
| **Total** | **46** | **42** | **2** | **2** |

Le produit compte desormais deux lignes `couvert-valide` sur 46 (contre une a la cloture de s05),
deux lignes `en cours` dont l'une correspond a un jeu refuse et retire du catalogue
(conjugaison CM1), et 42 lignes `non couvert`, chiffre inchange : aucune ligne nouvelle n'a ete
ouverte ce sprint, le mouvement porte uniquement sur les deux lignes deja engagees.

## Synthese apres les verdicts du sprint 7 (2026-08-10)

Le sprint 7 (`DEC-0015`) est transverse - la mecanique de machine a sous, pas un contenu
pedagogique - et n'ouvre aucune ligne nouvelle. Il porte neanmoins un second audit sur une ligne
deja engagee : `nadia-prof` a rejoue `bandit-manchot-conjugaison-present-cm1` sur le commit
`10dd74d` (`HEAD` de `round-two`) apres la correction de ses cinq indices fautifs, sur une branche
jetable qui n'ajoutait que l'entree de catalogue retiree par `DEC-0014` (le jeu reste hors
catalogue, cet audit ne l'y remet pas) (`squad/conformite/FICHE-bandit-manchot-conjugaison-
present-cm1.md`, verdict "second audit, increment livre en s07").

- **Francais CM1 (conjugaison au present)** : le REFUSE du 2026-08-10 (premier audit, cinq indices
  fautifs) est **leve**. `nadia-prof` prononce **VALIDE SOUS RESERVE**, Done pedagogique
  **accorde**. La ligne **reste `en cours`** : elle ne pouvait de toute facon pas atteindre
  `couvert-valide` sur cet increment (un temps sur trois, `DEC-0013`), et la barre qu'elle a
  ecrite elle-meme pour ce statut (ouvrir le futur et l'imparfait) est inchangee et non atteinte.
  Ce chantier fait l'objet de la story de chantier 4 de `DEC-0017`.

Ce second audit ne deplace aucun chiffre du tableau de synthese ci-dessus : la ligne reste
`en cours` avant comme apres, et aucune autre ligne n'est touchee par le sprint 7. La decision de
remettre ce jeu au catalogue jouable, qui suppose en plus la seconde condition de `DEC-0015`
(quatrieme amendement) sur le repli de mise en page, est tranchee par `DEC-0017` et non par cette
matrice, qui ne porte que l'etat des competences.

## Synthese apres les verdicts du sprint 8 (2026-08-11)

`DEC-0017` ouvre au sprint 8 l'extension de "Le bon verbe" au futur et a l'imparfait (S8-4), sur
la barre en six points close par `nadia-prof` le 2026-08-10. Cette extension a traverse quatre
audits sur la meme fiche (`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`) :
levee de la barre en six points au troisieme audit, puis REFUSE sur un motif distinct (largeur des
colonnes du cabinet a 360 px), corrige par S8-5 (`DEC-0018`), puis quatrieme audit sur l'increment
corrige.

- **Francais CM1 (conjugaison au present, futur, imparfait)** obtient **`couvert-valide`** :
  `nadia-prof` prononce **VALIDE SOUS RESERVE**, Done pedagogique accorde, sur le quatrieme audit
  (commit `bd9a8f0`). Sa barre en six points, levee au troisieme audit et non rouverte, est la
  seule condition qui gouvernait ce statut ; elle est tenue. La reserve qui rend ce Done "sous
  reserve" - le defaut de pli - n'est pas une condition de cette ligne (voir la note detaillee
  ci-dessus, "Etat au 2026-08-11, quatrieme audit").

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 10 | 0 | 1 |
| mathematiques CM1 | 12 | 12 | 0 | 0 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 0 | 2 |
| **Total** | **46** | **42** | **1** | **3** |

Le produit compte desormais trois lignes `couvert-valide` sur 46 (contre deux a la cloture de s06
et s07), une seule ligne `en cours` (francais CP, graphemes/phonemes), et 42 lignes `non couvert`,
chiffre inchange : `DEC-0017` gele l'objectif du sprint et aucune ligne nouvelle n'est ouverte.

## Perimetre de s09 (2026-08-12)

`DEC-0019-perimetre-sprint-9-quatre-lignes-textuelles-de-mathematiques-cp-denombrement-et-monnaie-
differes.md` : sur demande de l'utilisatrice, le sprint 9 attaque mathematiques CP, domaine
"Nombres et calculs", sous un gel de tout travail de rendu (aucune mesure, aucun garde-fou CSS,
aucun arbitrage visuel). Quatre lignes entrent au sprint, toutes exprimables dans l'enveloppe
`type: question` existante sans code neuf (`ADR-0007`) - comparer deux nombres par un signe (S9-3,
partielle, "ranger" exclu), soustractions dans la limite de 10 (S9-1), complements a 10 (S9-2), et
resoudre un probleme simple d'addition ou de soustraction (S9-4). **Ces quatre lignes passent
`en cours` par ce planning**, jeu et mecanique renseignes dans le tableau mathematiques CP
ci-dessus. Deux lignes restent `non couvert`, differees avec un motif ecrit (denombrement,
monnaie) : chacune nomme un verbe de reconnaissance visuelle que le gel de rendu met hors de
portee ce sprint. **Aucune ligne ne passe a `couvert-valide` par ce planning** : ce statut n'est
prononce que par `nadia-prof`, apres audit reel de chaque increment, sur les quatre fiches de
conformite ouvertes dans le meme geste que `DEC-0019`.

**Correction du 2026-08-12, apres l'avis de faisabilite de `theo-archi`.** Deux arbitrages
supplementaires, tous deux traites sous `DEC-0019` (amendee) et `DEC-0020` :

- la story `S9-2` (complements a 10), bloquee au planning par un obstacle technique (`ADR-0023`
  contre un espace d'items fini), est debloquee par `DEC-0020` : le jeu qui la couvre declare
  `nombreDeManches: 7` plutot que 10, voir la note sous le tableau mathematiques CP ci-dessus ;
- le motif "reconnaissance visuelle qu'aucun texte seul ne peut porter", ecrit ci-dessus pour les
  deux lignes differees, s'est revele imprecis pour la ligne monnaie (son verbe "utiliser" est
  entierement textuel, prouve par du contenu deja joue en production) et incomplet pour la ligne
  denombrement (un pictogramme-caractere y est techniquement portable) - corrige dans la note sous
  le tableau mathematiques CP ci-dessus. Les deux lignes restent `non couvert`, sur un motif
  desormais pedagogique et non plus technique.

Aucun de ces deux points ne deplace un chiffre du tableau de synthese ci-dessous.

## Synthese apres l'ouverture de s09 (2026-08-12)

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 4 | 1 |
| mathematiques CM1 | 12 | 12 | 0 | 0 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 0 | 2 |
| **Total** | **46** | **38** | **5** | **3** |

Quatre lignes de plus qu'a la cloture de s08 passent `en cours` (toutes en mathematiques CP), pour
un total de cinq lignes `en cours` sur le produit. Aucune ligne ne passe `couvert-valide` par cette
seule ouverture ; ce mouvement attend l'audit de `nadia-prof` sur les increments du sprint 9.

## Synthese apres les verdicts du sprint 9 (2026-08-12)

`nadia-prof` a rendu quatre verdicts sur les quatre fiches ouvertes par `DEC-0019`
(`squad/conformite/FICHE-bandit-manchot-complements-10-cp.md`,
`FICHE-bandit-manchot-soustractions-cp.md`, `FICHE-bandit-manchot-problemes-additifs-cp.md`,
`FICHE-bandit-manchot-comparer-nombres-cp.md`) :

- **Mathematiques CP (complements a 10)** obtient **`couvert-valide`** : `nadia-prof` prononce
  **VALIDE**, Done pedagogique accorde sans reserve bloquante, les onze items verifies un par un
  (`DEC-0020`, deux items degeneres gardes sur motif pedagogique explicite, sept manches
  confirmees suffisantes pour que l'aide reste atteignable). Quatrieme ligne `couvert-valide` du
  produit, deuxieme en mathematiques CP.
- **Mathematiques CP (soustractions)** est **REFUSE** : les 24 indices du paquet ecrivent "Leve"
  au lieu de "Lève", et 23 sur 24 ecrivent "baisse-en" au lieu de "baisses-en" - un mot mal
  orthographie affiche a l'enfant qui a echoue trois fois de suite. La mathematique et la methode
  des indices sont exactes ; seule la langue est fautive. La ligne reste `en cours`.
- **Mathematiques CP (resoudre un probleme simple d'addition/soustraction)** est **REFUSE** : deux
  items sur 22 (le clown et ses ballons, le jardinier et ses fleurs) attendent une reponse qui
  contredit la lecture correcte de leur propre enonce (le "en" partitif), et la reponse juste sous
  cette lecture n'est proposee dans aucun des deux cas. Les 20 autres items sont bons. La ligne
  reste `en cours`.
- **Mathematiques CP (comparer deux nombres)** est **REFUSE** : l'indice de l'item `56 ___ 60`
  affirme que 56 vient juste avant 60 dans la suite des nombres, ce qui est faux (59 est son
  predecesseur immediat) - une faussete sur exactement ce que le jeu construit. Les 24
  comparaisons et les 23 autres indices sont exacts. La ligne reste `en cours`, et n'aurait de
  toute facon pas pu passer `couvert-valide` sur cet increment : le perimetre reste partiel,
  "ranger" restant differe par `DEC-0019`.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 3 | 2 |
| mathematiques CM1 | 12 | 12 | 0 | 0 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 0 | 2 |
| **Total** | **46** | **38** | **4** | **4** |

Le produit compte desormais quatre lignes `couvert-valide` sur 46 (contre trois a la cloture de
s08), quatre lignes `en cours` (trois refus de ce sprint plus la ligne francais CP restee ouverte
depuis s06) et 38 lignes `non couvert`, chiffre inchange. Les trois refus sont en cours de
correction sur la seule correction bornee que `nadia-prof` a ecrite dans chaque fiche ; leurs
lignes ne passeront a `couvert-valide` que si `nadia-prof` rejoue l'increment corrige et prononce un
verdict `VALIDE`.
