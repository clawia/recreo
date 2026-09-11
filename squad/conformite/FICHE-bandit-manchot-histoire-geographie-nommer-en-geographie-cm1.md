# Fiche de conformite - Nommer, en geographie (`bandit-manchot-histoire-geographie-nommer-en-geographie-cm1`)

> **Fiche ouverte par `nadia-prof` a s35** (`S35-CONS-4`), et non par `camille-po`. Cette entree de
> catalogue est neuve : elle est l'une des **quatre** issues de la scission de
> `bandit-manchot-histoire-geographie-cm1` au commit `b16bbbcb`. **Aucun de ses items n'est neuf** -
> les 30 items de ce paquet viennent tous du paquet d'origine, mot pour mot.
>
> **Toute l'histoire pedagogique de ces items, jusqu'a s35, vit dans
> `FICHE-bandit-manchot-histoire-geographie-cm1.md`** : cinq verdicts de `nadia-prof` de s27 a s33,
> et les reserves `HG-R*` ouvertes, qui **restent opposables** aux items de ce paquet. Cette fiche
> ne les recopie pas - un verdict recopie n'est plus une preuve - elle y renvoie. Les sections
> descriptives sont reprises de `catalogue.yaml`, du paquet et du referentiel, sans rien y ajouter ;
> **le bloc de validation est le mien, comme toujours.**

- **Mecanique** : `bandit-manchot`
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-histoire-geographie-nommer-en-geographie.yaml` (30 items)
- **Entree de catalogue** : `bandit-manchot-histoire-geographie-nommer-en-geographie-cm1`, titre servi *"Nommer, en geographie"*, `nombreDeManches: 10`
- **Fiche de l'ancetre** : `FICHE-bandit-manchot-histoire-geographie-cm1.md`
- **Statut global** : **VALIDE** (premier passage sur cette entree, 2026-09-11, s35). **`CONS-4` levee sur cette entree**, et **`CONS-5` ouverte** (non
  bloquante) : un item de ce paquet ne fait pas ce que sa consigne annonce
- **Derniere mise a jour** : 2026-09-11 (s35)

## Competences visees

| Matiere | Niveau | Domaine | Lignes de referentiel servies |
|---|---|---|---|
| Histoire-geographie | CM1 (cycle 3) | Reperes historiques et geographiques | `HG-CM1-11`, `HG-CM1-12`, `HG-CM1-13` |

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
- [x] Plus d'items que de manches (`ADR-0023`) - 30 pour 10

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

**Ce paquet en porte 30, tous identiques a ce qu'ils etaient.**

### 2. La consigne devant les items de ce paquet

**Consigne servie, lue a l'ecran** : *"Nomme ce que l'enonce decrit."* - 5 mots, contre les 28 de la consigne
fourre-tout qu'elle remplace.

**Exacte sur vingt-neuf items sur trente.** L'enonce decrit un espace, une ressource ou un
acheminement, et l'enfant le nomme : *"Un centre-ville avec de nombreux immeubles et des commerces
est appele comment ?"*, *"Avant d'etre distribuee dans les maisons, l'eau est souvent stockee en
hauteur dans une grande construction en forme de tour. Comment appelle-t-on cette construction ?"*,
*"Le vent fait tourner de grandes helices qui produisent de l'electricite. Comment appelle-t-on ces
installations ?"*. **La consigne decrit exactement ce geste, et ce paquet est le plus homogene des
deux *Nommer*.**

**Un item ne le fait pas, et il fonde `CONS-5`** avec les trois de *Nommer, en histoire* (reserve
neuve, datee du 2026-09-11, non bloquante, ecrite en entier dans la fiche de l'ancetre) :

> *"Dans un espace urbain, la densite de population - le nombre d'habitants au kilometre carre -
> est-elle en general plus elevee ou plus faible que dans un espace rural ?"* -> **plus elevee**

**L'enfant compare deux espaces ; il ne nomme rien.** L'item est inchange depuis son ecriture, il est
exact, et le defaut est du cote de la consigne.

**Une famille plus lache, que je nomme sans la compter** : une dizaine d'items partent d'un espace
nomme pour demander un fait qui s'y rapporte (*"Dans un espace periurbain, quel type de logement est
le plus frequent ?"* -> *des maisons individuelles avec un jardin*). L'enonce **decrit** bien l'espace
et la reponse **est** bien un nom : la consigne tient. Je les ai regardes un par un pour ne pas les
compter a tort.

### 3. Ce que j'ai verifie a l'ecran sur cette partie

- **Les dix manches se sont jouees de bout en bout**, aucun ecran casse, aucune repetition.
- **Les indices** : exacts, a leur item. Celui du chateau d'eau (*"Elle sert a garder l'eau avant
  qu'elle ne descende ensuite vers les maisons."*) separe bien les trois propositions - la centrale
  electrique et le silo a grains sont deux candidats serieux, et l'indice tranche sans nommer la
  reponse.
- **`ADR-0023`** : 30 items pour 10 manches.
- **L'ecran de fin de partie** : les deux nombres, justes. Verdict separe dans
  `PASSE-TRANSVERSE-s35-ecran-de-fin-de-partie.md`.
- **Les reserves `HG-R*` ouvertes** sur ces items **restent ouvertes ici**, ni soldees ni rouvertes
  par la scission.

### 4. Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **Qu'une consigne corresponde aux items de son paquet** (`CONS-4`, `CONS-5`) | **aucun.** C'est moi, en jouant, texte devant items |
| **La fidelite d'une scission** | **aucun test ne compare un paquet a son ancetre.** Je l'ai fait a la main ce jour ; une scission prochaine repasserait sans rouge si un item etait perdu en route. Route a `theo-archi` comme symptome |
| **Les reserves `HG-R*` ouvertes** portees par les items de ce paquet | voir la fiche de l'ancetre - inchangees |
