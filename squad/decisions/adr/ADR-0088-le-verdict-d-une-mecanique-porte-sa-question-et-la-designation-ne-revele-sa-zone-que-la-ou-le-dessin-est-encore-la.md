# ADR-0088 - Le verdict d'une mecanique porte la question qu'il juge, la designation ne revele sa zone que la ou le dessin est encore la, et un garde-fou qui ne sait pas juger un objet le dit

- **Statut** : Accepte
- **Date** : 2026-09-08
- **Sprint** : s32 (instruction prealable)
- **Auteur** : theo-archi
- **Route par** : `nadia-prof`, section 17.6 de
  `squad/conformite/ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md`
  (commit `b1ee89f4`), qui tranche le fond des deux trous que mon avis de faisabilite lui avait
  routes ; et par l'utilisatrice, qui finance un lot de contenu de designation
- **Arbre mesure** : worktree `s32-theo`, rebase sur `round-two` a `b1ee89f4`. JDK **21.0.10**,
  version rapportee par la machine virtuelle des tests (`ADR-0071`). Toute mesure partielle du
  reacteur porte `-am` et nomme son module

---

## Contexte

### 1. Ce qui rend cette decision due maintenant, et pas plus tot

Le palier 2 - la mecanique de designation sur image - est dans l'arbre depuis s28 et jouable par
personne. Tant qu'aucun ecran ne le servait, deux questions restaient ouvertes sans cout, et
`ADR-0084` a explicitement decide de ne pas les trancher : *"Modifier son verdict pour un ecran qui
n'existe pas serait une abstraction ecrite pour un usage imagine."* Ce motif etait juste.

Il est perime depuis le 2026-09-08 : l'utilisatrice finance **un** lot de contenu de designation, et
`nadia-prof` ecrit que **les deux trous sont bloquants** - *"Je ne valide aucun lot de designation
avant."* Ce ne sont donc plus des questions ouvertes, ce sont deux conditions d'entree.

### 2. Trou A - `DesignationVerdict` ne porte pas d'enonce, la ou les deux autres verdicts en portent un obligatoire

`Verdict` (bandit manchot) et `SaisieVerdict` portent tous deux une composante `enonce`, obligatoire,
par `ADR-0084` / `REV-3` : *la reponse d'une fin de partie voyage avec la question qu'elle commente*.
Le fragment de fin de partie rend cet enonce dans un element de classe `question-rappelee`.
`DesignationVerdict` etait `(boolean juste, String bonneZone, Optional<String> explication)` : **pas
d'enonce**.

A l'instant de la fin de partie, `resoudre` a mis la manche courante a `null` : **ce verdict est la
seule trace possible de l'item joue.** Il n'y a rien a recomposer ailleurs.

**Le motif de `nadia-prof` est plus fort ici que sur les deux autres mecaniques, et c'est elle qui
peut le faire** : *"Sur une designation, la reponse est un endroit sur un dessin - et a la fin de la
partie, le dessin n'est plus a l'ecran."* L'enfant lirait donc une reponse sans sa question **et**
sans son terrain.

### 3. Trou B - la bonne zone n'est presentable ni par son identifiant, ni par son libelle

`DesignationVerdict.bonneZone()` rendait un `Zone.id()`, c'est-a-dire **la valeur que le formulaire
renvoie** - `Zone` distingue explicitement `id` de `libelle`, et `ADR-0080` decision 4 l'exige. Le
fragment de fin de partie rend la reponse attendue dans un element lu par un enfant : servir cet
ecran en l'etat aurait affiche un identifiant technique.

Mon avis de faisabilite avait ecrit que deux issues etaient faisables - le verdict transporte quelque
chose de presentable, ou la designation a son propre fragment de fin - et route a `nadia-prof` la
seule question qui n'etait pas de moi : **ce que l'enfant voit.** `ADR-0080` section 8 lui laissait
deja *"le libelle d'une zone, sa formulation"*.

**Sa reponse, et c'est le point qu'elle etait seule a pouvoir faire : ni l'un ni l'autre.** Le
libelle n'est pas meilleur que l'identifiant, parce qu'elle a **deja arbitre** que les libelles de
zone sont **positionnels** (`ZON-10`) - un garde-fou de contenu interdit qu'un enonce nomme le
libelle de sa bonne zone, et il a raison de l'interdire. Afficher *"la zone en bas a droite"* a la
fin d'une partie n'apprend rien de la France.

> **Sa decision : l'ecran de fin de partie d'une designation porte l'enonce et ne montre pas la zone
> attendue - il ne montre rien a sa place.** La revelation reste **sur l'ecran de manche**, ou le
> dessin est encore la et ou l'enfant voit *ou* c'etait, par `DesignationSession.bonneZoneRevelee()`.
> `SCO-5` intacte : une manche resolue par la revelation n'est jamais une reussite.

### 4. Le troisieme fait, qui n'est pas d'elle et que j'ai trouve en instruisant les deux premiers

Cinq garde-fous de rendu de `bootstrap` construisent leur population en relisant le catalogue reel,
et **ecartaient en silence tout paquet d'un type qu'ils ne connaissaient pas**. Le chargeur de
production enregistre **quatre** types de paquet ; quatre de ces cinq garde-fous en connaissaient un
ou deux et faisaient `continue` sur les autres.

**Le plus grave est celui de l'illustration** : `ZoneADesigner` refuse une illustration vide dans son
constructeur compact, donc la designation est **la seule mecanique ou l'illustration est
obligatoire** - et c'est exactement celle que le garde-fou de l'illustration aurait ecartee sans un
mot le jour ou un paquet de designation entre au catalogue. Un lot de contenu etant desormais
finance, **ce faux vert avait une date d'echeance**.

**Correction d'un de mes propres chiffres, dans le meme geste.** Mon avis de faisabilite, section
6.4, ecrit que les **cinq** font `continue` sur un paquet ni `question` ni `saisie`. C'est vrai de
quatre. Le cinquieme - le garde-fou du decalage inter-colonnes - **ne branche pas du tout sur le
type** : il lit les `items` par une forme generique, et un paquet de designation, qui declare des
items sans distracteurs, y vaut **1 colonne** sans qu'aucun grief ne le dise. Effet nul sur son
verdict, le plafond etant un maximum ; mais sa propre Javadoc promettait qu'*"une troisieme forme, le
jour ou elle arrivera, fera rougir ce garde-fou au lieu de passer en silence"*, et **cette promesse
etait fausse** pour un type neuf dont la forme se lit par accident. Une promesse fausse dans un
garde-fou est de la meme famille qu'un chiffre sans methode. Cinq cas, deux mecanismes, et ils sont
distingues ici plutot que confondus.

### 5. Le quatrieme fait, trouve par `nadia-prof` le meme jour et non referme

Le motif d'identifiant de `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` **ecartait les
codes a trois segments**. Ses lookarounds refusent un tiret de part et d'autre du prefixe et du
numero, ce qui ecarte un code de ligne de referentiel sans avoir a le nommer - et, sans que personne
le voie, ecartait aussi **tout identifiant de condition portant un segment de niveau**.

Elle voulait glisser le niveau au milieu de l'identifiant de sa barre de longueur d'enonce. Un tel
identifiant aurait ete **silencieusement ignore** par le garde-fou : ni alloue, ni signale. C'est le
faux vert exact que `PAR-5` et `ADR-0085` existent pour tuer. Elle a pris `LON-1` et `LON-2`, a deux
segments, pour passer a cote du trou, et a route le trou.

---

## Decision

### A. Le verdict d'une mecanique porte l'enonce de la manche qu'il juge

`DesignationVerdict` porte une composante `String enonce`, **obligatoire**, comme les deux verdicts
voisins. `DesignationSession.resoudre` y passe l'enonce de la manche resolue.

La regle est ecrite sur **tous** les verdicts et pas sur celui-ci : ce que quatre sprints ont montre,
c'est que rien ne rappelle une propriete decidee pour deux mecaniques le jour ou une troisieme
arrive - `ADR-0004` clause 3 voulant que chaque mecanique porte sa propre boucle de partie, donc son
propre verdict, et rien dans le fichier neuf ne renvoyant a l'ancien.

### B. Le verdict d'une designation ne porte aucune zone

La composante `bonneZone` est **retiree** de `DesignationVerdict`.

C'est la forme la moins chere de la decision de `nadia-prof`, et elle est plus forte qu'une consigne
de gabarit : **un adaptateur ne peut pas rendre une valeur qui n'existe pas a l'instant ou il rend
cet ecran.** Le reste suit par construction - a la fin d'une partie, la manche courante est `null` et
`revelee` vaut `false`, donc `bonneZoneRevelee()` rend une absence. La zone attendue n'est atteignable
que sur l'ecran de manche, ou le dessin est encore la.

### C. Un garde-fou de rendu qui ne sait pas juger un type de paquet le dit, en rouge

Les cinq garde-fous de rendu concernes ne font plus `continue` sur un type qu'ils n'ont pas instruit.
Quatre **levent** ; le cinquieme **ajoute un grief** et poursuit son balayage, sur la forme de son
propre fichier - un paquet peut porter deux defauts a la fois, et n'en montrer qu'un masquerait
l'autre.

**Aucun des cinq ne pose de branche pour le type `designation`.** C'est deliberement un rouge et non
une exemption : une exemption ecrite d'avance pourrirait le jour ou le contrat de l'item changerait,
et c'est precisement la famille du defaut paye ici. Le jour ou le premier paquet de designation entre
au catalogue, les cinq sont rouges, et chaque branche s'ecrit avec le record sous les yeux.

### D. Un identifiant de condition a trois segments est alloue comme n'importe quel autre

Le garde-fou du registre lit desormais **deux** formes : deux segments, et trois segments quand la
condition porte un niveau. Le registre peut allouer les deux.

**Le partage avec `ADR-0081` se fait par proprietaire, jamais par forme.** Un identifiant de condition
a trois segments s'ecrit **exactement** comme un code de ligne de referentiel, et aucun trait de forme
ne les distingue de facon fiable - le nombre de chiffres du dernier segment n'en est pas un, rien
n'interdisant a une condition d'atteindre deux chiffres. Donc : un code que les referentiels
declarent appartient a `ADR-0081` ; **tout autre** code a trois segments doit etre alloue dans le
registre. La liste des codes de ligne est **lue chez son proprietaire**, jamais recopiee
(`ADR-0061`), et une clause neuve refuse qu'un meme nom ait les deux proprietaires.

**Ce que cette decision ne fait pas** : elle n'interdit pas les trois segments, et elle ne les
recommande pas. La forme d'un identifiant de condition appartient a `nadia-prof` ; ce qui est decide
ici est qu'aucune forme n'echappe au registre.

---

## Regles executables

**Compte des `ArchRule` du depot : 33 avant ce lot, 34 apres.** Methode, dans le meme paragraphe que
le chiffre : motif `static final ArchRule` sur les arbres de sources des cinq modules declares par le
POM parent, `/target/` exclu et le faux module de contre-exemple de
`LesGardeFousSontMisALEpreuveTest` exclu - il en declare 2, d'ou un comptage naif a 36. Concordant par
un second chemin : `LesReglesMordentTest` execute **34** morsures apres ce lot (33 avant), une par
`ArchRule`, chiffre lu dans son **rapport texte** de surefire ; son rapport **XML** en compte 35,
l'ecart etant la regle `@ArchTest` que ce fichier declare lui-meme et que les deux moteurs JUnit ne
rapportent pas au meme endroit (`CLAUDE.md`, note sur les deux moteurs). Mesure partielle
`mvn -pl bootstrap -am`, module `recreo-bootstrap`, JDK 21.0.10.

### Clause A - une `ArchRule`, la douzieme de `DecouplageMecaniqueContenuTest`

**Fichier** : `bootstrap/src/test/java/com/recreo/archi/DecouplageMecaniqueContenuTest.java`, clause
12, `unVerdictDeMecaniquePorteSonEnonce`.
**Contre-exemple** : `bootstrap/src/test/java/com/recreo/domain/mechanics/contreexemple/VerdictSansSaQuestionContreExemple.java`,
morsure declaree dans `LesReglesMordentTest` (`ADR-0003`).

Toute classe de `com.recreo.domain.mechanics..` qui declare une composante `boolean juste` declare
aussi une composante `String enonce`.

**Perimetre derive, jamais enumere, et c'est le point delicat.** Aucun nom de type n'est ecrit dans
la regle et aucun suffixe `Verdict` n'est cherche : un critere sur le nom se contourne en renommant.
Ce qui designe un verdict est **ce qu'il juge** - la composante `boolean juste`, que les trois
mecaniques servies ecrivent identiquement et dont la Javadoc de chacune dit le sens (la manche
compte-t-elle comme une reussite). **Population verifiee par execution : 3 classes**, les trois
verdicts et eux seuls. `boolean juste` apparait par ailleurs sur les trois sessions, comme
**parametre** de `resoudre` ; un parametre n'est pas un champ et ArchUnit ne les confond pas.
`allowEmptyShould(false)` garde ce perimetre : le jour ou la marque disparaitrait des trois verdicts,
la clause le dirait au lieu de passer verte sur rien (`ADR-0051`).

**Elle etait rouge, et la mesure est donnee plutot qu'affirmee.** L'arbre remis dans son etat d'avant
ce diff pour les deux seuls fichiers de `domain` concernes, la clause seule relancee
(`mvn -pl bootstrap -am -Dtest=DecouplageMecaniqueContenuTest`, module `recreo-bootstrap`,
JDK 21.0.10) rend **1 violation sur 1** :

```
com.recreo.domain.mechanics.designation.DesignationVerdict juge une manche et ne porte pas son
enonce. ADR-0084 REV-3 : a l'instant de la fin de partie, la session n'a plus de manche courante et
ce verdict est la seule trace de l'item joue - il n'y a rien a recomposer ailleurs
```

**Ce qu'elle interdit reellement.** Le seul geste qui la rend verte est d'ajouter la composante au
troisieme verdict ; elle interdit ensuite exactement ce que ce depot vient de faire une fois. Elle
**decrit l'etat du depot** au lieu de borner un risque imagine, ce qui est la preuve la plus forte
qu'elle n'est pas decorative.

**Ce qu'elle ne garde pas, et qui le garde.** Elle voit une composante, jamais la valeur qui y voyage
ni l'element de document qui la rend. Une propriete gardee a moitie qui se croit gardee en entier est
la faute que ce depot a sanctionnee plusieurs fois : elle est decoupee, et chaque moitie porte le nom
de son garde - la valeur par la clause B ci-dessous, le document servi par
`AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest`.

### Clause B - un garde-fou de valeur sur une partie reellement jouee, et non une `ArchRule`

**Fichier** :
`domain/src/test/java/com/recreo/domain/mechanics/designation/LeVerdictDUneDesignationPorteSaQuestionEtJamaisSaZoneTest.java`,
**6 tests verts**, mesure partielle
`mvn -pl domain -am -Dtest=LeVerdictDUneDesignationPorteSaQuestionEtJamaisSaZoneTest`, module
`recreo-domain`, JDK 21.0.10.

**Pourquoi ce n'est pas une `ArchUnit`, et le motif est mesure et non repris de confiance.** ArchUnit
modelise des types et leurs relations. La propriete jugee ici est une **valeur** portee par un objet
apres qu'une partie a ete jouee : *l'enonce du verdict est celui de la derniere manche*, et *aucune
composante du verdict ne porte l'identifiant ni le libelle de la zone attendue*. Une `ArchRule`
aurait pu interdire un nom de composante, c'est-a-dire rien : renommer `bonneZone` en `attendue` la
contourne, et la propriete de la clause A ne tiendrait plus que par un seul fil.

**Critere derive, jamais enumere.** Les composantes du verdict ne sont pas nommees : elles sont lues
sur le record (`getRecordComponents`) et chacune est confrontee aux deux valeurs interdites,
`Optional` deplie - une zone rangee dans un `Optional` serait aussi lisible par un gabarit qu'une zone
nue. Une composante ajoutee demain entre dans le critere sans qu'une ligne du test change.

**La morsure porte sur du materiau reel plutot que sur une fixture de ma main**, et c'est la lecon de
s05 appliquee : `ZoneADesigner` est le seul type du domaine qui porte la bonne zone **a bon droit**,
et c'est exactement l'objet que le verdict ne doit pas recopier. Le critere doit rougir dessus, et un
test l'exige. S'il n'y rougissait pas, il ne rougirait pas davantage sur un verdict qui remettrait la
zone en circulation, et les trois assertions positives seraient vertes sur rien.

**Ce qui reste a T4 et T6, et pourquoi ce n'est pas un report** : que l'ecran de fin de partie servi
d'une designation porte l'enonce et **omette le bloc de reponse** est une propriete du document rendu.
Sa population est **vide aujourd'hui** - aucun ecran de designation n'existe - et `ADR-0051` refuse
une regle a population vide. Le mecanisme qui l'empeche d'etre oubliee existe deja et il est date :
`AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest` construit sa population par derivation
totale et **leve** sur une forme d'item qu'aucun ecran de fin de partie ne sait jouer - `ZoneADesigner`
aujourd'hui. Le jour ou un paquet de designation entre au catalogue, ce garde-fou est **rouge**, et la
decision de la section 3 s'y ecrit. Ce n'est pas une promesse d'`ADR`, c'est un test qui echoue.

### Clause C - cinq garde-fous de rendu, aucun n'est une `ArchRule`

Les objets juges sont des paquets de contenu et des documents HTML servis ; aucun n'est un type. Les
cinq vivent dans `bootstrap/src/test/java/com/recreo/bootstrap/rendu/`.

| Garde-fou | Ce qui change | Etat mesure |
|---|---|---|
| `LIllustrationDUnItemServiAtteintLEcranDeMancheTest` | leve sur un type non instruit ; le message dit que `ZoneADesigner` rend l'illustration obligatoire, donc que la reponse pour ce type-la est **oui** | **10 tests verts** (9 avant) |
| `LeBlocAdulteDUnItemReelAtteintLEcranDeMancheTest` | idem ; le message dit que `ZoneADesigner` declare bien une composante `texteAVoixHaute`, donc que la designation entre dans ce perimetre | **6 tests verts** (5 avant) |
| `LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest` | idem ; le message dit que `ZoneADesigner` ne declare aujourd'hui aucun texte support, **mais que c'est le contrat de l'item qui doit le dire, pas ce fichier** | **8 tests verts** (7 avant) |
| `LaPartDesigneeDUnItemServiEstMarqueeALEcranTest` | idem pour la part designee | **7 tests verts** (6 avant) |
| `ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest` | ajoute un grief sur un type non instruit et poursuit ; la Javadoc qui promettait un rouge que ce fichier ne rendait pas est **retiree** plutot que laissee a moitie vraie | **4 tests verts**, dont un dont l'assertion est renforcee |

Mesure partielle des cinq : `mvn -pl bootstrap -am -Dtest=<les cinq>`, module `recreo-bootstrap`,
JDK 21.0.10.

**Ce que ces gates interdisent reellement, et pourquoi ils ne sont pas decoratifs.** Ils rendent
impossible qu'un type de paquet neuf entre au catalogue sans qu'une decision soit prise, garde-fou par
garde-fou, sur ce que ce type peut declarer. C'est la difference entre un garde-fou qui garde une
population et un garde-fou qui garde la population qu'il connaissait le jour de son ecriture.

**La morsure porte sur du materiau reel.** Chacun des quatre qui levent porte un test qui prend le
corps d'un paquet **servi du depot**, decouvert et non nomme, et n'en mute que la valeur de la cle
`type`. C'est l'etat exact qu'aura la population le jour du premier paquet de designation. Le
cinquieme reutilise son jeu fautif deja commite : il portait deja un type qu'aucun chargeur
n'enregistre - le nom avait ete choisi pour la couleur du fautif - et **personne n'asserait que ce
type-la produise un grief**. C'est desormais exige.

**Ce qui n'est pas garde ici, et je le dis plutot que de le laisser supposer** : trois autres
garde-fous de rendu **levent deja** sur une forme d'item inconnue, et un quatrieme rougit sur une
entree de catalogue sans branche de descente. La clause C ne les remplace pas ; elle met les cinq
autres au meme niveau qu'eux.

### Clause D - le registre d'identifiants, et ce n'est pas une `ArchRule`

**Fichier** : `bootstrap/src/test/java/com/recreo/archi/ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest.java`,
**7 tests verts** (6 avant), mesure partielle
`mvn -pl bootstrap -am -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, module
`recreo-bootstrap`, JDK 21.0.10. ArchUnit lit du bytecode ; un identifiant ecrit dans un texte de
gouvernance n'y est pas une propriete lisible.

Ce qui change :

1. un second motif lit la forme a trois segments ; **un seul endroit du fichier lit les motifs**,
   sans quoi la forme neuve serait entree dans le perimetre reel et pas dans le trou de
   contre-exemple, ou l'inverse ;
2. les deux tables du registre acceptent un segment intermediaire, **sans quoi la forme aurait ete
   interdite au lieu d'etre gouvernee** - elle n'aurait eu aucune sortie verte ;
3. la legitimite d'un code a trois segments vient du registre **ou** des referentiels, ces derniers
   lus chez leur proprietaire par une methode ajoutee au garde-fou d'`ADR-0081` ;
4. une clause neuve refuse qu'un nom soit a la fois alloue par le registre et declare comme code de
   ligne - la collision du 2026-09-03 a l'echelle de deux espaces de noms au lieu de deux instances ;
5. une borne de non-vacuite exige que la forme a trois segments soit **reellement examinee** : sans
   elle, elargir le motif et ne rien lire du tout rendrait le meme vert qu'avant l'elargissement, qui
   est le faux vert exact que ce diff paie.

**Le compte courant du registre est inchange : 547 identifiants alloues, 94 prefixes distincts**,
verifie par la clause 5 du garde-fou lui-meme, qui est verte. Elargir les motifs de table ne pouvait
pas le changer, aucune allocation a trois segments n'existant.

**Mesure de la population, avec sa methode.** Balayage de tous les fichiers texte du depot, arbres de
sortie de build, arbres de contre-exemple et `.git` exclus, binaires reconnus a l'octet nul : **209
codes a trois segments distincts, 209 declares par un referentiel, zero hors referentiel.** La
population **en faute** est donc vide aujourd'hui ; ce n'est pas une regle a population vide mais un
cliquet a plafond zero, le balayage examinant 209 objets et n'en trouvant aucun en defaut
(`ADR-0051`, `ADR-0067`).

**La morsure, et elle est datee.** Le depot fautif de ce garde-fou porte desormais un arbitrage qui
ecrit un identifiant a trois segments que son registre n'alloue pas et qu'aucun referentiel ne
declare - ce depot fictif n'a pas de referentiel du tout. **Avant ce diff, ce fichier aurait passe
vert** : le motif ne lisait aucune forme a trois segments, donc le fautif n'etait meme pas examine. Le
jour ou quelqu'un remet une borne de forme qui ecarte les trois segments, ce test tombe.

**La preuve la plus forte que cette clause n'est pas decorative est que je ne l'ai pas cherchee.** La
premiere execution du motif elargi a rendu ce garde-fou **rouge sur mon propre fichier**, deux fois :
la Javadoc que je venais d'ecrire citait, pour l'expliquer, l'identifiant a trois segments que
`nadia-prof` avait voulu prendre, et un fragment de code de ligne. Ni l'un ni l'autre n'est alloue.
L'amendement du 2026-09-08 a `ADR-0085` ayant deja tranche que **citer n'est pas moins qu'employer**,
la sortie n'etait pas une exemption de contexte : les deux citations ont ete reecrites en decrivant la
forme au lieu de l'ecrire. **Un garde-fou aveugle a une forme est devenu, dans la meme heure, un
garde-fou qui mord la main qui l'elargit.**

---

## Consequences

**Bonnes :**

- La propriete de `REV-3` cesse d'etre vraie sur deux mecaniques et vraie par accident sur la
  troisieme : elle est vraie sur **toute** classe qui juge une manche, et une quatrieme mecanique ne
  peut plus la manquer en silence.
- La decision de `nadia-prof` sur la zone attendue est tenue **par l'absence d'une composante**, donc
  par le compilateur, et non par la vigilance d'un gabarit.
- Cinq faux verts de rendu ont une date d'echeance qui est celle du premier paquet de designation, et
  non celle d'une relecture.
- Un identifiant de condition a trois segments cesse d'etre hors garde. La forme reste disponible pour
  `nadia-prof`, ce qui evite qu'elle contourne une seconde fois.
- Le lot de contenu que l'utilisatrice finance n'a plus les deux trous de contrat devant lui.

**Mauvaises, et elles sont reelles :**

- **T4 et T6 heritent de cinq rouges de plus.** Ils sont voulus et chacun est une question a
  trancher, mais ils s'ajoutent aux quatre que mon avis de faisabilite nommait deja : le premier lot
  qui sert un paquet de designation rencontrera **neuf** garde-fous rouges avant d'etre vert. C'est le
  prix de la derivation totale, et il est paye les yeux ouverts.
- **La clause A repose sur une convention de nommage de composante.** Un quatrieme verdict qui
  appellerait `reussie` ce que les trois autres appellent `juste` sortirait du perimetre sans
  qu'aucune regle ne rougisse. Je le prefere a un critere sur le nom du type, qui se contourne aussi
  et qui ne dit rien de ce que le type fait ; mais le trou existe. **Ce qui me ferait rouvrir** : un
  contrat `RoundVerdict` dans `domain.mechanic` que les trois verdicts implanteraient - ce que
  `ADR-0004` clause 3 rend aujourd'hui plus couteux que le trou qu'il fermerait, et qui demanderait un
  `ADR` d'amendement, pas un refactoring.
- **La clause D fait lire un garde-fou par un autre.** Le garde-fou du registre appelle desormais une
  methode du garde-fou d'`ADR-0081`. C'est une piece mobile de plus, et elle est preferee a la copie
  de la liste des codes de ligne (`ADR-0061` : un second exemplaire d'une association rancit).
- **Le balayage du registre coute plus cher** : deux motifs sur tous les fichiers texte du depot, plus
  la lecture des referentiels. Mesure : 15,2 s contre 12,7 s avant, sur le meme arbre et la meme
  machine virtuelle.
- **Retirer `bonneZone` du verdict ferme une porte que quelqu'un pourrait vouloir rouvrir.** Si
  `nadia-prof` revient un jour sur sa reponse - par exemple parce qu'un lot montre qu'un enfant sort
  d'une partie sans savoir ou etait la reponse -, la composante se remet, et **le garde-fou de valeur
  de la clause B devient rouge**, ce qui est le bon comportement : la decision se reouvre par un
  amendement ecrit, jamais par une ligne ajoutee sans bruit.

---

## Alternatives considerees

1. **Amender `ADR-0080` plutot qu'ecrire un `ADR`.** Ecartee. `ADR-0080` decide la conception de la
   mecanique de designation ; il a deja recu un amendement le 2026-09-08 qui **nomme** les deux trous
   et route le second a `nadia-prof`. La reponse de `nadia-prof` et la regle qui la rend verifiable
   ne sont pas un correctif de cet `ADR` : la clause A porte sur **toutes** les mecaniques, la clause
   D sur un registre d'identifiants qui n'a rien a voir avec la designation. Un amendement les aurait
   loges sous un titre qui ne les annonce pas.
2. **Amender `ADR-0084`, dont une phrase devient fausse.** Ecartee pour la meme raison, et la phrase
   est traitee autrement : `ADR-0084` avait ecrit *"`DesignationVerdict` n'est pas modifie, et ce
   n'est pas un oubli"* avec son motif, qui etait juste au moment ou il l'ecrivait. Ce n'est pas une
   erreur a corriger, c'est une decision dont la condition a change. Une note de renvoi est ajoutee
   dans les deux `ADR` ; leur texte n'est pas reecrit.
3. **Faire porter au verdict un libelle presentable de la zone.** Ecartee **par `nadia-prof`**, et
   c'est son ressort : un libelle positionnel n'apprend rien, et le garde-fou qui interdit a un enonce
   de nommer le libelle de sa bonne zone a raison de l'interdire. C'etait l'issue que mon avis
   nommait comme la moins chere ; sa reponse rend une issue encore moins chere suffisante.
4. **Donner a la designation son propre fragment de fin de partie.** Ecartee : un gabarit de plus pour
   omettre un bloc, alors que la meme omission s'obtient par une condition dans le fragment existant.
   **Ce qui me ferait changer d'avis** : que l'ecran de fin d'une designation doive montrer autre
   chose que les deux autres, ce qui est une decision de `nadia-prof` et non un besoin technique.
5. **Ecrire dans les cinq garde-fous une branche `designation` des maintenant.** Ecartee, et c'est le
   choix le plus discutable de cet `ADR`. Elle aurait epargne cinq rouges a T6. Elle aurait aussi
   ecrit cinq exemptions sur un contrat d'item qu'aucun paquet reel n'a encore exerce, et une exemption
   qui ne nomme plus rien de reel n'est pas un cliquet - c'est la lecon que ce depot a payee assez de
   fois. Un rouge pose la question avec le record sous les yeux.
6. **Refuser les identifiants de condition a trois segments.** Ecartee. Elle etait moins chere - une
   borne de forme suffit - mais elle aurait tranche a la place de `nadia-prof` la forme de ses propres
   identifiants, pour m'epargner une lecture. La forme des conditions est d'elle ; ce qui est de moi
   est qu'aucune forme n'echappe au registre.
7. **Recopier la liste des codes de ligne dans le garde-fou du registre.** Ecartee par `ADR-0061` : un
   second exemplaire d'une association rancit, et celui-ci rancirait a chaque ligne de referentiel
   ajoutee - c'est-a-dire a chaque sprint.

---

## Ce que je route, et a qui

| Fait | A qui | Pourquoi ce n'est pas a moi |
|---|---|---|
| **Faut-il un intitule devant la question rappelee** sur l'ecran de fin d'une designation, comme la question reste ouverte pour les deux autres mecaniques (`ADR-0084`) | `nadia-prof` | Un texte lu par un enfant. Le cout est d'une ligne de gabarit et d'une entree d'inventaire |
| **L'enfant sort-il d'une partie de designation sans savoir ou etait la reponse**, et est-ce acceptable | `nadia-prof` | Cela se constate en jouant un lot reel, pas en lisant du code. C'est le declencheur qui reouvrirait la clause B |
| **La forme des identifiants de condition a trois segments** - les prendre ou pas, et selon quelle convention | `nadia-prof` | La clause D rend la forme allouable ; le choix de l'employer est le sien |
| **Le rang et l'affectation des lots** `L-a` a `L-e`, et du lot de contenu finance | `camille-po`, `sofia-sm` | Je ne priorise pas et je n'affecte personne |
| **Le Done pedagogique** du lot de contenu de designation | `nadia-prof` | Je ne le prononce jamais |

---

## Mesure de la porte technique, sur mon propre diff avant de commiter

**Les quatre jobs de `.github/workflows/ci.yml` lances chacun dans la forme que la CI lui donne, et
aucun deduit d'un autre** - c'est l'amendement de `CLAUDE.md` du 2026-09-08 qui l'exige, apres un
sprint entier pendant lequel un job est reste rouge derriere un `mvn verify` vert.

| Job | Resultat |
|---|---|
| `prefixes` | **vert** - ses deux tests de garde-fou verts, puis *"Tous les commits portent un prefixe d'agent connu."* sur le sujet de ce commit |
| `chemins` | **vert** - son test de garde-fou vert, puis *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* sur l'index complet |
| `outils` | **vert** - 9 juges purs executes, aucun motif de balayage vide |
| `build` | **vert** - `bash outils/mvn-jdk21.sh -B clean verify`, BUILD SUCCESS, reacteur complet depuis la racine, aucun `-pl` |

**Le job `build` a ete rouge sur ce diff, et le grief etait juste.** Le premier jet du garde-fou de
valeur de la clause B bornait sa boucle de tirage a une constante ecrite a la main, et
`UnGardeFouNeBorneJamaisSesTiragesSousLePlusGrandPaquetTest` (`ADR-0064`) l'a nomme : *"borne de
tirage MANCHES = 2, alors que le plus grand paquet enumere du catalogue en declare 99"*. La source
etant ici fabriquee a deux items, rien n'etait perdu - mais la **forme** etait celle du defaut mesure
le 2026-08-28, et une exemption aurait ete la mauvaise sortie. La boucle **vide** desormais sa source
jusqu'a l'absence, ce qui est plus fort qu'une borne derivee, et le compte attendu est assertionne
ensuite. Le rouge est ecrit ici parce qu'un garde-fou qui arrete son propre auteur est la seule preuve
qui vaille qu'il n'est pas decoratif.

**Compte de tests, avec sa methode - la meme qu'a la cloture de s31, les rapports XML apres
`clean`** : **192 fichiers `TEST-*.xml`, 1421 tests, 0 echec, 0 erreur, 0 ignore.** JDK **21.0.10**,
version rapportee par la machine virtuelle des tests (`ADR-0071`).

**Chaque unite du delta est attribuee** : 1402 a la cloture de s31 ; **+6** par
`ChaqueMecaniqueDuDomaineEstAtteignableDepuisLeProduitTest`, ecrit dans mon commit precedent de s32
et absent de `round-two` ; **+13** par ce commit - 6 pour le garde-fou de valeur de la clause B, 1
pour la clause ArchUnit A, 1 pour sa morsure, 1 pour la morsure a trois segments du registre, 4 pour
les morsures de type des quatre garde-fous de rendu qui levent. **1402 + 6 + 13 = 1421.** Aucun delta
non attribue.
