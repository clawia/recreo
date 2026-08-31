# Tri du perimetre historique de la formule de JDK disqualifiee - lot F de s25

- **Auteur** : `sofia-sm`, **casquette developpeuse**. Ce document ne prononce aucun Done, ne clot
  aucun obstacle, et n'arbitre aucun rang.
- **Date** : 2026-08-31. **Sprint** : s25. **Branche** : `s25-sofia-dev`, worktree dedie ouvert sur
  `origin/s25-sofia`.
- **Ce que ce document est** : le **tri** demande par la review de s24 (obstacle 9), sur le patron
  de `S21-6` - **amender, jamais reecrire**. Il classe chaque occurrence et nomme, pour chacune, qui
  en repond.
- **Ce qu'il n'est pas** : un balayage. Aucune correction mecanique n'a ete faite, et aucun document
  d'un autre agent n'a ete modifie.

---

## 1. La mesure, refaite par moi, et l'ecart avec le chiffre annonce

`theo-archi` a mesure **40 fichiers, 51 occurrences** le 2026-08-30, sur l'index des fichiers suivis
du worktree `s24-theo`. **Je ne relaie pas ce chiffre, je le refais**, et il n'est pas le mien.

**Ma methode**, citee avec son perimetre : recherche de la chaine `toolchain declaree` sur les
fichiers `.md` de l'index suivi de `s25-sofia-dev`, le 2026-08-31, avant les deux amendements de la
section 3.4.

```
git ls-files | grep '\.md$' | xargs grep -c "toolchain declaree"
```

| Mesure | Fichiers | Occurrences |
|---|---|---|
| `theo-archi`, 2026-08-30, worktree `s24-theo` | 40 | 51 |
| **moi, 2026-08-31, worktree `s25-sofia-dev`** | **44** | **59** |

**L'ecart est de +4 fichiers et +8 occurrences, et je dis d'ou il vient plutot que de le taire.**
L'arbre que je mesure n'est pas celui qu'il a mesure : il porte, en plus de `s24-theo`, la cloture
de s24 et les quatre branches amont de s25. Les fichiers qui expliquent l'ecart sont ceux qui
n'existaient pas ou n'etaient pas fusionnes le 2026-08-30 - la `review.md` et le `RAPPORT.md` de
s24, `relecture-s24.md`, `proposition-ines-ecran-de-saisie.md`, `ADR-0071` lui-meme. **Le chiffre
n'a pas augmente parce que quelqu'un a ecrit la formule apres l'interdit** : il a augmente parce que
la plupart de ces occurrences neuves **parlent** de la formule pour la disqualifier. C'est la
categorie A ci-dessous, et c'est la raison pour laquelle un comptage brut ne peut pas servir de
verdict.

---

## 2. Le critere de tri - il n'est pas de moi

`ADR-0071` ne disqualifie pas une chaine de caracteres, il disqualifie une **methode** :

> la version de JDK citee par une mesure est celle que la **machine virtuelle des tests rapporte**,
> jamais celle qu'une entree de toolchain **declare**.

Et `theo-archi` a ecrit pourquoi un balayage ne peut pas trancher : *"le chiffre est le meme dans
les deux cas, c'est tout le probleme"*. **Trier, c'est donc lire ce que chaque phrase donne comme
source.** Trois categories en sortent :

| # | Categorie | Ce qui est du |
|---|---|---|
| **A** | **Mention meta** - la formule est citee pour etre discutee, comptee ou disqualifiee | **Rien, jamais.** Corriger une citation la rendrait fausse |
| **B** | **Enonce de mecanisme** - la phrase decrit ce que fait l'enveloppe ou ce qu'exige le poste, sans dater aucune mesure | **Rien**, sauf si le mecanisme change - et il a change aujourd'hui |
| **C** | **Raccordement de source** - la phrase donne la declaration de toolchain comme **source** de la version d'une mesure datee | **Un amendement date, par l'auteur de la phrase** |

**Ce que C ne veut pas dire.** Le chiffre `21.0.10` est exact partout ou il est ecrit - `S21-6` l'a
etabli a trois sources independantes, `ADR-0071` l'a reconfirme par l'execution. Une phrase de
categorie C n'est pas fausse : **elle est sans methode**. C'est pour cela qu'elle appelle un
amendement date et non une correction.

---

## 3. Le tri - 44 fichiers, 59 occurrences

### 3.1 Categorie A - mention meta : 24 occurrences dans 14 fichiers, rien n'est du

| Fichier | Occ. | Auteur de la phrase |
|---|---|---|
| `squad/decisions/adr/ADR-0071-...md` | 2 | `theo-archi` |
| `squad/sprints/s24-.../complement-jdk-declare-contre-execute.md` | 2 | `theo-archi` |
| `squad/sprints/s21-.../amendement-jdk-douze-confirmations-s21-6.md` | 1 | `theo-archi` |
| `squad/sprints/s21-.../avis-faisabilite.md` | 2 | `theo-archi` |
| `docs/backlog/backlog.md` | 2 | `camille-po` |
| `squad/sprints/s23-.../RAPPORT.md` | 1 | moi |
| `squad/sprints/s23-.../retro.md` | 1 | moi |
| `squad/sprints/s23-.../review.md` | 1 | moi |
| `squad/sprints/s24-.../planning.md` | 3 | moi |
| `squad/sprints/s24-.../review.md` (l. 545) | 1 | moi |
| `squad/sprints/s24-.../relecture-s24.md` | 1 | `relecteur-pedagogique` |
| `squad/sprints/s24-.../proposition-ines-ecran-de-saisie.md` (l. 680) | 1 | `relecteur-pedagogique`, cite chez `ines-design` |
| `squad/conformite/FICHE-bandit-manchot-mots-de-liaison-cm1.md` (l. 369) | 1 | `nadia-prof` - **amendement deja porte** |
| `squad/conformite/FICHE-bandit-manchot-problemes-etapes-cm1.md` (l. 366) | 1 | `nadia-prof` - **amendement deja porte** |

**Les deux dernieres lignes sont le patron `S21-6` deja applique** : la phrase d'origine reste, et un
amendement date, quelques centaines de lignes plus bas, dit qu'elle n'avait pas ete constatee. Elles
comptent donc **deux fois** dans le balayage brut et **zero fois** dans ce qui reste du.

### 3.2 Categorie B - enonce de mecanisme : 4 occurrences dans 4 fichiers

| Fichier | Occ. | Verdict |
|---|---|---|
| `CLAUDE.md` (l. 50) | 1 | **Amende par ce lot** - voir 3.4 |
| `README.md` (l. 40) | 1 | **Amende par ce lot** - voir 3.4 |
| `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` (l. 609) | 1 | **Rien** : *"un JDK 21 (variable `JAVA_HOME` ou toolchain declaree)"* enonce un prerequis, ne date aucune mesure |
| `squad/sprints/s21-.../proposition-ines-rep-8.md` (l. 44) | 1 | **Rien** : meme forme, prerequis de lancement |

### 3.3 Categorie C - raccordement de source : 31 occurrences dans 26 fichiers, chacune a son auteur

**Aucune n'est corrigee ici**, et le motif est celui de `theo-archi`, mot pour mot : *"ces fichiers
sont des rapports de sprints clos, dont plusieurs appartiennent a d'autres agents ; je ne reecris
pas leurs documents, je route."*

| Auteur de la phrase | Fichiers | Occ. | Ce qui lui est route |
|---|---|---|---|
| **`theo-archi`** | `ADR-0055`, `ADR-0056`, `ADR-0057`, `ADR-0058` (2), `ADR-0059` (3), `ADR-0061`, `ADR-0062` ; s17 `avis-clause-invariante`, `avis-couleur-de-position`, `avis-garde-fous-routes`, `avis-population`, `avis-mecanisme-texte-support` (2) ; s18 `avis-faisabilite` (2) ; s19 `avis-faisabilite` ; s20 `avis-faisabilite`, `mesure-budget-vertical` | **19** | Un amendement date par `ADR`, ou **un seul amendement de portee nommee** s'il juge que ses dix-huit portes techniques relevent du meme geste. **La forme est a lui** : il a deja trie sur ce critere a `S21-6` |
| **`nadia-prof`** | `FICHE-pluriel-cp`, `FICHE-ranger-nombres-cp`, `FICHE-problemes-durees-cm1` ; s23 `avis-nadia-done-pedagogique-s23` | **4** | Le meme amendement date que celui qu'elle a deja porte sur deux de ses fiches. **Aucun Done n'est retire par la** : le chiffre est exact, c'est sa source qui n'etait pas constatee |
| **`yanis-qa`** | s19, s20 et s22 `rapport-qualite.md` | **3** | Un amendement date par rapport. La porte technique qu'il cite reste verte |
| **`marc-dev`** | s23 `verification-en-jeu-marc-corrections`, `verification-en-jeu-marc-reserves-d-e` | **2** | Un amendement date par document |
| **`ines-design`** | s21 `mesure-ines-sor1-a-sor4` ; s24 `proposition-ines-ecran-de-saisie` (l. 654) | **2** | La seconde est deja l'**obstacle 6 de la review de s24**, routee et non echue : `ines-design` n'avait pas ete invoquee sur ce sprint. La premiere s'y ajoute |
| **`nadia-prof`, dans un document dont je suis l'animatrice** | s24 `review.md` (l. 600) | **1** | Sa section *"J'ai joue"* de l'ajout du 2026-08-31. **Je ne l'amende pas a sa place** bien que le fichier soit ma ceremonie : la phrase est d'elle |

**Total categorie C : 31 occurrences.** A + B + C = 24 + 4 + 31 = **59**, et 14 + 4 + 26 = **44**
fichiers : les deux totaux de la section 1 sont retrouves, sans reste.

### 3.4 Ce que ce lot amende, et rien d'autre

Deux fichiers, tous deux de categorie B, tous deux amendes **parce que le mecanisme qu'ils decrivent
a change aujourd'hui** et non parce que la formule y figurait :

| Fichier | Ce qui a change | Motif |
|---|---|---|
| `CLAUDE.md`, section *Prerequis de build* | La phrase se terminait par *"et la version sur laquelle on a mesure est celle de la toolchain"* | **C'est la formule disqualifiee ecrite comme doctrine, dans le fichier que tous les agents lisent.** Elle est remplacee par le fait : l'enveloppe demande sa version au JDK et refuse quand il dement l'entree |
| `README.md` | La phrase decrivait une enveloppe qui lance, sans dire qu'elle refuse | Elle decrit l'outil que ce lot vient de changer. La laisser telle quelle en ferait une reference qui ne resout plus |

**Ces deux amendements ne sont pas le tri, ils en sont une consequence.** Le tri lui-meme ne corrige
rien.

---

## 4. Ce que ce tri etablit et qui n'etait pas dit

- **Pres de la moitie du perimetre brut n'a rien a corriger.** 28 occurrences sur 59 (categories A
  et B) sont soit des citations, soit des enonces de mecanisme. **Un balayage mecanique en aurait
  corrompu 24**, en transformant des citations exactes en citations fausses - la faute exacte que
  `S21-6` avait evitee et que `theo-archi` a refuse de commettre.
- **Le perimetre reellement du est de 31 occurrences dans 26 fichiers, reparties sur six auteurs**,
  dont **19 chez un seul**. Ce n'est pas une charge diffuse : c'est un lot chez `theo-archi` et cinq
  petits lots ailleurs.
- **Deux occurrences sont deja soldees** par le patron `S21-6`, et leur presence dans le comptage
  brut prouve a elle seule qu'un chiffre de balayage ne dit rien de ce qui reste du.

---

## 5. Ce que je route, et a qui

| # | Objet | A qui | Artefact attendu |
|---|---|---|---|
| 1 | **19 raccordements de source** dans huit `ADR` et neuf avis de faisabilite | `theo-archi` | Amendement date, forme a son choix |
| 2 | **4 raccordements** dans trois fiches de conformite et un avis de Done | `nadia-prof` | Amendement date. Aucun Done n'est en cause |
| 3 | **3 raccordements** dans trois rapports qualite | `yanis-qa` | Amendement date |
| 4 | **2 raccordements** dans deux verifications en jeu | `marc-dev` | Amendement date |
| 5 | **2 raccordements** chez `ines-design`, dont l'obstacle 6 de s24 deja ouvert | `ines-design` | Amendement date |
| 6 | **1 raccordement** dans la section *"J'ai joue"* de la review de s24 | `nadia-prof` | Amendement date. **Le fichier est ma ceremonie, la phrase est d'elle** |

**Aucun de ces six routages n'est clos par ce document.** Ils le seront quand les amendements
existeront sur le disque, pas sur une intention.

---

## 6. Ce que ce document ne fait pas

- Il **ne reecrit aucun document d'un autre agent**, ni aucun document de ceremonie close.
- Il **ne propose aucun garde-fou** contre cette formule : `ADR-0071` section 4.1 a tranche qu'un
  balayage ne pourrait constater que sa presence, jamais sa verite, et je ne rouvre pas sa decision.
- Il **ne prononce aucun Done**, ne retire aucune couverture, et ne conteste aucun chiffre de
  version : `21.0.10` est exact partout ou il est ecrit.
