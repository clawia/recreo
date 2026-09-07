# Avis de faisabilite technique - s31 (`theo-archi`)

> **Le nom de ce repertoire appartient a `sofia-sm`.** Il est ecrit ici pour que l'avis existe avant
> le planning ; si elle nomme le sprint autrement, le fichier suit le repertoire et rien d'autre ne
> change.

## 0. Ce sur quoi tout ce qui suit a ete mesure

**Worktree dedie `s31-theo`, cree sur `2ef583ba` (`git worktree add ../recreo-s31-theo -b s31-theo
2ef583ba`), lignee verifiee.** Toute mesure du reacteur - y compris ponctuelle - est faite depuis ce
worktree et depuis aucun autre, amendement de `squad/README.md` ecrit a la retro de s30. Les
worktrees des autres agents sont des repertoires freres et ne sont jamais balayes.

**JDK 21.0.10**, version **rapportee par la machine virtuelle qui execute les tests**, jamais par
l'entree de toolchain qui la designe (`ADR-0071`). Commande : `bash outils/mvn-jdk21.sh -B clean
verify` sur la racine ; le reacteur y est complet par construction, aucune mesure de cet avis n'est
partielle.

**Chaque chiffre de ce document porte sa methode dans le meme paragraphe.** C'est la regle que j'ai
enfreinte a s30 sur le compte des referentiels et que `sofia-sm` a corrigee en recomptant : un total
exact sur une paire fausse ne prouve rien.

---

## 1. `PAR-5` - le registre est ecrit, et voici son compte et sa methode

**Etat : paye.** `squad/decisions/REGISTRE-identifiants.md`, `ADR-0085`, garde-fou
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, **4 tests verts**, morsure des quatre
clauses sur un depot fautif.

### Le compte, et il n'est pas celui qu'on m'a transmis

**523 identifiants de condition, sur 83 prefixes, 14 889 occurrences dans 520 fichiers.**

*Methode : extraction du motif `PREFIXE-[R]nombre` - prefixe de deux a cinq lettres majuscules,
numero d'un ou deux chiffres, non colle a un mot ni a un tiret - sur les 1 198 fichiers texte du
depot, les repertoires de sortie de build, les arbres de contre-exemple et les fichiers binaires
exclus, ces derniers reconnus a l'octet nul et non a leur extension ; puis retrait des prefixes
`ADR` et `DEC`, qu'`ADR-0066` alloue deja, et d'un homonyme d'encodage. Dedupliquee. Worktree
`s31-theo` a `2ef583ba`, JDK 21.0.10.*

**Le chiffre de 147 qu'on m'a transmis n'est pas faux, et je n'ai rien corrige.** `nadia-prof` l'a
mesure le 2026-09-05 sur *"les dix-huit referentiels et les arbitrages prealables de s25 a s29"* :
147 identifiants sur 30 prefixes. Le mien porte sur le depot entier et sur toute son histoire ; il
ajoute les fiches de conformite, les arbitrages anterieurs a s25, le backlog, les rapports de
sprint, les chartes d'agent et le code. **Les deux sont justes sur leur perimetre**, et l'ecart est
entierement explique par lui.

**Le registre porte le perimetre large, et c'est une decision, pas une commodite** : une collision
ne demande pas la permission d'etre dans le perimetre de quelqu'un. Les dix identifiants que
l'instance B a ajoutes a s30 (`SIG-R1`, `SIG-R2`, `MEL-R1`, `MEL-R2`, `MEL-R3`, `POS-R3`, `CHA-R1`,
`OBT-R1`, `OBT-R2`, `PAR-11`) y sont, comme les 513 autres.

### Ce que le registre tient, et ce qu'il ne tient pas

| Il tient | Il ne tient pas |
|---|---|
| Deux allocations du meme identifiant sont rouges - **c'est la collision du 2026-09-03** | Que deux identifiants distincts ne portent pas la meme condition sous deux noms |
| Un identifiant ecrit sans sa ligne est rouge - **c'est l'allocation visible avant d'ecrire** | Que la condition allouee soit bien celle qu'on croit : le sens se lit, l'identite se tient |
| Une ligne dont le document ne parle plus est rouge | |
| Un homonyme ecarte que plus rien ne porte est rouge | |

### Trois formes essayees et abandonnees, sur mesure et non sur intuition

Ecrites parce qu'elles seront reproposees si je ne dis pas pourquoi elles tombent.

1. **Deriver l'allocation d'un marqueur de definition deja present dans les textes** - la forme la
   moins couteuse, zero saisie. **Juste a 55 % sur le materiau reel** : le marqueur gras-tiret donne
   un site unique pour 291 identifiants sur 524, plusieurs sites pour 103, aucun pour 130. Une
   derivation juste a 55 % est un generateur de faux rouges.
2. **Un registre par prefixe portant le plus grand numero alloue** - 83 lignes au lieu de 523.
   **Elle ne mord pas la collision** : deux instances qui allouent le meme numero portent toutes
   deux le plafond a la meme valeur, la fusion des deux textes est identique, rien ne rougit.
3. **Un registre qui recopie le sens de chaque condition.** Refusee par `ADR-0061` : un second
   exemplaire du sens rancit au premier amendement.

### Ce que cela coute, et je le dis avant qu'on le decouvre

- **523 lignes payees une fois** ; le fichier fait 588 lignes.
- **Une ligne de registre par condition neuve, dans le meme commit.** C'est deliberement une
  friction : une allocation qui ne coute rien n'est pas visible.
- **La prose qui parle des identifiants est contrainte.** `ADR-0085` en a fait les frais avant
  d'etre commite : sa premiere redaction epelait quatre identifiants d'exemple qui n'existent pas,
  et le garde-fou l'a refusee. Un texte qui discute d'une allocation la decrit desormais au lieu de
  l'epeler. C'est une gene reelle, et c'est le revers exact de ce qui fait mordre la clause.
- **Le garde-fou a trouve un defaut dans sa propre source a sa premiere execution** - troisieme
  balayage du depot dans ce cas. Je l'ecris aussi clairement que le vert qui a suivi.

### Ce que je ne prononce pas

**La levee de la retenue de 1,0 est celle de `nadia-prof`, pas la mienne.** Elle a nomme deux
conditions : un second sprint parallele sans defaut de couture, **et** ce registre. La seconde est
remplie du cote technique au 2026-09-07. **Je remplis la condition ; je ne prononce pas sa
consequence**, et je ne compte pas +1,0 dans une borne qui ne m'appartient pas.

---

## 2. Les garde-fous de la famille qui coute le plus cher

**Constat de `nadia-prof`, et il porte** : la reprise de s30 n'a ajoute **aucun** garde-fou sur les
proprietes que ses deux refus ont fait payer - contenu corrige, un commentaire d'exclusion mis a
jour, rien de plus.

**J'en ecris deux sur les cinq qu'elle nomme.** `ADR-0086`,
`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`, **7 tests verts, 3 morsures**.

*Toutes les mesures de cette section portent sur les **55 paquets a propositions et 1 077 items** du
contenu servi, charges depuis les repertoires de contenu des modules declares au pom agregateur, et
sur l'**etat de l'historique** ou les deux refus ont ete prononces - `712c293b` et `5824e752`, avant
la reprise de `lea-dev`.*

### 2.1 Les deux qui sont ecrites

| Clause | Ce qu'elle interdit | Rouges sur le contenu servi | Morsure |
|---|---|---|---|
| **1** - un item n'est pas servi plusieurs fois sous plusieurs noms | trois items ou plus au meme moule de propositions, portant tous la meme bonne reponse d'au moins trois mots | **0** sur 41 groupes examines, 264 items | **l'etat reel du depot a `712c293b`** : le paquet que `nadia-prof` a refuse sous `SCI-CM1-17` |
| **2** - l'amorce d'un distracteur ne le distingue pas d'une bonne reponse | quatre distracteurs ou plus au meme moule d'amorce quand aucune bonne reponse ne le partage | **1** sur 142 amorces : `cm1-emc-fait-opinion`, **8 sur 10** | le paquet servi lui-meme, son exemption retiree - **le compte exact de `nadia-prof`** |

**La morsure de la clause 1 n'est pas une fixture de ma main, et c'est le point.** C'est le fichier
tel qu'il vivait dans `git` le jour du refus. Une fixture ecrite par l'auteur d'une regle ressemble
a ce qu'il imagine ; celle-ci ressemble a ce que ce depot a reellement produit - la lecon de s05,
appliquee a moi.

**La clause 2 arrive avec un cliquet a plafond exact**, parce que `FOP-R4` est ouverte et non payee
et que le paquet reste servi et valide : **un paquet exempte, nomme, date, plafond a un**. Le
garde-fou verifie que l'exempte est **encore reellement fautif** ; le jour ou `FOP-R4` est payee,
l'exemption rougit et doit disparaitre dans le meme diff.

### 2.2 Ce que la clause 1 a coute a trouver, et qui doit etre lu avant de la modifier

**La formulation evidente est fausse, et c'est mesure.** Premiere ecriture : *deux items dont les
enonces coulent dans le meme moule*. Resultat sur le contenu servi : **plus de 120 paires rouges
dans 8 paquets valides**, et **le paquet refuse n'est pas mordu**.

Le cas qui tranche : *"Un chat, qu'est-ce que c'est ?"* contre *"Un arbre, qu'est-ce que c'est ?"*
(`cp-qlm-vivant-non-vivant`, valide) a **exactement la forme textuelle** de *"Que se passerait-il si
le renard disparaissait ?"* contre *"... si le lapin disparaissait ?"* (refuse). **Substituer un nom
dans une question est un classement legitime quand la reponse change avec le nom, et une repetition
quand elle ne change pas.** La difference n'est pas dans l'enonce. La clause 1 ne le regarde donc
pas du tout.

**Le plancher de trois mots sur la reponse repetee n'est pas un reglage de confort.** Sans lui, trois
paquets valides rougissent (`cp-voyelle-du-son`, `cp-lettre-du-son`, `cp-qlm-vivant-non-vivant`) :
leurs reponses repetees sont des **etiquettes de categorie** - une lettre, un son, *non* - et repeter
une etiquette **est** la tache. Le garde-fou prononcerait alors un verdict pedagogique, ce qu'il n'a
pas le droit de faire. *Marge du reglage, dite plutot que tue : a deux mots, le contenu servi reste
vert ; le plancher porte donc un mot de marge et n'est pas pose sur la valeur qui fait juste passer.*

### 2.3 Les trois que je n'encode pas, avec leur mesure

**Chacune a ete ecrite, executee et mesuree avant d'etre ecartee.**

**a. *La bonne reponse apparait mot pour mot dans son propre enonce*.** 39 items rouges sur 1 077,
dans 10 paquets - dont **12 sur 14** dans `cp-qlm-etats-eau`, ou la question alternative *"est-ce
solide ou liquide ?"* enumere ses deux reponses par construction. Et **zero** des items que
`nadia-prof` a effectivement signales sous ce motif (`SIG-R1`, `MEL-R2`). **Le critere textuel et la
propriete pedagogique n'ont pas la meme population et ne se recouvrent nulle part.** Une regle qui
rendrait rouges 39 items sans en attraper un seul de ceux qui ont fait ouvrir une reserve n'est pas
la regle de cette propriete.

**b. *Un item de cycle 3 redit un item de cycle 2* (`CYC-1`).** 280 082 couples examines (439 items
CP, 638 items CM1) : **zero** couple a enonce du meme moule et meme bonne reponse. L'item que
`nadia-prof` decrit comme *"un item de CP re-servi a l'identique"* n'est pas identique au sens
textuel - *"Quelle partie du velo sert a tourner ?"* au CP contre *"Dans un velo, quelle piece sert
a diriger la roue avant ?"* au CM1. **Son identite est pedagogique**, et ce n'est pas une chaine. La
mesure voisine - une meme bonne reponse d'au moins deux mots servie aux deux niveaux - rend **3
croisements**, tous dans du contenu qu'elle a valide : une regle bloquante devrait les exempter,
c'est-a-dire declarer dette ce qu'elle a valide. **Ce n'est pas mon geste.**

**c. `OBJ-R2` - *les distracteurs d'un item sont les bonnes reponses des autres items*.** C'est
celle que je voulais le plus encoder : elle a produit le meme defaut deux fois en deux jours sur
deux niveaux, et `nadia-prof` ecrit *"ce n'est plus une reserve de paquet, c'est un reflexe de
fabrication"*. Le proxy testable **reproduit sa mesure a la main** sur `cp-qlm-objet-technique` -
deux items sur cinq, exactement sa barre. Et il **echoue dans les deux sens sur le reste** :

| Mesure | Resultat |
|---|---|
| Paquets servis a moins de deux items porteurs | **16 sur 55**, dont `cp-le-bon-son`, `cp-lettre-du-son`, `cp-nature-mot`, `cm1-tables-multiplication` - tous valides, tous a ensemble ferme par la competence elle-meme |
| Paquet refuse pour ce motif exact | **vert**, parce que *"a tailler un crayon"* et *"a tailler la pointe d'un crayon"* ne sont pas la meme chaine |

**Une regle qui blanchit le paquet refuse et accuse seize paquets valides ne mesure pas la propriete
qu'elle porte.** Le mot portant d'`OBJ-R2` est **plausible**, et la plausibilite n'est pas une
chaine. Elle reste entierement a `nadia-prof`.

> **Ce que cela laisse ouvert, et je l'ecris plutot que de laisser croire couvert** : `OBJ-R2` est
> la propriete qui produit le plus de reserves depuis quatre sprints, et **elle n'a toujours aucun
> garde-fou**. La clause 1 n'en couvre que le cas degenere. **Le declencheur qui la rendrait
> encodable est nomme** : si un paquet **declarait ses taches** au lieu de les laisser deviner - ce
> que `FER-3` compte a la main -, les deux clauses se calculeraient par tache et `OBJ-R2` le
> deviendrait peut-etre a son tour. Cela commence par une decision de contenu, pas par une regle.

---

## 3. Faisabilite des deux lots

### 3.1 Sciences et technologie CM1, second passage - **faisable sans une ligne de code hors contenu**

**Methode, dans le meme paragraphe que le verdict.** (a) Releve des lignes deja servies par lecture
du champ `lignesDeReferentiel` des paquets du contenu servi : **4 lignes fermees sur 20** -
`SCI-CM1-02` (`cm1-sciences-melanges`), `SCI-CM1-05` (`cm1-sciences-signal-information`),
`SCI-CM1-12` (`cm1-sciences-objet-technique`), `SCI-CM1-17` (`cm1-sciences-chaine-alimentaire`) ;
**16 restent**. (b) Confrontation de chacune des 16 au schema d'item reellement lu par le chargeur
de production. (c) Verification que le referentiel porte ses codes : `grep -cE '^- \[...\] '` sur
`squad/programme/sciences-et-technologie/competences-cm1.md` - **20 codes sur 20 lignes**, alloues a
s30 par `DEC-0079`.

**Verdict : les 16 lignes restantes sont du contenu pur**, avec **deux declencheurs nommes**, aucun
des deux realise par un second passage ordinaire :

| Declencheur | Ce qu'il couterait | Statut |
|---|---|---|
| `SCI-CM1-20`, *"extraire une information d'un document (texte, **tableau**, schema, graphique)"* | une cle de schema, une composante de `Question`, un attribut de modele, un bloc `<table>` avec ses en-tetes, une regle CSS, l'extension du garde-fou de cles (`ADR-0070`), et un arbitrage d'accessibilite qui n'existe pas. **Ce n'est pas une ligne de contenu, c'est une capacite de rendu**, du meme genre que le texte-support ou l'illustration - chacune a coute un `ADR` | **instruit a s30, non realise.** Ma recommandation est inchangee : servir la moitie textuelle sans code, instruire le tableau quand il aura un usage constate |
| `SCI-CM1-08`, *"**ordonner** les etapes du developpement"* | si `nadia-prof` exige un vrai geste d'ordonnancement, c'est une **quatrieme mecanique** ; le depot en a trois (`banditmanchot`, `designation`, `saisie`) | **non declenche** tant que la ligne est servie par des items de choix (*"quelle etape vient apres ?"*). **C'est son arbitrage, pas le mien** |

**Le piege de s30 reste valable et n'est toujours pas encodable** : un paquet qui poserait
`indicesNommentLaReponse: true` n'afficherait jamais son indice, et `SCI-1` serait silencieusement
non tenue. Un garde-fou devrait savoir quelles lignes sont sous `SCI-1`, ce que le contenu ne
declare pas. **Consigne d'ecriture, pas regle** - et je le repete parce qu'un piege signale une fois
et jamais encode se represente.

### 3.2 EMC CP - **faisable sans une ligne de code hors contenu, avec un cout non nul qui n'est pas du code**

**Methode identique.** **2 lignes fermees sur 14** - `EMC-CP-02` (`cp-emc-permis-interdit`),
`EMC-CP-06` (`cp-emc-regles-de-vie`) ; **12 restent**. Referentiel : **14 codes sur 14 lignes**,
alloues a s29 par `lea-dev`. Toutes les lignes restantes s'ecrivent dans le schema existant.

**Une ligne demande un geste qui n'est ni du code ni du contenu, et personne ne l'a nomme** :

> `EMC-CP-08` - *"**Reconnaitre** et nommer les symboles de la Republique francaise (drapeau
> tricolore, Marianne, La Marseillaise, la fete nationale)."*

- **Le verbe est *reconnaitre*.** Servi en mots seuls, l'item devient *"de quelle couleur est le
  drapeau ?"*, ce qui n'est pas la meme competence. Servi en image, il demande **une illustration**.
- **Le canal existe en code** : `illustration` + `illustrationTexteAlternatif` (`ADR-0073`), et le
  corpus de reproductions d'oeuvres du domaine public (`ADR-0082`). **Zero ligne de code.** Mais le
  depot sert **24 illustrations, toutes maison** (figures geometriques, monnaie) : il faut donc
  **produire un dessin et sa preuve**, ce qui est un geste d'`ines-design` sous les conditions `DV`,
  ou une reproduction sous la **double preuve** d'`ADR-0082`.
- ***La Marseillaise* n'est pas servable.** Le produit n'a aucun son, par decision. Elle se sert par
  la famille a voix (`ADR-0075`) - l'adulte lit, l'enfant repond - ou pas du tout. **C'est
  l'arbitrage de `nadia-prof`, je ne fais que dire ce que la technique offre.**

**Consequence de planning, et elle est chiffrable a l'avance** : un lot d'EMC CP qui prend
`EMC-CP-08` n'est plus un lot de contenu seul. **S'il l'evite, les onze autres lignes sont du
contenu pur.**

**Un point de tarif qui n'est pas le mien** : `DEC-0077` porte que l'EMC CP est mixte - 1,0 restreint
aux lignes 1 a 7, 1,5 au-dela. Deux lots d'EMC CP franchissent tres probablement cette borne.
**C'est le tarif de `nadia-prof` et la composition de `camille-po` ; je le signale pour qu'il ne soit
pas decouvert a la cloture.**

### 3.3 `SAI-R1` - cause trouvee, correctif specifie, et je ne le livre pas

**La cause, une ligne.** `adapters/adapter-web/src/main/resources/templates/fragments/saisie.html`,
ligne 120 :

```html
th:text="'Tu as écrit : ' + ${chaineJugee} + '.'"
```

Le gabarit ajoute un point **a une chaine dont il ignore la forme**. Quand l'enfant tape lui-meme un
point, l'ecran lui rend deux points a la suite, dans la phrase precise ou le produit lui renvoie son
propre geste.

**Pourquoi `ADR-0083` ne l'a pas attrape, et ce n'est pas un oubli** : sa mesure a explicitement
declare cette composition **saine**, parce que `chaineJugee` **n'est pas une valeur de contenu** -
c'est ce que l'enfant a tape. `ADR-0083` interdit d'enchasser une valeur de **contenu** ;
`SAI-R1` porte sur une valeur que le produit ne controle pas du tout. **Deux proprietes voisines, une
seule etait gardee.**

**Le correctif est de retirer douze caracteres**, ` + '.'`. Mesure du rayon, methode : releve de
toutes les concatenations de gabarit servi (`grep " + "` sur les six gabarits, puis tri entre les
expressions de texte et les expressions d'adresse).

| Objet | Compte |
|---|---|
| Expressions de gabarit qui **composent un texte lu par l'enfant** | **5** |
| Dont celles qui ajoutent un litteral **apres** une valeur du modele | **1** - celle-ci, et elle seule |
| Assertions de test a mettre a jour | **2**, dans `EcranDeSaisieTest` |
| Regles CSS a toucher | **0** |

**Et la forme juste est deja dans le meme fichier, deux lignes plus bas.** `.forme-de-reference` rend
`On écrit :` suivi d'un `<span>` qui porte la chaine brute, **sans ponctuation finale**, depuis s24.
Le correctif n'invente rien : il aligne `.chaine-jugee` sur son voisin immediat.

**La regle executable est specifiee et sa population est mesuree** : *aucune expression de gabarit
n'ajoute un litteral apres une valeur du modele*. Population 5, fautive 1, zero faux positif - les
quatre autres (`'Score : ' + ${...}` trois fois, `'Pour le ' + ${niveauActif}`) placent toutes la
valeur en dernier. La derivation est propre : elle porte sur les attributs qui produisent du
**texte** (`th:text`, `th:utext`), pas sur ceux qui produisent une **adresse** (`th:href`, `th:src`,
`th:attr`), lesquels concatenent legitimement apres une valeur.

**Je ne livre ni le correctif ni la regle, et j'ecris pourquoi plutot que de le taire.** Le
correctif change un **texte servi a l'enfant**. `nadia-prof` en a publie le prix d'avance -
*"mot pour mot une fois le correctif livre, 0,25"* - et ce 0,25 se prend sur une borne d'audit que
je n'alloue pas. **Un architecte qui livre un changement d'ecran hors lot depense le budget de
quelqu'un d'autre.** Tout est pret pour qu'un developpeur le fasse en un commit : la cause, les
douze caracteres, les deux assertions, la forme du voisin et la regle avec sa population.
**Ma recommandation : le seater dans la dette restreinte de s31, il vaut 0,25 et il ferme la
derniere instance vivante d'une famille qui a produit quatre defauts en trois sprints.**

---

## 4. Les huit referentiels sans codes - ce n'est pas mon geste, et voici ce qui doit l'accompagner

**Le fait est confirme par ma propre mesure**, et non repris.

*Methode : `grep -cE '^- \[[A-Z]{2,5}-[A-Z0-9]{2,4}-[0-9]{2}\] '` et `grep -cE '^- '` sur chacun des
dix-huit fichiers `squad/programme/*/competences-*.md`, un fichier a la fois, le compte imprime a
cote de son nom - pas une somme globale, pour que chaque terme soit lisible. Aucune exclusion : les
dix-huit sont comptes, y compris ceux a zero. Worktree `s31-theo` a `2ef583ba`.*

| Referentiel sans aucun code | Lignes |
|---|---|
| `arts-plastiques/competences-cp.md` | 11 |
| `arts-plastiques/competences-cm1.md` | 10 |
| `education-musicale/competences-cp.md` | 11 |
| `education-musicale/competences-cm1.md` | 12 |
| `education-physique-et-sportive/competences-cp.md` | 11 |
| `education-physique-et-sportive/competences-cm1.md` | 10 |
| `histoire-des-arts/competences-cm1.md` | 10 |
| `langues-vivantes/competences-cp.md` | 6 |
| **Total** | **81 lignes sans code, sur 8 referentiels** |

**Les dix autres referentiels sont codes a 100 %**, et j'ai verifie le cas qui aurait ete le plus
dangereux : un referentiel **partiellement** code ressemble a un referentiel code.
`questionner-le-monde/competences-cp.md` affiche 16 codes pour 18 puces - **les deux puces sans code
sont des renvois en prose vers les mathematiques, pas des lignes de competence**. Aucun referentiel
n'est partiellement code. C'est le seul point ou ma mesure ajoute quelque chose au constat qu'on me
transmet.

**La consequence executable, et elle est deja en place** :
`ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` (`ADR-0081`) **refuse un code qu'aucun
referentiel ne declare**. Un paquet ecrit sur l'une de ces 81 lignes ne peut pas entrer en
production. Ce n'est pas un obstacle de conception : c'est un geste de referentiel, fait par le
developpeur du lot **dans le meme commit que le paquet** - precedent exact, `lea-dev` a alloue les 14
codes d'EMC CP dans le commit `5526932` du lot `S29-4`, et les 20 codes de sciences CM1 a s30.

**L'allocation appartient a `camille-po`. Ce qui doit l'accompagner de mon cote, et rien de plus :**

1. **La forme du code est contrainte et je la donne** : `[XXX-NIVEAU-nn]`, motif
   `[A-Z]{2,4}-[A-Z0-9]{2,4}-[0-9]{2}`, le segment de niveau devant concorder avec le nom du fichier
   de referentiel **et** avec le descripteur du paquet qui le cite. Un prefixe mal choisi rend le
   compte d'une ligne silencieusement nul.
2. **Le prefixe doit etre libre, et il se verifie desormais en un coup d'oeil** - c'est un usage
   immediat du registre de `PAR-5` livre aujourd'hui. Attention en particulier a `ART`, `EPS` et
   `MUS`, **deja alloues comme prefixes de conditions** : `ART-1` a `ART-5`, `EPS-1` a `EPS-3`,
   `MUS-1` et `MUS-2`. **Ce n'est pas une collision** - un code de ligne a trois segments et une
   condition en a deux, les deux formes se distinguent, exactement comme les codes de sciences CM1
   cohabitent avec `SCI-1` a `SCI-4` depuis s30. Je le signale parce que le contraire est le premier
   reflexe.
3. **Zero ligne de code, zero `ADR`.** L'allocation ne demande rien a l'architecture.

**Ce que je ne fais pas** : je ne dis pas si ces huit matieres doivent etre ouvertes, ni dans quel
ordre, ni si les 22 lignes atteignables citees dans la commande valent le prix. **C'est de la
priorisation produit, et elle remonte a `camille-po` et `sofia-sm`.**

---

## 5. Une dette que j'ouvre sur mon propre fichier, trouvee en le mettant a jour

`ADR-index.md` portait **`ADR-0064` et `ADR-0065` sur la meme ligne physique**, un saut de ligne
manquant. Consequence : **`ADR-0065` ne rendait pas comme une ligne de la table** depuis le
2026-08-30, soit huit sprints. Corrige dans ce commit ; verifie apres coup, **86 lignes d'index pour
86 fichiers d'`ADR`, aucun doublon, aucun absent**.

**Ce n'est pas le defaut qui compte, c'est qu'aucun automate ne le voyait.**
`ToutesLesReferencesDeDecisionResolventTest` verifie que toute reference **resout** ; rien ne
verifie que tout `ADR` **figure** a l'index, ni qu'il n'y figure qu'une fois. La propriete est
encodable en une trentaine de lignes, sur le perimetre deja derive de ce garde-fou.

**Je ne l'ecris pas aujourd'hui et je l'ouvre en dette nommee**, parce qu'elle n'appartient a aucune
des trois taches de ce sprint et que je viens d'ajouter deux garde-fous : ajouter le troisieme dans
le meme commit, sans qu'il ait ete route ni chiffre, serait exactement le geste que je reproche
ailleurs. **Elle est a moi, elle est datee du 2026-09-07, et elle vaut son ADR au prochain sprint.**

---

## 6. Recapitulatif

| Objet | Verdict | Ou |
|---|---|---|
| **`PAR-5`, registre des identifiants** | **paye** - 523 identifiants, 83 prefixes, methode ecrite ; 4 tests verts | `ADR-0085`, `REGISTRE-identifiants.md` |
| **Repetition d'un item sous plusieurs noms** | **encodee**, 0 faux rouge sur 55 paquets, mordue sur l'etat refuse reel | `ADR-0086` clause 1 |
| **Contrefacon par l'amorce (`FOP-R4`)** | **encodee**, 1 rouge sur 142 amorces, cliquet a plafond exact de 1 | `ADR-0086` clause 2 |
| **La reponse dans son propre enonce** | **non encodable** - 39 rouges dont 0 des instances signalees | `ADR-0086` |
| **`CYC-1`, cycle 3 redisant cycle 2** | **non encodable** - 0 sur 280 082 couples ; l'identite est pedagogique | `ADR-0086` |
| **`OBJ-R2`** | **non encodable** - blanchit le paquet refuse, accuse 16 paquets valides ; **reste sans garde-fou**, declencheur nomme | `ADR-0086` |
| **Sciences CM1, second passage** | **faisable sans code**, 16 lignes ouvertes, deux declencheurs nommes et non realises | section 3.1 |
| **EMC CP** | **faisable sans code**, 12 lignes ouvertes ; `EMC-CP-08` demande une image ou son arbitrage, pas du code | section 3.2 |
| **`SAI-R1`** | **cause trouvee, correctif de 12 caracteres specifie, regle specifiee** ; non livre - il coute 0,25 sur une borne que je n'alloue pas | section 3.3 |
| **8 referentiels sans codes, 81 lignes** | **confirme par ma mesure** ; allocation a `camille-po`, zero code, forme et pieges donnes | section 4 |
| **`ADR-index.md` sans garde-fou de completude** | **dette ouverte a mon nom**, datee du 2026-09-07 | section 5 |
