# s57, palier 1 - `nadia-prof` : verdict du poste 7, reponses sur la forme du poste 8, les reserves de `camille-po`, `RP-ACCUEIL-147` et `Q-T6`

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-15, 09:49 (UTC+02:00), lu par `date`.
- **Arbre** : branche `s57-nadia-forme2`, worktree dedie, partie de `round-two` a `1a3ec5d9`.
- **Instances** : A (poste 7), B (poste 8, `RP-ACCUEIL-147`), A' et A (`Q-R1`, `Q-R2`, `Q-R4`).
- **Lu** : `avis-theo-poste-8-art-cp-10.md` (annexe B entiere, B.7 compris) ; `proposition-ines-poste-8-art-cp-10.md` (revision 2) et
  `proposition-ines-poste-8-coupure-du-verdict.md` ; `releve-yanis-palier-1.md` (section 4) ; `releve-relecteur-palier-1.md` ;
  `docs/backlog/backlog.md` (stories `S57-TPH-CE1-R1`, `S57-GNA-CE1-R1`, `S57-VSJ-CE1-R2`, `RP-ACCUEIL-147`) ;
  `rapport-marc-dev.md` et le diff de `s57-marc-fra-ce1-04` ; `cloture-palier-1.md` ; `composition-palier-1.md`.
- **Joue** : le jar du poste 7 (fiche, section 1). **Non joue** : la forme du poste 8, qu'aucun jar ne sert encore ; je reponds
  sur les mesures d'`ines-design` et je le dis a chaque point qui en depend.

## Resume

| Code | Point | Decision | Section |
|---|---|---|---|
| R1 | Poste 7, `FRA-CE1-04` | **VALIDE SOUS RESERVE**, reserve neuve non bloquante `RCT-CE1-R1` ; titre *"Lis et réponds"* tenu ; l'intrus *"la"* / *"se"* n'est ni un refus ni une reserve | 1 |
| R2 | Poste 8, police du poste (`ines-design`, R2.5 question 1) | **Mes barres se jugent sur la police que le jar sert sur le poste de mesure.** Verdana, Comic Sans MS, Trebuchet MS et Courier New ne sont pas servies par la feuille | 2.1 |
| R3 | Poste 8, avec ou sans `L4` (question 2) | **Avec `L4`** | 2.2 |
| R4 | Poste 8, coupure de la phrase de `M4` (question 3 ; question 2 de la proposition sur la coupure) | **Oui** : jamais de coupure dans une de ses deux phrases. Condition de forme neuve, **pas une barre du refus** | 2.3 |
| R5 | `Q-T4` (question 1 de la proposition sur la coupure) | **Forme C : la coupure entre les deux phrases vaut pour tous les jeux** | 2.4 |
| R6 | `Q-T5` | **On cherche la forme commune sans `L1` ni `L2`.** Je n'accorde rien d'avance sur 143 jeux | 3 |
| R7 | `yanis-qa`, `Q1` | **Aucune exemption `aria-hidden`**, ni pour les attributs ni pour les noeuds de texte. Correction de ma formulation de `Q-C1` | 4.1 |
| R8 | `yanis-qa`, `Q2` | **Oui** : `aria-description`, `aria-roledescription`, `placeholder`, `value`, les cibles d'`aria-labelledby` et d'`aria-describedby` ; toute `<img>` porte un `alt` | 4.2 |
| R9 | `Q-R1` | **Prix, instances et palier confirmes** ; une quatrieme story, `RCT-CE1-R1`, sur le meme regime | 5.1 |
| R10 | `Q-R2` | **Non** : la revelation ne fait pas partie de la levee | 5.2 |
| R11 | `Q-R4` | **Oui, trois ajouts** : les 11 items de `ce1-texte-narratif` au-dessus du pli, un jeu de CP, un jeu de CM1, mesures sur le jar de base de la forme | 5.3 |
| R12 | `RP-ACCUEIL-147` | **Fait, voie (b)** : les deux occurrences annotees de leur date et de leur cause. Le 147 etait faux des l'ecriture | 6 |
| R13 | `Q-T6` | **Avis : une story, de faible priorite**, sur la meme propriete que la forme C | 7 |

---

## 1. Poste 7 : `FRA-CE1-04`, VALIDE SOUS RESERVE

Verdict, motifs et mesures : `squad/conformite/FICHE-bandit-manchot-texte-narratif-ce1.md`, verdict du 2026-10-15. En bref :

- **Done pedagogique prononce** sur `4dd6f298`. `camille-po` passe `FRA-CE1-04` a `couvert-valide` apres la fusion de ce commit.
- **`D1` et `D2` tenus**, joues sur les 20 items. **Titre *"Lis et réponds"*** : tenu (fiche, section 4) ; aucun ecran ne montre
  celui du CP et celui du CE1 ensemble.
- **Sara et Nour, la question de `marc-dev`** : oui, la bonne reponse y est la seule a ne pas commencer par *"la"* ou par *"se"*.
  **Ce signe vient de ma forme d'exemple**, et je le dis. Mesure sur le paquet : l'intrus du premier mot designe la bonne reponse
  sur 4 items et un distracteur sur 2. Irregulier, sans sens enseigne, et sur Sara il recouvre une lecture juste de la reprise :
  **ni refus ni reserve** (fiche, section 3). Je le compte desormais sur les lots a texte support.
- **`RCT-CE1-R1`**, non bloquante : a 744 x 551, apres une erreur, l'indice est sous le pli sur 9 items sur 20, et le verdict aussi
  sur 2 (Ibrahim, Chloé). Regime de `Q-N4` = non (fiche, section 5).
- **Pléonasme d'Inès** (question 3 de `marc-dev`) : recommandation non bloquante, inchangee. **Ni reserve ni critere** ; `camille-po`
  la prend ou non, je ne la durcis pas.

## 2. Poste 8 : les quatre questions d'`ines-design`

### 2.1 La police du poste (R2.5, question 1)

**Mes barres se jugent sur le jar, rendu par Chrome sur le poste de mesure, avec la police que la feuille y sert.** C'est la
methode de toutes mes cotes depuis s25, et c'est la seule que je peux refaire ; je ne la change pas apres coup.

**Ce que la feuille sert, lu dans le code** : `recreo.css` ne declare aucune `font-family` ; la page prend celle de Bootstrap,
`system-ui, -apple-system, "Segoe UI", Roboto, "Helvetica Neue", "Noto Sans", "Liberation Sans", Arial, sans-serif`. **Verdana,
Comic Sans MS, Trebuchet MS et Courier New ne sont dans cette liste a aucun rang** : un appareil de la cible (`DEC-0040`,
ordinateur et tablette) ne les recoit que si quelqu'un change la police de son navigateur. Les trois polices sur neuf ou `M4`
passe sous le pli ne sont donc pas un motif pour moi.

**Ce que je ne sais pas, dit** : les polices que la liste sert sur une tablette (San Francisco sur iPad, Roboto sur Android)
n'ont ete mesurees par personne, moi comprise. Arial, dernier recours de la liste, rend les memes cotes que la police du poste
(R2.4). Si un rejeu sur un de ces appareils devient possible, je le prends ; d'ici la, c'est un risque nomme, pas une barre.

### 2.2 Avec ou sans `L4` (question 2) : avec

**Avec `L4`.** Motif : `RDO-CP-R1` porte le milieu du chemin de l'enfant qui se trompe, et j'ai demande que la forme les vise
ensemble (`lecture-nadia-palier-1.md`, `D5`). Sans `L4`, l'indice reste 9 px sous le pli a 744 ; avec, il tient de 3 px.

**Ce que je paie, et pourquoi je l'accepte** : l'enonce perd 12 px d'air au-dessus du meuble, 8 px d'interligne les separent.
L'enonce d'`ART-CP-10` est une ligne, posee sur le cadre sombre du meuble : rien ne les confond a la lecture. **Je le regarde au
rejeu** : si l'enonce se lit comme une partie du meuble, je le dirai.

**Ce que les 3 px ne changent pas** : la barre du refus reste la sienne (`M4` a 744) ; si l'indice repasse sous le pli au
rejeu, `RDO-CP-R1` reste une reserve non bloquante. Une marge de 3 px sur une seule police n'est pas une garantie, et je ne
l'ecris pas comme telle.

### 2.3 La phrase de `M4` (question 3) : oui, jamais coupee dans une phrase

*"C'est cette œuvre qui va avec. Appuie dessus."* se coupe a 744 a l'interieur de sa premiere phrase (*"qui va / avec."*,
R2.3). **Je veux pour elle la meme propriete que pour le verdict** : une coupure entre les deux phrases, jamais dans une
phrase, et le texte ne deborde pas de sa boite. Meme motif : chaque ligne est une phrase entiere, plus facile a lire pour un
enfant de CP, et c'est la phrase que l'adulte lit au moment ou l'enfant apprend (`X7`).

**Ce que c'est, et ce que ce n'est pas.** Une condition de forme neuve, datee de ce jour. **Pas une barre du refus** (la phrase
au-dessus de y = 551 a 744, inchangee) ni de `RDO-CP-R1`. Si la forme livree ne la tient pas, ce sera au rejeu une reserve
neuve et non bloquante.

**Ce qui est a d'autres** : la phrase est un texte du gabarit (`fragments/manche.html` ligne 212), pas une constante du
controleur ; le moyen, et sa place sous `ADR-0067`, sont a `theo-archi` et `ines-design`. Si *"jamais coupee"* et *"ne deborde
pas"* ne tiennent pas ensemble a 744 dans la colonne de 1fr, la mesure me revient et je tranche.

### 2.4 `Q-T4` : forme C

**Forme C : la coupure entre les deux phrases du verdict vaut pour tous les jeux.** C'est la condition de `TB2` que `theo-archi`
me pose ; je la leve.

**Motif.** La propriete n'est pas propre a `ART-CP-10` : un enfant de CP lit plus facilement une ligne qui est une phrase
entiere, sur tous les jeux, et une coupure *"Essaie / encore !"* ou un `!` seul en debut de ligne n'est meilleur nulle part. Le
seul motif de limiter la regle a un ecran serait qu'elle abime un autre jeu. **La mesure dit le contraire** (proposition sur la
coupure, section 3) : sur un jeu servi, rien ne change a 576 px et plus ; en dessous, 5 coupures sur 6 tombaient dans une phrase
et la sixieme detachait le `!`, et toutes passent entre les phrases. Les largeurs ou quelque chose change (320 a 390 px) sont
celles du telephone, hors de la cible (`DEC-0040`) : **sur l'ordinateur et la tablette, aucun autre jeu ne change a l'oeil, et
la ou il change, il change en mieux.**

**Mes deux conditions** : le texte lu par l'enfant ne change pas d'un mot ni d'un signe, seules les espaces changent ; et le
garde-fou de `B.4` existe dans le meme commit, puisque `theo-archi` montre qu'aucun test d'aujourd'hui ne verrait une espace
ordinaire retapee. **Ce qui n'est pas mesure, nomme** : les ecrans de designation et de saisie (non rendus, section 5, point 6 de
la proposition) et la lecture par un lecteur d'ecran (`F11`). Ils ne changent pas ma reponse : U+00A0 est deja servie devant
chaque signe double de tous les paquets. Je regarde le verdict de designation et de saisie au premier rejeu d'un de ces jeux.

**L'orthotypographie de ces constantes** (l'espace devant `!`, `TYP-1`) reste au `relecteur-pedagogique` (`DEC-0002`, `DEC-0056`) ;
je ne juge ici que la coupure.

## 3. `Q-T5` : la forme commune se cherche sans `L1` ni `L2`

**On cherche sans eux.** J'ai accepte `L1` et `L2` *"sur cet ecran seul"* (`reponse-nadia-forme-poste-8.md`, sections 1 et 3), et
je ne les etends pas a 143 jeux sur un gain que personne n'a mesure (`TB4`, point 4).

**Pourquoi ce n'est pas un refus d'avance.** Mon motif de `L1` (*"entre un sourire decoratif et la phrase qu'on lui lit, je garde
la phrase"*) vaut aussi pour l'indice d'un enfant de CE1. Mais la forme commune ne peut pas dependre du paquet (critere 4 de
`S57-TPH-CE1-R1`) : `L1` retirerait le visage et les etoiles de tous les jeux, y compris des 11 items de `ce1-texte-narratif`, des
22 de `ce1-verbe-sujet` et des jeux de CP ou l'indice tient deja au-dessus du pli. **Retirer l'identite de la machine la ou elle
ne coute rien a l'enfant, c'est payer sans rien acheter.**

**L'ordre** :

1. `ines-design` mesure, sur un jar qui sert les quatre paquets (`ce1-phrases-types`, `ce1-accord-groupe-nominal`,
   `ce1-verbe-sujet` et, s'il est fusionne, `ce1-texte-narratif`), une forme sans `L1` ni `L2` : place du message (`L3`), marges
   (`L4`), rangees des propositions.
2. **Si elle leve les reserves** : je la juge sur sa proposition, puis au rejeu ; `L1` et `L2` ne se posent pas.
3. **Si elle ne les leve pas** : la proposition m'apporte les cotes avec et sans `L1`, avec et sans `L2`, sur ces paquets **et**
   sur la clause de non-regression (5.3). Je tranche sur ce releve, pas avant.

`L2` (le score a droite de la consigne) me coute moins que `L1` ; je ne l'accorde pas pour autant d'avance, la consigne des ecrans
a texte support etant plus longue que celle d'`ART-CP-10`.

## 4. Les questions de `yanis-qa` sur `D7`

### 4.1 `Q1` : aucune exemption `aria-hidden`

**Correction de ma formulation de `Q-C1`.** J'ai ecrit (`rejeu-nadia-palier-1.md`, section 5) : *"Un element sous
`aria-hidden="true"` n'en rend aucun (l'icone du signal)."* Je decrivais l'icone d'aujourd'hui ; la phrase se lit comme une
exemption. **`yanis-qa` a raison sur le fond** : `aria-hidden` retire un element du lecteur d'ecran, pas de l'oeil. Un noeud de
texte sous `aria-hidden` se lit, un `title` s'affiche au survol, un `alt` s'affiche quand l'image ne se charge pas.

> **`D7`, precisee** : sur l'ecran de fin d'un jeu dont la bonne reponse est une reproduction, juste ou revele, tout texte du
> fragment `retour` - noeud de texte ou valeur d'un attribut de la liste de 4.2 -, **sous `aria-hidden` ou non**, appartient a la
> liste fermee de `D7`, ou est vide.

Aucune equipe n'a ete jugee contre l'ancienne phrase : le test n'existe pas. Elle ne fait aucun faux rouge aujourd'hui (l'icone
n'a ni texte ni attribut, mesure de `yanis-qa`). C'est plus strict que sa forme par defaut, qui exemptait `alt` et
`aria-label` : sans exemption, il n'y a rien a decider du cas ou elle servirait.

### 4.2 `Q2` : les autres attributs

**Oui.** La propriete de `D7` porte sur ce que l'ecran donne a lire ou a entendre, pas sur trois noms d'attributs. Liste qui la
rend testable :

1. les valeurs d'`alt`, `title`, `aria-label`, `aria-description`, `aria-roledescription`, `placeholder` et `value` ;
2. pour `aria-labelledby` et `aria-describedby`, le texte des elements cibles, qui doivent etre dans le fragment (un renvoi hors du
   fragment est un rouge) ;
3. **toute `<img>` porte un attribut `alt`**, vide permis : sans lui, certains lecteurs d'ecran annoncent le nom du fichier, et un
   nom de fichier de reproduction nomme son sujet (`CON-5`).

Aucun n'est present dans `retour.html` aujourd'hui (`yanis-qa`, lu) : pas de faux rouge. **Ce que le test ne verra pas, et que je
garde** : un texte pose par la feuille (`content` d'un pseudo-element) ou par le JavaScript apres le rendu.

## 5. Les stories de reserve de `camille-po`

### 5.1 `Q-R1` : prix, instances, palier

| Story | Prix | Instance | Palier | Decision |
|---|---|---|---|---|
| `S57-TPH-CE1-R1` | 0,5 | A' | 2 au plus tot | **confirme** : premier rejeu de la forme, 24 items et la clause 3 |
| `S57-GNA-CE1-R1` | 0,25 ; 0,5 si la forme de ce paquet differe | A' | 2, avec ou apres `TPH` | **confirme** |
| `S57-VSJ-CE1-R2` | 0,25 si le paquet est inchange ; 0,5 si des textes changent | A | 2 | **confirme** |
| `RCT-CE1-R1`, a ecrire | 0,25 si le paquet est inchange ; 0,5 si des textes changent | A | 2, apres la fusion de `4dd6f298` | **propose** : meme regime que `VSJ-CE1-R2`, deux voies (fiche, section 5) |

**Condition d'engagement** : aucune de ces stories ne s'engage avant la mesure de la forme commune (section 3, point 1), sauf par
la voie des textes pour `VSJ-CE1-R2` et `RCT-CE1-R1`. Les prix sont pris sur le palier qui les engage, jamais sur les marges du
palier 1.

### 5.2 `Q-R2` : la revelation n'entre pas dans la levee

**Non.** Le critere de levee de `TPH-CE1-R1` est ce que j'ai ecrit : le bas du bloc `.indice`. La revelation decrite dans la
fiche est le contexte du constat, pas une seconde barre ; l'y ajouter maintenant, ce serait relever ma barre apres coup. Motif de
fond : a la revelation, l'enfant voit deux propositions barrees et la seule qui reste, qui est la bonne ; il finit la manche.
Celui qui a besoin de l'indice, c'est l'enfant qui se trompe une premiere fois. Meme reponse pour `GNA-CE1-R1`, `VSJ-CE1-R2` et
`RCT-CE1-R1`.

### 5.3 `Q-R4` : la clause de non-regression

**Oui, d'autres jeux y entrent.** La forme commune change l'ecran des 143 jeux `bandit-manchot` ; une clause qui ne regarde que
du francais de CE1 ne verrait pas un jeu de CP pousse sous le pli. Trois ajouts au critere 3 de `S57-TPH-CE1-R1` :

1. **les 11 items de `ce1-texte-narratif`** dont l'indice finit a 550 (fiche, section 5), si `4dd6f298` est fusionne avant la
   forme ;
2. **`bandit-manchot-additions-cp`**, tous ses items : le plus jeune lecteur, propositions courtes ;
3. **`bandit-manchot-comprehension-cm1`**, tous ses items : un texte support de CM1.

**La cote de reference de 2 et 3 se mesure sur le jar de base de la forme**, item par item, avec la methode etalonnee de la fiche
de `bandit-manchot-verbe-sujet-ce1` (rejeu du 2026-10-14, section 1), **jamais recopiee d'une fiche ancienne** : la page a change
depuis la plupart de ces mesures (`squad/lessons.md`, s56). **Sortie** : tout item dont l'indice finit au-dessus de y = 551 sur le
jar de base y reste apres le commit. Je ne pose aucune barre neuve sur un item deja sous le pli.

## 6. `RP-ACCUEIL-147` : fait

**Correction de mon propre constat, avant d'arbitrer.** Le 147 des lignes 41 et 236 de
`squad/conformite/ARBITRAGE-PREALABLE-accueil-par-matiere.md` **n'a jamais ete juste** : j'ai ecrit le fichier sur `aa99f18d`, et
`git show aa99f18d:adapters/adapter-content/src/main/resources/catalogue.yaml | grep -c "^  - id:"` rend **146** ;
`13d00bfe` (le retrait d'`ART-CP-10`) en est deja ancetre. J'ai recopie un compte anterieur au retrait sans le lire sur mon
arbre. Le relecteur et `camille-po` disaient *"146 depuis le retrait"* ; c'etait vrai le jour meme.

**Voie (b) de mon choix, annotation datee** (critere 1 amende par `camille-po`, critere 2). Motif : les deux phrases decrivent le
produit du 2026-10-11. Y ecrire 148, le compte d'aujourd'hui (`grep -c "^  - id:"` sur `catalogue.yaml` a `1a3ec5d9`), melangerait
deux dates dans une meme phrase ; et la phrase de la ligne 41 ne decrit plus le produit (`/` demande la classe, constate sur le jar
du poste 7). Chaque occurrence porte une mention datee qui renvoie a une annotation en fin de fichier, avec la cause, le compte du
jour et la commande. La ligne 398 n'est pas touchee.

**Instance et moment** (`Q-S3`, `DEC-0120` A.74) : B, le 2026-10-15, avant la cloture du palier 1 ; pris sur l'arbitrage
prealable de B, sans prix neuf. `grep -n 147` sur le fichier rend 41, 236 et 398, aux memes numeros qu'avant, plus deux
lignes de l'annotation (410, 424).

## 7. `Q-T6` : avis pour `camille-po`

**Une story, de faible priorite, sur la meme propriete que la forme C.** *"Ta partie s'est arrêtée. Choisis un nouveau jeu !"* est
lu par un enfant de CP, seul sur l'ecran d'accueil, apres une partie perdue : le `!` detache de son mot ou une coupure dans une
phrase y genent la lecture comme dans le verdict. **Pourquoi pas plus haut** : sur la cible (ordinateur et tablette), ces deux
phrases courtes tiennent sur une ligne a ma connaissance ; je ne l'ai pas mesure, et c'est la premiere chose a mesurer.

**Ce que je demande si elle l'ecrit** : la propriete de `B.4` (aucune espace ordinaire devant un signe double, aucune coupure dans
une phrase) sur tout texte que le controleur ecrit lui-meme, pas seulement sur les verdicts : un perimetre derive des constantes
d'une seule famille laisse echapper la suivante, comme `theo-archi` l'ecrit pour les verdicts. Le texte ne change pas. La forme,
le moment et le prix sont a `camille-po`, `theo-archi` et `sofia-sm` ; l'orthotypographie au `relecteur-pedagogique`.

## 8. Ce dont je reste la seule garde, ajoute par ce fichier

| Propriete | Pourquoi aucun test ne la tient |
|---|---|
| L'intrus du premier mot sur les lots a texte support | un signe se compte, mais seule la lecture dit s'il repond a la place de l'enfant |
| La lisibilite de l'enonce colle au meuble avec `L4` | une cote ne dit pas si deux blocs se confondent |
| La coupure de la phrase de `M4`, et celle du verdict sur les ecrans de designation et de saisie | `J1` a `J5` ne jugent pas la coupure (avis, 4.2) |
| Un texte pose par la feuille ou le JavaScript sur l'ecran de fin | le test de `D7` lit le fragment rendu par le serveur |

## 9. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | `FRA-CE1-04` a `couvert-valide` apres fusion de `4dd6f298` ; story de `RCT-CE1-R1` (fiche, section 5 ; prix en 5.1) ; `Q-R1`, `Q-R2`, `Q-R4` repondues ; `Q-T6` (section 7) ; le pleonasme d'Inès, a elle |
| `sofia-sm` | ce verdict et `RP-ACCUEIL-147` (fait) pour `cloture-palier-1.md`, sections 1 et 2 ; le commit joue, `4dd6f298`, a comparer au contenu fusionne |
| `theo-archi` | `Q-T4` : forme C, conditions en 2.4 ; `TB2` peut passer, l'amendement de B.7 n'attend plus que le commit du developpeur ; `Q-T5` (section 3) ; la coupure de `M4` (2.3), dont le moyen est le sien |
| `ines-design` | 2.1 a 2.3 ; la mesure de la section 3, point 1 |
| `yanis-qa` | `Q1` et `Q2` (section 4) pour le test de `D7` |
| `lea-dev` | `L4` dans la feuille (2.2) ; la forme C apres `P3` de `theo-archi`, levee par 2.4 |
| `marc-dev` | rien a reprendre sur `4dd6f298` |

## 10. Ce que ce fichier ne fait pas

Il ne fusionne rien, ne touche ni la matrice, ni un catalogue, ni un paquet, ni du code. Il ne juge pas la forme du poste 8 sur un
jar : aucun ne la sert. Il ne repond pas aux points `N1` a `N4` et `D1` a `D5` de `releve-relecteur-palier-1.md`, qui n'etaient pas
dans la demande de cette tache. **Identifiant ecrit et alloue** : `RCT-CE1-R1` (fiche du poste 7 et registre, meme commit) ; les
codes `R1` a `R13` sont des reperes de ce fichier.

**Tarifs** : A, 1,0 (audit du poste 7) ; B, aucun prix neuf (reponses sur l'arbitrage de B, `RP-ACCUEIL-147` compris). Marges de
rejeu de A, A' et B intactes.

**Signe** : `nadia-prof`, s57, palier 1, instances A, A' et B, 2026-10-15.

## 11. Jobs de `ci.yml` lances pour ce commit

Sur ce worktree, les quatre fichiers du commit dans l'index, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl` : `BUILD SUCCESS`, 2548 tests (120, 31, 486, 1534,
  371 et 6 d'integration), 0 echec, 0 erreur, 0 ignore ; JDK 21.0.10 (`java.version` des rapports de `bootstrap`). Le build a lu
  ce fichier avant l'ajout de cette section, qui ne contient aucun tableau. Avant lui, le garde-fou du registre : *"declare 984
  identifiants alloues et 178 prefixes distincts, sa table en porte 985 et 178"*, ligne d'etat portee a 985 / 178. Etape
  *"Package du fat jar"* non lancee.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` :
  sort 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; le
  sujet du commit passe dans `verifier-prefixes.sh` : sort 0. Le controle du proprietaire du garde-fou de l'accent n'est pas lance :
  aucun de ses chemins n'est touche.
- **`outils`** : non lance ; aucun outil n'est modifie.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.
