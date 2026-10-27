# s57 - relecture des corrections de `ce1-qlm-aimant` (`S57-BR-QLM-CE1-06`, `P5`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : debut 2026-10-27 14:49:19 (UTC+01:00), lu par `date` ; fin en section 6.
- **Arbre** : branche `s57-p5-nadia-corr06`, base `round-two` a `0286fde1`, lue par `git`.
- **Lu** : `git diff c86148d1 HEAD` sur `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-aimant.yaml` et
  `git show f3d7357c` sur `PaquetCe1QlmAimantTest.java` et `aimant-fautif.yaml` (correction de `marc-dev`, garde `g12`, sondes 54
  a 57), compares a la section 4 de `lecture-nadia-premier-jet-ce1-qlm-aimant.md` et a son `T2`. **Je n'ai pas relu le paquet.**
- **Type** : relecture des lignes corrigees, avant `verify`. **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune
  condition neuve. Je n'ai modifie ni le paquet, ni les tests.
- **Audit degrade, dit** : rien n'est joue a l'ecran. Le paquet n'est toujours pas au catalogue (`grep` de `ce1-qlm-aimant` dans
  les ressources et le code principal : le seul fichier qui le porte est le paquet). Les items 5, 6, 9 et 12 sont joues sur le
  papier, comme a la premiere lecture : avec la competence, avec la croyance fausse *"l'aimant attire les metaux"*, et en lisant
  *papier d'aluminium* comme du papier.
- **Mesures lancees** :
  - recompte par script (Python, hors du depot, non commite) sur le fichier a `HEAD` : mots par la regle de `PaquetLu.mots`, longueur
    en caracteres par la comparaison stricte de `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest.java:198-199`
    (fichier en NFC, verifie), quart par la lettre de ma regle 1, objets partages entre groupes, groupes qui portent *fil de fer* ou
    *papier d'aluminium* ;
  - `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1QlmAimantTest -Dsurefire.failIfNoSpecifiedTests=false`,
    JDK 21.0.10 (propriete `java.version` du rapport) : *"Tests run: 51, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS` (48
    au premier jet, 3 tests de `g12` en plus). Perimetre : ce test, module `adapter-content` et ses dependances. Ce n'est ni un
    `verify`, ni la porte technique.

## Resume

| Point | Reponse |
|---|---|
| Conformite a la section 4 | **les cinq textes tiennent**, caractere pour caractere (section 1) |
| Perimetre du diff | **seules les lignes prescrites changent** : 5 lignes, un seul commit (section 2) |
| Items 5, 6, 9, 12 | **tiennent** tous les quatre (section 1) |
| `g12` et ses sondes | **tient** : ce que mon `T2` demandait, refusees et passantes (section 1) |
| Comptes de `marc-dev` | **confirmes** tous (section 3) |
| Reserve du 2026-10-27, longueur en caracteres | **s'applique encore**, aux memes comptes : 4 sur 4 en B, 7 sur 12. Pas regardee a l'ecran : le paquet n'y est pas. Je la garde telle qu'ecrite (section 3) |
| Ouverture | **aucune** (section 4) |
| Seconde lecture | faits en section 5 ; le classement est a `camille-po` |

## 1. Comparaison a la section 4, element par element

| Point | Element | Verdict | Motif |
|---|---|---|---|
| 1 | item 5, `:113` : `distracteurs: ["trombone, épingle, papier d'aluminium", "aiguille, épingle, fil de cuivre"]` | **tient** | Texte a l'identique, ancien absent. *Papier d'aluminium* n'est plus que dans un groupe distracteur : l'enfant qui ecarte ce groupe comme *"un groupe avec du papier"* doit encore juger le fil de cuivre pour garder le bon. Composition de B tenue (trois objets en fer ; deux en fer et un en aluminium ou en cuivre), paires a un objet (*trombone*, *aiguille*, *épingle*) |
| 2 | item 6, `:117` : `bonneReponse: "la barquette en aluminium"` | **tient** | Texte a l'identique. Aucun nom de l'item ne porte plus *papier* : la croyance fausse plus la lecture du nom ne trouvent rien, il faut savoir que l'aluminium n'est pas attire. Premisse vraie, une seule proposition non attiree, l'item compte |
| 3 | item 6, `:118` : `distracteurs: ["le fil de fer", "l'épingle"]` | **tient** | Texte a l'identique. Un fil qu'on plie, une petite epingle de couture, un recipient : plus deux petits objets durs contre une feuille, aucune forme, taille ni usage communs aux deux distracteurs, aucun mot commun. Enonce, indice et famille `c, c` inchanges |
| 4 | item 9, `:134` : `indice: "Regarde les trois objets de chaque groupe, un par un."` | **tient** | Texte a l'identique, 10 mots. *Passe en revue* absent : plus de locution. Une methode appliquee a chaque objet, sans matiere ni *intrus*, ne designe aucun groupe. Aucun espace avant ponctuation a placer, le texte n'a ni `?` ni `:` |
| 5 | item 12, `:148` : `distracteurs: ["aiguille, trombone, fil de cuivre", "agrafe, trombone, papier d'aluminium"]` | **tient** | Texte a l'identique, ancien absent. *Papier d'aluminium* dans un seul groupe distracteur, le cuivre reste a juger dans l'autre. *Fil de fer* sort de l'item, comme je l'avais prescrit. Paires a un objet (*aiguille*, *agrafe*, *trombone*) |
| 6 | comptes et rang | **tient** | Aucune autre ligne du YAML ne change (section 2) : 12 items, en-tete et entree de rang inchanges |
| `T2` | garde `g12`, `verifierQueLePapierDAluminiumNeDonnePasLaReponse` | **tient** | Ce que je demandais, par egalite sur la liste fermee : en A au negatif, refus d'une bonne reponse *papier d'aluminium* ; en B, refus quand les deux groupes distracteurs le portent. Appliquee au paquet reel (`g12...DansLePaquet`), ce qui couvre les items 1 et 6 corrige que je voulais passants. Refusees : sonde 55 (item 6 du premier jet), 54 (item 5), 56 (item 12), chacune avec son message. Passantes : 57 (item 2), et 19 (A au negatif, *la gomme*), que je n'avais pas demandee et qui ne gene rien. Les sondes 54 a 56 recopient mot pour mot les items du premier jet |

**Ce que `g12` ne garde pas, et que je garde seule** : la forme, la taille et l'usage partages par deux distracteurs de A (le second
motif de l'item 6) ; une autre lecture fausse d'un nom que *papier*. `g12` ne lit que le papier d'aluminium.

## 2. Perimetre du diff

`git diff --stat c86148d1 HEAD` sur le YAML : *"1 file changed, 5 insertions(+), 5 deletions(-)"*, un seul commit (`f3d7357c`). Les
5 lignes sont mes points 1 a 5. **Rien d'autre ne change** : ni enonce, ni autre proposition, ni autre indice, ni declaration
`TRV-CE1-2`, ni en-tete. Le test gagne `g12` et deux tests de sondes ; le jeu fautif gagne les sondes 54 a 57, et rien d'autre.

## 3. Les comptes, apres correction

**Recompte sur le fichier a `HEAD`** [script] :

| Compte de `marc-dev` | Verdict | Mesure |
|---|---|---|
| Quart 8 sur 12, dont 4 items de A | **confirme** | A : 1, 3, 6, 7 ; B : 2, 5, 9, 12 |
| `S` = 2 sur 4 (items 9 et 12) | **confirme** | Mots par groupe : item 2, 5 contre 6 et 5 ; item 5, 5 contre 4 et 5 ; item 9, 3 contre 5 et 5 ; item 12, 3 contre 5 et 4 |
| Bonne reponse strictement la plus longue, 2 sur 12 | **confirme** | Items 3 et 6 |
| Groupes portant *fil de fer* par item de B : 2, 1, 0, 0 | **confirme** | Le chiffre attendu de mon point 7 |
| Chaque paire de groupes d'un item de B partage exactement un objet | **confirme** | Dans les 4 items |

**La reserve du 2026-10-27, longueur en caracteres : elle s'applique encore.** Le bon groupe reste strictement le plus court dans les
**4 items de B** (24 contre 39 et 28 ; 30 contre 37 et 32 ; 25 contre 37 et 29 ; 25 contre 33 et 36) et la bonne reponse l'est dans
**7 items sur 12** (A : 1, 7, 10). Mes corrections ont rapproche les distracteurs du bon groupe aux items 5 et 12 (37 et 34 devenus 37
et 32 ; 40 et 36 devenus 33 et 36) sans changer aucun compte, comme je l'avais annonce. Je ne l'ai pas regardee a l'ecran : le paquet
n'est pas au catalogue. **Ce que j'en fais** : rien de plus a cette relecture. Elle reste une reserve de jeu, datee, pas une regle ni
une condition ; je ne releve pas ma barre de 6.3 qui est en mots et que `marc-dev` tient. Je la regarderai a l'ecran a l'audit : si un
enfant prend le plus court, ce sera une reserve d'audit a traiter par la liste d'objets, pas par un item.

## 4. Ouvertures

**Aucune.** Aucun item, aucune formulation de la story ni des regles reportees n'est rouvert. Les cinq textes sont ceux que j'ai
ecrits, ils produisent l'effet que je leur demandais, et les comptes sont ceux que j'avais annonces. Rien a reporter par `camille-po`.

**Ce dont je reste la seule garde sur ce paquet, apres cette relecture** : l'exactitude de chaque objet contre sa classe ; la forme,
la taille et l'usage partages par deux distracteurs de A ; qu'aucun nom ne donne la reponse par une lecture fausse autre que celle que
`g12` encode ; qu'un indice soit une methode et que son vocabulaire soit du CE1 ; que *fil de fer* ne devienne pas le signe du bon
groupe ; la longueur en caracteres du bon groupe (reserve, section 3) ; *doré* ; les quatre cas d'`AIM-CE1-2` que la garde ne voit
pas ; l'operation mentale de `FER-3` ; `CYC-1`, `NRC-CE1-1` sur le sujet, `TRV-CE1-6`. **Aucune n'est couverte par un test.**

## 5. Faits pour le classement par `camille-po`

Cette relecture ne porte que sur les cinq lignes que j'ai prescrites (section 2) et sur la garde `g12` que mon `T2` demandait. Elle ne
relit aucun item hors des items 5, 6, 9 et 12, ne pose aucune correction, ne rouvre aucun verdict hors de ces lignes et n'ouvre aucun
tour. C'est la forme d'une seconde lecture au sens de `DEC-0120` A.272 (0,5 hors marge, A.270 point 4), celle qu'annoncait la section
7 de ma premiere lecture. Le classement est a `camille-po` ; je n'en convertis aucune duree.

## 6. Duree et verifications

Mesuree par `date` : debut **2026-10-27 14:49:19**, fin en ligne ci-dessous, avant le commit.

**Verifications avant commit**, ce fichier present dans l'arbre et indexe. Il n'ecrit ni ne renomme aucun identifiant de condition :
`T2` est le repere de demande de ma premiere lecture, `g12` celui de `theo-archi`.

- `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false` :
  *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`. Perimetre : ce seul test, module `bootstrap` et ses dependances.
- `.github/scripts/verifier-chemins.sh`, entree standard fermee (`< /dev/null`) : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0.
- Aucun autre job de `.github/workflows/ci.yml` lance.

**Fin** : **14:56:45**, soit **7 min 26 s**, dont la mesure du test du paquet et celle du garde-fou des identifiants.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
