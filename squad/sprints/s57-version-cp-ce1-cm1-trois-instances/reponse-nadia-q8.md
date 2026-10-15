# s57, palier 1 - `nadia-prof` : `Q-T8`, "tous ses items" de `bandit-manchot-additions-cp`

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-15, 14:33 (UTC+02:00), lu par `date`.
- **Arbre** : branche `s57-nadia-q8`, worktree dedie, partie de `round-two` a `cb28115b`.
- **Lu** : `avis-theo-poste-8-art-cp-10.md`, annexe D (`TD4`, `TD5`, D.3, `Q-T8`) ; `reponse-nadia-q-palier-1.md` section 1
  (`Q-R5`) ; `reponse-nadia-forme2-palier-1.md` section 5.3 ; `docs/backlog/backlog.md`, `S57-TPH-CE1-R1` et l'amendement
  `Q-R4` de son critere 3 ; `cp-additions-jusqu-a-10.yaml` ; `catalogue.yaml` (entree `bandit-manchot-additions-cp`) ;
  `AdditionsBorneesContentSource.java` ; `BanditManchotSession.java` ; `HasardConfiguration.java` ; `fragments/manche.html` ;
  `PartieController.java` (messages, lignes 61 a 88) ; `recreo.css` (lignes 609 a 641) ; la fiche de
  `bandit-manchot-verbe-sujet-ce1`, section 1 du rejeu du 2026-10-14.
- **Rien joue, aucun jar construit.** Ce que je ne peux pas dire sans mesure est dit en section 4.

## Resume

| Code | Point | Decision |
|---|---|---|
| N1 | `Q-T8` | **La mesure complete par script : les 65 enonces de `TD4`, mesures comme `TD5` le decrit.** Ni echantillon, ni partie d'essai qui en tiendrait lieu |
| N2 | Correction de mon constat | *"tous ses items"* a ete ecrit sans lire que le paquet est tire. `TD4` en est la lecture, je la fais mienne |
| N3 | Ce que le fichier de cote ecrit en plus | les trois propositions servies avec chaque cote, et les deux conditions de `Q-R5` |

**Motif, en une phrase** : un echantillon ne suffit que si l'on sait a quelles longueurs la forme reagit, ce qu'on ne saura qu'une
fois la forme ecrite, alors que les 65 enonces ne dependent de rien et ne coutent qu'un temps de script, du meme ordre que les
27 parties scriptees de mon rejeu de `verbe-sujet` du 2026-10-14.

## 1. Correction de mon propre constat, avant d'arbitrer

J'ai ecrit *"`bandit-manchot-additions-cp`, tous ses items"* le 2026-10-15 (`reponse-nadia-forme2-palier-1.md`, section 5.3) sans
ouvrir le paquet : il ne liste aucun item, il declare `sommeMaximale: 10` et un seul indice. `camille-po` l'a vu et a pose `Q-R6`.
Mon motif d'alors etait *"le plus jeune lecteur, propositions courtes"* : un jeu de CP, pas une liste d'additions.

**`TD4` est la bonne lecture, et je ne la deplace pas.** Les couples `(a, b)` avec `a + b <= 10`, `(0, 0)` excepte : verifie sur
`AdditionsBorneesContentSource.java` lignes 56 a 67, la somme de `11 - a` pour `a` de 0 a 10 vaut 66, moins 1, **65**.
L'ensemble est derive du paquet et du code, il ne se recopie pas.

## 2. Ce qui change d'un item a l'autre sur l'ecran mesure

Lu dans le gabarit, le controleur, le catalogue et la feuille, apres une erreur, au-dessus du bas de `.indice` :

| Bloc | Varie selon l'item ? | Source |
|---|---|---|
| `.score` | non : un chiffre de 0 a 9 avant la fin de la partie | `manche.html` ligne 36 |
| `.bloc-adulte`, `.texte-support`, tableau, illustration | absents : le jeu n'en declare aucun | `catalogue.yaml` lignes 3 a 8 ; `Question` construite sans, ligne 113 |
| `.consigne` | non : *"Combien ça fait ?"* | `catalogue.yaml` ligne 5 |
| `.enonce` | **oui, par sa longueur** : 5 signes, 6 pour `10 + 0` et `0 + 10` seulement | ligne 115 |
| `.proposition`, trois | **oui, par leur longueur** : de 0 a 12, un ou deux chiffres ; de zero a trois nombres a deux chiffres (trois seulement pour `10, 11, 12`) | lignes 136 a 151 |
| `.verdict` apres une erreur | non : une seule constante | `PartieController.java` ligne 78 |
| `.indice` | non : *"Compte sur tes doigts."* | paquet ligne 13 |

Sur la feuille actuelle, trois boutons de un ou deux chiffres se partagent une rangee (`flex: 1 1 auto`, lignes 609 a 620) : je
n'attends aucun retour a la ligne a 744, **je ne l'ai pas mesure**.

**Ce que cette table dit, et ce qu'elle ne dit pas.** Pour l'enfant, les 65 additions sont le meme exercice : pedagogiquement, un
echantillon qui couvrirait chaque longueur d'enonce et chaque nombre de propositions a deux chiffres me suffirait. Mais cette table
decrit le jar de base. La forme commune est celle des **propositions longues** (`S57-TPH-CE1-R1`) : elle a toutes les raisons de
reagir a une longueur, et son critere 4 n'interdit qu'une condition propre a un paquet, pas une condition sur une longueur. Une
strate definie aujourd'hui peut ne pas etre celle sur laquelle la forme tranche demain.

## 3. `Q-T8` : la mesure complete par script

**Decision (N1).** La cote de reference et la cote apres le commit portent les **65 enonces**, chacun mesure au moins une fois a
744 x 551, apres une erreur, bas de `.indice` par `getBoundingClientRect()`, avec la methode etalonnee de la fiche de
`bandit-manchot-verbe-sujet-ce1` (rejeu du 2026-10-14, section 1). Le parcours est celui de `TD5` : l'enonce donne la bonne
reponse, le script appuie sur une autre proposition, releve la cote, puis repond juste. La couverture se verifie sur le fichier :
65 enonces distincts, egaux a l'ensemble derive.

**Pourquoi pas l'echantillon de `Q-T8`** (*"justifie par une premiere partie mesuree ou le bas de `.indice` ne varie pas"*) :

1. une partie mesure dix manches **sur le jar de base** ; elle ne dit rien de ce que la forme fera sur le jar livre, qui est la
   seule chose que la clause regarde ;
2. dix manches ne voient presque jamais un enonce de 6 signes : les deux sont des couples a operande nul, retenus une fois sur trois
   (lignes 101 a 109).

**Pourquoi pas un echantillon stratifie, que j'aurais accepte pedagogiquement** (section 2) :

1. **Il ne coute pas moins de script, seulement moins de temps de script.** Le jar tire sans graine (`HasardConfiguration.java`
   ligne 23) : meme une strate se joue jusqu'a ce que le hasard la serve. Modele des regles de tirage en Python (generateur de
   Python, pas celui de Java ; 4000 executions, fichier de travail hors depot) : voir un enonce de 6 signes coute en moyenne
   **7,5 parties** (16 au 90e centile), le triplet `10, 11, 12` 2,1 (4). Les 65 enonces : **52 parties** (74), 513 manches. J'ai
   rejoue le modele de `theo-archi` avant de m'y appuyer (2000 executions, autre graine) : 512,9 manches, 51,7 parties, 74 au
   90e centile, ses chiffres.
2. **Sa suffisance se juge sur la forme, qui n'existe pas.** Il faudrait que je relise les strates a la livraison, et que je
   refuse la mesure si la forme reagit a une variable qu'elles ne couvrent pas : un second jugement, avec une barre qui depend
   de ce qu'on a livre.
3. **L'ecart de prix est du temps machine.** Une manche, ce sont deux appels aux routes servies ; j'ai deja joue 27 parties de dix
   manches de cette facon pour 24 items (`verbe-sujet`, 2026-10-14). Le script a ecrire est le meme dans les deux cas.

**Ce qui me ferait changer d'avis** : un mesureur qui montre, chiffres a l'appui, que le rendu des 65 fragments est hors de portee
de la capacite du palier, alors que celui de quelques fragments ne l'est pas. Ce n'est pas `Q-T8` tel qu'il est pose.

## 4. Ce que je peux dire sans jar, et ce que je ne peux pas dire

| Je peux dire, lu ou calcule | Je ne peux pas dire sans jar |
|---|---|
| l'ensemble des 65 enonces, et que le jar ne permet pas de le choisir | une seule cote, sur aucun enonce |
| ce qui varie d'un item a l'autre au-dessus de `.indice` (section 2) | si l'une de ces variations deplace le bas de `.indice` sur le jar de base |
| le cout de la couverture, sur un modele Python des trois regles de tirage | le cout reel en temps : il depend du script et du poste |
| que l'hypothese *"item = enonce"* de `TD5` repose sur des propositions qui changent a chaque tirage | si elle tient : seul un meme enonce mesure deux fois le dit |

## 5. Ce que le fichier de cote ecrit, en plus de ce que le critere demande deja (N3)

1. **Les trois propositions servies, a cote de chaque cote.** L'hypothese *"item = enonce"* de `TD5` vaut tant que les
   propositions ne deplacent rien ; si un meme enonce donne deux cotes, c'est la qu'on lit pourquoi. Ce n'est pas une barre
   neuve : c'est ce qui permet de lire la sortie que `theo-archi` a deja ecrite (*"la clause retourne a `nadia-prof`"*).
2. **Les deux conditions de `Q-R5`** : `git merge-base --is-ancestor <base> <commit livre>` sort 0, et `git diff --stat` entre
   les deux ne nomme aucun paquet ni `catalogue.yaml`.

La sortie reste celle de l'amendement `Q-R4` : un enonce dont l'indice finit au-dessus de y = 551 sur le jar de base y reste apres
le commit. **Aucune barre neuve sur un enonce deja sous le pli.**

## 6. Ce dont je reste la seule garde, pour ce jeu

La position de `.indice` n'est tenue par aucun test (`rejeu-nadia-palier-1.md`, section 10). Le fichier de cote est une mesure, pas
un garde-fou : il ne rougit sur aucun commit qui suit. Je ne prononce pas la non-regression de ce jeu avant d'avoir lu ses 65 cotes.

## 7. Routage

| A qui | Quoi |
|---|---|
| `sofia-sm` | N1 : qui ecrit le script et mesure, a la capacite ; meme jar que la mesure d'`ines-design` (`A5`) |
| `camille-po` | `Q-R6` est fermee pour moi : le point 3 du critere 3 amende se lit *"les 65 enonces derives, mesures par script"* |
| `theo-archi` | `TD4` et `TD5` acceptes tels quels ; aucune graine dans le jar, d'accord avec D.3 |

## 8. Ce que ce fichier ne fait pas

Il ne touche ni le backlog, ni un paquet, ni du code, ni le registre. Il n'ecrit ni n'alloue aucun identifiant de condition ou de
reserve : `N1` a `N3` sont des reperes de ce fichier. **Tarif** : A', aucun prix neuf.

**Signe** : `nadia-prof`, s57, palier 1, instance A', 2026-10-15.
