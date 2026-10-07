# Propositions de marc-dev - S51-SCI-CORPS-CM1-RESERVES (`CRP-CM1-R1` a `R3`)

Paquet : `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-corps.yaml`, jeu servi (`bandit-manchot-corps-cm1`).
Base de mesure : commit `dc2870eb` (`round-two`), worktree `s54-marc-branchement`.

## Statut

**Aucun YAML n'est edite par ce commit.** `squad/conformite/ARBITRAGE-PREALABLE-s54-instance-b-sciences-emc-cm1.md` n'existe pas
sur cette branche (`ls squad/conformite | grep s54-instance-b` : vide). Les textes ci-dessous sont des **propositions**
de `marc-dev` ; le texte exact des items 3, 5, 6, 7 et 11 est a `nadia-prof` (instance B). S'il differe, l'arbitrage prevaut et
ces propositions sont caduques.

Constat commun, `grep -c '^ *- enonce:' cm1-sciences-corps.yaml` : **12**. Aucune proposition ne retire ni n'ajoute d'item ni de
distracteur (`PaquetCm1SciencesCorpsTest` : 12 items, deux distracteurs chacun ; motif de `LeRang...` : 12 items, 36 propositions).

Contrainte de forme de `sci1ChaqueIndice...` : une seule phrase, terminee par un point, sans la bonne reponse, sans "ta reponse".

## Item 3 - `CRP-CM1-R1` (indice faux)

- **Constat** : `grep -n "ne fait rien d'autre" cm1-sciences-corps.yaml` rend la ligne 77. Le corps ne s'arrete pas pendant le sommeil.
- **Issue proposee** : reecrire l'indice. Enonce, bonne reponse et distracteurs inchanges.
- **YAML propose** :
  `indice: "Pendant le repos de la nuit, le coeur bat et la respiration continue : le corps ne s'arrête jamais."`
- **Motif** : vrai, accord avec `cm1-sciences-nutrition` item 3 ("bat sans arret, jour et nuit"), une phrase, ne nomme pas
  "assez de sommeil". Il repond au distracteur "rester eveille" en corrigeant l'idee d'un corps a l'arret. Point ouvert pour
  `nadia-prof` : il ne repond pas explicitement a "boire des boissons qui empechent de dormir".

## Item 5 - `CRP-CM1-R1` (indice inexact)

- **Constat** : `grep -n "n'utilise pas en grande quantité" cm1-sciences-corps.yaml` rend la ligne 85. Le corps utilise beaucoup de sucres.
- **Issue proposee** : reecrire l'indice. Reste de l'item inchange.
- **YAML propose** :
  `indice: "Le corps tire déjà le sucre dont il a besoin des féculents, et les boissons sucrées en ajoutent trop."`
- **Motif** : vrai (la fiche, section 8 : sucres tires surtout des feculents, l'exces vient du sucre ajoute), une phrase, ne nomme
  pas "l'eau", dit pourquoi "les jus de fruits sucres" et "les sodas" sont faux. "Feculents" est un mot de CM1 deja employe par la fiche ;
  a confirmer par `nadia-prof`.

## Item 6 - `CRP-CM1-R2` (portee du geste)

- **Constat** : `grep -n "évite de lui transmettre" cm1-sciences-corps.yaml` rend la ligne 86. "Evite" est trop large, "lui" est ambigu.
- **Issue proposee** : reecrire la question (et le commentaire `HIS-3` de la ligne, qui dit aussi "evite"). Bonne reponse,
  distracteurs et indice inchanges.
- **YAML propose** :
  `enonce: "Un enfant malade tousse près d'une autre personne. Quel geste limite la transmission de ses microbes à cette personne ?"`
  (espace insecable avant le "?" final, `TYP-1`, `ADR-0067`) ; dans le commentaire : "tousser dans le pli du coude limite le depot de
  microbes sur les mains puis sur les objets et les personnes".
- **Motif** : "limite" reprend l'item 4 du meme paquet ; l'autre personne est nommee ("cette personne"). La bonne reponse
  "tousser dans son coude" reste exacte et n'est pas allongee : la portee est portee par la question.

## Item 7 - `CRP-CM1-R3` (distracteurs implausibles)

- **Constat** : `grep -n "remplacer le sommeil\|se passer de manger" cm1-sciences-corps.yaml` rend la ligne 92. Aucun enfant de 9 ans ne les croit.
- **Issue proposee** : remplacer les deux distracteurs et l'indice. Enonce et bonne reponse inchanges.
- **YAML propose** :
  `distracteurs: ["à faire travailler seulement les jambes", "à user les articulations"]`
  `indice: "Les articulations sont faites pour bouger, et les muscles de tout le corps y participent."`
- **Motif** : deux idees plausibles et fausses sur ce que fait l'activite physique ; l'indice repond a chacune (tout le corps ;
  articulations faites pour bouger). Reste dans `EPS-9` : aucun effet a long terme ni effet immediat de l'effort. Points ouverts pour
  `nadia-prof` : "user les articulations" est une croyance courante, a juger pour la portee ; l'indice ressemble a celui de l'item 2
  ("servent a bouger") ; le distracteur "a durcir les os" de l'item 12 n'est pas repris.

## Item 11 - `CRP-CM1-R2` (role exact des fibres)

- **Constat** : `grep -n "aider le ventre à digérer" cm1-sciences-corps.yaml` rend la ligne 107. Les fibres ne sont pas digerees ;
  "ventre" est un mot du CP.
- **Issue proposee** : reecrire la bonne reponse. Enonce, distracteurs et indice inchanges (l'indice dit deja "presque pas absorbees").
- **YAML propose** :
  `bonneReponse: "à aider l'intestin à bien fonctionner"`
- **Motif** : role exact (transit), sans dire que les fibres sont digerees, et nomme l'organe comme `cm1-sciences-nutrition`.
  Le commentaire `HIS-3` de la ligne ("favorisent le transit intestinal") est deja conforme.

## Ce qui n'est pas verifiable par un automate

L'exactitude des cinq textes, la portee de l'item 6 et la plausibilite des distracteurs de l'item 7 relevent de `nadia-prof`
(fiche, section 9). `marc-dev` ne prononce aucun Done.
