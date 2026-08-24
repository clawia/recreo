# Review du sprint 19 - contenu seul et conception de la saisie

- **Horodatage de debut** : 2026-08-24 14:00 +0200 (`planning.md`)
- **Horodatage de fin** : 2026-08-24 15:23 +0200 (dernier commit de fusion portant du contenu,
  `d79ce80`)
- **Duree effective** : environ 1 h 23 min
- **Increment audite** : `round-two`, tete de contenu `d79ce80` (deux commits de journal de
  fusion suivent, `7e4ebc3` et ceux de cette cloture, sans toucher au code ni au contenu).

## Increment livre

Cinq paquets de contenu francais CP/CM1 livres et couverts (`cm1-accords-groupe-nominal`,
`cm1-accord-sujet-verbe`, `cm1-antonymes`, `cp-nature-mot` neufs ; `S19-5` deja livree en debut de
sprint sur l'accentuation des descripteurs), la conception de la mecanique de saisie posee sans
code (`ADR-0058`), un mecanisme de correctif de largeur devenu declaratif et parametre
(`ADR-0059`), et un garde-fou qui juge le texte servi sur son point de code plutot que sur sa
notation (`ADR-0060`). Dix-sept lots fusionnes sur `round-two`, en local, non pousses
(`origin/round-two` reste a `c3d3d47`).

## Dix-sept lots fusionnes - references

Le detail de chaque fusion, sa provenance verifiee et sa resolution de conflit vivent dans les
cinq journaux du sprint : `fusion-lots-1-a-6.md`, `fusion-lots-7-a-9.md`, `fusion-lots-10-a-11.md`,
`fusion-lots-12-a-15.md`, `fusion-lots-16-a-17.md`. Ils ne sont pas recopies ici.

## Porte technique - rapport de `yanis-qa`

`bash outils/mvn-jdk21.sh -B clean verify` sur le reacteur complet (six modules) : **BUILD
SUCCESS**. **666 tests**, methode de reference (somme de l'attribut `tests` des rapports XML
surefire et failsafe), confirmes par deux methodes concordantes (`yanis-qa` et `theo-archi`,
chacun sa methode) et par la mienne, sur l'arbre fusionne final (`d79ce80`), inchange par les deux
derniers lots qui ne portent aucun code. **30 regles ArchUnit**, methode de `yanis-qa`
(`grep -rn "static final ArchRule\|static ArchRule" --include=*.java . | grep -v "/contreexemple/"`)
reproduite par moi sur l'arbre final : **30**, identique.

**Trois garde-fous mutes sur du materiau de production reel**, jamais sur leur fixture, avec
preuve de morsure explicite (`rapport-qualite.md`, section 4) :

1. Clause 8 d'`ADR-0058` (`unContratNeDeclareAucunComportement`) : `ContentSource.
   indicesNommentLaReponse()` remise `default` -> rouge, remise abstraite -> vert.
2. `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` : entree `cm1-accords-groupe-nominal` retiree du paquet
   reel livre -> rouge (trois echecs, dont deux tests d'obligation 3 heritant de l'absence) ->
   remise -> vert.
3. La regle d'`ADR-0059` (`UnPlafondMaisonNAffamePasLaRangeeTest`) : `recreo.css` production reelle
   mute (`720px` -> `540px`) -> rouge sur le test de production -> remis -> vert.

**La regle d'`ADR-0060` mordait des sa naissance sur un `U+00A0` reel** (mutation d'un octet de
`cm1-antonymes.yaml`, absorbee du lot de `yanis-qa` dans le meme commit qui l'a ecrite) - ce n'est
pas une mise a l'epreuve a posteriori, c'est la preuve fournie au moment de l'ecriture.

## Confirmation ArchUnit - `theo-archi`

`confirmation-archunit.md` : deux mesures (arbre fusionne avant son lot, puis son propre lot),
BUILD SUCCESS sur les deux, methode de comptage citee et commande jointe. Concorde avec le compte
de `yanis-qa` sur les tests et sur les 30 regles.

## Corrections du `relecteur-pedagogique`

Neuf fichiers touches, texte seul, aucun chiffre ni critere pedagogique modifie
(`relecture-s19.md`) : un tableau `ADR-index.md` recolle (ligne blanche qui le coupait entre
`ADR-0055` et `ADR-0056`), deux accents isoles corriges dans des documents ASCII (`proposition-
ines-plafond-syn16.md`, `fusion-lots-7-a-9.md`), un exemple destine a l'enfant corrige pour
respecter `DEC-0002` (`ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md`), un accord corrige
(`FICHE-bandit-manchot-antonymes-cm1.md`), un critere de backlog simplifie sans changer sa valeur,
et **trois fiches de conformite restees factuellement perimees apres livraison de leur paquet**
(`nature-mot-cp`, `accord-sujet-verbe`, `accords-groupe-nominal`) - c'est cette troisieme famille
de correction qui a directement produit la collision de fusion sur la fiche `accord-sujet-verbe`
(voir `fusion-lots-12-a-15.md`).

## Tableau des obstacles du sprint

| # | Obstacle | Categorie | Destinataire | Etat |
|---|---|---|---|---|
| 1 | `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` mord sans avoir ete annonce (`marc-dev` et `lea-dev`, independamment) | Technique / documentation | `theo-archi` | **CLOS** - avis amende, cause generalisee a tout le depot par `yanis-qa` (meme famille sur `LesGardeFousSontMisALEpreuveTest`, quatre agents mordus ce sprint) |
| 2 | Defaut d'encodage d'un espace insecable, invisible a toute porte technique | Technique | `theo-archi` **et** `yanis-qa` (double routage, erreur de `sofia-sm`) | **CLOS** - `ADR-0060`, doublon arbitre (regle de `theo-archi` retenue, quatre epreuves par mutation de `yanis-qa` absorbees) |
| 3 | `NAT-5` (renfort visuel du mot cible sur `cp-nature-mot`), jamais routee a `ines-design` | Coordination / pedagogie | `sofia-sm` puis `ines-design` (`DEC-0044`) | **OUVERT** - reserve **bloquante**, ligne de matrice non deplacee, sequence a s20 (voir plus bas) |
| 4 | `NAT-9` (consigne circulaire de `cp-nature-mot`) | Pedagogie / contenu | `lea-dev`, apres `ines-design` | **OUVERT** - liee a `NAT-5`, meme sequencement |
| 5 | `AGN-3` (apostrophes droites dans les indices de "Le bon accord") | Redaction / contenu | `camille-po` (affectation optionnelle) | **OUVERT**, non bloquant, notee sans story par `DEC-0044` |
| 6 | Trois fiches de conformite perimees apres livraison | Redaction | `relecteur-pedagogique` | **CLOS** - corrige a la relecture |
| 7 | `ADR-index.md`, section "Lecture rapide", s'arrete a `ADR-0055` (`ADR-0056` a `ADR-0060` absentes) | Redaction / documentation | `theo-archi` (route par le relecteur via moi) | **OUVERT** - je le route a `theo-archi` pour s20, non bloquant |
| 8 | Relecture lancee sur un arbre qui ne portait pas encore l'audit pedagogique en cours | Gouvernance / coordination | `sofia-sm` (moi) | **CLOS** a la fusion (collision de fiche resolue), verse en retro |
| 9 | Un avis de faisabilite ne peut pas enumerer exhaustivement les garde-fous qui mordront | Architecture / process | `theo-archi` (auto-route la contre-mesure) | **CLOS** - contre-mesure de process ecrite (deposer un artefact minimal et mesurer avant d'ecrire le contenu), a retenir pour s20 |
| 10 | Deux drafts du sprint suivant deux pratiques opposees sur l'accentuation de phrases candidates destinees a l'enfant | Redaction | `sofia-sm` (moi, convention a trancher) | **CLOS** - convention tranchee ci-dessous, amendement route a `squad/README.md` en retro |

**Sur l'obstacle 10** : je tranche pour la convention suivante, a appliquer des le prochain
sprint - tout document de travail qui propose des phrases candidates a devenir du contenu servi a
l'enfant (une proposition d'items, un arbitrage prealable) les redige deja integralement
accentuees, comme l'a fait `lea-dev`, meme si le document lui-meme reste par ailleurs redige sans
accent (registre developpeur, `CLAUDE.md`). Motif : une phrase qui va devenir du contenu servi
gagne a etre ecrite une seule fois sous sa forme finale plutot que reecrite a la validation, et
cela reduit le risque de la meme famille de defaut que celui d'`ADR-0060` (une conversion
d'accentuation ratee entre un brouillon et le contenu livre).

## Avis de `nadia-prof` et Done pedagogique

Quatre paquets audites, un refus prononce et leve dans le meme sprint sur le meme increment
reellement servi les deux fois :

| Paquet | Premier verdict | Second verdict | Etat de la ligne de matrice |
|---|---|---|---|
| `cm1-accords-groupe-nominal` | - | `VALIDE SOUS RESERVE`, aucune reserve bloquante | `couvert-valide` |
| `cm1-antonymes` | - | `VALIDE SOUS RESERVE`, aucune reserve bloquante (`SYN-4` fermee) | `couvert-valide` |
| `cm1-accord-sujet-verbe` | `REFUSE` (5 motifs, 2026-08-24) | `VALIDE SOUS RESERVE`, aucune reserve bloquante, apres correctif de `marc-dev` et reaudition sur l'arbre fusionne (`6d9c00a`) | `couvert-valide` |
| `cp-nature-mot` | - | `VALIDE SOUS RESERVE` - **une reserve BLOQUANTE (`NAT-5`)**, pas dans les mains de `lea-dev` seule | reste **en cours**, pas `couvert-valide` |

**Aucun refus ne reste ouvert** sur ce sprint : la seule ligne qui ne bouge pas (`cp-nature-mot`)
n'est pas refusee, elle porte une reserve bloquante distincte (`NAT-5`, obstacle 3 ci-dessus), et
sa ligne de matrice reste `en cours` jusqu'a ce que `NAT-5` et `NAT-9` soient traitees et
l'increment reaudite.

## Les deux Done

- **Technique** : vert. `BUILD SUCCESS`, 666 tests, 30 regles ArchUnit, aucun echec instable
  rencontre.
- **Pedagogique** : quatre verdicts prononces, trois lignes de matiere passees `couvert-valide`
  dans ce sprint, une quatrieme (`cp-nature-mot`) qui reste ouverte sur une reserve nommee et
  routee, pas sur un refus.
