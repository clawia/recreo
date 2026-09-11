# Confirmation de la porte technique - s35 (`theo-archi`)

- **Auteur** : `theo-archi`. **Date** : 2026-09-11.
- **Perimetre mesure** : branche `s35-integration`, worktree dedie `s35-theo-confirm` branche depuis
  elle (amendement s30 : toute mesure du reacteur depuis son propre worktree, premiere ecriture ou
  non). `round-two..s35-integration` porte 8 commits.
- **Version** : **JDK 21.0.10**, la version que le JDK resolu par l'enveloppe rapporte, pas celle
  qu'une entree de toolchain declare (`ADR-0071`).

---

## 0. Verdict, en une table

| Objet | Verdict |
|---|---|
| `mvn -B clean verify` sur la racine | **vert**, 6 modules sur 6, `BUILD SUCCESS` - avant **et apres** les ecritures de ce commit |
| Job `chemins` de la CI, lance sur mon propre diff | **vert** : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| Compte de tests | **1479**, 0 echec, 0 erreur, 0 ignore |
| `ArchRule` du depot | **33**, inchange par ce sprint |
| `ADR-0091` | **`Propose` -> `Accepte`**, morsure refaite sur le materiau reel (section 3) |
| `ADR-0092` | **`Propose` -> `Accepte`**, avec **deux corrections de mes propres phrases** (section 4) |
| `ADR-0080` | **`Propose` -> `Accepte`**, son quatrieme et dernier critere est rempli (section 5) |
| Obstacle transverse du recouvrement des boutons | **borne, pas referme** - ouvert pour un futur paquet de designation (section 6) |
| Preuve de morsure sur un garde-fou recent | **trois**, toutes sur du materiau de production (sections 3, 4.1, 7) |

**Ce que cette confirmation ne fait pas** : elle ne prononce aucun Done pedagogique, elle ne ferme
aucune ligne de matrice, elle ne juge aucun contenu. La porte pedagogique est a `nadia-prof`.

---

## 1. La porte technique, avec sa commande et sa methode

```
bash outils/mvn-jdk21.sh -B clean verify
```

lancee a la racine du worktree `s35-theo-confirm`, **deux fois** : une premiere fois a l'ouverture,
sur l'arbre fusionne tel quel, et une seconde fois **apres les ecritures de ce commit** - un
architecte qui change un nom de constante et quatre fichiers de decision lance la commande de la CI
sur son propre diff avant de commiter (amendement du 2026-09-08 a `CLAUDE.md`, le geste qui manquait
a s30). Les deux sont vertes ; les chiffres ci-dessous sont ceux de la **seconde**. En-tete de
l'enveloppe : *"Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10"*.

```
[INFO] recreo ............................................. SUCCESS [  0.906 s]
[INFO] recreo-domain ...................................... SUCCESS [ 17.461 s]
[INFO] recreo-application ................................. SUCCESS [  5.763 s]
[INFO] recreo-adapter-web ................................. SUCCESS [ 48.871 s]
[INFO] recreo-adapter-content ............................. SUCCESS [ 26.564 s]
[INFO] recreo-bootstrap ................................... SUCCESS [01:51 min]
[INFO] BUILD SUCCESS
```

**Compte de tests, avec sa methode dans le meme paragraphe** : somme de l'attribut `tests="..."` des
**202** rapports XML de surefire et de failsafe apres le `clean`, jamais la somme des lignes
`Tests run:` des rapports `.txt` (amendement s11 : quatre classes de ce depot melangent deux moteurs
JUnit et le rapport texte n'en garde qu'un). **1479 tests, 0 echec, 0 erreur, 0 ignore.** Le chiffre
concorde avec celui de `sofia-sm`, obtenu sur le meme arbre par un autre passage.

**Compte des `ArchRule`, avec sa methode** : occurrences de l'annotation `@ArchTest` dans
`**/src/test/java/**/*.java` du seul worktree mesure - `target/` exclu par construction (le motif
ne vise que les arbres de sources), les worktrees des autres agents exclus parce qu'ils sont freres
de cet arbre et non dedans, et les jeux fautifs exclus parce qu'ils vivent sous
`src/test/resources`. **33 regles, dans 11 classes** : `DecouplageMecaniqueContenuTest` 12,
`AbstractionsCiteesTest` 4, `GestionDesErreursTest` 4, `DifficulteTest` 3,
`AccesALEtatDeSessionTest` 2, `ArchitectureEnCouchesTest` 2, `PureteDesCouchesTest` 2,
`AssertionsWebTest` 1, `ChargementDuContenuTest` 1, `LeChoixDesJeuxResteDerriereLePortTest` 1,
`LesReglesMordentTest` 1. **Ce sprint n'en ajoute aucune** : ses quatre garde-fous neufs jugent des
chaines de contenu, un document HTML servi et un compte de fichiers - aucun n'est un type, donc
aucun n'est exprimable en `ArchRule`.

**Ce que cette commande ne couvre pas, et je le nomme plutot que de le deduire** (amendement du
2026-09-08 a `CLAUDE.md`) : `mvn verify` **n'est que le job `build`** de
`.github/workflows/ci.yml`. Les jobs qui verifient les prefixes de commit, l'absence de chemin de
poste de travail et les juges purs des outils **ne sont pas lances par `mvn`**. Je n'annonce donc pas
"la CI est verte" : j'annonce que **le job `build` est vert**, et que j'ai lance en plus **le job
`chemins` sur mon propre diff** - `bash .github/scripts/verifier-chemins.sh` alimente par mes six
fichiers a la place de `git ls-files`, sortie *"Aucun chemin de poste de travail ni nom de compte
dans les fichiers verifies."*, code de retour 0. Les jobs de prefixes de commit et de juges purs des
outils ne sont pas lances par moi et **je ne les deduis pas**. Le job `chemins` est celui qui a coute
un sprint entier a rester rouge derriere un `mvn verify` vert.

---

## 2. Les garde-fous neufs de s35, recenses avant d'etre juges

Perimetre : fichiers de test ajoutes par `round-two..s35-integration`.

| Garde-fou | Auteur | Ce qu'il tient | Ou sa morsure est prouvee |
|---|---|---|---|
| `AucunTexteAlternatifDeDesignationNeNommeSesZonesTest` | `lea-dev` | `ADR-0091` clause 1 | section 3, materiau reel |
| `AucunIndiceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest` | `lea-dev` | `ADR-0091` clause 2 | section 3, materiau reel |
| `LEcranDeMancheDuJeuDeDesignationReelEstServiTest` | `lea-dev` | `ADR-0092` mecanisme 1, derniere clause d'`Accepte` d'`ADR-0080` | section 4.1, materiau reel |
| `AucunPaquetDeDesignationNEntreEnProductionSansLaDetteDeLisibiliteDuLibelleTest` | `lea-dev` | `ADR-0092` mecanisme 2, cliquet a egalite | section 4.3, par relecture adverse |
| `LEcranDeFinDePartiePorteLesDeuxNombresTest` | `sofia-sm` | `SCO-R1`, les deux nombres a l'ecran de fin | section 7, materiau reel |

`marc-dev` (`S35-CONS-4`, scission de
`cm1-histoire-geographie.yaml` en quatre fichiers) n'ecrit pas de garde-fou neuf : son lot fait
bouger des comptes derives, ce qui est exactement le comportement attendu d'un perimetre derive.

---

## 3. `ADR-0091` passe `Accepte` - la morsure a ete refaite, pas reprise

### 3.1 Pourquoi elle a du etre refaite

Le message du commit `212e9ea6` affirme *"ADR-0091 accepte"* et *"morsure verifiee sur le materiau
reel"*. **Aucun des deux fichiers d'`ADR` n'avait ete touche** : leur statut sur le disque etait
reste `Propose`. Un message de commit n'ecrit pas une decision d'architecture - le depot est la seule
memoire partagee, et c'est le fichier qui la porte. L'affirmation a donc ete verifiee.

### 3.2 La mesure

```
git show 212e9ea6^:adapters/adapter-content/src/main/resources/contenu/cp-frise-de-la-journee.yaml \
  > adapters/adapter-content/src/main/resources/contenu/cp-frise-de-la-journee.yaml

bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test \
  -Dtest='AucunTexteAlternatifDeDesignationNeNommeSesZonesTest,AucunIndiceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest' \
  -Dsurefire.failIfNoSpecifiedTests=false
```

Le `-am` est porte (`CLAUDE.md`, troisieme prerequis) : sans lui, `adapter-content` serait resolu
contre la derniere version **installee** dans le depot local et la mesure decrirait un autre arbre.

**`Tests run: 7, Failures: 2`.** Les deux echecs sont les deux balayages de production. **Les cinq
autres tests des deux classes restent verts sur le contenu fautif**, dont les deux cas verts
obligatoires - la propriete demandee : mordre le defaut et laisser passer le contenu valide.

| Clause | Defauts sur le materiau reel | Message |
|---|---|---|
| 1 | **40** - 7 items de `frise-journee-ecole` x 4 zones (`lever`, `petit-dejeuner`, `dejeuner`, `gouter`) + 4 items de `frise-soir-routine` x 3 zones (`diner`, `pyjama`, `coucher`) | *"le texte alternatif nomme la zone [...] (ADR-0091 clause 1, FRI-R1)"* |
| 2 | **1** - `item-0`, jeton `premier` | *"l'indice porte `premier`, jeton distinctif du libelle de sa bonne zone (`le premier`) (ADR-0091 clause 2, FRI-R1)"* |

**Les deux chiffres corrigent mon `ADR` vers le haut.** Ma lecture a la main du 2026-09-11 annoncait
*"quatre zones sur six"* : exact, mais sur le seul item 1. Le garde-fou balaye les onze items et les
deux dessins, d'ou 40. La clause 2, elle, rougit **exactement sur l'item que `nadia-prof` a mis en
cause et sur aucun autre**.

### 3.3 Verdict

**`Accepte`.** Les deux clauses sont dans l'arbre avec quatre jeux fautifs, rouges sur l'ancien
contenu, vertes sur le nouveau, et vertes sur `cp-plan-de-la-classe` - un paquet ecrit par quelqu'un
d'autre que l'auteur de la regle, ce qui est la seule preuve valable que la clause 1 n'interdit pas
de dire ce que l'image est.

### 3.4 Une ligne que je route plutot que d'encoder

L'item 9 de `cp-frise-de-la-journee` (`bonneZone: coucher`, libelle *"le troisieme"*) porte l'indice
*"C'est le tout dernier moment de la journee."* La clause 2 est **verte, et elle a raison de
l'etre** : `dernier` n'est pas un jeton du libelle de sa bonne zone. L'enfant doit encore compter les
rangs, et la frise du soir a quatre moments pour trois rangs. C'est la limite 2 deja ecrite dans
`ADR-0091` - la composition des trois textes reste un jugement - et je la nomme ici **avec son item
exact**, a `nadia-prof`. Je ne durcis pas la clause : aucune mesure ne dit que cet item se gagne
image effacee, et durcir une regle sur un usage que j'imagine est ce que `CLAUDE.md` refuse.

---

## 4. `ADR-0092` passe `Accepte`, et deux de mes propres phrases sont corrigees

### 4.1 Le mecanisme 1 mord, et la mesure dit exactement ce qu'il ajoute

Mutation du **materiau de production** : le cadre de la zone `armoire` de
`cp-plan-de-la-classe.yaml` ramene de `hauteur: 30` a `hauteur: 5` sur ses six occurrences, puis
`bash outils/mvn-jdk21.sh -B test` sur la **racine** du reacteur - pas sur un module, pour que la
reponse porte sur le depot et non sur un perimetre choisi par moi.

| Module | Tests | Echecs |
|---|---|---|
| `recreo-domain` | 111 | 0 |
| `recreo-application` | 13 | 0 |
| `recreo-adapter-web` | 397 | 0 |
| `recreo-adapter-content` | 705 | 0 |
| `recreo-bootstrap` | 247 | **1** |

**Un seul test du depot sur 1473 voit ce defaut**, et c'est
`LEcranDeMancheDuJeuDeDesignationReelEstServiTest.surChaqueJeuDeDesignationDuCatalogueReelLEcranDeMancheEstConforme`
: *"la zone `armoire` rend au plus 1.1rem (plafond de hauteur 22.0rem, plus petit cote 5.0%), sous le
plancher de cible tactile de 2.75rem (ADR-0080, garde-fou 3)"*.
`AucuneZoneDesignableNeDescendSousLaCibleTactileTest`, qui tient le meme critere sur un jeu fabrique
dans `adapter-web`, **reste vert** - il ne pouvait pas voir celui-la.

C'est la mesure que l'`ADR` promettait, obtenue par l'autre bout : il affirmait que sept garde-fous de
`bootstrap/rendu` ecartent la designation et qu'aucun test ne sert son ecran de manche. Un defaut
reel dans un paquet de production le confirme sans avoir a recenser les exclusions une a une.

### 4.2 Correction 1 - la decision 3 annoncait un levier *"gratuit"* qui ne l'est pas

`ines-design` me l'a remonte (sa proposition, section 2.2), en le trouvant par le reacteur.
**Refait par moi plutot que repris** - la meme discipline que celle appliquee au commit de
`lea-dev` : pave horizontal de `.zone` ramene a `0,25rem` dans `recreo.css`, puis

```
bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test \
  -Dtest='LActionNeDeclarePasMoinsQueLaSortieTest' -Dsurefire.failIfNoSpecifiedTests=false
```

**`Tests run: 13, Failures: 1`**, six griefs sur `templates/fragments/designation.html`, dont *"la
sortie `.btn.lien-accueil` "Les jeux" declare rembourrage gauche et droite a 1,10rem quand l'action
`.btn.zone` "" se contente de 0,25rem"*.

Valeurs lues par moi dans `recreo.css` : `.zone { padding: 0.75rem 1.25rem }`,
`.lien-accueil { padding: 0.5rem 1.1rem }`, `.chip-niveau { padding: 0.5rem 0.9rem }`. Le plancher
reel du pave de `.zone` est donc **1,1rem**, et le gain du levier vaut
`(1,25 - 1,1) x 2 x 16 = 4,8 px`, **pas 40 px**.

**Ce qui etait juste et ce qui ne l'etait pas se separent nettement** : l'arithmetique sur le
garde-fou 3 est exacte et reste vraie. *"Il ne casse rien"* etait une conclusion tiree du **seul**
garde-fou que j'avais regarde. **Une propriete verifiee contre un garde-fou n'est pas une propriete
verifiee contre le depot**, et un architecte qui annonce a un lot qu'un changement ne casse rien doit
avoir lance le reacteur. `ines-design` a fait ce que mon avis n'avait pas fait, et elle l'a ecrit.

### 4.3 Correction 2 - le mecanisme 2 prescrivait un nom qui affirme un audit

`ADR-0092` prescrivait `PAQUETS_DE_DESIGNATION_AUDITES_A_L_OEIL = 2` et `lea-dev` l'a implemente tel
quel, Javadoc comprise (*"`cp-plan-de-la-classe`, audite a l'oeil par `nadia-prof` sur `ZON-15`"*).

**Le fait, verifie dans l'arbre** : `squad/conformite/` porte
`FICHE-designation-frise-de-la-journee.md` et **aucune fiche pour `cp-plan-de-la-classe`** ; aucun
verdict de `nadia-prof` de s35 n'existe. **2 paquets servis, 1 seul audite.**

**La cause est dans mon `ADR`, pas dans son implementation** : le recensement que le cliquet
reutilise ne sait lire que les paquets **servis**. Aucun nom ne peut promettre l'audit sans mentir -
`ADR-0015`, un nom qui promet plus que son perimetre, applique a ma propre prescription.

**Corrige dans ce commit** : la constante s'appelle
`PAQUETS_DE_DESIGNATION_PORTANT_LA_DETTE_DE_LISIBILITE`, sa Javadoc et son message d'echec disent
qu'elle compte des paquets servis, et **ce que le cliquet fait reellement est ecrit** : il oblige un
diff a toucher ce chiffre, donc il **route** l'audit ; il ne le verifie pas et **il ne bloque pas un
paquet dont l'audit est en attente**. La valeur `2` ne change pas - elle est juste.

**Le cliquet lui-meme est-il mordant ?** Il ne porte aucun test de morsure propre, et l'`ADR` disait
pourquoi : son comptage est celui de
`AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest`, **appele et non
recrit**, dont les morsures couvrent les deux sens sur la population reelle - un paquet de plus
(*"morsure 1"*), un plafond que le compte ne justifie plus (*"morsure 3"*), une population vide
(`ADR-0051`), et un paquet d'un autre type qui ne doit rien faire bouger. Verifie par relecture de
ces quatre tests plutot qu'affirme. **Cas degenere verifie aussi** : si le recensement rendait une
population vide, `compte` vaudrait 0 et ce cliquet serait **rouge** - il ne peut pas etre vert a vide.

### 4.4 Verdict

**`Accepte`.** Les deux mecanismes sont dans l'arbre, le mecanisme 1 mord sur du materiau de
production de facon decisive, le mecanisme 2 fait ce qu'un cliquet peut faire et sa limite est
desormais ecrite dans son propre code.

---

## 5. `ADR-0080` passe `Accepte` - le quatrieme critere est rempli

Je ne l'avais pas au perimetre de cette confirmation ; je le bouge parce que **le laisser a
`Propose` ferait mentir deux `ADR`**. `ADR-0092` decision 4 ecrit que son mecanisme 1 *"est la
derniere clause d'`Accepte` d'`ADR-0080`"*, et cette clause est livree.

| Critere (section 6 d'`ADR-0080`) | Etat | Ou la mesure vit |
|---|---|---|
| T4 livre, garde-fous 1, 2, 3 ecrits et mordants | rempli | s34 pour l'ecriture, **s35 pour la morsure de 1 et 3 sur la population reelle** (section 4.1) |
| T6 livre, trois balayages etendus, cliquet a son compte reel dans le meme diff | rempli | `cp-plan-de-la-classe` servi, cliquet `1 -> 2`, commit `14a1b4e9` |
| Les deux trous de sa section 3 tranches | rempli | `ADR-0088` (s32) |
| Les huit regles vertes sur leur population reelle | rempli | six mesurees a s34 par mutation du reel, les deux dernieres a s35 |

**Ce que j'ai mesure aujourd'hui et ce que je cite** : les garde-fous 1 et 3, aujourd'hui, par la
mutation de la section 4.1. Les six autres regles ont ete mesurees par moi a s34, par mutation du
materiau reel, et leur mesure vit dans `confirmation-archunit.md` de s34, section 5 - je la cite, je
ne la refais pas, et je dis laquelle des deux dates porte quoi.

**Ce que cet `Accepte` ne dit pas** : aucun Done pedagogique - `QLM-CP-11` est **servie et non
auditee** -, aucune ligne de matrice fermee, et l'obstacle transverse non referme (section 6).

---

## 6. L'obstacle transverse du recouvrement des boutons de zone - ma position

### 6.1 Sur mon alternative (d), et sur le refus d'`ines-design`

Mon alternative (d) - bouton elastique, `min-width`/`min-height` au lieu de `width`/`height` - est
ecartee par `ines-design` (sa proposition, section 3.1) pour trois motifs. **Je ne la conteste pas**,
et un de ses motifs est de la nature de l'obstacle lui-meme, que je n'avais pas vu : **un bouton qui
grandit pour loger son texte recouvre une part du dessin plus grande que son cadre**, donc aggrave le
fait que l'alternative pretendait attenuer. Les deux autres motifs - `ZON-15` serait renversee, et la
coherence visuelle entre six zones - sont de son ressort et du ressort de `nadia-prof`, pas du mien.

### 6.2 Sa mesure, verifiee

| Ce qu'elle mesure | Verification |
|---|---|
| Le pave de `.zone` est borne a 1,1rem par `ADR-0017`, pas a 0 | **exact**, refait par moi : section 4.2 |
| `armoire` passe de 16 x 22 % a 30 x 30 %, `porte` de 18 x 16 % a 32 x 17 % | **exact**, lu dans `cp-plan-de-la-classe.yaml` servi (`x: 4, y: 48, largeur: 30, hauteur: 30` et `x: 4, y: 80, largeur: 32, hauteur: 17`), sur les six items |
| Plus petit cote 30 % et 17 %, soit 105,6 px et 59,84 px contre un plancher de 44 px | **exact** ; et desormais **rejoue par la CI sur l'ecran servi**, ce qui n'etait pas le cas quand elle l'a calcule (section 4.1) |
| Boite de contenu 61,6 px et 68,64 px, contre 12 px et 19 px | **exact** au pave inchange de 44 px |
| `recreo.css` non touche par cette story | **exact**, l'ajout de 18 lignes a `recreo.css` dans ce sprint vient du lot `S35-SCO1-ECRAN-FIN` |

### 6.3 Verdict : **borne, pas referme**

**Ce que le lot ferme** : la lisibilite du libelle sur les deux cadres etroits de
`cp-plan-de-la-classe`, par la geometrie seule - sous reserve de l'oeil de `nadia-prof`, la propriete
dependant de la police de l'appareil (`ADR-0092` decision 2).

**Ce que le lot ne ferme pas, et c'est la question transverse elle-meme** : le bouton reste pose sur
le dessin qu'il designe, fond translucide a 55 %, libelle a l'interieur. **Agrandir un cadre couvre
plus de dessin, pas moins.** `ines-design` l'ecrit elle-meme en section 3.3, et je le confirme : la
forme qui supprimerait le recouvrement - fond plus transparent, libelle hors de la boite de l'image,
zone qui ne serait plus un bouton plein - n'a pas ete choisie et n'appartient pas a ce lot.

**L'obstacle reste donc ouvert pour un futur paquet de designation.** La difference avec s34 est la
seule qui compte : il n'est plus route sans mecanisme. `ADR-0092` le borne par un cliquet a egalite
qui rougit au troisieme paquet, et ce cliquet dit maintenant ce qu'il fait - il route l'audit, il ne
le verifie pas. **Ce que je ne fais pas** : je ne propose aucune forme, et je ne rouvre pas
`ZON-15`.

---

## 7. Troisieme preuve de morsure - le garde-fou de `sofia-sm`, sur materiau de production

Le tirage demande par l'amendement s10 porte sur un garde-fou recent mute **sur le materiau de
production reel plutot que sur sa fixture**. `LEcranDeFinDePartiePorteLesDeuxNombresTest` porte sa
propre morsure, mais elle mute le document **en memoire** apres rendu. J'ai mute la source.

Methode : le bloc de score de
`adapters/adapter-web/src/main/resources/templates/fragments/retour.html` remis dans sa forme
d'avant s35 - la ligne unique
`<p class="score" th:text="'Score : ' + ${score.reussites()}"></p>` a la place des quatre elements
livres -, puis le garde-fou seul.

**`Tests run: 3, Failures: 3`** - les trois tests de la classe rougissent, avec six griefs sur trois
mecaniques :

```
"bandit-manchot-additions-cp : aucun element ne porte, seul et entier, le nombre de manches
 trouvees seul ("9") - SCO-R1"
"bandit-manchot-additions-cp : aucun element ne porte, seul et entier, le nombre de manches
 jouees ("10") - SCO-R1"
"saisie-complements-10-cp : ... ("6") ... ("7")"
"designation-frise-de-la-journee : ... ("9") ... ("10")"
```

**Ce que cela etablit** : le garde-fou est couple a l'etat reel du gabarit servi, pas seulement a une
mutation en memoire de sa propre production, et il nomme `SCO-R1` - le refus qu'il ferme. **Ce que
cela n'etablit pas** : rien sur le texte lui-meme (*"Tu as trouve, tout seul :"*), qui est du
contenu lu par un enfant et appartient a `nadia-prof`.

Tous les materiaux mutes ont ete remis en etat ; `git status` etait propre avant les ecritures de ce
commit, et le `verify` de la section 1 a ete relance apres elles.

---

## 8. Ce que cette confirmation laisse ouvert

1. **L'audit de `ZON-15` et de `ZON-16` sur `cp-plan-de-la-classe`** - non fait, aucune fiche de
   conformite, et le cliquet d'`ADR-0092` ne le verifie pas. A `nadia-prof`.
2. **L'item 9 de `cp-frise-de-la-journee`** (section 3.4) - route a `nadia-prof`, non encode.
3. **Le recouvrement du dessin par le bouton** (section 6.3) - ouvert, borne.
4. **Les jobs de CI autres que `build`** (section 1) - non lances par moi, non deduits.
