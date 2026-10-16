# s57, palier 2, instance A - rejeu de `nadia-prof` : lot `-05`, `ce1-pronoms` (`FRA-CE1-05`), apres correction

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-16, 20:02 (UTC+02:00), lu par `date`.
- **Arbre** : branche `s57-nadia-p2-a3`, worktree dedie, partie de `round-two` a `3eef868d`. Correction `42071c23`, fusion
  `02b4a34e`, toutes deux ancetres.
- **Audit joue** : jar construit dans ce worktree, joue a 744 x 551.

## Resume

| Poste | Jar joue | Verdict | Fiche |
|---|---|---|---|
| p2-3, rejeu du lot `-05` | code de `3eef868d` ; paquet egal a celui de `7c9ac220` | **VALIDE**, sans reserve. Le refus du 2026-10-16 (`TRV-CE1-6`) est leve | `squad/conformite/FICHE-bandit-manchot-pronoms-ce1.md`, rejeu du 2026-10-16 |

**Motif** : les cinq textes de ma section 5 sont servis mot pour mot. Sur les huit items ou un garcon et une fille sont en
relation, la fille agit envers le garcon sur quatre (3, 9, 13, 18), le garcon envers la fille sur trois (10, 15, 16), un est
mixte (19) : il ne reste aucune regularite. Le reste est tenu : `PRN-CE1-1` a `-3`, 20 referents justes, `PN4` a 0, pli.

## 1. Ce que j'ai lance

| | Valeur |
|---|---|
| Jar | `bash outils/mvn-jdk21.sh -B clean package -DskipTests`, `BUILD SUCCESS` ; `ce1-pronoms.yaml` du jar egal a `3eef868d` et `7c9ac220` |
| Code annonce identique a `7c9ac220` | **verifie, et inexact a la lettre** : `git diff --quiet 7c9ac220 3eef868d -- adapters domain application bootstrap pom.xml` sort 1. La difference est la phrase de revelation des ecrans a propositions en image (`RDO-CP-R3`) et quatre tests ; elle ne s'affiche pas sur ce jeu |
| Port 18511, libre avant | journal *"Tomcat started on port 18511"*, PID 42696, arrete par ce PID seul |
| Chrome | profil dans mon dossier de poste, port de debogage 19211, sorti seul ; aucun arret par nom d'image |
| Parties | 9 ; 20 items sur 20 joues deux fois en erreur, les deux distracteurs presses ; 0 ecart contre le fichier |
| Pli, 744 x 551, apres une erreur | **20 sur 20** (404 a 508) ; **240 couples (item, ordre) sur 240** |
| Done technique | non lance par moi |

## 2. Points poses par la demande

- **Item 19** : inchange ; la recommandation sur son indice est declinee par `camille-po`. Constate ; ce n'etait pas une reserve.
- **Titre et consigne** : inchanges, deja acceptes.
- **Item 9** : l'indice neuf est servi et se lit en entier apres l'erreur.

## 3. Ce que je n'ai pas joue ni mesure

- Le pli a 720 et 768 sur ce jeu ; un appareil reel, une autre police, un autre navigateur ; la lecture d'ecran.

## 4. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | `FRA-CE1-05` a `couvert-valide` ; statut de tete de la fiche a mettre a jour |
| `sofia-sm` | rejeu de p2-3 fait, 0,5 consomme en A ; fusion de `s57-nadia-p2-a3` (docs seules) |

## 5. Jobs de `ci.yml` lances pour ce commit

Voir `rejeu-nadia-palier-2-a-rct.md`, section 5 : un seul commit porte les deux rejeux, et les jobs y sont ecrits une fois.
