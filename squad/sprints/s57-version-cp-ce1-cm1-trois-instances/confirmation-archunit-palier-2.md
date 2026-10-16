# [Sprint s57] Palier 2 - confirmation ArchUnit a la cloture, coherence d'`ADR-0031` et d'`ADR-0065` A3, dettes routees au palier 3

- **Date** : 2026-10-16 (UTC+02:00).
- **Auteur** : `theo-archi`. Branche `s57-theo-p2-cloture`, worktree dedie, base `bb322938` (tete de `round-two`, poussee), `git status --short` vide avant ecriture.
- **Objet** : action sans budget de `planning.md` (*"`theo-archi`, confirmation ArchUnit a la review"*, sections du 2026-10-14 et du 2026-10-15 20:56), pour la cloture du palier 2.
- **Lu** : `planning.md` (amendements du 2026-10-16, 13:53 a 20:15) ; `avis-theo-palier-2.md` ; `lecture-theo-p2-7-forme-commune.md` ; `avis-theo-pa7-pa8.md` ; `lecture-theo-p2-12-rdo-cp-r3.md` ; `ADR-0031` et son amendement du 2026-10-16 ; `ADR-0065` A3 ; `ADR-0054` ; `ADR-0060` ; `ADR-0067` ; `ADR-0104` (methode du compte) ; `DEC-0122` ; au backlog, `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE`, `S57-GARDE-PILE-DE-POLICES` et `FM3b`.
- **Version** : **JDK 21.0.10**, propriete `java.version` des 313 rapports `TEST-*.xml` du reacteur complet, une seule valeur (`ADR-0071`).
- **Ce que ce fichier ne fait pas** : il ne modifie ni code, ni `ADR`, ni `ci.yml`, ni script ; les deux amendements de la section 4 sont des **propositions**. Il ne prononce aucun Done, ne juge aucun contenu, n'engage aucun poste.

## 0. Verdict

| Code | Question | Verdict |
|---|---|---|
| `V1` | Les regles ArchUnit sont-elles vertes sur l'arbre fusionne ? | **oui** : 15 classes porteuses de regles, 88 tests, 0 echec ; reacteur complet `BUILD SUCCESS`, 2669 tests, 0 echec (section 1) |
| `V2` | Le compte a-t-il bouge pendant le palier ? | **non** : 41 et 38 dans 16 fichiers a `f6134910` comme a `bb322938`, ecart nul ; 39 et 38 dans 15 fichiers hors contre-exemple (section 2) |
| `V3` | Les regles ajoutees ou amendees au palier sont-elles des tests executables ? | **oui, les trois**, lancees par le build et mordues sur l'arbre fusionne (section 3) |
| `V4` | `ADR-0031` amendee est-elle coherente avec le code fusionne ? | **non, sur deux points** : la decision 1 ne mord plus sur son propre exemple depuis s10 (`F1`), la decision de l'amendement promet `:has(...)` compris sans le tenir sous une virgule ni dans une regle imbriquee (`F2`). Deux amendements proposes, non appliques (section 4) |
| `V5` | `ADR-0065` A3 est-il coherent avec le code fusionne ? | **oui** (section 4.3) |
| `V6` | Un `ADR` neuf est-il appele par le palier 2 ? | **non** (section 7) |

## 1. Commandes et resultats

| Mesure | Commande | Resultat |
|---|---|---|
| `R1`, reacteur complet | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, sans `-pl`, arbre `bb322938` (ce fichier absent) | `BUILD SUCCESS`, exit 0, 7 min 50 s. **2669 tests, 0 echec, 0 erreur, 0 ignore** : 120, 31, 501, 1630, 381, 6 d'integration (lignes `Tests run` du reacteur) ; recompte a part par les attributs de 313 `TEST-*.xml` : 2669 |
| `R2`, perimetre ArchUnit | rapports `TEST-*.xml` de `R1`, classes declarant au moins une `ArchRule` (liste de la section 2) | **15 classes, 88 tests, 0 echec, 0 erreur** |

Detail de `R2`, nombre de tests par classe tel que Surefire le rapporte : `AssertionsWebTest` 2, `AbstractionsCiteesTest` 5, `AccesALEtatDeSessionTest` 2, `ArchitectureEnCouchesTest` 2, `ChargementDuContenuTest` 2, `DecouplageMecaniqueContenuTest` 13, `DifficulteTest` 3, `GestionDesErreursTest` 4, `LOrdreDesMatieresVientDeLaDonneeTest` 4, `LOrdreDesNiveauxResteUneDonneeTest` 5, `LeChoixDesJeuxResteDerriereLePortTest` 1, `LeSeulCookieEstLeMarqueurDePartieTest` 2, `LesReglesMordentTest` 39, `PureteDesCouchesTest` 2, `ScanDeComposantsTest` 2. Un rapport compte les `@ArchTest` et les `@Test` de la classe : ce nombre n'est pas le compte de regles. Les paquets `*.archi.*` portent 42 classes et 272 tests en tout ; les 27 autres sont des garde-fous JUnit sans `ArchRule`.

## 2. Compte de regles

**Methode d'`ADR-0104`** : lignes des `*.java` suivis par Git au commit nomme (`git grep`, ce qui exclut les worktrees d'agents) qui correspondent a `^\s*(public |private |protected )?static final ArchRule ` d'une part, a `^\s*@ArchTest\s*$` d'autre part ; sommes par `awk` ; fichiers par `git grep -l` sur l'un ou l'autre motif.

| Commit | `static final ArchRule` | `@ArchTest` | Fichiers | dont `RegleContreExemple.java` (declarations, `@ArchTest`, fichier) |
|---|---|---|---|---|
| `f6134910` (ouverture du palier 2) | 41 | 38 | 16 | 2, 0, 1 |
| `bb322938` (cloture) | **41** | **38** | **16** | 2, 0, 1 |
| ecart | **0** | **0** | **0** | 0 |

**Ce que 41 compte, pour qu'il ne soit pas relu comme 41 regles.** Deux des 41 lignes sont dans `bootstrap/src/test/resources/contreexemple/LesGardeFousSontMisALEpreuveTest/faux-module/.../RegleContreExemple.java`, un faux depot de contre-exemple qui declare une classe locale nommee `ArchRule` (`ADR-0013`) : ce ne sont pas des regles. **Regles reelles : 39 declarations dans 15 fichiers**, dont 38 executees par `@ArchTest` et une, `ScanDeComposantsTest.aucuneClasseDeTestSurLeClasspathDeLApplicationNePorteDeStereotype`, executee par deux `.check(...)` explicites. C'est le compte que donnent `ADR-0105` et `ADR-0106` (39) ; les comptes a 41 de ce sprint, les miens compris, sont la meme mesure contre-exemple compris. Les deux lectures sont exactes, elles ne comptent pas la meme chose.

**Aucune regle n'est affaiblie** : `git diff --stat f6134910 bb322938` restreint aux 16 fichiers ci-dessus, a `bootstrap/src/test/resources/contreexemple` et a `bootstrap/src/test/java/com/recreo/domain` (contre-exemples de mecanique) ne rend aucune ligne. Sur tout le diff `*.java` du palier (10 fichiers, 1868 insertions, 30 suppressions), aucun `@Disabled`, `@Ignore`, `ArchIgnore`, `allowEmptyShould(true)` ni `assume` ajoute. Les 6 lignes `assertThat` retirees sont celles du garde de D.4 d'`ADR-0067`, remplacees par les memes conditions sur la phrase neuve de `RDO-CP-R3` (`lecture-theo-p2-12-rdo-cp-r3.md`, `RL4`).

## 3. Regles ajoutees ou amendees pendant le palier : executables, et mordues sur l'arbre fusionne

Aucune des trois n'est une `ArchRule`, chacune pour un motif ecrit : une feuille de style et des gabarits (`ADR-0031`), un texte de paquet (`ADR-0065`), une mesure de mise en page (`LaFormeCommuneDesPropositionsLonguesEstEpingleeTest`, pas une decision d'`ADR`, `lecture-theo-p2-7-forme-commune.md` 4.4). Le compte de la section 2 ne bouge donc pas, ce qui est juste.

| Code | Regle | Fichier de test | Tests dans `R1` | Morsure sur l'arbre fusionne |
|---|---|---|---|---|
| `X1` | `ADR-0065` A3, l'affixe cite (`76642a48`) | `adapters/adapter-content/.../AucunMotServiNeSEcarteDeSonAccentTest.java` | 7, 0 echec | `B3` |
| `X2` | `ADR-0031`, amendement du 2026-10-16, garde de `C1'` point 1 (`b0c47c97`) | `adapters/adapter-web/.../UnEcranNeGouvernePasLApparenceDUnAutreTest.java` | 7, 0 echec (3 d'origine, 4 de l'amendement) | `B2` |
| `X3` | epinglage de la forme commune (`dc6f423d`), ferme par selecteur (`TL9`, `d9c6cc69`) | `adapters/adapter-web/.../LaFormeCommuneDesPropositionsLonguesEstEpingleeTest.java` | 5, 0 echec ; Javadoc *"quatre feuilles fautives"*, quatre repertoires sous `contreexemple/` (`TL10`) | `B1` |

**Methode des morsures.** Copie de l'arbre de `bb322938` (`git archive`) sous mon repertoire de travail, hors de tout worktree ; fichier mute par `sed` apres verification que le motif apparait une fois exactement, puis restaure par copie de reference (`cmp` : identique apres chaque mutation). Commandes : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test -Dtest=UnEcranNeGouvernePasLApparenceDUnAutreTest,LaFormeCommuneDesPropositionsLonguesEstEpingleeTest -Dsurefire.failIfNoSpecifiedTests=false` (12 tests) et, pour `B3`, la meme forme sur `adapters/adapter-content` avec la seule classe d'`X1` (7 tests). Perimetre : ces classes, pas le module. JDK 21.0.10 (rapports Surefire de la copie).

| Code | Mutation | Sortie |
|---|---|---|
| `B0` | aucune | 12 tests, 0 echec |
| `B1` | dans la regle de `R3-3` de `recreo.css`, `{ row-gap: 0.5rem; }` devient `{ row-gap: 0.5rem; column-gap: 0.25rem; }` (le `M3` qui passait sur `dc6f423d`) | **1 echec**, `laFormeCommuneEstCelleQuiAEteMesureeSurLaProduction` : *"... .propositions declare column-gap, que la forme mesuree ne declare pas"* |
| `B2` | ajout de `.ecran-de-jeu:has(.est-faux) .propositions { row-gap: 0; }` (`G6` de l'amendement) | **1 echec**, `aucuneRegleDontLeSujetEstHorsDuMessageNeNommeCeQueSeulLeMessagePose` : le message cite le selecteur, `manche.html`, sa region `[role=status]` et la classe `est-faux` |
| `B3` | `ce1-familles-de-mots.yaml`, ligne 22 : `(re-, ` devient `(re, ` (materiau reel, pas le test) | **1 echec sur 7**, `chaqueMotServiEnProductionPorteSonAccent` : *"le mot "re" n'est pas une forme du francais"* |

**`TL9` et `TL10`.** `planning.md` (13:53, section 4) ne les closait pas faute de mesure de morsure ; `B1` est cette mesure, sur l'arbre fusionne. Les clore est a `sofia-sm`.

## 4. Coherence des `ADR` amendes avec le code fusionne

### 4.1 `ADR-0031`, ce qui tient

Lu contre `bb322938` : les quatre tests nommes par l'amendement existent sous ces noms ; le seul nom ecrit par la clause est le role ARIA (`ATTRIBUT_DE_REGION = "role"`, `REGION_D_ANNONCE = "status"`) ; les lignes citees de `recreo.css` sont toujours 1569 (`.signal.est-juste .icone-signal`), 1817 (`O1`) et 1843 (`data-oeuvre-revelee`) ; `recreo.css` est identique a celui de `b0c47c97` (`git diff --quiet` sort 0) et le seul changement de gabarit depuis est une ligne de texte de `manche.html` (`RDO-CP-R3`), sans jeton neuf. Les populations de l'amendement (252 regles, 3 messages, 54 paires, 29 fautives) n'ont pas ete remesurees : leurs entrees n'ont pas bouge. Les limites ecrites `Z3` (4.a depuis `retour.html`) et `Z4` (un ecran qui perd sa region) y sont nommees.

### 4.2 `ADR-0031`, ce qui ne tient pas : deux constats, deux propositions

**`F1`. La decision 1 ne mord plus sur son exemple de reference depuis s10.** La decision 1 ecrit : *"`est-juste` est un jeton de `fragments/retour.html`, `cabinet-bandeau` un jeton de `fragments/manche.html`"*, et `body:has(.est-juste) .cabinet-bandeau { background-color: gold }` *"devient rouge"* ; sa table de morsure le cite comme premier rouge. Depuis `5c60102e` (2026-08-13, s10, *"la mecanique absorbe l'ecran intermediaire"*), `manche.html` pose `est-juste` sur son `.signal` : les deux jetons coexistent dans le meme document, et la co-occurrence le laisse passer. Mesure, meme methode qu'en section 3, une ligne ajoutee en fin de `recreo.css` :

| Code | Arbre | Mutation | Perimetre lance | Sortie |
|---|---|---|---|---|
| `Z5a` | `9c271e14` (avant la garde de `C1'`) | `body:has(.est-juste) .cabinet-bandeau { background-color: gold; }` | `adapter-web` entier et ses amonts | **496 tests, 1 echec**, `CabinetDeLaMachineTest.chaqueSelecteurEpingleEstEprouveParLeJeuFautif` : le selecteur neuf nomme `.cabinet-bandeau` et le jeu fautif ne l'eprouve pas. **Rouge pour une raison de registre, pas pour C4** |
| `Z5b` | `bb322938` | la meme | `UnEcranNeGouvernePasLApparenceDUnAutreTest` seule | **7 tests, 1 echec**, le test de `C1'` point 1 ; le test de la decision 1 (`aucuneRegleMaisonNeReunitDeuxEcransQuiNeSAffichentJamaisEnsemble`) reste vert |

La tentation que `nadia-prof` nomme en tete d'`ADR-0031` n'a donc ete refusee **pour son motif** par aucun test du 2026-08-13 au 2026-10-16 ; elle l'est depuis `b0c47c97`, par l'amendement. C'est la famille de defaut que ce depot sanctionne : une regle verte qui ne prouvait plus ce que son `ADR` affirme. Aucun fichier de `squad/decisions/adr` autre qu'`ADR-0031` ne parle de la co-occurrence (`git grep -n "co-occurrence"`) ; la preuve de morsure date de s08 et n'avait pas ete rejouee depuis.

**Proposition d'amendement `P1`, non appliquee** (a verser par moi dans le commit de `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE`, qui touche ce fichier) :

> **Amendement du AAAA-MM-JJ (`theo-archi`) - l'exemple de la decision 1 est garde par l'amendement du 2026-10-16, plus par la co-occurrence.** Depuis `5c60102e` (s10), `fragments/manche.html` pose `est-juste` sur son signal : `body:has(.est-juste) .cabinet-bandeau` ne reunit plus deux ecrans exclusifs, et la decision 1 le laisse passer (`Z5b` de `confirmation-archunit-palier-2.md` de s57). Du 2026-08-13 au 2026-10-16, il n'etait rouge que par `CabinetDeLaMachineTest.chaqueSelecteurEpingleEstEprouveParLeJeuFautif` (`Z5a`), pour une raison de registre. **La decision 1 reste juste** pour ce qu'elle juge : deux jetons de deux ecrans qui ne s'affichent jamais ensemble. **Ce n'est plus elle qui garde C4 sur l'ecran de manche** : c'est la clause du sujet hors du message. La ligne 1 de la table de morsure se lit comme une mesure du 2026-08-11, pas comme l'etat du code. **Regle executable** : la mutation `Z5b` ajoutee au fichier de la regle comme cas nomme (la production plus cette regle, rouge par le test du sujet hors du message), pour que le prochain changement de jetons ne la fasse pas disparaitre en silence. Compte ArchUnit inchange.

**`F2`. La decision de l'amendement du 2026-10-16 promet plus que le code ne tient.** Elle refuse une regle a sujet hors du message *"dont le selecteur nomme, `:has(...)` compris, un jeton"* propre au message. Deux formes l'ecrivent et passent, mesurees sur l'arbre fusionne (meme methode qu'en section 3, les 12 tests des deux classes) :

| Code | Ajout en fin de `recreo.css` | Sortie |
|---|---|---|
| `Z1` | `.ecran-de-jeu:has(.consigne, .est-faux) .propositions { row-gap: 0; }` | **12 tests, 0 echec** |
| `Z2` | `.ecran-de-jeu { &:has(.est-faux) .propositions { row-gap: 0; } }` | **12 tests, 0 echec** |

Cause lue : `LAnimationRetardeMaisNeDetermineRienTest.reglesMaison` coupe l'en-tete sur toute virgule (`entete.split(",")`, ligne 217), parentheses comprises, et prend le corps d'une regle jusqu'a la premiere `}` (`source.indexOf('}', position)`), donc ne voit jamais l'en-tete d'une regle imbriquee. La section *"Ce que cette regle ne garde pas"* ne nomme ni l'un ni l'autre.

**Proposition d'amendement `P2`, non appliquee** (meme commit que `P1`) : ajouter a *"Ce que cette regle ne garde pas"* les deux formes `Z1` et `Z2` avec leur mesure, puis, dans le meme commit, la correction de la section 5 (`Z1` et `Z2`), qui les fait sortir de cette liste. Si la correction n'est pas faite dans le meme commit, la liste seule est due a la premiere ecriture dans ce fichier.

### 4.3 `ADR-0065` A3 : coherent

- **Decision 1 et 2** : `estUnAffixeCite` admet une suite dont le trait d'union de bord n'est pas lui-meme colle a une lettre de l'autre cote, devant ou derriere ; un trait d'union entre deux lettres laisse les deux suites jugees. C'est le texte de la decision, lu dans le code.
- **Regle executable** : les deux clauses nommees existent, la classe porte 7 tests, verts dans `R1`. Le libelle recopie par `unAffixeCiteAvecSonTraitDUnionNEstPasJugeCommeUnMot` est toujours celui de la ligne 22 du paquet (un seul commit touche le fichier depuis `76642a48`, `1febd011`, anterieur ; la ligne est egale). `B3` mord sur le materiau reel, pas sur la recopie.
- **Dette ecrite par A3** (`domaine` et `competence` dans le perimetre sans etre lus par une vue) : toujours vraie a `bb322938` (section 5.10). Son declencheur n'a pas joue.

## 5. Dettes d'architecture ouvertes, a nommer au palier 3, avec leur route

| Code | Dette | Route | Ce qui la ferme |
|---|---|---|---|
| `Z1` | virgule dans `:has(...)` ou `:is(...)` | `theo-archi`, story `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE`, en tete du palier 3 | 5.1 |
| `Z2` | CSS imbrique | meme story, meme commit | 5.2 |
| `Z3` | verdict depuis `retour.html`, sans region d'annonce | aucune ecriture au palier 3 ; declencheur ecrit | 5.3 |
| `Z4` | un ecran qui perd `role="status"` sort du controle en silence | `theo-archi`, meme commit que `Z1` | 5.4 |
| `Z5` | decision 1 d'`ADR-0031` sans morsure propre sur son exemple (`F1`) | `theo-archi`, `P1`, meme commit que `Z1` | 4.2 |
| `RL5` a `RL7` | trois commentaires devenus faux | developpeur engage par `sofia-sm` ; forme tranchee ci-dessous | 5.5 |
| `PP` | pile de polices non gardee (`DEC-0122` 3.5) | `S57-GARDE-PILE-DE-POLICES` ; forme ci-dessous | 5.6 |
| `TQ` | 224 lignes a espace ordinaire avant `?` | aucune decision d'`ADR` a prendre ; route produit | 5.7 |
| `Q4` | crochet du harnais | l'utilisatrice | 5.8 |
| `E4` | `FM3b`, libelle `competence` expose par le port, servi par aucun gabarit | confirme, aucun amendement | 5.9, 5.10 |

### 5.1 `Z1`

**Corriger dans le decoupeur partage, pas dans la garde seule** : couper l'en-tete sur les seules virgules de profondeur zero (hors parentheses et crochets). Motif : le defaut est dans la lecture de la feuille, et les six tests qui la partagent lisent tous une population fausse des qu'une virgule est entre parentheses ; corriger la garde seule laisserait cinq lectures fausses. **Cout sur la production, mesure** : `recreo.css` de `bb322938` porte **0** virgule entre parentheses dans ses 169 en-tetes de regle (script de lecture hors depot, commentaires retires, profondeur comptee sur `(`, `)`, `[`, `]`) : aucun des cinq autres tests ne change de verdict sur la feuille servie, ce que le critere 7 de la story demande de dire avant. Trois autres decoupages a la virgule existent dans le module, hors de ce lecteur (`HabillageDesEcransTest` ligne 145, `TailleDesTextesEtBoutonsTest` ligne 195, `AucunEtatDePressableNEstAbandonneAuTiersTest` ligne 593) : le commit les lit et dit pour chacun s'il coupe un en-tete de regle ; s'il le fait, il passe par le lecteur corrige.

### 5.2 `Z2` : je prefere refuser le CSS imbrique, et je le dis avant, comme la story le demande

Le lecteur partage refuse toute regle dont le corps ouvre une accolade (`{` dans un bloc qui n'est pas une regle-`@`) : rouge, message *"regle imbriquee, non lue par les garde-fous de la feuille"*. **Motif** : `recreo.css` de `bb322938` porte **0** regle imbriquee (meme script) ; evaluer `&` demande de resoudre l'imbrication pour tous les garde-fous du module, pour un usage que personne n'a. Refuser coute une condition et un jeu fautif, et son mode de defaillance est un rouge. **Ce que cela amende dans la story** (a `camille-po`, avant le commit) : le critere 4 se lit *"au moins un test echoue et son message nomme l'imbrication"* au lieu de citer `.est-faux` ; le critere 5 (c), `.ecran-de-jeu { & .message-jeu .signal.est-faux { color: inherit; } }`, devient **rouge** au lieu de vert. **Ce qui me ferait changer d'avis** : une premiere regle imbriquee demandee pour un motif reel ; on evalue alors `&` dans le lecteur, une fois pour tous.

### 5.3 `Z3`

Limite 4.a d'`ADR-0031`, deja ecrite par l'amendement. **Pas de code au palier 3** : `retour.html` n'a pas de meuble (`CabinetDeLaMachineTest.leCabinetNAtteintNiLEcranDeRetourNiLAccueil`) et la neutralite de `.explication` y est gardee nommement (decision 3). **Declencheurs** : une region d'annonce posee sur `retour.html` (elle entre alors seule dans le controle, et `Z4` oblige a l'ecrire) ; ou une regle maison qui conditionne un element permanent a un jeton de `retour.html`. Poser `role="status"` sur cet ecran est une proposition d'accessibilite (`ines-design`), pas une decision d'architecture.

### 5.4 `Z4`

Un cliquet exact sur le nombre de regions d'annonce derivees des gabarits : **3** aujourd'hui (`designation.html`, `manche.html`, `saisie.html`, population de l'amendement), en egalite, sans nommer un gabarit. Ce qu'il interdit reellement : retirer `role="status"` d'un seul message sans que le commit le dise (le compte tombe a 2, rouge) ; en ajouter un sans le dire (4, rouge). Morsure demandee sur le gabarit reel : `role="status"` retire de `manche.html`, rouge. Il vit dans le fichier d'`ADR-0031`, meme commit que `Z1`, ecrit dans `P2`.

### 5.5 `RL5` a `RL7` : le commentaire de `manche.html` est servi ; la forme ne change pas

**Mesure par un rendu.** Jar `bootstrap/target/recreo.jar` construit par `R1` (`bb322938`), lance sur un port libre de mon espace de travail, arrete par son PID. `GET /`, puis `POST /parties/bandit-manchot-pronoms-ce1` (manche fraiche : 9 commentaires HTML dans la reponse), puis un coup `POST /parties/bandit-manchot-pronoms-ce1/coups` : la reponse porte **11** commentaires HTML, dont celui de `RL5` (*"cette phrase, de nadia-prof (M4)"*, lignes 208 a 213 du gabarit), dans `.message-jeu`. Thymeleaf rend `<!-- ... -->` tel quel ; aucune configuration du depot ne le retire.

**Ce que cela veut dire.** Le commentaire est dans le bloc `th:if="${message != null}"`, pas dans la branche des propositions en image : il est servi sur **toute** manche de `bandit-manchot` apres un coup, propositions ecrites comme en image, pas seulement sur `ART-CP-10`. Le corriger change l'octet servi de ces ecrans ; il ne change rien de ce qui s'affiche (un commentaire n'entre pas dans le rendu). `RL6` et `RL7` sont dans `src/test` : absents du jar, sans effet sur l'octet servi.

**Mon jugement sur la forme : elle ne change pas.** Le commentaire reste un commentaire HTML ; les trois commentaires sont corriges dans un seul commit, comme propose dans `lecture-theo-p2-12-rdo-cp-r3.md` (section 8). Motifs : `manche.html` sert 9 a 11 commentaires HTML, en passer un seul en commentaire de parseur (`<!--/* ... */-->`) ferait deux conventions dans le meme gabarit ; decider que les commentaires de gabarit ne sont jamais servis serait une decision distincte, avec sa garde (aucun `<!--` dans un rendu), que personne n'a demandee. **Ce qui force un rejeu n'est pas a moi** : je dis le fait, c'est `sofia-sm` et `nadia-prof` qui decident si un octet invisible change le jar qu'elle rejoue. **Condition que je pose** : le commit de commentaires entre au palier 3 **avant** le premier jar rejoue, et tout sommet de rejeu ecrit apres lui dit que le code differe du precedent par ce commentaire (lecon de l'amendement du 2026-10-16 20:15, section 1).

### 5.6 `S57-GARDE-PILE-DE-POLICES` : forme de la garde

**Un test d'`adapters/adapter-web`, et l'`ADR` que je designe est `ADR-0054`**, qui nomme *"la pile de polices declaree"* comme ce qui serait gardable : amendement dans le meme commit que le test. Pas une `ArchRule` : l'objet est une feuille et des gabarits. Compte ArchUnit inchange.

- **Feuille maison, perimetre derive de la feuille tierce.** Rouge si une regle de `recreo.css` declare `font-family` ou la raccourcie `font`, si la feuille porte une regle `@font-face`, ou si elle definit une propriete personnalisee que la feuille tierce lit dans une declaration de police. Cette derniere liste n'est **pas ecrite** dans le test : elle se derive de `static/vendor/bootstrap.min.css` (les `var(--...)` des declarations `font` et `font-family`, puis les variables qu'elles lisent elles-memes). Lecture faite par moi a `bb322938` : `--bs-body-font-family`, `--bs-btn-font-family`, `--bs-font-sans-serif`, `--bs-font-monospace`. Une population vide est un echec. Motif de la derivation : `--bs-body-font-family` nomme a la main laisserait passer `--bs-font-sans-serif`, que la feuille tierce lit au meme endroit.
- **Gabarits** (critere 4) : **refuse**, pas nomme comme limite. Rouge si un litteral d'un attribut `style` ou `th:style`, ou une balise `<style>`, nomme `font`. Cout nul, mesure : les gabarits portent 4 attributs de style (3 `th:style` de teinte dans `accueil.html`, 1 `style` de position dans `designation.html`) et 0 balise `<style>`, aucun ne nomme `font`.
- **Etat d'aujourd'hui, mesure** : `recreo.css` ne declare ni `font-family`, ni `font:`, ni `@font-face`, ni variable de police (0, 0, 0, 0) : la clause est verte sur la production sans exemption.
- **Ce qu'elle ne garde pas, a ecrire dans l'amendement** : une police posee par le JavaScript apres le rendu ; `letter-spacing`, `word-spacing`, `font-stretch` et toute propriete qui change la largeur d'un texte sans changer la police (hors de `DEC-0122`, qui parle de la pile) ; la feuille tierce elle-meme, deja epinglee par empreinte (`ADR-0014`).
- **Morsures** : les quatre formes du critere 2, une a une, plus une variable derivee non nommee par le critere (`:root { --bs-font-sans-serif: Georgia, serif; }`) et une valeur `font` dans un `th:style` mute sur `accueil.html`. Elles sont a `yanis-qa` (`CR39`).

### 5.7 Les 224 lignes a espace ordinaire avant `?` : aucune decision d'`ADR` n'est ouverte

**La decision existe et elle est gardee.** `ADR-0060` (consequence assumee 2, 2026-08-24) laissait la question ouverte ; elle a ete tranchee ensuite par `nadia-prof` (`TYP-1`, 2026-08-28 : un signe double ne se separe jamais de son mot) et par `ADR-0067` : le moyen vit dans la donnee, la dette est gelee par un cliquet exact, `AucunSigneDoubleNEstDetachableDeSonMotTest`, **490 occurrences** sur le texte servi, en egalite fichier par fichier, vert dans `R1`. Le compte du releve (lignes de fichiers YAML, quatre cles, commentaires compris) et celui du cliquet (occurrences de texte servi, quatre signes `?`, `!`, `;`, `:`) ne mesurent pas la meme chose.

**Un ecart de methode du releve, mesure.** Sur `bb322938`, la ligne de `cm1-anglais.yaml` que la commande du releve compte (ligne 209) porte `bonbons`, puis U+00A0, puis `?` (octets `c2 a0 3f`) : l'espace ordinaire comptee est dans le commentaire YAML de fin de ligne, pas dans le texte servi. `cm1-anglais` est absent de la table du cliquet, ce qui est juste. Une partie des autres lignes est de la notation mathematique, comme le releve le dit.

**Ce qui reste ouvert, et a qui** : (a) **quel caractere** (U+00A0, U+202F ou le retrait), ouvert par `ADR-0067` (*"Ce qui reste a decider a deux"*), une mesure de rendu d'`ines-design` et de `nadia-prof` ; le cliquet est agnostique et ne bouge pas ; (b) **payer la dette** (490), du contenu sur des paquets deja juges, dont l'ordre est a `camille-po` ; (c) si un `?` d'inconnue dans `a + ? = d` est un signe de ponctuation au sens de `TYP-1` : a `nadia-prof`. S'il ne l'est pas, le cliquet le surcompte et une exemption derivee s'ecrit avec un amendement d'`ADR-0067`, apres sa reponse, pas avant.

### 5.8 `Q4`

Mon avis est ecrit (`avis-theo-palier-2.md`, section 4) et inchange : un crochet qui lit le texte de la commande echoue par omission ; un controle de l'effet detecte toute ecriture sans attribuer ; sans crochet, la mesure de cloture est ce meme controle, une fois par palier. Le choix est a l'utilisatrice.

### 5.9 `E4` : `FM3b` confirme

`FM3b` (*"le libelle `descripteur.competence` n'est lu par aucun code de production ni ecrit par aucun gabarit"*) est vrai a `bb322938`, par des commandes plus larges que les siennes : `git grep -n -i 'competence'` sur `adapters/adapter-web/src/main`, `application/src/main` et `bootstrap/src/main` ne rend qu'un commentaire de `recreo.css` (ligne 394, sans rapport) ; `git grep -n '::competence\|\.competence()'` sur tout `*/src/main/*` ne rend rien ; `git grep -n -i 'descripteur'` sur `adapters/adapter-web/src/main` ne rend rien : **l'adaptateur web ne touche jamais le descripteur**. Seul le chargeur ecrit le champ.

**Ma lecture du port entrant** : le libelle y est expose parce que `ContentDescriptor` est un enregistrement du domaine porte tel quel, pas pour une vue a venir ; aucune vue ne le lit et aucune n'est prevue. `FM3b` n'a pas a s'amender. Si `camille-po` veut une commande qui couvre aussi une reference de methode (`::competence`), celle ci-dessus est a elle.

### 5.10 Dette d'A3, inchangee

Le garde-fou d'accent juge `domaine` et `competence` comme *"texte lu par un enfant"* (message de `B3`), ce que `FM3b` dement. Inclusion de trop, dont le mode de defaillance est un faux rouge ; declencheur ecrit dans A3 (un second faux rouge d'un champ non affiche, ou une vue qui affiche l'un des deux), non joue.

## 6. `ci.yml` (job `outils`) et `TMPDIR` de `outils/mvn-jdk21-test.sh`

### 6.1 `node` et Python dans le job `outils` : les declarer

**Avis : declarer les deux**, `actions/setup-node` et `actions/setup-python`, chacun a une majeure fixee. Motif decisif : **personne ici ne peut lire ce que l'image porte**. `gh run list --branch round-two` rend `HTTP 404` depuis ce poste ; tous les jobs `outils` annonces dans ce sprint ont tourne sur un poste. Le job `build` nomme son JDK (`actions/setup-java`) ; le job `outils` depend d'interpreteurs qu'il ne nomme pas. Le declarer rend son verdict independant de la composition de l'image, la ou l'accepter revient a accepter un fait que personne ne peut verifier.

- **Majeure** : celle que le poste de mesure rapporte, pour que le poste et la CI jugent sur la meme : `node --version` rend v24.12.0, `python --version` rend 3.14.0 (lus ce jour). `node` 24 couvre aussi le mode mesure du script (au moins 22, `marc-dev`). La disponibilite de ces majeures dans les deux actions est a verifier au moment du commit ; je ne l'ai pas verifiee.
- **Pas d'`ADR`.** Ce n'est pas une regle de structure, et sa garde executable existe deja : `outils/mesurer-additions-cp-test.sh` sort en erreur sans `node` (*"ECHEC node absent"*), et un juge Python sans interpreteur ne tourne pas. Le commit qui change `ci.yml` dit son motif en commentaire, comme les trois paragraphes datees du job le font.
- **A qui** : le commit est a un developpeur engage par `sofia-sm` ; la mutation (job lance sans `node`, rouge) a `yanis-qa`.

### 6.2 `TMPDIR` : normaliser, avec une garde contre le banc vide

**Avis : normaliser**, pas refuser. Le juge teste l'enveloppe, pas l'environnement : refuser un `TMPDIR` de forme `C:/...` rendrait rouge un poste legitime pour une raison qui n'est pas le defaut juge. La cause est un seul point (l'entree de `PATH` construite depuis `BAC`), la correction y tient en une ligne.

**Mesure, sur une copie de `outils/` de `bb322938`** (`git archive`, mon repertoire de travail ; script non commite). Ligne 23 remplacee par :

```bash
BAC="$(mktemp -d)" && BAC="$(cd "${BAC}" && pwd)" && [ -n "${BAC}" ] || { echo "ECHEC repertoire de banc"; exit 1; }
```

| `TMPDIR` | Ligne 23 | Sortie |
|---|---|---|
| forme mixte (`C:/...`) | telle quelle | exit 1, *"10 cas en echec"* |
| forme mixte | remplacee | exit 0, *"Tous les cas passent"* |
| forme POSIX (`/c/...`) | remplacee | exit 0 |

Sous Git Bash, `mktemp -d` rend le chemin dans la forme de `TMPDIR`, et `cd` puis `pwd` le rend en forme POSIX (mesure : meme repertoire, `C:/...` devient `/c/...`). Sur Linux, `pwd` rend le chemin inchange.

**La garde contre le banc vide n'est pas decorative.** Le script tourne sous `set -uo pipefail`, sans `-e` : si `mktemp -d` echoue aujourd'hui, `BAC` vaut la chaine vide et le banc s'ecrit sous `/bin` (`mkdir -p "${BAC}/bin"`, puis le `mvn` temoin dans `"${BAC}/bin/mvn"`). Lu, non mesure : je n'ai pas provoque l'echec de `mktemp`. **Preuve de la forme mixte** : manuelle et datee, dans le message du commit, aucun cas ne pouvant la rejouer sous Linux. Commit a un developpeur engage par `sofia-sm`, `outils/` n'etant pas a moi.

## 7. `ADR` appele par le palier 2 ?

**Aucun neuf.** Les deux amendements du palier (`ADR-0031` du 2026-10-16, `ADR-0065` A3) sont ecrits avec leur regle et verts. Les deux constats de la section 4.2 appellent un amendement d'`ADR-0031` (`P1`, `P2`), propose ici et non applique. `S57-GARDE-PILE-DE-POLICES` appellera un amendement d'`ADR-0054` dans son commit. `ADR-index.md` n'a rien a changer aujourd'hui.

## 8. Jobs de `ci.yml` lances pour ce commit

Lances sur ce worktree, ce fichier indexe, avant le commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `R1` sur `bb322938` sans ce fichier (section 1), puis `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, ce fichier present : `BUILD SUCCESS`, exit 0, 8 min 23 s, **2669 tests, 0 echec, 0 erreur, 0 ignore** (memes six lignes `Tests run` ; 313 rapports, JDK 21.0.10). Des tests de `bootstrap` lisent `squad/` : ils ont lu ce fichier avant l'ecriture de cette section, qui ne contient aucun tableau ni aucune reference neuve. Etape *"Package du fat jar"* : le jar est produit par `verify` et a servi a la mesure de 5.5 ; l'etape de la CI (`-pl bootstrap -am package`) n'est pas relancee a part.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou), ce fichier compris, passe a `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; `commit-atteignable.sh bb322938` sort 0 ; le sujet de ce commit passe dans `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, sortie 0. Le controle du proprietaire du garde-fou de l'accent est lance sur `bb322938..HEAD` apres le commit (rapport de la tache) : ce commit ne touche aucun de ses chemins.
- **`outils`** : les trois balayages (`outils/*-test.sh` : 6 ; `squad/outils/*/*-test.sh` : 2 ; `outils/*-test.py` : 5), **13 juges** lances (`bash` pour les `.sh`, `python` pour les `.py`), 13 sortis 0, aucun balayage vide, `TMPDIR` dans mon espace de travail en forme POSIX.

Aucun de ces resultats n'est celui de la CI distante, illisible depuis ce poste (6.1). **Le Done technique n'est pas prononce par moi.**

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ni amendee par ce fichier.** Il confirme le compte (ecart nul), mesure trois morsures sur l'arbre fusionne, propose deux amendements d'`ADR-0031` (`P1`, `P2`), tranche la forme de trois corrections a venir (`Z2` refuse, `RL5` sans changement de forme, garde de la pile de polices derivee de la feuille tierce) et donne deux avis (`node` et Python declares, `TMPDIR` normalise).
