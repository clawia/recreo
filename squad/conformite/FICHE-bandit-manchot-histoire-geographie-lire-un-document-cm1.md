# Fiche de conformite - Lire un document (`bandit-manchot-histoire-geographie-lire-un-document-cm1`)

> **Fiche ouverte par `nadia-prof` a s35** (`S35-CONS-4`), et non par `camille-po`. Cette entree de
> catalogue est neuve : elle est l'une des **quatre** issues de la scission de
> `bandit-manchot-histoire-geographie-cm1` au commit `b16bbbcb`. **Aucun de ses items n'est neuf** -
> les 10 items de ce paquet viennent tous du paquet d'origine, mot pour mot.
>
> **Toute l'histoire pedagogique de ces items, jusqu'a s35, vit dans
> `FICHE-bandit-manchot-histoire-geographie-cm1.md`** : cinq verdicts de `nadia-prof` de s27 a s33,
> et les reserves `HG-R*` ouvertes, qui **restent opposables** aux items de ce paquet. Cette fiche
> ne les recopie pas - un verdict recopie n'est plus une preuve - elle y renvoie. Les sections
> descriptives sont reprises de `catalogue.yaml`, du paquet et du referentiel, sans rien y ajouter ;
> **le bloc de validation est le mien, comme toujours.**

- **Mecanique** : `bandit-manchot`
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-histoire-geographie-lire-un-document.yaml` (10 items)
- **Entree de catalogue** : `bandit-manchot-histoire-geographie-lire-un-document-cm1`, titre servi *"Lire un document"*, `nombreDeManches: 7`
- **Fiche de l'ancetre** : `FICHE-bandit-manchot-histoire-geographie-cm1.md`
- **Statut global** : **VALIDE** (premier passage sur cette entree, 2026-09-11, s35). **`CONS-4` levee sur cette entree, et c'est l'ajustement le plus net des
  quatre** : aucune reserve propre a ce paquet
- **Derniere mise a jour** : 2026-09-11 (s35)

## Competences visees

| Matiere | Niveau | Domaine | Lignes de referentiel servies |
|---|---|---|---|
| Histoire-geographie | CM1 (cycle 3) | Reperes historiques et geographiques | `HG-CM1-14` (seule ligne servie par ce paquet) |

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
- [x] Plus d'items que de manches (`ADR-0023`) - 10 pour 7
- [x] **`nombreDeManches: 7`** et non le defaut de `DEC-0064` - **derogation ecrite, `DEC-0088`**
- [x] **Les items ont perdu leur cle `ligneDeReferentiel`** : ce paquet ne sert qu'une seule ligne
      (`HG-CM1-14`), et le garde-fou refuse explicitement le cas contraire (`DEC-0088`)
- [x] **Chacun des dix items porte un `texteSupport`** - verifie un par un, parce que *"le document
      fourni"* serait un mensonge s'il manquait une seule fois

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **VALIDE**
- **Date** : 2026-09-11 (s35)
- **Auteur** : `nadia-prof`
- **Arbre** : `s35-integration` a `c1b326d4`, worktree `s35-nadia`
- **Audit joue** : **oui.** Jar reconstruit depuis mon propre worktree, lance sur le JDK `21.0.10`
  de la toolchain. **Une partie complete de 7 manches sur cette entree**, chaque manche
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

**Ce paquet en porte 10, tous identiques a ce qu'ils etaient.** **Les dix seuls ecarts de toute la scission sont ici** : le retrait
de `ligneDeReferentiel: "HG-CM1-14"` sur ces dix items, **exactement ce que `DEC-0088` exige**, le
paquet ne servant plus qu'une seule ligne. **Aucun enonce, aucune reponse, aucun distracteur, aucun
indice, aucun texte support n'a bouge.**

### 2. La consigne devant les items de ce paquet

**Consigne servie, lue a l'ecran** : *"Lis le document fourni, puis reponds a la question."* - 8 mots, contre les 28 de la consigne
fourre-tout qu'elle remplace.

**Exacte sur les dix items, et c'est le meilleur ajustement des quatre paquets.** Les dix enonces
portent sur le texte servi : six commencent par *"D'apres ce texte..."*, les quatre autres demandent
la nature du document ou son auteur (*"Quel type de document est ce texte ?"*, *"Qui a ecrit ce
texte ?"*). **Et les dix items portent bien un `texteSupport`** - je l'ai verifie un par un, parce
qu'une consigne qui promet *"le document fourni"* devient un mensonge si le document manque une seule
fois.

**Ce paquet est celui qui gagne le plus a la scission** : avant, ses dix items etaient noyes parmi
cent autres sous une consigne qui ne mentionnait la lecture documentaire qu'en cinquieme position,
apres vingt-trois mots. **L'enfant sait maintenant ce qu'on lui demande avant de commencer.**

### 3. Ce que j'ai verifie a l'ecran sur cette partie

- **Les sept manches se sont jouees de bout en bout**, aucun ecran casse, aucune repetition. **Le
  texte support s'affiche a chaque manche, au-dessus de la question.**
- **Exactitude des dix items, verifiee un par un** : la Declaration des droits de l'homme et du
  citoyen est bien du 26 aout 1789 ; l'edit de Nantes est bien de 1598, et le distracteur *"en 1610"*
  est la date de la mort d'Henri IV, donc un vrai piege et non un remplissage ; *"un journal
  intime"* contre *"un edit royal"* et *"un poeme"* sur un texte qui commence *"Cher journal"*.
  **Rien a reprendre.**
- **Les indices renvoient au texte**, et c'est exactement ce qu'un indice de lecture documentaire
  doit faire : *"Relis la premiere phrase du texte, elle donne la date."*, *"Ce roi se presente
  lui-meme au tout debut du texte."*, *"Repense a qui la prison enfermait, en plus des prisonniers
  ordinaires."* **Ils enseignent la methode de lecture, pas la reponse.** Je le porte au credit du
  paquet.
- **Deux items partagent le meme texte support** (l'edit d'Henri IV) en posant deux questions
  differentes - son annee et son auteur. **C'est legitime et c'est meme bien vu** : le meme document
  se lit deux fois pour deux informations distinctes, ce qui est la competence `HG-CM1-14`.
- **`ADR-0023` avec la derogation de `DEC-0088`** : 10 items pour 7 manches, deux de marge. **Le
  blocage arithmetique que `theo-archi` avait mesure d'avance est bien leve dans la donnee servie, et
  pas seulement dans un document.**
- **L'ecran de fin de partie** : *"Tu as trouve, tout seul : ... / Tu as joue toutes les questions :
  7"*, justes sur une partie de sept manches. Verdict separe dans
  `PASSE-TRANSVERSE-s35-ecran-de-fin-de-partie.md`.
- **Les reserves `HG-R*` ouvertes** sur ces items **restent ouvertes ici**. **La mention de
  couverture partielle exigee d'avance sur `HG-CM1-14`** (bloc de s29 de la fiche de l'ancetre)
  **n'est ni levee ni modifiee par cette scission** : je ne l'ai pas rejugee et elle reste ce qu'elle
  etait.

### 4. Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **Qu'une consigne corresponde aux items de son paquet** (`CONS-4`, `CONS-5`) | **aucun.** C'est moi, en jouant, texte devant items |
| **La fidelite d'une scission** | **aucun test ne compare un paquet a son ancetre.** Je l'ai fait a la main ce jour ; une scission prochaine repasserait sans rouge si un item etait perdu en route. Route a `theo-archi` comme symptome |
| **Les reserves `HG-R*` ouvertes** portees par les items de ce paquet | voir la fiche de l'ancetre - inchangees |
