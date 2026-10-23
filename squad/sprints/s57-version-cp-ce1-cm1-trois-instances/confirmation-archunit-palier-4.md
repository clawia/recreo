# [Sprint s57] Palier 4 - confirmation ArchUnit sur l'arbre final, compte de regles, gardes du palier, aucune garde affaiblie

- **Date** : 2026-10-23, 15:03 (UTC+02:00), lue par `date`.
- **Auteur** : `theo-archi`. Branche `s57-theo-p4-archunit`, worktree dedie coupe de `round-two` a `f3b069890b61cc57a6166078753cb6e195fb4b6a`, `git status --short` vide avant ecriture.
- **Objet** : decision de l'utilisatrice du 2026-10-23, *"on ecrit le fichier"*. Ferme l'ecart que `cloture-palier-4.md` ecrit en section 2 (*"`confirmation-archunit-palier-4.md` n'existe pas"*) et en section 9 (obstacle **ouvert**, `theo-archi`). La fermeture de l'obstacle est a `sofia-sm`.
- **Ouverture du palier 4 retenue : `eb267ba7`** (cloture du palier 3, base des sept lots : `lecture-theo-lignee-palier-4.md:13`, `cloture-palier-4.md:11`). `892ab382` (reference locale de `origin/round-two` a l'ouverture) ne differe de `eb267ba7` que par `composition-palier-4.md` (`git diff --name-only 892ab382 eb267ba7`, une ligne) : le compte y est le meme (section 2).
- **Version** : **JDK 21.0.10**, propriete `java.version` des 15 rapports `TEST-*.xml` de la mesure `M1`, une seule valeur (`ADR-0071`).
- **Modeles** : `confirmation-archunit-palier-2.md`, `confirmation-archunit-palier-3.md`.
- **Ce que ce fichier ne fait pas** : il ne modifie ni code, ni `ADR`, ni `DEC`, ni registre, ni story. Il ne prononce aucun Done, technique ou pedagogique. Il ne fusionne rien.

## 0. Verdict

| Code | Question | Verdict |
|---|---|---|
| `V1` | Les regles ArchUnit sont-elles vertes au sommet `f3b06989` ? | **oui** : 15 classes porteuses de regles, 88 tests, 0 echec, mesure partielle `-am` de ma main (`M1`) ; reacteur complet 3232 tests, 0 echec a `38a77bf9`, **repris** de `sofia-sm` (`M2`) |
| `V2` | Le compte a-t-il bouge pendant le palier ? | **non** : 41 declarations, 38 `@ArchTest`, 16 fichiers a `eb267ba7` comme a `f3b06989` ; 39 regles reelles dans 15 fichiers hors contre-exemple (section 2) |
| `V3` | Une regle ArchUnit a-t-elle ete ajoutee ou modifiee ? | **non** : aucun commit du palier ne touche un fichier de regle ni un arbre de contre-exemple d'ArchUnit (section 3) |
| `V4` | Une garde est-elle affaiblie ? | **non** sur les 16 fichiers de regles (diff vide) ; sur les trois gardes partages modifies, aucune assertion retiree, quatre lignes supprimees lues une a une (section 4) |
| `V5` | Un `ADR` a-t-il change pendant le palier ? | **non** : `git diff --name-status eb267ba7 f3b06989 -- squad/decisions/adr` ne rend rien ; `ADR-index.md` inchange |

## 1. Ce qui est mesure, ce qui est repris

| Code | Mesure | Arbre | Auteur | Resultat |
|---|---|---|---|---|
| `M1` | 15 classes ArchUnit, mesure partielle, commande ci-dessous | `f3b06989`, mon worktree, arbre propre | **moi, ce jour** | exit 0, `BUILD SUCCESS`, 15:00:02 a 15:01:59 ; **15 classes, 88 tests, 0 echec, 0 erreur, 0 ignore** |
| `M2` | `verify` complet, racine, sans `-pl` | `38a77bf9` | `sofia-sm`, `cloture-palier-4.md:148` | exit 0 ; 3232 tests, 0 echec, 0 erreur, 0 ignore. **Repris, non relance** |
| `M3` | ecart entre `38a77bf9` et `f3b06989` | les deux | **moi** | `git diff --name-only 38a77bf9 f3b06989` rend une seule ligne, `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/cloture-palier-4.md` : aucun fichier de code, de test ni de contenu |

Commande de `M1`, perimetre `bootstrap` et `adapters/adapter-web` avec leurs amonts (`-am` present), restreint a ces 15 classes :

`bash outils/mvn-jdk21.sh -B -pl bootstrap,adapters/adapter-web -am test -Dtest=AssertionsWebTest,AbstractionsCiteesTest,AccesALEtatDeSessionTest,ArchitectureEnCouchesTest,ChargementDuContenuTest,DecouplageMecaniqueContenuTest,DifficulteTest,GestionDesErreursTest,LOrdreDesMatieresVientDeLaDonneeTest,LOrdreDesNiveauxResteUneDonneeTest,LeChoixDesJeuxResteDerriereLePortTest,LeSeulCookieEstLeMarqueurDePartieTest,LesReglesMordentTest,PureteDesCouchesTest,ScanDeComposantsTest -Dsurefire.failIfNoSpecifiedTests=false`

Detail de `M1` par l'attribut `tests` des 15 `TEST-*.xml`, identique classe par classe au palier 3 (`confirmation-archunit-palier-3.md:27`) : `AssertionsWebTest` 2, `AbstractionsCiteesTest` 5, `AccesALEtatDeSessionTest` 2, `ArchitectureEnCouchesTest` 2, `ChargementDuContenuTest` 2, `DecouplageMecaniqueContenuTest` 13, `DifficulteTest` 3, `GestionDesErreursTest` 4, `LOrdreDesMatieresVientDeLaDonneeTest` 4, `LOrdreDesNiveauxResteUneDonneeTest` 5, `LeChoixDesJeuxResteDerriereLePortTest` 1, `LeSeulCookieEstLeMarqueurDePartieTest` 2, `LesReglesMordentTest` 39, `PureteDesCouchesTest` 2, `ScanDeComposantsTest` 2. Les lignes `Tests run` du journal se repartissent sur deux moteurs JUnit (2 pour `adapter-web`, 86 pour `bootstrap`) ; les rapports XML les reunissent par classe. 88 compte les `@ArchTest` et les `@Test` des classes, ce n'est pas un compte de regles.

**Pourquoi `M1` et pas seulement `M2`.** `M2` porte sur un autre commit que le sommet ; `M3` montre que le code est le meme, ce qui rend `M2` valable pour `f3b06989` par deduction. `M1` est une mesure directe du sommet, limitee aux 15 classes : elle ne remplace pas un `verify` complet de `f3b06989`, que je n'ai pas lance.

## 2. Compte de regles

**Methode d'`ADR-0104`** (`ADR-0104...md:305-308`) : lignes des `*.java` suivis par Git au commit nomme (`git grep <commit>`, ce qui exclut les worktrees d'agents et les fichiers non suivis) qui correspondent a `^\s*(public |private |protected )?static final ArchRule ` d'une part, a `^\s*@ArchTest\s*$` d'autre part, comptees par `wc -l` ; fichiers par `git grep -l` sur l'un ou l'autre motif ; contre-exemple par le meme motif restreint a `*RegleContreExemple.java`. Mesure de ma main, ce jour, sur chaque commit de la table.

| Commit | `static final ArchRule` | `@ArchTest` | Fichiers | dont `RegleContreExemple.java` (declarations, `@ArchTest`) |
|---|---|---|---|---|
| `892ab382` (reference locale de `origin/round-two` a l'ouverture) | 41 | 38 | 16 | 2, 0 |
| `eb267ba7` (ouverture retenue, cloture du palier 3) | 41 | 38 | 16 | 2, 0 |
| `a65341cb` (fusion de `s57-yanis-p4-lp3-lp5`) | 41 | 38 | 16 | 2, 0 |
| `c975a7b8` (troisieme passe de `LP6`) | 41 | 38 | 16 | 2, 0 |
| `38a77bf9` (`verify` vert de `sofia-sm`) | 41 | 38 | 16 | 2, 0 |
| `f3b06989` (sommet) | **41** | **38** | **16** | 2, 0 |
| ecart sur le palier | **0** | **0** | **0** | 0 |

Les comptes a `a65341cb` et `c975a7b8` sont egaux a ceux de mes lectures de lignee (`lecture-theo-lignee-palier-4-lp6-passe-3.md:116`) : remesures ici, pas recopies. **Regles reelles : 39 declarations dans 15 fichiers**, dont 38 executees par `@ArchTest` et une par deux `.check(...)` explicites (`ScanDeComposantsTest`) ; les 2 lignes en plus sont la classe locale nommee `ArchRule` du faux depot de contre-exemple d'`ADR-0013`. Meme lecture qu'aux paliers 2 et 3.

## 3. Regles ajoutees ou modifiees pendant le palier 4

**Aucune regle ArchUnit.** `git log --oneline eb267ba7..f3b06989` restreint a `bootstrap/src/test/java/com/recreo/archi`, `adapters/adapter-web/src/test/java/com/recreo/adapter/web/archi`, `domain/src/test/java/com/recreo/domain/archi` et `bootstrap/src/test/resources/contreexemple` rend **0** commit. `git diff --name-only eb267ba7 f3b06989 -- '*/src/main/java/*'` ne rend rien : aucune classe de production ajoutee ni modifiee, donc rien que les regles existantes auraient a juger de neuf.

**Gardes non ArchUnit ajoutes ou modifies**, liste complete des `*.java` du diff du palier (`git diff --name-status eb267ba7 f3b06989 -- '*.java'`, 10 fichiers) :

| Code | Fichier | Etat | Nature | Lecture d'architecture |
|---|---|---|---|---|
| `G1` | `ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest` | ajoute, 213 lignes (`p4-10`) | garde de balayage du catalogue, `ADR-0013` | `lecture-theo-p4-10-garde-manches.md`, **reprise** : verdict `M` vert, morsure par cinq catalogues fautifs. Le litteral `src/main/` que je demandais pour qu'il entre au recensement est present au sommet (`grep -c` : 2) |
| `G2` | `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | modifie, +18 -3 | liste d'exemptions motivees `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` | section 4 |
| `G3` | `LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest` | modifie, +4 -1 | cliquet en egalite des paquets de saisie servis, `ADR-0051` | section 4 |
| `G4` | `TexteAccentueExposeParLePortEntrantTest` (`bootstrap`) | modifie, +66 -0 | inventaire, ajouts seuls | aucune suppression, rien a affaiblir |
| `G5` | six `PaquetCe1*Test` (`CalendrierDates`, `EmcDroitDevoir`, `EmcInteretCommun`, `EmcRegleSanction`, `MassesContenances`, `MotsInvariables`) | ajoutes, 794 a 1530 lignes | tests de paquet | hors du champ de cette confirmation ; ce sont des tests d'un paquet, pas des gardes d'architecture |

Aucun de ces gardes n'est une `ArchRule` : leurs objets sont des fichiers YAML et un catalogue, pas du bytecode. Le compte de la section 2 ne bouge donc pas, ce qui est juste.

## 4. Aucune garde affaiblie

**Fichiers de regles.** `git diff --stat eb267ba7 f3b06989` restreint aux 16 fichiers de la section 2, aux trois repertoires `archi`, a `bootstrap/src/test/resources/contreexemple` et a `domain/src/test/java/com/recreo/domain` (contre-exemples de mecanique) rend **0 ligne**.

**Tout le diff `*.java` du palier** : `git diff --shortstat eb267ba7 f3b06989 -- '*.java'` rend 10 fichiers, 7615 insertions, **4 suppressions**. Aucune ligne ajoutee ne porte `@Disabled`, `@Ignore`, `ArchIgnore`, `allowEmptyShould(true)` ni `assume` (`git diff ... | grep -nE '^\+.*(...)'`, exit 1, rien). Les 4 suppressions sont lues une a une, ce que le palier 3 n'avait pas fait :

| Code | Fichier | Lignes retirees | Lecture |
|---|---|---|---|
| `S1` | `G2` | les 3 lignes du motif de l'entree `ce1-mots-invariables` | texte d'un motif, pas une assertion : le paquet passe en saisie, l'ancien motif (*"72 propositions"*) etait rouge sur le paquet servi. Mesure de ma main a `56e9fd1b`, **reprise** de `lecture-theo-lignee-palier-4.md:92-96` : exit 1 avec l'ancien texte, 1 echec, `chaqueCompteEcritDansUnMotifVautLeCompteMesureSurLePaquet` |
| `S2` | `G3` | `List.of("cp-complements-10-saisie", "cp-mot-dicte");` | remplacee par la meme liste plus `ce1-mots-invariables` : le cliquet est en egalite, le perimetre de la clause de traversee s'elargit d'un paquet, il ne se reduit pas |

**Les trois entrees ajoutees a `G2`** (`ce1-emc-regle-sanction`, `ce1-emc-droit-devoir`, `ce1-emc-interet-commun`) sont des exemptions, donc a lire comme telles. Chacune porte son motif chiffre (*"24 items ... 72 propositions"*), que la clause `chaqueCompteEcritDansUnMotifVautLeCompteMesureSurLePaquet` recompte sur le paquet servi ; une exemption perimee, fantome ou muette est rouge (`LeRang...Test.java:1916-1957` au sommet, variantes `amputee`, `enTrop`, `fantome`, `muette`). Elles sont prevues par la composition (RF3 et RF5 de `composition-palier-4.md`). **La garde reste verte au sommet** : non lancee par `M1`, elle est dans les 3232 de `M2` (repris).

## 5. `ADR` de gardes lus pour ce fichier

| `ADR` | Ce que j'y ai lu |
|---|---|
| `ADR-0104` | la methode du compte (`:305-308`) appliquee en section 2 |
| `ADR-0013` | le faux depot de contre-exemple et ses 2 lignes `ArchRule` ; le recensement des gardes de balayage (`G1`) |
| `ADR-0087` | decision et regle executable : la classe `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` juge les tables de ce fichier (section 7) |
| `ADR-0071` | la version d'une mesure est celle des rapports de la machine virtuelle des tests |

`ADR-0051` (cliquet de `G3`) et `ADR-0086` (clause 1, jugee dans les lectures de `LP6`) ne sont pas relus ici : ce que j'en dis est repris de mes lectures de lignee du palier.

## 6. Dettes d'architecture au sommet

Etat lu ce jour ; aucune n'est payee par ce fichier.

| Code | Dette | Etat a `f3b06989` | Destinataire |
|---|---|---|---|
| `DA1` | Garde generique `E3` (`confirmation-archunit-palier-3.md:85`) | **non ecrit** : `git ls-files` ne connait aucun `LesPropositionsEtLesTextesSupportsDeCe1TiennentLeursBornesTest` ; la story `S57-GARDE-CE1-PROPOSITIONS-ET-SUPPORTS` est reportee (`cloture-palier-4.md:62`) | `camille-po`, puis le developpeur engage |
| `DA6` | Recensement d'`ADR-0013` aveugle aux chemins assembles par segments (`lecture-theo-p4-10-garde-manches.md:53-58`) | **non paye** : aucun `ADR` modifie au palier (`V5`) | `theo-archi`, amendement d'`ADR-0013` avec un depot fautif tire des fichiers reels |
| `DA7` | Ecart 39 : `verifier-chemins.sh` ne voit pas la forme `X--...` (`avis-theo-palier-4.md:140`, section 5) | **non paye** : `git log eb267ba7..f3b06989 -- .github outils` rend 0 commit ; `git grep -c` du motif d'`OV6` rend toujours 2 lignes dans `releve-relecteur-cloture-palier-3.md` | `theo-archi`, amendement d'`ADR-0010` avec son cas de test |

`DA4` et `DA5` du palier 3 sont portes par des stories reportees (`S57-SAISIE-MOTS-PROPOSES-PAR-LE-CLAVIER`, `cloture-palier-4.md:62`) ou par `LP9` non engage ; leur etat n'a pas change (`V5`).

## 7. Jobs de `ci.yml` lances pour ce commit

Lances dans mon worktree, ce fichier indexe, avant le commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`, mesure partielle** : les 11 classes de `bootstrap` qui lisent `squad/` ou `docs/backlog` (`grep -rl` sur `bootstrap/src/test`, fichiers `*.java`), dont `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` (`ADR-0087`), `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` et `ToutesLesReferencesDeDecisionResolventTest`. Commande `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=<ces 11 classes> -Dsurefire.failIfNoSpecifiedTests=false`, `-am` present, 15:07:26 a 15:10:57, puis relancee sur le texte de cette section, 15:12:09 a 15:15:49 : **exit 0, 79 tests, 0 echec, 0 erreur** les deux fois. Perimetre : ces 11 classes, pas le module.
- **Tables de ce fichier** : scanner de cellules ecrit a part, hors depot (largeur de l'en-tete contre chaque ligne de corps, barres echappees exclues) : 7 tables, 29 lignes, **0 ecart**, aucun caractere hors ASCII. Le scanner a ete eprouve sur du materiau reel : sur `DEC-0120` a `ad7f8022`, il rend l'unique ecart que le `verify` de `cloture-palier-4.md:100` a trouve, ligne 2579, en-tete 4, cellules 5.
- **`chemins`** : `verifier-chemins-test.sh` exit 0 ; `git ls-files` moins le test du garde-fou, ce fichier compris, passe a `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, exit 0.
- **`prefixes`** : `verifier-prefixes-test.sh` exit 0 ; le sujet de ce commit passe a `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, exit 0.
- **Non lances** : le `verify` complet (le code de `f3b06989` est celui de `38a77bf9`, `M3`, et ce commit n'ajoute qu'un fichier sous `squad/`) ; l'etape *"Package du fat jar"* ; le job `outils`, dont ce commit ne touche aucun juge.

**Le Done technique n'est pas prononce par moi.**

## Decisions prises

**Aucune `ADR` ni `DEC` creee ni amendee par ce fichier**, `ADR-index.md` inchange. Il confirme le compte (ecart nul sur le palier 4), mesure les 15 classes ArchUnit vertes au sommet, montre qu'aucune regle n'a ete ajoutee, modifiee ni affaiblie, et nomme trois dettes avec leur destinataire.
