# [Sprint s24] Review - 2026-08-31

- **Horodatage de fin** : 2026-08-31 11:45
- **Horodatage de debut** (rappel du planning) : 2026-08-30 22:32
- **Duree effective** : **13 h 13 min**, une nuit comprise. Un sprint est un increment livrable et
  non une duree ; ce chiffre est un fait de journal, jamais un objectif.
- **Anime par** : sofia-sm (casquette Scrum Master)
- **Branche de review** : `s24-sofia-review`, worktree dedie cree depuis `s24-sofia` (`4352fe8`)
- **Ce document ne prononce aucun Done pedagogique.** Il appartient a `nadia-prof`, elle n'a pas ete
  appelee au moment ou il est ecrit, et aucune ligne de matrice n'y passe `couvert-valide`.

---

## 1. La porte technique, mesuree par moi sur l'arbre fusionne - elle est ROUGE

```
mvn -B clean verify
```

a la racine du reacteur, **reacteur complet, aucun `-pl`, donc aucun `-am` a citer**.

> **BUILD FAILURE.** Le sprint 24 **n'atteint pas son Done technique.**

| Module | Verdict |
|---|---|
| `recreo` (agregateur) | SUCCESS |
| `recreo-domain` | SUCCESS |
| `recreo-application` | SUCCESS |
| `recreo-adapter-web` | SUCCESS |
| `recreo-adapter-content` | **FAILURE** |
| `recreo-bootstrap` | **SKIPPED - non mesure** |

### 1.1 Le compte de tests, avec sa methode et son perimetre

**Methode** : somme des attributs `tests`, `failures`, `errors` et `skipped` de l'element
`<testsuite>` de chaque `TEST-*.xml` de `target/surefire-reports/` et `target/failsafe-reports/`,
sur les rapports produits par ce build apres un `clean`.

**101 rapports, 787 tests, 0 echec, 2 erreurs, 0 ignore.**

**Perimetre de ce chiffre, et il n'est pas le depot** : les **quatre modules qui ont tourne**.
`bootstrap` n'a pas ete execute - le reacteur s'arrete avant lui. Repartition : `adapter-web` 349,
`adapter-content` 347, `domain` 78, `application` 13. **Il n'existe pas de total de depot pour s24**,
et je n'en fabrique pas un par addition d'un `bootstrap` mesure ailleurs : ce serait le defaut meme
que ce depot sanctionne.

**Les chiffres annonces par les agents ne se comparent pas a celui-ci, et je dis d'ou vient
l'ecart.** `marc-dev` annonce 825 tests sur `s24-marc-fra` (base 811 + 14) ; `sofia-sm` casquette dev
annonce 825 sur `s24-sofia-dev` (base 811 + 7 du lot 1 + 7 des siens). **Le meme nombre sur deux
arbres differents, et aucun des deux n'est l'arbre fusionne** : chacun mesure la base plus son propre
lot, comme a chaque sprint depuis s01. Le total de l'arbre fusionne aurait ete superieur aux deux ;
**il n'est pas etabli, parce que le build ne va pas jusqu'a `bootstrap`.**

**Ce que je ne peux pas dire, et je le dis plutot que de le laisser supposer** : je ne sais pas si ce
rouge en cache d'autres dans `bootstrap`. Un `-fae` ne le revele pas - `bootstrap` depend
d'`adapter-content` et reste `SKIPPED`. J'ai tente une sonde excluant le seul test rouge ; elle a
echoue sur un artefact de sa propre construction (`-Dtest=` change le balayage de surefire et bute
sur un fichier de contre-exemple non compile, dans `application`). **Cette sonde ne mesure rien et je
ne rapporte aucun de ses chiffres.** La reponse honnete est : inconnu tant que le defaut n'est pas
corrige.

### 1.2 La cause, nommee et unique a ce stade

```
AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest
  .aucunPaquetServiNeSeGagneEnNeLisantQuUnePhraseDeSonTexteSupport:208->verifier:185->itemsDe:170
  .leDomaineExamineNEstPasVide:216->itemsDe:170

ClassCast class com.recreo.domain.content.ReponseAEcrire cannot be cast to
class com.recreo.domain.content.Question
```

Ligne 170 du garde-fou : `Question item = (Question) tire.orElseThrow();` - **un transtypage nu**.

**Ce qui s'est passe, et personne n'a fait de faute isolee** :

- `theo-archi` ecrit `ADR-0072` et son garde-fou (`1f721df`) sur `s24-theo`, arbre qui portait le
  lot 1 et le lot 5b partiel (`866b4ef`) **mais pas le paquet de saisie dans `contenu/`**. Son
  garde-fou est vert sur son arbre, et `ADR-0072` porte le statut *"Accepte. Son garde-fou est ecrit,
  vert, et mordu sur du materiau reel"*. **C'est exact a sa date et sur son arbre.**
- `sofia-sm` (casquette dev) depose `cp-complements-10-saisie.yaml` dans `contenu/` (`acfecb3`) et
  passe les **quatorze** garde-fous de contenu que le lot 5a avait tries. **Vert sur son arbre.**
- **Le garde-fou d'`ADR-0072` est le quinzieme.** Il n'existait pas quand le lot 5a a ete ecrit, il
  derive son perimetre du repertoire `contenu/` comme les quatorze autres, et il n'a pas recu le
  traitement que le lot 5b a applique aux quatorze : declarer son domaine par rapport a
  `ClassementDesItems`, avec son motif et son assertion de population.

**Deux verts d'agents, mesures chacun sur son arbre, produisent un rouge apres fusion.** C'est la
lecon de cloture de ce depot appliquee dans son sens exact : un vert annonce se reverifie sur l'arbre
fusionne, il ne se relaie pas.

**Je ne corrige pas ce defaut.** C'est le garde-fou de `theo-archi` et son `ADR` ; sous casquette
Scrum Master je constate et je route (obstacle 1 de la section 7). Je n'affaiblis pas l'assertion et
je ne borne pas le garde-fou a la place de son auteur : la lecon de s01 est qu'un conflit ne se
resout jamais en affaiblissant une assertion.

### 1.3 Les autres criteres de sortie du Done technique (planning, section 10)

| # | Critere | Etat mesure |
|---|---|---|
| 1 | `mvn -B clean verify` vert a la racine | **NON TENU** - section 1.1 |
| 2 | `R-A`, `R-B`, `R-C`, `R-D` ecrites et vertes dans le commit de leur code | **Tenu sur les branches**, non reverifiable a la racine tant que 1 est rouge. Les quatre sont dans les commits de leur lot (`1317e4f`, `3dac4f5`), aucun lot de rattrapage |
| 3 | `input` ajoute aux pressables avec sa morsure, dans le meme commit que le fragment | **Tenu** - `c83d4c3`, fragment `saisie.html` et extension des garde-fous de pressables dans le meme commit |
| 4 | Les garde-fous de contenu etendus ou bornes avec leur motif ecrit | **Tenu sur quatorze, non tenu sur le quinzieme.** Zero exemption muette sur les quatorze ; le quinzieme n'est ni etendu ni borne, il casse |
| 5 | `git ls-files \| bash .github/scripts/verifier-chemins.sh` | **Tenu.** *"Aucun chemin de poste de travail ni nom de compte"*, sur l'index complet moins le fichier de cas de test du garde-fou lui-meme, exclusion que la CI applique (`ci.yml`, lignes 59-60) |

**30 regles ArchUnit a l'ouverture, 31 sur l'arbre fusionne.** Methode : comptage des lignes portant
`static final ArchRule` dans les `.java` des quatre arbres de modules, arbre de contre-exemple
exclu. La regle neuve vient du lot 2 (`DecouplageMecaniqueContenuTest`, porteur d'etat type sur une
mecanique).

---

## 2. Ce que le sprint livre

### 2.1 Les trois paquets de contenu - livres, non audites

| Story | Paquet | Entree de catalogue | Etat |
|---|---|---|---|
| `S24-NAR` | `cp-texte-narratif.yaml`, 20 items | `bandit-manchot-texte-narratif-cp` | **Livre**, `lea-dev` |
| `S24-PLU` | `cp-pluriel.yaml`, 21 items | `bandit-manchot-pluriel-cp` | **Livre**, `lea-dev` |
| `S24-FRA` | `cm1-fractions.yaml`, 24 items | `bandit-manchot-fractions-cm1` | **Livre**, `marc-dev` |

**Catalogue : 27 entrees a l'ouverture, 30 sur l'arbre fusionne.** Methode :
`grep -c '^  - id:' catalogue.yaml`. `contenu/` porte **31 fichiers** : les 27 d'ouverture, les trois
paquets ci-dessus, et `cp-complements-10-saisie.yaml` **qu'aucune entree de catalogue ne sert**
(section 2.2). L'ecart de un entre 30 et 31 est exactement ce paquet-la, et il est instruit.

**Un fait de contenu que je verse ici parce qu'aucun garde-fou ne l'a trouve** : `lea-dev` a corrige
sur `S24-NAR`, **en jouant le jar et non par la CI, verte avant comme apres**, une regularite qui
faisait gagner sept items sur vingt sans lire le texte - les sept bonnes reponses commencaient par
la meme famille de prepositions et se lisaient toutes dans la premiere phrase. C'est le refus de
`S12-3` transpose du rang numerique au lexique. **`ADR-0072` de `theo-archi` nait de ce fait** et le
rend executable pour la moitie "premiere phrase" ; la moitie lexicale reste hors garde-fou.

### 2.2 La mecanique de saisie - livree, et non atteignable par un enfant

| Lot | Objet | Commit | Etat |
|---|---|---|---|
| 1 | `ReponseAEcrire`, `ExigenceDeForme`, `SaisieContentSourceFactory` | `67202b9` | Livre |
| 2 | `Saisie`, `SaisieSession`, `SaisieVerdict`, `NormalisationDeSaisie`, `R-B` `R-C` `R-D` | `1317e4f` | Livre |
| 3 | Retypage d'`adapter-web` sur `GameSession`, `R-A` | `3dac4f5` | Livre |
| 4 | Le fragment `saisie.html`, `input` aux pressables avec sa morsure | `c83d4c3` | Livre |
| 5a | Le tri ecrit des garde-fous de contenu | `1e2ac4c` | Livre - 14 decisions motivees, zero muette |
| 5b | Les 14 garde-fous etendus ou bornes, `ClassementDesItems` | `acfecb3` | Livre |
| `S24-SAI-2` | `cp-complements-10-saisie.yaml`, 11 items | `acfecb3` | **Paquet livre, entree de catalogue retenue** |

**Le point de bascule du planning ne s'est pas declenche.** Le lot 5a etait ecrit avant que les lots
1 et 2 ne soient fusionnes verts. `S24-SAI-2` n'est pas sorti du sprint, `S24-DEN` n'y est pas entree
et reste non engagee. L'amendement du 2026-08-31 a `DEC-0059` existe sur le disque et n'a pas eu a
mordre.

**Ce que cela veut dire pour l'enfant, et je l'ecris sans le pallier** : la mecanique de saisie
existe dans `domain`, son ecran existe dans `adapter-web`, son paquet existe dans `contenu/`, et
**rien ne les joint dans le catalogue. Un enfant ne peut pas ouvrir ce jeu.** C'est exactement le cas
que `camille-po` avait pose en question ouverte dans l'amendement A.3 de `DEC-0059` - *"une mecanique
livree et inatteignable est-elle acceptable en l'etat, ou appelle-t-elle un `ADR` ?"* - et il est
maintenant reel au lieu d'hypothetique. Voir mon arbitrage en section 3.2 et l'obstacle 3.

### 2.3 Les decisions et arbitrages du sprint

**`ADR` de `theo-archi`** - tous cotes *Accepte* :

| # | Titre | Etat |
|---|---|---|
| `ADR-0069` | Le mode de forme d'un item de saisie | Accepte, fusionne a l'ouverture |
| `ADR-0070` | Aucune cle de schema d'un paquet servi avant que son champ existe | Accepte, fusionne a l'ouverture |
| `ADR-0071` | La version de JDK citee par une mesure est celle que la JVM des tests rapporte | Accepte, fusionne a l'ouverture |
| `ADR-0072` | Aucune phrase du texte support ne suffit a gagner la majorite d'un paquet | **Accepte, et son garde-fou casse le build apres fusion** - section 1.2 |

**`DEC` de `camille-po`** :

| # | Titre | Statut |
|---|---|---|
| `DEC-0059` | Perimetre de s24 | **Proposee**, corrigee (obstacle 5 du planning) et **amendee** (point de bascule) |
| `DEC-0060` | La porte pedagogique porte sur les elements neufs ou modifies | **Acceptee**, amendee des six reponses de l'utilisatrice |
| `DEC-0061` | Le score parfait par elimination est un defaut a corriger, non un score decoratif | **Acceptee**, non engagee a s24, premier report compte |
| `DEC-0062` | Convention d'attribution d'un enonce recu hors des neuf agents | **Acceptee** - le canal se nomme MASTER, et un enonce recu par lui **ne vaut jamais motif** |
| `DEC-0063` | Sous `Q1`, les 27 jeux deviennent modifies a la cloture ; regime de matrice tranche ; file de reaudit ouverte non engagee | **Acceptee** |

**Arbitrages de `nadia-prof`** - aucun n'est un Done :

- **Clause opposable de la porte pedagogique** : neuf clauses `C1` a `C9`, trois regimes d'audit
  `R-A` / `R-B` / `R-C` dates du 2026-08-31, quatre exigences refusables pour qu'une mesure
  d'invariance la decharge.
- **`ARBITRAGE-FRA`** : elle tient le seuil de rang sans le deplacer d'un point et amende sa propre
  `FRA-5` - famille (e), `n + d`, sur la forme (d) seule. Elle refuse les deux issues de `marc-dev`,
  la seconde par un motif qu'il n'avait pas vu.
- **`ARBITRAGE-D5`** : variante A avec condition ; la forme (b) est retiree le 2026-08-31.
- **`SUITES-s24`** : le lot 1 est discharge sous `R-C` par une preuve de structure, son exigence 2
  est amendee **par ajout** ; le lot 3 ne l'est pas ; `CPL-9` est posee (cinq clauses pour le titre
  et la consigne de l'entree de catalogue de saisie).

**`theo-archi`, `correction-et-verdict-lot-3`** : la troisieme forme tient pour le lot 3 **et** pour
le lot 4 - garde invariablement vraie plus corps identique a l'octet (2423 caracteres, 47 lignes,
compares caractere a caractere). **La decharge reste a `nadia-prof`, il ne la prononce pas.**

### 2.4 Rapport qualite de `yanis-qa` et corrections du `relecteur-pedagogique`

**Aucun des deux n'a ete invoque sur ce sprint.** Ni rapport qualite, ni liste de corrections. Le
rapport de tokens le confirme independamment : *"aucune invocation mesuree sur cette fenetre pour
`yanis-qa`, `relecteur-pedagogique`, `ines-design`"*. C'est un manque de conduite de ceremonie et il
est de moi, pas d'eux - je ne les ai pas appeles. Il part en retro.

`ines-design` a livre **avant** l'ouverture ; son lot est dans la fusion d'ouverture et sa fenetre de
mesure tombe hors du sprint. Son absence du tableau de tokens n'est pas une absence de travail.

---

## 3. Mes arbitrages de Scrum Master

### 3.1 L'ecart de discipline de `marc-dev` - la branche rouge poussee avec son sujet

**Les faits.** `3ba826f`, 2026-08-31 09:31, sujet *"S24-FRA - paquet cm1-fractions et son test -
NE PAS FUSIONNER EN L'ETAT"*. La branche etait rouge sur une assertion et une seule, structurellement
- le rang *"la plus petite"* impossible sur la forme (d). Le corps du message dit l'etat, la cause,
la mesure avec son perimetre nomme, et route l'obstacle. **Il l'a remonte lui-meme, avant que je le
trouve.** Il propose de reecrire l'historique et argumente contre - *"l'effacer reviendrait a retirer
du depot un fait qui a eu lieu"*.

**Ce que je tranche.**

> **L'historique n'est pas reecrit. Le commit `3ba826f` reste tel quel, sujet compris.** Et pousser
> cette branche-la, dans cet etat-la, avec ce sujet-la, **etait le bon geste** - je ne le tolere pas,
> je le valide.

**Trois motifs, dans l'ordre de leur poids.**

1. **La regle qu'il aurait enfreinte en gardant les 722 lignes hors du depot est plus lourde que
   celle qu'il a pliee.** *Le depot Git est la seule source de verite* et *la seule memoire
   partagee* : 722 lignes de contenu specifie vivant dans un worktree local ne sont pas une
   precaution, c'est un travail que personne ne peut lire, que `nadia-prof` ne peut pas instruire, et
   que ce depot a deja perdu deux fois - `DEC-0038` (trois branches, s16) et `DEC-0054` (deux
   branches, s22). `camille-po` a ecarte l'option de la retenue pour ce motif exact dans son
   amendement A.4 a `DEC-0059`, le meme jour : *"une branche retenue est une dette qui disparait de
   la vue de tout le monde"*.
2. **La regle de `mvn verify` vert avant de pousser garde une branche de fusion, pas une branche de
   travail.** Ce qu'elle empeche est un rouge qui entre dans `round-two` sans que personne le voie.
   Un rouge annonce dans le sujet du commit, chiffre dans le corps, et route dans un document
   d'obstacle a l'attention nommee du Scrum Master **ne peut pas entrer sans etre vu** : c'est la
   forme la plus visible qu'un rouge puisse prendre dans ce depot.
3. **Reecrire serait effacer la seule trace du chemin.** Le sprint a gagne a ce rouge : `nadia-prof`
   a amende `FRA-5`, a reverifie le fait par enumeration sur les 45 fractions admises - **0 sur 45,
   impossible et non rare**, plus fort que la mesure de `marc-dev` sur six items - et a ecarte une
   troisieme voie que ni elle ni lui n'avaient nommee, qui aurait sorti le paquet du domaine du
   garde-fou tout entier. **Un depot ou les branches sont toujours vertes est un depot ou l'on ne
   sait pas ce qui a coince.**

**Ce que je ne valide pas, et qui reste vrai** : `e3b8e47` porte la resolution et **c'est ce commit
qui dit l'etat de la branche**. La convention que je pose pour la suite, et qui n'existait pas :

> **Une branche poussee rouge porte l'etat dans le sujet de son commit, la mesure avec son perimetre
> dans son corps, et un document d'obstacle route nominativement. Elle n'est jamais fusionnee tant
> qu'un commit ulterieur n'a pas ecrit qu'elle est verte.** L'historique n'est pas reecrit : un fait
> qui a eu lieu reste dans le depot.

**C'est de la conduite d'equipe, donc de ma ligne de la table de routage**, et cela va en amendement
a `squad/README.md` par la retro - pas ici.

### 3.2 L'ordre de fusion que mon propre planning ne prevoyait pas - l'entree de catalogue de `S24-SAI-2`

**Ce que ma casquette de developpeuse a etabli** (tri des garde-fous, section 5 bis) : deux
garde-fous de `bootstrap` se declenchent a l'**entree de catalogue** et non au depot du fichier, et
l'un d'eux - `LaConsigneDUnJeuServiAtteintLEcranDeMancheTest` - **exige un ecran**, qui n'existe pas
avant les lots 2 et 4. Le planning comptait les deux gestes comme un seul (*"lot 5b et `S24-SAI-2` en
une seule fusion"*). Elle a livre le paquet et **retenu l'entree**, mesure a l'appui : avec l'entree,
`bootstrap` rend 4 echecs ; sans elle, 0.

**Ce que je reprends, maintenant que les lots sont la.** Je verifie les deux conditions de levee une
par une, plutot que de constater que "les lots sont arrives".

| Condition | Etat |
|---|---|
| **Technique** - la mecanique et son ecran existent | **Levee.** Lots 2 (`1317e4f`) et 4 (`c83d4c3`) fusionnes |
| **Pedagogique** - le titre et la consigne sont du texte neuf lu par un enfant, gouverne par aucune condition `CPL` (ligne F du tri) | **Levee le 2026-08-31** par `CPL-9` de `nadia-prof`, cinq clauses `CPL-9a` a `CPL-9e`. Elle ecrit : *"elle n'ecrit pas le texte. Je pose le resultat, `camille-po` et le developpeur ecrivent, et je juge a la porte"* |

**Mon arbitrage.**

> **La retenue est levee sur ses deux conditions. L'entree de catalogue de `S24-SAI-2` est reprenable
> et je l'affecte a un lot nomme - elle n'entre pas dans cette fusion de cloture, et le motif n'est
> ni un oubli ni une prudence.**

**Trois motifs.**

1. **Le texte propose ne tient pas `CPL-9c`.** La consigne ecrite au lot 5b - *"Ecris le nombre qui
   manque pour arriver a 10."* - compte **neuf mots** sous l'unite de compte que `nadia-prof` fixe
   (le mot est une suite de caracteres sans espace, ponctuation isolee non comptee), **contre une
   borne de huit**. Le titre - *"Ecris le complement a 10"* - compte cinq mots et tient `CPL-9c`,
   mais `CPL-9d` (vocabulaire de CP) et `CPL-9a` (distinguable de l'entree existante sans l'ouvrir)
   restent a juger. **Reprendre l'entree telle quelle serait faire entrer au catalogue un texte qui
   contredit une condition rendue le jour meme.**
2. **Ecrire ce texte est du travail de developpeuse, pas de Scrum Master.** Je ne porte pas mes deux
   casquettes dans la meme tache.
3. **La porte technique est deja rouge** (section 1). Ajouter une entree de catalogue sur un arbre
   dont le `verify` ne va pas jusqu'a `bootstrap` produirait un geste dont personne ne peut mesurer
   l'effet - les deux garde-fous qu'elle declenche vivent precisement dans `bootstrap`.

**Ce que je fixe comme sequence, et c'est du sequencement donc de ma main** : le correctif
d'`ADR-0072` d'abord, l'entree de catalogue ensuite, **jamais l'inverse**. Le lot est nomme en
section 7, obstacle 3.

**Ce que je ne fais pas** : je ne decide pas si une mecanique livree et inatteignable est acceptable a
la cloture d'un sprint. `camille-po` a pose la question elle-meme dans son amendement A.3 a
`DEC-0059` et ne l'a pas tranchee ; elle lui appartient, et je la lui rends **avec le fait accompli a
cote**, ce qu'elle n'avait pas quand elle l'a posee.

### 3.3 Les questions de `lea-dev`, restees sans proprietaire

Elles ont ete remontees au daily et le daily n'est pas journalise ; elles entrent ici, c'est leur
place.

**a) Le garde-fou de `PLU-1` est-il mecanisable via le lexique ?** `PLU-1` - *"la phrase ne porte
aucun autre indice de nombre pour le groupe"* - est aujourd'hui tenue par une discipline d'ecriture
(ne jamais mettre le groupe a trou en position de sujet) qu'**aucune clause ne prouve**. `lea-dev`
observe qu'un lexique la rendrait mecanisable.

**Je classe : technique, et je route a `theo-archi`.** Motif : la condition elle-meme est de
`nadia-prof` et elle n'est pas en cause ; ce qui est demande est **si une regle executable peut la
tenir**, ce qui est exactement la question qu'`ADR-0072` a tranchee sur un autre objet le meme jour.
Artefact attendu : un `ADR`, ou un refus motive. **`nadia-prof` reste proprietaire de la condition**
et la reponse technique ne peut pas la modifier.

**b) `nombreDeManches: 10` choisi par alignement.** Les 30 entrees du catalogue portent `10`, sauf
une qui porte `7` (`bandit-manchot-complements-10-cp`, onze items). `lea-dev` a mis `10` sur ses deux
paquets **parce que les autres y sont**, et le signale plutot que de le taire.

**Je classe : produit, et je route a `camille-po`.** Motif : le nombre de manches decide de la duree
qu'un enfant passe sur un jeu et de la part d'un paquet de 20 ou 21 items qu'il voit. Ce n'est ni une
valeur technique ni une condition pedagogique de contenu - c'est un parametre de produit, et **il n'a
jamais ete decide, seulement recopie**. Artefact attendu : une `DEC`, ou une ligne ecrite qui dit que
l'alignement est le choix. **Je ne le tranche pas et je ne demande a personne de changer une valeur
en attendant.**

### 3.4 La file de reaudit de `DEC-0063` - je suis nommee proprietaire du sequencement

`camille-po` m'en nomme proprietaire (section 4.2) et fixe le premier arbitrage de rang **au planning
de s25**. Trois questions me sont posees ; je reponds aux trois.

**a) La file est-elle engagee a s24 ?** **Non**, et je le confirme : elle n'a consomme aucune
capacite de ce sprint et aucun lot ne l'a touchee.

**b) La porte de cloture de s24 peut-elle etre prononcee avec une file de reaudit ouverte ?**
**Oui pour ce qui la concerne** - une dette nommee n'est pas un blocage, et je suis d'accord avec son
raisonnement. **Mais la cloture de s24 ne se prononce pas, et pour un autre motif** : la porte
technique est rouge (section 1). La file de reaudit n'y est pour rien.

**c) Le premier arbitrage de rang.** Il est du au planning de s25 et **je ne l'anticipe pas ici** :
le rendre maintenant serait le rendre sans la reponse de `nadia-prof` a la question 4.1, dont
`camille-po` ecrit qu'elle *"decide si le chiffre reel est 31 ou beaucoup moins"*. **Ce que je fixe
des maintenant, parce que c'est du sequencement et que l'oublier coute plus cher que de l'ecrire trop
tot** :

> **La file de reaudit des 27 est portee au planning de s25 comme un point nomme de son ordre du
> jour, avant l'affectation des lots et non apres.** Elle est comptee ouverte depuis le 2026-08-31.
> **Si elle n'est ni engagee ni arbitree a l'ouverture de s26, c'est un report et il sera ecrit comme
> tel**, sur la discipline de `DEC-0007` que `camille-po` invoque elle-meme.

**Etat de la file a la cloture, mesure sur les documents du disque** : le lot 1 est **discharge** sous
`R-C` par `nadia-prof` (preuve de structure acceptee, exigence 2 amendee par ajout). **Le lot 3 ne
l'est pas** : `theo-archi` a rendu le verdict que la troisieme forme tient pour les lots 3 et 4,
`nadia-prof` a explicitement ecrit qu'elle ne discharge rien sur cette base et que **la decharge reste
la sienne**. Elle n'a pas ete appelee depuis. **La file porte donc 27 jeux, dont aucun n'est discharge
par le lot 3.**

### 3.5 Les deux routages de `theo-archi`

**a) La dette d'outillage de `S21-6` - quatrieme occurrence documentee.** `outils/mvn-jdk21.sh`
affirme un raccordement qu'il ne verifie pas et **imprime au lieu de refuser** ; `outils/verifier-jdk.sh`
repond sur un autre fichier que celui dont il parle. Cout mesure : a induit en erreur une lectrice
competente le 2026-08-30, et a echappe a son auteur le meme jour.

**Ce que je tranche, et c'est de la capacite** : **non engagee a s24**, comme au planning - et je ne
la laisse pas glisser une cinquieme fois sans changer quelque chose au traitement.

> **Cette dette est portee au planning de s25 comme un item de plein droit, avec son harnais et son
> etape de CI, et non comme une finition d'un autre lot.** Motif ecrit : quatre occurrences
> documentees, dont la derniere a coute une erreur de lecture a un agent competent le jour meme de sa
> quatrieme constatation. **Une enveloppe qui imprime au lieu de refuser est un garde-fou qui ne
> garde rien** - la famille de defaut que ce depot sanctionne depuis s05, appliquee a l'outillage au
> lieu du code. Le **rang** reste a `camille-po` (obstacle 4 du planning, inchange) ; ce que je fixe
> est la forme et la place a l'ordre du jour, pas la priorite.

**b) Le tri des fichiers historiques portant la formule de JDK disqualifiee.** 40 fichiers, 51
occurrences, mesurees par `theo-archi`, qui a corrige ses trois documents et **a refuse de balayer
mecaniquement ceux des autres agents**. Il a raison de refuser et je ne le lui redemande pas.

**Ce que je tranche** :

> **Non engage a s24.** Le tri se fera **sur le patron de `S21-6` - amender, jamais reecrire** : un
> document de ceremonie porte ce que son auteur a mesure a sa date, et une correction mecanique en
> ferait un faux retrospectif, exactement ce que la lecon *"ne fabrique jamais une ceremonie qui n'a
> pas eu lieu"* interdit. **Il est porte au planning de s25 avec la dette (a), dont il est le
> jumeau** : la meme cause d'outillage a produit les deux, et les traiter separement ferait corriger
> les symptomes avant la cause.

**Le cas nomme au planning n'est pas clos** : l'occurrence de la formule dans la porte technique de
`proposition-ines-ecran-de-saisie.md` **subsiste** - `ines-design` n'a pas ete invoquee sur ce sprint.
Elle reste routee a son auteure (obstacle 6). **Je ne reecris pas le document d'un autre agent**, pour
le motif exact que `theo-archi` invoque.

### 3.6 L'amendement de `squad/README.md` section 7, route par `camille-po`

La section 7 decrit le Done pedagogique **sans borne de perimetre** : *"prononce par `nadia-prof`, et
par elle seule, avec un motif ecrit"*. Il l'est desormais - `DEC-0060` (acceptee, sur arbitrage de
l'utilisatrice) et la clause opposable de `nadia-prof` (neuf clauses, trois regimes) le bornent.

**Je l'ecris**, et je nomme ce que je fais : **je ne decide rien**. Le perimetre est deja tranche par
une `DEC` acceptee et par celle qui tient la porte ; je transcris dans le fichier de process ce que
les deux ont ecrit ailleurs, parce que `squad/README.md` est ce que les agents lisent et **qu'une
regle de process qui ne dit pas ce que la decision dit est une reference qui pointe vers rien** - la
famille d'ecart que `CLAUDE.md` porte deja en exemple. **L'amendement cite ses deux sources et n'en
interprete aucune.**

**Ce que je ne corrige pas dans cette section, et que je route** : la meme section decrit le Done
technique comme incluant *"integration H2 une fois `adapter-persistence` cree"*, ce que `DEC-0028`
(2026-08-14) a supprime du produit et que `CLAUDE.md` dit explicitement. C'est une phrase fausse dans
le fichier que tous les agents lisent, de la meme famille que celle que je viens de corriger.
**Elle n'est pas dans le perimetre de ce que `camille-po` me route et je ne l'elargis pas de mon
chef** : obstacle 7.

---

## 4. Les trois faits recus de MASTER

Ecrits sous la forme que `DEC-0062` etablit, avec l'enonce cite et la date. **Ils ne sont attribues a
aucun agent, et aucun ne vaut motif.**

### 4.1 Le chiffre de 28 a 33 lignes sur 43

> *"l'ouverture de la saisie ferait passer la couverture de 28 a 33 lignes sur 43"*
> - **recu de MASTER, le 2026-08-30, sans source dans le depot.**

**Ce chiffre a decide l'ouverture du sprint, et ses deux bornes etaient fausses.**

- **La borne haute.** `camille-po` a lu 18 lignes une par une contre les six refus prealables et a
  trouve **zero ligne ouverte** ; `nadia-prof` a refait cette lecture, l'a declaree exacte, et a
  instruit les cinq qui manquaient - **zero sur 23 lignes instruites**, soit toutes celles que la
  saisie pouvait faire bouger. **Deux instructions independantes, la meme valeur.**
- **La borne basse.** Le point de depart n'etait pas 28. **Il etait 24.** Verifie par moi sur l'arbre
  fusionne, methode nommee : comptage de la colonne Statut des quatre tableaux de matiere de
  `MATRICE-couverture.md` - 47 lignes, 24 `couvert-valide`, 4 `en cours`, 15 `non couvert`,
  4 `non couvrable en l'etat`, denominateur atteignable 43.

**L'arbitrage rendu sur ce chiffre tient** : il vaut par les motifs de `DEC-0059` section 5.2, pas
par le chiffre. **Ce qui remplace le 33 est de `nadia-prof`** : *"un enfant qui ne sait rien obtient
aujourd'hui 7 manches sur 7. En saisie, avec trois essais sur onze reponses possibles, il en obtient
au plus trois sur onze par manche. La saisie est la premiere mecanique de ce produit ou un enfant qui
ne sait pas peut echouer."* Elle a demande que cette phrase figure au rapport de sprint ; elle y est.

### 4.2 L'affirmation que toutes les mesures locales tournaient sur un JDK 25

> *"toutes les mesures locales tournaient sur un JDK 25"*
> - **recu de MASTER, le 2026-08-30, sans source dans le depot.**

**Demonte par `theo-archi`** (`ADR-0071` et son complement) : **il y a deux fichiers de toolchains sur
ce poste, Maven lit le bon, et les tests tournent sur un JDK 21.** Le chiffre ecrit partout dans ce
depot etait juste ; c'est la **methode** qui ne l'etait pas - une version lue sur ce qu'une toolchain
declare au lieu de ce que la machine virtuelle des tests rapporte d'elle-meme.

**Ce que l'enonce a produit de bon, et qui n'est pas a son credit** : il a fait chercher, et la
recherche a trouve un vrai defaut de methode, desormais tenu par
`LaMachineVirtuelleDesTestsPorteLaVersionQueLeBuildExigeTest` - **egalite, jamais plancher**, un `>=`
laissant passer le defaut meme.

### 4.3 L'annonce que le chemin C etait ecarte pour s24

> *"le lot 3 n'existe pas, donc le chemin C est ecarte pour s24"*
> - **recu de MASTER, le 2026-08-31, sans source dans le depot.**

**Faux, et mesurable.** `nadia-prof` est allee voir, methode nommee - `git fetch` puis `git log` sur
`origin/s24-marc` :

| Commit | Horodatage | Objet |
|---|---|---|
| `3dac4f5` | 2026-08-31 10:02 | **lot 3** - retypage d'`adapter-web` sur `GameSession` |
| `2989bc5` | 2026-08-31 10:16 | la reponse d'invariance de `theo-archi`, qui recense huit branches sans `s24-marc` |

**Le lot 3 etait pousse quatorze minutes avant que la phrase qui dit qu'il n'existe pas ne soit
ecrite.** Le recensement de `theo-archi` etait exact sur ce qu'il voyait, **et il en donnait la
methode - c'est parce qu'il l'a donnee que l'ecart se voit**. Il a rendu le verdict a nouveau sur un
instant nomme (`git fetch` du 2026-08-31 a 10:59:21, treize references vues), avec la reserve qu'il
porte lui-meme : *"une phrase d'invariance porte une date et un instantane, ou elle ne porte rien"*.

**Ce que cet enonce a fait, et c'est le pire des trois** : il a ete **relaye sans etre verifie contre
le recensement dont il pretendait decouler**. Deux enonces - *"le lot 3 n'existe pas"* et *"donc le
chemin C est ecarte"* - ont circule comme vrais alors qu'aucun des deux n'avait ete mesure. Le second
serait entre dans la porte de `nadia-prof` et y aurait referme 27 jeux sur un lot que personne n'avait
regarde. **C'est exactement ce que la borne "ne vaut jamais motif" de `DEC-0062` existe pour arreter**,
et c'est la lecon que la retro porte.

**Consequence sur la porte** : *"le chemin C est ecarte pour s24"* **n'est pas etabli**, et le lot 3
n'est discharge par personne a ce jour.

---

## 5. Etat de la matrice de couverture

**Methode** : comptage de la colonne Statut des quatre tableaux de matiere de
`squad/programme/MATRICE-couverture.md` sur l'arbre fusionne. **Perimetre : les quatre tableaux de
matiere, pas les tableaux de synthese historiques du meme fichier.**

| Matiere | couvert-valide | en cours | non couvert | non couvrable | lignes |
|---|---:|---:|---:|---:|---:|
| Mathematiques CP | 6 | 0 | 5 | 0 | 11 |
| Mathematiques CM1 | 7 | 1 | 2 | 2 | 12 |
| Francais CP | 3 | 3 | 5 | 1 | 12 |
| Francais CM1 | 8 | 0 | 3 | 1 | 12 |
| **Total** | **24** | **4** | **15** | **4** | **47** |

**24 / 43 atteignables, soit 55,8 %. Identique a l'ouverture. Aucune ligne n'a bouge.**

**Ce n'est pas un echec du sprint** : trois lignes sont `en cours` et attendent un verdict qui n'a pas
ete demande. L'objectif du planning - *"si les trois aboutissent sans cycle de refus, 27/43 (62,8 %)"*
- etait ecrit comme un objectif et **jamais comme un Done**. Il n'est pas atteint parce que la
ceremonie d'audit n'a pas eu lieu, pas parce que le contenu manque.

**Ce que la matrice ne porte pas encore et qui lui est du** : la mention datee de `DEC-0063`,
section 3, sur les lignes concernees par les lots 1 et 3. `camille-po` ecrit qu'elle la portera **au
moment de la fusion des lots**, qui vient d'avoir lieu. **C'est de sa main, pas de la mienne** :
obstacle 5.

---

## 6. Amendement a `squad/README.md`, section 7

**Ecrit dans ce geste**, sur le routage de `camille-po`. Il ajoute au paragraphe du Done pedagogique
la borne de perimetre que `DEC-0060` et la clause opposable de `nadia-prof` ont posee, en citant les
deux sources et sans en interpreter aucune. Le texte anterieur n'est pas reecrit : la borne est
ajoutee a la suite et datee, comme les amendements de s05 et s06 de la meme section.

---

## 7. Tableau des obstacles du sprint

**Ouvert = le fichier `ADR` ou `DEC` qui le tranche n'existe pas sur le disque.** Aucun obstacle n'est
clos ici sur une intention annoncee.

| # | Obstacle | Categorie | Proprietaire | Artefact attendu | Etat |
|---|---|---|---|---|---|
| 1 | **Le garde-fou d'`ADR-0072` transtype `(Question)` et casse le build apres fusion** - quinzieme garde-fou de contenu, hors du tri du lot 5a. `adapter-content` FAILURE, `bootstrap` non mesure | Technique | `theo-archi` | Correctif dans son garde-fou sur le patron de `ClassementDesItems` : domaine declare, motif ecrit, assertion de population. **Jamais un affaiblissement d'assertion.** Amendement a `ADR-0072` si son perimetre change | **OUVERT - bloque le Done technique** |
| 2 | **Le lot 3 n'est discharge par personne.** `theo-archi` a rendu son verdict, `nadia-prof` ecrit que la decharge reste la sienne et n'a pas ete appelee | Pedagogie | `nadia-prof` | Avis ecrit : decharge sous `R-C`, ou chemin B, ou chemin C | **OUVERT** |
| 3 | **`S24-SAI-2` est livre sans entree de catalogue : la saisie n'est atteignable par aucun enfant.** Les deux conditions de retenue sont levees ; le texte propose ne tient pas `CPL-9c` (neuf mots contre huit) | Sequencement, puis produit | **moi** pour l'affectation du lot ; **`camille-po`** pour la question de fond | Lot nomme : titre et consigne sous `CPL-9a` a `CPL-9e`, entree au catalogue, inventaire du port entrant etendu. **Apres l'obstacle 1, jamais avant.** Et la reponse de `camille-po` a sa propre question A.3 de `DEC-0059` : une mecanique livree et inatteignable est-elle acceptable, ou appelle-t-elle un `ADR` ? | **OUVERT** |
| 4 | **Le score parfait par elimination sur 582 des 615 items** (94,6 %). `DEC-0061` **existe et est acceptee** : c'est un defaut a corriger et non un score decoratif a assumer | Produit tranche, technique a suivre | `theo-archi` | `ADR` sur `M5` - le nombre d'essais cesse d'etre 3 quand le nombre de propositions le rend suffisant. **Non engage a s24, premier report compte** | **PRODUIT CLOS, TECHNIQUE OUVERT** |
| 5 | La mention datee de `DEC-0063` section 3 est due sur les lignes de matrice concernees, *"au moment de la fusion des lots 1 et 3"* - elle a eu lieu | Produit | `camille-po` | Mention portee dans `MATRICE-couverture.md` | **OUVERT, echu** |
| 6 | Une occurrence de *"JDK 21.0.10 de la toolchain declaree"* subsiste dans la porte technique de `proposition-ines-ecran-de-saisie.md`, formule qu'`ADR-0071` disqualifie | Redaction, methode de mesure | `ines-design` (auteure) | Correction dans son propre document. Je ne reecris pas le document d'un autre agent | **OUVERT** - elle n'a pas ete invoquee sur ce sprint |
| 7 | **`squad/README.md` section 7 decrit le Done technique comme incluant *"integration H2 une fois `adapter-persistence` cree"***, supprime du produit par `DEC-0028` le 2026-08-14 | Redaction, doc | `relecteur-pedagogique` ; `camille-po` si le fond bouge | Correction citant `DEC-0028`. Hors du perimetre de ce que `camille-po` m'a route, je ne l'elargis pas de mon chef | **OUVERT** |
| 8 | **Dette d'outillage de `S21-6`** - l'enveloppe imprime au lieu de refuser, `verifier-jdk.sh` repond sur un autre fichier. Quatrieme occurrence documentee | Capacite ; rang | **moi** pour la capacite ; `camille-po` pour le rang | **Portee au planning de s25 comme item de plein droit** (section 3.5.a). Story avec son harnais et son etape de CI | **OUVERT, sequence** |
| 9 | **40 fichiers, 51 occurrences de la formule de JDK disqualifiee.** `theo-archi` refuse a raison de balayer en aveugle | Capacite | **moi** | **Portee au planning de s25 avec l'obstacle 8**, patron de `S21-6` : amender, jamais reecrire | **OUVERT, sequence** |
| 10 | **La file de reaudit des 27 jeux** (`DEC-0063`). Lot 1 discharge, lot 3 non | Sequencement | **moi** | **Point nomme de l'ordre du jour du planning de s25, avant l'affectation des lots.** Comptee ouverte depuis le 2026-08-31 ; report ecrit si rien a l'ouverture de s26 | **OUVERT, sequence** |
| 11 | **La question 4.1 de `DEC-0063`** : un reaudit apres retypage coute-t-il un paquet contre la borne de 4 a 5 ? *"Elle decide si le chiffre reel est 31 ou beaucoup moins"* | Pedagogie | `nadia-prof` | Avis ecrit. **Si la reponse est "un paquet", la regle et la borne sont arithmetiquement incompatibles et le fait retourne a l'utilisatrice avec les deux chiffres cote a cote** | **OUVERT** |
| 12 | **Le garde-fou de `PLU-1` est-il mecanisable via le lexique ?** La condition est tenue par une discipline d'ecriture qu'aucune clause ne prouve | Technique | `theo-archi` | `ADR`, ou refus motive. **`nadia-prof` reste proprietaire de la condition** | **OUVERT** (section 3.3.a) |
| 13 | **`nombreDeManches: 10` n'a jamais ete decide, seulement recopie** - 29 entrees a 10, une a 7 | Produit | `camille-po` | `DEC`, ou une ligne ecrite qui dit que l'alignement est le choix | **OUVERT** (section 3.3.b) |
| 14 | **`D4`** - longueur maximale d'une reponse a ecrire. Ne mord pas sur s24 (`CPL-2` borne a deux chiffres), mordra sur le premier paquet alphabetique | Pedagogie, produit | `nadia-prof`, `camille-po` | Reponse le jour ou un paquet alphabetique entre | **OUVERT, non echu** |
| 15 | **`D5` - deux points restent ouverts** apres l'arbitrage : la place de la liste des tentatives (deplacee au-dessus du champ par le lot 4, **deplacement non mesure**, le releve des dix largeurs est a refaire) et le trait lui-meme | Design | `ines-design` | Releve refait sur la disposition reelle ; avis sur la place | **OUVERT** |
| 16 | **Le champ portera deux marques de focus** (anneau maison et contour natif du navigateur), mesurees ensemble, sans rien declarer. `ines-design` demande que `nadia-prof` le voie avant tout verdict | Pedagogie, a l'audit | `nadia-prof` | Regard porte a l'audit, pas une decision de plus | **OUVERT, porte a l'audit** |
| 17 | `LIA-3`, la coherence indice/enonce sur 21 des 24 items d'`ETA`, `SAI-6`, `SAI-7`, `SEUIL-2`, `R1` a `R6`, le clavier virtuel de tablette : hors de tout garde-fou | Fait a garder visible | - | **Aucune decision demandee.** `nadia-prof` en reste seule garde et le nomme elle-meme | **RECONDUIT** |
| 18 | *"Sur la tablette visee, que coute un accent ?"* - ouverte depuis le 2026-08-24 | Question ouverte | l'utilisatrice, par moi | Ne mord pas sur s24 (paquet numerique, `CPL-2`). Mordra sur le premier paquet alphabetique | **OUVERT, non echu** |
| 19 | **`DEC-0058`, `TYP-1`, obstacle 25 residuel de s21** (cinq scripts non testes par un juge pur) | Capacite | **moi** | Non engages a s24, au rang des obstacles 8 et 9 | **OUVERT** |

### Obstacles du planning clos par ce sprint, avec le fichier qui les clot

| Objet | Ce qui l'a clos, sur le disque |
|---|---|
| Obstacle 1 - la bascule livrerait une saisie non jouable | **`DEC-0059`, amendement du 2026-08-31**, ecrit **avant** le declencheur comme demande. La bascule ne s'est pas produite |
| Obstacle 5 - `DEC-0059` annonce 28 fichiers de `contenu/` pour 27 | **`DEC-0059` corrigee** par `camille-po` (`3b53786`) |
| `D2`, `D6` - mode de clavier, nom de la classe | Tranches dans les lots 1 et 2 ; `ADR-0069` et `ADR-0070` mordent dessus |
| `D5` - champ pre-rempli ou vide apres un refus | **`ARBITRAGE-D5` de `nadia-prof`** : variante A avec condition, forme (b) retiree. Deux points de forme restent (obstacle 15) |
| L'obstacle de `marc-dev` sur `FRA-2` forme (d) | **`ARBITRAGE-FRA` de `nadia-prof`** : `FRA-5` amendee, famille (e). La branche est passee verte |
| La clause opposable de la porte pedagogique, due par `DEC-0060` | **Ecrite par `nadia-prof`**, neuf clauses, sur le disque |

---

## 8. Ce que la review etablit, en clair

- **Done technique : NON ATTEINT.** `mvn -B clean verify` a la racine rend BUILD FAILURE.
  `adapter-content` en echec sur deux erreurs, `bootstrap` non mesure. Une cause unique identifiee et
  routee a `theo-archi`. **Je n'assouplis pas la definition du Done pour tenir une cloture.**
- **Done pedagogique : NON PRONONCE, et il ne l'est pas ici.** `nadia-prof` n'a pas ete appelee.
  Trois paquets neufs et un paquet de saisie l'attendent, plus l'audit d'une mecanique neuve ; elle a
  ecrit etre **a** sa borne et non en dessous.
- **Livre** : trois paquets de contenu (65 items), la mecanique de saisie complete de `domain` a
  l'ecran, quatorze garde-fous de contenu tries et executes, quatre `ADR`, cinq `DEC`, la clause
  opposable de la porte pedagogique.
- **Non livre** : la saisie **jouable** - le paquet existe, l'ecran existe, l'entree de catalogue qui
  les joint n'existe pas.
- **Matrice : 24/43 (55,8 %), inchangee.** Trois lignes `en cours` attendent un verdict.
- **Dix-neuf obstacles ouverts**, chacun avec son proprietaire nomme. Six obstacles du planning clos
  par un fichier qui existe sur le disque.
