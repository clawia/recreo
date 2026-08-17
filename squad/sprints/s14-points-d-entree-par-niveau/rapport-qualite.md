# Rapport qualite - sprint 14 (points d'entree par niveau)

- **Auteur** : yanis-qa
- **Date** : 2026-08-17
- **Worktree** : dedie, provisionne depuis `round-two` sur une branche neuve (`s14-yanis`), verifie
  par `outils/verifier-lignee.sh` avant toute ecriture ("le worktree sur la branche 's14-yanis'
  descend de la lignee de round-two"). Aucun commit fusionne, aucun push.
- **Commit audite** : `c1abb47`, tete de `round-two` a l'ouverture de cette review (les trois lots
  marc-dev, lea-dev, sofia-dev y sont fusionnes).
- **Constat JDK** : `~/.m2/toolchains.xml` porte une toolchain `jdk` de version `21` valide sur ce
  poste ; toutes les mesures ci-dessous sont prises via `outils/mvn-jdk21.sh` (Maven lance sur cette
  toolchain, JDK 21.0.10 constate dans les journaux Spring Boot), jamais par un `JAVA_HOME` positionne
  a la main ni ecrit dans un fichier commite.

Ce rapport ne recopie aucun chiffre sans l'avoir rejoue sur un build complet, et ne recopie aucun
garde-fou cite comme fermant un trou sans l'avoir casse puis remis en etat sur du materiau reel -
production quand la mecanique en cause en a un, fixture ArchUnit standard (compagnon de
`LesGardeFousSontMisALEpreuveTest`) quand la regle est structurelle et n'a pas d'autre objet.

## 1. Porte technique

```
bash outils/mvn-jdk21.sh -B clean verify
```

**Resultat : BUILD SUCCESS**, six modules en SUCCESS, execute deux fois - une fois avant les
mutations de la section 2, une fois apres restauration complete de toutes les mutations - avec un
total de tests identique les deux fois. `git status --short` vide avant la premiere execution et
apres la seconde (confirme ci-dessous).

| Module | Tests (rapports XML surefire/failsafe, agreges apres `clean`) |
|---|---|
| domain | 48 |
| application | 11 |
| adapter-web | 238 |
| adapter-content | 117 |
| bootstrap (surefire) | 82 |
| bootstrap (failsafe) | 6 |
| **total** | **502** |

**Methode** : somme de l'attribut `tests` de la balise racine `testsuite` de chacun des **80**
rapports `TEST-*.xml` sous `*/target/{surefire,failsafe}-reports/`, produits par le `clean verify`
ci-dessus. **502 confirme le chiffre de la saisine.**

**29 regles ArchUnit, confirme.** Methode : `git ls-files '*.java'` filtre sur les chemins contenant
`src/test/java`, recherche du motif litteral `ArchRule <nom> =`. Brut : **31** occurrences dans 13
fichiers. Deux vivent dans `RegleContreExemple.java`, le faux module de contre-exemple de
`LesGardeFousSontMisALEpreuveTest` (`bootstrap/src/test/resources/.../faux-module/...`), chemin qui
contient la sous-chaine `src/test/java` sans etre un module source compile par le pom agregateur :
**31 - 2 = 29**, meme methode que `theo-archi` (`confirmation-archunit.md`) et que les sprints
precedents.

**Garde-fou de chemins**, rejoue exactement comme la CI (`.github/workflows/ci.yml`) :
```
git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh
  -> "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.", EXIT=0
bash .github/scripts/verifier-chemins-test.sh
  -> "Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif."
```
Code 0, confirme. Premiere tentative sans rediriger l'entree standard dans le script (pas de liste
de fichiers en entree) : le script repond aussi "aucun chemin..." avec EXIT=0, mais **par defaut de
sujet a juger, pas par verdict** - une fausse confirmation que je note ici pour que personne ne la
reproduise en lisant ce rapport. La commande retenue ci-dessus est la seule qui vaut.

**Tests instables** : aucun constate sur les deux executions completes.

## 2. Preuves de morsure (garde-fous recents mutes sur du materiau reel)

Restauration integrale confirmee apres **chaque** mutation individuellement (`git diff --stat` vide
sur le fichier concerne avant de passer a la mutation suivante), et une derniere fois sur l'arbre
entier avant la section 1 : `git status --short` vide.

### 2.1 `LActionNeDeclarePasMoinsQueLaSortieTest` (ADR-0043, roles domines)

**Mutation 1, production** : `recreo.css`, `.chip-niveau { font-size: 1.50rem }` (etait 1.25rem,
identique a `.demarrer`). `mvn test -Dtest=LActionNeDeclarePasMoinsQueLaSortieTest` (module
adapter-web) : **rouge**, 1 echec sur 11 -
`aucuneSortieNeDeclarePlusQuUneActionDuMemeEcran` cite les trois ecrans ou `.chip-niveau` domine
desormais une action (`accueil.html`, `confirmation.html`, `erreur.html`). Restaure, rejoue vert.

Confirme que le troisieme role (`entree`) est reellement confronte aux actions sur les ecrans de
production ou nadia-prof a valide la boite ; ce n'est pas seulement une classe ArchUnit-like sur un
fichier de contre-exemple qui reste vraie par construction.

### 2.2 `LeChoixDesJeuxResteDerriereLePortTest` (ADR-0044, regle ArchUnit)

**Mutation, production** : `PartieController.niveaux(...)` reecrit pour filtrer
`partie.jeuxDisponibles().stream().filter(jeu -> jeu.descripteur().niveau().equals(niveau))` au lieu
d'appeler `partie.jeuxDuNiveau(niveau)` - exactement le chemin que le Javadoc de la regle nomme comme
le moins cher. `mvn test -Dtest=LeChoixDesJeuxResteDerriereLePortTest` (reacteur complet, `-pl
bootstrap -am`) : **rouge**, violation ArchUnit citant
`PartieController.niveaux(...) calls ContentDescriptor.niveau()`. Restaure, rejoue vert.

C'est une morsure sur le vrai code de production, pas seulement sur le module de contre-exemple
`com.recreo.adapter.web.contreexemple` que `LesReglesMordentTest` exerce deja par ailleurs (et que
j'ai laisse intact : les deux mecanismes sont complementaires, l'un prouve que la regle existe et
mord sur une forme fautive ecrite pour elle, l'autre que la regle mord sur le code livre).

### 2.3 `LeChoixDesJeuxNeVitPasDansLaVueTest` (ADR-0044, complement de gabarit)

**Mutation, production** : `accueil.html`, ajout de `th:if="${jeu.descripteur().niveau() == niveau}"`
sur le `<li>` de la liste des jeux - le second chemin le moins cher que le Javadoc nomme, celui que
la regle ArchUnit ne peut pas voir. `mvn test -Dtest=LeChoixDesJeuxNeVitPasDansLaVueTest` : **rouge**,
`aucunGabaritDeProductionNeLitLeDescripteurDeContenu` cite l'attribut `th:if` fautif dans
`accueil.html`. Restaure, rejoue vert.

Les deux garde-fous d'ADR-0044 mordent chacun sur le chemin qui echappe a l'autre, verifie sur le
gabarit et le controleur reels et non sur une fixture ecrite d'avance.

### 2.4 `EnTeteEtMenuTest` (comptage derive du catalogue plutot que fixe)

**Mutation, production** : ajout d'un second bouton `.chip-niveau` statique ("CM1") dans l'en-tete de
`accueil.html`, en plus de la boucle `th:each` sur `${niveaux}`. `mvn test -Dtest=EnTeteEtMenuTest` :
**rouge**, `leMenuDeLEnTeteContientUnPointDEntreeParNiveauEtUneSeuleSortieVersLAccueil` compare
`["CP", "CM1"]` (rendu) a `["CP"]` (`niveauxDuCatalogue()`) et refuse l'element en trop. Restaure,
rejoue vert.

Confirme que le remplacement du comptage fixe par une clause derivee (avis de faisabilite de
theo-archi, section 7.9 point 3) mord reellement : un point d'entree qui ne correspond a aucune
entree du catalogue est refuse, pas seulement un point d'entree absent.

### 2.5 `AucunArretDeTabulationNEstDestructeurTest` (corpus de routes etendu)

Trois mutations distinctes, chacune restauree avant la suivante :

1. **Route rendue muette** : `PartieController.niveaux(...)` reecrit pour toujours filtrer et rendre
   directement la liste, sans jamais passer par l'ecran de confirmation meme a partie en cours -
   exactement la forme que le piege 2 nomme ("une route qui ne ferait rien"). Resultat : **deux**
   garde-fous independants passent au rouge, pas un seul -
   `AucunArretDeTabulationNEstDestructeurTest.toutEcranAPartieEnCoursDeclareUnPointDeFocus` (clause
   4 : aucun point de focus declare sur l'ecran "demande de confirmation avant de changer de
   niveau") et `PartieControllerTest.unAppuiSurUnePuceDeNiveauPendantUnePartieNeModifieRienAvantConfirmation`
   (assertion explicite que `.question-confirmation` doit apparaitre). Voir section 3, piege (b).
2. **Corpus incomplet** : retrait de l'entree `"confirmation du changement de niveau..."` (route
   `POST /niveaux/{niveau}/confirmer`) de `ecrans()`. `chaqueRouteDuControleurEstExerceeParCeGardeFou` :
   **rouge**, "could not find the following elements: [POST /niveaux/{niveau}/confirmer]".
3. Chaque mutation restauree et rejouee verte avant la suivante.

### 2.6 Dette 2, `LaPositionDeLaBonneReponseALEcranNEstPasPredictibleSurUnGrandNombreDeTiragesTest`

**Mutation, production** : `BanditManchotSession.melanger`, `int j = hasard.nextInt(i + 1)` change en
`int j = 1 + hasard.nextInt(i)` - le swap ne touche plus jamais l'indice 0, donc la bonne reponse
(toujours deposee en tete avant melange) reste figee en position 0. `mvn test -Dtest=...` : **rouge**,
"position 0 : 30000 occurrences sur 30000 tirages (100,0 %), seuil de majorite 50,0 %". Restaure,
rejoue vert.

### 2.7 Dette 1, `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest`

**Mutation, production** : `catalogue.yaml`, permutation du champ `paquet` entre
`bandit-manchot-perimetre-cm1` (devient `cm1-mesures`) et `bandit-manchot-conversions-unites-cm1`
(devient `cm1-perimetre`) - une vraie mauvaise association pedagogique. `mvn test -Dtest=...` :
**reste vert**, comme le Javadoc de la classe le documente lui-meme ("elle ne peut donc jamais
detecter une mauvaise association ecrite dans le catalogue, seulement une divergence entre les deux
chemins de chargement du chargeur reel"). Restaure.

Ce n'est pas un defaut de ce garde-fou : c'est la preuve que la limite qu'il s'attribue par ecrit
est reelle et non une clause de style. Voir section 4 pour ce que cela laisse ouvert.

## 3. Les deux pieges nommes par theo-archi

**(a) Un repli CSS qui nomme le conteneur plutot que les puces.** Verifie sur le code livre :
`recreo.css` declare `.chip-niveau { ... }` (regle 650 et suivantes) directement sur la classe posee
sur chaque `<button>`, jamais sur `.bandeau-niveaux` (le `<nav>` conteneur). La mutation 2.1
ci-dessus, qui fait grossir `.chip-niveau` seul, suffit a faire rougir
`LActionNeDeclarePasMoinsQueLaSortieTest` : la regle lit bien la boite de la puce elle-meme, la
population d'ADR-0038 (derivee du balisage bouton par bouton, pas du conteneur) la voit. **Piege
ferme sur le code livre**, verifie et non seulement suppose.

**(b) Aucun garde-fou ne verifiait qu'un ecran de confirmation s'affiche.** La mutation 2.5.1
ci-dessus reproduit exactement la forme du piege - une route `/niveaux/{niveau}` qui filtre et rend
directement, sans jamais passer par `fragments/confirmation.html`, meme a partie en cours - et
**deux** tests independants la refusent : la clause 4 d'ADR-0040
(`toutEcranAPartieEnCoursDeclareUnPointDeFocus`, parce qu'un ecran qui ne rend jamais la
confirmation ne declare jamais son point de focus) et une assertion de contenu explicite dans
`PartieControllerTest` (`.question-confirmation` doit etre present). **Piege ferme sur le code
livre**, par deux mecanismes qui ne partagent pas la meme cause de defaillance - si l'un des deux
etait retire, l'autre resterait rouge sur ce scenario.

Aucun des deux pieges n'est resté ouvert dans ce que j'ai pu mettre a l'epreuve.

## 4. Criteres enumeres auto-declares (ADR-0013) : lecture, pas seulement grep

Grep mecanique de `Set.of(`/`List.of(` a cote d'un commentaire citant ADR-0013 dans les fichiers
touches par ce sprint (les sept fichiers de la section 2, plus `EnTeteEtMenuTest`). Aucune liste
neuve de ce sprint n'epingle un incident date : `ROLES_DOMINES` (`SORTIE`, `ENTREE`) et `COMPOSANTES`
(taille de police, hauteur minimale, rembourrage) sont des categories structurelles, pas des noms de
bugs ; `BALISES_NATURELLEMENT_FOCALISABLES` reprend le vocabulaire HTML, pas un historique.

Une liste existante, non modifiee par ce sprint mais dans le perimetre des fichiers exerces en
section 2 (`TexteAccentueDesGabaritsTest`), **epingle bien des incidents** : la clause
`aucunGabaritNeReintroduitUneFauteConnue` compare a cinq sous-chaines litterales ("ca.", "reponse
etait", "la partie est terminee", "d'avoir joue", "Récréo"), chacune une faute deja corrigee et
datee. C'est une lecture, pas un fait mecanique : le Javadoc de la classe le dit lui-meme depuis s11
("Le critere, lui, etait une liste de cinq fautes deja corrigees, donc vert par defaut sur tout
texte que ces cinq motifs ne traversent pas") et documente que c'est exactement pour cette raison
qu'ADR-0039 a ajoute a cote un second mecanisme, total et bidirectionnel
(`TEXTES_LUS_PAR_L_ENFANT`, verifie dans les deux sens par
`aucunGabaritDeProductionNEcritUnTexteHorsDeLInventaire` et
`chaqueTexteDeLInventaireEstEncoreEcritParUnGabarit`). La liste des cinq fautes n'est donc pas un
angle mort tu : elle est nommee comme telle, cantonnee a son role historique, et un texte neuf hors
des cinq motifs connus est deja ferme par le second mecanisme (verifie par
`unTexteNeufHorsDeLInventaireFaitEchouerLeGardeFou`, present avant ce sprint).

Aucune mutation supplementaire n'etait necessaire ici : le Javadoc de la classe designe deja
precisement sa propre limite et le garde-fou qui la comble.

## 5. Ce qu'un releve automatique peut et ne peut pas voir

**Les sept captures de `nadia-prof`** (`squad/conformite/releves-s14/*.png`) sont produites par
`outils/mesurer-rendu.sh`, qui pilote un vrai navigateur headless. Elles peuvent montrer un rendu
pixel exact a une largeur donnee sur le poste ou elles ont ete prises ; elles ne peuvent rien
prouver par elles-memes sur un autre poste ni entrer dans `mvn verify` - aucune assertion
automatique ne les compare, c'est `nadia-prof` qui les regarde.

**Le test de distribution de la dette 2** (30 000 tirages, graine `20260817L`) mesure
l'algorithme de melange Fisher-Yates de `BanditManchotSession.melanger` pour **toute** source
d'alea qu'on lui donne, comme prouve en 2.6. Il ne mesure pas la qualite du generateur reellement
cable en production. Verifie en lisant le cablage :
`bootstrap/src/main/java/com/recreo/bootstrap/config/HasardConfiguration.java` construit
`RandomSource hasard()` a partir de `new Random()` (graine d'entropie systeme, jamais fixee) ; le
test, lui, construit son propre `new Random(GRAINE)` avec une graine ecrite en dur, une instance
disjointe de celle du bean de production. Le Javadoc de la classe l'annonce ("Un generateur de
production biaise passerait ce test sans que rien ici ne le voie") et cette lecture du cablage le
confirme : c'est exact, pas une prudence rhetorique.

**La clause de la dette 1** derive le paquet attendu de `catalogue.yaml` lui-meme (le champ
`paquet` de chaque entree), donc compare deux chemins de chargement du meme chargeur pour la meme
valeur de champ. Une divergence entre `games()` et `chargerPaquet(id)` serait vue ; une mauvaise
valeur ecrite dans le champ `paquet` ne peut pas l'etre, puisque c'est cette meme valeur qui sert de
reference des deux cotes de la comparaison - confirme par la mutation 2.7, qui reste verte sur une
vraie permutation.

## 6. Ecart de mesure BARRE-8 (`outils/mesurer-rendu.sh`)

Reproduit dans ce worktree, sur ce poste, avec la commande exacte :
```
bash outils/mesurer-rendu.sh 360 http://localhost:8080/ /tmp/test.png
  -> "aucun navigateur trouve : mets un Chrome sur le PATH ou designe-le par la variable CHROME."
  -> EXIT=2
```
`command -v chrome`, `command -v google-chrome` et `command -v chromium` ne rendent rien dans ma
session, et `$CHROME` y est vide. C'est exactement le constat que `marc-dev` rapporte, obtenu
independamment, dans une session distincte de la sienne. Je n'ai pas reproduit la session de
`nadia-prof` (rien dans le depot ne dit ce que sa session porte comme variable ou comme PATH, et je
n'ecris aucun chemin de poste dans ce fichier) : je confirme seulement que le script lit `${CHROME}`
puis le `PATH` dans cet ordre (`.github/scripts` n'est pas concerne, le script vit sous `outils/`),
et que ces deux canaux sont bien absents ici comme dans la session de `marc-dev`.

**Ce que le depot garantit** : le comportement du script est deterministe et documente - meme ordre
de recherche, meme message, meme code de sortie non nul quand aucun navigateur n'est trouvable par
aucun des deux canaux (verifie en le rejouant ci-dessus). **Ce que le depot ne garantit pas** :
qu'un navigateur soit atteignable par l'un des deux canaux sur une session donnee - c'est un etat
d'environnement, exterieur au code et a Git, et la mesure BARRE-8 en depend entierement. Les deux
constats de `marc-dev` et de `nadia-prof` sont donc compatibles, chacun vrai dans son etat de
session, comme `sofia-sm` l'a deja etabli : ceci n'est ni une dette de code a corriger ici, ni un
defaut du script a assouplir. Je ne rouvre pas cette arbitrage, je le confirme par une mesure
independante.

## 7. `TexteAccentueDesGabaritsTest` face aux libelles "CP"/"CM1" : lequel decrit le code livre

Les deux affirmations citees dans la saisine (`marc-dev` : hors perimetre, vert parce que rendu par
`th:each`/`th:text` ; `theo-archi` : refuserait "CP" sans arbitrage cite) portent sur deux formes de
code differentes, verifiees separement :

- **La forme que `theo-archi` a mesuree** (avis de faisabilite, section 7.9) est un etat intermediaire
  du 2026-08-17, decrit explicitement comme "les deux puces posees ... et aucun controleur
  derriere" - une puce statique, sans liaison au modele. Reproduit ici en ajoutant un second bouton
  `<button ...>CP</button>` sans `th:text` : `mvn test -Dtest=TexteAccentueDesGabaritsTest` **rougit**
  bien, message "accueil.html ecrit \"CP\"... ne porte pas". La prediction de `theo-archi` est
  **exacte pour la forme qu'il a mesuree**.
- **La forme livree** (`accueil.html`, `<button ... th:each="niveau : ${niveaux}" ...
  th:text="${niveau}">CP</button>`) reste verte sur le build de la section 1, sans aucune mutation.
  Lecture du garde-fou : `remplaceAuRendu(element)` remonte les ancetres **en commencant par
  l'element lui-meme** ; le bouton porte `th:text` sur lui-meme, donc son propre noeud de texte
  ("CP", le contenu de repli avant rendu) est exclu du perimetre balaye, exactement comme le
  Javadoc de la classe l'annonce deja pour tout `th:text`/`th:utext` porte par un ancetre. Restaure
  (la mutation d'essai retiree) : rejoue vert.

**Conclusion : `marc-dev` decrit exactement le code livre.** La prediction de `theo-archi` n'est pas
fausse, elle porte sur un etat du gabarit qui n'a pas ete livre - le lot a choisi la forme dynamique
precisement pour rester dans le trou structurel que ce garde-fou documente deja pour tout texte
issu du modele, et non parce que l'arbitrage pedagogique aurait ete cite. Aucune contradiction reelle
entre les deux agents, seulement deux mesures a des moments differents.

Reste ouvert, pour `sofia-sm` et `nadia-prof`, une question que ce garde-fou ne peut pas trancher :
"CP"/"CM1" echappent au filet parce que ce sont des codes de niveau scolaire officiels (comme
"Recreo" echappe a l'exigence d'accentuation en tant que nom de produit, `leTitreDuProduitSecritRecreoSansAccent`),
ou parce qu'ils sont rendus dynamiquement, ce qui est une raison structurelle differente et non un
verdict sur le fond. Le garde-fou ne distingue pas les deux, et ce n'est pas a moi de le faire.

## 8. Defauts ouverts

Aucun defaut technique ouvert par cette review : les 502 tests, les 29 regles ArchUnit et le
garde-fou de chemins sont verts sur l'arbre fusionne, et chaque garde-fou mis a l'epreuve en section
2 a mordu sur du materiau reel, restaure a l'identique.

Point a signaler, non un defaut : le "second ecart" note en section 7 (le fondement exact de
l'exemption de "CP"/"CM1", officielle vs structurelle) n'est tranche par aucun garde-fou et
n'appartient pas au Done technique - je le remonte pour que `nadia-prof` s'en saisisse si elle le
juge utile, sans prejuger de sa reponse.

## 9. Rappel du double Done

Ce rapport ne prononce que le Done technique : `mvn verify` vert, sur les chiffres et la methode
ci-dessus. Le Done pedagogique de S14-1 - la barre d'entree par niveau, ses libelles, l'ecran de
confirmation - reste enterement a `nadia-prof`, qui n'a pas encore statue au moment de ce rapport.
Un increment techniquement vert n'est pas un increment accepte.
