# [Sprint s57] Releve du relecteur pedagogique - palier 1

- **Auteur** : `relecteur-pedagogique`, 2026-10-14. Arbre : worktree `recreo-s57-releve-releveur`, branche `s57-relecteur`, issue de `round-two` a `f95736b5`.
- **Perimetre relu** : les 12 enonces de `cm1-sciences-extraire-information.yaml` ; les paquets `ce1-phrases-types`, `ce1-accord-groupe-nominal` et `ce1-verbe-sujet` (24 enonces chacun) ; les fichiers de `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/` ; les fiches `FICHE-bandit-manchot-phrases-types-ce1.md`, `-accord-groupe-nominal-ce1.md`, `-verbe-sujet-ce1.md`, `-sciences-extraire-information-cm1.md`.
- **Aucune logique de code editee. Aucun texte lu par l'enfant modifie. Aucun push, aucune fusion. Aucun Done prononce.**
- **Convention de numerotation des lignes** : numeros de ligne sur l'arbre apres mes corrections.

## 1. Texte lu par l'enfant : orthographe, accents, ponctuation

Controle systematique (`DEC-0002` amendement du 2026-08-04, `DEC-0056`). Mesure par script Python hors depot (lecture UTF-8, valeurs entre guillemets des cles `enonce`, `indice`, `bonneReponse`, `distracteurs`, `texteSupport`, commentaires exclus).

| Fichier | Signes doubles (`?` `!` `:` `;`) | Dont precedes d'une espace insecable | Chevrons `«` `»` | Dont colles par une espace insecable | Apostrophe ou guillemet typographique |
|---|---|---|---|---|---|
| `cm1-sciences-extraire-information.yaml` | 15 | 15 | 0 | sans objet | 0 |
| `ce1-verbe-sujet.yaml` | 44 | 44 | 92 | 92 | 0 |
| `ce1-phrases-types.yaml` | 34 | 34 | 30 | 30 | 0 |
| `ce1-accord-groupe-nominal.yaml` | 37 | 37 | 0 | sans objet | 0 |

Aucune espace ordinaire avant un signe double, aucun signe colle, aucune double espace dans les valeurs. Les 12 enonces de sciences, relus un par un (accents de *après*, *régime*, *état*, *température*, *dioxygène*, *protège-t-elle*, *êtres*, *récoltent*, *à*) : **aucune faute.** Les 24 enonces de chaque paquet francais : **aucune faute** (accents de *Inès*, *Noé*, *prête*, *écrit*, *maîtresse*, *élèves*, *emmène*, *boîte*, *Où*, *coûte*, *gâteau*, *gâteaux*, *féminin*, *Vérifie*). Les fautes d'orthographe des distracteurs de `ce1-accord-groupe-nominal` (*chapeaus*, *jeus*, *une petit fille*) et les majuscules absentes des distracteurs de `ce1-phrases-types` sont voulues (`GNA-CE1-3`, `TPH-CE1-2`), pas des fautes de relecture.

Les 4 paquets : chaque enonce se termine par `?` (24, 24, 24, 12 sur autant), ce qui sert de preuve a la correction C1 ci-dessous.

## 2. Corrections appliquees (texte seul)

| # | Fichier | Ligne | Defaut | Correction |
|---|---|---|---|---|
| C1 | `adapters/adapter-content/src/main/resources/contenu/ce1-verbe-sujet.yaml` | 18 | **E2 de `marc-dev`, renvoye par la fiche (section 6).** L'en-tete dit *"La question est portee par la consigne du jeu quand l'enonce ne la porte pas."* Le code dit autre chose : les 24 enonces portent leur question (`?` final, mesure ci-dessus) et la consigne du catalogue (`catalogue.yaml`, entree `bandit-manchot-verbe-sujet-ce1`) est generique : *"Lis bien, puis choisis la bonne réponse."* La phrase decrit un cas qui n'existe pas dans ce paquet | *"Chaque enonce porte sa question ; la consigne du jeu (catalogue.yaml) est generique."* |
| C2 | `ce1-phrases-types.yaml` | 13 | Meme phrase, meme inexactitude (24 enonces sur 24 portent leur question ; consigne generique) | Meme correction |
| C3 | `ce1-accord-groupe-nominal.yaml` | 16 | Meme phrase, meme inexactitude | Meme correction |
| C4 | `squad/conformite/FICHE-bandit-manchot-sciences-extraire-information-cm1.md` | 4-5 | Chemin du paquet coupe sur deux lignes a l'interieur des guillemets inverses : rendu avec une espace dans le nom de fichier | Chemin rejoint sur une ligne |
| C5 | idem | 13-16 | Renvoi elide : `ARBITRAGE-PREALABLE-s32-...-le-prix-d-une-ligne-marginale.md` ne resout pas. Chemin du referentiel coupe sur deux lignes. *"ligne 20"* se lit comme une ligne du fichier ; c'est la ligne de referentiel `SCI-CM1-20` (ligne 78 du fichier) | Nom complet du fichier de s32 (la ligne 1033 porte bien la mention, verifie) ; chemin rejoint ; *"ligne `SCI-CM1-20`"* |
| C6 | `squad/conformite/FICHE-bandit-manchot-phrases-types-ce1.md` | 73 | *"la plus grande moitie du paquet (15 items sur 24)"* : une moitie n'est ni plus grande ni plus petite qu'une autre | *"la plus grande partie du paquet"* |
| C7 | `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/composition-palier-1.md` | 6 | Renvoi elide `ARBITRAGE-PREALABLE-s57-criteres-...` | Nom complet : `ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md` |
| C8 | `.../preparation.md` | 49, 66 | Meme renvoi elide, deux occurrences | Meme nom complet |

Le fichier du renvoi (`squad/conformite/ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`) existe ; la section citee (4.2, 4.3) n'a pas ete relue.

## 3. Points a trancher, routes sans reecriture

### 3.1 Vers `nadia-prof` : contenu destine a l'enfant (non modifie)

| # | Fichier | Ligne | Constat |
|---|---|---|---|
| N1 | `cm1-sciences-extraire-information.yaml` | 123 | *"D'après ce texte, que ce transport de pollen permet-il à de nombreuses plantes ?"* : orthographe et ponctuation justes. Construction lourde pour un enfant de CM1 (reprise du sujet par le pronom *il*, complement en *à* avant le complement attendu, reponse qui commence par *de*). Lisibilite, pas faute |
| N2 | idem | 112, 122, 127 | Les indices citent des mots du texte sans marque (*"après les mots ce qui permet"*, *"après le mot aussi"*, *"après le mot permet"*), alors que `ce1-verbe-sujet.yaml` cite avec des chevrons (*« Qui est-ce qui ? »*). Sur la ligne 112, *"ce qui permet"* se lit comme un groupe de mots du commentaire de l'indice. Convention de citation a trancher |
| N3 | `ce1-verbe-sujet.yaml` | 41, 149 | Les distracteurs *"parc"* (copie de *"au parc"*) et *"élèves"* (copie de *"aux élèves"*) sont des noms sans determinant, alors que les autres propositions des memes items en portent (*"des fraises"*, *"une histoire"*). Conforme a la regle *"copie de la phrase"* ; a juger pour la lisibilite d'un enfant de CE1 |
| N4 | `FICHE-bandit-manchot-phrases-types-ce1.md`, `lecture-nadia-palier-1.md` | 65-66 ; 245-246 | *"le distracteur (a) manque deux majuscules"* : la fiche ne dit pas que les deux majuscules sont la meme lettre (celle de la phrase et celle du nom propre) ; la lecture de s57 le dit. Une phrase a ajouter a la fiche, si elle est gardee |

### 3.2 Vers `nadia-prof` : documentation dont elle est l'auteure

| # | Fichier | Ligne | Constat |
|---|---|---|---|
| D1 | `rejeu-nadia-palier-1.md` | 53-54, 73 | *"Les reperes `E1` et `E2` ne sont ecrits dans aucun fichier de `round-two`"* et *"Le repere `E3` n'est ecrit nulle part dans le depot"* : faux. `FICHE-bandit-manchot-verbe-sujet-ce1.md` (lignes 64 et 92) ecrit `E1` (*regarde*) et `E2` (l'en-tete du paquet) de `marc-dev` ; `E3` existe dans `docs/backlog/backlog.md` (lignes 20896, 21014) et `ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md` (ligne 351). Les trois codes designent donc d'autres objets que ceux de la demande. Terminologie : un meme code, plusieurs sens. A nommer par un identifiant propre au document, ou a dire que les codes de la demande sont ceux de l'agent lanceur |
| D2 | `arbitrage-nadia-palier-1.md` | 10 | *"J'ai lu les cinq paquets candidats de Y"* : le groupe Y n'a que trois lots disponibles (`-10`, `-15`, `-24`, section 3.2) ; la section 0 (ligne 40) liste quatre paquets lus en entier, dont un de X (`ce1-texte-narratif`). Ni *cinq* ni *Y* ne se retrouvent dans le fichier |
| D3 | `rejeu-nadia-palier-1.md` (l. 181) et `FICHE-bandit-manchot-phrases-types-ce1.md` (l. 123) | idem | Provenance de forme 2 (`DEC-0062` 3.1) : la mention `de l'utilisatrice` et la date sont presentes, **ses mots ne sont pas cites entre guillemets** (*"`Q-N4` = non"* est une notation de l'agent, pas sa citation). |
| D4 | `arbitrage-nadia-palier-1.md` | 48 | Forme 2 : *"oui"* cite, mais la date de la reponse n'est pas ecrite (la phrase date la decision du 2026-10-13, pas la confirmation de l'utilisatrice) |
| D5 | `ce1-verbe-sujet.yaml`, `ce1-phrases-types.yaml`, `ce1-accord-groupe-nominal.yaml` | 1 | En-tete *"brouillon soumis a nadia-prof"* : les trois paquets sont valides (sous reserve), servis ou branches. L'en-tete decrit l'etat de s53, pas l'etat du fichier. Historique a garder, ou a mettre a jour : le texte ne dit pas lequel. A trancher par l'auteur du paquet (`marc-dev`) avec `nadia-prof` |

### 3.3 Vers `sofia-sm` : provenance (`DEC-0062` 3.1, forme 2)

Aucun enonce de forme 3 (`recu de MASTER, sans source dans le depot`) dans le perimetre, donc aucun enonce de MASTER qui fonde un critere, une `DEC`, un `ADR` ou un verdict. Aucune occurrence de `MASTER` dans les fichiers relus ; `Scrum Master` n'y apparait pas non plus, les fichiers nomment `sofia-sm`.

| # | Fichier | Ligne | Constat |
|---|---|---|---|
| S1 | `planning.md` | 138 | *"`lea-dev` est le choix de l'utilisatrice (`Q2` de `sofia-sm`, `DEC-0120` A.38, forme 2)"* : ni ses mots entre guillemets, ni la date |
| S2 | `planning.md` | 93 | *"comme l'utilisatrice l'a demande le 2026-10-12 (`DEC-0120` 4.5)"* : date presente, ses mots absents |
| S3 | `planning.md` | 203 | *"la decision de l'utilisatrice du 2026-10-11 (`DEC-0116` A.26)"* : date presente, ses mots absents |
| S4 | `duree-et-paliers.md` | 6 et 23 | *"oui plus que 8"* cite, rattache a `DEC-0120` A.38 ; la date de la reponse n'est pas ecrite |
| S5 | `avis-faisabilite.md` (`theo-archi`) | 138 | *"une decision de l'utilisatrice (`DEC-0116` A.26)"* : ni ses mots ni la date. Route a `theo-archi` par `sofia-sm` |

Le qualificatif *"relayee par l'agent principal, non lue de sa main"*, employe dans `planning.md`, `preparation.md`, `duree-et-paliers.md`, `rejeu-nadia-palier-1.md` et la fiche de phrases-types, n'est pas l'une des trois formes de `DEC-0062`. Je ne tranche pas s'il en fait une quatrieme ou une precision de la forme 2 : la regle est a `camille-po`, la convention a `sofia-sm`. Les cas ci-dessus sont signales independamment de ce point.

### 3.4 Constats sans ecart

- Comptes de tests recoupes par addition : `cloture-palier-1.md` (2548 = 120 + 31 + 486 + 1534 + 371 + 6 ; ecarts +22, +21, +1, +6), fiches (2520, 2519, 2504, 2499), `planning.md` (2498), `lecture-testabilite.md` (2492 + 6).
- Compteurs des paquets recoupes sur les fichiers : `ce1-phrases-types` 9 + 15 (5 par type), `ce1-accord-groupe-nominal` 21 adjectifs, 12 en -x, 6 en -s, 6 feminins, bonne reponse strictement la plus longue sur 10 items ; `ce1-verbe-sujet` 15 + 9, trois complements en tete (items 1, 16, 20), 10 mots au plus pour la phrase la plus longue.
- Aucun tiret cadratin ni demi-cadratin, aucun guillemet ou apostrophe typographique, aucun emoji ni symbole decoratif, aucun chemin absolu de poste de travail ni nom de compte dans les 12 fichiers du dossier s57 (hors ce releve) et les 4 fiches (`verifier-chemins.sh`, voir section 4).
- Les chevrons `«` `»` des citations de contenu portent une espace insecable dans les trois paquets francais ; la convention du depot les admet (`relecture-cloture.md` de s56, section 2).
- Elision residuelle non corrigee : les commentaires de `cm1-sciences-extraire-information.yaml` (lignes 13-15) coupent le nom du fichier de s32 sur deux lignes de commentaire. Commentaire non lu par l'enfant, nom lisible par continuite ; non modifie pour ne pas toucher un paquet valide au-dela de ce qui est requis.
- Meme phrase d'en-tete a corriger dans 9 autres paquets (`ce1-accord-sujet-verbe`, `ce1-classes-de-mots`, `ce1-conjugaison-present`, `ce1-familles-de-mots`, `ce1-futur`, `ce1-mots-invariables`, `ce1-negation`, `ce1-passe-compose`, `ce1-temps`), et dans `squad/sprints/s56-paquet-art-cp-10-et-francais-ce1-13/rapport-marc-dev.md` : hors perimetre de ce releve, non verifies un a un. A router a `marc-dev` (auteur) pour verifier si chaque enonce y porte sa question ; je n'ai pas modifie ces fichiers.

## 4. Jobs de `ci.yml` lances sur ce diff

Lances avant commit, sur ce worktree, avec les 8 fichiers du diff :

- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` (21 cas) sortent 0 ; le sujet du commit passe dans `verifier-prefixes.sh`, sortie 0 (rendu dans le rapport de la tache).
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; les fichiers suivis et non suivis (moins le test du garde-fou), ce releve compris, passes a `verifier-chemins.sh` : sort 0.
- **`build`** : non lance en entier. Mesure partielle, perimetre nomme : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test` (trois modules : `domain`, `application`, `adapter-content`), `BUILD SUCCESS`, 120, 31 et 1534 tests, 0 echec, 0 erreur, 0 ignore. Le 1534 egale celui de `cloture-palier-1.md`, section 4, pour `adapter-content`. En plus, `-pl bootstrap -am test -Dtest=AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` (ce releve suivi dans l'index) : 5 tests, 0 echec. Ni les autres tests de `bootstrap`, ni les tests d'integration, ni l'etape *"Package du fat jar"*, ne sont lances.
- **`outils`** : non lance ; aucun outil n'est modifie.

La CI distante n'est pas lue. Je ne deduis aucun job que je n'ai pas lance.

## 5. Ce que ce releve ne fait pas

- Ne modifie aucun texte lu par l'enfant (les 4 YAML ne changent que par un commentaire d'en-tete, 3 fichiers, 1 ligne chacun).
- Ne prononce aucun Done et ne juge ni la lisibilite, ni le ton, ni le fond du contenu (N1 a N4 sont signales, pas jugees).
- Ne tranche ni l'attribution des citations (D3, D4, S1 a S5), ni le statut du qualificatif *"relayee"*.
