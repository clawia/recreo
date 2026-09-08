# Fiche de conformite - bandit-manchot-etapes-developpement-cm1

- **Mecanique** : bandit manchot.
- **Contenu** :
  `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-etapes-developpement.yaml`.
- **Entree de catalogue** : `bandit-manchot-etapes-developpement-cm1`, titre servi *"Naître,
  grandir, vieillir"*, consigne servie *"Lis bien, puis dis quelle étape vient après, ou ce qui la
  caractérise."*, `nombreDeManches: 10`.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-08** par
  `nadia-prof`, instance B, **au second audit**, apres avoir joue la reprise `d3fb6627`).
  **`SCI-CM1-08` ferme** : `non couvert` -> **`couvert-valide`**, sans mention de couverture
  partielle. Deux reserves neuves et datees, `DEV-R1` et `DEV-R2`, non bloquantes.
- **Histoire des verdicts, aucun efface** : **2026-09-07, REFUSE** - le paquet enseignait un fait
  faux (*"un jeune arbre a fini de grandir"*), se dementait lui-meme, et deux de ses items etaient
  un seul item sous deux noms d'animal ; le bloc entier de ce refus reste lisible ci-dessous.
  **2026-09-08, VALIDE SOUS RESERVE** apres reprise, second bloc en fin de fiche. **Un refus efface
  n'a aucune valeur de preuve.**
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md`,
  section 4.3 (ligne 8) et section 5 (`FAB-1`, `PLA-1`), et
  `squad/programme/sciences-et-technologie/competences-cm1.md`.
- **Commits audites** : `3181b5e6` (`[LEA] s31: S31-3 et S31-4 - second passage de sciences CM1,
  quatre lignes de plus`) le 2026-09-07, puis `d3fb6627` (`[LEA] s31: reprise SCI-CM1-08 - un arbre
  ne finit jamais de grandir, deux items cessent d'etre clones`), sur l'arbre `60bde989` du
  2026-09-08.
- **Derniere mise a jour** : 2026-09-08 (**second verdict de `nadia-prof` apres avoir joue la
  reprise**, s31, instance B).

## Competence visee

`Ordonner les étapes du développement d'un être vivant (naissance, croissance, reproduction,
vieillissement, mort).` (`SCI-CM1-08`, sciences et technologie, CM1, *Le vivant, sa diversite et les
fonctions qui le caracterisent*).

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **REFUSE**
- **Date** : 2026-09-07
- **Audit joue** : **oui**

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `3181b5e6`, worktree dedie `s31-nadia-B`, jar reconstruit depuis
ce worktree sur le JDK de la toolchain (21.0.10) et lance sur le **port 8081**. **Partie complete de
dix manches par appels HTTP reels, avec une erreur volontaire sur chaque manche**, ecran de fin
atteint, score 10. **Et j'ai lu les onze items.** **Ce n'est pas un audit degrade, et les deux
motifs du refus sont des faits de partie jouee : les deux items en cause m'ont ete servis dans la
meme partie.**

**Ce que l'audit ne couvre pas** : j'ai lu le HTML servi, pas la page rendue par un navigateur.
Couleur, contraste, taille et position ne sont pas juges ici, **et le refus n'en depend pas**.

### Ce qui a ete verifie, et ce qui tient

Je commence par ce qui tient, parce qu'un refus n'est utile que si l'equipe sait ce qu'elle doit
garder - et il y a beaucoup a garder ici.

- **La decoupe de la tache A est juste, et c'est le point que le lot revendiquait.** Aucun item ne
  demande de reconstituer une sequence entiere : chaque item de la tache A compare **deux termes** -
  quelle etape suit une etape decrite. **Je confirme, et je confirme aussi qu'aucune mention de
  couverture partielle n'est due de ce fait.** Le verbe du libelle est *ordonner*, et
  quelqu'un pourrait exiger plus tard une mention pour cette raison : **je la refuse d'avance.**
  Ma note du 2026-09-03 sur cette ligne dit mot pour mot *"Ordonner deux etapes se pose en texte,
  sans frise ni manipulation"*, et ma table du 2026-09-06 n'exigeait de mention d'avance que sur
  les lignes **6**, **14** et **20**. **La ligne 8 n'en fait pas partie, et je ne deplace pas mon
  poteau apres coup.** Un vrai geste d'ordonnancement serait une quatrieme mecanique, et `V3` la
  refuse au palier 1.
- **`CYC-1` - recouvrement CP / CM1, verifie et non pris sur parole.** J'ai lu
  `cp-qlm-cycle-de-vie.yaml` (`QLM-CP-02`) : le CP enseigne *naitre, se nourrir, grandir, avoir des
  petits, mourir*, et **ne nomme pas le vieillissement**, absent de son propre libelle. Ce paquet-ci
  dit *croissance* et *reproduction* la ou le CP dit *grandir* et *avoir des petits*, **dans le meme
  sens**, et ajoute le vieillissement. **Progression, pas redite, aucune contradiction.** La
  declaration de la developpeuse est exacte.
- **`FER-1`** : les cinq etapes du libelle occupent chacune la position de bonne reponse des la
  seule tache A - croissance (item 1), reproduction (2, 6), vieillissement (3), mort (4), naissance
  (5). **`FER-3`** : deux taches distinctes par l'operation mentale - *situer* une etape dans la
  suite d'un cas decrit, *caracteriser* une etape nommee. **`SCI-3`** : zero item numerique sur
  onze. **`BIN-1`** : sans objet, aucun item ne porte deux propositions (verifie par comptage : 11
  items, 3 propositions chacun).
- **Lisibilite et charge cognitive** : consigne d'une ligne nommant les deux taches (`DEC-0078`
  tenue), enonces d'une phrase, vocabulaire de CM1, une seule propriete par item.
- **Traitement de l'erreur** : le mecanisme fonctionne, l'indice s'affiche des la premiere erreur,
  la revelation debloque a la troisieme.
- **Ton** : la mort d'un vieux chien est traitee sobrement, sans dramatisation ni euphemisme.
  **C'est bien fait**, et sur une ligne dont le libelle impose ce mot, c'etait le risque principal.
- **Biais** : aucun stereotype, aucun foyer, aucune personne mise en cause.

### Premier motif du refus - le paquet enseigne un fait faux, et il se dement lui-meme

**L'item 3 dit** : *"Un jeune arbre **a fini de grandir** et produit pour la première fois des
graines."*

**Un arbre ne finit jamais de grandir.** Il s'accroit en hauteur et en circonference toute sa vie -
c'est ce que les cernes montrent, c'est ce qu'un enfant de CM1 observe sur l'arbre de sa cour d'une
annee sur l'autre, et c'est la difference entre un vegetal ligneux et un animal. La premiere
fructification d'un arbre ne marque **aucun** arret de croissance. **La premisse de l'item est
fausse**, et l'enfant doit l'accepter pour repondre.

**Et le paquet se dement lui-meme, dans un item que la meme partie m'a servi.** L'item 8 demande
*"Pendant l'étape de la reproduction, que fait un être vivant adulte ?"* et range **en distracteur,
donc en reponse fausse**, la proposition *"il arrête de grandir définitivement"*. C'est exactement
ce que la premisse de l'item 3 affirme.

**Ce que cela fait a l'enfant, et c'est mesurable sur une partie** : il lit a une manche que l'arbre
a fini de grandir quand il produit ses graines, puis on lui dit a une autre manche que *"il arrete
de grandir definitivement"* est faux pour l'etape de la reproduction. **Il est sanctionne pour avoir
retenu ce que le jeu lui a appris**, et l'indice qui suit (*"Se reproduire, c'est donner la vie à une
nouvelle génération."*) ne lui explique pas pourquoi sa reponse etait fausse - ce que `SCI-1` exige
sur cette matiere depuis le 2026-09-03.

**Ma regle sur ce point est ecrite et je l'applique sans amenagement** : *"Une erreur factuelle dans
un contenu scolaire est un motif de refus a elle seule, sans discussion."* Il y en a une, elle est
doublee d'une contradiction interne, et les deux se corrigent dans deux phrases.

### Second motif - deux des six items de la tache A sont un seul item, sous deux noms d'animal

**C'est la famille de defaut de mon refus de `SCI-CM1-17` a s30**, et je la retrouve ici a plus
petite echelle. Les items 2 et 6 :

| | Item 2 | Item 6 |
|---|---|---|
| Enonce | *"Un têtard grandit dans la mare et se transforme peu à peu en grenouille adulte [...] Quelle étape suivra, une fois adulte ?"* | *"Une chenille a fini de se transformer en papillon adulte [...] Quelle étape suivra bientôt dans sa vie ?"* |
| Bonne reponse | *la reproduction* | *la reproduction* |
| Distracteurs | *la naissance*, *le vieillissement* | *la naissance*, *le vieillissement* |

**Meme situation biologique** (une metamorphose larve vers adulte), **meme question**, **meme bonne
reponse**, **memes deux distracteurs**. Seul le nom de l'animal change. **Ce n'est pas une seconde
occasion d'apprendre, c'est le meme item servi deux fois** : des la seconde, l'enfant reconnait le
triplet et presse *la reproduction* sans lire.

**Ce que ce motif ne fait pas, et je le dis parce que la difference compte.** A la difference de
`SCI-CM1-17`, **l'arithmetique ne tombe pas** : les items 2 et 6 comptant pour un, le paquet sert
encore **dix items distincts**, ce qui tient exactement le plancher de `FER-2`, et les cinq etapes
restent toutes en bonne reponse. `FER-1`, `FER-2` et `FER-3` sont donc **tenues**. **Pris seul, ce
motif serait une reserve, pas un refus** ; il accompagne le premier, et il est a corriger dans le
meme geste.

**Ce n'est pas non plus un durcissement de `FAB-1`.** Ma clause du 2026-09-07 ne condamne pas
litteralement une paire : sa branche (a) compte une tache **entiere** dont tous les items partagent
leur bonne reponse. **Le poteau que j'invoque ici est celui de mon refus de s30**, ecrit le
2026-09-06, et il portait deja sur *"un item, servi plusieurs fois avec un autre nom d'animal, meme
question, meme bonne reponse, memes deux distracteurs"*. **C'est cette phrase-la, mot pour mot, que
la paire realise.**

### Troisieme point, nomme mais non retenu comme motif

Sur les cinq items de la tache B, **chaque item porte une proposition qui n'appartient a rien** -
*"il cesse de se nourrir"* pour la croissance, *"il grandit de plus en plus vite"* pour le
vieillissement, *"il redevient tout petit comme à sa naissance"* pour la mort. C'est `PLA-1`. Sur
ces items l'autre distracteur, lui, est un vrai candidat (*"il donne naissance à des petits"* pour
la croissance, *"il se reproduit pour la première fois"* pour le vieillissement), **donc la tache
n'est pas gagnee sans la competence** comme elle l'est sur `familles-materiaux`. **Je le nomme comme
un point a ameliorer, pas comme un motif de refus**, et je l'ecris pour qu'on ne le decouvre pas au
second passage.

### Ce qu'il faut pour que je ferme cette ligne, et rien d'autre

1. **Reecrire la premisse de l'item 3** pour qu'elle soit vraie. Le plus simple, et cela ne coute
   pas l'item : *"Un arbre produit pour la première fois des graines."* La bonne reponse ne bouge
   pas. **Ou** remplacer l'arbre par un animal dont la croissance s'arrete vraiment.
2. **Trancher la contradiction avec l'item 8** : soit la premisse de l'item 3 cesse d'affirmer un
   arret de croissance, soit *"il arrête de grandir définitivement"* cesse d'etre un distracteur.
   **Les deux ne peuvent pas coexister.**
3. **Faire des items 2 et 6 deux items differents** : changer la bonne reponse de l'un, ou changer
   l'operation. *"Une chenille sort de son oeuf. Quelle etape commence ?"* n'est pas le meme item
   que *"Un tetard devenu grenouille adulte : quelle etape suivra ?"*
4. **Facultatif, et je le dis comme tel** : remplacer les cinq propositions de remplissage de la
   tache B par des caracteristiques d'autres etapes, qui sont deja de vrais candidats.

**Tout le reste est acquis** : la decoupe en deux termes, `CYC-1`, le ton sur la mort, la lisibilite.
**Ce paquet est a deux phrases et un item de la validation.**

### Reserves

**Sans objet : un increment refuse n'ouvre pas de reserve.** Les motifs ci-dessus sont des motifs,
et `camille-po` en fait du backlog.

### Mentions de non-couverture opposables

**Sans objet.** `MNC-1` n'est due que sur un Done `VALIDE` ou `VALIDE SOUS RESERVE`.

### Ce dont je reste la seule garde sur ce paquet, et ce que le garde-fou neuf ne voit pas

**C'est la section la plus utile de cette fiche, parce que le premier des deux garde-fous d'`ADR-0086`
a ete ecrit contre exactement ce defaut-ci et ne l'a pas vu.**

J'ai lu sa clause 1 avant de l'accuser. Elle forme des groupes d'items coules dans le meme moule de
propositions, et elle ne signale un groupe que s'il compte **au moins trois items** et si leurs
bonnes reponses tiennent dans un moule d'**au moins trois mots**
(`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`, constantes `ITEMS_POUR_UNE_REPETITION`
et `MOTS_POUR_UNE_PHRASE`). **La paire des items 2 et 6 passe sous les deux seuils a la fois** :
elle compte deux items, et sa bonne reponse *"la reproduction"* compte deux mots. **Le garde-fou est
vert et il a raison de l'etre au regard de son propre critere** ; c'est son critere qui est plus
etroit que la famille de defaut, et cela se mesure sur ces deux seuils, pas sur une impression.

`ADR-0032` (deux items d'un meme paquet ne presentent jamais le meme ecran) ne le voit pas non plus,
et pour un motif different : son identite est le couple **enonce + jeu de propositions**, et les
deux enonces different par le nom de l'animal.

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude de chaque fait de biologie**, y compris dans une **premisse d'enonce** | **aucun.** C'est le premier motif de ce refus, et il a traverse une CI entierement verte |
| **La coherence interne d'un paquet** - qu'une proposition rangee en distracteur ne soit pas affirmee ailleurs dans le meme paquet | **aucun.** C'est une propriete de paires d'items, textuelle mais semantique. **Route a `theo-archi`** - je dis la propriete, jamais la forme |
| **Un item clone en paire** (deux items, bonne reponse de moins de trois mots) | **aucun** : sous les deux seuils de la clause 1 d'`ADR-0086`. **Route a `theo-archi`** avec les deux chiffres, pour qu'il decide s'ils sont ajustables sans faux rouge |
| **`PLA-1`** - qu'une proposition soit un candidat et non un remplissage | **aucun**, et `ADR-0086` a mesure qu'elle n'est pas encodable |
| **`CYC-1`** vers le CP | **aucun.** Verifiee a la main, par lecture de `cp-qlm-cycle-de-vie.yaml` |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`SCI-CM1-08` reste `non couvert`** | `camille-po` | La ligne ne ferme pas. **Je ne touche pas la matrice** |
| **Les trois corrections obligatoires de la section *"Ce qu'il faut"***, plus la quatrieme facultative | `camille-po`, via `sofia-sm` | En faire une story. **Le refus ne bloque pas le sprint suivant** : deux phrases et un item. Regime de reaudit : **rejeu borne, 0,5 pour le paquet** (`PAR-10`) |
| **La coherence interne d'un paquet** (un distracteur affirme ailleurs comme vrai) | `theo-archi` | Propriete de paires d'items. Je dis la propriete, pas la forme |
| **Les deux seuils de la clause 1 d'`ADR-0086`** - trois items, trois mots - **laissent passer une paire clonee a bonne reponse courte** | `theo-archi` | Fait mesure sur ce paquet, avec ses deux chiffres. **Je ne prescris ni un seuil, ni une forme** : je dis ce que la clause ne couvre pas, et il decide si l'elargir coute un faux rouge |

---

## Second bloc de validation pedagogique - nadia-prof UNIQUEMENT, apres reprise

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. **Le bloc de refus qui precede
> n'est pas efface** : c'est lui qui prouve que la porte s'est fermee avant de s'ouvrir.

- **Verdict** : **VALIDE SOUS RESERVE** - **le refus du 2026-09-07 est leve**
- **Date** : 2026-09-08
- **Audit joue** : **oui**
- **Ce qui a ete rejoue** : la reprise `d3fb6627` sur l'arbre `round-two` a `60bde989`.

### Conditions du second audit

**J'ai joue, cinq parties.** Worktree dedie `s31-nadia-B2`, jar reconstruit depuis ce worktree,
reacteur complet, **JDK 21.0.10 - version rapportee par la machine virtuelle qui execute le jar, pas
par l'entree de toolchain** (`ADR-0071`) -, lance sur le **port 8081**.

- une partie de dix manches avec **une erreur volontaire sur chaque manche**, ecran de fin atteint,
  score 10 ;
- une partie ou **je me suis trompee trois fois par manche**, pour lire la revelation item par item ;
- **trois parties supplementaires** pour observer le tirage, en relevant les dix items servis a
  chaque fois - onze items pour dix manches, donc un ecarte par partie ;
- l'item de l'arbre presse **en cible sur *la croissance***, trois fois, jusqu'a la revelation ;
- **et j'ai relu les onze items dans le fichier de contenu.**

**La porte technique est rouge sur cet arbre, et j'ai verifie que ce rouge ne me concerne pas avant
de prononcer.** Le seul test en echec (`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`)
reproche a un rapport de qualite de citer un identifiant fictif **en preuve** ; aucun paquet, aucun
item, aucune fiche n'est en cause, et `theo-archi` le repare en parallele. Le jeu se lance, se joue
et se termine : **il est evaluable, et je prononce.**

**Une correction de methode, sur moi et contre moi, avant tout le reste.** Ma premiere tentative de
partie a produit un ecran qui refusait la bonne reponse **revelee** : mon outil renvoyait au serveur
la valeur du bouton telle qu'elle est **echappee dans le HTML** - l'apostrophe sous sa forme
`&#39;` -, donc une chaine que l'enfant ne peut pas presser. **Le defaut etait dans mon outil, pas
dans le produit** : corrige, rejoue, la bonne reponse revelee est acceptee a tous les coups. Je
l'ecris parce que j'ai failli enregistrer contre ce paquet un blocage qui n'existe pas.

**Ce que l'audit ne couvre pas** : j'ai lu le HTML servi, pas la page rendue par un navigateur.
Couleur, contraste, taille et position ne sont pas juges ici, **et ni la levee du refus ni les deux
reserves n'en dependent**.

### Mes trois motifs de refus, recomptes un par un

**Motif 1 - le fait faux. Paye.** L'enonce disait *"Un jeune arbre **a fini de grandir** et produit
pour la première fois des graines."* Il dit maintenant *"Un arbre produit pour la première fois des
graines. Quelle étape suivra, bien plus tard, dans sa vie ?"*. **La premisse fausse n'est plus la, et
la bonne reponse n'a pas bouge** : c'est la correction que j'avais proposee, au mot pres.

**Motif 2 - la contradiction interne avec l'item de la reproduction. Paye.** Cet item range toujours
*"il arrête de grandir définitivement"* en reponse fausse, **et plus aucun item du paquet n'affirme
le contraire** : je l'ai cherche sur les onze items, la seule autre mention d'une croissance sans fin
est *"il continue de grandir sans jamais s'arrêter"*, distracteur de l'item de la mort, ou elle est
fausse aussi. **La contradiction n'a plus d'objet.**

**Motif 3 - les deux items clones. Paye.** Les items du tetard et de la chenille etaient un seul item
sous deux noms d'animal : meme question, meme bonne reponse *"la reproduction"*, memes deux
distracteurs. L'item de la chenille est reecrit sur une autre transition - *"Une chenille vient tout
juste de sortir de son oeuf. Quelle étape de sa vie vient de commencer ?"*, bonne reponse *la
naissance*, distracteurs *le vieillissement* et *la mort*. **Question differente, bonne reponse
differente, distracteurs differents : le clone que j'ai refuse n'existe plus**, au regard du poteau
que j'avais ecrit le 2026-09-06 et cite dans le bloc precedent - *"meme question, meme bonne reponse,
memes deux distracteurs"*.

### `DEV-R1` - ce que la reprise a deplace, reserve neuve et datee du 2026-09-08

**Mesure sur trois parties consecutives, et le resultat est le meme les trois fois.** Le paquet porte
maintenant **deux items qui enseignent le meme fait** : *sortir de l'oeuf, c'est la naissance*.

| | L'item du poussin | L'item de la chenille |
|---|---|---|
| Enonce | *"Un oeuf de poule se trouve dans le nid depuis plusieurs jours. Quelle étape commence quand le poussin sort de l'oeuf ?"* | *"Une chenille vient tout juste de sortir de son oeuf. Quelle étape de sa vie vient de commencer ?"* |
| Bonne reponse | *la naissance* | *la naissance* |
| Distracteurs | *la croissance*, *la reproduction* | *le vieillissement*, *la mort* |

**Les deux ont ete servis dans la meme partie trois fois sur trois**, et l'item de la tache B sur la
naissance avec eux : **trois des dix manches ont *la naissance* pour bonne reponse, dont deux sur le
meme fait**. L'enfant qui a fait le poussin reconnait la sortie de l'oeuf et presse sans lire.

**Pourquoi une reserve et pas un maintien du refus, et la difference compte.** Les deux items ne
partagent **pas** leurs distracteurs, donc le triplet que j'avais condamne n'est pas reconstitue. Et
les distracteurs de l'item de la chenille - *le vieillissement*, *la mort*, pour un animal qui vient
d'eclore - **n'appartiennent a rien** (`PLA-1`), ce qui en fait une manche presque gratuite plutot
qu'un piege. L'arithmetique tient : dix items distincts, plancher de `FER-2` atteint, les cinq etapes
toujours en bonne reponse. **Ce n'est pas mon poteau du 2026-09-06 : c'est une reserve du
2026-09-08.**

**Et je nomme le garde-fou neuf, parce qu'il est vert et que le defaut est present.** Le test ajoute
par la reprise ne signale une paire d'items de la tache de transition que si elle partage **a la
fois** sa bonne reponse **et** ses deux distracteurs. La paire poussin/chenille partage la bonne
reponse et le fait enseigne, pas les distracteurs : **il passe.** C'est la meme chose que les deux
seuils de la clause 1 d'`ADR-0086`, un cran plus bas - **un test qui operationnalise plus etroitement
que la famille de defaut qu'il vise**. Je le dis pour que l'equipe ne croie pas couvert ce qui ne
l'est pas.

**Pour lever `DEV-R1`** : que l'un des deux items d'eclosion porte un autre fait - une autre
transition, ou une autre etape en bonne reponse. Un item.

### `DEV-R2` - le second effet du retrait de la premisse, reserve neuve et datee du 2026-09-08

**Fait de partie jouee, presse en cible.** L'item de l'arbre offre *le vieillissement* (juste), *la
naissance* et ***la croissance***. J'ai presse *la croissance* trois fois : *"Ce n'est pas ca"*, puis
la revelation, avec pour seul retour *"Après avoir pu se reproduire, un être vivant vieillit peu à
peu."*

**Or c'est moi qui ai ecrit, la veille et contre ce paquet, qu'un arbre ne finit jamais de grandir.**
Tant que l'enonce affirmait *"a fini de grandir"*, *la croissance* etait un distracteur legitimement
faux. **La premisse retiree, il est devenu, pour un arbre precisement, un enonce vrai range en
reponse fausse** : l'arbre continue bien de grandir apres sa premiere fructification. Dans la
sequence enseignee, *le vieillissement* est la bonne reponse et l'item fonctionne ; mais l'enfant qui
sait ce que le programme lui fait observer sur l'arbre de la cour est sanctionne sans explication -
`SCI-1` regarde ce retour.

**Reserve, pas refus, et le motif de ce classement est net** : le paquet n'affirme plus rien de faux,
c'est un distracteur qui est devenu equivoque **sur ce vivant-la**. Et mon refus offrait deja la
sortie : *"Ou remplacer l'arbre par un animal dont la croissance s'arrete vraiment."* La branche
choisie etait celle que j'avais appelee *"le plus simple"* ; **sa consequence est a moi autant qu'a
la developpeuse**, et c'est pourquoi je l'ecris comme une reserve neuve et datee plutot que comme un
durcissement de l'ancienne.

**Pour lever `DEV-R2`** : que cet item porte un vivant dont la croissance s'arrete vraiment, **ou**
que *la croissance* cesse d'y etre un distracteur.

### Le troisieme point de mon refus, nomme mais jamais retenu comme motif : il n'a pas bouge

Les cinq items de la tache B portent chacun une proposition qui n'appartient a rien - *"il cesse de
se nourrir"*, *"il grandit de plus en plus vite"*, *"il redevient tout petit comme à sa naissance"*.
**Je l'avais nomme comme un point a ameliorer et non comme un motif, et je le laisse tel quel** : sur
chacun de ces items l'autre distracteur est un vrai candidat, donc la tache n'est pas gagnee sans la
competence. **Je n'en fais pas une reserve apres coup.**

### Reserves ouvertes par ce Done

| Reserve | Objet | Regime |
|---|---|---|
| **`DEV-R1`** | Deux items enseignent le meme fait (*sortir de l'oeuf, c'est la naissance*) sous deux noms d'animal, servis ensemble 3 fois sur 3 ; le garde-fou neuf est vert parce qu'il exige aussi des distracteurs identiques | **rejeu borne, 0,5 pour le paquet entier** (`PAR-10`). Les deux reserves voyagent ensemble |
| **`DEV-R2`** | Sur l'item de l'arbre, *la croissance* est range en reponse fausse alors qu'un arbre continue de grandir toute sa vie ; le retour n'en dit rien | idem, **sans un centime de plus** |

**Les deux sont non bloquantes** : le Done est prononce, la ligne ferme, et `camille-po` en fait une
story. Allouees dans `squad/decisions/REGISTRE-identifiants.md` dans le meme commit que ce bloc
(`PAR-5`, `ADR-0085`).

### Mentions de couverture partielle

**Aucune n'est due sur cette ligne, et je le retranche d'avance une seconde fois.** Le verbe du
libelle est *ordonner*, et ma note du 2026-09-03 dit mot pour mot *"ordonner deux etapes se pose en
texte, sans frise ni manipulation"* ; ma table du 2026-09-06 n'exigeait de mention que sur les lignes
**6**, **14** et **20**. **La ligne 8 n'en fait pas partie. Je ne deplace pas mon poteau, pas plus au
second audit qu'au premier.**

### Mentions de non-couverture opposables

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element
> quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice, proposition. Cette
> propriete est sortie de la porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
> paquet.

### Ce dont je reste la seule garde, mis a jour au second audit

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude de chaque fait de biologie**, y compris dans une **premisse d'enonce** | **partiel, et seulement contre la chaine exacte.** Le test ajoute interdit la formule *"a fini de grandir"* dans l'enonce de l'item de l'arbre. Une premisse fausse neuve, ailleurs, passerait |
| **Qu'une proposition rangee en distracteur ne soit pas vraie du vivant decrit** | **aucun.** C'est `DEV-R2`, et c'est ma propre correction du 2026-09-07 qui l'a fait apparaitre |
| **Que deux items n'enseignent pas le meme fait sous deux habillages** | **aucun.** Le test ajoute exige bonne reponse **et** distracteurs identiques ; la paire poussin/chenille passe dessous, mesuree ici. **Route a `theo-archi`** - je dis la propriete, jamais la forme |
| **La coherence interne d'un paquet** - une proposition rangee en distracteur n'est pas affirmee ailleurs | **aucun de portee generale.** Le defaut de s31 est corrige dans son contenu, pas garde |
| **`PLA-1`** | **aucun**, non encodable (`ADR-0086`) |
| **`CYC-1`** vers le CP | **aucun.** Verifie a la main au premier audit ; les items concernes n'ont pas bouge, je ne le rejuge pas |

### Etat des verdicts et des reserves de ce jeu au 2026-09-08

| Date | Verdict | Etat |
|---|---|---|
| 2026-09-07 | **REFUSE** | **leve le 2026-09-08.** Son bloc reste lisible ci-dessus, non efface |
| 2026-09-08 | **VALIDE SOUS RESERVE** | en vigueur. `SCI-CM1-08` ferme |

| Reserve | Ouverte le | Etat |
|---|---|---|
| `DEV-R1` | 2026-09-08 | **ouverte**, non bloquante |
| `DEV-R2` | 2026-09-08 | **ouverte**, non bloquante |

### Ce que je route, au second audit

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`SCI-CM1-08` : `non couvert` -> `couvert-valide`**, sans mention de couverture partielle | `camille-po` | **Je ne touche pas la matrice** : le mouvement se fait sur la foi de ce bloc |
| **`DEV-R1` et `DEV-R2`** | `camille-po`, via `sofia-sm` | Une story. **Rejeu borne, 0,5 pour le paquet entier** (`PAR-10`) |
| **Deux items d'un meme paquet n'enseignent pas le meme fait sous deux habillages** | `theo-archi` | Le test neuf exige bonne reponse **et** distracteurs identiques ; la paire mesuree passe dessous. Je dis la propriete, pas la forme |
