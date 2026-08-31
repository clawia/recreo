# [Sprint s24] Planning - 2026-08-30

- **Horodatage de debut** : 2026-08-30 22:32
- **Anime par** : sofia-sm (casquette Scrum Master)
- **Participants** : camille-po, theo-archi, nadia-prof, ines-design, marc-dev, lea-dev, sofia-sm
- **Slug fixe par ce document** : `s24-trois-paquets-et-ouverture-de-la-saisie`

---

## 1. Le slug, et le renommage du repertoire provisoire

`theo-archi` et `ines-design` ont ecrit sous `s24-ouverture-de-la-saisie-slug-provisoire` en
signalant tous les deux le renommage ; `ines-design` le porte en dependance **`D7`**.

**Slug retenu : `s24-trois-paquets-et-ouverture-de-la-saisie`.** Le sprint n'est pas l'ouverture de
la saisie seule : `DEC-0059` engage **trois paquets de contenu** sur la mecanique existante **et**
l'ouverture de la mecanique de saisie, et pose entre les deux un ordre de priorite (section 5.2 :
le contenu prime la saisie). Un slug qui ne nommerait que la saisie decrirait le lot minoritaire en
lignes de matrice et majoritaire en risque - il ferait croire, dans six mois, que s24 etait un
sprint de mecanique.

**`D7` est close** : `git mv` du repertoire, sans deplacement de contenu, dans le meme commit que la
fusion d'ouverture. Les trois fichiers qui citaient l'ancien chemin sont mis a jour
(`avis-faisabilite.md`, `proposition-ines-ecran-de-saisie.md`,
`squad/outils/ines-design/mesurer-ecran-de-saisie.py`) et la mention "repertoire provisoire" de
chacun des deux documents est remplacee par le constat date du renommage. **Zero occurrence de
l'ancien slug** dans l'arbre suivi apres l'operation, verifie par recherche.

---

## 2. Etat de la fusion d'ouverture

Quatre lots ecrits avant ce planning, chacun dans un worktree dedie, depuis `round-two` a `ea546cf`
(tete de cloture de s23).

| Branche | Tete fusionnee | Contenu |
|---|---|---|
| `s24-camille` | `8ac06ac` | `DEC-0059` (perimetre), `DEC-index`, six stories au backlog, trois lignes de matrice passees `en cours` |
| `s24-theo` | `bd7161d` | `avis-faisabilite.md`, `ADR-0069`, `ADR-0070`, amendement 4 d'`ADR-0058`, puis `ADR-0071` et son complement sur le JDK declare contre execute |
| `s24-nadia` | `10cf4f4` | `ARBITRAGE-PREALABLE-s24` - borne reconfirmee, `DEN`, premier paquet de saisie (`CPL-1` a `CPL-8`), deux points de satisfaisabilite tranches |
| `s24-ines` | `e4d772b` | `proposition-ines-ecran-de-saisie.md`, prototype de mesure, script de mesure, releve de 80 mesures sur dix largeurs |

**Lignee verifiee par moi avant de fusionner, pas supposee** : `git merge-base --is-ancestor
round-two <branche>` vrai sur les quatre. `s24-nadia` porte les commits de `s24-camille` et le
premier commit de `s24-theo` dans son ascendance - elle a construit sur eux, ce que son document
dit lui-meme. Fusionnees par `git merge --no-ff` dans l'ordre camille, theo, nadia, ines :
**aucun conflit sur les quatre fusions.**

**`s24-theo` a avance pendant que je lisais.** Sa tete etait `335e6c1` a l'ouverture de ma lecture
et `bd7161d` au moment de la fusion : `theo-archi` a livre entre les deux `ADR-0071` et le
complement sur le JDK, le lot demande par l'utilisatrice sur le constat de `nadia-prof`. **C'est
`bd7161d` qui est fusionne**, et c'est pourquoi la section 8 de ce document peut etre ecrite au lieu
d'etre reportee.

### Porte technique, mesuree par moi sur l'arbre fusionne

```
bash outils/mvn-jdk21.sh -B clean verify
```

a la racine du reacteur, aucun `-pl`, donc aucun `-am` a citer : le reacteur est complet par
construction. **BUILD SUCCESS**, six modules en `SUCCESS`.

**Compte de tests, methode nommee** : somme des attributs `tests`, `failures`, `errors` et
`skipped` de l'element `<testsuite>` de chaque `TEST-*.xml` de `target/surefire-reports/` et
`target/failsafe-reports/`, sur les rapports produits par ce build apres un `clean`.
**124 rapports, 811 tests, 0 echec, 0 erreur, 0 ignore.** Repartition : `adapter-web` 332,
`adapter-content` 288, `bootstrap` 123 surefire + 6 failsafe, `domain` 49, `application` 13.

**D'ou vient l'ecart avec les chiffres annonces, plutot que de le taire.** La cloture de s23 portait
**802**. `ines-design` mesure **802** sur sa branche - exact, son lot ne touche aucun module.
`theo-archi` annonce **807** dans son avis puis **811** dans son complement : ses deux mesures sont
justes a leur date, la premiere avant `ADR-0071` (quatre tests) et la seconde apres. **811 est le
total de l'arbre fusionne, etabli par moi et non relaye** : 802 + 5 (`ADR-0070` quatre tests,
morsure d'`ADR-0069` un test) + 4 (`ADR-0071`) = 811.

**30 regles ArchUnit, inchange.** Methode : comptage des lignes portant `static final ArchRule` dans
les fichiers `.java` des quatre arbres de modules, arbre de contre-exemple exclu. Les trois `ADR` de
ce lot n'en ajoutent aucune, ce que chacun ecrit et que je verifie.

### Etat de la matrice a l'ouverture, verifie par moi

Comptage de la colonne Statut des **quatre tableaux de matiere** de
`squad/programme/MATRICE-couverture.md`, apres la fusion : **47 lignes** - 24 `couvert-valide`,
4 `en cours`, 15 `non couvert`, 4 `non couvrable en l'etat`. Denominateur atteignable **43**.
**24/43, soit 55,8 %.** Identique au compte de `nadia-prof` ; le compte de `DEC-0059` (1 `en cours`,
18 `non couvert`) est celui d'avant sa propre modification et reste exact a sa date.

**Catalogue : 27 entrees**, `grep -c '^  - id:' catalogue.yaml`, et **27 fichiers** dans
`contenu/`. Voir l'obstacle 5 : `DEC-0059` en annonce 28 avec un ecart d'un, et c'est le seul
chiffre de son tableau de verification que je ne retrouve pas.

---

## 3. Objectif du sprint (gele)

**Trois paquets de contenu neufs sur la mecanique existante, et l'ouverture de la mecanique de
saisie jusqu'a un jeu qu'un enfant peut jouer**, sur l'arbitrage de l'utilisatrice rapporte par
`DEC-0059` : *"ouvre s24 avec les trois paquets et la saisie"*.

- **Lot A - contenu** : `S24-NAR` (comprendre un texte narratif tres court, francais CP),
  `S24-PLU` (marques du pluriel en `-s`, francais CP), `S24-FRA` (notion de fraction simple,
  mathematiques CM1, moitie "notion" seule en couverture partielle nommee). Trois lignes passent
  `non couvert` -> `en cours` ; si les trois aboutissent sans cycle de refus, la matrice passerait de
  **24/43 (55,8 %)** a **27/43 (62,8 %)** - **un objectif de sprint, jamais un Done** : aucune ligne
  ne passe `couvert-valide` par ce document, ce statut n'est prononce que par `nadia-prof` apres
  audit reel.
- **Lot B - saisie** : la mecanique de saisie et son premier paquet
  (`S24-SAI-1`, `S24-SAI-2`). **Ce lot ne fait bouger aucune ligne de la matrice**, et la section
  suivante dit pourquoi ce zero est un fait instruit et non un manque d'instruction.

**En cas de conflit de capacite, le Lot A prime le Lot B** (`DEC-0059`, section 5.2 - l'ordre est de
`camille-po`, je ne le rouvre pas ; ce que j'en fais operationnellement est en section 6).

**`SUB` reste dehors, et l'echange `NAR` -> `SUB` n'a pas lieu.** `camille-po` me l'ouvrait comme
neutre pour toutes ses contraintes, sur la reponse de `nadia-prof`. Elle a repondu, mesure a
l'appui : *"je ne le prends pas, et le fait mesure joue dans l'autre sens"* - `SUB-2` autorise
45 mots de texte support par item contre 18 pour `NAR-2`, donc `SUB` est le paquet cher sur l'axe de
l'accentuation et `NAR` le paquet borne. **Je n'exerce pas l'echange.** `SUB` reste le premier
report, compte comme tel par `DEC-0059`.

**Gele** : cet objectif ne change plus pendant le sprint.

---

## 4. Un ecart de ma part, ecrit ici pour qu'il ne se cherche pas dans six mois

**J'ai affirme a l'utilisatrice que l'ouverture de la saisie ferait passer la couverture de 28 a
33 lignes sur 43. Ce chiffre est faux, et il est de moi.** Il n'a de source dans aucun arbitrage,
aucune `DEC`, aucun `ADR`. L'utilisatrice a arbitre l'ouverture de la saisie sur ce chiffre ; elle
en est informee, et **son arbitrage tient** - il vaut par les motifs de `DEC-0059`, section 5.2, pas
par mon chiffre.

**Deux instructions independantes le contredisent, et elles rendent la meme valeur :**

- `camille-po` (`DEC-0059`, section 5.1) a lu **18 lignes** une par une contre les six refus
  prealables `R1` a `R6` et la separation d'avec l'operation posee : **zero ligne ouverte**.
- `nadia-prof` (arbitrage prealable, section 6) a refait cette lecture, l'a declaree exacte, et a
  instruit **les cinq lignes qui manquaient** - les quatre `non couvrable en l'etat` et la ligne
  `en cours` depuis s03 : **zero sur 23 lignes instruites, soit toutes celles que la saisie pourrait
  faire bouger.**

**Verifie par moi sur l'arbre fusionne** : 47 lignes de matiere, 24 `couvert-valide`. 47 - 24 = **23
lignes** qui ne sont pas deja vertes, ce qui est exactement le perimetre que `nadia-prof` a
instruit. **Le point de depart n'etait pas non plus 28 : il est 24.** Les deux bornes de ma phrase
etaient fausses.

**Ce que le lot de saisie apporte reellement se chiffre ailleurs que sur la matrice**, et le chiffre
est de `nadia-prof`, pas de moi (section 7.1 de son arbitrage) : sur les complements a 10, *"un
enfant qui ne sait rien obtient aujourd'hui 7 manches sur 7. En saisie, avec trois essais sur onze
reponses possibles, il en obtient au plus trois sur onze par manche. La saisie est la premiere
mecanique de ce produit ou un enfant qui ne sait pas peut echouer."* **C'est cette phrase qui
remplace mon 33**, et `nadia-prof` demande explicitement qu'elle figure au rapport de sprint. Elle y
figurera.

---

## 5. Le point dur : le trou de sequencement du lot 5

### 5.1 Ce qui s'est passe, dit exactement

`camille-po` tranche que s24 vise la mecanique **jouable**, donc avec un premier paquet de saisie
(`DEC-0059`, section 5.3). `nadia-prof` choisit ce paquet : les complements a 10 au CP, en saisie
numerique (`CPL-1` a `CPL-8`). `theo-archi` mesure que **le premier paquet de saisie pose dans
`contenu/` declenche son lot 5** - le tri de 13 garde-fous de contenu qui derivent leur perimetre du
**repertoire** et non du catalogue - et il ecrit qu'il **n'en repond pas** : *"c'est le seul lot dont
je ne reponds pas [...] le poste que je surveillerais si le sprint devait deraper."*

**Aucun des trois n'avait lu les autres quand il a ecrit.** Ce n'est pas une contradiction de
personne.

### 5.2 Ce que le verdict de `theo-archi` dit, et ce qu'il ne dit pas

Son verdict d'infaisabilite porte des mots precis, et le resume qui m'en a ete fait ne les portait
pas : *"Ce qui serait infaisable [...] : ouvrir la saisie **et** livrer trois paquets **de saisie**
dans le meme sprint."* Et immediatement apres : *"Trois paquets de **choix** a cote de l'ouverture
de la saisie, c'est un autre calcul, et il est de `camille-po`."*

**s24 ne porte pas trois paquets de saisie. Il en porte un, et trois paquets de choix.** Le verdict
d'infaisabilite ne mord donc pas, litteralement, sur le perimetre de `DEC-0059`, et je refuse de le
relayer comme s'il y mordait - ce serait citer un chiffre sans son perimetre, ce que ce depot
sanctionne partout ailleurs.

**Ce qui mord sur s24 est sa condition 2**, et elle est aussi tranchante :

> *"Le lot 5 est compte dans le sprint et non decouvert dedans. [...] Si le perimetre le laisse
> dehors, alors le premier paquet de saisie ne peut pas entrer dans `contenu/` ce sprint - et sans
> paquet, la saisie n'est pas jouable. **Les deux ne peuvent pas etre vrais en meme temps.**"*

**Et le lot 5 n'est compte nulle part.** `DEC-0059` a ete ecrite avant l'avis de faisabilite : sa
section 6.3 nomme les couts techniques qu'elle connaissait - les deux regles executables dues par
`ADR-0058`, le retypage d'`adapter-web`, l'ordre de fusion - et **le lot 5 n'y figure pas**. Les
stories `S24-SAI-1` et `S24-SAI-2` ne le mentionnent pas davantage. **Le sprint engage un poste de
travail qu'aucun document de perimetre ne compte.** C'est un trou de sequencement, il est de ma
main, et je le tranche ici.

**Le chiffre de la sonde, avec son perimetre et son auteur** : `theo-archi` mesure, sur un paquet de
sonde depose dans `contenu/` puis retire, `bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-content
-am test` -> **288 tests, 8 echecs, 28 erreurs, sur 13 classes**. Perimetre : **le module
`adapter-content`, avec `-am`, pas le depot**. **Je ne l'ai pas reproduit** - je n'ai depose aucune
sonde. Ce que j'ai verifie par moi-meme sur l'arbre fusionne : les **13 classes nommees existent
toutes** aux chemins qu'il donne, et **36 classes transtypent `(Question)`** - 35 dans
`adapter-content`, 1 dans `domain` - pour 43 occurrences. Son ordre de grandeur tient.

### 5.3 Ce que je tranche, et c'est de la capacite et du sequencement - donc a moi

**a) Le lot 5 est engage, nomme, compte et affecte nominativement.** Il devient un lot de plein
droit du sprint, avec son critere de sortie, et cesse d'etre la finition de `S24-SAI-2`.

**b) Je scinde le lot 5 en deux, et c'est le geste qui ferme le trou.** `theo-archi` ecrit lui-meme
que le tri *"n'est pas mecanisable : il se decide garde-fou par garde-fou, en lisant ce que chacun
promet"*. **Lire ne demande pas que le paquet existe. Seul le vert le demande.**

- **Lot 5a - le tri ecrit.** Pour chacun des treize, une decision ecrite - l'etendre a l'item de
  saisie, ou le borner explicitement - **avec son motif**, tiree de ce que le garde-fou promet.
  Livrable : `tri-des-garde-fous-de-contenu-face-a-un-item-de-saisie.md` dans le repertoire de ce
  sprint. **Ne depend de rien. Demarre au jour 1, en parallele du lot 1.**
- **Lot 5b - l'execution.** Les treize garde-fous etendus ou bornes dans le code, dans la meme
  chaine de commits que le depot du paquet dans `contenu/`. **Depend du lot 5a et du lot 1.**

Le poste dont `theo-archi` ne repond pas devient ainsi le **premier** travail engage du sprint, pas
le dernier decouvert.

**c) La sonde du lot 1 ne descend pas dans `contenu/`.** C'est la consigne de `theo-archi`
lui-meme - paquet de sonde *"sous `src/test/resources`, jamais sous `src/main/resources/contenu`"* -
et j'en fais un **critere de sortie du lot 1**, parce que c'est elle qui empeche le lot 5 de partir
avant que le lot 5a ne soit ecrit.

**d) Le point de bascule, ecrit maintenant plutot que juge sur l'instant.**

> **Declencheur** : le lot 5a n'est pas ecrit - les treize decisions, chacune avec son motif - au
> moment ou les lots 1 et 2 sont fusionnes verts sur la branche de sprint.

S'il se produit, je declare la bascule : **`S24-SAI-2` sort du sprint et `S24-DEN` prend sa place**,
sur le filet que `nadia-prof` a pose et que je n'ai pas a redecouvrir (section 3.3 de son arbitrage :
*"`DEN` prend sa place sans nouvel arbitrage de ma part"*, arbitre depuis le 2026-08-24, huit
conditions ecrites, hors type `COMP-2`, audit qu'elle sait faire vite).

**Pourquoi ce point-la et pas un autre** : c'est le dernier moment ou il reste assez de sprint pour
ecrire les vingt items de `DEN`. Attendre le lot 4 rendrait la substitution nominale.

**Pourquoi le declencheur est ecrit d'avance** : parce que le lot 5a est affecte a moi-meme sous ma
casquette de developpeuse (section 6), et qu'un declencheur que je jugerais sur l'instant serait un
declencheur que je jugerais sur mon propre retard. Il est donc **objectif et verifiable par un
tiers** - un fichier existe avec treize decisions motivees, deux lots sont fusionnes verts - et
**`theo-archi` le constate avec moi** au daily ou il se produit. Il constate un fait ; il n'arbitre
rien, et je ne lui demande rien qu'il n'ait deja ecrit.

**e) Ce que la bascule couterait, et que je ne tranche pas.** Si elle se produit, s24 livre la
mecanique de saisie **non jouable par un enfant** - exactement ce que `DEC-0059`, section 5.3,
refuse d'avance, en citant `DEC-0037` et les quatre sprints clotures sans increment jouable. **C'est
une decision de perimetre et elle appartient a `camille-po`.** Je la lui route **maintenant**, avant
que le sprint ne tourne, en demandant un **amendement ecrit a `DEC-0059`** : la lecon de la cloture
de s21 est qu'une story qu'un sprint absorbe ou abandonne en cours de route s'acte par un amendement
a la decision d'origine, dans le meme geste ; ici l'amendement doit exister **avant** le declencheur,
pas apres. Voir section 9, obstacle 1.

**f) Ce que je ne fais pas.** Je ne decide pas que `DEN` entre au perimetre : le filet de
`nadia-prof` engage **sa porte d'audit**, pas le perimetre du sprint, et elle l'ecrit
(*"le perimetre reste a `camille-po`"*). `S24-DEN` reste **non engagee** a l'ouverture, et
`nadia-prof` est explicite sur sa capacite : quatre paquets (`NAR`, `PLU`, `FRA`, `SAI-2`) plus
l'audit d'une mecanique neuve, **elle est a sa borne, pas en dessous**.

---

## 6. Lots, stories, affectations

L'utilisatrice a ouvert la possibilite de plusieurs stories par developpeur. Trois developpeurs :
`marc-dev`, `lea-dev`, `sofia-sm` (casquette dev). **Neuf items de travail** pour trois personnes.

| Ordre | Item | Nature | Competence visee | Criteres | Affecte a |
|---|---|---|---|---|---|
| 1 | **Lot 5a** - tri ecrit des 13 garde-fous | technique, document | transverse | 13 decisions motivees, aucune muette | `sofia-sm` (dev) |
| 2 | **Lot 1** - forme d'item et chargement | technique | transverse | `ADR-0058` sections 1 et 2, `ADR-0069`, `ADR-0070` ; sonde hors `contenu/` | `marc-dev` |
| 3 | `S24-NAR` | contenu | francais CP, lecture et comprehension de l'ecrit | `NAR-1` a `NAR-10`, plus la section 5.1 de l'arbitrage s24 | `lea-dev` |
| 4 | **Lot 2** - la mecanique | technique | transverse | `SAI-1`, `SAI-3`, `SAI-4`, `SAI-12` ; `R-B`, `R-C`, `R-D` dans le meme commit | `marc-dev` |
| 5 | `S24-PLU` | contenu | francais CP, orthographe et grammaire | `PLU-1` a `PLU-9` | `lea-dev` |
| 6 | **Lot 3** - retypage `adapter-web` et `R-A` | technique | transverse | `EtatDePartie` et `EtatDePartieEnSession` sur `GameSession` ; `R-A` verte | `marc-dev` |
| 7 | `S24-FRA` | contenu | mathematiques CM1, nombres et calculs (couverture partielle nommee) | `FRA-1` a `FRA-9`, plus `FRA-10` neuve (arbitrage s24, 5.2) | `marc-dev` |
| 8 | **Lot 4** - l'ecran de saisie | technique | transverse | second fragment, `input` ajoute aux pressables **avec sa morsure**, `SAI-8`/`SAI-9a`/`SAI-9b`/`SAI-2c`/`SAI-12` rendus | `marc-dev` |
| 9 | **Lot 5b** + `S24-SAI-2` | technique + contenu | aucune ligne neuve | `CPL-1` a `CPL-8` ; les 13 garde-fous verts ou bornes | `sofia-sm` (dev) |
| - | `S24-DEN` | contenu, **non engagee** | mathematiques CP, `en cours` et jamais `couvert-valide` (`DEN-2`) | `DEN-1` a `DEN-8` | `sofia-sm` (dev), **seulement si la bascule est declaree** |

### Le motif de cette repartition

**`marc-dev` porte la colonne vertebrale de la saisie (lots 1 -> 2 -> 3 -> 4) sous une seule tete.**
Cette chaine est strictement serielle et `theo-archi` la juge lot par lot *"largement"*, *"oui"*,
*"le lot le plus mecanique des cinq"* et *"oui si la proposition de rendu existe"* - elle existe
(section 7). La decouper entre deux developpeurs ajouterait trois passages de main sur le chemin
critique et ferait porter `R-A`, `R-B`, `R-C` et `R-D` par des tetes differentes de celles qui
ecrivent leur code, ce qu'`ADR-0058` interdit en substance.

**`S24-FRA` est chez `marc-dev` a cote de la colonne, et c'est la ou l'ordre de priorite de
`camille-po` devient operationnel.** Le contenu prime la saisie en cas de conflit de capacite : en
mettant un paquet de contenu sur la meme tete que la colonne technique, cet ordre a un effet reel -
si `marc-dev` est serre, c'est un lot technique qui glisse, pas `S24-FRA`. Un ordre de priorite
ecrit entre deux personnes differentes ne se serait jamais applique. `S24-FRA` est aussi le plus
calculable des trois paquets : `nadia-prof` n'y garde en propre que `FRA-8` et le niveau.

**`lea-dev` porte les deux paquets de francais CP** (`S24-NAR`, `S24-PLU`) : meme referentiel, meme
niveau, meme fichier de competences, et `S24-NAR` est le paquet du type `COMP-2` du sprint - celui
dont l'audit coutera le plus cher a `nadia-prof`, et le seul autorise par sa regle de composition.

**`sofia-sm` (casquette dev) porte tout ce qui touche le perimetre `contenu/` de la saisie** : le
lot 5a au jour 1, puis le lot 5b et `S24-SAI-2` ensemble. Motif : la personne qui a decide ce que
chacun des treize garde-fous fait d'un item de saisie est celle qui depose le paquet qui les
declenche. Et c'est la tete sur laquelle le declencheur de bascule porte, donc celle qui sent le
retard au lieu d'en entendre parler au daily.

**Ce que cette repartition ne resout pas, et je l'ecris plutot que de le decouvrir** : neuf items
pour trois developpeurs est au-dessus de ce que s23 a porte (trois paquets et six reserves). Le lot
qui glisse en premier est designe d'avance par l'ordre de priorite de `camille-po`, et le point de
bascule de la section 5.3 est le seul mecanisme qui transforme ce glissement en decision plutot
qu'en constat de cloture.

### Ordre de fusion

`theo-archi` route l'ordre de fusion entre le lot de saisie et les lots de contenu, s'ils touchent
les memes fichiers de catalogue. Ils les touchent : `S24-NAR`, `S24-PLU`, `S24-FRA` et `S24-SAI-2`
ajoutent chacun une entree a `catalogue.yaml` et un fichier a `contenu/`.

1. **Lot A d'abord**, dans l'ordre ou chaque paquet est vert - `S24-NAR`, `S24-PLU`, `S24-FRA`. Les
   conflits sur `catalogue.yaml` entre eux sont des ajouts en fin de liste ; je les resous a la
   fusion, jamais l'auteur dans sa branche.
2. **Lot 5a** a tout moment : c'est un document, il ne touche aucun module.
3. **Lots 1, 2, 3, 4 dans cet ordre**, chacun fusionne vert. **Le lot 3 n'est jamais fusionne avec
   le lot 4** - consigne de `theo-archi`, motif ecrit : *"`R-A` est verte ou elle ne l'est pas, et un
   lot qui melange le retypage et le rendu rend cette frontiere illisible."*
4. **Lot 5b et `S24-SAI-2` en une seule fusion, en dernier.** `theo-archi` les declare
   indissociables ; le paquet dans `contenu/` est ce qui rend les treize garde-fous verts ou rouges.

**A chaque fusion, je verifie la lignee du worktree avant de fusionner**, meme si l'agent dit l'avoir
fait : le provisionnement sur la mauvaise branche s'est produit a chaque sprint depuis s03, et c'est
moi qui reponds de ce que je fusionne.

### Discipline de worktree - la confirmation quotidienne s'applique

`squad/README.md` section 9, amendement de la retro de s23 : **a chaque daily, je demande
explicitement a chaque agent en cours de lot de confirmer que son premier commit a bien ete ecrit
dans un worktree dedie.** Cinq occurrences de ce defaut sur le seul s23, toutes autocorrigees, en
plus de s06, s18, s21 et s22. La regle relue au planning ne l'a plus empeche ; c'est le moment de la
verification qui change.

**Constat a l'ouverture de s24 : les quatre agents amont sont en regle.** Quatre worktrees dedies,
quatre branches nommees, lignee verifiee par moi sur les quatre (section 2). C'est le premier sprint
depuis s22 dont l'ouverture ne porte aucune occurrence.

---

## 7. Les dependances ouvertes d'`ines-design`

`theo-archi` posait la proposition de rendu comme la **seule dependance externe du chemin critique**
et la condition 1 de sa faisabilite : *"la proposition de rendu d'`ines-design` existe a l'ouverture,
ou le lot 4 glisse et la saisie n'est pas jouable a la cloture."* **Elle existe sur le disque** -
`proposition-ines-ecran-de-saisie.md`, son prototype, son script de mesure et 80 mesures sur dix
largeurs. **Condition 1 remplie**, constatee et non annoncee.

| | Objet | Traitement au planning |
|---|---|---|
| **`D1`** | Contenu du premier paquet de saisie | **Close.** `nadia-prof` a repondu : complements a 10 au CP, en saisie numerique, `CPL-1` a `CPL-8` (arbitrage s24, section 4.1). Le fichier existe sur le disque |
| **`D2`** | Le mode de clavier de l'item (`${modeDeClavier}`) | **Routee a `theo-archi`**, tranchee dans le **lot 1** et pas apres. Le resultat pedagogique est deja fixe par `CPL-7` (*"entrer un chiffre ne doit pas obliger l'enfant a chercher parmi des lettres"*, resultat fixe, moyen libre) ; le moyen est une cle de schema du contrat de contenu, donc a lui. **`ADR-0070` mord dessus** : une cle ecrite avant que le champ existe est refusee, ce lot est nomme dans l'`ADR` comme le cas vise |
| **`D3`** | Cinq attributs de modele que le controleur ne fournit pas | **Affectee**, sans routage : c'est `adapter-web`, donc le **lot 4**, donc `marc-dev`. `ines-design` demandait une affectation, pas un arbitrage |
| **`D4`** | Longueur maximale d'une reponse a ecrire | **Portee, non bloquante a s24.** `CPL-2` borne la reponse a deux chiffres sur le paquet retenu, donc la question ne mord pas sur cet increment. Elle mordra sur le premier paquet alphabetique. **Routee a `nadia-prof` et `camille-po`** pour ce jour-la |
| **`D5`** | Champ pre-rempli (variante A) ou vide (variante B) apres un refus | **Rendue a `nadia-prof`**, comme `ines-design` le demande. Reponse attendue **avant la fusion du lot 4** : `ines-design` chiffre l'ecart a une ligne si elle arrive avant, et son motif de recommandation (`SAI-9a` dit *corriger*, un champ vide ne se corrige pas) comme son risque nomme (la meme chaine est a un appui d'etre renvoyee identique, et elle consomme un essai) sont tous deux des jugements sur ce qu'un enfant fait |
| **`D6`** | Le nom de la classe `.saisie` | **Routee a `theo-archi`**, a confirmer au lot 2 quand la mecanique portera son nom |
| **`D7`** | Renommage du repertoire de sprint | **Close par ce document**, section 1 |

**Deux points de la proposition qui appellent le regard de `nadia-prof` sur le rendu, pas une
decision de plus** : le champ portera deux marques de focus (l'anneau maison et le contour natif du
navigateur, mesures ensemble sur le prototype), et `ines-design` recommande de garder les deux sans
rien declarer. Elle ecrit elle-meme que c'est une difference visible d'un ecran a l'autre et que
`nadia-prof` doit la voir avant de prononcer quoi que ce soit. Je le porte a l'audit, pas au
planning.

---

## 8. La version de JDK des mesures - `theo-archi` a rendu

`nadia-prof` a constate qu'un fichier de toolchains du poste declare une toolchain `jdk` de version
`21` en designant une racine de JDK 25, ce qui rend sans methode toute phrase du depot affirmant
*"mesure sur le JDK 21 de la toolchain declaree"*. **Je m'etais interdit d'ecrire une version de JDK
dans ce document tant que `theo-archi` n'aurait pas rendu. Il a rendu** : `ADR-0071`, son garde-fou
et son complement sont fusionnes (`bd7161d`).

Ce que j'en reprends, sans le recopier : la version d'une mesure se lit desormais sur ce que **la
machine virtuelle des tests rapporte d'elle-meme**, jamais sur ce qu'une toolchain declare, et
`LaMachineVirtuelleDesTestsPorteLaVersionQueLeBuildExigeTest` verifie a chaque build l'egalite entre
cette version et l'exigence lue dans le pom agregateur - egalite et jamais plancher, un `>=`
laissant passer le defaut meme. Le garde-fou a tourne vert dans le build de la section 2, comme les
810 autres tests.

**Ce que le complement etablit et que je note pour la review** : le chiffre ecrit partout dans ce
depot etait juste, c'est la methode qui ne l'etait pas ; le poste porte deux fichiers de toolchains
divergents, dont un seul est celui que Maven lit ; et l'enveloppe `outils/mvn-jdk21.sh` imprime
depuis `S21-6` (2026-08-27) un avertissement qu'un filtre de lecture ordinaire fait disparaitre.
**Cette dette d'outillage est routee a moi, quatrieme occurrence documentee** : voir obstacle 4.

**Une occurrence de la formule disqualifiee reste sur l'arbre fusionne**, dans la porte technique de
`proposition-ines-ecran-de-saisie.md`. `theo-archi` a corrige ses trois documents et a explicitement
refuse de reecrire ceux des autres agents. **Je ne les reecris pas davantage** : voir obstacle 3.

---

## 9. Ce que ce document route, sans le trancher

| # | Obstacle | Categorie | Destinataire | Artefact attendu |
|---|---|---|---|---|
| 1 | **Si la bascule est declaree, s24 livre une mecanique de saisie non jouable par un enfant** - ce que `DEC-0059` section 5.3 refuse d'avance en citant `DEC-0037`. Le filet de `nadia-prof` couvre sa porte d'audit, pas le perimetre | Produit, perimetre | `camille-po` | **Amendement ecrit a `DEC-0059`**, attendu **avant** que le declencheur ne se produise, pas apres |
| 2 | **Le score de ce produit est parfait par elimination sur 582 des 615 items enumeres** (94,6 %) : `ESSAIS_AVANT_REVELATION` vaut 3, la bonne reponse est testee avant que l'essai soit compte, les propositions essayees restent marquees - un enfant qui presse au hasard a necessairement raison au troisieme essai, compte comme reussite. `nadia-prof` ne retire aucun Done et elargit sa reserve 5 | Produit d'abord, technique ensuite | `camille-po`, puis `theo-archi` si elle tranche dans ce sens | `DEC-XXXX` : score decoratif assume sur les paquets a deux et trois propositions, **ou** `M5` cesse de donner trois essais quand le nombre de propositions les rend suffisants - auquel cas `ADR-XXXX` de `theo-archi` |
| 3 | Une occurrence de *"JDK 21.0.10 de la toolchain declaree"* subsiste dans la porte technique de `proposition-ines-ecran-de-saisie.md`, formule qu'`ADR-0071` disqualifie comme methode | Redaction, methode de mesure | `ines-design` (auteure) | Correction dans son propre document. Je ne reecris pas le document d'un autre agent, `theo-archi` a refuse de le faire pour le meme motif |
| 4 | **Dette d'outillage de `S21-6`, quatrieme occurrence documentee** : le message de `outils/mvn-jdk21.sh` affirme un raccordement qu'il ne verifie pas, et `outils/verifier-jdk.sh` repond sur un autre fichier que celui dont il parle. Cout mesure : a induit en erreur une lectrice competente le 2026-08-30, et a echappe a son auteur le meme jour | Capacite et rang de backlog | `camille-po` pour le rang ; `theo-archi` pour le contenu technique | **Non engagee a s24** : c'est une story d'outillage avec son harnais et son etape de CI, et `theo-archi` refuse a juste titre de l'embarquer dans un lot de conception de la saisie |
| 5 | `DEC-0059` section 1 ecrit *"28 fichiers de `contenu/`, l'ecart d'un est `cm1-reperes.yaml`, present sur le disque et hors catalogue"*. **Sur l'arbre fusionne j'en compte 27**, le catalogue en porte 27, et `cm1-reperes` **est** au catalogue (`bandit-manchot-reperes-cm1`, ligne 105). Le chiffre de 27 jeux qu'elle valide est exact ; c'est la remarque a cote qui ne l'est pas, et elle ne change aucune de ses decisions | Produit, exactitude d'une decision | `camille-po` | Correction dans `DEC-0059`. Methode de mon comptage : `ls contenu/` et `grep -c '^  - id:' catalogue.yaml` sur l'arbre fusionne, section 2 |
| 6 | Le tri du perimetre historique des phrases citant un raccordement de JDK errone - 40 fichiers, 51 occurrences de la formule, mesures par `theo-archi` qui refuse a raison de balayer mecaniquement | Capacite | moi, arbitre au rang de backlog | **Non engagee a s24.** C'est un lot de relecture sur le patron de `S21-6` (amender, jamais reecrire), pas une correction mecanique |
| 7 | `DEC-0058` - outillage des trois etapes calculables de la procedure de specification de `nadia-prof` ; `TYP-1` (le moyen) ; obstacle 25 residuel de s21 (cinq scripts non testes par un juge pur) | Capacite | moi | **Non engages a s24**, au meme rang que l'obstacle 4. La capacite de developpement est prise par les neuf items de la section 6. Ils restent visibles ici pour qu'ils ne disparaissent pas du fil |
| 8 | `LIA-3` integralement, la coherence indice/enonce sur 21 des 24 items d'`ETA`, `SAI-6`, `SAI-7`, `SEUIL-2`, `R1` a `R6` et le comportement d'un clavier virtuel de tablette restent hors de tout garde-fou | Fait a garder visible | - | **Aucune decision demandee.** `nadia-prof` en reste seule garde et le nomme elle-meme (section 8 de son arbitrage). Reconduit ici pour qu'il ne se perde pas |
| 9 | *"Sur la tablette visee, que coute un accent ?"* - ouverte depuis le 2026-08-24 | Question ouverte | l'utilisatrice, par moi | **Ne mord pas sur s24** : le paquet retenu est numerique et `CPL-2` le borne a deux chiffres. `nadia-prof`, `camille-po` et `theo-archi` le disent separement. Mordra sur le premier paquet alphabetique |

### Mon classement de l'obstacle 2, et ce que je n'en fais pas

**Il n'entre pas dans s24**, et le motif est de capacite et de sequencement, donc de ma main : neuf
items sont deja affectes a trois developpeurs, l'objectif est gele, et toucher `M5` ferait bouger le
comportement des 27 jeux existants dans le sprint meme ou une seconde mecanique nait - deux
mecaniques en mouvement a la fois. **Ce que je ne fais pas** : juger si un score parfait par
elimination est acceptable, et juger si `M5` doit changer. Le premier est un arbitrage de produit,
le second en decoule et serait technique. **Je fournis le fait, tel que `nadia-prof` l'a mesure et
verifie dans le code, et je route.**

**Il ne se perdra pas** : il figure au tableau des obstacles de `review.md` et dans `RAPPORT.md` a la
cloture, ouvert tant que la `DEC` n'existe pas sur le disque. Il est nomme candidat a l'ouverture de
s25.

---

## 10. Criteres de sortie du sprint

**Done technique** - juge par la CI, jamais assoupli pour tenir un calendrier :

1. `mvn -B clean verify` a la racine du reacteur, vert sur l'arbre fusionne de cloture. Total de
   tests **etabli par moi sur cet arbre**, avec sa methode, jamais relaye depuis l'annonce d'un
   agent : aucun compte annonce n'a jamais correspondu au total apres fusion, a aucun sprint.
2. **`R-A`, `R-B`, `R-C` et `R-D` ecrites et vertes dans le commit de leur code**, jamais dans un lot
   de rattrapage - condition 3 de `theo-archi`. `R-A` verte signifie zero occurrence de
   `BanditManchot` dans `adapter-web` hors de son point de descente.
3. **L'extension du selecteur des pressables a `input`, avec sa morsure, dans le meme commit que le
   fragment.** Quatre garde-fous derivent aujourd'hui leurs pressables de `button, a[href]` ; sans
   cette extension, un champ sous le plancher de 1,25 rem ou sous la cible de 44 px passerait une CI
   verte. C'est l'omission par enumeration de types, que ce depot a deja payee deux fois.
4. **Les treize garde-fous de contenu, chacun etendu ou borne explicitement avec son motif ecrit.**
   Zero transtypage defensif silencieux : `theo-archi` le refuse d'avance, et treize exemptions
   muettes posees en une fois sont la famille de defaut sanctionnee depuis s05.
5. **Aucun chemin absolu de poste, aucun nom de compte** :
   `git ls-files | bash .github/scripts/verifier-chemins.sh` sur l'index complet, a chaque lot.

**Done pedagogique** - prononce **uniquement par `nadia-prof`**, apres avoir joue l'increment, avec
un motif ecrit dans `squad/conformite/FICHE-<jeu>.md`. **Je ne le prononce jamais et ce document n'en
anticipe aucun.**

- Trois fiches neuves pour `S24-NAR`, `S24-PLU`, `S24-FRA` ; les trois lignes ne passent
  `couvert-valide` que sur son verdict, avec la mention de couverture partielle de `FRA` sous sa
  signature.
- Si `S24-SAI-2` sort : **une fiche neuve**, un Done complet - `nadia-prof` confirme la lecture de
  `camille-po` - et **la ligne "Complements a 10" ne bouge pas**, ce que la fiche doit porter ecrit
  pour que deux entrees sur une meme ligne ne se lisent pas un jour comme une double couverture. Et
  la precision qu'elle demande qu'on n'oublie pas : **un REFUSE sur cette fiche ne touche pas le Done
  de `bandit-manchot-complements-10-cp`.**
- **`CPL-5` est du travail de developpeur dans le lot, pas une remarque** : quatre des onze indices
  du paquet existant nomment la reponse - `0 + ? = 10` (*"il manque tes 10 doigts"*), `5 + ? = 10`
  (*"il en reste 5"*), `9 + ? = 10` (*"compte celui qui manque"*, le singulier dit un),
  `10 + ? = 10` (*"il n'en manque plus aucun"*, qui dit zero). Inoffensif avec trois boutons a
  l'ecran dont la bonne reponse ; **avec un champ de saisie, l'indice donne litteralement la
  reponse**. Les quatre sont a reecrire dans le paquet de saisie ; les sept autres conduisent sans
  nommer et conviennent. Verifie par moi dans `cp-complements-10.yaml` : onze items, et les quatre
  que `nadia-prof` nomme sont bien ceux-la. **Ceci ne rouvre pas le Done du jeu existant**, elle
  l'ecrit explicitement.

---

## 11. Avis de faisabilite de `theo-archi`

`squad/sprints/s24-trois-paquets-et-ouverture-de-la-saisie/avis-faisabilite.md`, complete par
`complement-jdk-declare-contre-execute.md`.

**Verdict** : *"Faisable dans un sprint : l'ouverture de la saisie jusqu'a un jeu jouable de bout en
bout, lots 1 a 4, a trois conditions nommees."*

| Condition | Etat a l'ouverture |
|---|---|
| 1 - la proposition de rendu d'`ines-design` existe a l'ouverture | **Remplie**, constatee sur le disque (section 7) |
| 2 - le lot 5 est compte dans le sprint et non decouvert dedans | **Remplie par ce document** : lot 5a et lot 5b engages, affectes, avec leur point de bascule (section 5.3) |
| 3 - les regles dues sont dans le commit de leur code | **Portee en critere de sortie** (section 10, point 2) |

Ses trois recommandations techniques non tranchees restent a lui et au lot : le type de paquet YAML
neuf plutot qu'une extension du type `question`, la forme du point de descente a deux branches
nommees pour deux mecaniques, et l'entree d'`accent` dans la liste de vocabulaire de matiere -
decidee **au lot 2, mesuree sur les sources de la mecanique, jamais avant**. **Je n'en tranche
aucune.**

Un point qu'il nomme et que le lot 1 doit **decider et non subir** : `ChaquePaquetPorteUnIndiceTest`
compte ses items par les lignes commencant par `- enonce:` ; un item de saisie qui porterait un autre
premier champ ferait rendre zero a ce comptage, et un garde-fou qui borne a zero ne tire rien.
**C'est la seule defaillance muette de tout son tri** - un vert silencieux, pas un rouge.

---

## 12. Decisions prises par ce document

- **Le slug du sprint et le renommage du repertoire provisoire** (`D7`) - mon domaine de
  proprietaire de la ceremonie.
- **La scission du lot 5 en 5a et 5b, le placement du 5a au jour 1, et le point de bascule avec son
  declencheur objectif** - capacite et sequencement, ma ligne de la table de routage.
- **Les affectations nominatives des neuf items**, l'ordre de fusion, et le placement de `S24-FRA`
  sur la meme tete que la colonne technique pour rendre operationnel l'ordre de priorite de
  `camille-po`.
- **Le non-exercice de l'echange `NAR` -> `SUB`**, sur la reponse mesuree de `nadia-prof`, sans
  amender `DEC-0059` comme `camille-po` l'y autorisait.

**Aucune decision de produit, d'architecture ou de pedagogie n'est prise ici.** Les neuf obstacles
de la section 9 sont routes ; aucun n'est clos par ce document, et aucun ne sera clos sur une
intention annoncee - seulement quand le fichier `DEC` ou `ADR` qui le tranche existera sur le
disque.

**Decisions du sprint a l'ouverture, deja sur le disque** : `DEC-0059` (perimetre, statut
*Proposee*), `ADR-0069`, `ADR-0070`, `ADR-0071` (tous *Acceptes*), amendement 4 d'`ADR-0058`.

---

## Amendement du 2026-08-31 (cloture de s24) - trois attributions corrigees sous `DEC-0062`

*Bloc ajoute par `sofia-sm` a la cloture, sur la relecture de `relecteur-pedagogique`
(`relecture-s24.md`, section 4.2). **Rien de ce qui precede n'est efface** : les sections 1 a 12
decrivent l'etat du 2026-08-30 et restent lisibles a leur place. Le patron est celui de `S21-6` -
amender, jamais reecrire.*

### A. La section 4 s'attribuait un chiffre qui n'est pas de moi

**Ce que la section 4 dit** : *"J'ai affirme a l'utilisatrice que l'ouverture de la saisie ferait
passer la couverture de 28 a 33 lignes sur 43. Ce chiffre est faux, **et il est de moi**."*

**Ce qui est faux dans cette phrase, c'est la signature.** Le chiffre ne vient d'aucun agent de cette
squad, et je ne l'ai pas produit : je l'ai **relaye**. Sa provenance exacte est celle-ci :

> Le chiffre de *"28 a 33 lignes sur 43"* est **recu de MASTER, le 2026-08-30, sans source dans le
> depot.**

**Pourquoi cette correction n'est pas cosmetique et pourquoi elle m'incombe.** `DEC-0062` section 3.1
nomme *"il est de moi"* comme la forme **interdite** : elle attribue a un agent identifiable un
enonce qui n'est de personne de cette squad, et elle **efface le canal** au moment meme ou le depot
essaie de le rendre visible. C'est la fausse attribution que `DEC-0062` a ete ecrite pour corriger,
laissee dans le sprint qui l'a creee - **la dette 7 de `CLAUDE.md` sous une autre forme** : une
convention affirmee dans une decision et absente du document qu'elle vise.

**Ce qui ne change pas** : le chiffre reste faux, ses deux bornes restent fausses, et l'arbitrage
rendu dessus tient toujours par les motifs de `DEC-0059` section 5.2. **Ce que je continue d'assumer
sans le deplacer** : je l'ai relaye sans le mesurer, et le mesurer m'aurait pris le meme comptage que
celui de la section 4. **La faute de relais est de moi ; le chiffre ne l'est pas.**

### B. Le verbe de cet enonce - je ne peux pas etablir le mot pour mot, et je le dis

`relecteur-pedagogique` releve que le meme enonce est cite avec deux verbes : *"**fera** passer"*
chez `camille-po` (`DEC-0062` section 1), *"**ferait** passer"* chez moi (ici, `review.md`,
`RAPPORT.md`). Les deux ne peuvent pas etre le mot pour mot, et `DEC-0062` exige le mot pour mot.

**J'ai cherche la source et je ne peux pas la produire.** Le canal MASTER ne laisse rien dans le
depot - c'est sa definition meme - et aucun des deux libelles n'est adosse a un fichier. **Je refuse
de choisir**, pour le motif exact que le relecteur donne : choisir reviendrait a **fabriquer la
citation que la convention protege**, ce qui serait un defaut plus grave que la divergence.

> **Ce que je tranche** : l'incertitude est nommee et non levee. **Le verbe exact de cet enonce n'est
> pas etabli**, deux formulations coexistent dans le depot, aucune des deux n'est certifiee mot pour
> mot, **et l'enonce ne vaut de toute facon jamais motif**. Aucun des deux libelles n'est reecrit :
> les reecrire pour les aligner produirait une citation unique et fausse a la place de deux citations
> divergentes et honnetes.

**Ce que cela apprend sur la convention, et qui vaut plus que ce cas** : la forme 3 exige le mot pour
mot d'un canal qui n'ecrit nulle part. **La citation doit donc etre figee par son premier
destinataire, au moment de la reception**, ou elle ne sera plus figeable ensuite. C'est une limite
reelle de `DEC-0062`, elle est portee au tableau des obstacles a l'attention de `camille-po`, et
**c'est un fait sur la convention, pas une demande de la desserrer**.

### C. Une attribution de forme 1 a `nadia-prof` qui ne dit pas ce qu'elle a ecrit

**Ce que la section 8 dit** : *"`nadia-prof` a constate qu'un fichier de toolchains du poste declare
une toolchain `jdk` de version `21` en designant une racine de JDK 25."*

**Ce que la recherche rend, methode nommee** : `git log -S` sur la chaine, dans les fiches de
conformite. `nadia-prof` **a bien ecrit un constat sur ce sujet**, et il est plus ancien que s24 :

> `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, rubrique *"Moyens, dits franchement"*,
> commit `8877015` du **2026-08-05 (s01)** : *"le poste d'audit ne dispose que d'un JDK 25, pas du
> JDK 21 de la CI."*

**Les deux enonces ne sont pas le meme.** Le sien porte sur **son poste d'audit** et sur ce dont il
dispose. Celui que je lui attribue porte sur **le contenu d'un fichier de toolchains** - une
toolchain declaree `21` qui designe une racine `25` - qui est un fait tout autre, plus precis, et le
seul qui rende `ADR-0071` necessaire. **Cette precision-la n'est nulle part sous sa plume.**

> **Ce que je tranche, et c'est l'issue 2 du relecteur** : la reference de forme 1 est **corrigee en
> deux morceaux**. Le constat de disponibilite du JDK sur le poste d'audit est de `nadia-prof`,
> **forme 1, avec sa reference qui resout desormais** (fiche et commit ci-dessus). La lecture du
> fichier de toolchains est **un quatrieme fait recu de MASTER, le 2026-08-30, sans source dans le
> depot** : je ne peux produire aucun fichier ou elle serait ecrite, et l'affirmer de `nadia-prof`
> serait lui attribuer un enonce qu'elle n'a pas ecrit.

**Ce que cela ne change pas** : `ADR-0071` ne se fonde ni sur l'un ni sur l'autre. `theo-archi` a
**reproduit le mecanisme lui-meme** et son garde-fou verifie l'egalite entre la version que la machine
virtuelle des tests rapporte et celle que le build exige. L'enonce recu a fait chercher ; il n'a rien
fonde, et c'est exactement le regime que `DEC-0062` prevoit.

**Ce que cela dit du tri de la section 4.3 de `review.md`, et qui est neuf** : ce quatrieme fait est
**distinct** de celui de la section 4.2 (*"toutes les mesures locales tournaient sur un JDK 25"*).
Deux enonces recus sur le meme objet, l'un sur les mesures et l'autre sur un fichier de
configuration ; les distinguer est ce qui permet de dire lequel a ete demonte et lequel n'a jamais
ete verifiable.
