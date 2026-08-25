# Arbitrage prealable s19 - les quatre questions de validite de proxy de `DEC-0030`, six sprints apres

- **Auteur** : nadia-prof. **Date** : 2026-08-24. **Sprint** : s19.
- **Branche** : `s19-nadia`, worktree dedie, lignee verifiee par `outils/verifier-lignee.sh`.
- **Ce que ce document est** : ma reponse aux **quatre questions de validite de proxy** posees par
  `DEC-0030` section 5, et detaillees en section 4 de la meme decision. Chacune recoit un verdict
  unique - accepte, accepte sous conditions numerotees, ou refuse - et, quand je refuse, ce qui
  debloquerait la ligne.
- **Ce que ce document n'est pas** : ni un Done pedagogique, ni un jugement sur un increment. Rien
  n'est livre sur ces quatre lignes, aucune n'a de fiche de conformite. **Un paquet qui tiendrait
  toutes mes conditions peut encore etre refuse pour ce que seul le rendu montre.**
- **Mode d'audit** : je n'ai rien joue, et ici c'est legitime, pas degrade - il n'existe aucun
  increment sur ces quatre lignes. J'ai lu les quatre referentiels de `squad/programme/`, la
  matrice, `DEC-0002`, `DEC-0019`, `DEC-0029`, `DEC-0030`, `DEC-0039`, mes propres arbitrages de
  s12, s13 et s17, et les fichiers de `contenu-repris/` qui portent de la matiere sur ces lignes.
  Une seule sous-question exige une mesure que je n'ai pas faite ; je la nomme a l'endroit ou elle
  tombe (section 6) plutot que de la trancher a l'aveugle.

## 0. Ce que je verifie et ce que je corrige avant de repondre

### 0.1 Le constat de depart est exact

Verifie fichier par fichier :

- `squad/conformite/AVIS-PRIORITE-PEDAGOGIQUE-s13-seconde-mecanique.md` est le seul fichier ne de la
  section 5 de `DEC-0030`, et il repond a la **sixieme** demande, pas aux quatre premieres. Je l'y
  ecris moi-meme en clair : *"les quatre questions de validite de proxy de `DEC-0030` section 4 ne
  sont pas traitees par ce fichier et restent dues"*.
- Les quatre lignes sont toujours `non couvert` dans `squad/programme/MATRICE-couverture.md`
  (mathematiques CP denombrement, mathematiques CP figures planes, francais CP decodage, francais
  CM1 relecture). Aucune n'a bouge depuis.
- `DEC-0030` engage `camille-po` a ne les ouvrir sous aucune forme avant ma reponse. **Mon silence
  est donc bien un verrou reel, et il a tenu six clotures - s13, s14, s15, s16, s17, s18.** Je ne
  cherche pas d'excuse a ce delai ; je le nomme, parce qu'une porte pedagogique qui ne repond pas
  est aussi nuisible qu'une porte qui approuve tout.

### 0.2 Deux erreurs dans l'enonce qui m'a ete transmis, corrigees ici

**Erreur 1 - l'intitule de la quatrieme ligne.** La demande qui m'est faite cite *"Relire et corriger
son texte a l'aide de reperes simples (majuscules, ponctuation, **accords**)"*. Le referentiel
`squad/programme/francais/competences-cm1.md` ecrit, mot pour mot :

> *"Relire et corriger son texte a l'aide de reperes simples (majuscules, ponctuation)."*

**Il n'y a pas d'accords dans cette ligne**, et `DEC-0030` ne les y met pas non plus. Ce n'est pas un
detail : les accords ont **deux lignes a eux** dans le meme referentiel ("Accorder le verbe avec son
sujet", "Accorder en genre et en nombre dans le groupe nominal"), toutes deux `non couvert` et toutes
deux servables par la mecanique actuelle sans aucun arbitrage de moi. Les faire entrer dans la ligne
de relecture ferait couvrir trois lignes par un paquet qui n'en travaille aucune entierement -
exactement le motif de couverture partielle que ce depot sanctionne depuis s01. Je reponds sur la
ligne telle que le referentiel l'ecrit.

**Erreur 2 - "un obstacle qui n'est pas technique", vrai pour trois lignes sur quatre.** Il l'etait
pour les quatre au moment ou `DEC-0030` a pose les questions. Il ne l'est plus pour la quatrieme :
`DEC-0039` (s17) a reclasse *"Relire et corriger son texte"* dans la famille de la **production
d'ecriture libre**, avec un routage explicite a `theo-archi`, et `squad/programme/MATRICE-couverture.md`
porte cette note. Depuis s17, cette ligne est donc bloquee par un obstacle de mecanique **en plus**
de l'etre par mon silence. Cela ne me dispense pas de repondre - ma reponse en section 4 va plus loin
que le reclassement de `DEC-0039` - mais le constat merite d'etre exact.

### 0.3 Une correction a une de mes propres habitudes de lecture

Je m'appretais a opposer ma condition **C5 clause 1** (2026-08-11,
`FICHE-bandit-manchot-additions-cp.md`) aux questions 1 et 2, puisqu'elle interdit *"aucune image,
aucun pictogramme, aucun aplat decoratif a l'interieur d'une boite de proposition, ni derriere son
texte"*. **Je ne le fais pas, et je dis pourquoi, parce que m'en servir ici serait deplacer mes
poteaux.**

C5 clause 1 a un motif ecrit et une verification ecrite. Son motif est le raccourci visuel : *"un
enfant de CP qui a le choix entre dechiffrer `an` et regarder un dessin regarde le dessin"*. Sa
verification porte sur l'espace libre autour du mot le plus large et sur ce qui **s'y superpose**.
Elle vise donc un element **ajoute** dans la boite, a cote ou derriere le texte de la proposition -
pas le texte de la proposition lui-meme. Un pictogramme-caractere **est** le texte de la
proposition : c'est la chaine que le YAML ecrit et que les tests de contenu lisent, ce lien que j'ai
moi-meme pose comme decisif le 2026-08-16 en refusant le jeton en image.

**Clarification datee du 2026-08-24, et je l'ecris comme une clarification, pas comme un
assouplissement** : C5 clause 1 ne mord pas sur un pictogramme-caractere qui constitue le texte
entier d'une proposition ou d'un enonce. Elle mord toujours, sans changement, sur toute image, tout
aplat et tout pictogramme **ajoute** a cote ou derriere ce texte. Mes refus ci-dessous ne s'appuient
donc jamais sur C5 : ils s'appuient sur la competence.

---

## 1. Denombrer, lire, ecrire et decomposer les nombres jusqu'a 100 (mathematiques CP)

### Verdict : **REFUSE sur "denombrer" - ACCEPTE SOUS CONDITIONS sur "lire, ecrire, decomposer", en couverture partielle nommee**

`DEC-0019` avait refuse d'ouvrir "lire, ecrire, decomposer" sans "denombrer", au motif juste qu'une
couverture partielle presentee comme la ligne entiere est un mensonge de matrice. Ce motif reste
entier. Ce que j'ajoute, et qui n'existait pas quand il a ete ecrit, c'est le precedent que j'ai pose
moi-meme a s12 sur les fractions : **une moitie de ligne peut ouvrir seule si elle est nommee comme
telle et si la ligne reste `en cours`.** C'est ce que je fais ici.

### 1.1 Pourquoi "denombrer" n'est pas servi par un pictogramme-caractere

Trois motifs cumules. Le premier suffirait ; les trois ensemble ne laissent pas de place au doute.

**a. Le groupement par dix n'est pas controlable dans une chaine de texte, et c'est tout le sujet de
cette ligne.** Denombrer jusqu'a 100 au CP, ce n'est pas compter un a un jusqu'a 47 - c'est
apprendre a faire des paquets de dix et a compter les paquets. Le verbe "decomposer" de la meme
ligne est exactement la trace de ce travail. Or une chaine de glyphes repetes est rendue dans un
paragraphe et **retourne a la ligne la ou la largeur de l'ecran le decide**. Un paquet de dix qui se
coupe en 7 + 3 sur une tablette et en 6 + 4 sur un ordinateur ne montre pas dix : il montre l'inverse
de ce que la competence demande d'installer. Aucun contenu ne peut garantir ce groupement, et aucun
test de ce depot ne le verrait - les tests lisent la chaine, pas la ligne rendue.

**b. Le plafond reel de comptage a l'ecran est autour de dix objets, sur une ligne qui va a cent.**
Un enfant de CP qui compte pointe du doigt et marque ce qu'il a deja compte. Devant un bloc de trente
glyphes qu'il ne peut ni deplacer, ni barrer, ni entourer, il perd son compte et le recommence. Ce
n'est pas une supposition : c'est la raison pour laquelle on donne des jetons a manipuler et pas une
feuille imprimee. Un support qui ne sert que la tranche 1-10 d'une ligne qui va jusqu'a 100 ne couvre
pas cette ligne, meme partiellement.

**c. Une erreur de rendu devient une reponse fausse.** Je reprends ici, sans la modifier, la reserve
que `DEC-0019` m'a routee le 2026-08-12 et qui n'a jamais recu de reponse. Un glyphe absent de la
police s'affiche en carre vide ; un glyphe rendu par une pile de composition differente peut occuper
une largeur double. Sur un contenu ou l'enfant doit **compter** les glyphes, cela ne produit pas un
defaut d'apparence, cela produit un item dont la bonne reponse est fausse a l'ecran.

La matiere premiere du depot le montre deja. `contenu-repris/memory/memory-quantite-cp.yml` associe
les six faces de de aux chiffres 1 a 6. C'est du **bon materiel de CP** - les constellations du de
sont un support classique et je ne le denigre pas - mais ce n'est pas du denombrement : une face de
de se **reconnait d'un coup d'oeil** sans etre comptee, c'est meme son interet, et elle s'arrete a
six.

### 1.2 Ce que j'accepte, et sous quelles conditions

`camille-po` peut ouvrir un paquet sur **"lire, ecrire et decomposer les nombres jusqu'a 100"**, par
la mecanique actuelle et sans aucun pictogramme, aux conditions suivantes.

- **DEN-1 - Aucun item du paquet ne demande a l'enfant de compter une quantite affichee.** Ni glyphe
  repete, ni face de de, ni suite de caracteres a denombrer, dans l'enonce comme dans les
  propositions. Un seul item de ce type et je refuse le paquet entier, sans discussion sur les
  autres.
- **DEN-2 - La ligne reste `en cours` dans la matrice, jamais `couvert-valide`, meme si je valide le
  paquet.** La note de matrice porte, en toutes lettres et sous ma signature : *"le verbe
  'denombrer', premier de cette ligne, n'est travaille par aucun jeu ; seuls 'lire', 'ecrire' et
  'decomposer' le sont"*. Meme forme que la note "comparer/ranger" et que la note des fractions.
  C'est la contrepartie de mon acceptation et elle n'est pas negociable.
- **DEN-3 - "Lire" et "ecrire" sont travailles dans les deux sens, dans le meme paquet.** Chiffres
  vers lettres (lire) et lettres vers chiffres (ecrire). Un paquet a sens unique ne travaille qu'un
  des deux verbes et retombe sous DEN-2 avec une note plus large. Reconduction de **GN-1** et
  **GN-2** (2026-08-14, `ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`) : les distracteurs se
  distinguent par la **valeur designee**, jamais par l'orthographe des mots-nombres, et la
  designation litterale de l'enonce est ecrite sans faute et de facon constante dans tout le paquet.
- **DEN-4 - "Decomposer" a des items a lui, et c'est en dizaines et unites.** Au CP, decomposer 47,
  c'est dire *"4 dizaines et 7 unites"*, pas seulement *"40 + 7"*. Les deux ecritures sont
  acceptables dans le paquet, mais **au moins la moitie des items de decomposition emploie le
  vocabulaire dizaines/unites**, parce que c'est celui de la classe et celui qui prepare le CE1. Je
  n'accepte pas que "decomposer" soit tenu pour couvert par les seuls items de transcription : ce
  raccourci est celui que j'ai accorde au CM1 sur les grands nombres, ou lire "trois cent cinq mille"
  oblige a decouper en classes, et il ne se transporte pas ici.
- **DEN-5 - Le mot "decomposer" n'apparait jamais dans ce que l'enfant lit.** C'est un mot de maitre.
  La consigne dit *"Combien y a-t-il de dizaines ?"* ou *"Choisis la bonne ecriture"*. Ma regle de
  toujours : chaque mot de consigne se paie au CP, et un mot que l'enfant doit d'abord comprendre
  avant de comprendre la tache est un mot de trop.
- **DEN-6 - La tranche 60-99 est representee, et elle ne peut pas etre traitee a part.** La
  difficulte francaise de cette ligne est entierement la : *soixante-douze*, *quatre-vingts*,
  *quatre-vingt-dix-sept*. Un paquet qui s'arrete a 69, ou qui ne met des nombres de cette tranche
  qu'en distracteurs, esquive la seule chose qui soit difficile. **Au moins un tiers des items porte
  sur un nombre entre 60 et 99**, en position de bonne reponse.
- **DEN-7 - Les irreguliers et les dizaines rondes ne sont pas oublies.** Au moins un nombre entre 11
  et 16 (*onze*, *douze*, jusqu'a *seize*), et au moins un multiple de dix ecrit sans unite
  (*soixante*, *quatre-vingts*). Ce sont les items ou un enfant qui recite sans comprendre se trompe,
  donc ceux qui separent les deux.
- **DEN-8 - Aucun item n'est resoluble sans lire le nombre.** Concretement : les propositions d'un
  meme item ont le meme nombre de chiffres, ou une ecriture litterale de longueur comparable a un mot
  pres. Un item dont la bonne reponse est la seule proposition a deux chiffres est une devinette.
  C'est le critere qui m'a fait refuser "Le bon son" et je l'applique partout.

### 1.3 Ce qui debloquerait "denombrer", pour que `camille-po` sache ou router la moitie restante

**Famille visuelle, avec une interaction, et pas seulement un dessin.** Le denombrement demande que
l'enfant puisse **agir sur la collection** : deplacer un objet dans une boite, le marquer comme
compte, ou grouper par dix. Un simple rendu graphique de trente ronds, meme parfait, reproduit le
motif b ci-dessus.

**Si aucune interaction n'est envisageable**, voici le minimum en dessous duquel je ne validerai pas,
ecrit maintenant pour qu'aucun lot ne soit dessine contre : une collection **dessinee** (pas un
glyphe de police), dont le contenu **declare les groupes de dix** et dont le rendu les tient a toute
largeur d'ecran, avec un plafond d'objets isoles hors groupes qui ne depasse pas dix.

---

## 2. Reconnaitre et nommer quelques figures planes (carre, rectangle, triangle, cercle) (mathematiques CP)

### Verdict : **REFUSE**

Un glyphe geometrique Unicode ne sert pas "reconnaitre une figure plane". Trois motifs, dont le
premier est deja prouve par la matiere premiere du depot.

### 2.1 Carre contre rectangle est la discrimination que cette ligne demande, et c'est celle qu'un glyphe ne peut pas porter

La ligne nomme quatre figures et, sur ces quatre, une seule paire demande un vrai jugement au CP :
carre contre rectangle. La difference tient a **une propriete unique** - les quatre cotes sont-ils
egaux. Or les proportions d'un glyphe sont celles de la police, pas celles du contenu. Un glyphe de
rectangle rendu presque carre, un glyphe de carre rendu legerement oblong, et la bonne reponse est
fausse a l'ecran. Le contenu ne peut ni le declarer ni le verifier, et aucun test de ce depot ne lit
un rapport de largeur sur hauteur.

**Ce n'est pas une crainte, c'est deja arrive dans ce depot.** `contenu-repris/associer/cp-formes.yml`
associe a la cible `"rectangle"` le glyphe `LARGE ORANGE SQUARE` - un **carre**. Le seul fichier de
matiere premiere que ce depot possede sur cette competence enseigne donc qu'un carre s'appelle un
rectangle, et il l'enseigne exactement sur la paire que la ligne existe pour faire distinguer. Une
erreur factuelle dans un contenu scolaire est chez moi un motif de refus a elle seule ; ici elle est
en plus la demonstration du motif general.

### 2.2 La couleur imposee par le glyphe rend l'exercice soluble sans la competence

Le meme fichier le montre : le triangle y est rouge, le carre bleu, le cercle bleu, le pretendu
rectangle orange. `DEC-0002`, amendement du 2026-08-04, pose deja qu'aucun item ne doit faire de la
couleur un critere de tri et que la consigne ne doit jamais la mentionner. **Avec des pictogrammes de
forme, la couleur n'est pas un choix du contenu : elle est imposee, et elle differe d'une forme a
l'autre.** Au troisieme item, un enfant apparie "le bleu carre" au mot *carre* sans plus jamais
regarder la figure. C'est mot pour mot le defaut qui m'a fait refuser "Le bon son" - un exercice
reussi sans exercer la competence annoncee.

Les glyphes geometriques monochromes evitent la couleur mais pas le motif 2.1, et ils en ajoutent un :
ils sont **un par forme**. Un seul triangle, toujours le meme, toujours pointe vers le haut, installe
un prototype faux - et c'est `DEC-0002` qui l'ecrit, en creditant a raison la presence de deux
triangles d'orientations opposees dans le meme fichier comme une **qualite** : *"la competence
demande justement d'abstraire l'orientation, la taille et la couleur"*. Un glyphe unique par forme
fait donc l'inverse exact de ce que la competence demande.

### 2.3 La matiere premiere textuelle disponible ne travaille ni "reconnaitre" ni "nommer"

`contenu-repris/quiz/cp-geometrie.yml` est du contenu textuel pur, sans aucun glyphe, et il est
tentant de croire qu'il ouvre la ligne sans mecanique neuve. Il ne l'ouvre pas : *"Combien de cotes a
un triangle ?"*, *"Un carre a des cotes... tous egaux"* travaillent la **connaissance des proprietes
d'une figure nommee**. C'est utile et c'est du programme, mais ce n'est ni reconnaitre une figure
montree, ni la nommer. Un enfant peut repondre juste a ces huit questions sans avoir jamais vu une
figure.

Je signale au passage, puisque j'ai lu le fichier, deux defauts qui vaudraient refus si ce paquet
arrivait tel quel : il emploie *"le cercle"* et *"le disque"* comme deux bonnes reponses a deux
questions voisines du meme paquet, ce qui installe une confusion au lieu de la lever ; et son dernier
item porte sur les faces d'un **cube**, un solide, qui n'appartient pas a cette ligne.

### 2.4 Ce qui debloquerait la ligne

**Famille visuelle, avec un rendu dessine dont le contenu declare les dimensions.** Le minimum en
dessous duquel je ne validerai pas, ecrit maintenant :

- **FIG-1** - chaque figure est **dessinee**, et ses proportions sont declarees par le contenu, pas
  par une police. Un rectangle est un rectangle a toute taille d'ecran, verifiable autrement que par
  mon oeil.
- **FIG-2** - **au moins trois exemples differents par forme** dans le paquet, variant l'orientation,
  la taille et les proportions. Un carre pose sur la pointe reste un carre : c'est precisement ce
  qu'un CP doit apprendre, et un paquet a un exemplaire par forme enseigne le contraire.
- **FIG-3** - la couleur ne distingue jamais deux formes entre elles a l'interieur d'un meme item, et
  aucune consigne ne la mentionne. Reconduction de `DEC-0002`, amendement du 2026-08-04, sans
  changement.
- **FIG-4** - le paquet ne confond ni "cercle" et "disque", ni figure plane et solide.

**Consequence de comptage pour `camille-po`** : cette ligne quitte la famille "contenu seul" et
rejoint la famille **visuelle**, dont elle est desormais le cas le plus simple - une figure fixe,
sans aiguille ni graduation.

---

## 3. Decoder un mot regulier inconnu par assemblage syllabique (francais CP)

### Verdict : **REFUSE**

Et le refus va plus loin que la question posee : **ce n'est pas le nombre de propositions qui ne va
pas, c'est qu'aucun choix parmi des propositions ecrites ne peut porter cette ligne.**

### 3.1 Le proxy propose teste l'operation inverse de celle que la ligne nomme

Choisir entre *cha-peau*, *chap-eau* et *c-hapeau*, c'est reconnaitre une **segmentation**. La ligne
dit **assemblage** : partir de syllabes et produire le mot. Ce sont deux operations opposees, et
elles ne s'apprennent pas dans le meme sens. Pire, la segmentation en syllabes ecrites est un
exercice **metalinguistique** qui vient apres la lecture : on demande a l'enfant de raisonner sur la
regle de coupe, ce qui suppose qu'il sache deja lire le mot.

Consequence directe, et c'est mon critere habituel applique en miroir : **cet exercice ne peut etre
reussi que par un enfant qui possede deja la competence**. Celui qui sait lire *chapeau* choisit la
bonne coupe ; celui qui ne sait pas le lire ne peut rien choisir du tout. Un exercice reussi
uniquement par ceux qui n'en ont pas besoin est un test de tri, pas un apprentissage.

### 3.2 Le mot doit rester inconnu, et un choix parmi des propositions ecrites le montre toujours

La ligne dit *"un mot regulier **inconnu**"*. Toute la valeur de cette competence est la : c'est le
seul outil qui permet a un enfant de lire un mot qu'il n'a jamais vu, et c'est ce qui separe un
lecteur d'un enfant qui reconnait des silhouettes de mots apprises par coeur. Or un choix parmi
propositions ecrites **affiche le mot**, entier ou en morceaux, avant que l'enfant ne reponde. A
l'instant du choix, le mot n'est plus inconnu. Le defaut est structurel, pas reparable par une regle
d'ecriture des items.

### 3.3 Un choix silencieux ne distingue pas un enfant qui decode d'un enfant qui reconnait

Decoder, c'est convertir des lettres en sons et enchainer ces sons. Cela se verifie en **entendant**
l'enfant. Un appui sur un bouton ne dit pas par quel chemin il est arrive : le dechiffrage, ou la
reconnaissance globale du mot - qui est exactement ce que l'enseignement du code existe pour
remplacer. Je suis dans la meme situation qu'a s02 sur "Le bon son", ou 23 items sur 24 se
resolvaient par appariement de lettres sans jamais passer par le son.

### 3.4 Ce qui debloquerait la ligne, et c'est moins cher que ce que `DEC-0030` suppose

**Cette ligne n'a pas besoin de reconnaissance vocale. Elle a besoin d'emission de son, et de rien
d'autre.** Le format qui la sert reellement :

- **SYL-1** - le mot ecrit est affiche, et l'enfant ne l'a jamais rencontre : mot rare regulier, ou
  pseudo-mot regulier, qui est le materiel de reference pour evaluer le dechiffrage ;
- **SYL-2** - les propositions sont **entendues, pas lues** : trois enregistrements, dont un seul
  correspond a ce que les lettres affichees produisent. Les distracteurs different d'un seul phoneme
  ou d'un ordre de syllabes, jamais d'un mot entier sans rapport ;
- **SYL-3** - chaque proposition est **reecoutable autant de fois que l'enfant le veut**, et la
  reecoute ne coute pas un essai. Reconduction de mon seuil audio du 2026-08-15, sans changement ;
- **SYL-4** - aucune manche n'est chronometree, et rien d'essentiel ailleurs dans le catalogue ne
  passe par le son seul. Meme reconduction.

Un enfant qui doit dire lequel des trois sons correspond aux lettres affichees **convertit reellement
des lettres en sons**. Le mot reste inconnu a l'oeil, puisque ce qu'il doit produire est un son, et
il n'y a pas de silhouette a reconnaitre dans un enregistrement.

**Consequence de comptage pour `camille-po`** : cette ligne quitte la famille "contenu seul" et
rejoint la famille **audio**, qui passe de trois lignes a quatre. Elle ne rejoint pas la
reconnaissance vocale, que `theo-archi` a declaree hors d'atteinte et que je n'ai jamais reclamee.
Cela renforce l'ordre que j'ai donne le 2026-08-15 - audio, puis visuel, puis saisie - mais je le
signale comme une consequence, pas comme un argument neuf : je ne reouvre pas cet avis.

---

## 4. Relire et corriger son texte a l'aide de reperes simples (majuscules, ponctuation) (francais CM1)

### Verdict : **REFUSE - et je refuse aussi la solution de rechange que la question suppose**

La question posee est : *le possessif deplace-t-il cette competence vers la saisie ?* **Ma reponse est
non, et c'est la partie utile de ce verdict** : la saisie ne la debloque pas davantage qu'un choix.

### 4.1 Un choix entre plusieurs versions d'une phrase ne couvre pas la ligne

**La difficulte que cette competence nomme est une difficulte de relecture de soi, et elle n'existe
que sur son propre texte.** Un enfant qui relit ce qu'il vient d'ecrire lit ce qu'il **voulait**
ecrire, pas ce qu'il a ecrit. Cette cecite-la est tout le sujet : la relecture s'enseigne en donnant a
l'enfant des points de controle mecaniques - majuscule au debut, point a la fin, majuscule aux noms
propres - justement parce que la lecture spontanee de son propre texte ne les voit pas. Sur la phrase
d'un autre, cette cecite disparait, et avec elle la seule chose difficile.

Un choix retire une seconde chose : dans une vraie relecture, l'enfant **ne sait pas combien d'erreurs
il y a, ni s'il y en a**. Un choix parmi des versions garantit qu'il y en a exactement une de bonne et
qu'elle est a l'ecran.

### 4.2 La saisie ne la debloque pas non plus

Deux verrous, et le second est le plus dur.

- **Il faut d'abord que l'enfant ait produit un texte.** Ce texte est l'objet d'une **autre** ligne du
  meme referentiel - *"Rediger un texte narratif court et coherent"*. Cette ligne ne peut donc jamais
  etre couverte seule : elle est suspendue a une ligne qui l'est encore moins.
- **Il faut ensuite juger la correction.** Aucune comparaison de chaines ne dit si un enfant a
  correctement relu un texte libre qu'il vient d'ecrire. Ce jugement est celui d'un adulte qui lit,
  comme la fluence de lecture est celle d'un adulte qui ecoute - et j'ai deja ecrit, le 2026-08-15,
  que je ne reclamerais pas cette ligne-la pour la meme raison.

### 4.3 Ce que je recommande, plutot qu'une attente indefinie

**Cette ligne, telle que le referentiel l'ecrit, n'est pas couvrable par ce produit.** Je le dis
maintenant, franchement, pour qu'elle cesse de figurer dans une famille - "contenu seul" chez
`DEC-0030`, "production libre routee a `theo-archi`" chez `DEC-0039` - qui laisse croire qu'une
mecanique la debloquera un jour. Elle rejoint la meme categorie que *"Lire a voix haute un texte court
avec fluidite"* : **hors de portee, et pas pour une question de sprints.**

Ce que je route, sans le trancher :

- a **`camille-po`, via `sofia-sm`** : l'utile de cette ligne, ce sont les **reperes** eux-memes -
  savoir qu'une phrase commence par une majuscule et finit par un point, qu'un nom propre prend une
  majuscule, qu'un point d'interrogation clot une question. Cette connaissance-la est servable
  aujourd'hui par la mecanique actuelle, et bien. Mais elle n'est pas cette ligne, et je refuse qu'un
  paquet qui la travaille soit compte pour elle. Si l'utilisatrice veut la voir couverte, ce n'est pas
  un arbitrage de mecanique qu'il faut, c'est un **amendement du referentiel**, qui n'est ni mon
  fichier ni ma decision. Les referentiels de `squad/programme/` sont un socle de depart declare non
  exhaustif et non officiel ; c'est le bon moment de le rappeler.
- **ce qui est disponible tout de suite a la place** : deux lignes voisines - *"Accorder le verbe avec
  son sujet"* et *"Accorder en genre et en nombre dans le groupe nominal"* - sont `non couvert`,
  servables par la mecanique d'aujourd'hui, et n'attendent **aucun** arbitrage de moi. Si l'objectif
  est d'avancer sur l'orthographe grammaticale du CM1, elles le permettent et cette ligne-ci non.

---

## 5. Recapitulatif, et ce que cela fait au decompte de `DEC-0030` section 4

| Question | Ligne | Verdict | Famille apres arbitrage |
|---|---|---|---|
| 1 | Denombrer, lire, ecrire, decomposer jusqu'a 100 (math CP) | **partielle** : refus sur "denombrer", acceptation sous DEN-1 a DEN-8 sur les trois autres verbes | contenu seul (moitie) + visuel (moitie) |
| 2 | Reconnaitre et nommer des figures planes (math CP) | **REFUSE** | visuel |
| 3 | Decoder un mot regulier inconnu par assemblage syllabique (fr CP) | **REFUSE** | audio, emission seule |
| 4 | Relire et corriger son texte (fr CM1) | **REFUSE**, et la saisie ne la debloque pas | hors de portee |

**Le decompte de `DEC-0030` section 4, reexprime.** Je pars de son tableau - 19 entieres plus 1
partielle en contenu seul, 6 plus 1 partielle en visuel, 5 en saisie, 3 en audio, 1 a part, soit 35
lignes `non couvert` a s13 - et je n'y applique que mes quatre verdicts :

| Besoin mecanique | `DEC-0030` (s13) | Apres cet arbitrage |
|---|---|---|
| Contenu seul | 19 entieres + 1 partielle | **15 entieres + 2 partielles** |
| Visuel | 6 entieres + 1 partielle | **7 entieres + 2 partielles** |
| Saisie | 5 | **5**, inchange |
| Audio (emission) | 3 | **4** |
| Hors de portee | 1 | **2** |

Le total reste 35 lignes, dont deux comptees en deux moities - les fractions et le denombrement.
**Je compte sur la base de s13 pour rester comparable a sa table** ; la matrice en est a 31
`non couvert` aujourd'hui, et c'est a `camille-po` de reporter ces reclassements sur son propre etat,
pas a moi.

**Ce que ce recapitulatif change de reel** : trois lignes sur quatre sortent du bucket que
`camille-po` croyait ouvrable sans mecanique neuve. **La reserve de contenu seul est donc plus courte
qu'elle ne l'estimait** - elle parlait de quatre a six sprints de marge, et ce chiffre est a reprendre
par elle. Je ne le refais pas a sa place, mais je ne veux pas qu'il continue a circuler sans que ma
reponse y soit entree.

## 6. Sur quelles proprietes je reste la seule garde

Aucune des conditions de ce document n'est aujourd'hui tenue par un test. Concretement, si un paquet
est ecrit sur ces bases :

- **DEN-1, DEN-4, DEN-6, DEN-7, DEN-8** portent sur la composition d'un paquet - la presence d'un
  glyphe a compter, la part d'items en dizaines/unites, la part de la tranche 60-99, la comparabilite
  des propositions. **Rien dans ce depot ne les verifie.** Un paquet integralement fautif sur les cinq
  passerait une CI verte. C'est exactement la famille de defaut qui a traverse une CI verte quatre
  fois sur ce produit, et je serai la seule a la voir tant qu'aucun garde-fou n'existe.
- **DEN-2** est une note de matrice ecrite a la main. Rien ne l'impose ; c'est `camille-po` qui la
  porte, et moi qui verifierai qu'elle y est avant tout Done.
- **FIG-1** - les proportions declarees par le contenu - est le seul point de ce document qui pourrait
  devenir une regle executable, et il ne me revient pas de dire comment. Je le nomme a `theo-archi` le
  jour ou la famille visuelle ouvrira, pas avant.
- **La mesure que je n'ai pas faite** : je n'ai pas mesure le rendu reel d'un glyphe geometrique ou
  d'une face de de sur les deux cibles de `DEC-0040`. Mes refus des sections 1 et 2 ne reposent pas
  sur cette mesure - ils reposent sur la competence, et ils tiennent sans elle. Mais si quelqu'un veut
  un jour rouvrir le pictogramme-caractere sur un autre usage que le denombrement ou les figures,
  cette mesure sera due, et elle n'existe pas.

## 7. Suites hors de ma main

- **`camille-po`** : quatre reponses, dont une acceptation immediatement actionnable (section 1.2,
  DEN-1 a DEN-8). Trois reclassements de famille (section 5) et une reserve de contenu seul a
  reestimer. Une demande d'amendement du referentiel a instruire avec l'utilisatrice (section 4.3),
  que je ne fais pas moi-meme. Rien de ce document n'ouvre ni ne ferme un Done.
- **`sofia-sm`** : les deux lignes d'accords du francais CM1 sont ouvrables sans arbitrage de moi
  (section 4.3). C'est le seul point de ce document qui debloque de la capacite immediatement.
- **`theo-archi`** : rien ne lui est demande ici. FIG-1 lui reviendra le jour ou la famille visuelle
  ouvrira, et il n'a pas a l'instruire avant.

---

## Amendement du 2026-08-25 (hors sprint) - deux arbitrages de l'utilisatrice, deux reponses a `camille-po`

- **Auteur** : nadia-prof. **Rien du document ci-dessus n'est efface ni reecrit.**
- **Faits neufs** : l'utilisatrice arbitre que *"la saisie se fait uniquement par le clavier, aucun
  trace envisage"*, et elle **accepte** la proposition d'amendement du referentiel que la section
  4.3 lui routait - detacher les reperes de la ligne de relecture pour en faire une competence a
  part.
- **Ce que ce bloc est** : deux avis pedagogiques, demandes par `camille-po`. **Je ne classe aucune
  ligne, je ne deplace rien dans la matrice, je n'ecris aucune decision produit.** Elle tranche
  apres moi.
- **Mode** : aucune mesure, aucun jeu. Rien de neuf n'a ete livre ; ces deux points portent sur du
  texte de referentiel et sur ce qu'une mecanique peut faire, pas sur un increment.

---

## A. "Aucun trace envisage" et la ligne *Reconnaitre, nommer et tracer des figures planes* (mathematiques CM1)

### A.1 D'abord l'intitule exact, parce qu'il porte plus que ce qu'on m'a rapporte

`squad/programme/mathematiques/competences-cm1.md` ecrit, mot pour mot :

> *"Reconnaitre, nommer et tracer des figures planes (carre, rectangle, triangle, cercle) **avec
> outils (regle, equerre, compas)**."*

**La question qui m'est posee cite les trois verbes et omet les outils.** Ce n'est pas un detail de
citation : les outils ne sont pas un accessoire du trace, ils sont ce qui distingue cette ligne de
la ligne de CP. Retirer le trace ne retire donc pas un verbe sur trois, il retire **le trace et les
outils**, c'est-a-dire la moitie ecrite et la seule qui soit propre au CM1.

### A.2 La reconnaissance et la denomination, sans le trace, couvrent-elles quelque chose de reel pour un CM1 ?

**Non. C'est une couverture de facade, et le motif n'est pas le meme qu'au CP.**

Au CP, *"reconnaitre et nommer quelques figures planes"* **est** la competence entiere, et je l'ai
refusee pour un motif de rendu (section 2 ci-dessus, `FIG-1` a `FIG-4`) : les proportions d'un glyphe
sont celles de la police, pas du contenu. Le refus portait sur la faisabilite, pas sur la valeur.

Au CM1, la valeur elle-meme n'y est plus. **Un eleve de CM1 qui ne sait pas nommer un carre n'est
pas un eleve de CM1.** Cette reconnaissance-la est acquise depuis le cycle 2, et la reciter une
annee plus tard ne travaille rien. Ce que le CM1 ajoute est exactement ce que le trace instrumente
oblige a mobiliser : que les quatre cotes soient egaux, que les angles soient droits, que le compas
reporte une longueur, que l'equerre verifie un angle. **Sans le geste, il ne reste pas une moitie de
la competence : il reste l'exercice de CP avec une etiquette CM1 dessus.** Servir cette ligne ainsi
la ferait compter deux fois - une fois au CP, une fois au CM1 - pour le meme travail.

**Je serais malhonnete de m'arreter la, parce qu'il reste quelque chose de reel, et ce n'est pas la
reconnaissance.** Ce qui est de niveau CM1, sans aucun trace, c'est **la reconnaissance par les
proprietes enoncees** : *"J'ai quatre cotes de meme longueur et quatre angles droits. Qui suis-je ?"*,
*"Combien de sommets a un triangle ?"*, *"Quel instrument sert a verifier qu'un angle est droit ?"*.
C'est du vocabulaire geometrique - cote, sommet, angle droit, diagonale, rayon - et c'est du travail
de CM1 authentique, servable **aujourd'hui, par la mecanique existante, en texte pur, sans aucune
figure a l'ecran**. Ce n'est pas la ligne du referentiel. C'est une competence voisine, reelle, et
la faire compter pour la ligne ecrite serait precisement la couverture partielle que ce depot
sanctionne depuis s01.

### A.3 Ce que je recommande a `camille-po`, sans le trancher

**La distinction la plus utile que je puisse lui donner est celle-ci, et je la mets en avant parce
qu'elle porte sur le statut qu'elle va choisir.** *"Non couvrable en l'etat"* recouvre aujourd'hui
deux impossibilites de nature differente, et les confondre ferait perdre une information :

- *"Lire a voix haute un texte court avec fluidite"* et *"Relire et corriger son texte"* sont hors de
  portee parce que **juger la reponse demande un humain** - une oreille, un lecteur. Aucune decision
  produit ne les debloquera jamais.
- *"tracer des figures planes avec outils"* est hors de portee parce que **le produit a decide de ne
  pas avoir le geste**. C'est un arbitrage, pas une loi. Il se revise le jour ou l'utilisatrice
  revise sa phrase.

Si sa matrice ne sait dire que *"non couvrable en l'etat"*, la note de ligne doit porter la
difference. Le choix du dispositif lui appartient entierement.

**Trois points concrets, dans l'ordre ou je les crois utiles :**

1. **La ligne, telle que le referentiel l'ecrit, n'est pas couvrable** - le trace et les outils en
   sont la part propre au CM1, et le produit ne les aura pas.
2. **Je refuse qu'elle soit comptee couverte par la reconnaissance et la denomination seules.** Ce
   serait une facade, et une facade qui duplique le CP.
3. **La voie qui existe reellement est un amendement du referentiel**, de la meme famille que celui
   que l'utilisatrice vient d'accepter pour les reperes : detacher de cette ligne une competence de
   **vocabulaire et proprietes des figures**, servable en texte pur par la mecanique d'aujourd'hui.
   Si `camille-po` veut cet intitule-la, je l'ecris - je ne le fais pas maintenant, parce que
   personne ne me l'a demande et que je ne veux pas qu'un intitule non demande circule comme
   acquis.

### A.4 Une seconde ligne touchee, que la question ne nommait pas

J'ai relu les quatre referentiels ligne par ligne pour verifier ce que *"aucun trace"* atteint. Une
seule ligne nomme le verbe *tracer*, celle ci-dessus. **Mais une deuxieme est touchee sans le
nommer** : *"Reconnaitre et **utiliser** la notion de symetrie axiale simple"* (mathematiques CM1,
meme section). En classe, *utiliser* la symetrie axiale, c'est **completer une figure par symetrie**,
sur un quadrillage - un trace. La moitie *reconnaitre* (identifier un axe, dire si deux figures sont
symetriques) survit sans trace ; la moitie *utiliser* tombe avec la meme phrase que la precedente.
Cette ligne merite exactement la meme question, et elle est aujourd'hui `non couvert` sans que rien
ne le signale.

Aucune autre ligne des quatre referentiels n'est atteinte : la ligne CP *"Se reperer et decrire des
deplacements sur un quadrillage"* demande de **decrire**, pas de tracer, et reste entiere.

---

## B. L'intitule de la competence detachee (francais CM1) - ce que `camille-po` attend de moi

Rappel de ce qui est en jeu : la ligne d'origine est `non couvrable en l'etat` depuis s19, sur mon
refus de la section 4 ci-dessus. Ce que je detache n'est pas cette ligne : c'est la **connaissance
des reperes**, que la section 4.3 decrivait deja comme *"servable aujourd'hui par la mecanique
actuelle, et bien"*.

### B.1 L'intitule que je propose

> **"Reperer et corriger la majuscule et la ponctuation dans une phrase donnee (majuscule initiale,
> point final, majuscule au nom propre, point d'interrogation d'une question)."**

**Et la meme, integralement accentuee, parce que c'est celle-la qui doit entrer dans le fichier** -
`squad/programme/francais/competences-cm1.md` est un fichier accentue, et cette ligne est du texte
de programme scolaire. `camille-po` copie celle-ci, pas la precedente :

> **"Repérer et corriger la majuscule et la ponctuation dans une phrase donnée (majuscule initiale,
> point final, majuscule au nom propre, point d'interrogation d'une question)."**

Section du referentiel : **"Orthographe et grammaire"**, pas "Ecriture". Le motif est le coeur du
detachement - la section "Ecriture" de ce referentiel porte la **production** d'un texte (rediger,
relire son texte) et c'est justement de la production que je detache cette competence. C'est une
connaissance de la langue, elle va avec les accords et les homophones. Le placement final appartient
a `camille-po`.

**Chaque morceau de cet intitule porte une decision, et je les rends lisibles pour qu'aucune ne se
perde en chemin :**

- **"dans une phrase donnee"** est la charniere, et c'est le mot qui empeche les deux lignes de se
  reconfondre plus tard. Ma section 4.1 refusait le choix parce que *"la difficulte que cette
  competence nomme est une difficulte de relecture de soi, et elle n'existe que sur son propre
  texte"*. En detachant, je laisse deliberement cette difficulte-la dans la ligne d'origine. Sur la
  phrase d'un autre, la cecite a son propre texte disparait - et c'est acceptable ici, parce que ce
  que la competence detachee evalue n'est plus la relecture, c'est la **connaissance du repere**.
  **Je ne me contredis pas : je decoupe exactement le long de la ligne de mon propre refus.**
- **"Reperer et corriger"** plutot que "connaitre". Choisir la phrase bien ponctuee, ou designer ce
  qui manque, est un acte de correction au sens de la classe. "Connaitre" serait inverifiable ;
  "relire" ramenerait la ligne d'origine.
- **L'enumeration entre parentheses est limitative, et c'est voulu.** L'origine dit *"ponctuation"*,
  ce qui est trop large : **la virgule ne doit pas y entrer.** Son placement releve souvent du style
  et non de la regle, deux adultes competents la placent differemment, et une mecanique a reponse
  unique compterait faux un enfant qui a raison. Meme motif pour le point d'exclamation, affaire de
  ton plus que de regle. Les quatre reperes retenus sont ceux qu'une bonne reponse peut trancher
  sans discussion.
- **La forme suit le registre des lignes voisines** - verbe a l'infinitif, complement, precision
  entre parentheses, comme *"Distinguer et utiliser correctement les principaux homophones
  grammaticaux (et/est, son/sont, a/a accentue...)"*. Les points de suspension y sont ouverts ; ici
  je les omets volontairement, parce que ma liste est limitative.

### B.2 Ce qui reste dans la ligne d'origine, et si ce reste demeure hors de portee

La ligne d'origine peut rester litteralement inchangee : *"Relire et corriger son texte a l'aide de
reperes simples (majuscules, ponctuation)."* Le detachement ne lui retire aucun mot ; il retire
seulement l'idee qu'un paquet sur les reperes pourrait un jour la couvrir.

**Ce qui reste, et c'est tout ce qui restait deja apres mon refus de la section 4 :**

- **la relecture de soi** - la cecite d'un enfant a son propre texte, qui est la seule chose
  difficile de cette ligne ;
- **l'ignorance du nombre d'erreurs** - dans une vraie relecture, l'enfant ne sait ni combien il y
  en a, ni s'il y en a ;
- **la dependance structurelle** a *"Rediger un texte narratif court et coherent"*, elle-meme non
  couverte, sans laquelle il n'y a aucun texte a relire ;
- **le jugement** de la correction, qui demande un adulte qui lit.

**Oui, ce reste demeure hors de portee, et il l'est exactement au meme degre qu'avant.** Le
detachement n'ameliore pas cette ligne d'un pouce : il la rend seulement honnete, en lui retirant la
partie qui donnait l'illusion qu'un contenu pourrait l'entamer. **Je recommande a `camille-po` de
laisser son statut inchange** ; ce que le detachement change est ailleurs, dans le denominateur,
puisqu'une ligne servable apparait a cote.

**Si elle prefere un intitule reecrit** - pour que la ligne d'origine dise elle-meme pourquoi elle
est hors de portee plutot que de renvoyer a une note - voici le texte, et il est optionnel :

> *"Relire son propre texte et le corriger à l'aide des repères connus, sans indication du nombre ni
> de l'emplacement des erreurs."*

Je ne le recommande ni ne l'ecarte : un referentiel qui explique son impossibilite dans son intitule
est plus clair a lire et moins fidele a un programme scolaire. C'est un arbitrage de forme, il est a
elle.

### B.3 Couvrable par la mecanique existante, ou demande-t-elle la saisie ?

**Couvrable par la mecanique existante - un choix parmi des propositions - sans saisie, sans audio,
sans visuel, sans une ligne de code neuve.** Elle entre donc dans la reserve de contenu seul, pas
dans la file bloquee. C'est la reponse a la question posee, et elle est nette.

Elle a de plus une vertu que je signale parce que `camille-po` la cherchait : **cette reserve s'etait
raccourcie de trois lignes par mes propres refus** (section 5 ci-dessus). Celle-ci lui en rend une,
et c'est une ligne dont le contenu s'ecrit vite.

**Trois formes d'item sont possibles ; elles ne se valent pas et je ne laisse pas le choix a
l'estime :**

- **Forme A - quatre versions de la meme phrase, une seule correctement marquee.** C'est la forme
  qui vient a l'esprit et **c'est la moins bonne.** Les propositions sont des phrases entieres : elles
  sont longues, elles se ressemblent a un signe pres, et l'enfant doit comparer quatre chaines quasi
  identiques. Charge cognitive elevee pour la competence visee, et surtout ce depot a passe trois
  sprints sur la largeur des rangees de propositions - quatre phrases cote a cote y retomberaient
  tout droit. **Je la deconseille.**
- **Forme B - une phrase, et les propositions nomment ce qui manque** : *"il manque un point"*, *"il
  manque une majuscule"*, *"il manque une majuscule au prenom"*, *"cette phrase est correcte"*.
  Propositions courtes, phrase unique bien lisible, et c'est la question qu'un maitre pose vraiment.
  **C'est la forme que je recommande.** La quatrieme proposition, *"cette phrase est correcte"*,
  est ce qui rapproche le plus l'exercice d'une vraie relecture, ou l'enfant ne sait pas s'il y a une
  erreur - c'est la seule part de la difficulte d'origine que le detachement peut sauver, et elle
  vaut d'etre prise.
- **Forme C - un signe manquant a choisir en fin de phrase** (`.` contre `?`). Correcte sur le fond,
  mais les propositions sont alors des glyphes d'un caractere dans des boutons prevus pour des mots.
  Si elle est retenue, la lisibilite et la taille de cible relevent d'`ines-design`, pas du contenu.

**Quatre conditions prealables, que j'ecris maintenant plutot qu'apres coup**, sur le modele de ce
que je fais depuis s13. Elles ne remplacent pas mon audit : le paquet passera devant moi comme tout
autre.

- **`REP-1`** - Les phrases sont **courtes et de vocabulaire CM1**, et surtout **sans virgule**.
  Une virgule dans l'enonce ramene la question que l'intitule exclut, et un enfant qui doute de la
  virgule doutera de tout l'item.
- **`REP-2`** - **Au moins un quart des items ne contient aucune erreur**, et la proposition *"cette
  phrase est correcte"* y est la bonne reponse. Sans cela, l'enfant apprend qu'il y a toujours une
  faute, ce qui est le contraire exact de la relecture.
- **`REP-3`** - **Un seul repere en jeu par item.** Une phrase a la fois sans majuscule et sans
  point ne dit pas lequel des deux l'enfant a repere, et rend deux propositions justes.
- **`REP-4`** - Les quatre reperes de l'intitule sont **chacun representes**, aucun ne depassant la
  moitie des items. Sans cela, la competence annoncee n'est pas celle qui est travaillee.

### B.4 Sur quelles proprietes je reste la seule garde

`REP-1` a `REP-4` portent sur **la composition d'un paquet** - la presence de virgules, la part
d'items sans erreur, l'unicite du repere en jeu, l'equilibre des quatre reperes. **Aucun test de ce
depot ne les verifiera**, et un paquet integralement fautif sur les quatre passerait une CI verte
sans laisser de trace. C'est la meme famille que `DEN-1` a `DEN-8` et que `NAT-1` a `NAT-8`, et c'est
la famille de defaut qui a deja traverse une CI verte quatre fois sur ce produit.

`REP-2` est celle qui se perdra le plus facilement, parce qu'un paquet ou toutes les phrases ont une
faute est **plus simple a ecrire** et se lit tres bien.
