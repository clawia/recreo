# Fiche de conformite - bandit-manchot-conjugaison-present-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec
  `bandit-manchot-additions-cp`, `bandit-manchot-homophones-cm1` et
  `bandit-manchot-le-bon-son-cp`).
- **Contenu** : paquet a creer sous `adapters/adapter-content/src/main/resources/contenu/`
  (nom de fichier laisse a `theo-archi` / aux developpeurs, par exemple
  `cm1-conjugaison-present.yaml`), `type: question`. Matiere premiere issue de `contenu-repris/
  textes-trous/cm1-conjugaison.yml`, 6 items a l'etat de reserve, a completer et a repasser devant
  `nadia-prof` comme un contenu neuf (`contenu-repris/README.md`, `CLAUDE.md`).
- **Contenu livre et audite** :
  `adapters/adapter-content/src/main/resources/contenu/cm1-conjugaison-present.yaml`, 12 items,
  `type: question`, chacun porteur d'un indice. Entree de catalogue
  `bandit-manchot-conjugaison-present-cm1`, titre "Le bon verbe", 10 manches. Six items repris de
  `contenu-repris/textes-trous/cm1-conjugaison.yml`, six rediges en s06 ; les douze indices sont
  neufs.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique accorde le 2026-08-11, quatrieme
  audit, sur l'increment corrige de S8-5, commit `bd9a8f0`). Le **REFUSE du 2026-08-11 (troisieme
  audit) est LEVE** : son motif unique - `mangeons` a 156,02 px de glyphes dans une boite de
  152,00 px, ecrit sur la paroi du cabinet - est ferme. Mesure par moi sur le rendu servi, en Chrome
  reel a 360 x 640 px exactement : **zero** proposition qui deborde, **zero** sous 2,0 px d'air,
  minimum du paquet **2,86 px** sur `mangeons`, sur **les 100 emplacements de proposition** des 25
  items. Mes **sept conditions** du meme jour sont tenues, verifiees une par une, dont deux mises a
  l'epreuve sur la feuille de production plutot que lues. Le verdict est **sous reserve** pour une
  seule raison, qui n'est pas un motif : le defaut de pli, 24 items sur 25 quand l'indice s'affiche.
  **La ligne de matrice francais CM1 conjugaison doit passer a `couvert-valide`** (condition de
  `DEC-0018` section 4 remplie) ; je ne l'edite pas, elle appartient a `camille-po`. Le refus n'est
  pas efface : il reste lisible plus bas avec son motif et sa date.
  **Historique des verdicts anterieurs, conserve entier :**
  **REFUSE** (Done pedagogique refuse le 2026-08-10, premier audit). Motif
  unique et suffisant : cinq des douze indices enoncent une regle de conjugaison fausse, et trois
  d'entre elles sont dementies par un autre item du meme paquet.
  **Arbitrage prealable au developpement du 2026-08-10** : la reduction de ce paquet a **trois
  propositions par item est ACCEPTEE**, sur son propre motif - la quatrieme proposition est sous le pli
  a 360 x 640 - avec les douze triplets exacts fixes nommement. **Cette reduction ne fait pas tenir ce
  paquet en colonnes** : mesure sur le rendu, neuf items sur douze debordent encore a trois colonnes, et
  deux y rendent deux propositions indiscernables. Ce n'est pas un Done pedagogique, et le refus des
  cinq indices reste entier : il faut **les deux** corrections. `DEC-0014` n'est pas leve.
  **ETEINTE le meme jour par mon second arbitrage du 2026-08-10** (bloc en fin de fiche) : deux mesures
  posterieures montrent que mon motif visait une cause fausse - le defaut de pli vient de la longueur
  de l'enonce, pas du nombre de propositions, et il frappe aussi un paquet a deux propositions du
  catalogue - et que le repli en deux rangees d'`ines-design` rend le mot de la quatrieme proposition
  entierement lisible. **Les douze triplets ne sont plus applicables, le paquet garde ses quatre
  propositions, aucun distracteur n'est retire.** Ils restent ecrits comme trace, jamais effaces. La
  seule correction attendue sur ce paquet reste celle des cinq indices fautifs.
  **Second audit, increment livre en s07 (commit `10dd74d`) : VALIDE SOUS RESERVE, Done pedagogique
  accorde le 2026-08-10. Le REFUSE du 2026-08-10 est LEVE** : les douze indices sont desormais vrais et
  aucun ne dementit un autre item, verifie un par un et vu en jeu. Le refus n'est pas efface, il reste
  lisible plus bas avec son motif. Jeu joue sur une **branche jetable, jamais fusionnee et detruite**,
  qui n'ajoutait que la ligne de catalogue : `DEC-0014` n'est pas leve par mon audit. Le **repli en
  deux rangees est reproduit sur le code livre** et remesure (160 px, ecart de 16 px sur les deux axes,
  aucune troncature, les quatre propositions au-dessus du pli sans l'indice) - c'est la mesure que
  demandait la seconde condition de `camille-po`, qui reste la seule a pouvoir la fermer. Deux
  reserves neuves : `mangeons` tient a **2,0 px** de chaque cote de sa boite, et cinq items sur douze
  mettent la seconde rangee sous le pli quand l'indice s'affiche (`DEC-0016`, pas un motif).
  **Ma reference sur les modeles d'indice etait fautive et je la corrige** : le modele "nommer le
  groupe" n'existait pas dans le paquet, `marc-dev` a eu raison de ne pas l'appliquer.
- **Ligne de matrice** : **reste `en cours`.** Elle ne pouvait de toute facon pas atteindre
  `couvert-valide` sur cet increment (un temps sur trois, `DEC-0013`) ; le Done pedagogique est
  desormais accorde, mais la barre que j'ai ecrite moi-meme pour `couvert-valide` - ouvrir le futur et
  l'imparfait, refus du 2026-08-10 section 5 - est inchangee et non atteinte. **La ligne ne bouge pas
  sur le verdict du second audit.**
- **Arbitrage prealable au developpement du cabinet visible du 2026-08-11** (bloc en fin de fiche,
  arbitrage complet dans `FICHE-bandit-manchot-additions-cp.md`) : cabinet **compatible avec mon veto
  sous six proprietes verifiables**. **Ce jeu est explicitement hors du tableau de planchers de
  largeur** : il n'est pas au catalogue et sa disposition reste une saisine de `camille-po` avec
  `theo-archi`. Ce n'est pas un Done pedagogique, il est degrade, et aucun verdict de cette fiche
  n'est touche.
- **Troisieme audit, chantier de la conjugaison etendue (S8-4 et activation au catalogue), du
  2026-08-11, prononce sur l'increment livre (`533967d`) : REFUSE.** Audit joue, non degrade : douze
  parties, les vingt-cinq items vus, chacun aussi avec son indice. **Ma barre fermee de six points est
  LEVEE** - le contenu de S8-4 les satisfait toutes les six, et c'est la seule chose qui gouverne
  `couvert-valide`. **Ce qui est refuse est l'activation de ce jeu au catalogue**, sur un motif unique
  et neuf : a 360 px, dans le cabinet, les quatre colonnes mesurent **152,00 px** et deux propositions
  du paquet sont plus larges que leur boite - `mangeons` a **156,02 px** sort de **2,02 px** de chaque
  cote et vient s'ecrire sur la paroi du cabinet. C'est ma reserve 1 du 2026-08-10, ecrite de ma main,
  qui se realise. **Je reponds au point d'interpretation de `marc-dev`** : le 2e groupe absent de
  l'imparfait ne viole pas mon point 1, tel que je l'ai ecrit. **Le defaut de pli, 24 items sur 25,
  n'est PAS le motif** et je dis pourquoi.
- **Quatrieme audit, increment corrige de S8-5, du 2026-08-11, prononce sur l'increment livre
  (`bd9a8f0`) : VALIDE SOUS RESERVE, Done pedagogique accorde. Le REFUSE du 2026-08-11 est LEVE.**
  Audit joue, non degrade : 22 parties, 220 etats mesures, les 25 items vus et les 25 vus avec leur
  indice, **les 100 emplacements de proposition mesures sur le rendu servi** en Chrome reel a
  360 x 640 px exactement. Mon motif est ferme : zero debordement, zero sous 2,0 px, minimum
  **2,86 px** sur `mangeons`, et sur la capture le mot est entier dans son bouton avec du bleu de
  chaque cote. **Mes sept conditions sont tenues**, dont les conditions 2 et 3 mises a l'epreuve sur
  la feuille de production (1,8125 rem rougit, `calc` rougit) plutot que crues. **Je corrige deux de
  mes propres corrections du matin** : mon chiffre de refus -2,02 px etait exact et ma "correction" a
  -2,01 px etait fausse. **Le pli reste hors motif** et c'est lui, et lui seul, qui fait de ce Done un
  Done **sous reserve**. **La ligne de matrice doit passer a `couvert-valide`** ; je ne l'edite pas.
- **Note du 2026-08-11 sur le bullet "Ligne de matrice" ci-dessus** : il date du second audit et
  n'est plus l'etat courant. Il reste ecrit, non efface. L'etat courant est celui du quatrieme audit.
- **Arbitrage prealable de mecanique du 2026-08-12** (`DEC-0023` point 6 : l'ecran intermediaire
  disparait) : ecrit dans `FICHE-bandit-manchot-additions-cp.md`, bloc de validation, section
  "Arbitrage prealable au developpement - la mecanique apres la disparition de l'ecran
  intermediaire". **Il vaut pour ce jeu comme pour les huit du catalogue**, la mecanique etant
  commune : conditions M1 a M13, dont **deux bloquantes** - M4 (le message d'erreur doit etre vu
  sans defiler a 360 x 640) et la clause 1 de M13 (apres une reponse, le focus ne va jamais sur la
  sortie, qui detruit la partie sans confirmation). Ce n'est pas un Done pedagogique, rien n'est
  code.
  **Ce jeu est vise nommement par deux points** : la **reserve A** du 2026-08-12 - ses **25 indices
  sur 25** nomment la bonne reponse entre guillemets, manquement que je nomme comme le mien puisque
  je les avais valides sans verifier ce point - et la condition **M2**, qui lui interdit d'afficher
  son indice sur la question courante tant que ces textes ne sont pas reecrits.
- **Arbitrage prealable du 2026-08-13 sur l'indice appele par l'enfant (une ampoule) - ce jeu est le
  seul vise nommement.** L'arbitrage complet est dans `FICHE-bandit-manchot-additions-cp.md`, bloc de
  validation, section "Arbitrage prealable au developpement - l'indice appele par l'enfant (une
  ampoule), du 2026-08-13" : **accepte dans son principe, six conditions M16 a M21, M17 bloquante**.
  Ce n'est pas un Done pedagogique et il ne touche aucun verdict de cette fiche ; **mon refus du
  2026-08-13 tient**. Ce qui concerne ce paquet, et que je recopie ici parce que c'est ici qu'on
  viendra le chercher :
  **M21 - ce jeu ne portera pas d'ampoule.** Ses **25 indices sur 25** nomment la bonne reponse,
  reverifie sur le paquet servi ce jour ; l'utilisatrice l'a constate en jouant - un seul appui faux
  suffit pour que l'indice donne la reponse. Une aide a la demande n'est pas une porte de sortie a
  **M2** : l'ampoule n'est pas rendue du tout sur ce paquet, ni grisee ni inerte, tant que les textes
  ne sont pas reecrits (**reserve A**).
  **Consequence que j'accepte et que je nomme (reserve S)** : sur ce jeu, un enfant qui rate trois
  fois recevra **la reponse et rien d'autre**. Ce paquet ne porte aucun champ `explication` -
  `cp-le-bon-son` est le seul du catalogue qui en porte. **La reserve A cesse d'etre une amelioration
  et devient ce qui rend ce jeu enseignable.**
- **Arbitrage prealable de forme du 2026-08-12, deux conditions qui portent nommement sur ce paquet.**
  L'arbitrage complet est dans `FICHE-bandit-manchot-additions-cp.md` (bloc du 2026-08-12, "la forme du
  meuble") ; ce n'est pas un Done pedagogique et il ne touche aucun verdict de cette fiche. Ce qui
  concerne ce paquet, et que je recopie ici parce que c'est ici qu'on viendra le chercher :
  **F5 - les 2,0 px de ma condition 4 du quatrieme audit sont un PLANCHER, pas un constat.** Aucune
  colonne de ce jeu ne descend sous **150,27 px** (`146,27 + 2 x 2,00`) ; l'air de chaque proposition
  reste strictement positif et **au moins 2,0 px par cote** sur les 100 emplacements. Je ne releve pas
  ce plancher a l'etat courant de 2,86 px. Trois candidats d'elargissement de paroi ont ete rendus a
  360 px et mesures : l'elargissement symetrique a 1,25 rem ramene la colonne a **140,00 px** et fait
  **ressortir `mangeons` de 3,13 px par cote** - davantage que les -2,02 px que j'ai refuses le
  2026-08-11 - donc il est refuse ; la lecture "constat" ramene la colonne a 146,50 px pour **0,12 px**
  d'air, refusee ; seule la lecture "plancher" (bordure droite seule) laisse 150,50 px et **+2,12 px**.
  Il existe par ailleurs une route qui ne coute rien du tout a ce paquet, mesuree en section 2.c de
  l'arbitrage.
  **F6 - aucun item ni distracteur de ce paquet n'est remplace pour gagner de la largeur.** La
  "quatrieme voie" - descendre le mot le plus large a 136,00 px - est **refusee**, et son chiffre est
  incomplet : **trois** propositions depassent ce seuil (`mangeons` 146,27, `cherchons` 142,67,
  `cherchent` 139,03 px), dans **deux** items, et l'item de *chercher* est **impossible** a tenir sous
  136,00 px sans changer de verbe - il n'existe que deux formes du present de ce verbe sous le seuil
  (`cherches` 122,41, `cherchez` 123,58) pour trois distracteurs a fournir. Un remplacement se motive
  par le contenu, jamais par une largeur, et tout item touche repasse devant moi comme un contenu neuf
  sur ma barre de six points et les trois criteres du paquet. **Ma barre de six points n'est pas
  rouverte par cet arbitrage et reste levee.**
  **Reserve F, nouvelle et datee du 2026-08-12** : `mangeons` a 2,86 px d'air reste l'inconfort que
  j'ai enregistre le 2026-08-11 (*"le confort reel est de 2,86 px la ou ma definition en demande 20"*).
  La saisine sur la disposition de ce jeu doit etre instruite **pour elle-meme**, jamais pour financer
  une bordure.
- **Troisieme arbitrage prealable de forme du 2026-08-13, deux conditions mesurees sur ce paquet.**
  L'arbitrage complet est dans `FICHE-bandit-manchot-additions-cp.md` (bloc du 2026-08-13) ; ce n'est
  pas un Done pedagogique et il ne touche aucun verdict de cette fiche. Ce qui concerne ce paquet, et
  que je recopie ici parce que c'est ici qu'on viendra le chercher - les deux mesures ont ete faites
  sur l'item *"Vous ___ du chocolat."* de ce paquet, servi par les vraies routes :
  **F13 - le flanc droit n'offre que 20 px hors des colonnes, et ce paquet est celui qui le prouve.**
  A 360 px, sur le repli a quatre colonnes de ce jeu, entre le bord droit de la quatrieme proposition
  (`x = 339`) et le bord droit de l'ecran (`x = 359`) il y a **8 px de bordure + 12 px de fond de
  page**. Le levier propose par `ines-design` (variante E) fait 24 px de large : sa boule recouvre
  **34 px de la quatrieme proposition** (`x = 337..339`, `y = 479..492`, boite du bouton
  `x = 188..339`, `y = 444..514`) et cette bande de 3 x 14 px est **morte au toucher**, le levier
  etant un frere absolu place au-dessus sans `pointer-events: none`. Toute partie du levier plus large
  que 20 px recouvre necessairement une proposition, le corps du cabinet, ou sort de l'ecran. La meme
  variante replacee dans les 20 px recouvre **zero pixel** a l'interieur du cabinet, sur ce jeu comme
  sur un jeu de CP a trois colonnes, et garde sa lecture. **Aucune largeur de colonne n'est touchee** :
  contour du cabinet `12..347` / `317..538`, identique avec et sans levier, donc **F5 n'est pas
  rouverte et le plancher de 150,27 px n'est pas entame**.
  **F17 - le voile du rouleau a cesse d'aider ce paquet et a commence a lui couter.** Avec le texte
  sombre sur pastel accepte le meme jour, le mot d'une proposition passe de **9,55:1 au repos a
  6,07:1 sous le voile** (noir a 20 %), alors que sur le bouton bleu actuel le voile **augmentait** le
  contraste (5,41 vers 7,48:1). Le resultat reste au-dessus de 4,5:1 et **ce n'est donc pas un motif**,
  mais la marge n'est plus celle qu'elle etait : toute teinte plus claire ou tout voile plus opaque
  ferait passer un mot de ce paquet sous mon plancher sans qu'aucun test ne bouge.
  **Ma barre de six points n'est pas rouverte par cet arbitrage et reste levee**, et la reserve F du
  2026-08-12 sur les 2,86 px d'air de `mangeons` reste ouverte, inchangee.
- **Second verdict du 2026-08-13 sur le lot de cloture du sprint 10 (`643c6f4`)** : **REFUSE**,
  aucun Done pedagogique, **un seul motif bloquant** - **M13 clause 1 sur l'ecran de revelation de
  M5**. **Constate sur ce jeu** : 2 parties detruites sur 8 revelations. **M21 est TENUE** - plus
  aucun indice ne nomme la reponse. **M4 reste NON TENUE** sur les trois etats, et c'est le jeu le
  plus touche. Le verdict complet et l'etat de mes trente-huit conditions sont dans
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Second verdict du 2026-08-13". **Le
  refus du matin est corrige sur ses quatorze cas** et les clauses 2, 3, 4 et 5 de M13 sont tenues pour
  la premiere fois. **M4 ne bloque plus**, levee par `DEC-0025`, et mon constat n'est pas retire.
- **Correction de perimetre du 2026-08-14 (`camille-po`)** : le tableau "Competences visees"
  ci-dessous portait encore "sur le present uniquement pour ce jeu", restriction posee a
  l'ouverture de la ligne en s06 (`DEC-0013`) et perimee depuis S8-4 (extension au futur et a
  l'imparfait) et le troisieme audit du 2026-08-11 qui leve la barre en six points sur les 25
  items couvrant les trois temps. `nadia-prof` l'a signale dans son second verdict du sprint 10
  (`squad/sprints/s10-la-forme-du-bandit-manchot/review.md`) sans le corriger elle-meme, le
  tableau n'etant pas le sien. Corrige ici.
- **Derniere mise a jour** : 2026-08-14

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Orthographe et grammaire | Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif - etendu au futur et a l'imparfait par S8-4, present, futur et imparfait tous les trois couverts depuis le troisieme audit du 2026-08-11 | `squad/programme/francais/competences-cm1.md` |

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque manche presente une phrase a trou dont la bonne reponse est un verbe frequent
      conjugue au present de l'indicatif, jamais a un autre temps.
- [ ] La bonne reponse figure exactement une fois parmi les propositions ; chaque distracteur est
      une autre forme conjuguee du meme verbe au present (jamais un verbe different, jamais une
      forme d'un autre temps).
- [ ] Chacune des six personnes grammaticales (je, tu, il/elle, nous, vous, ils/elles) est
      representee par au moins un item du paquet.
- [ ] Le paquet demarre une partie sans erreur avec la mecanique bandit manchot existante et sans
      qu'aucune ligne de `domain/mechanic` ni `domain/mechanics` n'ait ete modifiee pour
      l'accueillir (`ADR-0007`, `CatalogueChargeableIT`).
- [ ] Sur 20 manches jouees a la suite, les phrases proposees ne sont pas toutes identiques.
- [ ] Tout texte destine a l'enfant (consigne, retours, indices s'il en existe) est redige en
      francais correct et integralement accentue, orthographe et ponctuation irreprochables
      (exigence de categorie B ajoutee par l'amendement du 2026-08-04 a `DEC-0002`).
- [ ] Les phrases sont d'un niveau de vocabulaire et de longueur adapte a un eleve de CM1 ; les
      verbes retenus sont effectivement "frequents" au sens du referentiel.
- [ ] L'accueil mentionne le niveau vise (reserve commune deja nommee sur les deux autres jeux de
      francais, `docs/backlog/backlog.md`, "Backlog - sprints suivants"), sauf si `camille-po` la
      traite ailleurs avant l'audit de ce jeu.

Voir `docs/backlog/backlog.md`, story S6-3, pour la formulation complete de ces criteres en
entree/sortie observables.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Reverification exigee par DEC-0026 (s12), du 2026-08-14 : VALIDE SOUS RESERVE

- **Verdict** : **VALIDE SOUS RESERVE**, sur le seul point que `DEC-0026` met en reverification -
  la clause 1 de M13 sur l'ecran de revelation et sur l'ecran de reprise. Ce verdict n'efface,
  ne modifie et ne rouvre aucun verdict anterieur de cette fiche : le REFUSE du 2026-08-13 reste
  ecrit, entier, et il portait sur un increment ou la partie etait detruite.
- **Le verdict complet, son protocole, ses chiffres et ses deux reserves sont dans**
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Reverification exigee par
  DEC-0026 (s12), du 2026-08-14". L'ecran de confirmation et la mecanique sont communs aux sept
  jeux : je ne recopie pas le raisonnement, j'ecris ici ce qui est propre a ce jeu.
- **Increment reverifie** : `round-two` a `ef801b4` (posterieur a `4eaddf0`, comme la tache
  l'exige), arbre propre, **jar reconstruit depuis mon propre worktree avant toute mesure**.
- **Ce que j'ai joue sur ce jeu precisement**, au clavier reel, Tabulation puis Entree depuis le
  point de focus :

| Mesure sur "Le bon verbe" | Resultat |
|---|---|
| revelations jouees, en tirages repetes | **9** |
| revelee en derniere position de l'ordre de tabulation, donc premiere Tabulation sur la sortie | **3 / 9** |
| **parties detruites** | **0 / 9** |
| clause 5 - le focus est sur la proposition qui porte la reponse revelee | **9 / 9** |
| Entree rend l'ecran de confirmation, avec le texte *"Si tu pars, ta partie s'arrête."* | **3 / 3** |
| ecran de reprise (`GET /` pendant une partie) : rien focalise au chargement, Tabulation sur la sortie, Entree sans destruction | **tenu** |
| manche fraiche, apres une reponse fausse, apres une reponse juste : Tabulation n'atteint jamais la sortie | **3 / 3** |

- **Ce qui est propre a ce jeu et que je note** : c'est le seul des sept a porter **quatre**
  propositions, donc le seul ou la revelee tombe en derniere position moins souvent (3 fois sur 9
  ici, contre 10 sur 10 sur "Calcul CP" a trois propositions). Cela ne change rien au verdict -
  la loi de s10 se reproduit a l'identique, et sa consequence a disparu sur les deux formats.
- **Les deux reserves du verdict complet valent aussi sur ce jeu**, parce qu'elles portent sur
  l'ecran de confirmation, qui est le meme : **reserve N** (aucun point de focus declare, premiere
  Tabulation instable) et **reserve M10 sur chemin neuf** (le retour depuis la confirmation rejoue
  le defilement des colonnes sur un tirage inchange). Aucune des deux n'empeche l'usage de
  l'increment.
- **Ce que ce verdict ne fait pas** : il ne fait passer aucune ligne de
  `squad/programme/MATRICE-couverture.md` a `couvert-valide`, il ne leve aucune autre reserve de ce
  jeu, et il n'ecrit aucune story.

### Second verdict du 2026-08-13 sur le lot de cloture du sprint 10 : REFUSE

- **Verdict** : **REFUSE.** Aucun Done pedagogique sur ce jeu.
- **Le verdict complet, ses mesures et l'etat de mes trente-huit conditions sont dans**
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Second verdict du 2026-08-13". Le
  meuble et la mecanique sont communs aux sept jeux du catalogue : je ne recopie pas le raisonnement,
  j'ecris ici ce qui est propre a ce jeu.
- **Increment audite** : `round-two` a `643c6f4`, jar **reconstruit depuis mon worktree** et correctif
  verifie dans le HTML servi. `mvn -B verify` : BUILD SUCCESS. Done technique acquis.
- **Mon motif de refus sur ce jeu, unique et bloquant - M13 clause 1 sur l'ecran de revelation** :
  - **Constate directement sur ce jeu** : sur **8 revelations** jouees, la reponse revelee a occupe la
    **derniere des quatre positions 2 fois**, et les deux fois **une seule Tabulation a atteint "Les
    jeux"** et Entree a **detruit la partie sans confirmation**. Les 6 autres fois, la tabulation est
    restee dans les propositions et la partie a survecu.
  - Ce jeu porte quatre propositions : le defaut y est donc moins frequent qu'ailleurs, pas absent.
- **M4 sur ce jeu, mesuree apres le resserrement de marges** (pire cas de 40 etats de message et de
  6 ecrans de revelation, 360 x 640, pli a `y = 640`) :
  - echec, pire cas : **703,19 px**, soit **63,19 px SOUS le pli** ;
  - ecran de revelation, pire cas : **703,19 px**, **63,19 px SOUS le pli** ;
  - **"Bravo !", pire cas : 675,19 px, soit 35,19 px SOUS le pli** - un enfant qui reussit sur les
    items a enonce long ne recoit toujours aucun retour visible.
  - **M4 reste NON TENUE sur ce jeu, sur les trois etats.** Elle etait a 891,19 px ce matin : le
    resserrement de marges et la disparition de l'indice (M21) lui rendent **188 px**, et il en manque
    encore 63. `DEC-0025` leve la condition en ecrivant ce que l'enfant y perd, jeu par jeu ; ce
    chiffre-ci est la mesure de ce qu'il perd apres le lot de cloture.
- **Ce qui est corrige sur ce jeu et que je credite** : apres une bonne comme apres une mauvaise
  reponse, Tabulation puis Entree laisse **la partie en cours** ; le focus va sur l'**enonce** de la
  question neuve apres une bonne reponse (clause 4) et sur la **proposition pressee, barree**, apres une
  mauvaise (clause 2) ; sur l'ecran de revelation il va sur la **proposition qui porte la reponse
  revelee** (clause 5). Les quatre clauses sont tenues sur ce jeu, mesurees au clavier.
- **M21 est TENUE sur ce jeu, et c'est le fait neuf le plus important pour lui.** Aucun indice n'est
  rendu : ni au premier echec, ni au deuxieme, ni sur l'ecran de revelation. La plainte de
  l'utilisatrice - la bonne reponse donnee des le premier echec - est **fermee**. Le perimetre est
  porte par une cle du paquet (`indicesNommentLaReponse`), fausse par defaut, et non par un
  identifiant de jeu compare en dur : c'est ce que j'avais exige.
- **Exactitude du contenu, verifiee item par item sur les 25** : enonces, bonnes reponses et 75
  distracteurs **justes**, accords de personne corrects, marqueurs temporels ("Demain", "Autrefois",
  "Maintenant") coherents avec le temps attendu, aucun distracteur absurde. La competence declaree,
  *"Conjuguer les verbes fréquents au présent, futur et imparfait de l'indicatif"*, est retrouvee mot
  pour mot dans `squad/programme/francais/competences-cm1.md`, et c'est bien celle-la que le jeu
  travaille. **Une maladresse de langue** : l'item *"Tu ___ un dessin."* attend `dessines`, donc "tu
  dessines un dessin".
- **Le prix de M21, que je nomme parce que personne ne le paiera a ma place** : un enfant de CM1 qui se
  trompe recoit desormais *"Ce n'est pas ça. Essaie encore !"* et **rien d'autre** ; a la revelation,
  *"C'est étions. Appuie sur étions."* et rien qui explique pourquoi. **Ce jeu n'enseigne plus rien sur
  l'erreur** : il sanctionne et il nomme. C'etait le moindre des deux maux, ce n'est pas un etat
  souhaitable, et **les 25 indices a reecrire sont desormais la seule chose qui separe ce jeu d'un jeu
  qui apprend quelque chose**.
- **M14 clause 5 NON TENUE, et ce jeu est le seul ou elle est observable** : quatre propositions, donc
  deux appuis a cote possibles apres la revelation. Mesure : marques `aimait` puis `aimait, aimeront`
  puis `aimait, aimais` - **`aimeront` perd sa marque**. La marque de M9 n'est pas cumulative apres la
  revelation, alors qu'elle l'est avant. Non bloquant, **reserve T** du verdict de reference.
- **Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge.**

### Verdict courant - chantier du bandit manchot (mecanique, forme, M10), du 2026-08-13 : REFUSE

- **Verdict** : **REFUSE.** Aucun Done pedagogique sur ce jeu.
- **Le verdict complet, ses mesures et mes trente-deux conditions une par une sont dans**
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`,
  section "Verdict courant - chantier du bandit manchot (mecanique, forme, M10) (...) du
  2026-08-13". Le meuble et la mecanique sont communs aux sept jeux du catalogue : je ne recopie pas
  le raisonnement, j'ecris ici ce qui est propre a ce jeu.
- **Increment audite** : `round-two` a `2d34a15`. Done technique acquis.
- **Mes deux motifs de refus, tous deux bloquants** :
  - **M4, bloquante, et c'est ici le pire cas du catalogue** : le bas du bloc de message est a
    **891,19 px**, soit **251,19 px sous le pli**, en echec avec indice ; **813,19 px** sur
    l'ecran de revelation (**173,19 px** dessous) ; et **le "Bravo !" lui-meme est a 659,19 px,
    soit 19,19 px sous le pli**. Sur ce jeu, **ni l'erreur ni la reussite ne produisent quoi
    que ce soit de visible**. La capture
    `squad/conformite/releves-s10/captures/le-bon-verbe-echec-1-message-hors-ecran.png` est
    l'ecran reel apres une mauvaise reponse : le seul changement visible est le mot barre.
    Cause visible : les quatre propositions sont en **deux rangees de deux** (152 x 71 px), ce
    qui porte le meuble a **222 px** de haut au lieu de 138.
  - **M2** : ce jeu **affiche** son indice sur la question courante, des le premier echec, et
    ses **25 indices sur 25 nomment la bonne reponse** - *"Au futur, avec 'nous', le verbe
    'faire' est irregulier : il devient 'ferons'."* Ma condition M2 du 2026-08-12 disait
    explicitement que ce jeu **ne peut pas afficher son indice** tant que les 47 textes de CM1
    ne sont pas reecrits (reserve A, non rouverte et non durcie). Il l'affiche. Consequence :
    **M5 est videe de son sens sur ce jeu**, puisque la reponse est donnee au premier echec au
    lieu du troisieme.
  - **M13 clause 1** : apres une bonne comme apres une mauvaise reponse, le focus reste sur
    `BODY`, **une seule Tabulation atteint le bouton "Les jeux"** (`data-role="sortie"`) et
    Entree **detruit la partie sans confirmation**. Verifie au clavier sur ce jeu. Cause :
    `recreo.js` est charge dans le `<head>` et leve
    `TypeError: Cannot read properties of null (reading 'addEventListener')`, donc l'ecouteur
    qui deplace le focus n'est **jamais enregistre** et `data-focus` est un attribut mort.
- **Ce qui est tenu et que je ferme sur ce jeu** : F16 (la marque "deja essayee" est un mot barre a
  **9,55:1 au minimum** sur les quatre teintes, contre un plancher de 3:1), F9, F10 y compris sa
  clause 3 que je n'avais pas pu fermer le 2026-08-13, F13 a F15 (le levier vit dans ses 20 px et
  n'intercepte aucun appui), F17, F18, F19, M6 (le meuble est identique au pixel dans les quatre
  etats), M8, M9, M10 et M12.
- **Condition neuve M14, datee du 2026-08-13** : apres la revelation, un appui sur une autre
  proposition doit la barrer comme n'importe quelle proposition deja essayee, sans compter d'essai,
  sans changer le message, sans mouvement et sans desactivation. Motif complet en section 6 du
  verdict de reference.
- **Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge.**

**Ce jeu porte donc trois motifs et non deux**, et c'est le plus touche du catalogue.


### Verdict courant - quatrieme audit, increment corrige de S8-5, du 2026-08-11

- **Verdict** : **VALIDE SOUS RESERVE.** Done pedagogique **accorde**. **Le REFUSE du 2026-08-11
  (troisieme audit) est LEVE** : son motif unique est ferme, mesure par moi sur le rendu servi. Le
  refus n'est pas efface et n'est pas retire, il reste lisible plus bas avec sa date et son motif -
  un refus efface n'a aucune valeur de preuve. Mes trois verdicts anterieurs restent egalement en
  place.
- **Date** : 2026-08-11.
- **Commit audite** : `bd9a8f0`, tete de `round-two` (fusion du lot `lea-dev`, S8-5).
- **Done technique** : acquis, et je ne le rediscute pas. `mvn -B clean verify` relance depuis mon
  propre worktree : **BUILD SUCCESS, 344 tests, 0 echec, 0 erreur**.
- **Pourquoi SOUS RESERVE et pas VALIDE sec** : la seule raison est le defaut de pli, chiffre en
  section 4. Ce n'est **pas** un motif - je l'ai moi-meme exclu, `DEC-0016` amende, `DEC-0017` et
  `DEC-0018` l'excluent aussi - mais je ne signe pas un VALIDE sec sur un ecran ou, sur **24 items
  sur 25**, l'arrivee de l'aide emporte la moitie des reponses sous le bas de l'ecran. Un VALIDE sec
  dirait que je n'ai rien trouve a corriger, et ce serait faux. Le Done est ouvert, la reserve est
  nommee.
- **Ligne de matrice** : la condition que `camille-po` a posee en section 4 de `DEC-0018` - "au
  premier verdict de `nadia-prof` sur l'increment corrige de `S8-5` qui n'est pas un `REFUSE`" - est
  **remplie**. La ligne `francais | CM1 | Orthographe et grammaire | Conjuguer les verbes frequents
  au present, futur et imparfait de l'indicatif` **doit passer de `en cours` a `couvert-valide`**,
  sans que ma barre de six points soit rouverte : elle est levee depuis le 2026-08-11 et je ne la
  reaudite pas. **Je n'edite pas cette ligne** : `squad/programme/MATRICE-couverture.md`, section
  "Qui la met a jour", nomme `camille-po` et personne d'autre, et je ne repriorise pas. Je le dis
  donc ici sous une forme qu'elle peut executer sans rien me redemander, et je nomme le risque :
  **si personne ne l'execute, c'est exactement le mode de defaillance de s04 que `camille-po` cite
  elle-meme dans `DEC-0018`** - une decision ecrite et jamais executee. La trace est ici pour que ce
  ne soit pas perdu.
- **Ce que ce Done ne dit pas** : que le jeu est confortable a 360 px. Il dit que la reponse ne sort
  plus de la chose qu'il faut presser, ce qui etait mon motif, et que les sept conditions de mon
  acceptation du matin sont tenues. Voir R1, en section 6, que je ne convertis pas en motif.

#### 0. Comment j'ai joue, et ce que je ne declare PAS degrade

**Audit joue, non degrade.** Le jeu est au catalogue et je l'ai atteint depuis l'accueil, comme un
enfant, sans branche jetable.

Worktree dedie `s08-nadia-verdict-2`, forke de `round-two` a `bd9a8f0`, lignee verifiee par
`outils/verifier-lignee.sh` ("le worktree sur la branche 's08-nadia-verdict-2' descend de la lignee
de round-two"). JDK constate par `outils/verifier-jdk.sh` (`java` du `PATH` en 1.8.0_202, JDK de
compilation 21.0.10, fichier de toolchains du poste present sous un nom que Maven ne lit pas),
`JAVA_HOME` pose sur le JDK 21 le temps des commandes Maven, fat jar reconstruit depuis ce worktree
et lance sur un port dedie.

- **22 parties completes** jouees par les vraies routes HTTP, ratees a chaque manche pour faire venir
  l'aide, jusqu'a l'ecran "C'est fini !" ;
- **220 etats d'ecran mesures**, **les 25 items vus**, et **les 25 vus aussi avec leur indice**,
  obtenu par des echecs consecutifs reels et non en lisant le YAML ;
- **880 propositions mesurees**, soit **les 100 emplacements de proposition du paquet** (97 chaines
  distinctes : `aidait`, `aidera` et `avaient` apparaissent chacune dans deux items). **Ce n'est plus
  une substitution de libelles comme dans mon arbitrage du matin** : chaque proposition a ete
  mesuree telle qu'un tirage reel l'a servie ;
- **Chrome reel, viewport 360 x 640 px exactement**, force par la surcharge de metriques du
  navigateur - qui n'est pas soumise au plancher de fenetre de `ADR-0025` -, verifie a chaque etat
  (`innerWidth` = 360, `innerHeight` = 640 sur les 220) ;
- geometrie relevee au centieme de pixel **sur les glyphes eux-memes** (boite du noeud de texte)
  contre la boite du bouton, jamais sur une valeur declaree ;
- captures a 360 x 640 des trois items ou le defaut se logeait, avec et sans indice, **et je les ai
  regardees**.

**Une limite, et une seule, que je nomme** : pas de doigt sur un ecran tactile, ni de lecteur
d'ecran. Je mesure ce que l'oeil recoit et ce que le clic produit, pas le geste.

**Un constat de jeu sans consequence, que je signale parce que je l'ai rencontre** : revenir sur `/`
avec une partie en cours restitue la partie plutot que le catalogue (le fragment porte
`data-arrivee="sans-geste"`, c'est donc un etat connu et marque) ; le bouton "Les jeux" de l'ecran de
jeu ramene bien au catalogue. Aucun enfant n'est bloque. Ce n'est ni une reserve ni un motif.

#### 0 bis. Ce que je corrige de mes propres constats, avant de m'appuyer dessus

**A. Ma "correction" B1 du matin etait fausse, et c'est mon chiffre de refus qui avait raison.**
Dans l'arbitrage du matin, section B1, j'ai corrige `mangeons` de **-2,02 px** a **-2,01 px** et
j'ai credite `ines-design` de la bonne valeur. **Je me suis corrigee a tort.** J'ai remis 2 rem sur
le seul selecteur du repli, sur le rendu reellement servi, et remesure les memes items : `mangeons`
mesure **156,02 px** de glyphes dans une boite de **152,00 px**, soit **-2,02 px** d'air de chaque
cote. Meme chose sur `cherchent` : mon refus disait **+1,84 px**, ma correction du matin disait
+1,85 px, et la mesure d'aujourd'hui donne **+1,84 px**. **Mes deux corrections du matin etaient
fausses dans le meme sens ; les chiffres de mon refus etaient exacts.** Cela ne change aucun motif
et aucune conclusion - c'est un centieme de pixel - mais je ne laisse pas un chiffre faux dans une
fiche de conformite, et je ne laisse surtout pas un credit mal attribue : sur ce point, personne
n'avait tort avant moi.

**B. Ce que ma section B2 du matin disait tient, verifie par la meme mesure.** A 2 rem, sur le rendu
reel : **deux** propositions sortent de leur boite (`mangeons` -2,02 px, `cherchons` -0,09 px) et
**trois** sont sous les 2,0 px d'air (`cherchent` +1,84 px). Trois propositions, deux items. C'etait
le bon perimetre.

**C. Ma condition 4 annoncait un minimum de 2,87 px ; le chiffre honnete est 2,86 px.** Les deux
cotes du mot ne sont pas symetriques au centieme : `mangeons` a **2,86 px** d'un cote et **2,87 px**
de l'autre. Je retiens **2,86 px**, le plus etroit des deux. Ma condition 4 exigeait "au moins
2,0 px", pas 2,87 : elle est tenue, et je le dis avant qu'on me le demande.

**D. Le point 1 de ma section E n'est plus a moi seule, et c'est une bonne nouvelle que je dois
enregistrer.** J'avais ecrit : *"la taille de police effective sous le repli a quatre colonnes.
Aucun test ne la lit."* **Un test la lit maintenant**, et je l'ai mis a l'epreuve plutot que de le
croire (section 2). Ce point sort de ma liste de garde.

#### 1. Les mesures de `lea-dev`, verifiees et non crues

Elle a declare sept mesures. Je les ai refaites, sans regarder les siennes pendant que je mesurais.
**Elles sont exactes.** Les seuls ecarts sont au centieme et ne changent rien :

| Ce qu'elle declare | Ce que je mesure | Ecart |
|---|---|---|
| minimum du paquet **2,86 px** sur `mangeons` | **2,86 px** | aucun |
| `cherchons` a **4,67 px** | **4,66 px** | 0,01 px |
| `cherchent` a **6,48 px** | **6,48 px** | aucun |
| **0** debordement, **0** sous le seuil | **0** et **0**, sur les 100 emplacements | aucun |
| boutons a **152,00 x 71,00 px** sous le repli | **152,00 x 71,00 px**, sur les 880 | aucun |
| `font-size: 1.875rem` litteral | litteral, ni variable ni `calc` | aucun |
| les trois autres jeux inchanges | inchanges, au pixel et a la police | aucun |

**Je releve ce qu'elle n'a pas fait et qu'elle aurait pu faire** : rien. Elle a mesure ce que ma
condition 4 demandait, sur le rendu, et elle a annonce le chiffre le plus defavorable pour elle
(2,86 et non 2,87). C'est la troisieme fois de ce sprint qu'un developpeur me rend une mesure que je
peux refaire et retrouver ; c'est ce qui rend un audit possible en une seule passe.

#### 2. Mes sept conditions, une par une

**Condition 1 - portee. SATISFAITE.** Mesure sur les quatre jeux du catalogue, joues chacun par
leurs vraies routes :

| Jeu | Propositions | `font-size` | Boite du bouton |
|---|---|---|---|
| Calcul CP | 3 | **32 px** | 96,00 x **74,00 px** |
| Le bon petit mot | 2 | **32 px** | 152,00 x **74,00 px** |
| Le bon son | 3 | **32 px** | 96,00 x **74,00 px** |
| **Le bon verbe** | **4** | **30 px** | 152,00 x **71,00 px** |

Aucun des trois autres ne bouge d'un pixel ni d'un point de police. Et ce n'est pas seulement un
constat : la seule modification fonctionnelle de tout le lot est **une declaration** placee dans un
selecteur qui exige une quatrieme `.proposition`. Aucun paquet du catalogue ne peut en produire
quatre - `cp-additions-jusqu-a-10` porte `nombreDePropositions: 3`, `cm1-homophones` un distracteur
par item, `cp-le-bon-son` deux - verifie dans les paquets, pas suppose.

**Condition 2 - 30 px est un plancher, pas le debut d'une pente. SATISFAITE, et mieux que ce que je
demandais.** Le contre-exemple neuf porte **exactement 1,8125 rem**, la valeur que j'avais nommement
mesuree et refusee, et non une valeur arbitraire. **Je ne me suis pas contentee de le lire** : j'ai
mis **1,8125 rem dans la feuille de production reellement servie** et relance la classe de tests.
Elle rougit, et sur le bon test :

```
laPoliceDuRepliAQuatreColonnesEstUneExceptionAuPlancherGeneralDeDeuxRem
[.propositions:has(.proposition:nth-child(4)) .proposition doit rester litteralement 1,875rem]
expected: 1.875
 but was: 1.8125
```

J'ai remis la feuille en etat ; rien de cette epreuve n'est commite. **Le garde-fou est une egalite
sur la production, pas seulement un plancher** : toute valeur autre que 1,875 rem rougit, vers le bas
comme vers le haut. C'est plus que ce que ma condition 2 exigeait, et c'est le bon choix.

**Condition 3 - la valeur est ecrite en clair. SATISFAITE, et gardee dans sa forme.**
`font-size: 1.875rem` litteral dans le bloc `:has()` deja existant, ni variable ni `calc`. **Et la
forme est gardee, pas seulement respectee** : j'ai mis `calc(2rem * 0.9375)` - qui calcule exactement
les memes 30 px et serait donc invisible a l'oeil - dans la feuille de production, et le test rougit
avec "`font-size` doit etre exprime en rem". C'est precisement le symptome que ma condition 3
nommait : un changement qui deplace la valeur sans que personne le voie. Il ne passe pas.

**Condition 4 - air strictement positif et au moins 2,0 px sur les 100 propositions. SATISFAITE.**
Mesure sur les 880 propositions servies, soit les 100 emplacements du paquet :

| | Resultat |
|---|---|
| propositions qui debordent de leur boite | **0 / 100** |
| propositions sous 2,0 px d'air | **0 / 100** |
| minimum du paquet | **2,86 px** sur `mangeons` (146,27 px de glyphes dans 152,00 px) |
| deuxieme plus etroit | **4,66 px** sur `cherchons` (142,67 px) |
| troisieme | **6,48 px** sur `cherchent` (139,03 px) |
| quatrieme | **9,03 px** sur `dessinent` (133,94 px) |

Et les six conditions de non-troncature du 2026-08-10, verifiees sur les 880 et non sur un
echantillon : **une seule ligne de texte** par proposition (880 / 880), `overflow: visible`,
`text-overflow: clip` jamais engage, `hyphens: manual`, `letter-spacing: normal`,
`font-stretch: 100%`, `writing-mode: horizontal-tb`, `transform: none`. **Rien n'est coupe, rien
n'est comprime, rien n'est coupe en deux, rien n'est couche.**

**Condition 5 - aucun second payeur. SATISFAITE.** Le cadre du cabinet est un
`border: 0.5rem solid` : cabinet mesure a **336,00 px** de large, interieur a **320,00 px**, soit
**8,00 px** de chaque cote, C1 intact. `gap` mesure a **16,00 px sur les deux axes** sur les quatre
jeux (acquis 4). Rembourrage calcule de `.proposition` : **12 px / 20 px**, soit 0,75 rem / 1,25 rem,
**identique sur les quatre jeux**, celui-ci compris (acquis 7). `min-height` a 44 px intact. **La
largeur n'a ete payee qu'une fois, sur la police, et sur ce seul repli.**

**Condition 6 - la cible tactile. SATISFAITE, et je peux enfin la chiffrer proprement.** Mesure
controlee sur le meme rendu, en remettant 2 rem sur le seul selecteur du repli :

| Etat | Boite du bouton | Hauteur du cabinet |
|---|---|---|
| 2 rem (avant S8-5) | 152,00 x **74,00 px** | **228,00 px** |
| 1,875 rem (livre) | 152,00 x **71,00 px** | **222,00 px** |

Les 6 px sortent **exactement** des boutons : 2 rangees x 3 px. C'est ce que j'avais decrit, et c'est
maintenant mesure comme un avant/apres et non deduit. **71 px reste tres au-dessus de mon acquis 1**
(44 px), qui demeure le plancher dur. **Aucune autre perte de hauteur ne l'accompagne** : je l'avais
pose comme clause, et la mesure la confirme.

**Condition 7 - l'exception doit etre lisible la ou on la cherchera. SATISFAITE.**
`leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul` n'a pas ete touche, et ses deux assertions
restent sur le selecteur litteral `.proposition` a 2 rem. `lea-dev` a juge qu'il lit bien ce
selecteur-la et qu'il reste vert a bon droit : **c'est la bonne lecture**, et c'est celle que j'avais
ecrite. Elle a ajoute un test distinct qui affirme **les deux planchers comme deux planchers
differents**, et qui nomme l'exception au mot ; la feuille porte la meme chose en commentaire, a la
regle. **Je n'ai decide ni le test ni sa forme - ce n'etait pas a moi** ; je juge le resultat, et le
symptome que je refusais est ferme aux deux endroits ou un lecteur regarde : la feuille et le test.

#### 3. Ce que l'enfant voit, parce que c'est la seule chose que je ne peux pas deleguer

J'ai regarde les captures a 360 x 640, pas seulement les nombres.

Sur **"Vous ___ du chocolat."**, `mangeons` est **entierement a l'interieur de son bouton**, avec un
filet de bleu clair visible de chaque cote. Son dernier `s` n'est plus sur la paroi bleu sombre du
cabinet. Dans la colonne de gauche comme dans celle de droite. **Le mot ne traverse plus la paroi de
la machine.** C'etait mon motif, mot pour mot, et il est ferme.

Sur **"Je ___ mon cahier dans mon sac."**, `cherchons` et `cherchent` sont tous les deux dans leur
boite, sur une ligne, entiers.

**Et la perte de taille ne se voit pas.** J'avais ecrit le matin qu'un enfant de CM1 lit `mangeons`
a 30 px exactement comme a 32 px, et que la difference ne se verrait pas. Je l'ai verifie sur le
rendu livre : je ne la vois pas. 30 px reste du gras blanc sur aplat plein, presque le double d'un
texte courant. **Entre les deux etats, l'enfant qui lit mieux est celui de 30 px**, et c'est le seul
motif de fond de mon acceptation.

#### 4. La reserve qui fait de ce Done un Done sous reserve : le pli

Mesure sur les 220 etats joues, pli a `y = 640` :

| Etat | Une rangee de reponses sous le pli | Items concernes |
|---|---|---|
| 66 etats **sans** indice | **0 / 66** | 0 |
| 154 etats **avec** indice | **144 / 154** | **24 / 25** |

**Les 6 px rendus par la reduction n'en ont rachete aucun.** Des que l'indice s'affiche - c'est-a-dire
apres trois echecs consecutifs, au moment ou l'enfant en a le plus besoin - deux des quatre reponses
passent sous le bas de l'ecran, sur 24 items sur 25. Je l'ai vu sur la capture, pas seulement dans
les nombres.

**Ce n'est pas un motif, et je ne le rouvre pas comme tel.** Je l'ai exclu de ma main au troisieme
audit, pour une raison qui n'a pas change : c'est le defaut de `DEC-0016`, il frappe aussi "Le bon
petit mot" dont le Done est accorde, et je ne peux pas appliquer deux barres a deux paquets pour un
seul defaut. `camille-po` a amende `DEC-0016` et ouvert une story commune aux deux jeux : c'est le
bon chemin, et il est ouvert.

**Mais c'est la raison pour laquelle mon verdict est SOUS RESERVE et non VALIDE sec.** Et je redis ce
que j'ai ecrit au refus, parce que rien ne l'a rendu faux : **si `camille-po` veut savoir ce qui gene
le plus l'enfant sur cet ecran, c'est cette ligne-la, pas mes 2 px.** Mes 2 px sont ce que je pouvais
refuser sans me contredire ; le pli est ce qui fait le plus de mal.

#### 5. Ce dont je reste seule garde apres ce verdict

Je le remets a jour, parce qu'une liste de garde perimee fait croire couvert ce qui ne l'est pas.

**Ce qui sort de ma liste** : la taille de police effective sous le repli a quatre colonnes. Un test
la lit desormais, en egalite, sur la feuille de production, et je l'ai mis a l'epreuve dans les deux
sens (valeur et forme). Je l'avais annoncee non gardable ; elle est gardee.

**Ce dont je reste seule garde, et personne ne doit croire le contraire :**

1. **L'air de chaque cote des glyphes dans la boite, sur les 25 items.** Aucun test ne mesure une
   largeur de glyphe rendu - `ADR-0025` l'a etabli. C'est le seul chiffre qui dit si le defaut est
   ferme, et c'est celui qui a mordu au troisieme audit. **Il mordra de nouveau** le jour ou une
   forme est ajoutee au paquet : a 2,86 px il n'y a plus de place, et le cadre est deja a son
   plancher. Toute forme nouvelle dans ce paquet revient devant moi, et c'est moi qui la mesure.
2. **Le rapport de taille entre la proposition et l'enonce tel que l'enfant le voit.** La forme
   declaree dit 2 rem >= 2 rem et reste verte a bon droit ; l'enfant voit **30 px contre 32 px** sur
   ce repli. J'ai accepte cette inversion le matin, en connaissance, et j'ai verifie sur la capture
   que les propositions restent de tres loin la chose la plus saillante de l'ecran. Mais **rien ne
   garde le rapport effectif**, et si un jour l'enonce grossit, personne ne le verra sauf moi.
3. **Le pli**, section 4. Aucun garde-fou de CI ne le mesure.

#### 6. Reserves - a convertir en stories par `camille-po`, non bloquantes

**Toutes les reserves ouvertes de mes verdicts anterieurs le restent**, et je ne les reformule pas.
Deux precisions, demandees et dues :

**R1 - "quatre colonnes de ce paquet ne sont pas confortables a 360 px, a aucune taille que
j'accepterais" : reste une reserve, et je refuse de la convertir en motif.** Ses chiffres tiennent
exactement, remesures aujourd'hui : le mot le plus large fait **146,27 px**, ma propre definition
d'une colonne lisible en demande 146,27 + 40 = **186,27 px** pour **152,00 px** disponibles, et l'air
reel est de **2,86 px** la ou ma definition en demande 20. **C'est un inconfort present, ce n'est pas
un defaut present** : le mot est dans sa boite, sur une ligne, en gras blanc de 30 px sur aplat
plein, et je l'ai regarde - un enfant de CM1 le lit. La transformer en motif un jour apres l'avoir
ecrite comme reserve, ce serait deplacer mes poteaux, et je ne le fais pas. Elle reste ce qu'elle
etait : le signe que la reponse durable est ailleurs - des formes plus courtes, ou une disposition
qui n'est pas un 2x2 a 360 px.

**R2 - "un paquet futur a quatre propositions heriterait de 30 px en silence" : reste un risque
futur, pas un defaut present.** Reverifie dans les paquets aujourd'hui : aucun autre paquet du
catalogue ne rend quatre propositions. Le jour ou l'un le fera, cela revient devant moi - et il
entrera dans ce selecteur sans qu'aucune ligne rougisse, avec des mots dont personne n'aura mesure
la largeur.

**Ce que je ne rouvre pas, et je le dis pour que ce soit ferme** : le defaut de pli comme motif
(section 4), la reserve de contraste cadre/colonnes a 1,71:1 (au backlog par `DEC-0018`, avec la
proposition chiffree d'`ines-design`), ma barre fermee de six points (levee le 2026-08-11, non
reauditee), et le verdict du cabinet (deja rendu, valide sous reserve). Aucun de ces quatre n'a pese
sur ce verdict, et aucun ne pourra m'etre oppose comme l'ayant pese.

### Arbitrage prealable au developpement - la police des propositions dans le repli a quatre colonnes (S8-5), du 2026-08-11

**Ce bloc n'est pas un Done pedagogique et il ne leve pas le refus qui suit.** `lea-dev` n'a rien
ecrit : il n'y a aucun increment a jouer, donc **aucun verdict a rendre**. Mon refus du meme jour
reste entier, avec son motif, jusqu'a ce que je joue l'increment corrige. Ce bloc repond a **une
seule question**, celle qu'`ines-design` me route dans `proposition-ines-largeur-du-cabinet.md`
section 7 : est-ce que j'accepte **1,875 rem (30 px) au lieu de 2 rem, pour `.proposition` et
seulement dans le repli a quatre colonnes** ?

**Reponse : OUI, SOUS CONDITION.** Les conditions sont en section C, elles sont sept, et elles sont
toutes verifiables sans me redemander ce que j'ai voulu dire.

**Ce que je ne traite pas ici**, parce que `DEC-0018` borne `S8-5` a la seule largeur : ma reserve de
contraste interieur du cabinet (1,71:1), et la nuance dediee qu'`ines-design` propose en section 6 de
sa proposition. Elle n'est pas abandonnee, elle est au backlog, et je la reprendrai la.

#### A. Comment j'ai mesure, et la limite de ma mesure

**Mesure, pas estimation - mais mesure d'un candidat, pas d'un livre.** Worktree dedie
`s08-nadia-arbitrage-largeur`, forke de `round-two` a `f958dbf`, lignee verifiee par
`outils/verifier-lignee.sh` ("le worktree sur la branche 's08-nadia-arbitrage-largeur' descend de la
lignee de round-two"). JDK constate par `outils/verifier-jdk.sh` (`java` du `PATH` en 1.8.0_202, JDK
de compilation 21.0.10), `JAVA_HOME` pose sur le JDK 21 le temps du `mvn -B -DskipTests clean
package`, fat jar lance sur un port dedie. J'ai **joue** le jeu par ses vraies routes jusqu'a tirer
l'item en cause ("Vous ___ du chocolat.", bonne reponse `mangez`, distracteurs `mange`, `mangeons`,
`mangent`), et c'est ce fragment servi que j'ai mesure, dans la vraie structure de page et sous les
deux feuilles reellement servies, a 360 px de largeur CSS exacte et 640 px de hauteur, en Chrome
reel. Glyphes par `Range.getBoundingClientRect()` sur le noeud de texte, boite par
`getBoundingClientRect()` sur le bouton.

**Trois limites, que je nomme plutot que de les laisser deviner :**

1. **je n'ai pas joue l'increment**, il n'existe pas. J'ai mesure une **copie** de la feuille servie
   a laquelle j'ai ajoute la regle proposee, dans un dossier de travail hors du depot, jamais
   commitee. Ce bloc est donc **degrade** au sens de ma methode, et il le reste par nature ;
2. **pas d'echange htmx, pas de clavier, pas d'animation** : les scripts sont retires de la page
   mesuree, donc je mesure l'**etat de repos**. C'est l'etat que l'enfant lit, mais ce n'est pas tout
   ce qu'il traverse ;
3. **le balayage des 25 items est une substitution, pas 25 tirages reels** : j'ai remplace les quatre
   libelles dans la structure reellement rendue, item apres item. La geometrie mesuree est la vraie ;
   le tirage, non. Je le dis parce que c'est exactement le genre de raccourci que je reproche aux
   autres.

#### B. Ce que je corrige de mes propres constats, avant de m'appuyer dessus

**B1. Mon chiffre sur `mangeons` etait faux d'un centieme.** Mon refus du meme jour ecrit
**-2,02 px** d'air de chaque cote. Remesure aujourd'hui, meme methode, meme item, meme largeur :
**-2,01 px**. `ines-design` avait reproduit -2,01, et c'est elle qui a raison. Sur `cherchons` mon
chiffre tient : **-0,09 px** (152,17 px de glyphes dans 152,00 px). Ce n'est pas un chiffre qui change
mon motif, c'est un chiffre que je ne veux pas laisser faux dans une fiche de conformite.

**B2. Mon refus sous-estimait la surface du defaut, et c'est plus important que B1.** J'y nommais
**deux** propositions. J'ai balaye aujourd'hui **les 100 propositions des 25 items** du paquet, et il
y en a **trois** sous les 2,0 px d'air que j'ai moi-meme au dossier comme la marge la plus etroite
jamais acceptee dans ce depot :

| Proposition | Item | Air de chaque cote a 2 rem | Etat |
|---|---|---|---|
| `mangeons` | 3 | **-2,01 px** | sort de sa boite |
| `cherchons` | 7 | **-0,09 px** | sort de sa boite |
| `cherchent` | 7 | **+1,85 px** | dans sa boite, mais sous ma propre marge de reference |

`cherchent` ne deborde pas : mon motif de refus, qui portait sur un mot **hors** de sa boite, n'en est
pas change. Mais le defaut porte sur **trois propositions et deux items**, pas sur deux propositions,
et je le corrige avant de m'appuyer sur mon propre constat.

#### C. Ma reponse, et les sept conditions

**J'accepte 1,875 rem (30 px). Motif, et il tient en une phrase : ce n'est pas une question de
lisibilite, c'est un chiffre que je garderais contre sa propre raison.**

**Le fond, d'abord, parce que c'est la seule chose que je peux juger et que personne ne peut juger a
ma place.** Un enfant de CM1 lit `mangeons` a 30 px exactement comme il le lit a 32 px. J'ai regarde
les deux captures a 360 x 640, cote a cote, et la difference de taille ne se voit pas ; ce qui se voit,
c'est qu'a 32 px le `m` et le `s` de `mangeons` sont **ecrits sur la paroi sombre du cabinet**, et
qu'a 30 px le mot est entier a l'interieur de son bouton avec un filet de bleu visible des deux
cotes. Entre les deux etats, l'enfant qui lit mieux est celui de 30 px. 30 px reste presque le double
d'un texte courant, en gras, blanc sur aplat plein.

**La raison pour laquelle je ne tiens pas mon chiffre.** Mon plancher de 2 rem est la condition 3 des
six que j'ai ecrites le 2026-08-10, et je l'ai ecrite ainsi : *"aucune reduction de `font-size` de
`.proposition` sous 2 rem **pour faire tenir une mise en page**"*. Elle existe pour proteger les
conditions 1 et 2 de la meme liste - le mot entier, sur une ligne, ni tronque ni coupe ni comprime,
**dans** sa boite. Aujourd'hui la lettre de ma condition 3 est tenue et mes conditions 1 et 2 sont
rompues. La piste retenue inverse exactement cela : elle coute 6,25 % d'un nombre et rachete la chose
pour laquelle ce nombre existait. **Garder 32 px, ce serait garder ma figure et perdre ma raison**, et
ce n'est pas ce que j'appelle tenir une barre.

**Et je nomme la brèche qu'`ines-design` n'a pas chiffree, parce qu'elle est double.** Mon acquis 3
n'est pas un chiffre, c'est **deux clauses** : `.proposition` >= 2 rem, **et**
`.proposition` >= `.enonce`. `ines-design` a chiffre la premiere (6,25 %). J'ai mesure la seconde : `.enonce` est a
**32 px** et le reste, donc a 30 px **la reponse devient plus petite que la question** sur ce repli.
**Les deux clauses cedent, pas une.** J'accepte aussi la seconde, et voici pourquoi : cette clause
existe pour que la reponse ne soit pas visuellement subordonnee a l'enonce (ma reserve 1 sur S2-1,
"le nombre doit peser plus que la barre qui le porte"). Ce qui porte ce poids n'est pas 2 px de
glyphe : c'est l'aplat bleu plein, le gras, les 20 px de rembourrage et les 71 px de cible, contre du
texte noir sur le fond de page. Sur ma capture a 30 px, les quatre propositions restent de tres loin
la chose la plus saillante de l'ecran. **Cette clause perd sa lettre et garde son objet.** Je l'ecris
ici, date, pour que personne ne le decouvre au second verdict.

**Les sept conditions de mon acceptation.** Elles sont fermees : ce qui n'y est pas ne bloquera pas
le Done et ne pourra pas m'etre oppose comme l'ayant bloque.

1. **Portee.** La reduction ne vit que sous `.propositions:has(.proposition:nth-child(4))
   .proposition`, c'est-a-dire **le seul repli a quatre colonnes**. `.enonce`, `.consigne`,
   `.indice`, `.explication`, les autres boutons, et `.proposition` dans les cas a deux et a trois
   colonnes restent a leur taille d'aujourd'hui. **Verifie par moi dans le contenu, pas suppose** :
   "Le bon petit mot" rend 2 propositions par item, "Le bon son" 3, "Calcul CP" 3
   (`nombreDePropositions: 3`) - aucun autre paquet du catalogue ne peut entrer dans ce selecteur, et
   mes mesures sur les trois ne bougent d'aucun pixel.
2. **30 px est un plancher, pas le debut d'une pente.** J'accepte **1,875 rem** et rien en dessous.
   J'ai mesure 1,8125 rem (29 px) : il donne plus d'air, il n'est **pas** approuve. Toute valeur sous
   30 px revient devant moi.
3. **La valeur est ecrite en clair.** `font-size: 1.875rem` litteral dans la regle, pas derriere une
   variable ni un `calc` qu'un changement ulterieur deplacerait sans que personne le voie. C'est la
   forme, pas l'architecture : si ce n'est pas realisable ainsi, c'est `theo-archi` qui tranche le
   moyen, et je juge le resultat.
4. **Ce que la reduction doit acheter, chiffre.** A 360 px de largeur reelle, **les 100 propositions
   des 25 items** du paquet ont un air **strictement positif de chaque cote de leur boite, et au
   moins 2,0 px** - les 2,0 px etant la marge la plus etroite jamais acceptee dans ce depot, mon
   propre chiffre au dossier et non une barre neuve. Mesure a 30 px : **zero** proposition qui
   deborde, **zero** sous 2 px, minimum du paquet **2,87 px** sur `mangeons`. La condition est donc
   deja satisfaite par la piste telle que chiffree ; je l'ecris pour que le second verdict ait un
   critere et pas une impression.
5. **Ce qui ne doit surtout pas payer une seconde fois.** Le cadre reste a **0,5 rem** (C1 intact),
   le `gap` reste a **1 rem sur les deux axes** (acquis 4), le rembourrage declare de `.proposition`
   reste **0,75 rem / 1,25 rem** (acquis 7), et les six conditions de non-troncature du 2026-08-10
   restent entieres : une ligne, aucune troncature, aucun `overflow: hidden` sur le texte, aucune
   compression de glyphe, aucune cesure, aucun texte vertical. **Si la largeur est payee une seconde
   fois sur l'un de ces postes, mon acceptation ne vaut plus.**
6. **La cible tactile, que personne n'a nommee et que j'ai mesuree.** La baisse de 6 px de hauteur du
   cabinet ne vient pas du cabinet : elle sort **des boutons eux-memes**, dont la boite passe de
   **152,00 x 74,00 px a 152,00 x 71,00 px**, deux rangees, soit les 6 px. C'est un effet a connaitre
   et non un cadeau. **Je l'accepte** : 71 px reste tres au-dessus de mon acquis 1 (2,75 rem, 44 px),
   qui demeure le plancher dur. Aucune autre perte de hauteur du bouton ne l'accompagne.
7. **L'exception doit etre lisible la ou on la cherchera.**
   `leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul` lit la taille declaree du selecteur litteral
   `.proposition` : ses **deux** assertions resteront vertes alors que l'enfant verra 30 px sous le
   repli, et donc moins que `.enonce`. `ines-design` a raison de le remonter, et **je ne decide ni le
   test ni la forme de sa trace** - c'est `theo-archi`. Ce que je pose, c'est le symptome que je
   refuse : qu'un lecteur de la feuille ou du test croie 2 rem encore garde dans tous les cas. **Mon
   acceptation couvre le repli a quatre colonnes de ce paquet ; elle n'est pas une licence generale
   d'ecrire 1,875 rem ailleurs.**

**Ce que je n'autorise pas, et qui aurait pu passer pour une facon elegante de reparer la seconde
clause :** faire descendre `.enonce` a 30 px pour retablir le rapport. `DEC-0018` borne `S8-5` a la
largeur, l'enonce est ce que l'enfant lit sur les quatre jeux, et sa taille est aussi la cause du
defaut de pli que `DEC-0016` regit. Si quelqu'un veut retablir le rapport par ce chemin, c'est une
question neuve et elle revient devant moi.

#### D. Deux reserves neuves, datees du 2026-08-11, qui ne sont pas un durcissement

Elles ne bloquent rien, elles ne sont pas dans `S8-5`, et elles ne relevent ni ne durcissent une
barre anterieure. Ce sont des **motifs pour `camille-po`**, qui en fait des stories si elle le juge.

**R1 - a 360 px, quatre colonnes de ce paquet ne sont pas confortables, a aucune taille que
j'accepterais.** Ma propre definition d'une colonne lisible est ecrite : le mot le plus large **plus**
le rembourrage declare de 1,25 rem de chaque cote, soit 40 px. Sur ce paquet a 30 px, cela vaut
`146,27 + 40 = 186,27 px`, pour **152,00 px** disponibles - et 152 px est le maximum atteignable, le
cadre etant deja a son plancher. L'air reel autour du mot le plus large sera de **2,87 px** la ou ma
definition en demande 20. **La piste retenue met fin a un defaut ; elle ne rend pas la colonne
confortable, et je ne veux pas qu'on lise mon oui comme si elle le faisait.** La reponse durable est
ailleurs - des formes plus courtes dans le paquet, ou une disposition qui n'est pas un 2x2 a 360 px -
et elle n'est pas dans `S8-5`. J'ecris ceci comme une reserve neuve et datee, pas comme un
relevement : `S8-5` reste borne a ce que `DEC-0018` a borne, et je le jugerai sur les conditions de
la section C, sur rien d'autre.

**R2 - un paquet futur a quatre propositions heriterait de 30 px en silence.** `nombreDePropositions`
est un parametre de contenu : le jour ou un paquet neuf en demande 4, ou qu'un item porte trois
distracteurs, il entre dans ce selecteur sans qu'aucune ligne rougisse, avec des mots dont personne
n'a mesure la largeur. Aucun paquet du catalogue ne le fait aujourd'hui, je l'ai verifie. **Le jour
ou l'un le fera, cela revient devant moi.**

#### E. Ce dont je reste seule garde apres cet arbitrage

Trois choses, et je les nomme pour qu'on ne croie pas couvert ce qui ne l'est pas - c'est un chantier
entierement visuel, et quatre defauts visibles par un enfant ont deja traverse une CI verte sur ce
jeu :

1. **la taille de police effective sous le repli a quatre colonnes.** Aucun test ne la lit ; celui qui
   existe restera vert a 1,875 rem (condition 7) ;
2. **l'air de chaque cote des glyphes dans la boite, sur les 25 items.** Aucun test ne le mesure.
   C'est le seul chiffre qui dit si le defaut est reellement ferme, et c'est ma condition 4 ;
3. **le rapport de taille entre la proposition et l'enonce tel que l'enfant le voit**, maintenant que
   sa forme declaree et sa forme effective divergent sur un cas.

Et rien de ce bloc n'accorde de Done : je jouerai l'increment corrige, par les vraies routes, avant
d'ecrire quoi que ce soit d'autre.

### Verdict courant - troisieme audit, chantier de la conjugaison etendue (S8-4 et activation au catalogue), du 2026-08-11

- **Verdict** : **REFUSE.** Done pedagogique **non accorde** sur cet increment. Le refus porte sur
  **l'activation de "Le bon verbe" au catalogue**, pas sur le contenu des treize items neufs, que je
  valide et dont je dis le bien en section 6. Mes deux verdicts anterieurs ne sont pas effaces et ne
  sont pas retires : ils restent lisibles plus bas avec leurs dates.
- **Date** : 2026-08-11.
- **Commit audite** : `533967d`, tete de `round-two`.
- **Done technique** : acquis, et je ne le rediscute pas. Un increment techniquement irreprochable
  peut etre refuse pedagogiquement, et c'est le cas ici.
- **Ma barre fermee de six points : LEVEE.** Les six points que j'avais ecrits moi-meme au refus du
  2026-08-10, section 5, sont satisfaits, je les verifie un par un en section 1, et j'avais ecrit
  *"Rien d'autre. Ce qui n'est pas dans cette liste ne bloquera pas `couvert-valide` et ne pourra pas
  m'etre oppose comme l'ayant bloque."* Je m'y tiens.
- **Ligne de matrice** : la ligne `francais | CM1 | Orthographe et grammaire | Conjuguer les verbes
  frequents au present, futur et imparfait de l'indicatif` **peut passer a `couvert-valide`**, parce
  que ma barre est levee et que c'est elle seule qui l'autorisait. Je n'edite pas cette ligne, elle
  appartient a `camille-po`. **Et je dis nettement ce que cela veut dire et ce que cela ne veut pas
  dire** : la competence est couverte et le contenu est valide ; le jeu, tel qu'il est servi
  aujourd'hui, est refuse. Ce ne sont pas deux jugements contradictoires, ce sont deux objets
  differents - le premier porte sur ce que le paquet enseigne, le second sur ce que l'enfant voit a
  l'ecran. **Garder ou non un jeu refuse au catalogue pendant la correction est la decision de
  `camille-po`**, pas la mienne : je ne repriorise pas et je n'ecris pas le backlog.

#### 0. Comment j'ai joue, et ce que je ne declare PAS degrade

**Audit joue, non degrade.** Ce jeu est desormais **au catalogue** : contrairement a mon audit de s07,
je n'ai eu besoin d'aucune branche jetable pour l'atteindre. C'est precisement ce qui change la nature
de ce que je juge - un enfant peut y arriver depuis l'accueil.

Meme installation que le verdict transverse du meme jour (`FICHE-bandit-manchot-additions-cp.md`,
section 0) : worktree dedie sur `s08-nadia-verdicts`, lignee verifiee par `outils/verifier-lignee.sh`,
JDK constate par `outils/verifier-jdk.sh`, fat jar reconstruit et lance sur un port dedie. Pour **ce
jeu** :

- **12 parties completes** jouees par les vraies routes de l'application, ratees a chaque manche pour
  faire venir l'aide, plus **24 parties** supplementaires (12 en repondant toujours juste, 12 toujours
  faux) pour verifier le tirage ;
- **les 25 items vus**, et **chacun des 25 vu aussi avec son indice**, obtenu par trois echecs
  consecutifs reels et non en lisant le YAML. 33 etats d'ecran distincts mesures ;
- geometrie relevee au centieme de pixel a **360 px de largeur reelle et 640 px de hauteur reelle**,
  sur les glyphes eux-memes et non sur des valeurs declarees ;
- capture a 360 x 640 par la commande versionnee du depot, et **je l'ai regardee**.

#### 0 bis. Ce que je corrige de mes propres constats, avant de m'appuyer dessus

**A. J'ai failli corriger a tort ma reserve 3, et je dis ce que j'ai verifie.** Ma reserve 3 disait
*"Douze items pour dix manches : une partie montre dix items sur douze."* En jouant 15 parties, j'ai
vu un item repete dans 7 d'entre elles et j'ai d'abord conclu que le tirage etait avec remise, ce qui
aurait fait tomber ma reserve. **J'ai verifie avant d'ecrire, et ma premiere conclusion etait
fausse** : le tirage est sans remise (`ListContentSource.next` ecarte les items deja tires), et 24
parties jouees sans jamais varier ne contiennent aucune repetition. Les repetitions venaient de la
**"manche plus simple" de `DEC-0004`**, qui reserve un item **deja reussi** apres trois echecs
consecutifs - un comportement valide. **Ma reserve tient donc, avec la nuance que je n'y avais pas
mise** : le paquet passe de 12 a 25 items pour dix manches, ce qui l'ameliore nettement, et la
repetition qui reste est celle de l'aide, pas celle du tirage.

**B. Ma reserve 1 de largeur etait chiffree contre 160 px, et la colonne n'en fait plus 160.** J'avais
ecrit : *"tout mot plus large que 156 px sortira de sa colonne"*, sur une colonne de 160 px. Le
cabinet, que j'ai autorise le meme jour, ramene la colonne a **152 px**. Mon seuil de 156 px n'est
donc plus le bon seuil : il est desormais de **152 px**, et deux mots du paquet le depassent. Je le
dis avant de m'appuyer dessus, parce que le chiffre a bouge sous ma propre decision.

**C. `marc-dev` annonce des marges superieures ou egales a 13 px, et je mesure 11,95 px.** Sur les
formes neuves, la plus large est `faisaient` a **128,09 px** de glyphes, qui laisse **11,95 px** d'air
de chaque cote dans une colonne de 152 px. Son chiffre a ete pris sur une colonne de 160 px (ou l'air
serait de 15,95 px), avant que le cabinet n'existe. **Ce n'est pas une faute de sa part** : les deux
lots ont ete developpes en parallele et sa mesure etait exacte sur l'etat qu'il avait sous les yeux.
Je corrige le chiffre, pas l'intention. **Aucune forme neuve ne sort de sa boite** : les 13 items de
S8-4 sont tous a l'interieur, la plus etroite marge etant ces 11,95 px.

#### 1. Ma barre fermee, les six points un par un

Les six points sont ceux de mon refus du 2026-08-10, section 5, cites au mot. Je ne les releve pas et
je ne les assouplis pas.

**Point 1 - "le futur de l'indicatif et l'imparfait de l'indicatif sont ajoutes, avec les six
personnes chacun, sur des verbes frequents des trois groupes comme le present les a deja". SATISFAIT.**

| Temps | Six personnes ? | Verbes, et leurs groupes |
|---|---|---|
| futur | **oui** : `jouerai` (je), `aideras` (tu), `finira` (elle), `ferons` (nous), `irez` (vous), `auront` (ils) | jouer et aider (1er), **finir (2e)**, faire, aller et avoir (3e) |
| imparfait | **oui** : `jouais` (je), `aidais` (tu), `faisait` (il), `étions` (nous), `aviez` (vous), `aimaient` (ils) | jouer, aider et aimer (1er), faire, être et avoir (3e) - **aucun 2e groupe** |

Les six personnes y sont pour chacun des deux temps, ce qui etait la seule chose que le mot "chacun"
distribuait dans ma phrase. Les trois groupes sont presents sur l'ensemble ajoute. Les verbes sont
frequents, tous du vocabulaire d'un enfant de CM1.

#### 2. Ma reponse au point d'interpretation que `marc-dev` me soumet

Il a satisfait mon point 1 sur l'ensemble futur+imparfait et non temps par temps : le 2e groupe
n'apparait qu'au futur (`finir`), volontairement absent de l'imparfait pour eviter les formes en
`-iss-` dont j'avais moi-meme nomme la largeur a risque. Il ne tranche pas et me laisse lire ma barre.
**Je la lis, et ma reponse est : oui, son interpretation est la bonne, et ce n'est pas une faveur.**

Trois raisons, et je les donne dans l'ordre de leur force :

1. **Ma phrase place "chacun" sur les six personnes, et sur rien d'autre.** J'ai ecrit "avec les six
   personnes **chacun**, sur des verbes frequents des trois groupes" : le distributif porte sur les
   personnes, la clause des groupes n'en porte aucun. Si j'avais voulu les trois groupes par temps, je
   devais l'ecrire, et je ne l'ai pas ecrit. **Le lire aujourd'hui comme une exigence par temps serait
   deplacer mes poteaux apres coup**, ce que je m'interdis.
2. **Ma propre clause de reference le dit encore plus clairement : "comme le present les a deja".**
   J'ai verifie ce que le present a deja, et il faut le regarder en face : sur ses douze items, le 2e
   groupe est porte par **un seul verbe**, `finir` (item 8). **L'etalon que j'ai designe moi-meme est
   un temps ou le 2e groupe tient sur un item.** Le futur en a exactement autant. Exiger davantage de
   l'imparfait que de l'etalon que j'ai nomme serait incoherent.
3. **Et son motif de fond est juste, ma mesure d'aujourd'hui le prouve.** J'avais nomme
   `finissaient` et `apprendraient` comme les formes qui feraient tomber la mise en page. La colonne
   fait aujourd'hui 152 px et `mangeons`, a 156,02 px, en sort deja. `finissaient` compte deux
   caracteres de plus que `faisaient`, qui mesure 128,09 px : il aurait tres probablement franchi la
   paroi comme `mangeons`. **Lui refuser sa lecture, c'etait lui demander d'ecrire le mot dont
   j'avais moi-meme dit qu'il casserait l'ecran.** Il a evite le piege que j'avais signale, et il a
   eu raison.

**Ce que je fais du fond pedagogique, puisqu'il existe et que je ne le nie pas.** L'imparfait des
verbes du 2e groupe est le seul endroit ou apparait l'infixe `-iss-` (`nous finissions`, `ils
finissaient`), et c'est une vraie difficulte de CM1 - la plus vraie du chapitre, meme. Un paquet
d'imparfait sans un seul 2e groupe laisse cette difficulte entierement de cote. **Ce n'est pas un
motif, parce que ma barre ne le demandait pas ; c'est une reserve nouvelle et datee du 2026-08-11**,
reserve 1 de la section 8, ouverte sur ma propre redaction et non sur le travail de `marc-dev`. Elle
ne pourra pas etre presentee comme ayant bloque quoi que ce soit.

#### 3. Points 2 et 3 : l'opposition des temps et la marque dans la phrase

**Point 2 - "au moins un item par temps oppose deux temps entre eux". SATISFAIT, et depasse.**

| Temps | Item | Bonne reponse | Distracteur d'un autre temps |
|---|---|---|---|
| futur | "Demain, ils ___ une récréation plus longue." | `auront` | **`avaient`** (imparfait) |
| imparfait | "Autrefois, ils ___ ce dessin animé chaque mercredi." | `aimaient` | **`aimeront`** (futur) |
| present | "Maintenant, elle ___ son frère avant l'école." | `aide` | **`aidait`** (imparfait) **et `aidera`** (futur) |

Le troisieme ne m'etait pas du : ma barre demandait le futur et l'imparfait, et un treizieme item de
present a ete ajoute pour que le temps deja couvert soit lui aussi mis en concurrence. C'est plus que
ce que je demandais, et c'est le bon geste : *"un enfant qui n'a jamais eu a choisir entre deux temps
n'a jamais eu a identifier le temps demande"*, et cela valait pour le present comme pour les autres.

**Point 3 - "la marque du temps est reperable dans la phrase plutot que devinable". SATISFAIT.** Les
six items de futur ouvrent sur `Demain,`, les six d'imparfait sur `Autrefois,`, le treizieme de
present sur `Maintenant,`. La marque est un complement de temps explicite, en tete de phrase, lu avant
le sujet. Sur les trois items ou le temps doit reellement etre choisi, c'est elle et elle seule qui
tranche - et elle est la.

#### 4. Point 4 : les vingt-cinq items relus un par un, par moi

J'avais ecrit *"j'auditerai ces items un par un"*. `marc-dev` l'a fait a la main de son cote parce que
ce n'est pas automatisable ; j'ai refait la lecture moi-meme, sur les vingt-cinq items, et je l'ai vue
en jeu. **Aucun item n'admet deux lectures correctes.**

| Item neuf | Bonne reponse | Ce qui exclut chaque distracteur | Double lecture ? |
|---|---|---|---|
| "Demain, je ___ dans le jardin." | `jouerai` | `joueras`, `jouera`, `joueront` : personne | non |
| "Demain, tu ___ tes camarades en classe." | `aideras` | `aiderai`, `aidera`, `aideront` : personne | non |
| "Demain, elle ___ son dessin avant le goûter." | `finira` | `finirai`, `finirons`, `finiront` : personne | non |
| "Demain, nous ___ un gâteau pour la fête." | `ferons` | `ferai`, `fera`, `feront` : personne | non |
| "Demain, vous ___ à la piscine avec la classe." | `irez` | `irai`, `ira`, `iront` : personne | non |
| "Demain, ils ___ une récréation plus longue." | `auront` | `aurai`, `aura` : personne ; **`avaient` : temps**, exclu par `Demain` | non |
| "Autrefois, je ___ tous les jours dans le jardin." | `jouais` | `jouait`, `jouions`, `jouaient` : personne | non |
| "Autrefois, tu ___ tes camarades en classe." | `aidais` | `aidait`, `aidions`, `aidaient` : personne | non |
| "Autrefois, il ___ du vélo le dimanche." | `faisait` | `faisais`, `faisions`, `faisaient` : personne | non |
| "Autrefois, nous ___ toujours les premiers à l'école." | `étions` | `étais`, `était`, `étaient` : personne | non |
| "Autrefois, vous ___ moins de devoirs." | `aviez` | `avais`, `avait`, `avaient` : personne | non |
| "Autrefois, ils ___ ce dessin animé chaque mercredi." | `aimaient` | `aimais`, `aimait` : personne ; **`aimeront` : temps**, exclu par `Autrefois` | non |
| "Maintenant, elle ___ son frère avant l'école." | `aide` | `aidez` : personne ; **`aidait` et `aidera` : temps**, exclus par `Maintenant` | non |

**Les deux items sur lesquels je me suis arretee le plus longtemps, parce que c'est la que le risque
que j'avais annonce se logeait.**

1. **"Demain, ils ___ une récréation plus longue." avec `avaient` en distracteur.** `avaient`
   s'accorde avec `ils` en personne et en nombre : le seul discriminant est `Demain`. C'est
   exactement ce que mon point 2 demandait, et c'est aussi le cas ou une seconde lecture pourrait se
   glisser. J'ai cherche celle-la : *"Demain, ils avaient une récréation plus longue"* existe en
   francais litteraire, en discours indirect libre, dans un recit au passe. **Ce n'est pas disponible
   au CM1 et ce n'est pas la norme scolaire** : je ne la compte pas comme une lecture correcte. Le
   risque que j'avais annonce - "hier" avec un present de narration - **n'a pas ete pris** : aucun
   item ne melange un complement de temps avec un temps qui ne lui correspond pas.
2. **"Maintenant, elle ___ son frère avant l'école."** Trois temps sont en concurrence sur un seul
   item, et il faut donc que `Maintenant` exclue nettement les deux autres. Il les exclut.

**Les distracteurs sont tous plausibles, et c'est une qualite que je releve.** Sur les vingt-cinq
items, chaque distracteur est une forme **reelle du meme verbe**. Aucun n'est absurde, donc aucun item
ne se resout par elimination du ridicule. C'est la difference entre un exercice et une devinette, et
elle est tenue.

**Et une reussite de conception que je nomme parce qu'elle est rare.** Les couples
`Demain, tu aideras tes camarades en classe.` / `Autrefois, tu aidais tes camarades en classe.` et
`Demain, je jouerai dans le jardin.` / `Autrefois, je jouais tous les jours dans le jardin.` sont des
paires minimales : meme verbe, meme complement, meme personne, seul le temps change. **Un enfant qui
tombe sur les deux dans la meme partie voit la marque du temps toute seule**, sans qu'aucune consigne
n'ait a le lui dire. Personne ne me l'avait demande et c'est de la bonne pedagogie.

#### 5. Point 5 : ma propre redaction est ambigue, et je le dis avant de m'en servir

Mon point 5 disait : *"les indices de ces items nomment la marque du temps et ne se contentent pas de
nommer la personne"*. **Il se lit de deux facons, et je ne vais pas choisir celle qui arrange ma
conclusion sans dire que l'autre existe.**

- **Lecture A** - "la marque du temps" designe le complement de la phrase, au sens que je lui donne
  moi-meme au point 3 ("un complement comme 'hier', 'demain', 'tous les jours'"). Alors **8 des 13
  indices neufs la citent** : les six de l'imparfait, celui de `auront`, celui de `aide` ("Le mot
  'autrefois' annonce l'imparfait", "Le mot 'demain' annonce le futur", "Le mot 'maintenant' annonce
  le present"). **Les cinq autres indices de futur ne la citent pas** : ils disent "Au futur, avec
  'je', le verbe 'jouer' se termine par '-erai'".
- **Lecture B** - le contraste que ma phrase pose elle-meme est avec "se contenter de nommer la
  personne". Alors **les 13 la satisfont** : chacun nomme son temps explicitement, aucun ne se
  contente de la personne.

**J'adopte la lecture B, et le motif est pedagogique, pas commode.** Au point 3 j'avais ecrit que la
marque dans la phrase *"est ce qui rend le point 2 jouable sans ambiguite"* : elle sert la ou l'enfant
doit **choisir** un temps. Or sur les cinq items de futur qui ne citent pas `Demain`, **les quatre
propositions sont toutes au futur** : le temps n'est pas en jeu, seule la personne l'est, et citer
`Demain` n'aiderait aucun enfant a choisir entre `jouerai` et `joueras`. Sur les **trois** items ou le
temps est reellement en jeu, la marque est nommee, sur les trois. **La fonction de mon point 5 est
donc servie exactement la ou elle mord.**

**Point 5 : SATISFAIT.** Et l'ambiguite est la mienne : c'est ma redaction qui aurait du distinguer
les items ou le temps se choisit de ceux ou il est donne. Je l'inscris en reserve 3 de la section 8,
sans en faire un motif, et je ne l'opposerai a personne.

**Point 6 - "j'aurai joue l'increment".** Joue : douze parties, les vingt-cinq items, chacun avec son
indice apres trois echecs reels, jusqu'a l'ecran de fin de partie.

**J'ai aussi verifie ce que mon point 5 ne demandait pas mais que je garde seule : la verite de ce qui
est enseigne.** Les treize indices neufs sont vrais et chacun est borne au verbe qu'il nomme - le
motif de mon refus du 2026-08-10 n'est pas reintroduit. **Une imprecision cependant, que je nomme
parce qu'elle est de mon ressort** : trois indices d'imparfait disent "le verbe 'faire' / 'être' /
'avoir' est irrégulier : il devient 'faisait' / 'étions' / 'aviez'". Ces phrases ne sont pas fausses -
ces verbes **sont** irreguliers - mais elles laissent croire que l'irregularite explique la forme,
alors qu'a l'imparfait les terminaisons `-ais -ais -ait -ions -iez -aient` sont **les memes pour tous
les verbes** : c'est le radical qui change, jamais la terminaison. C'est justement ce qui rend
l'imparfait apprenable, et ces trois indices le cachent. Reserve 2 de la section 8, non bloquante :
rien de faux n'est enseigne, une regularite utile est seulement laissee de cote.

#### 6. Ce que je credite au contenu de S8-4, avant de dire ce que je refuse

Je le mets avant le motif, comme au refus du 2026-08-10, parce que le refus ne porte pas sur cela.

- **Les treize items neufs sont exacts, un par un** : vingt-cinq couples personne/forme corrects,
  soixante-quinze distracteurs tous formes reelles du meme verbe, treize indices vrais et bornes.
- **Ma barre de six points est atteinte** sans qu'aucun de ses termes ait ete tire vers le bas, et
  depassee sur le point 2.
- **Le paquet passe de 12 a 25 items pour dix manches**, ce qui ameliore materiellement ma reserve 3.
- **Deux paires minimales de temps** apparaissent, que personne n'avait demandees (section 4).
- Et `marc-dev` a **evite le piege que j'avais moi-meme signale** au lieu de suivre ma consigne a la
  lettre au prix de la mise en page. C'est la seconde fois qu'il verifie une de mes references plutot
  que de l'appliquer les yeux fermes, et les deux fois il a eu raison.

#### 7. Le motif du refus, et ce qui n'en fait pas partie

**Le motif, unique, chiffre, neuf, et dont je suis la seule garde.**

A 360 px de largeur reelle, dans le cabinet livre le meme jour, les quatre colonnes de ce paquet
mesurent **152,00 px** (`(336 - 16 de cadre - 16 de gap) / 2` par rangee). Deux propositions du paquet
sont **plus larges que leur propre boite** :

| Proposition | Largeur des glyphes | Boite du bouton | Air de chaque cote |
|---|---|---|---|
| `mangeons` | **156,02 px** | 152,00 px | **-2,02 px** |
| `cherchons` | **152,17 px** | 152,00 px | **-0,09 px** |
| `cherchent` | 148,30 px | 152,00 px | +1,84 px |

**Ce que l'enfant voit, et je l'ai regarde sur la capture, pas seulement dans les nombres.** Sur
l'item "Vous ___ du chocolat.", `mangeons` s'ecrit **2,02 px au-dela de chaque bord de son bouton**.
Dans la colonne de droite, son dernier `s` est pose sur la paroi bleu sombre du cabinet et non sur le
bouton bleu clair auquel il appartient. Le mot **traverse la paroi de la machine**. Dans la colonne de
gauche, il la traverse de l'autre cote.

**Pourquoi c'est un motif et pas une reserve.**

1. **Ce n'est pas une troncature et je ne le presente pas comme telle** - rien n'est coupe, rien n'est
   comprime, `overflow` est bien `visible` et le mot reste lisible. **C'est une reponse qui sort de la
   chose qu'il faut presser.** Mon acquis 3 et mon acquis 8, dont j'ai exige nommement le maintien
   dans mon arbitrage du matin (section 7), portent sur ce que l'enfant lit **dans** sa colonne. Ici
   il le lit a cheval sur la colonne et sur le cadre.
2. **C'est le seul point de tout ce sprint ou une propriete que je garde seule a effectivement
   mordu.** Je l'avais ecrite de ma main le 2026-08-10, reserve 1 de cette fiche : *"tout mot plus
   large que 156 px sortira de sa colonne, et aucun test ne le verra jamais - `ADR-0025` a etabli que
   le budget est une largeur de glyphes rendus."* Elle a mordu, et aucun test ne l'a vue. **Si je ne
   la fais pas valoir aujourd'hui, elle n'a jamais rien voulu dire.**
3. **C'est le cabinet et l'activation ensemble qui le produisent, et personne n'a mesure la
   rencontre.** Sans le cabinet, la colonne fait 160 px et `mangeons` tient a 2,0 px. Sans
   l'activation, aucun enfant n'atteint ce paquet a quatre colonnes. Les deux lots ont ete routes au
   planning comme n'ayant "aucune dependance de fichier entre eux" - **et c'est exact, ils n'en
   avaient aucune.** La dependance etait sur le budget de 360 px, et ce budget n'appartient a aucun
   fichier : il n'appartient qu'a moi. **Je l'avais meme ecrit noir sur blanc** dans cette fiche, le
   matin meme : *"Un cabinet et ce paquet en quatre colonnes ne tiennent pas ensemble a 360 px, et il
   vaut mieux le savoir avant qu'apres."* Cela a ete su avant, et l'activation a eu lieu quand meme.
4. **Le refus est actionnable sans avoir a me redemander ce que je veux dire** : il faut que chaque
   proposition de ce paquet tienne dans une colonne de 152 px, glyphes compris, a 2 rem, sans
   troncature ni compression. Deux propositions ne le font pas. **Comment** on y arrive n'est pas ma
   decision : reduire le paquet a des formes plus courtes, ne pas mettre quatre colonnes dans le
   cabinet a 360 px, ou retirer le jeu du catalogue le temps de trancher sa disposition - ce dernier
   choix appartient a `camille-po`, les deux autres a `theo-archi`. Je decris, je ne prescris pas.

**Ce qui ne fait PAS partie du motif : le pli. Et je dois etre honnete sur le fait que c'est le
defaut le plus grave de cet ecran.**

Mesure sur les 33 etats joues, pli a `y = 640` :

| Etat | Bas du cabinet | Quatrieme cote sous le pli | Une rangee de reponses sous le pli |
|---|---|---|---|
| 8 etats sans indice | 540..636 px | **0 / 8** | **0 / 8** |
| 25 etats avec indice | 636..792 px | **24 / 25** | **24 / 25** |

Autrement dit : **des que l'indice s'affiche - c'est-a-dire exactement apres trois echecs consecutifs,
au moment ou l'enfant en a le plus besoin - deux des quatre reponses sont coupees par le bas de
l'ecran, et le cabinet n'a plus de fond visible. Sur 24 items sur 25.** L'aide arrive et emporte la
moitie des reponses avec elle.

**Un lecteur trouvera etrange que je refuse sur 2 px et pas sur cela, et il aura raison de le
trouver etrange. Voici pourquoi je m'y tiens quand meme :**

- c'est le defaut de `DEC-0016`, dont j'ai etabli moi-meme le 2026-08-10 que la cause est la longueur
  de l'enonce a 2 rem, pas le nombre de propositions ni la mise en page ;
- je l'ai **refuse comme motif sur "Le bon petit mot"**, jeu du catalogue dont le Done est accorde, et
  qui le porte encore aujourd'hui sur 11 etats sur 30. **Je ne peux pas appliquer deux barres a deux
  paquets pour un seul defaut** - c'est l'incoherence que j'ai refusee ce matin-la, et elle ne devient
  pas acceptable parce qu'elle m'arrangerait ;
- `DEC-0017` l'a explicitement laisse hors du perimetre de ce sprint, et mon propre arbitrage du matin
  a ecrit que je ne le rouvrais pas ;
- et **une part de son aggravation est mon erreur** : le cabinet y ajoute 40 px contre une limite de
  150 px que j'avais posee sur "Le bon son" en le croyant a tort le cas le plus charge du catalogue.

**Donc : constat neuf, date, chiffre, destinataire `camille-po`, sous `DEC-0016` - et pas un motif.**
Mais je dis clairement ce que cela implique, pour qu'on ne se trompe pas sur ma porte : **si
`camille-po` veut savoir ce qui rend vraiment ce jeu inutilisable a 360 px, c'est cette ligne-la et
non mes 2 px.** Mes 2 px sont ce que je peux refuser sans me contredire ; le pli est ce qui fait le
plus de mal a l'enfant. Les deux doivent etre traites, et `DEC-0016` devrait cesser d'etre une
intention non ouverte au backlog.

#### 8. Reserves - a convertir en stories par `camille-po`, non bloquantes

**Les six reserves de mon refus du 2026-08-10 et les deux du 2026-08-10 second audit restent
ouvertes**, et je ne les reformule pas. Deux evoluent, et je le dis :

- **reserve 3 (douze items pour dix manches)** : **materiellement amelioree**, le paquet est a 25
  items. Elle reste ouverte pour la seule part qui subsiste, la repetition due a la "manche plus
  simple" (section 0 bis, point A) ;
- **reserve 1 (`mangeons` a 2,0 px)** : elle n'est plus une reserve, **elle est devenue le motif de ce
  refus** (section 7). Elle sort donc de cette liste, non parce qu'elle est traitee mais parce qu'elle
  a mordu.

**Reserve 6 (le niveau non annonce sur l'accueil) empire, et je le chiffre** : l'accueil propose
desormais **trois** jeux de CM1 non marques a cote d'un seul jeu de CP marque (`Calcul CP`). Un enfant
de CP qui lit "Le bon verbe" n'a aucun moyen de savoir que ce n'est pas pour lui. Toujours une question
produit, toujours pas un motif.

**J'en ajoute quatre, datees du 2026-08-11.**

1. **L'imparfait ne porte aucun verbe du 2e groupe.** Section 2. C'est le temps ou apparait l'infixe
   `-iss-` (`nous finissions`, `ils finissaient`), et c'est la difficulte principale de l'imparfait au
   CM1 : elle est entierement absente du paquet. **Ma barre ne la demandait pas et je ne la lui
   oppose pas** ; elle est ouverte sur ma propre redaction. Elle ne peut pas etre traitee avant que
   la largeur de colonne soit reglee - `finissais` est la forme la plus courte disponible et le motif
   de la section 7 montre que la marge est deja nulle.
2. **Trois indices d'imparfait appellent "irregulier" ce qui ne l'est pas au niveau ou l'enfant
   regarde.** Section 5, fin. `faire`, `être` et `avoir` sont bien des verbes irreguliers, mais a
   l'imparfait les six terminaisons sont les memes pour tous les verbes ; seul le radical change. Ces
   indices masquent la regularite qui rend le temps apprenable. Rien de faux n'est enseigne, une
   occasion est perdue.
3. **Ma propre redaction du point 5 etait ambigue.** Section 5. Elle ne distinguait pas les items ou
   le temps se choisit de ceux ou il est donne. Consequence concrete et petite : sur les cinq items de
   futur qui ne citent pas `Demain`, un enfant n'apprend pas par l'indice que c'est `Demain` qui
   annonce le futur. La correction est de deux mots par indice.
4. **La marque du temps est toujours le premier mot de la phrase, et elle est totalement predictive.**
   `Demain,` sur les six items de futur, `Autrefois,` sur les six d'imparfait, `Maintenant,` sur le
   treizieme de present : 13 items sur 13. Un enfant peut donc trier le temps sur le premier mot
   **sans lire ni le verbe ni la phrase** - c'est le meme raccourci que ma reserve 1 du 2026-08-10
   (le sujet toujours pronominal et colle au trou), a un autre endroit. Mon point 3 demandait que la
   marque soit reperable, elle l'est, donc cela ne bloque rien ; mais une marque en fin de phrase, ou
   un item sur trois sans marque explicite, ferait travailler la meme competence plus haut.

**Et un constat mesure, qui n'est ni un motif ni une reserve, parce qu'il porte sur le tirage et non
sur le paquet** : seuls **3 items sur 25** opposent deux temps entre eux. Le tirage etant sans remise,
la probabilite qu'une partie de dix manches n'en contienne **aucun** est de
`C(22,10) / C(25,10)` = **19,8 %**. Environ **une partie sur cinq** ne demande donc jamais a l'enfant
d'identifier un temps. Ma barre parlait du paquet et elle est satisfaite ; je donne le chiffre parce
qu'il dit ou est la marge de progres, et parce que je preferais le calculer que le supposer.

#### 9. Ce dont je reste seule garde sur ce jeu

- **La verite de ce qui est enseigne dans un indice.** Inchange. `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest`
  garde la coherence interne, et il est reste vert sur les treize indices neufs - ce qui est un vrai
  gain. Mais "cette regle de grammaire est-elle vraie, et enseigne-t-elle la bonne chose" reste hors
  de portee d'un automate : c'est ce qui m'a permis de voir la reserve 2 ci-dessus, qu'aucune CI ne
  pouvait voir.
- **Qu'aucun item n'admette deux lectures correctes.** Non automatisable, verifie a la main sur les
  vingt-cinq items par `marc-dev` puis par moi, separement. C'est la bonne facon de faire pour un
  critere de cette nature, et je la note comme telle.
- **Qu'un mot tienne dans sa colonne.** **Ce point vient de mordre** (section 7). C'est le seul de mes
  points de garde solitaire qui ait produit un refus ce sprint, et il l'a produit dix heures apres que
  j'aie ecrit qu'il le produirait.
- **Que le quatrieme cote du cabinet soit dans l'ecran** (C1), qui ne l'est pas sur 24 items sur 25 :
  aucun automate ne voit une forme, et `ADR-0025` a etabli que ce budget n'est pas gardable en CI.
- **Tout ce que garde le verdict transverse** du meme jour (`FICHE-bandit-manchot-additions-cp.md`,
  section 9), dont l'acquis 11 sur le lexique de jeu d'argent, que j'ai scanne moi-meme sur le rendu
  parce que personne d'autre ne le fait.

#### 10. Suites hors de ma main

- **Pour `camille-po`** : le motif de la section 7 est un **motif, pas une story** ; les quatre
  reserves neuves de la section 8 et le constat de pli le sont aussi. **Ma barre est levee** : la ligne
  de la matrice peut passer a `couvert-valide`, et c'est le seul changement de statut que ce sprint
  autorise (`DEC-0017`, objectif gele). **Je n'ai pas edite cette ligne.** Le sort du jeu au catalogue
  pendant la correction est sa decision. Et je repete ce que la section 7 dit : `DEC-0016` merite mieux
  qu'une intention non ouverte au backlog.
- **Pour `theo-archi`**, comme symptomes et non comme conception : quatre colonnes de 152 px ne
  contiennent pas les glyphes de ce paquet a 2 rem ; le budget vertical du cabinet doit etre repose sur
  ce jeu et sur "Le bon petit mot", pas sur "Le bon son" ou je l'avais pose a tort.
- **Pour `marc-dev`, et je le dis parce que c'est utile** : son interpretation de mon point 1 est
  retenue, ses treize items sont exacts, sa relecture manuelle des points 4 et 5 etait la bonne
  methode, et sa mesure de largeur etait juste sur l'etat qu'il avait sous les yeux. **Le refus ne
  porte sur rien de ce qu'il a ecrit dans le paquet.** Il porte sur la mise au catalogue d'un jeu dont
  la disposition n'a jamais ete arretee - une question que j'avais moi-meme laissee ouverte.
- **Un refus renvoie l'increment a `camille-po`, qui en fait du backlog. Il ne bloque pas le sprint
  suivant**, et rien du contenu fusionne n'a besoin d'etre defait : les vingt-cinq items, les
  soixante-quinze distracteurs et les vingt-cinq indices sont acquis.
- **Je n'efface rien** : mon refus du 2026-08-10, mon Done du second audit et mes arbitrages
  prealables restent lisibles plus bas, dans l'ordre, avec leurs dates.

### Arbitrage prealable au developpement - silhouette du manche, relief et contraste du cabinet (s10), du 2026-08-12

**Ce bloc n'est pas un Done pedagogique et ne touche aucun verdict de cette fiche.** L'arbitrage
complet est ecrit une seule fois, dans `FICHE-bandit-manchot-additions-cp.md`, bloc du 2026-08-12 : il
porte sur la mecanique, donc sur ce jeu-ci. **Rien n'est code.** Ce qui concerne **ce jeu-ci**, et qui
est mesure sur lui plutot que sur un autre :

1. **C'est sur ce paquet que se mesure le pixel de relief**, parce qu'il porte l'etat le plus bas du
   catalogue. Etat de reference que j'ai nomme, condition N6 : enonce **"Autrefois, ils ___ ce dessin
   anime chaque mercredi."**, **sans indice**, 360 x 640. Derniere rangee non-fond attendue apres le
   lot : **635**, contre **634** aujourd'hui. Il reste **4 rangees libres** sous le cabinet, et le lot
   complet ne coute pas plus que l'ombre seule.
2. **Je corrige mes chiffres de pli du 2026-08-11 sur ce jeu, sans les effacer.** J'avais ecrit un bas
   de cabinet `540..636` ; je remesure aujourd'hui **`538..634`** sur 34 etats distincts joues (17 sans
   indice, 17 avec), soit **-2 px**. Je ne sais pas reconstituer la cause de l'ecart et je le dis.
   Les valeurs reellement observees sont 538, 586, 634, puis 664, 712, 742, 760, 790.
3. **Le fait neuf, et il ferme une question qu'`ines-design` laissait ouverte** : **aucun etat de ce
   jeu n'a son bas entre 635 et 639.** Entre le dernier bas visible (634) et le premier bas coupe
   (664) il y a 30 px. Un decalage d'1 px ne fait donc basculer **aucun** etat sous le pli.
4. **Le defaut de `DEC-0016` reste ce qui fait le plus de mal sur cet ecran**, remesure aujourd'hui a
   **16 etats coupes sur 34**, tous avec indice. Le pixel de relief n'y change rien, et je ne le
   rouvre pas comme barre - pour la quatrieme fois, et pour la meme raison.
5. **Ma reserve 1 de cette fiche n'est pas le constat de pli.** Elle est `mangeons` a 2,0 px, devenue
   le motif de mon refus du 2026-08-11. Le constat de pli est un constat neuf sous `DEC-0016`,
   section 7 du troisieme audit. `ines-design` et `ADR-0033` citent l'un pour l'autre ; la confusion
   vient de ma redaction et je la corrige la ou elle circule.

### Arbitrage prealable au developpement - cabinet visible de la machine, du 2026-08-11

**Ce bloc n'est pas un Done pedagogique et il est degrade** : le cabinet n'est pas ecrit. Il ne
touche aucun verdict de cette fiche. L'arbitrage complet est ecrit une seule fois, dans
`FICHE-bandit-manchot-additions-cp.md`, bloc du 2026-08-11 - il vaut pour la mecanique, donc pour ce
jeu si un jour il entre au catalogue. Ce qui concerne **ce jeu-ci** :

1. **Je le laisse volontairement hors de mon tableau de planchers de largeur de colonne.** Ce tableau
   chiffre les trois jeux du catalogue ; ce jeu-ci n'y est pas, et sa disposition - quatre
   propositions, repli en deux rangees - reste une saisine de `camille-po` avec `theo-archi`. Je ne
   la rouvre pas et je ne fabrique pas un plancher pour un cas dont la disposition n'est pas arretee.
2. **Ce que je dis quand meme, parce que ca se saura au moment du dessin** : c'est le cas le plus
   contraint du depot. `mangeons` tient a **2,0 px** de chaque cote de sa boite - la marge la plus
   etroite que j'aie mesuree. **Tout cadre lateral paye sur la largeur des colonnes tronque ce mot.**
   Un cabinet et ce paquet en quatre colonnes ne tiennent pas ensemble a 360 px, et il vaut mieux le
   savoir avant qu'apres.
3. **Ma reserve du 2026-08-10 sur le cumul du decalage au-dela de quatre colonnes reste ouverte** et
   reste ma garde. Un cabinet n'y change rien.

### Verdict - second audit, increment livre en s07, du 2026-08-10

- **Verdict** : **VALIDE SOUS RESERVE.** Done pedagogique accorde. **Mon refus du 2026-08-10 est
  leve** : son motif unique - cinq indices enoncant une regle de conjugaison fausse - est traite, et
  je l'ai verifie en jouant. Le refus n'est pas efface : il reste lisible plus bas avec sa date et son
  motif, parce qu'un refus efface n'a aucune valeur de preuve.
- **Date** : 2026-08-10.
- **Commit audite** : `10dd74d`, `HEAD` de `round-two`.
- **Moyens** : audit **joue**, non degrade. Meme installation que le verdict transverse
  (`FICHE-bandit-manchot-additions-cp.md`, section Moyens) : worktree dedie hors de l'arbre du depot,
  lignee et JDK verifies par les outils du depot, fat jar reconstruit, lance sur un port dedie,
  `mvn -B verify` vert (315 tests). **Ce jeu est hors catalogue** (`DEC-0014`) : pour le jouer, j'ai
  ouvert une branche jetable qui n'ajoutait que la ligne de catalogue retiree par `6060d32`, reprise
  au caractere depuis l'historique plutot que reecrite. **Cette branche n'a jamais ete fusionnee et
  elle est detruite**, et le fichier `catalogue.yaml` de mon lot est identique a celui de
  `round-two`. C'est le mecanisme qui tient `DEC-0014` a la lettre : le jeu a ete joue sans rejoindre
  le catalogue. **Trois parties completes** de dix manches, ratees a chaque manche pour faire venir
  l'aide, plus un balayage jusqu'a voir les douze items, a 360 x 640.
- **Done technique** : acquis.
- **Ligne de matrice** : **reste `en cours`**, et pas a cause de mes reserves. La barre pour
  `couvert-valide` est celle que j'ai ecrite moi-meme au refus du 2026-08-10, section 5, et elle est
  inchangee : le futur et l'imparfait doivent etre ouverts. Ce paquet couvre un temps sur trois. Je ne
  la deplace ni dans un sens ni dans l'autre. Je n'ai pas edite cette ligne, elle appartient a
  `camille-po`.

#### 1. Le motif du refus est leve, et voici la verification item par item

`marc-dev` a applique la troisieme des formes que j'avais nommees - nommer le verbe - aux cinq
indices fautifs. J'ai relu les **douze** indices du paquet, pas seulement les cinq, et je les ai vus
s'afficher en jeu.

| Item | Indice servi aujourd'hui | Vrai ? | Dementi par un autre item du paquet ? |
|---|---|---|---|
| 1 `va` | "Le verbe 'aller' est irrégulier : avec 'il' ou 'elle', il devient 'va' au présent." | oui | non |
| 2 `lisons` | "Avec 'nous', le verbe se termine **presque toujours** par '-ons'..." | oui, tempere | non |
| 3 `mangez` | "Avec 'vous', le verbe se termine **presque toujours** par '-ez'..." | oui, tempere | non |
| 4 `fais` | "Le verbe 'faire' est irrégulier : avec 'je', il devient 'fais' au présent." | oui | non |
| 5 `jouent` | "Avec 'ils' ou 'elles', **le verbe 'jouer'** se termine par '-ent'..." | oui | non |
| 6 `dessines` | "Avec 'tu', **le verbe 'dessiner'** se termine par '-es'..." | oui | non - l'item 8 parle de `finir`, pas de `dessiner` |
| 7 `cherche` | "Avec 'je', **le verbe 'chercher'** se termine par '-e'..." | oui | non - l'item 4 parle de `faire` |
| 8 `finis` | "Avec 'tu', le verbe 'finir' se termine par '-is'..." | oui | non |
| 9 `range` | "Avec 'il' ou 'elle', **le verbe 'ranger'** se termine par '-e'..." | oui | non - l'item 1 parle de `aller` |
| 10 `courons` | "Avec 'nous', le verbe 'courir' se termine par '-ons'..." | oui | non |
| 11 `prenez` | "Avec 'vous', le verbe 'prendre' se termine par '-ez'..." | oui | non |
| 12 `chantent` | "Avec 'ils' ou 'elles', **le verbe 'chanter'** se termine par '-ent'..." | oui | non |

**Plus aucun indice de ce paquet n'affirme d'une terminaison qu'elle vaut pour "le verbe" sans
restriction.** C'etait mon exigence, mot pour mot, et elle est satisfaite. Les deux formulations
temperees des items 2 et 3 sont conservees, ce qui est juste : elles etaient deja correctes et il n'y
avait rien a y toucher.

**Et je l'ai vu en jeu, pas seulement dans le fichier.** J'ai fait venir l'indice de l'item 7 apres
trois echecs et lu a l'ecran : *"Avec 'je', le verbe 'chercher' se termine par '-e' au présent : c'est
'cherche'."* La contradiction que j'avais vue se produire en deux minutes le 2026-08-10 - "avec tu, le
verbe se termine par -es" a la septieme manche puis `finis` a la dixieme - **ne peut plus se produire**,
parce que chaque enonce de regle est desormais borne au verbe qu'il nomme. Un enfant qui rate trois
fois de suite recoit maintenant une aide vraie. C'est tout ce que je demandais.

#### 2. Ma reference etait fautive sur un point, et `marc-dev` a raison

Il a verifie mes trois modeles d'indice et en a trouve un inexistant. **Je ne maintiens pas ma
reference, je la corrige.** J'avais ecrit : *"La correction est petite, exacte, et ses trois modeles
sont **deja dans le paquet**."* C'est faux pour le modele 2. Verifie par moi sur le paquet
d'avant correction : les douze indices employaient exactement deux formes attestees - temperer
("presque toujours", items 2 et 3) et nommer le verbe (items 1, 4, 8, 10, 11). **Aucun indice ne
nommait un groupe.** Le modele 2 etait de mon invention, presente a tort comme un modele existant.

Trois consequences, que je pose nettement pour qu'on n'ait pas a me redemander ce que je voulais dire :

1. **`marc-dev` a bien fait de n'appliquer que les deux formes attestees.** Suivre une consigne dont
   un tiers est invente aurait ete suivre mon erreur.
2. **Je maintiens que nommer le groupe aurait ete pedagogiquement acceptable**, et je maintiens ce que
   j'en disais : les cinq items concernes sont bien tous du premier groupe, je l'ai reverifie
   (`jouer`, `dessiner`, `chercher`, `ranger`, `chanter`). Ce qui tombe est ma phrase de fait, pas mon
   jugement de valeur sur la forme.
3. **Mon exigence n'a jamais ete "prends une de ces trois formes".** Elle etait : *"qu'aucun indice de
   ce paquet n'affirme d'une terminaison qu'elle vaut pour 'le verbe' sans restriction"*, et j'avais
   ecrit explicitement que je ne choisissais pas laquelle. Le choix de `marc-dev` satisfait
   l'exigence ; les trois modeles n'etaient qu'une aide, dont un etait errone.

Je le note aussi parce que c'est la troisieme fois qu'un fait que j'avais moi-meme enregistre se
revele inexact quand quelqu'un le verifie. Verifier ce que j'ai ecrit avant de m'appuyer dessus fait
partie de ma methode, et ici c'est un autre qui l'a fait a ma place.

#### 3. Le repli en deux rangees, remesure sur le code livre

Ce n'est **pas** ma condition - c'est la seconde condition de `camille-po` (`DEC-0015`, quatrieme
amendement, point 2), et c'est elle qui la ferme. Ma part est la mesure, et je la rends.

Balayage des **douze** items, indice non affiche, a 360 x 640 :

| Ce que j'avais confirme sur maquette | Ce que je mesure sur le code livre |
|---|---|
| deux colonnes par rangee a partir de la quatrieme proposition | conforme : `4 colonnes de 160 px`, deux rangees de deux |
| 160 px par colonne a 360 px | **160,0 px**, sur les douze items |
| ecart de 16 px sur les deux axes | **16 px** : rangee 1 a 365-439, rangee 2 a 455-529 ; 336 px de conteneur pour 2 x 160 px |
| aucune troncature | **aucune**, sur les douze items : aucun mot ne sort de sa boite, aucun sur deux lignes, police a 32 px partout |
| la quatrieme proposition entierement lisible au-dessus du pli | **oui, sur les douze items** : le glyphe le plus bas du paquet est a 561 px, soit 79 px au-dessus du pli |
| les cas a deux et trois colonnes inchanges | **inchanges** : les trois jeux du catalogue mesures separement, aucune regression |

Les cinq mots les plus larges du paquet, mesures sur le rendu :

| Mot | Largeur du mot | Air de chaque cote dans 160 px |
|---|---|---|
| `mangeons` | **156,0 px** | **2,0 px** |
| `cherchons` | 152,2 px | 3,9 px |
| `cherchent` | 148,3 px | 5,8 px |
| `dessinent` | 142,9 px | 8,6 px |
| `rangeons` | 139,4 px | 10,3 px |

**Aucun n'est tronque, et les six conditions de ma section 3 sont satisfaites sur le rendu.** Je
corrige au passage un de mes propres chiffres, a mon desavantage : j'avais ecrit qu'il restait "3 px
d'air a droite de mangeons" ; il en reste **2,0**. Le mot tient, mais il tient a 2 px, et c'est la
marge la plus etroite du depot. Voir la reserve 1.

**Et ce que ce repli demontre au-dela de sa propre mesure** : `cherche` et `cherches` sont a l'ecran
en meme temps, dans des colonnes de 160 px, **parfaitement distinguables** - je les ai lus. C'etait
le defaut que ma reduction a trois propositions creait, et son extinction du 2026-08-10 est confirmee
par le rendu. Les quatre propositions homophones du premier groupe sont bien la, et elles travaillent
la difficulte que cette competence doit faire travailler. Ce que j'avais ecrit au credit du paquet le
matin tient.

#### 4. Constat neuf, mesure, qui ne fonde PAS ce Done et ne le retire pas

**Cinq items sur douze mettent la seconde rangee de propositions entierement sous le pli quand
l'indice s'affiche.** Mesure sur le code livre, a 360 x 640, indice affiche :

| Enonce | Lignes d'enonce | Bas de l'indice | Rangee 1 | Rangee 2 | Etat |
|---|---|---|---|---|---|
| "Elle ___ toujours ses affaires avant de partir en récréation." | 3 | 531 | 543-617 | **633-707** | rangee 2 sous le pli |
| "Je ___ mon cahier dans mon sac." | 2 | 483 | 495-569 | **585-659** | rangee 2 sous le pli |
| "Ils ___ une chanson en rentrant de l'école." | 2 | 483 | 495-569 | **585-659** | rangee 2 sous le pli |
| "Tous les jours, il ___ à l'école." | 2 | 483 | 495-569 | **585-659** | rangee 2 sous le pli |
| "Vous ___ vos cahiers avant de commencer." | 2 | 483 | 495-569 | **585-659** | rangee 2 sous le pli |
| les sept autres, indice affiche | 1 | 435 | 447-521 | 537-611 | au-dessus du pli |
| les douze, **sans** indice | 1 a 3 | - | - | - | **tous** au-dessus du pli |

**Pourquoi ce n'est pas un motif, et pourquoi je m'y tiens.** C'est exactement le defaut de
`DEC-0016`, dont j'ai etabli moi-meme le 2026-08-10 que la cause est la longueur de l'enonce a 2 rem
et non le nombre de propositions ni la mise en page. Je l'ai mesure le meme jour sur "Le bon petit
mot", jeu du catalogue dont le Done est accorde, et j'ai **refuse** d'en faire un motif la-bas. Je ne
peux pas l'opposer ici sans appliquer deux barres differentes a deux paquets pour un seul defaut - ce
qui est precisement l'incoherence que j'ai refusee ce matin-la. Le traitement est donc le meme :
constat neuf et date, destinataire `camille-po`, sous `DEC-0016`, et le Done reste accorde.

**Ce que j'ajoute quand meme, parce que c'est le meme mecanisme et qu'il faut le compter une fois pour
toutes** : le defaut ne se declenche que quand l'enfant vient d'echouer trois fois et recoit son
aide. Sur ce paquet il touche **cinq items sur douze**, soit plus de la moitie des items a enonce de
deux lignes ou plus. Sur "Le bon petit mot" il touche cinq items sur vingt-quatre. Ce n'est pas une
question de paquet, c'est une question de hauteur d'ecran, et `DEC-0016` a raison de la traiter comme
transverse.

#### 5. Reserves - a convertir en stories par `camille-po`, non bloquantes

Les six reserves de mon refus du 2026-08-10 (section 6 de ce bloc) **restent ouvertes et inchangees**,
et je ne les reformule pas : sujet toujours pronominal et colle au trou, indice qui donne la reponse
en plus de la methode, douze items pour dix manches, la seule fille sujet qui range ses affaires,
"recreation" dans trois items, et le niveau non annonce sur l'accueil. Aucune n'a ete traitee par ce
sprint, aucune n'etait censee l'etre.

J'en ajoute deux, datees du 2026-08-10 :

1. **`mangeons` tient a 2,0 px de chaque cote de sa boite.** Ce n'est pas une troncature et je ne le
   traite pas comme telle. C'est un plancher atteint : **tout mot plus large que 156 px sortira de sa
   colonne**, et aucun test ne le verra jamais - `ADR-0025` a etabli que le budget est une largeur de
   glyphes rendus. Le jour ou ce paquet gagne un `finissaient` ou un `apprendraient` - ce qui est
   exactement ce que demande l'ouverture du futur et de l'imparfait de ma section 5 - la mise en page
   ne tiendra plus. Je le dis maintenant plutot qu'au sprint ou cela arrivera.
2. **La seconde rangee sous le pli avec l'indice**, section 4 ci-dessus, sous `DEC-0016`.

#### 6. Ce dont je reste seule garde sur ce jeu

- **La verite de ce qui est enseigne dans un indice.** Inchange, et ce sprint le confirme dans les
  deux sens : la CI etait verte sur les cinq indices faux, et elle est verte sur les cinq corriges.
  `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` epingle desormais qu'un indice ne dementit pas un
  autre item du meme paquet, et c'est un vrai gain sur la **coherence interne** - c'etait la moitie de
  mon motif. L'autre moitie, "cette regle de grammaire est-elle vraie dans la langue", reste hors de
  portee d'un automate et reste ma charge.
- **Qu'un mot tienne dans sa colonne** (reserve 1 ci-dessus), et qu'il ne soit ni tronque, ni coupe, ni
  comprime.
- **Tout ce que garde le verdict transverse** de `FICHE-bandit-manchot-additions-cp.md`, section 12, y
  compris ma reserve sur la fin du mouvement, qui vaut sur ce jeu comme sur les trois autres.

#### 7. Suites hors de ma main

- **Pour `camille-po`** : ma condition 1 de `DEC-0015` (quatrieme amendement) est **remplie** - les
  cinq indices sont corriges et j'accorde un nouveau Done pedagogique. Sa condition 2, le repli, est
  **reproduite sur le code livre** et je rends la mesure en section 3 ; c'est elle qui la ferme, pas
  moi. La remise au catalogue est sa decision et je n'en dis pas plus : je ne repriorise pas et je
  n'ecris pas le backlog.
- **Pour `camille-po`** : le constat de la section 4 et les deux reserves de la section 5 sont des
  motifs, pas des stories.
- **`squad/programme/MATRICE-couverture.md`** : la ligne `francais | CM1 | Orthographe et grammaire |
  Conjuguer les verbes fréquents au présent, futur et imparfait de l'indicatif` **reste `en cours`**,
  pour la raison ecrite au verdict "Ligne de matrice" ci-dessus. Elle ne bouge pas sur ce verdict.
- **Je n'efface rien** : le refus du 2026-08-10 et ses deux arbitrages du meme jour, dont la reduction
  a trois propositions que j'ai eteinte moi-meme, restent lisibles plus bas dans l'ordre.

---

### Verdict - premier audit, increment livre en s06

- **Verdict** : **REFUSE.** Done pedagogique non accorde. Ce jeu ne peut pas etre mis devant une
  classe de CM1 en l'etat.
- **Date** : 2026-08-10.
- **Commit audite** : `be60b10` (`HEAD` de `round-two`).
- **Moyens** : audit **joue**, non degrade. Worktree dedie hors de l'arbre du depot, lignee
  verifiee (`outils/verifier-lignee.sh` : "le worktree sur la branche '<ma branche>' descend de
  round-two"). JDK constate par `outils/verifier-jdk.sh` : `java du PATH : 1.8.0_202 | JDK de
  compilation : 21.0.10 | JAVA_HOME : vide | toolchains lu par Maven : non (fichier present sous un
  nom non lu)` ; `JAVA_HOME` pointe sur ce JDK 21 le temps de la commande, jamais ecrit dans un
  fichier commite. Fat jar reconstruit, lance sur un port dedie. Deux parties completes de dix
  manches jouees : une juste de bout en bout (score 10 sur 10), une fausse a chaque manche pour
  faire venir l'aide. Ecrans mesures au navigateur sans tete en 1280 x 700 et a une largeur de
  viewport reellement de 360 px (methode du rapport qualite de `yanis-qa`, voir la section 4).

#### 1. Ce qui est juste, et je le dis d'abord parce que le refus ne porte pas la-dessus

**Les douze enonces sont exacts, verifies un par un.** Pour chaque item, une seule des quatre
propositions est grammaticalement possible : aucun item n'admet deux reponses vraies.

| Enonce | Attendu | Les trois distracteurs | Verdict |
|---|---|---|---|
| "Tous les jours, il ___ à l'école." | `va` | vais, vont, allez | juste |
| "Nous ___ une histoire." | `lisons` | lit, lisent, lis | juste |
| "Vous ___ du chocolat." | `mangez` | mange, mangeons, mangent | juste |
| "Je ___ mes devoirs." | `fais` | fait, faisons, font | juste |
| "Elles ___ dans le jardin." | `jouent` | joue, jouons, jouez | juste |
| "Tu ___ un dessin." | `dessines` | dessine, dessinent, dessinez | juste |
| "Je ___ mon cahier dans mon sac." | `cherche` | cherches, cherchons, cherchent | juste |
| "Tu ___ ton exercice avant la récréation." | `finis` | finit, finissons, finissent | juste |
| "Elle ___ toujours ses affaires avant de partir en récréation." | `range` | ranges, rangeons, rangent | juste |
| "Nous ___ dans la cour pendant la récréation." | `courons` | cours, court, courent | juste |
| "Vous ___ vos cahiers avant de commencer." | `prenez` | prend, prenons, prennent | juste |
| "Ils ___ une chanson en rentrant de l'école." | `chantent` | chante, chantons, chantez | juste |

Trois qualites reelles, que je porte au credit du paquet :

- **Les six personnes grammaticales sont chacune representee deux fois exactement** : `je` (items 4
  et 7), `tu` (6 et 8), `il`/`elle` (1 et 9), `nous` (2 et 10), `vous` (3 et 11), `ils`/`elles`
  (5 et 12). C'est le bon equilibre, et il n'est pas frequent qu'un paquet l'atteigne du premier
  coup.
- **Les trois groupes sont travailles** : six verbes du premier groupe, `finir` pour le deuxieme,
  et cinq irreguliers frequents (`aller`, `lire`, `faire`, `courir`, `prendre`). Le referentiel dit
  "verbes frequents" ; ce sont bien ceux-la.
- **Les distracteurs sont tous des formes du meme verbe au meme temps**, donc aucun distracteur
  absurde et aucune devinette. Mieux : sur les items du premier groupe, les quatre propositions
  sont homophones entre elles (`dessine`, `dessines`, `dessinent`), ce qui est exactement la
  difficulte que cette competence doit faire travailler - on ne peut pas la resoudre a l'oreille.

Le contenu **travaille bien la competence annoncee**, et non une competence voisine plus facile :
c'est l'accord de la forme verbale avec la personne, au present, et rien d'autre. Sur le perimetre
d'un temps sur trois, je n'ai rien a ajouter a ce que `DEC-0013` assume deja : voir la section 5.

#### 2. Motif du refus - cinq indices sur douze enoncent une regle fausse

C'est le motif, il est unique, et il suffit.

| Item | Texte de l'indice | Ce qui cloche |
|---|---|---|
| 5 (`jouent`) | "Avec 'ils' ou 'elles', le verbe se termine par '-ent' au présent : c'est 'jouent'." | faux : `ils vont`, `ils ont`, `ils sont`, `ils font` |
| 6 (`dessines`) | "Avec 'tu', le verbe se termine par '-es' au présent : c'est 'dessines'." | faux, et **dementi par l'item 8 du meme paquet** : "Tu ___ ton exercice" -> `finis` |
| 7 (`cherche`) | "Avec 'je', le verbe se termine par '-e' au présent : c'est 'cherche'." | faux, et **dementi par l'item 4** : "Je ___ mes devoirs" -> `fais` |
| 9 (`range`) | "Avec 'il' ou 'elle', le verbe se termine par '-e' au présent : c'est 'range'." | faux, et **dementi par l'item 1** : "Tous les jours, il ___" -> `va` |
| 12 (`chantent`) | "Avec 'ils' ou 'elles', le verbe se termine par '-ent' au présent : c'est 'chantent'." | meme defaut que l'item 5 |

**On ne peut pas lire "le verbe" comme "le verbe de cette phrase-ci", et c'est le paquet lui-meme
qui l'interdit.** Il emploie deux formulations distinctes, et il les distingue :

- quand il veut parler d'un verbe precis, il le nomme : *"le verbe 'finir' se termine par '-is'"*
  (item 8), *"le verbe 'courir'"* (item 10), *"le verbe 'prendre'"* (item 11), *"Le verbe 'aller'
  est irrégulier"* (item 1), *"Le verbe 'faire' est irrégulier"* (item 4) ;
- quand il veut enoncer une regle generale, il ecrit "le verbe" tout court **et il la tempere** :
  *"le verbe se termine **presque toujours** par '-ons'"* (item 2), *"**presque toujours** par
  '-ez'"* (item 3).

Les cinq indices du tableau ci-dessus ecrivent "le verbe" tout court **sans temperer**. Sous la
convention du paquet, ce sont donc des regles generales, et elles sont fausses.

**Pourquoi cela ne peut pas etre une reserve.** L'indice est **le seul endroit de ce jeu ou l'on
enseigne quelque chose** : tout le reste est un choix entre quatre boutons. Et je l'ai verifie en
jouant : il n'apparait qu'a partir de la quatrieme manche, apres trois echecs consecutifs, donc il
est servi a l'enfant qui vient de se tromper trois fois de suite - celui qui est le moins en etat de
reperer que l'aide est fausse. Ce que cet enfant emporte de la partie, c'est "avec je, le verbe se
termine par -e", et il ecrira *je prende*, *il alle*, *je faie*. La faute que le jeu lui apprend est
exactement celle que le jeu existe pour prevenir.

**Et je l'ai vu se produire dans une seule partie**, ce n'est pas une deduction sur le fichier : a
la septieme manche j'ai lu "Avec 'tu', le verbe se termine par '-es' au présent", et a la dixieme
manche de la meme partie le jeu m'a demande "Tu ___ ton exercice avant la récréation." dont la
reponse est `finis`. Le jeu se contredit devant l'enfant, en deux minutes.

**La correction est petite, exacte, et ses trois modeles sont deja dans le paquet.** Pour chacun des
cinq indices, une seule des trois formes suffit :

1. temperer, comme les indices 2 et 3 : "le verbe se termine **presque toujours** par '-ent'" ;
2. nommer le groupe : "**les verbes en -er** se terminent par '-e' avec 'je'" - c'est la forme la
   plus juste des trois, parce que les cinq items concernes sont tous du premier groupe ;
3. nommer le verbe, comme les indices 8, 10 et 11 : "le verbe 'chanter' se termine par '-ent'".

Je ne choisis pas laquelle : les trois sont acceptables et le choix appartient a l'auteur du
contenu. Ce que j'exige, c'est qu'aucun indice de ce paquet n'affirme d'une terminaison qu'elle vaut
pour "le verbe" sans restriction.

#### 3. Ce dont je reste seule garde sur ce jeu

Pour qu'on ne croie pas couvert ce qui ne l'est pas. `PaquetsFrancaisS04Test` epingle desormais que
les propositions sont toutes des formes du meme verbe au present, que les six personnes sont
representees, et que le paquet varie sur vingt manches. **Aucun de ces garde-fous ne regarde le
contenu des indices, et aucun ne peut le faire** : "cette regle de grammaire est-elle vraie" n'est
pas une propriete qu'un test peut trancher. La verite de ce qui est enseigne a l'enfant est ma
charge, et ce refus en est la demonstration : la CI est verte sur ce paquet.

#### 4. Constat neuf, mesure, qui ne fonde PAS ce refus

Je le separe explicitement du motif, parce qu'il ne doit pas etre lu comme une barre que j'aurais
posee apres coup.

**Sur un telephone de 360 x 640, la quatrieme proposition est sous le pli.** "Le bon verbe" est le
premier jeu du produit a quatre propositions ; les deux autres en ont trois. Mesure a une largeur et
une hauteur de viewport reellement de 360 x 640 - la page reelle chargee dans un cadre de largeur
et de hauteur CSS fixees, dans une fenetre Chrome de 900 px, pour contourner le plancher de 500 px
etabli par `yanis-qa` :

| Element | Position verticale | Sous le pli a 640 px ? |
|---|---|---|
| bandeau | 0 - 76 | non |
| proposition 1 | 368 - 437 | non |
| proposition 2 | 458 - 527 | non |
| proposition 3 | 548 - 617 | non |
| **proposition 4** | **638 - 707** | **oui, 2 px visibles sur 69** |
| hauteur totale du document | 753 px | |

L'enfant voit trois propositions et un liseré. Comme l'ordre des propositions est tire a chaque
manche, la bonne reponse sera parfois la quatrieme : l'enfant repondra alors en ignorant qu'elle
existait. Sur le meme viewport, "Le bon son" (trois propositions) tient entierement au-dessus du
pli, verifie par la meme mesure - le defaut est bien lie au passage a quatre propositions et non a
l'habillage en general.

**Pourquoi ce n'est pas un motif.** Aucune decision du depot ne fixe de hauteur de viewport
minimale : `DEC-0007` ne parle que de largeur. Je ne vais pas fabriquer une barre en cours d'audit
pour la faire porter sur un increment qui n'a pas pu s'y preparer. C'est donc un **constat neuf et
date du 2026-08-10**, a instruire par `camille-po` comme une story, et il concernera tout futur
paquet a plus de trois propositions. Il ne durcit aucune reserve anterieure.

#### 5. Ou est la barre pour `couvert-valide`, puisque personne ne me demande de la prononcer

`DEC-0013` assume que la ligne du referentiel nomme trois temps et que ce paquet n'en couvre qu'un.
Je confirme que ce choix de perimetre est defendable - on n'ouvre pas trois temps a la fois - et je
n'en fais pas un motif. Mais je dis ou est la barre, comme je l'ai fait pour les homophones, pour
qu'elle ne se decouvre pas dans deux sprints :

1. **le futur de l'indicatif et l'imparfait de l'indicatif sont ajoutes**, avec les six personnes
   chacun, sur des verbes frequents des trois groupes comme le present les a deja ;
2. **au moins un item par temps oppose deux temps entre eux** et pas seulement deux personnes d'un
   meme temps. Le motif est de couverture : la competence dit "conjuguer au present, futur et
   imparfait", et un enfant qui n'a jamais eu a choisir *entre* deux temps n'a jamais eu a
   identifier le temps demande. Aujourd'hui les quatre propositions sont toujours au present, donc
   le temps est donne, jamais choisi ;
3. **la marque du temps est reperable dans la phrase** (un complement comme "hier", "demain",
   "tous les jours") plutot que devinable : c'est ce qui rend le point 2 jouable sans ambiguite ;
4. **aucun item n'admet deux lectures correctes**, comme aujourd'hui - et le risque augmente des
   qu'on melange les temps ("hier" avec un present de narration, par exemple), donc j'auditerai ces
   items un par un ;
5. **les indices de ces items nomment la marque du temps** et ne se contentent pas de nommer la
   personne ;
6. et **j'aurai joue l'increment** avant de le dire.

Rien d'autre. Ce qui n'est pas dans cette liste ne bloquera pas `couvert-valide` et ne pourra pas
m'etre oppose comme l'ayant bloque. Le motif de refus de la section 2 est independant de cette
liste : il doit etre corrige quel que soit le sort du futur et de l'imparfait.

#### 6. Reserves - a convertir en stories par camille-po, non bloquantes

Elles ne fondent pas le refus et elles ne le grossissent pas.

1. **Le sujet est toujours un pronom, colle au trou, dans les douze items.** Le meme constat que ma
   reserve 2 sur les homophones : la discrimination se fait sur le mot immediatement a gauche, donc
   sans jamais avoir a lire la phrase. Au CM1, la vraie difficulte est un sujet en groupe nominal
   ("Les élèves de la classe ___") ou un sujet eloigne du verbe ("Les enfants, quand la cloche
   sonne, ___"). En l'etat l'exercice est juste mais il est de consolidation CE2 plutot que
   d'apprentissage CM1. C'est le premier point que je traiterais apres le motif de la section 2, et
   c'est aussi ce qui donnerait sa profondeur au point 2 de la section 5.
2. **L'indice donne la reponse en plus de la methode** ("...: c'est 'lisons'."). Meme remarque que ma
   reserve 10 sur les homophones : apres trois echecs c'est defendable, mais la manche portant
   l'indice devient un point gratuit. Le jour ou le score mesurera quelque chose, l'indice devra
   s'arreter a la manipulation.
3. **Douze items pour dix manches : une partie montre dix items sur douze.** Deux items de
   difference entre deux parties, c'est peu ; l'enfant a pratiquement tout vu des la premiere. Le
   paquet des homophones est a vingt-quatre items pour dix manches et c'est la bonne proportion.
4. **"Elle ___ toujours ses affaires avant de partir en récréation."** est le seul item ou une fille
   est sujet d'une action, et cette action est de ranger. Ce n'est pas grave, ce n'est pas faux, et
   `Elles ___ dans le jardin.` equilibre en partie ; je le signale parce que le remplacement est
   gratuit et qu'on n'ecrit pas douze phrases en laissant celle-la seule.
5. **"récréation" apparait dans trois items sur douze** (8, 9, 10), et "école" dans deux. Sur un
   paquet aussi court, cela donne une impression de decor unique. Sans consequence
   d'apprentissage.
6. **L'accueil ne dit pas a quel niveau s'adresse le jeu.** "Le bon verbe" n'annonce pas le CM1
   alors que "Calcul CP" annonce le CP. Reserve deja ouverte sur les deux autres jeux de francais
   (critere de `camille-po` dans cette fiche) : elle n'est **pas** traitee, et elle empire, puisque
   l'accueil propose desormais deux jeux de CM1 non marques a cote d'un jeu de CP marque. Un enfant
   de CP peut ouvrir celui-ci. Question produit pour `camille-po`, pas un motif.

#### 7. Suites hors de ma main

- `squad/programme/MATRICE-couverture.md`, ligne `francais | CM1 | Orthographe et grammaire |
  Conjuguer les verbes fréquents au présent, futur et imparfait de l'indicatif` : **reste
  `en cours`**, et le Done pedagogique n'est pas accorde. Je n'ai pas edite cette ligne, elle
  appartient a `camille-po`.
- Un refus renvoie l'increment a `camille-po`, qui en fait du backlog. Il ne bloque pas le sprint
  suivant, et le contenu deja fusionne n'a pas besoin d'etre defait : les douze enonces, les douze
  couples personne/forme et les sept indices justes sont acquis et servent de base a la correction.
- Rien de ce refus ne concerne l'habillage commun aux jeux : je le tranche dans
  `FICHE-bandit-manchot-additions-cp.md`, qui est depuis s02 le lieu ou je l'ecris.

---

### Arbitrage prealable au developpement - ramener ce paquet a trois propositions, du 2026-08-10

**Ce bloc n'est pas un Done pedagogique**, et il ne modifie pas le refus du 2026-08-10, qui reste
entier : les cinq indices fautifs doivent etre corriges, quel que soit le sort de cette question.
`DEC-0014` reste en vigueur, ce jeu reste hors du catalogue, et il n'y rentrera pas sans un nouveau
Done pedagogique que je prononcerai en jouant.

**Saisine.** `theo-archi` (`ADR-0026`) et `camille-po` (`DEC-0015`) me routent une contradiction entre
deux stories du sprint 7 : le nombre de colonnes de la machine a sous suit le contenu, et ce paquet
est le seul du depot a quatre propositions. Une des sorties possibles est de le ramener a trois, ce
qui est un changement de contenu, donc ma main. L'habillage commun est tranche dans
`FICHE-bandit-manchot-additions-cp.md`, arbitrage du 2026-08-10.

#### Le verdict, en deux phrases parce qu'il en faut deux

**J'accepte de ramener ce paquet a trois propositions par item, et je l'accepte pour son propre motif -
la quatrieme proposition est deja sous le pli aujourd'hui, je l'ai mesure moi-meme le 2026-08-10 - et
non pour faire tenir une mise en page.** Et je constate, mesure sur le rendu, que **cette reduction ne
suffit pas a faire tenir ce paquet en colonnes** : neuf des douze items debordent encore a trois
colonnes, donc personne ne doit la vendre comme la reponse au probleme de largeur.

#### La mesure qui fonde la seconde phrase

Maquette jetable hors du depot, non commitee, sur les deux feuilles reellement servies et la structure
reelle de `fragments/manche.html`, seul delta `.propositions { flex-direction: row }` et
`.proposition { flex: 1 1 0 }`. Capture a une largeur de viewport reellement de 360 px par
`outils/mesurer-rendu.sh 360 <url> <png>`, navigateur designe par la variable `CHROME`. Les douze items
sont rendus avec leur bonne reponse et deux de leurs distracteurs, en trois colonnes de 101 px.

| Item | Trois propositions rendues | A 3 colonnes, 360 px |
|---|---|---|
| 1 `va` | va, vais, vont | tient |
| 2 `lisons` | lisons, lisent, lis | tient |
| 3 `mangez` | mangez, mangeons, mangent | **deborde** - et `mangez` est la bonne reponse, donc irretirable |
| 4 `fais` | fais, fait, font | tient |
| 5 `jouent` | jouent, joue, jouons | **deborde**, `jouent` et `jouons` touchent les bords |
| 6 `dessines` | dessines, dessine, dessinent | **deborde** ; `dessines` et `dessinent` s'affichent tous deux tronques |
| 7 `cherche` | cherche, cherches, cherchent | **deborde** ; `cherche` et `cherches` s'affichent **identiques** |
| 8 `finis` | finis, finit, finissent | **deborde** sur `finissent` |
| 9 `range` | range, ranges, rangent | **deborde** sur `rangent` |
| 10 `courons` | courons, courent, cours | **deborde** sur `courons` et `courent` |
| 11 `prenez` | prenez, prenons, prennent | **deborde** sur `prenez` et `prennent` |
| 12 `chantent` | chantent, chante, chantons | **deborde** sur `chantent` |

**Trois items sur douze tiennent. Neuf ne tiennent pas.** Et les items 6 et 7 sont pires que
disgracieux : deux propositions differentes s'y affichent avec les memes lettres visibles, donc l'item
n'a plus de reponse identifiable. **Un item dont deux propositions sont indiscernables a l'ecran est un
motif de refus a lui seul**, et il le sera si l'increment est livre ainsi.

La reduction a trois n'est donc pas la reponse au probleme de largeur. Ce qu'elle change, c'est
qu'elle en retire une cause aggravante ; la cause principale est la longueur des formes verbales, et
elle ne bougera pas : `dessinent`, `chantent`, `courons`, `prennent` sont le contenu de la competence.
Le choix de disposition reste la saisine de `camille-po` et de `theo-archi`, et je n'empiete pas.

#### Pourquoi j'accepte quand meme la reduction, sur son propre motif

Mon constat du 2026-08-10, section 4 de mon refus ci-dessus, mesure a 360 x 640 : **la quatrieme
proposition est sous le pli, 2 pixels visibles sur 69.** Comme l'ordre des propositions est tire a
chaque manche, la bonne reponse est parfois cette quatrieme, et l'enfant repond alors sans savoir
qu'elle existait. C'est un defaut deja etabli, deja ecrit, deja date, et anterieur au chantier machine
a sous. La reduction a trois le ferme.

Je repete ce que j'avais ecrit alors, pour ne pas etre lue comme deplacant mes poteaux : ce constat
**n'etait pas et ne devient pas un motif de refus**, parce qu'aucune decision du depot ne fixe de
hauteur de viewport minimale. Il est ce qu'il etait : un constat a instruire. Ce qui change
aujourd'hui, c'est qu'on me demande explicitement si j'accepte la reduction, et je reponds oui, en
citant ce constat comme la raison qui la rend bonne independamment de toute mise en page.

#### Le prix pedagogique, chiffre, et pourquoi je l'accepte

**La probabilite de reussir au hasard passe de 25 % a 33 %.** C'est reel et je ne le minimise pas.
Trois raisons pour lesquelles je l'accepte a ce niveau et sur ce paquet :

1. **trois propositions est la norme des deux autres paquets du depot** et une forme parfaitement
   courante au cycle 3. Ce n'est pas une derogation, c'est l'alignement ;
2. **le distracteur que je fais retirer est le moins discriminant des trois**, voir le tableau
   ci-dessous. Le pouvoir de discrimination de l'item **augmente** pendant que la chance augmente :
   l'enfant qui ne sait pas garde trois formes homophones ou quasi homophones entre lesquelles il ne
   peut pas trancher a l'oreille, ce qui est exactement la difficulte de la competence. Je juge le
   solde neutre a positif ;
3. **le score de ce jeu ne mesure encore rien** - c'est ma reserve 2 de la section 6 ci-dessus, non
   levee : l'indice donne la reponse. Ce n'est donc pas un instrument d'evaluation dont je degraderais
   la fiabilite.

**Ce que je n'accepte pas** : que cette reduction devienne une regle generale. Elle vaut pour **ce
paquet et ces douze items**. Un futur paquet a quatre propositions repassera devant moi comme un
contenu neuf, et je ne raboterai pas d'avance.

#### La consigne executable : les douze triplets exacts, aucune question a me reposer

La regle que j'applique : **on garde la bonne reponse et les deux formes les plus proches d'elle a
l'oreille ; on retire celle qui s'en distingue le plus nettement.** Le motif est celui du point 2
ci-dessus. Voici le resultat item par item ; ce sont ces valeurs-la que je valide, et pas un choix
libre de l'auteur.

| Item | Enonce | Bonne reponse | Distracteurs **conserves** | Distracteur **retire** |
|---|---|---|---|---|
| 1 | "Tous les jours, il ___ à l'école." | `va` | `vais`, `vont` | `allez` |
| 2 | "Nous ___ une histoire." | `lisons` | `lisent`, `lis` | `lit` (homophone de `lis`, donc redondant) |
| 3 | "Vous ___ du chocolat." | `mangez` | `mangeons`, `mangent` | `mange` (homophone de `mangent`) |
| 4 | "Je ___ mes devoirs." | `fais` | `fait`, `font` | `faisons` |
| 5 | "Elles ___ dans le jardin." | `jouent` | `joue`, `jouons` | `jouez` |
| 6 | "Tu ___ un dessin." | `dessines` | `dessine`, `dessinent` | `dessinez` |
| 7 | "Je ___ mon cahier dans mon sac." | `cherche` | `cherches`, `cherchent` | `cherchons` |
| 8 | "Tu ___ ton exercice avant la récréation." | `finis` | `finit`, `finissent` | `finissons` |
| 9 | "Elle ___ toujours ses affaires avant de partir en récréation." | `range` | `ranges`, `rangent` | `rangeons` |
| 10 | "Nous ___ dans la cour pendant la récréation." | `courons` | `courent`, `cours` | `court` (homophone de `cours`, donc redondant) |
| 11 | "Vous ___ vos cahiers avant de commencer." | `prenez` | `prenons`, `prennent` | `prend` |
| 12 | "Ils ___ une chanson en rentrant de l'école." | `chantent` | `chante`, `chantons` | `chantez` |

**Ce que j'ai verifie sur ces douze triplets, un par un :**

- **aucun item n'admet deux reponses correctes** apres le retrait. Verifie sur les douze ;
- **les douze enonces ne changent pas**, ni leur ponctuation, ni leurs accents. Le refus du 2026-08-10
  ne portait pas sur eux et ne les rouvre pas ;
- **les six personnes grammaticales restent representees deux fois chacune** : retirer un distracteur
  ne touche pas le sujet de l'enonce ;
- **les trois groupes restent travailles**, et les cinq verbes irreguliers frequents restent ;
- **la difficulte homophonique reste sur les items du premier groupe** : `dessines`/`dessine`/
  `dessinent` (6), `cherche`/`cherches`/`cherchent` (7), `range`/`ranges`/`rangent` (9),
  `chantent`/`chante` (12), `jouent`/`joue` (5). C'est la propriete que je portais au credit du paquet
  le 2026-08-10 et elle est intacte ;
- **les douze indices ne sont pas touches par cette reduction.** Le refus du 2026-08-10 les concerne,
  cette reduction non : ce sont deux corrections independantes du meme paquet, et il faut les deux.

#### Ce dont je reste seule garde sur ce point

1. **Que ces douze triplets soient ceux-la.** `PaquetsFrancaisS04Test` epingle que les propositions
   sont des formes du meme verbe au present ; il ne dira jamais **lesquelles** ni combien. Le choix du
   distracteur retire est un jugement pedagogique et il n'a aucun automate.
2. **Qu'aucune proposition ne soit tronquee a l'ecran.** Non gardable par un automate de ce depot
   (`ADR-0025`, `ADR-0026` section 6), et sur ce paquet ce n'est pas theorique : neuf items sur douze
   debordent aujourd'hui a trois colonnes, deux rendent deux propositions indiscernables.
3. **Que la reduction ne soit pas etendue a un autre paquet sans repasser devant moi.**

#### Suites hors de ma main

- **`DEC-0014` n'est pas leve par cet arbitrage.** Ce jeu reste hors du catalogue. Il y rentrera quand
  j'aurai joue un increment portant **les deux** corrections - les cinq indices, et les trois
  propositions - et prononce un Done pedagogique.
- **Pour `camille-po`** : la disposition qui fait tenir ce paquet en colonnes reste sa saisine avec
  `theo-archi`, et ma mesure ci-dessus est la piece qui montre que la reduction a trois n'y suffit pas.
  Je ne choisis pas entre repli des colonnes, report du jeu et recul sur la cible de largeur.
- **`squad/programme/MATRICE-couverture.md`** : la ligne `francais | CM1 | Orthographe et grammaire |
  Conjuguer les verbes fréquents au présent, futur et imparfait de l'indicatif` **reste `en cours`**.
  Cet arbitrage ne la fait pas bouger et je ne l'ai pas editee.
- **La barre pour `couvert-valide` reste celle de la section 5 de mon refus du 2026-08-10**, mot pour
  mot. Rien de cet arbitrage ne s'y ajoute.

---

### Second arbitrage du 2026-08-10 - j'eteins ma propre reduction a trois propositions

**Ce bloc n'est pas un Done pedagogique**, et il ne touche pas au refus du 2026-08-10 : les cinq
indices fautifs doivent toujours etre corriges, `DEC-0014` reste en vigueur, ce jeu reste hors du
catalogue. Il revient sur **mon acceptation** de la reduction a trois propositions, ecrite plus haut
ce meme jour, apres deux mesures posterieures.

#### Le verdict, en une phrase nette

**Mon acceptation du 2026-08-10 de ramener ce paquet a trois propositions est ETEINTE par deux mesures
posterieures du meme jour : les douze triplets ne sont plus applicables, les quatre propositions
restent, et personne ne doit executer cette reduction en croyant appliquer ma decision.**

#### Les deux mesures qui l'eteignent

**Premiere mesure : mon motif reposait sur une cause que j'avais mal identifiee.** J'avais accepte la
reduction "sur son propre motif - la quatrieme proposition est deja sous le pli aujourd'hui", et
j'avais ecrit le 2026-08-10, section 4 de mon refus ci-dessus :

> *"le defaut est bien lie au passage a quatre propositions et non a l'habillage en general."*

**C'est faux, et l'erreur est de moi.** J'ai mesure les vingt-quatre items de "Le bon petit mot",
paquet a **deux** propositions et jeu du catalogue : trois d'entre eux mettent leurs **deux** boutons
de reponse sous le pli a 360 x 640 quand l'indice s'affiche, et sur l'item 24 il n'en reste rien du
tout. Mesure et methode dans `FICHE-bandit-manchot-homophones-cm1.md`, constat du 2026-08-10. La
cause du defaut est **la longueur de l'enonce a 2 rem**, pas le nombre de propositions. J'avais
generalise depuis "Le bon son", dont les enonces sont des mots seuls en capitales.

**Retirer une proposition n'a donc jamais adresse la cause de ce que je voulais corriger.** C'est le
point qui decide, et il ne depend pas de la proposition d'`ines-design`.

**Seconde mesure : la mise en page, elle, adresse le symptome.** J'ai remesure le repli en deux
rangees d'`ines-design` (`squad/sprints/s07-machine-a-sous/proposition-ines-machine-a-sous.md`) et je
l'ai confirme - detail dans `FICHE-bandit-manchot-additions-cp.md`, second arbitrage du 2026-08-10.
Sur un ecran de manche complet a 360 px, avec indice, sur l'item 8 de ce paquet :

| Etat | Ou tombe la quatrieme proposition | Ce que l'enfant voit |
|---|---|---|
| ma mesure du 2026-08-10, quatre propositions empilees | 638 - 707 | **2 px sur 69**, aucun mot |
| repli en deux rangees, remesure par moi | 585 - 658, glyphes a 611 - 634 | **le mot est entierement lisible**, 6 px de garde sous le pli |

Le defaut que j'avais nomme - *"l'enfant voit trois propositions et un lisere (...) il repondra en
ignorant qu'elle existait"* - est donc **eteint** : le mot se lit, et les 55 px de bouton visibles
au-dessus du pli depassent encore la cible tactile de 44 px. Que la boite ne soit pas entierement
au-dessus du pli ne me gene pas : mon motif etait qu'une proposition soit **invisible**, pas qu'une
boite soit geometriquement entiere.

#### Pourquoi je n'invente pas un motif de remplacement

C'est la partie ou je devrais me surveiller, et je la traite explicitement.

On m'offre la lecture "la reduction reste justifiee pour elle-meme, sur un motif pedagogique que tu
nommerais - par exemple qu'un enfant de CM1 discrimine mieux sur trois propositions que sur quatre".
**Je ne la prends pas, parce que je ne le crois pas, et parce que j'ai ecrit le contraire ce matin
dans cette meme fiche**, au credit de ce paquet :

> *"sur les items du premier groupe, les quatre propositions sont homophones entre elles (`dessine`,
> `dessines`, `dessinent`), ce qui est exactement la difficulte que cette competence doit faire
> travailler - on ne peut pas la resoudre a l'oreille."*

Quatre propositions homophones travaillent cette competence **mieux** que trois. Quatre est une forme
courante et adaptee au cycle 3. Chercher aujourd'hui un motif pedagogique a une reduction dont le
motif reel vient de tomber serait fabriquer une raison apres coup pour sauver une decision - et une
barre qu'on rehabille apres coup fait de ma porte un caprice. Je m'y refuse ici comme je m'y refuse
quand c'est une equipe qui me le demande.

#### Le solde honnete de la reduction, maintenant que son benefice est tombe

| | |
|---|---|
| Benefice | **aucun.** Le pli est adresse par la mise en page, et la largeur aussi |
| Cout mesure | la chance de reussir au hasard passe de **25 % a 33 %** |
| Cout mesure | la difficulte homophonique de la competence est **amputee** sur les items du premier groupe |
| Defaut cree, mesure | sur les items 6 et 7, **deux propositions s'affichent avec les memes lettres visibles** a trois colonnes de 101 px (`cherche` et `cherches` rendant tous deux `cherche`), donc l'item n'a plus de reponse identifiable |

Un changement sans benefice, avec deux couts et un defaut, n'est pas un changement a faire. **Et le
probleme `cherche` / `cherches` n'a pas a etre resolu "autrement" : il disparait avec la reduction qui
le creait.** A quatre propositions dans le repli en deux rangees, aucun des douze items ne tronque,
verifie sur capture.

#### Ce que deviennent mes douze triplets

**Ils ne sont plus applicables. Ils restent ecrits.**

- Le tableau des douze triplets, plus haut dans cette fiche, **n'est pas efface** : un verdict n'est
  jamais efface de ces fiches, meme eteint, et l'histoire de ce paquet doit se lire dans l'ordre. Il
  est desormais a lire comme **la trace d'une decision eteinte le 2026-08-10 par ce bloc**, pas comme
  une consigne.
- **Aucun developpeur ne les applique.** Le paquet
  `adapters/adapter-content/src/main/resources/contenu/cm1-conjugaison-present.yaml` garde ses
  **quatre propositions par item** : une bonne reponse et trois distracteurs, exactement comme
  aujourd'hui. Aucun distracteur n'est retire.
- **La seule correction attendue sur ce paquet reste celle du refus du 2026-08-10** : les cinq indices
  qui enoncent une regle de conjugaison fausse. Elle est inchangee, entiere, et ses criteres sont deja
  ecrits.
- **La porte est fermee derriere cette reduction.** Si un increment futur fait reapparaitre une
  proposition invisible ou tronquee sur ce paquet, la reponse n'est **pas** de ressortir ces triplets :
  c'est de traiter la mise en page, parce qu'une reponse de mise en page existe desormais et qu'elle
  est mesuree. Je l'ecris pour qu'on ne me redemande pas cette reduction comme un raccourci dans deux
  sprints.

#### Ce que cela change au perimetre du sprint, puisque sofia-sm n'a pas gele

**Une sortie de moins, et elle n'en etait pas une.** `camille-po` avait trois sorties possibles pour
faire tenir ce paquet ; il lui en reste deux, le repli de mise en page ou le report du jeu hors du
sprint, **et je ne choisis pas entre elles.** Ce que ma decision lui apporte :

- la reduction du contenu n'est plus une option, et elle n'aurait de toute facon pas suffi - mesure du
  premier arbitrage : neuf items sur douze debordaient encore a trois colonnes ;
- **le repli de mise en page est valide de mon cote**, sous les deux conditions du second arbitrage
  (contraste de 4,5:1 pendant l'animation, voile confine dans la boite sans `overflow: hidden`) et
  avec la reserve chiffree des 3 px d'air sur `mangeons` ;
- **la correction des cinq indices reste independante des quatre stories du chantier** et prete a
  developper, comme `DEC-0015` le dit deja. Rien de ce bloc ne la retarde.

#### Ce que je declare degrade

- **Le chantier n'est pas ecrit.** Le repli en deux rangees n'existe dans aucun fichier du depot :
  j'ai mesure une maquette jetable, non commitee, portant les deux regles `:has()` d'`ines-design` sur
  les deux feuilles reellement servies. Si ce qui est livre differe, mes chiffres ne valent plus et
  cette extinction est a reexaminer - **mais le premier motif, l'erreur de cause, tient
  independamment de toute mise en page.**
- **Les 6 px de garde sous le pli sur l'item 8 dependent de la police de ma machine**, et l'item 9
  (l'enonce le plus long de ce paquet) reste sous le pli avec son indice, repli ou non - meme queue de
  defaut que sur "Le bon petit mot", meme constat, meme destinataire.
- **Je n'ai pas rejoue ce paquet.** Il est hors catalogue et son refus n'est pas leve ; il n'y a rien a
  jouer. Le Done pedagogique de ce jeu se prononcera quand j'aurai joue un increment portant la
  correction des cinq indices, et pas avant.

#### Suites hors de ma main

- **`DEC-0014` n'est pas leve.** Ce jeu reste hors du catalogue jusqu'a un nouveau Done pedagogique.
- **Pour `camille-po`** : sa decision de perimetre est intacte et lui appartient entierement. Le seul
  changement est qu'une des sorties qu'elle avait a peser est retiree par moi, avec son motif.
- **Pour `sofia-sm`** : si un developpeur a deja commence a retirer des distracteurs de ce paquet sur
  la foi de mon arbitrage precedent, ce travail est a defaire. C'est le risque exact que ce bloc existe
  pour fermer.
- **`squad/programme/MATRICE-couverture.md`** : la ligne `francais | CM1 | Orthographe et grammaire |
  Conjuguer les verbes fréquents au présent, futur et imparfait de l'indicatif` **reste `en cours`**.
  Je ne l'ai pas editee.
- **La barre pour `couvert-valide` reste celle de la section 5 de mon refus du 2026-08-10**, mot pour
  mot. Ce bloc n'y ajoute rien et n'en retire rien.
