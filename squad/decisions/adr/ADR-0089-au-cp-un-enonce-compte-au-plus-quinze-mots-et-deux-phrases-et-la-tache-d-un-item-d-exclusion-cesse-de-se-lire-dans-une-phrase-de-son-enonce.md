# ADR-0089 - Au CP, un enonce compte au plus quinze mots et au plus deux phrases, et la tache d'un item d'`exclusion` cesse de se lire dans une phrase de son enonce

- **Statut** : Accepte
- **Date** : 2026-09-08
- **Sprint** : s32
- **Auteur** : `theo-archi`
- **Route par** : `nadia-prof`, section 16 de
  `squad/conformite/ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md`,
  qui pose `LON-1` et `LON-2` avec leur convention de comptage et ecrit : *"Elle est entierement
  automatisable, et c'est le plus mecanique de tous les garde-fous que j'aie demandes. Je dis la
  propriete ; la forme du test appartient a `theo-archi`. Tant qu'il n'existe pas, `LON-1` et
  `LON-2` sont tenues par moi, en comptant."* Et par `sofia-sm`, qui m'a route `EXC-R5` avec son
  constat technique
- **Arbre mesure** : worktree `s32-theo2`, branche `s32-theo2` partie de `round-two` a `7ed471a7`
  (l'arbre fusionne de s32). JDK **21.0.10**, version que la machine virtuelle des tests rapporte
  d'elle-meme et non celle qu'une entree de toolchain declare (`ADR-0071`). Toute mesure partielle du
  reacteur porte `-am` et nomme son module ; les mesures de population sont rendues a l'identique par
  le garde-fou et par un compteur independant
- **Prolonge** : `ADR-0013` (perimetre derive, garde-fou mis a l'epreuve), `ADR-0051` (un garde-fou
  dit combien d'objets il examine), `ADR-0067` (cliquet a plafond exact), `ADR-0078` (le niveau est
  lu dans le paquet), `ADR-0087` (le mecanisme d'une exemption se prouve sur un materiau qui survit
  au paiement), `ADR-0088` (un garde-fou qui ecarte un objet le dit en rouge)
- **N'ecrit aucun contenu, ne prononce aucun Done, ne deplace aucun statut de couverture.** Trois
  chiffres de `nadia-prof` sont corriges ici parce que la mesure les dement ; **les trois reserves
  qu'ils portent ne sont pas touchees**, elles lui sont rendues au chapitre *Ce que je route*.

---

## Contexte

### 1. Le fait, et il n'est pas de moi

`nadia-prof` a pose le 2026-09-08 deux barres chiffrees :

> **`LON-1`** - au CP, un enonce compte **au plus 15 mots**.
> **`LON-2`** - au CP, un enonce compte **au plus deux phrases**, et quand il en compte deux, la
> seconde est la question.

Et elle a pose la convention de comptage **avant** le chiffre, pour que la barre soit verifiable :

> Un mot est une suite de caracteres separee par des espaces et contenant au moins une lettre ou un
> chiffre ; **une forme elidee compte pour un mot** (`qu'il`, `l'ami`, `Qu'est-ce`). Une phrase est
> un segment termine par un point, un point d'interrogation ou un point d'exclamation.

Sa section 16.6 conclut que rien ne l'execute : *"Tant qu'il n'existe pas, `LON-1` et `LON-2` sont
tenues par moi, en comptant."*

### 2. Ce que cette absence a coute ce sprint, mesure et non suppose

- `sofia-sm` a compte les 22 enonces de son lot d'EPS **par un script a la main**, hors du depot.
- `lea-dev` a ramene onze enonces de `cp-emc-regle-souhait-loi` de 13-30 mots a 12-15 mots, et
  **aucun automate ne l'a verifie** : le commentaire du paquet affirme la conformite, rien ne
  l'execute.
- Les trois reserves de longueur ouvertes par `nadia-prof` (`EMO-R1`, `EXC-R4`, `POL-R1`) sont
  suivies a la lecture, sprint apres sprint.

Une barre chiffree accompagnee de sa convention de comptage est **le cas le plus favorable qu'un
garde-fou puisse recevoir**. La laisser sans regle est exactement ce que `CLAUDE.md` appelle un
souhait.

### 3. Ma mesure reproduit la sienne, chiffre par chiffre, avant que je pose quoi que ce soit

Sur l'arbre ou elle a compte - `690f0cde`, cloture de s31 -, avec la convention de 16.1 :

| Chiffre de sa section 16.3 | Ma mesure sur `690f0cde` | |
|---|---|---|
| 33 paquets de CP | 33 | ok |
| 506 enonces | 506 | ok |
| Mediane 6 mots | 6 | ok |
| 90e centile 13 | 13 | ok |
| 95e centile 15 | 15 | ok |
| Maximum 30 | 30 | ok |
| 23 enonces au-dela de 15 mots | 23 | ok |
| 7 phrases au-dela de 15 mots | 7 | ok |
| 606 phrases | 606 sous son critere **litteral** (584 sous le critere derive du chapitre 5) | voir 5 |

**Huit chiffres reproduits a l'identique sur huit ; le neuvieme, les 606 phrases, se reproduit
sous son critere litteral et vaut 584 sous le critere derive du chapitre 5 - c'est ce que ce chapitre
instruit.** C'est ce qui autorise a poser une regle sur sa convention : elle est
reproductible par un tiers sans lui demander comment il faut compter.

### 4. Trois de ses comptes sont faux, et c'est l'automate qui les trouve

Elle avait deja corrige quatre de ses propres chiffres en section 16.2, *"tous d'une unite, tous
vers le haut, parce que je comptais les formes elidees pour deux"*. Trois de ses comptes de la
section 16.5 restent faux, et tous trois vont dans le meme sens - **une reserve sous-compte les
items qu'elle nomme** :

| Ce qu'elle ecrit (section 16.5) | Mesure sur `690f0cde`, convention de 16.1 |
|---|---|
| `EMO-R1` : *"item 4 a 17 mots, item 5 a 18"*, et *"Les **neuf autres** du paquet sont conformes"* | **Trois** items au-dela de 15 : 4 (**17**), 5 (**18**) et **6 (16 mots)**. L'item 6 n'est pas conforme, il n'est pas nomme, et il est compte parmi les neuf declares conformes |
| `EXC-R4` : *"les deux plus longs a 16 et 18 mots"*, et *"**Deux** enonces a ramener a 15 au plus"* | **Trois** items au-dela de 15 : 2 (**16**), 10 (**16**), 11 (**18**). Les deux longueurs qu'elle cite sont exactes ; c'est le nombre d'enonces a reecrire qui en compte un de moins |
| `POL-R1` : *"cinq enonces de la tache B a 20, 22, 23, 24 et 25 mots"* | Les cinq items de la tache B mesurent **19, 21, 22, 23 et 25** - les figures non recomptees sous sa propre convention de 16.2, sauf la derniere. Et le paquet porte **neuf** enonces au-dela de 15, tache A comprise |

**Aucun de ces ecarts ne change un verdict** - les enonces qu'elle nomme sont bien trop longs - et
je n'en tire aucune conclusion pedagogique. Ils changent le **compte** d'une dette, donc ils
changent la valeur d'un cliquet, donc ils devaient etre mesures avant que je pose le cliquet.
C'est la raison d'etre du chapitre 3.

### 5. La definition litterale de la phrase est fausse sur le contenu servi, et c'est mesure

Son critere - *un segment termine par un point, un point d'interrogation ou un point
d'exclamation* - applique tel quel a `"0 + ? = 10"` rend **deux** phrases, dont la seconde,
`"= 10"`, n'est pas une question. `LON-2` deviendrait donc rouge sur **22 enonces** de
`cp-complements-10` et de `cp-complements-10-saisie`, deux paquets servis dont aucune reserve de
longueur ne parle. Le `?` y est **le trou a completer**, pas une ponctuation.

Ce n'est pas une lecture de mon cru : son propre releve de la section 16.3 nomme quatre paquets
comme portant des enonces a deux phrases - `regles-de-vie`, `problemes-additifs`,
`permis-interdit`, `emotions-autrui` - et **aucun des deux paquets de complements a 10 n'y
figure**. Elle a compte en lisant, et en lisant `0 + ? = 10` est une seule expression.

### 6. `EXC-R5` est bloquee par un garde-fou, pas par son contenu

`EXC-R5`, ouverte le 2026-09-08 : *"Aucun item ne fait d'un adulte reel une reponse fausse. Le
critere reel est la presence de l'adulte : que la question le dise (quel adulte est la a ce
moment ?) plutot que 'a qui doit-il en parler ?'"*

`PaquetCpEmcExclusionTest` distinguait les deux taches du paquet par une sous-chaine de l'enonce :

```java
private static boolean estDeTacheAdulte(Question item) {
    return item.enonce().contains("À qui");
}
```

**La reserve demande de reecrire exactement cette phrase-la.** Mesure sur le contenu servi : la
reecriture supprime les cinq occurrences de `À qui`, les cinq items de la tache B basculent dans la
tache A, et `fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems` echoue sur
`adulte > 0`. Le blocage est technique de bout en bout : rien dans la structure des taches ne change.

---

## Decision

### A. Au CP, un enonce compte au plus quinze mots

Sous la convention de comptage de `nadia-prof` (section 16.1). Le perimetre est le champ `enonce`
des paquets dont **le descripteur porte `CP`** (`ADR-0078`), dans le contenu servi de chaque module
declare au pom agregateur.

### B. Au CP, un enonce compte au plus deux phrases, et la seconde est une question

Meme perimetre. Le test operatoire de *"la seconde est la question"* est qu'elle se termine par un
point d'interrogation.

### C. Une fin de phrase est un `.`, un `?` ou un `!` qui termine le texte ou qu'une majuscule suit

Derivation, pas reglage : elle ne nomme aucun paquet, aucun signe de calcul, aucune exception, et
elle est ecrite sur le chiffre du chapitre 5 - **0 enonce fautif la ou le critere litteral en rend
22**.

### D. La barre ne vaut ni pour le CM1, ni pour les propositions, ni pour l'indice, ni pour la consigne

C'est sa frontiere, ecrite en toutes lettres en 16.6 sur le motif qu'elle n'a pas compte ces
populations. Un garde-fou qui deborderait poserait un poteau qu'elle n'a pas pose.

### E. La tache d'un item de `cp-emc-exclusion` se lit dans sa bonne reponse, et le partage des taches est exhaustif

`estDeTacheAdulte` lit `ADULTES_VALIDES.contains(item.bonneReponse())`. Un item dont la bonne
reponse n'est ni un classement ni un adulte n'appartient a aucune des deux taches : **il est rouge
et il nomme sa reponse**, au lieu d'etre invisible de tous les comptages.

**Cette decision est locale a ce paquet, et je refuse d'en faire la regle generale** : voir les
alternatives, chapitre 3, avec le chiffre qui l'ecarte.

---

## Regles executables

### Clauses A a D - `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`, sept tests

`bootstrap/src/test/java/com/recreo/archi/AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.java`

**Ce n'est pas une `ArchRule`, et ce diff n'en ajoute aucune : le compte de regles ArchUnit du
depot est inchange.** ArchUnit lit du bytecode ; la longueur d'un enonce de paquet YAML n'y est pas
une propriete lisible. Je ne recopie pas le compte lui-meme : il n'est asserte par aucun test, la
seule mesure que j'ai faite ici (`grep "ArchRule "` sur les sources hors `target`) rend **39** en
comptant les arbres de contre-exemple, et un chiffre sans son perimetre est ce que `CLAUDE.md`
sanctionne depuis l'ecart de 29 annonce contre 25 reel.

Le coeur des deux clauses :

```java
private static final Pattern FIN_DE_PHRASE = Pattern.compile(
        "[.?!](?=" + ESPACES + "*$)|[.?!](?=" + ESPACES + "+\\p{Lu})");
```

`ESPACES` vaut `[\s ]`, et ce n'est pas un detail : **`\s` de Java ne contient pas U+00A0**, et
`TYP-1` pose une espace insecable devant chaque `?` final. Sans U+00A0 dans la classe, `"regle ?"`
serait un seul token et le comptage de mots serait faux sur les **938 espaces insecables**
que portent les enonces du contenu servi (tous niveaux, mesure du 2026-09-08 ; le contenu n'emploie
que U+0020 et U+00A0).

**Population, ecrite plutot qu'esperee (`ADR-0051`)** : 36 paquets de CP, **528 enonces**, 610
phrases, dont **82 enonces a exactement deux phrases** - la population de la seconde moitie de
`LON-2`. Les trois assertions de `leBalayageExamineTousLesEnoncesDeCp` posent des bornes larges sous
cette mesure, parce qu'un vert doit porter sur tous les enonces de CP servis et non sur une poignee
qu'un chargement aurait laisse tomber.

**Cliquet de `LON-1`, et le mecanisme est l'egalite, jamais un plafond** (`ADR-0087`, amendement de
la cloture de s31) :

```java
private static final Map<String, Integer> DETTE_DE_LONGUEUR = Map.of(
        "cp-emc-emotions-autrui.yaml", 1,
        "cp-emc-exclusion.yaml", 1,
        "cp-emc-formules-de-politesse.yaml", 3);
private static final int PLAFOND_DE_DETTE = 5;
```

La valeur est le nombre **exact** d'enonces au-dela de 15 mots que le paquet porte encore. Un de plus
est rouge, **un de moins est rouge aussi**, ce qui force a baisser le chiffre dans le diff qui paie.
Tout paquet de CP absent de la table est borne a zero. Les trois paquets nommes portent chacun une
reserve de longueur ouverte par `nadia-prof` le 2026-09-08 : cette dette **enregistre** trois
reserves deja ouvertes, elle n'en cree aucune et n'inverse aucun verdict (frontiere d'`ADR-0079`).
Elle est en droit de ne pas etre vide, `nadia-prof` ayant ecrit que sa barre *"ne vaut que pour les
enonces ecrits ou reecrits a partir du 2026-09-08"*.

**Cliquet de `LON-2` : plafond zero sur 528 objets, et non une regle a population vide** - la clause
n'exempte rien, et `PLAFOND_DE_DETTE_DE_FORME = 0` est compare sur le contenu vivant.

**Trois morsures, dont deux sur du materiau reel** (`ADR-0013` obligation 3) :

1. `lEtatQuiPorteLaReserveDeLongueurFaitRougirLesDeuxClauses` - `cp-emc-formules-de-politesse` tel
   qu'il etait servi a `690f0cde`, **l'etat sur lequel `POL-R1` a ete ouverte et la barre posee**,
   recopie octet pour octet sous `CONTRE_EXEMPLE` pour que la preuve survive au paiement de la
   reserve. `LON-1` y nomme **neuf** enonces, `LON-2` en nomme **un**, l'item 7 en trois phrases.
2. `unEnonceArithmetiqueNeCompteQuUnePhrase` - les 22 enonces reels de `cp-complements-10` et de
   `cp-complements-10-saisie`, et le test **compare les deux criteres sur ce materiau** plutot que
   d'affirmer la difference dans un commentaire : 22 segments sous le critere litteral, zero defaut
   sous le critere derive.
3. `unEnonceDontLaSecondePhraseNInterrogePasEstRouge` - **jeu fautif de ma main, et je le declare
   comme tel** : les 82 enonces de CP a deux phrases tiennent tous cette moitie, et l'historique n'en
   porte aucun contre-exemple. Sans fixture, cette moitie n'aurait aucune preuve qu'elle mord.

**Le perimetre est prouve par son bord, sur le materiau qui le prouve** :
`lesEnoncesDeCm1SontUnePopulationReelleQueLaBarreNeVoitPas` charge le CM1 avec le meme code et
constate ce qu'un debordement couterait - **165 enonces de CM1 au-dela de 15 mots** (maximum 36) et
**35 au-dela de deux phrases**, puis verifie qu'aucun n'entre dans la population bornee.

**Et la morsure qui decide de tout le reste, parce qu'elle porte sur le contenu vivant.** Mutation de
`cp-qlm-vivant-non-vivant`, paquet de CP servi hors dette, non commitee : un enonce porte a 17 mots
et un autre a trois phrases rendent **2 tests sur 7 rouges**, en nommant le paquet, le rang de
l'item, le compte et la clause. Sans cette mutation, ce fichier serait un vert qui ne prouve rien.

**Ce que cette mutation m'a appris sur ma propre verification, et qui merite d'etre ecrit.** Ma
premiere tentative de mutation **n'a rien mute** : la chaine que je cherchais portait une espace
ordinaire devant son `?`, le fichier une espace insecable, et le remplacement a echoue en silence.
Le garde-fou est reste vert, et j'ai eu sous les yeux, pendant une minute, la preuve apparente qu'il
ne mordait pas. C'est la faute de s05 dans sa forme la plus pure - **une detection qui cherche une
sous-chaine que le monde n'ecrit pas comme je l'imagine** -, cette fois du cote de l'outil de
verification et non de la regle. Elle est reperee parce que l'attente etait ecrite avant la mesure.

### Clause E - `PaquetCpEmcExclusionTest`, un test neuf et deux assertions retirees

`adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCpEmcExclusionTest.java`

`chaqueItemAppartientAExactementUneDesDeuxTaches` verifie le partage par un **ou exclusif** sur les
deux appartenances, item par item, puis que la somme des deux taches vaut le paquet entier. Sans ce
test, classer sur la bonne reponse creait un troisieme sac muet - le faux vert par ecartement
silencieux qu'`ADR-0088` vient de payer sur cinq garde-fous de rendu.

**Deux assertions sont retirees parce qu'elles sont devenues tautologiques**, et leur propriete est
reprise ailleurs : *"chaque item de la tache de classement porte l'une des deux categories
connues"* et *"chaque bonne reponse de la tache d'identification nomme un adulte reel"* etaient
vraies par construction des lors que l'appartenance se lit sur la reponse. La propriete qu'elles
gardaient - **aucune bonne reponse hors des deux listes** - est exactement ce que le partage
exhaustif verifie, sur les onze items au lieu de deux sous-ensembles. L'assertion sur les
**distracteurs** de la tache d'adulte, elle, n'est pas tautologique et reste.

**Preuve que le deblocage debloque**, mesuree et non annoncee : les cinq enonces de la tache B ont
ete reecrits selon la formule que `nadia-prof` propose (`À qui X doit-il en parler` devient
`Quel adulte est là`), les cinq occurrences de `À qui` ont disparu, et
`PaquetCpEmcExclusionTest` **passe vert**. Sous l'ancien critere, `itemsDAdulte()` rendait zero item
et le test de `FER-3` echouait. Mutation non commitee, revertie.

---

## Consequences

### Bonnes

- `nadia-prof` cesse de compter. Sa section 16.6 disait *"tant qu'il n'existe pas"* ; il existe.
- Un enonce de CP trop long **ne peut plus entrer en silence**, dans aucun paquet, par aucun agent :
  le perimetre est derive du contenu servi et du descripteur, jamais d'une liste de noms.
- Les trois reserves de longueur ouvertes ont un compte exact au lieu d'une lecture, et leur paiement
  **rend le test rouge**, donc il ne peut pas etre oublie a moitie.
- `EXC-R5` redevient une story de contenu ordinaire : plus aucun Java a toucher pour la payer.
- Deux chiffres de reserve sont corriges avant qu'un cliquet soit pose dessus.

### Mauvaises, et je les nomme

- **La dette de `LON-1` n'est pas vide, et elle est de trois paquets.** Un lecteur presse peut lire
  la table comme une autorisation. Elle n'en est pas une : c'est un compte exact, il ne peut pas
  monter, et son paiement est rouge. Mais tant qu'elle vit, la promesse *"au CP, aucun enonce ne
  depasse 15 mots"* est fausse de cinq enonces sur 528, et c'est ecrit ici plutot que d'etre deduit.
- **`LON-2` est a plafond zero, donc son vert ne bougera pas de longtemps.** Un garde-fou qui ne
  rougit jamais finit par n'etre plus lu. Sa seule defense est sa morsure, et l'une de ses deux
  moities n'a pour materiau qu'une fixture de ma main.
- **Le critere de fin de phrase est une derivation, pas une verite de langue.** Il compte une phrase
  de trop sur un texte qui enchainerait deux phrases dont la seconde commence par un chiffre ou une
  minuscule. Le contenu servi n'en porte aucun ; le jour ou il en porte un, ce sera un faux vert et
  non un faux rouge. Declencheur ecrit : le premier enonce de CP dont la seconde phrase ne commence
  pas par une majuscule.
- **Le compteur de mots ne compte que des mots.** Il ne voit ni une subordonnee, ni un referent
  elide, ni deux circonstances avant la question - `nadia-prof` l'ecrit elle-meme : *"Un paquet
  conforme a `LON-1` et `LON-2` peut rester illisible au CP, et je le refuserai."* Ce garde-fou est
  necessaire, jamais suffisant, et il ne prononce rien.
- **La clause E ne repare qu'un paquet.** Voir le chapitre suivant et la dette qu'il ouvre.

### La dette que j'ouvre explicitement, avec son chiffre et sa methode

**L'identite d'une tache lue dans une phrase de l'enonce est une famille, pas un cas.** Mesure du
2026-09-08 sur l'arbre `7ed471a7` : **25 fichiers sur les 95 tests de paquet** de
`adapters/adapter-content/src/test` lisent l'enonce par une operation de chaine
(`contains`, `startsWith`, `endsWith`, `matches`, `matcher`). **Methode et limite du chiffre** : c'est
un comptage par motif, et **toutes ces occurrences ne sont pas des classements de tache** - plusieurs
valident la *forme* de l'enonce, ce qui est legitime et n'a rien a voir. Le tri exact n'est pas fait,
et le 25 est donc un majorant.

**Pourquoi je n'encode pas la regle generale aujourd'hui** : elle serait rouge sur une population
qu'aucun arbitrage n'a instruite, et sa correction demanderait un **champ de tache dans le schema
d'item** - aucune des 19 cles d'item du contenu servi n'en porte -, donc une decision de schema, une
entree au recensement des cles et une reecriture de 68 paquets. Ce n'est ni le sprint, ni le
perimetre, ni une decision que je peux prendre seul.

**Declencheur ecrit** : le deuxieme paquet dont une reserve de `nadia-prof` demande de reecrire la
phrase qui porte l'identite d'une tache. Le premier est `cp-emc-exclusion`, paye ici.

---

## Alternatives considerees

1. **Ecrire `LON-1` et `LON-2` sur la definition litterale de la phrase.** Ecartee sur son
   chiffre : 22 enonces servis rouges, sur deux paquets qu'aucune reserve de longueur ne concerne et
   que son propre releve ne compte pas parmi les enonces a deux phrases. Un garde-fou qui prononce
   contre le releve de l'autorite qui l'a demande n'est pas un garde-fou.
2. **Exempter les deux paquets de complements a 10 par leur nom.** Ecartee : c'est un critere
   enumere sur le cote qui dispense de preuve (`ADR-0039`), il aurait fallu l'avouer au recensement
   de `LesGardeFousSontMisALEpreuveTest`, et il serait faux au troisieme paquet arithmetique. La
   derivation coute une expression et ne nomme personne.
3. **Etendre la barre aux propositions, puisque zero proposition de CP depasse 15 mots.** Ecartee, et
   c'est **le cas dangereux des trois** que sa frontiere nomme : l'extension serait gratuite
   aujourd'hui, donc invisible, et poserait un poteau qu'elle n'a pas pose sur une population qu'elle
   n'a pas comptee. Une regle qu'on ajoute parce qu'elle ne coute rien est une regle que personne n'a
   decidee. Pour memoire, les deux autres debordements ne sont pas gratuits : l'indice de CP porte 48
   enonces au-dela de 15 mots, le CM1 en porte 165.
4. **Etendre la barre au CM1.** Ecartee sur son chiffre : 165 enonces au-dela de 15 mots et 35
   au-dela de deux phrases. `nadia-prof` ecrit qu'elle n'a pas compte cette population et que
   `HG-R4` reste qualitative. Le remplissage de 53 items d'`histoire-geographie-cm1` a s32 et s33 est
   le moment qu'**elle** a nomme pour la mesurer.
5. **Un cliquet a plancher plutot qu'a egalite sur `LON-1`.** Ecartee : `ADR-0087` a mesure ce que
   coute un cliquet dont le plafond depasse la dette, et un plancher laisse le paiement partiel
   passer vert. L'egalite rend le paiement rouge, et c'est le seul mecanisme qui force a baisser le
   chiffre dans le meme diff.
6. **Une seule dette globale de cinq enonces, sans les repartir par paquet.** Ecartee : elle
   laisserait `cp-emc-exclusion` ajouter quatre enonces trop longs tant qu'un autre paquet en perd
   autant. La repartition par paquet est ce qui rend la dette locale a la reserve qui l'explique.
7. **Faire porter l'identite de tache par un champ d'item, pour tout le contenu.** Ecartee
   aujourd'hui, avec son chiffre et son declencheur : voir la dette ci-dessus. Elle reste la bonne
   forme le jour ou un deuxieme cas la finance.
8. **Laisser `EXC-R5` en dette technique et la rendre a `camille-po`.** Ecartee : la reserve ne
   demande aucun changement de structure de tache, et le seul obstacle etait un garde-fou de mon
   ressort. Rendre a la dette une reserve que le paiement d'un garde-fou debloque serait facturer a
   la pedagogie le prix d'un critere que j'ai la charge d'ecrire.

---

## Ce que je route, et a qui

| Fait | A qui | Pourquoi ce n'est pas a moi |
|---|---|---|
| `EMO-R1` nomme deux items au-dela de `LON-1` ; il y en a **trois** (item 6 a 16 mots). `EXC-R4` en nomme deux ; il y en a **trois**. `POL-R1` cite cinq longueurs non recomptees sous la convention de 16.2 | `nadia-prof` | Le compte est mecanique, la portee d'une reserve est pedagogique. Je ne modifie aucune de ses trois reserves, et le cliquet de `LON-1` porte le compte **reel** mesure aujourd'hui, pas le compte annonce |
| `EXC-R5` est deblocable et rendue au contenu : plus aucun Java a toucher | `camille-po`, via `sofia-sm` | Le contenu et la formulation de la question sont a `nadia-prof` et aux developpeurs |
| `EXC-R1` demande une consigne de catalogue reecrite. **Blast radius mesure : 4 echecs, un seul fichier**, `TexteAccentueExposeParLePortEntrantTest` - la mise a jour de son inventaire est le geste mecanique que `marc-dev` (`S32-1`) et `sofia-sm` (`S32-5`) ont fait chacun ce sprint | `camille-po`, via `sofia-sm` | La consigne est un texte lu par l'enfant, et l'inventaire ne peut pas etre mis a jour avant qu'elle soit ecrite. **L'obstacle technique n'existe pas** ; ce qui manque est le texte |
| Une barre de longueur pour le CM1, et le sort de `HG-R4` | `nadia-prof` | Elle ecrit qu'elle n'a pas compte cette population. Les 165 enonces de CM1 au-dela de 15 mots sont a sa disposition dans ce fichier |
