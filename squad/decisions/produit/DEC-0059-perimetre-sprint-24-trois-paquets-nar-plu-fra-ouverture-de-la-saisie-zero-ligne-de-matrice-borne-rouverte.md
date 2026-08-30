# DEC-0059 - Perimetre du sprint 24 : trois paquets (`NAR`, `PLU`, `FRA`), ouverture de la mecanique
# de saisie qui ne debloque aucune ligne de matrice, borne de capacite rouverte et rendue a `nadia-prof`

- **Type** : decision de perimetre de sprint
- **Date** : 2026-08-30
- **Auteur** : camille-po
- **Sprint** : s24 (ouverture)
- **Competence programme visee** : trois lignes nommees en section 2 pour le lot de contenu ;
  **transverse - pas de contenu pedagogique** pour le lot de saisie (section 5).
- **Statut** : Proposee

## Contexte

L'utilisatrice arbitre l'ouverture de s24 en ces termes, rapportes par `sofia-sm` :
*"ouvre s24 avec les trois paquets et la saisie"*. Cet arbitrage repond a la question que
`DEC-0057`, section 7, lui posait sans y repondre - le **moment** d'ouvrir la mecanique de saisie,
instruite jusqu'au bout depuis s19 (`SAI-1` a `SAI-12`, `R1` a `R6`, `SEUIL-1` a `SEUIL-5`,
`ADR-0058`, `ADR-0059`) et sans une ligne de code a ce jour.

Cette decision pose le perimetre. Elle ne prononce aucun Done pedagogique, ne tranche aucune
question d'architecture, et nomme en section 6 tout ce qu'elle laisse ouvert plutot que de le
laisser implicite.

## 1. Les chiffres de depart, verifies par moi avant d'etre repris

`sofia-sm` m'a transmis quatre chiffres de cloture de s23 en me demandant de ne pas la croire sur
parole. Verification faite sur le worktree `s24-camille`, ouvert sur `round-two` a `ea546cf`.

| Chiffre annonce | Verdict | Methode et perimetre |
|---|---|---|
| 802 tests, 0 echec | **exact** | `bash outils/mvn-jdk21.sh -B clean verify` a la **racine du reacteur**, aucun `-pl`, donc aucune question de `-am` : BUILD SUCCESS. Somme de l'attribut `tests` des fichiers `TEST-*.xml` de `target/surefire-reports/` et `target/failsafe-reports/` sur les cinq modules porteurs de tests = **802**. Aucun fichier ne porte un attribut `failures`, `errors` ou `skipped` non nul. Repartition : `adapter-web` 332, `adapter-content` 288, `bootstrap` 120, `domain` 49, `application` 13 |
| Couverture 24/43 = 55,8 % | **exact** | Comptage des lignes des quatre tableaux de matiere de `squad/programme/MATRICE-couverture.md`, colonne Statut : 24 `couvert-valide`, 1 `en cours`, 18 `non couvert`, 4 `non couvrable en l'etat`, total 47. Denominateur atteignable 47 - 4 = **43**. 24/43 = 55,8 % |
| Catalogue a 27 jeux | **exact** | `grep -c '^  - id:' adapters/adapter-content/src/main/resources/catalogue.yaml` = **27**. A comparer aux 28 fichiers de `adapters/adapter-content/src/main/resources/contenu/` : l'ecart d'un est `cm1-reperes.yaml`, present sur le disque et hors catalogue depuis `DEC-0049`/`DEC-0050`, ce qui est conforme |
| - | **un ecart mineur, sans consequence** | `RAPPORT.md` de s23 ecrit *"les 123 fichiers `TEST-*.xml`"*. J'en compte **122** par la meme methode sur le meme arbre. La **somme** des attributs `tests` est identique (802) et c'est elle qui porte le chiffre annonce ; je le note parce qu'un compte de fichiers cite a cote d'un compte de tests se recopie, pas parce qu'il change quoi que ce soit |

**Aucun des trois chiffres de `sofia-sm` n'est faux.**

## 2. Les trois paquets retenus

| Paquet | Matiere / niveau | Domaine | Ligne de competence visee | Conditions prealables | Type `COMP-2` ? |
|---|---|---|---|---|---|
| `NAR` | francais CP | Lecture et comprehension de l'ecrit | "Comprendre un texte narratif tres court (questions explicites : qui, ou, quoi)" (`squad/programme/francais/competences-cp.md`) | `NAR-1` a `NAR-10` | **oui** |
| `PLU` | francais CP | Orthographe et grammaire | "Reconnaitre et utiliser les marques du pluriel les plus frequentes (`-s`)" (`squad/programme/francais/competences-cp.md`) | `PLU-1` a `PLU-9` | non |
| `FRA` | mathematiques CM1 | Nombres et calculs | "Notion de fraction simple (unites fractionnaires, fractions inferieures a 1) et reperage sur une droite numerique" (`squad/programme/mathematiques/competences-cm1.md`), **moitie "notion" seule, en couverture partielle nommee** | `FRA-1` a `FRA-9` | non |

Les trois conditions vivent dans
`squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md`
(sections 3, 4 et 8bis). **Aucun arbitrage prealable supplementaire n'est du sur ces trois lignes** :
`nadia-prof` les a arbitrees le 2026-08-30, individuellement, avec leurs conditions ecrites pour etre
reprises telles quelles dans des criteres de story - elle le dit elle-meme dans son tableau de
routage.

### Le motif de chaque choix, et ce qui n'est pas mon motif

**`PLU` et `FRA` : ils entrent parce que rien ne les contraint.** Ni l'un ni l'autre n'est du type
`COMP-2` (`nadia-prof`, sections 4 et 8bis : *"ce paquet n'est pas du type `COMP-2` et ne consomme
pas ma condition d'au plus un par sprint"*), tous deux sont arbitres, et leurs conditions sont
presque entierement calculables - elle garde `PLU-1`, `PLU-8` et le niveau sur l'un, `FRA-8` et le
niveau sur l'autre.

**`NAR` entre plutot que `SUB`, et le motif est un desequilibre de couverture, pas un jugement
pedagogique.** Les deux sont du type `COMP-2` et donc mutuellement exclusives dans un meme sprint
(section 3). Etat par niveau au 2026-08-30, compte sur les quatre tableaux de la matrice :

| Matiere / niveau | `couvert-valide` | atteignable | Part |
|---|---|---|---|
| mathematiques CP | 6 | 11 | 54,5 % |
| mathematiques CM1 | 7 | 10 | 70,0 % |
| **francais CP** | **3** | **11** | **27,3 %** |
| francais CM1 | 8 | 11 | 72,7 % |

**Le francais CP est de loin le point le plus faible du produit** - moins d'une ligne atteignable sur
trois, contre pres de trois sur quatre au francais CM1. `NAR` et `PLU` y sont toutes les deux ; les
prendre ensemble deplace la zone la plus creuse de deux lignes en un sprint. `SUB` est en francais
CM1, la zone deja la mieux servie. **Je ne dis pas que `SUB` vaut moins ; je dis qu'elle attend un
sprint dans une zone qui peut se le permettre.**

**Ce que je dois signaler contre mon propre choix, parce que `nadia-prof` l'a ecrit avant que je ne
choisisse** : `NAR` est le paquet dont l'audit lui coutera le plus cher des sept (section 5 de son
arbitrage : `SUB` est *"le paquet du lot dont l'audit me coutera le plus cher **apres `NAR`**"*).
**Si sa capacite d'audit ne tient pas `NAR` a cote de `PLU` et `FRA`, l'echange `NAR` -> `SUB` est
neutre pour toutes mes contraintes** : meme type `COMP-2`, meme regle de composition tenue, meme
compte de trois paquets. Il coute une ligne dans la zone creuse au lieu d'une ligne dans la zone
pleine, et rien d'autre. `sofia-sm` peut faire cet echange au planning sur la reponse de
`nadia-prof`, **sans amender cette decision** - je l'ecris ici pour qu'il n'ait pas besoin d'un
aller-retour.

### `SUB` est reportee, et c'est le premier report

`SUB` ("Reperer les substituts d'un personnage (pronoms, groupes nominaux) dans un texte", francais
CM1) est arbitree, developpable, et **n'entre pas a s24**. C'est un report, et je le compte comme
tel : **premier report, s24**. Motif : la regle de composition de `nadia-prof` interdit de la prendre
avec `NAR`, et j'ai choisi `NAR` pour le motif de desequilibre ci-dessus. Si `SUB` est reportee une
seconde fois a s25 sur un motif a nouveau defendable, cette phrase-ci est ce qui devra etre relu :
`DEC-0007` a tenu la couverture programme fermee trois sprints d'affilee, chaque fois pour une bonne
raison.

## 3. La contrainte de composition de `nadia-prof`, verifiee a la source

**L'affirmation qui m'etait transmise** : *"`NAR` et `SUB` sont toutes deux de type `COMP-2` et donc
mutuellement exclusives dans un meme sprint"*, presentee par `sofia-sm` comme une affirmation de sa
part et non comme une lecture du depot, avec la consigne d'aller la verifier.

**Verification faite. Elle est exacte, et sur les deux moities.**

- La condition existe et elle est de `nadia-prof` :
  *"au plus un paquet par sprint porte une propriete du type de `COMP-2`"*
  (`squad/sprints/s22-couverture-maximale-du-catalogue/avis-nadia-done-pedagogique-s22.md`,
  section 12.3, condition 3 de sa capacite rechiffree ; reprise a l'identique dans `DEC-0057`,
  section 5).
- `NAR` en est : *"`NAR-6` [...] Aucun automate ne voit cette propriete. C'est `COMP-2`, et **ce
  paquet compte donc a lui seul contre ma condition d'au plus un paquet de ce type par sprint**"*
  (arbitrage prealable de s23, section 3).
- `SUB` en est : *"`SUB-6` [...] C'est `COMP-2`, et **ce paquet compte a lui seul contre ma condition
  d'au plus un paquet de ce type par sprint**"* (meme document, section 5).
- Elle-meme les nomme ensemble : *"Trois des sept portent une propriete du type de `COMP-2` -
  `NAR-6`, `SUB-6` et `LIA-3`"* (section 9.2). `LIA` a consomme le quota de s23 et est
  `couvert-valide` depuis le 2026-08-30.

**Elle mord sur ma selection.** Sans cette regle, `NAR` et `SUB` seraient prises toutes les deux et
`FRA` ou `PLU` attendrait. Avec elle, le lot est exactement celui de la section 2.

## 4. La borne de capacite : son motif a disparu, et ce que sa reouverture change reellement

### 4.1 Le motif que j'avais donne n'existe plus

`DEC-0057`, section 5, tenait la borne a trois paquets sur un motif que j'avais ecrit explicitement :
la **condition 1** de `nadia-prof` - *"l'accentuation est gardee par un automate"* - n'etait pas
remplie a l'ouverture de s23, et j'avais refuse de l'anticiper (*"je ne l'ecris pas comme acquise a
l'ouverture, parce qu'aucune de ses trois conditions n'est aujourd'hui verifiee remplie"*).

**Les trois conditions sont remplies aujourd'hui, verifiees une par une sur le depot et non sur un
rapport de sprint :**

1. **Le garde-fou d'accentuation existe et il est vert.** `ADR-0065` (accepte le 2026-08-30) juge
   chaque mot lu par un enfant sur un lexique morphologique exterieur au depot. Trois tests le
   portent, un par surface :
   `adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunMotServiNeSEcarteDeSonAccentTest.java`,
   `adapters/adapter-web/src/test/java/com/recreo/adapter/web/TexteAccentueDesGabaritsTest.java`,
   `bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java`.
   Sa morsure est prouvee sur le materiau reel du defaut de s22 et non sur un cas construit
   (`ADR-0065`, section de mesure).
2. **Les comptes declares par les auteurs** : pratique acquise depuis s22, verifiee exacte sur les
   trois lots par `nadia-prof` elle-meme.
3. **Au plus un paquet du type `COMP-2`** : tenue par ma selection (section 3).

**La reserve qu'elle posait avant de compter sur le garde-fou** - *"je veux aussi savoir **ce que ce
garde-fou ne verrait pas**, avant de compter dessus"* - a sa reponse : `ADR-0065` porte une section
entiere intitulee "Ce que ce garde-fou ne verra pas", et sa premiere entree est la classe des
homographes, mesuree a **232 des 1 331 mots distincts du contenu servi, soit 17 pour cent**.

**Le motif de ma borne a donc disparu, et je ne le reconduis pas.**

### 4.2 Une consequence de `ADR-0065` que personne n'a encore portee a `nadia-prof`, et qui vise mon propre lot

`ADR-0065` ecrit, dans ses consequences : *"17 pour cent du vocabulaire reste juge a l'oeil, **et le
chiffre monte mecaniquement avec la part de texte narratif dans le contenu**"*.

**`NAR` et `SUB` sont exactement du texte narratif.** Le garde-fou qui rend sa condition 1 rend donc
**moins** sur le paquet que je lui propose que sur un paquet de calcul. Je ne sais pas de combien -
la mesure des 17 pour cent porte sur le contenu servi au 2026-08-30, pas sur un paquet qui n'existe
pas encore - et je ne l'estime pas. **Je le nomme parce que c'est un fait qui joue contre ma propre
selection et que personne d'autre ne l'a mis devant elle.** Il entre dans la question de la
section 6.1.

### 4.3 Ce que la reouverture de la borne change a s24 - et l'analyse qui m'etait transmise ne tient pas

**L'analyse transmise par `sofia-sm`** : *"rouvrir la borne ne change rien a s24, parce que la
contrainte mordante n'est plus la borne mais la taille de la reserve arbitree (quatre candidates)
combinee a la regle de composition"*, avec la consigne de la verifier.

**Verification faite. Elle est fausse sur son chiffre, et donc sur sa conclusion.**

**La reserve n'est pas de quatre lignes, elle est de cinq.** Les quatre connues sont exactes -
`NAR`, `PLU`, `SUB`, `FRA`, restes des sept ouvrables de s23 apres consommation de `RAN`, `ETA` et
`LIA`. La cinquieme est **`DEN`** : la moitie *"lire, ecrire et decomposer"* de la ligne "Denombrer,
lire, ecrire et decomposer les nombres jusqu'a 100" (mathematiques CP), **arbitree par `nadia-prof`
le 2026-08-24** sous conditions `DEN-1` a `DEN-8`, et decrite dans cette meme matrice comme
*"candidat ouvrable sans arbitrage supplementaire des qu'une capacite de developpement lui est
affectee"* (`squad/programme/MATRICE-couverture.md`, amendement du 2026-08-24 sous le tableau
mathematiques CP). `DEN` n'est pas du type `COMP-2`.

**Ce que la reserve permet reellement, avec la regle de composition appliquee** :

| Lot | Composition | Regle de composition | Lignes gagnees a `couvert-valide` |
|---|---|---|---|
| 3 paquets | 1 parmi {`NAR`, `SUB`} + `PLU` + `FRA` | tenue | 3 |
| **4 paquets** | **le meme, plus `DEN`** | **tenue** | **3** (`DEN` en gagne **zero**) |
| 5 paquets | impossible depuis cette reserve | - | - |

**La conclusion juste, qui n'est pas celle qui m'etait transmise** : la borne a trois **est encore le
frein**, et elle l'est pour un quatrieme paquet exactement. Ce n'est pas la reserve qui plafonne a
trois, c'est ma borne ; la reserve plafonne a quatre. Ce qui est vrai dans l'analyse transmise, c'est
que la reserve et la regle de composition rendent **cinq** inatteignable, donc que la fourchette de
4-5 de `nadia-prof` ne pourrait au mieux etre utilisee qu'a moitie.

**Et ce que le tableau ci-dessus rend visible et qui ne se voyait pas dans cette discussion** : le
quatrieme paquet possible **ne fait bouger aucun compteur de couverture**. `DEN-2` est explicite et
`nadia-prof` la dit non negociable - *"la ligne reste `en cours` dans la matrice, jamais
`couvert-valide`, meme si je valide le paquet"*, le verbe "denombrer" restant hors de portee de toute
mecanique existante. Rouvrir la borne a s24 ne rapporte donc pas une ligne de plus : **elle rapporte
un jeu de plus jouable par un enfant de CP, sur zero ligne de matrice.**

### 4.4 Ce que je decide, et ce que je rends a `nadia-prof`

- **Le perimetre engage de s24 est de trois paquets de contenu** (section 2), plus le lot de saisie
  (section 5).
- **Je cesse de citer la borne de trois comme adossee a la condition 1** : ce motif est mort, il est
  remplace par celui-ci, plus etroit et verifiable - *trois paquets sont ce que la reserve permet
  d'engager sans depasser une capacite d'audit que `nadia-prof` n'a pas reconfirmee depuis que ses
  conditions sont remplies.*
- **`DEN` est un quatrieme paquet conditionnel**, ecrit en story avec ses criteres
  (`docs/backlog/backlog.md`, `S24-DEN`), **a n'affecter que si `nadia-prof` reconfirme sa borne a
  quatre ou plus pendant le sprint** et si `sofia-sm` a la capacite de developpement. Je ne
  l'anticipe pas et je ne l'engage pas.
- **La question lui est posee en section 6.1**, avec le fait de la section 4.2 qui joue contre ma
  propre selection.

## 5. Ce que l'ouverture de la saisie ajoute au perimetre

### 5.1 Le fait central, verifie ligne par ligne : la saisie ne debloque **aucune** ligne de la matrice

C'est le point que `sofia-sm` me demandait de ne pas laisser implicite. Je l'ai instruit sur les
**18 lignes `non couvert`** de la matrice, une par une, contre les refus prealables que `nadia-prof`
a ecrits pour cette mecanique.

| Ligne `non couvert` | La saisie l'ouvre-t-elle ? | Motif, dans les mots de la source |
|---|---|---|
| Copier un mot, une phrase courte sans erreur (fr. CP) | **non** | `R1` - competence graphomotrice, *"un clavier n'en travaille aucune"* |
| Comprendre un texte narratif tres court (fr. CP) | **non** | `R2` - l'espace des reponses justes ne se compare pas a des chaines. Prise a s24 en **choix** (`S24-NAR`), pas en saisie |
| Lire silencieusement un texte et en restituer l'essentiel (fr. CM1) | **non** | `R2`, meme motif |
| Rediger un texte narratif court et coherent (fr. CM1) | **non** | `R3` - *"un Done pedagogique sur cette ligne par une mecanique de saisie est refuse par avance, quelle que soit la qualite du lot"* |
| Ecrire de maniere autonome un mot simple (fr. CP) | **non** | `R6` (2026-08-25) - avec la correction automatique acceptee sur les deux appareils, c'est l'appareil qui produit les lettres. `nadia-prof` retire elle-meme cette ligne de la liste des candidats : *"elle cesse d'etre un candidat"* |
| Effectuer une multiplication posee (maths CM1) | **non** | Troisieme mecanique, jamais la saisie - `theo-archi` (s13) et `nadia-prof` (2026-08-15) le disent separement, et elle demande de *"ne jamais les compter ensemble dans un arbitrage de sequencement"* |
| Effectuer une division euclidienne simple (maths CM1) | **non** | Meme motif |
| Denombrer, lire, ecrire, decomposer jusqu'a 100 (maths CP) | **non** | La moitie servable est deja ouvrable en **contenu seul** (`DEN`) ; la moitie "denombrer" demande une interaction sur une collection, famille **visuelle** |
| Reconnaitre et utiliser la monnaie (maths CP) | **non** | Famille visuelle |
| Se reperer dans la journee, lire l'heure pleine (maths CP) | **non** | Famille visuelle |
| Se reperer sur un quadrillage simple (maths CP) | **non** | Famille visuelle |
| Reconnaitre et nommer quelques figures planes (maths CP) | **non** | Famille visuelle, `FIG-1` a `FIG-4` |
| Notion de fraction simple et reperage sur droite numerique (maths CM1) | **non** | Moitie "notion" prise a s24 en **choix** (`S24-FRA`) ; moitie "reperage" visuelle |
| Localiser une syllabe dans un mot (fr. CP) | **non** | Famille audio |
| Identifier un son (phoneme) dans un mot (fr. CP) | **non** | Famille audio, source de voix humaine non tranchee |
| Decoder un mot regulier par assemblage syllabique (fr. CP) | **non** | Famille audio |
| Reperer les substituts d'un personnage (fr. CM1) | **non** | Candidate de **choix** (`SUB`), reportee a s25 |
| Reconnaitre et utiliser les marques du pluriel (fr. CP) | **non** | Prise a s24 en **choix** (`S24-PLU`) |

**Aucune ligne des quatre referentiels n'est ouverte par la mecanique de saisie telle que
`nadia-prof` l'a arbitree.** Ce n'est pas un manque d'instruction de ma part : c'est le resultat de
six refus prealables qu'elle a ecrits precisement *"pour qu'aucun lot ne soit jete apres coup"*, et
elle le dit elle-meme sur `R6` - *"aucune autre ligne des quatre referentiels n'est fermee par cet
arbitrage, verifie ligne par ligne"*.

**Le seul usage qu'elle recommande positivement n'est pas de la couverture**, et c'est elle qui
l'ecrit : *"un usage de la saisie que je recommande positivement - la reponse numerique courte, qui
supprime la chance sur les paquets de calcul deja `couvert-valide`. **Ce dernier point n'est pas une
couverture neuve, c'est une amelioration d'existant**, et il lui revient d'en decider la valeur."*

### 5.2 Ce que s24 livre donc, dit sans detour

**s24 livre la mecanique de saisie et, si `nadia-prof` l'arbitre, un premier paquet qui s'en sert -
et ni l'une ni l'autre ne fait bouger un compteur de couverture.** Le mouvement de matrice de s24
vient entierement des trois paquets de choix de la section 2.

**J'assume ce perimetre et j'ecris pourquoi il est legitime plutot que de le presenter comme un
gain :**

1. **Il execute l'arbitrage de l'utilisatrice**, rendu en connaissance du cout nomme par `DEC-0057`.
2. **Il ferme le mur de la mecanique unique**, ouvert depuis s13 : douze conditions pedagogiques,
   six refus de ligne, cinq seuils, deux `ADR` mesures - et zero ligne de code. Une abstraction
   documentee partout et jamais implementee est la **dette numero 1** de la version precedente,
   listee comme telle dans `CLAUDE.md`. La saisie en est aujourd'hui la meilleure candidate du depot.
3. **Il rend une mecanique dont la valeur se mesure ailleurs que sur la matrice** : supprimer la
   chance sur un paquet de calcul rend un score honnete, ce qu'aucun compteur de couverture ne voit.

**Et j'assume ce qu'il coute :** s24 sera le premier sprint depuis s19 ou une part significative de
la capacite de developpement va a quelque chose qui ne fait avancer aucune ligne. Si les trois
paquets de la section 2 devaient etre sacrifies au lot de saisie, ce serait un changement de
direction et non un ajustement - **je pose donc l'ordre : le lot de contenu prime le lot de saisie en
cas de conflit de capacite**, et `sofia-sm` arbitre le detail au planning.

### 5.3 Un premier paquet de saisie, ou la mecanique seule ?

**Je tranche : s24 vise la mecanique jouable, pas la mecanique seule.** Une mecanique qui n'apparait
dans aucune entree de catalogue n'est pas jouable par un enfant, et ce produit a deja ete sanctionne
pour avoir cloture quatre sprints sans increment jouable (`DEC-0037`, cloture de s17).

**Mais je ne choisis pas le contenu de ce premier paquet**, parce que le seul usage recommande vise
des lignes **deja `couvert-valide`** : c'est un jugement de valeur pedagogique sur un jeu qui ne
gagne aucune ligne, et il est a `nadia-prof`. La story `S24-SAI-2` est donc **ecrite et conditionnee
a son arbitrage prealable**, pose des l'ouverture (section 6.2) - exactement la discipline que
`DEC-0057` a etablie apres avoir perdu un sprint a instruire `NAT-5` et `DEN-1` a `DEN-8` une fois le
developpement pret.

## 6. Ce que je ne tranche pas, et a qui je le pose

### 6.1 A `nadia-prof` - la borne, maintenant que ses trois conditions sont remplies

Trois questions, la troisieme portant un fait qui joue contre ma propre selection :

1. **Vos trois conditions du 2026-08-30 sont remplies** (section 4.1) : le garde-fou d'accentuation
   existe, mord sur le materiau reel du defaut de s22, et sa section "Ce que ce garde-fou ne verra
   pas" repond a la reserve que vous posiez avant de compter dessus. **Reconfirmez-vous la borne de
   quatre a cinq paquets ?**
2. **Si oui, elle ne peut etre utilisee qu'a quatre** (section 4.3) : la reserve arbitree n'a que
   cinq lignes et votre regle de composition en exclut une. Le quatrieme possible est `DEN`, qui
   **ne fera bouger aucune ligne** par votre propre condition `DEN-2`. **Vaut-il un audit a ce prix,
   ou preferez-vous garder cette capacite pour s25 ?**
3. **Le fait qui joue contre ma selection** : `ADR-0065` ecrit que la part du vocabulaire qui reste
   jugee a l'oeil - 17 pour cent aujourd'hui - *"monte mecaniquement avec la part de texte narratif
   dans le contenu"*. **`NAR` est du texte narratif.** Le garde-fou qui fonde votre condition 1 rend
   donc moins sur le paquet que je vous propose que sur un paquet de calcul. **Cela change-t-il votre
   borne, ou l'echange `NAR` -> `SUB` que je decris en section 2 ?** Je ne l'estime pas a votre
   place.

### 6.2 A `nadia-prof` - l'arbitrage prealable du premier paquet de saisie

Pose des l'ouverture, jamais quand le developpement sera pret :

- **Sur quel contenu le premier paquet de saisie doit-il porter ?** Votre seule recommandation
  positive vise la reponse numerique courte sur des paquets de calcul **deja `couvert-valide`** (CP,
  complements a 10, additions). Un second jeu sur une ligne deja verte a-t-il une valeur pedagogique
  qui justifie un audit, ou preferez-vous une autre cible ?
- **Un jeu de saisie sur une ligne deja `couvert-valide` demande-t-il un Done pedagogique complet ?**
  Ma lecture : oui - c'est une entree de catalogue neuve, donc une `FICHE-<jeu>.md` neuve - mais la
  ligne de matrice ne bouge pas. **Je ne prononce rien et je vous demande de le confirmer ou de le
  corriger.**
- **Quelles conditions s'ajoutent a `SAI-1` a `SAI-12` et `SEUIL-1` a `SEUIL-5` pour ce paquet
  precis ?** Ces conditions decrivent la mecanique ; elles ne decrivent pas un paquet.

### 6.3 A `theo-archi`, par `sofia-sm` - rien de technique n'est decide ici

`ADR-0058` et `ADR-0059` sont acceptes et je ne les rouvre pas. Ce que je nomme sans le trancher, et
qui lui appartient entierement :

- **`ADR-0058` declare lui-meme que deux de ses trois regles executables ne sont pas ecrites** et
  sont *"dues avec le lot de code"*. Elles font partie du lot, pas d'un sprint ulterieur.
- **Le retypage de l'etat de partie de `adapter-web`** - 20 occurrences de `BanditManchot` sur
  19 lignes dans 3 fichiers, mesure par lui - est le cout connu, deja chiffre. Je ne le rechiffre pas
  et je n'en deduis aucun ordre de grandeur d'effort.
- **L'ordre de fusion** entre le lot de saisie et les trois lots de contenu, s'ils touchent les memes
  fichiers de catalogue.

### 6.4 A l'utilisatrice, par `sofia-sm` - une question ouverte depuis s19 et jamais repondue

**Une lettre accentuee est-elle accessible d'un geste raisonnable sur la tablette visee ?**
`nadia-prof` la pose depuis le 2026-08-24, `DEC-0057` la signale toujours ouverte le 2026-08-30, et
elle conditionne la valeur pedagogique de la **saisie alphabetique** - pas son architecture, que
`ADR-0058` ecrit valable dans les deux cas.

**Ce que je peux dire pour ne pas la faire peser plus qu'elle ne pese** : si le premier paquet de
saisie est **numerique** (l'usage que `nadia-prof` recommande, section 5.1), cette question **ne mord
pas sur s24**. Elle mordra sur le premier paquet alphabetique, quel que soit le sprint. Je ne
recommande pas de bloquer s24 dessus ; je demande qu'elle cesse d'etre reportee en silence.

### 6.5 Ce que je laisse a `sofia-sm` et `theo-archi`, sans ordre de ma part

`DEC-0058` a verse au backlog l'outillage des trois etapes calculables de la procedure de
specification de `nadia-prof`, *"a l'ouverture de s24, au meme rang que `TYP-1` 'le moyen' et
l'obstacle 25 residuel"*. **Je maintiens ce rang et je ne recommande toujours aucun ordre entre les
trois** : ce sont trois chantiers de la meme famille - rendre executable une garantie qui repose sur
la vigilance d'un agent. La capacite qu'ils prennent est celle de `sofia-sm`.

## 7. Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| Les trois questions de borne (section 6.1) | `nadia-prof`, par `sofia-sm` | Une reponse avant l'affectation d'un quatrieme paquet, pas apres |
| L'arbitrage prealable du premier paquet de saisie (section 6.2) | `nadia-prof`, par `sofia-sm` | Pose des l'ouverture, comme `DEC-0057` l'a etabli |
| L'echange `NAR` -> `SUB`, neutre pour toutes mes contraintes (section 2) | `sofia-sm` | Realisable au planning sans amender cette decision, sur la reponse de `nadia-prof` |
| Le lot de saisie : `ADR-0058`, `ADR-0059`, les deux regles executables dues, le retypage | `theo-archi`, `sofia-sm` | Faisabilite et decoupage. Je ne tranche rien de technique |
| L'ordre en cas de conflit de capacite : le contenu prime la saisie (section 5.2) | `sofia-sm` | Le detail est a elle ; l'ordre est de moi |
| La question de l'accent sur tablette, ouverte depuis s19 (section 6.4) | l'utilisatrice, par `sofia-sm` | Ne mord pas sur s24 si le premier paquet est numerique. Qu'elle cesse d'etre reportee en silence |
| `SUB` reportee - **premier report, s24** (section 2) | moi-meme, a relire a l'ouverture de s25 | Un second report demandera un motif ecrit a cote de celui-ci |
| Trois points de satisfaisabilite non instruits sur `NAR` et `FRA` | `nadia-prof`, `marc-dev`/`lea-dev` | Detail dans les stories (`docs/backlog/backlog.md`, `S24-NAR` et `S24-FRA`) : a verifier **avant** d'ecrire vingt items, par l'etape 2 de sa propre procedure |

## 8. Ce que cette decision ne fait pas

- Elle **ne prononce aucun Done pedagogique** et n'en anticipe aucun. Les blocs de validation des
  fiches de conformite restent vides jusqu'a ce que `nadia-prof` les remplisse.
- Elle **ne fait passer aucune ligne a `couvert-valide`**. Les trois lignes de la section 2 passent
  `non couvert` -> `en cours`, ce qui est le geste de planning et rien d'autre.
- Elle **ne tranche aucune question technique** : `ADR-0058` et `ADR-0059` font foi, et ce qui n'y est
  pas est route en section 6.3.
- Elle **n'ouvre aucune fiche de conformite ce jour** : je les ouvre quand un jeu entre en
  construction, ce qui n'est pas le cas a l'ouverture du sprint - les identifiants de jeu et de
  paquet sont encore a confirmer par les developpeurs (voir les stories).
- Elle **ne modifie aucun fichier** sous `domain/`, `application/`, `adapters/` ou `bootstrap/`.

## Impact

- `squad/decisions/produit/DEC-0059-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0059` ajoutee.
- `docs/backlog/backlog.md` : section "Backlog - perimetre s24 (`DEC-0059`)" et six stories
  (`S24-NAR`, `S24-PLU`, `S24-FRA`, `S24-SAI-1`, `S24-SAI-2`, `S24-DEN`).
- `squad/programme/MATRICE-couverture.md` : trois lignes passent `en cours`, amendement date du
  2026-08-30 ajoute sans rien effacer.
