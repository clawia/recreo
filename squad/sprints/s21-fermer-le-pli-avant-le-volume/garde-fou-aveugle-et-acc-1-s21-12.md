# `S21-12` - le garde-fou qui ne regardait pas, le commentaire qui disait le contraire du code, et la cause d'`ACC-1`

- **Auteur** : `theo-archi`. **Date** : 2026-08-27. **Branche** : `s21-theo`, worktree dedie.
- **Prealable au deblocage du lot de `marc-dev`** : sans ce correctif, un lot vert entre dans le
  produit en violant `ADR-0017`.
- **Convention de chiffres tenue dans tout le document** : chaque chiffre porte sa methode, son
  perimetre et sa date dans la meme phrase.
- **Ce que ce lot ne fait pas** : il ne corrige aucune valeur de feuille. Le rembourrage de
  `.proposition` appartient au lot de `marc-dev`, celui de la sortie a `ines-design`
  (conditions `SOR-1` a `SOR-4` de `nadia-prof`), et la valeur d'`ACC-1` a `ines-design` et
  `nadia-prof`. Je livre le garde-fou qui voit, et la cause mesuree. **Je ne prejuge d'aucun
  chiffre de forme et je ne prononce aucun Done.**

## 1. Le defaut : un test vert 11 sur 11 qui n'avait pas lu la declaration qu'il jugeait

`blocsParSelecteur` **fusionne** les corps successifs d'un meme selecteur, dans l'ordre du document
(`ancien + ";" + nouveau`). `debutDeDeclaration` rend la **premiere** declaration trouvee. Sur la
feuille de `s21-marc` (`0e0bc4e`), `.proposition` recoit son rembourrage **deux fois** : `0.75rem
1.25rem` par le bloc partage des cinq boutons pleins (ligne 667), puis `0.5rem 1.25rem` par une
regle qui lui est propre et qui vient apres pour la surclasser a specificite egale (ligne 721).
`LActionNeDeclarePasMoinsQueLaSortieTest` lisait donc `0.75rem` - une valeur que rien ne rend a
l'ecran - et restait vert.

**Le defaut reel qu'il laissait passer** : l'action `.proposition` declare `0.5rem` de rembourrage
vertical la ou la sortie `.lien-accueil` en declare `0.6rem` (ligne 140) et `.retour-accueil` aussi
(ligne 849). **L'action declare moins que la sortie**, ce qu'`ADR-0017` interdit. Il a traverse une
CI verte trois fois.

**Le mode de defaillance est celui que j'ai instruit trois fois aujourd'hui : ce test ne se trompait
pas, il ne regardait pas.** Meme famille que `campagne-budget-vertical.sh` ecrivant `campagne
terminee` en ayant perdu 8 jeux sur 17 (`S21-1`).

## 2. Le correctif, en deux couches

### 2.1 Lire la declaration qui gagne la cascade

`derniereDeclaration(corps, propriete)` est ajoutee a `TailleDesTextesEtBoutonsTest` : la
**derniere** declaration, celle qui l'emporte a specificite egale et a origine egale, puisque le
corps fusionne l'est dans l'ordre du document. `LActionNeDeclarePasMoinsQueLaSortieTest.mesure()`
la lit desormais, ainsi que `proprieteEnRem` et `proprieteEnRemSiExiste`.

**Ce que je n'ai pas fait, et c'est delibere** : je n'ai pas fait d'une propriete declaree deux fois
un echec. Un bloc partage suivi d'une regle plus ciblee est une technique de feuille ordinaire et
saine ; la refuser serait un rouge sur une feuille correcte - le motif de refus d'`ADR-0028`. Ce qui
devient un echec, c'est de **lire la mauvaise des deux**.

### 2.2 Rendre l'omission bruyante au point de passage commun

`valeurDeclaree` est le point par lequel **tout** garde-fou de ce module lit une valeur de feuille.
Il refuse desormais deux situations, avec un message qui nomme les valeurs concurrentes :

- la position lue **n'est pas la derniere** declaration de cette propriete dans ce corps : la valeur
  rendue serait ecrasee, donc invisible a l'ecran ;
- la propriete est declaree plusieurs fois **et l'une porte `!important`** : le rang ne decide plus
  de la cascade, et ce lecteur ne sait pas la resoudre. Aucune valeur n'est rendue plutot qu'un
  chiffre sans methode. Aucun `!important` n'existe aujourd'hui dans la feuille maison (`grep -c
  '!important'` sur `recreo.css` rend **0**, le 2026-08-27) : ce refus-la garde contre une inversion
  future, il ne repare rien d'existant.

**Ce refus est ce qui empeche la rechute.** Mutation mesuree : `mesure()` remise sur
`debutDeDeclaration` seule, sans toucher au refus - le jeu fautif de la section 4 rougit quand meme,
sur le message du refus. Un futur auteur qui reviendrait a l'ancienne lecture ne retrouverait pas un
vert silencieux.

## 3. Est-il seul de son espece ? La commande, et ce qu'elle rend

**La question posee etait "quels autres tests portent le meme angle mort".** Je reponds par deux
mesures et non par une lecture.

### 3.1 Le perimetre statique, par la commande

```
comm -12 \
  <(grep -rl -e "blocsMaison(" -e "blocsTiers(" -e "blocsParSelecteur(" --include="*.java" adapters bootstrap | grep -v target | sort) \
  <(grep -rl -e "valeurDeclaree(" -e "proprieteEnRemSiExiste(" --include="*.java" adapters bootstrap | grep -v target | sort)
```

Executee le 2026-08-27 dans le worktree `s21-theo` (`target/` exclu), elle rend **9 classes** qui
lisent a la fois des corps fusionnes et une valeur : `CabinetDeLaMachineTest`,
`LActionNeDeclarePasMoinsQueLaSortieTest`, `LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest`,
`LaPuceActiveAfficheUnAnneauExterieurPercuTest`, `LeConteneurTierGardeSonRembourrageLateralTest`,
`LeFondDeLIconePorteLaCouleurDuBandeauTest`, `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest`,
`TailleDesTextesEtBoutonsTest`, `UnPressableFocaliseAuClavierAfficheUnIndicateurVisibleTest`.

**Mais un perimetre enumere par types echoue par inclusion autant que par omission** : ces 9 classes
sont celles qui *pourraient* etre touchees, pas celles qui le sont. Beaucoup n'emploient
`debutDeDeclaration` que pour constater une **presence** (`>= 0` / `< 0`), usage qu'un doublon
n'affecte pas. La liste seule serait une reponse qui a l'air d'une mesure.

### 3.2 Le perimetre reel, par le refus lui-meme

**Le refus de la section 2.2 est l'instrument qui rend la vraie reponse** : il rougit exactement la
ou une lecture ecrasee a lieu. Installe puis la suite du module executee, le 2026-08-27 :

| Classe | Verdict |
|---|---|
| `LActionNeDeclarePasMoinsQueLaSortieTest` | touchee - c'est le defaut de ce lot |
| `LaLargeurDemandeeParUneRangeeEstEpingleeTest` | **6 tests sur 8 rouges** |
| `LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest` | **7 tests sur 11 rouges** |
| les 7 autres classes de la liste de 3.1 | **aucune** - leurs lectures ne portent sur aucun doublon |

**Ce que les deux classes de largeur revelaient, et ce n'est pas le meme defaut.** Toutes deux
passent par `termesDeclares`, qui parcourt chaque declaration du corps **mais relisait la valeur par
`debutDeDeclaration`**, donc toujours la premiere. Une propriete declaree N fois rendait N fois la
meme valeur, et les N-1 autres n'etaient **jamais lues**. Le cas est reel : la feuille vendorisee
declare `width: -webkit-max-content`, `width: -moz-max-content` puis `width: max-content` dans un
meme corps - la triplette de repli de prefixes habituelle - et cette table epinglait trois fois
`-webkit-max-content`. Un changement de la valeur reellement appliquee (`max-content`) ne bougeait
pas d'un caractere dans la table censee l'epingler.

**Corrige : chaque occurrence est lue a sa propre position.** Et un fait qui compte pour la fusion :
**les deux tables epinglees d'`ADR-0054` et `ADR-0055` ne changent pas d'une ligne** - `Tests run:
19, Failures: 0` sur `-Dtest='LaLargeur*'`, `-pl adapters/adapter-web -am`, le 2026-08-27. Les
regles qui portaient un doublon etaient toutes ecartees plus loin par le filtre de designation.
Le trou etait donc **latent** : reel dans le lecteur, sans effet sur les valeurs epinglees a ce
jour. Je le dis ainsi plutot que de laisser croire que j'ai repare une table fausse.

## 4. La preuve : materiau reel, pas une fixture de ma main

Le jeu fautif neuf est
`adapters/adapter-web/src/test/resources/contreexemple/LActionNeDeclarePasMoinsQueLaSortieTest/rejeu-s21-2-cascade/static/css/recreo.css`
- la feuille de `s21-marc` a `0e0bc4e`, reprise **sans y changer un caractere**, rejouee sous les
**gabarits de production** (la double racine que ce garde-fou porte depuis son ecriture). Quatrieme
rejeu d'une regression reelle de ce depot, apres les trois de s02 et du lot Bootstrap.

**Deux tests neufs, et le second existe parce que le premier ne suffit pas :**

| Test | Ce qu'il prouve |
|---|---|
| `leRejeuDeLaCascadeDeSVingtEtUnDeuxFaitEchouerLeGardeFou` | la feuille reelle est desormais **rouge**, avec le message *"la sortie `.btn.lien-accueil` \"Les jeux\" declare rembourrage haut et bas a 0,60rem ... se contente de 0,50rem"* |
| `leJeuFautifDeLaCascadePorteBienDeuxDeclarationsDontLaPremiereEstCelleQuiTrompait` | le materiau porte **encore** les deux declarations, la premiere a `0.75rem 1.25rem` et la derniere a `0.5rem 1.25rem`. Ramene un jour a une seule, le jeu fautif rougirait toujours - la comparaison 0,5 contre 0,6 tiendrait - mais il ne prouverait plus rien sur la cascade, **et personne ne s'en apercevrait** |

**Non-vacuite, mesuree et non affirmee.** Arbre remis dans son etat d'avant ce lot - lecture par
`debutDeDeclaration` **et** refus neutralise - puis la classe relancee, arbre restaure et verifie
propre ensuite, le 2026-08-27 :

```
leRejeuDeLaCascadeDeSVingtEtUnDeuxFaitEchouerLeGardeFou <<< FAILURE!
java.lang.AssertionError: Expecting code to raise a throwable.
```

*"Expecting code to raise a throwable"* : sur la feuille reelle de `s21-marc`, l'ancien garde-fou
**ne levait rien**. C'est la preuve directe de l'angle mort, sur du materiau que je n'ai pas ecrit.

`LActionNeDeclarePasMoinsQueLaSortieTest` passe de **11 a 13 tests**, tous verts sur cet arbre.

## 5. Le commentaire de feuille qui disait le contraire du code

Route par `nadia-prof`. `recreo.css` portait, au-dessus de `.lien-accueil` :

> *"ADR-0022 : depuis S6-9 ce lien ne s'affiche que sur l'accueil, ou il est confronte a `.demarrer`.
> Il n'est plus confronte a `.proposition` ni a `.encore` : la regle de masquage ci-dessous fait
> qu'il ne se montre jamais en meme temps qu'elles."*

**C'est faux depuis `S15-3`** (arbitrage de l'utilisatrice du 2026-08-18), qui a retire la regle de
masquage `body:has(.ecran-de-jeu) .lien-accueil` : la sortie d'en-tete reste visible pendant une
manche. `nadia-prof` l'a reverifie a l'ecran le 2026-08-27.

**Trois faits qui rendent ce commentaire particulierement couteux :**

1. il contredisait **le commentaire situe six lignes plus haut dans le meme fichier**, qui dit
   correctement que la sortie reste desormais visible pendant une manche ;
2. il contredisait un **test executable** ecrit en s15,
   `leLienDeLEnTeteRedevientConfronteALaReponseSurLesEcransDeJeu`, qui exige exactement l'inverse ;
3. il nommait `.encore`, une classe qui n'existe dans **aucun gabarit depuis s10**.

Le commentaire est **amende et date**, pas efface : la phrase fautive est citee, puis corrigee, avec
la date et le motif. Meme forme que les douze `confirmation-archunit.md` de `S21-6` - un commentaire
qui contredit un test executable est de la meme famille que la citation d'`ADR-0029` trouvee dans
une Javadoc le meme matin.

## 6. `ACC-1` : la cause n'est pas la largeur des libelles, c'est une declaration - et je l'ai mesuree

`nadia-prof` route le symptome sans trancher la cause, et refuse tout retrait de proposition sur
`cm1-accords-groupe-nominal` (les quatre formes **sont** l'item). Elle mesure que **7 des 8 items
tiendraient** sur une rangee de quatre.

### 6.1 La cause

```css
.propositions:has(.proposition:nth-child(4)) .proposition {
    flex: 1 1 calc(50% - 0.5rem);
    font-size: 1.875rem;
}
```

**Ce n'est pas le selecteur `:has(.proposition:nth-child(4))` qui produit les deux rangees, c'est la
base de colonne qu'il porte.** `flex-basis: calc(50% - 0.5rem)` demande a **chacun** des quatre
boutons la moitie de la rangee moins la moitie de l'ecart : deux par rangee, **par construction,
quel que soit le libelle**. Les deux rangees d'un item a quatre propositions sont **declarees**,
pas subies. La distinction compte, parce que le selecteur porte **aussi** autre chose (6.3).

### 6.2 La mesure, avec sa methode, son perimetre et sa date

**Outil** : `squad/outils/theo-archi/mesurer-budget-vertical.py`, auquel ce lot ajoute un parametre
`REGLE` - la regle candidate est injectee dans le document rendu, **jamais dans `recreo.css`**, et
elle est reposee et **relue avant chaque lecture** (le juge de `S21-1`), si bien qu'une navigation
qui la jetterait serait un refus et non un silence. **Viewport 744 x 551, lu du navigateur ;
`ECHECS=1` ; jar de cet arbre ; le 2026-08-27 ; temoin et candidat sur la meme instance servie.**

| | items vus | items sur 2 rangees | items sous le pli | pire deficit |
|---|---|---|---|---|
| **temoin** (feuille telle quelle) | **24 sur 24** | **8** | **8** | 68,19 px |
| **candidat** `.propositions:has(.proposition:nth-child(4)) .proposition{flex-basis:auto}` | 22 | **1** | **1** | 68,19 px |

**Trois lectures, et la premiere est la plus importante :**

1. **Les 8 items du temoin ont tous exactement le meme deficit, 68,19 px.** Une constante sur des
   items dont les libelles n'ont pas la meme longueur est la signature d'une **mise en page
   declaree**, jamais d'un effet de largeur de contenu - meme raisonnement que les 551,00 px pile du
   levier de position.
2. **Le candidat ne touche que la base de colonne** ; il ne change ni la police, ni l'ecart, ni le
   rembourrage. **7 des 8 items repassent sur une seule rangee et au-dessus du pli.**
3. **Le huitieme resiste** : *"Les histoires sont ___."* reste a deux rangees. Ce levier ne ferme
   donc pas `ACC-1` en entier, et je l'ecris plutot que d'annoncer 8 sur 8.

**Releves commites**, item par item : `squad/conformite/releves-s21/s21-acc1-temoin-accords-groupe-nominal-744x551.json` et `squad/conformite/releves-s21/s21-acc1-candidat-base-de-colonne-accords-groupe-nominal-744x551.json` - le second porte la regle candidate dans son champ `regleInjectee`, pour qu'aucune lecture n'ait a supposer dans quelles conditions elle a ete prise. C'est la faute de methode que j'ai commise a s20 en ne commitant pas mes rapports par jeu, et que je ne refais pas.

**La concordance qui me fait tenir le resultat** : `nadia-prof` annonce 7 des 8 par une mesure de
largeur de libelles ; ma mesure rend 7 des 8 par un contrefactuel de mise en page. Deux methodes,
deux auteurs, le meme nombre.

### 6.3 Ce que la regle garde, et qu'il ne faut pas emporter avec la base

Le meme selecteur porte `font-size: 1.875rem`. **Ce n'est pas un detail de mise en page : c'est une
exception que `nadia-prof` a accordee en `S8-5`**, sur sept conditions
(`FICHE-bandit-manchot-conjugaison-present-cm1.md`, arbitrage prealable du 2026-08-11) - un
**plancher** et non une pente (condition 2), litteral et ni variable ni `calc` (condition 3), et
`lesSeulesTaillesDePropositionSontLesDeuxReglesNommees` rougit sur toute troisieme regle de taille
de proposition. **Mon candidat ne l'a pas touchee, et un correctif ne doit pas la toucher non
plus** : retirer le bloc entier emporterait l'exception avec la base, et rouvrirait un arbitrage
pedagogique en croyant regler une question de forme.

### 6.4 Ce que je ne dis pas

- **Je ne propose aucune valeur.** `flex-basis: auto` est une valeur de **mesure**, choisie parce
  qu'elle isole le mecanisme, pas parce qu'elle est bonne. La valeur appartient a `ines-design` et
  la decision a `nadia-prof`.
- **Je n'ai mesure qu'un viewport (744 x 551) et qu'un paquet.** `cm1-conjugaison-present`, l'autre
  paquet a quatre propositions (25 items), n'est pas mesure ici. Et la base a zero etait la cause du
  defaut de `S13` : tout candidat devra etre reverifie a 360 px, ou le repli est necessaire.
- **Je ne prononce aucun Done et aucun statut de couverture.**

## 7. La porte technique de ce lot

```
bash outils/mvn-jdk21.sh -B clean verify
```

A la racine du reacteur (six modules, **aucun `-pl`**), le 2026-08-27, sur `s21-theo` avec ce lot.
**`BUILD SUCCESS`, code de sortie 0, `Total time: 01:29 min`.** Blocs `Results:` releves module par
module dans le journal de cette commande, jamais deduits d'un total :

| Module | Bloc `Results:` |
|---|---|
| `recreo-domain` | `Tests run: 49, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-application` | `Tests run: 13, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-adapter-web` | `Tests run: 332, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-adapter-content` | `Tests run: 205, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-bootstrap` (surefire) | `Tests run: 98, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-bootstrap` (failsafe) | `Tests run: 6, Failures: 0, Errors: 0, Skipped: 0` |
| **total, somme des six blocs** | **703** |

**Delta avec ma base, et il s'explique en entier** : **701** sur `s21-theo` avant ce lot (mesure du
2026-08-27, meme commande, meme worktree, `porte-technique-lot-theo-s21.md`), **703** apres.
`recreo-adapter-web` passe de 330 a **332** : les deux tests neufs de
`LActionNeDeclarePasMoinsQueLaSortieTest`. Aucun autre module ne bouge, ce qui est attendu - ce lot
ne touche que ce module et de la redaction.

**Machine virtuelle des tests** : `Found matching toolchain for type jdk` du journal de cette
execution designe un **JDK 21.0.10**, et c'est cette ligne-la qui l'etablit, jamais la ligne de
diagnostic de l'enveloppe (`S21-6`).

## 8. Ce que je route

- **`sofia-sm`** : `S21-12` est fait et prouve ; le lot de `marc-dev` n'est plus retenu par le
  garde-fou aveugle. Il sera en revanche **rouge** tant que `.proposition` declarera moins que la
  sortie - c'est le but. **Deux valeurs doivent tenir ensemble** : le rembourrage de `.proposition`
  (`marc-dev`) et celui de la sortie (`ines-design`, `SOR-1` a `SOR-4`). Je ne prejuge d'aucune des
  deux ; `ADR-0017` exige seulement que l'action ne declare pas moins que la sortie.
- **`ines-design` et `nadia-prof`** : la cause d'`ACC-1` est la base de colonne, mesuree en 6.2, et
  **7 des 8 items ferment** sans toucher a la police. La valeur leur appartient. L'exception de
  police de `S8-5` doit survivre au correctif.
- **`camille-po`** : l'amendement du 2026-08-27 en fin de journee a `avis-faisabilite.md` section
  4.2 - mon "122 sur 348" couvre **81 %** de la matiere (348 items vus sur 428 servis, 13 paquets
  sur 17 vus en entier), quand la population des "56 items" de `REP-8` en couvre **39 %**. Les deux
  chiffres ne parlent pas de la meme population, et aucun des deux n'est un inventaire.
