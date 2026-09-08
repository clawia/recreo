# ADR-0086 - La forme des propositions d'un paquet ne designe jamais la bonne reponse, et trois proprietes voisines ne sont pas encodables

- **Statut** : Accepte
- **Date** : 2026-09-07
- **Auteur** : theo-archi
- **Sprint** : s31
- **Route par** : `nadia-prof`, verdicts de s30 (instance A et instance B) et
  `PASSE-TRANSVERSE-s30-les-trois-familles-de-portee-depot.md`, section 6 : *"la contrefacon par
  tirage - qu'un item se resolve grace a un autre item du meme paquet - est mordable en principe,
  c'est une recherche de chaine, et elle a produit deux reserves ce sprint"*.

## Contexte

### Le fait, et il est chiffre

**Les refus au premier passage sont la premiere fuite de budget d'audit du depot.** Quatre paquets
sur cinq refuses a s29, deux sur quatre a s30, un reaudit a **0,5** chacun. Et le constat qui rend
cet ADR necessaire est celui de `nadia-prof` : **la reprise de s30 n'a ajoute aucun garde-fou.** Le
contenu a ete corrige, un commentaire d'exclusion a ete mis a jour dans un test existant, rien de
plus. Les proprietes que ses deux refus ont fait payer restent gardees par elle seule - ce qui est
la definition d'une propriete que la CI ne tient pas.

Elle nomme **cinq** proprietes comme *"textuelles donc mordables"*. **Deux le sont. Trois ne le sont
pas, et le dire avec les chiffres est la moitie de cet ADR** : une regle a population vide est ce
qu'`ADR-0051` refuse, et un faux rouge sur une formulation legitime coute plus qu'il ne rapporte.

### La mesure, avec sa methode

*Toutes les mesures ci-dessous portent sur les **55 paquets a propositions et 1 077 items** du
contenu servi, chargees depuis les repertoires de contenu des modules declares au pom agregateur,
et sur l'**etat de l'historique** ou `nadia-prof` a prononce ses deux refus de s30 -
`cm1-sciences-chaine-alimentaire` et `cm1-sciences-objet-technique` a `712c293b` et `5824e752`,
avant la reprise de `lea-dev`. Worktree `s31-theo` a `2ef583ba`, JDK 21.0.10.*

**Le choix de mesurer sur un etat de l'historique plutot que sur une fixture est deliberatement la
lecon de s05** : une fixture ecrite par l'auteur d'une regle ressemble a ce qu'il imagine, pas a ce
que le monde ecrit. Ici le monde a ecrit le defaut, il est dans `git`, et il ne se discute pas.

## Decision

**La forme des propositions d'un paquet ne suffit jamais a designer la bonne reponse.** Deux
clauses, a l'imperatif.

### Clause 1 - un item n'est pas servi plusieurs fois sous plusieurs noms

**Trois items ou plus d'un meme paquet, dont les propositions se correspondent une a une dans le
meme moule, ne portent pas tous la meme bonne reponse d'au moins trois mots.**

C'est le refus de `SCI-CM1-17` : *"les cinq items de la seconde tache sont un seul item, servi cinq
fois avec un autre nom d'animal ; des la seconde occurrence l'enfant reconnait la phrase et la
presse, sans rien savoir du milieu"*.

**Deux moules definissent la clause, et ils ne sont pas negociables :** deux textes coulent dans le
meme moule s'ils sont identiques, ou s'ils comptent le meme nombre de mots, au moins quatre, et ne
different qu'en une position. C'est ce qui fait qu'*"il risquerait de manquer de nourriture"* et
*"elle risquerait de manquer de nourriture"* sont **une** reponse et non deux.

### Clause 2 - l'amorce d'un distracteur ne le distingue pas d'une bonne reponse

**Quatre distracteurs ou plus d'un meme paquet ne partagent pas le moule de leurs quatre premiers
mots quand aucune bonne reponse du paquet ne le partage.**

C'est `FOP-R4`, ouverte le 2026-09-06 : *"sur ces trois items, un enfant qui n'a rien compris trouve
la bonne reponse en lisant les deux premiers mots de chaque bouton - croire est faux, demander est
juste"*.

## Ce que ces clauses ont coute a trouver, et pourquoi les formulations evidentes sont fausses

**C'est la section que je ne veux pas qu'on saute**, parce qu'elle est la seule protection contre le
prochain garde-fou de cette famille qu'on ecrirait a l'intuition.

### La clause 1 evidente rend rouge du contenu valide et ne mord pas le paquet refuse

Premiere ecriture : *deux items dont les enonces coulent dans le meme moule*. Mesure sur le contenu
servi :

| Resultat | Compte |
|---|---|
| Paires d'items a enonce du meme moule et meme bonne reponse | **plus de 120**, dans 8 paquets, tous valides par `nadia-prof` |
| Paquet refuse mordu | **aucun** - les cinq enonces de la tache B different de bien plus d'un mot |

Le cas qui tranche : *"Un chat, qu'est-ce que c'est ?"* contre *"Un arbre, qu'est-ce que c'est ?"*
(`cp-qlm-vivant-non-vivant`, valide) a **exactement la forme textuelle** de *"Que se passerait-il si
le renard disparaissait ?"* contre *"... si le lapin disparaissait ?"* (refuse). **Substituer un nom
dans une question est un exercice de classement legitime quand la reponse change avec le nom, et une
repetition quand elle ne change pas.** La difference n'est pas dans l'enonce ; elle est dans la
reponse. C'est pourquoi la clause 1 ne regarde pas l'enonce du tout.

### Le plancher de trois mots sur la reponse repetee n'est pas un reglage de confort

Sans lui, la clause 1 rend rouges `cp-voyelle-du-son` (quatre groupes), `cp-lettre-du-son` et
`cp-qlm-vivant-non-vivant`, **tous valides**. Leurs reponses repetees sont des **etiquettes de
categorie** - une lettre, un son, *non* - et repeter une etiquette d'un item a l'autre **est** la
tache. Une phrase entiere repetee mot pour mot est une phrase que l'enfant reconnait. **Sans ce
plancher, le garde-fou prononcerait un verdict pedagogique**, ce qu'il n'a pas le droit de faire.

*Marge du reglage, dite plutot que tue : a deux mots au lieu de trois, le contenu servi reste vert.
Le plancher porte donc un mot de marge, et il n'est pas pose sur la valeur qui fait juste passer.*

### La clause 2 litterale accuse un paquet innocent

Un critere d'amorce **litterale** rend rouge `cm1-problemes-durees` : cinq distracteurs commencent
par *"1 heure 25 minutes"*, et aucune bonne reponse ne commence par ces quatre mots-la. Mais sa
bonne reponse *"1 heure 15 minutes"* coule dans le **meme moule**. Le critere de moule le laisse
vert, et il est le seul a le faire.

**Mesure finale de la clause 2, sur 142 amorces distinctes des 55 paquets : un seul rouge**,
`cm1-emc-fait-opinion`, **8 distracteurs sur 10** - le chiffre exact que `nadia-prof` a compte a la
main le 2026-09-06. Zero faux positif.

## Les trois proprietes que je n'encode pas, et pourquoi

**Chacune a ete ecrite, executee et mesuree avant d'etre ecartee.** Aucune n'est ecartee sur
intuition.

### 1. *La bonne reponse apparait mot pour mot dans son propre enonce* - refusee

| Mesure sur le contenu servi | Compte |
|---|---|
| Items ou la bonne reponse est un extrait litteral de son enonce | **39 sur 1 077**, dans 10 paquets |
| Dont `cp-qlm-etats-eau` | **12 sur 14** - la question alternative *"est-ce solide ou liquide ?"* contient les deux reponses par construction |
| Items que `nadia-prof` a effectivement signales sous ce motif (`SIG-R1`, `MEL-R2`) | **0** |

**Le critere textuel et la propriete pedagogique n'ont pas la meme population, et elles ne se
recouvrent nulle part.** Ce qu'elle voit est qu'un enonce **donne** sa reponse ; ce que la chaine
trouve est qu'une question alternative **enumere** ses options, ce qui est une forme legitime et
frequente. Une regle qui rendrait rouges 39 items sans en attraper un seul de ceux qui l'ont fait
ouvrir une reserve n'est pas la regle de cette propriete.

### 2. *Un item de cycle 3 redit un item de cycle 2* (`CYC-1`) - refusee

| Mesure | Compte |
|---|---|
| Couples CP x CM1 examines | **280 082** (439 items CP, 638 items CM1) |
| Couples a enonce du meme moule et meme bonne reponse | **0** |
| Bonnes reponses d'au moins deux mots servies aux deux niveaux | **3** : *l'hiver*, *le guidon*, *la lame* |

**L'item que `nadia-prof` decrit comme *"un item de CP re-servi a l'identique"* n'est pas identique
au sens textuel** : *"Quelle partie du velo sert a tourner ?"* au CP contre *"Dans un velo, quelle
piece sert a diriger la roue avant ?"* au CM1. Son identite est pedagogique - meme objet, meme
reponse, meme competence - et elle n'est pas une chaine. Et les trois croisements que la mesure
trouve **sont tous dans du contenu qu'elle a valide**, y compris les deux du velo apres reprise :
une regle bloquante devrait les exempter, c'est-a-dire declarer dette ce qu'elle a valide. **Ce
n'est pas mon geste.**

### 3. `OBJ-R2` - *les distracteurs d'un item sont les bonnes reponses des autres items* - refusee

C'est celle que je voulais le plus encoder : `nadia-prof` ecrit *"ce n'est plus une reserve de
paquet, c'est un reflexe de fabrication"*, le meme defaut ayant ete produit deux fois en deux jours
sur deux niveaux. Le proxy testable est *"un item porte au moins un distracteur qui n'est la bonne
reponse d'aucun autre item du paquet"*.

**Il reproduit sa mesure a la main sur le paquet qu'elle a compte** - `cp-qlm-objet-technique`, deux
items sur cinq, exactement sa barre. Et il echoue dans **les deux sens** sur le reste :

| Mesure | Resultat |
|---|---|
| Paquets servis a moins de deux items porteurs | **16 sur 55**, dont `cp-le-bon-son`, `cp-lettre-du-son`, `cp-nature-mot`, `cm1-tables-multiplication` - tous valides, tous a ensemble ferme par la competence elle-meme |
| Paquet refuse par `nadia-prof` pour ce motif exact | **vert**, parce que *"a tailler un crayon"* et *"a tailler la pointe d'un crayon"* ne sont pas la meme chaine |

**Une regle qui blanchit le paquet refuse et accuse seize paquets valides ne mesure pas la propriete
qu'elle porte.** Le mot portant d'`OBJ-R2` est **plausible** - *"une fonction plausible pour l'objet
nomme"* - et la plausibilite n'est pas une chaine. Elle reste a `nadia-prof`, entierement.

**Ce que cela laisse ouvert, et je l'ecris plutot que de le laisser croire couvert** : `OBJ-R2` est
la propriete qui a produit le plus de reserves quatre sprints de suite, et **elle n'a toujours aucun
garde-fou**. La clause 1 en couvre le cas degenere - quand la repetition est totale - et rien de
plus.

## Regle executable

**Fichier** :
`bootstrap/src/test/java/com/recreo/archi/LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest.java`

Ce n'est **pas une `ArchRule`**, et **le compte de regles `ArchRule` du depot est inchange, a 33.**
ArchUnit lit du bytecode ; la fabrication des propositions d'un paquet YAML n'y est pas une
propriete lisible.

**Perimetre derive (`ADR-0013` obligation 1)** : les paquets viennent du repertoire de contenu servi
de chaque module declare au pom agregateur, par
`AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu`. Aucun paquet, aucun item
et aucun texte n'est nomme dans la source hors de ses jeux fautifs et de son exemption datee. Un
paquet neuf entre dans la population le jour ou il entre au contenu servi.

### La preuve de la morsure, sur du materiau de production

**Trois epreuves, et elles ne prouvent pas la meme chose.**

1. **Clause 1, sur l'etat reel de l'historique.** Le jeu fautif n'est pas de ma main : c'est
   `cm1-sciences-chaine-alimentaire` **tel qu'il vivait a `712c293b`**, avant la reprise. La clause
   rend le grief attendu, en nommant les items et la reponse repetee.
2. **Clause 2, sur le paquet servi qui porte la reserve.** En lui retirant son exemption, la clause
   rougit sur `cm1-emc-fait-opinion` - **8 distracteurs sur 10**, le compte de `nadia-prof`.
3. **Deux jeux fautifs de ma main**, pour les situations que l'historique n'a pas encore produites.
   Ils viennent en troisieme et non en premier, et c'est volontaire.

S'y ajoute `lesFormesLegitimesNeSontPasDesRepetitions`, qui **epingle les trois formes que les
clauses doivent laisser passer** - deux propositions d'un mot, le pronom qui ne fait pas deux
reponses, le distracteur numerique dont la bonne reponse partage le moule. Un assouplissement futur
du moule les fera rougir.

**Sept tests, tous verts.** *Neuf depuis l'amendement du 2026-09-08, qui ajoute la clause 3 et son
cliquet.*

### Le cliquet de la clause 2, et son plafond vaut exactement la dette

`FOP-R4` est **ouverte et non payee** : `nadia-prof` l'a qualifiee non bloquante le 2026-09-06,
routee a `camille-po` avec son regime (rejeu borne, 0,5), et le paquet reste servi et valide. La
clause serait donc rouge sur du contenu que la porte pedagogique a laisse passer.

**Un paquet exempte, nomme, date ; plafond a un.** La liste ne peut pas grandir - un paquet neuf qui
contrefait son amorce est rouge -, elle ne peut pas pourrir : `laDetteNePeutNiGrandirNiPourrir`
exige que **chaque paquet exempte soit encore reellement fautif**. Le jour ou `FOP-R4` est payee,
l'exemption devient rouge et doit disparaitre dans le meme diff. Meme mecanique qu'`ADR-0067`.

## Consequences

**Bonnes.** Deux des familles qui ont coute deux refus a s30 cessent d'etre gardees par une seule
personne. Le cout d'un reaudit est de 0,5 ; le cout de la clause est nul apres son ecriture.

**Mauvaises, et je les ecris avant qu'on les decouvre.**

- **Le compte de garde-fous de contenu augmente d'un fichier a maintenir**, dont deux moules
  parametres par quatre constantes. Chaque constante porte son motif en Javadoc et la mesure qui l'a
  fixee ; aucune n'est un nombre rond choisi pour faire passer la suite.
- **La clause 1 ne mord que la repetition totale.** Un paquet ou quatre items sur cinq sont le meme
  item et le cinquieme differe reste vert. C'est la contrepartie du plancher de trois items, et
  c'est le prix a payer pour ne pas rougir sur un classement.
- **Trois proprietes routees restent sans garde-fou**, dont `OBJ-R2`, la plus productive en
  reserves. Elles sont nommees ci-dessus avec leur mesure, et ce n'est pas un oubli.
- **Un paquet neuf legitime pourrait rougir sur la clause 2.** La reponse serait alors une ligne
  d'exemption datee **et** un amendement de cet ADR disant ce que la mesure avait manque - jamais un
  relevement silencieux du seuil.

## Amendement du 2026-09-08 (s31, reaudits des deux instances de `nadia-prof`) - clause 3, deux proprietes de plus non encodables, et un plafond qui avait cesse d'en etre un

### Ce qui route cet amendement, et pourquoi le calendrier compte

**Les deux instances de `nadia-prof` ont chacune mesure un fait sur les garde-fous ecrits ce sprint
meme**, et quatre faits sont arrives par `sofia-sm` en cloture. Le plus serieux des quatre n'est pas
le plus gros : c'est **le seul motif de la seule ligne de programme que s31 ne ferme pas**
(`EMC-CP-07`), et **il est passe vert sous les deux clauses de cet ADR, ecrites le jour d'avant pour
attraper exactement sa famille**. Sa cause est de l'instance A, en une phrase :

> *"La clause 2 compare le moule des quatre premiers mots de propositions de meme longueur ne
> differant qu'en une position ; les distracteurs fautifs font trois mots (`seulement les pietons`,
> `seulement le conducteur`) ou different en plusieurs positions. **Le marqueur qui trie l'ecran pour
> l'enfant est un mot unique en tete, pas un moule de quatre mots.** Je dis la propriete, pas la
> forme du test, et je ne prescris aucun seuil."*

### La mesure, avec sa methode dans le meme paragraphe

*Toutes les mesures de cet amendement portent sur le **contenu servi entier** - les repertoires de
contenu de production des modules declares au pom agregateur -, soit **63 paquets a propositions et
1 165 items, dont 1 051 portent au moins deux distracteurs**. Le contenu a grossi depuis la mesure du
2026-09-07 (55 paquets, 1 077 items) : les quatre paquets de sciences CM1 et d'EMC CP de s31 y sont
entres. Etat mesure : worktree `s31-theo-gardefous` sur `round-two` a `d0c3c494`, JDK que la machine
virtuelle rapporte : **21.0.10**. Aucun chiffre ci-dessous ne vient d'un exemple ni d'une fixture de
ma main : ils viennent tous du contenu que le depot sert.*

### 1. Clause 3 - le marqueur de restriction

**Un mot de restriction ne figure pas sur tous les distracteurs d'un item et sur aucune part de sa
bonne reponse, sur deux items ou plus d'un meme paquet.**

Le critere, terme par terme :

- **mot de restriction** : une entree du lexique `MARQUEURS_DE_RESTRICTION`, sept mots
  (`seulement`, `uniquement`, `seul`, `seule`, `seuls`, `seules`, `sauf`) ;
- **sur tous les distracteurs** : la comparaison porte sur les mots, **jamais sur une position**.
  `nadia-prof` decrit un marqueur *"en tete"* ; encoder la position aurait manque l'item 9 du paquet
  fautif, `les enseignants seulement`. C'est pourquoi la clause en compte **quatre** la ou une
  lecture en tete en compte trois - sa fourchette annoncee etait *"3 a 4 items sur 5"*, et les quatre
  que la clause nomme sont les siens ;
- **aucune part de la bonne reponse** : l'item 11 du meme paquet - *"seulement les adultes"* en bonne
  reponse, la loi qui vise les adultes seuls - est **vert**, et il doit l'etre. C'est le
  contre-exemple que le paquet porte lui-meme ;
- **deux items ou plus** : un item isole ou la bonne reponse est la seule a ne pas restreindre reste
  un item ou l'enfant doit savoir a qui la loi s'applique. C'est **la repetition** qui apprend a
  presser le bouton sans *seulement*.

### 2. Ce que ce reglage a coute a trouver, et les deux formulations mesurees avant lui

| Critere mesure sur les 63 paquets | Items marques | Paquets | Rang du paquet refuse |
|---|---|---|---|
| Un mot quelconque sur tous les distracteurs, absent de la reponse | **194** sur 1 165 | **29** | **9e**, derriere `cp-nature-mot` (16/16) et `cp-qlm-vivant-non-vivant` (13/14), tous deux valides |
| Le meme, restreint aux items a deux distracteurs | **86** sur 1 051 | **23** | derriere `cm1-histoire-geographie` (11/57), valide |
| Le meme, en exigeant le **premier mot** partage (la forme litterale de `nadia-prof`) | **140** | **24** | **7e** |
| **Le critere retenu** : lexique de restriction, items a deux distracteurs | **4** | **1** | **le paquet refuse, seul** |

**Aucun seuil ne separe le defaut du contenu valide sur les trois premieres formulations** : le
paquet refuse n'y est jamais premier. Le seul discriminant est **la nature du mot**, et c'est ce qui
oblige au lexique. Deux exclusions du lexique portent chacune leur chiffre :

- **`que` et `qu` retires** : ils produisent un faux rouge sur `cm1-emc-fait-opinion`, **un item**,
  paquet valide, dont deux distracteurs portent *"en la croyant parce que ..."*. La conjonction de
  subordination est bien plus frequente que la particule restrictive, et un enfant ne trie pas un
  ecran sur `que`. Retires **sur ce chiffre**, pas sur une intuition ;
- **`juste` retire** : il dit *correct* autant que *seulement*, et le contenu servi n'en porte
  **aucune** occurrence en proposition. L'inclure n'aurait mordu que du futur ambigu.

**Le plancher de deux distracteurs n'est pas un reglage de confort.** Un item a deux propositions
n'oppose pas une bonne reponse a des distracteurs semblables : il oppose **deux etiquettes d'une
dichotomie fermee**, et n'importe quel mot de l'une est absent de l'autre. Sans ce plancher, 194
items rougissent et le premier est `cp-nature-mot`, 16 sur 16.

**Marge du reglage, dite plutot que tue** : le paquet fautif porte **quatre** items marques, et
**aucun autre paquet servi n'en porte un seul**. Le plancher de deux a donc deux items de marge de
chaque cote, et il n'est pose sur aucune valeur qui fait juste passer.

### 3. Ce que la clause 3 ne mord pas, et je l'assume comme un critere enumere

**Le lexique est ecrit a la main, donc la clause est verte par defaut sur tout marqueur qu'elle ne
nomme pas.** Un paquet qui ecrirait *"rien que les pietons"*, *"a part le conducteur"* ou *"hormis
les adultes"* passerait devant elle sans rougir. **C'est un critere enumere assume au sens
d'`ADR-0039`**, et il en paie le prix complet :

- l'aveu est ecrit dans la source du garde-fou, sous la formule figee qu'`ADR-0039` cherche ;
- `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` entre dans
  `CRITERES_ENUMERES_DECLARES`, et `PLAFOND_DE_CRITERES_ENUMERES` **monte de 2 a 3** dans le meme
  diff, comme il etait monte de 1 a 2 le 2026-08-19 ;
- **ce que ce recensement exige et que voici** : quel objet reel passerait vert devant lui - les
  trois formulations ci-dessus - et pourquoi aucun critere non enumere ne le remplace : **86 items
  dans 23 paquets, dont 22 valides**.

**Ce qui distingue son cas des deux declarants precedents, et c'est a son desavantage** :
`AucunVocabulaireDeMatiereTest` et `TexteAccentueExposeParLePortEntrantTest` ont un complement qui ne
s'atteint que par une decision de `camille-po` et de `nadia-prof` - une matiere neuve, un niveau
neuf. Le complement du lexique de restriction depend de la langue, et **il ne se ferme pas**. Trois
des sept entrees nomment quelque chose aujourd'hui - `seulement` (12 occurrences, 3 paquets), `seul`
(5, 2), `seule` (2, 2) ; les quatre autres ne paraissent nulle part. Elles restent, et le jeu fautif
de la clause 3 est la seule preuve qu'elles mordent.

### 4. Le cliquet de la clause 3, et pourquoi une exemption ne blanchit rien ici

`cp-emc-regle-souhait-loi` **est servi et il est refuse** : `nadia-prof` a maintenu le refus
d'`EMC-CP-07` le 2026-09-08, borne d'audit epuisee, motif route en story de s32. La clause serait
donc rouge des son ecriture, et un depot dont la porte technique est rouge n'a plus de porte.

**Un paquet exempte, nomme, date ; plafond a un**, et l'objection qui vient d'abord recoit sa
reponse : l'`ADR-0086` initial ecarte *"exempter du contenu valide, qui transformerait un verdict de
`nadia-prof` en dette technique"*. **Ce paquet n'est pas valide, il est refuse.** L'exemption
n'inverse aucun verdict : elle **enregistre** celui qui existe, et elle le rend visible en CI a
cote d'un compte. Le jour ou la story de s32 reecrit la tache B, l'exemption devient rouge et doit
disparaitre dans le meme diff - `laDetteNePeutNiGrandirNiPourrir` le tient, sur les deux dettes,
tenues separement parce qu'elles n'exemptent pas la meme clause.

### 5. La preuve de la morsure, sur le materiau que `nadia-prof` nomme

| Epreuve | Ce qu'elle prouve |
|---|---|
| `leContenuQuiPorteLeRefusMaintenuFaitRougirLaClauseTroisSansSonExemption` | Sur `cp-emc-regle-souhait-loi` **tel qu'il est servi**, sans son exemption, la clause nomme **4 items portent un marqueur de restriction (seulement)** et **items 7, 8, 9, 10**. Ce ne sont pas mes items, ce sont les siens |
| `troisPaquetsFautifsFontRougirChacuneLeurClause` | Un troisieme jeu fautif trie ses ecrans par `uniquement` et `sauf`, **les deux entrees du lexique que le contenu servi ne porte nulle part**, et son troisieme item porte le marqueur en fin de proposition |
| `unItemADeuxPropositionsNEntrePasDansLaPopulationDeLaClauseTrois` | Deux items binaires portant `seulement` exactement comme le paquet refuse le porte restent verts. Un assouplissement futur du plancher les fera rougir |
| **Mutation** : la ligne de la clause 3 retiree de `verifierLesPaquets` | **2 tests rouges sur 9**, dont celui qui porte le materiau reel |
| **Mutation** : `DOMINATION_MAXIMALE` porte de 2 a 5 (section 7) | **1 test rouge sur 11** dans `adapter-content` |

**Neuf tests dans le garde-fou** (sept avant cet amendement), tous verts.

### 6. Deux proprietes de plus que je n'encode pas - et le titre de cet ADR en compte trois

**Le titre dit *trois proprietes voisines*. Elles sont cinq depuis aujourd'hui.** Le nom du fichier
n'est pas reecrit : c'est une reference que d'autres documents resolvent (`ADR-0066`), et la renommer
casserait plus que la precision qu'elle gagnerait. **C'est cette section qui porte le compte exact.**

#### 4. *Deux items d'un meme paquet n'enseignent pas le meme fait sous deux habillages* - refusee

Routee par l'instance B (`DEV-R1`), avec un fait sur le test neuf de `lea-dev` : *"il exige bonne
reponse **et** distracteurs identiques - la paire poussin/chenille passe dessous"*. **Le fait est
exact**, verifie dans la source : le test compare `bonneReponse()` et l'ensemble des distracteurs, et
la paire ne partage que la premiere.

| Critere mesure | Paires trouvees | Paquets | La paire routee est-elle mordue ? |
|---|---|---|---|
| Bonne reponse repetee, plancher de **3 mots** (celui de la clause 1) | 59 | 13 | **non** - *"la naissance"* fait deux mots |
| Bonne reponse repetee, plancher de **2 mots** | **135** | **23**, tous valides | oui, avec 134 paires de contenu valide |
| Bonne reponse repetee **plus** un mot d'enonce partage vu dans au plus 2 items du paquet | **30** | **19** | oui - **et 29 paires dans 18 paquets valides** avec elle |

**Le critere le plus fin que j'ai su ecrire accuse 29 paires valides pour en attraper une.** Et ce
qu'il accuse n'est pas un hasard : `cm1-sciences-regimes-alimentaires` items 1 et 4 partagent *herbe*
et repondent tous deux *herbivore* - c'est **la tache**, validee par l'instance B ce sprint meme.
`cp-semaine` partage *mardi* et repond *mercredi*. La signature textuelle du defaut est celle de
l'exercice de classement legitime, **exactement l'ecueil de la clause 1 sous une autre face**. Ce qui
les separe est que le poussin et la chenille enseignent **le meme fait** - sortir de l'oeuf, c'est la
naissance - et cela, aucune chaine ne le dit.

**Sous la garde de qui elle reste** : `nadia-prof`, par `DEV-R1`, ouverte et non bloquante,
routee a `camille-po` avec son regime de rejeu. **Et je n'ai pas resserre le test de paquet de
`lea-dev`** : le resserrer a *"meme bonne reponse"* le rendrait rouge aujourd'hui, sur un paquet que
la porte pedagogique a valide sous reserve, et le paiement de `DEV-R1` est un geste de contenu, pas
de garde-fou. Une exemption pour trois jours n'est pas un cliquet, c'est du bruit.

#### 5. *Un item teste la propriete qu'il annonce* (`MAT-R1`) - refusee, et c'est une propriete de sens

Routee par l'instance B : *"un item teste la propriete qu'il annonce n'a aucun garde : c'est
`MAT-R1`, et c'etait le coeur de mon refus."* L'item de la bouteille annonce *"il faut un materiau
impermeable"* et c'est la retention du gout qui departage.

Le seul critere textuel voisin est *deux items annoncent le meme critere et portent deux bonnes
reponses differentes*. Mesure : **673 paires dans 22 paquets**, dont l'item de la bouteille est
**une**. Et le chiffre qui clot la question est **interne au paquet reserve** : *"il faut un materiau
souple"* y sert deux items - **le plastique** et **le tissu** - et `nadia-prof` a **explicitement
autorise** cette repetition dans le meme document ou elle ouvre `MAT-R1`. **Le critere ne distingue
donc meme pas les deux items du meme paquet**, l'un reserve et l'autre autorise par elle.

Ce qui manque au critere n'est pas un seuil : c'est de savoir **quel materiau porte quelle
propriete**. C'est un fait du monde, pas une chaine.

**Sous la garde de qui elle reste** : `nadia-prof`, entierement, par `MAT-R1`. Une regle syntaxique
ecrite ici ferait croire la propriete gardee, ce qui est pire que de la savoir non gardee.

### 7. `FAB-1b` : un plafond trois crans au-dessus de sa dette n'est pas un plafond

**Le fait de l'instance B est exact, et je l'ai verifie dans la source plutot que dans sa
description.** Le test de `cm1-sciences-familles-materiaux` exigeait *six bonnes reponses distinctes*
(`hasSize(itemsDeChoix().size())`) ; il compare desormais la frequence maximale a `choix.size()`,
donc **il accepte cinq bonnes reponses identiques sur six**. Sa lecture du 2026-09-07 dit que la
repetition est licite *"des lors que la bonne reponse varie"* ; elle ne demandait pas ce relachement,
et **elle ne fixe aucun plafond**.

**C'est la famille du cliquet, et le depot l'a deja sanctionnee** : un plafond dont la valeur depasse
la dette qu'il garde ne garde rien (`ADR-0013`, `ADR-0067`). Ici l'ecart est de **trois crans** -
plafond a 5, dette mesuree a **2** (six items de choix, cinq materiaux, le plastique deux fois, sur
la souplesse et l'impermeabilite).

**La borne vaut desormais 2, et ce 2 est ma decision, pas sa lecture.** Elle est ecrite, nommee,
motivee, et sans marge - un troisieme item sur le meme materiau, c'est la moitie de la tache derriere
un seul bouton. **La relever demande un amendement ecrit**, pas une expression qui suit la taille du
paquet. La morsure est arithmetique et non textuelle : la borne refuse la distribution que l'ancienne
ecriture acceptait (cinq sur six) et celle qui la depasse d'un cran (trois sur six), et la
distribution reellement servie la passe.

**Qui a mesure quoi, et pourquoi cette phrase-ci est ecrite.** *Correction d'attribution du
2026-09-08, sur signalement de `nadia-prof` en conclusion de s31 (section 5.6), le meme jour :* la
redaction precedente de ce paragraphe disait *"la borne vaut desormais exactement ce que sa lecture
autorise : deux"*, et la Javadoc du test disait *"la lecture de `nadia-prof` du 2026-09-08 autorise un
materiau sur deux proprietes differentes et pas davantage"*. **Les deux lui attribuaient un plafond
qu'elle n'a pas ecrit.** Le partage exact :

| Ce qui est de `nadia-prof` | Ce qui est de moi |
|---|---|
| **Le fait** : le garde-fou accepte cinq bonnes reponses identiques sur six la ou il exigeait six distinctes - *"varier n'est pas ne pas etre constant"*. Verifie dans la source, exact | **Le plafond de 2**, decision d'architecture prise sur la mesure ci-dessus |
| **La barre pedagogique** : la repetition est licite *"des lors que la bonne reponse varie"*. Elle ne chiffre rien | **Le choix de faire du chiffre mesure une borne**, plus strict que sa barre |
| **L'observation du plastique** sur la souplesse et l'impermeabilite, citee par elle comme une mesure du contenu servi | **La lecture de cette observation comme une limite**, qu'elle n'a pas faite |

Elle ne contredit pas ce 2 - *"un garde-fou plus strict que ma barre est dans son droit, et celui-la
est utile"* - et je ne le change pas. **Ce qui change est la paternite ecrite**, et le motif est une
regle de ce depot avant d'etre une politesse : un chiffre attribue a la porte pedagogique devient
inattaquable pour de mauvaises raisons, et son auteur reel n'est plus celui qui doit le defendre.
C'est la meme famille que l'exemption qui *"transforme un verdict de `nadia-prof` en dette
technique"*, ecartee plus bas dans les alternatives de cet ADR.

**La borne est atteinte sans marge : je confirme le constat, et je dis ce qu'il produit.** Six items
de choix, cinq materiaux, frequence maximale **2** : la borne vaut exactement la distribution servie.
Donc **le prochain paquet - ou la prochaine reprise de celui-ci - qui poserait un troisieme item sur
un meme materiau est rouge**, et rouge sur une borne d'architecture, pas sur un verdict pedagogique.
Ce n'est pas un effet de bord, c'est la propriete qu'`ADR-0013` exige d'un cliquet : un plafond qui
depasse la dette qu'il garde ne garde rien, et celui-ci ne la depasse pas d'un cran. **Ce que celui
qui rencontrera ce rouge doit savoir** : la marche a suivre n'est pas de relever la borne dans le test
mais d'amender cet ADR - une ligne ecrite qui dise quel materiau porte trois proprietes reelles,
verifiee par `nadia-prof` puisque c'est une question de contenu. La borne est **volontairement**
plus etroite que sa barre : elle refuse une distribution qu'elle autoriserait, et c'est le prix
assume d'un cliquet exact sur un seul paquet.

**Ce que je ne fais pas** : etendre cette borne a tous les paquets. Une clause generale se
calculerait **par tache**, et un paquet ne declare pas ses taches - c'est deja le declencheur ecrit
au bas de cet ADR, et il commence par une decision de contenu.

### 8. Ce que cet amendement change aussi, en une ligne

Les messages de la clause 1 nommaient les items **a partir de zero**, ceux de la clause 3 les
nomment a partir de un. Deux numerotations dans les messages d'un meme garde-fou coutent un decalage
a chaque lecture, et c'est `nadia-prof` qui les lit : **la clause 1 numerote desormais comme elle**,
a partir de un. Aucune assertion ne portait sur ces rangs.

## Alternatives considerees

- **Encoder les cinq proprietes.** Ecartee sur mesure : trois d'entre elles rendent rouges 39, 3 et
  16 objets valides, et deux d'entre elles ne mordent pas le paquet que `nadia-prof` a refuse.
- **N'encoder aucune des cinq et router la famille entiere.** Ecartee : deux clauses sur cinq
  mordent le materiau reel sans un seul faux positif, et un quatrieme sprint sans garde-fou sur
  cette famille est un quatrieme sprint ou le budget d'audit paie des reaudits.
- **Encoder les cinq et exempter le contenu valide qui rougit.** Ecartee, et c'est la plus
  tentante : une exemption qui nomme du contenu valide par la porte pedagogique **transforme un
  verdict de `nadia-prof` en dette technique**, sans qu'elle l'ait dit. Ce n'est pas mon geste, et
  c'est exactement la frontiere qu'`ADR-0079` trace.

## Impact

- `bootstrap/src/test/java/com/recreo/archi/LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest.java`
  (neuf ; clause 3 et son cliquet a l'amendement du 2026-09-08)
- `bootstrap/src/test/resources/contreexemple/LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest/`
  (neuf : un etat de l'historique, deux jeux fautifs ; un troisieme jeu fautif a l'amendement du
  2026-09-08)
- `bootstrap/src/test/java/com/recreo/archi/LesGardeFousSontMisALEpreuveTest.java` (amendement du
  2026-09-08 : un declarant de critere enumere de plus, plafond de 2 a 3)
- `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCm1SciencesFamillesMateriauxTest.java`
  (amendement du 2026-09-08 : la borne de `FAB-1b` vaut la dette mesuree)
- `squad/decisions/adr/ADR-index.md`

## Ce qui me ferait changer d'avis plus tard

- **Si `nadia-prof` refusait un paquet pour une repetition partielle** - quatre items identiques sur
  cinq -, le plancher de trois items descendrait, et il faudrait remesurer les paquets a ensemble
  ferme avant de le faire.
- **Si un paquet declarait ses taches** au lieu de les laisser deviner - ce que `FER-3` compte a la
  main -, les deux clauses se calculeraient par tache et non par paquet, et `OBJ-R2` deviendrait
  peut-etre encodable a son tour. C'est le declencheur le plus probable, et il n'est pas de mon
  ressort : il commence par une decision de contenu.
