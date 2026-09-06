# Rapport qualite - s29, yanis-qa

- **Mesure sur** : `round-two` a `8ef4fb2` (dernier commit du sprint, `[THEO] s29: ADR-0083`).
- **Date** : 2026-09-06.
- **Worktree dedie** : `../recreo-s29-yanis`, branche `s29-yanis`, lignee verifiee par
  `bash outils/verifier-lignee.sh` ("le worktree sur la branche 's29-yanis' descend de la lignee
  de round-two"). Worktree et branche detruits apres mesure (`git worktree remove --force`,
  `git branch -D`, puis suppression du repertoire residuel) : aucune trace laissee sur le depot
  partage, verifie par `git status --short` sur le worktree principal avant et apres.
- **JDK** : celui que la machine virtuelle des tests rapporte d'elle-meme (banniere de demarrage
  Spring Boot : *"Starting ... using Java 21.0.10"*), pas une entree de toolchain recopiee
  (`ADR-0071`) - **21.0.10**. Enveloppe : `bash outils/mvn-jdk21.sh`.
- Je n'ai touche aucun fichier hors de mon worktree dedie. Le worktree principal est reste sur
  `8ef4fb2`, `git status --short` vide, avant comme apres ma mesure.

---

## Porte technique - commande, resultat, methode de comptage

```
bash outils/mvn-jdk21.sh -B clean verify
```

**Reacteur complet, aucun `-pl`, executee par moi-meme sur `8ef4fb2`.** Resultat : **BUILD
SUCCESS**, les six modules (`recreo`, `recreo-domain`, `recreo-application`, `recreo-adapter-web`,
`recreo-adapter-content`, `recreo-bootstrap`) en `SUCCESS`, tests unitaires et d'integration
(`failsafe`, module `bootstrap`) compris.

**Compte de tests, methode de l'amendement s11** : somme de l'attribut `tests="..."` de chaque
rapport XML sous `target/surefire-reports/` et `target/failsafe-reports/` des cinq modules qui en
produisent, apres `clean`, sur mon propre worktree : **170 fichiers XML, 1262 tests, 0 echec,
0 erreur** (sommes des attributs `failures` et `errors`, egalement a zero). **Aucun ecart** avec
le 1262 annonce par `theo-archi` sur son propre commit, et aucun avec le 1259 qu'il cite comme
entree - je n'ai pas remesure ce dernier chiffre, la demande portant sur la sortie.

**Tests instables constates** : aucun. Une seule execution complete a produit ce resultat ; je
n'ai relance aucun test pour l'obtenir.

**Verdict : porte technique verte**, mesuree par moi sur `8ef4fb2`, reacteur complet. Ceci ne dit
rien du Done pedagogique, qui reste a `nadia-prof` seule : aucune des neuf lignes fermees ce
sprint n'a de Done pedagogique different de ce que ses verdicts (instance A, instance B, FICHE
correspondantes) prononcent deja.

---

## 1. `REV-1` a survecu douze sprints sous une CI verte - la famille, et ce qu'elle recouvre encore

### 1.1 Le defaut et son correctif, verifies et non relus sur parole

`PartieController` composait `"C'est " + reponse + ". Appuie sur " + reponse + "."` au moment de
la revelation ; `retour.html` composait `"Non, c'est " + bonneReponse + "."` a l'ecran de fin de
partie. Sur toute bonne reponse qui n'est pas un groupe nominal, la phrase est agrammaticale
(*"C'est ce n'est pas un etre vivant."*). `ADR-0083` retire la concatenation : les mots invariants
vivent desormais dans `manche.html` et dans `retour.html`, la valeur de contenu voyage seule sous
`.reponse-attendue`, rien ne les reunit.

**Ce que le depot documentait sans le garder, exactement comme la question le demandait** :
`TexteAccentueDesGabaritsTest.java` portait, avant ce sprint, l'entree `"Non, c'est"` dans son
inventaire avec le commentaire *"la reponse suit, posee par le modele"* - une description exacte
du defaut, ecrite en toutes lettres, qui n'a jamais ete traduite en garde-fou. C'est un garde-fou
*existant* qui a arrete `theo-archi` en train de corriger l'autre moitie du defaut, pas une lecture
ni un audit de lot : la lecon est que le depot avait deja la description, et pas le test.

### 1.2 La morsure, verifiee par moi et non relue sur le commit

Le fichier `AucunTexteServiNEnchasseLaReponseDansUnePhraseTest` (module `bootstrap`) contient deja
sa propre mise a l'epreuve interne (`leCadreQuiEnchasseLaReponseFaitRougirCeGardeFouSurDesItemsReels`,
qui reconstruit en memoire le cadre d'avant s29 sur la population reelle et verifie que les griefs
deviennent non vides). Je ne me suis pas contentee de la lire : dans mon worktree dedie, j'ai
**reellement retire le correctif** de `PartieController.java` (remis
`message = "C'est " + reponse + ". Appuie sur " + reponse + "."`, sans `reponseRevelee`) et de
`retour.html` (remis `"Non, c'est " + bonneReponse + "."`), puis relance
`aucunEcranServiNEnchasseLaReponseDansUnePhraseDeLaMecanique` seul :

- **Rouge**, avec des griefs nommant des items reels servis - `bandit-manchot-permis-interdit-cp`,
  `bandit-manchot-fait-opinion-cm1`, `bandit-manchot-droit-devoir-cm1`, entre autres - et le texte
  exact produit par la concatenation retablie (*"C'est un service. Appuie sur un service."*,
  *"C'est un fait. Appuie sur un fait."*).
- Restauration des deux fichiers (`git checkout --`), puis reexecution de la suite complete
  (section porte technique ci-dessus) : **verte**, 1262 tests.

**Verdict : la morsure n'est pas affirmee, je l'ai constatee moi-meme, sur du code de production
reellement degrade et reellement restaure.** Je n'ai pas recompte les 3150 griefs sur 2512 ecrans
que `theo-archi` cite (methode differente : lui sur la population entiere avec budget de tirage,
moi sur un seul essai localise) - ma mesure confirme le signe (rouge puis vert) et la nature des
griefs (des items reels, pas une fixture), pas le chiffre exact.

### 1.3 Le faux rouge que le garde-fou a produit sur lui-meme, et pourquoi il ne s'est pas reproduit

La Javadoc de la classe documente une premiere ecriture du critere (invariance sur l'ecran entier)
qui rougissait a tort sur les 28 items a voix, dont le bloc adulte est conditionnel (`ADR-0075`).
Le critere retenu porte sur l'**emplacement** (classe du parent, balise, classe de l'element) et
non sur l'ecran entier, precisement pour ne pas confondre un bloc qui apparait ou disparait avec
un bloc qui parle autrement. Je n'ai pas reproduit cette regression a la main (cela reviendrait a
recreer une ecriture de test deja abandonnee), mais ma mesure de porte technique - verte sur les
1262 tests, dont les items a voix de `cp-voyelle-du-son` et `cp-mot-dicte` font partie de la
population reelle balayee par ce garde-fou - confirme que ce faux rouge historique ne se
reproduit pas sur la forme actuelle.

### 1.4 Reste-t-il des choses de cette famille - une propriete documentee sans etre gardee ?

Deux points, l'un ferme par ce sprint, l'autre ouvert et disclosed :

- **Ferme** : la meme classe de defaut existait aussi sur l'ecran de saisie depuis s24
  (`MESSAGE_REVELATION`, une constante Java non concatenee mais dont la Javadoc de
  `TexteAccentueDesGabaritsTest` disait deja qu'elle est *"posee par le modele"* et donc hors du
  perimetre de ce gabarit-la). Lecture du code de `AucunTexteServiNEnchasseLaReponseDansUnePhraseTest`
  : sa population couvre **les deux mecaniques** (`ecransDUneQuestion` et
  `ecransDUneReponseAEcrire`), et verifie sur les deux que la reponse est seule dans son element.
  Le trou documente depuis s24 est donc desormais couvert, pas seulement documente.
- **Ouvert et disclosed, pas une omission** : `CONS-1` (la consigne generique passee de 5 a 10
  jeux) est exactement une propriete que `nadia-prof` nomme et qu'aucun garde-fou ne compte -
  `ADR-0083` le dit explicitement (*"Non ecrit de ma propre initiative : son plafond figerait une
  dette que personne n'a decide de traiter"*) et `DEC-0078` tranche pareillement (non bloquant,
  aucun garde-fou cree). Ce n'est pas un vert a tort : personne n'affirme que `CONS-1` est gardee.

**Ce que je route** : rien sur ce point precis - le correctif est verifie par moi, la famille
documentee-sans-etre-gardee qu'on demandait de chercher est refermee sur les deux mecaniques.
Point 4 ci-dessous montre que la famille plus large (*une declaration ecrite contredite par le
contenu qu'elle decrit*) a d'autres membres, non lies a `REV-1`.

---

## 2. Ce que je n'ai pas trouve d'autre garde de la famille "documente mais pas garde" pour `REV-1`

Traite dans la section 1.4 : rien a ajouter ici, la question etait double et j'ai repondu aux deux
volets ensemble pour ne pas dupliquer la preuve.

---

## 3. Les conditions de `nadia-prof` sur les lots de s29 - gardees, affirmees, ou laissees a elle seule

Methode : pour chaque lot, j'ai cherche le fichier de test dedie au paquet (`Paquet...Test.java`),
lu son contenu (pas seulement son nom), et classe chaque condition. Je ne compte comme "gardee"
que ce qui echouerait reellement sur une mutation du contenu ou du code.

### 3.1 Questionner le monde CP - cycle de vie, objet technique (S29-1)

`PaquetCpQlmCycleDeVieTest` et `PaquetCpQlmObjetTechniqueTest` existent et mordent reellement :
`fer1LesCinqCaracteresSontTousRepresentesEnBonneReponse` (et son equivalent objet-technique),
`fer2AuMoinsDixItems`, `fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems` - toutes
**gardees**. `CYC-1` (continuite cycle 2/cycle 3) est verifiee par lecture croisee des deux fichiers
YAML et notee dans les deux commentaires : **non automatisable** (comparaison semantique entre
deux referentiels), disclosed comme telle, coherent avec la nature de la propriete.

### 3.2 Histoire-geographie CM1, composition A (S29-2)

`PaquetCm1HistoireGeographieTest` est etendu et mord reellement sur les trois lignes portees a dix
items : `fer1LesQuatreRealisationsRoyalesSontServiesEnBonneReponse`,
`fer1LesQuatreEvenementsDeLaRevolutionSontServisEnBonneReponse`,
`hgCm114SertLaNatureEtLAuteurDuDocumentEnPlusDeLaDateEtDeLExtraction`,
`fer2EstTenueSurLesTroisLignesDeLaCompositionA`, deux tests `fer3...` - **gardees**.
`hgR3LesAnciensDistracteursAbsurdesOntDisparu` - **gardee**. `HIS-1` (plafond de restitution) vit
dans le test transverse `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest`,
**gardee** au niveau du lot. Deux ecarts sont declares dans le commentaire du paquet et non
masques (un seul type de document sur les sept items neufs, `HG-R4` non entierement tenue sur un
item herite de s28) : je les confirme lus, ils sont honnetement disclosed, pas un vert a tort.

### 3.3 EMC CP et EMC CM1 (S29-4, S29-5, et leurs reprises) - le defaut le plus serieux que j'ai trouve ce sprint

**Aucun des quatre paquets d'EMC livres ce sprint n'a de fichier de test dedie.** J'ai cherche
`Paquet*Test.java` sous `adapters/adapter-content/src/test/java/com/recreo/adapter/content/` pour
`cp-emc-regles-de-vie`, `cp-emc-permis-interdit`, `cm1-emc-fait-opinion`, `cm1-emc-droit-devoir` :
**aucun n'existe**, ni a leur premiere livraison (`5526932`, `ee3ef70`) ni a leur reprise apres
refus (`4dbf9b2`). C'est une rupture avec la pratique du reste du sprint : QLM CP, l'extension
d'histoire-geographie et le calcul pose ont chacun recu ou etendu un fichier de test dans le
**meme commit** que leur contenu ; l'EMC n'en a jamais recu.

Consequence verifiee par lecture : `FER-1` (les deux notions du libelle en bonne reponse), `FER-3`
(deux taches distinctes), `EMC-1`, `EMC-5`, `EMC-8`, `EMC-10`, `HIS-3` et `CYC-1`, toutes
**affirmees dans les commentaires YAML** des quatre paquets, ne sont **gardees par aucun test** -
seulement par les verdicts manuels de `nadia-prof` (`verdicts-nadia-instance-A.md`,
`verdicts-nadia-instance-B` via les quatre `FICHE-bandit-manchot-*.md`). Seul `FER-2` (plancher
d'items) est couvert, et generiquement : `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`
s'applique a tout jeu du catalogue sans connaitre la matiere.

Les garde-fous transverses mordent bel et bien sur ce contenu - `TYP-1` a rougi une fois pendant
S29-5 sur un point-virgule mal espace (`ee3ef70`), et c'est le seul filet qui a reellement arrete
quelque chose sur ces quatre paquets avant l'oeil de `nadia-prof`. Mais aucun n'est specifique a
l'EMC : un item qui perdrait demain sa seconde tache, ou qui reintroduirait une troisieme
proposition "decor" (le defaut que `nadia-prof` a elle-meme trouve et route sur
`cm1-emc-droit-devoir` puis `cm1-emc-fait-opinion`), passerait vert.

**Je route ce point a `sofia-sm`, pour `lea-dev`** : quatre paquets, deux lots, une reprise apres
refus, et toujours aucun test de matiere. C'est le meme defaut que j'avais route a s28 sur
`cp-qlm-corps-sens` (une ligne sans test malgre un contenu modifie), mais il porte ici sur un
sujet entier plutot que sur une ligne.

### 3.4 Calcul pose CM1 - division posee (S29-6, POS-R1 payee deux fois)

`PaquetCm1DivisionPoseeTest` est etendu et mord reellement. `POS-1` et `POS-3` restent
**explicitement documentees comme non calculables** dans la Javadoc (le marqueur textuel est
mordable, sa verite non - coherent avec l'arbitrage de `nadia-prof`). `POS-2`, transposee au
quotient (`leQuotientDesTroisPropositionsALeMemeNombreDeChiffres`), et `POS-4`
(`leDiviseurAUnSeulChiffreEtLaBonneReponseEstLeQuotientEtLeResteExacts`,
`auMoinsUnItemAResteNulEtPlusieursAResteNonNul`) sont **gardees**.

`POS-R1` a ete refusee deux fois puis payee, et j'ai verifie que le second correctif est
**genuinement garde a l'echelle de la serie et non plus de l'item** : j'ai lu
`leRangDeLaBonneReponseParmiLesQuotientsVarieEtAucunRangNEstMajoritaireNiAbsent` - elle classe la
bonne reponse par rang (plus petite / intermediaire / plus grande) parmi les trois quotients tries
et refuse qu'un rang soit absent ou majoritaire sur le paquet. Le premier correctif (couple de
distracteurs fixe, `+10` et `-2`) aurait rendu ce rang **constant** (toujours intermediaire), donc
absent sur les deux autres rangs - ce test l'aurait fait rougir, et le commit le dit explicitement
(*"il aurait rougi sur le fichier precedent"*). Je confirme cette affirmation par lecture du code,
je ne l'ai pas rejouee sur l'ancien fichier (il n'existe plus dans l'arbre).

### 3.5 La dette des paquets a voix (S29-7, S29-8) - disclosed comme non gardee, et c'est exact

`VOI-3`, `VOI-5`, `VOI-8` portent sur ce que l'adulte fait de sa voix. Aucun test ne les garde, et
`PASSE-TRANSVERSE-s29-les-proprietes-de-portee-depot.md` le dit sans detour (*"personne ne le
garde, pas meme moi"*). Ce que le lot corrige, et que j'ai verifie par lecture des six fichiers
YAML, c'est que le produit ne demande plus par ecrit un geste que ces conditions interdisent par
ailleurs. Ce n'est pas un vert a tort : rien n'affirme que `VOI-3/5/8` sont automatisees.

### 3.6 T4, la taille d'image de designation (`ines-design`)

Changement CSS seul, candidat (au sens ou `ines-design` l'emploie), non cable a un contenu servi
(`ZON-7` : `adapter-web` ne porte encore aucun paquet de designation). Aucun test n'etait attendu,
aucun n'a ete ajoute. Coherent, rien a router.

---

## 4. `FOP-R2` et sa famille - des declarations dans des fichiers commites que le contenu dement

### 4.1 `FOP-R2` confirmee par diff direct, pas par relecture du verdict de `nadia-prof`

`cm1-emc-fait-opinion.yaml` porte, dans son commentaire de correction (`4dbf9b2`) : *"Les dix
distracteurs sont reecrits pour dire une methode de verification reellement tentee mais
insuffisante [...], jamais l'absence de verification en toutes lettres."* J'ai compare
directement les dix distracteurs des cinq items de verification entre `ee3ef70` (avant) et
`4dbf9b2` (apres) : **deux d'entre eux sont byte pour byte identiques** avant et apres -
`"en la croyant parce que Malo l'a dit une seule fois"` (item 4) et
`"en imaginant une explication supplementaire"` (item 8). Un distracteur n'est pas "reecrit" s'il
n'a pas change d'un caractere. La declaration est donc fausse au sens strict qu'elle porte sur un
sous-ensemble plus large que ce que le diff montre.

### 4.2 Une seconde instance, trouvee en verifiant le paquet jumeau du meme jour

`cp-emc-permis-interdit.yaml` porte, dans son commentaire de correction (meme reprise, `4dbf9b2`) :
*"Tous les enonces sont reecrits en une seule phrase de situation plus une question courte."*
Diff direct entre `5526932` (avant) et `4dbf9b2` (apres) sur les onze enonces : **deux d'entre eux
sont byte pour byte identiques** avant et apres - *"Pourquoi est-il interdit de se moquer d'un
camarade ?"* (item 2) et *"Pourquoi est-il interdit d'insulter un camarade ?"* (item 4). Ces deux
enonces ne sont ni reecrits, ni meme de la forme annoncee (*"une phrase de situation plus une
question courte"*) : ce sont des questions directes, sans situation. **"Tous" est faux pour deux
enonces sur onze.**

Le reste de la declaration - profil de longueur mediane 10, maximum 14 - est en revanche exact :
je l'ai recalcule moi-meme par decompte de mots sur les onze enonces actuels (mediane 10, maximum
14, methode : `str.split()` sur chaque enonce, meme convention que celle visiblement utilisee dans
le paquet). Je ne generalise donc pas "la declaration est fausse" a l'ensemble du commentaire :
**le chiffre agrege est exact, l'enumeration ("tous", "dix") ne l'est pas.**

### 4.3 Ce que j'ai verifie ailleurs et qui tient

J'ai controle les comptes d'items des neuf paquets touches ou crees ce sprint contre leur
commentaire (`FER-2`, DEC-0064) par `grep -c "^  - enonce:"` : `cp-emc-permis-interdit` (11),
`cp-emc-regles-de-vie` (11), `cm1-emc-droit-devoir` (11), `cm1-emc-fait-opinion` (11),
`cp-qlm-cycle-de-vie` (11), `cp-qlm-objet-technique` (11), `cm1-anglais` (20),
`cm1-histoire-geographie` (57), `cm1-division-posee` (11) - **tous exacts**. Je n'ai pas balaye
l'integralite des 54 paquets pour ce type de declaration, seulement ceux touches par s29 ; un
defaut de meme famille peut subsister ailleurs dans le depot, non cherche par cette passe.

**Je route ces deux cas a `sofia-sm`, pour `lea-dev`** : une declaration "tous"/"dix" dans un
commentaire commite qui n'est vraie que pour huit ou neuf elements sur onze ou dix n'est pas une
question pedagogique - c'est une affirmation factuelle inexacte dans un fichier que l'equipe
relit comme source de verite sur ce qui a change.

---

## 5. Le cliquet de `HIS-4` - toujours a zero

`ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` porte encore
`DETTE_DE_DECLARATION = Set.of()` et `PLAFOND_DE_DETTE = 0` a `8ef4fb2` - verifie par lecture
directe des deux champs, inchanges depuis s28. J'ai controle que les neuf paquets touches ou crees
ce sprint (les quatre EMC, les deux QLM, `cm1-histoire-geographie`, `cm1-anglais`,
`cm1-division-posee`) declarent tous `lignesDeReferentiel` (`grep -c "^lignesDeReferentiel:"`,
un par fichier), et ma mesure de porte technique fait tourner ce test en vert sur le reacteur
complet - si un paquet servi avait manque sa declaration, le test aurait echoue sur mon execution.

**Verdict : cliquet a zero, tenu par le sprint.**

---

## 6. La couture entre les deux instances - le compte de lignes, et ce que j'ai cherche au-dela

`camille-po` a trouve que les deux instances annoncaient chacune huit lignes fermees (51/138 pour
l'instance A qui citait les trois de B, 3/138 pour B) sans qu'aucune ne totalise neuf : `EMC-CP-06`
fermait des le premier passage de l'instance B, avec Done prononce et note ecrite dans sa fiche,
mais l'instance A la citait sans l'ajouter a son propre total. J'ai lu `b93ef31` et confirme
l'arithmetique qu'il annonce : `52 + 7 + 79 = 138`, le denominateur retombe juste, et les neuf
lignes visees par `DEC-0077` ferment toutes les neuf. Je n'ai pas recompte les dix-huit tableaux
ligne par ligne moi-meme - ce recomptage est le travail que `camille-po` vient de faire, et le
refaire integralement sort du perimetre "porte technique" de ce rapport.

**Ce que j'ai cherche, au sens du mode de defaillance que `nadia-prof` avait nomme d'avance
("silencieux", "personne ne porte la somme")** : un point dont ni l'une ni l'autre instance, ni le
developpeur, n'avait la charge explicite. J'en ai trouve un candidat serieux, independant du
compte de lignes : **l'absence de test de matiere sur les quatre paquets d'EMC (section 3.3)**.
Ni `nadia-prof` (dont le mandat est pedagogique, pas la couverture de test) ni `camille-po` (dont
le recompte porte sur la matrice, pas sur les fichiers de test) n'avaient ce controle dans leur
perimetre declare ; les deux developpeurs qui ont livre puis repris ces quatre paquets ne l'ont
pas fait non plus, sur deux commits chacun. Ce n'est pas la meme couture que celle de la matrice
(un chiffre que personne ne somme), mais c'est la meme famille : **une verification que personne
n'avait explicitement pour tache, et qui ne rougit pas parce qu'elle n'existe pas.**

**Point mineur trouve en cherchant la couture, sans lien avec les tests** : `docs/backlog/backlog.md`
porte encore, a `b93ef31` (l'avant-dernier commit du sprint), une story "a ecrire pour s30" sur
`REV-1` avec des criteres d'acceptation presentes comme restant a satisfaire - alors que le commit
suivant et dernier du sprint (`8ef4fb2`) livre deja le correctif et dit *"il est livre et
jouable"*. Ce n'est pas un defaut de la porte technique ni du Done pedagogique : c'est un artefact
de sequence (la story a ete ecrite avant le correctif, dans le meme sprint), a reconcilier par
`sofia-sm`/`camille-po` avant le planning de s30 pour que la story ne soit pas instruite comme un
travail technique restant alors que seule la reverification a l'ecran de `nadia-prof` (chiffree a
0,25, deja prevue) reste ouverte.

---

## Ce que je route

- **A `sofia-sm`, pour `lea-dev`** : les quatre paquets d'EMC (`cp-emc-regles-de-vie`,
  `cp-emc-permis-interdit`, `cm1-emc-fait-opinion`, `cm1-emc-droit-devoir`) n'ont recu aucun
  fichier de test de matiere, ni a leur livraison ni a leur reprise apres refus. `FER-1`, `FER-3`,
  `EMC-1`, `EMC-5`, `EMC-8`, `EMC-10`, `HIS-3`, `CYC-1` y sont affirmees en commentaire et jugees
  uniquement par `nadia-prof` a l'oeil (section 3.3).
- **A `sofia-sm`, pour `lea-dev`** : deux declarations inexactes dans des commentaires YAML
  commites - `cm1-emc-fait-opinion.yaml` ("dix distracteurs reecrits", deux sont inchanges) et
  `cp-emc-permis-interdit.yaml` ("tous les enonces reecrits", deux sont inchanges) (section 4).
- **A `sofia-sm`/`camille-po`** : `docs/backlog/backlog.md` presente encore la story `REV-1` comme
  un travail technique a faire pour s30 alors que le correctif est livre depuis le dernier commit
  du sprint - a reconcilier avant le planning de s30 (section 6).
- **Rien d'autre sur la porte elle-meme** : elle est verte, mesuree par moi sur `8ef4fb2`,
  reacteur complet, 1262 tests, 0 echec, 0 erreur, et la morsure d'`ADR-0083` est verifiee par moi
  et non relue sur le commit qui l'annonce.

Je ne tranche ni la forme des correctifs ci-dessus (architecture, `theo-archi`), ni si les lots
d'EMC, de questionner le monde ou d'histoire-geographie sont pedagogiquement prets - cela reste a
`nadia-prof` seule, en jouant.
