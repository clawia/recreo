# Rapport qualite - s22, yanis-qa

- **Mesure sur** : `round-two` a `62037c7` (huit lots fusionnes, avant Done pedagogique).
- **Worktree dedie** : branche `s22-yanis`, lignee verifiee par `outils/verifier-lignee.sh`
  ("le worktree sur la branche 's22-yanis' descend de la lignee de round-two").
- **JDK** : `outils/verifier-jdk.sh` - constat citable : `java du PATH : 17.0.4.1 | JDK de
  compilation : 25.0.2 | JAVA_HOME : renseignee | toolchains lu par Maven : oui`. Le JDK de
  compilation et d'execution reel des tests, rapporte par le build lui-meme (Spring Boot,
  bannieres de demarrage des tests d'integration), est **21.0.10** (toolchain declaree dans le
  fichier de toolchains de l'utilisateur) - c'est la version sur laquelle toute mesure de ce
  rapport est faite.

## Porte technique - commande, resultat, methode de comptage

```
bash outils/mvn-jdk21.sh -B clean verify
```

**Reacteur complet, aucun `-pl`, executee par moi-meme (pas recopiee).** Resultat : **BUILD
SUCCESS**, les six modules (`recreo`, `recreo-domain`, `recreo-application`,
`recreo-adapter-web`, `recreo-adapter-content`, `recreo-bootstrap`) en `SUCCESS`.

**Compte de tests, deux methodes independantes, apres `clean`, les deux convergent a 761 :**

1. Somme des blocs `[INFO] Results:` du reacteur (six occurrences, une par execution
   surefire/failsafe) : `49 + 13 + 332 + 250 + 111 + 6 = 761`.
2. Somme de l'attribut `tests="..."` de chaque rapport `TEST-*.xml` sous
   `target/surefire-reports/` et `target/failsafe-reports/` des cinq modules qui en produisent
   (116 fichiers XML lus, `failsafe-summary.xml` exclu a bon droit - ce n'est pas un rapport par
   classe) : **761**, avec `failures=0`, `errors=0`, `skipped=0`.

**761 tests, 0 echec, 0 erreur, 0 ignore. Je confirme le chiffre de `sofia-sm`** (`review.md`,
"761 tests, 0 echec") par ma propre mesure, methode XML, sur mon propre `clean`. Aucun ecart entre
nous deux sur ce sprint.

**Rappel de la methode a appliquer, verifiee ici plutot que recitee** : le rapport texte (`.txt`)
sous-compte de 7 les quatre classes qui melangent un champ `@ArchTest ArchRule` et une methode
`@Test` ordinaire dans la meme classe (`AbstractionsCiteesTest`, `LesReglesMordentTest`,
`ChargementDuContenuTest`, `AssertionsWebTest`) - je ne l'ai pas utilise comme source du chiffre
ci-dessus, seulement les blocs `Results:` agreges et les rapports XML, qui comptent les deux
moteurs.

## Couvert

- Les six modules ont des tests dans le meme commit que leur code (`domain`, `application`,
  `adapter-web`, `adapter-content`, `bootstrap` : aucun module sans test).
- Les trois paquets neufs (`cm1-comprehension`, `cm1-multiplications-divisions-10-100`,
  `cm1-problemes-durees`) ont chacun une classe de test dediee dans `adapter-content`
  (`PaquetCm1ComprehensionTest`, `PaquetCm1MultiplicationsDivisionsTest`,
  `PaquetCm1ProblemesDureesTest`), tracant nommement les conditions `COMP-*`, `MD-*`, `D-*`
  qu'elles couvrent (detail section "Conditions pedagogiques" ci-dessous).
- Le champ `texteSupport` (`ADR-0050`) est couvert cote domaine, contenu, web et a la frontiere
  HTTP (`LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest`, module `bootstrap`), avec ses quatre
  mises a l'epreuve propres (texte retire, texte intrus, texte altere, texte double).
- Le catalogue fusionne manuellement (24 entrees) est verifie deux fois par moi, independamment
  des chiffres deja cites par `sofia-sm` et `theo-archi` : voir section dediee.

## Non couvert - les trous assumes

- **Aucune mesure de rendu compose** (hauteur d'ecran, pli) n'existe en CI pour
  `cm1-comprehension` : `nadia-prof` le dit elle-meme dans
  `FICHE-bandit-manchot-comprehension-cm1.md` ("Aucun test de la CI ne mesure la hauteur composee
  d'une manche"). Cette limite est humaine et documentee, je ne la refais pas a mon compte.
- **Le repli visuel de `catalogue.yaml` (TYP-1)** n'a aucun garde-fou qui exige la presence de
  l'espace insecable - voir section dediee : le seul garde-fou existant (comparaison de consigne)
  neutralise volontairement la difference entre insecable et espace ordinaire, dans les deux
  sens.
- **La regression visuelle sur les 21 jeux anterieurs** (impact du CSS `.texte-support` neuf sur
  la mise en page partagee) n'est verifiee par aucun automate du depot - propriete non gardable
  par l'outillage actuel (meme famille que l'amendement s15 du README), voir section dediee.

## Tests instables

**Aucun** constate sur mon execution. Je n'ai relance aucun test pour obtenir ce vert : une seule
execution de `mvn -B clean verify`, apres `clean`, a produit le 761/0 ci-dessus.

---

## 1. Le catalogue fusionne a la main - verifie par moi, pas suppose

`sofia-sm` annonce 24 identifiants distincts, aucun doublon, aucun marqueur residuel
(`review.md`). Je l'ai mesure moi-meme, independamment :

```
grep -c "^\s*- id:" adapters/adapter-content/src/main/resources/catalogue.yaml   -> 24
grep "^\s*- id:" ... | sed 's/.*id: *//' | sort | uniq -c | sort -rn                -> aucun compte > 1
grep -nE "^(<<<<<<<|=======|>>>>>>>)" catalogue.yaml                               -> aucun
```

**24 identifiants, tous distincts. Zero marqueur de conflit residuel.** Je confirme.

**Etendu au-dela de ce que la review annonce** : chaque `paquet:` reference dans `catalogue.yaml`
pointe vers un fichier qui existe dans `contenu/`, et chaque fichier de `contenu/` a bien une
entree qui le reference - dans les deux sens, par comparaison d'ensembles triee (`comm -23` /
`comm -13`), pas par lecture visuelle :

- 24 references `paquet:` dans `catalogue.yaml`, 24 uniques.
- 24 fichiers `.yaml` dans `adapters/adapter-content/src/main/resources/contenu/`.
- **Les deux ensembles sont identiques** : aucun paquet reference sans fichier, aucun fichier sans
  entree de catalogue.

Le merge manuel a trois branches (`s22-lea`, `s22-sofia-2`, `s22-marc`) sur `catalogue.yaml` et
`TexteAccentueExposeParLePortEntrantTest.java` ne laisse ni entree perdue ni entree dupliquee.

### Mise a l'epreuve (obligation de cloture, amendement s10)

Je n'ai pas cru le vert : j'ai mute le fichier de production reel, jamais une fixture, sur la
branche `s22-yanis`.

- Modification : l'identifiant `bandit-manchot-homophones-cm1` (deuxieme entree du fichier) change
  pour `bandit-manchot-additions-cp` (identifiant de la premiere entree), tout le reste de
  l'entree inchange - un doublon d'identifiant pur, sans doublon d'association mecanique+paquet.
- Commande : `bash outils/mvn-jdk21.sh -pl adapters/adapter-content -am test` (perimetre : module
  `adapter-content` et ses dependances amont, `-am` obligatoire pour ce `-pl`).
- **Resultat : rouge**, sur **deux** garde-fous independants, tous deux lisant le fichier reel
  sous `src/main/resources/` :
  - `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest.leCatalogueServiNeDoubleNiUneAssociationNiUnIdentifiant`
    - `Found duplicate(s): ["bandit-manchot-additions-cp"]`.
  - `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest` (echec associe).
- Fichier restaure a l'identique (`git diff` vide, verifie), les deux memes tests repasses :
  **vert**, 5 tests, 0 echec (`AucuneEntreeDuCatalogueNEnDoubleUneAutreTest` : 4 ;
  `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest` : 1).

Ce garde-fou du merge manuel mord reellement sur le fichier de production, pas seulement sur ses
propres fixtures de contre-exemple.

## 2. Regression sur les vingt-et-un jeux anterieurs (`PartieController`, `manche.html`,
`recreo.css`)

**Diff lu, pas suppose.** Le seul commit qui touche les trois fichiers ce sprint est `ccbb588`
(`S17-2`, `texteSupport`). Les trois changements sont strictement additifs :

- `PartieController` : une ligne ajoutee (`modele.addAttribute("texteSupport", ...)`), aucune
  ligne existante modifiee.
- `manche.html` : un `<p>` neuf, garde par `th:if="${texteSupport != null}"` - absent du rendu
  pour tout jeu dont l'item ne porte pas `texteSupport` (les vingt-et-un jeux anterieurs, et vingt
  des vingt-quatre du catalogue final).
- `recreo.css` : une regle neuve, `.texte-support { font-size: 1.25rem; margin-bottom: 0.25rem; }`,
  aucune regle existante touchee.
- `Question` (domaine) : le constructeur a sept composantes existant est **conserve**, un
  constructeur a huit composantes s'ajoute et delegue vers lui avec `Optional.empty()` par defaut
  - tout appelant existant continue de compiler et de produire le meme objet qu'avant.

**Ce qui prouve l'absence de fuite, sans se fier au diff seul** :
`LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest` (module `bootstrap`, comptee dans les 761)
joue, via `MockMvc`, **chaque entree reelle de `catalogue.yaml`** (pas un echantillon : la
configuration de test derive la liste des jeux du catalogue reel par reflexion sur
`entreesDuCatalogueReel()`) et verifie par selecteur structurel jsoup (`.select(".texte-support")`,
jamais une expression reguliere sur le HTML) qu'aucun paragraphe `.texte-support` n'apparait sur
un jeu qui n'en declare pas. Cette classe porte elle-meme quatre mises a l'epreuve (texte retire,
texte intrus, texte altere, doublon) qui prouvent que ses assertions mordent reellement.

**Ce que je ne credite pas** : la story cite "avoir joue plus de 60 manches sans regression"
comme preuve manuelle. J'ai cherche cette affirmation dans le depot (`grep` sur tout `squad/`,
tous les messages de commit de `marc-dev` sur ce sprint) : **elle n'existe nulle part par ecrit**.
D'autres releves de manches jouees existent bien dans ce depot pour d'autres agents et d'autres
sprints (`FICHE-bandit-manchot-additions-cp.md`, `DEC-0016` : "60 manches sur 'Le bon verbe'"),
donc la pratique de le tracer existe - elle n'a simplement pas ete appliquee ici. Par le principe
de gouvernance de ce depot ("un rapport qualite qui n'est pas dans un fichier n'a pas ete
produit"), **je ne compte pas cette affirmation comme une verification** : ni fausse, ni vraie,
absente. La preuve reelle de non-regression pour ce sprint est la classe de test citee ci-dessus,
pas cette phrase.

**Sur l'ordre de fusion** : `review.md` explique que `sofia-sm` a delibrement fusionne `s22-lea`
et `s22-sofia-2` (les deux paquets neufs) avant `s22-marc` (qui durcit le lecteur partage), pour
que la porte technique qui suit la fusion de `s22-marc` mesure ce lecteur contre l'union du
catalogue du sprint (24 jeux), conformement a l'amendement s21. `fd7eb59` est bien le commit de
fusion de `s22-marc`, et c'est sur ce meme etat (verifie : `62037c7` en est un descendant direct,
sans autre fusion de contenu apres) que j'ai moi-meme fait tourner `mvn verify` en tete de ce
rapport. Ce point n'est donc pas un trou : la mesure existe, elle est faite au bon moment, et je
l'ai reproduite independamment.

**Ce qui reste hors de portee de tout automate du depot** : l'effet visuel du `margin-bottom`
neuf de `.texte-support` sur les 21 ecrans anterieurs (interligne percu, espacement entre blocs) -
propriete de rendu compose, meme famille que celle deja nommee non gardable par l'amendement s15
du `squad/README.md`. Aucun releve de ce sprint ne la couvre ; je le nomme plutot que de le
taire.

## 3. `TYP-1` - etat reel, mesure par moi

`review.md` (obstacle 7) rapporte, sur `catalogue.yaml` seul : 17 espaces ordinaires devant `?`,
4 devant `!`, zero insecable. **Je le confirme par mon propre comptage** (script Python, comptage
par point de code du caractere precedant chaque `?`/`!` dans le champ `consigne`, hors `titre`,
sur `adapters/adapter-content/src/main/resources/catalogue.yaml` a `HEAD`) : **17 / 0 devant `?`,
4 / 0 devant `!`**. Chiffre confirme, meme methode, meme fichier, meme date (2026-08-29).

**Ce que la review ne dit pas, et que j'ai mesure en propre** : l'arbitrage de `nadia-prof`
(`squad/conformite/ARBITRAGE-typographie-espace-avant-signe-double.md`, 2026-08-28) classe
l'application de `TYP-1` en trois lots par urgence mesuree, et qualifie le **lot 1** (14 signes
orphelins reels, visibles a l'ecran, sur `cp-problemes-additifs.yaml` et `cm1-perimetre.yaml`) de
**seul lot qu'elle qualifie d'urgent** - contrairement au lot 3 (dont `catalogue.yaml` fait
partie), qu'elle qualifie elle-meme d'"hygiene" sans effet visible mesure. J'ai verifie ces deux
fichiers du lot 1 avec la meme methode :

```
cp-problemes-additifs.yaml : 22 espaces ordinaires devant '?', 0 insecable
cm1-perimetre.yaml          : 21 espaces ordinaires devant '?', 0 insecable
```

**Le lot que `nadia-prof` a nomme urgent le 2026-08-28 n'a recu aucune correction de contenu a ce
sprint**, pas plus que le lot 3 cite dans l'obstacle 7. Le seul geste livre ce sprint sur `TYP-1`
(`fa7b019`, `theo-archi`) porte sur le **garde-fou de comparaison**
(`LaConsigneDUnJeuServiAtteintLEcranDeMancheTest`) : il neutralise l'insecable en espace ordinaire
**des deux cotes** de la comparaison, pour que ce garde-fou ne devienne pas rouge le jour ou le
correctif de contenu sera pose. J'ai lu le diff : c'est exactement cela, ni plus ni moins - deux
tests neufs prouvent que la neutralisation accepte l'insecable sans laisser passer un texte
etranger.

**Consequence a ecrire sans detour** : cette neutralisation **rend le garde-fou aveugle a la
presence de l'insecable dans les deux sens** - un texte qui l'applique et un texte qui ne
l'applique pas passent tous les deux ce test. Il n'existe **aucun garde-fou automatique, nulle
part dans ce depot, qui echouerait si `TYP-1` restait indefiniment non applique**. L'obstacle 7
du tableau de `review.md` est correctement marque `OUVERT`, mais son libelle ne cite que
`catalogue.yaml` (le lot 3, hygiene) ; il ne rappelle pas que le lot 1 (urgent, CP compris) est
dans le meme etat "zero insecable" depuis le 2026-08-28. Je le remonte pour que ce ne soit pas lu
comme "seule l'hygiene reste a faire".

## 4. Conditions pedagogiques du sprint - garde-fou automatique, oui ou non

**Methode et perimetre.** Je compte les conditions numerotees actives des trois paquets neufs de
ce sprint, telles qu'ecrites dans les fiches de conformite et l'arbitrage prealable de
`nadia-prof` a la date de ce rapport (2026-08-29) : `COMP-1` a `COMP-6` moins celles retirees
comme bloquantes par `DEC-0053` (`COMP-4`, `COMP-7`, exclues du compte - ce ne sont plus des
conditions en vigueur), soit **5 conditions COMP** ; `MD-1` a `MD-10`, **10 conditions** ; `D-1` a
`D-11`, **11 conditions**. Total : **26 conditions actives**. Une condition compte "guardee" si
au moins un test executable du depot la cite nommement (traçabilite, meme exigence que pour un
critere d'acceptation de `camille-po`) et echouerait sur sa violation ; je note "guardee
faiblement" une condition dont le test verifie un marqueur auto-declare plutot que la propriete
de sens elle-meme.

| Bloc | Guardees | Non guardees | Detail des non guardees |
|---|---|---|---|
| `COMP` (5 actives) | 2 (`COMP-1`, `COMP-3` faible) | 3 | `COMP-2` (distracteurs plausibles sans le texte), `COMP-5` (lisibilite du style, jugee une fois par `nadia-prof`, aucun test ne la cite nommement), `COMP-6` (distinction visuelle consigne/texte-support, "garde-fou humain... inchange" par la fiche elle-meme) |
| `MD` (10) | 9 (`MD-1` a `MD-9`) | 1 | `MD-10` (le titre annonce les deux operations) |
| `D` (11) | 8 (`D-1`,`D-2`,`D-3`,`D-4`,`D-5`,`D-7`,`D-8`,`D-11`) | 3 | `D-6` (distracteurs, erreur reelle nommee), `D-9` (situations, stereotypes), `D-10` (indice, methode vs slogan) |
| **Total** | **19 / 26** | **7 / 26** | |

**Contraste avec s21 (3/27)** : ce sprint fait mieux, et la cause est nommee dans l'arbitrage
prealable de `nadia-prof` lui-meme (section 1) - elle y explique avoir delibrement remplace des
familles de distracteurs enumerees par des **conditions d'aboutissement sur un resultat mesurable
par un tiers**, exactement la lecon qu'elle tire d'un defaut trouve au sprint 12. Le nombre plus
haut vient d'un choix methodologique explicite, pas d'un artefact de comptage.

**Ce qui merite d'etre nomme, au-dela du chiffre** : trois des sept conditions que
`nadia-prof` avait elle-meme predites non guardables **avant que le code n'existe** (arbitrage du
2026-08-15, section 4) se sont revelees guardees une fois le code livre :

- `MD-6` (la valeur egale au nombre de l'enonce n'est jamais la bonne reponse, et n'apparait qu'un
  tiers du temps) - predite non vue par le garde-fou de rang, en realite couverte par un test
  dedie du paquet.
- `D-2` (un instant et une duree ne s'ecrivent jamais de la meme facon) - predite "rien ne peut le
  verifier sans savoir ce que le texte veut dire", en realite couverte par un test de forme
  (regex sur la structure des propositions), qui n'a pas besoin de comprendre le texte pour
  distinguer les deux graphies.
- `D-7` (rang de la bonne reponse equilibre sur les durees) - le defaut de methode qu'elle avait
  elle-meme signale comme obstacle a s13 (le garde-fou de rang generique lit "l'entier de tete"
  d'une duree et se trompe) a ete corrige : `D-7` est desormais mesuree par la CI, l'exigence de
  comptage manuel qu'elle avait posee est levee (`PORTE-PEDAGOGIQUE-apres-arbitrage-du-2026-08-28.md`).

Une prediction de non-testabilite ecrite avant le code n'est donc pas a prendre pour acquise une
fois le code livre - je le note comme un point de methode utile pour la prochaine fois qu'un
arbitrage prealable liste ce qu'il croit non gardable.

**Un faux ami trouve en verifiant la tracabilite** : `MD-10` (le titre du jeu annonce les deux
operations) est cite en commentaire dans
`TexteAccentueExposeParLePortEntrantTest.java` ("titre neuf, annonce les deux operations
(MD-10)"). J'ai lu ce test : il verifie que l'inventaire des chaines exposees par le port entrant
est integralement accentue, par egalite de chaine sur une liste ecrite a la main - il ne verifie
en rien que le titre mentionne les deux operations. Si le titre perdait demain le mot "diviser",
ce test ne le verrait pas : il suffirait de mettre a jour la liste attendue avec la nouvelle
chaine, accentuee, pour rester vert. Le commentaire documente pourquoi cette chaine precise
figure dans l'inventaire, il ne garde pas `MD-10`. Je le classe non guarde ci-dessus pour cette
raison, et je signale la reference pour qu'elle ne soit pas lue comme une couverture.

## 5. Ce que je route

- **A `sofia-sm`** : l'ecart de perimetre dans le libelle de l'obstacle 7 de `review.md` (section
  3 de ce rapport) - le lot 1 de `TYP-1`, qualifie urgent par `nadia-prof` le 2026-08-28, est dans
  le meme etat non applique que le lot 3 cite, et le tableau des obstacles ne le distingue pas.
- **A `sofia-sm`**, pour transmission a `camille-po` et `marc-dev` : la pratique de tracer par
  ecrit une session de jeu manuel citee comme preuve de non-regression (section 2) - la pratique
  existe deja ailleurs dans ce depot (`FICHE-bandit-manchot-additions-cp.md`), elle n'a pas ete
  suivie ici.
- **Aucun obstacle technique nouveau** de mon fait : la porte technique est verte, mesuree par
  moi-meme, et le garde-fou du merge manuel mord reellement (section 1).

Je ne tranche aucune de ces remontees : ni le fond de `TYP-1` (redactionnel/typographique, sous
`theo-archi` et `sofia-sm` par l'arbitrage lui-meme), ni si l'absence de trace du jeu manuel de
`marc-dev` appelle une correction de charte.
