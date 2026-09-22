# Rapport de sprint s37 - premier paquet mathematiques CE1 et ordre des niveaux

- **Duree** : 2026-09-21 22:05 -> 2026-09-22 08:35 (UTC+02:00), 10 h 30 min.
- **Objectif gele** (`planning.md`) : deux lots disjoints - engager `S36-ORDRE-NIVEAUX` (deja
  livree au planning, aucune action due) et lever le blocage pedagogique de `S37-1` (premier
  paquet mathematiques CE1, `MAT-CE1-03` sous-partie additions) avant tout developpement.

## L'increment livre, en une phrase

Le troisieme niveau du produit recoit son premier paquet jouable - additions dans la limite de
100 (`MAT-CE1-03`, Done pedagogique **VALIDE SOUS RESERVE**) - et l'en-tete affiche desormais les
niveaux dans l'ordre de la progression scolaire (CP, CE1, CM1) au lieu de l'ordre du catalogue,
garanti par un garde-fou de completude.

## Les decisions du sprint

| Decision | Titre | Statut |
|---|---|---|
| `ADR-0095` | L'ordre des niveaux est une cle de tri (`OrdreDesNiveaux.SEQUENCE`), et tout niveau servi est nomme dans la sequence (garde-fou de completude) | **Accepte** |
| `ADR-0096` | La repartition par forme des additions CE1 est la regle (`ADD-CE1-1`, `ADD-CE1-2`, garde-fou derive de la forme des items) ; le paquet statique en est la consequence, pas le generateur etendu | **Accepte** |

Les deux entrees sont verifiees presentes sur le disque, avec leur statut a jour, dans
`squad/decisions/adr/ADR-index.md`. Aucune ceremonie de ce sprint (planning, review, retro) n'a
elle-meme cree d'`ADR` ni de `DEC` : les deux ci-dessus ont ete redigees en cours de sprint, sur
des obstacles rencontres, puis enregistrees ici.

## Etat des deux Done

### Technique - verifie par `sofia-sm` sur l'arbre fusionne final, quatre jobs nommes un par un

| Job | Resultat |
|---|---|
| `build` (`bash outils/mvn-jdk21.sh -B clean verify`) | **BUILD SUCCESS**, 6 modules sur 6 |
| `chemins` | **vert** - aucun chemin de poste de travail ni nom de compte |
| `outils` | **vert** - 9 juges purs executes, 0 echec |
| `prefixes` | **vert** - tous les commits portent un prefixe d'agent connu |

**1518 tests unitaires + 6 tests d'integration = 1524, 0 echec, 0 erreur, 0 ignore**, mesures par
moi sur le journal complet du dernier lancement de `build` sur l'arbre fusionne (`45a25fe9`),
methode blocs `[INFO] Results:` par module - recoupe le compte autoritaire de `yanis-qa` (memes
1518+6, methode XML independante). Un ecart de methode (1509, somme fautive des rapports `.txt`)
a ete produit par moi-meme en cours de sprint et corrige par `yanis-qa` (`review.md`, section 2) ;
il n'affecte pas ce chiffre final. **ArchUnit** : `static final ArchRule` 34 -> 36, `@ArchTest`
33 -> 35, methode citee dans `ADR-0095` et `ADR-0096`.

**Porte technique verte dans son ensemble : les quatre jobs de `.github/workflows/ci.yml` sont
verifies, aucun n'est deduit.**

### Pedagogique - mixte, prononce par `nadia-prof`

| Story | Verdict | Ce qui ferme |
|---|---|---|
| `S37-1` (`MAT-CE1-03`, additions dans la limite de 100) | **VALIDE SOUS RESERVE** | Premier paquet mathematiques CE1 du produit ; deux reserves non bloquantes (`ADD-CE1-R1`, `ADD-CE1-R2`) |
| `S36-ORDRE-NIVEAUX` | **Pas de Done pedagogique** - story transverse, aucune competence de programme | aucun |

**Une validation sous reserve, une story sans Done car transverse.** Le Done pedagogique est
prononce dans `squad/conformite/FICHE-bandit-manchot-additions-ce1.md`, dont la section finale
confirme aussi `S36-ORDRE-NIVEAUX` sur l'ecran reellement servi sans en tirer de verdict pedagogique.

## Couverture du programme

**77/155 (49,7 %), inchangee.** Un seul mouvement de statut : `MAT-CE1-03` passe de `non couvert`
a `en cours` - un mouvement qui ne touche pas le numerateur (`couvert-valide` seul), verifie par
`camille-po` sur son propre diff.

## Le registre de dette

**Inchange, 9,25** (`review.md`, section 7). Ni `S37-1` ni `S36-ORDRE-NIVEAUX` ne sont des postes
de la composition du Plan 2 (`DEC-0086`/`DEC-0087`) : verifie par recherche des identifiants de ce
sprint dans les deux textes, aucune occurrence.

## Obstacles resolus et encore ouverts

**Resolus** : les deux reponses pedagogiques qui bloquaient `S37-1` (cible d'operande nul,
`ADD-CE1-1` ; table de repartition des sommes, `ADD-CE1-2`), rendues par `nadia-prof` avant tout
developpement ; le choix technique entre generateur recalibre et paquet statique, tranche par
`theo-archi` (`ADR-0096`) ; mon propre ecart de methode de comptage de tests (1509 au lieu de
1518), trouve et corrige par `yanis-qa`.

**Encore ouverts** : `MAT-CE1-18` (sens de la division, ajoutee au referentiel a s36) sans ligne
dans `squad/programme/MATRICE-couverture.md` - signale une seconde fois par `camille-po`, non
corrige, dette a router a un sprint ou elle est affectee ; `ADD-CE1-R1` et `ADD-CE1-R2` (deux
indices a corriger dans le paquet livre, textes de remplacement deja ecrits par `nadia-prof`) ;
le libelle transverse d'ecran de fin (*"Tu as trouve, tout seul :"*), constat hors perimetre de ce
sprint route a `camille-po`.

Tableau complet, avec la categorie et la route de chaque obstacle : `review.md`, section 6.

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s37`, fenetre 2026-09-21 20:05 -> 2026-09-22 06:35
UTC. Detail complet dans `RAPPORT-tokens.md` (ce dossier) et cumule dans
`squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 75 | 150 | 22 266 | 415 374 | 14 114 032 |
| `sofia-sm` | claude-sonnet-5 | 130 | 260 | 42 094 | 696 302 | 17 846 549 |
| `theo-archi` | claude-opus-5 | 86 | 172 | 67 820 | 1 462 130 | 21 883 902 |
| `yanis-qa` | claude-sonnet-5 | 124 | 248 | 34 580 | 347 465 | 12 194 256 |
| `nadia-prof` | claude-opus-5 | 188 | 376 | 89 791 | 586 212 | 14 337 451 |
| orchestrateur (fil principal) | claude-sonnet-5 | 26 | 52 | 28 664 | 609 732 | 6 562 403 |
| **Total** | | **629** | **1 258** | **285 215** | **4 117 215** | **86 938 593** |

Aucune invocation mesuree sur cette fenetre pour : `marc-dev`, `lea-dev`, `relecteur-pedagogique`,
`ines-design`.

Les quatre compteurs sont volontairement separes (voir `RAPPORT-tokens.md`) : les additionner
donnerait un classement faux, le cache lu depassant couramment l'output de deux ordres de
grandeur.
