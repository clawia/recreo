# Rapport qualite du palier 4, lots LP5 et LP3

Auteur : `yanis-qa`, transverse. Date : 2026-10-22. `round-two` est a `51a9d957`.
- LP5 : branche `s57-sofia-emc-ce1-03`, sommet `db5e0e6d`, code `edf2b03b`, paquet `ce1-emc-droit-devoir` (lot de `sofia-sm` : ce rapport qualite est le mien seul).
- LP3 : branche `s57-lea-mat-ce1-11`, sommet `c8404173`, code `8f25fd64`, paquet `ce1-calendrier-dates`.

Mesures faites dans mes worktrees (`s57-yanis-lp5` pour les `verify`, `s57-yanis-p4-lp3-lp5` pour les mutations de LP5), mis sur chaque sommet nomme, propres avant chaque mesure. Les worktrees des lots n'ont pas ete touches.

**Ce rapport ne prononce pas le Done pedagogique** (`nadia-prof`, seule).

## 1. Resultat

1. LP5 `db5e0e6d` : `bash outils/mvn-jdk21.sh -B clean verify`, **exit 0, 3084 tests, 0 echec** (annonce de `sofia-sm` : 3084). Les 64 tests de `PaquetCe1EmcDroitDevoirTest` passent.
2. LP3 `c8404173` : `verify` complet, **exit 0, 3070 tests, 0 echec**. Les 50 tests de `PaquetCe1CalendrierDatesTest` passent.
3. LP5 : porte technique verte sur tout ce que j'ai lance (`build` Verify et Package, `prefixes`, `chemins`, `outils`).
4. LP5, **ce qui ne mord pas** : le couple declare n'est pas relie a la situation (D15), un mot repris de quatre lettres (F02), trois facons de dire qu'un droit se merite (L04, L05, L08). Les autres gardes mordent (section 4).
5. LP3, **ce qui ne mord pas** : trois exclusions hors liste (matin, ete, semaine), une date de fete hors liste, un indice qui donne la reponse dans la forme "nombre de jours" ou sans l'article (section 5). **Les deux dettes nommees rougissent quand le paquet est corrige**, comme prevu : une entree qui ne designe plus rien est refusee (D1 a D4).

## 2. `verify` complet, sous le jeton RF1

Commande, pour chacun : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`. JDK 21.0.10 (propriete `java.version` des `TEST-*.xml`), 0 echec, erreur ou ignore. Un lot a la fois, jeton acquis par `mkdir` et supprime apres chaque etape (le jeton d'un autre agent a retarde ma premiere etape : je ne lance rien tant que `mkdir` echoue).

| Lot | Sommet | Exit | Duree | domain | application | adapter-web | adapter-content | bootstrap (381) + integration (6) | **Total** |
|---|---|---|---|---|---|---|---|---|---|
| LP5 | `db5e0e6d` | 0 | 566 s | 120 | 31 | 523 | 2023 | 387 | **3084** |
| LP3 | `c8404173` | 0 | 500 s | 120 | 31 | 523 | 2009 | 387 | **3070** |

Methode : attribut `tests` des 323 `TEST-*.xml` de chaque mesure, par module. Les durees sont celles de ce poste, avec des mutations tournant en parallele sur un autre worktree pendant le `verify` de LP3 : elles ne se comparent pas.

**Delta** : l'ecart entre les deux lots est **14 = 64 - 50**, les tests de classe de LP5 moins ceux de LP3 : les deux lots sont coherents avec une base commune de **3020** tests. **Je n'ai pas mesure `51a9d957` lui-meme** : cette base est deduite, pas mesuree. Ce sont des mesures de lots isoles, pas de l'arbre fusionne ; la somme des deux lots ne vaut pas total (ils touchent des fichiers partages : `catalogue.yaml`, `TexteAccentueExposeParLePortEntrantTest`).

## 3. Jobs de `.github/workflows/ci.yml` lances, LP5 `db5e0e6d`

| Job | Etapes | Resultat |
|---|---|---|
| `build` | "Verify" : `bash outils/mvn-jdk21.sh -B clean verify` | exit 0 (section 2) |
| `build` | "Package du fat jar" : `bash outils/mvn-jdk21.sh -B clean -pl bootstrap -am package`, sous le jeton | exit 0, 411 s, `bootstrap/target/recreo.jar` present, 3078 tests rejoues, 0 echec (bootstrap et ses dependances, sans les 6 d'integration) |
| `build` | "Verifier la presence du build Maven" | non lancee (teste `pom.xml`) |
| `prefixes` | les 5 etapes, verification sur `51a9d957..db5e0e6d` sans fusions | exit 0 |
| `chemins` | les 2 etapes | exit 0 |
| `outils` | balayage des trois motifs | exit 0, 24 juges, 0 echec, aucun motif vide |

Ecarts a la CI : enveloppe et interpreteur du poste, `clean` ajoute, plage de `prefixes` celle du lot. **Porte technique de LP5 au sommet `db5e0e6d` : verte sur tout ce que j'ai lance**, soit tous les jobs sauf l'etape triviale de presence de `pom.xml`. **Pour LP3, seul `verify` est lance** : la porte technique de LP3 n'est pas annoncee (ni `prefixes`, ni `chemins`, ni `outils`, ni "Package du fat jar").

## 4. Mutations de `PaquetCe1EmcDroitDevoirTest` (LP5)

**Methode.** Une mutation du paquet `ce1-emc-droit-devoir.yaml` ou de son entree de catalogue (donnees reelles), puis
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am -Dtest=PaquetCe1EmcDroitDevoirTest -Dsurefire.failIfNoSpecifiedTests=false test`
(perimetre : classe seule, 64 tests, `-am` present), verdict lu dans le XML Surefire, mutation annulee, `git status --short` vide apres chaque execution (53 executions). **Pour les six mutations restees vertes dans la classe (D15, F02, L03, L04, L05, L08)** : second passage en mesure partielle `-pl adapters/adapter-content,bootstrap -am` avec `PaquetCe1EmcDroitDevoirTest` et les gardes transverses (`*Aucun*Test`, `*Chaque*Test`, `*LeRang*Test`, `TexteAccentueExposeParLePortEntrantTest`, `com.recreo.archi.*Test` : 577 tests, verts avant mutation).

| Id | Garde | Mutation | Resultat |
|---|---|---|---|
| D01, D02 | couples fermes, liste | couple 1 reecrit ; cinquieme couple | rouge, motif : la liste n'est pas celle de nadia-prof |
| D03 | formulations | couple 1 droit reduit a deux formulations | rouge, motif : au moins trois formulations |
| D04 | formulations | formulation partagee entre deux couples | rouge, motif : "ecrite deux fois" |
| D05 | declaration | couple 5 hors liste | rouge, motif : bonne reponse sans formulation du couple |
| D06 | sens | sens declare inverse de la question ("Quel devoir") | rouge, motif : la question ne demande pas le cote annonce |
| D07 | declaration | bonne reponse d'un autre couple | rouge, motif : "n'est pas une formulation du devoir du couple 1" |
| D08 | deux sens | couple 4 devoir->droit ramene a un item | rouge, motif : "pas servi dans les deux sens" |
| D10 | familles | un distracteur est une formulation d'un couple | rouge, motif : distracteur formulation |
| D11, D12, D14 | familles | distracteur hors famille declaree ; famille que le sens n'admet pas ; famille non declaree | rouge, motif : famille |
| D13 | familles | famille declaree sans item (decor) | rouge, motif : "la famille g sert moins de 3 items" |
| **D15** | declaration | couple declare 2 ("etre respecte") pour une situation du couple 1 (apprendre), avec une bonne reponse du couple 2 | **VERT, 64 sur 64 ; 577 sur 577 avec les gardes transverses et bootstrap. Le garde ne mord pas.** |
| F01 | mot plein repris | "suivre la lecon" devient "ecouter la lecon" (la seule bonne reponse reprend "ecout") | rouge, motif : "reprend un mot plein de l'enonce" |
| **F02** | mot plein repris | "idee" (4 lettres) repris par la seule bonne reponse | **VERT, 64 sur 64 ; 577 sur 577.** Seuls les mots d'au moins cinq lettres comptent, sur leurs cinq premieres lettres |
| F03 | negation | la seule bonne reponse porte "ne pas" | rouge, motif : "la seule a porter une negation" |
| F05 | pronom complement en tete | la seule bonne reponse commence par "le" | rouge, motif : "la seule a commencer par un pronom complement" |
| F07 | majuscule | distracteur "Lui dire la reponse" | rouge, motif : majuscule (et famille) |
| F08 | chevrons | chevrons dans l'enonce | rouge, motif : "porte des chevrons ou des guillemets" |
| F09 | guillemets droits | guillemets droits dans l'indice | rouge, meme motif |
| L01, L02 | DDE-CE1-2 | "gagne", "merite" (accent) | rouge, motif : "un droit gagne, merite ou perdu" |
| L06, L07, L09 | DDE-CE1-2 | "sinon", "en echange", "perdu" | rouge, meme motif |
| **L04** | DDE-CE1-2 | "Leo y a droit grace a son travail" | **VERT, 64 ; 577 sur 577** |
| **L05** | DDE-CE1-2 | "Il y a droit seulement s'il travaille" | **VERT, 64 ; 577 sur 577** |
| **L08** | DDE-CE1-2 | "Il y a droit pour avoir bien travaille" | **VERT, 64 ; 577 sur 577** |
| L03 | DDE-CE1-2 | "Leo le merite" sans accent | vert dans la classe, **rouge dans `AucunMotServiNeSEcarteDeSonAccentTest`** (ADR-0065 : texte lu par un enfant integralement accentue) : ferme par un garde transverse |
| N01, N02 | LON | enonce de 22 mots ; de 4 phrases | rouge, motifs LON-CE1-1, LON-CE1-2 |
| N03 | LON | proposition de 7 mots | rouge, motif : "plus de six mots" |
| N04 | LON | indice de 14 mots | rouge, motif : "plus de douze mots" |
| N05 | indices | l'indice contient la bonne reponse | rouge, motif : "contient une proposition" |
| E01, E02, E03 | EMC-1 | "Tu vois," ; "chez" dans l'indice ; "Un eleve" sans prenom | rouge, motifs : "s'adresse a l'enfant", "mot du foyer", "ne nomme aucun personnage" |
| Q01, Q02 | equilibre | "Maya" remplacee par "Alice" ; "Samir" par "Lea" (hors table) | rouge, motifs : equilibre des prenoms, prenom declare absent |
| R01 | NRC-CE1-1 | distracteur repris de `cp-emc-droits-enfant` | rouge, motif : "est une proposition d'un paquet du CP" |
| P1a, P2a | pli | enonce de 112 caracteres ; indice de 46 (limites) | **vertes, comme attendu** |
| P1b, P2b | pli | 113 ; 47 | rouge, motif : trois lignes au plus ; une ligne au plus |
| L1a | bonne reponse la plus longue | portee de 11 a 12 sur 24 (limite) | `eb7` vert, comme attendu (les mutations rallongent le texte, d'autres gardes rougissent) |
| L1b | idem | portee a 13 | rouge, motif : "strictement la plus longue" |
| K01, K02, K03 | catalogue | titre avec niveau ; espace ordinaire dans la consigne ; 5 manches | rouge |
| I01, I02 | paquet | id different du fichier ; ligne `EMC-CE1-04` | rouge |

**Marges mesurees** (sur le YAML de `db5e0e6d`) : bonne reponse la plus longue **11 sur 24** (limite 12, marge 1) ; indice le plus long **46 caracteres** (limite 46, **marge 0**) ; enonce le plus long 96 caracteres (limite 112).

### Ce qui ne mord pas, et les trous deja vus sur LP4

1. **D15 - le couple est une declaration, pas une lecture.** Le garde exige que la bonne reponse soit une formulation du couple declare et du bon cote, et que la question demande le bon cote ; il ne relie pas la situation de l'enonce au couple. Une situation sur apprendre, declaree couple 2, avec une bonne reponse du couple 2, passe. C'est la forme "garde qui ne lit pas la chose qu'il nomme" de LP4 (une question de faute qui designe la sanction).
2. **F02 - les "mots pleins" sont de cinq lettres ou plus.** Un mot repris de quatre lettres ou moins ne compte pas. C'est la meme famille que les "racines sur trois lettres" de LP4, mais ici la limite est declaree dans le code (`length() >= 5`), pas dans l'en-tete.
3. **L04, L05, L08 - liste lexicale.** `DROIT_CONDITIONNEL` ferme gagner, meriter, perdre, retirer, enlever, priver, recompenser, condition, echange, retour, sinon ; "grace a", "seulement si", "pour avoir" passent. Le garde est sur les mots (l'en-tete du paquet le dit) ; la lecture de `nadia-prof` reste la seule garde de ce qui n'est pas dans la liste. L'absence d'accent est fermee par le garde transverse ADR-0065 (L03).
4. **Non lu** : un pronom complement hors liste (`se`, `en`, `y`) n'est pas reconnu par `PRONOM_COMPLEMENT_EN_TETE` ; je ne l'ai pas mute.

## 5. Mutations de `PaquetCe1CalendrierDatesTest` (LP3, mesure plus legere)

**Methode.** Memes conventions, sur le paquet `ce1-calendrier-dates.yaml` et son entree de catalogue, au sommet `c8404173`, perimetre : classe seule, 50 tests, `-am` present. 44 executions, `git status` vide apres chaque. Le paquet est celui de s53, inchange ; la classe de test est neuve.

| Id | Garde | Mutation | Resultat |
|---|---|---|---|
| C01a, C01b, C01f | CAL-CE1-1 exclusions | "printemps", "l'heure", "lundi" | rouge, motif : "parle d'une chose que la ligne exclut" |
| **C01c, C01d, C01e** | CAL-CE1-1 | "matin", "ete", "semaine" (hors liste) | **vertes, 50 sur 50. Le garde ne mord pas** : la liste ferme heure, horloge, cadran, saison, printemps, automne, hiver et les jours de la semaine, pas les moments de la journee ni "ete" |
| C02a a C02f | CAL-CE1-3 calendrier | fevrier a 29 jours ; lendemain du 30 septembre faux ; 31 juin ; ordre de trois dates faux ; "bissextile" ; ecriture en chiffres fausse | rouge, motif : calendrier recalcule, ou distracteur hors famille |
| C03a, C03b | CAL-CE1-4 ancrage | "vacances" ; "2026" | rouge, motif : ancrage |
| C03c | CAL-CE1-4 fetes | nouvelle date de fete (le 1er mai en bonne reponse) | rouge, motif : hors de la dette nommee |
| **C03d** | CAL-CE1-4 fetes | "le 11 novembre" (fete hors liste) | **vert, 50 sur 50** : la liste est celle de l'en-tete du paquet |
| D1 | dette de famille | "le 30 juin" devient "le 2 juin" (correction du paquet) | **rouge**, motif : dette perimee, aucune occurrence ne la porte plus |
| D2, D3 | dette de fetes | "le 1er mai" devient "le 1er juillet" ; "le 31 aout" devient "le 2 aout" | **rouges** (chacune), meme motif |
| D4 | les deux dettes | les trois corrections ensemble | **rouge** : la correction du paquet doit vider les listes `DETTE_DE_FAMILLE` et `DETTE_DE_FETES` **dans le meme commit**, sinon rouge |
| D5 | dette de famille | une occurrence nouvelle (distracteur "le 15 decembre") | rouge, motif : "un distracteur d'aucune des trois familles" |
| C04a, C04b | CAL-CE1-5 distracteurs | 29 jours ; ecriture dont le jour et le mois changent | rouge |
| C05a, C05d | indices | 13 mots ; l'indice donne la bonne reponse avec l'article | rouge |
| **C05b** | indices | indice "Fevrier a 28 jours" dans la forme "nombre de jours" | **vert** : le garde ne lit que les formes autres que "nombre de jours" |
| **C05c** | indices | "Pense a 15 mars" (reponse sans l'article) | **vert** : `doesNotContain(bonneReponse)` cherche "le 15 mars" |
| C06 | rangs | l'unique item de rang bas supprime | rouge, motif : rang bas a moins de 10 % |
| N01, N02, N03 | LON | enonce de 22 mots ; de 4 phrases ; derniere phrase pas la question | rouge, LON-CE1-1 et -2 |
| T1, X1, F1, F2 | TYP-1, champs, formes, trois dates | espace ordinaire ; `texteSupport` ; item d'aucune forme ; proposition pas une des trois dates | rouge |
| K1, K2, K3, I1, I2 | catalogue, id, ligne | titre avec niveau, espace, 5 manches, id, `MAT-CE1-12` | rouge |

**Dettes nommees** : les deux sont verifiees. Corriger le paquet fait rougir le test tant que la liste n'est pas videe ; une occurrence nouvelle rougit aussi (D5, C03c). Chaque entree designe encore une occurrence reelle au sommet : le paquet n'est pas corrige. **Une correction de `nadia-prof` doit donc modifier la classe de test et le YAML dans le meme commit.**

## 6. Limites

- Une execution de chaque `verify`, sous Windows, sans rejeu pour juger la stabilite. Aucun test instable observe. Couverture chiffree non mesuree.
- Mesure partielle de LP5 : classe seule (64 tests), plus 577 tests transverses pour les six mutations restees vertes. Mesure partielle de LP3 : classe seule (50 tests), pas de passage sur les gardes transverses pour ses mutations vertes.
- Les mutations couvrent les gardes nommes ; elles ne prouvent pas l'absence d'autres angles morts. Les listes lexicales ont des voisines non couvertes.
- Done pedagogique : non prononce ici, a `nadia-prof`.
