# ADR-0082 - Une reproduction d'oeuvre est une troisieme population d'images, sa preuve est double, et un pivot remplace tout calcul de duree

- **Statut** : Accepte.
- **Sa regle est ecrite, verte et mise a l'epreuve dans le meme commit** -
  `bootstrap/src/test/java/com/recreo/archi/ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest.java`,
  **18 tests**, dont **13 morsures** : onze par mutation en memoire du materiau de reference, deux
  sur le jeu fautif commite.
- **Sprint** : hors-sprint, instruction. **Date** : 2026-09-05. **Auteur** : `theo-archi`.
- **Arbre mesure** : `round-two` a `c9a9e44`, arbre propre avant les fichiers de cet `ADR`. **JDK
  21.0.10**, celui que la toolchain resout et que `maven-surefire-plugin` a lance pour les tests
  cites. Enveloppe : `bash outils/mvn-jdk21.sh`.
- **Applique l'arbitrage de l'utilisatrice du 2026-09-05** sur `DEC-0075`, option B : un corpus de
  reproductions d'oeuvres du domaine public. **Cet `ADR` instruit la source et la forme ; il ne
  livre aucune oeuvre, aucun paquet et aucun octet binaire.**
- **Prolonge** : `ADR-0073` (un item declare son illustration par un identifiant, la preuve change
  de source), `ADR-0007` (un seul chargeur pilote par un schema), `ADR-0013` (perimetre derive,
  garde-fou mordant), `ADR-0014` (garantie hors ligne), `ADR-0036` (deux populations d'images),
  `ADR-0051` (un garde-fou qui n'examine aucun objet le declare), `ADR-0081` (une declaration
  verifiable vit dans une donnee, jamais dans un commentaire).
- **Ne decide aucun contenu, aucune pedagogie, aucun perimetre de sprint.** Il ne choisit aucune
  oeuvre (`nadia-prof`), ne dit pas si le corpus est assez divers (`ART-5`, `nadia-prof`), n'ouvre
  ni ne ferme aucune ligne de referentiel (`camille-po`), et ne fixe l'ordre d'aucun lot
  (`camille-po` et l'utilisatrice).

---

## Contexte

### Le fait, et il vient de `nadia-prof` puis de l'utilisatrice

Le 2026-09-03, `nadia-prof` juge trois referentiels artistiques et classe **neuf lignes
atteignables** en attente d'une seule et meme chose : une image d'oeuvre. Deux en arts plastiques
CP (lignes 9 et 10), deux en arts plastiques CM1 (lignes 7 et 8), cinq en histoire des arts CM1
(lignes 1, 2, 3, 4, 6). Elle route la question sans la trancher, trois fois, dans les memes
termes : *"la question des droits d'usage des reproductions n'est pas de ma competence"*.
`camille-po` l'ouvre en `DEC-0075` avec trois options et sans recommandation. L'utilisatrice tranche
l'option B le 2026-09-05.

**Ces neuf lignes sont `non couvert`, jamais `non couvrable en l'etat`**, et `nadia-prof` a defendu
cette distinction deux fois : l'obstacle est un contenu qui n'existe pas, pas une contrainte
fondatrice du produit. Tout ce qui suit doit preserver cette difference, et rien de ce qui suit ne
la deplace.

### Le piege, et il joue dans les deux sens

C'est le point de fond de ce dossier, et il ne se resume pas a *"l'oeuvre peut etre libre sans que
la photo le soit"* :

- **une oeuvre du domaine public ne rend pas libre la photographie qui la reproduit.** Celui qui a
  pris la photographie peut y revendiquer un droit propre, et une institution peut attacher ses
  propres conditions au fichier qu'elle publie. La Bibliotheque nationale de France en est
  l'exemple francais le plus connu : ses fonds numerises sont massivement des oeuvres du domaine
  public, et elle attache neanmoins des conditions a la reutilisation de ses reproductions ;
- **et symetriquement, une renonciation de l'institution ne prouve rien sur l'oeuvre.** Une
  institution renonce a ce qu'elle a - sa photographie -, pas a ce qu'elle n'a pas. Une image en
  licence ouverte d'une oeuvre encore protegee est parfaitement possible, et elle est parfaitement
  inutilisable.

**Ce sont deux objets, donc deux preuves.** Une preuve unique, quel que soit le cote qu'elle
couvre, laisse l'autre ouvert - et le laisse ouvert **en silence**, ce qui est la famille de defaut
que ce depot sanctionne partout ailleurs.

Il existe une regle europeenne qui ferme la premiere moitie pour les oeuvres **planes** :
l'article 14 de la directive (UE) 2019/790 dispose qu'une reproduction fidele d'une oeuvre d'art
visuel dont la protection a expire n'est pas elle-meme protegee, sauf a etre originale. Le droit
americain arrive au meme resultat depuis *Bridgeman Art Library v. Corel* (1999). **Cet `ADR` ne
s'y appuie pas comme mecanisme**, et le motif est ecrit en decision 1 : je ne cite pas le numero de
l'article de transposition francaise, faute de pouvoir le verifier depuis ce depot, et une decision
d'architecture qui reposerait sur une lecture juridique que son auteur ne peut pas verifier est
exactement le genre de fondation qu'on ne remarque qu'apres.

**Je ne suis pas juriste, et cet `ADR` ne transforme pas une lecture en garantie.** Ce qu'il
garantit est de nature differente et c'est celle qu'un depot peut tenir : que **le depot montre,
oeuvre par oeuvre, pourquoi il la croit libre, et que la verification puisse etre refaite par un
tiers**. Le risque residuel de publier des images sur un depot public appartient a l'utilisatrice ;
il est nomme en consequences, pas dissous.

### Ce que le depot sait deja faire, et ce qu'il ne sait pas

Le palier 1 illustre existe depuis s25 : `ADR-0073`, un item declare son illustration par un
identifiant, l'adaptateur web le resout, **24 dessins SVG** servis sur deux paquets (figures planes,
monnaie). Ce qui manque n'est ni une mecanique, ni une forme d'item, ni un mecanisme de rendu :
c'est **une matiere que le depot ne fabrique pas**. Un carre se dessine ; *Sous la grande vague* ne
se dessine pas.

Et le dispositif existant **refuse** une photographie, ce qui est mesure et non suppose :

| Fait | Ou il se lit |
|---|---|
| Le garde-fou d'`ADR-0073` lit **chaque fichier** de `static/illustrations/` par `Files.readString` | `LIllustrationDeContenuResoutEtAucuneNeDortTest.fichiersIndexesParNomDeBase` |
| Un JPEG y jetterait une `MalformedInputException` : ses premiers octets (`FF D8 FF`) ne sont pas de l'UTF-8 valide | consequence directe du precedent - le garde-fou **exploserait** au lieu d'echouer proprement |
| Le garde-fou de geometrie juge un `viewBox`, un `stroke-width`, un `non-scaling-stroke` | `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` - aucun de ces criteres n'a de sens sur une image matricielle |
| Le cliquet de poids des dessins vaut **exactement** 30 730 octets au total et 2 775 pour le plus gros | memes constantes, `POIDS_TOTAL_MAXIMUM` et `POIDS_D_UN_DESSIN_MAXIMUM` |
| Le gabarit ne peut pas lire un repertoire pour savoir a quelle population appartient un identifiant | `ChargementDuContenuTest.seulLAdaptateurDeContenuLitDesFichiers` interdit `java.nio.file` hors de `com.recreo.adapter.content..` |

Ces cinq faits, pris ensemble, donnent la forme de la decision 2 : ce n'est pas une preference de
rangement, c'est ce que le depot impose deja.

---

## Decision

### 1. La source est une **renonciation enregistree**, pas une institution reputee

Une reproduction n'entre dans le depot que si **l'institution qui l'a publiee a renonce a ses
droits sur cette image par CC0 1.0**, et si le depot enregistre de quoi **refaire** la verification :
le lieu de conservation, l'identifiant de l'objet dans la collection, et l'adresse de la fiche
publiee.

**Pourquoi une renonciation plutot que l'article 14 de la directive.** L'article 14 est
probablement suffisant pour une oeuvre plane, et il est ecrit ici pour qu'on sache qu'il existe.
Mais il a trois defauts comme **mecanisme** : il ne couvre pas la photographie d'un objet en trois
dimensions, qui comporte des choix de prise de vue et peut etre originale ; il ne laisse **aucune
trace verifiable dans le depot**, puisqu'il n'y a rien a enregistrer ; et il ferait reposer le
depot sur ma lecture juridique, oeuvre par oeuvre, sans que personne ne puisse la controler. Une
renonciation CC0 est un **fait**, publie par un tiers identifiable, qui s'enregistre en une ligne
et se reverifie en une minute. Elle couvre en outre les objets en trois dimensions, que l'article
14 ne couvre pas.

**Pourquoi pas une liste blanche d'institutions dans le garde-fou.** Un perimetre enumere echoue
par inclusion autant que par omission, et une institution peut publier en licence ouverte une
partie de ses images seulement. Le garde-fou n'exige donc **aucune institution particuliere** : il
exige, pour **chaque** oeuvre, la licence exacte et de quoi la reverifier. Les programmes d'acces
ouvert connus - Metropolitan Museum of Art, Rijksmuseum, National Gallery of Art de Washington,
Art Institute of Chicago, Cleveland Museum of Art, Smithsonian, Paris Musees - sont des **pistes
ou chercher**, verifiees objet par objet au moment de l'entree, jamais une autorite qui dispenserait
de la verification.

### 2. Trois populations d'images, trois repertoires, et l'identifiant porte sa population

`ADR-0073` clause 2 en decrivait deux ; il y en a trois.

| Repertoire | Population | Format | Ce qui la juge |
|---|---|---|---|
| `static/images/` | le decor, dont le jeu ne depend pas de l'item | libre | `ADR-0036` clause 1, chemin litteral |
| `static/illustrations/` | les dessins maison | SVG | `ADR-0073`, geometrie, cliquet, aucun texte |
| `static/oeuvres/` | les reproductions d'oeuvres | **JPEG**, extension `.jpg` | **cet `ADR`** |

**Aucune des trois n'est une liste de noms : chacune est un repertoire**, donc derivable, et cette
propriete d'`ADR-0073` est reconduite sans une virgule.

**Un identifiant de reproduction porte le prefixe `oeuvre-`**, et l'alphabet reste celui
d'`ADR-0073` clause 1 : `oeuvre-[a-z0-9-]+`, ni point, ni barre oblique, ni majuscule. **Ce prefixe
n'est pas une convention de confort, c'est l'ombre du repertoire du cote du rendu** : le gabarit et
le controleur ne peuvent pas lire un repertoire - `ChargementDuContenuTest` le leur interdit -, donc
la population doit se lire sur l'identifiant lui-meme. Le garde-fou tient les deux bouts ensemble :
aucun fichier des deux autres repertoires ne porte ce prefixe, et aucun nom de base n'appartient a
deux populations.

**Un seul format, une seule extension.** Le gabarit ecrit l'extension **en clair** - c'est ce
qu'`ADR-0073` clause 3 admet, et une extension calculee est une forme que son garde-fou refuse
explicitement. Deux formats dans la meme population imposeraient une extension calculee, donc
percer cette exemption. JPEG parce que c'est le format que les institutions publient : le fichier
servi est alors le fichier source **redimensionne**, une seule etape, sans traitement qui pourrait
faire naitre une oeuvre nouvelle et derivee.

### 3. Le pivot de 1900 remplace tout calcul de duree

Une oeuvre n'entre que si **son auteur est mort avant 1900**, ou si elle est **anonyme et anterieure
a 1900**.

**Ce n'est pas la duree legale, et c'est le point.** La duree francaise est de 70 ans apres la mort
de l'auteur, mais elle se prolonge par les prorogations de guerre, par la mention *mort pour la
France*, et certains pays appliquent des durees plus longues. Un depot qui calculerait une duree
devrait tenir cette arithmetique juste **et la maintenir**, et son mode de defaillance serait un
**faux vert** : une oeuvre encore protegee que le calcul declare libre, servie a un enfant sans que
rien ne le dise.

Le pivot achete quatre choses d'un coup :

1. **aucune arithmetique juridique dans le depot** - une comparaison d'entiers, et rien d'autre ;
2. **aucune dependance a l'horloge** - un critere calcule sur l'annee courante rendrait un verdict
   qui change sans qu'aucun fichier ait bouge, ce qui est la definition d'un garde-fou dont on ne
   peut pas reproduire le verdict ;
3. **une marge qui absorbe les prorogations et les durees etrangeres** sans les nommer ;
4. **un nombre unique** : une oeuvre anonyme anterieure a 1900 a ete publiee avant 1900, donc sa
   protection, qui court depuis la publication, est echue depuis longtemps. Un seul pivot suffit aux
   deux cas.

**Ce que le pivot coute, et je l'ecris sans l'adoucir : le vingtieme siecle entier.** Aucun
Matisse, aucun Picasso, aucun Kandinsky, aucune oeuvre contemporaine. Pour l'histoire des arts de
CM1, cela ampute une partie du programme, et particulierement les lignes 4 (*associer une oeuvre a
une epoque*) et 6 (*reperes du patrimoine*). **C'est une consequence pedagogique, elle est routee a
`nadia-prof`, et elle n'est pas negociable en silence** : baisser le pivot rouvre exactement les
calculs que la decision 3 existe pour supprimer, et cela demande un `ADR`.

### 4. La preuve vit dans un registre, jamais dans un commentaire

`squad/corpus/oeuvres-domaine-public.yaml`. Une entree par oeuvre, neuf champs, tous obligatoires :

```yaml
- identifiant: oeuvre-<nom-court>
  titre: "<titre>"
  auteur: "<auteur>"            # ou exactement "anonyme"
  anneeDeLOeuvre: 1831
  anneeDeMortDeLAuteur: 1849    # obligatoire si l'auteur est nomme, interdit s'il est anonyme
  lieuDeConservation: "<institution, ville>"
  identifiantDObjet: "<identifiant dans la collection>"
  urlDeLaSource: "https://<fiche publiee par l'institution>"
  licenceDeLaReproduction: CC0-1.0
```

**Le titre, l'auteur, la date et le lieu de conservation sont exactement `ART-3` de `nadia-prof`**
(2026-09-03). Elle ecrit que l'oeuvre est identifiee *"dans le commentaire du paquet"*. **Je tiens
sa propriete et je change le lieu**, pour un motif qu'`ADR-0081` a deja etabli sur `HIS-4` : un
commentaire n'est verifiable par personne - le garde-fou d'`ADR-0070` retire meme les commentaires
avant de lire un paquet, precisement parce qu'une cle nommee dans un commentaire est le cas de la
cle ecrite avant son code. Une identification en commentaire aurait la forme d'une regle et la
force d'un souhait. **Le changement de lieu lui est route** ; s'il ne lui convient pas, la clause
change et le garde-fou avec.

**Le registre vit hors des modules**, dans `squad/`, pour trois raisons : il ne pese rien dans le
fat jar, rien ne le lit a l'execution, et il est la ou `nadia-prof` et le `relecteur-pedagogique`
travaillent. `ADR-0081` lit deja `squad/programme` depuis un garde-fou de `bootstrap` ; c'est le
meme geste.

### 5. Un corpus d'oeuvres n'est **pas** un dispositif de plus - c'est le palier 1 illustre, avec une seconde source d'images

C'est la reponse a la question *"meme dispositif ou un autre ?"*, et le critere n'est pas invente
ici : `ADR-0058`, repris par `ADR-0073` clause 6, dit qu'une forme d'item de plus se justifie quand
**la facon dont l'enfant repond** change. Elle ne change pas : l'enfant regarde une image et choisit
parmi trois propositions en texte, exactement comme devant un carre.

**Ce qui se reutilise, integralement et sans une ligne de plus** :

- la **mecanique** `bandit-manchot`, inchangee ;
- la **forme d'item** `question`, inchangee : aucune troisieme forme, aucun `permits` de plus ;
- les **deux cles de contenu** `illustration` et `illustrationTexteAlternatif`, avec leur regle
  reciproque d'`ADR-0073` clause 5 - une illustration declaree porte son texte alternatif ;
- la clause 6 d'`ADR-0073` : `enonce` reste la premiere cle de l'item, sans quoi l'item sort du
  comptage textuel partage des garde-fous de contenu ;
- le **chargeur** : **zero ligne changee**, aucune fabrique, aucune branche. La dette 3 de
  `CLAUDE.md` est intacte ;
- le **domaine** : `Question` ne gagne aucun champ ;
- les conditions `REC-1` a `REC-4` de `nadia-prof`, qui parlent d'un dessin et parlent identiquement
  d'une reproduction. `REC-3` (la couleur ne porte jamais la reponse) et `ART-1` mordent d'ailleurs
  plus fort ici que sur un dessin geometrique.

**Ce qui change, et c'est tout ce qui change** : la source des octets, le format, le repertoire,
l'extension, la population lue sur le prefixe, et **la preuve** - qui n'existait pas, parce qu'un
dessin maison n'a pas de provenance a prouver.

### 6. Cliquet exact a zero, et le poids est mesure par le lot qui livre

La population servie vaut **zero fichier et zero octet** au 2026-09-05, et le cliquet vaut
exactement cela. Un cliquet dont le plafond depasse la dette ne cliquette rien. **Le jour ou une
premiere reproduction entre, le garde-fou rougit**, et le lot qui la livre releve les deux nombres
sur la valeur qu'il installe, dans son propre diff. C'est le dispositif qu'`ADR-0080` a utilise pour
sa propre dette, et c'est ce qu'`ADR-0036` clause 5 avait ecrit pour les dessins.

### 7. Ce qui reste du au lot qui livre la premiere reproduction, ecrit maintenant dans sa forme exacte

Une dette n'est une dette que si on sait ce qu'elle coute et ce qui la rappelle. Les cinq points
ci-dessous ne sont pas payes par cet `ADR`, et **chacun a une alarme deja armee** :

| Du | Forme exacte | Ce qui le rappelle |
|---|---|---|
| **Amendement d'`ADR-0073`** : un identifiant declare par un paquet resout dans `illustrations/` **ou** dans `oeuvres/`, et la clause *"aucune ne dort"* vaut sur les deux repertoires | trois lignes dans `LIllustrationDeContenuResoutEtAucuneNeDortTest.verifier`, plus une morsure qui prouve qu'un identifiant `oeuvre-` inconnu des deux reste rouge | **le rouge de ce garde-fou lui-meme**, le jour ou un paquet declare une reproduction : il ne resoudra pas |
| **Seconde branche de gabarit** et **seconde exemption etroite** dans `RessourcesTiercesTest` : prefixe litteral `'/oeuvres/'`, un unique terme calcule, extension litterale `'.jpg'`, et **une morsure due avec le lot** - une seconde forme calculee doit rester rouge | meme forme, au caractere pres, que l'exemption d'`ADR-0073` clause 3 | le meme garde-fou, qui refuse aujourd'hui toute expression calculee non exemptee |
| **Plafond de pixels** : le plus grand cote d'une reproduction servie, lu dans l'en-tete du fichier | analogue matriciel du `viewBox` des dessins ; il tient le poids et evite de servir un fichier de qualite de substitution | rien aujourd'hui - **c'est la seule des cinq sans alarme**, et le cliquet de poids en tient la moitie |
| **Cliquet de poids mesure**, jamais estime | les deux constantes de la decision 6 | le cliquet a zero |
| **Habillage** de la nouvelle branche | la classe `illustration` existe ; une image matricielle a ses propres contraintes de plafond | `HabillageDesEcransTest`, qui rougit sur une classe qu'aucune feuille servie ne nomme |

### 8. Le registre n'est pas une source de rendu

Rien ne le lit a l'execution, et **cet `ADR` n'affiche rien a l'enfant**. Ce n'est pas un oubli :
afficher le titre et l'auteur a cote de l'image donnerait la reponse des lignes 4 et 6 de
l'histoire des arts, ce qui est exactement la contrefacon qu'`ART-4` interdit. **Si le produit doit
un jour crediter les oeuvres a l'ecran, c'est une decision de `camille-po` et un lot a part**, avec
sa propre question pedagogique.

---

## Regle executable

`bootstrap/src/test/java/com/recreo/archi/ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest.java`,
**18 tests**. Jeu fautif :
`bootstrap/src/test/resources/contreexemple/ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest/`.

> **Toute reproduction servie a une entree de registre, toute entree a son fichier, chaque entree
> prouve separement que l'oeuvre est libre et que la photographie l'est, chaque fichier est une
> image JPEG, et les trois populations d'images du depot restent disjointes.**

**Ce n'est pas une regle ArchUnit**, et le compte de regles ArchUnit du depot est inchange :
ArchUnit modelise des types Java et leurs relations ; les objets juges ici sont **des fichiers
d'images, des entrees de registre et des repertoires**, dont aucun n'est un type. C'est un
garde-fou de balayage, de la meme famille que ceux d'`ADR-0070`, d'`ADR-0071`, d'`ADR-0073` et
d'`ADR-0081`. **Je ne lui donne pas de rang dans cette famille** : le depot compte **66** garde-fous
de balayage - mesure du 2026-09-05 sur les cinq modules declares, par le critere du recensement
lui-meme, un fichier de source de test qui cite `src/main/`, le recensement retire - et aucun
decompte de *motifs distincts* n'a de methode ecrite. Le mecanisme automatique qui le garde est la
porte technique, `mvn -B verify` sur le reacteur complet.

**Perimetre derive, jamais enumere** (`ADR-0013` obligation 1) : les modules viennent du pom
agregateur, par `LesGardeFousSontMisALEpreuveTest.modulesDeclares`, **reutilisee et non reecrite** ;
les fichiers viennent des trois repertoires d'images de chaque module ; les entrees viennent de
SnakeYAML, la bibliotheque avec laquelle le chargeur de production lit les paquets. Le juge est une
**fonction pure**, `verifier(registre, reproductions, illustrations, decor)`, qui ne lit ni disque
ni classpath : c'est ce qui permet de le mordre sur du materiau mute en memoire plutot que sur une
fixture ecrite a la main.

**Ce que la regle interdit reellement** - la question que je me pose avant d'ecrire une regle, et
la reponse n'est pas *"rien"* :

1. deposer une image d'oeuvre **sans preuve** : aucune entree de registre ;
2. ecrire une preuve **sans image** : une entree que rien ne sert ;
3. laisser vide **n'importe lequel** des sept champs de texte, dont les deux qui rendent la preuve
   refaisable - un identifiant d'objet absent rend la verification impossible des que l'adresse
   pourrit ;
4. entrer un auteur **mort au vingtieme siecle**, c'est-a-dire l'endroit exact ou un calcul de duree
   serait necessaire et pourrait etre faux ;
5. entrer une **oeuvre anonyme recente** ;
6. **nommer un auteur sans dire quand il est mort** - la faute la plus silencieuse de la famille,
   parce que l'entree a l'air complete ;
7. porter une **annee de mort sur une oeuvre anonyme** : une preuve inventee a l'air d'une preuve ;
8. ecrire *"domaine public"*, *"libre de droits"* ou toute autre incantation a la place d'une
   licence identifiable ;
9. deposer un fichier qui **n'est pas une image JPEG**, ou qui ne porte pas l'extension que le
   gabarit ecrit en clair ;
10. **confondre les populations** : un nom de base partage entre deux repertoires, ou le prefixe
    `oeuvre-` pose sur un dessin maison ou sur une image de decor - ce qui ferait chercher le
    fichier dans le mauvais repertoire au rendu ;
11. faire **grandir le corpus en silence**, par le cliquet a zero.

**Ce que la regle ne juge pas, et qui reste a `nadia-prof`** : que l'attribution soit exacte - qu'un
tableau soit bien de qui on le dit -, que la reproduction soit fidele, et que le corpus tienne
`ART-5` (ni une seule epoque, ni une seule aire culturelle, ni des artistes d'un seul genre). **Une
entree qui ment passe.** Elle l'a ecrit elle-meme : *"aucun automate ne verifie qu'un tableau est
bien de qui on le dit"*. Ce qui est tenu est la **forme** de la preuve et sa **refaisabilite**, pas
sa veracite.

### Ce que ce garde-fou examine aujourd'hui, honnetement

**La population est vide** : `static/oeuvres/` n'existe dans aucun module et le registre ne declare
aucune oeuvre. Toutes les clauses portant sur une entree ou sur un fichier portent donc sur rien.
`ADR-0051` l'oblige et le fichier le declare, dans un test qui porte ce nom. **Les treize morsures
ne portent pas sur rien** : onze mutent un materiau de reference en memoire, deux s'executent sur
le jeu fautif commite.

**Une limite que je nomme plutot que de la laisser decouvrir** : le materiau de reference des onze
morsures en memoire est **de ma main**, faute de corpus reel a muter. C'est exactement le defaut que
s05 a etabli - *"une fixture ecrite par l'auteur d'une regle ressemble a ce qu'il imagine"* - et je
ne peux pas y echapper tant qu'aucune oeuvre n'est servie. **Le lot qui livre les premieres
reproductions remplace ce materiau de reference par du materiau reel mute**, et c'est une dette
ecrite, pas une preference.

---

## Le poids, et ce qu'il change au packaging

**Methode, avant les chiffres.** Le fat jar est mesure sur l'artefact `bootstrap/target/recreo.jar`
du 2026-09-04, **non reconstruit pour cette mesure** ; les chiffres des dessins sont les constantes
du garde-fou de poids, lues et non recalculees ; **le poids d'une reproduction est une estimation,
pas une mesure** - le depot ne contient aucune image photographique, et fabriquer une fixture pour
la mesurer aurait produit un chiffre sur ce que j'imagine.

| Grandeur | Valeur | Source |
|---|---|---|
| Fat jar aujourd'hui | **21 793 001 octets** | artefact du 2026-09-04 |
| Dessins servis | 24 fichiers, cliquet total **30 730 octets** | constantes du garde-fou de poids |
| Plafond de rendu d'une image d'item | **176 px** | `PLAFOND_DE_RENDU_EN_PIXELS`, meme garde-fou |
| Reproduction servie, plus grand cote | **352 px** vise (deux fois le plafond, pour un ecran a double densite) | derive du precedent |
| Poids d'une reproduction a cette taille | **25 a 50 ko**, *estimation* | ordre de grandeur d'un JPEG photographique de cette taille |
| Corpus pour neuf lignes | **30 a 45 oeuvres**, *estimation* | environ 90 items pour dix par ligne, une oeuvre servant deux a trois items |
| Corpus total | **1,1 a 2,2 Mo**, *estimation* | produit des deux precedents |

**Ce que cela change au packaging : rien.** Le fat jar passe d'environ 20,8 a environ 22,6 Mio, soit
**+5 a +10 %**. Pas de CDN, pas de stockage externe, pas de telechargement au build - lequel
casserait la garantie hors ligne d'`ADR-0014` et ferait dependre la construction du produit de la
disponibilite d'un musee.

**Ce que cela change vraiment est ailleurs, et c'est pour cela que le plafond de pixels est du** :
le meme corpus a la resolution ou les musees le publient - 3 000 a 6 000 px, 3 a 10 Mo par fichier -
peserait **100 a 400 Mo**, deux ordres de grandeur au-dessus. Un depot Git ne s'en remet pas : un
binaire n'a pas de diff et ne sort jamais de l'historique.

---

## Consequences

**Les bonnes.**

- Neuf lignes atteignables cessent d'etre bloquees par une question non instruite. Elles restent
  `non couvert` - cet `ADR` ne les ouvre pas -, mais l'obstacle nomme par `nadia-prof` a une reponse.
- Le depot peut **montrer** pourquoi chaque image est libre, et un tiers peut le reverifier. C'est
  plus que ce que le produit tenait sur n'importe quelle autre ressource, y compris ses
  bibliotheques vendorisees, gardees par une empreinte mais pas par une provenance.
- **Zero ligne de chargeur, zero champ de domaine, zero forme d'item.** Le principe directeur -
  une mecanique reutilisable, un contenu pilote par des regles - traverse un changement de nature de
  contenu sans bouger, ce qui est la premiere fois qu'il est mis a cette epreuve.

**Les mauvaises, et je ne les adoucis pas.**

- **Le vingtieme siecle est absent du produit.** C'est le prix du pivot, il est structurel, et il
  ampute une partie de ce que l'histoire des arts de CM1 attend. `nadia-prof` juge si ce qui reste
  vaut d'etre servi ; si sa reponse est non pour les lignes 4 et 6, le chantier retrecit et il faut
  le savoir avant de constituer un corpus.
- **La clause 4 d'`ADR-0073` - aucun texte dans une illustration - ne s'applique pas ici et ne peut
  pas s'appliquer.** Un JPEG ne se lit pas comme un SVG, et surtout une oeuvre reproduite **porte
  souvent du texte** : une signature, une inscription, un manuscrit. Le motif de la clause n'est pas
  defait - elle existait pour que le texte **ecrit par le produit** ne sorte pas des garde-fous de
  langue, et le produit n'ecrit rien la -, mais **l'enfant peut voir des mots qu'aucun garde-fou n'a
  lus**. C'est une question pedagogique, elle est routee.
- **Une preuve reste une transcription.** Une annee de mort fausse mais anterieure a 1900 passe. Le
  garde-fou tient la forme, jamais la verite ; c'est `nadia-prof` qui tient l'exactitude, et elle
  seule.
- **Un binaire entre dans l'historique Git** et n'en sort plus. Le plafond de pixels est ce qui
  separe 2 Mo de 200.
- **Une troisieme population, une branche de gabarit et une exemption de plus.** Chaque exemption
  affaiblit un garde-fou d'un cran ; celle-ci est etroite par construction et doit etre mordue par
  le lot qui l'ouvre, sans quoi elle prouvera qu'elle existe et non qu'elle est etroite.
- **Un risque juridique residuel demeure**, et il n'appartient pas a un architecte : le depot est
  public. Ce que cet `ADR` garantit est la tracabilite de la preuve, pas l'immunite.

---

## Alternatives considerees

| Alternative | Pourquoi elle est ecartee |
|---|---|
| **Wikimedia Commons comme source de preuve** | La licence y est lisible par machine, mais **la revendication est celle d'un contributeur**, les fichiers sont renommes et supprimes, et un bandeau *PD-Art* est une affirmation, pas une renonciation d'un titulaire identifiable. Excellent endroit pour **trouver** une oeuvre, jamais pour **prouver** qu'elle est libre |
| **S'appuyer sur l'article 14 de la directive (UE) 2019/790** | Probablement suffisant pour une oeuvre plane, mais ne couvre pas les objets en trois dimensions, **ne laisse aucune trace enregistrable** et ferait reposer le depot sur une lecture juridique que son auteur ne peut pas verifier. Conserve comme **declencheur ecrit**, voir plus bas |
| **Calculer la duree de protection (70 ans post mortem)** | Arithmetique juridique dans le depot, prorogations de guerre et durees etrangeres a maintenir, **dependance a l'horloge**, et un mode de defaillance en **faux vert**. Le pivot rend le calcul inutile pour le prix d'une amputation nommee |
| **Une licence acquise sur un corpus prive** (option C de `DEC-0075`) | Non tranchee ici : l'utilisatrice a choisi l'option B. Techniquement, elle ne changerait ni le registre, ni le garde-fou, ni la decision 2 - **seule la valeur admise de `licenceDeLaReproduction` changerait**, ce qui est une ligne. Le dispositif ne prejuge donc pas de la source |
| **Le meme repertoire que les dessins maison** | Quatre faits mesures s'y opposent : le garde-fou d'`ADR-0073` lit chaque fichier en UTF-8 et **exploserait** sur un JPEG ; les criteres de geometrie n'ont aucun sens sur une image matricielle ; le cliquet de poids des dessins est exact et devrait de toute facon se dedoubler ; et la clause *aucun texte* est inverifiable sur un JPEG. Ce serait quatre exemptions **a l'interieur** de garde-fous existants, contre une population de plus |
| **Une cle d'item declarant la population** (`illustrationEstUneOeuvre`) | Ferait traverser une composante a `Question`, a la fabrique, a la session et au controleur, pour une distinction que **l'arborescence porte deja**. Le prefixe coute un `startsWith` dans l'adaptateur et se verifie des deux cotes. Et une cle declarative peut mentir - un prefixe verifie contre le repertoire, non |
| **Telecharger le corpus au build, ou le servir depuis un stockage externe** | Casse la garantie hors ligne d'`ADR-0014` et fait dependre la construction du produit d'un tiers. Le produit se deploie en un fat jar unique, `java -jar`, et cette propriete ne se troque pas contre 2 Mo |
| **Le registre en Markdown dans `squad/programme`, comme les referentiels d'`ADR-0081`** | Demanderait un second analyseur ecrit a la main la ou SnakeYAML lit deja les donnees du depot. La forme suit ce que la machine lit deja, pas ce que le voisin ressemble |

---

## Le cout, et le decoupage en taches

**Methode, parce qu'un chiffre sans methode ne vaut rien** : les taches ci-dessous sont mon
estimation de developpement ; **la borne d'audit est le tarif publie par `nadia-prof` le 2026-09-03**
(`ARBITRAGE-PREALABLE-s28`, sections 4.3 et 4.4), et **l'arithmetique qui l'applique est la mienne**.
Le tarif est le sien ; sa confirmation lui appartient, et je ne la prononce pas a sa place.

### Les taches

| # | Tache | Qui | Cout |
|---|---|---|---|
| **T1** | Registre, garde-fou, jeu fautif, `ADR` | `theo-archi` | **fait par cet `ADR`** |
| **T2** | Amendement d'`ADR-0073` : resolution sur deux populations, plus sa morsure | dev | ~0,5 j |
| **T3** | Rendu : derivation du prefixe dans le controleur, seconde branche de gabarit, seconde exemption etroite dans `RessourcesTiercesTest` **et sa morsure**, habillage | dev | ~1 j |
| **T4** | Plafond de pixels, cliquet de poids **mesure**, remplacement du materiau de reference des morsures par du materiau reel | dev | ~0,5 j, **du avec T5** |
| **T5** | Constitution du corpus : choix des oeuvres (`nadia-prof`), recuperation, redimensionnement, entree de registre | `nadia-prof` puis dev | ~15 min par oeuvre cote developpement, **hors choix** |
| **T6** | Paquet arts plastiques CP, lignes 9 et 10 | dev, audit `nadia-prof` | 20 items |
| **T7** | Paquet arts plastiques CM1, lignes 7 et 8 | dev, audit `nadia-prof` | 20 items |
| **T8** | Paquets histoire des arts CM1, lignes 1, 2, 3, 4, 6 | dev, audit `nadia-prof` | jusqu'a 50 items, donc trois lots |

**T2, T3 et T4 forment le dispositif** : environ deux jours de developpement, et ils tiennent dans un
sprint a cote d'autre chose. **T5 a T8 sont le chantier**, et c'est la que la reponse change.

### Le chantier ne tient pas en un sprint, et ce n'est pas le code qui le borne

Application du tarif de `nadia-prof` a chaque lot, sous l'hypothese *matiere dont elle n'est pas la
source* (1,5) - elle l'a ecrit pour l'histoire des arts : *"cette matiere est de la famille de
l'histoire pour l'exactitude factuelle, et je n'en suis pas la source"* - **plus le supplement de
lot illustre** (+0,5, `REC-1`) :

| Lot | Items | Prix applique |
|---|---|---|
| Arts plastiques CP, lignes 9 et 10 | 20 | **2,0** |
| Arts plastiques CM1, lignes 7 et 8 | 20 | **2,0** |
| Histoire des arts CM1, lignes 1, 2, 3, 4, 6 | 50, soit **trois** lots | **6,0** |
| **Total pour fermer les neuf lignes** | ~90 | **10,0** |

**Sa borne est de 5,0 par sprint.** Fermer les neuf lignes coute donc **au moins deux sprints
entierement consacres a ce chantier, sans marge pour quoi que ce soit d'autre** - donc trois ou
quatre sprints en pratique, plus le sprint du dispositif. **Le premier increment naturel est le lot
d'arts plastiques CP** : 20 items, deux lignes, **2,0** contre sa borne, et il exerce le dispositif
entier sur le plus petit perimetre possible.

**Deux reserves sur ce calcul, et elles sont a elle** : l'hypothese *1,5* peut etre *1,0* si elle se
declare source des arts plastiques, ce qui ramene le total a 8,0 ; et **la verification de
l'attribution d'une oeuvre n'est dans aucune ligne de son tarif**, alors qu'elle s'en declare la
seule garde. Si ce geste vaut un supplement, tous les nombres ci-dessus montent.

**Et `s29` n'est pas concerne** : elle est deja pleine a 5,0 sur sa propre borne. Cet `ADR` instruit,
il ne planifie pas.

---

## Ce que je route, sans le decider

**A `nadia-prof`** :

1. **Le registre remplace le commentaire de paquet pour `ART-3`.** Je tiens ta propriete - titre,
   auteur, date, lieu de conservation - a un autre endroit, parce qu'un commentaire n'est verifiable
   par personne. Si tu tiens au commentaire, il devient une **seconde source** de la meme
   identification, et deux sources divergent - c'est le motif de la clause 3 d'`ADR-0081`.
2. **Le pivot de 1900 ampute le vingtieme siecle.** Les lignes 4 et 6 de l'histoire des arts, qui
   portent sur les epoques et sur les reperes du patrimoine, seraient servies sur un corpus qui
   s'arrete a 1900. Est-ce que ce qui reste sert encore la ligne, ou est-ce une contrefacon par
   perimetre ?
3. **`REC-1` transpose-t-elle a une reproduction ?** Nommer un dessin propositions masquees et
   nommer une oeuvre propositions masquees ne sont pas le meme geste : devant un tableau, le premier
   nom qui vient est rarement celui que l'item attend.
4. **Une oeuvre reproduite peut porter du texte** - signature, inscription, manuscrit -, et aucun
   garde-fou de langue ne l'a lu. Est-ce recevable pour un enfant de CP, de CM1 ?
5. **Le prix de la verification d'attribution**, qui n'est dans aucune ligne de ton tarif alors que
   tu t'en declares la seule garde.

**A `camille-po`** :

1. `DEC-0075` reste `Proposee` : c'est a toi de l'ecrire `Acceptee` sur l'option B, avec la date de
   l'arbitrage de l'utilisatrice.
2. **Le perimetre et l'ordre des lots** : le calcul ci-dessus dit que le chantier depasse un sprint,
   il ne dit pas ce qui passe en premier.
3. **Faut-il crediter les oeuvres a l'ecran ?** Le registre ne le fait pas, et le faire donnerait la
   reponse des lignes 4 et 6 (`ART-4`). C'est un lot a part et une question de produit.

**A l'utilisatrice, via `camille-po`** : le **risque residuel**. Cet `ADR` rend la preuve tracable
et reverifiable ; il ne rend personne juriste. Publier des images sur un depot public reste une
decision qui lui appartient.

---

## Ce qui me ferait changer d'avis

Ecrit maintenant, pour que ce soit un declencheur et non une revision.

1. **Une oeuvre que `nadia-prof` juge necessaire n'existe qu'en reproduction non CC0.** Alors
   l'article 14 de la directive entre comme **second motif**, et il entre borne : `motifDeLaReproduction`
   devient un vocabulaire ferme a deux valeurs, un champ `nature` devient obligatoire, et le
   garde-fou refuse le motif *article 14* des que la nature n'est pas plane - une photographie d'une
   sculpture comporte des choix de prise de vue que cet article ne couvre pas. **La forme est ecrite
   ici pour n'avoir pas a l'inventer sous pression.**
2. **Le corpus depasse quelques megaoctets.** Alors le format se rediscute - un format plus compact
   pese environ un tiers de moins -, au prix d'une etape d'encodage de plus et d'une extension
   litterale a changer dans un gabarit.
3. **`nadia-prof` demande qu'`ART-5` soit calculable.** Alors le registre gagne une epoque et une
   aire culturelle par oeuvre, et le seuil reste a elle : un garde-fou qui prononcerait *"ce corpus
   est assez divers"* rendrait un verdict pedagogique en CI.
4. **Le produit doit crediter les oeuvres a l'ecran.** Alors le registre cesse d'etre un artefact de
   depot et devient une ressource servie, avec le poids et le chargement que cela suppose.

---

## Le verdict qu'on m'a demande de rendre, y compris s'il etait negatif

Une quatrieme reponse etait ouverte : *"cette voie ne tient pas"*. **Elle n'est pas la mienne : la
voie tient**, a condition d'etre retrecie comme ci-dessus - oeuvres anterieures a 1900, reproductions
dont l'institution a explicitement renonce a ses droits, preuve enregistree et refaisable oeuvre par
oeuvre.

**Ce qui ne tient pas, en revanche, c'est l'idee que ce chantier soit petit.** Le dispositif coute
deux jours ; le corpus et les paquets coutent **au moins deux sprints entiers de la borne d'audit de
`nadia-prof`**, et la contrainte qui borne n'est pas le code. Et ce que le produit servira n'est pas
*"l'art"* : c'est ce que le domaine public en montre, ce qui s'arrete au dix-neuvieme siecle. C'est
un resultat, il est nommable d'avance, et il vaut mieux qu'il soit lu maintenant que decouvert au
troisieme lot.
