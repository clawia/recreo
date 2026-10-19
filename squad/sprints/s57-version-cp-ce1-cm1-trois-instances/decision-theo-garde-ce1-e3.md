# [Sprint s57] Decision - ecart E3, forme du garde de `LON-CE1-3` a `-5` et `TRV-CE1-1`

- **Date** : 2026-10-19.
- **Auteur** : `theo-archi`.
- **Repond a** : `note-sofia-garde-ce1-e3.md` (formes `F1`, `F2`, `F3`) et `avis-theo-preparation-palier-3-ce1.md`, ligne `G5` et question `Q4`.
- **Base** : `round-two` a `ad65827e`, `git diff --quiet HEAD -- adapters` sort 0 (contenu et tests identiques au commit).
- **Ce que ce fichier ne fait pas** : il n'ecrit ni code, ni `ADR`, ni story. Il ne fixe ni les matieres soumises a chaque regle (section 3, a `nadia-prof`), ni le moment du lot (a `camille-po`), ni son affectation (a `sofia-sm`).

## 1. Decision

**`F1` : un test generique neuf, perimetre derive du descripteur `niveau: "CE1"`.** `F2` et `F3` sont ecartees.

| Forme | Verdict | Motif |
|---|---|---|
| `F1` | **retenue** | Une classe, deux populations nommees (propositions, `texteSupport`) plus une propriete de presence (`texteAVoixHaute`). Tout paquet CE1 depose dans le repertoire de contenu est juge des son depot, branche ou non |
| `F2` | ecartee | `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` porte `ADR-0101`, une population (`enonce`) et un cliquet (`ce1-longueurs`, 4) qui ne concernent pas ces regles. L'etendre oblige a renommer, a amender `ADR-0101` et a faire coexister deux cliquets de sens different dans un fichier. Moins de lignes, plus de pieces a relire |
| `F3` | ecartee | Le trou est silencieux par construction : un paquet sans `PaquetCe1<Nom>Test` passe. Mesure en section 2 : 12 paquets CE1 non branches, et le palier 3 en ajoute en QLM et EMC |

Ce qui me ferait changer d'avis : une regle de `nadia-prof` qui ne se decide pas sur l'item seul (comparaison entre items ou entre paquets). Elle sortirait de ce test, pas l'inverse.

Les `PaquetCe1*Test` existants ne sont pas retires par cette decision. Leurs assertions de `LON-CE1-3` a `-5` et `TRV-CE1-1` deviennent redondantes ; les retirer est un geste distinct, hors de cette story.

## 2. Mesures rejouees

**Commande** : `python mesure_e3.py adapters/adapter-content/src/main/resources/contenu adapters/adapter-content/src/main/resources/catalogue.yaml`, lancee a la racine, Python 3.14 et PyYAML 6.0.3. Le script est hors depot. Il reprend la convention d'`ADR-0089` telle que la codent `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.mots` et `.phrases` : un mot est un token separe par des blancs (U+00A0 compris) qui porte une lettre ou un chiffre ; une fin de phrase est un `.`, `?` ou `!` qui termine le texte ou qu'une majuscule suit. Ecart connu avec le Java : classe des majuscules ecrite `A-Z` plus `À-Þ` au lieu de `\p{Lu}`. Population : les fichiers `*.yaml` du seul repertoire de contenu servi (`git ls-files` n'en connait pas d'autre hors `src/test`), filtres sur `descripteur.niveau == "CE1"`. Propositions = `bonneReponse` et `distracteurs` ; les items de designation (`bonneZone`) n'en ont pas. Aucun `mvn` lance : ces chiffres ne sont pas un verdict de build.

| Mesure | Rendu |
|---|---|
| Paquets CE1 par descripteur | 47 : Francais 26 (19 branches, 7 non), Mathematiques 21 (16 branches, 5 non). Aucun QLM ni EMC |
| Propositions textuelles | 3048 : Francais 1716, Mathematiques 1332. Aucune non textuelle |
| `LON-CE1-5`, plus de 6 mots, Francais | **0** |
| `LON-CE1-5`, plus de 6 mots, Mathematiques | **1** : `ce1-sens-multiplication.yaml` ligne 113, distracteur `"3 + 3 + 3 + 3 + 3 + 3 + 3"`, 7 mots (les chiffres comptent, les `+` non) |
| `LON-CE1-5`, plus d'une phrase | 0, toutes matieres |
| `texteSupport` | 80, dans 4 paquets seulement : `ce1-texte-narratif` (`FRA-CE1-04`), `ce1-pronoms` (`-05`), `ce1-inference` (`-06`), `ce1-texte-documentaire` (`-07`), 20 chacun |
| `LON-CE1-3` (plus de 30 mots) | **0** |
| `LON-CE1-4` (plus de 4 phrases ; phrase de plus de 12 mots) | **0** ; **0** |
| `texteSupport` hors `FRA-CE1-04` a `-07` | 0 |
| `TRV-CE1-1`, champ `texteAVoixHaute` dans un paquet CE1 | **0** |
| Tests `PaquetCe1*Test` (`git ls-files` filtre) | 31 |

Les "zero faute" de mon avis tiennent pour le francais avec cette methode, sur 47 paquets au lieu de 14. La seule faute trouvee est en mathematiques, hors du perimetre ecrit de `LON-CE1-5`.

## 3. Perimetre

**Population** : tout paquet de tout repertoire de contenu servi (`AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu`) dont le descripteur porte `niveau: "CE1"` (`ADR-0078`). Jamais le nom de fichier, jamais le catalogue : un paquet non branche est juge.

| Regle | Objet juge | Matieres ecrites aujourd'hui | A trancher par `nadia-prof` |
|---|---|---|---|
| `LON-CE1-5` | `bonneReponse` et chaque `distracteur` : au plus 6 mots, une seule phrase | Francais (s48, section 1.3) | Mathematiques (exclue a s48, population non mesuree alors ; mesuree ici, 1 faute) ; QLM et EMC (l'arbitrage s57, ligne `I1`, reprend la regle : a confirmer comme valant pour le garde) |
| `LON-CE1-3` | `texteSupport` d'un item d'un paquet qui declare `FRA-CE1-04` a `-07` : au plus 30 mots | Francais | aucune : la regle nomme ses lignes |
| `LON-CE1-4` | meme texte : au plus 4 phrases, aucune de plus de 12 mots | Francais | aucune |
| `TRV-CE1-1`, supports | aucun `texteSupport` hors `FRA-CE1-04` a `-07` | Francais (s48) ; QLM et EMC (s57, *"aucun `texteSupport` sur ces lignes"*) | Mathematiques |
| `TRV-CE1-1`, voix | aucun champ `texteAVoixHaute` | Francais (s48) ; QLM et EMC (s57 `I1`) | Mathematiques |

**Toute matiere de CE1 est classee, aucune n'est omise.** Pour chaque regle, le test porte deux tables fermees de valeurs de `descripteur.matiere` : les matieres soumises, et les matieres exclues avec leur motif ecrit (`ADR-0051`). Un paquet CE1 dont la matiere n'est dans aucune des deux est rouge : un futur paquet d'arts plastiques CE1 ne passe pas en silence. Les valeurs sont celles du descripteur (`"Mathématiques"`, `"Questionner le monde"`, `"Enseignement moral et civique"`), pas les libelles de `matieres.yaml` (`"Maths"`, `"Vivre ensemble"`), qui different.

## 4. Mecanisme qui garde la decision

**Ce n'est pas une regle ArchUnit** : ArchUnit lit du bytecode, et la longueur d'une proposition YAML n'y est pas lisible (meme motif qu'`ADR-0101`). Le compte de regles ArchUnit est inchange.

**Le garde** : un test JUnit, `bootstrap/src/test/java/com/recreo/archi/LesPropositionsEtLesTextesSupportsDeCe1TiennentLeursBornesTest.java`, a cote du garde de `LON-CE1-1` et `-2`. Il vit dans `bootstrap` pour voir tous les modules de contenu. Il **appelle** `mots(String)` et `phrases(String)` d'`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` et ne recopie pas la convention (`ADR-0061`). Il est compte par `LesGardeFousSontMisALEpreuveTest` (`ADR-0013`).

**Ce qu'il interdirait reellement** :

1. Un paquet QLM CE1 dont un distracteur compte 7 mots : rouge, meme sans entree de catalogue ni test nomme.
2. Un `texteSupport` de 31 mots, de 5 phrases ou dont une phrase compte 13 mots sur `ce1-inference` : rouge.
3. Un `texteSupport` ajoute a un paquet EMC CE1 ou a `ce1-negation` (`FRA-CE1-11`) : rouge.
4. Un champ `texteAVoixHaute` sur un item CE1 : rouge.
5. Un paquet CE1 d'une matiere absente des deux tables : rouge.
6. Une matiere declaree exclue qui figure aussi parmi les soumises, une matiere declaree qui ne designe plus aucun paquet, un motif vide : rouge (`ADR-0051`, trois sens). Exception nommee : une matiere soumise sans paquet est admise si elle est dans une table `POPULATION_VIDE` avec motif ; elle devient rouge des qu'un paquet de cette matiere existe et qu'elle y reste.

**Mise a l'epreuve sur du materiau reel, pas sur des fixtures de ma main** : les exemples refuses par `nadia-prof` elle-meme. Pour `LON-CE1-5`, *"Le petit chat noir dort sur le lit."* (8 mots, s48 section 1.3). Pour `LON-CE1-4`, le texte de 23 mots dont la premiere phrase compte 17 mots (s47, section 3). Pour `LON-CE1-3`, un vrai `texteSupport` d'un paquet servi, allonge d'une phrase. Chaque cas est rouge sur le texte refuse et vert sur sa version corrigee.

**Le cliquet** : plafond 0 sur le francais, mesure en section 2. Si `nadia-prof` soumet les mathematiques a `LON-CE1-5`, la faute de `ce1-sens-multiplication` est corrigee dans le commit du garde ou enregistree en dette exacte de 1 ; ce choix est a `camille-po`, pas au garde.

**L'`ADR`** est ecrit dans le commit du test, au numero suivant de `ADR-index.md` a ce moment, statut Accepte avec sa regle. Pas avant.

## 5. Criteres d'acceptation de la story a ecrire

A reprendre par `camille-po`. Je ne redige pas la story.

| Code | Critere |
|---|---|
| `CA1` | Un seul fichier de test neuf, chemin de la section 4. Aucun paquet, aucun catalogue, aucun gabarit, aucune feuille dans le commit |
| `CA2` | Le perimetre est derive du descripteur `niveau: "CE1"` sur tous les repertoires de contenu servis, ni du nom de fichier ni du catalogue. Un paquet CE1 non branche est juge (verifie sur un des 12 existants) |
| `CA3` | La convention de comptage est appelee depuis `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`, pas recopiee |
| `CA4` | Les tables de matieres par regle reprennent l'avis ecrit de `nadia-prof` sur la section 3, cite par chemin dans la Javadoc. Sans cet avis, la story n'entre pas en sprint |
| `CA5` | Les six interdictions de la section 4 ont chacune un cas de test rouge, sur les materiaux reels nommes, et son corrige vert |
| `CA6` | Le plafond de chaque regle vaut exactement le nombre de fautes mesure par le garde lui-meme sur le reacteur complet (`bash outils/mvn-jdk21.sh -B clean verify`), chiffre et methode ecrits dans la Javadoc |
| `CA7` | L'`ADR` est ecrit dans le meme commit, `ADR-index.md` mis a jour |
| `CA8` | Tous les jobs de `.github/workflows/ci.yml` sont verts et nommes dans le rapport du developpeur |

## Decisions prises

Forme `F1` retenue. Aucune `ADR` ni `DEC` creee ou amendee. `ADR-index.md` inchange. Question ouverte a `nadia-prof` : section 3, colonne de droite.
