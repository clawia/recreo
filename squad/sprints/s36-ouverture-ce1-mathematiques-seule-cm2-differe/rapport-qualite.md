# Rapport qualite de cloture - s36

**Auteur** : `yanis-qa`. **Date** : 2026-09-21. **Arbre mesure** : `s36-sofia` a `1d361786`,
worktree dedie (`git worktree add ../recreo-s36-yanis -b s36-yanis s36-sofia`), separe de tout
worktree partage (regle de mesure isolee de s30).

Contenu du sprint, pour situer cet audit : ouverture du perimetre CE1 (mathematiques seule,
referentiel de 17 competences par `nadia-prof`, matrice 138 -> 155 lignes, aucun contenu CE1 chiffre
encore livre) et le mecanisme d'`ADR-0094` + les clauses corrigees d'`ADR-0093` pour
`S36-QLM11-PLANCLASSE` (`cp-plan-de-la-classe`) : libelle de zone hors de la boite du bouton, numero
servi, nom accessible resolu (`aria-labelledby`/`aria-label`/texte) portant le libelle declare par
le paquet, legende sous le dessin.

---

## 1. Done technique - CI verte, commande exacte et resultat

Commande : `bash outils/mvn-jdk21.sh -B clean verify`, lancee depuis la racine du worktree dedie.

Resultat : **BUILD SUCCESS**, reacteur complet (`recreo`, `recreo-domain`, `recreo-application`,
`recreo-adapter-web`, `recreo-adapter-content`, `recreo-bootstrap`), toutes phases jusqu'a
`failsafe:verify` incluse.

Verdict de version rendu par l'enveloppe : Maven lance sur le JDK de la toolchain `jdk` `21`,
`21.0.10` (constate en interrogeant ce JDK, pas la toolchain declaree - `mvn-jdk21.sh`).

**Cet enonce ne dit que ce que j'ai constate sur ce lancement precis** ; je n'ai pas mesure la CI
GitHub Actions elle-meme (`.github/workflows/ci.yml` porte plusieurs jobs, dont `chemins` et les
juges de prefixe de commit, qu'aucun `mvn` ne couvre - voir `CLAUDE.md`). Ce rapport ne porte que
sur le job `build`.

## 2. Compte de tests autoritaire, avec sa methode

**Methode 1** : somme des blocs `[INFO] Results:` du reacteur complet, extraits du journal de
lancement, six blocs (un par module qui execute des tests) :

| Module | Tests run |
|---|---|
| recreo-domain | 111 |
| recreo-application | 13 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 707 |
| recreo-bootstrap (surefire) | 250 |
| recreo-bootstrap (failsafe) | 6 |
| **Total** | **1487** |

**Methode 2, independante** : somme de l'attribut `tests="..."` de chaque `<testsuite>` des rapports
XML Surefire et Failsafe generes par ce meme lancement (201 fichiers
`target/{surefire,failsafe}-reports/TEST-*.xml`, tous modules confondus) : **1487**.

Les deux methodes, sur le meme lancement, convergent sur **1487 tests, 0 echec, 0 erreur, 0
ignore**. Ce chiffre confirme, par reverification independante et non par recopie, celui de 1487
annonce par `sofia-sm` en cloture de fusion. Delta face a s35 (1479, `d22d24d1`) : **+8**, sur le
meme commit de base que ce rapport nomme, pas un total etabli par moi - le total sur l'arbre fusionne
reste a `sofia-sm`.

**Couverture par module** (fichiers de test presents, aucun module vide, et **inchangee face a
s35** - aucun fichier de test cree ni supprime ce sprint, seules des methodes ajoutees dans des
classes existantes) :

| Module | Fichiers `*Test.java` / `*IT.java` |
|---|---|
| domain | 10 |
| application | 2 |
| adapter-web | 49 |
| adapter-content | 95 |
| bootstrap | 47 |

## 3. Mutation de garde-fous recents sur materiau reel

Regle appliquee (amendement s10) : le garde-fou est mis a l'epreuve en mutant l'objet reel qu'il
pretend garder - le gabarit ou le code de production reellement servi - jamais une fixture ni une
copie en memoire deja ecrite par le garde-fou lui-meme.

### 3.1 Les deux garde-fous corriges par `ADR-0093` - `fragments/designation.html` mute

Fichier mute : `adapters/adapter-web/src/main/resources/templates/fragments/designation.html` -
l'attribut `aria-label=${libelleDeZoneHorsDeLaBoite} ? ${zone.libelle()} : null` retire de la
declaration `th:attr` du bouton de zone (ligne 56), le reste du gabarit inchange. C'est la forme
exacte d'un relais 4 (`PartieController`/gabarit) qui aurait pose le booleen de vue sans jamais
emettre le porteur de nom accessible qu'`ADR-0093` clause 1 et `ADR-0094` decision 5 promettent :
un bouton numerote (`1`, `2`, ...), sans aria-label, sur un paquet qui declare
`emplacementDuLibelleDeZone: hors-de-la-boite` (`cp-plan-de-la-classe`, seul paquet servi dans ce
cas).

**`LEcranDeMancheDuJeuDeDesignationReelEstServiTest` (bootstrap)** :

Commande : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dtest=LEcranDeMancheDuJeuDeDesignationReelEstServiTest -Dsurefire.failIfNoSpecifiedTests=false`

- **Avant restauration (gabarit mute)** : rouge, **3 echecs sur 8** -
  `surChaqueJeuDeDesignationDuCatalogueReelLEcranDeMancheEstConforme` (les six boutons de
  `cp-plan-de-la-classe` ont pour nom accessible leur seul numero, "1" a "6", qui ne porte le
  libelle declare pour aucune zone - grief ADR-0093 clause 2 pour chacun),
  `unLibelleDeclareMuteEnMemoireFaitEchouerLeGardeFou` (meme cause, la comparaison echoue avant meme
  la mutation en memoire du test) et
  `unAriaLabelRemplaceParUneAutreChaineFaitEchouerLeGardeFouAlorsQueLeTexteNAPasBouge` (l'assertion
  de precondition "le bouton doit deja porter un aria-label egal au libelle declare" echoue, l'attribut
  etant absent).
- **Apres restauration** (`git checkout` sur le fichier, `git status --porcelain` vide verifie) :
  vert, 8/8.

**`ChaqueZoneDesignableEstUnBoutonDuFormulaireDeCoupTest` (adapter-web), meme mutation** :

Commande : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test
-Dtest=ChaqueZoneDesignableEstUnBoutonDuFormulaireDeCoupTest -Dsurefire.failIfNoSpecifiedTests=false`

- **Gabarit mute (meme etat que ci-dessus)** : **vert, 8/8** - aucune regression.

Ce n'est pas un defaut : ce garde-fou juge le jeu fabrique de sa propre configuration de test
(`PartieControllerTest.JeuDeTestConfiguration`), jamais le catalogue reel, et sa propre Javadoc de
classe le dit ("Ce module ne compare pas le nom resolu au libelle declare par le paquet [...] cette
moitie-la est rejouee dans bootstrap"). La mutation confirme exactement la frontiere documentee : la
correspondance nom-accessible/libelle-declare (`ADR-0093` clause 2) n'est gardee que par le module
qui voit les deux cotes, et seul lui rougit. **Les deux garde-fous mordent donc reellement, chacun
sur le perimetre qu'il revendique** - aucun des deux ne pretend couvrir ce que l'autre couvre.

### 3.2 `ADR-0013`, critere enumere auto-declare - extension de la clause 2 d'`ADR-0050`

**Recherche** : aucune nouvelle classe de test de la famille "critere enumere auto-declare" n'est
entree dans l'arbre ce sprint (le diff Java du sprint, `git diff --stat a8fcb000..1d361786 --
'*.java'`, touche huit fichiers, tous deja dans l'arbre a s35). La seule extension d'une
enumeration auto-declaree est celle d'`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`
(`ADR-0050` clause 2) : `emplacementDuLibelleDeZone` entre dans
`COMPOSANTES_DANS_L_IDENTITE_D_ECRAN`, le nouveau champ de `ZoneADesigner` introduit par
`ADR-0094`.

A la difference de `PaquetCpEmcExclusionTest` (s35, liste de chaines nommant un incident clos),
cette enumeration **se verifie elle-meme par reflexion** : `verifierQueChaqueComposanteDUnItemEstClassee`
compare l'union des deux ensembles ecrits a la main aux `RecordComponent` reels de chaque forme
permise par `Item` (`getPermittedSubclasses()`), pas a une liste figee. Je l'ai mise a l'epreuve sur
la forme la plus proche du defaut qu'`ADR-0013` cherche : retirer du classement le nom du champ
**reellement ajoute ce sprint** a `ZoneADesigner`, sans le declarer ailleurs - simulant l'oubli que
`lea-dev` aurait pu commettre.

Commande : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test
-Dtest=AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest -Dsurefire.failIfNoSpecifiedTests=false`

- **Avant restauration** (`emplacementDuLibelleDeZone` retire de `COMPOSANTES_DANS_L_IDENTITE_D_ECRAN`,
  laisse dans aucun des deux ensembles) : rouge, 1 echec sur 18 -
  `chaqueComposanteDUnItemEstClasseeDansLIdentiteDEcranOuHorsDElle`, le message nommant le champ
  porte et non classe.
- **Apres restauration** (`git checkout`, `git status --porcelain` vide verifie) : vert, 18/18.

**Verdict** : ce garde-fou peut dire plus que lui-meme, parce que sa population n'est pas une liste
ecrite a la main mais une derivation par reflexion sur la scellure reelle d'`Item` - le meme jugement
que celui deja porte a s24 sur ce fichier. Aucun defaut de la famille `ADR-0013` trouve ici.

## 4. Defaut trouve - relais 1 d'`ADR-0094` sans test dedie, refus-sur-valeur-inconnue non garde

`ADR-0094` decision 4.1 (rappelee par `squad/decisions/adr/ADR-index.md`, entree ADR-0094) promet,
sur le patron explicitement cite d'`exigenceDeForme` (`ADR-0058`) : *"une valeur ecrite mais
inconnue arrete le chargement en nommant la cle et la valeur"*. Le precedent qu'`ADR-0094` cite est
teste : `SaisieContentSourceFactoryTest.uneExigenceDeFormeInconnueArreteLeChargementEnNommantLaCleEtLaValeur`.
**Aucun equivalent n'existe pour `DesignationContentSourceFactory`** :
`grep -rln "emplacementDuLibelleDeZone" --include=*Test.java --include=*IT.java` ne trouve qu'un
seul fichier (`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`, section 3.2 ci-dessus), qui ne
teste jamais cette methode ni ce refus. Aucun fichier `DesignationContentSourceFactoryTest`
n'existe. Aucun paquet de test ne declare de valeur invalide pour cette cle
(`grep -rl "emplacementDuLibelleDeZone" --include=*.yaml .` ne trouve que le paquet de production).

**Mise a l'epreuve, sur le code reel de production** : dans
`DesignationContentSourceFactory.emplacementDe`, le `throw new IllegalStateException(...)` sur
valeur inconnue remplace par `return EmplacementDuLibelleDeZone.DANS_LA_BOITE;` - une faute de
frappe dans `emplacementDuLibelleDeZone` (par exemple `hors-de-la-boîte` avec un accent, ou
`horsDeLaBoite`) se rechargerait alors silencieusement dans le comportement par defaut, exactement
le defaut qu'`ADR-0094` nomme et que le patron `exigenceDeForme` garde par un test.

Commande : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test` (module entier), puis
`bash outils/mvn-jdk21.sh -B -pl bootstrap -am test-compile failsafe:integration-test surefire:test`
(le seul autre module qui charge le catalogue reel).

**Resultat sur le code mute : vert des deux cotes** - `recreo-adapter-content` BUILD SUCCESS,
`recreo-bootstrap` (surefire) 250/250. Aucun test du depot ne rougit. Fichier restaure ensuite
(`git checkout`, `git status --porcelain` vide verifie), suite reverifiee au point 1 ci-dessus
(reacteur complet vert, 1487/1487).

**Consequence** : ce n'est pas un defaut de comportement en production aujourd'hui - le code
refuse bien de charger sur une valeur inconnue, je l'ai lu et la mutation le confirme a l'envers -
mais c'est un defaut de tracabilite au sens de mes responsabilites : le Done technique ne peut pas
repondre "ce comportement documente par `ADR-0094` est-il garde ?" en cherchant dans le code, la
reponse est non. Le chemin par defaut (cle absente) et le chemin `hors-de-la-boite` sont, eux,
exerces indirectement par les deux paquets de production (`cp-frise-de-la-journee`,
`cp-plan-de-la-classe`) a travers `LEcranDeMancheDuJeuDeDesignationReelEstServiTest` et les
garde-fous de contenu ; seul le chemin de refus est untested. A traiter avant qu'`ADR-0094` ne
passe `Accepte`.

## 5. Statut d'`ADR-0093` et d'`ADR-0094` - ce que "Done technique" couvre et ne couvre pas ce sprint

Les deux `ADR` sont `Propose`, pas `Accepte`, et le disent eux-memes avec leur propre condition de
passage (`ADR-index.md`, entrees ADR-0093 et ADR-0094). Pour `ADR-0093` : les deux clauses sont dans
l'arbre avec leurs morsures - verifie en section 3.1, les deux garde-fous existent et mordent
reellement. Pour `ADR-0094` : les quatre relais sont dans l'arbre (verifie par lecture -
`DesignationContentSourceFactory`, `ZoneADesigner`, `DesignationSession`, `PartieController`
portent tous `EmplacementDuLibelleDeZone`), **mais sa regle executable propre,
`AucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulierTest`, n'existe dans aucun module** - je l'ai
cherchee par son nom exact et par balayage, absente. La condition de passage `Accepte` qu'`ADR-0094`
s'est fixee n'est donc pas remplie, et l'`ADR` le sait et le dit. Ce n'est pas un defaut que je
signale ici : c'est l'etat honnetement declare d'un `ADR` encore `Propose`. Je le note parce que
`theo-archi` confirme ArchUnit sur ce meme arbre et que la distinction entre "les relais existent"
et "la regle qui les garde existe" doit rester lisible dans ce rapport.

**Verification independante que le decouplage tient deja, avant meme cette regle executable** :
`grep -rn "plan-classe\|plan-de-la-classe"
adapters/adapter-web/src/main/` (gabarits, feuille, Java) ne trouve aucune occurrence. Le principe
directeur du produit - une mecanique de designation generique, aucun contenu particulier nomme dans
`adapter-web` - tient sur ce sprint, sans que le garde-fou qui le figerait soit encore ecrit.

## 6. Tests instables

Aucun test instable observe. Chaque lancement cite dans ce rapport n'a ete execute qu'une fois ;
un test lance une seule fois n'est pas qualifie de stable pour autant, et ce rapport ne l'affirme
pas.

## 7. Ce que ce rapport ne fait pas

Il ne prononce aucun Done pedagogique - `nadia-prof` seule le fait. Il ne juge le niveau scolaire
d'aucun contenu (aucun contenu CE1 chiffre n'est livre ce sprint : seul un referentiel de
competences entre dans l'arbre). Il ne mesure que le job `build` de la CI, pas les autres jobs de
`.github/workflows/ci.yml`. Il ne confirme aucune `ArchRule` : c'est le mandat de `theo-archi`.
