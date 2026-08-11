# Rapport qualite - sprint 8 (cabinet de la machine)

- **Auteur** : yanis-qa
- **Date** : 2026-08-11
- **Worktree** : dedie, provisionne depuis `round-two` sur une branche neuve (`s08-yanis-qualite`),
  hors de l'arbre du depot principal. Lignee verifiee avant toute ecriture :
  `outils/verifier-lignee.sh` -> "le worktree sur la branche 's08-yanis-qualite' descend de la
  lignee de round-two (ancetre commun trouve dans son historique)".
- **Commit audite** : `bd9a8f0`, tete de `round-two` au moment de la saisine - confirme etre
  toujours la tete au moment d'ecrire ce rapport (`git rev-parse round-two`).
- **Constat de JDK** (`outils/verifier-jdk.sh`) : `java du PATH : 1.8.0_202 | JDK de compilation :
  21.0.10 | JAVA_HOME : vide | toolchains lu par Maven : non (fichier present sous un nom non
  lu)`. `JAVA_HOME` a ete positionne sur ce JDK 21 le temps de chaque commande citee ci-dessous,
  jamais ecrit dans un fichier commite. Anomalie d'environnement preexistante et hors perimetre,
  nommee comme demande : le fichier de toolchains du poste est enregistre sous un nom mal
  orthographie et n'est donc jamais lu par Maven.
- **Branche non fusionnee, hors de mon audit** : `s08-nadia-verdict-2` (`eb125e0`). Verifie par
  moi : `git diff bd9a8f0..eb125e0 --stat` ne touche qu'un seul fichier,
  `squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md` (326 insertions, 1
  suppression) - aucun code, aucun test, aucun chiffre technique n'y change. Le contenu de ce
  commit (quatrieme audit, Done pedagogique VALIDE SOUS RESERVE sur la conjugaison) est donc lu
  pour comprendre le contexte des points de la section 4, mais n'entre dans aucun chiffre ni
  verdict technique de ce rapport tant qu'il n'est pas fusionne.

Ce rapport ne recopie aucune mesure des autres agents sans l'avoir soit rejouee, soit refutee, y
compris les garde-fous cites comme fermant un refus ou une dette : je les ai casses puis remis en
etat, pas relus.

## 1. Porte technique - `mvn -B clean verify`

Commande executee deux fois dans ce worktree (une fois avant la mise a l'epreuve des garde-fous de
la section 3, une fois apres, pour verifier que l'arbre revient exactement a l'etat qu'il avait
avant) :

```
mvn -B clean verify
```

**Resultat les deux fois : BUILD SUCCESS**, six modules en SUCCESS.

**Compte de tests, deux methodes qui se recoupent.**

1. Somme de l'attribut `Tests run` du bloc `Results:` de chaque module dans la sortie Maven, apres
   un `clean`.
2. Somme de l'attribut `tests` de la balise racine `<testsuite>` de chaque rapport XML sous
   `*/target/surefire-reports/` et `*/target/failsafe-reports/` des cinq modules de code declares
   au pom agregateur - jamais un balayage depuis la racine du depot, qui verrait les huit
   worktrees d'agents qui coexistent a cote du mien.

| Module | Methode 1 (`Results:`) | Methode 2 (XML `tests=`) |
|---|---|---|
| domain | 43 | 43 |
| application | 7 | 7 |
| adapter-web | 175 | 175 |
| adapter-content | 39 | 39 |
| bootstrap (surefire) | 77 | 77 |
| bootstrap (failsafe) | 3 | 3 |
| **total** | **344** | **344** |

**Les deux methodes s'accordent exactement, et confirment le chiffre annonce par `sofia-sm`
(344), mesure independamment et non recopie.**

**Le piege signale par le planning ne s'est pas materialise a ce niveau d'agregation, et je dis
pourquoi plutot que de l'ignorer.** Le rapport texte **par classe** sous-compte reellement deux
classes par rapport a leur propre XML - verifie a la main :

- `com.recreo.archi.AbstractionsCiteesTest.txt` : "Tests run: 1", XML de la meme classe : `tests="5"`.
- `com.recreo.archi.LesReglesMordentTest.txt` : "Tests run: 25", XML de la meme classe : `tests="26"`.

Mais le bloc `Results:` **agrege par module**, en fin de build, n'est pas ce rapport par classe :
il recompte a partir des XML et rend 77 pour `bootstrap`, chiffre qui inclut deja les 5 et les 26
reels. J'ai verifie ce point precis en sommant moi-meme les `tests=` de tous les XML de
`bootstrap` (77) : identique au `Results:` du module. **Le piege mord uniquement celui qui
additionnerait les rapports `.txt` classe par classe** ; il ne mord ni le bloc `Results:` du
reacteur, ni une somme faite directement sur les XML comme je l'ai fait ici. Je le nomme pour que
personne d'autre ne tombe dans la version dangereuse de ce calcul.

**Regles ArchUnit : 28, confirme independamment.** Methode : `grep` de la forme
`ArchRule <nom> = ` dans les fichiers `.java` sous `src/test/java` des cinq modules de code
declares au pom agregateur, execute dans ce seul worktree. 28 fichiers correspondants, methode et
resultat identiques a ceux de `theo-archi` dans `confirmation-archunit.md` - je ne les recopie pas,
je les retrouve.

**Aucun test instable constate.** La suite a tourne quatre fois en tout dans ce worktree pendant
cette review (deux `mvn clean verify` complets encadrant la section 3, plus deux executions
ciblees des garde-fous mis a l'epreuve) : memes chiffres a chaque fois, aucune variation d'un run a
l'autre.

## 2. Tracabilite critere -> test

Verifie par lecture des fichiers de test et des commits, pas suppose :

- **S8-1 et S8-3 (cabinet, `lea-dev`)** : `CabinetDeLaMachineTest`, ecrit dans le meme commit que
  le code (`recreo.css`, `fragments/manche.html`). Chaque methode de test porte le critere qu'elle
  garde en commentaire ou dans son nom : `leCabinetEntoureLesPropositionsEtRienDAutreQuiSePresse`
  (S8-1), `leCabinetNePorteAucuneConditionDeRendu` (ADR-0030 point 1.c),
  `leCabinetNAtteintNiLEcranDeRetourNiLAccueil` (ADR-0030 point 1.d, nadia-prof section 6),
  `lesDimensionsDuCabinetSontExprimeesEnRemOuEnPourcentJamaisEnPixels` (S8-1), et une methode
  distincte sur le retrait du disque decoratif de `.demarrer`/`.encore`. Preuve de morsure fournie
  par des jeux fautifs nommes (`uneDimensionDuCabinetEnPixelsFaitEchouerLeGardeFou`, etc.), verifie
  a la lecture.
- **S8-2 (fin du mouvement, `theo-archi`, deja livre)** : `LAnimationRetardeMaisNeDetermineRienTest`,
  methode `derniereImageDeChaqueAnimationEgaleLEtatDeRepos`. Rejoue par moi-meme en section 3 :
  garde-fou mordant.
- **`ADR-0031` (co-occurrence d'ecrans, C4, `theo-archi`)** :
  `UnEcranNeGouvernePasLApparenceDUnAutreTest`, trois cas (production, jeu fautif, jumeau corrige).
  Rejoue par moi-meme en section 3.
- **Activation de "Le bon verbe" (`marc-dev`)** : aucun test nomme dediee, et c'est le bon choix -
  le commit `eaccf9d` le dit lui-meme dans son message ("verifie par les garde-fous derives
  existants... sans qu'aucun ne nomme ce jeu : ils l'exercent des qu'il existe") :
  `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`, `ChaquePaquetPorteUnIndiceTest` et
  `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` sont derives du catalogue reel (`ADR-0013`)
  et couvrent la quatrieme entree sans modification. Verifie a la lecture des trois fichiers.
- **S8-4 (extension conjugaison, `marc-dev`)** : `PaquetsFrancaisS04Test`, quatre methodes portant
  le mot "S8-4" dans leur commentaire de tracabilite et reprenant les points 1 a 3 de la barre de
  `nadia-prof` au mot pres (`...LesSixPersonnesSontRepresenteesAuFuturEtALImparfait`,
  point 2 sur l'opposition d'un distracteur d'un autre temps, point 3 sur la marque temporelle
  reperable). Ecrit dans le meme commit que le contenu (`bf268bd`).
- **S8-5 (police du repli, `lea-dev`)** : `TailleDesTextesEtBoutonsTest`, deux methodes neuves
  (`laPoliceDuRepliAQuatreColonnesEstUneExceptionAuPlancherGeneralDeDeuxRem`,
  `unePoliceSousUnRemHuitCentSoixanteQuinzeSousLeRepliFaitEchouerLeGardeFou`), commentaire de
  tracabilite citant les sept conditions de l'arbitrage prealable de `nadia-prof` du 2026-08-11.
  Rejoue par moi-meme en section 3.
- **`AGENTS_SQUAD` (`sofia-sm`)** : `outils/rapport-tokens-test.sh`, cas
  "ines-design est present dans AGENTS_SQUAD". Rejoue par moi-meme en section 3.

**Aucun module sans test dans ce que la squad a livre ce sprint** : les deux lots touchent
`adapter-web` (tests etendus dans le meme commit) et `adapter-content` (tests etendus dans le meme
commit), tous deux deja couverts avant ce sprint. Verifie sur les commits de fusion cites plus
haut : chacun porte son test dans le meme diff que son code de production, jamais dans un commit
separe.

**Aucune assertion HTML par expression reguliere constatee dans les tests neufs.**
`CabinetDeLaMachineTest`, `TailleDesTextesEtBoutonsTest` et `UnEcranNeGouvernePasLApparenceDUnAutreTest`
lisent le CSS et le HTML par selecteurs structurels (blocs de regles, attributs Thymeleaf,
derivation de repertoire de gabarits), jamais par `java.util.regex` sur du balisage - verifie par
lecture des imports et des methodes de ces trois fichiers.

## 3. Garde-fous cites comme fermant un refus ou une dette - mis a l'epreuve par moi

Un vert ne prouve rien tant que je ne l'ai pas casse. Quatre garde-fous sont cites ce sprint comme
fermant quelque chose ; j'ai mute le correctif que chacun protege, constate le rouge, remis le
correctif, constate le retour au vert. `git status --short` confirme un arbre propre apres chaque
cycle.

### 3.1 `ADR-0031` - le canal de feuille de la condition C4

**Mutation 1** : ajout dans `recreo.css` de
`body:has(.est-juste) .cabinet-bandeau { background-color: gold }` - la tentation exacte que
`nadia-prof` nomme.

```
mvn -B -pl adapters/adapter-web test -Dtest=UnEcranNeGouvernePasLApparenceDUnAutreTest
```

**Rouge** : `Tests run: 3, Failures: 1`, message nommant les deux jetons et les deux fragments qui
les posent (`retour.html` pour `est-juste`, `manche.html` pour `cabinet-bandeau`), sans qu'aucun
des deux noms ne soit ecrit dans le test - la derivation tient.

**Restauration** puis reexecution : `Tests run: 3, Failures: 0`. Retour au vert confirme.

**Mutation 2, la limite documentee comme non fermee (4.a)** : ajout de
`body:has(.est-juste) .entete { background-color: #2e7d32 }` - le verdict qui colore la coquille
permanente.

**Resultat : vert (3 cas passants), sans restauration necessaire pour le constater** - exactement
la limite qu'`ADR-0031` nomme et mesure, pas une limite supposee. Je confirme donc, sur ma propre
mutation, que ce canal reste ouvert.

**Conclusion sur C4, tranchee comme demande** : je considere la dette **reduite, non fermee**,
pour la meme raison qu'`ADR-0031` l'ecrit et que ma propre mutation confirme - le canal de feuille
qui reunit deux ecrans exclusifs est ferme et mordant (mutation 1), mais le canal de la coquille
permanente (mutation 2) et celui du gabarit (`th:classappend` depuis une variable de modele, non
testable sans introduire d'abord une variable de verdict qui n'existe nulle part aujourd'hui - je
n'ai donc pas pu le muter, faute d'objet reel a muter, ce qui est le symptome meme du canal ouvert)
restent tous deux ouverts. Rien dans ma propre lecture du code ne contredit cette clause ; je ne la
requalifie pas en "fermee" par confort de rapport.

### 3.2 S8-2 - la fin du mouvement des colonnes

**Mutation** : `to { clip-path: inset(80% 0 0 0) }` remis dans `@keyframes rouleau-glisse`
(la valeur fautive de s07).

```
mvn -B -pl adapters/adapter-web test -Dtest=LAnimationRetardeMaisNeDetermineRienTest#derniereImageDeChaqueAnimationEgaleLEtatDeRepos
```

**Rouge** : message citant exactement la discontinuite retrouvee ("l'animation rend donc la main
sur un autre etat que celui qui la suit").

**Restauration** (`inset(100% 0 0 0)`) puis reexecution : vert. `git status --short` : arbre
propre.

**Chiffre de `nadia-prof` (0 px contre 13 px avant)** : je ne l'ai pas remesure au pixel moi-meme
(pas de rendu Chrome relance pour ce point precis, capacite concentree sur les points 3.1, 3.3 et
3.4) ; je confirme en revanche que le garde-fou qui est cense l'empecher de regresser mord
reellement sur la regression exacte qu'elle decrit.

### 3.3 S8-5 - le plancher de police du repli a quatre colonnes

Trois mutations, chacune sur la production puis restauree :

- `font-size: 1.8125rem` (29 px, la valeur que `nadia-prof` a nommement refusee) sous le
  selecteur du repli : **rouge**,
  `laPoliceDuRepliAQuatreColonnesEstUneExceptionAuPlancherGeneralDeDeuxRem` echoue avec
  "expected: 1.875 but was: 1.8125".
- `font-size: calc(2rem * 0.9375)` (memes 30 px, ecrits autrement) : **rouge**, message "`font-size`
  doit etre exprime en rem" - la condition 3 de `nadia-prof` (valeur ecrite en clair, ni variable ni
  `calc`) est gardee dans sa forme et pas seulement dans son resultat.
- Restauration a `font-size: 1.875rem` litteral : vert, `Tests run: 2` (les deux methodes neuves),
  `Failures: 0`.

**Confirme sans reserve** : les sept conditions de l'arbitrage prealable qui portent une forme
testable (conditions 2, 3, 7) sont effectivement gardees par du code, pas seulement par une
promesse de commit.

### 3.4 `AGENTS_SQUAD`

**Mutation** : retrait de `"ines-design"` de la liste `AGENTS_SQUAD` dans
`outils/rapport-tokens.py`.

```
bash outils/rapport-tokens-test.sh
```

**Rouge** : "ECHEC : ines-design est absent de AGENTS_SQUAD." et l'ecart declare/reel affiche.

**Restauration** puis reexecution : "Tous les cas passent : AGENTS_SQUAD est aligne avec le roster
reel de la squad." `git status --short` : arbre propre.

### 3.5 Garde-fous de CI - rejoues sur la fenetre reelle du sprint

```
bash .github/scripts/verifier-prefixes-test.sh
bash .github/scripts/verifier-chemins-test.sh
bash .github/scripts/commit-atteignable-test.sh
```

Les trois suites : vertes, tous cas passants.

Puis les garde-fous eux-memes, **appliques exactement comme le fait `.github/workflows/ci.yml`**
(`--no-merges`, pas un `git log` brut) :

```
git log --no-merges --format=%s f29a2b0..bd9a8f0 | bash .github/scripts/verifier-prefixes.sh
git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh
```

Les deux : verts sur la fenetre du sprint (`f29a2b0`, tete de `round-two` avant les quatre lots de
s08, jusqu'a `bd9a8f0`).

**Un constat que j'ecris parce que je l'ai trouve en testant, puis que j'ai refute pour ne pas le
grossir a tort** : un `git log --format=%s` sans `--no-merges` sur l'historique complet fait
apparaitre un commit de fusion sans prefixe (`08e9eef0`, "Merge branch
'worktree-agent-aca7082667de45143' into round-two", du 2026-08-05, ancetre de `origin/round-two`
donc deja partage). J'ai verifie la cause avant de le rapporter comme un defaut : la CI exclut
deliberement les commits de fusion de ce controle (`--no-merges`, ligne 44 du workflow), pour la
bonne raison qu'un sujet de fusion est auto-genere par git et non redige par un agent. Ce n'est
donc **pas** un trou du garde-fou annonce en `CLAUDE.md` ("la CI rejette un commit sans tag
d'agent") : c'est mon propre test qui, en imitant mal l'invocation reelle, aurait signale un faux
positif. Je le documente pour que personne ne le redecouvre par la meme voie et ne le rapporte a
tort.

## 4. Les trois points a trancher ou nommer

### 4.1 La dette C4 - reduite, non fermee

Position rendue en section 3.1, avec preuve de morsure propre : **reduite, pas fermee.** Le canal
de feuille qui reunirait deux ecrans exclusifs (la tentation nommee par `nadia-prof`) est ferme et
verifie mordant par moi. Restent ouverts, exactement comme `ADR-0031` les nomme : le canal de la
coquille permanente (verifie vert sur ma propre mutation, donc une limite reelle et non supposee)
et le canal du gabarit (non testable aujourd'hui faute de toute variable de modele portant un
verdict sur l'ecran de manche - constat de structure, pas une case vide oubliee). L'identite des
pixels entre deux captures reste hors de portee de tout automate de ce depot et demeure la garde de
`nadia-prof` (`ADR-0025`).

### 4.2 Le scan du lexique de jeu d'argent - avis chiffre, decision non prise ici

Je n'ecris pas ce garde-fou moi-meme : c'est une decision de capacite qui revient a `sofia-sm`,
comme demande. Mon avis, chiffre :

**Faisabilite technique confirmee par lecture du code existant** :
`AucunVocabulaireDeMatiereTest` (domaine) et `TexteAccentueDesGabaritsTest` (adapter-web) portent
deja le patron exact - liste fermee de mots, perimetre derive d'un repertoire de sources ou de
gabarits, preuve de morsure sur un jeu fautif hors production. Ecrire une clause "aucun texte ni
attribut ne contient un mot du lexique de jeu d'argent" sur ce patron est un geste de meme taille
que ces deux fichiers, pas une nouveaute architecturale.

**Ce que le perimetre doit couvrir, et ce que ca coute** : `theo-archi` pose deux conditions dont
je confirme le cout par comptage direct dans ce worktree :

- **couvrir les paquets de contenu, pas seulement les gabarits** - c'est la contrainte qui change
  l'ordre de grandeur. `adapters/adapter-content/src/main/resources/contenu/` compte 4 paquets
  actifs ; `contenu-repris/` en compte **61** (compte direct, `find ... -name "*.yml" -o -name
  "*.yaml" | wc -l` sur ce worktree). Un scan qui ne lirait que `templates/` (4 gabarits derives,
  mesure par `ADR-0031` section "Non-vacuite") laisserait tout le texte que l'enfant lit
  effectivement hors du perimetre nomme par la clause - le defaut exact qu'`ADR-0015` a deja
  corrige une fois sur `HabillageDesEcransTest`.
- **eprouver la liste sur les 61 paquets de `contenu-repris/`, pas seulement sur une fixture ecrite
  par son auteur** - precedent direct et documente : la liste de mots interdits d'`ADR-0008` a du
  etre amendee en s07 pour avoir couvert le verdict et pas le tirage. Une liste jamais confrontee a
  du materiau qu'elle n'a pas ecrit est un vert qui ne prouve rien de plus que l'imagination de son
  auteur.

**Mon estimation d'effort** : la clause elle-meme (parcourir gabarits + paquets, comparer a une
liste fermee) est un geste d'une demi-journee au vu des deux patrons existants. L'epreuve sur les
61 paquets de `contenu-repris/` est le poste qui coute reellement - relire au moins les enonces et
propositions de 61 fichiers pour verifier qu'aucun mot legitime n'entre en collision avec la liste
(le meme risque que "son" dans "raison" nomme par `AucunVocabulaireDeMatiereTest`), soit l'ordre de
grandeur d'une revue de contenu complete, pas d'un ajout de test.

**Ce qu'elle ne garderait pas dans tous les cas, a dire au meme moment que la decision** : C6 (une
machine a sous evoquee par la forme et le decor) n'est pas un defaut de mots, et aucune liste ne le
verra. La moitie qui compte sur C6 reste entierement a `nadia-prof` sur le rendu, avec ou sans ce
scan.

**Decision : a `sofia-sm`**, avec ce chiffrage. Je ne l'ecris pas de ma propre initiative.

### 4.3 Ce qui reste garde par `nadia-prof` seule, sans filet de test

Verifie par lecture de `TailleDesTextesEtBoutonsTest.java` et confirme par ma propre mutation
(section 3.3) :

- **La taille de police effective sous le repli** : **gardable, et deja gardee.**
  `laPoliceDuRepliAQuatreColonnesEstUneExceptionAuPlancherGeneralDeDeuxRem` lit la valeur reelle du
  selecteur du repli (pas le selecteur litteral `.proposition`) et l'egalite qu'elle exige rougit
  dans les deux sens - vers le bas (29 px) comme vers une valeur masquee (`calc`). Ce n'est plus a
  la garde de `nadia-prof` seule.
- **L'air de chaque cote des glyphes dans la boite** : **non gardable a un cout raisonnable, et je
  rejoins l'avis de `nadia-prof` et de `theo-archi` sur ce point precis.** Mesurer un glyphe rendu
  exige un rendu reel (police installee, sous-pixel), exactement la raison pour laquelle
  `ADR-0025` a deja refuse d'automatiser le debordement de mise en page. A 2,86 px de marge sur le
  pire mot du paquet le plus charge, ce chiffre mordra a la prochaine forme ajoutee - mais le
  fermer par un automate couterait une dependance a un binaire externe que `mvn verify` ne peut pas
  garantir, pour une propriete qui depend de la pile de polices du poste qui l'execute. Je ne le
  recommande pas.
- **Le rapport effectif proposition/enonce (30 px contre 32 px)** : **non garde aujourd'hui, mais
  gardable a un cout faible - c'est celui des trois que je juge le plus rentable a fermer.** Les
  deux tailles en jeu sont deja des **valeurs declarees** que les tests existants savent lire
  (`tailleEnRem`, deja utilise sur `.proposition` et sur `.enonce` dans
  `leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul`) : il ne s'agit pas de mesurer un glyphe rendu
  comme pour le point precedent, seulement d'ajouter une comparaison entre deux valeurs de feuille
  deja lues ailleurs, sous le selecteur du repli plutot que sous `.proposition` seul. C'est un
  ajout de quelques lignes au meme fichier, pas une nouvelle capacite de mesure.
- **Le pli** : **non gardable par choix ecrit** (`ADR-0028`), et je ne rouvre pas ce refus. Les
  trois raisons qui le motivent (dependance a un binaire externe, mesure de pixels rendus
  dependante des polices du poste, interdiction explicite de `DEC-0016` de raisonner en nombre de
  caracteres) sont les memes que celles d'`ADR-0025`, et aucune n'a change ce sprint. Ce que
  `ADR-0028` livre a la place - `outils/mesurer-rendu.sh` avec un quatrieme argument de hauteur -
  rend la mesure rejouable sans main, ce qui n'est pas rien, mais reste un outil pour `nadia-prof`,
  pas un vert ou un rouge de `mvn verify`.

**En resume sur les trois** : la police (deja fermee), l'air des glyphes (a garder a la main,
motif tenable), le rapport proposition/enonce (gardable a peu de frais, je le recommande comme le
prochain geste le moins cher pour `theo-archi` ou `yanis-qa` si la squad veut reduire encore ce
qui reste sur la seule main de `nadia-prof`), le pli (non gardable, par decision deja ecrite et que
je confirme tenir).

## 5. La mesure de geometrie artisanale, repetee cinq fois - avis sur l'outillage

`nadia-prof` (trois fois), `ines-design` (une fois) et `lea-dev` (une fois) ont chacun reconstruit
la meme technique : `getBoundingClientRect` sur le texte contre `getBoundingClientRect` sur le
bouton, fragments obtenus par les vraies routes HTTP, Chrome reel a 360 px. `outils/mesurer-rendu.sh`
capture une image ; aucun des cinq n'en a besoin d'une, chacun a besoin d'un nombre.

**Mon avis : cette mesure merite d'entrer dans l'outillage versionne, et la doctrine d'`ADR-0009`
s'applique directement - elle ne l'interdit pas, elle demande de la couper en deux comme
`verifier-lignee.sh` et `verifier-jdk.sh` l'ont deja ete.**

- Une **fonction pure** qui, a partir d'un document HTML deja assemble et d'une feuille de style
  deja resolue, rend les rectangles englobants compares (texte, bouton) - c'est la partie
  testable sur des fixtures figees, sans navigateur.
- Une **enveloppe** qui pilote reellement Chrome (memes options que `outils/mesurer-rendu.sh` -
  profil isole, taille de fenetre, variable `CHROME`) et appelle la fonction pure - non testee
  pour la meme raison que l'enveloppe de `verifier-jdk.sh` : elle touche le poste, elle se prouve
  une fois a la main, pas en CI.

**Ce que je ne recommande pas** : en faire un test JUnit qui s'execute dans `mvn verify`.
`ADR-0025` et `ADR-0028` ont deja refuse cette forme pour la meme famille de mesure, pour des
raisons qui s'appliquent identiquement ici (dependance a un binaire externe non garanti par
`mvn verify`, resultat dependant des polices du poste). L'outillage a sa place dans `outils/`,
rejouable a la main par n'importe quel agent sans reecrire la technique a chaque fois - ce que
cinq reconstructions independantes du meme geste montrent, c'est le cout de ne pas l'avoir fait
plus tot, pas une raison de le automatiser en CI.

## 6. Ce qui reste hors de mon perimetre, nomme pour ne pas etre confondu avec le Done technique

- **Done pedagogique du cabinet (S8-1 a S8-3)** : `nadia-prof` l'a rendu VALIDE SOUS RESERVE sur
  `bd9a8f0` (verifie dans `FICHE-bandit-manchot-additions-cp.md`, section lue en section 4). Je ne
  le prononce pas et je ne le requalifie pas.
- **Done pedagogique de la conjugaison etendue (S8-4)** : a la date de ce rapport, sur `bd9a8f0`
  (le seul commit que j'audite), la ligne de `squad/programme/MATRICE-couverture.md` reste
  **`en cours`** (verifie a la lecture) et le troisieme audit de `nadia-prof` porte encore
  **REFUSE** sur le commit qu'il visait. Le quatrieme audit qui leve ce refus (VALIDE SOUS RESERVE)
  existe sur la branche non fusionnee `eb125e0`, hors de mon audit comme precise en tete de ce
  rapport : je ne l'anticipe pas comme un Done acquis sur `round-two`.
- **Confirmation ArchUnit** : `theo-archi`, dans `confirmation-archunit.md`. Je n'y touche pas ; je
  l'ai retrouvee independamment (28 regles, section 1).

## Fichiers cites dans ce rapport

- `adapters/adapter-web/src/main/resources/static/css/recreo.css` (lu, mute quatre fois, restaure
  a chaque fois - sections 3.1 a 3.4)
- `adapters/adapter-web/src/main/resources/templates/fragments/manche.html`
- `adapters/adapter-web/src/test/java/com/recreo/adapter/web/CabinetDeLaMachineTest.java`
- `adapters/adapter-web/src/test/java/com/recreo/adapter/web/LAnimationRetardeMaisNeDetermineRienTest.java`
- `adapters/adapter-web/src/test/java/com/recreo/adapter/web/UnEcranNeGouvernePasLApparenceDUnAutreTest.java`
- `adapters/adapter-web/src/test/java/com/recreo/adapter/web/TailleDesTextesEtBoutonsTest.java`
- `domain/src/test/java/com/recreo/domain/archi/AucunVocabulaireDeMatiereTest.java`
- `adapters/adapter-web/src/test/java/com/recreo/adapter/web/TexteAccentueDesGabaritsTest.java`
- `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetsFrancaisS04Test.java`
- `adapters/adapter-content/src/main/resources/catalogue.yaml`
- `outils/rapport-tokens.py` (mute puis restaure, section 3.4)
- `outils/rapport-tokens-test.sh`
- `.github/workflows/ci.yml`, `.github/scripts/verifier-prefixes.sh`, `.github/scripts/verifier-chemins.sh`
- `squad/decisions/adr/ADR-0025-une-mesure-de-rendu-porte-sa-commande-le-debordement-reste-non-gardable.md`
- `squad/decisions/adr/ADR-0028-outiller-le-pli-le-contenu-au-dessus-du-pli-reste-non-gardable.md`
- `squad/decisions/adr/ADR-0031-un-ecran-ne-gouverne-jamais-l-apparence-d-un-autre.md`
- `squad/conformite/FICHE-bandit-manchot-additions-cp.md`
- `squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`
- `squad/programme/MATRICE-couverture.md`
- `squad/sprints/s08-cabinet-de-la-machine/planning.md`
- `squad/sprints/s08-cabinet-de-la-machine/confirmation-archunit.md`
- `squad/sprints/s08-cabinet-de-la-machine/proposition-ines-largeur-du-cabinet.md`
