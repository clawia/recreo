# Rapport qualite - sprint 7 (machine a sous)

- **Auteur** : yanis-qa
- **Date** : 2026-08-10
- **Worktree** : dedie, provisionne depuis `round-two` sur une branche neuve
  (`outils/verifier-lignee.sh` : "le worktree sur la branche 's07-yanis-qa' descend de la lignee de
  round-two (ancetre commun trouve dans son historique)"), hors de l'arbre du depot principal.
- **Commit audite** : `d53181b52d05ca97a78c2adfd5e71cba99f81a51` (tete de `round-two`), qui integre
  les deux lots du sprint : `65c9b45` (fusion marc-dev) puis `0179479`/`d53181b` (fusion lea-dev).
- **Constat de JDK** (`outils/verifier-jdk.sh`) : `java du PATH : 1.8.0_202 | JDK de compilation :
  21.0.10 | JAVA_HOME : vide | toolchains lu par Maven : non (fichier present sous un nom non lu)`.
  `JAVA_HOME` a ete positionne sur ce JDK 21 pour la duree de chaque commande citee ci-dessous,
  jamais ecrit dans un fichier commite.

Ce rapport ne recopie aucune mesure des autres agents sans l'avoir soit rejouee, soit refutee. Les
sections qui suivent disent laquelle des deux pour chaque chiffre cite.

## 1. Porte technique - `mvn -B clean verify`

Commande executee deux fois dans ce worktree (une fois avant, une fois apres la mise a l'epreuve du
garde-fou de la section 3, pour verifier que l'arbre revient a l'etat vert qu'il avait avant) :

```
mvn -B clean verify
```

**Resultat les deux fois : BUILD SUCCESS**, six modules en SUCCESS. Aucune difference entre les
deux executions.

**Compte de tests, methode** : somme de l'attribut `Tests run` du bloc `Results` de chaque module
dans la sortie Maven, **apres un `clean`**. `Skipped: 0` releve dans chaque bloc de module.

| Module | Tests run | Failures | Errors | Skipped |
|---|---|---|---|---|
| domain | 43 | 0 | 0 | 0 |
| application | 7 | 0 | 0 | 0 |
| adapter-web | 149 | 0 | 0 | 0 |
| adapter-content | 35 | 0 | 0 | 0 |
| bootstrap (surefire) | 73 | 0 | 0 | 0 |
| bootstrap (failsafe) | 3 | 0 | 0 | 0 |
| **total** | **310** | **0** | **0** | **0** |

Ce total (310) est conforme au chiffre annonce par `sofia-sm` avant ma saisine, mesure
independamment et non recopie.

Le compte de regles ArchUnit (28) est confirme par `theo-archi` dans `confirmation-archunit.md` ;
je ne le remesure pas, ce n'est pas mon garde-fou et je n'edite pas ce fichier.

**Aucun test instable constate.** La suite a tourne trois fois au total dans ce worktree pendant
cette review (deux `mvn clean verify` complets, plus une execution ciblee de
`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` a la section 3) : memes chiffres a chaque fois.

## 2. Traçabilite critere -> test

Les quatre stories du chantier mecanique (S7-1 a S7-4) ne portent aucune competence pedagogique
(`DEC-0015`) ; leurs criteres d'acceptation sont les regles executables d'`ADR-0026` et `ADR-0027`,
et chacune porte un test nomme et un commentaire de tracabilite explicite, verifie par lecture des
fichiers :

- `LAnimationRetardeMaisNeDetermineRienTest` (quatre clauses `ADR-0026`, trois clauses ajoutees par
  `ADR-0027`) : le nom et le Javadoc renvoient a la section de l'ADR qu'il garde.
- `LEcranDeJeuDeclareCommentIlEstArriveTest` : renvoie explicitement au defaut route par
  `nadia-prof` et ferme par `ADR-0027`.
- `TailleDesTextesEtBoutonsTest.lEcartEntreDeuxBoutonsDeReponseEstAuMoinsUnRemSurLesDeuxAxes` et
  `leReplniEnDeuxRangeesEstScopeALaQuatriemePropositionSeulement` : renvoient a `ADR-0026` section 4
  et a la proposition d'`ines-design` section 5.
- `FrontieresDuJavaScriptTest` (vocabulaire `proposition`/`colonne`) : renvoie a `ADR-0026` regle 2.

La story de correction des cinq indices porte un seul garde-fou neuf,
`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest`, dont le Javadoc cite mot pour mot le motif du
refus de `nadia-prof` (fiche, section 2, manche 7 / manche 10) : tracabilite explicite, verifiee par
lecture.

**Aucun module sans test dans ce que la squad a livre ce sprint** : les deux lots touchent
`adapter-web` (tests etendus) et `adapter-content` (garde-fou neuf), tous deux deja couverts avant
ce sprint et enrichis dans le meme commit que le code qu'ils gardent - verifie sur les deux commits
de fusion (`65c9b45`, `d53181b`).

**Aucune assertion HTML par expression reguliere constatee dans les tests neufs** : `AssertionsWebTest`
reste vert et `LAnimationRetardeMaisNeDetermineRienTest` lit le CSS caractere par caractere avec une
pile de regles-`@`, pas par `java.util.regex` (verifie par lecture du fichier).

## 3. Garde-fou de marc-dev - preuve de morsure refaite moi-meme

`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` ferme le defaut le plus grave du sprint 6 (cinq
indices sur douze enoncant une regle de conjugaison fausse, dont trois dementies par un autre item
du meme paquet). C'est le garde-fou le plus important du sprint et sofia-sm demande explicitement
une preuve de morsure refaite, pas relue.

**Methode** : mutation reelle du fichier de production
`adapters/adapter-content/src/main/resources/contenu/cm1-conjugaison-present.yaml`, pas d'un jeu de
contre-exemple. J'ai remplace l'indice corrige de l'item 6 par son texte fautif d'origine :

- avant (production) : `"Avec 'tu', le verbe 'dessiner' se termine par '-es' au présent : c'est
  'dessines'."`
- mutation : `"Avec 'tu', le verbe se termine par '-es' au présent : c'est 'dessines'."`

Puis execution ciblee :

```
mvn -B -pl adapters/adapter-content -am test -Dtest=AucunIndiceNeContreditUnAutreItemDuMemePaquetTest
```

**Resultat, mutation en place : 1 echec sur 3 cas.**

```
aucunIndiceDeProductionNeContreditUnAutreItemDuMemePaquet -- FAILURE
java.lang.AssertionError:
[paquet cm1-conjugaison-present : l'indice de "Tu ___ un dessin." enonce une regle generale
(terminaison '-es') que la bonne reponse "finis" de l'item "Tu ___ ton exercice avant la
récréation." dement]
Expecting actual: "finis" to end with: "es"
```

C'est exactement la contradiction manche 7 / manche 10 que `nadia-prof` avait constatee en jouant
(fiche, section 2). Le garde-fou mord sur le vrai defaut, pas sur un succedane.

**Restauration** : le fichier a ete remis a son etat de production (verbe nomme), et
`git diff --stat` confirme un arbre propre. Reexecution du meme test cible : **3 cas, 0 echec**.
`mvn -B clean verify` final (section 1) confirme que l'arbre entier revient au vert qu'il avait
avant la mutation, avec les memes chiffres.

**Limite de cette preuve, dite franchement** : le garde-fou reconnait une seule forme syntaxique de
regle generale non temperee (`"le verbe se termine par '-X'"`, sans nommer ni temperer). Il ne peut
pas juger la verite d'une regle de grammaire deja tempere ou deja nommee mais fausse par ailleurs -
`nadia-prof` le dit elle-meme dans sa fiche ("cette regle de grammaire est-elle vraie n'est pas une
propriete qu'un test peut trancher"). C'est une limite assumee et documentee, pas une lacune cachee.

## 4. Mesures independantes a 360 x 640 - methode et resultats

Application reconstruite (`bootstrap/target/recreo.jar`, meme JDK 21) et lancee sur un port dedie.
Trois parties jouees par l'API HTTP reelle de l'application (session par cookies, aucune donnee
fabriquee a la main) : une requete `POST /parties/{jeuId}` par jeu du catalogue, puis des coups
delibrement faux (valeur sentinelle absente de toute liste de propositions reelles, donc toujours
inegale a la bonne reponse - le controleur ne valide que l'egalite, aucune appartenance) jusqu'a
l'apparition de l'indice apres trois echecs consecutifs. Chaque manche obtenue est assemblee dans la
coquille reelle (`GET /`, fragment insere dans `#jeu`, meme methode que celle documentee dans
`squad/conformite/FICHE-bandit-manchot-additions-cp.md`), capturee par Chrome headless dans un cadre
`iframe` de largeur et hauteur CSS fixees a 360 x 640 (le viewport de reference de `DEC-0016`),
fenetre parente hors du plancher de 500 px etabli en s06 (`outils/mesurer-rendu.sh`), profil isole.
Ce n'est pas `outils/mesurer-rendu.sh` tel quel (sa hauteur d'iframe est fixee a 1400 px, adaptee a
une mesure de largeur, pas a une mesure de pli) : c'est une variante jetable, non commitee, qui
reprend son enveloppe et son plancher de fenetre.

### 4.1 Largeurs de colonnes - confirmation independante des chiffres de lea-dev

Mesure par balayage de pixels non fond de page (`#f4f7fb`) a mi-hauteur de la rangee de boutons, sur
les captures ci-dessus (methode differente de celle de `nadia-prof` - qui balaie les aplats de
`--recreo-accent` - et de celle d'`ADR-0026` ; les trois methodes concordent) :

| Jeu | Colonnes | Largeurs mesurees | Ecart mesure | Hauteur mesuree |
|---|---|---|---|---|
| Calcul CP (3 propositions) | 3 | 101, 102, 101 px | 16, 16 px | 74 px |
| Le bon petit mot (2 propositions) | 2 | 160, 160 px | 16 px | 74 px |

**Confirme a l'unite pres** : 101/102/101 px et 16 px d'ecart pour trois colonnes (chiffres
d'`ADR-0026` et de la fiche de `nadia-prof`), 160 px pour deux colonnes. Aucun plancher approche par
le bas (cible tactile 44 px, hauteur 74 px mesuree contre 44 px exiges). Le plancher d'ecart (1rem =
16 px) est tenu a l'egalite exacte, sans marge, comme deja documente - je ne trouve pas de marge
cachee ni d'ecart qui s'en eloigne.

**Ce que je n'ai pas pu mesurer moi-meme** : le repli a quatre colonnes (deux rangees). Aucun paquet
du catalogue actuel n'a quatre propositions (`cm1-conjugaison-present` reste hors catalogue,
`DEC-0014`), donc aucune partie jouable par l'API reelle ne l'exerce. La regle CSS existe
(`.propositions:has(.proposition:nth-child(4))`, lue dans `recreo.css`) et correspond a ce
qu'annoncent `ADR-0026` et la fiche de `nadia-prof` (flex-wrap, `calc(50% - 0.5rem)`), et
`TailleDesTextesEtBoutonsTest.leReplniEnDeuxRangeesEstScopeALaQuatriemePropositionSeulement` verifie
que la regle existe et est bien scopee - mais ce test, par son propre commentaire, ne verifie **pas
que le rendu tienne**. Je ne peux ni confirmer ni refuter le rendu a quatre colonnes sur du contenu
reel : cette latitude reste, comme documentee, hors de portee d'un automate de ce depot et non
verifiable sur le catalogue livre.

### 4.2 DEC-0016 - "enonce, indice et propositions visibles sans defilement" : defaut persistant trouve, non requalifie

`DEC-0016` fixe le viewport de reference a 360 x 640 et exige que l'enonce, l'indice s'il est
affiche et les propositions restent visibles sans defilement. Son mecanisme de garde est hors
perimetre du sprint 7 (arbitrage de `sofia-sm`, planning.md) : **aucun automate ne la verifie**,
j'ai donc mesure moi-meme, sur les trois jeux du catalogue, avec et sans indice.

**Resultat, sans indice** : sur les trois jeux (items obtenus par tirage reel), enonce et
propositions tiennent confortablement au-dessus du pli, marge large (plus de 150 px de marge
verticale libre dans les trois cas observes).

**Resultat, avec indice** : Calcul CP et Le bon son tiennent au-dessus du pli avec une marge
confortable sur les items obtenus (`5 + 5` / `10, 11, 12` ; `NOMBRE` / `on, in, an`), confirmant que
le passage en colonnes libere bien de la hauteur par rapport a l'empilement precedent, comme
`nadia-prof` l'a mesure dans sa fiche.

**Sur "Le bon petit mot" (`bandit-manchot-homophones-cm1`, jeu du catalogue, Done pedagogique deja
accorde), le defaut que `nadia-prof` avait signale persiste sur l'increment livre**, sur au moins
deux des items qu'elle avait identifies :

- item "Après cette longue randonnée en montagne, le vieux chien ne ___ plus vraiment courir comme
  avant." (bonne reponse `peut`, distracteur `peu`) : a 360 x 640, avec l'indice affiche, l'enonce se
  replie sur cinq lignes et l'encadre de l'indice est deja coupe par le pli. **Les deux propositions
  de reponse n'apparaissent pas du tout dans le cadre visible** - identique au constat de
  `nadia-prof` sur ce meme item ("il n'en reste rien du tout").
- item "Après la récréation, tous les élèves ___ rangent devant la porte de la classe." (bonne
  reponse `se`, distracteur `ce`) : a 360 x 640, avec l'indice affiche, **les deux boutons de
  reponse sont coupes par le pli** (seul le haut des deux boutons, quelques pixels, reste dans le
  cadre visible), le mot de chaque proposition n'est pas lisible.

Sur ces deux items, le passage en colonnes n'a rien change au defaut, pour la raison que
`nadia-prof` a deja etablie : la cause est la longueur de l'enonce (qui se replie sur cinq a six
lignes), pas le nombre de propositions ni leur disposition. Une mise en page en colonnes ne libere
de la hauteur que sur la rangee de boutons elle-meme (74 px au lieu de deux fois 74 px plus 16 px
d'ecart pour ce jeu a deux propositions) ; elle ne touche pas a la hauteur que l'enonce et l'indice
occupent, qui est ce qui pousse les boutons sous le pli sur ces items precis.

**Je ne requalifie pas ce constat.** `DEC-0016` dit explicitement que son mecanisme de garde n'est
pas de la capacite de ce sprint, et le motif de `sofia-sm` (aucun garde-fou attendu ce sprint) est
donc tenu - ce n'est pas un defaut cache. Mais le defaut lui-meme, sur un jeu du catalogue dont le
Done pedagogique reste accorde, **n'est pas ferme par l'increment livre** : il est identique, sur les
memes items, a ce que `nadia-prof` avait mesure avant le sprint. Je le rapporte tel quel, sans le
minimiser en "reserve" ni le grossir en motif de refus - ce jugement pedagogique reste entierement
celui de `nadia-prof`, qui l'a deja explicitement declare non bloquant pour le Done deja accorde.

**Methode citee pour verification independante** : session HTTP reelle sur `bandit-manchot-homophones-cm1`,
coup delibrement faux jusqu'a l'indice (3 echecs consecutifs), capture 360 x 640 par Chrome
headless avec un profil isole, verifiee visuellement sur l'image. Reproduit deux fois (deux sessions
distinctes) pour le second item, meme resultat les deux fois.

## 5. Repli de mouvement (`prefers-reduced-motion`) - la forme ET le resultat

`ADR-0026` clause 2 garde la forme litterale du repli ; `ADR-0027` et `nadia-prof` disent tous deux
explicitement que la forme ne garantit pas le resultat (un ecran lisible et **jouable** a l'arret).
Je verifie donc le resultat, pas seulement la regle CSS.

**Methode** : capture de la meme page assemblee (Calcul CP, `data-arrivee="par-un-geste"` confirme
present dans le HTML servi), avec `--force-prefers-reduced-motion` et sans, a un budget de temps
virtuel de 300 ms - c'est-a-dire **pendant** la fenetre d'animation (0,6 s de duree, jusqu'a 0,24 s
de decalage cumulatif sur la quatrieme colonne).

- **Sans le flag, a 300 ms** : le voile sombre (noir a 20 %) est visible en cours de glissement sur
  les trois boutons, a des positions differentes selon leur delai d'animation - comportement
  normal en cours de mouvement. Le texte de chaque proposition (`10`, `11`, `12`) reste lisible sous
  le voile, coherent avec le contraste de 7,48:1 mesure par `nadia-prof`.
- **Avec le flag, au meme instant (300 ms)** : **aucun voile n'est visible sur aucun des trois
  boutons** ; les trois boutons sont dans leur etat de repos exact, identique a l'etat obtenu apres
  la fin naturelle de l'animation (capture a 4000 ms, avec ou sans le flag). Le texte est
  entierement lisible des la premiere image, sans transition perceptible a chercher.

**Conclusion : la forme litterale ET le resultat tiennent.** Un enfant avec la reduction de
mouvement activee voit un ecran immediatement immobile, entierement lisible, et le bouton reste
structurellement le meme `<button type="submit">` dans le meme `<form>` quel que soit l'etat
d'animation - la jouabilite ne depend d'aucune classe ni d'aucun etat visuel, seulement de la
presence du bouton dans le document, inchangee. Je ne trouve aucun ecart entre la forme gardee par
la CI et le resultat que `nadia-prof` reverifiera en jouant.

## 6. Retro s05 - un test ecrit en plus du correctif, ou seulement le correctif ?

**Lot mecanique (S7-1 a S7-4, lea-dev).** Trois defauts distincts, tous fermes par un test ecrit
dans le meme commit que le correctif, avec preuve de morsure documentee par mutation reelle
(verifiee par lecture des ADR, pas rejouee par moi pour celles-ci - voir limite ci-dessous) :

- vocabulaire de tirage cote client (`FrontieresDuJavaScriptTest` amende) : trouve a la lecture par
  `theo-archi` avant tout code, test ecrit avant que le defaut n'existe en production - c'est la
  forme la plus forte de la lecon de s05.
- ecart lateral entre propositions non garde (`TailleDesTextesEtBoutonsTest` amende) : le defaut de
  fond ("le commentaire disait la limite a voix haute") a ete trouve en ecrivant l'amendement, pas
  en jouant ; test et correctif dans le meme commit.
- reprise silencieuse animant les colonnes sans geste (`LEcranDeJeuDeclareCommentIlEstArriveTest`,
  `ADR-0027`) : trouve par `nadia-prof` **en lisant le gabarit**, pas en jouant - largeur d'ecran
  sans objet ici, c'est un defaut de logique serveur, pas de rendu. Test ecrit dans le meme commit
  que le correctif, avec trois mutations reelles documentees (dont une relecture adverse par
  inversion de polarite, qui prouve que les deux clauses sont necessaires l'une a l'autre).

**Largeur ou un defaut de rendu a ete trouve vs verifiee** : le seul defaut de rendu de ce lot
(largeurs de colonnes a 360 px) a ete trouve et verifie a la meme largeur, 360 px, puis confirme
tenir aussi a 375 px (la cible de `DEC-0007`) - pas de largeur cachee entre la decouverte et la
verification.

**Limite que je nomme** : je n'ai pas refait moi-meme les preuves de morsure d'`ADR-0026` et
`ADR-0027` (les mutations sur `FrontieresDuJavaScriptTest`, sur l'ecart lateral, sur le marqueur de
geste) - je les ai lues et elles sont documentees avec assez de detail pour etre rejouees (jeux
fautifs nommes, messages d'erreur cites). J'ai concentre ma propre remorsure sur le garde-fou que
`sofia-sm` designe explicitement comme le plus important du sprint (section 3) et sur les deux
proprietes sans aucun garde-fou du tout (sections 4 et 5).

**Lot indices (correction des cinq indices, marc-dev).** Un seul defaut, un seul test,
`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest`, qui n'existait pas avant le refus de
`nadia-prof` en s06 et a ete ecrit dans le meme commit que le correctif (`6913827`). Reponse a la
question de retro : **oui**, le test a ete ecrit en plus du correctif, pas seulement le correctif -
et je l'ai verifie moi-meme par une mutation independante de celle documentee dans le test
(l'objet neuf du test reproduit la paire manche 7 / manche 10 ; ma mutation a reintroduit le texte
fautif reel de l'ancien indice 6 directement dans le fichier de production, voir section 3). Sans
objet de largeur d'ecran : ce lot ne touche aucun rendu.

## 7. Ce qui reste hors de mon perimetre, nomme pour ne pas etre confondu avec le Done technique

- **Done pedagogique du chantier machine a sous (S7-1 a S7-4)** : non prononce a la date de ce
  rapport. La fiche `FICHE-bandit-manchot-additions-cp.md` ne porte, a ma lecture, qu'un arbitrage
  prealable au developpement (degrade, "le chantier n'est pas ecrit"), pas un verdict sur
  l'increment fusionne. Je ne le prononce pas et je ne l'anticipe pas.
- **Done pedagogique de la correction des cinq indices** : la fiche
  `FICHE-bandit-manchot-conjugaison-present-cm1.md` porte encore le refus du premier audit ("Statut
  global : REFUSE") et la ligne de `squad/programme/MATRICE-couverture.md` reste `en cours`, tous
  deux anterieurs a la fusion du correctif de `marc-dev`. Le fait que mon garde-fou technique morde
  correctement (section 3) ne prejuge en rien de ce que `nadia-prof` dira en rejouant l'increment
  fusionne - **je ne confonds pas les deux portes.**
- **Confirmation ArchUnit** : `theo-archi`, dans `confirmation-archunit.md`. Je n'y touche pas.

## Fichiers cites dans ce rapport

- `adapters/adapter-content/src/main/resources/contenu/cm1-conjugaison-present.yaml` (lu et
  temporairement mute puis restaure, section 3)
- `adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunIndiceNeContreditUnAutreItemDuMemePaquetTest.java`
- `adapters/adapter-web/src/main/resources/templates/fragments/manche.html`
- `adapters/adapter-web/src/main/resources/static/css/recreo.css`
- `adapters/adapter-web/src/test/java/com/recreo/adapter/web/LAnimationRetardeMaisNeDetermineRienTest.java`
- `adapters/adapter-web/src/test/java/com/recreo/adapter/web/LEcranDeJeuDeclareCommentIlEstArriveTest.java`
- `adapters/adapter-web/src/test/java/com/recreo/adapter/web/TailleDesTextesEtBoutonsTest.java`
- `squad/decisions/adr/ADR-0026-une-animation-retarde-mais-ne-determine-rien-le-levier-n-est-pas-un-bouton-de-plus.md`
- `squad/decisions/adr/ADR-0027-un-ecran-de-jeu-declare-comment-il-est-arrive-le-mouvement-suit-le-geste.md`
- `squad/decisions/produit/DEC-0015-sprint-7-la-vraie-machine-a-sous-ferme-la-dette-1.md`
- `squad/decisions/produit/DEC-0016-hauteur-de-viewport-de-reference-et-contenu-au-dessus-du-pli.md`
- `squad/conformite/FICHE-bandit-manchot-additions-cp.md`
- `squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`
