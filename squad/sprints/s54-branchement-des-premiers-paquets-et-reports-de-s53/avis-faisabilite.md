# [Sprint s54] Avis de faisabilite - theo-archi

- **Date** : 2026-10-07 21:05 (UTC+02:00), lu par `date`.
- **Auteur** : theo-archi. Branche `s54-theo-faisabilite`, worktree dedie, base `4bb4dbd5` (tip de `round-two`), `git status
  --short` vide avant ecriture. Le planning nommait `s54-theo-avis` ; la branche a ete ouverte sous ce nom-ci par l'agent qui
  m'a lance.
- **Perimetre** : les cinq branchements engages (`S54-BR-*`), les questions T1 a T7 du planning, et les questions que le
  lancement m'a adressees en plus (journal de tokens, `ART-CP-10`, `CRP-CM1-R1` a `R3`, paquets laisses hors catalogue). Aucun
  YAML de contenu ni aucun Java n'est modifie par ce commit.
- **Ce que ce commit change en dehors de ce fichier** : le statut d'`ADR-0104` (`Propose` devient `Accepte`, T6) et sa ligne
  d'index ; un paragraphe de `squad/README.md` section 9 (T7). Aucun `ADR` neuf.

## 1. Methode : une simulation, pas une lecture

**Worktree jetable** ouvert en `--detach` sur `4bb4dbd5`, hors de ce depot de travail, supprime apres mesure. J'y ai ajoute les cinq
entrees de catalogue, telles qu'un developpeur les ecrirait, avec des identifiants, titres et consignes de ma main (aucun n'est une
proposition de contenu : les mots sont a l'auteur et a `nadia-prof`). Consignes reprises de l'existant :
"Lis bien, puis choisis la bonne forme du verbe." (`ce1-futur`, `ce1-passe-compose`) et "Lis bien, puis choisis la bonne réponse." (les trois autres).

| Passage | Diff | Commande | Resultat |
|---|---|---|---|
| 1 | les cinq entrees seules (30 lignes de `catalogue.yaml`) | `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,bootstrap -am test -fae` ; perimetre : `domain`, `application`, `adapter-web`, `adapter-content`, `bootstrap`, phase `test` (pas de Failsafe) | **rouge sur une seule classe** : `TexteAccentueExposeParLePortEntrantTest`, 3 tests sur 5 ; les quatre autres modules verts, `adapter-content` 1 318 tests, 0 echec |
| 2 | passage 1 plus 15 chaines inventoriees (5 identifiants, 5 titres, 5 competences ; aucune consigne, toutes deja inventoriees) | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine | **`BUILD SUCCESS`**, 2 174 tests (domain 120, application 17, adapter-web 401, adapter-content 1 318, bootstrap 312 Surefire et 6 Failsafe), 0 echec, 0 erreur |

Version : **21.0.10**, propriete `java.version` des rapports `TEST-*.xml` des cinq modules, seule valeur trouvee. Comptes :
somme des attributs `tests`, `failures`, `errors` de ces rapports. Horloge : passage 1 de 20:55:28 a 20:58:32, passage 2 de
20:59:19 a 21:02:40.

**Ce que le passage 2 prouve et ce qu'il ne prouve pas.** Il prouve que les cinq paquets passent deja tous les garde-fous de
population catalogue (C) et que seul l'inventaire d'accent mord au branchement. **Le total ne bouge pas** (2 174, le meme qu'a la
cloture de s53) : un paquet branche entre dans des tests existants sans en creer. Un compte de tests ne dit donc jamais qu'un paquet
neuf a ete juge ; seul le test du paquet (T1) ajoute des tests. Il ne prouve rien sur les mots que j'ai choisis : un titre qui
porterait son niveau rougirait `AucunTitreDeJeuNePorteSonNiveauTest` (non mesure, lu), et une consigne neuve demande sa propre ligne
d'inventaire.

## 2. Avis par branchement

Commun aux cinq (mesure, section 1) : `type: question`, cles d'item `enonce`, `bonneReponse`, `distracteurs`, `indice` et rien
d'autre (comptage des cles des cinq fichiers) ; mecanique `bandit-manchot` ; deja declares dans
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` par `70b1cdc1` (seul test qui les nomme, `git grep` sur les `*.java`) ;
aucun blanc ordinaire devant `? ! : ;` hors commentaires (comptage par script), donc vert sous la regle a cliquet de `TYP-1`, qui
exige zero pour un paquet absent de sa table. `nombreDeManches: 10` tient l'inegalite stricte d'`ADR-0023` pour 24, 12 et 11 items.

| Story | Entree de catalogue | Jeu, mecanique | Test du paquet (T1) | Garde-fous qui mordent au branchement | Dev sans ADR ? |
|---|---|---|---|---|---|
| `S54-BR-FRA-CE1-18` `ce1-futur` (24 items) | 6 lignes | `bandit-manchot` | du : `FUT-CE1-1` (etre et avoir dans au moins un quart des items) n'est comptee par aucun garde-fou ; modele `PaquetCe1ConjugaisonPresentTest` | `TexteAccentueExpose...` : identifiant, titre, competence (3 chaines), consigne si neuve | **oui, faisable en l'etat** |
| `S54-BR-FRA-CE1-19` `ce1-passe-compose` (24) | 6 lignes | `bandit-manchot` | du : `PCO-CE1-3` (a), l'infinitif pour participe dans au moins un tiers des items, n'est comptee nulle part | idem | **oui** |
| `S54-BR-SCI-CM1-15` `cm1-sciences-terre-jour-nuit-saisons` (12) | 6 lignes, **identifiant et titre neufs obligatoires** | `bandit-manchot` | du : `SCI-1`, `SCI-3`, `SCI-4` ne vivent que dans les tests de paquet (9 classes, aucun garde-fou generique) ; `PaquetCm1SciencesTerreTest` (240 lignes) existe pour le paquet refuse et se recible | `TexteAccentueExpose...` ; **`LesJeuxRetiresDuCatalogueNeSontPasServisTest`** si l'entree reprend `bandit-manchot-terre-cm1` ou le titre "La Terre, le jour et les saisons" (lu, non mesure : `doesNotContain` sur les deux) | **oui**, a condition de ne pas reprendre l'identifiant ni le titre retires ; les reprendre demande que `camille-po` amende `DEC-0111` section 3 avant tout edit de ce test |
| `S54-BR-SCI-CM1-18` `cm1-sciences-environnement-activite-humaine` (12) | 6 lignes, idem | `bandit-manchot` | du, meme motif ; `PaquetCm1SciencesActiviteHumaineTest` (273 lignes) se recible | idem, identifiant `bandit-manchot-activite-humaine-cm1` et titre "L'activité humaine et la nature" interdits | **oui**, meme condition |
| `S54-BR-EMC-CM1-02` `cm1-emc-atteinte-autrui` (11) | 6 lignes | `bandit-manchot` | precedent : 8 paquets d'EMC CM1 servis, 8 tests ; `EMC-9` reste une lecture, aucun automate ne la juge | `TexteAccentueExpose...` | **oui** |

**Aucun ne demande d'`ADR`** : zero ligne de Java de production, aucune mecanique, forme d'item, cle YAML ni champ neuf.

**Fait a trancher par `camille-po`, non bloquant** : les deux paquets refuses a s51 (`cm1-sciences-terre.yaml`,
`cm1-sciences-activite-humaine.yaml`) et leurs deux tests restent dans le depot ; apres branchement, deux fichiers par ligne
`SCI-CM1-15` et `SCI-CM1-18` vivront dans `contenu/`, dont un refuse et non servi. Rien ne rougit (passage 2). Les retirer serait
un recomptage de `LeRang...` (deux motifs a retirer dans le meme commit), pas un affaiblissement.

## 3. Fichiers partages entre les deux couloirs, et ordre de fusion

| Fichier | marc-dev | lea-dev | Conflit textuel |
|---|---|---|---|
| `catalogue.yaml` | 2 entrees | 3 entrees | **mesure** : deux ajouts en fin de fichier sur deux branches de `4bb4dbd5` -> `git merge` en **conflit** ; entree de marc-dev inseree apres l'entree `paquet: ce1-conjugaison-present` (ligne 781) et celle de lea-dev en fin -> **fusion propre**, deux entrees presentes |
| `TexteAccentueExposeParLePortEntrantTest` | 3 chaines par entree | 3 par entree | meme mecanisme (une `List.of`, trois sections) ; non mesure, deduit du cas precedent |
| `AucunSigneDoubleNEstDetachableDeSonMotTest` | - | `S53-REPORT-GRANDS-NOMBRES` (T5) | aucun |
| `LeRangDeLaBonneReponse...`, `LesJeuxRetires...`, compteurs de lot | - | - | aucun edit du par un branchement |
| `cm1-sciences-corps.yaml` (`CRP`) | correction | - | aucun |

**Ancres recommandees** (lignes a `4bb4dbd5`), pour que les deux couloirs n'ecrivent jamais au meme endroit :
marc-dev apres l'entree `bandit-manchot-conjugaison-present-ce1` du catalogue (781-782) et, dans l'inventaire, apres
`"bandit-manchot-conjugaison-present-ce1"` (405), `"Les verbes au présent"` (678) et la competence `"Conjuguer au présent ..."` (1561) ;
lea-dev apres l'entree `bandit-manchot-corps-cm1` (799-800) et apres `"bandit-manchot-corps-cm1"` (348), `"Prendre soin de son corps"`
(639), `"Relier l'alimentation ..."` (1482). Ecarts entre ancres : 18 lignes au catalogue, 57 a 79 dans l'inventaire.

**Ordre de fusion** : celui du planning tient, et rien de technique ne l'impose : `CRP` ne partage aucun fichier avec un branchement.
Decisions, puis `CRP` (jeu servi, priorite pedagogique), puis marc-dev, puis lea-dev, `clean verify` sur chaque commit de fusion.
**Un seul point d'ordre est technique** : chaque commit de branchement porte son entree, ses chaines d'inventaire et son test de
paquet ensemble. Une entree sans son inventaire est rouge (passage 1) ; un inventaire sans son entree est vert et ment.

## 4. Reponses T1 a T7

**T1 - le test de paquet est-il obligatoire ?** **Par convention, pas par un garde-fou.** Aucun test ne derive "un test par paquet"
du catalogue. Mesure (script sur `catalogue.yaml` et les fichiers `Paquet*Test.java` d'`adapters/`) : 135 paquets servis, 124 nommes
entre guillemets par un test de paquet, 11 non (quatre de mathematiques CE1 tenus par des garde-fous derives de la forme, sept de CP).
**Je le tiens pour du a ces cinq**, pour un motif precis : les conditions comptables de `nadia-prof` (`FUT-CE1-1`, `PCO-CE1-3` (a),
`SCI-1`, `SCI-3`, `SCI-4`) ne sont encodees que dans des tests de paquet. Sans lui, la ligne est jugee par les garde-fous de forme seuls.
**Je n'ecris pas de regle "chaque paquet a son test"** : elle exigerait l'existence d'un fichier, pas son contenu, et un test vide la
passerait. Elle serait decorative.

**T2 - quelles listes les cinq modifient-ils encore ?** **Une seule** : l'inventaire de `TexteAccentueExposeParLePortEntrantTest`
(3 chaines par entree si la consigne existe deja, 4 sinon). Mesure en section 1. `LeRang...`, `LaValeur...`, les compteurs de lot et
les sous-parties ne bougent pas.

**T3 - un des cinq demande-t-il autre chose que `bandit-manchot` ?** **Non.** Cinq `type: question`, aucune cle `illustration`,
`texteSupport`, voix ni grille ; le passage 2 les charge et les joue sous `bandit-manchot`.

**T4 - le geste couvre-t-il `main` sans `--sprint` ?** **Oui, s'il est fait dans `ecrire_rapport_sprint` et non dans `main`.**
Mesure par `python outils/rapport-tokens.py --dry-run` sur cet arbre (rien n'est ecrit, `git status --short` inchange apres) :
**sans `--sprint`, l'outil reecrirait aujourd'hui 23 des 50 `RAPPORT-tokens.md` en "Aucune consommation mesuree sur cette fenetre."**
(s00, s01, s03, s05 a s09, s11, s13 a s19, s21, s23 a s28), qui portent tous des chiffres aujourd'hui (aucun des 50 ne contient
cette phrase) ; le journal regenere aurait 23 sections vides, contre 17 aujourd'hui (s00 a s21, deja perdues : `JOURNAL-tokens.md`
les a, les rapports par sprint les ont encore). **Prix** : `ecrire_journal`, 20 a 30 lignes (inchange depuis s53) ; `ecrire_rapport_sprint`,
la meme regle au niveau du fichier, 8 a 12 lignes (relire le rapport existant, le garder s'il porte des chiffres et que rien n'est mesure,
imprimer "conserve") ; `main` inchange. Test : `outils/rapport-tokens-journal-test.py`, six cas (les quatre de la story, un rapport
conserve, un lancement sans `--sprint` qui ne touche aucun rapport chiffre), 130 a 180 lignes sur le modele de
`rapport-tokens-worktree-test.py` (127). CI inchangee : le job `outils` lance deja `outils/*-test.py`. **Non-preuve nommee** : une
fenetre **partiellement** mesuree (transcriptions d'un seul agent effacees) est "mesuree" et ecrase des chiffres plus hauts ; ni la story
ni ce prix ne la couvrent. Pas d'`ADR`, pas d'`ArchRule` (Python, hors bytecode).

**T5 - les deux constantes suffisent-elles ?** **Oui pour le test du paquet, non pour le commit.** `startsWith` et `substring(PREFIXE.length())`
restent justes : un caractere remplace un caractere. Une divergence entre le caractere du YAML et celui des constantes ne passe pas en
silence : `chacunDesQuatreVerbes...` exige que la somme des quatre verbes vaille le total. **Mais la story oublie un garde-fou** :
`AucunSigneDoubleNEstDetachableDeSonMotTest` porte `cm1-grands-nombres` a **26**, en egalite (un fichier qui descend sous son compte est
rouge), sur un total de **516**. Les 14 enonces corriges laissent 12 occurrences, toutes dans des `indice` (lignes 15 a 126 du YAML).
**Le commit unique porte donc quatre edits** : 14 lignes de YAML, 2 constantes, `26 -> 12` et `516 -> 502` dans ce test. C'est un
resserrement de cliquet, conforme a C5. Le caractere est celui d'`ADR-0067` ; le depot sert U+00A0 devant `:` dans 251 enonces.

**T6 - statut d'`ADR-0104` et prix.** **`Accepte` par ce commit.** Sa seule condition, la reponse de `nadia-prof` sur `C5` clause 1,
est ecrite depuis le 2026-10-06 (`ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md`, 4.2 : elle ne mord pas sur une reproduction
qui constitue la proposition entiere, repli non appele). Regle (13) remesuree au passage 2 : `DecouplageMecaniqueContenuTest` 13 tests,
`LesReglesMordentTest` 38, 0 echec ; compte de regles 37 `static final ArchRule` et 36 `@ArchTest` hors contre-exemples (`git grep`, methode
d'`ADR-0104`), inchange. **Accepter n'engage ni la construction ni la ligne** (Q6, utilisatrice). **Prix reverifie, inchange** : depuis
`3ade55cb`, seuls des SVG ont ete ajoutes a l'adaptateur web de production ; `REPRODUCTIONS_SERVIES` 8, `POIDS_TOTAL_MAXIMUM` 176 474,
plafond 352 px, cliquet de `RessourcesTiercesTest` a 3 ; les trois oeuvres retirees se restaurent depuis `1db6348f^` (31 398 + 21 707 +
21 336 = 74 441 octets, `git cat-file -s`).

| Poste | Taille |
|---|---|
| T2 garde-fous 1 a 3 d'`ADR-0104` | ~0,5 j |
| T3 rendu, controleur, revelation, fin, garde-fou 4, cliquet 3 -> 4 | ~1 j, apres arbitrages de `ines-design` et `nadia-prof` |
| T4 corpus, constantes 8 -> 11 et 176 474 -> 250 915 si les paires restent | ~0,25 j |
| T5 un paquet a reproductions, **qui n'existe pas** | contenu ; `cp-arts-oeuvres-familles` ne le remplace pas (12 items, 0 `illustration`, en-tete DEGRADE qui dit ne pas fermer la ligne) |
| Contrainte | trois paires donnent au plus six items, donc `nombreDeManches` au plus 5 |
| Audit (tarif de `nadia-prof`, a elle) | 2,0, ou 2,5 sous `PAR-11` |

Developpement : **~1,75 j pour le dispositif**, hors paquet. Brancher le paquet DEGRADE texte seul coute ce que coute un des cinq
branchements ci-dessus (6 lignes, 3 ou 4 chaines, un test) ; s'il sert la ligne est une question pedagogique, pas la mienne.

**Amendement du 2026-10-08 (cloture, `theo-archi`) : le prix de T6 en tailles.** `DEC-0114` section 6 demande ce prix *"en tailles
(le depot n'a pas de tarif de developpement, `DEC-0112` section 7)"*. Les jours ci-dessus sont mon estimation, pas un tarif du depot ; le
prix qui repond a la demande est celui-ci. **Methode** : nombre de lignes des fichiers a toucher par `wc -l` sur `542c1c6e` ; tailles
d'ajout estimees sur le precedent mesure d'`ADR-0082`, T2 a T4, commit `90c36ab0` (4 fichiers, +366 -30, dont 18 lignes de gabarit de
production) et sur la taille des garde-fous existants de meme forme. Les plages sont des estimations sur precedent, pas des mesures.

| Poste | Module | Fichier (lignes a `542c1c6e`) | Production | Tests |
|---|---|---|---|---|
| T2, garde-fou 1 | `bootstrap` (test) | `LIllustrationDeContenuResoutEtAucuneNeDortTest` (907) | 0 | modifie, +1 a 2 tests, +100 a 150 lignes (precedent +144), 1 YAML de contre-exemple (20 a 33 lignes, precedents) |
| T2, garde-fou 2 | `adapter-content` ou `bootstrap` (test) | **neuf**, trois invariants de la decision 5 | 0 | 1 fichier, 4 tests (3 clauses, population non vide), 200 a 380 lignes (precedents `AucunePropositionNEstEgale...` 215, `AuPlusTroisPropositions...` 381) |
| T2, garde-fou 3 | `adapter-content` (test) | `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest` (485) | 0 | modifie, +1 test de morsure, +20 a 40 lignes |
| T3, controleur | `adapter-web` (main) | `PartieController.java` (834) | +10 a 20 lignes : booleen `propositionsEnImage`, `reponseRevelee` nulle, `reponseAttendueVisible` faux | `PartieControllerTest` (1 046, 42 `@Test`) : +3 a 5 tests, +80 a 150 lignes |
| T3, gabarit | `adapter-web` (main) | `fragments/manche.html` (205) ; `fragments/retour.html` (108) inchange, le controleur pose le booleen | +10 a 18 lignes (precedent 18) ; 0 CSS si `.image-item` est reutilisee | - |
| T3, garde-fou 4 | `bootstrap` (test) | **neuf**, `bootstrap/rendu` | 0 | 1 fichier, 5 tests (4 ecrans, nom accessible) et 1 morsure sur gabarit mute, 250 a 630 lignes (precedents du paquetage : 234 a 632) |
| T3, cliquet | `adapter-web` (test) | `RessourcesTiercesTest` (891) | 0 | 3 -> 4, 5 a 20 lignes |
| T4, corpus | `adapter-web` (main, ressources) ; `squad/corpus` | 3 JPEG restaures de `1db6348f^`, registre `oeuvres-domaine-public.yaml` (137) | 74 441 octets ; +30 lignes de registre (lignes 107 a 136 a `1db6348f^`) | 2 constantes de `ChaqueReproductionServie...Test` : 8 -> 11, 176 474 -> 250 915 |

**Totaux.** Production : **2 fichiers d'un seul module (`adapter-web`), 20 a 38 lignes**, plus 3 images et 30 lignes de registre. Tests :
**3 fichiers neufs (dont un YAML de contre-exemple), 5 modifies, 15 a 18 tests neufs, 680 a 1 400 lignes** (somme des bornes du
tableau). **Zero ligne** dans `domain`, `application`, le chargeur
d'`adapter-content`, `bootstrap` main, les POM et la CI. **Zero regle ArchUnit neuve** : la regle (13) d'`ADR-0104` est la seule que ce
chantier appelle, elle est en vigueur depuis le 2026-10-05, verte et mordue a la cloture (`confirmation-archunit.md`, section 1) ; les
garde-fous 1 a 4 jugent des YAML, des images et du HTML rendu, aucun type Java. Hors prix : le paquet (contenu, T5) et l'audit (tarif de
`nadia-prof`).

**T7 - regle du push.** **Ecrite** dans `squad/README.md` section 9, apres l'amendement de s47 : avant tout `git push`, `clean verify`
sur la racine sans `-pl` et les juges des jobs `prefixes`, `chemins`, `outils`, jobs nommes dans le rapport. **Elle n'est pas
executable** : aucun crochet de pre-push n'est installe par le depot ; ce qui la rattrape est la CI, apres le push. Je ne propose pas de
crochet : il ne s'installe pas de lui-meme sur un poste et serait donc aussi une discipline.

## 5. Questions du lancement

**`CRP-CM1-R1` a `R3` (`cm1-sciences-corps` est servi).** Le branchement ne change rien a ce poste : aucun fichier commun (section 3).
Ce que le fait d'etre servi change : la correction tombe sous les garde-fous de population C en plus de R, et passe en ligne a la fusion.
A respecter dans le commit : `PaquetCm1SciencesCorpsTest` (12 items exactement, deux distracteurs, `sci1` : un indice d'une phrase qui
ne nomme ni la bonne reponse ni "ta reponse" - c'est lui qui juge la reecriture de l'indice de l'item 3) ; `TYP-1` a zero
(`cm1-sciences-corps` est absent de la table) ; le motif de `LeRang...` ("12 items", "36 propositions"), intact tant qu'aucun item ni
distracteur n'est ajoute ou retire.

**Les 40 paquets laisses hors catalogue (dont l'item 12 de `cp-qlm-situer-la-france`).** **Aucun compteur ne casse.** Les compteurs de
lot (`UneConditionDeLot...` : QLM CP 15 fichiers et 186 items, HG CM1 6 et 143) et les motifs de `LeRang...` sont de population R :
ils comptent les fichiers et les items de `contenu/`, branches ou non. Mesure : passage 2, 40 paquets hors catalogue, vert. Un compteur
ne bouge que si un fichier ou un item est ajoute ou retire, jamais par le branchement ou son absence.

## 6. Ce que je n'ai pas mesure

- Le conflit de fusion de l'inventaire d'accent (deduit du catalogue, non rejoue).
- `LesJeuxRetires...` et `AucunTitreDeJeuNePorteSonNiveauTest` sur un identifiant ou un titre fautif : lus, non simules.
- La forme exacte des tests de paquet et leurs morsures : a leurs auteurs.
- Le correctif de `rapport-tokens.py` : prix sur lecture et sur `--dry-run`, rien n'est code.
- Le verdict de CI sur `round-two` (Q9) : non lisible depuis ce poste.

## 7. Jobs de CI lances sur ce commit

| Job | Commande | Resultat |
|---|---|---|
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh`, puis les fichiers suivis et ceux de ce commit dans `bash .github/scripts/verifier-chemins.sh` | code 0 et 0 |
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, puis le sujet de ce commit dans `verifier-prefixes.sh` | codes 0 pour les deux tests ; le dernier pas est relance apres le commit, resultat dans le rapport de la tache |
| `build` | **non lance sur cet arbre.** Lance en entier sur l'arbre de simulation (passage 2). Sur cet arbre, la seule suite qui lit `squad/` : `bash outils/mvn-jdk21.sh -B -q clean test -pl bootstrap -am '-Dtest=com/recreo/archi/**/*' -Dsurefire.failIfNoSpecifiedTests=false` (perimetre : paquetage `com.recreo.archi`, `-am` construisant les quatre modules amont), 21:07:18 a 21:08:43 | 229 tests, 0 echec, 0 erreur, JDK 21.0.10 |
| `outils` | non lance : aucun outil touche | - |

## Decisions prises

1. `ADR-0104` passe `Accepte` (condition ecrite remplie, regle remesuree) ; index mis a jour, ligne d'amendement ajoutee.
2. Regle du push ecrite au README (A8 de la retro de s53), avec ce qui ne l'execute pas.
3. Aucun `ADR` neuf : aucune regle durable ne sort des cinq branchements. La regle "un test par paquet" est ecartee comme decorative (T1).
