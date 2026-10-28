# Rapport de marc-dev, palier 5 : entrees de catalogue de huit lignes de questionner le monde CE1 et de l'horloge

Base : `round-two` a `d6933bda`, branche `s57-p5-marc-catalogue2` (commit refait par-dessus la lecture de `nadia-prof`, `2f2977fb`). Story : `S57-QLM-CE1-ENTREES-CATALOGUE`,
plus l'entree de l'horloge `MAT-CE1-11` (decision de l'utilisatrice du 2026-10-28 : engager ces entrees
avant la cloture du palier 5). Aucun Done n'est prononce ici : le pedagogique est a `nadia-prof`, le
technique est juge par la CI.

## 1. Le diff, explique

Deux fichiers touches, un commit.

1. `adapters/adapter-content/src/main/resources/catalogue.yaml` : neuf entrees ajoutees en fin de
   fichier, `bandit-manchot`, `nombreDeManches: 10`, forme des entrees d'EMC CE1. Le champ de type
   (`question`) n'est pas une cle de l'entree de catalogue : il est dans le descripteur du paquet, et
   aucune entree existante ne l'ecrit. Je n'ai pas ajoute de cle.
   - huit entrees QLM CE1, consigne `"Lis bien, puis choisis la bonne réponse."` (critere 3) ;
   - l'horloge : titre `"L'heure sur l'horloge"`, consigne `"Regarde les deux aiguilles, puis choisis
     la bonne heure."`, 10 manches (reserve d de `nadia-prof`, mot pour mot).
2. `bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java` :
   l'inventaire des chaines exposees par le port entrant est etendu, sans toucher une garde. Sans cela
   le test sortait rouge sur trois methodes : chaque identifiant, titre, consigne et competence
   (`descripteur.competence`) servi doit y figurer. Ajoutes : 9 identifiants, 9 titres, 1 consigne (celle
   de l'horloge ; celle des huit QLM y etait deja), 9 competences (les huit libelles du referentiel
   copies mot pour mot, et le libelle du descripteur du paquet de l'horloge, sans point final).

Identifiants des entrees, convention des entrees existantes (`bandit-manchot-<sujet>-ce1`) :

| Paquet | Identifiant d'entree |
|---|---|
| `ce1-qlm-etapes-de-developpement` | `bandit-manchot-etapes-de-developpement-ce1` |
| `ce1-qlm-ce-que-mangent-les-animaux` | `bandit-manchot-ce-que-mangent-les-animaux-ce1` |
| `ce1-qlm-besoins-de-la-plante` | `bandit-manchot-besoins-de-la-plante-ce1` |
| `ce1-qlm-familles-d-aliments` | `bandit-manchot-familles-d-aliments-ce1` |
| `ce1-qlm-eau-trois-etats` | `bandit-manchot-eau-trois-etats-ce1` |
| `ce1-qlm-aimant` | `bandit-manchot-aimant-ce1` |
| `ce1-qlm-dangers-electricite` | `bandit-manchot-dangers-electricite-ce1` |
| `ce1-qlm-paysages` | `bandit-manchot-paysages-ce1` |
| `ce1-lire-l-heure` | `bandit-manchot-lire-l-heure-ce1` |

## 2. `ce1-qlm-generations` (`QLM-CE1-12`) : non ecrite

Je n'ai pas ecrit l'entree. La story dit que le releve de `S57-QLM-CE1-12-ACCORD` vient d'abord
(`DEC-0120` A.283, D3) : le Done pedagogique de la ligne ne vaut pas entree tant que cet accord n'est
pas releve. Le paquet existe, l'entree n'existe pas ; `grep -c "paquet: ce1-qlm-generations$"` sur
le catalogue rend 0. Le commentaire que j'ai ajoute dans l'inventaire le dit.

## 3. Titres definitifs, ceux de nadia-prof

Les titres et consignes sont ceux de la section 1 de
`lecture-nadia-entrees-de-catalogue-ce1-qlm-et-horloge.md`, compares un a un par script au
catalogue et a l'inventaire (9 sur 9 identiques, apostrophes droites comme dans sa source). Deux
titres que j'avais proposes sont remplaces par elle : `QLM-CE1-01` et `QLM-CE1-06`. Les sept autres
sont acceptes tels que je les avais ecrits.

Ce commit descend de sa lecture : `git merge-base --is-ancestor 2f2977fb HEAD` sort avec le code 0.

| Ligne | Titre | Competence de la ligne |
|---|---|---|
| `QLM-CE1-01` | Grandir chez les animaux et les plantes | Ordonner les etapes du developpement d'un animal ou d'une plante |
| `QLM-CE1-02` | Ce que mangent les animaux | Relier des animaux familiers a ce qu'ils mangent |
| `QLM-CE1-03` | Ce qu'il faut à une plante | Identifier ce dont une plante a besoin |
| `QLM-CE1-04` | Les familles d'aliments | Classer des aliments dans leur famille |
| `QLM-CE1-05` | Les trois états de l'eau | Reconnaitre l'eau sous ses trois etats |
| `QLM-CE1-06` | Ce que l'aimant attire ou non | Reconnaitre les objets qu'un aimant attire et n'attire pas |
| `QLM-CE1-08` | Les dangers de l'électricité | Connaitre les dangers de l'electricite de la maison et les gestes qui les evitent |
| `QLM-CE1-10` | Les paysages | Identifier quelques paysages a partir de leurs elements |
| `MAT-CE1-11` (horloge) | L'heure sur l'horloge | Lire l'heure sur une horloge |

## 4. Tests et inventaires touches

Touche, dans le meme commit : `TexteAccentueExposeParLePortEntrantTest` (inventaire etendu, voir 1).

Lus et laisses intacts, parce que verts sans edition : `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest`,
`ChaqueEntreeDuCatalogueChargeBienSonPaquetTest`, `ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest`,
`ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`, `AucunTitreDeJeuNePorteSonNiveauTest`,
`LIllustrationDeContenuResoutEtAucuneNeDortTest`. Aucun test ne compte les entrees du catalogue, aucune
liste de jeux ni compteur n'est codee en dur pour la page de questionner le monde ou de mathematiques CE1.
Aucune garde assouplie.

## 5. Mesures (JDK 21.0.10, rapporte par la machine virtuelle des tests)

Toutes sur la base `d6933bda` plus ce lot, avec `-am`, apres les deux changements de titre.

1. `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` (perimetre : `domain`, `application`,
   `adapter-web`, `adapter-content` en entier, puis `bootstrap` en entier) : BUILD SUCCESS, `bootstrap`
   395 tests, 0 echec, 0 erreur, 0 ignore.
2. Delta de tests : 0 ajoute, 0 retire. Le lot ajoute des donnees a un inventaire et au catalogue, pas
   de methode de test.

## 6. Ce que je n'ai pas lance

- `mvn -B clean verify` complet (lance par `sofia-sm`).
- Les jobs `outils` et `build` de la CI. Aucun job n'est declare vert ici.
- Le jar : aucun rendu a l'ecran. Les titres que l'enfant verra n'ont pas ete regardes sur la page.
- Ni la matrice, ni les en-tetes `DEGRADE`, ni les commentaires "le paquet n'est pas au catalogue" des
  fichiers de paquets : hors du lot demande.

## 7. Duree brute

Environ 5 minutes pour `bootstrap -am test` complet.
