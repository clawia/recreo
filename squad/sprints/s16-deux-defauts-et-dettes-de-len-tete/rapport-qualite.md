# Rapport qualite - sprint 16 (deux defauts et dettes de l'en-tete)

- **Auteur** : yanis-qa
- **Date** : 2026-08-19
- **Worktree** : dedie, provisionne depuis `round-two` sur une branche neuve (`s16-yanis`), verifie
  par `outils/verifier-lignee.sh` avant toute ecriture ("le worktree sur la branche 's16-yanis'
  descend de la lignee de round-two"). Aucun commit fusionne, aucun push.
- **Commit audite** : `73ae3b0`, tete de `round-two` a l'ouverture de cette saisine (les lots
  theo-archi, camille-po, marc-dev, sofia-dev, lea-dev et relecteur-pedagogique de s16 y sont
  fusionnes).
- **Constat JDK** : une toolchain `jdk` de version `21` valide sur ce poste ; toutes les mesures
  ci-dessous sont prises via `outils/mvn-jdk21.sh` (Maven lance sur cette toolchain, JDK 21.0.10
  constate dans les journaux Spring Boot), jamais par un `JAVA_HOME` positionne a la main ni ecrit
  dans un fichier commite.
- **Objet de la saisine** : le rapport qualite de s15 a ete declare perime par `sofia-sm` (objet
  disparu, chiffre deja reverifie trois fois). Sa duty reportee - la mutation-morsure d'un
  garde-fou recent sur du materiau de production reel - porte ici sur le garde-fou d'`ADR-0046`
  (`AucunEtatDePressableNEstAbandonneAuTiersTest`) tel que corrige par `lea-dev` en s16
  (fusion `f0e2762`, S16-3+S16-4), avec un point precis signale par `sofia-sm` : aucune fixture
  dediee ne prouvait par mutation executable le cas du sur-credit par classe de base, seule la
  Javadoc l'argumentait.

Ce rapport ne recopie aucun chiffre sans l'avoir rejoue sur un build complet, et ne recopie aucun
garde-fou cite comme fermant un trou sans l'avoir casse puis remis en etat sur du materiau reel.

## 1. Porte technique

```
bash outils/mvn-jdk21.sh -B clean verify
```

**Resultat : BUILD SUCCESS**, six modules en SUCCESS. Execute une fois en baseline (section 1), puis
a nouveau apres chacune des quatre mutations de la section 2 sur le seul module concerne (toujours
`-am`, jamais un `-pl` isole) ; `git status --short` vide avant la premiere execution, apres chaque
restauration individuelle, et une derniere fois sur l'arbre entier avant redaction de ce rapport.

| Module | Tests (rapports XML `surefire`/`failsafe`, agreges apres `clean`) |
|---|---|
| domain | 48 |
| application | 12 |
| adapter-web | 269 |
| adapter-content | 123 |
| bootstrap (surefire) | 87 |
| bootstrap (failsafe) | 6 |
| **total** | **545** |

**Methode, double** : (1) somme des blocs `[INFO] Results:` du reacteur, un par module, lus sur la
sortie complete de `bash outils/mvn-jdk21.sh -B clean verify` (jamais le resume `.txt`, qui a deja
sous-compte de 7 sur ce depot par le passe) ; (2) somme de l'attribut `tests` de la balise racine
`testsuite` de chacun des **87** rapports `TEST-*.xml` sous `*/target/{surefire,failsafe}-reports/`
produits par ce meme `clean verify`. Les deux methodes rendent **545**, sur le meme arbre, sans
aucune mutation active - **confirme le chiffre reconcilie par `sofia-sm`.**

**29 regles ArchUnit, confirme.** Methode : `git ls-files '*.java'` filtre sur les chemins contenant
`src/test/java`, recherche du motif litteral `ArchRule <nom> =`. Brut : **31** occurrences dans 13
fichiers. Deux vivent dans `RegleContreExemple.java`, le faux module de contre-exemple de
`LesGardeFousSontMisALEpreuveTest` (chemin qui contient la sous-chaine `src/test/java` sans etre un
module source compile par le pom agregateur) : **31 - 2 = 29**, meme methode que les sprints
precedents.

**Garde-fou de chemins**, rejoue exactement comme la CI :
```
git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh
  -> "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.", EXIT=0
```

**Tests instables** : aucun constate sur l'ensemble des executions completes de cette review.

## 2. Preuves de morsure - `ADR-0046`, `AucunEtatDePressableNEstAbandonneAuTiersTest`

Toutes les mutations ci-dessous sont jouees sur la production reelle (`recreo.css` ou
`bootstrap.min.css`, jamais une fixture), avec `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web
-am test -Dtest=AucunEtatDePressableNEstAbandonneAuTiersTest`. Restauration integrale confirmee
apres **chaque** mutation (`git status --short` vide sur le fichier concerne) avant de passer a la
suivante, puis une derniere execution du meme test confirmant le retour au vert.

### 2.1 Le premier trou (selecteur compose reduit a son premier element), rejoue tel quel

**Mutation, production** : dans `recreo.css`, la regle
```
body:has(.filtre-niveau) .chip-niveau.est-actif,
body:has(.filtre-niveau) .chip-niveau.est-actif:hover,
body:has(.filtre-niveau) .chip-niveau.est-actif:active { ... }
```
est reduite a son seul selecteur de repos (les deux lignes `:hover`/`:active` retirees) -
exactement la mutation que `sofia-sm` avait jouee en s16 et qui laissait alors les sept tests
verts.

**Resultat : rouge.** 1 echec sur 7 (`laDetteDEtatsDeLaProductionVautExactementSonPlafond`) : les
six exigences `.chip-niveau.est-actif` / `:hover` et `:active` / `color`, `background-color`,
`border` reapparaissent contre un plafond vide. Restaure, rejoue vert (7/7).

**Verdict** : la correction de `lea-dev` ferme bien le premier trou - la meme mutation qui passait
inapercue avant son lot est desormais attrapee.

### 2.2 Le sur-credit par classe de base - la fixture qui manquait

C'est le point signale par `sofia-sm` : `.proposition.deja-essayee` (identite composee) ne declare
aucun etat propre pour `color`/`background-color` a l'ouverture de s16 avant correction (seul
`text-decoration` y est declare au repos, pour le trait de M9) ; `.proposition:hover, :active`
(classe de base) declare deja ces deux proprietes pour tout `.proposition`. Le risque nomme mais
non prouve : que la classe de base "credite" a tort l'identite composee sans que celle-ci declare
rien en propre.

**Mutation, production** : retrait complet de la regle ajoutee par `lea-dev` dans `recreo.css` -
```
.proposition.deja-essayee:hover,
.proposition.deja-essayee:active {
    color: var(--recreo-accent-texte);
    background-color: var(--recreo-accent);
}
```
- ne laissant subsister que la regle de la classe de base `.proposition:hover, .proposition:active`
(inchangee, toujours presente).

**Resultat : rouge.** 1 echec sur 7 (`laDetteDEtatsDeLaProductionVautExactementSonPlafond`) : quatre
exigences reapparaissent - `.proposition.deja-essayee` / `:hover` et `:active` / `color`,
`background-color`. La classe de base ne credite pas l'identite composee : chaque sujet exact doit
declarer ses propres etats, exactement l'invariant que `etatsMaisonParSujet` (indexation par sujet
exact, amendement s16) est cense tenir. Restaure, rejoue vert (7/7).

**Verdict** : le cas que `sofia-sm` demandait de prouver par mutation executable est desormais
couvert par du materiau reel, pas seulement par la Javadoc de la classe.

### 2.3 Portee de ce que le mecanisme voit dynamiquement - le trait de M9 n'est pas qu'une mesure figee

La Javadoc de `lea-dev` affirme : "le trait de M9 (`text-decoration` et ses trois longhands sur
`.proposition.deja-essayee`) n'est touche par aucune des deux regles ci-dessous ni par aucun etat
de la feuille tierce [...] : il survit sans avoir a etre redeclare ici." Formulee ainsi, c'est une
mesure au jour de l'ecriture, jamais rejouee par un test dedie (contrairement au cas symetrique de
`:focus-visible`, epingle par
`lAnneauDeFocusClavierEstUneOmbrePorteeTierceEtCetEtatResteHorsDuPerimetre`). Je l'ai donc mise a
l'epreuve plutot que de la citer telle quelle.

**Mutation, production** : dans `bootstrap.min.css` (feuille vendorisee reelle, jamais une copie),
ajout de `text-decoration:underline` a la regle `.btn:hover` existante (celle qui declare deja
`color`, `background-color`, `border-color`).

**Resultat : rouge.** 1 echec sur 7, six exigences nouvelles dont quatre sur
`.proposition.deja-essayee` / `:hover` / `text-decoration` et ses longhands, plus deux sur
`.lien-accueil` et `.retour-accueil` (qui declarent aussi `text-decoration` a leur propre repos).
Restaure, rejoue vert (7/7).

**Verdict, et c'est la bonne nouvelle de cette review** : contrairement a ce que la formulation en
prose laissait craindre, cette propriete n'est **pas** seulement mesuree une fois puis figee dans
un commentaire - elle est **derivee et reverifiee a chaque execution**, exactement comme le reste
du mecanisme (`ETATS_GARDES`, `TOUS_LES_ETATS`, `proprietesRendues` lus sur la feuille tierce
reelle). Le jour ou une bibliotheque tierce ajouterait effectivement `text-decoration` a un etat
d'un selecteur applicable a un bouton maison, ce garde-fou rougirait tout seul, sans qu'il faille
se souvenir de revenir relire cette phrase. La formulation de la Javadoc gagnerait a le dire
explicitement (elle lit aujourd'hui comme un fait acquis plutot que comme une consequence
mecanique du reste de la classe), mais ce n'est pas un defaut du garde-fou lui-meme.

### 2.4 Ce que ce garde-fou ne peut structurellement toujours pas voir

Sans changement depuis la Javadoc de la classe, verifie par lecture et non recopie sans verification :

- **Un etat declare sur une balise nue** (`button:hover` plutot qu'une classe) : aucun bouton du
  depot n'en depend aujourd'hui (mesure sur la feuille vendorisee, `a:hover` est le seul cas et ne
  declare qu'une propriete personnalisee), mais la clause ne le verrait pas si un jour une
  bibliotheque le faisait.
- **La condition structurelle d'une regle tierce** (`:first-child`, `+`) n'est pas evaluee - la
  surcollecte assumee grossit une obligation, jamais une exemption, donc son mode de defaillance
  reste un rouge et non un vert a tort.
- **Ce que l'enfant voit reellement** : la clause exige une declaration, jamais une valeur juste ni
  un contraste calcule. Le verdict de rendu reste entierement a `nadia-prof`.
- **Le survol lui-meme** n'est simule par aucun outil de mesure de ce depot ; ce defaut a ete vu par
  une main sur une souris et ne peut l'etre que par elle.

Aucune de ces limites n'est nouvelle en s16 ; je les revalide plutot que de les laisser vieillir
sans lecture.

## 3. Le garde-fou de provenance (S16-7) - il mord, et il m'a mordu

**Test, sur ma propre commande.** Une fois le reacteur complet installe localement
(`bash outils/mvn-jdk21.sh -B install -DskipTests`, pour simuler un `mvn install` anterieur dans le
depot local Maven), j'ai lance une mesure partielle volontairement fautive :
```
bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web test -Dtest=ProvenanceDesClassesAmontEstLeDepotTest
```
sans `-am`.

**Resultat : rouge**, sur les deux tests de cette classe dans `adapter-web`
(`laClasseDeDomainVientDuTargetDuDepotEtNonDuDepotLocal`,
`laClasseDApplicationVientDuTargetDuDepotEtNonDuDepotLocal`) : chacun nomme le module, le
`target/` attendu dans le worktree, et la provenance mesuree - le depot local Maven configure sur
ce poste, jamais un chemin ecrit ici. **Le garde-fou attrape exactement le mode de defaillance
qu'il annonce**, sur ma propre invocation fautive et non sur une fixture ecrite pour lui. J'ai
ensuite retire du depot local Maven les artefacts `com.recreo:*` que cet essai y avait installes,
pour laisser le poste dans l'etat ou je l'ai trouve, et rejoue `bash outils/mvn-jdk21.sh -B clean
verify` en entier pour confirmer le retour au vert sur le reacteur complet (section 1).

**Consequence pour ce rapport et pour toute mesure partielle future** : chaque commande `-pl` de ce
document porte `-am`, sans exception, et c'est desormais verifie plutot que suppose.

## 4. Portee des garde-fous neufs du sprint - ce qu'ils voient, ce qu'ils ne voient pas

### 4.1 `TexteAccentueExposeParLePortEntrantTest` (S16-5)

**Ce qu'il voit** : toute chaine exposee par `PartieUseCase` a une vue, derivee par reflexion des
composantes `record` de `GameDefinition` et `ContentDescriptor` - verifie par lecture,
`ContentSource` (l'interface portee par `GameDefinition.contenu()`) et ses implementations
(`ListContentSource`, `AdditionsBorneesContentSource`) sont des classes ordinaires, jamais des
`record` : la reflexion s'y arrete structurellement.

**Ce qu'il ne voit pas, et ce n'est pas suppose** : le texte d'un paquet (enonces, propositions,
indices) porte par ces classes ordinaires n'entre jamais dans ce perimetre - il reste couvert par
le garde-fou distinct de `adapter-content` (`TexteAccentueDesGabaritsTest` exclut deja `th:text`
pour ce motif). C'est la meme frontiere que `sofia-sm` declare et que je confirme par lecture du
code, pas seulement de la Javadoc : `domain/src/main/java/com/recreo/domain/content/ContentSource.java`
est bien une interface, sans aucune de ses implementations declarees `record`.

**L'exemption "CP"/"CM1" - incident ou categorie ?** C'est une categorie, pas un incident date :
la Javadoc de la classe et son entree dans `LesGardeFousSontMisALEpreuveTest` (recensement 3)
motivent l'exemption par une propriete generale et reutilisable ("aucune de leurs lettres n'est
accentuable"), jamais par un nom de defaut corrige. Le plafond de criteres enumeres passe de 1 a 2
dans le meme commit (`PLAFOND_DE_CRITERES_ENUMERES`), avec la meme mecanique de cliquet que
`PLAFOND_DE_DETTE` - deja mise a l'epreuve par
`unCritereEnumereDeclareEtNonRecenseEstRefuse` sur un depot fictif, verte dans la section 1. Un
niveau neuf portant une lettre accentuable ("Theoreme") est deja epingle par mutation dans
`unNiveauNeufHorsDeLInventaireEtHorsExemptionFaitEchouerLeGardeFou`, execute et vert dans le build
de la section 1 : je ne l'ai pas rejoue a la main, il fait deja partie des 545.

### 4.2 Les trois garde-fous de provenance (S16-7)

**Ce qu'ils voient** : la provenance (`CodeSource`) d'une classe reellement chargee d'un module
amont, comparee au `target/` de ce module dans l'arbre du worktree courant - mordu sur ma propre
invocation, section 3.

**Ce qu'ils ne voient pas** : uniquement les trois classes nommees explicitement
(`GameMechanic` pour `domain`, `PartieUseCase` pour `application`, et une classe d'`adapter-content`
pour le troisieme, non relue en detail ici faute de temps dans cette review) - une classe amont
tierce qui ne serait jamais chargee par le module aval pendant les tests choisis ne serait pas
couverte, mais ce n'est pas le mode de defaillance vise : le mecanisme derive de "provient-elle du
bon `target/`", qui est une propriete de la resolution Maven et non de la classe particuliere -
n'importe quelle classe amont reellement utilisee suffit a la juger.

### 4.3 `ADR-0047` (duree du marqueur) et `ADR-0048` (adresse par en-tete)

Ni l'un ni l'autre n'est une regle ArchUnit (les deux ADR le disent au mot, et le compte de 29 a la
section 1 le confirme). Les deux se lisent sur du materiau reel et dynamique - une vraie
`MockHttpSession` dont `getMaxInactiveInterval()` est interroge, jamais un nombre recopie
(`laDureeDeVieDuMarqueurEstLueSurLaSessionEtJamaisRecopiee`), et un en-tete de reponse HTTP reel lu
dans les deux sens - present sur le catalogue complet, absent sur la confirmation
(`laReponseDeRetourNeDeclareAucuneAdresseAvecUnePartieEnCours` et son symetrique). Les deux ADR
ecrivent eux-memes, section "Ce qui reste non gardable", ce qu'aucun automate de ce depot ne peut
prouver : qu'une session ait reellement expire par inactivite (ADR-0047), et qu'un navigateur
honore reellement l'en-tete ou affiche reellement l'adresse (ADR-0048, ADR-0047). Je confirme ces
deux frontieres par lecture du code cite dans chaque ADR plutot que de les retranscrire sans
verification ; je n'ai pas rejoue de mutation dediee sur ces deux garde-fous dans le temps de cette
review, le risque principal du sprint (le mecanisme d'`ADR-0046`) ayant absorbe le budget de
mutation-morsure.

## 5. Defauts ouverts

Aucun defaut technique ouvert par cette review sur le mecanisme d'`ADR-0046` : les trois mutations
de la section 2 mordent toutes dans le sens attendu et se restaurent integralement.

Un point a signaler, non un defaut bloquant : la Javadoc de `lea-dev` sur le trait de M9 (section
2.3 ci-dessus) se lit comme une mesure figee alors qu'elle decrit une consequence mecanique
reverifiee a chaque execution - une reformulation legere eviterait qu'un futur lecteur la recopie
comme un fait acquis a ne plus revalider. Je le remonte a `sofia-sm` pour arbitrage sur la
pertinence d'un commentaire correctif, sans y voir un obstacle a la porte technique.

## 6. Rappel du double Done

Ce rapport ne prononce que le Done technique : `mvn verify` vert, sur les chiffres et la methode
ci-dessus, garde-fous d'`ADR-0046` et de S16-7 mis a l'epreuve et confirmes mordants. Le Done
pedagogique des stories de ce sprint (S16-1, S16-2, S16-6) reste entierement a `nadia-prof`, qui
n'a pas encore statue au moment de ce rapport. Un increment techniquement vert n'est pas un
increment accepte.

## 7. Complement du 2026-08-19 - les deux mutations dues avant cloture de s16

`sofia-sm` a pris acte de la section 2.2 (sur-credit par classe de base, desormais prouve) et a
redemande, avant cloture de s16, les deux points que la section 4 nommait sans les avoir mis a
l'epreuve : la portee structurelle des trois garde-fous de provenance (S16-7), et une mutation
propre sur `ADR-0047`/`ADR-0048`, sans reprendre la trace laissee par `marc-dev` (retiree avant
son propre commit).

**Commit audite pour ce complement** : `d2460a3` (fusion du rapport des sections 1 a 6), puis
fast-forward vers `round-two` a jour, qui embarque en outre `899bb48`/`ca3adc9` (`theo-archi` :
correction de l'exclusion de `:focus-visible` dans `ADR-0046`, statut `Accepte` pour `ADR-0047` et
`ADR-0048`). **Je n'ai pas mute la forme corrigee de `:focus-visible`** : `theo-archi` n'a touche
que le texte de l'ADR dans ce lot (il ecrit lui-meme "je n'y touche pas" a propos de la regle
executable), le code (`AucunEtatDePressableNEstAbandonneAuTiersTest.java`, `recreo.css`) est
identique a ce que j'ai deja mute trois fois en section 2 (`git diff 73ae3b0..d2460a3` confirme
aucun changement sur ces deux fichiers). Cette morsure reste donc a faire une fois le lot de
`lea-dev` fusionne.

Restauration integrale confirmee apres chaque mutation individuellement (`git status --porcelain`
vide sur le fichier concerne avant la mutation suivante), et build complet rejoue en fin de
section (`bash outils/mvn-jdk21.sh -B clean verify`, BUILD SUCCESS, 545 tests - meme methode double
que section 1, meme chiffre) : aucune des huit mutations ci-dessous ne laisse de trace dans
l'arbre.

### 7.1 ADR-0047 - la duree du marqueur, deux mutations sur PartieController.java / EtatDePartieEnSession.java

**Mutation A - valeur recopiee au lieu d'etre lue** (le defaut exact que `theo-archi` annonce comme
"faux en silence le jour d'une configuration") : `EtatDePartieEnSession.dureeInactiviteMaximale()`
change de `return httpSession.getMaxInactiveInterval();` a `return 1800;` (1800 est aussi la valeur
que fixe `PartieControllerTest.laDureeDeVieDuMarqueurEstLueSurLaSessionEtJamaisRecopiee` pour sa
session de test, choisie expres pour eprouver le risque au mot).

`mvn -pl adapters/adapter-web -am test -Dtest=PartieControllerTest,EtatDePartieEnSessionTest` :
vert sur `PartieControllerTest` (34 sur 34, y compris le test cite ci-dessus - la coincidence
1800/1800 le rend aveugle a cette mutation precise), rouge sur
`EtatDePartieEnSessionTest.dureeInactiviteMaximaleLitLaDureeReelleDeLaSessionHttp`, qui fixe sa
propre session a 900 : l'assertion 1800 = 900 echoue. Restaure (`git status --porcelain` vide sur
les deux fichiers), rejoue vert (38 sur 38).

Verdict : le risque nomme par `theo-archi` est reel a l'echelle d'un seul test - si
`EtatDePartieEnSessionTest` n'existait pas, ou choisissait la meme valeur que
`PartieControllerTest`, une valeur recopiee passerait inapercue. Ce qui ferme le risque n'est pas
une garantie ecrite dans la regle elle-meme, c'est que les deux tests qui exercent
`dureeInactiviteMaximale()` fixent deliberement deux durees differentes (1800 et 900), une
propriete des donnees d'entree des tests (ADR-0013, amendement), pas de la regle. Signale en
section 8 : ce n'est pas un defaut a corriger dans l'immediat (la suite mord bien, aujourd'hui),
mais une dependance fragile a nommer.

**Mutation B - le defaut d'avant ADR-0047, rejoue sans la trace de marc-dev** : dans
`marquerPartieEnCours`, retrait complet de `cookie.setMaxAge(...)` (le cookie redevient un cookie
de session navigateur, exactement l'etat que `ADR-0047` decrit comme le defaut mesure sur la
production d'avant son lot).

`mvn -pl adapters/adapter-web -am test -Dtest=PartieControllerTest,EtatDePartieEnSessionTest` :
rouge, 1 echec sur 38 -
`PartieControllerTest.laDureeDeVieDuMarqueurEstLueSurLaSessionEtJamaisRecopiee` (`getMaxAge()` vaut
-1, le defaut d'un `Cookie` construit sans `setMaxAge`, contre 1800 attendu). Restaure, rejoue vert
(38 sur 38).

Verdict : la regle mord bien sur le defaut original, etabli par moi et non recopie de la trace
disparue de `marc-dev`.

### 7.2 ADR-0048 - l'adresse par en-tete, deux mutations sur PartieController.java

**Mutation C - en-tete absente quand elle est due** : dans `retour(...)`, retrait de la ligne qui
pose `HX-Push-Url` dans la branche sans partie en cours.

`mvn -pl adapters/adapter-web -am test -Dtest=PartieControllerTest` : rouge, 1 echec sur 34 -
`laReponseDeRetourDeclareLAdresseDuCatalogueCompletSansPartieEnCours` (l'en-tete vaut `null` au
lieu de `/`). Restaure, rejoue vert.

**Mutation D - en-tete posee inconditionnellement**, exactement la forme que `theo-archi` refuse
au point 3 de l'ADR : la meme ligne est deplacee avant le test de partie en cours, donc posee
aussi sur la branche de confirmation.

`mvn -pl adapters/adapter-web -am test -Dtest=PartieControllerTest` : rouge, 1 echec sur 34 -
`laReponseDeRetourNeDeclareAucuneAdresseAvecUnePartieEnCours` (l'en-tete vaut `/` au lieu de
`null`, sur l'ecran de confirmation ou l'adresse mentirait). Restaure, rejoue vert.

Verdict : la regle mord dans les deux sens annonces par l'ADR - absence quand due, presence ou
elle est explicitement refusee - sur du materiau de production reel et non sur une trace recopiee.

### 7.3 Les trois garde-fous de provenance (S16-7) - ce qu'ils voient, ce qu'ils ne voient pas

J'avais deja etabli (section 3) qu'ils mordent sur l'absence de `-am` avec un depot local Maven
deja peuple d'un artefact `com.recreo`. Trois questions restaient : un module absent du reacteur
(pas seulement resolu ailleurs), un `target/` perime que le reacteur n'a pas recompile, et si la
comparaison par prefixe (repertoire de classes ou archive, jamais un chemin exact) est elle-meme
un trou.

Cas 1, module amont absent de tout, reacteur et depot local (pas seulement resolu ailleurs). Depot
local Maven verifie vide de tout artefact `com.recreo` avant l'essai. `mvn -pl adapters/adapter-web
test -Dtest=ProvenanceDesClassesAmontEstLeDepotTest` (sans `-am`) : BUILD FAILURE avant tout test -
resolution de dependances en echec, artefacts amont introuvables.

Ce n'est pas un angle mort du garde-fou, c'est un mode de defaillance different et plus sur : la
resolution de dependances Maven echoue avant que la classe de test ne soit meme chargee. Rien a
attraper puisque rien ne s'execute - le risque que ce garde-fou ferme (la commande reussit et
decrit un autre arbre) suppose qu'un artefact soit disponible quelque part pour etre resolu a
tort ; un module completement absent ne produit jamais ce risque, il echoue fort et tot.

Cas 2, target perime que le reacteur, invoque avec -am, ne recompile pas. Etabli par mutation
reelle : `domain/target/classes` recompile une premiere fois (`mvn -pl domain clean compile`),
puis `GameMechanic.java` modifie (ajout d'une methode par defaut) et sa date de modification
remise anterieure a celle du `.class` deja compile (manipulation d'horodatage, technique de test,
jamais commise). `mvn -pl adapters/adapter-web -am test
-Dtest=ProvenanceDesClassesAmontEstLeDepotTest` : BUILD SUCCESS, "Nothing to compile - all classes
are up to date" sur `recreo-domain`, les deux tests de provenance verts.

Verdict, et c'est l'angle mort reel : le garde-fou verifie une provenance, jamais une fraicheur.
Meme invoque avec -am - la forme que ce sprint impose partout - il ne voit pas qu'un
target/classes a l'interieur du perimetre qu'il accepte peut etre en retard sur le code source
qu'il pretend representer, des lors que l'heuristique de compilation incrementale de Maven (fondee
sur les dates de modification de fichier) juge a tort qu'il n'y a rien a refaire. La voie que j'ai
empruntee (horodatage truque a la main) est deliberement adversariale et non un mode de
defaillance ordinaire d'un poste de developpement - un editeur ou un compilateur avancent
toujours l'horloge d'un fichier modifie - mais elle a un equivalent naturel : une restauration de
cache de build, une manipulation d'horodatage involontaire, ou une horloge de poste desynchronisee
produiraient la meme illusion sans qu'aucune main ne l'ait cherchee. Le garde-fou ferme le trou
qu'il annonce (depot local contre arbre du worktree) ; il ne ferme pas celui-ci, qui est d'une
autre nature (fraicheur du contenu, jamais nommee dans sa Javadoc).

Cas 3, la comparaison par prefixe (repertoire de classes ou archive) est-elle un trou distinct ?
Reponse : non, c'est la meme limite que le cas 2, pas une seconde. La comparaison de chemins
delegue a la semantique standard de comparaison de chemins de la plateforme Java, qui compare des
elements de chemin entiers et non des caracteres - un repertoire voisin nomme, par exemple,
domain/target-old, ne satisferait jamais ce test (le dernier element differe : target-old contre
target). La souplesse que `lea-dev` a du introduire (accepter le repertoire de classes pendant les
tests et l'archive une fois la phase package atteinte) n'elargit le perimetre qu'a deux
emplacements que seul le reacteur de ce worktree ecrit en usage normal ; elle ne cree pas de
troisieme voie qu'un chemin fabrique pourrait emprunter. Le veritable defaut partage par les deux
formes (repertoire ou archive) est celui du cas 2 : aucune des deux ne porte d'information de
fraicheur, et une archive packagee a partir d'un repertoire de classes deja perime serait acceptee
exactement comme ce repertoire perime lui-meme.

## 8. Defauts ouverts (mise a jour)

Aucun defaut technique bloquant sur ADR-0046, ADR-0047, ADR-0048 ni sur le garde-fou -am de S16-7 :
les huit mutations de la section 7 mordent toutes dans le sens attendu, et les trois de la section
2 restent acquises. Deux points a signaler, ni bloquants ni proposes en correction par moi (hors
mandat) :

1. `EtatDePartieEnSession.dureeInactiviteMaximale()` n'est protegee contre une valeur recopiee que
   par la difference des deux durees choisies dans deux tests distincts (1800 dans
   `PartieControllerTest`, 900 dans `EtatDePartieEnSessionTest`), jamais par une propriete de la
   regle elle-meme. Si l'un des deux tests disparaissait ou changeait sa valeur pour coincider avec
   l'autre, une regression vers une constante recopiee passerait inapercue. Remonte a `sofia-sm`
   pour arbitrage sur l'opportunite d'un test dedie qui varie explicitement la duree plutot que de
   compter sur la divergence actuelle des fixtures.
2. Le garde-fou de provenance (S16-7) verifie un emplacement, jamais une fraicheur (section 7.3,
   cas 2) : un target perime que la compilation incrementale de Maven juge a tort a jour reste
   invisible, meme avec -am. Etabli par mutation adversariale (horodatage truque), sans equivalent
   commis dans le depot. Remonte a `sofia-sm` : fermer ce trou demanderait de comparer le contenu
   ou l'horodatage du fichier compile a celui de sa source, ce qui est un choix d'architecture de
   garde-fou, pas une correction de bug.

## 9. Rappel du double Done (inchange)

Ce complement ne prononce, comme la section 6, que le Done technique. Le Done pedagogique des
stories de ce sprint reste entierement a `nadia-prof`.
