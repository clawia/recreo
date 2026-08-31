# [Sprint s25] Planning - 2026-08-31

- **Horodatage de debut** : **2026-08-31, 18:24 (UTC+2)**.
- **Anime par** : `sofia-sm`, **casquette Scrum Master**. Ma casquette de developpeuse n'intervient
  nulle part dans ce document ; elle y est affectee comme les deux autres, section 6.
- **Arbre de reference** : worktree dedie `s25-sofia`, ouvert sur `round-two` a `130ba64`, puis
  fusion des quatre branches amont (section 2).
- **Ce que ce document decide** : le slug, la capacite, le sequencement, les affectations, l'ordre
  de fusion, les criteres de sortie, le traitement des dix dependances d'`ines-design`. **Rien
  d'autre.**
- **Ce qu'il ne decide pas** : aucun perimetre produit, aucune architecture, aucune pedagogie. Le
  desaccord de la section 5 est **route, pas tranche**.
- **Statut** : **finalise le 2026-08-31 par l'amendement de finalisation en fin de document.**
  `Q1` et `Q2` de la section 5.3 ont recu leur reponse, l'utilisatrice a tranche la bascule de la
  section 6.5, et la composition passe a **quatre paquets**. **Rien du texte ci-dessous n'est
  efface** : `DEC-0065` second amendement cite nommement mes sections 5.3 et 6.5, et une reference
  qui pointe vers rien est de la meme famille de defaut qu'un chiffre sans methode. Les enonces que
  la finalisation remplace portent un renvoi a l'endroit ou ils sont remplaces.

---

## 1. Le slug - fixe, et il ne change pas

> **`s25-le-premier-palier-visuel`.**

C'est celui que `theo-archi` a pris et qu'`ines-design` a adopte. **Je le confirme sans le
modifier**, et pour un motif de cout et un motif de fond :

- **Cout** : trois fichiers du depot le citent, tous les trois a l'interieur du repertoire lui-meme.
  Un renommage ne couterait presque rien - ce n'est donc pas l'economie qui decide.
- **Fond** : l'increment de ce sprint **est** le premier palier visuel. Les paquets de texte sont du
  contenu comme les vingt-sept precedents, et l'instruction de la dette de pli est une dette, pas
  l'increment. Le patron de s24 (`s24-trois-paquets-et-ouverture-de-la-saisie`) donnerait ici un
  slug portant un **compte de paquets** - or ce compte est precisement ce que la section 5 laisse
  ouvert. **Un slug qui porte un chiffre que le sprint peut encore deplacer est un chiffre qui
  pourrit dans un nom de repertoire.**

**`DV-10` d'`ines-design` est close par cette section.** Aucun de ses fichiers, prototypes, dessins
ou releves n'a a suivre.

---

## 2. Etat de la fusion d'ouverture

### 2.1 Lignee des quatre branches - verifiee par moi, pas supposee

`git merge-base --is-ancestor round-two <branche>` sur les quatre :

| Branche | `round-two` (`130ba64`) est-il son ancetre ? | Ce qu'elle apporte |
|---|---|---|
| `s25-camille` | **oui** | `DEC-0065` (et son amendement), `DEC-0066`, `DEC-index`, l'instruction des 16 lignes, la matrice, le backlog |
| `s25-nadia` | **oui** | les deux arbitrages prealables de s25 et l'amendement d'apres `ADR-0073`, `AVIS-PRIORITE-PEDAGOGIQUE-s13`, `FICHE-bandit-manchot-fractions-cm1` |
| `s25-theo` | **oui** | `ADR-0073` et son amendement, son garde-fou et son paquet de contre-exemple, `ADR-index`, l'avis de faisabilite |
| `s25-ines` | **oui**, et elle **contient `s25-theo`** (`5e45d83`, `6126197`) | la proposition de palier 1, la reponse des douze chiffres traces, quatre outils de mesure, quatre releves, prototypes et dessins de demonstration |

**Aucune des quatre n'est provisionnee sur la mauvaise branche.** C'est le deuxieme sprint
consecutif dont l'ouverture ne porte aucune occurrence de ce defaut, apres s24 et malgre s03, s06,
s18, s21, s22 et s23.

**Fusion faite dans l'ordre `s25-theo` -> `s25-ines` -> `s25-camille` -> `s25-nadia`**, quatre
fusions `--no-ff`, **zero conflit**. `s25-ines` reposant sur `s25-theo`, la fusionner apres lui
evite de rejouer le meme diff Java sur deux tetes.

### 2.2 Porte technique, mesuree par moi sur l'arbre fusionne

```
bash outils/mvn-jdk21.sh -B clean verify
```

**BUILD SUCCESS**, cinq modules, **937 tests, 0 echec, 0 erreur, 0 ignore.**

**Methode du comptage** : somme des lignes de resultat **de module** (`surefire` et `failsafe`),
jamais des lignes par classe. **Perimetre : le reacteur complet a la racine, donc aucun `-pl` et
aucune question de `-am`.** Le detail, pour que le total se refasse sans me croire :

| Module | Tests |
|---|---|
| `recreo-domain` | 78 |
| `recreo-application` | 13 |
| `recreo-adapter-web` | 349 |
| `recreo-adapter-content` | 355 |
| `recreo-bootstrap` (surefire) | 136 |
| `recreo-bootstrap` (failsafe) | 6 |
| **Total** | **937** |

**L'ecart avec les 926 de `130ba64` est de 11, et je dis d'ou il vient plutot que de le taire** :
`LIllustrationDeContenuResoutEtAucuneNeDortTest` rend **11 tests** sur cet arbre, et rien d'autre
n'a ete touche cote Java par les quatre branches. `theo-archi` a annonce 936 puis 937 sur son propre
arbre ; **son second chiffre correspond au mien apres fusion, et c'est la premiere fois qu'un compte
annonce correspond au total mesure apres fusion.** Le motif est structurel et vaut d'etre note : un
seul agent a livre du Java ce sprint, donc il n'y a rien a additionner. **Cela ne fait pas de
l'annonce une source** - le total ci-dessus est le mien, sur l'arbre ci-dessus.

**Garde-fou de chemins**, sur l'index complet et avec l'exclusion que la CI applique elle-meme :

```
git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh
```

*"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*

*Sans cette exclusion, le script rend 10 occurrences, toutes dans son propre fichier de test, ou
elles sont des cas de test volontaires. Je l'ecris parce qu'un lecteur qui lance la commande courte
verrait un rouge qui n'en est pas un.*

### 2.3 Etat de la matrice a l'ouverture, verifie par moi

**Methode** : comptage de la colonne `Statut` des **quatre tableaux de matiere** de
`squad/programme/MATRICE-couverture.md`, sur l'arbre fusionne. **Aucun module compile, donc aucune
question de `-am`.**

| Statut | Compte |
|---|---|
| `couvert-valide` | **27** |
| `non couvert` | 13 |
| `en cours` | 1 |
| `non couvrable en l'etat` | **6** |
| **Total des lignes du referentiel** | **47** |

> **Denominateur 47 - 6 = 41. Couverture a l'ouverture : 27 / 41 = 65,9 %.**

**Le chiffre d'entree qui m'a ete transmis - "matrice 27/43" - decrit `130ba64`, pas l'arbre
fusionne.** `DEC-0066` retire deux lignes du denominateur : **le numerateur n'a pas bouge d'une
unite.** Les deux taux se lisent cote a cote et **ne s'additionnent pas a un progres** : c'est ce que
`DEC-0066` ecrit, et je ne le reformule pas.

---

## 3. Les faits recus, avec leur provenance - `DEC-0062`

`DEC-0062` exige que tout enonce ecrit dans un fichier commite porte sa provenance sous l'une de
trois formes, et sa regle `3.5` dit que **le premier agent qui l'ecrit fige la citation**.

### 3.1 Deja figes par un autre agent - je cite le fichier, ma citation est de forme 1

| Enonce | Fige par | Ou |
|---|---|---|
| *"Ouvrir le visuel, palier 1"* | `camille-po`, 2026-08-31 | `DEC-0065`, amendement, section A.1 |
| *"Reclasser, taux revu"* | `camille-po`, 2026-08-31 | `DEC-0065`, section A.1 ; `DEC-0066`, en-tete |

**Je ne recite pas ma propre reception de ces deux enonces**, conformement a `3.5`. Ils ont un
proprietaire de citation dans le depot, et c'est lui qui fait foi.

### 3.2 Non presents dans le depot - je les fige ici, et c'est la premiere fois

Deux arbitrages de l'utilisatrice me parviennent aujourd'hui et **aucune occurrence n'existe dans
l'arbre suivi** - verifie par `git grep` sur l'index complet avant d'ecrire cette section.

> **De l'utilisatrice, 2026-08-31**, cite mot pour mot et fige a la reception :
> **"Il reste hors de la porte"**

> **De l'utilisatrice, 2026-08-31**, cite mot pour mot et fige a la reception :
> **"Instruire dans s25"**

**Tout agent ulterieur cite cette section-ci** (forme 1), et non sa propre reception.

### 3.3 Recus de MASTER, sans source dans le depot

Deux enonces me sont parvenus par le fil d'animation sans etre attribues a l'utilisatrice et sans
source dans l'arbre. Ils sont cites mot pour mot, dates du 2026-08-31, et **ils ne fondent rien** :
`DEC-0062` section 3.2 interdit qu'un enonce de cette forme serve de motif a une decision, a un
critere d'acceptation ou a un verdict.

- *"Les arbitrages 3 et 4 sont en tension et je te le dis plutot que de te laisser le decouvrir"* -
  **recu de MASTER, sans source dans le depot.** Traite en section 6.5 comme une contrainte de
  composition que je verifie moi-meme, **pas** comme le motif d'une decision.
- *"yanis-qa et relecteur-pedagogique ont ete lances apres la review au lieu d'avant"* - **recu de
  MASTER, sans source dans le depot.** Traite en section 8 comme un fait de conduite de ceremonie a
  ne pas repeter.

**Un troisieme enonce m'est arrive par le meme canal et je ne l'ecris pas sous cette forme, parce
qu'il est dans le depot** : *"c'est le moment le plus cher pour reporter le cliquet et la porte de
la dette n. 4"* est de `camille-po`, `DEC-0065` amendement section A.7. Je le cite donc de forme 1
en section 6.6. *Je note le geste plutot que de le taire : c'est le reflexe que `DEC-0062` section
3.4 demande - avant d'ecrire un fait recu, aller voir s'il est dans le depot.*

---

## 4. Objectif du sprint (gele)

> **Ouvrir le palier 1 du visuel jusqu'a un jeu illustre jouable et auditable de bout en bout,
> livrer les paquets de texte que la capacite pedagogique autorise a cote, et instruire la dette de
> pli sur le catalogue deja livre.**

**Ce que "palier 1" veut dire, et ce n'est pas de moi** : une **image fixe** et des **propositions en
texte** rendues par la mecanique de choix qui existe deja (`nadia-prof`, arbitrage prealable de s25
sur le palier 1, section 2). Ni proposition positionnee sur l'image, ni manipulation d'une
collection : `V1` a `V4` de son document disent ce que le palier 1 n'ouvre pas, et un lot qui les
franchirait serait **refuse a la porte**, quelle que soit la qualite de l'image.

**Ce que le palier 1 n'est pas, et c'est de `theo-archi`** : *"le palier 1 n'est **pas** une
troisieme forme d'item, et **pas** une troisieme mecanique"* (`ADR-0073`, decision 6, et avis de
faisabilite section 1.3). **La facon dont l'enfant repond ne change pas.** Ce fait gouverne tout ce
planning : il explique pourquoi `SEUIL-1` a `SEUIL-5` ne sont pas a rejouer, pourquoi l'audit de
mecanique de `nadia-prof` tombe, et pourquoi son chiffre remonte de trois a quatre paquets.

**La ligne gagnee par ce relevement est portee au credit de `theo-archi`**, comme `nadia-prof` le
demande explicitement : *"La ligne de plus n'est pas de moi, elle est de `theo-archi` : elle vient
de sa mesure, pas de ma capacite."*

### 4.1 Le pli - ce que l'arbitrage change et ce qu'il ne change pas

`ines-design` a mesure : **53 px** de hauteur d'image apres un echec a la largeur de reference, et
**zero** sous 720 px de large - l'ecran passe deja sous le pli **avec une image de hauteur nulle**.
Les pieces demandent **43 px** et passent ; le cadran a douze nombres demande **123 px** et ne passe
pas.

L'arbitrage de la section 3.2 - **"Il reste hors de la porte"** - **ne cree aucune decision
neuve** : `DEC-0051` porte le pli, `DEC-0053` l'a sorti de la porte pedagogique a s22. Il
**reconfirme** ces deux decisions, qui sont sur le disque.

**Ce qu'il change, et c'est un fait de planning** : le budget de 53 px **ne met aucun veto sur le
cadran**. `HOR` et `MON` restent techniquement au palier 1, comme `theo-archi` l'a etabli, et
`DV-7` d'`ines-design` - la seule dependance qu'elle m'ait routee pour remontee - **est close par
`DEC-0053` reconfirme**, pas par une decision de moi.

**Ce qu'il ne change pas** : le nombre de paquets visuels que `nadia-prof` accepte d'auditer - deux
au plus, section 6.1 -, ni le perimetre, qui reste a `camille-po`. **Le pli ne veto plus ; il ne
compose pas pour autant.**

---

## 5. Le desaccord sur la monnaie - route, non tranche, et il decide seul l'ampleur du sprint

### 5.1 Les deux positions, dans les mots de leurs auteures

**`camille-po` refuse** (`docs/backlog/backlog.md`, `S25-MON-1`, close) :

> *"Sur les huit items, sept sont un jeu deja valide avec un symbole d'euro colle au resultat. Un
> enfant qui joue ce paquet ne travaille pas la monnaie ; il refait du calcul."*

et elle corrige son propre critere dans le meme geste :

> *"Le critere n'est pas "un paquet est-il ecrivable", c'est "un enfant apprend-il quelque chose
> que le catalogue ne lui apprend pas deja"."*

**`nadia-prof` compte la monnaie** (arbitrage prealable de s25 sur le palier 1, sections 2 et 9.4) :
`MON` est **ACCEPTE** sous `MON-1` a `MON-5` plus `CON-1` a `CON-4`, et sa composition a quatre
paquets est *"`FIG` et `MON` en visuel, `SEM` et l'un de `SUB` ou `LIS` en texte"*.

### 5.2 Ces deux positions ne portent pas sur le meme objet, et je le verifie avant de router

**Le refus de `camille-po` porte sur un paquet de contenu textuel** - la moitie *"utiliser"* servie
en texte. **L'acceptation de `nadia-prof` porte sur un paquet illustre.** Le verdict `REFUSE` de
`nadia-prof` du matin porte lui aussi sur le seul contenu textuel : *"`MON-1` a `MON-5` disent ce qui
la debloquerait"*. **Les deux sont d'accord sur le contenu textuel** ; le desaccord ne nait qu'a
l'arrivee de l'image.

**Ce qui reste alors reellement en desaccord, et c'est un chiffre** :

| Document | Ce qu'il dit du cout de `MON` |
|---|---|
| `nadia-prof`, arbitrage des 16 lignes, section 3.1 | *"deux d'entre elles - monnaie et `DEN` - demandent le visuel **et** un paquet textuel, donc deux paquets chacune"* |
| `nadia-prof`, arbitrage du palier 1, section 2, **plus tard le meme jour** | `MON` **ACCEPTE**, `couvert-valide`, **ligne entiere** - *"le paquet sert reconnaitre par l'image et utiliser par la question"*, **un seul paquet** |
| `camille-po`, `DEC-0065` A.3 et backlog | *"une image. **Deux paquets, pas un**"* - elle cite le premier document |

**`camille-po` a relaye le chiffre du premier document ; le second, du meme jour et de la meme
autrice, dit un paquet.** Je ne dis pas lequel est juste - **je n'ai voix ni sur l'un ni sur
l'autre** - mais je refuse de laisser deux documents du depot porter deux couts differents pour le
meme paquet sans que personne le nomme. C'est la famille de defaut que `DEC-0062` amendement B a
sanctionnee il y a un jour.

### 5.3 Ce que je route, a qui, et sous quelle forme

| Question | A qui | Artefact attendu | Etat |
|---|---|---|---|
| **Q1** - `MON-1` a `MON-5` servis par **un** paquet illustre font-ils passer la ligne entiere, ou faut-il un second paquet textuel ? Les deux documents du 2026-08-31 se contredisent | **`nadia-prof`** - autorite pedagogique, et les deux enonces sont d'elle | **Une** reponse, dans l'un de ses documents, disant laquelle des deux phrases gouverne | **OUVERT** |
| **Q2** - le quatrieme paquet du sprint est-il `MON` illustre ? | **`camille-po`** - le perimetre est a elle, et son refus vise le contenu textuel, pas l'image | **Amendement de `DEC-0065`** : reouverture de `S25-MON-1` sur la forme illustree, ou maintien clos avec le motif d'apres `Q1` | **OUVERT**, **depend de `Q1`** |

**Ce que je ne fais pas** : je n'ouvre pas `MON` au perimetre, je ne le ferme pas, et je n'affecte
personne dessus. **Aucun developpeur ne commence un dessin de piece avant que l'amendement de
`DEC-0065` existe sur le disque.** Un obstacle ne se clot pas sur une intention annoncee.

> **`Q1` et `Q2` sont closes** - `nadia-prof` et `camille-po` ont rendu, et **l'amendement de
> `DEC-0065` existe sur le disque**. Voir l'amendement de finalisation, section F.1. La condition que
> je posais ci-dessus est remplie.

### 5.4 Ce que ce desaccord vaut, en lignes, verifie par moi

**Le piege de comptage m'a ete signale ; je l'ai verifie plutot que de le supposer.** Sur les onze
lignes du tableau *Mathematiques - CP*, `SEM` et `HOR` visent **la meme et unique ligne** :

> *"Se reperer dans la journee, la semaine ; lire l'heure pleine sur une horloge"* - **une seule
> ligne du referentiel, statut `non couvert`.**

*Methode : lecture des onze lignes du tableau, une par une, sur l'arbre fusionne. Ce ne sont pas deux
lignes que le point-virgule separerait ; c'est une ligne que le point-virgule coupe en deux moities,
et `nadia-prof` a ecrit qu'une seule des deux suffit a la faire passer.*

**Consequence, et elle est decisive** : sans `MON`, **quatre paquets ne donnent que trois lignes**,
parce qu'un quatrieme paquet ne pourrait viser que la moitie deja prise de la ligne de l'heure. Les
lignes atteignables a s25 sont exactement cinq - figures, heure, monnaie, `SUB`, `LIS` - et `SUB` et
`LIS` sont exclusives l'une de l'autre par le quota `COMP-2` de `nadia-prof`.

| Composition | Paquets | Lignes | Taux |
|---|---|---|---|
| **Perimetre engage par ce planning** - `SUB` + `SEM` + `FIG` | 3, dont 1 visuel | **+3** | **30 / 41 = 73,2 %** |
| Si `Q1` et `Q2` ouvrent `MON` illustre - plus `MON` | 4, dont 2 visuels | **+4** | **31 / 41 = 75,6 %** |
| `FIG` + `HOR` + `SUB` + `DEN`, sans `MON` | 4, dont 2 visuels | **+3** | 30 / 41 = 73,2 % |

**Aucun de ces trois chiffres n'est une prevision.** Ce sont des plafonds, et ils supposent qu'un
paquet ecrit dans le sprint passe la porte pedagogique du premier coup - *"ce qui n'est jamais arrive
sur ce produit"* (`camille-po`, `DEC-0065` A.4).

> **Le plafond que je retiens pour le perimetre engage est +3 lignes, soit 30 / 41 = 73,2 %.**
> **La quatrieme ligne n'appartient a personne dans cette squad tant que `Q1` et `Q2` n'ont pas
> rendu.**

*Elles ont rendu. **Le plafond retenu passe a +4 lignes, soit 31 / 41 = 75,6 %**, avec la meme
methode et le meme denominateur - amendement de finalisation, section F.2. Ce paragraphe reste
lisible a sa place : c'est lui que `DEC-0065` B.4 cite.*

---

## 6. Capacite, lots et affectations

### 6.1 La capacite pedagogique gouverne le nombre de paquets, et elle n'est pas de moi

`nadia-prof`, section 9.3 de son arbitrage du palier 1 :

> *"Ma capacite pour s25 : quatre paquets, dont au plus deux paquets visuels, plus l'audit d'ecran
> de la section 9.2."*

**Le perimetre engage en consomme trois, dont un visuel. Je suis sous sa borne et je n'y touche
pas.** Le quatrieme paquet est celui de la section 5 ; s'il s'ouvre, sa borne est atteinte
exactement, pas depassee.

*La composition finale consomme les **quatre** paquets de sa borne, dont les deux visuels qu'elle
autorise - amendement de finalisation, section F.2. Sa borne est atteinte exactement, jamais
depassee.*

**Ce qu'elle refuse de faire pour tenir un chiffre, et que je traite comme une contrainte de
sequencement et non comme une reserve** :

> *"Si un lot arrive trop tard ou trop gros, je decline par ecrit plutot que de rendre un verdict que
> je n'ai pas les moyens d'avoir rendu."*

**Le risque que `camille-po` nomme est celui-la exactement** : la saisie, ouverte a s24 dans les
memes termes, avait derriere elle `SAI-1` a `SAI-12`, `SEUIL-1` a `SEUIL-5`, `ADR-0058` et
`ADR-0059`, **tous rendus avant le sprint** ; le visuel a `ADR-0073`, rendu la veille de l'ouverture,
et rien d'autre. **Ma reponse est dans l'ordre de fusion (section 7), dans la demande d'audit
anticipee (section 7.1) et dans le declencheur de bascule (section 6.4).**

### 6.2 Les huit items de travail

Trois developpeurs : `marc-dev`, `lea-dev`, `sofia-sm` (casquette dev). **Huit items** - un de moins
que les neuf que s24 a portes.

> **Ce tableau est remplace par celui de la section F.3** : le lot G sort, la chaine `MON` entre, et
> les affectations sont refaites. **Neuf items, trois par tete.** Les criteres de chaque lot survivant
> sont inchanges et se lisent ici.

| Ordre | Item | Nature | Competence visee | Criteres | Affecte a |
|---|---|---|---|---|---|
| 1 | **Lot A** - le champ de contenu, du domaine jusqu'a l'ecran | technique | transverse | `ADR-0073` clauses 1 a 6 ; les **trois obligations mesurees** de l'avis de faisabilite section 4 ; la borne de l'exemption de `RessourcesTiercesTest` **mordue dans les deux sens** | `marc-dev` |
| 2 | **Lot F** - les deux dettes d'outillage de s24 | technique, outillage | aucune ligne | l'enveloppe **refuse** au lieu d'imprimer ; `verifier-jdk.sh` repond sur le fichier dont il parle ; un harnais et une etape de CI ; le tri des 40 fichiers **sur le patron "amender, jamais reecrire"** | `sofia-sm` (dev) |
| 3 | **Lot B** - les dessins de `FIG` et le cliquet de poids | technique et contenu visuel | mathematiques CP, espace et geometrie | `FIG-1` a `FIG-4` ; `ADR-0073` clause 4 - ni `<text>` ni `<tspan>` ; `DV-1` ; **le cliquet d'`ADR-0036` clause 5, pose sur la valeur que le lot installe** | `lea-dev` |
| 4 | **Lot C** - le paquet `FIG` et son entree de catalogue (`S25-FIG-1`) | contenu | mathematiques CP, espace et geometrie, **ligne entiere** | `FIG-1` a `FIG-4`, `CON-1` a `CON-5` ; `ADR-0051` ; `TYP-1` a zero ; `enonce` en premiere cle sur **chaque** item | `lea-dev` |
| 5 | **Lot E** - `S25-SUB-1`, substituts d'un personnage | contenu | francais CM1, lecture et comprehension de l'ecrit | criteres du backlog `S25-SUB-1` ; **paquet du type `COMP-2` du sprint, le seul autorise** | `marc-dev` |
| 6 | **Lot D** - `S25-SEM-1`, se reperer dans la journee, la semaine | contenu | mathematiques CP, grandeurs et mesures, **couverture partielle avec mention** | `SEM-1` a `SEM-9` ; la mention exacte exigee par `nadia-prof`, reprise mot pour mot | `sofia-sm` (dev) |
| 7 | **Lot G** - instruction de la dette de pli sur le catalogue livre | technique, document | aucune ligne | le compte item par item sur **tout** le catalogue enumere ; la distribution des longueurs de proposition ; **aucune borne proposee** (section 9, `DV-4`) | `sofia-sm` (dev) |
| 8 | **Lot H** - `S25-FRA-1`, item 18 de `cm1-fractions` | contenu, correction | aucune ligne, **Done non retire** | un item, un verbe, borne ecrite par `nadia-prof` | `lea-dev` |

### 6.3 Le motif de cette repartition

**`marc-dev` porte le lot A seul et en premier, sans rien d'autre sur sa table.** Le lot A est le
**seul blocage de l'increment entier** : les lots B et C en dependent, et l'audit d'ecran de
`nadia-prof` aussi. Contrairement a s24, **je ne place aucun paquet de contenu en concurrence avec lui
sur la meme tete** : a s24 ce placement rendait operationnel l'ordre de priorite de `camille-po` -
*"le contenu prime"* - en faisant glisser le lot technique ; **ici, faire glisser le lot technique
fait tomber le paquet visuel**, c'est-a-dire le seul element de l'ordre de sacrifice de `nadia-prof`
qui ne tombe jamais. Le patron de s24 etait bon ; il ne s'applique pas a un lot qui bloque son propre
paquet.

*Correction de ma main, 2026-08-31 : cette phrase disait d'abord "je ne mets **aucun paquet de
contenu derriere lui** sur la meme tete", et le paragraphe suivant placait `S25-SUB-1` derriere le
lot A sur cette meme tete. **Les deux ne pouvaient pas etre vrais en meme temps.** Ce que je decidais
est ce qui est ecrit maintenant - aucun paquet **en concurrence** avec le lot A, la file d'une tete
n'etant pas une concurrence. C'est une contradiction interne dans un document que tous les
developpeurs lisent, du meme genre que celle que j'ai relevee chez une autre en section 5.2, et je la
corrige au meme titre.*

`S25-SUB-1` arrive sur sa tete **apres** le lot A. C'est la ligne garantie du sprint et le seul
paquet `COMP-2` autorise ; **si `marc-dev` est serre apres le lot A, c'est `S25-SUB-1` qui reste, et
rien ne s'intercale devant.**

**`lea-dev` porte la chaine visuelle de contenu, lots B puis C, sous une seule tete.** Motif ecrit :
`FIG-1` exige que *"les proportions de la figure soient declarees par le contenu"* - **la main qui
trace le dessin est celle qui declare ses proportions dans l'item.** Les separer ferait porter la
declaration a une tete differente de celle qui dessine. Le lot H, une correction d'un item et d'un
verbe, ferme sa file sans peser.

**`sofia-sm`, casquette dev, porte les deux lots d'outillage et de mesure, plus un paquet de texte.**
Motif : les deux dettes d'outillage sont a moi depuis s24 et je les ai deja laissees glisser une
fois ; le lot G est une mesure dont l'outil existe deja
(`squad/outils/nadia-prof/compter-le-pli-du-catalogue-entier.py`) et dont le resultat alimente une
decision que je ne prends pas. **Le lot F passe avant le lot G, et ce n'est pas un ordre de
confort** : une enveloppe de mesure qui imprime au lieu de refuser est ce qui a induit en erreur une
lectrice competente le 2026-08-30. **On repare l'instrument avant de mesurer avec.**

### 6.4 Declencheur de bascule - objectif, observable, sans calendrier

Un sprint n'a pas de timebox sur ce produit. Le declencheur ne peut donc pas etre une date.

> **Si `S25-SUB-1` et `S25-SEM-1` sont tous deux fusionnes verts alors que le lot A ne l'est pas
> encore, je bascule** : `marc-dev` et `lea-dev` passent tous les deux sur le lot A, et les lots G et
> H sortent du perimetre engage, avec leur motif ecrit dans `review.md`.

*Ce declencheur cesse d'etre observable avec les affectations finales - `S25-SUB-1` y est derriere le
lot A. **Il est remplace par les deux declencheurs de la section F.4**, qui portent le meme objet :
proteger ce qui ne tombe jamais.*

**Motif** : le seul point de l'ordre de sacrifice de `nadia-prof` qui ne tombe jamais est **le
premier paquet visuel** - *"une mecanique livree sans aucun paquet est une mecanique que je ne peux
pas auditer"*. Deux paquets de texte fusionnes devant un lot A rouge est le signe objectif que le
sprint livre ce qui tombe en premier et laisse tomber ce qui ne tombe jamais.

### 6.5 Ce que je sacrifie, et pourquoi - la tension des deux arbitrages

L'arbitrage **"Il reste hors de la porte"** maximise les lignes ; l'arbitrage **"Instruire dans
s25"** consomme de la capacite. **C'est un arbitrage, pas une incoherence**, et je compose en le
disant.

**La tension est plus petite qu'elle n'en a l'air, et je le verifie au lieu de le dramatiser.**
L'arbitrage 4 demande deux choses, et une seule est du travail de developpeur :

1. **Combien de jeux du catalogue ont des items sous le pli** - c'est un **comptage**, l'outil existe
   deja sur l'arbre et il a ete ecrit precisement pour ne pas echantillonner. C'est le lot G, et il
   tient sur une tete.
2. **Quelle borne il faudrait sur la longueur des propositions** - **ce n'est pas du travail de
   developpeur.** C'est `DV-4` d'`ines-design`, deja routee a `nadia-prof` et `camille-po`, et c'est
   la meme famille que `D4` a s24. **Le lot G rend la distribution ; il ne propose aucune borne** -
   une borne sur du texte lu par un enfant n'est pas a moi.

**Ce que je sacrifie, nommement** :

| Sacrifie | Motif, ecrit par moi |
|---|---|
| **`S25-SCO-1`** - le score parfait par elimination. **Second report** | Les deux motifs du premier report sont tombes le 2026-08-31 et je n'en use pas. Le mien : **ses deux prerequis ne sont pas sur le disque a l'ouverture** - le resultat attendu de `nadia-prof` et l'`ADR` de `theo-archi` - et les trois tetes sont sur la colonne visuelle. **C'est un motif de capacite et de dependance, et `camille-po` a ecrit que ce motif-la m'appartient.** J'en reporte le prix, qui monte mecaniquement : s25 ajoutera un a trois jeux de plus a la partition `R-B` |
| **`S25-DEN-1`** - zero ligne | Il consommerait un paquet de la borne de `nadia-prof` sans deplacer aucune ligne, et `DEN-2` interdit qu'il fasse passer la sienne au vert. **Un paquet qui coute une unite de capacite d'audit et ne rend rien sort en premier**, avant meme l'ordre de sacrifice |
| **`S25-LIS-1`** - **second report, motif neuf et mesure** | `LIS-6` la rend exclusive de `S25-SUB-1`, qui est au rang 1. **Et sa condition d'ouverture est desormais rendue, negativement** : `nadia-prof` avait ecrit *"sans cette mesure, je ne fais pas ouvrir cette ligne"* ; `ines-design` l'a rendue, et le verdict est **NON** - *"a la largeur de reference, apres un echec, la reponse est non meme avec un texte de zero mot. Ce ne sont pas les mots qui debordent, ce sont les propositions"*. La borne qui la debloquerait est `DV-4`, non rendue |
| **Les trois lots de dette de `theo-archi`** - gestionnaire inline, cliquet typographique, trois noms d'un concept | Non engages. **La decision de les ouvrir est a `camille-po`**, `theo-archi` l'ecrit trois fois. Ce que je fais a la place est en section 6.6, et **ce n'est pas un substitut au lot** |

**Ce que je ne sacrifie pas et qui aurait pu l'etre** : rien du perimetre de contenu. Les trois
paquets engages sont exactement les trois premiers rangs de `DEC-0065` A.3.

**Et si `Q1` et `Q2` ouvrent le quatrieme paquet** : `lea-dev` est deja sur la chaine visuelle, donc
les dessins et le paquet de `MON` iraient sur `sofia-sm`, et **le lot G sortirait du perimetre
engage**. Je l'ecris maintenant pour que l'arbitrage se fasse en le sachant : **le quatrieme paquet
et l'instruction de la dette de pli ne tiennent pas ensemble sur trois tetes.** Le choix entre les
deux appartient a `camille-po` pour le perimetre, pas a moi.

*C'est ce paragraphe que `DEC-0065` B.4 cite, et l'arbitrage est tombe : **le lot G sort, la
quatrieme ligne reste**. La phrase reste vraie et le sacrifice est acte en section F.5. **Mais
l'affectation que j'annoncais - la chaine `MON` sur ma propre tete - n'est pas celle que je retiens**,
et la section F.3 dit pourquoi : ce que j'avais annonce protegeait mal la ligne que l'utilisatrice a
choisi de garder.*

### 6.6 Les deux dettes que je n'achete pas, mais dont j'arrete la croissance

`camille-po` a verse un fait **contre son propre perimetre**, et je ne le laisse pas tomber avec
l'ancien :

> *"les trois postes touchent tous des gabarits ou du comportement, et s25 en cree. Les reporter dans
> le sprint qui ouvre une mecanique de rendu est le moment le plus cher pour le faire."*
> (`camille-po`, `DEC-0065`, amendement, section A.7)

`theo-archi` le dit dans les memes termes : *"un cliquet pose sur zero est exact ; pose apres le
premier, il ne l'est plus."*

**Acheter le lot n'est pas a moi. Tenir le zero l'est** - c'est du sequencement, et cela ne coute
aucune capacite :

- **Zero attribut d'evenement inline dans un gabarit servi.** Verifie par moi **a chaque fusion**,
  avec sa commande : `git grep -nE '\s(on[a-z]+|hx-on:)=' -- '*.html'`. **Mesure a l'ouverture sur
  l'arbre fusionne : zero occurrence.** Un lot qui en introduirait un ne serait pas fusionne.
  **Cela ne repare pas le budget d'`ADR-0008`**, qui derive toujours sa population des fichiers `.js`
  servis et laisse 926 tests verts sur un `onclick` : **c'est un garde-fou humain, il est nomme comme
  tel, et il expire a la cloture de s25.**
- **Zero occurrence typographique neuve.** Le cliquet de `TYP-1` derive son perimetre du texte servi
  par les **paquets** et ne voit ni les gabarits ni les constantes de message ; s24 y a ajoute 3
  occurrences sous une porte verte, sur 11 au total. **Le `relecteur-pedagogique` verifie le diff de
  s25, et lui seul** - pas les 11 existantes, dont le traitement demande le choix de caractere de
  `nadia-prof`, que personne ne lui a encore demande.

**Ces deux verifications sont des criteres de sortie (section 10), pas des lots.** Elles ne
remplacent ni le correctif de perimetre de `theo-archi`, ni le choix de caractere de `nadia-prof`, et
elles ne dispensent `camille-po` d'aucun arbitrage de rang.

---

## 7. Ordre de fusion

**A chaque fusion, je verifie la lignee du worktree avant de fusionner**, meme si l'agent dit
l'avoir fait : le provisionnement sur la mauvaise branche s'est produit a chaque sprint depuis s03,
et c'est moi qui reponds de ce que je fusionne.

1. **Lot F**, a tout moment et **le plus tot possible**. Il ne touche aucun module de production ; il
   repare l'instrument avec lequel tout le reste sera mesure.
2. **Lot A**, seul, vert, **avant tout lot visuel**. C'est le premier verrou du sprint.
3. **Lots B et C en une seule fusion, jamais separement.** **Motif technique, et il est dur** : le
   garde-fou bidirectionnel d'`ADR-0073` **refuse une illustration deposee sans paquet qui la
   declare** - `ines-design` l'a constate en placant ses deux dessins de demonstration hors du
   repertoire d'illustrations, pour cette raison exacte. **Un lot B fusionne seul rendrait la porte
   technique rouge.** C'est la forme exacte de l'indissociabilite du lot 5b et de `S24-SAI-2` a s24,
   et elle avait bien marche.
4. **Lots E et D**, chacun des qu'il est vert, dans l'ordre ou ils le deviennent. Les conflits sur
   `catalogue.yaml` entre eux sont des ajouts en fin de liste ; **je les resous a la fusion, jamais
   l'auteur dans sa branche.**
5. **Lot G**, quand il est rendu. Aucun module, aucun conflit possible.
6. **Lot H**, a tout moment - il touche un paquet existant, pas le catalogue.

> **Cet ordre est complete, non remplace, par la section F.6** : une seconde paire indissociable
> (`MON`) s'ajoute, quatre lots touchent desormais `catalogue.yaml` au lieu de trois, et la premiere
> paire fusionnee pose le cliquet de poids.

### 7.1 L'audit de `nadia-prof` ne se demande pas a la review

**C'est ma reponse au risque de la section 6.1, et c'est un ecart delibere avec la conduite
habituelle.**

> **Des que la fusion 3 - lots B et C - est verte, je demande a `nadia-prof` son audit d'ecran** :
> celui qu'elle a defini elle-meme en section 9.2 - *"le dessin est-il la quand l'enfant en a
> besoin"*, *"ce que l'enfant voit si un identifiant ne resout rien"*, *"le dessin et l'indice
> tiennent-ils ensemble au-dessus du pli"*. Elle le price **en dessous d'un paquet**.
> **Je n'attends pas que les lots D, E, G et H soient fusionnes.**

Motif : elle *"decline par ecrit un lot qui arrive trop tard plutot que de l'auditer vite"*. Un audit
d'ecran demande a la review arriverait apres tous les autres lots, sur le seul objet du sprint
qu'aucune fiche anterieure ne couvre. **Le seul levier que j'aie sur ce risque est le moment ou je le
lui demande.**

> **Cette parade tient telle quelle avec deux paquets visuels, et elle ne suffit plus a elle seule.**
> Section F.7 : l'audit d'ecran est *"une fois, pas par paquet"*, donc il reste demande a la premiere
> paire visuelle verte ; **mais l'audit de contenu du second paquet visuel, lui, est par paquet**, et
> c'est le sequencement de la section F.3 qui le protege, pas cette demande-ci.

---

## 8. `yanis-qa` et `relecteur-pedagogique` - avant la review, pas apres

**Fait recu de MASTER, sans source dans le depot** (section 3.3) : *"yanis-qa et
relecteur-pedagogique ont ete lances apres la review au lieu d'avant"*. **Il ne fonde rien**, mais
`squad/README.md` place bien leurs rapports **dans** `review.md` : un rapport qualite qui arrive
apres la review ne peut pas y figurer. Je les sequence donc explicitement, et **je dirai quand les
lancer** plutot que de les supposer lances.

| Qui | Quand | Sur quoi, nommement |
|---|---|---|
| **`relecteur-pedagogique`** | **apres la derniere fusion de contenu (lots C, D, E, H), avant la review** | Les paquets neufs ; les textes alternatifs des illustrations sous `CON-5` ; les fichiers de ceremonie ; **le diff typographique de s25** (section 6.6) ; **et `S25-SUB-1`, dont `nadia-prof` price le volume de texte a deux fois et demie celui d'un paquet ordinaire - un cout qui est le sien depuis que `DEC-0056` a sorti l'accentuation de la porte de `nadia-prof`, et que je price ici chez celui qui le porte** |
| **`yanis-qa`** | **apres la derniere fusion technique, avant la review** | La porte technique sur l'arbre fusionne ; **la borne de l'exemption de `RessourcesTiercesTest`, mordue dans les deux sens** - `theo-archi` la nomme *"le point le plus dangereux du sprint"* ; la bidirectionnalite du garde-fou d'`ADR-0073` ; le cliquet de poids d'`ADR-0036` clause 5 ; sa campagne de mutation sur le materiau reel, dans la forme de s24 |

**Aucun des deux ne prononce de Done**, ni technique ni pedagogique. Le premier est juge par la CI,
le second par `nadia-prof` seule.

> **Le point de declenchement exact de chacun est nomme en section F.8**, comme il m'a ete demande de
> le faire plutot que de le laisser deduire.

---

## 9. Les dix dependances ouvertes d'`ines-design`

Traitees une par une : **affectee**, **close par un fichier qui existe**, ou **portee avec son
proprietaire nomme**. Aucune n'est laissee sans traitement.

| # | Objet | Traitement au planning |
|---|---|---|
| **`DV-1`** | La taille intrinseque du dessin servi doit etre **au moins egale au plafond retenu** | **Affectee, sans routage** : c'est une propriete du fichier `.svg`, donc le **lot B**, donc `lea-dev`. `FIG-1` l'exige deja cote contenu - *"ses proportions sont declarees par le contenu"*. `ines-design` demandait une affectation, pas un arbitrage |
| **`DV-2`** | Comment un item porte une donnee de dessin | **CLOSE** - `ADR-0073` est sur le disque et sa decision 1 la tranche : *"un item declare une illustration par un identifiant, pas par un chemin, pas par des octets, pas par une adresse"*. `ines-design` a ecrit sa proposition **sans presumer aucune cle** ; seuls les deux `th:` de son gabarit changent, et aucune de ses regles de feuille n'en depend. **Le lot A porte l'ecart** |
| **`DV-3`** | Le contraste du trait du dessin servi | **Portee, non bloquante a s25, et je nomme la consequence** : `FIG-3` interdit que la **couleur distingue deux formes**, elle ne dit rien du contraste du trait contre la surface. **Routee a `nadia-prof`** pour une condition d'ecriture de la famille `FIG-3`, et a `theo-archi` et `yanis-qa` pour la faisabilite d'un garde-fou lisant les `.svg` servis. **Tant qu'aucune des deux n'a rendu, `nadia-prof` en est seule garde a l'audit du lot C** |
| **`DV-4`** | La longueur maximale d'une proposition, sur `LIS` et au-dela | **Routee a `nadia-prof` et `camille-po`. Meme famille que `D4` a s24, et je ne reconduis pas le traitement de s24** : `D4` avait ete portee non bloquante parce que `CPL-2` bornait la reponse sur le paquet retenu ; **ici elle mord deja, et sur un jeu vert** - c'est elle qui ferme `S25-LIS-1` (section 6.5). **Le lot G rend la distribution mesuree pour qu'elles decident sur un chiffre et non sur une impression ; il ne propose aucune borne** |
| **`DV-5`** | Une explication de revelation peut-elle designer une partie du dessin ? | **Portee, non bloquante a s25, motif ecrit par son autrice** : `nadia-prof` a leve d'elle-meme la contrainte - *"aucune de mes conditions de palier 1 n'exige d'explication"*, ses trois familles demandent un **indice**. Elle mordra au premier paquet illustre qui voudra une explication. **Routee a `theo-archi` et `nadia-prof`** pour ce jour-la |
| **`DV-6`** | Le texte de remplacement de l'image | **Moitie close** : le choix entre les quatre options est rendu par **`CON-5`**, ecrite par `nadia-prof` le 2026-08-31 et sur le disque. **Ce qui reste ouvert est un fait de produit, pas de design** : ouvrir le palier 1 rend une ligne du programme injouable sans la vue, et aucun texte alternatif conforme a `CON-5` ne peut y changer quoi que ce soit. **Route a `camille-po`, et remonte par moi a la review** - un fait de produit remonte avec l'increment qui le rend concret, pas avant |
| **`DV-7`** | Le pli lui-meme, pour un item qui porte un dessin | **CLOSE**, et pas par moi : `DEC-0051` porte le pli, `DEC-0053` l'a sorti de la porte pedagogique a s22, les deux sont sur le disque, et l'arbitrage **"Il reste hors de la porte"** les reconfirme. **Consequence de planning en section 4.1 : le budget de 53 px ne met aucun veto sur le cadran.** `ines-design` me l'avait routee pour remontee ; la remontee a eu lieu et la reponse est revenue |
| **`DV-8`** | Les onze items de `bandit-manchot-comprehension-cm1` sous le pli, sur les treize vus | **Affectee au lot G pour le comptage** - le jeu est ne a s22, apres la mesure de catalogue de `DEC-0051`, qui ne pouvait donc pas le contenir. **Le jugement - defaut du jeu, ou dette du catalogue - reste a `nadia-prof` et `camille-po`.** Le lot G rend un compte, jamais un verdict, et **ne retire aucun Done** |
| **`DV-9`** | La revelation de l'ecran de saisie sous le pli, a sept largeurs sur dix | **Affectee au lot G pour le comptage**, avec `DV-8` et par le meme outil. **La question qu'`ines-design` pose - *"defaut, ou trou du critere qui vaudrait pour les 31 jeux"* - est routee a `nadia-prof`** : elle porte sur ce qu'un enfant voit, pas sur un chiffre. Je ne la tranche pas, et le lot G ne la tranche pas |
| **`DV-10`** | Le slug du repertoire de sprint | **CLOSE par la section 1** : `s25-le-premier-palier-visuel`, inchange. Aucun de ses fichiers n'a a suivre |

**Deux d'entre elles avaient deja mordu ailleurs, et cela change leur traitement** : `DV-4` est la
meme famille que `D4` a s24 - portee non bloquante alors, elle mord aujourd'hui sur un jeu vert et
ferme une story -, et `DV-2` touchait `ADR-0073`, qui l'a tranchee avant l'ouverture. **La premiere
est la raison pour laquelle je ne recopie pas le traitement de s24 ; la seconde est la raison pour
laquelle le lot A ne decouvre rien.**

> **Trois lignes de ce tableau changent de traitement avec la sortie du lot G** - `DV-4`, `DV-8` et
> `DV-9`, qui y etaient affectees. **Section F.5.** Aucune n'est abandonnee : `DV-4` change de
> proprietaire, `DV-8` et `DV-9` retournent au backlog avec le lot, et le fait qui les accompagne est
> ecrit.

---

## 10. Criteres de sortie du sprint

### Done technique - juge par la CI, jamais assoupli pour tenir un calendrier

1. **`mvn -B clean verify` a la racine du reacteur**, vert sur l'arbre fusionne de cloture. **Total
   de tests etabli par moi sur cet arbre, avec sa methode**, jamais relaye depuis l'annonce d'un
   agent. *Reference de depart : 937 sur l'arbre d'ouverture, section 2.2.*
2. **Le garde-fou d'`ADR-0073` reste bidirectionnel et ses deux morsures restent vertes** : une
   illustration declaree qui ne resout pas est rouge, **et** une illustration deposee sans paquet qui
   la declare est rouge.
3. **La borne de l'exemption de `RessourcesTiercesTest`, mordue dans les deux sens, dans le commit de
   son code** : une seconde forme calculee posee dans le meme gabarit **doit rester rouge**. Sans
   cette moitie-la, la morsure prouve que l'exemption existe, pas qu'elle est etroite. `theo-archi` :
   *"le point le plus dangereux du sprint"*.
4. **Le cliquet de poids d'`ADR-0036` clause 5, pose dans le commit qui livre la premiere
   illustration**, sur la valeur que ce lot installe. **Je fixe le mecanisme - un cliquet se pose sur
   une valeur mesuree, jamais inventee - et non le nombre** : `theo-archi` route la cible a moi et a
   `nadia-prof`, et ce qui est acceptable sur le telephone d'un enfant est un jugement sur l'enfant,
   donc le sien.
5. **`enonce` en premiere cle de chaque item d'un paquet illustre** (`ADR-0073` clause 6), avec sa
   morsure. Motif de `nadia-prof` : sans cela, ses conditions de longueur cessent d'etre executees
   **exactement sur les paquets qui sont neufs**.
6. **Zero attribut d'evenement inline dans un gabarit servi**, verifie par moi a chaque fusion
   (section 6.6). Mesure a l'ouverture : **zero**.
7. **Zero occurrence typographique neuve** dans le diff de s25, verifiee par
   `relecteur-pedagogique` avant la review (section 6.6).
8. **Aucun chemin absolu de poste, aucun nom de compte** :
   `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh`
   sur l'index complet, **a chaque lot**.
9. **Lot F** : `outils/mvn-jdk21.sh` **refuse** au lieu d'imprimer, `outils/verifier-jdk.sh` repond
   sur le fichier dont il parle, les deux ont un harnais et une etape de CI. **Une enveloppe qui
   imprime au lieu de refuser est un garde-fou qui ne garde rien.**
10. **Les tests dans le meme commit que le code, dans tous les modules.** Aucun lot de rattrapage.
11. **Le cliquet de poids ne monte pas silencieusement au second paquet illustre** - critere neuf que
    le quatrieme paquet cree, section F.6.

*Les dix criteres ci-dessus tiennent tels quels avec un quatrieme paquet ; le onzieme est le seul que
la seconde chaine visuelle rend necessaire. Verifie critere par critere en section F.6, plutot que
reconduit en bloc.*

### Done pedagogique - prononce uniquement par `nadia-prof`

**Je ne le prononce jamais et ce document n'en anticipe aucun.**

- **Une fiche neuve par paquet fusionne** dans `squad/conformite/`, avec son motif ecrit.
- **`S25-SEM-1` fait passer sa ligne avec mention**, et la mention est reprise **mot pour mot** de
  `nadia-prof` - la symetrique exacte de celle de `HOR` : la moitie *lire l'heure pleine* reste non
  couverte. Deux entrees sur une meme ligne ne sont pas une double couverture (`CPL-1`).
- **L'audit d'ecran** de sa section 9.2, demande des la fusion 3 (section 7.1).
- **`S25-FRA-1` ne retire aucun Done** : la correction est bornee a un item et un verbe, elle l'ecrit
  explicitement.
- **Aucune ligne ne passe `couvert-valide` sans son verdict.** La matrice ne bouge qu'apres.

---

## 11. Avis de faisabilite de `theo-archi`

`squad/sprints/s25-le-premier-palier-visuel/avis-faisabilite.md`, complete le 2026-08-31 par sa
section 11.

| Lot de son decoupage | Verdict, dans ses mots | Ce que j'en fais |
|---|---|---|
| **Lot 1** - le champ jusqu'a l'ecran | *"Faisable en l'etat, sous `ADR-0073`, qui est ecrit"* | **Lot A**, `marc-dev`, en tete et seul |
| **Lot 2** - les dessins | *"Faisable en l'etat cote architecture"*, et **il porte la dette de cliquet de poids d'`ADR-0036` clause 5, ouverte depuis s10** | **Lot B**, `lea-dev`, avec le cliquet en critere de sortie |
| **Lot 3** - le paquet et le catalogue | *"Faisable moyennant une decision qui n'est pas la mienne"* - la coupure `explication` / `graphie` corrigee, ou un premier paquet sans explication | **Lot C**, `lea-dev`. **La condition est levee** : `nadia-prof` ecrit *"aucune de mes conditions de palier 1 n'exige d'explication"*. Sa section 6.2 perd son objet pour ce sprint ; le defaut reste signale et entier |
| **La famille visuelle entiere en un sprint** | *"Infaisable sans revoir le decoupage"*, et le motif n'est pas technique | **Non tentee.** Un seul paquet visuel engage, section 5 |

**Le resultat qu'il juge le plus utile de son avis, et qui gouverne ce planning** : l'ouverture du
palier 1 coute **zero arret de compilation, quatre classes de garde-fou rouges, six echecs**, et
**aucune classe de mecanique neuve** - *"moins cher que le mot ne le laisse croire"*, et c'est le
resultat d'une mesure, pas d'une opinion.

**Sur les chiffres du cadran et de la piece** : il a mesure **la meme piece de deux euros deux fois**
sur le chemin de production reel - chiffre trace en `path`, clause 4 **verte** ; le meme chiffre en
element de texte, clause 4 **rouge**. Et il a demontre sur les **680 bonnes reponses des 27 paquets
servis** qu'aucune distinction n'est tenable a l'interieur d'un element de texte : les trois criteres
candidats echouent tous *"du meme cote, le grave"*. **Aucune clause n'a ete elargie pour faire tenir
une ligne** - le compte des morsures monte de six a sept.

**La reserve qu'il attache et qui n'est pas d'architecture** : *"c'est `ines-design` qui dit si douze
chiffres traces sont producibles"*. **Elle a repondu : oui - dix glyphes et non douze dessins, zero
police, zero texte - mais 123 px de hauteur d'image pour etre lisibles, contre 43 px pour une
piece.**

---

## 12. Ce que ce document route, sans le trancher

| # | Obstacle | Categorie | A qui | Artefact attendu | Etat |
|---|---|---|---|---|---|
| 1 | **Un paquet illustre de monnaie fait-il passer la ligne entiere, ou faut-il deux paquets ?** Deux documents du 2026-08-31, de la meme autrice, portent deux couts differents (section 5.2) | Pedagogie | `nadia-prof` | Une reponse disant laquelle des deux phrases gouverne | **OUVERT** |
| 2 | **`S25-MON-1` est-elle rouverte sur sa forme illustree ?** Le refus porte sur le contenu textuel ; l'image n'a jamais ete refusee | Produit, perimetre | `camille-po` | **Amendement de `DEC-0065`**, dans un sens ou dans l'autre | **OUVERT**, depend de 1 |
| 3 | **Le rang des trois lots de dette** - gestionnaire inline (porte de la dette n. 4 de `CLAUDE.md`), cliquet typographique aveugle aux gabarits, trois noms d'un concept | Produit, rang | `camille-po` | `DEC` de rang, ou report explicite avec motif | **OUVERT** - non engages a s25, croissance arretee par les criteres 6 et 7 |
| 4 | **Le caractere retenu pour `TYP-1`** - insecable ordinaire, insecable etroite, ou retrait de l'espace | Pedagogie | `nadia-prof` | Avis ; le **perimetre** du cliquet reste a `theo-archi` | **OUVERT** |
| 5 | **Le contraste du trait d'un `.svg` servi** (`DV-3`) | Pedagogie, puis technique | `nadia-prof`, puis `theo-archi` et `yanis-qa` | Condition d'ecriture, puis faisabilite d'un garde-fou | **OUVERT** |
| 6 | **La borne de longueur d'une proposition** (`DV-4`), qui ferme `S25-LIS-1` | Pedagogie et produit | `nadia-prof`, `camille-po` | Condition chiffree | **OUVERT**, le lot G rend la mesure |
| 7 | **Les onze items sous le pli d'un jeu vert** (`DV-8`) et **la revelation de l'ecran de saisie** (`DV-9`) - defaut, ou trou du critere de `DEC-0051` | Pedagogie et produit | `nadia-prof`, `camille-po` | Verdict sur le critere | **OUVERT**, le lot G rend le compte |
| 8 | **Une ligne du programme devient injouable sans la vue** (`DV-6`, moitie restante) | Produit | `camille-po`, puis l'utilisatrice | `DEC` ou arbitrage | **OUVERT**, remonte par moi **a la review**, pas avant |
| 9 | **La coupure `explication` / `graphie`** - defaut du depot trouve par la sonde de `theo-archi` | Produit pour le rang, technique pour le moyen | `camille-po`, `theo-archi` | `DEC` de rang, puis `ADR` | **OUVERT**, **ne mord pas a s25** |
| 10 | **`S25-SCO-1`, second report** - le seul poste dont le prix augmente mecaniquement a chaque sprint | Produit, rang | `camille-po` | Rang a s26, sur le motif ecrit en 6.5 | **OUVERT** |

**Aucun de ces dix obstacles n'est clos par ce document, et aucun ne le sera sur une intention
annoncee** - seulement quand le fichier `DEC` ou `ADR` qui le tranche existera sur le disque.

> **Quatre lignes de ce tableau ont bouge** : les obstacles 1 et 2 sont **CLOS par des fichiers qui
> existent**, l'obstacle 6 change de proprietaire, l'obstacle 7 retourne au backlog. **Etat a jour en
> section F.9**, avec les obstacles neufs que la finalisation ouvre.

---

## 13. Discipline de worktree - la confirmation quotidienne s'applique

`squad/README.md` section 9, amendement de la retro de s23 : **a chaque daily, je demande
explicitement a chaque agent en cours de lot de confirmer que son premier commit a bien ete ecrit
dans un worktree dedie.** La regle relue au planning ne suffisait plus ; c'est le moment de la
verification qui a change.

**Constat a l'ouverture de s25 : les quatre agents amont sont en regle** (section 2.1). Second sprint
consecutif sans occurrence a l'ouverture.

**Le daily n'est pas journalise** : il ne produit aucun fichier. Les obstacles qu'il leve remontent
dans le tableau de `review.md` et dans `RAPPORT.md`.

---

## 14. Decisions prises par ce document

Toutes dans mon domaine - capacite, sequencement, affectation, conduite de ceremonie :

- **Le slug `s25-le-premier-palier-visuel`, fixe et inchange** (`DV-10` close).
- **Les affectations nominatives des huit items**, et le placement du lot A seul et en tete, avec son
  motif ecrit **contre** le patron de s24.
- **L'indissociabilite des lots B et C a la fusion**, sur le motif technique du garde-fou
  bidirectionnel d'`ADR-0073`.
- **L'ordre de fusion**, et **le passage du lot F avant le lot G** - on repare l'instrument avant de
  mesurer avec.
- **Le declencheur de bascule** de la section 6.4, objectif et sans calendrier.
- **La demande de l'audit d'ecran de `nadia-prof` des la fusion 3**, et non a la review.
- **Le sequencement de `yanis-qa` et de `relecteur-pedagogique` avant la review**, avec le perimetre
  de chacun, et le cout de `S25-SUB-1` price chez celui qui le porte.
- **Les deux verifications qui tiennent le zero sans acheter les lots de dette** (criteres 6 et 7),
  nommees comme des garde-fous humains qui expirent a la cloture.
- **Le motif ecrit du second report de `S25-SCO-1`**, que `camille-po` a explicitement laisse a moi,
  et ceux de `S25-DEN-1` et `S25-LIS-1`.
- **Le traitement des dix dependances d'`ines-design`** : trois closes par des fichiers qui existent,
  deux affectees a un lot, cinq portees avec leur proprietaire nomme.

**Aucune decision de produit, d'architecture ou de pedagogie n'est prise ici.**

**Decisions du sprint deja sur le disque a l'ouverture** : `DEC-0065` (*Acceptee*, amendee),
`DEC-0066` (*Acceptee*), `ADR-0073` (*Accepte*, amende). **Aucune autre.** Les dix obstacles de la
section 12 n'ont produit a ce jour **aucun fichier de decision**, et c'est ecrit ici plutot que
laisse vide.

---

## Amendement de finalisation du 2026-08-31 - `Q1` et `Q2` ont rendu, l'utilisatrice a tranche la bascule, la composition passe a quatre paquets

**Statut du planning apres cet amendement : finalise.**

**Pourquoi un amendement et non une reecriture** : `DEC-0065`, second amendement, **cite nommement
mes sections 5.3 et 6.5**, et `docs/backlog/backlog.md` cite la seconde dans la story rouverte.
Reecrire ces sections ferait pointer deux references vers rien, dans une decision *Acceptee*. C'est
la famille de defaut que `CLAUDE.md` porte deja une fois corrigee. **Rien n'est efface ; ce qui est
remplace porte un renvoi a l'endroit ou il l'est.**

### F.1 Les deux questions sont closes, et par des fichiers qui existent

| Question | Reponse | Fichier sur le disque |
|---|---|---|
| **`Q1`** - un paquet illustre de monnaie ou deux ? | **Un paquet, ligne entiere.** *"C'est la phrase du palier 1 qui gouverne."* | `nadia-prof`, arbitrage des seize lignes, **section 12** (commit `b47c5dc`) |
| **`Q2`** - le quatrieme paquet est-il `MON` illustre ? | **Oui**, `S25-MON-1` **rouverte** sur sa forme illustree | `camille-po`, **`DEC-0065` second amendement**, sections B.2 et B.3 ; story rouverte dans `docs/backlog/backlog.md` (commit `b5aaec4`) |

**Ce que je verifie avant de m'appuyer dessus, plutot que de le relayer** : `nadia-prof` tranche **par
son propre document**, sans rien d'exterieur - *"compter deux paquets, c'etait compter comme second
paquet celui que je venais de refuser trois pages plus haut. Ce n'est pas une hesitation entre deux
lectures defendables : c'est une incoherence interne."* Et le moyen de verifier qu'elle n'a pas choisi
le chiffre qui arrange est dans le fichier : **`MON-3` et le `REFUSE` du textuel sont ecrits dans la
version du matin, avant que la question du cout ne se pose.** Relu par moi sur le fichier : c'est
exact. *"Si le chiffre juste avait ete deux, il m'aurait fallu rouvrir un refus ; c'est l'inverse."*

**Et `camille-po` rouvre avec son propre critere, pas par consequence** - `nadia-prof` ayant ecrit
n'avoir aucune preference entre monnaie et figures planes : *"le paquet textuel nommait les pieces, la
reconnaissance etait deja faite a la place de l'enfant. Le paquet illustre les montre. **Deux objets,
pas deux lectures.**"*

**Ce que je n'avais pas vu et qu'elle corrige chez elle** : `DEN` **reste a deux paquets** -
`nadia-prof` le verifie au lieu de l'aligner par commodite, *"deux exercices differents, pas deux
verbes du meme item"*. Ma section 5.2 opposait deux phrases sur la monnaie ; **elle ne disait rien de
`DEN`, et c'etait juste de ne rien en dire.**

### F.2 La composition finale - quatre paquets, quatre lignes distinctes

| Paquet | Nature | Ligne visee | Story |
|---|---|---|---|
| **`FIG`** - figures planes | **visuel, 1 sur 2** | Reconnaitre et nommer quelques figures planes (maths CP), **ligne entiere** | `S25-FIG-1` |
| **`MON`** - la monnaie illustree | **visuel, 2 sur 2** | Reconnaitre et utiliser la monnaie (maths CP), **ligne entiere** | `S25-MON-1`, **rouverte** |
| **`SEM`** - se reperer dans la journee, la semaine | texte | La ligne de l'heure (maths CP), **avec mention** | `S25-SEM-1` |
| **`SUB`** - substituts d'un personnage | texte, **`COMP-2` du sprint** | Reperer les substituts (francais CM1), **ligne entiere** | `S25-SUB-1` |

> **Plafond retenu : +4 lignes, soit 31 / 41 = 75,6 %.**

**Methode, refaite par moi sur l'arbre fusionne et non recopiee** : 27 lignes `couvert-valide`, 13
`non couvert`, 1 `en cours`, 6 `non couvrable en l'etat` sur les quatre tableaux de matiere de
`squad/programme/MATRICE-couverture.md`, total 47, denominateur 47 - 6 = 41 ; quatre paquets bougeant
chacun **une ligne distincte** ajoutent quatre au numerateur. *Perimetre : les quatre tableaux de
matiere. Aucun module compile, donc aucune question de `-am`.* **Les deux commits amont de cette
finalisation n'ont fait bouger aucun statut de la matrice** - recompte par moi apres fusion, les
quatre chiffres sont identiques a ceux de la section 2.3.

**Ce chiffre reste un plafond et pas une prevision** : il suppose que **quatre** paquets ecrits dans
le sprint passent la porte pedagogique du premier coup, ce qui n'est jamais arrive sur ce produit avec
un seul.

**La borne de `nadia-prof` est atteinte exactement** - quatre paquets, dont deux visuels - **et jamais
depassee.** `CON-4`, le controle exhaustif par masquage de l'image, porte sur **chaque** paquet
visuel ; elle a ecrit qu'elle ne lui applique **pas** le quota `COMP-2` et pourquoi, *"une demi-passe
de plus sur un paquet, pas un paquet"*. **Le quota `COMP-2` de s25 reste donc pris par `S25-SUB-1`, et
par lui seul** - je le note parce que deux paquets visuels auraient pu laisser croire le contraire.

**Le piege de comptage sert une troisieme fois, et c'est ce qui rend `MON` irremplacable** : `HOR` vise
la meme et unique ligne que `SEM`. **Tout autre quatrieme paquet que `MON` ferait quatre paquets pour
trois lignes.**

### F.3 Les affectations finales - neuf items, trois par tete

**Le lot G sort ; la chaine `MON` entre en deux lots.** Huit items deviennent neuf.

| Ordre | Item | Nature | Competence visee | Affecte a | Depend de |
|---|---|---|---|---|---|
| 1 | **Lot A** - le champ de contenu, du domaine jusqu'a l'ecran | technique | transverse | `marc-dev` | rien |
| 2 | **Lot F** - les deux dettes d'outillage de s24 | technique, outillage | aucune ligne | `sofia-sm` (dev) | rien |
| 3 | **Lot B** - les dessins de `FIG` et le cliquet de poids | technique et contenu visuel | mathematiques CP, espace et geometrie | `lea-dev` | rien |
| 4 | **Lot I** - les dessins de `MON` | technique et contenu visuel | mathematiques CP, nombres et calculs | `sofia-sm` (dev) | rien |
| 5 | **Lot C** - le paquet `FIG` et son entree de catalogue | contenu | mathematiques CP, espace et geometrie, **ligne entiere** | `lea-dev` | **lots A et B** |
| 6 | **Lot J** - le paquet `MON` et son entree de catalogue | contenu | mathematiques CP, nombres et calculs, **ligne entiere** | `sofia-sm` (dev) | **lots A et I** |
| 7 | **Lot E** - `S25-SUB-1` | contenu | francais CM1, lecture et comprehension de l'ecrit | `marc-dev` | rien |
| 8 | **Lot D** - `S25-SEM-1` | contenu | mathematiques CP, grandeurs et mesures, **mention** | `lea-dev` | rien |
| 9 | **Lot H** - `S25-FRA-1` | contenu, correction | aucune ligne, **Done non retire** | `marc-dev` | rien |

**Les criteres de chaque lot survivant sont ceux de la section 6.2 et ne bougent pas.** Les criteres
du lot J sont `MON-1` a `MON-5` plus `CON-1` a `CON-5`, dans les mots de `nadia-prof`, tels que la
story rouverte les porte ; ceux du lot I sont `ADR-0073` clause 4, `DV-1`, et **`MON-1` traduit en
dessin** - les pieces montrees portent leur valeur **gravee en geometrie**, jamais en element de
texte.

**Le motif de cette repartition, et il n'est pas celui que j'avais annonce en section 6.5 :**

- **Les deux chaines visuelles courent sur deux tetes differentes, en parallele, et jamais l'une
  derriere l'autre.** C'est la seule disposition qui protege la quatrieme ligne, et **la quatrieme
  ligne est ce que l'utilisatrice vient de choisir de garder.** En section 6.5 j'annoncais la chaine
  `MON` sur ma propre tete a cote du lot G ; le lot G etant sorti, cette contrainte est tombee, et la
  mettre derriere la chaine `FIG` sur une seule tete aurait fait arriver le second paquet visuel en
  fin de sprint - **exactement les conditions dans lesquelles `nadia-prof` decline par ecrit.**
- **Les deux lots de dessins ne dependent de rien** (`theo-archi` : le lot des dessins *"ne depend
  d'aucun autre"*). Ils occupent donc l'attente du lot A sur les deux tetes qui ne le portent pas.
  **L'attente du lot A est le seul temps mort structurel du sprint, et les dessins sont le seul
  travail visuel qui puisse le remplir.**
- **La main qui trace declare les proportions.** `lea-dev` dessine et ecrit `FIG` ; `sofia-sm` dessine
  et ecrit `MON`. `FIG-1` exige que *"les proportions de la figure soient declarees par le contenu"*,
  et `MON-2` que les valeurs montrees soient celles qui existent : les separer ferait declarer par une
  tete ce qu'une autre a dessine.
- **`marc-dev` porte le lot A seul et en tete**, puis les deux items qui ne bloquent personne -
  `S25-SUB-1`, le plus gros paquet de texte et le seul `COMP-2` autorise, puis le lot H. **Il est
  aussi le renfort des deux declencheurs de la section F.4** : la tete qui a ecrit le champ
  d'illustration est celle qui debloque le plus vite une chaine visuelle en retard.
- **Les deux paquets de contenu seul sont en fin de file sur leur tete respective.** Ce n'est pas un
  hasard : ce sont **les premiers a tomber dans l'ordre de sacrifice de `nadia-prof`**, et la place
  qu'ils occupent dans une file doit dire la meme chose que cet ordre.

**Ce que cette repartition ne resout pas, et je l'ecris plutot que de le decouvrir** : neuf items pour
trois tetes est le niveau de s24, et s24 n'a pas tout livre. La difference est que **le lot qui glisse
en premier est designe d'avance** par la place qu'il occupe, et que deux declencheurs objectifs
transforment un glissement en decision plutot qu'en constat de cloture.

### F.4 Les deux declencheurs - objectifs, verifiables par moi, sans calendrier

Le declencheur de la section 6.4 cesse d'etre observable : `S25-SUB-1` y est desormais derriere le lot
A. **Il est remplace par deux declencheurs, qui portent le meme objet.**

> **Declencheur 1 - le lot A bloque deux chaines.** Si les branches des lots B **et** I portent
> chacune au moins un dessin commite et que le lot A n'est pas fusionne vert, **`lea-dev` rejoint
> `marc-dev` sur le lot A.** *Motif : les deux chaines visuelles sont alors entierement bloquees par
> un lot que `theo-archi` decrit comme "le plus petit des trois", et deux tetes attendent. C'est un
> renfort, aucun lot ne sort.*

> **Declencheur 2 - la quatrieme ligne.** Si le lot C est fusionne vert alors que **la branche du lot
> I ne porte aucun dessin commite**, je reaffecte `lea-dev` sur le lot I, et **`S25-SEM-1` sort du
> perimetre engage**, motif ecrit dans `review.md`. *Motif : les dessins sont la seule partie de la
> chaine `MON` qui pouvait etre faite pendant l'attente du lot A ; s'ils ne le sont pas quand le
> premier paquet illustre est deja vert, le second arrivera en fin de sprint. `S25-SEM-1` est un
> paquet de contenu seul, le premier a tomber dans l'ordre de `nadia-prof`, et le seul lot dont la
> chute ne fait tomber que sa propre ligne.*

**Les deux se verifient par `git`, sur une branche, sans que personne ait a se declarer.** C'est
volontaire : la confirmation quotidienne de worktree (section 13) porte deja sur une declaration, et
un declencheur de capacite qui reposerait aussi sur une declaration ne serait verifie par rien.

### F.5 Le lot G sort - ce qui le suit, et le fait qui l'accompagne

> **L'utilisatrice a tranche la bascule que `camille-po` lui avait rendue en une phrase :
> "Garder la quatrieme ligne".** *De l'utilisatrice, 2026-08-31, cite mot pour mot et fige a la
> reception - aucune occurrence de cet enonce n'existait dans l'arbre suivi avant cette ligne.*

**Le lot G - l'instruction de la dette de pli sur le catalogue livre - sort du perimetre engage comme
lot de developpeur. Le comptage n'aura pas lieu a s25.** Je l'ecris sans l'habiller, comme
`camille-po` a refuse de l'habiller.

**Ce qui survit et ne consomme aucune tete** : `camille-po` prend **`DV-4`**, la borne sur la longueur
des propositions, qui lui etait deja routee. Elle pose la question a `nadia-prof` avec le materiau
qu'`ines-design` a deja produit. **Ce n'est pas un substitut au comptage, et elle l'ecrit elle-meme** :
*"personne ne doit lire ce paragraphe comme si l'arbitrage etait execute."*

**Ce qui retourne au backlog avec le lot** :

| Ce qui retourne | Etat |
|---|---|
| **Le comptage du pli item par item sur tout le catalogue enumere** | **Au backlog**, non instruit a s25. L'outil existe (`squad/outils/nadia-prof/compter-le-pli-du-catalogue-entier.py`) et le lot F le rend fiable ; il ne manque qu'une tete |
| **`DV-8`** - les onze items de `bandit-manchot-comprehension-cm1` sous le pli, sur les treize vus | **Au backlog** avec le comptage. Route a `nadia-prof` et `camille-po`, non instruit |
| **`DV-9`** - la revelation de l'ecran de saisie sous le pli a sept largeurs sur dix | **Au backlog** avec le comptage. Route a `nadia-prof`, non instruit |
| **`DV-4`** - la borne de longueur d'une proposition | **Change de proprietaire** : prise par `camille-po` dans s25, sans tete de developpeur |

**Le fait qui doit rester attache a ce report, et qui n'est pas de moi** : `camille-po` ecrit que **la
dette de pli n'a pas de terme** - un sprint de plus a l'instruire ne coute rien d'irreversible, la ou
la quatrieme ligne ne se rattraperait pas au meme prix, le plafond de deux paquets visuels de
`nadia-prof` ne valant *"pour s25 seulement"*.

**Et le fait que je refuse de laisser tomber avec le lot** : **un jeu deja valide a onze de ses treize
items vus sous le pli**, a la largeur de reference, apres un echec - le critere meme de `DEC-0051`.
`bandit-manchot-comprehension-cm1` est ne a s22, apres la mesure de catalogue de `DEC-0051`, qui ne
pouvait donc pas le contenir. **Personne ne sait aujourd'hui si c'est un cas isole ou un motif**, et
c'est precisement ce que le comptage aurait dit. Ce fait est d'`ines-design`, section 4.4 de sa
proposition, et il reste ouvert.

**Le lot F garde son rang au jour 1, et son motif change** - je le dis parce que le motif que j'avais
ecrit dependait du lot G :

- **Ce qui tombe** : *"on repare l'instrument avant de mesurer avec"* ne s'appuie plus sur le lot G,
  qui n'a plus lieu. `camille-po` a d'ailleurs ecarte de sacrifier F **a cause** de cette dependance,
  et cette raison-la disparait avec G.
- **Ce qui reste, et qui suffit** : le sprint mesure encore a chaque fusion - ma porte technique, le
  cliquet de poids pose sur une valeur mesuree, la campagne de `yanis-qa`. **L'enveloppe est l'outil
  de toutes ces mesures.** Et j'ai refuse par ecrit, a la review de s24, qu'une quatrieme occurrence
  documentee glisse une cinquieme fois.
- **Ce qui decide vraiment son rang, et c'est du sequencement pur** : **le lot F occupe le seul creneau
  du sprint ou une tete n'a rien de bloquant a faire** - l'attente du lot A. Il ne prend sa place a
  aucun autre lot. **Un item gratuit ne se reporte pas.**

### F.6 Les criteres de sortie tiennent-ils avec un quatrieme paquet ? - verifie critere par critere

| Critere | Tient-il ? |
|---|---|
| 1 - porte technique mesuree par moi sur l'arbre de cloture | **oui**, inchange |
| 2 - garde-fou d'`ADR-0073` bidirectionnel, deux morsures vertes | **oui**, et il est desormais **exerce par deux paquets** au lieu d'un |
| 3 - borne de l'exemption de `RessourcesTiercesTest`, mordue dans les deux sens | **oui**, inchange - c'est une propriete du lot A, pas du nombre de paquets |
| 4 - cliquet de poids d'`ADR-0036` clause 5 | **oui, mais il ne suffit plus seul** - voir le critere 11 |
| 5 - `enonce` en premiere cle de chaque item illustre | **oui**, et il porte maintenant sur **deux** paquets |
| 6 - zero attribut d'evenement inline | **oui**, et son motif se renforce : deux chaines visuelles font deux fois plus de gabarit |
| 7 - zero occurrence typographique neuve | **oui**, inchange |
| 8 - aucun chemin de poste ni nom de compte | **oui**, inchange |
| 9 - lot F | **oui**, inchange |
| 10 - tests dans le commit de leur code | **oui**, inchange |

**Le seul critere que le quatrieme paquet rend necessaire, et il est neuf :**

> **11 - Le cliquet de poids ne monte pas silencieusement au second paquet illustre.** `ADR-0036`
> clause 5 fait poser le cliquet par *"le commit qui livre la premiere image illustree"*. **Avec deux
> chaines visuelles, il y a une premiere et une seconde.** La premiere paire fusionnee pose le cliquet
> sur la valeur qu'elle installe ; **la seconde le respecte, ou le deplace dans le meme commit avec son
> motif ecrit et sa valeur mesuree.** Un cliquet releve sans motif au second lot ne cliquete plus rien
> - c'est la definition meme d'un garde-fou qui ne garde plus, et ce depot l'a deja sanctionnee.

**L'ordre de fusion est complete, non remplace** :

1. **Lot F**, le plus tot possible. Aucun module de production.
2. **Lot A**, seul, vert, **avant toute paire visuelle**.
3. **Lot B + C en une fusion**, et **lot I + J en une fusion**, **chaque paire indissociable**, dans
   l'ordre ou elles deviennent vertes. *Motif inchange et desormais double : le garde-fou
   bidirectionnel d'`ADR-0073` refuse une illustration deposee sans paquet qui la declare, donc un lot
   de dessins fusionne seul rendrait la porte technique rouge.* **La premiere paire fusionnee pose le
   cliquet de poids ; la seconde tombe sous le critere 11.**
4. **Lots E et D**, chacun des qu'il est vert.
5. **Lot H**, a tout moment.

**Quatre lots touchent desormais `catalogue.yaml`** - C, J, E et D - au lieu de trois. Ce sont des
ajouts en fin de liste : **je resous les conflits a la fusion, jamais l'auteur dans sa branche.**

### F.7 La parade contre le lot tardif, avec deux paquets visuels

**Elle tient telle quelle sur la moitie qu'elle couvrait, et elle ne couvre pas l'autre.**

- **L'audit d'ecran de `nadia-prof` est *"une fois, pas par paquet"*** - c'est sa definition, section
  9.2. **Je le lui demande donc des que la premiere paire visuelle est fusionnee verte**, section 7.1,
  inchangee. Un second paquet illustre ne le fait pas rejouer.
- **L'audit de contenu d'un paquet visuel, lui, est par paquet** - la passe d'exactitude image par
  image plus le masquage de `CON-4`. **C'est celui-la qu'elle decline s'il arrive trop tard**, et
  aucune demande anticipee ne peut le devancer : il exige que le paquet existe. **Ce qui le protege est
  le sequencement de la section F.3** - deux chaines paralleles sur deux tetes - **et le declencheur 2
  de la section F.4.**

**Je le formule ainsi parce que la parade que j'avais ecrite aurait pu passer pour couvrir les deux, et
elle n'en couvre qu'une.**

### F.8 Quand lancer `yanis-qa` et `relecteur-pedagogique` - les points nommes

Le sequencement de la section 8 est inchange : **les deux avant la review, jamais apres.** Ce qui
manquait est le point de declenchement, et il m'a ete demande de le nommer plutot que de le laisser
deduire.

| Qui | Je le nomme quand | Observable |
|---|---|---|
| **`relecteur-pedagogique`** | **quand les quatre lots de contenu et le lot H sont fusionnes verts** - C, J, E, D, H | `git log` sur `s25-sofia` : les cinq fusions sont dans l'arbre |
| **`yanis-qa`** | **quand la derniere fusion technique est verte** - A, F et les deux paires visuelles - **et apres le rapport du relecteur**, pour qu'une correction de texte ne rouvre pas sa mesure | `git log` : lots A, F, B+C, I+J fusionnes ; rapport du relecteur sur le disque |

**Si les deux conditions tombent en meme temps, le relecteur passe en premier** : ses corrections
touchent des fichiers servis, et une campagne de mutation lancee avant elles porterait sur un arbre qui
n'est pas celui de la cloture.

**Le cout de `S25-SUB-1` reste price chez le `relecteur-pedagogique`** - deux fois et demie le volume
de texte d'un paquet ordinaire, depuis que `DEC-0056` a sorti l'accentuation de la porte de
`nadia-prof`. **Et il porte maintenant, en plus, les textes alternatifs de deux paquets illustres**,
dont `CON-5` fait le seul endroit du produit ou une reponse peut fuir sans que personne s'en apercoive.

### F.9 Etat des obstacles apres finalisation

| # | Obstacle | Etat |
|---|---|---|
| 1 | Un paquet illustre de monnaie fait-il passer la ligne entiere ? | **CLOS** - `nadia-prof`, section 12 de son arbitrage des seize lignes, sur le disque |
| 2 | `S25-MON-1` rouverte sur sa forme illustree ? | **CLOS** - `DEC-0065` second amendement, *Acceptee*, sur le disque |
| 3 | Le rang des trois lots de dette de `theo-archi` | **OUVERT**, inchange. Croissance arretee par les criteres 6 et 7 |
| 4 | Le caractere retenu pour `TYP-1` | **OUVERT**, inchange |
| 5 | Le contraste du trait d'un `.svg` servi (`DV-3`) | **OUVERT**, inchange - et il porte desormais sur **deux** paquets illustres au lieu d'un |
| 6 | La borne de longueur d'une proposition (`DV-4`) | **OUVERT, proprietaire change** : prise par `camille-po` dans s25, sans tete de developpeur. Le lot G ne la mesure plus |
| 7 | Les onze items sous le pli d'un jeu vert (`DV-8`) et la revelation de l'ecran de saisie (`DV-9`) | **OUVERT, retourne au backlog** avec le comptage. **Non instruit a s25**, avec le fait de la section F.5 |
| 8 | Une ligne du programme devient injouable sans la vue (`DV-6`) | **OUVERT**, et il **s'aggrave** : deux lignes du programme, pas une. Remonte par moi a la review |
| 9 | La coupure `explication` / `graphie` | **OUVERT**, ne mord pas a s25 |
| 10 | `S25-SCO-1`, second report | **OUVERT**, inchange |
| **11** | **La longueur des propositions n'est bornee par aucune condition** - `nadia-prof` note que `LIS-2` et `LIS-3` bornent le texte support et l'enonce, et que **rien chez elle ne borne les propositions** : *"un manque de ma part"* | **OUVERT** - c'est le fond de `DV-4`, et l'autrice des conditions le nomme elle-meme. Elle ecrira la condition manquante quand `LIS` reviendra |
| **12** | **`nadia-prof` ignore que la collision des chiffres traces est reglee** : elle ecrit *"la question reste posee a `theo-archi` si elle n'a pas deja recu de reponse"*, alors qu'`ADR-0073` amende y repond nommement sur `MON-1` depuis le meme jour | **OUVERT, et il est a moi** : c'est une synchronisation d'information, pas une decision. **Je le lui porte au premier daily**, avec la reference qui resout. Un audit conduit en croyant une contrainte non levee n'est pas le meme audit |

**Aucun de ces obstacles n'est clos sur une intention.** Les obstacles 1 et 2 le sont parce que **deux
fichiers existent sur le disque et que je les ai lus.**

### F.10 Les deux faits techniques que la monnaie consomme, et qui ne sont pas de moi

Ils sont acquis, ils sont mesures, et ils portent la quatrieme ligne :

- **Une piece dessinee peut porter sa valeur gravee.** `theo-archi`, `ADR-0073` amende, repond
  **nommement sur `MON-1`** : un chiffre **trace en geometrie** passe la clause 4, le meme **ecrit en
  element de texte** la fait rougir - mesure sur **la meme piece de deux euros deux fois**, sur le
  chemin de production reel. **Aucune clause n'a ete elargie pour faire tenir une ligne** ; le compte
  des morsures monte de six a sept.
- **La monnaie illustree passe le pli, et pas le cadran.** `ines-design` mesure **43 px** pour une
  piece contre un budget de **53 px** ; le cadran en demande **123**. Ce qui ne bloque plus le cadran,
  **le pli restant hors de la porte pedagogique par arbitrage de l'utilisatrice** - et `nadia-prof`
  ecrit le prendre sans le rouvrir a sa porte.
- **Un fait de methode que je releve parce qu'il est rare** : la mesure du budget par `ines-design`
  (53 px) et celle que `nadia-prof` avait tiree de ses archives de s21 (65 px) **convergent**, les
  12 px d'ecart etant les marges du bloc d'illustration. **Deux mesures independantes qui se rejoignent
  valent mieux que l'une des deux repetee** - c'est ce que les exigences `P1` a `P5` cherchaient a
  produire, et c'est ce qu'elles ont produit.

### F.11 Ce que cet amendement ne fait pas

- Il **n'efface rien** du planning, y compris les enonces qu'il remplace et les deux que `DEC-0065`
  cite.
- Il **ne rouvre aucun des deux arbitrages** de `nadia-prof` ni le perimetre de `camille-po`, et il
  **ne releve la borne pedagogique d'aucune unite**.
- Il **ne prononce aucun Done**, ne fait bouger aucune ligne de la matrice, et **n'anticipe aucun
  verdict** sur les quatre paquets.
- Il **ne recompose pas les criteres d'acceptation** d'un seul lot : ils sont de `camille-po` et de
  `nadia-prof`, et ils se lisent dans le backlog et dans les arbitrages.
- Il **ne fait entrer aucun des trois lots de dette de `theo-archi`** au perimetre : la place liberee
  par le lot G est prise par la chaine `MON`, sur l'arbitrage de l'utilisatrice, et le rang de ces
  trois lots reste a `camille-po`.
