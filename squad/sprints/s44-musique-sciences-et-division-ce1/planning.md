# Planning - s44 - education musicale CP, sciences et technologie CM1, mathematiques CE1 (division)

- **Horodatage de debut** : 2026-09-30 18:48 (UTC+02:00).
- **Casquette** : `sofia-sm`, Scrum Master.
- **Slug** : `s44-musique-sciences-et-division-ce1`, dossier ouvert par ce planning - aucun des deux
  avis de faisabilite ne l'avait ouvert (ils le disent tous deux explicitement : "aucun dossier
  `squad/sprints/s44-...` n'existe, et l'ouvrir n'est pas mon role").

## 1. Lignee du worktree

Verifie avant toute ecriture (`git rev-parse --abbrev-ref HEAD` puis
`bash outils/verifier-lignee.sh round-two`) : `s44-sofia` (`../recreo-s44-sofia`) descend de
`round-two` a `ca277753` (dernier commit sur `round-two` au moment de l'ouverture, fusion de l'avis
de faisabilite musique/sciences), sans aucun autre ecart.

## 2. Objectif gele, et sa provenance

**Objectif** (`DEC-0099`, Acceptee, section 9.6, engagement section 10, 2026-09-25) : fermer les
trois lots du perimetre de s44 - `MUS-CP-11`, `SCI-CM1-13`, `MAT-CE1-18` - cible 4,0/5,0, marge 1,0,
**instance unique** de `nadia-prof`. Si les trois lignes ferment integralement, la matrice de
couverture passe de 101/156 (64,7 %, etat de sortie de s43) a 104/156 (66,7 %).

**Ce que ce planning ne rouvre pas** : `DEC-0099` elle-meme, les tarifs de ses trois lignes. Les
trois lots sont deja verifies `non couvert` dans `MATRICE-couverture.md` (verification repetee par
`theo-archi` a la section 10.1 de `DEC-0099` et dans ses deux avis) et resolus ligne par ligne,
citation comprise, par ses deux avis de faisabilite.

## 3. Stories deja instruites, avis de faisabilite deja rendus

Les trois stories sont ecrites avec criteres d'acceptation dans `docs/backlog/backlog.md`, section
"Sprint 44". Deux avis de faisabilite de `theo-archi` les couvrent :

| Lot | Ligne | Tarif | Verdict `theo-archi` | Source |
|---|---|---|---|---|
| `S44-MAT-DIVISION` | `MAT-CE1-18` | 1,0 | **Faisable en l'etat**. `DIV-CE1-1` a `DIV-CE1-6` toutes encodables dans le test du paquet (section 4 de l'avis). Prealable bloquant (frontiere `MAT-CE1-18`/`MAT-CE1-06`) deja leve par `nadia-prof` (`DIV-CE1-6`) et fusionne avant cet avis | `squad/rapports/avis-faisabilite-s44-mat-division.md` (commit `1fa47c8b`) |
| `S44-MUS-VOCABULAIRE` | `MUS-CP-11` | 1,5 | **Faisable en l'etat**, basse complexite, aucun `ADR` ni `ArchRule` | `squad/rapports/avis-faisabilite-s44-mus-sci.md` (commit `71ce3063`, fusion `ca277753`) |
| `S44-SCI-OBJET-TECHNIQUE` | `SCI-CM1-13` | 1,5 | **Faisable sous condition**. Zero ligne de code de production. La condition (section 5.1 de l'avis) est une correction de texte de la story par `camille-po`, pas un geste technique : elle cite une `SCI-1` remplacee deux fois depuis sa redaction et pointe le mauvais champ de retour (`explication` au lieu de l'`indice`) | meme fichier |

Porte technique : aucune mesure `mvn verify` n'est due a ce planning, aucun code de production n'a
encore ete touche par les trois lots.

## 4. Route Q1 et Q3 vers `nadia-prof` - non bloquantes pour ce planning, dues avant l'ecriture des paquets concernes

Les deux questions suivantes viennent de `squad/rapports/avis-faisabilite-s44-mus-sci.md`, section
10. Je ne les tranche pas : `theo-archi` les a explicitement routees "via `sofia-sm`" a
`nadia-prof`, et je transmets telles quelles.

| Code | Question | Paquet concerne | Bloquant pour ce planning ? |
|---|---|---|---|
| Q1 | `FER-1` exige `pulsation` et `silence` en bonne reponse sur `MUS-CP-11`. Quelle forme d'enonce les sert sans supposer une ecoute (`MUS-1`) ni un geste frappe (`MUS-2`) ? | `S44-MUS-VOCABULAIRE` | Non - mais due avant que le paquet de musique ne s'ecrive |
| Q3 | Deux adjacences de materiau, entre `SCI-CM1-13` (evolution d'un objet) et les lignes deja servies `SCI-CM1-12` (fonction d'un objet) et `SCI-CM1-11` (familles de materiaux, section 6 de l'avis) : quel discriminant poser ? | `S44-SCI-OBJET-TECHNIQUE` | Non - mais due avant que le paquet de sciences ne s'ecrive |

Categorie du routage (`squad/README.md` section 1, table de routage des obstacles) : pedagogie,
niveau, formulation d'un contenu -> `nadia-prof`. Artefact attendu : avis ecrit, a verser dans la
fiche de conformite du jeu concerne une fois le paquet ouvert.

## 5. Route Q2 vers `camille-po` - correction bloquante de `S44-SCI-OBJET-TECHNIQUE`

**Fait transmis, non tranche ici** (avis de faisabilite, section 5.1) : `docs/backlog/backlog.md`,
story `S44-SCI-OBJET-TECHNIQUE` (lignes 15850-15851 et 15866-15867 au moment de ce planning) cite
encore *"Apres une reponse fausse, le retour dit pourquoi elle est fausse, en une phrase"* et en tire
un critere d'acceptation sur "le retour". C'est la formulation de `SCI-1` du 2026-09-03, remplacee
deux fois depuis - la version en vigueur est celle de
`squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`,
section 1.1 (lignes 83-93) : le porteur de l'explication apres une erreur est
l'**indice** (`BanditManchotSession.indice()`), affiche des la premiere erreur, jamais
l'**explication** de l'ecran de retour, que `nadia-prof` a mesuree invisible pour la quasi-totalite
des enfants qui se trompent. La condition ne porte en outre que sur les items a conception fausse,
jamais sur "ta reponse".

**Correction demandee a `camille-po`, dans `docs/backlog/backlog.md`, section "Sprint 44", story
`S44-SCI-OBJET-TECHNIQUE`** :

1. remplacer la citation de `SCI-1` et le critere d'acceptation qui en decoule par sa version du
   2026-09-06 (porteur = l'indice, items a conception fausse seulement, jamais "ta reponse") ;
2. ajouter `SCI-4` (un item a conception fausse porte au moins trois propositions) a la liste des
   conditions citees et a un critere d'acceptation dedie ;
3. citer `FER-1` a `FER-3`, absentes de la story, sur le meme patron que `DIV-CE1-1` a `-6` dans
   `S44-MAT-DIVISION`.

**Classement** (`squad/README.md` section 1) : produit, perimetre, redaction d'une story -
`camille-po`. Artefact attendu : la correction ecrite dans `docs/backlog/backlog.md`, pas un `DEC`
neuf - `theo-archi` le dit explicitement, ce n'est pas une decision de perimetre, seulement une
citation perimee a corriger.

**Ce que je n'ai pas fait** : je n'ai pas corrige le texte moi-meme. La redaction de la story
appartient a `camille-po` ; je route et j'attends sa correction ecrite avant de considerer
l'obstacle clos.

## 6. Decision de capacite - `S44-SCI-OBJET-TECHNIQUE` s'affecte des maintenant, l'ecriture du paquet attend

Le prealable de Q2 porte sur le **texte de la story**, pas sur une inconnue technique : la section
5.2 de l'avis donne deja, condition par condition, la table d'assertions que le developpeur affecte
doit encoder (`SCI-1` sur l'indice et non l'explication, `SCI-4` sur deux distracteurs, `SCI-3`,
`BIA-3`, `HIS-3`, `CON-1`, `FER-1` a `FER-3`). Rien de cette table ne change si `camille-po`
corrige le texte de la story : la correction aligne la story sur une specification que l'avis a
deja rendue, elle ne la redecide pas.

**Decision** (capacite/charge, la mienne a trancher) : j'affecte nominativement le lot des
maintenant, avec une consigne explicite au developpeur affecte - **ne pas ecrire le paquet ni son
test avant que la correction de `camille-po` (section 5 ci-dessus) soit committee et fusionnee sur
`round-two`**. Ce que cela achete : le developpeur peut deja lire le referentiel, l'avis, et les
deux paquets adjacents cites en section 6 de l'avis (`cm1-sciences-objet-technique`,
`cm1-sciences-familles-materiaux`), sans attendre inutilement avant de commencer sa lecture. Ce que
cela ne fait pas courir : aucun risque de coder contre une story qu'on sait fausse, puisque le
premier commit de code reste interdit jusqu'a la fusion de la correction. Ce n'est pas le patron de
`S43-EPS-EFFETS-ACTIVITE`/`S43-MAT-DUREE` (une condition pedagogique posee par `nadia-prof` sur un
texte de story deja exact) : ici le texte de la story est errone au sens ou le suivre a la lettre
produirait exactement le defaut que `nadia-prof` a deja nomme (avis, section 5.1, dernier
paragraphe) - donc le premier commit de code attend la correction, la lecture preparatoire non.

## 7. Affectations nominatives finales

`docs/backlog/backlog.md` (section "Sprint 44") delegue la repartition a `sofia-sm` sans proposition
nominative de `camille-po` cette fois (a la difference de s41 a s43, ou la proposition etait deja
ecrite). Affectation tranchee ici, un lot par developpeur :

| Lot | Ligne | Developpeur | Condition | Peut demarrer l'ecriture de code des maintenant ? |
|---|---|---|---|---|
| `S44-MUS-VOCABULAIRE` | `MUS-CP-11` | `marc-dev` | Q1 due avant l'ecriture du paquet (section 4) | Oui |
| `S44-MAT-DIVISION` | `MAT-CE1-18` | `lea-dev` | Aucune, prealable deja leve | Oui |
| `S44-SCI-OBJET-TECHNIQUE` | `SCI-CM1-13` | `sofia-sm` (casquette dev) | Correction `camille-po` (section 5) a fusionner avant le premier commit de code ; Q3 due avant l'ecriture du paquet (section 4) | Non - lecture preparatoire seulement |

**Pourquoi `sofia-sm` prend le lot bloque.** Sous sa casquette Scrum Master, c'est elle qui route et
suit la fermeture de l'obstacle Q2 aupres de `camille-po` : la meme personne qui verra la correction
arriver n'a pas besoin d'etre prevenue par un tiers avant de demarrer, ce qui evite un delai de
relais. `marc-dev` et `lea-dev` prennent les deux lots immediatement ouvrables, sans dependance
externe a leur premier commit.

## 8. Instance unique de `nadia-prof` - Done pedagogique en fin de sprint

Une seule instance de `nadia-prof` audite les trois lots (`DEC-0099`, section 9). Elle porte, avant
d'auditer `S44-MUS-VOCABULAIRE` et `S44-SCI-OBJET-TECHNIQUE`, les reponses qu'elle aura elle-meme
donnees a Q1 et Q3 (section 4). Budget opposable 5,0, cout retenu 4,0, marge 1,0 - non allouee entre
les lots, au niveau du sprint.

## 9. Verification `PAR-16` - point de controle poste/story

| Element | Affectation nominative | Issue |
|---|---|---|
| `S44-MUS-VOCABULAIRE` (`MUS-CP-11`) | `marc-dev`, puis `nadia-prof`, instance unique | Engagee |
| `S44-MAT-DIVISION` (`MAT-CE1-18`) | `lea-dev`, puis `nadia-prof`, instance unique | Engagee |
| `S44-SCI-OBJET-TECHNIQUE` (`SCI-CM1-13`) | `sofia-sm` (casquette dev), puis `nadia-prof`, instance unique | Engagee sous condition (section 6) - premier commit de code differe |

Aucun element n'est "non engage, budget libere" : les trois lots du perimetre de `DEC-0099` sont
tous affectes.

## 10. Ce que ce planning ne fait pas

- Il n'engage aucun `ADR` ni aucune regle ArchUnit neuve : les deux avis de faisabilite le disent
  explicitement, aucun n'est du sur ce perimetre.
- Il ne corrige pas le texte de `S44-SCI-OBJET-TECHNIQUE` : redaction de story, a `camille-po`
  (section 5), obstacle route et non clos par ce document.
- Il ne pose aucun discriminant pour Q1 ni Q3 (section 4) : question pedagogique, a `nadia-prof`
  seule.
- Il ne tranche pas le fond de `SCI-1`, `SCI-4`, `FER-1` a `FER-3` : deja rendu par l'avis de
  faisabilite, section 5.2, cite et non reproduit.
- Il ne rouvre pas `DEC-0099`.
- Il ne prononce aucun Done pedagogique ni aucun Done technique : rien n'est encore livre a ce
  sprint.

## Decisions prises

Aucune `DEC` ni `ADR` n'est cree ou amende par ce planning. Une seule decision m'appartient en
propre et elle est ecrite ici : la decision de capacite de la section 6 (affecter
`S44-SCI-OBJET-TECHNIQUE` des maintenant, ecriture de code differee a la fusion de la correction de
`camille-po`) et l'affectation nominative de la section 7. `DEC-0099` (objectif et perimetre),
l'avis de `theo-archi` sur `S44-MAT-DIVISION` et l'avis conjoint sur `S44-MUS-VOCABULAIRE`/
`S44-SCI-OBJET-TECHNIQUE` existent deja avant ce planning et ne sont pas amendes par lui.
