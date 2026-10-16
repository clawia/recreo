# s57, palier 2, instance A - rejeu de `nadia-prof` : p2-10, `RCT-CE1-R1` (`FRA-CE1-04`, `ce1-texte-narratif`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-16, 20:02 (UTC+02:00), lu par `date`.
- **Arbre** : branche `s57-nadia-p2-a3`, worktree dedie, partie de `round-two` a `3eef868d`. Textes `c77560f2`, fusion `4e5c9314`,
  tous deux ancetres.
- **Audit joue** : jar construit dans ce worktree, joue a 744, 720 et 768 x 551.

## Resume

| Poste | Jar joue | Verdict | Fiche |
|---|---|---|---|
| p2-10, `RCT-CE1-R1`, voie des textes | code de `3eef868d` ; paquet egal a celui de `7c9ac220` | **`RCT-CE1-R1` levee.** `FRA-CE1-04` passe a **VALIDE**, aucune reserve ouverte | `squad/conformite/FICHE-bandit-manchot-texte-narratif-ce1.md`, rejeu du 2026-10-16 |

**Motif** : a 744 x 551, apres une erreur, l'indice d'Inès, d'Ibrahim et de Chloé finit a 478 dans les six ordres, et celui des
20 items au plus a 508 (240 couples sur 240). Memes cotes a 720 et 768. Les trois jeux de propositions disent l'ordre des faits du
texte.

## 1. Ce que j'ai lance

| | Valeur |
|---|---|
| Jar | `bash outils/mvn-jdk21.sh -B clean package -DskipTests`, `BUILD SUCCESS` ; `ce1-texte-narratif.yaml` du jar egal a `3eef868d` et `7c9ac220` |
| Code annonce identique a `7c9ac220` | `git diff --quiet` sort 1 : phrase de revelation des ecrans en image et quatre tests, non affichee sur ce jeu (detail dans la fiche) |
| Port 18511, libre avant | PID 42696 lu dans le journal, arrete par ce PID seul |
| Chrome | trois profils dans mon dossier de poste, un par largeur, port de debogage 19211, sortis seuls |
| Parties | 8 a 744, 11 a 720, 6 a 768 ; a chaque largeur, 20 items sur 20 joues deux fois en erreur ; 0 ecart |
| Done technique | non lance par moi |

## 2. Ce que j'ai vu, en bref

- Inès, Ibrahim, Chloé : deux rangees de propositions au lieu de trois ; verdict et indice lus en entier sans defiler.
- Ordre des faits : juste sur les trois ; aucun test ne le garde.
- Intrus du premier mot : 5 items, bonne reponse sur 4. Le distracteur qui disparait (Inès) est retire par mon propre texte.
  Pas une reserve, motif dans la fiche, section 3 du rejeu.
- Items 5, 6, 9 (Noé, Léa, Louis) : indice visible a 508, barre de defilement apres l'erreur ; observation deja classee.

## 3. Ce que je n'ai pas joue ni mesure

- Un appareil reel, une autre police, un autre navigateur ; la lecture d'ecran.

## 4. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | fermer `S57-RCT-CE1-R1` ; `FRA-CE1-04` reste `couvert-valide` |
| `sofia-sm` | p2-10 audite, 0,5 consomme en A ; fusion de `s57-nadia-p2-a3` (docs seules) |

## 5. Jobs de `ci.yml` lances pour ce commit

Un seul commit porte ce rejeu et celui du lot `-05`. Sur ce worktree, les quatre fichiers du commit dans l'index, avant commit.
Je ne deduis aucun job que je n'ai pas lance.

- **`build`, partiel** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` restreint a
  `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`,
  `ConcordanceDesTagsDAgentTest`, `ToutesLesReferencesDeDecisionResolventTest`, perimetre `bootstrap` et ses amonts : `BUILD
  SUCCESS`, 19 tests (10, 5, 1, 3), 0 echec. Lance avant l'ecriture de cette section, qui ne contient aucun tableau. **Ce n'est
  pas `mvn verify`** : ce commit ne touche ni code ni contenu servi.
- **`chemins`** et **`prefixes`** : lances apres l'ecriture de cette section, sorties 0 (`verifier-chemins-test.sh`,
  `verifier-chemins.sh` sur `git ls-files`, `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`,
  `verifier-garde-fou-accent-test.sh`, le sujet du commit dans `verifier-prefixes.sh`).
- **`outils`** : non lance ; aucun fichier d'outil touche.

Aucun de ces resultats n'est celui de la CI distante. Rien n'est pousse.
