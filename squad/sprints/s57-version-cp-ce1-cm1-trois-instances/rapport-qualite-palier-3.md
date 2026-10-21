# [Sprint s57] Rapport qualite de `yanis-qa` - palier 3, controle croise des cinq branchements (`FRA-CE1-06`, `-07`, `-11`, `MAT-CE1-01`, `EMC-CE1-01`)

- **Auteur** : `yanis-qa`. **Date** : 2026-10-21. **Arbre mesure** : `round-two` a `b52c4ac5` (`git rev-parse round-two`). Mutations lancees dans un worktree coupe de ce commit, branche `s57-yanis-p3-mutations`, **sans aucun commit** ; worktree retire et branche supprimee a la fin (section 5). Le checkout principal n'a pas ete modifie par les mesures.
- **Ce que ce fichier ne fait pas** : il ne prononce ni Done technique ni Done pedagogique. Il n'a lance **aucun** `mvn verify` complet et aucun job de `ci.yml` (`build`, `chemins`, `prefixes`, `outils` : non lances). Il ne corrige ni code ni contenu, et ne commite rien.
- **Verdict** : sur 98 mutations valides, **96 rouges et 2 verts**. Un vert est un trou de garde (`DO2`, section 3) ; l'autre est le vert voulu de `NE5` (section 2.3). Pour chaque rouge, le test que j'avais designe avant le lancement figure parmi les echecs.

## 1. Total de tests a `round-two`

- `git rev-parse round-two` : `b52c4ac5672118254614f54548576873dd475a5d`.
- **Total au sommet : non relu.** Les rapports Surefire presents dans le checkout principal (modules `domain`, `application`, `adapters/adapter-content`, `adapters/adapter-web`, `bootstrap`) datent du **2026-10-20, 09:17 a 09:19**. Des commits touchant `adapters/` leur sont posterieurs (`git log --since=2026-10-20T09:19 round-two -- adapters domain application bootstrap` : entre autres `19aa6c98`, `62b65097`, `d0f3b1aa`, `ac986aca`, `5302af53`). Ils decrivent un arbre plus ancien que le sommet.
- Ce que ces rapports donnent (somme de l'attribut `tests` des `TEST-*.xml`, aucun rapport Failsafe present) : domain 120, application 31, adapter-content 1794, adapter-web 508, bootstrap (Surefire) 381, soit **2834** a cette date. Ce n'est pas le chiffre du sommet : **2855 n'est ni confirme ni infirme** par moi, et je n'explique pas l'ecart de 21.
- **Delta de ce rapport : 0 test ajoute ou retire.** Mesure partielle faite au sommet, temoin avant toute mutation : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=<les cinq classes> -Dsurefire.failIfNoSpecifiedTests=false` : **164 tests, 0 echec, 0 erreur, 0 ignore** (32 + 36 + 31 + 30 + 35), `BUILD SUCCESS`. JDK : `java.version` 21.0.10, lu dans les rapports Surefire du worktree. Ce chiffre vaut pour ces cinq classes seulement.

## 2. Les cinq lots

### 2.1 Methode

- **Commande de chaque lancement** : celle du temoin, `-am` present, perimetre `adapters/adapter-content`. Rien d'autre n'a ete lance (`bootstrap`, `adapter-web` : non mesures).
- **Ce qui est mute** : les vrais fichiers lus par le chargeur (`contenu/<paquet>.yaml`) et l'entree reelle du paquet dans `catalogue.yaml`, jamais un jeu ecrit a la main (`ADR-0013`, amendement : les donnees d'entree font partie du perimetre du test). Une mutation par lancement et par lot ; **cinq lots sont mutes dans le meme lancement**, chacun sur un fichier ou une entree que seule sa classe lit, et le verdict est lu **par classe** dans les XML Surefire (noms des tests rouges).
- **Restauration** : `git checkout -- adapters` apres chaque lancement, `git status --short` vide verifie apres chacun des 25 lancements mutes.
- **Lancements** : 1 temoin, puis 25 lancements mutes pour 98 mutations valides. Aucun test relance pour obtenir un resultat ; chaque ligne est une mesure unique.
- **Deux invalidations, dites** : (a) la premiere salve a ete jetee : le sous-processus lancait un autre `bash` que celui du depot, Maven n'a jamais tourne et les rapports Surefire anterieurs ont ete lus comme verts (signal : code de sortie 1 avec 0 echec). Trouve avant toute conclusion, fichiers restaures, tout refait. (b) La mutation de `NB5` sur la consigne du catalogue est sortie verte a tort une premiere fois : le remplacement avait touche la consigne d'une autre entree. Refaite sur l'entree visee, rouge.
- **Lecture des echecs** : j'ai lu les messages pour les mutations de catalogue, `NB5` et `EB5` (foyer). Pour les autres, je n'ai lu que le nom du test rouge.
- **Bornes** : les effectifs sont mutes a l'unite (19 contre 20, 21 contre 22) ; les longueurs de texte, de proposition et d'indice le sont avec de la marge (33 mots contre 30, 15 contre 12), pas a l'unite pres.

### 2.2 Par lot : test, jeux fautifs, mutations

| Lot | Classe | Tests | Jeux fautifs sous `contreexemple/<Classe>/` | Mutations | Rouges | Verts |
|---|---|---|---|---|---|---|
| `FRA-CE1-06` `ce1-inference` | `PaquetCe1InferenceTest` | 32 | 6 : `catalogue-deux-entrees`, `-espace-ordinaire`, `-manches`, `-titre-avec-niveau` ; `contenu/ce1-inference-fautif`, `-fautif-longueur` | 18 | 18 | 0 |
| `FRA-CE1-07` `ce1-texte-documentaire` | `PaquetCe1TexteDocumentaireTest` | 36 | 10 : 4 `catalogue-*` comme ci-dessus ; `contenu/` `-fautif`, `-fautif-consigne`, `-fautif-longueur`, `-fautif-proportions`, `-fautif-sans-marqueur`, `-fautif-voix` | 22 | 21 | 1 (`DO2`) |
| `FRA-CE1-11` `ce1-negation` | `PaquetCe1NegationTest` | 31 | 11 : 4 `catalogue-*` ; `contenu/` `-fautif`, `-fautif-affirmatifs`, `-elision`, `-longueur`, `-negatifs`, `-sans-famille`, `-texte-support` | 20 | 19 | 1 (`NE5`, voulu) |
| `MAT-CE1-01` `ce1-nombres-jusqu-a-1000` | `PaquetCe1NombresJusqua1000Test` | 30 | 6 : `catalogue-cinq-manches`, `-consigne-decomposer`, `-deux-entrees`, `-espace-ordinaire`, `-titre-avec-niveau` ; `contenu/ce1-nombres-fautif` | 19 | 19 | 0 |
| `EMC-CE1-01` `ce1-emc-biens-communs` | `PaquetCe1EmcBiensCommunsTest` | 35 | 5 : `catalogue-cinq-manches`, `-deux-entrees`, `-espace-ordinaire`, `-titre-avec-niveau` ; `contenu/ce1-emc-fautif` | 19 | 19 | 0 |

Total : 164 tests, 38 fichiers de jeux fautifs (`git ls-files | grep -c` sur les cinq repertoires `contreexemple/PaquetCe1...Test`), 98 mutations. **Je n'ai pas mute les jeux fautifs eux-memes** : les tests `leJeuFautifCommiteEst...` prouvent que la verification refuse ces jeux, pas que chaque jeu est fautif pour le motif que son nom annonce.

### 2.3 Une mutation par critere de la story

Critere = identifiant de la story (`S57-BR-*`, fin de `docs/backlog/backlog.md`). `K3` : entree de catalogue (unique, titre sans niveau, espace insecable, dix manches, `DEC-0121`). Colonne "Test rouge" : le test attendu parmi les echecs, tronque a 60 caracteres ; "rouge, n" : n tests rouges dans la classe.

### INF

| Critere | Mutation | Resultat | Test rouge |
|---|---|---|---|
| IN1 | dernier item supprime (19) | rouge, 2 | `in1LePaquetGardeSesVingtItemsChacunATroisPropositionsEtSonTe` |
| IN1 | item 2 reprend le texte de l'item 1 (19 textes distincts) | rouge, 2 | `in1LePaquetGardeSesVingtItemsChacunATroisPropositionsEtSonTe` |
| IN2 | les 6 enonces `Ou ...` deviennent `Pourquoi ...` (0 lieu) | rouge, 1 | `in2LesTroisSortesDInferenceSontRecomptees` |
| IN3 | bonne reponse de l'item 1 remplacee par une suite de mots du texte | rouge, 1 | `in3AucuneBonneReponseNEstEcriteTelleQuelleDansLeTexte` |
| IN4 | texte de l'item 1 a 33 mots, 3 phrases de 11 a 12 mots | rouge, 1 | `in4AucunTexteSupportNeDepasseTrenteMots` |
| IN4 | texte de l'item 1 a 5 phrases | rouge, 1 | `in4AucunTexteSupportNeDepasseQuatrePhrases` |
| IN4 | texte de l'item 1 d'une phrase de 16 mots | rouge, 1 | `in4AucunePhraseDuTexteSupportNeDepasseDouzeMots` |
| IN5 | un distracteur de 9 mots | rouge, 1 | `in5ChaquePropositionTientEnSixMotsEtLEnonceEnVingtMots` |
| IN5 | un indice de 14 mots | rouge, 1 | `in5ChaqueIndiceCompteAuPlusDouzeMotsNeRenvoiePasALOreilleEtN` |
| IN5 | tous les indices identiques | rouge, 1 | `in5ChaqueIndiceCompteAuPlusDouzeMotsNeRenvoiePasALOreilleEtN` |
| IN6 / TRV-CE1-1 | champ `texteAVoixHaute` ajoute a l'item 1 | rouge, 1 | `trv1ChaqueItemPorteUnTexteSupportEtAucunChampAVoixHaute` |
| IN6 / TRV-CE1-4 | mot `inference` dans un indice | rouge, 1 | `in6AucunMotDuLexiqueExcluNEstServiDansCeQueLEnfantLit` |
| IN7 | bonne reponse de l'item 1 seule a majuscule | rouge, 1 | `in7LaMajusculeNeDesigneJamaisLaBonneReponseSurLesVingtItems` |
| TRV-CE1-5 | indice de l'item 1 qui contient la bonne reponse | rouge, 1 | `trv5AucunIndiceNeContientUnePropositionDeSonItem` |
| K3 | titre de l'entree + ` au CE1` | rouge, 1 | `k3LEntreeDuCatalogueEstUniqueSonTitreNePorteNiNiveauNiEspace...` |
| K3 | titre de l'entree + ` ?` avec espace ordinaire | rouge, 1 | idem |
| K3 / DEC-0121 | `nombreDeManches: 5` | rouge, 1 | idem |
| K3 | entree dupliquee (identifiant `-bis`) | rouge, 1 | idem |

### DOC

| Critere | Mutation | Resultat | Test rouge |
|---|---|---|---|
| DO1 | dernier item supprime (19) | rouge, 1 | `do1LePaquetGardeSesVingtItemsChacunATroisPropositionsEtSonTe` |
| DO1 | item 2 reprend le texte de l'item 1 | rouge, 3 | `do1LePaquetGardeSesVingtItemsChacunATroisPropositionsEtSonTe` |
| DO2 | les 9 marqueurs `# consigne` deviennent `# documentaire` | rouge, 1 | `do2LesDeuxGenresSontMarquesEtRecomptesSurLeFichier` |
| DO2 | les 7 enonces `faut-il` / `met-on` sont reecrits (`doit-on`, `place-t-on`), marqueurs intacts | **vert** | aucun |
| DO2 | texte d'une consigne reduit a une instruction | rouge, 4 | `do2LesDeuxGenresSontMarquesEtRecomptesSurLeFichier` |
| DO3 | bonne reponse de l'item 1 absente du texte | rouge, 1 | `do3LaBonneReponseFigureDansLeTexte` |
| DO3 | aucun distracteur de l'item 1 dans le texte | rouge, 1 | `do3AuMoinsUnDistracteurFigureDansLeTexte` |
| DO4 | `1001` dans un indice | rouge, 1 | `do4AucunChiffreNEstServiDoncAucunNombreAuDelaDeMille` |
| DO5 | texte de l'item 1 a 35 mots | rouge, 1 | `do5AucunTexteSupportNeDepasseTrenteMots` |
| DO5 | texte de l'item 1 a 5 phrases | rouge, 1 | `do5AucunTexteSupportNeDepasseQuatrePhrases` |
| DO5 | texte de l'item 1 d'une phrase de 17 mots | rouge, 1 | `do5AucunePhraseDuTexteSupportNeDepasseDouzeMots` |
| DO6 | un distracteur de 7 mots | rouge, 1 | `do6ChaquePropositionTientEnSixMotsEtLEnonceEnVingtMots` |
| DO6 | un indice de 17 mots | rouge, 1 | `do6ChaqueIndiceCompteAuPlusDouzeMotsNeRenvoiePasALOreilleEtN` |
| DO6 | tous les indices identiques | rouge, 1 | `do6ChaqueIndiceCompteAuPlusDouzeMotsNeRenvoiePasALOreilleEtN` |
| DO6 / TRV-CE1-1 | champ `texteAVoixHaute` ajoute | rouge, 1 | `trv1ChaqueItemPorteUnTexteSupportEtAucunChampAVoixHaute` |
| DO6 / TRV-CE1-4 | mot `suffixe` dans un indice | rouge, 1 | `do6AucunMotDuLexiqueExcluNEstServiDansCeQueLEnfantLit` |
| DO7 | bonne reponse de l'item 1 seule a majuscule | rouge, 1 | `do7LaMajusculeNeDesigneJamaisLaBonneReponseSurLesVingtItems` |
| TRV-CE1-5 | indice de l'item 1 qui contient la bonne reponse | rouge, 1 | `trv5AucunIndiceNeContientUnePropositionDeSonItem` |
| K3 (4 mutations : niveau, espace ordinaire, 5 manches, entree dupliquee) | voir INF | rouge, 1 chacune | `k3LEntreeDuCatalogueEstUniqueSonTitreNePorteNiNiveauNiEspace...` |

### NEG

| Critere | Mutation | Resultat | Test rouge |
|---|---|---|---|
| NE1 | 5 derniers items supprimes (19) | rouge, 1 | `ne1LePaquetGardeAuMoinsVingtItemsChacunATroisPropositionsDis` |
| NE1 | un distracteur egal a la bonne reponse | rouge, 1 | `ne1LePaquetGardeAuMoinsVingtItemsChacunATroisPropositionsDis` |
| NE2 | 3 enonces `phrase negative` deviennent `phrase affirmative` (11 vers la negative) | rouge, 1 | `ne2LesProportionsVersLaNegativeEtVersLAffirmativeSontRecompt` |
| NE2 | 5 enonces `phrase affirmative` deviennent `phrase negative` (5 vers l'affirmative) | rouge, 1 | `ne2LesProportionsVersLaNegativeEtVersLAffirmativeSontRecompt` |
| NE3 | `jamais` dans une bonne reponse | rouge, 1 | `ne3AucuneNegationHorsNePasNEstServie` |
| NE4 | toutes les elisions `n'` remplacees par `ne ` (0) | rouge, 2 | `ne4LElisionFigureDansAuMoinsUnCinquiemeDesItems` |
| NE5 | ` ici` ajoute a toutes les bonnes reponses (la plus longue dans les 24 items) | rouge, 2 | `ne5LaBonneReponseNEstStrictementLaPlusLongueQueDansLaMoitieD` |
| NE5 | un commentaire de famille supprime | rouge, 1 | `ne5ChaqueItemNommeLaFamilleDeSesDistracteursDansLeFichier` |
| NE5 | distracteurs sans espace (1 mot chacun) : bonne reponse la plus longue en mots, pas en caracteres | **vert** | aucun |
| NE6 | champ `texteSupport` ajoute | rouge, 1 | `ne6AucunItemNePorteDeTexteSupportDeVoixHauteNiDIllustration` |
| NE6 | un distracteur de 8 mots | rouge, 1 | `ne6ChaquePropositionTientEnSixMotsEtLEnonceEnVingtMots` |
| NE6 | un indice de 15 mots | rouge, 1 | `ne6ChaqueIndiceCompteAuPlusDouzeMotsNeRenvoiePasALOreilleEtN` |
| NE6 | tous les indices identiques | rouge, 1 | `ne6ChaqueIndiceCompteAuPlusDouzeMotsNeRenvoiePasALOreilleEtN` |
| NE7 | deux distracteurs de l'item 1 en minuscule initiale | rouge, 1 | `ne7LaMajusculeNeDesigneJamaisLaBonneReponse` |
| TRV-CE1-4 | mot `complement` dans un indice | rouge, 1 | `trv4AucunMotDuLexiqueExcluNEstServiDansCeQueLEnfantLit` |
| TRV-CE1-5 | indice de l'item 1 qui contient une proposition | rouge, 1 | `trv5AucunIndiceNeContientUnePropositionDeSonItem` |
| K3 (4 mutations) | voir INF | rouge, 1 chacune | `k3LEntreeDuCatalogueEstUniqueSonTitreNePorteNiNiveauNiEspace...` |

### NOMB

| Critere | Mutation | Resultat | Test rouge |
|---|---|---|---|
| NB1 | dernier item supprime (21) | rouge, 1 | `nb1LePaquetGardeSesVingtDeuxItemsChacunATroisPropositionsEtP` |
| NB2 / NUM-CE1-1 | `1000` devient `1001` dans un enonce | rouge, 2 | `num1AucunNombreNeDepasseMille` |
| NB2 / NUM-CE1-1 | tous les nombres de trois chiffres ramenes a un chiffre | rouge, 5 | `num1LaMoitieAuMoinsDesItemsPorteUnNombreDeTroisChiffres` |
| NB3 / NUM-CE1-3 | les enonces `Lis ce nombre` deviennent `Dis ce nombre` | rouge, 3 | `num3LireEtEcrireSontServisDansLeMemePaquet` |
| NB3 / NUM-CE1-3 | les enonces `Ecris en chiffres` deviennent `Note en chiffres` | rouge, 2 | `num3LireEtEcrireSontServisDansLeMemePaquet` |
| NB3 / NUM-CE1-3 | `cinquante` ecrit `cinquant` dans un distracteur | rouge, 1 | `num3ChaqueDesignationEnLettresEstSansFauteEtLesDistracteursD` |
| NB4 / NUM-CE1-4 | centaines, dizaines, unites retires des enonces | rouge, 1 | `num4LesDecompositionsSontAssezNombreusesVocabulaireEtTroisCh` |
| NB4 / NUM-CE1-4 | vocabulaire et ecritures additives retires (0 decomposition) | rouge, 1 | `num4LesDecompositionsSontAssezNombreusesVocabulaireEtTroisCh` |
| NB5 / NUM-CE1-5 | `Decompose ce nombre` dans un enonce | rouge, 2 | `num5LeMotDecomposerNApparaitDansAucunChampLu` |
| NB5 / NUM-CE1-5 | `Decompose` dans la consigne de l'entree du catalogue | rouge, 1 | `k3LEntreeDuCatalogueEstUniqueATitreEtConsigneSansFaute` |
| NB6 / NUM-CE1-6 | tous les chiffres 0 des nombres de trois chiffres remplaces par 1 | rouge, 3 | `num6LesZerosEtLaTrancheSoixanteQuatreVingtDixNeufSontServis` |
| NB6 / NUM-CE1-6 | chiffre des dizaines 6 a 9 remplace par 5 | rouge, 3 | `num6LesZerosEtLaTrancheSoixanteQuatreVingtDixNeufSontServis` |
| NB7 / NUM-CE1-7 | un distracteur chiffre d'un chiffre de moins que les autres | rouge, 1 | `num7LesTroisPropositionsDUnItemOntLaMemeLongueur` |
| NB8 | champ `texteSupport` ajoute | rouge, 1 | `nb8AucunItemNePorteDeTexteSupportNiDeVoix` |
| NB8 | champ `texteAVoixHaute` ajoute | rouge, 1 | `nb8AucunItemNePorteDeTexteSupportNiDeVoix` |
| K3 (4 mutations) | voir INF | rouge, 1 chacune | `k3LEntreeDuCatalogueEstUniqueATitreEtConsigneSansFaute` |

### EMC

| Critere | Mutation | Resultat | Test rouge |
|---|---|---|---|
| EB1 | 5 derniers items supprimes (19) | rouge, 2 | `eb1LeChargeurLitTousLesItemsDuYamlEtIlsDepassentLePlancherDe` |
| EB2 | bonne reponse de l'item 1 : `ranger le ballon de la cour` | rouge, 2 | `eb2RangerEtRemettreASaPlaceNeSontJamaisLaBonneReponse` |
| EB3 | seule bonne reponse portant `bibliotheque` remplacee | rouge, 2 | `eb3LesTroisExemplesDuLibelleSontChacunEnBonneReponse` |
| EB4 | toutes les marques `tache 2` deviennent `tache 1` | rouge, 2 | `eb4LesDeuxTachesSontServiesEtChaqueItemEnPorteUneSeule` |
| EB4 | marque de tache supprimee sur l'item 1 | rouge, 2 | `eb4LesDeuxTachesSontServiesEtChaqueItemEnPorteUneSeule` |
| EB5 | champ `texteSupport` ajoute | rouge, 1 | `eb5ChaqueItemNePorteQueSesQuatreChampsSansTexteSupportNiVoix` |
| EB5 | champ `texteAVoixHaute` ajoute | rouge, 1 | `eb5ChaqueItemNePorteQueSesQuatreChampsSansTexteSupportNiVoix` |
| EB5 | `chez toi` dans un indice (foyer) | rouge, 1 | `eb9AucunMotDuFoyerNiPossessifDeDeuxiemePersonneDansCeQueLEnf` |
| EB6 | une proposition de 8 mots | rouge, 1 | `eb6ChaquePropositionTientEnSixMotsEtUneSeulePhraseEtChaqueIt` |
| EB6 | un indice de 15 mots | rouge, 1 | `eb6ChaqueIndiceTientEnDouzeMotsEtNeContientAucuneProposition` |
| EB6 | tous les indices identiques | rouge, 1 | `eb6LIndiceNEstPasIdentiqueSurToutLePaquet` |
| EB7 / TRV-CE1-2 | ` ici` ajoute a toutes les bonnes reponses | rouge, 2 | `eb7LaBonneReponseNEstStrictementLaPlusLongueQueDansLaMoitieD` |
| EB7 / TRV-CE1-2 | commentaire de famille supprime sur l'item 1 | rouge, 1 | `bco2LesFamillesDeclareesSontCoherentesAvecLaTacheEtAvecLeCon` |
| BCO-CE1-1 | famille `z, z` hors de la tache | rouge, 1 | `bco2LesFamillesDeclareesSontCoherentesAvecLaTacheEtAvecLeCon` |
| TRV-CE1-5 | indice de l'item 1 qui contient une proposition | rouge, 1 | `eb6ChaqueIndiceTientEnDouzeMotsEtNeContientAucuneProposition` |
| K3 (4 mutations) | voir INF | rouge, 1 chacune | `k3LEntreeDuCatalogueEstUniqueATitreEtConsigneSansFaute` |

Le vert de `NE5` est voulu : `Q-X2` est repondue (`reponses-nadia-questions-stories-palier-3.md`, section 2 ; amendement du backlog : le test asserte les caracteres seuls). La mutation prouve que la mesure en mots a bien ete retiree.

## 3. Ce que le controle croise montre que `marc-dev`, `lea-dev` et `nadia-prof` n'ont pas vu

Une chose, mesuree.

- **`DO2` (`FRA-CE1-07`, `DOC-CE1-1`) : le test recompte les consignes sur un commentaire, pas sur l'enonce.** La story fonde le recompte sur l'enonce (`grep -cE 'enonce: .*(faut-il|met-on)' F` rend 9) et ajoute que "le test du developpeur se lit contre `DOC-CE1-1`". `PaquetCe1TexteDocumentaireTest.do2LesDeuxGenresSontMarquesEtRecomptesSurLeFichier` lit les lignes `  # consigne` et `  # documentaire` au-dessus de chaque item. Mutation : les enonces `faut-il` et `met-on` reecrits (`doit-on`, `place-t-on`), marqueurs intacts. Le recompte de la story passe de 9 a 2, sous le quart exige (5 sur 20) ; **le test reste vert**. La mutation inverse (marqueur change, enonce intact) est rouge. Le test garde l'accord du fichier avec ses propres marqueurs, pas avec ce que l'enfant lit. Je ne tranche pas laquelle des deux lectures est la bonne : la story dit "enonce", le test dit "marqueur", c'est a `camille-po` de le reformuler, par `sofia-sm`. Je n'ai trouve cet ecart dans aucun de `rapport-sofia-dev-palier-3.md`, `lecture-nadia-palier-3.md`, `verdict-nadia-palier-3-ce1.md` (`git grep` sur `marqueur` et `faut-il`).

Rien d'autre : les 96 autres mutations rougissent sur un test que la story designe ou que le test du paquet nomme d'apres elle.

## 4. Trous de garde connus

### 4.1 Criteres des stories qu'aucun test ne garde

- **Clauses de procedure, non testables dans `adapter-content`** : `K1`, `K2`, `K5`, `K6`, `K7`, `K8`, `K9`, `K10`, `LC`. Elles se verifient par commande (`git diff-tree`, `git grep`, `git merge-base`). Non rejouees ici, sauf deux lectures de depot au sommet : la ligne `lecture complete : <paquet>, N items lus` existe pour les cinq paquets (20, 20, 24, 22, 24) ; la phrase de `MATRICE-couverture.md` exigee pour `MAT-CE1-01` rend 1.
- **`K4` et `EB8`, seconde moitie** : `ChaqueJeuDuCatalogueReelEstAtteignableDansLesDeuxVuesDeLAccueilTest` est dans `bootstrap`. **Non mute, non lance** (perimetre `adapter-content` seul).
- **`DO2`, par l'enonce** : section 3.
- **`IN7`, `DO7` (audits)** : l'intrus du premier mot, le mot cite entre chevrons, l'indice qui dit ou regarder. Le Javadoc de `PaquetCe1InferenceTest` les declare non gardes (`K8`). Non mutes : ils ne se mutent pas par un champ.
- **`IN3`** : la mesure voit une sous-chaine contigue, pas des mots du texte non contigus (la story le dit).
- **`NE3`** : la mesure lit un mot (`jamais`), pas une negation ni le temps du verbe (la story le dit).
- **`EB2`** : une bonne reponse qui paraphrase `ranger` echappe a la mesure (la story le dit).
- **`EB5`, seconde moitie** : le test garde les mots du foyer et les possessifs de deuxieme personne (mutation `chez toi`, rouge). Une commune, un type d'ecole ou une configuration familiale sans ces mots ne sont vus par aucun test (lu dans le motif `MOTS_DU_FOYER`, non mute).
- **`NEG-CE1-3`** : `verifierLesFamillesNommees` compte un commentaire a deux lettres de a a d par item, il ne juge pas sa verite (lu dans le code, non mute).
- **A `nadia-prof` seule** (stories, "Ce que la story ne dit pas") : exactitude des reponses et des faits, `TRV-CE1-6` pour les lots qui ne le portent pas, `NRC-CE1-1` (aucun garde ne compare deux paquets), `BCO-CE1-2`, le pli a 744 x 551, les items joues texte masque.

### 4.2 Ecart `E3` : garde generique de `LON-CE1-3` a `-5` et `TRV-CE1-1`

Decide par `theo-archi` (`decision-theo-garde-ce1-e3.md`, forme retenue : un test generique derive du descripteur `niveau: "CE1"`), **non livre** : `git ls-files | grep -c LesPropositionsEtLesTextesSupportsDeCe1` rend **0** a `b52c4ac5`. Ces regles ne sont gardees que par la copie d'assertions de chaque `PaquetCe1*Test` (les cinq lots les gardent : section 2.3). Un paquet CE1 depose sans `PaquetCe1<Nom>Test` n'est juge par rien.

### 4.3 `rapport-qualite-palier-3-mutations-css.md` (2026-10-20, arbre `558dd887`)

| Mutation | Trou | Etat au sommet |
|---|---|---|
| `ME8`, `ME9` | un echappement CSS (`\66ont-family`, `@\66ont-face`) passe le garde de polices | `19aa6c98` ([THEO], present sur `round-two`) annonce le decodage des echappements, avec des fixtures `echappement-sans-police`. **Non remesure par moi** : a rejouer |
| `ME10` | `font-family: inherit` refuse | `19aa6c98` annonce `inherit` admis. **Non remesure** |
| `ME11` | `letter-spacing`, `word-spacing`, `font-stretch`, `font-size-adjust` non vus | limite nommee par `ADR-0054`, inchangee |
| `MC8` | `:has(.est-faux, .est-juste)` sous un sujet `.message-jeu` reste vert | clause verte voulue, pas un trou |
| non mesure | garde de polices non desactive pour voir le rouge revenir ; mutations de gabarit (critere 4) ; jobs `chemins`, `prefixes`, `outils` ; navigateur | inchange |

## 5. Etat de mes outils

Worktree `s57-yanis-p3-mutations` : aucun commit, `git status --short` vide, HEAD egal a `b52c4ac5`. Retire par `git worktree remove` sans `--force` ; branche supprimee par `git branch -d`. Scripts et journaux des mutations : hors depot, non commites.

## Done

- **Done technique** : non prononce ici. Mesure partielle (cinq classes, `adapter-content`), aucun job de `ci.yml` lance, total au sommet non relu.
- **Done pedagogique** : non prononce ici, a `nadia-prof` seule. Aucune mutation de ce rapport ne change un verdict de `nadia-prof`.
