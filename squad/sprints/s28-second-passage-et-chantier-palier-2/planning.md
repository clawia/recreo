# [Sprint 28] Planning - 2026-09-03

- **Horodatage de debut** : 2026-09-03 22:18
- **Anime par** : sofia-sm
- **Participants** : sofia-sm (SM et dev), marc-dev, lea-dev, theo-archi (avis de faisabilite
  rendu ci-dessous et marqueur `HIS-4` a definir), nadia-prof (arbitrage prealable deja rendu,
  merge ci-dessous), camille-po (decisions produit deja rendues, merges ci-dessous)

## 0. Ce qui est deja entre, avant l'ouverture

A la difference de s27, rien ne dormait sur une branche separee : les cinq commits de pre-sprint
sont deja sur `round-two`, dans l'ordre ou ils ont ete ecrits :

| Commit | Agent | Contenu |
|---|---|---|
| `94f8be8` | `camille-po` | `DEC-0074` - quatre arbitrages de l'utilisatrice pour s28 (perimetre a 4,5 avant correction, palier 2 en chantier de mecanique, dix referentiels a ecrire, execution normale) |
| `1cf80f4` | `nadia-prof` | arbitrage prealable de s28 - corrige le prix a 5,0, ramene la cible de 14 a 6-7 fermetures, et livre les dix referentiels des enseignements absents |
| `f251b6c` | `theo-archi` | `ADR-0080` - la mecanique de palier 2 (designation sur image), statut Propose |
| `9c48b9c` | `nadia-prof` | avis sur le fait d'architecture d'`ADR-0080` - le palier 2 ouvre cinq lignes, pas six ; la ligne des echelles en sort |
| `ef4a636` | `camille-po` | matrice a 35/138 (25,4 %), `DEC-0074` amendee (prix 5,0), `DEC-0075` ouverte, stories `S28-1` a `S28-5` |

**Verification faite, pas supposee** : `git diff --name-only c9a9e44 ef4a636` liste 22 fichiers
modifies, **aucun `.java`**. Les cinq commits ne touchent que de la documentation, des
referentiels de programme et le backlog.

**Mesure d'entree technique**, `round-two` a `c9a9e44` (avant les cinq commits ci-dessus) :
`mvn -B clean verify` sur le reacteur complet (racine, pas de `-pl`). **BUILD SUCCESS. 1128
tests, 0 echec, 0 erreur** (somme de l'attribut `tests="..."` des 157 rapports XML
surefire/failsafe du reacteur, methode de l'amendement s11). **Ce chiffre vaut aussi pour l'etat
actuel du depot (`ef4a636`)**, puisqu'aucun fichier Java n'a bouge entre les deux points - ce
n'est pas une mesure reconduite sans verification, c'est une mesure verifiee inchangee.

## 1. Objectif du sprint

**Fermer entre six et sept des quatorze lignes ouvertes a s27 (questionner le monde CP,
histoire-geographie CM1), ouvrir un premier lot d'anglais CM1 et un lot de calcul pose CM1, et
construire la mecanique du palier 2 comme chantier testable, sans paquet ni ecran servi.**

Perimetre arbitre par l'utilisatrice le 2026-09-03 (`DEC-0074`, amendee le meme jour sur
l'arbitrage prealable de `nadia-prof`) : les quatre lots de contenu restent inchanges a leur
composition initiale, et le chantier de mecanique du palier 2 (`ADR-0080`) entre en s28 sans
fermer aucune ligne.

## 2. Taux d'entree du sprint

**35 / 138 = 25,4 %, pas 46,7 %.** La matrice a ete corrigee **avant l'ouverture** de ce sprint,
sur decision de l'utilisatrice (`DEC-0074`, point 3) : 119 lignes issues de dix referentiels
jusque-la absents entrent au denominateur (75 -> 138), le numerateur reste a 35. **Aucune
regression du produit n'est en cause** : aucun jeu ne se casse, les 35 lignes `couvert-valide`
d'hier le restent toutes, ainsi que les 14 lignes `en cours`
(`squad/programme/MATRICE-couverture.md`, section "Deux corrections... apres les verdicts...",
methode de comptage ligne a ligne des dix tableaux neufs, verifiee par script independant).

**Fait nouveau a retenir** : le denominateur du produit est complet pour la premiere fois. Les
huit enseignements du cycle 2 et les dix du cycle 3 ont desormais tous au moins une ligne dans la
matrice.

## 3. Le perimetre et son plafond honnete

**Le prix face a la borne de `nadia-prof` est 5,0 sur une borne de 5. Zero marge.** Corrige par
son arbitrage prealable (`1cf80f4`) contre le 4,5 initial de `DEC-0074` : son critere n'est pas
*matiere neuve* contre *matiere connue*, c'est *suis-je la source de cette matiere ?* - un cout qui
se paie a l'item et ne s'eteint jamais. Histoire-geographie CM1 reste a 1,5 a son second lot
(elle n'en sera jamais la source) ; questionner le monde CP descend a 1,0 (elle en est la source,
c'etait une surfacturation a s27).

**Trois conditions sans lesquelles ce prix ne tient pas**, nommees par `nadia-prof` avant que le
contenu n'existe :

| Condition | Si elle tombe |
|---|---|
| L'anglais CM1 est textuel, jamais illustre | +0,5 -> 5,5, elle decline par ecrit |
| Le lot de questionner le monde n'ouvre pas les lignes 15 ou 16 | la fermeture des trois lignes de `S28-1` tombe |
| La dette des paquets a voix reste dehors du sprint | +0,25 -> 5,25, ne tient pas ; reportee a s29 par l'utilisatrice |

**Si un paquet est refuse, aucun rejeu n'est possible dans ce sprint** : la borne serait
depassee. L'utilisatrice l'a arbitre en le voyant (`DEC-0074`, section 8.4) - un refus se traite
au sprint suivant, pas par un second essai immediat.

**Plafond honnete de fermeture : +8 lignes, soit 43/138 = 31,2 %**, decompose lot par lot sur les
criteres d'acceptation ecrits dans `docs/backlog/backlog.md` (aucun lot ne ferme plus que ce que
son propre critere promet) :

| Lot | Lignes fermables au mieux |
|---|---|
| `S28-1` (questionner le monde CP) | +3 (etres vivants, corps et sens, etats de l'eau) |
| `S28-2` (histoire-geographie CM1, composition B) | +2 (types d'espaces, eau/energie/aliments) |
| `S28-3` (anglais CM1) | +1 (lire et comprendre des mots familiers ; la ligne "decouverte culturelle" reste `en cours`) |
| `S28-4` (calcul pose CM1) | +2 (multiplication posee, division euclidienne) |
| **Total** | **+8** |

**L'attente raisonnable de `nadia-prof`, ecrite avant le sprint, est +6 a +7** - pas le plafond de
+8. Elle nomme d'avance ou ce plafond cassera : les lots de mathematiques (`POS-2`, `POS-3`) et
d'anglais (`REC-5`, `REC-6`) appliquent pour la premiere fois des conditions qui n'ont jamais
rencontre de contenu reel. **Aucun texte de ce planning ne promet davantage que cette fourchette.**
C'est la lecon de s26 appliquee pour la quatrieme fois.

## 4. Stories retenues

| Story | Titre | Competence programme visee | Decision liee | Affectee a |
|---|---|---|---|---|
| `S28-1` | Second paquet de questionner le monde (CP) : fermer "etres vivants", "corps et cinq sens", "etats de l'eau" | Questionner le monde CP - trois lignes `en cours` depuis s27 | `DEC-0072`, `DEC-0074` | `lea-dev` |
| `S28-2` | Second paquet d'histoire-geographie (CM1), composition B : fermer "types d'espaces" et "eau, energie, aliments" | Histoire-geographie CM1 - deux des onze lignes `en cours` depuis s27 | `DEC-0072`, `DEC-0074` | `marc-dev` |
| `S28-3` | Premier paquet d'anglais (CM1) : lire et comprendre des mots familiers, decouverte culturelle en francais | Langues vivantes (anglais) CM1, matiere neuve | `DEC-0072` (report a s28), `DEC-0074` | `lea-dev` |
| `S28-4` | Un lot de calcul pose (CM1) : multiplication posee et division euclidienne | Mathematiques CM1, domaine "Nombres et calculs" | `DEC-0074`, `DEC-0064` | `marc-dev` |
| `S28-5` | Le palier 2 existe comme mecanique testable, sans paquet ni ecran servi (`ADR-0080`, taches T1, T2, T3, T5) | Transverse - aucune ligne de matrice ne bouge | `DEC-0074` (arbitrage 2), `ADR-0080` | `sofia-sm` |

**Motif de l'affectation nominative.** Continuite du lot deja livre a s27 pour chacun des deux
paquets de fermeture : `lea-dev` a ecrit le premier paquet de questionner le monde CP a s27, elle
reprend son second passage (`S28-1`) ; `marc-dev` a ecrit le premier paquet d'histoire-geographie
CM1 a s27, il reprend le sien (`S28-2`), qui porte aussi le cout d'audit le plus eleve des quatre
lots (`nadia-prof` n'est jamais la source de cette matiere). Les deux lots neufs restants sont
repartis pour equilibrer le prix : `S28-3` (anglais, 1,5) a `lea-dev`, `S28-4` (calcul pose, 1,0)
a `marc-dev` - total 2,5 de chaque cote sur les 5,0 du sprint. `S28-5`, transverse et sans
contenu pedagogique, me revient sous ma casquette dev, comme `S27-4` au sprint precedent.

## 5. Avis de faisabilite - theo-archi

| Story | Avis | ADR requis |
|---|---|---|
| `S28-1` | Mecanique et chargeur existants (bandit manchot, fichiers YAML deja livres a s27) : pas d'obstacle technique connu, aucun `ADR` requis | Non requis |
| `S28-2` | Meme famille que `S28-1`. **Depend d'un point non tranche** : la forme du marqueur `HIS-4` (que chaque item neuf doit declarer, sur le modele de `HIS-1`), a router a `theo-archi` avant le debut du developpement (voir section 6) | Non requis pour la mecanique ; le marqueur `HIS-4` est une extension mineure du schema, a confirmer par lui |
| `S28-3` | Premiere matiere neuve, textuelle pure, sur la mecanique bandit-manchot existante - meme famille que `S27-2`/`S27-3`, deja jouee sans obstacle | Non requis |
| `S28-4` | Contenu et mecanique inchanges, deux paquets de mathematiques sur le schema existant | Non requis |
| `S28-5` | **Rendu d'avance par `theo-archi` lui-meme, dans `ADR-0080`** : les taches T1, T2, T3 et T5 sont "faisables en l'etat en s28, par un developpeur, sans aucune dependance externe a la squad" - c'est l'avis de faisabilite de cette story, il n'a pas besoin d'etre redemande | `ADR-0080` (statut Propose, ses deux `ArchRule` a ecrire dans le meme geste que T1) |

## 6. Ce que je route, pas ce que je tranche

- **La ligne "situer son lieu de vie a differentes echelles"** (histoire-geographie CM1, ligne 6
  du recensement d'`ADR-0080`) sort de derriere le palier 2 : `nadia-prof` a corrige son propre
  classement du 2026-09-02 (`9c48b9c`, section 1.1) - elle l'avait rangee la par assimilation
  ("meme motif"), sans l'examiner, alors que son enonce ne nomme aucun support graphique et que
  son objet reel est une relation d'emboitement d'echelles, jouable au palier 1 des aujourd'hui.
  **Elle n'entre pas dans s28** : le sprint est plein a 5,0 sur 5, l'ajouter coute de l'audit
  qu'aucun lot ne porte. Elle est tracee pour s29. Le second obstacle qu'elle porte reste entier
  et n'est pas de la responsabilite du palier 2 : le produit ne connait pas le lieu de vie de
  l'enfant, faute de persistance (`DEC-0028`) - une question reste ouverte pour l'utilisatrice,
  posee par `nadia-prof` elle-meme (section 5 de son arbitrage) : accepte-t-elle qu'une competence
  formulee sur *son* lieu de vie soit servie sur *un* lieu de vie donne par l'item, avec sa
  mention, ou prefere-t-elle la laisser fermee jusqu'a ce que la carte existe ? Je la route a
  `camille-po` pour transmission a l'utilisatrice, hors perimetre de s28.
- **`DEC-0075`** (corpus de reproductions d'oeuvres, droits d'usage non instruits) reste au statut
  `Proposee`. Neuf lignes atteignables (2 arts plastiques CP, 2 arts plastiques CM1, 5 histoire
  des arts CM1) restent `non couvert` tant que l'utilisatrice n'a pas choisi entre les trois
  options ecrites sans recommandation par `camille-po`. **Aucune story, aucun changement de code
  dans s28** : trace comme obstacle produit ouvert, en attente d'arbitrage.
- **`T4` du palier 2** (route web, gabarit, garde-fous 1 a 3 d'`ADR-0080`) est suspendue a un
  arbitrage d'`ines-design` qui n'existe pas encore - la taille rendue de l'image du palier 2,
  dont depend le garde-fou de cible tactile. **Elle n'entre pas dans `S28-5`**, dont le perimetre
  se limite aux taches T1, T2, T3 et T5. Dit au planning plutot qu'a la review, comme
  `docs/backlog/backlog.md` le porte deja dans sa reserve dediee.
- **`T6` du palier 2** (premier paquet de designation) est hors de portee de s28, confirme par
  `DEC-0074` et `ADR-0080` : un paquet de designation exige un arbitrage prealable de
  `nadia-prof`, comme toute famille de contenu neuve, et cet arbitrage n'est pas ecrit. Aucune des
  six lignes que le palier 2 rendrait constructibles ne peut fermer avant s29 au plus tot.
- **Le marqueur `HIS-4`** (forme technique que chaque item neuf d'histoire-geographie doit
  declarer, sur le modele de `HIS-1`) est route a `theo-archi`, a trancher avant le debut du
  developpement de `S28-2` - sans lui, `FER-1` et `FER-2` ne seraient verifiables que par
  `nadia-prof`, a la main.
- **Les deux trous de garde-fou trouves par `theo-archi` en lisant le code des voisins**
  (`ADR-0080`, section 5) : `AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` ne descend pas
  dans une liste de correspondances portee par un item - les cles `id`, `libelle`, `cadre`, `x`,
  `y`, `largeur`, `hauteur` lui seraient invisibles - et le garde-fou d'`ADR-0070` ne prouve pas
  qu'une cle citee soit lue au bon endroit, ce qu'une troisieme fabrique de contenu rend probable.
  **Il ne les tranche pas lui-meme au-dela de ce qu'il a deja ecrit** : les deux remedes sont deja
  specifies dans `ADR-0080` (extension de la descente par derivation, garde-fou 6) et deja
  inscrits dans les criteres d'acceptation de `S28-5`. Je ne fais que confirmer qu'ils sont
  affectes avec la story et non laisses de cote.
- **`ZON-2`** - le garde-fou 5 d'`ADR-0080` (l'enonce ne nomme pas le libelle de sa bonne zone)
  n'attrape que le recouvrement lexical entre l'enonce et le libelle ; il ne voit ni un libelle
  qui nomme la zone autrement, ni une relation portee par d'autres mots que ceux de l'enonce
  (`9c48b9c`, section 2). `nadia-prof` l'a signale et garde `ZON-2` dans sa seule main -
  verification item par item, non automatisable par la CI. **Rien a router techniquement** : elle
  ne demande ni `ADR` ni story, elle demande d'etre relue par elle sur tout paquet de designation
  a venir, ce qui n'existera pas avant s29 au plus tot (`T6`). Je le note ici pour que la
  tracabilite ne se perde pas d'ici la.

## 7. Decisions prises

Aucune decision produit ni architecture n'est prise par moi dans ce planning : je reprends du
travail deja tranche par ses auteurs (`camille-po`, `nadia-prof`, `theo-archi`), j'affecte
nominativement les cinq stories et je route ce qui reste ouvert. Les seules "decisions" de ce
document sont d'organisation (affectations, ordre de traitement) et n'entrent pas dans la table
des `ADR`/`DEC`.

## 8. Prochaines etapes

- `theo-archi` : tranche la forme du marqueur `HIS-4` avant que `marc-dev` ne commence `S28-2`.
- `lea-dev` (`S28-1`, puis `S28-3`), `marc-dev` (`S28-2`, puis `S28-4`), `sofia-sm` (`S28-5`) :
  demarrent chacun dans un worktree dedie (`git worktree add ../recreo-s28-<agent> -b
  s28-<agent> round-two`), verifie par `outils/verifier-lignee.sh` avant le premier commit.
- `nadia-prof` : audite chaque lot des sa livraison, verifie `FER-1` a `FER-3` sur les deux lots
  de fermeture, `ANG-4`/`ANG-5`/`REC-5`/`REC-6` sur l'anglais, `POS-1` a `POS-4` sur le calcul
  pose ; prononce le Done pedagogique ligne par ligne, jamais lot par lot.
- `camille-po` : porte a l'utilisatrice la question ouverte sur la ligne des echelles (section 6)
  et fait avancer `DEC-0075` des qu'un arbitrage existe.
