# s57 - arbitrage des indices de `ce1-qlm-etapes-de-developpement` (`S57-BR-QLM-CE1-01`, `P0`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-26, lecture de 12:44 a 12:52 (UTC+01:00), lue par `date`.
- **Arbre** : branche `s57-p5-nadia-indices`, base `round-two` a `5e5fdb7c`.
- **Entrees** : `relecture-redactionnelle-ce1-qlm-etapes-de-developpement.md` (`relecteur-pedagogique`, sections 1 et 3) et ma
  seconde lecture `relecture-nadia-corrections-ce1-qlm-etapes-de-developpement.md` (section 4).
- **Type** : arbitrage de cinq indices. **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune condition neuve. Je ne
  modifie ni le YAML ni un test : `marc-dev` applique.
- **Audit degrade, dit** : rien n'est joue, le paquet n'est pas au catalogue. Je lis le YAML et le test transverse.
- **Mesure lancee** : longueur des indices retenus par un script (Python, hors du depot, non commite) qui reprend la regle de
  `PaquetLu.mots` (decoupe sur espace et espace insecable, un segment sans lettre ni chiffre ne compte pas, donc `?` isole ne compte
  pas). Ce n'est pas le test : le chiffre qui fait foi est celui de
  `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest`, que `marc-dev` lance apres application.

## Resume

| Ligne | Item | Decision | Mots |
|---|---|---|---|
| 88 | 5 | **retenu tel que le relecteur le propose** | 9 |
| 130 | 11 | **reformule** : la proposition du relecteur, avec l'etape de reference a la place de l'espece | 12 |
| 151 | 14 | **retenu tel que le relecteur le propose** | 10 |
| 60, 67 | 1, 2 | **inchanges** | - |
| *"Remonte la vie du ..."* | 5, 11, 13, 14 | **comprise par un enfant de CE1** ; formule gardee | - |

## 1. Les trois indices *"Que vient (juste) avant ?"*

La construction est au `relecteur-pedagogique` et je la reprends : *"Qu'est-ce qui vient (juste) avant ?"* est la question correcte.
Ce que j'arbitre est le fond : ce que l'enfant comprend, et vers quoi l'indice le dirige.

### 1.1 Ligne 88, item 5 (grenouille, avant le tetard : *l'oeuf*) - retenu

L'indice nomme le tetard, qui est l'etape de reference de l'enonce. Il dit le sens (*avant*) sans dire l'etape. Aucune proposition
(*l'oeuf*, *la grenouille*, *la chrysalide*) n'y est ecrite. *"avant"* sans *"juste"* suffit ici : rien ne precede le tetard que
l'oeuf dans la vie de la grenouille, et l'item ne demande pas ce qui vient avant l'oeuf.

### 1.2 Ligne 130, item 11 (plante, avant la jeune pousse : *la graine*) - reformule

**Correction de mon propre ecrit.** Ma seconde lecture (section 4) disait des trois indices : *"la methode (remonter la vie de
l'espece) est juste"*. Pour celui-ci, ce n'est pas exact, et je ne l'avais pas vu. Les items 5 et 14 nomment l'etape de reference
(*le tetard* ; *le papillon*, qui est a la fois l'espece et l'etape). L'item 11 nomme **l'espece, et l'espece est elle-meme une etape
de la liste** (*la plante*, rang 2). Un enfant qui lit *"Remonte la vie de la plante. Qu'est-ce qui vient juste avant ?"* peut
comprendre *"juste avant la plante"* : la reponse serait alors *la jeune pousse*, qui n'est pas proposee. L'indice ne le trompe pas
vers un distracteur, mais il lui donne une autre reference que celle de la question, et c'est l'enfant deja hesitant qui lit
l'indice.

La correction ne change que le nom : l'indice nomme l'etape de reference, comme ceux des items 5 et 14. Je garde *"juste"* : dans le
cycle, le fruit vient avant la graine, donc avant la jeune pousse ; seul *"juste avant"* l'ecarte, comme le dit l'enonce.

Ce n'est pas une barre relevee pour `marc-dev` : la ligne m'etait soumise a l'arbitrage, et c'est ma propre appreciation de la
seconde lecture que je corrige.

### 1.3 Ligne 151, item 14 (papillon, avant le papillon : *la chrysalide*) - retenu

L'indice nomme le papillon, qui est l'etape de reference. *"juste"* est utile : l'oeuf et la chenille viennent aussi avant le
papillon, et *l'oeuf* est un distracteur de l'item.

## 2. *"Remonte la vie du ..."* (relecteur, section 3, premier point)

Le relecteur pose la question ; je n'en avais pas ecrit dans mes deux lectures, je la prends ici.

**Un enfant de CE1 la comprend.** *Remonter* est un mot qu'il connait (remonter l'escalier, remonter la rue), et en classe on dit
*remonter le temps* devant une frise, que le CE1 travaille (`QLM-CE1-12`, apres `QLM-CP-14`). Le referentiel n'ecrit pas la
formule : c'est ma pratique, pas une ligne du programme. Sur les lignes 88, 130 et 151, la seconde phrase dit
*"avant"* : l'image est expliquee dans le meme indice. La ligne 144 (item 13, *"Remonte la vie de la jeune grenouille pas à pas."*)
ne porte pas *"avant"*, mais l'enonce de l'item le dit (*"quelle étape vient juste avant la jeune grenouille ?"*) et l'indice est lu
apres l'enonce. Je garde la formule partout, la ligne 144 comprise.

Ce que je regarde en jouant, sans le demander : si un enfant hesite sur *"Remonte"* a la ligne 144. Si oui, ce sera une reserve
ecrite a ce moment-la.

## 3. Lignes 60 et 67 (relecteur, section 3, second point)

*"Suis la vie du têtard pas à pas, vers la suite."* (item 1) et *"Suis la vie du papillon pas à pas, vers la suite."* (item 2) :
**inchanges**.

L'indice n'a pas a dire quelle etape chercher : la dire serait donner la reponse. Il donne une methode, que l'enfant peut appliquer
seul - reciter les etapes une a une, dans le sens du temps - et le sens (*vers la suite*). C'est ce que je demande a un indice.
Le cas n'est pas celui de l'item 11 : *"pas à pas, vers la suite"* ne pose pas une relation precise (*juste apres*) sur un autre nom
que celui de l'enonce. L'enonce reste la reference, l'indice dit comment avancer.

## 4. Texte exact a appliquer (`marc-dev`)

Trois lignes du YAML, et elles seules. L'espace avant `?` est l'espace insecable U+00A0, celui que le fichier emploie deja
(`TYP-1`, `ADR-0067`). L'apostrophe de `Qu'est-ce` est l'apostrophe droite.

| Ligne | Texte actuel | Texte a ecrire |
|---|---|---|
| 88 | `Remonte la vie du têtard. Que vient avant ?` | `Remonte la vie du têtard. Qu'est-ce qui vient avant ?` |
| 130 | `Remonte la vie de la plante. Que vient juste avant ?` | `Remonte la vie de la jeune pousse. Qu'est-ce qui vient juste avant ?` |
| 151 | `Remonte la vie du papillon. Que vient juste avant ?` | `Remonte la vie du papillon. Qu'est-ce qui vient juste avant ?` |

Controles, a confirmer par le test transverse apres application :

- **Longueur** (`TRV-CE1-5`, douze mots au plus) : 9, 12 et 10 mots par mon script. **La ligne 130 est a la limite**, sans marge.
  Si le test la compte au-dessus de douze, ne pas retirer *"juste"* (section 1.2) : me le signaler.
- **Aucune proposition dans l'indice** : *la jeune pousse* n'est pas une proposition de l'item 11 (*la graine*, *la fleur*,
  *le fruit*) ; les items 5 et 14 n'ecrivent aucune de leurs propositions.
- **Indices distincts** : les trois sont distincts entre eux et des douze autres ; la ligne 130 differe de la ligne 144
  (*jeune pousse* contre *jeune grenouille*).
- **Rien d'autre ne change** : ni enonce, ni proposition, ni declaration de famille. L'entree de rang garde ses totaux.

## 5. Cout de `P0`

Cet arbitrage porte sur cinq indices, pas sur le paquet. Je le compte comme je l'avais annonce (seconde lecture, section 6) : s'il
est classe comme une troisieme lecture du lot, `P0` atteint 2,0 et depasse 1,67. Le classement est a `camille-po` (A.244). La
correction de la ligne 130 ne demande pas de nouvelle lecture du paquet : je relirai ces trois lignes, et elles seules, sur le
commit de `marc-dev`.

## 6. Ce dont je reste la seule garde

Qu'un indice nomme la meme reference que son enonce : aucun test ne le verifie, c'est ce qui a passe ma seconde lecture sur la
ligne 130. La methode de chaque indice ; la comprehension de *"Remonte"* a la ligne 144, regardee en jouant. Le reste de la liste de
ma seconde lecture (section 7) est inchange.

## 7. Routage

| A qui | Quoi |
|---|---|
| `marc-dev` | appliquer les trois lignes de la section 4, lancer le test transverse, recopier son verdict |
| `relecteur-pedagogique` | rien : sa construction est reprise sur les trois lignes |
| `sofia-sm`, `camille-po` | classement de cet arbitrage dans `P0` (section 5) |

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-26.
