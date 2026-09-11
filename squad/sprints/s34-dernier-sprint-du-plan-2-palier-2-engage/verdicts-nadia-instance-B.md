# [Sprint s34] Verdicts pedagogiques - `nadia-prof`, instance B

- **Date** : 2026-09-11
- **Auteure** : `nadia-prof`, instance B (`PAR-7`, deux images tenues a la fois)
- **Arbre** : `round-two` a `a7852398` - fusion des quatre branches, porte technique verte,
  1464 tests
- **Perimetre** : `S34-EMCCP07`, `S34-EXC-TACHEB`, le palier 2 (`S33-PAL2-1`, `S33-PAL2-TROU-A`,
  `S33-PAL2-TROU-B`), plus les deux points que `sofia-sm` m'a routes en section 15.2 du planning

---

## 0. Mode d'audit, declare avant les verdicts

- **Joue.** Application lancee sur le JDK 21.0.10 de la toolchain, port 8099. **Avant de jouer, j'ai
  compare le contenu du jar au contenu de l'arbre sur les trois paquets audites** : identiques. Je ne
  juge pas un jar plus vieux que l'arbre - c'est le meme genre d'ecart muet que `CLAUDE.md` sanctionne
  sur une mesure sans `-am`.
- **Six parties completes** : une de dix manches toutes justes sur la frise, une avec une erreur
  volontaire par manche sur chacun des trois jeux, deux poussees jusqu'a l'epuisement des essais sur
  une meme manche, une de dix manches justes sur `regle-souhait-loi`.
- **Et, pour la premiere fois, un ecran regarde et non seulement lu.** L'ecran de manche de
  designation a ete rendu dans un navigateur avec les feuilles et les dessins servis par
  l'application, aux deux largeurs de conteneur reellement servies (768 px et 1024 px), capture en
  image et mesure zone par zone. **`ZON-9` ne se tient pas autrement**, et trois des cinq constats du
  palier 2 ne sont visibles que la.
- **Lu sur fichier, et declare comme lecture** : `PaquetCpEmcExclusionTest`, `recreo.css`,
  `recreo.js`, les trois entrees de `catalogue.yaml`, les deux `svg`. **Deux tests executes**
  (`PaquetCpEmcExclusionTest`, `PaquetCpEmcRegleSouhaitLoiTest`, avec `-am`, 7 et 8 tests verts).
- **Ce que je n'ai pas pu mesurer, et je le dis plutot que de l'affirmer** : la visibilite de l'anneau
  de focus apres un appui au doigt. Je l'ai mesuree apres un focus programmatique **sans geste
  anterieur** - c'est le meilleur cas, pas celui de l'enfant. Voir `FRI-R5`.

---

## 1. Les quatre verdicts, en une ligne chacun

| Ligne / increment | Verdict | Motif en une phrase |
|---|---|---|
| **`EMC-CP-07`** (`S34-EMCCP07`) | **VALIDE SOUS RESERVE - Done pedagogique prononce** | Les trois modes de defaillance nommes d'avance sont evites a l'ecran ; deux reserves neuves, non bloquantes |
| **`S34-EXC-TACHEB`** (ligne `EMC-CP-03`, deja fermee) | **REFUSE** | Les cinq indices de la tache B n'ont pas ete touches : ils repondent a la question que la refonte supprime, et aucun ne dit a l'enfant ce qu'il a rate |
| **`QLM-CP-14`** (`S33-PAL2-1`, `cp-frise-de-la-journee`) | **REFUSE** | Neuf items sur onze se gagnent sans regarder le dessin (`ZON-1`), et le pictogramme du lever ne se lit pas (`ZON-9`) |
| **`S33-PAL2-TROU-A` et `S33-PAL2-TROU-B`** | **payees, verifiees a l'ecran de fin** | La question rappelee est celle de la derniere manche ; ni identifiant ni libelle de zone |

**Mouvement de matrice attendu** : **une seule ligne**, `EMC-CP-07` a `couvert-valide`.
`QLM-CP-14` et `QLM-CP-11` restent `non couvert`. `EMC-CP-03` reste `couvert-valide`, non rouverte.
**Je ne touche pas `squad/programme/MATRICE-couverture.md`.**

**Ce que cela vaut contre l'attente du planning** : l'attente de fermeture etait de **1** sur
`EMC-CP-07`, conditionnee au prealable technique obtenu avant l'ouverture de la branche. **Le
prealable a ete obtenu** (`f705d62a`, commite par `theo-archi` avant `cefb15aa`, sequence verifiee sur
l'historique du fichier) : l'attente etait donc de 1 et elle est tenue. Le palier 2 portait sa propre
condition de fermeture et non un chiffre ; **cette condition n'est pas remplie.**

---

## 2. `S34-EMCCP07` - le premier test grandeur nature de `RES-1`

**Verdict : VALIDE SOUS RESERVE. Done pedagogique prononce.** Bloc complet dans
`squad/conformite/FICHE-bandit-manchot-regle-souhait-loi-cp.md`.

**La question que ce sprint devait trancher n'etait pas *"le defaut est-il la ?"*, c'etait *"un mode
de defaillance nomme d'avance empeche-t-il le defaut, ou permet-il seulement de le nommer apres
coup ?"*** Sur ce paquet, **il l'a empeche**, et les trois se verifient a l'ecran :

- **`FD-1`, le principal, celui que j'avais assorti d'un refus d'avance.** L'item transpose sert
  *"Qui doit le porter ?"* avec **tous les enfants concernes, meme pour un trajet tres court** contre
  *seulement ceux qui font de la competition* et *seulement si les parents le decident*. **Le mot
  *adulte* n'est sur aucun des trois boutons.** L'axe est celui de `O-2`, pas celui que ma propre
  phrase du 2026-09-10, executee a la lettre, aurait produit.
- **`FD-2`** : les items 5 et 6 sont corriges dans le meme geste, *une loi* retiree des deux. Vu a
  l'ecran, deux boutons chacun.
- **`FD-3`** : `FAB-1c` recomptee sur six items, un item fautif sur six pour un seuil a deux.

**Ce que j'en conclus pour le dispositif, et c'est la reponse au quatrieme travail** : voir section 5.

**Deux reserves neuves, non bloquantes, au format `RES-1`** : `RSL-R1` (l'item transpose est le seul
des six a ne nommer aucune source de droit - ni *loi*, ni *code de la route*, ni *En France*, qu'il
portait avant le mouvement) et `RSL-R2` (*"tous les enfants concernes"* : dix mots sur un bouton, et
*concerne* n'est pas un mot de CP). **Chacune porte son objet distinct de son remede, son mode de
defaillance nomme d'avance et son perimetre mesure sur l'etat d'arrivee.**

**Pourquoi `RSL-R2` n'est pas un motif de refus, dit plutot que laisse a deviner** : aucune de mes
barres ecrites ne porte sur la longueur ni sur le vocabulaire d'une **proposition** - `LON-1` et
`LON-2` portent sur l'enonce. **Refuser un cinquieme passage sur un mot, quand l'equipe a evite les
trois modes que j'avais nommes d'avance, serait le caprice que je m'interdis.**

---

## 3. `S34-EXC-TACHEB` - la lettre executee, l'objet manque

**Verdict : REFUSE.** Bloc complet dans `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md`.
**`EMC-CP-03` n'est pas rouverte.**

**Le motif, `EXC-R10`.** `git diff` sur `365de10a` : les cinq `enonce`, les cinq `bonneReponse` et les
dix `distracteurs` de la tache B sont reecrits. **Les cinq lignes `indice` ne sont pas touchees.**
Elles sont celles de la tache *"Quel adulte est la ?"* que la refonte supprime. **Joue, manche 1 :
j'ai presse *attendre que cela s'arrete tout seul* et le jeu m'a repondu *"Cette personne est la pour
veiller sur les eleves qui jouent."*** Quelle personne ? Je n'en ai nomme aucune.

**La condition n'est ni neuve ni cachee : elle est ecrite dans cette fiche, par moi, la veille, comme
la condition d'acceptation du distracteur qui faute ici** - *"une conduite du genre attendre que cela
s'arrete est acceptable [...] a la condition que l'indice dise pourquoi attendre ne marche pas."*
Elle vaut sur **zero item sur cinq**. Et mon perimetre disait *"les cinq items de la tache B **en
entier**"* : un item, c'est aussi son indice.

**Ce que la refonte a reussi, et ce n'est pas une consolation.** `C-1`, `C-2`, `EXC-R7`, `EXC-R8` et
mon interdit de s31 sont tenus, verifies a l'ecran item par item. **Le bouton *"a personne"* n'est pas
revenu** : ce que j'avais fait retirer etait un constat desinvolte (*"ce n'est pas grave"*), ce qui est
servi aujourd'hui sont des conduites qu'un enfant a reellement. **Un seul point manque, et il manque
sur les cinq items.**

### La propriete dont je m'etais declaree seule garde - elle est de nouveau gardee, et je dis jusqu'ou

Le 2026-09-10 j'ai ecrit, apres m'etre corrigee sur ma propre mesure : *"la methode qui encodait ma
condition de s31 devient verte sur une population vide [...] je redeviens seule garde du non-retour
du bouton *a personne*."*

**Verifie sur l'arbre fusionne.** `PaquetCpEmcExclusionTest.laPropositionAuRabaisAPersonneNApparaitDansAucuneTache`
parcourt **`items()`, le paquet entier**, et non plus une tache filtree qui pouvait se vider. **Et la
population est prouvee non vide par une methode voisine de la meme classe** :
`fer2AuMoinsDixItems` echouerait si `items()` rendait moins de dix items, et elle est verte.
**Mesure : 7 tests, 0 echec** (`mvn -pl adapters/adapter-content -am`, JDK 21.0.10). C'est exactement
la difference avec le defaut que j'avais mal decrit : la boucle s'execute sur onze items et trente-
trois propositions.

**Ce dont je reste seule garde, et je le dis pour que personne ne croie couvert plus que ce qui
l'est** : la sonde compare un **prefixe litteral** (*"a personne"*). *"ne rien dire a personne"* ou
*"personne ne peut l'aider"* passeraient sans un rouge. **La forme litterale du bouton de s31 est
gardee ; la famille du bouton ne l'est pas, et c'est moi, en jouant.**

**Deux reserves neuves, non bloquantes** : `EXC-R11` (sur trois items sur cinq, la bonne reponse est
la seule proposition qui nomme un adulte - **ma condition du 2026-09-10 ne posait aucun nombre et
elle est tenue ; ceci est une reserve neuve et datee, pas un durcissement**) et `EXC-R12`
(*"esperer qu'un adulte l'ait remarque"* : subjonctif passe et pronom elide sans antecedent, au-dessus
du CP).

---

## 4. Le palier 2 - la premiere epreuve reelle des huit conditions `ZON-*`

**Verdict sur `QLM-CP-14` : REFUSE.** Fiche neuve ouverte :
`squad/conformite/FICHE-designation-frise-de-la-journee.md`, ou tout est detaille.

**`ZON-7` est franchie, et c'est un fait a porter au credit du lot** : l'ecran de manche de
designation est servi, le jeu se joue de bout en bout, trois parties completes sans un ecran casse,
sur trois couches et sur une premiere livraison. **Ce lot *entre* dans mon audit**, ce qu'aucun lot de
designation n'avait pu faire depuis s28.

### Les deux motifs de refus, tous deux anterieurs a ce sprint

**`FRI-R1` - le texte alternatif enumere les moments dans leur ordre (`ZON-1`, `ZON-3`).** Les deux
dessins servent un texte alternatif qui liste leurs moments dans l'ordre de la frise, et les libelles
de zone sont des rangs. **Les deux ensemble forment une table de correspondance complete.** La passe
image effacee, item par item : **neuf items sur onze se gagnent entierement sans regarder le dessin**,
et sur l'item 1 c'est l'indice lui-meme (*"C'est le tout premier moment de la journee"*) qui donne le
libelle de la bonne zone mot pour mot. `ZON-1` dit ce qui arrive alors, et le disait avant que ce lot
existe : *"l'item est une contrefacon et le lot est refuse a la porte."*

**C'est la defaillance que j'avais nommee d'avance a s29 et dont je m'etais declaree seule garde** :
*"Ce qu'elle n'attrape pas, et je reste sa seule garde : la paraphrase. [...] Aucun automate ne verra
cela, et je le verifie item par item."*

**Je corrige ici une formulation que j'allais ecrire, avant de m'appuyer dessus.** Le garde-fou 5
d'`ADR-0080` ne compare pas le texte alternatif au libelle : **verifie dans le test et non dans
l'`ADR`**, `AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest` compare l'**enonce** au
libelle de la bonne zone. **Il ne lit pas le texte alternatif, et aucun test du depot ne le lit
contre quoi que ce soit.** Le defaut est donc plus large que je n'allais le dire : **rien ne
regarde.** Le test le dit lui-meme en me citant. **Quatre jobs verts, neuf items sur onze repondus
sans image.**

**La reparation est petite - deux lignes.** Un texte alternatif honnete dit ce que l'image **est**,
pas ce qu'elle contient dans l'ordre. **Je n'ecris pas les deux textes** (`RES-2`).

**Et je tranche la lecture plutot que de la laisser ouverte** : ce ne sont pas les libelles qui
fautent. Un rang dit ou est la zone, pas quel moment elle porte ; pour s'en servir, l'enfant doit
d'abord lire le dessin. **Ce sont les rangs *plus* le texte alternatif qui ferment la boucle.**
`ZON-10` n'est pas amendee et les libelles positionnels restent obligatoires sur ce paquet.

**`FRI-R2` - le pictogramme du lever ne se lit pas comme un lit (`ZON-9`).** Regarde a la taille
servie, zones masquees, puis au triple agrandissement : un rectangle large avec un petit rectangle et
un trait, plus un trait vertical detache qui deborde au-dessus. **Je ne trouve pas le lit.** L'item 1
est refuse sur ce seul motif, independamment de `FRI-R1`. L'item 7, qui vise la meme zone par une
relation, n'est pas touche.

### `ZON-11` sur la frise - mon propre arbitrage, verifie sur ce qui est reellement servi

**Servi exactement.** Sur `frise-soir-routine`, l'ordre des boutons dans le formulaire est `diner`,
`brosse-dents`, `pyjama`, `coucher` : **l'ordre de l'axe d'abord, puis le haut avant le bas dans la
colonne partagee**, ce que j'avais tranche le 2026-09-10 contre l'application litterale de `ZON-11`.
**La liste ne dit jamais le troisieme avant le deuxieme.** Le repli que j'avais ecarte n'a pas ete
tente et `ZON-13` est tenue : les deux cadres empiles partagent leur abscisse et sont separes de 2 %
en ordonnee, verifie sur les quatre nombres et vu au rendu.

`FER-1` est tenue sur la parenthese conjonctive : *avant* (items 5, 7), *apres* (item 6), *en meme
temps* (items 10, 11) - **et *en meme temps*, celle que j'avais nommee la plus difficile a dessiner,
est servie par une geometrie qui la montre.** `FER-3` : `T-B` a 5 items, `T-A` a 6 - et `T-A` est la
seconde tache que j'avais moi-meme autorisee sur cette ligne a s29 (*"Montre le gouter"*), **je ne la
rouvre pas.**

### Trois reserves, plus le fait le plus lourd de cet audit

`FRI-R3` (le bol du diner ne porte pas le repas du soir), `FRI-R4` (*"le quatrieme"* et *"le
cinquieme"* debordent de 2 a 3 px de leur bouton, mesure au rendu), `FRI-R5` (la revelation apres
trois essais n'est qu'un deplacement de focus, sans marque visible).

**`FRI-R5` est la reponse a la question qu'`ADR-0088` m'a explicitement routee** - *"l'enfant sort-il
d'une partie de designation sans savoir ou etait la reponse, et est-ce acceptable ?"* **Ma reponse :
oui il en sort sans le savoir, et je ne rouvre pas la clause B pour autant.** L'ecran de fin n'a pas a
porter la zone ; c'est l'ecran de manche, ou le dessin est encore la, qui doit la montrer - et il ne
la montre qu'au clavier. **Sur le bandit manchot, la meme situation sert un bloc explicite (*"Appuie
sur la bonne reponse :"*), joue et vu le meme jour.** Le chemin fonctionne : la pression suivante
passe a la manche d'apres, donc un enfant bloque avance.

**Et le fait le plus lourd, que je nomme sans en faire un motif de refus** : **les boutons de zone
couvrent le dessin qu'ils designent.** `.zone` pose un fond blanc a 55 % et un texte gras de 20 px
par-dessus l'image ; sur la frise de la journee, les six boutons couvrent tout le dessin. Au rendu,
**les six pictogrammes sont des fantomes gris derriere le texte, l'axe du temps est interrompu par
les boites, et l'ecran ne se lit plus comme une frise mais comme six cartes numerotees.** La
strategie la plus rapide devient : lire le rang, jamais le dessin. **Ce n'est pas un defaut du contenu
de `lea-dev`** - cela vient de la feuille de style contre des cadres de cette forme, et **cela
frappera tous les paquets de designation a venir.** Je ne le compte pas dans le refus parce que la
condition qui le juge, je l'ecris aujourd'hui (`ZON-15`).

### Le plancher de lisibilite, que `sofia-sm` m'a route (planning, section 15.2)

**Je rends deux conditions neuves, datees du 2026-09-11, et je dis qu'elles ne valent pas contre le
lot d'aujourd'hui.** Texte complet dans la fiche du jeu de designation.

> **`ZON-15`.** Le libelle d'une zone est un texte qu'un enfant de CP doit lire pour presser le bon
> bouton : **le plancher est 1,25rem**, il est de moi, il n'est pas neuf, `ADR-0050` (decision 4) le
> tient deja sur toute taille declaree par la feuille maison, et **aucune geometrie ne justifie de le
> baisser**. Deux conditions s'y ajoutent, parce qu'une hauteur de caractere ne suffit pas : a la
> taille servie, le libelle **tient entierement dans son cadre** sans deborder ni toucher une zone
> voisine, et **ne recouvre aucun trait du dessin que sa zone designe**. **Si les deux ne tiennent
> pas ensemble a 1,25rem, c'est le cadre ou le libelle qui change, jamais la police.**
>
> **`ZON-16`.** Un libelle positionnel de CP compte **au plus trois mots et ne porte qu'un seul
> repere**. *"en haut, au centre"* passe ; *"a gauche, contre le mur"* ne passe pas.

**Ce que cela repond a `lea-dev`, concretement.** Les six cadres du plan (`armoire` 56 x 77 px,
`porte` 63 x 56 px) n'offrent que 16 a 23 px de boite de contenu apres le pave de `.zone` : **aucun
libelle n'y tient a 1,25rem, meme d'un mot.** Le plancher ne bouge pas, donc **la sortie est double et
les deux moities sont necessaires** - les cadres a `ines-design` (`ZON-15`), les six libelles a moi
(`ZON-16`). **Ni l'une ni l'autre seule ne suffit**, et la geometrie n'est pas de mon ressort : je ne
la tranche pas.

---

## 5. Mon dispositif a l'epreuve - ce que `RES-1` a tenu et ce qu'il n'a pas tenu

**Question posee : pour chaque correction, a-t-elle suivi l'objet ou seulement la lettre ?**

| Increment | Objet ou lettre ? | Ce qui a fait la difference |
|---|---|---|
| **`S34-EMCCP07`** | **l'objet** | Trois modes de defaillance nommes d'avance, dont un assorti d'un refus d'avance ecrit en toutes lettres. Les trois evites |
| **`S34-EXC-TACHEB`** | **la lettre** | Objet et perimetre ecrits ; **le mode de defaillance qui aurait mordu ne l'etait pas** |
| **`S33-PAL2-1`** | ni l'un ni l'autre - **conditions anterieures non verifiees** | Aucune passe image effacee n'a ete faite avant livraison, et `ZON-1` demande exactement cela |

**La lecon, et elle porte sur mon propre format, pas sur le travail des developpeurs.**

Sur `EMC-CP-07`, `FD-1` etait **une phrase qu'un developpeur pouvait tester contre son diff** : *"si
je trouve cet axe a l'ecran, je refuse le cinquieme passage sans autre motif."* Elle est dans un bloc
intitule *Mode de defaillance*, sous l'objet qu'elle protege. **Elle a marche.**

Sur `cp-emc-exclusion`, la condition qui manque aujourd'hui - *l'indice dit pourquoi attendre ne
marche pas* - etait **ecrite le meme jour, par moi, dans la meme fiche**, mais **enfouie dans la
derniere phrase de ma reponse a `Q3`**, et non listee parmi les modes de defaillance de la section
4.2. Mon propre bloc *Mode de defaillance* de cette story n'en portait aucune trace : il nommait
*"garder trois adultes"*, *"payer les reserves une par une"*, `C-1` et `C-2`. **Une condition rangee
ailleurs que dans le bloc qui la fait lire ne compte pas.**

> **Ce que j'en tire, et ce n'est pas une clause neuve, c'est une application de `RES-1` a
> moi-meme.** Une condition d'acceptation que je pose en repondant a une question du developpeur ou
> de `theo-archi` **est recopiee dans le bloc *Mode de defaillance* de la story qu'elle gouverne**,
> avant que la branche ne s'ouvre. Sinon elle est lue une fois et jamais relue. **Le geste coute une
> ligne ; ne pas le faire a coute une story ce sprint.**

**Et un second fait, sur la part executable.** `theo-archi` a refuse d'encoder le controle d'objet de
`RES-1` (69 fiches, aucune ligne `Objet`, une regle ecrite aujourd'hui serait rouge sur une population
entierement exemptee). **Il avait raison, et ce sprint le confirme dans les deux sens** : la part
humaine a tenu sur `EMC-CP-07` et a lache sur `cp-emc-exclusion`, **sans qu'aucun automate puisse dire
lequel des deux**. Je ne demande toujours pas de regle ; je nomme la place ou une personne doit
regarder.

---

## 6. Ce dont je reste la seule garde, tous paquets de ce perimetre confondus

| Propriete | Garde executable ? |
|---|---|
| **`ZON-1`, `ZON-3`** - qu'aucun texte servi ne remplace l'image | **aucun**, et le defaut du jour le prouve : le garde-fou 5 d'`ADR-0080` compare l'**enonce** au libelle, **il ne lit pas le texte alternatif**, et aucun test du depot ne le confronte a quoi que ce soit |
| **`ZON-9`** - qu'un pictogramme se lise a la taille servie | **aucun**, et aucun n'est concevable |
| **`ZON-15` (b)** - qu'un libelle ne couvre pas le dessin | **aucun a ce jour** |
| **`EXC-R10`** - qu'un indice refute la conduite ecartee | **aucun.** Quatre jobs verts sur cinq indices sans rapport avec leur item |
| **`EMC-5`** - qu'aucune proposition vraie ne soit marquee fausse | **aucun** |
| **`FD-1`, `PLA-1`, `CAT-1`, `C-1`** | **aucun** |
| **L'exactitude des six faits de droit** | **aucun.** Reverifies contre leur source |
| **`BIN-1` mesuree sur la partie** | **aucun**, et je ne demande toujours pas de garde-fou |
| **La famille du bouton *"a personne"***, ecrite dans d'autres mots | **aucun.** La sonde neuve garde le prefixe litteral, pas la famille |
| **Le non-retour litteral du bouton *"a personne"*** | **de nouveau garde**, sur une population de onze items prouvee non vide par une methode voisine de la meme classe. **Je ne suis plus seule garde de ce point-la** |
| **`LON-1`, `LON-2`** | **mordues** par `ADR-0089` |

---

## 7. Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EMC-CP-07` a `couvert-valide`** | `camille-po` | Le seul mouvement de matrice de cet audit. **Je ne touche pas la matrice** |
| **`RSL-R1`, `RSL-R2`** | `camille-po` | Deux stories, ou une seule : elles touchent le meme item et un paquet se relit comme un tout |
| **`EXC-R10`** (bloquant), **`EXC-R11`**, **`EXC-R12`** | `camille-po` | **A traiter dans un meme geste** : les trois touchent la tache B |
| **`FRI-R1`** (bloquant) | `lea-dev`, via `sofia-sm` | Deux textes alternatifs et l'indice de l'item 1. **Je n'ecris aucun des trois** (`RES-2`) |
| **`FRI-R2`** (bloquant), **`FRI-R3`**, **`FRI-R4`** | `ines-design`, via `sofia-sm` | *Le pictogramme d'un moment se reconnait a la taille servie, sans son commentaire.* La forme est a elle |
| **Les boutons de zone couvrent le dessin** | `ines-design`, `theo-archi` | **Le fait le plus lourd de cet audit.** Pas un defaut de ce contenu ; il frappera tous les paquets de designation. `ZON-15` (b) le nomme a partir d'aujourd'hui |
| **`FRI-R5`** et la reponse a `ADR-0088` | `ines-design`, `theo-archi` | La clause B n'est pas rouverte ; **c'est la revelation sur l'ecran de manche qui doit se voir** |
| **`ZON-15`, `ZON-16` appliquees a `QLM-CP-11`** | `camille-po`, `ines-design` | Les deux moities de l'obstacle du plan : cadres a `ines-design`, libelles a moi. **Aucune ne suffit seule** |
| **L'accord du participe dans *"esperer qu'un adulte l'ait remarque"*** | `relecteur-pedagogique` | Orthographe et grammaire, pas mon registre |
| **La lettre de `EMC-CP-03` face a une mecanique a trois boutons** | `camille-po` | Deja route le 2026-09-10 ; **ce que la ligne couvre apres la refonte est desormais ecrit dans sa fiche**, comme je m'y etais engagee |
| **Ma propre lecon de format** (section 5) | moi-meme, a appliquer des le prochain arbitrage prealable | Une condition d'acceptation posee en repondant a une question est recopiee dans le bloc *Mode de defaillance* de la story qu'elle gouverne |

---

## 8. Allocations, et le manquement de poste que je declare

**Douze identifiants alloues** dans `squad/decisions/REGISTRE-identifiants.md` : `ZON-15`, `ZON-16`,
`FRI-R1` a `FRI-R5`, `RSL-R1`, `RSL-R2`, `EXC-R10` a `EXC-R12` - **deux prefixes neufs**, `FRI` et
`RSL`, verifies libres sur la table de la section 4 avant allocation.

**Recompte avant d'ecrire, sur le fichier que j'avais sous les yeux** : **611 identifiants distincts,
100 prefixes distincts, 0 doublon**, sous le motif du garde-fou lui-meme. **C'est exactement la ligne
d'etat courant que le fichier declarait : aucun ecart a signaler, donc rien a reparer a l'aveugle.**
Ligne portee a **623 / 102**, recomptee par le meme motif apres ecriture.

**Ce que ce total ne peut pas etre** : deux autres instances auditent s34 en parallele. Mon chiffre
est juste sur le fichier que j'ai sous les yeux et sur lui seul ; si l'une d'elles alloue, la CI
rougira a l'assemblage et la reparation sera arithmetique, sans qu'aucune allocation ni aucun sens ne
bouge. C'est la limite structurelle de `PAR-5`.

**Et je declare le manquement plutot que de le taire** : **j'ai ecrit depuis le checkout partage, pas
depuis un worktree dedie**, contrairement a la regle que `sofia-sm` a inscrite dans `squad/README.md`,
section 9, a l'ouverture meme de ce sprint - une regle ecrite pour fermer le faux vert emprunte que
`theo-archi` avait mesure sur son propre document. **Le mode de defaillance que cette regle ferme est
donc ouvert sur ce diff**, et c'est a la fusion qu'il se verra. Je l'ecris ici parce qu'un manquement
non ecrit n'a pas eu lieu, et parce que je viens de refuser une story pour une condition rangee au
mauvais endroit.

**Et le faux rouge que cette regle existe pour eviter est deja la, mesure, pour la troisieme fois.**
J'ai lance `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` sur le checkout partage apres mes
ecritures : **7 tests, 1 echec, huit griefs - et aucun des huit ne nomme un de mes fichiers.** Les
huit viennent de l'**instance C**, qui a ecrit `verdicts-nadia-instance-C.md` et
`FICHE-bandit-manchot-regles-securite-eps-cp.md` dans ce meme checkout sans allouer `EFF-R1` a
`EFF-R7` ni `SEC-R6`. **Mon propre diff est vert sur ce garde-fou**, y compris sur la clause 5 : la
ligne d'etat courant que j'ai portee a 623 / 102 est celle que le motif recompte sur la table.

**Ce que `sofia-sm` doit savoir avant de fusionner**, dit d'avance plutot qu'a decouvrir : l'instance
C devra allouer huit identifiants, dont le prefixe `EFF` qui est neuf. **La ligne d'etat courant sera
donc a recalculer une fois les trois branches assemblees**, et ce recalcul est arithmetique - aucune
allocation ajoutee ni retiree. C'est le prix annonce de la clause 5, paye a l'endroit prevu.

**Rien n'est commite, rien n'est pousse.**
