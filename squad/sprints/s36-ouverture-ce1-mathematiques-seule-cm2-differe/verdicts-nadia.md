# Verdicts pedagogiques de s36 - `nadia-prof`

**Auteur** : `nadia-prof`. **Une seule instance ce sprint.**
**Date** : 2026-09-21.
**Arbre** : `s36-sofia` a `72013e74`, worktree dedie `s36-nadia-audit`, ouvert depuis ce commit.
Lignee verifiee : `72013e74` descend bien de `round-two` a `a8fcb000`.
**Ou vivent les verdicts qui font foi** : dans les fiches de conformite, pas ici. Ce document les
reprend pour la review ; **en cas d'ecart, la fiche gagne.**

---

## 0. Conditions de l'audit, declarees avant les verdicts

**J'ai joue, et cette fois dans un vrai navigateur.** C'est la difference de methode avec s35, ou
j'avais declare un audit degrade faute d'avoir rendu l'ecran.

- Jar construit depuis mon propre worktree par `mvn -B clean verify` sur la racine, via l'enveloppe
  de toolchain, puis lance par `java -jar bootstrap/target/recreo.jar` sur le JDK `21.0.10` que
  cette toolchain resout.
- Jeu pilote dans Chrome par le protocole DevTools : **clics de pointeur reels**
  (`Input.dispatchMouseEvent`, la sequence qu'un doigt produit), geometries lues par
  `getBoundingClientRect` sur le document servi, captures d'ecran du rendu reel, y compris agrandi
  seize fois pour lire le rapport entre l'encre d'un caractere et les traits d'un dessin.
- **Une partie complete de cinq manches sur `cp-plan-de-la-classe`, les six items vus a l'ecran**,
  plus une manche rejouee a **trois erreurs volontaires** pour lire ce que l'enfant obtient quand
  il bloque. Plus une manche sur `cp-designation-frise-de-la-journee`, pour verifier que l'ecran
  que j'avais deja accorde n'a pas bouge.

**Porte technique : je nomme ce que j'ai lance, et rien d'autre** (`CLAUDE.md`, amendement du
2026-09-08). J'ai lance le contenu du seul job `build` : `mvn -B clean verify` sur la racine, vert,
**6 modules, 1 496 tests** (111 + 13 + 400 + 708 + 258 + 6). **Les autres jobs du workflow, je ne
les ai pas lances et je ne les deduis pas** ; la porte technique complete est confirmee par
`sofia-sm`.

---

## 1. `S36-QLM11-PLANCLASSE` - **VALIDE SOUS RESERVE**, le refus de s35 est leve

**Fiche qui fait foi** : `squad/conformite/FICHE-designation-plan-de-la-classe.md`, bloc de
validation nº 2 du 2026-09-21. Le bloc nº 1, le refus du 2026-09-11, y est **conserve entier** : un
refus efface n'a aucune valeur de preuve, et c'est lui qui a produit la forme livree ce sprint.

**Mouvement de matrice** : **`QLM-CP-11` passe `non couvert` -> `couvert-valide`.** C'est
`camille-po` qui ecrit la ligne ; le Done pedagogique qui l'autorise est dans la fiche.

### Ce qui a ete verifie, en trois points

1. **`ZON-15`, premiere moitie - tenue sur les six zones.** C'etait tout le motif du refus de s35 :
   17 a 95 px de debordement sur six zones sur six. Mesure du jour sur le document servi :
   **0,00 px de debordement horizontal et vertical sur les six**, et `scrollWidth`/`scrollHeight`
   n'en cachent aucun. Le plancher de police est tenu (20 px gras, soit 1,25rem), le pave de
   `.zone` n'a pas ete rogne, la police n'a pas ete baissee. **La barre que j'avais ecrite est
   atteinte, je leve ma reserve.**

2. **`ZON-15`, seconde moitie - rompue a la lettre sur trois zones, clarifiee plutot que tue.**
   Mesure au pixel, dessin agrandi seize fois, par diff des rendus avec et sans les chiffres : le
   **1** pose 21,1 % de son encre sur le bord inferieur du rectangle du tableau, le **4** 30,9 % sur
   la ligne mediane des deux vantaux de l'armoire, le **6** 28,2 % sur l'arc de debattement de la
   porte. Zones 2, 3 et 5 : zero. **Je clarifie ma propre condition, datee du 2026-09-21** : elle
   protege que l'enfant voie la silhouette de l'objet, pas qu'aucun jambage ne croise aucun trait.
   **C'est un assouplissement, je le dis comme tel**, meme famille que la reformulation de `ZON-16`
   du 2026-09-11. Ce qui l'autorise : j'ai regarde les deux rendus cote a cote, les six silhouettes
   sont aussi identifiables dans l'un que dans l'autre. **La sortie propre existe quand meme** et
   je l'ouvre en reserve (`PCL-R3`) : un chiffre dans un coin du cadre plutot qu'en son centre ne
   croiserait aucun des trois traits.

3. **La correspondance numero -> libelle - lisible sans ambiguite par un enfant de CP.** C'est la
   propriete qu'`ADR-0094` me laisse explicitement et que rien ne garde. Verifiee sur le document
   servi : un caractere unique de 1 a 6 servi par le serveur, a 20 px gras ; une legende de six
   lignes dans le meme ordre, a 20 px, jamais plus petite que le chiffre qu'elle explique ; les six
   correspondances bijectives, chacune vraie de la position reelle de sa zone.
   **Et le raisonnement, pas seulement le resultat** : cette forme passe au CP parce que **l'enfant
   n'a jamais besoin de la legende pour jouer**. La question est *"Montre le tableau."*, la reponse
   est sur le plan, et le libelle positionnel est le nom du bouton, pas l'enonce. La numerotation
   n'ajoute rien a tenir en tete. **Si la reponse avait dependu de la lecture de la legende,
   j'aurais refuse cette forme au CP** : un aller-retour entre un dessin et une liste de six lignes
   est trop cher a six ans.

### Les reserves, quatre au total

| Code | Objet | Etat |
|---|---|---|
| `PCL-R1` | Les six indices disent a quoi sert l'objet plutot que d'aider a le situer sur ce plan-ci | **Ouverte depuis le 2026-09-11, inchangee.** Etait un critere d'acceptation de la story |
| `PCL-R2` | L'indice du tableau est inexact, et l'adulte de l'ecole est *"la maitresse"* deux fois sur six | **Ouverte depuis le 2026-09-11, inchangee.** Etait un critere d'acceptation de la story |
| `PCL-R3` | Le chiffre est centre dans son cadre et croise un trait sur trois zones | **Neuve, 2026-09-21** |
| `PCL-R4` | Le vocabulaire positionnel s'est detache de la position qu'il nomme | **Neuve, 2026-09-21** |

**`PCL-R1` et `PCL-R2` sont le seul critere d'acceptation de la story qui n'est pas tenu.** Les six
indices du paquet sont inchanges, caractere pour caractere. **Je ne les transforme pas en motif de
refus, deliberement** : je les ai moi-meme classees non bloquantes le 2026-09-11, par ecrit, et je
ne reclasse pas apres coup une reserve que j'ai gradee, le sprint ou l'equipe paie le motif que
j'avais nomme comme etant le refus. Une barre qu'on releve apres coup fait de ma porte un caprice.

---

## 2. Deux defauts transverses trouves en jouant - neufs, dates du 2026-09-21

Ils sont ecrits en detail dans la fiche du plan de la classe (bloc nº 2, section 5), parce que c'est
la que je les ai vus. **Ils ne sont pas des regressions de s36** : `git log -S` sur la feuille
maison les date de s34. **Ils touchent la mecanique de designation, donc aussi
`cp-designation-frise-de-la-journee`**, deja `couvert-valide` (`QLM-CP-14`).

> **`PCL-R5`** - **une zone deja essayee ne se distingue en rien d'une zone neuve.** La classe
> `deja-essayee` est bien posee, et `background-color`, `border-color`, `color`,
> `text-decoration` et `opacity` sont identiques a ceux d'un bouton non essaye. La feuille maison
> ne declare `.zone.deja-essayee` qu'a `:hover`, `:active` et `:focus-visible` ; **aucune regle au
> repos**, la ou `.proposition.deja-essayee` en a une sur l'autre mecanique. La marque de `M9`
> n'existe pas sur une zone de designation.

> **`PCL-R6`** - **apres la troisieme erreur, la bonne zone n'est designee par rien a l'ecran.**
> Erreurs 1 et 2 : *"Ce n'est pas ca. Essaie encore !"*. Erreur 3 : *"Ce n'est pas ca."*, la manche
> se termine, la bonne zone recoit le focus du document - mais **`:focus-visible` est faux apres un
> appui de pointeur** : `outline` vaut `none 0px`, `box-shadow` vaut `none`, le fond et la bordure
> ne bougent pas. L'enfant voit l'indice et **six cases rigoureusement identiques**.

**Je ne rouvre pas `QLM-CP-14`.** Je ne les avais pas vus quand je l'ai accordee ; je le dis, et je
ne me sers pas d'aujourd'hui pour durcir hier. Ils partent au backlog comme des reserves neuves, a
`camille-po`, avec la forme a `ines-design` et la cause a `theo-archi`.

**Ce que cela ajoute au compte que ce depot tient deja** : ce sont **deux defauts visibles par un
enfant de plus** qui ont traverse une CI entierement verte. Le motif n'a pas change - aucune de ces
deux proprietes n'a de garde-fou, et tant qu'elle n'en a pas, c'est moi le garde-fou, et je ne le
suis qu'en jouant.

---

## 3. Le referentiel de mathematiques CE1 - relu, il tient, et ce n'est pas un Done pedagogique

`squad/programme/mathematiques/competences-ce1.md`, 17 lignes `MAT-CE1-01` a `MAT-CE1-17`, ecrit par
moi ce sprint sur le routage de `DEC-0089`.

**Ce document ne recoit pas de Done pedagogique, et il ne faut pas faire semblant du contraire** :
aucun paquet de contenu CE1 n'existe, rien n'est jouable, **il n'y a rien a jouer**. Un Done
pedagogique se prononce apres avoir joue un increment ; il n'y a pas d'increment. Je le mentionne
plutot que de le laisser sans trace, parce qu'un document qui traverse un sprint sans mention finit
par etre cru valide.

**Relu une derniere fois le 2026-09-21, il tient ce que je voulais y mettre.** Ce que j'ai
reverifie :

- **La progression contre le CP est reelle, ligne a ligne**, et c'est la seule chose qui rend un
  referentiel de deuxieme annee de cycle utile : 100 -> 1000 (`MAT-CP-01` -> `MAT-CE1-01`), comparer
  -> comparer, ranger, encadrer et placer sur une ligne graduee, calcul mental dans 10 puis 20 ->
  dans 100, comparer des longueurs sans instrument -> mesurer a la regle graduee en cm et en m,
  heure pleine -> demi-heures et quarts d'heure, reconnaitre des figures -> les decrire et les
  tracer. **Aucune ligne n'est un doublon deguise d'une ligne de CP.**
- **Les trois entrees veritablement neuves du CE1 y sont** : le sens de la multiplication
  (`MAT-CE1-06`), les solides usuels (`MAT-CE1-16`), l'axe de symetrie (`MAT-CE1-17`).
- **Les trois domaines du cycle 2 sont couverts** et aucun intitule n'est un titre de jeu
  (`ADR-0078`).
- **La condition de lisibilite n'a pas ete glissee dans une competence** : `LON-CE1-1` et
  `LON-CE1-2` vivent dans `ARBITRAGE-s36-longueur-d-enonce-au-ce1.md`, ou elles ont leur place.
  Une borne de longueur d'enonce n'est pas une competence, et melanger les deux est exactement ce
  que la dette 2 de `CLAUDE.md` decrit une couche plus haut.

**Une chose que j'ajouterais, et que je date plutot que de la corriger en silence** : `MAT-CE1-08`
enumere *"addition, soustraction et multiplication"*. **Les problemes de partage et de groupement -
le sens de la division - manquent**, et ils appartiennent au CE1. Ce n'est pas une erreur dans ce
qui est ecrit, c'est une ligne absente ; le document se declare lui-meme *"non exhaustif"*, et il
prendrait `MAT-CE1-18` sans renumeroter quoi que ce soit. **Je ne l'ajoute pas dans le meme geste
que mon verdict de sprint** - une ligne de referentiel s'ecrit posement, pas en marge d'un audit -
et je la route a `camille-po` pour qu'elle decide quand.

**Ce que ce paragraphe ne dit surtout pas** : que le referentiel serait valide. Il est **ecrit, relu
et coherent**. Il deviendra opposable ligne par ligne le jour ou un paquet servira une de ses
lignes et ou j'aurai joue cet increment.

---

## 4. Ce qui reste dans ma seule garde, apres s36

Je le nomme parce que sinon l'equipe croira couvert ce qui ne l'est pas.

| Propriete | Garde executable ? |
|---|---|
| **La justesse d'une declaration `hors-de-la-boite`** | **aucune**, et `ADR-0094` le nomme lui-meme : un paquet peut la declarer sans en avoir besoin, ou l'omettre avec des cadres trop etroits, et la CI reste verte |
| **`ZON-15`, ses deux moities** | **aucune** (`ADR-0092` decision 2). A l'oeil, paquet par paquet |
| **Que la correspondance numero -> libelle soit lisible a six ans** | **aucune** |
| **`ZON-16`, `ZON-9`, `ZON-12`, la passe image effacee** | **aucune** |
| **L'exactitude d'un indice et le fait qu'il serve la competence visee** | **aucune** |
| **Que l'enfant voie ce qu'il a essaye et ce qu'il a rate** (`PCL-R5`, `PCL-R6`) | **aucune** |
| **Toute competence de `competences-ce1.md`** | **aucune**, et aucun paquet ne la sert encore |

---

## 5. Recapitulatif pour la review

| Objet | Verdict | Mouvement de matrice |
|---|---|---|
| `S36-QLM11-PLANCLASSE` / `cp-plan-de-la-classe` | **VALIDE SOUS RESERVE** (2026-09-21) | **`QLM-CP-11` : `non couvert` -> `couvert-valide`**, a ecrire par `camille-po` |
| `competences-ce1.md` (17 lignes) | **Pas de Done pedagogique - rien n'est jouable.** Relu, coherent, il tient | aucun |
| `PCL-R1`, `PCL-R2` | Ouvertes, inchangees depuis le 2026-09-11, **second sprint** | - |
| `PCL-R3`, `PCL-R4` | Neuves, 2026-09-21, non bloquantes | - |
| `PCL-R5`, `PCL-R6` | Neuves, 2026-09-21, **transverses aux deux paquets de designation** | `QLM-CP-14` **non rouverte** |
| `MAT-CE1-18` (sens de la division) | Ligne absente a ajouter, routee a `camille-po` | - |
