# Arbitrage prealable s19 - ce qu'est une reponse juste quand l'enfant ecrit

- **Auteur** : nadia-prof. **Date** : 2026-08-24. **Sprint** : s19.
- **Branche** : `s19-nadia`, worktree dedie, lignee verifiee par `outils/verifier-lignee.sh`.
- **Ce que ce document est** : l'entree pedagogique de l'`ADR` de `theo-archi` sur la mecanique de
  saisie, et **il le precede**. Il dit ce qu'est une reponse juste quand l'enfant produit sa reponse
  au lieu d'en choisir une, ce que l'enfant doit voir apres, et sur quelles lignes du referentiel je
  refuse par avance qu'on bascule.
- **Ce que ce document n'est pas** : ni une decision d'architecture, ni un Done. **Je ne dis jamais
  ou vit la reponse** - dans la mecanique, dans le paquet, ou entre les deux. Je dis quel resultat
  l'enfant doit obtenir ; `theo-archi` decide par quel chemin, sous la contrainte de sa propre regle
  `uneMecaniqueIgnoreLaMatiere` qui n'est pas la mienne a lever.
- **Mode** : je n'ai rien joue, et ici c'est legitime - la mecanique de saisie n'existe pas, il n'y a
  rien a jouer. J'ai lu `fragments/manche.html` et `fragments/retour.html` pour verifier ce que
  l'enfant voit **aujourd'hui**, `BanditManchotSession` pour la regle des essais, la section "Saisie"
  de l'avis de `theo-archi` (`squad/sprints/s13-grands-nombres-et-chemin-de-catalogue/
  avis-ordre-de-grandeur-seconde-mecanique.md`), `DEC-0002`, `DEC-0040`, et mes propres conditions M5,
  M9 et M11.
- **Une question reste hors de ma competence et je la route sans la trancher** : section 8.

## 0. Ce que je verifie avant de trancher, et une chose que la saisie detruirait sans qu'on l'ait dit

**Verifie et exact** : le retour juste/faux ne depend pas de la couleur seule. `fragments/manche.html`
et `fragments/retour.html` rendent tous deux un signal non textuel (`.icone-signal`) **et** un texte
de verdict (`.verdict`), sur la meme boite. C'est ma condition M11 clause 1 et elle est tenue.

**Verifie et exact** : trois essais au maximum par manche, puis revelation
(`BanditManchotSession.ESSAIS_AVANT_REVELATION = 3`, ma condition M5).

**Ce que je trouve et que personne n'a nomme, et c'est le point le plus important de cette
section.** Aujourd'hui, quand un enfant se trompe, **la proposition qu'il a pressee reste a l'ecran,
a sa place, marquee** - c'est ma condition M9, "marque = mot barre", et elle est tenue. L'enfant voit
donc en permanence ce qu'il a deja essaye, et au moment de la revelation il voit son erreur **a cote**
de la bonne reponse. **Une mecanique de saisie detruit cette propriete par construction** : un champ
de saisie se vide, ou garde une seule valeur, et l'enfant qui recoit "Non, c'est élève" n'a plus
sous les yeux ce qu'il avait ecrit.

Ce n'est pas un detail d'ergonomie. C'est un acquis pedagogique deja accorde sur ce produit, qu'une
story de saisie deferait sans que personne ne l'ait relie a l'avance - exactement le cas que
`squad/README.md` section 10 demande de signaler en arbitrage prealable plutot que d'auditer apres
coup. Je le signale ici, et j'en fais la condition **SAI-9**.

---

## 1. L'accent - "eleve" pour *eleve* accentue

### Verdict : **JUSTE par defaut, au CP comme au CM1. FAUX seulement quand le contenu declare que l'accent est la competence.**

**Le motif.** Taper un accent n'est pas une competence scolaire, c'est une competence de clavier.
L'enfant qui a ecrit les bonnes lettres dans le bon ordre **a le bon mot** : il connait la reponse, et
la lui compter fausse ne lui apprend rien sur la matiere, cela lui apprend qu'il s'est trompe alors
qu'il avait raison. C'est la pire chose qu'une correction puisse faire, et c'est celle qui fait
abandonner. Sur un exercice de mathematiques ou de vocabulaire, l'accent n'est pas evalue ; il ne doit
donc pas decider du verdict.

**La reponse est la meme au CP et au CM1**, et je le dis explicitement parce que la question m'est
posee. Au CM1 l'enfant sait que l'accent existe, mais il ne sait pas mieux le taper : sur les deux
cibles de `DEC-0040`, l'ordinateur et la tablette, l'acces a une lettre accentuee n'est pas le meme
geste et n'est pas garanti (section 8). Un verdict ne peut pas dependre du materiel de la maison.

**Elle n'est pas la meme quand l'accent est la competence**, et ce cas n'est pas theorique : il est
deja dans le catalogue. La ligne francais CM1 des homophones grammaticaux est `couvert-valide` depuis
s18, et elle porte `a` contre `a` accentue, `ou` contre `ou` accentue. Une tolerance d'accent
appliquee aveuglement rendrait **tous** les items de ce paquet insolubles - les deux reponses
deviendraient la meme chaine. Meme chose pour *la* et *la* accentue, *des* et *des* accentue, *sur* et
*sur* circonflexe. La tolerance doit donc etre **debrayable, et debrayee par le contenu**.

- **SAI-1** - Par defaut, une reponse qui ne differe de la forme attendue que par la presence ou
  l'absence d'accents et de cedilles est **juste**. Cela vaut au CP et au CM1, sur tout exercice qui
  ne declare pas le contraire.
- **SAI-2** - Un paquet dont la competence porte sur l'accent - homophones, orthographe lexicale,
  distinction de graphies - **declare la stricte egalite**, et il la declare **la ou elle est vraie** :
  au niveau du paquet si le paquet entier est concerne, au niveau de l'item si le paquet melange les
  deux cas. Jamais une deduction faite par le code a partir de la matiere ou du niveau : une regle
  devinee est une regle qui se trompera un jour sur un paquet que personne ne relira. **Un paquet qui
  ne declare rien est en mode tolerant** - le defaut protege l'enfant, pas l'exercice.

---

## 2. La majuscule

### Verdict : **JUSTE par defaut, y compris une reponse ecrite entierement en capitales. FAUX seulement quand le contenu declare que la majuscule est la competence.**

**Le motif.** Un enfant de CP tape en majuscules, souvent parce que c'est ce qu'il sait faire et parce
que c'est l'ecriture qu'on lui a apprise a lire en premier. Un enfant de CM1 laisse la touche de
verrouillage enclenchee et ne s'en apercoit pas. Dans les deux cas, la casse ne dit rien de ce qu'il
sait.

L'exception est la meme que pour l'accent, et elle est reelle : la majuscule **est** la competence sur
les reperes de relecture du CM1 (majuscule en debut de phrase, majuscule aux noms propres). Si un
paquet travaille cela un jour, il declare la stricte egalite au titre de SAI-2.

- **SAI-3** - Par defaut, la casse ne decide jamais du verdict. Une reponse tout en capitales, tout en
  minuscules, ou avec une capitale initiale en trop ou en moins, est **juste** si les lettres sont les
  bonnes. Le mode strict s'obtient par la declaration de SAI-2, jamais autrement.

---

## 3. Les espaces

### Verdict : **JUSTE, sans exception que je connaisse aujourd'hui.**

Trois cas, et je les separe parce qu'ils n'ont pas la meme force.

**a. Espaces en debut et en fin, espaces multiples a l'interieur.** Toujours juste. Il n'existe aucune
competence, dans les quatre referentiels, pour laquelle un espace surnumeraire soit la reponse.

**b. Espace avant une ponctuation.** Toujours juste, et je vais plus loin : **on ne doit jamais
compter un enfant faux la-dessus.** La regle francaise - espace avant les deux-points, le
point-virgule, le point d'exclamation et le point d'interrogation, pas avant la virgule ni le point -
est une regle de **typographie**, pas une regle de programme. Elle ne figure dans aucun des quatre
referentiels. Un adulte sur deux l'ignore.

**c. Espace manquant entre deux mots** - *pommedeterre* pour *pomme de terre*, *deuxcentcinq* pour
*deux cent cinq*. Juste, sur tout exercice qui ne declare pas la stricte egalite au titre de SAI-2.
Meme raisonnement que l'accent : les lettres sont la, dans l'ordre, l'enfant a la reponse. Sur un
paquet de grands nombres, un enfant qui ecrit *deuxcentcinq* a lu le nombre - c'est cela qu'on evalue.

**Le cas ou je change d'avis, et c'est le seul** : le CP porte une ligne *"Copier un mot, une phrase
courte sans erreur, **en respectant l'espacement**"*. Elle n'est pas concernee - non pas parce que
l'espacement y serait tolere, mais parce que cette ligne ne doit jamais etre servie par une saisie du
tout (section 6, refus **R1**).

- **SAI-4** - Les espaces de bord, les espaces multiples internes, les espaces autour d'une ponctuation
  et l'absence d'un espace entre deux mots ne decident jamais du verdict. Le mode strict de SAI-2 est
  la seule exception.

---

## 4. Un mot juste mais mal orthographie, sur un exercice qui ne porte pas sur l'orthographe

C'est la question la plus difficile des six et je ne la contourne pas. Elle a deux parties, et ma
reponse n'est pas la meme.

### 4.1 Ce qu'un maitre fait dans sa classe

Sur un probleme de mathematiques, je ne compte pas faux une faute d'orthographe. La competence
evaluee est le raisonnement, pas la graphie. Sur une question de vocabulaire, si l'enfant a trouve le
bon mot mais l'a mal ecrit, il a trouve le bon mot. Le principe est donc clair : **une faute
d'orthographe, sur un exercice qui ne porte pas sur l'orthographe, ne doit pas rendre la reponse
fausse.**

### 4.2 Ce que je refuse quand meme : la tolerance automatique

**Je refuse tout appariement approximatif calcule par le code** - une tolerance d'une lettre, une
distance entre chaines, quel qu'en soit le nom. Deux motifs, et le premier est disqualifiant a lui
seul.

**a. Sur une reponse numerique, une lettre de difference est une autre valeur.** 300 et 200 different
d'un caractere. 47 et 42 aussi. 1 000 000 et 1 000 000 avec un zero de moins aussi. Une tolerance
approximative sur une reponse chiffree ne rend pas l'exercice indulgent, elle le rend faux - et
`recreo` est majoritairement un catalogue de mathematiques. **C'est le point sur lequel je refuserai
un increment sans discuter.**

**b. Sur une reponse en mots, une tolerance d'une lettre confond des mots reels.** *chevaux* et
*cheveux*, *pere* et *perte*, *dent* et *dont*, *sept* et *cept*. Rendre ces paires equivalentes
transforme l'exercice en devinette - c'est la meme faute que le distracteur absurde, et je la refuse
sous le meme motif. Je ne sais pas ecrire une regle automatique qui separe *"l'enfant connait le mot
et l'a mal tape"* de *"l'enfant ne connait pas le mot"*, et je ne crois pas que quiconque ici le
sache.

### 4.3 Ce que j'accepte a la place

Le chemin que `theo-archi` recommande - **le paquet porte l'ensemble des formes acceptees** - est le
bon, et j'y ajoute ce qui manque : **la regle de ce qu'on y met, et qui l'ecrit.**

- **SAI-5** - **Aucun appariement approximatif, aucune tolerance calculee sur le nombre de caracteres
  differents, nulle part.** Interdiction absolue et sans exception de niveau sur toute reponse qui est
  un nombre, une mesure, une unite, une date ou une heure. Les seules tolerances existantes sont
  SAI-1, SAI-3 et SAI-4, qui sont des regles nommees, pas des distances.
- **SAI-6** - Les formes acceptees d'un item sont **les fautes previsibles de ce mot precis**, celles
  qu'un maitre voit reellement dans les copies de ce niveau, enumerees une par une. Jamais une
  variante inventee pour faire nombre, jamais un mot different.
- **SAI-7** - **Cette liste est ecrite ou relue par moi, sur chaque paquet de saisie, avant tout Done
  pedagogique.** Ce n'est pas une exigence de confort : la liste des formes acceptees **est** le bareme
  de l'exercice. La faire ecrire par un developpeur revient a lui faire noter la copie. Je le reclame
  maintenant plutot que de le decouvrir en refusant un paquet deja ecrit.

---

## 5. Faut-il distinguer le faux du presque juste dans le retour a l'enfant ?

### Verdict : **le verdict reste binaire, le message ne l'est pas.**

**Pourquoi le verdict reste binaire.** Un enfant de six ou neuf ans a besoin de savoir s'il a reussi.
Un troisieme etat - "presque" - est illisible a cet age et il est surtout **injuste dans le score** :
l'enfant ne sait pas si cela lui compte, et l'adulte a cote de lui non plus. Le score de ce produit
compte des manches reussies ; il n'y a pas de demi-manche.

**Pourquoi le message ne l'est pas.** C'est exactement ce qu'un maitre fait : il valide, et il corrige
dans le meme souffle. *"Oui, c'est bien élève - regarde, il y a deux accents."* La reponse est
comptee juste ; l'enfant apprend quand meme l'orthographe. La distinction que la question cherche
existe donc, mais elle vit dans le texte du retour, pas dans le verdict ni dans le score.

- **SAI-8** - Quand une reponse est acceptee par une tolerance (SAI-1, SAI-3, SAI-4), le verdict est
  **juste**, la manche est reussie, le score augmente, **et le retour montre la forme de reference,
  integralement accentuee et sans faute** (`DEC-0002`, amendement du 2026-08-04). Une formulation qui
  convient : *"Oui ! On écrit : élève."* Elle valide d'abord, elle corrige ensuite, elle ne reproche
  rien.
- **SAI-9** - Quand une reponse est refusee, **le retour montre ce que l'enfant a ecrit a cote de la
  reponse attendue**. C'est la condition qui remplace l'acquis M9 que la saisie detruit (section 0).
  *"Tu as écrit : elleve. On écrit : élève."* Un simple *"Non, c'est élève"* ne dit pas ou est
  l'erreur, et l'enfant qui ne voit pas son erreur ne l'apprend pas.
- **SAI-10** - Le signal non textuel de M11 clause 1 continue de s'appliquer sans changement : le
  retour ne repose jamais sur la couleur seule. Deux etats de signal, pas trois.

---

## 6. Les lignes sur lesquelles la saisie serait pire qu'un choix, et sur lesquelles je refuse par avance

Cinq refus, ecrits maintenant pour qu'aucun lot ne soit jete apres coup. Ils sont opposables a un
increment.

- **R1 - *Copier un mot, une phrase courte sans erreur, en respectant l'espacement* (francais CP).**
  C'est la ligne qui ressemble le plus a de la saisie, et c'est un piege. Copier est une competence
  **graphomotrice** : tenir l'outil, former les lettres, respecter les interlignes, espacer les mots
  sur la ligne. Un clavier n'en travaille aucune. Un paquet de saisie sur cette ligne la ferait passer
  en `couvert-valide` sans avoir jamais travaille ce qu'elle nomme.
- **R2 - *Comprendre un texte narratif ou documentaire* (CP et CM1) et *Lire silencieusement un texte
  et en restituer l'essentiel* (CM1).** Une reponse libre sur un texte a des dizaines de formulations
  correctes. Enumerer les formes acceptees (SAI-6) revient a en retenir trois et a compter fausses les
  quatre-vingt-dix-sept autres. L'enfant qui a parfaitement compris et qui l'a dit autrement est puni
  d'avoir ses propres mots. Sur ces lignes, un choix parmi quatre est **superieur**, et je l'ai deja
  accepte comme tel a s17.
- **R3 - *Rediger un texte narratif court et coherent* (CM1).** C'est la ligne que la saisie aura
  l'air d'ouvrir, et elle ne l'ouvre pas. La coherence d'un recit ne se compare pas a des chaines. Un
  Done pedagogique sur cette ligne par une mecanique de saisie **est refuse par avance**, quelle que
  soit la qualite du lot.
- **R4 - *Relire et corriger son texte* (CM1).** Traite dans mon autre arbitrage de ce sprint
  (`ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`, section 4) : la saisie ne la
  debloque pas, elle est hors de portee de ce produit tel que le referentiel l'ecrit.
- **R5 - *Utiliser des synonymes et antonymes pour preciser ou nuancer un propos* (CM1).** Un mot a
  plusieurs synonymes justes, et un enfant qui en connait un de plus que la liste est compte faux pour
  avoir su davantage. Le choix parmi quatre ferme l'espace des reponses, et c'est ici une qualite.

**Et les deux lignes d'operation posee - multiplication posee, division euclidienne.** Je reconduis
sans le modifier ce que j'ai ecrit le 2026-08-15 : ce n'est pas de la saisie, le geste lui-meme est la
competence, et il se fait sur du papier en colonnes. `theo-archi` a confirme de son cote que ce sont
une **troisieme** mecanique et non une variante de la saisie. Elles ne doivent pas etre comptees avec
elle dans un arbitrage de sequencement.

### Le cas du CP, qui merite d'etre separe en deux

**La saisie alphabetique est pire qu'un choix sur toutes les lignes de CP, et le motif n'est pas
pedagogique au sens etroit : il est social.** Un enfant de CP ne connait pas le clavier. Chercher les
lettres d'un mot lui prend une minute, et cette minute est entierement de la charge cognitive hors
sujet - pendant qu'il cherche le `p`, il perd le mot qu'il voulait ecrire. Surtout, ce qu'un tel jeu
mesure alors, c'est la familiarite de l'enfant avec un clavier, donc l'equipement de la maison.
`DEC-0040` place explicitement ce produit *"a la maison sur l'appareil disponible"*. Je ne veux pas
d'un exercice ou l'enfant d'une famille equipee reussit et l'autre non, sur une difference qui n'est
pas scolaire.

**La saisie numerique courte au CP est une autre affaire, et elle est meilleure qu'un choix.** Taper
`7` ou `12`, c'est appuyer sur une ou deux touches que l'enfant reconnait, et cela **supprime la
chance** : sur les complements a 10, sur les additions, un enfant qui choisit parmi quatre tombe juste
une fois sur quatre sans rien savoir. Une saisie de un a deux chiffres retire ce bruit et rend le
score honnete. Je suis favorable a cet usage-la.

- **SAI-11** - Au CP, la saisie est autorisee pour une reponse **numerique d'au plus deux chiffres**,
  et refusee pour toute reponse alphabetique, tant qu'aucun arbitrage de l'utilisatrice n'a repondu a
  la question de la section 8.

**Une exception qu'il faut connaitre avant de la decouvrir.** La ligne CP *"Ecrire de maniere autonome
un mot simple en respectant les correspondances grapheme/phoneme connues"* est la seule ou la saisie
alphabetique **est** la competence : l'enfant doit produire les lettres, un choix ne le ferait pas
travailler. Mais pour qu'il ecrive un mot sans qu'on le lui montre, il faut qu'il l'**entende** - sinon
on le lui affiche et c'est de la copie, donc R1. **Cette ligne demande donc la saisie et l'audio
ensemble**, et aucune des deux seule. Je le signale a `camille-po` parce que les deux familles y sont
nouees, et que cela ne se voit dans aucun tableau de sequencement.

---

## 7. Le seuil en dessous duquel je prefererais que la saisie n'existe pas

Sur le modele de ce que j'ai ecrit pour l'audio le 2026-08-15, et pour la meme raison : ecrit
maintenant, pas apres coup. Ce ne sont pas des conditions d'audit, c'est la barre en dessous de
laquelle cette mecanique vaudrait moins que celle qu'on a deja.

- **SEUIL-1** - **Si l'enfant ne voit pas ce qu'il a ecrit au moment du verdict, la saisie ne doit pas
  exister.** C'est SAI-9. Aujourd'hui, avec des boutons, l'enfant voit son erreur ; une saisie qui
  ferait moins bien serait une regression pedagogique, pas une nouvelle capacite.
- **SEUIL-2** - **Si une reponse juste refusee pour une question de forme coute un essai, la saisie ne
  doit pas exister.** Il n'y a que trois essais avant la revelation (M5). Un enfant qui ecrit le bon
  mot de trois facons legerement differentes brulerait sa manche entiere en ayant eu raison trois
  fois. C'est le pire scenario de cette mecanique et il est entierement evitable par SAI-1, SAI-3,
  SAI-4.
- **SEUIL-3** - **Si l'enfant ne peut pas corriger sa saisie avant de valider, la saisie ne doit pas
  exister.** Effacer, retaper, relire avant d'envoyer. Une frappe irreversible transformerait chaque
  manche en piege de dexterite.
- **SEUIL-4** - **Si le champ de saisie n'est pas atteignable et validable au clavier seul, la saisie
  ne doit pas exister.** Ce produit a paye trois sprints de dette sur les arrets de tabulation ; je ne
  laisse pas une mecanique neuve rouvrir ce chantier.
- **SEUIL-5** - **Si les formes acceptees d'un paquet ne sont relues par personne de competent en
  francais, la saisie ne doit pas exister.** C'est SAI-7. Une liste de formes acceptees ecrite
  a l'estime est un bareme secret : elle decide de la note sans que personne ne l'ait validee, et
  aucune CI ne la verra jamais.

---

## 8. Ce que je route plutot que de le trancher

- **A `theo-archi`** : ou vivent SAI-1, SAI-3 et SAI-4 - dans une normalisation que la mecanique
  possede, dans une enumeration portee par le paquet, ou en deux endroits. Je fixe le resultat, jamais
  le chemin. Je note seulement, comme observation et non comme prescription, que SAI-2 exige un
  interrupteur declare par le contenu **au niveau de l'item**, pas seulement du paquet : c'est ce
  qu'un paquet melangeant vocabulaire et homophones imposerait.
- **A `theo-archi` egalement** : SAI-9 suppose que le serveur connaisse la chaine que l'enfant a
  envoyee au moment ou il rend le retour. Je n'ai aucune idee de ce que cela coute et ce n'est pas ma
  question ; c'est en revanche ma condition la plus dure et il vaut mieux qu'il l'apprenne avant
  d'ecrire l'`ADR` qu'apres.
- **A l'utilisatrice, via `sofia-sm`, et cette question conditionne la portee de tout ce document** :
  **une lettre accentuee est-elle accessible d'un geste raisonnable sur la tablette visee ?** Sur un
  clavier physique francais, les lettres accentuees courantes sont des touches directes. Sur un clavier
  virtuel de tablette, elles demandent souvent un appui long. Je ne peux pas le mesurer : aucun agent
  de cette squad ne dispose de l'appareil, exactement comme pour l'ecoute au lecteur d'ecran de la
  reserve F11 et pour l'enregistrement des sons. Si la reponse est "appui long", alors SAI-1 n'est plus
  un confort, c'est la condition d'existence de la saisie alphabetique.
- **A l'utilisatrice egalement** : **dans quel contexte un enfant de CP utilise-t-il `recreo`** - seul,
  avec un adulte a cote, sur quel appareil ? `DEC-0040` fixe les supports mais pas cela, et je n'ai
  trouve la reponse nulle part dans le depot. Elle change mon verdict de la section 6 sur le CP : un
  enfant accompagne peut taper un mot, un enfant seul devant une tablette non.

---

## 9. Sur quelles proprietes je reste la seule garde

- **SAI-6 et SAI-7** - le contenu des listes de formes acceptees. Aucun test ne peut dire si
  *"eleve"* manque a la liste de *eleve*, ni si *"cheveux"* n'a rien a y faire. **C'est le point le
  plus dangereux de toute cette mecanique** : un bareme faux passe une CI verte sans laisser de trace,
  et l'enfant est le seul a en payer le prix, une reponse a la fois.
- **SAI-8 et SAI-9** - la formulation du retour. Un test peut verifier que deux chaines sont affichees ;
  il ne peut pas dire si un enfant de six ans comprend ce qu'il lit.
- **R1 a R5** - ces cinq refus ne sont tenus par rien d'autre que ce fichier. Le jour ou une story
  ouvrira une de ces lignes en saisie, la CI sera verte et je serai la seule a dire non.
- **SEUIL-2** - l'interaction entre une tolerance manquante et les trois essais de M5. Aucune regle
  n'existe qui empeche une reponse juste de couter une manche.

## 10. Suites hors de ma main

- **`theo-archi`** : SAI-1 a SAI-11 sont les entrees pedagogiques de son `ADR`. SAI-2 (interrupteur
  declare par le contenu) et SAI-9 (montrer ce que l'enfant a ecrit) sont les deux qui ont le plus de
  chances de peser sur sa conception ; les autres decrivent un resultat sans contraindre le chemin.
- **`camille-po`** : cinq lignes du referentiel sur lesquelles je refuse par avance (R1 a R5), une
  ligne qui demande saisie **et** audio ensemble (section 6), et un usage de la saisie que je
  recommande positivement - la reponse numerique courte, qui supprime la chance sur les paquets de
  calcul deja `couvert-valide`. Ce dernier point n'est pas une couverture neuve, c'est une
  amelioration d'existant, et il lui revient d'en decider la valeur.
- **`sofia-sm`** : deux questions a router a l'utilisatrice (section 8), dont la premiere - l'acces a
  une lettre accentuee sur la tablette - devrait etre posee **avant** que l'`ADR` ne soit ecrit.
- **`ines-design`** : rien ne lui est demande ici, mais SAI-9 lui donnera un objet a dessiner - deux
  textes cote a cote dans un retour qui n'en portait qu'un. Le jour venu, sa proposition ne se valide
  pas contre mon avis, comme d'habitude.

---

## Amendement du 2026-08-24, meme jour - `SAI-9` : `theo-archi` a raison, ma redaction disait plus que mon intention

`theo-archi` a ecrit `ADR-0058` en s'appuyant sur cet arbitrage et **il conteste la lecture
litterale de `SAI-9`** au lieu de la contourner en silence. Il a eu raison de le faire, et il a
raison sur le fond. Je tranche ici, et **je ne rends pas ma reponse plus facile pour l'architecture
qu'elle ne doit l'etre pour l'enfant** : je verifie d'abord ce que la lecture litterale ferait a
l'enfant, et c'est cela qui decide.

### 1. Ce que ma phrase disait, et ce que j'avais en tete

J'ai ecrit : *"Quand une reponse est refusee, le retour montre ce que l'enfant a ecrit a cote de la
reponse attendue"*, avec l'exemple *"Tu as écrit : elleve. On écrit : élève."*

**Applique a chaque refus, cela nomme la bonne reponse des le premier essai.** `theo-archi` cite
exactement ce qui s'y oppose, et j'ai verifie les deux citations mot pour mot :

- **`M5`** donne **trois** essais par manche avant la revelation
  (`BanditManchotSession.ESSAIS_AVANT_REVELATION = 3`) ;
- **`M9` clause 5** - *"aucune marque ne designe la bonne reponse avant la revelation"* - est
  encodee dans le domaine, et sa Javadoc l'ecrit : *"la bonne reponse de la manche courante, exposee
  seulement une fois revelee. Avant ce moment, aucune methode de cette classe ne la designe."*

**Et ma propre section 0 dit deja la bonne chose.** J'y ecris que l'enfant *"voit en permanence ce
qu'il a deja essaye, et **au moment de la revelation** il voit son erreur a cote de la bonne
reponse"*. Deux moments distincts, deux contenus distincts. `SAI-9` les a fondus en un seul et a
demande les deux a chaque refus. **C'est ma redaction qui a derive, pas sa lecture.**

### 2. Ce que la lecture litterale ferait a l'enfant, et c'est cela qui tranche

**Elle detruirait les trois essais.** Un enfant qui recoit *"On écrit : élève"* apres sa premiere
tentative n'a plus rien a chercher : les essais 2 et 3 deviennent de la recopie. `M5` existe parce
qu'un enfant a le droit de se reprendre avec sa propre tete, et un dispositif qui donne la reponse
au premier ecart lui retire exactement l'occasion d'apprendre que le troisieme essai lui offre. Ce
serait **strictement pire que la mecanique d'aujourd'hui**, ou aucune proposition n'est designee
avant la revelation.

**J'aurais refuse un increment qui aurait implemente ma phrase au pied de la lettre.** C'est le
motif de ma decision, et il n'est pas de commodite : la lecture de `theo-archi` n'est pas un
compromis technique, c'est la lecture pedagogiquement juste.

### 3. La decision

**Je retiens la lecture de `theo-archi`**, et je reecris `SAI-9` en deux clauses qui disent
separement ce que ma phrase melangeait. Le libelle d'origine reste ci-dessus, il n'est pas efface :
c'est de lui que la correction part.

- **`SAI-9a`** - **A chaque refus, avant la revelation : la chaine que l'enfant a ecrite reste
  visible, et la reponse attendue n'est jamais nommee.** Le retour dit qu'il s'est trompe et lui
  laisse ce qu'il a produit sous les yeux, pour qu'il le corrige plutot que de retaper a l'aveugle.
  Aucune formulation ne doit designer, meme indirectement, la bonne reponse - pas plus qu'une marque
  ne le fait aujourd'hui sur un bouton.
- **`SAI-9b`** - **A la revelation, et seulement la : la chaine de l'enfant et la reponse attendue
  sont montrees ensemble.** C'est la que mon exemple s'applique : *"Tu as écrit : elleve. On écrit :
  élève."* C'est aussi le moment ou la mecanique actuelle nomme deja la bonne reponse, donc rien
  n'est ajoute au regime existant.

**`M9` clause 5 n'est pas rouverte, et son encodage dans le domaine n'a pas a bouger.** Je le dis
explicitement pour que `theo-archi` n'engage pas l'amendement date qu'il proposait : il n'y a rien a
amender.

### 4. Une condition **neuve**, datee du 2026-08-24, que cet echange me fait voir et que je n'ecris pas comme un durcissement de `SAI-9`

En verifiant `M9`, je vois ce que ni `SAI-9` ni la lecture de `theo-archi` ne couvrent, et c'est un
manque reel.

Avec des boutons, **toutes** les propositions deja pressees restent marquees : au troisieme essai,
l'enfant voit ses deux erreurs precedentes et ne les repete pas. C'est la clause de `M9` qui n'a
jamais eu besoin d'etre ecrite, parce que le balisage la donnait gratuitement. Dans un champ de
saisie, si seule la derniere chaine survit, **un enfant peut retaper au troisieme essai ce qu'il
avait deja ecrit au premier**, et user une manche sur une repetition qu'il ne voit pas.

- **`SAI-12` (neuve, 2026-08-24, non retroactive)** - **Aux deuxieme et troisieme essais, l'enfant
  voit la liste de ce qu'il a deja ecrit sur cette manche**, pas seulement sa derniere tentative.
  C'est l'equivalent de `M9` pour la saisie, et c'est la seule facon que les trois essais veuillent
  dire la meme chose dans les deux mecaniques.

**Je l'ecris comme une condition neuve et non comme une precision de `SAI-9`**, parce qu'elle demande
davantage que ce que `theo-archi` a chiffre - il ecrit que le serveur garde la chaine de toute facon,
au singulier, et garder trois chaines n'est pas la meme chose. **Si elle coute, qu'il le dise et
qu'il la chiffre a part** ; je ne la fais pas passer pour acquise dans une decision deja rendue.

### 5. Ce que cet amendement ne change pas

`SAI-1` a `SAI-8`, `SAI-10`, `SAI-11`, `R1` a `R5` et `SEUIL-1` a `SEUIL-5` sont inchanges.
**`SEUIL-1` reste ecrit contre `SAI-9a`** : si l'enfant ne voit pas ce qu'il a ecrit au moment du
verdict, la saisie ne doit pas exister. Ce seuil ne portait pas sur la reponse attendue, il portait
sur la chaine de l'enfant, et cette moitie-la n'a jamais ete en cause.

Je confirme par ailleurs, sans rien y ajouter, les deux points que `theo-archi` valide sans
correction dans mon autre arbitrage de ce sprint : ni les figures planes ni le decodage syllabique
n'appellent une mecanique neuve, ce sont des formes d'item. Mes deux routages - visuel et audio -
portent sur ce que la forme d'item doit pouvoir rendre, jamais sur le geste de l'enfant, et sa
lecture est la bonne.

---

## Amendement du 2026-08-25 (hors sprint) - "la saisie se fait uniquement par le clavier, aucun trace envisage"

- **Auteur** : nadia-prof. **Fait neuf** : l'utilisatrice arbitre que *"la saisie se fait uniquement
  par le clavier, aucun trace envisage"* et qu'*"un enfant de CP fait toujours le premier essai
  accompagne"*. Le second point repond a la **deuxieme** question que la section 8 lui routait.
- **Rien du document ci-dessus n'est efface ni reecrit.** Cet amendement ajoute, il ne remplace pas.
- **Mode** : aucune mesure, aucun jeu - la mecanique de saisie n'existe toujours pas. Je relis mes
  propres conditions contre deux phrases neuves, c'est tout ce que ce bloc est.

### 0. Une inexactitude de decompte, corrigee avant d'aller plus loin

Ce document est cite ailleurs comme portant *"onze conditions `SAI-1` a `SAI-12`"*. Les deux moities
de cette phrase ne peuvent pas etre vraies ensemble : **il y a douze conditions numerotees,
`SAI-1` a `SAI-12`**, `SAI-12` ayant ete ajoutee par l'amendement du 2026-08-24 (section 4 de cet
amendement), et `SAI-9` comptant de plus deux clauses distinctes, `SAI-9a` et `SAI-9b`. Onze etait
le compte avant cet amendement-la. Je le corrige ici plutot qu'ailleurs, parce que c'est mon fichier
qui fait foi.

### 1. "Uniquement par le clavier" : aucune des douze conditions ne bouge, et je dis pourquoi plutot que de me taire

**Ce document etait deja ecrit pour un clavier, de bout en bout, et je l'ai verifie mot par mot
plutot que de m'en souvenir** : le mot *trace* n'y figure nulle part, ni *stylet*, ni *manuscrit*,
ni *cursive*. `SEUIL-4` exige deja que le champ soit *"atteignable et validable au clavier seul"*, et
la question de la section 8 oppose deja *"un clavier physique francais"* a *"un clavier virtuel de
tablette"* - donc deux claviers, jamais un geste trace.

**Consequence : `SAI-1` a `SAI-12`, `R1` a `R5` et `SEUIL-1` a `SEUIL-5` sont inchanges par la
moitie "clavier" de cet arbitrage.** Je l'ecris explicitement pour qu'on ne lise pas ce silence
comme un oubli : l'arbitrage confirme une hypothese que j'avais deja prise, il n'en corrige aucune.

**Un seul changement de statut, sans changement de texte.** `SEUIL-4` cesse d'etre une precaution
d'accessibilite et devient la **definition** de la mecanique : si le clavier est le seul geste, un
champ qu'on n'atteint pas au clavier n'est pas une mecanique degradee, c'est une mecanique
inutilisable. Le libelle est bon tel quel, sa portee est plus lourde.

**Un renfort de motif sur `SAI-3`, sans changement de texte non plus.** Sur un clavier virtuel, le
systeme met tres souvent une capitale a la premiere lettre sans qu'on la demande. `SAI-3` disait
deja que la casse ne decide jamais du verdict, pour un motif d'enfant (la touche de verrouillage
oubliee, l'ecriture en capitales du CP) ; s'y ajoute maintenant un motif d'appareil, ou l'enfant
n'est meme pas l'auteur de la capitale. La condition ne change pas ; elle a un motif de plus, et il
est plus fort que le premier.

### 2. Ce que cet arbitrage ne tranche pas, et que je n'ai pas suppose

La question de la section 8 - **une lettre accentuee est-elle accessible d'un geste raisonnable sur
la tablette visee ?** - **reste entierement ouverte.** L'arbitrage dit *clavier*, il ne dit pas
*lequel*, et `DEC-0040` vise l'ordinateur **et** la tablette. Sur un clavier physique francais une
lettre accentuee courante est une touche ; sur un clavier virtuel elle peut couter un appui long.
Tant que ce point n'est pas repondu, **`SAI-1` n'est pas un confort mais la condition d'existence de
la saisie alphabetique**, exactement comme je l'ecrivais le 2026-08-24.

**J'elargis cette question d'un point que "uniquement par le clavier" fait apparaitre**, et je
l'ecris comme un elargissement de la question ouverte, pas comme une condition neuve : un clavier
virtuel **corrige et complete souvent tout seul** ce que l'enfant tape. Une correction automatique
peut rendre juste une reponse fausse - l'enfant est credite d'une orthographe qu'il n'a pas produite
- et peut aussi defaire une reponse juste. Cela ne se voit sur aucune capture et aucun test du
depot ne l'atteindra. Sur un paquet en mode strict au titre de `SAI-2` - les homophones, qui sont
`couvert-valide` - ce serait franchement grave. **Je ne peux pas le mesurer** : aucun agent de cette
squad ne dispose de l'appareil. La question est donc double et va a l'utilisatrice avec la premiere :
sur la tablette visee, **que coute un accent, et le clavier corrige-t-il tout seul ?**

### 3. Ce que "le premier essai accompagne" fait a `SAI-11`, et c'est la seule condition que cet arbitrage deplace

**`SAI-11` est la condition touchee**, plus que `SAI-1` ou `SAI-3`, parce qu'elle est la seule dont
le libelle contient une clause suspensive qui vient d'etre partiellement remplie. Elle dit :

> *"Au CP, la saisie est autorisee pour une reponse numerique d'au plus deux chiffres, et refusee
> pour toute reponse alphabetique, tant qu'aucun arbitrage de l'utilisatrice n'a repondu a la
> question de la section 8."*

Un arbitrage vient d'y repondre. **Je verifie donc d'abord si ma propre barre est atteinte, avant de
dire quoi que ce soit d'autre** - c'est exactement le cas ou une porte pedagogique se transforme en
caprice si elle se derobe.

**Ce que j'avais ecrit en section 8** : *"un enfant accompagne peut taper un mot, un enfant seul
devant une tablette non"*. Cette phrase pose l'accompagnement comme un **etat durable de l'usage**.
Ce que l'arbitrage accorde est **borne au premier essai**. Ce n'est pas la meme chose, et ce n'est
pas moi qui retrecis la reponse : c'est la reponse qui est plus etroite que la question. Je le dis
franchement dans les deux sens - **ma barre n'est pas atteinte, et elle ne l'est pas parce que la
reponse ne porte pas dessus, non parce que je l'aurais relevee.**

**Sur le fond, les deux motifs du refus survivent intacts a la borne, et c'est cela qui decide.**
Mon refus de la saisie alphabetique au CP (section 6) tenait a deux choses :

- **la charge cognitive** : chercher les lettres coute une minute, et pendant ce temps l'enfant perd
  le mot qu'il voulait ecrire. Un adulte assis a cote ne rend pas les touches plus faciles a
  trouver ; et surtout **cette difficulte revient a chaque item**, dix fois par partie, alors que
  l'accompagnement, lui, ne revient pas ;
- **le motif social** : un tel exercice mesure la familiarite de la maison avec un clavier, pas ce
  que l'enfant sait. Un adulte present au premier essai n'equipe pas la maison.

**`SAI-11` tient donc**, mais **son libelle doit changer**, sinon il expire tout seul sur une
question qui vient de recevoir une reponse partielle et quelqu'un lira l'expiration comme une levee.

- **`SAI-11`, reformulee au 2026-08-25 (le libelle d'origine reste ci-dessus, il n'est pas efface)** -
  *Au CP, la saisie est autorisee pour une reponse **numerique d'au plus deux chiffres**. Elle est
  refusee pour toute reponse alphabetique, pour deux motifs qui ne dependent d'aucune question
  ouverte : la recherche des lettres est une charge hors sujet qui se represente a chaque item, et
  le resultat mesure l'equipement de la maison. **Cette clause n'expire plus d'elle-meme.***
- **Ce qui la leverait, nomme pour qu'elle soit fermable et non eternelle** : que l'accompagnement
  d'un adulte soit **durable sur toute une session de CP** et non borne au premier essai. Si
  l'utilisatrice le dit un jour, je leve `SAI-11` sur l'alphabetique le jour meme, sans rien y
  ajouter. Je m'y engage ici pour que ce ne soit pas a rediscuter.

**Une moitie de ma deuxieme question reste par ailleurs sans reponse** : j'avais demande *"seul, avec
un adulte a cote, **sur quel appareil** ?"*. L'arbitrage repond sur l'adulte, pas sur l'appareil, et
je ne le suppose pas.

### 4. Une consequence hors de la mecanique de saisie, que je route sans la trancher

*"Aucun trace envisage"* depasse la saisie et touche la couverture du programme. **Je ne classe
aucune ligne** - c'est `camille-po` qui le fait. Mon avis, qu'elle m'a demande, est ecrit dans
`ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`, amendement du 2026-08-25,
section A, a cote de mes conditions `FIG-1` a `FIG-4` sur les figures planes du CP, parce que c'est
la que la geometrie de ce depot est deja instruite.

Deux points brefs qui restent ici parce qu'ils portent sur la saisie :

- **`R1`** - *"Copier un mot, une phrase courte sans erreur, en respectant l'espacement"* (CP). Je
  l'avais refusee a la saisie pour un motif qui lui est propre. *"Aucun trace envisage"* la ferme
  une seconde fois et par une autre voie : la copie au CP est un geste d'ecriture manuscrite, et ce
  produit ne l'aura pas. **Le refus ne change pas, sa cause est desormais double.**
- **La ligne CP *"Ecrire de maniere autonome un mot simple en respectant les correspondances
  grapheme/phoneme connues"*** demande saisie **et** audio ensemble (section 6). `SAI-11` restant
  fermee sur l'alphabetique au CP, elle reste bloquee par un troisieme verrou en plus des deux que
  j'avais nommes. Rien de neuf, mais le decompte doit etre exact pour `camille-po`.

### 5. Sur quelles proprietes je reste la seule garde, apres cet amendement

Inchange par rapport a la section 9 ci-dessus, avec deux ajouts que cet arbitrage cree :

- **Le comportement reel d'un clavier virtuel** - cout d'un accent, correction automatique
  silencieuse. Aucun test de ce depot ne l'atteindra, aucune capture ne le montrera, et un bareme
  fausse par l'appareil ressemble trait pour trait a un bareme faux : une CI verte, et l'enfant seul
  a payer.
- **La presence effective de l'adulte au premier essai.** Le produit en depend desormais et ne la
  demande a personne. Je le route a `camille-po` comme un point produit (voir aussi
  `FICHE-bandit-manchot-nature-mot-cp.md`, amendement du 2026-08-25, section 6).

---

## Second amendement du 2026-08-25 (hors sprint) - accompagnement durable au CP : je leve `SAI-11` sur l'alphabetique, comme je m'y etais engagee ce matin

- **Auteur** : nadia-prof. **Fait neuf** : *"considere qu'un eleve de CP est toujours accompagne par
  un adulte"*. Ce n'est plus le premier essai, c'est l'etat durable.
- **Rien de ce qui precede n'est efface ni reecrit**, ni le document du 2026-08-24, ni l'amendement
  du meme jour, ni le premier amendement du 2026-08-25.

### 1. La barre etait la mienne, elle est atteinte, je leve - et ce n'est pas negociable avec moi-meme

J'ai ecrit ce matin, a la fin de la section 3 du premier amendement, mot pour mot :

> *"Ce qui la leverait, nomme pour qu'elle soit fermable et non eternelle : que l'accompagnement d'un
> adulte soit **durable sur toute une session de CP** et non borne au premier essai. Si
> l'utilisatrice le dit un jour, **je leve `SAI-11` sur l'alphabetique le jour meme, sans rien y
> ajouter. Je m'y engage ici pour que ce ne soit pas a rediscuter.**"*

Elle vient de le dire. **Je leve, le jour meme, et je n'ajoute rien.**

Je le fais en sachant tres bien que j'aurais pu argumenter. C'est precisement pour cela que
l'engagement etait ecrit : une porte pedagogique dont la condition de levee se renegocie le jour ou
elle est remplie n'est plus une porte, c'est un caprice. **Refuser ce matin une reponse trop etroite
et lever ce soir une reponse qui couvre est le meme geste, dans les deux sens.**

### 2. `SAI-11`, reformulee au 2026-08-25 (second amendement)

Les deux libelles precedents restent ci-dessus, ils ne sont pas effaces.

- **`SAI-11`, en vigueur** - *Au CP, la saisie est autorisee, pour une reponse numerique comme pour
  une reponse alphabetique. Le refus de l'alphabetique est leve : la premisse du produit est qu'un
  eleve de CP est durablement accompagne par un adulte.*
- **La recommandation positive de la section 6 est inchangee et je la reconduis** : la reponse
  **numerique d'au plus deux chiffres** reste le meilleur usage de la saisie au CP, parce qu'elle
  **supprime la chance** sur des paquets de calcul deja `couvert-valide`. Levee ne veut pas dire
  equivalence : la saisie alphabetique est desormais permise, la saisie numerique courte reste ce
  que je recommande en premier.

### 3. Les deux motifs, un par un, comme on me le demande

**Motif 1 - la charge cognitive. Il tombe, et je ne le retiens pas.** J'avais ecrit que chercher les
lettres coute une minute et que *"pendant qu'il cherche le `p`, il perd le mot qu'il voulait
ecrire"*. Un adulte durablement present repond exactement a ce point : il redit le mot, il indique
la touche. Ce qui reste est que l'ecriture sera lente - et **la lenteur n'a jamais ete un motif de
refus**, dans aucun de mes avis.

**Motif 2 - l'equipement. La lecture qui m'est proposee est juste, et je ne le retiens quand meme
pas.** Il est exact que ce motif parle d'equipement et non de presence, que l'arbitrage ne dit rien
de l'equipement, et qu'un adulte assis a cote ne met pas un clavier dans une maison qui n'en a pas.
**Il survivrait donc a l'arbitrage, si je le laissais survivre.**

**Je ne le laisse pas survivre, et le motif est de methode, pas de fond.** Le motif 2 n'etait pas
une condition separee : c'etait la **deuxieme moitie de `SAI-11`**. Lever `SAI-11` puis rouvrir
immediatement le meme refus sous un autre numero, avec la moitie du meme raisonnement, quelques
heures apres avoir ecrit *"sans rien y ajouter"*, serait deplacer mes poteaux en le documentant
proprement - ce qui est pire que de les deplacer franchement. **La levee est entiere ou elle n'est
pas.**

**Ou va ma preoccupation, alors, puisqu'elle est reelle.** Elle va la ou elle aurait toujours du
etre : **dans mon audit de l'increment**, pas dans un refus prealable. Le jour ou un paquet de
saisie alphabetique au CP sera livre, je le jouerai comme les autres, et **si l'exercice mesure
visiblement la familiarite avec un clavier plutot que ce que l'enfant sait, ce sera un verdict sur
cet increment-la, motive et opposable** - pas une condition qui l'aura interdit avant d'exister.
C'est une position plus faible que le refus prealable, et je l'assume : c'est le prix de
l'engagement que j'ai pris, et il vaut d'etre paye.

### 4. Ce que cet arbitrage ne touche pas, verifie condition par condition

- **`SAI-1` (accents) et `SAI-3` (casse)** : **inchangees, aux deux niveaux.** Leur motif est
  materiel - ce que coute une lettre accentuee sur l'appareil, ce qu'un clavier virtuel capitalise
  tout seul. **Un adulte a cote ne change pas le clavier.** Elles ne dependaient pas de la solitude
  de l'enfant et ne dependent pas davantage de sa compagnie.
- **`SAI-2`, `SAI-4` a `SAI-10`, `SAI-12`, `SEUIL-1` a `SEUIL-5`** : inchangees. Aucune ne reposait
  sur l'hypothese d'un enfant seul.
- **`R1` a `R5`** : inchangees, relues une par une pour le verifier plutot que l'affirmer. `R1` est
  un refus **graphomoteur** (tenir l'outil, former les lettres), `R2`, `R3` et `R5` portent sur
  l'**espace des reponses justes** qu'une comparaison de chaines ne couvre pas, `R4` est hors de
  portee du produit. **Aucun des cinq ne mentionne ni ne suppose la presence ou l'absence d'un
  adulte.**

### 5. Le CP seulement - l'arbitrage ne dit rien du CM1 et je ne l'etends pas

**L'arbitrage porte sur *"un eleve de CP"*. Le CM1 n'y est pas, et je refuse de l'y lire.** Je
l'ecris comme une mise en garde parce que la formule *"toujours accompagne"* est du genre a se
propager toute seule d'un niveau a l'autre dans un fichier de backlog.

Et je le dis dans le sens pedagogique aussi, pas seulement par prudence de perimetre : **un eleve de
CM1 durablement assiste par un adulte n'est pas le cas normal a cet age**, et concevoir du contenu
de CM1 sur cette hypothese serait une erreur plus grave que l'inverse. Au CM1, l'autonomie fait
partie de ce qu'on travaille. Toute condition visant les deux niveaux garde donc sa portee CM1
entiere : c'est le cas de `SAI-1` et de `SAI-3`, les seules concernees.

### 6. La question de la section 8 reste ouverte, et elle porte desormais plus lourd

**Je ne la ferme pas par entrainement.** L'arbitrage repond sur l'adulte ; il ne dit **ni l'appareil,
ni le clavier**, et `DEC-0040` vise l'ordinateur **et** la tablette. Restent donc entieres les deux
faces que j'ai posees ce matin : **ce que coute un accent sur la tablette visee, et si son clavier
corrige tout seul** - une correction automatique pouvant crediter un enfant d'une orthographe qu'il
n'a pas produite, ce qui serait franchement grave sur un paquet en mode strict au titre de `SAI-2`
comme les homophones, `couvert-valide` depuis s18.

**Et ce que la levee de `SAI-11` fait a cette question, il faut le dire clairement a `sofia-sm` :
elle devient plus urgente, pas moins.** Tant que l'alphabetique etait refuse au CP, la question ne
pesait que sur le CM1. Elle pese maintenant sur les deux niveaux, et sur un usage que je viens
d'autoriser.

### 7. Sur quelles proprietes je reste la seule garde, apres cette levee

- **`SAI-6` et `SAI-7`** - le contenu des listes de formes acceptees. Inchange, et c'est toujours le
  point le plus dangereux de cette mecanique.
- **Le comportement reel d'un clavier virtuel** - cout d'un accent, correction silencieuse. Inchange.
- **Ce que je viens de ceder, et je le nomme pour qu'il ne se perde pas** : la saisie alphabetique au
  CP n'est plus gardee par aucune condition prealable. **Elle n'est gardee que par mon audit de
  l'increment**, une fois qu'il existera. C'est plus tard et c'est plus faible ; c'est desormais le
  seul filet sur cette famille-la.

---

## Troisieme amendement du 2026-08-25 (hors sprint) - correction automatique acceptee sur les deux appareils : `SAI-2` est amendee, ni maintenue telle quelle ni supprimee

- **Auteur** : nadia-prof. **Fait neuf** : l'utilisatrice tranche la question que je routais depuis
  le 2026-08-24 - **ordinateur et tablette, correction automatique acceptee**, en connaissance de la
  consequence que j'avais nommee.
- **Rien de ce qui precede n'est efface ni reecrit.** Quatrieme bloc date de ce fichier.
- **Mode** : aucune mesure. Je n'ai pas d'appareil tactile et je le redis ici, parce que ce bloc
  repose sur un raisonnement et non sur un releve - je marque a la section 2.3 l'endroit exact ou
  cela compte.

### 0. Ce que je corrige chez moi avant d'arbitrer, et je l'ai ecrit deux fois aujourd'hui

J'ai ecrit ce matin, puis ce soir, que la correction automatique *"serait franchement grave sur un
paquet en mode strict au titre de `SAI-2` comme les homophones, `couvert-valide` depuis s18"*.
**C'est inexact, et l'inexactitude va dans le sens alarmiste.** Verifie dans
`adapters/adapter-content/src/main/resources/catalogue.yaml` plutot que de memoire : l'entree
`bandit-manchot-homophones-cm1` porte `mecanique: bandit-manchot` - **c'est un jeu a choix**. Or
`SAI-2` ne gouverne que des paquets de **saisie**, et **aucun paquet de saisie n'existe** dans ce
depot.

**Consequence exacte : zero contenu servi aujourd'hui n'est expose a cette defaillance.** Le paquet
d'homophones `couvert-valide` ne l'est pas, ni maintenant ni si la saisie arrive - il restera un jeu
a choix. Ce que cet arbitrage met en cause est un contenu **futur**, et je le traite comme tel. Une
porte pedagogique qui exagere l'urgence use son credit aussi surement qu'une porte qui approuve tout.

### 1. Ce que cet arbitrage rompt, en une phrase

**Sur un des deux appareils vises, l'identite entre *ce que l'enfant a produit* et *ce que le serveur
a recu* n'est plus garantie.**

C'est la seule chose nouvelle, et c'est la bonne facon de trier mes douze conditions : **celles qui
reposaient sur cette identite sont touchees, les autres ne le sont pas.** Je ne les passe pas toutes
en revue par acquit de conscience, je les trie par ce critere.

### 2. `SAI-2`

#### 2.1 Je refuse la troisieme issue - supprimer le mode strict - et le motif est chiffrable

C'est l'issue qui a l'air la plus honnete et c'est la pire. **Sans mode strict, un paquet de saisie
sur les homophones est insoluble sur 100 % des items et sur 100 % des appareils** : `SAI-1` tolere
l'accent par defaut, donc `a` et `a` accentue deviennent la meme chaine, `ou` et `ou` accentue
aussi, et les deux reponses d'un item deviennent litteralement indiscernables. **Avec un mode strict
expose a la correction automatique, l'exercice est corrompu parfois, sur une partie du parc.**

Supprimer le mode strict ne repare donc pas le defaut : **il l'universalise**, et il le rend
certain la ou il n'etait qu'occasionnel. Je ne troque pas une defaillance intermittente contre une
defaillance totale.

#### 2.2 Je refuse aussi la premiere issue - maintenir tel quel et prendre acte

Non pas parce que le motif de `SAI-2` aurait disparu : **il est intact.** `SAI-2` n'a jamais servi a
certifier que l'enfant avait produit l'orthographe ; elle sert a **empecher qu'une tolerance rende
l'exercice insoluble**. Cet arbitrage ne touche pas a cela.

Ce qu'il touche, c'est la **promesse implicite** que `SAI-2` portait sans l'ecrire : qu'un verdict
strict dit quelque chose de l'enfant. Desormais **un verdict strict certifie la chaine recue, pas la
production de l'enfant.** Sur un clavier physique les deux coincident ; sur un clavier virtuel elles
peuvent differer. Laisser cet ecart non ecrit serait exactement la defaillance silencieuse que je
sanctionne ailleurs.

**Ce n'est donc pas le motif de `SAI-2` qui est en cause, c'est sa tenabilite** - la distinction que
l'on me demande de faire, et elle est juste.

#### 2.3 Ce que j'amende, et l'endroit exact ou je raisonne faute de pouvoir mesurer

Le libelle du 2026-08-24 reste ci-dessus. Je le scinde en trois clauses, sur le modele de `SAI-9a` et
`SAI-9b` que j'ai deja employe dans ce fichier.

- **`SAI-2a` - inchangee, c'est le libelle d'origine.** Un paquet dont la competence porte sur
  l'accent declare la stricte egalite, la ou elle est vraie - paquet ou item -, jamais par deduction
  du code. Un paquet qui ne declare rien est en mode tolerant.
- **`SAI-2b` (neuve, 2026-08-25, non retroactive)** - **La stricte egalite n'est declarable que si la
  faute que l'exercice cherche a attraper est elle-meme un mot francais valide.** Si la faute
  attendue est un non-mot, le mode strict promet ce qu'il ne peut pas tenir et le paquet ne doit pas
  etre ecrit.
- **`SAI-2c` (neuve, 2026-08-25)** - **Sur un paquet en mode strict, la chaine jugee est montree a
  l'enfant aussi en cas de reussite**, pas seulement en cas de refus. Extension de `SAI-8` a la
  reussite stricte, dans la meme forme que celle qui y est deja acceptee : *"Oui ! On écrit :
  élève."* devient, quand la chaine recue le merite, une phrase qui la fait voir.

**Le motif de `SAI-2b`, et c'est ici que je raisonne au lieu de mesurer.** Un correcteur de clavier
**repare des non-mots** ; il n'echange pas spontanement deux mots valides l'un pour l'autre, faute
de savoir lequel le contexte demande. `a` et `a` accentue, `ou` et `ou` accentue, `la` et `la`
accentue, `son` et `sont` sont **tous des mots francais existants** : la famille des homophones
grammaticaux, celle pour laquelle `SAI-2` a ete ecrite, passe le test et survit. *eleve* pour
*élève*, *bato* pour *bateau* sont **des non-mots** : c'est exactement ce qu'un clavier repare, et le
mode strict y certifierait le clavier.

**Je ne peux pas verifier ce raisonnement** : aucun agent de cette squad ne dispose de l'appareil,
comme pour l'ecoute au lecteur d'ecran et pour l'enregistrement des sons. **C'est pourquoi j'ajoute
`SAI-2c` plutot que de m'en tenir a `SAI-2b`** : si mon raisonnement est faux et qu'un clavier
accentue tout de meme un `a`, `SAI-2c` fait que **quelqu'un le voit** - l'enfant lit la chaine qui a
ete jugee, et elle n'est pas celle qu'il a tapee. **Une defaillance visible n'est plus la famille de
defaut que je refuse.** C'est la seule protection que je puisse ecrire sans sortir de mon perimetre,
et elle ne coute rien a personne d'autre qu'un texte de retour.

**`SAI-2c` s'adresse a l'enfant, jamais a l'adulte.** L'utilisatrice a tranche que le produit ne
s'adresse jamais a l'adulte accompagnant ; ce que je demande ici est du texte enfant, de la meme
famille que le retour qui existe deja, et il n'y a aucun avertissement adresse a un parent.

### 3. Une ligne du referentiel que cet arbitrage ferme a la saisie - `R6`, neuf, CP

- **`R6` (2026-08-25) - *Ecrire de maniere autonome un mot simple en respectant les correspondances
  grapheme/phoneme connues* (francais CP).** **Refusee a la saisie** tant que les deux appareils sont
  vises avec la correction acceptee.

**Le motif n'est pas neuf, il est a moi et il est anterieur a la question** : j'ai ecrit le
2026-08-24, en fondant `SAI-1`, qu'**"un verdict ne peut pas dependre du materiel de la maison"**.
Ici il en depend entierement. Cette ligne demande a l'enfant de **produire les lettres** ; sur un
clavier physique il les produit, sur un clavier qui corrige c'est l'appareil qui les produit et la
mecanique valide une correspondance grapheme/phoneme que l'enfant n'a pas etablie. Le meme enfant,
avec le meme savoir, reussit ou echoue selon l'appareil.

**Ce que `R6` change a ce que j'ai ecrit ce matin, et je le dis plutot que de le laisser trouver** :
j'ai decrit cette ligne comme demandant *"saisie et audio ensemble"*, donc comme un candidat futur.
**Elle cesse d'etre un candidat.** Ce n'est pas un retour deguise de `SAI-11`, que j'ai levee ce
matin et que je ne rouvre pas : `SAI-11` portait sur l'equipement et la familiarite du clavier, `R6`
porte sur le fait que l'appareil produit la reponse a la place de l'enfant. Motifs distincts, et
`R6` vaudrait meme dans une maison parfaitement equipee.

**`R6` est levable, et je nomme comment pour qu'il ne soit pas eternel** : que la correction
automatique cesse d'etre acceptee, ou que cette ligne ne soit servie que sur un appareil ou elle ne
s'applique pas. **Je ne demande ni l'un ni l'autre** - l'utilisatrice a ecarte l'option de
neutralisation et je ne la reintroduis pas par la bande.

**Aucune autre ligne des quatre referentiels n'est fermee par cet arbitrage**, verifie ligne par
ligne : il n'existe **pas** de ligne d'orthographe lexicale au CM1 - la section "Orthographe et
grammaire" y porte les deux accords, les homophones, la conjugaison et la ligne de reperes ajoutee
par `DEC-0046`, et aucune ne demande a l'enfant de produire l'orthographe d'un mot du lexique.

### 4. Les autres conditions, triees par le critere de la section 1

**Touchees, parce qu'elles reposaient sur l'identite rompue :**

- **`SAI-9a` et `SAI-9b`** - *"le retour montre ce que l'enfant a ecrit"*. Sur un clavier qui
  corrige, **ce n'est plus ce que l'enfant a ecrit, c'est ce que l'appareil a envoye**. Le cas qui me
  gene n'est pas le mot repare en bonne reponse - il n'y a alors pas de refus - mais **le mot repare
  en une autre faute** : l'enfant tape *bato*, l'appareil envoie *baton*, et le retour lui dit *"Tu
  as écrit : bâton"*. Il n'a jamais ecrit cela et il ne comprendra pas. **Je ne change pas le
  libelle** : montrer la chaine recue est tout ce que le serveur peut faire, et le faire reste
  meilleur que ne rien montrer. **J'inscris la limite**, parce que `SAI-9` a ete ecrite pour que
  l'enfant comprenne son erreur et que, dans ce cas precis, elle ne le permet pas.
- **`SAI-6` et `SAI-7`** - les listes de formes acceptees sont calibrees sur *"les fautes previsibles
  de ce mot precis"*. Si l'appareil repare la faute avant l'envoi, **la liste n'est jamais
  sollicitee sur cet appareil et l'est sur l'autre**. Elle reste juste ; sa couverture devient
  inegale. Aucun changement de libelle, mais un piege de lecture que je nomme : **des items qui
  passent ne prouveront plus que la liste est bonne.**
- **`SAI-12`** - la liste de ce que l'enfant a deja ecrit sur la manche montre des chaines
  d'appareil. Meme limite que `SAI-9a`, meme decision : libelle inchange.
- **`SEUIL-2`** - *"si une reponse juste refusee pour une question de forme coute un essai, la saisie
  ne doit pas exister"*. Une correction automatique peut defaire une reponse **juste**. C'est rare et
  c'est exactement le scenario de `SEUIL-2`. Le seuil n'est pas franchi - `SAI-1`, `SAI-3` et `SAI-4`
  absorbent l'ecrasante majorite des cas - mais la cause est desormais double.

**Non touchees, et je dis pourquoi plutot que de les enumerer :**

- **`SAI-1` (accents), `SAI-3` (casse), `SAI-4` (espaces)** : ce sont des **tolerances**. Elles
  elargissent l'ensemble des reponses acceptees ; une correction automatique ne peut que produire une
  chaine deja dans cet ensemble ou une chaine plus proche de la reference. **Elles n'ont rien a
  perdre a cet arbitrage.** `SAI-3` y gagne meme : la capitale initiale posee par l'appareil, que je
  notais ce matin comme une hypothese, est desormais un **fait acquis du produit**, et son motif
  cesse d'etre conjectural.
- **`SAI-5`** (aucun appariement approximatif), **`SAI-8`** (montrer la forme de reference),
  **`SAI-10`** (jamais la couleur seule), **`SAI-11`** (levee ce matin) : aucune ne suppose que la
  chaine recue soit celle de l'enfant.
- **`SEUIL-1`** - *"si l'enfant ne voit pas ce qu'il a ecrit au moment du verdict"*. **Il n'est pas
  franchi et je le dis explicitement pour que personne ne l'invoque** : `SEUIL-1` exige que l'enfant
  ait sous les yeux une chaine a corriger, pas que cette chaine soit prouvee sienne. Il l'a.
- **`SEUIL-3`, `SEUIL-4`, `SEUIL-5`**, **`R1` a `R5`** : inchanges.

### 5. Ma reponse differe-t-elle entre CP et CM1 ? Oui, sur un seul point, et pas celui qu'on attend

**Elle differe sur `R6`, et seulement sur `R6` :** la ligne d'ecriture autonome de mots n'existe
qu'au CP. Il n'y a **aucune ligne equivalente au CM1**, verifie ci-dessus. `SAI-2b` et `SAI-2c`
valent identiquement aux deux niveaux.

**Et elle ne differe pas la ou on pourrait le croire.** On pourrait penser le CP mieux protege,
puisque l'utilisatrice a arbitre ce matin qu'un eleve de CP est toujours accompagne : un adulte
present verrait la chaine affichee par `SAI-2c` et remarquerait que l'appareil a corrige. **Je
refuse de compter cela comme une protection.** Le produit ne s'adresse jamais a l'adulte - c'est
l'arbitrage de ce jour - donc rien ne lui demande de regarder, et j'ai deja ecrit deux fois qu'une
propriete tenue par personne n'est pas tenue. La presence de l'adulte est un fait, pas un
garde-fou. **L'exposition reelle est donc la meme aux deux niveaux.**

### 6. Ce que je n'ai pas fait, et que je nomme pour qu'on puisse le verifier

**Je n'ai demande aucune neutralisation technique, ni directement ni sous un autre nom.** Aucune de
`SAI-2b`, `SAI-2c` et `R6` ne suppose qu'un attribut desactive la correction, ni qu'un champ soit
configure d'une facon plutot qu'une autre. `SAI-2b` est une regle d'**ecriture de contenu**,
`SAI-2c` une regle de **texte affiche a l'enfant**, `R6` un **refus de ligne**. L'utilisatrice a
ecarte l'option de neutralisation ; je ne la reintroduis pas et je ne la regrette pas par ecrit.

**Je prends acte du troisieme arbitrage et il ferme un de mes points.** J'ai route deux fois
aujourd'hui, sans le trancher, le fait que le produit repose sur un adulte a qui il ne demande rien.
La reponse est que le produit ne s'adressera jamais a lui. **Ce point est clos, je le retire de mes
routages**, et il contraint ma propre solution ci-dessus - ce que `SAI-2c` fait voir, elle le fait
voir a l'enfant.

### 7. Sur quelles proprietes je reste la seule garde, apres cet amendement

- **`SAI-2b`** - qu'une faute attendue soit ou non un mot valide se juge **mot par mot, en francais**.
  Aucun test ne le dira, et un paquet qui se trompe la-dessus aura une CI verte.
- **`SAI-6` et `SAI-7`**, aggravees : le bareme etait deja le point le plus dangereux de cette
  mecanique, et **des items qui passent ne prouvent plus que la liste est bonne**. Ma relecture des
  listes n'est plus seulement necessaire, elle est desormais la seule chose qui les eprouve.
- **`SAI-2c`** - qu'un enfant de six ou neuf ans comprenne la chaine qu'on lui montre. Un test dira
  qu'elle est affichee, jamais qu'elle est lue.
- **`R6`** - rien ne l'empeche d'etre viole. Le jour ou une story ouvrira cette ligne en saisie, la
  CI sera verte et je serai la seule a dire non.
- **Le comportement reel d'un clavier virtuel** - inchange depuis ce matin, sauf que ce n'est plus
  une question ouverte mais un **fait accepte** dont je ne connais toujours pas l'ampleur. C'est
  desormais un angle mort assume du produit, et il est ecrit.
