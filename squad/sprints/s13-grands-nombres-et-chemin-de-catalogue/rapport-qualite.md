# Rapport qualite - sprint 13 (grands nombres et chemin de catalogue)

- **Auteur** : yanis-qa
- **Date** : 2026-08-17
- **Worktree** : dedie, provisionne depuis `round-two` sur une branche neuve (`s13-yanis-qualite`),
  hors du depot principal ou `relecteur-pedagogique` travaille en parallele. Aucun push.
- **Commit audite** : `a948785`, tete de `round-two` au moment de la saisine.
- **Constat JDK** : `outils/verifier-jdk.sh` donne le meme constat que les sprints precedents sur ce
  poste - le `java` du `PATH` ne compile pas, le fichier de toolchains local est mal orthographie et
  n'est donc pas lu par Maven. Toutes les mesures ci-dessous sont prises avec `JAVA_HOME` pointe sur
  le JDK 21 nomme par ce script pour chaque commande, jamais ecrit dans un fichier commite.

Ce rapport ne recopie aucun chiffre sans l'avoir rejoue sur un build complet, et ne recopie aucun
garde-fou cite comme fermant un trou sans l'avoir casse puis remis en etat sur du materiau reel.

## 1. Porte technique

```
mvn -B clean verify
```

**Resultat : BUILD SUCCESS**, six modules en SUCCESS, execute deux fois (baseline avant mutations,
verification finale apres restauration complete des deux mutations de la section 2) avec un total
identique les deux fois. `git status --porcelain` vide avant la premiere execution et apres la
seconde.

| Module | Tests (agregat Maven, "Results:") |
|---|---|
| domain | 47 |
| application | 7 |
| adapter-web | 229 |
| adapter-content | 120 |
| bootstrap (surefire) | 80 |
| bootstrap (failsafe) | 6 |
| **total** | **489** |

**489 confirme le chiffre annonce dans la saisine**, sur un `mvn -B clean verify` complet - jamais
une mesure incrementale (`-pl` seul sans `-am`).

**28 regles ArchUnit, confirme.** Methode : `git ls-files '*.java'` filtre sur `src/test/java` des
cinq modules du pom agregateur, recherche du motif litteral `ArchRule <nom> =`. Brut : **30**
occurrences. Deux vivent dans le faux depot de contre-exemple de
`LesGardeFousSontMisALEpreuveTest` (`bootstrap/src/test/resources/.../RegleContreExemple.java`),
chemin qui contient la sous-chaine `src/test/java` sans etre un module source compile : **30 - 2 =
28**, meme methode que `theo-archi` en `confirmation-archunit.md` et que les sprints precedents.

**Garde-fou de chemins**, rejoue comme la CI, sur les fichiers suivis Git y compris ce rapport une
fois ajoute a l'index :
```
git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh
  -> "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.", EXIT=0
```

**Tests instables** : aucun constate sur les deux executions completes.

**Un ecart de publication, releve et non corrige par moi** : `confirmation-archunit.md` de ce
sprint est date du 2026-08-15, a l'ouverture (`9430cfd`), et `theo-archi` y ecrivait explicitement
qu'il l'amenderait "a la review, sur l'arbre fusionne, section ajoutee et non reecrite". A la tete
`a948785`, ce fichier compte toujours 143 lignes et un seul commit (`f9c5b57`) : aucune section de
cloture n'y a ete ajoutee. Je ne le corrige pas a sa place - ce n'est pas mon role - mais je le
nomme : le planning de `sofia-sm` faisait de cette republication un critere de cloture explicite.

## 2. Mutation sur le garde-fou neuf le plus lourd : la regle de la voie 4 de `lea-dev`

Regle mise a l'epreuve, appliquee au garde-fou cite par la saisine comme fermant les 29
debordements : `TailleDesTextesEtBoutonsTest.leConteneurSeReplieOuLaColonneGardeUneBaseNonNulle`
(`adapters/adapter-web`). Deux mutations, sur le fichier de production reel
(`static/css/recreo.css`), jamais une fixture ecrite pour l'occasion ; `git status --porcelain`
verifie vide avant la premiere et apres chaque restauration.

| # | Mutation | Resultat | Restaure |
|---|---|---|---|
| 1 | `.propositions` : `flex-wrap: wrap` retire ; `.proposition` : `flex: 1 1 auto` remplace par `flex: 1 1 0` - l'etat exact de la production a `34abbb7`, celui que `nadia-prof` a refuse le 2026-08-16 | **rouge**, 1 echec sur 26 : `[.propositions ne declare pas flex-wrap: wrap et .proposition fixe sa base de flexibilite a une longueur nulle (0) : c'est exactement la forme du defaut refuse par nadia-prof le 2026-08-16 ...]` | oui |
| 2 | couple garde intact (`flex-wrap: wrap` present, base `auto`) ; `white-space: nowrap` ajoute a la regle `.proposition { font-size: 2rem; }` | **vert a tort** - 26/26, aucun echec | oui |

`git diff --stat` vide et `mvn -B -pl adapters/adapter-web -am test -Dtest=TailleDesTextesEtBoutonsTest`
de nouveau a 26/26 apres chaque restauration.

**Ce que la mutation 1 etablit.** Le garde-fou mord bien sur l'etat reel qui a motive son ecriture,
pas seulement sur la fixture de contre-exemple deja presente dans le depot
(`conteneur-rigide-et-colonne-a-base-nulle`, qui porte deja mot pour mot ce meme etat en
commentaire). Rejoue sur le fichier de production lui-meme, la mutation confirme dans l'autre sens
la revendication de la saisine ("`theo-archi` a applique le correctif qui ferme les 29
debordements ... pas un test n'a bouge") : le defaut retire pour de vrai fait rougir pour de vrai.

**Ce que la mutation 2 etablit, et qui repond a la verification 4.** Voir section 5.4.

## 3. Reponse a la question sur le quatrieme axe

La saisine nomme trois axes trouves en jouant (valeur, longueur du texte, largeur de la boite) et
demande s'il en existe un quatrieme, et s'il est enumerable autrement qu'en jouant.

**Il y en a un : la position a l'ecran de la proposition, independante de sa valeur.** Une manche de
`bandit-manchot` place la bonne reponse et ses distracteurs par un tirage de Fisher-Yates
(`BanditManchotSession.melanger`, `domain/src/main/java/com/recreo/domain/mechanics/banditmanchot/BanditManchotSession.java`),
rejoue a chaque manche sur la `RandomSource` injectee. C'est deja nomme en substance dans
`review.md` ("la mecanique melange les propositions a chaque manche, donc le rang affiche n'est pas
une propriete du paquet") - mais nomme comme un raisonnement sur le code, jamais comme une mesure.

**Cet axe se distingue des trois autres sur le point precis que pose la saisine.** Les trois axes
trouves l'ont ete en jouant, parce qu'aucune lecture de code ne les aurait reveles a l'avance : la
longueur du texte et la largeur de boite sont des proprietes de rendu, hors de portee d'un automate
de ce depot (`ADR-0025`). La position, elle, **se lit dans le code** avant de se jouer : un
Fisher-Yates correctement ecrit, applique a chaque manche independamment, ne peut structurellement
pas favoriser un rang d'ecran plutot qu'un autre - ce n'est pas une conjecture de jeu, c'est la
propriete mathematique de l'algorithme, verifiable par lecture.

**Ce que la lecture ne remplace pas.** Aucun test de ce depot ne mesure la distribution empirique
qui resulterait de cette lecture - il n'existe pas d'equivalent, pour la position, de
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (qui mesure la distribution reelle du rang
**de valeur**). Recherche menee sur `domain/src/test/java/.../BanditManchotTest.java` : aucune
methode n'assertionne une distribution de position sur un grand nombre de tirages. La conclusion
"la position ne trahit rien" repose donc aujourd'hui sur une lecture de code juste, pas sur une
mesure - exactement la distinction que ce depot a appris a ne pas confondre (`ADR-0039`, `ADR-0041`)
: un raisonnement correct sur un algorithme n'est pas la meme preuve qu'un comptage sur ses sorties
reelles, et rien n'exclut par exemple un biais dans l'implementation de `RandomSource` elle-meme
(bornes de `nextInt`, qualite du generateur sous-jacent) qu'une lecture du seul `melanger` ne peut
pas voir.

**Reponse a la question posee.** Oui, un quatrieme axe existe (la position), et il repond en partie
"non" a la seconde question : il est enumerable par lecture de code, contrairement aux deux axes de
rendu (texte, boite), qui ne le sont pas et qui n'ont ete trouves qu'en jouant. Mais l'enumeration
par lecture ne suffit pas a elle seule : elle identifie l'axe et donne une raison de le croire sans
danger, elle ne le **mesure** pas. Une cinquieme categorie de propriete de proposition n'est pas
exclue par cette recherche - je ne l'ai cherchee que dans le mecanisme de tirage et le rendu deja
nommes, pas au-dela.

## 4. Verifications sur les mutations et sur les chiffres cites

### 4.1 - Verification 1 : les quatre tests au motif fragile (`marc-dev`)

**Confirme, par mutation reelle.** Quatre classes de test d'`adapter-content` -
`PaquetCm1MesuresTest`, `PaquetCm1PerimetreTest`, `PaquetCpComplements10S9Test`,
`PaquetCpSoustractionsS9Test` - portent chacune une methode de la forme
`leJeuDuCatalogue...ChargeBienLePaquet...`, qui resout l'entree de catalogue par
`.stream().filter(...).findFirst().orElseThrow()`. C'est le meme motif, au nom pres, que celui
retire puis restitue sur `cm1-grands-nombres` (voir 4.2).

Mutation sur le fichier de production reel (`adapters/adapter-content/src/main/resources/catalogue.yaml`) :
entree `bandit-manchot-perimetre-cm1` retiree entierement, simulant un retrait de catalogue du
genre de `DEC-0009`, `DEC-0014`, `DEC-0024` et `DEC-0031`.

```
mvn -B -pl adapters/adapter-content -am test -Dtest=PaquetCm1PerimetreTest
-> Tests run: 8, Failures: 0, Errors: 1
   java.util.NoSuchElementException: No value present
   at PaquetCm1PerimetreTest.jeu(PaquetCm1PerimetreTest.java:197)
   at PaquetCm1PerimetreTest.leJeuDuCatalogueChargeBienLePaquetCm1Perimetre(...:165)
```

Entree restauree, `git status --porcelain` vide, `mvn -B -pl adapters/adapter-content -am test
-Dtest=PaquetCm1PerimetreTest` de nouveau a 8/8.

**Ce que cette mutation etablit.** Le motif rougit bien au retrait de catalogue, mais pas comme un
garde-fou qui nomme sa cause : `NoSuchElementException: No value present` est une erreur
d'execution sans message, jamais un `AssertionError` avec un texte comme celui de
`LeRangDeLaBonneReponse...laDetteNePeutNiGrandirNiPourrir` ("n'existe plus dans ..."). Le precedent
`a9a41aa` (`marc-dev`) confirme le geste attendu devant ce rouge : retirer a la main la methode avec
l'entree, plutot que la transformer. C'est exactement ce geste manuel, repete a chaque retrait de
catalogue et jamais encode, qui a laisse passer l'absence signalee en verification 2 - le fait que
la restitution de l'entree ne restitue pas la methode. **Le motif est donc fragile au sens precis ou
`marc-dev` l'a signale** : il ne casse pas silencieusement (il rougit bien), mais sa reparation
attendue est un geste humain non outille, sur quatre paquets aujourd'hui, identique a celui qui a
deja failli une fois ce sprint.

**Ce que je remonte a `sofia-sm`** : le motif se repete a l'identique sur quatre classes
(`PaquetCm1MesuresTest`, `PaquetCm1PerimetreTest`, `PaquetCpComplements10S9Test`,
`PaquetCpSoustractionsS9Test`) plus la cinquieme deja touchee (`PaquetCm1GrandsNombresTest`). Un
retrait de catalogue sur n'importe lequel des cinq paquets reproduira le meme geste manuel, avec le
meme risque a la restitution. Ce n'est pas a moi de choisir le mecanisme qui le fermerait - c'est une
question d'architecture de test, pour `theo-archi` et `sofia-sm`.

### 4.2 - Verification 2 : le couple retrait-de-catalogue / methode-de-test

**Confirme, par lecture d'historique.** `a9a41aa` (`marc-dev`) retire
`leJeuDuCatalogueChargeBienLePaquetCm1GrandsNombres` avec l'entree de catalogue (`DEC-0031`),
suivant "le meme traitement qu'aux precedents `DEC-0009` et `DEC-0014`". `18a56ad` fusionne le lot
`nadia-prof` qui reinstalle l'entree pour verifier, sur un worktree non fusionne. `a948785`
(`sofia-sm`) restitue l'entree **et** la methode dans le meme commit, en citant explicitement que
"la reinstallation seule ne pouvait pas le reveler" - seule la lecture de `nadia-prof` sur son
worktree l'a fait. Entre les deux, la suite est passee de 488 a 488 : aucun chiffre ne bougeait
pendant que le couple etait rompu.

**Rien dans ce depot ne garde ce couple aujourd'hui.** Ce n'est pas une omission locale a
`cm1-grands-nombres` : c'est la consequence directe du motif de la verification 4.1 - une methode de
liaison catalogue/paquet écrite a la main, retiree a la main, et rien qui la referme
automatiquement au retour de l'entree. Je n'ai pas trouve, et je ne peux pas ecrire a la place de
`theo-archi`, un mecanisme generique qui garantirait ce couplage sans reintroduire l'invariant que
`ADR-0039` a explicitement refuse d'encoder ("l'appartenance au catalogue est une decision produit
trop mouvante pour etre encodee comme invariant dans un test generique", cite par `a9a41aa`) : un
test qui exigerait "chaque paquet du repertoire a une entree de catalogue" romprait a chaque retrait
volontaire, ce qui est le contraire de ce que la doctrine veut. Ce que je peux dire avec certitude :
la seule chose qui a revele l'absence cette fois est une action humaine hors protocole (le
"detour" de `nadia-prof` par un worktree de verification), pas un mecanisme du depot. Si cette
verification independante n'avait pas eu lieu, l'absence serait passee inapercue jusqu'au prochain
incident de contenu. Je remonte le symptome a `sofia-sm`, sans trancher le remede.

### 4.3 - Verification 3 : le chiffre de VR-4 (huit items sous le pli)

**Confirme, sur la donnee brute, pas seulement sur la prose.**
`squad/conformite/releves-s13/releve-verification-voie4-360x640.json`, cle `parJeu.voie-4`, porte
`"jeu": "bandit-manchot-grands-nombres-cm1", "items": 28, "sousLePli": 8` et
`"jeu": "bandit-manchot-perimetre-cm1", "items": 22, "sousLePli": 1`. Les deux chiffres cites en
prose dans `review.md` et `ARBITRAGE-PREALABLE-s13-voie-de-rendu-des-propositions.md` ("huit items
de `cm1-grands-nombres`", "l'item du perimetre que j'avais nomme") correspondent exactement aux deux
seules entrees non nulles du champ `sousLePli` sur les onze jeux de l'etat `voie-4` du releve. Aucun
autre jeu du releve ne porte de valeur non nulle sur ce champ.

### 4.4 - Ce que la regle de la voie 4 laisse ouvert (verification 4)

**Mesure par la mutation 2 de la section 2.** La regle
(`leConteneurSeReplieOuLaColonneGardeUneBaseNonNulle`) ne verifie qu'un couple precis : que
`.propositions` declare `flex-wrap: wrap` **ou** que `.proposition` ne fixe pas sa base de
flexibilite a une longueur nulle. C'est exactement ce que sa propre Javadoc annonce ("elle garde une
cause, pas un effet") et la mutation 2 le confirme sur le fichier de production reel : avec le
couple intact, l'ajout de `white-space: nowrap` sur `.proposition` reste **26/26 vert**, alors que
cette declaration empeche le texte d'un bouton de passer a la ligne et le fait deborder de sa
colonne dans un navigateur reel - un debordement de la meme famille que celui que la regle a ete
ecrite pour fermer.

**Ce que ca laisse ouvert, precisement.** Toute reintroduction d'un debordement qui ne passe pas par
ce couple precis reste invisible : une largeur fixee en valeur absolue (`px`, `ch`) sur `.proposition`
ou son conteneur, un `white-space: nowrap` comme mesure ici, une police agrandie au-dela de ce que la
colonne peut recevoir, ou un changement du `gap`/`padding` qui reduit la largeur utile de la colonne
sans toucher `flex-wrap` ni la base. Aucun de ces chemins n'est couvert, et `ADR-0025` interdit un
moteur de rendu dans ce depot : rien n'automatisera la mesure de largeur qui verrait l'effet tant que
cette limite tient. **Cette propriete reste gardee par une seule personne, en jouant, a 360 px** -
exactement la meme conclusion que `nadia-prof` tire elle-meme dans `review.md` ("aucun garde-fou ne
connait la largeur d'un ecran"), et je la confirme par une mutation plutot que par une lecture de sa
prose.

### 4.5 - Verification 5 : deux chiffres verifies au hasard parmi ceux non corriges

Methode : calcul direct sur le contenu de production reel (YAML charge, pas une transcription a la
main), sans passer par une nouvelle implementation de la lecture de valeur - je compte des
caracteres et des nombres, je ne rejuge pas ce que `lireValeur` fait deja.

**1. "La bonne reponse est la plus courte sur 13 items sur 24" (`cm1-mesures`, `review.md`,
verdict du 2026-08-16 sur la voie 4).** Recompte sur
`adapters/adapter-content/src/main/resources/contenu/cm1-mesures.yaml` : 24 items, pour chacun la
proposition la plus courte en nombre de caracteres comparee a `bonneReponse`. **13 sur 24
correspondent exactement, aucune egalite de longueur entre deux propositions d'un meme item.**
Chiffre confirme au chiffre pres. Le second chiffre cite dans le meme paragraphe ("47,5 %, 57
manches sur 120") est une frequence mesuree en jouant sur un tirage reel, donc dependante du
chemin aleatoire suivi par `nadia-prof` ce jour-la (avec ou sans repetition immediate d'item, ce que
je n'ai pas les moyens de rejouer a l'identique) ; je ne peux ni le confirmer ni l'infirmer par un
calcul statique, et je le dis plutot que de trancher sans les moyens de le faire - il est compatible
avec le ratio 13/24 = 54,2 % dans la marge d'un tirage fini de 120 manches, sans etre egal a
l'esperance.

**2. "90,0 % sur `cm1-perimetre`, mesure sur 20 items, pas 22" (`ADR-0041`, section 4).** Recompte
sur `adapters/adapter-content/src/main/resources/contenu/cm1-perimetre.yaml` : 22 items, dont 2
hors domaine (les deux items conceptuels, sans valeur numerique en tete de proposition). Sur les
**20 items numeriques restants**, rang de la bonne reponse par valeur : **0 fois la plus petite, 18
fois la valeur intermediaire, 2 fois la plus grande**. `18 / 20 = 90,0 %` exactement. Chiffre
confirme au chiffre pres, methode et population identiques a celles que l'`ADR` decrit.

**Reponse a la question posee.** Les deux chiffres verifies tiennent exactement. Je n'ai trouve
aucune huitieme erreur sur cet echantillon de deux, ce qui ne permet pas de conclure que "sept" est
un plafond plutot qu'un plancher - un echantillon de deux sur l'ensemble des chiffres non corriges
du sprint ne prouve l'absence de rien au-dela de lui-meme. Ce que je peux dire : les deux chiffres
que j'ai choisis sans les lire d'abord (sur les deux paquets nommes par la saisine sous un autre
angle, mesures et conversions) sont exacts, calcules directement sur le contenu de production, pas
recopies d'une table.

## 5. Ce que ce rapport ne couvre pas

- Aucun Done pedagogique : il n'est prononce que par `nadia-prof`. Les verdicts cites ici
  (`VALIDE SOUS RESERVE` sur les grands nombres et le perimetre, Done maintenu avec reserve sur les
  mesures) sont lus dans `review.md`, jamais reproduits ou anticipes par moi.
- Aucun jugement sur le niveau scolaire ni la plausibilite pedagogique d'un distracteur.
- La mesure du rendu reel (largeur en pixels, debordement visuel) reste hors de portee d'un automate
  de ce depot (`ADR-0025`) ; je n'ai rejoue aucune mesure au navigateur, seulement des mutations sur
  le CSS et le YAML de production et leur effet sur les tests existants.

## Conclusion

**Vert, constate par moi-meme, sur `round-two` a `a948785`** : `mvn -B clean verify`, BUILD SUCCESS,
**489 tests** (agregat Maven, build complet, rejoue deux fois, aucun ecart), **28 regles ArchUnit**,
garde-fou de chemins en code 0. Deux mutations sur le garde-fou le plus cite du sprint (la regle de
la voie 4) : l'etat reel refuse a `34abbb7` fait rougir le garde-fou, et un debordement reintroduit
par un chemin different du couple qu'il garde le laisse vert - la regle garde une cause, pas un
effet, et c'est mesure, pas suppose. Un quatrieme axe de rang existe (la position a l'ecran), il est
enumerable par lecture de code contrairement aux deux axes de rendu, mais aucune mesure ne verifie
la distribution qu'il produit reellement. Les quatre tests au motif fragile signales par `marc-dev`
sont confirmes par mutation reelle sur `catalogue.yaml` ; le couple retrait/restitution de methode
de test n'est garde par rien d'automatique aujourd'hui, seulement par une verification humaine hors
protocole qui a eu lieu une fois. Le chiffre de VR-4 (huit items) est confirme sur la donnee brute du
releve. Deux chiffres non corriges verifies au hasard tiennent exactement ; l'echantillon est trop
petit pour dire si "sept corrections" est un plancher. Rien de tout cela ne change le Done
pedagogique, qui reste la seule autorite de `nadia-prof`.
