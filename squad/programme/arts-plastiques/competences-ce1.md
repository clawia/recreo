# Référentiel de compétences - Arts plastiques - CE1 (cycle 2)

> Écrit par `nadia-prof` le 2026-10-13
> (`squad/conformite/ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, section 11.5),
> au même grain et dans la même forme que `squad/programme/arts-plastiques/competences-cp.md` : une
> compétence par ligne, un verbe en tête, bornée par sa parenthèse. **Socle réaliste, non exhaustif
> et non officiel** : à compléter et à faire valider par l'utilisatrice au fil des sprints, en lien
> avec `squad/programme/MATRICE-couverture.md`. Ne remplace pas une lecture des programmes officiels
> de l'Éducation nationale.
>
> Les arts plastiques sont une matière de cycle 2, dont le programme est écrit pour le cycle et non
> pour chaque année. **Une ligne n'est écrite ici que si un paquet de CE1 doit y servir autre chose
> qu'un paquet de CP sur la ligne voisine** : la frontière avec le CP est écrite sur chaque ligne
> (section "Frontières"). Les compétences du CP que le CE1 reprend sans contenu neuf ne sont pas
> recopiées (section "Ce que ce référentiel ne reprend pas du CP").

**Codes de ligne.** Chaque compétence porte un code entre crochets, alloué une fois et
jamais réutilisé : c'est par lui qu'un paquet de contenu déclare la ligne qu'il sert, et
par lui que le nombre d'items servant une ligne se compte (`ADR-0081`). Reformuler un
intitulé ne change pas son code ; insérer une ligne prend le numéro libre suivant, sans
renuméroter les autres. Le code n'est donc pas un rang.

## Se repérer dans les domaines liés aux arts plastiques

- [ART-CE1-01] Reconnaître, devant une œuvre montrée, comment elle a été faite (peinte, dessinée, photographiée).
- [ART-CE1-02] Comparer deux œuvres montrées ensemble qui représentent le même sujet, et dire ce qui les distingue (la façon dont chacune est faite, ce que chacune montre du sujet).

---

## Frontières, avec le CP et avec le CM1

| Ligne de CE1 | Ligne voisine | Ce qui les sépare |
|---|---|---|
| `ART-CE1-01` | `ART-CP-09` (décrire une œuvre : sujet et matériaux) | au CP, ce qu'on voit et la seule différence entre un objet en volume et une image ; ici, entre trois façons de faire une image plate. Un item dont la réponse est *statue* contre *tableau* sert le CP |
| `ART-CE1-01` | `ART-CP-11` (nommer outils, supports, gestes) et `cp-arts-oeuvres-familles` | au CP, le mot de l'outil ou du métier, sans œuvre regardée ; ici, la façon de faire se lit sur l'œuvre. Un item qui se répond sans regarder l'image sert le CP, et `ART-4` le refuse |
| `ART-CE1-01` | `HDA-CM1-02`, `HDA-CM1-03` (caractéristiques techniques, matériaux et mise en forme) | au CM1, la technique fine et le matériau précis ; au CE1, la façon de faire seule, en trois mots |
| `ART-CE1-02` | `ART-CP-10` (reconnaître des œuvres et les rapprocher) | au CP, ce que deux œuvres ont en commun ; ici, deux œuvres d'un même sujet et ce qui les distingue. Aucune paire du paquet de CP n'est reprise |
| `ART-CE1-02` | `ART-CM1-08`, `HDA-CM1-01` (décrire avec un lexique : support, format, composition) | au CM1, le vocabulaire de la description ; au CE1, la différence entre deux œuvres, dite avec les mots de `ART-CE1-01` |

## Ce que ce référentiel ne reprend pas du CP, et pourquoi

- **`ART-CP-05` (couleurs) et `ART-CP-11` (outils)** : le CE1 n'y apporte aucune connaissance que le
  CP ne sert pas déjà. Un paquet de CE1 y servirait la ligne de CP avec des items plus longs.
- **`ART-CP-09` (décrire une œuvre)** : le sujet et les matériaux se décrivent au CE1 comme au CP.
  Ce que le CE1 ajoute est porté par `ART-CE1-01`.
- **`ART-CP-10` (rapprocher deux œuvres)** : ce que deux œuvres ont en commun reste la ligne du CP.
  Ce que le CE1 ajoute est porté par `ART-CE1-02`.
- **`ART-CP-01`, `-02`, `-03`, `-04`, `-06`, `-07`, `-08`** : produire, employer des outils,
  observer sa propre production, exprimer, dire ce qu'on ressent, raconter, transformer. Le
  programme du cycle 2 les travaille aussi au CE1, et ce produit ne les sert à aucun niveau, pour
  une raison de nature irréversible (`competences-cp.md`, jugement de faisabilité). Une jumelle de
  CE1 n'entrerait pas au dénominateur atteignable et ne servirait rien de plus à un enfant.

## Jugement de faisabilité (`nadia-prof`, 2026-10-13)

**Audit dégradé, et je le déclare** : aucun paquet n'existe sur ces lignes, je n'ai rien joué. Ce
jugement dit la situation de chaque ligne à son entrée dans la matrice ; il n'est pas l'instruction
des lignes. **L'instruction, ligne par ligne, avec ses conditions, est due avant le premier
paquet.**

**Les moyens du produit** : la mécanique de choix, le palier 1 illustré (une image fixe, des
propositions en texte, `ADR-0073`), un corpus de douze reproductions
(`squad/corpus/oeuvres-domaine-public.yaml`) : onze peintures ou estampes et un objet. **Aucun
son**, conformément à la réponse de l'utilisatrice du 2026-10-13 (`DEC-0120` A.27).

| Ligne | Situation à l'entrée dans la matrice | Motif |
|---|---|---|
| `ART-CE1-01` | **`non couvert`**, en attente d'un matériau | la forme existe : une reproduction par item, celle d'`ART-CP-09` (`couvert-valide`). **Ce qui manque est le corpus** : aucun dessin, aucune photographie parmi les douze reproductions. Une façon de faire décrite dans l'énoncé serait une contrefaçon (`CON-1`) |
| `ART-CE1-02` | **`non couvert`**, en attente de la forme à deux œuvres **et** d'un matériau | deux œuvres montrées ensemble : c'est la forme d'`ART-CP-10`, refusée le 2026-10-11 et pas encore rejouée. **L'instruction de cette ligne attend le verdict de ce rejeu**, pas son existence : un refus de forme n'est pas un motif de nature, la ligne reste au dénominateur. Le corpus n'a pas deux œuvres d'un même sujet faites de deux façons |

Aucune ligne n'est `non couvrable en l'état` : deux lignes, deux au dénominateur atteignable.

**Conditions qui valent déjà, sans identifiant neuf** : `ART-1` et `ART-2` (posées sur le CP),
`ART-3`, `ART-4`, `ART-5` (posées sur le CM1), `CON-1`, `CON-5`, `REC-1` à `REC-4` transposées à une
reproduction (`ARBITRAGE-PREALABLE-s50-art-cp.md`), `TRV-CE1-1` (aucun champ à voix haute au CE1),
`TRV-CE1-6`, et `NRC-CE1-1` (aucune reprise du CP, item par item), étendue aux arts plastiques CE1 le
2026-10-13 (`ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, section 12).

**Point de vigilance nommé d'avance, pour l'instruction** : `ART-1` mord sur `ART-CE1-01`. Un dessin
ne se reconnaît pas à ce qu'il est gris, ni une photographie à ses couleurs : la façon de faire se
lit sur le trait, l'aplat, le grain. Une paire que seule la couleur sépare est refusée.

### Mention exigée d'avance si une ligne bouge

Si une ligne passe un jour à `couvert-valide`, la matrice porte cette mention : *"réception
d'œuvres seule ; aucune pratique plastique n'est servie par ce produit"*.

### Bilan chiffré - arts plastiques CE1

| | Nombre |
|---|---|
| Lignes du référentiel | **2** |
| dont `non couvert` | **2** |
| dont `non couvrable en l'état` | **0** |
| **Entrent au dénominateur atteignable** | **2** |
| dont jouables **dès aujourd'hui** par une mécanique et un corpus existants | **0** |

**Ce dont je reste la seule garde sur cette matière** : qu'aucun item de CE1 ne serve une ligne de
CP (`NRC-CE1-1`), qu'aucun ne se réponde sans regarder l'image (`ART-4`), `ART-1` sur la façon de
faire, et l'attribution de chaque œuvre (`ART-3`). Aucun automate ne compare deux paquets.

**Aucun intitulé ci-dessus ne devient un titre de jeu** (`ADR-0078`).
