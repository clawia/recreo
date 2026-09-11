# Fiche de conformite - Situer dans le temps (`bandit-manchot-histoire-geographie-situer-dans-le-temps-cm1`)

> **Fiche ouverte par `nadia-prof` a s35** (`S35-CONS-4`), et non par `camille-po`. Cette entree de
> catalogue est neuve : elle est l'une des **quatre** issues de la scission de
> `bandit-manchot-histoire-geographie-cm1` au commit `b16bbbcb`. **Aucun de ses items n'est neuf** -
> les 25 items de ce paquet viennent tous du paquet d'origine, mot pour mot.
>
> **Toute l'histoire pedagogique de ces items, jusqu'a s35, vit dans
> `FICHE-bandit-manchot-histoire-geographie-cm1.md`** : cinq verdicts de `nadia-prof` de s27 a s33,
> et les reserves `HG-R*` ouvertes, qui **restent opposables** aux items de ce paquet. Cette fiche
> ne les recopie pas - un verdict recopie n'est plus une preuve - elle y renvoie. Les sections
> descriptives sont reprises de `catalogue.yaml`, du paquet et du referentiel, sans rien y ajouter ;
> **le bloc de validation est le mien, comme toujours.**

- **Mecanique** : `bandit-manchot`
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-histoire-geographie-situer-dans-le-temps.yaml` (25 items)
- **Entree de catalogue** : `bandit-manchot-histoire-geographie-situer-dans-le-temps-cm1`, titre servi *"Situer dans le temps"*, `nombreDeManches: 10`
- **Fiche de l'ancetre** : `FICHE-bandit-manchot-histoire-geographie-cm1.md`
- **Statut global** : **VALIDE** (premier passage sur cette entree, 2026-09-11, s35). **`CONS-4` levee sur cette entree.** Une reserve non bloquante heritee de
  l'audit de la scission : aucune propre a ce paquet
- **Derniere mise a jour** : 2026-09-11 (s35)

## Competences visees

| Matiere | Niveau | Domaine | Lignes de referentiel servies |
|---|---|---|---|
| Histoire-geographie | CM1 (cycle 3) | Reperes historiques et geographiques | `HG-CM1-01`, `HG-CM1-02`, `HG-CM1-07` |

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
- [x] Plus d'items que de manches (`ADR-0023`) - 25 pour 10

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

**Ce paquet en porte 25, tous identiques a ce qu'ils etaient.**

### 2. La consigne devant les items de ce paquet

**Consigne servie, lue a l'ecran** : *"Situe le fait dans le temps, ou remets les faits dans l'ordre."* - 11 mots, contre les 28 de la consigne
fourre-tout qu'elle remplace.

**Exacte, avec un point lache que je nomme plutot que de le taire.** Vingt des vingt-cinq items
situent un fait dans le temps (*"A quelle periode cela correspond-il ?"*, *"En quel siecle cela se
passe-t-il ?"*, *"En quelle annee commence la Revolution francaise ?"*) ou ordonnent des faits
(*"Parmi ces trois faits, lequel a eu lieu le plus tot ?"*, *"Quel evenement a eu lieu juste apres
l'abolition des privileges ?"*). **La consigne les nomme tous les deux, et c'est juste.**

**Les cinq items *"Quelle grande periode vient juste apres X ?"* ordonnent des periodes, pas des
faits**, et la consigne dit *"les faits"*. **C'est un mot, pas un contresens** : un enfant de CM1
qui lit *"remets les faits dans l'ordre"* et voit trois grandes periodes fait exactement ce qu'on
attend de lui. **Je le note et je n'en fais pas une demande.**

### 3. Ce que j'ai verifie a l'ecran sur cette partie

- **Les dix manches se sont jouees de bout en bout**, aucun ecran casse, aucune repetition.
- **Les items *"Parmi ces trois faits..."* sont autonomes** : les trois propositions **portent leur
  date** (*"la prise de la Bastille, en 1789"*, *"la bataille d'Orleans menee par Jeanne d'Arc, en
  1429"*, *"Clovis devient roi des Francs, en 481"*). L'enfant compare trois nombres, ce qui **est**
  la competence de chronologie. **Rien a leur reprocher.**
- **Les indices** : exacts, a leur item. Celui du siecle de 1789 enseigne la methode (*"Compte les
  centaines d'annees. 1789 se situe entre 1701 et 1800, donc au XVIIIe siecle."*) - **c'est ce qu'un
  indice doit faire, et il donne la reponse apres l'avoir expliquee, ce qui est le bon ordre.**
- **`ADR-0023`** : 25 items pour 10 manches.
- **`HG-CM1-07` est aussi servie par *Nommer, en histoire***, ce que `theo-archi` avait nomme
  d'avance. **Rien a y opposer** : une ligne peut etre travaillee par deux taches, et c'est
  souhaitable.
- **L'ecran de fin de partie** : les deux nombres, justes. Verdict separe dans
  `PASSE-TRANSVERSE-s35-ecran-de-fin-de-partie.md`.

### 4. Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **Qu'une consigne corresponde aux items de son paquet** (`CONS-4`, `CONS-5`) | **aucun.** C'est moi, en jouant, texte devant items |
| **La fidelite d'une scission** | **aucun test ne compare un paquet a son ancetre.** Je l'ai fait a la main ce jour ; une scission prochaine repasserait sans rouge si un item etait perdu en route. Route a `theo-archi` comme symptome |
| **Les reserves `HG-R*` ouvertes** portees par les items de ce paquet | voir la fiche de l'ancetre - inchangees |
