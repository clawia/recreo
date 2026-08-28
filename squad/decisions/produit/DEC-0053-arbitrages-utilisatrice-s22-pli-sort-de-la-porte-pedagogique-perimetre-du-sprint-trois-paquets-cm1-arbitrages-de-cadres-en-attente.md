# DEC-0053 - Arbitrages de l'utilisatrice pour s22 : le pli sort de la porte pedagogique sur tout
# le catalogue ; perimetre du sprint (trois paquets deja arbitres, priorite S13-2/S13-3/S17-2) ;
# borne de capacite rechiffree ; deux arbitrages prealables poses d'avance pour la suite

- **Type** : arbitrage de perimetre (utilisatrice, deux arbitrages) et decision de perimetre de
  sprint (`camille-po`)
- **Date** : 2026-08-28
- **Auteur** : camille-po
- **Sprint** : s22 (ouverture)
- **Competence programme visee** : transverse - pas de contenu pedagogique pour les deux arbitrages
  de l'utilisatrice (arbitrage 1 et 2) et pour le rechiffrage de capacite (section 3). Les lignes
  candidates retenues pour le perimetre du sprint sont instruites section 4, chacune avec son lien
  de referentiel.
- **Statut** : Acceptee

## Contexte

s21 se cloture a **18/43 (41,9 %)** (`DEC-0052`, amendement du 2026-08-28, second). La retro de s21
propose cinq amendements a `squad/README.md`, aucun tranche par ce document (`retro.md`), dont deux
qui me concernent directement : distinguer, dans un chiffrage de capacite, un cycle de refus cause
par le contenu livre d'un cycle cause par une specification prealable fausse de `nadia-prof`
elle-meme (point 4, a trancher par moi et `sofia-sm`) ; et je m'etais moi-meme engagee, dans
`DEC-0052` section 11, a ne pas rechiffrer la borne de capacite "a chaud" sur le cas qui venait de
se produire - ce rechiffrage reste du, et c'est l'objet de la section 3 ci-dessous.

L'utilisatrice ouvre le sprint 22 aujourd'hui avec une consigne unique : **couverture maximale du
catalogue attendu**, avant une livraison test qui doit arriver la semaine prochaine. Deux
arbitrages sont rendus dans la conversation d'ouverture.

## Decision

### Arbitrage 1 (utilisatrice) - le pli sort de la porte pedagogique, pour tout le catalogue

Motif retenu, mot pour mot : "la mesure n'est plus necessaire a la validation du jeu meme si ca
peut ouvrir une dette croissante sur le reste du catalogue". Portee confirmee explicitement :
**oui, pour tout le catalogue, paquets neufs de s22 compris** - pas seulement les 64 items CM1
deja mesures sous le pli a la cloture de s21 (`DEC-0051`, amendement du 2026-08-28, troisieme).

**Consequence pour `nadia-prof`** : elle ne prononce plus de `REFUSE` sur le motif "un element du
jeu (indice, verdict, proposition) reste hors de vue sans defiler" - qu'il s'agisse de l'indice
(`REP-8`), du verdict, ou d'un autre element du meme bloc. Ce motif est desormais consigne en
reserve non bloquante, comme les reserves deja en usage sur ce produit (`REP-7`, `REP-9`, `REP-10`,
`ASV-1`, `AGN-1`, `SYN-16`, etc.).

**`REP-8` reste ouverte, nommee, et sa dette est desormais explicitement croissante et assumee** :
les paquets neufs de s22 peuvent l'agrandir, ce qui n'aurait pas ete tolerable sous l'arbitrage de
`DEC-0051` (fermer le pli avant tout ajout de jeu, precisement pour ne pas faire grossir cette
dette). Cet arbitrage 1 **remplace** celui de `DEC-0051` sur ce point precis - `DEC-0051` n'est pas
reecrite, elle reste exacte pour l'etat qu'elle decrivait le 2026-08-27 ; c'est l'utilisatrice qui
choisit aujourd'hui un arbitrage different, en connaissance du prix nomme par `DEC-0051` (dette qui
grossit) et du motif qui le justifie aujourd'hui (livraison test la semaine prochaine).

**Les 4 items sans levier connu** (obstacle 21 de s21, `bandit-manchot-grands-nombres-cm1` x2,
`bandit-manchot-reperes-cm1` x2, nommes par `nadia-prof` elle-meme comme un fait a porter et non
une story a ecrire) suivent le meme regime : reserve non bloquante, aucun statut de couverture
n'en depend.

### Point de vigilance instruit avant d'ecrire la consequence ci-dessus : ce que cet arbitrage ne touche pas

Cet arbitrage porte sur un **critere de validation pedagogique** - ce que `nadia-prof` peut
prononcer comme Done pedagogique - et seulement sur celui-la. Il ne touche pas les garde-fous et
`ADR` qui encodent la forme en regle **technique**, executee par la CI independamment de tout
jugement pedagogique :

- **`ADR-0017`** (comparer deux boutons d'un meme ecran, `LActionNeDeclarePasMoinsQueLaSortieTest`)
  reste en vigueur : c'est une regle de hierarchie visuelle entre deux elements d'un meme ecran, pas
  une regle de visibilite sans defilement.
- **`ADR-0063`** (au plus trois propositions par item, `AuPlusTroisPropositionsParItemTest`) reste
  en vigueur : c'est une regle de composition d'item ecrite comme test ArchUnit/JUnit dans le meme
  commit, independante de tout audit de `nadia-prof`. Un paquet qui la viole ne passe pas `mvn
  verify`, quel que soit l'arbitrage pedagogique du jour.
- **Les tests de decalage de colonne** et **l'outillage de mesure de `outils/`** (`mesurer-rendu.sh`,
  les scripts de `squad/outils/nadia-prof/`) restent des instruments valides : ce que cet arbitrage
  change, c'est l'usage que `nadia-prof` fait de leurs resultats pour prononcer un Done, pas leur
  existence ni leur exactitude.

**Ce que je retiens, et ce que je route.** Le Done **technique** (CI verte, `mvn verify`) et le Done
**pedagogique** restent deux portes independantes, exactement comme `CLAUDE.md` les definit ; cet
arbitrage assouplit la seconde sur un motif precis, il ne touche pas la premiere. Si un jour cet
arbitrage devait avoir une consequence sur un test ArchUnit existant (par exemple si un garde-fou
technique venait a dependre implicitement d'une notion de "au-dessus du pli" que je n'ai pas trouvee
en le cherchant), ce serait a `theo-archi` de le dire et de le trancher : je ne le presume pas, je ne
l'ai pas trouve, et je ne modifie aucun fichier sous `domain/`, `application/`, `adapters/` ou
`bootstrap/`.

**Un point que je ne tranche pas et que je route explicitement a `nadia-prof`** (section 5, candidat
`cm1-comprehension`) : un blocage pedagogique specifique, ecrit par elle le 2026-08-19 comme non
negociable ("ma condition sur le resultat ... ne bouge pas"), portait exactement sur cette famille de
critere (`COMP-4`/`COMP-7`, "entierement visibles a 360 x 640 sans defiler"). La formulation large de
l'utilisatrice ("le pli sort de la porte pedagogique, pour tout le catalogue") couvrirait, a lecture
naturelle, ce cas aussi - mais je ne decide pas a sa place qu'une condition qu'elle a elle-meme
qualifiee de non negociable neuf jours plus tot tombe sur la foi d'une formule generale que je lis.
Je pose la question, je ne reponds pas pour elle.

### Arbitrage 2 (utilisatrice) - s22 demarre aujourd'hui, couverture maximale, sans point de coupe impose

La livraison test prendra l'etat du catalogue au moment ou elle arrive. Consequence pour le
planning : aucune date de gel n'est fixee par ce document ; `sofia-sm` conduit le sprint selon son
rythme habituel, la livraison intervenant sur l'etat qu'elle trouvera.

## 1. Verification personnelle de l'etat avant d'ecrire quoi que ce soit

### Matrice de couverture

`squad/programme/MATRICE-couverture.md`, section "Amendement du 2026-08-28 (second, s21 cloture)" :
**18/43 (41,9 %)** sur le denominateur atteignable (43 sur 47 lignes, 4 lignes `non couvrable en
l'etat` retirees du denominateur). Repartition par matiere/niveau, relue table par table : mathematiques
CP 5 `couvert-valide` sur 11 (0 `non couvrable`) ; mathematiques CM1 4 sur 12 (2 `non couvrable`) ;
francais CP 3 sur 12 (1 `non couvrable`) ; francais CM1 6 sur 12 (1 `non couvrable`). Total :
18 `couvert-valide`, 4 `en cours`, 21 `non couvert`, 4 `non couvrable en l'etat`.

### Catalogue

`adapters/adapter-content/src/main/resources/catalogue.yaml` : **21 entrees** (`grep -c "^  - id:"`),
correspondance un pour un avec les 21 fichiers de `adapters/adapter-content/src/main/resources/
contenu/` (`ls *.yaml | wc -l`), aucun paquet dormant.

### Fiches de conformite ouvertes sans increment livre

Releve par lecture du repertoire `squad/conformite/` et de chaque fiche dont la taille laissait
supposer une ouverture sans contenu : deux fiches confirment un statut **"ouverte, aucun increment
livre a ce jour"** en toutes lettres, toutes deux depuis leur ouverture au sprint 13 (2026-08-15) :

- `squad/conformite/FICHE-bandit-manchot-multiplications-divisions-cm1.md`
- `squad/conformite/FICHE-bandit-manchot-problemes-durees-cm1.md`

Une troisieme fiche, `squad/conformite/FICHE-bandit-manchot-comprehension-cm1.md`, est **en
construction** : aucun code de production n'existe pour elle (verifie a l'ouverture de la fiche
elle-meme, 2026-08-19, jamais contredit depuis), mais l'arbitrage prealable de principe y est deja
rendu (voir section 2).

## 2. Trois lignes deja arbitrees, jamais codees - verifiees contre leur source primaire

### `S13-2` - mathematiques CM1, "Calcul mental - multiplications et divisions simples (multiples de
10, 100)"

Arbitrage prealable rendu le 2026-08-15 (`squad/conformite/ARBITRAGE-PREALABLE-s13-multiplications-
divisions-et-durees-cm1.md`, section 2, conditions `MD-1` a `MD-10`) : la ligne ouvre **en entier**,
verrou de `DEC-0030` leve. **Aucun code n'a ete ecrit depuis** : verifie par l'absence d'entree
`bandit-manchot-multiplications-divisions-cm1` dans `catalogue.yaml` et l'absence du fichier
correspondant sous `adapters/adapter-content/src/main/resources/contenu/`, et par le statut de la
fiche elle-meme ("Statut global : ouverte, aucun increment livre a ce jour"), inchange depuis le
2026-08-15.

### `S13-3` - mathematiques CM1, "Resoudre des problemes de durees"

Meme constat, meme date : arbitrage prealable rendu (`ARBITRAGE-PREALABLE-s13-multiplications-
divisions-et-durees-cm1.md`, section 3, conditions `D-1` a `D-11`), verrou de `DEC-0030` leve, zero
code depuis. Un obstacle technique est signale d'avance dans le meme arbitrage (section 3.6, non
leve depuis) : les deux garde-fous de rang lisent l'entier de tete d'une proposition et classeraient
`1 h 30` sous `45 min` sur ce contenu - route a `theo-archi` et `yanis-qa`, ne bloque pas l'ecriture
du contenu, conditionne la lecture de la CI a la review.

### `S17-2` - francais CM1, "Comprendre un texte narratif ou documentaire"

Arbitrage prealable de principe rendu le 2026-08-19 : **ACCEPTE** sous cinq conditions bloquantes
(`COMP-1` a `COMP-5`, `squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md`,
section 1.3). Le meme jour, la ligne **sort du perimetre de s17** (amendement du 2026-08-19 a
`DEC-0039`) sur un motif distinct de l'arbitrage de principe : `COMP-4`/`COMP-7` (le support, la
question et toutes les propositions entierement visibles a 360 x 640 sans defiler) exigent un
budget de texte-support d'environ 4 lignes / 20 mots dans l'assemblage le plus favorable, insuffisant
pour porter l'inference que `COMP-3` exige (`squad/conformite/FICHE-bandit-manchot-comprehension-
cm1.md`, "Jugement de lisibilite du texte-support (COMP-5)"). **Zero code de production n'existait a
cette date, et zero depuis** (meme verification que ci-dessus).

**Ce que l'arbitrage 1 change potentiellement ici, sous condition de confirmation** : `COMP-4` et
`COMP-7` sont exactement un critere de la famille "pli" (visibilite sans defilement) que l'arbitrage
1 retire de la porte pedagogique. Si `nadia-prof` confirme que cet arbitrage couvre aussi ce cas
precis - qu'elle avait pourtant qualifie de non negociable le 2026-08-19 - le blocage de perimetre
qui a fait sortir `S17-2` de s17 tombe, et le paquet redevient developpable (`COMP-1`, `COMP-2`,
`COMP-3`, `COMP-5` restent inchangees et ne dependent pas du pli). Question posee section 5.

## 3. Rechiffrage de la borne de capacite, avec sa methode

`DEC-0051` section 5 tenait "deux a trois paquets neufs" comme borne haute auditable a s21, chiffree
sur le taux de cycles de refus observe sur six paquets audites en deux sprints (s19, s20). La retro
de s21 (`squad/sprints/s21-fermer-le-pli-avant-le-volume/retro.md`, section "`nadia-prof` contre
elle-meme") ajoute un troisieme sprint a l'observation et nomme une distinction que ce chiffre ne
faisait pas.

### Ce que je verifie, sprint par sprint, avant de rechiffrer

Methode : je compte les paquets neufs qui atteignent un Done pedagogique (`VALIDE` ou `VALIDE SOUS
RESERVE`) **dans le sprint ou ils sont instruits**, pas les paquets simplement audites - c'est la
metrique que `DEC-0051` section 5 utilisait deja, je la reconduis a l'identique pour que les trois
sprints se comparent.

| Sprint | Paquets neufs instruits | Atteignent Done dans le sprint | Dont avec cycle de refus intra-sprint |
|---|---|---|---|
| s19 | 4 (accords-GN, accord-sujet-verbe, synonymes+antonymes, nature-mot) | 3 (accords-GN, accord-sujet-verbe, synonymes+antonymes - `nature-mot` ferme seulement en s20) | 1 (accord-sujet-verbe, REFUSE puis VALIDE le meme jour) |
| s20 | 2 (nature-mot - fermeture de `NAT-5` -, cm1-reperes) | 2 | 1 (cm1-reperes, REFUSE puis VALIDE SOUS RESERVE le meme sprint) |
| s21 | 3 (cp-syllabes, cp-mot-manquant, cp-comparer) | 3 | 2 (cp-syllabes une fois ; cp-mot-manquant deux fois) |

Moyenne sur trois sprints : (3 + 2 + 3) / 3 = **2,67**, plancher 2, plafond 3 - le chiffre de
`DEC-0051` tient encore apres un sprint de plus, verifie plutot que suppose.

### Ce que la retro ajoute, et ce que j'en tire

Sur les quatre refus prononces depuis s20 (`retro.md`, tableau), **trois** ont leur cause racine
dans une specification prealable fausse de `nadia-prof` elle-meme (`cm1-reperes`, `cp-syllabes`,
`cp-mot-manquant` au reaudit), un seul dans le contenu livre (`cp-mot-manquant`, premier verdict).
`DEC-0051` ne distinguait pas les deux familles dans son chiffrage. **Je ne peux pas conclure de
cette seule distinction que la capacite doit monter** : un cycle cause par une specification fausse
coute quand meme un cycle - l'arbitrage de l'utilisatrice sur `cheminee` (s21) a lui-meme demande un
aller-retour jusqu'a elle, pas seulement une correction de `nadia-prof`. Ce que j'en retiens comme
**risque**, pas comme economie : les arbitrages prealables de `S13-2` et `S13-3` (2026-08-15) ont ete
ecrits par categorie de raisonnement, **sans verification contre un corpus reel** - exactement le
mecanisme qui a fait echouer `SEG-1` (`cp-syllabes`) une fois le paquet ecrit et mesure contre les
650 mots du corpus CP. Rien ne garantit que `MD-1` a `MD-10` ou `D-1` a `D-11` survivront intacts a
la meme epreuve.

**Ce que l'arbitrage 1 change reellement au chiffrage, verifie plutot que suppose.** Sur les neuf
audits des trois derniers sprints, **un seul** refus (`cm1-reperes`, s20) portait le pli comme
motif conjoint - les huit autres motifs de refus (`SEG-1`, `crayon/feutre/pinceau`, `PRE-2`/`PRE-3`,
les cinq motifs d'`accord-sujet-verbe`) sont tous des motifs de contenu ou de largeur de colonne,
jamais de defilement vertical. L'economie que l'arbitrage 1 procure par paquet est donc **reelle mais
modeste** sur cet echantillon : elle ne retire pas une famille de refus qui dominait le chiffrage
precedent, elle retire une famille qui n'en representait qu'une petite part. La ou l'economie est la
plus nette, c'est sur le geste **transverse** que `REP-8` imposait (mesure du catalogue entier,
item par item, avant tout paquet neuf) - un cout de sprint entier a s21, qui ne se represente pas de
la meme ampleur a s22 puisqu'il n'a jamais ete recurrent d'un sprint a l'autre avant celui-ci.

### La borne que je tiens pour s22, et ce qu'elle n'est pas

**Je tiens trois paquets neufs comme borne haute auditable a s22**, sur la meme methode que
`DEC-0051` (moyenne sustained sur trois sprints, 2,67, arrondie au plafond deja observe) - **je ne la
monte pas mecaniquement au-dessus de trois sur la seule foi de l'arbitrage 1**, pour trois raisons
verifiees ci-dessus et non supposees : l'economie de pli mesuree sur l'echantillon des trois derniers
sprints est reelle mais porte sur une petite part des refus observes (un motif sur neuf audits) ;
le risque nomme par la retro (specifications prealables non verifiees contre un corpus) touche en
plein deux de mes trois candidats prioritaires (`S13-2`, `S13-3`) et n'est pas attenue par
l'arbitrage 1 ; et `DEC-0052` section 11 m'engageait explicitement a ne pas rechiffrer "a chaud" sur
un seul cas recent - je tiens la meme discipline pour l'autre sens (ne pas monter la borne a chaud
sur un seul arbitrage recent, aussi favorable soit-il en apparence).

**Ce que l'arbitrage 1 change quand meme, et que je nomme pour ne pas le taire** : il retire du
chiffrage de s22 le risque specifique qu'un des trois paquets retenus echoue **sur le seul motif du
pli**, motif qui a compte pour un refus sur les neuf audits mesures. Je tiens donc les trois memes
paquets qu'a capacite inchangee, mais avec une confiance mesurablement plus haute de les voir
aboutir sans cycle de refus supplementaire lie a cette cause precise - sans que cela change le
chiffre lui-meme.

## 4. Perimetre du sprint 22, classe par gain de couverture reel sur le compteur de la matrice

**Distinction demandee et verifiee ligne par ligne avant d'ecrire ce tableau** : les lignes qui
peuvent faire bouger le compteur `couvert-valide` de la matrice, et celles qui ne le peuvent pas par
construction meme si elles sont travaillees ce sprint.

### Lignes qui font bouger le compteur (candidates prioritaires de s22)

| Priorite | Story | Ligne de matrice | Gain si `VALIDE` | Etat au 2026-08-28 |
|---|---|---|---|---|
| 1 | `S13-2` | mathematiques CM1, "Calcul mental - multiplications et divisions simples (multiples de 10, 100)" | ligne entiere, `en cours` -> `couvert-valide` | arbitrage prealable rendu, verrou leve, zero code ; pret |
| 2 | `S13-3` | mathematiques CM1, "Resoudre des problemes de durees" | ligne entiere, `en cours` -> `couvert-valide` | arbitrage prealable rendu, verrou leve, zero code ; pret, un obstacle technique nomme d'avance (garde-fous de rang) |
| 3 | `S17-2` | francais CM1, "Comprendre un texte narratif ou documentaire" | ligne entiere sous la mention de couverture partielle deja nommee par `nadia-prof` ("textes courts, un texte par question, sans lecture longue"), `non couvert` -> `couvert-valide` | arbitrage de principe rendu, **conditionnel** a la confirmation de `nadia-prof` sur `COMP-4`/`COMP-7` (section 2 et 5) |

Si les trois aboutissent sans cycle de refus supplementaire, la matrice passerait de **18/43
(41,9 %)** a **21/43 (48,8 %)**. Si `S17-2` ne se debloque pas a temps, elle passerait a **20/43
(46,5 %)** sur les deux seules lignes mathematiques. **Ce chiffre est un objectif de sprint, pas un
Done : aucune ligne ne passe `couvert-valide` par ce document, ce statut n'est prononce que par
`nadia-prof`, apres audit reel de chaque increment.**

### Lignes qui ne peuvent pas bouger ce compteur, meme parfaitement tenues

Nommees pour qu'aucun agent ne les travaille en croyant qu'elles font avancer le taux :

- **`DEN-2`** (`squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`,
  section 1.2) interdit explicitement a la ligne "Denombrer, lire, ecrire et decomposer les nombres
  jusqu'a 100" d'atteindre `couvert-valide`, meme si un paquet tient parfaitement `DEN-1` a `DEN-8` :
  la note de matrice obligatoire ("le verbe 'denombrer' n'est travaille par aucun jeu") maintient la
  ligne `en cours` par construction. Verifie contre la source citee, verbatim confirme.
- La moitie "reperage sur une droite numerique" de la ligne "Notion de fraction simple... et
  reperage sur une droite numerique" (mathematiques CM1) empeche cette ligne d'atteindre
  `couvert-valide` tant qu'aucun mecanisme ne dessine une ligne numerique reellement graduee
  (`ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 2 ; `squad/programme/
  MATRICE-couverture.md`, section mathematiques CM1). Un paquet qui ne couvrirait que la moitie
  "notion" resterait, au mieux, une couverture partielle nommee, jamais `couvert-valide` en entier.

Ces deux lignes ne sont pas retenues au perimetre du sprint (aucune matiere premiere jugee viable
sans mecanisme neuf, hors de mon perimetre de decision) ; elles sont nommees ici pour que le
classement par gain reel n'oublie aucune ligne structurellement plafonnee.

## 5. Question posee a `nadia-prof` avant tout premier commit de code sur `cm1-comprehension`

**Question, formulee pour qu'elle y reponde sans avoir a deviner ce que je pense** : l'arbitrage 1
de l'utilisatrice retire le motif "pli" de toute REFUSE pedagogique, sur tout le catalogue, paquets
neufs de s22 compris. Le blocage qui a fait sortir `S17-2` du perimetre de s17 (`COMP-4`/`COMP-7`,
"entierement visibles a 360 x 640 sans defiler") appartient a cette meme famille. **Confirmez-vous
que cet arbitrage couvre aussi `COMP-4`/`COMP-7` pour ce paquet precis, et que le budget de 4
lignes / 20 mots mesure le 2026-08-19 cesse d'etre bloquant (devenant, comme pour `REP-8`, une
reserve non bloquante), ou maintenez-vous cette condition specifique en dehors du regime nouveau ?**
Je n'ai pas de position sur cette question : c'est un jugement pedagogique qui vous appartient
entierement, et vous l'avez ecrit vous-meme comme non negociable neuf jours avant cet arbitrage.

**Ce que cette reponse change concretement** : si `COMP-4`/`COMP-7` tombent, `marc-dev` ou `lea-dev`
peut ecrire le paquet neuf sur les 20 items complets (le proxy de 45 mots redevient utilisable,
`COMP-3` redevient atteignable) sans attendre un arbitrage de forme d'`ines-design`/`theo-archi` sur
des pixels manquants qui n'ont jamais ete trouves. Si elles ne tombent pas, `S17-2` reste hors du
perimetre de developpement de s22 et le sprint retient `S13-2` et `S13-3` seules parmi les
candidates prioritaires.

## 6. Deux arbitrages prealables poses des maintenant pour la suite (niveau 2 de `DEC-0051`)

Ma borne de trois paquets est deja remplie par les trois candidats de la section 4. Je ne retiens
donc aucun quatrieme paquet neuf a developper ce sprint. Mais, pour ne pas reproduire le defaut deja
nomme sur `NAT-5` et sur `DEN-1` a `DEN-8` (perdre un sprint entier a instruire un arbitrage une fois
le developpement pret), je pose des maintenant les deux questions de niveau 2 de `DEC-0051` (section
4) que personne n'a encore posees depuis leur identification le 2026-08-27 - verifie par recherche de
ces deux formulations dans `squad/conformite/` : aucun fichier d'arbitrage ne les porte a ce jour.

**Question 1, sur `contenu-repris/mots/cp-premiere-lettre.yml`** (mot -> premiere lettre ecrite) :
un choix de la premiere lettre **ecrite** d'un mot satisfait-il la competence "Identifier un son
(phoneme) dans un mot" (francais CP, `non couvert`), ou cette competence exige-t-elle un phoneme
**entendu**, ce que ce fichier ne fournit pas (aucune emission de son) ? Point signale au passage,
sans consequence sur la question de fond : ce fichier porte un artefact de detection deja note
(`DEC-0052`, section 4 - `VELO` sans accent, une capitale privee de son accent), a corriger si ce
fichier est retenu, pas un motif de refus de principe.

**Question 2, sur `contenu-repris/fractions/cm1-compare.yml`** (deux fractions, signe `<`/`>`/`=`) :
ce fichier peut-il couvrir la moitie "notion de fraction simple" de la ligne mathematiques CM1
nommee section 4 ci-dessus, en couverture partielle explicitement nommee (sur le modele deja accepte
pour "comparer/ranger" et pour les tables de multiplication), sans que cela laisse croire que la
moitie "reperage sur une droite numerique" est traitee ?

Je ne propose ni l'un ni l'autre comme story de s22 : je pose les deux questions pour que la
reponse existe avant que la capacite libere un prochain paquet, pas pour ouvrir un quatrieme
developpement ce sprint.

## 7. Recompte du plafond de la mecanique unique (ajout de `sofia-sm`, comparaison a `main`)

**Ce que cette section ne fait pas** : elle ne rouvre pas le perimetre de s22 fixe sections 3 et 4.
C'est une instruction sur l'horizon du produit, versee au dossier pour l'utilisatrice, pas un
arbitrage de sprint.

### Correction prealable : ce que "visuel" et "audio" veulent dire architecturalement

Avant de recompter, une correction que je dois a deux avis deja rendus et non contredits depuis,
verifies contre leur source avant de m'en servir : `theo-archi` a mesure, des s13
(`squad/sprints/s13-grands-nombres-et-chemin-de-catalogue/avis-ordre-de-grandeur-seconde-
mecanique.md`) et confirme en l'ecrivant dans `ADR-0058` (s19, 2026-08-24), qu'**une mecanique se
definit par le geste de l'enfant et ce que le serveur en fait**, et que sur cette definition,
**seule la saisie est une seconde mecanique** : le visuel et l'audio (emission) sont des **formes
d'item** que `BanditManchot` sert sans une ligne de changement au coup joue - presser l'une des
trois ou quatre propositions, que la proposition soit un mot, une image ou un son. Mesure a l'appui
(section "Le decouplage tient-il ? Oui, et c'est mesure") : ajouter une forme d'item est un cout
borne et visible par le compilateur (deux `switch` exhaustifs sur `Item` dans tout le depot),
`ADR-0006` (le contrat de contenu ne porte que du texte) force deja la bonne forme pour les trois
familles, et aucun port de domaine ne change pour le visuel ou l'audio.

Je reprends cette lecture parce qu'elle est verifiee et qu'elle change ma reponse : le "plafond de
la mecanique unique" au sens strict (le geste `presser une proposition`) est **plus haut** que ce
qu'une lecture au vocabulaire seul (visuel, saisie, audio) laisserait croire. Deux familles de plus,
distinctes des trois de `DEC-0030`, ressortent de cette meme relecture ligne par ligne : les
**operations posees** (multiplication, division euclidienne) sont, de l'aveu meme de `theo-archi`,
"une troisieme mecanique et non une variante de la seconde" - une grille de champs et un verdict
partiel, pas une chaine unique ; et le **denombrement par manipulation reelle** (deplacer, marquer,
grouper une collection, condition posee par `nadia-prof` pour "denombrer") est un geste qu'aucune
des deux definitions ci-dessus ne couvre - ni presser une proposition, ni produire une chaine. Je le
nomme comme un besoin mecanique a part, non chiffre a ce jour par `theo-archi`, et je ne lui invente
pas de cout.

### 1. Le plafond de couverture atteignable sans jamais ecrire de mecanique neuve

Methode : relecture des 21 lignes `non couvert` et des 4 lignes `en cours` d'aujourd'hui - population
complete, verifiee ligne par ligne contre les quatre tableaux de `squad/programme/
MATRICE-couverture.md`, pas un echantillon - contre ce que `BanditManchot` peut porter aujourd'hui
plus les deux formes d'item que `theo-archi` chiffre comme gratuites en mecanique (visuel, audio en
emission). Chaque ligne classee porte sa source ; les lignes sans arbitrage individuel encore rendu
sont marquees **a confirmer**, distinctes de celles deja arbitrees.

| Ligne | Matiere/niveau | Besoin | Etat d'instruction |
|---|---|---|---|
| Comparer et ranger - moitie "ranger" | maths CP, `en cours` | contenu seul | a confirmer |
| Resoudre des problemes a plusieurs etapes (4 operations) | maths CM1, `non couvert` | contenu seul | a confirmer, risque nomme (`DEC-0021`) |
| Calcul mental x10/x100 (`S13-2`) | maths CM1, `en cours` | contenu seul | arbitre, verrou leve |
| Problemes de durees (`S13-3`) | maths CM1, `en cours` | contenu seul | arbitre, verrou leve |
| Comprendre un texte narratif tres court | francais CP, `non couvert` | contenu seul | a confirmer |
| Marques du pluriel (-s) | francais CP, `non couvert` | contenu seul | a confirmer |
| Comprendre un texte narratif ou documentaire (`S17-2`) | francais CM1, `non couvert` | contenu seul | arbitre en principe, conditionnel (section 5) |
| Reperer les substituts d'un personnage | francais CM1, `non couvert` | contenu seul | a confirmer |
| Mots de liaison | francais CM1, `non couvert` | contenu seul | a confirmer |
| Monnaie - moitie "reconnaitre" | maths CP, `non couvert` | item visuel (image) | a confirmer |
| Heure sur une horloge | maths CP, `non couvert` | item visuel (horloge rendue) | a confirmer |
| Deplacements sur un quadrillage | maths CP, `non couvert` | item visuel (grille rendue) | a confirmer |
| Figures planes | maths CP, `non couvert` | item visuel (forme rendue) | arbitre (`FIG-1` a `FIG-4`) |
| Fraction - moitie "reperage sur droite numerique" | maths CM1, `non couvert` | item visuel (ligne graduee rendue) | arbitre (refus du proxy texte, motif ecrit) |
| Localiser une syllabe | francais CP, `non couvert` | item audio (emission) | famille etablie, item non arbitre |
| Identifier un son (phoneme) | francais CP, `non couvert` | item audio (emission) | famille etablie, item non arbitre |
| Decoder un mot par assemblage syllabique | francais CP, `non couvert` | item audio (emission) | arbitre (`SYL-1` a `SYL-4`) |
| Graphemes/phonemes - digraphes ch/ou/oi | francais CP, `en cours` | item audio (emission) | famille etablie par `nadia-prof` (audit 2026-08-10) |

**18 lignes** relevent ainsi de la seule mecanique existante, sous forme de contenu seul ou d'item
visuel/audio - aucune n'exige le geste "produire une chaine" ni un geste de manipulation. Ajoutees
aux 18 `couvert-valide` d'aujourd'hui : **plafond theorique de 36/43 (83,7 %)**, **si et seulement
si** chacune de ces 18 lignes finit par etre validee par `nadia-prof` sans redirection. Sur ces 18,
**6 sont deja arbitrees et pretes** (`S13-2`, `S13-3`, `S17-2` conditionnelle, figures planes,
fraction-reperage, decoder par assemblage syllabique) ; **12 n'ont recu aucun arbitrage individuel**
(9 marquees "a confirmer" dans le tableau, plus 3 dont seule la famille - non l'item - est etablie :
localiser une syllabe, identifier un son, la portion digraphes des graphemes/phonemes) et rien ne
demontre, pour ces douze, qu'elles seront validees telles quelles.

**Ce plafond n'est pas un objectif de sprint et je ne le traite pas comme tel.** Deux reserves que je
nomme pour qu'elles ne se perdent pas :

- **L'audio a un obstacle de production, pas d'architecture** (`avis-ordre-de-grandeur-seconde-
  mecanique.md`, section "Audio") : il faut un enregistrement de voix humaine pour chaque syllabe et
  chaque phoneme, et "aucun agent de cette squad ne peut le produire". Tant que l'utilisatrice n'a
  pas tranche une source (elle-meme, un tiers, une synthese que `nadia-prof` rejugerait), les 4
  lignes audio de ce tableau restent hors d'atteinte **quel que soit le nombre de sprints** - ce
  n'est pas une question de capacite de developpement.
- **Le visuel n'a encore aucun geste de construction commence** : contrairement a la saisie
  (`ADR-0058`, arbitrage pedagogique `SAI-1` a `SAI-11` deja rendus, zero ligne de code), aucun `ADR`
  ne decrit comment un item porte une donnee de dessin (horloge, quadrillage, droite graduee, forme).
  `theo-archi` en donne l'ordre de grandeur (section 121-138 de son avis), pas une conception. Les 5
  lignes visuelles de ce tableau ne sont donc pas "pretes a coder" au sens ou `S13-2` l'est : il leur
  manque une decision d'architecture qui n'existe pas encore.

**Plafond realiste a court terme, ecartant l'audio (bloque sur une source qui n'existe pas) et le
visuel (aucune architecture ecrite)** : les 9 lignes de contenu seul seules, dont 2 deja arbitrees et
retenues a s22, 1 conditionnelle (`S17-2`), et 6 encore a arbitrer. **18 + 9 = 27/43 (62,8 %)** au
mieux, sans construire quoi que ce soit de neuf - et ce chiffre suppose que les 6 lignes non encore
arbitrees survivent toutes comme contenu seul.

### 2. Sprints de marge a ma borne rechiffree, et quand la mecanique unique bloque

A la borne de trois paquets par sprint (section 3), et en retirant `S13-2`, `S13-3` (et `S17-2` si
elle se confirme) au perimetre de s22, il reste **6 lignes de contenu seul non arbitrees** pour
alimenter les sprints suivants, plus 5 lignes visuelles sans architecture et 4 lignes audio sans
source.

**Scenario optimiste** (les six survivent integralement comme contenu seul, aucune redirection) :
6 lignes / 3 par sprint = **2 sprints de marge** (s23, s24) avant epuisement du contenu seul.

**Scenario que je retiens, et pourquoi je ne retiens pas l'optimiste.** Les quatre lignes que
`DEC-0030` avait laissees "sous reserve d'un jugement pedagogique prealable, pensees content-only"
(denombrer, figures planes, decoder par assemblage syllabique, relire son texte) ont **toutes les
quatre** ete redirigees hors du contenu seul pur une fois l'arbitrage rendu - vers le visuel, vers
l'audio, ou vers `non couvrable en l'etat`. Zero sur quatre a survecu tel quel. Je ne peux pas
ignorer ce taux en estimant les six lignes non arbitrees d'aujourd'hui, meme si leur risque parait
moindre a la lecture des intitules (`ranger`, `pluriel -s` et `mots de liaison` ressemblent a des
lignes deja validees ; `comprendre un texte tres court` et `substituts de personnage` portent le
meme risque d'appariement que `cm1-comprehension`, refusee une fois avant d'etre acceptee sous cinq
conditions). **Je tiens donc une marge de un sprint (s23), pas deux**, avant que la reserve surement
disponible ne soit epuisee - le sprint s24 et au-dela dependent d'arbitrages qui n'existent pas
encore et dont l'historique de ce produit ne garantit pas l'issue.

**A quel sprint la mecanique unique devient-elle bloquante ?** Des la cloture de s23, si les six
arbitrages ne sont pas poses avant, ou des la cloture de s24 si les six lignes sont confirmees
contenu seul mais qu'aucune n'a ete redirigee : dans les deux cas, plus tot que l'estimation de
`DEC-0030` ("quatre a six sprints" depuis s13, soit un horizon qui aurait du courir jusqu'a s17-s19).
**Ce n'est pas que le contenu seul s'est epuise plus vite que prevu : c'est que le goulot reel n'a
jamais ete le nombre de lignes disponibles, mais la capacite d'audit de `nadia-prof`** (`DEC-0051`,
section 5) - la reserve de lignes a dure plus longtemps que l'horizon de sprints, precisement parce
que la capacite d'audit, pas le contenu, a borne le rythme depuis s13.

### 3. Ventilation des lignes hors d'atteinte de "presser une proposition parmi trois ou quatre", par ce qu'elles exigent

Reprise des categories demandees, avec la precision architecturale ci-dessus : je place le visuel et
l'audio ci-dessous parce que la question posee les y range, pas parce qu'ils exigent une seconde
mecanique - eux n'en exigent pas, au sens de `theo-archi`. Seules la saisie (deux formes) et la
manipulation reelle en exigent une.

**Mecanique visuelle (item, pas mecanique - 5 lignes)** : Reconnaitre et utiliser la monnaie (moitie
"reconnaitre", maths CP) ; Se reperer dans la journee/semaine et lire l'heure sur une horloge (maths
CP) ; Se reperer et decrire des deplacements sur un quadrillage (maths CP) ; Reconnaitre et nommer
des figures planes (maths CP) ; Notion de fraction simple - moitie "reperage sur une droite
numerique" (maths CM1).

**Audio, emission seulement (item, pas mecanique - 4 lignes)** : Localiser une syllabe dans un mot
(francais CP) ; Identifier un son/phoneme dans un mot (francais CP) ; Decoder un mot regulier
inconnu par assemblage syllabique (francais CP) ; Associer graphemes et phonemes - portion digraphes
`ch`, `ou`, `oi` (francais CP, ligne deja `en cours` pour sa portion voyelles nasales).

**Saisie simple (mecanique neuve, deja instruite - `ADR-0058`, `SAI-1` a `SAI-11` - 2 lignes)** :
Copier un mot, une phrase courte sans erreur (francais CP) ; Ecrire de maniere autonome un mot
simple (francais CP).

**Saisie posee / grille (troisieme mecanique distincte selon `theo-archi`, jamais instruite - 2
lignes)** : Effectuer une multiplication posee d'un nombre a 2-3 chiffres par un nombre a 1-2
chiffres (maths CM1) ; Effectuer une division euclidienne simple, quotient et reste (maths CM1).

**Manipulation reelle d'une collection (besoin mecanique a part, jamais chiffre - 1 ligne)** :
Denombrer, lire, ecrire et decomposer les nombres jusqu'a 100 - moitie "denombrer" (maths CP ;
rappel : `DEN-2` interdit deja a cette ligne d'atteindre `couvert-valide`, meme si "lire/ecrire/
decomposer" sont serves en contenu seul).

**Reconnaissance vocale (structurellement hors d'atteinte, `avis-ordre-de-grandeur-seconde-
mecanique.md`, "Ce que le depot ne pourra pas faire")** : aucune ligne parmi les 25 - "Lire a voix
haute un texte court..." porte deja le statut `non couvrable en l'etat` et n'est donc plus comptee
dans ce denominateur (`DEC-0042`).

**Jugement humain sur une production libre (`ADR-0008` - 2 lignes)** : Lire silencieusement un
texte de longueur croissante et en restituer l'essentiel (francais CM1) ; Rediger un texte narratif
court et coherent (francais CM1, qui exige en plus la saisie - le jugement humain resterait le
verrou meme si la saisie existait). "Relire et corriger son texte..." porte deja le statut
`non couvrable en l'etat` pour le meme motif et n'est pas recomptee ici.

### 4. Ma recommandation - le declencheur, pas la decision

**Je ne recommande pas d'ouvrir une seconde mecanique ce sprint**, et je ne recommande pas laquelle :
ce jugement pedagogique reste a `nadia-prof` (son avis du 2026-08-15, `AVIS-PRIORITE-PEDAGOGIQUE-
s13-seconde-mecanique.md`, tient toujours et n'a jamais ete conteste : audio, puis visuel, puis
saisie) et l'arbitrage final a l'utilisatrice.

**Ce que je constate, et qui n'est pas un jugement pedagogique** : parmi les trois familles, la
**saisie** est la seule aujourd'hui **instruite jusqu'au bout sans qu'aucune capacite de
developpement n'y ait ete affectee** - onze conditions pedagogiques rendues (`SAI-1` a `SAI-11`,
2026-08-24), une decision d'architecture ecrite et mesuree (`ADR-0058`), zero ligne de code. C'est
exactement l'etat que `DEC-0030` section 6 demandait d'atteindre "en parallele, sans affecter de
capacite" - il est atteint pour cette seule famille. L'audio attend une reponse a une question qui
n'est pas technique (source d'un enregistrement de voix humaine) ; le visuel n'a encore aucune
decision d'architecture.

**Le declencheur que je tiens, ecrit pour qu'il ne se decouvre pas apres coup** : le moment ou
`nadia-prof` a arbitre les six candidates non arbitrees ci-dessus (section 1) et que **moins de trois
d'entre elles survivent comme contenu seul** - c'est-a-dire le moment ou la reserve confirmee de
contenu seul retombe sous la borne d'un sprint. A ce moment, faire attendre le developpement sur un
arbitrage qui n'existe pas encore, quand une famille (la saisie) est deja pretes a l'emploi sans
attendre aucune instruction de plus, serait le meme defaut que `DEC-0007` a deja sanctionne sur ce
produit (un perimetre ferme trois sprints de suite pour un motif chaque fois defendable). Je propose
donc que ce moment - pas une date fixe, un etat mesurable a chaque cloture - soit le signal qui rend
l'ouverture de la saisie inevitable, quel que soit l'ordre pedagogique que `nadia-prof` prefererait
dans l'absolu.

**Ce que la comparaison a `main` ajoute, sans y changer ma recommandation.** `sofia-sm` verse une
comparaison que j'ai verifiee independamment sur le fichier de `main` (comptage direct des lignes de
tableau, hors en-tetes) : **70 lignes, 69 `couvert-valide`**, chiffre confirme a l'identique. `main`
portait huit mecaniques, dont une "droite graduee" qui couvrait precisement la moitie "reperage" que
`round-two` classe aujourd'hui item visuel non arbitre - verifie aussi, la ligne existe bien dans son
fichier. **Cette comparaison ne dit pas que `round-two` doit repliquer huit mecaniques** : `main`
payait ce chiffre par les dettes que `CLAUDE.md` liste explicitement en tete de ce depot (neuf
familles de chargeurs quasi identiques, une matrice qui ne se sert jamais du referentiel complet
comme denominateur - 70 lignes auto-selectionnees, pas 43 issues d'un referentiel verifie). Ce que
j'en tire, et rien de plus : le principe directeur du produit (une mecanique reutilisable, un
contenu pilote par des regles) n'a, sur `round-two`, jamais ete mis a l'epreuve d'une seconde
mecanique - `theo-archi` le nommait deja a s13 - et `main` montre que sans ce principe, la couverture
totale est atteignable, mais au prix des dettes que ce depot a choisi de ne pas reproduire. Je ne
tranche pas ce compromis : je le nomme pour que l'utilisatrice l'ait sous les yeux au moment ou elle
arbitrera la saisie.

## Ce que je route

- **`sofia-sm`** : affectation de `S13-2`, `S13-3` (pretes) et `S17-2` (conditionnelle a la reponse
  de `nadia-prof`, section 5) au planning de s22 ; verification de l'avis de faisabilite de
  `theo-archi` sur l'obstacle technique deja nomme de `S13-3` (garde-fous de rang lisant l'entier de
  tete) avant tout code.
- **`nadia-prof`** : la question de la section 5 (`COMP-4`/`COMP-7` sur `cm1-comprehension`,
  urgente pour le perimetre du sprint) et les deux questions de la section 6 (non urgentes, pour la
  suite).
- **`theo-archi`** : le point de vigilance de la section "Arbitrage 1" ci-dessus (confirmer qu'aucun
  garde-fou technique existant ne repose implicitement sur une notion de pli que je n'aurais pas
  trouvee) ; l'avis de faisabilite deja du sur `S13-2` et `S13-3` avant tout code, et l'obstacle
  technique nomme d'avance sur `S13-3`.
- **`marc-dev`/`lea-dev`** (via `sofia-sm`) : ecriture des paquets `S13-2` et `S13-3` sous le schema
  `type: question` deja en usage, des que l'avis de faisabilite de `theo-archi` est rendu.
- **`nadia-prof`** (section 7) : les six candidats de contenu seul non encore arbitres (ranger,
  problemes a plusieurs etapes, comprendre un texte tres court CP, marques du pluriel, substituts de
  personnage, mots de liaison) et les quatre candidats visuels non encore arbitres individuellement
  (monnaie, horloge, quadrillage - figures planes et fraction-reperage le sont deja) ; le declencheur
  de la section 7.4 (moins de trois de ces six survivant comme contenu seul) est a surveiller a
  chaque cloture, pas a repondre aujourd'hui.
- **`theo-archi`** (section 7) : confirmer ou corriger ma lecture de sa propre analyse (visuel/audio
  = forme d'item, saisie = seconde mecanique, operations posees = troisieme mecanique) avant qu'elle
  ne serve de base a une decision de l'utilisatrice ; chiffrer, le jour ou la question se pose, le
  besoin de manipulation reelle d'une collection (denombrement), non chiffre a ce jour.
- **L'utilisatrice** (section 7.4, via `sofia-sm`) : le declencheur que je recommande pour
  l'ouverture d'une seconde mecanique (saisie, seule famille aujourd'hui instruite jusqu'au bout sans
  capacite affectee), et le compromis que montre la comparaison a `main` (couverture totale
  possible avec huit mecaniques, au prix des dettes que ce depot a choisi de ne pas reproduire).

## Ce que je ne fais pas

- Je ne tranche aucune question technique : le point de vigilance sur les garde-fous techniques
  (`ADR-0017`, `ADR-0063`, l'outillage de `outils/`) est route a `theo-archi`, pas decide ici ; la
  lecture architecturale de la section 7 (visuel/audio = forme d'item, pas mecanique) reprend un avis
  deja rendu par `theo-archi`, je ne la fabrique pas moi-meme et je la lui route pour confirmation.
- Je ne juge aucune qualite pedagogique de contenu : la question sur `S17-2` (section 5), les deux
  questions de niveau 2 (section 6) et les dix candidats non arbitres de la section 7 sont poses, pas
  repondus.
- Je ne prononce aucun Done pedagogique et ne fais bouger aucune ligne de
  `squad/programme/MATRICE-couverture.md` : les chiffres de la section 4 sont un objectif de sprint,
  pas un verdict ; ceux de la section 7 sont un plafond theorique, pas un objectif.
- Je ne recommande aucune famille de mecanique a ouvrir en premier (section 7.4) : ce jugement reste
  a `nadia-prof` (deja rendu, `AVIS-PRIORITE-PEDAGOGIQUE-s13-seconde-mecanique.md`) et a
  l'utilisatrice. Je nomme un declencheur objectif (l'etat de la reserve arbitree), pas un ordre de
  preference.
- Je ne cree ni le repertoire de sprint ni `planning.md` : `sofia-sm` les ecrit.
- Je ne fixe aucune date de gel pour la livraison test (arbitrage 2) : `sofia-sm` conduit le sprint
  a son rythme habituel.

## Impact

- `squad/decisions/produit/DEC-0053-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0053` ajoutee.
- `docs/backlog/backlog.md` : entrees `S13-2` et `S13-3` mises a jour (arbitrage prealable rendu
  desormais reflete, affectation a s22) ; entree `S17-2` completee d'une note posant la question de
  la section 5 et d'une affectation conditionnelle a s22 ; entree `REP-8` completee d'une note
  reflechissant l'arbitrage 1 ; section "Backlog - candidats de niveau 2 en attente d'arbitrage"
  ajoutee, portant les deux questions de la section 6 et les dix candidats de la section 7.
- `squad/programme/MATRICE-couverture.md` : note ajoutee (arbitrage 1 et sa portee sur la lecture du
  statut `couvert-valide`), aucune ligne ne change de statut. La section 7 (plafond de la mecanique
  unique) n'y ajoute rien de plus : instruction d'horizon, pas un mouvement de couverture.
- `squad/conformite/FICHE-bandit-manchot-multiplications-divisions-cm1.md`,
  `FICHE-bandit-manchot-problemes-durees-cm1.md` : note de priorisation s22 ajoutee, bloc de
  validation pedagogique non touche.
- `squad/conformite/FICHE-bandit-manchot-comprehension-cm1.md` : question de la section 5 versee en
  toutes lettres, bloc de validation pedagogique non touche.
- Aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/` n'est modifie par cette
  decision.

## Amendement du 2026-08-28 (apres premier commit) - relecture de `nadia-prof`, une correction que je fais, une question que je repose plutot que de la presumer tranchee

`nadia-prof` a rendu, en parallele et sans se lire avec moi, sa propre position pedagogique
d'ouverture de s22 (`squad/conformite/PORTE-PEDAGOGIQUE-apres-arbitrage-du-2026-08-28.md`, commits
`9f9489e` et `ad66c1f`, ce second commit trouvant ce document non commite dans l'arbre de travail).
Je verifie ce qui suit contre sa source avant de le reprendre, comme pour tout verdict.

### 1. Ce qu'elle confirme sur les trois candidats - je le reprends

- **`S13-2` et `S13-3` : aucune de ses vingt-et-une conditions ne tombe.** Elle avait ete ecrite sans
  motif de pli pour dix d'entre elles ; le pli n'etait qu'un avantage secondaire, jamais le motif,
  sur la onzieme (`MD-9`).
- **`S17-2` : confirme developpable.** `COMP-4` et `COMP-7` tombent comme conditions bloquantes,
  `COMP-1`, `COMP-2`, `COMP-3` et `COMP-5` restent inchangees et bloquantes. Elle l'ecrit sans
  ambiguite : *"le blocage que j'avais pose sur la story est leve - elle est developpable"*. Je
  releve donc, section 4 ci-dessus, la mention "conditionnel" en "confirme" pour cette ligne (voir
  point 3 ci-dessous pour la nuance qui reste).
- **Obstacle technique de `S13-3` deja route (section 2 ci-dessus, "garde-fous de rang lisant
  l'entier de tete") : perime, verifie par moi contre le code, pas seulement contre son dire.** Elle
  ecrit avoir relu `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`
  (`adapters/adapter-content/src/test/java/com/recreo/adapter/content/`) et y avoir trouve
  `dureeEnMinutes`/`lireValeur`, qui lisent deja une duree en minutes avant toute lecture de nombre
  nu, et un `rangDe` qui rend `null` sur un conflit de valeurs plutot que de classer silencieusement
  "la plus petite". **Verifie par moi-meme, independamment, dans le fichier cite** : les deux
  methodes existent telles qu'elle les decrit (`grep -n "dureeEnMinutes\|lireValeur\|rangDe"` sur ce
  fichier, lu en entier autour des occurrences). **L'obstacle que j'avais route a `theo-archi` et
  `yanis-qa`, section 2 et "Ce que je route" ci-dessus, n'a donc plus d'objet** : le garde-fou de
  rang est deja correct pour ce contenu, sans qu'aucun code n'ait ete touche par cette decision ni
  par la sienne.

### 2. Ce qui converge sur la capacite, sans que j'aie besoin de changer mon chiffre

`nadia-prof` avait d'abord tenu quatre paquets pour s22, sur un compte errone de s19 (elle comptait
les paquets audites, pas ceux ayant atteint un Done pedagogique dans le sprint). Elle a verifie
elle-meme contre la source primaire (`FICHE-bandit-manchot-nature-mot-cp.md` : Done prononce le
2026-08-25, hors de s19) et corrige son propre chiffre a trois, aligne sur ma section 3. Je ne
change donc rien a ma borne : elle est desormais verifiee deux fois, par deux methodes
independantes, avec le meme resultat.

### 3. Ce que je ne presume pas tranche, et que je repose a `sofia-sm` plutot que de le lire dans un fichier non commite

`nadia-prof` note, dans son document, une divergence de portee entre sa premiere lecture (section
1.4 de son fichier : l'arbitrage de l'utilisatrice porte sur l'indice seul, elle gardait le verdict
et les propositions comme motifs de refus possibles) et ma section "Point de vigilance" ci-dessus,
qui lit l'arbitrage plus largement ("un element du jeu - indice, verdict, proposition"). Elle ecrit
qu'elle n'oppose pas sa lecture et qu'elle applique la mienne des lors que ce document est commite -
**mais je constate, en relisant ma propre section "Point de vigilance", que ma formulation va
au-dela de ce qui m'a ete rapporte mot pour mot a l'ouverture du sprint** ("le motif 'indice sous le
pli'"). Le fichier de `nadia-prof` porte, au moment ou j'ecris cet amendement, des annotations non
commitees affirmant que l'utilisatrice aurait deja tranche cette lecture large - je ne les reprends
pas ici : un etat non commite n'est pas une decision au sens de la regle de gouvernance de ce depot,
quelle que soit la personne qui l'a ecrit.

**Ce que je fais donc, plutot que de trancher a sa place ou de presumer un arbitrage que je n'ai pas
vu ecrit** : je maintiens ma lecture large dans ce document (elle reste ma meilleure lecture de la
phrase de l'utilisatrice, "le pli sort de la porte pedagogique, pour tout le catalogue"), et je
demande explicitement a `sofia-sm` de faire confirmer par l'utilisatrice, dans un fichier commite,
si cette lecture large couvre bien la visibilite du verdict et des propositions avant reponse
(`COMP-4`), et pas seulement celle de l'indice apres une erreur (`REP-8`) - avec la consequence que
`nadia-prof` nomme elle-meme et que je reprends sans l'affaiblir : **sous la lecture large, un jeu
ou l'enfant presse un bouton sans recevoir aucun retour visible pourrait obtenir un Done
pedagogique**, ce qui etait exactement le motif de son `REFUSE` sur `cm1-reperes` a s20. Tant que
cette confirmation n'est pas ecrite, je garde le statut "confirme developpable" sur `S17-2` (point 1
ci-dessus, sur la seule foi du jugement pedagogique de `nadia-prof`, qui ne depend pas de cette
question de portee), mais je ne fais pas passer la mention de perimetre de mon tableau de section 4
au-dela de "conditionnel" tant que la portee exacte de l'arbitrage n'est pas ecrite dans un fichier
commite.

**Nuance neuve versee par `nadia-prof` sur `S17-2`, a porter dans la story plutot qu'a trancher
ici** : sa propriete n'est plus "visible sans defiler" mais "relisible" - un texte qu'on rejoint en
faisant defiler satisfait sa competence, un texte qu'on ne peut pas rejoindre du tout ne la
satisfait pas. Elle ne refusera pas dessus a s22, mais demande qu'un Done eventuel nomme la
propriete non couverte ("l'enfant peut avoir a memoriser le texte pour repondre") si elle n'est pas
tenue. Je verse cette nuance a la story `S17-2` plutot que de l'arbitrer moi-meme.

### Impact de cet amendement

- Ce fichier : section ajoutee, rien reecrit au-dessus.
- `docs/backlog/backlog.md` : note ajoutee sur `S13-3` (obstacle technique du garde-fou de rang
  ferme, verifie contre le code) et sur `S17-2` (confirme developpable sur le fond pedagogique,
  portee exacte de l'arbitrage encore a confirmer).
- `squad/conformite/FICHE-bandit-manchot-problemes-durees-cm1.md` : note de fermeture de l'obstacle
  technique.
- Route a `sofia-sm` : faire confirmer par l'utilisatrice, dans un fichier commite, la portee exacte
  de l'arbitrage 1 (indice seul, ou aussi verdict et propositions), avec la consequence nommee par
  `nadia-prof` ci-dessus.

## Amendement du 2026-08-28 (second, apres commit de la portee tranchee) - la question posee ci-dessus est fermee, ecrite dans un fichier commite

Entre mon amendement precedent et celui-ci, `nadia-prof` a commite
(`squad/conformite/PORTE-PEDAGOGIQUE-apres-arbitrage-du-2026-08-28.md`, commit dont je verifie qu'il
existe bien sur cette branche avant de m'en servir) sa section 10, qui porte l'arbitrage que
j'attendais : **l'utilisatrice, sollicitee par `sofia-sm` avec la consequence dans les mots de
`nadia-prof` et le precedent de son `REFUSE` sur `cm1-reperes` a s20, choisit la lecture large.**

**Ce qui est desormais ecrit, et que je reprends** : `nadia-prof` ne prononce plus de `REFUSE` sur
le motif "un element du jeu reste hors de vue sans faire defiler la page" - indice, verdict,
proposition, ou tout autre element du meme bloc. Ma lecture large de la section "Point de vigilance"
ci-dessus est donc confirmee, pas seulement maintenue par defaut. Je releve, section 4, la mention
de perimetre de `S17-2` de "conditionnel" a **confirme** : les trois candidats de ce sprint (`S13-2`,
`S13-3`, `S17-2`) sont desormais tous les trois pretes sans reserve de portee. **L'objectif de
sprint redevient donc, sans condition, 21/43 (48,8 %) si les trois aboutissent** - la formulation
"20 ou 21/43 selon `S17-2`" de la section 4 et du `DEC-index` est perimee sur ce point precis.

**La contrepartie ecrite par `nadia-prof`, `MNC-1`, que je reprends comme le mecanisme qui rend
"dette croissante et assumee" verifiable plutot qu'une formule.** Tout Done pedagogique prononce a
partir d'aujourd'hui porte, dans le bloc de validation de sa fiche, la mention fixe qu'elle a
redigee : ce Done ne couvre pas la visibilite sans defilement d'aucun element du jeu, cette
propriete est sortie par cet arbitrage, sa dette est ouverte et non mesuree sur ce paquet. Un Done
qui ne porte pas cette mention est incomplet - c'est un defaut de fiche a corriger, pas un motif de
refus du paquet. Je verse `MNC-1` a la note que j'ai deja ajoutee dans
`squad/programme/MATRICE-couverture.md`, pour que la matrice pointe vers le texte exact plutot que
vers ma paraphrase.

**Ce que je fais du texte de `MNC-1`, sur demande explicite de `nadia-prof` relayee par
`sofia-sm`** : je ne le recopie pas dans ce document, dont ce n'est pas la fonction, mais je le
verse dans le gabarit du bloc de validation pedagogique (`squad/TEMPLATES.md`, section 7), pour que
sa presence sur chaque fiche a venir ne depende pas de la memoire de `nadia-prof` d'un sprint a
l'autre - c'est le geste qui me revient, elle en est l'autrice.

### Impact de ce second amendement

- Ce fichier : section ajoutee, rien reecrit au-dessus - la question posee dans l'amendement
  precedent reste lisible telle qu'ecrite, a l'heure ou elle etait encore ouverte.
- `squad/TEMPLATES.md` : le gabarit de la fiche de conformite (section 7) porte desormais une
  sous-section "Mentions de non-couverture opposables", avec `MNC-1` reprise mot pour mot depuis sa
  source.
- `squad/decisions/produit/DEC-index.md` : a corriger par moi dans le meme geste, la ligne `DEC-0053`
  portant encore "20 ou 21/43 selon `S17-2`".
- `squad/programme/MATRICE-couverture.md` : note complementaire ajoutee, renvoi a `MNC-1`.
- `docs/backlog/backlog.md` : note ajoutee sur `S17-2`, perimetre confirme sans reserve.
