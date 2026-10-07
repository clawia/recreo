# [Sprint s53] Avis de faisabilite - theo-archi

- **Date** : 2026-10-07 14:02 (UTC+02:00), lu par `date`.
- **Auteur** : theo-archi. Branche `s53-theo-p10`, worktree dedie, base `5c1353a9` (tip de `round-two`), `git status
  --short` vide avant ecriture.
- **Perimetre** : les neuf stories `S53-REL-1` a `S53-REL-9` du planning, et la decision de `P10`. Aucun YAML de contenu
  n'est modifie par ce fichier ni par ce commit.
- **Mesures** : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,bootstrap -am test` (la commande de C4, `-am`,
  perimetre : les modules `domain`, `application`, `adapter-web`, `adapter-content`, `bootstrap`, soit le reacteur entier).
  Version : **21.0.10**, celle que l'enveloppe lit sur le JDK qu'elle a resolu avant de lancer Maven. Les comptes de
  contenu sont faits par un script Python sur `adapters/adapter-content/src/main/resources/contenu/*.yaml`, methode ecrite
  a cote de chaque chiffre.

## 1. A lire avant les stories : la base de s53 est rouge

**C4 n'est atteignable sur aucune branche de s53 en l'etat.** Sur `5c1353a9`, sans aucune modification :

| Module | Tests | Echecs |
|---|---|---|
| `adapter-content` | 1318 | 0 |
| `bootstrap` | 312 | **5**, dans trois classes |

Les cinq echecs, tous sur des paquets de la session hors sprint (tous dans la liste des 45) :

| Classe (bootstrap) | Paquets et items en defaut |
|---|---|
| `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` (2 tests, `LON-1`, `LON-2`, ADR-0089) | `cp-emc-personnes-differentes` items 1 a 4 (4 phrases, 20 a 24 mots), 6 et 11 (16 mots) ; `cp-qlm-modes-de-vie` item 12 (3 phrases, 17 mots) ; **`cp-qlm-vocabulaire-de-position` items 7 (16 mots), 12 (3 phrases), 13 (3 phrases, 21 mots), 14 (3 phrases, 18 mots)** |
| `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` (2 tests, `LON-CE1-2`, ADR-0101) | `ce1-mots-invariables` item 7 ("Un jouet est ___." : la derniere phrase n'est pas la question) |
| `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` (1 test, clause 2, ADR-0086) | `cp-emc-personnes-differentes` : onze amorces de distracteurs (ex. "aya parce que c", 7 distracteurs sur 14) qu'aucune bonne reponse ne partage |

**Cause** : ces trois garde-fous derivent leur population du **repertoire de contenu**, pas du catalogue
(`AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu`) ; un paquet non branche est donc juge. Le
commit `70b1cdc1` a declare les 45 paquets dans quatre garde-fous d'`adapter-content`, et rien dans `bootstrap`.

**Ce que cela fait aux stories** : seul l'item 7 de `cp-qlm-vocabulaire-de-position` est dans un point (`P5`). Les items 12
a 14 du meme fichier, `cp-emc-personnes-differentes`, `cp-qlm-modes-de-vie` et `ce1-mots-invariables` ne sont dans aucun
des onze points. **Je ne decide pas leur traitement** (corriger, retirer le paquet du depot, ou changer le critere C4) :
route a `sofia-sm` pour le perimetre et a `camille-po` pour la story. Je ne propose ni exemption ni cliquet : ces
garde-fous sont a plafond 0 par decision (`ADR-0067`, `ADR-0089`, `ADR-0101`), et inscrire du contenu non audite dans une
dette serait l'inverse de ce qu'ils gardent.

## 2. Avis par story

Legende des garde-fous cites. Population **R** : derivee du repertoire de contenu, juge les 45 paquets non branches.
Population **C** : derivee du catalogue, ne les voit pas. Aucun des treize fichiers touches n'a de test propre de paquet
(`grep -rl <nom>` sur les tests : seuls `LeRang...`, `LaValeur...` et `UneCondition...` les nomment).

| Story | Avis | ADR | Garde-fous qui peuvent mordre sur la correction | Declarations de `70b1cdc1` qu'une correction peut casser |
|---|---|---|---|---|
| `S53-REL-1` (P1) | faisable en l'etat | non | `AucunePropositionNEstEgaleAUneAutreDansUnItemTest` (R) mord si un distracteur egale la nouvelle bonne reponse, c'est le critere du planning ; `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` (R) si l'item retombe sur l'ecran d'un autre | motif de `ce1-accord-groupe-nominal` dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` : "24 items", "72 propositions". Intact tant que l'item garde deux distracteurs |
| `S53-REL-2` (P2) | faisable en l'etat | non | reclassement seul : rien (l'en-tete est un commentaire). Reecriture : `AucunEnonceDeCe1...` (R, 20 mots, 3 phrases, la derniere est la question) sur l'enonce ; `AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest` (R, ADR-0072) sur le `texteSupport`, que portent les 20 items | motif `ce1-pronoms` : "20 items", "60 propositions" |
| `S53-REL-3` (P3, P4) | faisable en l'etat | non | P3 : `AucunePropositionNEstEgale...` (R) sur le distracteur de remplacement. P4 : `ChaquePaquetPorteUnIndiceTest` (R) exige un indice non vide. **La borne de douze mots d'un indice (`TRV-CE1-5`) n'est encodee que dans des tests propres a des paquets de CE1** ; aucun ne lit ce paquet : le critere reste une lecture | motif `cm1-emc-principes-republique` : "11 items", "33 propositions". **Retirer "la liberte" sans la remplacer rend 32 et rougit `chaqueCompteEcritDansUnMotifVautLeCompteMesureSurLePaquet`** |
| `S53-REL-4` (P5, P10) | **faisable, sous la reserve de la section 1** | non (section 3) | P5 : `AucunEnonceDeCp...` (R) : l'item 7 est **deja rouge a 16 mots** ; sa reecriture doit tenir 15 mots et 2 phrases, ce qui contraint toute scene a trois elements. P10 : voir section 3 | motifs `cp-qlm-vocabulaire-de-position` ("14 items", "41 propositions") et `cp-qlm-situer-la-france` ("12 items", "35 propositions") ; compteurs du lot QLM CP (15 fichiers, 186 items) dans `UneConditionDeLot...` : **retirer un item ou un fichier les rougit** |
| `S53-REL-5` (P6) | faisable en l'etat | non | `ChaquePaquetPorteUnIndiceTest` (R). **Aucun garde-fou transversal ne compare l'indice d'un item a sa bonne reponse** (le seul champ voisin, `indicesNommentLaReponse`, est une declaration de paquet que rien ne verifie) : "l'indice ne contient pas la bonne reponse" et "douze mots" restent des lectures | `ce1-masses-contenances` : entree `MASSES_ET_CONTENANCES` (`MAT-CE1-10`, competence exacte) de `SousPartieDeMathematiquesCe1` ; entree 18 de `PAQUETS_A_ITEMS_ECARTES` ; repartition de rang (paquet numerique). **Un indice ne touche aucune des trois** ; changer une proposition ou la competence les toucherait |
| `S53-REL-6` (P7) | faisable en l'etat | non | aucun : le depot n'a pas de borne de longueur d'enonce au CM1 (`bootstrap/.../archi` n'en porte que pour le CP et le CE1) | motif `cm1-hda-epoque-civilisation` : "12 items", "36 propositions". La matiere est "Histoire des arts" : le paquet n'est pas dans le lot HG CM1 |
| `S53-REL-7` (P8) | faisable en l'etat | non | `AucunePhraseDuTexteSupportNeSuffitAGagner...` (R, ADR-0072) : la strategie jugee presse la seule proposition trouvee dans une phrase du support ; **reecrire un possessif dans le support sans le reecrire dans la proposition, ou l'inverse, deplace ce compte**. `AucunEnonceDeCe1...` (R) si un enonce change | motif `ce1-texte-documentaire` : "20 items", "60 propositions" |
| `S53-REL-8` (P9) | faisable en l'etat | non | aucun : `grep -rl DEGRADE --include=*.java .` ne rend rien ; la ligne `# DEGRADE` est un commentaire que le chargeur ne voit pas (25 fichiers la portent, `grep -l "^# DEGRADE" contenu/*.yaml`). **Si la `DEC` en faisait une cle YAML, ce serait une autre decision** (une cle de paquet que la fabrique ne lit pas, dette d'ADR-0024) et elle passerait par moi | aucune |
| `S53-REL-9` (P11) | faisable en l'etat | non, sauf si Q4 en sort une regle | aucun ne mord, aucun ne garde : `competence` est lu par `YamlContentPackLoader` et porte par `ContentDescriptor`, mais aucune classe d'`application` ni d'`adapter-web` ne le lit (`grep -rn "descri"` : seul `niveau()` est lu, par `PartieService`), donc il n'atteint aucun ecran. Le garde-fou de `TYP-1` (`AucunSigneDoubleNEstDetachableDeSonMotTest`) ne lit pas ce champ et sa population est C | `SousPartieDeMathematiquesCe1` compare la competence **exacte** des paquets de mathematiques CE1 ; aucun des 17 fichiers ci-dessous n'en est un. **Si la convention de Q4 etait etendue a ces paquets, chaque entree de l'enum serait a reecrire dans le meme commit** |

**Fait de P11, pour le decideur de Q4** (script Python : pour chaque ligne `competence:` de `contenu/*.yaml`, nature du
caractere qui precede chaque ":") : **17 fichiers** portent un blanc avant ":" dans `competence`. **13 portent l'espace
ordinaire** (les 4 du point et 9 autres, liste identique a celle du planning) et **4 portent deja l'espace insecable** :
`ce1-accord-groupe-nominal`, `ce1-classes-de-mots`, `ce1-inference`, `ce1-texte-narratif`. Je n'explique pas le "onze" du
backlog et je ne le corrige pas. Si la decision de Q4 pose une convention durable, elle s'encode dans un garde-fou de
contenu de population R, ecrit par moi dans le meme geste ; ce ne sera pas une `ArchRule` (ArchUnit lit du bytecode, pas du
YAML, meme motif qu'ADR-0086).

**Ordre des edits et listes de `70b1cdc1`** : les quatre motifs d'exemption cites ci-dessus sont gardes par
`chaqueCompteEcritDansUnMotifVautLeCompteMesureSurLePaquet` (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`),
qui recompte sur le chargeur reel. Une correction qui change le nombre d'items ou de propositions d'un des treize fichiers
porte la mise a jour du motif **dans le meme commit**, avec le compte mesure : c'est un recomptage, pas un affaiblissement
(C5). Aucune correction prevue ne fait entrer une proposition numerique dans ces paquets ; si cela arrivait,
`aucunPaquetNEstEcarteEnSilenceDuCalculDeRang` rougirait la declaration, et c'est voulu.

## 3. Decision de P10 - un item a un seul distracteur

**Decision : un item a un seul distracteur est admis par le chargeur, par le domaine et par tous les garde-fous du depot.
Aucune modification de code, aucun ADR, aucune regle ArchUnit.** La recevabilite pedagogique de chacun des deux items est
a `nadia-prof` (section 4) ; la decision technique tient pour les deux issues.

### Ce que le code admet, lu et mesure

- `QuestionContentSourceFactory.question` exige que `distracteurs` soit une liste, sans borne de taille. Le record
  `Question` n'a qu'un invariant (illustration et texte alternatif ensemble). **Un item a un distracteur se charge.**
- **La forme est deja servie.** Compte par script sur `contenu/*.yaml` (listes en ligne et sur plusieurs lignes, 0 forme
  non lue), puis `grep "paquet: <nom>$" catalogue.yaml` pour l'etat de branchement : **205 items a un seul distracteur, dont
  203 dans 18 paquets branches** et 2 dans la session (`cp-qlm-situer-la-france` item 12, `cp-qlm-vocabulaire-de-position`
  item 7). Le lot QLM CP en sert deja dans six paquets (`cp-qlm-saisons` 2, `cp-qlm-autrefois-aujourdhui` 7,
  `cp-qlm-cycle-de-vie` 6, `cp-qlm-etats-eau` 12, `cp-qlm-vivant-non-vivant` 11). Recoupement avec le code : le test
  `unMotifDontLesComptesSontExactsPasseLeGardeFou` ecrit "11 items, 28 propositions" pour `cp-emc-regle-souhait-loi`, soit
  5 items a un distracteur, le compte du script.
- `ADR-0063` borne par le haut seulement (au plus trois propositions). `ADR-0086` reconnait l'item a deux propositions
  comme une forme legitime ("deux etiquettes d'une dichotomie fermee").

### Effets sur les garde-fous

| Garde-fou | Issue A : garder un distracteur | Issue B : en ajouter un |
|---|---|---|
| Calcul du rang (`LeRang...`, `rangDe`) | **Aucun effet.** `rangDe` rend `null` des que `distracteurs().size() != 2`, et les deux paquets sont deja entierement hors du domaine (propositions textuelles), declares dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`. La declaration reste valide dans les deux issues (zero item classe) | meme chose : trois propositions textuelles restent hors du domaine |
| `AuPlusTroisPropositionsParItemTest` | vert : deux propositions, la regle ne borne que par le haut | vert : trois propositions |
| `LaFormeDesPropositions...` clause 3 | l'item est hors de sa population (`DISTRACTEURS_POUR_UN_MARQUEUR = 2`) | l'item y entre ; aucun marqueur de restriction dans ces propositions |
| Motifs de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` | rien a changer | **rouge** : "35 propositions" devient 36, "41 propositions" devient 42 (si P5 n'a pas deja change le compte du second). Mise a jour dans le meme commit que le YAML |
| `AucunePropositionNEstEgale...` | sans objet | le nouveau distracteur differe de la bonne reponse et de l'autre |
| `AucunEnonceDeCp...` | item 7 deja rouge a 16 mots, independamment de P10 | si l'item 7 recoit un troisieme element dans sa scene, il doit tenir 15 mots et 2 phrases |

### Ce qu'il faut pour que le paquet reste correct

1. **Issue A** : aucun diff. Le motif des deux entrees reste exact. Un defaut de prose existe deja : les deux motifs
   disent *"certains items portent un seul distracteur"* alors que chaque paquet en porte **un**. Le garde-fou des comptes ne
   lit pas cette prose (sa Javadoc le nomme comme non garde) ; la corriger est un edit de test que je laisse a l'auteur du
   commit qui touchera ces deux entrees, sans en faire une condition.
2. **Issue B** : dans le **meme commit** que le YAML, l'auteur (`lea-dev`) recompte et ecrit les nouveaux totaux dans les
   deux motifs, et **retire la parenthese "(certains items portent un seul distracteur, donc deux propositions)"**, qui
   deviendrait fausse sans qu'aucun test ne le dise. Puis `awk '/distracteurs: \["[^"]*"\]$/' <fichier>` ne rend rien sur
   les deux fichiers (critere du planning).
3. **Ordre** : P5 avant P10 sur `cp-qlm-vocabulaire-de-position` (planning). Le motif de ce fichier se recompte apres P5,
   pas avant.

### Pourquoi aucune regle n'est ecrite

Une regle "au moins deux distracteurs" rougirait 203 items servis que `nadia-prof` a laisses passer, dont les dichotomies
qu'elle a demandees (`la`/`là`, `FICHE-bandit-manchot-homophones-cm1.md`) : elle prononcerait un verdict pedagogique,
ce qu'un garde-fou n'a pas le droit de faire (motif d'ADR-0086). Une regle "un seul distracteur autorise" n'interdirait
rien. **Aucune regle durable ne sort de P10.**

**Non-preuve nommee** : le chargeur admet aussi une liste **vide** (un item a un seul bouton), et aucun garde-fou ne la
refuse. Mesure : 0 occurrence dans les 182 fichiers de `contenu/` (meme script). Je n'ecris pas de regle sur un usage non
constate. Ce qui me ferait changer d'avis : la premiere occurrence, ou une demande de `nadia-prof`.

## 4. Route a nadia-prof (question Q5 du planning)

La part pedagogique de P10 est a elle ; je ne la tranche pas. Ce qu'elle a deja ecrit, et qui ne tranche pas ces deux
items : elle a **refuse** l'item a un distracteur sur du contenu numerique (`FICHE-bandit-manchot-tables-multiplication-ce1.md`,
ligne 82 : *"Deux propositions, c'est une chance sur deux"*, et `SOP-CE1-R1` a s47), et elle a **tenu** des dichotomies a
deux propositions (`FICHE-bandit-manchot-homophones-cm1.md`, ligne 800).

Question posee : **les deux items sont-ils des dichotomies fermees recevables, ou exigent-ils deux distracteurs ?**

- `cp-qlm-situer-la-france` item 12 : *"La France fait partie de l'Europe. Qu'est-ce qui est le plus grand ?"*, propositions
  "l'Europe" et "la France". L'enonce ne nomme que deux espaces.
- `cp-qlm-vocabulaire-de-position` item 7 : *"Le chat est près de la porte, le chien est loin. Qui est le plus loin ?"*,
  propositions "le chien" et "le chat". L'enonce ne nomme que deux animaux, et P5 le reecrit d'abord.

Ce que sa reponse entraine techniquement est en section 3 ; dans l'issue B, le troisieme element est du contenu, a elle et
a l'auteure, pas a moi.

## 5. Ce que cet avis ne fait pas

- Il ne decide aucun des points P1 a P9 ni P11, ni le traitement de la base rouge (section 1).
- Il ne cree ni ADR ni regle ArchUnit, et ne modifie ni `ADR-index.md` ni aucun test.
- Les cinq echecs de la section 1 sont des garde-fous de contenu ; aucune `ArchRule` n'est en echec dans le meme lancement.
  La confirmation ArchUnit de cloture reste a faire sur l'arbre fusionne.
- Jobs de `.github/workflows/ci.yml` lances : aucun en tant que tel. Seul le contenu du job `build` a ete execute, sur deux
  modules et leurs dependances (`-am`), via `test` et non `verify`.
