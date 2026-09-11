# DEC-0087 - Cloture de s34 et fin de la fenetre du Plan 2 : le plafond de base est atteint pour
# la premiere fois (6 lignes sur 6), le registre de dette monte malgre tout de 9,0 a 9,5, le
# palier 2 ne ferme aucune de ses deux lignes ; la strategie au-dela du Plan 2 est routee a
# l'utilisatrice, qui seule a arbitre le choix du Plan 2 lui-meme

- **Type** : constat de cloture de `camille-po`, sur la foi des verdicts des trois instances de
  `nadia-prof` et du recompte independant de la matrice ; routage d'une question de strategie a
  l'utilisatrice, sans arbitrage nouveau rendu ici.
- **Date** : 2026-09-11.
- **Auteur** : `camille-po`.
- **Sprint** : s34 (cloture). Dernier sprint de la fenetre de trois sprints (s32 a s34) que
  `DEC-0083` a ouverte pour le Plan 2.
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision constate
  un resultat deja arbitre (`DEC-0083`, `DEC-0086`) et route sa suite ; les competences ligne a
  ligne sont nommees dans `docs/backlog/backlog.md` et dans `squad/programme/MATRICE-couverture.md`.
- **Statut** : Acceptee.
- **Lien** : `DEC-0083` (Plan 2, fenetre de trois sprints, borne, palier 2), `DEC-0084` (cloture de
  s32), `DEC-0085` (strategie de s33, premier report du palier 2), `DEC-0086` (strategie de s34,
  abandon de la projection de dette, point de controle poste/story, amendement engageant l'entree
  minimale du palier 2), `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/review.md`
  (`sofia-sm`), `verdicts-nadia-instance-A.md`, `-B.md`, `-C.md` (`nadia-prof`),
  `squad/programme/MATRICE-couverture.md` (section "Synthese apres les verdicts du sprint 34"),
  `docs/backlog/backlog.md`.

---

## 1. Le fait a enregistrer : le resultat contre le plan

`DEC-0086`, apres son amendement du 2026-09-10, chiffrait pour s34 un **plafond arithmetique de
8 lignes** (77/138 = 55,8 %, palier 2 compris) et une **attente raisonnable de `nadia-prof` de 2 a
4 lignes** sur les six postes prioritaires du vivier (71 a 73/138, soit 51,4 % a 52,9 %),
explicitement non etendue aux deux lignes du palier 2.

**Le resultat reel : six lignes ferment, aucune du palier 2.** `HG-CM1-12`, `EMC-CP-07`,
`EPS-CP-04`, `EPS-CP-08`, `EPS-CP-10` et `EPS-CP-11` passent `couvert-valide`. Recompte independant
sur les dix-huit tableaux de `squad/programme/MATRICE-couverture.md`, chaque ligne de donnees
reclassee telle qu'elle est ecrite apres mise a jour, methode verifiee contre deux defauts trouves
dans ma propre premiere passe (une mention de couverture partielle niee dans sa propre cellule -
`QLM-CP-11` - comptee a tort comme positive par une recherche de sous-chaine naive ; un statut
`non couvert` mis en gras non reconnu par un motif qui ne cherchait que sa forme non grasse -
`EMC-CP-07` avant mouvement) : **75/138 = 54,3 %**, contre 69/138 = 50,0 % a l'ouverture. **Je
confirme le chiffre de `sofia-sm`** (`review.md`, section 1) : nos deux recomptes, faits
independamment, tombent sur le meme total a l'entree comme a la sortie.

**Six lignes sur six visees au plafond de base est le plafond arithmetique atteint a l'unite pres,
pour la premiere fois depuis l'ouverture de cette strategie** : s32 fermait 3 lignes sur 8 visees,
s33 fermait 3 lignes sur 9 visees, s34 ferme 6 lignes sur 6. C'est aussi au-dessus de la borne
haute de l'attente raisonnable de `nadia-prof` (4), qui elle-meme mesurait, pour la premiere fois,
si la discipline neuve `RES-1` a `RES-4` porte son objet et non seulement sa lettre - la reponse
que les trois instances rendent, chacune par un chemin independant, est oui (`review.md`,
section 5 ; `verdicts-nadia-instance-C.md`, section 4).

**Les deux lignes du palier 2 ne ferment pas.** `QLM-CP-11` (plan de la classe) n'a jamais ete
livree : la geometrie de deux cadres et le plancher de lisibilite d'un libelle de zone restent a
trancher (`ZON-15`, `ZON-16`, routes a `ines-design` et `nadia-prof`). `QLM-CP-14` (frise de la
journee) a ete livree et **refusee** (`FRI-R1`, `FRI-R2`, bloquantes) : neuf items sur onze se
gagnent sans regarder le dessin, et le pictogramme du lever ne se lit pas comme un lit. **Les deux
faits sont vrais ensemble et je les ecris ensemble, comme `review.md` le fait** (section 3) : le
plafond de base est atteint a l'unite pres, et l'extension que le palier 2 devait apporter (+2,
jusqu'a 77/138 = 55,8 %) reste entierement non realisee.

**Trois refus, deux sans consequence sur une ligne, un sur une ligne du palier 2** : `S34-SCO1-
REMEDE` (`SCO-R1`, bloquante, portee transverse - aucune ligne de cette matrice n'est concernee) ;
`S34-EXC-TACHEB` (`EXC-R10`, bloquante - `EMC-CP-03` n'est pas rouverte et reste `couvert-valide`) ;
`QLM-CP-14` (deja compte ci-dessus).

**Aucun de ces neuf resultats n'est rejuge ici.** Les six fermetures et les trois refus sont ceux
que `nadia-prof` prononce dans ses trois documents de verdict ; cette decision les enregistre et en
tire les consequences de gouvernance.

## 2. Le cout depense par instance, contre le budget de 9,0 engage sur une borne de 12,0

| Instance | Borne engagee | Depense mesure | Idle | Motif de l'idle |
|---|---|---|---|---|
| A | 3,0 | 2,0 | 1,0 | `S34-CONS-4` non engagee, aucune branche ouverte - aucun audit du a une story qui n'existe pas |
| B | 3,5 (1,5 EMC CP + 2,0 palier 2) | 3,5 | 0 | Les trois postes, y compris le refus et le palier 2 audite en bloc, ont chacun recu un audit complet |
| C | 2,5 | 2,5 | 0 | Les trois stories ont chacune recu un audit complet |
| **Total** | **9,0** | **8,0** | **1,0** | |

Recopie de `review.md`, section 8, sans recalcul : le seul idle du sprint vient de `S34-CONS-4`,
qui n'a jamais recu de story - c'est un fait de capacite de developpement, pas d'audit.

## 3. Le registre de dette a la cloture : 9,5, mesure et non projete (`DEC-0086`, section 4)

Methode reprise a l'identique de celle qu'ouvrait `RECALCUL-registre-de-dette-ouverture-s34.md` :
un paquet est **PAYE** quand la totalite de ses codes connus a l'ouverture sont leves et que
l'increment qui les leve n'est pas refuse ; sinon il reste **NON PAYE** en entier, au prix plein du
paquet.

**Verification independante du chiffre de `review.md` (section 7), poste par poste, plutot que
reprise sur sa seule ligne de total.** Seize postes a l'ouverture (`DEC-0086`, section 2) : six
touches ce sprint, dix non touches.

| Categorie | Postes | Montant a l'ouverture | Montant a la cloture |
|---|---|---|---|
| Six touches, non payes | `cm1-histoire-geographie`, `cp-eps-regles-jeu-collectif`, `cm1-anglais` (0,5 chacun), `cp-emc-exclusion` (1,0, increment refuse) | 3,0 | **3,0, inchange** |
| Deux touches, payes puis remis a neuf | `cp-emc-regle-souhait-loi`, `cp-eps-regles-de-securite` (0,5 chacun) | 1,0 | **1,0, inchange** - chacun leve puis rouvert au meme montant par ses reserves neuves (`RSL-R1`/`R2`, `SEC-R6`), sans effet net |
| Dix non touches | neuf postes a 0,5 (`cp-emc-regles-de-vie`, `cp-emc-permis-interdit`, `cp-emc-emotions-autrui`, `cp-emc-formules-de-politesse`, `cm1-sciences-signal-information`, `cm1-sciences-chaine-alimentaire`, `cm1-division-posee`, `cm1-emc-fait-opinion`, `cm1-emc-droit-devoir`), groupe 2b a 1,0 | 5,5 | **5,5, inchange** |
| Poste neuf, jamais present avant ce sprint | `cp-eps-corps-effort` (`EFF-R1` a `EFF-R7`, rejeu 0,5 pour le paquet entier) | - | **+0,5** |
| **Total** | | **9,0** (10 non touches, corrige a 5,5) + 3,0 + 1,0 | **9,5** |

**Une ligne de `review.md` (section 7, tableau "Chiffre mesure a la cloture de s34") porte un
montant incoherent avec sa propre enumeration** : elle chiffre "Dix postes non touches (inchanges)"
a "6,0 + 1,0 (groupe 2b) = 7,0", alors que sa propre liste de ces dix postes (section 7, paragraphe
"Composition de la dette de 9,0 a l'ouverture") en nomme neuf a 0,5 chacun (4,5) plus le groupe 2b à
1,0, soit **5,5 et non 7,0**. Je ne corrige pas `review.md`, hors de mon perimetre pour ce document
- je le route a `sofia-sm`. **Ce que cette erreur ne change pas** : recalculee poste par poste sur
sa propre enumeration plutot que sur cette seule ligne, la somme des seize postes retombe
exactement sur **9,5**, le chiffre que `review.md` annonce comme total et que je confirme
independamment ci-dessus. Le total est donc juste ; une ligne intermediaire qui y menait ne
l'etait pas.

**Le registre augmente de 9,0 a 9,5 malgre six lignes fermees, entierement du fait du poste neuf
`cp-eps-corps-effort`** (+0,5) : les deux paquets qui se clarifient (`cp-emc-regle-souhait-loi`,
`cp-eps-regles-de-securite`) rouvrent chacun une dette de meme montant dans le meme geste qui les
soldait, exactement le mecanisme que `DEC-0086` (section 4) nommait comme la premiere raison
d'abandonner la projection de descente. **Plancher au sens de `DEC-0086`** (ouverture moins les
postes dont le paquet clarifie ET dont la ligne ferme reellement) : `cp-emc-regle-souhait-loi` et
`cp-eps-regles-de-securite` sont les deux seuls dans ce cas -> 9,0 - 1,0 = **8,0**. Le chiffre
mesure (9,5) est superieur au plancher, ce qui est attendu : le plancher est une borne basse, pas
une cible.

**Je ne ratifie pas ce chiffre comme une decision produit close** : la methode est celle que j'ai
ecrite et appliquee moi-meme pour l'ouverture de s34 (`DEC-0086`), `sofia-sm` l'applique a
l'identique pour la cloture, et je viens de la verifier poste par poste plutot que de la reprendre
sur sa seule ligne de synthese. C'est cette verification qui constitue la ratification : le
registre de dette a l'ouverture de s35 est **9,5**.

## 4. La fenetre du Plan 2 se referme : le palier 2 n'est ni ferme ni reporte une troisieme fois

**`DEC-0083` ouvrait une fenetre de trois sprints (s32 a s34) pour le Plan 2. Elle se clot avec
s34, quel que soit le sort du palier 2** - `DEC-0086`, section 9, l'ecrivait deja comme un fait
distinct de la question de savoir si le palier 2 fermerait dans cette fenetre.

**Ce qui s'est passe n'est pas un troisieme report.** Le palier 2 avait deja ete finance a s32
(`DEC-0083`, section 13) puis reporte une premiere fois de s33 a s34 (`DEC-0085`). A s34,
l'utilisatrice a tranche la question que je lui avais explicitement routee (`DEC-0086`, section 9) :
elle a engage l'entree minimale du palier 2 (`DEC-0086`, section 12.1). **Il a donc ete engage,
audite, et n'a pas ferme** - c'est une categorie distincte d'un report silencieux ou nomme : le
budget a ete depense (section 2 ci-dessus, instance B), l'audit a eu lieu (`verdicts-nadia-
instance-B.md`, section 4), et le resultat est un refus motive plus une non-livraison, pas une
absence de tentative.

**La fenetre de trois sprints du Plan 2 se referme sur ce bilan** : 12 lignes fermees sur les trois
sprints (3 a s32, 3 a s33, 6 a s34) contre un plafond arithmetique cumule de 8+9+8=25 et une
attente raisonnable cumulee de 19 a 24 ; le palier 2, engage a son entree minimale au dernier
sprint de la fenetre, ferme 0 ligne sur 2. **Ce bilan n'est pas rejuge ici** : il est enregistre
pour fonder la section suivante.

## 5. La strategie au-dela du Plan 2 : routee a l'utilisatrice, pas tranchee par moi

**`DEC-0086` (sections 9 et 12.3) l'ecrivait deja comme une chose due, pas comme une chose que je
deciderais seule** : *"la fenetre de trois sprints de `DEC-0083` se clot avec s34 [...] et une
strategie neuve, au-dela du Plan 2, sera due a la cloture de s34."* Cette cloture est ce document.

**Je ne tranche pas cette question moi-meme, pour le meme motif que je ne l'ai pas tranchee a
l'ouverture de s34** (`DEC-0086`, section 9, sur l'engagement du palier 2) : **le choix du Plan 2
lui-meme etait un arbitrage de l'utilisatrice** (`DEC-0083`, entre Plan 1 - couverture maximale -
et Plan 2 - couverture soutenable), pas une decision de `camille-po` seule. Je n'ai pas de mandat
neuf qui me donnerait autorite pour choisir a sa place ce qui vient apres un plan qu'elle a
elle-meme arbitre.

**Les deux faits qui motivent que cette question soit posee maintenant, sans que je les tranche** :

1. **Le plafond de base est atteint pour la premiere fois** (section 1) - le produit peut fermer
   la totalite des lignes qu'une strategie de sprint chiffre comme plafond, ce qui n'etait arrive
   ni a s32 ni a s33.
2. **Le registre de dette monte malgre tout, de 9,0 a 9,5** (section 3) - le mecanisme qui le
   produit (`DEC-0086`, section 4 : aucun paquet reaudite n'a jamais solde sa dette a zero) n'est
   pas un accident de s34, c'est le troisieme sprint consecutif ou il joue, et il joue maintenant
   au moment ou le sprint est par ailleurs le plus reussi de la strategie sur son propre critere de
   lignes fermees.

**Je ne fais de ces deux faits ni une recommandation de continuer le Plan 2 tel quel, ni une
recommandation d'en changer.** Je les nomme parce qu'ils sont la matiere sur laquelle une decision
de strategie devrait porter, et parce qu'un routage qui ne les nommerait pas serait moins qu'un
routage.

**Ce que je route explicitement, avant tout planning de s35** : la question de la strategie de
couverture au-dela de la fenetre du Plan 2 - poursuite du Plan 2 sans fenetre nommee, plan neuf, ou
tout autre arbitrage - est posee a l'utilisatrice. A defaut de reponse avant la cloture du planning
de s35, `sofia-sm` planifie s35 sur la seule base des stories de reprise routees ci-dessous
(section 6), sans plafond ni attente chiffres au-dela de ce que ces reprises portent deja - je ne
laisse pas ce defaut glisser sans etre nomme, a la difference du declencheur devenu recurrent que
`DEC-0086` (section 9) avait deja signale sur le palier 2 lui-meme.

## 6. Ce que je decide

1. **Le resultat de s34 est enregistre** : six lignes ferment (`HG-CM1-12`, `EMC-CP-07`,
   `EPS-CP-04`, `EPS-CP-08`, `EPS-CP-10`, `EPS-CP-11`), 75/138 = 54,3 %, plafond de base atteint a
   l'unite pres pour la premiere fois depuis l'ouverture de cette strategie ; recompte independant
   sur les dix-huit tableaux, confirme (section 1).
2. **Le registre de dette a la cloture de s34 est 9,5**, verifie poste par poste (section 3) ; une
   ligne intermediaire de `review.md` est signalee incoherente avec sa propre enumeration sans en
   changer le total, et routee a `sofia-sm` pour correction dans ce document.
3. **La fenetre de trois sprints de `DEC-0083` pour le Plan 2 se referme avec s34.** Le palier 2,
   engage a son entree minimale sur arbitrage de l'utilisatrice, ferme 0 ligne sur 2 dans cette
   fenetre - ce n'est ni un troisieme report ni une fermeture, et je le nomme comme tel (section 4).
4. **La strategie au-dela du Plan 2 est routee a l'utilisatrice**, avec les deux faits qui la
   motivent, sans etre tranchee ici (section 5).
5. **Stories de reprise routees a `sofia-sm` pour le planning de s35**, reprises telles que les
   trois verdicts les nomment : `SEC-R6` (indice de l'item 3, cage a grimper) ; `JEU-R11`, `JEU-R12`
   (reconduites, non levees) ; `EFF-R1` a `EFF-R7` (rejeu 0,5 pour le paquet `cp-eps-corps-effort`
   entier) ; `RSL-R1`, `RSL-R2` (regle-souhait-loi) ; `EXC-R10` (bloquante), `EXC-R11`, `EXC-R12`
   (tache B d'exclusion, un meme geste) ; `FRI-R1` a `FRI-R5` (frise de la journee, deux bloquantes)
   plus les deux obstacles de `QLM-CP-11` (`ZON-15` a `ines-design`, `ZON-16` a `nadia-prof`) ; le
   corollaire du remede de `SCO-1` (ecran de fin de partie, `SCO-R1`) ; `S34-CONS-4`, non engagee,
   reconduite.
6. **Aucun arbitrage de `DEC-0086` n'est rouvert.**

## 7. Ce que cette decision ne fait pas

- Elle ne prononce aucun Done pedagogique : les six verdicts qui ferment une ligne et les trois
  refus sont de `nadia-prof`, dans les documents cites.
- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` au-dela de ce que les
  verdicts de `nadia-prof` autorisent et que j'ai deja execute dans ce fichier.
- Elle ne fixe aucun plafond ni aucune attente chiffree pour s35 : c'est une decision de strategie
  neuve, distincte de cette cloture, et elle attend soit l'arbitrage de l'utilisatrice sur la
  question routee en section 5, soit le planning de `sofia-sm` a defaut de reponse.
- Elle ne tranche pas la geometrie des cadres du plan de classe, ni le plancher de lisibilite d'un
  libelle de zone, ni le corollaire de l'ecran de fin de partie, ni le texte des cinq indices de la
  tache B d'exclusion : tous restent routes comme les trois verdicts les routent.
- Elle ne corrige aucun fichier hors de son propre perimetre : l'incoherence trouvee dans
  `review.md` (section 3) est nommee et routee a `sofia-sm`, pas corrigee ici.
- Elle ne rejuge aucun des neuf resultats de ce sprint (six fermetures, trois refus).
