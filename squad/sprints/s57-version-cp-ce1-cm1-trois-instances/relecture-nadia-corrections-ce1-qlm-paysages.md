# s57 - relecture des corrections de `ce1-qlm-paysages` (`S57-BR-QLM-CE1-10`, `P5`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : debut 2026-10-27 14:49:41 (UTC+01:00), ecriture 14:54:29, lues par `date` ; fin en section 7.
- **Arbre** : branche `s57-p5-nadia-corr10`, base `round-two` a `0286fde1`, lue par `git`.
- **Lu** : `git diff 7cc77996 HEAD` sur `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-paysages.yaml`, un seul
  commit (`beb1baf5`, `[MARC]`) ; le meme diff sur `PaquetCe1QlmPaysagesTest.java` et `paysages-fautifs.yaml` (sondes 70 a 80) ;
  pour l'unicite et `F3`, les lignes `enonce`, `distracteurs` et `indice` du paquet et sa liste `R10-1` (`:29-36`), par `grep`.
  Compare a mes sections 5 et 6 de `lecture-nadia-premier-jet-ce1-qlm-paysages.md`. **Je n'ai pas relu le paquet.**
- **Type** : relecture des lignes corrigees, avant `verify`. **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune
  condition neuve. Je n'ai modifie ni le paquet, ni les tests. `L1`..., `T1`... sont les reperes locaux de ma lecture.
- **Audit degrade, dit** : rien n'est joue a l'ecran (paquet hors catalogue). Les items 3, 6, 7 et 9 sont joues sur le papier.
- **Mesures lancees** :
  - comparaison caractere par caractere des lignes du diff par script (Python, hors du depot, non commite), `repr` de chaque ligne ;
  - `bash outils/mvn-jdk21.sh -B -q -pl adapters/adapter-content -am test -Dtest='PaquetCe1QlmPaysagesTest,ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest' -Dsurefire.failIfNoSpecifiedTests=false`,
    sortie 0, `java.version=21.0.10` (rapport surefire) : **58 tests, 0 echec** pour le paquet ; **18 tests, 0 echec** pour le
    transverse. Perimetre : ces deux classes, module `adapter-content` et ses dependances. Ce n'est ni un `verify`, ni la porte
    technique.

## Resume

| Point | Reponse |
|---|---|
| `L1` a `L5` | **tous tiennent**, au caractere pres ; espaces insecables avant *?* (items 3, 6) et avant *:* (item 9) presentes (section 1) |
| Perimetre du diff | **seules les lignes prescrites changent** : 10 ajouts, 7 retraits, tous dans ma section 5 (section 2) |
| `T1` a `T5` et sondes 70 a 80 | **tiennent** (section 3) |
| Point (a), question de l'item 6 | **ma colonne "Avant" etait fausse**, pas l'application de `marc-dev`. La question *"Dans quel paysage est-on ?"* n'etait pas voulue ; je la garde, aucun texte a demander (section 4) |
| Point (b), ligne `CYC-1` | **rien a ajouter** : elle reste vraie, et la phrase neuve de `R10-2` dit deja que *le port* n'entre dans aucun enonce de A (section 4) |
| Unicite, items 3 et 6 | **tient** ; *le littoral* et *la ville* ne sont proposes ensemble dans aucun des huit items A (section 5) |
| Faux rouge `F3` | **confirme** : aucun indice de `-10` ne porte *ferm* sous aucune forme (section 5) |
| Ouverture | **aucune** (section 6) |
| Duree | section 7 |

## 1. Comparaison a ma section 5, element par element

| Repere | Ligne | Verdict | Motif |
|---|---|---|---|
| `L1` | item 3, `:94` : *"On voit une plage et une falaise. Quel est ce paysage ?"* | **tient** | Identique a ma colonne "Apres" ; espace avant *?* en `U+00A0` |
| `L1` | item 3, `:96` : `["la montagne", "la campagne"]` | **tient** | Identique ; ancien `["la ville", "la montagne"]` absent |
| `L1` | item 3, commentaire *TRV-CE1-2 : a, b* | **tient** | Inchange, comme prescrit : *la montagne* porte *la falaise* (a), *la campagne* non (b) |
| `L2` | item 6, `:109` : *"On voit des immeubles et une rivière. Dans quel paysage est-on ?"* | **tient** | Identique a ma colonne "Apres" ; *rivière* accentue ; espace avant *?* en `U+00A0`. Ecart de ma colonne "Avant" : section 4 |
| `L2` | item 6, `:111` : `["la montagne", "la campagne"]` | **tient** | Identique ; ancien `["le littoral", "la campagne"]` absent |
| `L2` | item 6, commentaire *TRV-CE1-2 : a, a* | **tient** | Identique ; les deux distracteurs portent *la rivière* |
| `L3` | item 7, `:117` : *"Pense d'abord au mot qui ne va qu'avec un seul paysage."* | **tient** | Identique ; apostrophes droites, aucune ponctuation double |
| `L4` | item 9, `:127` : *"Un des mots ne va qu'avec un seul paysage : trouve-le."* | **tient** | Identique ; espace avant *:* en `U+00A0`, trait d'union simple dans *trouve-le* |
| `L5` | en-tete, `:46-48` | **tient** | Ma phrase de `R10-2`, mot pour mot, sans italique et sans accent comme le reste de l'en-tete ; espace ordinaire avant *:*, comme toutes les autres lignes de commentaire, qui ne sont pas lues par l'enfant |

Les indices des items 3 et 6 restent, comme prescrit. Joues sur le papier : l'enfant qui choisit *la montagne* a l'item 3 parce
qu'il y voit une falaise est ramene par *"Pour chaque paysage, regarde si on y voit tous les mots."* : pas de plage a la montagne.
A l'item 6, *"Écarte les paysages où l'on ne voit pas tous les mots."* ecarte *la montagne* et *la campagne*, qui ont une riviere et
pas d'immeubles. A l'item 7, *la mer* est le mot qui ne va qu'avec un seul paysage ; a l'item 9, *les pistes de ski* : les deux
methodes discriminent, ce que l'ancien indice de l'item 9 ne faisait pas. L'indice de l'item 7 se lit d'une traite au CE1.

## 2. Perimetre du diff

`git diff --stat 7cc77996 HEAD` sur le YAML : *"1 file changed, 10 insertions(+), 7 deletions(-)"*, un seul commit (`beb1baf5`). Les
10 ajouts : 3 lignes d'en-tete (`L5`), 2 lignes de l'item 3 (`L1`), 3 lignes de l'item 6 (`L2`, commentaire compris), 1 indice
(`L3`), 1 indice (`L4`). Les 7 retraits sont leurs anciennes versions. **Rien d'autre ne change** : ni bonne reponse, ni autre item,
ni ligne de compte, ni `CYC-1`.

## 3. Les gardes de `theo-archi`

| Repere | Ajustement lu | Verdict | Motif |
|---|---|---|---|
| `T1` | `G10-2` : pour chaque partage nomme, `PARTAGE.get(element)` contient la bonne reponse ; sonde 70 | **tient** | Sonde 70 = ma sonde, mot pour mot (*"On voit un sommet et un port. Quel est ce paysage ?"*, *la montagne* ; *la ville*, *la campagne*). Elle n'est refusee que par l'assertion neuve : *sommet* est un propre de la bonne reponse, *la ville* porte *le port*, *le littoral* n'est pas propose. Le message exige *"que le paysage de la bonne réponse"*. Passe : l'item 1, par `verifierLesItemsDeLaTachePaysage(paquet().items())` (`:699`). La Javadoc ne liste plus ce point parmi les non-gardes |
| `T2` | `G10-2` : refus quand les propositions contiennent *le littoral* et *la ville* ; sondes 71, 72 | **tient** | Sondes 71 et 72 = items 3 et 6 du premier jet, tels quels. Passe : l'item 3 corrige, par le meme appel sur le paquet, vert |
| `T3` | `G10-4` B : `ELEMENT_DANS_UN_INDICE` sur l'indice entier hors littoral, sur l'indice prive de la zone *par exemple* sur le littoral ; sondes 73, 74, 75 | **tient** | Sonde 73 = ma sonde (*"Pense à la neige."*, item a la montagne). 74 couvre le littoral hors de l'exemple, 75 croise `T5`. `sansLExemple` retire la zone que lit `G13`, du *par exemple* au premier point. Passent : les items 2 et 5, par `verifierLesIndices(paquet().items())` (`:709`) |
| `T4` | `FORME_PAYSAGE` gagne *littoraux* et *campagnes* ; sondes 76, 77 | **tient** | Sonde 76 = ma sonde (*"Compare les campagnes."*) ; 77 pour *littoraux* |
| `T5` | `FORMES_DE_L_INDICE_SEULEMENT` = *piste*, *pistes*, *ski*, ajoutees a la seule recherche dans un indice ; sondes 78, 79, 80 | **tient** | Sonde 78 = ma sonde (*"Regarde s'il y a des pistes."*). La sonde 80 montre que *pistes* seul reste refuse dans un enonce (`G10-1`) : la liste n'entre pas dans `FORME_ELEMENT`, comme demande |

`T6` (aucune garde) : aucune liste ne gagne *seul*. **Tient.**

## 4. Les deux points a trancher

**(a) La question de l'item 6. Je corrige mon propre ecrit d'abord.** Ma colonne "Avant" de `L2` ecrivait *"On voit des immeubles et
un port. Dans quel paysage est-on ?"*. Le fichier, a `7cc77996`, portait *"... Quel est ce paysage ?"* (ligne retiree du diff, lue par
`repr`). L'erreur est dans ma recopie, et `marc-dev` a fait exactement ce que ma colonne "Apres" demandait : il n'y a rien a lui
reprocher. Ma seule intention etait de changer les elements, pas la question.

**Je garde *"Dans quel paysage est-on ?"* et ne demande aucun texte.** Les deux questions sont celles du paquet depuis le premier jet,
lisibles au CE1, et posent la meme tache : le changement ne touche ni la competence, ni l'unicite, ni un indice. Le partage passe de
4 et 4 a 3 *"Quel est ce paysage ?"* (items 1, 3, 4) et 5 *"Dans quel paysage est-on ?"* (items 6, 7, 9, 11, 13) ; aucune regle ne
demande d'alterner, et aucune question ne designe un paysage. Renvoyer l'item pour retablir l'ancienne question couterait un tour sans
rien apprendre de plus a l'enfant.

**(b) La ligne `CYC-1`.** Elle dit que `cm1-histoire-geographie-nommer-en-geographie` classe un port dans un village de peche et
qu'*"aucun item ne le contredit"* : c'est toujours vrai, et plus encore qu'avant. La phrase neuve de `R10-2` (`:46-48`) dit deja que
*le port* n'entre dans aucun enonce de A, et aucun item B ne l'a jamais porte. Ajouter la meme chose a `CYC-1` repeterait l'en-tete.
**Je ne veux rien ajouter** ; la ligne reste en l'etat.

## 5. Ce que les corrections doivent tenir

**Unicite, items 3 et 6**, contre `R10-1` (`:31-36`) :

- item 3 : *la plage* est un propre du littoral, *la falaise* est du littoral et de la montagne. *La montagne* a la falaise et pas
  la plage, *la campagne* n'a ni l'une ni l'autre. **Une seule reponse : *le littoral*.** *La ville* n'est pas proposee ;
- item 6 : *les immeubles* sont un propre de la ville, *la rivière* est de la montagne, de la campagne et de la ville. Les deux
  distracteurs ont la riviere et pas d'immeubles. **Une seule reponse : *la ville*.** *Le littoral* n'est pas propose.

**Le littoral et la ville jamais ensemble en A**, lu sur les huit lignes `distracteurs` des items A avec leur bonne reponse : items 1
(montagne ; campagne, ville), 3 (littoral ; montagne, campagne), 4 (campagne ; montagne, ville), 6 (ville ; montagne, campagne),
7 (littoral ; montagne, campagne), 9 (montagne ; campagne, littoral), 11 (campagne ; montagne, ville), 13 (ville ; montagne,
campagne). **Aucun item ne propose les deux.** *Le port* n'apparait dans aucun enonce ni aucune proposition (`grep`, seulement en
commentaire). `T2` le garde desormais par test.

**Faux rouge `F3`.** Les treize indices de `-10` (`:87` a `:147`) ne contiennent *ferm* sous aucune forme (`grep -ci 'ferm'` sur les
lignes `indice` : 0). *Une ferme* n'est que dans les enonces des items 9 et 11, au sens du nom, et aucun texte du paquet n'ecrit
*fermé* ni *fermer*. **Aucun indice de `-10` n'est concerne.**

## 6. Ouvertures

**Aucune.** Aucun item, aucune formulation n'est rouvert. La seule difference avec mon texte est une recopie fausse de ma colonne
"Avant" (section 4) : elle porte sur un fait de ma lecture, pas sur une regle, et je n'en tire aucune correction. Les deux phrases a
reporter par `camille-po` restent celles de ma lecture, section 7 ; cette relecture n'en ajoute pas.

**Ce dont je reste la seule garde sur `-10`, apres cette relecture** : qu'un indice soit une methode et non une description de la
scene ou une periphrase par un mot hors des listes ; qu'une methode discrimine vraiment ; qu'un indice se lise au CE1 ; qu'une
explication de *littoral* ne porte pas aussi une definition ; un adjectif dans un indice hors de l'exemple ; un jugement hors des 48
formes ; un lieu reel ; l'exactitude de chaque element et de chaque scene au CE1 ; `CYC-1`, `NRC-CE1-1`, `TRV-CE1-6`, `FER-3`.
**Aucune n'est couverte par un test.** L'unicite entre *le littoral* et *la ville* et l'appartenance de la bonne reponse a chaque
partage nomme sont desormais gardees (`T1`, `T2`).

## 7. Duree et faits pour le classement

Mesuree par `date` : debut **2026-10-27 14:49:41**, ecriture de ce fichier 14:54:29, fin **14:57:23**, avant le commit.
**Duree : 7 min 42 s**, dont la mesure des deux tests et l'execution des deux garde-fous ci-dessous. Aucun seuil de duree ne
s'applique a une relecture.

**Verifications avant commit**, ce fichier present dans l'arbre. Il n'ecrit ni ne renomme aucun identifiant de condition : `L1`,
`T1`, `F3` sont des reperes de demande.

- `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false` :
  *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`. Perimetre : ce seul test, module `bootstrap` et ses
  dependances.
- `.github/scripts/verifier-chemins.sh`, entree standard fermee, ce fichier indexe : *"Aucun chemin de poste de travail ni nom de
  compte dans les fichiers verifies."*
- Aucun autre job de `.github/workflows/ci.yml` lance.

**Faits pour le classement par `camille-po`** : cette relecture ne porte que sur les lignes que j'ai prescrites (`L1` a `L5`,
section 2) et sur les gardes que j'ai demandees (`T1` a `T6`) ; les lectures de `distracteurs` et d'`indice` des autres items ne
servent qu'a verifier l'unicite et `F3`, et ne rejugent aucun item. Aucun verdict hors des lignes prescrites n'est rouvert, aucune
correction n'est posee, aucun tour n'est ouvert. C'est la forme d'une seconde lecture au sens de `DEC-0120` A.272 (0,5 hors marge).
Le classement est a `camille-po` ; je n'en convertis aucune duree.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
