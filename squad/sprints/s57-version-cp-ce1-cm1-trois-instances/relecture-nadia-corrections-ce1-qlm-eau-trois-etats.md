# s57 - relecture des corrections de `ce1-qlm-eau-trois-etats` (`S57-BR-QLM-CE1-05`, `P5`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : debut 2026-10-27 14:49:11 (UTC+01:00), lu par `date` ; fin en section 6.
- **Arbre** : branche `s57-p5-nadia-corr05`, base `round-two` a `0286fde1`, lue par `git`.
- **Lu** : `git diff e6694d47 HEAD` sur `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-eau-trois-etats.yaml`, et
  `git show e0875968` sur `PaquetCe1QlmEauTroisEtatsTest.java` et `eau-fautive.yaml` (correction de `marc-dev`), compares a la
  section 4 de `lecture-nadia-premier-jet-ce1-qlm-eau-trois-etats.md`. Dans le test : la sonde `s12`, les deux gardes neuves et les
  sondes 54 a 57. **Je n'ai pas relu le paquet.**
- **Type** : relecture des lignes corrigees, avant `verify`. **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune
  condition neuve. Je n'ai modifie ni le paquet, ni les tests.
- **Audit degrade, dit** : rien n'est joue a l'ecran (paquet hors catalogue). Les trois items corriges sont joues sur le papier.
- **Mesures lancees** :
  - comparaison octet par octet du diff (`cat -A`), espace avant `?` comprise ;
  - recompte des 12 items par script (Python, hors du depot, non commite) : longueur de la bonne reponse et des distracteurs,
    *rien ne change* bonne reponse et distracteur ;
  - `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1QlmEauTroisEtatsTest -Dsurefire.failIfNoSpecifiedTests=false` :
    *"Tests run: 52, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`. Perimetre : ce test, module `adapter-content` et ses
    dependances. Ce n'est ni un `verify`, ni la porte technique.

## Resume

| Point | Reponse |
|---|---|
| Conformite a la section 4 | **les cinq points tiennent**, texte a l'identique (section 1) |
| Perimetre du diff | **seules les lignes prescrites changent** : 5 lignes, mes points 1 a 5 (section 2) |
| Items 4, 8, 12 | **tiennent** (section 3) |
| `TRV-CE1-2`, bonne reponse strictement la plus longue | **6 sur 12, confirme** : items 1, 4, 6, 8, 9, 12, ceux que j'avais annonces |
| Ouverture | **aucune** (section 5) |
| Duree | section 6 |

## 1. Comparaison a la section 4, element par element

| Point | Element | Verdict | Motif |
|---|---|---|---|
| 1 | item 4, `:92` : `"Une flaque est dans la cour. Le soleil brille toute la journée. Que devient l'eau de la flaque ?"` | **tient** | Texte a l'identique ; seul *dehors* devient *dans la cour*. L'espace avant `?` est l'insecable (`C2 A0`), avant et apres : c'est ce que ma section 4 demandait en toutes lettres (*"L'espace avant `?` reste insecable (`TYP-1`)"*). Mon tableau portait une espace ordinaire ; c'est ma phrase qui fait foi, et `marc-dev` l'a suivie |
| 2 | item 4, `:94` : `["elle n'existe plus", "elle devient de la buée"]` | **tient** | A l'identique, *du brouillard* absent |
| 3 | item 8, `:118` : `["elle n'existe plus", "elle devient du brouillard"]` | **tient** | A l'identique, *un nuage* absent |
| 4 | item 12, `:139` : `# TRV-CE1-2 : d, e` | **tient** | A l'identique |
| 5 | item 12, `:142` : `["l'eau gèle", "rien ne change"]` | **tient** | A l'identique, *les glaçons disparaissent* absent |
| `T1` | sonde `s12` : *"regle 4 : rien ne change est distracteur de 3"* | **tient** | Le fragment que j'avais deduit de la lecture du code, sans l'avoir mesure. Il l'est desormais : le test passe sur le paquet corrige. Le recompte par script donne *rien ne change* distracteur de 3 items (2, 5, 12), bonne reponse de 1 |
| `T2` | garde *nuage* ou *fumée* en distracteur d'un item de vapeur dont l'enonce porte `BOUILLIR` ; sondes 54, 55 | **tient** | La forme que j'avais decrite. Sonde 54 : l'item 8 du premier jet a l'identique, refuse sur *"elle devient un nuage"* ; sonde 55 : *de la fumée* pour la meme casserole, refuse. *L'eau bout* est bien lu par `BOUILLIR`. Le *brouillard* n'est pas dans le motif, et ne doit pas y etre : c'est le remplacement que j'ai choisi. Echantillon qui passe : les items reels qui portent *nuage* ou *fumée* en distracteur, soit l'item 10 (*de la fumée* sur la route, sans ebullition). Ce n'est pas l'item 8 corrige que je nommais, qui ne porte plus ni l'un ni l'autre et passe par le test sur tout le paquet ; l'item 10 montre en plus que la garde ne refuse pas la fumee hors d'une casserole. Mieux que ma sonde |
| `T3` | garde *disparaître* en distracteur d'un item de temperature dont la bonne reponse est la fonte ; sondes 56, 57 | **tient** | Sonde 56 : l'item 12 du premier jet a l'identique, refuse ; sonde 57 : *la glace disparaît* a cote de *la glace fond*, refuse. Le motif `dispar(?:ai|u|ition)` lu apres `plat` prend *disparaît*, *disparu*, *disparaissent*. Echantillon qui passe : les items reels qui portent une disparition en distracteur, dont l'item 11 (*le glaçon disparaît*, bonne reponse *rien ne change*), celui que je nommais ; les items 1 et 6 passent aussi, parce qu'ils sont de vapeur |

## 2. Perimetre du diff

`git diff --stat e6694d47 HEAD` sur le YAML : *"1 file changed, 5 insertions(+), 5 deletions(-)"*, un seul commit (`e0875968`). Les 5
lignes sont mes points 1 a 5, aux lignes que j'avais nommees (`:92`, `:94`, `:118`, `:139`, `:142`). **Rien d'autre ne change** : ni
les autres enonces, ni les bonnes reponses, ni les indices, ni les lignes `# TRV-CE1-2 : a, b` des items 4 et 8, ni l'en-tete et ses
lignes de compte. Le test change sur trois points, tous de ma section 5 (`T1`, `T2`, `T3`) ; le jeu fautif gagne les sondes 54 a 57,
et rien d'autre.

## 3. Les trois items corriges, joues sur le papier

| Item | Verdict | Motif |
|---|---|---|
| 4, flaque dans la cour | **tient** | *Dans la cour* met la flaque sur un sol dur : toute l'eau s'en va dans l'air, la bonne reponse est vraie pour toute la flaque. Plus de *brouillard* que la meteo de l'enonce ecarte : *la buée* ne s'associe ni au soleil ni a l'ombre, l'enfant ne l'ecarte qu'en sachant qu'une flaque ne fait pas de buee. *Elle n'existe plus* est la conception, jamais juste. **Une seule reponse defendable.** 18 mots, trois phrases |
| 8, casserole qui bout | **tient** | Plus de *nuage* : rien de ce que l'enfant voit au-dessus de la casserole n'est propose sous le nom qu'il lui donne. *Du brouillard* est de la famille de la buee (un seul element par item, regle 3 tenue), et l'enonce n'a pas de meteo qui l'ecarterait. **Une seule reponse defendable** |
| 12, glacons au soleil | **tient** | Plus de *les glaçons disparaissent*, vrai dans la langue de l'enfant. *Rien ne change* est faux au soleil, *l'eau gèle* aussi : **une seule reponse defendable**, et l'ecarter demande de savoir que la chaleur fait fondre. Le cout que j'avais dit (*rien ne change* distracteur de 3 items pour 1 bonne reponse) est celui que je mesure ; je le regarde a l'ecran, sans durcir ma regle |

## 4. `TRV-CE1-2` : bonne reponse strictement la plus longue

Recompte par script sur le fichier corrige : **6 items sur 12**, les items 1, 4, 6, 8, 9 et 12 (item 4 : 31 caracteres contre 18 et
23 ; item 8 : 31 contre 18 et 26 ; item 12 : 19 contre 10 et 14). C'est le chiffre et la liste de ma section 4, et celui que
`marc-dev` signale. **A la limite de *la moitie au plus*, tenue.** Ma reponse de la section 3 du premier jet tient sans changement :
un septieme item serait refuse par le test transverse.

## 5. Ouvertures

**Aucune.** Aucun item, aucune formulation n'est rouvert : les cinq lignes sont celles que j'ai ecrites, les trois items tiennent, et
les gardes sont les formes que je proposais a `theo-archi`. Rien a reporter par `camille-po`.

**Ce dont je reste la seule garde sur ce paquet, apres cette relecture** : l'exactitude de chaque fait contre 3.5 et le CM1, la flaque
sur un sol dur comprise ; qu'un distracteur de vapeur ne soit pas ce que la meteo de l'enonce ecarte (le brouillard au soleil, `T4`) ;
qu'un distracteur de vapeur ne soit pas ce que la situation produit **hors d'une ebullition**, ou d'un nom que la garde ne lit pas ;
qu'une disparition ne soit pas vraie dans la langue de l'enfant **hors d'un item de fonte** ; l'eau liquide decrite sans le mot *eau* ;
les paraphrases de *rien ne change* ; qu'un indice aide un enfant bloque ; `FER-3` item par item ; `NRC-CE1-1` sur le sujet et
`CYC-1` ; `TRV-CE1-6`. Les gardes `T2` et `T3` couvrent desormais le nuage ou la fumee d'une eau qui bout et la disparition dans un
item de fonte, et rien au-dela.

## 6. Duree et faits pour le classement

Mesuree par `date` : debut **2026-10-27 14:49:11**, fin **14:55:58**, avant le commit. **Duree : 6 min 47 s**, dont la mesure du
test du paquet et celles des deux gardes ci-dessous.

Le garde-fou des identifiants et `.github/scripts/verifier-chemins.sh` sont lances sur l'arbre portant ce fichier ; leurs verdicts
sont ci-dessous. Aucun autre job de `.github/workflows/ci.yml` lance.

- `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false` :
  *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS` ; ce fichier n'ecrit aucun identifiant neuf.
- `.github/scripts/verifier-chemins.sh`, entree standard fermee : *"Aucun chemin de poste de travail ni nom de compte dans les
  fichiers verifies."*, sortie 0.

**Faits pour le classement par `camille-po`** : cette relecture ne porte que sur les lignes que j'ai prescrites (section 2) et sur les
gardes qu'en nomme ma section 5 ; elle ne relit aucun item hors des items 4, 8 et 12, ne pose aucune correction et n'ouvre aucun tour.
C'est la forme d'une seconde lecture au sens de `DEC-0120` A.272 (0,5 hors marge). Le classement est a `camille-po` ; je n'en convertis
aucune duree.
