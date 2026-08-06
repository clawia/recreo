# DEC-0009 - Retrait de "Le bon son" du catalogue jusqu'a sa correction

- **Type** : perimetre produit, retrait temporaire d'un jeu du catalogue
- **Date** : 2026-08-06
- **Auteur** : camille-po
- **Sprint** : s04 (cloture) / s05 (ouverture)
- **Competence programme visee** : francais, CP (cycle 2), domaine "Lecture et comprehension de
  l'ecrit" - "Associer les graphemes aux phonemes (voyelles, consonnes simples, puis digraphes
  ch, ou, on...)" (`squad/programme/francais/competences-cp.md`).
- **Statut** : Acceptee

## Contexte

`nadia-prof` a prononce **REFUSE** sur `bandit-manchot-le-bon-son-cp`
(`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`, audit du 2026-08-06), sur cinq motifs
cumules :

1. La consigne annonce un son que l'application ne joue jamais.
2. Les propositions de reponse affichent des crochets phonetiques (`[an]`) que le CP n'a jamais
   appris a lire.
3. 23 items sur 24 se reussissent par simple appariement visuel des lettres, sans jamais passer
   par le phoneme que la competence vise ; le seul item qui l'exige contredit ce que l'enfant
   vient d'apprendre sur la graphie du son.
4. Trois items admettent deux reponses vraies mais n'en acceptent qu'une.
5. Aucune aide n'existe dans ce jeu, pour une raison structurelle : `QuestionContentSourceFactory`
   construit chaque `Question` sans champ `indice`, donc un paquet `type: question` ne peut en
   porter aucun quel que soit ce qu'un auteur ecrit dans le YAML.

Ce jeu est neanmoins dans `adapters/adapter-content/src/main/resources/catalogue.yaml` et jouable
par un enfant qui lance l'application aujourd'hui, exactement dans l'etat que `nadia-prof` a
refuse.

## Decision

**"Le bon son" est retire du catalogue jouable jusqu'a ce qu'un increment corrige repasse devant
`nadia-prof` et obtienne un Done pedagogique.** Execution en story S5-1 de
`docs/backlog/backlog.md`.

## Justification

Le principe du double Done (`CLAUDE.md`) dit qu'un increment techniquement irreprochable peut
etre refuse pedagogiquement, et que les deux sont independants. Un increment refuse qui reste
accessible a un enfant rend cette independance sans consequence pratique : la porte pedagogique
ne protege plus rien si le contenu qu'elle bloque continue d'etre joue pendant sa correction.

Trois motifs concrets, pas seulement le principe :

- Les motifs 1 et 3 disent qu'un enfant qui joue aujourd'hui recoit une consigne fausse et reussit
  23 manches sur 24 sans jamais passer par la competence que le jeu pretend travailler : ce n'est
  pas une imperfection de detail, c'est un jeu qui n'enseigne pas ce qu'il annonce enseigner.
- Le motif 4 (trois items a deux reponses vraies) peut apprendre activement a un enfant a se
  mefier d'une perception auditive qui etait pourtant correcte.
- Le motif 5 (aucune aide) laisse un enfant en echec repete sans aucun recours dans ce jeu
  precis, alors que le meme dispositif existe et fonctionne dans `bandit-manchot-additions-cp`
  depuis s03.

## Ce que cette decision ne fait pas

- Elle ne supprime ni le paquet de contenu (`contenu/cp-le-bon-son.yaml`) ni la fiche de
  conformite ni l'historique du refus : ils restent la matiere de travail des stories de
  correction (S5-2 a S5-5, `docs/backlog/backlog.md`).
- Elle ne prejuge pas du verdict que `nadia-prof` rendra sur l'increment corrige.
- Elle ne tranche aucune question technique sur la maniere de retirer le jeu du catalogue
  (suppression de la ligne, drapeau de desactivation, autre mecanisme) : ce choix appartient a
  `theo-archi` et aux developpeurs (voir la story S5-1, qui decrit le comportement attendu sans
  prescrire l'implementation).
- Elle ne change pas le statut de la ligne de matrice associee : celui-ci suit le verdict
  pedagogique de `nadia-prof`, pas la presence ou l'absence du jeu au catalogue. La ligne
  `francais | CP | Lecture et comprehension de l'ecrit | Associer les graphemes aux phonemes`
  reste `en cours`, comme elle l'etait avant cette decision.
- Elle ne retire aucun autre jeu ni ne touche a la mecanique bandit manchot : les deux autres jeux
  du catalogue continuent de fonctionner sans changement.

## Impact

- `docs/backlog/backlog.md`, nouvelle section "Sprint 5" : story S5-1 (retrait du catalogue),
  S5-2 a S5-5 (corrections de contenu et constat structurel a router vers `theo-archi`).
- `squad/programme/MATRICE-couverture.md` : note de la ligne francais CP mise a jour pour
  refleter le refus et ce retrait.
- `squad/validation/FILE-ATTENTE.md` : `bandit-manchot-le-bon-son-cp` entre dans la file comme
  increment refuse en attente de reprise.
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0009 ajoutee.

## Ce qui reste non testable par construction

- Que le retrait effectif du catalogue soit la bonne reponse produit plutot que, par exemple, une
  correction accéléree livrée avant tout retrait, est un jugement d'opportunite de ma part : je le
  retiens parce que le refus porte sur des motifs qui touchent directement ce que l'enfant apprend
  (motifs 1, 3, 4) et non sur des reserves de confort, et qu'aucune correction n'est encore livree
  a la date de cette decision.
- Que l'increment corrige leve effectivement les cinq motifs releve du seul jugement de
  `nadia-prof` a son prochain audit ; cette decision ne prejuge pas de ce verdict.
