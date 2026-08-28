# Avis de `nadia-prof` et Done pedagogique - s21

- **Auteure** : `nadia-prof`. **Verdicts prononces le** 2026-08-28.
- **Mesures et parties** : 2026-08-27, entre 22:02 et 22:35.
- **Arbre audite** : `round-two` a `8e21613`, porte technique verte (722 tests, 0 echec, mesuree par
  `sofia-sm`). **Increment construit, servi et joue** depuis le worktree `s21-nadia-2`.
- **Perimetre** : les trois paquets de volume de `S21-9`, `S21-10` et `S21-11`, plus les quatre
  points que le sprint m'a routes.

**Ce fichier n'est pas la review.** `review.md` appartient a `sofia-sm` et n'existe pas encore ; le
depot interdit d'ecrire a la suite d'un fichier de ceremonie (`squad/README.md`, section 2). **La
conclusion ci-dessous est a reprendre telle quelle dans `review.md`**, comme le prevoit ma fiche de
role. Le detail de chaque verdict, avec ses chiffres et sa methode, vit dans les trois fiches de
conformite et n'est pas recopie ici.

## Les trois verdicts

| Jeu | Story | Verdict | Motif, en une ligne |
|---|---|---|---|
| `bandit-manchot-syllabes-cp` | `S21-9` | **VALIDE SOUS RESERVE** *(revise le 2026-08-28 ; d'abord REFUSE le meme jour)* | Refus prononce sur l'item `CHEMINEE`, puis **leve apres arbitrage de l'utilisatrice** : *"cheminee vaut bien 3 syllabes"*. Le motif tombe ; c'est ma condition `SEG-1` qui etait trop large, et elle est amendee. Reserve neuve `SEG-13` |
| `bandit-manchot-mot-manquant-cp` | `S21-10` | **REFUSE** *(maintenu au reaudit du 2026-08-28, correction `fabd6cb`)* | Des deux motifs d'origine, **deux items sur trois sont corriges** (item 6 par *"Pour lire le soir"*, item 4 par `impermeable`/`capuchon`, qui ferme aussi `PRE-11`). **Motif restant : l'item 3, *"Lea dessine avec un ___."*, a trois reponses defendables sur trois** - `feutre` et `pinceau` sont refuses a l'ecran alors qu'ils sont corrects. **Le triplet est celui que j'avais moi-meme nomme** |
| `bandit-manchot-comparer-cp` | `S21-11` | **VALIDE SOUS RESERVE** | Les dix conditions tenues, 18 items sur 18 decidables, aucune erreur factuelle. Sept reserves non bloquantes |

Fiches : `squad/conformite/FICHE-bandit-manchot-syllabes-cp.md`,
`FICHE-bandit-manchot-mot-manquant-cp.md`, `FICHE-bandit-manchot-comparer-cp.md`.

**Deux Done pedagogiques sont prononces** *(revise le 2026-08-28 : un seul l'etait a la premiere
redaction de ce fichier)* : `cp-comparer` et, apres arbitrage de l'utilisatrice, `cp-syllabes`.
`camille-po` peut passer les lignes **89** et **485** de `MATRICE-couverture.md` a
`couvert-valide`. **La ligne 496 reste `non couvert`** : `cp-mot-manquant` demeure refuse.

**Sur la ligne 485** : elle ouvre la premiere des trois lignes du domaine "Langage oral et
conscience phonologique" ; les deux autres restent hors d'atteinte sans emission de son
(`DEC-0042`, arbitrage 2). **Ne pas lire cette ouverture comme l'ouverture du domaine.**

**Le retrait des deux entrees refusees du catalogue releve de `DEC-0045` section 3 : le geste
appartient a `camille-po` et `sofia-sm`, pas a moi.** Je n'ai touche ni `catalogue.yaml`, ni aucun
fichier de contenu, ni la matrice. Je n'ai rien pousse et rien fusionne.

**Les deux refus ne bloquent pas le sprint suivant** : ils renvoient les increments a `camille-po`,
qui en fait du backlog. **Aucune condition nouvelle n'est posee sur les deux paquets refuses** :
`SEG-1` a `SEG-9` et `PRE-1` a `PRE-8` restent la specification, et chaque fiche dit ce qui est
verifie et bon pour que la reprise ne le refasse pas.

## Sur la symetrie des deux refus, parce qu'elle sera contestee

Les deux paquets sont refuses pour **la meme famille de defaut** : un item qui dit a un enfant qui a
raison qu'il a tort. `CHEMINEE` pour `marc-dev`, *"La nuit, Tom allume sa ___."* pour `lea-dev`.
J'ai ecrit moi-meme, avant que ces paquets existent, qu'*"un item dont deux comptes sont defendables
n'a pas une bonne reponse, il en a deux, et c'est une erreur factuelle"*, et je tiens depuis le
debut de ce produit qu'une erreur factuelle est un motif de refus a elle seule. **Trancher
differemment entre deux lots du meme sprint aurait ete deplacer mes poteaux d'un lot a l'autre.**

Je distingue en revanche, et expressement, l'item 11 de `cp-mot-manquant`
(*"Tom ___ la lumiere avant de dormir."*) : *"avant de dormir"* oriente reellement vers `eteint`,
la ou *"La nuit"* n'oriente vers rien. **Item 11 = reserve, item 6 = motif.** La distinction est
celle-la et pas une autre.

## Les quatre points que le sprint m'a routes

### 1. `SOR-4` - **fermee**

`SOR-4` (*"la sortie reste atteignable du premier coup"*) etait la seule des quatre que je pouvais
fermer, et je la ferme. Mesuree en **pressant**, sur **six cas** (les trois jeux neufs, chacun en
cours de manche et apres un echec), a 744 x 551, le 2026-08-27 : sortie **entierement dans le
viewport**, **108,83 x 50,00 px** (6 px au-dessus du plancher tactile de 44, exactement le chiffre
d'`ines-design`), **centre libre** - le point au centre exact rend la sortie elle-meme, rien ne la
recouvre -, **212 px** du bouton de reponse le plus proche, **zero defilement**, et **chaque appui
porte du premier coup**. `.retour-accueil` porte la **meme boite au centieme** : `SOR-3` tient a
l'ecran et pas seulement dans la feuille. **La descente de 0,6 rem a 0,5 rem ne coute rien a
l'enfant** : j'avais choisi de faire descendre la sortie plutot que de reprendre le gain de
`marc-dev`, et je le confirme en l'ayant pressee.

**Limite, avec l'etat exact qu'elle couvre** : 744 x 551 uniquement, par un **clic programme au
centre geometrique de la boite**. **360 px et un doigt qui vise mal restent sans mesure, et personne
d'autre n'en a.**

### 2. L'effet de `S21-2` sur le catalogue - **j'ai remesure, et je ne remplace pas le 26**

J'avais confirme l'effet **par item** (-8 px a plat, -8 px par rangee) mais pas le compte de 26,
etabli sur un **echantillon de 167 items sur 428**. J'ai mesure le **catalogue entier** le
2026-08-27, a 744 x 551 : **478 items sur 478, 20 jeux enumeres sur 21. Ce n'est pas un
echantillon.** Le vingt-et-unieme (`bandit-manchot-additions-cp`, paquet genere non enumere)
**n'est pas mesure et n'est couvert par rien.**

| Etat, meme arbre, sans mutation de fichier | Indice sous le pli | Verdict sous le pli |
|---|---|---|
| **Servi** (arbre fusionne de s21) | **64** sur 478 | **10** sur 478 |
| **Avant s21** (trois declarations d'avant reinjectees) | **114** sur 478 | **15** sur 478 |
| **Ecart ferme** | **50** | **5** |

**Les trois declarations ont ete reinjectees ensemble : 50 est l'effet du travail vertical de s21
dans son entier - `S21-2` et la descente de la sortie reunies -, pas celui de `S21-2` seul. Je ne
l'attribue pas a `S21-2` et il ne remplace pas le 26**, qui portait sur `S21-2` seul et sur 167
items. Remplacer l'un par l'autre serait refaire l'erreur que je viens de corriger.

**Ce que la mesure apprend, et qui est a `camille-po`** :

- **Le pli est ferme sur CP, il ne l'est pas sur CM1.** Les **9 jeux CP enumeres, 162 items,
  n'ont plus aucun item sous le pli** (22 avant s21). **Les 64 qui restent sont tous CM1**, sur 316.
- **Quatre jeux CM1 portent 58 des 64** : `accord-sujet-verbe-cm1` 20 sur 21, `reperes-cm1` 16 sur
  16, `grands-nombres-cm1` 14 sur 28, `accords-groupe-nominal-cm1` 8 sur 24.
- **Ou s21 a le plus porte** : `problemes-additifs-cp` de 22 sur 22 a **0 sur 22**,
  `perimetre-cm1` de 20 sur 22 a **1 sur 22**. Ces deux jeux font 41 des 50.

**Un fait a connaitre avant de remesurer** : sur cet arbre, l'outil qui mesure "avec et sans
`S21-2`" rend **deux colonnes identiques au centieme**, parce que `S21-2` est **deja dans la feuille
servie**. Une mesure "avec et sans" n'y mesure plus rien, et un lecteur presse en conclurait que
`S21-2` est sans effet.

### 3. Les trois paquets neufs contre le pli - **verifie moi-meme, paquet entier**

Chacun avait ete mesure par son auteur avec un outil different. Je les ai remesures tous les trois,
**sur le paquet entier et non sur les dix manches d'une partie**, le 2026-08-27, a 744 x 551 :

| Paquet | Items vus | Rangees | Lignes enonce | Lignes indice | Bas de l'indice | Defilement |
|---|---|---|---|---|---|---|
| `cp-syllabes` | **16 / 16** | 1 | 1 | 1 | **486 px** | 0 |
| `cp-mot-manquant` | **16 / 16** | 1 | 1 | 1 | **486 px** | 0 |
| `cp-comparer` | **18 / 18** | 1 | 1 | 1 | **486 px** | 0 |

**50 items sur 50 sont entierement au-dessus du pli de 551, verdict et indice compris, avec 65 px
de marge et zero defilement. L'objet du sprint - que les jeux neufs naissent au-dessus du pli - est
atteint sur les trois, y compris sur les deux que je refuse par ailleurs.** La marge la plus mince
du lot est celle de `cp-comparer` sur le budget de rangee : **20,06 px** sur 522, a connaitre avant
d'y toucher.

**Deux estimations a moi qui etaient fausses, et que je corrige** : j'avais projete que l'indice le
plus long de `cp-mot-manquant` (74 caracteres) tomberait sur deux lignes, et que son enonce le plus
long (49 caracteres) serait a la limite. **Les deux tiennent sur une ligne.** Je m'etais appuyee sur
mon propre ordre de grandeur en caracteres au lieu de mesurer, apres avoir moi-meme ecrit que
**seul le chiffre en pixels fait foi**.

### 4. `cp-syllabes` sans retour pedagogique - **ma decision tient, et je m'etais trompee sur un fait**

`camille-po` m'a route la question sans la trancher : un enfant de CP qui compte juste et ne recoit
qu'un *"Bravo !"* apprend-il quelque chose ? **Ma decision d'accepter cette absence tient** : ce
n'est pas un motif de refus, parce que le defaut est dans la mecanique, partagee par vingt autres
jeux deja valides, et non dans ce paquet.

**Mais j'avais assume le mauvais cout.** J'avais suppose un retour **vide**. En jouant, j'ai vu
qu'il est **attache au mauvais mot** : la mecanique re-rend l'ecran entier avec le mot suivant, si
bien que le *"Bravo !"* du mot repondu s'affiche a cote du mot d'apres. Releve du 2026-08-27, apres
avoir repondu juste sur `CADEAU` : *"Score : 1 / Tape dans tes mains. Combien de syllabes ? /
COUSSIN / 1 3 2 / Bravo !"* - `CADEAU` n'est plus a l'ecran. **Sur un paquet ou l'enseignement est
la segmentation du mot qu'on vient de compter, il n'existe aucun instant ou le mot et sa coupe sont
montres ensemble**, ni pendant la partie, ni sur l'ecran de fin, qui ne revient sur rien.

**Reserve ouverte (`SEG-10`), pas un motif**, routee inchangee a `theo-archi` et `camille-po`. **Je
continue de ne pas autoriser** qu'un paquet de syllabes declare une `graphie` pour franchir
`ChaquePaquetExpliqueSesItemsAVariationTest` : ce serait detourner le sens d'un champ de phonologie
pour faire passer un garde-fou.

## Suites que je route, et qui ne sont pas de moi

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **64 items sous le pli, tous CM1**, concentres sur quatre jeux (58 sur 64). Compte etabli sur 478 items sur 478 | `camille-po` | Prendre le compte, pas l'echantillon. Le pli n'est ferme que sur CP |
| `squad/outils/nadia-prof/auditer-un-paquet-entier.py` **identifie un item par la seule chaine de son enonce** et s'arrete en erreur sur tout paquet qui en repete une (`cp-comparer` : 10 chaines pour 18 items). Le mode de defaillance est ici bruyant ; **rien ne garantit qu'il le soit toujours** - une table qui confond deux items peut aussi rendre un chiffre | `theo-archi` | J'ai commite une variante qui indexe par (enonce, triplet), et qui reproduit le chiffre. La cause dans l'outil d'origine reste a traiter |
| **L'outillage de mesure s'attache au premier navigateur qui repond sur un port de deverminage fixe** sans verifier que c'est le sien (`S21-1`, non corrige a l'heure de mes mesures) | `theo-archi` | J'ai mitige par un port distinct par execution. C'est une mitigation, pas une garantie |
| **Divergence entre deux de mes propres releves sur `cm1-reperes`** : mon verdict du 2026-08-27 porte *"verdict entierement visible sur 14 items sur 16"*, ma mesure du soir rend 16 sur 16. Outils differents, definitions peut-etre differentes | `nadia-prof` (moi) | **Je ne me sers d'aucun des deux pour bouger quoi que ce soit.** Le `VALIDE SOUS RESERVE` de `cm1-reperes` est inchange. Un chiffre que je ne sais pas reconcilier ne doit pas servir a lever une reserve |
| **La mecanique laisse represser un bouton deja barre**, et l'essai est consomme. Un enfant de CP peut bruler deux de ses trois essais sur le meme bouton (`SEG-12`) | `camille-po` | Question de conception, pas defaut de contenu. Je ne la tranche pas |
| **Aucun personnage adulte masculin dans `cp-mot-manquant`**, et les deux seules taches menageres sont faites par les deux personnages feminins (`PRE-14`). Aucune de mes conditions ne le couvrait : **c'est un manque de ma specification** | `camille-po` | A corriger a la reprise, en changeant deux prenoms |

## Sur quelles proprietes je reste la seule garde

**Vingt-sept conditions sur les trois paquets, dont trois seulement sont partiellement tenues par un
test** - la regle 1 du contrat de forme (`S21-5`) couvre la clause de nombre de `SEG-3`, `PRE-6` et
`GRA-8`, et rien de plus. **Les vingt-quatre autres n'ont aucun garde-fou et ne doivent pas en
avoir** : un classifieur qui deciderait tout seul si un distracteur est plausible ou si une coupe
syllabique est stable se tromperait, et une regle qui passe pour une mauvaise raison est pire qu'une
regle absente.

**La contrepartie est que j'en suis le seul garde-fou, et ce sprint le montre** : la porte technique
est verte a **722 tests, 0 echec**, et elle n'a rien vu des trois motifs de refus. Restent sans
aucune couverture automatique :

- **l'exactitude du contenu** - comptes de syllabes, decidabilite des comparaisons, plausibilite
  d'un distracteur, unicite de la reponse defendable ;
- **qu'un indice n'enseigne pas une regle fausse** (`GRA-15` : *"pense a la taille pour deviner le
  poids"*, la confusion meme que la competence existe pour defaire) ;
- **qu'un libelle raccourci pour tenir dans un budget de forme enseigne encore quelque chose** ;
- **les biais de genre, d'origine, de milieu et de configuration familiale** ;
- **le pli a 744 x 551 et `SOR-4`**, tous deux hors `mvn verify` ;
- **360 px**, ou je n'ai aucune mesure et personne n'en a.

## Conclusion a reprendre dans `review.md`

*Revisee le 2026-08-28 apres l'arbitrage de l'utilisatrice sur `cheminee`. La version d'origine
disait "un valide sous reserve, deux refus" et donnait `cp-syllabes` pour refuse ; elle est
corrigee ici plutot que laissee a pourrir, et le detail des deux verdicts successifs est conserve,
date, dans `FICHE-bandit-manchot-syllabes-cp.md`.*

**Done pedagogique de s21 : deux valides sous reserve, un refus.**

`bandit-manchot-comparer-cp` est **VALIDE SOUS RESERVE** - dix conditions sur dix tenues, 18 items
sur 18 decidables sans instrument, aucune erreur factuelle, sept reserves non bloquantes.
`bandit-manchot-syllabes-cp` est **VALIDE SOUS RESERVE** : je l'avais refuse sur l'item `CHEMINEE`,
**l'utilisatrice a tranche que le mot vaut trois syllabes, et le motif est tombe**. Une reserve
neuve (`SEG-13`) y est ouverte. `bandit-manchot-mot-manquant-cp` **reste REFUSE apres reaudit de la
correction `fabd6cb`** : `lea-dev` a corrige deux items sur trois, et bien - mais l'item 3 a
desormais **trois reponses defendables sur trois**, et **le triplet en cause est celui que j'avais
moi-meme nomme**.

**Ce que ce sprint doit retenir de mon lot, et ce n'est pas a l'avantage de la porte que je
tiens.** Mon refus de `cp-syllabes` n'etait pas une erreur d'application : c'etait l'application
exacte d'une condition que j'avais ecrite **avant** que le paquet existe, et cette condition etait
**environ quatre fois trop large** - mesure du 2026-08-28 sur les 650 mots du corpus CP du depot :
39 cas de schwa attrapes, 30 a tort, dont `petit`, `cheval`, `renard` et `semaine`. **C'est la
troisieme fois en deux sprints qu'une de mes conditions ou de mes exemples, et non le travail
qu'ils encadraient, est la cause racine d'un refus** - a s20 des libelles que j'avais dits courts
et qui faisaient 34 caracteres ; ce matin `SEG-1` ; et au reaudit de `cp-mot-manquant` le triplet
`crayon`/`feutre`/`pinceau` que j'avais qualifie d'*"evident"* et que `lea-dev` a repris au mot
pres. **Le defaut commun est identifiable : je propose des exemples la ou je devrais nommer la
propriete a tenir.** Un exemple se revele faux ; une propriete se verifie. Un arbitrage prealable
evite un cycle quand il est juste ; il en coute un quand il est faux, et il faut le compter.

**Le sprint a atteint ce qu'il visait sur le pli** : les 50 items neufs sont au-dessus, `SOR-4` est
fermee, et le pli est desormais ferme sur les 162 items des 9 jeux CP enumeres. **Ce n'est pas la
forme qui a fait echouer un paquet sur trois, c'est le contenu**, et c'est la seule chose que la CI
ne regardera jamais.
