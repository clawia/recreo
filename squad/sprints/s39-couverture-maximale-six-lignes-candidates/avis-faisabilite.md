# s39 - Avis de faisabilite (`theo-archi`) - `S39-ENERGIE` et `S39-OUTILS`

- **Date** : 2026-09-22
- **Base** : `round-two` a `5a7a2312`, stories de `camille-po` dans `docs/backlog/backlog.md`,
  section "Sprint 39" (non commitees a l'heure de cet avis).
- **Perimetre de cet avis** : **deux** lots sur les six, ceux que `DEC-0092` dispense d'arbitrage
  pedagogique prealable - `S39-ENERGIE` (`SCI-CM1-04`) et `S39-OUTILS` (`ART-CP-11`). Les quatre
  autres (`S39-DEN`, `S39-LONGUEUR`, `S39-MONNAIE`, `S39-SOLIDES`) sont en arbitrage prealable
  chez `nadia-prof` et **ne sont pas instruits ici**.
- **Ce que cet avis ne vaut pas** : il ne vaut pas livraison. L'affectation nominative du
  developpement revient a `sofia-sm` (`DEC-0092`, section 5, reconduisant `DEC-0091`). Aucun code
  n'est ecrit avec cet avis.

---

## 1. Verdicts

**`S39-ENERGIE` : faisable en l'etat, sans `ADR` neuf et sans regle neuve.** C'est le cas le plus
banal du depot : un paquet statique de type `question` de plus dans un lot de matiere deja servi
(`Sciences et technologie` / `CM1`, 8 paquets aujourd'hui). Complexite **basse**.

**`S39-OUTILS` : faisable en l'etat, sans `ADR` neuf et sans regle neuve, mais ce n'est pas le
meme cas.** Le mecanisme est identique ; ce qui change est que **`Arts plastiques` serait la
treizieme matiere du corpus servi et la premiere de cette matiere**. Complexite **basse cote
architecture**, le cout reel etant editorial et pedagogique. Section 4.

## 2. Le mecanisme existe deja, et aucun `ADR` neuf n'est du

La question posee - *existe-t-il un mecanisme du produit qui couvre le choix textuel pour du
contenu de connaissance fixe, sans code neuf ?* - a deja trois reponses ecrites, et elles se
composent :

- **`ADR-0004`** decouple la mecanique du contenu : `bandit-manchot` ne connait pas la matiere
  qu'elle sert.
- **`ADR-0007`** impose **un seul chargeur pilote par un schema** : un paquet de type `question`
  entre par sa donnee, pas par une classe.
- **`ADR-0096`** a acte que **le paquet statique est la norme du depot**, le generatif
  l'exception, et que le choix du moyen est reversible sans toucher aux garde-fous.

**Preuve, sur du materiau reel plutot que sur mon estimation.** Commit `712c293b` (`[LEA]`, s30),
qui a livre deux paquets de sciences CM1 servant `SCI-CM1-05` et `SCI-CM1-17` - exactement le
regime que la story de `S39-ENERGIE` invoque : **7 fichiers, 512 lignes ajoutees, zero ligne de
Java de production**.

Je n'ecris donc **aucun `ADR`** pour ces deux lots. Un `ADR` qui redirait ce que `ADR-0004`,
`ADR-0007` et `ADR-0096` decident deja serait un `ADR` decoratif, et ecrire un `ADR` apres coup
pour documenter une pratique deja reglee est precisement ce que mon mandat m'interdit.

**Ce qui me ferait changer d'avis, ecrit maintenant** : si `nadia-prof` refusait la liste finie au
motif de la repetition sur une partie de dix manches, la question du generatif se rouvrirait - et
elle se paierait alors en production seule, les garde-fous portant la propriete et jamais le moyen
(`ADR-0096`, decision 3).

## 3. `S39-ENERGIE` (`SCI-CM1-04`) - fichiers concernes

Derives du commit `712c293b`, un paquet au lieu de deux :

| Fichier | Nature |
|---|---|
| `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-<nom>.yaml` | paquet neuf, `type: question`, `lignesDeReferentiel: ["SCI-CM1-04"]` |
| `adapters/adapter-content/src/main/resources/catalogue.yaml` | une entree, titre et consigne propres |
| `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCm1Sciences<Nom>Test.java` | garde-fou du paquet |
| `adapters/adapter-content/src/test/java/com/recreo/adapter/content/LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java` | inventaire d'exemption du domaine numerique, une entree avec son motif **mesure** |
| `bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java` | inventaire des chaines exposees par le port entrant |

**Zero fichier de production Java, zero schema, zero contrat.**

## 4. `S39-OUTILS` (`ART-CP-11`) - ce qui differe, et ce qui ne differe pas

**Ce qui ne differe pas** : la meme liste de fichiers qu'en section 3, avec
`adapters/adapter-content/src/main/resources/contenu/cp-arts-<nom>.yaml` et
`lignesDeReferentiel: ["ART-CP-11"]`.

**Ce qui differe : `Arts plastiques` serait une matiere neuve du corpus servi.** Comptage du
2026-09-22 sur `adapters/adapter-content/src/main/resources/contenu/` (78 fichiers lus, descripteur
par descripteur) : **12 couples (matiere, niveau) servis, aucun en arts plastiques**. J'ai verifie
ce que cela coute **structurellement**, et la reponse est : rien.

1. **Aucune enumeration de matieres en production.** `matiere` est un champ de
   `domain/.../ContentDescriptor` ; aucune classe de production, aucun gabarit Thymeleaf, aucune
   feuille de style ne nomme une matiere. Recherche sur `"Questionner le monde"` - la derniere
   matiere neuve entree (s27) - hors du repertoire de contenu : **que des tests**.
2. **Aucun garde-fou a perimetre enumere par matiere.**
   `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` (`ADR-0049`) ne porte aucune liste de
   matieres : il exige une seule graphie par matiere, ce qu'une matiere neuve satisfait par
   construction. `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest` (`ADR-0079`)
   derive ses lots des descripteurs ; ses assertions de rapprochement ne portent que sur le lot
   `Questionner le monde / CP` et un lot neuf ne les touche pas.
3. **Le code de ligne est deja alloue.**
   `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` (`ADR-0081`) lit les codes dans
   `squad/programme`, jamais dans une liste. `ART-CP-11` y est
   (`squad/programme/arts-plastiques/competences-cp.md`, ligne 47) et `SCI-CM1-04` aussi
   (`squad/programme/sciences-et-technologie/competences-cm1.md`, ligne 50). Rien a allouer.

**Le cout reel de ce lot est donc editorial, pas architectural**, et il vit sous une contrainte que
le lot sciences n'a pas : **c'est du CP**.

## 5. Garde-fous qui mordent sur ces deux lots

Aucun n'est a ecrire ; tous sont a tenir. Ceux qui decident la forme du paquet, donc a lire
**avant** d'ecrire un item et non apres un rouge :

| Regle | Ce qu'elle impose ici |
|---|---|
| `ADR-0023` | le paquet offre **strictement plus** d'items que la partie n'a de manches : 11 items pour `nombreDeManches: 10`, ce que font les paquets de sciences deja fermes |
| `ADR-0063` | au plus **trois** propositions par item |
| `ADR-0032` / `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` | deux items d'un meme paquet ne presentent jamais le meme ecran |
| `ADR-0086` / `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` | la longueur ni la forme des propositions ne designent la bonne reponse |
| `ADR-0067` (`TYP-1`) | blanc insecable avant `?` et `:` dans tout enonce francais neuf |
| `ADR-0065` / `AucunMotServiNeSEcarteDeSonAccentTest` | chaque mot servi est juge sur le lexique exterieur - **point de vigilance sur le vocabulaire plastique** (`esquisse`, `argile`, `fusain`) |
| `ADR-0049` | `Arts plastiques` s'ecrit d'une seule graphie dans toute la donnee |
| **`ADR-0089`** | **`S39-OUTILS` seulement** : au CP, **au plus quinze mots et deux phrases par enonce** (`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`). Contrainte reelle sur une definition de vocabulaire - *"Avec quel outil peint-on ?"* passe, une glose ne passe pas |

Conditions pedagogiques opposables, rappelees pour memoire et **non arbitrees par moi** : `ART-1`
(aucune reponse portee par une couleur percue) et `ART-2` (aucune question sur le ressenti) sur
`S39-OUTILS` ; `SCI-1` a `SCI-4` et `FER-1` a `FER-3` sur `S39-ENERGIE`. `nadia-prof` a deja tarife
les arts plastiques CP a *"non au-dela d'`ART-1`"* (`squad/conformite/ARBITRAGE-PREALABLE-s32-trois-
instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md`, ligne 524) :
c'est ce qui rend l'absence d'arbitrage prealable coherente, et non une omission.

## 6. Risques

**R1 - l'inventaire d'exemption du domaine numerique pourrit en silence (les deux lots).**
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` demande un **motif mesure** par paquet
exempte. Ce fichier porte deja la trace de sa propre derive : les motifs de trois paquets
`cp-qlm-*` dataient du 2026-09-02 et n'avaient pas suivi le lot de s28 qui les a portes de huit a
quatorze items, **deux d'entre eux etant faux sur la forme** ; recomptes le 2026-09-10. Un motif
recopie d'un paquet voisin au lieu d'etre compte sur le paquet livre reproduit ce defaut.
**Parade** : compter sur le paquet servi, ecrire le compte et sa date.

**R2 - `ART-CP-11` est du vocabulaire, et le vocabulaire se heurte a `ADR-0089` (S39-OUTILS).**
C'est le seul endroit ou un rouge est previsible. Il est mecanique et se voit au premier `verify`.

**R3 - `SCI-CM1-04` touche a l'energie, ou la simplification fausse est facile (S39-ENERGIE).**
*"Le soleil produit de l'electricite"* est le genre d'item qu'un garde-fou ne verra jamais et que
`nadia-prof` refusera. Hors de ma main, nomme ici pour qu'il ne soit pas decouvert au Done.

**R4 - `-am` sur toute mesure partielle** (`CLAUDE.md`). `mvn -pl adapters/adapter-content test`
sans `-am` mesure le depot local, pas le worktree. La porte reste `mvn -B clean verify` sur la
racine, et la porte technique est **l'ensemble des jobs de `.github/workflows/ci.yml`**, pas
`mvn verify` seul.

## 7. Ce que cet avis ne dit pas

Il ne dit pas quels faits, quels outils ni quels exemples de conversion retenir - cela reste a
l'auteur du paquet puis a `nadia-prof`. Il ne prononce aucun Done pedagogique. Il ne dit pas si
ces deux lignes passeront `couvert-valide` : `FER-2` et l'audit d'items sont dans la seule main de
`nadia-prof`.

---

# s39 - Avis de faisabilite (`theo-archi`), seconde partie - `S39-DEN`, `S39-LONGUEUR`, `S39-MONNAIE`

- **Date** : 2026-09-22. **Base** : `round-two` a `502dcca6`.
- **Entree** : `squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md`
  (`nadia-prof`, 2026-09-22), sections 1 a 3.
- **Perimetre** : **trois** des quatre lots arbitres. `S39-SOLIDES` est instruit a part et n'est
  pas traite ici.
- **Version de mesure** : toute commande citee ci-dessous a ete lancee par
  `bash outils/mvn-jdk21.sh`, qui a rapporte **`Maven lance sur le JDK de la toolchain jdk 21 :
  21.0.10`**. C'est la version que la machine virtuelle des tests rapporte, pas celle qu'une entree
  de toolchain declare (`ADR-0071`).
- **Ce que cet avis ne vaut pas** : ni livraison, ni affectation (`DEC-0092`, section 5). Aucune
  ligne de code n'est ecrite avec lui. Il ne prononce aucun Done pedagogique et ne rouvre aucune
  borne de `nadia-prof`.

## 8. Verdicts

| Lot | Verdict |
|---|---|
| `S39-DEN` | **Faisable en l'etat pour la part servie** (`DEN-1` a `DEN-8`, ligne plafonnee `en cours`), sans `ADR` neuf. **Infaisable dans ce sprint pour `DEN-2`** : je confirme `nadia-prof` sur le code, et je chiffre le chantier - section 9 |
| `S39-LONGUEUR` | **Faisable moyennant un `ADR`**, pour la part textuelle seule. Deux obstacles verifies qu'aucune story ne nomme : l'allocation de sous-partie d'`ADR-0097`, et une **collision directe entre `MES-CE1-3` et un garde-fou en vigueur** - section 10 |
| `S39-MONNAIE` | **Faisable moyennant le meme `ADR`**, `couvert-valide` techniquement atteignable. Un obstacle propre, verifie : **les centimes sortent le paquet du domaine numerique** - section 11 |

**Obstacle commun aux trois, et il est deja rouge.** `mvn -B clean verify` sur la racine de ce
worktree **echoue** a `502dcca6`, avant toute story de s39 :
`ToutesLesReferencesDeDecisionResolventTest.leDepotReelEstCoherent` (`ADR-0066`, clause 1) nomme
deux fichiers commites qui citent `DEC-0092` - l'arbitrage prealable de `nadia-prof` et la premiere
partie de cet avis - alors qu'**aucun fichier de `squad/decisions/` ne porte `DEC-0092`**. Mesure :
`Tests run: 265, Failures: 1` sur `recreo-bootstrap`, les cinq autres modules verts. La cause n'est
pas une faute de redaction : `DEC-0092` existe, il n'est simplement pas commite. **Ce document
ajoute une troisieme citation et n'ameliore pas ce rouge** - je le nomme plutot que de l'ignorer.
Il se paie en commitant `DEC-0092`, ce qui n'est pas de ma main.

## 9. `S39-DEN` - je confirme `DEN-2` sur le code, et je chiffre ce qu'il coute

**Ce que j'ai lu moi-meme**, sans recopier : `Designation.java`, `DesignationSession.java` (268
lignes), `DesignationMove.java` (11 lignes), `ZoneADesigner.java`, `Zone.java`, `Item.java`,
`ReponseAEcrire.java`, `Question.java`.

**Le constat de `nadia-prof` est exact.** `DesignationMove` porte **un seul** identifiant de zone
par coup ; `DesignationSession.jouer` resout la manche des que ce choix est la bonne zone, et une
zone fausse n'est que **marquee** dans `zonesEssayees` - jamais deplacee, jamais regroupee, jamais
porteuse d'un etat propre. Aucune boucle de la session ne permet une sequence d'actions sur les
elements d'une meme collection.

**Deux choses qu'elle n'avait pas a verifier, et qui ferment la question au lieu de la laisser
ouverte.** Je les ai cherchees precisement parce qu'elle juge le pedagogique et pas la faisabilite :

1. **Le contrat de contenu n'a que trois formes d'item**, et c'est une couture **scellee** :
   `public sealed interface Item permits Question, ReponseAEcrire, ZoneADesigner`. Il n'existe donc
   aucune quatrieme forme dormante qu'une story pourrait reveiller.
2. **`ReponseAEcrire` ne porte aucune illustration** - verifie sur ses composantes, la ou
   `Question` en porte une facultative depuis `ADR-0073`. La voie a laquelle on penserait
   spontanement - *une collection dessinee, et l'enfant ecrit combien il en a compte* - n'est donc
   **pas constructible aujourd'hui**, meme en laissant de cote qu'elle ne satisferait pas `DEN-2`
   faute d'action sur la collection. Je le dis pour qu'elle ne soit pas proposee comme un raccourci
   au sprint suivant.

**Ce qu'un `DEN-2` satisfait couterait, chiffre sur du materiau reel plutot que sur mon
estimation.** Le depot a deja introduit une mecanique une fois, et c'est la mesure la plus proche :

- **six `ADR` sur neuf sprints d'ecart** pour la designation - `ADR-0080` (s28, le plus long du
  depot), `ADR-0088` (s32), `ADR-0091` et `ADR-0092` (s35), `ADR-0093` et `ADR-0094` (s36) ;
- **13 fichiers de test dont le nom porte `Designation` ou `Zone`** (methode : `find` sur les
  quatre racines de test du reacteur, correspondance sur le nom de fichier, comptage du
  2026-09-22 ; 12 tests et un contre-exemple). Ce chiffre ne compte pas les garde-fous existants
  qu'il a fallu etendre, il est donc un **plancher** ;
- et une quatrieme permission a la couture scellee, dont `ADR-0080` a mesure qu'elle arrete le
  compilateur a huit endroits exacts pour la troisieme.

**Et ce serait plus cher, pas moins.** La designation a reutilise la boucle de partie de
`BanditManchotSession` mot pour mot (`ADR-0080`, decision 3) : trois essais, revelation, une manche
resolue par revelation ne compte pas. Une mecanique de denombrement **ne peut pas** la reutiliser -
`DEN-2` demande une sequence d'actions sur une meme collection, donc un etat par element au sein
d'une manche, donc une boucle de partie neuve. Le poste de cout qui a ete **evite** a s28 est
precisement celui qu'il faudrait payer ici.

**Verdict.** La part textuelle (`DEN-1` a `DEN-8`) est un paquet statique ordinaire, du meme regime
que la section 2 de cet avis, sans `ADR` neuf. `DEN-2` est **un chantier de mecanique, pas une
story** : infaisable dans s39 sans revoir le decoupage. Je ne l'ouvre pas de moi-meme - c'est a
`sofia-sm` et `camille-po` de decider s'il entre un jour au backlog, et a `nadia-prof` seule de
dire si la ligne le vaut.

## 10. `S39-LONGUEUR` - deux obstacles verifies, dont un que la borne pedagogique heurte de front

### 10.1 La part textuelle a un precedent servi, et il dit aussi comment ne pas s'y prendre

`cm1-mesures` sert `MAT-CM1-08` par 24 items de type `question`, conversions `cm`/`m`/`mm`/`km`,
`g`/`kg`/`tonne`, `ml`/`cl`/`L`, **sans aucune image**. Le mecanisme demande par `MES-CE1-1` a
`MES-CE1-3` existe donc, servi, et ne coute aucune ligne de Java de production.

### 10.2 `MES-CE1-3`, tel qu'il est ecrit, est rouge sur un garde-fou en vigueur

C'est le point central de cet avis, et il se lit dans le code, pas dans une estimation.
`LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest`, clause
`lesTroisPropositionsDUnItemDuDomaineLaissentLeMemeResteApresLecture` (`ADR-0041`), exige que les
trois propositions d'un item **laissent le meme reste** apres lecture de leur valeur - le reste
etant l'unite ecrite. Son propre message d'erreur nomme le cas :

> *"Une unite ecrite ('cm' face a 'm') est le cas le plus courant de reste different, ce n'est pas
> le seul"*

Or `MES-CE1-3` demande un distracteur qui soit **une confusion d'unite** - *"repondre en cm la
valeur attendue en m, ou l'inverse"*. Un item dont la bonne reponse est `3 m` et un distracteur
`300 cm` laisse les restes `{m, cm}` : **rouge**, avec un message qui decrit exactement cet item.

**Ce n'est pas un garde-fou a assouplir.** Sa raison est ecrite et elle est juste : deux
propositions d'unites differentes ne mesurent pas la meme chose, l'ordre qu'on en tire est faux, et
*"une valeur existe, elle est seulement fausse"* - un faux vert. Le precedent le montre en creux :
`cm1-mesures` exprime la faute d'unite comme une **erreur de facteur dans une seule unite**
(`7 cm` -> `70 mm`, distracteurs `700 mm` et `7000 mm`), jamais comme deux unites dans un meme
item. La seconde moitie de `MES-CE1-3` - *"une erreur de conversion par un facteur autre que 100"* -
passe telle quelle ; c'est la premiere moitie qui ne se construit pas sous cette forme.

**Ce que je fais, et ce que je ne fais pas.** Je ne reformule pas `MES-CE1-3` : c'est une borne
pedagogique, elle est a `nadia-prof`. Je nomme l'obstacle avec sa preuve pour qu'il soit arbitre
avant developpement, et non decouvert comme un rouge mecanique au premier `verify`.

### 10.3 `ADR-0097` bloque tout paquet de mathematiques de CE1 neuf

`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` recense **tout** paquet du repertoire de
contenu declarant niveau `CE1` et matiere `Mathematiques`, et rend rouge celui dont la `competence`
declaree n'est pas allouee dans l'enumeration `SousPartieDeMathematiquesCe1`. Polarite `ADR-0039` :
un paquet que le tableau ne nomme pas est **rouge, pas vert**. Le tableau porte aujourd'hui quatre
entrees, **toutes des sous-parties de `MAT-CE1-03`** (additions, soustractions, complements a la
dizaine, moities). Mesure : `mvn -B -pl adapters/adapter-content -am test` sur ce seul test,
**`Tests run: 10, Failures: 0`** - la regle est verte et elle mord.

**Le defaut n'est pas le garde-fou, c'est l'ecart entre son perimetre et sa decision.** Sa
population est *tout paquet de mathematiques de CE1* ; son allocation s'intitule *sous-partie de
`MAT-CE1-03`*. Tant que `MAT-CE1-03` etait la seule ligne CE1 servie, les deux coincidaient. Un
paquet de longueur ou de monnaie fait apparaitre l'ecart : il doit etre alloue dans un tableau dont
le nom et la Javadoc affirment qu'il n'enumere que des sous-parties d'une autre ligne. C'est la
famille de defaut que mon propre mandat me designe - **un perimetre qui echoue par inclusion**.

**Consequence : un `ADR` est du**, et il tient en une decision - *l'allocation declare la
sous-partie de la ligne de referentiel qu'un paquet de mathematiques de CE1 sert, quelle que soit
cette ligne*. Sa regle executable est le meme fichier, dont l'enumeration devient indexee par ligne
plutot que supposee sur une seule. Cet `ADR` n'est **pas** propre a `S39-LONGUEUR` : il est du pour
`S39-MONNAIE` a l'identique, et pour toute ligne CE1 de mathematiques apres elles. **Un seul `ADR`
pour les deux lots** - c'est une question posee deux fois, pas deux questions, exactement comme
`ADR-0097` l'avait etabli pour les trois paquets de s38.

### 10.4 Le rendu de regle graduee - et une nature de manque que l'arbitrage n'a pas tranchee

Je confirme l'absence : **les 27 illustrations servies** (methode : contenu de
`adapters/adapter-web/src/main/resources/static/illustrations/`, comptage du 2026-09-22) sont des
figures planes, deux frises, douze images de monnaie et un plan de classe. Aucune regle graduee.

**Mais la nature du manque n'est pas celle de `S39-DEN`, et c'est ce qu'il faut ne pas confondre.**
`nadia-prof` a fait cette distinction pour `S39-SOLIDES` ; elle vaut ici aussi et personne ne l'a
posee. La mecanique de designation sert deja *"montre l'endroit X sur l'image Y"* : un dessin de
regle graduee dont les graduations sont des `Zone` declarees permet techniquement *"montre le trait
qui marque 7 cm"*. **Ce qui manque est le materiau - un dessin - pas la mecanique.** Il ne s'agit
donc pas d'un chantier de la taille de `DEN-2`, mais d'un lot de contenu illustre, du meme ordre
que `S39-SOLIDES`.

**Ce que je ne dis pas** : qu'une designation de graduation *serve* le verbe "mesurer avec une
regle graduee". C'est un jugement pedagogique et il appartient a `nadia-prof` seule - elle a
explicitement laisse la faisabilite technique de cote, je lui rends donc le constat technique sans
le convertir en verdict. Je note seulement que son classement de cette ligne *"de la meme famille
que l'horloge"* est exact sur le rendu et **inexact sur le cout** : l'horloge exigerait une lecture
d'aiguilles, une regle graduee tient dans une image a zones.

### 10.5 Verdict

Faisable pour la part textuelle, moyennant l'`ADR` de 10.3, et **a la condition que `MES-CE1-3`
soit arbitre avant developpement** (10.2). Ligne plafonnee `en cours` par `nadia-prof`, ce que je
n'ai ni a confirmer ni a contester. Complexite **basse une fois ces deux points leves**, le paquet
lui-meme etant un `cm1-mesures` restreint a `cm`/`m`.

## 11. `S39-MONNAIE` - les centimes sortent le paquet du domaine numerique

### 11.1 Le mecanisme

Paquet statique de type `question`, bandit manchot, aucune image obligatoire - `nadia-prof` ecarte
`MON-1` avec son motif. Aucune ligne de Java de production. Les douze images `monnaie-*.svg` du CP
restent utilisables mais ne sont pas requises ; elles ne sont pas non plus a reutiliser sans
arbitrage, `LIllustrationDeContenuResoutEtAucuneNeDortTest` gardant leur emploi.

### 11.2 `MON-CE1-1` introduit la premiere virgule decimale du corpus servi, et elle a un effet

Mesure : **aucune ecriture decimale a la virgule n'existe dans les 78 paquets servis** (methode :
recherche d'un chiffre suivi d'une virgule puis d'un chiffre dans
`adapters/adapter-content/src/main/resources/contenu/`, 2026-09-22, aucun fichier retourne). Un
paquet portant `3,45 euros` serait donc le premier, et j'ai verifie ce que cela declenche plutot
que de le supposer.

`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.lireValeur` lit le premier nombre d'une
proposition, puis **rend `null` si le reste porte encore un chiffre** (`ADR-0068`). La Javadoc de
cette ligne nomme le cas explicitement : *"une liste, une fraction, un intervalle ou **une ecriture
decimale a la virgule** sont attrapees par la meme ligne"*. Une proposition `3,45 euros` est donc
**hors du domaine numerique**, et le garde-fou de predictibilite du rang ne la juge pas.

**Deux consequences, et la premiere est un rouge.**

1. **Un paquet qui melange des items avec centimes et des items sans est rouge**, par
   `aucunPaquetNEstJugeSurUnePartieDeSesItemsSansQueLeDepotLeDeclare` (`ADR-0041`) : un paquet juge
   sur une partie seulement de ses items doit etre declare dans `PAQUETS_A_ITEMS_ECARTES` avec son
   **nombre exact** d'items ecartes - trois paquets y figurent aujourd'hui. Comme `MON-CE1-1`
   impose des centimes en multiples de 5 et que `MON-CE1-3` impose au moins un tiers d'items de
   rendu, le melange est le cas nominal : **la declaration sera due**, avec son compte mesure sur
   le paquet livre et non recopie. C'est exactement le risque `R1` de la section 6, ici certain
   plutot que possible.
2. **Les items a centimes ne sont gardes par aucun automate de rang.** Leur predictibilite repose
   entierement sur la relecture de `nadia-prof`, comme les 14 items de `cm1-grands-nombres` et les
   18 de `cm1-fractions`. Je l'ecris pour que le taux qui sera cite sur ce paquet porte sa methode,
   et non pour l'excuser.

### 11.3 Un second point, plus discret, sur `MON-CE1-3`

La clause de reste identique (10.2) mord ici aussi, sur les seuls items **sans** centimes : un item
dont la bonne reponse est `2 euros` et un distracteur `50 centimes` laisse les restes
`{euros, centimes}` - rouge. Les trois propositions d'un meme item se tiennent dans une seule
unite. C'est une contrainte de redaction du paquet, sans effet sur les bornes de `nadia-prof`.

### 11.4 Verdict

Faisable, `couvert-valide` techniquement atteignable, moyennant **l'`ADR` de 10.3** - le meme, pas
un second - et sous les trois contraintes de forme ci-dessus. Complexite **basse cote architecture,
moyenne cote redaction du paquet** : c'est le premier paquet du depot a ecrire des decimales, et
trois garde-fous en vigueur reagissent a ce seul fait.

## 12. Ce que cette seconde partie ne dit pas

Elle ne fixe aucune borne, aucun montant, aucun distracteur : `MES-CE1-1` a `3` et `MON-CE1-1` a
`4` sont a `nadia-prof`, y compris la reponse a l'obstacle de 10.2. Elle n'affecte personne et ne
priorise rien (`DEC-0092`, section 5). Elle ne prononce aucun Done, et elle ne dit pas si ces trois
lignes bougeront dans la matrice - un statut suit un Done pedagogique, jamais un avis de
faisabilite.
