# s57 - palier 5 : avis de theo-archi sur la forme des gardes de S57-BR-QLM-CE1-10 et -12 (G10-1 a G10-7, G12-1 a G12-7)

- **Auteur** : `theo-archi`. Date : 2026-10-26 17:35 (UTC+01:00), lue par `date`.
- **Arbre** : worktree `s57-p5-theo-g1012`, base `round-two` a `eaac43a6` (`git rev-parse`). Aucun paquet `ce1-qlm-paysages` ni `ce1-qlm-generations` n'existe a cette base ; trois paquets `ce1-qlm-*` existent (`-01`, `-02`, `-03`).
- **Demande** : `arbitrage-nadia-premiers-jets-ce1-qlm-10-12.md`, sections 1.2 et 2.2 (gardes et sondes), regles `R10-1` a `R10-8` et `R12-1` a `R12-9` ; `T10-1`, `T12-1`, `T12-2` de l'annotation du 2026-10-26, septieme (`docs/backlog/backlog.md`, sections 7 et 8).
- **Lu** : cet arbitrage en entier ; les stories `S57-BR-QLM-CE1-10` et `-12` et l'annotation septieme ; `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest.java`, `PaquetLu.java`, `PaquetCe1QlmEtapesDeDeveloppementTest.java`, `PaquetCe1QlmCeQueMangentLesAnimauxTest.java` en entier, `PaquetCe1QlmBesoinsDeLaPlanteTest.java` (lignes 1 a 330) ; l'en-tete et l'entree `ce1-qlm-*` de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java` ; les moments servis par `cp-frise-de-la-journee.yaml` ; mes avis `avis-theo-palier-5-garde-fer-3-ce1-qlm-02.md` et `avis-theo-palier-5-garde-rotation-ce1-qlm-01.md`.
- **Ce que ce fichier ne fait pas** : il n'ecrit ni test, ni jeu fautif, ni contenu ; il ne cree aucun identifiant de condition et ne prononce aucun Done. Les codes `C1`, `M1`, `A1`... sont des reperes de lecture de ce fichier. Aucun `mvn verify` lance.

## 0. Verdict

| Code | Question | Reponse |
|---|---|---|
| `V1` | Executable ? | **Les quatorze gardes sont executables**, aucune ne s'ecrit *"non garde"*. Chacune laisse des proprietes a `nadia-prof` (sections 1.9 et 2.9) |
| `V2` | Lieu | **Le test du paquet** de chaque lot (`PaquetCe1QlmPaysagesTest`, `PaquetCe1QlmGenerationsTest`, noms a suivre de ceux que `marc-dev` donne aux paquets). Aucune garde dans le test transverse : la mesure (section 3) montre trois faux rouges qu'elles y feraient |
| `V3` | Reconnaissance | **Egalite** apres normalisation sur des tables fermees (elements, paysages, generations, evenements), jamais une sous-chaine ; recherche de mot borne par `\p{L}` pour les mots interdits |
| `V4` | Tache | Une fonction de la **seule bonne reponse**, comme `-02` (`R10-7`, `R12-2`) ; aucune declaration de tache par item ; deux lignes de compte en en-tete, relues contre les items |
| `V5` | Rotation de `-01` sur `-12` | **Inapplicable**, `camille-po` a raison (section 2.8) ; `G12-3` est la garde de distracteur d'ordre que `T12-2` disait absente |
| `V6` | Mesure | 190 fichiers, 3 257 items, 15 889 champs : aucun motif ne mord sur un paquet `ce1-qlm-*` existant sauf *vacances* dans `-03`, hors de la portee du test de `-10` ; JDK 21.0.10 rapporte par la machine virtuelle (section 3) |
| `V7` | Conflits avec les regles de `nadia-prof` | Six, en section 4 ; le principal : la sonde *doit passer* de `G12-3` n'est vraie que pour deux elements **voisins** (`C1`) |
| `V8` | ArchUnit, ADR | Aucune regle neuve, aucun `ADR` : gardes de contenu sur du YAML, dans la forme d'`ADR-0013` (jeu fautif commite) et des precedents `-01`, `-02` |

## 1. Lot `-10` (`ce1-qlm-paysages`)

### 1.1 Ce que le test tient, ce que le paquet porte

**Tables dans le test, pas lues dans l'en-tete.** Les deux listes de `R10-1` et la table de `R10-3` sont des constantes du test, source `arbitrage-nadia-premiers-jets-ce1-qlm-10-12.md` section 1 (`R10-8`), comme `ESPECES` a `-01`. Les lire dans l'en-tete du paquet laisserait le developpeur etendre la liste en editant un commentaire, et la garde suivrait. Ici un element neuf est un diff du test, visible en revue.

- `PROPRE` : *plage*, *mer*, *phare* -> littoral ; *sommet*, *piste de ski* -> montagne ; *champ*, *ble* -> campagne ; *immeuble*, *metro* -> ville.
- `PARTAGE` : *foret* {L, M, C} ; *riviere* {M, C, V} ; *neige* {M, C, V} ; *port* {L, V} ; *village* {L, M, C} ; *falaise* {L, M} ; *ferme* {M, C} ; *rue* {L, M, C, V}.
- `FORME_ELEMENT` : chaque forme ecrite vers son element, **singulier et pluriel enumeres** (*plages*, *pistes de ski*, *bles*, *metros*...), jamais un `s` retire a l'aveugle.
- `FORME_PAYSAGE` : *littoral*, *montagne(s)*, *campagne*, *ville(s)*.
- `TABLE_B` : la table de `R10-3`, quatorze couples (bonne reponse, paysage nomme). Verifie par le programme de la section 3 : **chacun des quatorze couples admet au moins un propre du paysage nomme et au moins un partage commun**, donc la regle des distracteurs ne rend aucun couple de la table impossible a ecrire.

**Ce que le paquet porte** : deux lignes de compte dans l'en-tete, au format de `-02`, qui nomment les taches de `R10-7` :

```
#   paysage : identifier le paysage a partir de ses elements (N items)
#   element : l'element qui n'appartient pas a un paysage nomme (N items)
```

relues par `^#\s+(paysage|element) : (identifier le paysage a partir de ses elements|l'element qui n'appartient pas a un paysage nomme) \((\d+) items?\)` sur la ligne mise sans accent. Le nom de la tache est donc ecrit, et le compte est verifie, jamais cru.

Chaque enonce a l'une de deux formes fermees, lues sur le texte normalise (1.2) :

- **A** : `^(?:on|\p{L}+) voit (?<liste>.+?)\. (quel est ce paysage|dans quel paysage est-on) \?$`, la liste coupee sur `, ` et ` et ` ;
- **B** : `^lequel ne voit-on pas (a la montagne|a la campagne|en ville|sur le littoral) \?$`, lieu vers paysage par une table de quatre lignes.

Une forme neuve entre comme une ligne, ecrite par `marc-dev` en le disant. *"Au bord de la mer"* n'est pas un lieu admis : il nomme *la mer*, et `R10-3` veut l'enonce *"sans autre element"*.

**Tache** (`R10-7`) : bonne reponse egale a un paysage, tache `paysage` ; egale a un element, tache `element` ; sinon refus `G10-1`. La forme de l'enonce doit etre celle de sa tache.

### 1.2 Normalisation

| Usage | Etapes |
|---|---|
| Proposition, segment de liste, lieu | `PaquetLu.sansAccent` (casse, accents, oe) ; `U+2019` vers apostrophe droite ; toute suite de blancs, `U+00A0` et `U+202F` compris, vers une espace, puis `strip` ; `.` ou `!` final retire ; **un** determinant de tete retire sur une liste fermee (`de la `, `de l'`, `un `, `une `, `des `, `les `, `le `, `la `, `l'`, `du `, `d'`, `de `) ; puis egalite dans `FORME_ELEMENT` ou `FORME_PAYSAGE` |
| Forme de l'enonce | les trois premieres etapes, ponctuation gardee : l'insecable de `TYP-1` avant `?` devient une espace |
| `G10-4` (indice) | forme de l'enonce, puis recherche bornee `(?<!\p{L})(formes)(?!\p{L})` des formes interdites |
| `G10-5`, `G10-6` | **minuscule avec accents** (`toLowerCase(Locale.FRENCH)`), apostrophe et blancs normalises, recherche bornee par `\p{L}`. **Jamais `sansAccent`** : la mesure (section 3) trouve 28 champs reels ou *"a duré"* deviendrait *"a dure"* |

### 1.3 Les gardes

| Garde | Forme | Refuse (contre-exemple) | Passe | Ne garde pas |
|---|---|---|---|---|
| `G10-1` | Bonne reponse, distracteurs, chaque segment de la liste de A, lieu de B : egalite dans les tables, sinon refus. La forme fermee de l'enonce rend le reste de l'enonce constant | *"On voit une cascade et un sommet. Quel est ce paysage ?"* ; un distracteur *"le désert"* | *"On voit une forêt et des champs."* | un element hors liste dans l'**indice** (nadia ne le demande pas sur l'indice) ; voir `C4` pour *un grand champ* |
| `G10-2` | Sur la liste de A : au moins deux elements distincts ; au moins un `PROPRE` du paysage de la bonne reponse ; aucun `PROPRE` d'un autre paysage ; **pour chaque** `PARTAGE` nomme, au moins un distracteur dans ses paysages ; les trois propositions sont trois des quatre paysages | *"On voit un sommet."* ; *"On voit une forêt et une rivière."* ; *"On voit une plage et des immeubles."* ; **le refus qui mord** : *"On voit un sommet et une falaise."*, bonne *la montagne*, distracteurs *la campagne*, *la ville* (aucun n'est un paysage de *falaise*) | *"On voit un sommet et une forêt."*, *la montagne* ; *la campagne*, *la ville* | que le partage nomme rende vraiment le distracteur tentant |
| `G10-3` | Sur B : `TABLE_B[bonne]` contient le paysage nomme ; parmi les deux distracteurs, un `PROPRE` du paysage nomme et un `PARTAGE` qui contient le paysage nomme **et** celui de la bonne reponse | *la ferme* a la montagne ; *la plage* en ville ; *la mer* a la montagne avec *le sommet*, *la rivière* (la riviere n'est pas au littoral) ; *la mer* avec *le sommet*, *les pistes de ski* (aucun partage) | *la mer* a la montagne, *le sommet*, *la forêt* | rien de plus sur B : la table et la liste ferment l'exactitude que la garde peut lire |
| `G10-4` | Indice, forme de 1.2. A : aucune forme du paysage de la bonne reponse ni d'un `PROPRE` de ce paysage. B : aucune forme d'un element des trois propositions | A littoral, *"Pense à la mer et au sable."* ; B, *"Pense aux forêts."* (le test transverse cherche *"la forêt"* tel quel et laisse passer *forêts*) | *"Cherche le mot qui ne va qu'avec un seul paysage."* | la periphrase par un mot hors liste (*le sable*, *les vagues*) ; qu'un indice soit une methode |
| `G10-5` | `JUGEMENT` : les dix-sept mots en **formes enumerees**, 48 formes : *beau, bel, belle, beaux, belles, joli(e)(s), laid(e)(s), calme(s), tranquille(s), bruyant(e)(s), dur(e)(s), facile(s), pauvre(s), riche(s), triste(s), sale(s), dangereux, dangereuse(s), ennuyeux, ennuyeuse(s), mieux, meilleur(e)(s), pire(s)*. Huit mots n'ont pas de feminin distinct ; *mieux* est invariable. Enonce, indice, propositions | chaque forme, une par champ du jeu fautif ; *"Plus joli"* ; *le calme* (nom, vise par *"et toute autre"*) | *une grande ville*, *un grand champ* ; *beaucoup*, *durent* (tuent la recherche sans borne) ; *"La neige a duré tout l'hiver."* (proposee contre la recherche sans accent ; non lancee, l'effet est mesure sur 28 champs reels, section 3) | tout jugement hors des dix-sept mots (*agréable*, *moche*, *magnifique*) |
| `G10-6` | `(?<!\p{L})(tu\|toi\|ton\|ta\|tes\|vacances?)(?!\p{L})`, enonce, indice, propositions | *"Dans quel paysage habites-tu ?"* (le `-` de `-tu` n'est pas une lettre) ; *"Pense à tes vacances."* ; *"Pendant les vacances, regarde."* | *Cherche*, *Regarde* ; **la montagne** et **les pistes de ski** (portent *ta* et *tes* en sous-chaine : tuent la recherche sans borne) | *vous*, *votre*, *te*, *t'* (hors de sa liste) ; un lieu reel (`R10-6`) |
| `G10-7` | Calcul sur les items, puis comparaison a l'en-tete, dans cet ordre (forme de `-02`) : deux taches ; `element` au moins 3 ; chacun des quatre paysages bonne reponse d'au moins un item `paysage` (donc A au moins 4) ; comptes de l'en-tete egaux aux comptes calcules | paquet reel restreint a ses items `paysage`, en-tete inchange (*"deux tâches"*) ; items `paysage` plus deux `element` (*"la moins servie"*) ; paquet reel prive des items dont la bonne reponse est *la ville* (`FER-1`, *"ville"*) ; en-tete au compte change (*"comptes"*) ; paquet vide | le paquet reel | l'operation mentale de chaque tache (`FER-3`) |

Les refus `G10-7` partent du paquet reel, en-tete inchange : c'est ce qui tue une garde qui lirait l'en-tete au lieu des items (`avis-theo-palier-5-garde-fer-3-ce1-qlm-02.md`, section 3). Les autres sondes vivent dans `contreexemple/PaquetCe1QlmPaysagesTest/contenu/`, reperees par `sonde NN` en commentaire, comme `-02`.

### 1.4 Le test transverse, deja la, et ce qu'il fait a `-10`

- `TRV-CE1-5` refuse un indice qui contient une proposition **telle qu'ecrite**. Dans un item A ou *le littoral* est une proposition, un indice qui ecrit *le littoral* pour l'expliquer (`QMT-CE1-1`) est refuse par le transverse. L'explication vit donc dans un item ou *le littoral* n'est pas une proposition (A sans lui, ou B *sur le littoral*). C'est compatible avec `R10-4`, je le signale pour que `marc-dev` ne le decouvre pas a `verify`.
- `QC10` : `ce1-qlm-paysages` entre dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` avec son motif, comme les trois paquets existants.

## 2. Lot `-12` (`ce1-qlm-generations`)

### 2.1 Ce que le test tient, ce que le paquet porte

- `GENERATIONS`, rang du plus age au plus jeune : *arriere-grands-parents* 0, *grands-parents* 1, *parents* 2, *enfants* 3.
- `EVENEMENTS` (`R12-1`), rang : *naissance* 0, *entree a l'ecole* 1, *entree au college* 2, *premier emploi* 3, *naissance de son enfant* 3. **Le rang egal des deux derniers est `R12-6` ecrit en donnee** : aucun ordre n'est lu entre eux.
- `VOISINS_ADMIS` : les quatre questions de `R12-6`, en triplets (reference, sens, bonne reponse) : (*naissance*, apres, *entree a l'ecole*) ; (*entree a l'ecole*, avant, *naissance*) ; (*entree a l'ecole*, apres, *entree au college*) ; (*entree au college*, avant, *entree a l'ecole*).
- `SUITE_ADMISE` : *naissance, entree a l'ecole, premier emploi*.
- **Determinants de tete**, liste fermee : `celle des `, `la generation des `, `les `, `la `, `le `, `l'`, `sa `, `son `.
- **Prenoms de l'enonce** : les mots `(?<!\p{L})\p{Lu}\p{Ll}+(?!\p{L})` de l'enonce brut. Le mot de tete d'une phrase (*Pour*) y entre aussi ; une proposition *Pour* n'a pas de sens, l'effet est nul.
- **Groupe d'une proposition** : `gen` si sa forme normalisee est une generation, `evt` si c'est un evenement, `pre` si elle est egale a un prenom de l'enonce, sinon rien.

**Tache** (`R12-2`) : bonne reponse coupee sur `,` en trois parties, tache `ordre` ; en une partie, tache `voisin` ; autrement, refus. En-tete :

```
#   voisin : une generation ou un evenement juste avant ou juste apres (N items)
#   ordre : trois generations ou trois evenements dans l'ordre (N items)
```

relu par `^#\s+(voisin|ordre) : .*\((\d+) items?`, comme `-02`.

### 2.2 Normalisation

| Usage | Etapes |
|---|---|
| Proposition, partie d'une suite, reference de l'enonce | comme 1.2 (sans accent, `U+2019`, `U+00A0`, `U+202F`, ponctuation finale), puis le determinant de 2.1, puis egalite dans les tables ; prenom par egalite brute apres `strip` |
| Formes d'enonce, sens, `G12-1`, `G12-5` (*ans*), `G12-6` | texte **sans accent** (`plat`) : les mots cherches portent des accents que l'auteur peut omettre (*dîner*, *goûter*, *âgé*) ; aucune collision constatee (section 3) |
| `G12-5` (chiffre) | texte brut, `\p{Nd}` |

### 2.3 Les gardes

| Garde | Forme | Refuse (contre-exemple) | Passe | Ne garde pas |
|---|---|---|---|---|
| `G12-1` | `(?<!\p{L})(reveils?\|lever\|petit-dejeuners?\|dejeuners?\|ecole du matin\|ecole de l'apres-midi\|gouters?\|diners?\|coucher\|pyjamas?\|brosser les dents\|autrefois)(?!\p{L})` sur tout champ lu. **La liste est celle des dix moments que `cp-frise-de-la-journee.yaml` sert**, plus *reveil* de la sonde : voir `C5` | *"réveil, déjeuner, coucher"* ; chaque moment, un par champ ; *"Autrefois, que faisaient les grands-parents ?"* | *"naissance, entrée à l'école, premier emploi"* ; *école* seul | les verbes conjugues (*se réveille*, *se couche*), *matin*, *soir*, *récréation* ; ce qu'une generation faisait *jadis* ou *avant* |
| `G12-2` | Item `voisin` : chaque proposition a un groupe ; les trois ont le meme groupe ; l'enonce contient *juste avant*, *juste apres* ou *de quelle generation* (`R12-2`, ajout tire de son texte, `A6`) ; sous *juste avant/apres X*, X a un groupe et aucune proposition n'a le groupe et la forme de X. Item `ordre` : chaque partie a un groupe, les trois parties le meme | *le mariage* en proposition ; *celle des enfants* et *l'entrée à l'école* ensemble ; *sa naissance* sous *"juste après sa naissance"* ; *la grand-mère* (voir `C3`) | *"Sara est la maman de Lina. Ali est le papa de Sara. Pour Lina, de quelle génération est Ali ?"*, *celle des grands-parents* ; *celle des parents*, *celle des arrière-grands-parents* | l'exactitude de la bonne reponse d'un item *de quelle generation* (elle depend des relations ecrites) ; le sens de *avant* pour une generation, que je ne tranche pas |
| `G12-3` | Item `ordre` : l'enonce contient *du plus age au plus jeune* ou *du premier au dernier* ; chaque distracteur est les trois memes elements, dans un autre ordre, **et n'est pas la bonne suite renversee**. Ajouts tires de `N12-2` (`A6`) : le sens va avec le groupe (`gen`, `pre` : *plus age* ; `evt` : *premier*) ; une suite `gen` est en rang croissant ; une suite `evt` est `SUITE_ADMISE` | *"enfants, parents, grands-parents"* sous la bonne *"grands-parents, parents, enfants"* ; *"Range ces générations dans l'ordre."* ; *"Lina, Sara, Ali"* sous *"Ali, Sara, Lina"* (la garde est structurelle, elle mord aussi sur des prenoms) | *"parents, grands-parents, enfants"* et *"grands-parents, enfants, parents"* (echanges de deux **voisins**) ; *"parents, enfants, grands-parents"* (une rotation, admise sur un ordre lineaire) | que l'ordre des prenoms soit le seul que les relations donnent ; la plausibilite des quatre ordres admis |
| `G12-4` | Item `voisin` d'evenements : le triplet (reference, sens, bonne reponse) est dans `VOISINS_ADMIS`. Item `ordre` d'evenements : `SUITE_ADMISE` (deja dans `G12-3`). Un distracteur etant une permutation de la bonne suite, aucune suite ne peut porter *premier emploi* et *naissance de son enfant* | *"juste après le premier emploi"* ; *"juste avant la naissance de son enfant"* ; *"juste après l'entrée au collège"* ; *"juste après l'entrée à l'école"*, bonne *le premier emploi* ; la suite *"naissance, premier emploi, naissance de son enfant"* | les quatre questions de `R12-6`, chacune avec sa bonne reponse | la garde **calcule** aussi la bonne reponse des items d'evenements, puisque le triplet la contient ; pour les generations, rien de tel (colonne `G12-2`) |
| `G12-5` | `\p{Nd}` sur le texte brut ; `(?<!\p{L})ans(?!\p{L})` sur le texte sans accent ; tout champ lu | *"Lina a 7 ans."* ; *"Lina a sept ans."* | *"Dans la vie de Sara, ..."* (porte *ans* en sous-chaine : tue la recherche sans borne) | un age sans *ans* (*"Ali a soixante"*), *an*, *année* : `R12-4` dit *"aucune annee"*, `G12-5` ne la demande pas ; lu par elle |
| `G12-6` | `(?<!\p{L})(vit\|vivent) avec(?!\p{L})` ; `(?<!\p{L})(tu\|toi)(?!\p{L})` ; `(?<!\p{L})(ton\|ta\|tes) (?:\p{L}+ )?(PARENTE)(?!\p{L})`, `PARENTE` liste fermee : *maman, papa, mere, pere, parent, grand-mere, grand-pere, grands-parents, arriere-grands-parents, frere, soeur, famille, enfant, oncle, tante, cousin, cousine, fils, fille, bebe*, pluriels ; un mot permis entre les deux (*ton petit frère*) ; tout champ lu | *"Qui vit avec Lina ?"* ; *"Les parents de Lina vivent avec qui ?"* ; *"Qui vit avec toi à la maison ?"* ; *"Pense à tes grands-parents."* ; *"Pense à ta propre maman."* | *"Pour Lina, de quelle génération est Ali ?"* ; *"Compte dans ta tête les générations."* (borne de *suivi d'un mot de parente*) | *habite avec*, *vous*, un mot de parente hors `PARENTE` ; qu'aucune configuration familiale ne soit la norme |
| `G12-7` | Comme `G10-7` : deux taches, la moins servie au moins 3 ; *enfants*, *parents*, *grands-parents* chacune la bonne reponse d'au moins un item `voisin` (groupe `gen`, `R12-3`) ; au moins un item `voisin` de groupe `evt` ; comptes de l'en-tete egaux aux comptes calcules. Une suite, un prenom, un mot de parente ne comptent pas | paquet reel prive des items de bonne reponse *grands-parents* (`FER-1`) ; prive des items `voisin` d'evenements (`FER-1`, *"evenement"*) ; items `voisin` plus deux `ordre` (*"la moins servie"*) ; en-tete au compte change ; paquet vide | le paquet reel | l'operation mentale ; *arriere-grands-parents* n'est pas exige (la parenthese du libelle ne le nomme pas) |

Sondes dans `contreexemple/PaquetCe1QlmGenerationsTest/contenu/`, reperees par `sonde NN`. Chaque verificateur est appele seul sur sa sonde : la sonde *vit avec* est aussi refusee par `G12-2` (aucune des trois formes d'enonce), et le test attend le message de `G12-6`.

### 2.4 `QC10`, l'exception de rang et les suites

`R12-4` retire l'exception des ages. **Aucun avis contraire sur les suites** : `valeurDe` du test de rang rend `null` sur une proposition qui ne commence pas par un chiffre, et `G12-5` refuse tout chiffre ; une suite comme une generation est donc hors du domaine numerique. `ce1-qlm-generations` entre dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` avec son motif, comme les trois paquets existants, et `PAQUETS_A_ITEMS_ECARTES` ne le nomme pas.

### 2.5 Ce que le transverse fait deja a `-12`

`LON-CE1-5` (six mots par proposition) tient *"naissance, entrée à l'école, premier emploi"* (6) ; `TRV-CE1-2` (trois propositions distinctes) refuse deux distracteurs identiques ; sa regle de la plus longue ne mord pas sur une tache `ordre`, ou les trois propositions ont la meme longueur en caracteres.

### 2.6 Ce qui est encodable et que je n'ajoute pas

- **`R12-7`, premiere moitie** : l'indice ne contient ni la bonne reponse de A ni le premier element de B. Le transverse ne refuse que la proposition ecrite telle quelle (*"celle des grands-parents"*, pas *"grands-parents"*). Forme possible : celle de `G10-4` sur les formes de 2.2. Non demandee dans `G12-*` ; je ne l'ajoute pas sans elle (`A8`).
- **`R12-5`, distracteur du meme cote** : lisible sur les rangs. Non demande. *"Aucun distracteur lui aussi juste avant ou juste apres"* (`T12-2`) est deja tenu par construction pour les items d'evenements et de generations, les rangs etant uniques hors de la paire que `G12-4` exclut ; il ne reste ouvert que sur *de quelle generation*.

### 2.7 Pourquoi pas le test transverse, pour les deux lots

Chaque liste est un fait de sa ligne. Mesure sur le corpus servi (section 3) : *vacances* est dans un champ de `ce1-qlm-besoins-de-la-plante` (*"oubliée pendant les vacances"*), juste a `-03` ; *toi* est dans 234 champs d'autres paquets (*"Demande-toi"*) ; *goûter* et *réveil* dans des paquets de CE1. `G10-6` ou `G12-1` dans le transverse rougiraient sur `-03` des le premier commit. Les deux listes de deuxieme personne different (possessifs seuls a `-10`, possessifs suivis d'une parente a `-12`) : trois lignes recopiees valent mieux qu'une extraction. **Ce qui me ferait changer d'avis** : une troisieme ligne qui porte exactement la meme liste.

### 2.8 La garde de rotation de `-01` appliquee a `-12`

**Inapplicable, deux raisons.** Elle vit dans `PaquetCe1QlmEtapesDeDeveloppementTest` et ne lit que le fichier de `-01` : elle ne lit rien de `-12` par construction. Et sur un ordre lineaire une rotation n'est pas un ordre defendable : *"parents, enfants, grands-parents"* est un distracteur juste. `G12-3` admet donc les rotations et refuse l'inverse, la seule permutation qui devient juste en lisant la consigne a l'envers.

## 3. Mesure sur le materiau reel

- **Perimetre** : tout fichier `*.yaml` de `adapters/adapter-content/src/main/resources/contenu/` a `eaac43a6`. 190 fichiers lus, 189 avec une cle `items` (dont 3 `ce1-qlm-*`), 3 257 items, 15 889 champs lus (enonce, indice, bonne reponse, distracteurs, quand ils existent), 9 375 propositions. Exclus : les jeux fautifs de `src/test/resources`.
- **Methode** : programme Java a fichier unique, hors du depot et non commite, lance par le lanceur de sources ; **JDK 21.0.10, propriete `java.version` imprimee par le programme** ; classpath `snakeyaml` 2.6 du depot local Maven, version que les avis de `-01` et `-02` ont lue par `dependency:build-classpath` et que je n'ai pas relue. Chaque fichier est lu par `new Yaml().load`, comme `PaquetLu`. Les motifs et les tables sont ceux des sections 1 et 2. **Ce n'est pas le test** : la preuve dans le depot sera l'execution de chaque classe sur son jeu fautif commite.

| Motif | Champs touches, tout le corpus | Dont `ce1-qlm-*` | Lecture |
|---|---|---|---|
| `G10-5`, accents gardes | 135 | 0 | vrais emplois (*"les beaux chapeaux"*, *"au calme"*) dans des paquets de francais et d'EMC |
| `G10-5` si la recherche etait sans accent | 135 + 28 | 0 | les 28 en plus sont tous *"a duré"* de `ce1-problemes-durees` : **faux rouges**, d'ou le choix de 1.2 |
| `G10-6` | 283 | **1** | *vacances* dans `-03` (2.7) ; hors de la portee du test de `-10` |
| `G12-1` | 75 | 0 | *goûter*, *réveil*, *pyjama*, *autrefois* dans d'autres paquets |
| `G12-5`, chiffre | 3 675 | 0 | mathematiques |
| `G12-5`, *ans* | 7 | 0 | sept ages reels (*"dix-huit ans"*, *"15 ans"*) ; aucun *dans* |
| `G12-6`, *vit avec* | 0 | 0 | - |
| `G12-6`, *tu*, *toi* | 234 | 0 | - |
| `G12-6`, possessif et parente | 6 | 0 | six vrais (*"ton frère"*, *"ta sœur"*, *"ton petit frère"*, *"ton cousin"*) : le mot permis entre les deux est mesure utile |
| Element ou paysage de `-10`, par egalite | 15 propositions | 0 | toutes sont litteralement un element (*"une plage"*, *"le métro"*, *"ferme"*) |
| Generation ou evenement de `-12`, par egalite | 3 propositions, aucune suite | 0 | *"enfants"* (deux paquets de francais), *"la naissance"* (`cm1-sciences-etapes-developpement`) |

**Ce que ces chiffres prouvent** : aucun motif ne mord sur un paquet `ce1-qlm-*` deja ecrit, sauf *vacances* sur `-03`, qu'aucune de ces gardes ne lit ; la reconnaissance par egalite ne reconnait que le mot lui-meme. **Ce qu'ils ne prouvent pas** : que les gardes mordent sur `-10` et `-12`, qui n'existent pas. C'est le role des jeux fautifs.

**Sondes** : le meme programme applique une ecriture des gardes aux sondes de `nadia-prof` et aux miennes, 32 pour `-10` et 41 pour `-12`. Chacune rend le verdict attendu, sauf une sonde de ma main, *"Qui est de la génération des grands-parents de Lina ?"* avec trois prenoms, refusee faute d'une des trois formes de `R12-2` : c'est `C2`, pas un defaut de la garde. Les sondes *beaucoup*, *durent*, *la montagne*, *les pistes de ski*, *Dans la vie de Sara* rendent un verdict different sous les mutants *recherche sans borne* (programme separe, meme JDK) : chacune tue son mutant. Ces sondes sont ecrites par moi, et valent moins que du materiau reel ; les 28 champs de *"a duré"* sont, eux, reels.

## 4. Conflits avec les regles de `nadia-prof`, a trancher par elle

| Code | Ou | Constat | Ce que je fais en attendant |
|---|---|---|---|
| `C1` | `G12-3`, sonde *doit passer* | Sur trois elements, **echanger le premier et le dernier, c'est renverser la suite**. *"Un distracteur qui echange deux elements"* n'est vrai que pour deux **voisins** ; l'echange des extremes est l'inverse, que sa propre regle refuse. Quatre ordres sur six restent des distracteurs (deux echanges de voisins, deux rotations) | la sonde *doit passer* s'ecrit avec un echange de voisins ; l'echange des extremes est une sonde *refusee* |
| `C2` | `G12-2` contre `R12-2`, `R12-5` | `G12-2` admet les prenoms de l'enonce en proposition ; `R12-5` veut trois generations ou trois evenements ; et aucune des trois formes d'enonce de `R12-2` ne demande un prenom en bonne reponse de A | les prenoms forment un troisieme groupe, admis seul ; en pratique ils ne servent que les suites de B |
| `C3` | `G12-2` contre `R12-3` | `R12-3` dit qu'un mot de parente *ne compte pas* pour `FER-1`, ce qui le suppose admis ; `G12-2` refuse toute proposition hors des listes et des prenoms, donc *la grand-mère* | refus, lettre de `G12-2` |
| `C4` | `G10-1` contre la sonde de `G10-5` | *un grand champ* passe `G10-5` mais, dans la liste d'un enonce A, `G10-1` le refuse : *"grand champ"* n'est pas un element (`R10-1` libere l'article et le nombre, pas l'adjectif). Mesure par le programme | refus ; si elle admet une taille, une liste fermee d'adjectifs (*grand*, *petit*, *haut*) entre devant le nom |
| `C5` | `G12-1` | *"tout mot des moments de `cp-frise-de-la-journee`"* : le paquet servi en a dix, sa parenthese en nomme six. J'ajoute ceux du paquet reel : *goûter*, *école de l'après-midi*, *se brosser les dents* | les dix, plus *reveil* et *autrefois* |
| `C6` | `R12-1` contre `GEN-CE1-1` | `GEN-CE1-1` ecrit *"naissance d'un enfant"*, `R12-1` *"naissance de son enfant"* ; seule la seconde est dans la table | refus de la premiere ; une ligne de `EVENEMENTS` si elle l'admet |

Une lecture a confirmer, sans conflit : `G10-2` exige un distracteur dans les paysages de **chaque** element partage nomme. `R10-2` dit *"un element partage"* ; avec deux partages, je lis *chacun*. Sur la liste fermee, la difference ne mord que si deux partages sont nommes et qu'aucun distracteur ne sert l'un d'eux.

## 5. Ce qui reste lu par `nadia-prof`

**`-10`**, ses mots, inchanges : un jugement hors des dix-sept mots ; un lieu reel ; qu'un indice soit une methode et pas une periphrase par un mot hors liste ; que A et B soient deux operations ; l'exactitude contre `R10-1` et les voisins (`CYC-1`, `NRC-CE1-1`). S'y ajoutent : un element hors liste dans un indice ; *vous*, *votre*, *te*, *t'* ; que l'en-tete ecrive les listes de `R10-1` (le test tient les siennes, il ne compare pas le commentaire).

**`-12`**, `T12-2` moins ce que `G12-3` voit : que la suite de prenoms soit la seule dans le sens ecrit ; qu'aucun distracteur d'un item *de quelle generation* ne soit lui aussi juste ; que *generation* soit vu depuis le personnage nomme ; qu'aucune configuration familiale ne soit la norme ou incomplete (`GEN-CE1-2`) ; prenoms et roles varies (`TRV-CE1-6`). S'y ajoutent : la bonne reponse d'un item *de quelle generation* ; *an*, *annee*, un age sans *ans* ; les verbes de la journee ; *habite avec* ; un indice qui donne la reponse (`A8`) ; qu'aucun item n'ecrive *la naissance* pour celle de l'enfant.

## 6. Actions et routage

| Code | Quoi | Qui |
|---|---|---|
| `A1` | Tables de 1.1, formes d'enonce, `G10-1` a `G10-7` dans `PaquetCe1QlmPaysagesTest`, avec le paquet, dans le commit du premier jet | `marc-dev` |
| `A2` | Tables de 2.1, `G12-1` a `G12-7` dans `PaquetCe1QlmGenerationsTest`, avec le paquet, dans le commit du premier jet | `marc-dev` |
| `A3` | Jeux fautifs commites : chaque sonde *refusee* attend l'identifiant de sa garde et son message propre, chaque sonde *passe* aucune exception ; les refus de `G10-7`, `G12-7` construits sur le paquet reel, en-tete inchange | `marc-dev` |
| `A4` | Deux lignes de compte en en-tete de chaque paquet, formes de 1.1 et 2.1 ; entree `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` pour chacun (`QC10`) | `marc-dev` |
| `A5` | `C1` a `C6` et la lecture de `G10-2` | `nadia-prof`, par `sofia-sm` |
| `A6` | Ajouts tires de ses textes, a confirmer ou retirer : la forme d'enonce A de `R12-2` dans `G12-2` ; le sens qui va avec le groupe, l'ordre de la bonne suite et `SUITE_ADMISE` dans `G12-3` | `nadia-prof`, par `sofia-sm` |
| `A8` | `R12-7`, premiere moitie, en garde (forme de `G10-4`) ou lue par elle | `nadia-prof` |
| `A7` | Rotation de `-01` inapplicable a `-12` : confirme le critere 8 de la story ; `T12-2` est repondu par `G12-3` | `camille-po`, pour information |

**Signe** : `theo-archi`, s57, palier 5, 2026-10-26.
