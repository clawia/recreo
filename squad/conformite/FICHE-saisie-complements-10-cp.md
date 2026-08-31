# Fiche de conformite - la saisie des complements a 10 (CP) - `S24-SAI-1` et `S24-SAI-2`

- **Mecanique** : **saisie**, mecanique neuve de s24 (`ADR-0058`, `ADR-0069`), livree du domaine
  jusqu'a l'ecran. C'est la seconde mecanique de ce produit.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-complements-10-saisie.yaml`,
  `type: saisie`, **11 items**, `exigenceDeForme: libre`, story `S24-SAI-2`. Ecriture de `sofia-sm`.
- **Entree de catalogue** : **AUCUNE.** Verifie par moi sur l'arbre de cloture : 30 entrees au
  catalogue, **31 paquets** dans le contenu servi, et l'entree manquante est celle-ci. **Le jeu
  n'existe pas pour un enfant.**
- **Conditions applicables** : `SAI-1` a `SAI-12` et `SEUIL-1` a `SEUIL-5` (mecanique, 2026-08-24 et
  amendements), `CPL-1` a `CPL-8` (paquet, 2026-08-30), `CPL-9` (entree de catalogue, 2026-08-31),
  `D5` (2026-08-31).
- **Statut global** : **Done pedagogique REFUSE sur `S24-SAI-2` le 2026-08-31.** Aucun Done ne peut
  etre prononce sur un jeu de saisie a s24 : **il n'y a pas de jeu.**

## Competences visees

Mathematiques CP - *"Complements a 10"*. **Cette ligne est deja `couvert-valide`** par
`bandit-manchot-complements-10-cp` et **ne bouge pas** (`CPL-1`). Deux entrees sur une meme ligne ne
sont pas une double couverture, et la fiche le porte ecrit.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-08-31 (s24)

## Verdict : **REFUSE** sur `S24-SAI-2`

**Motif, en une phrase actionnable** : le paquet ne declare pas la forme acceptee que `CPL-3` exige -
**l'ecriture en chiffres avec zero initial, `07` valant `7`** - de sorte qu'un enfant qui ecrit `03`
pour la reponse `3` **s'entend dire qu'il s'est trompe et perd un essai**. Trois ecritures de ce
genre brulent la manche entiere, et la revelation lui affiche *"Tu as écrit : 03. On écrit : 3."*
**C'est `SEUIL-2` franchi : une reponse juste refusee sur une question de forme lui coute un essai.**

**Ce refus renvoie l'increment a `camille-po`, qui en fait du backlog. Il ne bloque pas le sprint
suivant, et il ne retire aucun Done ailleurs** - en particulier pas celui de
`bandit-manchot-complements-10-cp`, qui est un autre increment, une autre fiche, un autre verdict.

---

### 1. Ma methode, et l'endroit ou elle est degradee - je le dis avant les resultats

**J'ai joue la mecanique.** Mais **le jeu n'existe pas au catalogue** : un appui sur son identifiant
rend *"Oups ! Le jeu a un souci."*, ce que j'ai verifie. Pour jouer, **j'ai fabrique moi-meme une
entree de catalogue de sonde, hors du depot**, posee devant le jar sur le chemin de classes, sans
modifier un seul fichier du depot et sans la commiter. Elle a ete retiree ensuite.

**Ce que cela etablit, et ce que cela n'etablit pas.** J'ai joue **la vraie mecanique, le vrai
paquet, le vrai ecran, servis par le vrai jar**. Ce que j'etablis est donc : *la mecanique se comporte
ainsi quand elle est atteignable*. Ce que je n'etablis pas : que quoi que ce soit soit atteignable
dans le produit livre. **C'est un audit degrade sur ce point precis, et sur celui-la seulement.**

Je n'ai pas de tablette : tout ce que je dirais du clavier virtuel resterait un raisonnement, et je
n'en dis rien.

---

### 2. La mecanique de saisie - ce que j'ai joue, ecran par ecran

*Audit de mecanique, hors compte de paquets, comme je l'ai annonce le 2026-08-30.*

| Ce que j'ai fait | Ce que l'ecran a rendu | Verdict |
|---|---|---|
| Ouvrir une manche | L'enonce `6 + ? = 10`, un champ vide, un bouton *"Je réponds"* | **`CPL-6` tenue** - l'enfant voit ou va ce qu'il ecrit |
| Repondre juste en chiffres | *"Bravo !"*, manche suivante, score en hausse | tenue |
| Repondre juste **en lettres** (`quatre`) | *"Bravo ! On écrit : 4"* | **`SAI-8` tenue** - la forme de reference est montree quand une tolerance a joue, **et `CPL-8` tenue** : rien ne reproche a l'enfant d'avoir ecrit en lettres |
| Repondre faux une fois | *"Ce n'est pas ça. Essaie encore !"*, l'indice apparait, **le champ garde ma chaine**, la chaine barree s'affiche au-dessus | **`SAI-9a`, `D5` et sa condition tenues** |
| Repondre faux deux fois | Deux chaines barrees, le champ porte la derniere | **`SAI-12` tenue** - toutes les tentatives de la manche, cumulativement, telles que je les ai tapees |
| Repondre faux trois fois | *"Ce n'est pas ça. Tu as écrit : 9. On écrit : 3."*, le champ disparait, un bouton *"Continuer"* | **`SAI-9b` tenue**, litteralement dans la forme que j'avais ecrite le 2026-08-24. **Un enfant bloque avance** |
| Renvoyer la meme chaine a l'identique | Elle compte un essai, **et elle est deja barree au-dessus du champ** | **La condition de `D5` est servie** : au moment ou l'enfant peut appuyer, la chaine du champ lui est montree comme deja refusee |
| Aller au bout d'une partie | *"Score : 7 - Bravo, c'est juste ! - C'est fini !"* et un retour vers la liste des jeux | tenue |
| Corriger avant de valider | Le champ est un champ de texte natif, il **recoit le focus apres une erreur** - je l'ai verifie sur la cible de focus rendue - et il est atteignable et validable au clavier seul | **`SEUIL-3` et `SEUIL-4` tenues** |

**`SEUIL-1` tenue** : l'enfant voit ce qu'il a ecrit au moment du verdict, dans le champ et dans la
liste.

**Ce que la mecanique apporte, et c'est le motif qui l'a fait ouvrir** : sur les complements a 10, un
enfant qui ne sait rien obtenait 7 manches sur 7 en boutons. **En saisie, il echoue.** Je l'ai
verifie en jouant : trois essais, onze reponses possibles. **C'est la premiere mecanique de ce
produit ou un enfant qui ne sait pas peut echouer**, et cela reste vrai.

**La mecanique n'est pas en cause dans le refus.** Voir la section 4.

---

### 3. Le paquet - mes conditions, une par une

*Lecture du fichier YAML servi, 11 items, et jeu reel sur chacune des formes. Aucun build.*

| Condition | Verdict | Ce que j'ai lu ou joue |
|---|---|---|
| **`CPL-1`** - complements a 10 et rien d'autre ; la ligne ne bouge pas | **tenue** | 11 items, tous de la forme `n + ? = 10` |
| **`CPL-2`** - toute bonne reponse est un entier de 0 a 10 | **tenue** | Les 11 reponses vont de 0 a 10 |
| **`CPL-3`** - formes acceptees, enumerees et relues par moi | **NON TENUE** | Voir la section 4. **C'est le motif du refus** |
| **`CPL-4`** - aucun mode strict | **tenue** | `exigenceDeForme: libre`, declare au niveau du paquet |
| **`CPL-5`** - les quatre indices qui nommaient la reponse sont reecrits | **tenue** | Les onze indices sont neufs et conduisent sans nommer. *"Lève 6 doigts, puis compte ceux qui manquent pour arriver à 10."* conduit ; *"il en reste 5"* nommait. Les quatre que j'avais designes le 2026-08-30 ont disparu, y compris les deux cas durs : `9 + ? = 10` ne dit plus *"compte celui qui manque"* au singulier, et `10 + ? = 10` demande *"combien t'en reste-t-il à lever ?"* au lieu de dire *"aucun"* |
| **`CPL-6`** - l'enfant voit ou va ce qu'il ecrit | **tenue** | Joue |
| **`CPL-7`** - entrer un chiffre ne doit pas obliger a chercher parmi des lettres | **reserve, non jugee** | Le champ est un champ de texte sans demande de clavier numerique. Sur un clavier physique, aucun enfant ne cherche parmi des lettres et la condition est tenue. **Sur une tablette, je ne peux pas juger** : c'est mon angle mort declare depuis le 2026-08-25, je n'ai pas l'appareil, et je refuse d'en faire un motif de refus comme j'ai refuse d'en faire un motif d'approbation |
| **`CPL-8`** - le retour de reussite ne felicite pas l'appareil | **tenue** | *"Bravo ! On écrit : 4"*. Aucun reproche |
| **`CPL-9`** - titre et consigne de l'entree de catalogue | **sans objet** | L'entree n'existe pas. Voir la section 5 |

---

### 4. Le motif du refus, etabli en jouant puis isole par une sonde

#### 4.1 Le fait

`CPL-3`, du 2026-08-30, enumere ce qui est accepte sur ce paquet : *"l'ecriture en chiffres du
nombre, **zero initial tolere (`07` vaut `7`)**, espaces de tete et de fin ignorees ; et l'ecriture
en lettres du meme nombre"*.

**Ce que le jeu fait**, mesure en jouant huit manches, chacune sur un item different, en tapant la
bonne reponse precedee d'un zero :

| Enonce | Bonne reponse | Ce que j'ai tape | Reponse du jeu |
|---|---|---|---|
| `7 + ? = 10` | 3 | `03` | **refusee** |
| `2 + ? = 10` | 8 | `08` | **refusee** |
| `6 + ? = 10` | 4 | `04` | **refusee** |
| `4 + ? = 10` | 6 | `06` | **refusee** |
| `1 + ? = 10` | 9 | `09` | **refusee** |
| `10 + ? = 10` | 0 | `00` | **refusee** |
| `0 + ? = 10` | 10 | `010` | **refusee** |
| `9 + ? = 10` | 1 | `01` | **refusee** |

**Huit sur huit.** Les autres formes de `CPL-3` sont, elles, acceptees - je les ai toutes jouees :
chiffres, lettres, lettres en majuscules, lettres avec capitale, lettres sans accent, espaces de
tete et de fin. **Et ce qui doit etre refuse l'est** : l'operation ecrite (`10-7`) et la phrase
(*"il manque 4"*), comme `CPL-3` le demande.

#### 4.2 Pourquoi cela franchit un seuil, et non une simple condition

**Le refus coute un essai, verifie en jouant** : j'ai tape `03` trois fois de suite sur un item dont
la reponse etait `3`. La liste des tentatives a affiche `03`, `03`, `03`, la manche s'est terminee
sur la revelation, et l'ecran a dit a l'enfant : *"Ce n'est pas ça. Tu as écrit : 03. On écrit :
3."*

**Un enfant a eu raison trois fois et le jeu lui apprend qu'il avait tort.** C'est mot pour mot le
scenario contre lequel `SEUIL-2` a ete ecrit le 2026-08-24 : *"Si une reponse juste refusee pour une
question de forme coute un essai, la saisie ne doit pas exister. Un enfant qui ecrit le bon mot de
trois facons legerement differentes brulerait sa manche entiere en ayant eu raison trois fois."*

**Et le zero initial n'est pas une fantaisie de ma part** : c'est l'ecriture qu'un enfant de CP
produit spontanement quand il aligne ses reponses ou qu'il ecrit un chiffre dans une case de deux
caracteres. C'est pour cela que je l'avais enumeree.

#### 4.3 Ce que j'ai fait pour ne pas accuser la mecanique a tort

**J'ai isole la cause avant d'ecrire le motif**, parce que ma frontiere est de decrire le symptome et
non de designer un coupable technique.

*Methode : j'ai pose, hors du depot et sans le modifier, une copie du paquet ou chaque item declare
en plus la forme a zero initial de sa propre reponse ; puis j'ai rejoue les memes manches.*

**Resultat : `03`, `08`, `04`, `02`, `05`, `09` sont alors toutes acceptees, six sur six.**

**Conclusion, et elle est la moitie utile de ce refus** : le moyen existe et il fonctionne. La
mecanique tient `SAI-1`, `SAI-3` et `SAI-4` - les accents, la casse, les blancs - et elle accepte
sans reserve toute forme que le paquet declare. **Ce qui manque est une declaration dans le paquet,
pas un comportement dans la mecanique.**

**Donc : `SEUIL-2` est franchi *sur ce paquet*. Il n'est pas franchi par la mecanique**, et personne
ne doit lire mon seuil plus large qu'il n'est. **Je ne refuse pas la mecanique de saisie.**

#### 4.4 Ce qui leve le refus

**Une forme acceptee de plus par item, onze chaines en tout** - l'ecriture a zero initial de la
reponse de chaque item. Rien d'autre n'est en cause dans ce paquet : les onze enonces, les onze
reponses et les onze indices sont justes et au niveau.

*Autre voie, si `theo-archi` la prefere : que la mecanique replie les ecritures numeriques. **Ce
choix n'est pas de moi** - je fixe le resultat, jamais le chemin - et je note seulement qu'il
porterait sur tous les paquets de saisie a venir plutot que sur celui-ci.*

---

### 5. L'entree de catalogue absente - la question que `sofia-sm` me route, et ma reponse

#### 5.1 Le fait, verifie par moi

30 entrees au catalogue, 31 paquets servis. **Aucune entree ne sert
`cp-complements-10-saisie`.** La liste du niveau CP ne le propose pas. Un appui sur son identifiant
rend l'ecran d'erreur. **La saisie n'est pas jouable par un enfant a la cloture de s24.**

Le motif de la retenue est verifie et il est exact : la consigne proposee, *"Écris le nombre qui
manque pour arriver à 10."*, compte **neuf mots** sous l'unite de compte que j'ai fixee, **contre la
borne de huit** de `CPL-9c`.

#### 5.2 Ce que cela coute, et je ne l'adoucis pas

**`DEC-0059` visait la mecanique *jouable*, et elle ne l'est pas.** Une mecanique qu'aucun enfant ne
peut ouvrir n'apprend rien a personne. Le gain pedagogique de s24 sur la saisie est, pour un enfant,
**nul** : ce qui existe est une capacite du produit, pas un jeu. Je le dis sans nuance parce que
c'est le fait, et parce qu'une porte qui adoucit ce genre de constat ne sert a rien.

#### 5.3 Et pourtant la retenue etait le bon geste - deux motifs, dont un que personne ne pouvait connaitre

**Premier motif, celui de `sofia-sm`, et il est juste.** Le choix etait entre retenir l'entree et
faire entrer au catalogue une consigne qui contredit une condition rendue le jour meme. **Elle a
applique ma condition plutot que de l'enfreindre.** C'est exactement ce que je demande, et je ne vais
pas le lui reprocher parce que le resultat me deplait.

**Second motif, et il n'apparait qu'apres avoir joue** : si l'entree etait entree, **le jeu aurait
ete servi a des enfants avec le defaut de la section 4**. Un enfant de CP qui ecrit `03` se serait
entendu dire qu'il avait tort. **Ce que la retenue a coute n'est donc pas un bon jeu retenu, c'est un
jeu defectueux non servi.** Ce n'est pas un merite de la decision - elle a ete prise pour une tout
autre raison - mais c'est ce qu'elle a produit, et cela change ce que le report coute reellement.

#### 5.4 Ce que je demande pour la reprise

L'entree, quand elle sera ecrite, passe `CPL-9a` a `CPL-9e`. **`CPL-9a` est celle qui mord** : le
catalogue portera alors **deux entrees sur la meme competence et le meme niveau**, cote a cote dans
la liste du CP, l'une avec des boutons et l'autre avec un champ. **Un enfant de CP doit pouvoir dire
en quoi elles different sans les ouvrir**, et pas au moyen d'un mot d'interface.

*J'ai verifie que le risque est reel et non theorique : la liste du niveau CP est bien filtree par
niveau - je l'ai ouverte - donc les deux entrees s'y afficheraient ensemble.*

**Et l'ordre reste celui que `sofia-sm` a fixe** : le correctif d'`ADR-0072` d'abord, l'entree
ensuite. Je n'ai rien a y redire ; c'est du sequencement.

---

### 6. Sur quelles proprietes je reste la seule garde

- **`SAI-6` et `SAI-7`, les formes acceptees.** Je l'avais ecrit le 2026-08-30 : *"le point le plus
  dangereux de la mecanique de saisie ; un bareme faux passe une CI verte sans laisser de trace."*
  **C'est exactement ce qui vient d'arriver** : 923 tests verts, et un enfant qui ecrit `03` a tort.
  Le garde-fou de cette propriete, c'est moi, et il n'a fonctionne que parce que j'ai joue.
- **`SAI-8`, `SAI-9a`, `SAI-9b`, `SAI-2c`, `SAI-12`** - la formulation de tout ce que l'enfant lit
  apres avoir repondu. Un test verifie que deux chaines sont affichees ; il ne dit pas si un enfant
  de six ans comprend ce qu'il lit.
- **`SEUIL-2`** - qu'une reponse juste ne coute jamais un essai. **Aucune regle n'existe qui
  l'empeche**, et c'est la deuxieme fois que je l'ecris.
- **`CPL-9a`** - qu'un enfant de CP distingue deux entrees sur la meme competence sans les ouvrir.
- **Ce que je ne peux pas garder du tout** : le comportement d'un clavier virtuel de tablette, donc
  `CPL-7` sur tablette. Personne dans cette squad n'a l'appareil.

---

### 7. Ce que ce verdict fait, et ce qu'il ne fait pas

- Il **REFUSE `S24-SAI-2`**, avec un motif que `camille-po` peut transformer en story sans me
  redemander ce que je voulais dire : onze formes acceptees a ajouter.
- Il **ne refuse pas la mecanique de saisie**, dont j'ai joue les cinq seuils et qui les tient. La
  section 4.3 etablit que le moyen fonctionne.
- Il **ne prononce aucun Done sur un jeu de saisie**, parce qu'il n'y a pas de jeu.
- Il **ne fait bouger aucune ligne de matrice**, ni dans un sens ni dans l'autre. `CPL-1` le disait
  d'avance et cela reste vrai : la ligne *"Complements a 10"* est servie par un autre jeu et **ne
  bouge pas**.
- Il **ne retire aucun Done** ailleurs, et surtout pas celui de
  `bandit-manchot-complements-10-cp` : deux increments, deux fiches, deux verdicts. Je l'avais ecrit
  le 2026-08-30 pour que personne n'hesite a me livrer un jeu de saisie de peur de faire tomber une
  ligne verte, et cela vaut aujourd'hui qu'un refus est prononce.
- Il **n'est pas fonde sur la CI.** La porte technique est verte ; ce refus vient d'avoir joue.
- Il **ne demande le retrait d'aucun jeu du catalogue** : le jeu refuse n'y est pas.
