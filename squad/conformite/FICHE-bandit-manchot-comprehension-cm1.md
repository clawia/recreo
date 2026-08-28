# Fiche de conformite - bandit-manchot-comprehension-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cm1-comprehension.yaml`
  (a creer, `type: question`, au moins 20 items), story `S17-2` (`docs/backlog/backlog.md`). Matiere
  premiere identifiee a l'ouverture : `contenu-repris/quiz/cm1-comprehension.yml` (8 items) -
  **REFUSEE comme matiere premiere, voir mise a jour ci-dessous.**
- **Statut global** : **en construction** - aucun code, aucun contenu neuf ecrit a l'ouverture de
  cette fiche. Arbitrage prealable rendu le 2026-08-19 (voir section dediee) : principe **ACCEPTE**
  sous cinq conditions bloquantes (COMP-1 a COMP-5) ; matiere premiere **REFUSEE**. Aucun code ecrit
  a ce jour.
- **Derniere mise a jour** : 2026-08-19 (arbitrage prealable de `nadia-prof`, `camille-po` en tire
  les consequences sur cette fiche).
  Complete le 2026-08-19 par le jugement de lisibilite du texte-support de `nadia-prof`
  (COMP-5 satisfaite, COMP-6 et COMP-7 nouvelles, budget de 8 lignes retire) - derniere section.
- **Question posee le 2026-08-28 (ouverture s22, `camille-po`, `DEC-0053` section 5)** : l'utilisatrice
  arbitre que le pli sort de la porte pedagogique sur tout le catalogue, paquets neufs compris
  (`DEC-0053`, arbitrage 1). `COMP-4` et `COMP-7` ci-dessous sont exactement un critere de cette
  famille ("entierement visibles a 360 x 640 sans defiler"), que vous avez ecrit le 2026-08-19 comme
  non negociable pour ce paquet precis ("ma condition sur le resultat ... ne bouge pas"). **Confirmez-
  vous que l'arbitrage de l'utilisatrice couvre aussi ce cas, et que le budget de 4 lignes / 20 mots
  cesse d'etre bloquant (devenant une reserve non bloquante, comme pour `REP-8`), ou maintenez-vous
  cette condition specifique en dehors du regime nouveau ?** Je n'ai pas de position sur cette
  question pedagogique ; je la pose, je n'y reponds pas. Reponse attendue avant tout premier commit
  de code sur ce paquet si `S17-2` est retenue au perimetre de s22.

**Mise a jour du 2026-08-19, apres l'arbitrage prealable de `nadia-prof`**
(`squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md`, section 1, branche
`s17-nadia`, tete `c5d2bce`, worktree dedie non fusionne). Elle a mesure les huit items de
`contenu-repris/quiz/cm1-comprehension.yml`
un par un : **8 sur 8** ont une bonne reponse recopiee mot pour mot du texte ; **4 sur 8** ont des
distracteurs absents du texte ; **au moins 4 sur 8** se repondent par la seule connaissance du monde,
texte masque ; chaque support est **une seule phrase**, donc **zero item sur huit** ne demande
l'inference que le referentiel nomme. Sa conclusion, au mot pres : *"ce n'est pas une reprise, c'est
un paquet neuf"*. **Je retire donc l'economie de reprise de mon estimation** : ce paquet est un
contenu integralement neuf, au meme rang de cout qu'une ligne sans aucune matiere premiere
identifiee (voir `DEC-0039`, amendement du 2026-08-19, section 1, pour ce que cela change a mon
plafond d'ouvertures). Les huit items repris ne sont pas retouches ; ils ne sont pas repris du tout.

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Lecture et comprehension de l'ecrit | Comprendre un texte narratif ou documentaire (questions explicites et implicites, inferences simples) | `squad/programme/francais/competences-cm1.md` |

**Perimetre exact, assume a l'ouverture.** Le referentiel porte deux competences voisines dans le
meme domaine : celle-ci ("comprendre", questions sur un texte) et "lire silencieusement un texte de
longueur croissante et en restituer l'essentiel". Cette fiche ne couvre que la premiere. La seconde
demande a l'enfant de produire un resume dans ses propres mots ; le mecanisme du bandit manchot ne
sait que faire choisir une proposition parmi plusieurs, jamais recueillir une production libre. Ce
n'est pas un oubli : c'est un report explicite, nomme dans `DEC-0039` et dans
`docs/backlog/backlog.md`, sur le meme motif structurel que "reperage sur une droite numerique" en
mathematiques CM1 (`squad/programme/MATRICE-couverture.md`, section mathematiques CM1).

## Criteres pedagogiques attendus - camille-po

**Mise a jour du 2026-08-19** : cette checklist reprend au mot pres les cinq conditions bloquantes
de l'arbitrage prealable de `nadia-prof` (COMP-1 a COMP-5), pour que `yanis-qa` en tire un test sans
avoir a lire deux documents. Le critere "sans gabarit ni feuille de style neuve" de l'ouverture de
cette fiche est **retire** : `nadia-prof` a mesure qu'il est incompatible avec la competence visee
(voir "Arbitrage prealable" ci-dessous) ; je le leve plutot que de sortir la story du perimetre.

- [ ] **COMP-1.** Texte visible, question et propositions masquees : aucun item ne se resout en
  cherchant laquelle des propositions figure dans le texte. Consequence executable : toutes les
  propositions d'un item emploient des mots presents dans le texte, ou aucune ne le fait - jamais
  une seule.
- [ ] **COMP-2.** Question et propositions visibles, texte masque : un enfant qui n'a pas lu le
  texte ne fait pas mieux que le hasard - ni fait de culture generale, ni distracteur absurde qui
  s'elimine seul.
- [ ] **COMP-3.** Au moins un tiers des items exigent de relier deux informations distinctes du
  texte, et pour ceux-la la bonne reponse n'est pas un fragment recopie du support.
- [ ] **COMP-4.** Le support, la question et toutes les propositions sont entierement visibles a
  360 x 640 sans defiler, mesure par `outils/mesurer-rendu.sh` sur la totalite des items du paquet.
- [ ] **COMP-5.** Le texte-support recoit un style distinct de celui de l'enonce, plus petit
  (budget mesure par `nadia-prof` : 5 lignes avec le style d'enonce actuel, insuffisant pour un
  texte a inference ; 8 lignes avec un style a la taille de la consigne, suffisant). Forme exacte
  (taille, police, couleur) proposee par `ines-design`, mecanisme par `theo-archi`, jugee par
  `nadia-prof` sur sa lisibilite au niveau vise avant tout item ecrit sur sa base.
  *Rendu le 2026-08-19 par `nadia-prof` : forme **ACCEPTEE SOUS CONDITIONS** (variante nue
  d'`ines-design`). Le chiffre "8 lignes / 45 mots" cite ci-dessus est **retire** - il etait mesure
  sans bloc de question et sans propositions en mots. Il est remplace par COMP-6 et COMP-7. La ligne
  n'est pas effacee, pour que l'ecart reste lisible : voir "Jugement de lisibilite du texte-support
  (COMP-5)" en fin de fiche.*
- [ ] Le paquet compte au moins 20 items.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf autre que le
  support de style de COMP-5.

**Limite du proxy, a porter sur la matrice le jour ou cette ligne bouge** (`nadia-prof`, meme
arbitrage, section 1.5) : un item est un texte de 45 mots maximum : "textes courts, un texte par
question, sans lecture longue". Un enfant ne lira jamais, dans ce jeu, un texte assez long pour que
la comprehension d'un texte long soit en jeu.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : <a rendre>
- **Date** : <a rendre>
- **Audit joue** : <a rendre>

### Ce qui a ete verifie

- **Ancrage au programme** :
- **Lisibilite au niveau vise** :
- **Charge cognitive** :
- **Progression** :
- **Traitement de l'erreur** :
- **Exactitude du contenu** :
- **Biais et ton** :

### Motif

### Reserves

## Arbitrage prealable bloquant, avant tout premier commit de code

Pose par `camille-po` a l'ouverture de cette fiche, sur le modele impose depuis l'incident du "Bon
verbe" en s07 (`squad/README.md`, section 10) : toute story qui ouvre un contenu neuf attend
l'arbitrage prealable de `nadia-prof` avant que la premiere ligne de code ne soit ecrite.

**Question posee** : un texte-support affiche a l'ecran en meme temps que la question et les
propositions constitue-t-il un exercice valide de la competence "comprendre un texte narratif ou
documentaire", ou seulement un exercice de recherche d'information localisee qu'un enfant peut
resoudre par appariement de mots entre la question et le texte, sans avoir vraiment compris
l'ensemble ? C'est exactement le risque que `nadia-prof` a nomme pour "Le bon son" en s02.

**Reponse rendue le 2026-08-19**
(`ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md`, section 1) : **le principe est ACCEPTE**
- "une question a choix multiple sur un texte affiche peut verifier une comprehension reelle... ce
n'est pas la forme qui decide, c'est la construction des items" - sous les cinq conditions COMP-1 a
COMP-5 reprises dans la checklist ci-dessus. **La matiere premiere identifiee a l'ouverture est
REFUSEE** : les huit items de `contenu-repris/quiz/cm1-comprehension.yml` sont exactement le defaut
qu'elle refuse ("l'appariement visuel de s02, transpose du grapheme au mot"), et ne sont pas
retouchables - le paquet a ecrire est integralement neuf. `nadia-prof` a egalement mesure que mon
critere d'ouverture "sans gabarit ni feuille de style neuve" ne tenait pas avec la competence visee
(budget de 5 lignes au style d'enonce actuel, insuffisant pour un texte a inference) ; je le leve en
faveur de COMP-5 plutot que de laisser sa reponse au principe basculer a NON, ce qu'elle a ecrit se
produirait si ce critere restait tel quel.

---

## Jugement de lisibilite du texte-support (COMP-5) - nadia-prof, 2026-08-19

**Ce que cette section est, et ce qu'elle n'est pas.** Elle repond a la proposition de forme
d'`ines-design` (`squad/sprints/s17-francais-cm1-et-dette/proposition-ines-texte-support-comp5.md`,
branche `s17-ines-comp5`, non fusionnee au moment ou j'ecris), sur la seule condition `COMP-5`.
**Ce n'est pas un Done pedagogique** : le bloc de validation ci-dessus reste a rendre, et il le
restera tant qu'aucun item n'existera. Aucun autre agent ne remplit ce bloc.

- **Branche** : `s17-nadia-comp5`, worktree dedie, lignee verifiee par `outils/verifier-lignee.sh`.
- **Verdict** : **ACCEPTE SOUS CONDITIONS.**

### 1. Le verdict sur la forme, en une phrase

**La variante nue d'`ines-design` (classe `.texte-support`, 1,25rem, graisse normale, couleur et
interligne herites, entre la consigne et l'enonce, sans cadre) est lisible par un CM1 et satisfait
`COMP-5` : je l'accepte** - mais le budget vertical que `COMP-5` citait entre parentheses (8 lignes,
45 mots) **ne survit pas a la mise en page reelle de `S17-2`**, et c'est moi qui l'avais mesure trop
etroitement, pas elle qui l'a mal lu.

Ce que j'ai verifie sur la forme elle-meme, en jouant le rendu et non en lisant la proposition :

- **Taille.** 1,25rem rendu 20 px, interligne herite 30 px, mesures sur le rendu servi. C'est la
  taille de `.consigne`, deja en usage partout dans l'application. Un CM1 lit un texte narratif de
  trois phrases a cette taille sans effort ; la ligne rendue tient une quarantaine de caracteres a
  360 px, longueur de ligne confortable pour un lecteur de neuf ans.
- **Graisse normale.** Correcte, et pour le motif qu'elle donne : le gras convient a un texte court
  qu'on repere, pas a un texte qu'on lit en entier. Verifie dans `recreo.css` : `font-weight: bold`
  n'y apparait que quatre fois, dont `.enonce`, et aucune ne porte un texte multiligne.
- **Interligne non declare.** Verifie : `line-height` n'est declare **nulle part** dans
  `recreo.css`. La valeur qui s'applique est bien celle de Reboot, 1,5, et c'est bien celle sur
  laquelle ma mesure du jour est faite. Son choix de ne rien ecrire est le bon, et son motif exact.
- **Repli sur plusieurs lignes, jamais de troncature.** Verifie sur le rendu : un texte de 45 mots
  se replie sur sept lignes completes, aucun mot coupe, aucun texte masque.
- **Ordre de lecture** consigne, texte, question, reponses : c'est l'ordre qu'un enfant suit, et le
  rendu le confirme.

### 2. La verification des deux contrastes - je ne l'ai pas crue, je les ai recalcules

Formule WCAG 2.1, luminance relative sur canaux lineaires, `(L1 + 0,05) / (L2 + 0,05)`.

| Paire | Valeur d'`ines-design` | Ma valeur | Verdict |
|---|---|---|---|
| `#1c2733` sur `#f4f7fb` (variante nue) | 14,08:1 | **14,09:1** | plus de trois fois le plancher de 4,5:1 |
| `#1c2733` sur `#eaf1f7` (variante encadree) | 13,28:1 | **13,28:1** | reproduit exactement |

Mes luminances : `#1c2733` L = 0,019369 ; `#f4f7fb` L = 0,927197 ; `#eaf1f7` L = 0,871184. Rapports
exacts avant arrondi : 14,0869 et 13,2794.

**Un ecart d'un centieme sur la premiere paire, et il faut dire dans quel sens il va.** Ses canaux
lineaires intermediaires pour `#f4f7fb` (0,903620 / 0,929770 / 0,964860) sont legerement bas ; les
valeurs exactes sont 0,904661 / 0,930111 / 0,964686. L'ecart pousse le rapport juste sous la
frontiere d'arrondi et rend 14,08 au lieu de 14,09. **La valeur deja portee par le depot
(`FICHE-bandit-manchot-additions-cp.md`, ligne 2049, 14,09:1 - verifiee, elle y est bien) est la
bonne, et c'est son 14,08 qui est l'artefact d'arrondi**, non l'inverse comme sa section 3 le
suppose. **Consequence sur le verdict : aucune.** Les deux paires depassent le plancher d'un facteur
trois ; le contraste ne separe pas ses deux variantes, exactement comme elle l'ecrit.

### 3. Le point qu'elle m'a route : mon budget de 8 lignes ne tient pas, et voici de combien

Elle a eu raison de refuser de le trancher elle-meme et de nommer ce qu'elle ne pouvait pas mesurer.
**J'ai fait la mesure.**

**Methode**, rejouable : jar reconstruit sur le JDK de la toolchain 21, application servie sur un
port a moi et arretee par son PID. Une page de mesure reproduit le balisage exact de
`fragments/manche.html` et **charge les deux feuilles de style depuis l'application en cours
d'execution**, jamais une copie. Une sonde lit `getBoundingClientRect()` de chaque bloc et le bas de
la derniere proposition, compte les lignes par l'interligne calcule, et rend ses valeurs par le DOM.
Captures a `360 x 640` :

```
outils/mesurer-rendu.sh 360 <url> <png> 640
```

Releves : `squad/conformite/releves-s17/s17-comp5-*.png`.

**Premier resultat, et il porte sur mon propre releve du matin : mes chiffres se reproduisent a la
ligne pres.** Mon tableau du 2026-08-19 donnait, au style de la consigne, 7 lignes a `y = 583` et 9
lignes a `y = 643` ; je retrouve exactement `y = 583,19` et le meme pas de 30 px par ligne. **Le
point a 8 lignes, que j'avais extrapole et qu'`ines-design` a eu la rigueur de signaler comme
extrapole, est desormais mesure** : `y = 613,19`, marge de **26,81 px** avant le pli. Il etait juste.
Je ne corrige donc aucun de mes chiffres.

**Ce que je corrige, c'est leur portee - et c'est ma faute, pas la sienne.** Ce budget a ete mesure
sur une manche de "Calcul CP", c'est-a-dire sur **trois choses a la fois** que je n'avais pas
nommees : une consigne rendue sur **une** ligne, **aucun** bloc de question separe, et **trois
propositions d'un seul chiffre tenant sur une seule rangee**. La mise en page reelle de `S17-2`
change les trois. Surcouts mesures, chacun isole, a hauteur de texte-support egale :

| Ce que la mise en page reelle ajoute | Surcout mesure |
|---|---|
| consigne rendue sur 2 lignes au lieu d'1 ("Lis le texte, puis reponds a la question.") | **+30 px** |
| question separee `.enonce` (2rem, gras) tenant sur 1 ligne | **+52 px** |
| la meme question tenant sur 2 lignes | **+100 px** |
| 4 propositions d'**un mot court** au lieu de 3 chiffres (elles passent a 2 rangees) | **+84 px** |
| 3 propositions d'**un mot court** (2 rangees) | **+90 px** |
| 3 propositions de **deux ou trois mots** ("un renard", "le voisin") : 3 rangees | **+180 px** |

**La marge disponible etait de 26,81 px.** Le plus petit de ces surcouts la depasse a lui seul.

**Plafond reel du texte-support, mesure, selon l'assemblage** (pli a 640 px, toutes propositions
comprises) :

| Assemblage | Plafond du texte-support |
|---|---|
| consigne 1 ligne, question 1 ligne, 3 ou 4 propositions d'un mot court | **4 lignes, environ 20 mots** (marge mesuree : 4,81 px) |
| consigne 1 ligne, question 2 lignes, 3 propositions d'un mot court | **2 lignes, environ 12 mots** (marge mesuree : 16,81 px) |
| consigne 2 lignes, question 2 lignes, 3 propositions de deux mots | **aucun** : l'assemblage depasse le pli meme avec zero ligne de texte |

Le releve `s17-comp5-compose-45mots-4props.png` montre ce dernier cas tel qu'un enfant le verrait :
texte de 45 mots, question reelle, quatre propositions de deux mots - **l'enfant ne voit pas une
seule proposition sans defiler**, le cabinet commence tout juste au bas de l'ecran.

**Reponse a la question routee, sans detour : mon budget de 8 lignes / 45 mots ne tient plus. Le
budget compose est de 4 lignes, environ 20 mots, dans l'assemblage le plus favorable qui soit encore
realiste.** Et ce chiffre, je l'ai deja qualifie ce matin : "on peut y mettre un reperage litteral -
c'est-a-dire exactement le defaut que je viens de refuser - mais pas un texte narratif portant une
inference" (arbitrage, section 1.4, a propos des 18 a 20 mots du style d'enonce). **Je ne me dedis
pas de cette phrase parce qu'elle devient genante.** Le gain que `COMP-5` devait acheter est annule
par la mise en page reelle, et `COMP-3` (un tiers d'items reliant deux informations) n'est pas
atteignable a 20 mots.

**Ce n'est pas un defaut de la proposition d'`ines-design`.** Sa variante nue est celle qui coute le
moins : sa variante encadree ajouterait 20 px, soit quatre fois la marge qui reste dans le meilleur
assemblage. Elle a recommande la bonne des deux, et pour le bon motif.

### 4. Mes conditions

- **`COMP-5` est satisfaite** par la variante nue. Je leve ma reserve de forme : la barre que
  j'avais ecrite ("un style distinct de celui de l'enonce, plus petit") est atteinte par deux
  signaux mesurables, la taille et la graisse. Je ne demande pas un troisieme signal.
- **COMP-6 (bloquante, nouvelle, 2026-08-19).** **La consigne de ce jeu est rendue sur une seule
  ligne a 360 px.** Deux motifs, et le second est celui qui compte : elle rend 30 px au budget, et
  surtout `.texte-support` et `.consigne` sont **rendues a l'identique** (meme taille, meme graisse,
  meme couleur, meme marge - verifie dans `recreo.css`, `.consigne` ne declare aucune graisse). Sur
  le releve a consigne longue, un CM1 lit deux paragraphes typographiquement indiscernables et ne
  sait pas ou l'histoire commence ; sur le releve a consigne d'une ligne, la distinction se fait
  seule. **Verification** : la sonde de la section 3, `.consigne` a une hauteur d'exactement un
  interligne.
- **COMP-7 (bloquante, nouvelle, 2026-08-19).** **Le chiffre "8 lignes / 45 mots" de `COMP-5` est
  retire et remplace par : aucun nombre de lignes n'est un budget en soi.** Ce qui est mesure, item
  par item, est le bas de la derniere proposition a `y <= 640`, texte-support **et** question **et**
  toutes les propositions comprises. `COMP-4` disait deja cela ; `COMP-7` dit qu'aucun autre chiffre
  ne peut lui etre substitue, y compris un chiffre de moi.
- **Reserve nouvelle et datee, non bloquante (2026-08-19), routee a `ines-design`** : la distinction
  visuelle entre `.consigne` et `.texte-support` repose aujourd'hui entierement sur `COMP-6`. Si un
  jour une consigne de ce jeu passe a deux lignes, il faudra un signal propre. **Contrainte a lui
  transmettre avec la question : le budget vertical restant est de 4,81 px dans le meilleur
  assemblage mesure**, donc une reponse qui coute des pixels verticaux ne passe pas. Je ne dessine
  pas ce signal a sa place.

### 5. Ce que `marc-dev` peut ecrire des maintenant sans que mon accord tombe

Il attend mon verdict ; le voici, avec sa part utilisable et sa part bloquee.

**Il peut, sans aucun risque de perte :**

1. **La consigne du paquet**, tenue sur une ligne rendue a 360 px (`COMP-6`). C'est sa decision,
   elle est gratuite et elle est bonne dans tous les assemblages.
2. **La forme des propositions : un mot court chacune.** Mesure : c'est l'ecart entre `+84 px` et
   `+180 px`, soit pres de cent pixels gagnes sur un budget qui n'en a plus.
3. **Le squelette du paquet** : identifiant, niveau, matiere, competence, consigne - tout ce qui
   n'est pas le texte d'un item.
4. **Un seul item, et le pire de tous.** C'est ma reponse a la deuxieme question d'`ines-design` :
   oui, on mesure avant les dix-neuf autres, et je vais plus loin qu'elle - **le premier item ecrit
   n'est pas un item typique, c'est le plus long que `marc-dev` estime avoir besoin d'ecrire**
   (support le plus long, question la plus longue, propositions les plus longues). Un premier item
   moyen qui passe n'apprend rien sur les dix-neuf suivants ; le pire cas, lui, ferme la question.
   Je le mesure des qu'il est commite.

**Il ne peut pas, et ce n'est pas une prudence de ma part mais une consequence de la mesure :**

- **ecrire les vingt items.** Le plafond du jour est de 20 mots de support, et j'ai ecrit ce matin
  que 18 a 20 mots ne portent pas une inference. Vingt items ecrits contre ce plafond seraient vingt
  items de reperage litteral, c'est-a-dire le paquet que je viens de refuser. **Les ecrire
  maintenant, c'est les ecrire pour rien.**

**Ce qui debloque la story n'est pas de mon ressort et je ne le tranche pas** : trouver les pixels
manquants est un arbitrage de mise en page (`ines-design`) et de mecanisme (`theo-archi`), a
prioriser par `camille-po`. Je dis seulement ou ils sont : les trois postes du tableau de la section
3. Ma condition sur le resultat, quelle qu'en soit la voie, est celle-la et elle ne bouge pas : **un
texte qui porte une inference et toutes ses propositions, entierement visibles a 360 x 640 sans
defiler.**

### 6. Reponses aux trois questions d'`ines-design`

1. **Oui**, la variante nue satisfait "un style distinct, plus petit" au sens de `COMP-5`. Je
   n'attends pas de signal visuel supplementaire vis-a-vis de l'enonce. Le signal qui manque est
   vis-a-vis de la **consigne**, et `COMP-6` le rend inutile tant que la consigne tient sur une
   ligne.
2. **Oui**, et le premier item mesure doit etre le pire cas, pas un item typique (section 5).
3. **Le plafond que je retiens n'est pas un nombre de lignes** : c'est celui du tableau de la
   section 3, un plafond par assemblage. Le seul chiffre qui gouverne est `y <= 640` pour le bas de
   la derniere proposition (`COMP-7`).

### 7. Ce dont je reste seule garde, et que personne ne doit croire couvert

**Aucun test de la CI ne mesure la hauteur composee d'une manche.** `mvn verify` peut etre vert avec
un ecran dont l'enfant ne voit aucune proposition : le releve `s17-comp5-compose-45mots-4props.png`
en est l'image. `COMP-4`, `COMP-6` et `COMP-7` n'ont **aucun garde-fou executable** ; ils n'existent
que par ma mesure, item par item, sur le rendu reel. Tant qu'il en est ainsi, c'est moi le garde-fou,
et je le suis en regardant l'ecran.
