# s57 - lecture avant `verify` du premier jet de `ce1-qlm-generations` (`S57-BR-QLM-CE1-12`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : debut 2026-10-27 12:08:48 (UTC+01:00), ecriture 12:23:05, lues par `date` ; fin en section 9.
- **Arbre** : branche `s57-p5-nadia-lecture12`, base `round-two` a `bdfed668`, lue par `git rev-parse`.
- **Commit lu** : `de814b09` (`[MARC]`), premier jet de `marc-dev`, fusionne dans `round-two`.
- **Type** : lecture avant `verify` (`QC7`). **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune condition neuve.
  Je n'ai modifie ni le paquet, ni les tests. Les codes `K*` (corrections), `M*` (reponses a `marc-dev`) et `X*` (garde) sont des
  reperes de lecture de ce fichier.
- **Audit degrade, dit** : rien n'est joue a l'ecran, le paquet n'est pas au catalogue (`QC3`). J'ai joue chaque item sur le papier,
  comme un enfant de CE1, y compris en me trompant (un lien de trop, le frere pris pour un parent, la mauvaise naissance).
- **Lu** : `ce1-qlm-generations.yaml` (12 items) ; `PaquetCe1QlmGenerationsTest.java` ; les jeux fautifs
  `generations-fautives.yaml` et `journee.yaml` ; l'entree de rang du paquet ; story `S57-BR-QLM-CE1-12` (criteres 1 a 9 et leurs blocs
  de corrections) ; mes trois arbitrages de `-12` (premiers jets 10-12, contradictions 10-12, dernier lot 04 a 12, section 6.6 : rien a
  reporter) ; les avis de gardes de `theo-archi` sur `-10`/`-12` (gardes, 2 et 5 ; ajustements, 1 a 3) ; `DEC-0120` A.272, A.273 ;
  mes lectures de `-01` a `-04` ; les voisins `cp-frise-de-la-journee`, `cp-qlm-autrefois-aujourdhui`.
- **Mesures lancees** :
  - recompte par script (Python, hors du depot, non commite) : mots par la regle de `PaquetLu.mots`, longueur stricte de
    `TRV-CE1-2`, sur le paquet puis sur mes textes de remplacement ;
  - `bash outils/mvn-jdk21.sh -B -q -pl adapters/adapter-content -am test -Dtest=PaquetCe1QlmGenerationsTest
    -Dsurefire.failIfNoSpecifiedTests=false`, JDK 21.0.10 (propriete `java.version` du rapport) : **39 tests, 0 echec, 0 erreur**.
    Perimetre : ce seul test, module `adapter-content` et ses dependances. Ce n'est ni un `verify`, ni la porte technique.

## Resume

| Point | Reponse |
|---|---|
| Items | **9 justes, 3 a corriger (6, 9, 12), 0 refuse en l'etat** (section 2) |
| Autocontrole A.273 | **a** (indice qui donne la reponse) : 2 items (6, 12). **b** (distracteur ecarte sans la competence) : aucun item ; un trait de paquet dit en `M8`, sans correction. **c** (source circulaire) : aucune. S'y ajoute un indice qui enseigne une regle fausse (item 9) |
| Proprietes que je garde seule | toutes **tenues** sur le paquet, sauf les deux periphrases de `K1` et `K4` (section 1) |
| Commentaire du paquet | deux passages a recrire (`K5`, `K6`) : un texte de `R12-1` d'avant correction, et une lecture fausse de `R12-5` |
| Reouverture d'une formulation | **oui, une phrase** : `R12-2`, B, suite d'evenements (section 7) ; aucun item actuel n'en est touche |
| Garde | une ligne a `theo-archi` (`X1`, section 6) |
| Cout | **1,0 au bareme** ; 1,5 avec la relecture des corrections (section 8) |

## 1. Ce que j'ai verifie avant les items (ce qu'aucun test ne garde)

- **Exactitude des generations, vues depuis la personne nommee** : les quatre items *de quelle generation* (1, 4, 6, 9) ont chacun
  une seule reponse par les relations ecrites, et chacun dit *Pour X*. Les deux suites de generations (3, 8) disent *Dans la famille
  de X*. Tenu.
- **Suite de prenoms (item 11)** : *Omar a une fille, Julie. Julie a un fils, Noé.* donne un seul ordre, et les trois prenoms sont de
  trois generations differentes (grands-parents, parents, enfants, vus depuis Noé). Tenu.
- **Periphrases dans les indices** : par la parente, l'item 6 (*le fils ou la fille*, sens du mot *enfant*, `K1`) ; par la vie,
  l'item 12 (*le début*, la naissance, `K4`). Aucun indice n'ecrit un mot hors liste (*l'école* seul n'apparait dans aucun indice).
- **Moments de la journee hors des formes de `G12-1`** (*l'après-midi*, *le matin*, *le soir*, *la nuit*, *midi*) : aucun dans aucun
  champ. Tenu.
- **`GEN-CE1-2`, normes familiales** : aucune famille n'est dite complete ou incomplete. L'item 6 nomme un seul parent (Nora) sans
  rien en dire ; les liens sont *maman*, *papa*, *fils*, *fille*, *a une fille*, *a un fils*, repartis entre femmes et hommes (Sara,
  Rose, Jade, Nora, Julie ; Ali, Hugo, Max, Omar). Aucun item ne dit qui vit avec qui ni ne demande sa famille a l'enfant. Tenu.
- **`TRV-CE1-6`, prenoms et roles** : dix-neuf prenoms distincts, d'origines variees ; trois reviennent deux fois (Sara 1 et 2, Lina 1
  et 3, Jade 4 et 12) sans se contredire. Le premier emploi est dans la vie de Sara, Léa, Paul et Jade, la naissance de son enfant dans
  celle de Malik : aucun role n'est attache a un sexe. Aucun item ne suppose un foyer. Tenu.
- **`QMT-CE1-2`** : l'en-tete cite mon arbitrage comme source unique, ni le test ni la story. Tenu (pour `K5`, voir section 5).
- **`CYC-1`, `NRC-CE1-1` au-dela du texte** : aucun paquet de CP ne porte les generations ni les evenements d'une vie (recherche de
  *grands-parents*, *génération*, *arrière-grand* et *naissance* sur `cp-*.yaml`) ; aucun item ne dit ce qu'une generation faisait
  autrefois ; `cp-frise-de-la-journee` n'est contredit par aucun item. Tenu.
- **`TRV-CE1-2`** : bonne reponse strictement la plus longue en caracteres dans 2 items sur 12 (7 et 10), limite 6 ; mesure, comme
  `marc-dev`.
- **Familles de distracteurs a a e** : relues a la main contre les 12 items, toutes conformes a leur declaration. Le texte de la
  famille a porte la lecture fausse de `K6`.

## 2. Verdict par item

Colonnes : juste ; lisible au CE1 ; une seule bonne reponse defendable ; distracteurs ; indice, methode sans la reponse
(`TRV-CE1-5`, `R12-7`). Mots par la regle de `PaquetLu.mots`.

| Item | Tache | Juste | Lisible | Une seule | Distracteurs | Indice | Verdict |
|---|---|---|---|---|---|---|---|
| 1 Ali pour Lina | A, gen | oui | oui, 19 mots | oui | oui : *parents* (Sara, piege), *arrière-grands-parents* | methode | **juste** |
| 2 apres sa naissance | A, evt | oui | oui | oui | table de `R12-5`, ligne 1 | methode | **juste** |
| 3 famille de Lina | B, gen | oui | oui | oui | echange des deux premiers, rotation | methode | **juste** |
| 4 Jade pour Léo | A, gen | oui | oui, 19 mots | oui | oui : *grands-parents* (Max, piege), *arrière-grands-parents* | methode (`M3`) | **juste** |
| 5 avant l'entree a l'ecole | A, evt | oui | oui | oui | table, ligne 2 ; deux *naissance*, bon piege | methode | **juste** |
| 6 Tom pour Mia | A, gen | oui | oui, 19 mots | oui | oui (`M4`) | **periphrase** | **a corriger, `K1`** |
| 7 apres l'entree a l'ecole | A, evt | oui | oui | oui | table, ligne 3 | methode | **juste** |
| 8 famille de Samir | B, gen | oui | oui, mots longs mais de la liste | oui | echange des deux derniers, rotation | methode | **juste** |
| 9 Rose pour Inès | A, gen | oui | oui | oui | oui : *parents* (Hugo, piege) | **regle fausse** | **a corriger, `K2`** |
| 10 avant l'entree au college | A, evt | oui | oui | oui | table, ligne 4 | methode | **juste** |
| 11 Omar, Julie, Noé | B, pre | oui | oui, 19 mots | oui | echange des deux premiers, rotation | methode | **juste** |
| 12 vie de Jade | B, evt | oui | **non** (*dernier moment*) | oui (`X1`) | echange des deux premiers, rotation | **periphrase** | **a corriger, `K3`, `K4`** |

### 2.1 Item 6 : l'indice dit le mot de la reponse par la parente

*"Regarde de qui chacun est le fils ou la fille."* ne contient pas *enfants*, mais *le fils ou la fille* est exactement le sens du mot
*enfant*. L'enfant qui ne raisonne pas sur Nora lit l'indice et prend *celle des enfants* parce que l'indice parle d'enfants. C'est la
periphrase par la parente que `N12-9` nomme (*"Le papa de la maman, c'est le grand-père"*). Le doute de `marc-dev` est fonde.

### 2.2 Item 9 : compter les liens est une regle fausse dans ce paquet

*"Compte les liens entre Rose et Inès."* ne donne pas la reponse : l'enfant doit encore savoir que deux liens vers le haut font les
grands-parents. Mais l'indice enseigne *nombre de liens = ecart de generations*, et l'item 6 du meme paquet le dement : Mia, Nora, Tom,
deux liens, meme generation. Un enfant qui a retenu l'indice de l'item 9 se trompe a l'item 6. Un indice est une methode qui vaut ; une
methode fausse est une erreur de contenu, pas une question de style.

### 2.3 Item 12 : *le dernier moment*, *le début*, *la fin*

- Enonce : *"Dans la vie de Jade, quelle suite va du premier au dernier moment ?"*. *Le dernier moment* d'une vie, c'est la mort (*ses
  derniers moments*), que `R12-1` ecarte des evenements. La formule de `R12-2` est *du premier au dernier* ; le mot *moment* colle a
  *dernier* fait la lecture.
- Indice : *"Raconte la vie de Jade, du début jusqu'à la fin."* *Le début* d'une vie est la naissance, premier element de la suite, dit
  par periphrase (`R12-7`) ; *la fin* d'une vie est encore la mort.

## 3. Reponses aux doutes de `marc-dev`

### 3.1 Doutes (a), les indices

- **`M1`, item 6** : fonde, correction `K1` (section 2.1).
- **`M2`, item 9** : fonde, pour un autre motif que le sien : *2 liens = grands-parents* est le raisonnement attendu, pas la reponse ;
  le defaut est que la regle est fausse sur l'item 6 (section 2.2). Correction `K2`.
- **`M3`, item 4** : *"Écris les noms en colonne, les plus âgés en haut."* **est admis.** Il dit le sens de la colonne, pas la
  generation de Jade ; dans un item *de quelle generation* le sens n'est pas la reponse. Il suppose une feuille, qu'une classe de CE1
  a ; aucune correction.
- **Indice de depart remplace** : le remplacement etait juste (*"Commence par les plus âgés..."* designait le premier element de B,
  `R12-7`) ; *"Compare deux générations à la fois, puis la troisième."* (item 3) est une methode.

### 3.2 Doutes (b), les distracteurs et la forme

- **`M4`, item 6, *celle des enfants*** : la structure est admise. La bonne reponse est la seule proposition qui n'est pas plus agee,
  mais c'est justement ce que l'item demande de savoir (le frere est de la meme generation) ; rien a la surface ne la designe. **Le
  motif de `marc-dev` est inexact** : l'element de reference de `R12-5` est celui que l'enonce nomme apres *juste avant* ou *juste
  après* ; un item *de quelle generation* n'a pas d'element de reference parmi les generations (Mia est une personne). La preuve
  par l'absurde : si *celle des enfants* etait l'element de reference, `R12-5` (*"Aucune [des trois propositions] n'est l'element de
  reference"*) refuserait l'item 6 lui-meme, et `FER-1` deviendrait intenable pour *enfants*. Le test ne porte pas cette lecture
  (`verifierUnItemVoisin` ne lit la reference que sous *juste avant/apres*) ; seul le commentaire la porte : `K6`. *Celle des enfants*
  est donc admise en distracteur ; l'employer ou non est laisse au developpeur, qui le signale dans son rapport.
- **`M5`, items 1 et 9, la generation du milieu** : sur les quatre items *de quelle generation*, la bonne reponse est celle du milieu
  des trois proposees deux fois (1, 9) et la plus jeune deux fois (4, 6). Aucun rang n'est predictible. Admis.
- **`M6`, items 7 et 10, la plus longue** : 2 sur 12, mesure ; sous la limite de 6. Admis.
- **`M7`, les quatre suites** : recompte a la main sur les positions, scores 1 ; 2 ; 1 dans les quatre items, la proposition la plus
  ressemblante est toujours l'echange de voisins, jamais la bonne. Confirme.
- **`M8`, table de `R12-5`** : les quatre items d'evenements sont chacun une ligne de la table, college dans chacun. Le choix de
  `marc-dev` pour les items 7 et 10 est juste et je le confirme avec son motif : avec *la naissance de son enfant*, l'item 7
  porterait *sa naissance* et *la naissance de son enfant* contre *l'entrée au collège*, et la bonne reponse serait la seule sans
  le mot *naissance* ; meme chose a l'item 10. A l'item 5, les deux *naissance* comprennent la bonne reponse : c'est un piege, pas un
  signe.
- **Trait de paquet, dit sans correction** : *celle des arrière-grands-parents* est distracteur dans trois items sur quatre (1, 4, 9)
  et ne peut jamais etre la bonne reponse (`M11`). Un enfant qui rejoue l'apprend ; il lui reste a choisir entre parents et
  grands-parents, ce qui est la competence. Ce n'est pas un distracteur ecarte sans la competence au sens du defaut **b**. Remplacer
  l'un d'eux par *celle des enfants* (admis par `M4`) est laisse au developpeur, qui le signale dans son rapport.

### 3.3 Points interpretes

- **`M9`, generations seulement sous *de quelle generation*** : admis. `R12-2` admet trois formes, il n'en exige aucune.
- **`M10`, chaine de deux liens ou lien frere/soeur** : admis. Le lien frere/soeur est la seule voie vers *enfants* en bonne reponse,
  le personnage etant de la generation des enfants.
- **`M11`, aucune bonne reponse *arrière-grands-parents*** : admis. Trois liens sur la forme de l'exemple de `R12-2` font 25 mots
  (6 + 6 + 6 + 7), au-dela de 20 (`LON-CE1-1`) ; `FER-1` ne l'exige pas (la parenthese du libelle ne le nomme pas).
- **`M12`, `EVENEMENTS` sans rangs** : juste. Un rang lu entre le premier emploi et la naissance de son enfant serait l'ordre que
  `R12-6` refuse ; la table de `VOISINS_ADMIS` porte tout l'ordre utile.
- **`M13`, familles a a e non relues par le test de `-12`** : je les ai relues (section 1). Ce que le format ne voit pas reste a moi.
- **`M14`, `G12-3` et `G12-4` detectent toutes deux la suite fautive** : double detection sur la sonde 34, sans effet sur le
  contenu ; rien a changer.

## 4. Corrections a appliquer par `marc-dev` (texte exact)

Espace insecable (`U+00A0`) avant *?* (`TYP-1`). Mots par la regle de `PaquetLu.mots`.

| Code | Item, champ | Texte actuel | Texte a ecrire | Mots |
|---|---|---|---|---|
| `K1` | 6, `indice` | *Regarde de qui chacun est le fils ou la fille.* | *Pars de Mia et suis les liens jusqu'à Tom.* | 9 |
| `K2` | 9, `indice` | *Compte les liens entre Rose et Inès.* | *Pars d'Inès et remonte d'une génération à la fois.* | 9 |
| `K3` | 12, `enonce` | *Dans la vie de Jade, quelle suite va du premier au dernier moment ?* | *Dans la vie de Jade, quelle suite range les moments du premier au dernier ?* | 14 |
| `K4` | 12, `indice` | *Raconte la vie de Jade, du début jusqu'à la fin.* | *Compare deux moments à la fois, puis le troisième.* | 9 |

Verifie sur mes textes : aucun ne contient une proposition de son item ni une forme fermee de la bonne reponse ou du premier element
(`G12-6`, `TRV-CE1-5`) ; `K3` garde *du premier au dernier* (`G12-3`) ; aucun indice n'est identique a un autre du paquet.

Commentaire du paquet (en ASCII, comme l'en-tete actuel) :

- **`K5`**, ligne *"Evenements d'une vie : naissance, entree a l'ecole, entree au college (admise, pas demandee),"* : remplacer
  *"(admise, pas demandee)"* par *"(admise ; dans chaque item voisin d'evenements, dans aucune suite)"*. Motif : c'est le texte de
  `R12-1` d'avant ma correction du 2026-10-27 (`C6`, `Q8-6`), que le meme en-tete contredit plus bas.
- **`K6`**, phrase *"La proposition "celle des enfants" n'est jamais un distracteur : elle est la generation du personnage nomme,
  c'est-a-dire l'element de reference de l'enonce (R12-5)."* : la remplacer par *"La proposition "celle des enfants" est la generation
  du personnage nomme ; elle est admise en bonne reponse (lien frere ou soeur) comme en distracteur : l'element de reference de R12-5
  est celui que l'enonce nomme apres "juste avant" ou "juste apres"."* Et la ligne de la famille a : *"famille a : tache voisin, une
  autre generation que la bonne reponse"* (retirer *"jamais celle des enfants"*). Motif : `M4`.

Les comptes de l'en-tete (8 et 4) ne changent pas ; aucun item n'est ajoute ni retire, l'entree de rang ne change pas.

## 5. Ce que je ne demande pas

- **Signal au `relecteur-pedagogique`, pas une correction de ma part** : *"les générations du plus âgé au plus jeune"* (items 3, 8)
  accorde au masculin un groupe qui suit un nom feminin. C'est la formule de `R12-2`, que `G12-3` lit a la lettre ; l'accord est de
  son ressort (`DEC-0002`, `DEC-0056`), et toute autre ecriture demanderait d'ajuster `G12-3`.
- Rien sur l'item 4 (`M3`), sur *celle des enfants* en distracteur (`M4`) ni sur *arrière-grands-parents* (`M8`).

## 6. Garde a ajuster par `theo-archi` (une ligne ; la forme est la sienne)

| Code | Garde | Ce que je veux |
|---|---|---|
| `X1` | `G12-3` (`R12-2`, B, suite d'evenements) | sur une suite `evt`, refus du distracteur *"entrée à l'école, premier emploi, naissance"* (la rotation qui met *naissance* en dernier, rangs 1, 2, 0) ; sonde refusee : la suite admise avec cette rotation ; sonde qui passe : l'item 12 du paquet (echange des deux premiers, rotation rangs 2, 0, 1) |

Motif : dans la suite, *naissance* s'ecrit sans possessif (`LON-CE1-5` ne laisse pas *sa naissance* : 7 mots). Place en dernier, juste
apres *premier emploi*, il se lit comme *la naissance de son enfant*, qui est un evenement de la vie de Jade (`R12-1`) ; la suite devient
alors juste, et l'item a deux reponses. L'autre rotation, *"premier emploi, naissance, entrée à l'école"*, ne devient juste qu'en
lisant aussi l'entree a l'ecole comme celle de l'enfant, qui n'est pas un evenement de la vie de Jade : je l'admets. L'item 12 actuel
n'est pas touche.

**Ce dont je reste la seule garde sur `-12`, en plus de ce que mes fichiers et les avis de `theo-archi` listent** : qu'un indice ne
dise pas la reponse par la parente ou par la vie (*le fils ou la fille*, *le début*) ; qu'un indice-methode reste vrai sur tous les
items du paquet ; qu'un enonce n'evoque pas la mort (*le dernier moment*, *la fin*) ; qu'un indice de B ne nomme pas deux elements de
la suite dans leur ordre (la sonde 67, *"Pense aux parents puis aux enfants."*, passe la garde ; elle en marque la limite, je la lis).
**Aucune n'est couverte par un test.**

## 7. Reouverture d'une formulation

**Oui, une phrase, ajoutee a `R12-2`, B** (texte exact, que `camille-po` reporte au critere 9 de `-12` avec le marqueur *[arbitre par
`nadia-prof` le 2026-10-27, `lecture-nadia-premier-jet-ce1-qlm-generations.md`]*) :

> **`R12-2`, B, phrase ajoutee.** Dans la suite d'evenements, *"entrée à l'école, premier emploi, naissance"* n'est jamais un
> distracteur : *naissance*, sans possessif et juste apres le premier emploi, se lit comme la naissance de son enfant, et la suite
> devient juste.

C'est une reserve nouvelle et datee, pas un durcissement de la barre que le premier jet devait tenir : l'item 12 la tient deja. Aucune
autre regle de `-12` n'est rouverte ; `K1` a `K6` appliquent `R12-1`, `R12-5`, `R12-7` et `TRV-CE1-5` tels qu'ecrits.

## 8. Cout

**1,0 au bareme** (lecture du lot avant `verify`, A.272, autocontrole d'A.273 compris). Mes corrections touchent quatre champs et deux
passages du commentaire : leur relecture sera une seconde lecture, **0,5 hors marge** (A.272), soit 1,5 pour `-12`. Je ne deduis aucun
seuil que les textes ne posent pas.

## 9. Duree et verifications

Mesuree par `date` : debut **2026-10-27 12:08:48**, fin **12:29:02**, apres les verifications et avant le commit. **Duree :
20 min 14 s.** Aucune regle ne convertit une duree en bareme.

Verifications avant commit, ce fichier present dans l'arbre. Il n'ecrit ni ne renomme aucun identifiant de condition : `K1`, `M1`,
`X1` sont des reperes de lecture.

- `bash outils/mvn-jdk21.sh -B -q -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
  -Dsurefire.failIfNoSpecifiedTests=false`, JDK 21.0.10 : rapport *tests="10" errors="0" skipped="0" failures="0"*. Perimetre : ce
  seul test, module `bootstrap` et ses dependances ; ni un `verify`, ni la porte technique.
- `.github/scripts/verifier-chemins.sh < /dev/null` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers
  verifies."*, code de sortie 0.
- Aucun autre job de `.github/workflows/ci.yml` lance.

## 10. Routage

| A qui | Quoi |
|---|---|
| `marc-dev` | `K1` a `K6` (section 4) ; dans son rapport, le choix sur *celle des enfants* et *arrière-grands-parents* (`M4`, `M8`) |
| `theo-archi` | `X1` (section 6) |
| `camille-po` | la phrase de `R12-2` (section 7) ; le cout (section 8) |
| `relecteur-pedagogique` | l'accord de *du plus âgé au plus jeune* (section 5) |
| `sofia-sm` | la duree (section 9) |

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
