# DEC-0108 - Ouverture de s50 : deux lignes neuves de francais CE1 (`FRA-CE1-02`, `FRA-CE1-16`),
# trois corrections differees, chantier de sourcing accepte avec deux echecs assumes, `SOP-CE1-R1`
# et `TAB-CE1-R1` ecartees comme doublons de reserves deja levees a s48

- **Type** : decision d'ouverture de sprint (perimetre, chiffrage, routage).
- **Date** : 2026-10-03
- **Auteur** : `camille-po`
- **Sprint** : s50
- **Competence programme visee** : transverse - ce document gele un perimetre de sprint, il ne
  tranche aucune question pedagogique de contenu.
- **Statut** : Acceptee
- **Lien** : `squad/sprints/s50-francais-ce1-sourcing-bloque-par-cloudflare/planning.md`
  (`sofia-sm`, proposition non gelee) ; `squad/rapports/sourcing-oeuvres-s49-verification.md`
  (verification reelle des 25 oeuvres) ; `squad/corpus/CHOIX-oeuvres-candidates-T5.md` (section 3.1,
  refus de `nadia-prof` de remplacer les trois oeuvres bloquees) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` (sections 3.2 et 5.7, verdicts sur
  `FRA-CE1-02` et `FRA-CE1-16`) ;
  `squad/decisions/adr/ADR-0082-une-reproduction-d-oeuvre-est-une-troisieme-population-d-images-et-sa-preuve-est-double.md`
  (section 7, dette assumee et ses alarmes) ; `DEC-0107` (precedent de tarif `FRA-CE1-01` et de traitement hors chiffrage du
  chantier de sourcing) ; `DEC-0106` (perimetre de s48, ou `SOP-CE1-R1` et `TAB-CE1-R1` ont deja ete
  engagees et fermees) ; `squad/sprints/s48-.../RAPPORT.md` et `review.md` (verdicts `VALIDE`, reserve
  levee, sur les deux) ; `docs/backlog/backlog.md` (les cinq stories de correction differee).

---

## 0. Methode

Ce document reprend la proposition de `sofia-sm` (planning de s50) point par point, en
revérifiant chaque fait au disque plutot que de le reprendre de confiance - en particulier le point
4, ou deux des cinq corrections signalees "pretes, developpeur non affecte" se revelent deja
closes. Il cite mot pour mot, date et route l'instruction recue hors des neuf agents (forme 3,
`squad/README.md` section 2 bis), conformement a sa deuxieme borne.

## 1. Le chantier de sourcing (`ADR-0082`) : le commit entre, avec deux echecs nommes et assumes

**J'accepte le commit avec les deux echecs de `mvn verify` qu'il laisse, nommes et expliques comme
dette intentionnelle.** Motif :

- **Ce n'est pas un rouge silencieux.** `ADR-0082` section 7 a arme cette alarme a l'avance, dans sa
  propre decision, pour exactement ce moment : *"le rouge de ce garde-fou lui-meme, le jour ou un
  paquet declare une reproduction : il ne resoudra pas"* - les vingt-cinq fichiers reels entrent au
  registre et au repertoire `static/oeuvres/` et aucun paquet ne declare encore un identifiant
  `oeuvre-`. L'echec est le comportement concu, pas une regression trouvee apres coup.

  **Note du 2026-10-03 (`camille-po`), correction factuelle signalee par la coordination.** Cette
  section et la section 7 point 1 ecrivaient que l'amendement d'`ADR-0073` (`T2`, `T3`) restait du et
  devenait "la priorite de developpement de s50". **C'est faux, verifie au disque** : `90c36ab0`
  (`[SOF]`, s49, deja pousse sur `round-two`) a deja fait T2 (`LIllustrationDeContenuResoutEtAucuneNeDortTest`
  resout un identifiant `oeuvre-` dans `static/oeuvres/` en plus de `static/illustrations/`, clause
  "aucune ne dort" sur les deux repertoires) et T3 (`fragments/manche.html` porte la seconde branche
  d'image, `RessourcesTiercesTest` a sa seconde exemption etroite) ; `8f6ee008` (`[THEO]`) a fait T4
  (cliquet releve sur 25 reproductions, 620 639 octets, materiau reel). **T2 a T4 sont donc clos**, pas
  a refaire. Les deux echecs de `mvn verify` (`LIllustrationDeContenuResoutEtAucuneNeDortTest`, "aucune
  ne dort") ne se ferment ni par T2 ni par T3 : ils se ferment uniquement quand un paquet reel declare
  au moins un identifiant `oeuvre-`, c'est-a-dire par `T6` - que je laisse a juste titre hors perimetre
  de s50 (section 2). **Il n'y a donc aucune tache de developpement a affecter en priorite pour fermer
  ces deux echecs a s50** : c'est une dette qui reste ouverte jusqu'a l'ouverture effective de `T6`,
  pas une tache executable ce sprint.
- **Le precedent existe deja dans ce meme chantier.** `DEC-0107` (sections 5 et 6) a deja accepte
  T2 a T5 au perimetre de s49 hors chiffrage, sur le modele du palier 2 de `DEC-0074` (chantier de
  mecanique seul, porte en developpeur affecte, jamais en unite comptee). Refuser aujourd'hui le
  commit qui livre le materiau que T5 devait produire reviendrait a accepter la tache en principe et
  a la refuser dans les faits.
- **Ce que j'exige en echange, et qui n'est pas negociable.** Le commit nomme explicitement, dans son
  message, les deux tests qui restent rouges et la raison exacte (`ADR-0082` section 7 : dette ouverte
  jusqu'a `T6`, voir la note ci-dessus) ; **aucune story ni ligne de `MATRICE-couverture.md` n'est
  close par ce commit** - il ne pretend a aucun Done, technique ou pedagogique.
- **Je ne commite pas ces fichiers moi-meme** : ils vivent sous `adapters/` et `bootstrap/`, hors de
  mon mandat. Le commit est route a `theo-archi`, proprietaire du cliquet de poids et de la regle
  ArchUnit qu'il a deja reverifiee sur l'etat a 25 oeuvres (planning, section 3), ou a un developpeur
  sous sa confirmation - affectation nominative a `sofia-sm`.

## 2. T6 (arts plastiques CP, lignes 9 et 10) : hors perimetre de s50

**Je ne l'engage pas.** `nadia-prof` a deja tranche de ne pas remplacer les trois oeuvres bloquees
par Cloudflare (`CHOIX-oeuvres-candidates-T5.md`, section 3.1 : *"le lot d'arts plastiques CP ne se
joue pas avant leur entree"*) ; je ne rouvre pas ce choix a sa place. Le blocage est confirme par
deux verifications independantes, outils differents (session principale avec acces web reel ;
`sofia-sm` via ses agents) : un defi Cloudflare (HTTP 403 a toute requete automatisee) sur le serveur
IIIF de l'Art Institute of Chicago, pour les trois fichiers `oeuvre-chambre`, `oeuvre-rue-sous-la-pluie`
et `oeuvre-grande-jatte` - tous trois deja verifies `CC0-1.0`. **Ce n'est pas un desaccord de licence,
c'est une barriere technique qu'aucun agent de la squad ne peut franchir** ; je ne le fais pas
revérifier une troisieme fois.

**Signal explicite, pour l'utilisatrice** : les trois fichiers restent a recuperer a la main, par une
personne munie d'un navigateur, depuis les pages d'oeuvre de l'Art Institute of Chicago citees dans
`squad/rapports/sourcing-oeuvres-s49-verification.md` (section 3.1), puis a inscrire au registre sur
le modele des vingt-cinq entrees existantes. Sans ce geste manuel, `ART-CP-09` reste a 5 oeuvres sur
8 et `ART-CP-10` ne peut pas fermer. Je ne route pas ce besoin ailleurs que dans ce document : il est
ecrit ici, et c'est lui qui en tient lieu de signalement.

## 3. Vivier francais CE1 : `FRA-CE1-02` et `FRA-CE1-16`, deux lignes

**Je retiens deux lignes, pas une seule et pas trois.**

**Pourquoi plus qu'une.** Le motif qui bornait a une ligne par sprint depuis s37 (`DEC-0107`,
section 3) visait un vivier vierge, pour reduire le risque qu'un defaut transverse se reproduise sur
deux paquets a la fois. `FRA-CE1-01` a depuis produit un paquet construit et audite deux fois
(`squad/conformite/FICHE-bandit-manchot-graphemes-complexes-ce1.md`) : l'appareil transverse entier
(`TRV-CE1-1` a `TRV-CE1-7`, `LON-CE1-1`, `LON-CE1-2`, `LON-CE1-5`) a ete verifie en conditions
reelles, une seule reserve non bloquante en est sortie. Le risque que le frein existait pour contenir
est donc reduit, pas disparu - je n'ouvre pas trois lignes pour autant.

**Pourquoi `FRA-CE1-02` et `FRA-CE1-16`, et pas une troisieme.** Les deux lignes partagent la forme la
plus simple du referentiel (`TRV-CE1-2` : choix binaire, precedent deja servi par `cm1-homophones`),
aucune ne porte de `texteSupport` (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, sections 3.2 et 5.7),
et leurs collisions avec le francais CM1 voisin sont deja verifiees par `nadia-prof` dans le meme
document (`FRA-CE1-16` contre `cm1-homophones`). Je m'arrete a deux plutot que de monter a trois
(plafond mecanique de `PAR-8`, section 5) parce qu'une troisieme ligne neuve ramenerait la marge de
l'instance a 0,5 - exactement le defaut de marge nulle que `DEC-0098` (section 6, repris par
`DEC-0101` section 6) nomme comme cout reel, pas hypothetique, d'avoir deja coute deux refus sur le
meme lot a s41. Deux lignes laissent une marge de 1,5, suffisante pour qu'un refus sur l'une des deux
n'empeche pas l'autre de fermer dans le meme sprint.

**Tarif : 1,0 chacune**, meme precedent que `FRA-CE1-01` (`DEC-0107`, section 3 : `nadia-prof` est la
source du referentiel de francais CE1 qu'elle a ecrit elle-meme, `DEC-0103`). Les verdicts et
conditions sont deja ecrits (`VLE-CE1-1` a `VLE-CE1-3` pour `FRA-CE1-02`, `HOM-CE1-1` a `HOM-CE1-3`
pour `FRA-CE1-16`) : aucun arbitrage prealable supplementaire n'est du.

**Les 22 autres lignes du vivier restent ouvertes, non engagees**, y compris `FRA-CE1-22` (troisieme
ligne a choix binaire) qui aurait pu etre une troisieme candidate equivalente : je ne la retiens pas,
pour la raison de marge ci-dessus, pas pour un defaut qui lui serait propre.

## 4. Corrections differees : trois entrent, deux sont ecartees comme doublons deja fermes

**J'engage `GRC-CE1-R1`, `SOU-CE1-R1`, `DIV-CE1-R1`. Je n'engage ni `SOP-CE1-R1` ni `TAB-CE1-R1`.**

**Correction factuelle du planning de s50, section 2.** Le document les cite toutes les cinq comme
"pretes a developper au meme tarif de precedent, 0,5 chacune [...] developpeur non affecte sur aucune
d'elles". **C'est faux pour deux d'entre elles**, verifie au disque et non repris de confiance :

- `SOP-CE1-R1` est entree au perimetre de s48 par `DEC-0106` (tarif 0,5, section 1) et **fermee** :
  `squad/sprints/s48-problemes-ce1-et-corrections-differees-de-s47/RAPPORT.md` (ligne 65) et
  `review.md` (ligne 110) portent toutes deux **VALIDE, reserve levee**, developpeur `lea-dev`
  (`rapport-qualite.md`, ligne 19 : commit `f99a47a9`), test de paquet passe de 13 a 16 assertions.
- `TAB-CE1-R1` est entree au meme perimetre de s48 (`DEC-0106`, section 1) et **fermee** :
  memes fichiers, memes lignes (`RAPPORT.md` ligne 60, `review.md` ligne 105), developpeur
  `lea-dev` egalement, test de paquet passe de 8 a 9 assertions.

**Ce n'est pas un premier report** : ces deux corrections n'ont jamais ete en souffrance, elles sont
closes depuis la cloture de s48. Le nom qui se ressemble ("reserve de correction differee de s47")
est exactement ce qui a produit l'ambiguite dans le document source - `sofia-sm` a verifie leur
existence en tant que stories mais pas leur statut de cloture. Je ne les engage donc pas une seconde
fois : les inclure chargerait le perimetre de s50 d'un travail deja livre et audité, et ferait
apparaitre a tort un volume de 2,5 pour les corrections quand il n'est que de 1,5.

**Les trois corrections retenues, verifiees non closes et non dupliquees** (aucune des trois
n'apparait dans les fichiers de cloture de s48 ni de s49) :

| Story | Paquet | Items touches | Tarif | Motif |
|---|---|---|---|---|
| `GRC-CE1-R1` | `bandit-manchot-graphemes-complexes-ce1` | 4 sur 24 | 0,5 | minorite, `PAR-10` |
| `SOU-CE1-R1` | `bandit-manchot-soustractions-ce1` | 4 sur 32 | 0,5 | minorite, `PAR-10` |
| `DIV-CE1-R1` | `bandit-manchot-division-ce1` | 8 sur 21 | 0,5 | minorite, `PAR-10` |

Aucune des trois ne touche la majorite des items de son paquet : `PAR-14` ne s'applique a aucune,
`PAR-10` s'applique aux trois (meme precedent que `DEC-0106`/`DEC-0107`).

## 5. Perimetre chiffre et instance

| Element | Tarif |
|---|---|
| `GRC-CE1-R1` | 0,5 |
| `SOU-CE1-R1` | 0,5 |
| `DIV-CE1-R1` | 0,5 |
| `FRA-CE1-02` | 1,0 |
| `FRA-CE1-16` | 1,0 |
| **Total** | **3,5 / 5,0** |

Marge **1,5**. Deux lots au sens de `PAR-8` (les deux lignes neuves ; les corrections ne comptent pas
comme lots, meme regle que `DEC-0107` section 4), sous le plafond de trois. Plafond individuel de
5,0 tenu avec marge.

## 6. Nombre d'instances : une seule, et `PAR-2` n'est pas la raison

**Instruction recue, citee mot pour mot** (forme 3, `squad/README.md` section 2 bis, deja
transcrite dans le depot par `sofia-sm` avant que je n'ecrive ce document) : *"jusqu'a 4 instances de
nadia-prof si besoin"*, recue le 2026-10-03, pour maximiser la croissance de couverture du programme.
C'est un **plafond disponible**, pas un objectif a atteindre : elle ne fonde par elle-meme aucune
ligne, aucun tarif, aucune ouverture d'instance (premiere borne de la forme 3).

**Je retiens une seule instance, et je ne recopie pas le motif que `sofia-sm` invoque sans l'avoir
verifie.** Le planning de s50 (section 6) ecrit que `PAR-2` (aucune matiere partagee entre deux
instances) interdirait une seconde instance, faute d'une seconde matiere disponible. **C'est inexact :
`PAR-2` ne l'interdit pas.** Les corrections de mathematiques CE1 (`SOU-CE1-R1`, `DIV-CE1-R1`) et le
vivier de francais CE1 sont deux couples matiere/niveau distincts ; rien dans `PAR-2` n'empeche de
confier le premier a une instance B et le second a une instance A. Je le verifie moi-meme plutot que
de reprendre l'affirmation.

**La raison reelle de rester a une instance est une raison de volume, pas de clause.** Une instance B
qui ne porterait que les deux corrections de mathematiques CE1 pèserait 1,0/5,0 : aucune ligne neuve
de mathematiques CE1 n'est disponible pour l'etoffer, le vivier etant instruit epuise depuis s48
(`DEC-0106`, section 4 ; planning de s50, section 4, reverifie sans changement). Ouvrir une seconde
instance pour 1,0 de volume reproduirait exactement le cout que `DEC-0077` (section 8.5) a deja nomme
et que `DEC-0107` (section 4) a deja applique dans ce meme vivier : *"deux instances sur des lots
disjoints sont deux paires d'yeux, pas un double controle"* - un cout de parallelisation reel, pas
compense par un gain de couverture ou de controle proportionne, pour un total de sprint (3,5/5,0)
qui tient deja confortablement dans le plafond individuel d'une seule instance. **Je ne vais donc pas
jusqu'a 4 ni jusqu'a 2** : le plafond disponible n'implique aucune obligation de l'atteindre, et le
volume engageable ce sprint ne le demande pas.

## 7. Ce que je decide

1. Le chantier de sourcing du corpus d'oeuvres (`ADR-0082`) entre au perimetre de s50 par le commit
   qui livre les 25 oeuvres verifiees (registre, fichiers, cliquet de poids, correction du registre
   d'identifiants), avec deux echecs de `mvn verify` nommes et assumes comme dette intentionnelle
   (section 1). Ce commit ne ferme aucune story ni aucun Done.

   **Note du 2026-10-03 (`camille-po`), correction factuelle.** Ce point ecrivait que T2/T3
   devenaient "la priorite de developpement de s50". **Faux, verifie au disque** : `90c36ab0`
   (`[SOF]`, s49) et `8f6ee008` (`[THEO]`) ont deja clos T2, T3 et T4 - voir la note de la section 1.
   Les deux echecs restants ne se ferment ni par T2 ni par T3, deja faits ; ils ne se ferment que par
   `T6` (un paquet reel declarant un identifiant `oeuvre-`), laisse hors perimetre de s50 (section 2).
   **Aucune tache de developpement n'est donc a affecter ce sprint pour les fermer** : la dette reste
   ouverte jusqu'a l'ouverture effective de `T6`, point final.
2. T6 (arts plastiques CP, lignes 9 et 10) reste hors perimetre de s50, bloque par un fournisseur,
   non par la squad (section 2). Signal explicite d'un besoin de recuperation manuelle de trois
   fichiers.
3. Deux lignes neuves de francais CE1 entrent au perimetre : `FRA-CE1-02` (1,0) et `FRA-CE1-16`
   (1,0), sur le precedent de tarif de `FRA-CE1-01` (section 3).
4. Trois corrections differees entrent au perimetre, 0,5 chacune : `GRC-CE1-R1`, `SOU-CE1-R1`,
   `DIV-CE1-R1`. `SOP-CE1-R1` et `TAB-CE1-R1` **n'entrent pas** : verifiees closes depuis s48
   (`VALIDE`, reserve levee), le planning de s50 les citait a tort comme pendantes (section 4).
5. Total de l'instance unique : **3,5 / 5,0**, marge 1,5.
6. Une seule instance de `nadia-prof` pour s50 (section 6) : l'instruction recue (*"jusqu'a 4
   instances [...] si besoin"*) est un plafond disponible, non un objectif ; `PAR-2` ne force pas une
   seconde instance (verifie, pas recopie) ; le volume engageable (3,5/5,0) et l'absence de ligne
   neuve de mathematiques CE1 rendent une seconde instance couteuse sans gain proportionne.
7. `squad/programme/MATRICE-couverture.md` n'est pas modifiee par ce document : les lignes restent
   `non couvert` jusqu'au Done pedagogique de l'instance.
8. Developpeurs a affecter nominativement par `sofia-sm` au planning : le commit du chantier de
   sourcing (T2 a T4 deja clos, voir note de la section 1) et les deux lignes de francais CE1,
   sans tache de developpement due sur T2/T3.

## 8. Ce que cette decision ne fait pas

- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` et ne ferme aucune ligne.
- Elle ne prononce aucun Done, technique ou pedagogique.
- Elle ne commite elle-meme aucun fichier du chantier de sourcing : ils vivent sous `adapters/` et
  `bootstrap/`, hors du mandat de `camille-po` ; le commit est route a `theo-archi`/un developpeur.
- Elle ne fixe aucun critere de contenu, aucune forme de distracteur, pour aucune des deux lignes
  neuves : travail entier de `nadia-prof` et des developpeurs affectes.
- Elle ne rend aucun avis de faisabilite technique sur `FRA-CE1-02`, `FRA-CE1-16` ou les trois
  corrections : travail de `theo-archi`.
- Elle ne rouvre pas le refus de `nadia-prof` sur le remplacement des trois oeuvres bloquees : il
  tient (section 2).
