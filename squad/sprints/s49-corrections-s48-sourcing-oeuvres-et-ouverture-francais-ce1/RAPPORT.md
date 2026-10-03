# Rapport de sprint s49 - trois corrections differees de s48, `FRA-CE1-01` (premiere ligne construite
du vivier francais CE1), chantier de sourcing du corpus d'oeuvres du domaine public inscrit

- **Duree** : 2026-10-03 14:59:00 -> 2026-10-03 19:20:25 (UTC+02:00), 4 h 21 min.
- **Objectif gele** (`DEC-0107`, Acceptee, amendee le jour meme) : instance unique de `nadia-prof` -
  trois corrections differees de s48 (`PRO-CE1-R1`/`PRO-CE1-R2`, `ANG-CP-R3` + l'item non leve
  d'`ANG-CP-R1`/`ANG-CP-R2`, la reserve sans identifiant d'`EMC-CP-14`), tarif 0,5 chacune ;
  `FRA-CE1-01`, tarif 1,0 - total **2,5/5,0**, marge 2,5. Hors chiffrage : le chantier de sourcing du
  corpus d'oeuvres du domaine public (T2 a T5, entre au perimetre sans tarif en lots).

## L'increment livre, en une phrase

`FRA-CE1-01` (lire des mots a graphemes complexes et les associer a leur son) passe de `non couvert`
a `couvert-valide` - premier paquet de contenu de francais CE1 construit, refuse au premier passage de
`nadia-prof` pour deux motifs d'exactitude pedagogique puis valide sous reserve au second passage le
meme jour, apres correction reprenant au mot pres la forme que `nadia-prof` avait ecrite - pendant que
trois corrections differees de s48 levent leurs reserves sans bouger de statut et que le chantier de
sourcing du corpus d'oeuvres du domaine public avance d'un mecanisme technique sans encore produire
aucune preuve verifiee. La couverture passe de **119/182 (65,4 %)** a **120/182 (65,9 %)**.

## Les decisions du sprint

| Decision | Numero | Statut | Objet |
|---|---|---|---|
| `DEC-0107` | Produit | Acceptee, amendee le 2026-10-03 (meme jour) | Ouverture de s49 : perimetre et tarif des trois corrections differees et de `FRA-CE1-01` (2,5/5,0) ; chantier de sourcing du corpus d'oeuvres inscrit hors chiffrage. Amendement (section 6) : `ADR-0082` existait deja (correction de l'erreur de `theo-archi`), T2 a T5 entrent au perimetre, hors chiffrage en lots |
| `DEC-0105` (ecrite a s48) | Produit | Acceptee, correction factuelle le 2026-10-03 (section 2 bis) | Correction de son affirmation erronee : `ADR-0082` existait depuis le 2026-09-05, contrairement a ce que la section 2 affirmait sur la foi de l'avis de `theo-archi` |

**Aucun `ADR` n'est cree a s49.** `ADR-0082`, deja Accepte depuis le 2026-09-05, n'est ni reecrit ni
amende : l'erreur corrigee ce sprint porte sur deux `DEC` qui affirmaient a tort son absence, pas sur
son contenu. Detail complet, y compris la chronologie de l'erreur : `review.md`, section 3.

**Si ce sprint n'avait produit aucune decision, cette section le dirait explicitement** ; ce n'est pas
le cas : une `DEC` d'ouverture et un amendement factuel sur une `DEC` anterieure sont ecrits.

## Etat des deux Done

### Technique - rapport de `yanis-qa`, les quatre jobs de la CI reproduits un par un, mecanisme casse puis remis

`bash outils/mvn-jdk21.sh -B clean verify` : **BUILD SUCCESS**, 6 modules sur 6, lance deux fois, ~9 min
chaque fois. **1975 tests (1969 unitaires + 6 integration), 0 echec** - +21 depuis s48, ecart explique
sans reste par six fichiers. **Zero ligne de `src/main/java` touchee par les quatre stories de
contenu** ; le mecanisme T2-T4 touche `adapter-web` et ses propres tests, sans toucher de regle
ArchUnit neuve. Les quatre jobs de la CI (`build`, `prefixes`, `chemins`, `outils`) sont **verts,
chacun reproduit localement**, aucun deduit de `mvn verify` seul. `yanis-qa` **casse** volontairement
le mecanisme T2-T4 (fuite simulee d'un fichier de test dans un repertoire reellement servi) pour
verifier que les garde-fous mordent reellement : **rouge sur trois angles a la fois, cinq echecs**,
puis retire le fichier et reconfirme le vert. Huit commits, huit prefixes d'agent conformes.
**Conclusion de `yanis-qa` : "La porte technique de s49 est verte."** Detail complet : `review.md`,
section 2.

### Pedagogique - quatre Done pedagogiques prononces, un refus intermediaire leve le jour meme

| Story | Verdict final | Episode notable |
|---|---|---|
| `FRA-CE1-01` | VALIDE SOUS RESERVE (`GRC-CE1-R1`) | **REFUSE au premier passage** (deux motifs d'exactitude), leve au second passage le meme jour par une correction reprenant la forme exacte que `nadia-prof` avait ecrite |
| `PRO-CE1-R1`/`PRO-CE1-R2` (`MAT-CE1-08`) | VALIDE, sans reserve | les deux reserves de s48 levees, ligne deja `couvert-valide` depuis s48, aucun statut ne bouge |
| `ANG-CP-R3` + item non leve (`LVE-CP-06`) | VALIDE, sans reserve | les trois reserves ouvertes depuis s47/s48 levees, ligne deja `couvert-valide`, aucun statut ne bouge |
| Reserve sans identifiant (`EMC-CP-14`) | VALIDE, sans reserve | reserve de l'indice de l'item 7 levee, ligne deja `couvert-valide`, aucun statut ne bouge |

**Zero refus final sur les quatre stories.** Le seul refus du sprint (`FRA-CE1-01`, premier passage)
est leve dans le meme mouvement, le meme jour, par un second passage. Detail complet, y compris les
deux motifs du refus et leur levee mot pour mot : `review.md`, section 4.

## Le chantier de sourcing du corpus d'oeuvres du domaine public - aucune ligne fermee

T2, T3 et T4 (branchement technique du mecanisme de reproduction d'oeuvre) sont livres sur fixtures
synthetiques, sans aucune oeuvre reelle - **aucun acces web disponible a la squad**. T5 (choix des
oeuvres) est engage en partie : `nadia-prof` propose 28 oeuvres candidates pour les neuf lignes
bloquees, **aucune verifiee**, explicitement qualifiees de proposition a verifier. T6 a T8 (les trois
paquets de contenu) ne sont pas engages : `squad/corpus/oeuvres-domaine-public.yaml` declare toujours
`oeuvres: []`, verifie par `yanis-qa`. **Les neuf lignes de la matiere (arts plastiques CP/CM1,
histoire des arts CM1) restent `non couvert`**, inchangees par ce sprint. Detail : `review.md`,
section 5.

## Couverture du programme

**120/182 (65,9 %)**, verifiee sur `squad/programme/MATRICE-couverture.md` (note de cloture de
`camille-po`, `a272c703`). Une seule ligne fermee : `FRA-CE1-01`. 119/182 (65,4 %) a l'ouverture de s49.

## Obstacles resolus et encore ouverts

**Resolus le jour meme** :

- L'erreur de `theo-archi` (son avis de s48 affirmait a tort qu'aucun `ADR` ne couvrait le chantier de
  sourcing) - trouvee et corrigee par lui-meme dans son avis de s49, propagee dans `DEC-0105` et
  `DEC-0107`, corrigees par `camille-po` dans le meme commit.
- Le refus de `FRA-CE1-01` au premier passage - leve au second passage, meme jour.
- La reference perimee de `DEC-0106` section 4 sur la borne de longueur du texte support - corrigee
  dans `DEC-0107`.

**Encore ouverts** :

- La reserve neuve `GRC-CE1-R1` (le distracteur *colline* revient sur les quatre items `ill`) : story
  deja ecrite au backlog par `camille-po`, developpeur a affecter.
- Le chantier de sourcing du corpus d'oeuvres du domaine public ne produit a ce jour aucune preuve
  verifiee, faute d'acces web de la squad - **limite structurelle**, pas un defaut de process a
  corriger, nommee pour les sprints futurs (`retro.md`).
- T6 a T8 du chantier de sourcing restent non engages, neuf lignes toujours `non couvert`.

Tableau complet, avec routage par categorie : `review.md`, section 7.

## Prochain perimetre

**Non propose par cette cloture** : ce n'est pas le role de cette cloture, il appartient a
`camille-po`. Candidates disponibles, non engagees : `FRA-CE1-02` (vivier francais CE1 ouvert, non
engagee par choix explicite de `DEC-0107`), la story de `GRC-CE1-R1`, et T6 des que T5 aura produit
une premiere oeuvre verifiee.

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s49`, fenetre 2026-10-03 12:59 -> 2026-10-03 17:20 UTC
(horodatage de debut du planning jusqu'au dernier commit du sprint). Detail complet dans
`RAPPORT-tokens.md` (ce dossier) et cumule dans `squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 202 | 404 | 95 010 | 945 743 | 20 087 775 |
| `sofia-sm` | claude-sonnet-5 | 96 | 192 | 64 251 | 1 096 157 | 14 751 812 |
| `theo-archi` | claude-opus-5-5 | 132 | 264 | 57 507 | 433 049 | 18 556 218 |
| `marc-dev` | claude-sonnet-5 | 317 | 634 | 169 523 | 2 180 756 | 62 033 656 |
| `lea-dev` | claude-sonnet-5 | 144 | 288 | 46 837 | 755 358 | 16 783 712 |
| `yanis-qa` | claude-sonnet-5 | 77 | 154 | 19 947 | 279 424 | 4 228 224 |
| `relecteur-pedagogique` | claude-sonnet-5 | 47 | 94 | 6 288 | 225 144 | 2 661 391 |
| `nadia-prof` | claude-opus-5-5 | 201 | 402 | 104 943 | 757 152 | 16 824 202 |
| orchestrateur (fil principal) | claude-sonnet-5 | 69 | 138 | 75 919 | 108 247 | 18 795 296 |
| **Total** | | **1285** | **2 570** | **640 225** | **6 781 030** | **174 722 286** |

Aucune invocation mesuree sur cette fenetre pour `ines-design` : aucun travail de design n'etait du sur
ce sprint (perimetre entierement textuel et de mecanisme technique). Les quatre compteurs sont
volontairement separes (voir `RAPPORT-tokens.md`) : les additionner donnerait un classement faux, le
cache lu depassant couramment l'output de deux ordres de grandeur.
