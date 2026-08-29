# Fiche de conformite - bandit-manchot-multiplications-divisions-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : contenu neuf a creer (`type: question`, au moins 24 items), story `S13-2`
  (`docs/backlog/backlog.md`). `contenu-repris/cm1-calcul-mental.yml` est une matiere premiere
  inspirante seulement : schema incompatible (`difficulty`/`bounds`, configuration de difficulte
  taillee pour l'arithmetique qu'aucune fabrique de `round-two` ne lit) et perimetre trop large
  (melange addition, soustraction et multiplication, sans borner aux multiples de 10 et 100) - voir
  `DEC-0029`, section "Ce que le schema existant peut porter sans code neuf".
- **Statut global** : **ouverte, aucun increment livre a ce jour.** Rien n'a encore ete joue.
- **Arbitrage prealable de `nadia-prof` : rendu le 2026-08-15** (voir section dediee plus bas,
  `MD-1` a `MD-10`) - la ligne ci-dessus disait encore "demande et non encore rendu" jusqu'au
  2026-08-28, perimee depuis huit sprints ; corrigee ici plutot que laissee en l'etat. Les criteres
  ci-dessous, notamment les bornes, restent ceux de `camille-po` a l'ouverture, **completes et
  confirmes** par l'arbitrage rendu.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, branche `s10-nadia-mecanique` et audits suivants) :
  conditions M1 a M13. Rien n'est mesure ici a l'ouverture de la fiche, faute d'increment a mesurer.
  **Mise a jour du 2026-08-29 (`camille-po`)** : `M4` est sortie de la porte pedagogique le
  2026-08-28 (`DEC-0053`) et sa reference `360 x 640` etait deja morte depuis `DEC-0040`
  (2026-08-22) ; voir `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, en-tete, et
  `squad/conformite/ARBITRAGE-M4-ET-BALAYAGE-DES-CONDITIONS-DE-MECANIQUE-2026-08-29.md`
  (`nadia-prof`) avant de citer ce bloc.
- **Priorite s22** (`DEC-0053`, section 4) : premiere candidate du perimetre du sprint, verrou
  d'arbitrage leve, aucun obstacle nomme sur ce paquet en dehors de l'avis de faisabilite de
  `theo-archi`, encore du.
- **Derniere mise a jour** : 2026-08-28 (`camille-po`, correction de statut et priorisation s22,
  `DEC-0053`)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CM1 (cycle 3) | Nombres et calculs | Calcul mental - multiplications et divisions simples (multiples de 10, 100) | `squad/programme/mathematiques/competences-cm1.md` |

**Perimetre propose, sous reserve de l'arbitrage prealable de `nadia-prof`** : multiplier ou diviser
un nombre entier par 10 ou par 100, sans unite de grandeur (calcul mental pur, a distinguer de la
ligne "convertir des unites" deja `couvert-valide` au sprint 12), sans ecriture decimale.

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque item est une multiplication ou une division d'un nombre entier par 10 ou par 100,
  sans unite de grandeur ; la bonne reponse est le resultat exact.
- [ ] Le nombre de depart et le resultat sont des entiers positifs ; aucune division qui ne tombe
  pas juste, aucune ecriture decimale nulle part dans le paquet.
- [ ] La multiplication et la division sont representees a une unite d'ecart pres l'une de l'autre.
- [ ] Les items sur le facteur 10 et sur le facteur 100 sont chacun representes par au moins un
  quart du paquet.
- [ ] Chaque item porte exactement trois propositions, dont deux distracteurs entiers correspondant
  a une erreur de facteur identifiable, jamais deux propositions egales dans le meme item.
- [ ] Le paquet compte au moins 24 items.
- [ ] Chaque item porte un indice non vide qui donne la methode, pas le resultat.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue. L'arbitrage prealable demande par `DEC-0030` (bornes, unites,
> profondeur sur 100) sera rendu ici ou dans un fichier d'arbitrage dedie, avant tout code.

### Arbitrage prealable rendu le 2026-08-15 (s13) - ce n'est pas un Done pedagogique

- **Ou** : `squad/conformite/ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md`,
  section 2. **Dix conditions, MD-1 a MD-10**, plus la liste de ce que je refuse d'avance
  (section 3.5).
- **Ce qu'il tranche** : la ligne ouvre **en entier**, sous la lecture stricte du referentiel -
  l'operateur est 10 ou 100, jamais 1000 ni un multiple compose. Les bornes de `camille-po` sont
  **confirmees** (entiers seulement, aucune ecriture decimale) et **completees** : quatre familles
  d'operation chacune a un cinquieme du paquet au moins, resultat sous le million, signes multiplie
  et divise reels a la place de la lettre `x` et de la barre oblique, et une **condition
  d'aboutissement sur le rang de la bonne reponse** (entre 20 % et 40 % par rang) plus stricte que
  les seuils de la CI, parce qu'elle est atteignable sur ce contenu.
- **Ce qu'il ne fait pas** : il ne vaut aucun Done pedagogique par avance. Un lot qui tient les dix
  conditions peut encore etre refuse pour ce que seul le jeu montre.
- **Statut** : le verrou d'arbitrage prealable de `DEC-0030` sur cette story est **leve**. Le code
  peut commencer des que `theo-archi` a rendu son avis de faisabilite.

---

### Done pedagogique - 2026-08-29 (s22) - REFUSE

**Verdict : REFUSE.** Le motif est unique, isole, et corrigeable en quatre chaines de caracteres.
**Ce n'est pas un refus sur les dix conditions `MD-1` a `MD-10`** : neuf d'entre elles sont tenues,
la dixieme l'est sur son fond. Le motif est ailleurs, et je le nomme en clair pour que personne ne
le cherche dans mes conditions.

#### Comment j'ai audite, et ce que je declare

- **J'ai joue.** Application lancee depuis le fat jar construit sur l'arbre de la branche, parties
  entieres jouees, en me trompant volontairement a chaque manche jusqu'a epuiser les propositions.
  **Audit non degrade.**
- **J'ai recompte moi-meme**, sur le fichier de contenu, les vingt-cinq items : les quatre familles,
  le rang de la bonne reponse, l'exactitude des vingt-cinq resultats, le separateur de milliers, les
  bornes, le compte des items admettant le nombre de l'enonce comme distracteur. **Perimetre du
  compte : le seul paquet `cm1-multiplications-divisions-10-100`, vingt-cinq items, arbre de
  `s22-nadia-3` issu de `round-two` a `62037c7`.**

#### Le motif du refus

**Les vingt-cinq indices du paquet - c'est-a-dire la totalite du texte que l'enfant lit apres une
erreur - ne sont pas accentues.** Quatre chaines distinctes, une par famille :

> *"Multiplier par 10, c'est ecrire un zero de plus a droite du nombre."*
> *"Multiplier par 100, c'est ecrire deux zeros de plus a droite du nombre."*
> *"Diviser par 10, c'est enlever un zero a droite du nombre."*
> *"Diviser par 100, c'est enlever deux zeros a droite du nombre."*

L'enfant lit **ecrire** la ou il faut lire *ecrire* accentue, **zero** la ou il faut *zero*
accentue, **a droite** la ou il faut *a* accentue. Compte, avec sa methode : le mot *zero* ou
*zeros* apparait sur **25 items sur 25**, *a droite* sur **25 sur 25**, *ecrire* sur **12 sur 25**
(les douze items de multiplication) - releve fait a la main sur le fichier de contenu, quatre
chaines distinctes reparties 6 / 6 / 6 / 7 par famille.

**Pourquoi c'est un motif de refus et pas une remarque de style.** Ce texte n'est pas de la
documentation : c'est ce qu'un enfant de neuf ans lit **au moment precis ou on le corrige**. Un
enfant de CM1 apprend l'orthographe en meme temps que le calcul, et il ne trie pas entre ce qui est
a copier et ce qui ne l'est pas. Le depot a tranche ce point le 2026-08-04, par l'amendement a
`DEC-0002` : le contenu lu par l'enfant est *"redige en francais correct et integralement accentue,
orthographe et ponctuation irreprochables, parce qu'il est lu par un enfant en cours d'apprentissage
de la lecture"*. La regle est anterieure de onze jours a mes propres conditions sur ce paquet.
**Je ne mets pas ce texte au tableau de ma classe ; je ne le mets pas non plus dans un jeu.**

**Ce n'est pas un durcissement de `MD-7` et je m'oblige a le dire.** `MD-7` demande *"une methode,
pas un slogan"* : sur ce point les quatre indices sont bons, ils donnent la regle et jamais le
resultat, et il y en a quatre distincts, un par famille. **Ma condition est tenue.** Le defaut est
d'une autre nature, il porte sa date d'aujourd'hui, et il ne remonte pas dans `MD-7`.

**Ce que ce refus coute a lever : quatre chaines.** Aucun item a reecrire, aucun nombre a changer,
aucune structure a toucher. Je nomme le cout ici pour que `camille-po` puisse juger si cela revient
dans le sprint meme plutot qu'au backlog.

**Une question de competence que je pose plutot que de la trancher seule.** `CLAUDE.md` range
l'accentuation du texte enfant parmi les regles verifiees par `relecteur-pedagogique`, et ma fiche
de role me confie *"ce que l'enfant lit a l'ecran"*. Les deux sont vrais. Le defaut est passe sans
etre vu, **aucun garde-fou executable ne couvre l'accentuation des indices d'un paquet** -
`TexteAccentueExposeParLePortEntrantTest` couvre les chaines du catalogue et non celles des items,
`LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest` juge la notation et non la presence des
accents - et je l'ai vu en jouant. **Si la porte competente est celle du relecteur et non la mienne,
la correction est la meme et ce refus aura coute un lot.** Je route la question sans attendre la
reponse pour prononcer.

#### Les dix conditions, une par une, contre le contenu reel

| Condition | Etat | Ce que j'ai verifie moi-meme |
|---|---|---|
| `MD-1` operateur | **tenue** | 25 items, operateur 10 ou 100 uniquement, aucun 1000, aucun multiple compose |
| `MD-2` quatre familles a un cinquieme | **tenue** | 6 / 6 / 6 / 7 - plancher de 5 tenu par les quatre |
| `MD-3` bornes | **tenue** | entiers positifs, aucune virgule ; resultat maximal 810 000, sous le million ; depart a quatre chiffres au plus ; **2** items partent d'un nombre a un chiffre (plafond 6) ; **4** items partent d'un nombre a zero intercalaire - 305, 2 050, 2 007, 9 020 - (plancher 3) |
| `MD-4` ce que l'enfant lit | **tenue** | signes multiplie et divise reels ; aucune lettre `x`, aucune barre oblique ; separateur de milliers par espace insecable, **constant sur tout le paquet, aucune anomalie** ; aucune unite de grandeur |
| `MD-5` aboutissement | **tenue** | **8 / 8 / 9** sur 25, soit **32 % / 32 % / 36 %** - dans les bornes 20 %-40 %. Recompte item par item sur la valeur, pas sur l'ordre d'ecriture |
| `MD-6` distracteurs | **tenue sur le fond, NON TENUE sur sa clause de declaration** - reserve non bloquante 2 | voir ci-dessous |
| `MD-7` indice | **tenue** | quatre indices distincts, un par famille, methode et jamais resultat. *Le defaut d'accentuation ne remonte pas dans cette condition* |
| `MD-8` formes exclues | **tenue** | aucune mise en situation, aucun facteur manquant, aucune operation a trou, aucune unite, aucun item definitionnel |
| `MD-9` enonce | **tenue** | une ligne, le calcul seul, forme citee en ASCII `305 x 10 = ?` et rendue avec le signe reel |
| `MD-10` titre | **tenue** | *"Multiplier ou diviser par 10 et 100"* - les deux operations annoncees |

**Exactitude : les vingt-cinq resultats sont justes.** Recalcules un par un, aucun ecart. C'est la
propriete que je regarde en premier et elle est acquise.

**`MD-6` en detail.** La condition demande deux choses. La premiere - *chaque distracteur correspond
a une erreur qu'un CM1 fait vraiment* - est **tenue** : j'ai relu les cinquante distracteurs, chacun
est un zero de trop, un zero de moins, le mauvais sens, ou la valeur de depart inchangee ; aucun
nombre de remplissage. La clause du tiers est tenue elle aussi : **8 items sur 25** admettent le
nombre de l'enonce comme distracteur, soit exactement le plafond d'un tiers sans le depasser. La
seconde - *cette erreur est nommee dans le fichier* - **n'est pas tenue** : le fichier de contenu ne
porte **aucune ligne de commentaire**. Reserve non bloquante : la declaration manquante m'a coute la
verification, elle n'a rien coute a l'enfant.

#### Ce que le contenu reel a mis en defaut dans mes propres conditions

**Rien, sur ce paquet.** Les dix conditions ont rencontre vingt-cinq items pour la premiere fois et
aucune ne s'est revelee ni trop large ni trop etroite. **C'est un resultat, pas une absence de
resultat** : je l'ecris parce que je m'attendais a l'inverse, et parce que la clause de `MD-6` sur
le tiers - la seule que j'aie ecrite sans precedent - tombe a **8 sur 25** contre un plafond de
8,33, c'est-a-dire qu'elle a mordu au chiffre pres sans rien interdire d'utile.

**Un avertissement sur ce que la CI verte ne dit pas.** `yanis-qa` etablit que dix-neuf de mes
vingt-six conditions sont desormais gardees automatiquement, et que trois de mes predictions de
non-testabilite - dont `MD-6` - etaient fausses. **Aucun de ces garde-fous n'a vu le defaut qui fait
ce refus**, parce qu'aucun ne le cherche. Un garde-fou vert dit qu'une condition est tenue telle
qu'elle est ecrite ; il ne dit pas qu'elle est bien ecrite, et il ne dit rien de ce qu'aucune
condition ne couvre.

#### Reserves

- **Reserve 1, BLOQUANTE, 2026-08-29 - accentuation des vingt-cinq indices.** Motif du refus
  ci-dessus. A lever en corrigeant les quatre chaines, sans aucun autre changement.
- **Reserve 2, non bloquante, 2026-08-29 - `MD-6`, les erreurs ne sont nommees nulle part.** Le
  fichier ne porte aucun commentaire. Les deux autres paquets de s22 en portent, dont un qui nomme
  ses familles de distracteurs. A aligner, pour qu'un tiers puisse verifier la condition sans
  refaire mon travail.

#### Ce que je reste seule a garder sur ce paquet

Exactitude des vingt-cinq resultats ; plausibilite des cinquante distracteurs ; qu'un indice
n'enseigne pas une regle fausse ; **l'accentuation et l'orthographe de ce que l'enfant lit dans un
item** - aucun garde-fou ne la couvre, et c'est ce sprint qui le prouve.

#### `MNC-1`

**Aucun Done pedagogique n'etant prononce ici, `MNC-1` n'a pas a y figurer.** Elle porte sur tout
Done `VALIDE` ou `VALIDE SOUS RESERVE` a partir du 2026-08-28 ; un `REFUSE` n'est pas un Done. Son
absence de ce bloc n'est pas un oubli, et elle devra etre portee par le bloc qui prononcera le Done
de ce paquet, quel qu'en soit le sprint.

#### Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **Le refus, et son cout de levee : quatre chaines** | `camille-po` | En faire du backlog, ou juger que cela revient dans s22 - c'est sa main, pas la mienne |
| **La reserve 2 (`MD-6` non declaree)** | `camille-po` | Une story, ou un ajout au meme lot que la reserve 1 |
| **A qui appartient l'accentuation du texte d'un item : `relecteur-pedagogique` ou moi ?** | `sofia-sm`, puis `camille-po` | Trancher, et dire si un garde-fou executable est possible. **Aucun n'existe aujourd'hui**, et le defaut a traverse une CI verte et un lot livre |
