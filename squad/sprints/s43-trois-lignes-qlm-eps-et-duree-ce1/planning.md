# Planning - s43 - trois lignes : questionner le monde CP, education physique et sportive CM1,
mathematiques CE1

- **Horodatage de debut** : 2026-09-25 15:35 (UTC+02:00).
- **Casquette** : `sofia-sm`, Scrum Master.
- **Slug** : `s43-trois-lignes-qlm-eps-et-duree-ce1`, dossier deja ouvert par `theo-archi` (son
  avis de faisabilite y est commite avant ce planning, `9e388a11`).

## 1. Lignee du worktree

Verifie avant toute ecriture (`git rev-parse --abbrev-ref HEAD` puis
`bash outils/verifier-lignee.sh`) : `s43-sofia` (`../recreo-s43-sofia`) descend de `round-two` a
`9e388a11` (dernier commit sur `round-two` au moment de l'ouverture, `ADR-0101` et `ADR-0102`),
sans aucun autre ecart.

## 2. Objectif gele, et sa provenance

**Objectif** (`DEC-0099`, Acceptee, amendee le 2026-09-25, section 9) : fermer les trois lots du
perimetre rechiffre de s43 - `QLM-CP-08`, `EPS-CM1-10`, `MAT-CE1-12` - avec une **instance unique**
de `nadia-prof`, cible 4,0/5,0, marge 1,0. Si les trois lignes ferment integralement, la matrice de
couverture passe de 98/156 (62,8 %, etat de sortie de s42) a 101/156 (64,7 %).

**Ce que ce planning ne rouvre pas** : `DEC-0099` elle-meme, les tarifs de ses trois lignes, la
composition a trois lots plutot que deux (justifiee par la source factuelle `nadia-prof` sur
`MAT-CE1-12`, tarif 1,0). Les trois lots sont deja verifies `non couvert` dans
`MATRICE-couverture.md` et resolus ligne par ligne, citation comprise, par l'avis de faisabilite de
`theo-archi` (section 2 de cet avis).

## 3. Stories deja instruites, avis de faisabilite deja rendu

Les trois stories sont deja ecrites avec criteres d'acceptation testables dans
`docs/backlog/backlog.md`, section "Sprint 43". L'avis de faisabilite de `theo-archi`
(`squad/sprints/s43-trois-lignes-qlm-eps-et-duree-ce1/avis-faisabilite.md`, fusionne avant ce
planning) porte sur les trois :

| Lot | Ligne | Competence programme visee | Tarif | Verdict `theo-archi` |
|---|---|---|---|---|
| `S43-QLM-NUMERIQUE` | `QLM-CP-08` | Questionner le monde, CP - reconnaitre les objets numeriques du quotidien et quelques regles simples de leur usage | 1,5 | Faisable en l'etat, sans `ADR` ni regle neuve. Deux adjacences avec du contenu deja servi dans le meme lot, aucune bloquante (section 8 de l'avis) |
| `S43-EPS-EFFETS-ACTIVITE` | `EPS-CM1-10` | Education physique et sportive, CM1 - connaitre les effets de l'activite physique sur le corps et les regles de securite qui l'entourent | 1,5 | **Faisable sous condition**, sans `ADR` ni regle neuve. La frontiere avec les sciences CM1 que la story instruit est prospective ; le recouvrement reel est vertical, avec le CP (section 7 de l'avis) - condition transmise section 5 ci-dessous |
| `S43-MAT-DUREE` | `MAT-CE1-12` | Mathematiques, CE1 - resoudre des problemes de durees et d'horaires | 1,0 | **Faisable sous condition**. Seul lot du sprint qui porte un `ADR` du (`ADR-0101`, barre de longueur du CE1) : deja fusionne avant ce planning, la condition est levee. Une collision reelle avec `cm1-problemes-durees`, hors du perimetre verifie par `nadia-prof`, reste a router (section 6 de l'avis) - condition transmise section 6 ci-dessous |

Porte technique mesuree par `theo-archi` sur l'arbre de `5bd99d74` :
`bash outils/mvn-jdk21.sh -B clean verify` vert, cinq modules et le reacteur complet - ce vert ne
vaut que pour le job `build` de la CI, les trois autres jobs (`prefixes`, `chemins`, `outils`) ne
sont pas mesures par cette commande.

## 4. Les deux gestes techniques deja payes avant le developpement

`theo-archi` note dans son avis, en amendement du 2026-09-25 : les deux couts techniques du lot
`S43-MAT-DUREE` sont payes avant ce planning, dans des commits deja fusionnes sur `round-two`
(`9e388a11`) :

- **`ADR-0101`** encode la barre de longueur d'enonce du CE1 (`LON-CE1-1`, `LON-CE1-2`) avec son
  cliquet exact - plafond de dette fixe a 4, mesure sur les 144 enonces CE1 deja servis, tous
  portes par `ce1-longueurs.yaml`. Le paquet neuf de `MAT-CE1-12` entre dans cette regle des son
  premier commit.
- **`ADR-0102`** corrige la lecture des durees : les deux ecritures que `DUR-CE1-3` autorise
  (`2 h 30 min` et `2 heures et demie`) se lisent desormais toutes deux en minutes. Aucun
  arbitrage technique sur l'ecriture n'est donc du au developpeur affecte.

Ce que cela ne retire pas, et qui reste une condition du lot : le cout de l'allocation de
sous-partie (`ADR-0097`/`ADR-0098`, `SousPartieDeMathematiquesCe1`, huitieme entree a ajouter dans
le meme diff que le paquet) et la collision de la section 6 ci-dessous.

## 5. Condition transmise sur `S43-EPS-EFFETS-ACTIVITE` - differenciation CM1/CP reelle exigee

**Fait transmis explicitement, non tranche ici** (avis de faisabilite, section 7.2) : les trois
composantes du libelle CM1 de `EPS-CM1-10` (effets de l'activite physique, echauffement/
hydratation/recuperation, regles de securite) sont deja toutes servies au CP, par deux paquets
livres - `cp-eps-corps-effort` (`EPS-CP-10`, `EPS-CP-11`) et `cp-eps-regles-de-securite`
(`EPS-CP-04`). Les enonces servis au CP et le besoin ecrit par la story CM1 sont, au mot pres, la
meme phrase a deux niveaux. **Aucun garde-fou du depot ne compare deux paquets differents**, et
`NIVEAU_BORNE` ne borne que le CP : rien ne force structurellement le paquet CM1 a depasser en
exigence son equivalent CP deja livre.

**Condition de verdict, transmise telle quelle a `nadia-prof` et au developpeur affecte, sur le
meme patron que `S42-EMC-SYMBOLES-CM1`** : lire `cp-eps-corps-effort` et
`cp-eps-regles-de-securite` avant que `S43-EPS-EFFETS-ACTIVITE` ne soit ecrit, et poser le
discriminant qui fait que le paquet CM1 est une differenciation reelle et non une reformulation
superficielle du contenu CP. Ce planning ne dit pas ce que ce discriminant doit etre - question
pedagogique, elle appartient a `nadia-prof` seule. L'instance de ce sprint etant **unique**, le
risque de s42 - deux instances qui ne se lisent pas - ne se pose pas ; il reste celui d'un contenu
livre a un sprint anterieur et que personne ne relit sans cette condition explicite.

## 6. Condition transmise sur `S43-MAT-DUREE` - collision avec `cm1-problemes-durees`, conformite `ADR-0101`/`ADR-0102`

**Fait transmis explicitement, non tranche ici** (avis de faisabilite, section 6) : `cm1-problemes-
durees` (`MAT-CM1-10`, 24 items) sert deja la forme exacte de `MAT-CE1-12` sur ses douze premiers
items - memes situations (`DUR-CE1-6`), meme conception d'ecriture (instant abrege, duree en
toutes lettres, `DUR-CE1-3`). Ce n'est ni une faute, ni un double-compte de couverture : deux
lignes distinctes, deux niveaux distincts, `ADR-0081` clause 5 tient. **Aucun garde-fou du depot ne
compare deux paquets** : le seul discriminant prescrit et non deja tenu structurellement est
`DUR-CE1-2` - minutes bornees a `00` ou `30`, la ou le CM1 emploie des minutes quelconques.

**Condition de verdict, transmise a `nadia-prof` avant que le paquet ne soit ecrit** : le
discriminant entre les deux paquets est une question de contenu, non de mecanique - `nadia-prof` le
pose, ce planning ne le pose pas.

**Deux conditions techniques supplementaires, tenues avant ce planning et rappelees pour le
developpeur affecte** :

1. Le paquet neuf se conforme des l'ecriture a `ADR-0101` (barre de longueur CE1, 20 mots/3
   phrases, la derniere etant la question) et a `ADR-0102` (les deux ecritures de duree que
   `DUR-CE1-3` autorise se lisent en minutes - aucun arbitrage d'ecriture n'est du).
2. Le paquet declare sa competence dans `SousPartieDeMathematiquesCe1` (`ADR-0097`/`ADR-0098`,
   huitieme entree) et ne s'inscrit pas dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` - ses propositions
   sont lues comme des durees, il est dans le domaine (avis de faisabilite, section 10, point 2).

## 7. Affectations nominatives finales

**Proposition de `camille-po`, verifiee et confirmee sans ajustement** (`docs/backlog/backlog.md`,
section "Sprint 43") : trois lots, trois developpeurs, un chacun.

| Lot | Ligne | Developpeur | Condition |
|---|---|---|---|
| `S43-QLM-NUMERIQUE` | `QLM-CP-08` | `marc-dev` | - |
| `S43-EPS-EFFETS-ACTIVITE` | `EPS-CM1-10` | `lea-dev` | Differenciation CM1/CP reelle, section 5 |
| `S43-MAT-DUREE` | `MAT-CE1-12` | `sofia-sm` (casquette dev) | Conformite `ADR-0101`/`ADR-0102` et collision `cm1-problemes-durees`, section 6 |

`sofia-sm` (casquette developpeuse) prend `S43-MAT-DUREE` : matiere dont `nadia-prof` est la
source factuelle des conditions techniques deja tranchees (`ADR-0101`, `ADR-0102`), le lot le
moins expose a un refus d'exactitude, coherent avec sa double casquette. `marc-dev` prend
`S43-QLM-NUMERIQUE`, `lea-dev` prend `S43-EPS-EFFETS-ACTIVITE`.

## 8. Instance unique de `nadia-prof` - Done pedagogique en fin de sprint

Une seule instance de `nadia-prof` audite les trois lots (`DEC-0099`, section 9). Elle porte les
deux conditions transmises ci-dessus (sections 5 et 6) avant d'auditer `S43-EPS-EFFETS-ACTIVITE`
et `S43-MAT-DUREE`. Budget opposable 5,0, cout retenu 4,0, marge 1,0 - non allouee entre les lots,
au niveau du sprint.

## 9. Verification `PAR-16` - point de controle poste/story

| Element | Affectation nominative | Issue |
|---|---|---|
| `S43-QLM-NUMERIQUE` (`QLM-CP-08`) | `marc-dev`, puis `nadia-prof`, instance unique | Engagee |
| `S43-EPS-EFFETS-ACTIVITE` (`EPS-CM1-10`) | `lea-dev`, puis `nadia-prof`, instance unique | Engagee sous condition (section 5) |
| `S43-MAT-DUREE` (`MAT-CE1-12`) | `sofia-sm` (casquette dev), puis `nadia-prof`, instance unique | Engagee sous condition (section 6) |

Aucun element n'est "non engage, budget libere" : les trois lots du perimetre amende de `DEC-0099`
sont tous affectes.

## 10. Ce que ce planning ne fait pas

- Il n'engage aucun `ADR` ni aucune regle ArchUnit neuve : les deux seuls dus sur le perimetre
  (`ADR-0101`, `ADR-0102`) sont deja fusionnes avant son ouverture.
- Il ne pose aucun discriminant pour `S43-EPS-EFFETS-ACTIVITE` (section 5) ni pour `S43-MAT-DUREE`
  (section 6) : question pedagogique, a `nadia-prof` seule.
- Il ne tranche pas l'ecriture des durees du paquet neuf : les deux formes que `DUR-CE1-3` autorise
  se lisent desormais identiquement (`ADR-0102`), la question technique qui restait ouverte dans
  l'avis de faisabilite est fermee.
- Il ne corrige aucune mention de `MATRICE-couverture.md` : tenue de matrice, a `camille-po`, en
  fin de sprint.
- Il ne prononce aucun Done pedagogique ni aucun Done technique : rien n'est encore livre a ce
  sprint.
- Il ne rouvre pas `DEC-0099`.

## Decisions prises

Aucune decision de sprint n'est prise dans ce planning. `DEC-0099` (objectif et perimetre, amendee)
et l'avis de faisabilite de `theo-archi` (`ADR-0101`, `ADR-0102` compris) existent deja avant ce
planning ; ce document confirme l'affectation nominative proposee par `camille-po` sans l'amender,
transmet explicitement les deux conditions techniques aux destinataires nommes, et ouvre le sprint
au developpement.
