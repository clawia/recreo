# Rapport qualite du palier 4, lot LP4 (S57-BR-EMC-CE1-02, paquet ce1-emc-regle-sanction)

Auteur : `yanis-qa`, transverse. Date : 2026-10-22. Branche du lot : `s57-sofia-emc-ce1-02`, sommet `9f3fdd4b`, base `eb267ba7`. Mesures faites dans un worktree propre de ma branche `s57-yanis-p4` au sommet `9f3fdd4b`. Le worktree du lot n'a pas ete touche.

Ce rapport dit si le job `build` de la CI est vert au sommet de LP4 et si les verificateurs de `PaquetCe1EmcRegleSanctionTest` mordent. **Il ne prononce pas le Done pedagogique** (`nadia-prof`, seule) et **il n'annonce pas la porte technique** : les jobs de `.github/workflows/ci.yml` que je n'ai pas lances sont nommes section 4.

## 1. Resultat

1. `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, sommet `9f3fdd4b` : **exit 0, BUILD SUCCESS**, **2908 tests, 0 echec, 0 erreur, 0 ignore**.
2. Les 7 gardes annonces par le developpeur (cible/tache, familles, mots interdits, longueur, premier mot, TYP-1, equilibre des prenoms) mordent sur leur cas nominal. La mutation trouve **deux gardes qui ne mordent pas sur une partie de leur population** (section 3) : G1 (sanction = reparation, aveugle sur 7 items sur 24) et G2 (mots refuses, trois sondes de formulation hors liste vertes). G3 : la regle de longueur de la bonne reponse est a zero marge. Deux affirmations de l'en-tete du paquet ne sont lues par aucun test.
3. LP1 et LP2 : lecture plus legere faite (section 5). LP1 : un garde absent (chevron). LP2 : rien d'anormal.

## 2. Job `build`, mesure

Commande : `bash outils/mvn-jdk21.sh -B clean verify`, lancee a la racine du worktree, sous le jeton de `verify` de `composition-palier-4.md` (RF1) : `mkdir` du repertoire de jeton reussi au premier essai (aucune attente), fichier `yanis-<heure>.txt` ecrit, repertoire supprime a la fin du `verify`. **Premiere utilisation de la mecanique** : elle a fonctionne sans contention observee. Je n'ai pas releve les processus `java` du poste pendant la mesure, donc je ne dis rien de la charge.

| Module | Tests (lignes `Tests run` de fin de module) |
|---|---|
| domain | 120 |
| application | 31 |
| adapter-web | 523 |
| adapter-content | 1847 |
| bootstrap | 381 |
| integration (Failsafe, dans bootstrap) | 6 |
| **Total** | **2908** |

- **Methode du total** : somme des six lignes ci-dessus, recomptee par l'attribut `tests` des 320 fichiers `TEST-*.xml` de `target/surefire-reports` et `target/failsafe-reports` du worktree (2908, 0 echec ou erreur, 0 ignore). Perimetre : reacteur complet, cinq modules plus le module d'integration.
- **JDK** : 21.0.10, la version que rapporte la machine virtuelle des tests (propriete `java.version` presente dans les 320 rapports). La premiere ligne de l'enveloppe dit aussi 21.0.10.
- **Duree** : 541 s mur (horodatages avant et apres la commande), `Total time: 08:55 min` selon Maven. `theo-archi` annonce 11 min 39 s sur une copie de `50dbd73f` (`avis-theo-palier-4.md` 3.5). Je n'explique pas l'ecart.
- **Delta contre 2855** (mesure de `theo-archi`, `avis-theo-palier-4.md` 3.5, et de `sofia-sm`, `cloture-palier-3.md`) : **+53**, tout dans adapter-content (1794 a 1847). Je n'ai **pas mesure la base** `eb267ba7`. Le rapprochement tient sur `git diff --stat 03de0f6d eb267ba7 -- adapters domain application bootstrap pom.xml outils .github` : sortie 0, aucune ligne, donc le code et les tests de la base sont ceux de `03de0f6d`, ou 2855 a ete mesure. Les 53 sont les 53 `@Test` de `PaquetCe1EmcRegleSanctionTest` (`grep -c "@Test"`), tous executes. Les deux autres fichiers de test touches (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `TexteAccentueExposeParLePortEntrantTest`) ajoutent des entrees d'inventaire et aucun `@Test`. **Ce delta est a rapprocher de ma mesure du sommet, pas d'une mesure de la base.** `sofia-sm` etablit le total sur l'arbre fusionne.
- Tests instables : aucun observe. Une execution complete ne prouve pas la stabilite ; je n'ai relance aucun test pour le faire passer.
- Couverture chiffree : non mesuree (pas de rapport de couverture dans cette mesure).

## 3. Mutations de `PaquetCe1EmcRegleSanctionTest`

**Methode.** Pour chaque garde annonce, une mutation du paquet `ce1-emc-regle-sanction.yaml` (ou, pour les trois dernieres lignes catalogue, de l'entree du catalogue), appliquee dans mon worktree, puis :
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am -Dtest=PaquetCe1EmcRegleSanctionTest -Dsurefire.failIfNoSpecifiedTests=false test`
(perimetre : classe seule, 53 tests, modules domain, application, adapter-content ; `-am` present). Les tests rouges et leurs messages sont lus dans le rapport Surefire XML de la classe. Apres chaque mutation, `git checkout -- <fichier>` ; `git status --short` vide a la fin. **Les mutations portent sur les donnees reelles du paquet, pas sur les jeux fautifs commites** (`ADR-0013`, amendement : le perimetre d'un test inclut les valeurs d'entree). 48 executions.

Legende : "rouge, motif" = la classe rougit pour la raison visee, lue dans le message. Les items sont designes par rang dans le fichier (1 a 24).

| Id | Garde | Mutation | Resultat |
|---|---|---|---|
| M01 | cible/tache | cible `regle` de l'item 1 annoncee tache 2 | rouge, motif : "annonce la tache 2 pour la cible regle, qui releve de la tache 1" |
| M02 | cible/tache | mention de cible retiree sur l'item 2 | rouge, motif : "porte exactement une mention de tache et de cible" (5 tests rouges, tous ceux qui lisent la cible) |
| M03 | cible/tache | bonne reponse de l'item 2 (cible sanction) devient "la regle" | rouge, motif : "n'est pas celle que la cible commande" |
| M04 | cible/tache | bonne reponse de l'item 13 (cible reparation) devient "a rappeler ..." | rouge, motif : meme message ; la regle de longueur rougit aussi (13 sur 24) |
| M05 | cible/tache (FER-1) | 4 items de cible rappel supprimes | rouge, motif : "FER-1 : au moins trois items de cible rappel" |
| M06 | cible/tache (FER-3) | 11 items de tache 2 supprimes (1 reste) | rouge, motif : "FER-3 ... (12 et 1)" ; plancher de 20 items rouge aussi |
| M07 | familles | distracteurs de l'item 1 permutes, declaration inchangee | rouge, motif : "declare bc, ses distracteurs sont de familles cb" |
| M08 | familles (cible) | item 13 : distracteur de famille f, declaration `f, e` | rouge, motif : "que sa cible n'admet pas" |
| M09 | familles | distracteur "a faire peur aux autres" (aucune famille) | rouge, motif : famille lue `?` |
| M10 | premier mot | distracteur "une faute" a la place de "la faute" | rouge, motif : "n'a pas le meme premier mot" (et famille) |
| M11 | premier mot, isole | distracteur "A changer la regle" (majuscule, famille intacte) | rouge, motif : premier mot seul |
| M12 | mots refuses | indice : "Tom, mechant" | rouge, motif : "mot refuse d'avance" |
| M13 | mots refuses | distracteur "a punir Lina" | rouge, motif : meme message (famille et longueur rougissent aussi) |
| M14, M15, M16 | mots refuses | trois sondes de formulation hors liste, sur l'item 3 : "tire l'oreille" (punition physique), "sans dessert" (privation de repas), "gronde tous les eleves" (sanction collective) | rouge, **mais pour la longueur de l'enonce** (item de 20 mots porte a 24 ou 25) : mauvaise raison |
| M14b, M15b, M16b | mots refuses | memes trois sondes sur l'item 2, sous 20 mots | **VERT, 53 sur 53. Le garde ne mord pas.** |
| M17 | longueur | enonce de l'item 1 a 21 mots | rouge, motif : "LON-CE1-1 ... plus de vingt mots" |
| M18 | longueur | enonce de l'item 1 en 4 phrases, memes mots | rouge, motif : "LON-CE1-2 ... trois phrases" |
| M19 | longueur | proposition de 7 mots | rouge, motif : "LON-CE1-5 ... six mots" |
| M20 | longueur | indice de 14 mots | rouge, motif : "TRV-CE1-5 ... douze mots" |
| M21 | longueur | indice contenant la proposition "la faute" | rouge, motif : "contient une proposition de son item" |
| M22 | TYP-1 | espace ordinaire avant `?` dans l'enonce de l'item 1 | rouge, motif : "espace ordinaire avant ?, :, ; ou !" |
| M23 | TYP-1 | chevron ouvrant sans espace insecable | rouge, motif : "chevron sans espace insecable" |
| M24 | TYP-1 | espace ordinaire avant `:` dans un indice | rouge, motif : meme message qu'en M22 |
| M25 | prenoms | "Maya" remplacee par "Alice" (hors table) | rouge, motif : "6 prenoms feminins distincts pour 8 masculins" |
| M26 | prenoms | "Samir" remplace par "Lea" (hors table) : l'equilibre 7 et 7 tient | rouge, motif : "chaque prenom de la table figure dans un item" (et "ne nomme aucun personnage" sur l'item 22) |
| M27 | chevrons | regle de l'item 1 sans chevrons | rouge, motif : "cite la regle entre chevrons" |
| M28 | sanction = reparation | item 3, "le rendre" devient "faire cent pompes" (+1 mot) | rouge, **mais seulement par la longueur** (21 mots) : `rsa1LesSanctionsDesSituationsSontDesReparations` reste vert |
| M28c | sanction = reparation | item 3, "le rendre" devient "deux pompes" (meme nombre de mots) | **VERT, 53 sur 53. Le garde ne mord pas.** |
| M28b | sanction = reparation | item 2 (cible sanction), "la nettoyer" devient "faire cent pompes" | **VERT, 53 sur 53. Le garde ne mord pas.** |
| M28d | sanction = reparation | item 1 (cible regle), "recoller la page" devient "copier la page" | rouge, motif : "n'est pas une reparation" |
| M29 | EMC-5 | item 16 (rappel) : "claque" devient "casse" la porte | rouge, motif : "decrit ce qui est abime" |
| M30 | EMC-5 | item 15 (reparation) : "dechire" devient "pris" | rouge, motif : "ne decrit rien d'abime" |
| M31 | EMC-1 | mot du foyer ("chez") dans un indice | rouge, motif : "mot du foyer" |
| M32 | EMC-1 | "Tu vois," dans un enonce | rouge, motif : "s'adresse a l'enfant" |
| M33 | RSA-CE1-2 | "Un eleve" a la place de "Tom" | rouge, motif : "ne nomme aucun personnage" |
| M34 | bonne reponse la plus longue | un distracteur raccourci d'un caractere (item 13) | rouge, motif : "13 items ... sur 24" |
| M35 | champs servis | `texteSupport` ajoute | rouge, motif : "TRV-CE1-1" |
| M36 | catalogue | titre "La regle et la sanction CE1" | rouge, motif : titre porte le niveau |
| M37 | catalogue | espace ordinaire avant `:` dans la consigne | rouge, motif : espace insecable |
| M38 | catalogue | `nombreDeManches: 5` | rouge, motif : "nombreDeManches vaut 10" |
| M39 | id du paquet | `ce1-emc-regle-sanctions` | rouge, motif : id different du nom de fichier |
| M40 | ligne de referentiel | `EMC-CE1-03` | rouge, motif : `lignesDeReferentiel` |
| M41 | affirmation d'en-tete du paquet (lignes 20 et 21) : "le geste demande par l'adulte est repris dans la question" | item 2, derniere phrase "Cela, c'est quoi ?" | **VERT, 53 sur 53 : aucun test ne lit cette affirmation** |
| M45 | affirmation d'en-tete (lignes 32 et 33) : "treize et onze" | les 11 "La maitresse" deviennent "Le maitre" | **VERT, 53 sur 53 : aucun test ne lit la repartition.** Le test la declare hors de ce qu'il garde (en-tete de classe, TRV-CE1-6) ; je l'ai verifiee par `grep` sur les lignes `- enonce:` : 13 "Le maitre" et 11 "La maitresse" |

### Gardes qui ne mordent pas

**G1 - "les sanctions des situations sont des reparations" (`verifierQueLesSanctionsSontDesReparations`).** Deux causes, toutes deux lues dans `GESTE_REPARATEUR` et son usage :
- Le motif est cherche **dans l'enonce entier**, pas dans la phrase de sanction. Les six items de cible sanction (tache 1) reprennent le geste dans la question ("Nettoyer la table, c'est quoi ?") : la question seule satisfait le garde (M28b). L'item 12 le satisfait aussi par la phrase de la faute ("a efface expres").
- La sous-chaine `rendr` apparait dans "prendre" : l'item 3 cite la regle "On demande avant de prendre", le garde est satisfait par la regle et non par la sanction (M28c).
- Etendue : 7 items sur 24 (2, 3, 4, 6, 8, 10, 12) ne peuvent pas etre refutes sur leur phrase de sanction. Methode : expression reguliere du test appliquee en Python a chaque phrase de l'enonce, sur le YAML de `9f3fdd4b` ; les items 14 et 24 ("reprendre") contiennent le geste legitimement et ne sont pas comptes. Les items 1, 5, 7, 9, 11 et ceux de la tache 2 sont mordus (M28d pour l'item 1).
- Correction proposee, non appliquee (lot de `sofia-sm`) : chercher le geste dans la phrase qui commence par "Le maitre" ou "La maitresse", et ancrer les racines courtes (`rendr` en `(?<!\p{L})rendr`, comme `CE_QUI_EST_ABIME`).

**G2 - mots refuses d'avance (`REFUS_D_AVANCE`).** Trois sondes de la meme famille que la liste (punition physique, privation de repas, sanction collective) ne sont pas vues : "tire l'oreille", "sans dessert", "gronde tous les eleves" (M14b a M16b). Le test ecrit lui-meme qu'il garde "sur les mots" et ne lit pas une intention ; c'est une limite lexicale, pas un defaut de logique. **L'en-tete du paquet (lignes 27 a 29) ecrit "aucune situation ... ne contient une humiliation, une punition physique, une sanction collective ou une privation de repas"** : ce que le test prouve est l'absence des mots de sa liste, et la lecture de `nadia-prof` reste la seule garde du reste. Les mots de la liste sont mordus (M12, M13).

**G3 - bonne reponse strictement la plus longue dans la moitie des items au plus (eb7).** Il mord (M34), mais le paquet est **exactement a la limite : 12 items sur 24** (calcul sur les longueurs en caracteres, YAML de `9f3fdd4b`). Un item de plus dont la bonne reponse depasse ses distracteurs fait rougir. Pas un defaut : une marge nulle, a savoir avant d'ajouter un item.

Les autres gardes annonces (cible/tache, familles, premier mot, longueur des enonces, des propositions et des indices, TYP-1, equilibre des prenoms) mordent sur au moins une mutation de chaque branche testee (tableau). Item a 20 mots exactement : 1 et 3, par decompte a la main selon le separateur du test ; M17 (+1 mot) et M28 (+1 mot) rougissent.

## 4. Jobs de `.github/workflows/ci.yml`

| Job | Etape lancee | Etat |
|---|---|---|
| `build` | "Verify" (`mvn -B verify`), jouee par `bash outils/mvn-jdk21.sh -B clean verify` (enveloppe du poste, `clean` en plus de la commande de la CI) | **lance, vert (section 2)** |
| `build` | "Package du fat jar" (`mvn -B -pl bootstrap -am package`) | **non lancee** |
| `build` | "Verifier la presence du build Maven" | non lancee |
| `chemins` | "Tester le garde-fou de chemins" (`bash .github/scripts/verifier-chemins-test.sh`) et "Verifier l'absence de chemins" (`git ls-files ... \| bash .github/scripts/verifier-chemins.sh`, sur mon arbre, ce rapport inclus) | **lances, exit 0 et exit 0** : job entier |
| `prefixes` | "Tester le garde-fou de prefixes" (exit 0) et "Verifier les prefixes", joue sur le sujet du commit de ce rapport seul, pas sur une plage `BASE..SHA` (exit 0) | **partiel** : "Tester le garde-fou de plage de commits" et les deux etapes du garde-fou de l'accent non lancees |
| `outils` | tous | **non lance** |

**Je n'annonce pas la porte technique de LP4** : `build` (etape Verify) et `chemins` sont lances, `prefixes` l'est en partie, `outils` et l'etape "Package du fat jar" ne le sont pas. Ces mesures valent pour `9f3fdd4b` (build) et pour l'arbre de ma branche (chemins), pas pour une fusion.

## 5. LP1 et LP2, lecture plus legere

**Ce que j'ai fait.** J'ai pose dans mon worktree les fichiers `adapters/adapter-content/` du sommet du lot (`git checkout <sommet> -- <chemins du diff avec eb267ba7>`), sans toucher aux worktrees des lots, puis je l'ai ramene a `9f3fdd4b` (`git status --short` vide). Pour mesurer le lot seul, j'ai retire le paquet de LP4 de cet arbre, et pour LP2 remis `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` a sa version de la base. **Je n'ai pas lance de `verify` complet sur LP1 ni sur LP2 : je ne donne aucun total de tests pour eux.** Mesures partielles : `-pl adapters/adapter-content -am` (`bootstrap -am` pour une classe de bootstrap), classes nommees ci-dessous.

**LP1** (`s57-marc-fra-ce1-21`, `56e9fd1b`, `ce1-mots-invariables`, saisie). Classes `PaquetCe1MotsInvariablesTest` (34), `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (28), `LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest` (4) : 66 tests, exit 0. Onze mutations du paquet :
- Mordues, bon motif : mot ecrit dans l'enonce (L1), indice de 14 mots (L2), mot attendu accentue (L3), enonce sans trou (L4), mot du lexique exclu (L5), forme acceptee fautive (L6), apostrophe typographique retiree (L7), renvoi a l'oreille dans l'indice (L8), type repasse a `question` (L11).
- L9, espace ordinaire avant `?` dans un enonce : verte dans la classe du lot, **rouge dans `AucunSigneDoubleNEstDetachableDeSonMotTest`** (`leCliquetDeLaDetteDeTyp1EstExact`, classe transverse, 5 tests, verte avant mutation).
- **L10, chevron sans espace insecable dans un enonce : verte partout ou je l'ai cherchee** (classe du lot, classe transverse ci-dessus). L'en-tete du paquet cite `TYP-1` et le paquet ecrit ses chevrons avec une espace insecable ; aucun test que j'ai lance ne la garde pour la saisie. LP4 a ce garde (M23). Je n'ai pas lance les tests de `bootstrap` pour LP1.

**LP2** (`s57-lea-mat-ce1-10`, `fc6ee0a1`, `ce1-masses-contenances`, paquet inchange, test et entree de catalogue ajoutes). Classes `PaquetCe1MassesContenancesTest` (34), `AucunSigneDoubleNEstDetachableDeSonMotTest` (5), `LeRang...` (28) : 67 tests, exit 0. Neuf mutations du paquet, toutes rouges, bon motif : unite hors ligne (P1), nombre au-dessus de mille (P2), bonne reponse pas la plus lourde (P3), indice qui deduit la masse de la taille (P5), litre en minuscule (P7), `texteSupport` (P8), proposition qui change d'unite (P9). Deux ne sont pas vues par la classe du lot et le sont par une classe transverse : enonce de 25 mots (P4, `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest`, bootstrap, rouge) et espace ordinaire avant `?` (P6, `AucunSigneDoubleNEstDetachableDeSonMotTest`, rouge). L'en-tete du paquet declare ces deux regles, la classe du lot ne les teste pas, le depot les garde ailleurs.

## 6. Limites

- Une seule execution du `verify` complet, sur Windows, JDK 21.0.10. Pas de relance pour juger la stabilite.
- Les mutations couvrent les gardes annonces et quelques sondes ; elles ne prouvent pas l'absence d'autres angles morts. Les angles lexicaux (G2) sont infinis par nature.
- L'ecart de duree avec `theo-archi` (541 s contre 699 s) est constate, pas explique.
- Le delta de +53 s'appuie sur la mesure de `theo-archi` et de `sofia-sm` pour la base, pas sur une mesure de ma part.
- LP1 et LP2 : classes nommees seulement, base de lecture composee a la main (voir section 5), aucun total.
- Done pedagogique : non prononce ici, a `nadia-prof`.
