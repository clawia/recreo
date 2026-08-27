# Proposition ines-design - REP-8 (l'indice hors de vue apres un echec, catalogue entier)

- **Auteure** : ines-design. **Date** : 2026-08-27. **Sprint** : s21 (ouverture), branche
  `round-two`, tete `478e04d`.
- **Mandat de ce document** : `REP-8` (`squad/conformite/FICHE-bandit-manchot-reperes-cm1.md`,
  verdict du 2026-08-27 ; `DEC-0050`, section 4) est routee a `camille-po`, `ines-design` et
  `theo-archi` comme story de catalogue, pas de contenu. Je propose ; je ne tranche rien. Le Done
  pedagogique reste a `nadia-prof`, une decision d'architecture ou d'`ADR` reste a `theo-archi`,
  le perimetre et les criteres d'acceptation restent a `camille-po`.
- **Ce que je ne fais pas** : je ne touche a aucun fichier sous `domain/`, `application/`,
  `bootstrap/`. Je ne modifie ni gabarit ni feuille de style ici (aucun changement de production
  livre par ce document) - toute valeur citee ci-dessous est une mesure sur une regle **injectee**
  dans le rendu, jamais commitee dans `recreo.css`.

## 0. Perimetre et methode, avant tout chiffre

**Le jar `bootstrap/target/recreo.jar` n'a pas ete reconstruit.** Verifie plutot que suppose : les
deux derniers commits de la branche (`1c9c313`, `478e04d`) ne touchent aucun fichier sous
`domain/`, `application/`, `adapters/`, `bootstrap/` (`git diff --name-only 1c9c313..478e04d --
adapters domain application bootstrap` rend aucun fichier, le 2026-08-27) ; l'entree
`bandit-manchot-reperes-cm1` de `catalogue.yaml` (ajoutee par `a266b4c`, avant le jar) et
`cm1-reperes.yaml`, `recreo.css`, `fragments/manche.html` sont **identiques en `sha256`** entre le
jar deja present dans `bootstrap/target/` et l'arbre de travail. Le jar mesure est donc celui de
la tete de branche, sans reconstruction et sans consommer le reacteur.

**Application servie** : `java -jar bootstrap/target/recreo.jar --server.port=<port>` (JDK 21 ou
plus recent - variable `JAVA_HOME` ou toolchain declaree, jamais un chemin de poste).

**Outillage** : `squad/outils/nadia-prof/auditer-paquet.py`, **non modifie**, appele une fois par
jeu :

```
CHROME=<chemin local de Chrome/Chromium> \
PAQUET=adapters/adapter-content/src/main/resources/contenu/<paquet>.yaml \
python squad/outils/nadia-prof/auditer-paquet.py http://<hote>:<port> <id_du_jeu> <nombreDeManches> <sortie.json>
```

`<nombreDeManches>` vient de `catalogue.yaml` pour chaque jeu (7 pour
`bandit-manchot-complements-10-cp`, 10 pour les seize autres jeux `type: question`). Viewport de
reference `744 x 551` (`DEC-0040`), fixe par le script.

**17 jeux mesures sur 18 servis** (2026-08-27). `bandit-manchot-additions-cp` est exclu, **meme
motif que `theo-archi`** (`mesure-budget-vertical.md`, section 0) : son paquet est `type:
additions-bornees`, combinatoire, sans `bonneReponse` lisible dans un fichier de paquet - l'outil
ne peut pas distinguer une reponse fausse d'une reponse juste pour ce jeu sans deviner. Je n'ai pas
tente de contourner cette limite par un outil ad hoc : une premiere tentative en ce sens a echoue
de facon repetee sur ce poste (le bouton de demarrage n'etait pas trouve par le navigateur pilote),
et le cout de la deboguer n'etait pas justifie par la valeur d'un seul jeu deja documente comme
non mesurable par la meme methode que celle de `theo-archi`. `nadia-prof` a mesure cet unique jeu
autrement dans son propre balayage (`squad/conformite/releves-s20/s20-pli-du-catalogue-744x551.json`,
entree `bandit-manchot-additions-cp` : 1 item vu, indice a 505,19 px, au-dessus du pli) ; je cite
son chiffre sans le revendiquer comme verifie par moi.

**Releve commite** : `squad/conformite/releves-s21/s21-pli-du-catalogue-744x551.json` (17 jeux,
167 items, format aligne sur celui de `nadia-prof` pour rester comparable). Les releves candidats
des sections 2.2 et 2.3 sont commites a cote (`s21-candidat-*-grands-nombres.json`).

## 1. Ma mesure du catalogue servi aujourd'hui - 18 jeux, 17 mesurables

**Commande exacte** : celle de la section 0, rejouee une fois par jeu, le 2026-08-27, sur
`round-two` a `478e04d` (code identique a `1c9c313`, verifie ci-dessus).

| Constat, 2026-08-27, 167 items vus sur 17 jeux mesurables sur 18 servis | Chiffre |
|---|---|
| Jeux ou l'**indice** passe sous le pli sur au moins un item vu | **9 sur 17** |
| Items vus dont l'indice est sous le pli | **56 sur 167** |
| Jeux ou le **verdict** passe sous le pli sur au moins un item vu | **3 sur 17** |
| Items vus dont le verdict est sous le pli | **6 sur 167** |
| Pire enfoncement de l'indice sous le pli | **164,19 px** (`bandit-manchot-grands-nombres-cm1`, bas a 715,19 px) |

Detail par jeu (indice sous le pli / items vus) : `accord-sujet-verbe-cm1` 10/10,
`problemes-additifs-cp` 10/10, `perimetre-cm1` 9/10, `grands-nombres-cm1` 6/10, `synonymes-cm1`
5/10, `reperes-cm1` 10/10, `accords-groupe-nominal-cm1` 3/10, `homophones-cm1` 2/10,
`conversions-unites-cm1` 1/10. Les huit autres jeux mesures (0 item sous le pli sur les items vus) :
`antonymes-cm1`, `comparer-nombres-cp`, `complements-10-cp`, `conjugaison-present-cm1`,
`le-bon-son-cp`, `nature-mot-cp`, `soustractions-cp`, `tables-multiplication-cm1`.

### Ma population n'est pas celle de `nadia-prof`, et je dis pourquoi avant de comparer un chiffre

Sa mesure du 2026-08-27 (`FICHE-bandit-manchot-reperes-cm1.md`, verdict du meme jour, section 3 ;
`DEC-0050`, section 4) porte sur **17 jeux** aussi, mais **pas les memes 17** : son audit a eu lieu
**avant** l'execution de `DEC-0050` (`a266b4c`, 10:07) - son catalogue comptait
`bandit-manchot-additions-cp` (mesurable par sa methode a elle) et ne comptait pas encore
`bandit-manchot-reperes-cm1`. Le mien, execute apres, compte l'inverse. Les deux ensembles de 17
partagent donc **16 jeux**, pas 17.

**Sur les 16 jeux communs, les chiffres se recoupent exactement sur douze d'entre eux** (0 item
sous le pli des deux cotes sur sept jeux ; memes valeurs sur `conversions-unites-cm1` 1/10,
`homophones-cm1` 2/10, `accords-groupe-nominal-cm1` 3/10, `problemes-additifs-cp` 10/10,
`grands-nombres-cm1` 6/10 - **le pire bas d'indice de ce dernier, 715,19 px, est identique au
centieme entre les deux mesures**, malgre deux tirages independants).

**Deux jeux divergent, et la cause est mesuree, pas supposee** : un tirage aleatoire sur un paquet
plus grand que le nombre de manches jouees.

- `bandit-manchot-accord-sujet-verbe-cm1` : elle compte 9 sur 10, je compte 10 sur 10. Le paquet
  `cm1-accord-sujet-verbe.yaml` porte **21 phrases** pour 10 manches servies. Son tirage a inclus
  *"Toi et ton frere ___ la vaisselle apres le repas."* (indice a 535,19 px, au-dessus du pli,
  enonce court) ; le mien ne l'a pas tire, et les dix phrases que j'ai tirees font toutes plus de
  535,19 px. Aucune des deux mesures n'est fausse : les deux sont des planchers sur un
  echantillon aleatoire d'un paquet plus grand que la manche.
- `bandit-manchot-synonymes-cm1` : elle compte 3 sur 10, je compte 5 sur 10. Le paquet
  `cm1-synonymes.yaml` porte **16 phrases** pour 10 manches. Mon tirage a inclus deux phrases
  qu'elle n'a pas tirees (*"La maman parle a son bebe..."*, *"Le nouveau voisin est..."*),
  toutes deux a 583,19 px - un enonce sur deux lignes, la meme cause que `REP-9` sur
  `cm1-reperes`.
- `bandit-manchot-perimetre-cm1` : nos comptes d'indice sous le pli concordent (9 sur 10), mais
  son **verdict** sous le pli (1 item, *"Le perimetre d'une figure, c'est..."*, verdict a
  606,19 px) n'est pas dans mon tirage de dix, sur un paquet de **22 items**. C'est la meme cause :
  cet enonce est le plus long du paquet, et mon tirage aleatoire ne l'a pas pris.

**Aucune divergence n'est une erreur de methode : les deux mesures sont des planchers
echantillonnes, jamais un inventaire**, exactement comme `nadia-prof` le nomme elle-meme pour la
sienne (`avis-nadia-cloture.md`, "Ce que je n'ai pas mesure"). Un paquet plus grand que sa manche
donne un tirage different a chaque partie ; c'est attendu, et c'est pour cela qu'aucune des deux
mesures ne remplace l'autre.

## 2. Pistes de correction, chiffrees chacune

### 2.1 Le contrat deja etabli a s20 (je verifie le chiffre a la source, je ne le reprends pas tel quel)

`nadia-prof` a mesure, le 2026-08-27, sur `cm1-reperes` (`FICHE-bandit-manchot-reperes-cm1.md`,
verdict du meme jour, section 3, "Second fait...") : a la largeur offerte de 680 px, **trois
propositions tiennent sur une seule rangee si chaque libelle fait au plus 10 caracteres** ; a
11 caracteres, elles retombent sur deux rangees. **Une rangee au lieu de deux remonte l'indice de
90 px sur un enonce d'une ligne**, mais seulement de **2,19 px sous le pli au mieux sur un enonce
de deux lignes** ("une rangee ou pas"). Un enonce qui tient sur une ligne au lieu de deux vaut
**48 px** de plus (`REP-9`).

Je verifie ce chiffre a sa source (le releve cite,
`s20-longueur-de-libelle-atteignable-744x551.json`) plutot que de le recopier : il porte
exactement les deux memes valeurs, 90 px et 2,19 px, et je le confirme au lieu de le re-deriver.

**C'est le lever le plus puissant de cette proposition, et il n'est pas le mien** : il est deja
etabli, mesure et chiffre. Ce que j'ajoute en section 3 est de le transformer en **contrat
d'entree** pour les paquets neufs, plutot que de le laisser un diagnostic sur un seul paquet deja
livre.

### 2.2 Deux leviers de forme neufs, mesures par regle injectee (jamais commitee)

`DEC-0050` cite deux termes comme "ce qui reste, sans etre ferme" : *"la police (2rem, en gras) et
le rembourrage (0,75rem/1,25rem) de `.proposition` a moins de quatre colonnes"*. Je verifie leur
source dans `recreo.css` plutot que de les recopier du brief : la police est verrouillee par un
garde-fou pedagogique (`TailleDesTextesEtBoutonsTest`,
`leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul`, S3-4 - "le chiffre de reponse doit peser au
moins autant que `.enonce`") que je ne touche pas ici (section 2.4). Le rembourrage, lui, **n'a
aucun plancher numerique code** : `TailleDesTextesEtBoutonsTest` protege `padding` contre un
ecrasement par une classe tierce, jamais contre une reduction posee par la feuille maison
elle-meme. C'est le seul des deux termes que j'ai mesure ici.

J'ajoute un second terme, non cite par le brief mais du meme registre - une marge, pas un contenu :
`.score { margin: 0.5rem 0 }`, le seul autre terme du bucket de 80 px chiffre par `theo-archi`
(`mesure-budget-vertical.md`, section 3) qui **ne touche que les ecrans de jeu**, jamais
`accueil.html` (`.score` n'apparait que dans `fragments/manche.html` et `fragments/retour.html`,
verifie par lecture des gabarits). Les deux autres termes de son bucket -
`.entete` et `.zone-jeu` - habillent aussi l'accueil : je ne les propose pas ici, ce n'est pas une
regle de mecanique de jeu mais une regle d'ecran partage, hors de mon perimetre de ce document
(section 4).

**Methode de mesure** : `squad/outils/ines-design/mesurer-candidats-rep-8.py` (cree par ce
document, meme protocole qu'`auditer-paquet.py`, aucune ligne de production touchee - la regle
candidate est injectee dans le `<head>` du document rendu). Verifie sur le jeu le plus enfonce,
`bandit-manchot-grands-nombres-cm1` (pire deficit 164,19 px), avant et apres injection.

| Candidat | Regle injectee | Delta mesure sur le bas de l'indice |
|---|---|---|
| A - marge du score | `.score{margin:0.25rem 0}` | **-8 px**, constant, sur 5 items independants (releve `s21-candidat-score-grands-nombres.json`) |
| B - rembourrage vertical de `.proposition` | `.proposition{padding:0.5rem 1.25rem}` | **-8 px par rangee de propositions** : -8 px sur un item a une rangee, -32 px sur un item a trois rangees (releve `s21-candidat-padding-grands-nombres.json`) |
| A + B combines | les deux regles ensemble | additif, verifie et non suppose : -16 px sur un item a une rangee, -32 px sur un item a trois rangees (releve `s21-candidat-combine-grands-nombres.json`) |

**Ce que ce lever ferme, applique a la population entiere de la section 1** (calcul sur le releve
commite, pas sur le seul jeu de test) : le candidat A seul (marge du score, -8 px) fait remonter
au-dessus du pli **26 des 56 items "indice sous le pli"**, sur **5 des 9 jeux touches** - dont
trois jeux **entierement** fermes sur cette propriete parmi les items vus :
`bandit-manchot-perimetre-cm1` (9 sur 9), `bandit-manchot-problemes-additifs-cp` (10 sur 10),
`bandit-manchot-homophones-cm1` (2 sur 2). **Sur l'indice, le candidat B (rembourrage) n'ajoute
aucune fermeture de plus sur cette population precise** (toujours 26 sur 56, verifie par le meme
calcul) : les items a plusieurs rangees qui restent sous le pli (`grands-nombres-cm1` a trois
rangees, `reperes-cm1` et `accords-groupe-nominal-cm1` a deux) ont un deficit trop grand pour le
gain combine de ce candidat sur les items tires aujourd'hui - **son effet reste reel et
proportionnel au nombre de rangees** (mesure en 2.2), il ne suffit simplement pas, seul, a faire
franchir le pli a ces items-la. Sur le **verdict** (6 items sous le pli, section 1), en revanche,
le candidat A seul referme les 2 items de `bandit-manchot-conjugaison-present-cm1` (deficit
7,19 px, sous le gain de 8 px du score seul) ; le candidat B **combine** au candidat A referme en
plus les 2 items de `bandit-manchot-reperes-cm1` (deficit 13,19 px, 2 rangees, gain combine
24 px) - une fermeture que le score seul (8 px) ne rendait pas.

**Ce que ce lever ne ferme pas** : sur les 56 items de la population, **30 restent sous le pli**
apres les deux candidats combines, dont les deux items les plus enfonces de
`bandit-manchot-grands-nombres-cm1` (deficit residuel de 102,19 et 132,19 px, sur les items a
trois rangees) - ce lever est reel et verifie, mais petit face au lever de la section 2.1 (jusqu'a
90 px sur un seul item).

**Cout** : aucun plancher pedagogique connu n'est touche (le rembourrage n'en porte pas ; la marge
du score non plus). Le cout est visuel et humain, pas mesurable par un script : un score plus
serre contre l'en-tete, des boutons de reponse moins genereux au toucher. C'est une proposition,
pas une valeur figee - `nadia-prof` reste seule a juger si l'ecart reste assez respirant, exactement
comme elle l'a fait pour la marge de `.consigne`/`.enonce` a S10 (meme registre de decision).

### 2.3 Ce que je nomme sans le tester ni le proposer

- **La police de `.proposition` (2rem)**. `leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul` exige
  qu'elle reste au moins aussi grande que `.enonce` (S3-4, reserve pedagogique de `nadia-prof`).
  La faire descendre exige de rouvrir sa reserve, jamais un geste unilateral de ma part - je ne
  l'ai pas mesuree.
- **Le bucket de 80 px de `theo-archi`** (`.entete` + `.zone-jeu` + `.score`) - je ne reprends que
  le tiers qui n'habille que les ecrans de jeu (`.score`, section 2.2). Les deux autres termes
  touchent `accueil.html` : les proposer ici deguiserait une regle d'ecran partage en regle de
  mecanique de jeu, exactement l'erreur que mon mandat me demande de nommer et de remonter plutot
  que de commettre.
- **Une precision sur une citation du brief.** Le brief cite `ADR-0029` comme la decision qui
  "gouverne cette forme" (rembourrage/police du repli a quatre colonnes). Verifie a la source :
  `ADR-0029` porte sur le decalage d'animation par colonne (`animation-delay`), pas sur la police
  ni le rembourrage de `.proposition` - le commentaire de `recreo.css` (ligne 328 et suivantes)
  attribue lui-meme le plancher de 1,875rem a l'arbitrage prealable de `nadia-prof` dans
  `FICHE-bandit-manchot-conjugaison-present-cm1.md`, section C (S8-5), pas a `ADR-0029`. Je ne
  corrige pas la fiche de `nadia-prof` (hors de mon mandat), je signale seulement la source que
  j'ai verifiee pour ma propre proposition, pour qu'un lecteur qui suivrait cette reference-la
  ne cherche pas au mauvais endroit.

### 2.4 Garde-fous verifies avant d'ecrire ces chiffres - lus, non executes

**Je n'ai pas lance Maven** (consigne de ce sprint, reacteur partage). J'ai verifie mes deux
candidats en lisant `TailleDesTextesEtBoutonsTest.java` ligne a ligne plutot qu'en le laissant
juger a ma place :

- `leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul` et
  `laPoliceDuRepliAQuatreColonnesEstUneExceptionAuPlancherGeneralDeDeuxRem` : je n'ai touche
  aucune valeur de police, les deux restent intactes.
- `verifierCibleTactile` (`CIBLE_TACTILE_REM = 2,75rem`) : `min-height` de `.proposition` n'est
  pas dans ma proposition ; `min-height` plafonne la boite independamment du rembourrage
  (`display: inline-flex; align-items: center`), donc une reduction de `padding` ne peut pas faire
  descendre la cible tactile sous 44 px tant que `min-height` reste declare - ce que je n'ai pas
  touche.
- `verifierPlancherDeLisibilite` (1,25rem) : ne s'applique pas a `.proposition` (deja a 2rem) ni a
  `.score` (deja a 1,25rem, inchange - je ne touche que sa marge).
- `verifierQueLeTiersNeDefaitPasLesPlanchers`, `lesSeulesTaillesDePropositionSontLesDeuxReglesNommees` :
  portent sur `font-size`, jamais sur `padding` ni `margin` ; hors du perimetre de mes deux
  candidats.

Si `sofia-sm` ou `theo-archi` veulent une confirmation executee plutot que lue, la commande est
`mvn -pl adapters/adapter-web -am test -Dtest=TailleDesTextesEtBoutonsTest` - je ne l'ai pas
lancee moi-meme pour ne pas retenir le reacteur.

## 3. Le contrat de forme pour un paquet ajoute a s21

Ce que je propose a `camille-po` pour ses criteres d'acceptation, pour qu'un paquet neuf naisse
au-dessus du pli sans repasser par une reprise :

1. **Trois propositions par item au plus** (jamais quatre). Cout mesure sur `cm1-reperes` avant sa
   correction : quatre propositions retombaient dans le repli a quatre colonnes (police 1,875rem
   force, deux rangees), qui a lui seul poussait le bas du cabinet a 492 px sur 551
   (`FICHE-bandit-manchot-reperes-cm1.md`, "Correction du 2026-08-26") - hors de portee de tout
   raccourcissement de libelle a ce nombre de colonnes.
2. **Chaque libelle de proposition tient en 10 caracteres au plus**, quand l'item en compte trois
   (section 2.1) - une contrainte sur la forme du libelle, jamais sur le fond que choisit
   `nadia-prof`.
3. **Chaque enonce tient sur une seule ligne a 744 px de large** (verifie au rendu, pas suppose a
   la lecture du texte).
4. **Porte d'acceptation, avec l'outillage deja au depot, aucun outil neuf a ecrire** : avant de
   fusionner un paquet neuf, jouer
   `python squad/outils/nadia-prof/auditer-paquet.py <url> <id_du_jeu> <nombreDeManches> <sortie.json>`
   a 744 x 551 et verifier que **`itemsIndiceSousLePli` et `itemsVerdictSousLePli` sont vides sur
   tous les items vus**. C'est une porte de forme, pas un Done pedagogique : elle ne remplace ni
   ne devance l'avis de `nadia-prof`, elle evite seulement qu'un paquet neuf entre au catalogue en
   portant deja le defaut que ce sprint ferme.

Les regles 1 a 3 ne fixent aucun libelle ni aucun enonce a la place de `nadia-prof` ou de
`lea-dev` : elles disent seulement la forme dans laquelle un contenu doit tenir, exactement le
registre que mon mandat me reserve.

## 4. Ce que cette proposition ne ferme pas

- **Les deux items aux enonces les plus longs de `cm1-reperes`** (*"Nous partons demain matin pour
  la montagne"*, *"Pourquoi les feuilles tombent-elles en automne"*) ne sont pas raccourcissables
  (instruction de `nadia-prof`, *"la matiere est saine"*) : le contrat de la section 3 ne s'applique
  qu'aux paquets **neufs**, il ne rouvre pas `cm1-reperes`, deja `VALIDE SOUS RESERVE`.
- **La limite `--hide-scrollbars`, je ne la leve pas.** `nadia-prof` l'a nommee comme la sienne
  (toutes ses campagnes, et la mienne par heritage du meme outillage) : le rendu avec une barre de
  defilement classique peinte n'est mesure par aucun des deux. Ce n'est pas une decision de forme
  que je peux trancher seule - lever ce drapeau est un choix d'outillage de mesure (`theo-archi`,
  `nadia-prof`), pas une regle de `recreo.css`. Ce que je peux dire depuis mon siege : rien dans
  `recreo.css` ne force `overflow: hidden` sur `body` ni sur le document (verifie par lecture,
  aucune occurrence) - le defilement, quand il existe, n'est pas bloque par la feuille maison.
  Reste ouverte la question qui n'est ni la mienne ni celle d'un script : **un enfant de CP ou de
  CM1 devine-t-il qu'il peut faire defiler pour voir l'indice ?** C'est un jugement humain, que
  `nadia-prof` a deja nomme comme le sien a nommer, pas a mesurer.
- **Les 30 items sur 56 qui restent sous le pli meme apres les deux candidats de la section 2.2
  combines.** Le lever le plus puissant reste celui de la section 2.1 (jusqu'a 90 px), deja
  etabli et pas invente ici ; je ne pretends pas fermer `REP-8` avec ce seul document.
- **Le bucket de 80 px de `theo-archi` au-dela de `.score`** : `.entete` et `.zone-jeu` touchent
  l'accueil, hors du perimetre "mecanique de jeu" que je me suis fixe ici (section 2.3).
- **La police de `.proposition`** : gelee par la reserve pedagogique de `nadia-prof` (S3-4), pas
  rouverte par ce document.

## Ce que je route

- **`camille-po`** : le contrat de la section 3, pour ses criteres d'acceptation des paquets de
  s21 ; les deux comparaisons chiffrees de la section 1 pour arbitrer si `REP-8` reste une story
  de catalogue non affectee ou entre au sprint.
- **`nadia-prof`** : les deux candidats de la section 2.2 (marge du score, rembourrage de
  `.proposition`) restent **des propositions**, jamais un Done - elle seule juge si l'ecart visuel
  reste assez respirant pour l'enfant. La question ouverte sur le defilement (section 4) lui
  revient telle qu'elle l'a nommee.
- **`theo-archi`** : la precision de source sur `ADR-0029` (section 2.3) - je ne demande aucun
  amendement, je signale seulement que ma propre proposition ne s'appuie pas sur cet `ADR`-la.
  Si le bucket de 80 px (`.entete`/`.zone-jeu`) doit un jour etre ouvert pour `REP-8`, la decision
  d'y toucher lui revient, pas a moi seule.
- **`sofia-sm`** : deplacement de ce fichier vers `squad/sprints/s21-<slug>/` si le sprint s21
  s'ouvre formellement avec ce chantier ; le jar n'a pas ete reconstruit (section 0), donc aucune
  contention sur le reacteur.

## Impact

- `squad/conformite/proposition-ines-rep-8.md` : cree (ce fichier).
- `squad/conformite/releves-s21/s21-pli-du-catalogue-744x551.json` : cree (17 jeux, 167 items).
- `squad/conformite/releves-s21/s21-candidat-padding-grands-nombres.json` : cree.
- `squad/conformite/releves-s21/s21-candidat-score-grands-nombres.json` : cree.
- `squad/conformite/releves-s21/s21-candidat-combine-grands-nombres.json` : cree.
- `squad/outils/ines-design/mesurer-candidats-rep-8.py` : cree (outil de mesure par regle
  injectee, aucune ligne de production touchee).
- Aucun fichier sous `domain/`, `application/`, `bootstrap/` ni aucun gabarit ou feuille de style
  de production n'est modifie par ce document.
