# Confirmation ArchUnit - s53 (`theo-archi`)

- **Auteur** : `theo-archi`. **Date** : 2026-10-07.
- **Objet** : confirmer, pour la cloture de s53, sur l'arbre ou les corrections de relecture (P1 a P11,
  `S53-REL-10`) sont fusionnees, que les regles ArchUnit sont vertes, que leur compte n'a pas bouge, que le
  diff du sprint ne viole ni n'affaiblit aucune regle d'architecture, et rendre l'etat du poste de journal de
  tokens que `DEC-0112` section 5.5 m'a demande de chiffrer.
- **Arbre juge** : `round-two` a **`54953faa`**, mesure dans un worktree dedie ouvert a ce commit (branche
  `s53-theo-archunit`), `git status --short` vide avant la mesure. **Base de comparaison** : `9d44d510`
  (cloture de s52), ancetre de `54953faa` (`git merge-base --is-ancestor`, code 0).
- **Version** : **JDK 21.0.10**, telle que la machine virtuelle des tests la rapporte (`ADR-0071`) :
  propriete `java.version` des 278 rapports `TEST-*.xml` des cinq modules (Surefire et Failsafe), valeur
  `21.0.10` dans les 278, aucune autre.

---

## 0. Verdict

| Question | Verdict |
|---|---|
| Les regles ArchUnit sont-elles vertes ? | **oui** : reacteur complet `BUILD SUCCESS`, 229 tests dans `com.recreo.archi`, 0 echec, 0 erreur, 0 ignore (section 1) |
| Le compte de regles a-t-il change pendant s53 ? | **non** : 39 / 36 contre-exemples compris, 37 / 36 exclus, a `9d44d510` comme a `54953faa` ; diff du paquetage `com.recreo.archi` vide (section 2) |
| Le diff `9d44d510..54953faa` viole-t-il ou affaiblit-il une regle d'architecture ? | **non** : 0 ligne de `src/main/java`, aucun fichier de `domain` ni d'`application`, 4 tests d'`adapter-content` modifies, chacun un constat exact a egalite dans les deux sens, aucun plafond ni exemption ouverte (section 3) |
| Les cinq echecs de `bootstrap` constates a la base du sprint ont-ils ete leves sans affaiblir leur garde-fou ? | **oui** : verts par correction de contenu, aucun test de `bootstrap` modifie (section 3.3) |
| Une regle durable est-elle a ecrire ? | **non**, ni pour P10 ni pour le reste du diff (section 4) |
| Le journal de tokens (`S51-OUTIL-JOURNAL-TOKENS`) est-il chiffre et livre ? | **chiffre ici, pas dans mon avis ; non livre a s53** (section 5) |

## 1. Le reacteur complet

```
bash outils/mvn-jdk21.sh -B clean verify
```

Lance le 2026-10-07 a 14:45:35+02:00, termine a 14:52:22+02:00, code de sortie 0 : **`BUILD SUCCESS`**, six
modules du reacteur en `SUCCESS`. Premiere ligne de l'enveloppe : *"Verdict rendu sur le fichier de
toolchains que Maven lit, qui n'est pas celui de ce shell."*, puis *"Maven lance sur le JDK de la toolchain
jdk 21 : 21.0.10"* (meme situation de poste que la section 6 de la confirmation de s52). Le fat jar
`bootstrap/target/recreo.jar` est produit par le `repackage` de la meme commande.

**Comptes**, methode : somme des attributs `tests`, `failures`, `errors`, `skipped` des rapports `TEST-*.xml`
de chaque module, apres la commande ci-dessus.

| Module | Rapports | Tests | Echecs | Erreurs | Ignores |
|---|---|---|---|---|---|
| `domain` (Surefire) | 11 | 120 | 0 | 0 | 0 |
| `application` (Surefire) | 2 | 17 | 0 | 0 | 0 |
| `adapter-web` (Surefire) | 48 | 401 | 0 | 0 | 0 |
| `adapter-content` (Surefire) | 163 | 1 318 | 0 | 0 | 0 |
| `bootstrap` (Surefire) | 50 | 312 | 0 | 0 | 0 |
| `bootstrap` (Failsafe) | 4 | 6 | 0 | 0 | 0 |
| dont **`com.recreo.archi`** | 32 | **229** | 0 | 0 | 0 |

**Ecarts avec s52, expliques.** `com.recreo.archi` : 229 a la cloture de s52, 229 ici. `bootstrap` : 310 a
s52, 312 ici ; les +2 sont les deux tests de `UnePartieOuverteSurUnJeuRetireNeServeAucunResultatTest`, ajoutee
entre `40daa419` et `9d44d510` (`git diff --stat 40daa419 54953faa -- bootstrap/src/test` : ce fichier et une
ligne d'un autre), donc avant la base de s53 ; `git diff --stat 9d44d510 54953faa -- bootstrap` est vide.
`adapter-content` : 1 318 a s52 et ici, les 45 paquets neufs entrant dans des tests existants sans en creer.

## 2. Le compte de regles

Methode **`ADR-0104`**, inchangee : lignes des `*.java` suivis correspondant a
`^\s*(public |private |protected )?static final ArchRule ` et a `^\s*@ArchTest\s*$`, par `git grep` sur
l'arbre de Git du commit, contre-exemples compris puis exclus par `':!**/contreexemple/**'`. `target/` et
worktrees exclus par construction.

| Commit | compris | exclus |
|---|---|---|
| `9d44d510` (cloture s52) | 39 / 36 | 37 / 36 |
| **`54953faa`** (tete s53) | **39 / 36** | **37 / 36** |

Lecture : `static final ArchRule` / `@ArchTest`. `git diff --stat 9d44d510 54953faa -- bootstrap/src/test/java/com/recreo/archi`
est vide, 32 fichiers suivis dans ce paquetage aux deux commits. **Aucune regle ajoutee, retiree ni modifiee.**

## 3. Le diff du sprint

```
git diff --stat 9d44d510 54953faa
```

59 fichiers, 8 097 lignes ajoutees, 10 retirees. Hors `squad/` et `docs/` :

- **45 fichiers de paquet ajoutes** sous `adapters/adapter-content/src/main/resources/contenu`, tous `A`,
  aucun `M` ni `D` (`git diff --name-status`). `catalogue.yaml` n'est pas touche : **aucun branchement**,
  conforme a `DEC-0112`.
- **4 tests modifies**, tous dans `adapters/adapter-content/src/test/java/com/recreo/adapter/content` (section 3.2).
- `git diff --numstat 9d44d510 54953faa -- domain application '*/src/main/java/**' '*pom.xml' '*/templates/**' '.github/**' 'outils/**'`
  ne rend que les 4 tests ci-dessus : **0 ligne de `src/main/java`** dans aucun module, aucun `pom.xml`,
  gabarit, workflow ni outil. Lignes ajoutees contenant `springframework` dans le diff Java : 0.

### 3.1 Clean Architecture

Aucun fichier de production Java ne change : les regles de couches, d'independance du domaine et
d'isolement des mecaniques ne peuvent pas etre contournees par ce diff, et elles sont parmi les 229 tests
verts de la section 1.

### 3.2 Les entrees de test de `70b1cdc1` et apres

Deux commits touchent du Java : `70b1cdc1` (`sofia-sm`, declaration des 45 paquets) et `9811dfd1`
(`lea-dev`, P10). Pour chacun des quatre fichiers, la question est : la declaration ajoutee laisse-t-elle
passer quelque chose que le garde-fou refusait ?

| Test | Changement | Nature du garde-fou | Affaiblit ? |
|---|---|---|---|
| `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | 41 entrees ajoutees a `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` (les 45 moins les 4 paquets numeriques), chacune avec ses comptes d'items et de propositions ; `9811dfd1` passe `cp-qlm-situer-la-france` de 35 a 36 propositions et retire la parenthese du distracteur unique | constat exact dans les deux sens : `aucunPaquetNEstEcarteEnSilenceDuCalculDeRang` rougit un paquet hors domaine non declare **et** un paquet declare a tort ; `chaqueCompteEcritDansUnMotifVautLeCompteMesureSurLePaquet` recompte chaque motif sur le chargeur reel | **non**. Un paquet a propositions textuelles est hors du domaine par construction (`valeurDe` rend `null`) : la declaration est forcee, pas accordee. Une proposition numerique qui y entrerait rougirait la declaration |
| `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` | 4 entrees ajoutees a `PAQUETS_A_ITEMS_ECARTES` : `ce1-nombres-jusqu-a-1000` 7, `ce1-masses-contenances` 18, `ce1-calendrier-dates` 18, `ce1-figures-planes` 10 | constat exact (`containsExactlyInAnyOrderEntriesOf`), sans plafond, Javadoc : *"un paquet declare qui ne melange plus est rouge, un nombre faux est rouge"* | **non**. Voir la limite nommee ci-dessous |
| `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest` | lot QLM CP 12 vers 15 fichiers, 148 vers 186 items ; lot HG CM1 4 vers 6 fichiers, 115 vers 143 items | egalites exactes (`hasSize`, `isEqualTo`) | **non** : recomptage d'une population qui a grandi, au meme niveau d'exigence |
| `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` | 5 entrees ajoutees a l'enum des sous-parties (`MAT-CE1-01`, `-10`, `-11`, `-14`, `-15`), libelles copies du referentiel | comparaison de la competence **exacte** du paquet a l'entree | **non** : chaque paquet neuf est soumis a la meme comparaison que les anciens |

**Limite nommee, pas un affaiblissement.** Les items ecartes de `PAQUETS_A_ITEMS_ECARTES` ne sont gardes par
aucun garde-fou de rang automatique ; la Javadoc de la classe le dit et nomme la relecture de `nadia-prof`
comme ce qui les garde. Pour les quatre paquets neufs, cela fait 53 items (7 + 18 + 18 + 10) hors du calcul
de rang, dont 18 sur 21 pour `ce1-masses-contenances` (items comptes par `grep -c "bonneReponse:"` sur le
fichier). Ces paquets ne sont pas branches et n'ont pas eu cette relecture : le jour de leur branchement,
le taux de rang affiche pour eux portera sur une minorite de leurs items. Les six entrees anterieures ont
chacune un paragraphe de motif date dans la Javadoc ; les quatre neuves n'en ont pas. Ce n'est pas une
condition de verdict (le test juge l'egalite, pas la prose), c'est un fait a lire au branchement.

**Prose non gardee, deja signalee.** Le motif de `cp-qlm-vocabulaire-de-position` dit encore *"certains
items portent un seul distracteur"* ; le fichier en porte **un**
(`awk '/distracteurs: \["[^"]*"\]$/'` rend 1 ligne). Le compte de 41 propositions est exact (test vert).
C'est l'imprecision que mon avis (section 3, point 1) laissait a l'auteur du prochain commit sur cette
entree ; inchangee, sans effet sur aucun verdict.

### 3.3 Les cinq echecs de la base

Mon avis (section 1) constatait cinq echecs dans trois classes de `bootstrap` sur `5c1353a9`. Ici :

| Classe | Tests | Echecs |
|---|---|---|
| `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` | 7 | 0 |
| `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` | 8 | 0 |
| `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` | 9 | 0 |

Aucun de ces trois fichiers n'est dans le diff : ils sont verts parce que le contenu a ete corrige
(`32262d3b`, `c2531df6`, `90d3532c`, story `S53-REL-10`), pas parce qu'une exemption a ete ouverte. Leurs
plafonds restent a 0.

## 4. Regle durable

**Aucune.** Pour P10, ma conclusion de l'avis (section 3) tient : une regle *"au moins deux distracteurs"*
prononcerait un verdict pedagogique, une regle *"un seul distracteur autorise"* n'interdirait rien.
L'issue retenue confirme qu'aucun garde-fou n'avait a bouger : `cp-qlm-situer-la-france` a recu un
troisieme element avec son recomptage dans le meme commit (`9811dfd1`), `cp-qlm-vocabulaire-de-position`
garde un item a un distracteur, et la suite est verte dans les deux cas.

Pour le reste du diff : aucun fichier de production, aucune decision de structure. L'episode de la base
rouge (des garde-fous de population repertoire qui jugent des paquets non branches) est le comportement
voulu par `ADR-0013` obligation 1, et il a fait ce pour quoi il existe : obliger a corriger avant de
brancher. Aucun `ADR` n'est ouvert.

## 5. `S51-OUTIL-JOURNAL-TOKENS` : l'engagement de `DEC-0112` section 5.5

**Etat.** `DEC-0112` engageait le poste a s53 **en exigeant son prix dans mon avis de faisabilite**. Mon avis
(`76b0c779`) ne le contient pas : c'est un manque de ma part, pas un choix. Aucun commit de s53 ne touche
`outils/` (`git diff --stat 9d44d510 54953faa -- outils` vide) et le planning ne l'affecte a personne : **le
poste n'est pas livre a s53**.

**Prix**, sur lecture de `outils/rapport-tokens.py` a `54953faa` :

- **Correctif** : dans `ecrire_journal`, relire le `JOURNAL-tokens.md` existant, le decouper par titre
  `## Sprint sNN`, conserver la section d'un sprint quand le calcul ne mesure rien et que la section existante
  porte des chiffres, et imprimer la liste des sprints conserves. Ordre de grandeur : 20 a 30 lignes dans une
  seule fonction.
- **Test** : un fichier `outils/rapport-tokens-journal-test.py` sur le modele des deux existants
  (`rapport-tokens-horodatage-test.py`, 84 lignes ; `rapport-tokens-worktree-test.py`, 127 lignes), quatre
  cas, un par critere de la story.
- **CI** : aucun changement. Le job `outils` execute deja `outils/*-test.py` par motif.
- **Architecture** : aucun `ADR`, aucune `ArchRule` (outil Python, hors du bytecode qu'ArchUnit lit) ; le
  mecanisme automatique est le test d'outil du job `outils`.

Le depot n'a pas de tarif de developpement (`DEC-0112` section 7) : je le chiffre en taille, **un lot outil
d'une fonction et d'un fichier de test**, du meme ordre que chacun des deux correctifs de `rapport-tokens.py`
deja commites. **Ce prix n'est pas trop lourd pour s53** ; ce qui manque n'est pas la capacite, c'est un
proprietaire, et le sprint est a sa cloture.

**Fait de perimetre pour `camille-po`.** `ecrire_rapport_sprint` a le meme defaut (ligne 353 : *"Aucune
consommation mesuree sur cette fenetre."*), et `main` sans `--sprint` reecrit le `RAPPORT-tokens.md` de
**chaque** sprint (lignes 462 a 464). Les rapports de s22 a s27 portent des chiffres (`grep -c "Aucune
consommation mesuree"` rend 0 pour les six). Les criteres de la story ne visent que le journal : livree telle
quelle, elle laisserait la meme perte possible sur ces six fichiers. Etendre ou non les criteres est a
`camille-po` ; le prix ci-dessus double au plus le correctif (meme logique, une seconde fonction) et ajoute
deux cas au meme fichier de test.

**Ce que je ne fais pas.** Je ne reporte pas ce poste : le reporter est une decision que `DEC-0112` interdit
de prendre en silence au quatrieme sprint. Je le route a `sofia-sm` pour la cloture de s53 (le poste engage
est a ecrire non livre dans la review et la retro) et a `camille-po` pour la `DEC` d'ouverture de s54, qui
l'engage avec ce prix et un proprietaire, ou le retire avec motif.

## 6. Jobs de CI lances sur cet arbre

Les quatre jobs de `.github/workflows/ci.yml`, lances depuis ce worktree :

| Job | Commande | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine (section 1), qui produit aussi le fat jar du pas `package` | **`BUILD SUCCESS`**, chiffres en section 1 |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | code 0 et 0 ; *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | les trois motifs du job : 5 `outils/*-test.sh`, 2 `squad/outils/*/*-test.sh`, 2 `outils/*-test.py` | code 0 pour les 9 |
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, puis `git log --no-merges --format=%s round-two..HEAD \| bash .github/scripts/verifier-prefixes.sh` | code 0 pour les trois ; relance a prevoir sur le commit de ce fichier |

Ce fichier a ete ecrit apres la mesure ; il ne touche que `squad/`, que la suite de `com.recreo.archi` lit.
Relances sur l'arbre final, ce fichier en place : `chemins` avec ce fichier ajoute a la liste, code 0 ; la
suite de `com.recreo.archi` par
`bash outils/mvn-jdk21.sh -B clean test -pl bootstrap -am '-Dtest=com/recreo/archi/**/*' -Dsurefire.failIfNoSpecifiedTests=false`
(perimetre : le seul paquetage `com.recreo.archi`, `-am` construisant les quatre modules dont `bootstrap`
depend depuis ce worktree), 14:57:33 a 14:59:42 : `BUILD SUCCESS`, 229 tests, 0 echec, 0 erreur, 0 ignore.

## 7. Ce que cette confirmation ne fait pas

- Elle ne prononce aucun Done pedagogique et ne ferme aucune ligne de `MATRICE-couverture.md`.
- Elle n'ouvre aucune regle ni dette, ne modifie aucun test, contenu ni outil, n'alloue aucun identifiant.
- Elle ne tranche pas le statut d'`ADR-0104` (`DEC-0112` section 9), qui reste a faire.

## Decisions prises

Aucune. Le prix du journal de tokens (section 5) est une mesure due par `DEC-0112`, pas une decision.
