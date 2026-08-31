# Fiche de conformite - `bandit-manchot-figures-planes-cp`

- **Jeu** : `bandit-manchot-figures-planes-cp`, paquet `cp-figures-planes`, 12 items illustres.
- **Competence visee** : Mathematiques CP, "Espace et geometrie" - *"Reconnaitre et nommer quelques
  figures planes (carre, rectangle, triangle, cercle)"*.
- **Conditions opposables** : `FIG-1` a `FIG-4` (2026-08-24, reconduites sans changement le
  2026-08-31), `CON-1` a `CON-5` (2026-08-31).
- **Auteure du verdict** : `nadia-prof`. **Un verdict n'est jamais efface** : les suivants
  s'ajoutent, dates.

---

## Verdict du 2026-08-31 (s25, premiere paire visuelle)

# Verdict : **VALIDE SOUS RESERVE** - Done pedagogique **accorde**, une reserve non bloquante

**La ligne de matrice peut passer `couvert-valide`, ligne entiere, sans mention de couverture
partielle.** `camille-po` en dispose.

**La reserve porte sur l'indice et elle est en section 10.** Elle ne retient pas le Done : sur l'ecran
d'arrivee, celui ou l'enfant repond la premiere fois, **il n'y a aucune fuite** et la competence est
reellement exercee sur les douze items.

### 1. Ce que j'ai fait, avec sa methode et son perimetre

**J'ai joue**, sur un jar construit depuis l'arbre de `s25-sofia-review` (lignee verifiee par moi :
`130ba64` est bien un ancetre). Partie ouverte, **une reponse fausse, puis une deuxieme, puis une
troisieme jusqu'a la revelation**, pour voir ce que l'enfant recoit a chaque etat.

**J'ai verifie les douze dessins un par un**, en lisant leur geometrie et non leur nom de fichier.
C'est la seconde passe que j'avais price le 2026-08-31, et personne ne pouvait la faire a ma place.

**Ce que je n'ai pas fait** : aucun `mvn verify`, aucune mesure de pixel. **La porte technique n'est
pas la mienne et je ne la reprends pas a mon compte** ; le Done que je prononce ici n'est pas fonde
sur elle.

### 2. Un fait sur l'outil de construction, que je verse parce qu'il a failli me tromper

`bash outils/mvn-jdk21.sh` a **refuse de construire**, et son message est juste : l'entree
`<version>21</version>` du fichier de toolchains de l'utilisateur designe un JDK qui **rapporte
25.0.2**. J'ai construit en designant directement ce JDK, et **je le declare plutot que d'annoncer
un chiffre sur "21"**.

**Ce qui m'a evite l'erreur, et je l'ecris pour que la prochaine y pense** : apres l'echec, un jar
etait toujours la - **celui de ma session du matin**. J'ai regarde son horodatage avant de le jouer.
**Sans ce regard, j'aurais audite l'increment d'hier en croyant auditer celui d'aujourd'hui.** C'est
exactement le mode de defaillance que `CLAUDE.md` nomme : *l'ecart muet*.

*Consequence sur mes propres dires : le jar que j'ai joue ce matin a ete construit par cette meme
enveloppe avant qu'elle ne soit durcie, donc sur un JDK qui rapporte 25.0.2 et non 21. Cela ne change
rien a ce que j'ai lu a l'ecran et je ne retire aucun constat ; je corrige ce que je croyais de la
version.*

### 3. `FIG-1` a `FIG-4`, verifiees une par une sur la geometrie reelle

| Condition | Verdict | Ce que j'ai verifie, et comment |
|---|---|---|
| **`FIG-1`** - figures dessinees, proportions declarees par le contenu | **TENUE, 12 sur 12** | Chaque dessin porte ses coordonnees. **Les trois carres mesurent 72x72, 46x46 et 60x60** - carres a l'unite pres. **Les trois rectangles mesurent 84x36, 36x88 et 70x30** - aucun n'approche le carre. Aucune police, aucun glyphe : `rect`, `circle`, `polygon` |
| **`FIG-2`** - au moins trois exemples par forme, variant orientation, taille et proportions | **TENUE**, avec une lecture que je motive en section 5 | Carres : droit, sur la pointe a 45 degres, incline a 20 degres, trois tailles. Rectangles : couche, debout, incline a -25 degres, trois rapports. Triangles : pose, rectangle, renverse. Cercles : trois tailles |
| **`FIG-3`** - la couleur ne distingue jamais deux formes, aucune consigne ne la mentionne | **TENUE, 12 sur 12** | Une seule teinte sur les douze fichiers, trait seul, aucun remplissage. **Les douze dessins lus en niveaux de gris donnent les memes reponses** |
| **`FIG-4`** - ni "cercle" et "disque" confondus, ni figure plane et solide | **TENUE** | Les trois cercles sont sans remplissage : c'est un cercle, pas un disque. Le mot "disque" n'apparait nulle part, aucun solide n'est nomme |

**`CON-1` a `CON-5`** :

- **`CON-1`** - en effacant l'image, la reponse n'est plus trouvable : l'enonce demande *"quelle est
  cette figure"* et les trois propositions sont trois noms de figures. **TENUE, 12 sur 12.**
- **`CON-2`** - **aucun des douze enonces** ne decrit la position, la forme, la quantite ni la
  couleur. Trois formulations en tout, toutes neutres. **TENUE.**
- **`CON-3`** - la donnee est dans l'image, une seule fois. **TENUE.**
- **`CON-5`** - **les douze textes alternatifs sont identiques**, et c'est ce que ma condition
  impose, pas une economie. **TENUE**, developpe en section 6.

### 4. Ce que j'ai vu en jouant, et qu'aucun test ne dit

- **Le dessin survit a l'echec.** Verifie a l'ecran : present a l'arrivee, **present apres un echec,
  present apres trois, present a la revelation**. C'etait le defaut que je cherchais en priorite - un
  dessin qui disparaitrait apres une reponse fausse retirerait a l'enfant la donnee de la question
  sans qu'il ait rien fait de mal. **Il ne se produit pas.**
- **L'ordre de lecture est celui d'un enfant** : consigne, dessin, question, reponses. La consigne
  *"Regarde bien le dessin, puis choisis son nom."* dit le geste dans l'ordre ou il se fait. Six
  mots, tous lisibles au CP.
- **Un identifiant qui ne resout rien rend 404**, et l'enfant voit alors le texte alternatif : ce
  n'est pas un ecran vide. Le garde-fou de `theo-archi` empeche par ailleurs qu'un tel identifiant
  atteigne la production. **Ma question de la section 9.2 de mon arbitrage est repondue par
  l'observation, et je la referme.**
- **Les indices sont vrais, courts, et aucun ne nomme une figure** : *"Compare la longueur des
  cotes."*, *"Compte les cotes de la figure."*, *"Regarde si la figure a des coins."* **Ils ne
  donnent pas la reponse par leur sens - mais ils la designent par leur distribution, et c'est la
  reserve de la section 10.** Je le signale ici pour qu'on ne lise pas cette ligne comme un quitus.

### 5. `FIG-2` sur le cercle - la question de `lea-dev`, tranchee

**Elle a raison, et j'accepte. Ses trois cercles varient par la taille seule.**

**Je lis `FIG-2` par son motif, que la condition porte elle-meme** : *"Un carre pose sur la pointe
reste un carre : c'est precisement ce qu'un CP doit apprendre, et un paquet a un exemplaire par forme
enseigne le contraire."* **Ce que la condition demande est l'invariance de la forme sous une
transformation qui ne la change pas.** Pour un cercle, **la taille est la seule transformation de ce
genre qui existe** : il n'a ni orientation ni proportions. Trois tailles servent donc le motif entier
de `FIG-2`, et exiger davantage serait exiger une chose qui n'existe pas.

**Ce que cette lecture n'autorise pas, et je le borne tout de suite** : elle vaut **pour le cercle
seul**, et **par une propriete de la figure**. Sur le carre, le rectangle et le triangle,
l'orientation et les proportions existent, `FIG-2` les demande, et **un paquet qui ne ferait varier
que la taille sur ces trois formes-la serait refuse**. Ce n'est pas un precedent.

**Elle a eu raison de me la router plutot que de trancher** : c'etait un jugement sur ce que la
condition veut dire, et la condition est de moi.

### 6. Le fait que ce paquet demontre, et qui n'est pas un defaut de ce paquet

**Les douze textes alternatifs identiques ne sont pas une negligence : ils sont la consequence exacte
de `CON-5`**, et `lea-dev` le dit correctement - *"toute difference entre eux porterait la reponse"*.
J'ai cherche une formulation qui distinguerait sans livrer, et **je n'en ai pas trouve**.

**La conclusion que `sofia-sm` en tire est juste et je la confirme** : *ce n'est pas le palier 1 qui
rend une ligne injouable sans la vue, c'est le palier 1 sur une ligne dont la reponse est ce que
l'image montre.* Sur cette ligne-ci, la reponse **est** le dessin ; aucun texte alternatif ne peut la
decrire sans la donner.

**Ce que je fais de ce fait, et ce que je n'en fais pas.** Je ne refuse pas le paquet pour cela : **la
competence elle-meme est visuelle** - *reconnaitre* une figure -, et le referentiel la pose ainsi. Une
ligne qu'un enfant aveugle ne peut pas jouer n'est pas un defaut de ce contenu, c'est une question de
produit. **Elle appartient a `camille-po` puis a l'utilisatrice, pas a ma porte**, et je la verse sans
la trancher.

### 7. Une inexactitude que je signale sans qu'elle bloque

Le commentaire de `figure-triangle-pose.svg` annonce *"Triangle a trois cotes egaux"*. **Il ne l'est
pas** : sommets `(50,10)`, `(89,84)`, `(11,84)`, donc **base 78 et cotes 83,65** - un triangle
**isocele**, pas equilateral. *Methode : distances euclidiennes entre les trois sommets declares dans
le fichier.*

**Cela ne touche pas l'enfant** : il voit un triangle, il repond *"un triangle"*, il a raison. Le
dessin est juste ; c'est la phrase qui le decrit qui ne l'est pas. **Je le route comme une correction
de commentaire, pas comme un defaut de contenu**, et cela ne retient pas le Done.

*Je le signale quand meme, et voici pourquoi : un commentaire faux dans un fichier de contenu est de
la meme famille qu'un chiffre sans methode. Le prochain qui ecrira un triangle equilateral en
recopiant celui-ci croira en avoir un.*

### 8. Sur quelles proprietes je reste la seule garde sur ce jeu

- **L'exactitude des douze dessins.** Aucun test ne mesure qu'un carre est carre. Je l'ai fait, et je
  le referai a chaque paquet illustre.
- **`CON-1`, `CON-3`, `CON-5`** - qu'aucun enonce et aucun texte alternatif ne porte la reponse.
- **`FIG-2`** - qu'une variation soit une vraie variation et non trois fois le meme dessin
  redimensionne. Sur le cercle, c'est justement ce que j'ai accepte, et par une propriete de la
  figure.
- **Que l'enfant voie le dessin quand il en a besoin**, par opposition a ce que le serveur envoie.

### 9. Ce que ce verdict ne fait pas

- Il **n'est pas fonde sur la CI** ni sur un compte de tests, que je n'ai pas mesure.
- Il **ne prononce rien sur les trois autres paquets de s25**, chacun sur sa fiche.
- Il **ne referme pas** la question de la ligne injouable sans la vue, qui n'est pas la mienne.
- Il **ne modifie aucun fichier** de code ni de contenu.

### 10. La reserve - l'indice partitionne les reponses, et **aucune de mes conditions ne le gouvernait**

**Le fait vient du `relecteur-pedagogique`, qui me l'a donne comme un fait mesure et non comme un
verdict. Il a eu raison de me le router. Je l'ai refait, et je le corrige a la hausse.**

Le paquet emploie **trois indices pour quatre formes**, et chacun est determine par la reponse :

| Indice | Reponses qu'il couvre |
|---|---|
| *"Compare la longueur des cotes."* | un carre, un rectangle |
| *"Regarde si la figure a des coins."* | **un cercle, et rien d'autre** |
| *"Compte les cotes de la figure."* | **un triangle, et rien d'autre** |

**Ma mesure : sur 8 items sur 12, l'indice designe la reponse a lui seul** - une seule des trois
propositions appartient a l'ensemble que cet indice couvre. *Methode : pour chaque item,
l'intersection de ses trois propositions avec l'ensemble des bonnes reponses que son indice porte
dans le paquet ; l'item est compte designe si cette intersection est un singleton.*

**Le `relecteur-pedagogique` en comptait 6 ; j'en compte 8.** Les deux qu'il n'a pas vus sont les
items 11 et 12, ou l'indice *"Compare la longueur des cotes"* ne laisse qu'un candidat parce que
l'autre membre de sa paire n'est pas propose. **Je corrige dans le sens defavorable au paquet, et je
le dis pour qu'on ne me croie pas sur parole : le compte est refaisable.**

**Son rapprochement est le vrai point, et il vise mes conditions, pas le paquet** : `CON-5` a rendu
les douze textes alternatifs identiques ; `SEM-8` gouverne l'indice du paquet de la semaine ; **pour
un paquet illustre, rien ne gouverne l'indice.** `FIG-1` a `FIG-4` n'en parlent pas, `CON-2` ne nomme
que l'enonce. **C'est un trou dans ce que j'ai ecrit, et c'est moi qui l'ai laisse.**

### 11. Pourquoi c'est une reserve et non un refus - et je viens d'en refuser un autre le meme jour

**Je refuse `cm1-substituts` aujourd'hui pour une marque de surface. Je dois donc dire ce qui separe
les deux cas, sinon ma porte est un caprice.**

**Trois differences, et la troisieme est decisive.**

1. **Le moment.** Le raccourci de `cm1-substituts` gagne **sur l'ecran d'arrivee, du premier coup,
   sans jamais echouer**. Ici, **l'indice n'est pas affiche a l'arrivee** - verifie en jouant :
   l'ecran d'arrivee ne porte ni verdict ni indice, l'indice n'apparait qu'apres un echec. **Sur les
   douze items, la premiere reponse de l'enfant se prend en regardant le dessin, et rien d'autre.**
2. **Le detour.** Le raccourci de `cm1-substituts` se lit dans le texte de l'item. Celui-ci demande
   d'avoir **memorise une correlation entre plusieurs items** - il faut avoir joue plusieurs fois et
   avoir remarque la regularite.
3. **Ce que le raccourci enseigne, et c'est ce qui tranche.** Dans `cm1-substituts`, la strategie
   recompensee est **fausse** : le premier personnage nomme n'est pas le referent en general, et
   l'enfant sort conforte dans une erreur. **Ici, l'association recompensee est vraie** : on
   reconnait bien un triangle en comptant ses cotes, et un cercle a ce qu'il n'a pas de coins. **Un
   enfant qui retient cela a appris quelque chose de juste.** Ce n'est pas une contrefacon au sens de
   `CON-1` : le texte ne fait pas a la place de l'enfant le geste que la ligne nomme, il **nomme la
   propriete a regarder**, ce qui est le travail normal d'un indice au CP.

**Et une quatrieme raison, qui est de discipline et non de fond** : pour `cm1-substituts`, la
propriete que j'oppose etait **ecrite et datee avant que le paquet ne soit ecrit**. Ici, **aucune de
mes conditions ne gouvernait l'indice d'un paquet illustre**. Refuser sur une exigence que je n'avais
pas ecrite serait deplacer mon poteau, et je ne le fais pas - c'est la meme regle qui m'a fait ne pas
refuser `NAR` a s24 et ne pas refuser la reserve 2 de la monnaie aujourd'hui.

**Ce qui aggrave quand meme le point, et que je verse sans en faire un motif** : `DEC-0061` a etabli
qu'un second essai ne coute rien au score. **Un enfant qui repond au hasard puis lit l'indice ne perd
donc rien**, et sur 8 items il gagne. Ce defaut-la est deja documente et il n'appartient pas a ce
paquet.

> **`CON-6`, neuve, datee du 2026-08-31, non retroactive** - Sur un paquet illustre, **l'indice est
> choisi d'apres les propositions de l'item, jamais d'apres sa bonne reponse.** *Verification : sur
> au moins la moitie des items, l'indice de l'item est aussi porte par un item dont la bonne reponse
> est differente. Unite de compte : l'item.*
>
> **Motif** : l'indice n'apparait qu'apres un echec, et un indice qui ne va qu'avec une seule reponse
> devient a ce moment-la une designation et non une aide.
>
> **Elle ne s'applique pas aux douze items de ce paquet.** Elle vaut pour les paquets illustres
> suivants, et **elle est calculable** - c'est `theo-archi` et `yanis-qa` qui diront si elle vaut un
> garde-fou. Je recommande qu'elle en devienne un : c'est le champ que ma famille `CON` ne couvrait
> pas, et il vient de servir.

**Ce que je demande pour un sprint suivant, sans que cela retienne le Done** : que les indices de ce
paquet soient reecrits de facon qu'un meme indice puisse accompagner des reponses differentes.
*"Regarde si la figure a des coins."* convient parfaitement a un item dont la reponse est un carre,
des lors qu'un cercle figure parmi ses propositions. **Le paquet n'a pas besoin d'items neufs, il a
besoin d'une autre distribution de ses trois indices.**

### 12. Une verification que j'ai du refaire, et pourquoi je la raconte

**Les douze dessins ont ete modifies par `lea-dev` pendant mon audit**, apres ma premiere passe
d'exactitude. **J'ai donc refait la passe sur l'arbre final** plutot que de garder un constat qui
aurait porte sur un increment disparu.

**Resultat** : la modification porte sur la **taille declaree** de chaque dessin - 480 devient 176 -
et **sur rien d'autre**. Le cadre de vue et **toutes les coordonnees des douze figures sont
identiques a l'unite pres**. *Methode : comparaison des elements de forme des douze fichiers entre
les deux etats.*

**Les proportions sont ce que `FIG-1` gouverne, et elles n'ont pas bouge. Ma verification tient sur
l'arbre final**, et c'est cet arbre-la que juge ce verdict.

*Je le raconte parce que le fait inverse aurait ete invisible : un dessin corrige apres une passe
d'exactitude, et un Done prononce sur des coordonnees qui n'existent plus. C'est le meme mode de
defaillance que le jar de la section 2, deux fois dans le meme audit.*
