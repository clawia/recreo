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

---

### Verdict du 2026-08-31, second passage (s24) - apres le correctif de `sofia-sm`

## Verdict : **VALIDE** - le refus du 2026-08-31 est **leve**

**Le verdict REFUSE ci-dessus n'est pas efface et ne le sera jamais.** Il est date, il porte un
motif, et c'est lui qui a fait exister le correctif. Un refus efface n'a aucune valeur de preuve.

### 1. Ce que j'ai fait, et la difference avec le premier passage

**J'ai rejoue, et cette fois sans aucune sonde.** L'entree de catalogue existe :
`saisie-complements-10-cp` apparait dans la liste du niveau CP, quatorzieme jeu de la liste. **Mon
audit n'est plus degrade** : le premier passage l'etait sur ce point precis, il ne l'est plus.

*Arbre joue : ma branche `s24-nadia-2`, qui reunit les trois correctifs - `5a8a428` de `sofia-sm`,
`16d4a24` de `lea-dev`, `fdfff7c` de `marc-dev`. Jar construit dessus, sur le JDK 21 de la toolchain
declaree. **Aucune branche poussee ne contenait les trois quand j'ai ete appelee** : section 6.*

### 2. `CPL-3` - les onze items, joues un par un

**Ma condition depuis s17 est la totalite et non un echantillon.** Le premier passage n'avait atteint
que huit items sur onze par tirage aleatoire ; celui-ci les a tous.

*Methode : je redemarre une partie jusqu'a rencontrer chaque item, puis je tape la reponse precedee
d'un zero. 31 tirages pour couvrir les onze.*

| Enonce | Reponse | J'ai tape | Resultat |
|---|---|---|---|
| `10 + ? = 10` | 0 | `00` | **acceptee** |
| `9 + ? = 10` | 1 | `01` | **acceptee** |
| `8 + ? = 10` | 2 | `02` | **acceptee** |
| `7 + ? = 10` | 3 | `03` | **acceptee** |
| `6 + ? = 10` | 4 | `04` | **acceptee** |
| `5 + ? = 10` | 5 | `05` | **acceptee** |
| `4 + ? = 10` | 6 | `06` | **acceptee** |
| `3 + ? = 10` | 7 | `07` | **acceptee** |
| `2 + ? = 10` | 8 | `08` | **acceptee** |
| `1 + ? = 10` | 9 | `09` | **acceptee** |
| `0 + ? = 10` | 10 | `010` | **acceptee** |

**Onze sur onze.** `03` etait le cas que j'avais refuse ; il passe.

**Et j'ai rejoue la matrice entiere de `CPL-3`**, pour verifier que le correctif n'a rien ouvert
d'autre :

- **acceptees, et elles le sont** : chiffres, lettres, lettres en majuscules, lettres avec capitale,
  lettres sans accent (`zero` pour la forme accentuee), zero initial, espaces de bord, **zero initial
  et espaces ensemble** ;
- **refusees, et elles le sont** : l'operation ecrite, la phrase, un autre nombre, les lettres d'un
  autre nombre.

**`CPL-3` est tenue dans ses deux moities** : ce qu'elle accepte est accepte, ce qu'elle exclut est
exclu. **`SEUIL-2` n'est plus franchi.**

### 3. Le piege octal - verifie plutot que cru

`sofia-sm` ecrit que `010` non quote est lu **en octal** et sert `8`. **Je l'ai mesure moi-meme**, et
la mesure dit plus que sa phrase :

| Ecriture nue dans le YAML | Ce que le lecteur rend |
|---|---|
| `010` | l'entier **8** |
| `07` | l'entier 7 |
| `00` | l'entier 0 |
| `01` | l'entier 1 |
| `09` | la chaine `09` - octal invalide, repli en chaine |

**Et j'ai verifie pourquoi cela se chargerait sans bruit** : `SaisieContentSourceFactory` lit les
formes acceptees par `String::valueOf`. Un entier `8` y devient la chaine `"8"` **sans erreur**. Le
paquet resterait valide, se chargerait, et servirait un bareme faux - exactement ce qu'elle decrit.
Et `07`, `00`, `01` ne rendraient pas une valeur fausse mais **perdraient silencieusement leur
zero**, ce qui ramenerait le defaut que je venais de refuser.

**Les onze ecritures sont entre guillemets dans le fichier livre, et le fichier dit pourquoi.** J'ai
verifie a l'ecran que le piege ne mord pas : sur `0 + ? = 10`, dont la reponse est `10`, taper `8`
est **refuse**.

### 4. Le garde-fou de `CPL-3` - eprouve, pas cru

`CPL-3` etait **une condition ecrite, datee, precise, qu'aucune regle n'executait**. C'est ce trou
qui a laisse passer le defaut sous 923 tests verts. Il est desormais ferme par
`PaquetCpComplements10SaisieTest`.

**Ce qui fait sa valeur, et je le nomme parce que c'est reproductible ailleurs** : les formes
attendues sont **calculees depuis l'enonce** - le complement a 10 - et **jamais lues dans le
paquet**. Une clause qui lirait le fichier des deux cotes verrait un paquet coherent avec lui-meme,
ce qu'il etait deja quand je l'ai refuse. **C'est la difference entre compter une population et la
juger**, et c'est ce qui rend cette regle capable de voir une declaration **manquante**.

**Morsure verifiee par moi**, et non reprise d'un rapport : j'ai remis le fichier du paquet dans
l'etat exact que j'avais joue et refuse, puis relance la classe.

*Perimetre de la mesure : module `adapter-content` et ses amonts, **pas le depot**. Le `-am` est
porte.*

> `Tests run: 3, Failures: 1` - message rendu :
> *"CPL-3 enumere l'ecriture 010 et la mecanique doit l'accepter. SEUIL-2 : une reponse juste
> refusee sur une question de forme coute un essai a l'enfant, et trois de ces refus brulent la
> manche en lui apprenant qu'il avait tort."*

**La regle rougit sur le materiau reel du defaut, et son message porte mon motif.** Le fichier a ete
restaure ensuite ; l'empreinte du paquet servi est inchangee.

### 5. `CPL-9` - l'entree de catalogue

*Mesures faites par moi sur le catalogue servi. Unite de compte : le mot est une suite de caracteres
sans espace, ponctuation isolee non comptee.*

| Clause | Verdict | Mesure |
|---|---|---|
| **`CPL-9a`** - distinguable de l'entree existante **sans l'ouvrir** | **tenue** | Les deux entrees figurent bien ensemble dans la liste du CP, que j'ai ouverte. Elles se distinguent par le **verbe d'action** en tete du titre et de la consigne, et par aucun mot d'interface. Un enfant de CP lit ce verbe-la |
| **`CPL-9b`** - la consigne dit que l'enfant ecrit | **tenue** | La difference d'action est dans la consigne, pas decouverte a l'ecran |
| **`CPL-9c`** - titre au plus 5 mots, consigne au plus 8 | **tenue, aux deux bornes** | Titre **5 mots**, consigne **8 mots**. *Et je confirme la mesure qui avait motive la retenue : l'ancienne proposition compte **9 mots**. Je l'ai recomptee moi-meme, je ne l'ai pas relayee* |
| **`CPL-9d`** - vocabulaire de CP | **tenue** | La consigne n'emploie que des mots courts et courants. Le titre emploie un terme **deja porte par l'entree voisine** : l'entree neuve n'introduit aucun vocabulaire que le catalogue ne servait pas |
| **`CPL-9e`** - la consigne ne nomme pas la reponse | **tenue** | Elle enonce la tache, elle ne donne ni la reponse ni une methode |

**Inventaire verifie** : **31 entrees au catalogue, 31 paquets servis, aucun paquet orphelin.** Au
premier passage il y en avait un, et c'etait celui-ci.

### 6. Ce que je remonte, et qui n'est pas un defaut du correctif

**L'arbre de reference qu'on m'a donne pour reverifier ne contient qu'un des trois correctifs.**
Verifie par moi : celui de `lea-dev` vit sur `s24-lea`, celui de `marc-dev` sur `s24-marc-2`, et
**aucune branche poussee ne portait les trois**.

**J'ai donc construit l'arbre que j'ai joue**, en reunissant les trois sur ma branche. **Mes trois
verdicts portent sur cet arbre-la**, et un tiers peut le refaire. Empreintes des fichiers audites :

| Fichier | Empreinte |
|---|---|
| `cp-complements-10-saisie.yaml` | `8c92fe5d1d6b048f209c3aa6e7cf47bd3fa127c1` |
| `cp-texte-narratif.yaml` | `3f3911392df562af8c3e6284ed15d474f273a7fe` |
| `cm1-fractions.yaml` | `9bd75dafd99703a78aa867df3b5e858ae5ac50b8` |
| `catalogue.yaml` | `bf70e7eea690569d9607eea04ea1e847a7ff10e2` |

**Si l'integration de cloture ne rend pas ces quatre empreintes, elle ne porte pas ce que j'ai
valide.**

*Porte technique sur cet arbre, mesuree par moi : `mvn -B clean verify` a la racine du reacteur
complet - **926 tests, 0 echec, 0 erreur, 0 ignore**, BUILD SUCCESS, JDK **21.0.10** rapporte par la
machine virtuelle des tests. **Ce chiffre ne prononce rien** ; il dit que l'arbre que j'ai joue tient
debout. Il coincide avec celui qu'on m'a annonce pour un arbre a un seul correctif, et l'explication
est simple : les deux autres correctifs ne touchent que du YAML de contenu et n'ajoutent aucun test.
**La coincidence est expliquee, pas suspecte** - mais elle montre qu'un compte de tests ne distingue
pas deux arbres.*

### 7. Ce qui reste ouvert, et je ne l'adoucis pas

**Le garde-fou de `CPL-3` garde ce paquet-ci. Il ne gardera pas le prochain bareme.** `sofia-sm` le
concede et elle a raison. Voici **pourquoi**, parce que le motif compte plus que le constat.

- **Sur ce paquet, les formes acceptees sont calculables** : la reponse est une fonction de l'enonce,
  et les ecritures tolerees d'un nombre forment un ensemble ferme et mecanique - chiffres, zero
  initial, lettres. Un automate peut les engendrer sans lire le paquet.
- **Sur un paquet alphabetique, elles ne le sont pas** : savoir quelles variantes d'un mot comptent
  pour la meme reponse est **un jugement de francais**, pas une fonction de l'enonce. Aucun automate
  ne les engendre, et `SEUIL-5` le dit depuis le 2026-08-24 : *"Si les formes acceptees d'un paquet
  ne sont relues par personne de competent en francais, la saisie ne doit pas exister."*

**Ce qui reste donc dans ma seule main** : **la completude des formes acceptees d'un paquet face a ce
qu'un enfant de ce niveau ecrirait legitimement.** Une regle peut prouver la **coherence** d'un
paquet avec un calcul ; elle ne peut pas prouver sa **completude** face a un enfant. C'est `SAI-6` et
`SAI-7`, et le premier passage a montre ce que cela coute quand personne ne joue.

**Ce que je route comme une question, et non comme une demande** - a `theo-archi` et `yanis-qa` : la
**forme** de ce garde-fou est transferable meme quand son calcul ne l'est pas. Sur un paquet
alphabetique, la source independante du paquet ne serait plus l'enonce mais **ma liste ecrite dans la
fiche**. Cela deplace le risque vers une erreur de transcription, ce qui n'est pas rien. **Je dis que
c'est concevable ; je ne dis pas qu'il faut l'ecrire**, et je ne l'exigerai pas avant le premier
paquet de saisie alphabetique.

### 8. Ce que ce second verdict fait, et ce qu'il ne fait pas

- Il **leve le refus** du 2026-08-31 sur `S24-SAI-2` et **prononce le Done pedagogique**.
- Il **n'efface pas le refus**, qui reste lisible avec sa date et son motif.
- Il **ne fait bouger aucune ligne de matrice.** `CPL-1` le disait d'avance : la ligne *"Complements
  a 10"* est deja `couvert-valide` par un autre jeu et **ne bouge pas**. Deux entrees sur une meme
  ligne ne sont pas une double couverture.
- Il **confirme l'audit de la mecanique de saisie** rendu au premier passage, cette fois joue depuis
  le catalogue livre : les cinq seuils tiennent, la revelation debloque, la partie se termine.
- **Un fait que je verse, et qui n'est pas rien** : le catalogue contient desormais **un jeu ou un
  enfant qui ne sait pas peut echouer**. Sur les trente autres il ne le peut pas - c'est la reserve 5
  elargie, tranchee par `DEC-0061`. **La saisie est la premiere breche dans ce mur, et elle est d'un
  jeu sur trente et un.**
- **`CPL-7` sur tablette reste hors de ma portee**, et la reserve du premier passage tient telle
  qu'elle est ecrite. Personne dans cette squad n'a l'appareil.


---

# s30 - audit de `nadia-prof`, **instance A**, 2026-09-06

- **Verdicts complets du sprint** :
  `squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/verdicts-nadia-instance-A.md`.
- **Passe transverse** : `squad/conformite/PASSE-TRANSVERSE-s30-les-trois-familles-de-portee-depot.md`.
- **Arbre audite** : `round-two`, HEAD `dbbfe129`, joue depuis le worktree `s30-nadia-A`, port 8082.
- **Audit degrade sur un seul point, et je le nomme** : j'ai joue **par requetes HTTP**, donc j'ai lu
  le HTML servi et non la page rendue. Aucun jugement de ce bloc ne repose sur le rendu visuel.
- **Aucun verdict anterieur n'est efface par ce bloc.**

## `REV-3` levee sur ce jeu, et `SAI-R1` ouverte

**J'ai joue une partie complete** jusqu'a l'ecran de fin, **avec une saisie fausse a chaque manche**,
poussee jusqu'a la revelation.

### `REV-3` - **levee**, verifiee sur cette mecanique

L'ecran de fin porte desormais **l'enonce de la manche que son verdict ferme**, avant la reponse :

> *Tu n'as pas trouve.* / *4 + ? = 10* / *La bonne reponse :* / *6*

C'etait le second critere de la reserve, et il est tenu **sur l'autre mecanique aussi** - je ne
l'avais verifie qu'au bandit manchot a s29. La reponse occupe **son propre element**, seule et
entiere, ce qui rend le cadre indifferent a sa forme grammaticale (ici une valeur numerique).

**C'est le cas qui montre le mieux ce que `REV-3` reparait.** *"La bonne reponse : 6"* seule ne dit
rien : un enfant qui vient d'enchainer dix manches ne sait plus de quel complement il s'agit.
Avec l'enonce qui la precede, la phrase redevient lisible et l'ecran cesse de commenter une
question invisible.

### `SAI-R1` - **reserve nouvelle, datee du 2026-09-06, non bloquante**

**Vue a l'ecran sur ce jeu**, en ecrivant volontairement une reponse fausse terminee par un point :

> *"Tu as ecrit : zzz.."*

Le gabarit de saisie ajoute un point a la chaine saisie **quelle qu'elle soit**. Quand l'enfant a
lui-meme tape un point, l'ecran lui montre **deux points a la suite**, dans la phrase precise ou le
produit lui renvoie son propre geste.

**Pourquoi ce n'est pas bloquant** : l'incidence est faible - un enfant de CP qui ecrit un mot dicte
ou un nombre ne tape pas de point final -, rien de faux n'est enseigne, l'enfant n'est pas bloque.

**Pourquoi je l'ouvre quand meme** : c'est, apres `REV-3`, **le dernier endroit du depot ou un texte
que le produit ne controle pas entre dans une phrase que le produit compose** - la mesure complete
est en section 1.2 de ma passe transverse. C'est le mecanisme exact de `REV-1`, qui a mis trois
sprints et trois codes a se resorber.

> **`SAI-R1`. Propriete a tenir** : la ponctuation finale d'une phrase qui cite ce que l'enfant a
> ecrit n'est pas ajoutee par le gabarit a une chaine dont le gabarit ignore la forme. **Je donne la
> propriete, jamais la forme** : la cause est technique et son traitement appartient a `theo-archi`.
> **Regime : mot pour mot une fois le correctif livre, 0,25.**

**Aucune ligne ne bouge.**
