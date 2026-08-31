# ADR-0072 - Aucune phrase du texte support ne suffit a gagner la majorite d'un paquet, et la propriete generale des marques de surface n'est pas formulable

- **Statut** : Accepte. **Son garde-fou est ecrit, vert, et mordu sur du materiau reel** -
  `adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest.java`,
  5 tests. *Defaut de gestion a signaler plutot qu'a taire : ce garde-fou a ete pousse au commit
  `2989bc5`, une entree avant cet `ADR`, par une faute de ma part au moment de composer le commit.
  Une regle a donc vecu un commit sans sa decision ; cet `ADR` ferme l'ecart et le date.*
- **Sprint** : s24. **Date** : 2026-08-31. **Auteur** : theo-archi.
- **Arbre mesure** : `s24-theo` apres fusion d'`origin/s24-sofia-dev` (`866b4ef`). JDK **21.0.10**,
  version rapportee par la machine virtuelle des tests elle-meme (`ADR-0071`).
- **Route par** : `lea-dev`, qui a trouve le defaut en jouant son propre paquet et a refuse de
  combler seule le trou de garde-fou au motif que ce serait decider une regle. Elle a raison.
- **Prolonge** : `ADR-0013` (perimetre derive, garde-fou mordant), `ADR-0039` (une propriete
  calculee plutot qu'une enumeration), `ADR-0051` (un garde-fou qui n'examine aucun objet le
  declare), `ADR-0055` (un plancher ne garde qu'une moitie de comparaison), `ADR-0068`.
- **Ne prononce aucun Done, ne decide aucun contenu.**

## Contexte

**Le defaut, trouve en jouant et non par la CI.** `lea-dev` a joue `cp-texte-narratif` avant de le
pousser et a constate qu'un enfant gagnait sans lire le texte. Le build etait vert, le jar livrable.
C'est la famille sanctionnee a s12 - une bonne reponse reconnaissable sans exercer la competence -
transposee hors du rang numerique.

**Le trou qu'elle nomme est reel, et il est large.**
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` sort de son domaine des qu'un item n'a pas
trois propositions numeriques. **Mesure du 2026-08-31**, perimetre : les 27 paquets du repertoire de
contenu, items tires par le chargeur reel, un item etant dans le domaine si ses trois propositions se
lisent comme des nombres au sens de `valeurDe` (`ADR-0068`) :

- **15 paquets sur 27 n'ont aucun item dans le domaine** : `cm1-accord-sujet-verbe`,
  `cm1-accords-groupe-nominal`, `cm1-antonymes`, `cm1-comprehension`, `cm1-conjugaison-present`,
  `cm1-homophones`, `cm1-mots-de-liaison`, `cm1-reperes`, `cm1-synonymes`, `cp-comparer`,
  `cp-comparer-nombres`, `cp-le-bon-son`, `cp-mot-manquant`, `cp-nature-mot`, `cp-ranger-nombres` ;
- 2 y sont partiellement : `cm1-grands-nombres` (14 items sur 28), `cm1-perimetre` (20 sur 22) ;
- 10 y sont entierement.
- Les deux paquets neufs de `lea-dev` sont **entierement hors du domaine** - mesure : zero item de
  `cp-texte-narratif` et zero de `cp-pluriel` n'a ses trois propositions numeriques. **Apres leur
  fusion, 17 paquets sur 29.**

*Methode et sa limite : le compte de paquets ne depend d'aucun budget de tirage. Un compte d'items ne
serait pas comparable d'un paquet a l'autre, `cp-additions-jusqu-a-10` etant combinatoire - le nombre
d'items qu'on en tire est un budget, pas une population.*

**Le garde-fou ne ment pas et ne rend pas un faux vert : il ne s'applique pas.** Rien ne s'applique a
sa place, et c'est ce que cet `ADR` traite.

## Ce que le materiau montre, et qui n'est pas ce qui m'a ete rapporte

Le fait m'a ete relaye ainsi : *"sur les sept items Ou, les sept bonnes reponses commencaient par
sous, devant ou derriere, aucun distracteur n'employait ces prepositions"*. **Verifie sur le paquet
commite (`s24-lea`, `b6772c0`), et ce n'est pas exact :**

- 7 items `Ou`, dont **4** - et non sept - ont une bonne reponse commencant par `sous`, `devant` ou
  `derriere` ;
- **2 distracteurs emploient ces prepositions** (`sous le preau`, `sous le tilleul`), donc
  *"aucun distracteur"* est faux sur le materiau commite ;
- la strategie *"presser celle qui commence par sous, devant ou derriere"* gagne **4** items, en
  **perd 2**, et est indefinie sur 1.

**Ce qui est decisif est ailleurs, et c'est mesurable** : sur ce meme paquet, un enfant qui ne lit
que la **premiere phrase** du texte support et presse la seule proposition qu'il y trouve gagne
**65 %** des items, contre 33,3 % par hasard. **C'est cette regularite-la qui rend le paquet gagnable
sans lire**, et c'est elle que la correction de `lea-dev` a fait tomber a **50,0 %**.

**Cela ne retire rien a ce que `lea-dev` a fait** : elle a trouve en jouant un defaut qu'aucun
automate ne voyait, et sa correction le corrige. C'est la description du defaut que je corrige, pas
son constat - et je le fais parce qu'une regle ecrite sur la description relayee aurait garde la
mauvaise propriete.

## Decision

### 1. La propriete generale n'est pas formulable, et je l'ecris plutot qu'une consigne

*"Une bonne reponse ne doit pas etre reconnaissable par une marque de surface que ses distracteurs
ne portent pas"* **n'est pas encodable**, et pas par manque d'effort :

- **"marque de surface" quantifie sur toutes les fonctions calculables des chaines.** Pour tout
  paquet il existe une fonction qui separe les bonnes reponses des distracteurs - la fonction
  indicatrice de l'ensemble des bonnes reponses. L'enonce universel est donc soit toujours viole,
  soit vide selon la lecture, et dans les deux cas il ne juge rien.
- Restreint aux marques **qu'un enfant peut percevoir et generaliser**, il demande de savoir ce
  qu'un enfant de six ans percoit, ce qui est de la pedagogie et non du calcul.
- Toute version encodable **fixe d'avance une famille de traits**, et ne tient alors que cette
  famille. Une regle qui ne connaitrait que les prepositions serait une liste d'exemples deguisee en
  regle, ce que ce depot a deja sanctionne.

**Je n'ai pas conclu cela par raisonnement seul : j'ai essaye trois familles et je les ai mesurees.**

| Famille essayee | Attrape-t-elle le paquet fautif ? | Cout sur le catalogue actuel |
|---|---|---|
| **Premier mot** d'une proposition - un mot qui commence plusieurs bonnes reponses et aucun distracteur | **Non.** Zero marque trouvee sur le paquet fautif : `sous` figure aussi en tete de deux distracteurs | Marquerait **7 paquets** a partir de 2 occurrences, **2** a partir de 3 - donc une liste d'exemptions des le premier jour |
| **Dernier mot**, meme forme | **Non**, meme motif | Meme ordre de grandeur |
| **Rang par longueur** - la transposition apparente du rang numerique aux paquets non numeriques | **Non** : sur le paquet fautif, la bonne reponse est la plus longue sur 25 % des items, la plus courte sur 10 %, jamais majoritaire | Rougirait des le premier jour sur `cm1-mesures` (la plus courte a **54,1 %**) |

**Les trois echouent du meme cote** : elles ne voient pas le defaut reel, et elles refuseraient du
contenu sain. C'est le resultat qui compte, et il est plus utile qu'une regle ecrite au jugement.

### 2. La propriete de s12 s'etend, non au lexique, mais a la strategie

Ce que `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` tient depuis s12 n'est pas une
propriete du rang : c'est *"une strategie qui ignore le contenu ne doit jamais etre majoritaire"*. Le
rang n'en est qu'une instance. **Une seconde instance est formulable, et c'est celle du defaut de
`lea-dev`** :

> **Sur un paquet dont les items portent un texte support, aucune strategie qui ne lit qu'une seule
> phrase de ce texte ne gagne la majorite des items.**

- **Le seuil n'est pas de moi** : c'est celui de s12, repris sans le renegocier, comparaison stricte
  comme la sienne.
- **Le domaine** : les items qui portent un texte support. Un item sans support n'est pas ignore par
  erreur - la strategie n'a aucun sens sur lui, exactement comme le rang d'une proposition non
  numerique n'en a pas dans le garde-fou de s12.
- **Ce que la regle interdit reellement** : ecrire un paquet de comprehension ou la reponse se trouve
  toujours au meme endroit du texte. C'est le raccourci que `lea-dev` a constate en jouant, et le
  seul de cette famille qu'une machine puisse voir sans dictionnaire.

### 3. Ce que cette decision ne fait pas

- Elle **ne ferme pas** la famille des marques de surface. Elle en ferme **une** instance, et la
  section 1 dit pourquoi il n'y en aura pas de version generale.
- Elle **n'exempte personne** : aucune dette, aucun plafond. Le catalogue actuel passe.
- Elle **ne juge pas le contenu**. `SAI-6`, `NAR-6` et leurs voisines restent la main de
  `nadia-prof` ; ce garde-fou ne dit rien de la qualite d'un texte, seulement de la place de sa
  reponse.

## Regle executable

**Ce n'est pas une regle ArchUnit** : les objets juges sont un texte de ressource et les items rendus
par le chargeur reel. Garde-fou de balayage, septieme motif distinct de cette famille apres
`ADR-0059` a `ADR-0062`, `ADR-0070` et `ADR-0071`.

**Fichier** :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest.java`.
**Jeux fautifs** : `adapters/adapter-content/src/test/resources/contreexemple/AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest/`.

- **Perimetre derive** (`ADR-0013` obligation 1) : les paquets viennent du **repertoire** de contenu,
  jamais du catalogue ; les items du chargeur reel ; les tirages sont bornes par ce que le paquet
  declare (`ADR-0064`), une lecture partielle etant un echec nomme.
- **Comptage des gains decisifs, jamais une esperance** : une phrase qui ne porte aucune proposition,
  ou qui en porte plusieurs, ne fait pas gagner. Modeliser le tiers de chance restant ferait entrer
  une hypothese de comportement dans un garde-fou. **Ce choix a une consequence, et je la nomme
  plutot que de la laisser trouver** : c'est lui qui place le paquet corrige a 50,0 % et non
  au-dessus.
- **Repliage de casse a la comparaison** : une proposition en tete de phrase y porte une majuscule
  que le paquet n'ecrit pas. Manquer cette occurrence rendrait le garde-fou plus indulgent qu'il ne
  doit l'etre, c'est-a-dire vert a tort.
- **Non-vacuite** (`ADR-0051`) : le domaine examine est compte, et un domaine vide echoue au lieu de
  passer. Aujourd'hui il est non vide sans les paquets de `lea-dev` - `cm1-comprehension` porte
  20 items a texte support, mesures a **0 %** sur ses deux phrases.

**Mise a l'epreuve sur du materiau reel, dans les deux sens** :

1. **Le jeu fautif est le paquet que `lea-dev` a ecrit, relu et pousse** (`b6772c0`), repris tel quel
   a un en-tete pres. Ce n'est pas une fixture imaginee par l'auteur de la regle : c'est du contenu
   ecrit de bonne foi, qui passait la CI. Mesure : **65 %** sur la phrase 1, le garde-fou rougit en
   nommant le paquet, le rang de phrase et le taux.
2. **Le cas vert est le meme paquet, par la meme personne, un jour plus tard** (`44188a8`), apres la
   seule correction en cause. Mesure : **50,0 %**, il passe. **Sans cette seconde moitie, la morsure
   prouverait que le garde-fou rougit sur quelque chose, pas qu'il distingue le changement que
   `lea-dev` a fait.**
3. La clause de non-vacuite est mordue elle aussi.

**Mesure** : `bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-content -am test -Dtest=AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest`
- **5 tests, 0 echec**. Le `-am` est porte, le perimetre est le module et ses amonts.

**Compte des regles ArchUnit du depot : inchange a 30.** Ce lot n'ajoute aucun champ `ArchRule`.

## Ce que je route a `nadia-prof`, et qui n'est pas a moi

**Le paquet corrige est exactement a la borne : 50,0 %, marge nulle.** Il passe par comparaison
stricte, comme `cp-soustractions` passait a 50,0 % pile le garde-fou de s12. Un enfant qui ne lit que
la premiere phrase gagne donc encore une manche sur deux, contre une sur trois par hasard. **Le seuil
est le sien, la valeur est un fait, et c'est a elle de dire si une marge nulle lui convient sur une
ligne de comprehension de lecture.** Je ne deplace pas un seuil que je n'ai pas fixe, et je ne
prononce rien sur le paquet.

## Consequences

- **Un paquet a texte support dont la reponse est toujours au meme endroit arrete le build**, en
  nommant le paquet, le rang de phrase et le taux.
- **Le cout d'un paquet de comprehension monte**, et c'est voulu : repartir les reponses entre les
  phrases est desormais une contrainte verifiee, plus une intention.
- **Quinze paquets sur vingt-sept restent hors de toute propriete de cette famille** - ceux dont les
  propositions ne sont pas numeriques et dont les items ne portent pas de texte support. Cet `ADR`
  n'en couvre aucun, et la section 1 dit pourquoi aucune regle generale ne les couvrira. **C'est une
  limite ecrite, pas un oubli.**
- **Limite de methode nommee** : le decoupage en phrases suit les trois ponctuations fortes ; une
  abreviation pointee couperait une phrase en deux. Aucun texte support du depot n'en porte au
  2026-08-31.

## Alternatives considerees

1. **Une regle sur les prepositions**, ou sur toute autre liste de mots. Ecartee : c'est une liste
   d'exemples deguisee en regle, et la mesure montre en plus qu'elle n'aurait pas attrape le defaut.
2. **Le rang par longueur**, transposition apparente du rang numerique. Ecartee sur mesure : elle
   n'attrape pas le paquet fautif et rougirait des le premier jour sur un paquet sain.
3. **Le premier ou le dernier mot d'une proposition.** Ecartee sur mesure, meme double echec.
4. **Etendre le garde-fou de s12 plutot qu'en ecrire un second.** Ecartee : son domaine est le rang
   parmi des valeurs numeriques, et y greffer une strategie d'une autre nature ferait un garde-fou
   qui garde deux proprietes sans que ni l'une ni l'autre ne se lise. Le seuil, lui, est partage - et
   il l'est explicitement, avec renvoi.
5. **Compter l'esperance de gain plutot que les gains decisifs.** Ecartee : elle ferait entrer une
   hypothese de comportement d'enfant dans un garde-fou. Consequence assumee et nommee : c'est ce qui
   place le paquet corrige a la borne exacte plutot qu'au-dessus.

---

## Amendement du 2026-08-31 (s24, cloture) - la regle etait vraie sur son perimetre et fausse des qu'une seconde forme d'item est entree dans le repertoire

- **Auteur** : theo-archi. **Fait neuf** : la fusion des huit branches de s24.
- **Rien de ce qui precede n'est efface.** Second bloc date de ce fichier, apres celui qui note que
  le garde-fou etait parti un commit avant son `ADR`.

**Le defaut, tel que la porte technique l'a rendu.** Sur l'arbre fusionne par `sofia-sm`, le
reacteur echoue : deux erreurs, `bootstrap` en `SKIPPED`, et la cause unique est ce garde-fou. Il
tirait ses items par `Question item = (Question) tire.orElseThrow();` - **un transtypage en aveugle
qui suppose que toute forme d'item est une `Question`**. Le lot 5b de `sofia-sm` a fait entrer dans
le repertoire de contenu servi un paquet de saisie ; le transtypage a leve une
`ClassCastException`.

**Le mecanisme est plus interessant que la faute.** Ce garde-fou est le **quinzieme** de ce module,
ecrit pendant le sprint **apres** que le tri du lot 5a eut ferme sa liste a quatorze. **Il etait
vert sur ma branche et vert sur la sienne** : ma branche ne portait aucun paquet de saisie servi, la
sienne ne portait pas ce garde-fou. **Deux verts sur deux branches ne font pas un vert**, et aucun
de nous deux ne pouvait le voir depuis son propre arbre. C'est un defaut d'integration, et il n'a
qu'un remede : mesurer sur l'arbre fusionne, ce que je n'avais pas fait.

**Ce que je corrige, et selon quel patron.** Pas d'exemption, pas de liste de noms, pas de second
mecanisme : **le classement des formes d'item est ecrit une fois** (`ClassementDesItems`, lot 5a),
et ce garde-fou **declare son domaine** par rapport a lui, comme les quatorze autres. Inventer une
seconde facon de classer les memes formes serait la dette 3 de `CLAUDE.md` - neuf familles de
chargeurs quasi identiques - reproduite sur les garde-fous.

**Le domaine, et ses deux bornes ont chacune leur motif :**

- **un item de saisie est ecarte**, et pour une raison plus forte qu'une inapplicabilite : la
  strategie jugee ici presse *la seule proposition trouvee dans une phrase*, et un item auquel
  l'enfant repond en ecrivant n'en presente aucune. La donnee que le critere lit n'existe pas
  davantage - **`ReponseAEcrire` ne porte pas de texte support** ; ce n'est pas une composante vide,
  c'est une composante absente du type ;
- **un item sans texte support reste hors du domaine**, comme avant.

**Et le compte des ecartes est tenu** : une borne qui emporterait toute la population servie n'est
plus une borne, c'est une exemption generale, et elle est desormais rouge - avec sa morsure, sur un
repertoire qui ne porte que des items de saisie. La clause porte sur un ecart **reellement
constate** : sans cette precision, un repertoire vide aurait declenche ce message-la en parlant
d'une exemption qui n'a ecarte personne, et aurait masque la clause de non-vacuite, qui est celle
qui dit vrai dans ce cas.

**Le garde-fou passe de 5 a 6 tests.** La sixieme est la morsure de la borne de domaine.

**Ce que cet amendement ne change pas** : la decision, son seuil, son perimetre derive, ses deux
morsures sur le materiau reel de `lea-dev` et le fait route a `nadia-prof` sur la marge nulle. **La
propriete gardee est la meme** ; ce qui change est qu'elle dit desormais sur quoi elle porte.

**Mesure de cet amendement**, sur l'arbre fusionne (`origin/s24-sofia-review` fusionne dans
`s24-theo`), `bash outils/mvn-jdk21.sh -B clean verify` a la racine du reacteur : **`adapter-content`
vert, 348 tests, 0 echec**, la ou l'arbre fusionne rendait 2 erreurs et `bootstrap` en `SKIPPED`.
