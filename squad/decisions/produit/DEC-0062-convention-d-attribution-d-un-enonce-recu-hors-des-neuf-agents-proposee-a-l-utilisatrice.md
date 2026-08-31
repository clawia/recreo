# DEC-0062 - Convention d'attribution d'un enonce recu hors des neuf agents : le depot n'en a
# aucune, et ce manque a produit une fausse attribution le 2026-08-30. Convention proposee, nommage
# rendu a l'utilisatrice

- **Type** : gouvernance de depot - convention de redaction et d'attribution
- **Date** : 2026-08-31
- **Auteur** : camille-po, en partage de perimetre avec `sofia-sm`
- **Sprint** : s24 (en cours)
- **Competence programme visee** : **transverse - pas de contenu pedagogique.**
- **Statut** : **Proposee.** La convention est ecrite pour etre appliquee telle quelle ; **un seul
  point est rendu a l'utilisatrice** et il est en section 6.

## 0. Ce que ce document est

Le depot possede une regle d'attribution pour ses neuf agents - un tag de commit par agent, rejete
par la CI s'il est absent - et une pratique constante pour l'utilisatrice, dont les arbitrages sont
cites entre guillemets, dates, et traces en `DEC`. **Il n'a rien pour un enonce recu d'aucun des
deux.** C'est ce trou qui est instruit ici.

Il ne rouvre pas le perimetre de s24, ne modifie le document d'aucun agent, et n'engage aucun
developpeur.

---

## 1. Le cas, avec sa provenance declaree

**L'enonce fautif** : *"l'ouverture de la saisie fera passer la couverture de 28 a 33 lignes sur
43"*. Il a servi d'appui a l'utilisatrice pour arbitrer l'ouverture de la mecanique de saisie.

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
