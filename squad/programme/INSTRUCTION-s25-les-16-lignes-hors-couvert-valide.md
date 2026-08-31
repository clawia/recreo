# Instruction des 16 lignes qui ne sont pas `couvert-valide` - ouverture de s25

- **Date** : 2026-08-31. **Auteur** : `camille-po`. **Sprint** : ouverture de s25.
- **Arbre** : worktree `s25-camille`, ouvert sur `round-two` a `130ba64`.
- **Ce que ce document est** : l'instruction ligne par ligne demandee a l'ouverture de s25, sur
  laquelle `DEC-0065` s'appuie. **C'est lui qui decide le perimetre, pas l'inverse.**
- **Ce qu'il n'est pas** : il ne prononce aucun Done, ne deplace aucune ligne de
  `squad/programme/MATRICE-couverture.md`, ne tranche rien de technique et ne juge la qualite
  pedagogique d'aucun contenu. Quand une ligne bute sur un jugement de `nadia-prof` ou sur une
  conception de `theo-archi`, je le nomme et je m'arrete.

---

## 0. Le compte, avec sa methode, avant tout le reste

**Methode** : lecture des quatre tableaux de matiere de `squad/programme/MATRICE-couverture.md`
(sections "Mathematiques - CP", "Mathematiques - CM1", "Francais - CP", "Francais - CM1"), une ligne
de tableau par competence, colonne `Statut`. Comptage automatise sur le fichier, pas a l'oeil.
Aucun module n'est compile ; la question du `-am` ne se pose pas pour cette mesure et je le dis
plutot que de laisser le lecteur le deviner.

| Statut | Nombre |
|---|---|
| `couvert-valide` | **27** |
| `non couvert` | **15** |
| `en cours` | **1** |
| `non couvrable en l'etat` | 4 |
| **Total des lignes des quatre referentiels** | **47** |

**Denominateur atteignable** : 47 - 4 = **43**. **27/43 = 62,8 %.**

**Le compte de 16 lignes hors `couvert-valide` (15 + 1) est exact.** Il m'a ete transmis comme un
inventaire a verifier ; je l'ai recompte et je le confirme.

**Couverture par niveau, meme methode, meme perimetre** - elle sert au motif de rang plus bas :

| Matiere / niveau | `couvert-valide` | atteignable | Part |
|---|---|---|---|
| mathematiques CP | 6 | 11 | 54,5 % |
| mathematiques CM1 | 8 | 10 | 80,0 % |
| **francais CP** | **5** | **11** | **45,5 %** |
| francais CM1 | 8 | 11 | 72,7 % |

Le francais CP reste le point le plus faible, mais il a gagne 18,2 points en un sprint (27,3 % ->
45,5 %) : `NAR` et `PLU` ont fait exactement ce que `DEC-0059` en attendait.

---

## 1. Le tableau d'instruction - ce qui manque a chaque ligne, et de quelle nature

**Colonne "nature du manque"** : c'est la question posee. Elle repond a *"qu'est-ce qui empeche
cette ligne de bouger aujourd'hui"*, pas a *"combien ca coute"* - le cout est a `theo-archi` et la
capacite a `sofia-sm`.

| # | Ligne | Matiere / niveau | Ce qui manque exactement | Nature du manque | Bouge a s25 ? |
|---|---|---|---|---|---|
| 1 | Denombrer, lire, ecrire et decomposer les nombres jusqu'a 100 | maths CP | Le paquet de contenu est **arbitre et pret** (`DEN-1` a `DEN-8`, 2026-08-24). Mais `DEN-2` interdit `couvert-valide` tant que le verbe *denombrer* n'est travaille par aucun jeu, et *denombrer* exige une collection **dessinee, groupee par dix, sur laquelle l'enfant agit** | **contenu (fait) + visuel avec interaction (absent)** | **Non pour la ligne.** Un paquet est livrable, il fait passer `non couvert` vers `en cours`, **zero ligne de couverture** |
| 2 | Reconnaitre et utiliser la monnaie (pieces/billets simples) | maths CP | La moitie *"utiliser"* est servable en texte seul - verifie par moi sur `contenu-repris/quiz/cp-monnaie.yml` (8 items, additions et rendu de monnaie, aucune image). La moitie *"reconnaitre"* exige de **voir** une piece. **Aucun arbitrage individuel de `nadia-prof` sur cette ligne** | **arbitrage manquant**, puis visuel pour la seconde moitie | **Conditionnel** - voir section 3 |
| 3 | Se reperer dans la journee, la semaine ; lire l'heure pleine sur une horloge | maths CP | La ligne porte **deux moities separees par un point-virgule dans le referentiel lui-meme**. *"Se reperer dans la journee, la semaine"* est servable en texte seul - verifie sur `contenu-repris/quiz/cp-durees.yml` (jours, mois, saisons, matin et nuit, anteriorite). *"Lire l'heure pleine sur une horloge"* exige une horloge dessinee. **Aucun arbitrage individuel** | **arbitrage manquant**, puis visuel pour la seconde moitie | **Conditionnel** - voir section 3 |
| 4 | Se reperer et decrire des deplacements sur un quadrillage simple | maths CP | Une grille rendue, dont le contenu declare les cases et le chemin. **Aucun arbitrage individuel.** Aucune architecture d'item visuel n'existe | **visuel + arbitrage manquant** | Non |
| 5 | Reconnaitre et nommer quelques figures planes | maths CP | Le **plus instruit** des visuels : `FIG-1` a `FIG-4` sont ecrites depuis le 2026-08-24 (figure dessinee dont le contenu declare les proportions, trois exemples par forme, la couleur ne distingue jamais, cercle distinct de disque). Ce qui manque est **entierement architectural** : aucun `ADR` ne dit comment un item porte une donnee de dessin | **visuel** (arbitrage de forme rendu, architecture absente) | Non |
| 6 | Effectuer une multiplication posee | maths CM1 | Une **troisieme mecanique** (saisie posee en colonnes), jamais instruite. `nadia-prof` ajoute un avis de fond : *"c'est le geste lui-meme qui est la competence"*, et *"je prefere une case vide a une case fausse"* | **mecanique 3 + reserve pedagogique de fond** | Non |
| 7 | Effectuer une division euclidienne simple | maths CM1 | Idem, meme famille, meme avis | **mecanique 3 + reserve pedagogique de fond** | Non |
| 8 | Localiser une syllabe dans un mot (debut, milieu, fin) | francais CP | Un **son emis**. La famille est etablie, **l'item n'a jamais ete arbitre**, et la **source de la voix n'est pas tranchee** : aucun agent de cette squad ne peut produire un enregistrement de voix humaine | **audio + source de voix (question a l'utilisatrice)** | Non |
| 9 | Identifier un son (phoneme) dans un mot | francais CP | Idem. Le seul proxy propose, `contenu-repris/mots/cp-premiere-lettre.yml`, a ete **REFUSE** le 2026-08-30 (arbitrage de s23, section 7) | **audio + source de voix** | Non |
| 10 | Associer les graphemes aux phonemes - **`en cours`** | francais CP | Le jeu `bandit-manchot-le-bon-son-cp` est `VALIDE SOUS RESERVE` depuis le 2026-08-10 ; la ligne reste `en cours` parce que la competence nomme **plus** que ce que le paquet travaille - la portion **digraphes** demande d'entendre le son | **audio + source de voix** | Non |
| 11 | Decoder un mot regulier inconnu par assemblage syllabique | francais CP | Arbitre `SYL-1` a `SYL-4` : mot rare ou pseudo-mot affiche, **trois propositions entendues et non lues**, reecoutables sans coup d'essai. **Pas de reconnaissance vocale**, seulement de l'emission | **audio + source de voix** | Non |
| 12 | Copier un mot, une phrase courte sans erreur | francais CP | `R1` : c'est une competence **graphomotrice**, *"un clavier n'en travaille aucune"*. Rien de ce que ce produit peut construire ne l'ouvre | **contrainte fondatrice** (candidate a `non couvrable en l'etat`, voir section 5) | Non |
| 13 | Ecrire de maniere autonome un mot simple | francais CP | `R6`, du 2026-08-25 : avec la correction automatique acceptee sur les deux appareils cibles, **c'est l'appareil qui produit les lettres**. `nadia-prof` retire elle-meme la ligne de la liste des candidats : *"elle cesse d'etre un candidat"* | **contrainte fondatrice** (candidate a `non couvrable en l'etat`) | Non |
| 14 | Lire silencieusement un texte de longueur croissante et en restituer l'essentiel | francais CM1 | *"Restituer l'essentiel"* est une **production libre** jugee par un humain. `R2` ferme la voie de la saisie ; le bandit manchot ne sait que faire choisir | **contrainte fondatrice** (candidate a `non couvrable en l'etat`) | Non |
| 15 | **Reperer les substituts d'un personnage (pronoms, groupes nominaux)** | francais CM1 | **Rien.** Arbitre `SUB-1` a `SUB-10` le 2026-08-30, contenu seul, mecanisme existant (`texteSupport` generique), aucune architecture neuve. Type `COMP-2` : consomme le quota d'un paquet de ce type par sprint | **aucun manque - developpable des l'ouverture** | **OUI, +1 ligne** |
| 16 | Rediger un texte narratif court et coherent | francais CM1 | `R3` : *"un Done pedagogique sur cette ligne par une mecanique de saisie est refuse par avance, quelle que soit la qualite du lot"*. Production libre, jugement humain | **contrainte fondatrice** (candidate a `non couvrable en l'etat`) | Non |

**Sources, une fois pour toutes** : `DEN-*`, `FIG-*`, `SYL-*` et les motifs des lignes 1, 4, 5, 11
sont dans `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`.
`R1` a `R6` sont dans `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md`. `SUB-1` a
`SUB-10` et le refus de `cp-premiere-lettre.yml` sont dans
`squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md`,
sections 5 et 7. La ventilation par famille est celle de `DEC-0053`, section 7.1, verifiee ligne par
ligne contre la matrice au moment ou elle a ete ecrite et non reconstituee ici de memoire.

---

## 2. Ce que ce tableau dit, et qu'il faut lire en face

### 2.1 La reserve arbitree contient exactement deux entrees, et une seule rapporte une ligne

L'inventaire qui m'a ete transmis - *"il reste `SUB` et `DEN`, et `DEN` ne rapporte aucune ligne"* -
**est exact**. Je l'ai reverifie contre les huit verdicts de l'arbitrage de s23 et contre `DEN-1` a
`DEN-8` : `RAN`, `ETA`, `NAR`, `PLU`, `LIA` et `FRA` sont consommes et verts, `cp-premiere-lettre` et
`cm1-compare` sont refuses, il reste `SUB`. Plus `DEN`, arbitre a part le 2026-08-24.

**Un sprint qui se contenterait de consommer la reserve plafonne a une seule ligne neuve.** C'est
le fait central de s25 et il n'est pas contournable par un choix de perimetre.

### 2.2 Le plafond que j'avais moi-meme calcule a s22 est atteint aujourd'hui, a la ligne pres

`DEC-0053`, section 7.1, ecrite par moi le 2026-08-28 :

> *"Plafond realiste a court terme, ecartant l'audio (bloque sur une source qui n'existe pas) et le
> visuel (aucune architecture ecrite) : les 9 lignes de contenu seul seules [...] **18 + 9 = 27/43
> (62,8 %) au mieux, sans construire quoi que ce soit de neuf**."*

**Le produit est a 27/43 (62,8 %) le 2026-08-31.** Le chiffre est atteint au dixieme de point.

**Et il faut le lire correctement, parce qu'il ne dit pas que la reserve est vide** : des 9 lignes
de contenu seul de ce calcul, **8 sont vertes et `SUB` est la neuvieme**. Le produit est a 27 et non
a 26 parce que `FRA` a rapporte une ligne qui n'etait **pas** dans ces 9 - la moitie *"notion de
fraction simple"*, classee visuelle a s22 et redirigee en contenu seul par l'arbitrage 8bis du
2026-08-30.

**Consequence, et c'est le chiffre a retenir :**

> **Le plafond de s25 sans ouvrir de famille neuve est 28/43 (65,1 %).** Methode : 27 lignes vertes
> aujourd'hui, plus `SUB`, seule ligne restante dont l'arbitrage prealable est rendu et dont la
> famille est le contenu seul. **Aucune autre ligne des 16 ne peut atteindre `couvert-valide` a s25
> sans un arbitrage de `nadia-prof` qui n'existe pas encore.**

### 2.3 La redirection historique, que je ne peux pas ignorer une seconde fois

`DEC-0053` notait que les quatre lignes que `DEC-0030` pensait "contenu seul" ont **toutes les
quatre** ete redirigees hors du contenu seul une fois l'arbitrage rendu : **zero sur quatre**. Cela
s'applique directement a la section 3 : les deux candidates que j'y propose peuvent parfaitement
etre refusees. **Je les pose comme des questions, pas comme un perimetre acquis.**

---

## 3. Deux candidates de contenu seul que personne n'a jamais posees a `nadia-prof`

**C'est le seul endroit de cette instruction ou une ligne neuve peut apparaitre a s25 sans famille
neuve.** Les deux reposent sur la meme observation, et elle est verifiable : **deux des lignes
rangees en "famille visuelle" portent une moitie qui ne demande aucune image.**

### 3.1 Le fait, verifie dans le depot et non suppose

| Ligne | Moitie textuelle | Matiere premiere presente dans `contenu-repris/` |
|---|---|---|
| Monnaie (maths CP) | *"utiliser [...] dans des problemes concrets"* | `quiz/cp-monnaie.yml`, **8 items** : *"Tu as une piece de 2 euros et une piece de 1 euro. Combien as-tu en tout ?"*, *"Un pain coute 1 euro. Tu paies avec une piece de 2 euros. Combien te rend-on ?"*. **Aucune image.** J'avais deja etabli ce fait a `DEC-0019` : *"il ne travaille que 'utiliser'"* |
| Heure (maths CP) | *"Se reperer dans la journee, la semaine"* - **moitie separee par un point-virgule dans le referentiel lui-meme** | `quiz/cp-durees.yml`, **11 items**, dont 7 sur cette moitie : jours de la semaine et leur ordre, mois, saisons, matin, midi et nuit, comparaison de durees. **Aucune image** |

*Comptage : occurrences de la cle `question:` dans chacun des deux fichiers. Les deux fichiers sont
de la **matiere premiere** au sens de `contenu-repris/README.md`, pas un cahier des charges : ils
portent des items a quatre propositions, ce qu'`ADR-0063` interdit, et ils repasseraient devant
`nadia-prof` comme un contenu neuf. Je ne les propose pas tels quels et je ne juge pas leur qualite.*

### 3.2 La question, et elle est entierement a `nadia-prof`

> **Une moitie textuelle d'une ligne classee visuelle peut-elle porter cette ligne a
> `couvert-valide` avec une mention de couverture partielle nommee - ou la ligne reste-t-elle
> `en cours` comme `DEN-2` l'impose sur "denombrer" ?**

**Le depot porte les deux precedents, et c'est pourquoi je ne peux pas y repondre a sa place :**

- **Precedent "vert avec mention"** : `FRA-9` (fractions, moitie *"notion"*) et `LIA-10` (mots de
  liaison). Dans les deux cas `nadia-prof` a fait passer la ligne au vert **en dictant elle-meme la
  mention** portee par la matrice.
- **Precedent "reste `en cours`"** : `DEN-2`, ecrite le 2026-08-24, *"non negociable"*, et
  **reconfirmee par elle le 2026-08-30**.

Rien dans ces trois textes ne dit ce qui separe les deux cas. **Le critere est a elle, il est
pedagogique, et l'inventer serait exactement ce que ma charte m'interdit.**

### 3.3 Ce que la reponse change, chiffre

| Reponse de `nadia-prof` | Plafond de s25 |
|---|---|
| Les deux moities peuvent porter leur ligne au vert avec mention | **30/43 (69,8 %)**, si les trois paquets sortent et sont valides |
| Une seule des deux | **29/43 (67,4 %)** |
| Aucune - regime `DEN-2` generalise | **28/43 (65,1 %)**, `SUB` seule |

**Aucun de ces chiffres n'est une prevision.** Ce sont des plafonds : ils supposent qu'un paquet
ecrit dans le sprint passe la porte du premier coup, ce que l'historique de ce produit ne donne
jamais - a s23, trois paquets ouverts ont donne trois lignes, mais apres deux refus, trois tours de
correction et cinq reaudits.

---

## 4. Le plus gros bloc atteignable - verification de la lecture qui m'a ete transmise

**La lecture transmise** : *"la famille visuelle est le plus gros paquet de lignes non couvertes qui
ne se heurte a aucune contrainte fondatrice - monnaie, heure, quadrillage, figures planes, et la
moitie denombrer de `DEN`"*.

### 4.1 Sur le compte : exacte

| Famille | Lignes qu'elle ferait passer a `couvert-valide` | Detail |
|---|---|---|
| **Visuelle** | **5** | monnaie, heure, quadrillage, figures planes, **plus** `DEN` - mais `DEN` **seulement si le paquet de contenu est fait aussi**, les deux moities etant necessaires |
| Audio | **4** | localiser une syllabe, identifier un son, decoder par assemblage, **plus** la ligne `en cours` des graphemes qui passerait au vert |
| Saisie posee (mecanique 3) | 2 | multiplication posee, division euclidienne |
| Contenu seul | **1** | `SUB` |

**Le visuel est bien le plus gros bloc.** Et la lecture est exacte sur un second point que je
confirme : **le visuel n'est pas une mecanique neuve.** `theo-archi` classe le visuel et l'audio
comme des **formes d'item**, pas comme des mecaniques - seules la saisie et l'operation posee en
sont. La moitie *"reperage sur une droite numerique"* de la ligne des fractions est visuelle elle
aussi, mais **elle ne rapporterait aucune ligne** : la ligne est deja verte depuis le 2026-08-31.

### 4.2 Trois corrections, et elles portent

**a) L'ecart avec l'audio est d'une ligne, pas d'un ordre de grandeur.** 5 contre 4. Presenter le
visuel comme *"le seul chemin vers une couverture nettement plus grande"* n'est pas soutenu par le
compte.

**b) `nadia-prof` a rendu un ordre de priorite explicite, et il place l'audio devant le visuel.**
Ce n'est pas un detail de sequencement, c'est un avis ecrit, date du 2026-08-15, motive, et il n'a
jamais ete retire :

> *"Mon ordre : audio, puis visuel, puis saisie."*
>
> *"Ces trois lignes ne sont pas une matiere parmi d'autres, ce sont les fondations de la lecture
> [...] Ces trois lignes sont les seules des 35 qui ne peuvent recevoir aucun contenu, pas meme un
> morceau."*
>
> Et, sur le visuel : *"Ces notions se rencontrent ailleurs, et mieux. L'heure s'apprend sur une
> horloge murale, la monnaie avec de la vraie monnaie, une figure plane se trace avec une regle."*
>
> `squad/conformite/AVIS-PRIORITE-PEDAGOGIQUE-s13-seconde-mecanique.md`

La saisie a ete construite en premier, contre cet ordre, sur arbitrage de l'utilisatrice rendu en
connaissance du cout. **Ouvrir le visuel a s25 remettrait l'audio en troisieme position sur trois,
apres avoir ete classe premier.** Ce n'est pas a moi de trancher - c'est un arbitrage de
l'utilisatrice - mais il ne doit pas se produire par defaut, en silence, parce qu'un compte de
lignes a designe le visuel.

**c) L'audio et le visuel butent sur des choses de nature differente, et une seule est un
arbitrage.**

| Famille | Ce qui bloque | Qui peut le lever |
|---|---|---|
| Audio | **La source de la voix.** Il faut un enregistrement de voix humaine par syllabe et par phoneme, et *"aucun agent de cette squad ne peut le produire"*. Une synthese vocale est une alternative, que `nadia-prof` rejugerait | **L'utilisatrice seule.** Aucune capacite de developpement n'y change rien - la ligne est hors d'atteinte *"quel que soit le nombre de sprints"* tant que la source n'est pas tranchee |
| Visuel | **L'architecture.** Aucun `ADR` ne dit comment un item porte une donnee de dessin. Et **3 des 4 lignes visuelles n'ont aucun arbitrage individuel** : seules figures planes (`FIG-1` a `FIG-4`) et fraction-reperage sont arbitrees | `theo-archi` pour l'architecture, `nadia-prof` pour les trois arbitrages, l'utilisatrice pour la capacite |

**Ce que cela change au rang** : la question de la source de voix **coute une reponse, pas un
sprint**. Elle est ouverte depuis le 2026-08-28 et n'a jamais ete posee frontalement a
l'utilisatrice. **Le visuel coute une conception d'architecture, trois arbitrages et des paquets** -
plusieurs sprints, et `nadia-prof` demande explicitement qu'il ne soit pas fait vite :

> *"Une mecanique visuelle mal faite enseigne faux, et c'est celle que je voudrais voir construite
> avec le plus de soin, donc pas dans la precipitation. Une horloge dont les aiguilles sont a peu
> pres placees, un quadrillage dont les cases ne sont pas carrees, une droite graduee dont les
> intervalles ne sont pas egaux : sur chacun de ces objets, l'a-peu-pres est une erreur de contenu,
> pas un defaut de forme."*

**Conclusion de section, et c'est la seule chose que je tranche ici** : la lecture transmise est
**juste sur le compte et sur l'absence de contrainte fondatrice**, et **incomplete sur trois
points** - l'ecart avec l'audio est d'une ligne, l'ordre pedagogique ecrit place l'audio devant, et
le visuel est le seul des deux dont le deblocage ne tient pas dans une reponse.

---

## 5. Quatre lignes qui devraient probablement changer de statut, et je ne le fais pas seule

Les lignes 12, 13, 14 et 16 du tableau (copier un mot ; ecrire un mot en autonomie ; restituer
l'essentiel ; rediger un texte narratif) portent le statut `non couvert`. **Aucune n'est
"non couverte faute de capacite" : chacune bute sur une propriete de ce que le produit est.**

`DEC-0046`, section 4.1, a etabli une distinction que ce cas appelle : une ligne
`non couvrable en l'etat` peut l'etre de facon **reversible** (une decision produit la rouvrirait)
ou **irreversible** (rien ne la rouvrira).

**Ce que je constate** : les garder `non couvert` gonfle un denominateur de 43 avec quatre lignes
qu'aucun sprint n'atteindra, et **fait passer le produit pour plus en retard qu'il ne l'est**. Les
reclasser ferait passer le denominateur a 39 et la couverture affichee de 62,8 % a **69,2 %** sans
qu'une seule ligne de code soit ecrite.

**C'est exactement pourquoi je ne le fais pas dans le sprint ou l'objectif est la couverture
maximale.** Une reclassification qui ameliore mon propre indicateur, decidee par moi, le jour ou on
me demande de le maximiser, n'est pas lisible - meme si elle est juste. **La question part a
`nadia-prof` (le motif pedagogique) et a l'utilisatrice (l'arbitrage), par `sofia-sm`, et le gain
d'affichage est nomme dans la question elle-meme pour que personne n'ait a le decouvrir.**
`DEC-0065`, section 7, la porte.

---

## 6. Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| La question de la couverture partielle nommee sur une moitie textuelle (section 3.2) | `nadia-prof`, par `sofia-sm` | **La question qui decide le plafond de s25.** Une reponse a l'ouverture, avant l'affectation |
| L'arbitrage prealable de la moitie *"utiliser"* de la monnaie et de la moitie *"se reperer dans la journee, la semaine"* | `nadia-prof`, par `sofia-sm` | Conditionne a la reponse ci-dessus. Sans conditions ecrites, aucun paquet n'est developpable |
| La source de la voix, ouverte et jamais posee frontalement (section 4.2) | l'utilisatrice, par `sofia-sm` | Elle bloque 4 lignes *"quel que soit le nombre de sprints"*. Elle coute une reponse |
| L'ordre audio puis visuel, que s25 deciderait par defaut s'il ouvrait le visuel (section 4.2) | l'utilisatrice, par `sofia-sm` | Qu'il soit choisi, pas subi |
| L'architecture d'un item visuel, inexistante | `theo-archi`, par `sofia-sm` | Un ordre de grandeur, **pas** une conception : rien ne l'a encore demande formellement |
| Le statut des quatre lignes de la section 5 | `nadia-prof` pour le motif, l'utilisatrice pour l'arbitrage, par `sofia-sm` | Avec le gain d'affichage nomme dans la question |
