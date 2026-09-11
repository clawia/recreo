# Fiche de conformite - Le corps et l'effort (`bandit-manchot-corps-effort-eps-cp`)

- **Mecanique** : `bandit-manchot`
- **Contenu(s)** : `cp-eps-corps-effort` (22 items, 11 par ligne)
- **Statut global** : **VALIDE SOUS RESERVE** - **Done pedagogique prononce sur les deux lignes**
  (2026-09-11)
- **Derniere mise a jour** : 2026-09-11

> **Fiche ouverte par `nadia-prof`, instance C, le 2026-09-11**, le paquet etant neuf et n'en ayant
> aucune. **C'est la troisieme fiche d'education physique et sportive du depot**, apres
> `FICHE-bandit-manchot-regles-securite-eps-cp.md` et
> `FICHE-bandit-manchot-regles-jeu-collectif-eps-cp.md`. J'ai rempli les sections descriptives depuis
> la story `S34-EPS-CORPS-EFFORT` du backlog et depuis le referentiel, sans rien y ajouter de mon
> cru ; **le bloc de validation est le mien, comme toujours**. Si `camille-po` veut reprendre la main
> sur les sections qui lui reviennent, elle le peut : elles ne portent aucun jugement.

## Competences visees

**Deux lignes servies par un seul paquet**, a consigne de deux clauses - grain arbitre par
`camille-po` le 2026-09-10 et confirme par `nadia-prof` le meme jour (arbitrage prealable de s34,
section 4.3). Chaque item est attribue a une ligne et une seule (`ADR-0081`, clauses 4 et 5).

| Matiere | Niveau | Domaine | Competence | Reference au referentiel | Items |
|---|---|---|---|---|---|
| Education physique et sportive | CP (cycle 2) | Entretenir sa sante par la pratique | Reconnaitre les effets de l'effort sur son corps (essoufflement, coeur qui bat vite, chaleur, soif) | `EPS-CP-10`, `squad/programme/education-physique-et-sportive/competences-cp.md` | 1 a 11 |
| Education physique et sportive | CP (cycle 2) | Entretenir sa sante par la pratique | Connaitre quelques regles d'echauffement, d'hydratation et de recuperation autour de l'effort | `EPS-CP-11`, meme referentiel | 12 a 22 |

## La frontiere corps-et-effort, opposable des le premier item

Ecrite par `nadia-prof`, reprise par `DEC-0083` section 9.2, **recopiee mot pour mot dans la story
avant qu'un seul item ne soit ecrit** (refus d'avance numero 11) :

> - L'EPS ne porte que l'effort lui-meme : ce que l'effort fait au corps dans l'instant
>   (essoufflement, coeur qui bat vite, chaleur, soif), ce qui l'entoure (echauffement, hydratation,
>   recuperation) et les regles de securite de la pratique. Jamais l'hygiene de vie en general.
> - Les sciences CM1 ne portent que les quatre facteurs de sante generale - alimentation, activite
>   physique, sommeil, hygiene - rapportes au fonctionnement du corps, jamais aux effets immediats
>   d'un effort.
> - Questionner le monde CP ne porte que le niveau CP de l'hygiene de vie, et `CYC-1` s'applique :
>   aucun item de cycle 3 ne contredit ce que le CP enseigne sur le meme objet.

## Criteres pedagogiques attendus - camille-po

Repris de `docs/backlog/backlog.md`, story `S34-EPS-CORPS-EFFORT`.

- [x] Au moins vingt items, strictement plus d'items que de manches (`FER-2`, `ADR-0023`) - **22,
      onze par ligne**
- [x] Deux taches distinctes au minimum par ligne (`FER-3`) - **quatre au total**
- [x] Chaque fait sur le corps a l'effort verifie contre une source (`BIA-1`, `BIA-2`)
- [x] Aucun item ne porte sur l'hygiene de vie en general, l'alimentation, le sommeil ou l'hygiene
      corporelle hors du contexte de l'effort (frontiere ci-dessus)
- [x] Chaque regle est rappelee dans l'enonce ou universellement scolaire (`EPS-1`)
- [x] Aucun item ne compare des corps, des performances ou des capacites (`EPS-2`)
- [x] Aucune attitude, aucune emotion, aucune conduite morale portee par une bonne reponse (`EPS-3`)
- [x] Aucun indice ne decrit la dynamique d'un effort par une image lue au premier degre (`EPS-6`)
- [x] Chaque indice apporte un fait que l'enfant ne peut pas deviner de l'enonce seul (`EPS-8`)
- [x] Chaque item fixe le moment de l'effort dont il parle (refus d'avance numero 8)
- [x] `FAB-1a`, `FAB-1b`, `FAB-1c`
- [ ] **Chaque proposition est un candidat qu'un enfant de CP peut serieusement envisager
      (`PLA-1`)** - **non tenue sur trois propositions** (`EFF-R1`, `EFF-R2`, `EFF-R6`)
- [x] Au plus 15 mots et deux phrases par enonce (`LON-1`, `LON-2`)
- [x] Au plus la moitie des items d'une tache portent deux propositions (`BIN-1`) - **zero item
      binaire**
- [ ] **Le paquet declare ses deux lignes et attribue chaque item a l'une des deux (`ADR-0081`,
      clauses 4 et 5)** - **declaration faite, attribution fausse sur un item** (`EFF-R4`)
- [ ] **La consigne nomme les deux taches reellement servies en deux clauses (`DEC-0078`)** - **elle
      nomme deux themes pour quatre taches** (`EFF-R7`)
- [ ] **Les reserves non bloquantes ouvertes par ce lot neuf sont soldees dans le meme sprint
      (`PAR-10`)** - **impossible : le lot est livre apres la fermeture de la fenetre de
      developpement de s34** ; les sept reserves partent a s35

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **VALIDE SOUS RESERVE** - **Done pedagogique prononce sur `EPS-CP-10` et sur
  `EPS-CP-11`**, independamment l'une de l'autre
- **Date** : 2026-09-11
- **Auteur** : `nadia-prof`, **instance C de s34**
- **Increment audite** : `S34-EPS-CORPS-EFFORT`, commit `8ee750f0`, arbre fusionne `a7852398`
- **Audit joue** : **oui.** Jar reconstruit depuis un worktree dedie, application lancee sur un port
  local, arretee **par l'identifiant de son processus**. **Quatre parties, quarante manches, les
  vingt-deux items vus a l'ecran** avec erreur volontaire sur chacun pour lire l'indice servi ; plus
  **une partie sans aucune erreur**, **une partie avec une seule erreur** et **un chemin d'echec
  repete seize fois sur un meme item** pour voir ce que le produit dit a un enfant bloque.
- **Audit degrade sur un point, declare** : joue par requetes HTTP reelles, donc **HTML servi** et
  non page rendue par un navigateur. **Ni couleur, ni contraste, ni taille, ni position** - aucune
  conclusion de cette fiche n'en depend.
- **Premier contact de trois proprietes avec du contenu reel** : `EPS-2`, la frontiere
  corps-et-effort, et le refus d'avance numero 8 (le moment de l'effort). **Mon attente de fermeture
  etait *0 a 1 pour la paire*, et je penchais vers 0** - motif ecrit : le seul taux mesure de l'EPS
  au premier contact etait alors zero sur deux.
- **Document de verdicts** :
  `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/verdicts-nadia-instance-C.md`

### Pourquoi les deux lignes ferment, alors que j'attendais qu'elles ne ferment pas

**Les quatre modes de defaillance que j'avais nommes d'avance le 2026-09-10 : aucun des quatre n'est
pris.**

| Mode de defaillance nomme d'avance | Etat, verifie a l'ecran |
|---|---|
| Une performance chiffree presentee comme une norme (*"au bout de combien de minutes est-on essouffle ?"*) | **EVITE.** Il n'y a pas un seul chiffre dans les vingt-deux items |
| Une conduite en bonne reponse a la deuxieme personne (*"que dois-tu faire si tu as soif ?"*) | **EVITE.** Les vingt-deux enonces sont impersonnels |
| Un indice qui decrit l'effort par une image (*"le coeur s'emballe"*, *"les jambes deviennent lourdes"*) | **EVITE.** Les vingt-deux indices apportent un fait mecanique ou physiologique |
| Un item qui ne fixe pas le moment de l'effort dont il parle | **EVITE, et c'est le point le plus visible du lot.** Les vingt-deux enonces posent leur moment en tete : *"Pendant"*, *"Juste après"*, *"Le lendemain"*, *"Avant"*, *"autour d'un effort"* |

**L'exactitude, ensuite, parce que c'est ce que le prix de 1,5 achetait.** Les vingt-deux faits
credites sont exacts, verifies un par un : essoufflement, frequence cardiaque, chaleur produite par
le muscle, soif par perte d'eau en transpirant, retour au calme non immediat, rougeur du visage,
echauffement contre la blessure, hydratation avant, pendant et apres, repos apres l'effort,
courbatures le lendemain. **Aucune reponse creditee n'est fausse.**

### La frontiere corps-et-effort, verifiee item par item

**Tenue.** Aucun item ne porte sur le sommeil, l'alimentation ou l'hygiene du corps. **Les trois
seules mentions d'un repas ou du soir vivent dans des distracteurs**, a l'interieur d'une question
ancree sur l'effort. **Aucun item de ce paquet ne pourrait etre compte sur la ligne de sciences CM1
ni sur celle de questionner le monde CP** : le recouvrement que `DEC-0083` craignait n'existe pas.

**Et elle est ecrite dans la specification du lot avant le premier item**, dans la story comme en
tete du fichier de paquet. **Le refus d'avance numero 11 attendait son increment depuis deux
sprints ; il est paye.**

### Les conditions, comptees a la main

`FER-2` : **22 items, 11 par ligne**, plancher de dix par ligne tenu (`DEC-0064`). `FER-1` : les
quatre elements enumeres par `EPS-CP-10` sont chacun servis en bonne reponse (items 1 a 4), les trois
de `EPS-CP-11` aussi (items 12, 13, 14). `FER-3` : quatre taches, deux par ligne. `BIN-1` : zero item
binaire. `EPS-2`, `EPS-3`, `EPS-6`, `BIA-4`, `FAB-1a`, `FAB-1b`, `FAB-1c` tenues. `LON-1` et `LON-2`
tenues, le plus long enonce faisant quatorze mots - **executees par `ADR-0089`, je ne les recompte
pas**. **Tout le reste est compte a la main : aucun test de paquet n'existe pour l'EPS, et il y a
maintenant trois paquets dans cette matiere.**

### Sept reserves non bloquantes - aucune ne fait apprendre a l'enfant quelque chose de faux sur la competence visee

**Le detail au format `RES-1` - constat, objet, mode de defaillance, perimetre - est dans le document
de verdicts, section 5.3. Resume opposable :**

| Identifiant | Ligne | Objet en une phrase | Perimetre |
|---|---|---|---|
| `EFF-R1` | `EPS-CP-10`, `EPS-CP-11` | Les trois propositions d'un item doivent etre trois reponses que l'enfant peut lire et envisager - *"somnolent"* (item 1) et *"d'un repas très copieux"* (item 14) ne sont pas du vocabulaire du CP | 2 propositions |
| `EFF-R2` | `EPS-CP-10` | Sur un lot qui parle du corps de l'enfant, aucune proposition ne doit lui presenter comme envisageable une chose qui lui ferait peur si elle etait vraie - *"il s'arrête un instant"*, du coeur (item 2) | 1 distracteur |
| `EFF-R3` | `EPS-CP-10` | Chaque effet enseigne doit etre un effet que l'enfant qui joue peut reconnaitre sur lui-meme - la rougeur du visage (item 6) n'est pas visible sur toutes les peaux. **C'est celle des sept que je paierais en premier** | 1 item |
| `EFF-R4` | `EPS-CP-11` | Chaque item doit travailler la ligne sur laquelle il est compte - l'item 17 decrit un effet de l'effort sur le corps et est compte sur la ligne des regles | 1 item ou son etiquette |
| `EFF-R5` | `EPS-CP-10` | L'enfant qui a choisi une proposition vraie a un autre moment doit repartir en sachant quand elle est vraie - *"on a froid"* (item 3), *"affamé"* (item 1) | 2 indices |
| `EFF-R6` | `EPS-CP-11` | Aucune proposition vraie n'est marquee fausse - *"de transpirer moins vite"* (item 22) est un effet reel du manque d'eau | 1 distracteur |
| `EFF-R7` | les deux | L'enfant doit savoir, en lisant la consigne, quel genre de reponse on attend de lui - elle nomme deux themes, le paquet sert quatre taches et dix items demandent *"pourquoi"* ou *"que risque-t-on"* | 1 consigne de catalogue, 0 item |

**Regime commun** : **non bloquantes**, les deux lignes ferment avec elles. **Prix que je rends :
0,5, un rejeu pour le paquet entier** - elles vivent toutes dans un seul paquet, aucune tache n'est
reecrite en entier, ni `PAR-14` ni `PAR-15` ne mordent. **Soldees a s35** : `PAR-10` demandait le
meme sprint, et la fenetre de developpement de s34 etait fermee quand j'ai joue.

### Points que je ne transforme pas en reserve, et je le dis pour qu'on ne me les redemande pas

- **Les items 12 et 15, 18 et 21, 19 et 22 disent la meme chose dans deux directions**, et la ligne
  `EPS-CP-11` porte donc onze items pour **trois objets**. **C'est exactement ce que son libelle
  enumere** (`FER-1`), et au CP rencontrer trois fois la meme regle sous trois angles est un moyen
  d'apprentissage. **Ecarte.**
- **Le groupe de mots *"un effort physique intense"* revient dans la moitie des enonces, et ni
  *physique* ni *intense* n'est du vocabulaire de CP.** Je tranche contre la reserve : **les mots qui
  portent la discrimination sont *pendant*, *juste apres*, *le lendemain* et *avant*, tous lisibles
  au CP** ; les deux mots savants sont dans le decor de la question, jamais dans ce qui la fait
  gagner. **Je note la densite et je ne demanderai pas une reecriture pour tenir un compte de mots.**
- **Les indices sont longs**, jusqu'a douze mots, avec *articulations* et *progressivement* a
  l'item 21. **`LON-1` et `LON-2` portent sur les enonces** et je n'ai aucune barre sur la longueur
  des indices. **Je note la question, je ne cree pas une barre en cours de sprint.**
- **L'orthographe de *coeur*, sans ligature**, dans quatre items et deux indices. **Ce n'est pas ma
  porte** : l'orthographe du texte lu par l'enfant appartient au `relecteur-pedagogique`
  (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`). **Route, pas corrige.**
- **`EPS-3` appliquee a la lettre de mon propre refus d'avance numero 9 aurait refuse `EPS-CP-11` en
  entier.** L'abregement etait le mien - j'avais ecrit *"une conduite"* la ou le referentiel dit
  *"une conduite morale"*. **Corrige le 2026-09-11** (verdicts, section 2.1). **Une regle factuelle
  et impersonnelle n'est pas une conduite morale**, et c'est deja la forme validee sur
  `cp-eps-regles-de-securite`.

### Mentions de couverture partielle - condition de la fermeture (refus d'avance numero 7)

**Ces deux mentions n'existaient pas : la matrice n'en portait de pre-ecrite que pour les lignes 4
et 8. Je les ecris aujourd'hui, et `camille-po` les recopie mot pour mot.** Une fermeture sans elles
est refusee.

| Ligne | Mention |
|---|---|
| `EPS-CP-10` | *"connaissance des effets de l'effort seule ; ce produit ne recoit aucun corps en mouvement et n'observe aucun effet reel sur l'enfant qui joue"* |
| `EPS-CP-11` | *"connaissance des regles qui entourent l'effort seule ; ce produit n'observe ni echauffement, ni boisson, ni repos reels"* |

**Et la mention d'ensemble du referentiel reste due des qu'une de ces lignes bouge** : *"connaissances
qui entourent la pratique physique seules ; aucune activite physique, aucun geste et aucune
performance ne sont servis par ce produit"*.

### Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude de chaque fait sur le corps a l'effort** | **aucune.** Vingt-deux faits verifies a la main, et les quatre jobs sont verts sur l'arbre qui les porte |
| **La frontiere corps-et-effort** | **aucune**, et elle vient d'etre tenue pour la premiere fois. **Elle redeviendra dangereuse au premier lot de sciences CM1 ou de questionner le monde CP sur la sante**, ecrit par une autre instance que celle-ci |
| **`EPS-2`, `EPS-3`, `EPS-6`, `EPS-8`, `BIA-4`, le refus d'avance numero 8** | **aucune** |
| **`PLA-1`** | **aucune**, et ce paquet en donne deux formes que rien ne distingue : le mot illisible (`EFF-R1`) et la proposition factuellement vraie (`EFF-R6`) |
| **Ce qu'une proposition enseigne par elimination** | **aucune**, et **c'est neuf** : `EFF-R6` est le premier cas ou le defaut n'est pas dans ce que l'item credite mais dans ce qu'il ecarte |
| **L'universalite d'un fait sur le corps** | **aucune**, et **c'est neuf aussi** (`EFF-R3`). **Je ne pose pas de condition nouvelle en cours de sprint** ; la reserve suffit pour ce lot |
| **`FER-1`, `FER-2`, `FER-3`, `BIN-1`, `FAB-1a`, `FAB-1b`, `FAB-1c`** | **aucune** : aucun test de paquet n'existe pour l'EPS. **Route a `theo-archi` a s32, a s33, et de nouveau ici - trois paquets maintenant** |
| **Le texte de l'ecran de fin de partie** | **aucune**, et **mon refus d'avance numero 6 s'est realise dessus** en jouant ce paquet : un enfant qui finit les dix manches en trouvant chacune au second essai lit *"Score : 0"* et rien d'autre. **Defaut transverse a toutes les entrees du catalogue, porte par `S34-SCO1-REMEDE`, audite par l'instance A : je ne le porte au debit d'aucune de mes lignes** (verdicts, section 6) |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EPS-CP-10` et `EPS-CP-11` passent a `couvert-valide`** | `camille-po` | Chacune avec sa mention ci-dessus. **Je ne touche pas la matrice** |
| **`EFF-R1` a `EFF-R7`** | `camille-po`, via `sofia-sm` | **Rejeu 0,5 pour le paquet entier**, solde a s35 |
| **L'orthographe de *coeur*** | `relecteur-pedagogique` | Texte lu par l'enfant, registre orthographique |
| **Un test de paquet pour les trois paquets d'EPS** | `theo-archi`, via `sofia-sm` | Route a s32 et s33, non paye |

### Historique des verdicts sur ce paquet

| Date | Verdict | Instance | Motif en une ligne |
|---|---|---|---|
| 2026-09-11 | **VALIDE SOUS RESERVE**, Done pedagogique **prononce sur les deux lignes** | C (s34) | Vingt-deux faits exacts, les quatre modes de defaillance nommes d'avance tous evites, la frontiere corps-et-effort tenue et ecrite avant le premier item. **Sept reserves neuves, `EFF-R1` a `EFF-R7`, toutes non bloquantes** |
