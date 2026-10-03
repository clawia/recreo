# Fiche de conformité - bandit-manchot-problemes-etapes-ce1

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-problemes-etapes.yaml`
  (24 items, type `question`), écrit par `marc-dev` (`d08d6bbe`).
- **Entrée de catalogue** : `bandit-manchot-problemes-etapes-ce1`, titre servi *"Les problèmes à
  étapes"*, consigne servie *"Lis bien, puis trouve la réponse !"*, `nombreDeManches: 10`.
- **Ligne déclarée** : `MAT-CE1-08`. **Décision** : `DEC-0106`.
- **Compétence programme visée** : `squad/programme/mathematiques/competences-ce1.md`, `MAT-CE1-08`,
  *"Résoudre des problèmes d'addition, de soustraction et de multiplication à une ou deux étapes."*
- **Conditions opposables** : `PRO-CE1-1` à `PRO-CE1-7`
  (`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md`, section 2.3), `LON-CE1-1`,
  `LON-CE1-2` (`ARBITRAGE-s36-longueur-d-enonce-au-ce1.md`), `TYP-1` (`ADR-0067`).
- **Statut global** : **VALIDÉ SOUS RÉSERVE** le 2026-10-03 (s48, instance A), deux réserves non
  bloquantes, `PRO-CE1-R1` et `PRO-CE1-R2`. Fiche ouverte par ce verdict : aucune fiche n'existait
  pour ce jeu.
- **Dernière mise à jour** : 2026-10-03 (second passage, s49) : **VALIDÉ**. `PRO-CE1-R1` et
  `PRO-CE1-R2` levées. Le verdict de s48 reste écrit tel quel.

---

## Bloc de validation pédagogique

### Verdict du 2026-10-03 (`nadia-prof`, s48, instance A) : **VALIDÉ SOUS RÉSERVE**

**Done pédagogique prononcé.** Les sept conditions sont tenues sur les 24 items, sous deux réserves
datées de ce jour (section 5). `camille-po` peut passer `MAT-CE1-08` à `couvert-valide` et faire une
story de chaque réserve.

#### 1. Ce que j'ai fait

**Audit joué, avec une limite que je déclare.** Arbre `round-two` à `bc1e7835`, sans worktree dédié :
je n'ai rien construit. J'ai lancé `bootstrap/target/recreo.jar`, produit par le `clean verify` du
rapport qualité de `yanis-qa` à ce commit, après avoir vérifié que les sept fichiers YAML de s48
contenus dans le jar sont identiques octet pour octet à ceux de l'arbre. Port dédié, libre avant le
lancement, servi par mon propre processus. Je n'ai lancé aucun job de la CI et je n'en déduis rien :
le Done technique est celui de `yanis-qa`. **Rendu non vu au navigateur** : je n'ai joué que par
appels HTTP réels et lu le HTML servi. Le gabarit n'a pas changé, mais les énoncés de ce paquet sont
parmi les plus longs du CE1 ; la mise en page à 768 px reste à voir.

**Huit parties complètes de dix manches** : sept avec une erreur sur une manche sur deux (deux
erreurs, puis la bonne réponse), une en pressant la même mauvaise réponse jusqu'à la révélation.
**Les 24 items ont été tirés et joués.** Après une erreur : *"Ce n'est pas ça. Essaie encore !"* et
l'indice. Après la troisième : *"Appuie sur la bonne réponse : ..."*. Fin de partie atteinte. Les trois
boutons servis sont, à chaque manche, la bonne réponse et les deux distracteurs du paquet, dans un
ordre qui change d'une partie à l'autre.

Les 24 items recalculés à la main : résultat, résultat intermédiaire, valeur de chaque famille
déclarée, rang de la bonne réponse.

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne et pas sa voisine plus facile. Quatorze items à deux étapes, dont
huit avec une multiplication : c'est ce qui sépare `MAT-CE1-08` de `MAT-CP-06` (une étape, addition et
soustraction), et l'arbitrage l'exigeait. Aucun item n'est un problème multiplicatif à une étape
(`MAT-CE1-06`), aucun n'est un achat chiffré (`MAT-CE1-13`), aucun n'est une durée (`MAT-CE1-12`).

#### 3. Les sept conditions

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `PRO-CE1-1` | **oui** | 14 items à deux étapes (58 %), 10 à une étape (42 %), aucun à trois |
| `PRO-CE1-2` | **oui** | addition étape de 15 items, soustraction de 15, multiplication de 8 (33 %) |
| `PRO-CE1-3` | **oui** | les dix items à une étape sont une addition ou une soustraction |
| `PRO-CE1-4` | **oui, voir `PRO-CE1-R2`** | aucun nombre ni résultat au-delà de 85, aucune division, aucun partage ni groupement, aucun prix ni pièce. Chaque multiplication est un fait des tables de 2, 3, 4, 5 et 10. L'item 8 porte une vente |
| `PRO-CE1-5` | **oui, voir `PRO-CE1-R1`** | chaque distracteur vaut sa famille déclarée. Rangs : la plus petite 4 (17 %), le milieu 11 (46 %), la plus grande 9 (37,5 %). La plausibilité de la famille (c), que je tiens seule, n'est pas atteinte sur trois items |
| `PRO-CE1-6` | **oui** | énoncés de deux ou trois phrases, la dernière étant la question. Aucun jardin, animal, chambre, voiture, vacances ni argent de poche. Les sujets sont des collectifs (la classe, la cantine, le club) : aucun rôle n'est distribué par genre |
| `PRO-CE1-7` | **oui** | 24 items. L'indice donne la méthode de la première étape sans aucun résultat, douze mots au plus (onze en coupant aux seuls blancs, douze en séparant l'élision de *d'abord*), et il change d'un item à l'autre |

**Exactitude** : les 24 résultats sont justes, les 48 distracteurs valent leur famille.

#### 4. Les deux choix tranchés sans moi, Q1 et Q2 de l'avis de faisabilité de s48

**Q1 - confirmé.** *"Aucune opération qui exigerait une technique posée"* se lit comme `marc-dev`
l'a lu : nombres et résultats dans la limite de 100, celle de `MAT-CE1-03`, et toute multiplication est
un fait des tables de `MAT-CE1-07`. C'est ce que dit ma propre phrase (*"à la portée du calcul mental
que `MAT-CE1-03` a fermé"*), et elle s'encode. **Une précision, pour que le test ne lise pas plus que
moi** : le plafond de 20 % que j'ai mis à s37 sur les calculs à deux chiffres avec retenue
(`ADD-CE1-2`, ligne 8) est une règle de distribution du paquet de calcul mental de `MAT-CE1-03`, pas
une définition de la technique posée. Il ne s'applique pas ici. `38 + 14` ou `60 - 22` restent du
calcul mental de CE1.

**Q2 - confirmé pour la durée, précisé pour la voiture.** Un problème qui porte sur une durée sert
`MAT-CE1-12` et n'a pas sa place ici (`ADR-0081`, un item une ligne) : je confirme l'exclusion. Pour la
voiture, ma condition exclut **la voiture à la maison**, c'est-à-dire ce que la situation suppose de la
famille de l'enfant, pas le mot. Ne servir aucune voiture est conforme. Le test du paquet, qui
interdit le mot, est plus strict que ma condition : c'est un choix de fabrication, pas ma barre. Il en
va de même pour *animal* et *chien*.

#### 5. Les deux réserves, datées du 2026-10-03

**`PRO-CE1-R1` (non bloquante) - la famille (c) n'est pas une mélecture plausible sur les items 9, 10
et 11.** Ces trois énoncés disent *"reçoit 40 images, puis 10 images de plus"*, *"reçoit 38 pommes,
puis 14 pommes de plus"*, *"reçoit 44 perles, puis 16 perles de plus"*. La famille (c) y soustrait la
seconde arrivée de la première (`40 - 10`, `38 - 14`, `44 - 16`). Un enfant de CE1 qui lit *"de plus"*
ne soustrait pas : c'est la clause de `PRO-CE1-5` que je m'étais réservée, et elle ne tient pas sur ces
trois items. Joué : sur l'item 10, l'enfant choisit entre 52, 34 et 6 ; sur l'item 11, entre 60, 38 et
6. Sur l'item 9, la valeur (c) vaut 15, le nombre recopié de l'énoncé. **Ce qui lève la réserve** :
ces trois items déclarent deux autres familles, comme `PRO-CE1-5` le prévoit (*"sinon l'item déclare
deux autres familles"*), par exemple (a) et (b). Les rangs restent dans leurs bornes : la plus petite
passerait à 7, le milieu à 8, la plus grande resterait à 9. En reprenant ces items, l'indice des items
9 et 11 (*"les deux arrivées d'images"*, *"de perles"*) emploie *arrivée* comme nom, rare au CE1 :
celui de l'item 10 (*"les deux livraisons de pommes"*) se lit mieux.

**`PRO-CE1-R2` (non bloquante) - l'item 8 porte une vente.** *"Le magasin scolaire a 2 lots de 10
gommes. Il en vend 9."* `PRO-CE1-4` exclut l'achat ; une vente est le même échange vu de l'autre
côté. **Pourquoi ce n'est pas un refus** : l'item ne chiffre ni prix ni monnaie et ne sert pas
`MAT-CE1-13`. Le motif de l'exclusion, le doublon avec une ligne fermée, n'est pas atteint. Et un
*"magasin scolaire"* n'est pas un lieu que tous les enfants connaissent. **Ce qui lève la réserve** :
l'item 8 ne porte ni magasin ni vente, par exemple *"La réserve a 2 lots de 10 gommes. Elle en
donne 9."*

#### 6. Ce dont je reste la seule garde

- La plausibilité de chaque distracteur de famille (c), item par item (`PRO-CE1-R1` montre qu'elle a
  traversé une CI verte).
- Que l'indice donne la méthode de la première étape, et dans des mots de CE1.
- Les présupposés sociaux et les rôles par genre au-delà des mots que le test cherche.
- **Le lexique de `PRO-CE1-4` et de `PRO-CE1-6` est écrit sans accents dans le test du paquet**
  (`piece`, `achete`, `partagee`, `divise`) : un énoncé qui écrirait *"une pièce"* ou *"acheté"* ne
  serait pas trouvé. Je décris le symptôme ; la cause est à `theo-archi`, via `sofia-sm`. *Vente*,
  *vendre* et *magasin* n'y sont pas non plus (`PRO-CE1-R2`). Tant que ce n'est pas corrigé, c'est
  moi qui tiens ce lexique, en lisant.
- La mise en page à 768 px, non vue à ce passage.

**Identifiants** : deux neufs, `PRO-CE1-R1` et `PRO-CE1-R2`. Forme copiée de `TAB-CE1-R1` et
`DUR-CE1-R1` : trois segments, niveau au milieu, marqueur `R` dans le dernier (`ADR-0100`). Alloués
dans `squad/decisions/REGISTRE-identifiants.md`, section 4, dans le même diff. Aucun renommé.

**Signé** : `nadia-prof`, s48, instance A, 2026-10-03.

---

### Second passage du 2026-10-03 (`nadia-prof`, s49, instance A) : **VALIDÉ**

**Les deux réserves de s48 sont levées, chacune par la barre que j'avais écrite.** `MAT-CE1-08` reste
`couvert-valide`, sans réserve ouverte.

#### 1. Ce que j'ai fait

Contenu à `f5a5df9b` (`lea-dev`), arbre `round-two` à `bbfdde17`. Jar construit par
`bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests`, YAML du jar vérifié identique à
l'arbre, lancé sur un port dédié servi par mon propre processus. Aucun job de la CI lancé par moi.
**Huit parties complètes de dix manches** par appels HTTP réels : **les 24 items tirés**, les items 8,
10 et 11 joués avec une ou deux erreurs. `git show f5a5df9b` : l'en-tête du YAML, l'énoncé de
l'item 8, la famille, les distracteurs et l'indice des items 9 à 11, le lexique du test. Rendu non revu
au navigateur : aucun gabarit n'a changé.

#### 2. Les deux réserves

| Réserve | Barre écrite le 2026-10-03 (s48) | Servi | Tenu |
|---|---|---|---|
| `PRO-CE1-R1` | items 9, 10, 11 déclarent deux autres familles que (c), par exemple (a) et (b) ; rangs 7, 8, 9 | `fam=a,b` ; distracteurs `50`/`65`, `52`/`70`, `60`/`82`, recalculés juste à la main ; rangs 7 (29 %), 8 (33 %), 9 (37,5 %) | **oui** |
| `PRO-CE1-R2` | l'item 8 ne porte ni magasin ni vente | *"La réserve a 2 lots de 10 gommes. Elle en donne 9."*, ma forme mot pour mot | **oui** |

La famille (b) des items 9 à 11 additionne les trois nombres de l'énoncé (`40 + 10 + 15 = 65`) : c'est
la mélecture d'un enfant qui ne voit pas *"donne"*, plausible. Les indices des items 9 et 11 disent
désormais *livraisons*, comme celui de l'item 10 que j'avais cité en exemple.

#### 3. Le lexique ajouté par `lea-dev` au test

Vérifié forme par forme. **Les ajouts sont justes** : *magasin*, *vente*, *vendre* et leurs flexions
gardent le critère de `PRO-CE1-R2` ; les formes accentuées *pièce*, *acheté*, *partagé*, *divisé* et
leurs flexions sont celles que j'avais signalées ; *divise* sans accent est gardé à bon droit, c'est le
verbe (*il divise*). Aucun ajout n'exclut un mot que `PRO-CE1-4` admet.

**Ce qui manque encore**, constaté, pas reproché : *achète*, *achètes*, *achètent* (accent grave) ne
sont pas dans la liste. L'ancienne forme *achete* ne les trouvait pas non plus, et elle a été remplacée
par *acheté*, qui ne les trouve pas davantage. Manquent aussi *vends*, *vendons*, *partagent*,
*divisent*. Le paquet servi ne contient aucune de ces formes (relu ce jour) : ce n'est pas un défaut de
contenu, c'est un trou du garde-fou. Je décris le symptôme ; la cause est à `theo-archi`, via
`sofia-sm`. Pas de réserve, puisque rien de servi n'est faux.

#### 4. Ce dont je reste la seule garde

Inchangé (section 6 du premier passage), moins le lexique accentué que ce passage a corrigé, plus les
formes du présent listées en section 3 : jusqu'à ce qu'elles soient ajoutées, c'est moi qui les tiens,
en lisant.

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s49, instance A, 2026-10-03.
