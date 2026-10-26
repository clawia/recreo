# s57 - relecture des trois indices corriges de `ce1-qlm-etapes-de-developpement` (`S57-BR-QLM-CE1-01`, `P0`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-26 (UTC+01:00), bornes lues par `date` : lecture des trois lignes et du diff de 13:11:12 a 13:12:40 ;
  redaction et controles jusqu'a 13:20:16.
- **Controles lances sur ce fichier** : `verifier-chemins.sh` (aucun chemin de poste) ;
  `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (`-pl bootstrap -am`) : `Tests run: 10, Failures: 0, Errors: 0, Skipped: 0`.
  Aucun autre job de la CI n'est lance.
- **Arbre** : branche `s57-p5-nadia-c1c3`, base `round-two` a `3f4da89d` (contient `5e56ea7e` de `marc-dev`, A.268 et A.269).
- **Entrees** : DEC-0120 A.268 et A.269 ; mon arbitrage `arbitrage-nadia-indices-ce1-qlm-etapes-de-developpement.md` (sections 4
  et 5) ; les lignes 88, 130 et 151 de `ce1-qlm-etapes-de-developpement.yaml`, et elles seules.
- **Type** : relecture de trois lignes, routee par A.268 et A.269. **Aucun Done, aucun verdict de lot**, aucun identifiant neuf.
  C1, C2, C3, `P0` et `P3` sont les reperes locaux deja ecrits (A.268, `planning.md`).
- **Audit degrade, dit** : rien n'est joue. Je lis trois lignes du YAML, octet par octet.

## Resume

| Point | Reponse |
|---|---|
| Trois lignes conformes au texte a ecrire | **oui**, caractere pour caractere, espace insecable U+00A0 avant `?` et apostrophe droite |
| C1 | **vraie** |
| C2 | **vraie** |
| C3 | **vraie** |
| Cout de `P0` | **1,5**, seuil de 1,67 non franchi |
| Formulation rouverte | **aucune** ; pas de second arbitrage |
| Marge (A.269, Q3) | **confirmee** : la seconde lecture de `-01` prend 0,50 sur la marge de 1,5, il reste 1,0 |

## 1. Les trois lignes

Methode : un script Python hors du depot, non commite, lit le fichier en UTF-8, extrait la valeur entre guillemets des lignes 88, 130
et 151 et la compare a une chaine construite avec U+00A0 pour l'espace avant `?` et `'` (U+0027) pour l'apostrophe. Les octets
ont ete lus a part (`od -c`) : `302 240` (U+00A0) avant `?` sur les trois lignes, `'` droite dans `Qu'est-ce`.

| Ligne | Valeur lue (l'espace avant `?` y est U+00A0) | Egale au texte a ecrire |
|---|---|---|
| 88 | `Remonte la vie du têtard. Qu'est-ce qui vient avant ?` | oui |
| 130 | `Remonte la vie de la jeune pousse. Qu'est-ce qui vient juste avant ?` | oui |
| 151 | `Remonte la vie du papillon. Qu'est-ce qui vient juste avant ?` | oui |

**Correction de mon propre ecrit.** Dans mon arbitrage, section 4, la colonne *"Texte a ecrire"* porte entre accents graves une
espace ordinaire (U+0020) avant `?`, lue aux octets ; la phrase qui precede le tableau prescrit l'espace insecable U+00A0. Le YAML
suit la phrase, qui est la prescription, et le fichier l'emploie deja (`ADR-0067`). L'ecart est dans mon tableau, pas dans la
correction de `marc-dev`. Le fichier d'arbitrage n'est pas modifie ; l'ecart est dit ici.

Relues, les trois lignes ne me rouvrent aucune formulation : elles sont celles que j'ai arbitrees, au mot pres. Il n'y a pas de
second tour d'arbitrage, celui qu'A.269 ne declare pas couvert.

## 2. C1, C2, C3

**C1 - vraie.** J'ai lu les lignes 88, 130 et 151 du YAML et aucune autre : ni enonce, ni proposition, ni autre indice. Le diff de C3
a affiche les lignes retirees et ajoutees, rien d'autre du fichier. Je n'ai pas relu le paquet.

**C2 - vraie.** Seule la formulation des trois indices change : le diff (C3) ne touche aucun enonce, aucune proposition, aucune bonne
reponse, aucun autre indice. Aucun des 15 verdicts *juste* de ma seconde lecture n'est rouvert, ni par l'arbitrage ni par cette
relecture. Je reprends la precision d'A.269 sans la discuter : pour la ligne 130, l'arbitrage a juge une methode et corrige une
appreciation de ma seconde lecture (section 4), qui n'etait pas l'un des 15 verdicts. Le motif *"ne juge aucun item"* d'A.268 est
inexact sur ce point, comme A.269 l'a marque ; la condition, elle, tient a la lettre.

**C3 - vraie.** Commande lancee, en lecture seule : `git diff 47279e05 HEAD -- adapters/adapter-content/src/main/resources/contenu/ce1-qlm-etapes-de-developpement.yaml`.

- `--numstat` : `3	3`, trois lignes retirees, trois ajoutees ;
- trois blocs, `@@ -88 +88 @@`, `@@ -130 +130 @@`, `@@ -151 +151 @@` ;
- un seul commit touche le fichier dans cet intervalle : `5e56ea7e` (`marc-dev`).

## 3. Cout de `P0`

Les trois conditions sont vraies : le classement d'A.268 tient, l'arbitrage compte 0,0.

| Ligne | Bareme [calcul] |
|---|---|
| lot, lecture du premier jet et correction qui la suit | 1,0 |
| seconde lecture avant `verify`, sur la marge | 0,5 |
| arbitrage des trois indices | 0,0 |
| relecture des trois lignes (le present fichier) | 0,0 : c'est la verification de C1, pas une lecture du paquet |
| **cout de `P0`** | **1,5** |

Seuil de 1,67 non franchi, 0,17 de reserve [calcul]. Ce chiffre est ecrit avant que `P1` commence, comme A.268 le demande.

## 4. La marge

**Je confirme A.269, Q3.** La seconde lecture de `-01` est prise sur la marge de 1,5 de l'instance neuve : 0,50, il reste 1,0, soit
deux rejeux a 0,50 [calcul]. A.269 a lu mes textes comme je les ai ecrits, et j'ai verifie ses deux citations au disque :
`arbitrage-nadia-palier-5-lp7-d5.md`, ligne 262 (*"n'est pas dans le lot : elle va sur la marge"*) ;
`reponse-nadia-palier-5-rejeu-horloge-et-double-compte.md`, section 1.1 (*"La marge est celle d'une instance"*). Il n'y a pas d'autre
imputation possible dans mes textes : la marge de B est celle de l'instance de l'horloge, elle ne paie pas un rejeu d'un lot de
l'instance neuve.

**Consequence sur `P3`** (texte en vigueur, `planning.md`, ligne 2468). Le texte n'est pas reecrit et sa regle ne change pas :

- *"autant que la marge en paie"* gouverne : deux rejeux sur la marge de 1,5, et non trois. La parenthese *"(trois a 1,5, au prix de
  0,50 du rejeu, A.199)"* decrivait la marge entiere ; elle n'est plus le compte courant. Je ne releve ni n'abaisse aucune barre :
  c'est la regle ecrite appliquee a une marge entamee.
- **un rejeu au plus par lot dans le palier 9** : inchange.
- **aucun rejeu avant le premier audit de chacun des dix lots du palier** : inchange.
- un troisieme rejeu sur l'instance neuve sort de la marge et va au palier 10, sauf le cas qu'A.269 decrit (horloge rejouee sur la
  marge de B).

L'utilisatrice a dit *"trois rejeux pour les dix lots"* (`planning.md`, section 2 de l'amendement correspondant). Je ne dis pas si
elle les voulait garantis quand la marge est entamee. L'ecart ne mord que si trois lots de l'instance neuve sont refuses hors
horloge ; c'est alors a `sofia-sm` de le lui porter, comme A.269 l'ecrit.

## 5. Ce dont je reste la seule garde

Inchange depuis mon arbitrage, section 6 : qu'un indice nomme la meme reference que son enonce (aucun test ne le verifie), la
methode de chaque indice, la comprehension de *"Remonte"* a la ligne 144, regardee en jouant. La longueur de la ligne 130 (douze
mots, a la limite) est au test transverse que `marc-dev` lance ; je ne l'ai pas relancee ici.

## 6. Routage

| A qui | Quoi |
|---|---|
| `sofia-sm` | lire le cout de `P0` : 1,5 ; la ligne de registre de l'instance neuve porte 0,50 de marge consomme par la seconde lecture de `-01`, reste 1,0 |
| `camille-po` | rien a reclasser : pas de second arbitrage ; C1 et C3, que A.269 laissait a verifier, sont vraies |

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-26.
