# Reaudit des deux refus d'EMC CP de s31 - `nadia-prof`, instance A

- **Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
- **Date** : 2026-09-08.
- **Arbre reaudite** : `round-two` a `60bde989`, worktree dedie `s31-nadia-A2`, scratchpad propre a
  cette instance.
- **Perimetre, strict** : `cp-emc-exclusion` (`EMC-CP-03`) et `cp-emc-regle-souhait-loi`
  (`EMC-CP-07`), **les deux paquets que j'ai refuses le 2026-09-07**, et rien d'autre. Les quatre
  paquets neufs de sciences CM1 et leurs reprises appartiennent a l'instance B ; **ce document n'en
  juge aucun, pas meme sur la couture**. Mes quatre autres verdicts de s31 sont rendus et ne se
  rejugent pas ici.
- **Correctif reaudite** : `60bde989`, `marc-dev`.

---

## 1. Les deux verdicts

| Paquet | Ligne | Verdict du 2026-09-07 | **Verdict du reaudit** | En une phrase |
|---|---|---|---|---|
| `cp-emc-exclusion` | `EMC-CP-03` | REFUSE | **VALIDE SOUS RESERVE** - **refus leve** | Mes trois motifs sont payes et je les ai recomptes : la moitie *reconnaitre* est reellement travaillee, aucune tache ne porte de reponse invariante, `CAT-1` est tenue sur les deux taches et je l'ai verifiee a l'ecran manche par manche. **Cinq reserves neuves et datees**, aucune bloquante |
| `cp-emc-regle-souhait-loi` | `EMC-CP-07` | REFUSE | **REFUSE - maintenu**, sur **un** motif | Le fait de droit faux est corrige et `FAB-1a` est tenue - les deux sont acquis et ne se redemandent pas. **`FAB-1c` n'est pas tenue** : sur **4 items sur 5** de la tache B, la bonne reponse est la seule proposition qui ne porte pas *seulement*, la ou ma barre est *"au plus un tiers des items de la tache"* |

> **Une ligne de programme ferme** : `EMC-CP-03`. **`EMC-CP-07` reste `non couvert`.** Le mouvement
> de matrice appartient a `camille-po` ; je n'y touche pas et je n'additionne rien avec le resultat
> de l'instance B, que je n'ai pas audite.

Chaque verdict porte son motif complet dans la fiche du jeu, en **bloc de reaudit ajoute sous le
bloc du 2026-09-07, qui reste lisible entier** :

- `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md`
- `squad/conformite/FICHE-bandit-manchot-regle-souhait-loi-cp.md`

**Je n'efface aucun verdict.** Le REFUSE du 2026-09-07 reste ecrit dans les deux fiches, avec son
motif d'origine : un refus efface n'a aucune valeur de preuve.

---

## 2. Mon mode d'audit, et ses angles morts

**J'ai joue, et c'est la seule raison pour laquelle ce document dit quelque chose que la CI ne dit
pas.**

- **Jar reconstruit depuis mon worktree** a `60bde989`, reacteur complet, `-DskipTests` (motif en
  section 3), lance sur le **port 8092**, dedie a cette instance.
- **`cp-emc-exclusion`** : **deux parties completes de dix manches** - la premiere a deux erreurs
  volontaires par manche, la seconde a **trois erreurs par manche, donc dix revelations**. Les deux
  parties reunies m'ont servi **les onze items** ; la partie a revelations en a servi dix, et le
  onzieme (*"Aya marche sur le pied de Hugo..."*) a ete lu a l'ecran dans la premiere.
- **`cp-emc-regle-souhait-loi`** : **une partie complete a trois erreurs sur chacune des dix
  manches, donc dix revelations**. Dix items servis sur onze ; le onzieme (le casque a velo, item 4,
  **tache A inchangee**) lu dans le fichier.
- **Tous les items des deux paquets ont ete lus**, y compris ceux que le tirage n'a pas servis.

**Trois angles morts, declares :**

1. **J'ai lu le HTML servi, pas la page rendue par un navigateur.** Cela couvre les textes, l'ordre
   des ecrans, l'ordre reel des propositions apres brassage, l'enchainement apres une erreur et
   apres trois, l'indice, la revelation, la fin de partie et le tirage. **Cela ne couvre ni la
   couleur, ni le contraste, ni la taille, ni la position.** **Aucun des deux verdicts ne repose sur
   un jugement visuel** : tout se recompte sur les fichiers, et j'en donne les comptes.
2. **La porte technique est rouge sur cet arbre**, pour un motif etranger au contenu (section 3).
3. **Je n'ai joue aucun autre paquet du catalogue.** Ce document ne dit rien des cinquante-neuf
   autres entrees.

---

## 3. Je prononce sur un arbre dont la porte technique est rouge, et voici pourquoi c'est legitime

`sofia-sm` m'a explicitement laisse le choix de rendre la main. **Je prononce, et le motif est
verifiable, pas de convenance.**

**Ce qui est rouge** : `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, parce que le rapport
qualite de `yanis-qa` cite verbatim l'identifiant fictif `ZZZ-9` de sa propre mutation de garde-fou.
**Aucun paquet, aucun item, aucune fiche, aucune mecanique n'est en cause**, et le defaut vit dans un
fichier de ceremonie.

**Ce que j'ai verifie moi-meme plutot que de le tenir de quelqu'un, parce que ma regle est de ne pas
valider un increment dont le Done technique n'est pas acquis** :

| Mesure | Perimetre nomme a cote du chiffre | Resultat |
|---|---|---|
| `PaquetCpEmcExclusionTest` et `PaquetCpEmcRegleSouhaitLoiTest` | `-pl adapters/adapter-content -am`, deux classes | **13 tests, 0 echec** |
| `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` (les deux garde-fous d'`ADR-0086`) | `-pl bootstrap -am`, une classe | **7 tests, 0 echec** |

**Les deux mesures portent `-am`**, comme `CLAUDE.md` l'exige de toute mesure partielle, et je nomme
leur perimetre a cote de leur chiffre : **un module mesure n'est pas le depot**, et ces deux
commandes ne remplacent pas `mvn -B clean verify`.

**Conclusion** : les deux increments que je juge se compilent, se chargent et passent leurs propres
garde-fous de contenu. **Un jeu qui plante n'est pas evaluable ; ceux-la ne plantent pas, et je les
ai joues jusqu'a la fin de partie.** Ce que la porte technique doit encore rendre est le vert global,
et il appartient a `theo-archi` ; il ne change rien a ce que j'ai lu a l'ecran.

---

## 4. `CAT-1` sur `exclusion` - le point que `sofia-sm` m'a demande de verifier explicitement

**Sa question est la bonne, et c'est la mienne** : un correctif qui repare `FAB-1a` en laissant la
bonne reponse reconnaissable a son registre ne repare rien de ce que je reprochais. **J'ai donc
verifie `CAT-1` a l'ecran, item par item, sur les deux taches, dans l'ordre reel ou les propositions
sont servies.**

**Tache A - `CAT-1` tenue.** Les trois propositions de chaque item sont **trois noms de situation**,
jamais une attitude. Releve a l'ecran :

| Manche | Bonne reponse | Les deux autres |
|---|---|---|
| *Malo empeche Aya de s'asseoir, chaque midi* | *une moquerie qui se repete* | *un accident isole*, *une bagarre* |
| *Hugo se moque de la facon dont parle Tom* | *une moquerie qui se repete* | *un accident isole*, *une blague entre amis* |
| *Malo ecarte Tom du jeu depuis plusieurs jours* | *une moquerie qui se repete* | *un accident isole*, *un jeu de recreation* |
| *Tom bouscule Malo une fois et s'excuse* | *un accident isole* | *une moquerie qui se repete*, *une bagarre* |
| *Aya marche sur le pied de Hugo par accident* | *un accident isole* | *une moquerie qui se repete*, *une dispute* |
| *Malo fait tomber le dessin de Lea sans faire expres* | *un accident isole* | *une moquerie qui se repete*, *une vengeance* |

**Il n'y a plus une seule attitude a l'ecran.** Les douze propositions negligentes ou agressives que
j'avais relevees le 2026-09-07 - *"se moquer de Hugo a son tour"*, *"attendre Hugo a son tour pour se
venger"*, *"forcer un camarade a lui donner la main"*, *"trouver que ce n'est pas grave"* - **ont
toutes disparu**. Ce qui distingue les propositions est **ce qu'elles disent** (une repetition contre
une occurrence unique), jamais comment elles sonnent. **C'est exactement la forme que `CAT-1`
demande.**

**Tache B - `CAT-1` tenue.** La proposition au rabais *"a personne, ..."*, que j'avais nommee comme
ecartable au ton sur cinq items sur cinq, **n'existe plus**. Les trois propositions de chacun des
cinq items sont **les trois memes adultes reels**, qui tournent en bonne reponse et en distracteur
selon le lieu : *a sa maitresse*, *a un surveillant de la cour*, *a l'adulte qui surveille la
cantine*. **Aucun ton ne les separe, et il n'y a plus de bouton a ecarter sans savoir.**

**Verdict sur le point le plus serieux du sprint** : sur ce paquet, `CAT-1` n'est pas contournee, elle
est tenue - et elle est tenue par **un changement de nature de la tache**, pas par un habillage des
propositions. **Je le dis sans reserve parce que je l'ai vu a l'ecran et non dans un commentaire de
paquet** : c'est ce meme commentaire qui, le 2026-09-07, declarait `CAT-1` tenue alors que le jeu la
violait.

---

## 5. `FER-2` recompte sur le materiau corrige, plutot que declare tenue

`sofia-sm` a raison d'exiger le recompte : j'avais chiffre l'echec en **items comptables** (6 sur 10,
puis 7 sur 10), et un chiffre qui a servi a refuser ne se remplace pas par une declaration.

**La regle de comptage est la mienne, `FAB-1a`, mot pour mot** : *"une tache dont **tous** les items
ont la meme bonne reponse compte pour **un** item au titre de `FER-2` et de `FER-3`"*. Elle ne mord
donc que sur une tache **entierement** invariante.

| Paquet | Tache | Items | Bonnes reponses distinctes | Comptables |
|---|---|---|---|---|
| `exclusion` | A - classer la situation | 6 | **2** (*moquerie qui se repete* x3, *accident isole* x3) | **6** |
| `exclusion` | B - nommer l'adulte du lieu | 5 | **3** (les trois adultes, chacun au moins une fois) | **5** |
| | | | | **11 sur un plancher de 10 - tenue** |
| `regle-souhait-loi` | A - regle, souhait ou loi | 6 | **3** (*regle* x3, *souhait* x2, *loi* x1) | **6** |
| `regle-souhait-loi` | B - a qui la loi s'applique | 5 | **5**, toutes distinctes | **5** |
| | | | | **11 sur un plancher de 10 - tenue** |

**`FER-2` est tenue sur les deux paquets, et elle cesse d'etre un motif sur l'un comme sur l'autre.**
Le refus qui reste ne s'appuie pas sur un compte d'items.

**Et je nomme la lecture que je n'applique pas, pour qu'on n'ait pas a me la redemander.** Prise a la
lettre, la premiere phrase de `FAB-1a` - *"deux items ne portent pas la meme bonne reponse"* -
condamnerait la tache A d'`exclusion`, dont trois items disent *moquerie* et trois disent *accident*.
**Elle ne s'applique pas ici, et ce n'est pas une faveur de ce matin** : le meme paragraphe, ecrit le
2026-09-07 **avant que les lots existent**, porte son exception explicitement :

> *"un classement a categories fermees (homogene / heterogene, herbivore / carnivore / omnivore) sert
> necessairement les memes propositions a chaque item. **C'est licite** des lors que la bonne reponse
> **varie**."*

**Une tache de discrimination a deux categories est le cas nomme par cette exception** - et c'est
d'ailleurs moi qui, dans le refus du 2026-09-07, ai demande que la tache A **devienne** un
classement. Je ne peux pas commander une forme puis la refuser au nom d'une clause qui l'exempte.

**`FAB-1c` sur `exclusion`, verifiee au meme titre** : sur la tache A, chacune des deux bonnes
reponses est **servie en distracteur sur les items de l'autre categorie** ; sur la tache B, les trois
memes chaines tournent en bonne reponse et en distracteur. **Aucune forme, aucune amorce, aucune
longueur ne designe la bonne reponse. Zero item sur onze. Tenue.**

---

## 6. `regle-souhait-loi` - ce qui est paye, et le seul motif qui reste

### 6.1 Ce qui est paye, acquis, et ne se redemandera pas

- **Le fait de droit faux est corrige.** L'ancien item 11 (*"une loi interdit de traverser en dehors
  des passages prevus pour les pietons"*) est **retire**. Il est remplace par un item sur la vente
  d'alcool aux mineurs, **que j'ai verifie contre sa source** : l'article L3342-1 du code de la sante
  publique interdit la vente de boissons alcooliques aux mineurs, et *"seulement les adultes"* est
  la consequence exacte de cette interdiction, la majorite etant a dix-huit ans. **Le motif principal
  de mon refus du 2026-09-07 est paye.**
- **Les quatre autres faits de la tache B restent exacts**, reverifies un par un sur le materiau
  corrige : feu rouge, ceinture pour le conducteur et tous les passagers, interdiction de fumer dans
  l'enceinte scolaire sans exception pour les adultes, avertisseur sonore limite au danger immediat.
- **`FAB-1a` est tenue** : cinq bonnes reponses **entierement distinctes** la ou il y en avait une
  seule a un mot pres. **Et le contre-exemple que je demandais existe** : sur l'item de l'alcool, la
  loi ne vise que les adultes, donc *"seulement ..."* est vrai quelque part. **C'est un vrai progres
  pedagogique et je le porte au credit du correctif.**
- **`PLA-1` est payee sur l'item du klaxon** : *"seulement les enfants"*, qu'aucun enfant de six ans
  ne peut envisager, est remplace par *"seulement les motards"* et *"seulement les chauffeurs de
  taxi"*, deux usagers qui klaxonnent reellement.
- **La tache A est inchangee**, comme je l'avais demande, et elle reste bonne.
- **`CAT-1`, `BIN-1`, `FER-1`, `FER-2`, `FER-3`, `EMC-1`, `EMC-5`, `EMC-8`, `EMC-10`** : tenues,
  reverifiees.

### 6.2 Le seul motif qui reste - `FAB-1c`, recompte

**Ma clause, datee du 2026-09-07, avant les lots** : *"la bonne reponse ne se reconnait pas a la
**forme** de son texte - son amorce, sa longueur, son registre - sur **plus d'un tiers** des items de
la tache."* Cinq items : la barre autorise **un** item, pas deux.

**Ce que la tache B sert, releve a l'ecran dans l'ordre reel des propositions** :

| Item | Bonne reponse | Les deux distracteurs | La bonne reponse est-elle la seule sans *seulement* ? |
|---|---|---|---|
| feu rouge | *tout le monde, y compris les adultes* | *seulement les enfants a velo*, *seulement les pietons* | **oui** |
| ceinture | *le conducteur et tous les passagers* | *seulement le conducteur*, *seulement les enfants a l'arriere* | **oui** |
| tabac a l'ecole | *personne, pas meme les adultes* | *les enseignants seulement*, *les visiteurs seulement* | **oui** |
| klaxon | *tout conducteur, meme adulte* | *seulement les motards*, *seulement les chauffeurs de taxi* | **oui** |
| alcool | *seulement les adultes* | *tout le monde, meme les enfants*, *seulement les enfants* | non - **le seul** |

**Deux comptes, parce que les deux lectures existent et que je ne veux pas qu'on me demande laquelle
je voulais dire :**

- **strictement sur l'amorce** (le premier mot) : la bonne reponse est la seule proposition qui ne
  commence pas par *seulement* sur **3 items sur 5** - feu rouge, ceinture, klaxon ;
- **sur la presence du mot restrictif ou qu'il se trouve dans la proposition** : **4 items sur 5**,
  l'item du tabac s'y ajoutant, ses deux distracteurs posant *seulement* en fin de proposition.

**Ma barre autorise un item. Le compte le plus favorable au paquet en donne trois.** L'ecart n'est
pas marginal : il faudrait que **quatre** items sur cinq cessent d'etre reconnaissables, et un seul
l'a cesse.

**Ce que cela fait a l'enfant, et c'est la raison pour laquelle cette clause existe.** Un enfant qui
ne sait rien du droit applique *"j'evite le bouton qui commence par seulement"* et **gagne 4 manches
sur 5 sans lire l'enonce**. La seconde moitie du libelle - *reconnaitre qu'une loi s'impose a tous* -
reste donc gagnee **par la forme des boutons**, plus par une seule formule repetee comme au premier
passage, mais gagnee sans la competence tout de meme. **C'est `CON-1`, et c'est le motif meme de mon
refus du 2026-09-07** : je l'avais chiffre a 5 items sur 5, il est aujourd'hui a 3 ou 4 sur 5, et ma
barre est a 1.

**Ce qui fermerait la ligne, et je ne prescris aucun texte** :

> **Au plus un item de la tache B laisse la bonne reponse comme seule proposition depourvue du
> marqueur restrictif.** Les quatre autres l'obtiennent de l'une ou l'autre facon - la bonne reponse
> **est** elle-meme une restriction vraie (l'item de l'alcool montre que c'est faisable, et le droit
> en fournit d'autres : ce qui n'est permis qu'a partir d'un age, ce qui n'oblige qu'une categorie
> d'usagers), **ou** l'un des deux distracteurs est lui-meme une proposition universelle et fausse,
> de sorte que le marqueur ne trie plus rien. **La forme appartient au developpeur ; ce qui est
> opposable est le compte.**

### 6.3 Ce que je corrige dans ma propre phrase, avant qu'on me l'oppose

**Mon refus du 2026-09-07 disait, dans le paragraphe qui prescrivait la sortie** : *"de sorte que
'seulement les conducteurs' soit vrai **quelque part**"*. **`marc-dev` a fait exactement cela** : la
restriction est vraie sur un item. **Ma phrase est satisfaite, et elle etait trop faible pour la
clause qu'elle etait censee servir** : *quelque part* dit un item, `FAB-1c` en exige quatre.

**C'est ma faute de redaction, et c'est la troisieme fois que je la fais** - `EMC-3` ou j'avais fige
une chaine de caracteres au lieu d'une categorie, `SCI-4` ou j'avais compte des propositions au lieu
d'exiger des candidats, et cette phrase-ci ou j'ai ecrit un exemple la ou il fallait un compte.

**Ce que cela change au verdict : rien, et je dis pourquoi sans me menager.** `FAB-1c` est une clause
ecrite et datee **avant l'ouverture du sprint**, citee **nommement** dans le motif de mon refus du
2026-09-07 (*"C'est `FAB-1a` **et** `FAB-1c`"*), et son compte n'a pas bouge d'un chiffre depuis.
**Je ne releve aucune barre : je recompte la meme, sur le meme item de clause, avec le meme
denominateur.** Ce que je corrige est ma **prescription**, qui etait plus faible que ma clause ; je
la remplace par le compte, en section 6.2, pour que le prochain correctif n'ait pas a deviner.

**Et je nomme la direction de la pression** : lever ce refus aurait clos le sprint a deux lignes
d'EMC fermees au lieu d'une, aurait epargne un aller-retour a l'equipe, et personne ne m'aurait
demande de comptage. **C'est precisement pour cela que je le compte.**

---

## 7. Ce que j'ai vu en jouant et que je n'avais pas vu, ecrit comme reserve neuve et jamais comme un durcissement

**La regle que je m'applique** : quand une equipe atteint la barre que j'ai ecrite, je leve ma
reserve, meme si je vois desormais mieux. Ce que je vois de plus s'ecrit **neuf et date**, jamais
comme un durcissement de l'ancien. **Les cinq reserves ci-dessous sont donc neuves, datees du
2026-09-08, et aucune n'est bloquante** : le refus d'`exclusion` est leve **sur les motifs que
j'avais ecrits**, et ces cinq points n'en font pas partie.

| Reserve | Paquet | Ce qu'elle demande | Bloquante ? |
|---|---|---|---|
| **`EXC-R1`** | `exclusion` | **La consigne du jeu annonce la tache que le paquet sert.** Le catalogue dit encore *"dis ce qu'il faut faire, ou a qui en parler"* : c'est la consigne de la version refusee. La tache A ne demande plus ce qu'il faut faire, elle demande de nommer la situation, et **aucune des trois propositions n'est une action** | non |
| **`EXC-R2`** | `exclusion` | **Chaque indice de la tache B ajoute ce que l'enonce ne donne pas.** Les cinq indices ne redonnent que le lieu deja ecrit dans l'enonce, et sur deux items ils reprennent les mots de la bonne reponse (*"Dans la cour, ..."* -> *"C'est un adulte present dans la cour ..."* -> *a un surveillant de la cour*) | non |
| **`EXC-R3`** | `exclusion` | **La troisieme proposition de la tache A est un candidat serieux sur chaque item** (`PLA-1`). Elle ne l'est pas sur trois d'entre eux : *une bagarre* la ou personne ne se bat, *une dispute* sur un pied ecrase par accident, *une vengeance* sur un geste que l'enonce dit *"sans faire expres"* | non |
| **`EXC-R4`** | `exclusion` | **Les deux enonces les plus longs de la tache B (17 et 19 mots) disent leur situation en une seule proposition principale**, comme les trois autres (15 et 16 mots) | non |
| **`EXC-R5`** | `exclusion` | **Aucun item ne fait d'un adulte reel une reponse fausse.** *"A sa maitresse"* est marque faux sur les trois items ou la scene n'est pas en classe, alors qu'un enfant qui parle a sa maitresse d'une moquerie de la cour fait ce qu'il faut. Le critere reel est la presence de l'adulte : que la question le dise (*quel adulte est la a ce moment ?*) | non |

**`EXC-R5` est une reserve que j'ouvre contre mon propre constat de la veille**, et je l'ecris en
toutes lettres : le 2026-09-07 j'ai porte au credit du paquet que *"les trois adultes de la tache B
sont exacts et adaptes au lieu"*, et j'ai confirme sa forme sans reserve. **Cette tache est inchangee
depuis. Je l'avais creditee trop vite, et c'est moi qui ai demande qu'elle garde sa forme** - je ne
peux donc pas la refuser aujourd'hui pour cette forme. Ce qui attenue le defaut, et je le dis aussi :
sur une partie entiere, *a sa maitresse* est la **bonne** reponse deux fois, ce qui enseigne bien
*"cela depend de qui est la"* plutot que *"pas la maitresse"*.

**Les deux paquets refuses ne portent aucune reserve**, et `regle-souhait-loi` n'en porte donc
aucune : ses defauts sont dans son motif, et ils se paient par la reecriture.

**Regime des cinq reserves** : elles voyagent ensemble dans **un seul rejeu borne du paquet entier**
(`PAR-10`), **et ce rejeu n'est pas payable a s31** (section 8). Aucune ne rouvre `EMC-CP-03`.

---

## 8. Mon cout, et le fait qu'il tombe a 4,0 exactement

| Poste | Prix opposable | Consomme | Motif |
|---|---|---|---|
| Report du 2026-09-07 | 4,0 | **3,0** | Quatre lots et blocs audites |
| Rejeu de `cp-emc-exclusion` | 0,5 | **0,5** | Rejeu borne du paquet entier (`PAR-10`) |
| Rejeu de `cp-emc-regle-souhait-loi` | 0,5 | **0,5** | Idem. **Un refus maintenu se paie comme une levee** : l'audit a ete fait en entier, et c'est lui qui produit le compte |
| **Total** | **4,0** | **4,0** | **Aucun depassement** |

**C'est exactement le cas que j'avais chiffre d'avance le 2026-09-07** : *"Si `marc-dev` reecrit
`exclusion` et `regle-souhait-loi` dans le sprint, je peux les reauditer tous les deux et je clos a
4,0 exactement, sans depassement."* Il s'est realise au chiffre pres.

**Et je m'applique la phrase que j'avais ecrite pour ce cas, sans l'adoucir** : *"si une seule des
deux reecritures demande un **second** rejeu, la marge est epuisee (...) s'il n'y a plus de budget, la
ligne ne ferme pas - elle ne ferme pas **quand meme**."*

> **Ma borne de s31 est atteinte. `EMC-CP-07` ne ferme pas a s31**, et un troisieme passage sur
> `regle-souhait-loi` **n'est pas payable dans ce sprint**, quelle que soit la qualite d'un correctif
> qui arriverait ce soir. Le motif de la section 6.2 est ecrit pour etre repris en story, pas pour
> etre rejoue aujourd'hui. **Les cinq reserves d'`exclusion` sont dans le meme cas.**

**Ecart a mon attente d'avant-sprint, tenu a jour** : trois lignes d'EMC CP fermees sur quatre
tentees, soit **un taux de refus de 25 %** contre les 80 % de s29 et les 50 % que je notais hier.
**L'equipe fait mieux que ses deux precedents sur cette matiere.**

---

## 9. Ce dont je reste la seule garde - dit pour que l'equipe ne croie pas couvert ce qui ne l'est pas

**Le fait du reaudit, mesure et non suppose** : les deux garde-fous d'`ADR-0086` et les deux tests de
regression neufs de `marc-dev` sont **verts sur les deux paquets** (section 3), et **`FAB-1c` est
pourtant fausse sur 3 a 4 items sur 5** de `regle-souhait-loi`. **Un test vert sur `FAB-1a` cohabite
avec un `FAB-1c` faux, et rien dans la CI ne le dit.**

**Pourquoi la clause 2 d'`ADR-0086` ne peut pas voir ce defaut-la**, et je le dis en decrivant la
propriete, jamais la forme d'un test : elle compare le moule des **quatre premiers mots** de
propositions de **meme longueur** ne differant qu'en **une** position. Or les distracteurs qui
portent ce defaut font trois mots (*seulement les pietons*, *seulement le conducteur*, *seulement les
motards*) ou different en plusieurs positions. **Le marqueur qui trie l'ecran pour l'enfant est un
mot unique en tete de proposition, pas un moule de quatre mots.** Je ne prescris aucun seuil : je dis
ce que la clause ne couvre pas, et le choix appartient a `theo-archi`.

| Propriete | Qui la tient aujourd'hui | Automatisable ? |
|---|---|---|
| **`CAT-1`** - la bonne reponse ne se reconnait pas a son ton | **moi seule** | **non**, jugement de registre. **Tenue sur les deux paquets ce coup-ci, verifiee a l'ecran** |
| **`FAB-1c`** - la bonne reponse ne se reconnait pas a la forme de son texte | **moi seule en pratique** | **partiellement**, et le defaut qui reste ce soir est un **comptage** : un mot restrictif present sur les deux distracteurs et absent de la bonne reponse, sur plus d'un tiers des items d'une tache. **`ADR-0086` clause 2 ne l'attrape pas, et c'est mesure** |
| **`PLA-1`** - chaque proposition est un candidat serieux | **moi seule** | **non**. `EXC-R3` en est l'exemple du jour : *une vengeance* sur un geste dit *"sans faire expres"* |
| **`FAB-1a`** - pas de bonne reponse invariante dans une tache | `marc-dev` l'a mordue **par paquet** | **oui**, et c'est fait sur ces deux paquets. **Un garde-fou general reste route a `theo-archi`** |
| **La coherence entre la consigne du jeu et la tache servie** | **moi seule** | **oui, et ce serait rentable** : la consigne vit dans le catalogue, la tache dans le paquet, rien ne les compare. `EXC-R1` est passe par une reecriture complete, deux tests neufs et une CI que rien n'a alerte |
| **Qu'un indice ajoute quelque chose a l'enonce** | **moi seule** | **non** |
| **`EMC-5`** - aucun item a deux reponses defendables | **moi seule** | **non**. `EXC-R5` en est l'exemple du jour, **et je ne l'avais pas vu hier** |
| **L'exactitude d'un fait de droit cite a un enfant** | **moi seule, contre source** | **non**. Le fait faux est corrige ; **son remplacant, je l'ai verifie contre L3342-1 avant de le crediter** |
| **La lisibilite d'un enonce au CP** | **moi seule** | **partiellement**, et seulement le jour ou j'aurai ecrit une barre chiffree. **Je ne la cree toujours pas en cours de sprint** ; elle est due dans mon arbitrage prealable de s32 |

---

## 10. Ce que je route

| # | Suite | Destinataire | Ce que j'attends |
|---|---|---|---|
| 1 | **`EMC-CP-03` -> `couvert-valide`**, sans mention de couverture partielle | `camille-po` | Les deux moities du libelle sont servies. Le compte de la matrice lui appartient ; **je n'y touche pas** |
| 2 | **`EMC-CP-07` reste `non couvert`** | `camille-po` | Et **elle ne ferme pas a s31** : ma borne est atteinte (section 8) |
| 3 | **Le motif unique du refus maintenu**, avec son compte et sa sortie (section 6.2) | `camille-po`, via `sofia-sm` | En faire une story de s32. **La tache A reste a conserver telle quelle, et les quatre faits de droit exacts aussi** : la story ne touche que la forme des propositions de la tache B |
| 4 | **`EXC-R1` a `EXC-R5`** | `camille-po`, via `sofia-sm` | En faire des stories. **Un seul rejeu borne pour les cinq** (`PAR-10`), a financer a s32. **`EXC-R1` est celle que je corrigerais la premiere** : c'est un residu de la version refusee, visible sur les dix manches |
| 5 | **La section 4 de la fiche `camille-po` des deux jeux porte encore mes cases non cochees du 2026-09-07** | `camille-po` | Trois criteres qu'elle avait ecrits sont desormais tenus sur `exclusion` ; **cette section est la sienne, je ne la reecris pas**, et mon bloc de reaudit dit lesquels |
| 6 | **Le marqueur restrictif en tete de proposition, non couvert par la clause 2 d'`ADR-0086`** | `theo-archi` | **Fait mesure ce soir, avec sa cause** (section 9). Je dis la propriete, jamais la forme du test, et je ne prescris aucun seuil |
| 7 | **Rien ne compare la consigne d'une entree de catalogue a la tache que son paquet sert** | `theo-archi` | Symptome : `EXC-R1`. La cause technique lui appartient |
| 8 | **Le registre `PAR-5` ne voit pas une allocation faite dans une branche soeur non fusionnee** | `theo-archi` | **Deja route le 2026-09-07, et la section 11 en donne aujourd'hui une seconde mesure**, sur mon propre chiffre |
| 9 | **Un plafond chiffre de longueur d'enonce au CP**, et **la source d'un fait de droit dans le commentaire d'un paquet** | moi-meme, arbitrage prealable de s32 | Les deux etaient deja dues hier. `EXC-R4` et l'item de l'alcool les redemandent ; **je ne les cree ni l'une ni l'autre en cours de sprint** |
| 10 | **La conclusion de ce document dans `review.md`** | `sofia-sm` | Le fichier n'existe toujours pas ; la reprise est due a la cloture et elle m'appartient |

---

## 11. Mes cinq identifiants neufs sont alloues, et je corrige deux chiffres de moi-meme

**Mes cinq reserves ouvrent cinq identifiants, sur un prefixe neuf (`EXC`).** Je les ai alloues
moi-meme dans `squad/decisions/REGISTRE-identifiants.md`, **table de la section 4, dans le meme geste
que ce verdict** (`PAR-5`, `ADR-0085`) : les laisser non alloues ferait rougir
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, qui a deja rougi deux fois ce sprint pour ce
motif exact. **Le prefixe `EXC` etait libre, verifie sur le depot entier avant d'ecrire.**

**Je n'ai touche que la table de la section 4 et ma propre note.** La ligne de mesure de la section 1
et la note de correction de `theo-archi` sont a lui ; **je n'ai reecrit ni l'une ni l'autre**, et il
travaille en parallele sur ce fichier.

**Deux corrections a mes propres chiffres, verifiees plutot que relayees :**

1. **Mon compte du 2026-09-07, *"532 identifiants sur 89 prefixes"*, etait juste sur son perimetre et
   ne l'est plus sur l'arbre fusionne.** Recompte mecaniquement sur la table de la section 4 a
   `60bde989` : **534 identifiants sur 90 prefixes**. L'ecart est exactement `RGA-R1` et `RGA-R2`,
   alloues par l'**instance B** dans une branche que la mienne ne voyait pas - **la meme limite de
   couture que je route depuis hier, mesuree une seconde fois sur mon propre chiffre.** Ce n'est pas
   une occurrence neuve de ma forme (c) : l'allocation etait faite et correcte, elle m'etait
   invisible.
2. **La methode que j'avais ecrite sous ce chiffre annoncait un retrait qui n'a pas eu lieu** :
   *"puis retrait de l'unique homonyme de la section 3 (`UTF-8`) et de son prefixe"*. **`UTF-8` n'est
   pas alloue dans la table de la section 4**, donc il n'y avait rien a retirer, et le retirer aurait
   donne un chiffre faux. **Mon nombre etait bon, ma phrase de methode decrivait une etape de trop.**
   Je la corrige ici plutot que de la laisser servir de modele : la methode qui fait foi est le
   comptage des lignes de la table, sans retrait.

**Compte apres mon allocation de ce soir, meme methode** : **539 identifiants, sur 91 prefixes.**

---

## 12. Ce que ce document ne fait pas

- **Il ne touche ni `squad/programme/MATRICE-couverture.md` ni `docs/backlog/backlog.md`** :
  `camille-po` execute le mouvement de matrice sur la foi de ce verdict, comme a chaque cloture.
- **Il n'efface aucun verdict** : les deux REFUSE du 2026-09-07 restent lisibles entiers dans leurs
  fiches, avec leur motif d'origine et leur date, sous lesquels mon bloc de reaudit est ajoute.
- **Il ne durcit aucune clause.** `FAB-1c` est recomptee au chiffre pres, sur le meme denominateur ;
  l'exception ecrite de `FAB-1a` est appliquee **en faveur** du paquet ; les cinq reserves neuves
  sont neuves et datees, et **aucune ne motive le refus maintenu**.
- **Il ne corrige aucun contenu a la place d'un developpeur** : chaque motif et chaque reserve dit
  une **propriete a tenir**, jamais un texte a ecrire.
- **Il ne modifie aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/`.** J'ai lu
  deux paquets YAML, trois classes de test et un catalogue ; je n'y ai rien ecrit.
- **Il ne juge aucun paquet du perimetre de l'instance B**, ni les quatre lignes de sciences neuves,
  ni leurs reprises, **ni la couture entre nos deux moities**.
- **Il ne priorise pas et n'ouvre aucun perimetre** (`camille-po`) ; **il ne gele rien et n'affecte
  personne** (`sofia-sm`) ; **il ne prescrit la forme d'aucun test** (`theo-archi`) ; **il ne corrige
  ni orthographe, ni accentuation, ni ponctuation**, y compris dans les textes destines a l'enfant
  (`relecteur-pedagogique`, `DEC-0002` amende, `DEC-0056`).
