# s57, palier 4, poste p4-8 - rapport de `sofia-sm` (casquette développeuse, instance A'), lot `LP5`

- **Story** : `S57-BR-EMC-CE1-03`, paquet `ce1-emc-droit-devoir`, ligne `EMC-CE1-03`. **Prix** : 1,0.
- **Casquette** : développeuse uniquement. Rien n'est fusionné, aucune lignée n'est jugée, aucun amendement de `planning.md` n'est animé, `LP6` n'est pas touché.
- **Date** : 2026-10-22. **Branche** : `s57-sofia-emc-ce1-03`, worktree `recreo-s57-sofia-emc-ce1-03`.
- **Base** : `51a9d957` (sommet de `round-two`, avec `LP1`, `LP2` et `LP4` fusionnés). `git merge-base --is-ancestor 51a9d957 HEAD` sort 0.
- **Rejeu** : voir la section 11 (commit `5b9bb648`). Les sections 1 à 10 décrivent le premier passage (`edf2b03b`) et ne sont pas réécrites.
- **Commit de code, premier passage** : `edf2b03b` (14 fichiers, 1 973 lignes ajoutées, 0 supprimée). **Ce rapport** est un second commit, qui ne touche que ce fichier.

## 1. Ce qui est livré

| Fichier | Rôle |
|---|---|
| `adapters/adapter-content/src/main/resources/contenu/ce1-emc-droit-devoir.yaml` | le paquet : 24 items, type `question`, aucun texte support, aucune voix |
| `adapters/adapter-content/src/main/resources/catalogue.yaml` | entrée `bandit-manchot-droit-devoir-ce1`, titre servi *"Les droits et les devoirs"*, consigne de `LP1`/`LP4` reprise, `nombreDeManches: 10` |
| `PaquetCe1EmcDroitDevoirTest.java` + `contreexemple/PaquetCe1EmcDroitDevoirTest/` | le test du paquet (64 tests) et ses jeux fautifs |
| `TexteAccentueExposeParLePortEntrantTest.java` | trois ajouts (identifiant, titre, compétence), aucun retrait |
| `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java` | une entrée `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`, aucun retrait |

`git diff 51a9d957 HEAD` ne retire aucune ligne d'un fichier partagé : aucune assertion n'est affaiblie. Aucun garde-fou de `bootstrap` n'est modifié (`ChaqueJeuDuCatalogueReelEstAtteignableDansLesDeuxVuesDeLAccueilTest`, `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`, `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` : `git diff --name-only` ne les nomme pas). Aucun identifiant neuf n'est écrit : `DDE-CE1-1`, `DDE-CE1-2`, `NRC-CE1-1`, `TRV-CE1-1`, `-2`, `-5`, `-6`, `LON-CE1-1`, `-2`, `-5`, `FER-1` à `FER-3`, `EMC-1` sont des identifiants existants ; les déclarations `couple N : sens` et `famille X` sont des libellés de commentaire du paquet, pas des conditions.

## 2. Le paquet

Quatre couples, la liste fermée de `nadia-prof` (`ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, 4.3), recopiée dans l'en-tête avec ses mots :

1. apprendre / laisser les autres apprendre ;
2. être respecté / respecter les autres ;
3. parler / écouter quand un autre parle ;
4. être en sécurité / ne mettre personne en danger.

Vingt-quatre items : **12 `droit->devoir`** (le droit est donné, on demande le devoir : *"Léo a le droit d'apprendre. Quel devoir ont les autres élèves envers lui ?"*) et **12 `devoir->droit`** (le geste est donné, on demande le droit protégé : *"Omar laisse Mila travailler au calme. Quel droit de Mila est ainsi protégé ?"*), **3 + 3 par couple**. Les items alternent couples et sens dans le fichier. Aucune situation ne décrit une faute ni une sanction. Aucun adulte n'est nommé (`grep -vE '^\s*#' F | grep -ciE 'maître|maîtresse|professeur'` rend 0).

Le commentaire d'en-tête écrit, pour chaque couple et chaque côté, **les formulations qui peuvent être une bonne réponse** (3 par côté, 6 pour le côté *droit* du couple 2 qui porte les deux genres), et **cinq familles de distracteurs** :

- `a` un geste ou un mot que l'enfant croit utile et qui n'est pas le devoir demandé (*lui dire la réponse*, *l'inviter à jouer*) ;
- `b` un devoir de l'élève qui ne répond à aucun des quatre droits (*arriver à l'heure*, *finir son travail*) ;
- `c` un droit de l'élève hors des quatre couples (*emprunter un livre*, *se reposer*) ;
- `f` une envie ou un comportement qui n'est pas un droit (*passer en premier*, *couper la parole*).

Les items `droit->devoir` tirent leurs deux distracteurs dans `a`, `b`, `c` ; les items `devoir->droit` dans `b`, `c`, `f`. **Aucun distracteur n'est une formulation d'un couple** : un distracteur n'est jamais la réponse défendable d'un autre couple.

## 3. Critères de la story

| Critère | Commande, sortie | Verte |
|---|---|---|
| Existence d'abord | `ls adapters/adapter-content/src/main/resources/contenu \| grep -c '^ce1-emc-droit-devoir'` : **0** sur `51a9d957` (`git ls-tree`), **1** sur `edf2b03b` | oui |
| 1, un paquet pour une ligne | `grep -l 'lignesDeReferentiel: \["EMC-CE1-03"\]' .../contenu/*.yaml \| wc -l` rend 1 ; `grep -c 'bonneReponse:' F` rend 24 | oui |
| 2, `DDE-CE1-1` | le commentaire d'en-tête liste les quatre couples ; 12 items `droit->devoir` et 12 `devoir->droit` (`grep -c 'droit->devoir$' F`, `grep -c 'devoir->droit$' F`). **Recompte par le test** : `dde1LaListeDesCouplesEstExactementCelleDeNadiaProf`, `dde1ChaqueItemDeclareUnCoupleEtSaBonneReponseEstUneFormulationDeCeCoupleEtDeCeCote`, `dde1ChaqueCoupleEstServiDansLesDeuxSens...` | oui |
| 3, `DDE-CE1-2` | `grep -vE '^\s*#' F \| grep -ciE 'gagn\|mérit\|perd\|retir\|enlev\|priv\|récompens\|condition\|échange\|retour'` rend 0 ; `dde2AucunItemNePresenteUnDroitCommeGagneOuPerdu`. **Le sens d'un item n'est pas gardé** : `nadia-prof` le lit | oui |
| 4, clauses de forme | `grep -vE '^\s*#' F \| grep -cE 'texteSupport\|texteAVoixHaute'` rend 0 ; propositions de six mots au plus et une phrase (test `eb6`) ; indices de douze mots au plus, **24 distincts sur 24** (`grep '^    indice:' F \| sort -u \| wc -l`) ; trois propositions, une seule acceptable, familles nommées en commentaire à côté de chaque item (`TRV-CE1-2 : x, y`) ; bonne réponse strictement la plus longue dans 11 items sur 24 (garde : 12 au plus ; mesure par mon script d'auteur, hors dépôt) | oui |
| 5, entrée de catalogue | `grep -c "paquet: ce1-emc-droit-devoir$" catalogue.yaml` rend 1 ; `nombreDeManches: 10`, titre sans le niveau (`ADR-0078`), U+00A0 avant `?` (`k3LEntreeDuCatalogue...`) ; `ChaqueJeuDuCatalogueReelEstAtteignableDansLesDeuxVuesDeLAccueilTest` vert, fichier non modifié | oui |
| 6, Done technique | section 5 | oui |

## 4. Leçons de `LP4`, et ce qui les garde

| Leçon de `LP4` | Dans `LP5` | Garde, et jeu fautif qui la mord |
|---|---|---|
| La forme donne la réponse (chevrons) | aucun chevron, aucun guillemet ; propositions en minuscule | `verifierLaForme` : sondes 18 (majuscule), 19 (chevrons) |
| Mot repris, négation, article | la bonne réponse n'est **jamais la seule** à reprendre un mot plein de l'énoncé, à porter une négation, à ouvrir par un pronom complément (valeur identique à celle d'au moins un distracteur) | `verifierQueLaBonneReponseNEstJamaisSeuleDeSonEspece` : sondes 14 et 17 (mot repris, dans les deux sens), 15 (négation), 16 (pronom) |
| Distracteurs absurdes | familles `a`, `b`, `c`, `f` nommées, dont deux (`b`, `c`) sont de vraies règles ou de vrais droits d'élève | familles gardées (sondes 06, 07, 08, familles servies au moins trois fois) ; **la vraisemblance n'est pas gardée** |
| Bonne réponse qui reprend le verbe de l'énoncé | voir ligne 2 : la reprise n'est tolérée que si un distracteur la partage ; les bonnes réponses de côté *devoir* sont des paraphrases (*le laisser travailler* pour *apprendre*) | sondes 14, 17 |
| Acte qui n'est pas une faute ou une sanction | aucun acte fautif, aucune sanction | sans objet ; `DDE-CE1-2` gardée sur les mots (sondes 09, 10, 11) |
| Intention illisible | sans objet (aucune faute) | - |
| Indice qui nomme le mauvais adulte | aucun adulte nommé | sans objet |
| Énoncés de trois lignes sous le pli | énoncé 112 caractères au plus, indice 46 au plus (seuils de `LP4`, pire cas 550 sur 551) ; énoncé de 20 mots et 3 phrases au plus | sondes 41, 42, 21, 22. **Non mesuré à 744 x 551 sur ce paquet** |
| Règles générales | sans objet : liste fermée de couples | `dde1LaListeDesCouplesEstExactementCelleDeNadiaProf` : sondes `entete-trois-couples`, `entete-cinq-couples`, `entete-couple-reecrit` |

**Un garde corrigé en route, jamais affaibli** : `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` (clause 2, `FOP-R4`) a rougi au premier essai sur le paquet (*"arriver à l'heure"* six fois et *"se reposer un peu"* quatre fois en distracteur). Le contenu a été corrigé (deux formulations de plus dans la famille `b`, *"se reposer"* à deux mots) ; l'exemption `DETTE_D_AMORCE` n'est pas touchée. `leContenuServiEstConforme` est vert.

Le rouge de `LaForme...` n'est apparu que dans `bootstrap` : le test du paquet seul (`-pl adapters/adapter-content -am`) était vert. **Toute mesure partielle de ce lot inclut `bootstrap`.**

## 5. Porte technique : jobs de `ci.yml` lancés

Commit vérifié : `edf2b03b`, arbre propre (`git status --short` vide).

| Job | Commande | Sortie |
|---|---|---|
| `build`, étape Verify | `bash outils/mvn-jdk21.sh -B clean verify` à la racine du worktree, **jeton RF1 pris à 18:13:24 (aucune attente) et rendu à 18:27:26** | **exit 0**, 18:13 à 18:22, `BUILD SUCCESS`, 6 modules |
| `build`, étape Package du fat jar | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package` | **exit 0**, `BUILD SUCCESS`, `bootstrap/target/recreo.jar` produit |
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `git log --no-merges --format=%s 51a9d957..HEAD \| bash .github/scripts/verifier-prefixes.sh`, `verifier-garde-fou-accent-test.sh`, `verifier-garde-fou-accent.sh 51a9d957..HEAD` | **exit 0** pour les cinq (sur `edf2b03b`) |
| `chemins` | `verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **exit 0**, *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | balayage `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` | **exit 0**, 13 juges purs exécutés |

Les jobs `build`, `prefixes`, `chemins`, `outils` et l'étape "Package du fat jar" de `ci.yml` sont lancés en local. **Cela ne vaut pas la CI** : celle de GitHub n'a pas tourné sur ce commit (aucun push). Les jobs `prefixes` et `chemins` sont à relancer sur le commit de ce rapport et sur le commit de fusion.

**Tests** (surefire sur l'arbre de `edf2b03b`, par module, dans l'ordre du réacteur) : domain 120, application 31, adapter-web 523, adapter-content 2 023, bootstrap 381 plus 6 d'intégration : **3 084**, 0 échec, 0 erreur, 0 ignoré.

**Delta** : **+64** tests, tous dans `PaquetCe1EmcDroitDevoirTest` (24 de paquet, 40 de jeux fautifs). Le diff n'ajoute aucun `@Test` ailleurs (`git diff 51a9d957 HEAD -- '*Test.java' | grep -c '^+.*@Test'` rend 64). **Le total de la base n'est pas mesuré par moi** : 3 084 moins 64 donne 3 020 pour une base qui n'a que ce lot de moins, c'est une déduction, pas une mesure. Le total après fusion est à `sofia-sm` (Scrum Master), qui le mesure sur l'arbre fusionné.

**Jar** : aucun jar n'est lancé, aucun port (18532, 19232) n'est utilisé par ce rapport.

## 6. Ce que `nadia-prof` doit juger

Aucun compteur ne le voit.

1. **J1, lecture complète** (`LC`) : `lecture complète : ce1-emc-droit-devoir, 24 items lus`, à ses mains. Les 24 items, 48 distracteurs, 24 indices, en-tête.
2. **J2, paraphrases des couples** : les formulations sont des paraphrases des côtés de ses couples, écrites en en-tête. Le couple 1 côté devoir : *le laisser travailler*, *la laisser écouter*, *la laisser réfléchir* (pour *laisser les autres apprendre*). Le couple 4 côté devoir : *faire attention à elle*, *jouer doucement avec lui*, *courir avec prudence* (pour *ne mettre personne en danger*, écrit en négation, que j'ai évitée pour que la négation ne donne pas la réponse). Ce sont des paraphrases que j'ai choisies : sont-elles recevables comme *couples fermes*, ou faut-il ses mots exacts ?
3. **J3, `NRC-CE1-1` sur le fond** : mon garde compare les textes avec `cp-emc-droits-enfant` et `cp-emc-regles-de-vie` (aucune proposition identique). **Il ne compare pas les sujets** : le couple 3 (écouter quand un autre parle) et le couple 4 (courir, ne blesser personne) recoupent `cp-emc-regles-de-vie` (*"Pourquoi écoute-t-on un camarade quand il parle ?"*, *"pour que personne ne se blesse"*). Ces deux couples sont dans sa liste ; est-ce une reprise ? Les cousins de CM1 (`cm1-emc-droit-devoir`, `cm1-emc-regle-loi`) sont à relire par elle.
4. **J4, vraisemblance et lecture unique des distracteurs** (`EMC-5`) : famille `a` (*lui dire la réponse*, *lui prêter son cahier*, *lui donner un cadeau*) : un geste aimable peut-il se défendre comme un devoir envers le camarade ? Famille `c` (*emprunter un livre*, *se reposer*) proposée quand on demande un devoir : confusion des deux notions, mais ce sont des droits de l'élève. Famille `b` (*arriver à l'heure*, *finir son travail*) : devoirs réels, mais l'énoncé dit *envers lui* en `droit->devoir`. Famille `f` : *couper la parole*, *parler tout le temps* sont des conduites à écarter proposées comme droit.
5. **J5, lecture de `DDE-CE1-2`** sur les items où le titulaire du droit est aussi celui qui a le devoir : item *"Maya a le droit qu'on la respecte. Quel devoir a-t-elle envers les autres élèves ?"* (et l'item d'Adam : *"Quel devoir a-t-il quand un autre élève parle ?"*). Le mot *envers* sans *en retour* suffit-il à ne pas faire du devoir la contrepartie du droit ? Les deux autres formes (devoir des autres envers le titulaire, geste donné) n'ont pas cette lecture.
6. **J6, lisibilité CE1** : *ainsi protégé* (répété à l'identique dans les 12 items `devoir->droit`), *chuchote*, *à l'abri du danger*, *se sentir en sécurité*, *écouter celui qui parle*, *courir avec prudence*, *lui montrer du respect*.
7. **J7, exactitude** des 24 situations et des quatre couples (que *le droit d'apprendre et le devoir de laisser les autres apprendre* soit bien celui du programme est la parenthèse de la compétence, copiée dans le descripteur).
8. **J8, le sens de chaque indice** (`TRV-CE1-5`, *relu par moi* sur les 24 : aucun ne contient une proposition, aucun ne nomme *droit*, aucun ne donne un adulte).
9. **J9, `TRV-CE1-6`** : 15 prénoms, 7 féminins et 8 masculins (table déclarée dans le test, recomptée) ; aucun item n'attribue un rôle par genre (le rôle est un geste de politesse ou d'attention, ou un droit).

## 7. K8 : chaque point d'audit a un test ou *"non gardé"*

| Point | État |
|---|---|
| `DDE-CE1-1`, couples fermes, deux sens | **gardé** (tests de la section 3) |
| `DDE-CE1-2` | **gardé sur les mots** ; **non gardé : le sens d'un item** |
| Aucune forme ne donne la réponse | **gardé** : majuscule, chevrons, mot plein repris, négation, pronom complément en tête, longueur ; **non gardé : article en tête, forme grammaticale du premier mot** (une forme *infinitif* a été essayée et retirée : elle donne des faux rouges sur les formes à pronom, *l'inviter à jouer* étant un infinitif) |
| `NRC-CE1-1` | **gardé sur l'égalité des textes** avec deux paquets de CP ; **non gardé : le sujet, `cp-emc-regle-souhait-loi`, les trois paquets de CM1** |
| `EMC-1`, `EMC-6` | **gardé** (aucune adresse à l'enfant, aucun mot du foyer, personnage nommé) ; non gardé : aucun lieu de vie hors ce que le texte dit (lecture) |
| `TRV-CE1-6` | gardé sur l'équilibre des prénoms ; non gardé : les rôles |
| `LON-CE1-1`, `-2`, `-5`, `TRV-CE1-1`, `-5` | **gardés** (test du paquet, et `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` vert) |
| Pli à 744 x 551 | seuils de `LP4` gardés (112, 46) ; **non mesuré** sur ce paquet |

## 8. Questions routées (RG5)

| Q | Destinataire | Question |
|---|---|---|
| Q1 | `nadia-prof` | J2 : paraphrases des couples, ou ses mots exacts, y compris la négation du couple 4 |
| Q2 | `nadia-prof` | J3 : les couples 3 et 4 reprennent-ils `cp-emc-regles-de-vie` malgré des propositions différentes |
| Q3 | `nadia-prof` | J4 et J5 : lecture des distracteurs et du mot *envers* sur les items où le titulaire a le devoir |
| Q4 | `ines-design` | relevé à 744 x 551 de ce paquet : les seuils de `LP4` (enoncé de trois lignes, indice d'une ligne) ne sont pas une mesure d'ici ; le signal *"Bravo !"* de `LP4` finissait à 564 pour les items à trois rangées (`MP4-2`), les propositions de ce paquet font 10 à 24 caractères |
| Q5 | `theo-archi` | `ADR-0086` clause 2 a rougi sur ce paquet au premier essai et a été tenue par le contenu, sans exemption. Pas de question : un fait à lire. La liste des sondes `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` reçoit une ligne neuve, dont la mesure est : 24 items sur 24, aucune proposition ne commence par un chiffre (lue par le test lui-même, vert) |
| Q6 | `camille-po` | rien à trancher au branchement : la cellule `EMC-CE1-03` de la matrice n'est pas touchée (`git diff 51a9d957 HEAD --stat -- squad/programme/MATRICE-couverture.md` vide) ; la fiche `FICHE-bandit-manchot-droit-devoir-ce1.md` est ouverte par `nadia-prof` |

## 9. Ce que je n'ai pas fait

- Je n'ai pas joué le jar, ni lancé de port.
- Je n'ai pas mesuré le pli, ni lu le paquet avec le regard de `nadia-prof` : `TRV-CE1-5` est relu par moi, `EMC-5`, `NRC-CE1-1` sur le fond et `DDE-CE1-2` sur le sens sont à elle.
- Je n'ai pas lancé `verifier-lignee.sh` : la lignée est lue par `theo-archi`. Seul fait : `51a9d957` est ancêtre de `HEAD`.
- Je n'ai ni poussé, ni rebasé, ni fusionné, ni écrit dans `planning.md`.

## 10. Isolation

Répertoire de scratchpad utilisé : `s57-p4-sofia-dev-ap` (créé par `mkdir -p` en première commande, existant avant moi avec des fichiers de mes instances précédentes, que je n'ai ni déplacés ni supprimés). Seule exception : le répertoire de jeton RF1 `s57-p4-jeton-verify`, créé par `mkdir` à la racine à 18:13:24, supprimé à 18:27:26 ; le répertoire qui s'y trouve à la fin de ma lecture appartient à `lea-dev` (fichier `lea-dev-1827.txt`, début 18:27:31).

Listes de fin (`ls -1` de la racine du scratchpad, puis du répertoire parent des worktrees) : voir la réponse de l'agent, qui porte la commande et la sortie (la liste de fin est faite après ce commit). Rien n'a été écrit hors du répertoire de l'agent, du jeton et du worktree.

## 11. Rejeu du 2026-10-22 (refus de `nadia-prof`, M1, M2 et une réserve ; prix 0,25)

Commit de code : **`5b9bb648`**, sur `db5e0e6d`. Il ne change que trois items du paquet (`git diff db5e0e6d 5b9bb648 -- .../ce1-emc-droit-devoir.yaml` : 3 items et 4 lignes de commentaire d'en-tête), le test et ses jeux fautifs. Les 21 autres items sont inchangés, texte pour texte.

| Item (rang dans le fichier) | Avant | Après |
|---|---|---|
| 14, M1 | distracteur *finir son travail*, indice *"Que peut faire Hugo quand Jade agit ainsi ?"* | distracteur *apporter son cahier* (famille `b`, devoir hors couples qui ne dit pas la scène), indice *"Pense à ce que Hugo fait en classe."* ; la situation et la bonne réponse (*suivre la leçon*) ne changent pas |
| 24, M2 | *"Zoé remercie Adam quand il l'aide."*, *être traité poliment*, indice *"Que reçoit Adam quand Zoé le remercie ?"* | *"Zoé demande poliment à Adam de lui passer la colle."*, *être bien traité* (formulation déjà écrite en en-tête), distracteurs *apporter son cahier*, *jouer en premier*, indice *"Pense à la façon dont Zoé s'adresse à Adam."* : le geste ne dépend d'aucune action d'Adam |
| 19, réserve | indice *"Pense à l'attitude des autres envers Hugo."* | *"Pense à ce que les autres font pour Hugo."* |

**Gardes ajoutés** (aucune assertion retirée ni assouplie ; `git diff` du test : ajouts seuls, plus deux mots dans la liste de `DDE-CE1-2`) :

| Garde | Jeu fautif qui le mord |
|---|---|
| `dde2AucunDroitNEstRenduEnRetourDUneActionDeCeluiQuiLeDetient` : une scène `devoir->droit` ne contient aucune subordonnée (*quand*, *lorsque*, *si*, *parce que*, *dès que*, *après que*) dont *il* ou *elle* soit le sujet | sonde 45 : *"Zoé remercie Mila quand elle l'aide"* (c'est la scène de l'item 24 avant correction) |
| liste de `DDE-CE1-2` étendue à *reçoi* et *recev* | sonde 46 : indice *"Que reçoit Mila quand Zoé parle ?"* |
| `emc5AucunDistracteurNeDitLaSituationMieuxQueLaBonneReponse` : si la bonne réponse ne reprend aucun mot plein de l'énoncé, aucun distracteur n'en reprend | sonde 47 : l'item 14 avant correction (*finir son exercice* / *finir son travail*) |

Ce dernier garde remplace, dans la pratique, la règle symétrique de la section 4 pour le cas *la bonne réponse ne reprend rien* : le mot repris par un distracteur seul est maintenant refusé. Le paquet le tient sans autre changement que l'item 14 ; l'ancienne règle (la bonne réponse n'est jamais seule à reprendre un mot) reste en place.

**Ce que `nadia-prof` rejoue** : les items **14**, **24** et l'indice de l'item **19** ; rien d'autre n'a bougé. Pour l'item 24, lire si *"demande poliment"* ne fait plus dépendre le droit d'une action d'Adam ; pour l'item 14, que *finir son travail* ne soit plus proposé et que *suivre la leçon* reste la seule lecture.

**Porte technique** (commit `5b9bb648`, arbre propre) :

| Job | Résultat |
|---|---|
| `build`, Verify | `bash outils/mvn-jdk21.sh -B clean verify` : **exit 0**, `BUILD SUCCESS`. Jeton RF1 pris à 19:29:46 après attente, rendu à 19:59:35. Une première instance de mon script de lancement avait aussi pris le jeton à 19:16:13 pour le même commit, sans conflit (le jeton a été tenu une fois à la fois) |
| `build`, "Package du fat jar" | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package` : **exit 0**, `BUILD SUCCESS` |
| `prefixes`, `chemins`, `outils` | cinq scripts de `prefixes` sur `51a9d957..HEAD`, `verifier-chemins-test.sh` et `verifier-chemins.sh`, 13 juges purs : tous **exit 0** (à relancer sur le commit de ce rapport et sur la fusion) |

**Tests** : 120 + 31 + 523 + 2 028 + 381 + 6 = **3 089** (3 084 au premier passage). **Delta du lot : +69** (`PaquetCe1EmcDroitDevoirTest` : 69 tests, 26 de paquet et 43 de jeux fautifs). Mesure partielle préalable avec `-am` et `bootstrap` (ADR-0086, `TexteAccentue...`, `ChaqueJeu...`, `AucunEnonceDeCe1...`, `LeRang...`) : verte. La base n'est pas mesurée par moi.

**Pli** : toujours non mesuré sur ce paquet (`ines-design` indisponible) ; les seuils de `LP4` sont repris sans mesure, aucune ligne ajoutée. **Jar** : aucun lancé, aucun port utilisé.

Questions routées : aucune neuve. Les trous que `yanis-qa` relèverait dans ces gardes me sont à router après son rapport.

## 12. Commit de test seul : trou D15 de `yanis-qa` (2026-10-22, après le verdict VALIDE SOUS RESERVE sur `5b9bb648`)

Commit **`84064ce9`**, sur `ebdfa265`. `git diff --name-only ebdfa265 84064ce9` :

```
adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCe1EmcDroitDevoirTest.java
adapters/adapter-content/src/test/resources/contreexemple/PaquetCe1EmcDroitDevoirTest/contenu/ce1-emc-fautif.yaml
```

Ni le paquet YAML, ni le catalogue, ni un gabarit, ni `src/main` ne sont touchés. **RF13** : le jar joué par `nadia-prof` (`5b9bb648`) n'est pas concerné, aucun fichier du lot n'est sous `src/main` dans ce commit. Ce rapport est dans le commit suivant, qui ne touche que lui.

**Le trou** : le couple déclaré d'un item était une déclaration, pas une lecture. **Le garde** `dde1LaSituationDeChaqueItemNommeLeCoupleQuIlDeclare` : la situation (l'énoncé) reprend au moins un mot entier de la liste fermée du couple déclaré, écrite dans le test (`MOTS_DE_LA_SITUATION_PAR_COUPLE`), et aucun mot de la liste d'un autre couple. Le paquet réel passe sans changement de contenu.

| Jeu fautif | Ce qu'il mord |
|---|---|
| sonde 48 : *"Léo a le droit d'apprendre"* déclarée couple 2, bonne réponse *lui parler poliment* (couple 2) : **la sonde D15**. `verifierLesDeclarations` la laisse passer (le test le prouve), le nouveau garde la refuse | couple 2 sans mot du couple 2 |
| sonde 49 : couple 1 avec *"et de parler poliment"* | mots d'un autre couple dans la situation |
| sonde 50 : situation sur *apprendre* déclarée couple 3 avec sa réponse du couple 3 | couple 3 sans mot du couple 3 |

**Preuve que le garde ne refuse aucun item du paquet réel** : `dde1LaSituationDeChaqueItemNommeLeCoupleQuIlDeclare` est vert sur les 24 items.

**Mutation sur le paquet réel, rejouée** : item 1 passé de `couple 1` à `couple 2` et sa bonne réponse de *le laisser travailler* à *lui parler poliment* (la déclaration reste cohérente). `-pl adapters/adapter-content -am -Dtest=PaquetCe1EmcDroitDevoirTest` : **rouge**, un seul échec, `dde1LaSituationDeChaqueItemNommeLeCoupleQuIlDeclare`. Fichier restauré par `git checkout`, `git diff -- adapters/adapter-content/src/main` vide ensuite.

**Fermés en même temps, sans rougir le paquet réel** : la liste lexicale de `DDE-CE1-2` reçoit *grâce*, *seulement si*, *pour avoir* (L04, L05, L08) ; sondes 51, 52, 53. **Laissé en dette dite** : F02 (mot repris de quatre lettres) : le seuil à cinq lettres est inchangé, je n'ai pas tenté de l'abaisser (il demanderait une liste de mots outils et un risque de rouge sur le contenu valide).

**Aucune assertion retirée ni assouplie** : le diff du test ne contient que des ajouts, hormis les trois mots ajoutés à une expression.

**Porte technique** (commit `84064ce9`, arbre propre) :

| Job | Résultat |
|---|---|
| `build`, Verify | `bash outils/mvn-jdk21.sh -B clean verify` : **exit 0**, `BUILD SUCCESS`. Jeton RF1 pris à 21:17:39, rendu à 21:25:24 |
| `build`, "Package du fat jar" | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package` : **exit 0** |
| `prefixes`, `chemins`, `outils` | scripts de ci.yml sur `51a9d957..HEAD` : **exit 0** ; 13 juges purs, exit 0 |

Mesure partielle préalable `-pl adapters/adapter-content,bootstrap -am` (ADR-0086 et gardes de `bootstrap`) : 105 et 48 tests, verte.

**Tests** : 120 + 31 + 523 + 2 033 + 381 + 6 = **3 094** (3 089 avant ce commit, +5 : 74 tests dans `PaquetCe1EmcDroitDevoirTest` au lieu de 69 ; un test de paquet et quatre de jeux fautifs). La base n'est pas mesurée par moi.
