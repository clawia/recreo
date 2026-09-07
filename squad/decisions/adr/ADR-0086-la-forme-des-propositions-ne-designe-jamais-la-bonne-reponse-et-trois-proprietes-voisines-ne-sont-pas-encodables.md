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

**Sept tests, tous verts.**

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
  (neuf)
- `bootstrap/src/test/resources/contreexemple/LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest/`
  (neuf : un etat de l'historique, deux jeux fautifs)
- `squad/decisions/adr/ADR-index.md`

## Ce qui me ferait changer d'avis plus tard

- **Si `nadia-prof` refusait un paquet pour une repetition partielle** - quatre items identiques sur
  cinq -, le plancher de trois items descendrait, et il faudrait remesurer les paquets a ensemble
  ferme avant de le faire.
- **Si un paquet declarait ses taches** au lieu de les laisser deviner - ce que `FER-3` compte a la
  main -, les deux clauses se calculeraient par tache et non par paquet, et `OBJ-R2` deviendrait
  peut-etre encodable a son tour. C'est le declencheur le plus probable, et il n'est pas de mon
  ressort : il commence par une decision de contenu.
