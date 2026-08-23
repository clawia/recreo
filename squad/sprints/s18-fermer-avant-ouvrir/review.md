# Review du sprint 18 - fermer avant ouvrir

- **Horodatage de debut** : 2026-08-22 21:35 +0200 (`planning.md`)
- **Horodatage de fin** : 2026-08-23 20:26 +0200 (dernier commit de fusion)
- **Duree effective** : 22 h 51 min
- **Increment audite** : `round-two`, tete `9741dfe`.

## Fusion des lots du sprint - moi, casquette Scrum Master

**Deux lots restaient a fusionner a l'ouverture de cette cloture**, tous deux forkes proprement de
la tete du moment (`10e0e0f`), documentaires, sans recouvrement de fichier entre eux.

| Lot | Branche | Tete | Lignee verifiee | Fichiers touches | Conflit |
|---|---|---|---|---|---|
| `camille-po` - cloture | `s18-camille-po-cloture` | `9efbf18` | fork = `10e0e0f`, exacte | `DEC-0041`, `DEC-index.md`, `MATRICE-couverture.md` | aucun |
| `nadia-prof` - Done de `S17-C` | `s18-nadia-s17c` | `02b0ece` | fork = `10e0e0f`, exacte | `FICHE-barre-entree-niveau.md`, un releve | aucun |

Commits de fusion : `4b3033b` (camille-po), `9741dfe` (nadia-prof, tete actuelle).

**Rappel de la lignee complete du sprint**, pour que la tracabilite ne se coupe pas a cette seule
cloture (toutes verifiees a leur propre fusion, par `git merge-base`, jamais supposees) :
`8bfcb44` (perimetre camille-po) -> `b73deec` (avis theo-archi, `ADR-0055`) -> `eec9575`
(corrections post-gel camille-po) -> `52d6d15` (`s18-nadia-remesure`, embarquant `s18-lea`,
`s18-theo` avec `ADR-0056`/`ADR-0057`, `s18-marc`, `s18-nadia-bpm1`, `s18-nadia-syn11`) ->
`03d0c51` (seuil de largeur revise a 744 px, camille-po) -> `10e0e0f` (deux propositions de forme,
ines-design) -> `4b3033b` -> `9741dfe`.

**Aucun push, aucun worktree supprime dans ce geste.**

## Porte technique - rejouee sur l'arbre fusionne final, jamais recopiee

```
bash outils/mvn-jdk21.sh -B clean verify
```

**BUILD SUCCESS**, six modules en `SUCCESS`.

**Chiffre de tests, par mes deux methodes reellement distinctes, sur l'arbre fusionne (`9741dfe`),
apres un `clean`** :

| Methode | Resultat |
|---|---:|
| (1) Somme des blocs `[INFO] Results:` du reacteur (48+13+321+144+89+6) | **621** |
| (2) Somme de l'attribut `tests="..."` des 98 rapports XML (`surefire`/`failsafe`) | **621** |
| (3) Somme des lignes `Tests run:` des 97 rapports texte (`.txt`) | **614** |

Les methodes (1) et (2) concordent, a la meme granularite : c'est le chiffre de reference, celui
que la coordination attendait (621, verifie et non recopie). La methode (3) sous-compte de **7**,
ecart intact, documente depuis `ADR-0054` (des classes melangent une regle executee par le moteur
ArchUnit et une methode executee par Jupiter ; le rapport texte n'en garde qu'une execution la ou
le rapport XML garde les deux). Ce chiffre est passe de 604 (ouverture de s18) a 621 par les lots
de code du sprint - `ADR-0055` (+8), `S17-C`/`ADR-0057` et le correctif `SYN-11`/`ADR-0056`
(sur `adapter-web`).

**Regles ArchUnit, par la methode etablie depuis s16** : **29** - 28 champs `@ArchTest` (`git grep`,
onze fichiers reels, aucun dans le module de contre-exemple) plus une regle declaree par un champ
`ArchRule` statique verifie hors du mecanisme `@ArchTest` (`ScanDeComposantsTest`). **Inchange sur
tout le sprint** : ni `ADR-0055`, ni `ADR-0056`, ni `ADR-0057` n'ajoutent de regle ArchUnit au sens
strict - toutes trois jugent le document assemble ou la feuille servie via `jsoup`, pas via le
moteur ArchUnit, quel que soit le mot qui les designe dans leur `ADR`.

**Trois garde-fous de depot**, rejoues sur l'arbre final : `verifier-prefixes.sh` (tous les
commits de l'historique fusionne portent un prefixe d'agent connu), `verifier-chemins.sh` (aucun
chemin de poste de travail ni nom de compte), les quatre juges purs de `outils/*-test.sh`. Les
trois verts.

**Ce que je nomme plutot que de le fabriquer** : il n'existe pas, pour s18, de fichier de
confirmation ArchUnit dedie de `theo-archi` (le patron `confirmation-archunit.md` de s14), ni de
rapport qualite dedie de `yanis-qa`, ni de fichier de relecture dedie de `relecteur-pedagogique`.
Un commit `[YAN]` existe dans l'historique du depot (`8b2f9e1`, `TexteATrouWebTest`) mais **n'est
pas un ancetre de la tete auditee** (`git merge-base --is-ancestor` le confirme) : il ne fait pas
partie de cet increment et je ne le compte pas. La confirmation ArchUnit de ce sprint vit dans les
`ADR` eux-memes (leurs tableaux de morsure, section suivante) et dans mon propre comptage
ci-dessus, pas dans un fichier dedie qui n'a pas ete ecrit.

## Chaque porte de sortie du planning gele, verifiee physiquement (amendement s13)

| Porte de sortie (planning.md) | Verifiee sur | Etat |
|---|---|---|
| `S17-4` : entree de catalogue + `mvn verify` vert | `catalogue.yaml`, six lignes | **franchie** |
| `S17-4` : `BPM-1` produite par `nadia-prof`, aux trois etats d'echec, sur les 24 items | `FICHE-bandit-manchot-homophones-cm1.md`, verdict du 2026-08-23, 23 parties, **624 lectures** | **franchie** - TENUE |
| `S17-4` : Done pedagogique complet, dans le meme geste que `BPM-1` | meme fiche, remesure du meme jour sur l'increment livre, 6 parties, 246 lectures | **franchie** - VALIDE SOUS RESERVE, aucune reserve bloquante |
| `S17-4` : la ligne de matrice passe `couvert-valide` si le Done est sans reserve bloquante | `squad/programme/MATRICE-couverture.md`, ligne 468, lue directement : `couvert-valide` | **franchie physiquement**, pas seulement promise |
| `S17-3` : mecanisme de mise en page arbitre par `ines-design` puis `nadia-prof` avant tout correctif | `FICHE-bandit-manchot-synonymes-cm1.md`, arbitrage du 2026-08-23, variante retenue (`.zone-jeu` et en-tete, 720 px) | **franchie** |
| `S17-3` : `mvn verify` vert sur le correctif, tables `ADR-0054`/`ADR-0055` mises a jour | meme fiche, section 1 ; `ADR-0056` corrige au meme geste une regression que ce correctif aurait sinon laisse passer | **franchie** |
| `S17-3` : remesure de `nadia-prof` confirmant la fermeture de `SYN-11` | meme fiche, remesure du 2026-08-23 sur le servi, 8 parties, 328 lectures | **franchie** - `SYN-11` FERMEE |
| `S17-C` : trois attributs de gabarit + reduction du script + `ADR` de `theo-archi` dans le meme lot | `ADR-0057`, deux clauses avec leur morsure, meme commit que le correctif de `marc-dev` | **franchie** |
| `S17-C` : verification de `nadia-prof` sur l'ordre de tabulation reel au clavier | `FICHE-barre-entree-niveau.md`, verdict du 2026-08-23, mesure explicite dans les deux sens (quitter le bouton nomme, le retrouver par tabulation seule) | **franchie** - VALIDE, sans reserve |

**Aucune porte n'est creditee sur une promesse.** Deux d'entre elles (`BPM-1`/Done complet,
`SYN-11`) ont d'abord ete refusees le matin meme sur l'increment injecte, puis franchies l'apres-
midi sur l'increment reellement **servi** - je le porte parce que c'est exactement la distinction
que l'amendement de s13 exige et que `nadia-prof` a elle-meme appliquee sans qu'on le lui demande.

## Les trois verdicts pedagogiques

- **`S17-4` (homophones)** : **VALIDE SOUS RESERVE, aucune reserve bloquante.** `BPM-1` tenue
  (marge minimale +139,81 px), `BPM-5` fermee (0 item sur 24 coupe, contre 8 le matin). Reserves
  non bloquantes versees : `BPM-6` (clause 1 fermee par `S17-C`, clause 2 non tenue - touche morte
  a la revelation), `BPM-7` (marge de 9,81 px sur deux items, sous le seuil de bruit de police,
  declencheur de remesure explicite), `BPM-8` (le correctif ne mord pas sous 720 px de viewport).
- **`S17-3` (synonymes)** : **VALIDE SOUS RESERVE, aucune reserve bloquante**, mais **la ligne de
  matrice ne bouge pas** : l'arbitrage de `nadia-prof` en s17 l'interdit tant que `SYN-4` (les
  antonymes) manque, et elle le confirme elle-meme dans ce verdict. Reserves non bloquantes :
  `SYN-12` (3 items sur 16 perdent la fin de leur indice), `SYN-16` (le correctif ne mord pas sous
  720 px), `SYN-4` (nomme comme la seule cause qui bloque la matrice).
- **`S17-C`** : **VALIDE, sans reserve.** "La seule mesure du sprint ou je ne trouve rien a
  reprocher au premier passage, et je le dis sans le nuancer." Deux conditions neuves et non
  bloquantes en decoulent sur la mecanique partagee : `M22` (l'ecran `erreur.html` n'a pas de cible
  de focus declaree ; audit degrade, `nadia-prof` refuse de fabriquer un appel HTTP pour le jouer)
  et `M23` (rien a l'ecran n'annonce que le troisieme echec revele la reponse - fait deja nomme en
  s17, pose ici en condition pour qu'il cesse d'etre repete sans suite).

## Mouvement de couverture - verifie physiquement sur la matrice, pas recopie du planning

| | Avant s18 | Apres s18 |
|---|---:|---:|
| `couvert-valide` | 9 / 46 | **10 / 46** |
| Taux | 19,6 % | **21,7 %** |

**Une seule ligne bouge** : francais CM1, "Distinguer et utiliser correctement les principaux
homophones grammaticaux", `en cours` -> `couvert-valide` (`DEC-0024`, s10 ; condition de retour
remplie dans son entier pour la premiere fois). **`camille-po` refuse d'en faire un retournement
d'ensemble**, et je porte les deux moities de son constat : la serie que `DEC-0037` nommait (quatre
sprints consecutifs, s14 a s17, sans mouvement reel de couverture) **s'arrete a s18** ; et "le
chantier francais CM1 n'est qu'a moitie sorti de la stagnation qu'il visait a rompre" - `S17-3`
obtient le meme type de verdict pedagogique mais reste `en cours`, faute d'antonymes.

## Quatre decisions d'architecture et de produit, neuves ce sprint

| Decision | Statut | Ce qu'elle porte |
|---|---|---|
| `ADR-0055` | Accepte | La largeur demandee par une rangee est epinglee - complete `ADR-0054`, qui n'epinglait que la largeur offerte. 8 tests, morsure sur materiau reel. |
| `ADR-0056` | Accepte | Un seuil de largeur maison est juge au meme titre sous une regle-`@` qu'au niveau superieur ; un ancrage de mutation doit designer exactement un endroit. Trouve en essayant d'appliquer la variante d'`ines-design`, pas en la lisant : deux defauts, tous deux d'`ADR-0054`/`ADR-0055` elles-memes. |
| `ADR-0057` | Accepte | La focalisabilite est declaree par le gabarit ; un script qui ne la pose plus ne suffit pas a la garder. Deux clauses, chacune trouvee fautive une fois - une variante textuelle qui restaure le defaut, un retrait des trois attributs qui defait le mecanisme entier - et chacune corrigee avec sa preuve de morsure. |
| `DEC-0007`, note du 2026-08-23 (troisieme) | Amendement | Le seuil technique de largeur telephone (S3-8, 375 px) est revise a 744 px, jamais supprime : sa premisse (la cible telephone) a disparu avec `DEC-0040`, mais il gardait un risque de debordement horizontal reel qui survit au changement de cible. |

**Preuve de morsure decrite pour chacun des trois `ADR`**, condition non negociable pour qu'un
garde-fou cite comme fermant une dette compte comme complet : `ADR-0055` (8 tests, sept morsures
sur materiau reel commis) ; `ADR-0056` (le jeu fautif `largeur-excessive-sous-media`, identique a
`largeur-excessive` a la seule difference du nichage sous `@media`, vert **avant** ce lot, rouge
apres) ; `ADR-0057` (les deux morsures de la section "Trois trouvailles" ci-dessous, chacune verte
sur l'arbre fusionne avant sa propre regle).

## Trois trouvailles de garde-fou - la meme famille, versees avec leur preuve de morsure

Toutes trois trouvees **en essayant reellement de defaire un correctif ou une regle**, jamais en
relisant du texte :

1. **Un seuil qu'il suffisait d'indenter pour l'eviter.** La clause interdisant une largeur maison
   au-dela de la cible (S3-8, `ViewportEtLargeurTelephoneTest`) passait verte des qu'une
   declaration illegale etait nichee sous une regle `@media` - son balayage ne descendait pas dans
   ce contexte. Morsure : `largeur-excessive-sous-media`, meme declaration que le cas deja garde,
   seul le nichage change, **vert avant** `ADR-0056`, rouge apres.
2. **Une clause que l'orthographe la plus idiomatique de la faute contournait.** La clause
   textuelle proposee pour `ADR-0057`, sensible a la casse, laissait passer
   `pointDeFocus.tabIndex = -1` - la forme la plus naturelle du geste interdit - qui restaure
   integralement le defaut que `nadia-prof` avait mesure. Morsure : 618 tests verts sur cette
   mutation avant correction de la clause.
3. **La moitie du mecanisme de `S17-C` que rien ne gardait.** Retirer les trois attributs
   `tabindex="-1"` des gabarits - c'est-a-dire defaire le mecanisme entier, pas seulement le
   script - laissait la suite verte : 618 tests, aucun echec. `ADR-0057` corrige les deux moities
   dans le meme commit.

**Candidat fort pour `retro.md`** : les trois ont ete trouvees par le meme geste (appliquer
reellement une variante ou un retrait candidat et mesurer), jamais par une relecture de code ou de
test. Detaille dans `retro.md`.

## Un ecart de process, signale par son auteure et verifie par moi

`camille-po` a d'abord edite `squad/programme/MATRICE-couverture.md` **dans le checkout principal**
au lieu d'ouvrir un worktree dedie pour son lot de cloture - **detecte par elle-meme avant tout
commit**, et corrige en rouvrant un worktree propre. **Verifie de mon cote** : le checkout
principal est propre (`git status --short`, aucune modification non commitee a aucun moment de
cette cloture) et la matrice y est intacte jusqu'a la fusion legitime du lot. **Rien n'a ete
perdu.** C'est neanmoins la **meme famille d'incident que celle nommee par l'amendement de s06**
("tout agent qui ecrit dans le depot ouvre un worktree dedie avant sa premiere ecriture, sans
exception") : un agent different, le meme geste, deux sprints plus tard. Je l'inscris ici plutot
que de le laisser filer parce qu'il n'a rien casse - c'est exactement le raisonnement que
l'amendement de s13 interdit de tenir sur une porte de sortie, et je ne le tiens pas non plus sur
un incident de process.

## Tableau des obstacles du sprint

| Description | Categorie | Destinataire | Etat |
|---|---|---|---|
| Deux defauts d'un meme critere de `S17-4` (reference perimee, outil hors sujet) | produit | `camille-po` | **clos** - critere corrige par note datee dans le backlog |
| `SYN-13` ecrite comme critere de lot alors qu'elle est conditionnee au mecanisme | produit | `camille-po` | **clos** - requalifiee en observation |
| Mecanisme de mise en page pour `SYN-11` | design puis pedagogie | `ines-design` puis `nadia-prof` | **clos** - variante retenue (`.zone-jeu` + en-tete, 720 px), `SYN-11` fermee |
| Charge de mesure de `nadia-prof` sur trois campagnes (`BPM-1`, `SYN-11`, `S17-C`) | capacite | `sofia-sm` | **clos** - sequence tenue, les trois instruites separement, aucune creditee sans etre jouee |
| Migration `ADR-0049` hors perimetre, candidate d'ajout tardif | capacite | `sofia-sm` | **reste decline** - aucun dev ne s'est libere pendant les campagnes de mesure ; candidate du prochain sprint |
| `SYN-16` / `BPM-8` - le correctif ne mord pas entre 576 et 719 px de viewport | produit, pedagogie | `camille-po` (tranche : bande = appareil reel) puis `theo-archi` (mecanisme) | **ouvert, non bloquant** - question de mecanisme non instruite |
| Hypothese de fenetre maximisee non nommee dans l'inventaire d'appareils | design | `ines-design` | **ouvert** - a nommer dans l'inventaire, pas a remesurer ce sprint |
| `SYN-12` - 3 items sur 16 perdent la fin de leur indice | pedagogie | `nadia-prof` seule | **ouvert, non bloquant** |
| `BPM-6` clause 2 - touche morte a la revelation ne rend pas un ecran identique | pedagogie | `nadia-prof` seule | **ouvert, non bloquant** |
| `BPM-7` - marge de 9,81 px sous le seuil de bruit de police | pedagogie | `nadia-prof` seule, declencheur de remesure | **ouvert, non bloquant** |
| `M22` - l'ecran `erreur.html` n'a pas de cible de focus declaree | technique, audit degrade | non affecte | **ouvert, non bloquant** |
| `M23` - rien n'annonce que le troisieme echec revele la reponse | produit | `camille-po` (story ou refus explicite) | **ouvert, non bloquant** |
| Contradiction 576-719 px : corriger le pli effondre les marges, les preserver laisse le verdict sous le pli | technique | `theo-archi` | **route, non traite** |
| Ecart de process - matrice editee dans le checkout principal, recurrence de l'incident de s06 | methode | a porter en retro | **clos sans perte**, inscrit ci-dessus |
| Trois trouvailles de garde-fou trouvees en essayant de defaire | technique | `theo-archi` | **clos**, corrigees avec preuve de morsure (`ADR-0056`, `ADR-0057`) |
