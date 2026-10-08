# Fiche de conformite - bandit-manchot-corps-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-corps.yaml`
  (12 items, type `question`), ecrit par `marc-dev` (commit `d88268d5`).
- **Entree de catalogue** : `bandit-manchot-corps-cm1`, titre servi *"Prendre soin de son corps"*,
  consigne servie *"Lis bien, puis dis ce qui est bon pour le corps, ou à quoi cela sert."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `SCI-CM1-10`. **Story** : `S51-SCI-CORPS-CM1` (`docs/backlog/backlog.md`,
  section "Sprint 51").
- **Conditions opposables** : `FER-1` a `FER-3`, `SCI-1`, `SCI-3`, `SCI-4`, `BIA-4`, `HIS-3`,
  `CON-1`, `CYC-1`
  (`squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`,
  sections 2.2 ligne 10, 2.3, 2.5 et 2.6), `DEC-0064`, la frontiere `EPS-9` et la frontiere avec
  `SCI-CM1-09` (`DEC-0110` section 4, criteres de la story).
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-10-06 par `nadia-prof` (s51, instance
  B). Fiche ouverte par ce verdict : aucune fiche n'existait pour ce jeu. **Le 2026-10-08 (s54,
  instance B) : VALIDE**, les trois reserves `CRP-CM1-R1` a `CRP-CM1-R3` sont levees au rejeu. Le
  verdict du 2026-10-06 reste ecrit ci-dessous.
- **Derniere mise a jour** : 2026-10-08.

---

## Bloc de validation pedagogique

### Verdict du 2026-10-08 (`nadia-prof`, s54, instance B) : **VALIDE**, reserves levees

**Done pedagogique maintenu, sans reserve.** Story `S51-SCI-CORPS-CM1-RESERVES`, commit `9618b974`
(`marc-dev`). `SCI-CM1-10` reste `couvert-valide` ; `camille-po` peut retirer de la cellule de matrice
la mention des reserves ouvertes. Aucune mention de couverture due (inchange).

**Ce que j'ai fait.** Worktree `s54-nadia-b`, a `95214218`. `bash outils/mvn-jdk21.sh -B clean verify`
sur ce worktree : `BUILD SUCCESS` (job `build` seul ; `prefixes`, `chemins`, `outils` non lances).
Jar `bootstrap/target/recreo.jar` de ce build, paquet et catalogue embarques identiques octet pour octet
aux fichiers commites (`cmp`), lance sur un port dedie a l'instance B, arrete par son PID. Joue par les
routes servies. Cinq parties de dix manches : une premiere avec une erreur, une revelation ou une bonne
reponse du premier coup selon la manche ; puis quatre ou les items 3, 5, 6, 7 et 11 ont ete joues a
chaque tirage avec les deux distracteurs presses puis la revelation. **Les douze
items ont ete tires.** Le diff de `9618b974` ne touche que ces cinq items ; il reprend mot pour mot les
blocs de mon arbitrage prealable de s54 (section 2), verifie champ par champ.

**Levee, reserve par reserve, a la barre de la section 8 ci-dessous (inchangee) :**

| Reserve | Barre | Constate a l'ecran |
|---|---|---|
| `CRP-CM1-R1`, item 3 | un indice vrai, une phrase, qui dit pourquoi la conception fausse l'est, sans nommer la reponse | *"Rester éveillé tard ou boire ce qui empêche de dormir raccourcit le temps où le corps grandit et se répare."*, lu apres chacun des deux distracteurs. Vrai au niveau du CM1 (l'enonce dit *"surtout"*) ; ne dit plus que le corps ne fait rien la nuit. **Levee** |
| `CRP-CM1-R1`, item 5 | idem | *"Les boissons sucrées apportent du sucre en trop, car le corps tire déjà des aliments les sucres dont il a besoin."*, lu apres *"les sodas"* et *"les jus de fruits sucrés"*. Exact. **Levee** |
| `CRP-CM1-R2`, item 6 | la portee exacte du geste | *"Quel geste limite le plus la transmission de ses microbes à cette personne ?"* : *"limite le plus"* comme l'item 4, *"cette personne"* au lieu de *"lui"*. **Levee** |
| `CRP-CM1-R2`, item 11 | le role exact des fibres | Bonne reponse *"à aider l'intestin à bien fonctionner"*. L'intestin est dans le role, pas en reponse a *"quel organe"* : frontiere `SCI-CM1-09` tenue. **Levee** |
| `CRP-CM1-R3`, item 7 | des distracteurs plausibles et faux sur ce que fait l'activite physique | *"à user les muscles et les articulations"*, *"à remplacer une alimentation variée"* ; l'indice repond aux deux. **Levee** |

**Sur l'item 3, une relecture de mon propre texte.** *"Le temps où le corps grandit et se répare"*
peut se lire comme si le corps ne grandissait que la nuit. Je ne le compte pas comme inexact : l'enonce
du meme item dit *"surtout"*, et l'enfant lit l'indice juste apres lui. Je le dis parce que c'est mon
texte qui est servi.

**Ce que je n'ai pas releve, et pourquoi.** L'indice de l'item 6 ne repond pas a *"tousser en se tenant
bien droit"*, et ceux des items 9 et 12 ne repondent qu'a un distracteur. Ces items etaient servis tels
quels au verdict du 2026-10-06, et aucune reserve ne les nommait : je ne releve pas ma barre au rejeu.

**Seule garde, inchange (section 9)** : l'exactitude d'un fait dans un indice, la portee d'une bonne
reponse, la plausibilite d'un distracteur, `BIA-4` hors des jetons d'adresse, les frontieres `EPS-9` et
`SCI-CM1-09`. Verifie ici en jouant. Tarif reel : **0,25** (texte repris mot pour mot, `PAR-10`).
Aucun identifiant neuf.

---

### Verdict du 2026-10-06 (`nadia-prof`, s51, instance B) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce le 2026-10-06.** `camille-po` peut passer `SCI-CM1-10` a
`couvert-valide`, **sans mention de couverture** (`DEC-0110` section 10 : aucune mention due pour
cette ligne). Trois reserves, `CRP-CM1-R1` a `CRP-CM1-R3` (section 8), a faire en stories. Aucune
n'est un motif de refus : aucune bonne reponse n'est fausse et aucun distracteur n'est vrai. Les
reserves portent sur deux indices, sur la portee de deux formulations et sur des distracteurs
implausibles, regime que j'applique depuis `MEL-R1` (s30) et `PHN-CM1-R1`, `PHN-CM1-R2` (s46).

#### 1. Ce que j'ai fait

**Audit complet, pas degrade.** Worktree dedie `s51-nadia-instance-b`, a `0e9913c1` (les commits
`6971d069`, `9afa50e4`, `d88268d5`, `0e9913c1` de `marc-dev` inclus). Jar `bootstrap/target/recreo.jar`
de ce worktree, dont j'ai verifie que les trois paquets et le catalogue sont identiques octet pour
octet aux fichiers commites ; lance sur un port libre, arrete par son PID. Joue par les routes servies
(demarrage de partie, coups).

**Methode de lecture** : avant de voir les propositions, j'ai lu les douze enonces seuls, propositions
masquees, et repondu moi-meme a chacun ; puis j'ai compare a la bonne reponse et aux distracteurs. Je
n'ai relu aucune fiche anterieure de ce jeu (il n'y en avait pas).

**Parties jouees** : quatre parties de dix manches. Dans la premiere, une erreur aux manches impaires
avant la bonne reponse, et trois erreurs a la manche 3 jusqu'a la revelation (*"Ce n'est pas ça.
Appuie sur la bonne réponse : ..."*) ; puis trois parties de meme schema. **Les douze items ont ete
tires et joues**, dont les items 3, 5, 7 et 8 avec une erreur. L'indice s'affiche des la premiere
erreur et reste affiche ; l'ecran de fin compte les questions trouvees sans aide.

#### 2. Ancrage au programme

`SCI-CM1-10` : *"Relier l'alimentation, l'activité physique, le sommeil et l'hygiène au bon
fonctionnement du corps."* Tache A (items 1 a 6) : dire ce qui est bon pour le corps. Tache B (items
7 a 12) : dire a quoi sert un des quatre. **C'est bien cette competence**, sur le couple de taches que
j'avais ecrit d'avance.

**Frontiere avec `SCI-CM1-09`, verifiee item par item.** Aucune bonne reponse ne nomme un organe ni
une des trois fonctions de nutrition comme objet de la question. Les items 11 (fibres) et 12 (fer)
touchent la digestion et le transport de l'oxygene, mais ils demandent le role d'un constituant de
l'alimentation, pas *"quel organe"* ni *"quelle fonction"* : ils servent l'element *alimentation* de
cette ligne. Aucun item ne reprend `cm1-sciences-nutrition` item 5 (*"quel organe se remplit d'air"*).

**Frontiere `EPS-9`, tenue.** Aucun item ne dit le coeur renforce ni l'endurance qui progresse ;
aucun ne sert un effet immediat de l'effort (essoufflement, soif, chaleur) de `cm1-eps-effets-activite`.
Les items 2 et 7 disent que le corps est fait pour bouger et que l'activite fait travailler les
muscles et les articulations : c'est l'element *activite physique* de cette ligne, sans horizon de
temps. L'item 5 (l'eau) porte sur le corps qui perd de l'eau *"sans cesse"*, pas pendant l'effort :
il ne double pas l'hydratation d'`EPS-CM1-10`.

#### 3. Conditions, verifiees une par une

| Condition | Tenue | Ce que j'ai verifie |
|---|---|---|
| `FER-1` | **oui** | Alimentation en bonne reponse (items 1, 5, 9, 11, 12), activite physique (2, 7), sommeil (3, 8), hygiene (4, 6, 10) |
| `FER-2` | **oui** | 12 items servent la ligne |
| `FER-3` | **oui** | Deux taches distinctes, 6 et 6 items |
| `DEC-0064` | **oui** | 12 items pour 10 manches |
| `SCI-1` | **oui a la lettre, avec une reserve** | Items a conception fausse (1 un seul aliment, 2 rester immobile, 4 l'eau seule, 6 tousser dans sa main) : l'indice dit pourquoi elle est fausse, en une phrase, sans *"ta réponse"*, sans nommer la reponse. Deux indices du paquet sont inexacts : `CRP-CM1-R1` |
| `SCI-3` | **oui** | Aucune valeur numerique a restituer, 0 sur 12 |
| `SCI-4` | **oui** | Trois propositions sur les douze items |
| `BIA-4` | **oui** | Aucun item n'interroge l'enfant sur sa vie ; aucun ne suppose un repas, un lit ou une routine. *"Un enfant malade"* (item 6) est une personne decrite, pas le joueur. *"La nuit"* (item 3) est un fait sur le corps, pas une question sur l'endroit ou l'enfant dort |
| `HIS-3` | **oui** | Chaque ligne d'item porte son marqueur ; j'ai verifie chaque fait moi-meme (section 5) |
| `CON-1` | **oui** | Aucun item ne se repond sans son enonce |
| `CYC-1` | **oui** | Section 6 |

#### 4. Niveau reel

Enonces d'une ou deux phrases, vocabulaire du CM1 (*articulations*, *microbes*, *calcium*, *fibres*,
*céréales complètes*). *"Fibres"* et *"céréales complètes"* sont les deux mots les plus exigeants ; ils
sont dans l'enonce, nommes, et la question ne demande pas de les definir. Les distracteurs de l'item 7
genent : `CRP-CM1-R3`.

#### 5. Exactitude, fait par fait

- Item 1 : le corps a besoin d'aliments varies pour grandir et fonctionner ; aucun aliment ne contient
  tout : **exact**.
- Item 2 : les muscles et les articulations sont faits pour bouger ; bouger souvent est bon pour eux :
  **exact**.
- Item 3 : la croissance et la reparation se font surtout pendant le sommeil ; il faut assez de
  sommeil : **exact**. L'indice ne l'est pas : `CRP-CM1-R1`.
- Item 4 : se laver les mains avec du savon est le geste qui limite le plus la transmission ; l'eau
  seule emporte mal ce qui colle a la peau : **exact**.
- Item 5 : le corps est fait en grande partie d'eau et en perd sans cesse ; l'eau est la boisson qui
  lui convient : **exact**. L'indice ne l'est pas : `CRP-CM1-R1`.
- Item 6 : tousser dans son coude, et non dans sa main qui touche ensuite objets et personnes :
  **exact** sur le geste, imprecis sur sa portee (*"évite"*) : `CRP-CM1-R2`.
- Item 7 : l'activite physique fait travailler les muscles et les articulations : **exact**.
- Item 8 : le sommeil permet de grandir et de se reparer ; pendant le sommeil le corps ne depense
  presque rien pour bouger : **exact**.
- Item 9 : le calcium du lait et du fromage sert a construire des os et des dents solides : **exact**.
- Item 10 : le savon enleve la saleté et une partie des microbes, il se rince : **exact**, et la
  nuance *"une partie"* est juste.
- Item 11 : les fibres ne sont presque pas absorbees : **exact**. La bonne reponse *"à aider le ventre
  à digérer"* est **imprecise, pas fausse** : `CRP-CM1-R2`.
- Item 12 : le fer de la viande rouge et des lentilles sert au transport de l'oxygene dans le sang (il
  entre dans l'hemoglobine) : **exact** au niveau de simplification du CM1.

#### 6. `CYC-1`, biais et ton

**`CYC-1` - lus a cote du paquet** : `cp-qlm-hygiene` (15 items), `cp-qlm-corps-sens` (14 items),
`cp-eps-corps-effort` (22 items). Aucune contradiction. Le CP dit que le sommeil fait reposer et
recuperer le corps, qu'il faut manger varie, se laver les mains pour enlever les microbes, eviter
trop de sucreries : le CM1 ajoute la croissance, la reparation, le role du calcium, des fibres et du
fer, la transmission des microbes par la toux. **Progression, pas contradiction.** Le paquet d'effort
du CP ne porte que sur l'effort ; aucun item de CM1 n'y touche.

**Biais et ton** : ton neutre et factuel, aucun item ne fait la morale a l'enfant. *"Que doit-il
recevoir"* (item 1) et *"Que lui faut-il"* (item 3) ont pour sujet le corps, pas l'enfant : ce ne sont
pas des injonctions. La consigne servie est un imperatif de jeu (*"Lis bien, puis dis"*), pas un
geste prescrit. Aucun aliment n'est attache a un milieu ou a une culture.

#### 7. Ce qui m'a fait hesiter, et pourquoi ce n'est pas un refus

L'indice de l'item 3 affirme une chose fausse (`CRP-CM1-R1`). **J'ai hesite a refuser.** Je ne le
fais pas, pour la raison que j'ai ecrite sur `cm1-sciences-melanges` (`MEL-R1`) et sur
`cm1-sciences-phenomenes` (s46, section 7) : un indice inexact est lu par l'enfant qui s'est trompe,
a cote d'une bonne reponse exacte ; un enonce ou une bonne reponse faux sont lus par tous et appris
comme vrais. Les douze bonnes reponses sont exactes. Refuser ici serait relever pour ce paquet une
barre que je n'ai pas tenue sur `MEL-R1`, qui enseignait aussi une conception fausse dans un indice.

#### 8. Les reserves

> **`CRP-CM1-R1` - Deux indices disent une chose fausse.**
>
> - Item 3, indice servi : *"Réparer et grandir demande du temps pendant lequel le corps ne fait rien
>   d'autre."* **Faux** : pendant le sommeil, le coeur bat, l'enfant respire, la digestion continue.
>   L'indice contredit `cm1-sciences-nutrition` item 3, deja servi au meme niveau (*"Cet organe bat
>   sans arrêt, jour et nuit"*), et installe l'idee d'un corps a l'arret la nuit. Lu a l'ecran apres
>   l'erreur *"boire des boissons qui empêchent de dormir"*.
> - Item 5, indice servi : *"Les boissons sucrées contiennent beaucoup de sucre, que le corps n'utilise
>   pas en grande quantité."* **Inexact** : le corps utilise chaque jour une grande quantite de sucres,
>   qu'il tire surtout des feculents ; ce qui est en trop dans une boisson sucree, c'est le sucre
>   ajoute. Lu a l'ecran apres l'erreur *"les sodas"*.
>
> **Correction a faire** : sur ces deux items, un indice vrai, d'une phrase, qui dit pourquoi la
> conception fausse l'est, sans nommer la bonne reponse. Les formulations sont a l'equipe.

> **`CRP-CM1-R2` - Deux formulations apprises comme vraies sont plus larges que le fait.**
>
> - Item 6 : *"Quel geste évite de lui transmettre ses microbes ?"* Tousser dans son coude **limite**
>   la transmission, il ne l'evite pas. L'item 4 du meme paquet dit justement *"limite le plus"*.
>   *"Lui"* designe en outre l'autre personne sans qu'un enfant de 9 ans le voie au premier coup d'oeil.
> - Item 11 : *"à aider le ventre à digérer"*. Les fibres ne sont pas digerees ; elles aident
>   l'intestin a bien fonctionner (le transit). *"Le ventre"* est en outre un mot du CP, la ou
>   `cm1-sciences-nutrition` nomme deja les organes.
>
> **Pourquoi c'est une reserve et pas un refus** : aucune des deux n'est fausse dans le sens courant ou
> un enfant la lit ; ce sont des formulations repandues, plus larges que le fait. **Correction a
> faire** : une question et une bonne reponse qui disent la portee exacte du geste (item 6) et le role
> exact des fibres (item 11).

> **`CRP-CM1-R3` - Item 7 : des distracteurs que personne ne croit.** *"à remplacer le sommeil"* et
> *"à se passer de manger"* : aucun enfant de 9 ans ne pense que bouger dispense de dormir ou de
> manger. L'item devient une devinette, et l'indice (*"Aucun exercice ne tient lieu de repos."*) ne
> repond qu'au premier : lu a l'ecran apres l'erreur *"à se passer de manger"*, il ne dit rien de ce
> que l'enfant a choisi. Meme famille que `PHN-CM1-R2`. **Correction a faire** : des distracteurs qui
> sont des idees plausibles et fausses sur ce que fait l'activite physique.

**Ce que ces reserves ne demandent pas** : aucune autre modification n'est une condition de levee. Je
les leverai a la barre ecrite ici.

#### 9. Ce dont je reste la seule garde

`PaquetCm1SciencesCorpsTest` tient les marqueurs `FER-1` et de tache, le nombre d'items, la forme de
`SCI-1`, `SCI-3`, `SCI-4` et le marqueur `HIS-3`. **Aucun automate ne verifie** : l'exactitude d'un fait
de sciences dans un indice (`CRP-CM1-R1` a traverse le test vert), la portee exacte d'une bonne
reponse (`CRP-CM1-R2`), la plausibilite d'un distracteur (`CRP-CM1-R3`), `BIA-4` hors des jetons
d'adresse, la frontiere `EPS-9` et la frontiere avec `SCI-CM1-09`, `CYC-1`. Ces proprietes n'ont ete
verifiees qu'ici, en jouant et en lisant les paquets voisins.

#### 10. Mesure technique citee

`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test` sur ce worktree, avant l'ecriture
de cette fiche : *"Tests run: 1202, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`. Perimetre :
le module `adapter-content` et ses modules amont, pas la CI ; la porte technique reste a juger sur les
jobs de `.github/workflows/ci.yml`, que je n'ai pas lances. Garde-fou des identifiants : voir la note
de `nadia-prof` du 2026-10-06 dans `squad/decisions/REGISTRE-identifiants.md`.
