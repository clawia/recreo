# Fiche de conformite - Nommer, en histoire (`bandit-manchot-histoire-geographie-nommer-en-histoire-cm1`)

> **Fiche ouverte par `nadia-prof` a s35** (`S35-CONS-4`), et non par `camille-po`. Cette entree de
> catalogue est neuve : elle est l'une des **quatre** issues de la scission de
> `bandit-manchot-histoire-geographie-cm1` au commit `b16bbbcb`. **Aucun de ses items n'est neuf** -
> les 45 items de ce paquet viennent tous du paquet d'origine, mot pour mot.
>
> **Toute l'histoire pedagogique de ces items, jusqu'a s35, vit dans
> `FICHE-bandit-manchot-histoire-geographie-cm1.md`** : cinq verdicts de `nadia-prof` de s27 a s33,
> et les reserves `HG-R*` ouvertes, qui **restent opposables** aux items de ce paquet. Cette fiche
> ne les recopie pas - un verdict recopie n'est plus une preuve - elle y renvoie. Les sections
> descriptives sont reprises de `catalogue.yaml`, du paquet et du referentiel, sans rien y ajouter ;
> **le bloc de validation est le mien, comme toujours.**

- **Mecanique** : `bandit-manchot`
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-histoire-geographie-nommer-en-histoire.yaml` (45 items)
- **Entree de catalogue** : `bandit-manchot-histoire-geographie-nommer-en-histoire-cm1`, titre servi *"Nommer, en histoire"*, `nombreDeManches: 10`
- **Fiche de l'ancetre** : `FICHE-bandit-manchot-histoire-geographie-cm1.md`
- **Statut global** : **VALIDE** (premier passage sur cette entree, 2026-09-11, s35). **`CONS-4` levee sur cette entree**, et **`CONS-5` ouverte** (non
  bloquante) : trois items de ce paquet ne font pas ce que leur consigne annonce
- **Derniere mise a jour** : 2026-09-11 (s35)

## Competences visees

| Matiere | Niveau | Domaine | Lignes de referentiel servies |
|---|---|---|---|
| Histoire-geographie | CM1 (cycle 3) | Reperes historiques et geographiques | `HG-CM1-03`, `HG-CM1-04`, `HG-CM1-06`, `HG-CM1-07`, `HG-CM1-08` |

`squad/programme/histoire-geographie/competences-cm1.md`. **Aucun statut de matrice ne bouge par
cette scission** : ces lignes gardent exactement l'etat que mes verdicts de s27 a s33 leur ont
donne, et les items qui les servent n'ont pas change.

## Criteres pedagogiques attendus - camille-po

Repris de `docs/backlog/backlog.md`, stories `S34-CONS-4` (criteres qui font foi) et `S35-CONS-4`,
plus les trois criteres ajoutes par `DEC-0088`.

- [x] Les items viennent du paquet d'origine, sans ajout, sans perte, sans texte change
- [x] La consigne de l'entree nomme la tache reellement servie par ce paquet (`CONS-4`)
- [x] Le descripteur `matiere` / `niveau` / `domaine` est identique a celui des trois autres paquets
      issus de la scission (`DEC-0088`), pour que `HIS-1` continue de se compter sur le lot de
      matiere entier
- [x] Plus d'items que de manches (`ADR-0023`) - 45 pour 10

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **VALIDE**
- **Date** : 2026-09-11 (s35)
- **Auteur** : `nadia-prof`
- **Arbre** : `s35-integration` a `c1b326d4`, worktree `s35-nadia`
- **Audit joue** : **oui.** Jar reconstruit depuis mon propre worktree, lance sur le JDK `21.0.10`
  de la toolchain. **Une partie complete de 10 manches sur cette entree**, chaque manche
  jouee en pressant les propositions jusqu'a la bonne, pour lire les indices servis. **La consigne a
  ete lue a l'ecran, au-dessus de ses items, et non dans le catalogue.**
- **Mouvement de matrice** : **aucun.**
- **Portee de ce verdict** : il porte sur **ce que la scission fait a ces items** - leur fidelite au
  contenu d'origine et l'ajustement de la consigne neuve a ce paquet. **Il ne rejuge pas les items
  eux-memes** : ils ont deja recu cinq verdicts, et les reserves `HG-R*` encore ouvertes dans la
  fiche de l'ancetre **restent ouvertes sur ce paquet** et voyagent avec les items qui les portent.

### 1. Fidelite - verifiee, pas supposee

Mesure faite sur les quatre paquets a la fois, methode et chiffres complets dans le bloc de s35 de
`FICHE-bandit-manchot-histoire-geographie-cm1.md`, section 1 : **110 items a l'origine, 110 dans les
quatre paquets, zero enonce perdu, zero enonce neuf, zero ecart** sur un `enonce`, une
`bonneReponse`, un `distracteurs`, un `indice`, un `texteSupport` ou une `explication`.

**Ce paquet en porte 45, tous identiques a ce qu'ils etaient.**

### 2. La consigne devant les items de ce paquet

**Consigne servie, lue a l'ecran** : *"Nomme ce que l'enonce decrit."* - 5 mots, contre les 28 de la consigne
fourre-tout qu'elle remplace.

**Exacte sur quarante-deux items sur quarante-cinq, et c'est un tres net progres sur la consigne
qu'elle remplace.** L'enonce decrit une realisation, un peuple, un evenement, et l'enfant le nomme :
*"Des arenes et des aqueducs en pierre ont ete construits en Gaule durant l'Antiquite. Quel peuple
les a construits ?"*, *"Comment appelle-t-on la grande salle du chateau de Versailles decoree de
miroirs ?"*, *"Napoleon Bonaparte fait rediger un meme ensemble de lois pour toute la France. Comment
appelle-t-on ce texte ?"*. **La consigne decrit exactement ce geste.**

**Trois items ne le font pas, et ils fondent `CONS-5`** (reserve neuve, datee du 2026-09-11, non
bloquante, ecrite en entier dans la fiche de l'ancetre) :

| Enonce | Bonne reponse | Ce que l'enfant fait |
|---|---|---|
| *"Que se passe-t-il finalement en 476 ?"* | *l'Empire romain d'Occident disparait* | il produit **un evenement**, pas un nom |
| *"Que devient son expansion en Europe ?"* | *elle s'arrete* | il produit **une issue** |
| *"Que devient ce territoire ?"* | *il se divise en plusieurs royaumes* | il produit **une issue** |

**Ces trois items sont inchanges depuis leur ecriture, et le defaut est du cote de la consigne, pas
d'eux.** Un mot de plus dans la consigne (*"Nomme ou dis ce que l'enonce decrit"*) les couvrirait.
**Je ne l'ecris pas a la place de qui decide** (`RES-2`), et je ne rouvre pas le grain du catalogue
(`ADR-0081`).

**Un quatrieme cas, plus lache, que je nomme sans le compter** : les quatre items *"Quel fait
caracterise le regne de X ?"* partent d'un roi nomme pour demander un fait. L'enonce **ne decrit
rien** - mais la reponse **est** bien un nom (*la croisade*, *la Renaissance*, *l'edit de Nantes*,
*Versailles*). La consigne tient a moitie ; cela ne genera aucun enfant, et je n'en fais pas une
demande.

### 3. Ce que j'ai verifie a l'ecran sur cette partie

- **Les dix manches se sont jouees de bout en bout**, aucun ecran casse, aucune repetition.
- **Les indices** : exacts, a leur item, et ils distinguent bien les trois propositions. Celui de
  l'edit de Nantes (*"Ce texte accorde la liberte de culte aux protestants."*) est exact et
  discriminant.
- **`ADR-0023`** : 45 items pour 10 manches, le plus grand des quatre paquets.
- **L'ecran de fin de partie** : les deux nombres, justes. Verdict separe dans
  `PASSE-TRANSVERSE-s35-ecran-de-fin-de-partie.md`.
- **Les reserves `HG-R*` ouvertes** sur ces items - dont `HG-R19` (un mot de l'enonce qui appelle la
  bonne reponse) et `HG-R21` (un indice qui doit separer les trois propositions) - **restent
  ouvertes ici** et ne sont ni soldees ni rouvertes par la scission.

### 4. Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **Qu'une consigne corresponde aux items de son paquet** (`CONS-4`, `CONS-5`) | **aucun.** C'est moi, en jouant, texte devant items |
| **La fidelite d'une scission** | **aucun test ne compare un paquet a son ancetre.** Je l'ai fait a la main ce jour ; une scission prochaine repasserait sans rouge si un item etait perdu en route. Route a `theo-archi` comme symptome |
| **Les reserves `HG-R*` ouvertes** portees par les items de ce paquet | voir la fiche de l'ancetre - inchangees |
