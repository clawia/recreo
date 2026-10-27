# s57 - arbitrage des contradictions de `-10` (`ce1-qlm-paysages`) et `-12` (`ce1-qlm-generations`) avant premiers jets

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-27, debut 08:03:37 (UTC+01:00), lue par `date` ; fin en section 6.
- **Arbre** : branche `s57-p5-nadia-c1012`, base `round-two` a `fb689ee2`.
- **Entrees** : `arbitrage-nadia-premiers-jets-ce1-qlm-10-12.md` (mon arbitrage du 2026-10-26, cite *"mon 10-12"*) ;
  `avis-theo-palier-5-gardes-ce1-qlm-10-12.md` (conflits `C1` a `C6`, lecture de `G10-2`, `A6`, `A8`) ; l'annotation du 2026-10-26,
  huitieme, de `docs/backlog/backlog.md` (`Q8-5`, `Q8-6`) ; `QMT-CE1-1`, `PAY-CE1-1`, `GEN-CE1-1`, `GEN-CE1-2`
  (`squad/conformite/ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, 2 et 3.10, 3.12) ;
  `cp-frise-de-la-journee.yaml` (identifiants des moments servis, relus pour `C5`).
- **Type** : arbitrage prealable. **Aucun Done, aucun verdict, aucun identifiant de condition neuf.** Les codes `R10-*`, `R12-*`,
  `G10-*`, `G12-*` sont ceux de mon 10-12 ; `T1` a `T9` (ajustements demandes a `theo-archi`) sont des reperes de ce fichier. Je ne
  modifie ni story, ni paquet, ni test. Mon 10-12 n'est pas reecrit : ses textes corriges le sont ici, avec le marqueur
  *[corrige le 2026-10-27]* et le texte qu'ils remplacent.
- **Audit degrade, dit** : aucun paquet n'existe, rien a jouer. Je juge des formes d'items.

## Resume

| Point | Reponse | En une ligne |
|---|---|---|
| `Q8-5` (`-10`, *littoral*) | **reformulation, derogation limitee et dite** | l'explication de *littoral* vit dans les items B *sur le littoral* ; dans un item A ou *le littoral* est une proposition, l'indice ne l'explique pas, parce que l'expliquer donne ou ecarte une proposition |
| `Q8-6` (`-12`, intrus et college) | **contradiction reelle, corrigee** | les distracteurs des quatre questions *voisin* d'evenements sont fixes par une table : un moment d'enfance impose, un moment d'adulte au choix ; le college est donc dans chaque item A d'evenements |
| `C1` (echanges dans une suite de trois) | **theo a raison, ma sonde etait fausse** | distracteurs admis : les deux echanges de voisins et les deux rotations ; l'echange des extremes est l'inverse, refuse ; les deux echanges de voisins ne sont jamais ensemble |
| `C2` (prenoms) | **prenoms en tache B seulement** | en A, trois generations ou trois evenements ; en B, une suite de trois prenoms de l'enonce est admise |
| `C3` (mot de parente) | **non, refuse en proposition** | *la grand-mere* reste dans l'enonce, jamais dans une proposition ; `R12-3` corrige |
| `C4` (*un grand champ*) | **non, refuse** | aucun adjectif sur un element ou un paysage dans l'enonce ou une proposition ; la sonde de `G10-5` se lance seule |
| `C5` (moments de la journee) | **confirme** | les dix moments du paquet, plus *reveil* et *autrefois* |
| `C6` (*naissance d'un enfant*) | **refuse** | seule *la naissance de son enfant* ; *d'un enfant* se lit aussi comme la naissance du personnage |
| Lecture de `G10-2` | **confirmee : chacun** | chaque element partage nomme a au moins un de ses paysages en distracteur |
| `A6`, `A8` de theo | **confirmes ; `A8` encode** | voir `T8`, `T9` |
| Cout | **0,00** | decision de l'utilisatrice du 2026-10-27 |

---

## 1. Lot `-10`

### `Q8-5` - `QMT-CE1-1` contre `R10-4`. **Reformulation : la derogation existe, elle est limitee aux items ou elle s'impose.**

`camille-po` a raison : c'est une derogation a la lettre de `QMT-CE1-1`, et je la dis comme telle. Le motif : dans un item A dont la
bonne reponse est *le littoral*, l'enonce nomme un element propre du littoral (`R10-2`), et toute explication de *littoral* par des
exemples (*la mer*, *la plage*, *le sable*) designe la bonne reponse. L'indice cesse d'etre une methode (`TRV-CE1-5`) et devient la
reponse, le defaut **a** d'A.273 que j'ai trouve dix fois sur `-02` et `-03`. Entre deux conditions, je garde celle qui laisse l'item
etre une question.

**Correction de mon propre ecrit.** `R10-4` admettait l'explication *"dans un item ou le littoral n'est pas la bonne reponse"*, donc
aussi dans un item A ou *le littoral* est un distracteur. C'etait trop large : l'expliquer ecarte un distracteur, ce qui est une
demi-reponse, et le test transverse refuse deja l'indice qui ecrit *le littoral* quand c'est une proposition (avis de `theo-archi`,
1.4). Le seul item ou l'enfant lit *littoral* sans que l'expliquer touche une proposition est l'item B *"Lequel ne voit-on pas sur le
littoral ?"*. L'explication vit la.

**Ce qui en resulte pour `QMT-CE1-1`** : le mot est explique dans au moins un item de la ligne, et dans chaque item ou il est dans
l'enonce. Un enfant qui tombe sur un item A sans avoir vu un item B *sur le littoral* lit *le littoral* sans aide, et peut encore le
trouver par elimination des trois autres paysages, qu'il connait. **Aucun garde-fou ne verifie qu'il a vu l'explication avant** : le
tirage des manches ne le garantit pas, et je ne le demande pas.

Les exemples sont introduits par *par exemple* : une enumeration fermee (*"on voit la mer et des phares"*) pousserait vers l'element
qu'elle tait (*la plage*, s'il est un distracteur), alors que *par exemple* dit qu'il en existe d'autres.

### `C4` - *un grand champ*. **Refuse.**

`R10-1` libere l'article et le nombre, pas l'adjectif. Ma phrase de `R10-5` (*"Une taille n'en est pas un"*) disait qu'une taille n'est
pas un jugement ; elle se lisait comme une admission, et je la corrige. Un adjectif devant un element ajoute de la lecture sans rien
apprendre, et *grand* pres de *ville* ou de *champ* glisse vers la description qui hierarchise. La sonde *doit passer* de `G10-5` reste
juste pour `G10-5` seule (`T4`).

### Lecture de `G10-2`. **Confirmee : chacun.**

Un element partage dont aucun paysage n'est en distracteur ne designe, parmi les trois propositions, que la bonne reponse : il fait
office d'element propre dans l'item, et l'enfant peut repondre par lui sans lire l'element qui tranche. `PAY-CE1-1` veut que ce soit
l'element propre qui decide. Avec deux elements partages, chacun a un de ses paysages en distracteur.

### 1.1 Regles de contenu pour la story `-10` (texte exact, critere 9 ; `camille-po` reporte)

> **`R10-2` [corrige le 2026-10-27 par `nadia-prof`, `arbitrage-nadia-contradictions-ce1-qlm-10-12.md`, lecture de `G10-2` ; texte
> d'origine : *"Quand l'enonce nomme un element partage, au moins un distracteur est un paysage de cet element"*].** Pour **chaque**
> element partage que l'enonce nomme, au moins un distracteur est un paysage de cet element.
>
> **`R10-4` [corrige le 2026-10-27 par `nadia-prof`, meme fichier, `Q8-5` ; texte d'origine : *"Un indice est une methode
> (`TRV-CE1-5`) : il ne contient ni le paysage de la bonne reponse, ni un element propre de ce paysage (tache A), ni aucune des trois
> propositions (tache B). Littoral s'explique par des exemples (`QMT-CE1-1`) seulement dans un item ou le littoral n'est pas la bonne
> reponse, et ces exemples ne sont pas des propositions de l'item."*].** Un indice est une methode (`TRV-CE1-5`). En tache A, il ne
> contient aucun des quatre paysages ni un element propre du paysage de la bonne reponse ; il n'explique pas *littoral*. En tache B, il
> ne contient aucune des trois propositions. **Derogation a `QMT-CE1-1`, limitee** : *littoral* s'explique par des exemples dans les
> items B *"Lequel ne voit-on pas sur le littoral ?"*, et dans eux seuls. Le paquet en porte au moins un. Dans chacun, l'indice nomme
> *le littoral* et, apres *par exemple*, un ou deux elements propres du littoral qui ne sont pas des propositions de l'item
> (*"Sur le littoral, il y a par exemple la mer."*, 10 mots, quand *la plage* est le distracteur). Jamais une definition (*"le bord de
> la mer"*).
>
> **`R10-5`, derniere phrase [corrige le 2026-10-27 par `nadia-prof`, meme fichier, `C4` ; texte d'origine : *"Une taille n'en est pas
> un (une grande ville)."*].** Une taille n'est pas un jugement, mais elle n'est pas admise pour autant : **aucun adjectif** n'accompagne
> un element ou un paysage dans un enonce ou une proposition (ni *un grand champ*, ni *une grande ville*, ni *un haut sommet*).

Les autres regles de `-10` (`R10-1`, `R10-3`, `R10-6` a `R10-8`) sont inchangees.

---

## 2. Lot `-12`

### `Q8-6` - l'intrus de *"juste apres sa naissance"*, et le college. **Contradiction reelle, la mienne. Corrigee.**

**Correction de mon propre ecrit.** Mon 10-12 (section *Intrus sur -12*) dit que `R12-5` empeche l'intrus *"un moment d'enfance parmi
deux moments d'adulte"*. C'est faux pour deux des quatre questions admises. Sous *"juste apres sa naissance"*, `R12-5` admet *le premier
emploi* et *la naissance de son enfant* ; sous *"juste avant l'entree a l'ecole"*, il admet les memes. Dans les deux cas *l'entree a
l'ecole* ou *sa naissance* est le seul moment d'enfance, et l'enfant le trouve sans savoir ce qui vient juste apres ou juste avant.

Les quatre questions admises (`R12-6`) ont toutes pour bonne reponse un moment d'enfance (*sa naissance*, *l'entree a l'ecole*,
*l'entree au college*). La correction : un des deux distracteurs est toujours un autre moment d'enfance, que ni la reference ni la bonne
reponse n'occupent. Il n'en reste qu'un dans chaque cas, donc la table est fermee. L'autre distracteur est un moment d'adulte au choix.

| Enonce | Bonne reponse | Distracteur d'enfance (impose) | Distracteur d'adulte (au choix) |
|---|---|---|---|
| *juste apres sa naissance* | *l'entree a l'ecole* | *l'entree au college* | *le premier emploi* ou *la naissance de son enfant* |
| *juste avant l'entree a l'ecole* | *sa naissance* | *l'entree au college* | idem |
| *juste apres l'entree a l'ecole* | *l'entree au college* | *sa naissance* | idem |
| *juste avant l'entree au college* | *l'entree a l'ecole* | *sa naissance* | idem |

Verifie sur chaque ligne : aucun distracteur n'est juste avant ou juste apres la reference ; aucun ne repose sur l'ordre du premier
emploi et de la naissance de son enfant (`R12-6`), puisqu'un seul moment d'adulte est propose ; les trois propositions sont deux moments
d'enfance et un d'adulte, sans intrus.

**Le college** : *"admise, pas demandee"* (`R12-1`) voulait dire que `GEN-CE1-1` ne le nomme pas et que je l'admettais. Sous la table,
il est dans chaque item A d'evenements. `R12-1` est corrige.

### `C1` - quels echanges dans une suite de trois. **Theo a raison : ma sonde etait fausse.**

**Correction de mon propre ecrit.** La sonde *doit passer* de `G12-3`, *"un distracteur qui echange deux elements"*, inclut l'echange
du premier et du dernier, qui est l'ordre inverse, que `R12-2` refuse dans la meme story. Sur une bonne suite *a, b, c*, les cinq autres
ordres sont :

| Ordre | Nature | Admis |
|---|---|---|
| *b, a, c* | echange des deux premiers (voisins) | oui |
| *a, c, b* | echange des deux derniers (voisins) | oui |
| *b, c, a* | rotation | oui |
| *c, a, b* | rotation | oui |
| *c, b, a* | echange des extremes, l'inverse | **non** |

Une rotation n'est pas une bonne reponse sur un ordre lineaire (avis de `theo-archi`, 2.8) ; elle reste une erreur qu'un enfant fait
quand il place bien deux elements l'un par rapport a l'autre et mal le troisieme.

**Une regle de plus, avant le premier jet** : les deux distracteurs ne sont jamais les deux echanges de voisins ensemble. Avec *a, b, c*,
*b, a, c* et *a, c, b*, l'element de tete de la bonne reponse est en tete de deux propositions sur trois, et son dernier element en fin de
deux sur trois : l'enfant qui choisit la proposition la plus ressemblante aux deux autres trouve la reponse sans ranger. C'est le
defaut **b** d'A.273 sous une forme nouvelle. Les autres paires (deux rotations, ou une rotation et un echange de voisins) ne
designent pas la bonne reponse ainsi.

### `C2` - les prenoms. **En tache B seulement.**

En tache A, les trois propositions sont trois generations ou trois evenements (`R12-5`), et aucune forme d'enonce de `R12-2` ne demande
une personne. Je n'en ajoute pas : *"Qui est de la generation des grands-parents de Lina ?"* avec trois prenoms demande de tenir trois
relations pour designer un element seul, c'est l'operation de B sur une seule case. En tache B, une suite de trois prenoms de l'enonce,
chacun d'une generation differente, *du plus âgé au plus jeune*, est admise : c'est ce que `T12-2` supposait (*"que les relations
decrites dans l'enonce donnent un seul ordre des prenoms"*). Elle ne compte pas pour `FER-1` (`R12-3`).

### `C3` - le mot de parente. **Refuse en proposition.**

`R12-3` ecrivait qu'un mot de parente *"ne compte pas"*, ce qui le supposait admis. Ce n'etait pas voulu : *la grand-mere* en bonne
reponse transforme *"de quelle generation"* en *"qui est-elle pour Lina"*, une relation de parente et non une generation. Un mot de
parente vit dans l'enonce (*"Sara est la maman de Lina"*), jamais dans une proposition. `G12-2` le refuse deja.

### `C5` - les moments de la journee. **Confirme.**

Relu dans `cp-frise-de-la-journee.yaml` : dix moments servis (lever, petit-dejeuner, ecole du matin, dejeuner, ecole de l'apres-midi,
gouter, diner, se brosser les dents, pyjama, coucher). Ma parenthese de `G12-1` en nommait six ; *"tout mot des moments"* designait le
paquet, et c'est la liste du paquet qui fait foi. Les dix, plus *reveil* et *autrefois*.

### `C6` - *naissance d'un enfant*. **Refuse.**

`GEN-CE1-1` nomme l'evenement, `R12-1` en fixe l'ecriture. Dans *"la vie de Sara"*, *la naissance d'un enfant* se lit aussi comme la
naissance de Sara, qui a ete un enfant : c'est l'ambiguite que `R12-1` ferme pour les deux naissances. Seule *la naissance de son enfant*
entre dans un item. `GEN-CE1-1` n'est pas reecrit.

### 2.1 Regles de contenu pour la story `-12` (texte exact, critere 9 ; `camille-po` reporte)

> **`R12-1`, evenements [corrige le 2026-10-27 par `nadia-prof`, `arbitrage-nadia-contradictions-ce1-qlm-10-12.md`, `Q8-6`, `C6` ;
> texte d'origine : *"entree au college (admise, pas demandee)"*].** *Entree au college* n'est pas nommee par `GEN-CE1-1` ; elle est
> admise, et elle est dans chaque item A d'evenements (table de `R12-5`). Elle n'entre dans aucune suite de B. L'evenement de
> `GEN-CE1-1` *"naissance d'un enfant"* s'ecrit toujours *la naissance de son enfant*, jamais *la naissance d'un enfant*.
>
> **`R12-2`, B [precise le 2026-10-27 par `nadia-prof`, meme fichier, `C1`, `C2`].** Les distracteurs sont les trois memes elements
> dans un autre ordre : un echange de deux voisins ou une rotation, **jamais l'echange du premier et du dernier**, qui est l'ordre
> inverse. Les deux distracteurs ne sont jamais les deux echanges de voisins ensemble. Exemple, bonne reponse *grands-parents, parents,
> enfants* : *parents, grands-parents, enfants* et *parents, enfants, grands-parents*. Une suite est trois generations, la suite
> d'evenements admise, ou **trois prenoms de l'enonce, chacun d'une generation differente**, dans le sens *du plus âgé au plus jeune*.
>
> **`R12-3` [corrige le 2026-10-27 par `nadia-prof`, meme fichier, `C3` ; texte d'origine : *"Une suite de trois, un prenom ou un mot
> de parente ne compte pas."*].** Une suite de trois ne compte pas. Un prenom n'est jamais une proposition de A. Un mot de parente
> (*la grand-mère*, *le papa*) n'est jamais une proposition : il vit dans l'enonce.
>
> **`R12-5`, evenements [corrige le 2026-10-27 par `nadia-prof`, meme fichier, `Q8-6` ; texte d'origine : *"Exemple, juste avant
> l'entree au college : l'entree a l'ecole ; sa naissance (du meme cote, pas juste avant), le premier emploi (de l'autre cote). Quand
> rien n'existe de l'autre cote (juste apres sa naissance), les deux distracteurs sont du meme cote."*].** Pour les generations, la
> regle du meme cote est inchangee. Pour les evenements, les propositions des quatre questions de `R12-6` sont fixees par cette table,
> et par elle seule :
>
> | Enonce | Bonne reponse | Distracteurs |
> |---|---|---|
> | *juste après sa naissance* | *l'entrée à l'école* | *l'entrée au collège* ; *le premier emploi* ou *la naissance de son enfant* |
> | *juste avant l'entrée à l'école* | *sa naissance* | *l'entrée au collège* ; *le premier emploi* ou *la naissance de son enfant* |
> | *juste après l'entrée à l'école* | *l'entrée au collège* | *sa naissance* ; *le premier emploi* ou *la naissance de son enfant* |
> | *juste avant l'entrée au collège* | *l'entrée à l'école* | *sa naissance* ; *le premier emploi* ou *la naissance de son enfant* |
>
> Motif : la bonne reponse est toujours un moment d'enfance ; avec deux moments d'adulte en distracteurs, elle serait le seul moment
> d'enfance, et l'enfant la trouverait sans savoir ce qui vient juste avant ou juste apres.

Les autres regles de `-12` (`R12-4`, `R12-6` a `R12-9`) sont inchangees.

---

## 3. Ajustements demandes a `theo-archi` (forme de test ; la forme est la sienne)

| Code | Garde | Ce que je veux |
|---|---|---|
| `T1` | `G12-3` (`C1`) | sonde *doit passer* : un echange de voisins et une rotation ; sondes *refusees* : l'echange des extremes (l'inverse, deja refuse), et **un item dont les deux distracteurs sont les deux echanges de voisins** |
| `T2` | `G12-2` (`C2`) | le groupe `pre` n'est admis que dans un item `ordre`, ses trois parties toutes `pre` ; un item `voisin` dont une proposition est `pre` est refuse |
| `T3` | `G12-2` (`C3`) | refus de *la grand-mère* en proposition, tel que tu l'as ecrit |
| `T4` | `G10-1`, `G10-5` (`C4`) | refus de *un grand champ* par `G10-1`, tel que tu l'as ecrit ; la sonde *doit passer* de `G10-5` est appelee sur `G10-5` seule |
| `T5` | `G12-1` (`C5`) | les dix moments, plus *reveil* et *autrefois*, tels que tu les as ecrits |
| `T6` | `G12-4` (`C6`) | refus de *la naissance d'un enfant*, tel que tu l'as ecrit |
| `T7` | `G12-4` (`Q8-6`) | `VOISINS_ADMIS` porte la table de `R12-5` : (reference, sens, bonne reponse, distracteur d'enfance) et un second distracteur dans {*premier emploi*, *naissance de son enfant*} ; refus de tout autre jeu de propositions, dont *"juste apres sa naissance"* avec *le premier emploi* et *la naissance de son enfant* |
| `T8` | `G10-4`, `G10-7` (`Q8-5`) | en A, aucune forme des quatre paysages dans l'indice (ta forme ne refusait que celui de la bonne reponse) ; tout item B dont le lieu est *sur le littoral* a un indice qui contient *littoral* ; au moins un tel item dans le paquet. Que les exemples suivent *par exemple* et ne soient pas une definition, je le lis |
| `T9` | `A6`, `A8` | `A6` confirme tel quel (formes d'enonce A dans `G12-2` ; sens lie au groupe, rang croissant des generations et `SUITE_ADMISE` dans `G12-3`). `A8` : encode la premiere moitie de `R12-7` dans la forme de `G10-4` ; je la demande |

La lecture de `G10-2` (*chacun*) est confirmee : rien a changer.

**Ce dont je reste la seule garde, ajoute a ce que mon 10-12 et ton avis (section 5) listent** : que l'explication de *littoral* soit
faite d'exemples et non d'une definition ; qu'un item A ou *le littoral* est propose reste trouvable sans l'explication ; que les
relations d'une suite de prenoms donnent un seul ordre ; qu'un distracteur de rotation soit plausible pour les elements de l'item.

## 4. Ce que ce document ne fait pas

Il ne prononce aucun Done, n'ecrit aucune condition et n'alloue aucun identifiant. Il ne modifie ni story, ni paquet, ni test, ni
`GEN-CE1-1`, ni `QMT-CE1-1`. Il ne touche aucun fichier de `-04`, `-05`, `-02`, `-06`, `-08`. Les corrections de mon propre ecrit
(`R10-2`, `R10-4`, `R10-5`, `R12-1`, `R12-3`, `R12-5`, la sonde de `G12-3`, la section *Intrus sur -12*) sont datees ici ; mon 10-12
n'est pas reecrit.

## 5. Cout

**0,00**, selon la decision de l'utilisatrice du 2026-10-27 : les arbitrages avant premier jet ne sont pas chiffres.

## 6. Duree

Mesuree par `date` : debut **2026-10-27 08:03:37**, fin **08:14:54**, avant le commit. **Duree : 11 min 17 s**, dont la mesure
du garde-fou des identifiants (`bash outils/mvn-jdk21.sh -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` :
*"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`, ce fichier present dans l'arbre ; il n'ecrit aucun
identifiant neuf) et `.github/scripts/verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers
verifies."*

## 7. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | sections 1.1 et 2.1, dans le critere 9 de `-10` et de `-12`, par annotation datee |
| `theo-archi` | `T1` a `T9` |
| `marc-dev` | rien avant le report par `camille-po` |
| `sofia-sm` | la duree (section 6) |

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
