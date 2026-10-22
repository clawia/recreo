# [Sprint s57] Rapport de developpeur, palier 4, lot LP1 - `marc-dev`, poste p4-3 (`S57-BR-FRA-CE1-21`)

- **Auteur** : `marc-dev`, instance A. **Date** : 2026-10-22. **Base** : `eb267ba7` (`git log -1 --format=%h` au premier message). Branche `s57-marc-fra-ce1-21`, worktree voisin du checkout. Aucun push, aucun rebase, aucune fusion.
- **Rejeu du 2026-10-22** : les sections 1 a 8 decrivent le premier jet (`56e9fd1b`) ; la section 9 dit ce que le refus de `nadia-prof` a change et prevaut sur elles (mots, enonces, nombres de tests, jar).
- **JDK de mesure** : 21.0.10, `java.version` lu dans les 60 rapports Surefire de `bootstrap` ; le `java` du `PATH` n'est pas celui qui a mesure.
- **Commits du lot** : `c591a49b` (le lot), `3d3d9382` (prenoms, `TRV-CE1-6`), `83035447` (deux enonces resserres), `6554bf87` (critere 11, commentaire seul), puis un dernier commit portant ce rapport et celui du palier 3. Le `verify` complet a ete lance a `83035447` ; les commits suivants ne touchent qu'un commentaire de test et deux fichiers `.md`.
- **Delta, pas un total** : **+34 tests**, tous dans `PaquetCe1MotsInvariablesTest` (15 gardes du paquet et de la mecanique, 19 jeux fautifs commites : 14 sur le contenu, 5 sur le catalogue), mesure sur la base `eb267ba7` plus ce seul lot. Total de mon reacteur : 2889 (120 + 31 + 523 + 1828 + 381 + 6 d'integration), contre 2855 mesures par `theo-archi` sur arbre propre (`avis-theo-palier-4.md` 3.5) : l'ecart est de 34, il n'est pas verifie par moi sur un arbre fusionne. Le total apres fusion est a `sofia-sm`.
- **Un seul fichier hors de la liste de `K2`**, avec son motif : `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java` portait pour `ce1-mots-invariables` un motif *"24 items sur 24 portent des propositions textuelles (...) aucune des 72 propositions"*, faux des que le paquet est de saisie (aucune proposition). Le motif est reecrit sur celui de `cp-mot-dicte` ; aucune assertion n'est affaiblie, `chaqueCompteEcritDansUnMotifVautLeCompteMesureSurLePaquet` le juge (24 items).

## 1. Criteres de l'amendement (9 a 14) et criteres repris (1 a 8)

Les `grep` de la story sur `bonneReponse:` rendent 0 par construction (le paquet n'a plus cette cle) : les valeurs ci-dessous sont lues sur `formeDeReference`, et le test du paquet les recompte sur les champs charges.

| Critere | Avant (`eb267ba7`) | Apres | Garde |
|---|---|---|---|
| 1 type saisie, 24 `formeDeReference`, aucun `distracteurs` | `type: saisie` 0 ; items a `distracteurs` | `type: saisie` 1 ; `formeDeReference` 24 ; `distracteurs` 0 | `c1...`, `miv1...` |
| 2 mot absent de l'ecran avant la reponse | 0 sur 24 (enonce), 0 sur 24 (indice) | 0 et 0, **et aucun des 24 mots n'est ecrit dans l'enonce ou l'indice d'un autre item** | `c2...` + jeu fautif |
| 3 aucun accent dans le mot attendu | 0 | 0 | `c3...` + jeu fautif |
| 4 formes acceptees | aucun champ | 23 vides, 1 non vide (`aujourd'hui`, U+2019) ; chaque forme est le mot a l'accent, la casse et l'apostrophe pres | `c4...` + jeu fautif |
| 5 `DEGRADE` retire, deux phrases d'en-tete reecrites | `^# DEGRADE` 1 | 0 ; *"n'est pas arbitree"* 0 ; la phrase sur la consigne est reecrite | `c5...` + jeu fautif |
| 6 `NRC-CE1-1` contre `cp-mot-dicte` | 0 | 0 | `nrcCe11...` + jeu fautif |
| 7 mention de la cellule de matrice | 0 | **non touche** : ecrite au verdict, jamais avant (`K9`, diff de la matrice vide) | - |
| 8 Done technique | - | jobs section 6 | - |
| 9 un seul mot ferme la phrase | 22 sur 24 admettent un autre mot | **non testable** ; pour chaque item, enonce reecrit et mots essayes (section 3) ; **a `nadia-prof` seule** | `c9...` : un seul trou par enonce |
| 10 `QP1`, *"Aujourd'hui"* | 1 apostrophe, aucune forme acceptee | 1 apostrophe U+0027 en reference, U+2019 en forme acceptee, **jugees justes par la mecanique reelle** (avec majuscule, en capitales, entre blancs) ; sans apostrophe, refusee | `qp1...` + jeu fautif |
| 11 liste de saisie | 2 entrees, 0 occurrence | 3 entrees, **1 occurrence** de `ce1-mots-invariables` dans le fichier | `LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest`, vert a 3 entrees |
| 12 aucune mecanique touchee | 0 | `git diff-tree` des quatre commits : 0 fichier `saisie.html`, `NormalisationDeSaisie` ou sous `domain/` ; `K10` : 0 fichier du garde de l'accent, `pom.xml`, `ADR-0065` | `git diff-tree --no-commit-id --name-only -r <commit>` |
| 13 jeu par `nadia-prof` sur le jar du commit du lot | aucun | **a elle** ; mon jeu de la section 4 n'est pas le sien | - |
| 14 gardes de texte de l'enfant | non mesure | `LON-CE1-1` (20 mots) et `LON-CE1-2` (trois phrases, la derniere est la question) tiennent sans exemption ; `TRV-CE1-4` et `-5` tenus par le test du paquet ; **`TRV-CE1-6` non garde** (dix prenoms, cinq et cinq, lus a la main) | `trv4...`, `trv5...` |

**Ce que `LON-CE1-2` a impose** : un enonce de plus d'une phrase doit finir par la question. Dix des 24 enonces du premier jet etaient rouges au garde de `bootstrap` ; ils sont reecrits en une phrase (virgule ou deux-points) ou finissent par `?`.

**Catalogue** : `grep -c "paquet: ce1-mots-invariables$"` rend 1 (0 avant) ; 158 entrees (157 avant) ; `saisie-mots-invariables-ce1`, titre *Écris le petit mot* (sans niveau, `ADR-0078`), consigne *Lis la phrase, puis écris le mot qui manque.*, **10 manches** (`DEC-0121` : 10 est une exigence, aucune derogation demandee).

## 2. Mots remplaces

Onze des 24 mots de S53 ne se fermaient pas et sont remplaces : `souvent`, `maintenant`, `alors`, `aussi`, `vraiment`, `parfois`, `ailleurs`, `pourtant`, `ensuite`, `surtout`, `seulement` (le plus souvent un synonyme de CE1 ferme aussi la phrase : *puis*, *après*, *donc*, *mais*, *très*, *beaucoup*). Ils sont remplaces par `loin`, `tard`, `avant`, `comment`, `combien`, `quand`, `sans`, `chez`, `entre`, `sous`, `peu`. Treize mots restent : `toujours`, `jamais`, `aujourd'hui`, `pourquoi`, `devant`, `dedans`, `dehors`, `partout`, `autour`, `pendant`, `depuis`, `presque`, `enfin`. **A `nadia-prof`** : `MIV-CE1-1` (s48, 5.12) listait vingt-quatre mots ; l'amendement de la story dit que les mots peuvent changer, je ne decide pas si la liste de la condition doit etre amendee.

## 3. Item par item : mots essayes et ecartes (critere 9)

*"Essayes et ecartes"* est ma lecture. La colonne de droite dit ce que je n'ai pas pu fermer en un mot de CE1 ; c'est a `nadia-prof` de juger si c'est un refus (*"un item ou un autre mot, bien écrit, complète la phrase"*).

| Item | Mot | Enonce (a trou) | Mots essayes et ecartes | Reste a juger par nadia-prof |
|---|---|---|---|---|
| 1 | toujours | Le soleil se lève ___ à l'est. | souvent, parfois, jamais (faux le matin) | rien en un mot (« chaque jour » en deux) ; item que sa premiere lecture disait ferme |
| 2 | jamais | « As-tu déjà vu un poisson marcher ? » « Non, ___ ! » | toujours (contredit « non »), rarement | « rarement » (hors niveau, sens bancal) |
| 3 | aujourd'hui | Hier, c'était lundi, demain ce sera mercredi : ___, c'est mardi. | hier et demain (dits dans la phrase), ce jour-ci | **« maintenant »** peut sembler juste |
| 4 | pourquoi | Inès met son manteau parce qu'il fait froid. ___ met-elle son manteau ? | comment, quand, où (la phrase donne la cause) | item ferme |
| 5 | devant | Omar marche derrière Mia, donc Mia marche ___ Omar. | derrière (dit dans la phrase), près (il faudrait « de ») | « avant » (peu naturel pour une place) |
| 6 | dedans | On a mis des bonbons à l'intérieur de la boîte : il y a des bonbons ___. | dehors, partout (contredisent l'intérieur) | **« ici », « là »** |
| 7 | dehors | Les enfants sortent de la classe et jouent ___, dans la cour. | dedans (contredit « sortent »), partout | « ailleurs », « ici », « là » |
| 8 | partout | Il y a des feuilles ___ : sur le sol, sur les bancs, sur les murs. | ici, là (un seul endroit, trois sont cites) | **« beaucoup »** |
| 9 | autour | On a planté des fleurs en cercle ___ de l'arbre : elles l'entourent. | loin (contredit), près (ne dit pas « entourent ») | « près » |
| 10 | loin | Léa habite à cent kilomètres de l'école : elle habite ___ de l'école. | près (cent kilomètres), devant (sans « de ») | item ferme |
| 11 | tard | Tom se couche à minuit : il ne se couche pas tôt, il se couche ___. | tôt (nié dans la phrase), minuit | item ferme |
| 12 | pendant | Chloé lit du début à la fin de la récréation : elle lit ___ la récréation. | avant, après (contredisent « du début à la fin »), dans | « durant » (hors niveau) |
| 13 | depuis | Il a commencé à pleuvoir ce matin et il pleut encore : il pleut ___ ce matin. | dès, pendant, avant (temps ou tour impossible) | item ferme |
| 14 | avant | Le jour qui vient ___ le mercredi, c'est le mardi. | après (faux), devant (pas une place) | item ferme (« juste avant » en deux mots) |
| 15 | enfin | Après trois heures d'attente, le train arrive ___ ! | alors, ensuite (pas de soulagement), finalement | **« maintenant »**, « finalement » (hors niveau) |
| 16 | presque | Il ne manque qu'une goutte pour que le verre soit plein : il est ___ plein. | très, tout, déjà (le verre n'est pas plein) | « bien », « quasiment » |
| 17 | comment | Je m'appelle Karim. Et toi, ___ t'appelles-tu ? | pourquoi, quand, combien, où (aucun ne va avec « t'appelles-tu ») | item ferme |
| 18 | combien | Ce livre coûte six euros. Et ce cahier, ___ coûte-t-il ? | pourquoi, comment, quand (la phrase d'avant parle du prix) | « pourquoi », « comment » sont grammaticaux, pas coherents |
| 19 | quand | Mon anniversaire est le douze mai. Et toi, ___ est ton anniversaire ? | pourquoi, combien | **« où », « comment »** sont grammaticaux |
| 20 | sans | Sofia met du sucre dans son thé, Ethan n'en met pas : il boit son thé ___ sucre. | avec (contredit « n'en met pas »), pour | item ferme |
| 21 | chez | Jules n'est pas à l'école, il est ___ lui, dans sa chambre. | avec (« avec lui » n'a pas de sens), près (sans « de ») | item ferme |
| 22 | entre | Le mardi est ___ le lundi et le mercredi. | avant, après, sur, sous (faux) | item ferme |
| 23 | sous | Le chat est assis sur le tapis : le tapis est ___ le chat. | sur (dit dans la phrase), contre | « dessous » (non standard devant un nom) |
| 24 | peu | Il ne reste qu'une goutte d'eau : il y en a très ___. | beaucoup (faux), « un peu » (ecarte par « très ») | « moins » (comparatif, sens bancal) |

**Items a risque, par ordre de ce que je ferais en premier** : 3 et 15 (*maintenant*), 6 et 7 (*ici*, *là*), 8 (*beaucoup*), 19 (*où*, *comment*). Ce sont des mots qui completent la phrase grammaticalement et dont le sens est moins bon que celui du mot attendu. Je n'ai pas de formulation qui les exclut sans ajouter une phrase que `LON-CE1-2` refuserait ; **je ne les ai pas ecartes par un `formesAcceptees`** : accepter un autre mot est une decision pedagogique, pas une graphie.

## 4. Jeu au jar

Jar du commit `83035447` (`clean verify` du meme arbre), port de jeu **18521**, libre avant (`netstat -ano`, aucune ligne), *"Tomcat started on port 18521"* et PID **47580** lus dans le journal du jar, arret par ce PID. Navigateur de mesure : port de debogage **19221**, profil dans le repertoire de l'instance, arrete par son PID.

- **Parcours serveur, 20 parties de 10 manches (200 manches)** : les 24 enonces sont servis (24 sur 24), jamais un enonce inconnu du YAML. Sur chacun : champ vide refuse, faute refusee avec l'indice, mot attendu en majuscules et entre blancs juge juste (*Bravo*), 200 reponses justes sur 200 ; *aujourd'hui* saisi avec l'apostrophe U+2019 juge juste. **Constat de mecanique, non decide par moi** : un champ vide valide compte comme un essai (la liste des tentatives porte une chaine vide).
- **Ecran a 744 x 551**, huit captures prises (liste du francais CE1 avec *Écris le petit mot* ; arrivee ; apres une faute avec l'indice ; revelation apres trois fautes avec *On écrit : toujours* ; quatre ecrans d'items, dont l'item 2 a guillemets et l'item 3 a deux-points), **quatre regardees a l'oeil** (arrivee, revelation, items 2 et 3), les autres lues dans le DOM. Aucun debordement horizontal sur les ecrans mesures, hauteur du document 551. Les espaces insecables tiennent sur les deux items regardes : le deux-points reste en fin de ligne avec son mot, les guillemets de l'item 2 se lisent « ... ». **Je n'ai pas regarde les 24 items a l'ecran** : 24 sont lus dans le DOM, 5 sont vus en capture (1, 2, 3, 24 et l'item de Sofia, 20).
- **Ce que le jeu ne dit pas** : ce que fait le clavier d'une tablette (mots proposes au-dessus des touches, apostrophe produite) ; `autocorrect="off"` est sur le champ (`ADR-0058` amende), l'effet n'est pas observable ici. **Reserve de `nadia-prof`, pas la mienne.**

## 5. Questions routees

1. **`nadia-prof`** : les lignes a risque de la section 3 ; la liste de `MIV-CE1-1` (section 2) ; l'indice de l'item 3, *"Il porte une apostrophe."*, que j'ai ecrit pour que l'enfant sache ou chercher la lettre de plus, et qu'aucune de ses conditions ne demande.
2. **`camille-po`** : la story ecrit ses mesures sur `bonneReponse:` (criteres 2, 3, 10) ; la cle n'existe plus dans un paquet de saisie. Elle dit si elle veut le texte corrige ; je n'ai modifie aucune story.
3. **`sofia-sm`** : le motif de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (hors liste de `K2`, ci-dessus) ; le `verify` complet est a `83035447`, la difference avec le sommet est `6554bf87` (commentaire) et les deux rapports : `git diff --name-only 83035447 HEAD` nomme ces fichiers.
4. **Ecart a mes propres messages de commit** : `c591a49b` dit *"dont 17 jeux fautifs"* (il y en a 19, dont 14 sur le contenu) ; le sujet de `83035447` porte une coquille (*"ttres"* pour *"tres"*). Je ne reecris pas un historique ; le compte juste est ici.
5. **Aucune question d'architecture** : aucune regle ArchUnit touchee (aucune classe de production), `ArchUnit` vert dans `bootstrap`.

## 6. Jobs de `ci.yml` lances, avec leur sortie

- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-garde-fou-accent-test.sh` sortent 0 ; `commit-atteignable.sh eb267ba7` sort 0 ; les sujets de `eb267ba7..HEAD` passent dans `verifier-prefixes.sh`, sortie 0 ; `verifier-garde-fou-accent.sh "eb267ba7..HEAD"` sort 0. Lance a `83035447` ; **relance sur le sommet de la branche, voir le message de remise**.
- **`chemins`** : `verifier-chemins-test.sh` sort 0, `verifier-chemins.sh` sur `git ls-files` sort 0. Lance a `83035447` ; relance sur le sommet.
- **`outils`** : 13 juges, 13 sortis 0, a `3d3d9382`.
- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine d'un worktree propre, au jeton de `RF1` (`mkdir` du repertoire du jeton, fichier `marc-dev-<heure>.txt`, repertoire supprime a la fin) : `BUILD SUCCESS`, 04:59 min, sortie 0, 2889 tests, 0 echec, JDK 21.0.10. **Un premier `verify` a ete interrompu par moi environ deux minutes apres son debut** (PID de l'enveloppe et PID de Maven, jeton restitue) pour inclure deux corrections de contenu avant de mesurer : il n'a rien rendu, le seul `verify` qui compte est le second.
- **Mesures partielles** (`-pl adapters/adapter-content,bootstrap -am`, perimetre nomme) : plusieurs passes pendant l'ecriture, la derniere verte avant le `verify` complet.
- La CI distante n'est pas lue. **Ce rapport ne prononce ni le Done technique ni le Done pedagogique.**

## 7. Isolation

Repertoire `s57-p4-marc-dev-a`, cree par `mkdir -p` a la premiere commande ; tout ce que j'ai ecrit hors du worktree y est : journaux de build et de jar, profil de Chrome, captures, scripts de jeu, fichier de PID. Les seuls ecrits ailleurs sont les fichiers du worktree et le repertoire du jeton de `RF1`. **racine : propre** : `find <racine> -maxdepth 1 -mindepth 1 -type f` ne rend rien, les repertoires sont `s57-p4-lea-dev-b`, `s57-p4-marc-dev-a`, `s57-p4-sofia-dev-ap` ; le repertoire parent des worktrees ne porte aucun fichier date du 2026-10-22. Liste refaite a la remise (message).

## 8. Ce que je n'ai pas fait

- `p4-10` (`S57-GARDE-NOMBRE-DE-MANCHES`) : il vient apres la livraison de `LP1`.
- Aucune ligne de matrice, aucune fiche, aucune mention de cellule (`K9`).
- Pas de mesure sur tablette, pas de cote a 720 ou 768, pas de capture des items non vus a l'ecran.
- Aucun Done : le jeu de `nadia-prof` (critere 13) se fait sur le jar du commit qui sera fusionne, qu'elle nomme dans son verdict (`RF13`).

## 9. Rejeu apres le refus de `nadia-prof` (fiche `FICHE-saisie-mots-invariables-ce1.md` sur `s57-nadia-p4-a`, lue en lecture seule)

**Commit du rejeu** : `ea34c750`, meme branche, meme worktree, base `6554bf87` pour le delta de tests (le sommet etait `56e9fd1b`, qui ne differe de `6554bf87` que par les deux rapports). Un dernier commit porte cette section. `verify` complet a `ea34c750`, voir 9.5.

### 9.1 Ce qui change, par motif

| Motif | Avant | Apres |
|---|---|---|
| M1 item 3 (*maintenant*) | Hier, c'était lundi, demain ce sera mercredi : ___, c'est mardi. | **Demain, ce sera mercredi : mardi, c'est ___.** (aujourd'hui) |
| M1 item 15 (*maintenant*) | enfin, train en retard | **mot remplace** par hier : *Nous sommes mercredi : mardi, c'était ___.* Aucune phrase d'attente ne ferme *enfin* sans laisser *maintenant*, *finalement* ou *voila* |
| M1 item 7 (*ensemble*, *la*) | dehors, *jouent ___, dans la cour* | **mot remplace** par moins : *Cinq billes, c'est ___ que dix billes.* Un adverbe de lieu en fin de phrase admet toujours *ici* ou *la* |
| M1 item 12 (*toute*) | pendant, *elle lit ___ la récréation* | *Chloé a lu de 10 h à 10 h 10 : elle a lu ___ dix minutes.* (pendant) |
| M1 item 16 (*bientot*) | presque, verre plein | *Il reste une seule miette de gâteau : on a ___ tout mangé.* (presque) |
| M2 item 18 | combien | **avec** : *On tient une cuillère dans la main : on mange la soupe ___ cette cuillère.* L'en-tete ne dit plus que le paquet n'a aucun mot decide par une autre ligne : il dit que *combien* est retire |
| `MIV-CE1-1` | le nombre des mots | **les 24 mots ecrits en tete**, gardes par `miv1LEnteteListeLesMots` |
| Reserve, item 1 | indice avec *exception* | *Pense à tous les matins de l'année, un par un.* |
| Reserve, item 8 | indice avec *la cour* | *Pense à tous les endroits cités, un par un.* |

Mots du paquet apres rejeu : toujours, jamais, aujourd'hui, pourquoi, devant, dedans, moins, partout, autour, loin, tard, pendant, depuis, avant, hier, presque, comment, avec, quand, sans, chez, entre, sous, peu. **Treize** mots de S53 sont remplaces : les onze du premier jet, plus *dehors* et *enfin*. L'indice de l'item 20 (sans) est reecrit (*Pense à ce qui manque dans son thé.*) : l'ancien nommait *avec*, devenu mot cible, et le garde du critere 2 le refusait. Aucun ajout a `formesAcceptees` : 23 vides, 1 non vide (U+2019).

### 9.2 Rejeu sur le jar de chaque mot refuse

Jar du commit `ea34c750`, port **18521** libre avant et apres, *"Tomcat started on port 18521"* et PID **2820** lus dans le journal, arret par PID. Script de jeu HTTP du repertoire de l'instance : **58 essais d'un autre mot, tous refuses** (*"Ce n'est pas ça"*), **0 accepte**. Les mots de `nadia-prof` y sont : item 3 *maintenant*, *hier*, *demain* ; item 15 *avant*, *demain*, *autrefois* ; item 7 *plus*, *mieux*, *autant*, *davantage*, *pire* ; item 12 *toute*, *depuis*, *durant*, *en*, *avant*, *apres* ; item 16 *bientot*, *deja*, *vite*, *bien*, *tout*, *quasiment* ; item 18 *sans*, *sur*, *dans*, *par*, *pour*. Les mots a risque de la section 3 aussi (*ici* et *la* sur dedans et partout, *beaucoup* sur partout, *ou* et *comment* sur quand, *pres* sur autour et devant). Parcours de controle : 20 parties, 200 manches, **24 enonces sur 24 servis**, 200 reponses justes sur 200. **Ce n'est pas le jeu de `nadia-prof`** : un mot que je n'ai pas pense a essayer reste possible.

### 9.3 Le pli a la revelation (l'utilisatrice : l'indice reste affiche)

Je n'ai pas touche `saisie.html`. Mesure par moi sur ce jar, 744 x 551, trois fautes par item, **24 items sur 24** :

- **6 items tiennent au pli** (hauteur du document 551, bas de l'indice 516) : *Le soleil se lève ___ à l'est.* (item 1), *Cinq billes...* (7), *Le mardi est ___...* (22), *Nous sommes mercredi...* (15), *Demain, ce sera mercredi...* (3), *Je m'appelle Karim...* (17).
- **18 items depassent** : hauteur 596, bas de l'indice 564, **13 px sous le pli** ; *Continuer* (360 px) et la forme juste restent au-dessus. Valeurs identiques sur les 18 : ce sont tous les enonces a deux lignes.
- **Ce qui ramenerait un item au pli** : un enonce d'une seule ligne, 47 caracteres au plus (le plus long qui tient). Les plus proches : l'item 14 (*Le jour qui vient ___ le mercredi, c'est le mardi.*, 50 caracteres), l'item 5 (*Omar marche derrière Mia, donc Mia marche ___ Omar.*, 51), l'item 24 (*Il ne reste qu'une goutte d'eau : il y en a très ___.*, 53) et l'item 2 (53). **Je n'en ai raccourci aucun** : cela retire du contexte qui ferme la phrase (l'item 5 perdrait *derrière*, l'item 14 *le mardi*, l'item 24 *une goutte d'eau*). Decision a `nadia-prof` et `ines-design`.
- **Ecart a la mesure d'`ines-design`** (21 sur 24 sous le pli) : j'en mesure 18, sur un jeu d'enonces modifie par ce rejeu (items 3, 7, 12, 15, 16, 18). Je ne dis pas que sa mesure est fausse ; la mienne est celle de ce jar-ci.

### 9.4 Garde `TYP-1` et mutation L10 (`yanis-qa`)

`verifierLesEspacesInsecables` lit l'enonce et l'indice de chaque item : une espace ordinaire, ou aucune, apres `«`, avant `»`, ou avant `?`, `:`, `;`, `!` fait echouer `typ1LesChevronsEtLesSignesDoublesPortentUneEspaceInsecable`. C'est dans le perimetre du lot (son propre test, aucun fichier partage). Jeu fautif commite : `ce1-mots-invariables-fautif-espaces.yaml`. **Mutation** : une espace ordinaire a la place de l'espace insecable dans le chevron de l'indice de l'item 2 ; `PaquetCe1MotsInvariablesTest` : 40 tests, **1 echec**, ce test, avec le message `TYP-1` ; fichier restaure, `cmp` identique a la copie de sauvegarde. Deux autres gardes neufs, chacun avec son jeu fautif : la regle de la nasale (`m` devant `b` ou `p`, mordue par *Combien*) et la liste de l'en-tete.

### 9.5 Tests et jobs

- **Delta du rejeu : +6 tests** (`PaquetCe1MotsInvariablesTest` de 34 a 40), base `6554bf87`. Cumul du lot sur `eb267ba7` : **+40**. Total de mon reacteur : 2895 (120, 31, 523, 1834, 381, 6 d'integration), 0 echec, soit 2855 plus 40, a verifier par `sofia-sm` sur l'arbre fusionne.
- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify`, racine d'un worktree propre, au jeton de `RF1` (acquis 12:54:02, libere 12:59:10) : `BUILD SUCCESS`, 05:03 min, sortie 0, JDK 21.0.10. Un seul `verify` pour ce rejeu.
- **`prefixes`, `chemins`, `outils`** : relances au sommet, voir le message de remise. Mesures partielles (`-pl adapters/adapter-content,bootstrap -am`) avant le verify : trois passes, la derniere verte.

### 9.6 Questions routees

1. **`nadia-prof`** : *comment* (item 17) contient `mm`, ou le son n'est pas la nasale de `FRA-CE1-22` mais un doublement de consonne. Je l'ai lu comme hors de l'exclusion *m devant m, b, p* ; mon garde ne vise que `m` devant `b` ou `p`. **Si elle le lit autrement, c'est un quatrieme remplacement.**
2. **`nadia-prof`** : l'item 12 reste le plus expose (*en* est un mot que je refuse au jar mais qu'un adulte peut juger possible) ; item 16 : *deja* est refuse au jar, mais *on a déjà tout mangé* reste une phrase qu'un adulte pourrait accepter malgre la miette.
3. **`nadia-prof`, `ines-design`** : le pli (9.3). Aucune action de ma part.
4. **`sofia-sm`** : `git diff --name-only ea34c750 HEAD` ne nomme que ce rapport. Aucune question d'architecture ; aucune story, aucun critere modifie.
