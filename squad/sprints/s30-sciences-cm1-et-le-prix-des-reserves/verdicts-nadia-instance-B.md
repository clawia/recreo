# s30 - Verdicts pedagogiques de `nadia-prof`, instance B

- **Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
- **Date** : 2026-09-06.
- **Perimetre** : celui que j'ai reparti moi-meme dans mon arbitrage prealable, section 5.2 - **les
  deux lots de sciences et technologie CM1** (`PAR-2` interdit de les partager) et **le bloc D,
  `POS-R2`**.
- **Budget opposable** : **4,0**, marge de rejeu comprise (`PAR-9`, que j'ai posee a ce sprint en
  correction de `PAR-8`).
- **Arbre audite** : `round-two` a `dbbfe129`. Worktree dedie `s30-nadia-B`, jar reconstruit depuis
  ce worktree, application lancee sur le **port 8081**.
- **Instance A** : perimetre disjoint. **Je n'ai touche ni a son perimetre, ni a sa branche, ni a
  son worktree, et je n'ai relu aucun de ses paquets.** Rappel de ce que j'ai ecrit dans mon
  arbitrage : deux paires d'yeux sur des lots disjoints, ce sont deux paires d'yeux, **pas un double
  controle**.

---

## 1. Les verdicts, ligne par ligne

| Ligne | Jeu | Verdict | La ligne ferme ? |
|---|---|---|---|
| **`SCI-CM1-05`** - signal et information | `bandit-manchot-signal-information-cm1` | **VALIDE SOUS RESERVE** | **oui** : `non couvert` -> `couvert-valide` |
| **`SCI-CM1-17`** - milieu proche et chaine alimentaire | `bandit-manchot-chaine-alimentaire-cm1` | **REFUSE** | **non**, reste `non couvert` |
| **`SCI-CM1-02`** - melanges et separation | `bandit-manchot-melanges-cm1` | **VALIDE SOUS RESERVE** | **oui** : `non couvert` -> `couvert-valide` |
| **`SCI-CM1-12`** - objet technique | `bandit-manchot-objet-technique-cm1` | **REFUSE** | **non**, reste `non couvert` |
| **`MAT-CM1-05`** - division posee (bloc D, `POS-R2`) | `bandit-manchot-division-posee-cm1` | **VALIDE SOUS RESERVE** (quatrieme audit) | deja `couvert-valide`, **inchangee** |

**Deux lignes ferment sur les quatre soumises**, toutes deux sur la partie de mon perimetre que
j'avais moi-meme designee comme la moins couteuse. **Mon attente ecrite le 2026-09-06 etait de 3 a 4
lignes pour le sprint entier, deux instances confondues** ; ma moitie en apporte deux.

**Aucune mention de couverture partielle n'est due sur les deux lignes qui ferment.** Mon arbitrage
prealable (section 2.4) enumerait les **trois** lignes de sciences qui en exigent une - les lignes 6
(classement du vivant), 14 (environnement numerique) et 20 (document scientifique). Ni `SCI-CM1-05`
ni `SCI-CM1-02` n'en fait partie, et **je n'en ajoute pas une apres coup** : leurs parentheses sont
exemplatives (*feu tricolore, panneau, alarme, ecran*) ou au singulier (*identifier **un** moyen*),
et les deux paquets servent au-dela de ce qui etait exige.

**Je ne touche pas a `squad/programme/MATRICE-couverture.md`** : son compte appartient a
`camille-po`, je l'ai ecrit moi-meme.

**Toutes les fiches sont ecrites**, les quatre de sciences ouvertes de zero sur le modele de
`squad/TEMPLATES.md` et des fiches existantes :

- `squad/conformite/FICHE-bandit-manchot-signal-information-cm1.md`
- `squad/conformite/FICHE-bandit-manchot-chaine-alimentaire-cm1.md`
- `squad/conformite/FICHE-bandit-manchot-melanges-cm1.md`
- `squad/conformite/FICHE-bandit-manchot-objet-technique-cm1.md`
- `squad/conformite/FICHE-bandit-manchot-division-posee-cm1.md` (quatrieme bloc, rien d'efface)

---

## 2. Ce que j'ai reellement joue

**Aucun de ces cinq verdicts n'est rendu sur une lecture de fichier seule.**

| Jeu | Ce que j'ai joue |
|---|---|
| signal-information | partie complete de dix manches, **une erreur volontaire par manche** ; une manche poussee a **trois erreurs** pour atteindre la revelation ; les onze items lus |
| chaine-alimentaire | partie complete de dix manches, une erreur volontaire par manche ; les onze items lus |
| melanges | partie complete de dix manches, une erreur volontaire par manche ; les onze items lus |
| objet-technique | partie complete de dix manches, une erreur volontaire par manche ; les onze items lus ; **plus la relecture item par item de `cp-qlm-objet-technique.yaml`**, que le motif du refus exige |
| division-posee | **deux** parties completes - une a une erreur par manche, une a **trois** erreurs par manche pour atteindre chaque revelation ; les onze divisions recalculees |

**Audit degrade sur un point, et je le declare, comme le 2026-09-06** : j'ai joue **par requetes HTTP
reelles**, donc j'ai lu le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les
textes, l'ordre des ecrans, la presence et l'absence de chaque bloc, l'enchainement apres une erreur
et apres trois. Cela **ne** couvre pas la couleur, le contraste, la taille reelle ni la position a
l'ecran. **Aucun des cinq verdicts ne repose sur un jugement visuel.**

**Trois faits de partie que la lecture du fichier seule ne donnait pas**, et ils portent deux des
cinq verdicts :

1. Sur `chaine-alimentaire`, le tirage m'a servi **cinq des cinq items clones** sur dix manches :
   **la moitie de la partie est le meme item**. C'est ce qui transforme un defaut de fabrication en
   defaut visible par un enfant.
2. Sur `melanges`, la manche 2 m'a montre a l'ecran que la reponse *"laisser reposer le melange"*
   est ecrite dans sa propre question.
3. Sur `objet-technique`, la consigne servie - *"nomme sa piece"* - et l'indice de l'item 4 -
   *"l'air d'une piece"* - se suivent a l'ecran a quelques centimetres. Les deux sens du mot ne
   cohabitent que dans le rendu, pas dans le fichier.

---

## 3. Les motifs, en resume - le detail actionnable est dans chaque fiche

### `SCI-CM1-05` - VALIDE SOUS RESERVE

**La ligne est servie et elle ferme.** Onze items exacts, deux taches reellement distinctes, aucun
fait faux, aucun biais, `SCI-1` tenue sur le seul item qui la mobilise, `SCI-4` tenue, `SCI-3`
mesuree **sur le lot** (zero item numerique sur 22). La tache B - decoder l'information qu'un signal
porte - est le meilleur travail du lot 1.

**Reserves** : `SIG-R1` (la tache A ne monte jamais et cinq items sur six donnent leur reponse dans
l'enonce) ; `SIG-R2` (la troisieme proposition de l'item de l'ambulance, *"un signal qui se sent"*,
n'est pas un candidat plausible, donc `SCI-4` ne protege rien de reel sur cet item).

### `SCI-CM1-17` - REFUSE

**Les cinq items de la seconde tache sont un seul item, servi cinq fois avec un autre nom
d'animal** : meme question, meme bonne reponse (*"il / elle risquerait de manquer de nourriture"*),
memes deux distracteurs. Des la seconde occurrence l'enfant reconnait la phrase et la presse, sans
rien savoir du milieu.

**Le refus s'appuie sur une barre publiee avant que le lot existe** (arbitrage prealable de s30,
section 2.3) : *"changer le nom de l'animal dans la meme question n'est pas une seconde tache"*. La
tache B compte donc pour **un** item : `FER-3` echoue (1 < 3), `FER-2` echoue (7 < 10).

**J'y reconnais ma part** : le couple de taches implemente est celui que j'avais propose, et je
n'avais pas dit que la seconde devait produire des reponses differentes d'un item a l'autre. C'est
ecrit dans la fiche, et la propriete a tenir l'est aussi.

**Ce qui tient et n'est pas a refaire** : les six relations alimentaires, exactes ; le milieu,
coherent ; `V3`, `CYC-1`, `SCI-2`, `SCI-3`, la lisibilite.

### `SCI-CM1-02` - VALIDE SOUS RESERVE

**Le meilleur des quatre paquets de sciences de s30.** Six classements exacts, cinq moyens de
separation reels et bien apparies, une vraie progression de difficulte, et des indices qui portent
**le critere** et non la categorie sur les cinq items binaires - la forme que `DRO-R2` demande.

**Reserves** : `MEL-R1` (l'indice de l'item du sel dit *"il faut faire disparaitre l'eau"* : cela
plante la conception fausse que `SCI-CM1-01` devra defaire, et cela nomme la reponse) ; `MEL-R2`
(l'item de la decantation ecrit sa reponse dans sa question) ; `MEL-R3` (troisieme proposition non
plausible, meme famille que `SIG-R2`).

**J'ai envisage le refus sur `MEL-R1` et j'ecris pourquoi je ne le prononce pas** : ce n'est ni un
enonce, ni une bonne reponse, ni un fait affirme comme vrai, mais une formulation relachee dans un
indice dont la premiere phrase est excellente. **Et la ligne ferme meme en retirant l'item que
`MEL-R2` conteste** : dix items servent encore la ligne, exactement le plancher de `FER-2`. Je l'ai
verifie explicitement, parce que j'aurais refuse si la fermeture avait repose sur l'item defectueux.

### `SCI-CM1-12` - REFUSE

**Les quatre items de fonction reproduisent, un jour apres, le defaut que j'ai ecrit sous le nom
`OBJ-R2`** : les deux distracteurs sont toujours les fonctions des autres objets du paquet, si bien
qu'un enfant qui ignore totalement l'objet nomme elimine quand meme les deux. `OBJ-R2` a ete ecrite
le 2026-09-06 sur le paquet jumeau de CP, et mon arbitrage prealable porte **"Attention a `OBJ-R2`"**
en face de cette ligne exactement.

**Et la tache est en dessous du CP, pas au-dessus** : *"A quoi sert un velo ?"* au CM1, quand le CP
sert *parapluie, ciseaux, stylo*. L'item 6 (*le guidon*) est un item de CP re-servi a l'identique.

Consequence : `FER-2` echoue (7 < 10), `FER-1` perd le seul support de l'element *"la fonction"*.

**Ce qui tient et n'est pas a refaire** : la tache B entiere (six pieces reelles, distracteurs pris
sur le meme objet) et **l'item 11, qui est le meilleur item des quatre paquets de sciences de s30**.

### `MAT-CM1-05` - bloc D, `POS-R2` : levee, `POS-R3` ouverte

**`POS-R2` est levee.** Sur les huit items a reste unique, la bonne le porte desormais **quatre
fois** contre **zero** auparavant. Ma strategie composee du 2026-09-06, qui rapportait **7 items sur
11 (63,6 %)** sans poser une seule division, en rapporte **4 sur 11 (36,4 %)** contre 33,3 % au
hasard : **elle est morte**.

**Je corrige au passage une formulation de moi avant de m'appuyer dessus.** Ma reserve demandait que
les items a restes egaux ne portent pas tous *"un triplet de quotients symetrique"* ; les trois
triplets restent arithmetiquement symetriques. **Mon texte nommait la mauvaise propriete** : ce qui
donne la reponse sans diviser est **la position de la bonne au milieu**, ce que ma propre phrase
disait juste apres, et c'est cela que `marc-dev` a casse sur 234 / 9. **La chose que je demandais de
casser est cassee ; c'est le nom que je lui avais donne qui etait imprecis.**

**`POS-R3` est ouverte**, comme un poteau neuf a cote d'une barre franchie et **non comme un
durcissement** : la proposition au reste unique est la bonne **4 fois sur 8 (50 %)** la ou le hasard
donnerait 33 %, et deux des trois items a restes egaux mettent encore la bonne au milieu. La
meilleure strategie sans division rapporte donc encore **6 sur 11 (54,5 %)**. **La correction a fait
gagner un item sur onze**, et je le dis sans adoucissement : c'est peu. `POS-R3` coute **0,5** et
**ne ferme aucune ligne** ; c'est a `camille-po` de decider si un item sur onze vaut ce prix.

---

## 4. Les reserves neuves, avec leur regime

| Reserve | Paquet | Bloquante ? | Regime et prix | Ferme une ligne ? |
|---|---|---|---|---|
| **`SIG-R1`** - la tache A ne monte jamais, l'enonce donne le canal de perception | `cm1-sciences-signal-information` | non | **rejeu borne 0,5 pour le paquet entier** (`PAR-10` : un rejeu se paie par paquet, pas par reserve) | non |
| **`SIG-R2`** - troisieme proposition non plausible | idem | non | voyage avec `SIG-R1`, meme rejeu | non |
| **`MEL-R1`** - *"faire disparaitre l'eau"*, plus la reponse nommee | `cm1-sciences-melanges` | non | **rejeu borne 0,5 pour le paquet entier** | non |
| **`MEL-R2`** - l'enonce contient sa reponse | idem | non | voyage avec `MEL-R1` | non |
| **`MEL-R3`** - troisieme proposition non plausible | idem | non | voyage avec `MEL-R1` | non |
| **`POS-R3`** - la bonne porte le reste unique une fois sur deux | `cm1-division-posee` | non | **rejeu borne 0,5** | non |

**Deux corrections a cout nul, qui voyagent avec un rejeu deja paye et ne creent aucune dette
propre** : l'indice du sirop de menthe parle d'une *carafe* quand son enonce parle d'un *bol*
(`cm1-sciences-melanges`) ; le mot *"piece"* designe un composant dans la consigne et une salle dans
l'indice de l'item 4 (`cm1-sciences-objet-technique`). **Les deux sont du fond, donc de mon ressort**,
et non de l'orthographe - ce controle-la appartient au `relecteur-pedagogique` (`DEC-0002` amende le
2026-08-04, `DEC-0056`), y compris sur le texte destine a l'enfant.

**`SIG-R2` et `MEL-R3` sont le meme defaut sur deux paquets.** Je **n'en fais pas une condition
transverse** : mes clauses se posent dans l'arbitrage prealable - un document, une instance, avant le
sprint - et **aucune instance n'en cree, n'en amende ni n'en retire en cours de sprint**. C'est a
`camille-po` de decider si elle en fait une condition generale a s31.

---

## 5. Mon cout reel, poste par poste, sur 4,0

| Poste | Planifie | Consomme | Ecart |
|---|---|---|---|
| **Lot 1 de sciences** - `signal-information` + `chaine-alimentaire` | 1,5 | **1,5** | 0 |
| **Lot 2 de sciences** - `melanges` + `objet-technique` | 1,5 | **1,5** | 0 |
| **Bloc D** - rejeu borne de `POS-R2` | 0,5 | **0,5** | 0 |
| **Sous-total** | **3,5** | **3,5** | **0** |
| **Marge de rejeu, nommee a l'ouverture, allouee a aucun lot** | 0,5 | **0,0** | **intacte** |
| **Total** | **4,0** | **3,5 / 4,0** | **0,5 disponible** |

**Je n'ai pas tire sur ma marge, et je dis pourquoi.** Un refus ne coute rien de plus qu'un audit :
ce qui coute, c'est le **reaudit** de la correction, et aucune correction n'existe encore. **Ma marge
est intacte au moment ou j'ecris, et elle est deja engagee par les deux refus.**

**Le tarif de 1,5 par lot n'a pas bouge, et il n'a pas ete decoupe.** Je l'avais ecrit le 2026-09-03,
confirme le 2026-09-05, et refuse de le decouper le 2026-09-06 en donnant `COR-R2` pour motif - le
produit ayant enseigne pendant deux sprints qu'on goute avec la bouche, sur la ligne meme ou je
m'etais declaree la source. **Le poste principal de ce tarif, la verification de chaque fait contre
une source, a effectivement ete le plus long des quatre paquets**, et il n'a trouve aucune erreur de
fait : c'est le resultat le plus rassurant du lot.

### Ce que ma marge ne couvre pas, et je le dis maintenant plutot qu'a la cloture

**Deux paquets sont refuses. Deux reaudits sont donc dus, a 0,5 chacun par `PAR-10` : 1,0.** Ma
marge est de **0,5**. **Je peux reauditer une des deux corrections dans s30, pas les deux.**

Trois issues, et **le choix appartient a `camille-po` et a `sofia-sm`, pas a moi** :

1. **Une seule reprise est livree dans s30** - je la reaudite sur ma marge, la seconde va a s31.
   **Aucun depassement.** C'est l'issue que ma composition supporte sans rien changer.
2. **Les deux reprises sont livrees** - le sprint coute **8,5 sur une borne de 8,0**. C'est le
   troisieme depassement de suite, et je l'ai ecrit d'avance : *"une borne qu'on depasse deux sprints
   de suite n'est plus une borne"*.
3. **Aucune reprise n'est livree dans s30** - les deux refus deviennent du backlog de s31, ma marge
   revient au sprint, et **s30 ferme deux lignes de sciences au lieu de quatre**.

**Je reconduis l'engagement de calendrier que j'avais pris** : **j'audite chaque lot le jour de sa
livraison**, jamais en bloc a la cloture. Si une reprise arrive, je la prends dans l'ordre de sa
livraison, sans arbitrer entre les deux paquets - ce n'est pas a moi de prioriser.

---

## 6. Ce que j'avais dit qui casserait, et ce qui a casse

Mon arbitrage prealable, section 7, classait cinq risques par confiance decroissante. Voici le
resultat sur ma moitie, sans reecriture.

| Ce que j'avais annonce | Ce qui s'est passe |
|---|---|
| **1. `SCI-1` et son indice - *"mon attente : c'est ici que le premier refus tombera"*** | **Je me suis trompee.** `SCI-1` est **tenue sur les trois paquets qui la mobilisent**, et l'item 11 d'`objet-technique` en est meme un exemple abouti. La condition que je disais la plus risquee est celle qui est le mieux passee |
| **2. `FER-1` sur les lignes a parenthese longue** | **Sans objet sur mon perimetre** : les quatre lignes servies ont 2, 2, 3 et 3 elements. La parade que j'avais nommee - apparier une ligne large avec une ligne etroite - n'a pas eu a jouer, les quatre lignes etant etroites |
| **3. La couture entre les deux instances** | **Non observable de l'interieur de ma moitie**, par construction, et c'est exactement ce que j'avais ecrit du defaut de s29. Je n'ai pas relu l'instance A et je n'affirme rien sur elle |
| **4. Le depassement de borne** | **Non realise a ce jour sur ma moitie** : 3,5 consommes sur 4,0. **Le risque est reporte sur les reaudits**, et sa mesure est en section 5 |
| **5. La matrice non corrigee** | **Sans consequence sur mon perimetre** : les libelles fautifs etaient sur les lignes 7 et 9, et aucun des quatre paquets ne les sert |

**Ce qui a reellement casse, et je ne l'avais pas annonce** : **les deux refus portent tous deux sur
la repetition d'une regle de fabrication a l'interieur d'une tache** - cinq clones de reponse sur
`chaine-alimentaire`, quatre distracteurs pris chez le voisin sur `objet-technique`. C'est la famille
qu'`OBJ-R2` decrit et que `PAR-6` nomme : **un defaut que la lecture item par item laisse passer un a
un, et que seule la lecture du paquet entier voit.** Je l'avais nomme sur un paquet de CP le
2026-09-06 ; je ne l'avais pas nomme comme le risque principal de s30, et il l'etait.

---

## 7. Ce dont je reste la seule garde sur ce perimetre

Dit explicitement, sinon l'equipe croira couvert ce qui ne l'est pas. **Quatre defauts visibles par
un enfant ont deja traverse une CI entierement verte** ; tant qu'un garde-fou n'existe pas pour une
propriete, **c'est moi le garde-fou, et je le suis en jouant**.

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude d'un fait scientifique** | **aucun.** Aucun test du depot ne sait qu'un enonce scolaire est vrai. C'est le poste principal du tarif a 1,5, et il n'a rien trouve ce sprint |
| **`SCI-1`** - que l'indice corrige une croyance | **aucun.** Un test sait verifier qu'un indice est non vide ; il ne sait pas qu'il corrige quelque chose |
| **`SCI-2`, `BIA-2`, `BIA-3`, `BIA-4`** | **aucun.** Jugements sur ce qu'un item met en cause |
| **`SCI-3`** - la part d'items numeriques **sur le lot** | **aucun**, faute d'une declaration de type par item. Sur ce sprint : verifiee par moi, zero sur 22 dans chaque lot |
| **`SCI-4`** - le nombre de propositions | **mordable** ; **la plausibilite de la troisieme ne l'est pas**, et c'est elle que `SIG-R2` et `MEL-R3` attaquent |
| **`FER-1`, `FER-3`** | **jugements.** `FER-2` est desormais **derivable** : les codes `SCI-CM1-xx` sont alloues et les quatre paquets les declarent |
| **La plausibilite d'un distracteur** | **aucun**, et c'est la propriete qui a produit le plus de reserves de s27 a s30 |
| **`CYC-1`** | **aucun.** Elle compare deux fichiers que rien ne relie |
| **Le niveau reel d'un item** - qu'un item de CM1 demande plus qu'un item de CP | **aucun**, et c'est la moitie du motif du refus sur `objet-technique` |

**Trois proprietes que j'ai gardees ce sprint sont pourtant textuelles, donc mordables**, et je les
route sans prescrire de forme :

1. **Plusieurs items d'un paquet portant la meme bonne reponse** (`chaine-alimentaire`, cinq fois).
2. **La bonne reponse d'un item apparaissant mot pour mot dans son enonce** (`melanges`, item 10).
3. **Un item de cycle 3 portant la meme bonne reponse sur le meme objet qu'un item de cycle 2 deja
   servi** (`objet-technique`, item 6 contre le guidon du CP).

---

## 8. Ce que je route

| Fait | A qui | Ce qu'il en fait |
|---|---|---|
| **`SCI-CM1-05` et `SCI-CM1-02` : `non couvert` -> `couvert-valide`, sans mention de couverture partielle** | `camille-po` | Le compte de la matrice lui appartient. **Je n'y touche pas** |
| **`SCI-CM1-17` et `SCI-CM1-12` restent `non couvert`** | `camille-po` | Idem |
| **Deux refus, avec leur propriete a tenir ecrite dans chaque fiche** | `camille-po`, via `sofia-sm` | En faire du backlog. **Aucun des deux paquets n'est a jeter** : 6 items sur 11 tiennent sur l'un, 7 sur 11 sur l'autre |
| **Six reserves neuves** - `SIG-R1`, `SIG-R2`, `MEL-R1`, `MEL-R2`, `MEL-R3`, `POS-R3` | `camille-po`, via `sofia-sm` | En faire des stories. **Prix total 1,5** en trois rejeux bornes de 0,5, dont aucun ne ferme de ligne |
| **Ma marge (0,5) ne couvre qu'un des deux reaudits dus (1,0)** | `sofia-sm`, `camille-po` | Trois issues chiffrees en section 5. **Le choix ne m'appartient pas** ; je le dis maintenant pour qu'il soit un choix et non une surprise a la cloture |
| **`OBJ-R2` a produit le meme defaut deux fois en deux jours, sur deux niveaux** | `camille-po`, `sofia-sm` | Ce n'est plus une reserve de paquet, c'est un reflexe de fabrication de distracteurs. A traiter comme tel |
| **Trois proprietes textuelles gardees par moi seule et mordables** (section 7) | `theo-archi` | **Je dis la propriete, jamais la forme.** Troisieme sprint de suite ou je route une famille de ce genre |
| **`SCI-3` et `SCI-4` deviennent mordables si le contenu declare le type de chaque item** | `theo-archi` | Deuxieme sprint de suite ou je le route |
| **Le registre d'identifiants de `PAR-5` n'existe toujours pas**, et ma moitie de s30 y ajoute six identifiants (`SIG-R1`, `SIG-R2`, `MEL-R1`, `MEL-R2`, `MEL-R3`, `POS-R3`) | `theo-archi` | Troisieme sprint de suite. **C'est aussi l'une des deux conditions qui bloquent la levee de ma retenue de 1,0**, et elle n'est toujours pas remplie |
| **`SIG-R2` et `MEL-R3` sont le meme defaut sur deux paquets** | `camille-po` | A elle de decider si cela devient une condition generale a s31. **Je n'en cree pas en cours de sprint** |

---

## 9. Ce que ce document ne fait pas

- **Il ne touche pas a `squad/programme/MATRICE-couverture.md`** : je route les cinq changements de
  statut, je ne les ecris pas.
- **Il n'ecrit aucune correction de contenu a la place d'un developpeur.** Chaque motif de refus et
  chaque reserve donne **la propriete a tenir**, jamais le texte : les formulations sont a `lea-dev`,
  les valeurs a `marc-dev`. **Je prescris, ils appliquent, et je rejoue.**
- **Il ne modifie aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/`.** J'ai
  lu les quatre paquets YAML, le catalogue et trois paquets de cycle 2 pour verifier du contenu ;
  je n'y ai rien ecrit.
- **Il n'ecrit aucune story, ne repriorise rien et n'affecte personne.**
- **Il ne corrige ni l'orthographe, ni l'accentuation, ni la ponctuation** de quoi que ce soit : ce
  controle appartient au `relecteur-pedagogique` (`DEC-0002` amende le 2026-08-04, `DEC-0056`), y
  compris sur le texte destine a l'enfant. Ce que je corrige est le **vocabulaire, le ton, le fond et
  le niveau scolaire** de ce que l'enfant lit - c'est a ce titre que *carafe / bol* et le double sens
  de *piece* sont dans ce document.
- **Il ne cree, n'amende ni ne retire aucune condition transverse.** `SIG-R2` et `MEL-R3` sont deux
  reserves de paquet, pas une clause neuve : mes clauses se posent dans l'arbitrage prealable, avant
  le sprint.
- **Il ne juge rien du perimetre de l'instance A** et ne constitue pas une seconde lecture de son
  travail.

**Aucun verdict anterieur n'est efface par ce document.** Les quatre blocs successifs de
`FICHE-bandit-manchot-division-posee-cm1.md` restent lisibles avec leurs motifs entiers, y compris
le refus du 2026-09-05 et les deux reserves levees depuis.
