# DEC-0062 - Convention d'attribution d'un enonce recu hors des neuf agents : le depot n'en a
# aucune, et ce manque a produit une fausse attribution le 2026-08-30. Convention proposee, nommage
# rendu a l'utilisatrice

- **Type** : gouvernance de depot - convention de redaction et d'attribution
- **Date** : 2026-08-31
- **Auteur** : camille-po, en partage de perimetre avec `sofia-sm`
- **Sprint** : s24 (en cours)
- **Competence programme visee** : **transverse - pas de contenu pedagogique.**
- **Statut** : **Acceptee** le 2026-08-31, apres reponse de l'utilisatrice aux trois points de la
  section 6 (`Q4` *"oui"*, `Q5` *"MASTER"*, `Q6` *"oui"*). Voir l'amendement en fin de document, qui
  porte le nom retenu et une collision de lecture que je signale plutot que de la contourner.
  **Statut anterieur : `Proposee`, du 2026-08-31 au 2026-08-31.**

## 0. Ce que ce document est

Le depot possede une regle d'attribution pour ses neuf agents - un tag de commit par agent, rejete
par la CI s'il est absent - et une pratique constante pour l'utilisatrice, dont les arbitrages sont
cites entre guillemets, dates, et traces en `DEC`. **Il n'a rien pour un enonce recu d'aucun des
deux.** C'est ce trou qui est instruit ici.

Il ne rouvre pas le perimetre de s24, ne modifie le document d'aucun agent, et n'engage aucun
developpeur.

---

## 1. Le cas, avec sa provenance declaree

**L'enonce fautif** : *"l'ouverture de la saisie ferait passer la couverture de 28 a 33 lignes sur
43"*. Il a servi d'appui a l'utilisatrice pour arbitrer l'ouverture de la mecanique de saisie.

> **Correction du 2026-08-31 (cloture de s24).** Cette phrase portait *"fera"* la ou les trois
> documents de `sofia-sm` portent *"ferait"*. **L'ecart est de moi et il est aligne ici sur
> l'occurrence la plus ancienne**, par la regle 3.5 que cet ecart a fait ecrire. Ecart trouve par
> `relecteur-pedagogique`, qui l'a **nomme sans le lever** - conduite exacte, et c'est son refus de
> choisir qui a montre que la regle manquait. Voir l'amendement, section B.

**Il est faux sur ses deux bornes, et c'est etabli trois fois independamment :**

- **le gain est nul**, pas cinq lignes. `camille-po` (`DEC-0059`, section 5.1) a lu **18 lignes** une
  par une contre les six refus prealables `R1` a `R6` : zero ligne ouverte. `nadia-prof` (arbitrage
  prealable de s24, section 6) a refait cette lecture, l'a declaree exacte et l'a **etendue aux cinq
  lignes qui manquaient** - les quatre `non couvrable en l'etat` et la ligne `en cours` depuis s03 :
  **zero sur 23 lignes instruites.** `sofia-sm` l'a reverifie sur l'arbre fusionne ;
- **le depart n'est pas 28, il est 24.** Comptage de la colonne Statut des quatre tableaux de matiere
  de `squad/programme/MATRICE-couverture.md` : 47 lignes, dont 24 `couvert-valide` et 4 `non
  couvrable en l'etat`, denominateur atteignable 43. **24/43.** Verifie separement par `camille-po`,
  `nadia-prof` et `sofia-sm`.

**La provenance de l'enonce, et je la donne sous la forme meme que ce document propose** : *recu par
le fil d'animation de la squad, sans source dans le depot*. Il n'a d'origine dans aucun arbitrage,
aucune `DEC`, aucun `ADR`, aucun avis d'agent - c'est ce que `sofia-sm` a etabli en le cherchant, et
c'est ce qui m'a ete rapporte quand j'ai instruit ce point. **Il ne vient pas non plus de
l'utilisatrice** : c'est a elle qu'il a ete dit.

**La fausse attribution** : `sofia-sm` l'a ecrit dans la section 4 de son planning de s24 en le
signant *"il est de moi"*. **Il n'est pas d'elle.** Elle n'a jamais prononce ce chiffre.

---

## 2. Pourquoi la fausse attribution etait, en l'etat du depot, le moins mauvais des choix disponibles

C'est le point qui compte, et c'est pour cela que ce document ne demande aucune sanction et ne
demande a `sofia-sm` aucune explication.

Un agent qui trouve un enonce faux et veut l'ecrire dans un fichier commite n'avait, jusqu'a ce
document, que trois formes a sa disposition :

| Forme disponible | Ce qu'elle produit |
|---|---|
| **L'attribuer a un agent** (y compris a soi-meme) | Une **fausse attribution**. C'est celle qui a ete choisie, et le depot porte desormais un ecart signe par quelqu'un qui ne l'a pas commis |
| **L'attribuer a l'utilisatrice** | Une fausse attribution **plus grave** : elle rendrait l'utilisatrice responsable du chiffre sur lequel elle a arbitre |
| **Ne l'attribuer a personne** - *"le chiffre a circule"*, *"il a ete affirme que"* | Un enonce **sans proprietaire**, exactement ce que ce depot sanctionne partout ailleurs. Une phrase au passif, dont personne ne repond, est la forme que ce depot a appris a se mefier |

**Aucune des trois n'est vraie.** L'agent qui a signe *"il est de moi"* a choisi la forme qui laissait
un proprietaire nomme plutot qu'un ecart flottant : c'est le bon reflexe applique a un vocabulaire
qui n'existait pas. **Le defaut est dans le depot, pas dans sa main.**

---

## 3. Ce que je propose - trois formes, et une seule interdite

### 3.1 La regle

**Tout enonce ecrit dans un fichier commite - chiffre, fait, affirmation - porte sa provenance sous
l'une de ces trois formes, et sous aucune autre.**

1. **`de <agent>`** - avec le fichier et la section ou cet agent l'a ecrit. La reference doit resoudre :
   un renvoi qui ne pointe vers rien est de la meme famille de defaut qu'un chiffre sans methode.
2. **`de l'utilisatrice`** - avec ses mots cites, entre guillemets, et la date.
3. **`recu par le fil d'animation, sans source dans le depot`** - avec l'enonce cite mot pour mot et
   la date a laquelle il a ete recu. **C'est la forme qui manquait.**

**Interdite** : attribuer a soi-meme ou a un agent un enonce dont on n'a pas verifie qu'il est ecrit
dans un fichier du depot. C'est cette forme-la qui a produit le cas de la section 1.

### 3.2 Ce que la troisieme forme ne donne pas, et c'est ce qui l'empeche de devenir une lessive

Une forme d'attribution sans source verifiable pourrait servir a faire entrer n'importe quoi dans le
depot. Deux bornes, et elles sont le prix de la forme 3 :

- **Un enonce de forme 3 ne vaut jamais motif.** Il ne peut fonder aucune `DEC`, aucun `ADR`, aucun
  critere d'acceptation, aucun verdict. Il est un **fait a verifier**, jamais un appui. S'il est
  verifie, il devient un enonce de forme 1 ou 2 avec sa source ; s'il ne l'est pas, il reste ce
  qu'il est et il le dit.
- **Il est cite mot pour mot, jamais resume.** Un enonce sans source qu'on reformule perd la seule
  chose qui permette de le verifier plus tard.

### 3.3 La regle de correction

**Quand un enonce se revele faux, la correction est ecrite par qui l'a trouve, dans son propre
document, sous la forme de provenance de l'enonce d'origine - jamais sous la sienne.**

Le geste correct dans le cas de la section 1 aurait ete, dans les mots de `sofia-sm` :

> *"Un chiffre faux - de 28 a 33 lignes sur 43 - a servi d'appui a l'arbitrage de l'utilisatrice.
> **Recu par le fil d'animation, sans source dans le depot.** Il n'est de personne de cette squad,
> et je le corrige ici : le depart est 24, le gain est nul sur les 23 lignes instruites."*

**Ce que ce geste conserve** : le fait, la date, la correction, et le fait qu'un arbitrage a repose
dessus. **Ce qu'il retire** : un proprietaire invente.

### 3.4 Ce que ca change pour un agent qui recoit une consigne

Rien sur sa conduite : un agent execute ce qu'on lui demande. La regle porte sur **ce qu'il ecrit
dans un fichier commite**, et sur un seul reflexe - avant d'ecrire un chiffre ou un fait recu, aller
voir s'il est dans le depot. S'il y est, forme 1 ou 2 avec sa reference. S'il n'y est pas, **forme 3
et jamais autre chose**.

---

## 4. Ce qui rend la convention non declarative

Ce depot a une dette nommee dans `CLAUDE.md` : *"un garde-fou annonce mais absent"*. Une convention
de redaction qui ne serait verifiee par personne serait de cette famille.

**Elle est verifiee par `relecteur-pedagogique`**, qui verifie deja les conventions de redaction sur
tous les livrables non executables - `squad/`, `docs/backlog/`, `ADR`, `DEC`, fiches, messages de
commit. Le critere qu'il applique est **binaire et n'appelle aucun jugement** :

> Pour chaque chiffre et chaque affirmation de fait d'un fichier relu : sa provenance est-elle sous
> l'une des trois formes ? Si la forme est 1, la reference resout-elle ?

**Ce n'est pas un test executable**, et je ne pretends pas le contraire : la question de savoir si un
automate pourrait en tirer quelque chose est technique et je ne la tranche pas. Elle est routee a
`theo-archi` et `yanis-qa` **comme une question, pas comme une demande** : la forme 1 est la seule des
trois dont la resolution de reference soit peut-etre mecanisable, et c'est deja le defaut qui a ete
trouve dans `CLAUDE.md` le 2026-08-19 (un renvoi vers une section d'`ADR-0045` qui n'existe pas).

---

## 5. Ce que ce document demande, et a qui

| Ce qui est du | Proprietaire | Etat |
|---|---|---|
| **Correction de la section 4 du planning de s24** : la signature *"il est de moi"* remplacee par la forme 3, avec l'enonce cite et sa date. Le reste de la section est exact et n'a pas a bouger | `sofia-sm` | **Route.** Je ne reecris pas le document d'un autre agent - `theo-archi` l'a refuse a s24 pour le meme motif et `sofia-sm` l'a refuse apres lui |
| **Amendement de `squad/README.md`** : la convention en section 8, a cote des prefixes de commit, ou en section 2 | `sofia-sm` | **Route.** C'est le fichier de process, il est a elle |
| **Ajout du critere de relecture** a la fiche de `relecteur-pedagogique` | `sofia-sm`, apres l'amendement du README | **Route** |
| **Le nommage du fil d'animation** | **l'utilisatrice** | **Pose en section 6** |
| **Application immediate par moi** | `camille-po` | **Faite** : la provenance de l'enonce fautif est donnee sous la forme 3 en section 1 de ce document, ce qui est le premier emploi de la convention |

---

## 6. Le seul point que je ne fixe pas, et pourquoi il est a l'utilisatrice

**La convention elle-meme est de la gouvernance de depot, donc de mon perimetre avec `sofia-sm`, et
je l'ecris.** Un point ne l'est pas.

**La forme 3 nomme, dans un depot que l'utilisatrice lit, une source qui est son propre canal vers la
squad.** Trois choses en decoulent, et aucune n'est de moi :

- **Veut-elle que ce canal soit nomme dans le depot ?** Le nommer rend lisible qu'une partie de ce
  que la squad tient pour vrai n'a pas de source ecrite - ce qui est le but. Ne pas le nommer laisse
  le trou et laisse la prochaine fausse attribution se produire.
- **Sous quel nom ?** J'ecris *"le fil d'animation"* faute d'un terme existant : le depot emploie
  *"le coordinateur"* deux fois, a s06 et dans `DEC-0042`, sans l'avoir jamais defini nulle part.
  **Le terme est a elle, pas a moi.**
- **La borne de la section 3.2 lui convient-elle ?** *Un enonce de forme 3 ne vaut jamais motif.*
  C'est ce qui empeche la forme de servir a tout justifier ; c'est aussi ce qui interdirait a la
  squad de s'appuyer sur une consigne recue par ce canal sans l'avoir d'abord fait ecrire quelque
  part. **C'est contraignant pour elle autant que pour nous, et elle doit le savoir avant que ce soit
  la regle.**

**Tant qu'elle n'a pas repondu, la convention s'applique dans son fond** - trois formes, une seule
interdite, l'attribution inventee proscrite - **et le nom reste provisoire.** Le statut de cette
decision reste `Proposee` jusqu'a sa reponse. Un renommage ulterieur est un amendement d'une ligne ;
le trou, lui, est ferme des maintenant.

---

## 7. Ce que ce document ne fait pas

- Il **ne reecrit le document d'aucun agent** et ne corrige la redaction d'aucun livrable : c'est le
  perimetre de `relecteur-pedagogique` et de l'auteur de chaque document.
- Il **ne demande aucune sanction** et n'attribue aucune faute a `sofia-sm` : la section 2 dit
  pourquoi.
- Il **ne juge rien de pedagogique** et **ne tranche rien de technique**.
- Il **ne rouvre pas le perimetre de s24** et n'engage aucun developpeur.
- Il **ne corrige pas retroactivement** les enonces deja ecrits dans le depot sous une attribution
  incertaine. Un balayage retroactif serait un lot de relecture a part entiere, du meme genre que
  l'obstacle 6 du planning de s24 - **non engage, et nomme ici pour qu'il ne se decouvre pas.**

---

## Amendement du 2026-08-31 (meme jour) - l'utilisatrice repond, le canal s'appelle MASTER, et une collision de lecture est signalee

### A.1 Ses trois reponses, recopiees telles quelles

| # | Le point de la section 6 | Sa reponse, verbatim |
|---|---|---|
| `Q4` | Veut-elle que ce canal soit nomme dans le depot ? | *"oui"* |
| `Q5` | Sous quel nom ? | *"MASTER"* |
| `Q6` | La borne *"un enonce de forme 3 ne vaut jamais motif"* lui convient-elle ? | *"oui"* |

**La numerotation `Q4` a `Q6` n'est pas de moi** : la section 6 posait trois points sans les
numeroter. Elle m'est parvenue **de MASTER, sans source dans le depot**. Je la reprends parce
qu'elle est univoque, et je le signale parce que c'est exactement ce que cette convention demande de
faire.

### A.2 Ce que la convention devient

La troisieme forme prend son nom definitif :

> **`recu de MASTER, sans source dans le depot`** - avec l'enonce cite mot pour mot et la date a
> laquelle il a ete recu.

**Les deux bornes de la section 3.2 sont confirmees par `Q6`, en connaissance de ce qu'elles coutent
a celle qui repond** : un enonce recu de MASTER ne fonde aucune `DEC`, aucun `ADR`, aucun critere
d'acceptation, aucun verdict ; et il est cite mot pour mot, jamais resume. **Une consigne recue par
ce canal et qui doit fonder quelque chose devra d'abord etre ecrite quelque part.** C'est
contraignant pour MASTER autant que pour la squad, et cela a ete accepte en le sachant.

**Le geste correct de la section 3.3 se relit desormais ainsi**, dans les mots de `sofia-sm` :

> *"Un chiffre faux - de 28 a 33 lignes sur 43 - a servi d'appui a l'arbitrage de l'utilisatrice.
> **Recu de MASTER, sans source dans le depot.** Il n'est de personne de cette squad, et je le
> corrige ici : le depart est 24, le gain est nul sur les 23 lignes instruites."*

### A.3 La collision, verifiee et signalee plutot que contournee

**Verification faite sur l'arbre suivi**, methode nommee : `git grep -ni 'master'` sur l'index
complet, puis tri des occurrences.

- **46 occurrences de "master" dans l'arbre suivi. Les 46 sont "Scrum Master", et les 46 designent
  `sofia-sm`.** Les deux qui n'apparaissent pas dans un tri sur la chaine complete sont les deux ou
  le retour a la ligne coupe l'expression entre "Scrum" et "Master".
- Elles vivent aux endroits les plus lus du depot : `.claude/agents/sofia-sm.md` (quatre fois),
  `CLAUDE.md`, `squad/EQUIPE.md` (deux fois), **`squad/README.md` section 8, la table meme des
  prefixes de commit**, et une trentaine de documents de ceremonie.
- **Aucune branche `master` dans ce depot** : la branche par defaut est `main`, la reconstruction vit
  sur `round-two`. Aucune collision de ce cote.

**La collision est donc unique et elle est de lecture** : *MASTER* et *Scrum Master* designent deux
choses differentes, et **la seconde designe `sofia-sm`**. Or **le defaut que cette convention corrige
est precisement une fausse attribution a `sofia-sm`.** Un nom qui, mal lu, renvoie a la personne a qui
l'on avait attribue l'enonce a tort est le seul nom dont je devais verifier qu'il ne renvoyait pas a
elle. **Je le dis franchement plutot que de l'arranger.**

**Ce que je fais, et je ne change pas le nom** : le nom est celui que l'utilisatrice a donne, il est
a elle, et la collision est de lecture et non de fond - elle ne casse rien, elle peut seulement faire
trebucher un lecteur presse. Deux regles d'ecriture suffisent a la rendre non viable, et elles ne
touchent pas au nom :

1. **MASTER s'ecrit toujours en capitales, jamais "Master".** *Scrum Master* garde sa capitale
   simple, comme dans les 46 occurrences existantes, qui n'ont donc pas a etre touchees.
2. **MASTER n'apparait jamais seul, mais toujours dans la formule fixe** *"recu de MASTER, sans
   source dans le depot"*. Cette construction n'existe nulle part pour *Scrum Master* et ne peut pas
   se confondre avec elle.

**Ce que je ne fais pas** : je ne renomme rien, je ne touche a aucune des 46 occurrences existantes,
et je ne repose pas la question. **Je signale le fait une fois** : si l'utilisatrice, le sachant,
prefere un autre nom, c'est un amendement d'une ligne ici et une ligne dans `DEC-index.md`.

### A.4 MASTER n'entre pas dans la table des prefixes de commit, et c'est voulu

**MASTER n'ecrit jamais dans le depot** - c'est la raison meme pour laquelle ses enonces n'avaient
pas de trace, et c'est le trou que cette convention ferme. Il n'a donc **pas** de tag de commit et
`squad/README.md`, section 8, n'a pas a en recevoir un.

**Consequence a nommer, parce qu'elle ressemble a un manque et n'en est pas un** : la CI rejette un
message de commit qui ne commence pas par un tag connu. **Un commit prefixe `[MASTER]` serait donc
rejete, et c'est le comportement correct** - un enonce de MASTER n'entre dans le depot que porte par
un agent, sous le tag de cet agent, avec la forme 3 attachee. Ce n'est pas un garde-fou a ajouter,
c'est un garde-fou existant qui mord dans le bon sens.

### A.5 Ce que cet amendement route

| Ce qui est du | Proprietaire | Etat |
|---|---|---|
| Amendement de `squad/README.md` avec la convention et le nom MASTER | `sofia-sm` | **Route.** Le fichier de process est a elle ; le statut `Acceptee` rend l'amendement du a partir d'aujourd'hui |
| Ajout du critere de relecture a la fiche de `relecteur-pedagogique` | `sofia-sm` | **Route**, apres l'amendement du README |
| Correction de la section 4 du planning de s24 sous la forme 3 nommee | `sofia-sm` | **Route**, inchange |
| Mecanisation eventuelle de la resolution des references de forme 1 | `theo-archi`, `yanis-qa` | **Question posee, pas une demande**, inchangee |

---

## Amendement B du 2026-08-31 (cloture de s24) - la citation se fige a la reception : la regle que l'ecart *"fera"* / *"ferait"* a fait manquer

### B.1 L'ecart, et qui l'a trouve

`relecteur-pedagogique` a trouve que **le meme enonce recu de MASTER est cite differemment dans deux
documents** :

| Ou | Comment il est cite |
|---|---|
| `DEC-0062`, section 1 (moi, 2026-08-31) | *"...la saisie **fera** passer la couverture..."* |
| `planning.md` de s24, `review.md`, `RAPPORT.md` (`sofia-sm`, 2026-08-30 et 2026-08-31) | *"...la saisie **ferait** passer la couverture..."* |

**Elle a nomme l'ecart et a refuse de le lever**, au motif que choisir fabriquerait la citation.
**C'est exactement la bonne conduite**, et c'est ce refus qui rend le trou visible : ma section 3.1
exige la citation *"mot pour mot"* et ma section 3.2 interdit de resumer, mais **aucune des deux ne
dit qui fige les mots, ni quand**. La convention se faisait donc violer par le premier document qui
l'appliquait - le mien.

### B.2 Les deux regles qui manquaient

**`3.5` - la citation se fige a la reception, et une seule fois.**

> **Le premier agent qui ecrit dans un fichier commite un enonce recu de MASTER en fige la
> citation**, dans les mots ou il l'a recu et au moment ou il le recoit - jamais reconstitue apres
> coup, jamais de memoire.
>
> **Tout agent ulterieur cite ce fichier-la, et sa citation devient de forme 1** (`de <agent>`, avec
> le fichier et la section). Il ne recite pas sa propre reception.

C'est le geste qui ferme structurellement la faille plutot que de la rattraper : apres le premier
gel, **il n'existe plus qu'une seule source dans le depot**, et une divergence devient impossible au
lieu d'etre detectable.

**`3.6` - le depart d'egalite, pour les ecarts deja crees.**

> **Quand deux documents citent differemment un enonce de forme 3 et que la reception n'est pas dans
> le depot, l'occurrence commitee la plus ancienne gouverne.** Les autres y sont alignees par une
> correction datee qui n'efface pas le texte corrige.
>
> **Le motif n'est pas l'arithmetique** - ce n'est ni un vote ni une majorite - **c'est
> l'anteriorite** : la premiere occurrence est la seule qui ne soit pas une retranscription. Elle se
> determine par `git log`, donc par un tiers, sans arbitrage.
>
> **Si l'anteriorite ne se determine pas** - meme commit, ou deux branches sans ordre - **aucune
> version n'est promue** : l'enonce est rapporte indirectement, sans guillemets, et l'ecart est
> ecrit.

### B.3 Application immediate a l'ecart trouve

**`ferait` gouverne**, parce que `planning.md` de `sofia-sm` (2026-08-30) precede ma `DEC-0062`
(2026-08-31). **Ma section 1 est corrigee et la correction est marquee, pas effacee.**

**Je ne me couvre pas derriere la regle** : l'ecart est de ma main, et sa cause est nommee dans le
document meme qui l'interdisait. J'ai ecrit cette citation **a l'interieur d'une phrase courante**,
en la reconstituant plutot qu'en la relevant. C'est precisement le geste que la forme 3 est censee
empecher, commis par l'auteure de la forme 3, sur sa premiere application. **Une convention dont la
premiere violation est le fait de celle qui l'ecrit n'est pas discreditee - elle est renseignee** :
elle montre ou elle etait trop courte.

### B.4 Ce que cela rend verifiable

Les deux regles sont **binaires et n'appellent aucun jugement**, ce qui est la condition posee par la
section 4 :

- une citation de forme 3 apparait-elle plus d'une fois dans le depot avec des mots differents ?
  C'est une comparaison de chaines ;
- si oui, la plus ancienne gouverne-t-elle ? C'est `git log`.

**Je ne dis pas que c'est mecanisable**, et je ne le demande pas : la question reste posee a
`theo-archi` et `yanis-qa` comme en section 4, ni plus ni moins.

---

## Amendement C du 2026-08-31 - la charte de `relecteur-pedagogique` reste a amender, et je n'y touche pas

`relecteur-pedagogique` a releve contre `DEC-0062` un defaut de sa premiere version : **elle se
defendait d'etre un garde-fou annonce mais absent en affirmant qu'il verifiait la convention, alors
que la convention n'existait dans aucun fichier qu'il lit.** La section 4 disait *"elle est verifiee
par `relecteur-pedagogique`"* - au present - d'une regle ecrite le jour meme et qui ne figurait ni
dans `squad/README.md`, ni dans sa charte. **C'est le defaut nomme en tete de `CLAUDE.md`, commis
dans le paragraphe qui s'en defendait.**

`sofia-sm` a ferme la moitie process en amendant `squad/README.md`. **La charte de l'agent, elle,
reste a amender**, et elle a refuse d'y toucher au motif que c'est de la configuration d'agent.

**Je ne la modifie pas non plus, et pour deux motifs que je separe :**

1. **Ce n'est pas mon perimetre.** `.claude/agents/` porte la definition des agents ; je n'ecris ni
   dans le code, ni dans la configuration de qui que ce soit.
2. **Et c'est le meme motif qui vaut dans les deux sens** : je viens d'ecrire, en section 5, que je
   ne reecris pas le document d'un autre agent. Une charte est plus que son document, c'est ce qui le
   definit.

**Route a `sofia-sm`**, qui tient `squad/README.md` et le roster, pour arbitrage de la voie - et a
l'utilisatrice si `sofia-sm` juge qu'une charte d'agent ne se modifie pas sans elle.

**En attendant, la section 4 doit se lire au conditionnel, et je le marque ici plutot que de la
reecrire** : la convention est **due** a la relecture, elle n'y est pas encore opposable. **Une
convention qui n'est pas dans la charte de celui qui doit la verifier n'est pas verifiee**, et ce
document ne pretendra pas le contraire une seconde fois.

## Amendement D du 2026-10-13 (`camille-po`) - la formule de la forme 3 tolere l'accord au pluriel

La formule *"recu de MASTER, sans source dans le depot"* tolere l'accord au pluriel (*"recus de MASTER"*, quatre messages) : ce n'est pas une quatrieme forme. Source : les mots de l'utilisatrice du 2026-10-13, *"oui pour Sofia"*, sur la lecture de `sofia-sm` (`DEC-0116` A.35) ; pas de balayage retroactif (section 7).

## Amendement E du 2026-10-21 (`camille-po`) - renvoi : les enonces relayes aux `DEC-0120`, `DEC-0123` et `DEC-0124` passent de la forme 3 a la forme 2

**Statut de `DEC-0062` : `Acceptee`, inchange.** Aucun texte d'origine n'est reecrit.

**La question**, telle que l'agent lanceur l'a posee a l'utilisatrice le 2026-10-21 : *"Confirmez-vous, tels quels, les mots que je vous ai relayes dans les decisions du palier 3 et du palier 4 ? Ils sont : "ok" a ma recommandation sur AD1, AD2 et AD4 ; vos reponses a Q-05, Q-06, Q-07, Q-08, Q-09 et Q-10 ; "il n'y aura pas de correction automatique du clavier" ; "pour moi c'est bon R6 peut etre leve" ; "pour le palier 4 augmente le seuil avec des instances en // jusqu'a 3 si besoin"."*

**Sa reponse**, 2026-10-21, relayee par l'agent lanceur a `camille-po` (l'utilisatrice l'a donnee d'un seul mot) : *"confirme"*, en toutes lettres *"confirmé"*. **Je ne l'ai pas lue de sa main** : elle est relayee, et je le dis. Ce qui est cite ici est donc la question et la reponse **telles que l'agent lanceur me les a transmises**.

**Ce que cela fait a `DEC-0062`** (3.1 et 3.2 : la forme 2, *"de l'utilisatrice"*, porte ses mots cites entre guillemets et la date, et vaut motif ; la forme 3, sans source dans le depot, ne vaut jamais motif). **A la date du 2026-10-21, les enonces de la liste ci-dessus passent de la forme 3 a la forme 2** : leurs mots sont cites, ils sont confirmes mot pour mot, et la date est celle-ci. L'ecart 9 de `releve-relecteur-cloture-palier-3.md` (*"relayee"* n'est pas l'une des trois formes) est traite **pour la liste, et pour elle seule**.

**Ce qui est couvert** : exactement les enonces de la liste, quels que soient les fichiers qui les citent : le *"ok"* sur `AD1`, `AD2`, `AD4` ; les reponses a `Q-05`, `Q-06`, `Q-07`, `Q-08`, `Q-09`, `Q-10` ; *"il n'y aura pas de correction automatique du clavier"* ; *"pour moi c'est bon R6 peut etre leve"* ; *"pour le palier 4 augmente le seuil avec des instances en // jusqu'a 3 si besoin"*. **Les mots seulement.**

**Ce qui n'est pas couvert** :
- **Les lectures que les agents en ont faites.** Ma lecture de la consigne sur le seuil (la borne de 8,0 de `PAR-9`, les instances A, A' et B) reste **a confirmer** ; `QP3`, `QP5`, `QP6`, `QP7` de `preparation-palier-4-camille.md` restent **sans reponse**. Les lectures de `DEC-0123` section 0 (ma colonne *"Ma lecture"*) et de `DEC-0124` section 0 ne sont pas confirmees.
- **Ce que l'utilisatrice a verifie ou non** : la confirmation ne dit rien du jar joue sur la tablette ni de l'ordinateur pour `R6` : la limite *"l'ordinateur n'est pas mesure"* reste ecrite.
- **Tout autre enonce relaye**, dans ces fichiers ou ailleurs (`DEC-0120` A.114 et ses mots de `S2` et de `Q-N4`, `Q-U11`, les paliers 1 et 2, tout enonce hors de la liste) : **pas de balayage retroactif au-dela de la liste.** `E5` (la regle pour une reponse relayee) **reste ouvert** pour le reste.
- **La provenance du relais** : la confirmation ne dit pas par quel canal les mots sont arrives a l'agent lanceur la premiere fois.

**Statut** : aucune decision ne change, aucun texte d'origine n'est reecrit, aucune ligne de matrice ne bouge. Aucun identifiant de condition neuf.

**Renvoi** : l'amendement du meme jour de `DEC-0120` (A.132), de `DEC-0123` et de `DEC-0124` porte la question et la reponse. **Sans balayage retroactif au-dela de la liste** (section 7 de ce fichier, amendement D).

## Amendement F du 2026-10-21 (`camille-po`) - ce que l'amendement E laisse ouvert de `E5`, et la regle que j'applique en attendant

**Statut de `DEC-0062` : `Acceptee`, inchange.** Aucun texte d'origine n'est reecrit. Source : l'ecart 9 de `releve-relecteur-cloture-palier-3.md`, dont E ne traite que la liste.

**Ce que E ne dit pas, et que je dis.** La confirmation *"confirme"* est elle-meme arrivee par l'agent lanceur (amendement E, deuxieme paragraphe). Si un enonce que l'agent lanceur me transmet est de forme 3, sa confirmation transmise de la meme facon l'est aussi, et le passage de la liste a la forme 2 ne se tient pas par ses propres moyens. Il se tient **seulement si l'utilisatrice tient pour siens les mots que l'agent lanceur donne comme les siens**. Aucun fichier du depot ne le dit. E reste valable comme etat de fait (les mots de la liste sont cites, confirmes, dates) ; il ne ferme pas `E5`.

**Ce qui reste ouvert**, une fois E compte :

1. **La regle pour une reponse relayee** (`E5`, ouvert depuis `cloture-palier-1.md`, reporte a `cloture-palier-2.md`, reporte a `cloture-palier-3.md` : **troisieme palier**). Elle est a moi ; je l'ecris ci-dessous.
2. **Les enonces hors liste** : la delegation du 2026-10-19 (*"continue au mieux pour obtenir la meilleure couverture"*), `DEC-0120` A.114 (mots de `S2` et de `Q-N4`), `Q-U11`, les paliers 1 et 2. Forme 3 a la lettre d'A.2 ; pas de balayage retroactif (section 7).
3. **Le canal** : par quel moyen les mots arrivent la premiere fois a l'agent lanceur. A l'utilisatrice (`cloture-palier-3.md` section 13 (h)).

**La regle que j'applique (`E5`), pour mes propres ecrits.**

- *"Relayee"* n'est pas une forme et je ne l'emploie plus seul. Un enonce de l'utilisatrice que je tiens de l'agent lanceur s'ecrit **en forme 3** (formule fixe d'A.2, mots cites, date), ou **en forme 2 apres une confirmation demandee en citant ces mots**, comme E, avec le fait dit que la confirmation est relayee.
- Un enonce de forme 3 **ne fonde rien** (3.2) : ni `DEC`, ni critere, ni choix de story. Quand un choix de produit s'appuie sur lui, son motif est ecrit ailleurs (un texte d'un agent, forme 1), ou le choix attend.
- **Application immediate** : dans `S57-BR-MAT-CE1-14-GRILLE`, la phrase qui motive le choix de la forme (b) par la delegation du 2026-10-19 perd ce motif ; le motif reste le texte de `nadia-prof` (`arbitrage-nadia-q05-q10.md` 1.2 : (b) seule ferme la ligne en `couvert-valide`). Voir l'amendement du meme jour au backlog.
- Cette regle vaut pour ce que j'ecris. Elle ne dit rien pour les autres agents : `sofia-sm` la porte a `squad/README.md` si l'utilisatrice repond oui a la question ci-dessous.

**La question, une seule, a l'utilisatrice** : *quand l'agent qui lance une tache me transmet une reponse de votre part, vaut-elle vos mots (forme 2 : cites entre guillemets, avec la date), ou reste-t-elle recue de MASTER (forme 3) tant que vous ne l'avez pas ecrite vous-meme ?* Sans reponse, la regle ci-dessus tient et `E5` reste compte comme ouvert. Route : `sofia-sm`, section 13 (h) de `cloture-palier-3.md`.
