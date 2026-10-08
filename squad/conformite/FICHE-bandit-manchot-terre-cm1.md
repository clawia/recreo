# Fiche de conformite - bandit-manchot-terre-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-terre.yaml`
  (12 items, type `question`), ecrit par `marc-dev` (commit `0e9913c1`).
- **Entree de catalogue** : `bandit-manchot-terre-cm1`, titre servi *"La Terre, le jour et les
  saisons"*, consigne servie *"Lis bien, puis dis pourquoi il y a le jour et la nuit, ou les
  saisons."*, `nombreDeManches: 10`.
- **Ligne declaree** : `SCI-CM1-15`. **Story** : `S51-SCI-TERRE-CM1` (`docs/backlog/backlog.md`,
  section "Sprint 51").
- **Conditions opposables** : `FER-1` a `FER-3`, `SCI-1`, `SCI-3`, `SCI-4`, `HIS-3`, `CON-1`, `CYC-1`
  (`squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`,
  sections 2.2 ligne 15, 2.3, 2.5 et 2.6), `DEC-0064`, et la lecture de `cp-qlm-saisons` due par
  `DEC-0110` section 4.
- **Statut global** : **REFUSE**, prononce le 2026-10-06 par `nadia-prof` (s51, instance B). Fiche
  ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
- **Derniere mise a jour** : 2026-10-06.
- **Suite du 2026-10-08 (s54, instance B)** : ce jeu reste retire et ce refus reste ecrit. La reprise,
  paquet distinct `cm1-sciences-terre-jour-nuit-saisons`, est **VALIDE**, les deux motifs leves a la
  barre de la section 7 : `squad/conformite/FICHE-bandit-manchot-jour-nuit-saisons-cm1.md`.

---

## Bloc de validation pedagogique

### Verdict du 2026-10-06 (`nadia-prof`, s51, instance B) : **REFUSE**

**Done pedagogique non prononce.** `SCI-CM1-15` reste `non couvert` ; `camille-po` ne ferme pas la
ligne. **Deux motifs de refus**, items 4 et 11 (section 7) : sur chacun, un distracteur est une
reponse juste a la question posee. La cause des saisons, que j'avais designee comme le point ou
`SCI-1` mord le plus, est **juste** : l'item 7 porte l'inclinaison de l'axe en bonne reponse et
l'indice de mon arbitrage mot pour mot. Quatre points non bloquants (section 8) peuvent etre pris
dans le meme diff que la correction ; l'un d'eux est un indice faux. Le lot repasse devant moi apres
correction, et je le rejoue avant de juger.

#### 1. Ce que j'ai fait

**Audit complet, pas degrade.** Worktree dedie `s51-nadia-instance-b`, a `0e9913c1`. Jar
`bootstrap/target/recreo.jar` de ce worktree, dont j'ai verifie que le paquet et le catalogue sont
identiques octet pour octet aux fichiers commites ; lance deux fois sur un port libre, arrete chaque
fois par son PID. Joue par les routes servies (demarrage de partie, coups).

**Methode de lecture** : avant de voir les propositions, j'ai lu les douze enonces seuls,
propositions masquees, et repondu moi-meme a chacun ; puis j'ai compare a la bonne reponse et aux
distracteurs.

**Parties jouees** : six parties de dix manches. Les trois premieres avec une erreur sur une manche
sur deux et trois erreurs jusqu'a la revelation a la manche 3 ; les trois suivantes avec une erreur
a chaque manche avant la bonne reponse. **Les douze items ont ete tires et joues, chacun avec au
moins une erreur** ; les indices cites ci-dessous sont ceux que j'ai lus a l'ecran apres l'erreur
nommee.

#### 2. Ancrage au programme

`SCI-CM1-15` : *"Situer la Terre dans le système solaire et relier les mouvements de la Terre au
jour, à la nuit et aux saisons."* Tache C (items 1 et 2) : situer la Terre dans le systeme solaire.
Tache A (items 3 a 6 et 12) : expliquer le jour et la nuit. Tache B (items 7 a 11) : expliquer les
saisons. **C'est bien cette competence**, sur le couple de taches que j'avais ecrit d'avance ; la
tache C sert le premier element du libelle. L'element *situer la Terre* est servi au plus juste
(*"autour du Soleil"*, *"le Soleil au centre"*) ; c'est suffisant pour `FER-1`, et je ne le compte pas
contre le paquet.

#### 3. Conditions, verifiees une par une

| Condition | Tenue | Ce que j'ai verifie |
|---|---|---|
| `FER-1` | **oui** | Systeme solaire en bonne reponse (items 1, 2), jour (3, 4, 6), nuit (5, 12), saisons (7 a 11) |
| `FER-2` | **oui** | 12 items servent la ligne |
| `FER-3` | **oui** | Jour et nuit 5 items, saisons 5 items, plus 2 items de situation |
| `DEC-0064` | **oui** | 12 items pour 10 manches |
| `SCI-1` | **oui a la lettre** | Item 7 : distracteur *"la Terre est plus près du Soleil en été"*, indice *"La distance de la Terre au Soleil change très peu au cours de l'année."* - exactement ce que j'attendais. Items 3, 5, 8, 9 : un indice qui dit pourquoi une conception fausse l'est, en une phrase, sans *"ta réponse"*. Item 4 : l'indice decrit la bonne reponse par paraphrase, famille de `PHN-CM1-R1`, a corriger avec le motif 1 ; items 3 et 9 : point 3, section 8 |
| `SCI-3` | **oui** | Une valeur numerique a restituer (item 6, *"environ vingt-quatre heures"*), plafond 2 sur 12 |
| `SCI-4` | **oui** | Trois propositions sur les douze items |
| `HIS-3` | **oui** | Chaque ligne d'item porte son marqueur ; j'ai verifie chaque fait moi-meme (section 5) |
| `CON-1` | **oui** | Aucun item ne se repond sans son enonce |
| `CYC-1` | **oui** | Section 6 |

#### 4. Niveau reel

Questions courtes, une phrase le plus souvent. Vocabulaire du CM1 (*astre*, *système solaire*,
*inclinaison*, *axe*, *hémisphère sud*, *horizon*). *"Tantôt plus, tantôt moins"* (item 8) et
*"l'endroit opposé"* (item 12) sont les deux formulations les plus difficiles ; elles restent lisibles
par un enfant de 9 ans.

#### 5. Exactitude, fait par fait

- Item 1 : le Soleil, une etoile, est au centre du systeme solaire : **exact**.
- Item 2 : la Terre tourne autour du Soleil en une annee : **exact**. Indice : point 4, section 8.
- Item 3 : le jour et la nuit viennent de la rotation de la Terre sur elle-meme ; la distance au
  Soleil ne change pas entre le jour et la nuit : **exact**.
- **Item 4 : un distracteur est vrai.** Motif 1.
- Item 5 : la region de nuit est tournee du cote oppose au Soleil, qui brille tout le temps :
  **exact**.
- Item 6 : un tour sur elle-meme en environ vingt-quatre heures : **exact** (*"environ"* couvre
  l'ecart entre jour solaire et rotation).
- Item 7 : la cause des saisons est l'inclinaison de l'axe ; la distance change tres peu : **exact**.
  Un distracteur est a revoir : point 2, section 8.
- Item 8 : avec l'axe incline, une region recoit tantot plus, tantot moins de lumiere : **exact**.
- Item 9 : en ete en France metropolitaine, le Soleil monte plus haut et les jours sont plus longs ;
  le Soleil brille de la meme facon toute l'annee : **exact**.
- **Item 10, verifie a part comme demande.** Bonne reponse : en ete boreal, c'est l'hiver dans
  l'hemisphere sud : **exact**. **Son indice est faux** : point 1, section 8.
- **Item 11 : un distracteur est aussi juste que la bonne reponse.** Motif 2.
- Item 12 : quand il est midi en un lieu, il fait nuit au lieu oppose : **exact**.

#### 6. `CYC-1`, biais et ton

**`CYC-1` - `cp-qlm-saisons` (`QLM-CP-13`, 16 items) lu a cote du paquet.** Le CP nomme et ordonne
les mois et les saisons, dit que l'hiver est la saison la plus froide et l'ete la plus chaude. Le CM1
ajoute la cause (l'inclinaison), l'inversion des saisons entre hemispheres, la longueur des jours.
**Aucune contradiction** ; la correction demandee au motif 2 n'en cree pas (le CP ne dit rien de la
longueur des jours).

**Biais et ton** : ton neutre. Un point de presuppose geographique sur l'item 10 : point 1, section 8.

#### 7. Motifs du refus

> **Motif 1 - Item 4 : le distracteur de la distance est une affirmation vraie.**
>
> Item servi : *"Dans une région de la Terre, c'est le jour. Dans quelle position est cette région ?"*
> - bonne reponse *"elle est tournée vers le Soleil"*, distracteurs *"elle est cachée par la Lune"* et
> *"elle est plus proche du Soleil que le reste de la Terre"*, indice *"Le Soleil éclaire la partie de
> la Terre qui lui fait face."*
>
> **Le second distracteur est vrai.** La moitie de la Terre ou il fait jour est, de fait, plus
> proche du Soleil que la moitie ou il fait nuit - de quelques milliers de kilometres. Ce n'est pas
> *pourquoi* il fait jour, mais la question demande une **position**, et cette proposition en decrit
> une qui est exacte. L'enfant qui la choisit recoit *"Ce n'est pas ça."* : il apprend comme faux un
> fait vrai. C'est une cle de reponse fausse. La conception fausse que ce distracteur veut porter - la
> Terre qui se rapproche du Soleil le jour - est deja bien ecrite a l'item 3 (*"la Terre s'éloigne du
> Soleil la nuit"*).
>
> **Correction attendue** : un second distracteur faux, qui porte la conception de la distance sans
> etre vrai a la lettre. Les formulations sont a l'equipe.
>
> **Point a corriger dans le meme geste, qui n'est pas une condition de levee** : l'indice decrit la
> bonne reponse. *"La partie de la Terre qui lui fait face"* est *"tournée vers le Soleil"* dit
> autrement ; je l'ai lu a l'ecran apres chacun des deux distracteurs, et il donne la reponse des la
> premiere erreur (`M21`, famille de `PHN-CM1-R1`). Le distracteur corrige portant la conception de la
> distance, l'indice devra dire pourquoi elle est fausse (`SCI-1`) sans decrire la position attendue.

> **Motif 2 - Item 11 : *"le printemps"* a d'aussi longs jours que *"l'été"*.**
>
> Item servi : *"Quelle saison a les jours les plus longs en France ?"* - bonne reponse *"l'été"*,
> distracteurs *"l'hiver"* et *"le printemps"*, indice *"Plus le Soleil monte haut dans le ciel, plus
> il reste longtemps au-dessus de l'horizon."*
>
> Le jour le plus long de l'annee, le 20 ou le 21 juin, est la **limite** entre le printemps et l'ete.
> Au printemps, les jours s'allongent de douze heures environ jusqu'a ce maximum ; en ete, ils
> raccourcissent depuis ce maximum jusqu'a douze heures environ. Les deux saisons parcourent les memes
> durees de jour, dans l'ordre inverse : **aucune n'a des jours plus longs que l'autre.** Un enfant qui
> repond *"le printemps"* parce que les longues soirees de mai et de juin sont au printemps a raison,
> et il recoit *"Ce n'est pas ça."* L'indice ne le departage pas : le Soleil monte aussi haut fin mai
> que mi-juillet. C'est une cle de reponse fausse.
>
> **Correction attendue** : un item ou une seule saison a les jours les plus longs parmi les trois
> proposees - par exemple en remplacant *"le printemps"* par *"l'automne"*, dont les jours sont bien
> plus courts que ceux de l'ete. Les formulations sont a l'equipe.

**Ce que le refus ne demande pas** : aucune autre modification n'est une condition de levee. La
levee porte sur les items 4 et 11, a la barre ecrite ici, et je ne la releverai pas au rejeu.

#### 8. Points non bloquants, a prendre dans le meme diff

Ils ne sont pas des motifs de refus et ne portent pas d'identifiant. Si le rejeu les laisse en
l'etat, je les ouvrirai alors comme reserves datees.

1. **Item 10 : l'indice est faux, et *"en France"* ne vaut pas pour toute la France.** Indice servi,
   lu apres *"l'été aussi"* et apres *"le printemps"* : *"Les deux moitiés de la Terre ne sont pas
   tournées vers le Soleil en même temps."* Dans ce paquet, *"tournée vers le Soleil"* veut dire
   *il y fait jour* (items 4 et 5). Lu ainsi, l'indice dit que le nord et le sud de la Terre n'ont
   pas le jour en meme temps, ce qui est faux, et il explique les saisons par le jour et la nuit -
   la confusion que la ligne doit defaire. Ce qui est vrai : quand une moitie de la Terre penche vers
   le Soleil, l'autre penche a l'oppose. **Pourquoi ce n'est pas un motif** : c'est un indice
   inexact a cote d'une bonne reponse exacte, regime de `MEL-R1`, que je ne releve pas ici. Dans le
   meme item, *"Quand c'est l'été en France"* : La Reunion, Mayotte et la Nouvelle-Caledonie sont en
   France et dans l'hemisphere sud, et le programme y est le meme. A faire : un indice vrai sur
   l'inclinaison, et *"en France métropolitaine"* (ou une ville nommee) dans l'enonce ; la meme
   precision vaut pour les items 9 et 11.
2. **Item 7 : le distracteur *"la Terre tourne plus vite en hiver"* decrit un fait vrai.** La Terre
   parcourt son orbite un peu plus vite debut janvier, quand elle est au plus pres du Soleil. Ce n'est
   pas la cause des saisons, et la question demande une cause : la cle reste juste, c'est pourquoi ce
   n'est pas un motif. Mais l'enfant apprend comme faux un enonce qui, lu au sens de la course autour
   du Soleil, est vrai. A faire : un distracteur faux dans les deux lectures de *"tourner"* (par
   exemple *"en été"*).
3. **Items 3 et 9 : l'indice repond a la conception la moins courante des deux.** Item 3 : apres
   *"le Soleil tourne autour de la Terre"*, la conception la plus repandue chez les enfants, l'indice
   parle de distance. Item 9 : apres *"la Terre est plus près du Soleil"*, la conception que j'ai
   designee pour cette ligne, l'indice dit que *"Le Soleil brille de la même façon toute l'année"*.
   `SCI-1` est tenue a la lettre (l'indice dit pourquoi **une** conception fausse l'est) ; je ne
   releve pas cette lettre. A faire : sur l'item 9 au moins, un indice qui dit pourquoi la distance
   n'explique pas la chaleur de l'ete.
4. **Item 2 : l'indice ne vaut pas contre *"autour d'une autre planète"*.** *"Un petit astre ne peut
   pas faire tourner autour de lui un astre bien plus gros."* Jupiter et Saturne sont bien plus grosses
   que la Terre : pour l'enfant qui le sait, l'indice laisse ce distracteur possible. A faire : un
   indice qui ecarte la Lune ou une autre planete sans s'appuyer sur la seule taille.

#### 9. Ce dont je reste la seule garde

`PaquetCm1SciencesTerreTest` tient les marqueurs `FER-1` et de tache, le nombre d'items, la forme de
`SCI-1`, `SCI-3`, `SCI-4` et le marqueur `HIS-3`. **Aucun automate ne verifie** : qu'un distracteur ne
soit pas vrai (les deux motifs de ce refus ont traverse le test vert) ; qu'un indice ne decrive pas la
bonne reponse par paraphrase ; l'exactitude d'un fait d'astronomie dans un indice ; qu'une
formulation geographique vaille pour tous les enfants qui suivent le programme ; `CYC-1`. Ces
proprietes n'ont ete verifiees qu'ici, en jouant.

#### 10. Mesure technique citee

`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test` sur ce worktree, avant l'ecriture
de cette fiche : *"Tests run: 1202, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`. Perimetre :
le module `adapter-content` et ses modules amont, pas la CI ; la porte technique reste a juger sur les
jobs de `.github/workflows/ci.yml`, que je n'ai pas lances. Cette fiche n'ecrit aucun identifiant neuf.
