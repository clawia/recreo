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
