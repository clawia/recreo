# Arbitrage prealable - s43, lot 1 : cinq lignes de mathematiques CE1

- **Auteur** : `nadia-prof`. **Date** : 2026-09-25. **Sprint** : s43 (arbitrage prealable, avant
  toute story et avant tout avis de faisabilite de `theo-archi`).
- **Ce qui m'est pose** : cinq des quatorze lignes de mathematiques CE1 encore `non couvert` -
  **`MAT-CE1-01`, `MAT-CE1-02`, `MAT-CE1-04`, `MAT-CE1-05`, `MAT-CE1-06`**. `DEC-0099`, section 4,
  constate que ces lignes n'ont *"aucun arbitrage pedagogique prealable ecrit"* et que les engager
  en suppose un. Ce document le rend, pour mon lot seul. Deux autres instances traitent les neuf
  lignes restantes (`MAT-CE1-07`, `-08`, `-10`, `-11`, `-12`, `-14`, `-15`, `-17`, `-18`) : je les
  **signale** quand elles touchent mon lot, je ne les juge pas.
- **Ce document ne prononce aucun Done**, pedagogique ou technique. Il n'ecrit ni backlog ni
  garde-fou, ne cree ni ne deplace aucune ligne de `squad/programme/MATRICE-couverture.md`, ne fixe
  aucun tarif et ne rend aucun avis de faisabilite technique.
- **Ce que je n'ai pas pu jouer, et je le dis plutot que de le laisser croire.** Aucun paquet ne
  sert aucune de ces cinq lignes : il n'y a rien a lancer. C'est un **audit degrade** au sens de ma
  methode, et il l'est entierement. Ce que j'ai fait a la place : relu les deux referentiels de
  cycle 2 et celui de CM1, relu mes quatre arbitrages CE1 anterieurs en entier, relu les fiches des
  jeux deja servis qui touchent mon lot, et **lu le code de production** de la forme d'item de
  designation plutot que de recopier ce qu'une `DEC` en dit (section 0.2, ou je corrige un fait).
- **Lien** : `ARBITRAGE-s36-longueur-d-enonce-au-ce1.md` (`LON-CE1-1`, `LON-CE1-2`),
  `ARBITRAGE-s37-operande-nul-et-forme-du-tirage-a-l-echelle-100.md` (`ADD-CE1-1`, `ADD-CE1-2`),
  `ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md` (`SOU-CE1-1`, `SOU-CE1-2`,
  `CDZ-CE1-1`, `CDZ-CE1-2`, `MOI-CE1-1`, `MOI-CE1-2`),
  `ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` (`MES-CE1-*`, `MON-CE1-*`, `SOL-CE1-*`),
  `ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md` (`DEN-1` a `DEN-8`),
  `ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` (`RAN-1` a
  `RAN-8`, `FRA-9`), `ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`
  (`FER-1` a `FER-3`, `POS-1` a `POS-4`), `DEC-0099`.

---

## 0. Ce que je verifie avant de juger quoi que ce soit

### 0.1 Les seuils CE1 deja poses valent pour ce lot, et je ne les recontredis pas

Sept jeux de mathematiques CE1 sont deja servis. Les regles generales de niveau qu'ils ont laissees
derriere eux **s'appliquent telles quelles a mes cinq lignes**, sans que j'aie a les redire :

| Regle deja posee | Ou elle vit | Ce qu'elle impose a mon lot |
|---|---|---|
| `LON-CE1-1` (au plus 20 mots) et `LON-CE1-2` (au plus trois phrases, la derniere etant la question) | arbitrage de s36 | s'appliquent au champ `enonce` de tout paquet de mon lot. **Rappel de la convention, parce que mon lot en depend plus que les autres** : une fin de phrase se derive (un `.`, `?` ou `!` qui termine le texte ou qu'une majuscule suit), jamais au critere litteral - sinon `4 + 4 + 4 = ?` et `347 < 374` compteraient deux phrases chacun et rougiraient sur du contenu parfaitement lisible |
| Un cas degenere se plafonne, il ne se plancheise pas | `ADD-CE1-1`, `SOU-CE1-1`, `MOI-CE1-1` | je reprends ce regime pour les cas degeneres de mon lot (section 3.3, section 4.3). Au CE1, une propriete acquise au CP ne merite pas un plancher |
| Un paquet de CE1 ne doit pas etre un paquet de CP reetiquete | plafonds des lignes 2 de `ADD-CE1-2`, 3 de `SOU-CE1-2`, 2 de `MOI-CE1-2` | je reprends ce regime : chacune de mes cinq lignes porte une clause d'echelle qui interdit qu'un paquet tienne entierement sous la borne de son equivalent CP |
| `FER-1` (extension), `FER-2` (au moins dix items), `FER-3` (deux taches) decident quand une ligne **peut** fermer | arbitrage de s28 | s'appliquent a mes cinq lignes sans clause supplementaire de ma part. Je ne les recopie pas et je ne les amende pas |
| Une couverture partielle se **nomme dans la matrice**, mot pour mot, et c'est une condition et non une remarque | `FRA-9`, `POS-1`, `DEN-2` | trois de mes cinq lignes en portent une (sections 2, 3, 6) |

**Ce que je ne fais pas** : je ne rouvre ni `ADD-CE1-2` ni `SOU-CE1-2`. Elles gouvernent
`MAT-CE1-03` et rien d'autre. Deux de mes lignes sont voisines de la leur, et c'est le sujet de la
section 0.3.

### 0.2 Une correction de fait, avant que quiconque s'appuie dessus : le palier 2 existe

`DEC-0099`, section 4 bis, ecrit que le palier 2 designe *"une mecanique de `designation`
entierement absente du produit a ce jour"*, en citant mon propre arbitrage de s32
(*"le palier 2 n'est jouable par personne"*). **La citation est exacte ; la phrase qu'elle appuie
est fausse au 2026-09-25**, et je le dis parce que deux de mes cinq lignes en dependent
directement.

**Methode de ma verification, a cote de mon affirmation.** Je n'ai pas conclu depuis une `DEC` ni
depuis ma propre phrase de s32. J'ai lu :

- `adapters/adapter-content/src/main/resources/catalogue.yaml` : deux entrees portent
  `mecanique: designation`, `designation-frise-de-la-journee` et `designation-plan-de-la-classe` ;
- `domain/src/main/java/com/recreo/domain/content/ZoneADesigner.java` et
  `domain/src/main/java/com/recreo/domain/mechanics/designation/DesignationSession.java` : la forme
  d'item et la boucle de partie existent en code de production ;
- `squad/conformite/FICHE-designation-plan-de-la-classe.md` : j'ai moi-meme prononce **VALIDE SOUS
  RESERVE** le 2026-09-21 au second passage, et `QLM-CP-11` a ferme dessus.

Ma phrase de s32 etait vraie le jour ou je l'ai ecrite et elle a ete payee depuis. **Une phrase
datee ne pourrit pas ; une phrase datee recitee au present, si.** Je route ce constat a
`camille-po` via `sofia-sm` (section 8) plutot que de le corriger moi-meme : `DEC-0099` n'est pas
mon document.

**Ce que cette correction ne change pas, et je le dis dans le meme paragraphe pour qu'on ne me
credite pas plus que ce que j'ai verifie.** Elle ne rouvre **pas** `DEN-2`. Ma condition de
denombrement exige une collection sur laquelle l'enfant **agit** - deplacer, marquer, grouper. J'ai
relu `DesignationSession` pour ne pas me contenter du constat de s39 : une manche porte **une**
`bonneZone`, l'enfant designe **une** zone par coup, en au plus trois essais, et **aucune zone ne
change d'etat au sein d'une manche**. Rien dans cette boucle ne permet une suite de gestes sur les
elements d'une meme collection. `DEN-2` reste donc insatisfaite, et la section 2 en tire la
consequence. Ce que la correction ouvre est autre chose : **designer un endroit sur une image fixe**
- ce qui est exactement, et seulement, ce que *"placer sur une ligne graduee"* demande (section 3).

### 0.3 Collisions avec les sept jeux CE1 deja servis - verifiees une par une

| Jeu deja `couvert-valide` | Ligne servie | Collision avec mon lot | Verdict |
|---|---|---|---|
| `bandit-manchot-additions-ce1` | `MAT-CE1-03` | **reelle, avec `MAT-CE1-04`.** J'ai ecrit moi-meme a s37 que la ligne 8 de `ADD-CE1-2` (deux nombres a deux chiffres avec retenue) est plafonnee a 20 % parce qu'*"au-dela, le paquet bascule sur `MAT-CE1-04`, joue mentalement"*. Un item `48 + 26` existe donc **deja** dans le paquet de `MAT-CE1-03` | traitee par `ADP-CE1-3`, section 4 |
| `bandit-manchot-soustractions-ce1` | `MAT-CE1-03` | **reelle, avec `MAT-CE1-05`**, symetrique de la precedente : la ligne 7 de `SOU-CE1-2` (deux a deux chiffres avec emprunt) est plafonnee a 30 % pour le meme motif ecrit | traitee par `SOP-CE1-3`, section 5 |
| `bandit-manchot-complements-dizaine-ce1` | `MAT-CE1-03` | forme `a + ? = d`, `d` multiple de dix jusqu'a 100. Voisine de l'encadrement a la dizaine de `MAT-CE1-02`, **sans etre la meme tache** : l'une demande un terme manquant, l'autre demande entre quelles bornes un nombre se situe | traitee par `ORD-CE1-4`, section 3 |
| `bandit-manchot-moities-ce1` | `MAT-CE1-03` | aucune avec mon lot | rien a faire |
| `bandit-manchot-longueurs-ce1` | `MAT-CE1-09` | aucune avec mon lot. La *regle* graduee de `MAT-CE1-09` et la *ligne* graduee de `MAT-CE1-02` sont deux objets differents - l'une porte des centimetres, l'autre des nombres - mais elles partagent leur obstacle, et c'est ce que dit la section 3.2 | signalee, pas une collision de contenu |
| `bandit-manchot-monnaie-ce1` | `MAT-CE1-13` | faible mais reelle avec `MAT-CE1-04`/`-05` : une addition posee sur des montants serait a la fois un calcul pose et une composition de somme | traitee par `ADP-CE1-6` et `SOP-CE1-6` |
| `bandit-manchot-solides-ce1` | `MAT-CE1-16` | faible avec `MAT-CE1-06` : une disposition en lignes et colonnes dessinee avec des objets en volume serait lue comme un item de geometrie | traitee par `MUL-CE1-5` |

**Et une verification que je fais parce que personne ne me l'a demandee** : `MAT-CE1-01` (jusqu'a
1000) touche-t-elle `bandit-manchot-grands-nombres-cm1` (`MAT-CM1-01`, jusqu'au million) ? **Non.**
Mesure faite sur le fichier servi `cm1-grands-nombres.yaml` : le plus petit nombre qu'il met en jeu
est **2 000**, le plus grand **1 000 000**. Les deux populations ne se recouvrent pas, la frontiere
etant a 1000, que `NUM-CE1-1` borne par le haut.

---

## 1. Les cinq verdicts, en une page

| Ligne | Verdict | Conditions | Plafond de statut atteignable |
|---|---|---|---|
| `MAT-CE1-01` Denombrer, lire, ecrire, decomposer jusqu'a 1000 | **COUVERTURE PARTIELLE nommee** - accepte sous conditions sur *lire*, *ecrire*, *decomposer* ; **refuse sur *denombrer*** | `NUM-CE1-1` a `NUM-CE1-7` | **`en cours`**, jamais `couvert-valide` |
| `MAT-CE1-02` Comparer, ranger, encadrer jusqu'a 1000, et placer sur une ligne graduee | **COUVERTURE PARTIELLE nommee** sur un paquet textuel seul - accepte sous conditions sur *comparer*, *ranger*, *encadrer* ; **le quatrieme verbe n'est pas servable par du texte** | `ORD-CE1-1` a `ORD-CE1-6` | **`en cours`** sur texte seul ; `couvert-valide` atteignable **si** une ligne graduee reellement dessinee est servie en designation |
| `MAT-CE1-04` Poser et effectuer une addition avec retenue | **ACCEPTE SOUS CONDITIONS**, avec mention de couverture partielle exigee | `ADP-CE1-1` a `ADP-CE1-6` | **`couvert-valide`**, a la condition que la mention de `ADP-CE1-1` soit ecrite dans la matrice |
| `MAT-CE1-05` Poser et effectuer une soustraction | **ACCEPTE SOUS CONDITIONS**, avec mention de couverture partielle exigee | `SOP-CE1-1` a `SOP-CE1-6` | **`couvert-valide`**, meme condition de mention |
| `MAT-CE1-06` Comprendre le sens de la multiplication | **ACCEPTE SOUS CONDITIONS** sur *addition reiteree* ; **COUVERTURE PARTIELLE nommee** si la disposition en lignes et colonnes n'est pas dessinee | `MUL-CE1-1` a `MUL-CE1-7` | **`en cours`** sur texte seul ; `couvert-valide` atteignable avec les dessins de `MUL-CE1-5` |

**Aucun de ces cinq verdicts n'est un Done.** Ils disent ce qu'un paquet doit tenir pour passer
devant moi ; ils ne disent pas qu'il passera. Un paquet conforme a toutes les conditions ci-dessous
peut encore etre refuse sur une erreur de fait, une consigne illisible, un distracteur absurde, un
biais ou un glissement de ligne (section 7).

---

## 2. `MAT-CE1-01` - denombrer, lire, ecrire et decomposer jusqu'a 1000

> *"Denombrer, lire, ecrire et decomposer les nombres jusqu'a 1000 (unites, dizaines, centaines)."*

### 2.1 Verdict : COUVERTURE PARTIELLE nommee - trois verbes sur quatre

Cette ligne est la soeur exacte de `MAT-CP-01`, dont j'ai separe les verbes le 2026-08-24 et
reconfirme la separation le 2026-09-22 apres lecture du code. **Je ne deplace pas ce poteau et je ne
le durcis pas** : ce que j'ai refuse au CP pour *denombrer*, je le refuse ici pour la meme raison,
qui est une propriete de la mecanique et non du contenu (section 0.2). Les trois autres verbes sont
servables par une mecanique textuelle, exactement comme ils l'ont ete au CP.

**`NUM-CE1-2` porte la mention, et c'est une condition, pas une remarque** - meme regime que
`DEN-2`, `FRA-9` et `POS-1`. Sans elle ecrite dans la matrice, je ne valide pas le Done qui ferait
bouger la ligne.

### 2.2 Conditions

> **`NUM-CE1-1` - bornes et echelle.** Les nombres mis en jeu sont des entiers de 0 a 1000 inclus.
> **Au moins la moitie des items portent, en position de bonne reponse ou d'enonce, un nombre de
> trois chiffres.** Aucun nombre au-dessus de 1000.
>
> *Motif.* Sans le plancher, le paquet est `cp-nombres-jusqu-a-100.yaml` reetiquete CE1 - la
> compétence voisine plus facile, deja servie et deja validee. La borne haute a 1000 est celle de
> l'intitule, et elle laisse `MAT-CM1-01` commencer ou il commence reellement (2 000, mesure de la
> section 0.3).

> **`NUM-CE1-2` - couverture partielle, mention exacte exigee dans la matrice.** Le jour ou cette
> ligne bouge, `camille-po` ecrit mot pour mot : **"le verbe 'denombrer', premier de cette ligne,
> n'est travaille par aucun jeu ; seuls 'lire', 'ecrire' et 'decomposer' le sont"**. **La ligne
> reste `en cours`, jamais `couvert-valide`**, quel que soit le Done que je prononce sur le paquet.
>
> *Motif.* Identique a `DEN-2`, et la formulation est volontairement la sienne : deux redactions
> d'une meme mention pour deux niveaux divergeraient, et une case fausse est pire qu'une case vide.

> **`NUM-CE1-3` - *lire* et *ecrire* dans les deux sens, dans le meme paquet.** Chiffres vers
> lettres et lettres vers chiffres. Les distracteurs se distinguent par la **valeur designee**,
> jamais par l'orthographe d'un mot-nombre, et la designation litterale d'un enonce est ecrite sans
> faute et de facon constante dans tout le paquet.
>
> *Motif.* `DEN-3` transpose sans changement, `GN-1`/`GN-2` reconduites. Un paquet a sens unique ne
> travaille qu'un des deux verbes.

> **`NUM-CE1-4` - *decomposer* a des items a lui, et la centaine y est.** Au moins **quatre** items
> de decomposition. Au moins **la moitie** d'entre eux emploient le vocabulaire
> **centaines/dizaines/unites** ; au moins **la moitie** d'entre eux portent sur un nombre de trois
> chiffres. L'ecriture additive (`347 = 300 + 40 + 7`) est acceptable, mais elle ne suffit pas a
> tenir cette condition a elle seule.
>
> *Motif.* `DEN-4` disait *dizaines et unites* parce que c'etait la nouveaute du CP. **La nouveaute
> du CE1 est la centaine, et elle est nommee par la parenthese de l'intitule.** Un paquet qui
> decomposerait 47 en 4 dizaines et 7 unites, quinze fois, travaillerait `MAT-CP-01` sous le nom de
> `MAT-CE1-01` - le glissement exact que ma porte existe pour attraper, et que j'ai deja sanctionne
> a s37 sur `MAT-CE1-03`.

> **`NUM-CE1-5` - le mot *decomposer* n'apparait jamais dans ce que l'enfant lit.** Ni dans un
> enonce, ni dans une proposition, ni dans la consigne de catalogue.
>
> *Motif.* `DEN-5` transpose. C'est un mot de maitre. Le CE1 lit mieux que le CP, il ne connait pas
> mieux le vocabulaire de la didactique.

> **`NUM-CE1-6` - les deux pieges de l'echelle 1000 sont servis.** Au moins **deux** items portent
> sur un nombre dont le chiffre des dizaines **ou** celui des unites est zero (par exemple 305, 407,
> 620, 800), et au moins **un** item porte sur un nombre de la tranche 60-99 ou sur un nombre de
> trois chiffres dont les deux derniers chiffres tombent dans cette tranche (par exemple 372, 490).
>
> *Motif, et il est la moitie de la difficulte de cette ligne.* Le zero intercalaire est ce qui
> separe l'enfant qui a compris la position du chiffre de celui qui recite : *trois cent cinq*
> s'ecrit `305` et non `35` ni `3005`. `DEN-7` couvrait les irreguliers et les dizaines rondes au
> CP ; a l'echelle 1000 le zero intercalaire s'y ajoute et pese plus lourd que les deux. Je garde la
> tranche 60-99, qui ne disparait pas en montant d'echelle : *trois cent quatre-vingt-dix-sept* la
> contient entiere.

> **`NUM-CE1-7` - aucun item n'est resoluble sans lire le nombre.** Les trois propositions d'un meme
> item ont le **meme nombre de chiffres**, ou une ecriture litterale de longueur comparable a un mot
> pres.
>
> *Motif.* `DEN-8` transpose. Un item dont la bonne reponse est la seule proposition a trois
> chiffres est une devinette.

### 2.3 Ce que cette section ne fait pas

Elle ne rouvre pas `DEN-2`, elle ne la transpose pas non plus telle quelle : `NUM-CE1-2` est une
clause neuve, sur une population neuve, dont le texte de mention est celui de `DEN-2` pour que les
deux niveaux ne portent pas deux redactions d'une meme limite. `DEN-1` a `DEN-8` restent ce
qu'elles sont, pour le CP, inchangees.

---

## 3. `MAT-CE1-02` - comparer, ranger, encadrer, et placer sur une ligne graduee

> *"Comparer, ranger et encadrer les nombres jusqu'a 1000, et les placer sur une ligne graduee."*

### 3.1 Verdict : COUVERTURE PARTIELLE nommee sur texte seul

Quatre verbes, de deux natures. **Comparer**, **ranger** et **encadrer** sont des operations
numeriques sur des nombres deja ecrits : un paquet textuel les sert entierement. **Placer sur une
ligne graduee** demande une ligne reellement dessinee et graduee, et un geste de l'enfant sur cette
ligne.

### 3.2 Pourquoi le quatrieme verbe ne se sert pas par du texte - et ce que j'ai verifie avant de le redire

**J'ai relu mon propre refus avant de m'appuyer dessus**, comme je dois le faire. Le 2026-08-14,
sur la moitie *"reperage sur une droite numerique"* de `MAT-CM1-06`, j'ai ecrit : *"le reperage
exige une ligne reellement dessinee et graduee, et je prefere une case vide a une case fausse"*.
`FRA-9` l'a reconfirme le 2026-08-30, et la moitie est restee non couverte depuis.

Ce refus tient ici mot pour mot, et pour la meme raison qu'a s39 sur la regle graduee de
`MAT-CE1-09` : **donner la position dans l'enonce, c'est avoir place le nombre a la place de
l'enfant.** Un item *"Sur une ligne graduee de 0 a 1000 de 100 en 100, entre quelles graduations se
trouve 450 ?"* n'est pas un placement, c'est un encadrement deguise - donc le troisieme verbe servi
une seconde fois sous le nom du quatrieme.

**Ce que je corrige de mon propre dossier, et c'est un fait neuf de 2026-09-25.** En aout, le
quatrieme verbe etait hors de portee parce qu'**aucune mecanique** ne permettait a l'enfant de
montrer un endroit sur une image. Ce n'est plus vrai (section 0.2). Une ligne graduee dessinee,
portant des zones declarees par le contenu, et un enonce *"Ou se place 450 ?"* est **exactement** ce
que `ZoneADesigner` sert : une image fixe, un ensemble fini de zones, une bonne zone, un geste par
manche. **Le verbe cesse d'etre bloque par une mecanique absente et devient une question de
materiau** - la meme nature de manque que pour les cinq dessins de solides a s39, et pas celle de
`S39-DEN` ou c'est la boucle elle-meme qui manque. Je le nomme pour que `theo-archi` ne confonde
pas les deux natures, et **je ne prejuge pas du cout** : combien de lignes graduees, a quelles
echelles, et si cela tient dans un sprint ne sont pas de mon ressort.

### 3.3 Conditions

> **`ORD-CE1-1` - bornes et echelle.** Les nombres compares, ranges ou encadres sont des entiers de
> 0 a 1000 inclus, deux a deux distincts a l'interieur d'un meme item. **Au moins la moitie des
> items mettent en jeu au moins un nombre de trois chiffres.**
>
> *Motif.* Sans ce plancher, le paquet est `cp-comparer-nombres.yaml` et `cp-ranger-nombres.yaml`
> reetiquetes CE1 : ces deux paquets existent, sont servis et sont valides sur `MAT-CP-02`, qui va
> jusqu'a 100. La collision serait totale.

> **`ORD-CE1-2` - *ranger* reprend `RAN-1` a `RAN-8`, avec deux valeurs changees et rien d'autre.**
> `RAN-1` (forme et sens unique sur tout le paquet), `RAN-4` (les deux distracteurs sont derives et
> la derivation est nommee a cote de l'item), `RAN-5`, `RAN-6`, `RAN-7` et `RAN-8` s'appliquent sans
> changement, dans leur redaction amendee du 2026-08-30. Deux valeurs seulement changent :
> - `RAN-2` devient **des entiers de 0 a 1000** au lieu de 0 a 100 ;
> - `RAN-3` devient **au plus 16 caracteres, espaces comprises**, au lieu de 12.
>
> *Motif du 16, calcule et pas choisi.* Une proposition s'ecrit `a, b, c`. Avec trois entiers
> distincts d'au plus 1000, le pire cas est un nombre a quatre chiffres et deux a trois chiffres :
> `1000, 380, 308` fait **15** caracteres. Je pose 16 pour ne pas border au caractere pres. **Ce
> n'est pas un poteau que je deplace** : `RAN-3` reste a 12 pour le CP, sur la population ou je l'ai
> mesuree ; c'est une clause neuve pour une population neuve, et je l'ecris comme telle plutot que
> de retoucher l'ancienne.

> **`ORD-CE1-3` - *comparer* garde ses items propres, et ils ne sont pas des items de rangement a
> trois nombres.** Au moins **trois** items posent une comparaison de deux nombres (`<`, `>`, `=`,
> ou *"lequel est le plus grand"*), distincts des items de rangement.
>
> *Motif.* `FER-3` demande deux taches distinctes ; ranger trois nombres et comparer deux nombres
> sont deux operations mentales differentes, et la seconde est celle qui se travaille encore au CE1
> a l'echelle de la centaine. Une ligne qui enumere trois verbes et n'en sert qu'un ne tient pas
> `FER-1`.

> **`ORD-CE1-4` - *encadrer* a des items a lui, aux deux echelles.** Au moins **quatre** items
> d'encadrement. **Les deux echelles sont servies** : encadrer a la dizaine (`340 < 347 < 350`) et
> encadrer a la centaine (`300 < 347 < 400`), chacune par au moins un item. L'enonce nomme
> explicitement l'echelle demandee.
>
> *Motif de l'echelle nommee.* Sans elle, `347` a deux encadrements justes et l'enfant ne peut pas
> savoir lequel on attend : l'item devient une devinette, et deux propositions seraient vraies -
> defaut que je refuse immediatement en jouant.
>
> *Motif de la separation d'avec `bandit-manchot-complements-dizaine-ce1`.* Ce jeu sert deja
> `a + ? = d`, `d` etant la dizaine superieure. La forme est voisine, la tache ne l'est pas :
> l'un demande **de combien** il faut avancer, l'autre demande **entre quoi et quoi** le nombre se
> trouve. Un item d'encadrement dont la reponse attendue serait le complement (`3` plutot que
> `340 et 350`) sert `MAT-CE1-03` sous le nom de `MAT-CE1-02`, et je le refuserai.

> **`ORD-CE1-5` - couverture partielle, mention exacte exigee dans la matrice tant que la ligne
> graduee n'est pas dessinee.** Le jour ou cette ligne bouge sur un paquet textuel, `camille-po`
> ecrit mot pour mot : **"comparer, ranger et encadrer seuls ; le verbe 'placer sur une ligne
> graduee' n'est servi par aucun jeu et ne peut pas l'etre par du texte"**. **La ligne reste
> `en cours`.**

> **`ORD-CE1-6` - si une ligne graduee est dessinee, elle l'est reellement.** La ligne porte des
> graduations visibles et regulierement espacees ; le pas de graduation est constant sur une meme
> image ; les nombres portes en reference sont ecrits sur la ligne, et **l'enonce ne dit jamais
> entre quelles graduations la reponse se trouve**. Les zones a designer sont reparties le long de
> la ligne, et la bonne zone n'est ni systematiquement la premiere ni systematiquement la derniere.
>
> *Motif du dernier point.* C'est la transposition de ce que j'ai fait mordre partout ailleurs sur
> le rang de la bonne reponse : une bonne zone toujours au meme endroit se trouve sans lire le
> nombre. *Motif de l'avant-dernier point* : c'est `SOL-CE1-1` retourne - un enonce qui dit la
> position fait le travail perceptif a la place de l'enfant.

---

## 4. `MAT-CE1-04` - poser et effectuer une addition avec retenue

> *"Poser et effectuer une addition avec retenue (nombres a deux ou trois chiffres)."*

### 4.1 Verdict : ACCEPTE SOUS CONDITIONS, avec mention de couverture partielle

Le precedent est etabli et il est a moi : **quatre lignes de calcul pose ou de calcul tout court
sont deja `couvert-valide` sur ce produit sur le raisonnement "le resultat atteste le calcul"**, et
j'ai ferme `MAT-CM1-04` (multiplication posee) le 2026-09-04 sous `POS-1` a `POS-4`. Je ne deplace
pas ce poteau : ce que j'ai accepte pour la multiplication posee au CM1, je l'accepte pour
l'addition posee au CE1, aux memes conditions transposees.

**Aucune ligne de CM1 ne sert l'addition ni la soustraction posees** - verifie ligne par ligne sur
les douze lignes de `squad/programme/mathematiques/competences-cm1.md`. Il n'y a donc **aucun
precedent de contenu** a reprendre ni a contredire sur ces deux operations : `MAT-CE1-04` et
`MAT-CE1-05` sont les premieres.

### 4.2 Conditions

> **`ADP-CE1-1` - mention exacte exigee dans la matrice, mot pour mot.** **"le resultat atteste le
> calcul ; la technique posee elle-meme - disposition en colonnes, retenues - n'est ni montree ni
> verifiee par le produit."** **Sans cette mention ecrite dans la matrice, je ne ferme pas la
> ligne.**
>
> *Motif.* `POS-1` transpose, et le verbe *poser* est le premier de l'intitule. Le produit demande
> a l'enfant un geste qu'il ne peut pas verifier ; la consigne de catalogue doit le lui demander
> quand meme (c'est ce que j'ai loue sur `bandit-manchot-multiplication-posee-cm1`), et la matrice
> doit dire que le produit ne le verifie pas.

> **`ADP-CE1-2` - `FER-1` sur la parenthese, et la retenue est dans tous les items.** Les deux
> combinaisons de l'intitule sont servies : **nombres a deux chiffres** et **nombres a trois
> chiffres**, chacune par au moins trois items. **Tout item de cette ligne comporte au moins une
> retenue** - l'intitule dit *"avec retenue"*. Au moins **deux** items portent une retenue sur la
> colonne des dizaines (donc une centaine formee), et au moins **un** item porte **deux** retenues.
>
> *Motif du dernier point.* La double retenue (`178 + 265`) est l'endroit ou la technique posee cesse
> d'etre remplacable par un calcul mental : c'est ce que cette ligne existe pour travailler, et un
> paquet qui n'en porterait aucune servirait `MAT-CE1-03` plus haut en echelle.

> **`ADP-CE1-3` - separation d'avec `MAT-CE1-03`, deja servie.** **Au moins les deux tiers des
> items mettent en jeu un nombre de trois chiffres ou produisent une somme superieure a 100.**
>
> *Motif, et il est mesurable contre ce qui est deja servi.* `bandit-manchot-additions-ce1` sert
> deja, sous `MAT-CE1-03`, des tirages `a + b` avec retenue aux unites a deux chiffres, dans la
> limite de 100 : c'est la ligne 8 de `ADD-CE1-2`, que j'ai plafonnee a 20 % en ecrivant que
> au-dela *"le paquet bascule sur `MAT-CE1-04`, joue mentalement"*. Un paquet de `MAT-CE1-04` qui
> resterait sous 100 serait donc **la meme matiere servie deux fois sous deux codes de ligne**.
> `ADR-0081` verifie qu'un code resout et qu'un item ne sert qu'une ligne ; il ne verifie jamais que
> la declaration dit vrai. Cette clause-ci est le seul garde-fou de ce recouvrement.

> **`ADP-CE1-4` - l'item ne se repond pas sans poser l'operation.** Les trois propositions ont le
> **meme nombre de chiffres** et le **meme ordre de grandeur**, et **au moins un distracteur partage
> le chiffre des unites avec la bonne reponse**.
>
> *Motif.* `POS-2` transpose sans changement. Sinon l'enfant elimine par la longueur ou par le
> dernier chiffre, et l'item ne teste rien.

> **`ADP-CE1-5` - chaque distracteur est une erreur classique nommee, declaree dans le paquet.**
> Les formes admises, et seulement celles-la : **retenue oubliee** (chaque colonne additionnee
> separement, le chiffre des dizaines de la somme d'une colonne etant perdu) ; **retenue reportee
> deux fois** ; **retenue reportee dans la mauvaise colonne**. Tous les distracteurs sont des
> entiers positifs, distincts entre eux et de la bonne reponse ; si une forme coincide avec la bonne
> reponse ou avec un distracteur deja choisi, une autre forme de la liste est prise.
>
> *Motif.* `POS-3` transpose. La retenue oubliee est, a cette echelle, exactement ce que l'exercice
> veut faire voir : un enfant qui choisit `48 + 26 -> 614` vient de commettre dans sa tete l'erreur
> que la technique posee sert a empecher. **Et c'est la reserve `ADD-CE1-R2` que j'ai ouverte puis
> fait fermer a s38**, sur un item qui n'offrait pas la retenue oubliee ; je l'ecris ici en clause
> plutot que d'attendre de la rouvrir en reserve.
>
> *Correction du 2026-10-01 (`nadia-prof`, s45).* L'exemple `48 + 26 -> 614` du motif ci-dessus est
> faux : c'est la somme entiere de chaque colonne ecrite cote a cote, pas la retenue oubliee. **La
> definition de la clause fait foi : `48 + 26 -> 64`.** Les trois formes sont definies colonne par
> colonne, avec leur portee et leur lecture sous `ADP-CE1-4`, dans
> `squad/conformite/ARBITRAGE-PREALABLE-s45-cinq-lots.md`, section 2. Le texte ci-dessus n'est pas
> efface.

> **`ADP-CE1-6` - aucun item n'est habille d'une situation, ni ecrit en unites.** Les items portent
> des nombres nus. Ni euros, ni centimes, ni centimetres, ni grammes, ni ecriture decimale.
>
> *Motif double.* D'abord `MAT-CE1-08` (problemes a une ou deux etapes) et `MAT-CE1-13` (monnaie,
> deja `couvert-valide`) : une addition posee habillee d'une situation est un probleme, et une
> addition posee sur des montants est une composition de somme. Ensuite, et c'est le motif
> pedagogique : cette ligne travaille la **technique**, pas la modelisation. Un habillage rajoute
> une etape de comprehension avant le calcul et deplace ce qui est teste.

---

## 5. `MAT-CE1-05` - poser et effectuer une soustraction

> *"Poser et effectuer une soustraction (nombres a deux ou trois chiffres)."*

### 5.1 Verdict : ACCEPTE SOUS CONDITIONS, avec mention de couverture partielle

Meme famille que la section 4, meme precedent, meme regime de mention. **Une difference d'intitule
que je releve plutot que de la lisser** : `MAT-CE1-04` dit *"avec retenue"*, `MAT-CE1-05` ne dit
pas *"avec emprunt"*. Sa parenthese ne porte donc que les deux tailles de nombres. Je n'ajoute pas
un mot a l'intitule - ce serait fixer une competence par la bande - mais je refuse qu'un paquet
sans aucun emprunt ferme cette ligne, et `SOP-CE1-2` dit pourquoi.

### 5.2 Conditions

> **`SOP-CE1-1` - mention exacte exigee dans la matrice, mot pour mot.** **"le resultat atteste le
> calcul ; la technique posee elle-meme - disposition en colonnes, emprunts - n'est ni montree ni
> verifiee par le produit."** **Sans cette mention ecrite dans la matrice, je ne ferme pas la
> ligne.**

> **`SOP-CE1-2` - `FER-1` sur la parenthese, et les deux formes de soustraction sont servies.** Les
> nombres a deux chiffres et les nombres a trois chiffres sont chacun servis par au moins trois
> items. **Au moins la moitie des items comportent un emprunt**, et au moins un item comporte deux
> emprunts. Au moins **deux** items n'en comportent aucun.
>
> *Motif du plancher a la moitie.* Sans emprunt, une soustraction posee se fait colonne par colonne
> sans rien de la technique : le paquet testerait la table de soustraction, pas l'algorithme.
> *Motif du plancher inverse a deux items sans emprunt.* Un enfant doit rencontrer le cas ou il ne
> faut **pas** emprunter, sinon il emprunte par reflexe - c'est l'erreur symetrique, et elle est
> reelle.
>
> **Ce plancher ne contredit pas `SOU-CE1-2`, et je le dis avant qu'on me le demande.** `SOU-CE1-2`
> plafonne la forme *"deux a deux chiffres avec emprunt"* a **30 %** dans un paquet de **calcul
> mental** (`MAT-CE1-03`), parce qu'au-dela ce paquet-la bascule sur la technique posee. Ici c'est
> la technique posee qui **est** la competence : le plancher et le plafond portent sur deux lignes
> differentes et disent la meme chose - l'emprunt appartient a `MAT-CE1-05`.

> **`SOP-CE1-3` - separation d'avec `MAT-CE1-03`, deja servie.** **Au moins les deux tiers des items
> mettent en jeu un nombre de trois chiffres, ou ont un premier terme superieur a 100.**
>
> *Motif.* Symetrique exact de `ADP-CE1-3`. `bandit-manchot-soustractions-ce1` sert deja, sous
> `MAT-CE1-03`, `a - b` avec `0 <= b <= a <= 100`, emprunt compris (ligne 7 de `SOU-CE1-2`). Sans
> cette clause, les deux paquets porteraient la meme matiere sous deux codes de ligne.

> **`SOP-CE1-4` - l'item ne se repond pas sans poser l'operation.** Memes exigences que
> `ADP-CE1-4` : meme nombre de chiffres, meme ordre de grandeur, au moins un distracteur partageant
> le chiffre des unites avec la bonne reponse.

> **`SOP-CE1-5` - chaque distracteur est une erreur classique nommee, declaree dans le paquet.**
> Les formes admises, et seulement celles-la : **l'emprunt oublie**, c'est-a-dire la soustraction
> chiffre par chiffre en valeur absolue - pour deux nombres a deux chiffres,
> `10 * (dizaine(a) - dizaine(b)) + |unite(a) - unite(b)|`, et la derivation colonne par colonne
> correspondante pour trois chiffres ; **l'emprunt non reporte** sur la colonne suivante ; **la
> somme au lieu de la difference** (`a + b`). Tous les distracteurs sont des entiers positifs,
> distincts entre eux et de la bonne reponse ; si une forme coincide, une autre forme de la liste
> est prise.
>
> *Motif, et je reprends volontairement ma propre formule plutot que d'en ecrire une seconde.* La
> premiere forme est **mot pour mot** celle que j'ai posee a s38 pour les soustractions mentales
> (section 1.4 de l'arbitrage de s38). Deux redactions d'une meme erreur divergeraient. Elle est ici
> la plus identifiable des trois, et elle est l'erreur que la technique posee existe pour empecher.
> **La troisieme forme est la reparation d'avance de `SOU-CE1-R1`**, reserve que j'ai ouverte a s38
> sur quatre items dont le second distracteur ne correspondait a aucune erreur plausible, et dont le
> correctif que j'avais donne etait precisement *"remplacer par la somme `a + b`"*.
>
> *Amendement du 2026-10-01 (`nadia-prof`, s46).* Les trois formes ci-dessus donnent toujours une
> valeur superieure a la bonne reponse (`theo-archi`, `squad/rapports/avis-faisabilite-s46-cinq-lots.md`,
> section 3.2, constat verifie). **Une quatrieme forme est admise : l'emprunt reporte deux fois**,
> de valeur `a - b - 10^(i+1)`, avec sa portee, sa regle d'admission et une phrase sur le rang du
> paquet entier, dans `squad/conformite/FICHE-bandit-manchot-soustractions-posees-ce1.md`, section 2.
> Le texte ci-dessus n'est pas efface ; la liste "et seulement celles-la" compte desormais quatre
> formes.
>
> *Amendement du 2026-10-02 (`nadia-prof`, s47, arbitrage de `SOP-CE1-R1`).* **Une cinquieme forme
> est admise : le decalage des colonnes**, de valeur `a - 10 x b`, **obligatoire sur tout item sans
> emprunt**, avec sa regle d'admission, dans
> `squad/conformite/FICHE-bandit-manchot-soustractions-posees-ce1.md`, section "Arbitrage de
> `SOP-CE1-R1`". La liste "et seulement celles-la" compte desormais cinq formes.

> **`SOP-CE1-6` - bornes, resultat jamais negatif, et aucun habillage.** `0 <= b <= a <= 1000`.
> Aucun item n'est habille d'une situation, ni ecrit en euros, centimes, centimetres, grammes ni en
> ecriture decimale.
>
> *Motif.* Identique a `ADP-CE1-6`, plus la borne du signe : un resultat negatif est hors programme
> de CE1, et le paquet de `MAT-CE1-03` porte deja cette borne (`SOU-CE1-1` et son perimetre d'AC).

---

## 6. `MAT-CE1-06` - comprendre le sens de la multiplication

> *"Comprendre le sens de la multiplication (addition reiteree, disposition en lignes et en
> colonnes)."*

### 6.1 Verdict : ACCEPTE SOUS CONDITIONS, couverture partielle si les dispositions ne sont pas dessinees

La parenthese nomme **deux** representations, et elles ne sont pas de meme nature :

- **l'addition reiteree** (`4 + 4 + 4 = 4 x 3`) est une ecriture, donc entierement servable par du
  texte ;
- **la disposition en lignes et en colonnes** est une **image**. Son interet pedagogique entier est
  que l'enfant voie **la meme collection** comme trois rangees de cinq et comme cinq colonnes de
  trois : c'est de la que sort la commutativite, et c'est ce que le texte ne peut pas faire.

**Un enonce qui dit *"il y a 3 rangees de 5 gommettes"* n'est pas une disposition, c'est sa
description** - et la description a deja fait, a la place de l'enfant, le travail de lecture que la
representation vise. C'est mot pour mot le motif sous lequel j'ai refuse les solides en texte a s39
(*"ce n'est pas de la reconnaissance, c'est de la traduction"*), et je l'applique ici sans le
durcir : je ne refuse pas la ligne, je refuse que ce verbe-la soit tenu pour servi par un enonce.

**La nature du manque est celle des solides, pas celle de `S39-DEN`.** La forme d'item illustre
(`ADR-0073`) existe, elle est servie, et elle a ferme `MAT-CE1-16` le 2026-09-22 sur quinze dessins
que j'ai regardes a leur taille reelle. Ce qui manque ici est le **materiau** - les dessins de
reseaux rectangulaires -, pas la mecanique. Je nomme cette difference pour que `theo-archi` ne
traite pas les deux de la meme facon, et **je ne prejuge pas du cout**.

### 6.2 Conditions

> **`MUL-CE1-1` - bornes.** Les deux facteurs sont des entiers de 2 a 10 inclus ; le produit est au
> plus 100.
>
> *Motif du plancher a 2.* `a x 1` et `a x 0` ne montrent aucune reiteration : ce sont des cas
> degeneres, de la meme famille que ceux que `ADD-CE1-1` et `SOU-CE1-1` plafonnent, et ici je les
> exclus totalement plutot que de les plafonner - **meme raisonnement que `CDZ-CE1-1`** : ce lot n'a
> qu'une poignee de formes, un item degenere y couterait bien plus cher que dans un paquet melange.
> *Motif du plafond a 10.* Au-dela, l'enfant ne peut plus verifier en comptant, et c'est justement
> par le comptage que le *sens* se construit avant que la table ne soit sue.

> **`MUL-CE1-2` - aucun item ne se presente comme un produit nu.** Aucun item de cette ligne n'a
> pour enonce `a x b = ?` seul. Chaque item montre soit l'addition reiteree ecrite, soit une
> disposition dessinee.
>
> *Motif, et c'est la separation la plus importante de cette section.* `MAT-CE1-07` (*"Calcul mental
> - tables de multiplication de 2, 5 et 10, puis de 3 et 4"*) est la ligne du rappel automatise ; un
> item `4 x 7 = ?` la sert, elle, et pas celle-ci. **La difference n'est pas de difficulte, elle est
> de nature** : l'une demande de se souvenir, l'autre demande de comprendre pourquoi. Un paquet de
> `MAT-CE1-06` fait de produits nus servirait `MAT-CE1-07` sous un autre nom - le glissement exact
> que j'ai sanctionne a s37 entre `MAT-CE1-03` et `MAT-CE1-04`. `MAT-CE1-07` n'est pas dans mon lot
> et je ne la juge pas ; cette clause protege la mienne, pas la sienne.

> **`MUL-CE1-3` - les deux sens de l'addition reiteree, et ce sont deux taches.** Au moins **trois**
> items vont de la somme repetee vers le produit (*"4 + 4 + 4, c'est quelle multiplication ?"*), et
> au moins **trois** items vont du produit vers la somme repetee (*"3 x 5, c'est quelle addition
> ?"*).
>
> *Motif.* `FER-3` : deux taches distinctes, la moins servie portant au moins trois items. Et
> pedagogiquement, le second sens est celui qui distingue l'enfant qui a compris de celui qui a
> appris a traduire dans un seul sens.

> **`MUL-CE1-4` - la commutativite est servie, et par la disposition.** Au moins **deux** items
> portent sur une meme disposition lue dans les deux sens - rangees fois colonnes et colonnes fois
> rangees - et **l'enonce ou l'explication dit que les deux comptes donnent le meme total**.
>
> *Motif.* C'est la seule chose que la disposition en lignes et en colonnes apporte et que l'addition
> reiteree n'apporte pas. Une ligne dont la parenthese nomme cette representation et qui n'en tirerait
> pas ce fait-la ne la sert pas, elle l'illustre.

> **`MUL-CE1-5` - si une disposition est dessinee, elle l'est reellement.** Les elements sont
> **identiques entre eux** (meme forme, meme taille, meme couleur) et alignes en un reseau
> rectangulaire regulier ; au plus **dix** rangees et **dix** colonnes ; chaque element reste
> distinguable de son voisin a la taille reelle de rendu. **Les elements ne sont ni des solides, ni
> des figures planes nommees par le programme** (carre, rectangle, triangle, cercle, cube, pave,
> boule, cylindre, cone) : un rond plein ou un point. **L'enonce ne dit jamais le nombre de rangees
> ni le nombre de colonnes.**
>
> *Motif du dernier point.* C'est la clause qui fait la difference entre lire une disposition et
> lire une phrase qui la decrit. Sans elle, le dessin devient une decoration.
> *Motif de l'exclusion des formes nommees.* `MAT-CE1-15` et `MAT-CE1-16` (deja `couvert-valide` sur
> les solides) travaillent la reconnaissance de ces formes-la. Un reseau de petits carres serait lu
> par un enfant de CE1 comme un item de geometrie, et par moi comme un recouvrement.
> *Motif de la couleur unique.* `SOL-CE1-3` transpose : la couleur n'est jamais discriminante.

> **`MUL-CE1-6` - distracteurs, et les trois rangs de la bonne reponse sont atteignables.** Les
> formes admises, et seulement celles-la : **la somme des deux facteurs** (`a + b`, confusion
> d'operation) ; **une rangee oubliee** (`a x (b - 1)`) ; **une rangee de trop** (`a x (b + 1)`) ;
> **une colonne de trop** (`(a + 1) x b`), et, quand `a = b`, `a x (b + 2)` a sa place. Tous les
> distracteurs sont des entiers positifs, distincts entre eux et de la bonne reponse.
>
> **Deux collisions que je nomme plutot que de les laisser decouvrir a l'ecriture du premier item ou
> elles se produisent** : `a + b` egale `a x b` **exactement** quand `a = b = 2` (4 dans les deux
> cas), et cette forme est alors interdite sur cet item ; `a x (b + 1)` egale `(a + 1) x b`
> **exactement** quand `a = b`, d'ou le remplacement par `a x (b + 2)`.
>
> **Verification que les trois rangs sont reellement atteignables, faite a la main plutot que
> supposee** - c'est l'omission que j'ai payee a s38 sur les moities, ou trois formes de distracteurs
> condamnaient le rang *"plus grande"* a 0 % sur tout paquet, et que je ne repete pas :
>
> | Paire de distracteurs | Position de la bonne reponse | Condition |
> |---|---|---|
> | `a + b`, `a x (b - 1)` | **plus grande** | `a` et `b` non tous deux egaux a 2 |
> | `a x (b - 1)`, `a x (b + 1)` | **intermediaire** | aucune |
> | `a x (b + 1)`, `(a + 1) x b` | **plus petite** | `a` different de `b` |
> | `a x (b + 1)`, `a x (b + 2)` | **plus petite** | `a` egal a `b` |
>
> Les trois rangs sont atteints, et le rang *"plus petite"* l'est par une paire qui existe pour
> chaque couple de facteurs admissible. Un paquet qui repartit ses items entre ces quatre paires
> tient les trois rangs au-dessus de 10 %, sans qu'aucun n'atteigne la majorite.

> **`MUL-CE1-7` - couverture partielle, mention exacte exigee dans la matrice tant que les
> dispositions ne sont pas dessinees.** Si le paquet livre ne porte que de l'addition reiteree
> ecrite, `camille-po` ecrit mot pour mot : **"addition reiteree seule ; la disposition en lignes et
> en colonnes n'est servie par aucun jeu et ne peut pas l'etre par du texte"**, et **la ligne reste
> `en cours`**. Si les dispositions sont dessinees et que `MUL-CE1-4` et `MUL-CE1-5` sont tenues,
> aucune mention n'est due et la ligne peut atteindre `couvert-valide`.

---

## 7. Ce dont je reste la seule garde, et qu'aucun automate ne verra

Les trente-deux conditions ci-dessus sont **decidables sur l'item seul ou sur la liste des items** - donc
automatisables sur le patron exact de `ADD-CE1-2` et de `SOU-CE1-2` -, a une exception que je nomme :
`ORD-CE1-6` et `MUL-CE1-5` portent sur **ce qu'un dessin montre**, et cela ne se compte pas. **Tant
qu'aucun garde-fou n'execute les autres, c'est moi qui les tiens, en comptant.** Restent en plus
dans ma seule main, et je les nomme pour que l'equipe ne croie pas couvert ce qui ne l'est pas :

1. **Que chaque paquet livre travaille reellement la ligne qu'il declare.** C'est le risque le plus
   cher de ce lot, et il est structurel : quatre de mes cinq lignes ont une voisine **plus facile ou
   deja servie** a un pas de distance - `MAT-CP-01` pour `MAT-CE1-01`, `MAT-CP-02` pour
   `MAT-CE1-02`, `MAT-CE1-03` pour `MAT-CE1-04` et `MAT-CE1-05`, `MAT-CE1-07` pour `MAT-CE1-06`.
   `ADR-0081` verifie qu'un code de ligne resout et qu'un item n'en sert qu'une ; il ne verifie
   **jamais** que la declaration dit vrai.
2. **La lisibilite reelle** d'un enonce conforme a `LON-CE1-1` et `LON-CE1-2`, et **l'accessibilite
   du vocabulaire**. Le compteur ne voit ni une subordonnee ni un referent elide.
3. **L'exactitude arithmetique** de chaque enonce, de chaque bonne reponse et de chaque distracteur,
   et la **plausibilite** de chaque distracteur - qu'il soit reellement l'erreur classique qu'il
   declare, et pas un nombre choisi pour remplir la case.
4. **Ce qu'un dessin montre** : qu'une graduation soit lisible a la taille reelle de rendu
   (`ORD-CE1-6`), qu'un reseau rectangulaire se compte a l'oeil sans se confondre (`MUL-CE1-5`).
   C'est exactement la part que j'ai gardee sur l'ellipse d'equateur de la boule a s39, et aucun
   automate ne la fera a ma place.
5. **La coherence de l'indice** avec la tache. Les indices n'existent pas encore ; je les jugerai a
   l'audit, comme sur les additions de s37 ou j'avais signale d'avance que l'indice unique d'un
   paquet pouvait ne pas aider a toutes ses formes - et ou `ADD-CE1-R1` m'a donne raison.
6. **La progression a l'interieur d'une partie.** Toutes mes clauses de repartition sont des
   distributions ; aucune ne dit l'ordre des manches.
7. **Que les mentions de couverture partielle soient reellement ecrites dans la matrice**
   (`NUM-CE1-2`, `ORD-CE1-5`, `ADP-CE1-1`, `SOP-CE1-1`, `MUL-CE1-7`). Aucun test ne lit la matrice.

---

## 8. Collisions avec les neuf lignes CE1 hors de mon lot - signalees, pas jugees

Une autre instance traite `MAT-CE1-07`, `-08`, `-10`, `-11`, `-12`, `-14`, `-15`, `-17` et `-18`.
**Je ne rends aucun verdict sur elles et je n'ecris aucune condition qui les gouverne.** Je signale
les recouvrements que j'ai vus en instruisant mon lot, pour qu'ils soient traites plutot que
decouverts a la livraison.

| Ligne hors lot | Avec quelle ligne de mon lot | Nature du recouvrement | Ce que ma clause fait deja de mon cote |
|---|---|---|---|
| `MAT-CE1-07` tables de 2, 5, 10, puis 3 et 4 | `MAT-CE1-06` | **le plus serre du referentiel.** Un produit nu sert les tables ; le meme produit accompagne de sa reiteration sert le sens. La frontiere est la forme de l'item, pas la valeur | `MUL-CE1-2` interdit le produit nu de mon cote |
| `MAT-CE1-08` problemes a une ou deux etapes | `MAT-CE1-04`, `-05`, `-06` | un calcul pose habille d'une situation **est** un probleme a une etape ; une addition reiteree habillee aussi | `ADP-CE1-6` et `SOP-CE1-6` interdisent tout habillage de mon cote. **Rien n'interdit de mon cote qu'un item de `MAT-CE1-08` porte une addition posee** : c'est a l'autre instance de decider si elle le borne |
| `MAT-CE1-18` sens de la division (partage et groupement) | `MAT-CE1-06` | **symetrique et reel.** Un reseau rectangulaire lu *"36 gommettes en 6 rangees, combien par rangee ?"* est un groupement, donc de la division, sur exactement le meme dessin | `MUL-CE1-3` fixe les deux sens que **je** sers ; le dessin, lui, est partageable, et c'est ce partage qui merite d'etre decide |
| `MAT-CE1-14` se reperer et coder un deplacement sur un quadrillage | `MAT-CE1-06` | un reseau rectangulaire regulier **est** un quadrillage ; et si `MAT-CE1-14` ouvre en designation, les deux lignes voisineront sur la meme forme d'image | `MUL-CE1-5` impose des elements identiques et sans reperage, ce qui ecarte la lecture "case A3" - sans fermer la question |
| `MAT-CE1-15` figures planes (carre, rectangle, triangle, triangle rectangle, cercle) | `MAT-CE1-06` | un reseau dessine avec des petits carres se lit comme un item de geometrie | `MUL-CE1-5` exclut nommement ces formes de mes elements |
| `MAT-CE1-11` heure et calendrier ; `MAT-CE1-12` durees | `MAT-CE1-02` | faible : une frise horaire est aussi une ligne graduee, et `designation-frise-de-la-journee` existe deja au CP. Si `MAT-CE1-11` ouvre en designation sur une frise, deux lignes serviront des images de la meme famille | `ORD-CE1-6` borne ce que **ma** ligne graduee porte : des nombres, pas des heures |
| `MAT-CE1-10` masses et contenances (g, kg, L) | `MAT-CE1-01`, `-02` | faible : les nombres de cette ligne vont naturellement jusqu'a 1000 (1 kg = 1000 g), et un item de conversion ressemble a un item de numeration | `ADP-CE1-6` et `SOP-CE1-6` excluent les unites de mes deux lignes de calcul. `NUM-CE1-1` et `ORD-CE1-1` ne portent que des nombres nus |
| `MAT-CE1-17` symetrie sur quadrillage | aucune de mon lot | signale pour memoire : recouvrement avec `MAT-CE1-14` et non avec moi | rien de mon cote |

---

## 9. Ce que je route, et a qui

| Fait | A qui | Pourquoi ce n'est pas a moi |
|---|---|---|
| **`DEC-0099`, section 4 bis, affirme au present que la mecanique de designation est *"entierement absente du produit a ce jour"*.** C'est faux au 2026-09-25 : deux entrees de catalogue la servent, `ZoneADesigner` et `DesignationSession` sont en production, et `QLM-CP-11` a ferme dessus le 2026-09-21 sur mon propre Done. La citation de mon arbitrage de s32 est exacte, mais elle etait vraie a s32 et elle a ete payee depuis (section 0.2) | `camille-po`, via `sofia-sm` | `DEC-0099` est son document. Je corrige un fait qui est le mien, je ne reecris pas sa decision - et l'effet sur le perimetre de s43/s44 lui appartient |
| Les trente-deux conditions `NUM-CE1-*`, `ORD-CE1-*`, `ADP-CE1-*`, `SOP-CE1-*`, `MUL-CE1-*` sont automatisables sur le patron de `ADD-CE1-2`/`SOU-CE1-2`, **sauf `ORD-CE1-6` et `MUL-CE1-5`** qui portent sur ce qu'un dessin montre | `theo-archi`, via `sofia-sm` | Je dis la propriete ; la forme du test lui appartient. Leur population est vide aujourd'hui, et `ADR-0051` a une doctrine sur ce cas qui est la sienne |
| Deux **materiaux visuels** sont a instruire, et ils sont de la meme nature que les cinq dessins de solides de s39 - mecanique presente, materiau absent - et **non** de celle de `S39-DEN` : les lignes graduees de `ORD-CE1-6` (en designation) et les reseaux rectangulaires de `MUL-CE1-5` (en item illustre) | `theo-archi` | Le cout, le nombre de dessins et leur faisabilite dans un sprint ne sont pas de mon ressort. Je nomme la difference de nature pour qu'il ne traite pas les deux manques de la meme facon |
| Trois lignes de mon lot portent une **mention de couverture partielle exigee mot pour mot** (`NUM-CE1-2`, `ORD-CE1-5`, `MUL-CE1-7`) et deux une **mention de technique non verifiee** (`ADP-CE1-1`, `SOP-CE1-1`). Ce sont des conditions de fermeture, pas des remarques | `camille-po` | La matrice est a elle. Je produis les motifs, elle ecrit les lignes et le backlog |
| Les recouvrements de la section 8 avec les neuf lignes hors de mon lot | `camille-po`, via `sofia-sm`, et l'instance qui traite ces lignes | Je n'ai pas autorite a borner une ligne que je n'arbitre pas |
| Les trente-deux identifiants ci-dessus sont alloues dans `squad/decisions/REGISTRE-identifiants.md`, section 4, **dans le meme commit que ce document** | moi-meme, fait | `PAR-5` (`ADR-0085`, clause 2) |

**Les cinq lignes sont arbitrables, et donc engageables** : `theo-archi` peut rendre son avis de
faisabilite sur chacune. **Aucune n'aura de Done pedagogique avant que je n'aie joue le paquet
livre**, exactement comme pour les sept jeux CE1 qui precedent.
