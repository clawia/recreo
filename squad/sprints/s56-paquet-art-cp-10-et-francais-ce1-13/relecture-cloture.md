# Relecture de cloture s56 - relecteur-pedagogique

- **Auteur** : `relecteur-pedagogique`
- **Date** : 2026-10-11
- **Arbre** : worktree `s56-releve`, branche issue de `round-two` a `7130dec6` (`git log -1 --format=%h` au depart), `git status --short` vide.
- **Plage relue** : `git diff --stat 4cd44098..7130dec6` (92 fichiers). `4cd44098` est le dernier commit avant le planning de s56 (`491ec8dd`).
- **Aucune logique de code editee. Aucun texte lu par l'enfant modifie. Aucun push, rebase ni fusion. Aucun Done prononce.**
- **Modele de ce fichier** : `squad/sprints/s55-francais-ce1-emc-cm1-et-reserves-servies/relecture-cloture.md`.

## 1. A12 : ponctuation des 12 indices de `cm1-grands-nombres`

**Rien a corriger.** `adapters/adapter-content/src/main/resources/contenu/cm1-grands-nombres.yaml`, mesure sur `7130dec6` :

| Commande | Resultat |
|---|---|
| `grep -cP '^ *indice:' <fichier>` | 28 |
| `grep -cP '^ *indice: ".* [:;!?]' <fichier>` (espace ordinaire avant un signe double) | 0 |
| `grep -cP '^ *indice: ".*\x{00A0}[:;!?]' <fichier>` (espace insecable) | 12 |
| `grep -nP '^ *indice: ".*[^\x{00A0} ][:;!?]' <fichier>` (signe double colle au mot) | aucune ligne |

Contre-verification par un script Python hors depot (lecture UTF-8, `re.search` sur le texte qui suit `indice:`) : 28 indices, 0 avec espace ordinaire, 12 avec espace insecable, 0 avec signe colle. Les 12 indices concernes sont ceux des lignes 15, 19, 23, 27, 35, 60, 73, 81, 85, 110, 118 et 126. Les 16 autres n'ont pas de signe double. Le fichier n'est pas modifie par s56
(`git diff 4cd44098..7130dec6 -- <fichier>` vide). Orthographe et accents des 28 indices relus : aucune faute. Les apostrophes autour de `mille` sont droites.

## 2. Texte lu par l'enfant ajoute a s56 : orthographe, accents, ponctuation

Seules ces trois choses sont de mon ressort ; le vocabulaire, le ton et le fond sont a `nadia-prof`.

| Fichier | Lignes ajoutees relues | Resultat |
|---|---|---|
| `cm1-emc-respect-differences.yaml` (`EMC-CM1-R8`) | 2 indices (items 10 et 11) | aucune faute, aucun signe double |
| `cm1-emc-principes-republique.yaml` (`EMC-CM1-R9`) | 1 indice (item 3) et le commentaire d'en-tete | aucune faute |
| `ce1-verbe-sujet.yaml` | 5 `enonce`, 5 jeux de `distracteurs`, 5 `indice`, 10 `bonneReponse` en minuscule, 2 lignes d'en-tete | aucune faute ; accents (`prête`, `Inès`, `Noé`, `écrit`) presents |
| `ce1-accord-sujet-verbe.yaml` (`ACV-CE1-R2`) | 3 `enonce`, 3 jeux de `distracteurs`, 2 `indice` | aucune faute |
| `ce1-classes-de-mots.yaml` (`CLM-CE1-R1`) | 24 `indice` | aucune faute |
| `cp-rapprocher-deux-oeuvres.yaml`, `catalogue.yaml` (entree du paquet) | `enonce`, indices, explications, titre, consigne, competence | aucune faute ; `œ` present partout |

Signe double : `grep -nP '[^\x{00A0}] [:;!?]'` sur les lignes `enonce`, `indice`, `bonneReponse` et `distracteurs` ajoutees dans les cinq YAML de la plage : 0 resultat.
Un signe double precede d'une espace ordinaire aurait fait rougir `AucunSigneDoubleNEstDetachableDeSonMotTest`. Les chevrons des citations (`« chanter »`) portent une espace ordinaire, comme tout le contenu existant : pas un ecart de s56.

## 3. Defauts corriges (texte seul)

Un commit `[REL]` (`git log --grep='^\[REL\]' --format='%h %s' 7130dec6..HEAD`).

| # | Fichier | Defaut | Correction | Preuve |
|---|---|---|---|---|
| C1 | `avis-faisabilite.md`, section 5, tableau des reproductions restaurables | Le tableau liste 19 reproductions, la phrase qui le suit dit "Somme des vingt : 516 335 octets". Il manquait `oeuvre-vitrail`, ligne absente, d'ou une somme de colonne de 485 661 | Ligne `oeuvre-vitrail` (30 674 octets) ajoutee en fin de la colonne de droite | `git ls-tree -l 1db6348f^ adapters/adapter-web/src/main/resources/static/oeuvres/` : 28 fichiers, dont 8 servis aujourd'hui, donc 20 restaurables ; 485 661 + 30 674 = 516 335, la valeur du texte |
| C2 | `planning.md`, section "Provenance", ligne du poste neuf | "Un poste ne d'un verdict" : `ne` se lit comme une negation, le sens est "issu de" | "Un poste issu d'un verdict" | relecture de la ligne |
| C3 | `ADR-0026`, amendement du 2026-10-09, paragraphe "Regle executable" | "Neuf : `laClause3Admet...`" : "neuf" se lit comme le nombre | "Test neuf : `laClause3Admet...`" | l'amendement d'`ADR-0024` ecrit "Deux tests neufs" ; la table de morsures de `ADR-0026` compte 28 tests, soit les 27 d'avant plus celui-ci |
| C4 | `rapport-lea-dev.md`, section 13.4, point F1 | "et assert `hasSize(...)`" : verbe inexistant | "et asserte `hasSize(...)`" ; la meme phrase ecrit deja "assertait" | relecture de la ligne |

## 4. Points a trancher ou a verifier, routes sans reecriture

| # | Fichier | Constat | Route |
|---|---|---|---|
| P1 | `DEC-0116` A.7 et `DEC-0114` lignes 13, 52, 79 | `camille-po` demande de distinguer une attribution d'une mention (`DEC-0062` section 4). **Les trois lignes sont des mentions de la forme**, pas des attributions : la ligne 13 resume le statut de la `DEC`, la ligne 52 titre la borne de la forme 3, la ligne 79 renvoie a la section 7. L'attribution de `DEC-0114` est en 0.1 (ligne 31) : "recus de MASTER, sans source dans le depot, le 2026-10-07", les quatre messages cites entre guillemets, date presente. **La formule y est au pluriel** ("recus"), donc absente de la formule fixe `recu de MASTER, sans source dans le depot` : c'est ce qu'un test de `theo-archi` compterait comme 0 occurrence. Aucune des trois lignes n'est un ecart de provenance ; le pluriel est un ecart de formule, que je ne reecris pas dans le document d'un autre agent | `sofia-sm` (retro de s56) et `camille-po` : le pluriel de 0.1 vaut-il la formule ? `DEC-0062` section 7 interdit le balayage retroactif |
| P2 | `ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md`, section 11, ligne `S56-BR-FRA-CE1-13` | "`TRV-CE1-5` avec la barre de s54 (1.2)" : ce document n'a pas de section 1.2 (la section 1 n'a pas de sous-section). Renvoi qui ne resout pas | `nadia-prof` : dire quel document et quelle section la porte |
| P3 | `planning.md`, sections "Ordre de fusion" et "Porte technique" | Le total 2307 est cite deux fois : "reacteur de `bootstrap` avec `-am`, a `c986adcf`" (mesure de `DEC-0116` 0.3), puis "reacteur complet ... il egale la mesure de `DEC-0116` 0.3 (meme perimetre)". Les deux perimetres sont dits differents puis identiques. Les comptes par module sont les memes ; la phrase "meme perimetre" n'est pas demontree | `sofia-sm` : dire que le reacteur de `bootstrap` avec `-am` contient les cinq modules, ou retirer "meme perimetre" |
| P4 | `proposition-ines-forme-art-cp-10.md` section 1.4 et `rapport-lea-dev.md` section 11.4 | `marque-echec.svg` : "295 octets" d'un cote, "296 octets" de l'autre, sans commande ni cause. `git cat-file -s HEAD:<chemin>` rend 295 et `wc -c` sur le poste rend 296 | `ines-design` et `lea-dev` : nommer la commande de chaque chiffre ; l'ecart tient aux fins de ligne du poste (`core.autocrlf`), je ne l'ai pas etabli par une seconde mesure |
| P5 | `CHOIX-OEUVRES-s56-art-cp-10.md` ligne 12 et `planning.md` C7 | Le document des oeuvres applique "1 a 5" des regles de `ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md` 3.4 ; le planning (C7) et `DEC-0116` 3.2 en citent six. La section 3.4 en compte six ; la sixieme (la visibilite a 176 px) ne se juge qu'a l'ecran, ce qui explique sans doute "1 a 5" | `nadia-prof` : confirmer que "1 a 5" est voulu |

## 5. Verifications sans ecart

- **Identifiants** : `RDO-CP-R1`, `RDO-CP-R2` et `VSJ-CE1-R1` ont chacun une ligne dans `squad/decisions/REGISTRE-identifiants.md` (lignes 2322, 2323, 2272), pointant vers la fiche qui les definit. Chacun apparait dans cette fiche
  (`git grep -c`). `git grep -ohE "\b(RDO|VSJ)-[A-Z0-9-]+"` ne rend aucune autre forme (`RDO-CP`, `VSJ-CE1` sont des prefixes). Le compte "933 / 159" de la ligne d'etat est celui que `nadia-prof` dit avoir lu sur le garde-fou, non relance par moi.
- **Caracteres** : aucun tiret cadratin, guillemet typographique, emoji ni symbole decoratif dans les lignes ajoutees des `.md`, `.java`, `.sh`, `.js`, `.mjs`, `.html` et `.css` de la plage (balayage `grep -P` sur U+2013, U+2014, U+2018, U+2019, U+201C, U+201D, U+2026, U+2190, U+2192, U+2713, U+2717 et les plages d'emoji).
  Les caracteres accentues et `œ` restent dans les textes francais.
- **Chemins de poste, noms de compte** : `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh` : "Aucun chemin de poste de travail ni nom de compte".
- **References** : chaque nom de fichier cite entre accents graves dans les lignes ajoutees des 30 `.md` de la plage resout vers un fichier suivi, sauf les cas suivants, tous voulus : `lessons.md` (dit absent par le planning), `contenu/cp-sim-adr0024.yaml` (fichier retire apres mesure, dit par le backlog),
  les chemins d'URL de `rapport-lea-dev.md` (`/css/recreo.css`, `/images/...`, `/oeuvres/...`), `bootstrap/target/recreo.jar` (artefact de build), `.../contenu/ce1-verbe-sujet-fautif.yaml` (chemin abrege). Les 31 `ADR-NNNN` et `DEC-NNNN` distincts cites dans ces lignes ont un fichier. Les hashes cites entre accents graves dans les `.md` de la plage resolvent par `git cat-file -e`, sauf `066f857`, `c2c82ac` et `dcbcd65a`, qui ne sont pas dans des lignes ajoutees par s56.
- **Provenance, formes de `DEC-0062`** : aucune attribution de forme 3 neuve dans la plage (`grep` de "MASTER" et "forme 3" sur les lignes ajoutees : seulement des mentions de la forme et la question A14). Les mots de l'utilisatrice cites (`"O3 avec ecart 0,25"`, `"lea-dev"`, `"Ecart de 0,5"`, `"Oui, a 4,75"`,
  `"Reporter a s57, abandon ou placement ecrit alors"`) portent la mention `de l'utilisatrice`, les mots entre guillemets et la date ; `DEC-0116` 0.1 dit qu'ils lui viennent de l'agent lanceur et qu'elle ne les a pas lus de sa main. Aucun enonce recu de MASTER ne sert de motif.
- **Arithmetique relue** : capacite de `DEC-0116` 2.1 (4,0 + 4,25, 8,5 avec la forme), planning (3,5 + 4,75 = 8,25), arbitrage B section 0 (3,25 + 0,25 = 3,5), cloture de `nadia-prof` (5,75 = 2,5 + 3,25), tests (2307, 2332, 2343, 2354, 2381, 2383, 2386, 2403 : chaque delta de `rapport-lea-dev.md` et de `confirmation-archunit.md` recompte),
  cotes de pli de `Q7` (ecarts au pli de 551), 485 661 et 516 335 octets. Seul C1 ne tenait pas.
- **Tableaux Markdown** : la ligne ajoutee en C1 compte quatre cellules comme son en-tete (`ADR-0087`).

## 6. Ce que je n'ai pas verifie

- Le sens pedagogique, le choix des mots et les verdicts : `nadia-prof`. Un exemple vu en passant, laisse a elle : l'indice "Ces élèves donnent pour aider d'autres enfants." (`EMC-CM1-R9`) est grammatical mais n'a pas de complement a `donnent`.
- `docs/backlog/backlog.md` et les sept fiches de conformite de la plage : balayages mecaniques (caracteres, references, identifiants) seulement, pas de relecture integrale. `lis-items-mesures.js`, `pilote-q7.mjs` et les pages HTML de mesure : balayage de caracteres et de chemins.
- Les commandes citees par les autres agents (`grep`, `awk`, `git log`, `mvn`) : relues, pas relancees, sauf celles de la section 1, les chiffres recomptes de la section 5 et le `git ls-tree` de C1.
- Le fond technique des garde-fous, des mutations et des mesures de rendu : `theo-archi`, `yanis-qa`, `ines-design`.

## 7. Mesure

Commande, lancee a la racine du worktree avec les quatre corrections de la section 3 et ce fichier : `bash outils/mvn-jdk21.sh -B clean verify` (reacteur complet, sans `-pl`). `BUILD SUCCESS`, code de sortie 0. L'enveloppe rapporte le JDK 21.0.10 ;
je n'ai pas relu la version de la machine virtuelle des tests dans les `TEST-*.xml` (`ADR-0071`).

```
Tests run: 120, Failures: 0, Errors: 0, Skipped: 0   (recreo-domain)
Tests run: 18, Failures: 0, Errors: 0, Skipped: 0    (recreo-application)
Tests run: 442, Failures: 0, Errors: 0, Skipped: 0   (recreo-adapter-web)
Tests run: 1470, Failures: 0, Errors: 0, Skipped: 0  (recreo-adapter-content)
Tests run: 348, Failures: 0, Errors: 0, Skipped: 0   (recreo-bootstrap)
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0     (Failsafe, bootstrap)
```

Total 2404. `confirmation-archunit.md` compte 2403 sur `80c06e64` ; l'ecart de 1 est `bootstrap` (347 puis 348), soit le test ajoute par `7b315f36` (`rapport-lea-dev.md` 13.4, F1), arrive apres cette confirmation.

Jobs de `.github/workflows/ci.yml` lances par moi, sur l'arbre qui porte ce fichier :

- `build` : `mvn -B clean verify` ci-dessus. L'etape "Verifier la presence du build Maven" et l'etape "Package du fat jar" ne sont pas lancees par moi.
- `chemins` : `verifier-chemins-test.sh` (0) et `verifier-chemins.sh` sur `git ls-files` hors le test, plus ce fichier (0).
- `prefixes` : `verifier-prefixes-test.sh` (0), `commit-atteignable-test.sh` (0) ; `verifier-prefixes.sh` sur les sujets de `7130dec6..HEAD` : voir le message de livraison.
- `outils` : non lance, aucun outil touche.

Le workflow lui-meme n'a pas tourne sur GitHub. Aucun Done n'est prononce ici.

## Decisions prises

Aucune decision de produit ni d'architecture. Quatre corrections de texte (section 3) et cinq points routes (section 4).
