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
  **Perime sur ce dernier point depuis le 2026-08-28 : voir mise a jour du 2026-08-29 ci-dessous.**
  Aujourd'hui, quatre conditions restent bloquantes (`COMP-1`, `COMP-2`, `COMP-3`, `COMP-5`), le
  blocage de developpement est leve, et le developpement de `S17-2` est en cours (`marc-dev`).
- **Derniere mise a jour** : 2026-08-19 (arbitrage prealable de `nadia-prof`, `camille-po` en tire
  les consequences sur cette fiche).
  Complete le 2026-08-19 par le jugement de lisibilite du texte-support de `nadia-prof`
  (COMP-5 satisfaite, COMP-6 et COMP-7 nouvelles, budget de 8 lignes retire) - derniere section.
  **Mise a jour du 2026-08-29 (`camille-po`, s22) : fiche perimee corrigee contre l'arbitrage de
  l'utilisatrice du 2026-08-28 (`DEC-0053`) et la relecture de `nadia-prof`
  (`PORTE-PEDAGOGIQUE-apres-arbitrage-du-2026-08-28.md`, section 3.3) - voir le detail en fin de
  preambule et dans la checklist.**
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

  > **REPONDUE le 2026-08-28**, le meme jour, par `nadia-prof`
  > (`squad/conformite/PORTE-PEDAGOGIQUE-apres-arbitrage-du-2026-08-28.md`, section 3.3) puis par
  > l'utilisatrice sur la portee la plus large de la question (meme document, section 10 ;
  > `DEC-0053`, second amendement du 2026-08-28). Cette question ne reste ouverte pour personne.
  > Le detail de ce qui tombe et de ce qui tient est repris, condition par condition, dans la
  > "Mise a jour du 2026-08-29" ci-dessous et dans la checklist qui suit. Ce paragraphe est
  > conserve tel qu'ecrit le 2026-08-28 : c'est lui qui a servi a poser la question.

- **Mise a jour du 2026-08-29 (s22, `camille-po`) - la fiche etait perimee depuis le 2026-08-28,
  voici ou et pourquoi.** Cette fiche portait encore comme bloquantes des conditions que
  `nadia-prof` a levees par ecrit le 2026-08-28, et une formule que `theo-archi` a qualifiee de
  fausse. Source primaire :
  `squad/conformite/PORTE-PEDAGOGIQUE-apres-arbitrage-du-2026-08-28.md`, section 3.3, et
  `squad/sprints/s22-couverture-maximale-du-catalogue/avis-faisabilite.md`, section 3.3. Je ne
  reecris aucune condition d'origine ; je trace ce qui change, avec sa date et sa source, a cote
  d'elles.
  - **Tombent comme conditions bloquantes** : `COMP-4` (entierement derivee du pli), `COMP-7`
    (qui n'existait que pour interdire un chiffre de remplacement a `COMP-4`), et le **premier**
    motif de `COMP-6` (les 30 px gagnes par une consigne d'une ligne). Motif : arbitrage de
    l'utilisatrice du 2026-08-28, `DEC-0053`, arbitrage 1 - le pli sort de la porte pedagogique sur
    tout le catalogue, paquets neufs compris.
  - **Tiennent, inchangees et bloquantes** : `COMP-1`, `COMP-2`, `COMP-3`. Ce sont le motif de s02
    applique a cette competence, et ce motif ne depend d'aucun ecran ; `nadia-prof` l'ecrit
    elle-meme dans sa relecture du 2026-08-28. `COMP-5` **reste satisfaite et close** depuis le
    2026-08-19 : elle a ete acceptee sur la lisibilite (taille, graisse, contrastes), jamais sur le
    cout vertical, donc elle ne tombe pas avec le pli. Le **second** motif de `COMP-6` (distinguer
    `.texte-support` de `.consigne`, aujourd'hui rendues a l'identique) tient pour la meme raison :
    ce n'est pas une question de pixels.
  - **`COMP-6` est reformulee en propriete, son moyen d'origine (consigne d'une ligne) devenant une
    facon suffisante de la tenir et non plus la seule.** Formulation qui fait foi a partir
    d'aujourd'hui, mot pour mot celle de `nadia-prof` : *"un enfant de CM1 distingue, sans qu'on le
    lui dise, la consigne du texte a lire."* Le moyen ne lui appartient pas : `ines-design` pour la
    forme, `theo-archi` pour le mecanisme ; elle juge le resultat en jouant.
  - **Le blocage de `nadia-prof` sur cette story est leve.** Elle avait ecrit, le 2026-08-19, que
    `marc-dev` ne pouvait pas ecrire les vingt items parce que le plafond mesure de 4 lignes / 20
    mots ne portait pas d'inference. Ce blocage etait entierement bati sur le budget vertical ; il
    n'a plus de fondement. **`S17-2` est developpable a s22**, et son avis de s17 ne doit plus etre
    lu comme un veto.
  - **Ce qui reste du texte de deux phrases, une fois le rendu sorti de la porte pedagogique : un
    plancher, pas un plafond - c'est l'inverse de ce que produisait le regime precedent.** Le texte
    doit rester assez long pour porter deux informations distinctes a relier, sans quoi `COMP-3`
    est inatteignable et le paquet retombe dans le reperage litteral refuse en s02 ; deux phrases en
    sont le minimum vraisemblable, sans nombre fixe - un nombre serait exactement l'exemple que la
    regle de methode de `nadia-prof` (section 4 du meme document) lui interdit desormais d'ecrire a
    la place d'une propriete. Le plafond, lui, n'est plus vertical : c'est la charge de lecture d'un
    CM1, jugee en jouant et non mesuree.
  - **Une propriete nommee par `nadia-prof`, qu'elle ne fait pas d'un motif de refus a s22** : si
    l'enfant ne peut pas revenir au texte pendant qu'il repond, l'inference demandee devient un
    exercice de memoire. Sa barre n'est pas "visible sans defiler" mais **"relisible"** : un texte
    qu'on rejoint en faisant defiler la page la satisfait, un texte qu'on ne peut pas rejoindre du
    tout ne la satisfait pas. Elle ne refusera pas dessus a s22 ; si le Done de `S17-2` est
    prononce, elle y nommera cette propriete comme non couverte plutot que de laisser une ligne
    `couvert-valide` dire une chose fausse. **Ce n'est pas un critere de plus pour `yanis-qa`** :
    c'est une information sur ce que le Done, s'il vient, devra dire.
  - **`MNC-1` s'appliquera automatiquement au premier Done pedagogique prononce sur ce paquet**,
    comme sur tout Done a partir du 2026-08-28 (`DEC-0053` ; gabarit dans `squad/TEMPLATES.md`,
    section 7). Je n'ecris rien dans le bloc de validation ci-dessous : cette clause est de la
    seule main de `nadia-prof`, au moment ou elle la remplit.
  - **La formule "sans code Java neuf autre que le support de style de `COMP-5`" est corrigee : elle
    est fausse.** `theo-archi` l'a qualifiee "fausse a la lettre"
    (`squad/sprints/s22-couverture-maximale-du-catalogue/avis-faisabilite.md`, section 3.3) : la
    story coute cinq fichiers dans quatre modules (`Question` dans `domain/content`,
    `BanditManchotSession` dans `domain/mechanics`, `QuestionContentSourceFactory` dans
    `adapter-content`, `PartieController` et `fragments/manche.html` plus `recreo.css` dans
    `adapter-web`), en suivant le patron deja trace par `partDesignee` (`ADR-0062`, s20). Voir la
    correction du dernier critere de la checklist ci-dessous.
  - **Statut de construction** : le developpement de `S17-2` est en cours (`marc-dev`), et n'est
    plus a l'arret dans l'attente de cette fiche.

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
  *Tombe comme condition bloquante le 2026-08-28 : arbitrage de l'utilisatrice, le pli sort de la
  porte pedagogique sur tout le catalogue (`DEC-0053`, arbitrage 1). `nadia-prof` le confirme dans
  `PORTE-PEDAGOGIQUE-apres-arbitrage-du-2026-08-28.md`, section 3.3 - c'etait, mot pour mot, "la
  seule condition de ce document que je devrai mesurer moi-meme". Elle n'est plus opposable ; elle
  n'est pas effacee, pour que l'ecart reste lisible (mise a jour du 2026-08-29, `camille-po`).*
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
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`).
  *Correction du 2026-08-29 (`camille-po`) : la formule d'origine ("sans code Java neuf autre que
  le support de style de COMP-5") est retiree parce qu'elle est fausse, pas parce qu'elle a change
  de sens. `theo-archi` l'a qualifiee "fausse a la lettre"
  (`squad/sprints/s22-couverture-maximale-du-catalogue/avis-faisabilite.md`, section 3.3) : le
  champ `texteSupport` n'existe dans aucun fichier Java, et la chaine coute cinq fichiers dans
  quatre modules (`Question` dans `domain/content`, `BanditManchotSession` dans
  `domain/mechanics`, `QuestionContentSourceFactory` dans `adapter-content`, `PartieController` et
  `fragments/manche.html` plus `recreo.css` dans `adapter-web`), en suivant le patron deja trace
  par `partDesignee` (`ADR-0062`, s20). `S17-2` n'est pas une story de contenu seul.*

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
  *Mise a jour du 2026-08-29 (`camille-po`), reprenant `PORTE-PEDAGOGIQUE-apres-arbitrage-du-
  2026-08-28.md`, section 3.3 : le premier motif ci-dessus (les 30 px) tombe avec le pli, arbitrage
  de l'utilisatrice du 2026-08-28 (`DEC-0053`). Le second motif tient et devient la condition
  elle-meme, reformulee en propriete sans son moyen : "un enfant de CM1 distingue, sans qu'on le
  lui dise, la consigne du texte a lire." Une consigne d'une ligne reste une facon suffisante de la
  tenir ; ce n'est plus la seule.*
- **COMP-7 (bloquante, nouvelle, 2026-08-19).** **Le chiffre "8 lignes / 45 mots" de `COMP-5` est
  retire et remplace par : aucun nombre de lignes n'est un budget en soi.** Ce qui est mesure, item
  par item, est le bas de la derniere proposition a `y <= 640`, texte-support **et** question **et**
  toutes les propositions comprises. `COMP-4` disait deja cela ; `COMP-7` dit qu'aucun autre chiffre
  ne peut lui etre substitue, y compris un chiffre de moi.
  *Tombe comme condition bloquante le 2026-08-28 (meme source que `COMP-4` ci-dessus) : elle
  n'existait que pour interdire qu'un autre chiffre soit substitue a `COMP-4`, et `COMP-4` n'a plus
  d'objet (mise a jour du 2026-08-29, `camille-po`).*
- **Reserve nouvelle et datee, non bloquante (2026-08-19), routee a `ines-design`** : la distinction
  visuelle entre `.consigne` et `.texte-support` repose aujourd'hui entierement sur `COMP-6`. Si un
  jour une consigne de ce jeu passe a deux lignes, il faudra un signal propre. **Contrainte a lui
  transmettre avec la question : le budget vertical restant est de 4,81 px dans le meilleur
  assemblage mesure**, donc une reponse qui coute des pixels verticaux ne passe pas. Je ne dessine
  pas ce signal a sa place.
  *Mise a jour du 2026-08-29 (`camille-po`) : la contrainte de pixels ci-dessus n'a plus d'objet, le
  budget vertical n'etant plus une condition (voir mise a jour de `COMP-6` ci-dessus, meme source).
  Ce qui reste de cette reserve - un signal propre si la consigne passe un jour a deux lignes - n'est
  plus borne par un budget de pixels ; je ne l'ai pas reposee a `ines-design`, elle n'a pas ete
  activee a s22.*

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

> **Mise a jour du 2026-08-29 (`camille-po`).** C'est cette derniere phrase que la question posee le
> 2026-08-28 citait comme non negociable. Elle a bouge : arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`, arbitrage 1), confirme par `nadia-prof`
> (`PORTE-PEDAGOGIQUE-apres-arbitrage-du-2026-08-28.md`, section 3.3). La visibilite sans defilement
> n'est plus la condition ; ce qui la remplace est trace au debut de cette fiche et dans la checklist
> ci-dessus. Ce paragraphe est conserve tel qu'ecrit le 2026-08-19, pour que l'ecart reste lisible.

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

> **Mise a jour du 2026-08-29 (`camille-po`).** `COMP-4` et `COMP-7` sont tombees comme conditions
> bloquantes le 2026-08-28 (`DEC-0053`, arbitrage 1) ; il n'y a donc plus de garde manquant a leur
> sujet, puisqu'il n'y a plus de condition a garder. Ce que `nadia-prof` reste seule a garder,
> desormais, est la propriete **"relisible"** qu'elle nomme dans la meme relecture
> (`PORTE-PEDAGOGIQUE-apres-arbitrage-du-2026-08-28.md`, section 3.3) : elle ne refusera pas dessus,
> elle la nommera comme non couverte dans le Done s'il vient. `COMP-6`, elle, garde un garde-fou
> humain sur son second motif (distinguer visuellement la consigne du texte-support), inchange.

---

## Done pedagogique - 2026-08-29 (s22) - REFUSE

**Verdict : REFUSE.** Motif : **`COMP-2`, bloquante, n'est pas tenue.** Un item sur les vingt se
resout sans lire le texte, et il s'y resout par le chemin exact que `COMP-2` nomme et interdit - un
fait de culture generale. Deux autres items donnent un avantage plus faible mais reel au meme
enfant. **Ce sont, tous les trois, les seuls items que `marc-dev` n'a pas marques `COMP-3`.**

**Ce que je dis d'abord, parce que ce refus ne doit pas etre lu comme un jugement sur le lot.** Sur
dix-sept des vingt items, ce paquet fait ce que j'avais refuse a s02 et jamais obtenu depuis sur
cette competence : il oblige a lire. Les textes sont bons, les inferences sont vraies, les
distracteurs sont des situations reelles, et `COMP-1` est tenue sur la branche la plus dure. Le
defaut est localise, il porte sur trois items sur vingt, et il se corrige en reecrivant des
questions - pas des textes.

### Comment j'ai audite, et ce que je declare

- **J'ai joue.** Application lancee depuis le fat jar construit sur l'arbre de la branche. Parties
  entieres, en me trompant volontairement pour voir l'indice, jusqu'a avoir rencontre a l'ecran les
  vingt items - dont les quatre sur lesquels porte ce verdict, vus rendus, pas seulement lus dans le
  fichier. **Audit non degrade.**
- **J'ai applique la procedure de verification que `COMP-1` et `COMP-2` prescrivent** : masquer la
  question et essayer de repondre, item par item ; puis masquer le texte et essayer de repondre,
  item par item. Vingt items dans chaque sens.
- **Perimetre de tout chiffre ci-dessous** : le seul paquet `cm1-comprehension`, vingt items, arbre
  de `s22-nadia-3` issu de `round-two` a `62037c7`.

### Le motif du refus - `COMP-2`

**L'item du pecheur, texte masque, se resout en une seconde :**

> Question : *"Que vend le vieux pecheur au marche ?"*
> Propositions : *des produits de la mer* / *des fruits et legumes* / *des vetements chauds*

**La question nomme le metier ; la reponse est ce que ce metier vend.** Un CM1 qui n'a pas lu une
ligne du texte repond juste, et il repond juste **parce qu'il sait ce qu'est un pecheur**, pas parce
qu'il a compris quoi que ce soit. `COMP-2` interdit cela nommement : *"les questions dont la reponse
est un fait de culture generale (le hibou, le vent)"*. C'est le meme defaut que celui que j'ai
refuse a s02, sur un paquet de comprehension, et c'est la raison pour laquelle `COMP-2` a ete ecrite
bloquante.

**Le texte de cet item, lui, est bon** - *"Des caisses de glace pilee entourent les poissons
argentes fraichement peches"* - et il porterait un item honnete sous une **autre question**. Ce
n'est pas le support qu'il faut jeter, c'est la question qui donne sa propre reponse.

**Deux items donnent un avantage plus faible, et je les nomme parce qu'ils se corrigent dans le meme
geste.**

- **Le cahier rouge.** Propositions : *a ecrire les lecons du soir* / *a dessiner en recreation* /
  *a ranger les crayons*. **Un cahier ne range pas des crayons** : un CM1 ecarte cette proposition
  sans avoir lu, et se retrouve a une chance sur deux au lieu d'une sur trois. C'est le second
  interdit de `COMP-2` - *"les distracteurs absurdes qui s'eliminent seuls"*.
- **Les bougies.** Question : *"Pourquoi les voisins utilisent-ils des bougies ?"* Propositions :
  *le courant est coupe* / *ils fetent un anniversaire* / *ils regardent les etoiles*. On ne
  s'eclaire pas aux bougies pour regarder les etoiles, et la panne est la raison canonique. Meme
  mecanisme, plus faible.

**Le fait qui rend ces trois-la reperables sans moi, et que je verse au dossier** : ce sont
**exactement** les trois items que `marc-dev` n'a pas marques `COMP-3` dans le fichier. Les
dix-sept items qui exigent de relier deux informations sont aussi les dix-sept qui resistent au
texte masque. **La ou l'inference est reelle, `COMP-2` tient toute seule ; la ou elle ne l'est pas,
`COMP-2` tombe.** Ce n'est pas une coincidence, c'est la meme propriete vue de deux cotes, et cela
donne a `camille-po` un critere de reecriture sans qu'elle ait a me redemander quoi que ce soit :
**les trois items a reprendre sont ceux qui ne portent pas la marque.**

### Un second defaut, sur l'exactitude, qui n'est pas le motif du refus mais qui doit etre corrige avec lui

**L'item du lion fait valider a l'enfant une lecture que le texte contredit.**

> Texte : *"Le lion se repose la majeure partie de la journee a l'ombre d'un acacia. C'est
> **surtout** au lever et au coucher du soleil qu'il part chasser avec le reste de son groupe."*
> Bonne reponse : *"**seulement** matin et soir"*

Le texte dit *surtout*, la bonne reponse dit *seulement*. **Dans un exercice de comprehension, la
precision de la lecture est la competence elle-meme** : un enfant qui remarque la difference - et un
bon lecteur de CM1 la remarque - voit son observation juste sanctionnee comme fausse, et un enfant
qui ne la remarque pas apprend que *surtout* et *seulement* disent la meme chose. C'est de la famille
de l'indice qui enseigne une regle fausse (`GRA-15`, s21), en plus grave, parce que c'est la bonne
reponse et non l'indice.

**L'item reste decidable** - les deux autres propositions sont contredites par le texte - donc ce
n'est pas le motif du refus, et je ne le compte pas deux fois. Il se corrige en un mot : *surtout* a
l'un des deux endroits, ou *souvent* dans la proposition.

### Les conditions, une par une, contre le contenu reel

| Condition | Etat | Ce que j'ai verifie moi-meme |
|---|---|---|
| `COMP-1` (bloquante) | **tenue, sur sa branche la plus dure** | **Zero recouvrement sur 20 items sur 20** : aucune proposition, bonne reponse ou distracteur, ne reprend un mot de son texte-support (mots de quatre lettres et plus, hors mots-outils). La clause *"toutes, ou aucune, jamais une seule"* est donc tenue par le cote "aucune" partout. Recompte par moi sur le fichier, independamment du test permanent que `marc-dev` a commite |
| `COMP-2` (bloquante) | **NON TENUE** | Motif du refus. **1 item** franchement (le pecheur), **2** plus faiblement (le cahier rouge, les bougies) - soit **3 items sur 20** ou l'enfant fait mieux que le hasard sans lire |
| `COMP-3` (bloquante) | **tenue** | 17 items marques, plancher d'un tiers a 7. Verifie sur le critere que j'ai ecrit : la bonne reponse ne se lit dans aucune phrase du texte, sur les 17. **Mais voir ci-dessous : mon critere ne dit pas ce que je croyais qu'il disait** |
| `COMP-4`, `COMP-7` | **sans objet** | Tombees le 2026-08-28 (`DEC-0053`). Je ne les rouvre par aucun autre nom |
| `COMP-5` (bloquante) | **satisfaite et close** | Jugee le 2026-08-19 sur la lisibilite. Je ne rejuge pas un rendu que j'ai accepte |
| `COMP-6` reformulee | **tenue** | Juge en jouant, comme je m'y etais engagee. La consigne est une ligne imperative courte, le support un paragraphe narratif, la question en grand et en gras : **je distingue les trois sans qu'on me le dise**, et la consigne d'une ligne est le moyen que j'avais moi-meme declare suffisant. Voir la reserve 3 |

**Exactitude du contenu, hors l'item du lion** : les vingt textes sont justes, correctement
orthographies et **integralement accentues** ; les vingt questions sont grammaticalement correctes ;
les distracteurs sont des situations reelles, jamais des remplissages. Aucun stereotype de genre,
d'origine, de milieu ni de configuration familiale : la maitresse enseigne, Monsieur Albert jardine,
le metier deduit des cahiers corriges est *enseignant* sans genre, et aucune situation ne suppose un
bien que tous les enfants n'ont pas.

**Un point de construction que je releve comme bon, parce qu'il est rare** : l'item du metier pose
*"une pile de cahiers corriges"* sur *"la table de la cuisine"* et offre *cuisinier* en distracteur.
L'enfant qui apparie des mots au lieu de comprendre tombe dans le piege. C'est exactement ce que
`COMP-1` cherche a produire, et c'est bien fait.

### Ce que le contenu reel a mis en defaut dans mes propres conditions

**`COMP-3` ne mesure pas ce que je croyais.** Je l'avais ecrite ainsi : *"au moins un tiers des items
exigent de relier deux informations distinctes du texte (...) si la reponse se lit dans une seule
phrase du texte, l'item ne compte pas dans le tiers."* Le critere que j'ai rendu decidable - *la
reponse se lit dans une seule phrase* - **ne separe pas l'inference a deux indices de l'inference a
un seul**, parce que dans un item d'inference la reponse ne se lit litteralement dans **aucune**
phrase. Consequence : sous mon critere ecrit, les 17 items comptent tous ; sous l'intention que le
critere servait, **six a sept** seulement exigent reellement deux informations - dans plusieurs
items marques, une seule suffit (*parapluie et bottes* donnent la pluie sans les flaques ; *arrose
ses tomates* donne le jardinage sans le chapeau de paille).

**Je ne durcis rien et je ne bouge pas ma barre.** Le plancher etait un tiers, il est atteint sous
le critere que j'ai ecrit, **et je le declare atteint**. Le paquet ne doit rien de plus a `COMP-3`.
Ce constat est **une reserve nouvelle et datee**, adressee a ma propre facon de specifier, exactement
comme la regle que j'ai adoptee le 2026-08-28 m'y oblige : *"une condition est ecrite comme une
propriete decidable item par item par un tiers"* - `COMP-3` l'est, mais elle decide autre chose que
ce qu'elle annonce. C'est le troisieme cas de cette famille en deux jours, apres `SEG-1` et `D-9`, et
il confirme que le defaut est chez moi et pas chez ceux qui appliquent.

**`COMP-2`, elle, a parfaitement mordu.** Elle a attrape trois items qu'aucune autre condition,
aucun garde-fou et aucune relecture n'avaient vus, et elle les a attrapes par la procedure exacte
que j'avais ecrite avec elle. C'est la premiere fois qu'elle rencontre un contenu reel, et elle
tient.

### Reserves

- **Reserve 1, BLOQUANTE, 2026-08-29 - `COMP-2` sur trois items.** *Que vend le vieux pecheur au
  marche ?* est a reecrire integralement (la question porte sa reponse) ; *A quoi va servir le cahier
  rouge ?* et *Pourquoi les voisins utilisent-ils des bougies ?* sont a reecrire sur leurs
  propositions, dont l'une s'elimine sans le texte. **Critere de verification par un tiers, sans me
  le redemander** : masquer le texte-support, presenter la question et les trois propositions a
  quelqu'un qui n'a pas lu, et verifier qu'il ne peut pas ecarter une proposition.
- **Reserve 2, BLOQUANTE, 2026-08-29 - exactitude de l'item du lion.** Le texte dit *surtout*, la
  bonne reponse dit *seulement*. A aligner dans un sens ou dans l'autre.
- **Reserve 3, non bloquante, 2026-08-29 - un commentaire du code m'attribue un jugement que je
  n'avais pas rendu.** La regle `.texte-support` de la feuille de style porte *"nadia-prof juge ce
  moyen suffisant tant que la consigne du jeu tient sur une seule ligne rendue"*. **Je rends ce
  jugement aujourd'hui, apres avoir joue, et il est positif** - donc la phrase se trouve vraie. Mais
  elle a ete ecrite avant que je le rende. Un commentaire qui anticipe la porte pedagogique la
  transforme en formalite ; qu'il ait eu raison cette fois ne change pas ce qu'il aurait coute s'il
  avait eu tort. A reformuler au passe et avec la date reelle. **Cette reserve ne demande aucun
  travail sur le contenu et ne retient aucun Done.**
- **`REP-8-CONSTAT`, 2026-08-29** - vu en jouant, sur ce paquet : sur les items ou le texte-support
  fait deux phrases pleines, l'indice qui suit un echec arrive bas dans la page. **Constat, non
  bloquant, sans mesure** : je ne mesure plus, l'arbitrage du 2026-08-28 l'a retire. Aucun effet sur
  le verdict.

### Ce que je reste seule a garder sur ce paquet, et que personne ne doit croire couvert

1. **Qu'aucun chemin ne contourne la lecture** - `COMP-1`, `COMP-2`, `COMP-3`. C'est ce refus meme
   qui le prouve : la porte technique etait verte et n'a vu aucun des trois items.
2. **L'exactitude d'un texte face a sa bonne reponse** - l'item du lion en est l'exemple : aucun
   automate ne peut voir que *seulement* trahit *surtout*.
3. **Que le vocabulaire et la charge de lecture soient d'un CM1.** Juge en jouant : ils le sont.
4. **La propriete "relisible"** - que l'enfant puisse revenir au texte pendant qu'il repond. **Je ne
   refuse pas dessus et je ne l'ai pas instruite**, conformement a ce que j'ai ecrit le 2026-08-28.
   Elle reste non couverte.
5. **Ce qu'un lecteur d'ecran annonce** : jamais couvert par aucun de mes Done.
6. **La distinction visuelle consigne / texte-support** (`COMP-6`) : tenue aujourd'hui **par la
   brievete de la consigne, pas par une regle de style** - `.consigne` et `.texte-support` portent
   la meme taille et la meme graisse. **Une consigne plus longue sur un futur jeu la ferait tomber
   sans que rien ne s'allume.** C'est une fragilite, pas un defaut, et elle est a ma seule garde.

### `MNC-1`

**Aucun Done pedagogique n'etant prononce ici, `MNC-1` n'a pas a y figurer** - elle porte sur tout
Done `VALIDE` ou `VALIDE SOUS RESERVE` a partir du 2026-08-28, et un `REFUSE` n'est pas un Done. Son
absence de ce bloc n'est pas un oubli. Le bloc qui prononcera le Done de ce paquet devra la porter.

### Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **Les reserves 1 et 2, bloquantes** - trois questions a reecrire, un mot a aligner | `camille-po` | En faire du backlog. Critere de reecriture donne dans la reserve 1, applicable sans moi |
| **`COMP-3` ne separe pas l'inference a deux indices de celle a un seul** | `camille-po` | **Ne pas l'appliquer retroactivement a ce paquet** : le plancher est atteint sous le critere ecrit et je le declare atteint. A reecrire par moi avant le prochain paquet de comprehension, comme une condition neuve et datee |
| **La reserve 3 - un commentaire de code qui anticipe mon jugement** | `theo-archi`, `ines-design` | Reformuler au passe, avec la date du jugement reel. Aucune urgence, mais le pli est mauvais |
| **Aucun garde-fou ne peut voir `COMP-2`** et personne ne doit croire le contraire | `yanis-qa`, `sofia-sm` | Le porter au rapport qualite : dix-neuf conditions gardees automatiquement, et la seule qui a fait tomber ce lot n'en fait pas partie |

---

### Done pedagogique - 2026-08-30 (s22) - VALIDE

**Reaudit apres le lot de `marc-dev`. Audit non degrade : fat jar reconstruit sur l'arbre de ma
branche, application lancee, paquet joue.**

#### Ce que j'ai joue

**Cinq parties, 20 items sur 20 servis.** Sur chaque item, les deux propositions fausses pressees
avant la bonne, puis une troisieme pression pour atteindre la revelation. Enonce, texte-support,
propositions et indice relus dans le HTML rendu et compares au fichier apres desechappement :
**zero ecart** sur les 20 enonces, 20 textes-supports, 20 bonnes reponses, 40 distracteurs et
20 indices. Le texte-support est bien rendu entre la consigne et l'enonce, dans un style distinct.

#### `COMP-2` - refaite item par item, par ma propre procedure, sur les vingt

Le motif de mon refus du 2026-08-29 etait `COMP-2`, et j'avais ecrit son critere de levee :
**masquer le texte-support, presenter question et propositions a quelqu'un qui n'a pas lu, verifier
qu'il ne peut ecarter aucune proposition.** Je l'ai refaite sur les vingt items, pas seulement sur
les trois reecrits - un item que j'avais laisse passer peut avoir ete casse par la reecriture d'un
autre, et je n'avais aucune raison de le supposer indemne.

**Aucun des vingt items ne permet d'ecarter une proposition sans lire le texte. `COMP-2` est
tenue.**

**Les trois items reecrits, verifies sur le principe declare par l'auteur** - le distracteur porte
desormais le stereotype, la bonne reponse exige la lecture :

| Item | Ce qu'un enfant qui n'a pas lu repondrait | Ou se trouve cette reponse |
|---|---|---|
| Le pecheur | "des poissons frais" | **distracteur** ; la bonne reponse est "des produits sucres", inaccessible sans le filet revenu vide |
| Le cahier rouge | "a noter les devoirs du soir" | **distracteur** ; la bonne reponse exige de relier la mesure hebdomadaire et le chiffre inscrit |
| Les bougies | voir la reserve 2 ci-dessous |  |

**L'inversion est reelle sur les deux premiers** : la culture generale y conduit desormais a la
mauvaise reponse, ce qui est exactement le contraire du defaut que j'avais refuse.

**Un item que je n'avais pas signale le 2026-08-29 et que je reverifie plutot que de le supposer
indemne** : "Pourquoi les pompiers sont-ils venus ?", avec "un incendie", "un chat coince" et "une
fuite d'eau". Je le tiens conforme, et je dis pourquoi plutot que de le taire : les trois sont des
interventions de pompiers que tout enfant connait, **aucune ne s'ecarte**, et "un incendie" n'est
qu'un pari de frequence, pas une reponse definitionnelle. C'est ce qui le distingue de l'ancien
"Que vend le vieux pecheur au marche ?", ou "des produits de la mer" etait dans la definition meme
du mot "pecheur". **C'est la distinction que j'ai appliquee le 2026-08-29 et je l'applique
identiquement aujourd'hui.**

#### `COMP-1` - reverifiee mecaniquement, contre la declaration de l'auteur

`marc-dev` declare avoir verifie que `COMP-1` reste intacte apres la reecriture. **Je l'ai remesuree
plutot que de le croire.**

Methode : pour chacun des vingt items, extraction des mots de **quatre lettres ou plus** du
texte-support et des trois propositions, comparaison **insensible aux accents et a la casse**, mots
grammaticaux exclus. **Zero recouvrement sur les vingt items, soixante propositions.** La declaration
est exacte.

#### Les autres conditions

| Condition | Verdict | Mesure |
|---|---|---|
| `COMP-3` | **tenue** | Les **20 items sur 20** portent desormais le marquage et ses deux informations reliees, contre 17 avant le lot. Le plancher d'un tiers (7/20) est tres largement franchi. **Je ne rouvre pas le critere que j'ai declare imprecis le 2026-08-29** : il sera reecrit en condition neuve et datee avant le prochain paquet de comprehension |
| `COMP-5` | **tenue** | Satisfaite et close depuis le 2026-08-19. Je n'ai pas rejuge un rendu que j'ai accepte |
| `COMP-6` | **tenue** | Jugee sur sa formulation du 2026-08-28, par le moyen que j'avais moi-meme declare suffisant. **Je ne deplace pas ce poteau** |
| **Exactitude - item du lion** | **tenue - reserve 4 levee** | La bonne reponse dit desormais "principalement matin et soir" la ou le texte dit "surtout au lever et au coucher du soleil". **Le mot "seulement", qui rendait la reponse fausse, a disparu.** Le choix de "principalement" plutot que du mot du texte est le bon : reprendre "surtout" aurait viole `COMP-1`. Et la culture generale de l'enfant, qui associe le lion a la nuit, le conduit ici au distracteur - donc elle ne donne pas la reponse |
| **Accentuation** | **tenue** | Meme methode que sur les deux autres paquets : 124 chaines, 480 jetons distincts, 385 sans caractere accentue, **dont aucun ne devrait en porter**. Les homographes accentuables (a, ou, la, du, des, sur) releves en contexte un par un : tous corrects |
| `TYP-1` | **tenue** | Les 20 enonces portent une espace insecable devant le signe. **Mesure au navigateur reel a 744 x 551 : zero signe orphelin sur ce paquet** |
| **Biais et ton** | **tenue** | Prenoms et roles varies. Le jardinage est tenu par un homme age, l'enseignement par une maitresse, la randonnee par un grand-pere ; aucune configuration familiale n'est presupposee ; aucun item ne suppose un bien que tous les enfants n'ont pas |

#### VERDICT : VALIDE

**Done pedagogique prononce le 2026-08-30 par `nadia-prof`.** `camille-po` peut passer la ligne de
matrice de `S17-2` a `couvert-valide`.

**Reserve 3 (bloquante, 2026-08-29, `COMP-2`) : levee.** **Reserve 4 (bloquante, 2026-08-29, le mot
du lion) : levee.** **Reserve 7 (non bloquante, le commentaire de feuille de style qui m'attribuait
un jugement) : levee** - j'ai verifie que la phrase a disparu du depot, elle n'y figure plus nulle
part. Les verdicts du 2026-08-29 restent ecrits au-dessus.

#### Reserves nouvelles, non bloquantes

- **Reserve 1, non bloquante, 2026-08-30 - l'en-tete se sous-declare.** Il affirme que "17 items sur
  20 sont marques `# COMP-3`" ; le fichier en porte **20 sur 20**. La phrase etait vraie avant le
  lot et ne l'est plus. Ce n'est pas un defaut de contenu et l'enfant ne le lit pas, mais un tiers
  qui verifie `COMP-3` sans rejouer le paquet lira un chiffre faux. Meme famille que la reserve E
  du paquet des durees.
- **Reserve 2, non bloquante, 2026-08-30 - l'item des bougies a la marge la plus mince des vingt.**
  La reecriture a permute les deux propositions les plus fortes : "le courant est coupe" etait la
  bonne reponse, c'est desormais un distracteur, et "ils fetent un anniversaire" a fait le chemin
  inverse. Les trois propositions sont les memes qu'avant.
  **Sous ma procedure ecrite - l'elimination - `COMP-2` est tenue** : rien ne s'ecarte, et un enfant
  qui n'a pas lu se trouve devant deux candidats aussi forts l'un que l'autre, qu'il ne peut
  departager qu'avec l'odeur de gateau depuis midi et les volets fermes avant la nuit. **Je la
  declare tenue.**
  **Ce que je note quand meme** : l'association bougie-anniversaire est, pour un enfant, au moins
  aussi immediate que l'association bougie-panne. La seconde clause de `COMP-2` - "aucune ne se
  devine par la seule culture generale" - **ne tranche pas ce cas**, et c'est une imprecision de ma
  redaction, de la meme famille que les trois que j'ai reconnues le 2026-08-29. **Je ne l'applique
  pas plus durement aujourd'hui qu'hier** ; je la reecrirai en condition neuve et datee avant le
  prochain paquet de comprehension, avec un test qui separe l'elimination du pari.

#### `MNC-1`

**`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element
quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice, proposition. Cette
propriete est sortie de la porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28
(`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
paquet.

Ce paquet est le seul du catalogue a rendre **trois** blocs de texte avant les propositions -
consigne, texte-support, enonce. La mention le concerne donc plus que les autres, et elle reste
exactement aussi inconditionnelle.

#### Ce que je reste seule a garder sur ce paquet

**`COMP-2`**, et je confirme le motif de `marc-dev` parce qu'il est le mien : un automate n'a pas de
culture generale a mettre en defaut, et les deux voies envisageables retombent soit sur une liste
figee d'associations, soit sur un critere aussi imprecis que celui que je viens de reconnaitre a la
reserve 2. **Aucun garde-fou n'existe et je n'en demande pas un mauvais.** Je garde aussi
**`COMP-3`** dans son intention - relier deux informations, et non une seule -, **l'exactitude des
vingt textes-supports**, **l'accentuation**, et **l'absence de stereotype**.

**Le corollaire, pour que personne ne se trompe sur ce que ce Done couvre** : `COMP-2` est verifiee
par une personne, item par item, a chaque livraison. Un item ajoute a ce paquet sans que je le
rejoue n'est couvert par rien.
