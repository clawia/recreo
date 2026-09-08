# Reaudit de `nadia-prof` - s31, instance B : les deux paquets de sciences CM1 refuses le 2026-09-07

- **Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
- **Date** : 2026-09-08.
- **Ce que ce document juge** : les deux reprises de `lea-dev` sur les deux paquets que j'ai
  **refuses** la veille (`verdicts-nadia-instance-B.md`, arbre `3181b5e6`) -
  `cm1-sciences-familles-materiaux` (`SCI-CM1-11`) et `cm1-sciences-etapes-developpement`
  (`SCI-CM1-08`). **Rien d'autre.**
- **Arbre reaudite** : `round-two` a `60bde989`. Worktree dedie `s31-nadia-B2`, **port 8081**,
  scratchpad propre a cette instance.
- **Perimetre, et je le redis parce qu'il n'a pas bouge** : l'EMC CP, les deux reprises
  `MEL-R1/R2/R3` et `OBT-R1/R2` sont a l'**instance A**, convoquee en parallele ; je n'y touche pas,
  y compris sur la couture. Mes deux verdicts `VALIDE` de la veille (`SCI-CM1-01`, `SCI-CM1-07`) sont
  rendus et **ne se rejugent pas**.
- **Ce document ne modifie ni la matrice, ni le backlog, ni aucun contenu.** Il prononce deux
  verdicts et les route. La conclusion sera reprise dans le `review.md` du sprint, que `sofia-sm`
  ecrit a la cloture.

---

## 0. Comment j'ai reaudite, et sous quelle porte technique

**J'ai joue les deux paquets, pour de vrai, comme au premier passage.**

**La porte technique est rouge sur cet arbre, et j'ai verifie que ce rouge ne me concerne pas avant
de mesurer quoi que ce soit.** `sofia-sm` me l'a annonce ; je ne l'ai pas cru sur parole, j'ai lu le
garde-fou en cause (`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`) et ce qu'il reproche :
un identifiant fictif cite **en preuve** dans un rapport de qualite, que le balayage ne distingue pas
d'un identifiant **employe**. Aucun paquet, aucun item, aucune fiche n'est en cause. **Je prononce
donc**, et je dis pourquoi je m'y autorise : ma regle est qu'un jeu qui plante n'est pas evaluable -
elle porte sur **l'increment que j'audite**, pas sur un test de gouvernance textuelle qui vit dans un
autre fichier. Les deux jeux se lancent, se jouent et se terminent, je l'ai constate dix manches par
dix manches.

**Ce que j'ai fait, en detail** :

- jar reconstruit depuis mon propre worktree, reacteur complet, tests sautes
  (`-DskipTests`, puisque `verify` ne produit pas le jar sur cet arbre), lance sur le **port 8081** ;
- **JDK 21.0.10, version rapportee par la machine virtuelle qui execute le jar** (ligne de demarrage
  de l'application), pas par l'entree de toolchain - `ADR-0071` ;
- **`cm1-sciences-familles-materiaux` : cinq parties completes.** Une partie de dix manches avec une
  erreur volontaire sur chaque manche, ecran de fin atteint, score 10. Une partie ou **je me suis
  trompee trois fois sur chacune des dix manches**, pour lire la revelation dix fois. Trois parties
  supplementaires pour observer le tirage. Plus deux items presses en cible (le tuyau, la
  combinaison) ;
- **`cm1-sciences-etapes-developpement` : cinq parties completes.** Meme protocole, ecran de fin
  atteint, score 10, revelation atteinte item par item, plus l'item de l'arbre presse en cible sur
  *"la croissance"* jusqu'a la revelation ;
- **et j'ai relu les vingt-deux items dans les deux fichiers de contenu**, y compris ceux que le
  tirage laisse de cote - il en laisse exactement un par partie, onze items pour dix manches.

**Audit degrade sur un point, le meme que la veille, et je le declare** : j'ai joue par requetes HTTP
reelles, donc j'ai lu le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les
textes, l'ordre des ecrans, le tirage, l'enchainement apres une erreur, apres trois, et l'ecran de
fin. Cela **ne** couvre ni la couleur, ni le contraste, ni la taille, ni la position. **Aucune des
deux levees de refus, et aucune des quatre reserves, ne repose sur un jugement visuel.**

**Une correction de methode, sur moi et contre moi, avant tout le reste.** Ma premiere tentative de
partie sur `etapes-developpement` a produit un ecran qui refusait la bonne reponse **revelee** :
j'avais renvoye au serveur la valeur du bouton telle qu'elle est **echappee dans le HTML**
(l'apostrophe sous sa forme `&#39;`), donc une chaine que l'enfant ne peut pas presser. **Le defaut
etait dans mon outil, pas dans le produit** : corrige, rejoue, et la bonne reponse revelee est
acceptee a tous les coups. Je l'ecris parce que j'ai failli enregistrer contre ce paquet un blocage
qui n'existe pas, et que c'est exactement la famille d'erreur que je me suis engagee a verifier avant
de m'appuyer sur un constat.

---

## 1. Les deux verdicts

| Ligne | Paquet | Verdict du 2026-09-07 | Verdict du 2026-09-08 | En un motif |
|---|---|---|---|---|
| **`SCI-CM1-11`** - familles de materiaux et proprietes | `cm1-sciences-familles-materiaux` | **REFUSE** | **VALIDE SOUS RESERVE** - **refus leve** | **Mes quatre motifs sont payes, les quatre.** Les quatre proprietes du libelle sont desormais servies chacune par un item ou l'enfant a une alternative serieuse a ecarter. Deux reserves neuves, `MAT-R1` et `MAT-R2`, non bloquantes |
| **`SCI-CM1-08`** - etapes du developpement | `cm1-sciences-etapes-developpement` | **REFUSE** | **VALIDE SOUS RESERVE** - **refus leve** | **Mes trois motifs sont payes, les trois.** Le fait faux est retire, la contradiction interne n'a plus d'objet, la paire clonee n'existe plus. Deux reserves neuves, `DEV-R1` et `DEV-R2`, non bloquantes |

**Les lignes qui ferment : deux.** `SCI-CM1-11` et `SCI-CM1-08`, toutes deux **sans mention de
couverture partielle** - ma table du 2026-09-06 n'en exigeait que sur les lignes **6**, **14** et
**20**, et **je ne deplace pas ce poteau apres coup**, pas plus aujourd'hui qu'hier.

**Total de sciences CM1 sur ce sprint : quatre lignes sur quatre.** Mon attente d'avant sprint etait
de 3 a 4 ; elle est atteinte, mais **en deux temps et au prix de deux refus**, ce qui n'est pas la
meme chose que de l'atteindre du premier coup. Je le note parce que la difference se paie en marge de
rejeu, pas en couverture.

---

## 2. `SCI-CM1-11` - mes quatre motifs, recomptes un par un

**Le titre du commit `0e162501` n'annonce qu'un de mes quatre motifs** (les distracteurs, `PLA-1`).
J'ai donc verifie les trois autres **nommement**, sans me fier au titre. Ils y sont tous les quatre.

### 2.1 Motif principal - les quatre proprietes de `FER-1`, servies par un item qui teste ce qu'il annonce

C'etait mon motif bloquant : *"sur les quatre proprietes que `FER-1` oblige a servir, une seule est
servie par un item qui teste ce qu'il annonce"*. Etat apres reprise, releve item par item, sur les
propositions telles qu'elles m'ont ete **servies a l'ecran** :

| Propriete du libelle | Item | Bonne reponse | Distracteurs servis | Un enfant en envisage-t-il un ? |
|---|---|---|---|---|
| **resistance** | le pont | le metal | **le bois**, le verre | **oui, le bois.** Un pont en bois existe, et il faut savoir pourquoi il ne suffit pas pour des camions. C'est mot pour mot la correction que j'avais nommee |
| **isolation** | le manche de poele | le bois | **le metal**, le verre | **oui, le metal**, et c'est la conception fausse utile. Item inchange : c'etait deja le bon |
| **souplesse** | le vetement | le tissu | **le cuir**, le metal | **oui, le cuir.** Un vetement en cuir existe et il est bien moins souple : l'enfant doit comparer deux candidats reels |
| **impermeabilite** | la combinaison de plongee | le plastique | **le tissu**, le bois | **oui, le tissu.** C'est la reponse spontanee sur un vetement |

**Quatre proprietes sur quatre, contre une sur quatre la veille.** `FER-1` n'est plus seulement tenue
a la lettre : son objet est atteint. **C'est la barre que j'avais ecrite, elle est franchie, et le
refus tombe.**

### 2.2 Deuxieme motif - `PLA-1` sur les trois items sans alternative serieuse

Ma demande etait : *"sur les items 6, 10 et 11, et de preference aussi 8, remplacer au moins un
distracteur par un materiau qu'un enfant choisirait vraiment"*. **Les trois items obligatoires sont
corriges** (le bois pour le pont, le plastique pour la bouteille, le cuir pour le vetement).
**L'item du tuyau d'arrosage - mon *de preference* - ne l'est pas** : il offre encore *le verre* et
*le metal*, un tuyau d'arrosage en verre n'etant pas une reponse d'enfant. Je l'ai presse en cible et
lu jusqu'a la revelation.

**Ce point etait explicitement facultatif dans mon refus, et il reste facultatif aujourd'hui.** Il
devient la reserve **`MAT-R2`**, non bloquante. **Une barre qu'on releve apres coup fait de ma porte
un caprice.**

### 2.3 Troisieme motif - l'indice qui ne voulait rien dire

L'ancien indice disait *"Ce materiau ne laisse jamais passer l'eau, meme liquide."* - une eau qui le
serait moins n'existe pas. **Il est reecrit** : *"Ce matériau ne garde jamais aucune odeur ni aucun
goût, même après des centaines de lavages."* **C'est vrai** - le verre est chimiquement inerte et ne
retient ni gout ni odeur, a la difference du plastique - **et c'est comprehensible par un enfant de
CM1**. Lu a l'ecran, apres erreur volontaire, sur trois parties. **Motif paye.**

### 2.4 Quatrieme motif - la sixieme famille jamais enseignee

Le caoutchouc etait servi en bonne reponse a l'item de la combinaison sans avoir jamais ete enseigne
comme famille. **Il est remplace par le plastique, qui est enseigne en tache A** (le gobelet). Mon
refus offrait exactement ces deux issues - enseigner la sixieme famille, ou revenir a une famille
deja enseignee - et c'est la seconde qui a ete prise. **Motif paye.**

**Un point nomme, non retenu, et je le nomme pour qu'on ne le decouvre pas plus tard.** La reponse la
plus exacte pour une combinaison de plongee serait le **caoutchouc** (neoprene), et le paquet dit
maintenant *le plastique*. Au niveau du cycle 3, ou les familles enseignees sont bois, metal, verre,
plastique et tissu, ranger un polymere de synthese dans les matieres plastiques est **defendable et
non fautif**. Surtout : **c'est l'issue que j'ai moi-meme autorisee**, je ne vais donc pas la
reprocher. Si un jour le paquet enseigne six familles, cet item devra recuperer le caoutchouc.

**Le cuir, introduit en distracteur a l'item du vetement, n'est pas le meme cas** : mon motif portait
sur une famille servie **en bonne reponse** sans etre enseignee, et c'est moi qui ai propose le cuir.
Un distracteur n'a pas besoin d'etre une famille du programme pour etre un candidat que l'enfant
envisage.

### 2.5 Ce que la reprise a introduit, et qui devient `MAT-R1` - reserve neuve et datee

**C'est un fait de partie jouee, mesure sur quatre parties consecutives.** L'item de la bouteille est
devenu :

> *"On veut fabriquer une bouteille réutilisable des centaines de fois, qui ne doit jamais garder le
> goût du dernier liquide versé. **Il faut un matériau imperméable.** Lequel choisir ?"* - bonne
> reponse *le verre*, distracteurs *le plastique* et *le bois*.

Et l'item de la combinaison, dans **la meme partie** :

> *"On veut fabriquer une combinaison de plongée qui ne doit jamais laisser entrer l'eau. **Il faut
> un matériau imperméable.** Lequel choisir ?"* - bonne reponse **le plastique**.

**Les deux items ont ete servis dans la meme partie quatre fois sur quatre** (onze items pour dix
manches, un seul item ecarte par partie). L'enfant lit donc deux fois la meme phrase - *il faut un
materiau impermeable* - et **le plastique est juste a l'une et faux a l'autre**. Presse *le
plastique* sur la bouteille, il recoit *"Ce n'est pas ca"* et un indice qui parle du gout, **jamais
du fait que le plastique est impermeable lui aussi**. Verifie a l'ecran jusqu'a la revelation.

**Deux consequences, et je les separe** :

1. **Le critere annonce n'est plus celui qui departage.** L'impermeabilite elimine le bois ; c'est la
   retention du gout qui elimine le plastique. L'item **reste solvable** parce que l'enonce dit la
   retention du gout - c'est ce qui en fait une reserve et non un refus - mais il porte
   **deux criteres**, la ou ma note du 2026-09-03 sur cette ligne, que j'avais verifiee tenue sur les
   onze items au premier audit, dit *"une propriete par item, jamais deux : la charge cognitive
   double sinon"*.
2. **Le risque d'apprentissage est nommable** : un enfant peut en repartir avec *"le plastique n'est
   pas impermeable"*, qui est faux, et que l'autre item du meme paquet dement.

**Pourquoi c'est une reserve et pas un refus, dit franchement.** Ma section *"Ce qu'il faut pour que
je ferme cette ligne, et rien d'autre"* offrait deux issues sur cet item : *que l'enonce dise ce qui
departage le verre du plastique*, **ou** *que le verre cesse d'etre la reponse*. La premiere a ete
prise, a la lettre. Le resultat me montre quelque chose que je ne voyais pas quand j'ai ecrit cette
phrase - **je l'ecris donc comme une reserve nouvelle et datee, pas comme un durcissement de
l'ancienne**. C'est la difference entre voir plus loin et deplacer ses poteaux.

**Ce qu'il faut pour lever `MAT-R1`, actionnable sans me redemander ce que je voulais dire** : que
l'item de la bouteille cesse d'annoncer *l'impermeabilite* comme critere - par exemple en n'annoncant
que la propriete qui departage reellement - **ou** que le verre cesse d'y etre la bonne reponse. Une
phrase.

---

## 3. `SCI-CM1-08` - mes trois motifs, recomptes un par un

### 3.1 Premier motif - le fait faux

L'enonce disait *"Un jeune arbre **a fini de grandir** et produit pour la première fois des
graines."* Il dit maintenant : *"Un arbre produit pour la première fois des graines. Quelle étape
suivra, bien plus tard, dans sa vie ?"*. **La premisse fausse n'est plus la, et la bonne reponse n'a
pas bouge.** C'est la correction que j'avais proposee, au mot pres. **Motif paye.**

### 3.2 Deuxieme motif - la contradiction interne avec l'item de la reproduction

L'item de la reproduction range toujours *"il arrête de grandir définitivement"* en reponse fausse.
**Plus aucun item du paquet n'affirme le contraire** : je l'ai cherche sur les onze items, la seule
autre mention de croissance sans fin est *"il continue de grandir sans jamais s'arrêter"*, distracteur
de l'item de la mort, ou elle est fausse aussi. **La contradiction n'a plus d'objet. Motif paye.**

### 3.3 Troisieme motif - les deux items clones

Les items du tetard et de la chenille etaient un seul item sous deux noms d'animal : meme question,
meme bonne reponse *"la reproduction"*, memes deux distracteurs. **L'item de la chenille est reecrit
sur une autre transition** - *"Une chenille vient tout juste de sortir de son oeuf. Quelle étape de
sa vie vient de commencer ?"*, bonne reponse *la naissance*, distracteurs *le vieillissement* et *la
mort*. **Question differente, bonne reponse differente, distracteurs differents. Le clone que j'ai
refuse n'existe plus. Motif paye**, au regard du poteau que j'avais ecrit le 2026-09-06 et cite dans
ma fiche : *"meme question, meme bonne reponse, memes deux distracteurs"*.

### 3.4 Ce que la reprise a deplace, et qui devient `DEV-R1` - reserve neuve et datee

**Mesure sur trois parties consecutives, et le resultat est le meme les trois fois.** Le paquet
contient maintenant **deux items qui portent le meme fait** : *sortir de l'oeuf, c'est la naissance*.

| | L'item du poussin | L'item de la chenille |
|---|---|---|
| Enonce | *"Un oeuf de poule se trouve dans le nid depuis plusieurs jours. Quelle étape commence quand le poussin sort de l'oeuf ?"* | *"Une chenille vient tout juste de sortir de son oeuf. Quelle étape de sa vie vient de commencer ?"* |
| Bonne reponse | *la naissance* | *la naissance* |
| Distracteurs | *la croissance*, *la reproduction* | *le vieillissement*, *la mort* |

**Les deux ont ete servis dans la meme partie trois fois sur trois**, et l'item de la tache B sur la
naissance avec eux : **trois des dix manches ont *la naissance* pour reponse**, dont deux sur le meme
fait. L'enfant qui a fait le poussin reconnait la sortie de l'oeuf et presse sans lire.

**Pourquoi une reserve et pas un maintien du refus, et la difference compte.** Les deux items ne
partagent **pas** leurs distracteurs, donc le triplet que j'avais condamne n'est pas reconstitue ; et
les distracteurs de l'item de la chenille - *le vieillissement*, *la mort* pour un animal qui vient
d'eclore - **n'appartiennent a rien** (`PLA-1`), ce qui en fait une manche presque gratuite plutot
qu'un piege. L'arithmetique tient : dix items distincts, plancher de `FER-2` atteint, les cinq etapes
toujours en bonne reponse. **Ce n'est pas mon poteau du 2026-09-06, c'est une reserve du 2026-09-08.**

**Et je nomme le garde-fou neuf, parce qu'il est vert et que le defaut est present.** Le test ajoute
par la reprise ne signale une paire que si elle partage **a la fois** sa bonne reponse **et** ses deux
distracteurs. La paire poussin/chenille partage la bonne reponse et le fait enseigne, pas les
distracteurs : **il passe.** C'est la troisieme fois de ce sprint que je constate la meme chose - un
test operationnalise plus etroitement que la famille de defaut qu'il vise, comme les deux seuils de
la clause 1 d'`ADR-0086`. **Je dis la propriete, jamais la forme, et je la route a `theo-archi`** :
*deux items d'un meme paquet n'enseignent pas le meme fait sous deux habillages*.

**Ce qu'il faut pour lever `DEV-R1`** : que l'un des deux items d'eclosion porte un autre fait - une
autre transition, ou une autre etape en bonne reponse. Un item.

### 3.5 Le second effet du retrait de la premisse, et qui devient `DEV-R2`

**Fait de partie jouee, presse en cible jusqu'a la revelation.** L'item de l'arbre offre *le
vieillissement* (juste), *la naissance* et ***la croissance***. J'ai presse *la croissance* trois
fois : *"Ce n'est pas ca"*, puis la revelation, avec pour seul retour *"Après avoir pu se reproduire,
un être vivant vieillit peu à peu."*

**Or c'est moi qui ai ecrit, la veille et contre ce paquet, qu'un arbre ne finit jamais de grandir.**
Tant que l'enonce affirmait *"a fini de grandir"*, *la croissance* etait un distracteur legitimement
faux. **La premisse retiree, il est devenu, pour un arbre precisement, un enonce vrai range en
reponse fausse** : l'arbre continue bien de grandir apres sa premiere fructification. Dans la
sequence enseignee, *le vieillissement* est la bonne reponse et l'item fonctionne ; mais l'enfant qui
sait ce que le programme lui fait observer sur l'arbre de la cour est sanctionne sans explication.

**Reserve, pas refus, et le motif de ce classement est net** : le paquet n'affirme plus rien de faux,
c'est un distracteur qui est devenu equivoque **sur ce vivant-la**. Et mon refus offrait deja la
sortie : *"Ou remplacer l'arbre par un animal dont la croissance s'arrete vraiment."* La branche
choisie etait celle que j'avais appelee *"le plus simple"* ; sa consequence est a moi autant qu'a la
developpeuse.

**Ce qu'il faut pour lever `DEV-R2`** : que cet item porte un vivant dont la croissance s'arrete
vraiment, **ou** que *la croissance* cesse d'y etre un distracteur.

---

## 4. Les quatre reserves neuves et leur regime

**Allouees dans `squad/decisions/REGISTRE-identifiants.md`, section 4, dans le meme commit que ce
document** (`PAR-5`, `ADR-0085`). Deux prefixes neufs, `MAT` et `DEV`, verifies libres dans la table
avant d'ecrire. **Je n'ai touche aucun chiffre de compte global et aucune note d'un autre auteur** :
`theo-archi` et l'instance A ecrivent dans ce fichier en meme temps que moi.

| Reserve | Paquet | Objet | Regime |
|---|---|---|---|
| **`MAT-R1`** | `familles-materiaux` | L'item de la bouteille annonce l'impermeabilite comme critere alors que c'est la retention du gout qui departage, et **le plastique y est faux sous une phrase qui le rend juste a l'item de la combinaison**, servi dans la meme partie 4 fois sur 4 | **rejeu borne, 0,5 pour le paquet entier** (`PAR-10`). Les deux reserves du paquet voyagent ensemble |
| **`MAT-R2`** | `familles-materiaux` | L'item du tuyau d'arrosage n'offre aucune alternative serieuse (*le verre*, *le metal*) - `PLA-1`. C'etait le *de preference* de mon refus, il reste facultatif | idem, **sans un centime de plus** |
| **`DEV-R1`** | `etapes-developpement` | Deux items enseignent le meme fait (*sortir de l'oeuf, c'est la naissance*) sous deux noms d'animal, servis ensemble 3 fois sur 3 ; le garde-fou neuf est vert parce qu'il exige aussi des distracteurs identiques | **rejeu borne, 0,5 pour le paquet entier** (`PAR-10`) |
| **`DEV-R2`** | `etapes-developpement` | Sur l'item de l'arbre, *la croissance* est range en reponse fausse alors qu'un arbre continue de grandir ; le retour n'en dit rien | idem |

**Aucune reserve anterieure n'est levee ni durcie par ce document.** `RGA-R1` et `RGA-R2` restent ce
qu'elles etaient sur `SCI-CM1-07`, ligne que j'ai fermee la veille et que je ne rouvre pas.

---

## 5. Ce dont je reste la seule garde apres ce reaudit

**Je le dis explicitement, sinon l'equipe croira couvert ce qui ne l'est pas.** La reprise a ajoute
cinq tests de contenu ; voici ce qu'ils gardent et ce qu'ils ne gardent pas.

| Propriete | Garde executable ? |
|---|---|
| **`PLA-1`** - une proposition est un candidat, jamais un remplissage | **aucun de portee generale** (`ADR-0086` a mesure que la clause n'est pas encodable). Le test ajoute verifie **les trois materiaux nommes dans mon refus** sur **les trois items nommes** - c'est un cliquet contre la regression exacte, pas un garde de la propriete. Le tuyau (`MAT-R2`) le montre : il est vert et l'item n'a pas d'alternative serieuse |
| **Qu'un item teste la propriete qu'il annonce** | **aucun.** C'est `MAT-R1`, et c'est le coeur de ce que mon refus faisait payer |
| **Qu'une proposition rangee en distracteur ne soit pas vraie ailleurs, ou vraie du vivant decrit** | **aucun.** C'est `DEV-R2` |
| **Que deux items n'enseignent pas le meme fait sous deux habillages** | **aucun.** Le test ajoute exige bonne reponse **et** distracteurs identiques ; la paire poussin/chenille passe dessous. **Route a `theo-archi`** - je dis la propriete, jamais la forme |
| **`SCI-1`** - qu'un indice dise quelque chose de vrai et explique l'erreur | **partiel, et seulement contre la chaine exacte.** Le test ajoute interdit la formule *"meme liquide"* dans l'indice d'un item nomme. Un indice neuf qui ne veut rien dire passerait |
| **La coherence entre les familles enseignees et les familles servies en bonne reponse** | **oui, desormais, et c'est le seul garde-fou de propriete generale que cette reprise ajoute.** Toute bonne reponse de la tache de choix doit etre une famille enseignee en tache A. **C'est exactement ce que j'avais route a `theo-archi`, et c'est paye** |
| **`FAB-1b`** telle que le test l'encode maintenant | **plus faible que mon exception ne le dit.** Le test exigeait six bonnes reponses distinctes ; il exige desormais qu'aucun materiau ne domine **la totalite** de la tache - il accepterait cinq items identiques sur six. **Le contenu actuel varie reellement** (cinq materiaux sur six items), donc **ce n'est pas un motif** ; mais *varier* n'est pas *ne pas etre constant*, et ma correction de lecture du 2026-09-07 ne demandait pas ce relachement. **Route a `theo-archi` et a `lea-dev`, comme un ecart de garde-fou, pas comme un defaut de contenu** |
| **L'exactitude de chaque fait de biologie et de chaque appariement propriete / materiau** | **aucun** |
| **`CYC-1`** - recouvrement CP / CM1 | **aucun.** Verifie a la main la veille, non rejuge : les items concernes n'ont pas bouge |

---

## 6. Mon cout, poste par poste

| Poste | Prix | Consomme |
|---|---|---|
| **Reaudit de `cm1-sciences-familles-materiaux`** - cinq parties, onze items relus, quatre motifs recomptes, un verdict, deux reserves | **0,5** (`PAR-10`) | **0,5** |
| **Reaudit de `cm1-sciences-etapes-developpement`** - cinq parties, onze items relus, trois motifs recomptes, un verdict, deux reserves | **0,5** (`PAR-10`) | **0,5** |
| **Total du reaudit** | **1,0** | **1,0** |

**Cela tient exactement dans la marge de rejeu `PAR-11` que j'avais annoncee intacte hier, et pas un
centime au-dela** - je l'avais ecrit d'avance : *"si `lea-dev` reprend les deux paquets dans s31, mon
reaudit tient exactement dans ma marge, 0,5 + 0,5 = 1,0"*. **Cumul de l'instance B sur s31 : 4,0
consommes sur 4,0.** Il ne reste rien : un troisieme paquet a rejouer sortirait de ma borne, et il
faudrait le dire avant, pas apres.

---

## 7. Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`SCI-CM1-11` : `non couvert` -> `couvert-valide`**, sans mention de couverture partielle, avec `MAT-R1` et `MAT-R2` ouvertes | `camille-po` | **Je ne touche pas la matrice.** Le mouvement se fait sur la foi de ce verdict et de la fiche |
| **`SCI-CM1-08` : `non couvert` -> `couvert-valide`**, sans mention de couverture partielle, avec `DEV-R1` et `DEV-R2` ouvertes | `camille-po` | idem |
| **Les quatre reserves** | `camille-po`, via `sofia-sm` | Deux stories, une par paquet. **Rejeu borne, 0,5 par paquet** (`PAR-10`) |
| **Deux items d'un meme paquet n'enseignent pas le meme fait sous deux habillages** | `theo-archi` | Le test neuf exige bonne reponse **et** distracteurs identiques ; la paire mesuree passe dessous. Je dis la propriete, pas la forme |
| **`FAB-1b` : le test accepte desormais cinq bonnes reponses identiques sur six** | `theo-archi`, `lea-dev` | Un garde-fou relache au-dela de ce que ma correction de lecture demandait. Le contenu, lui, est conforme |
| **Un item teste la propriete qu'il annonce** | `theo-archi` | Propriete de contenu, probablement non encodable comme `PLA-1`. **A nommer comme telle plutot qu'a laisser croire couverte** |
| **La porte technique rouge sur `60bde989`** | `theo-archi` | Je n'y touche pas et je n'attends rien de plus que sa reparation. **Mes deux verdicts n'en dependent pas**, et je dis pourquoi en section 0 |
