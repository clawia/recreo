# s57 - palier 5 : reponse de nadia-prof a Q7 de theo-archi (perimetre de la garde d'`AIM-CE1-2`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-26, 09:18 (UTC+01:00), lu par `date`.
- **Arbre** : branche `s57-p5-nadia-q7`, base `round-two` a `9bd5778b` (lue par `git rev-parse`).
- **Repond a** : `avis-theo-palier-5-exemption-aim-ce1-2.md`, section 6, `Q7`.
- **Type** : reponse a une question routee. **Aucun Done**, aucune condition neuve, aucune condition reecrite, **aucun
  identifiant neuf**. Ni code, ni paquet, ni `DEC`, ni matrice, ni backlog, ni registre. La forme de la garde reste a
  `theo-archi`.
- **Je n'ai rien joue** : le paquet `ce1-qlm-aimant` n'existe pas.
- **Lu** : l'avis de `theo-archi` ; ma `reponse-nadia-aim-ce1-2-q1-q2.md` ; `QuestionContentSourceFactory.java`
  (lignes 99-127) ; `ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md` (1.3, 2, 3.6) ;
  `ARBITRAGE-PREALABLE-s48-francais-ce1.md` (`TRV-CE1-1` et son annotation du 2026-10-25) ; `DEC-0123` section 5 ;
  `CLAUDE.md`, section *"L'enfant de CP n'est jamais seul"* ; story `S57-BR-QLM-CE1-06`, `QC4` et `QC6`
  (`docs/backlog/backlog.md`) ; `PaquetCe1EmcInteretCommunTest.java` (lignes 667-673).

## Reponse

**Non. `texteAVoixHaute` et `texteSupport` n'entrent pas dans la condition d'`AIM-CE1-2`**, parce que sur
`QLM-CE1-06` ils ne doivent pas exister du tout, et qu'une regle deja ecrite et deja demandee en test les refuse par
leur seule presence. Lire le contenu d'un champ qui doit etre absent ne garderait rien.

## 1. Ce qui s'applique au CE1

1. **`TRV-CE1-1`** (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, lignes 173-178) : *"Aucun item ne porte de champ a voix
   haute"*. Mon annotation du 2026-10-25 (meme fichier, lignes 180-184) y admet **deux exceptions et deux seulement** :
   `FRA-CE1-27` et `FRA-CE1-29`. `QLM-CE1-06` n'en est pas une.
2. **`DEC-0123` section 5** limite l'extension de la premisse de l'adulte a ces deux lignes nommees, et ecrit qu'une
   troisieme ligne demande une nouvelle reponse de l'utilisatrice. Aucune n'a ete donnee pour questionner le monde.
3. **Mon instruction de questionner le monde CE1** reprend `TRV-CE1-1` sur les dix-sept lignes, *"aucun champ a voix
   haute ; aucun `texteSupport` sur ces lignes"* (section 2), et section 1.3 : *"aucune des dix-sept lignes ne demande une
   voix, et aucun item n'en portera"*.
4. **`QC6`** (`docs/backlog/backlog.md`, ligne 24069) demande deja au test de chaque paquet (`QC4`, ligne 24066) : *"un item portant un champ
   `texteSupport` est refuse ; un item portant un champ `texteAVoixHaute` est refuse"*. Le precedent de forme existe :
   `PaquetCe1EmcInteretCommunTest.java`, lignes 667-673, asserte l'ensemble exact des cles servies.

**Correction d'un constat a moi.** La section 1.3 de mon instruction (2026-10-13) ecrit que la premisse *"reste
arbitree pour le CP seul"*. C'etait exact a sa date ; depuis le 2026-10-21 elle vaut aussi pour `FRA-CE1-27` et
`FRA-CE1-29`. Sa conclusion sur questionner le monde ne change pas : aucun champ a voix haute.

## 2. Motif

- **La presence est refusee, le contenu n'a pas a etre lu.** Une garde de contenu sur ces deux champs, appliquee a un
  paquet ou `QC6` les refuse, ne lirait jamais rien : verte sur tout materiau reel, elle donnerait l'apparence d'une
  couverture sans en fournir. La garde utile est celle de `QC6`, plus forte : *"l'aimant attire les metaux"* ne peut
  pas etre lue a l'enfant par ce canal si le canal est interdit.
- **Je n'elargis pas ma condition.** `AIM-CE1-2` nomme enonce, indice et explication ; `Q2` y a ajoute la bonne reponse
  par l'exactitude et `AIM-CE1-1`, sans la reecrire. L'etendre ici n'apporterait rien et deplacerait un poteau.
- **Principe, pour le jour ou la question changerait de nature.** Ce que l'adulte lit a voix haute, l'enfant
  l'entend : c'est lui dit, comme un enonce. Si une nouvelle reponse de l'utilisatrice ouvrait un champ a voix haute
  sur une ligne de questionner le monde, la question `Q7` se reposerait, et je la trancherais alors. Ce n'est pas une
  condition ecrite aujourd'hui.

## 3. Les champs, un par un (cles lues par `QuestionContentSourceFactory.java`, lignes 102-116)

| Cle | Dans la condition d'`AIM-CE1-2` | Qui garde |
|---|---|---|
| `enonce` | **oui** (texte de la condition) | garde de `theo-archi`, section 1 |
| `indice` | **oui** (texte de la condition) | garde de `theo-archi`, section 1 |
| `explication` | **oui** (texte de la condition), si le paquet en porte | garde de `theo-archi`, section 1 |
| `bonneReponse` | **oui, en paire avec l'`enonce`**, par l'exactitude et `AIM-CE1-1` (`Q2`), pas par une condition neuve | garde de `theo-archi`, section 1 |
| `distracteurs` | non | moi seule, item par item, a la lecture avant `verify` (`QC9`) |
| `texteAVoixHaute` | non : **sa presence est refusee** (`TRV-CE1-1`) | test du paquet (`QC4`), sonde de `QC6` |
| `texteSupport` | non : **sa presence est refusee** (`TRV-CE1-1`, instruction section 2) | test du paquet (`QC4`), sonde de `QC6` |
| `partDesignee`, `illustration`, `illustrationTexteAlternatif`, `tableauSupport` | hors de `Q7`, non tranches ici | tout texte que l'enfant lit ou entend reste sous ma lecture (`QC9`) |

## 4. Ce que cela demande, et a qui

| Qui | Quoi |
|---|---|
| `theo-archi` | rien de neuf : la garde de la section 1 de son avis lit les champs marques **oui** ci-dessus, et seulement eux |
| developpeur de `S57-BR-QLM-CE1-06` | les deux sondes de `TRV-CE1-1` deja ecrites en `QC6` (ligne 24069), dans `PaquetCe1QlmAimantTest`. **Sans elles, la reponse de cette page ne tient pas** : l'exclusion des deux champs de la garde d'`AIM-CE1-2` repose sur leur refus par presence |
| `camille-po` | au critere 2 de `-06` (`docs/backlog/backlog.md`, ligne 24169), remplacer *"`Q7` [...] est ouverte, a `nadia-prof`"* par un renvoi a ce fichier |

**Ce que je garde seule, inchange** : les distracteurs et les quatre cas de ma section 3 de
`reponse-nadia-aim-ce1-2-q1-q2.md` (paraphrases sans *attirer*, metaux nommes presentes comme attires, designation hors
des quatre formes, negation fausse sans designation).

**Correction du 2026-10-26, 09:28 (UTC+01:00)** : les sondes de `TRV-CE1-1` (ligne 24069) sont dans `QC6`, pas dans `QC4` (ligne 24066, le test du paquet en general) ; ecart trouve par `camille-po`, mentions corrigees, verdict et motif inchanges.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-26.
