# [Sprint s26] Planning - 2026-09-01

- **Horodatage de debut** : 2026-09-01 14:32
- **Anime par** : sofia-sm
- **Participants** : camille-po, theo-archi (en cours d'instruction), nadia-prof, ines-design,
  marc-dev, lea-dev, sofia-sm

## Objectif du sprint

Perimetre arbitre par l'utilisatrice, mot pour mot, 2026-09-01 : **"demarre s26 avec en plus la
dette"** - les quatre lignes a voix arbitrees le 2026-09-01 (`DEC-0068` et son amendement) **plus**
la dette technique nommee au rang 3 a 5 de `DEC-0067` (le trou d'`ADR-0008`, le garde-fou "un rang
suffit" avec sa clause de non-vacuite, et les trois identifiants que rien n'execute). Gele a
l'ouverture ; ne change plus pendant le sprint.

`DV-4` (rang 2 de `DEC-0067`) est portee par `camille-po` elle-meme, sans cout de developpeur : elle
prepare `LIS` pour s27, elle n'ouvre pas `LIS` a s26. `S26-NAR-1` (rang 6) reste au backlog, non
retenue : `camille-po` l'a ecrite non urgente de ses propres mots.

**Etat d'entree, verifie par moi et non recopie** : `round-two` a `cf57328`, vert -
`bash outils/mvn-jdk21.sh -B clean verify` sur le reacteur complet (pas de `-pl`), **1018 tests**
(80 domain + 13 application + 364 adapter-web + 410 adapter-content + 145 bootstrap unitaires + 6
bootstrap integration, somme des blocs `Results:` apres `clean`), 0 echec, 0 erreur, BUILD SUCCESS.
35 jeux au catalogue, 3 mecaniques, 24 illustrations, 31 regles ArchUnit (chiffres repris de
l'etat d'entree communique, non remesures par moi ce planning - seul le compte de tests et le vert
CI sont de ma propre execution).

## Stories retenues

| Story | Titre | Competence programme visee | Decision liee | Affectee a |
|---|---|---|---|---|
| `S26-VOI-1` | Paquet "localiser une syllabe" (voix d'un adulte present) | Francais CP - Langage oral et conscience phonologique | `DEC-0068` ; `ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md` | `marc-dev` |
| `S26-VOI-2` | Paquet "identifier un son" (voix d'un adulte present) | Francais CP - idem | `DEC-0068` ; idem | `marc-dev` |
| `S26-VOI-3` | Paquet "graphemes, clause 1" - voyelles et consonnes simples | Francais CP - idem | `DEC-0068` ; idem, section 11 | `lea-dev` |
| `S26-VOI-4` | Paquet "graphemes, clause 2" - `ou`, `oi`, `ch` | Francais CP - idem | `DEC-0068` ; idem, section 11 | `lea-dev` |
| `S26-VOI-5` | Paquet "ecrire un mot en autonomie" (saisie alphabetique + voix d'un adulte) | Francais CP - idem | `DEC-0068` ; idem | `sofia-sm` (casquette dev) |
| `S26-DETTE-1` | Fermer le trou d'`ADR-0008` : un element `<script>` neuf porte du vocabulaire interdit sans faire rougir un test | transverse | `ADR-0008` ; `DEC-0067` rang 3 | `theo-archi` |
| `S26-DETTE-2` | Garde-fou "un rang suffit" + clause de non-vacuite par paquet (domaine derive vide sur `cm1-comprehension`) | transverse | `DEC-0067` rang 4 ; `ADR` a ouvrir | `theo-archi`, puis dev a affecter une fois l'`ADR` ecrit |
| `S26-DETTE-3` | `SUB-8` - encoder la moitie calculable manquante (precedent `PaquetCpTexteNarratifTest`, meme module) | transverse | `DEC-0067` rang 5 | `marc-dev` |
| `S26-DETTE-4` | `DES-6` - faire mordre le garde-fou sur la chaine de la monnaie, servie depuis s25 | transverse | `DEC-0067` rang 5 | `ines-design` (propriete), `theo-archi` (perimetre), dev a affecter |

**Non retenus, notes pour memoire** : `MON-6` (rang 5) - population vide aujourd'hui, non actionnable
avant le prochain paquet de composition. `S26-NAR-1` (rang 6) - backlog, non urgent.

## Avis de faisabilite - theo-archi

| Story | Avis | ADR requis |
|---|---|---|
| `S26-VOI-1` a `S26-VOI-5` | **En cours d'instruction a l'heure ou ce planning est ecrit.** `theo-archi` repond a la question que `camille-po` lui route (`DEC-0067` B.5/B.7) : la famille a voix est-elle un champ d'item (le precedent d'`ADR-0073`) ou une mecanique neuve ? Voir la borne conditionnelle ci-dessous, ecrite d'avance par `nadia-prof` pour les deux reponses possibles. Ce planning ne fabrique pas sa reponse et ne bloque pas dessus. | a determiner par sa reponse |
| `S26-DETTE-1` | A instruire par `theo-archi` (rang 3 de `DEC-0067`, routee par `yanis-qa` a s25) | probable |
| `S26-DETTE-2` | A instruire par `theo-archi` (rang 4 de `DEC-0067`) | requis |
| `S26-DETTE-3` | Faisable en l'etat, precedent deja ecrit dans le meme module | non |
| `S26-DETTE-4` | Perimetre a instruire par `theo-archi` ; propriete a `ines-design` et `nadia-prof` | a determiner |

### La borne conditionnelle de `nadia-prof`, portee ici explicitement plutot que d'attendre

Ecrite d'avance par `nadia-prof` (`ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md`, section 12.4),
**avant** de connaitre la reponse de `theo-archi` :

| Conclusion de `theo-archi` | Sa borne | Ce qui s'ajoute | Lignes que s26 peut fermer | Plafond de couverture |
|---|---|---|---|---|
| **Champ d'item** (cas d'`ADR-0073`) | 5 paquets, inchangee | rien : l'ecran de l'adulte est deja juge par `VOI-12` | **4 sur 4** | **35 / 41 = 85,4 %** |
| **Mecanique** | 4 paquets | un audit de mecanique neuve, plus le chemin non audite de l'ecran de revelation (un adulte qui le lit dit la reponse a voix haute) | **3 sur 4** | **34 / 41 = 82,9 %** |

Si sa conclusion est "mecanique", une des quatre lignes a voix ne tient pas dans s26 - le choix de
laquelle reste a `camille-po`, jamais a moi. Ce planning n'ecrit donc pas de plafond unique : les
deux chiffres ci-dessus sont les deux etats possibles, pas une fourchette a moyenner.

**Sa borne, confirmee sans marge, quelle que soit sa reponse** (`ARBITRAGE-PREALABLE-s26...`,
section 12.2) : **5 paquets, 20 items par paquet, 115 items plafond, au moins 5 ecrans d'adulte a
juger.** Un sixieme paquet, elle decline par ecrit (`C3`). La ligne des graphemes exige ses deux
moities pour compter : livrer une seule moitie depense un paquet et ne ferme aucune ligne
(section 11.6).

`VOI-2` est insatisfaisable telle qu'elle avait ete ecrite (elle interdit d'ecrire le mot pendant
que le texte de l'adulte l'exige) ; `VOI-13` la remplace (section 14.3) ; elle nomme explicitement
`VOI-13` comme une quatrieme condition sans garde, de la meme famille que `VOI-3`, `VOI-5` et
`VOI-8` - rien dans le produit ne verifie qu'un enfant de CP ne lit pas le texte de l'adulte par
dessus son epaule, et le seul levier est de le dire a l'adulte.

## Obstacles ouverts a la date de ce planning

| Obstacle | Categorie | Route a | Statut |
|---|---|---|---|
| `round-two` etait rouge : `CLAUDE.md`, ligne 20, citait `DEC-0068` sans que le fichier n'ait jamais ete fusionne sur `round-two` - `ToutesLesReferencesDeDecisionResolventTest` echouait depuis `153df5a` | technique, gouvernance du depot | resolu directement (voir ci-dessous) | **clos** |
| Aucun garde-fou d'`adapter-content` ne mesure `P(bonne / presente)` - la mesure qui fonde le verdict de fuite de reponse de `nadia-prof` sur `cp-le-bon-son` (24 items, `ch` a 100 %) n'existe nulle part dans le depot, verifie test par test par elle | qualite, test, CI - avec un volet technique | `yanis-qa` **et** `theo-archi`, sur le meme perimetre (`adapter-content`, garde-fous de distribution de contenu) - nomme explicitement pour que les deux sachent que l'autre ecrit dessus | ouvert |
| Quatre doutes d'`ines-design` sur sa proposition d'ecran `VOI-12`, dont un qui pourrait defaire le motif pedagogique de `nadia-prof` pour la ligne "identifier un son" (sa consigne d'exemple ecrit le grapheme cible, `« ch »`, la ou l'arbitrage de `nadia-prof` loue cette ligne pour detacher le son de la lettre) | pedagogie, formulation d'un contenu | `nadia-prof` | ouvert |

### L'obstacle du rouge, ferme, et l'attribution qui va avec

**L'auteur du defaut est l'orchestrateur.** `CLAUDE.md`, section "L'enfant de CP n'est jamais
seul", a ete ecrit sur `round-two` en citant `DEC-0068` alors que le fichier de decision restait sur
la branche d'instruction `s26-camille` (`386492b`), et le tout a ete pousse sans lancer `mvn
verify`. C'est ecrit ici tel quel parce que l'attribution est une regle de ce depot, et elle vaut
aussi quand l'auteur est l'orchestrateur.

**Resolution, sous ma casquette dev** : fusion de `s26-camille` (`386492b`, qui portait deja `s26-
nadia` a `451ff2d`) dans `round-two`, `mvn -B clean verify` vert sur le reacteur complet avant tout
push (`7934d37`). `s26-ines` (`489e65e`, restee locale tant que l'arbre etait rouge) et le second
constat de `s26-nadia` (`ea06dbe`, reste hors arbre apres la premiere fusion) sont entres dans le
meme mouvement, chacun revert par `mvn -B clean verify` avant son push (`5d35062`, `cf57328`).

**Le vrai enseignement, et il merite d'etre garde au-dela de cet incident** : le defaut n'a ete
decouvert que parce qu'`ines-design` a refuse de pousser son travail sur un `round-two` rouge et
est remontee, au lieu de pousser quand meme ou de contourner. Le garde-fou a mordu ; c'est la
discipline d'un agent qui l'a rendu audible. Cet obstacle sera repris dans le tableau de `review.md`
a la cloture, avec son statut `clos`, conformement a la regle de `squad/README.md` section 4 qui
veut que le tableau des obstacles ne se perde pas faute de daily journalise.

## Decisions prises

Aucune decision `ADR` ou `DEC` prise pendant ce planning. Les fusions decrites ci-dessus font
entrer sur `round-two` des decisions et des constats deja rediges par leurs auteurs sur leurs
propres branches (`DEC-0068` et son amendement, l'arbitrage prealable de `nadia-prof` et son
addendum) ; elles ne creent aucune decision nouvelle.

## Prochaines etapes

- `marc-dev` et `lea-dev` demarrent respectivement `S26-VOI-1`/`S26-VOI-2` et `S26-VOI-3`/
  `S26-VOI-4` des que `theo-archi` a rendu sa reponse sur le calibrage (champ d'item ou mecanique) :
  elle decide si les quatre lignes tiennent entierement dans ce sprint ou si l'une d'elles bascule
  hors borne, question que je ne tranche pas a leur place.
- `sofia-sm`, casquette dev, prend `S26-VOI-5` (ecriture autonome d'un mot, saisie deja ouverte par
  `SAI-11`).
- `theo-archi` rend son avis de faisabilite sur `S26-VOI-1` a `S26-VOI-5` et instruit `S26-DETTE-1`
  et `S26-DETTE-2` (`ADR` a ouvrir pour chacune).
- `marc-dev` prend `S26-DETTE-3` (`SUB-8`) en parallele des paquets a voix : cout d'un test, precedent
  deja ecrit.
- `nadia-prof` repond aux quatre doutes d'`ines-design`, en particulier le doute 4 sur la consigne
  de la ligne "identifier un son".
- `yanis-qa` et `theo-archi` instruisent l'obstacle `P(bonne / presente)` : au minimum, dire si un
  garde-fou de ce type peut s'ecrire sur le patron des garde-fous de distribution deja existants
  (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`) ou si la forme est neuve.
- `sofia-sm` route la reponse de `theo-archi` sur le calibrage des` S26-VOI-*` des qu'elle arrive et
  ajuste l'affectation en consequence si une ligne bascule hors borne.

---

## Addendum du 2026-09-01 (sofia-sm) - la reponse de theo-archi ferme la borne haute, l'avis de faisabilite se cloture

- **Auteure de l'addendum** : `sofia-sm`.
- **Ce qu'il ne fait pas** : rien du texte ci-dessus n'est modifie ni retire. Cet addendum applique,
  pour la premiere fois a un `planning.md`, la discipline de l'amendement s22 (`squad/README.md`
  section 2) : l'avis de faisabilite de `theo-archi` etait le seul contenu obligatoire de ce
  planning (README section 4) reste ouvert a l'ecriture du fichier ci-dessus, marque "en cours
  d'instruction" ; il est rendu, et cette section le cloture sans toucher un mot de ce qui precede.

### Ce que `theo-archi` a rendu (`s26-theo`, `d1afa5e`, fusionne dans `round-two` a `1d9a376`)

- **La famille a voix est un champ d'item, pas une mecanique - `ADR-0075`.** La question posee par
  `camille-po` (`DEC-0067`, B.5/B.7) est tranchee. **La borne haute de `nadia-prof` se realise : 5
  paquets, 4 lignes sur 4, 35 / 41 = 85,4 %.** Sa branche mecanique (4 paquets, 3 lignes, 82,9 %) ne
  se realise pas. Mesure par sonde sur quatre paquets reels, pas deduite.
- **Ce n'est pas pour autant le cas d'`ADR-0073` a l'identique** : `ADR-0032` (l'identite d'un tour)
  rougit sur trois paquets sur quatre, six collisions - `VOI-13` chasse de l'ecran de l'enfant la
  matiere qui distinguait deux tours (l'illustration, vue ; le texte de l'adulte, entendu, jamais vu
  par l'enfant). **`ADR-0075` amende `ADR-0032` sur son critere**, jamais sur sa propriete : ce
  n'est pas le critere qui a derive, c'est le monde qu'il doit couvrir qui s'est elargi.
- **`S26-DETTE-1` est fermee - `ADR-0074`.** La dette du rang 3 de `DEC-0067`, a son troisieme
  sprint de routage, portee de la dette n. 4 de `CLAUDE.md`. Mordue sur l'arbre de production et
  non sur une fixture : `M7` (l'element `<script>` de dix lignes) laissait le reacteur a 1 012
  tests et 1 echec avant le remede, `9/0` apres, mutant retire et verifie. Le remede ferme le
  porteur (tout JavaScript servi vit dans un fichier, jamais dans un gabarit) et non le
  vocabulaire interdit, dont le perimetre ne portait pas jusqu'au gabarit.
- **L'obstacle `P(bonne / presente)`, ouvert dans le tableau ci-dessus, change de statut sans que
  la ligne d'origine soit reecrite** : `theo-archi` l'a chiffre lui-meme et le coupe du perimetre
  de s26. Sa forme naive rougit 8 paquets deja valides sur 33, population utile vide la ou elle
  compte - **quatrieme instance du meme defaut** (la famille du critere enumere / releve automatique
  sans clause de non-vacuite, deja nommee au rang 4 de `DEC-0067`). **C'est un routage a
  journaliser pour un prochain sprint, pas un abandon** : le seuil qui resterait a poser est un
  jugement pedagogique, pas le sien.
- **Verdict de `theo-archi`, cite mot pour mot** : *"le perimetre complet tient, et il ne tient que
  parce que la dette en est sortie - payee au planning plutot que reportee une quatrieme fois."*
- **Contrainte a porter aux stories de graphemes (`S26-VOI-3`, `S26-VOI-4`)** :
  `cp-le-bon-son.yaml` ne doit pas etre touche par ce lot - un geste de forme y coute 15 items sur
  les 115 de la borne de `nadia-prof`, pour rien, en reactivant sa clause 3 (rejeu des nasales).
- **Sa mesure, distincte de la mienne et nommee comme telle** : `theo-archi` rapporte 1 022 tests,
  0 echec, JDK 21.0.10 rapporte par la machine virtuelle des tests, sur son arbre fusionne avec
  `round-two` a `7934d37`. **Ma propre mesure**, faite apres avoir fusionne `s26-theo` en plus de
  `s26-camille`, `s26-ines` et `s26-nadia` deja dans `round-two`, sur le reacteur complet
  (`bash outils/mvn-jdk21.sh -B clean verify`, sans `-pl`) : **1 022 tests** (80 domain + 13
  application + 368 adapter-web + 410 adapter-content + 145 bootstrap unitaires + 6 bootstrap
  integration), 0 echec, 0 erreur, BUILD SUCCESS. Les deux chiffres coincident ; ce n'est pas le
  meme arbre (le mien porte trois branches de plus), et je ne recopie pas le sien comme preuve du
  mien.

### La borne, non conditionnelle

La table "Avis de faisabilite" ci-dessus et sa borne conditionnelle sont desormais tranchees sur
la premiere ligne de leurs deux scenarios : **champ d'item, 5 paquets, 4 lignes sur 4, plafond
35 / 41 = 85,4 %.** Le scenario "mecanique" ne s'est pas realise et reste lisible ci-dessus pour
memoire, jamais comme un etat actuel.

### Un fait signale par `theo-archi`, pour la retro et non pour ce planning

**`ADR-0066` a mordu deux fois ce sprint, le meme jour, sur deux auteurs differents** : sur
`CLAUDE.md` citant `DEC-0068` avant sa fusion (defaut de l'orchestrateur, voir l'obstacle clos
ci-dessus), et sur `theo-archi` lui-meme, citant `ADR-0074` avant d'ecrire le fichier qui le porte.
Je ne tranche rien ici : je le garde pour qu'il n'atterrisse pas seulement dans une memoire
d'agent, et je le porterai a `retro.md` a la cloture.

### L'implementation, lancee

`marc-dev` (`s26-marc`) prend le champ `texteAVoixHaute` sur les deux formes d'item, l'amendement
d'identite d'`ADR-0075`, et la clause 4 de traversee. `lea-dev` (`s26-lea`) cable l'ecran
d'`ines-design`, sous les cinq points de `VOI-12` et la contrainte d'`ADR-0074`. `camille-po`
(`s26-camille`) ecrit les cinq stories de contenu. Ce planning n'affecte donc plus les
`S26-VOI-1` a `S26-VOI-5` a `marc-dev`/`lea-dev`/`sofia-sm` comme table ci-dessus le disait a
l'ouverture : l'affectation reelle, au moment de cet addendum, est celle de ce paragraphe.
