# [Sprint s56] Planning - 2026-10-09

- **Horodatage de debut** : 2026-10-09 20:36 (UTC+02:00), lu par `date` au moment de l'ecriture.
- **Anime par** : sofia-sm, casquette Scrum Master. Aucune story de developpement portee par moi dans ce fichier.
- **Participants** : aucun agent invoque. Ce planning constate l'etat du depot, mesure, decoupe et affecte.
  **Sources lues, par auteur** : camille-po (`DEC-0116` en entier, amendement A.1 et A.2 ; backlog "Sprint 56" et ses amendements) ; sofia-sm (`planning.md`
  de s55, `retro.md` de s55 pour A1 a A15 via `DEC-0116` section 6) ; theo-archi, nadia-prof, ines-design : **rien d'ecrit pour s56** (voir plus bas).
- **Slug** : `s56-paquet-art-cp-10-et-francais-ce1-13`. Le dossier n'existait pas (`ls squad/sprints` suivi de `grep -c '^s56-'` rendait 0) ; il est cree par ce planning.
- **Lignee verifiee avant ecriture** : branche `s56-sofia-planning`, `git log -1 --format=%h` rend `4cd44098` (tip de `round-two`, qui porte `DEC-0116` et son amendement),
  `bash outils/verifier-lignee.sh` sort 0 (*"descend de la lignee de round-two"*), `git status --short` vide.
- **`lessons.md`** : le depot n'en a pas (constat de s55, non remesure). La memoire des corrections est `retro.md` et `squad/README.md`.

## Objectif du sprint

Gele : **ecrire puis auditer le premier paquet a reproductions d'`ART-CP-10` (la forme du dispositif livre a s55, sans cousine), brancher un lot de francais CE1 (`ce1-verbe-sujet`,
`FRA-CE1-13`) et lever six reserves de jeux deja servis (`ACV-CE1-R1`, `ACV-CE1-R2`, `CLM-CE1-R1`, `EMC-CM1-R8`, `EMC-CM1-R9`, `EXC-R16`).** Composition : option O3 de `proposition-s56.md`,
ecart de 0,5 au-dessus de `PAR-9` accorde pour s56 seulement (`DEC-0116` 2.2 et A.1). Au mieux deux lignes ferment : **141/182 (77,47 %) a 143/182 (78,57 %)**. Ce n'est pas une promesse :
un Done pedagogique refuse reste un refus, et aucune ligne de matrice ne bouge avant le verdict ecrit de `nadia-prof`.

## Provenance, et ce qui manque a la cloture de ce planning

- **Source du perimetre** : `DEC-0116`, statut **`Proposee`** (0.5, A.3) : l'ordre des matieres (`Q4`) n'a pas de reponse. `DEC-0116` 0.5 dit que **rien de s56 n'attend `Q4`** : la composition, l'ecart
  et le proprietaire du paquet viennent des mots de l'utilisatrice, que la decision cite en forme 2 sans les avoir lus de sa main (0.1, A.1). Je ne les relis pas : je planifie ce que la decision ecrit.
  Elle est sur `round-two` : `git merge-base --is-ancestor 4cd44098 HEAD` sort 0 (critere C1 rempli).
- **Aucun avis de faisabilite de `theo-archi` pour s56** : `ls squad/sprints/s56-*/avis-faisabilite.md` ne rend rien (le dossier est neuf). Je ne l'ai pas invoque et je n'ecris pas son avis.
- **Aucun arbitrage prealable de `nadia-prof` pour s56** : `ls squad/conformite` suivi de `grep -c s56` rend **0** a 20:36. Les tarifs ci-dessous sont ceux de `DEC-0116` 2.1, qui reprend ceux de ses fiches
  et arbitrages de s55 ; **je n'en ecris aucun comme le sien**. Conformement a l'amendement de s48 (`squad/README.md` section 9), le planning affecte sans attendre l'avis ni les arbitrages ;
  **`review.md` dira comment ils l'ont rattrape.**
- **CI distante** : aucun verdict lu pour ce planning. **Je ne dis pas que la CI est verte** ; `A3` (lecture de la CI, job par job, sur un commit nomme) est a l'utilisatrice, second report (tableau des actions).
- **Lecon de s55, appliquee ici** (retro de s55, C2 et action A5) : **aucun poste n'existe sans ligne dans ce planning ou dans un amendement date de lui, avant l'ouverture du worktree concerne.**
  Un poste issu d'un verdict, d'une reserve ou d'un rouge de CI s'ajoute par amendement de ce fichier, et l'agent qui lance cite la ligne dans son message de lancement. Les postes sans prix (A4, A8, A11, A12, A13, A14) ont leur ligne plus bas.
- **Trailers** : les commits de cette tache portent `Co-Authored-By: Claude Sonnet 5.5 <noreply@anthropic.com>`, le modele que cette session execute.

## Capacite : ce que les plafonds de `nadia-prof` permettent

Regles lues dans leur texte (`DEC-0116` 2.4), non amendees par ce planning : `PAR-1` (un lot n'est jamais partage), `PAR-2` (une matiere et un niveau ne sont jamais partages dans un sprint), `PAR-8` (borne 8,0, plafond dur 5,0
par instance, au plus trois lots par instance, marge de rejeu de 0,5), `PAR-9` (somme des budgets par instance egale a la borne), `PAR-13` (un seul terrain neuf par sprint), `PAR-14` (reprise touchant plus de la moitie des items = tarif d'un lot),
`PAR-16` (un poste sans story n'est pas alloue). `PAR-11` : non tranchee, non appliquee (l'audit de 2,5 porte deja son +0,5).

Tarifs de `DEC-0116` 2.1, additionnes par moi (aucun n'est mesure, ce sont des prix) :

| Poste | Instance A (francais CE1, choix des oeuvres) | Instance B (EMC CM1, EMC CP, arts plastiques CP) |
|---|---|---|
| Arbitrage prealable | 0,5 | 0,5 |
| Marge de rejeu, allouee a aucun lot | 0,5 | 0,5 |
| Lot `ce1-verbe-sujet` | 1,0 | - |
| `ACV-CE1-R1` 0,25 + `ACV-CE1-R2` 0,5 + `CLM-CE1-R1` 1,0 (`PAR-14`) | 1,75 | - |
| `EMC-CM1-R8`, `EMC-CM1-R9`, `EXC-R16`, 0,25 chacune | - | 0,75 |
| Choix des oeuvres distractrices | 0,25 | - |
| Audit du paquet a reproductions (1,5 + 0,5 illustre + 0,5 forme sans contenu) | - | 2,5 |
| Arbitrage de forme (`S56-ART-CP-10-FORME`) | - | 0,25 |
| **Total** | **4,0** (0,5 + 0,5 + 1,0 + 1,75 + 0,25) | **4,5** (0,5 + 0,5 + 0,75 + 2,5 + 0,25) |

**Somme recomptee par moi : 8,5 sur la borne de 8,0, soit 0,5 d'ecart, egal a l'ecart accorde (`DEC-0116` A.1).** Plafond dur de 5,0 : B a 4,5, non approche. Lots : A en porte deux (`-13` ; `CLM-CE1-R1` au tarif d'un lot,
`PAR-14`), B un (l'audit) : sous le maximum de trois. **Ecart accorde, pas jurisprudence** (`DEC-0114` A.20) : la `DEC` d'ouverture de s57 compte selon `PAR-9` telle qu'ecrite, 8,0.
**Si `nadia-prof` place le choix des oeuvres sur B** (`PAR-2`, `DEC-0116` 2.3 point 2) : A 3,75, B 4,75, au-dela de l'ecart : amendement et question a l'utilisatrice avant l'ouverture du premier worktree du paquet.
Si le rejeu groupe de `ACV-CE1-R1` et `ACV-CE1-R2` coute moins que 0,75, **la difference n'est pas allouee ailleurs sans amendement** (`DEC-0116` 2.4).

## Stories retenues, postes hors `PAR`, verification poste par poste (`PAR-16`)

Je suis `DEC-0116` section 7 ligne par ligne et je verifie, pour chaque poste, que la story a un titre `###` dans `docs/backlog/backlog.md` a partir de la ligne 20802 (`awk 'NR>=20802' docs/backlog/backlog.md | grep -c '^### .*<story>'`,
lance sur `4cd44098`) et que le prix du tableau du backlog est celui de la `DEC`. `S55-ART-CP-10-PAQUET` rend 2 : un titre propre, et la mention du titre `S56-AUD-ART-CP-10` ("Done pedagogique de `S55-ART-CP-10-PAQUET`").

| # | Poste | Story | Titre au backlog | Inst. | Agent nominatif | Prix `DEC` = prix backlog | Verdict |
|---|---|---|---|---|---|---|---|
| 1 | Arbitrage prealable de A | `S56-ARB-A` | 1 | A | `nadia-prof` (A) | 0,5 = 0,5 | ok |
| 2 | Lot `ce1-verbe-sujet` | `S56-BR-FRA-CE1-13` | 1 | A | `marc-dev` | 1,0 = 1,0 | ok |
| 3 | `ACV-CE1-R1` | `S55-ACV-CE1-R1` | 1 | A | `nadia-prof` (A, rejeu) ; aucun dev, texte applique (`2b66a441`) | 0,25 = 0,25 | ok |
| 4 | `ACV-CE1-R2` | `S55-ACV-CE1-R2` | 1 | A | `marc-dev` | 0,5 = 0,5 | ok |
| 5 | `CLM-CE1-R1` | `S55-CLM-CE1-R1` | 1 | A | `marc-dev` | 1,0 = 1,0 | ok |
| 6 | Choix des oeuvres distractrices | `S56-ART-CP-10-OEUVRES` | 1 | A (sous `PAR-2`) | `nadia-prof` (A) | 0,25 = 0,25 | ok |
| 7 | Marge de rejeu de A | aucune | - | A | aucun | 0,5 = 0,5, allouee a aucun lot | ok (nommee) |
| 8 | Arbitrage prealable de B | `S56-ARB-B` | 1 | B | `nadia-prof` (B) | 0,5 = 0,5 | ok |
| 9 | `EMC-CM1-R8` | `S55-EMC-CM1-R8` | 1 | B | `lea-dev` | 0,25 = 0,25 | ok |
| 10 | `EMC-CM1-R9` | `S55-EMC-CM1-R9` | 1 | B | `lea-dev` | 0,25 = 0,25 | ok |
| 11 | `EXC-R16` | `S55-EXC-R16` | 1 | B | `nadia-prof` (B, rejeu) ; aucun dev, texte applique (`e6262ac2`) | 0,25 = 0,25 | ok |
| 12 | Audit du paquet | `S56-AUD-ART-CP-10` | 1 | B | `nadia-prof` (B) | 2,5 = 2,5 | ok |
| 13 | Arbitrage de forme | `S56-ART-CP-10-FORME` | 1 | B | `nadia-prof` (B), avec `ines-design` et `theo-archi` | 0,25 = 0,25 ; le backlog le dit "hors de l'ecart", **remplace par l'amendement du backlog du 2026-10-09 (couvert par l'ecart de 0,5)** | ok |
| 14 | Marge de rejeu de B | aucune | - | B | aucun | 0,5 = 0,5, allouee a aucun lot | ok (nommee) |
| 15 | Paquet, T4 et T5 | `S55-ART-CP-10-PAQUET` | 2 (voir plus haut) | hors `PAR` | `lea-dev` | 0,0 = 0,0 | ok |
| 16 | Plafond de 176 px | `S55-ART-CP-10-PLAFOND` | 1 | hors `PAR` | `lea-dev` ; prix et forme : `theo-archi` | 0,0 = 0,0 | ok |
| 17 | Marque de l'echec | `S56-ART-CP-10-ECHEC` | 1 | hors `PAR` | `lea-dev` | 0,0 = 0,0, **critere de la marque a ecrire par amendement avant le worktree** | ok sous cette condition |

Dix-sept lignes : la `DEC` en compte seize, parce qu'elle ecrit une seule ligne pour les deux marges de rejeu (une par instance) ; j'en fais deux, une par instance. Quinze postes ont une story. **Aucun poste sans story, aucun sans affectation nominative.** Sorties de `DEC-0116` 7, non reprises :
rien. Sorties du tableau du backlog : rien. L'ecart de nom entre l'agent du backlog ("aucun") et celui de la `DEC` (`nadia-prof` pour les rejeux, lignes 3 et 11) est une difference de colonne : le backlog nomme le developpeur, la `DEC` l'agent.

**Postes sans prix de `nadia-prof`, a leur ligne** (`DEC-0116` section 9 : engager une action de `theo-archi`, `yanis-qa`, `ines-design`, du `relecteur-pedagogique` ou de `nadia-prof` sans budget ne consomme rien, `PAR-16` ne s'y applique pas) :

| Poste | Agent | Artefact | Ou il est ecrit |
|---|---|---|---|
| A4 cause du rouge de CI que `a3606b2f` corrige | `theo-archi` | note, avec la commande ou le message qui le reproduit | son avis de faisabilite de s56 |
| A14 la formule de la forme 3 peut-elle etre gardee par un test ? | `theo-archi` | test, ou refus motive | son avis de faisabilite de s56 |
| A8 rejouer `Q-Y8` sur `2224e91e`, mutation d'A7, morsures de T2 | `yanis-qa` | rapport qualite | `rapport-qualite.md` de s56 |
| A11 en-tete de `FICHE-bandit-manchot-exclusion-cp.md`, titre du jeu | `nadia-prof` (B) | fiche | avec le rejeu d'`EXC-R16` |
| A12 ponctuation des 12 indices de `cm1-grands-nombres` | `relecteur-pedagogique` | une ligne | `relecture-cloture.md`, meme sans trouvaille |
| A13 lever `R4`, `F2`, `F3` quand le fichier est touche | `lea-dev` | commits des rejeux | citation de 2018 avec `S55-EMC-CM1-R9` ; commentaire de `manche.html` si `S56-ART-CP-10-ECHEC` le touche |
| Lecture de testabilite des criteres du backlog "Sprint 56" | `yanis-qa` | `lecture-testabilite.md` | avant la premiere livraison de code |
| `Q1` (proposition de marque), `Q7` (releve a dix largeurs sur le gabarit livre) | `ines-design` | fichier de `squad/design/` | `Q7` apres `Q1`, avant l'entree de catalogue ; elle propose, ne tranche pas |
| Fiches de conformite de `-13` et du paquet a reproductions | `camille-po` | `FICHE-bandit-manchot-<id>.md` | apres le commit de l'entree de catalogue (`DEC-0115` A.11) |
| Amendement des criteres de `S56-BR-FRA-CE1-13` et de `S56-ART-CP-10-ECHEC` | `camille-po` | amendement du backlog, mots de `nadia-prof` | avant l'ouverture des worktrees concernes |

## Ordre d'execution, par agent

Un dev tient un seul worktree a la fois. **Chemin critique : le paquet** (audit de 2,5 aval). Les prealables, dans l'ordre de `DEC-0116` 3.1 : `DEC-0116` fusionnee (fait) ; `S56-ARB-A`, `S56-ARB-B` et l'avis de `theo-archi` ;
`S56-ART-CP-10-OEUVRES` avant la premiere ligne du paquet ; `S56-ART-CP-10-FORME` avant l'entree de catalogue.

**lea-dev, contraint** (`DEC-0116` 3.1 point 4, `ADR-0104` tableau "Cout et decoupage" : T5 depend de T2 a T4 et de l'arbitrage prealable) :

1. `S55-ART-CP-10-PAQUET` : le fichier du paquet, T4 (restaurer les reproductions et leurs entrees de registre, relever `REPRODUCTIONS_SERVIES` et `POIDS_TOTAL_MAXIMUM`), le registre, **tout sauf l'entree de catalogue**. Ouvre apres `S56-ARB-A`, l'avis de `theo-archi` et `S56-ART-CP-10-OEUVRES`.
2. `S55-ART-CP-10-PLAFOND` : le garde-fou du plafond de 176 px. Forme et prix de `theo-archi` (avis).
3. `S56-ART-CP-10-ECHEC` : la marque de l'echec. **Ne s'ouvre pas sans l'amendement du critere de la marque** (reponse a `Q1` de `S56-ART-CP-10-FORME`, voie de `theo-archi`).
4. L'entree de catalogue du paquet (branchement). Ancetres exiges : plafond et echec (`git merge-base --is-ancestor`). Apres `S56-ART-CP-10-FORME` et `Q7` d'`ines-design`.
5. `S55-EMC-CM1-R8`, puis `S55-EMC-CM1-R9` (avec la citation de 2018, A13).

**Heure de fusion du paquet** (`DEC-0116` 3.3 : *"sofia-sm inscrit a son planning l'heure a laquelle le paquet doit etre fusionne"*). **C'est une estimation de Scrum Master, pas une mesure** : le depot n'a aucun paquet
de cette forme et aucun comparable mesure. Repere seul : s55 a dure 7 h 38 d'horloge (`review.md` de s55), mecanique du dispositif comprise.

| Echeance | Heure (UTC+02:00) | Ce qui se passe au-dela |
|---|---|---|
| Prealables presents : `S56-ARB-A`, `S56-ARB-B`, avis de `theo-archi`, `S56-ART-CP-10-OEUVRES` | 2026-10-10 08:00 | je le dis a l'utilisatrice le jour meme, avec la liste de ce qui manque et de qui |
| `S55-ART-CP-10-PAQUET` (fichier, T4, registre) fusionne sur `round-two` | 2026-10-10 12:00 | idem : le dire le jour meme, sans attendre la cloture |
| Entree de catalogue du paquet fusionnee | 2026-10-10 20:00 | **l'audit (2,5) glisse : poste engage non livre, report compte** (`DEC-0116` 3.3) |

**Ce que je dirai a l'utilisatrice en cas de glissement, en ces termes** : la ligne `ART-CP-10` porte **quatre reports (s50 a s53) et deux engagements sans fermeture (s54, s55)** ; un report de l'audit est le premier de ce poste
mais ne repart pas de zero. **Si le paquet est en retard**, `S55-EMC-CM1-R8` et `S55-EMC-CM1-R9` passent a `marc-dev` par amendement de ce fichier (precedent : justification 2 du planning de s55). `S55-ART-CP-10-PAQUET` ne change pas de proprietaire
sans l'utilisatrice, qui a nomme `lea-dev`. Si le paquet est en retard parce qu'un prealable manque, le report est a lui, pas a `lea-dev` : l'obstacle va au tableau de `review.md` avec son agent.

**marc-dev, propose dans `DEC-0116` 7** : `S56-BR-FRA-CE1-13` (ferme une ligne), `S55-ACV-CE1-R2`, `S55-CLM-CE1-R1`. **Dependance** : `S56-BR-FRA-CE1-13` ne s'ouvre pas avant que `S56-ARB-A` confirme le paquet
et que `camille-po` amende ses criteres (conditions de `ce1-verbe-sujet` : mots de `nadia-prof`). **Si marc-dev est libre et que `S56-ARB-A` ne l'est pas**, il prend `S55-ACV-CE1-R2` en premier : ses criteres sont deja ecrits, aucun
n'attend `S56-ARB-A`. C'est de la capacite, donc a moi ; je l'ecris maintenant pour ne pas le decouvrir au daily, et l'attente est une entree du tableau d'obstacles de `review.md`. `-13` redevient le suivant des que `S56-ARB-A` est ecrit.
Si `nadia-prof` remplace `-13` par `-15` (`ce1-accord-groupe-nominal`), la story change de nom, l'affectation et le tarif restent (`DEC-0116` 1).

**nadia-prof, instance A** : `S56-ARB-A` d'abord (elle debloque marc-dev, lea-dev et le choix des oeuvres), puis `S56-ART-CP-10-OEUVRES` (avant la premiere ligne du paquet ; `git merge-base --is-ancestor` du document vers le premier commit du paquet),
puis audits de `-13` et rejeux d'`ACV-CE1-R1`, `ACV-CE1-R2`, `CLM-CE1-R1` au fil des livraisons.
**nadia-prof, instance B** : `S56-ARB-B`, puis `S56-ART-CP-10-FORME` (avant l'entree de catalogue, pas avant le fichier), rejeu d'`EXC-R16` (avec A11), rejeux d'`EMC-CM1-R8` et `-R9`, puis `S56-AUD-ART-CP-10` apres l'entree de catalogue.
**Les deux ecrivent la meme ressource sans le savoir** (forme (c), s50) : scratchpad et worktree dedies ; `REGISTRE-identifiants.md` s'ecrit depuis le worktree de l'instance.

**ines-design** : `Q1`, puis `Q7`, avant l'entree de catalogue ; `Q1` est sur le chemin de `S56-ART-CP-10-ECHEC`. **theo-archi** : l'avis (section plus bas) en premier, parce que quatre de ses reponses sont des prealables de `lea-dev`.
**yanis-qa** : lecture de testabilite avant la premiere livraison de code, puis la liste des verbes du premier groupe de `PRS-CE1-1` avec `marc-dev` (critere de `S55-ACV-CE1-R2`), puis rapport qualite de cloture sur l'arbre fusionne.
**relecteur-pedagogique** : `relecture-cloture.md` a la cloture (A12). **camille-po** : amendements des criteres (`-13`, `ECHEC`), fiches apres chaque entree de catalogue, matrice au verdict seulement. **sofia-sm** : lignee a chaque fusion, `review.md`,
`RAPPORT.md`, `python outils/rapport-tokens.py --sprint s56` (avec `--sprint` explicite), `retro.md`. Le daily n'est pas journalise.

## Isolation : branche, worktree, scratchpad, un par agent et par poste (action A5)

**Chaque agent a lancer a sa ligne ici.** Les worktrees sont des dossiers voisins du checkout, nommes `recreo-<branche>`. Scratchpad : un dossier hors depot nomme comme dans la colonne, **choisi et cree par l'agent qui ouvre** (aucun chemin n'est ecrit ici) ;
un scratchpad dedie par instance de `nadia-prof`. Un port de jeu par instance si `nadia-prof` en lance un. Les worktrees sont ouverts **par l'agent qui me lance**, apres la fusion de ce planning sur `round-two` ; cette tache n'en ouvre aucun.

| Agent | Poste | Branche | Worktree (voisin du checkout) | Scratchpad |
|---|---|---|---|---|
| sofia-sm (ce planning) | planning de s56 | `s56-sofia-planning` | `../recreo-s56-sofia-planning` | `s56-sofia` |
| theo-archi | avis de faisabilite (AV1 a AV8), A4, A14 | `s56-theo-avis` | `../recreo-s56-theo-avis` | `s56-theo` |
| nadia-prof, instance A | `S56-ARB-A`, `S56-ART-CP-10-OEUVRES`, rejeu `ACV-CE1-R1`, audits et rejeux de A | `s56-nadia-a` | `../recreo-s56-nadia-a` | `s56-nadia-a` (dedie A) |
| nadia-prof, instance B | `S56-ARB-B`, `S56-ART-CP-10-FORME`, rejeu `EXC-R16`, rejeux d'EMC, `S56-AUD-ART-CP-10` | `s56-nadia-b` | `../recreo-s56-nadia-b` | `s56-nadia-b` (dedie B) |
| ines-design | `Q1`, `Q7` | `s56-ines-design` | `../recreo-s56-ines-design` | `s56-ines` |
| camille-po | amendements des criteres, fiches, matrice au verdict | `s56-camille-suivi` | `../recreo-s56-camille-suivi` | `s56-camille` |
| lea-dev, poste 1 | `S55-ART-CP-10-PAQUET` | `s56-lea-paquet` | `../recreo-s56-lea-paquet` | `s56-lea` |
| lea-dev, poste 2 | `S55-ART-CP-10-PLAFOND` | `s56-lea-plafond` | `../recreo-s56-lea-plafond` | `s56-lea` |
| lea-dev, poste 3 | `S56-ART-CP-10-ECHEC` | `s56-lea-echec` | `../recreo-s56-lea-echec` | `s56-lea` |
| lea-dev, poste 4 | entree de catalogue du paquet | `s56-lea-catalogue` | `../recreo-s56-lea-catalogue` | `s56-lea` |
| lea-dev, postes 5 et 6 | `S55-EMC-CM1-R8`, `S55-EMC-CM1-R9` (un commit par poste) | `s56-lea-emc` | `../recreo-s56-lea-emc` | `s56-lea` |
| marc-dev, poste 1 | `S56-BR-FRA-CE1-13` | `s56-marc-fra-ce1-13` | `../recreo-s56-marc-fra-ce1-13` | `s56-marc` |
| marc-dev, poste 2 | `S55-ACV-CE1-R2` | `s56-marc-acv-ce1-r2` | `../recreo-s56-marc-acv-ce1-r2` | `s56-marc` |
| marc-dev, poste 3 | `S55-CLM-CE1-R1` | `s56-marc-clm-ce1-r1` | `../recreo-s56-marc-clm-ce1-r1` | `s56-marc` |
| yanis-qa | lecture de testabilite, test des verbes, A8, rapport qualite | `s56-yanis-qualite` | `../recreo-s56-yanis-qualite` | `s56-yanis` |
| relecteur-pedagogique | relecture de cloture, A12 | `s56-rel-redaction` | `../recreo-s56-rel-redaction` | `s56-rel` |
| sofia-sm (cloture) | `review.md`, `RAPPORT.md`, `RAPPORT-tokens.md`, `retro.md` | `s56-sofia-cloture` | `../recreo-s56-sofia-cloture` | `s56-sofia` |

**Pourquoi une branche par poste pour `lea-dev` et `marc-dev`** : chaque poste s'ouvre apres une fusion precise (OEUVRES, amendement de `ECHEC`, `FORME`) et les ancetres se verifient sur des commits fusionnes
(`git merge-base --is-ancestor`, `DEC-0116` 3.1, criteres de `S55-ART-CP-10-PLAFOND` et du paquet). Les agents ne rebasent, ne poussent et ne fusionnent pas : une branche neuve part du sommet de `round-two` fusionne.
`s56-lea-emc` porte deux postes de meme file, comme la regle de s55 (une branche, un commit par poste, fusion par commit).
**Chaque agent note `git log -1 --format=%h` dans son premier message** ; **je verifie la lignee avant chaque fusion** (`bash outils/verifier-lignee.sh`), meme quand l'agent dit l'avoir faite.

**Longueur des chemins.** Le chemin suivi le plus long fait 219 caracteres a `4cd44098` (`git ls-files | awk '{ print length($0) }' | sort -n | tail -1`). Le plus long nom de dossier ci-dessus est
`recreo-s56-marc-fra-ce1-13` (et `-acv-ce1-r2`, `-clm-ce1-r1`) : 26 caracteres, soit 255 avec la formule du planning de s54 (nom + 10 de prefixe de poste) : marge de 5 sous 260. Non verifie sur un autre poste.

## Ordre de fusion

Reacteur complet, `bash outils/mvn-jdk21.sh -B clean verify`, a chaque commit de fusion ; **tout compte de tests est celui du reacteur sur l'arbre fusionne**, jamais la somme de deux branches. Dernier total connu : **2307** tests
(mesure de `DEC-0116` 0.3, perimetre : reacteur de `bootstrap` avec `-am`, a `c986adcf`) ; **le total de s55 a la cloture n'est pas relu ici**, a remesurer.

1. Decisions et portes : ce planning ; `S56-ARB-A`, `S56-ARB-B` ; l'avis de `theo-archi` ; `S56-ART-CP-10-OEUVRES` ; `Q1` d'`ines-design` ; l'amendement de `S56-ART-CP-10-ECHEC` ; l'amendement de `S56-BR-FRA-CE1-13`.
2. **`S55-ART-CP-10-PAQUET`** (fichier, T4, registre), **avant toute autre livraison de `lea-dev`** (`DEC-0116` 3.3).
3. `S55-ART-CP-10-PLAFOND`, puis `S56-ART-CP-10-ECHEC`.
4. `S56-ART-CP-10-FORME` et `Q7`, puis **l'entree de catalogue du paquet** (ancetres : plafond et echec).
5. Couloir de `marc-dev` en parallele des points 2 a 4 : `-13` (apres `S56-ARB-A`), `S55-ACV-CE1-R2`, `S55-CLM-CE1-R1`, dans l'ordre de livraison.
6. `S55-EMC-CM1-R8`, `S55-EMC-CM1-R9` de `lea-dev`, apres l'entree de catalogue du paquet.
7. Audits et rejeux de `nadia-prof`, au fil des livraisons ; cloture.

**Fichiers communs.** `catalogue.yaml` : deux entrees neuves (`-13` par `marc-dev`, le paquet par `lea-dev`) ; l'inventaire de `TexteAccentueExposeParLePortEntrantTest` et les declarations des garde-fous sont modifies par les deux couloirs.
**Au conflit on garde les ajouts des deux cotes, jamais une assertion affaiblie** ; le premier pret est fusionne le premier, le second refait un `verify` complet apres fusion. `REGISTRE-identifiants.md` : ecrit par `nadia-prof` seule, depuis le worktree de son instance.
`recreo.css` : touche par le plafond (aucune ligne, test seulement, selon sa forme) et par `S56-ART-CP-10-ECHEC` ; **la ligne 1780 du critere de mutation du plafond est celle de `4cd44098`** : si une fusion anterieure la deplace, le developpeur relit la ligne avant de muter.

## Actions de la retro de s55 : A1 a A15

Sort d'apres `DEC-0116` section 6 (je n'en change aucun). Chaque ligne a un proprietaire et un artefact.

| # | Action | Sort | Proprietaire, artefact |
|---|---|---|---|
| A1 | `DEC` d'ouverture de s56 | **faite** : `DEC-0116` (`Proposee`), sur `round-two` | `camille-po` |
| A2 | Mots de l'utilisatrice : `Q6` de `DEC-0115`, moment du paquet, ordre, deux instances | **partielle** : `Q6` et moment du paquet repondus ; `Q4` et `Q3` sans reponse | utilisatrice |
| A3 | Pousser, lire la CI sur un commit nomme, job par job | **hors sprint, second report compte** : seule l'utilisatrice lit la CI distante | utilisatrice ; verdict nomme |
| A4 | Cause du rouge de CI que `a3606b2f` corrige | **engagee**, hors `PAR` | `theo-archi`, avis de s56 ; commande ou message qui le reproduit |
| A5 | Un poste prealable de tout paquet a reproductions (`S55-ART-CP-10-PLAFOND`) et aucun poste sans ligne au planning | **engagee** : le poste a sa ligne (ligne 16) ; la regle des noms de branche, worktree et scratchpad est appliquee dans la section "Isolation" | `camille-po` (story), `theo-archi` (prix, forme), `lea-dev`, `sofia-sm` (ligne) |
| A6 | Tout `.md` sous `squad/` ou `docs/` passe par `mvn-jdk21.sh -B clean verify` avant commit | **engagee, pratique de `sofia-sm`**, appliquee a ce planning (jobs dans "Porte technique") | `sofia-sm` ; ligne "jobs lances" de ses rapports |
| A7 | Cinq ecarts de critere du rapport qualite | **faite pour quatre** (`DEC-0116` 8) ; le cinquieme est le planning de s55, traite a la cloture de s55 | `camille-po` ; `yanis-qa` relit (A8) |
| A8 | Rejouer `Q-Y8` sur `2224e91e` ; mutation d'A7 ; morsures de T2 | **engagee**, hors `PAR` ; non livre a la cloture de s56 = report compte | `yanis-qa`, `rapport-qualite.md` de s56 |
| A9 | Laquelle des lectures de la capacite de B (3,5 ou 4,0) vaut | **engagee**, ligne de `S56-ARB-B` | `nadia-prof`, `camille-po` |
| A10 | Verification de s54 (trois instances) contre les formes (a), (b), (c) | **non engagee a s56** (deux instances) ; due avant toute `DEC` proposant une troisieme ; pas un report | `nadia-prof` |
| A11 | En-tete de `FICHE-bandit-manchot-exclusion-cp.md` et titre du jeu | **engagee** avec `S55-EXC-R16`, sans prix propre | `nadia-prof` ; fiche, au rejeu |
| A12 | Ponctuation des 12 indices de `cm1-grands-nombres` | **engagee**, hors `PAR` | `relecteur-pedagogique` ; ligne dans `relecture-cloture.md` |
| A13 | Lever `R4`, `F2`, `F3` quand le fichier est touche | **engagee, conditionnelle** : citation de 2018 avec `S55-EMC-CM1-R9` ; commentaire de `manche.html` si `S56-ART-CP-10-ECHEC` le touche | `lea-dev` ; commits des rejeux |
| A14 | La formule de la forme 3 peut-elle etre gardee par un test ? | **engagee**, hors `PAR` : un avis ecrit | `theo-archi`, avis de s56 ; test ou refus motive |
| A15 | Mesure de pli de `LIS-2` en tete de s56 si le lot est voulu | **non engagee** (le lot n'est pas dans O3, `Q6`) ; pas un report ; a placer si s57 veut `cm1-lire-et-restituer` | `ines-design`, `camille-po` |

Les propositions d'amendement de `squad/README.md` de la section 6 de la retro de s55 sont a `sofia-sm`, `theo-archi` et a l'utilisatrice : **non decidees ici** (`DEC-0116` section 6, derniere ligne).

## Criteres d'acceptation

**Communs**, avec le resultat de chaque commande a `4cd44098` (2026-10-09, perimetre : le worktree entier, aucune mesure partielle) :

- **C1 - decision d'abord.** `DEC-0116` est sur `round-two` et nomme chaque poste : `git merge-base --is-ancestor 4cd44098 HEAD` sort 0. **Rempli.** Reste a remplir : les amendements de criteres (`-13`, `ECHEC`) avant l'ouverture de leurs worktrees.
- **C2 - un branchement est un diff borne.** `git diff round-two --stat` d'une story de branchement ne montre que l'entree de `catalogue.yaml`, le test du paquet, ses fixtures fautives, l'inventaire de chaines, les declarations des garde-fous ;
  aucun YAML de paquet sauf correction decidee par `nadia-prof`. Reference mesuree a s55 : 3 a 6 fichiers, 21 a 523 lignes. **Sans objet aujourd'hui : aucun commit de branchement.**
- **C3 - `grep -c "paquet: ce1-verbe-sujet$" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 1** apres livraison ; **rend 0 aujourd'hui**. Les autres paquets sans entree : **37 fichiers sans entree de catalogue** sur 182 (`comm -23` des fichiers de `contenu/`
  et des `paquet:` du catalogue, relance par moi) ; **36 a la cloture si `-13` est branche** : le paquet a reproductions ajoute un fichier et son entree dans le meme geste, donc ne change pas le compte.
- **C4 - suite verte, perimetre nomme.** Mesure partielle : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,bootstrap -am test` (`-am` obligatoire). Porte : `bash outils/mvn-jdk21.sh -B clean verify` de la racine.
- **C5 - aucun garde-fou assoupli.** Un cliquet ne monte jamais ; un garde-fou neuf compte au tableau de `theo-archi`.
- **C6 - jobs nommes** : chaque livraison nomme les jobs de `.github/workflows/ci.yml` qu'elle a lances (`prefixes`, `chemins`, `outils`, `build`) et ne deduit pas les autres.
- **C7 - un resultat "relu par le developpeur" a un fichier.** `ls squad/sprints/s56-*/rapport-marc-dev.md squad/sprints/s56-*/rapport-lea-dev.md` ne rend aucun fichier aujourd'hui (le dossier ne contient que ce planning) : **rouge**, doit en rendre deux a la cloture.
  Chaque rapport porte, par poste, une ligne "relu" ou "non relu" pour les regles que `nadia-prof` nomme dans ses arbitrages de s56 (`TRV-CE1-5` pour `-13`, les regles 1 a 6 de `ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md` section 3.4 pour le paquet).
- **C8 - chaque motif de refus ecrit mot pour mot par `nadia-prof` a un test qui le mord, ou une ligne "non garde : <motif>"** dans le rapport du dev ; `theo-archi` dit si la regle est du (A14 de la meme famille).
- **C9 - un branchement s'arrete au verdict.** `git diff round-two --stat -- squad/programme/MATRICE-couverture.md` est vide au branchement ; la matrice reste a **141/182** : commande de la "Methode du compte" de `MATRICE-couverture.md` relancee par moi, `couvert-valide` 141, `en cours` 4,
  `non couvert` 37, `non couvrable en l'etat` 76, total 258. **Le planning ne prononce aucun Done.** Si une ligne ferme : la mention de s50 pour `ART-CP-10` est reproduite mot pour mot (backlog, `S56-AUD-ART-CP-10`).

**Specifiques : renvoi au backlog "Sprint 56" (`docs/backlog/backlog.md`, a partir de la ligne 20802), valeurs d'aujourd'hui relancees par moi sur `4cd44098`.** Je ne recopie pas les criteres longs : la source de verite est le backlog.

| Story | Critere de la story (resume, source : backlog) | Valeur d'aujourd'hui (ma commande) | Cible |
|---|---|---|---|
| `S56-BR-FRA-CE1-13` | C3 ci-dessus ; un test de paquet | `grep -c "paquet: ce1-verbe-sujet$"` catalogue : **0** ; `git ls-files \| grep -c PaquetCe1VerbeSujetTest` : **0** ; `grep -c '^ *- enonce:'` du paquet : **24** | 1 ; au moins 1 ; 24 |
| `S55-ACV-CE1-R1` | verdict de rejeu date apres le 2026-10-08 | `grep -c 'ACV-CE1-R1.*LEVEE'` fiche : **0** ; `grep -c 'lequel est le bon'` du paquet : **1** (texte applique) | au moins 1 |
| `S55-ACV-CE1-R2` | au moins 8 des 12 items a sujet pluriel en -ent du premier groupe de `PRS-CE1-1` | **5 sur 12** (lecture des 24 `bonneReponse`, backlog) ; le comptage par suffixe rend 7 et sur-estime, il n'est pas un critere | au moins 8 |
| `S55-CLM-CE1-R1` | quatre anciens textes d'indice retires | commande du backlog : **24** ; `grep -c '^ *indice:'` : 24 | 0 ; 24 |
| `S55-EMC-CM1-R8` | deux textes remplaces | `Cherche le motif qui parle` : **2** (`cm1-emc-respect-differences.yaml`) | 0 ; les deux textes neufs 1 et 1 ; 11 items |
| `S55-EMC-CM1-R9` | un texte remplace, citation de 2018 (A13) | `se soutiennent les uns les autres` : **1** (`cm1-emc-principes-republique.yaml`) | 0 ; `dans un but humanitaire` au moins 1 |
| `S55-EXC-R16` | verdict de rejeu | `grep -c 'EXC-R16.*LEVEE'` fiche : **0** ; `grep -c "n'est pas une mise à l'écart\."` du paquet : **1** (texte applique) | au moins 1 |
| `S56-ARB-A`, `S56-ARB-B` | un fichier par instance, chaque poste y a un prix, les points de `Q2` repondus | `ls squad/conformite \| grep -c s56` : **0** | 1 par instance ; `grep -c` de chaque poste au moins 1 |
| `S56-ART-CP-10-OEUVRES` | document de `squad/conformite/` : pour chaque item, la reference et trois oeuvres, identifiants du vivier ou du registre ; ancetre du premier commit du paquet | aucun document : **rouge par absence** | document existant, `merge-base --is-ancestor` sort 0 |
| `S56-ART-CP-10-FORME` | `Q1` a `Q6` repondues dans un fichier de s56 ; `Q7` d'`ines-design` | `grep -oE 'Q[1-6]' cloture-nadia.md \| sort -u \| wc -l` rend 6 (les questions) ; fichier de reponses absent | meme commande sur le fichier de reponses : 6 |
| `S56-ART-CP-10-ECHEC` | marque ne dependant pas de l'image ; branche texte inchangee ; critere de la marque **a ecrire par amendement** | `grep -c 'line-through'` de `recreo.css` : **2** ; la regle `.deja-essayee img` ne declare que `opacity` | `line-through` : 2 apres ; la marque (amendement) |
| `S55-ART-CP-10-PLAFOND` | mutation de la ligne 1780 (`11rem` vers `14rem`) rend au moins un echec ; arbre non mute : 0 echec | ligne 1780 : `max-width: calc(11rem + 0.5rem + 0.25rem);` ; mutation rendait **0 echec sur 2307** (`DEC-0116` 0.3, je ne l'ai pas relancee) | au moins 1 echec sous mutation |
| `S55-ART-CP-10-PAQUET` | neuf criteres du backlog | `grep -lE '^ *bonneReponse: "oeuvre-'` sur `contenu/*.yaml` : **0 fichier** ; `ITEMS_A_PROPOSITIONS_EN_IMAGE = 0` ; **8** JPEG ; **8** `identifiant:` ; `REPRODUCTIONS_SERVIES = 8` ; `POIDS_TOTAL_MAXIMUM = 176_474` | 1 fichier ; cliquet monte du nombre exact d'items ; les trois nombres montent ensemble |
| `S56-AUD-ART-CP-10` | bloc de verdict de `nadia-prof` date de s56, apres l'entree de catalogue | aucun des deux commits n'existe | verdict ecrit, `git log -1 --format=%cs -- <fiche>` pas anterieur au commit d'entree |

Aucun critere ci-dessus n'est insatisfiable : chacun a une valeur d'aujourd'hui mesuree (ou une absence, nommee) et une cible atteignable par la story. Deux criteres dependent d'un amendement qui n'existe pas (`ECHEC`, conditions de `-13`) :
ils sont **conditionnes a leur amendement, pas rouges par oubli** ; sans lui le worktree ne s'ouvre pas. Je n'ai relance ni la mutation du plafond (2 min 35 s d'apres la `DEC`), ni les verbes de `ACV-CE1-R2`.

**Ce que `yanis-qa` doit dire**, avant la premiere livraison : lesquels de ces criteres un automate ne juge pas (mes lectures : le jugement en jouant de la marque, de la visibilite a 176 px, de "le sujet ne suffit jamais", `TRV-CE1-5`, le jugement de C8, la liste des verbes du premier groupe).

## Avis de faisabilite - theo-archi

**Aucun avis ecrit a la cloture de ce planning.** Je ne l'ai pas invoque. Le tableau dit ce qu'il doit rendre (`DEC-0116` section 9) ; la colonne de droite est ma lecture, pas un avis.
Je numerote `AV1` a `AV8` pour ne pas les confondre avec `T4` et `T5` d'`ADR-0104`.

| Code | Objet | Ma lecture (non un avis) |
|---|---|---|
| AV1 | Prix et forme du garde-fou du plafond de 176 px (`S55-ART-CP-10-PLAFOND`) | garde par rien aujourd'hui (mutation verte, `DEC-0116` 0.3) ; condition de l'entree de catalogue |
| AV2 | Question de corpus de la regle 2 de `nadia-prof` : des distracteurs qui partagent le sujet sans la propriete existent-ils en reproductions de provenance prouvee ? | le registre a 8 reproductions ; prealable de `S56-ART-CP-10-OEUVRES` |
| AV3 | Voie de la marque de l'echec (`Q1`), structure de `Q5`, `Q6` | prealable de `S56-ART-CP-10-ECHEC` ; `ines-design` propose, `nadia-prof` juge |
| AV4 | Valeurs de T4 apres le choix des oeuvres (l'avis de s54 ecrit 11 et 250 915 *"si les paires restent"*) | a remesurer, `REPRODUCTIONS_SERVIES` et `POIDS_TOTAL_MAXIMUM` montent ensemble |
| AV5 | Decoupage en commits du paquet (fichier, T4, registre) avec `lea-dev` | `DEC-0116` 3.2 : ni `camille-po` ni moi ne le decidons |
| AV6 | **A4** : cause du rouge de CI que `a3606b2f` corrige (le jar reproductible dont toutes les entrees portent le meme horodatage, `ProvenanceDesClassesAmontEstLeDepotTest`), avec la commande ou le message qui le reproduit | le message de commit dit le mecanisme ; aucun fichier de `squad/` n'en ecrit la cause (retro de s55, C1) |
| AV7 | **A14** : la formule de la forme 3 (*"recu de MASTER, sans source dans le depot"*) peut-elle etre gardee par un test ? test, ou refus motive | deux occurrences sans formule en trois sprints (`R2` de s53, `R1` de s55) |
| AV8 | `Q3` : deux instances de francais CE1 a partir de s57 n'amendent-elles pas `ADR-0065` en meme temps ? | demande d'avis, pas un engagement (`DEC-0116` 4) |

Si l'avis change un prix, un ordre ou une affectation, **je l'amende ici avant l'ouverture du worktree concerne**, et `review.md` le constate.

## Arbitrages prealables de nadia-prof, a venir

**Aucun n'est ecrit.** Ce que chacun doit repondre (`DEC-0116` section 9, `proposition-s56.md` `Q2`) :

- **`S56-ARB-A`** : tarifs de A ; **`PAR-2` sur le choix des oeuvres** (A ou B) ; **`PAR-13` sur `-13`** (cousin de `-12` selon la proposition ; elle confirme ou le remplace par `-15`) ; prix d'un rejeu groupe de `ACV-CE1-R1` et `ACV-CE1-R2` (moins de 0,75 ?) ; conditions de `ce1-verbe-sujet` (mots a elle, que `camille-po` ajoute au backlog avant le worktree de `marc-dev`).
- **`S56-ARB-B`** : tarifs de B, dont l'audit (2,5) et la forme (0,25) ; **A9** (la lecture de la capacite de B a s55, 3,5 ou 4,0) ; `PAR-2` sur les arts plastiques CP ; `EMC-CM1-R8`, `EMC-CM1-R9`, `EXC-R16` au tarif. **Si `Q-U1` s'etendait (B a 4,75), je le porte a l'utilisatrice avant l'ouverture du premier worktree du paquet.**

## Risques

| Code | Risque | Ce qui le contient |
|---|---|---|
| R1 | `DEC-0116` est `Proposee` (`Q4` sans reponse) | `DEC-0116` 0.5 : rien de s56 n'attend `Q4` ; un amendement la passe a `Acceptee` |
| R2 | **Le chemin critique est mono-ecrivain** : quatre postes sequentiels de `lea-dev`, precedes de cinq prealables de quatre agents (`ARB-A`, OEUVRES, avis, `Q1`, amendement d'`ECHEC`) | heures de la section "Ordre d'execution" ; dire le glissement le jour meme ; reprise des EMC par `marc-dev` |
| R3 | **L'audit glisse** (report compte, historique de quatre reports et deux engagements sans fermeture) | echeance du 2026-10-10 20:00 ; entree au tableau d'obstacles de `review.md` |
| R4 | **Prix inconnus** : aucun arbitrage de s56 n'existe ; `PAR-2` lue autrement porte B a 4,75 | `S56-ARB-A` et `-B` en tete ; question a l'utilisatrice avant le premier worktree du paquet |
| R5 | Deux entrees de catalogue et deux couloirs sur `catalogue.yaml`, l'inventaire de chaines et les garde-fous | "ajouts des deux cotes, jamais une assertion affaiblie" ; `verify` complet apres chaque fusion |
| R6 | Un rouge de CI sans cause ecrite revient (retro de s55, C1) ; la CI distante n'est pas lue (A3) | A4 dans l'avis ; je nomme les jobs que j'ai lances et ne deduis pas les autres |
| R7 | Les deux instances de `nadia-prof` ecrivent la meme ressource (forme (c), s50) | scratchpad et worktree dedies ; registre ecrit depuis le worktree de l'instance |
| R8 | `lea-dev` n'est pas l'auteure des paquets d'EMC (`R8`, `R9`) : elle lit le paquet et son test avant d'editer | cout nomme, comme `cm1-emc-atteinte-autrui` a s54 ; reprise par `marc-dev` si le paquet est en retard |
| R9 | Un critere non automatisable reste sans rapport ecrit (s54 : 2 sur 2 manques ; s55 : 2 sur 2 tenus) | C7 : les deux fichiers sont nommes ici |
| R10 | Longueur de chemin, 255 sur 260 | marge de 5, non verifiee sur un autre poste |

## Questions ouvertes

Ecrites comme ouvertes ; aucune n'est tranchee par ce planning. `Q-U1` et `Q-U2` sont closes par l'amendement A.1 et A.2 de `DEC-0116` ; je ne les rouvre pas.

| Code | Question | Route a | Artefact attendu |
|---|---|---|---|
| QS1 | `PAR-2` : qui porte le choix des oeuvres distractrices (A ou B) | `nadia-prof` | `S56-ARB-A`, `S56-ARB-B` |
| QS2 | `PAR-13` : `-13` confirme ou remplace par `-15` | `nadia-prof` | `S56-ARB-A` |
| QS3 | Un rejeu groupe de `ACV-CE1-R1` et `ACV-CE1-R2` coute-t-il moins de 0,75 | `nadia-prof` | `S56-ARB-A` |
| QS4 | Lecture de la capacite de B a s55 (A9) | `nadia-prof`, `camille-po` | `S56-ARB-B` |
| QS5 | Le critere de la marque de l'echec | `ines-design`, `theo-archi`, `nadia-prof`, `camille-po` | amendement du backlog |
| QS6 | `ADR-0065` et deux instances de francais CE1 (AV8) | `theo-archi` | avis |
| QS7 | Ordre des matieres pour s56 (`Q4` de la `DEC`) ; deux instances de francais CE1 a partir de s57 (`Q3` de la `DEC`) ; mesure de pli de `LIS-2` (`Q6` de la `DEC`) | utilisatrice | forme 2, avec sa date |
| QS8 | **Verdict de la CI sur un commit nomme, job par job** (A3, second report) | utilisatrice | verdict nomme |

## Porte technique

- **Juge : la CI, tous les jobs de `.github/workflows/ci.yml`** : `prefixes`, `chemins`, `outils`, `build`. `mvn verify` n'est que le contenu du job `build`. Chaque agent nomme les jobs qu'il a lances.
- **`yanis-qa`** : rapport qualite de cloture sur l'arbre fusionne, jobs nommes, commande exacte, total mesure, origine de l'ecart avec la base. **Je n'annonce aucun total.**
- **`theo-archi`** : confirmation ArchUnit a la cloture ; une regle `ArchUnit` dans le meme geste si `AV1` en appelle une.
- **Porte pedagogique** : **prononcee par `nadia-prof` seule**, apres avoir joue chaque paquet, avec un motif ecrit dans `squad/conformite/FICHE-<jeu>.md`. **Ce planning ne prononce aucun Done**, ni technique ni pedagogique.
- **Jobs lances pour ce planning** (A6, sur l'arbre avec ce fichier, avant commit) : **`build`** par `bash outils/mvn-jdk21.sh -B clean verify` de la racine, `BUILD SUCCESS`, 2307 tests, 0 echec, 0 erreur (120, 18, 407, 1423, 333 et 6 d'integration) ;
  **`outils`** : les juges purs de `outils/*-test.sh`, `squad/outils/*/*-test.sh` et `outils/*-test.py`, tous sortis 0 ; **`chemins`** : `verifier-chemins-test.sh` et `verifier-chemins.sh` sur `git ls-files` et sur ce fichier, sortis 0 ;
  **`prefixes`** : `verifier-prefixes-test.sh` sorti 0 (le verdict sur le sujet du commit est lu apres la creation du commit, hors de ce fichier). Aucun de ces resultats n'est celui de la CI distante, que personne n'a lue (`QS8`).
  Le total de 2307 est celui du reacteur complet sur cet arbre ; il egale la mesure de `DEC-0116` 0.3 (meme perimetre, ce planning n'ajoute aucun test).

## Ce que ce planning ne fait pas

- Il ne decide ni les tarifs, ni les plafonds, ni la lecture de `PAR-2` ou de `PAR-13`, ni l'ordre des matieres, ni le decoupage en commits du paquet, ni la forme du garde-fou, ni la voie de la marque : il les route.
- Il n'ecrit aucune `DEC`, aucun `ADR`, aucun avis a la place de `theo-archi`, aucun arbitrage a la place de `nadia-prof`, et n'amende ni `DEC-0116` ni aucune regle `PAR-*`.
- Il n'ouvre aucun worktree d'agent, ne fusionne rien et ne pousse rien.
- **Il ne modifie ni le backlog, ni la matrice, ni une fiche, ni le registre, ni `catalogue.yaml`.** La matrice reste a 141/182 ; aucune ligne ne change de statut.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee par ce planning.** `DEC-0116` (`Proposee`, amendee le 2026-10-09 par `camille-po`, A.1 et A.2) est la seule decision d'ouverture de s56 ; elle est citee, pas modifiee. Aucun obstacle n'est clos par ce planning :
l'obstacle "aucun avis, aucun arbitrage" reste ouvert tant que `avis-faisabilite.md` et les deux fichiers `ARBITRAGE-PREALABLE-s56-*` n'existent pas sur le disque. Sept decisions de planning, qui relevent de mon role :

1. **Decoupage en postes et affectations nominatives** : les seize lignes de `DEC-0116` 7 (dix-sept ici, marges separees), reprises sans ecart ; `marc-dev` (`-13`, `ACV-CE1-R2`, `CLM-CE1-R1`), `lea-dev` (paquet, plafond, echec, entree de catalogue, `R8`, `R9`), `nadia-prof` par instance.
2. **Une branche et un worktree par poste de developpement, un scratchpad par agent**, dedies par instance de `nadia-prof` ; deux postes de meme file seulement pour `s56-lea-emc` (justification dans "Isolation").
3. **Heures du paquet** : prealables 2026-10-10 08:00, paquet fusionne 2026-10-10 12:00, entree de catalogue 2026-10-10 20:00 ; estimations de Scrum Master, non mesurees, dites comme telles.
4. **Capacite de `marc-dev`** : si `S56-ARB-A` n'est pas ecrit quand il est libre, `S55-ACV-CE1-R2` passe avant `-13` ; l'attente va au tableau d'obstacles.
5. **Reprise des deux EMC** : par `marc-dev`, par amendement de ce fichier, si le paquet est en retard (`DEC-0116` 3.3).
6. **Numerotation `AV1` a `AV8`** pour les questions a `theo-archi`, parce que `T4` et `T5` designent deja deux taches d'`ADR-0104`.
7. **Fichiers de rapport nommes des le planning** (C7) : `rapport-marc-dev.md`, `rapport-lea-dev.md`.

## Prochaines etapes

Ce planning est fusionne sur `round-two` par l'agent qui m'a lance avant tout worktree. Ensuite, par agent :

- `theo-archi` : avis de faisabilite (`AV1` a `AV8`, dont A4 et A14).
- `nadia-prof` (A) : `S56-ARB-A`, puis `S56-ART-CP-10-OEUVRES` ; (B) : `S56-ARB-B`, puis `S56-ART-CP-10-FORME`.
- `ines-design` : `Q1`, puis `Q7`. `camille-po` : amendements d'`ECHEC` et de `-13` apres les mots de `nadia-prof`.
- `lea-dev`, `marc-dev` : edits dans l'ordre ci-dessus ; rapports nommes en C7.
- `yanis-qa` : lecture de testabilite, puis A8 ; `relecteur-pedagogique` : relecture de cloture (A12).
- `sofia-sm` : lignee a chaque fusion, echeances du 2026-10-10 a dire a l'utilisatrice le jour meme si elles sont depassees, `review.md` avec le tableau des obstacles, `RAPPORT.md`, `python outils/rapport-tokens.py --sprint s56`, `retro.md`.
- **Utilisatrice** : `Q4`, `Q3`, `Q6` de la `DEC` ; A3.

---

## Amendement du 2026-10-09 22:17 (UTC+02:00) (`sofia-sm`, action A5 : aucun poste sans ligne)

Le texte ci-dessus est inchange : il dit ce que le planning savait a 20:36. Cet amendement est ecrit avant l'ouverture du worktree du paquet et de celui de `S56-ART-CP-10-ECHEC`. Il ne decide ni produit, ni architecture, ni pedagogie,
ne prononce aucun Done, et ne touche ni le backlog, ni la matrice, ni une fiche, ni `catalogue.yaml`. Il n'ajoute aucune ligne d'heure de debut ou de fin de ceremonie : la seule que `outils/rapport-tokens.py` lit est celle de l'en-tete de ce fichier.

- **Heure de l'amendement** : 2026-10-09 22:17 (UTC+02:00), lue par `date`.
- **Lignee** : branche `s56-sofia-amend`, `git log -1 --format=%h` rend `c47582f8` (tip de `round-two`), `bash outils/verifier-lignee.sh` sort 0, `git status --short` vide avant ecriture.
- **Sources lues, par auteur** : camille-po (`DEC-0116` A.4 a A.8, backlog "Sprint 56" et ses annotations) ; theo-archi (`avis-faisabilite.md`, sections 1 a 11 et amendement de 21:55, A.1 a A.4) ;
  nadia-prof (`ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md`, `ARBITRAGE-PREALABLE-s56-instance-b-art-cp-10-et-emc-cm1.md` bloc de 21:37 R.1 a R.3, `CHOIX-OEUVRES-s56-art-cp-10.md` bloc de 22:07 sur `K1`) ; sofia-sm (`git worktree list`).
- **Ce que ce planning avait suppose et qui est arrive** : l'avis, `S56-ARB-A`, `S56-ARB-B`, `S56-ART-CP-10-OEUVRES` et `S56-ART-CP-10-FORME` existent maintenant sur `round-two` (`ls squad/conformite | grep -c s56` rend 5 entrees a `c47582f8` : quatre fichiers et le dossier `releves-s56/` ;
  l'avis est dans le dossier du sprint). Je ne reconcilie pas le planning avec eux ici : `review.md` le constatera.

### A. Reponse de l'utilisatrice du 2026-10-09 : "Oui, a 4,75"

Mots cites par `DEC-0116` A.4 (forme 2, recus par l'agent lanceur qui les atteste ; **je ne les ai pas lus de sa main**, meme reserve que `camille-po`). Effet, tel que la decision l'ecrit :

- **B est a 4,75** (plafond dur de 5,0 non atteint) ; l'ecart de 0,5 vaut quelle que soit la repartition entre les instances.
- **`PAR-2` : `S56-ART-CP-10-OEUVRES` est sur l'instance B** (arbitrage A 3.1, arbitrage B 2.1). Question `QS1` close. Le risque R4 de ce planning (B a 4,75 si `PAR-2` est lue autrement) est realise et couvert par l'ecart.
- **Remplace dans ce fichier** : ligne 6 du tableau "Stories retenues" (instance B, et non "A sous `PAR-2`") ; dans "Isolation", `S56-ART-CP-10-OEUVRES` passe de la ligne de `nadia-prof` A a celle de `nadia-prof` B (`s56-nadia-b`, scratchpad dedie B) ;
  dans "Ordre d'execution", le document des oeuvres precede la premiere ligne du paquet comme avant, ecrit par l'instance B. Il est ecrit : `CHOIX-OEUVRES-s56-art-cp-10.md`.

### B. Capacite : 8,25 (A 3,5, B 4,75), 0,25 sans poste

Source : reconciliation de `nadia-prof`, instance B (bloc du 21:37, R.3), qui prend le rejeu groupe de `ACV-CE1-R1` et `R2` a 0,5 de l'arbitrage de A (section 4). **Je recompte sans reprendre le total** :

| Poste | A | B |
|---|---|---|
| Arbitrage prealable | 0,5 | 0,5 |
| Lot `ce1-verbe-sujet` | 1,0 | - |
| Rejeu groupe `ACV-CE1-R1` et `ACV-CE1-R2` | 0,5 | - |
| `CLM-CE1-R1` (`PAR-14`) | 1,0 | - |
| `EMC-CM1-R8`, `EMC-CM1-R9`, `EXC-R16` | - | 0,75 |
| Arbitrage de forme | - | 0,25 |
| Choix des oeuvres | 0,0 | 0,25 |
| Audit du paquet | - | 2,5 |
| Marge de rejeu, allouee a aucun lot | 0,5 | 0,5 |
| **Total** | **3,5** | **4,75** |

**Somme : 8,25 sur la borne de 8,0.** L'ecart accorde de 0,5 permet 8,5 : **les 0,25 entre 8,25 et 8,5 ne sont alloues a aucun poste, et ne le sont pas sans amendement** (`DEC-0116` 2.4). Remplace le tableau "Capacite" de ce fichier (A 4,0, B 4,5, somme 8,5), et ses lignes 3 et 4
du tableau "Stories retenues" (0,25 + 0,5) lues ensemble a 0,5. Si `S55-ACV-CE1-R2` n'est pas livree, A rejoue `ACV-CE1-R1` seule a 0,25 : sa consommation descend, son engagement reste 3,5. Lots : A en porte deux, B un, sous le maximum de trois.
**Statut de la ligne** : `DEC-0116` A.5 la dit en attente de cette reconciliation ; `camille-po` l'amende (en cours). **Je porte 8,25 a la review sur la source de `nadia-prof`, et le dis si l'amendement de la decision dit autre chose.**
Les prix restent ceux de `nadia-prof`. `A9` : les deux instances ecrivent 3,5 consommes pour B a s55 (`DEC-0116` A.5) ; ligne close.

### C. Deux postes neufs hors `PAR` pour `theo-archi`

Ils naissent de l'amendement de l'avis du 21:55 (A.1, A.3, A.4) et du choix de `nadia-prof` du 22:07 sur `K1`. Hors `PAR` : **0,0 pour `nadia-prof`**, environ 0,25 jour chacun pour `theo-archi` (son estimation, pas un tarif de `nadia-prof`).
**Story au backlog : aucune a ce jour** (`grep -rn "S56-ADR-0026\|S56-ADR-0024" docs squad` ne rend rien avant cet amendement) ; la story est a `camille-po`, et le poste a sa ligne ici, avant son worktree.

| Poste | Story | Objet | Agent | Branche | Worktree | Scratchpad |
|---|---|---|---|---|---|---|
| Amender `ADR-0026` clause 3 | `S56-ADR-0026` | la clause 3 (`LAnimationRetardeMaisNeDetermineRienTest.aucuneRegleMaisonNeRendUneActionInactionnable`) rougit sur une regle dont le sujet est `::before` ; l'amendement et son test dans un seul commit (avis, A.1) | `theo-archi` | `s56-theo-adr0026` | `../recreo-s56-theo-adr0026`, **deja ouvert** (`git worktree list`) | `s56-theo` |
| Amender `ADR-0024` regle 1 | `S56-ADR-0024` | `K1` : le controle de la regle 1 n'entre que sur `graphie` ; un paquet sans graphie qui porte `explication` la porte sur tous ses items ou sur aucun. **Issue (b), choisie par `nadia-prof` seule** (`CHOIX-OEUVRES-s56-art-cp-10.md`, 22:07) ; amendement et test dans un seul commit (avis, A.3) | `theo-archi` | `s56-theo-adr0024` | `../recreo-s56-theo-adr0024`, **a ouvrir** par l'agent qui me lance (aucun worktree de ce nom aujourd'hui) | `s56-theo` |

Longueur du nom de worktree `recreo-s56-theo-adr0024` : 23 caracteres, sous les 26 du plus long de la section "Isolation" (marge de 5 sur 260 inchangee).
**Ordre de fusion** : les deux amendements d'`ADR`, avec leur test, sont fusionnes sur `round-two` **avant l'ouverture du worktree du paquet (`s56-lea-paquet`) et de celui de `S56-ART-CP-10-ECHEC` (`s56-lea-echec`)**. C'est la consigne du lanceur ;
l'avis est moins strict (`ADR-0024` bloque le premier commit du paquet, `ADR-0026` doit preceder `ECHEC`, avis A.4), et je prends la lecture la plus stricte : un seul point de controle. Aucune des deux ne change le prix ni l'heure du paquet de ce planning, sauf si l'une d'elles n'est pas fusionnee a 2026-10-10 08:00 :
elle rejoint alors la liste des prerequis manquants que je dis a l'utilisatrice le jour meme. **`ADR-0024` et `ADR-0026` sont amendes par `theo-archi`, pas par moi** ; aucun `ADR` n'est cree par cet amendement de planning, et `ADR-index.md` n'est pas touche ici.

### D. `lea-dev` : branches et ordre inchanges, prerequis du premier commit du paquet precises

Branches, worktrees et ordre de la section "Isolation" : **inchanges** (`s56-lea-paquet`, `-plafond`, `-echec`, `-catalogue`, `-emc`). Prerequis du commit de `S55-ART-CP-10-PAQUET`, d'apres l'avis (A.3, simulation de 6 echecs sur 2307) :

- **`K1`** : `S56-ADR-0024` fusionne (ci-dessus). Sans lui, le premier commit rougit.
- **`K2`** : une entree de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` avec son motif, comme `cp-decrire-une-oeuvre` (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`). Un ajout, pas un assouplissement.
- **`K3`** : les deux morsures de `LIllustrationDeContenuResoutEtAucuneNeDortTest` (`unIdentifiantDOeuvreResoutDesQueLeFichierDeReproductionExiste`, `uneOeuvreServieSeulementEnPropositionNEstPasDormante`) recoivent les propositions reelles du depot. Retouche du meme commit.
- **Les quatre constantes**, qui montent ensemble dans le meme commit (backlog, critere de `S55-ART-CP-10-PAQUET`) : `ITEMS_A_PROPOSITIONS_EN_IMAGE`, `ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION`, `REPRODUCTIONS_SERVIES`, `POIDS_TOTAL_MAXIMUM`. Valeurs : celles que l'avis chiffre pour six items et quatre reproductions restaurees ; **`lea-dev` les relit au `verify`** (le cliquet exact juge), je n'en recopie aucune.
- `K2` et `K3` sont des retouches du commit du paquet, pas des postes ; l'avis le dit, et leurs causes sont attribuees par message d'echec (a confirmer au `verify`).
- **Les prerequis de la section "Ordre d'execution" sont tenus** : `S56-ARB-A`, `S56-ARB-B`, avis, `S56-ART-CP-10-OEUVRES` existent sur `round-two`. **Restent** : les deux amendements d'`ADR` (C), et pour `s56-lea-echec` l'amendement du critere de la marque (`camille-po`, en cours, F).

### E. `marc-dev` : worktree ouvert

`marc-dev` a ouvert **`s56-marc-fra-ce1-13`**, worktree `../recreo-s56-marc-fra-ce1-13`, pour `S56-BR-FRA-CE1-13` (`git worktree list` le confirme ; sa base est la fusion de `s56-camille-suivi`, `806aeb46`).
La dependance de ce planning (conditions de `-13` ecrites par `nadia-prof` et criteres amendes par `camille-po`) est tenue : `S56-ARB-A` porte `J1` a `J5`, et `DEC-0116` A.6 dit que le worktree peut s'ouvrir. **La disposition conditionnelle (`S55-ACV-CE1-R2` avant `-13`) n'est pas utilisee.**
Ses deux autres branches (`s56-marc-acv-ce1-r2`, `s56-marc-clm-ce1-r1`) restent a ouvrir, dans l'ordre, une fois celle-ci rapportee.

### F. Criteres de la marque d'echec et du plafond : en cours chez `camille-po`

`camille-po` amende, en ce moment, la story de la marque de `S56-ART-CP-10-ECHEC` et celle du plafond (`S55-ART-CP-10-PLAFOND`, forme d'`ines-design` selon l'avis A.2). **Je ne les reproduis pas** : la source est le backlog "Sprint 56", annotations du jour. Cet amendement n'ajoute aucun critere.
Condition inchangee : le worktree de `S56-ART-CP-10-ECHEC` ne s'ouvre pas avant cet amendement **et** la fusion de `S56-ADR-0026`. `PLAFOND` precede `ECHEC` (`DEC-0116` A.6).

### G. Verification poste/story, mise a jour

Aux dix-sept lignes de la section "Stories retenues" s'ajoutent deux lignes, **dix-neuf au total** :

| # | Poste | Story | Titre au backlog | Instance | Agent nominatif | Prix | Verdict |
|---|---|---|---|---|---|---|---|
| 18 | Amendement d'`ADR-0026` | `S56-ADR-0026` | **0 aujourd'hui** | hors `PAR` | `theo-archi` | 0,0 pour `nadia-prof` | ligne au planning ; story a ecrire par `camille-po` |
| 19 | Amendement d'`ADR-0024` | `S56-ADR-0024` | **0 aujourd'hui** | hors `PAR` | `theo-archi` | 0,0 pour `nadia-prof` | idem |

**Ecart signale, non solde** : `PAR-16` demande une story par poste a l'ouverture ; ces deux postes sont nes de l'avis apres l'ouverture, et leur story manque. Elle est a `camille-po` (le backlog est a elle). Ce planning nomme et affecte, il ne l'ecrit pas a sa place. Lignes 3, 4 et 6 : voir A et B.

### H. Ce que cet amendement ne fait pas, decisions prises, jobs lances

- **Decisions prises** : aucune `ADR` ni `DEC` creee ou amendee par cet amendement. Quatre decisions de planning, de mon role : (1) les deux postes d'`ADR` ont leur ligne, leur branche et leur worktree ; (2) ils precedent le worktree du paquet **et** celui d'`ECHEC` (lecture la plus stricte) ;
  (3) la capacite est portee a 8,25 (A 3,5, B 4,75) sur la reconciliation de `nadia-prof`, sous reserve de l'amendement de `camille-po` ; (4) la disposition conditionnelle de `marc-dev` n'est pas declenchee.
  Aucune decision n'est close par cet amendement : l'obstacle "story absente des deux postes d'`ADR`" reste ouvert tant que `camille-po` ne l'a pas ecrite, et "ADR-0024 et ADR-0026 non amendes" tant que leurs commits ne sont pas fusionnes.
- **Il ne fait pas** : fixer un prix, ecrire un critere, amender `ADR-0024` ou `ADR-0026`, ouvrir un worktree, fusionner, pousser.
- **Jobs lances pour cet amendement** (A6, sur l'arbre avec ce texte, avant commit) : **`build`** par `bash outils/mvn-jdk21.sh -B clean verify` de la racine, `BUILD SUCCESS`, 2307 tests, 0 echec, 0 erreur (120, 18, 407, 1423, 333 et 6 d'integration), meme total qu'au planning ;
  **`outils`** : les juges purs de `outils/*-test.sh`, `squad/outils/*/*-test.sh` et `outils/*-test.py`, tous sortis 0 ; **`chemins`** : `verifier-chemins-test.sh` et `verifier-chemins.sh` sur `git ls-files`, sortis 0 ; **`prefixes`** : `verifier-prefixes-test.sh` sorti 0.
  Aucun de ces resultats n'est celui de la CI distante, que personne n'a lue (`QS8`).
