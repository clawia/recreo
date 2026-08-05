# [Sprint s02] Rapport de sprint - identite visuelle

- **Debut** : aucun horodatage de planning (voir anomalie de methode ci-dessous).
- **Fin** : 2026-08-05 17:34 (commit `df6b4e3`, dernier commit du sprint).
- **Duree effective** : non mesurable au sens habituel. Repere de repli, a titre indicatif
  seulement : 1h12 entre le premier commit (`5af6160`, 16:22:55) et le dernier (`df6b4e3`,
  17:34:38).

## Anomalie de methode - a lire avant le reste de ce rapport

**Ce sprint n'a eu aucun planning.** Le travail est parti d'une observation faite en jouant
l'application - elle n'avait ni style ni icone - et non d'une ceremonie geant un objectif,
affectant nominativement, ou recueillant un avis de faisabilite ecrit de `theo-archi`. Aucun
`planning.md` n'a ete fabrique retrospectivement pour combler ce vide : un fichier de ceremonie qui
n'a pas eu lieu est un faux. Le detail est dans `review.md` et `retro.md`.

## Increment livre

`recreo` a maintenant une identite visuelle : plus aucun ecran n'est nu, plus aucun bouton ni
texte lu par l'enfant ne retombe au style par defaut du navigateur, et l'application se reconnait
dans l'onglet du navigateur par une icone. `nadia-prof` a accorde le chantier apres avoir refuse et
fait corriger un recul pedagogique reel introduit par le premier increment.

En detail :

- `camille-po` a comble un trou de backlog constate en rejouant l'application : trois stories
  (S2-1, S2-2, S2-3) et `DEC-0006` sur leur perimetre.
- `ines-design` a livre une premiere version qui couvrait S2-1, S2-2 et S2-3, mais qui inversait la
  hierarchie de saillance de l'ecran de jeu : le bouton de sortie devenait la seule zone coloree et
  la plus grande, les boutons de reponse restaient gris et petits.
- `nadia-prof` a **refuse S2-1** sur ce motif (recul pedagogique demontre au pixel), et **accorde
  sous reserve S2-2 et S2-3**.
- `ines-design` a corrige : le bouton de reponse devient le bouton plein et saillant de l'ecran, la
  sortie repasse en style discret. `nadia-prof` a remesure et **leve le refus**, S2-1 passant a
  **ACCORDE SOUS RESERVE**.
- En verifiant elle-meme le garde-fou corrige (en le cassant, pas en le lisant), `nadia-prof` a
  demontre qu'il derive la liste des boutons a controler depuis leurs **classes** CSS : un bouton
  sans classe reste invisible pour la regle - la forme exacte du defaut de s01 que ce garde-fou
  existait pour empecher. Route a `yanis-qa` et `theo-archi`, non tranche par ce sprint.
- **Aucun rapport qualite de `yanis-qa` ni confirmation ArchUnit de `theo-archi` n'existe pour ce
  sprint** : la porte technique n'a pas ete rendue par ses garants (voir "Etat des deux Done").

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| DEC-0006 | Perimetre du chantier identite visuelle de s02 et statut de l'icone d'application au regard de DEC-0002 | Produit | Acceptee, **nouvelle ce sprint** |

Une seule decision ecrite ce sprint. Aucun `ADR` n'a ete cree ni amende : ni le CSS, ni le fichier
d'icone statique, ni la correction de `TailleDesTextesEtBoutonsTest` n'ont exige de decision
d'architecture nouvelle.

## Etat des deux Done

| Done | Juge par | Etat | Reference |
|---|---|---|---|
| Technique | CI (`mvn verify`) | **Non rendu par ses garants.** Aucun rapport qualite de `yanis-qa`, aucune confirmation ArchUnit de `theo-archi` pour ce sprint. Ce qui existe : `ines-design` et `nadia-prof` rapportent chacune un `mvn -B verify` vert au moment de leur travail (`nadia-prof` sur JDK 21.0.10, commit `df6b4e3`) ; `sofia-sm` a reverifie a la cloture, `mvn -B verify` sur JDK 21.0.10 a `HEAD`, **BUILD SUCCESS, 132 tests, 0 echec**, et les deux scripts de garde-fou de CI (prefixes, chemins) verts. Ce sont des observations de passage, pas la porte technique au sens de `squad/README.md` section 7. | `review.md`, section "Porte technique" |
| Pedagogique | nadia-prof | **ACCORDE** (chantier d'identite visuelle) : S2-1 ACCORDE SOUS RESERVE (refus initial leve), S2-2 et S2-3 VALIDE SOUS RESERVE (accordes). Le Done pedagogique du jeu `bandit-manchot-additions-cp`, accorde en s01, **n'a pas ete retire**. | `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, points 1 a 6 |

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos par |
|---|---|---|---|
| Trou de backlog constate en jouant (aucune story d'identite visuelle) | camille-po | `DEC-0006`, trois stories S2-1/S2-2/S2-3 | `5af6160` |
| S2-1 refuse : hierarchie de saillance inversee sur l'ecran de jeu | nadia-prof, ines-design | refus puis correction, refus leve, `FICHE-bandit-manchot-additions-cp.md` points 2 et 2 bis | `df6b4e3` |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| Aucun rapport qualite `yanis-qa` ni confirmation ArchUnit `theo-archi` pour s02 | yanis-qa, theo-archi | 2026-08-05 | un rapport qualite nomme et une confirmation ArchUnit nommee, produits par leurs garants |
| `TailleDesTextesEtBoutonsTest` derive la liste des boutons de leurs classes ; un bouton sans classe est invisible a la regle (demontre par nadia-prof) | yanis-qa, theo-archi | 2026-08-05 | une decision sur la forme de la correction (la regle ne doit pas prescrire la solution ici) |
| Audit pedagogique impossible sous ~500 px de large (harnais de nadia-prof), alors qu'un support tablette/telephone portrait est plausible | camille-po (perimetre), yanis-qa / theo-archi (outillage) | 2026-08-05 | une decision de perimetre, puis un harnais de rendu capable de largeurs plus etroites |
| Cinq reserves nouvelles de nadia-prof sur S2-1/S2-2/S2-3 (poids du chiffre, ecart entre boutons, appui rate compte comme erreur, retrogradation mal calibree sur l'ecran de fin, icone a redessiner) | camille-po | 2026-08-05 | des stories a instruire |
| Question de process ouverte par DEC-0006 (fiche de conformite distincte pour un habillage transverse une fois un deuxieme jeu existant) | camille-po | 2026-08-05 | une decision de process pour l'avenir |
| Aucun planning n'a eu lieu pour s02 | sofia-sm | 2026-08-05 | discussion de l'amendement propose en `retro.md` |
| Le trou de backlog a survecu a la creation meme d'ines-design en s01 sans qu'on lui demande le perimetre complet de son mandat | sofia-sm | 2026-08-05 | discussion de l'amendement propose en `retro.md` |
| `outils/rapport-tokens.py --sprint s02` echoue faute de planning.md | sofia-sm | 2026-08-05 | discussion de l'amendement propose en `retro.md` |

## Couverture programme

Aucun changement. `DEC-0006` l'avait deja pose : les trois stories de ce sprint sont transverses
(identite visuelle), aucune ne porte de competence du referentiel. `squad/programme/
MATRICE-couverture.md` n'est pas modifiee.

## Consommation de tokens

**Aucun `RAPPORT-tokens.md` n'existe pour ce sprint, et ce n'est pas un chiffre a zero : c'est une
mesure impossible a produire, pour une raison honnete et verifiee.**

`python outils/rapport-tokens.py --sprint s02` a ete lance depuis la racine du depot. Resultat :

```
ignore s02-identite-visuelle : pas d'horodatage de debut dans planning.md
Sprint inconnu : s02
```

(code de sortie 1). Le script construit la fenetre temporelle d'un sprint exclusivement a partir
de l'horodatage de debut lu dans `planning.md` (`squad/README.md`, section 6) ; ce sprint n'en a
pas, pour la raison exposee dans l'anomalie de methode ci-dessus. Je n'ai pas fabrique de
`planning.md` pour satisfaire l'outil.

J'ai egalement verifie, par un `--dry-run` sans `--sprint`, ce que produirait une regeneration
complete : les fenetres de s00 et de s01 y apparaissent avec des totaux differents de ceux deja
committes dans leurs `RAPPORT-tokens.md` respectifs, et un agent etranger a la squad
(`Explore`) apparait dans le bucket `hors sprint`. Le script agrege toutes les transcriptions
presentes sur le poste sous le chemin du projet, sans les isoler par session : relancer la
regeneration complete aurait donc reecrit les rapports deja committes de s00 et s01 avec du bruit
non explique plutot que de simplement combler s02. Je choisis de **ne pas** executer cette
regeneration complete : corrompre la memoire ecrite de deux sprints deja clos pour tenter d'en
produire une pour un troisieme qui n'a pas la donnee necessaire serait un remede pire que le mal.
Le sujet est route en amendement de process dans `retro.md` (amendement 4) plutot que force ici.

`squad/rapports/JOURNAL-tokens.md` n'est donc pas mis a jour par ce sprint.
